package com.example.souvik.projectivkitkatapi19.data.RebrandlyModel.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Souvik on 9-7-18.
 */

public class Creator
{
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("avatarUrl")
    @Expose
    private String avatarUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
