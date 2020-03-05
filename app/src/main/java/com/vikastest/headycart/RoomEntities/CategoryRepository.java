package com.vikastest.headycart.RoomEntities;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import com.vikastest.headycart.model.ProductList;

import java.util.List;

/**
 * Created by vikas on 03/03/20.
 */

public class CategoryRepository {

    private String DB_NAME = "db_user_profile";

    private Context mCtx;
    private static CategoryRepository mInstance;
    private CategoryDatabase appDatabase;


    private CategoryRepository(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //Category is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, CategoryDatabase.class, "Category").build();
    }

    public static synchronized CategoryRepository getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new CategoryRepository(mCtx);
        }
        return mInstance;
    }

    public void insertTask(final ProductList productList) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.daoAccess().insertTask(productList);
                return null;
            }
        }.execute();
    }
    public LiveData<List<ProductList>> getProducts() {
        return appDatabase.daoAccess().fetchAllCategories();
    }
    public LiveData<Integer> getCategoryCount() {
        return appDatabase.daoAccess().getCount();
    }


}
