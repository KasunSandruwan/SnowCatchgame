package com.ksnsandaruwangmail.snowcatch.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

/**
 * Created by welcome on 1/11/2017.
 */

public class Connection  extends BroadcastReceiver{

        public static boolean b;

        @Override
        public void onReceive(Context context, Intent intent) {
            b = checkConnection(context);
        }

        public static boolean checkConnection(Context context) {
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final android.net.NetworkInfo wifi = connMgr
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            final android.net.NetworkInfo mobile = connMgr
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (wifi.isConnected() || mobile.isConnected()) {
                return true;

            } else {
                return false;

            }

        }
    }




