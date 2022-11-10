package com.example.contentprovidertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import com.example.contentprovidertest.databinding.ActivityMainBinding;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "CHOCO";
    CompositeDisposable mDisposable = new CompositeDisposable();//lintに怒られる対策

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //databindingのためのsetContentView
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //viewmodelを得る
        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // content provider試しに触ってみようとした
        // browserのブックマークをdbとして扱いたいなとおもったけど 6.0以降で不可能に
        /*
        Cursor cursor = getContentResolver().query(
                UserDictionary.Words.CONTENT_URI,
        );
         */

        /*
        //observeをviewmodelに登録して、viewにdatabindすれば結果が反映される
        binding.setViewModel(mainViewModel);
        mainViewModel.getBookmarks().observe(this, bookmarks -> {//abstract なonChangedメソッドの実装をラムダ表記
            // update UI layoutファイルのdata に、viewmodelを書かない場合このような書き方。 変化を受け取って値を書き換えてる
            //binding.set(bookmarks.get(0).title);
        });
         */

        //layoutファイルのdataに、viewmodelを書く場合に必要 上記のObserveをやらなくて済む
        binding.setLifecycleOwner(this);
        binding.setViewModel(mainViewModel);

        RecyclerView recyclerView = binding.myList;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        BookmarkListAdapter adapter = new BookmarkListAdapter(this, mainViewModel);
        recyclerView.setAdapter(adapter);

        //adapterには値の変更を通知してやるためにsubmitList関数を読んでやらないといけない //これはActivityにかいていていいのかな... bookmarksの個数が変わったらどうなるのだろう
        mainViewModel.getBookmarks().observe(this, bookmarks -> {
            adapter.submitList(bookmarks);
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mDisposable.dispose();
    }
}