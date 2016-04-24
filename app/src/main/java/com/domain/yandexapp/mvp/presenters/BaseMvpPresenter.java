package com.domain.yandexapp.mvp.presenters;

public abstract class BaseMvpPresenter<T> implements MvpPresenter<T> {

    protected T mvpView;

    public void attachMvpView(T mvpView) {
        this.mvpView = mvpView;
    }

    public void detachMvpView() {
        mvpView = null;
    }

}
