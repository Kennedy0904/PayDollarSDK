/*
	Project 	: KT B2C
	Arthur  	: Edward Ng 
	Description	: The program is a java class used for define the tx message format of wing hang bank
*/
package com.example.spos_sdk2.ISO_8583;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;

public class Message
{
    // Length
    public int	msgLength;
    public byte[] 	msgBody ;
    public String cardType = "";

    // Define Bit Value
    int bit8 = 1;
    int bit7 = 2;
    int bit6 = 4;
    int bit5 = 8;
    int bit4 = 16;
    int bit3 = 32;
    int bit2 = 64;
    int bit1 = 128;

    // Header
    public byte[] 		dataLength = new byte[2];
    public byte[] 		tpduHeader = new byte[5];
    public byte[] 		msgType = new byte[2];
    public byte[] 		bitMap1 = new byte[8];
    public boolean[] 	bitMapArray = new boolean[64] ;

    // Message Body

    // Bit 2  Primary Account Number
    public byte[]   pAccLength = new byte[1];
    public byte[]   pAccData = new byte[8];
    // Bit 3  Processing Code
    public byte[] 	processCode = new byte[3];
    // Bit 4  Transaction Amount
    public byte[]   tranAmount = new byte[6];
    // Bit 11 System Trace Audit Number
    public byte[] 	sysTraceNo = new byte[3];
    // Bit 12 Local Transaction Time
    public byte[] 	txTime = new byte[3];
    // Bit 13 Local Transaction Date
    public byte[] 	txDate = new byte[2];
    // Bit 14  Account Expiry Date
    public byte[]   expDate = new byte[2];
    // Bit 22 POS Entry Mode
    public byte[]   posEntryMode = new byte[2];
    // Bit 24 Network International ID
    public byte[] 	networkId = new byte[2];
    // Bit 25 POS Condition Code
    public byte[]   posCondCode = new byte[1];
    // Bit 35  Track II Data
    public byte[]   track2Length = new byte[1];
    public byte[]   track2Data = new byte[37];
    // Bit 37 Retrieval Reference Number
    public byte[] 	retRefNo = new byte[12];
    // Bit 38 Authorization ID
    public byte[] 	authId = new byte[6];
    // Bit 39 Response Code
    public byte[]	responseCode = new byte[2];
    // Bit 41 Card Acceptor Terminal ID
    public byte[]   cardAccTId = new byte[8];
    // Bit 42 Card Acceptor ID
    public byte[]   cardAccId = new byte[15];
    // Bit 48 Additional Data 48
    public byte[]   addData48Length = new byte[2];
    public byte[]   addData48Data = new byte[2000];
    // Bit 52 PIN Data
    public byte[]   pinData = new byte[8];
    // Bit 54 Additional Amount
    public byte[]   addAmountLength = new byte[1];
    public byte[]   addAmountData = new byte[12];
    // Bit 55 emvData
    public int   emvDataLength  = 0;
    public byte[]   addEmvData = new byte[2000];
    // Bit 60 Original Data / Batch Number
    public byte[]   oriDataLength = new byte[2];
    public byte[]   oriData = new byte[2000];
    // Bit 61 Additional Data 61
    public byte[]   addData61Length = new byte[2];
    public byte[]   addData61Data = new byte[2000];
    // Bit 62 Invoice/ECR ref
    public byte[]   invoiceLength = new byte[2];
    public byte[]   invoiceData = new byte[2000];
    // Bit 63 Additional Data / Batch Totals
    public byte[]   addData63Length = new byte[2];
    public byte[]   addData63Data = new byte[2000];

    public Message()
    {
        super();
        // tpdu header
        tpduHeader[0] =  ByteHandler.stringToByte("6C");
        tpduHeader[1] =  ByteHandler.stringToByte("00");
        tpduHeader[2] =  ByteHandler.stringToByte("35");
    }

    public void setbitmap0(int bitMap)
    {
        bitMap1[0] = (byte)bitMap;
    }

    public void setbitmap1(int bitMap)
    {
        bitMap1[1] = (byte)bitMap;
    }

    public void setbitmap2(int bitMap)
    {
        bitMap1[2] = (byte)bitMap;
    }

    public void setbitmap3(int bitMap)
    {
        bitMap1[3] = (byte)bitMap;
    }

    public void setbitmap4(int bitMap)
    {
        bitMap1[4] = (byte)bitMap;
    }

    public void setbitmap5(int bitmap5)
    {
        bitMap1[5] = (byte)bitmap5;
    }

    public void setbitmap6(int bitmap6)
    {
        bitMap1[6] = (byte)bitmap6;
    }

    public void setbitmap7(int bitmap7)
    {

        bitMap1[7] = (byte)bitmap7;
    }
    // TPDU Header
    public String gettpduHeader()
    {
        String tStr = "" ;

        tStr =  tStr + ByteHandler.packToString(tpduHeader[3]);
        tStr =  tStr + ByteHandler.packToString(tpduHeader[4]);

        return tStr;
    }

    public void settpduHeader( String ip_tpduHeader )
    {
        String tStr = "";

        tStr = AddChar.addString( ip_tpduHeader, 4, "0", true);

        tpduHeader[3] = ByteHandler.stringToByte( tStr.substring(0,2));
        tpduHeader[4] = ByteHandler.stringToByte( tStr.substring(2,4));
    }

    public int getpAccLength()
    {
        return    Integer.parseInt(ByteHandler.packToString(pAccLength[0]));
    }

    public String getpAccData()
    {
        if ( getpAccLength() == 16 )
        {
            return  ByteHandler.packToString(pAccData[0])
                    + ByteHandler.packToString(pAccData[1])
                    + ByteHandler.packToString(pAccData[2])
                    + ByteHandler.packToString(pAccData[3])
                    + ByteHandler.packToString(pAccData[4])
                    + ByteHandler.packToString(pAccData[5])
                    + ByteHandler.packToString(pAccData[6])
                    + ByteHandler.packToString(pAccData[7]);
        }
        else if ( getpAccLength() == 14 )
        {
            return  ByteHandler.packToString(pAccData[0])
                    + ByteHandler.packToString(pAccData[1])
                    + ByteHandler.packToString(pAccData[2])
                    + ByteHandler.packToString(pAccData[3])
                    + ByteHandler.packToString(pAccData[4])
                    + ByteHandler.packToString(pAccData[5])
                    + ByteHandler.packToString(pAccData[6]);
        }
        else
            return null;
    }

    public void setpAccData( String ip_pAccData )
    {
        String tStr = AddChar.addString( ip_pAccData, 16, " ", false);

        pAccData[0] = ByteHandler.stringToByte(tStr.substring(0,2));
        pAccData[1] = ByteHandler.stringToByte(tStr.substring(2,4));
        pAccData[2] = ByteHandler.stringToByte(tStr.substring(4,6));
        pAccData[3] = ByteHandler.stringToByte(tStr.substring(6,8));
        pAccData[4] = ByteHandler.stringToByte(tStr.substring(8,10));
        pAccData[5] = ByteHandler.stringToByte(tStr.substring(10,12));
        pAccData[6] = ByteHandler.stringToByte(tStr.substring(12,14));
        pAccData[7] = ByteHandler.stringToByte(tStr.substring(14,16));
    }

    public String getsysTraceNo()
    {
        String tStr = "" ;

        tStr =  tStr + ByteHandler.packToString(sysTraceNo[0]);
        tStr =  tStr + ByteHandler.packToString(sysTraceNo[1]);
        tStr =  tStr + ByteHandler.packToString(sysTraceNo[2]);

        return tStr;
    }

    public void setsysTraceNo( String ip_sysTraceNo )
    {
        String tStr = "";

        tStr = AddChar.addString( ip_sysTraceNo, 6, "0", true);

        sysTraceNo[0] = ByteHandler.stringToByte( tStr.substring(0,2));
        sysTraceNo[1] = ByteHandler.stringToByte( tStr.substring(2,4));
        sysTraceNo[2] = ByteHandler.stringToByte( tStr.substring(4,6));
    }

    public String gettxTime()
    {
        String tStr = "" ;

        tStr =  tStr + ByteHandler.packToString(txTime[0]);
        tStr =  tStr + ByteHandler.packToString(txTime[1]);
        tStr =  tStr + ByteHandler.packToString(txTime[2]);

        return tStr;
    }

    public void settxTime( String ip_txTime )
    {
        String tStr = "";

        tStr = AddChar.addString( ip_txTime, 6, "0", true);

        txTime[0] = ByteHandler.stringToByte( tStr.substring(0,2));
        txTime[1] = ByteHandler.stringToByte( tStr.substring(2,4));
        txTime[2] = ByteHandler.stringToByte( tStr.substring(4,6));
    }

    public String gettxDate()
    {
        String tStr = "" ;

        tStr =  tStr + ByteHandler.packToString(txDate[0]);
        tStr =  tStr + ByteHandler.packToString(txDate[1]);

        return tStr;
    }

    public void settxDate( String ip_txDate )
    {
        String tStr = "";

        tStr = AddChar.addString( ip_txDate, 4, "0", true);

        txDate[0] = ByteHandler.stringToByte( tStr.substring(0,2));
        txDate[1] = ByteHandler.stringToByte( tStr.substring(2,4));
    }

    public String getprocessCode()
    {
        return    ByteHandler.packToString(processCode[0])
                + ByteHandler.packToString(processCode[1])
                + ByteHandler.packToString(processCode[2]);
    }

    public void setprocessCode( String ip_processCode )
    {
        String tStr = "";

        tStr = AddChar.addString(ip_processCode, 6, "0", true);

        processCode[0] = ByteHandler.stringToByte(tStr.substring(0,2));
        processCode[1] = ByteHandler.stringToByte(tStr.substring(2,4));
        processCode[2] = ByteHandler.stringToByte(tStr.substring(4,6));
    }

    public void setcardType(String ip_cardType)
    {
        pAccLength[0] = ByteHandler.stringToByte("16");
    }

    public void setPosEntryMode(String ip_posEntryMode)
    {
        String entryMode1 = ip_posEntryMode.substring(0,1);
        String entryMode2 = ip_posEntryMode.substring(ip_posEntryMode.length() - 2);

        posEntryMode[0] = ByteHandler.stringToByte("0"+entryMode1);
        posEntryMode[1] = ByteHandler.stringToByte(entryMode2);
    }

    public void setNetworkId(){
        networkId[0] = ByteHandler.stringToByte("00");
        networkId[1] = ByteHandler.stringToByte("19");
    }

    public void setPosConditionCode(String ip_posConditionCode)
    {
        // pos condition code
        posCondCode[0] = ByteHandler.stringToByte(ip_posConditionCode);
    }

    public String gettranAmount()
    {
        return    ByteHandler.packToString(tranAmount[0])
                + ByteHandler.packToString(tranAmount[1])
                + ByteHandler.packToString(tranAmount[2])
                + ByteHandler.packToString(tranAmount[3])
                + ByteHandler.packToString(tranAmount[4])
                + ByteHandler.packToString(tranAmount[5]);
    }

    public void settranAmount( String ip_tranAmount )
    {
        String tStr = "";

        tStr = AddChar.addString(ip_tranAmount, 12, "0", true);

        tranAmount[0] = ByteHandler.stringToByte(tStr.substring(0,2));
        tranAmount[1] = ByteHandler.stringToByte(tStr.substring(2,4));
        tranAmount[2] = ByteHandler.stringToByte(tStr.substring(4,6));
        tranAmount[3] = ByteHandler.stringToByte(tStr.substring(6,8));
        tranAmount[4] = ByteHandler.stringToByte(tStr.substring(8,10));
        tranAmount[5] = ByteHandler.stringToByte(tStr.substring(10,12));
    }

    public String getexpDate()
    {
        return    ByteHandler.packToString(expDate[0])
                + ByteHandler.packToString(expDate[1]);
    }

    public void setexpDate( String ip_expDate )
    {
        String tStr = "";

        tStr = AddChar.addString(ip_expDate, 4, "0", true);

        expDate[0] = ByteHandler.stringToByte(tStr.substring(0,2));
        expDate[1] = ByteHandler.stringToByte(tStr.substring(2,4));
    }

    public int gettrack2Length()
    {
        return    Integer.parseInt(ByteHandler.packToString(track2Length[0])+ ByteHandler.packToString(track2Length[1]));
    }

    public String gettrack2Data()
    {
        String tStr = "";

        for ( int i = 0 ; i < gettrack2Length() ; i ++ )
        {
            tStr = tStr + String.valueOf((char) track2Data[i]);
        }

        return  tStr ;
    }

    public void settrack2Data( String ip_track2Data )
    {
        String tStr = "";

        int length = track2Data.length;

        tStr = AddChar.addString(ip_track2Data, 17, "0", true);

        boolean firstData = true;
        int x = 0;
        int y = 2;

        for(int i = 0; i < 17; i++){

            if(firstData){
                track2Data[i] = ByteHandler.stringToByte(tStr.substring(x,y));
                firstData = false;
            }else {
                x = x + 2;
                y = y + 2;
                track2Data[i] = ByteHandler.stringToByte(tStr.substring(x , y));
            }
        }
    }

    public String getauthId()
    {
        String tStr = "";

        for ( int i = 0 ; i < 6 ; i ++ )
        {
            tStr = tStr + String.valueOf((char) authId[i]);
        }

        return  tStr ;
    }

    public void setauthId( String ip_authId )
    {
        String tStr = "";

        tStr = AddChar.addString( ip_authId, 6 , "0", true);

        for ( int i = 0 ; i < 6 ; i ++ )
        {
            authId[i] = (byte) tStr.charAt(i);
        }
    }

    public String getretRefNo()
    {
        String tStr = "";

        for ( int i = 0 ; i < 12 ; i ++ )
        {
            tStr = tStr + String.valueOf((char) retRefNo[i]);
        }

        return  tStr ;
    }

    public void setretRefNo( String ip_retRefNo )
    {
        String tStr = "";

        tStr = AddChar.addString( ip_retRefNo, 12 , "0", true);

        for ( int i = 0 ; i < 12 ; i ++ )
        {
            retRefNo[i] = (byte) tStr.charAt(i);
        }
    }

    public String getresponseCode()
    {
        String tStr = "";

        for ( int i = 0 ; i < 2 ; i ++ )
        {
            tStr = tStr + String.valueOf((char) responseCode[i]);
        }

        return  tStr ;
    }

    public void setresponseCode( String ip_responseCode )
    {
        String tStr = "";

        tStr = AddChar.addString( ip_responseCode, 2 , "0", true);

        for ( int i = 0 ; i < 2 ; i ++ )
        {
            responseCode[i] = (byte) tStr.charAt(i);
        }
    }

    public String getcardAccTId()
    {
        return  String.valueOf((char) cardAccTId[0])
                + String.valueOf((char) cardAccTId[1])
                + String.valueOf((char) cardAccTId[2])
                + String.valueOf((char) cardAccTId[3])
                + String.valueOf((char) cardAccTId[4])
                + String.valueOf((char) cardAccTId[5])
                + String.valueOf((char) cardAccTId[6])
                + String.valueOf((char) cardAccTId[7]);
    }

    public void setcardAccTId( String ip_cardAccTId )
    {
        String tStr = "";

        tStr = AddChar.addString( ip_cardAccTId, 8 , "0", true);

        for ( int i = 0 ; i < 8 ; i ++ )
        {
            cardAccTId[i] = (byte) tStr.charAt(i);
        }
    }

    public String getcardAccId()
    {
        String tStr = "";

        for ( int i = 0 ; i < 15 ; i ++ )
        {
            tStr = tStr + String.valueOf((char) cardAccId[i]);
        }

        return  tStr ;
    }

    public void setcardAccId( String ip_cardAccId )
    {
        String tStr = "";

        tStr = AddChar.addString( ip_cardAccId, 15 , "0", true);

        for ( int i = 0 ; i < 15 ; i ++ )
        {
            cardAccId[i] = (byte) tStr.charAt(i);
        }
    }

    public int getaddData48Length()
    {
        return    Integer.parseInt(ByteHandler.packToString(addData48Length[0])+ ByteHandler.packToString(addData48Length[1]));
    }

    public String getaddData48Data()
    {
        String tStr = "";

        for ( int i = 0 ; i < getaddData48Length() ; i ++ )
        {
            tStr = tStr + String.valueOf((char) addData48Data[i]);
        }

        return  tStr ;
    }

    public void setaddData48Data( String ip_addData48Data )
    {
        String tStr = "";

        tStr = AddChar.addString( ip_addData48Data, getaddData48Length() , "0", true);

        for ( int i = 0 ; i < getaddData48Length() ; i ++ )
        {
            addData48Data[i] = (byte) tStr.charAt(i);
        }
    }

    public void setaddData48Data(String ip_addData48DataLength, String ip_addData48Data) {
        //String tStr = "";

        int datalength = Integer.parseInt(ip_addData48DataLength);

        addData48Length[0] = (byte) new Integer(datalength / 256).byteValue();
        addData48Length[1] = (byte) new Integer(datalength % 256).byteValue();

        if (ip_addData48Data!=null && !ip_addData48Data.equals(""))
        {
            for (int i = 0; i < getaddData48Length(); i++) {
                addData48Data[i] = (byte) ip_addData48Data.charAt(i);
            }
        }

    }


    public int getaddAmountLength()
    {
        return    Integer.parseInt(ByteHandler.packToString(addAmountLength[0])+ ByteHandler.packToString(addAmountLength[1]));
    }

    public String getaddAmount()
    {
        String tStr = "";

        for ( int i = 0 ; i < getaddAmountLength() ; i ++ )
        {
            tStr = tStr + String.valueOf((char) addAmountData[i]);
        }

        return  tStr ;
    }

    public void setaddAmount( String ip_addAmount )
    {
        String tStr = "";

        tStr = AddChar.addString( ip_addAmount, getaddAmountLength() , "0", true);

        for ( int i = 0 ; i < getaddAmountLength() ; i ++ )
        {
            addAmountData[i] = (byte) tStr.charAt(i);
        }
    }

    public void setAddEmvData(String ipEmvData, String dataLength )  // For settlement
    {
        emvDataLength = 0;

        ipEmvData = dataLength + ipEmvData;

        emvDataLength = ipEmvData.length()/2;

        String tStr = "";

        tStr = AddChar.addString(ipEmvData, emvDataLength, "0", true);

        boolean firstData = true;
        int x = 0;
        int y = 2;

        for(int i = 0; i < emvDataLength; i++){

            if(firstData){
                addEmvData[i] = ByteHandler.stringToByte(tStr.substring(x,y));
                firstData = false;
            }else {
                x = x + 2;
                y = y + 2;
                addEmvData[i] = ByteHandler.stringToByte(tStr.substring(x,y));
            }
        }
    }

    public int getoriDataLength()
    {
        return    Integer.parseInt(ByteHandler.packToString(oriDataLength[0])+ ByteHandler.packToString(oriDataLength[1]));
    }

    public String getoriData()
    {
        String tStr = "";

        for ( int i = 0 ; i < getoriDataLength() ; i ++ )
        {
            tStr = tStr + String.valueOf((char) oriData[i]);
        }

        return  tStr ;
    }

    public void setoriData( String ip_oriData )
    {
        String tStr = "";

        tStr = AddChar.addString( ip_oriData, getoriDataLength() , "0", true);

        for ( int i = 0 ; i < getoriDataLength() ; i ++ )
        {
            oriData[i] = (byte) tStr.charAt(i);
        }
    }

    public int getaddData61Length()
    {
        return    Integer.parseInt(ByteHandler.packToString(addData61Length[0])+ ByteHandler.packToString(addData61Length[1]));
    }

    public String getaddData61Data()
    {
        String tStr = "";

        for ( int i = 0 ; i < getaddData61Length() ; i ++ )
        {
            tStr = tStr + String.valueOf((char) addData61Data[i]);
        }

        return  tStr ;
    }

    public void setaddData61Data( String ip_addData61Data )
    {
        String tStr = "";

        tStr = AddChar.addString( ip_addData61Data, getaddData61Length() , "0", true);

        for ( int i = 0 ; i < getaddData61Length() ; i ++ )
        {
            addData61Data[i] = (byte) tStr.charAt(i);
        }
    }

    public int getinvoiceLength()
    {
        return    Integer.parseInt(ByteHandler.packToString(invoiceLength[0])+ ByteHandler.packToString(invoiceLength[1]));
    }

    public String getinvoiceData()
    {
        String tStr = "";

        for ( int i = 0 ; i < getinvoiceLength() ; i ++ )
        {
            tStr = tStr + String.valueOf((char) invoiceData[i]);
        }

        return  tStr ;
    }

    public void setinvoiceData( String ip_invoiceData )
    {

        String tStr = "";

        tStr = AddChar.addString(ip_invoiceData, 9, "0", true);

        boolean firstData = true;
        int x = 0;
        int y = 2;

        for(int i = 0; i < 9; i++){

            if(firstData){
                invoiceData[i] = ByteHandler.stringToByte(tStr.substring(x,y));
                firstData = false;
            }else {
                x = x + 2;
                y = y + 2;
                invoiceData[i] = ByteHandler.stringToByte(tStr.substring(x,y));
            }
        }


    }

    public int getaddData63Length()
    {
        return    Integer.parseInt(ByteHandler.packToString(addData63Length[0])+ ByteHandler.packToString(addData63Length[1]));
    }

    public String getaddData63Data()
    {
        String tStr = "";

        for ( int i = 0 ; i < getaddData63Length() ; i ++ )
        {
            tStr = tStr + String.valueOf((char) addData63Data[i]);
        }

        return  tStr ;
    }

    public void setaddData63Data( String ip_addData63Data )  // For settlement
    {

        String tStr = "";

        tStr = AddChar.addString(ip_addData63Data, 25, "0", true);

        boolean firstData = true;
        int x = 0;
        int y = 2;

        for(int i = 0; i < 25; i++){

            if(firstData){
                addData63Data[i] = ByteHandler.stringToByte(tStr.substring(x,y));
                firstData = false;
            }else {
                x = x + 2;
                y = y + 2;
                addData63Data[i] = ByteHandler.stringToByte(tStr.substring(x,y));
            }
        }
    }

    public void setaddData63Data(String ip_cardType, String ip_appId , String ip_eci, String ip_xId, String ip_cavv ) // For Sale and Auth
    {
        // APP ID
        AddChar.addString( ip_appId , 2 , "0", true)  ;

        addData63Data[0] = (byte) ip_appId.charAt(0);
        addData63Data[1] = (byte) ip_appId.charAt(1);

        // ECI
        AddChar.addString( ip_eci , 2 , "0", true)  ;

        addData63Data[2] = (byte) ip_eci.charAt(0);
        addData63Data[3] = (byte) ip_eci.charAt(1);

        //
        // Construct the XID
        //
        byte[] xIdByte = new byte[20];

        // XID
        if ( ip_xId.equals("") )
        {
            // No XID
            /*
			for ( int i = 0 ; i < 20 ; i ++ )
                                        xIdByte[i] = (byte) ((char) 255);
			*/
            //20100312
            System.out.println("xId is empty:" + ip_xId );

            String defaultXID = "";
            for ( int i = 0 ; i < 20 ; i ++ )
                defaultXID = defaultXID + (char)255;

            xIdByte = defaultXID.getBytes();
            //end
        }
        else
        {
            // padd space to the end
            String tXId = AddChar.addString( ip_xId, 20 , " ", false);

            // Decode the CAVV
            xIdByte = tXId.getBytes();
        }

        // Convert to display hexadecimal (dx) format
        String xIdPack = ByteHandler.arrayToString(xIdByte, 20);

        System.out.println("XID in pack:" + xIdPack );

        for ( int i = 0 ; i < 20 ; i ++ )
        {
            addData63Data[4+(i*2)] = (byte)xIdPack.charAt(2*i);
            addData63Data[4+(i*2)+1] = (byte)xIdPack.charAt((2*i)+1);
        }

        //
        // Construct the CAVV
        //
        byte[] cavvByte = new byte[20];


        if ( ip_cardType.equals("VISA") )
        {
            // CAVV
            if ( ip_cavv.equals("") )
            {
                // No Cavv
                for ( int i = 0 ; i < 20 ; i ++ )
                    cavvByte[i] = (byte) ((char) 255);
            }
            else
            {
                // Decode the CAVV
                cavvByte = Base64.decode(ip_cavv);
            }

            // Convert to display hexadecimal (dx) format
            String decodeCavvPack = ByteHandler.arrayToString(cavvByte, 20);

            System.out.println("Decoded CAVV in pack:" + decodeCavvPack );

            for ( int i = 0 ; i < 20 ; i ++ )
            {
                addData63Data[44+(i*2)] = (byte)decodeCavvPack.charAt(2*i);
                addData63Data[44+(i*2)+1] = (byte)decodeCavvPack.charAt((2*i)+1);
            }
        }
        else if ( ip_cardType.equals("Master"))
        {
            if ( ip_cavv.equals("") )
            {
                // No UCAF
                for ( int i = 0 ; i < 40 ; i ++ )
                    addData63Data[44+i] = (byte) ((char) ' ');
            }
            else
            {
                // Have UCAF
                for ( int i = 0 ; i < 28 ; i ++ )
                    addData63Data[44+i] = (byte) ip_cavv.charAt(i);

                for ( int i = 0 ; i < 12 ; i ++ )
                    addData63Data[72+i] = (byte) ((char) ' ');
            }
        }
    }

    public void decodeBitMap()
    {
        int tempByte = 0 ;

        for ( int i = 0 ; i <8 ; i++ )
        {
            tempByte = (int) bitMap1[i] ;

            if ( tempByte < 0 )
                tempByte = tempByte + 256 ;

            for ( int j = 0 ; j <8 ; j++ )
            {
                double b = Math.pow(2,8-(j+1));
                double a = tempByte - b;
                if ( a >= 0 )
                {
                    bitMapArray[i*8 + j] = true;
                    double c = (int) Math.pow(2,8-(j+1));
                    tempByte = tempByte - (int)c;
                }
                else
                {
                    bitMapArray[i*8 + j] = false;
                }
            }
        }
    }

    public void encodeBitMap()
    {
        int tempByte = 0 ;

        for ( int i = 0 ; i <8 ; i++ )
        {
            tempByte = (int) bitMap1[i] ;

            if ( tempByte < 0 )
                tempByte = tempByte + 256 ;

            for ( int j = 0 ; j <8 ; j++ )
            {
                if ( ( tempByte - Math.pow(2,8-(j+1)) ) >= 0 )
                {
                    bitMapArray[i*8 + j] = true;
                    tempByte = tempByte - (int) Math.pow(2,8-(j+1));
                }
                else
                {
                    bitMapArray[i*8 + j] = false;
                }
            }
        }
    }

    public byte[] construct() throws Exception
    {
        try
        {
            // update txDate and txTime
            java.util.Date currentDate = new java.util.Date();
            SimpleDateFormat formatter = new SimpleDateFormat("MMddkkmmss");

            if ( msgType[0] == ByteHandler.stringToByte("08") && msgType[1] ==  ByteHandler.stringToByte("00") )
            {
                settxDate((formatter.format(currentDate)).substring(0,4));
                settxTime((formatter.format(currentDate)).substring(4));
            }

            ByteArrayOutputStream bo = new ByteArrayOutputStream();

            // construct the message

            bo.write(tpduHeader,0,5);
            bo.write(msgType,0,2);
            bo.write(bitMap1,0,8);

            // Decode the Bit Map to check which bits are included in the message
            decodeBitMap();

            // Bit 2  Primary Account Number
            if ( bitMapArray[1])
            {
                bo.write(pAccLength,0,1);
                bo.write(pAccData,0,this.getpAccLength()/2);
            }

            // Bit 3  Processing Code
            if ( bitMapArray[2])
            {
                bo.write(processCode,0,3);
            }

            // Bit 4  Transaction Amount
            if ( bitMapArray[3])
            {
                bo.write(tranAmount,0,6);
            }

            // Bit 11 System Trace Audit Number
            if ( bitMapArray[10])
            {
                bo.write(sysTraceNo,0,3);
            }

            // Bit 12 Local Transaction Time
            if ( bitMapArray[11])
            {
                bo.write(txTime,0,3);
            }

            // Bit 13 Local Transaction Date
            if ( bitMapArray[12])
            {
                bo.write(txDate,0,2);
            }

            // Bit 14  Account Expiry Date
            if ( bitMapArray[13])
            {
                bo.write(expDate,0,2);
            }

            // Bit 22 POS Entry Mode
            if ( bitMapArray[21])
            {
                bo.write(posEntryMode,0,2);
            }

            // Bit 24 Network International ID
            if ( bitMapArray[23])
            {
                bo.write(networkId,0,2);
            }

            // Bit 25 POS Condition Code
            if ( bitMapArray[24])
            {
                bo.write(posCondCode,0,1);
            }

            // Bit 35 Track 2 Data
            if ( bitMapArray[34])
            {
//                bo.write(track2Data,0,17);
                bo.write(track2Data,0,20);
            }

            // Bit 37 Retrieval Reference Number
            if ( bitMapArray[36])
            {
                bo.write(retRefNo,0,12);
            }

            // Bit 38 Authorization ID
            if ( bitMapArray[37])
            {
                bo.write(authId,0,6);
            }

            // Bit 39 Response Code
            if ( bitMapArray[38])
            {
                bo.write(responseCode,0,2);
            }

            // Bit 41 Card Acceptor Terminal ID
            if ( bitMapArray[40])
            {
                bo.write(cardAccTId,0,8);
            }

            // Bit 42 Card Acceptor ID
            if ( bitMapArray[41])
            {
                bo.write(cardAccId,0,15);
            }

            // Bit 48 Additional Data 48
            if ( bitMapArray[47])
            {
                bo.write(addData48Length,0,2);
                bo.write(addData48Data,0,this.getaddData48Length());
            }

            // Bit 52 PIN Data
            if ( bitMapArray[51])
            {
                bo.write(pinData,0,8);
            }

            // Bit 54 Additional Amount
            if ( bitMapArray[53])
            {
                bo.write(addAmountLength,0,2);
                bo.write(addAmountData,0,this.getaddAmountLength());
            }

            // Bit 55 emvData
            if ( bitMapArray[54])
            {
                bo.write(addEmvData,0,emvDataLength);
            }

            // Bit 60 Original Data / Batch Number
            if ( bitMapArray[59])
            {
                bo.write(oriDataLength,0,2);
                bo.write(oriData,0,this.getoriDataLength());
            }

            // Bit 61 Additional Data 61
            if ( bitMapArray[60])
            {
                bo.write(addData61Length,0,2);
                bo.write(addData61Data,0,this.getaddData61Length());
            }

            // Bit 62 Invoice/ECR ref / Points Batch Totals
            if ( bitMapArray[61])
            {
                bo.write(invoiceData,0,9);
            }

            // Bit 63 Additional Data / Batch Totals
            if ( bitMapArray[62])
            {
                bo.write(addData63Data,0,25);
            }

            // Data Length
            msgLength = bo.size();

            String dataLengthStr = AddChar.addString(ByteHandler.toHex(bo.size()), 4, "0", true);

            dataLength[0] =  ByteHandler.stringToByte(dataLengthStr.substring(0,2));
            dataLength[1] =  ByteHandler.stringToByte(dataLengthStr.substring(2,4));

            // Construct the whole message with data length
            ByteArrayOutputStream boMsg = new ByteArrayOutputStream();

            boMsg.write(dataLength,0,2);
            boMsg.write(bo.toByteArray(),0,bo.size());

            msgBody = boMsg.toByteArray();

            bo.close();
            boMsg.close();

            return msgBody;
        }catch (Exception e){
            System.out.println("BBL Message - construct() eroor: " + e.getMessage());
            return null;
        }
    }

    public boolean destruct(String data) throws Exception
    {
        try
        {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();

            String offset = "";
            msgLength = Integer.parseInt(data.substring(0,4), 16);
            offset = data.substring(4);

            // msgBody
            msgBody = ISO8583Utils.getInstance().strToBcd(offset, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);

            // tpduHeader
            data = offset.substring(0,10);
            tpduHeader = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
            offset = offset.substring(10);

            // msgType
            data = offset.substring(0,4);
            msgType = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
            offset = offset.substring(4);

            // bitMap1
            data = offset.substring(0,16);
            bitMap1 = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
            offset = offset.substring(16);


            // Decode the Bit Map to check which bits are included in the message
            decodeBitMap();

            // Bit 2  Primary Account Number
            if ( bitMapArray[1])
            {
                // pAccLength
                data = offset.substring(0,2);
                pAccLength = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(2);

                // pAccData
                data = offset.substring(0, Integer.parseInt(data));
                pAccData = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(data.length());

                if ( getpAccData().substring(0,1).equals("4"))
                    cardType = "VISA";
                else if ( getpAccData().substring(0,1).equals("5"))
                    cardType = "Master";
                else
                    cardType = "None";
            }

            // Bit 3  Processing Code
            if ( bitMapArray[2]) {
                data = offset.substring(0,6);
                processCode = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(6);
            }

            // Bit 4  Transaction Amount
            if ( bitMapArray[3]) {
                data = offset.substring(0,12);
                tranAmount = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(12);
            }

            // Bit 11 System Trace Audit Number
            if ( bitMapArray[10]) {
                data = offset.substring(0,6);
                sysTraceNo = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(6);
            }

            // Bit 12 Local Transaction Time
            if ( bitMapArray[11]) {
                data = offset.substring(0,6);
                txTime = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(6);

            }

            // Bit 13 Local Transaction Date
            if ( bitMapArray[12]) {
                data = offset.substring(0,4);
                txDate = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(4);
            }

            // Bit 14  Account Expiry Date
            if ( bitMapArray[13]) {
                data = offset.substring(0,4);
                expDate =  ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(4);
            }

            // Bit 22 POS Entry Mode
            if ( bitMapArray[21]) {
                data = offset.substring(0,4);
                posEntryMode =  ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(4);
            }

            // Bit 24 Network International ID
            if ( bitMapArray[23]) {
                data = offset.substring(0,4);
                networkId =  ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(4);
            }

            // Bit 25 POS Condition Code
            if ( bitMapArray[24]) {
                data = offset.substring(0,2);
                posCondCode = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(2);
            }

            // Bit 35 Track 2 Data
            if ( bitMapArray[34])
            {
                // Length
                data = offset.substring(0,2);
                track2Length = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(2);

                // Data
                data = offset.substring(0, Integer.parseInt(data));
                track2Data = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(data.length());
            }

            // Bit 37 Retrieval Reference Number
            if ( bitMapArray[36])
            {
                data = offset.substring(0,24);
                retRefNo =  ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(24);
            }

            // Bit 38 Authorization ID
            if ( bitMapArray[37])
            {
                data = offset.substring(0,12);
                authId = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(12);
            }

            // Bit 39 Response Code
            if ( bitMapArray[38])
            {
                data = offset.substring(0,4);
                responseCode = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(4);
            }

            // Bit 41 Card Acceptor Terminal ID
            if ( bitMapArray[40])
            {
                data = offset.substring(0,16);
                cardAccTId = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(16);
            }

            // Bit 42 Card Acceptor ID
            if ( bitMapArray[41])
            {
                data = offset.substring(0,30);
                cardAccId = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(30);
            }

            // Bit 48 Additional Data 48
            if ( bitMapArray[47])
            {
                // Length
                data = offset.substring(0,3);
                addData48Length = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(3);

                // Data
                data = offset.substring(0, Integer.parseInt(data));
                addData48Data = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(data.length());
            }

            // Bit 55 EMV data
            if ( bitMapArray[54])
            {
                // Length
                data = offset.substring(0,4);
                emvDataLength = Integer.parseInt(data);
                offset = offset.substring(4);

                // Data
                data = offset.substring(0, Integer.parseInt(data)*2);
                addEmvData = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(data.length());
            }

            // Bit 60 Original Data / Batch Number
            if ( bitMapArray[59])
            {
                // Length
                data = offset.substring(0,6);
                oriDataLength = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(6);

                // Data
                data = offset.substring(Integer.parseInt(data));
                oriData = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(data.length());
            }

            // Bit 62 Invoice/ECR ref / Points Batch Totals
            if ( bitMapArray[61])
            {
                // invoiceLength
                data = convertHexToString(offset.substring(0,6));
                invoiceLength = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(6);

                // invoiceData
                data = offset.substring(0, Integer.parseInt(data) *2);
                invoiceData = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(data.length());
            }

            // Bit 63 Additional Data / Batch Totals
            if ( bitMapArray[62])
            {
                // Length
                data = convertHexToString(offset.substring(0,6));
                addData63Length = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
                offset = offset.substring(6);

                // Data
                data = offset.substring(0, Integer.parseInt(data) *2);
                addData63Data = ISO8583Utils.getInstance().strToBcd(data, ISO8583Utils.EPaddingPosition.PADDING_RIGHT);
            }

            bo.close();

            return true;

        }
        catch (Exception e){
            System.out.println("Message - Method destruct(): error - "+e.getMessage());
            return false;
        }
    }

    public String convertHexToString(String hex){

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        for( int i=0; i<hex.length()-1; i+=2 ){

            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char)decimal);

            temp.append(decimal);
        }

        return sb.toString();
    }
}
