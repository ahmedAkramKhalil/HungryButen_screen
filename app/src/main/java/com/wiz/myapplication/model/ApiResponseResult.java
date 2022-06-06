package com.wiz.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponseResult implements Parcelable {
    @SerializedName("success") @Expose
    User user;

    public ApiResponseResult(User user) {
        this.user = user;
    }
    public ApiResponseResult() {
    }

    protected ApiResponseResult(Parcel in) {
        user = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<ApiResponseResult> CREATOR = new Creator<ApiResponseResult>() {
        @Override
        public ApiResponseResult createFromParcel(Parcel in) {
            return new ApiResponseResult(in);
        }

        @Override
        public ApiResponseResult[] newArray(int size) {
            return new ApiResponseResult[size];
        }
    };

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(user, i);
    }
}
