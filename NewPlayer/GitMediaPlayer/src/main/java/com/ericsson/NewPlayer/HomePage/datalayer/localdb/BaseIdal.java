package com.ericsson.NewPlayer.HomePage.datalayer.localdb;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by eqruvvz on 1/19/2017.
 */
public class BaseIdal {
    protected final SQLiteDatabase mSQLiteDatabase;
    public BaseIdal(SQLiteDatabase database) {
        mSQLiteDatabase = database;
    }
}
