package com.eastwood.common.adapter.auto;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eastwood.common.adapter.BaseQuickRecyclerAdapter;
import com.eastwood.common.adapter.R;
import com.eastwood.common.adapter.RecyclerAdapterHelper;

import java.util.List;

public abstract class BaseAutoRecyclerAdapter<T, VH extends RecyclerAdapterHelper> extends BaseQuickRecyclerAdapter<T, VH> {

    private LayoutInflater mLayoutInflater;

    private boolean autoLoadUsable;

    private boolean manualLoad;

    private boolean loadError;
    private boolean loadEnd;
    private boolean loading;

    private int loadingLayoutResId;
    private int manualLayoutResId;
    private int loadEndLayoutResId;
    private int loadErrorLayoutResId;

    private OnAutoLoadListener mOnAutoLoadListener;
    private View.OnClickListener mOnLastItemClickListener;
    private View.OnClickListener mEmptyClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private static final int AUTO_LOAD_VIEW_TYPE = -3001;
    private static final int CLICKABLE_VIEW_TYPE = -3002;
    private static final int ERROR_VIEW_TYPE = -3003;
    private static final int END_VIEW_TYPE = -3004;

    protected BaseAutoRecyclerAdapter() {
        super();
    }

    protected BaseAutoRecyclerAdapter(Context context) {
        super(context);
    }

    protected BaseAutoRecyclerAdapter(Context context, List<T> data) {
        super(context, data);
    }

    protected abstract int getBodyCount();

    protected abstract int getBodyItemViewType(int position);

    protected abstract void onBodyBindViewHolder(RecyclerView.ViewHolder viewHolder, int position);

    protected View createAutoLoadView(ViewGroup parent) {
        initLayout(parent.getContext());
        View autoLoadView = null;
        if (loadError) {
            autoLoadView = getItemView(loadErrorLayoutResId, parent);
            autoLoadView.setOnClickListener(mOnLastItemClickListener);
        } else if (loadEnd) {
            autoLoadView = getItemView(loadEndLayoutResId, parent);
            autoLoadView.setOnClickListener(mEmptyClick);
        } else {
            if (!manualLoad || loading) {
                autoLoadView = getItemView(loadingLayoutResId, parent);
                autoLoadView.setOnClickListener(mEmptyClick);
            } else {
                autoLoadView = getItemView(manualLayoutResId, parent);
                autoLoadView.setOnClickListener(mOnLastItemClickListener);
            }
        }
        return autoLoadView;
    }

    private void initLayout(Context context) {
        if (loadingLayoutResId == 0) {
            loadingLayoutResId = resolve(context, R.attr.auto_adapter_loading_layout, R.layout.default_loading_layout);
        }
        if (manualLayoutResId == 0) {
            manualLayoutResId = resolve(context, R.attr.auto_adapter_manual_layout, R.layout.default_manual_layout);
        }
        if (loadEndLayoutResId == 0) {
            loadEndLayoutResId = resolve(context, R.attr.auto_adapter_end_layout, R.layout.default_end_layout);
        }
        if (loadErrorLayoutResId == 0) {
            loadErrorLayoutResId = resolve(context, R.attr.auto_adapter_error_layout, R.layout.default_error_layout);
        }
    }

    @Override
    public int getItemCount() {
        return getBodyCount() + (autoLoadUsable ? 1 : 0);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (position < getBodyCount()) {
            // This is a real position, not a header or footer. Bind it.
            onBodyBindViewHolder(viewHolder, position);
        } else {
            // Footers don't need anything special
            if (autoLoadUsable && position + 1 == getItemCount()) {
                if (loadError) {
                    viewHolder.itemView.setOnClickListener(mOnLastItemClickListener);
                } else if (loadEnd) {
                    viewHolder.itemView.setOnClickListener(mEmptyClick);
                } else {
                    if (!manualLoad || loading) {
                        if (!loading && mOnAutoLoadListener != null) {
                            mOnAutoLoadListener.onLoading();
                        }
                        viewHolder.itemView.setOnClickListener(mEmptyClick);
                    } else {
                        viewHolder.itemView.setOnClickListener(mOnLastItemClickListener);
                    }
                }
            } else {
                // Footers don't need anything special
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position < getBodyCount()) {
            return getBodyItemViewType(position);
        } else {
            if (autoLoadUsable && position + 1 == getItemCount()) {
                if (loadError) {
                    return ERROR_VIEW_TYPE;
                } else if (loadEnd) {
                    return END_VIEW_TYPE;
                } else {
                    if (!manualLoad || loading) {
                        return AUTO_LOAD_VIEW_TYPE;
                    } else {
                        return CLICKABLE_VIEW_TYPE;
                    }
                }
            } else {
                return position - getBodyCount();
            }
        }
    }

    protected boolean isAutoLoadView(int viewType) {
        return viewType == AUTO_LOAD_VIEW_TYPE || viewType == CLICKABLE_VIEW_TYPE || viewType == ERROR_VIEW_TYPE || viewType == END_VIEW_TYPE;
    }

    protected View getItemView(int layoutResId, ViewGroup parent) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }
        return mLayoutInflater.inflate(layoutResId, parent, false);
    }

    public void setAutoLoadUsable(boolean usable) {
        if (usable == autoLoadUsable) return;
        autoLoadUsable = usable;
        notifyDataSetChanged();
    }

    public boolean isAutoLoadUsable() {
        return autoLoadUsable;
    }

    public boolean isManualLoad() {
        return manualLoad;
    }

    public void setManualLoad(boolean manualLoad) {
        if (manualLoad == this.manualLoad) return;
        this.manualLoad = manualLoad;
        notifyDataSetChanged();
    }

    public boolean isLoadError() {
        return loadError;
    }

    public void setLoadError(boolean loadError) {
        if (loadError == this.loadError) return;
        this.loadError = loadError;
        notifyDataSetChanged();
    }

    public boolean isLoadEnd() {
        return loadEnd;
    }

    public void setLoadEnd(boolean loadEnd) {
        if (loadEnd == this.loadEnd) return;
        this.loadEnd = loadEnd;
        notifyDataSetChanged();
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        if (loading == this.loading) return;
        this.loading = loading;
        notifyDataSetChanged();
    }

    public void setLoadingLayoutResId(int resId) {
        this.loadingLayoutResId = resId;
    }

    public int getLoadingLayoutResId() {
        return loadingLayoutResId;
    }

    public void setManualLayoutResId(int resId) {
        this.manualLayoutResId = resId;
    }

    public int getManualLayoutResId() {
        return manualLayoutResId;
    }

    public int getLoadEndLayoutResId() {
        return loadEndLayoutResId;
    }

    public void setLoadEndLayoutResId(int loadEndLayoutResId) {
        this.loadEndLayoutResId = loadEndLayoutResId;
    }

    public int getLoadErrorLayoutResId() {
        return loadErrorLayoutResId;
    }

    public void setLoadErrorLayoutResId(int loadErrorLayoutResId) {
        this.loadErrorLayoutResId = loadErrorLayoutResId;
    }

    public void setOnAutoLoadListener(OnAutoLoadListener listener) {
        mOnAutoLoadListener = listener;
    }

    public void setOnLastItemClickListener(View.OnClickListener listener) {
        this.mOnLastItemClickListener = listener;
    }

    public void onAutoLoadError() {
        loadError = true;
        notifyDataSetChanged();
    }

    public void onAutoLoadStart() {
        manualLoad = false;
        loading = false;
        loadError = false;
        loadEnd = false;
        notifyDataSetChanged();
    }

    public void onAutoLoadFinished(boolean loadEnd) {
        this.loading = false;
        this.loadError = false;
        this.loadEnd = loadEnd;
        notifyDataSetChanged();
    }

    private int resolve(Context context, int attr, int defValue) {
        TypedArray a = context.getTheme().obtainStyledAttributes(new int[]{attr});
        try {
            return a.getResourceId(0, defValue);
        } finally {
            a.recycle();
        }
    }

}
