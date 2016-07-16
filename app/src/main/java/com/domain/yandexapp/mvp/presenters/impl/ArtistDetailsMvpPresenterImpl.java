package com.domain.yandexapp.mvp.presenters.impl;

import android.os.Bundle;

import com.domain.yandexapp.domain.DataManager;
import com.domain.yandexapp.model.Artist;
import com.domain.yandexapp.mvp.presenters.ArtistDetailsMvpPresenter;
import com.domain.yandexapp.mvp.presenters.BaseMvpPresenter;
import com.domain.yandexapp.mvp.views.ArtistDetailsMvpView;

public class ArtistDetailsMvpPresenterImpl extends BaseMvpPresenter<ArtistDetailsMvpView>
        implements ArtistDetailsMvpPresenter {

    private Artist artist;
    private DataManager dataManager;

    public ArtistDetailsMvpPresenterImpl(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void onCreateMvpView(Bundle savedInstanceState) {
    }

    @Override
    public void onResumeMvpView() {
    }

    @Override
    public void onPauseMvpView() {
    }

    @Override
    public void initArtist(int position) {
        artist = dataManager.getArtists().get(position);
        mvpView.showArtistDetails(artist);
    }

    @Override
    public void onLinkClick() {
        mvpView.navigateToBrowser(artist.getLink());
    }

    @Override
    public void onHomeButtonPressed() {
        mvpView.navigateToArtistsList();
    }

}
