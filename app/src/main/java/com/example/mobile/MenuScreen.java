package com.example.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MenuScreen extends AppCompatActivity {
    private String[] menuArray;
    private String[] preword = {"高脂肪、適量蛋白、低碳水" ,"高蛋白、飲食以原型食物為主", "碳水化合物比例最低", "媽媽的味道", "維持一天基本所需"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
        Button accept = (Button)findViewById(R.id.accept);
        ImageButton targetPage = (ImageButton)findViewById(R.id.target_menu);
        ImageButton diary_menu = (ImageButton)findViewById(R.id.diary_menu);
        ImageButton menu_menu = (ImageButton)findViewById(R.id.menu_menu);

        Spinner menulist = (Spinner)findViewById(R.id.spinner);
        TextView name = (TextView)findViewById(R.id.Ingre1);
        TextView pre = (TextView)findViewById(R.id.preword);
        Button next = (Button)findViewById(R.id.change);
        ImageView photo = (ImageView)findViewById(R.id.photo);


        SpannableString content = new SpannableString("Content");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);

        menuArray = getResources().getStringArray(R.array.menu);
        ArrayAdapter<String>adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, menuArray);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        menulist.setAdapter(adapter);
        int menuid = menulist.getSelectedItemPosition();

        menulist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                pre.setText(preword[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pre.setText("");
            }
        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int menuid = menulist.getSelectedItemPosition();
                /*TextView step1 = (TextView)findViewById(R.id.step1);
                TextView step2 = (TextView)findViewById(R.id.step2);
                TextView step3 = (TextView)findViewById(R.id.step3);
                TextView step4 = (TextView)findViewById(R.id.step4);*/
                TextView gre1 = (TextView)findViewById(R.id.gre1);
                TextView gre2 = (TextView)findViewById(R.id.gre2);
                TextView gre3 = (TextView)findViewById(R.id.gre3);
                TextView gre4 = (TextView)findViewById(R.id.gre4);

                switch(menuid){
                    case 0:
                        name.setText("酪梨鮮蝦沙拉");
                        gre1.setText("白蝦200g");
                        gre2.setText("酪梨1顆");
                        gre3.setText("玉米筍適量");
                        gre4.setText("羅曼,海鹽");
                        /*step1.setText("1.玉米筍過熱水燙熟，瀝乾後切成細塊備用");
                        step2.setText("2.白蝦燙熟,起鍋瀝乾備用");
                        step3.setText("3.蘋果、酪梨切成細塊");
                        step4.setText("4.以蘿蔓做底,灑上玉米筍、白蝦,再灑上蘋果、酪梨、些許海鹽、黑胡椒粒");*/
                        photo.setImageResource(R.drawable.shrimp_salad);
                        break;
                    case 1:
                        name.setText("萊姆香料烤鮭魚");
                        gre1.setText("鮭魚1片");
                        gre2.setText("大蒜4個切末");
                        gre3.setText("酪梨油15ml");
                        gre4.setText("巴西里少許切碎");
                        /*step1.setText("1.將烤箱預熱至200度");
                        step2.setText("2.白蝦燙熟,起鍋瀝乾備用鮭魚洗淨，切大塊用紙巾擦乾放在烘焙紙上。");
                        step3.setText("3.將大蒜、巴西里葉撒在鮭魚上，淋上酪梨油及檸檬");
                        step4.setText("4.進烤箱烘烤15-20分鐘或直到熟透即完成");*/
                        photo.setImageResource(R.drawable.fish);
                        break;
                    case 2:
                        name.setText("番茄蔬菜歐姆蛋");
                        gre1.setText("蛋3顆、鹽/黑胡椒 少許");
                        gre2.setText("小番茄5顆");
                        gre3.setText("羽衣甘藍1把");
                        gre4.setText("有機初榨椰子油10ml");
                        /*step1.setText("1.將羽衣甘藍、小番茄切碎");
                        step2.setText("2.將蛋加入切碎的羽衣甘藍、小番茄、椰子油、鹽、黑胡椒拌勻");
                        step3.setText("3.不沾鍋倒入椰子油用紙巾塗抹均勻,熱鍋10秒");
                        step4.setText("4.倒入蛋液,攪拌至底部熟透");*/
                        photo.setImageResource(R.drawable.omlet);
                        break;
                    case 3:
                        name.setText("滷肉飯");
                        gre1.setText("豬絞肉300g");
                        gre2.setText("油蔥酥10g");
                        gre3.setText("醬油150g");
                        gre4.setText("糖20g");
                        /*step1.setText("1.將羽衣甘藍、小番茄切碎");
                        step2.setText("2.將蛋加入切碎的羽衣甘藍、小番茄、椰子油、鹽、黑胡椒拌勻");
                        step3.setText("3.不沾鍋倒入椰子油用紙巾塗抹均勻,熱鍋10秒");
                        step4.setText("4.倒入蛋液,攪拌至底部熟透");*/
                        photo.setImageResource(R.drawable.rice);
                        break;
                    case 4:
                        name.setText("番茄蔬菜歐姆蛋3");
                        /*step1.setText("1.將羽衣甘藍、小番茄切碎");
                        step2.setText("2.將蛋加入切碎的羽衣甘藍、小番茄、椰子油、鹽、黑胡椒拌勻");
                        step3.setText("3.不沾鍋倒入椰子油用紙巾塗抹均勻,熱鍋10秒");
                        step4.setText("4.倒入蛋液,攪拌至底部熟透");*/
                }
                next.setOnClickListener(new View.OnClickListener() {
                    int c = 0;
                    @Override
                    public void onClick(View v) {
                        if(c%2==0) {
                            name.setText("輕食便當");
                            gre1.setText("雞胸肉100g");
                            gre2.setText("蘆筍5根");
                            gre3.setText("花椰菜1人分");
                            gre4.setText("薑黃30g");
                            photo.setImageResource(R.drawable.shantong);
                        }
                        else {
                            name.setText("巴沙魚佐青醬");

                            gre1.setText("巴沙魚200g");
                            gre2.setText("蘆筍5根");
                            gre3.setText("鹽巴5g");
                            gre4.setText("羅勒葉15g");
                            photo.setImageResource(R.drawable.basa);
                        }
                        c++;
                    }
                });
            }
        });

        targetPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MenuScreen.this, TargetPage.class);
                startActivity(intent);
            }
        });
        diary_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MenuScreen.this, DiaryScreen.class);
                startActivity(intent);
            }
        });
        menu_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MenuScreen.this, MenuScreen.class);
                startActivity(intent);
            }
        });
    }
}