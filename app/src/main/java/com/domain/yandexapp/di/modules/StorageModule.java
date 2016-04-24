package com.domain.yandexapp.di.modules;

import android.content.Context;

import com.domain.yandexapp.domain.datasources.storage.DbHelper;
import com.domain.yandexapp.domain.datasources.storage.DbManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    @Provides
    DbHelper provideDbHelper(Context context) {
        return new DbHelper(context);
    }

    @Singleton
    @Provides
    DbManager provideDbManager(Context context, DbHelper dbHelper) {
        return new DbManager(context, dbHelper);
    }

}
