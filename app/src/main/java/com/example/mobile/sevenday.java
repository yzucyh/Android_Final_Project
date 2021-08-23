package com.example.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class sevenday extends AppCompatActivity {
    private TextView tvhello;
    String name;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sevenday);
        Button back =(Button)findViewById(R.id.button11);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sevenday.this,mainscreen.class));
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,
                        R.array.week_array,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0, false);
        spinner.setOnItemSelectedListener(spnOnItemSelected);
        tvhello= (TextView) findViewById(R.id.hello);
        Bundle dData = this.getIntent().getExtras();
        name = dData.getString("username");
        Log.d("seven", name);

        //tvhello.setText("選項:"+spinner.getSelectedItem().toString());
        DB = new DBHelper(this);
    }

    private AdapterView.OnItemSelectedListener spnOnItemSelected
            = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String sPos=String.valueOf(position);
            String sInfo=parent.getItemAtPosition(position).toString();
            switch (sInfo){
                case "星期一":
                    //Cursor c = DB.rawQuery("SELECT * FROM users WHERE username = '"+name+"'",null);
                    Cursor c = DB.getReadableDatabase().rawQuery("SELECT * FROM users WHERE username = '"+name+"'",null);
                    if(c.moveToNext()){
                        Log.d("cursor5", c.getString(5));
                        tvhello.setText("早餐: "+c.getString(5));
                    }
                    break;
                case "星期二":
                    Cursor c1 = DB.getReadableDatabase().rawQuery("SELECT * FROM users WHERE username = '"+name+"'",null);
                    if(c1.moveToNext()){
                        Log.d("cursor5", c1.getString(5));
                        tvhello.setText("早餐: " + c1.getString(5));
                    }
                    break;
                case "星期三":
                    Cursor c2 = DB.getReadableDatabase().rawQuery("SELECT * FROM users WHERE username = '"+name+"'",null);
                    if(c2.moveToNext()){
                        Log.d("cursor5", c2.getString(5));
                        tvhello.setText("早餐: " + c2.getString(5));
                    }
                    break;
                case "星期四":
                    Cursor c3 = DB.getReadableDatabase().rawQuery("SELECT * FROM users WHERE username = '"+name+"'",null);
                    if(c3.moveToNext()){
                        Log.d("cursor5", c3.getString(5));
                        tvhello.setText("早餐: " + c3.getString(5));
                    }
                    break;
                case "星期五":
                    tvhello.setText("FRI");
                    break;
                case "星期六":
                    tvhello.setText("SAT");
                    break;
            }
            //String sInfo=parent.getSelectedItem().toString();
            //tvhello.setText("選項:"+sInfo);
        }

        public void onNothingSelected(AdapterView<?> parent) {
            //
        }
    };
}