package com.example.souvik.projectivkitkatapi19.data.RebrandlyModel.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Souvik on 9-7-18.
 */

public class Domain
{
    @SerializedName("id")
    @Expose
    private String id;

    public String getId() {
        return "8f104cc5b6ee4a4ba7897b06ac2ddcfb";      //for rebrand.ly/...
    }

    public void setId(String id) {
        this.id = id;
    }
}
