package com.diana.ukrainsky.hw1.data.repository;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;

public class LocationScanner {
    private static LocationScanner INSTANCE = null;

    private LocationScanner() {
    }

    public static LocationScanner getInstance() {
        if (INSTANCE == null)
            INSTANCE = new LocationScanner();
        return INSTANCE;
    }

    public boolean isLocationGranted(Context context) {
        return ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED;
    }

}
