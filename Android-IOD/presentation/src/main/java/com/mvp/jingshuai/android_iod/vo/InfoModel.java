package com.mvp.jingshuai.android_iod.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by eqruvvz on 1/16/2017.
 */
public class InfoModel extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = false)
    @JsonProperty("id")
    String infoId;

    @Column
    @JsonProperty("name")
    String infoName;

    @Column
    @JsonProperty("attribute")
    String attribute;

    @Column
    @JsonProperty("description")
    String description;

    @Column
    @JsonProperty("category")
    String category;

    @Column
    @JsonProperty("imageUrl")
    String imageUrl;

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
