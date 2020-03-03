package com.vikastest.headycart.model;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by vikas on 26/02/20.
 */
@Entity
public class ProductList  implements Serializable{

    public ProductList(){

    }
    @SerializedName("categories")
    private ArrayList<Category> categoryArrayList;

    @SerializedName("rankings")
    private ArrayList<Ranking> rankingArrayList;

    public ArrayList<Category> getCategoryArrayList() {
        return categoryArrayList;
    }

    public void setCategoryArrayList(ArrayList<Category> categoryArrayList) {
        this.categoryArrayList = categoryArrayList;
    }

    public ArrayList<Ranking> getRankingArrayList() {
        return rankingArrayList;
    }

    public void setRankingArrayList(ArrayList<Ranking> rankingArrayList) {
        this.rankingArrayList = rankingArrayList;
    }
}
