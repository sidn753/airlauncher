package com.rayboot.airlauncher.model;

import android.database.Cursor;
import android.provider.MediaStore;
import com.rayboot.airlauncher.App;

/**
 * @author rayboot
 * @from 14-7-11 10:46
 * @TODO
 */
public class MusicDetailObj
{
    public String path;
    public String name;
    public String owner;
    public int year;
    public int time;
    public String timeString;

    public MusicDetailObj(String path)
    {
        this.path = path;
        setMp3Info(path);
        timeString = (time / 60) + ":" + (time % 60);
        this.name = path.substring(path.lastIndexOf("/") + 1,
                path.lastIndexOf("."));
    }

    public static String formatDuration(long millis)
    {
        long seconds = (millis / 1000) + 1;
        return String.format("%d:%02d", seconds / 60, seconds % 60);
    }

    public void setMp3Info(String path)
    {
        // 查询媒体数据库
        Cursor cursor = App.getInstance()
                .getContentResolver()
                .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null,
                        MediaStore.Audio.Media.DATA + "='" + path + "'", null,
                        MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        // 遍历媒体数据库
        if (cursor.moveToFirst())
        {
            while (!cursor.isAfterLast())
            {
                // 歌曲编号
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(
                        MediaStore.Audio.Media._ID));
                // 歌曲id
                int trackId = cursor.getInt(cursor.getColumnIndexOrThrow(
                        MediaStore.Audio.Media.ALBUM_ID));
                // 歌曲标题
                String title = cursor.getString(cursor.getColumnIndexOrThrow(
                        MediaStore.Audio.Media.TITLE));
                // 歌曲的专辑名：MediaStore.Audio.Media.ALBUM
                String album = cursor.getString(cursor.getColumnIndexOrThrow(
                        MediaStore.Audio.Media.ALBUM));
                // 歌曲的歌手名： MediaStore.Audio.Media.ARTIST
                String artist = cursor.getString(cursor.getColumnIndexOrThrow(
                        MediaStore.Audio.Media.ARTIST));
                // 歌曲文件的路径 ：MediaStore.Audio.Media.DATA
                String url = cursor.getString(cursor.getColumnIndexOrThrow(
                        MediaStore.Audio.Media.DATA));
                // 歌曲的总播放时长：MediaStore.Audio.Media.DURATION
                int duration = cursor.getInt(cursor.getColumnIndexOrThrow(
                        MediaStore.Audio.Media.DURATION));
                // 歌曲文件的大小 ：MediaStore.Audio.Media.SIZE
                Long size = cursor.getLong(cursor.getColumnIndexOrThrow(
                        MediaStore.Audio.Media.SIZE));
                // 歌曲文件显示名字
                String disName = cursor.getString(cursor.getColumnIndexOrThrow(
                        MediaStore.Audio.Media.DISPLAY_NAME));
                // 歌曲年份
                int yearTime = cursor.getInt(cursor.getColumnIndexOrThrow(
                        MediaStore.Audio.Media.YEAR));
                this.owner = artist;
                this.year = yearTime;
                this.time = duration / 1000;
                cursor.moveToNext();
            }
            cursor.close();
        }
    }
}
