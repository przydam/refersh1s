package com.google.damianprzybysz.refersh1s;

/**
 * Created by damian.przybysz on 23.01.2018.
 */

import android.annotation.SuppressLint;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import java.util.Calendar;

public class UpdateService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

       // String time = getCurrentDateTime();
        CallersCount callersCount = new CallersCount();

        String time = "dzwoniących: " + callersCount.getCallersCount();



        RemoteViews view = new RemoteViews(getPackageName(), R.layout.refresh_widget);
        view.setTextViewText(R.id.appwidget_text, time);
        ComponentName theWidget = new ComponentName(this, UpdatingWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        int intCallersCount = Integer.parseInt(callersCount.getCallersCount());

        if(intCallersCount >5){
            view.setTextColor(R.id.appwidget_text, R.color.red);
        }



        manager.updateAppWidget(theWidget, view);

        return super.onStartCommand(intent, flags, startId);
    }

    private String getCurrentDateTime() {
        Calendar c = Calendar.getInstance();
        int minute = c.get(Calendar.MINUTE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int seconds = c.get(Calendar.SECOND);

        return hour + ":" + minute + ":" + seconds;
    }
}