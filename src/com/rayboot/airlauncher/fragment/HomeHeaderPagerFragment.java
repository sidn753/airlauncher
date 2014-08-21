package com.rayboot.airlauncher.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.base.BaseFragment;
import com.rayboot.airlauncher.model.FileObj;
import com.rayboot.airlauncher.util.PicUtil;
import java.io.File;

/**
 * @author rayboot
 * @from 14-6-24 14:51
 * @TODO
 */
public class HomeHeaderPagerFragment extends BaseFragment
{
    FileObj fileObj;
    @InjectView(R.id.ivHeader) ImageView mIvHeader;

    public static HomeHeaderPagerFragment newInstance(FileObj fileObj)
    {
        HomeHeaderPagerFragment fragment = new HomeHeaderPagerFragment();
        Bundle args = new Bundle();
        args.putSerializable("file_obj", fileObj);
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        fileObj = (FileObj) getArguments().getSerializable("file_obj");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        View vFragment =
                inflater.inflate(R.layout.fragment_home_header_pager, container, false);
        ButterKnife.inject(this, vFragment);

        PicUtil.getPicasso().load(new File(fileObj.imgPath))
                .placeholder(R.drawable.ic_launcher).into(mIvHeader);
        return vFragment;
    }

    public void onEvent(View view)
    {

    }
}
