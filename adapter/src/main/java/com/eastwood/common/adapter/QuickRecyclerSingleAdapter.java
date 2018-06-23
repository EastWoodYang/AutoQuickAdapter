package com.eastwood.common.adapter;

import android.content.Context;

import java.util.List;

public abstract class QuickRecyclerSingleAdapter<T> extends BaseQuickRecyclerAdapter<T, RecyclerAdapterHelper> {

    private static final int DEFAULT_VIEW_TYPE = -0xff;
    private final int singleLayoutResId;

    public QuickRecyclerSingleAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }

    public QuickRecyclerSingleAdapter(Context context, int layoutResId, List<T> data) {
        super(context, data);
        singleLayoutResId = layoutResId;
    }

    @Override
    protected int getItemType(int position) {
        return DEFAULT_VIEW_TYPE;
    }

    @Override
    protected int getItemLayoutId(int type) {
        return singleLayoutResId;
    }
}
