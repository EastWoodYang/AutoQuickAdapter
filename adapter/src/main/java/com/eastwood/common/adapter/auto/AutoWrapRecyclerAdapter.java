package com.eastwood.common.adapter.auto;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.eastwood.common.adapter.RecyclerAdapterHelper;

public class AutoWrapRecyclerAdapter<T extends RecyclerView.Adapter> extends BaseAutoRecyclerAdapter {

    private final T mBase;

    public AutoWrapRecyclerAdapter(T base) {
        super();
        mBase = base;
    }

    public T getWrappedAdapter() {
        return mBase;
    }

    @Override
    protected int getBodyCount() {
        return mBase.getItemCount();
    }

    @Override
    protected int getBodyItemViewType(int position) {
        return mBase.getItemViewType(position);
    }

    @Override
    protected void onBodyBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        mBase.onBindViewHolder(viewHolder, position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (isAutoLoadView(viewType)) {
            View view = createAutoLoadView(viewGroup);
            return createBaseViewHolder(view);
        } else {
            return mBase.onCreateViewHolder(viewGroup, viewType);
        }
    }

    @Override
    protected void convert(int position, RecyclerAdapterHelper helper, Object item) {

    }

    @Override
    protected int getItemType(int position) {
        return 0;
    }

    @Override
    protected int getItemLayoutId(int type) {
        return 0;
    }

    protected RecyclerAdapterHelper createBaseViewHolder(View view) {
        return new RecyclerAdapterHelper(context, view) {
        };
    }

}