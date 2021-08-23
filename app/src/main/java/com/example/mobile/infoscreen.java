package com.example.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class infoscreen extends AppCompatActivity {
    EditText h;
    EditText w;
    EditText t;
    DBHelper MyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infoscreen);

        h = (EditText)findViewById(R.id.et1);                           // 取得身高物件
        w = (EditText)findViewById(R.id.et2);
        t = (EditText)findViewById(R.id.et3);
        Button submit = (Button)findViewById(R.id.button2);
        Button nextpage = (Button)findViewById(R.id.button3);
        MyDB = new DBHelper(this);

        Bundle bData = this.getIntent().getExtras();
        String name = bData.getString("username");
        String pass = bData.getString("password");

        Log.d("name", name);
        Log.d("pwd", pass);

        nextpage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent newAct = new Intent();
                newAct.setClass(infoscreen.this, mainscreen.class);
                Bundle cData = new Bundle();
                cData.putString("username", name);
                cData.putString("password", pass);
                cData.putString("height",h.getEditableText().toString());
                cData.putString("weight",w.getEditableText().toString());
                cData.putString("exer",t.getEditableText().toString());

                newAct.putExtras(cData);
                startActivity(newAct);
            }
        });
        submit.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View arg0) {
                if (!("".equals(h.getText().toString())
                        || "".equals(w.getText().toString())
                        ||"".equals(t.getText().toString()))) {
                    float fh = Float.parseFloat(h.getEditableText().toString());      // 取得 身高輸入值
                    float fw = Float.parseFloat(w.getEditableText().toString());
                    float ft = Float.parseFloat(t.getEditableText().toString()); // 取得
                    double fresult;
                    Integer it = Integer.parseInt(t.getEditableText().toString());

                    TextView result = (TextView)findViewById(R.id.tv4);
                    fh = fh/100;
                    fh = fh*fh;

                    NumberFormat nf = NumberFormat.getInstance();   // 數字格式
                    nf.setMaximumFractionDigits(2);                 // 限制小數第二位
                    fresult = fw/fh;                                // 計算BMI
                    fresult =fresult*fw;

                    if(ft<1)
                        fresult = fresult*1.2;
                    else if(ft>=1&&ft<=3)
                        fresult = fresult*1.375;
                    else if(ft>3&&ft<=5)
                        fresult = fresult*1.55;
                    else if(ft>5&&ft<=7)
                        fresult = fresult*1.725;

                    result.setText("每日應攝取"+nf.format(fresult) +"大卡");           // 顯示BMI計算結果
                    TextView dia = (TextView)findViewById(R.id.tv4);// 取得 顯示診斷 物件

                }
            }
        });
    }
}