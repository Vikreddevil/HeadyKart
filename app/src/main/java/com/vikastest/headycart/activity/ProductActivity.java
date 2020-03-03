package com.vikastest.headycart.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vikastest.headycart.R;
import com.vikastest.headycart.adapter.CategoryAdapter;
import com.vikastest.headycart.adapter.ProductAdapter;
import com.vikastest.headycart.model.Category;
import com.vikastest.headycart.model.Product;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    private RecyclerView product_recycler_view;
    private ProductAdapter productsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        product_recycler_view=findViewById(R.id.product_recycler_view);
        Intent intent=getIntent();

        ArrayList<Product> productArrayList= (ArrayList<Product>) intent.getSerializableExtra("product");

        productsAdapter=new ProductAdapter(ProductActivity.this,productArrayList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        product_recycler_view.setLayoutManager(linearLayoutManager);
        product_recycler_view.setAdapter(productsAdapter);
    }
}
