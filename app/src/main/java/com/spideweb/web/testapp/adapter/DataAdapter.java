package com.spideweb.web.testapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.spideweb.web.testapp.R;
import com.spideweb.web.testapp.activity.DetailsActivity;
import com.spideweb.web.testapp.models.Datum;

import org.w3c.dom.Text;

import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    Context context;
    List<Datum> datumList;

        private SearchFilter mFilter = new SearchFilter();
    public DataAdapter(Context context, List<Datum> datumList) {
        this.context = context;
        this.datumList = datumList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.app_data_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Datum datum = datumList.get(position);
        String image_url = datum.getThumbImage();
        Glide.with(context).load(image_url).into(holder.app_image);
        holder.app_name.setText(datum.getName());

        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String package_name = datum.getPackageName();
//                Intent intent_playstore = new Intent(Intent.ACTION_VIEW);
//                intent_playstore.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + package_name));
//                context.startActivity(intent_playstore);
                final String appPackageName = datum.getPackageName();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView app_name, download;
        ImageView app_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            app_name = itemView.findViewById(R.id.app_name);
            download = itemView.findViewById(R.id.download);
            app_image = itemView.findViewById(R.id.app_image);
//            view_all_categories = itemView.findViewById(R.id.view_all_categories);
        }
    }

    private class SearchFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String searchText = constraint.toString().toLowerCase(Locale.ROOT);
            FilterResults filterResults = new FilterResults();
            final List<Datum> list = datumList;
            int count = list.size();

            final List<Datum> datumList = new ArrayList<>(count);
            Datum filterableString;
            for (int i = 0; i < count; i++) {
                filterableString = list.get(i);
                if (filterableString.getName().toLowerCase().contains(searchText)) {
                    datumList.add(filterableString);
                }
            }
            filterResults.values = datumList;
            filterResults.count = datumList.size();

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            datumList = (List<Datum>) results.values;
            notifyDataSetChanged();
        }
    }
    public Filter getFilter() {
        return mFilter;
    }

}
