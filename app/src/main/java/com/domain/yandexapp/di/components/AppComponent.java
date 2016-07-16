package com.domain.yandexapp.di.components;

import com.domain.yandexapp.di.modules.AppModule;
import com.domain.yandexapp.di.modules.InteractorsModule;
import com.domain.yandexapp.di.modules.PresentersModule;
import com.domain.yandexapp.ui.activities.MainActivity;
import com.domain.yandexapp.ui.fragments.ArtistDetailsFragment;
import com.domain.yandexapp.ui.fragments.ArtistsListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                PresentersModule.class,
                InteractorsModule.class
        }
)
public interface AppComponent {

    void inject(ArtistsListFragment fragment);

    void inject(ArtistDetailsFragment fragment);

    void inject(MainActivity activity);

}
