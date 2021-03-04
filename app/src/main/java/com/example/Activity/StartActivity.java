package com.example.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    TextView recyclerViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewBtn = (TextView) findViewById(R.id.go_to_recyclerView);
        recyclerViewBtn.setOnClickListener(view->{
            Intent intent;
            intent=new Intent(getApplicationContext(), RecyclerViewActivity.class);
            startActivity(intent);
        });
    }
}