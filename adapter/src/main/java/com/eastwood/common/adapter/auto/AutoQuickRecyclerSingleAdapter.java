package com.eastwood.common.adapter.auto;

import android.content.Context;

import java.util.List;

public abstract class AutoQuickRecyclerSingleAdapter<T> extends AutoQuickRecyclerAdapter<T> {

    private static final int DEFAULT_VIEW_TYPE = -0xff;
    private final int singleLayoutResId;

    public AutoQuickRecyclerSingleAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }

    public AutoQuickRecyclerSingleAdapter(Context context, int layoutResId, List<T> data) {
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
