package com.vikastest.headycart.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.vikastest.headycart.R;
import com.vikastest.headycart.adapter.CategoryAdapter;
import com.vikastest.headycart.model.Category;
import com.vikastest.headycart.model.ProductList;
import com.vikastest.headycart.network.GetProductList;
import com.vikastest.headycart.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView category_recycler_view;
    private CategoryAdapter categoryAdapter;
    private ArrayList<Category> categoryArrayList;
    private ArrayList<Category> mainCategoryArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainCategoryArrayList=new ArrayList<>();
        category_recycler_view =findViewById(R.id.category_recycler_view);
        categoryArrayList=new ArrayList<>();
        categoryAdapter =new CategoryAdapter(CategoryActivity.this,categoryArrayList,mainCategoryArrayList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        category_recycler_view.setLayoutManager(linearLayoutManager);
        category_recycler_view.setAdapter(categoryAdapter);



         /*Create handle for the RetrofitInstance interface*/
        GetProductList service = RetrofitInstance.getRetrofitInstance().create(GetProductList.class);

        /*Call the method with parameter in the interface to get the product data*/
        Call<ProductList> call = service.getProductList();


        // API call
        call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(@NonNull Call<ProductList> call, @NonNull Response<ProductList> response) {


                if(response.body()!=null){
                    if(response.body().getCategoryArrayList()!=null){
                    int i=0;

                    if(ProductList.count(ProductList.class)==0)
                    {
                        ProductList productList=response.body();
                        productList.save();
                        System.out.println("size is "+productList.getCategoryArrayList().size());
                    }

                        while(i<response.body().getCategoryArrayList().size()){

                                Category category=response.body().getCategoryArrayList().get(i);
                                categoryArrayList.add(category);





                            i++;
                        }
                generateMainCategory();

                    }




                }

            }

            @Override
            public void onFailure(@NonNull Call<ProductList> call, @NonNull Throwable t) {
                if(ProductList.count(ProductList.class)!=0){
                    ProductList productList=ProductList.listAll(ProductList.class).get(0);
                    if(productList!=null&&productList.getCategoryArrayList()!=null)
                    categoryArrayList.addAll(productList.getCategoryArrayList());
                    generateMainCategory();

                }
                else
                Toast.makeText(CategoryActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void generateMainCategory(){
        int i=0;
        while(i<categoryArrayList.size()){

            long id=categoryArrayList.get(i).getCategory_id();
            boolean is_sub_category=false;
            for(int j=0;j<categoryArrayList.size();j++){
                if(j==i)
                    continue;
                long[] temp_category_id=categoryArrayList.get(j).getChildCategoryList();

                if(Arrays.binarySearch(temp_category_id,id)>=0){
                    is_sub_category=true;
                }


            }
            categoryArrayList.get(i).setIs_sub_category(is_sub_category);
            if(!is_sub_category){
                mainCategoryArrayList.add(categoryArrayList.get(i));
            }


            i++;
        }
        categoryAdapter.notifyDataSetChanged();

    }
}
