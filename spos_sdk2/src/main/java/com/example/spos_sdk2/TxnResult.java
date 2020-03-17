package com.example.spos_sdk2;

public class TxnResult {

    public final static int SUCCESS = 0;
    public final static int FAILED = -1;

    private int resultCode;
    private String orderStatus;
    private String merRef;
    private String payRef;
    private String amount;
    private String curCode;
    private String returnMsg;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

//    public String getOrderStatus() {
//        return orderStatus;
//    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

//    public String getMerRef() {
//        return merRef;
//    }

    public void setMerRef(String merRef) {
        this.merRef = merRef;
    }

//    public String getPayRef() {
//        return payRef;
//    }

    public void setPayRef(String payRef) {
        this.payRef = payRef;
    }

//    public String getAmount() {
//        return amount;
//    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

//    public String getCurCode() {
//        return curCode;
//    }

    public void setCurCode(String curCode) {
        this.curCode = curCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
}
