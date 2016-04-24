package com.domain.yandexapp.domain.datasources.network;

import com.domain.yandexapp.model.Artist;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface YandexService {

    @GET("artists.json")
    Call<ArrayList<Artist>> getArtists();

}
