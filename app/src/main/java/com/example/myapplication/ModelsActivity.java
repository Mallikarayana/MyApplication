package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ModelsActivity extends AppCompatActivity {

    ListView modellistview;

    TextView brandname;
    String[] models;

    Integer[] logos;
    int[] logosint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_models);
        //set ids
        modellistview = findViewById(R.id.items_lv);
        brandname = findViewById(R.id.items_tv);

        //receive data
        models=getIntent().getStringArrayExtra("models");
        String Brandname=getIntent().getStringExtra("brandname");
        logosint=getIntent().getIntArrayExtra("logos");
        //logos=toConvertInteger(logosint);


        //Adapter
        //ArrayAdapter adapter =new ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,models);
        //BrandAdapter adapter=new BrandAdapter(this,models,logos);
        //modellistview.setAdapter(adapter);

        brandname.setText(Brandname);

    }
    public static Integer[] toConvertInteger(int[] ids){

        Integer[] newArray=new Integer[ids.length];
        for (int i=0; i<ids.length; i++){
            newArray[i]=Integer.valueOf(ids[i]);
        }
        return newArray;
    }
}