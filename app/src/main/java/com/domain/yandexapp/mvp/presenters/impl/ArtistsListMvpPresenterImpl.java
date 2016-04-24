package com.domain.yandexapp.mvp.presenters.impl;

import android.os.Bundle;

import com.domain.yandexapp.domain.DataManager;
import com.domain.yandexapp.domain.interactors.UpdateArtistsInteractor;
import com.domain.yandexapp.domain.interactors.listeners.OnArtistsUpdateListener;
import com.domain.yandexapp.mvp.presenters.ArtistsListMvpPresenter;
import com.domain.yandexapp.mvp.presenters.BaseMvpPresenter;
import com.domain.yandexapp.mvp.views.ArtistsListMvpView;
import com.domain.yandexapp.utils.NetworkUtil;

public class ArtistsListMvpPresenterImpl extends BaseMvpPresenter<ArtistsListMvpView>
        implements ArtistsListMvpPresenter, OnArtistsUpdateListener {

    private DataManager dataManager;
    private NetworkUtil networkUtil;
    private UpdateArtistsInteractor updateInteractor;

    public ArtistsListMvpPresenterImpl(DataManager dataManager,
                                       NetworkUtil networkUtil,
                                       UpdateArtistsInteractor updateInteractor) {
        this.dataManager = dataManager;
        this.networkUtil = networkUtil;
        this.updateInteractor = updateInteractor;
    }

    @Override
    public void onCreateMvpView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            updateArtistsList();
        }
    }

    @Override
    public void onResumeMvpView() {
        updateInteractor.setListener(this);

        mvpView.showArtistsList(dataManager.getArtists());
        if (updateInteractor.isRunning()) {
            mvpView.showProgress(false);
        } else {
            mvpView.hideProgress(false);
        }
    }

    @Override
    public void onPauseMvpView() {
        updateInteractor.removeListener();
    }

    @Override
    public void onItemClick(int position) {
        mvpView.navigateToArtistDetails(position);
    }

    @Override
    public void updateArtistsList() {
        if (networkUtil.isNetworkAvailable()) {
            updateInteractor.execute();
            mvpView.showProgress(true);
        } else {
            mvpView.showNoNetworkMessage();
        }
    }

    @Override
    public void onArtistsUpdatingSuccess() {
        mvpView.hideProgress(true);
        mvpView.showSuccessMessage();
        mvpView.showArtistsList(dataManager.getArtists());
    }

    @Override
    public void onArtistsUpdatingFailure() {
        mvpView.hideProgress(true);
        mvpView.showFailureMessage();
    }

}
