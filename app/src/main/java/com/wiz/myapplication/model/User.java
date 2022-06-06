package com.wiz.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User extends BaseObservable implements Parcelable {

    @SerializedName("user_id")
    @Expose
    @Nullable
    String id;
    @SerializedName("name")
    @Expose
    @Nullable
    String name;
    @SerializedName("email")
    @Expose
    @Nullable
    String email;
    @SerializedName("password")
    @Expose
    @Nullable
    String password;
    @SerializedName("token")
    @Expose
    @Nullable
    String token;


    protected User(Parcel in) {
        id = in.readString();
        name = in.readString();
        email = in.readString();
        password = in.readString();
        token = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(token);
    }
}
