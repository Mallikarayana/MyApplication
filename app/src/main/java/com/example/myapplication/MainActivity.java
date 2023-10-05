package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mobileEd, passwordEd;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mobileEd=findViewById(R.id.editt1);
        passwordEd=findViewById(R.id.editt2);
        loginBtn=findViewById(R.id.login);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile=mobileEd.getText().toString();
                String password=passwordEd.getText().toString();

                if(!mobile.isEmpty()) {
                    //continue
                    if (!password.isEmpty()) {

                        if(mobileEd.length()==10) {
                            if(password.equals("reshma@123")) {
                                //success

                                //Toast.makeText(MainActivity.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                                //intent
                                Intent View=new Intent(MainActivity.this,HomeActivity.class);
                                startActivity(View);
                                finish();

                            }else{
                                passwordEd.setError("invalid password");
                            }
                        }else{
                            mobileEd.setError("Enter 10 Digits");
                            Toast.makeText(MainActivity.this,"Enter Valid Mobile Number",Toast.LENGTH_SHORT).show();
                        }
                        //continue
                        Toast.makeText(MainActivity.this,"Mobile:"+mobile+"\n Password:"+password,Toast.LENGTH_SHORT).show();
                    } else {
                        //empty
                        Toast.makeText(MainActivity.this, "Password is required", Toast.LENGTH_SHORT).show();
                        passwordEd.setError("Password is Required");
                    }
                }else{
                    //empty
                    Toast.makeText(MainActivity.this,"Mobile is Required",Toast.LENGTH_SHORT).show();
                    mobileEd.setError("Mobile is Required");
                }
            }
        });

    }
    public void openAdmin(View view) {
        Intent intent=new Intent(MainActivity.this,AdminActivity.class);
        startActivity(intent);
    }
}