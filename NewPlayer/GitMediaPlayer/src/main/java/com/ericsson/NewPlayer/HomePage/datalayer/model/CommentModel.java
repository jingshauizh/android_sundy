package com.ericsson.NewPlayer.HomePage.datalayer.model;

import com.ericsson.NewPlayer.HomePage.datalayer.localdb.NewPlayerDB;
import com.ericsson.NewPlayer.HomePage.util.Validation;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

/**
 * Created by eqruvvz on 9/6/2017.
 */

@Table(database = NewPlayerDB.class)
public class CommentModel extends BaseModel implements Validation, Serializable {


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Column
    @PrimaryKey(autoincrement = false)
    String key;

    @Column
    Integer id;


    @Column
    Integer m_id;

    @Column
    String userName;

    @Column
    String comment;

    @Override
    public void validate() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getM_id() {
        return m_id;
    }

    public void setM_id(Integer m_id) {
        this.m_id = m_id;
    }
}
