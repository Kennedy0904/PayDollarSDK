package com.example.spos_sdk2;

public class CancelData {

    private String merchantId;
    private String payRef;
    private EnvBase.PayMethod payMethod;
    private EnvBase.PayGate payGate;
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

    public EnvBase.PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(EnvBase.PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    public void setPayGate(EnvBase.PayGate payGate) {
        this.payGate = payGate;
    }

    public String getCurrCode() {
        return currCode;
    }

    public void setCurrCode(String currCode) {
        this.currCode = currCode;
    }
}
