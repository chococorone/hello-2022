package com.example.contentprovidertest;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface BookmarkDao {
    @Query("SELECT * FROM bookmarks")
    Single<List<Bookmark>> getAll();

    @Query("SELECT * FROM bookmarks WHERE id IN (:bookmarkIds)")
    List<Bookmark> loadAllByIds(int[] bookmarkIds);

    // TODO: 2022/11/03  前方一致で検索させたい
    @Query("SELECT * FROM bookmarks WHERE title LIKE :word LIMIT 1")
    Bookmark findByTitle(String word);


}
