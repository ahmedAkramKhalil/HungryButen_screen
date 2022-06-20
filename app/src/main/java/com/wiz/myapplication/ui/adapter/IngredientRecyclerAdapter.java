package com.wiz.myapplication.ui.adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.wiz.myapplication.R;
import com.wiz.myapplication.databinding.IngredientItemBinding;
import com.wiz.myapplication.model.Ingredient;
import com.wiz.myapplication.model.IngredientsCategory;

import java.util.ArrayList;
import java.util.List;

public class IngredientRecyclerAdapter extends  RecyclerView.Adapter<IngredientRecyclerAdapter.IngredientViewHolder> {

    private Context context;
    private IngredientsCategory ingredientsCategory;
    IngredientItemBinding ingredientItemBinding ;
    List<Ingredient> ingredients ;


    public IngredientRecyclerAdapter(Context context, IngredientsCategory ingredientsCategory) {
        this.context = context;
        this.ingredientsCategory = ingredientsCategory;
        ingredients = ingredientsCategory.getIngredients();
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ingredientItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.ingredient_item,
                parent,
                false);

        return new IngredientViewHolder(ingredientItemBinding);


    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        holder.ingredientItemBinding.setIngredient(ingredient);
        if (ingredient.getAmount() != 0 ){
            holder.ingredientItemBinding.buttonsLayout.setVisibility(View.VISIBLE);
        }else {
            holder.ingredientItemBinding.buttonsLayout.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }



    public class IngredientViewHolder extends RecyclerView.ViewHolder {
        private IngredientItemBinding ingredientItemBinding;

        public IngredientViewHolder( IngredientItemBinding ingredientItemBinding) {

            super(ingredientItemBinding.getRoot());
            this.ingredientItemBinding = ingredientItemBinding;
        }

    }
}

