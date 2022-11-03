package com.example.contentprovidertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "CHOCO";
    CompositeDisposable mDisposable = new CompositeDisposable();//lintに怒られる対策

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // content provider試しに触ってみようとした
        // browserのブックマークをdbとして扱いたいなとおもったけど 6.0以降で不可能に
        /*
        Cursor cursor = getContentResolver().query(
                UserDictionary.Words.CONTENT_URI,
        );
         */

        //Room を試してみる
        BookmarkDatabase db = Room.databaseBuilder(getApplicationContext(),
                    BookmarkDatabase.class, "test.db")
                .createFromAsset("database/bookmark.db")
                .build();

        BookmarkDao bookmarkDao = db.bookmarkDao();


        //rxjavaでの非同期処理
        mDisposable.add( //ライフサイクルのこと考慮したstreamにしてる？
                bookmarkDao.getAll()
                        .subscribeOn(Schedulers.io()) //mainスレッドでroomアクセスしようとすると怒られる
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(result ->{
                            Log.d(TAG, "result");
                            Log.d(TAG, result.get(result.size()-1).title);
                        })
        );
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mDisposable.dispose();
    }
}