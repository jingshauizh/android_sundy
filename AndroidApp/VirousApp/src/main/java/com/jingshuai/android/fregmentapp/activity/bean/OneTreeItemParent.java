package com.jingshuai.android.fregmentapp.activity.bean;


import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.view.TreeItem;
import com.baozi.treerecyclerview.view.TreeItemGroup;
import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.activity.bean.bean.CityBean;

import java.util.List;

/**
 * Created by baozi on 2016/12/8.
 */
public class OneTreeItemParent extends TreeItemGroup<CityBean> {
    @Override
    public List<TreeItem> initChildsList(CityBean data) {
        return ItemHelperFactory.createTreeItemList(data.getCitys(), TwoTreeItemParent.class, this);
    }

    @Override
    public int initLayoutId() {
        return R.layout.itme_one;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getProvinceName());
    }

}
