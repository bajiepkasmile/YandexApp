package com.domain.yandexapp.mvp.presenters;

import android.os.Bundle;

public interface MvpPresenter<T> {

    void attachMvpView(T mvpView);

    void detachMvpView();

    void onCreateMvpView(Bundle savedInstanceState);

    void onResumeMvpView();

    void onPauseMvpView();

}
