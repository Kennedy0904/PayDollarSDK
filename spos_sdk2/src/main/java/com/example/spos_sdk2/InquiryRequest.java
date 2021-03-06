package com.example.spos_sdk2;

import android.app.Activity;
import android.util.Log;

public class InquiryRequest {

    protected static final String INQUIRY_TAG = "InquiryReq";

    InquiryData inquiryData;
    InquiryResponse response;
    ErrorResult errorResult = new ErrorResult();
    Activity activity;

    public InquiryRequest(Activity activity){
        this.activity = activity;
    }

    public void setInquiryData(InquiryData inquiryData){
        this.inquiryData = inquiryData;
    }

    public void process(){

        Thread thread;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d(INQUIRY_TAG, "Inquiry Reqeuest Start...");
                if(validateInquiryData()){

                    Log.d(INQUIRY_TAG, "Inquiry Reqeuest Start...");

                    InquiryCall req = new InquiryCall();
                    final InquiryResult result = req.callInquiryAPI(inquiryData);

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            response.getResponse(result);
                        }
                    });

                }else{

                    Log.d(INQUIRY_TAG, "request onError");

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

    public boolean validateInquiryData() {

        boolean isDataValid = false;

        if (inquiryData == null){
            errorResult.setErrCode(ErrorCode.ERR_INQUIRYDATA);
            errorResult.setErrMessage("Inquiry data cannot be null.");
        } else if (inquiryData.getMerchantId() == null || inquiryData.getMerchantId().isEmpty()){
            errorResult.setErrCode(ErrorCode.ERR_MERID);
            errorResult.setErrMessage("Please add the merchant ID.");
        } else if (inquiryData.getPayRef() == null ||inquiryData.getPayRef().isEmpty()) {
            errorResult.setErrCode(ErrorCode.ERR_PAYREFNO);
            errorResult.setErrMessage("Please add the reference no of transaction.");
        } else if (inquiryData.getPayMethod() == null){
            errorResult.setErrCode(ErrorCode.ERR_PAYMETHOD);
            errorResult.setErrMessage("Please add the payment method of transaction.");
        } else if(inquiryData.getPayGate() == null){
            errorResult.setErrCode(ErrorCode.ERR_PAYGATE);
            errorResult.setErrMessage("Please add the paygate.");
        } else {
            isDataValid = true;
        }

        return isDataValid;
    }

    public void responseHandler(InquiryResponse response) {
        setInquiryResponse(response);
    }

    protected void setInquiryResponse(InquiryResponse response) {
        this.response = response;
    }

}
