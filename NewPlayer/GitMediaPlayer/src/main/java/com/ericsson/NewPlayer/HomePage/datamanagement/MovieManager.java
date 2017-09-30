package com.ericsson.NewPlayer.HomePage.datamanagement;


import com.ericsson.NewPlayer.HomePage.datalayer.localdb.DBManager;
import com.ericsson.NewPlayer.HomePage.datalayer.net.NetDataManager;
import com.ericsson.NewPlayer.HomePage.movie.MovieType;
import com.ericsson.NewPlayer.HomePage.moviefrag.IMovieFragModel;
import com.ericsson.NewPlayer.PlayerApp;
import com.ericsson.NewPlayer.VideoData;

import java.util.List;

/**
 * Created by eqruvvz on 9/4/2017.
 */

public class MovieManager implements IMovieDalIF {
    private final String TAG = "MovieManager";

    private DBManager mDBManager;
    private NetDataManager mNetDataManager;
    private IMovieFragModel.OnPageRefreshListener lisenter;

    public MovieManager(){
        mDBManager = PlayerApp.getDbManager();
        mNetDataManager = new NetDataManager();
    }


    @Override
    public List<VideoData.VideosBean> getMoviesList(MovieType movieType) {

        return mDBManager.getMoviesList(movieType);
    }

    @Override
    public void requestMovieList(MovieType movieType,IMovieFragModel.OnPageRefreshListener plisenter) {
        lisenter = plisenter;
        mNetDataManager.retuestMoviesList(movieType,lisenter);
    }

    @Override
    public List<VideoData.VideosBean> getHomeUIRecommendMovieList(MovieType movieType, IMovieFragModel.OnPageRefreshListener lisenter) {
        return null;
    }
}
