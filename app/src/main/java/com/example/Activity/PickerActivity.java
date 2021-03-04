package com.example.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class PickerActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    DatePicker datePicker;
    Button pickerBtn;
    public static String yy,mm,dd;
    public static boolean check=false;
    @Override


    protected void onCreate(Bundle savedInstanceState) {

        sharedPreferences = getSharedPreferences("sFile",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepicker);

        pickerBtn = findViewById(R.id.pickerBtn);
        datePicker = findViewById(R.id.datePicker);

        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                (view, year, monthOfYear, dayOfMonth) -> {
                    yy=Integer.toString(year);
                    mm=Integer.toString(monthOfYear-1);
                    dd=Integer.toString(dayOfMonth);
                    check=true;
                });

        pickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.putExtra("memoTitle",yy+"년도"+mm+"월"+dd+"일");

                setResult(RESULT_OK, intent);
                finish();

            }
        });

    }

}