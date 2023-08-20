package com.wiz.myapplication;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.HashMap;
import java.util.List;


public class CustomConstraintLayout extends ConstraintLayout {

    HashMap<Integer,View> viewHashMap ;
    List<Integer> positions;
    public CustomConstraintLayout(@NonNull Context context) {
        super(context);
    }

    void insertView(View view,int position){
        this.getChildAt(0);
        ConstraintSet set = new ConstraintSet();
        view.setId(position);
        addView(view,position);
        set.clone(this);
        set.connect(view.getId(), ConstraintSet.TOP, this.getId(), ConstraintSet.TOP, 60);
        set.applyTo(this);
    }

    void add(int position){
//        viewHashMap.get()

    }
}
