package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BrandActivity extends AppCompatActivity {

    String[] brand_names={};
    String[] logos={};


    ListView listview;

    BeautyZoneSqlite beautyZoneSqlite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brands);
        beautyZoneSqlite = new BeautyZoneSqlite(this);
        //set ids
        listview=findViewById(R.id.custom_list);
        String purpose="category";

        readCategory(purpose);
        //Adapter
        BrandAdapter adapter=new BrandAdapter(this,brand_names, logos);
        listview.setAdapter(adapter);



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int position=i;
                String brandsselected=listview.getItemAtPosition(position).toString();
                Toast.makeText(BrandActivity.this, "Selected: "+brandsselected , Toast.LENGTH_SHORT).show();

//                String[] models={""};
//                int[] logosarray={};
//                if (brandsselected.equals("cetaphil")){
//                    models=brand1;
//                    logosarray=toint(logos1);
//                }
//                else if(brandsselected.equals("foxtale")){
//                    models=brand2;
//                    logosarray=toint(logos2);
//                }
//
//                else if (brandsselected.equals("dermaco")){
//                    models=brand3;
//                    logosarray=toint(logos3);
//
//                }
//                else if (brandsselected.equals("dotandkey")){
//                    models=brand4;
//                    logosarray=toint(logos4);
//                }
//
//                else if (brandsselected.equals("plum")){
//                    models=brand5;
//                    logosarray=toint(logos5);
//                }
//
//                else if (brandsselected.equals("thefaceshop")){
//                    models=brand6;
//                    logosarray=toint(logos6);
//                }

//                Intent intent=new Intent(BrandActivity.this, com.example.reshma.ModelsActivity.class);
//                intent.putExtra("models",models);
//                intent.putExtra("brandname",brandsselected);
//                intent.putExtra("logos",logosarray);
//                startActivity(intent);
            }
        });
    }
    private void readCategory(String purpose){
        String  condition=null;
        Cursor result=beautyZoneSqlite.listData(purpose,condition);
        if (result.getCount()==0){
            displayMessage("data","No Data Found");
        }

        ArrayList<String> arrayList=new ArrayList<>();
        ArrayList<String> logoArray=new ArrayList<>();
        while (result.moveToNext()){
            arrayList.add(result.getString(1));
            logoArray.add(result.getString(2));
            Log.i("image db:", result.getString(2));
        }

        brand_names=new String[arrayList.size()];
        for (int i=0;i<arrayList.size();i++){
            brand_names[i]=arrayList.get(i);
        }
        logos=new String[logoArray.size()];
        for(int i=0; i<arrayList.size();i++){
            logos[i]=logoArray.get(i);
            Log.i("image address:"+i,logoArray.get(i));
        }

    }

    private void displayMessage(String data, String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(data);
        builder.setMessage(message);
        builder.show();
    }

    public static int[] toint(Integer[] WrapperArray){
        int[] newArray = new int[WrapperArray.length];
        for (int i=0;i<WrapperArray.length;i++){
            newArray[i]=WrapperArray[i].intValue();
        }
        return newArray;
    }
}