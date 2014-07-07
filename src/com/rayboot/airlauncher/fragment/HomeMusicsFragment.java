package com.rayboot.airlauncher.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.activity.MusicPlayerActivity;
import com.rayboot.airlauncher.adapter.HomeMusicAdapter;
import com.rayboot.airlauncher.base.BaseFragment;
import com.rayboot.airlauncher.model.MusicObj;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rayboot
 * @from 14-6-24 14:52
 * @TODO
 */
public class HomeMusicsFragment extends BaseFragment
{
    List<MusicObj> musicObjs = new ArrayList<MusicObj>(12);
    @InjectView(R.id.gvMusics) GridView mGvMusics;
    HomeMusicAdapter<MusicObj> adapter;

    public static HomeMusicsFragment newInstance()
    {
        HomeMusicsFragment fragment = new HomeMusicsFragment();
        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        musicObjs = MusicObj.getTopObjs(12);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        View vFragment =
                inflater.inflate(R.layout.fragment_home_musics, container,
                        false);
        ButterKnife.inject(this, vFragment);

        if (musicObjs.size() <= 11)
        {
            for (int i = 0; i < 11; i++)
            {
                musicObjs.add(musicObjs.get(0));
            }
        }

        adapter = new HomeMusicAdapter<MusicObj>(getActivity(), musicObjs);
        mGvMusics.setAdapter(adapter);

        mGvMusics.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id)
            {

                Intent intent =
                        new Intent(getActivity(), MusicPlayerActivity.class);
                intent.putExtra("music_obj",
                        (MusicObj) view.getTag(R.string.obj_data));
                startActivity(intent);
            }
        });
        return vFragment;
    }
}
