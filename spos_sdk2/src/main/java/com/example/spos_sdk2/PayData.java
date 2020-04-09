package com.example.spos_sdk2;

import com.example.spos_sdk2.ISO_8583.CardData;
import com.example.spos_sdk2.ISO_8583.ISO8583Data;

public class PayData {

    private boolean enableSurcharge;
    private CardData cardData;
    private ISO8583Data ISO8583Data;
    private EnvBase.Currency currCode;
    private EnvBase.PayGate payGate;
    private EnvBase.PayMethod payMethod;
    private EnvBase.PayType payType;
    private EnvBase.Payment payment;
    private String amount;
    private String batchNo;
    private String invoiceRef;
    private String merchantId;
    private String merchantName;
    private String merRequestAmt;
    private String operatorId;
    private String orderRef;
    private String surcharge;
    private String terminalId;
    private String txnNo;

    public boolean isEnableSurcharge() {
        return enableSurcharge;
    }

    public void setEnableSurcharge(boolean enableSurcharge) {
        this.enableSurcharge = enableSurcharge;
    }

    public CardData getCardData() {
        return cardData;
    }

    public void setCardData(CardData cardData) {
        this.cardData = cardData;
    }

    public ISO8583Data getISO8583Data() {
        return ISO8583Data;
    }

    public void setISO8583Data(ISO8583Data ISO8583Data) {
        this.ISO8583Data = ISO8583Data;
    }

    public EnvBase.Currency getCurrCode() {
        return currCode;
    }

    public void setCurrCode(EnvBase.Currency currCode) {
        this.currCode = currCode;
    }

    public EnvBase.PayGate getPayGate() {
        return payGate;
    }

    public void setPayGate(EnvBase.PayGate payGate) {
        this.payGate = payGate;
    }

    public EnvBase.PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(EnvBase.PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    public EnvBase.PayType getPayType() {
        return payType;
    }

    public void setPayType(EnvBase.PayType payType) {
        this.payType = payType;
    }

    public EnvBase.Payment getPayment() {
        return payment;
    }

    public void setPayment(EnvBase.Payment payment) {
        this.payment = payment;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getInvoiceRef() {
        return invoiceRef;
    }

    public void setInvoiceRef(String invoiceRef) {
        this.invoiceRef = invoiceRef;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerRequestAmt() {
        return merRequestAmt;
    }

    public void setMerRequestAmt(String merRequestAmt) {
        this.merRequestAmt = merRequestAmt;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(String orderRef) {
        this.orderRef = orderRef;
    }

    public String getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(String surcharge) {
        this.surcharge = surcharge;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTxnNo() {
        return txnNo;
    }

    public void setTxnNo(String txnNo) {
        this.txnNo = txnNo;
    }
}
