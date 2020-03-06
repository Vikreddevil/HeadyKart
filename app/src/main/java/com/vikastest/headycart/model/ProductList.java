package com.vikastest.headycart.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;


import com.google.gson.annotations.SerializedName;
import com.vikastest.headycart.Utils.DataConverter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vikas on 26/02/20.
 */
@Entity
public class ProductList  implements Serializable{


    @PrimaryKey(autoGenerate = true)
    private int id;

    @TypeConverters({DataConverter.class})
    @ColumnInfo(name = "categories")
    @SerializedName("categories")
    private List<Category> categoryArrayList;

    @TypeConverters({DataConverter.class})
    @ColumnInfo(name = "rankings")
    @SerializedName("rankings")
    private List<Ranking> rankingArrayList;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Category> getCategoryArrayList() {
        return categoryArrayList;
    }

    public void setCategoryArrayList(List<Category> categoryArrayList) {
        this.categoryArrayList = categoryArrayList;
    }

    public List<Ranking> getRankingArrayList() {
        return rankingArrayList;
    }

    public void setRankingArrayList(List<Ranking> rankingArrayList) {
        this.rankingArrayList = rankingArrayList;
    }
}
