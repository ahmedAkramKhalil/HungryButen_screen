package com.wiz.myapplication.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.wiz.myapplication.R;

public class ViewPagerBinder extends BaseObservable {

    public static void bind(SmartTabLayout tabLayout , ViewPager2 pager2  ) {
        Context context = tabLayout.getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);
        final Resources res = tabLayout.getContext().getResources();

        tabLayout.setCustomTabView(new SmartTabLayout.TabProvider() {
            @Override
            public View createTabView(ViewGroup container, int position, FragmentStateAdapter adapter) {
                ImageView icon = (ImageView) inflater.inflate(R.layout.tab_icon_layout, container,
                        false);
                IngredientsCategoryPagerAdapter adapter1 =  (IngredientsCategoryPagerAdapter) adapter ;
                 icon.setImageDrawable(res.getDrawable(adapter1.getItem(position).getImageResource()));
                return icon;
            }
        });
        tabLayout.setViewPager(pager2);
    }


}
