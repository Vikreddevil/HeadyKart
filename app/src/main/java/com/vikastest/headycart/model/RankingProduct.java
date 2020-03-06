package com.vikastest.headycart.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by vikas on 26/02/20.
 */

public class RankingProduct implements Serializable{

    @SerializedName("id")
    private int product_id;

    private int view_count;

    private int order_count;

    private int shares;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }
}
