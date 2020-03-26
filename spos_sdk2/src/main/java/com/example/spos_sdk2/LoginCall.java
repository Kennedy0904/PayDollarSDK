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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import static com.example.spos_sdk2.LoginRequest.LOGIN_TAG;
import static com.example.spos_sdk2.LoginResult.*;

public class LoginCall {

    private String baseUrl = null;

    LoginResult callLoginAPI(LoginData loginData){

        String result = "";

        HashMap<String, String> map;
        LoginResult loginResult = new LoginResult();

        try {
            baseUrl = PayGate.getLoginURL(loginData.getPayGate());
            URL url = new URL(baseUrl);
//            url = setLoginURL(loginData.getPayGate());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            TrustModifier.relaxHostChecking(con);
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setConnectTimeout(60*1000);
            con.setReadTimeout(60*1000);

            Map<String, String> parameters = new HashMap<>();
            parameters.put(Constants.merchantId, loginData.getMerchantId());
            parameters.put(Constants.userId, loginData.getUserId());
            parameters.put(Constants.password, loginData.getPassword());

            Log.d(LOGIN_TAG, "merchantId: " + loginData.getMerchantId());
            Log.d(LOGIN_TAG, "userId: " + loginData.getUserId());
            Log.d(LOGIN_TAG, "password: " + loginData.getPassword());
            Log.d(LOGIN_TAG, "payGate: " + loginData.getPayGate().toString());

            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(Utils.getParamsString(parameters));
            out.flush();
            out.close();

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
            Log.d(LOGIN_TAG, "MalformedURLException: " + e.toString());
        } catch (ProtocolException e) {
            e.printStackTrace();
            Log.d(LOGIN_TAG, "ProtocolException: " + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(LOGIN_TAG, "IOException: " + e.toString());
            result = "resultCode=-1&errMsg=ConnectionError";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Log.d(LOGIN_TAG, "NoSuchAlgorithmException: " + e.toString());
        } catch (KeyStoreException e) {
            e.printStackTrace();
            Log.d(LOGIN_TAG, "KeyStoreException: " + e.toString());
        } catch (KeyManagementException e) {
            e.printStackTrace();
            Log.d(LOGIN_TAG, "KeyManagementException: " + e.toString());
        }

        Log.d(LOGIN_TAG, "Result: " + result);
        map = PayGate.split(result);
        loginResult = prepareLoginResult(map);

        return loginResult;
    }

//    private URL setLoginURL(EnvBase.PayGate payGate) throws MalformedURLException {
//
//        if (payGate.equals(EnvBase.PayGate.PAYDOLLAR)) {
//            url = new URL(Constants.url_paydollar_merInfo);
//        } else if (payGate.equals(EnvBase.PayGate.SIAMPAY)) {
//            url = new URL(Constants.url_siampay_merInfo);
//        } else if (payGate.equals(EnvBase.PayGate.PESOPAY)) {
//            url = new URL(Constants.url_pesopay_merInfo);
//        }
//
//        return url;
//    }

    public static String getFullResponse(HttpURLConnection con) throws IOException {
        StringBuilder fullResponseBuilder = new StringBuilder();

        // read status and message

        // read headers

        // read response content

        return fullResponseBuilder.toString();
    }

    protected LoginResult prepareLoginResult(Map<String, String> map) {

//        resultCode=0&merName=MY Testing Merchant 2&cur=458&errMsg=Success&
//                payMethod=BOOSTOFFL,GRABPAYOFFL,Master&payBankId=BOOST,GRABPAY,MAYBANK&channelType=SPC,DPC,DPS,DPL,MPOS
//                &merClass=Basic&amexOnlineRefund=f&visaOnlineRefund=f&masterOnlineRefund=f&jcbOnlineRefund=f&enableMPOSMS=F
//                &rate=0.0&fixed=0.0&hideSurcharge=T&partnerlogo=&adminId=apiuser&apipassword=api1234&address1=Testing Address 01
//                &address2=Testing Address 02&address3=Testing Address 03&bankKey=MCM0013563,ce4b942c-9222-4af7-b990-23c7777d34a3,123456&bankTerId=null,a4a6447749a7999ee96be6a48,null

        String merName = null;
        String cur = null;
        String payMethod;
        String payBankId;
        String channelType;
        String merClass = null;
        String amexOnlineRefund;
        String visaOnlineRefund;
        String masterOnlineRefund;
        String jcbOnlineRefund;
        String enableMPOSMS;
        String rate;
        String fixed;
        String hideSurcharge;
        String partnerlogo = null;
        String adminId = null;
        String apipassword = null;
        String address1 = null;
        String address2 = null;
        String address3 = null;
        String bankKey;
        String bankTerId;

        int resultCodeInt = -1;
        List<PayMethod> payMethods = new ArrayList<PayMethod>();
        String[] channelTypesList = null;
        boolean amexOR = false;
        boolean visaOR = false;
        boolean masterOR = false;
        boolean jcbOR = false;
        boolean enableSMS = false;
        boolean hideCharge = false;
        double rateValue  = 0.0;
        double fixedValue = 0.0;

        LoginResult result = new LoginResult();

        String resultCode = map.get("resultCode");
        String errMsg = map.get("errMsg");

        if (resultCode.equals("0")) {

            resultCodeInt = SUCCESS;

            merName = map.get("merName");
            cur = map.get("cur");
            payMethod = map.get("payMethod");
            payBankId = map.get("payBankId");
            channelType = map.get("channelType");
            merClass = map.get("merClass");
            amexOnlineRefund = map.get("amexOnlineRefund");
            visaOnlineRefund = map.get("visaOnlineRefund");
            masterOnlineRefund = map.get("masterOnlineRefund");
            jcbOnlineRefund = map.get("jcbOnlineRefund");
            enableMPOSMS = map.get("enableMPOSMS");
            rate = map.get("rate");
            fixed = map.get("fixed");
            hideSurcharge = map.get("hideSurcharge");
            partnerlogo = map.get("partnerlogo");
            adminId = map.get("adminId");
            apipassword = map.get("apipassword");
            address1 = map.get("address1");
            address2 = map.get("address2");
            address3 = map.get("address3");
            bankKey = map.get("bankKey");
            bankTerId = map.get("bankTerId");

            // Payment method
            String[] payMethodList = splitOnComma(payMethod);
            String[] payBankIdList = splitOnComma(payBankId);
            String[] bankKeyList = splitOnComma(bankKey);
            String[] bankTerIdList = splitOnComma(bankTerId);

            int i = 0;
            while (i < payMethodList.length) {
                payMethods.add(new PayMethod(payMethodList[i], payBankIdList[i], bankKeyList[i], bankTerIdList[i]));
                i++;
            }

            // Channel Type
            channelTypesList = splitOnComma(channelType);

            // AMEX OnlineRefund
            if(amexOnlineRefund.equalsIgnoreCase("T")){
                amexOR = true;
            }

            // Visa OnlineRefund
            if(visaOnlineRefund.equalsIgnoreCase("T")){
                visaOR = true;
            }

            // Master OnlineRefund
            if(masterOnlineRefund.equalsIgnoreCase("T")){
                masterOR = true;
            }

            // JCB OnlineRefund
            if(jcbOnlineRefund.equalsIgnoreCase("T")){
                jcbOR = true;
            }

            // MPOS SMS
            if (enableMPOSMS != null) {
                if (enableMPOSMS.equalsIgnoreCase("T")) {
                    enableSMS = true;
                } else {
                    enableSMS = false;
                }
            } else {
                enableSMS = false;
            }

            // Rate
            rateValue = Double.parseDouble(rate);

            // Fixed
            fixedValue = Double.parseDouble(fixed);

            // Hide Surcharge
            if(hideSurcharge.equalsIgnoreCase("T")){
                hideCharge = true;
            }

        } else if (resultCode.equals("-1")) {

            if (errMsg.equalsIgnoreCase("Parameter Merchant Id Incorrect")) {
                resultCodeInt = INV_MERID;
            } else if (errMsg.equalsIgnoreCase("No this user")) {
                resultCodeInt = NO_USER;
            } else if (errMsg.equalsIgnoreCase("Invalid apiPassword")) {
                resultCodeInt = INV_PASSWORD;
            } else {
                resultCodeInt = CONN_ERR;
            }
        }

        result.setResultCode(resultCodeInt);
        result.setReturnMsg(errMsg);
        result.setMerchantName(merName);
        result.setCurrencyCode(cur);
        result.setPayMethod(payMethods);
        result.setChannelType(channelTypesList);
        result.setMerchantClass(merClass);
        result.setAmexOnlineRefund(amexOR);
        result.setVisaOnlineRefund(visaOR);
        result.setMasterOnlineRefund(masterOR);
        result.setJcbOnlineRefund(jcbOR);
        result.setEnableMPOSMS(enableSMS);
        result.setRate(rateValue);
        result.setFixed(fixedValue);
        result.setHideSurcharge(hideCharge);
        result.setPartnerLogo(partnerlogo);
        result.setApiId(adminId);
        result.setApiPassword(apipassword);
        result.setAddressLine1(address1);
        result.setAddressLine2(address2);
        result.setAddressLine3(address3);

//        if (!success)
//            payResult.setErrMsg(message);
//        else
//            payResult.setSuccessMsg(message);

        return result;
    }

    static String[] splitOnComma(String str) {

        List<String> strings = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(str, ",");

        while (tokenizer.hasMoreTokens()) {
//                System.out.println(payMethodtokenizer.nextToken());
            strings.add(tokenizer.nextToken());
        }

        String[] ret = new String[strings.size()];
        return strings.toArray(ret);

//        String[] values = payMethod.split(",");
//        System.out.println(Arrays.toString(values));
    }
}



