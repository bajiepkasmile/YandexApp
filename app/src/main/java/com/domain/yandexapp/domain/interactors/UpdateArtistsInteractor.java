package com.domain.yandexapp.domain.interactors;

import android.os.AsyncTask;

import com.domain.yandexapp.domain.DataManager;
import com.domain.yandexapp.domain.interactors.listeners.OnArtistsUpdateListener;

import java.io.IOException;

public class UpdateArtistsInteractor
        extends BaseInteractor<OnArtistsUpdateListener, UpdateArtistsInteractor.UpdateTask> {

    private DataManager dataManager;

    public UpdateArtistsInteractor(DataManager dataManager) {
        this.dataManager = dataManager;
        task = new UpdateTask();
    }

    @Override
    public void execute() {
        if (task.getStatus() != AsyncTask.Status.RUNNING) {
            task = new UpdateTask();
            task.execute();
        }
    }

    class UpdateTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                return dataManager.updateArtists();
            } catch (IOException e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                if (listener != null) listener.onArtistsUpdatingSuccess();
                new SaveTask().execute();
            } else {
                if (listener != null) listener.onArtistsUpdatingFailure();
            }
        }
    }

    class SaveTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            dataManager.saveArtists();
            return null;
        }
    }

}
