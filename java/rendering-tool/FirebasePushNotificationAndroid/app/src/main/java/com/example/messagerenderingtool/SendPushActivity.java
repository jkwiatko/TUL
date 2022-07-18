//package com.example.messagerenderingtool;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.app.Notification;
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.media.RingtoneManager;
//import android.net.Uri;
//import android.os.Build;
//import android.support.annotation.Nullable;
//import android.support.v4.app.NotificationCompat;
//import android.text.TextUtils;
//import android.util.Log;
//
//import com.bumptech.glide.Glide;
//import com.google.gson.Gson;
//import org.parceler.Parcels;
//
//import java.lang.reflect.Method;
//import java.util.HashMap;
//import java.util.concurrent.ExecutionException;
//
///**
// * Created by anwyr1 on 12/3/2017.
// */
//
//public class SendPushActivity extends Activity {
//    public static final String CHANNEL_ID = "BpMaCarteNotifications";
//
//    private final HashMap<String, Integer> soundMap = new HashMap<>();
//    private final HashMap<String, Integer> iconMap = new HashMap<>();
//    private final Gson gson = new Gson();
//
//    private static String message;
//
//    public SendPushActivity() {
////        soundMap.put("sound_1.wav", R.raw.sound_1);
////        soundMap.put("sound_2.wav", R.raw.sound_2);
////        soundMap.put("sound_3.wav", R.raw.sound_3);
////        soundMap.put("sound_4.wav", R.raw.sound_4);
////        soundMap.put("sound_5.wav", R.raw.sound_5);
////        soundMap.put("sound_6.wav", R.raw.sound_6);
////        soundMap.put("sound_7.wav", R.raw.sound_7);
////        soundMap.put("sound_8.wav", R.raw.sound_8);
////        iconMap.put("default", R.mipmap.icon);
////        iconMap.put("empty", R.drawable.empty);
//    }
//
////    @Override
////    public void onMessageReceived(RemoteMessage remoteMessage) {
////        if (!remoteMessage.getNotification().getBody().isEmpty()) {
////            String json = remoteMessage.getNotification().getBody();
////            handleNotification(gson.fromJson(json, NotificationModel.class));
////        }
////    }
////
////    private int getSmallIcon(String icon_url, String defaultKeyImage) {
////        Integer id = iconMap.get(icon_url);
////        if (id == null) {
////            if (defaultKeyImage == null) {
////                return iconMap.get("default");
////            } else {
////                return iconMap.get(defaultKeyImage);
////            }
////        }
////
////        return id;
////    }
////
////    private int getSmallIcon(String icon_url) {
////        return getSmallIcon(icon_url, null);
////    }
////
////    private Uri getSoundUri(String key) {
////        Integer id = soundMap.get(key);
////        if (id == null) {
////            return RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
////        }
////        return Uri.parse("android.resource://" + MyFirebaseMessagingService.this.getApplicationContext().getPackageName() + "/" + id);
////    }
////
////    @Nullable
////    private Bitmap loadImage(String imageUrl) {
////        if (TextUtils.isEmpty(imageUrl)) {
////            return null;
////        }
////        try {
////            Drawable drawable = Glide.with(this)
////                    .load(imageUrl)
////                    .submit().get();
////            if (drawable instanceof BitmapDrawable) {
////                return ((BitmapDrawable) drawable).getBitmap();
////            }
////        } catch (InterruptedException | ExecutionException e) {
////            return null;
////        }
////        return null;
////    }
////
////    private void handleNotification(NotificationModel model) {
////        Bitmap smallImage = loadImage(model.getSmallImageUrl());
////        Bitmap bigImage = loadImage(model.getLargeImageUrl());
////        renderNotification(model, smallImage, bigImage);
////    }
////
////    private void renderNotification(NotificationModel model, @Nullable Bitmap smallImage, @Nullable Bitmap bigImage) {
////        Intent intent = new Intent(this, ScreenCaptureImageActivity.class);
////        int notificationId = Integer.parseInt(model.getId());
////        intent.putExtra(ScreenCaptureImageActivity.EXTRA_NOTIFICATION_ID, model.getId());
////        if (model.getClickActionCustom() != null) {
////            intent.putExtra(ScreenCaptureImageActivity.EXTRA_ACTION_MODEL, Parcels.wrap(model.getClickActionCustom()));
////        }
////        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////        PendingIntent contentIntent = PendingIntent.getActivity(MyFirebaseMessagingService.this, notificationId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
////
////        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MyFirebaseMessagingService.this, CHANNEL_ID)
////                .setContentText(model.getContent())
////                .setContentTitle(model.getTitle())
////                .setSound(getSoundUri(model.getSoundId()))
////                .setSmallIcon(getSmallIcon(model.getSmallIconId()))
////                .setContentIntent(contentIntent)
////                .setWhen(System.currentTimeMillis());
////
////        int idx = 0;
////        for (ActionModel action : model.getActions()) {
////            try {
////                idx++;
////                Intent actionIntent = new Intent(this, ScreenCaptureImageActivity.class);
////                actionIntent.putExtra(ScreenCaptureImageActivity.EXTRA_ACTION_MODEL, Parcels.wrap(action));
////                actionIntent.putExtra(ScreenCaptureImageActivity.EXTRA_NOTIFICATION_ID, model.getId());
////                actionIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////
////                PendingIntent actionPendingIntent = PendingIntent.getActivity(this, idx, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);
////
////                NotificationCompat.Action actionButton = new NotificationCompat.Action.Builder(getSmallIcon(action.getImgId(), "empty"), action.getName(), actionPendingIntent).build();
////
////                notificationBuilder.addAction(actionButton);
////            } catch (Exception e) {
////                notificationBuilder.addAction(getSmallIcon(action.getImgId(), "empty"), action.getName(), contentIntent);
////            }
////        }
////
////        if (smallImage != null) {
////            notificationBuilder.setLargeIcon(smallImage);
////        }
////
////        if (bigImage != null) {
////            NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle().bigPicture(bigImage)
////                    .setSummaryText(model.getContent())
////                    .setBigContentTitle(model.getTitle());
////            if (smallImage != null) {
////                style.bigLargeIcon(smallImage);
////            }
////            notificationBuilder.setStyle(style);
////        }
////
////        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
////        Notification notification = notificationBuilder.build();
////        mNotificationManager.notify(notificationId, notification);
////    }
//
//    public static void setMessage(String msg) {
//        message = msg;
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        onMessageReceived();
//    }
//
//    public void onMessageReceived() {
//       // ScreenCaptureImageActivity.increaseCounter();
//        Intent intent = new Intent(this, ScreenCaptureImageActivity.class);
//        try {
//            startActivity(intent);
//            Thread.sleep(1000);
//            sendNotification(message, false);
//            setResult(Activity.RESULT_OK, intent);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        try {
//            Thread.sleep(800);
//            sendNotification(message, true);
//        //    ScreenCaptureImageActivity.increaseCounter();
//            startActivity(intent);
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        Intent uploadScreens = new Intent(this, UploadToFtpActivity.class);
//        startActivity(uploadScreens);
//    }
//
//
//    private void sendNotification(String remoteMessage, boolean bigSize){
//        String chanelId = CHANNEL_ID;
//
//        Intent intent = new Intent(this, SendPushActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, chanelId);
//        notificationBuilder.setContentTitle("FCM NOTIFICATION");
//        notificationBuilder.setContentText(remoteMessage);
////        notificationBuilder.setAutoCancel(true);
//        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
//        notificationBuilder.setContentIntent(pendingIntent);
//        if(bigSize) {
//            notificationBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText("--big notification--"+remoteMessage));
//            notificationBuilder.addAction(R.drawable.app_launcher, "show activity", pendingIntent);
//            notificationBuilder.addAction(R.drawable.ic_launcher_web, "ignore", pendingIntent);
//        }
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        if (Build.VERSION.SDK_INT >= 26) {
//            NotificationChannel mChannel = new NotificationChannel(chanelId, "fbchannel", NotificationManager.IMPORTANCE_HIGH);
//            notificationManager.createNotificationChannel(mChannel);
//        }
//        notificationManager.notify(0, notificationBuilder.build());
//        expandNotificationBar();
//        try {
//            Thread.sleep(5000);
//        }
//        catch(InterruptedException e) {
//            Log.d("interupt", "couldnt sleep");
//        }
//    }
//
//    @SuppressLint("WrongConstant")
//    private void expandNotificationBar(){
//        try {
//            Object sbservice;
//            Class<?> statusbarManager = Class.forName("android.app.StatusBarManager");
//            Method showsb;
//            if (Build.VERSION.SDK_INT >= 17) {
//                showsb = statusbarManager.getMethod("expandNotificationsPanel");
//            }
//            else {
//                showsb = statusbarManager.getMethod("expand");
//            }
//            showsb.invoke( getSystemService( "statusbar" ) ); // dont mind its working
//        }
//        catch(Exception e) {
//            Log.d("error", "Couldnt expend statusbar");
//        }
//    }
//}
