package com.rxjavademo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class RxChangeActivity extends AppCompatActivity {
    private String TAG="RxChangeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_change);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //buffer
        RxBuffer();

        //FlatMap
        /**
         * FlatMap将一个发射数据的Observable变换为多个Observables,
         * 然后将它们发射的数据合并后放进一个单独的Observable
         *
         * FlatMap操作符使用一个指定的函数对原始Observable发射的每一项数据执行变换操作，这个函数
         * 返回一个本身也发射数据的Observable，然后FlatMap合并这些Observables发射的数据，最后将合
         * 并后的结果当做它自己的数据序列发射。
         *这个方法是很有用的，例如，当你有一个这样的Observable，他发射一个数据序列，
         * 这些数据本身包含Observable成员或者可以变换为Observable，因此你可以创建一个新的Observable
         * 发射这些次级Observable发射的数据的完整集合。
         *
         * 注意:FlatMap对这些Observables发射的数据做的是合并(merge)操作，因此他们可能是交错的。
         * 在许多语言特定的实现中，还有一个操作符不会让变换后的Observables发射的数据交错，他按照
         * 严格的顺序发射这些数据，这个操作符通常被叫做ConcatMap或者类似的名字。
         */
        Observable.just(1,2,3,4,5,6).flatMap(new Func1<Integer, Observable<?>>() {
            @Override
            public Observable<?> call(Integer integer) {
                return Observable.just(integer);
            }
        }).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                Log.d(TAG,"flatMap complete");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                Log.d(TAG,o+"");
            }
        });
    }

    private void RxBuffer() {
        //变换操作

        //buffer
        /**
         * 定期收集Observable数据放进一个数据包裹，然后发射这些数据包裹，而不是一次发射一个值。
         * Buffer操作符将一个Observable变换为另一个，原来的Observable正常发射数据，变换产生的Observable
         * 发射这些数据的缓存集合。Buffer操作符在很多语言特定的实现中有很多中变体，他们在如何缓存这个问题上
         * 存在区别。
         * 注意：如果原来的Observable发射了一个onError通知，Buffer会立即传递这个通知，
         * 而不是首先发射缓存的数据，即使在这之前缓存中包含了原始Observable发射的数据。
         * buffer(count) 以列表(List)的形式发射非重叠的缓存，
         * 每一个缓存至多包含来自原始Observable的count项数据(最后发射的列表数可能少于count项)
         */
        Observable.range(1, 5).buffer(2).subscribe(new Subscriber<List<Integer>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "buffer completed");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"buffer "+e.getMessage());
            }

            @Override
            public void onNext(List<Integer> integers) {
                for (int i = 0; i <integers.size() ; i++) {//打断点调试看
                    Log.d(TAG,integers.get(i)+"");
                }
            }
        });

        /**
         * 剔除 每次删除skip个
         * buffer(count,skip)从原始Observable的第一项数据开始创建新的缓存，此后每次收到skip项数据，
         * 用count向数据填充缓存：开头的一项和后续的count-1项，它以列表(List)的形式发射缓存，
         * 取决于count和skip的值，这些缓存可能会有重叠部分（比如skip < count时），也可能会有间隙（比如skip > count时）。
         */

        Observable.range(1,5).buffer(4,3).subscribe(new Subscriber<List<Integer>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG,"buffer skip complete");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,e.getMessage());
            }

            @Override
            public void onNext(List<Integer> integers) {
                for (int i = 0; i <integers.size() ; i++) {
                    Log.d(TAG,integers.get(i)+"");
                }
            }
        });

        Observable.range(1,5).buffer(5,5).subscribe(new Subscriber<List<Integer>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG,"buffer skip complete");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,e.getMessage());
            }

            @Override
            public void onNext(List<Integer> integers) {
                for (int i = 0; i <integers.size() ; i++) {
                    Log.d(TAG,integers.get(i)+"");
                }
            }
        });

        Observable.range(1,5).buffer(5,6).subscribe(new Subscriber<List<Integer>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG,"buffer skip complete");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,e.getMessage());
            }

            @Override
            public void onNext(List<Integer> integers) {
                for (int i = 0; i <integers.size() ; i++) {
                    Log.d(TAG,integers.get(i)+"");
                }
            }
        });
    }

}
