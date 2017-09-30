package com.ericsson.NewPlayer.CachePage;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.widget.TabHost;

import com.ericsson.NewPlayer.R;
import com.ericsson.NewPlayer.TabItem;
import com.ericsson.mvp.presenter.FragmentPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ehaoqii on 9/5/2017.
 */

public class CacheFragment extends FragmentPresenter<CacheView> {

    private CacheView cacheView;
    private CacheModel cacheModel;
    private List<TabItem> m_TableItemList;
    private FragmentTabHost fragmentTabHost;


    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        cacheView = this.view;
        cacheModel = new CacheModel(getContext());

        initTabData();
        initTabHost();
    }

    private void initTabHost() {
        //实例化FragmentTabHost对象
        fragmentTabHost = cacheView.findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(getContext(), getChildFragmentManager(), android.R.id.tabcontent);

        for (int i = 0; i < m_TableItemList.size(); i++) {
            TabItem tabItem = m_TableItemList.get(i);
            //实例化一个TabSpec,设置tab的名称和视图
            TabHost.TabSpec tabSpec = fragmentTabHost.newTabSpec(tabItem.getTitleString()).setIndicator(tabItem.getView2());
            fragmentTabHost.addTab(tabSpec, tabItem.getFragmentClass(), null);

            //给Tab按钮设置背景
            fragmentTabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(R.color.cachepage_tab_normal));

            //默认选中第一个tab
            if (i == 0) {
                fragmentTabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(R.color.cachepage_tab_select));
            }
        }

        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //重置Tab样式
                for (int i = 0; i < m_TableItemList.size(); i++) {
                    TabItem tabitem = m_TableItemList.get(i);
                    if (tabId.equals(tabitem.getTitleString())) {
                        fragmentTabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(R.color.cachepage_tab_select));
                    } else {
                        fragmentTabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(R.color.cachepage_tab_normal));
                    }
                }
            }
        });
    }

    private void initTabData() {
        m_TableItemList = new ArrayList<>();
        //添加tab
        m_TableItemList.add(new TabItem(getContext(), R.string.cachepage_menu_finnished, Fragment1.class));
        m_TableItemList.add(new TabItem(getContext(), R.string.cachepage_menu_downloading, Fragment2.class));

    }
}