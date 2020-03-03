package com.vikastest.headycart.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by vikas on 26/02/20.
 */

public class RankingProduct implements Serializable{

    @SerializedName("id")
    private long product_id;

    private long view_count;

    private long order_count;

    private long shares;

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public long getView_count() {
        return view_count;
    }

    public void setView_count(long view_count) {
        this.view_count = view_count;
    }

    public long getOrder_count() {
        return order_count;
    }

    public void setOrder_count(long order_count) {
        this.order_count = order_count;
    }

    public long getShares() {
        return shares;
    }

    public void setShares(long shares) {
        this.shares = shares;
    }
}
