package com.ericsson.NewPlayer.HomePage.moviefrag;

import android.view.View;

import com.ericsson.NewPlayer.HomePage.RecyclerHomeAdapter;
import com.ericsson.NewPlayer.VideoData;

import java.util.List;

/**
 * Created by eqruvvz on 8/22/2017.
 */

public interface IRecirclerFragView  {
    void refreshView();
    void refreshView(List<VideoData.VideosBean> mDatas);
    void initViewData(List<VideoData.VideosBean> mDatas);
    View getScrollableView();
    void setOnItemClickLisener(RecyclerHomeAdapter.OnItemClickListener pListener);
}
