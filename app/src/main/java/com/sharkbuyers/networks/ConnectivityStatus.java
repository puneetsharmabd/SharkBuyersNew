package com.sharkbuyers.networks;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.sharkbuyers.R;

public class ConnectivityStatus {


    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;

    public static int getConnectivityStatus(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    public static String getConnectivityStatusString(Context context) {

        int conn = ConnectivityStatus.getConnectivityStatus(context);

        String status = null;

        if (conn == ConnectivityStatus.TYPE_WIFI) {

            status = context.getString(R.string.connected_with_wifi);


        } else if (conn == ConnectivityStatus.TYPE_MOBILE) {

            status = context.getString(R.string.connected_with_mobile);


        } else if (conn == ConnectivityStatus.TYPE_NOT_CONNECTED) {

            status = context.getString(R.string.you_are_offline);

        }

        return status;
    }
}
