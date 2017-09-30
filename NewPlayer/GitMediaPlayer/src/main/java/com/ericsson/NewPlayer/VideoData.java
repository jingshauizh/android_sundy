package com.ericsson.NewPlayer;

import com.ericsson.NewPlayer.HomePage.movie.MovieType;

import java.io.Serializable;
import java.util.List;

/**
 * Created by eyngzui on 8/18/2017.
 */

public class VideoData implements Serializable {
    private List<VideosBean> videos;

    public List<VideosBean> getVideos() {
        return videos;
    }

    public void setVideos(List<VideosBean> videos) {
        this.videos = videos;
    }

    public static class VideosBean implements Serializable {
        /**
         * id : 1
         * title : 足球
         * subtitle : 小朋友踢足球
         * videoImageUrl : http://img.taopic.com/uploads/allimg/140518/234753-14051R30K114.jpg
         * videoUrl : https://storage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%20Hangin'%20with%20the%20Google%20Search%20Bar.mp4
         * tags : ["足球","踢球","小朋友"]
         * like : false
         * comments : [{"id":1,"userName":"userName1","comment":"这些看起来不错哦"},{"id":2,"userName":"userName2","comment":"这些看起来不错哦"},{"id":3,"userName":"userName3","comment":"这些看起来不错哦"},{"id":4,"userName":"userName4","comment":"这些看起来不错哦"}]
         */

        private int id;
        private String title="";
        private String subtitle="";
        private String info="";
        private String videoImageUrl="";
        private String videoUrl="";
        private boolean like=false;
        private List<String> tags;
        private List<CommentsBean> comments;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }

        public String getVideoImageUrl() {
            return videoImageUrl;
        }

        public void setVideoImageUrl(String videoImageUrl) {
            this.videoImageUrl = videoImageUrl;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public boolean isLike() {
            return like;
        }

        public void setLike(boolean like) {
            this.like = like;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public static class CommentsBean implements Serializable {
            /**
             * id : 1
             * userName : userName1
             * comment : 这些看起来不错哦
             */

            private int id;
            private String userName;
            private String comment;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }
        }
    }
}
