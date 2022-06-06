package com.wiz.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result extends BaseObservable implements Parcelable {
    @SerializedName("success") @Expose
    String success ;
    @SerializedName("data") @Expose
    List<IngredientsCategory> ingredientsCategoryList ;
    @SerializedName("message") @Expose
    String message ;

    protected Result(Parcel in) {
        success = in.readString();
        ingredientsCategoryList = in.createTypedArrayList(IngredientsCategory.CREATOR);
        message = in.readString();
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<IngredientsCategory> getIngredientsCategoryList() {
        return ingredientsCategoryList;
    }

    public void setIngredientsCategoryList(List<IngredientsCategory> ingredientsCategoryList) {
        this.ingredientsCategoryList = ingredientsCategoryList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(success);
        dest.writeTypedList(ingredientsCategoryList);
        dest.writeString(message);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
}
