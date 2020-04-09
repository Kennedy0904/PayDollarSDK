package com.example.spos_sdk2.FDMS;

import com.example.spos_sdk2.EnvBase;

public class FdmsData {

    private String merchantId;
    private String merName;
    private String amount;
    private String orderRef;
    private String payMethod;
    private String operatorId = "";
    private String cardNo;
    private String cardHolder;
    private String epMonth;
    private String epYear;

    private String aid;
    private String appCode;
    private String appName;
    private String atc;
    private String RRN;
    private String tc;
    private String tsi;
    private String tvr;

    private String batchNo;
    private String invoiceNo;
    private String traceNo;

    private EnvBase.Currency currCode;
    private EnvBase.PayBankId payBankId;
    private EnvBase.PayType payType;
    private EnvBase.PayGate payGate;
    private EnvBase.FDRequest requestAction;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
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

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getEpMonth() {
        return epMonth;
    }

    public void setEpMonth(String epMonth) {
        this.epMonth = epMonth;
    }

    public String getEpYear() {
        return epYear;
    }

    public void setEpYear(String epYear) {
        this.epYear = epYear;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAtc() {
        return atc;
    }

    public void setAtc(String atc) {
        this.atc = atc;
    }

    public String getRRN() {
        return RRN;
    }

    public void setRRN(String RRN) {
        this.RRN = RRN;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getTsi() {
        return tsi;
    }

    public void setTsi(String tsi) {
        this.tsi = tsi;
    }

    public String getTvr() {
        return tvr;
    }

    public void setTvr(String tvr) {
        this.tvr = tvr;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getTraceNo() {
        return traceNo;
    }

    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }

    public EnvBase.Currency getCurrCode() {
        return currCode;
    }

    public void setCurrCode(EnvBase.Currency currCode) {
        this.currCode = currCode;
    }

    public EnvBase.PayBankId getPayBankId() {
        return payBankId;
    }

    public void setPayBankId(EnvBase.PayBankId payBankId) {
        this.payBankId = payBankId;
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

    public EnvBase.FDRequest getRequestAction() {
        return requestAction;
    }

    public void setRequestAction(EnvBase.FDRequest requestAction) {
        this.requestAction = requestAction;
    }
}
