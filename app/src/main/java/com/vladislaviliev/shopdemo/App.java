package com.vladislaviliev.shopdemo;

import android.app.Application;
import android.content.Context;
import android.net.Uri;

import androidx.room.Room;

import com.vladislaviliev.shopdemo.db.Db;
import com.vladislaviliev.shopdemo.db.ItemDao;

// Dependency injection seems overkill for now, use this as container
public class App extends Application {

    public static String[] imagesEnum;

    public ItemDao dbDao;

    public static Uri getImageUri(final String imageName) {
        return Uri.parse("file:///android_asset/db_images/" + imageName);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        final Context context = getApplicationContext();
        try {
            imagesEnum = context.getAssets().list("db_images");
        } catch (final Exception e) {
            imagesEnum = new String[0];
            e.printStackTrace();
        }
        dbDao = Room.databaseBuilder(context, Db.class, "shopdemo-db").build().userDao();
    }
}