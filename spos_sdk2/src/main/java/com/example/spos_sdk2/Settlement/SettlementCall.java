package com.example.spos_sdk2.Settlement;

import android.util.Log;

import com.example.spos_sdk2.Constants;
import com.example.spos_sdk2.EnvBase;
import com.example.spos_sdk2.HistoryData;
import com.example.spos_sdk2.PayGate;
import com.example.spos_sdk2.TrustModifier;
import com.example.spos_sdk2.Utils;

import org.json.JSONException;
import org.json.JSONObject;

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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.example.spos_sdk2.Settlement.SettlementRequest.SETTLEMENT_TAG;

public class SettlementCall {

    private String baseUrl = null;

    String callSettlementAPI(SettlementData settlementData){

        String result = "";

        HashMap<String, String> map;

        try {
            baseUrl = PayGate.getSettlementURL(settlementData.getPayGate());
            URL url = new URL(baseUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            TrustModifier.relaxHostChecking(con);

            int pageNo = 1;
            int pageRecords = 999999;
            Calendar c = Calendar.getInstance();
            String startDay = "01/01/2015";
            String today = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
            String d1 = startDay.replace("/", "") + "000000";
            String d2 = today.replace("/", "") + "235959";
            String merchantId = settlementData.getMerchantId();
            String loginId = settlementData.getApiId();
            String password = settlementData.getApiPassword();
            String batchNo = settlementData.getBatchNo();
            String payBankId = settlementData.getPayBankId();
            String operatorId = settlementData.getOperatorId() == null ? "" : settlementData.getOperatorId();

            Map<String, String> parameters = new HashMap<>();
            parameters.put(Constants.merchantId, merchantId);
            parameters.put(Constants.loginId, loginId);
            parameters.put(Constants.password, password);
            parameters.put(Constants.startDate, d1);
            parameters.put(Constants.endDate, d2);
            parameters.put(Constants.enableMobile, "T");
            parameters.put(Constants.sortOrder, "desc");
            parameters.put(Constants.batchNo, batchNo);
            parameters.put(Constants.payBankId, payBankId);
            parameters.put(Constants.operatorId, operatorId);
            parameters.put(Constants.settlementStatus, "F");
            parameters.put(Constants.pageNo, String.valueOf(pageNo));
            parameters.put(Constants.pageRecords, String.valueOf(pageRecords));

            Log.d(SETTLEMENT_TAG, "merchantId: " + merchantId);
            Log.d(SETTLEMENT_TAG, "loginId: " + loginId);
            Log.d(SETTLEMENT_TAG, "password: " + password);
            Log.d(SETTLEMENT_TAG, "startDate: " + d1);
            Log.d(SETTLEMENT_TAG, "endDate: " + d2);
            Log.d(SETTLEMENT_TAG, "batchNo: " + batchNo);
            Log.d(SETTLEMENT_TAG, "payBankId: " + payBankId);
            Log.d(SETTLEMENT_TAG, "operatorId: " + operatorId);

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
            Log.d(SETTLEMENT_TAG, "IOException: " + e.toString());
            result = "Connection Error";
//            result = "{\"error\":\"Connection Error\"}";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        try {
            result = prepareSettlementResult(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(SETTLEMENT_TAG, "Result: " + result);

        return result;
    }

    private String prepareSettlementResult(String result) throws JSONException {

        String errorMsg = "";
        boolean error = true;

        if (result.toLowerCase().contains("invalid login")) {
            errorMsg = "Invalid API Login ID";
        } else if(result.toLowerCase().contains("invalid password")){
            errorMsg = "Invalid API Login Password";
        } else if(result.toLowerCase().contains("connection error")){
            errorMsg = "Connection Error";
        } else{
            error = false;
        }

        JSONObject json;
        if(error){
            json = new JSONObject();
            json.put("resultCode", -1);
            json.put("error", errorMsg);
        }else {
            json = new JSONObject(result);
            json.put("resultCode", 0);
        }
        result = json.toString();

        return result;
    }
}
