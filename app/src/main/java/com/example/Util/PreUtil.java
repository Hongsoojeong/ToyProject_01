package com.example.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.Data.ItemData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PreUtil {
    Context context;
    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;

    public PreUtil(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("sFile", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setDiaryPref(ItemData diary) {
        ArrayList<ItemData> diaries = getDiaryPref(); //배열형태 데이터로 diaries 의 내용을 저장한다.
        diaries.add(diary); // 배열에 diaries 를 추가한다. (요소의 개수 +1)
        Gson gson = new Gson(); //Gson의 객체 생성
        String json = gson.toJson(diaries); //diaries 를 json화 한다.
        editor.putString("memo", json); //editor에 json을 저장한다.
        editor.commit();
    }

    public ArrayList<ItemData> getDiaryPref(){
        ArrayList<ItemData> arrayItems = new ArrayList<>();
        String serializedObject = preferences.getString("memo", null);
        //여기서 serializedObject는 null 값이 아님을 알수있음
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<ItemData>>(){}.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }
        return arrayItems;
    }
//그래서 temp가 있는건가? 나중에 변경했을때 글러수있게..

    public void setTempPref(ItemData diary) {
        Gson gson = new Gson();
        String json = gson.toJson(diary);
        editor.putString("temp", json);
        editor.commit();
    }

    public ItemData getTempPref() {
        ItemData diary = new ItemData();
        String serializedObject = preferences.getString("temp", null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<ItemData>(){}.getType();
            diary = gson.fromJson(serializedObject, type);
        }
        return diary;
    }


    public void removeDiaryPref(int position){
        ArrayList<ItemData> diaries = getDiaryPref();
        diaries.remove(position);
        Gson gson = new Gson();
        String json = gson.toJson(diaries);
        editor.putString("memo", json);
        editor.commit();
    }


    public void removeTempPref() {
        Gson gson = new Gson();
        String json = gson.toJson(new ItemData());
    }

    private void resetDiaryPref(){
        editor.clear();
    }

}
