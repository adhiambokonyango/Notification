package com.example.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.concurrent.TimeUnit;

public class EventHandler extends Worker {
int counter;
    public EventHandler(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        counter ++;
        showNotification();
        return null;
    }

    public static void oneOffRequest(){
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(EventHandler.class)
                .setInitialDelay(10, TimeUnit.SECONDS)
                .setConstraints(setConstraints())
                .build();

        WorkManager.getInstance().enqueue(oneTimeWorkRequest);
    }

    public static void periodRequest(){
        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(EventHandler.class, 10, TimeUnit.SECONDS)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .setConstraints(setConstraints())
                .addTag("periodic")
                .build();
        WorkManager.getInstance().enqueueUniquePeriodicWork("periodic", ExistingPeriodicWorkPolicy.REPLACE, periodicWorkRequest);

    }

    public void showNotification(){


        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "1");
        builder .setSmallIcon(R.drawable.ic_baseline_message_24)
                .setContentTitle("new message")
                .setContentText("HELLOOOO")
                .setContentIntent(pendingIntent)
        .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(counter, builder.build());
    }

    public static Constraints setConstraints(){
        Constraints constraints = new Constraints.Builder()
                //.setRequiresCharging(true)
                .build();
        return constraints;
    }
}
