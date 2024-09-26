package com.spideweb.web.testapp.shopping.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.spideweb.web.testapp.shopping.models.ShoppingModel;
import com.spideweb.web.testapp.shopping.repository.ShoppingRepository;

import java.util.List;

public class ShoppingViewModels extends AndroidViewModel {

    private LiveData<List<ShoppingModel>> shoppingModelLiveData;
    private static ShoppingRepository shoppingRepository;

    public ShoppingViewModels(@NonNull Application application) {
        super(application);
    }

    public void getShoppingRepositiory(){
        shoppingRepository = new ShoppingRepository();
        shoppingModelLiveData = shoppingRepository.listLiveData();
    }

    public LiveData<List<ShoppingModel>> getShoppingModelLiveData(){
        return shoppingModelLiveData;
    }

    public void callApi(){
        shoppingRepository.getApiCall();
    }

}
