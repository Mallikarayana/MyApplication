package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {
    Button CatAddBtn, CatUpdatebtn,  subcataddbtn, subcatupdatebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        CatAddBtn=findViewById(R.id.catadd);
        CatUpdatebtn=findViewById(R.id.catupdate);
        subcataddbtn=findViewById(R.id.subcatadd);
        subcatupdatebtn=findViewById(R.id.subcatupdate);



        CatAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCategoryOperation();
            }
        });
        CatUpdatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCategoryOperation();
            }
        });

        subcataddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSubCategoryOperation();
            }
        });
        subcatupdatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSubCategoryOperation();
            }
        });

    }

    private void openSubCategoryOperation() {
        Intent intent=new Intent(AdminActivity.this,CrudActivity.class);
        intent.putExtra("purpose","subcategory");
        startActivity(intent);

    }

    private void openCategoryOperation() {
        Intent intent=new Intent(AdminActivity.this,CrudActivity.class);
        intent.putExtra("purpose","category");
        startActivity(intent);
    }
}