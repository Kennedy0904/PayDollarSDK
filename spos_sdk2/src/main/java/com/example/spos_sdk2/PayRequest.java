package com.example.spos_sdk2;

import android.app.Activity;
import android.util.Log;

public class PayRequest {

    protected static final String PAYMENT_TAG = "PaymentReq";

    PayData payData;
    PayResponse response;
    ErrorResult errorResult = new ErrorResult();
    Activity activity;

    public PayRequest(Activity activity){
        this.activity = activity;
    }

    public void setPayData(PayData payData){
        this.payData = payData;
    }

    public void process(){

        Thread payThread;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d(PAYMENT_TAG, "Payment Reqeuest Start...");
                if(validatePayData()){

                    Log.d(PAYMENT_TAG, "Payment Reqeuest Start...");

                    PayCall pReq = new PayCall();
                    final PayResult result = pReq.callPaymentAPI(payData);

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            response.getResponse(result);
                        }
                    });

                }else{

                    Log.d(PAYMENT_TAG, "request onError");

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            response.onError(errorResult);
                        }
                    });
                }
            }
        };

        payThread = new Thread(runnable);
        payThread.start();
    }

    public boolean validatePayData() {

        boolean isDataValid = false;

        if (payData == null){
            errorResult.setErrCode(ErrorCode.ERR_PAYDATA);
            errorResult.setErrMessage("Payment data cannot be null.");
        } else if (payData.getMerchantId() == null || payData.getMerchantId().isEmpty()){
            errorResult.setErrCode(ErrorCode.ERR_MERID);
            errorResult.setErrMessage("Please add the merchant ID.");
        } else if (payData.getAmount() == null ||payData.getAmount().isEmpty()) {
            errorResult.setErrCode(ErrorCode.ERR_AMOUNT);
            errorResult.setErrMessage("Please add the amount of transaction.");
        } else if (payData.getCurrCode() == null){
            errorResult.setErrCode(ErrorCode.ERR_CURCODE);
            errorResult.setErrMessage("Please add the currency of transaction.");
        } else if (payData.getPayType() == null){
            errorResult.setErrCode(ErrorCode.ERR_PAYTYPE);
            errorResult.setErrMessage("Please add the payment type of transaction.");
        } else if (payData.getOrderRef() == null || payData.getOrderRef().isEmpty()){
            errorResult.setErrCode(ErrorCode.ERR_MERREFNO);
            errorResult.setErrMessage("Please add the merchant reference no of transaction.");
        } else if (payData.getpMethod() == null){
            errorResult.setErrCode(ErrorCode.ERR_PAYMETHOD);
            errorResult.setErrMessage("Please add the payment method of transaction.");
        } else  if (payData.getPayment() == null){
            errorResult.setErrCode(ErrorCode.ERR_PAYMENT);
            errorResult.setErrMessage("Please add the payment of transaction.");
        } else if(payData.getPayGate() == null){
            errorResult.setErrCode(ErrorCode.ERR_PAYGATE);
            errorResult.setErrMessage("Please add the paygate.");
        } else {
            isDataValid = true;
        }

        if (payData.getPayment() != null && (payData.getTxnNo() == null || payData.getTxnNo().isEmpty())) {
            if (payData.getPayment().equals(EnvBase.Payment.SCAN_QR)) {
                errorResult.setErrCode(ErrorCode.ERR_TXNNO);
                errorResult.setErrMessage("Please add the transaction number");
                isDataValid = false;
            } else if (payData.getPayment().equals(EnvBase.Payment.CARD)) {
                errorResult.setErrCode(ErrorCode.ERR_TXNNO);
                errorResult.setErrMessage("Please add the card number");
                isDataValid = false;
            }else {
                isDataValid = true;
            }
        }

//        if(!isInEnum(payData.getCurrency().toString(), EnvBase.Currency.class)){
//            errorResult.setErrMessage("Invalid currency of transaction.");
//        }
        return isDataValid;
    }


    public <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
        for (E e : enumClass.getEnumConstants()) {
            if(e.name().equals(value)) { return true; }
        }
        return false;
    }

    public void responseHandler(PayResponse response) {
        setPayResponse(response);
    }

    protected void setPayResponse(PayResponse response) {
        this.response = response;
    }
}

