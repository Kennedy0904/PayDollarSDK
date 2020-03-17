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
import java.util.HashMap;
import java.util.Map;

import static com.example.spos_sdk2.PayResult.TXN_FAILED;
import static com.example.spos_sdk2.PayResult.TXN_SUCCESS;
import static com.example.spos_sdk2.PayRequest.PAYMENT_TAG;

public class PayCall {

    String oriAmount;
    EnvBase.Currency oriCur;
    EnvBase.PayMethod oriPMethod;
    String operatorId;

    URL url = null;

    PayResult callPaymentAPI(PayData payData){

        oriAmount = payData.getAmount();
        oriCur = payData.getCurrCode();
        oriPMethod = payData.getpMethod();

        operatorId = payData.getOperatorId() == null ? "" : payData.getOperatorId();
        String result = "";

        HashMap<String, String> map;
        PayResult payResult = new PayResult();

        try {
            url = setPaymentURL(payData.getPayGate());

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            TrustModifier.relaxHostChecking(con);

            Map<String, String> parameters = new HashMap<>();
            parameters.put(Constants.merchantId, payData.getMerchantId());
            parameters.put(Constants.amount, payData.getAmount());
            parameters.put(Constants.merRequestAmt, payData.getAmount()); //Mandatory
            parameters.put(Constants.currCode, payData.getCurrCode().toString());
            parameters.put(Constants.payType, payData.getPayType().toString());
            parameters.put(Constants.orderRef, payData.getOrderRef());
            parameters.put(Constants.pMethod, payData.getpMethod().toString());
            parameters.put(Constants.operatorId, operatorId);
            parameters.put(Constants.channelType, "MPOS");

            if (payData.getPayment().equals(EnvBase.Payment.PRESENT_QR) && !payData.getpMethod().toString().equals("BOOSTOFFL")){
                parameters.put("presentQR", "T");
            }else{
                if(payData.getpMethod().toString().equals("BOOSTOFFL")){
                    parameters.put(Constants.cardNo, "4518354303130007");
                    Log.d(PAYMENT_TAG, "txnNo: " + "4518354303130007");
                }else {
                    parameters.put(Constants.cardNo, payData.getTxnNo());
                    Log.d(PAYMENT_TAG, "txnNo: " + payData.getTxnNo());
                }
            }
            Log.d(PAYMENT_TAG, "merchantId: " + payData.getMerchantId());
            Log.d(PAYMENT_TAG, "amount: " + payData.getAmount());
            Log.d(PAYMENT_TAG, "currCode: " + payData.getCurrCode().toString());
            Log.d(PAYMENT_TAG, "payType: " + payData.getPayType().toString());
            Log.d(PAYMENT_TAG, "orderRef: " + payData.getOrderRef());
            Log.d(PAYMENT_TAG, "pMethod: " + payData.getpMethod().toString());
            Log.d(PAYMENT_TAG, "operatorId: " + operatorId);

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
            Log.d(PAYMENT_TAG, "IOException: " + e.toString());
            result = "resultCode=-1&errMsg=ConnectionError";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        Log.d(PAYMENT_TAG, "Result: " + result);
        map = PayGate.split(result);
        payResult = preparePayResult(map);

        return payResult;
    }

    private URL setPaymentURL(EnvBase.PayGate payGate) throws MalformedURLException {

        if (payGate.equals(EnvBase.PayGate.PAYDOLLAR)) {
            url = new URL(Constants.url_paydollar_payComp);
        } else if (payGate.equals(EnvBase.PayGate.SIAMPAY)) {
            url = new URL(Constants.url_siampay_payComp);
        } else if (payGate.equals(EnvBase.PayGate.PESOPAY)) {
            url = new URL(Constants.url_pesopay_payComp);
        }

        return url;
    }

    protected PayResult preparePayResult(HashMap<String, String> map) {

//        successcode=1&Ref=123456&PayRef=7651872&Amt=1.0&Cur=458&prc=-9&src=-9&Ord=&Holder=&AuthId=&TId=a4a6447749a7999ee96be6a48&MId=ce4b942c-9222-4af7-b990-23c7777d34a3&TxTime=2020-03-06
//        11:57:15.0&errMsg=Transaction failed

//        successcode=0&Ref=000001&PayRef=7566648&
//                QRCode=00020101021226600014A000000615000101068900460228JjXrENybjHu7ag3sShhEmDUh3amy520472985303458540115802MY5917AsiaPay-POS Sub 16006Kangar624005073UktVjo0725FUXkHSHGrI6uP98B4JsvUpybc64270002EN0117AsiaPay-POS Sub 163047BF7
//            &QRRef=890d95d5bc714f168e8b5e679d9a6de4
//            &Amt=1.0&Cur=458&prc=0&src=02&Ord=0b30dd8a81094d95acef889fd3cbd4ed&Holder=&AuthId=&TId=a4a6447749a7999ee96be6a48&MId=ce4b942c-9222-4af7-b990-23c7777d34a3&TxTime=2020-02-19 12:00:06.0
//                &errMsg=Transaction completed

        int resultCode = -1;

        String successCode = map.get("successcode");

        if(successCode.equals("0")){
            resultCode = TXN_SUCCESS;
        }else{
            resultCode = TXN_FAILED;
        }

        String returnMsg = map.get("errMsg");
        String merchantRef = map.get("Ref");
        String payDollarRef = map.get("PayRef");
        String amount = map.get("Amt") == null ? oriAmount : map.get("Amt");
        String currCode = map.get("Cur") == null ? oriCur.toString() : map.get("Cur");
        String payMethod = oriPMethod.name();
        int prc = Integer.parseInt(map.get("prc") == null ? "-9" : map.get("prc"));
        int src = Integer.parseInt(map.get("src") == null ? "-9" : map.get("src"));
        String txnNo = map.get("Ord");
        String txTime = map.get("TxTime");
        String holder = map.get("Holder");
        String bankMId = map.get("MId");
        String bankTId = map.get("MId");

        String QRCodeStr = map.get("QRCode") == null ? map.get("base64QR") : map.get("QRCode");
        String QRRef = map.get("QRRef") == null ? map.get("token") : map.get("QRCode");
        String QRCodeType = null;
        if(QRCodeStr != null){
            if(payMethod.equalsIgnoreCase("PROMPTPAY") || payMethod.equalsIgnoreCase("BOOST")) {
                QRCodeType = "base64";
            }else{
                QRCodeType = "text";
            }
        }
        Log.d(PAYMENT_TAG, "QRRef: " + QRRef);
        PayResult payResult = new PayResult();
        payResult.setResultCode(resultCode);
        payResult.setReturnMsg(returnMsg);
        payResult.setMerchantRef(merchantRef);
        payResult.setPayDollarRef(payDollarRef);
        payResult.setAmount(amount);
        payResult.setCurrency(CurrCode.getName(currCode));
        payResult.setBankRef(txnNo);
        payResult.setTxnTime(txTime);
        payResult.setPayMethod(payMethod);
        payResult.setPrc(prc);
        payResult.setSrc(src);
        payResult.setBankMerId(bankMId);
        payResult.setBankMerId(bankTId);
        payResult.setQRCode(QRCodeStr);
        payResult.setQRRef(QRRef);
        payResult.setQRCodeType(QRCodeType);

//        if(successCode.equals("0")){
////                Bitmap bitmap = null;
////                byte[] decodedString;
////
////                if(payData.getpMethod().equals(EnvBase.PayMethod.PROMPTPAY)){
////                    decodedString = android.util.Base64.decode(QRCodeStr, Base64.DEFAULT);
////                    bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
////                }else{
////                    bitmap = TextToImageEncode(QRCodeStr);
////                }
//            payResult.setMerchantRef(merchantRef);
//            payResult.setPayDollarRef(payDollarRef);
//            payResult.setAmount(amount);
//            payResult.setCurrency(currCode);
//            payResult.setBankRef(txnNo);
//            payResult.setTxnTime(txTime);
//            payResult.setPayMethod(payMethod);
//            payResult.setPrc(prc);
//            payResult.setSrc(src);
//            payResult.setReturnMsg(returnMsg);
//            payResult.setQRCode(QRCodeStr);
//            payResult.setQRRef(QRRef);
//        }else {
//            payResult.setReturnMsg("Failed to get QR Code.");
//        }

        return payResult;
    }


//    public Bitmap TextToImageEncode(String text) throws WriterException, IOException {
//
//        Bitmap bitmap = null;
//
//        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
//        try {
//            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,300,300);
//            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
//            bitmap = barcodeEncoder.createBitmap(bitMatrix);
//
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
//
//        return bitmap;
//    }
}
