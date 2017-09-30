package com.ericsson.NewPlayer.HomePage.datalayer.model;

import com.ericsson.NewPlayer.HomePage.datalayer.localdb.NewPlayerDB;
import com.ericsson.NewPlayer.HomePage.util.Validation;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by eqruvvz on 9/5/2017.
 */
@Table(database = NewPlayerDB.class)
public class MovieModel extends BaseModel implements Validation, Serializable {


    @Column
    @PrimaryKey(autoincrement = false)
    Integer id;

    @Column
    String title = "";

    @Column
    String subtitle = "";


    @Column
    String videoImageUrl = "";

    @Column
    String videoUrl = "";


    @Column
    String tags = "";

    @Column
    boolean like = false;

    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }

    List<CommentModel> comments;

    @OneToMany(methods = OneToMany.Method.ALL, variableName = "comments")
    public List<CommentModel> dbFlowOneTwoManyUtilMethod() {
        if (comments == null) {
            comments = new Select()
                    .from(CommentModel.class)
                    .where(CommentModel_Table.m_id.eq(id))
                    .queryList();
        }
        return comments;
    }


    @Override
    public void validate() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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


    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

}
