package com.example.souvik.projectivkitkatapi19.data.Remote;

import com.example.souvik.projectivkitkatapi19.data.RebrandlyModel.Response.Response;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Souvik on 9-7-18.
 */

public interface RebrandlyApiService
{
    String rebrandlyApiKey = "fce9948d80714287af1036f30a3a2112";        //Rebrandly API key
    @Headers({
            "apikey: " + rebrandlyApiKey,
            "Content-Type: application/json"
    })
    @POST("links")
    Call<Response> savePost(@Body com.example.souvik.projectivkitkatapi19.data.RebrandlyModel.Request.Body requestBody);
}
