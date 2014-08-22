package com.rayboot.airlauncher.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.artifex.mupdfdemo.MuPDFActivity;
import com.balysv.material.drawable.menu.MaterialMenuDrawable;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.adapter.BaseListAdapter;
import com.rayboot.airlauncher.adapter.HomeBookAdapter;
import com.rayboot.airlauncher.adapter.HomeMusicAdapter;
import com.rayboot.airlauncher.adapter.MoviesAdapter;
import com.rayboot.airlauncher.base.BaseActionBarActivity;
import com.rayboot.airlauncher.model.BookObj;
import com.rayboot.airlauncher.model.FileObj;
import com.rayboot.airlauncher.model.MovieObj;
import com.rayboot.airlauncher.model.MusicObj;
import java.util.List;

/**
 * @author rayboot
 * @from 14-7-7 17:09
 * @TODO
 */
public class ContentListActivity extends BaseActionBarActivity
{
    int CONTENT_TYPE = 1;
    List<FileObj> contentDatas;
    BaseListAdapter<FileObj> adapter;

    @InjectView(R.id.gvContent) GridView mGvContent;

    @Override public void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_list);

        ButterKnife.inject(this);
        materialMenu.animatePressedState(MaterialMenuDrawable.IconState.X);

        CONTENT_TYPE = getIntent().getIntExtra("content_type", 1);

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
                    intent = new Intent(ContentListActivity.this,
                            MovieDetailActivity.class);
                    intent.putExtra("movie_obj", dataObj);
                    startActivity(intent);
                    break;
                case FileObj.TYPE_BOOK:
                    Uri uri = Uri.parse(dataObj.filePath);
                    intent = new Intent(ContentListActivity.this,
                            MuPDFActivity.class);
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(uri);
                    startActivity(intent);
                    break;
                case FileObj.TYPE_MUSIC:
                    intent = new Intent(ContentListActivity.this,
                            MusicPlayerActivity.class);
                    intent.putExtra("music_obj", dataObj);
                    startActivity(intent);
                    break;
                }
            }
        });
    }

    private void fillContent(int content_type)
    {
        switch (content_type)
        {
        case FileObj.TYPE_MOVIE:
            contentDatas = (List) MovieObj.getAll();
            adapter = new MoviesAdapter<FileObj>(this, contentDatas);
            break;
        case FileObj.TYPE_BOOK:
            contentDatas = (List) BookObj.getAll();
            adapter = new HomeBookAdapter<FileObj>(this, contentDatas);
            break;
        case FileObj.TYPE_MUSIC:
            contentDatas = (List) MusicObj.getAll();
            adapter = new HomeMusicAdapter<FileObj>(this, contentDatas);
            break;
        }

        mGvContent.setAdapter(adapter);
    }
    public void onEvent(View view)
    {

    }
}
