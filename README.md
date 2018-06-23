# AutoQuickAdapter

## Look Like

<img src='https://github.com/EastWoodYang/AutoQuickAdapter/blob/master/picture/1.gif'/><img src='https://github.com/EastWoodYang/AutoQuickAdapter/blob/master/picture/2.gif'/>

## Get it
AutoQuickAdapter is now available on JCentral.

    implementation 'com.eastwood.common:auto-quick-adapter:1.0.0'


## Usage

#### AutoQuickAdapter

    AutoQuickAdapter autoQuickAdapter = new AutoQuickAdapter<T>(this, R.layout.list_item, mDataList) {
     
        @Override
        protected void convert(int position, BaseAdapterHelper helper, SampleModel item) {
            ...
            helper.setText(R.id.textView1, item.getValues());
            ...
        }
        
    };
    
#### AutoQuickRecyclerAdapter
    
    QuickRecyclerAdapter quickRecyclerAdapter = new QuickRecyclerAdapter<SampleModel>(this, mDataList) {
        
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
        protected void convert(int position, RecyclerAdapterHelper helper, SampleModel item) {
            helper.setText(R.id.textView1, item.getValues());
        }
        
    };



# Thanks
[JoanZapata / base-adapter-helper](https://github.com/JoanZapata/base-adapter-helper)   
