# AutoQuickAdapter
Make load-more easy for ListView and RecycleView.

# Features
* Supports ListView, RecycleView.
* Easy to custom your load-more style.
* Combine with QuickAdapter, the best convenient way to write adapter.
* Support wrap the other adapter.

## Look Like
#### Default style
<img src='https://github.com/EastWoodYang/AutoQuickAdapter/blob/master/picture/1.png'/>

<img src='https://github.com/EastWoodYang/AutoQuickAdapter/blob/master/picture/1.gif'/>

#### You can custom style
<img src='https://github.com/EastWoodYang/AutoQuickAdapter/blob/master/picture/2.png'/>

<img src='https://github.com/EastWoodYang/AutoQuickAdapter/blob/master/picture/2.gif'/>

## Get it
AutoQuickAdapter is now available on JCentral.

    implementation 'com.eastwood.common:auto-quick-adapter:1.0.1'


## Usage

### Simple Demo for AutoQuickAdapter

    autoQuickAdapter = new AutoQuickAdapter<SampleModel>(this, R.layout.list_item, dataList) {

        @Override
        protected void convert(int position, ViewHelper helper, SampleModel item) {
            ...
            helper.setText(R.id.textView1, item.getValues());
            ...
        }
    };
    autoQuickAdapter.setAutoLoadUsable(true);
    autoQuickAdapter.setOnAutoLoadListener(new OnAutoLoadListener() {
        @Override
        public void onLoading() {
            // to get more data and add to dataList
            
            // after auto-load done, you need call
            // autoQuickAdapter.onAutoLoadComplete(boolean autoLoadUsable);
        }
    });

#### AutoQuickAdapter

    AutoQuickAdapter<T>(Context context, int layoutResId)
    AutoQuickAdapter<T>(Context context, int layoutResId, List<T> data)
    
    
### Simple Demo for AutoQuickRecyclerAdapter

    quickRecyclerAdapter = new AutoQuickRecyclerAdapter<SampleModel>(this, dataList) {
        
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
            if (type == 1) {
                return R.layout.list_item;
            } else {
                return R.layout.list_item_2;
            }
        }
     
        @Override
        protected void convert(int position, ViewHelper helper, SampleModel item) {
            ...
            helper.setText(R.id.textView1, item.getValues());
            ...
        }
        
    };
    ...

#### AutoQuickRecyclerAdapter
    
    AutoQuickRecyclerAdapter<T>(Context context, int layoutResId)
    AutoQuickRecyclerAdapter<T>(Context context, int layoutResId, List<T> data)


### Simple Demo for AutoWrapAdapter and AutoWrapRecyclerAdapter
compatible with the old adapter.

    oldAdapter = new SampleAdapter(this, dataList);
    autoWrapAdapter = new AutoWrapAdapter<SampleAdapter>(oldAdapter);
    listView.setAdapter(autoWrapAdapter);

###  Common methods of Auto***Adapter

* ```onAutoLoadComplete(boolean autoLoadUsable)```
* ```onAutoLoadStart()```
* ```onAutoLoadError()```
* ```setAutoLoadUsable(boolean usable)```
* ```isAutoLoadUsable()```
* ```setOnAutoLoadListener(OnAutoLoadListener listener)```
* ```setOnLastItemClickListener(View.OnClickListener listener)```
* ```setManualLayoutResId(boolean resId)```
* ```getManualLayoutResId()```
* ```setLoadingLayoutResId(boolean resId)```
* ```getLoadingLayoutResId()```
* ```setLoadEndLayoutResId(boolean resId)```
* ```getLoadEndLayoutResId()```
* ```setLoadErrorLayoutResId(boolean resId)```
* ```getLoadErrorLayoutResId()```
* ```setManualLoad(boolean manualLoad)```
* ```setLoadError()```
* ```setManualLoad(boolean loadError)```
* ```isLoadError()```
* ```setLoadEnd(boolean loadEnd)```
* ```isLoadEnd()```
* ```setLoading(boolean loading)```
* ```isLoading()```
* ```setManualLoad(boolean manualLoad)```
* ```isManualLoad()```

### ViewHelper
* ```setText()``` Calls ```setText(int viewId, String value)``` on any TextView.
* ```setTextColor()``` Calls ```setTextColor(int textColor)``` on any TextView.
* ```setImageResource()``` Calls ```setImageResource(int viewId, int imageResId)``` on any ImageView.
* ```setImageDrawable()``` Calls ```setImageDrawable(int viewId, Drawable drawable)``` on any ImageView.
* ```setImageBitmap()``` Calls ```setImageBitmap(int viewId, Bitmap bitmap)``` on any ImageView.
* ```setBackgroundColor()``` Calls ```setBackgroundColor(int viewId, int color)``` on any View.
* ```setAlpha()``` Calls ```setAlpha(int viewId, float value)``` on any View.
* ```setVisible()``` Calls ```setVisibility(int viewId, int visibility)``` on any View.
* ```linkify()``` Calls ```Linkify.addLinks(int viewId)``` on any TextView.
* ```setTypeface()``` Calls ```setTypeface(int viewId, Typeface typeface)``` on any TextView.
* ```setProgress()``` Calls ```setProgress(int viewId, int progress)``` on any ProgressBar.
* ```setMax()``` Calls ```setMax(int viewId, int max)``` on any ProgressBar.
* ```setRating()``` Calls ```setRating(int viewId, float rating)``` on any RatingBar.
* ```setOnClickListener()```
* ```setOnTouchListener()```
* ```setOnLongClickListener()```
* ```setTag()```
* ```setChecked()```
* ```setAdapter()```
* ...
* If you need something else, please [report an issue](https://github.com/EastWoodYang/AutoQuickAdapter/issues). Any contribution is welcome! In the meanwhile, you can still use ```getView(id)```on the adapter to do custom operations.

### Apply global Style

    <style name="CustomTheme" parent="AppTheme">
        <item name="auto_adapter_loading_layout">@layout/custom_loading_layout</item>
        <item name="auto_adapter_manual_layout">@layout/custom_manual_layout</item>
        <item name="auto_adapter_end_layout">@layout/custom_end_layout</item>
        <item name="auto_adapter_error_layout">@layout/custom_error_layout</item>
    </style>


# Thanks
[JoanZapata / base-adapter-helper](https://github.com/JoanZapata/base-adapter-helper)   


## License
```
   Copyright 2018 EastWood Yang

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
