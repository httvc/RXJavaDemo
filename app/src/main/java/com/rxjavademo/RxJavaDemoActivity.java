package com.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

public class RxJavaDemoActivity extends AppCompatActivity {
    private String TAG="RxJavaDemoActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_demo);
        //create()方法
        reCreate();

        //Defer
        /**
         * 直到有观察者订阅才创建Observable，并且为每个观察者创建一个新的Observable
         */

        //from
        RxFrom();

        //just
        RxJust();

        //range
        RxRange();

        //repeat
        /**
         * 创建一个发射特定数量重复多次的Observable
         * repeat重复的发射数据，某些实现允许你重复的发射某个数据序列，还有一些允许你限制重复的次数。
         * RxJava将这个操作符实现为repeat方法，他不是创建一个Observable，而是重复发射原始Observable的
         * 数据序列，这个序列或者是无限的，或者通过repeat(n)指定重复次数。
         *
         * repeat操作符默认在trampoline调度器上执行，有一个变体可以通过可选参数指定Scheduler。
         */

        //start
        /**
         * 返回一个Observable，它发射一个类似于函数声明的值
         * Start操作符的多种RxJava实现都属于可选的rxjava-async模块。

         *rxjava-async模块包含start操作符，它接受一个函数作为参数，
         *调用这个函数获取一个值，然后返回一个会发射这个值给后续观察者的Observable。

         *注意：这个函数只会被执行一次，即使多个观察者订阅这个返回的Observable。
         */

        //timer
        /**
         *创建一个Observable，它在一个给定的延迟后发射一个特殊的值。
         * Timer操作符创建一个在给定的时间段之后返回一个特殊值的Observable。
         RxJava将这个操作符实现为timer函数。
         timer返回一个Observable，它在延迟一段给定的时间后发射一个简单的数字0。
         timer操作符默认在computation调度器上执行。有一个变体可以通过可选参数指定Scheduler。
         */
    }

    private void RxRange() {
        /**
         * 创建一个发射特定整数序列的Observable
         * Range操作符发射一个范围的有序整数序列，你可以指定范围的起始和长度。
         *RxJava将这个操作符实现为range函数，它接收两个参数，一个范围的起始值，一个是范围的数据的数目，
         * 如果你将第二个参数设为0，将导致Observable不发射任何数据（如果设置为负数，会抛异常）。
         * range默认不在任何特定的调度器上执行。有一个变体可以通过可选参数指定Scheduler。
         */
        Observable.range(4, 10).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "range complete");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"range "+e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG,integer+"");
            }
        });
    }

    private void RxJust() {
        /**
         *创建一个发射指定值的Observable，Just将单个数据转换为发射那个数据的Observable。
         *
         *Just类似于From，但是From会将数组或Iterable的素具取出然后逐个发射，
         * 而Just只是简单的原样发射，将数组或Iterable当做单个数据。
         *
         * 注意：如果你传递null给Just，它会返回一个发射null值的Observable。
         * 不要误认为它会返回一个空Observable（完全不发射任何数据的Observable），
         * 如果需要空Observable你应该使用Empty操作符。
         */
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "just Complete");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"just "+e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG,integer+"");
            }
        });
    }

    private void RxFrom() {
        /**
         * 将其他种类的对象和数据类型转换为Observable
         * 当你使用Observable时，如果你要处理的数据都可以转换成展现为Observables，
         * 而不是需要混合使用Observables和其他类型的数据，会非常方便。
         * 这让你在数据流的整个生命周期中，可以使用一组统一的操作符来管理他们。
         *
         * 在RxJava中，from操作符可以转换Future、Iterable和数组。对于Iterable和数组，
         * 产生的Observable会发射Iter或数组的每一项数据。
         */
        Integer[] items={0,1,2,3,4,5};
        Observable<Integer> myObservable=Observable.from(items);

        myObservable.subscribe(new Action1<Integer>() {
                                   @Override
                                   public void call(Integer integer) {
                                       Log.d(TAG, integer + "");
                                   }
                               },
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d(TAG, "from "+throwable.getMessage());
                    }
                },
                new Action0() {
                    @Override
                    public void call() {
                        Log.d(TAG,"from Complete");
                    }
                });
    }

    private void reCreate() {
        /**
         * Create操作符从头开始创建一个Observable，
         * 给这个操作符传递一个接受观察者作为参数的函数，
         * 编写这个函数让它的行为表现为一个Observable--恰当的调用观察者的onNext，onError和onCompleted方法。
         *一个形式正确的有限Observable必须尝试调用观察者的onCompleted正好一次或者它的onError正好一次，
         *而且此后不能再调用观察者的任何其它方法。
         */
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> observer) {
                try {
                    // 建议你在传递给create方法的函数中检查观察者的isUnsubscribed状态，
                    // 以便在没有观察者的时候，让你的Observable停止发射数据或者做昂贵的运算。
                    if (!observer.isUnsubscribed()) {
                        for (int i = 0; i < 5; i++) {
                            observer.onNext(i);
                        }
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "create complete.");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"create "+e.getMessage());
            }

            @Override
            public void onNext(Integer item) {
                Log.d(TAG,item+"");
            }
        });
    }
}
