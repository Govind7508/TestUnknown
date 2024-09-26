package com.spideweb.web.testapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.spideweb.web.testapp.Common.AppConst;
import com.spideweb.web.testapp.adapter.AppCenterCategoriesAdapter;
import com.spideweb.web.testapp.adapter.DataAdapter;
import com.spideweb.web.testapp.adapter.HomeAdapter;
import com.spideweb.web.testapp.adapter.MoreAppAdapter;
import com.spideweb.web.testapp.databinding.ActivityJsonParsingBinding;
import com.spideweb.web.testapp.models.AppCenter;
import com.spideweb.web.testapp.models.Datum;
import com.spideweb.web.testapp.models.Home;
import com.spideweb.web.testapp.models.MoreApp;
import com.spideweb.web.testapp.models.NewAppDataCallBack;
import com.spideweb.web.testapp.models.SubCategory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JsonParsingActivity extends AppCompatActivity {
    Datum datum;
    List<NewAppDataCallBack> newAppDataCallBacksList = new ArrayList<>();
    List<Datum> datumList = new ArrayList<>();
    List<AppCenter> appCentersList = new ArrayList<>();
    List<Home> homeList = new ArrayList<>();

    List<MoreApp> moreAppList = new ArrayList<>();
    RecyclerView app_data_recycle;
    ActivityJsonParsingBinding binding;
    DataAdapter dataAdapter;
    AppCenterCategoriesAdapter appCenterAdapter;
    MoreAppAdapter moreAppAdapter;
    HomeAdapter homeAdapter;
//    AppCenterCategoriesAdapter.ItemClickListener itemClickListener;
    HomeAdapter.getHome getHome;

    private Boolean textSearch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJsonParsingBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


//        new MyAsynTask().execute();
        getJsonParsingWithAndroidNetworking();
        textFindWithSearch();
        viewAllBtnClick();

    }

    public void getJsonParsingWithAndroidNetworking() {
        AndroidNetworking.get("https://vasundharaapps.com/artwork_apps/api/AdvertiseNewApplications/17/com.hd.camera.apps.high.quality")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Response", "Response Check : " + response);
//                        NewAppDataCallBack newAppDataCallBack = new NewAppDataCallBack();
                        try {
//                            get Data from Response
                            JSONArray getData = response.getJSONArray("data");


                            //This loop for fetching Data,s Data from Json Array
                            for (int i = 0; i < getData.length(); i++) {
                                datum = new Datum();
                                try {
                                    JSONObject jsonObject = (JSONObject) getData.get(i);
                                    datum.setName(jsonObject.get("name").toString());
                                    datum.setThumbImage(jsonObject.get("thumb_image").toString());
                                    datum.setPackageName(jsonObject.get("package_name").toString());
                                    if (datum != null) {
                                        datumList.add(datum);
                                        binding.dataParentRecycle.setVisibility(View.VISIBLE);
                                        binding.dataAppTextContainer.setVisibility(View.VISIBLE);
                                    } else {
                                        binding.dataParentRecycle.setVisibility(View.GONE);
                                        binding.dataAppTextContainer.setVisibility(View.GONE);
                                    }
                                } catch (Exception e) {

                                }

                            }

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        try {
                            JSONArray app_center_Array = response.getJSONArray("app_center");
                            for (int i = 0; i < app_center_Array.length(); i++) {
                                JSONObject app_center_object = app_center_Array.getJSONObject(i);
                                AppCenter appCenter = new AppCenter();
                                appCenter.setName(app_center_object.get("name").toString());
                                appCenter.setPosition((Integer) app_center_object.get("position"));

                                List<SubCategory> subCategoriesList = new ArrayList<>();

                                try {
                                    JSONArray subCategoryArray = app_center_Array.getJSONObject(i).getJSONArray("sub_category");
                                    for (int j = 0; j < subCategoryArray.length(); j++) {
                                        JSONObject subCategoriesJsonObject = subCategoryArray.getJSONObject(j);
                                        SubCategory subCategory = new SubCategory();
                                        subCategory.setName(subCategoriesJsonObject.get("name").toString());
                                        subCategory.setIcon(subCategoriesJsonObject.get("icon").toString());
                                        subCategory.setAppLink(subCategoriesJsonObject.get("app_link").toString());
                                        subCategoriesList.add(subCategory);
                                    }
                                } catch (Exception e) {
                                }

                                appCenter.setSubCategory(subCategoriesList);
                                appCentersList.add(appCenter);

                            }

                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                        try {
                            JSONArray homeCategories = response.getJSONArray("home");
                            for (int i = 0; i < homeCategories.length(); i++) {
                                JSONObject homeJsonObject = homeCategories.getJSONObject(i);
                                Home home = new Home();
                                home.setName(homeJsonObject.get("name").toString());
                                home.setPosition((Integer) homeJsonObject.get("position"));
                                JSONArray subCategoryArray = homeJsonObject.getJSONArray("sub_category");
                                List<SubCategory> subCategoriesList = new ArrayList<>();
                                try {
                                    for (int j = 0; j < subCategoryArray.length(); j++) {
                                        JSONObject subCategoriesJsonObject = subCategoryArray.getJSONObject(j);
                                        SubCategory subCategory = new SubCategory();
                                        subCategory.setName(subCategoriesJsonObject.get("name").toString());
                                        subCategory.setIcon(subCategoriesJsonObject.get("icon").toString());
                                        subCategory.setAppLink(subCategoriesJsonObject.get("app_link").toString());
                                        subCategoriesList.add(subCategory);
                                    }
                                } catch (Exception e) {
                                }
                                home.setSubCategory(subCategoriesList);
                                homeList.add(home);
                            }
                        } catch (Exception e) {

                        }
                        try {
                            JSONArray moreAppJsonArray = response.getJSONArray("more_apps");
                            for (int i = 0; i < moreAppJsonArray.length(); i++) {
                                JSONObject moreAppJsonObject = moreAppJsonArray.getJSONObject(i);
                                MoreApp moreApp = new MoreApp();
                                moreApp.setName(moreAppJsonObject.get("name").toString());
                                moreApp.setPosition((Integer) moreAppJsonObject.get("position"));
                                JSONArray moreAppSubcategory = moreAppJsonObject.getJSONArray("sub_category");
                                List<SubCategory> subCategories = new ArrayList<>();
                                for (int j = 0; j < moreAppSubcategory.length(); j++) {
                                    JSONObject moreAppSubcategoryObjects = moreAppSubcategory.getJSONObject(i);
                                    SubCategory subCategoryMoreApp = new SubCategory();
                                    subCategoryMoreApp.setName(moreAppSubcategoryObjects.get("name").toString());
                                    subCategoryMoreApp.setIcon(moreAppSubcategoryObjects.get("icon").toString());
                                    subCategoryMoreApp.setAppLink(moreAppSubcategoryObjects.get("app_link").toString());
                                    subCategories.add(subCategoryMoreApp);
                                }
                                moreApp.setSubCategory(subCategories);
                                moreAppList.add(moreApp);
                            }
                        } catch (Exception e) {

                        }
//  Datum Data set in recycleview
                        datumRecMeth(datumList);

//  App_center_recycle set in recycleview
                        appCenteRecMeth(appCentersList);
//  home Data set in recycleview
                        homeRecMeth(homeList);
////  MoreApp Data set in recycleview
                        moreAppRecMeth(moreAppList);

                        binding.shimmerLayout.startShimmer();

                        binding.shimmerLayout.setVisibility(View.GONE);
                        binding.nestedScroll.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(JsonParsingActivity.this, "something went wrong !", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    protected void onResume() {
        binding.shimmerLayout.startShimmer();
        super.onResume();
    }

    @Override
    protected void onPause() {
        binding.shimmerLayout.stopShimmer();
        super.onPause();
    }

    public void datumRecMeth(List<Datum> dummies) {
        if (dummies != null) {

            binding.dataParentRecycle.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
            dataAdapter = new DataAdapter(getApplicationContext(), dummies);
            binding.dataParentRecycle.setAdapter(dataAdapter);

        } else {
            binding.dataParentRecycle.setVisibility(View.GONE);
            binding.dataAppTextContainer.setVisibility(View.GONE);
        }
    }

    public void appCenteRecMeth(List<AppCenter> applist) {
//        itemClickListener = new AppCenterCategoriesAdapter.ItemClickListener() {
//            @Override
//            public void onClick(int position, AppCenter value) {
//
//
//            }
//
//        };
        binding.appDataRecycle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        appCenterAdapter = new AppCenterCategoriesAdapter(JsonParsingActivity.this, applist);
        binding.appDataRecycle.setAdapter(appCenterAdapter);

    }

    public void homeRecMeth(List<Home> homelist) {

//        getHome = new HomeAdapter.getHome() {
//            @Override
//            public void onClick(int position, Home value) {
//
//                Intent intent = new Intent(JsonParsingActivity.this, DetailsActivity.class);
//                Bundle args = new Bundle();
//
//                if(position == 0) {
//                    intent.putExtra("homeList", homeList.get(0));
//                    args.putSerializable("SubCategoriesList", (Serializable) homeList.get(0).getSubCategory());
//                }else if (position == 1){
//                    intent.putExtra("homeList", homeList.get(1));
//                    args.putSerializable("SubCategoriesList", (Serializable) homeList.get(1).getSubCategory());
//
//                }
//                else if (position == 2){
//                    intent.putExtra("homeList", homeList.get(2));
//                    args.putSerializable("SubCategoriesList", (Serializable) homeList.get(2).getSubCategory());
//
//                }
//                else if (position == 3){
//                    intent.putExtra("homeList", homeList.get(3));
//                    args.putSerializable("SubCategoriesList", (Serializable) homeList.get(3).getSubCategory());
//
//                }   else if (position == 4){
//                    intent.putExtra("homeList", homeList.get(4));
//                    args.putSerializable("SubCategoriesList", (Serializable) homeList.get(4).getSubCategory());
//
//                }
//                intent.putExtra("BUNDLE",args);
//                startActivity(intent);
//            }
//
//        };


        binding.homeParentRecycle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        homeAdapter = new HomeAdapter(JsonParsingActivity.this, homelist);
        binding.homeParentRecycle.setAdapter(homeAdapter);
    }

    public void moreAppRecMeth(List<MoreApp> morelist) {

        binding.moreParentRecycle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        moreAppAdapter = new MoreAppAdapter(getApplicationContext(), morelist);
        binding.moreParentRecycle.setAdapter(moreAppAdapter);
    }

    private void textFindWithSearch() {
        if (binding.searchEditText != null) {
            binding.searchEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    try {
                        if (s != null && s.toString().length() > 0) {
                            textSearch = true;
                        } else {
                            textSearch = false;
                        }
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    try {
                        if (s != null && s.toString().length() > 0 || textSearch) {
                            if (dataAdapter != null) {
                                dataAdapter.getFilter().filter(s.toString());
                            }
                        } else {
                            textSearch = false;
                        }
                    } catch (Exception e) {

                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    class MyAsynTask extends AsyncTask<Void, Boolean, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                getJsonParsingWithAndroidNetworking();
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (datumList != null && datumList.isEmpty()) {
                app_data_recycle.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                DataAdapter dataAdapter = new DataAdapter(getApplicationContext(), datumList);
                app_data_recycle.setAdapter(dataAdapter);
            } else {
            }
        }

    }

    public void viewAllBtnClick(){
        binding.viewAllCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}