package com.ericsson.NewPlayer.HomePage.moviefrag;

import com.ericsson.NewPlayer.HomePage.datamanagement.MovieManager;
import com.ericsson.NewPlayer.HomePage.movie.MovieType;
import com.ericsson.NewPlayer.VideoData;
import com.ericsson.mvp.model.Imodel;

import java.util.List;


/**
 * Created by eqruvvz on 8/22/2017.
 *
 *
 *
 */

public class MovieFragModel implements Imodel, IMovieFragModel {

    private final String TAG = "MovieFragModel";
    private MovieManager mMovieManager;
    public MovieFragModel() {
        mMovieManager = new MovieManager();
    }

    @Override
    public List<VideoData.VideosBean> getMovieList(MovieType mMovieType) {

        return mMovieManager.getMoviesList(mMovieType);
    }

    @Override
    public void requestMovieList(MovieType mMovieType,OnPageRefreshListener lisenter) {

        mMovieManager.requestMovieList(mMovieType,lisenter);
    }


}
