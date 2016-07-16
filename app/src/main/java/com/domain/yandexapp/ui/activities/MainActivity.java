package com.domain.yandexapp.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.domain.yandexapp.App;
import com.domain.yandexapp.R;
import com.domain.yandexapp.domain.interactors.LoadArtistsInteractor;
import com.domain.yandexapp.domain.interactors.listeners.OnArtistsLoadListener;
import com.domain.yandexapp.ui.fragments.ArtistDetailsFragment;
import com.domain.yandexapp.ui.interfaces.ArtistDetailsNavigator;
import com.domain.yandexapp.ui.interfaces.ArtistsListNavigator;
import com.domain.yandexapp.utils.AppConstants;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity
        implements OnArtistsLoadListener, ArtistsListNavigator, ArtistDetailsNavigator {

    private static final int POSITION_UNSELECTED = -1;

    private int position = POSITION_UNSELECTED;

    @Inject
    LoadArtistsInteractor loadInteractor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent(this).inject(this);

        if (savedInstanceState == null) {
            loadInteractor.setListener(this);
            loadInteractor.execute();
        } else {
            if (!loadInteractor.isRunning()) {
                setContentView(R.layout.main);
                position = savedInstanceState.getInt(AppConstants.ARG_POSITION);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(AppConstants.ARG_POSITION, position);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onArtistsLoadingFinished() {
        loadInteractor.removeListener();setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.main);
    }

    @Override
    public void navigateToBrowser(String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);
    }

    @Override
    public void navigateToArtistsList() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void navigateToArtistDetails(int position) {
        this.position = position;
        ArtistDetailsFragment fragment = ArtistDetailsFragment.newInstance(position);
        getSupportFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, fragment, ArtistDetailsFragment.TAG)
                .addToBackStack(ArtistDetailsFragment.TAG)
                .commit();
    }

}
