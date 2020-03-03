package com.vikastest.headycart.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by vikas on 26/02/20.
 */

public class Product implements Serializable{


    public Product(){

    }
    @SerializedName("id")
    private int product_id;

    @SerializedName("name")
    private String product_name;

    private String date_added;

    @SerializedName("variants")
    private ArrayList<Variant> variantArrayList;

    @SerializedName("tax")
    private Tax tax;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public ArrayList<Variant> getVariantArrayList() {
        return variantArrayList;
    }

    public void setVariantArrayList(ArrayList<Variant> variantArrayList) {
        this.variantArrayList = variantArrayList;
    }


    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }
}
