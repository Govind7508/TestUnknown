package com.spideweb.web.testapp.shopping.badapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.spideweb.web.testapp.shopping.models.ShoppingModel;

public class BAdapter {
    Context context;

    @BindingAdapter("Image")
    public static void imageUrl(ImageView v, String fileName){
        Glide.with(v.getContext()).load(fileName).into(v);

    }

}
