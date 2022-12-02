package com.diana.ukrainsky.hw1.ui;

import android.content.Context;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.diana.ukrainsky.hw1.BR;
import com.diana.ukrainsky.hw1.data.model.ChargeState;
import com.diana.ukrainsky.hw1.data.model.User;
import com.diana.ukrainsky.hw1.data.repository.BatteryScanner;
import com.diana.ukrainsky.hw1.data.repository.WifiScanner;

public class LoginViewModel extends BaseObservable {
    private User user;

    private String successMessage = "Login was successful";
    private String errorMessage = "Password not valid";

    @Bindable
    private String toastMessage = null;

    public LoginViewModel() {
        user = new User("", -1);
    }

    // The three conditions are declared here
    public void onLoginClicked(Context context) {
        setParameters(context);
        if (isInputMatchesBatteryLevel() && isUserWifiEnabled() && isUserLocationEnabled() && isUserCharging() && isUserChargingViaUsbOrFull())
            setToastMessage(successMessage);
        else
            setToastMessage(errorMessage);
    }

    private boolean isUserChargingViaUsbOrFull() {
        if (BatteryScanner.getInstance().howIsCharging().equals(ChargeState.USB_CHARGE) ||
                BatteryScanner.getInstance().howIsCharging().equals(ChargeState.FULL_CHARGED))
            return true;
        return false;
    }

    private boolean isUserCharging() {
        return BatteryScanner.getInstance().isChargingOrIsFull();
    }

    private void setParameters(Context context) {
        setUserBatteryLevel(BatteryScanner.getInstance().getBatteryPercentage(context));
        setUserWifiEnabled(WifiScanner.getInstance().isWifiEnabled(context));
        setUserLocationEnabled(context);
    }


    public String getToastMessage() {
        return toastMessage;
    }


    private void setToastMessage(String toastMessage) {

        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    @Bindable
    public String getUserPassword() {
        return user.getPassword();
    }

    public void setUserPassword(String password) {
        user.setPassword(password);
        notifyPropertyChanged(BR.userPassword);
    }

    @Bindable
    public float getUserBatteryLevel() {
        return user.getBatteryLevel();
    }

    public void setUserBatteryLevel(float batteryLevel) {
        user.setBatteryLevel(batteryLevel);
        notifyPropertyChanged(BR.userBatteryLevel);

    }

    @Bindable
    public boolean isUserWifiEnabled() {
        return user.isWifiEnabled();
    }

    public void setUserWifiEnabled(boolean isWifiEnabled) {
        user.setWifiEnabled(isWifiEnabled);
        notifyPropertyChanged(BR.userWifiEnabled);
    }

    @Bindable
    public boolean isUserLocationEnabled() {
        return user.isLocationEnabled();
    }

    public void setUserLocationEnabled(Context context) {
        user.setLocation(context);
        notifyPropertyChanged(BR.userLocationEnabled);
    }

    private boolean isInputMatchesBatteryLevel() {
        return String.valueOf((int) getUserBatteryLevel()).equals(getUserPassword());
    }

}
