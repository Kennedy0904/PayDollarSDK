package com.example.spos_sdk2;

import android.util.Log;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.spos_sdk2.HistoryRequest.HISTORY_TAG;

public class HistoryCall {

    ArrayList<Record> record_data = new ArrayList<Record>();
    private String baseUrl = null;

    String callHistoryAPI(HistoryData historyData){

        String result = "";

        HashMap<String, String> map;

        try {
            baseUrl = PayGate.getHistoryURL(historyData.getPayGate());
            URL url = new URL(baseUrl);
//            url = new URL("https://test2.paydollar.com/b2cDemo/GenTransactionJSONSPOS");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            TrustModifier.relaxHostChecking(con);
            Log.d(HISTORY_TAG, "merchantId: " + historyData.getMerchantId());
            int pageNo = historyData.getPageNumber() == 0 ? 1 : historyData.getPageNumber();
            int pageRecords = historyData.getPageRecords() == 0 ? 999999 : historyData.getPageRecords();
            String sortOrder = historyData.getSortOrder() == null ? EnvBase.SortOrder.ASC.toString() : historyData.getSortOrder().toString();
            String orderStatus = historyData.getOrderStatus() == null ? EnvBase.OrderStatus.ALL.toString() : historyData.getOrderStatus().toString();
            String payRef = historyData.getPayRef() == null ? "" : historyData.getPayRef();
            String orderRef = historyData.getOrderRef() == null ? "" : historyData.getOrderRef();
            String operatorId = historyData.getOperatorId() == null ? "" : historyData.getOperatorId();

            Map<String, String> parameters = new HashMap<>();
            parameters.put(Constants.merchantId, historyData.getMerchantId());
            parameters.put(Constants.loginId, historyData.getApiId());
            parameters.put(Constants.password, historyData.getApiPassword());
            parameters.put(Constants.startDate, historyData.getStartDate());
            parameters.put(Constants.endDate, historyData.getEndDate());
            parameters.put(Constants.enableMobile, "T");
            parameters.put(Constants.sortOrder, sortOrder);
            parameters.put(Constants.operatorId, operatorId);
            parameters.put(Constants.orderStatus, orderStatus);
            parameters.put(Constants.payRef, payRef);
            parameters.put(Constants.orderRef, orderRef);
            parameters.put(Constants.pageNo, String.valueOf(pageNo));
            parameters.put(Constants.pageRecords, String.valueOf(pageRecords));

            Log.d(HISTORY_TAG, "merchantId: " + historyData.getMerchantId());
            Log.d(HISTORY_TAG, "loginId: " + historyData.getApiId());
            Log.d(HISTORY_TAG, "password: " + historyData.getApiPassword());
            Log.d(HISTORY_TAG, "startDate: " + historyData.getStartDate());
            Log.d(HISTORY_TAG, "endDate: " + historyData.getEndDate());
            Log.d(HISTORY_TAG, "sortOrder: " + sortOrder);
            Log.d(HISTORY_TAG, "operatorId: " + operatorId);
            Log.d(HISTORY_TAG, "orderStatus: " +orderStatus);
            Log.d(HISTORY_TAG, "payRef: " + payRef);
            Log.d(HISTORY_TAG, "orderRef: " + orderRef);
            Log.d(HISTORY_TAG, "pageNo: " + pageNo);
            Log.d(HISTORY_TAG, "pageRecords: " + pageRecords);

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

//            try {
//                String text = result;
//
//                InputStream is = new ByteArrayInputStream(text.getBytes("UTF-8"));
//                XML2Record xml2Record = new XML2Record();
//                android.util.Xml.parse(is, Xml.Encoding.UTF_8, xml2Record);
//                List<Record> records = xml2Record.getRecords();
//                int i = 0;
//                Record recordData[] = new Record[records.size()];
//                for (Record record : records) {
//                    if (Double.parseDouble(record.getamt()) >= 0.0) {
//                        recordData[i] = new Record(record.PayRef(),
//                                record.merref(),
//                                record.getOrderdate(),
//                                record.currency(),
//                                record.getamt(),
//                                record.getSurcharge(),
//                                record.getMerRequestAmt(),
//                                record.remark(),
//                                record.orderstatus(),
//                                "merName",
//                                record.getPayType(),
//                                record.getPaymethod(),
//                                record.getPayBankId(),
//                                record.accountno(),
//                                record.getCardHolder(),
//                                record.getSettle(),
//                                record.getBankId());
//                        record_data.add(recordData[i]);
//                        i++;
//                    }
//                } //end for loop
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            in.close();
            con.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(HISTORY_TAG, "IOException: " + e.toString());
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
            result = prepareHistoryResult(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(HISTORY_TAG, "Result: " + result);

        return result;
//        return record_data;
    }

    private String prepareHistoryResult(String result) throws JSONException {

        String errorMsg = "";
        boolean error = true;
        Log.d(HISTORY_TAG, "Result: " + result);

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
