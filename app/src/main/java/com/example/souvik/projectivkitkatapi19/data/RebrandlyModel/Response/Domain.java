package com.example.souvik.projectivkitkatapi19.data.RebrandlyModel.Response;

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
    @SerializedName("ref")
    @Expose
    private String ref;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("active")
    @Expose
    private Boolean active;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
