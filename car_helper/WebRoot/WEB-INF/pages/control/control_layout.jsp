<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/import.jsp"%>
<html>
<head>
<title>易收付商务管理控台</title>
<%@ include file="/include/main_resources.jsp"%>
</head>

<body leftmargin="0" topmargin="0">


<table border="0" cellspacing="0" cellpadding="0" width="100%" height="100%">
<tr>
	<td valign="top" style="width:100%">
	
<!--查询区域外框开始-->
<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="9" align="left" valign="top"><img src="${ctx}/ui/images/right_yellow_top_left.gif" width="9" height="25"></td>
    <td width="1019" align="left" valign="bottom" background="${ctx}/ui/images/right_yellow_top_background.gif" class="black_blod">
	
	<!--查询标题开始-->
      <table width="500" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="25">&nbsp;</td>
          <td width="475" align="left" valign="bottom" class="black_blod"><tiles:insert attribute="formTitle" name="formTitle" ignore="true"/></td>
        </tr>
      </table>
	<!--查询标题结束-->
	  
	  </td>
    <td width="10" align="right" valign="top"><img src="${ctx}/ui/images/right_yellow_top_right.gif" width="10" height="25"></td>
  </tr>
  <tr>
    <td align="right" valign="top" style="background-image:url(${ctx}/ui/images/right_yellow_left_line.gif); background-position:left; background-repeat:repeat-y;">&nbsp;</td>
    <td height="80" align="left" valign="top" bgcolor="#F0F0F0">

	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="left" valign="top" background="${ctx}/ui/images/right_yellow_left_jiao_back.gif"><img src="${ctx}/ui/images/right_yellow_left_jiao.gif" width="28" height="9"></td>
      </tr>
    </table>
	
	   <!--查询区域表格开始-->

  <table width="100%"  border="0" cellpadding="0" cellspacing="1" class="blue_kuang" style="margin-top:7px;">
  
      <form action="perform2705.do" method="post" target="rightdown">
    
  
   
               <tr align="left" valign="bottom" class="black_text_k5"> 
                  <td width="23%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">代理人：</td>
                  <td width="77%" bgcolor="#FFFFFF"><table width="100%"  border="0" cellpadding="0" cellspacing="2" class="black_text">
                      <tr align="left"> 
                        <td>
                           
                        </td>
                      </tr>
                    </table></td>
                </tr>
                
                <tr align="left" valign="bottom" class="black_text_k5"> 
                  <td width="23%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">币种：</td>
                  <td width="77%" bgcolor="#FFFFFF"><table width="100%"  border="0" cellpadding="0" cellspacing="2" class="black_text">
                      <tr align="left"> 
                        <td>
                           
                        </td>
                      </tr>
                    </table></td>
                </tr>
                
               <tr align="left" valign="bottom" class="black_text_k5"> 
                  <td width="23%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">兑换币种：</td>
                  <td width="77%" bgcolor="#FFFFFF"><table width="100%"  border="0" cellpadding="0" cellspacing="2" class="black_text">
                      <tr align="left"> 
                        <td>

                        </td>
                      </tr>
                    </table></td>
                </tr>
                
                
                
                <tr align="left" valign="bottom" class="black_text_k5"> 
                 <td height="25" align="right" bgcolor="D9DFEE">日期：</td>
                 <td bgcolor="#FFFFFF"><table border="0" cellpadding="0" cellspacing="2" class="black_text">
                  <tr align="left">
                    <td>
                      <input name="rateDate" type="text" size="11">
                      <input type="button" name="Submit2" class="date" value="" onClick="">
                    </td>
                    
                  </tr>
                </table></td>
                </tr>
                
              

             <tr align="center" bgcolor="#FFFFFF" class="black_text_k5"> 
                  <td height="42" colspan="2" align="center"><table border="0" cellpadding="3" cellspacing="4">
                      <tr> 
                        <td align="right">
                          <input type="button" value="查询" length="60" onclick="perform2705();" >
                          <button type="button">查询</button>
                        </td>
                        
                      </tr>
                    </table></td>
             </tr>
    
         </form>
  </table>

  <!--查询区域表格结束-->

    </td>
    <td align="left" valign="top" style="background-image:url(${ctx}/ui/images/right_yellow_right_line.gif); background-position:right; background-repeat:repeat-y;">&nbsp;</td>
  </tr>
  <tr>
    <td align="left" valign="bottom" background="${ctx}/ui/images/right_yellow_bottom_background.gif"><img src="${ctx}/ui/images/right_yellow_bottom_left.gif" width="7" height="6"></td>
    <td background="${ctx}/ui/images/right_yellow_bottom_background.gif"></td>
    <td align="right" valign="top" background="${ctx}/ui/images/right_yellow_bottom_background.gif"><img src="${ctx}/ui/images/right_yellow_bottom_right.gif" width="7" height="6"></td>
  </tr>
</table>

<!--查询区域外框开始结束-->


	</td>
</tr>
</table>


</body>
</html>
