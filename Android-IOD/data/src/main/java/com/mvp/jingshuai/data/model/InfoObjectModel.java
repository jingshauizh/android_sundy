package com.mvp.jingshuai.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mvp.jingshuai.data.storage.IODDatabase;
import com.mvp.jingshuai.data.util.Validation;
import com.mvp.jingshuai.data.util.ValidationFailedException;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by eqruvvz on 12/28/2016.
 */

@Table(databaseName = IODDatabase.NAME)
public class InfoObjectModel extends BaseModel implements Validation {

    @Column
    @PrimaryKey(autoincrement = false)
    @JsonProperty("id")
    String infoId;

    @Column
    @JsonProperty("name")
    String infoName;

    @Column
    @JsonProperty("priority")
    int priority;

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

    @Column
    @JsonProperty("viewCount")
    int viewCount;


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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    @Override
    public void validate() {
        if (StringUtils.isEmpty(infoId)) {
            throw new ValidationFailedException("invalid infoId");
        }
        if (StringUtils.isEmpty(infoName)) {
            throw new ValidationFailedException("invalid infoName");
        }
        if (StringUtils.isEmpty(description)) {
            throw new ValidationFailedException("invalid description");
        }
        if (StringUtils.isEmpty(category)) {
            throw new ValidationFailedException("invalid category");
        }
        if (StringUtils.isEmpty(imageUrl)) {
            throw new ValidationFailedException("invalid imageUrl");
        }
    }
}
