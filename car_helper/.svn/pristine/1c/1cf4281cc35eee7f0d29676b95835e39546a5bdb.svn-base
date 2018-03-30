package com.yrtech.wx.capp.framework.core.util;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.BigDecimal;
import java.text.*;
import java.sql.*;



public class CPublic extends java.lang.Object
{
	
	public static char[] DigitalMap={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M',
		'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		

	 public String pwdConvert( String orgString )
	 {
	 	
		return(null);
	 	
	 }		 
	 		

	

	public static String replaceSubString(String orgString ,String resSubString , String replaceString)
	{
		String sTemp = orgString ;
		String sPreString = "" ;
		String sSufString = "" ;
		int    iBlkStart  = 0  ;
		if ( (iBlkStart =orgString.indexOf(resSubString,0))== -1 )
		{
			return orgString ;
		}
		else 
		{
			sPreString = orgString.substring(0,iBlkStart);
			sSufString = orgString.substring(sPreString.length()+resSubString.length());
			return sPreString + replaceString + sSufString ;
		}		
	}	

	
	public static String i2a(int x, int len, int base)
	{
		char s[]=new char[len];
		int i;
		for(i=len-1;i>=0;i--)
		{
			s[i]=DigitalMap[x%base];
			x=x/base;
		}
		return new String(s);
	}
	

	public static String gb2Unicode(String str)
	{
		if(str==null)return null;
		
		try{
			byte [] b= (str.trim()).getBytes("GB2312");
			String rstr=new String(b);
			
			return rstr;
		}catch(Exception e)
		{		
			System.out.println(e.toString());
		}
		return null;
	}



	public static String unicode2Gb(String str)
	{
		if(str==null)return null;
		
		try{
			byte [] b= str.getBytes();
			String rstr=new String(b,"GB2312");
			
			return rstr;
		}catch(Exception e)
		{		
			System.out.println(e.toString());
		}
		return null;
	}


	public static String d2a(double x, int len, int dic) throws Exception
	{
		int i;
		char [] buf=new char[len];
		if(dic >len -1) throw new Exception("the length of digital is longer than total length.");
		
		int dp=len-dic-1;
		buf[dp]='.';
		
		double dicCorrect=0.5;
		for(i=0;i<dic;i++)
		{
			dicCorrect/=10;
		}
		x+=dicCorrect;

		double intPart= Math.floor(x);
		double digPart= x-(int)x;
		
		int intDig;
		for(i=dp+1;i<len;i++)
		{
			digPart=digPart*10;
			intDig=(int)digPart;
			buf[i]=(char)('0'+intDig);
			digPart=digPart-intDig;
		}
		
		double y=0;
		for(i=dp-1;i>=0;i--)
		{
			y=Math.floor(intPart/10);
			intDig=(int)(intPart-y*10);
			if(intPart==0)
			{
				buf[i]=' ';
			}else
			{
				buf[i]=(char)('0'+intDig);
			}
			intPart=y;
		}
		if(y>9)System.out.println("lost dicision!");
		return new String(buf);
	}


	public static String leftPad(String str,int len,char ch)
	{
		StringBuffer nstr=new StringBuffer(len);

		int p=len-str.length();
		
		for(int i=0;i<len;i++)
		{
			if(i<p)
				nstr.append(ch);
			else
				nstr.append(str.charAt(i-p));
		}
		return new String(nstr);
	}
	
	
	public static String leftPadb(String str,int len,char ch)
	{
		byte[] ret_byte_arr = new byte[len] ;

		//System.out.println( "�? ��str.length() : " + str.length()  ) ;
		//System.out.println( "�? ��str.getBytes().length : " + str.getBytes().length  ) ;
		
		byte[] tmp_byte_arr = str.getBytes() ;
		
		int p=len-tmp_byte_arr.length ;
		
		for(int i=0;i<len;i++)
		{
			if(i<p)
				ret_byte_arr[i] = (byte)ch ;
			else
				ret_byte_arr[i] = tmp_byte_arr[i-p] ;
		}
		return new String(ret_byte_arr);
	}
	
	
	public static String rightPad(String str, int len, char ch)
	{
		int p=len-str.length();
		String nstr="";
		
		for(int i=0;i<p;i++)
		{
			nstr=nstr+ch;	
		}
		nstr=str+nstr;
		
		return nstr ;
	}

	
   
   
   	/**
	 * Get Current Date
	 *
	 * Format :YYYYMMDD
	 *
	 * Return Format Date
	 */
	 
	public static String getDateFormat1 ( )
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
 		java.util.Date dateCurrent = new java.util.Date();
 		
 		String strCurrentDate = formatter.format( dateCurrent );
 		
 		return strCurrentDate;	
		
	}
	
	/**
	 * Get Current Date
	 *
	 * Format :YYYY-MM-DD
	 *
	 * Return Format Date
	 */
	 
	public static String getDateFormat2 ( )
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
 		java.util.Date dateCurrent = new java.util.Date();
 		
 		String strCurrentDate = formatter.format( dateCurrent );
 		
 		return strCurrentDate;	
		
	}
	
	
   	/**
	 * Get Current Date
	 *
	 * Format :YYYYMMDD
	 *
	 * Return Format Date
	 */
	 
	public static String getDate ( )
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
 		java.util.Date dateCurrent = new java.util.Date();
 		
 		String strCurrentDate = formatter.format( dateCurrent );
 		
 		return strCurrentDate;	
		
	}
	
	
	public static String getTime( )
	{
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
 		java.util.Date dateCurrent = new java.util.Date();
 		
 		String strCurrentTime = formatter.format( dateCurrent );
 		
 		return strCurrentTime;
	}
	
	
	public static String getDateAndTime( )
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
 		java.util.Date dateCurrent = new java.util.Date();
 		
 		String strCurrentTime = formatter.format( dateCurrent );
 		
 		return strCurrentTime;
	}
	
	
	public static String getDateAndTime2( )
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		java.util.Date dateCurrent = new java.util.Date();
 		
 		String strCurrentTime = formatter.format( dateCurrent );
 		
 		return strCurrentTime;
	}



	/*************************************************************
	 * function:	add bar to a card no. , such as if the card no. 
	 * 				is "9566123456789000" , the return string is 
	 * 				"9566-1234-5678-9000"
	 * input	:	card no. as "9566123456789000"
	 * output	:	card no. as "9566-1234-5678-9000"
	 * note		:	scottie xu
	 *********************************************************/
	public static String addBarToCardNo ( String cardNo )
	{
		String retStr = "" ;
		
		if( cardNo == null || cardNo.length() < 12 )
		{
			return cardNo ;
		}
		
		retStr = cardNo.substring(0,4) + "-" 
					+ cardNo.substring(4,8) + "-" 
					+ cardNo.substring(8,12) + "-" 
					+ cardNo.substring(12) ;
		
 		return retStr ;	
		
	}
	
	
	/*************************************************************
	 * function:	add bar to a date string , such as if the date 
	 * 				string is "20060912" , the return string is 
	 * 				"2006-09-12"
	 * input	:	date string as "20060912"
	 * output	:	date string as "2006-09-12"
	 * note		:	scottie xu
	 *********************************************************/
	public static String addBarToDateString ( String dateStr )
	{
		String retDateStr = "" ;
		
		if( dateStr == null || dateStr.length() != 8 )
		{
			return dateStr ;
		}
		
		retDateStr = dateStr.substring(0,4) + "-" 
					+ dateStr.substring(4,6) + "-" 
					+ dateStr.substring(6) ;
		
 		return retDateStr ;	
		
	}

	/*************************************************************
	 * function:	add Chinese to a date string , such as if the date 
	 * 				string is "20060912" , the return string is 
	 * 				"2006��09��12��"
	 * input	:	date string as "20060912"
	 * output	:	date string as "2006��09��12��"
	 * note		:	man wu
	 *********************************************************/
	public static String addChineseToDateString ( String dateStr )
	{
		String retDateStr = "" ;
		
		if( dateStr == null || dateStr.length() != 8 )
		{
			return dateStr ;
		}
		
		retDateStr = dateStr.substring(0,4) + "��" 
					+ dateStr.substring(4,6) + "��" 
					+ dateStr.substring(6)  + "��";
		
 		return retDateStr ;	
		
	}

	/*************************************************************
	 * function:	add bar to a date string , such as if the date 
	 * 				string is "090807" , the return string is 
	 * 				"09ʱ08��07��"
	 * input	:	date string as "090807"
	 * output	:	date string as "09ʱ08��07��"
	 * note		:	scottie xu
	 *********************************************************/
	public static String addChineseToTimeString ( String timeStr )
	{
		String retDateStr = "" ;
		
		if( timeStr == null || timeStr.length() != 6 )
		{
			return timeStr ;
		}
		
		retDateStr = timeStr.substring(0,2) + "ʱ" 
					+ timeStr.substring(2,4) + "��" 
					+ timeStr.substring(4) + "��" ;
		
 		return retDateStr ;	
		
	}

	
	/*************************************************************
	 * function:	add bar to a date string , such as if the date 
	 * 				string is "090807" , the return string is 
	 * 				"09:08:07"
	 * input	:	date string as "090807"
	 * output	:	date string as "09:08:07"
	 * note		:	scottie xu
	 *********************************************************/
	public static String addColonToTimeString ( String timeStr )
	{
		String retDateStr = "" ;
		
		if( timeStr == null || timeStr.length() != 6 )
		{
			return timeStr ;
		}
		
		retDateStr = timeStr.substring(0,2) + ":" 
					+ timeStr.substring(2,4) + ":" 
					+ timeStr.substring(4) ;
		
 		return retDateStr ;	
		
	}
	

	
	/*************************************************************
	 * function:	add bar to a date string , such as if the date 
	 * 				string is "20060912090807" , the return string is 
	 * 				"2006-09-12 09:08:07"
	 * input	:	date string as "20060912090807"
	 * output	:	date string as "2006-09-12 09:08:07"
	 * note		:	scottie xu
	 *********************************************************/
	public static String addBarAndColonToDateString ( String dateStr )
	{
		String retDateStr = "" ;
		
		if( dateStr == null || dateStr.length() != 14 )
		{
			return dateStr ;
		}
		
		retDateStr = dateStr.substring(0,4) + "-" 
					+ dateStr.substring(4,6) + "-" 
					+ dateStr.substring(6,8) + " "
					+ dateStr.substring(8,10) + ":"
					+ dateStr.substring(10,12) + ":"
					+ dateStr.substring(12) ;
		
 		return retDateStr ;	
		
	}
	

	
	/*************************************************************
	 * function:	remove the bar from a date string , such as if 
	 * 				the date string is "2006-09-12" , the return  
	 * 				string is "20060912" .
	 * input	:	date string as "20060912"
	 * output	:	date string as "2006-09-12"
	 * note		:	scottie xu
	 *********************************************************/
	public static String removeBarFromDateString ( String dateStr )
	{
		String retDateStr = "" ;
		
		if( dateStr == null || dateStr.length() != 10 )
		{
			return dateStr ;
		}
		
		retDateStr = dateStr.substring(0,4) + dateStr.substring(5,7) + dateStr.substring(8) ;
		
 		return retDateStr ;	
		
	}

	
	
	/*************************************************************
	 * function:	remove the bar from a date string , such as if 
	 * 				the date string is "09:08:07" , the return  
	 * 				string is "090807" .
	 * input	:	date string as "09:08:07"
	 * output	:	date string as "090807"
	 * note		:	scottie xu
	 *********************************************************/
	public static String removeColonFromTimeString ( String timeStr )
	{
		String retDateStr = "" ;
		
		if( timeStr == null || timeStr.length() != 8 )
		{
			return timeStr ;
		}
		
		retDateStr = timeStr.substring(0,2) + timeStr.substring(3,5) + timeStr.substring(6) ;
		
 		return retDateStr ;	
		
	}
	
	
	
	public static String genRandom(int len)
	{
		String result = "";
                Random ran = new Random();

                String sValue = String.valueOf(Math.abs(ran.nextLong()));
		if(sValue.length() >= len)
			result = sValue.substring(0,len);
		else
			result = rightPad(sValue, len, '0');

		return(result);
	}



	

	/*****************************
	 * ȡ��ĳ�µ�һ������
	 *
	 * Format :YYYY-MM-DD	 
	 * 
	 * @return Format Date
	 * lei.wang
	 *****************************/
	public static String getDateFormat4( )
	{
	
		Calendar now=Calendar.getInstance();
		int year=now.get(Calendar.YEAR);
		int mon=now.get(Calendar.MONTH);
		if( (mon+1) <10 )
		{		
			return year + "-0" + (mon+1) + "-" + "01" ;
		}
		else
		{
			return year + "-" + (mon+1) + "-" + "01" ;
		}
		

	}



}
