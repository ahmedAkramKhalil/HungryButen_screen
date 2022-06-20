package com.wiz.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.wiz.myapplication.ActionEvent;
import com.wiz.myapplication.R;
import com.wiz.myapplication.databinding.ActivitySplashBinding;
import com.wiz.myapplication.databinding.LanguageDialogBinding;

public class SplashActivity extends AppCompatActivity {


    ActivitySplashBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
         binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        binding.startButtonAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this, ChefActivity.class);
                Pair<View, String> p1 = Pair.create(binding.joLogo, "jo");
                Pair<View, String> p2 = Pair.create(binding.hbLogo, "hb");
                Pair<View, String> p3 = Pair.create(binding.customBurgerTv, "text");
                intent.putExtra("language","ar");

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(SplashActivity.this, p1, p2, p3);
                startActivity(intent,options.toBundle());
//                showLanguageChooserDialog();
            }
        });

    }

    public  void showLanguageChooserDialog() {


        LanguageDialogBinding dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.language_dialog, null, false);
        Intent intent = new Intent(SplashActivity.this, ChefActivity.class);
        Pair<View, String> p1 = Pair.create(binding.joLogo, "jo");
        Pair<View, String> p2 = Pair.create(binding.hbLogo, "hb");
        Pair<View, String> p3 = Pair.create(binding.customBurgerTv, "text");
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(SplashActivity.this, p1, p2, p3);

         dialogBinding.arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("language","ar");
                startActivity(intent, options.toBundle());
            }});

        dialogBinding.english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { intent.putExtra("language","en");
                startActivity(intent, options.toBundle());

            }});

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this,R.style.ThemeOverlay_App_MaterialAlertDialog)
                   .setView(dialogBinding.getRoot());
        builder.setBackground(new ColorDrawable(Color.TRANSPARENT));

        builder.show();

    }

}