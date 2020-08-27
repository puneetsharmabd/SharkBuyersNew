package com.sharkbuyers.networks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {
    public Context contex;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.contex = context;
        String status = ConnectivityStatus.getConnectivityStatusString(context);
        Toast.makeText(context, status, Toast.LENGTH_LONG).show();
    }
}
