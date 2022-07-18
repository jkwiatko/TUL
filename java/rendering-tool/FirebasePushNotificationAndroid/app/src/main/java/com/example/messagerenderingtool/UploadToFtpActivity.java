package com.example.messagerenderingtool;

import android.support.v4.util.ObjectsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class UploadToFtpActivity extends AppCompatActivity {
    private static final String TAG = "FTP Upload" ;
    private static int UUID;

    public static void setUUID (int u) {
        UUID = u;
    }


    @Override
    protected void onStart() {
        super.onStart();
        new Thread() {
            @Override
            public void run() {
                uploadScreens();
            }
        }.start();
    }

    public void uploadScreens() {
        FTPClient ftpClient = new FTPClient();
        String server = "aniatest.hekko24.pl";

        try {
            ftpClient.connect(server);

            ftpClient.setSoTimeout(10000);

            ftpClient.enterLocalPassiveMode();
            if (ftpClient.login("ania@aniatest.hekko24.pl", "Silnehaslo1#@")) {
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
                if(!ftpClient.changeWorkingDirectory("/public_html/screens/"))
                    throw new IOException();
                ftpClient.makeDirectory(String.valueOf(UUID));
                ftpClient.changeWorkingDirectory("./" + UUID);
                final File folder = new File(getExternalFilesDir(null).getAbsolutePath() + "/screenshots/");

                if (folder.listFiles() != null)
                    for (final File fileEntry : folder.listFiles()) {
                        try {
                            FileInputStream fs = new FileInputStream(fileEntry);
                            if (!fileEntry.isDirectory()) {
                                String fileName = fileEntry.getName();
                                ftpClient.storeFile(fileName, fs);
                                fs.close();
                                Log.i(TAG, "sent");
                            }
                        } catch (Exception e) {
                            Log.i(TAG, "error uploading");
                        }
                    }
                }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
