package com.vikastest.headycart.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by vikas on 26/02/20.
 */

public class Ranking implements Serializable{

    private String ranking;

    @SerializedName("products")
    private ArrayList<RankingProduct> rankingProductArrayList;

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public ArrayList<RankingProduct> getRankingProductArrayList() {
        return rankingProductArrayList;
    }

    public void setRankingProductArrayList(ArrayList<RankingProduct> rankingProductArrayList) {
        this.rankingProductArrayList = rankingProductArrayList;
    }
}
