package com.diana.ukrainsky.hw1.data.model;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import com.diana.ukrainsky.hw1.data.repository.LocationScanner;

import java.util.Map;

public class User {
    private String password;
    private float batteryLevel;
    private boolean isWifiEnabled;
    private boolean isLocationEnabled;

    public User(String password,float batteryLevel) {
        this.password = password;
        this.batteryLevel=-1;
    }

    public User() {
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getPassword() {
        return password;
    }

    public float getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(float batteryLevel) {
        this.batteryLevel = batteryLevel;
    }


    public boolean isWifiEnabled() {
        return isWifiEnabled;
    }

    public void setWifiEnabled(boolean wifiEnabled) {
        isWifiEnabled = wifiEnabled;
    }

    public boolean isLocationEnabled() {
        return isLocationEnabled;
    }

    public void setLocation(Context context) {
        isLocationEnabled = !LocationScanner.getInstance().isLocationGranted(context);
    }

}
