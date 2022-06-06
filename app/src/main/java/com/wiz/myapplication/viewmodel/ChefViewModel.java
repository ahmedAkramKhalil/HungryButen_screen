package com.wiz.myapplication.viewmodel;

import  android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.wiz.myapplication.ActionEvent;
import com.wiz.myapplication.model.IngredientsCategory;
import com.wiz.myapplication.model.Product;
import com.wiz.myapplication.repositories.IngredientRepository;

import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class ChefViewModel extends AndroidViewModel   {
    IngredientRepository repository ;
    MutableLiveData<List<IngredientsCategory>> listMutableLiveData ;
    MutableLiveData<ActionEvent> eventTrigger;



    public ChefViewModel(@NonNull Application application) {
        super(application);
        repository = new IngredientRepository(application);
        eventTrigger = new MutableLiveData<>();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)

    public MutableLiveData<List<IngredientsCategory>> getCategoryLiveData(){
        listMutableLiveData = repository.getIngredientCategoryLiveData();
        return listMutableLiveData;
    }

    public MutableLiveData getEvent(){
        return eventTrigger;
    }

    public void sendActionEvent(ActionEvent action){
        eventTrigger.postValue(action);
    }





}
