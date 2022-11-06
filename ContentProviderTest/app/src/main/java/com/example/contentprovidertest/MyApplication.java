package com.example.contentprovidertest;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
    public static final String TAG = "CHOCO-MyApplication";
    public static BookmarkDatabase mBookmarkDatabase;

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(TAG, "onCreate");
        mBookmarkDatabase = BookmarkDatabase.getInstance(getApplicationContext());
        //javaでこんなことやっていいかは不明　要検討 kotlinのコードだとsingletonなオブジェクトでやってた
    }
}
