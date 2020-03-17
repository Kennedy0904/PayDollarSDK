package com.example.spos_sdk2;

import android.util.Log;

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

import static com.example.spos_sdk2.InquiryRequest.INQUIRY_TAG;
import static com.example.spos_sdk2.InquiryResult.INQUIRY_FAILED;
import static com.example.spos_sdk2.InquiryResult.NOT_FOUND;
import static com.example.spos_sdk2.InquiryResult.TXN_FAILED;
import static com.example.spos_sdk2.InquiryResult.TXN_SUCCESS;

public class InquiryCall {

    private String baseUrl = null;

    InquiryResult callInquiryAPI(InquiryData inquiryData){

        String result = "";

        HashMap<String, String> map;
        InquiryResult inquiryResult = new InquiryResult();

        try {
            baseUrl = setInquiryURL(inquiryData.getPayGate(), inquiryData.getpMethod());
            URL url = new URL(baseUrl);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            TrustModifier.relaxHostChecking(con);

            Map<String, String> parameters = new HashMap<>();
            parameters.put(Constants.merchantId, inquiryData.getMerchantId());
            parameters.put(Constants.orderId, inquiryData.getPayRef());
            parameters.put(Constants.pMethod, inquiryData.getpMethod().toString());
            parameters.put(Constants.action, "inquiry");

            Log.d(INQUIRY_TAG, "merchantId: " + inquiryData.getMerchantId());
            Log.d(INQUIRY_TAG, "orderId: " + inquiryData.getPayRef());
            Log.d(INQUIRY_TAG, "pMethod: " + inquiryData.getpMethod().toString());

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

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(INQUIRY_TAG, "Error: " + e.getMessage());
            result = "resultCode=-1&returnMsg=ConnectionError";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        Log.d(INQUIRY_TAG, "Result: " + result);
        map = PayGate.split(result);
        inquiryResult = prepareInquiryResult(map);

        return inquiryResult;
    }

    private String setInquiryURL(EnvBase.PayGate payGate, EnvBase.PayMethod payMethod) throws MalformedURLException {

        String url = null;

        if(payMethod.equals(EnvBase.PayMethod.BOOST)){
            url = PayGate.getBoostURL(payGate);
        } else if(payMethod.equals(EnvBase.PayMethod.GRABPAY)){
            url = PayGate.getGrabURL(payGate);
        } else if(payMethod.equals(EnvBase.PayMethod.PROMPTPAY)){
            url = PayGate.getPromptPayURL(payGate);
        }

        return url;
    }

    protected InquiryResult prepareInquiryResult(HashMap<String, String> map) {

//        result = "resultCode=" + resultCode + "&status=" + status + "&errCode=" + errCode + "&returnMsg=" + returnMsg
//                + "&orderId=" + orderId + "&trxTime=" + TrxTime + "&bankRef=" + bankRef;

//        successcode=2&Ref=000067&PayRef=7681242&Amt=4.0&Cur=458&prc=0&src=0&Ord=&Holder=BOOSTOFFL&PayMethod=BOOSTOFFL&AuthId=&TxTime=2020-03-13
//        14:43:59.0&errMsg=Transaction completed

        String payMethod = map.get("PayMethod") == null ? "" : map.get("PayMethod") ;

        String resultCodeStr = "";
        String status = "";
        String errCode = "";
        String returnMsg = "";
        String orderId = "";
        String trxTime = "";
        String bankRef = "";

        int resultCode = INQUIRY_FAILED;

        if(payMethod.equals("BOOSTOFFL")){
            resultCodeStr = map.get("successcode");
            returnMsg = map.get("errMsg");
            trxTime = map.get("trxTime");
            orderId = map.get("PayRef");

            if(resultCodeStr != null){
                if(resultCodeStr.equalsIgnoreCase("0")){
                    resultCode = TXN_SUCCESS;
                }else if(resultCodeStr.equalsIgnoreCase("1")){
                    resultCode = TXN_FAILED;
                }else {
                    resultCode = NOT_FOUND;
                }
            }

        }else {
            resultCodeStr = map.get("resultCode");
            status = map.get("status");
            errCode = map.get("errCode");
            returnMsg = map.get("returnMsg");
            orderId = map.get("orderId");
            trxTime = map.get("trxTime");
            bankRef = map.get("bankRef");

            if(status != null){
                if(status.equalsIgnoreCase("Authorized") || status.equalsIgnoreCase("Success")){
                    resultCode = TXN_SUCCESS;
                }else if(status.equalsIgnoreCase("Failed")){
                    resultCode = TXN_FAILED;
                }else {
                    resultCode = NOT_FOUND;
                }
            }
        }

        InquiryResult result = new InquiryResult();

        result.setResultCode(resultCode);
//        result.setStatus(status);
//        result.setErrCode(errCode);
        result.setReturnMsg(returnMsg);
        result.setPayRef(orderId);
        result.setTxnTime(trxTime);
        result.setBankRef(bankRef);

        return result;
    }
}
