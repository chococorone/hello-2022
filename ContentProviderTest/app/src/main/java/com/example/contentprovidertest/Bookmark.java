package com.example.contentprovidertest;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

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

    public Boolean equals(Bookmark b){
        //todo さすがになにかいい書き方があるはず
        //kotlinならこんな実装わざわざしなくっていいのに...
        return id == b.id && Objects.equals(title, b.title) && Objects.equals(url, b.url) && Objects.equals(image, b.image);
    }
}
