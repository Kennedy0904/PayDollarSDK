package com.example.spos_sdk2;

import android.content.Context;
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

import static com.example.spos_sdk2.TxnRequest.TXN_TAG;
import static com.example.spos_sdk2.TxnResult.FAILED;
import static com.example.spos_sdk2.TxnResult.SUCCESS;

public class TxnCall {

    String baseUrl = null;

    TxnResult callVoidAPI(TxnData txnData){

        String result = "";

        HashMap<String, String> map;
        TxnResult txnResult = new TxnResult();

        try {
            baseUrl = PayGate.getOrderAPIURL(txnData.getPayGate());
            URL url = new URL(baseUrl);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            TrustModifier.relaxHostChecking(con);

            String amount = txnData.getAmount() == null ? "" : txnData.getAmount();
            Map<String, String> parameters = new HashMap<>();
            parameters.put(Constants.merchantId, txnData.getMerchantId());
            parameters.put(Constants.amount, amount);
            parameters.put(Constants.payRef, txnData.getPayRef());
            parameters.put(Constants.loginId, txnData.getApiId());
            parameters.put(Constants.password, txnData.getApiPassword());
            parameters.put(Constants.actionType, txnData.getActionType().toString());

            Log.d(TXN_TAG, "merchantId: " + txnData.getMerchantId());
            Log.d(TXN_TAG, "amount: " + amount);
            Log.d(TXN_TAG, "payRef: " + txnData.getPayRef());
            Log.d(TXN_TAG, "loginId: " + txnData.getApiId());
            Log.d(TXN_TAG, "password: " + txnData.getApiPassword());
            Log.d(TXN_TAG, "actionType: " + txnData.getActionType().toString());

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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        Log.d(TXN_TAG, "Result: " + result);
        map = PayGate.split(result);
        txnResult = prepareTxnResult(map);

        return txnResult;
    }

    protected TxnResult prepareTxnResult(HashMap<String, String> map) {

//        resultCode=-1&orderStatus=&ref=&payRef=&amt=&cur=&errMsg=Parameter Payment Reference Number Incorrect.

        String resultCodeStr = "";
        String orderStatus = "";
        String payRef = "";
        String amount = "";
        String returnMsg = "";

        TxnResult result = new TxnResult();

        resultCodeStr = map.get("resultCode");
        orderStatus = map.get("orderStatus");
        payRef = map.get("payRef");
        amount = map.get("amt");
        returnMsg = map.get("errMsg");

        int resultCode = -1;
        if(resultCodeStr.equals("0")){
            resultCode = SUCCESS;
        }else if(resultCodeStr.equals("-1")){
            resultCode = FAILED;
        }

        result.setResultCode(resultCode);
        result.setOrderStatus(orderStatus);
        result.setAmount(amount);
        result.setPayRef(payRef);
        result.setReturnMsg(returnMsg);

        return result;
    }

}
