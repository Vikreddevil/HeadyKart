package com.vikastest.headycart.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vikastest.headycart.R;
import com.vikastest.headycart.Utils.ProductUtil;
import com.vikastest.headycart.activity.ProductActivity;
import com.vikastest.headycart.model.Category;
import com.vikastest.headycart.model.Product;
import com.vikastest.headycart.model.Ranking;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by vikas on 27/02/20.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CustomViewHolder>{


    private ArrayList<Category> categoryArrayList;
    private ArrayList<Category> mainCategoryArrayList;
    private ArrayList<Ranking> rankingArrayList;
    private Context context;


    public CategoryAdapter(Context context, ArrayList<Category> categoryArrayList, ArrayList<Category> mainCategoryArrayList,ArrayList<Ranking> rankingArrayList){
        this.categoryArrayList=categoryArrayList;
        this.rankingArrayList=rankingArrayList;
        this.mainCategoryArrayList=mainCategoryArrayList;
        this.context=context;

    }
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_row, viewGroup,false);
        CategoryAdapter.CustomViewHolder viewHolder = new CategoryAdapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder,final int i) {

        customViewHolder.tv_category_name.setText(mainCategoryArrayList.get(i).getCategory_name());

        //getting Sub Categories
       for(int j=0;j<mainCategoryArrayList.get(i).getChildCategoryList().length;j++) {
           long categoryId=mainCategoryArrayList.get(i).getChildCategoryList()[j];
           Category subCategory=new Category();
           for(Category c : categoryArrayList){
               if(c.getCategory_id()==categoryId){
                   subCategory=c;
               }
           }
           //creating dynamic text views for Sub Categories
           LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                   LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
           TextView tv = new TextView(context);
           tv.setLayoutParams(lparams);
           tv.setTextColor(context.getResources().getColor(R.color.black));
           tv.setText(subCategory.getCategory_name());
           tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
           float scale = context.getResources().getDisplayMetrics().density;
           int dpAsPixels = (int) (7*scale + 0.5f);
           tv.setPadding(0,dpAsPixels,0,dpAsPixels);

           customViewHolder.ll_sub_categories.addView(tv);

           final Category finalSubCategory = subCategory;
           tv.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                   //sending data of selected Sub Category to ProductActivity Class
                   ArrayList<Product> productCategoryArrayList=new ArrayList<>();


                   for(Category c : categoryArrayList){
                       if(Arrays.binarySearch(finalSubCategory.getChildCategoryList(),c.getCategory_id())>=0){
                           productCategoryArrayList.addAll(c.getProductArrayList());
                       }
                       //something here
                   }
                   productCategoryArrayList= ProductUtil.calculateRankings(productCategoryArrayList,rankingArrayList);
                   Intent intent=new Intent(context, ProductActivity.class);
                   intent.putExtra("product", productCategoryArrayList);
                   intent.putExtra("title",finalSubCategory.getCategory_name());
                   context.startActivity(intent);
               }
           });


       }



    }

    @Override
    public int getItemCount() {
        return mainCategoryArrayList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_category_name;

        private LinearLayout ll_sub_categories;


        CustomViewHolder(View view) {
            super(view);


            tv_category_name=view.findViewById(R.id.tv_category_name);
            ll_sub_categories=view.findViewById(R.id.ll_sub_categories);


        }
    }

}
