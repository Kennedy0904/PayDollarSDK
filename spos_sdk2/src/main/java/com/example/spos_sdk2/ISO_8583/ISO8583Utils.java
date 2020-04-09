package com.example.spos_sdk2.ISO_8583;

import android.content.Context;
import android.util.Log;

import com.pax.gl.commhelper.ICommSslClient;
import com.pax.gl.commhelper.impl.PaxGLComm;


public class ISO8583Utils {
    private static final String TAG = ISO8583Utils.class.getSimpleName();
    private static ISO8583Utils instance;

    private ISO8583Utils() {
    }

    public synchronized static ISO8583Utils getInstance() {
        if (instance == null) {
            instance = new ISO8583Utils();
        }

        return instance;
    }

    /**
     * padding position
     * 
     */
    public static enum EPaddingPosition {
        /**
         * padding left
         */
        PADDING_LEFT,
        /**
         * padding right
         */
        PADDING_RIGHT
    }
    
    public String bcdToStr(byte[] b) throws IllegalArgumentException {
        if (b == null) {
            Log.e(TAG, "bcdToStr input arg is null");
            throw new IllegalArgumentException("bcdToStr input arg is null");
        }

        char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }

        return sb.toString();
    }

    public byte[] strToBcd(String str, EPaddingPosition paddingPosition) throws IllegalArgumentException {
        if (str == null || paddingPosition == null) {
            Log.e(TAG, "strToBcd input arg is null");
            throw new IllegalArgumentException("strToBcd input arg is null");
        }

        int len = str.length();
        int mod = len % 2;
        if (mod != 0) {
            if (paddingPosition == EPaddingPosition.PADDING_RIGHT) {
                str = str + "0";
            } else {
                str = "0" + str;
            }
            len = str.length();
        }
        byte abt[] = new byte[len];
        if (len >= 2) {
            len = len / 2;
        }
        byte bbt[] = new byte[len];
        abt = str.getBytes();
        int j, k;
        for (int p = 0; p < str.length() / 2; p++) {
            if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
                j = abt[2 * p] - 'a' + 0x0a;
            } else if ((abt[2 * p] >= 'A') && (abt[2 * p] <= 'Z')) {
                j = abt[2 * p] - 'A' + 0x0a;
            } else {
                j = abt[2 * p] - '0';
            }

            if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
                k = abt[2 * p + 1] - 'a' + 0x0a;
            } else if ((abt[2 * p + 1] >= 'A') && (abt[2 * p + 1] <= 'Z')) {
                k = abt[2 * p + 1] - 'A' + 0x0a;
            } else {
                k = abt[2 * p + 1] - '0';
            }

            int a = (j << 4) + k;
            byte b = (byte) a;
            bbt[p] = b;
        }
        return bbt;
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

    public String dataLength(String data){

        //Add Length of ASCII
        int length = data.length()/2;
        int lengthInByte = 4 - String.valueOf(length).length();
        String dataLength = "";

        for(int i = 0; i < lengthInByte; i++){
            dataLength += "0";
        }

        return dataLength + length;
    }

    public String addLengt15(String data){

        //Add Length of ASCII
        int length = data.length();
        int lengthToAdd = 15 - length;
        String dataLength = "";

        for(int i = 0; i < lengthToAdd; i++){
            dataLength += "0";
        }

        return dataLength + data;
    }

    public String sendToBankHost(Context context, byte[] request){

        String isoResponse = null;

        try{
            ICommSslClient sslClient = PaxGLComm.getInstance(context).createSslClient("cert.ssl.ap.globalpay.com", 443,
                    null);

            sslClient.connect();
            byte[] snd = request;
            Log.i(TAG, "to send" + snd);
            sslClient.send(snd);
            sslClient.setRecvTimeout(3000);

            byte[] response = sslClient.recv(3000);
            Log.i(TAG, "ISO Request: " + bcdToStr(request));
            Log.i(TAG, "ISO Response: " + bcdToStr(response));
            isoResponse = bcdToStr(response);
            sslClient.disconnect();

        }catch(Exception e){
            Log.i(TAG, "sendToBankHost error: "+ e.getMessage());
        }
        return isoResponse;
    }
}
