package com.eastwood.common.adapter.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eastwood.common.adapter.ViewHelper;
import com.eastwood.common.adapter.auto.AutoQuickRecyclerSingleAdapter;
import com.eastwood.common.adapter.auto.OnAutoLoadListener;

import java.util.ArrayList;
import java.util.List;

public class SampleRecyclerViewWithAutoQuickRecyclerAdapterManualLoad extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AutoQuickRecyclerSingleAdapter<SampleModel> mAutoQuickRecyclerSingleAdapter;
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

        mAutoQuickRecyclerSingleAdapter = new AutoQuickRecyclerSingleAdapter<SampleModel>(this, R.layout.list_item, mDataList) {
            @Override
            protected void convert(int position, ViewHelper helper, SampleModel item) {
                helper.setText(R.id.textView1, item.getValues());
            }
        };

        setContentView(R.layout.sample_recycler_view);
        mRecyclerView = findViewById(R.id.refresh_layout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAutoQuickRecyclerSingleAdapter);

        mAutoQuickRecyclerSingleAdapter.setAutoLoadUsable(true);
        mAutoQuickRecyclerSingleAdapter.setManualLoad(true);
        mAutoQuickRecyclerSingleAdapter.setOnAutoLoadListener(new OnAutoLoadListener() {
            @Override
            public void onLoading() {
                handleAutoLoadEvent();
            }
        });
        mAutoQuickRecyclerSingleAdapter.setOnLastItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAutoQuickRecyclerSingleAdapter.onAutoLoadStart();
            }
        });
    }

    private void handleAutoLoadEvent() {
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mAutoLoadCount == 0) {
                    mAutoLoadCount++;
                    mAutoQuickRecyclerSingleAdapter.onAutoLoadError();
                    return;
                }

                for (int i = 0; i < 20; i++) {
                    SampleModel sampleMode = new SampleModel();
                    sampleMode.setValues("RecyclerView add item " + (20 * mAutoLoadCount + i) + " by auto-load");
                    mDataList.add(sampleMode);
                }
                mAutoQuickRecyclerSingleAdapter.notifyDataSetChanged();

                if (mAutoLoadCount < 2) {
                    mAutoLoadCount++;
                    mAutoQuickRecyclerSingleAdapter.onAutoLoadFinished(true);
                } else {
                    mAutoQuickRecyclerSingleAdapter.onAutoLoadFinished(false);
                }
            }
        }, 1500);
    }

}
