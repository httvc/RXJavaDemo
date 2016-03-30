package com.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class FiltrateActivity extends AppCompatActivity {
    String TAG="FiltrateActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtrate);

        //filter
        filterMethod();

        //distinct
        distinctMethod();

        //elementAt
        elementAtMethod();

        //IgnoreElements
        /**
         * 不发射任何数据，只发射Observable的终止通知
         * IgnoreElements操作符抑制原始Observable发射的所有数据，
         * 只允许它的终止通知（onError或onCompleted）通过。
         *
         * 如果你不关心一个Observable发射的数据，
         * 但是希望在它完成时或遇到错误终止时收到通知，
         * 你可以对Observable使用ignoreElements操作符，
         * 它会确保永远不会调用观察者的onNext()方法。
         *
         *RxJava将这个操作符实现为ignoreElements。
         *Javadoc: ignoreElements())
         *ignoreElements默认不在任何特定的调度器上执行。
         */

        //first
        firstMethod();

        //last
        /**
         * 只发射最后一项（或者满足某个条件的最后一项）数据
         *
         *  last(Func1)) 这个版本的last也是接受一个谓词函数，
         *  返回一个发射原始Observable中满足条件的最后一项数据的Observable。
         *
         *lastOrDefault(T))  lastOrDefault与last类似，
         * 不同的是，如果原始Observable没有发射任何值，它发射你指定的默认值。
         *
         *lastOrDefault(T)) 这个版本的lastOrDefault可以接受一个谓词函数，
         * 如果有数据满足条件，返回的Observable就发射原始Observable满足条件的最后一项数据，
         * 否则发射默认值。
         *
         * last和lastOrDefault默认不在任何特定的调度器上执行。
         */
        Observable.just(1, 2, 3)
                .last()
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer item) {
                        Log.d(TAG,"Next: " + item);
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.d(TAG,"last: " + error.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        Log.d(TAG,"last complete.");
                    }
                });

    }

    private void firstMethod() {
        /**
         * 只发射第一项(或者满足条件的第一项)数据
         * 如果你只对Observable发射的第一项数据，
         * 或者满足某个条件的第一项数据感兴趣，你可以使用First操作符。
         *
         * first(Func1)) 传递一个谓词函数给first，然后发射这个函数判定为true的第一项数据。
         *
         * firstOrDefault(T)) firstOrDefault与first类似，
         * 但是在Observagle没有发射任何数据时发射一个你在参数中指定的默认值。
         *
         * firstOrDefault(T, Func1))传递一个谓词函数给firstOrDefault，
         * 然后发射这个函数判定为true的第一项数据，
         * 如果没有数据通过了谓词测试就发射一个默认值。
         *
         *takeFirst(Func1))  takeFirst与first类似，
         * 除了这一点：如果原始Observable没有发射任何满足条件的数据，
         * first会抛出一个NoSuchElementException，
         * takeFist会返回一个空的Observable（不调用onNext()但是会调用onCompleted）。
         *
         */
        Observable.just(1, 2, 3).first().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "first complete");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"first "+e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG,integer+"");
            }
        });
    }

    private void elementAtMethod() {
        /**
         * 只发射指定的项
         * ElementAt操作符获取原始Observable发射的数据序列指定索引位置的数据项，然后当做自己
         * 的唯一数据发射。
         * Rxjava将这个操作符实现为elementAt，给它传递一个基于0的索引值，它会发射原始Obserbable
         * 数据序列对应索引位置的值，如果你传递给elementAt的值为5，那么它会发射第六项的数据。
         * 如果你传递的是一个负数，或者原始Observable的数据项数小于index+1，将会抛出一个异常。
         *elementAt(int)
         * RxJava还实现了elementAtOrDefault操作符。与elementAt的区别是，如果索引值大于数据项数，
         * 它会发射一个默认值（通过额外的参数指定），而不是抛异常，如果你还是传递一个负数索引
         * 它还是会抛IndexOutOfBoundsException异常。 elementAtOrDefault(int,T)
         *
         * elementAt和elementAtOrDefault默认不在任何特定的调度器上执行。
         */
        Observable.just(1, 2, 3, 4, 5, 6).elementAt(4).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "elementAt complete");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"element "+e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG,integer+"");
            }
        });

        Observable.just(1,2,3,4,5,6,7).elementAtOrDefault(7,8).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG,"elementAtOrDefault complete");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"elementAtOrDefault "+e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG,integer+"");
            }
        });
    }

    private void distinctMethod() {
        /**
         * 过滤掉重复的数据项
         * Distinct的过滤规则是：只允许还没有发射过的数据项通过。
         * 在某些实现中，有一些变体允许你调整判定两个数据不同的标准。
         * 还有一些实现只比较一项数据和它的直接前驱，
         * 因此只会从序列中过滤掉连续重复的数据。
         */
        Observable.just(1, 2, 1, 2, 1, 3, 1, 4).distinct().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "distinct complete");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"distinct "+e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG,integer+"");
            }
        });
    }

    private void filterMethod() {
        /**
         * 只发射通过了谓词测试的数据项
         * Filter操作符使用你指定的一个谓词函数测试数据项，只有通过测试的数据才会被发射
         */
        Observable.just(1, 2, 3, 4, 5, 6).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return (integer<5);
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "Filter complete");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"FIlter "+e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG,integer+"");
            }
        });
    }
}
