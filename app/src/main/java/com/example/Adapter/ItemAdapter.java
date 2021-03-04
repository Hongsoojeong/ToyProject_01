package com.example.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Activity.MemoActivity;
import com.example.Activity.R;
import com.example.Data.ItemData;

import java.util.ArrayList;


//어댑터 코드


//어댑터 코드에서 리사이클러 어댑터를 상속받아야 함

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    // adapter에 들어갈 list.
    // 데이터리스트를 미리 만들었잖아. 그 데이터타입의 배열을 선언해서 그걸 안에 요소로써 넣는것이다.


    private ArrayList<ItemData> listData = new ArrayList<>();
    private ViewGroup parent;
    
    public interface OnListItemLongSelectedInterface {
        void onItemLongSelected(View v, int position);
    }

    public interface OnListItemSelectedInterface{
        void onItemSelected (View v, int position);
    }


    private OnListItemSelectedInterface mListener;
    private OnListItemLongSelectedInterface mLongListener;

    public void Adapter(OnListItemSelectedInterface listener, OnListItemLongSelectedInterface longListener) {
        this.mListener = listener;
        this.mLongListener = longListener;
    }
//오버라이드 되야하는 함수는 총 3가지.
// 1. 뷰홀더를 만드는 함수 2. 갯수를 세는 함수 3. 뷰 홀더에 뷰의 데이터를 바인딩하는 함수



    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // layoutInflater 를 이용하여 전 단계에서 만들었던 item.xml 를 inflate 시킴.
        // return 인자는 ViewHolder.
        
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item, parent, false);

        //View 타입의 객체를 만든 후, layoutInflater 를 통해서 해당 레이아웃의 데이터를 인플레이트
        // date, title 이 있고, add 버튼이 있었던 그 레이아웃

        return new ItemViewHolder(view);

        // 그리고 그 해당되는 view 를 리턴해주는 거지
        // 밑에 ItemViewHolder 를 정의해줬음

    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(position, listData.get(position), parent); //onBind 라는 함수를 통해서 데이터를 바인딩하는거
    }


    @Override
    public int getItemCount() {
        // RecyclerView 의 총 개수.
        return listData.size();
    }

    public void addItem(ItemData data) {
        // 외부에서 item 을 추가시킬 함수.
        listData.add(data);
    }

    public void resetItem(){
        listData.clear();
    }
    // RecyclerView 의 핵심인 ViewHolder.
    // 여기서 subView 를 setting.


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView number;
        private TextView date;
        private TextView title;


        ItemViewHolder(View itemView) {
            super(itemView);
            //앞에서 item 레아이웃 view 를 받았으니까 해당하는 id 값을 가져와서 할 수 있음.

            number =itemView.findViewById(R.id.count_item);
            date =itemView.findViewById(R.id.date_item);
            title =itemView.findViewById(R.id.title_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent;
                    intent=new Intent(itemView.getContext(), MemoActivity.class);
                    itemView.getContext().startActivity(intent);
                }
            });

            }


        void onBind(int position, ItemData data, @NonNull ViewGroup parent) {

            number.setText(data.getNumber());
            date.setText(data.getDate());
            title.setText(data.getTitle());
            //imageView.setImageResource(data.getImage());

        }


    }

    //어댑터에서 함수의 이름만 선언했음.
    // 함수의 몸체, 즉 함수의 기능은 해당 액티비티에 코드를 쓰는 것이다.
}