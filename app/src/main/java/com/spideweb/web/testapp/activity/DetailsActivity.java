package com.spideweb.web.testapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.google.gson.Gson;
import com.spideweb.web.testapp.adapter.AppCenterCategoriesAdapter;
import com.spideweb.web.testapp.adapter.DataAdapter;
import com.spideweb.web.testapp.adapter.HomeAdapter;
import com.spideweb.web.testapp.adapter.MoreAppAdapter;
import com.spideweb.web.testapp.adapter.SubCategoriesAdapter;
import com.spideweb.web.testapp.databinding.DetailsActivityBinding;
import com.spideweb.web.testapp.models.AppCenter;
import com.spideweb.web.testapp.models.Datum;
import com.spideweb.web.testapp.models.Home;
import com.spideweb.web.testapp.models.MoreApp;
import com.spideweb.web.testapp.models.NewAppDataCallBack;
import com.spideweb.web.testapp.models.SubCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class DetailsActivity extends AppCompatActivity {
    DetailsActivityBinding binding;
    Datum datum;
    List<NewAppDataCallBack> newAppDataCallBacksList = new ArrayList<>();
    List<Datum> datumList = new ArrayList<>();
    List<AppCenter> appCenters = new ArrayList<>();
    List<Home> homeList = new ArrayList<>();
    AppCenter appCenter;
    List<MoreApp> moreAppList = new ArrayList<>();

    DataAdapter dataAdapter;
    AppCenterCategoriesAdapter appCenterAdapter;
    MoreAppAdapter moreAppAdapter;
    HomeAdapter homeAdapter;
    int posAppCenter = 0;
    int posHome = 0;
    int posMore = 0;
    String heading = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailsActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.parentRecycle.setLayoutManager(layoutManager);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");

        List<SubCategory> AppsubCategories = (List<SubCategory>) args.getSerializable("SubCategoriesList");
        heading = getIntent().getStringExtra("AppCenterList");
//        Home home = (Home) getIntent().getSerializableExtra("homeList");
//        MoreApp moreApp = (MoreApp) getIntent().getSerializableExtra("moreAppList");

//        try {
//            posAppCenter = appCenter.getPosition();
//        } catch (Exception e) {
//
//        }
//        try {
//            posHome = home.getPosition();
//        } catch (Exception e) {
//
//        }
//        try {
//            posMore = moreApp.getPosition();
//
//        } catch (Exception e) {
//
//        }


//        if (posAppCenter == 1) {
//            binding.subcategoriesHeading.setText(appCenter.getName().toString());
//        } else if (posAppCenter == 2) {
//            binding.subcategoriesHeading.setText(appCenter.getName().toString());
//        } else if (posAppCenter == 3) {
//            binding.subcategoriesHeading.setText(appCenter.getName().toString());
//        } else if (posAppCenter == 4) {
//            binding.subcategoriesHeading.setText(appCenter.getName().toString());
//        } else if (posHome == 1) {
//            binding.subcategoriesHeading.setText(home.getName().toString());
//        } else if (posHome == 2) {
//            binding.subcategoriesHeading.setText(home.getName().toString());
//        } else if (posHome == 3) {
//            binding.subcategoriesHeading.setText(home.getName().toString());
//        } else if (posHome == 4) {
//            binding.subcategoriesHeading.setText(home.getName().toString());
//        } else if (posHome == 5) {
//            binding.subcategoriesHeading.setText(home.getName().toString());
//        } else if (posMore == 0) {
//            binding.subcategoriesHeading.setText(moreApp.getName().toString());
//        }


        binding.subcategoriesHeading.setText(heading);

        try {
            SubCategoriesAdapter subCategoriesAdapter = new SubCategoriesAdapter(this, AppsubCategories);
            binding.parentRecycle.setAdapter(subCategoriesAdapter);
        } catch (Exception e) {

        }


    }


}


//



