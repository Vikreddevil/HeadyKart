package com.vikastest.headycart.RoomEntities;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.vikastest.headycart.model.ProductList;

/**
 * Created by vikas on 03/03/20.
 */
@Database(entities = {ProductList.class}, version = 1, exportSchema = false)
public abstract class CategoryDatabase extends RoomDatabase{
    public abstract DaoAccess daoAccess();
}
