package com.domain.yandexapp.mvp.views;

import com.domain.yandexapp.model.Artist;

public interface ArtistDetailsMvpView {

    void showArtistDetails(Artist artist);

    void navigateToBrowser(String link);

    void navigateToArtistsList();

}
