package com.eastwood.common.adapter;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

public interface ViewHelper<VH> {

    /** Retrieve the convertView */
    public View getView();

    /**
     * This method allows you to retrieve a view and perform custom
     * operations on it, not covered by the RecyclerAdapterHelper.<br/>
     * If you think it's a common use case, please consider creating
     * a new issue at https://github.com/JoanZapata/base-adapter-helper/issues.
     * @param viewId The id of the view you want to retrieve.
     */
    public <T extends View> T getView(int viewId);

    /**
     * Will set the text of a TextView.
     * @param viewId The view id.
     * @param value  The text to put in the text view.
     * @return The T for chaining.
     */
    public VH setText(int viewId, String value);

    public VH setText(int viewId, CharSequence value);

    public VH setText(int viewId, int valueId);

    /**
     * Will set the image of an ImageView from a resource id.
     * @param viewId     The view id.
     * @param imageResId The image resource id.
     * @return The T for chaining.
     */
    public VH setImageResource(int viewId, int imageResId);

    /**
     * Will set background color of a view.
     * @param viewId The view id.
     * @param color  A color, not a resource id.
     * @return The T for chaining.
     */
    public VH setBackgroundColor(int viewId, int color);

    /**
     * Will set background of a view.
     * @param viewId        The view id.
     * @param backgroundRes A resource to use as a background.
     * @return The T for chaining.
     */
    public VH setBackgroundRes(int viewId, int backgroundRes);

    /**
     * Will set text color of a TextView.
     * @param viewId    The view id.
     * @param textColor The text color (not a resource id).
     * @return The T for chaining.
     */
    public VH setTextColor(int viewId, int textColor);

    /**
     * Will set text color of a TextView.
     * @param viewId       The view id.
     * @param textColorRes The text color resource id.
     * @return The T for chaining.
     */
    public VH setTextColorRes(int viewId, int textColorRes);

    /**
     * Will set the image of an ImageView from a drawable.
     * @param viewId   The view id.
     * @param drawable The image drawable.
     * @return The T for chaining.
     */
    public VH setImageDrawable(int viewId, Drawable drawable);

    /** Add an action to set the image of an image view. Can be called multiple times. */
    public VH setImageBitmap(int viewId, Bitmap bitmap);

    /**
     * Add an action to set the alpha of a view. Can be called multiple times.
     * Alpha between 0-1.
     */
    public VH setAlpha(int viewId, float value);

    /**
     * Set a view visibility to VISIBLE (true) or GONE (false).
     * @param viewId  The view id.
     * @param visibility One of VISIBLE, INVISIBLE, or GONE.
     * @return The T for chaining.
     */
    public VH setVisibility(int viewId, int visibility);

    /**
     * Add links into a TextView.
     * @param viewId The id of the TextView to linkify.
     * @return The T for chaining.
     */
    public VH linkify(int viewId);

    /** Apply the typeface to the given viewId, and enable subpixel rendering. */
    public VH setTypeface(int viewId, Typeface typeface);

    /** Apply the typeface to all the given viewIds, and enable subpixel rendering. */
    public VH setTypeface(Typeface typeface, int... viewIds);

    /**
     * Sets the progress of a ProgressBar.
     * @param viewId   The view id.
     * @param progress The progress.
     * @return The T for chaining.
     */
    public VH setProgress(int viewId, int progress);

    /**
     * Sets the progress and max of a ProgressBar.
     * @param viewId   The view id.
     * @param progress The progress.
     * @param max      The max value of a ProgressBar.
     * @return The T for chaining.
     */
    public VH setProgress(int viewId, int progress, int max);

    /**
     * Sets the range of a ProgressBar to 0...max.
     * @param viewId The view id.
     * @param max    The max value of a ProgressBar.
     * @return The T for chaining.
     */
    public VH setMax(int viewId, int max);

    /**
     * Sets the rating (the number of stars filled) of a RatingBar.
     * @param viewId The view id.
     * @param rating The rating.
     * @return The T for chaining.
     */
    public VH setRating(int viewId, float rating);

    /**
     * Sets the rating (the number of stars filled) and max of a RatingBar.
     * @param viewId The view id.
     * @param rating The rating.
     * @param max    The range of the RatingBar to 0...max.
     * @return The T for chaining.
     */
    public VH setRating(int viewId, float rating, int max);

    /**
     * Sets the on click listener of the view.
     * @param viewId   The view id.
     * @param listener The on click listener;
     * @return The T for chaining.
     */
    public VH setOnClickListener(int viewId, View.OnClickListener listener);

    /**
     * Sets the on touch listener of the view.
     * @param viewId   The view id.
     * @param listener The on touch listener;
     * @return The T for chaining.
     */
    public VH setOnTouchListener(int viewId, View.OnTouchListener listener);

    /**
     * Sets the on long click listener of the view.
     * @param viewId   The view id.
     * @param listener The on long click listener;
     * @return The T for chaining.
     */
    public VH setOnLongClickListener(int viewId, View.OnLongClickListener listener);

    /**
     * Sets the listview or gridview's item click listener of the view
     * @param viewId  The view id.
     * @param listener The item on click listener;
     * @return The T for chaining.
     */
    public VH setOnItemClickListener(int viewId, AdapterView.OnItemClickListener listener);

    /**
     * Sets the listview or gridview's item long click listener of the view
     * @param viewId The view id.
     * @param listener   The item long click listener;
     * @return The T for chaining.
     */
    public VH setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener);

    /**
     * Sets the listview or gridview's item selected click listener of the view
     * @param viewId The view id.
     * @param listener The item selected click listener;
     * @return The T for chaining.
     */
    public VH setOnItemSelectedClickListener(int viewId, AdapterView.OnItemSelectedListener listener);

    /**
     * Sets the tag of the view.
     * @param viewId The view id.
     * @param tag    The tag;
     * @return The T for chaining.
     */
    public VH setTag(int viewId, Object tag);

    /**
     * Sets the tag of the view.
     * @param viewId The view id.
     * @param key    The key of tag;
     * @param tag    The tag;
     * @return The T for chaining.
     */
    public VH setTag(int viewId, int key, Object tag);

    /**
     * Sets the checked status of a checkable.
     * @param viewId  The view id.
     * @param checked The checked status;
     * @return The T for chaining.
     */
    public VH setChecked(int viewId, boolean checked);

    /**
     * Sets the adapter of a adapter view.
     * @param viewId  The view id.
     * @param adapter The adapter;
     * @return The T for chaining.
     */
    public VH setAdapter(int viewId, Adapter adapter);

}
