package com.example.first_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

public class ListActivity extends AppCompatActivity {
    ImageView image;
    TextView save;
    ScrollView scrollview;
    int REQUEST_IMAGE_CODE=1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        save=(TextView) findViewById(R.id.save);
        image=(ImageView) findViewById(R.id.upload_image);
        scrollview=(ScrollView) findViewById(R.id.scroll);
        save.setOnClickListener(view->{
            Toast.makeText(getApplicationContext(), "Save is Completed",Toast.LENGTH_LONG).show();
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
}