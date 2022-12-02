package com.diana.ukrainsky.hw1.data.repository;

import android.content.Context;
import android.net.wifi.WifiManager;

public class WifiScanner {

    private static WifiScanner INSTANCE =null;

    private WifiScanner(){

    }

    public static WifiScanner getInstance() {
        if(INSTANCE==null)
            INSTANCE = new WifiScanner();
        return INSTANCE;
    }

    public boolean isWifiEnabled(Context context) {
        WifiManager wifi = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        if (wifi.isWifiEnabled()){
            return true;
        }
        return false;
    }
}
