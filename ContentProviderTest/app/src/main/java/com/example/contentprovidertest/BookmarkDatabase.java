package com.example.contentprovidertest;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.Objects;

@Database(entities = {Bookmark.class}, version = 15, exportSchema = false)
public abstract class BookmarkDatabase extends RoomDatabase{
    public abstract BookmarkDao bookmarkDao();

    private static BookmarkDatabase instance = null;

    public static BookmarkDatabase getInstance(Context con){
        if ( instance == null ){
            instance = Room.databaseBuilder(con, BookmarkDatabase.class, "test.db")
                    .createFromAsset("database/bookmark.db")
                    .build();
        }
        return Objects.requireNonNull(instance);
    }
}
