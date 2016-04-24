package com.domain.yandexapp.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastManager {

    private static Toast toast;

    public static void showToast(Context context, int resId, int duration) {
        if (toast == null){
            toast = Toast.makeText(context, resId, duration);
        } else {
            toast.setText(resId);
            toast.setDuration(duration);
        }
        toast.show();
    }

}
