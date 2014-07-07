package com.rayboot.airlauncher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import java.util.List;

/**
 * @author rayboot
 * @from 14-3-12 10:24
 * @TODO 基础adapter类，继承该类，子类专注实现getView方法
 */
public abstract class BaseListAdapter<T> extends BaseAdapter
{

    Context mContext;
    LayoutInflater mLayoutInflater;
    List<T> listDatas;

    public BaseListAdapter(Context context, List<T> listDatas) {
        // TODO Auto-generated constructor stub
        this.mContext = context;
        this.listDatas = listDatas;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public List<T> getListDatas()
    {
        return listDatas;
    }

    public void setListDatas(List<T> listDatas)
    {
        this.listDatas = listDatas;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listDatas == null ? 0 : listDatas.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return listDatas == null ? null : listDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

}
