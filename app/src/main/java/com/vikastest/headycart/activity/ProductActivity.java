package com.vikastest.headycart.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import com.vikastest.headycart.R;
import com.vikastest.headycart.adapter.CategoryAdapter;
import com.vikastest.headycart.adapter.ProductAdapter;
import com.vikastest.headycart.model.Category;
import com.vikastest.headycart.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProductActivity extends AppCompatActivity {
    private RecyclerView product_recycler_view;
    private ProductAdapter productsAdapter;
    private TextView tv_sub_category;
    private Toolbar toolbar;
    private ImageButton ib_menu;
    private  ArrayList<Product> productArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        getRef();


        //sorting menu

        ib_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pop up menu for sorting
                createPopupMenu();
            }
        });

        //toolbar back button navigation
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        Intent intent=getIntent();

        //getting Product list of a sub category from Intent
       productArrayList= (ArrayList<Product>) intent.getSerializableExtra("product");

        String categoryName=intent.getStringExtra("title");
        tv_sub_category.setText(categoryName);

        //setting Product adapter
        productsAdapter=new ProductAdapter(ProductActivity.this,productArrayList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        product_recycler_view.setLayoutManager(linearLayoutManager);
        product_recycler_view.setAdapter(productsAdapter);
    }
    private void getRef(){
        //getting references for Views
        ib_menu=findViewById(R.id.ib_menu);
        product_recycler_view=findViewById(R.id.product_recycler_view);
        tv_sub_category=findViewById(R.id.tv_sub_category);
        toolbar=findViewById(R.id.toolbar);

        //setting toolbar for navigation
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
    private void createPopupMenu(){

        PopupMenu popup = new PopupMenu(ProductActivity.this, ib_menu);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.sort_menu, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {

                //switch case to handle the various sorting options
                switch (item.getItemId()){

                    case R.id.menu_most_ordered_products:
                    {
                        //sorting using Collections.sort
                        Collections.sort(productArrayList, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                return o2.getOrder_count()-o1.getOrder_count();
                            }
                        });
                        productsAdapter.notifyDataSetChanged();

                        break;
                    }
                    case R.id.menu_most_shared_products:
                    {
                        //sorting using Collections.sort
                        Collections.sort(productArrayList, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                return o2.getShares()-o1.getShares();
                            }
                        });
                        productsAdapter.notifyDataSetChanged();

                        break;
                    }
                    case R.id.menu_most_viewed_products:
                    {
                        //sorting using Collections.sort
                        Collections.sort(productArrayList, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                return o2.getView_count()-o1.getView_count();
                            }
                        });
                        productsAdapter.notifyDataSetChanged();

                        break;
                    }
                }
                return true;
            }
        });

        popup.show();
    }
}
