package com.wiz.myapplication.ui;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.wiz.myapplication.ActionEvent;
import com.wiz.myapplication.R;
import com.wiz.myapplication.databinding.LayoutDialogBinding;
import com.wiz.myapplication.viewmodel.ChefViewModel;

public class DialogBuilder {

    private Activity context;
    private static DialogBuilder dialogBuilder;
    private ChefViewModel viewModel;

    private DialogBuilder(Activity context, ChefViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;
    }

    public static DialogBuilder getInstance(Activity context, ChefViewModel viewModel) {
        if (dialogBuilder == null)
            dialogBuilder = new DialogBuilder(context, viewModel);
        return dialogBuilder;
    }

    public void showFinishDialog() {
        LayoutDialogBinding dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_dialog, null, false);
        dialogBinding.title.setText(context.getResources().getString(R.string.finish_dialog_title));
        dialogBinding.body.setText(context.getResources().getString(R.string.finish_dialog_message));
        dialogBinding.animationView.setAnimation("info_nutral.json");
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context, R.style.ThemeOverlay_App_MaterialAlertDialog)
                .setView(dialogBinding.getRoot());
        builder.setBackground(new ColorDrawable(Color.TRANSPARENT));
        dialogBinding.positive.setText(context.getResources().getString(R.string.yes));
        dialogBinding.positive.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.red_text_color)));
        dialogBinding.negative.setText(context.getResources().getString(R.string.dissmiss));
        AlertDialog alertDialog = builder.create();
        dialogBinding.positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.sendActionEvent(ActionEvent.FINISH_ORDERING);
                if (alertDialog != null)
                    alertDialog.dismiss();

            }
        });
        dialogBinding.negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alertDialog != null)
                    alertDialog.dismiss();

            }
        });
        alertDialog.show();

    }

    public void showCancelDialog() {
        LayoutDialogBinding dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_dialog, null, false);

        dialogBinding.title.setText(context.getResources().getString(R.string.cancel_dialog_title));
        dialogBinding.body.setText(context.getResources().getString(R.string.cancel_dialog_message));
        dialogBinding.animationView.setAnimation("info_error.json");
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context, R.style.ThemeOverlay_App_MaterialAlertDialog)
                .setView(dialogBinding.getRoot());
        builder.setBackground(new ColorDrawable(Color.TRANSPARENT));
        dialogBinding.positive.setText(context.getResources().getString(R.string.dissmiss));
        dialogBinding.negative.setText(context.getResources().getString(R.string.cancel));
        AlertDialog alertDialog = builder.create();
        dialogBinding.positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alertDialog != null)
                    alertDialog.dismiss();
            }
        });
        dialogBinding.negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:RESTART THE APP
                if (alertDialog != null)
                    alertDialog.dismiss();

            }
        });
        alertDialog.show();
    }

}
