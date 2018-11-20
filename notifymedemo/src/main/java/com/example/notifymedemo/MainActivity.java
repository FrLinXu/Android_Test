package com.example.notifymedemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.security.Timestamp;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Button mNotifyButton;
    private Button mUpdateButton;
    private Button mCancelButton;

    private NotificationManager mNotifyManager;

    private static final String CHANNEL_ID = "channel_0";
    private static final int NOTIFICATION_ID = 0;
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String NOTIFICATION_GUIDE_URL =
            "https://developer.android.com/design/patterns/notifications.html";

    private static final String ACTION_UPDATE_NOTIFICATION =
            "com.glriverside.xgqin.notifymedemo.ACTION_UPDATE_NOTIFICATION";

    private NotificationReceiver mNotificationReceiver = new NotificationReceiver();

    private View.OnClickListener clickListener = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.notify:
                    sendNotification();
                    Log.d(TAG, "notify me! clicked!");
                    break;
                case R.id.update:
                    updateNotification();
                    break;
                case R.id.cancel:
                    cancelNotification();
                    break;
            }

        }
    };

    private void cancelNotification() {
        mNotifyManager.cancel(NOTIFICATION_ID);
    }

    private void updateNotification() {
        Bitmap androidImage = BitmapFactory.decodeResource(getResources(), R.drawable.img_running);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);
        notifyBuilder.setContentTitle("You've been notified!")
                .setContentText("This is your notification text.")
                .setSmallIcon(R.drawable.img_running)
                .setLargeIcon(androidImage)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(androidImage)
                        .setBigContentTitle("Notification Updated!")
                )
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notificationIntent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(MainActivity.this,
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        notifyBuilder.setContentIntent(notificationPendingIntent);
        notifyBuilder.setPriority(NotificationCompat.PRIORITY_MAX);

        notifyBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);

        Notification notification = notifyBuilder.build();
        mNotifyManager.notify(NOTIFICATION_ID, notification);
    }

    private void sendNotification() {

        /*
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);
        notifyBuilder.setContentTitle("You've been notified!")
                .setContentText("This is your notification text.")
                .setSmallIcon(R.drawable.ic_android);

        notifyBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
        notifyBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);

        Intent notificationIntent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(MainActivity.this,
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        notifyBuilder.setContentIntent(notificationPendingIntent);


        Intent learnMoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(NOTIFICATION_GUIDE_URL));
        PendingIntent learnMorePendingIntent = PendingIntent.getActivity(MainActivity.this,
                NOTIFICATION_ID, learnMoreIntent, PendingIntent.FLAG_ONE_SHOT);

        notifyBuilder.addAction(R.drawable.ic_learn_more, "Learn More", learnMorePendingIntent);

        Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
        PendingIntent updatePendingIntent = PendingIntent.getBroadcast(MainActivity.this,
                NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT);

        notifyBuilder.addAction(R.drawable.ic_update, "Update", updatePendingIntent);

        Notification notification = notifyBuilder.build();


        mNotifyManager.notify(NOTIFICATION_ID, notification);
        */

        /*
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);
        notifyBuilder.setContentTitle("You've been notified!")
                .setContentText("This is your notification text.")
                .setSmallIcon(R.drawable.ic_android);

        notifyBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);


        notifyBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
        notifyBuilder.setProgress(100, 10, false);

        Notification notification = notifyBuilder.build();

        mNotifyManager.notify(NOTIFICATION_ID, notification);
        */

        Notification notification = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                .setSmallIcon(R.drawable.img_running)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setStyle(new NotificationCompat.MessagingStyle("Me")
                        .setConversationTitle("Team lunch")
                        .addMessage("Hi", (int) System.currentTimeMillis(), "")
                        .addMessage("What's up?", (int) System.currentTimeMillis(), "Coworker")
                        .addMessage("Not much", (int) System.currentTimeMillis(), "")
                        .addMessage("How about lunch?", (int) System.currentTimeMillis(), "Coworker"))
                .build();

/*
        Notification notification = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_android)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build();
                */

        mNotifyManager.notify(NOTIFICATION_ID, notification);
    }

    @Override
    protected void onDestroy() {

        unregisterReceiver(mNotificationReceiver);

        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotifyButton = (Button) findViewById(R.id.notify);
        mUpdateButton = (Button) findViewById(R.id.update);
        mCancelButton = (Button) findViewById(R.id.cancel);

        mNotifyButton.setOnClickListener(clickListener);
        mUpdateButton.setOnClickListener(clickListener);
        mCancelButton.setOnClickListener(clickListener);

        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        }

        if (Build.VERSION.SDK_INT >= 26) {

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Channel1", NotificationManager.IMPORTANCE_HIGH);

            channel.enableLights(true);
            channel.setLightColor(Color.GREEN);
            channel.setShowBadge(true);

            mNotifyManager.createNotificationChannel(channel);
        }

        registerReceiver(mNotificationReceiver, new IntentFilter(ACTION_UPDATE_NOTIFICATION));

    }

    public class NotificationReceiver extends BroadcastReceiver {
        public NotificationReceiver () {}

        @Override
        public void onReceive(Context context, Intent intent) {
            updateNotification();
        }
    }
}
