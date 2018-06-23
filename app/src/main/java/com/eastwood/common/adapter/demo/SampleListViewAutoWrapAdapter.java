package com.eastwood.common.adapter.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.eastwood.common.adapter.auto.AutoWrapAdapter;
import com.eastwood.common.adapter.auto.OnAutoLoadListener;

import java.util.ArrayList;

public class SampleListViewAutoWrapAdapter extends AppCompatActivity {

    private ListView listView;

    private SampleAdapter mSampleAdapter;
    private AutoWrapAdapter<SampleAdapter> mAutoWrapAdapter;
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

        mSampleAdapter = new SampleAdapter(this, mDataList);
        mAutoWrapAdapter = new AutoWrapAdapter<SampleAdapter>(mSampleAdapter);

        setContentView(R.layout.sample_list_view);
        listView = findViewById(R.id.refresh_layout);
        listView.setAdapter(mAutoWrapAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("onItemClick", "position: " + position);
            }
        });
        mAutoWrapAdapter.setAutoLoadUsable(true);
        mAutoWrapAdapter.setOnAutoLoadListener(new OnAutoLoadListener() {
            @Override
            public void onLoading() {
                handleAutoLoadEvent();
            }
        });
        mAutoWrapAdapter.setOnLastItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAutoWrapAdapter.onAutoLoadStart();
            }
        });
    }

    private void handleAutoLoadEvent() {
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mAutoLoadCount == 0) {
                    mAutoLoadCount++;
                    mAutoWrapAdapter.onAutoLoadError();
                    return;
                }

                for (int i = 0; i < 20; i++) {
                    SampleModel sampleMode = new SampleModel();
                    sampleMode.setValues("ListView add item " + (20 * mAutoLoadCount + i) + " by auto-load");
                    mDataList.add(sampleMode);
                }
                mAutoWrapAdapter.notifyDataSetChanged();

                if (mAutoLoadCount < 2) {
                    mAutoLoadCount++;
                    mAutoWrapAdapter.onAutoLoadComplete(true);
                } else {
                    mAutoWrapAdapter.onAutoLoadComplete(false);
                }
            }
        }, 1500);
    }

}
