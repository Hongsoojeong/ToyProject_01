package com.example.first_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
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

import Data.ItemData;

public class ListActivity extends AppCompatActivity {

    public static boolean check = false;
    int bf_key = 0;
    int lc_key = 0;
    int dn_key = 0;
    String yy,mm,dd;
    TextView ymBtn;
    String year, month, day;

    ImageView image;
    TextView save;
    TextView Back;
    EditText editContent;

    Switch Breakfast;
    Switch Lunch;
    Switch Dinner;

    SharedPreferences sf_2; // 앱 내 데이터를 저장할 객체
    SharedPreferences.Editor editor; // 앱 내 데이터를 수정할 객체

    int REQUEST_IMAGE_CODE = 1001;

    @SuppressLint("WrongViewCast")
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ItemData data= new ItemData("","","");

        sf_2 = getSharedPreferences("sFile",MODE_PRIVATE);
        editor = sf_2.edit();


        Breakfast = (Switch) findViewById(R.id.Breakfast);
        Lunch = (Switch) findViewById(R.id.Lunch);
        Dinner = (Switch) findViewById(R.id.Dinner);

        Back = findViewById(R.id.back);
        editContent = findViewById(R.id.content);
        ymBtn = findViewById(R.id.ymBtn);

        save = (TextView) findViewById(R.id.save);
        image = (ImageView) findViewById(R.id.upload_image);


        boolean bfSwitch = sf_2.getBoolean("isBreakfastOn",false);
        boolean lcSwitch = sf_2.getBoolean("isLunchOn",false);
        boolean dnSwitch = sf_2.getBoolean("isDinnerOn",false);


        Breakfast.setChecked(bfSwitch);
        Lunch.setChecked(lcSwitch);
        Dinner.setChecked(dnSwitch);


        String title = sf_2.getString("memoTitle", "");
        Log.d("LOGTAG/onCreate",title);

        String content = sf_2.getString("memoContent", "");
        Log.d("LOGTAG/onCreate",content);

        editContent.setText(content);
        ymBtn.setText(title);






//스위치 중복 상태를 방지하기 위해서 if문을 설정. setChecked를 이용해서 만약 얘가 setChecked 상태다.. 하면
        //다른 setChecked가 된 스위치들은 꺼놓도록 하는 것이다.
        Breakfast.setOnCheckedChangeListener((view,b)->{
                    if (b){
                        Lunch.setChecked(false);
                        Dinner.setChecked(false);
                    }
                }
        );
        Lunch.setOnCheckedChangeListener((view,b)->{
                    if (b){
                        Breakfast.setChecked(false);
                        Dinner.setChecked(false);
                    }
                }
        );

        Dinner.setOnCheckedChangeListener((view,b)->{
                    if (b){
                        Breakfast.setChecked(false);
                        Lunch.setChecked(false);
                    }
                }
        );




        save.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Save is Completed", Toast.LENGTH_SHORT).show();
            String contentTrim = editContent.getText().toString().trim();
            String titleTrim=ymBtn.getText().toString().trim();

            Log.d("save.setonClickListener",ymBtn.getText().toString().trim());


            if (!titleTrim.equals("")&&!contentTrim.equals("")) {
                memo(titleTrim, contentTrim);
            }
            else{
                Toast.makeText(getApplicationContext(), "Please Enter All value", Toast.LENGTH_SHORT).show();
            }
    });



        image.setOnClickListener(view -> {
            openGallery();
        });

        Back.setOnClickListener(view -> {
            finish();
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
        //날짜 저장
        editor.putString("memoTitle", title).commit();
        Log.d("LOGTAG/LISTACTIVITY",sf_2.getString("memoTitle",""));
        // 내용 저장
        editor.putString("memoContent", content).commit();
        Log.d("LOGTAG/LISTACTIVITY",sf_2.getString("memoContent",""));

        //스위치의 상태 저장
        editor.putBoolean("isBreakfastOn",Breakfast.isChecked()).commit();
        editor.putBoolean("isLunchOn",Lunch.isChecked()).commit();
        editor.putBoolean("isDinnerOn",Dinner.isChecked()).commit();

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


