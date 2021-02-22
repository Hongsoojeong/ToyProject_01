package com.example.first_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;
import java.text.BreakIterator;
import java.util.Calendar;
import java.util.Map;


public class ListActivity extends AppCompatActivity {

    public static boolean check = false;
    int bf_key = 0;
    int lc_key = 0;
    int dn_key = 0;

    TextView ymBtn;
    String year, month, day;

    ImageView image;
    TextView save;
    TextView Back;
    EditText editContent;

    Switch Breakfast;
    Switch Lunch;
    Switch Dinner;

    SharedPreferences sf; // 앱 내 데이터를 저장할 객체
    SharedPreferences.Editor editor; // 앱 내 데이터를 수정할 객체

    int REQUEST_IMAGE_CODE = 1001;

    @SuppressLint("WrongViewCast")
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        sf = getSharedPreferences("sFile", MODE_PRIVATE);
        editor = sf.edit();

        Breakfast = (Switch) findViewById(R.id.Breakfast);
        Lunch = (Switch) findViewById(R.id.Lunch);
        Dinner = (Switch) findViewById(R.id.Dinner);
        Back = findViewById(R.id.back);
        editContent = findViewById(R.id.content);
        ymBtn = findViewById(R.id.ymBtn);



        if(picker.check){
            Intent pickerData = getIntent(); /*데이터 수신*/
            if (getIntent() != null) {
                if (getIntent().getExtras() != null) {
                    year = pickerData.getExtras().getString("yy");
                    month = pickerData.getExtras().getString("mm");
                    day = pickerData.getExtras().getString("dd");
                    ymBtn.setText(year + "년 " + month + "월 " + day + "일");
                } else {
                    Toast.makeText(getApplicationContext(), "날짜를 다시한번 선택해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        }






        String title = sf.getString("memoTitle", "");
        String content = sf.getString("memoContent", "");


        editContent.setText(content);

        save = (TextView) findViewById(R.id.save);
        image = (ImageView) findViewById(R.id.upload_image);




        save.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Save is Completed", Toast.LENGTH_SHORT).show();
            String contentTrim = editContent.getText().toString().trim();
            if (Breakfast.isChecked()) {

                bf_key = 1;
            }

            if (Lunch.isChecked()) {
                lc_key = 1;
            }

            if (Dinner.isChecked()) {
                dn_key = 1;
            }

            if (!contentTrim.equals("")) {
                Toast.makeText(getApplicationContext(), "Please Enter All value", Toast.LENGTH_SHORT).show();
            }

        });



        image.setOnClickListener(view -> {
            openGallery();
        });




        Back.setOnClickListener(view -> {
            Intent intent;
            intent = new Intent(getApplicationContext(), Start_list.class);
            startActivity(intent);
        });


    }


    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intent, 101);
    }


    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                Uri fileUri = data.getData();
                ContentResolver resolver = getContentResolver();

                try {
                    InputStream instream = resolver.openInputStream(fileUri);
                    Bitmap imgBitmap = BitmapFactory.decodeStream(instream);
                    image.setImageBitmap(imgBitmap);
                    instream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }


    private void memo(String title, String content) {
        editor.putString("memoTitle", title).commit();
        editor.putString("memoContent", content).commit();
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.ymBtn:
                Intent picker = new Intent(getApplicationContext(),picker.class);
                startActivity(picker);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    }

