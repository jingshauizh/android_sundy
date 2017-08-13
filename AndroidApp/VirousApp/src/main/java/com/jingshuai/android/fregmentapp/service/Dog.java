package com.jingshuai.android.fregmentapp.service;



import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/1/27.
 */
public class Dog implements Parcelable {

    public int getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int gender;
    private String name;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.gender);
        dest.writeString(this.name);
    }

    public Dog() {
    }

    protected Dog(Parcel in) {
        this.gender = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Dog> CREATOR = new Parcelable.Creator<Dog>() {
        public Dog createFromParcel(Parcel source) {
            return new Dog(source);
        }

        public Dog[] newArray(int size) {
            return new Dog[size];
        }
    };
}
