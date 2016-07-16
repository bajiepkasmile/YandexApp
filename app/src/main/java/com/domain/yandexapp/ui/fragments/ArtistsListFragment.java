package com.domain.yandexapp.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.domain.yandexapp.App;
import com.domain.yandexapp.R;
import com.domain.yandexapp.utils.ToastManager;
import com.domain.yandexapp.model.Artist;
import com.domain.yandexapp.mvp.presenters.ArtistsListMvpPresenter;
import com.domain.yandexapp.mvp.views.ArtistsListMvpView;
import com.domain.yandexapp.ui.adapters.ArtistsListAdapter;
import com.domain.yandexapp.ui.interfaces.ArtistsListNavigator;
import com.domain.yandexapp.ui.interfaces.OnItemClickListener;
import com.domain.yandexapp.utils.animation.FabAnimation;
import com.domain.yandexapp.utils.animation.UpdatingBarAnimation;

import java.util.ArrayList;

import javax.inject.Inject;

public class ArtistsListFragment extends Fragment implements ArtistsListMvpView, OnItemClickListener {

    public static final String TAG = "ArtistListFragment";

    private RecyclerView rvArtistsList;
    private FloatingActionButton fab;
    private LinearLayout updatingBar;

    private ArtistsListAdapter adapter;
    private ArtistsListNavigator navigator;

    @Inject
    ArtistsListMvpPresenter mvpPresenter;

    public static ArtistsListFragment newInstance() {
        return new ArtistsListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        App.getComponent(getActivity()).inject(this);
        mvpPresenter.attachMvpView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artists_list, container, false);

        initViews(view);

        navigator = (ArtistsListNavigator) getActivity();
        mvpPresenter.onCreateMvpView(savedInstanceState);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mvpPresenter.onResumeMvpView();
    }

    @Override
    public void onPause() {
        super.onPause();
        mvpPresenter.onPauseMvpView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mvpPresenter.detachMvpView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void showArtistsList(ArrayList<Artist> artists) {
        if (adapter == null) {
            adapter = new ArtistsListAdapter(getActivity(), artists, this);
            rvArtistsList.setAdapter(adapter);
        } else {
            adapter.setData(artists);
        }
    }

    @Override
    public void showProgress(boolean withAnimation) {
        if (withAnimation) {
            FabAnimation.animateOut(fab);
            UpdatingBarAnimation.animateIn(updatingBar);
        } else {
            updatingBar.setVisibility(View.VISIBLE);
            fab.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideProgress(boolean withAnimation) {
        if (withAnimation) {
            FabAnimation.animateIn(fab);
            UpdatingBarAnimation.animateOut(updatingBar);
        } else {
            updatingBar.setVisibility(View.GONE);
            fab.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showSuccessMessage() {
        ToastManager.showToast(getActivity(), R.string.message_updated, Toast.LENGTH_SHORT);
    }

    @Override
    public void showFailureMessage() {
        ToastManager.showToast(getActivity(), R.string.message_updating_failure, Toast.LENGTH_LONG);
    }

    @Override
    public void showNoNetworkMessage() {
        ToastManager.showToast(getActivity(), R.string.message_no_network, Toast.LENGTH_SHORT);
    }

    @Override
    public void navigateToArtistDetails(int position) {
        navigator.navigateToArtistDetails(position);
    }

    @Override
    public void onItemClick(int position) {
        mvpPresenter.onItemClick(position);
    }

    private void initViews(View view) {
        rvArtistsList = (RecyclerView) view.findViewById(R.id.rv_artists_list);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        updatingBar = (LinearLayout) view.findViewById(R.id.ll_updating_bar);

        setupToolbar(view);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvpPresenter.updateArtistsList();
            }
        });
    }

    private void setupToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        getActivity().setTitle(R.string.artists);
    }

}
