package com.eastwood.common.adapter.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eastwood.common.adapter.auto.AutoWrapRecyclerAdapter;
import com.eastwood.common.adapter.auto.OnAutoLoadListener;

import java.util.ArrayList;
import java.util.List;

public class SampleRecyclerViewWithAutoWrapRecyclerAdapter extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SampleRecyclerAdapter mSimpleAdapter;
    private AutoWrapRecyclerAdapter<SampleRecyclerAdapter> mAutoWrapRecyclerAdapter;
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

        mSimpleAdapter = new SampleRecyclerAdapter(this, mDataList);
        mAutoWrapRecyclerAdapter = new AutoWrapRecyclerAdapter<>(mSimpleAdapter);

        setContentView(R.layout.sample_recycler_view);
        mRecyclerView = findViewById(R.id.refresh_layout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAutoWrapRecyclerAdapter);

        mAutoWrapRecyclerAdapter.setAutoLoadUsable(true);
        mAutoWrapRecyclerAdapter.setOnAutoLoadListener(new OnAutoLoadListener() {
            @Override
            public void onLoading() {
                handleAutoLoadEvent();
            }
        });
        mAutoWrapRecyclerAdapter.setOnLastItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAutoWrapRecyclerAdapter.onAutoLoadStart();
            }
        });
    }

    private void handleAutoLoadEvent() {
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mAutoLoadCount == 0) {
                    mAutoLoadCount++;
                    mAutoWrapRecyclerAdapter.onAutoLoadError();
                    return;
                }

                for (int i = 0; i < 20; i++) {
                    SampleModel sampleMode = new SampleModel();
                    sampleMode.setValues("RecyclerView add item " + (20 * mAutoLoadCount + i) + " by auto-load");
                    mDataList.add(sampleMode);
                }
                mAutoWrapRecyclerAdapter.notifyDataSetChanged();

                if (mAutoLoadCount < 2) {
                    mAutoLoadCount++;
                    mAutoWrapRecyclerAdapter.onAutoLoadFinished(true);
                } else {
                    mAutoWrapRecyclerAdapter.onAutoLoadFinished(false);
                }
            }
        }, 1500);
    }

}
