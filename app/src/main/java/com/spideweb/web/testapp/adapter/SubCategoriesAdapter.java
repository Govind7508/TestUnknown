package com.spideweb.web.testapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.spideweb.web.testapp.R;
import com.spideweb.web.testapp.models.Datum;
import com.spideweb.web.testapp.models.SubCategory;

import java.util.List;

public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.ViewHolder> {
    List<SubCategory> subCategories;
    Context context;

    public SubCategoriesAdapter(Context context, List<SubCategory> subCategories) {
        this.context = context;
        this.subCategories = subCategories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.app_data_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SubCategory subCategory = subCategories.get(position);
        String image_url = subCategory.getIcon();
        Glide.with(context).load(image_url).into(holder.app_image);
        holder.app_name.setText(subCategory.getName());

        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String package_name = datum.getPackageName();
//                Intent intent_playstore = new Intent(Intent.ACTION_VIEW);
//                intent_playstore.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + package_name));
//                context.startActivity(intent_playstore);
                final String appPackageName = subCategory.getAppLink();
                if (appPackageName != null || !appPackageName.isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return subCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView app_name, download;
        ImageView app_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            app_name = itemView.findViewById(R.id.app_name);
            download = itemView.findViewById(R.id.download);
            app_image = itemView.findViewById(R.id.app_image);
        }
    }
}
