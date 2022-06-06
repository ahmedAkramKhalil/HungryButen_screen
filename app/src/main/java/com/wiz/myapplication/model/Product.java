package com.wiz.myapplication.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

public class Product extends BaseObservable implements Parcelable {
    @Expose @SerializedName("name")
    String name ="";
    @Expose @SerializedName("ingredients")
    List<IngredientsCategory> ingredientsCategoryList = new ArrayList<>();
    float totalPrice = 0;

    public Product() {
    }

    public Product(Parcel in) {
        name = in.readString();
        ingredientsCategoryList = in.createTypedArrayList(IngredientsCategory.CREATOR);
        totalPrice = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
    @Bindable
    public List<IngredientsCategory> getIngredientsCategoryList() {
        return ingredientsCategoryList;
    }

    public void setIngredientsCategoryList(List<IngredientsCategory> ingredientsCategoryList) {
        this.ingredientsCategoryList = ingredientsCategoryList;
        notifyPropertyChanged(BR.ingredientsCategoryList);
    }
    @Bindable
    public float getTotalPrice() {
        return totalPrice;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setTotalPrice(List<IngredientsCategory> ingredientsCategoryList) {
         AtomicReference<Float> totalPrice = new AtomicReference<>((float) 0);
        ingredientsCategoryList.forEach(ingredientsCategory -> {
            totalPrice.updateAndGet(v -> new Float((float) (v + ingredientsCategory.getTotalPrice())));
        });
        this.totalPrice = totalPrice.get();
        notifyPropertyChanged(BR.totalPrice);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeTypedList(ingredientsCategoryList);
        parcel.writeFloat(totalPrice);
    }
}
