package com.eastwood.common.adapter.auto;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.eastwood.common.adapter.BaseAdapterHelper;

import java.util.List;

public abstract class AutoQuickAdapter<T> extends BaseAutoAdapter<T> {

    public AutoQuickAdapter(Context context, int layoutResId) {
        super(context, layoutResId, null);
    }

    public AutoQuickAdapter(Context context, int layoutResId, List<T> data) {
        super(context, layoutResId, data);
    }

    protected BaseAdapterHelper getAdapterHelper(int position, View convertView, ViewGroup parent) {
        return BaseAdapterHelper.get(context, convertView, parent, layoutResId, position);
    }

    @Override
    protected int getBodyCount() {
        return data.size();
    }

}
