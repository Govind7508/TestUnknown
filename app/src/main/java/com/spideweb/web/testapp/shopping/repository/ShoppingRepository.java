package com.spideweb.web.testapp.shopping.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.spideweb.web.testapp.Common.AppConst;
import com.spideweb.web.testapp.shopping.models.ShoppingModel;
import com.spideweb.web.testapp.shopping.retrofitpost.ShoppingRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShoppingRepository {
Context context;
    private static Retrofit retrofit;
    private MutableLiveData<List<ShoppingModel>> shoppingModelMutableLiveData;

public ShoppingRepository(){
    shoppingModelMutableLiveData = new MutableLiveData<>();
}

    public Retrofit retrofitData(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConst.SHOPPING_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public void getApiCall(){
        ShoppingRetrofit shoppingRetrofit = retrofitData().create(ShoppingRetrofit.class);
Call<List<ShoppingModel>> shoppingModelCall = shoppingRetrofit.SHOPPING_MODEL_CALL();
shoppingModelCall.enqueue(new Callback<List<ShoppingModel>>() {
    @Override
    public void onResponse(Call<List<ShoppingModel>> call, Response<List<ShoppingModel>> response) {
        if (response.isSuccessful()) {
            shoppingModelMutableLiveData.postValue(response.body());
        }else{
            Toast.makeText(context, "Something went Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<List<ShoppingModel>> call, Throwable t) {
        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
    }
});
    }


    public LiveData<List<ShoppingModel>> listLiveData(){
    return shoppingModelMutableLiveData;
    }

}
