package com.ericsson.NewPlayer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TabHost;
import android.widget.Toast;

import com.ericsson.NewPlayer.CachePage.CacheFragment;
import com.ericsson.NewPlayer.HomePage.HomeUIFragment;
import com.ericsson.NewPlayer.RecommendPage.RecommendFragment;
import com.ericsson.NewPlayer.SearchPage.SearchFragment;
import com.ericsson.NewPlayer.UserPage.UserpageFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivityNP extends AppCompatActivity {

    private List<TabItem> m_TableItemList;
    private final String TAG = "MainActivityNP";
    private static final int MY_PERMISSIONS_REQUEST_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        initTabData();
        initTabHost();
        PlayerApp.getRefWatcher(this).watch(this);
//        if(android.os.Build.VERSION.SDK_INT >=19){
//            try {
//                reportFullyDrawn();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        //checkPermission();

    }

    private void checkPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(this,
                    new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_STORAGE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if (requestCode == MY_PERMISSIONS_REQUEST_STORAGE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(MainActivityNP.this, "Permission getted", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "MainActivityNP start onStart");
        super.onStart();
        Log.i(TAG, "MainActivityNP start onStart finished");
    }

    private void initTabHost() {
        //实例化FragmentTabHost对象
        FragmentTabHost fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        //去掉分割线
        fragmentTabHost.getTabWidget().setDividerDrawable(null);

        for (int i = 0; i < m_TableItemList.size(); i++) {
            TabItem tabItem = m_TableItemList.get(i);
            //实例化一个TabSpec,设置tab的名称和视图
            TabHost.TabSpec tabSpec = fragmentTabHost.newTabSpec(tabItem.getTitleString()).setIndicator(tabItem.getView());
            fragmentTabHost.addTab(tabSpec, tabItem.getFragmentClass(), null);

            //给Tab按钮设置背景
            fragmentTabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(R.color.main_bottom_bg));

            //默认选中第一个tab
            if (i == 0) {
                tabItem.setChecked(true);
            }
        }

        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //重置Tab样式
                for (int i = 0; i < m_TableItemList.size(); i++) {
                    TabItem tabitem = m_TableItemList.get(i);
                    if (tabId.equals(tabitem.getTitleString())) {
                        tabitem.setChecked(true);
                    } else {
                        tabitem.setChecked(false);
                    }
                }
            }
        });
    }

    //初始化Tab数据
    private void initTabData() {
        m_TableItemList = new ArrayList<>();
        //添加tab
        m_TableItemList.add(new TabItem(this, R.drawable.main_bottom_homepage_normal, R.drawable.main_bottom_homepage_press, R.string.main_homepage_text, HomeUIFragment.class));
        m_TableItemList.add(new TabItem(this, R.drawable.main_bottom_search_normal, R.drawable.main_bottom_search_press, R.string.main_search_text, SearchFragment.class));
        m_TableItemList.add(new TabItem(this, R.drawable.main_bottom_cache_normal, R.drawable.main_bottom_cache_press, R.string.main_cache_text, CacheFragment.class));
        m_TableItemList.add(new TabItem(this, R.drawable.main_bottom_recommend_normal, R.drawable.main_bottom_recommend_press, R.string.main_recommend_text, RecommendFragment.class));
        m_TableItemList.add(new TabItem(this, R.drawable.main_bottom_userpage_normal, R.drawable.main_bottom_userpage_press, R.string.main_userpage_text, UserpageFragment.class));

    }
}

