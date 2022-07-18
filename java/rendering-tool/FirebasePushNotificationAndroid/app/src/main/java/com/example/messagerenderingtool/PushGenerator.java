package com.example.messagerenderingtool;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by anwyr1 on 03/02/2018.
 */

public class PushGenerator {
    public static final String CHANNEL_ID = "BpMaCarteNotifications";

    private final HashMap<String, Integer> soundMap = new HashMap<>();
    private final HashMap<String, Integer> iconMap = new HashMap<>();
    private static final Gson gson = new Gson();
    private NotificationManager notificationManager;
    private Object statusBarService;

    private static NotificationModel notification;

    public static void setMessageFromJson(String msg) {
        try {
            JSONObject obj = new JSONObject(msg);
            String content = obj.getJSONObject("data").getString("data");
            notification = gson.fromJson(content, NotificationModel.class);
        } catch (JsonSyntaxException | JSONException ex) {
            System.err.println(ex.getMessage());
            notification = new NotificationModel();
            notification.setContent(msg);
            notification.setTitle(msg.substring(0, 10));
        }
    }

    public PushGenerator(NotificationManager manager, Object barService) {
        notificationManager = manager;
        statusBarService = barService;
//        soundMap.put("sound_1.wav", R.raw.sound_1);
//        soundMap.put("sound_2.wav", R.raw.sound_2);
//        soundMap.put("sound_3.wav", R.raw.sound_3);
//        soundMap.put("sound_4.wav", R.raw.sound_4);
//        soundMap.put("sound_5.wav", R.raw.sound_5);
//        soundMap.put("sound_6.wav", R.raw.sound_6);
//        soundMap.put("sound_7.wav", R.raw.sound_7);
//        soundMap.put("sound_8.wav", R.raw.sound_8);
//        iconMap.put("default", R.mipmap.icon);
//        iconMap.put("empty", R.drawable.empty);
    }

    public static void generateMessageFromExtras(Bundle extras) {
        notification = new NotificationModel();
        notification.setTitle(extras.getString("title"));
        notification.setContent(extras.getString("body"));
        notification.setLargeImageUrl(extras.getString("largeImage"));
        notification.setSmallImageUrl(extras.getString("smallImage"));
        notification.setActions(generateActions(extras));
    }

    private static List<ActionModel> generateActions (Bundle extras) {
        ActionModel actionA = new ActionModel();
        actionA.setName(extras.getString("actionA"));
        ActionModel actionB = new ActionModel();
        actionB.setName(extras.getString("actionB"));

        ArrayList<ActionModel> actions = new ArrayList<>();
        actions.add(actionA);
        actions.add(actionB);

        return actions;
    }

    public void sendNotification(Context context, boolean bigSize, Intent intent){
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID);
        notificationBuilder.setContentTitle(notification.getTitle());
        notificationBuilder.setContentText(notification.getContent());
        notificationBuilder.setContentIntent(pendingIntent);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        if(bigSize) {
            notificationBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(notification.getContent()));
            for (ActionModel action : notification.getActions()) {
                notificationBuilder.addAction(R.drawable.app_launcher, action.getName(), pendingIntent);
            }
        }

        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, "fbchannel", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }
        notificationManager.notify(0, notificationBuilder.build());
        expandNotificationBar();
        try {
            Thread.sleep(5000);
        }
        catch(InterruptedException e) {
            Log.d("interrupt", "couldn't sleep");
        }
    }

    private void expandNotificationBar(){
        try {
            Class<?> statusbarManager = Class.forName("android.app.StatusBarManager");
            Method showsb;
            if (Build.VERSION.SDK_INT >= 17) {
                showsb = statusbarManager.getMethod("expandNotificationsPanel");
            }
            else {
                showsb = statusbarManager.getMethod("expand");
            }
            showsb.invoke( statusBarService ); // don't mind it's working
        }
        catch(Exception e) {
            Log.d("error", "Couldn't expend status bar");
        }
    }


}
