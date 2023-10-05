package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    String[] brandnames={"foxtale","cetaphil","derma co","dot and key","plum","the face shop"};

    ListView brandlistView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //setids
        brandlistView=findViewById(R.id.listView);
        //adapter
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,brandnames);
        brandlistView.setAdapter(adapter);
    }
}