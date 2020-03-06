package com.vikastest.headycart.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vikastest.headycart.R;

import com.vikastest.headycart.Utils.ProductUtil;
import com.vikastest.headycart.model.Product;
import com.vikastest.headycart.model.Variant;

import java.util.ArrayList;

import java.util.HashMap;


/**
 * Created by vikas on 27/02/20.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.CustomViewHolder>{

    private Context context;
    private ArrayList<Product> productArrayList=new ArrayList<>();
    private HashMap<Integer,Integer> hashMap;

    public ProductAdapter(Context context,ArrayList<Product> productArrayList){

        this.hashMap=new HashMap<>();
        this.productArrayList=productArrayList;
        this.context=context;

        for (int i=0;i<productArrayList.size();i++){

            hashMap.put(i,0);
        }

    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_row, viewGroup,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder customViewHolder, final int i) {


        customViewHolder.setIsRecyclable(false);

        //setting Product Name
        customViewHolder.tv_product_name.setText(productArrayList.get(i).getProduct_name());

        //setting Product's initial Price

        String price="₹ "+productArrayList.get(i).getVariantArrayList().get(0).getPrice();
        customViewHolder.tv_product_price.setText(price);


        int previous_size=0;
        ArrayList<String> previousColorArrayList=new ArrayList<>();

        if(productArrayList.get(i).getVariantArrayList().get(0).getSize()==0)
            customViewHolder.ll_product_size.setVisibility(View.GONE);
        else
            customViewHolder.ll_product_size.setVisibility(View.VISIBLE);

        //looping through the Variant arrayList for getting size and color of the Product
        for (int j=0;j<productArrayList.get(i).getVariantArrayList().size();j++){

            if(previous_size!=productArrayList.get(i).getVariantArrayList().get(j).getSize()) {

                //create dynamic TextView for setting size and adding it to the Linear Layout
               final TextView tv= createTextView(i,j,0);
               customViewHolder.ll_product_size.addView(tv);


               //setting listener for every Size available
               tv.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {

                       tv.setTypeface(tv.getTypeface(),Typeface.BOLD);
                       resetTypefaceSize(i, Integer.parseInt(tv.getText().toString()),customViewHolder);
                       hashMap.put(i,tv.getId());

                   }
               });


            }
            //tracking previous size for not repeating values
            previous_size=productArrayList.get(i).getVariantArrayList().get(j).getSize();

            if(!previousColorArrayList.contains(productArrayList.get(i).getVariantArrayList().get(j).getColor())){
                //create dynamic TextView for setting size and adding it to the Linear Layout
                final TextView tv= createTextView(i,j,1);
                customViewHolder.ll_product_color.addView(tv);

                //setting listener for every color available

                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        tv.setTypeface(tv.getTypeface(),Typeface.BOLD);
                        resetTypefaceColor(i, tv.getText().toString(),customViewHolder);
                        resetPrice(customViewHolder,tv.getId(),i);
                    }
                });

            }
            //tracking previous color for not repeating values
            previousColorArrayList.add(productArrayList.get(i).getVariantArrayList().get(j).getColor());

        }
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_product_name;
        private LinearLayout ll_product_color;
        private TextView tv_product_price;

        private LinearLayout ll_product_size;

        CustomViewHolder(View view) {
            super(view);

            ll_product_color=view.findViewById(R.id.ll_product_color);
            tv_product_name=view.findViewById(R.id.tv_product_name);
            ll_product_size=view.findViewById(R.id.ll_product_size);
            tv_product_price=view.findViewById(R.id.tv_product_price);


        }
    }
    //method for creating Dynamic text view for Size and Color of the Product
    private TextView createTextView(int i,int j,int type){
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView tv = new TextView(context);

        float scale = context.getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (7 * scale + 0.5f);
        tv.setPadding(dpAsPixels, 0, dpAsPixels, 0);
        tv.setLayoutParams(lparams);

        tv.setTextColor(context.getResources().getColor(R.color.black));
        //setting the first values selected by default

        if(j==0){


            tv.setTypeface(tv.getTypeface(), Typeface.BOLD);


        }

        if(type==0) {
            String size = productArrayList.get(i).getVariantArrayList().get(j).getSize() + "";
            tv.setText(size);
            //setting id range for size
            tv.setId(i+j+1);
            //set id for initial Textview of Size
            if(j==0)
                hashMap.put(i,tv.getId());
        }
        else {
            String color = productArrayList.get(i).getVariantArrayList().get(j).getColor();
            tv.setText(color);
            //setting id range for color
            tv.setId(i+j+9999);
        }
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);

        return tv;
    }

    //setting Typeface to normal for non selected Sizes

    private void resetTypefaceSize(int i,int size,CustomViewHolder customViewHolder){

        ArrayList<Variant> variantArrayList=new ArrayList<>();
        variantArrayList.addAll(productArrayList.get(i).getVariantArrayList());

        //changing typeface of non selected Size
        for(int j=0;j<variantArrayList.size();j++){
            if(variantArrayList.get(j).getSize()!=size){

                TextView tv_size=customViewHolder.itemView.findViewById(i+j+1);
               if(tv_size!=null)
                tv_size.setTypeface(null,Typeface.NORMAL);
            }
        }


    }
    //setting Typeface to normal for non selected Colors
    private void resetTypefaceColor(int i,String color,CustomViewHolder customViewHolder){

        ArrayList<Variant> variantArrayList=new ArrayList<>();
        variantArrayList.addAll(productArrayList.get(i).getVariantArrayList());

        //changing typeface of non selected Size
        for(int j=0;j<variantArrayList.size();j++){
            if(!variantArrayList.get(j).getColor().equals(color)){

                TextView tv_size=customViewHolder.itemView.findViewById(i+j+9999);
                if(tv_size!=null)
                    tv_size.setTypeface(null,Typeface.NORMAL);
            }
        }


    }

    //resetting Price based on Size and Color selection
    private void resetPrice(CustomViewHolder customViewHolder,int id,int i){

        //reset Price based on Color selection
        TextView tv_color=customViewHolder.itemView.findViewById(id);
        TextView tv_size = null;
        if(hashMap.get(i)!=0)
         tv_size=customViewHolder.itemView.findViewById(hashMap.get(i));

        if(tv_color!=null){

            int size=0;
            if(tv_size!=null)
             size= Integer.parseInt(tv_size.getText().toString());
            String color=tv_color.getText().toString();
            long price=0;

            for (Product p:productArrayList){

                for (Variant v:p.getVariantArrayList()){

                    if(v.getSize()==size&&v.getColor().equals(color)){
                        price=v.getPrice();
                        break;
                    }


                }
                if(price!=0)
                    break;
            }
            if(price!=0){
                String str_price="₹ "+price;
                customViewHolder.tv_product_price.setText(str_price);
            }
            else
                ProductUtil.createAlert(context);
            //creating alert if Product size and color combination not available

        }

        
    }



}
