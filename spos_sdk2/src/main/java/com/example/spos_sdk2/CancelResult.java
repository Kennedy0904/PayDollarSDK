package com.example.spos_sdk2;

public class CancelResult {

    public final static int CANCEL_SUCCESS = 0;
    public final static int CANCEL_FAILED = -1;

    private int resultCode;
    private String status;
    private String errCode;
    private String returnMsg;
    private String payRef;
    private String txnTime;
    private String bankRef;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }

//    public String getErrCode() {
//        return errCode;
//    }
//
//    public void setErrCode(String errCode) {
//        this.errCode = errCode;
//    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getPayRef() {
        return payRef;
    }

    public void setPayRef(String payRef) {
        this.payRef = payRef;
    }

    public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public String getBankRef() {
        return bankRef;
    }

    public void setBankRef(String bankRef) {
        this.bankRef = bankRef;
    }
}


