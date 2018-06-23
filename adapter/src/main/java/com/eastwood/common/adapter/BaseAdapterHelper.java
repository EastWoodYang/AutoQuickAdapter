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
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Allows an abstraction of the ViewHolder pattern.<br>
 * <br>
 * <p/>
 * <b>Usage</b>
 * <p/>
 * <pre>
 * return BaseAdapterHelper.get(context, convertView, parent, R.layout.item)
 *         .setText(R.id.tvName, contact.getName())
 *         .setText(R.id.tvEmails, contact.getEmails().toString())
 *         .setText(R.id.tvNumbers, contact.getNumbers().toString())
 *         .getView();
 * </pre>
 */
public class BaseAdapterHelper implements ViewHelper<BaseAdapterHelper> {

    /** Views indexed with their IDs */
    private final SparseArray<View> views;

    private final Context context;

    private int position;

    private View convertView;

    protected BaseAdapterHelper(Context context, ViewGroup parent, int layoutId) {
        this.context = context;
        this.views = new SparseArray<View>();
        convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        convertView.setTag(this);
    }

    /**
     * This method is the only entry point to get a BaseAdapterHelper.
     * @param context     The current context.
     * @param convertView The convertView arg passed to the getView() method.
     * @param parent      The parent arg passed to the getView() method.
     * @return A BaseAdapterHelper instance.
     */
    public static BaseAdapterHelper get(Context context, View convertView, ViewGroup parent, int layoutId) {
        return get(context, convertView, parent, layoutId, -1);
    }

    /** This method is package private and should only be used by QuickAdapter. */
    public static BaseAdapterHelper get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new BaseAdapterHelper(context, parent, layoutId);
        }

        // Retrieve the existing helper and update its position
        BaseAdapterHelper existingHelper = (BaseAdapterHelper) convertView.getTag();
        existingHelper.position = position;
        return existingHelper;
    }

    /**
     * Retrieve the overall position of the data in the list.
     * @throws IllegalArgumentException If the position hasn't been set at the construction of the this helper.
     */
    public int getPosition() {
        if (position == -1)
            throw new IllegalStateException("Use BaseAdapterHelper constructor " +
                    "with position if you need to retrieve the position.");
        return position;
    }

    @SuppressWarnings("unchecked")
    private  <T extends View> T retrieveView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /** Retrieve the convertView */
    public View getView() {
        return convertView;
    }

    @Override
    public <T extends View> T getView(int viewId) {
        return retrieveView(viewId);
    }

    @Override
    public BaseAdapterHelper setText(int viewId, String value) {
        TextView view = retrieveView(viewId);
        view.setText(value);
        return this;
    }

    @Override
    public BaseAdapterHelper setText(int viewId, CharSequence value) {
        TextView view = retrieveView(viewId);
        view.setText(value);
        return this;
    }

    @Override
    public BaseAdapterHelper setText(int viewId, int valueId) {
        TextView view = retrieveView(viewId);
        view.setText(valueId);
        return this;
    }

    @Override
    public BaseAdapterHelper setImageResource(int viewId, int imageResId) {
        ImageView view = retrieveView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    @Override
    public BaseAdapterHelper setBackgroundColor(int viewId, int color) {
        View view = retrieveView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    @Override
    public BaseAdapterHelper setBackgroundRes(int viewId, int backgroundRes) {
        View view = retrieveView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    @Override
    public BaseAdapterHelper setTextColor(int viewId, int textColor) {
        TextView view = retrieveView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    @Override
    public BaseAdapterHelper setTextColorRes(int viewId, int textColorRes) {
        TextView view = retrieveView(viewId);
        view.setTextColor(context.getResources().getColor(textColorRes));
        return this;
    }

    @Override
    public BaseAdapterHelper setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = retrieveView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

//    public BaseAdapterHelper setImageUrl(int viewId, String imageUrl, ImageLoader imageLoader) {
//        ImageView view = retrieveView(viewId);
//        imageLoader.displayImage(imageUrl, view);
//
////        if (!TextUtils.isEmpty(imageUrl) && view.getTag() == null
////                || !view.getTag().equals(imageUrl)) {
////            imageLoader.displayImage(imageUrl, view);
////            view.setTag(imageUrl);
////        }
//        return this;
//    }
//
//    public BaseAdapterHelper setImageUrl(int viewId, String imageUrl, ImageLoader imageLoader, DisplayImageOptions options) {
//        ImageView view = retrieveView(viewId);
//        imageLoader.displayImage(imageUrl, view, options);
//
////        if (!TextUtils.isEmpty(imageUrl) && view.getTag() == null
////                || !view.getTag().equals(imageUrl)) {
////            imageLoader.displayImage(imageUrl, view, options);
////            view.setTag(imageUrl);
////        }
//        return this;
//    }

    @Override
    public BaseAdapterHelper setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = retrieveView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    @Override
    public BaseAdapterHelper setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            retrieveView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            retrieveView(viewId).startAnimation(alpha);
        }
        return this;
    }

    @Override
    public BaseAdapterHelper setVisibility(int viewId, int visibility) {
        View view = retrieveView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    @Override
    public BaseAdapterHelper linkify(int viewId) {
        TextView view = retrieveView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    @Override
    public BaseAdapterHelper setTypeface(int viewId, Typeface typeface) {
        TextView view = retrieveView(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        return this;
    }

    @Override
    public BaseAdapterHelper setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = retrieveView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    @Override
    public BaseAdapterHelper setProgress(int viewId, int progress) {
        ProgressBar view = retrieveView(viewId);
        view.setProgress(progress);
        return this;
    }

    @Override
    public BaseAdapterHelper setProgress(int viewId, int progress, int max) {
        ProgressBar view = retrieveView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    @Override
    public BaseAdapterHelper setMax(int viewId, int max) {
        ProgressBar view = retrieveView(viewId);
        view.setMax(max);
        return this;
    }

    @Override
    public BaseAdapterHelper setRating(int viewId, float rating) {
        RatingBar view = retrieveView(viewId);
        view.setRating(rating);
        return this;
    }

    @Override
    public BaseAdapterHelper setRating(int viewId, float rating, int max) {
        RatingBar view = retrieveView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    @Override
    public BaseAdapterHelper setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = retrieveView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    @Override
    public BaseAdapterHelper setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = retrieveView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    @Override
    public BaseAdapterHelper setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = retrieveView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    @Override
    public BaseAdapterHelper setOnItemClickListener(int viewId, AdapterView.OnItemClickListener listener) {
        AdapterView view = retrieveView(viewId);
        view.setOnItemClickListener(listener);
        return this;
    }

    @Override
    public BaseAdapterHelper setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener) {
        AdapterView view = retrieveView(viewId);
        view.setOnItemLongClickListener(listener);
        return this;
    }

    @Override
    public BaseAdapterHelper setOnItemSelectedClickListener(int viewId, AdapterView.OnItemSelectedListener listener) {
        AdapterView view = retrieveView(viewId);
        view.setOnItemSelectedListener(listener);
        return this;
    }

    @Override
    public BaseAdapterHelper setTag(int viewId, Object tag) {
        View view = retrieveView(viewId);
        view.setTag(tag);
        return this;
    }

    @Override
    public BaseAdapterHelper setTag(int viewId, int key, Object tag) {
        View view = retrieveView(viewId);
        view.setTag(key, tag);
        return this;
    }

    @Override
    public BaseAdapterHelper setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) retrieveView(viewId);
        view.setChecked(checked);
        return this;
    }

    @Override
    public BaseAdapterHelper setAdapter(int viewId, Adapter adapter) {
        AdapterView view = retrieveView(viewId);
        view.setAdapter(adapter);
        return this;
    }

}
