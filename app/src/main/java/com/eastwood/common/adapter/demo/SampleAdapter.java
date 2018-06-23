package com.eastwood.common.adapter.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SampleAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<SampleModel> mModelList;

    public SampleAdapter(Context context, List<SampleModel> modelList) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mModelList = modelList;
    }

    @Override
    public int getCount() {
        return mModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item, null);
            viewHolder.textView1 = (TextView) convertView.findViewById(R.id.textView1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SampleModel sampleModel = mModelList.get(position);
        if (sampleModel != null){
            viewHolder.textView1.setText(sampleModel.getValues());
        }
        return convertView;
    }

    class ViewHolder {
        public TextView textView1;
    }
}
