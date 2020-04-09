package com.example.spos_sdk2.ISO_8583;

public class Message0210 extends Message
{
	public Message0210()
	{
		super();	

                dataLength[0] =  ByteHandler.stringToByte("00");
                dataLength[1] =  ByteHandler.stringToByte("3E");

		// message type id
		msgType[0] =  ByteHandler.stringToByte("02");
		msgType[1] =  ByteHandler.stringToByte("10");
	}
}
