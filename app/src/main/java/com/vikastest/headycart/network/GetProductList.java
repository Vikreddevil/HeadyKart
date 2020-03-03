package com.vikastest.headycart.network;

import com.vikastest.headycart.model.ProductList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vikas on 27/02/20.
 */

public interface GetProductList {

    @GET("/json")
    Call<ProductList> getProductList();
}
