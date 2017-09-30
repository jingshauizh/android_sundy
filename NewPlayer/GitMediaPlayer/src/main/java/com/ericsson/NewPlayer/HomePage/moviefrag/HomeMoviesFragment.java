package com.ericsson.NewPlayer.HomePage.moviefrag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.os.Trace;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ericsson.NewPlayer.HomePage.RecyclerHomeAdapter;
import com.ericsson.NewPlayer.HomePage.movie.MovieType;
import com.ericsson.NewPlayer.Play.PlayerActivity;
import com.ericsson.NewPlayer.PlayerApp;
import com.ericsson.NewPlayer.R;
import com.ericsson.NewPlayer.VideoData;
import com.ericsson.mvp.presenter.IPresenter;
import com.lzy.widget.tab.CircleIndicator;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class HomeMoviesFragment extends AHomeUIFragmentPresenter<RecirclerFragView> implements IPresenter<RecirclerFragView> {
    private final String TAG = "HomeMoviesFragment";
    private List<VideoData.VideosBean> mDatas = null;
    private Activity activity;
    private MovieFragModel mMovieFragModel;
    private RecyclerHomeAdapter.OnItemClickListener mOnItemClickListener;
    private RecirclerFragView attachView;
    private IMovieFragModel.OnPageRefreshListener mOnPageRefreshListener;
    private String videoDataUrl = "http://150.236.223.172:8008/db";
    private ViewPager pagerHeader;
    private HeaderFragAdapter mHeaderFragAdapter;
    private TextView homepage_recommendview_title;
    private CircleIndicator ci;
    private MovieType mMovieType = MovieType.ALL;
    private Boolean pageInit_flag = false;


    public static HomeMoviesFragment newInstance() {
        return new HomeMoviesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void created(Bundle savedInstance) {
        // TODO Auto-generated method stub
        //Debug.startMethodTracing("HomeMoviesFragment___");
        //Debug.startMethodTracing("/data/data/com.ericsson.NewPlayer/HomeMoviesFragment___.trace");
        activity = getActivity();
        //final File methodTracingFile = new File(activity.getExternalFilesDir("Caches"), "AppStart.trace");
        //Log.d(TAG, "methodTracingPath=" + methodTracingFile.getPath());
        //Debug.startMethodTracing(methodTracingFile.getPath());
        PlayerApp.getRefWatcher(activity).watch(this);
        attachView = this.view;

        mMovieFragModel = new MovieFragModel();
        initListener();
        pagerHeader = (ViewPager) getActivity().findViewById(R.id.frag_pagerHeader);

        homepage_recommendview_title = (TextView) getActivity().findViewById(R.id.homepage_recommendview_title);
        ci = (CircleIndicator) getActivity().findViewById(R.id.frag_ci);
        initMovieView();



    }

    private void initListener() {
        mOnItemClickListener = new RecyclerHomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                openPlayPage(mDatas.get(position));
            }

            @Override
            public void onItemLongClick(View v, int position) {
                Toast.makeText(getActivity(), "第" + position + "项被long点击了", Toast.LENGTH_SHORT).show();
            }
        };


        mOnPageRefreshListener = new IMovieFragModel.OnPageRefreshListener() {
            @Override
            public void onError() {
                Log.i(TAG, "mOnGetMovieListFinishedListener on error");
            }

            @Override
            public void onSuccess() {
                refreshMovieView();
                //Debug.stopMethodTracing();
            }
        };
    }

    private void initMovieView(){
        mMovieFragModel.requestMovieList(mMovieType,mOnPageRefreshListener);
        refreshMovieView();
    }

    private void refreshMovieView(){
        mDatas = mMovieFragModel.getMovieList(mMovieType);
        if(mDatas != null && mDatas.size() >0){
            if(!pageInit_flag){
                mHeaderFragAdapter = new HeaderFragAdapter(mDatas);
                pagerHeader.setAdapter(mHeaderFragAdapter);
                ci.setViewPager(pagerHeader);
                attachView.initViewData(mDatas);
                attachView.setOnItemClickLisener(mOnItemClickListener);
                pageInit_flag = true;
            }
            else{
                attachView.refreshView(mDatas);
            }

        }
        else{
            if(pageInit_flag){
                attachView.refreshView(mDatas);
            }
            //do not need change
            //mHeaderFragAdapter.setMovieList(mDatas);

        }
    }

    @Override
    public View getScrollableView() {
        return this.view.getScrollableView();
    }



    private void openPlayPage(VideoData.VideosBean pVideosBean){
        Intent playerIntent = new Intent(activity.getApplicationContext(), PlayerActivity.class);
        playerIntent.putExtra("videoBean", pVideosBean);
        playerIntent.putExtra("listMovies", (Serializable) mDatas);
        activity.startActivity(playerIntent);
    }

    /**
     * 头布局的适配器
     */
    private class HeaderFragAdapter extends PagerAdapter {

        public List<VideoData.VideosBean> getMovieList() {
            return movieList;
        }

        public void setMovieList(List<VideoData.VideosBean> movieList) {
            this.movieList = movieList;
            this.notifyDataSetChanged();
        }

        private List<VideoData.VideosBean> movieList;

        public HeaderFragAdapter(final List<VideoData.VideosBean> pmovieList) {
            movieList = pmovieList;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView imageView = new ImageView(getActivity().getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setImageResource(images[position]);
            Glide.with(activity)
                   // .load(movieList.get(position).getVideoImageUrl())
                    .load(R.drawable.video_image)
                    .centerCrop()    //先填充image 再剪切多余的
                    .into(imageView);
            homepage_recommendview_title.setText(movieList.get(position).getTitle());
            container.addView(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openPlayPage(movieList.get(position));
                }
            });
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            if(movieList == null){
                return 0;
            }
            if (movieList.size() > 5) {
                return 5;
            } else {
                return movieList.size();
            }
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }



    public MovieType getmMovieType() {
        return mMovieType;
    }

    public void setmMovieType(MovieType mMovieType) {
        this.mMovieType = mMovieType;
        refreshMovieView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mOnPageRefreshListener = null;
    }
}
