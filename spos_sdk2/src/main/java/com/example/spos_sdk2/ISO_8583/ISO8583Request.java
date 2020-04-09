package com.example.spos_sdk2.ISO_8583;

import android.app.Activity;
import android.util.Log;

import com.example.spos_sdk2.ErrorResult;
import com.example.spos_sdk2.PayData;
import com.example.spos_sdk2.PayResponse;
import com.example.spos_sdk2.PayResult;

public class ISO8583Request {
    
    protected static final String ISO8583_TAG = "ISO8583";

    PayData payData;
    PayResponse response;
    ErrorResult errorResult = new ErrorResult();
    Activity activity;

    public ISO8583Request(Activity activity){
        this.activity = activity;
    }

    public void setPayData(PayData payData){
        this.payData = payData;
    }

    public void process(){

        Thread thread;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                Log.d(ISO8583_TAG, "ISO8583 Reqeuest Start...");

//                ISO8583HttpReq req = new ISO8583HttpReq();
//                final PayResult payResult = req.callISO8583API(activity, payData);
//
//                activity.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        response.getResponse(payResult);
//                    }
//                });

                Void0200Call req = new Void0200Call();
                final PayResult payResult = req.callISO8583API(activity, payData);

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        response.getResponse(payResult);
                    }
                });

//                if(validateCancelData()){
//
//                    Log.d(ISO8583_TAG, "ISO8583 Reqeuest Start...");
//
//                    ISO8583HttpReq req = new ISO8583HttpReq();
//                    final CancelResult result = req.callCancelAPI(cancelData);
//                    req.callISO8583API();
//                    activity.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            response.getResponse(result);
//                        }
//                    });
//
//                }else{
//
//                    Log.d(ISO8583_TAG, "request onError");
//
//                    activity.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            response.onError(errorResult);
//                        }
//                    });
//                }
            }
        };

        thread = new Thread(runnable);
        thread.start();
    }

//    public boolean validateCancelData() {
//
//        boolean isDataValid = false;
//
//        if (cancelData == null){
//            errorResult.setErrCode(ErrorCode.ERR_CANCELDATA);
//            errorResult.setErrMessage("Cancel data cannot be null.");
//        } else if (cancelData.getMerchantId() == null || cancelData.getMerchantId().isEmpty()){
//            errorResult.setErrMessage("Please add the merchant ID.");
//            errorResult.setErrMessage("Please add the merchant ID.");
//        } else if (cancelData.getPayRef() == null ||cancelData.getPayRef().isEmpty()) {
//            errorResult.setErrCode(ErrorCode.ERR_PAYREFNO);
//            errorResult.setErrMessage("Please add the reference no of transaction.");
//        } else if (cancelData.getPayMethod() == null){
//            errorResult.setErrCode(ErrorCode.ERR_PAYMETHOD);
//            errorResult.setErrMessage("Please add the payment method of transaction.");
//        } else if(cancelData.getPayGate() == null){
//            errorResult.setErrCode(ErrorCode.ERR_PAYGATE);
//            errorResult.setErrMessage("Please add the paygate.");
//        } else {
//            isDataValid = true;
//        }
//
//        return isDataValid;
//    }

    public void responseHandler(PayResponse response) {
        setPayResponse(response);
    }

    protected void setPayResponse(PayResponse response) {
        this.response = response;
    }
}
