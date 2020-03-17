package com.example.spos_sdk2;

public class Constants {

    public static String pg_paydollar = "Paydollar";
    public static String pg_pesopay = "Pesopay";
    public static String pg_siampay = "Siampay";

    //CHECK VERSION URL
    public static String url_check_version = "https://test2.paydollar.com/b2cDemo/CheckVersion";

    //PayDollar API URL
    public static String url_paydollar_payForm = "https://test2.paydollar.com/b2cDemo/eng/payment/payForm.jsp";
    public static String url_paydollar_payComp = "https://test2.paydollar.com/b2cDemo/eng/directPay/payCompMPOS.jsp";
    public static String url_paydollar_orderApi = "https://test2.paydollar.com/b2cDemo/eng/merchant/api/orderApi_mpos.jsp";
    public static String url_paydollar_merInfo = "https://test2.paydollar.com/b2cDemo/eng/merchant/api/merInfoReturn_mpos.jsp";
    public static String url_paydollar_sendNoti = "https://test2.paydollar.com/b2cDemo/SendNotification";
    public static String url_paydollar_fileUpload = "https://test2.paydollar.com/b2cDemo/FileUploadServlet";
    public static String url_paydollar_genTxtXML = "https://test2.paydollar.com/b2cDemo/GenTxnXML";
//    public static String url_paydollar_genTxtXMLMPOS = "https://test2.paydollar.com/b2cDemo/GenTransactionSPOS";
    public static String url_paydollar_genTxtXMLMPOS = "https://test2.paydollar.com/b2cDemo/GenTransactionJSONSPOS";
    public static String url_paydollar_partnerlogo = "https://test.paydollar.com/b2cDemo/images/merLogo/";
    public static String url_paydollar_CheckStatus = "https://sit.paydollar.com/b2cDemo/eng/directPay/payComp_test.jsp";
    public static String url_paydollar_sendOrder = "https://test2.paydollar.com/b2cDemo/SendEmailMPOS";
    public static String url_paydollar_settinginfo = "https://test2.paydollar.com/b2cDemo/smartpos/mer_info_setting/getMerchantSettingsInfo.jsp";
    public static String url_paydollar_checkBoostStatus = "https://test2.paydollar.com/b2cDemo/eng/payment/checkStatusBOOSTOFFL_MPOS.jsp";
    public static String url_paydollar_QRAction = "https://test2.paydollar.com/b2cDemo/eng/directPay/payKBankQR_Cancel.jsp";
    public static String url_paydollar_GrabPayAction = "https://test2.paydollar.com/b2cDemo/eng/directPay/mPOS/payGrabMPOS.jsp";

    //PesoPay API URL
    public static String url_pesopay_payForm = "https://test2.pesopay.com/b2cDemo/eng/payment/payForm.jsp";
    public static String url_pesopay_payComp = "https://test2.pesopay.com/b2cDemo/eng/directPay/payCompMPOS.jsp";
    public static String url_pesopay_orderApi = "https://test2.pesopay.com/b2cDemo/eng/merchant/api/orderApi_mpos.jsp";
    public static String url_pesopay_merInfo = "https://test2.pesopay.com/b2cDemo/eng/merchant/api/merInfoReturn_mpos.jsp";
    public static String url_pesopay_sendNoti = "https://test2.pesopay.com/b2cDemo/SendNotification";
    public static String url_pesopay_fileUpload = "https://test2.pesopay.com/b2cDemo/FileUploadServlet";
    public static String url_pesopay_genTxtXML = "https://test2.pesopay.com/b2cDemo/GenTxnXML";
//    public static String url_pesopay_genTxtXMLMPOS = "https://test2.pesopay.com/b2cDemo/GenTransactionSPOS";
    public static String url_pesopay_genTxtXMLMPOS = "https://test2.pesopay.com/b2cDemo/GenTransactionJSONSPOS";
    public static String url_pesopay_partnerlogo = "https://test2.pesopay.com/b2cDemo/images/";
    public static String url_pesopay_QRAction = "https://test2.pesopay.com/b2cDemo/eng/directPay/payKBankQR_Cancel.jsp";
    public static String url_pesopay_GrabPayAction = "https://test2.pesopay.com/b2cDemo/eng/directPay/mPOS/payGrabMPOS.jsp";

    //SiamPay API URL
    public static String url_siampay_payForm = "https://test2.pesopay.com/b2cDemo/eng/payment/payForm.jsp";
    public static String url_siampay_payComp = "https://test2.siampay.com/b2cDemo/eng/directPay/payCompMPOS.jsp";
    public static String url_siampay_orderApi = "https://test2.siampay.com/b2cDemo/eng/merchant/api/orderApi_mpos.jsp";
    public static String url_siampay_merInfo = "https://test2.siampay.com/b2cDemo/eng/merchant/api/merInfoReturn_mpos.jsp";
    public static String url_siampay_sendNoti = "https://test2.siampay.com/b2cDemo/SendNotification";
    public static String url_siampay_fileUpload = "https://test2.siampay.com/b2cDemo/FileUploadServlet";
    public static String url_siampay_genTxtXML = "https://test2.siampay.com/b2cDemo/GenTxnXML";
//    public static String url_siampay_genTxtXMLMPOS = "https://test2.siampay.com/b2cDemo/GenTransactionSPOS";
    public static String url_siampay_genTxtXMLMPOS = "https://test2.siampay.com/b2cDemo/GenTransactionJSONSPOS";
    public static String url_siampay_partnerlogo = "https://test2.siampay.com/b2cDemo/images/";
    public static String url_siampay_QRAction = "https://test2.siampay.com/b2cDemo/eng/directPay/payKBankQR_Cancel.jsp";

    //COUNTRY CODE
    public static String countryCode_HK = "852";
    public static String countryCode_PH = "63";
    public static String countryCode_TH = "66";

    //API Parameters
    public static String merchantId = "merchantId";
    public static String userId = "userId";
    public static String password = "password";
    public static String amount = "amount";
    public static String currCode = "currCode";
    public static String merRequestAmt = "merRequestAmt";
    public static String surcharge = "surcharge";
    public static String lang = "lang";
    public static String payType = "payType";
    public static String orderRef = "orderRef";
    public static String pMethod = "pMethod";
    public static String payMethod = "payMethod";
    public static String cardNo = "cardNo";
    public static String epMonth = "epMonth";
    public static String epYear = "epYear";
    public static String cardHolder = "cardHolder";
    public static String operatorId = "operatorId";
    public static String channelType = "channelType";
    public static String useSurcharge = "useSurcharge";
    public static String loginId = "loginId";
    public static String startDate = "startDate";
    public static String endDate = "endDate";
    public static String enableMobile = "enableMobile";
    public static String sortOrder = "sortOrder";
    public static String payRef = "payRef";
    public static String orderStatus = "orderStatus";
    public static String pageNo = "pageNo";
    public static String pageRecords = "pageRecords";
    public static String action = "action";
    public static String orderId = "orderId";
    public static String actionType = "actionType";
}
