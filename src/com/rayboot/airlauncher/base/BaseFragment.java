package com.rayboot.airlauncher.base;

import android.support.v4.app.Fragment;
import de.greenrobot.event.EventBus;

/**
 * @author rayboot
 * @from 14-6-24 14:52
 * @TODO
 */
public class BaseFragment extends Fragment
{

    @Override public void onPause()
    {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override public void onResume()
    {
        super.onResume();
        EventBus.getDefault().register(this);
    }

}
