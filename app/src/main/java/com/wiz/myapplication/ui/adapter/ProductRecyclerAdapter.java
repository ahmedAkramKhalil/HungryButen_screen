package com.wiz.myapplication.ui.adapter;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.wiz.myapplication.model.AnimatedIngredient;
import com.wiz.myapplication.R;
import com.wiz.myapplication.databinding.ProductItemBinding;
import com.wiz.myapplication.model.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.IngredientViewHolder> {

    private Context context;
     ProductItemBinding productItemBinding;
     List<AnimatedIngredient> animatedIngredients;
    Handler mainHandler ;
    List<IReplayListener>  replayListeners = new ArrayList<>();


    public ProductRecyclerAdapter(Context context) {
        this.context = context;
        animatedIngredients = new ArrayList<>();
//        setHasStableIds(true);
        mainHandler = new Handler(Looper.getMainLooper());
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        productItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.product_item,
                parent,
                false);
         IngredientViewHolder ingredientViewHolder = new IngredientViewHolder(productItemBinding);
         replayListeners.add(ingredientViewHolder);
        return ingredientViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredient ingredient = animatedIngredients.get(position).getIngredient();
        holder.productItemBinding.setProduct(ingredient);
        holder.productItemBinding.setAnimation(animatedIngredients.get(position));

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                // Running playAnimation inside onBindViewHolder causes unusual behavior  so it posted to main thread
                //TODO: find a better solution for playAnimation() of lottie library
                holder.productItemBinding.animationView.playAnimation();
            }
        };
        mainHandler.post(myRunnable);
    }

    @Override
    public int getItemCount() {
        return animatedIngredients.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public  void updateItems(List<Ingredient> ingredientList) {

        if (animatedIngredients.size() > ingredientList.size()) {
            removeNotExistingObjects(ingredientList);
        }

        ingredientList.forEach(product -> {
            int position = 0 ;
            int count = 0;
            for (int i = 0; i < animatedIngredients.size(); i++) {
                if (animatedIngredients.get(i).getIngredient().getId() == product.getId()){
                    position = Math.max(animatedIngredients.indexOf(animatedIngredients.get(i)),position);
                    count++;

                }
            }

            if (product.getAmount() == 0 && count >= 0) {
                removeItemAtPosition(position);
            }
            if (product.getAmount() > 0) {
                if (count> product.getAmount()) {
                    removeItemAtPosition(position);
                } else if (count< product.getAmount()) {
                    AnimatedIngredient animatedIngredient = new AnimatedIngredient(product, false,position);
                    animatedIngredient.setPosition(position);
                    addItemAtPosition(animatedIngredient);
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void removeNotExistingObjects(List<Ingredient> list){
        List<Integer> toRemoveIds = new ArrayList<>();
        for (int i = 0; i < animatedIngredients.size(); i++) {
            if (!isThisIDExist(list,animatedIngredients.get(i).getIngredient().getId())){
                toRemoveIds.add(i);
            }
        }
        toRemoveIds.forEach(i -> removeItemAtPosition(i));
    }

    boolean isThisIDExist(List<Ingredient> list, long id){
        for (int i = 0; i < list.size(); i++) { if (list.get(i).getId() == id){ return true; } }
        return false;
    }

    public void addItemAtPosition(AnimatedIngredient ingredient) {
        animatedIngredients.add(ingredient.getPosition(), ingredient);
        notifyItemInserted(ingredient.getPosition());
    }

    public void removeItemAtPosition(int position) {
        if (animatedIngredients.size() > position)
            animatedIngredients.remove(position);
            notifyItemRemoved(position);
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder implements IReplayListener {
        private ProductItemBinding productItemBinding;
        public IngredientViewHolder(ProductItemBinding productItemBinding) {
            super(productItemBinding.getRoot());
            this.productItemBinding = productItemBinding;
        }

        @Override
        public void onReplayEvent() {
            this.productItemBinding.animationView.playAnimation();
        }
    }


    public void notifyAnimationReplay(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            replayListeners.forEach(iReplayListener -> iReplayListener.onReplayEvent());
        }

    }
    interface IReplayListener{
        void onReplayEvent();
    }

}

