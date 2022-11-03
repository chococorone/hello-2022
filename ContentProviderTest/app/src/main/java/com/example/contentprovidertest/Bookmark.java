package com.example.contentprovidertest;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookmarks")
public class Bookmark {
    @PrimaryKey
    @Nullable//用意したdbがnullableになってたからそれに合わせた。primarykeyだしnullableじゃない方がよいと思う
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "url")
    public String url;

    @ColumnInfo(name = "image")
    public String image;
}
