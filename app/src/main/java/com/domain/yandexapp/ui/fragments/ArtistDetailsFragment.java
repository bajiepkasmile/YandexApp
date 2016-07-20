package com.domain.yandexapp.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.domain.yandexapp.App;
import com.domain.yandexapp.R;
import com.domain.yandexapp.model.Artist;
import com.domain.yandexapp.mvp.presenters.ArtistDetailsMvpPresenter;
import com.domain.yandexapp.mvp.views.ArtistDetailsMvpView;
import com.domain.yandexapp.ui.activities.MainActivity;
import com.domain.yandexapp.ui.interfaces.ArtistDetailsNavigator;
import com.domain.yandexapp.utils.Converter;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class ArtistDetailsFragment extends Fragment implements ArtistDetailsMvpView {

    public static final String TAG = "ArtistDetailsFragment";
    public static final String ARG_POSITION = "position";

    private ImageView ivCover;
    private TextView tvGenres;
    private TextView tvStatistics;
    private TextView tvLink;
    private TextView tvDescription;

    private ArtistDetailsNavigator navigator;

    @Inject
    ArtistDetailsMvpPresenter mvpPresenter;

    public static ArtistDetailsFragment newInstance(int position) {
        ArtistDetailsFragment fragment = new ArtistDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_artist_details, container, false);

        initViews(view);

        navigator = (ArtistDetailsNavigator) getActivity();
        mvpPresenter.initArtist(getPosition());
        mvpPresenter.onCreateMvpView(savedInstanceState);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
    public void showArtistDetails(Artist artist) {
        Picasso.with(getActivity())
                .load(artist.getBigCoverUrl())
                .memoryPolicy(MemoryPolicy.NO_STORE)
                .placeholder(R.drawable.cover_placeholder_big)
                .into(ivCover);

        getActivity().setTitle(artist.getName());

        if (artist.getGenres().length != 0) {
            tvGenres.setText(Converter.genresToString(artist.getGenres()));
        } else {
            tvGenres.setVisibility(View.GONE);
        }

        if (artist.getLink() != null) {
            tvLink.setText(artist.getLink());
        } else {
            tvLink.setVisibility(View.GONE);
        }

        tvStatistics.setText(
                Converter.albumsToString(artist.getAlbum()) +
                "   •   " +
                Converter.tracksToString(artist.getTracks()));
        tvDescription.setText(artist.getDescription());
    }

    @Override
    public void navigateToBrowser(String link) {
        navigator.navigateToBrowser(link);
    }

    private void initViews(View view) {
        ivCover = (ImageView) view.findViewById(R.id.iv_big_cover);
        tvGenres = (TextView) view.findViewById(R.id.tv_genres);
        tvStatistics = (TextView) view.findViewById(R.id.tv_statistics);
        tvLink = (TextView) view.findViewById(R.id.tv_link);
        tvDescription = (TextView) view.findViewById(R.id.tv_description);

        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        tvLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvpPresenter.onLinkClick();
            }
        });
    }

    private int getPosition() {
        return getArguments().getInt(ARG_POSITION, 0);
    }

}
