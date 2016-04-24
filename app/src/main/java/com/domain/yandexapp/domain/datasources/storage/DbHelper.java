package com.domain.yandexapp.domain.datasources.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, DbConstants.DB_NAME, null, DbConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbConstants.SQL_CREATE_TABLE_ARTISTS);
        db.execSQL(DbConstants.SQL_CREATE_TABLE_GENRES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DbConstants.SQL_DROP_TABLE_ARTISTS);
        db.execSQL(DbConstants.SQL_DROP_TABLE_GENRES);
        onCreate(db);
    }

}
