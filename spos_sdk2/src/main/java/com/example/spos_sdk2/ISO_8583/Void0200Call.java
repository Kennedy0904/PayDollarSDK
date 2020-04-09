package com.example.spos_sdk2.ISO_8583;

import android.content.Context;
import android.util.Log;

import com.example.spos_sdk2.Constants;
import com.example.spos_sdk2.PayData;
import com.example.spos_sdk2.PayGate;
import com.example.spos_sdk2.PayResult;
import com.example.spos_sdk2.TrustModifier;
import com.example.spos_sdk2.Utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import static com.example.spos_sdk2.ISO_8583.ISO8583Request.ISO8583_TAG;

public class Void0200Call {

//    private String orderRef;
//    private String processingCode;
//    private String amount;
//    private String traceNo;
//    private String POSEntryMode;
//    private String track2Data;
//    private String rrn;
//    private String terminalId;
//    private String cardAcceptorId;
//    private String invoiceRef;

    private PayResult payResult = new PayResult();
    private String result;

    PayResult callISO8583API(Context context, PayData payData){

        HashMap<String, String> map;

        /*
          Part 1: Get ISO Required Params from PD
         */

        try {
            /** Get Params from payISO8583GetOrder.jsp */
            String baseUrl = "https://test2.paydollar.com/b2cDemo/eng/directPay/mPOS/payISO8583GetOrder.jsp";
            URL url = new URL(baseUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            TrustModifier.relaxHostChecking(con);
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setConnectTimeout(60 * 1000);

//            amount = payData.getAmount();
//            orderRef = payData.getOrderRef();
//
//            ISO8583Data ISO8583Data = payData.getISO8583Data();
//            processingCode = ISO8583Data.getProcessingCode();
//            traceNo = ISO8583Data.getTraceNo();
//            POSEntryMode = ISO8583Data.getPOSEntryMode();
//            rrn = ISO8583Data.getRrn();
//            track2Data = ISO8583Data.getTrack2Data();
//            terminalId = ISO8583Data.getTerminalId() == null ? "" : ISO8583Data.getTerminalId();
//            cardAcceptorId = ISO8583Data.getCardAcceptorId();
//            invoiceRef = ISO8583Data.getInvoiceRef();
//
//            Log.d(ISO8583_TAG, "amount: " + amount);
//            Log.d(ISO8583_TAG, "processingCode: " + processingCode);
//            Log.d(ISO8583_TAG, "traceNo: " + traceNo);
//            Log.d(ISO8583_TAG, "rrn: " + rrn);
//            Log.d(ISO8583_TAG, "POSEntryMode: " + POSEntryMode);
//            Log.d(ISO8583_TAG, "track2Data: " + track2Data);
//            Log.d(ISO8583_TAG, "terminalId: " + terminalId);
//            Log.d(ISO8583_TAG, "cardAcceptorId: " + cardAcceptorId);

            Map<String, String> parameters = new HashMap<>();
            parameters.put(Constants.orderId, payData.getOrderRef());

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

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(ISO8583_TAG, "IOException Error: " + e.getMessage());
            result = "successCode=-1";
        }

//        successCode=0&amount=1.0&cardNo=4100410411328232&trxTime=&trxDate=&expDate=20208&sysTraceNo=000000003315&posEntryMode=051&bankRef=&batchNo=&authId=&tId=19082001&mId=201908200000001&emvData=5F2A0201565F34010182023C008407A0000000031010950500800000009A032004089C01009F02060000000000019F03060000000000019F0902008C9F100706010A0360AC029F1A0201569F1E0830383230333430319F2608AA2263A2F4396E759F2701409F3303E0F8C89F34031E03009F3501229F360201BA9F370466505160&invoice=000001&banknetData=&terminalId=&cardHolder=Yu
//        Chen Ho&AID=&TC=&appName=

        Log.d(ISO8583_TAG, "Result: " + result);
        map = PayGate.split(result);

        String successCode = map.get("successCode");

        NumberFormat nfPay = NumberFormat.getInstance();
        nfPay.setMinimumFractionDigits(0);
        nfPay.setGroupingUsed(false);
        String amount = map.get("amount");
        String cardNo = map.get("cardNo");
        String txnTime = map.get("trxTime");
        String txnDate = map.get("trxDate");
        String expDate = map.get("expDate");
        if(expDate.length() < 6){
            String date = "";
            date = expDate.substring(4);

            if(date.length() == 1){
                date = "0" + date;
                expDate = expDate.substring(2,4) + date;
            }
        }else{
            expDate = expDate.substring(2,6);
        }
        String traceNo = map.get("sysTraceNo");
        String POSEntryMode = map.get("posEntryMode");
        String bankRef = map.get("bankRef");
        String batchNo = map.get("batchNo");
        String authId = map.get("authId");
        String terminalId = map.get("tId");
        String cardAcceptorId = map.get("mId");
        String emvData = map.get("emvData");
        String invoiceRef = map.get("invoice");
        String banknetData = map.get("banknetData");
//        terminalId = map.get("terminalId");
//        cardHolder = map.get("batchNo");
//        AID = map.get("AID");
//        TC = map.get("TC");
//        appName = map.get("appName");


        /*
          Part 2: Send ISO Request to GP
         */

        if(successCode.equals("0")){

            try{
                /** Start to construct 0200 void request */
                Message0200 m0200 = new Message0200();

                /** TPDU Header */
                m0200.settpduHeader("0000");

                /** Field 2 PAN */

                /** Field 3 Processing Code */
                m0200.setprocessCode(processingCode);

                /** Field 4 Amount */
                String amount0200 = nfPay.format(((Double.parseDouble(amount)) * 100));
                int amountIndex = amount0200.indexOf(".");
                if (amountIndex >= 0){
                    amount0200 = amount0200.substring(0, amountIndex);
                }
                m0200.settranAmount(amount0200);

                /** Field 11 Trace Number */
                m0200.setsysTraceNo(traceNo);

                /** Field 22 POS Entry Mode */
                m0200.setPosEntryMode(POSEntryMode);

                /** Field 24 NII */
                m0200.setNetworkId();

                /** Field 25 POS Condtion Code */
                m0200.setPosConditionCode("50");

                /** Field 35 Track 2 Data */
                m0200.settrack2Data(track2Data.length() + track2Data);

                /** Field 37 RRN */
                rrn = ISO8583Utils.getInstance().asciiToHex(rrn);
                m0200.setretRefNo(rrn);

                /** Field 41 Terminal ID */
                m0200.setcardAccTId(terminalId);

                /** Field 42 Card Acceptor ID */
                m0200.setcardAccId(cardAcceptorId);

//            /** Field 55 EMV Chip Data */
//            if (POSEntryMode.equals("051") || POSEntryMode.equals("071")) {
//                m0200.setAddEmvData(ISO8583Data, emvDataLength);
//            } else {
//                m0200.setbitmap6((byte) 0);
//            }

                /** Field 62 Invoice Ref */
                invoiceRef = ISO8583Utils.getInstance().asciiToHex(invoiceRef);
                m0200.setinvoiceData(invoiceRef);

                /** Field 63 Additional Data */
                m0200.setaddData63Data(Properties.GP0200F63);

                m0200.construct();

                /** Send 0200 Void Request to GP */
                String isoRequest = ByteHandler.arrayToString(m0200.msgBody, m0200.msgLength + 2);
                byte[] packed = ISO8583Utils.getInstance().strToBcd(isoRequest, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);

                /** Receive 0210 response */
                String isoResponse = ISO8583Utils.getInstance().sendToBankHost(context, packed);

                if(isoResponse != null) {
                    Log.d(ISO8583_TAG, "Receiving 0210 response");

                    Message0210 m0210 = new Message0210();

                    if (m0210.destruct(isoResponse)) {

                        Log.d(ISO8583_TAG, "Destruct 0210 Response Successfully");

                        /** Field 12 Txn Time */
                        String txnTime0210 = m0210.gettxTime();

                        /** Field 13 Txn Date */
                        String txnDate0210 = m0210.gettxDate();

                        /** Field 37 RRN */
                        String bankRef0210 = m0210.getretRefNo();

                        /** Field 38 Auth Code */
                        String authId0210 = m0210.getauthId();

                        /** Field 39 Response Code */
                        String responseCode0210 = m0210.getresponseCode();

                        /** Field 63 Additional Data */
                        String banknetData0210 = m0210.getaddData63Data();

                        payResult.setTxnTime(txnTime);
                        payResult.setBankRef(bankRef);
                        payResult.setAuthId(authId);

                    } else {
                        Log.d(ISO8583_TAG, "Failed to destruct 0210 Response");
                        payResult.setReturnMsg("Failed to destruct 0210 Response");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else{
            payResult.setResultCode(PayResult.TXN_FAILED);
            payResult.setReturnMsg("Connection Error");
        }

        return payResult;
    }
}
