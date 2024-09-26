package com.spideweb.web.testapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spideweb.web.testapp.R;
import com.spideweb.web.testapp.activity.DetailsActivity;
import com.spideweb.web.testapp.activity.JsonParsingActivity;
import com.spideweb.web.testapp.models.AppCenter;
import com.spideweb.web.testapp.models.Datum;
import com.spideweb.web.testapp.models.SubCategory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AppCenterCategoriesAdapter extends RecyclerView.Adapter<AppCenterCategoriesAdapter.ViewHolder> {
    List<AppCenter> appCenters;
    Context context;
    int selectedPosition = -1;

    public AppCenterCategoriesAdapter(Context context, List<AppCenter> appCenters) {
        this.appCenters = appCenters;
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
        AppCenter appCenter = appCenters.get(position);
        holder.categories_name.setText(appCenter.getName());
        holder.view_all_categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (itemClickListener != null) {
//                    itemClickListener.onClick(position, appCenter);
//                    selectedPosition = position;
//                    notifyDataSetChanged();
//
//                }

                String headingName = "";

                if(appCenter.getName() == null || appCenter.getName().isEmpty()){
                    headingName = appCenters.get(position).getName();
                }
                else{
                    headingName = appCenter.getName();
                }


                proceedToNextActivity(position,headingName,appCenters.get(position).getSubCategory());
            }
        });
        holder.categories_recycle.setLayoutManager(new LinearLayoutManager(context.getApplicationContext(), RecyclerView.HORIZONTAL, false));
        SubCategoriesAdapter subCategoriesAdapter = new SubCategoriesAdapter(context, appCenters.get(position).getSubCategory());
        holder.categories_recycle.setAdapter(subCategoriesAdapter);
    }

    @Override
    public int getItemCount() {
        return appCenters.size();
    }

    public void proceedToNextActivity(int position, String heading, List<SubCategory> applist) {

        if(context instanceof AppCompatActivity){
            Intent intent = new Intent(context, DetailsActivity.class);
            Bundle args = new Bundle();

            intent.putExtra("AppCenterList", heading);
            args.putSerializable("SubCategoriesList", (Serializable) applist);

            intent.putExtra("BUNDLE", args);
            context.startActivity(intent);
        }


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categories_name, view_all_categories;
        RecyclerView categories_recycle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categories_name = itemView.findViewById(R.id.categories_name);
            categories_recycle = itemView.findViewById(R.id.categories_recycle);
            view_all_categories = itemView.findViewById(R.id.view_all_categories);
        }
    }

    public class Listfilter extends Filter {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return super.convertResultToString(resultValue);
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

        }
    }
}
