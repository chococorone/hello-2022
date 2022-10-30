package com.example.rxjavatest;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("search") //baseurl以降のメソッドを記載
    Single<Response> getData(@Query("zipcode") String code); //Single というObservableを返す

    /*
    @GET("search/{hoge}")
    Single<Response> getData(@Path("hoge") String hoge, //メソッドのpathを指定する場合
            @Query("zipcode") String code);
     */


}
