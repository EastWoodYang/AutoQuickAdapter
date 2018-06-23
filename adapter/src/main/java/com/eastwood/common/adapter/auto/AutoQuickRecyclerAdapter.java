package com.eastwood.common.adapter.auto;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.eastwood.common.adapter.RecyclerAdapterHelper;

import java.util.List;

public abstract class AutoQuickRecyclerAdapter<T> extends BaseAutoRecyclerAdapter<T, RecyclerAdapterHelper> {

    AutoQuickRecyclerAdapter(Context context) {
        this(context, null);
    }

    protected AutoQuickRecyclerAdapter(Context context, List<T> data) {
        super(context, data);
    }

    @Override
    protected int getBodyCount() {
        return data.size();
    }

    @Override
    protected int getBodyItemViewType(int position) {
        return getItemType(position);
    }

    @Override
    public RecyclerAdapterHelper onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (isAutoLoadView(viewType)) {
            view = createAutoLoadView(parent);
        } else {
            view = getItemView(getItemLayoutId(viewType), parent);
        }
        return createViewHolder(view);
    }

    @Override
    protected void onBodyBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        convert(position, (RecyclerAdapterHelper) viewHolder, data.get(position));
    }

}
