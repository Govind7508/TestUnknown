package com.spideweb.web.testapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spideweb.web.testapp.R;
import com.spideweb.web.testapp.activity.DetailsActivity;
import com.spideweb.web.testapp.models.Home;
import com.spideweb.web.testapp.models.MoreApp;
import com.spideweb.web.testapp.models.SubCategory;

import java.io.Serializable;
import java.util.List;

public class MoreAppAdapter extends RecyclerView.Adapter<MoreAppAdapter.ViewHolder> {
    List<MoreApp> moreAppsList;
    Context context;
    int selectedPosition =-1;
    public MoreAppAdapter(Context context, List<MoreApp> moreAppsList) {
        this.moreAppsList = moreAppsList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categories_name_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MoreApp moreApp = moreAppsList.get(position);
        holder.categories_name.setText(moreApp.getName());
        holder.view_all_categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceedToNextActivity(position,moreApp.getName(),moreAppsList.get(position).getSubCategory());
            }

        });
        holder.categories_recycle.setLayoutManager(new LinearLayoutManager(context.getApplicationContext(), RecyclerView.HORIZONTAL, false));
SubCategoriesAdapter subCategoriesAdapter = new SubCategoriesAdapter(context ,moreAppsList.get(position).getSubCategory());
        holder.categories_recycle.setAdapter(subCategoriesAdapter);
    }



    @Override
    public int getItemCount() {
        return moreAppsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categories_name,view_all_categories;
        RecyclerView categories_recycle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categories_name = itemView.findViewById(R.id.categories_name);
            categories_recycle = itemView.findViewById(R.id.categories_recycle);
            view_all_categories = itemView.findViewById(R.id.view_all_categories);
        }
    }
    public void proceedToNextActivity(int position ,String heading , List<SubCategory> home) {
        if (context instanceof AppCompatActivity) {
            Intent intent = new Intent(context, DetailsActivity.class);
            Bundle args = new Bundle();

            intent.putExtra("AppCenterList", heading);
            args.putSerializable("SubCategoriesList", (Serializable) home);

            intent.putExtra("BUNDLE", args);
            context.startActivity(intent);
        }
    }
 }

