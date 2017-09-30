package com.ericsson.NewPlayer.HomePage.datalayer.localdb;

import android.content.Context;

import com.ericsson.NewPlayer.HomePage.datalayer.IDataIf;
import com.ericsson.NewPlayer.HomePage.datalayer.model.MovieList;
import com.ericsson.NewPlayer.HomePage.datalayer.model.MovieModel;
import com.ericsson.NewPlayer.HomePage.movie.MovieType;
import com.ericsson.NewPlayer.VideoData;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.List;

/**
 * Created by eqruvvz on 9/5/2017.
 */

public class DBManager implements IDataIf {
    private MovieIdal mMovieIdal;
    private Context mContext;

    public DBManager(Context pContext){
        mMovieIdal = new MovieIdal();
        mContext=pContext;
        FlowManager.init(pContext);
    }

    @Override
    public List<VideoData.VideosBean> getMoviesList(MovieType mMovieType) {
        List<MovieModel> mMovieModellist = mMovieIdal.loadAllByType(mMovieType);

        if(mMovieModellist != null && mMovieModellist.size()>0){
            List<VideoData.VideosBean> movieList = MovieList.castResultToVideosBeanlist(mMovieModellist);
            return movieList;
        }
        return null;
    }
}
