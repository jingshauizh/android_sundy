package com.ericsson.NewPlayer.HomePage.datamanagement;

import com.ericsson.NewPlayer.HomePage.movie.MovieType;
import com.ericsson.NewPlayer.HomePage.moviefrag.IMovieFragModel;
import com.ericsson.NewPlayer.VideoData;

import java.util.List;

/**
 * Created by eqruvvz on 9/4/2017.
 */

public interface IMovieDalIF {
    List<VideoData.VideosBean> getMoviesList(MovieType movieType);
    void requestMovieList(MovieType movieType,IMovieFragModel.OnPageRefreshListener lisenter);
    List<VideoData.VideosBean> getHomeUIRecommendMovieList(MovieType movieType,IMovieFragModel.OnPageRefreshListener lisenter);
}
