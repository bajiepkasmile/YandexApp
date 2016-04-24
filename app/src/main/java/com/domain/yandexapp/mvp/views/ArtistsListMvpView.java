package com.domain.yandexapp.mvp.views;

import com.domain.yandexapp.model.Artist;

import java.util.ArrayList;

public interface ArtistsListMvpView {

    void showArtistsList(ArrayList<Artist> artists);

    void showProgress(boolean withAnimation);

    void hideProgress(boolean withAnimation);

    void showSuccessMessage();

    void showFailureMessage();

    void showNoNetworkMessage();

    void navigateToArtistDetails(int position);

}
