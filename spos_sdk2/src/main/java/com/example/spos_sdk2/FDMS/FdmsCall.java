package com.example.spos_sdk2.FDMS;

import android.util.Log;

import com.example.spos_sdk2.PayGate;
import com.example.spos_sdk2.TrustModifier;
import com.example.spos_sdk2.Utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static com.example.spos_sdk2.FDMS.FdmsRequest.FDMS_TAG;

public class FdmsCall {

    String callFDMSAPI() {

        String baseUrl = "";
        String result = "";
//        Map<String, String> parameters = new HashMap<>();
//
//        if (FdmsVariable.getRequestAction().equalsIgnoreCase("createTxn")) {
//            baseUrl = PayGate.getPayCompURL(FdmsVariable.getPayGate());
//
//            parameters.put("merchantId", FdmsVariable.getMerId());
//            parameters.put("merName", FdmsVariable.getMerName());
//            parameters.put("currCode", FdmsVariable.getCurrCode());
//            parameters.put("amount", FdmsVariable.getAmount());
//            parameters.put("merRequestAmt", FdmsVariable.getMerRequestAmt());
//            parameters.put("surcharge", FdmsVariable.getSurcharge());
//            parameters.put("payType", FdmsVariable.getPayType());
//            parameters.put("orderRef", FdmsVariable.getMerRef());
//            parameters.put("pMethod", FdmsVariable.getpMethod());
//            parameters.put("cardNo", FdmsVariable.getCardNo());
//            parameters.put("cardHolder", FdmsVariable.getCardHolder());
//            parameters.put("epMonth", FdmsVariable.getEpMonth());
//            parameters.put("epYear", FdmsVariable.getEpYear());
//            parameters.put("CVV2Data", FdmsVariable.getCVV2Data());
//            parameters.put("operatorId", FdmsVariable.getOperatorId());
//            parameters.put("channelType", FdmsVariable.getChannel());
//            parameters.put("useSurcharge", FdmsVariable.getHideSurcharge());
//            parameters.put("processingCode", FdmsVariable.getProcessingCode());
//            parameters.put("POSEntryMode", FdmsVariable.getPOSEntryMode());
//            parameters.put("PANSeqNo", FdmsVariable.getPANSeqNo());
//            parameters.put("POSCondtionCode", FdmsVariable.getPOSCondtionCode());
//            parameters.put("track2Data", FdmsVariable.getTrack2Data());
//            parameters.put("enryptedPIN", FdmsVariable.getEnryptedPIN());
//            parameters.put("EMVData", FdmsVariable.getEMVICCRelatedData());
////            parameters.put("invoiceRef", FdmsVariable.getInvoiceRef());
////            parameters.put("batchNo", FdmsVariable.getBatchNo());
//
//        } else if (FdmsVariable.getRequestAction().equalsIgnoreCase("updateFailedTxn")) {
//            baseUrl = PayGate.getFDMSReturnURL(FdmsVariable.getPayGate());
//
//            parameters.put("merchantId", FdmsVariable.getMerId());
//            parameters.put("orderId", FdmsVariable.getPayRef());
//            parameters.put("action", FdmsVariable.getAction());
//
//        } else if (FdmsVariable.getRequestAction().equalsIgnoreCase("updateTxnAccepted")) {
//            baseUrl = PayGate.getFDMSReturnURL(FdmsVariable.getPayGate());
//
//            parameters.put("merchantId", FdmsVariable.getMerId());
//            parameters.put("orderId", FdmsVariable.getPayRef());
//            parameters.put("action", FdmsVariable.getAction());
//            parameters.put("invoiceNo", FdmsVariable.getInvoiceNo());
//            parameters.put("cardNo", FdmsVariable.getCardNo());
//            parameters.put("RRN", FdmsVariable.getRRN());
//            parameters.put("batchNo", FdmsVariable.getBatchNo());
//            parameters.put("traceNo", FdmsVariable.getTraceNo());
//            parameters.put("payMethod", FdmsVariable.getPayMethod());
//            parameters.put("appCode", FdmsVariable.getAppCode());
//            parameters.put("TC", FdmsVariable.getTc());
//            parameters.put("TSI", FdmsVariable.getTsi());
//            parameters.put("ATC", FdmsVariable.getAtc());
//            parameters.put("TVR", FdmsVariable.getTvr());
//            parameters.put("appName", FdmsVariable.getAppName());
//            parameters.put("AID", FdmsVariable.getAid());
//
//        } else if (FdmsVariable.getRequestAction().equalsIgnoreCase("updateTxnRejected")
//                || FdmsVariable.getRequestAction().equalsIgnoreCase("updateTxnCancelled")) {
//            baseUrl = PayGate.getFDMSReturnURL(FdmsVariable.getPayGate());
//
//            parameters.put("merchantId", FdmsVariable.getMerId());
//            parameters.put("orderId", FdmsVariable.getPayRef());
//            parameters.put("action", FdmsVariable.getAction());
//            parameters.put("payMethod", FdmsVariable.getPayMethod());
//
//        } else if (FdmsVariable.getRequestAction().equalsIgnoreCase("voidTxn")) {
//            baseUrl = PayGate.getOrderAPIURL(FdmsVariable.getPayGate());
//
//            parameters.put("merchantId", FdmsVariable.getMerId());
//            parameters.put("loginId", FdmsVariable.getUserID());
//            parameters.put("password", FdmsVariable.getPassword());
//            parameters.put("payRef", FdmsVariable.getPayRef());
//            parameters.put("actionType", FdmsVariable.getAction());
//            parameters.put("invoiceNo", FdmsVariable.getInvoiceNo());
//            parameters.put("traceNo", FdmsVariable.getTraceNo());
//
//        } else if (FdmsVariable.getRequestAction().equalsIgnoreCase("settlementTxn")) {
//            baseUrl = PayGate.getFDMSReturnURL(FdmsVariable.getPayGate());
//
//            parameters.put("merchantId", FdmsVariable.getMerId());
//            parameters.put("payRefArray", FdmsVariable.getPayRefArray());
//            parameters.put("action", FdmsVariable.getAction());
//        }
//
//        try{
//            URL url = new URL(baseUrl);
//
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("POST");
//            TrustModifier.relaxHostChecking(con);
//
//            Log.d(FDMS_TAG, "Params: " + Utils.getParamsString(parameters));
//
//            con.setDoOutput(true);
//            DataOutputStream out = new DataOutputStream(con.getOutputStream());
//            out.writeBytes(Utils.getParamsString(parameters));
//            out.flush();
//            out.close();
//
//            con.setConnectTimeout(5000);
//            con.setReadTimeout(5000);
//
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuffer content = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                content.append(inputLine);
//            }
//
//            result = content.toString();
//
//            in.close();
//            con.disconnect();
//
//        } catch (MalformedURLException e) {
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
//
//        Log.d(FDMS_TAG, "Result: " + result);
        return result;
    }
}

