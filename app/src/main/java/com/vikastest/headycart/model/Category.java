package com.vikastest.headycart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by vikas on 26/02/20.
 */

public class Category implements Serializable{



    public Category(){

    }
    @SerializedName("id")
    private long category_id;

    @SerializedName("name")
    private String category_name;

    @SerializedName("products")
    private ArrayList<Product> productArrayList;

    @SerializedName("child_categories")
    private long[] childCategoryList;

    private boolean is_sub_category;

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public ArrayList<Product> getProductArrayList() {
        return productArrayList;
    }

    public void setProductArrayList(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }




    public boolean isIs_sub_category() {
        return is_sub_category;
    }

    public void setIs_sub_category(boolean is_sub_category) {
        this.is_sub_category = is_sub_category;
    }

    public long[] getChildCategoryList() {
        return childCategoryList;
    }

    public void setChildCategoryList(long[] childCategoryList) {
        this.childCategoryList = childCategoryList;
    }
}
