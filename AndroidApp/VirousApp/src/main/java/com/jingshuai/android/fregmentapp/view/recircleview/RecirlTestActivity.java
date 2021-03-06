package com.jingshuai.android.fregmentapp.view.recircleview;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.jingshuai.android.fregmentapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecirlTestActivity extends AppCompatActivity {


    private List<String> mDatas = new ArrayList<>();
    private Activity activity;
    @BindView( R.id.recircle_test_view )
    RecyclerView mRecyclerView;

    private RecyclerTestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recirl_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        activity = this;
        ButterKnife.bind(this);
        initDatas();
        adapter = new RecyclerTestAdapter(this,mDatas);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        adapter.setListener(new RecyclerTestAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(activity,"第"+position+"项被点击了",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View v,int position) {
                Toast.makeText(activity,"第"+position+"项被long点击了",Toast.LENGTH_SHORT).show();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    private void initDatas(){
        for (int i = 0;i<50;i++){
            mDatas.add(String.valueOf(i+1));
        }
    }

}
