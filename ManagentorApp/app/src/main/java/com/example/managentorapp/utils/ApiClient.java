package com.example.managentorapp.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    /*public static final String BASE_URL =
            "http://172.21.3.34:8080/RakutenTV/webresources/";*/

    public static final String BASE_URL1 = "http://192.168.1.42:8888/ManagentorApi/webresources/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL1)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

