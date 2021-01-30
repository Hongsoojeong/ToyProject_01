package com.example.first_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Start_Screen extends AppCompatActivity {
    TextView button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (TextView) findViewById(R.id.go_to_list);
        button.setOnClickListener(view->{
            Intent intent;
            intent=new Intent(getApplicationContext(),ListActivity.class);
            startActivity(intent);
        });
    }
}