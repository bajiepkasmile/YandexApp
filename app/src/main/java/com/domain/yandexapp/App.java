package com.domain.yandexapp;

import android.app.Application;
import android.content.Context;

import com.domain.yandexapp.di.components.AppComponent;
import com.domain.yandexapp.di.components.DaggerAppComponent;
import com.domain.yandexapp.di.modules.AppModule;

public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }

    private void initComponent() {
        component = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getComponent(Context context) {
        return ((App) context.getApplicationContext()).component;
    }

}
