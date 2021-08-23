package com.example.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class mainscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);

        Button sevenday = (Button)findViewById(R.id.button10);
        ImageButton target = (ImageButton)findViewById(R.id.button9);
        ImageButton diary = (ImageButton)findViewById(R.id.button7);
        ImageButton menu = (ImageButton)findViewById(R.id.button8);

        Bundle dData = this.getIntent().getExtras();
        String name = dData.getString("username");
        String pass = dData.getString("password");
        String h = dData.getString("height");
        String w = dData.getString("weight");
        String t = dData.getString("exer");

        Bundle tData = this.getIntent().getExtras();
        String name_record = tData.getString("username");

        TextView show = (TextView)findViewById(R.id.usershow);
        show.setText("Hi user " + name);

        sevenday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newAct = new Intent();
                newAct.setClass(mainscreen.this, sevenday.class);
                Bundle cData = new Bundle();
                cData.putString("username", name_record);

                newAct.putExtras(cData);
                startActivity(newAct);
            }
        });

        target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mainscreen.this, TargetPage.class);
                startActivity(intent);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mainscreen.this, MenuScreen.class);
                startActivity(intent);
            }
        });

        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newAct = new Intent();
                newAct.setClass(mainscreen.this, DiaryScreen.class);
                Bundle cData = new Bundle();
                cData.putString("username", name);
                cData.putString("password", pass);
                cData.putString("height",h);
                cData.putString("weight",w);
                cData.putString("exer",t);

                newAct.putExtras(cData);
                startActivity(newAct);
            }
        });
    }
}