package com.jingshuai.android.fregmentapp.activity.bean;


import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;

import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.view.TreeItem;
import com.jingshuai.android.fregmentapp.MyApplication;
import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.activity.CityListActivity;
import com.jingshuai.android.fregmentapp.activity.bean.bean.CityBean;
import com.jingshuai.android.fregmentapp.eventbus.MainEventActivity;
import com.jingshuai.appcommonlib.log.MLog;

/**
 */
public class ThreeItem extends TreeItem<CityBean.CitysBean.AreasBean> {


    @Override
    public int initLayoutId() {
        return R.layout.item_three;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getAreaName());
    }

    @Override
    public void onClick() {
        MLog.i("Click "+data.getAreaClass());
        Intent mIntent = new Intent( MyApplication.getAppContext(), MainEventActivity.class);
//        mIntent.setAction(Intent.ACTION_VIEW);
        //ComponentName  do not work
//        ComponentName com = new ComponentName(
//                data.getAreaPackage(),
//                data.getAreaClass());
//        mIntent.setComponent(com);
        mIntent.setClassName(MyApplication.getAppContext(),data.getAreaClass());
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getAppContext().startActivity(mIntent);

    }
}
