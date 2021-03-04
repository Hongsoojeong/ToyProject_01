package com.example.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
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

    private ItemAdapter adapter;
    RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private ArrayList<ItemData> diaries;
    private PreUtil prefUtil;

    Button addBtn;
    int count=1;


    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        prefUtil = new PreUtil(this);
        sharedPreferences = getSharedPreferences("sFile",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        recyclerView=findViewById(R.id.recyclerView);
        addBtn =findViewById(R.id.add_itemView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new ItemAdapter();
        recyclerView.setAdapter(adapter);

        getData();


        recyclerView.setOnClickListener(view->{
        });


        addBtn.setOnClickListener(view->{
            count+=1;
            Toast.makeText(this, "Create The New Diet Record!", Toast.LENGTH_SHORT).show();
            adapter.addItem(new ItemData(String.valueOf(count),"Date", "When","content"));
            adapter.notifyDataSetChanged();
        });

    }


    private void getData(){
        diaries = prefUtil.getDiaryPref();
        for(int i = 0; i < diaries.size(); i++) adapter.addItem(diaries.get(i));
        adapter.notifyDataSetChanged();

        }



    }


