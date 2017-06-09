package com.jingshuai.android.fregmentapp.test.commonadapter;

import android.content.Context;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.dummy.DummyContent;
import com.jingshuai.appcommonlib.adapter.CommonAdapter;
import com.jingshuai.appcommonlib.adapter.ViewHolder;

import java.util.List;

/**
 * Created by eqruvvz on 8/3/2016.
 */
public class ListAdapter extends CommonAdapter<DummyContent.DummyItem> {

    public ListAdapter(Context context, List<DummyContent.DummyItem> mDatas, int itemLayoutId){
        super(context,mDatas,itemLayoutId);
    }
    @Override
    public void convert(ViewHolder helper, DummyContent.DummyItem item) {
        helper.setText(R.id.content,item.content);
    }
}
