package com.android.fetchrewards.ui.base;

import android.content.Context;
import android.widget.BaseExpandableListAdapter;

import java.util.Map;

public abstract class BaseAdapter<K, V> extends BaseExpandableListAdapter {

    private Context mContext;

    private Map<K, V> mData;

    private K[] mGroup;

    protected BaseAdapter(Context context){
        this.mContext = context;
    }

    protected BaseAdapter(Context context, Map<K, V> data){
        this.mContext = context;
        setData(data);
    }

    protected void setData(Map<K,V> data){
        this.mData = data;
        mGroup = (K[]) data.keySet().toArray();
    }

    @Override
    public int getGroupCount() {
        return null != mData? mData.size() : 0;
    }

    @Override
    public V getGroup(int i) {
        return null != mData && mData.size() > i ? mData.get(mGroup[i]) : null;
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    protected Context getContext(){
        return mContext;
    }

    protected K getGroupItem(int position){
        return null != mGroup && mGroup.length> position? mGroup[position] : null;
    }

}
