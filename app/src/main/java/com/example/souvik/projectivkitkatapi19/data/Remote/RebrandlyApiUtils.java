package com.example.souvik.projectivkitkatapi19.data.Remote;

/**
 * Created by Souvik on 9-7-18.
 */

public class RebrandlyApiUtils
{
    private RebrandlyApiUtils()
    {}

    public static final String BASE_URL = "https://api.rebrandly.com/v1/";

    public static RebrandlyApiService getAPIService()
    {
        return RetrofitClient.getClient(BASE_URL).create(RebrandlyApiService.class);
    }
}
