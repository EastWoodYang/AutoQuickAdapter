package com.eastwood.common.adapter.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.eastwood.common.adapter.QuickAdapter;
import com.eastwood.common.adapter.ViewHelper;

import java.util.ArrayList;

public class SampleListViewQuickAdapter extends AppCompatActivity {

    private ListView listView;

    private QuickAdapter<SampleModel> mQuickAdapter;
    private ArrayList<SampleModel> mDataList = new ArrayList<SampleModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int i = 0; i < 20; i++) {
            SampleModel sampleModel = new SampleModel();
            sampleModel.setValues("ListView item " + i);
            mDataList.add(sampleModel);
        }
        mQuickAdapter = new QuickAdapter<SampleModel>(this, R.layout.list_item, mDataList) {

            @Override
            protected void convert(int position, ViewHelper helper, SampleModel item) {
                helper.setText(R.id.textView1, item.getValues());
            }
        };

        setContentView(R.layout.sample_list_view);
        listView = findViewById(R.id.refresh_layout);
        listView.setAdapter(mQuickAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("onItemClick", "position: " + position);
            }
        });
    }
}
