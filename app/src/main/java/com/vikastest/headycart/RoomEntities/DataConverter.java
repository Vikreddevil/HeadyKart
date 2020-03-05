package com.vikastest.headycart.RoomEntities;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vikastest.headycart.model.Category;
import com.vikastest.headycart.model.Ranking;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by vikas on 04/03/20.
 */

public class DataConverter implements Serializable {

    @TypeConverter // note this annotation
    public String fromCategoryList(List<Category> categoryList) {
        if (categoryList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Category>>() {
        }.getType();
        String json = gson.toJson(categoryList, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<Category> toCategoryList(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Category>>() {
        }.getType();
        List<Category> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }
    @TypeConverter // note this annotation
    public String fromRankingList(List<Ranking> rankingList) {
        if (rankingList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Ranking>>() {
        }.getType();
        String json = gson.toJson(rankingList, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<Ranking> toRankingList(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Ranking>>() {
        }.getType();
        List<Ranking> rankingList = gson.fromJson(optionValuesString, type);
        return rankingList;
    }
}
