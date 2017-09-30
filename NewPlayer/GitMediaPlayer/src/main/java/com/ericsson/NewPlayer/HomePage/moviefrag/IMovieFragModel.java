package com.ericsson.NewPlayer.HomePage.moviefrag;

import com.ericsson.NewPlayer.HomePage.movie.MovieType;
import com.ericsson.NewPlayer.VideoData;

import java.util.List;

/**
 * Created by eqruvvz on 8/22/2017.
 */

public interface IMovieFragModel  {

    interface OnPageRefreshListener {

        void onError();

        void onSuccess();
    }
    List<VideoData.VideosBean> getMovieList(MovieType mMovieType);
    void requestMovieList(MovieType mMovieType,OnPageRefreshListener lisenter);
}
