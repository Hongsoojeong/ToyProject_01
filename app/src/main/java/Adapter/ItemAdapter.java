package Adapter; //오타났음 ㅠ
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.first_project.ListActivity;
import com.example.first_project.R;
import com.example.first_project.Start_list;

import java.util.ArrayList;
import Data.ItemData;


//어댑터 코드


//어댑터 코드에서 리사이클러 어댑터를 상속받아야 함
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    // adapter에 들어갈 list 입니다. // 데이터리스트를 미리 만들었잖아. 그 데이터타입의 배열을 선언해서 그걸 안에 요소로써 넣는것이다.
    private ArrayList<ItemData> listData = new ArrayList<>();



    public interface OnListItemSelectedInterface{
        void onItemSelcected (View v, int position);
    }




//오버라이드 되야하는 함수는 총 3가지. 갯수를 세는 함수.  뷰홀더를 만드는 함수. 뷰 홀더에 뷰의 데이터를 바인딩하는 함수

    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.

        //view는 모든 view의 상위클래스. view 안에 함수들이 막 있는데 우리가 흔히 imageview라던지
        //그런거 사용할 때 쓰는 함수들이 보여. 그러니까 view가 상위클래스라는 걸 알 수 있지.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        //View 타입의 객체를 만들어줘. 그 객체는 layoutInflater를 통해서 해당 레이아웃의 데이터를 인플레이트해
        return new ItemViewHolder(view); // 그리고 그 해당되는 view를 리턴해주는 거지  // 밑에 ItemViewHolder를 정의해줬음
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.

        holder.onBind(listData.get(position)); //onBind 라는 함수를 통해서 데이터를 바인딩하는거
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }


    public void addItem(ItemData data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView Number;
        private TextView Date;
        private TextView Title;


        ItemViewHolder(View itemView) {
            super(itemView); //앞에서 item 레아이웃 view를 받았으니까 해당하는 id값을 가져와서 할 수 있는거야
            Number=itemView.findViewById(R.id.image_item_recycler);
            Date=itemView.findViewById(R.id.Date);
            Title=itemView.findViewById(R.id.Title);

            Number.setOnClickListener(view->{
                Intent intent;
                intent=new Intent(itemView.getContext(),ListActivity.class);
                itemView.getContext().startActivity(intent);
                    }
            );
            }




        void onBind(ItemData data) {
            Number.setText(data.getNumber());
            Date.setText(data.getDate());
            Title.setText(data.getTitle());
//            imageView.setImageResource(data.getImage());
        }

    }

    //어댑터에서 함수의 이름만 선언했음. 함수의 몸체, 즉 함수의 기능은 해당 액티비티에 코드를 쓰는 것이다.

}