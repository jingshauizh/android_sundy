// IBookManager.aidl
package com.example.aa.ipcsystem;

// Declare any non-default types here with import statements
import com.example.aa.ipcsystem.Book;
import com.example.aa.ipcsystem.INewBookArriveListener;
interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(INewBookArriveListener listener);
    void unRegisterListener(INewBookArriveListener listener);
}
