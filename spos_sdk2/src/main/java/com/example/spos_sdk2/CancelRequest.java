package com.example.spos_sdk2;

import android.util.Log;

public class CancelRequest {

    protected static final String CANCEL_TAG = "CancelReq";

    CancelData cancelData;
    CancelResponse response;
    ErrorResult errorResult = new ErrorResult();

    public void setCancelData(CancelData data){
        this.cancelData = data;
    }

    public void process(){

        Thread thread;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d(CANCEL_TAG, "Cancel Reqeuest Start...");

                if(validateCancelData()){

                    Log.d(CANCEL_TAG, "Cancel Reqeuest Start...");

                    CancelCall req = new CancelCall();
                    final CancelResult result = req.callCancelAPI(cancelData);

                    cancelData.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            response.getResponse(result);
                        }
                    });

                }else{

                    Log.d(CANCEL_TAG, "request onError");

                    cancelData.getActivity().runOnUiThread(new Runnable() {
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

    public boolean validateCancelData() {

        boolean isDataValid = false;

        if (cancelData == null){
            errorResult.setErrCode(ErrorCode.ERR_CANCELDATA);
            errorResult.setErrMessage("Cancel data cannot be null.");
        } else if (cancelData.getMerchantId() == null || cancelData.getMerchantId().isEmpty()){
            errorResult.setErrMessage("Please add the merchant ID.");
            errorResult.setErrMessage("Please add the merchant ID.");
        } else if (cancelData.getPayRef() == null ||cancelData.getPayRef().isEmpty()) {
            errorResult.setErrCode(ErrorCode.ERR_PAYREFNO);
            errorResult.setErrMessage("Please add the reference no of transaction.");
        } else if (cancelData.getpMethod() == null){
            errorResult.setErrCode(ErrorCode.ERR_PAYMETHOD);
            errorResult.setErrMessage("Please add the payment method of transaction.");
        } else if(cancelData.getPayGate() == null){
            errorResult.setErrCode(ErrorCode.ERR_PAYGATE);
            errorResult.setErrMessage("Please add the paygate.");
        } else if (cancelData.getActivity() == null) {
            errorResult.setErrCode(ErrorCode.ERR_ACTIVITY);
            errorResult.setErrMessage("Please add the activity");
        } else {
            isDataValid = true;
        }

        return isDataValid;
    }

    public void responseHandler(CancelResponse response) {
        setCancelResponse(response);
    }

    protected void setCancelResponse(CancelResponse response) {
        this.response = response;
    }
}
