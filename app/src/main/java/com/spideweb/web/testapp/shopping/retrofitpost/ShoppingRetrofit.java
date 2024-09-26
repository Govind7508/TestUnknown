package com.spideweb.web.testapp.shopping.retrofitpost;



import com.spideweb.web.testapp.shopping.models.ShoppingModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ShoppingRetrofit {
    @GET("products")
    Call<List<ShoppingModel>> SHOPPING_MODEL_CALL();
}
