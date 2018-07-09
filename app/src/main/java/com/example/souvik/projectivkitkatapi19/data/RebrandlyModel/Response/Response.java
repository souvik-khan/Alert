package com.example.souvik.projectivkitkatapi19.data.RebrandlyModel.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Souvik on 9-7-18.
 */

public class Response
{
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("linkId")
    @Expose
    private Integer linkId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("slashtag")
    @Expose
    private String slashtag;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("tags")
    @Expose
    private List<Object> tags = null;
    @SerializedName("clicks")
    @Expose
    private Integer clicks;
    @SerializedName("isPublic")
    @Expose
    private Boolean isPublic;
    @SerializedName("shortUrl")
    @Expose
    private String shortUrl;
    @SerializedName("domainId")
    @Expose
    private String domainId;
    @SerializedName("domainName")
    @Expose
    private String domainName;
    @SerializedName("domain")
    @Expose
    private Domain domain;
    @SerializedName("https")
    @Expose
    private Boolean https;
    @SerializedName("favourite")
    @Expose
    private Boolean favourite;
    @SerializedName("creator")
    @Expose
    private Creator creator;
    @SerializedName("integrated")
    @Expose
    private Boolean integrated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlashtag() {
        return slashtag;
    }

    public void setSlashtag(String slashtag) {
        this.slashtag = slashtag;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public Boolean getHttps() {
        return https;
    }

    public void setHttps(Boolean https) {
        this.https = https;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public Boolean getIntegrated() {
        return integrated;
    }

    public void setIntegrated(Boolean integrated) {
        this.integrated = integrated;
    }
}
