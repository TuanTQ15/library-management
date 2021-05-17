package com.example.qlthuvien.model;


import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataConverter {
    Gson gson = new Gson();
    @TypeConverter
     public String listToString(List<SachMuon> sachMuonList){
        return gson.toJson(sachMuonList);
    }

    @TypeConverter
    public List<SachMuon> stringToList(String json){
        Type collectionType = new TypeToken<List<SachMuon>>(){}.getType();
        return gson.fromJson(json,collectionType);
    }
}
