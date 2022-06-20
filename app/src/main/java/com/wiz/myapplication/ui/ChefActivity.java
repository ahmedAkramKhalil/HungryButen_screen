package com.wiz.myapplication.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.flexbox.AlignContent;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.robinhood.ticker.TickerUtils;
import com.wiz.myapplication.ActionEvent;
import com.wiz.myapplication.ItemDecorator;
import com.wiz.myapplication.ZoomOutPageTransformer;
import com.wiz.myapplication.ui.adapter.ProductRecyclerAdapter;
import com.wiz.myapplication.model.Ingredient;
import com.wiz.myapplication.model.Product;
import com.wiz.myapplication.ui.adapter.ViewPagerBinder;
import com.wiz.myapplication.viewmodel.ChefViewModel;
import com.wiz.myapplication.ui.adapter.IngredientsCategoryPagerAdapter;
import com.wiz.myapplication.R;
import com.wiz.myapplication.databinding.ActivityChefBinding;
import com.wiz.myapplication.model.IngredientsCategory;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import eightbitlab.com.blurview.RenderScriptBlur;

public class ChefActivity extends AppCompatActivity {

    private IngredientsCategoryPagerAdapter pagerAdapter;
    ProductRecyclerAdapter productRecyclerAdapter;
    ActivityChefBinding binding;
    ChefViewModel viewModel;
    ViewPager2 viewPager;
    RecyclerView productRecycler;
    List<Ingredient> productIngredients;
    List<IngredientsCategory> ingredientsCategoryList;
    Product product;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLocale(getIntent().getStringExtra("language").equalsIgnoreCase("ar") ? "ar" : "en");

        setContentView(R.layout.activity_chef);
//        setLocale("ar");
        Log.d("language", Locale.getDefault().getLanguage() + "  " + getIntent().getStringExtra("language"));

        binding = DataBindingUtil.setContentView(this, R.layout.activity_chef);
        viewModel = new ViewModelProvider(this).get(ChefViewModel.class);
        getIngredientsCategories();
        binding.setLifecycleOwner(this);
        binding.setHandler(this);
        product = new Product();
        binding.setVm(viewModel);
        binding.setProduct(product);
        binding.totalPrice.setCharacterLists(TickerUtils.provideNumberList());


        initRecycler();
        handleActionEvent();
    }

    public void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getIngredientsCategories() {
        viewModel.getCategoryLiveData().observe(this, new Observer<List<IngredientsCategory>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(List<IngredientsCategory> ingredientsCategories) {
                ingredientsCategoryList = ingredientsCategories;
                ingredientsCategories.forEach(ingredientsCategory -> {
                    ingredientsCategory.getIngredients().forEach(in -> {
                        in.addOnPropertyChangedCallback(callback);
                    });
                });
                showOnViewPager();
            }
        });
    }

    private void handleActionEvent() {
        viewModel.getEvent().observe(this, new Observer() {
            @Override
            public void onChanged(Object action) {
                ActionEvent actionEvent = (ActionEvent) action;
                switch (actionEvent) {
                    case SHOW_CANCEL_DIALOG_EVENT:
                        new DialogBuilder(ChefActivity.this, viewModel).showCancelDialog();
                        break;
                    case SHOW_FINISH_DIALOG_EVENT:
                        new DialogBuilder(ChefActivity.this, viewModel).showFinishDialog();
                        break;
                    case NEXT_PAGE_EVENT:
                        onClickToNext();
                        break;
                    case BACK_PAGE_EVENT:
                        onClickToBack();
                        break;
                    case FINISH_ORDERING:
                        showFinishOrderingTransition();
                        break;

                }

            }
        });
    }


    Observable.OnPropertyChangedCallback callback = new Observable.OnPropertyChangedCallback() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            if (propertyId == BR.amount) {
                product.setTotalPrice(ingredientsCategoryList);
                updateProductIngredientAdapter();
                enableNextButton(product.getTotalPrice());
            }
        }
    };


    void showFinishOrderingTransition() {
       // #TODO: transition
//        binding.container.transitionToEnd();
//        binding.container.addTransitionListener(new MotionLayout.TransitionListener() {
//            @Override
//            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {
//            }
//
//            @Override
//            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {
//            }
//
//            @Override
//            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
//                startCompressProductItemsAnimation();
//            }
//
//            @Override
//            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {
//            }
//        });

    }

    private void startCompressProductItemsAnimation() {
        productRecycler.addItemDecoration(new ItemDecorator(-90));
        productRecyclerAdapter.notifyAnimationReplay();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startInvoiceActivity();
            }
        }, 2000);

    }

    void startInvoiceActivity() {
        Intent in = new Intent(ChefActivity.this, InvoiceActivity.class);
        in.putParcelableArrayListExtra("ingredients", (ArrayList<? extends Parcelable>) productIngredients);
        in.putExtra("price", product.getTotalPrice());
        startActivity(in);
        finish();
    }

    private void showOnViewPager() {
        viewPager = binding.mainPager;
        pagerAdapter = new IngredientsCategoryPagerAdapter(this, ingredientsCategoryList);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(new ZoomOutPageTransformer());
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.setIngredient(pagerAdapter.getItem(viewPager.getCurrentItem()));
                if (pagerAdapter.getItemCount() - 1 > viewPager.getCurrentItem()) {
                    binding.nextButton.setText(getResources().getString(R.string.next));
                    binding.nextButton.setBackgroundTintList(getResources().getColorStateList(R.color.yellow));
                } else {

                    binding.nextButton.setBackgroundTintList(getResources().getColorStateList(R.color.red_text_color));
                    binding.nextButton.setText(getResources().getString(R.string.done));

                }

                if (viewPager.getCurrentItem() == pagerAdapter.getItemCount() - 1) {
                    enableNextButton(product.getTotalPrice());
                }
            }

        });
        SmartTabLayout tabLayout = binding.viewPagerTab;
        ViewPagerBinder.bind(tabLayout, viewPager);
    }

    public void enableNextButton(float price) {
        if (viewPager != null && pagerAdapter != null)
            if (viewPager.getCurrentItem() == pagerAdapter.getItemCount() - 1) {
                if (price <= 0.0) {
                    binding.nextButton.setEnabled(false);
                    binding.nextButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.button_negative_color)));
                } else {
                    binding.nextButton.setEnabled(true);
                    binding.nextButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red_text_color)));
                }
            }
    }


    public void onClickToNext() {

        if (pagerAdapter.getItemCount() - 1 > viewPager.getCurrentItem()) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            binding.nextButton.setClickable(true);
        } else {

            new DialogBuilder(this, viewModel).showFinishDialog();
        }
    }

    public void onClickToBack() {
        if (viewPager.getCurrentItem() != 0) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);

    void initRecycler() {
        productRecycler = binding.productRecyclerView;
        flexboxLayoutManager.setFlexDirection(FlexDirection.COLUMN);
        flexboxLayoutManager.setAlignItems(AlignItems.STRETCH);
        flexboxLayoutManager.setFlexWrap(FlexWrap.NOWRAP);

//        productRecycler.setLayoutManager(flexboxLayoutManager);
        productRecycler.setLayoutManager(new LinearLayoutManager(this));
        productRecycler.addItemDecoration(new ItemDecorator(-100));


    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateProductIngredientAdapter() {
        productIngredients = new ArrayList<>();
        ingredientsCategoryList.forEach(ingredientsCategory -> {
            ingredientsCategory.getIngredients().forEach(ingredient -> {
                if (ingredient.getAmount() > 0) {
                    productIngredients.add(ingredient);
                }
            });
        });
        if (productRecyclerAdapter == null) {
            productRecyclerAdapter = new ProductRecyclerAdapter(this);
            productRecycler.setAdapter(productRecyclerAdapter);
        }
        productRecyclerAdapter.updateItems(productIngredients);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ingredientsCategoryList.forEach(ingredientsCategory -> {
            ingredientsCategory.getIngredients().forEach(in -> {
                in.removeOnPropertyChangedCallback(callback);
            });
        });
    }

}