package com.example.spos_sdk2;

import android.app.Activity;
import android.util.Log;

public class LoginRequest {

    protected static final String LOGIN_TAG = "LoginReq";

    LoginData loginData;
    LoginResponse response;
    ErrorResult errorResult = new ErrorResult();
    Activity activity;

    public LoginRequest(Activity activity) {
        this.activity = activity;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }

    /** Start Calling PayDollar*/
    public void process() {

        Thread payThread;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if(validateLoginData()){

                    Log.d(LOGIN_TAG, "Login Reqeuest Start...");

                    LoginCall pReq = new LoginCall();
                    final LoginResult result = pReq.callLoginAPI(loginData);

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                             response.getResponse(result);
                        }
                    });
                }else{

                    Log.d(LOGIN_TAG, "request onError");

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

    public boolean validateLoginData() {

        boolean isDataValid = false;

        if (loginData == null) {
            errorResult.setErrMessage("Merchant data cannot be null.");
            errorResult.setErrCode(ErrorCode.ERR_LOGINDATA);
        } else if (loginData.getMerchantId() == null || loginData.getMerchantId().isEmpty()) {
            errorResult.setErrMessage("Please add the merchant ID.");
            errorResult.setErrCode(ErrorCode.ERR_MERID);
        } else if (loginData.getUserId() == null || loginData.getUserId().isEmpty()) {
            errorResult.setErrMessage("Please add the username.");
            errorResult.setErrCode(ErrorCode.ERR_USER);
        } else if (loginData.getPassword() == null || loginData.getPassword().isEmpty()) {
            errorResult.setErrMessage("Please add the user password.");
            errorResult.setErrCode(ErrorCode.ERR_PASSWORD);
        } else if(loginData.getPayGate() == null){
            errorResult.setErrMessage("Please add the paygate.");
            errorResult.setErrCode(ErrorCode.ERR_PAYGATE);
        }
//        else if(loginData.getPayGate() != null &&
//                (!loginData.getPayGate().equals(EnvBase.PayGate.PAYDOLLAR)
//                && !loginData.getPayGate().equals(EnvBase.PayGate.SIAMPAY)
//                && !loginData.getPayGate().equals(EnvBase.PayGate.PESOPAY))){
//                errorResult.setErrMessage("Invalid login channel.");
//        }
//        else if (loginData.getActivity() == null) {
//            errorResult.setErrMessage("Please add the activity");
//            errorResult.setErrCode(ErrorCode.ERR_ACTIVITY);
//        }
        else {
            isDataValid = true;
        }

        return isDataValid;
    }

    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }

    public void responseHandler(LoginResponse response) {
//        this.response = response;
        setPayResponse(response);
    }

    protected void setPayResponse(LoginResponse response) {
        this.response = response;
    }
}
