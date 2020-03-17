package com.example.spos_sdk2;

import java.util.List;

public class LoginResult {

    public final static int SUCCESS = 0;
    public final static int INV_MERID = -1;
    public final static int INV_PASSWORD = -2;
    public final static int NO_USER= -3;
    public final static int CONN_ERR = -4;

    private int resultCode;
    private String merchantName;
    private String currencyCode;
    private String returnMsg;
    private List<PayMethod> payMethod;
//    private String[] payMethod;
    private String[] channelType;
    private String merchantClass;
    private boolean amexOnlineRefund;
    private boolean visaOnlineRefund;
    private boolean masterOnlineRefund;
    private boolean jcbOnlineRefund;
    private boolean enableMPOSMS;
    private double rate;
    private double fixed;
    private boolean hideSurcharge;
    private String partnerLogo;
    private String apiId;
    private String apiPassword;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
//    private String[] bankKey;
//    private String[] bankTerId;


    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public List<PayMethod> getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(List<PayMethod> payMethod) {
        this.payMethod = payMethod;
    }

    public String[] getChannelType() {
        return channelType;
    }

    public void setChannelType(String[] channelType) {
        this.channelType = channelType;
    }

    public String getMerchantClass() {
        return merchantClass;
    }

    public void setMerchantClass(String merchantClass) {
        this.merchantClass = merchantClass;
    }

    public boolean isAmexOnlineRefund() {
        return amexOnlineRefund;
    }

    public void setAmexOnlineRefund(boolean amexOnlineRefund) {
        this.amexOnlineRefund = amexOnlineRefund;
    }

    public boolean isVisaOnlineRefund() {
        return visaOnlineRefund;
    }

    public void setVisaOnlineRefund(boolean visaOnlineRefund) {
        this.visaOnlineRefund = visaOnlineRefund;
    }

    public boolean isMasterOnlineRefund() {
        return masterOnlineRefund;
    }

    public void setMasterOnlineRefund(boolean masterOnlineRefund) {
        this.masterOnlineRefund = masterOnlineRefund;
    }

    public boolean isJcbOnlineRefund() {
        return jcbOnlineRefund;
    }

    public void setJcbOnlineRefund(boolean jcbOnlineRefund) {
        this.jcbOnlineRefund = jcbOnlineRefund;
    }

    public boolean isEnableMPOSMS() {
        return enableMPOSMS;
    }

    public void setEnableMPOSMS(boolean enableMPOSMS) {
        this.enableMPOSMS = enableMPOSMS;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getFixed() {
        return fixed;
    }

    public void setFixed(double fixed) {
        this.fixed = fixed;
    }

    public boolean isHideSurcharge() {
        return hideSurcharge;
    }

    public void setHideSurcharge(boolean hideSurcharge) {
        this.hideSurcharge = hideSurcharge;
    }

    public String getPartnerLogo() {
        return partnerLogo;
    }

    public void setPartnerLogo(String partnerLogo) {
        this.partnerLogo = partnerLogo;
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

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }
}
