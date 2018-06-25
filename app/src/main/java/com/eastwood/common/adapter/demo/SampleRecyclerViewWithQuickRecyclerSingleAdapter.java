package com.eastwood.common.adapter.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.eastwood.common.adapter.QuickRecyclerSingleAdapter;
import com.eastwood.common.adapter.ViewHelper;

import java.util.ArrayList;
import java.util.List;

public class SampleRecyclerViewWithQuickRecyclerSingleAdapter extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private QuickRecyclerSingleAdapter<SampleModel> mQuickRecyclerSingleAdapter;
    private List<SampleModel> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int i = 0; i < 20; i++) {
            SampleModel sampleMode = new SampleModel();
            sampleMode.setValues("RecyclerView item_" + i);
            mDataList.add(sampleMode);
        }

        mQuickRecyclerSingleAdapter = new QuickRecyclerSingleAdapter<SampleModel>(this, R.layout.list_item, mDataList) {
            @Override
            protected void convert(int position, ViewHelper helper, SampleModel item) {
                helper.setText(R.id.textView1, item.getValues());
            }
        };

        setContentView(R.layout.sample_recycler_view);
        mRecyclerView = findViewById(R.id.refresh_layout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mQuickRecyclerSingleAdapter);
    }

}
