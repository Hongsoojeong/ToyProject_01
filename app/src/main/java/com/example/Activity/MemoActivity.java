package com.example.Activity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Data.ItemData;
import com.example.Util.PreUtil;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;


public class MemoActivity extends AppCompatActivity {

    public static boolean check = false;

    TextView selectDate;
    // 날씨 입력하는 textView 를 클릭하면 datePicker 액티비티로 넘어가므로
    ImageView image;
    TextView saveBtn;
    TextView backBtn;
    EditText content;
    PreUtil preUtil;
    Switch breakfast_switch;
    Switch lunch_switch;
    Switch dinner_switch;

    SharedPreferences sharedPreferences; // 앱 내 데이터를 저장할 객체
    SharedPreferences.Editor editor; // 앱 내 데이터를 수정할 객체

    int REQUEST_IMAGE_CODE = 1001;

    @SuppressLint("WrongViewCast")
    @Override

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        preUtil = new PreUtil(this);

        sharedPreferences = getSharedPreferences("sFile",MODE_PRIVATE);
        editor = sharedPreferences.edit();


        breakfast_switch = (Switch) findViewById(R.id.breakfast_switch);
        lunch_switch = (Switch) findViewById(R.id.lunch_switch);
        dinner_switch = (Switch) findViewById(R.id.dinner_switch);



        backBtn = findViewById(R.id.back);
        content = findViewById(R.id.content);
        selectDate = findViewById(R.id.go_to_datePicker);


        saveBtn = (TextView) findViewById(R.id.save);
        image = (ImageView) findViewById(R.id.upload_image);



        boolean bfSwitch = sharedPreferences.getBoolean("isBreakfastOn",false);
        boolean lcSwitch = sharedPreferences.getBoolean("isLunchOn",false);
        boolean dnSwitch = sharedPreferences.getBoolean("isDinnerOn",false);


        breakfast_switch.setChecked(bfSwitch);
        lunch_switch.setChecked(lcSwitch);
        dinner_switch.setChecked(dnSwitch);



        breakfast_switch.setOnCheckedChangeListener((view,b)->{
                    if (b){
                        lunch_switch.setChecked(false);
                        dinner_switch.setChecked(false);
                    }
                }
        );
        lunch_switch.setOnCheckedChangeListener((view,b)->{
                    if (b){
                        breakfast_switch.setChecked(false);
                        dinner_switch.setChecked(false);
                    }
                }
        );

        dinner_switch.setOnCheckedChangeListener((view, b)->{
                    if (b){
                        breakfast_switch.setChecked(false);
                        lunch_switch.setChecked(false);
                    }
                }
        );



        saveBtn.setOnClickListener(view -> {

            Toast.makeText(getApplicationContext(), "Save is Completed", Toast.LENGTH_SHORT).show();
            String contentTrim = this.content.getText().toString().trim();
            String titleTrim= selectDate.getText().toString().trim();
            Log.d("save.setonClickListener", selectDate.getText().toString().trim());

            if (!titleTrim.equals("")&&!contentTrim.equals("")) {
                memo(titleTrim, contentTrim);
                finish();
            }
            else{
                Toast.makeText(getApplicationContext(), "Please Enter All value", Toast.LENGTH_SHORT).show();
            }


        });


        image.setOnClickListener(view -> {
            openGallery();
        });

        backBtn.setOnClickListener(view -> {
            finish();
        });

        getData();

    }


    private void getData() {
        Intent diaryIntent = getIntent();
        selectDate.setText(diaryIntent.getStringExtra("date"));
        content.setText(diaryIntent.getStringExtra("content"));
        if (diaryIntent.getStringExtra("image")!="1") {
            image.setImageBitmap(StringToBitmap(diaryIntent.getStringExtra("image")));
        }
        if (diaryIntent.getStringExtra("when")=="1")
        {
            this.breakfast_switch.setChecked(true);
        }
        else if (diaryIntent.getStringExtra("when")=="2")
        {
            this.lunch_switch.setChecked(true);
        }
        else if (diaryIntent.getStringExtra("when")=="3")
        {
            this.dinner_switch.setChecked(true);
        }
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
                    imgBitmap = imgBitmap.createScaledBitmap(imgBitmap,200,200,true);
                    image.setImageBitmap(imgBitmap);
                    String bitmap=BitMapToString(imgBitmap); // string으로 변환했어 그다음에 어떻게 저장하느나...!
                    editor.putString("image",bitmap);
                    instream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                selectDate.setText(data.getStringExtra("memoTitle"));
            }
        }

    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;

    }

    public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
    private void memo(String title, String content) {
        //날짜 저장
        Intent intent = new Intent();
        intent.putExtra("date", title);
        intent.putExtra("content",content);
        Log.d("content",intent.getStringExtra("content"));
        //스위치의 상태 저장
        editor.putBoolean("isBreakfastOn",breakfast_switch.isChecked()).commit();
        editor.putBoolean("isLunchOn",lunch_switch.isChecked()).commit();
        editor.putBoolean("isDinnerOn", dinner_switch.isChecked()).commit();
        if (breakfast_switch.isChecked()==true)
        {
            intent.putExtra("when","1");
            if (sharedPreferences.getString("image","")!=""){
                String image=sharedPreferences.getString("image","");
                preUtil.setDiaryPref(new ItemData("Record",title, "breakfast", content,image,"1"));
            }
            else
            {
                preUtil.setDiaryPref(new ItemData("Record",title, "breakfast", content,"1","1"));
            }
        }
        else if (lunch_switch.isChecked()==true)
        {
            intent.putExtra("when","2");
            if (sharedPreferences.getString("image","")!=""){
                String image=sharedPreferences.getString("image","");
                preUtil.setDiaryPref(new ItemData("Record",title, "lunch", content,image,"2"));
            }
            else{
                preUtil.setDiaryPref(new ItemData("Record",title, "lunch",content,"1","2"));
            }

        }
        else if (dinner_switch.isChecked()==true){
            intent.putExtra("when","3");
            if (sharedPreferences.getString("image","")!=""){
                String image=sharedPreferences.getString("image","");
                preUtil.setDiaryPref(new ItemData("Record",title, "dinner", content,image,"3"));
            }
            else {
                preUtil.setDiaryPref(new ItemData("Record", title, "dinner", content, "1", "3"));
            }
        }

    }



    public void onClick(View v){
        switch(v.getId()){
            case R.id.go_to_datePicker:
                Intent picker = new Intent(this, PickerActivity.class);
                startActivityForResult(picker, 1);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }


}
