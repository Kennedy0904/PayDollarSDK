package com.example.spos_sdk2.Settlement;

import android.app.Activity;
import android.util.Log;

import com.example.spos_sdk2.ErrorCode;
import com.example.spos_sdk2.ErrorResult;

public class SettlementRequest {

    protected static final String SETTLEMENT_TAG = "SettlementReq";

    SettlementData settlementData;
    SettlementResponse response;
    ErrorResult errorResult = new ErrorResult();
    Activity activity;

    public SettlementRequest(Activity activity){
        this.activity = activity;
    }

    public void setSettlementData(SettlementData settlementData) {
        this.settlementData = settlementData;
    }

    public void process() {

        Thread thread;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d(SETTLEMENT_TAG, "Settlement Reqeuest Start...");

                if(validateSettlementData()){
                    SettlementCall req = new SettlementCall();
                    final String records = req.callSettlementAPI(settlementData);

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            response.getResponse(records);
                        }
                    });
                }else{

                    Log.d(SETTLEMENT_TAG, "request onError");

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

    private boolean validateSettlementData() {

        boolean isDataValid = false;

        if (settlementData == null){
            errorResult.setErrCode(ErrorCode.ERR_SETTLEMENTDATA);
            errorResult.setErrMessage("Settlement data cannot be null.");
        } else if (settlementData.getMerchantId() == null || settlementData.getMerchantId().isEmpty()){
            errorResult.setErrCode(ErrorCode.ERR_MERID);
            errorResult.setErrMessage("Please add the merchant ID.");
        } else if (settlementData.getApiId() == null ||settlementData.getApiId().isEmpty()) {
            errorResult.setErrCode(ErrorCode.ERR_APIID);
            errorResult.setErrMessage("Please add the API userID.");
        } else if (settlementData.getApiPassword() == null ||settlementData.getApiPassword().isEmpty()) {
            errorResult.setErrCode(ErrorCode.ERR_APIPASSWORD);
            errorResult.setErrMessage("Please add the API Password.");
        } else if (settlementData.getBatchNo() == null ||settlementData.getBatchNo().isEmpty()) {
            errorResult.setErrCode(ErrorCode.ERR_BATCHNO);
            errorResult.setErrMessage("Please add the batch number");
        } else if (settlementData.getPayBankId() == null ||settlementData.getPayBankId().isEmpty()) {
            errorResult.setErrCode(ErrorCode.ERR_PAYBANKID);
            errorResult.setErrMessage("Please add the acquire bank ID.");
        } else if(settlementData.getPayGate() == null){
            errorResult.setErrCode(ErrorCode.ERR_PAYGATE);
            errorResult.setErrMessage("Please add the paygate.");
        } else {
            isDataValid = true;
        }

        return isDataValid;
    }

    public void responseHandler(SettlementResponse response) {
        setHistoryResponse(response);
    };

    private void setHistoryResponse(SettlementResponse response) {
        this.response = response;
    }
}
