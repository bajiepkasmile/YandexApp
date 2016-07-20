package com.domain.yandexapp.domain.recievers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.domain.yandexapp.R;

public class HeadsetReciever extends BroadcastReceiver {

    private static final String YANDEX_MUSIC_PACKAGE_NAME = "ru.yandex.music";
    private static final String YANDEX_RADIO_PACKAGE_NAME = "ru.yandex.radio";
    private static final int NOTIF_ID = 1;

    NotificationManager notificationManager;

    public HeadsetReciever(Context context) {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
            int state = intent.getIntExtra("state", -1);
            switch (state) {
                case 0:
                    closeNotification();
                    break;
                case 1:
                    showNotification(context);
                    break;
            }
        }
    }

    private void showNotification(Context context) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(context.getString(R.string.notification_content_title))
                .setContentText(context.getString(R.string.notification_content_text));

        Intent intentMusic = context.getPackageManager().getLaunchIntentForPackage(YANDEX_MUSIC_PACKAGE_NAME);
        Intent intentRadio = context.getPackageManager().getLaunchIntentForPackage(YANDEX_RADIO_PACKAGE_NAME);

        if ((intentMusic == null) && (intentRadio == null)) {
            return;
        }

        if (intentMusic != null) {
            PendingIntent pendingIntentMusic = PendingIntent.getActivity(context, 0, intentMusic, 0);
            notificationBuilder.addAction(0, context.getString(R.string.notification_title_yandex_music), pendingIntentMusic);
        }

        if (intentRadio != null) {
            PendingIntent pendingIntentRadio = PendingIntent.getActivity(context, 0, intentRadio, 0);
            notificationBuilder.addAction(0, context.getString(R.string.notification_title_yandex_radio), pendingIntentRadio);
        }

        notificationManager.notify(NOTIF_ID, notificationBuilder.build());
    }

    private void closeNotification() {
        notificationManager.cancel(NOTIF_ID);
    }

}
