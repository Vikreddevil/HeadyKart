package com.vikastest.headycart.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.vikastest.headycart.R;
import com.vikastest.headycart.activity.CategoryActivity;
import com.vikastest.headycart.model.Category;
import com.vikastest.headycart.model.Product;
import com.vikastest.headycart.model.Ranking;
import com.vikastest.headycart.model.RankingProduct;

import java.util.ArrayList;

/**
 * Created by vikas on 06/03/20.
 */

public class ProductUtil {

    public static ArrayList<Product> calculateRankings(ArrayList<Product> productArrayList, ArrayList<Ranking> rankingArrayList){



        ArrayList<Product> finalProductArrayList1=new ArrayList<>();

        //setting Ranks to Products

            for(int i=0;i<productArrayList.size();i++) {
                finalProductArrayList1.add(productArrayList.get(i));
                for (Ranking r : rankingArrayList) {

                    for (RankingProduct rP : r.getRankingProductArrayList()) {


                        if(productArrayList.get(i).getProduct_id()== rP.getProduct_id()){

                            if(rP.getShares()!=0)
                            finalProductArrayList1.get(i).setShares(rP.getShares());
                            if(rP.getOrder_count()!=0)
                            finalProductArrayList1.get(i).setOrder_count(rP.getOrder_count());
                            if(rP.getView_count()!=0) {
                                finalProductArrayList1.get(i).setView_count(rP.getView_count());
                                System.out.println("View count is for id " + rP.getProduct_id() + " and count is" + rP.getView_count());
                            }
                            break;

                        }

                    }

                }
            }

        return finalProductArrayList1;

    }
    public static void createAlert(Context context){
        new AlertDialog.Builder(context)
                .setTitle("Product Not available")
                .setMessage("Sorry, the product of selected color is not available !")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation

                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
