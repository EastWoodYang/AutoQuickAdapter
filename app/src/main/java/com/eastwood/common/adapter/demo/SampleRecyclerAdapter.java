package com.eastwood.common.adapter.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SampleRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<SampleModel> sampleModelList;

    public SampleRecyclerAdapter(Context context, List<SampleModel> sampleModelList) {
        this.context = context;
        this.sampleModelList = sampleModelList;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        final RecyclerView.ViewHolder holder = new TextItemView(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TextItemView textItemView = (TextItemView) holder;
        textItemView.textView1.setText(sampleModelList.get(position).getValues());
    }

    @Override
    public int getItemCount() {
        return sampleModelList == null ? 0 : sampleModelList.size();
    }

    public class TextItemView extends RecyclerView.ViewHolder {

        private TextView textView1;

        public TextItemView(View itemView) {
            super(itemView);
            textView1 = (TextView) itemView.findViewById(R.id.textView1);
        }
    }
}