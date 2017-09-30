package com.ericsson.NewPlayer.HomePage.datalayer;

import com.ericsson.NewPlayer.HomePage.movie.MovieType;
import com.ericsson.NewPlayer.VideoData;

import java.util.List;

/**
 * Created by eqruvvz on 9/4/2017.
 */

public interface IDataIf {
    List<VideoData.VideosBean> getMoviesList(MovieType pMovieType);
}
