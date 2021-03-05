package com.example.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.ItemAdapter;
import com.example.Data.ItemData;
import com.example.Util.PreUtil;

import java.util.ArrayList;


//리사이클러 뷰 코드

public class RecyclerViewActivity extends AppCompatActivity  {


    private PreUtil prefUtil;
    private ItemAdapter adapter;
    RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private ArrayList<ItemData> diaries;


    Button addBtn;
    int count=1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);


        prefUtil = new PreUtil(this);
        sharedPreferences = getSharedPreferences("sFile",MODE_PRIVATE);
        editor = sharedPreferences.edit();


        recyclerView=findViewById(R.id.recyclerView);
        addBtn =findViewById(R.id.add_itemView);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new ItemAdapter();
        recyclerView.setAdapter(adapter);


        addBtn.setOnClickListener(view->{
            Toast.makeText(this, "Create The New Diet Record!", Toast.LENGTH_SHORT).show();
            adapter.addItem(new ItemData(String.valueOf(count),"Date","When","1"));
            count+=1;
            adapter.notifyDataSetChanged();
        });
    }

    public void onItemLongSelected(View v, int position) {
        prefUtil.removeDiaryPref(position);
        Toast.makeText(getApplicationContext(), "Remove Diary", Toast.LENGTH_SHORT).show();
        getData();
    }





    private void getData(){
        adapter.resetItem();
        diaries = prefUtil.getDiaryPref();
        for(int i = 0; i < diaries.size(); i++) adapter.addItem(diaries.get(i));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
}
