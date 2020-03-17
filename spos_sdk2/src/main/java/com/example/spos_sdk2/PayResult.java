package com.example.spos_sdk2;

public class PayResult {

    public final static int TXN_SUCCESS = 0;
    public final static int TXN_FAILED = -1;

    private int resultCode;
    private String merchantRef;
    private String payDollarRef;
    private String bankRef;
    private String amount;
    private String currency;
    private String payMethod;
    private String returnMsg;
    private String txnTime;
    private int prc;
    private int src;
    private String QRRef;
    private String QRCode;
    private String QRCodeType;
    private String authId;
    private String bankMerId;
    private String bankTerminalId;

//    private String Cur;
//    private String AuthId;
//    private String Ref;
//    private String Holder;
//    private String TxTime;
//    private String errMsg;
//    private String PayRef;
//    private String Amt;
//    private String successcode;
//    private String PayMethod;
//    private String orderRefNo;


    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMerchantRef() {
        return merchantRef;
    }

    public void setMerchantRef(String merchantRef) {
        this.merchantRef = merchantRef;
    }

    public String getPayDollarRef() {
        return payDollarRef;
    }

    public void setPayDollarRef(String payDollarRef) {
        this.payDollarRef = payDollarRef;
    }

    public String getBankRef() {
        return bankRef;
    }

    public void setBankRef(String bankRef) {
        this.bankRef = bankRef;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public int getPrc() {
        return prc;
    }

    public void setPrc(int prc) {
        this.prc = prc;
    }

    public String getQRRef() {
        return QRRef;
    }

    public void setQRRef(String QRRef) {
        this.QRRef = QRRef;
    }

    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

    public String getQRCodeType() {
        return QRCodeType;
    }

    public void setQRCodeType(String QRCodeType) {
        this.QRCodeType = QRCodeType;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getBankMerId() {
        return bankMerId;
    }

    public void setBankMerId(String bankMerId) {
        this.bankMerId = bankMerId;
    }

    public String getBankTerminalId() {
        return bankTerminalId;
    }

    public void setBankTerminalId(String bankTerminalId) {
        this.bankTerminalId = bankTerminalId;
    }
}
