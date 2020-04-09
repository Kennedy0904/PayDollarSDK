/*
        Project : B2c
        Creator : Edward Ng

        Description:    The program is a java program used for 
                        handling byte  
*/

package com.example.spos_sdk2.ISO_8583;

public class ByteHandler
{

	public ByteHandler()
	{

	}

	static public byte stringToByte( String ip_str)
	{
		int byteValue = hexToInt(ip_str.substring(0,1)) * 16 + hexToInt(ip_str.substring(1,2));

		if (byteValue > 127 )
			byteValue = byteValue - 256;
		
		return (byte) byteValue ;
	
	}

	static public String packToString(byte ip_byte)
	{
		try 
		{
			String output = "";
			int byteValue = 0;

			Byte tByte = new Byte(ip_byte);

			byteValue = tByte.intValue();

			if ( byteValue < 0 )
				byteValue = byteValue + 256;

			int leftPart = (int)byteValue / 16;
			int rightPart = (int)byteValue % 16;

			if ( rightPart < 0 )
				rightPart = rightPart + 256;

			output = toHex(leftPart) + toHex(rightPart);

			return output ;
		}
		catch ( Exception e )
		{
			System.out.println("BBL");
			return null ;
		}

	}

	static public String arrayToString (byte[] ip_byte , int length )
	{
		String tStr = "";
	
		for ( int i = 0 ; i < length ; i++)	
		{
			tStr = tStr + packToString(ip_byte[i]);
		}

		return tStr;
	}

	static public String charArrayToString (byte[] ip_byte , int length )
	{
		String tStr = "";
	
		for ( int i = 0 ; i < length ; i++)	
		{
			tStr = tStr + String.valueOf((char)ip_byte[i]);
		}

		return tStr;
	}

	static public String toHexString (int value )
	{
		switch ( value )
		{
			case 0 :
				return "0";
			case 1 :
				return "1";
			case 2 :
				return "2";
			case 3 :
				return "3";
			case 4 :
				return "4";
			case 5 :
				return "5";
			case 6 :
				return "6";
			case 7 :
				return "7";
			case 8 :
				return "8";
			case 9 :
				return "9";
			case 10:
				return "A";
			case 11:
				return "B";
			case 12:
				return "C";
			case 13:
				return "D";
			case 14:
				return "E";
			case 15:
				return "F";
			default: 
				return null;
		}
	}

	static public int hexToInt ( String ip_str )
	{
		if ( ip_str.equals("0") ) 
			return 0;
		else if ( ip_str.equals("1") ) 
			return 1;
		else if ( ip_str.equals("2") )
			return 2;
		else if ( ip_str.equals("3") )
			return 3;
		else if ( ip_str.equals("4") )
			return 4;
		else if ( ip_str.equals("5") )
			return 5;
		else if ( ip_str.equals("6") )
			return 6;
		else if ( ip_str.equals("7") )
			return 7;
		else if ( ip_str.equals("8") )
			return 8;
		else if ( ip_str.equals("9") )
			return 9;
		else if ( ip_str.equals("a") || ip_str.equals("A"))
			return 10;
		else if ( ip_str.equals("b") || ip_str.equals("B"))
			return 11;
		else if ( ip_str.equals("c") || ip_str.equals("C"))
			return 12;
		else if ( ip_str.equals("d") || ip_str.equals("D"))
			return 13;
		else if ( ip_str.equals("e") || ip_str.equals("E"))
			return 14;
		else if ( ip_str.equals("f") || ip_str.equals("F"))
			return 15;
		else
			return -1;
	}

	public static String toHex(int n)
	{
  		String h = "" ;
  		int r=0;
  		int q=0;
  		int nn=n ;

  		do
  		{
    			r=nn % 16 ;
    			nn= nn / 16 ;
    			switch (r)
    			{
      				case 10: h = "A" + h; break ;
      				case 11: h = "B" + h; break ;
      				case 12: h = "C" + h; break ;
      				case 13: h = "D" + h; break ;
      				case 14: h = "E" + h; break ;
      				case 15: h = "F" + h; break ;
      				default: h = r + h; break ;
    			}
  		}
		while (nn > 0) ;

		return h ;
	}

}
