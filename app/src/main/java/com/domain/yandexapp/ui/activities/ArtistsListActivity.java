package com.domain.yandexapp.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.domain.yandexapp.R;
import com.domain.yandexapp.ui.fragments.ArtistDetailsFragment;
import com.domain.yandexapp.ui.interfaces.ArtistDetailsNavigator;
import com.domain.yandexapp.ui.interfaces.ArtistsListNavigator;
import com.domain.yandexapp.utils.AppConstants;

public class ArtistsListActivity extends AppCompatActivity implements ArtistsListNavigator, ArtistDetailsNavigator {

    private static final int POSITION_UNSELECTED = -1;

    private int position = POSITION_UNSELECTED;
    private boolean withDetails = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists_list);
        setTitle(R.string.artists);

        withDetails = findViewById(R.id.fl_artist_details_container) != null;
        if (savedInstanceState != null) {
            position = savedInstanceState.getInt(AppConstants.ARG_POSITION);
        }

        if (withDetails && (position != POSITION_UNSELECTED))
            showDetails();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(AppConstants.ARG_POSITION, position);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void navigateToArtistDetails(int position) {
        this.position = position;
        showDetails();
    }

    @Override
    public void navigateToBrowser(String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);
    }

    private void showDetails() {
        if (withDetails) {
            ArtistDetailsFragment fragment = ArtistDetailsFragment.newInstance(position);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_artist_details_container, fragment, ArtistDetailsFragment.TAG)
                    .commit();
        } else {
            Intent intent = new Intent(this, ArtistDetailsActivity.class);
            intent.putExtra(AppConstants.ARG_POSITION, position);
            startActivity(intent);
        }
    }

}
