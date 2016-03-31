package com.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.rxjavademo.bean.HomeImage;
import com.rxjavademo.retrofit.RestPool;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SchedulerActivity extends AppCompatActivity {
    String TAG="SchedulerActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
        //scheduler
        /**
         * 在不指定线程的情况下，RxJava遵循的是线程不变的原则，即：在那个线程调用subscribe()
         * 就在那个线程生产事件，在哪个线程生产事件，就在那个线程消费事件。如果要切换线程，
         * 就需要用到Scheduler(调度器)
         * Shceduler相当于现场控制器，通过它来指定每一段代码应该运行在什么样的线程中。
         * RxJava内置了几个Scheduler：
         * Schedulers.immediate():直接在当前线程运行，相当于不知道线程，这是默认的Scheduler。
         * Schedulers.newThread():总是启用新线程，并在新线程执行操作。
         * Schedulers.io()：I/O操作(读写文件，读写数据库，网络信息交互等)所使用的Scheduler。
         * 行为模式和newThread()差不多，区别在于io()的内部实现的是是用一个无数量上限的线程池，
         * 可以重用空闲的线程，因此多数情况下io()比newThread()效率更高。
         * Shedulers.computation():计算所使用的Scheduler。这个计算指的是cpu密集型计算，例如图形的计算
         * 这个Scheduler使用的固定的线程池，大小为cpu核数，不要把I/o操作放入，会浪费cpu的。
         * AndroidSchedulers.mainThread(),它指定的操作将在Android主线程运行。
         *
         * subscribeOn():指定subscribe()所发生的线程，即Observable.OnSubscribe被激活时所处的线程，
         * 或者叫做时间产生的线程。
         * observeOn():指定subscriber所运行在的线程。或者叫做时间消费的线程。
         */
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())//指定subscribe()发生在IO线程
                .observeOn(AndroidSchedulers.mainThread())//指定Subscriber的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, integer + "");
                    }
                });

        new RestPool().init().homeImageIcon("北京").enqueue(new Callback<HomeImage>() {
            @Override
            public void onResponse(Response<HomeImage> response, Retrofit retrofit) {
                if (response.body() != null) {
                    HomeImage homeImage = response.body();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        new RestPool().init().homeImageIcon1("北京")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeImage>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(HomeImage homeImage) {

                    }
                });
    }

}
