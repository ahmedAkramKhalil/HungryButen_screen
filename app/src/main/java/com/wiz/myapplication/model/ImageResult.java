package com.wiz.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Observable;

public class ImageResult extends BaseObservable implements Parcelable {
    @SerializedName("id") @Expose
    Long id ;
    @SerializedName("file") @Expose
    String path;

    public ImageResult(Long id, String path) {
        this.id = id;
        this.path = path;
    }

    public ImageResult() {
    }

    protected ImageResult(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        path = in.readString();
    }

    public static final Creator<ImageResult> CREATOR = new Creator<ImageResult>() {
        @Override
        public ImageResult createFromParcel(Parcel in) {
            return new ImageResult(in);
        }

        @Override
        public ImageResult[] newArray(int size) {
            return new ImageResult[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeString(path);
    }
}
