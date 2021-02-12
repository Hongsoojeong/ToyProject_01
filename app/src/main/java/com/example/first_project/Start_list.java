package com.example.first_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import Adapter.ItemAdapter;
import Data.ItemData;

//리사이클러 뷰 코드

public class Start_list extends AppCompatActivity  {
    private ItemAdapter adapter;
    RecyclerView recyclerView;
    Button button;
    int count=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        recyclerView=findViewById(R.id.recycler);
        button=findViewById(R.id.add);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new ItemAdapter();
        recyclerView.setAdapter(adapter);

        getData();


        recyclerView.setOnClickListener(view->{
        });


        button.setOnClickListener(view->{
            count+=1;
            Toast.makeText(this, "Create The New Diet Record!", Toast.LENGTH_SHORT).show();
            adapter.addItem(new ItemData(String.valueOf(count),"Date", "Title"));
            adapter.notifyDataSetChanged();
        });


        //여기서부터 오류. 아이템리스트에 있는 항목들중 하나만이라도 클릭하면 ListActivity로 넘어가게끔 하려고 했음


    }



    private void getData(){
        adapter.addItem(new ItemData(String.valueOf(count),"Date","Title"));
        adapter.notifyDataSetChanged();
    }
}