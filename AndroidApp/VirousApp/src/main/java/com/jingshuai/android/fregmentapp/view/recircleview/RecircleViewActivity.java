package com.jingshuai.android.fregmentapp.view.recircleview;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jingshuai.android.fregmentapp.R;

import java.util.ArrayList;
import java.util.List;

public class RecircleViewActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recircle_view);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//    }


    private Activity activity;
    private Button btnAdd,btnRemove;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<String> mDatas = new ArrayList<>();

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recircle_view);
        activity = this;
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnRemove = (Button) findViewById(R.id.btn_remove);

        initDatas();
        adapter = new RecyclerAdapter(activity,mDatas);

        // 设置RecyclerView布局方式为纵向布局
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        // 设置RecyclerView布局方式为横向布局
//        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(layoutManager);

        // 设置纵向的4列的表格布局
//        GridLayoutManager layoutManager = new GridLayoutManager(this,4,GridLayoutManager.VERTICAL,false);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.addItemDecoration(new DividerGridItemDecoration(activity));

        // 设置横向的3行的表格布局
//        GridLayoutManager layoutManager = new GridLayoutManager(this,3,GridLayoutManager.HORIZONTAL,false);
//        recyclerView.setLayoutManager(layoutManager);

        // 给纵向显示RecyclerView设置分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(activity,DividerItemDecoration.VERTICAL));
        // 给横向显示RecyclerView设置分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(activity,DividerItemDecoration.HORIZONTAL));

//        // 设置纵向的瀑布流布局
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);

        // 给RecyclerView设置默认动画效果
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter.setLitener(new RecyclerAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(activity,"第"+position+"项被点击了",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(activity,"第"+position+"项被长按了",Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addData(1);
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.removeData(2);
            }
        });
    }

    private void initDatas(){
        for (int i = 0;i<50;i++){
            mDatas.add(String.valueOf(i+1));
        }
    }

}
