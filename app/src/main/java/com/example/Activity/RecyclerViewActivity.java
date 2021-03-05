package com.example.Activity;

import android.content.Intent;
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

public class RecyclerViewActivity extends AppCompatActivity  implements ItemAdapter.OnListItemSelectedInterface, ItemAdapter.OnListItemLongSelectedInterface {

    private enum Page{
        DIARY
    }


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

        adapter = new ItemAdapter(this, this);

        recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        recyclerView.setAdapter(adapter);

        sharedPreferences = getSharedPreferences("sFile",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        addBtn =findViewById(R.id.add_itemView);





        addBtn.setOnClickListener(view->{
            adapter.addItem(new ItemData(String.valueOf(count),"Date","When","1"));
            adapter.notifyDataSetChanged();
            count+=1;
            Toast.makeText(this, "Create The New Diet Record!", Toast.LENGTH_SHORT).show();
        });


    }


    private void getData(){
        adapter.resetItem();
        diaries = prefUtil.getDiaryPref();
        for(int i = 0; i < diaries.size(); i++) adapter.addItem(diaries.get(i));
        adapter.notifyDataSetChanged();
    }

@Override
    public void onItemLongSelected(View v, int position) {
        if (position!=RecyclerView.NO_POSITION) {
            prefUtil.removeDiaryPref(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "Remove The Recorded Diet!", Toast.LENGTH_SHORT).show();
            getData();
        }
    }


@Override
    public void onItemSelected(View v, int position) {
    if(diaries.size()==position)
    {
        Intent intent;
        intent = new Intent(getApplicationContext(), MemoActivity.class);
        startActivity(intent);
    }
    else {
        goToPage(Page.DIARY, diaries.get(position));
    }

    }

    private void goToPage(Page page, ItemData diary){
        Intent intent;
        switch (page){
            case DIARY:
                intent = new Intent(getApplicationContext(), MemoActivity.class);
                            intent.putExtra("date", diary.getDate());
                            intent.putExtra("content", diary.getContent());

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + page);
        }
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
}
