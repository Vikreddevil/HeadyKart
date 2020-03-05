package com.vikastest.headycart.RoomEntities;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.vikastest.headycart.model.ProductList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vikas on 28/08/19.
 */

@Dao
public interface DaoAccess {

    @Insert
    Long insertTask(ProductList productList);


    @Query("SELECT * FROM ProductList")
    LiveData<List<ProductList>> fetchAllCategories();

    @Query("SELECT COUNT(*) FROM ProductList")
    LiveData<Integer> getCount();

    @Update
    void updateTask(ProductList userProfileEntity);


    @Delete
    void deleteTask(ProductList userProfileEntity);
}
