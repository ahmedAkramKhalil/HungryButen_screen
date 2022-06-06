package com.wiz.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wiz.myapplication.service.RetrofitInstance;

import java.util.Locale;

public class Ingredient extends BaseObservable implements Parcelable {

    @SerializedName("id") @Expose
    Long id;
    @SerializedName("name") @Expose
    String name;
    @SerializedName("name_en") @Expose
    String englishName;
    @SerializedName("description") @Expose
    String description;
    @SerializedName("description_en") @Expose
    String englishDescription ;
    @SerializedName("price") @Expose
    float price;
    @SerializedName("classification_id") @Expose
    long classificationID;
    @SerializedName("photo") @Expose
    ImageResult photo;
    int amount = 0 ;
    float totalPrice = 0 ;
    String animationPath = "https://assets8.lottiefiles.com/packages/lf20_dn48qc.json";



    public Ingredient() {
    }


    protected Ingredient(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        name = in.readString();
        englishName = in.readString();
        description = in.readString();
        englishDescription = in.readString();
        price = in.readFloat();
        classificationID = in.readLong();
        photo = in.readParcelable(ImageResult.class.getClassLoader());
        animationPath = in.readString();
    }


    @BindingAdapter({"posterPath"})
    public static void loadImage(ImageView imageView , ImageResult imageResult){
        String imagePath = RetrofitInstance.BASE_URL + imageResult.getPath() ;
        Glide.with(imageView.getContext())
                .load(imagePath)
                .into(imageView);

    }

    @BindingAdapter({"animationPath"})
    public static void loadAnimation(LottieAnimationView animationView ,String animationPath){
        animationView.setAnimationFromUrl(animationPath);
    }


    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    public float getTotalPrice() {
        return totalPrice ;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Bindable
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        setTotalPrice(amount *  price);
        notifyPropertyChanged(BR.amount);
    }

    @Bindable
    public String getAnimationPath(){
        return this.animationPath;
    }

    public void setAnimationPath(String animationPath){
        this.animationPath = animationPath ;
        notifyPropertyChanged(BR.animationPath);
    }

    @Bindable
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        notifyPropertyChanged(BR.id);

    }
    @Bindable
    public String getName() {
       if (Locale.getDefault().getLanguage()== "en")
           return getEnglishName();
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);

    }
    @Bindable
    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
        notifyPropertyChanged(BR.englishName);

    }
    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);

    }
    @Bindable
    public String getEnglishDescription() {
        return englishDescription;
    }

    public void setEnglishDescription(String englishDescription) {
        this.englishDescription = englishDescription;
        notifyPropertyChanged(BR.englishDescription);

    }
    @Bindable
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
        notifyPropertyChanged(BR.price);

    }
    @Bindable
    public long getClassificationID() {
        return classificationID;

    }

    public void setClassificationID(long classificationID) {
        this.classificationID = classificationID;
        notifyPropertyChanged(BR.classificationID);

    }

    @Bindable
    public ImageResult getPhoto() {
        return photo;
    }

    public void setPhoto(ImageResult photo) {
        this.photo = photo;
        notifyPropertyChanged(BR.photo );
    }

    public void incrementAmount(){
        setAmount(amount+1);
        Log.d("LO","" + amount);
    }

    public void decrementAmount(){
       if (this.amount > 0){
           setAmount(this.amount-1);
       }
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
        parcel.writeString(name);
        parcel.writeString(englishName);
        parcel.writeString(description);
        parcel.writeString(englishDescription);
        parcel.writeFloat(price);
        parcel.writeLong(classificationID);
        parcel.writeParcelable(photo, i);
        parcel.writeString(animationPath);
    }

    @Override
    public void addOnPropertyChangedCallback(@NonNull OnPropertyChangedCallback callback) {
        super.addOnPropertyChangedCallback(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(@NonNull OnPropertyChangedCallback callback) {
        super.removeOnPropertyChangedCallback(callback);
    }
}
