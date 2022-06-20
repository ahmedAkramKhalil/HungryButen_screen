package com.wiz.myapplication.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wiz.myapplication.R;
import com.wiz.myapplication.service.RetrofitInstance;
import com.wiz.myapplication.viewmodel.ChefViewModel;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

public class IngredientsCategory extends BaseObservable implements Parcelable {
    @SerializedName("id") @Expose
    long id ;
    @SerializedName("name") @Expose
    String name;
    @SerializedName("name_en") @Expose
    String englishName;
    @SerializedName("ingredients") @Expose
    List<Ingredient> ingredients ;

    int imageResource;

    float totalPrice = 0;

    protected IngredientsCategory() {
    }

    protected IngredientsCategory(Parcel in) {
        id = in.readLong();
        name = in.readString();
        englishName = in.readString();
        ingredients = in.createTypedArrayList(Ingredient.CREATOR);
    }

    @BindingAdapter({"imgResource"})
    public static void loadImage(ImageView imageView , int imageResource){
        Glide.with(imageView.getContext())
                .load(imageResource)
                .into(imageView);
    }


    @Bindable
    public int getImageResource() {
        return getImage(getId());
//        return imageResource;
    }


    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
        notifyPropertyChanged(BR.imageResource);
    }

    public static final Creator<IngredientsCategory> CREATOR = new Creator<IngredientsCategory>() {
        @Override
        public IngredientsCategory createFromParcel(Parcel in) {
            return new IngredientsCategory(in);
        }

        @Override
        public IngredientsCategory[] newArray(int size) {
            return new IngredientsCategory[size];
        }
    };

    @Bindable
    @RequiresApi(api = Build.VERSION_CODES.N)
    public float getTotalPrice(){
        AtomicReference<Float> result = new AtomicReference<>((float) 0);
        ingredients.forEach(ingredient -> {
            result.updateAndGet(v -> new Float((float) (v + ingredient.getAmount() * ingredient.getPrice())));
        });
        this.totalPrice =  result.get();;

        return totalPrice;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setTotalPrice(float id) {
        AtomicReference<Float> result = new AtomicReference<>((float) 0);
        ingredients.forEach(ingredient -> {
            result.updateAndGet(v -> new Float((float) (v + ingredient.getAmount() * ingredient.getPrice())));
        });
        this.totalPrice =  result.get();;
        notifyPropertyChanged(BR.totalPrice);
    }


    @Bindable
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }
    @Bindable
    public String getName() {
        if (Locale.getDefault().getLanguage()=="en")
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
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        notifyPropertyChanged(BR.ingredients);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeString(englishName);
        parcel.writeTypedList(ingredients);
    }

    int getImage(long id){
        switch ((int) id) {
            case 1:
                return (R.drawable.bun_icon);
            case 2:
                return (R.drawable.meat_icon);
            case 3:
                return (R.drawable.cold_icon);
            case 4:
                return (R.drawable.hot_icon);
            case 5:
                return (R.drawable.sauce_icon);
            case 6:
                return (R.drawable.bune);
            default:
                return (R.drawable.bune);
        }
    }

}
