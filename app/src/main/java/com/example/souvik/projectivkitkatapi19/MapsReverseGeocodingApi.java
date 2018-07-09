package com.example.souvik.projectivkitkatapi19;

import com.example.souvik.projectivkitkatapi19.data.JsonConverter;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Souvik on 1-7-18.
 */

public interface MapsReverseGeocodingApi
{
    @GET
    Call<JsonConverter> getResponse(@Url String url);
}