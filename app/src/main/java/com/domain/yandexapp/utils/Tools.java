package com.domain.yandexapp.utils;

import android.content.res.Configuration;
import android.content.res.Resources;

public class Tools {

    public static boolean isScreenLarge(Resources resources) {
        return (resources.getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static boolean isScreenLandscape(Resources resources) {
        return resources.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

}
