package com.vladislaviliev.shopdemo.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Item.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class Db extends RoomDatabase {
    public abstract ItemDao userDao();
}