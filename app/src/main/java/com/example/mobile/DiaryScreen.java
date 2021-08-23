package com.example.mobile;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

public class DiaryScreen extends AppCompatActivity {
    public static final String TAG = DiaryScreen.class.getSimpleName()+"My";
    private String mPath = "";//設置高畫質的照片位址
    public static final int CAMERA_PERMISSION = 100;//檢測相機權限用
    public static final int REQUEST_HIGH_IMAGE = 101;//檢測高畫質相機回傳
    public static final int REQUEST_LOW_IMAGE = 102;//檢測低畫質相機回傳
    DBHelper DB;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_screen);

        ImageButton btHigh = (ImageButton) findViewById(R.id.buttonHigh);
        ImageButton target_diary = (ImageButton) findViewById(R.id.target_diary);
        ImageButton diary_diary = (ImageButton) findViewById(R.id.diary_diary);
        ImageButton menu_diary = (ImageButton) findViewById(R.id.menu_diary);

        Button br = (Button)findViewById(R.id.Breakfast);
        Button lu = (Button)findViewById(R.id.Lunch);
        Button di = (Button)findViewById(R.id.Dinner);

        EditText foodname = (EditText)findViewById(R.id.foodname_diary);
        EditText kcal = (EditText)findViewById(R.id.kcal_diary);
        EditText protein = (EditText)findViewById(R.id.protein_diary);

        Button store = (Button)findViewById(R.id.store);
        DB = new DBHelper(this);

        Bundle dData = this.getIntent().getExtras();
        String name = dData.getString("username");
        String pass = dData.getString("password");
        String h = dData.getString("height");
        String w = dData.getString("weight");
        String t = dData.getString("exer");

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION);
        btHigh.setOnClickListener(v -> {
            Intent highIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //檢查是否已取得權限
            if (highIntent.resolveActivity(getPackageManager()) == null) return;
            //取得相片檔案的URI位址及設定檔案名稱
            File imageFile = getImageFile();
            if (imageFile == null) return;
            //取得相片檔案的URI位址
            Uri imageUri = FileProvider.getUriForFile(
                    this,
                    "com.jetec.cameraexample.CameraEx",//記得要跟AndroidManifest.xml中的authorities 一致
                    imageFile
            );
            highIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(highIntent, REQUEST_HIGH_IMAGE);//開啟相機
        });

        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodname.setText("");
                kcal.setText("");
                protein.setText("");
            }
        });

        lu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodname.setText("");
                kcal.setText("");
                protein.setText("");
            }
        });

        di.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodname.setText("");
                kcal.setText("");
                protein.setText("");
            }
        });
        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String nameTXT = foodname.getText().toString();
                DB.insertData(name, pass, h, w, t, foodname.getText().toString());
                DB.close();

                Intent newAct = new Intent();
                newAct.setClass(DiaryScreen.this, mainscreen.class);
                Bundle tData = new Bundle();
                tData.putString("username", name);
                newAct.putExtras(tData);
                startActivity(newAct);
            }
        });
        target_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DiaryScreen.this, TargetPage.class);
                startActivity(intent);
            }
        });
        diary_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DiaryScreen.this, DiaryScreen.class);
                startActivity(intent);
            }
        });
        menu_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DiaryScreen.this, MenuScreen.class);
                startActivity(intent);
            }
        });

    }

    private File getImageFile() {
        String time = new SimpleDateFormat("yyMMdd").format(new Date());
        String fileName = time+"_";
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try {
            //給予檔案命名及檔案格式
            File imageFile = File.createTempFile(fileName,".jpg",dir);
            //給予全域變數中的照片檔案位置，方便後面取得
            mPath = imageFile.getAbsolutePath();
            return imageFile;
        } catch (IOException e) {
            return null;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**可在此檢視回傳為哪個相片，requestCode為上述自定義，resultCode為-1就是有拍照，0則是使用者沒拍照*/
        Log.d(TAG, "onActivityResult: requestCode: " + requestCode + ", resultCode " + resultCode);
        /**如果是高畫質的相片回傳*/
        if (requestCode == REQUEST_HIGH_IMAGE && resultCode == -1) {
            ImageView imageHigh = findViewById(R.id.Photo);
            new Thread(() -> {
                //在BitmapFactory中以檔案URI路徑取得相片檔案，並處理為AtomicReference<Bitmap>，方便後續旋轉圖片
                AtomicReference<Bitmap> getHighImage = new AtomicReference<>(BitmapFactory.decodeFile(mPath));
                Matrix matrix = new Matrix();
                matrix.setRotate(0f);//轉90度
                getHighImage.set(Bitmap.createBitmap(getHighImage.get()
                        , 0, 0
                        , getHighImage.get().getWidth()
                        , getHighImage.get().getHeight()
                        , matrix, true));
                runOnUiThread(() -> {
                    //以Glide設置圖片(因為旋轉圖片屬於耗時處理，故會LAG一下，且必須使用Thread執行緒)
                    Glide.with(this)
                            .load(getHighImage.get())
                            .centerCrop()
                            .into(imageHigh);
                });
            }).start();
        }
    }
}