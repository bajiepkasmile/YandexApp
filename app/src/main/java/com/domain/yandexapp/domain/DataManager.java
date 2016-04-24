package com.domain.yandexapp.domain;

import com.domain.yandexapp.domain.datasources.network.YandexService;
import com.domain.yandexapp.domain.datasources.storage.DbManager;
import com.domain.yandexapp.model.Artist;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Response;

public class DataManager {

    private YandexService service;
    private DbManager dbManager;

    private ArrayList<Artist> artists;

    public DataManager(YandexService service, DbManager dbManager) {
        this.service = service;
        this.dbManager = dbManager;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public boolean updateArtists() throws IOException {
        Response<ArrayList<Artist>> response = service.getArtists().execute();
        if (response.isSuccessful()) {
            artists = service.getArtists().execute().body();
            return true;
        } else {
            return false;
        }
    }

    public void saveArtists() {
        dbManager.saveArtists(artists);
    }

    public void loadArtists() {
        artists = dbManager.loadArtists();
    }

}
