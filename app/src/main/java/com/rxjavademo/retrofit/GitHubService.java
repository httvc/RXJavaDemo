package com.rxjavademo.retrofit;


import com.rxjavademo.bean.HomeImage;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Android Studio
 * Project：MatchLayout
 * Email：jfjie2013@163.com
 * Date：2016/1/25.
 */
public interface GitHubService {
    @GET("system/main")
    Call<HomeImage> homeImageIcon(@Query("city") String chooseCity);

    //用rxJava
    @GET("system/main")
    Observable<HomeImage> homeImageIcon1(@Query("city") String chooseCity);
}
