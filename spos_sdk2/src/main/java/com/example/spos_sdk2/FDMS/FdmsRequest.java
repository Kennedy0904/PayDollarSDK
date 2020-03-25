package com.example.spos_sdk2.FDMS;

import android.app.Activity;
import android.content.Context;

import com.example.spos_sdk2.ErrorResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.lang.Integer.parseInt;

public class FdmsRequest extends Activity {

    protected static final String FDMS_TAG = "FDMS";

    FdmsResponse response;
    ErrorResult errorResult = new ErrorResult();

    Context context;
    public FdmsRequest(Context context) {
        this.context = context;
    }

    FdmsApiFunction createtxn;

    public void processTransaction() {
        // Paydollar create Txn
        // 'requestAction' used for send request in 'FdmsHttpRequest'

        Calendar c = Calendar.getInstance();
        String epMonth = new SimpleDateFormat("MM").format(c.getTime());
        //System.out.println("epMonth: " + epMonth);

        String year = new SimpleDateFormat("yyyy").format(c.getTime());
        String epYear = String.valueOf(parseInt(year) + 3);

//        FdmsVariable.setRequestAction("createTxn");
//        FdmsVariable.setCardNo("4518354303137777");
//        FdmsVariable.setSurcharge("0");
//        FdmsVariable.setpMethod("VISA");
//        FdmsVariable.setCardHolder("");
//        FdmsVariable.setEpMonth(epMonth);
//        FdmsVariable.setEpYear(epYear);
//        FdmsVariable.setOperatorId("admin");
//        FdmsVariable.setChannel("MPOS");

//        createtxn = new FdmsApiFunction(context);
//        FdmsHttpReq request = new FdmsHttpReq(new FdmsAsyncResponse() {
//            @Override
//            public void processFinish(String output) {
////                response.getResponse(output);
//            }
//        }, createtxn, context);
//        request.execute();
    }

//    public void process() {
//
//        Thread thread;
//
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                Log.d(FDMS_TAG, "FDMS Reqeuest Start...");
//
//                FdmsCall req = new FdmsCall();
//                final String result = req.callFDMSAPI();
//
//                activity.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        response.getResponse(result);
//                    }
//                });
//            }
//        };
//
//        thread = new Thread(runnable);
//        thread.start();
//    }
}
