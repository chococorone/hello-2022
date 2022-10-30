package com.example.rxjavatest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "CHOCO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate before");
        testRxJava();
        Log.d(TAG, "onCreate after");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    void testRxJava(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://zipcloud.ibsnet.co.jp/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        retrofit.create(ApiInterface.class).getData("1000000") //rxのObservableとしてApiInterfaceのgetData
                .subscribeOn(Schedulers.io()) //ここ以降の処理はioスレッドで実行される
                .map(result -> { //データの加工を行うmap
                    Log.d(TAG, "map on stream"); //ioスレッドでログ
                    return result; //何もせずに返す
                })
                .filter(result -> { //データのフィルタを行うfilter
                    Log.d(TAG, "filter on stream"); //ioスレッドでログ
                    return result != null; // nullではない場合はこれ以降を流れる
                })
                .observeOn(AndroidSchedulers.mainThread()) //ここ以降の処理はmainスレッド
                .subscribe(result -> { // onSuccessの実装
                    Log.d(TAG, "result"); //mainスレッドでログ
                    Log.d(TAG, result.getResults().get(0).getAddress1());
                }, Throwable::printStackTrace); // onErrorの実装

    }
}