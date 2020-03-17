package com.example.spos_sdk2;

import android.app.Activity;

public class HistoryData {

    String merchantId;
    String apiId;
    String apiPassword;
    String startDate;
    String endDate;
    String enableMobile;
    EnvBase.SortOrder sortOrder;
    String operatorId;
    EnvBase.OrderStatus orderStatus;
    String payRef;
    String orderRef;
    int pageNumber;
    int pageRecords;
    private EnvBase.PayGate payGate;
    Activity activity;

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

//    public String getEnableMobile() {
//        return enableMobile;
//    }
//
//    public void setEnableMobile(String enableMobile) {
//        this.enableMobile = enableMobile;
//    }

    public EnvBase.SortOrder getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(EnvBase.SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public EnvBase.OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(EnvBase.OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPayRef() {
        return payRef;
    }

    public void setPayRef(String payRef) {
        this.payRef = payRef;
    }

    public String getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(String orderRef) {
        this.orderRef = orderRef;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageRecords() {
        return pageRecords;
    }

    public void setPageRecords(int pageRecords) {
        this.pageRecords = pageRecords;
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
