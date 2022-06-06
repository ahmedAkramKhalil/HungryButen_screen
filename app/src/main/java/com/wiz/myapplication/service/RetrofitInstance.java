package com.wiz.myapplication.service;

import com.wiz.myapplication.service.RetrofitDataService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit = null ;
    public static final  String BASE_URL = "http://hungrybuten.ps";

    public static RetrofitDataService getService(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit.create(RetrofitDataService.class);
    }}
