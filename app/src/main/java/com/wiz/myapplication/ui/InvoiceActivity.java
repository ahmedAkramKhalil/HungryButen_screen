package com.wiz.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.wiz.myapplication.R;
import com.wiz.myapplication.databinding.ActivityInvoiceBinding;
import com.wiz.myapplication.model.Ingredient;
import com.wiz.myapplication.ui.adapter.InvoiceRecyclerAdapter;

import java.util.List;
import java.util.Random;

public class InvoiceActivity extends AppCompatActivity {

    List<Ingredient> ingredientList ;
    InvoiceRecyclerAdapter adapter ;
    RecyclerView recyclerView ;
    float totalPrice  ;
    TextView priceTv;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        ActivityInvoiceBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_invoice);
        ingredientList = getIntent().getParcelableArrayListExtra("ingredients");
        totalPrice = getIntent().getFloatExtra("price",0.0f);
        name = getIntent().getStringExtra("name");
        recyclerView = binding.recyclerView ;
        priceTv = binding.totalPrice;

        animateTextView();
        initRecycler();
        binding.componentName.setText(name + "`s Custom Burger ");

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    void initRecycler(){
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_up_to_down);
        adapter = new InvoiceRecyclerAdapter(this,ingredientList);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutAnimation(controller);
        adapter.notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    public void animateTextView() {
        float initialValue = 0;
        float finalValue = totalPrice ;
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator(0.9f);
        float start = Math.min(initialValue, finalValue);
        float end = Math.max(initialValue, finalValue);
        float difference = Math.abs(finalValue - initialValue);
        Handler handler = new Handler();
        for (float count = start; count <= end; count++) {
            float time = Math.round(decelerateInterpolator.getInterpolation((((float) count) / difference)) * (900/finalValue)) * count;
            final float finalCount = ((initialValue > finalValue) ? initialValue - count : count);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    priceTv.setText(String.valueOf(finalCount));
                }
            }, (long) time);
        }
    }

}