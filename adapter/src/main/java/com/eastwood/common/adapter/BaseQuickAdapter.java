/**
 * Copyright 2013 Joan Zapata
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.eastwood.common.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseQuickAdapter<T, VH extends BaseAdapterHelper> extends BaseAdapter {

    protected static final String TAG = BaseQuickAdapter.class.getSimpleName();

    protected final Context context;

    protected final int layoutResId;

    protected final List<T> data;

    public BaseQuickAdapter() {
        this(null, 0, null);
    }

    public BaseQuickAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }

    public BaseQuickAdapter(Context context, int layoutResId, List<T> data) {
        this.data = data == null ? new ArrayList<T>() : data;
        this.context = context;
        this.layoutResId = layoutResId;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        if (position >= data.size()) return null;
        return data.get(position);
    }

    public List<T> getData() {
        return data;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position >= data.size() ? 1 : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHelper<VH> helper = getAdapterHelper(position, convertView, parent);
        T item = getItem(position);
        convert(position, helper, item);
        return helper.getView();
    }

    @Override
    public boolean isEnabled(int position) {
        return position < data.size();
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

    /** Clear data list */
    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param position The item position.
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    protected abstract void convert(int position, ViewHelper helper, T item);

    protected abstract ViewHelper<VH> getAdapterHelper(int position, View convertView, ViewGroup parent);

}
