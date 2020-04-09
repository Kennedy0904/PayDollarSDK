package com.example.spos_sdk2.ISO_8583;

import android.content.Context;
import android.util.Log;

import com.example.spos_sdk2.Constants;
import com.example.spos_sdk2.CurrCode;
import com.example.spos_sdk2.PayData;
import com.example.spos_sdk2.PayGate;
import com.example.spos_sdk2.PayResult;
import com.example.spos_sdk2.TrustModifier;
import com.example.spos_sdk2.Utils;

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
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.spos_sdk2.ISO_8583.ISO8583Request.ISO8583_TAG;
import static com.example.spos_sdk2.PayResult.TXN_FAILED;
import static com.example.spos_sdk2.PayResult.TXN_SUCCESS;

public class ISO8583HttpReq {

    private String baseUrl;

    /** Input Params */
    private String merchantId = null;
    private String merName = null;
    private String amount = null;
    private String useSurcharge = null;
    private String surcharge = null;
    private String merRequestAmt = null;
    private String currCode = null;
    private String orderRef = null;
    private String invoiceRef = null;
    private String batchNo = null;
    private String payType = null;
    private String pMethod = null;
    private String pMethodName = null;
    private String operatorId = null;
    private String terminalId = null;
    private String cardNo = null;
    private String cardHolder = null;
    private String epMonth = null;
    private String epYear = null;
    private String AID = null;
    private String EMVData = null;
    private String POSConditionCode = null;
    private String POSEntryMode = null;
    private String TC = null;
    private String appName = null;
    private String enryptedPIN = null;
    private String processingCode = null;
    private String track2Data = null;

    PayResult callISO8583API(Context context, PayData payData){

        String result = "";

        HashMap<String, String> map;

        try {
            baseUrl = "https://test2.paydollar.com/b2cDemo/eng/directPay/payCompMPOS_GP.jsp";
//            baseUrl = PayGate.getLoginURL(loginData.getPayGate());
            URL url = new URL(baseUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            TrustModifier.relaxHostChecking(con);
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setConnectTimeout(60*1000);

            merchantId = payData.getMerchantId();
            merName = payData.getMerchantName() == null ? "" : payData.getMerchantName();
            amount = payData.getAmount();
            useSurcharge = "F"; //Default disable surcharge
            boolean enableSurcharge = payData.isEnableSurcharge() ? false : payData.isEnableSurcharge();
            if(enableSurcharge){
                useSurcharge = "T";
            }
            surcharge = payData.getSurcharge() == null ? "0" : payData.getSurcharge();
            merRequestAmt = payData.getMerRequestAmt() == null ? payData.getAmount() : payData.getMerRequestAmt();
            currCode = payData.getCurrCode().toString();
            orderRef = payData.getOrderRef();
            invoiceRef = payData.getInvoiceRef() == null ? "" : payData.getInvoiceRef();
            batchNo = payData.getBatchNo() == null ? "" : payData.getBatchNo();
            payType = payData.getPayType().toString();
            pMethod = payData.getPayMethod().toString();
            pMethodName = payData.getPayMethod().name();
            operatorId = payData.getOperatorId() == null ? "" : payData.getOperatorId();
            terminalId = payData.getTerminalId() == null ? "" : payData.getTerminalId();

            /** Card Data */
            CardData cardData = payData.getCardData();
            cardNo = cardData.getCardNo();
            cardHolder = cardData.getCardHolder();
            epMonth = cardData.getEpMonth();
            // Convert expiry month from yy to yyyy
            String rawEpYear = cardData.getEpYear();
            SimpleDateFormat formatter = new SimpleDateFormat("yy");
            Date date = formatter.parse(rawEpYear);
            formatter = new SimpleDateFormat("yyyy");
            epYear = formatter.format(date);

            /** EMV Related Data */
            ISO8583Data ISO8583Data = payData.getISO8583Data();
            AID = ISO8583Data.getAID();
            EMVData = ISO8583Data.getEMVData();
            POSConditionCode = ISO8583Data.getPOSConditionCode();
            POSEntryMode = ISO8583Data.getPOSEntryMode();
            TC = ISO8583Data.getTC();
            appName = ISO8583Data.getAppname();
            enryptedPIN = ISO8583Data.getEnryptedPIN();
            processingCode = ISO8583Data.getProcessingCode();
            track2Data = ISO8583Data.getTrack2Data();

//            -----TransactionQuery:merchantId=88148491&merName=Global+Payment+Demo&currCode=344&amount=5&merRequestAmt=5&surcharge=0&payType=N&orderRef=000001&pMethod=Master&cardNo=5399816352866914&cardHolder=YU+CHUN+HO&epMonth=01&epYear=2024&CVV2Data=&operatorId=admin&channelType=MPOS&useSurcharge=F&processingCode=003000&POSEntryMode=051&POSCondtionCode=00&track2Data=5399816352866914D24012210000000901900&enryptedPIN=12F767FC830B1FF4&ISO8583Data=5F2A0201565F340101820239008407A0000000041010950500000400009A032003319C01009F02060000000000059F03060000000000059F090200029F10120110606001240000CA2400000000000000FF9F1A0201569F1E0830383230333430319F260887EEFB292559FC1C9F2701409F3303E0F8C89F34034203009F3501229F360202C09F3704FA4CA109&invoiceRef=000001&batchNo=000001
            Map<String, String> parameters = new HashMap<>();
            parameters.put(Constants.merchantId, merchantId);
            parameters.put(Constants.merName, merName);
            parameters.put(Constants.amount, amount);
            parameters.put(Constants.useSurcharge, useSurcharge);
            parameters.put(Constants.surcharge, surcharge);
            parameters.put(Constants.merRequestAmt, merRequestAmt);
            parameters.put(Constants.currCode, currCode);
            parameters.put(Constants.orderRef, orderRef);
            parameters.put(Constants.invoiceRef, invoiceRef);
            parameters.put(Constants.batchNo, batchNo);
            parameters.put(Constants.payType, payType);
            parameters.put(Constants.pMethod, pMethod);
            parameters.put(Constants.operatorId, operatorId);
            parameters.put(Constants.channelType, "MPOS");
            parameters.put(Constants.cardNo, cardNo);
            parameters.put(Constants.cardHolder, cardHolder);
            parameters.put(Constants.epMonth, epMonth);
            parameters.put(Constants.epYear, epYear);
            parameters.put(Constants.EMVData, EMVData);
            parameters.put(Constants.POSConditionCode, POSConditionCode);
            parameters.put(Constants.POSEntryMode, POSEntryMode);
            parameters.put(Constants.enryptedPIN, enryptedPIN);
            parameters.put(Constants.processingCode, processingCode);
            parameters.put(Constants.track2Data, track2Data);

            Log.d(ISO8583_TAG, "merchantId: " + merchantId);
            Log.d(ISO8583_TAG, "merName: " + merName);
            Log.d(ISO8583_TAG, "amount: " + amount);
            Log.d(ISO8583_TAG, "useSurcharge: " + useSurcharge);
            Log.d(ISO8583_TAG, "surcharge: " + surcharge);
            Log.d(ISO8583_TAG, "merRequestAmt: " + merRequestAmt);
            Log.d(ISO8583_TAG, "currCode: " + currCode);
            Log.d(ISO8583_TAG, "orderRef: " + orderRef);
            Log.d(ISO8583_TAG, "invoiceRef: " + invoiceRef);
            Log.d(ISO8583_TAG, "batchNo: " + batchNo);
            Log.d(ISO8583_TAG, "payType: " + payType);
            Log.d(ISO8583_TAG, "pMethod: " + pMethod);
            Log.d(ISO8583_TAG, "operatorId: " + operatorId);
            Log.d(ISO8583_TAG, "cardNo: " + cardNo);
            Log.d(ISO8583_TAG, "cardHolder: " + cardHolder);
            Log.d(ISO8583_TAG, "epMonth: " + epMonth);
            Log.d(ISO8583_TAG, "epYear: " + epYear);
            Log.d(ISO8583_TAG, "cardNo: " + cardNo);
            Log.d(ISO8583_TAG, "ISO8583Data: " + EMVData);
            Log.d(ISO8583_TAG, "POSConditionCode: " + POSConditionCode);
            Log.d(ISO8583_TAG, "POSEntryMode: " + POSEntryMode);
            Log.d(ISO8583_TAG, "enryptedPIN: " + enryptedPIN);
            Log.d(ISO8583_TAG, "processingCode: " + processingCode);
            Log.d(ISO8583_TAG, "track2Data: " + track2Data);

            Log.d(ISO8583_TAG, "Params: " + Utils.getParamsString(parameters));

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

            Log.d(ISO8583_TAG, "Result: " + result);

            map = PayGate.split(result);
            String successcode = map.get(Constants.successcode);
            String merRef = map.get(Constants.Ref);
            String payRef = map.get(Constants.PayRef);
            String amt = map.get(Constants.Amt);
            String mid = map.get(Constants.MId);
            String tid = map.get(Constants.TId);
            String traceNo = map.get(Constants.sysTraceNo);

            if(successcode.equals("0")) {

                /** Start to construct iso 0200 request */
                Log.d(ISO8583_TAG, "Pending txn created in PD");
                Log.d(ISO8583_TAG, "Start to construct ISO8583 Message");

                if (traceNo.length() > 6) {
                    traceNo = traceNo.substring(traceNo.length() - 6);
                }

                NumberFormat nfPay = NumberFormat.getInstance();
                nfPay.setMinimumFractionDigits(0);
                nfPay.setGroupingUsed(false);
                String isoAmount = nfPay.format(((Double.parseDouble(amt)) * 100));
                int amountIndex = isoAmount.indexOf(".");
                if (amountIndex >= 0)
                    isoAmount = isoAmount.substring(0, amountIndex);

                invoiceRef = ISO8583Utils.getInstance().asciiToHex(merRef);
                String emvDataLength = ISO8583Utils.getInstance().dataLength(EMVData);

                Message0200 m0200 = new Message0200();

                m0200.setcardType("VISA");
                m0200.settpduHeader("0000");

//                if (POSEntryMode.equals("012")) {
//                    String expDate = epYear.substring(2, 4) + AddChar.addString(epMonth, 2, "0", true);
//                    m0200.setpAccData(cardNo);
//                    m0200.setexpDate(expDate);
//                } else {
//                    track2Data = track2Data.substring(0, 24);
//                    track2Data += "FFFFFFF8";
//                    m0200.settrack2Data("32" + track2Data);
//                }

                //void
                //m0200.setretRefNo("303039383038353732353530");
                //m0200.setinvoiceData("303036303033323635");
                m0200.setprocessCode(processingCode);
                m0200.settranAmount(isoAmount);
                m0200.setsysTraceNo(traceNo);
                m0200.setPosEntryMode(POSEntryMode);

                m0200.settrack2Data(track2Data.length() + track2Data);
                m0200.setcardAccTId(tid);
                m0200.setcardAccId(mid);

                if (POSEntryMode.equals("051") || POSEntryMode.equals("071")) {
                    m0200.setAddEmvData(EMVData, emvDataLength);
                } else {
                    m0200.setbitmap6((byte) 0);
                }

                m0200.setinvoiceData(invoiceRef);
                m0200.setaddData63Data(Properties.GP0200F63);
                m0200.construct();

                String isoRequest = ByteHandler.arrayToString(m0200.msgBody, m0200.msgLength + 2);

                byte[] packed = ISO8583Utils.getInstance().strToBcd(isoRequest, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);

                Log.i(ISO8583_TAG, "Request " + ISO8583Utils.getInstance().bcdToStr(packed));

                /** Receive 0210 response */
                String isoResponse = ISO8583Utils.getInstance().sendToBankHost(context, packed);

                String PRC = "";
                String SRC = "";
                String tPRC = "";
                String bankRef = "";
                String authId = "";
                String txnTime = "";
                String txnDate = "";
                String banknetData = "";

                isoResponse = null;
//                SRC=-9&isoRequest=006A600019000002003020058020C000060000000000000005000032130051001950325399816352866914D2401221FFFFFFF8313930383230303132303139303832303030303030303130303630303030303130323230380002303031310007402130303221403133000134&banknetData=&authId=&terminalId=000001&posEntryMode=0051&orderId=7760474&isoResponse=003860000000190210303801000A8000000000000000000005000032131852330403001930303030303031383439343039343139303832303031&trxDate=&bankRef=&sysTraceNo=003213&PRC=-9&trxTime=
//                SRC=0&isoRequest=006A600019000002003020058020C000060000000000000003000032140051001950325399816352866914D2401221FFFFFFF8313930383230303132303139303832303030303030303130303630303030303130323230380002303031310007402130303221403133000134&banknetData=30323230380002303031310007402130303221403133000134&authId=MC0300&terminalId=000001&posEntryMode=0051&orderId=7760479&isoResponse=003E60000000190210303801000E800000000000000000000300003214185934040300193030393431303536393838334D433033303030303139303832303031&trxDate=0403&bankRef=009410569883&sysTraceNo=003214&PRC=0&trxTime=185934
                if (isoResponse != null) {

                    Log.d(ISO8583_TAG, "Receiving 0210 response");

                    Message0210 m0210 = new Message0210();
                    if (m0210.destruct(isoResponse)) {
                        tPRC = m0210.getresponseCode();

                        if (tPRC.equals("00")) {
                            PRC = "0";
                            SRC = "0";

                            bankRef = m0210.getretRefNo();
                            authId = m0210.getauthId();
                            txnDate = m0210.gettxDate();
                            txnTime = m0210.gettxTime();
                            banknetData = m0210.getaddData63Data();

                            if (banknetData.isEmpty() || banknetData == null) {
                                banknetData = Properties.GP0200F63;
                            }
                        } else {
                            PRC = "1";
                            SRC = tPRC ;
                            bankRef = m0210.getretRefNo();
                            authId = m0210.getauthId();
                            txnDate = m0210.gettxDate();
                            txnTime = m0210.gettxTime();
                        }

                        ISO8583LogGenerator.generateLog(
                                isoRequest,
                                isoResponse,
                                POSEntryMode);

                    } else {
                        PRC = "-9";
                        SRC = "-9" ;

                        bankRef = m0210.getretRefNo();
                        authId = m0210.getauthId();
                        txnDate = m0210.gettxDate();
                        txnTime = m0210.gettxTime();
                    }
                } else {
                    /** Start to construct iso 0400 Reversal request */
                    Log.d(ISO8583_TAG, "Payment failed. Constructing 0400 ISO");

                    PRC = "-9";
                    SRC = "-9" ;
                    Message0400 m0400 = new Message0400();
                    m0400.setcardType("VISA");
                    m0400.settpduHeader("0000");
                    m0400.setprocessCode(processingCode);
                    m0400.settranAmount(amt);
//                    m0400.setsysTraceNo(traceNo);
                    m0400.settxDate("0406");
                    m0400.setexpDate("2401");

                    m0400.setPosEntryMode(POSEntryMode);
                    m0400.setPosEntryMode(track2Data);
//                    m0400.settrack2Data("324188984900001026D1809201FFFFFFF8");

                    if (POSEntryMode.equals("012")) {
                        String expDate = epYear.substring(2, 4) + AddChar.addString(epMonth, 2, "0", true);
                        m0400.setpAccData(cardNo);
                        m0400.setexpDate(expDate);
                    } else {
                        m0400.settrack2Data("32" + track2Data);
                    }

                    m0400.setcardAccTId(tid);
                    m0400.setcardAccId(mid);

//                    if (POSEntryMode.equals("051") || POSEntryMode.equals("071")) {
//                        m0400.setAddEmvData(ISO8583Data, emvDataLength);
//                    } else {
//                        m0400.setbitmap6((byte) 0);
//                    }

                    m0400.setoriData("0200"+traceNo);

//                    m0400.setinvoiceData(invoiceRef);
                    m0400.setaddData63Data(Properties.GP0200F63);
                    m0400.construct();

//                    String isoReversalRequest  = ByteHandler.arrayToString(m0400.msgBody,m0400.msgLength + 2);
                    String isoReversalRequest = "007660001900000400703C05800CC00016164188984900001026203000000000020804000019154627032220010012001950393239323932393932303230776F6F776F7731373130323430313230313731303234303030303030310010020000001930303030313931310007402130303221403133000134";
                    byte[] reversalPacked = ISO8583Utils.getInstance().strToBcd(isoReversalRequest, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);

                    Log.i(ISO8583_TAG, "Request " + ISO8583Utils.getInstance().bcdToStr(reversalPacked));

                    /** Receive 0410 response */
                    isoResponse = ISO8583Utils.getInstance().sendToBankHost(context, reversalPacked);

                }

                String returnURL = "https://test2.paydollar.com/b2cDemo/eng/directPay/mPOS/payISO8583ReturnMPOS.jsp";
                URL url2 = new URL(returnURL);
                HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
                TrustModifier.relaxHostChecking(con2);
                con2.setReadTimeout(30000);
                con2.setConnectTimeout(25000);
                con2.setRequestMethod("POST");
                con2.setDoInput(true);
                con2.setDoOutput(true);

                Map<String, String> parameters2 = new HashMap<>();
                parameters2.put(Constants.bankRef, bankRef);
                parameters2.put(Constants.authId, authId);
                parameters2.put(Constants.trxTime, txnTime);
                parameters2.put(Constants.trxDate, txnDate);
                parameters2.put(Constants.orderId, payRef);
                parameters2.put(Constants.banknetData, banknetData);
                parameters2.put(Constants.sysTraceNo, traceNo);
                parameters2.put(Constants.isoRequest, isoRequest);
                parameters2.put(Constants.isoResponse, isoResponse);
                parameters2.put(Constants.terminalId, terminalId);
                parameters2.put(Constants.posEntryMode, POSEntryMode);
                parameters2.put(Constants.PRC, PRC);
                parameters2.put(Constants.SRC, SRC);

                if (POSEntryMode.equals("051") || POSEntryMode.equals("071")) {
                    parameters2.put(Constants.AID, AID);
                    parameters2.put(Constants.TC, TC);
                    parameters2.put(Constants.appName, appName);
                }

                Log.d(ISO8583_TAG, "bankRef: " + bankRef);
                Log.d(ISO8583_TAG, "authId: " + authId);
                Log.d(ISO8583_TAG, "trxTime: " + txnTime);
                Log.d(ISO8583_TAG, "trxDate: " + txnDate);
                Log.d(ISO8583_TAG, "orderId: " + payRef);
                Log.d(ISO8583_TAG, "banknetData: " + banknetData);
                Log.d(ISO8583_TAG, "sysTraceNo: " + traceNo);
                Log.d(ISO8583_TAG, "isoRequest: " + isoRequest);
                Log.d(ISO8583_TAG, "isoResponse: " + isoResponse);
                Log.d(ISO8583_TAG, "terminalId: " + terminalId);
                Log.d(ISO8583_TAG, "posEntryMode: " + POSEntryMode);

                Log.d(ISO8583_TAG, "ISO Params: " + Utils.getParamsString(parameters2));

                DataOutputStream out2 = new DataOutputStream(con2.getOutputStream());
                out2.writeBytes(Utils.getParamsString(parameters2));
                out2.flush();
                out2.close();

                BufferedReader in2 = new BufferedReader(
                        new InputStreamReader(con2.getInputStream()));
                String inputLine2;
                StringBuffer content2 = new StringBuffer();
                while ((inputLine2 = in2.readLine()) != null) {
                    content2.append(inputLine2);
                }
                String result2 = content2.toString();
                Log.d(ISO8583_TAG, "Result2: " + result2);

                result += "&PRC="+ PRC
                        + "&SRC="+ SRC
                        + "&bankRef="+ bankRef
                        + "&authId="+ authId;

                in2.close();
                con2.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d(ISO8583_TAG, "MalformedURLException: " + e.toString());
        } catch (ProtocolException e) {
            e.printStackTrace();
            Log.d(ISO8583_TAG, "ProtocolException: " + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(ISO8583_TAG, "IOException: " + e.toString());
            result = "resultCode=-1&errMsg=ConnectionError";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Log.d(ISO8583_TAG, "NoSuchAlgorithmException: " + e.toString());
        } catch (KeyStoreException e) {
            e.printStackTrace();
            Log.d(ISO8583_TAG, "KeyStoreException: " + e.toString());
        } catch (KeyManagementException e) {
            e.printStackTrace();
            Log.d(ISO8583_TAG, "KeyManagementException: " + e.toString());
        } catch (NullPointerException e){
            e.printStackTrace();
            Log.d(ISO8583_TAG, "NullPointerException: " + e.toString());
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d(ISO8583_TAG, "ParseException: " + e.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(ISO8583_TAG, "Exception: " + e.toString());
        }

        Log.d(ISO8583_TAG, "result: " + result);

        PayResult payResult = prepareISOPayResult(PayGate.split(result));
        return payResult;
    }

    protected PayResult prepareISOPayResult(HashMap<String, String> map) {

//        successcode=0&Ref=000001&PayRef=7760072&Amt=5.0&Cur=344&prc=0&src=02&Ord=&Holder=Yu Chen Ho&AuthId=&TxTime=2020-04-03 15:12:03.0&errMsg=Transaction completed&MId=201908200000001&TId=19082001&sysTraceNo=000000003181
//        &PRC=0&SRC=0&bankRef=009407569857&authId=MC0500

        String successCode = map.get("successcode") == null ? "-1" : map.get("successcode");
        int prc = Integer.parseInt(map.get("PRC") == null ? "-9" : map.get("PRC"));
        int src = Integer.parseInt(map.get("SRC") == null ? "-9" : map.get("SRC"));
        String merchantRef = map.get("Ref");
        String payDollarRef = map.get("PayRef");
        String amountReturn = map.get("Amt") == null ? amount : map.get("Amt");
        String currCodeReturn = map.get("Cur") == null ? currCode : map.get("Cur");
        String payMethodReturn = pMethodName;
        String txnNo = map.get("Ord");
        /** TBC either need add cardNo & cardHolder in payResult */
        String cardNoReturn = cardNo;
        String holder = map.get("Holder") == null ? cardHolder : map.get("Holder");
        String txTime = map.get("TxTime");
        String bankMId = map.get("MId");
        String bankTId = map.get("MId");
        String bankRef = map.get("bankRef");
        String authId = map.get("authId");
        String traceNo = map.get("sysTraceNo");
        String returnMsg = map.get("errMsg");

        int resultCode = -1;
        if(successCode.equals("0")){
            resultCode = TXN_SUCCESS;
        }else{
            resultCode = TXN_FAILED;
        }

        PayResult payResult = new PayResult();
        payResult.setResultCode(resultCode);
        payResult.setMerchantRef(merchantRef);
        payResult.setPayDollarRef(payDollarRef);
        payResult.setAmount(amountReturn);
        payResult.setCurrency(CurrCode.getName(currCodeReturn));
        payResult.setBankRef(txnNo);
        payResult.setTxnTime(txTime);
        payResult.setPayMethod(payMethodReturn);
        payResult.setCardNo(cardNoReturn);
        payResult.setCardHolderName(holder);
        payResult.setPrc(prc);
        payResult.setSrc(src);
        payResult.setBankMerId(bankMId);
        payResult.setBankMerId(bankTId);
        payResult.setRrn(bankRef);
        payResult.setAuthId(authId);
        payResult.setTraceNo(traceNo);
        payResult.setReturnMsg(returnMsg);

        return payResult;
    }

}
