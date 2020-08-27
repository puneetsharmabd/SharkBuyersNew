package com.sharkbuyers.utils;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;

import static android.content.ContentValues.TAG;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    String device_token;
    Context context;
    public MyFirebaseMessagingService() {
    }

    @Override
    public void onCreate() {

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        } else {
                            device_token = task.getResult().getToken();
                            AppController.getInstance(MyFirebaseMessagingService.this).put(AppController.Key.SAVE_DEVICE_TOKEN, device_token);
                        }
                    }
                });
    }

    @Override
    public void onNewToken(String device_token) {
        this.device_token = device_token;
        AppController.getInstance(MyFirebaseMessagingService.this).put(AppController.Key.SAVE_DEVICE_TOKEN, device_token);
    }
}
