package com.eastwood.common.adapter.auto;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.eastwood.common.adapter.BaseAdapterHelper;
import com.eastwood.common.adapter.ViewHelper;

public class AutoWrapAdapter<A extends BaseAdapter> extends BaseAutoAdapter {

    private final A mBase;

    public AutoWrapAdapter(A mBase) {
        this.mBase = mBase;
    }

    public A getWrappedAdapter() {
        return mBase;
    }

    @Override
    protected int getBodyCount() {
        return mBase.getCount();
    }

    @Override
    public Object getItem(int position) {
        if (getItemViewType(position) == 0) {
            return mBase.getItem(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (getItemViewType(position) == 0) {
            return mBase.getItemId(position);
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position >= mBase.getCount() ? 1 : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (getItemViewType(position) == 0) {
            if (convertView != null && convertView.getTag() == null) {
                convertView = null;
            }
            return mBase.getView(position, convertView, parent);
        }
        return createAutoLoadView(convertView, parent);
    }

    @Override
    protected void convert(int position, ViewHelper helper, Object item) {

    }

    @Override
    protected BaseAdapterHelper getAdapterHelper(int position, View convertView, ViewGroup parent) {
        return null;
    }

}
