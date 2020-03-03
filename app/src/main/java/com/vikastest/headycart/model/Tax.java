package com.vikastest.headycart.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by vikas on 26/02/20.
 */

public class Tax implements Serializable{

    @SerializedName("name")
    private String tax_name;

    @SerializedName("value")
    private double tax_value;

    public String getTax_name() {
        return tax_name;
    }

    public void setTax_name(String tax_name) {
        this.tax_name = tax_name;
    }
}
