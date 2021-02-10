package com.example.first_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import Adapter.ItemAdapter;
import Data.ItemData;

public class Start_list extends AppCompatActivity {
    private ItemAdapter adapter;
    RecyclerView recyclerView;
    Button button;
    TextView Date;
    TextView Title;
    TextView newname;
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
            adapter.addItem(new ItemData("new name","Date", "Title"));
            adapter.notifyDataSetChanged();
        });


        //여기서부터 오류. 아이템리스트에 있는 항목들중 하나만이라도 클릭하면 ListActivity로 넘어가게끔 하려고 했음
        Date.setOnClickListener(view->{
            gotoList();
        });
        Title.setOnClickListener(view->{
            gotoList();
        });
        newname.setOnClickListener(view->{
            gotoList();
        });


    }
private void gotoList(){
    Intent intent;
    intent=new Intent(getApplicationContext(),ListActivity.class);
    startActivity(intent);
}
    private void getData(){
        for (int i=0; i<=1; i++) adapter.addItem(new ItemData("new name","Date","Title"));
        adapter.notifyDataSetChanged();
    }
}