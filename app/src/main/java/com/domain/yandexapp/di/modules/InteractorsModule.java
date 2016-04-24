package com.domain.yandexapp.di.modules;

import com.domain.yandexapp.domain.DataManager;
import com.domain.yandexapp.domain.interactors.LoadArtistsInteractor;
import com.domain.yandexapp.domain.interactors.UpdateArtistsInteractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = AppModule.class
)
public class InteractorsModule {

    @Singleton
    @Provides
    UpdateArtistsInteractor provideUpdateInteractor(DataManager dataManager) {
        return new UpdateArtistsInteractor(dataManager);
    }

    @Singleton
    @Provides
    LoadArtistsInteractor provideLoadInteractor(DataManager dataManager) {
        return new LoadArtistsInteractor(dataManager);
    }

}
