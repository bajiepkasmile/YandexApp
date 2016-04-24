package com.domain.yandexapp.domain.interactors;

import android.os.AsyncTask;

import com.domain.yandexapp.domain.DataManager;
import com.domain.yandexapp.domain.interactors.listeners.OnArtistsLoadListener;

public class LoadArtistsInteractor extends BaseInteractor<OnArtistsLoadListener, LoadArtistsInteractor.LoadTask> {

    private DataManager dataManager;

    public LoadArtistsInteractor(DataManager dataManager) {
        this.dataManager = dataManager;
        task = new LoadTask();
    }

    @Override
    public void execute() {
        if (task.getStatus() != AsyncTask.Status.RUNNING) {
            task = new LoadTask();
            task.execute();
        }
    }

    class LoadTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            dataManager.loadArtists();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            listener.onArtistsLoadingFinished();
        }
    }

}
