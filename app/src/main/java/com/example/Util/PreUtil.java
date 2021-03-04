package com.example.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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

    private static final String DIARY_DATA = "diary_data";

    public PreUtil(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("sFile", Context.MODE_PRIVATE);
        editor = preferences.edit();

    }

    public void setDiaryPref(ItemData diary) {
        ArrayList<ItemData> diaries = getDiaryPref();
        diaries.add(diary);
        Gson gson = new Gson();
        String json = gson.toJson(diaries);

        editor.putString("memoTitle", json);
        editor.commit();
    }

    public ArrayList<ItemData> getDiaryPref(){
        ArrayList<ItemData> arrayItems = new ArrayList<>();
        String serializedObject = preferences.getString("memoTitle", null);
        Log.d("PreUtil ArrayList",serializedObject );
        //여기서 serializedObject는 null 값이 아님을 알수있음
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<ItemData>>(){}.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }

        return arrayItems;
    }

    public void removeDiaryPref(int position){
        ArrayList<ItemData> diaries = getDiaryPref();
        diaries.remove(position);
        Gson gson = new Gson();
        String json = gson.toJson(diaries);
        editor.putString(DIARY_DATA, json);
        editor.commit();
    }

    private void resetDiaryPref(){
        editor.clear();
    }
}
