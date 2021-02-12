package com.example.first_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;
import java.util.Map;


public class ListActivity extends AppCompatActivity {
    ImageView image;
    TextView save;

    EditText editTitle;
    EditText editContent;


    SharedPreferences sf; // 앱 내 데이터를 저장할 객체
    SharedPreferences.Editor editor; // 앱 내 데이터를 수정할 객체

    int REQUEST_IMAGE_CODE=1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        sf=getSharedPreferences("sFile", MODE_PRIVATE);
        editor= sf.edit();

        editTitle = findViewById(R.id.dateAndTime);
        editContent = findViewById(R.id.content);

        String title= sf.getString("memoTitle","");
        String content = sf.getString("memoContent", "");

        editTitle.setText(title);
        editContent.setText(content);


        save=(TextView) findViewById(R.id.save);
        image=(ImageView) findViewById(R.id.upload_image);


        save.setOnClickListener(view->{
            Toast.makeText(getApplicationContext(), "Save is Completed",Toast.LENGTH_LONG).show();

            String titleTrim = editTitle.getText().toString().trim();
            String contentTrim = editContent.getText().toString().trim();

            if (!titleTrim.equals("") && !contentTrim.equals("")) {
                memo(titleTrim, contentTrim);
            }
            else {
                Toast.makeText(getApplicationContext(), "Please Enter All value", Toast.LENGTH_LONG).show();
            }


        });

        image.setOnClickListener(view->{
           openGallery();
        });
    }
public void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intent, 101);
}
@Override
protected void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==101){
            if (resultCode==RESULT_OK){
                Uri fileUri = data.getData();
                ContentResolver resolver = getContentResolver();

                try{
                    InputStream instream =resolver.openInputStream(fileUri);
                    Bitmap imgBitmap = BitmapFactory.decodeStream(instream);
                    image.setImageBitmap(imgBitmap);
                    instream.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
}

    private void memo (String title, String content){

        editor.putString("memoTitle", title).commit();
        editor.putString("memoContent", content).commit();

    }
}