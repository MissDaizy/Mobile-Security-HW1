package com.diana.ukrainsky.hw1.data.repository;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import com.diana.ukrainsky.hw1.data.model.ChargeState;

import static android.content.Context.BATTERY_SERVICE;

public class BatteryScanner {

    private static BatteryScanner INSTANCE=null;
    private Intent batteryStatus;

    private BatteryScanner() {
    }
    public static BatteryScanner getInstance( ) {
        if(INSTANCE==null)
            INSTANCE=new BatteryScanner();
        return INSTANCE;
    }


    public boolean isChargingOrIsFull() {
        // Are we charging / charged?
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        return status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;
    }

    public ChargeState howIsCharging() {
        // How are we charging?
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
        boolean fullCharged = chargePlug == BatteryManager.BATTERY_STATUS_FULL;

        if(usbCharge)
            return ChargeState.USB_CHARGE;
        else if(acCharge)
            return ChargeState.AC_CHARGE;
        else if(fullCharged)
            return ChargeState.FULL_CHARGED;

        return ChargeState.NONE;
    }


    public int getBatteryPercentage(Context context) {
        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        batteryStatus = context.registerReceiver(null, iFilter);

        if (Build.VERSION.SDK_INT >= 21) {

            BatteryManager bm = (BatteryManager) context.getSystemService(BATTERY_SERVICE);
            return bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

        } else {

            int level = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) : -1;
            int scale = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1) : -1;

            double batteryPct = level / (double) scale;

            return (int) (batteryPct * 100);
        }
    }


}
