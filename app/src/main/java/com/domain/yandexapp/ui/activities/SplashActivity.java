package com.domain.yandexapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.domain.yandexapp.App;
import com.domain.yandexapp.domain.interactors.LoadArtistsInteractor;
import com.domain.yandexapp.domain.interactors.listeners.OnArtistsLoadListener;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity implements OnArtistsLoadListener {

    @Inject
    LoadArtistsInteractor loadInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getComponent(this).inject(this);

        loadInteractor.setListener(this);
        loadInteractor.execute();
    }

    @Override
    public void onArtistsLoadingFinished() {
        loadInteractor.removeListener();
        startActivity(new Intent(this, ArtistsListActivity.class));
        finish();
    }

}
