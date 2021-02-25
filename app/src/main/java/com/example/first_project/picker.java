package com.example.first_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.first_project.R;


public class picker extends AppCompatActivity {

    SharedPreferences sf;
    SharedPreferences.Editor editor;

    DatePicker datepicker;
    Button pickerBtn;
    public static String yy,mm,dd;
    public static boolean check=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sf = getSharedPreferences("sFile",MODE_PRIVATE);
        editor = sf.edit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datepicker);

        pickerBtn = findViewById(R.id.pickerBtn);
        datepicker = findViewById(R.id.datepicker);

        datepicker.init(datepicker.getYear(), datepicker.getMonth(), datepicker.getDayOfMonth(),
                (view, year, monthOfYear, dayOfMonth) -> {
                    yy=Integer.toString(year);
                    mm=Integer.toString(monthOfYear-1);
                    dd=Integer.toString(dayOfMonth);
                    check=true;
                });

        pickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("pickerBtn/yy mm dd", yy+"년도"+mm+"월"+dd+"일");
                editor.putString("memoTitle",yy+"년도"+mm+"월"+dd+"일");
                editor.commit();
                Log.d("pickerBtn/editor",sf.getString("memoTitle",""));
                Intent intent = new Intent(getApplication(), ListActivity.class);
                startActivityForResult(intent,1);
                finish();
            }
        });

    }

}