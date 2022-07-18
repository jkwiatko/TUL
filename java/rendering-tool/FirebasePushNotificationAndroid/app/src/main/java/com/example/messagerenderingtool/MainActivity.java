package com.example.messagerenderingtool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = this.getIntent().getExtras();

        if ( extras != null ) {
            if (extras.containsKey("uuid")) {
                UploadToFtpActivity.setUUID(Integer.parseInt(extras.getString("uuid")));
            }
            else {
                Log.d("uuid", "no uuid here");
            }
            if (extras.containsKey("body")) {
                PushGenerator.generateMessageFromExtras(extras);
            }
        } else {
            Log.d("message", "no message here");
            return;
        }

        final Intent intent = new Intent(this, ScreenCaptureImageActivity.class);
        new Thread() {
            @Override
            public void run() {
                startActivity(intent);
            }
        }.start();
    }

}
