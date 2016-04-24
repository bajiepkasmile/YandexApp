package com.domain.yandexapp.di.modules;

import com.domain.yandexapp.domain.DataManager;
import com.domain.yandexapp.domain.interactors.UpdateArtistsInteractor;
import com.domain.yandexapp.mvp.presenters.ArtistDetailsMvpPresenter;
import com.domain.yandexapp.mvp.presenters.ArtistsListMvpPresenter;
import com.domain.yandexapp.mvp.presenters.impl.ArtistDetailsMvpPresenterImpl;
import com.domain.yandexapp.mvp.presenters.impl.ArtistsListMvpPresenterImpl;
import com.domain.yandexapp.utils.NetworkUtil;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentersModule {

    @Provides
    ArtistsListMvpPresenter provideArtistsListMvpPresenter(DataManager dataManager,
                                                           NetworkUtil networkUtil,
                                                           UpdateArtistsInteractor interactor) {
        return new ArtistsListMvpPresenterImpl(dataManager, networkUtil, interactor);
    }

    @Provides
    ArtistDetailsMvpPresenter provideArtistDetailsMvpPresenter(DataManager dataManager) {
        return new ArtistDetailsMvpPresenterImpl(dataManager);
    }

}
