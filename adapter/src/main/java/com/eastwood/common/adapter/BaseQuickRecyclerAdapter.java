package com.eastwood.common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseQuickRecyclerAdapter<T, VH extends RecyclerAdapterHelper> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected final Context context;

    protected final List<T> data;

    protected LayoutInflater mLayoutInflater;

    protected BaseQuickRecyclerAdapter() {
        this(null, null);
    }

    protected BaseQuickRecyclerAdapter(Context context) {
        this(context, null);
    }

    protected BaseQuickRecyclerAdapter(Context context, List<T> data) {
        this.data = data == null ? new ArrayList<T>() : data;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public T getItem(int position) {
        if (position >= data.size()) return null;
        return data.get(position);
    }

    public List<T> getData() {
        return data;
    }

    @Override
    public int getItemViewType(int position) {
        return getItemType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getItemView(getItemLayoutId(viewType), parent);
        return createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        convert(position, (VH) viewHolder, data.get(position));
    }

    protected View getItemView(int layoutResId, ViewGroup parent) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }
        return mLayoutInflater.inflate(layoutResId, parent, false);
    }

    @SuppressWarnings("unchecked")
    protected RecyclerAdapterHelper createViewHolder(View view) {
        return new RecyclerAdapterHelper(context, view);
    }

    public void add(T elem) {
        data.add(elem);
        notifyDataSetChanged();
    }

    public void addAll(List<T> elem) {
        data.addAll(elem);
        notifyDataSetChanged();
    }

    public void set(T oldElem, T newElem) {
        set(data.indexOf(oldElem), newElem);
    }

    public void set(int index, T elem) {
        data.set(index, elem);
        notifyDataSetChanged();
    }

    public void remove(T elem) {
        data.remove(elem);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        data.remove(index);
        notifyDataSetChanged();
    }

    public void replaceAll(List<T> elem) {
        data.clear();
        data.addAll(elem);
        notifyDataSetChanged();
    }

    public boolean contains(T elem) {
        return data.contains(elem);
    }

    /**
     * Clear data list
     */
    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    protected abstract void convert(int position, VH helper, T item);

    protected abstract int getItemType(int position);

    protected abstract int getItemLayoutId(int type);

}
