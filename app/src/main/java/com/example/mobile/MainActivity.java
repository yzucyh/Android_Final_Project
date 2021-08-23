package com.example.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    Button buttonlogin;
    DBHelper MyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        buttonlogin = findViewById(R.id.btn_login);
        MyDB = new DBHelper(this);
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = username.getText().toString();
                String contactTXT = password.getText().toString();
                Toast.makeText(MainActivity.this,"登入成功", Toast.LENGTH_SHORT).show();

                Intent newAct = new Intent();
                newAct.setClass(MainActivity.this, infoscreen.class);
                Bundle bData = new Bundle();
                bData.putString("username", username.getText().toString());
                bData.putString("password", password.getText().toString());

                newAct.putExtras(bData);
                startActivity(newAct);

                //startActivity(new Intent(MainActivity.this,infoscreen.class));
            }
        });
    }
}