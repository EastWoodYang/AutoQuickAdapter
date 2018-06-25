package com.eastwood.common.adapter.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.eastwood.common.adapter.ViewHelper;
import com.eastwood.common.adapter.auto.AutoQuickAdapter;
import com.eastwood.common.adapter.auto.OnAutoLoadListener;

import java.util.ArrayList;

public class SampleListViewAutoQuickAdapterCustomTheme extends AppCompatActivity {

    private ListView listView;

    private AutoQuickAdapter<SampleModel> mAutoQuickAdapter;
    private ArrayList<SampleModel> mDataList = new ArrayList<SampleModel>();
    private int mAutoLoadCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int i = 0; i < 20; i++) {
            SampleModel sampleModel = new SampleModel();
            sampleModel.setValues("ListView item " + i);
            mDataList.add(sampleModel);
        }
        mAutoQuickAdapter = new AutoQuickAdapter<SampleModel>(this, R.layout.list_item, mDataList) {

            @Override
            protected void convert(int position, ViewHelper helper, SampleModel item) {
                helper.setText(R.id.textView1, item.getValues());
            }
        };

        setContentView(R.layout.sample_list_view);
        listView = findViewById(R.id.refresh_layout);
        listView.setAdapter(mAutoQuickAdapter);
        mAutoQuickAdapter.setAutoLoadUsable(true);
        mAutoQuickAdapter.setManualLoad(true);
        mAutoQuickAdapter.setOnAutoLoadListener(new OnAutoLoadListener() {
            @Override
            public void onLoading() {
                handleAutoLoadEvent();
            }
        });
        mAutoQuickAdapter.setOnLastItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAutoQuickAdapter.onAutoLoadStart();
            }
        });
    }

    private void handleAutoLoadEvent() {
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mAutoLoadCount == 0) {
                    mAutoLoadCount++;
                    mAutoQuickAdapter.onAutoLoadError();
                    return;
                }

                for (int i = 0; i < 20; i++) {
                    SampleModel sampleMode = new SampleModel();
                    sampleMode.setValues("ListView add item " + (20 * mAutoLoadCount + i) + " by auto-load");
                    mDataList.add(sampleMode);
                }
                mAutoQuickAdapter.notifyDataSetChanged();

                if (mAutoLoadCount < 2) {
                    mAutoLoadCount++;
                    mAutoQuickAdapter.onAutoLoadFinished(true);
                } else {
                    mAutoQuickAdapter.onAutoLoadFinished(false);
                }
            }
        }, 1500);
    }

}
