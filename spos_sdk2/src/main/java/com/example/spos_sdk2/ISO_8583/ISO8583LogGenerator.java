package com.example.spos_sdk2.ISO_8583;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ISO8583LogGenerator{

    public static void generateLog(
            String isoRequest,
            String isoResponse,
            String posEntryMode){

        File logFile = new File(Environment.getExternalStorageDirectory() + "/log.txt");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            logFile.delete();
        }
        try {

            Message0200 m0200 = new Message0200();
            m0200.destruct(isoRequest);

            String request = "Request: \n" +
                    "[H]   " + ISO8583Utils.getInstance().bcdToStr(m0200.tpduHeader) + "\n" +
                    "[M]   " + ISO8583Utils.getInstance().bcdToStr(m0200.msgType) + "\n";

            if(m0200.bitMapArray[1]) {
                request += "[2]   " + ISO8583Utils.getInstance().bcdToStr(m0200.pAccData) + "\n";
            }

            if(m0200.bitMapArray[2]) {
                request +=  "[3]   " + ISO8583Utils.getInstance().bcdToStr(m0200.processCode) + "\n";
            }

            request += "[4]   " + ISO8583Utils.getInstance().bcdToStr(m0200.tranAmount) + "\n" +
                    "[11]  " + ISO8583Utils.getInstance().bcdToStr(m0200.sysTraceNo) + "\n";

            if(m0200.bitMapArray[11]) {
                request +=  "[12]  "+ ISO8583Utils.getInstance().bcdToStr(m0200.txTime) + "\n";
            }

            if(m0200.bitMapArray[12]) {
                request +=  "[13]  "+ ISO8583Utils.getInstance().bcdToStr(m0200.txDate) + "\n";
            }

            if(m0200.bitMapArray[13]) {
                request +=  "[14]  "+ ISO8583Utils.getInstance().bcdToStr(m0200.expDate) + "\n";
            }


            request +=  "[22]  " + ISO8583Utils.getInstance().bcdToStr(m0200.posEntryMode) + "\n" +
                    "[24]  " + ISO8583Utils.getInstance().bcdToStr(m0200.networkId) + "\n" +
                    "[25]  " + ISO8583Utils.getInstance().bcdToStr(m0200.posCondCode) + "\n";

            if(m0200.bitMapArray[34]){
                request += "[35]  " + ISO8583Utils.getInstance().bcdToStr(m0200.track2Length) + " " + ISO8583Utils.getInstance().bcdToStr(m0200.track2Data) + "\n";
            }

            if(m0200.bitMapArray[36]) {
                request +=  "[37]  "+ ISO8583Utils.getInstance().bcdToStr(m0200.retRefNo) + "\n";
            }

            if(m0200.bitMapArray[37]) {
                request +=  "[38]  "+ ISO8583Utils.getInstance().bcdToStr(m0200.authId) + "\n";
            }

            request += "[41]  " + ISO8583Utils.getInstance().bcdToStr(m0200.cardAccTId) + "\n"+
                    "[42]  " + ISO8583Utils.getInstance().bcdToStr(m0200.cardAccId) + "\n";

            if(m0200.bitMapArray[54]){
                request += "[55]  " + m0200.emvDataLength + " " + ISO8583Utils.getInstance().bcdToStr(m0200.addEmvData) + "\n";
            }

            System.out.println("field60 : " +m0200.oriData.length);
            if(m0200.bitMapArray[59]){
                request += "[60]  " + ISO8583Utils.getInstance().bcdToStr(m0200.oriDataLength).substring(0,3) +" " + ISO8583Utils.getInstance().bcdToStr(m0200.oriData) + "\n";
            }

            if(m0200.bitMapArray[61]){
                request += "[62]  " + ISO8583Utils.getInstance().bcdToStr(m0200.invoiceLength).substring(0,3) +" " + ISO8583Utils.getInstance().bcdToStr(m0200.invoiceData) + "\n";
            }

            if(m0200.bitMapArray[62]){
                request += "[63]  " + ISO8583Utils.getInstance().bcdToStr(m0200.addData63Length).substring(0,3) + " " + ISO8583Utils.getInstance().bcdToStr(m0200.addData63Data) + "\n";
            }

            Message0210 m0210 = new Message0210();
            m0210.destruct(isoResponse);

            String response = "\n Response: \n" +
                    "[H]   " + ISO8583Utils.getInstance().bcdToStr(m0210.tpduHeader) + "\n" +
                    "[M]   " + ISO8583Utils.getInstance().bcdToStr(m0210.msgType) + "\n";

            // Bit 2  Primary Account Number
            if (m0210.bitMapArray[1]) {
                response += "[2]   " + ISO8583Utils.getInstance().bcdToStr(m0210.pAccData) + "\n";
            }

            // Bit 3  Processing Code
            if (m0210.bitMapArray[2]) {
                response +=  "[3]   " + ISO8583Utils.getInstance().bcdToStr(m0210.processCode) + "\n";
            }

            // Bit 4  Transaction Amount
            if (m0210.bitMapArray[3]) {
                response += "[4]   " + ISO8583Utils.getInstance().bcdToStr(m0210.tranAmount) + "\n";
            }

            // Bit 11 System Trace Audit Number
            if (m0210.bitMapArray[10]) {
                response += "[11]  " + ISO8583Utils.getInstance().bcdToStr(m0210.sysTraceNo) + "\n";
            }

            // Bit 12 Local Transaction Time
            if (m0210.bitMapArray[11]) {
                response +=  "[12]  "+ ISO8583Utils.getInstance().bcdToStr(m0210.txTime) + "\n";
            }

            // Bit 13 Local Transaction Date
            if (m0210.bitMapArray[12]) {
                response +=  "[13]  "+ ISO8583Utils.getInstance().bcdToStr(m0210.txDate) + "\n";
            }

            // Bit 14  Account Expiry Date
            if (m0210.bitMapArray[13]) {
                response +=  "[14]  "+ ISO8583Utils.getInstance().bcdToStr(m0210.expDate) + "\n";
            }

            // Bit 22 POS Entry Mode
            if (m0210.bitMapArray[21]) {
                response +=  "[22]  " + ISO8583Utils.getInstance().bcdToStr(m0210.posEntryMode) + "\n";
            }

            // Bit 24 Network International ID
            if (m0210.bitMapArray[23]) {
                response += "[24]  " + ISO8583Utils.getInstance().bcdToStr(m0210.networkId) + "\n";
            }

            // Bit 25 POS Condition Code
            if (m0210.bitMapArray[24]) {
                response += "[25]  " + ISO8583Utils.getInstance().bcdToStr(m0210.posCondCode) + "\n";
            }

            // Bit 35 Track 2 Data
            if (m0210.bitMapArray[34]) {
                response += "[35]  " + ISO8583Utils.getInstance().bcdToStr(m0210.track2Length) + " " + ISO8583Utils.getInstance().bcdToStr(m0210.track2Data) + "\n";
            }

            // Bit 37 Retrieval Reference Number
            if (m0210.bitMapArray[36]) {
                response +=  "[37]  "+ ISO8583Utils.getInstance().bcdToStr(m0210.retRefNo) + "\n";
            }

            // Bit 38 Authorization ID
            if (m0210.bitMapArray[37]) {
                response +=  "[38]  "+ ISO8583Utils.getInstance().bcdToStr(m0210.authId) + "\n";
            }

            // Bit 39 Response Code
            if (m0210.bitMapArray[38]) {
                response += "[39]  " + ISO8583Utils.getInstance().bcdToStr(m0210.responseCode) + "\n";
            }

            // Bit 41 Card Acceptor Terminal ID
            if (m0210.bitMapArray[40]) {
                response += "[41]  " + ISO8583Utils.getInstance().bcdToStr(m0210.cardAccTId) + "\n";
            }

            // Bit 42 Card Acceptor ID
            if (m0210.bitMapArray[41]){
                response += "[42]  " + ISO8583Utils.getInstance().bcdToStr(m0210.cardAccId) + "\n";
            }

            // Bit 55 EMV data
            if (m0210.bitMapArray[54]) {
                response += "[55]  " + m0210.emvDataLength + " " + ISO8583Utils.getInstance().bcdToStr(m0210.addEmvData) + "\n";
            }

            // Bit 60 Original Data / Batch Number
            if (m0210.bitMapArray[59]) {
                response += "[60]  " + ISO8583Utils.getInstance().bcdToStr(m0210.oriDataLength).substring(0,3) +" " + ISO8583Utils.getInstance().bcdToStr(m0210.oriData) + "\n";
            }

            // Bit 62 Invoice/ECR ref / Points Batch Totals
            if (m0210.bitMapArray[61]) {
                response += "[62]  " + ISO8583Utils.getInstance().bcdToStr(m0210.invoiceLength).substring(0,3) +" " + ISO8583Utils.getInstance().bcdToStr(m0210.invoiceData) + "\n";
            }

            // Bit 63 Additional Data / Batch Totals
            if (m0210.bitMapArray[62]) {
                response += "[63]  " + ISO8583Utils.getInstance().bcdToStr(m0210.addData63Length).substring(0,3) + " " + ISO8583Utils.getInstance().bcdToStr(m0210.addData63Data) + "\n";
            }


            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = dateFormat.format(date);

            //BufferedWriter for performance, true to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(String.format("[%s] \n", strDate) + request + "\n" + response);
            buf.newLine();
            buf.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
