package com.jingshuai.android.fregmentapp.service;



import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/1/27.
 */
public class Person implements Parcelable {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    private int gender;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.gender);
    }

    public Person() {
    }

    protected Person(Parcel in) {
        this.name = in.readString();
        this.gender = in.readInt();
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}