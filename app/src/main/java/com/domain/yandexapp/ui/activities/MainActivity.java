package com.domain.yandexapp.ui.activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.domain.yandexapp.App;
import com.domain.yandexapp.R;
import com.domain.yandexapp.domain.interactors.LoadArtistsInteractor;
import com.domain.yandexapp.domain.interactors.listeners.OnArtistsLoadListener;
import com.domain.yandexapp.ui.fragments.AboutFragment;
import com.domain.yandexapp.ui.fragments.ArtistDetailsFragment;
import com.domain.yandexapp.ui.fragments.ArtistsListFragment;
import com.domain.yandexapp.ui.fragments.FeedbackFragment;
import com.domain.yandexapp.ui.interfaces.ArtistDetailsNavigator;
import com.domain.yandexapp.ui.interfaces.ArtistsListNavigator;
import com.domain.yandexapp.domain.recievers.HeadsetReciever;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity
        implements OnArtistsLoadListener, ArtistsListNavigator, ArtistDetailsNavigator {

    HeadsetReciever reciever;

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
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerHeadsetReciever();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(reciever);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            replaceFragment(R.id.fl_fragment_container, ArtistsListFragment.newInstance(), ArtistsListFragment.TAG);
            return true;
        } else if (id == R.id.menu_feedback) {
            replaceFragment(R.id.fl_fragment_container, FeedbackFragment.newInstance(), FeedbackFragment.TAG);
            return true;
        } else if (id == R.id.menu_about) {
            replaceFragment(R.id.fl_fragment_container, AboutFragment.newInstance(), AboutFragment.TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onArtistsLoadingFinished() {
        loadInteractor.removeListener();
        setContentView(R.layout.main);
        replaceFragment(R.id.fl_fragment_container, ArtistsListFragment.newInstance(), ArtistsListFragment.TAG);
    }

    @Override
    public void navigateToBrowser(String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);
    }

    @Override
    public void navigateToArtistDetails(int position) {
        replaceFragment(R.id.fl_fragment_container, ArtistDetailsFragment.newInstance(position), ArtistDetailsFragment.TAG);
    }

    private void registerHeadsetReciever() {
        reciever = new HeadsetReciever(getApplicationContext());
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(reciever, filter);
    }

    private void replaceFragment(@IdRes int container, Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(container, fragment, tag)
                .commit();
    }

}
