package com.wiz.myapplication.ui.adapter;

import android.app.Activity;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.wiz.myapplication.BR;
import com.wiz.myapplication.databinding.ActivityChefBinding;
import com.wiz.myapplication.model.Ingredient;
import com.wiz.myapplication.ui.fragment.ChefItemFragment;
import com.wiz.myapplication.model.IngredientsCategory;

import java.util.List;
import java.util.Observable;

public class IngredientsCategoryPagerAdapter extends FragmentStateAdapter  {
    List<IngredientsCategory> ingredientsCategoryList ;
    Activity activity ;
    IngredientsCategory ingredientsCategory ;



    public IngredientsCategoryPagerAdapter(FragmentActivity fa , List<IngredientsCategory> ingredientsCategoryList) {
        super(fa);
        this.activity = fa;
        this.ingredientsCategoryList = ingredientsCategoryList ;

    }

    @Override
    public Fragment createFragment(int position) {
        return new ChefItemFragment(ingredientsCategoryList.get(position));
    }

    @Override
    public void onBindViewHolder(@NonNull FragmentViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }



    @Override
    public int getItemCount() {
        return ingredientsCategoryList.size() -1 ;
    }


    @Override
    public void onViewDetachedFromWindow(@NonNull FragmentViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }


    public IngredientsCategory getItem(int position) {
        if (!ingredientsCategoryList.isEmpty())
            return ingredientsCategoryList.get(position);
        return null;
    }
}

