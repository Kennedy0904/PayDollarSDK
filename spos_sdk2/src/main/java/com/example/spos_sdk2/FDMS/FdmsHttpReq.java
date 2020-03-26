package com.example.spos_sdk2.FDMS;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.spos_sdk2.EnvBase;
import com.example.spos_sdk2.PayGate;
import com.example.spos_sdk2.TrustModifier;
import com.example.spos_sdk2.Utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.example.spos_sdk2.FDMS.FdmsRequest.FDMS_TAG;

public class FdmsHttpReq extends AsyncTask<String, Void, String> {

    FdmsVariable fdmsVariable = null;
    FdmsAsyncResponse delegate = null;//Call back interface
    FdmsRequest FDActivity;
    Context context;

    protected FdmsHttpReq(FdmsAsyncResponse asyncResponse, FdmsVariable fdmsVariable, FdmsRequest activity, Context context) {
        this.delegate = asyncResponse;//Assigning call back interface through constructor
        this.fdmsVariable = fdmsVariable;
        this.FDActivity = activity;
        this.context = context;

    }

    public FdmsHttpReq(FdmsVariable fdmsVariable, FdmsRequest activity, Context context) {
        this.fdmsVariable = fdmsVariable;
        this.context = context;
        this.FDActivity = activity;
    }

    @Override
    protected String doInBackground(String ... strings) {

        String baseUrl = "";
        String result = "";
        Map<String, String> parameters = new HashMap<>();

        if (fdmsVariable.getRequestAction().toString().equalsIgnoreCase("createTxn")) {

            baseUrl = PayGate.getPayCompURL(fdmsVariable.getPayGate());

            parameters.put("merchantId", fdmsVariable.getMerId());
            parameters.put("merName", fdmsVariable.getMerName());
            parameters.put("currCode", fdmsVariable.getCurrCode().toString());
            parameters.put("amount", fdmsVariable.getAmount());
            parameters.put("merRequestAmt", fdmsVariable.getAmount());
            parameters.put("surcharge", fdmsVariable.getSurcharge());
            parameters.put("payType", fdmsVariable.getPayType().toString());
            parameters.put("orderRef", fdmsVariable.getMerRef());
            parameters.put("pMethod", fdmsVariable.getpMethod());
            parameters.put("cardNo", fdmsVariable.getCardNo());
            parameters.put("cardHolder", fdmsVariable.getCardHolder());
            parameters.put("epMonth", fdmsVariable.getEpMonth());
            parameters.put("epYear", fdmsVariable.getEpYear());
            parameters.put("CVV2Data", fdmsVariable.getCVV2Data());
            parameters.put("operatorId", fdmsVariable.getOperatorId());
            parameters.put("useSurcharge", fdmsVariable.getHideSurcharge());
            parameters.put("processingCode", fdmsVariable.getProcessingCode());
            parameters.put("POSEntryMode", fdmsVariable.getPOSEntryMode());
            parameters.put("PANSeqNo", fdmsVariable.getPANSeqNo());
            parameters.put("POSCondtionCode", fdmsVariable.getPOSCondtionCode());
            parameters.put("track2Data", fdmsVariable.getTrack2Data());
            parameters.put("enryptedPIN", fdmsVariable.getEnryptedPIN());
            parameters.put("EMVData", fdmsVariable.getEMVICCRelatedData());
            parameters.put("channelType", "MPOS");

//            parameters.put("invoiceRef", FdmsVariable.getInvoiceRef());
//            parameters.put("batchNo", FdmsVariable.getBatchNo());

        } else if (fdmsVariable.getRequestAction().toString().equalsIgnoreCase("updateFailedTxn")) {

            baseUrl = PayGate.getFDMSReturnURL(fdmsVariable.getPayGate());

            parameters.put("merchantId", fdmsVariable.getMerId());
            parameters.put("orderId", fdmsVariable.getPayRef());
            parameters.put("action", fdmsVariable.getAction());

        } else if (fdmsVariable.getRequestAction().toString().equalsIgnoreCase("updateTxnAccepted")) {
            Log.d(FDMS_TAG, "Start to update txn");
            baseUrl = PayGate.getFDMSReturnURL(fdmsVariable.getPayGate());

            parameters.put("merchantId", fdmsVariable.getMerId());
            parameters.put("orderId", fdmsVariable.getPayRef());
            parameters.put("action", fdmsVariable.getAction());
            parameters.put("invoiceNo", fdmsVariable.getInvoiceNo());
            parameters.put("cardNo", fdmsVariable.getCardNo());
            parameters.put("RRN", fdmsVariable.getRRN());
            parameters.put("batchNo", fdmsVariable.getBatchNo());
            parameters.put("traceNo", fdmsVariable.getTraceNo());
            parameters.put("payMethod", fdmsVariable.getPayMethod());
            parameters.put("appCode", fdmsVariable.getAppCode());
            parameters.put("TC", fdmsVariable.getTc());
            parameters.put("TSI", fdmsVariable.getTsi());
            parameters.put("ATC", fdmsVariable.getAtc());
            parameters.put("TVR", fdmsVariable.getTvr());
            parameters.put("appName", fdmsVariable.getAppName());
            parameters.put("AID", fdmsVariable.getAid());

            Log.d(FDMS_TAG, fdmsVariable.getMerId());
            Log.d(FDMS_TAG, fdmsVariable.getPayRef());
            Log.d(FDMS_TAG, fdmsVariable.getAction());
            Log.d(FDMS_TAG, fdmsVariable.getInvoiceNo());
            Log.d(FDMS_TAG, fdmsVariable.getCardNo());
            Log.d(FDMS_TAG, fdmsVariable.getRRN());
            Log.d(FDMS_TAG, fdmsVariable.getBatchNo());
            Log.d(FDMS_TAG, fdmsVariable.getTraceNo());
            Log.d(FDMS_TAG, fdmsVariable.getPayMethod());
            Log.d(FDMS_TAG, fdmsVariable.getAppCode());
            Log.d(FDMS_TAG, fdmsVariable.getTc());
            Log.d(FDMS_TAG, fdmsVariable.getTsi());
            Log.d(FDMS_TAG, fdmsVariable.getAtc());
            Log.d(FDMS_TAG, fdmsVariable.getTvr());
            Log.d(FDMS_TAG, fdmsVariable.getAppName());
            Log.d(FDMS_TAG, fdmsVariable.getAid());

        } else if (fdmsVariable.getRequestAction().toString().equalsIgnoreCase("updateTxnRejected")
                || fdmsVariable.getRequestAction().toString().equalsIgnoreCase("updateTxnCancelled")) {
            baseUrl = PayGate.getFDMSReturnURL(fdmsVariable.getPayGate());

            parameters.put("merchantId", fdmsVariable.getMerId());
            parameters.put("orderId", fdmsVariable.getPayRef());
            parameters.put("action", fdmsVariable.getAction());
            parameters.put("payMethod", fdmsVariable.getPayMethod());

        } else if (fdmsVariable.getRequestAction().toString().equalsIgnoreCase("voidTxn")) {
            baseUrl = PayGate.getOrderAPIURL(fdmsVariable.getPayGate());

            parameters.put("merchantId", fdmsVariable.getMerId());
            parameters.put("loginId", fdmsVariable.getUserID());
            parameters.put("password", fdmsVariable.getPassword());
            parameters.put("payRef", fdmsVariable.getPayRef());
            parameters.put("actionType", fdmsVariable.getAction());
            parameters.put("invoiceNo", fdmsVariable.getInvoiceNo());
            parameters.put("traceNo", fdmsVariable.getTraceNo());

        } else if (fdmsVariable.getRequestAction().toString().equalsIgnoreCase("settlementTxn")) {
            baseUrl = PayGate.getFDMSReturnURL(fdmsVariable.getPayGate());

            parameters.put("merchantId", fdmsVariable.getMerId());
            parameters.put("payRefArray", fdmsVariable.getPayRefArray());
            parameters.put("action", fdmsVariable.getAction());
        }

        try{
            URL url = new URL(baseUrl);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            TrustModifier.relaxHostChecking(con);

            Log.d(FDMS_TAG, "Params: " + Utils.getParamsString(parameters));

            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(Utils.getParamsString(parameters));
            out.flush();
            out.close();

            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            result = content.toString();

            in.close();
            con.disconnect();

        } catch (Exception e){
            e.printStackTrace();
            Log.d(FDMS_TAG, "IOException Error: " + e.getMessage());
            result = "ConnectionError";
        }
//        catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.d(FDMS_TAG, "IOException Error: " + e.getMessage());
//            result = "resultCode=-1&returnMsg=ConnectionError";
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        }

        Log.d(FDMS_TAG, "Result: " + result);
        return result;
    }

    @Override
    protected void onPostExecute(String result) {

        if (fdmsVariable.getRequestAction().toString().equalsIgnoreCase("createTxn")) {

            Log.d(FDMS_TAG, "FDMS Txn create result: " + result);

            HashMap<String, String> map = PayGate.split(result);
            fdmsVariable.setCreateTxnCode(map.get("successcode"));
            fdmsVariable.setMerRef(map.get("Ref"));
            fdmsVariable.setPayRef(map.get("PayRef"));
            fdmsVariable.setAmount(map.get("Amt"));
            // PayDollar error msg
            fdmsVariable.setErrMsg(map.get("errMsg"));

            if (fdmsVariable.getCreateTxnCode() != null &&
                    fdmsVariable.getCreateTxnCode().equalsIgnoreCase("0")) {
                FDActivity.setFdmsVariables(fdmsVariable);
                FDActivity.saleRequest();
            } else {

                if (fdmsVariable.getErrMsg() != null) {

                    delegate.processFinish(fdmsVariable.getErrMsg());

                    fdmsVariable.setRequestAction(EnvBase.FDRequest.UPDATE_FAILED_TXN);
                    fdmsVariable.setAction("Update_Failed_Txn");

                    FdmsHttpReq request = new FdmsHttpReq(fdmsVariable, FDActivity, context);
                    request.execute();

                } else {
                    delegate.processFinish("Create transaction failed");
                }
            }
        } else if (fdmsVariable.getRequestAction().toString().contains("updateTxn")) {

            Log.d(FDMS_TAG, "FDMS Txn update result: " + result);

            delegate.processFinish(result);

        } else if (fdmsVariable.getRequestAction().toString().contains("voidTxn")) {

            Log.d(FDMS_TAG, "FDMS Txn void result: " + result);

            delegate.processFinish(result);

        } else if (fdmsVariable.getRequestAction().toString().contains("settleTxn")) {

            Log.d(FDMS_TAG, "FDMS Txn settlement result: " + result);

            delegate.processFinish(result);

        }
    }

}
