package com.example.spos_sdk2.FDMS;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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
    FdmsApiFunction FDActivity;
    Context context;

    protected FdmsHttpReq(FdmsAsyncResponse asyncResponse, FdmsVariable fdmsVariable, FdmsApiFunction activity, Context context) {
        this.delegate = asyncResponse;//Assigning call back interface through constructor
        this.fdmsVariable = fdmsVariable;
        this.FDActivity = activity;
        this.context = context;

    }

    public FdmsHttpReq(FdmsVariable fdmsVariable, FdmsApiFunction activity, Context context) {
        this.fdmsVariable = fdmsVariable;
        this.context = context;
        this.FDActivity = activity;
    }

    @Override
    protected String doInBackground(String ... strings) {

        String baseUrl = "";
        String result = "";
        Map<String, String> parameters = new HashMap<>();

        if (fdmsVariable.getRequestAction().equalsIgnoreCase("createTxn")) {
            baseUrl = PayGate.getPayCompURL(fdmsVariable.getPayGate());

            parameters.put("merchantId", fdmsVariable.getMerId());
            parameters.put("merName", fdmsVariable.getMerName());
            parameters.put("currCode", fdmsVariable.getCurrCode());
            parameters.put("amount", fdmsVariable.getAmount());
            parameters.put("merRequestAmt", fdmsVariable.getMerRequestAmt());
            parameters.put("surcharge", fdmsVariable.getSurcharge());
            parameters.put("payType", fdmsVariable.getPayType());
            parameters.put("orderRef", fdmsVariable.getMerRef());
            parameters.put("pMethod", fdmsVariable.getpMethod());
            parameters.put("cardNo", fdmsVariable.getCardNo());
            parameters.put("cardHolder", fdmsVariable.getCardHolder());
            parameters.put("epMonth", fdmsVariable.getEpMonth());
            parameters.put("epYear", fdmsVariable.getEpYear());
            parameters.put("CVV2Data", fdmsVariable.getCVV2Data());
            parameters.put("operatorId", fdmsVariable.getOperatorId());
            parameters.put("channelType", fdmsVariable.getChannel());
            parameters.put("useSurcharge", fdmsVariable.getHideSurcharge());
            parameters.put("processingCode", fdmsVariable.getProcessingCode());
            parameters.put("POSEntryMode", fdmsVariable.getPOSEntryMode());
            parameters.put("PANSeqNo", fdmsVariable.getPANSeqNo());
            parameters.put("POSCondtionCode", fdmsVariable.getPOSCondtionCode());
            parameters.put("track2Data", fdmsVariable.getTrack2Data());
            parameters.put("enryptedPIN", fdmsVariable.getEnryptedPIN());
            parameters.put("EMVData", fdmsVariable.getEMVICCRelatedData());
//            parameters.put("invoiceRef", FdmsVariable.getInvoiceRef());
//            parameters.put("batchNo", FdmsVariable.getBatchNo());

        } else if (fdmsVariable.getRequestAction().equalsIgnoreCase("updateFailedTxn")) {
            baseUrl = PayGate.getFDMSReturnURL(fdmsVariable.getPayGate());

            parameters.put("merchantId", fdmsVariable.getMerId());
            parameters.put("orderId", fdmsVariable.getPayRef());
            parameters.put("action", fdmsVariable.getAction());

        } else if (fdmsVariable.getRequestAction().equalsIgnoreCase("updateTxnAccepted")) {
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

        } else if (fdmsVariable.getRequestAction().equalsIgnoreCase("updateTxnRejected")
                || fdmsVariable.getRequestAction().equalsIgnoreCase("updateTxnCancelled")) {
            baseUrl = PayGate.getFDMSReturnURL(fdmsVariable.getPayGate());

            parameters.put("merchantId", fdmsVariable.getMerId());
            parameters.put("orderId", fdmsVariable.getPayRef());
            parameters.put("action", fdmsVariable.getAction());
            parameters.put("payMethod", fdmsVariable.getPayMethod());

        } else if (fdmsVariable.getRequestAction().equalsIgnoreCase("voidTxn")) {
            baseUrl = PayGate.getOrderAPIURL(fdmsVariable.getPayGate());

            parameters.put("merchantId", fdmsVariable.getMerId());
            parameters.put("loginId", fdmsVariable.getUserID());
            parameters.put("password", fdmsVariable.getPassword());
            parameters.put("payRef", fdmsVariable.getPayRef());
            parameters.put("actionType", fdmsVariable.getAction());
            parameters.put("invoiceNo", fdmsVariable.getInvoiceNo());
            parameters.put("traceNo", fdmsVariable.getTraceNo());

        } else if (fdmsVariable.getRequestAction().equalsIgnoreCase("settlementTxn")) {
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

        if (fdmsVariable.getRequestAction().equalsIgnoreCase("createTxn")) {

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
                FDActivity.saleRequest(fdmsVariable);
            } else {

                if (fdmsVariable.getErrMsg() != null) {

                    delegate.processFinish(fdmsVariable.getErrMsg());

                    fdmsVariable.setRequestAction("updateFailedTxn");
                    fdmsVariable.setAction("Update_Failed_Txn");

                    FdmsHttpReq request = new FdmsHttpReq(fdmsVariable, FDActivity, context);
                    request.execute();

                } else {
                    delegate.processFinish("Create transaction failed");
                }
            }
        } else if (fdmsVariable.getRequestAction().contains("updateTxn")) {

            Log.d(FDMS_TAG, "FDMS Txn update result: " + result);

            delegate.processFinish(result);

        } else if (fdmsVariable.getRequestAction().contains("voidTxn")) {

            Log.d(FDMS_TAG, "FDMS Txn void result: " + result);

            delegate.processFinish(result);

        } else if (fdmsVariable.getRequestAction().contains("settleTxn")) {

            Log.d(FDMS_TAG, "FDMS Txn settlement result: " + result);

            delegate.processFinish(result);

        }
    }

}
