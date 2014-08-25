package com.rayboot.airlauncher.musicservice;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;
import com.rayboot.airlauncher.model.MusicDetailObj;
import java.io.File;
import java.io.IOException;

/**
 * @author rayboot
 * @from 14-7-11 11:30
 * @TODO
 */
public class MusicService extends Service
        implements MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
        MusicFocusable
{

    // our media player
    MediaPlayer mPlayer = null;

    // our AudioFocusHelper object, if it's available (it's available on SDK level >= 8)
    // If not available, this will be null. Always check for null before using!
    AudioFocusHelper mAudioFocusHelper = null;

    ICurMusicListener curMusicListener = null;

    // indicates the state our service:
    public enum State
    {
        Stopped,    // media player is stopped and not prepared to play
        Preparing,  // media player is preparing...
        Playing,    // playback active (media player ready!). (but the media player may actually be
        // paused in this state if we don't have audio focus. But we stay in this state
        // so that we know we have to resume playback once we get focus back)
        Paused      // playback paused (media player ready!)
    }

    //    State mState = State.Retrieving;
    State mState = State.Stopped;

    public State getState()
    {
        return mState;
    }

    // if in Retrieving mode, this flag indicates whether we should start playing immediately
    // when we are ready or not.
    boolean mStartPlayingAfterRetrieve = false;

    // if mStartPlayingAfterRetrieve is true, this variable indicates the URL that we should
    // start playing when we are ready. If null, we should play a random song from the device
    Uri mWhatToPlayAfterRetrieve = null;

    enum PauseReason
    {
        UserRequest,  // paused by user request
        FocusLoss,    // paused because of audio focus loss
    }

    // why did we pause? (only relevant if mState == State.Paused)
    PauseReason mPauseReason = PauseReason.UserRequest;

    // do we have audio focus?
    enum AudioFocus
    {
        NoFocusNoDuck,    // we don't have audio focus, and can't duck
        NoFocusCanDuck,   // we don't have focus, but can play at a low volume ("ducking")
        Focused           // we have full audio focus
    }

    AudioFocus mAudioFocus = AudioFocus.NoFocusNoDuck;

    // The tag we put on debug messages
    final static String TAG = "RandomMusicPlayer";

    // The volume we set the media player to when we lose audio focus, but are allowed to reduce
    // the volume instead of stopping playback.
    public final float DUCK_VOLUME = 0.1f;

    PlayList playList;

    public PlayList getPlayList()
    {
        return playList;
    }

    public void setPlayList(PlayList playList)
    {
        this.playList = playList;
    }

    public void setCurMusicListener(ICurMusicListener curMusicListener)
    {
        this.curMusicListener = curMusicListener;
    }

    /**
     * Makes sure the media player exists and has been reset. This will create
     * the media player
     * if needed, or reset the existing media player if one already exists.
     */
    void createMediaPlayerIfNeeded()
    {
        if (mPlayer == null)
        {
            mPlayer = new MediaPlayer();

            // Make sure the media player will acquire a wake-lock while playing. If we don't do
            // that, the CPU might go to sleep while the song is playing, causing playback to stop.
            //
            // Remember that to use this, we have to declare the android.permission.WAKE_LOCK
            // permission in AndroidManifest.xml.
            mPlayer.setWakeMode(getApplicationContext(),
                    PowerManager.PARTIAL_WAKE_LOCK);

            // we want the media player to notify us when it's ready preparing, and when it's done
            // playing:
            mPlayer.setOnPreparedListener(this);
            mPlayer.setOnCompletionListener(this);
            mPlayer.setOnErrorListener(this);
        }
        else
        {
            mPlayer.reset();
        }
    }

    @Override
    public void onCreate()
    {
        mAudioFocusHelper = new AudioFocusHelper(getApplicationContext(), this);
    }

    /**
     * Called when we receive an Intent. When we receive an intent sent to us
     * via startService(),
     * this is the method that gets called. So here we react appropriately
     * depending on the
     * Intent's action, which specifies what is being requested of us.
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        // String action = intent.getAction();

        return START_NOT_STICKY; // Means we started the service, but don't want it to
        // restart in case it's killed.
    }

    public void processPlayPauseRequest()
    {
        switch (mState)
        {
        case Stopped:
            // If we're stopped, just go ahead to the next song and start playing
            tryToGetAudioFocus();
            playNextSong();
            break;

        case Paused:
            // If we're paused, just continue playback and restore the 'foreground service' state.
            tryToGetAudioFocus();
            mState = State.Playing;
            configAndStartMediaPlayer();
            break;

        case Playing:
        case Preparing:
            // Pause media player and cancel the 'foreground service' state.
            mState = State.Paused;
            mPlayer.pause();
            relaxResources(
                    false); // while paused, we always retain the MediaPlayer
            giveUpAudioFocus();
            break;
        }
    }

    public void processPlayNextRequest()
    {
        tryToGetAudioFocus();
        playNextSong();
    }

    public void processPlayPreRequest()
    {
        tryToGetAudioFocus();
        playPreSong();
    }

    public void processPlayNowRequest(MusicDetailObj file)
    {
        tryToGetAudioFocus();
        playSong(file);
    }

    public void setPosition(int pos)
    {
        if (mPlayer != null)
        {
            mPlayer.seekTo(pos);
        }
    }

    /**
     * Releases resources used by the service for playback. This includes the
     * "foreground service"
     * status and notification, the wake locks and possibly the MediaPlayer.
     *
     * @param releaseMediaPlayer Indicates whether the Media Player should also
     * be released or not
     */
    void relaxResources(boolean releaseMediaPlayer)
    {
        // stop being a foreground service
        stopForeground(true);

        // stop and release the Media Player, if it's available
        if (releaseMediaPlayer && mPlayer != null)
        {
            mPlayer.reset();
            mPlayer.release();
            mPlayer = null;
        }
    }

    void giveUpAudioFocus()
    {
        if (mAudioFocus == AudioFocus.Focused
                && mAudioFocusHelper != null
                && mAudioFocusHelper.abandonFocus())
        {
            mAudioFocus = AudioFocus.NoFocusNoDuck;
        }
    }

    /**
     * Reconfigures MediaPlayer according to audio focus settings and
     * starts/restarts it. This
     * method starts/restarts the MediaPlayer respecting the current audio
     * focus
     * state. So if
     * we have focus, it will play normally; if we don't have focus, it will
     * either leave the
     * MediaPlayer paused or set it to a low volume, depending on what is
     * allowed by the
     * current focus settings. This method assumes mPlayer != null, so if you
     * are calling it,
     * you have to do so from a context where you are sure this is the case.
     */
    void configAndStartMediaPlayer()
    {
        if (mAudioFocus == AudioFocus.NoFocusNoDuck)
        {
            // If we don't have audio focus and can't duck, we have to pause, even if mState
            // is State.Playing. But we stay in the Playing state so that we know we have to resume
            // playback once we get the focus back.
            if (mPlayer.isPlaying())
            {
                mPlayer.pause();
            }
            return;
        }
        else if (mAudioFocus == AudioFocus.NoFocusCanDuck)
        {
            mPlayer.setVolume(DUCK_VOLUME,
                    DUCK_VOLUME);  // we'll be relatively quiet
        }
        else
        {
            mPlayer.setVolume(1.0f, 1.0f); // we can be loud
        }

        if (!mPlayer.isPlaying())
        {
            mPlayer.start();
        }
    }

    /**
     * Shortcut to making and displaying a toast. Seemed cleaner than repeating
     * this code everywhere, at least for this sample.
     */
    void say(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    void tryToGetAudioFocus()
    {
        if (mAudioFocus != AudioFocus.Focused
                && mAudioFocusHelper != null
                && mAudioFocusHelper.requestFocus())
        {
            mAudioFocus = AudioFocus.Focused;
        }
    }

    /**
     * Starts playing the next song. If manualUrl is null, the next song will
     * be
     * randomly selected
     * from our Media Retriever (that is, it will be a random song in the
     * user's
     * device). If
     * manualUrl is non-null, then it specifies the URL or path to the song
     * that
     * will be played
     * next.
     */

    void playPreSong()
    {
        playSong(playList.preFile());
    }

    void playSong(MusicDetailObj file)
    {
        if (curMusicListener != null)
        {
            curMusicListener.currentMusic(file);
        }
        mState = State.Stopped;
        relaxResources(false); // release everything except MediaPlayer
        if (file == null)
        {
            return;
        }
        playList.setCurrent(file);
        try
        {
            // set the source of the media player a a content URI
            createMediaPlayerIfNeeded();
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.setDataSource(getApplicationContext(),
                    Uri.fromFile(new File(file.path)));

            mState = State.Preparing;

            // starts preparing the media player in the background. When it's done, it will call
            // our OnPreparedListener (that is, the onPrepared() method on this class, since we set
            // the listener to 'this').
            //
            // Until the media player is prepared, we *cannot* call start() on it!
            mPlayer.prepareAsync();
        }
        catch (IOException ex)
        {
            Log.e("MusicService",
                    "IOException playing next song: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    void playNextSong()
    {
        playSong(playList.nextFile());
    }

    /** Called when media player is done playing current song. */
    @Override
    public void onCompletion(MediaPlayer player)
    {
        // The media player finished playing the current song, so we go ahead and start the next.
        playNextSong();
    }

    /** Called when media player is done preparing. */
    @Override
    public void onPrepared(MediaPlayer player)
    {
        // The media player is done preparing. That means we can start playing!
        mState = State.Playing;
        configAndStartMediaPlayer();
    }

    /**
     * Called when there's an error playing media. When this happens, the media
     * player goes to
     * the Error state. We warn the user about the error and reset the media
     * player.
     */
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra)
    {
        Toast.makeText(getApplicationContext(),
                "Media player error! Resetting.", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "Error: what="
                + String.valueOf(what)
                + ", extra="
                + String.valueOf(extra));

        mState = State.Stopped;
        relaxResources(true);
        giveUpAudioFocus();
        return true; // true indicates we handled the error
    }

    @Override
    public void onGainedAudioFocus()
    {
        Toast.makeText(getApplicationContext(), "gained audio focus.",
                Toast.LENGTH_SHORT).show();
        mAudioFocus = AudioFocus.Focused;

        // restart media player with new focus settings
        if (mState == State.Playing)
        {
            configAndStartMediaPlayer();
        }
    }

    @Override
    public void onLostAudioFocus(boolean canDuck)
    {
        Toast.makeText(getApplicationContext(),
                "lost audio focus." + (canDuck ? "can duck" : "no duck"),
                Toast.LENGTH_SHORT).show();
        mAudioFocus =
                canDuck ? AudioFocus.NoFocusCanDuck : AudioFocus.NoFocusNoDuck;

        // start/restart/pause media player with new focus settings
        if (mPlayer != null && mPlayer.isPlaying())
        {
            configAndStartMediaPlayer();
        }
    }

    @Override
    public void onDestroy()
    {
        // Service is being killed, so make sure we release our resources
        mState = State.Stopped;
        relaxResources(true);
        giveUpAudioFocus();
    }

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder
    {
        public MusicService getService()
        {
            // Return this instance of LocalService so clients can call public methods
            return MusicService.this;
        }
    }

    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();

    @Override
    public IBinder onBind(Intent arg0)
    {
        return mBinder;
    }

    public int getPosition()
    {
        if (mPlayer != null)
        {
            return mPlayer.getCurrentPosition();
        }

        return 0;
    }
}