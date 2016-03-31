package com.rxjavademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 原理方法
         */
        Observable<String> MyObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("hello,world!!!");
                        subscriber.onCompleted();
                    }
                });

        Subscriber<String> MySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "完成");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "出错");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, s);
            }
        };

        MyObservable.subscribe(MySubscriber);

        /**
         * 简便方法
         */
        Observable<String> observable = Observable.just("hahahahhaha");
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, s);
            }
        };
        observable.subscribe(onNextAction);

        /**
         * 更简便的方法
         * 将Observable和Subscribe连到一起
         */
        Observable.just("hehhehehehhe")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d(TAG, s);
                    }
                });

        /**
         * 使用lambda代码更简洁
         */
       /* Observable.just("whattttttt")
                .subscribe(s -> System.out.println(s));*/

        Observable.just("Hello,world!!!!")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "-Dan";
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, s);
            }
        });

        Observable.just("hello,world!!!")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, Integer.toString(integer));
            }
        });

        Observable.just("hello,world")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                }).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return Integer.toString(integer);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, s);
            }
        });


        Observable<String> stringObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("床前明月光,");
                subscriber.onNext("疑是地上霜.");
                subscriber.onNext("举头望明月,");
                subscriber.onNext("低头思故乡.");
                subscriber.onCompleted();
            }
        });

        Observable<String> justObservable = Observable.just("床前明月光,", "疑是地上霜.", "举头望明月,", "低头思故乡.");

        String[] poetry={"床前明月光,", "疑是地上霜.", "举头望明月,", "低头思故乡."};
        Observable<String> fromObservable = Observable.from(poetry);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void btnRxJava(View view) {
        Intent intent=new Intent(MainActivity.this,RxJavaDemoActivity.class);
        startActivity(intent);
    }

    public void btnRxChange(View view) {
        Intent intent=new Intent(MainActivity.this,RxChangeActivity.class);
        startActivity(intent);
    }

    public void btnRxFilter(View view) {
        Intent intent=new Intent(MainActivity.this,FiltrateActivity.class);
        startActivity(intent);
    }

    public void btnRxsyc(View view) {
        Intent intent=new Intent(MainActivity.this,SchedulerActivity.class);
        startActivity(intent);
    }
}
