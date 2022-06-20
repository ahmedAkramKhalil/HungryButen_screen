package com.wiz.myapplication;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class ItemDecorator extends RecyclerView.ItemDecoration {
    private final int mSpace;

    public ItemDecorator(int space) {

        this.mSpace = space;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);

//        if (position != 0) {
////            if (position == state.getItemCount()-1){
//                outRect.top = mSpace ;
//                outRect.bottom = 0;
//            }
            outRect.bottom = mSpace;
//        }else {
//            outRect.top = mSpace ;
//            outRect.bottom = 0;
//
//        }
    }
}
