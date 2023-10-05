package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.BeautyZoneSqlite;

import java.io.File;
import java.util.ArrayList;

public class CrudActivity extends AppCompatActivity {

    TextView purposetv;

    EditText idEd, nameEd, statusEd;
    Button addbtn, deletebtn, updatebtn, readbtn, uploadbtn;
    ImageView previewimageview;


    String Pathaddress="";

    String[] brands_names={};
    BeautyZoneSqlite beautyZoneSqlite;

    Spinner categoryspinner, subcatspinner;

    private final static int pic_id = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);
        purposetv = findViewById(R.id.Purpose_Tv);
        beautyZoneSqlite=new BeautyZoneSqlite(this);
        categoryspinner=findViewById(R.id.category_spinner);
        subcatspinner=findViewById(R.id.subcategory_spinner);

        idEd = findViewById(R.id.pid_edit1);
        nameEd = findViewById(R.id.name_edit2);
        statusEd = findViewById(R.id.status_edit3);

        addbtn = findViewById(R.id.add_btn);
        updatebtn = findViewById(R.id.update_btn);
        readbtn = findViewById(R.id.read_btn);
        uploadbtn = findViewById(R.id.upload_btn);
        previewimageview = findViewById(R.id.preview_imageview);

        String purpose = getIntent().getStringExtra("purpose");
        purposetv.setText(purpose);

        if (purpose.equals("subcategory")){
            categoryspinner.setVisibility(View.VISIBLE);
            idEd.setEnabled(false);
        }
        String condition=null;
        ArrayList<String> arrayList=new ArrayList<String>();
        ArrayList<String>categoryIDList=new ArrayList<String>();
        Cursor results=beautyZoneSqlite.listData("category",condition);
        if (results.getCount()==0){
            arrayList.add("No Data");

        }

        while (results.moveToNext()){
            arrayList.add(results.getString(1));
            categoryIDList.add(results.getString(0));
        }

        //new string array
        brands_names=new String[arrayList.size()];
        for (int i=0;i<arrayList.size();i++){
            brands_names[i]=arrayList.get(i);
        }

        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,brands_names);
        categoryspinner.setAdapter(adapter);

        categoryspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int position=i;
                idEd.setText(categoryIDList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEd.getText().toString();
                Integer status = Integer.parseInt(statusEd.getText().toString());

                String id=idEd.getText().toString();
                boolean isInserted =  beautyZoneSqlite.insertData( purpose,id,name,Pathaddress ,status);
                if (isInserted) {
                    Toast.makeText(CrudActivity.this, "" + purpose + "Added Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(CrudActivity.this, CrudActivity.class);
                    intent.putExtra("purpose",purpose);
                    startActivity(intent);
                } else {
                    Toast.makeText(CrudActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //implicit intent
                Intent open_camera=new Intent();
                open_camera.setType("image/*");
                open_camera.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(open_camera, "Selected image"), pic_id);
            }
        });
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=nameEd.getText().toString();
                Integer status= Integer.parseInt(statusEd.getText().toString());
                String id=idEd.getText().toString();

                boolean isUpdated= beautyZoneSqlite.updateData(id, purpose, name, Pathaddress, status);
                if (isUpdated){
                    Toast.makeText( CrudActivity.this,"" + purpose + "Updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(CrudActivity.this,CrudActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(CrudActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=idEd.getText().toString();
                Integer isDeleted= beautyZoneSqlite.deleteData(id,purpose);
                if (isDeleted>0){
                    Toast.makeText(CrudActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CrudActivity.this,"Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==pic_id){
            Uri selectedimg=data.getData();
            previewimageview.setImageURI(selectedimg);
            //new
            Uri uri = data.getData();
            File file = new File(uri.getPath());//create path from uri
            final String[] split = file.getPath().split(":");// split the path
            String filepath = split[1];//assign it to a string(your choice)

            Log.i("path Image", filepath);
            Pathaddress = filepath;
        }
    }
}