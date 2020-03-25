package com.example.spos_sdk2;

import android.app.Activity;
import android.util.Log;

public class TxnRequest {

    protected static final String TXN_TAG = "TransactionReq";

    TxnData txnData;
    TxnResponse response;
    ErrorResult errorResult = new ErrorResult();
    Activity activity;

    public TxnRequest(Activity activity){
        this.activity = activity;
    }

    public void setTxnData(TxnData txnData){
        this.txnData = txnData;
    }

    public void process(){

        Thread thread;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d(TXN_TAG, "Transaction Reqeuest Start...");
                if(validateTxnData()){

                    Log.d(TXN_TAG, "Transaction Reqeuest Start...");

                    TxnCall req = new TxnCall();
                    final TxnResult result = req.callVoidAPI(txnData);

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            response.getResponse(result);
                        }
                    });

                }else{

                    Log.d(TXN_TAG, "request onError");

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            response.onError(errorResult);
                        }
                    });
                }
            }
        };

        thread = new Thread(runnable);
        thread.start();
    }

    public boolean validateTxnData() {

        boolean isDataValid = false;

        if (txnData == null){
            errorResult.setErrCode(ErrorCode.ERR_TXNDATA);
            errorResult.setErrMessage("Transaction data cannot be null.");
        } else if (txnData.getMerchantId() == null || txnData.getMerchantId().isEmpty()){
            errorResult.setErrCode(ErrorCode.ERR_MERID);
            errorResult.setErrMessage("Please add the merchant ID.");
        } else if(txnData.getActionType() == null){
            errorResult.setErrCode(ErrorCode.ERR_TXNACTION);
            errorResult.setErrMessage("Please add the transaction action.");
        } else if (txnData.getApiId() == null || txnData.getApiId().isEmpty()) {
            errorResult.setErrCode(ErrorCode.ERR_APIID);
            errorResult.setErrMessage("Please add the API userID.");
        } else if (txnData.getApiPassword() == null || txnData.getApiPassword().isEmpty()) {
            errorResult.setErrCode(ErrorCode.ERR_APIPASSWORD);
            errorResult.setErrMessage("Please add the API Password.");
        } else if (txnData.getPayRef() == null || txnData.getPayRef().isEmpty()){
            errorResult.setErrCode(ErrorCode.ERR_PAYREFNO);
            errorResult.setErrMessage("Please add the PayDollar reference no of transaction.");
        } else if(txnData.getPayGate() == null){
            errorResult.setErrCode(ErrorCode.ERR_PAYGATE);
            errorResult.setErrMessage("Please add the paygate.");
        } else {
            isDataValid = true;
        }

        if (txnData.getActionType() != null && (txnData.getAmount() == null || txnData.getAmount().isEmpty())) {
            if (txnData.getActionType().equals(EnvBase.TxnAction.PARTIAL_REFUND)) {
                errorResult.setErrCode(ErrorCode.ERR_AMOUNT);
                errorResult.setErrMessage("Please add the amount of transaction.");
                isDataValid = false;
            }
        }

        return isDataValid;
    }

    public void responseHandler(TxnResponse response) {
        setVoidResponse(response);
    }

    protected void setVoidResponse(TxnResponse response) {
        this.response = response;
    }
}
