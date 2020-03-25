package com.example.spos_sdk2.Settlement;

import android.app.Activity;

import com.example.spos_sdk2.EnvBase;

public class SettlementData {

    private String merchantId;
    private String apiId;
    private String apiPassword;
    private String batchNo;
    private String payBankId;
    private String operatorId;
    private EnvBase.PayGate payGate;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
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

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getPayBankId() {
        return payBankId;
    }

    public void setPayBankId(String payBankId) {
        this.payBankId = payBankId;
    }


    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public EnvBase.PayGate getPayGate() {
        return payGate;
    }

    public void setPayGate(EnvBase.PayGate payGate) {
        this.payGate = payGate;
    }
}
