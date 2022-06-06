package com.wiz.myapplication.repositories;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.wiz.myapplication.service.RetrofitInstance;
import com.wiz.myapplication.model.ApiResponseResult;
import com.wiz.myapplication.model.IngredientsCategory;
import com.wiz.myapplication.model.User;
import com.wiz.myapplication.service.RetrofitDataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepository {
    private List<IngredientsCategory> ingredientsCategories = new ArrayList<>();
    private MutableLiveData<List<IngredientsCategory>> ingredientLiveData = new MutableLiveData<>();
    private Application application;

    public ApiRepository (Application application){
        this.application = application;
    }

    public void login(User user){
      RetrofitDataService retrofitDataService = RetrofitInstance.getService();
      Call<User> call = retrofitDataService.login(user.getEmail(),user.getPassword());
      call.enqueue(new Callback<User>() {
          @Override
          public void onResponse(Call<User> call, Response<User> response) {
              //TODO:Save data and return value
          }

          @Override
          public void onFailure(Call<User> call, Throwable t) {

          }
      });

    }

    public void register(User user){
        RetrofitDataService retrofitDataService = RetrofitInstance.getService();
        Call<ApiResponseResult> call = retrofitDataService.register(user.getName(),user.getEmail(),user.getPassword(),user.getPassword());
        call.enqueue(new Callback<ApiResponseResult>() {
            @Override
            public void onResponse(Call<ApiResponseResult> call, Response<ApiResponseResult> response) {

            }

            @Override
            public void onFailure(Call<ApiResponseResult> call, Throwable t) {

            }
        });
    }

}
