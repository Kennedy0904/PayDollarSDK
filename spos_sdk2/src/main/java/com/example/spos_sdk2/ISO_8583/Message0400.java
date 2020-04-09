/*
	Project 	: KT B2C
	Arthur  	: Edward Ng
	Description	: The program is a java class used for define the tx message format of wing hang bank
			  0200 - Financial Transaction Request

*/
package com.example.spos_sdk2.ISO_8583;

public class                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   Message0400 extends Message
{
    public Message0400()
    {
        super();

        // message type id
        msgType[0] =  ByteHandler.stringToByte("04");
        msgType[1] =  ByteHandler.stringToByte("00");

        // tpdu header
        tpduHeader[0] =  ByteHandler.stringToByte("60");
        tpduHeader[1] =  ByteHandler.stringToByte("00");
        tpduHeader[2] =  ByteHandler.stringToByte("19");

//        // bit map 1
//        bitMap1[0] = (byte) Integer.parseInt(String.valueOf(bit3 | bit4));
//        bitMap1[1] = (byte) Integer.parseInt(String.valueOf(bit3));
//        bitMap1[2] = (byte) Integer.parseInt(String.valueOf(bit6 | bit8 ));
//        bitMap1[3] = (byte) Integer.parseInt(String.valueOf(bit1));
//        bitMap1[4] = (byte) Integer.parseInt(String.valueOf(bit3));
//        bitMap1[5] = (byte) Integer.parseInt(String.valueOf(bit1 | bit2));
//        bitMap1[6] = (byte) Integer.parseInt(String.valueOf(bit7));
//        bitMap1[7] = (byte) Integer.parseInt(String.valueOf(bit6 | bit7));

        // bit map 1
        bitMap1[0] = (byte) Integer.parseInt(String.valueOf(bit3 | bit4));
        bitMap1[1] = (byte) Integer.parseInt(String.valueOf(bit5 | bit6));
        bitMap1[2] = (byte) Integer.parseInt(String.valueOf(bit6 | bit8 ));
        bitMap1[3] = (byte) Integer.parseInt(String.valueOf(bit1));
        bitMap1[4] = (byte) Integer.parseInt(String.valueOf(bit3));
        bitMap1[5] = (byte) Integer.parseInt(String.valueOf(bit1 | bit2));
        bitMap1[6] = (byte) 0;
        bitMap1[7] = (byte) Integer.parseInt(String.valueOf(bit4 | bit7));

        // invoice length
        invoiceLength[0] = ByteHandler.stringToByte("00");
        invoiceLength[1] = ByteHandler.stringToByte("06");

        // Field 63 Length
        addData63Length[0] = ByteHandler.stringToByte("00");
        addData63Length[1] = ByteHandler.stringToByte("25");

    }
}
