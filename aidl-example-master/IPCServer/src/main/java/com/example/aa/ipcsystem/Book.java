package com.example.aa.ipcsystem;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 16-5-22.
 */
public class Book implements Parcelable {
    public int bookId;
    public String bookName;
    public Book(int pBookId,String pBookName){
        bookId = pBookId;
        bookName = pBookName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bookId);
        dest.writeString(bookName);
    }
    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>(){
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    private Book(Parcel source){
        bookId=source.readInt();
        bookName = source.readString();
    }

}
