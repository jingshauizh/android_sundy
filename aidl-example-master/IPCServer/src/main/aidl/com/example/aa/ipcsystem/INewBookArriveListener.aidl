// INewBookArriveListener.aidl
package com.example.aa.ipcsystem;

// Declare any non-default types here with import statements
import com.example.aa.ipcsystem.Book;
interface INewBookArriveListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onNewBookArrived(in Book newBook);
}
