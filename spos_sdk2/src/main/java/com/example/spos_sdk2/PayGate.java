package com.example.spos_sdk2;
import android.util.Log;

import org.apache.http.NameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

public class PayGate {

    private static String URLmerInfo;
    private static String URLpayment;
    private static String URLQRAction;
    private static String URLgenTxtJSON;
    private static String URLorder;
    private static String URLpayFDMSReturnMPOS;
    private static String URLgenTxtXML_Settlement;
    private static String URLpartnerlogo;

    /** Merchant Login URL */
    public static String getLoginURL(EnvBase.PayGate payGate) {
        if (payGate.equals(EnvBase.PayGate.PAYDOLLAR)) {
            URLmerInfo = Constants.url_paydollar_merInfo;
        } else if (payGate.equals(EnvBase.PayGate.SIAMPAY)) {
            URLmerInfo = Constants.url_siampay_merInfo;
        } else if (payGate.equals(EnvBase.PayGate.PESOPAY)) {
            URLmerInfo = Constants.url_pesopay_merInfo;
        }
        return URLmerInfo;
    }

    /** PayComp URL */
    public static String getPayCompURL(EnvBase.PayGate payGate) {

        if (payGate.equals(EnvBase.PayGate.PAYDOLLAR)) {
            URLpayment = Constants.url_paydollar_payComp;
        } else if (payGate.equals(EnvBase.PayGate.SIAMPAY))  {
            URLpayment = Constants.url_siampay_payComp;
        } else if (payGate.equals(EnvBase.PayGate.PESOPAY)) {
            URLpayment = Constants.url_pesopay_payComp;
        }
        return URLpayment;
    }

    /** Boost Inquiry Payment URL */
    public static String getBoostURL(EnvBase.PayGate payGate) {
        if(payGate.equals(EnvBase.PayGate.PAYDOLLAR)){
            URLQRAction = Constants.url_paydollar_checkBoostStatus;
        } else if (payGate.equals(EnvBase.PayGate.SIAMPAY)) {
            URLQRAction = Constants.url_paydollar_checkBoostStatus;
        } else if (payGate.equals(EnvBase.PayGate.PESOPAY)) {
            URLQRAction = Constants.url_paydollar_checkBoostStatus;
        }
        return URLQRAction;
    }

    /** Grab Inquiry Payment URL */
    public static String getGrabURL(EnvBase.PayGate payGate) {
        if(payGate.equals(EnvBase.PayGate.PAYDOLLAR)){
            URLQRAction = Constants.url_paydollar_GrabPayAction;
        } else if (payGate.equals(EnvBase.PayGate.PESOPAY)) {
            URLQRAction = Constants.url_pesopay_GrabPayAction;
        }
        return URLQRAction;
    }

    /** PromptPay (KBank) Inquiry Payment URL */
    public static String getPromptPayURL(EnvBase.PayGate payGate) {
        if(payGate.equals(EnvBase.PayGate.PAYDOLLAR)){
            URLQRAction = Constants.url_paydollar_QRAction;
        } else if (payGate.equals(EnvBase.PayGate.SIAMPAY)) {
            URLQRAction = Constants.url_siampay_QRAction;
        } else if (payGate.equals(EnvBase.PayGate.PESOPAY)) {
            URLQRAction = Constants.url_pesopay_QRAction;
        }
        return URLQRAction;
    }

    /** Retrieve Txn History URL */
    public static String getHistoryURL(EnvBase.PayGate payGate) {
        if (payGate.equals(EnvBase.PayGate.PAYDOLLAR)) {
            URLgenTxtJSON = Constants.url_paydollar_genTxtJSONSPOS;
        } else if (payGate.equals(EnvBase.PayGate.SIAMPAY)) {
            URLgenTxtJSON = Constants.url_siampay_genTxtJSONSPOS;
        } else if (payGate.equals(EnvBase.PayGate.PESOPAY)) {
            URLgenTxtJSON = Constants.url_pesopay_genTxtJSONSPOS;
        }
        return URLgenTxtJSON;
    }

    /** orderAPI (Refund & Void) URL */
    public static String getOrderAPIURL(EnvBase.PayGate payGate) {
        if(payGate.equals(EnvBase.PayGate.PAYDOLLAR)){
            URLorder = Constants.url_paydollar_orderApi;
        } else if (payGate.equals(EnvBase.PayGate.SIAMPAY)) {
            URLorder = Constants.url_siampay_orderApi;
        } else if (payGate.equals(EnvBase.PayGate.PESOPAY)) {
            URLorder = Constants.url_pesopay_orderApi;
        }

        return URLorder;
    }

    /** FDMS Payment Return URL */
    public static String getFDMSReturnURL(EnvBase.PayGate payGate){
        if(payGate.equals(EnvBase.PayGate.PAYDOLLAR)){
            URLpayFDMSReturnMPOS=Constants.url_paydollar_payFDMSReturnMPOS;
        } else if (payGate.equals(EnvBase.PayGate.SIAMPAY)) {
            URLpayFDMSReturnMPOS=Constants.url_siampay_payFDMSReturnMPOS;
        } else if (payGate.equals(EnvBase.PayGate.PESOPAY)) {
            URLpayFDMSReturnMPOS=Constants.url_pesopay_payFDMSReturnMPOS;
        }

        return URLpayFDMSReturnMPOS;
    }

    /** Txn Settlement URL */
    public static String getSettlementURL(EnvBase.PayGate payGate) {
        if(payGate.equals(EnvBase.PayGate.PAYDOLLAR)){
            URLgenTxtXML_Settlement = Constants.url_paydollar_genTxtJSONSPOS_Settlement;
        } else if (payGate.equals(EnvBase.PayGate.SIAMPAY)) {
            URLgenTxtXML_Settlement = Constants.url_siampay_genTxtJSONSPOS_Settlement;
        } else if (payGate.equals(EnvBase.PayGate.PESOPAY)) {
            URLgenTxtXML_Settlement = Constants.url_pesopay_genTxtJSONSPOS_Settlement;
        }
        return URLgenTxtXML_Settlement;
    }

    /** PayForm URL */
    public static String getURL_PayForm(String payGate) {
        if (Constants.pg_paydollar.equalsIgnoreCase(payGate)) {
            URLpayment = Constants.url_paydollar_payForm;
        } else if (Constants.pg_pesopay.equalsIgnoreCase(payGate)) {
            URLpayment = Constants.url_pesopay_payForm;
        } else if (Constants.pg_siampay.equalsIgnoreCase(payGate)) {
            URLpayment = Constants.url_siampay_payForm;
        }
        return URLpayment;
    }

    /** Partner Logo URL */
    public static String getURL_partnerlogo(String payGate) {
        if (Constants.pg_paydollar.equalsIgnoreCase(payGate)) {
            URLpartnerlogo = Constants.url_paydollar_partnerlogo;
        } else if (Constants.pg_pesopay.equalsIgnoreCase(payGate)) {
            URLpartnerlogo = Constants.url_pesopay_partnerlogo;
        } else if (Constants.pg_siampay.equalsIgnoreCase(payGate)) {
            URLpartnerlogo = Constants.url_siampay_partnerlogo;
        }
        return URLpartnerlogo;
    }

    public static String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (NameValuePair pair : params) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }
        Log.d("result", result.toString());
        return result.toString();
    }

    public static HashMap<String, String> split(String resultStr) {
        HashMap<String, String> map = new HashMap<String, String>();
        String[] result = resultStr.split("&");
        for (int i = 0; i < result.length; i++) {
            String[] splitString = result[i].split("=");
            for (int j = 0; j < splitString.length; j++) {
                if (splitString.length == 2) {
                    map.put(splitString[0], splitString[1]);
                    System.out.println(splitString[0] + "--->" + splitString[1]);

                }
            }
        }
        return map;
    }

    public static HashMap<String, String> splitOctopus(String resultStr) {
        HashMap<String, String> map = new HashMap<String, String>();
        String[] result = resultStr.split("&");

        for (int i = 0; i < result.length; i++) {
            String[] splitString = result[i].split("=", 2);
            for (int j = 0; j < splitString.length; j++) {
                map.put(splitString[0], splitString[1]);
                System.out.println(splitString[0] + "--->" + splitString[1]);
            }
        }
        return map;
    }
}

