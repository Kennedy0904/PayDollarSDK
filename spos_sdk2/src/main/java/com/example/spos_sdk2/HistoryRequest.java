package com.example.spos_sdk2;

import android.util.Log;

import java.util.ArrayList;

public class HistoryRequest {

    protected static final String HISTORY_TAG = "HistoryReq";

    HistoryData historyData;
    HistoryResponse response;
    ErrorResult errorResult = new ErrorResult();

    public void setHistoryData(HistoryData historyData) {

        this.historyData = historyData;
    }

    public void process() {

        Thread thread;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d(HISTORY_TAG, "History Reqeuest Start...");

                if(validateHistoryData()){
                    HistoryCall req = new HistoryCall();
//                final ArrayList<Record> records = req.callHistoryAPI();

                    final String records = req.callHistoryAPI(historyData);

                    historyData.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            response.getResponse(records);
                        }
                    });
                }else{

                    Log.d(HISTORY_TAG, "request onError");

                    historyData.getActivity().runOnUiThread(new Runnable() {
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

    private boolean validateHistoryData() {

        boolean isDataValid = false;

        if (historyData == null){
            errorResult.setErrCode(ErrorCode.ERR_HISTORYDATA);
            errorResult.setErrMessage("Payment data cannot be null.");
        } else if (historyData.getMerchantId() == null || historyData.getMerchantId().isEmpty()){
            errorResult.setErrCode(ErrorCode.ERR_MERID);
            errorResult.setErrMessage("Please add the merchant ID.");
        } else if (historyData.getApiId() == null ||historyData.getApiId().isEmpty()) {
            errorResult.setErrCode(ErrorCode.ERR_APIID);
            errorResult.setErrMessage("Please add the API userID.");
        } else if (historyData.getApiPassword() == null ||historyData.getApiPassword().isEmpty()) {
            errorResult.setErrCode(ErrorCode.ERR_APIPASSWORD);
            errorResult.setErrMessage("Please add the API Password.");
        } else if (historyData.getStartDate() == null ||historyData.getStartDate().isEmpty()) {
            errorResult.setErrCode(ErrorCode.ERR_STARTDATE);
            errorResult.setErrMessage("Please add the start date.");
        } else if (historyData.getEndDate() == null ||historyData.getEndDate().isEmpty()) {
            errorResult.setErrCode(ErrorCode.ERR_ENDDATE);
            errorResult.setErrMessage("Please add the end date.");
        } else if(historyData.getPayGate() == null){
            errorResult.setErrCode(ErrorCode.ERR_PAYGATE);
            errorResult.setErrMessage("Please add the paygate.");
        } else if (historyData.getActivity() == null) {
            errorResult.setErrCode(ErrorCode.ERR_ACTIVITY);
            errorResult.setErrMessage("Please add the activity");
        } else {
            isDataValid = true;
        }

        return isDataValid;
    }

    public void responseHandler(HistoryResponse response) {
        setHistoryResponse(response);
    };

    protected void setHistoryResponse(HistoryResponse response) {
        this.response = response;
    }
}
