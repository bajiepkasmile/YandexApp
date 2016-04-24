package com.domain.yandexapp.mvp.presenters;

import com.domain.yandexapp.mvp.views.ArtistsListMvpView;

public interface ArtistsListMvpPresenter extends MvpPresenter<ArtistsListMvpView> {

    void onItemClick(int position);

    void updateArtistsList();

}
