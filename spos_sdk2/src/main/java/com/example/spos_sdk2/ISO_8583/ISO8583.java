package com.example.spos_sdk2.ISO_8583;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.pax.gl.commhelper.ICommSslClient;
import com.pax.gl.commhelper.impl.PaxGLComm;
import com.pax.gl.pack.IIso8583;
import com.pax.gl.pack.impl.PaxGLPacker;

import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

public class ISO8583 extends ISO8583Extend {
    private static final String TAG = ISO8583.class.getSimpleName();

    public IIso8583 iso8583;
    ProgressDialog progressDialog;

    Context context;
    String msgId;
    String pan;
    String processingCode;
    String amount;
    String sysTraceNo;
    String time;
    String date;
    String expDate;
    String posEntryMode;
    String nii;
    String posConCode;
    String track2Data;
    String track3Data;
    String retRefNo;
    String authCode;
    String responseCode;
    String tid;
    String mid;
    String cardVerificationData;
    String pinData;
    String additionalAmount;
    String emvChipData;
    String addPOSData;
    String invoice;
    String banknetData;

    String merRef;
    String payRef;

    public ISO8583(Context context,
                   String msgId,
                   String pan,
                   String processingCode,
                   String amount,
                   String sysTraceNo,
                   String time,
                   String date,
                   String expDate,
                   String posEntryMode,
                   String nii,
                   String posConCode,
                   String track2Data,
                   String track3Data,
                   String retRefNo,
                   String authCode,
                   String responseCode,
                   String tid,
                   String mid,
                   String cardVerificationData,
                   String pinData,
                   String additiionalAmount,
                   String emvChipData,
                   String addPOSData,
                   String invoice,
                   String banknetData,
                   String merRef,
                   String payRef){

        this.context = context;
        this.msgId = msgId;
        this.pan = pan;
        this.processingCode = processingCode;
        this.amount = amount;
        this.sysTraceNo = sysTraceNo;
        this.time = time;
        this.date = date;
        this.expDate = expDate;
        this.posEntryMode = posEntryMode;
        this.nii = nii;
        this.posConCode = posConCode;
        this.track2Data = track2Data;
        this.track3Data = track3Data;
        this.retRefNo = retRefNo;
        this.authCode = authCode;
        this.responseCode = responseCode;
        this.tid = tid;
        this.mid = mid;
        this.cardVerificationData = cardVerificationData;
        this.pinData = pinData;
        this.additionalAmount = additiionalAmount;
        this.emvChipData = emvChipData;
        this.addPOSData = addPOSData;
        this.invoice = invoice;
        this.banknetData = banknetData;
        this.merRef = merRef;
        this.payRef = payRef;

    }

    byte[] packed;
    byte[] response;

    String isoResponse;
    Message0210 m0210 = new Message0210();

    String PRC;
    String SRC;
    String result;

    @Override
    public String run(){

        try{

            iso8583 = PaxGLPacker.getInstance(context).getIso8583();
            IIso8583.IIso8583Entity entity = iso8583.getEntity();

            NumberFormat nfPay = NumberFormat.getInstance();
            nfPay.setMinimumFractionDigits(0);
            nfPay.setGroupingUsed(false);

            String ISOBody = "";
            String ISOBodyLength = "";
            String ISOHeader = "";

            PRC = "-9";
            SRC = "-9";
            result = "";

            if(msgId.equals("0200")){
                if(processingCode.equals("003000")){
                    if(posEntryMode.equals("051")){ // Chip

                        String amt = nfPay.format(((Double.parseDouble(amount)) * 100));

                        int amtIndex = amt.indexOf(".");

                        if ( amtIndex >= 0 )
                            amt = amount.substring(0,amtIndex);

                        Message0200 m0200 = new Message0200();


                        if(!invoice.isEmpty() || invoice != null){
                            invoice = asciiToHex(invoice);
                        }

                        m0200.setcardType("VISA");
                        m0200.settpduHeader("0000");
                        m0200.setprocessCode(processingCode);
                        m0200.settranAmount(amt);
                        m0200.setsysTraceNo(sysTraceNo);
                        m0200.setPosEntryMode(posEntryMode);
                        m0200.settrack2Data("324188984900001026D1809201FFFFFFF8");
                        m0200.setcardAccTId(tid);
                        m0200.setcardAccId(mid);
                       // m0200.setAddEmvData("01055F2A0206085F34010282027C00950500800080009A031510269C01009F02060000000200019F03060000000000009F100706010A03A020029F1A0206089F1E0831303130323339379F2608C0B82E0579074E1A9F2701809F3303E0B0C89F360200209F37046FAA0C3F");
                        m0200.setinvoiceData(invoice);
                        m0200.setaddData63Data(Properties.GP0200F63);

                        m0200.construct();


                        String isoRequest  = ByteHandler.arrayToString(m0200.msgBody,m0200.msgLength + 2);

                        packed = ISO8583Utils.getInstance().strToBcd(isoRequest, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);

                        new Thread(new Runnable() {

                            @Override
                            public void run() {
                                try {
                                    ICommSslClient sslClient = PaxGLComm.getInstance(context).createSslClient("cert.ssl.ap.globalpay.com", 443,
                                            null);

                                    sslClient.connect();
                                    byte[] snd = packed;
                                    Log.i(TAG, "to send" + snd);
                                    sslClient.send(snd);
                                    sslClient.setRecvTimeout(10000);
                                    response = sslClient.recv(10000);
                                    Log.i(TAG, "Response " + ISO8583Utils.getInstance().bcdToStr(response));
                                    isoResponse = ISO8583Utils.getInstance().bcdToStr(response);
                                    sslClient.disconnect();

                                    String tPRC = "";
                                    if(response.length > 0){
                                        if(m0210.destruct(isoResponse)){
                                            tPRC = m0210.getresponseCode();

                                            if(tPRC.equals("00")){
                                                PRC = "0";
                                                SRC = "0";

                                                result = "SRC=" +SRC+
                                                        "&PRC="+PRC+
                                                        "&ankRef=" +m0210.getretRefNo()+
                                                        "&authI="+m0210.getauthId()+
                                                        "&trxDate="+ m0210.gettxDate() +
                                                        "&trxTime="+ m0210.gettxTime();

                                                if ((progressDialog != null) && progressDialog.isShowing()) {
                                                    progressDialog.dismiss();
                                                }

                                            }else{
                                                PRC = "1";
                                                SRC = tPRC ;

                                                result = "SRC=" +SRC+
                                                        "&PRC="+PRC+
                                                        "&ankRef=" +m0210.getretRefNo()+
                                                        "&authI="+m0210.getauthId()+
                                                        "&trxDate="+ m0210.gettxDate() +
                                                        "&trxTime="+ m0210.gettxTime();
                                            }
                                        }else{

                                        }
                                    }else{

                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                        TimeUnit.SECONDS.sleep(10);

                        String a = "";

                    }else if(posEntryMode.equals("902")){  // Swipe
                        String amt = nfPay.format(((Double.parseDouble(amount)) * 100));

                        int amtIndex = amt.indexOf(".");

                        if ( amtIndex >= 0 )
                            amt = amount.substring(0,amtIndex);

                        entity.loadTemplate("ISO8583.xml")
                                .setFieldValue("m", "0200")
                                .setFieldValue("3", processingCode)
                                .setFieldValue("4", amt)
                                .setFieldValue("11", sysTraceNo)
                                .setFieldValue("22", posEntryMode)
                                .setFieldValue("24", "019")
                                .setFieldValue("25", posConCode)
                                .setFieldValue("35", "4188984900001026D2001201267")
                                .setFieldValue("41", tid)
                                .setFieldValue("42", mid)
                                .setFieldValue("62", "303036303030303139")
                                .setFieldValue("63", Properties.GP0200F63);

                        ISOBody = "6000190000" + ISO8583Utils.getInstance().bcdToStr(iso8583.pack());

                        ISOBodyLength = Integer.toHexString(ISOBody.length()/2);

                        ISOHeader = ISOBodyLength.length() == 3? "0"+ ISOBodyLength: "00"+ISOBodyLength;

                        entity.setFieldValue("h",ISOHeader+ "6000190000");

                    }else if(posEntryMode.equals("802")){ //fallback
                        String amt = nfPay.format(((Double.parseDouble(amount)) * 100));

                        int amtIndex = amt.indexOf(".");

                        if ( amtIndex >= 0 )
                            amt = amount.substring(0,amtIndex);

                        entity.loadTemplate("ISO8583.xml")
                                .setFieldValue("m", "0200")
                                .setFieldValue("3", processingCode)
                                .setFieldValue("4", amt)
                                .setFieldValue("11", sysTraceNo)
                                .setFieldValue("22", posEntryMode)
                                .setFieldValue("24", "019")
                                .setFieldValue("25", posConCode)
                                .setFieldValue("35", "4188984900001026D2001201267")
                                .setFieldValue("41", tid)
                                .setFieldValue("42", mid)
                                .setFieldValue("62", invoice)
                                .setFieldValue("63", Properties.GP0200F63FALLBACK);

                        ISOBody = "6000190000" + ISO8583Utils.getInstance().bcdToStr(iso8583.pack());

                        ISOBodyLength = Integer.toHexString(ISOBody.length()/2);

                        ISOHeader = ISOBodyLength.length() == 3? "0"+ ISOBodyLength: "00"+ISOBodyLength;

                        entity.setFieldValue("h",ISOHeader+ "6000190000");
                    }else if(posEntryMode.equals("012")){ // manual entry
                        String amt = nfPay.format(((Double.parseDouble(amount)) * 100));

                        int amtIndex = amt.indexOf(".");

                        if ( amtIndex >= 0 )
                            amt = amount.substring(0,amtIndex);

                        entity.loadTemplate("ISO8583.xml")
                                .setFieldValue("m", "0200")
                                .setFieldValue("2", pan)
                                .setFieldValue("3", processingCode)
                                .setFieldValue("4", amt)
                                .setFieldValue("11", sysTraceNo)
                                .setFieldValue("14", expDate)
                                .setFieldValue("22", posEntryMode)
                                .setFieldValue("24", "019")
                                .setFieldValue("25", posConCode)
                                .setFieldValue("41", tid)
                                .setFieldValue("42", mid)
                                .setFieldValue("62", "303036303030303039")
                                .setFieldValue("63", Properties.GP0200F63);

                        ISOBody = "6000190000" + ISO8583Utils.getInstance().bcdToStr(iso8583.pack());

                        ISOBodyLength = Integer.toHexString(ISOBody.length()/2);

                        ISOHeader = ISOBodyLength.length() == 3? "0"+ ISOBodyLength: "00"+ISOBodyLength;

                        entity.setFieldValue("h",ISOHeader+ "6000190000");
                    }
                }
            }

        }catch(Exception e){
            Log.i(TAG, "Pack Error " + e.getMessage());
        }

        return result;
    }

    public String getResult() {
        return result;
    }

    public String asciiToHex(String asciiString){

        //Add Length of ASCII
        int asciiLength = asciiString.length();
        int length = 3 - String.valueOf(asciiLength).length();
        String hexLength = "";

        for(int i = 0; i < length; i++){
            hexLength += "0";
        }

        hexLength = hexLength + asciiLength;
        asciiString = hexLength + asciiString;

        //Convert ASCII string to Char Array
        char[] ch = asciiString.toCharArray();

        StringBuilder builder = new StringBuilder();
        for(char c : ch){
            String hexCode = String.format("%H", c);
            builder.append(hexCode);
        }

        String hex = builder.toString();

        return hex;
    }
}
