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
        if (position < mBase.getCount()) {
            return mBase.getItem(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (position < mBase.getCount()) {
            return mBase.getItemId(position);
        }
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return mBase.getViewTypeCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mBase.getCount()) {
            return mBase.getItemViewType(position);
        }
        return mBase.getViewTypeCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position < mBase.getCount()) {
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
