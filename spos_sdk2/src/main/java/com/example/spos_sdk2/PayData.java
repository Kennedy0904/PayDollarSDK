package com.example.spos_sdk2;

import android.app.Activity;

public class PayData {

    private String merchantId;
    private String amount;
    private String orderRef;
    private String txnNo;
    private String operatorId;
    private EnvBase.Payment payment;
    private EnvBase.Currency currCode;
    private EnvBase.PayType payType;
    private EnvBase.PayMethod pMethod;
    private EnvBase.PayGate payGate;

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

    public String getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(String orderRef) {
        this.orderRef = orderRef;
    }

    public EnvBase.PayMethod getpMethod() {
        return pMethod;
    }

    public void setpMethod(EnvBase.PayMethod pMethod) {
        this.pMethod = pMethod;
    }

    public String getTxnNo() {
        return txnNo;
    }

    public void setTxnNo(String txnNo) {
        this.txnNo = txnNo;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public EnvBase.Payment getPayment() {
        return payment;
    }

    public void setPayment(EnvBase.Payment payment) {
        this.payment = payment;
    }

    public EnvBase.Currency getCurrCode() {
        return currCode;
    }

    public void setCurrCode(EnvBase.Currency currCode) {
        this.currCode = currCode;
    }

    public EnvBase.PayType getPayType() {
        return payType;
    }

    public void setPayType(EnvBase.PayType payType) {
        this.payType = payType;
    }

    public EnvBase.PayGate getPayGate() {
        return payGate;
    }

    public void setPayGate(EnvBase.PayGate payGate) {
        this.payGate = payGate;
    }
}
