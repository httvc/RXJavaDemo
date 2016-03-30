package com.rxjavademo.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Android Studio
 * Project：MatchLayout
 * Author：Jiafujie
 * Email：jfjie2013@163.com
 * Date：2016/2/25.
 */
public class st implements Parcelable {
    private final String name;
    private final Integer num;

    public st(String name, Integer num) {
        this.name = name;
        this.num = num;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeValue(this.num);
    }

    private st(Parcel in) {
        this.name = in.readString();
        this.num = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<st> CREATOR = new Creator<st>() {
        public st createFromParcel(Parcel source) {
            return new st(source);
        }

        public st[] newArray(int size) {
            return new st[size];
        }
    };


}
