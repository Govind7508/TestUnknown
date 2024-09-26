package com.spideweb.web.testapp.shopping.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.widget.GridLayout;

import com.spideweb.web.testapp.R;
import com.spideweb.web.testapp.databinding.ActivityShoppingBinding;
import com.spideweb.web.testapp.shopping.adapter.ShoppingAdapter;
import com.spideweb.web.testapp.shopping.models.ShoppingModel;
import com.spideweb.web.testapp.shopping.repository.ShoppingRepository;
import com.spideweb.web.testapp.shopping.viewmodels.ShoppingViewModels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShoppingActivity extends AppCompatActivity {
List<ShoppingModel> shoppingModelList = new ArrayList<>();
ShoppingRepository shoppingRepository;
ShoppingAdapter shoppingAdapter;
ShoppingViewModels shoppingViewModels;
ActivityShoppingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShoppingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        binding.recycle.setLayoutManager(gridLayoutManager);
        shoppingAdapter = new ShoppingAdapter(ShoppingActivity.this,shoppingModelList);
        binding.recycle.setAdapter(shoppingAdapter);



        shoppingViewModels = new ViewModelProvider(this).get(ShoppingViewModels.class);
        shoppingViewModels.getShoppingRepositiory();

        shoppingViewModels.getShoppingModelLiveData().observe(this, new Observer<List<ShoppingModel>>() {
            @Override
            public void onChanged(List<ShoppingModel> shoppingModelLists) {
                if (shoppingModelLists != null) {
                    shoppingModelList = shoppingModelLists;
                    List<String> catShoppingList=new ArrayList<>();
                    List finalShoppingList=new ArrayList();
                    for(ShoppingModel sm:shoppingModelList){
                        if(!catShoppingList.contains(sm.getCategory())){
                            catShoppingList.add(sm.getCategory());
                            finalShoppingList.add(sm);
                        }

                    }
//                    Set<ShoppingModel> set=new HashSet<>();
//                    set.addAll(shoppingModelList);
//                    shoppingModelList.clear();
//                    shoppingModelList.addAll(set)

                    shoppingAdapter.updateLsit(finalShoppingList);

                }
            }
        });



        shoppingViewModels.callApi();


    }
}