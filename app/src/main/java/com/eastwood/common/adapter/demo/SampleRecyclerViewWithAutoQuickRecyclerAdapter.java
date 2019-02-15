package com.eastwood.common.adapter.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eastwood.common.adapter.ViewHelper;
import com.eastwood.common.adapter.auto.AutoQuickRecyclerAdapter;
import com.eastwood.common.adapter.auto.OnAutoLoadListener;

import java.util.ArrayList;
import java.util.List;

public class SampleRecyclerViewWithAutoQuickRecyclerAdapter extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AutoQuickRecyclerAdapter<SampleModel> mAutoQuickRecyclerAdapter;
    private List<SampleModel> mDataList = new ArrayList<>();
    private int mAutoLoadCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int i = 0; i < 20; i++) {
            SampleModel sampleMode = new SampleModel();
            sampleMode.setValues("RecyclerView item " + i);
            mDataList.add(sampleMode);
        }

        mAutoQuickRecyclerAdapter = new AutoQuickRecyclerAdapter<SampleModel>(this, mDataList) {

            @Override
            protected int getItemType(int position) {
                if (position % 2 == 0) {
                    return 1;
                } else {
                    return 2;
                }
            }

            @Override
            protected int getItemLayoutId(int type) {
                if (type % 2 == 0) {
                    return R.layout.list_item;
                } else {
                    return R.layout.list_item_2;
                }
            }

            @Override
            protected void convert(int position, ViewHelper helper, SampleModel item) {
                helper.setText(R.id.textView1, item.getValues());
            }
        };

        setContentView(R.layout.sample_recycler_view);
        mRecyclerView = findViewById(R.id.refresh_layout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAutoQuickRecyclerAdapter);
        mAutoQuickRecyclerAdapter.setAutoLoadEnable(true);
        mAutoQuickRecyclerAdapter.setOnAutoLoadListener(new OnAutoLoadListener() {
            @Override
            public void onLoading() {
                handleAutoLoadEvent();
            }
        });
        mAutoQuickRecyclerAdapter.setOnLastItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAutoQuickRecyclerAdapter.onAutoLoadStart();
            }
        });
    }

    private void handleAutoLoadEvent() {
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mAutoLoadCount == 0) {
                    mAutoLoadCount++;
                    mAutoQuickRecyclerAdapter.onAutoLoadError();
                    return;
                }

                for (int i = 0; i < 20; i++) {
                    SampleModel sampleMode = new SampleModel();
                    sampleMode.setValues("RecyclerView add item " + (20 * mAutoLoadCount + i) + " by auto-load");
                    mDataList.add(sampleMode);
                }
                mAutoQuickRecyclerAdapter.notifyDataSetChanged();

                if (mAutoLoadCount < 2) {
                    mAutoLoadCount++;
                    mAutoQuickRecyclerAdapter.onAutoLoadFinished(true);
                } else {
                    mAutoQuickRecyclerAdapter.onAutoLoadFinished(false);
                }
            }
        }, 1500);
    }
}
