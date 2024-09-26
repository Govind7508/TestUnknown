package com.spideweb.web.testapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spideweb.web.testapp.R;
import com.spideweb.web.testapp.models.MoreApp;
import com.spideweb.web.testapp.models.NewAppDataCallBack;

import java.util.List;

public class AllAppAdapter extends RecyclerView.Adapter<AllAppAdapter.ViewHolder> {
    List<NewAppDataCallBack> newAppDataCallBacksList;
    Context context;
    public AllAppAdapter(Context context, List<NewAppDataCallBack> newAppDataCallBacksList) {
        this.newAppDataCallBacksList = newAppDataCallBacksList;
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
        NewAppDataCallBack newAppDataCallBack = newAppDataCallBacksList.get(position);
        newAppDataCallBack.getData();
        newAppDataCallBack.getAppCenter();

    }

    @Override
    public int getItemCount() {
        return newAppDataCallBacksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categories_name;
        RecyclerView categories_recycle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categories_name = itemView.findViewById(R.id.categories_name);
            categories_recycle = itemView.findViewById(R.id.categories_recycle);
        }
    }
}