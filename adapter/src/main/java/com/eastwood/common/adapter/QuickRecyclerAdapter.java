package com.eastwood.common.adapter;

import android.content.Context;

import java.util.List;

public abstract class QuickRecyclerAdapter<T> extends BaseQuickRecyclerAdapter<T, RecyclerAdapterHelper> {

    public QuickRecyclerAdapter(Context context) {
        super(context);
    }

    public QuickRecyclerAdapter(Context context, List<T> data) {
        super(context, data);
    }

}
