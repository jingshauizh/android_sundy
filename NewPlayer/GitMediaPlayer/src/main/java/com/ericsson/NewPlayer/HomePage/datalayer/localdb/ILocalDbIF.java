package com.ericsson.NewPlayer.HomePage.datalayer.localdb;

import com.ericsson.NewPlayer.HomePage.datalayer.IDataIf;
import com.ericsson.NewPlayer.VideoData;

import java.util.List;

/**
 * Created by eqruvvz on 9/4/2017.
 */

public interface ILocalDbIF extends IDataIf {
    Boolean saveMovieList(List<VideoData.VideosBean> movieList);
}
