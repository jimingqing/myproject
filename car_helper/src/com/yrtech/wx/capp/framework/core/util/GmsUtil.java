package com.yrtech.wx.capp.framework.core.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

public class GmsUtil{



	public static int validAmt( String TransAmt ){
		int 	ret , len = TransAmt.length( ) ;
		char	ch	;
		for( ret = 0 ; ret < len ; ret ++ )
	    	{
	      		ch = TransAmt.charAt( ret ) ;
	
			if( ret == 0 )
			{
				if(  ch > '9' || ch < '0' )
					return -202 ;
				if( ch == '0' && len != 4 )
					return -202 ;
			}
	
			if( ret == len - 3 )
			{
				if( ch != '.' ) 
					return -202 ;
				else
					continue ;
			}
	
			if(  ch >'9' || ch <'0' )
				return -202 ;
		}
	
		return 0 ;
	}
	
	
	/* flag -- 'a' : application mode ，在应用程序中调用*/
	/* flag -- 'w' : websphere mode ，在websphere环境中调用　
			and default flag value is 'w' */ 
	/* len -- 返回的seq_id长度，左补0，缺省为6*/
	
	
	// delete all blank in a string
	/**
	*将一个String里的空格全部去除
	*@return 去除空格后的String
	*/
	static public String delallblank( String s )
	{
		int len = s.length( ) ;
		int i , k ;
		char cc[ ] = new char[ len ] ;
	
		char 	ch ;
	
		for( i = 0 , k = 0 ; i < len ; i ++ )
		{
			ch = s.charAt( i ) ;	
			if( ch == ' ' ) continue ;
	
			cc[ k ] = ch ;
			k ++ ;
		} 
	
		return( new String( cc , 0 , k ) ) ;
	}
	
	// input : 10.004 , output : 10.00 ; 
	// input : 10.006 , output : 10.01 ;
	/**
	*对浮点数精确到小数点后两位，四舍五入  
	* input : 10.004 , output : 10.00 ; 
	* input : 10.006 , output : 10.01 ;
	*@return 精确后的浮点数
	*/
	
	static public float fRound( float f ) 
	{
	
		long l , l0 ;
	
		l = ( long ) ( f * 100 ) ;
		l0 = ( long ) ( f * 1000 ) ;
	
		l0 = l0 - l * 10 ;
		if( l0 >= 5 ) 
			l ++ ;
	
		return ( ( float )( l / 100.0 ) ) ;
	}
	/**
	*将浮点数精确到两位后转化为字符串
	*/
	static public String float2str(  float f )
	{
		long amt ;
		
		amt = ( long ) ( fRound( f ) * 100 ) ;
	
		return( CPublic.leftPad( "" + amt , 12 , '0' ) ) ;
	
	}
	/**
	*将金额字符串格式化
	*@return 转化为以分为单位前补零的12位字符串。
	*/
	static public String amt2str( String s )
	{
		long amt ;
		
		amt = ( long )(( new Double( s ) ).doubleValue( ) * 100 ) ;
	
		return String.valueOf(amt) ;
	
	}
	
	
	static public String double2amt( String s_amt )
	{
		return( double2amt( ( new Double( s_amt ) ).doubleValue( ) ) ) ;
	}
	
	static public String double2amt( double f )
	{
	        java.text.DecimalFormat dmf = new java.text.DecimalFormat("0.00") ;
	        return ( dmf.format( f ) ) ;
	}
	
	
	
	/**
	*将String 转化为金额小数点后定长的String
	*@param bit 小数点后精确到多少位
	*@return String为带小数点后bit位的String，
	*比原来减位的话，为四舍五入。
	*本函数谨慎使用，扩位结果不可测。
	*/
	static public String float2amt( String s , int bit )
	{
	
	        return( float2amt( ( new Float( s ) ).floatValue( ) , bit ) ) ;
	}
	/**
	*将浮点数转化为金额float
	*@param bit 小数点后精确到多少位
	*@return 对浮点数进行去尾取整，比如float2amt(5.9876,5)，返回值为String "5.00000"
	*/
	static public String float2amt( float f , int bit )
	{
	
	        long    l1 , l2 , l ;
	        float  	ff      ;
	
	        l1 = ( long ) f ;
	        ff = f - l1 ;
	        l = 1 ;
		
		int	i ;
		for( i = 0 ; i < bit ; i ++ )
		{
			l = l * 10 ;
		}
	
		l2 = ( long )( ff * l ) ;
	
		//System.out.println(l2);
		//System.out.println(ff);
	
	        if( ff * l * 10 - l2 * 10 >= 5 )
	                l2 ++ ;
	
	        return( "" + l1  + "." + CPublic.leftPad( "" + l2 , bit , '0' ) ) ;
	}
	/**
	*将字符型金额转换为long型
	*@return long为去除String右起第三位后的转化结果。如"5432"结果为532
	*/
	static public long amt2long( String s )
	{
		long	l1 , l2 ;
	
		int	len = s.length( ) ;
		l1 = new Long( s.substring( 0 , len - 3 ) ).longValue( ) ;
		l2 = new Long( s.substring( len - 2 ) ).longValue( )  ;
	
		return( l1 * 100 + l2 ) ;
	}
	/**
	*将字符型金额最后两位转换为小数点后两位。
	*@return String里原String的最后两位会被认为是小数点后两位。
	*/
	static public String long2amt( String s )
	{
		return( long2amt( ( new Long( s ) ) .longValue( ) ) ) ;
	}
	/**
	*将long型金额转换为字符型
	*@return String里long的最后两位会被认为是小数点后两位。
	*/
	static public String long2amt( long l )
	{
	        long amt1 , amt2 ;
	
	        amt1 = Math.abs((long)(l/100)) ;
	
	        amt2 = Math.abs(l%100) ;
		if(l<0) 
		{
			return( "-" + amt1  + "." + CPublic.leftPad( "" + amt2 , 2 , '0' ) ) ;
		} 
		else 
		{
			return( "" + amt1  + "." + CPublic.leftPad( "" + amt2 , 2 , '0' ) ) ;
		}
	}
	
	/**
	*将字符型金额最后两位转换为小数点后两位。
	*@return String里原String的最后两位会被认为是小数点后两位。
	*/
	
	static public String str2amt( String Amt )
	{
		long amt , amt1 , amt2 ;
		
		amt = ( new Long( Amt ) ).longValue( ) ;
	
		amt1 = (long)(amt/100) ;
	
		amt2 = amt%100 ;
	
		return( "" + amt1  + "." + CPublic.leftPad( "" + amt2 , 2 , '0' ) ) ;
	
	}
	
	/**
	*计算下一天的字符串
	*@param String YYYYMMDD
	*@return String YYYYMMDD
	*/
	static public String NextDay( String Today ) 
	{
	
		int year , month , day ;
	
		day 	= Integer.parseInt( Today.substring( 6 , 8 ) ) ;
		if( day < 28 ) 
		{
			day ++ ;
			if( day < 10 ) 
				return( Today.substring( 0 , 6 ) + "0" + day ) ;
			else 
				return( Today.substring( 0 , 6 ) + day ) ;
		}
	
		year 	= Integer.parseInt( Today.substring( 0 , 4 ) ) ;
		month 	= Integer.parseInt( Today.substring( 4 , 6 ) ) ;
	
		switch( month ) 	
		{
			case 1 :
			case 3 :
			case 5 :
			case 7 :
			case 8 :
			case 10 :
			case 12 :
				if( day < 31 ) 
				{
					day ++ ;
					if( day < 10 ) 
						return( Today.substring( 0 , 6 ) + "0" + day ) ;
					else 
						return( Today.substring( 0 , 6 ) + day ) ;
				}
				else
				{
					day = 1 ;
					month ++ ;
				}
				break	;
			case 2 :
				if( day == 29 )
				{
					day = 1 ;
					month = 3 ;
					break ;
				}
				else if( day == 28 )
				{
					month = 3 ;
					day = 1 ;
	
					if( year%4 == 0 )
					{
						month = 2 ;
						day = 29 ;
						if( year%100 == 0 )
						if( year%400 != 0 ) 
						{
							month = 3 ;
							day = 1 ;
						}
					}
				}
				break	;
			case 4 :
			case 6 :
			case 9 :
			case 11 :
				if( day < 30 ) 
				{
					day ++ ;
					if( day < 10 ) 
						return( Today.substring( 0 , 6 ) + "0" + day ) ;
					else 
						return( Today.substring( 0 , 6 ) + day ) ;
				}
				else
				{
					day = 1 ;
					month ++ ;
				}
				break	;
		}
	
		if( month >12 ) 
		{
			year ++ ;
			month = 1 ;
		}
	
		String sMonth ;
		if( month < 10 )
			sMonth = "0" + month ;
		else 
			sMonth =  "" + month ;
	
		String sDay ;
		if( day < 10 )
			sDay = "0" + day ;
		else 
			sDay =  "" + day ;
	
		return( "" + year + sMonth + sDay ) ;
	
	}
	/**
	*检查一个String是否为空
	*@return 为空则true
	*/
	static public boolean isEmpty(String s)
	{
		if ((s == null) || (s.length() == 0))
		{
			return true;
		}
		return false;
	}
	/**
	*字符串里是否包含不可见字符
	*@return true为包含不可见字符
	*/
	static public boolean isWhitespace (String s)
	{
		String whitespace = " \t\n\r";
		
		int i;
		for (i = 0; i < s.length(); i++)
		{   
			char c = s.charAt(i);
			if (whitespace.indexOf(c) >= 0) 
			{
				return true;
			}
		}
		return false;
	}
	/**
	*String的长度是否在最小最大长度范围之间
	*@return true 为在范围之间
	*/
	static public boolean islength(String s, int maxlength, int minlength)
	{
		if ((s.length()>=minlength)&&(s.length()<=maxlength))
		{
			return true;
		}
		return false;
	}
	/**
	*String s是否任何字符都不包含在String bag里。
	*@return 如果s里有任一字符在bag里出现，则return false。
	*/
	static public boolean isCharsInBagEx (String s, String bag)
	{
		int i;
		char c;
		
		for (i = 0; i < s.length(); i++)
		{   
		      c = s.charAt(i);
			if (bag.indexOf(c) > -1) 
		      return false;
		}
		return true;
	}
	/**
	*String s是否有任一字符包含在String bag里。
	*@return 如果s里有任一字符在bag里出现，则return true。
	*/
	
	static public boolean isCharsInBag (String s, String bag)
	{
		int i;
		char c;
	 
		for (i = 0; i < s.length(); i++)
		{   
		    c = s.charAt (i);
		    if (bag.indexOf(c) > -1) return true;
		}
		return false;
	}
	
	/**
	*判断字符窜是否全是数字组合
	*@return true,是 false,否
	*/
	
	static public boolean isNumber (String s)
	{
	    StringBuffer sb = new StringBuffer(s) ;
	    for( int i=0;i<s.length();i++ )
	    {
	      char c = sb.charAt(i) ;
	      if( c < '0' || c > '9' )
	        return false ;
	    }
	    return true ;
	}
	
	
	
	/**
	*判断字符窜是否全是数字组合
	*@return true,是 false,否
	*/
	
	static public boolean isNum (String s)
	{
	    StringBuffer sb = new StringBuffer(s) ;
	    for( int i=0;i<s.length();i++ )
	    {
	      char c = sb.charAt(i) ;
	      if( c < '0' || c > '9' )
	        return false ;
	    }
	    return true ;
	}
	
	
	
	
	/**
	*判断字符窜是否全是合理的email
	*@return true,是 false,否
	*/
	
	static public boolean isEmail (String s)
	{
	    
		String mail_box = "" ;
		int ind = -1 ;
		int len = -1 ;
		
		
		if( s == null )
			return false ;
		
		
		len = s.length() ;
		
		if( len <= 0 )
			return false ;
	
		
		ind = s.indexOf( '@' ) ;
		
		if( ind <= 0 )
			return false ;
		
		
		/* if '@' is the last char , return false */
		if( ind == len -1 )
			return false ;
		
			
		
		/* check the mail_box */
		mail_box = s.substring( ind + 1 ) ;
		
		len = mail_box.length() ;
		
		ind = mail_box.indexOf( '.' ) ;
		
		if( ind <= 0 )
			return false ;
		
		
		/* if '.' is the last char , return false */
		if( ind == len -1 )
			return false ;
		
		
		
	    return true ;
	
	}
	
	
	
	
	
	
	/**
	*将一个字符串取byte[]后附加在另一个后面。
	*先将bData从start开始len位填充为一固定值。然后再附加byte[]。
	*@param bData  前面的byte[]
	*@param start  位移从前面的byte[]的哪一位开始附加
	*@param s  附加用来取byte[]的String
	*@param len 附加多少位byte
	*@param mode  如果是'|'，则开始填充' '，否则填充'0'；
	*如果是'|'，则从start开始顺位附加，否则从start+len-s.length处开始附加。
	*/
	
	static public void append( byte bData[ ] , int Start , String s , int len , char mode )
	{
	
		byte bTmp[ ] = s.getBytes( ) ;
		int  l = bTmp.length ;
	
		byte bb = ( byte ) '0' ;
		if( mode == 'l' ) 
		{
			bb = ( byte ) ' ' ;
		}
	
		int i ;
		for( i = 0 ; i < len ; i ++ ) bData[ Start + i ] = bb ;
	
		if( l > len )  l = len ;
	
		if( mode == 'l' ) 
		{
			for( i = 0 ; i < l ; i ++ ) 
				bData[ Start + i ] = bTmp [ i ] ;
		}
		else
		{
			for( i = 0 ; i < l ; i ++ ) 
				bData[ Start + len - l + i ] = bTmp [ i ] ;
		}
	
	}
	/**
	*将一个byte[] 附加在另一个后面。
	*先将bData从start开始len位填充为一固定值。然后再附加byte[]。
	*@param bData  前面的byte[]
	*@param start  位移从前面的byte[]的哪一位开始附加
	*@param s  附加的byte[]
	*@param len 附加多少位byte
	*@param mode  如果是'|'，则开始填充' '，否则填充'0'；
	*如果是'|'，则从start开始顺位附加，否则从start+len-s.length处开始附加。
	
	*/
	static public void append( byte bData[ ] , int Start , byte[ ] s , int len , char mode )
	{
	
		int  l = s.length ;
	
		byte bb = ( byte ) '0' ;
		if( mode == 'l' ) 
		{
			bb = ( byte ) ' ' ;
		}
	
		int i ;
		for( i = 0 ; i < len ; i ++ ) bData[ Start + i ] = bb ;
	
		if( l > len )  l = len ;
	
		if( mode == 'l' ) 
		{
			for( i = 0 ; i < l ; i ++ ) 
				bData[ Start + i ] = s [ i ] ;
		}
		else
		{
			for( i = 0 ; i < l ; i ++ ) 
				bData[ Start + len - l + i ] = s [ i ] ;
		}
	
	}
	/**
	*将一个byte[]从start开始截取len位。
	*@param bData 原byte数组
	*@param start 截取开始处
	*@param len 截取位数
	*@return 截取出来的byte[]
	*/
	static public byte[ ] extract( byte bData[ ] , int start , int len )
	{
		int i ;
	
		byte bTmp[ ] = new byte [ len ] ; 
	
		for( i = 0 ; i < len ; i ++ ) 
		{
			bTmp[ i	] = bData[ start + i ] ;
		} 
	
		return ( bTmp ) ;
		
	}
	/**
	*将16进制byte按16进制代码转化为可见的Asc码。
	*@param len 只对输入的byte[]按前len位进行操作。
	*@param in 输入的byte[]。
	*@return 可见的Asc字符串。
	*/
	public static String Hex2Asc(int len , byte[ ] in )
	{
	        int i;
	
		byte[ ] out ;
	
		byte  h , l ;
	
		out = new byte[ 2 * len ] ;
	
	        for(i = 0; i < len ; i++)
	        {
			h = ( byte ) ( in[ i ] >> 4 ) ;
			h = ( byte ) ( h & 0x0F ) ;
	
			l = ( byte ) ( in[ i ] & 0x0F ) ;
	
			out[i * 2] = ( byte ) ( (h > 9) ? 'A' + h - 10 : '0' + h ) ;
	                out[i * 2 + 1] = ( byte ) ( (l > 9) ? 'A' + l - 10 : '0' + l ) ;
	        }
	        return( new String( out ) ) ;
	}
	/**
	*将可见的Asc码按16进制代码转化为16进制byte。
	*@param len 只对输入的String按前len位进行操作，len必须为2的倍数。
	*@param in 输入的可见的Asc码String。
	*@return 16进制byte[]。
	*/
	public static byte[ ] Asc2Hex(int len , String in ) 
	{
		int i , j ;
		byte b , h , l ;
		char	ch	;
	
		in = in.toUpperCase( ) ;
	
		if( len % 2 != 0 )
			return null ;
	
		for(i = 0; i < len; i ++)
		{
			ch = in.charAt( i ) ;
			if(( ch < '0') || (( ch > '9') && ( ch < 'A')) || ( ch > 'Z'))
				return null ;
		}
	
		byte[ ] out = new byte[ len / 2 ] ;
	
		for(i = 0; i < len / 2; i++)
		{
			ch = in.charAt( i * 2 ) ;
			h = (byte)(((ch>='0')&&(ch<= '9')) ? ch-'0' : ch-'A' + 10 ) ;
	
			ch = in.charAt( i * 2 + 1 ) ;
			l = (byte)(((ch>='0')&&(ch<= '9')) ? ch-'0' : ch-'A' + 10 ) ;
	
			out[ i ] = ( byte ) ( h * 16 + l ) ;
		}
	
		return out ;
	}
	
		
	
	
		public static String getYesterday( String today )
		{
		
			int year , month , day ;
		
			day 	= Integer.parseInt( today.substring( 6 , 8 ) ) ;
			if( day > 1 ) 
			{
				day -- ;
				if( day < 10 ) 
					return( today.substring( 0 , 6 ) + "0" + day ) ;
				else 
					return( today.substring( 0 , 6 ) + day ) ;
			}
		
			year 	= Integer.parseInt( today.substring( 0 , 4 ) ) ;
			month 	= Integer.parseInt( today.substring( 4 , 6 ) ) ;
		
			month -- ;
		
			if( month <= 0 ) {
				month = 12 ;
				year -- ;
			}
		
			switch( month ) {
				case 1 :
				case 3 :
				case 5 :
				case 7 :
				case 8 :
				case 10 :
				case 12 :
					day = 31 ;
					break	;
				case 2 :
					day = 28 ;
					if( year%4 == 0 )
					{
						day = 29 ;
						if( year%100 == 0 )
						if( year%400 != 0 ) 
							day = 28 ;
					}
					break	;
				case 4 :
				case 6 :
				case 9 :
				case 11 :
					day = 30 ;
					break	;
			}
		
			String sMonth ;
			if( month < 10 )
				sMonth = "0" + month ;
			else 
				sMonth =  "" + month ;
		
			String sDay ;
			if( day < 10 )
				sDay = "0" + day ;
			else 
				sDay =  "" + day ;
		
			return( "" + year + sMonth + sDay ) ;
		
		}
		
	  /**
	   * 取得oldDate的相隔sizeOfParamValue的日期，可以带符号
	   * @param oldDate                   初始日期
	   * @param sizeOfParamValue          时间间隔（天）
	   * @return  String
	   */
	  public static String getNextDate(String oldDate, int sizeOfParamValue)
	  {
	    try{
	      if(oldDate.length() != 8 || !isNumber(oldDate))
	      {
	        System.out.println("oldDate 必须是YYYYMMDD格式");
	        return null;
	      }
	      int year = new Integer(oldDate.substring(0, 4)).intValue();
	      int month = new Integer(oldDate.substring(4, 6)).intValue() - 1;
	      int date = new Integer(oldDate.substring(6, 8)).intValue();
	      //System.out.println("year[" + year + "]month[" + month + "]date[" + date + "]ParamValue[" + ParamValue + "]");
	      java.util.Calendar calender = java.util.Calendar.getInstance();
	      calender.set(year, month, date);
	      calender.add(java.util.Calendar.DATE, sizeOfParamValue);
	      year = calender.get(java.util.Calendar.YEAR);
	      month = calender.get(java.util.Calendar.MONTH) + 1;
	      date = calender.get(java.util.Calendar.DATE);
	      //System.out.println("year[" + year + "]month[" + month + "]date[" + date + "]ParamValue[" + ParamValue + "]");
	      String NextDate = CPublic.leftPad("" + year, 4, '0') + CPublic.leftPad("" + month, 2, '0') + CPublic.leftPad("" + date, 2, '0');
	      return NextDate;
	    }catch(Exception e){
	      System.out.println("Exception in getNextDate[" + e.getMessage() + "]");
	      return null;
	    }
	  }	
	
		/* 取两者日期差 */
	  	public static int getDayBetween(String s_date1 , String s_date2 ) 
		{
			int     year1 , year2 , month1 , month2 , day1 , day2 ;
			year1    = Integer.parseInt( s_date1.substring( 0 , 4 ) ) ;
			month1   = Integer.parseInt( s_date1.substring( 4 , 6 ) ) - 1 ;
			day1     = Integer.parseInt( s_date1.substring( 6 , 8 ) ) ;
			year2    = Integer.parseInt( s_date2.substring( 0 , 4 ) ) ;
			month2   = Integer.parseInt( s_date2.substring( 4 , 6 ) ) - 1 ;
			day2     = Integer.parseInt( s_date2.substring( 6 , 8 ) ) ;
			java.util.Calendar calender1 = java.util.Calendar.getInstance();
			java.util.Calendar calender2 = java.util.Calendar.getInstance();
			calender1.set(year1, month1, day1);
			calender2.set(year2, month2, day2);
	        
			long	ll = 86400000l ;
			return(( int )( ( calender1.getTimeInMillis() - calender2.getTimeInMillis())/ll) );
			
		}
	
		/* 取之前或之后某天的日期 offset可以为正数或负数。*/
	  	public static String getOtherDate(String s_date , int offset ) 
		{
		 	return getNextDate(s_date, offset);
		}
		
		
		
		
		
		/**
		 * 以下三个方法实现从客户端到服务端的通讯，传输的数据项以|作为分割，如"137018455553|younger|07"，客户端通过clientComm发送和接收数据，服务端通过serverRecv接收数据和serverSend发送数据。
		 * 
		 * @param Ip
		 *            服务端Ip,
		 * @param Port
		 *            服务端Port,
		 * @param SendData ,
		 *            发送的数据，以|作为分割，如"137018455553|younger|07"
		 * @return String[ ] ,收到数据数据项以数组方式返回,如果为null,则表示执行失败。
		 */
		static public String[] clientComm(String Ip, int Port, String SendData) { 	//080407从中间账户复制而来
	
			Socket sid = null;
			DataOutputStream out = null;
			DataInputStream in = null;
			int len0 = 0, len = 0, k = 0;
			byte[] bData, bSendData, bRecvData;
	
			bSendData = SendData.getBytes();
	
			try {
	
				sid = new Socket(Ip, Port);
				out = new DataOutputStream(sid.getOutputStream());
				in = new DataInputStream(sid.getInputStream());
	
				sid.setSoTimeout(30000);
	
				String sMsgLen = "" + bSendData.length;
				bData = new byte[4];
				GmsUtil.append(bData, 0, "" + bSendData.length, 4, 'r');
				out.write(bData);
				out.write(bSendData);
	
				in.read(bData, 0, 4);
				len0 = (new Integer(new String(bData))).intValue();
	
				int j = len0;
				bRecvData = new byte[len0];
				while (true) {
					if (j <= 0)
						break;
	
					len = in.read(bRecvData, k, j);
					if (len < 0)
						break;
	
					k += len;
					j -= len;
				}
	
				sid.close();
				in.close();
				out.close();
			} catch (UnknownHostException e) {
				try {
					sid.close();
					in.close();
					out.close();
				} catch (IOException sube) {
					System.out.println("io err" + sube + "]");
					return null;
				} catch (Exception ee) {
					System.out.println("other" + ee + "]");
					return null;
				}
				System.out.println("unkown host err" + e + "]");
				return null;
			} catch (IOException e) {
				try {
					sid.close();
					in.close();
					out.close();
				} catch (IOException sube) {
					System.out.println("io err" + sube + "]");
					return null;
				} catch (Exception ee) {
					System.out.println("other" + ee + "]");
					return null;
				}
				System.out.println("io err" + e + "]");
				return null;
			} catch (Exception e) {
				try {
					sid.close();
					in.close();
					out.close();
				} catch (IOException sube) {
					System.out.println("io err" + sube + "]");
					return null;
				}
				System.out.println("other err" + e + "]");
				return null;
			}
	
			len = 1;
			for (k = 0; k < len0; k++) {
				if (bRecvData[k] == '|')
					len++;
			}
	
			String ssData[] = new String[len];
	
			int pos1 = 0, pos2, cnt = 0;
			for (k = 0; k < len0; k++) {
				if (bRecvData[k] == '|') {
					if (k == pos1) {
						ssData[cnt] = "";
					} else {
						pos2 = k - 1;
						ssData[cnt] = new String(bRecvData, pos1, pos2 - pos1 + 1);
					}
					pos1 = k + 1;
					cnt++;
				}
			}
	
			ssData[cnt] = new String(bRecvData, pos1, k - pos1);
	
			return (ssData);
		}
	
		
		
		
		
		
		
		
		public static int getTimeInterval(String s_date1, String s_date2){
			int     year1 , month1, day1, hour1, min1, sec1;
			int 	year2, month2 ,  day2, hour2, min2, sec2;
			year1    = Integer.parseInt( s_date1.substring( 0 , 4 ) ) ;
			month1   = Integer.parseInt( s_date1.substring( 4 , 6 ) ) - 1 ;
			day1     = Integer.parseInt( s_date1.substring( 6 , 8 ) ) ;
			hour1	 = Integer.parseInt( s_date1.substring( 8 , 10 ) ) ;
			min1	 = Integer.parseInt( s_date1.substring( 10 , 12 ) ) ;
			sec1	 = Integer.parseInt( s_date1.substring( 12 , 14 ) ) ;
			year2    = Integer.parseInt( s_date2.substring( 0 , 4 ) ) ;
			month2   = Integer.parseInt( s_date2.substring( 4 , 6 ) ) - 1 ;
			day2     = Integer.parseInt( s_date2.substring( 6 , 8 ) ) ;
			hour2	 = Integer.parseInt( s_date2.substring( 8 , 10 ) ) ;
			min2	 = Integer.parseInt( s_date2.substring( 10 , 12 ) ) ;
			sec2	 = Integer.parseInt( s_date2.substring( 12 , 14 ) ) ;
			java.util.Calendar calender1 = java.util.Calendar.getInstance();
			java.util.Calendar calender2 = java.util.Calendar.getInstance();
			calender1.set(year1, month1, day1, hour1, min1, sec1);
			calender2.set(year2, month2, day2, hour2, min2, sec2);
			long	tl = 1000 ;
			return((int)((calender1.getTimeInMillis() - calender2.getTimeInMillis())/tl));
		}
		
		
		public static String getDateSqlStr(String dateBegin, String dateEnd){
			String returnStr = "";
			
			if(dateBegin==null || dateEnd==null)
				return "";
			
			if(dateBegin.trim().equals("") || dateEnd.trim().equals(""))
				return "";
			
			int dep = GmsUtil.getDayBetween(dateEnd, dateBegin);
			String[] datelist = new String[dep+1];
			returnStr += " ( ";
			
			for(int i=0 ; i<dep+1 ; i++){
				String tmp = GmsUtil.getOtherDate(dateBegin, i);
				returnStr += "'" + tmp + "'";
				if(i<dep)
					returnStr += ",";
			}
			
			returnStr += " ) ";
			return returnStr;
		}
		
		
		public static String getRemoteIpAddr(HttpServletRequest request){
			try{
				String ipAddr = request.getHeader("X-Forwarded-For");
				if(ipAddr == null || ipAddr.trim().length() == 0){
					ipAddr = request.getHeader("Proxy-Client-IP");			
				}
				if(ipAddr == null || ipAddr.trim().length() == 0){
					ipAddr = request.getHeader("WL-Proxy-Client-IP");			
				}
				if(ipAddr == null || ipAddr.trim().length() == 0){
					ipAddr = request.getRemoteAddr();			
				}
				return ipAddr;
			}catch(Exception e){
				System.out.println(e);
				return "";
			}
		}
		
		/**
		 * 验证日期
		 * @param date	yyyymmdd
		 * @return
		 */
		public static boolean isDate(String date){
			String regex = "[1-9][0-9]{3}(0[0-9]|1[0-2])(0[0-9]|1[0-9]|2[0-9]|3[0-1])";
			return date.matches(regex);
		}
		
		/**
		 * 验证时间
		 * @param time hhmmss
		 * @return
		 */
		public static boolean isTime(String time){
			
			if (time != null && time.length() == 6 && GmsUtil.isNumber(time)){
				int hh = Integer.parseInt( time.substring(0, 2) );
				int mm = Integer.parseInt( time.substring(2, 4) );
				int ss = Integer.parseInt( time.substring(4) );
				
				if (hh < 24 && mm < 60 && ss < 60){
					return true;
				}else{
					return false;
				}
			}
			
			return false;
		}
		
		public static void main(String[] args){
	
//			System.out.println( GmsUtil.isDate( "20091212" ) );
//			System.out.println( GmsUtil.isTime( "135207" ) );
			System.out.println(validAmt(double2amt("3.9")));
			/*
			
			System.out.println("[" + getDayBetween("20060420", "20060424") + "]");
			System.out.println("[" + getDayBetween("20060321", "20060401") + "]");
			System.out.println("[" + getDayBetween("20060224", "20060301") + "]");
			
			System.out.println("getTimeInterval[" + getTimeInterval("20060505011212", "20060505011200") + "]");
			System.out.println("getTimeInterval[" + getTimeInterval("20060504011212", "20060505121234") + "]");
			System.out.println("getTimeInterval[" + getTimeInterval("20060505121212", "20060505123534") + "]");
			System.out.println("getTimeInterval[" + getTimeInterval("20060505101212", "20060505121234") + "]");
			*/
		}
		
}
	
