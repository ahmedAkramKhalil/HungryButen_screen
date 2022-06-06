package com.wiz.myapplication.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.wiz.myapplication.model.IngredientsCategory;
import com.wiz.myapplication.model.Result;
import com.wiz.myapplication.service.RetrofitInstance;
import com.wiz.myapplication.service.RetrofitDataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientRepository {
    private List<IngredientsCategory> ingredientsCategories = new ArrayList<>();
    private MutableLiveData<List<IngredientsCategory>> ingredientLiveData = new MutableLiveData<>();
    private Application application;

    public IngredientRepository (Application application){
        this.application = application;
    }

    public  MutableLiveData<List<IngredientsCategory>> getIngredientCategoryLiveData(){
        RetrofitDataService dataService = RetrofitInstance.getService();
        Call<Result>  call= dataService.getIngredients(sharedPreferencesGetToken());
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                Log.d("LOG","onResponse " + result);

                if (result != null && result.getIngredientsCategoryList()!= null){
                    ingredientsCategories = result.getIngredientsCategoryList();
                    ingredientLiveData.setValue(ingredientsCategories);

                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                Log.d("LOG","onResponse " + " " + t.getMessage());


            }
        });
        return ingredientLiveData;


    }

    //TODO: Create sharedPreferences class
    String sharedPreferencesGetToken(){
        return "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiNDQwZjNiYzFjZmUwZTYzZDQ2YTczYzJlNDFkMGU0YTc4ZGJmYjI2MDEyNDhhYWU5NzNmNDgxOTFlYTk5MTM2M2ZjZjA5NzA3NjhiNjI2NjUiLCJpYXQiOjE2NTI4OTIyODUsIm5iZiI6MTY1Mjg5MjI4NSwiZXhwIjoxNjg0NDI4Mjg1LCJzdWIiOiI2Mzg1Iiwic2NvcGVzIjpbXX0.XP8QimOdXx-zxD5Cr_iw31mWK705gQGlOabjiQGoCnNrnGnuExO7Y7YPGm5Ww_ege2RkFIGelLhe0LTBn4Fq0kxa-vRwfhRyaN2kut4RsCzJ407AZPKFu1PmavAgPBdWsUNtbArG6OYMzfoS31qblywUl9mUq5OnFiLSsSB65CqGp8_9DvI2ukuoFJxEeSXVBwUL-V74Ijmek5OOBNgymnaHIRVo38R_d7evzM7-SIGPKUUiAlR4YVtrxSx2wA3TvliWuIXY6HVv7DDpP4DCSNv_VwNsxZdkkn1v8XXo7uxFKLlAeZdc042I8mCDD9P9r_IL-InSo0OYpQlYGXLOZLzMpXSqiWPBdkQr1OdzrN6b3YXVAaEtqtSxqu3akh7eq4UQ4HReGo4vsf_oY8A8jVTl3-Wbnof7-KkFa6KY-smZpTR55ST4jjH5Un2HBYJLc_vOlHdTgsyJmeR2MXpYSYjunjUDncMWfVVl_-7SwnTchkvrBFfqDM7ERgq6qfFBmPUX2Tjg8HcNFmVWUKdo28VtdAERVnV0DT7hoh_NEMgX01cjFr5EpWcndznWB9HSnqIeEYC4pEocZPbkw0P3-SldshZAPf8g1BWmI7aA3BfxxS1u17Ndqszd0WzccYhjQCRyPIkm2wG6pQLYw4kE612_qyrEyQ5W6dZosddiHl4";
    }

}
