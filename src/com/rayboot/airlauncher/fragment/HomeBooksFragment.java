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
import com.rayboot.airlauncher.adapter.HomeBookAdapter;
import com.rayboot.airlauncher.base.BaseFragment;
import com.rayboot.airlauncher.model.BookObj;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rayboot
 * @from 14-6-24 14:52
 * @TODO
 */
public class HomeBooksFragment extends BaseFragment
{
    @InjectView(R.id.gvBooks) GridView mGvBooks;

    List<BookObj> bookObjs = new ArrayList<BookObj>(12);
    HomeBookAdapter<BookObj> adapter;

    public static HomeBooksFragment newInstance()
    {
        HomeBooksFragment fragment = new HomeBooksFragment();
        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        bookObjs = BookObj.getTopObjs(12);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        View vFragment =
                inflater.inflate(R.layout.fragment_home_books, container,
                        false);
        ButterKnife.inject(this, vFragment);

        for (int i = bookObjs.size(); i <= 11; i++)
        {
            bookObjs.add(bookObjs.get(0));
        }

        adapter = new HomeBookAdapter<BookObj>(getActivity(), bookObjs);
        mGvBooks.setAdapter(adapter);

        mGvBooks.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id)
            {
                Uri uri = Uri.parse(bookObjs.get(position).filePath);
                Intent intent = new Intent(getActivity(), MuPDFActivity.class);
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(uri);
                startActivity(intent);
            }
        });

        return vFragment;
    }
}
