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

import static com.example.spos_sdk2.CancelRequest.CANCEL_TAG;
import static com.example.spos_sdk2.CancelResult.CANCEL_FAILED;
import static com.example.spos_sdk2.CancelResult.CANCEL_SUCCESS;

public class CancelCall {

    private String baseUrl = null;

    CancelResult callCancelAPI(CancelData cancelData){

        String result = "";
        CancelResult cancelResult;
        HashMap<String, String> map;

        try {
            baseUrl = setCancelURL(cancelData.getPayGate(), cancelData.getpMethod());
            URL url = new URL(baseUrl);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            TrustModifier.relaxHostChecking(con);

            Map<String, String> parameters = new HashMap<>();
            parameters.put(Constants.merchantId, cancelData.getMerchantId());
            parameters.put(Constants.orderId, cancelData.getPayRef());
            parameters.put(Constants.pMethod, cancelData.getpMethod().toString());
            parameters.put(Constants.action, "cancel");

            if(cancelData.getpMethod().toString().equals("BOOSTOFFL")){
                parameters.put("currCode", cancelData.getCurrCode());
                parameters.put("cardNo", "4518354303130007");
                parameters.put("payType", "N");
                parameters.put("channelType", "MPOS");
                parameters.put("amount", "1");
                parameters.put("merRequestAmt", "1");
                parameters.put("orderRef", "000001");
                parameters.put("boostRequest", "cancel");
            }

            Log.d(CANCEL_TAG, "merchantId: " + cancelData.getMerchantId());
            Log.d(CANCEL_TAG, "orderId: " + cancelData.getPayRef());
            Log.d(CANCEL_TAG, "pMethod: " + cancelData.getpMethod().toString());

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
            Log.d(CANCEL_TAG, "IOException Error: " + e.getMessage());
            result = "resultCode=-1&returnMsg=ConnectionError";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        Log.d(CANCEL_TAG, "Result: " + result);
        map = PayGate.split(result);
        cancelResult = prepareCancelResult(map);

        return cancelResult;
    }

    private String setCancelURL(EnvBase.PayGate payGate, EnvBase.PayMethod payMethod) throws MalformedURLException {

        String url = null;

        if(payMethod.equals(EnvBase.PayMethod.BOOST)){
            url = PayGate.getPayCompURL(payGate);
        } else if(payMethod.equals(EnvBase.PayMethod.GRABPAY)){
            url = PayGate.getGrabURL(payGate);
        } else if(payMethod.equals(EnvBase.PayMethod.PROMPTPAY)){
            url = PayGate.getPromptPayURL(payGate);
        }

        return url;
    }

    protected CancelResult prepareCancelResult(HashMap<String, String> map) {

//        result = "resultCode=" + resultCode + "&status=" + status + "&errCode=" + errCode + "&returnMsg=" + returnMsg
//                + "&orderId=" + orderId + "&trxTime=" + TrxTime + "&bankRef=" + bankRef;

//        successcode=0&Ref=000074&PayRef=7681516&token=&base64QR=&Amt=1.0&Cur=458&prc=1&src=94&Ord=&Holder=&AuthId=&TId=&MId=MCM0013563&TxTime=2020-03-13 16:31:58.0&errMsg=Transaction completed

        String resultCodeStr = "";
        String status = "";
        String errCode = "";
        String returnMsg = "";
        String orderId = "";
        String trxTime = "";
        String bankRef = "";

        CancelResult result = new CancelResult();

        resultCodeStr = map.get("resultCode");
        status = map.get("status");
        errCode = map.get("errCode");
        returnMsg = map.get("returnMsg") == null ? map.get("errMsg") : map.get("returnMsg");
        orderId = map.get("orderId");
        trxTime = map.get("trxTime");
        bankRef = map.get("bankRef");

        int resultCode = -1;

        // For BOOST only
        String successcode = map.get("successcode") == null ? "" : map.get("successcode");
        if(successcode.equalsIgnoreCase("0")){
            resultCode = CANCEL_SUCCESS;
        }else{
            resultCode = CANCEL_FAILED;
        }

        if(status != null){
            if(status.equalsIgnoreCase("Success")){
                resultCode = CANCEL_SUCCESS;
            }else{
                resultCode = CANCEL_FAILED;
            }
        }

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
