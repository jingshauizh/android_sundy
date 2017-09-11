package com.jingshuai.android.fregmentapp.glidesample;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;
import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.appcommonlib.log.MLog;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GlideActivity extends AppCompatActivity implements GlideFragment.OnFragmentInteractionListener {

    @BindView(R.id.constraintLayout_id)
    android.support.constraint.ConstraintLayout mConstraintLayout;
    @BindView(R.id.bt_glide_sample_loadButton2)
    Button loadButton;
    @BindView(R.id.img_glide_image_view2)
    ImageView mImageView;

    private   GlideFragment mGlideFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_glide_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadClick();
                downloadImage(v);
            }
        });




//        mGlideFragment = GlideFragment.newInstance("","");
//
//
//
//
//
//
//        ConstraintSet constraintSet=new ConstraintSet();//新建一个ConstraintSet
//
//        LinearLayout mlinearLayout = new LinearLayout(this);
//        mlinearLayout.setLayoutParams(new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT
//        ));
//
//        mConstraintLayout.addView(mlinearLayout);
//
//        constraintSet.clone(mConstraintLayout);
//
//        constraintSet.constrainWidth(mlinearLayout.getId(), ConstraintLayout.LayoutParams.MATCH_PARENT);
//        constraintSet.constrainHeight(mlinearLayout.getId(),ConstraintLayout.LayoutParams.WRAP_CONTENT);
//        constraintSet.connect(mlinearLayout.getId(),ConstraintSet.END, ConstraintSet.PARENT_ID,ConstraintSet.END);
//        constraintSet.connect(mlinearLayout.getId(),ConstraintSet.START, ConstraintSet.PARENT_ID,ConstraintSet.START);
//        //这个按钮距离顶部的margin值为1000
//        constraintSet.connect(mlinearLayout.getId(),ConstraintSet.LEFT, ConstraintSet.PARENT_ID,ConstraintSet.LEFT);
//        constraintSet.connect(mlinearLayout.getId(),ConstraintSet.TOP, ConstraintSet.PARENT_ID,ConstraintSet.TOP);
//        constraintSet.applyTo(mConstraintLayout);
//        mlinearLayout.setId(R.id.etName_1);
//        getSupportFragmentManager().beginTransaction().add(mlinearLayout.getId(),mGlideFragment).commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void downloadImage(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://cn.bing.com/az/hprichbg/rb/TOAD_ZH-CN7336795473_1920x1080.jpg";
                    final Context context = getApplicationContext();
                    FutureTarget<File> target = Glide.with(context)
                            .load(url)
                            .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
                    final File imageFile = target.get();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, imageFile.getPath(), Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void loadClick(){
        String url = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=504673108,2123030926&fm=200&gp=0.jpg";
        MLog.i("loadClick");
        Glide.with(this)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(mImageView);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
