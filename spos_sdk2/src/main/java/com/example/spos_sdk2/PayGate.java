package com.example.spos_sdk2;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.apache.http.NameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class PayGate {
    public static String MasterMerRef;
    public static String URLcheckVersion;
    public static String URLpayment;
    public static String URLorder;
    public static String URLmerInfo;
    public static String URLsendNoti;
    public static String URLfileUpload;
    public static String URLgenTxtXML;
    public static String URLpartnerlogo;
    public static String URLsettinginfo;
    public static String URLQRAction;

    public static String countryCode;

    public static String url;

    public static String getBoostURL(EnvBase.PayGate payGate) {
        if(payGate.equals(EnvBase.PayGate.PAYDOLLAR)){
            url = Constants.url_paydollar_checkBoostStatus;
        } else if (payGate.equals(EnvBase.PayGate.SIAMPAY)) {
            url = Constants.url_paydollar_checkBoostStatus;
        } else if (payGate.equals(EnvBase.PayGate.PESOPAY)) {
            url = Constants.url_paydollar_checkBoostStatus;
        }
        return url;
    }

    public static String getGrabURL(EnvBase.PayGate payGate) {
        if(payGate.equals(EnvBase.PayGate.PAYDOLLAR)){
            URLQRAction = Constants.url_paydollar_GrabPayAction;
        } else if (payGate.equals(EnvBase.PayGate.PESOPAY)) {
            URLQRAction = Constants.url_pesopay_GrabPayAction;
        }
        return URLQRAction;
    }

    public static String getPromptPayURL(EnvBase.PayGate payGate) {
        if(payGate.equals(EnvBase.PayGate.PAYDOLLAR)){
            URLQRAction = Constants.url_paydollar_QRAction;
        } else if (payGate.equals(EnvBase.PayGate.SIAMPAY)) {
            URLQRAction = Constants.url_pesopay_QRAction;
        } else if (payGate.equals(EnvBase.PayGate.PESOPAY)) {
            URLQRAction = Constants.url_siampay_QRAction;
        }
        return URLQRAction;
    }

    public static String getURL_orderAPI(EnvBase.PayGate payGate) {
        if(payGate.equals(EnvBase.PayGate.PAYDOLLAR)){
            URLorder = Constants.url_paydollar_orderApi;
        } else if (payGate.equals(EnvBase.PayGate.SIAMPAY)) {
            URLorder = Constants.url_pesopay_orderApi;
        } else if (payGate.equals(EnvBase.PayGate.PESOPAY)) {
            URLorder = Constants.url_siampay_orderApi;
        }

        return URLorder;
    }

    public static String getMasterMerRef(String payGate) {
        if (Constants.pg_paydollar.equalsIgnoreCase(payGate)) {
            MasterMerRef = "1";
        } else if (Constants.pg_pesopay.equalsIgnoreCase(payGate)) {
            MasterMerRef = "12";
        } else if (Constants.pg_siampay.equalsIgnoreCase(payGate)) {
            MasterMerRef = "13";
        }
        return MasterMerRef;
    }

    public static String getURL_CheckStatus(String payGate) {
        if (Constants.pg_paydollar.equalsIgnoreCase(payGate)) {
            URLsendNoti = Constants.url_paydollar_CheckStatus;
        }
        return URLsendNoti;
    }

    public static String getURL_SendOrder(String payGate) {
        if (Constants.pg_paydollar.equalsIgnoreCase(payGate)) {
            URLsendNoti = Constants.url_paydollar_sendOrder;
        }
        return URLsendNoti;
    }


    public static String getURL_PayComp(String payGate) {
        if (Constants.pg_paydollar.equalsIgnoreCase(payGate)) {
            URLpayment = Constants.url_paydollar_payComp;
        } else if (Constants.pg_pesopay.equalsIgnoreCase(payGate)) {
            URLpayment = Constants.url_pesopay_payComp;
        } else if (Constants.pg_siampay.equalsIgnoreCase(payGate)) {
            URLpayment = Constants.url_siampay_payComp;
        }
        return URLpayment;
    }



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



    public static String getURL_merInfo(String payGate) {
        if (Constants.pg_paydollar.equalsIgnoreCase(payGate)) {
            URLmerInfo = Constants.url_paydollar_merInfo;
        } else if (Constants.pg_pesopay.equalsIgnoreCase(payGate)) {
            URLmerInfo = Constants.url_pesopay_merInfo;
        } else if (Constants.pg_siampay.equalsIgnoreCase(payGate)) {
            URLmerInfo = Constants.url_siampay_merInfo;
        }
        return URLmerInfo;
    }

    public static String getURL_sendNoti(String payGate) {
        if (Constants.pg_paydollar.equalsIgnoreCase(payGate)) {
            URLsendNoti = Constants.url_paydollar_sendNoti;
        } else if (Constants.pg_pesopay.equalsIgnoreCase(payGate)) {
            URLsendNoti = Constants.url_pesopay_sendNoti;
        } else if (Constants.pg_siampay.equalsIgnoreCase(payGate)) {
            URLsendNoti = Constants.url_siampay_sendNoti;
        }
        return URLsendNoti;
    }

    public static String getURL_fileUpload(String payGate) {
        if (Constants.pg_paydollar.equalsIgnoreCase(payGate)) {
            URLfileUpload = Constants.url_paydollar_fileUpload;
        } else if (Constants.pg_pesopay.equalsIgnoreCase(payGate)) {
            URLfileUpload = Constants.url_pesopay_fileUpload;
        } else if (Constants.pg_siampay.equalsIgnoreCase(payGate)) {
            URLfileUpload = Constants.url_siampay_fileUpload;
        }
        return URLfileUpload;
    }

    public static String getURL_genTxtXML(String payGate) {
        if (Constants.pg_paydollar.equalsIgnoreCase(payGate)) {
            URLgenTxtXML = Constants.url_paydollar_genTxtXML;
        } else if (Constants.pg_pesopay.equalsIgnoreCase(payGate)) {
            URLgenTxtXML = Constants.url_pesopay_genTxtXML;
        } else if (Constants.pg_siampay.equalsIgnoreCase(payGate)) {
            URLgenTxtXML = Constants.url_siampay_genTxtXML;
        }
        return URLgenTxtXML;
    }

    public static String getURL_genTxtXMLMPOS(String payGate) {
        if (Constants.pg_paydollar.equalsIgnoreCase(payGate)) {
            URLgenTxtXML = Constants.url_paydollar_genTxtXMLMPOS;
        } else if (Constants.pg_pesopay.equalsIgnoreCase(payGate)) {
            URLgenTxtXML = Constants.url_pesopay_genTxtXMLMPOS;
        } else if (Constants.pg_siampay.equalsIgnoreCase(payGate)) {
            URLgenTxtXML = Constants.url_siampay_genTxtXMLMPOS;
        }
        return URLgenTxtXML;
    }

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

    public static String getCountryCode(String payGate) {
        if (Constants.pg_paydollar.equalsIgnoreCase(payGate)) {
            countryCode = Constants.countryCode_HK;
        } else if (Constants.pg_pesopay.equalsIgnoreCase(payGate)) {
            countryCode = Constants.countryCode_PH;
        } else if (Constants.pg_siampay.equalsIgnoreCase(payGate)) {
            countryCode = Constants.countryCode_TH;
        }
        return countryCode;
    }

    public static String getURL_checkVersion(String payGate) {
        if (Constants.pg_paydollar.equalsIgnoreCase(payGate)) {
            URLcheckVersion = Constants.url_check_version;
        } else if (Constants.pg_pesopay.equalsIgnoreCase(payGate)) {
            URLcheckVersion = Constants.url_check_version;
        } else if (Constants.pg_siampay.equalsIgnoreCase(payGate)) {
            URLcheckVersion = Constants.url_check_version;
        }
        return URLcheckVersion;
    }

    public static String getURL_settingInfo(String payGate){
        if(Constants.pg_paydollar.equalsIgnoreCase(payGate)){
            URLsettinginfo=Constants.url_paydollar_settinginfo;
        }else if (Constants.pg_pesopay.equalsIgnoreCase(payGate)){
            URLsettinginfo=Constants.url_paydollar_settinginfo;
        }else if (Constants.pg_siampay.equalsIgnoreCase(payGate)){
            URLsettinginfo=Constants.url_paydollar_settinginfo;
        }
        return URLsettinginfo;
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

