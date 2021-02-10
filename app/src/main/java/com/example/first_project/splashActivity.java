package com.example.first_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class splashActivity extends AppCompatActivity {

    //전체화면 만들기
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        //서버 연동
        // 서버 연동이 되면 데이터 받기
        // 데이터 받으면 메인 화면으로 넘기기
        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent (splashActivity.this, Start_Screen.class);
                startActivity(mainIntent);
                finish();
            }
        },2000);


    }}