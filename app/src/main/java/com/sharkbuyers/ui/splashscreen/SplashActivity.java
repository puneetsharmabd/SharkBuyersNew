package com.sharkbuyers.ui.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.SparseIntArray;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.snackbar.Snackbar;
import com.sharkbuyers.R;
import com.sharkbuyers.ui.authentication.loginActivity.LoginActivity;
import com.sharkbuyers.ui.mainActivity.MainActivity;
import com.sharkbuyers.utils.AppController;
import com.sharkbuyers.utils.MyFirebaseMessagingService;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    Context context;
    String access_token="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        VideoView view = (VideoView)findViewById(R.id.videoView);
        //String path = "android.resource://" + getPackageName() + "/" + R.raw.sharkbuyerhowitworks;
        String path = "android.resource://" + getPackageName() + "/" + R.raw.videoplayback;
        view.setVideoURI(Uri.parse(path));
        view.start();
        context = SplashActivity.this;
        access_token=AppController.getInstance(context).getString(AppController.Key.SAVE_ACCESS_TOKEN);
        startTimer();
    }


    private void startTimer() {
        startService(new Intent(SplashActivity.this, MyFirebaseMessagingService.class));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over

                if (access_token!=null){
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();
                }else {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();
                }
            }
        }, 29800);
    }
}

