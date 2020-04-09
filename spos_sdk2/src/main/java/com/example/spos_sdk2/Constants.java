package com.example.spos_sdk2;

public class Constants {

    protected static String pg_paydollar = "Paydollar";
    protected static String pg_pesopay = "Pesopay";
    protected static String pg_siampay = "Siampay";

    /** PayDollar API URL *///
    protected static String url_paydollar_payForm = "https://test2.paydollar.com/b2cDemo/eng/payment/payForm.jsp";
    protected static String url_paydollar_payComp = "https://test2.paydollar.com/b2cDemo/eng/directPay/payCompMPOS.jsp";
    protected static String url_paydollar_orderApi = "https://test2.paydollar.com/b2cDemo/eng/merchant/api/orderApi_mpos.jsp";
    protected static String url_paydollar_merInfo = "https://test2.paydollar.com/b2cDemo/eng/merchant/api/merInfoReturn_mpos.jsp";
    protected static String url_paydollar_genTxtJSONSPOS = "https://test2.paydollar.com/b2cDemo/GenTransactionJSONSPOS";
    protected static String url_paydollar_partnerlogo = "https://test.paydollar.com/b2cDemo/images/merLogo/";
    protected static String url_paydollar_checkBoostStatus = "https://test2.paydollar.com/b2cDemo/eng/payment/checkStatusBOOSTOFFL_MPOS.jsp";
    protected static String url_paydollar_QRAction = "https://test2.paydollar.com/b2cDemo/eng/directPay/payKBankQR_Cancel.jsp";
    protected static String url_paydollar_GrabPayAction = "https://test2.paydollar.com/b2cDemo/eng/directPay/mPOS/payGrabMPOS.jsp";
    protected static String url_paydollar_payFDMSReturnMPOS = "https://test2.paydollar.com/b2cDemo/eng/directPay/mPOS/payFDMSReturnMPOS.jsp";
    protected static String url_paydollar_genTxtJSONSPOS_Settlement = "https://test2.paydollar.com/b2cDemo/GenSettlementTransactionJSONSPOS";

    /** PesoPay API URL */
    protected static String url_pesopay_payForm = "https://test2.pesopay.com/b2cDemo/eng/payment/payForm.jsp";
    protected static String url_pesopay_payComp = "https://test2.pesopay.com/b2cDemo/eng/directPay/payCompMPOS.jsp";
    protected static String url_pesopay_orderApi = "https://test2.pesopay.com/b2cDemo/eng/merchant/api/orderApi_mpos.jsp";
    protected static String url_pesopay_merInfo = "https://test2.pesopay.com/b2cDemo/eng/merchant/api/merInfoReturn_mpos.jsp";
    protected static String url_pesopay_genTxtJSONSPOS = "https://test2.pesopay.com/b2cDemo/GenTransactionJSONSPOS";
    protected static String url_pesopay_partnerlogo = "https://test2.pesopay.com/b2cDemo/images/";
    protected static String url_pesopay_QRAction = "https://test2.pesopay.com/b2cDemo/eng/directPay/payKBankQR_Cancel.jsp";
    protected static String url_pesopay_GrabPayAction = "https://test2.pesopay.com/b2cDemo/eng/directPay/mPOS/payGrabMPOS.jsp";
    protected static String url_pesopay_payFDMSReturnMPOS = "https://test2.pesopay.com/b2cDemo/eng/directPay/mPOS/payFDMSReturnMPOS.jsp";
    protected static String url_pesopay_genTxtJSONSPOS_Settlement = "https://test2.pesopay.com/b2cDemo/GenSettlementTransactionJSONSPOS";

    /** SiamPay API URL */
    protected static String url_siampay_payForm = "https://test2.pesopay.com/b2cDemo/eng/payment/payForm.jsp";
    protected static String url_siampay_payComp = "https://test2.siampay.com/b2cDemo/eng/directPay/payCompMPOS.jsp";
    protected static String url_siampay_orderApi = "https://test2.siampay.com/b2cDemo/eng/merchant/api/orderApi_mpos.jsp";
    protected static String url_siampay_merInfo = "https://test2.siampay.com/b2cDemo/eng/merchant/api/merInfoReturn_mpos.jsp";
    protected static String url_siampay_genTxtJSONSPOS = "https://test2.siampay.com/b2cDemo/GenTransactionJSONSPOS";
    protected static String url_siampay_partnerlogo = "https://test2.siampay.com/b2cDemo/images/";
    protected static String url_siampay_QRAction = "https://test2.siampay.com/b2cDemo/eng/directPay/payKBankQR_Cancel.jsp";
    protected static String url_siampay_payFDMSReturnMPOS = "https://test2.siampay.com/b2cDemo/eng/directPay/mPOS/payFDMSReturnMPOS.jsp";
    protected static String url_siampay_genTxtJSONSPOS_Settlement = "https://test2.siampay.com/b2cDemo/GenSettlementTransactionJSONSPOS";

    /** API Parameters */
    public static String EMVData = "ISO8583Data";
    public static String POSConditionCode = "POSConditionCode";
    public static String POSEntryMode = "POSEntryMode";
    public static String action = "action";
    public static String actionType = "actionType";
    public static String amount = "amount";
    public static String batchNo = "batchNo";
    public static String cardHolder = "cardHolder";
    public static String cardNo = "cardNo";
    public static String channelType = "channelType";
    public static String currCode = "currCode";
    public static String enableMobile = "enableMobile";
    public static String endDate = "endDate";
    public static String enryptedPIN = "enryptedPIN";
    public static String epMonth = "epMonth";
    public static String epYear = "epYear";
    public static String invoiceRef = "invoiceRef";
    public static String lang = "lang";
    public static String loginId = "loginId";
    public static String merName = "merName";
    public static String merRequestAmt = "merRequestAmt";
    public static String merchantId = "merchantId";
    public static String operatorId = "operatorId";
    public static String orderId = "orderId";
    public static String orderRef = "orderRef";
    public static String orderStatus = "orderStatus";
//    public static String payMethod = "payMethod";
    public static String pMethod = "pMethod";
    public static String pageNo = "pageNo";
    public static String pageRecords = "pageRecords";
    public static String password = "password";
    public static String payBankId = "payBankId";
    public static String payRef = "payRef";
    public static String payType = "payType";
    public static String processingCode = "processingCode";
    public static String settlementStatus = "settlementStatus";
    public static String sortOrder = "sortOrder";
    public static String startDate = "startDate";
    public static String surcharge = "surcharge";
    public static String track2Data = "track2Data";
    public static String useSurcharge = "useSurcharge";
    public static String userId = "userId";

    public static String bankRef = "bankRef";
    public static String authId = "authId";
    public static String trxTime = "trxTime";
    public static String trxDate = "trxDate";
    public static String banknetData = "banknetData";
    public static String isoRequest = "isoRequest";
    public static String isoResponse = "isoResponse";
    public static String AID = "AID";
    public static String TC = "TC";
    public static String appName = "appName";
    public static String terminalId = "terminalId";
    public static String posEntryMode = "posEntryMode";
    public static String PRC = "PRC";
    public static String SRC = "SRC";

    /** PayDollar Return Parameters */
    public static String Amt = "Amt";
    public static String MId = "MId";
    public static String PayRef = "PayRef";
    public static String Ref = "Ref";
    public static String TId = "TId";
    public static String successcode = "successcode";
    public static String sysTraceNo = "sysTraceNo";

}
