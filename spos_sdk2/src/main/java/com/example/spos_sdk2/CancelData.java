package com.example.spos_sdk2;

import android.app.Activity;

public class CancelData {

    private String merchantId;
    private String payRef;
    private EnvBase.PayMethod pMethod;
    private EnvBase.PayGate payGate;
    private Activity activity;
    private String currCode;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getPayRef() {
        return payRef;
    }

    public void setPayRef(String payRef) {
        this.payRef = payRef;
    }

    public EnvBase.PayGate getPayGate() {
        return payGate;
    }

    public EnvBase.PayMethod getpMethod() {
        return pMethod;
    }

    public void setpMethod(EnvBase.PayMethod pMethod) {
        this.pMethod = pMethod;
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

    public String getCurrCode() {
        return currCode;
    }

    public void setCurrCode(String currCode) {
        this.currCode = currCode;
    }
}
