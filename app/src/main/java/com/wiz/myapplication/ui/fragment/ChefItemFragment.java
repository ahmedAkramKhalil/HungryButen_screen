package com.wiz.myapplication.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wiz.myapplication.R;
import com.wiz.myapplication.ui.adapter.IngredientRecyclerAdapter;
import com.wiz.myapplication.databinding.FragmentChefItemBinding;
import com.wiz.myapplication.model.IngredientsCategory;

public class ChefItemFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    IngredientsCategory ingredientsCategory;
    FragmentChefItemBinding chefItemBinding;

    RecyclerView recyclerView;
    IngredientRecyclerAdapter ingredientRecyclerAdapter ;

    private static ChefItemFragment chefItemFragment= null ;

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public ChefItemFragment(IngredientsCategory ingredientsCategory) {
        // Required empty public constructor
        this.ingredientsCategory = ingredientsCategory;

    }


    public ChefItemFragment() {
    }

   public static ChefItemFragment getInstance(IngredientsCategory ingredientsCategory){
        if (chefItemFragment == null){
            chefItemFragment = new ChefItemFragment(ingredientsCategory);
        }
            return chefItemFragment;
   }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        chefItemBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_chef_item, container, false);
        View view = chefItemBinding.getRoot();
        showOnRecyclerView(ingredientsCategory);
        return view;
    }

    private void showOnRecyclerView(IngredientsCategory ingredientsCategory) {
        recyclerView = chefItemBinding.rvIngredients;
        ingredientRecyclerAdapter = new IngredientRecyclerAdapter(getContext(), ingredientsCategory);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(ingredientRecyclerAdapter);
        ingredientRecyclerAdapter.notifyDataSetChanged();
    }



}