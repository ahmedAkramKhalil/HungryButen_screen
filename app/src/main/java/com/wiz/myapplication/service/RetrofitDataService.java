package com.wiz.myapplication.service;

import com.wiz.myapplication.model.ApiResponseResult;
import com.wiz.myapplication.model.Result;
import com.wiz.myapplication.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitDataService {
    @GET("/api/auth/ingredients")
    Call<Result> getIngredients(@Header("Authorization") String token );

    @POST("/api/auth/login")
    Call<User> login(@Query("email") String email , @Query("password") String password);

    @POST("/api/auth/register")
    Call<ApiResponseResult> register(@Query("name") String name,
                                     @Query("email") String email,
                                     @Query("password") String password,
                                     @Query("password_confirmation") String passwordConfirmation
                          );




}
