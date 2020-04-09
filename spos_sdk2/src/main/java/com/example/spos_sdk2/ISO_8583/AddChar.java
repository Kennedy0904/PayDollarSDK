/*
        Project : B2c
        Creator : Edward Ng

        Description:    The program is a java program used for 
                        Adding characters to the input String 
			in order to make the String have valid str length
*/

package com.example.spos_sdk2.ISO_8583;

public class AddChar
{

	public AddChar()
	{

	}

	static public String addString(String ip_msg,
                                   int	ip_length,
                                   String ip_char ,
                                   boolean ip_front	)
	{
		try 
		{
			ip_msg.trim();	

			// message length 
			int loc_int_length = ip_msg.length();

			for ( int i = 0 ; i <  ip_length - loc_int_length ; i ++ )	
			{
				if ( ip_front )
					ip_msg = ip_char + ip_msg ;
				else
					ip_msg = ip_msg + ip_char;
			}

			return ip_msg ;
		}
		catch ( Exception e )
		{
			
			return null ;
		}

	}
}
