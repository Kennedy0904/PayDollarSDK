package com.example.spos_sdk2;

import android.app.Activity;

public class LoginData {

    String merchantId;
    String userId;
    String password;
    EnvBase.PayGate payGate;
    Activity activity;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EnvBase.PayGate getPayGate() {
        return payGate;
    }

    public void setPayGate(EnvBase.PayGate payGate) {
        this.payGate = payGate;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
