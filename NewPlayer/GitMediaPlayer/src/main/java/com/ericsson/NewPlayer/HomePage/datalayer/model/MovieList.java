package com.ericsson.NewPlayer.HomePage.datalayer.model;


import com.ericsson.NewPlayer.HomePage.movie.MovieType;
import com.ericsson.NewPlayer.VideoData;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by eqruvvz on 9/5/2017.
 */

public class MovieList extends VideoData {

    public List<MovieModel> castResultToMovieModellist(){
        List<MovieModel> ovieModellist = new ArrayList<MovieModel>();
        List<CommentModel> commentModellist ;
        List<VideosBean> videos = this.getVideos();
        for(VideosBean video: videos){
            MovieModel mMovieModel = new MovieModel();
            mMovieModel.setId(video.getId());
            mMovieModel.setLike(false);
            mMovieModel.setSubtitle(video.getSubtitle());
            mMovieModel.setTitle(video.getTitle());
            mMovieModel.setVideoImageUrl(video.getVideoImageUrl());
            mMovieModel.setVideoUrl(video.getVideoUrl());

            //cast list to string save to db
            video.getTags().add(MovieType.ALL.name());
            mMovieModel.setTags(StringUtils.join(video.getTags().toArray(),","));
            commentModellist = new ArrayList<CommentModel>();

            for(VideosBean.CommentsBean comment:video.getComments()){
                CommentModel mCommentModel = new CommentModel();
                mCommentModel.setId(comment.getId());
                mCommentModel.setComment(comment.getComment());
                mCommentModel.setUserName(comment.getUserName());
                mCommentModel.setM_id(video.getId());
                mCommentModel.setKey(mCommentModel.getId()+"_"+mCommentModel.getM_id());
                commentModellist.add(mCommentModel);
            }
            mMovieModel.setComments(commentModellist);
            ovieModellist.add(mMovieModel);
        }
        return ovieModellist;
    }


    public static List<VideosBean> castResultToVideosBeanlist(List<MovieModel> movieModellist){
        List<VideosBean.CommentsBean> commentsBeanlist ;
        List<VideosBean> videos =  new ArrayList<VideosBean>();
        for(MovieModel movie: movieModellist){
            VideosBean mVideosBean = new VideosBean();
            mVideosBean.setId(movie.getId());
            mVideosBean.setLike(false);
            mVideosBean.setSubtitle(movie.getSubtitle());
            mVideosBean.setTitle(movie.getTitle());
            mVideosBean.setVideoImageUrl(movie.getVideoImageUrl());
            mVideosBean.setVideoUrl(movie.getVideoUrl());

            //cast string to list from db
            if(movie.getTags() != null){
                mVideosBean.setTags(Arrays.asList(movie.getTags().split(",")));
            }

            commentsBeanlist = new ArrayList<VideosBean.CommentsBean>();
            for(CommentModel comment:movie.getComments()){
                VideosBean.CommentsBean mCommentsBean = new VideosBean.CommentsBean();
                mCommentsBean.setId(comment.getId());
                mCommentsBean.setComment(comment.getComment());
                mCommentsBean.setUserName(comment.getUserName());
                commentsBeanlist.add(mCommentsBean);
            }
            mVideosBean.setComments(commentsBeanlist);
            videos.add(mVideosBean);
        }
        return videos;
    }



}
