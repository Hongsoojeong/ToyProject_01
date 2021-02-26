package com.example.first_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {
    TextView button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (TextView) findViewById(R.id.go_to_list);
        button.setOnClickListener(view->{
            Intent intent;
            intent=new Intent(getApplicationContext(), ItemListActivity.class);
            startActivity(intent);
        });
    }
}