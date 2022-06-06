package com.wiz.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActivitySplashBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLanguageChooserDialog();
            }
        });

    }

    public  void showLanguageChooserDialog() {


        LanguageDialogBinding dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.language_dialog, null, false);
         Intent intent = new Intent(SplashActivity.this,ChefActivity.class);

         dialogBinding.arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { intent.putExtra("language","ar");startActivity(intent); }});

        dialogBinding.english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { intent.putExtra("language","en");startActivity(intent); }});

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this,R.style.ThemeOverlay_App_MaterialAlertDialog)
                   .setView(dialogBinding.getRoot());
        builder.setBackground(new ColorDrawable(Color.TRANSPARENT));

        builder.show();

    }

}