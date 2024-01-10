package com.harsh.swipeandroidassignment;

import android.app.NotificationManager;
import android.content.Context;

import androidx.core.app.NotificationCompat;

public class NotificationHelper {

    private static final int NOTIFICATION_ID = 1;
    public static void showNotification(Context context, String fullMessage) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Product Notification")
                .setContentText(fullMessage)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(fullMessage))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager != null) {
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
    }
}

