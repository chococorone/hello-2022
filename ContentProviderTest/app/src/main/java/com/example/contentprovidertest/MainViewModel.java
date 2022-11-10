package com.example.contentprovidertest;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {
    public static final String TAG = "CHOCO:viewModel";
    protected CompositeDisposable mDisposable = new CompositeDisposable();

    private MutableLiveData<List<Bookmark>> mBookmarks;
    private MutableLiveData<Integer> mCount = new MutableLiveData<>(0);

    @Override
    public void onCleared(){
        super.onCleared();
        mDisposable.dispose();
    }

    public LiveData<List<Bookmark>> getBookmarks(){
        if ( mBookmarks == null ){
            mBookmarks = new MutableLiveData<List<Bookmark>>();
            loadBookmarks();
        }
        return mBookmarks;
    }

    public LiveData<Integer> getCount(){
        return mCount;
    }

    private void loadBookmarks(){
        BookmarkDao bookmarkDao = MyApplication.mBookmarkDatabase.bookmarkDao();

        //rxjavaでの非同期処理
        mDisposable.add(
            bookmarkDao.getAll()
                    .subscribeOn(Schedulers.io()) //mainスレッドでroomアクセスしようとすると怒られる
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result ->{
                        Log.d(TAG, "result size =" + result.size());
                        mBookmarks.setValue(result);//MutableDataへと値を格納する
                    })
        );
    }

    public void onClick(){ //textviewのclickのたびに、list内の見せる値を変更
        Log.d(TAG, "onClick");
        //requireNonNullはlintに怒られるから書いた kotlinだとオプショナル変数(mCount?) でとれると思うからシンプル
        int count = Objects.requireNonNull(mCount.getValue());

        count++; //押された回数
        count = count < mBookmarks.getValue().size() ? count : 0;
        Log.d(TAG, "count = " + count);

        //kotlinだとgetter,setter省略して書けるからシンプル
        mCount.setValue(count);
    }

    public void onClickItem() {
        Log.d(TAG, "onClickItem");

    }

}
