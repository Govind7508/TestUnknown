package com.spideweb.web.testapp.shopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.spideweb.web.testapp.databinding.ShoppingCategoriesCardListBinding;
import com.spideweb.web.testapp.databinding.ShoppingItemCardBinding;
import com.spideweb.web.testapp.shopping.models.ShoppingModel;

import java.util.List;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder> {
    Context context;
    List<ShoppingModel> shoppingModelList;

    public ShoppingAdapter(Context context, List<ShoppingModel> shoppingModelList) {
        this.context = context;
        this.shoppingModelList = shoppingModelList;
    }

    public void updateLsit(List<ShoppingModel> shoppingModelList) {
        this.shoppingModelList = shoppingModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    //    ShoppingCategoriesCardListBinding binding = ShoppingCategoriesCardListBinding.inflate(layoutInflater, parent, false);
        ShoppingItemCardBinding binding = ShoppingItemCardBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShoppingModel shoppingModel = shoppingModelList.get(position);
        holder.binding.setShopmodel(shoppingModel);


    }

    @Override
    public int getItemCount() {
        return shoppingModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        ShoppingCategoriesCardListBinding binding;
ShoppingItemCardBinding binding;
//        public ViewHolder(@NonNull ShoppingCategoriesCardListBinding binding) {
        public ViewHolder(@NonNull ShoppingItemCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
//@BindingAdapter("Image")
//    public static void imageUrl(ImageView v ,Context context, ShoppingModel shoppingModel){
//        String url = shoppingModel.getImage();
//    Glide.with(context).load(shoppingModel.getImage()).into(v);
//
//}
}
