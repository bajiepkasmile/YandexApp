package com.domain.yandexapp.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.domain.yandexapp.R;
import com.domain.yandexapp.ui.fragments.ArtistDetailsFragment;
import com.domain.yandexapp.ui.interfaces.ArtistDetailsNavigator;
import com.domain.yandexapp.utils.AppConstants;
import com.domain.yandexapp.utils.Tools;

public class ArtistDetailsActivity extends AppCompatActivity implements ArtistDetailsNavigator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isScreenLarge = Tools.isScreenLarge(getResources());
        boolean isScreenLandscape = Tools.isScreenLandscape(getResources());

        //Вернуться к предыдущей activity, если достаточно места для двух фрагментов
        if (isScreenLarge && isScreenLandscape) {
            finish();
        }

        setContentView(R.layout.activity_artist_details);
        displayHomeButton();

        if (savedInstanceState == null) {
            ArtistDetailsFragment fragment = ArtistDetailsFragment.newInstance(getPosition());
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.nsv_artist_details_container, fragment, ArtistDetailsFragment.TAG)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void navigateToBrowser(String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);
    }

    private void displayHomeButton() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private int getPosition() {
        return getIntent().getIntExtra(AppConstants.ARG_POSITION, 0);
    }

}
