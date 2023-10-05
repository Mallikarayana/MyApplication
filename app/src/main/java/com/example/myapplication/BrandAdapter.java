package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ActionBarPolicy;

import com.squareup.picasso.Picasso;

import java.io.File;

public class BrandAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] products;

    private String[] logos;


    public BrandAdapter(Context context, String[] cafes, String[] logos) {
        super(context, R.layout.custom_brand_item, cafes);

        this.context = context;
        this.products = cafes;
        this.logos = logos;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.custom_brand_item, null, true);


        ImageView logoImg = item.findViewById(R.id.brand_img);
        TextView brandname = item.findViewById(R.id.brandname);

        brandname.setText(products[position]);
        //logoImg.setImageResource(logos[position]);
        File file=new File(logos[position]).getAbsoluteFile();
        Picasso.get().load(file).into(logoImg);

        return item;
    }
}