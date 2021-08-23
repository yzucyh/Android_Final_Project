package com.example.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class TargetPage extends AppCompatActivity {

    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_page);

        EditText weight = (EditText) findViewById(R.id.weight);
        EditText days = (EditText) findViewById(R.id.days);
        Button compute = (Button) findViewById(R.id.compute);
        ImageButton MenuPagebtn = (ImageButton)findViewById(R.id.menu_target);
        ImageButton Diarybtn = (ImageButton)findViewById(R.id.diary_target);

        TextView kcal = (TextView) findViewById(R.id.kcal); // textview
        TextView protein = (TextView) findViewById(R.id.protein); // textview
        TextView fat = (TextView) findViewById(R.id.fat); // textview
        TextView carbon = (TextView) findViewById(R.id.carbon); // textview
        DB = new DBHelper(this);

        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kcal.setText("1740kcal");
                protein.setText("70g");
                fat.setText("20g");
                carbon.setText("400g");
            }
        });

        MenuPagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TargetPage.this, MenuScreen.class);
                startActivity(intent);
            }
        });

        Diarybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TargetPage.this, DiaryScreen.class);
                startActivity(intent);
            }
        });
    }
}