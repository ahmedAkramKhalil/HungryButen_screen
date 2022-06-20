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

import com.wiz.myapplication.R;
import com.wiz.myapplication.databinding.InvoiceRecyclerItemBinding;
import com.wiz.myapplication.databinding.ProductItemBinding;
import com.wiz.myapplication.model.AnimatedIngredient;
import com.wiz.myapplication.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class InvoiceRecyclerAdapter extends RecyclerView.Adapter<InvoiceRecyclerAdapter.IngredientViewHolder> {

    private Context context;
     InvoiceRecyclerItemBinding invoiceRecyclerItemBinding;
     List<Ingredient> ingredients;



    public InvoiceRecyclerAdapter(Context context,List<Ingredient> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        invoiceRecyclerItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.invoice_recycler_item,
                parent,
                false);
        return  new IngredientViewHolder(invoiceRecyclerItemBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        holder.invoiceRecyclerItemBinding.setIngredient(ingredient);
        Log.d("INV"," "+ position);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }



    public class IngredientViewHolder extends RecyclerView.ViewHolder {
        private InvoiceRecyclerItemBinding invoiceRecyclerItemBinding;
        public IngredientViewHolder(InvoiceRecyclerItemBinding invoiceRecyclerItemBinding) {
            super(invoiceRecyclerItemBinding.getRoot());
            this.invoiceRecyclerItemBinding = invoiceRecyclerItemBinding;
        }
    }




}

