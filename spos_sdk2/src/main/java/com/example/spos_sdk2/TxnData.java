package com.example.spos_sdk2;

import android.app.Activity;

public class TxnData {

    private String merchantId;
    private String amount;
    private String payRef;
    private String apiId;
    private String apiPassword;
    EnvBase.TxnAction actionType;
    EnvBase.PayGate payGate;
    Activity activity;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayRef() {
        return payRef;
    }

    public void setPayRef(String payRef) {
        this.payRef = payRef;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getApiPassword() {
        return apiPassword;
    }

    public void setApiPassword(String apiPassword) {
        this.apiPassword = apiPassword;
    }

    public EnvBase.TxnAction getActionType() {
        return actionType;
    }

    public void setActionType(EnvBase.TxnAction actionType) {
        this.actionType = actionType;
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
