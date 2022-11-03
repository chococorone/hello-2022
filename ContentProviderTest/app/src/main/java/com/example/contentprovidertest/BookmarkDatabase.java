package com.example.contentprovidertest;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Bookmark.class}, version = 15, exportSchema = false)
public abstract class BookmarkDatabase extends RoomDatabase{
    public abstract BookmarkDao bookmarkDao();
}
