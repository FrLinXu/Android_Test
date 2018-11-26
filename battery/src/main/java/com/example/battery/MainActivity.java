package com.example.battery;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.os.BatteryManager ;
import android.app.NotificationChannel;
import android.support.v4.app.NotificationCompat;
import android.os.Build;
import android.graphics.Color;
import android.content.IntentFilter;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "channel_0";
    private NotificationManager mNotifyManager;
    private static final int NOTIFICATION_ID = 0;
    private Context context =MainActivity.this;
    private BroadcastReceiver mbatteryReceiver=new BroadcastReceiver()
    {
        public void onReceive(final Context context, Intent intent)
        {

            String action =intent.getAction();
            if(Intent.ACTION_BATTERY_CHANGED.equals(action));
            {
                int status=intent.getIntExtra("status",BatteryManager.BATTERY_STATUS_UNKNOWN);
                if(status==BatteryManager.BATTERY_STATUS_CHARGING)
                {
                    Notification notification = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setDefaults(NotificationCompat.DEFAULT_ALL)
                            .setPriority(NotificationCompat.PRIORITY_MAX)
                            .setStyle(new NotificationCompat.MessagingStyle("Hi")
                                    .setConversationTitle("USB")
                                    .addMessage("充电已经连接", (int) System.currentTimeMillis(), ""))

                            .build();
                    mNotifyManager.notify(NOTIFICATION_ID, notification);
                }
                else
                {
                    Notification notification = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setDefaults(NotificationCompat.DEFAULT_ALL)
                            .setPriority(NotificationCompat.PRIORITY_MAX)
                            .setStyle(new NotificationCompat.MessagingStyle("Hi")
                                    .setConversationTitle("USB")
                                    .addMessage("充电已经断开", (int) System.currentTimeMillis(), ""))

                            .build();
                    mNotifyManager.notify(NOTIFICATION_ID, notification);

                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        registerReceiver(mbatteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
}
