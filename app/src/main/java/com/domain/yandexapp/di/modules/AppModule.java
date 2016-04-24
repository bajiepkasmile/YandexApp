package com.domain.yandexapp.di.modules;

import android.content.Context;

import com.domain.yandexapp.domain.DataManager;
import com.domain.yandexapp.domain.datasources.network.YandexService;
import com.domain.yandexapp.domain.datasources.storage.DbManager;
import com.domain.yandexapp.utils.NetworkUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = {
                NetworkModule.class,
                StorageModule.class
        }
)
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return context;
    }

    @Singleton
    @Provides
    DataManager provideDataManager(YandexService service, DbManager dbManager) {
        return new DataManager(service, dbManager);
    }

    @Singleton
    @Provides
    NetworkUtil provideNetworkUtil(Context context) {
        return new NetworkUtil(context);
    }

}
