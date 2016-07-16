package com.domain.yandexapp.mvp.presenters;

import com.domain.yandexapp.mvp.views.ArtistDetailsMvpView;

public interface ArtistDetailsMvpPresenter extends MvpPresenter<ArtistDetailsMvpView> {

    void initArtist(int position);

    void onLinkClick();

    void onHomeButtonPressed();

}
