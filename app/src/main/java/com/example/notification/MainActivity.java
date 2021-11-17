package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventHandler.periodRequest();
       // EventHandler.oneOffRequest();

        button = findViewById(R.id.notify);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String msg = "new message";
//                NotificationCompat.Builder
//                builder = new NotificationCompat.Builder(MainActivity.this, "M_CH_ID")
//                        .setSmallIcon(R.drawable.ic_baseline_message_24)
//                        . setContentTitle("new notification")
//                        .setContentText(msg)
//                        .setAutoCancel(true);
//
//                Intent intent = new Intent(MainActivity.this, NotifiedActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.putExtra("msg", msg);
//
//                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//                builder.setContentIntent(pendingIntent);
//                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                notificationManager.notify(0, builder.build());

                counter ++;

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "1");
                builder .setSmallIcon(R.drawable.ic_baseline_message_24)
                        .setContentTitle("new message")
                        .setContentText("HELLOOOO");

                Notification notification = builder.build();
                NotificationManagerCompat.from(MainActivity.this).notify(counter, notification);

            }
        });

    }
}