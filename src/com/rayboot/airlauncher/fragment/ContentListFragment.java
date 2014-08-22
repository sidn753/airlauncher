package com.rayboot.airlauncher.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.artifex.mupdfdemo.MuPDFActivity;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.activity.MusicPlayerActivity;
import com.rayboot.airlauncher.adapter.BaseListAdapter;
import com.rayboot.airlauncher.adapter.HomeBookAdapter;
import com.rayboot.airlauncher.adapter.HomeMusicAdapter;
import com.rayboot.airlauncher.adapter.MoviesAdapter;
import com.rayboot.airlauncher.base.BaseFragment;
import com.rayboot.airlauncher.model.BookObj;
import com.rayboot.airlauncher.model.FileObj;
import com.rayboot.airlauncher.model.MovieObj;
import com.rayboot.airlauncher.model.MusicObj;
import com.rayboot.airlauncher.util.Util;
import java.util.List;

/**
 * @author rayboot
 * @from 14-8-21 14:45
 * @TODO
 */
public class ContentListFragment extends BaseFragment
{

    int CONTENT_TYPE = 1;
    List<FileObj> contentDatas;
    BaseListAdapter<FileObj> adapter;

    @InjectView(R.id.gvContent) GridView mGvContent;

    private static ContentListFragment contentListFragment;

    public static ContentListFragment newInstance(int type)
    {
        contentListFragment = new ContentListFragment();
        Bundle args = new Bundle();
        args.putInt("content_type", type);
        contentListFragment.setArguments(args);
        return contentListFragment;
    }

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        CONTENT_TYPE = getArguments().getInt("content_type", 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        View vFragment =
                inflater.inflate(R.layout.fragment_content_list, container,
                        false);
        ButterKnife.inject(this, vFragment);
        fillContent(CONTENT_TYPE);
        mGvContent.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id)
            {
                FileObj dataObj = (FileObj) view.getTag(R.string.obj_data);
                Intent intent = null;
                switch (CONTENT_TYPE)
                {
                case FileObj.TYPE_MOVIE:

                    Util.addFragment(contentListFragment.getChildFragmentManager(),R.id.root,
                            MovieDetailFragment.newInstance((MovieObj)dataObj));
                    //intent = new Intent(getActivity(),
                    //        MovieDetailActivity.class);
                    //intent.putExtra("movie_obj", dataObj);
                    //startActivity(intent);
                    break;
                case FileObj.TYPE_BOOK:
                    Uri uri = Uri.parse(dataObj.filePath);
                    intent = new Intent(getActivity(), MuPDFActivity.class);
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(uri);
                    startActivity(intent);
                    break;
                case FileObj.TYPE_MUSIC:
                    intent = new Intent(getActivity(),
                            MusicPlayerActivity.class);
                    intent.putExtra("music_obj", dataObj);
                    startActivity(intent);
                    break;
                }
            }
        });
        return vFragment;
    }

    private void fillContent(int content_type)
    {
        switch (content_type)
        {
        case FileObj.TYPE_MOVIE:
            contentDatas = (List) MovieObj.getAll();
            adapter = new MoviesAdapter<FileObj>(getActivity(), contentDatas);
            break;
        case FileObj.TYPE_BOOK:
            contentDatas = (List) BookObj.getAll();
            adapter = new HomeBookAdapter<FileObj>(getActivity(), contentDatas);
            break;
        case FileObj.TYPE_MUSIC:
            contentDatas = (List) MusicObj.getAll();
            adapter =
                    new HomeMusicAdapter<FileObj>(getActivity(), contentDatas);
            break;
        }

        mGvContent.setAdapter(adapter);
    }

    public void onEvent(View view)
    {

    }
}
