package com.example.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Activity.R;
import com.example.Data.ItemData;

import java.util.ArrayList;



public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    // adapter에 들어갈 list.
    // 데이터리스트를 미리 만들었잖아. 그 데이터타입의 배열을 선언해서 그걸 안에 요소로써 넣는것이다.


    private ArrayList<ItemData> listData = new ArrayList<>();
    private ViewGroup parent;

    public ItemAdapter(OnListItemSelectedInterface listener, OnListItemLongSelectedInterface longListener) {
        this.mListener = listener;
        this.mLongListener = longListener; }

    public interface OnListItemLongSelectedInterface {
        void onItemLongSelected(View v, int position);
    }

    public interface OnListItemSelectedInterface{
        void onItemSelected (View v, int position);
    }


    private OnListItemSelectedInterface mListener;
    private OnListItemLongSelectedInterface mLongListener;




    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item, parent, false);
        return new ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.onBind(position, listData.get(position), parent);
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
                    Log.d("where",String.valueOf(getAdapterPosition()));
                    mListener.onItemSelected(view, getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mLongListener.onItemLongSelected(view, getAdapterPosition());
                    return false;
                }
            });
//왜 size는 2밖에 안되는거지?
        }



        void onBind(int position, ItemData data, @NonNull ViewGroup parent) {
            number.setText(String.valueOf(position + 1));
            date.setText(data.getDate());
            title.setText(data.getWhen());
            //imageView.setImageResource(data.getImage());
        }

    }

    //어댑터에서 함수의 이름만 선언했음.
    // 함수의 몸체, 즉 함수의 기능은 해당 액티비티에 코드를 쓰는 것이다.
}