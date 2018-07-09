package com.example.souvik.projectivkitkatapi19.data.RebrandlyModel.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Souvik on 9-7-18.
 */

public class Body
{
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("domain")
    @Expose
    private Domain domain;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }
}
