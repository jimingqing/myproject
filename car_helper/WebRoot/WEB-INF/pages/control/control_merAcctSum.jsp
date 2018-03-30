<%@page import="com.yrtech.wx.capp.framework.core.util.DateOper"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/import.jsp"%>
<html>
<head>
<title>易收付商务管理控台</title>
<%@ include file="/include/main_resources.jsp"%>
</head>

<body leftmargin="0" topmargin="0">

<table border="0" cellspacing="0" cellpadding="0" width="100%">
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
          <td width="475" align="left" valign="bottom" class="black_blod" style="color:#fff;font-size:14px;font-weight:bold;">
          	商户报表
          </td>
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

								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										商户名称：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<c:if test="${userInfoVo.merCode eq '880001' }">
											<select id="merId"></select>
										</c:if>
										<c:if test="${userInfoVo.merCode ne '880001' }">
											<select id="merId1"  >
											    <option value="${userInfoVo.merId}">${userInfoVo.merName}</option>
											</select>
										</c:if>
									</td>
								</tr>
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="15%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										账务日期：
									</td>
									<td width="35%" bgcolor="#FFFFFF" colspan="3">
										<input name="startDate" id="startDate" type="text" class="txt_input" size="10" value="<%=DateOper.evalTime(DateOper.getYesterday(), "yyyy-MM-dd", "yyyy-MM-dd", "day", -2) %>" readonly="readonly" onClick="WdatePicker()">
										-
										<input name="endDate" id="endDate" type="text" class="txt_input" size="10" value="<%=DateOper.getYesterday()%>" readonly="readonly" onClick="WdatePicker()">
									</td>
								</tr>

								<tr align="center" bgcolor="#FFFFFF" class="black_text_k5">
									<td height="42" colspan="4" align="center">
										<input type="button" value="查  询" class="btn2" onclick="actsubmit()">
									</td>
								</tr>
							</table>
						</td>
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

	<div style="height:10px; width:auto;padding:10px;">
		<table id="header" width="auto" border=0 cellspacing="1" cellpadding="0" bgcolor="#C0C9E0" class="List">
			<thead>
			<tr>
			    <td width="80" class="div">商户id</td>
				<td width="80" class="div">交易时间</td>
				<td width="150" class="div">交易笔数</td>
				<td width="150" class="div">交易金额</td>
			</tr>
			<tr id="nodata" class="none">
				<td colspan="4" align="center" class="font14-bold">无数据</td>
			</tr>
			</thead>
			<tbody class="List" id="result">
			</tbody>
		</table>
		<br/>
		
	</div>
</body>

<script type="text/javascript">
	
	$(function(){
		initMerInfo();
		actsubmit();
		//doAjaxRequest("${ctx}/console/merReport.action", null, succCallBack1);
	})
	/*****
		var succCallBack1=function(data){
			$("#result").html("");
			if(data.retCode == "0000"){
              if(data.content!=null)
              {
				
				if(data.content.length > 0){
					var content = data.content;
					var html = "";
					/****
					$.each(data.content, function(index, item){
						html += "<tr>";
						html += "<td align='center'>"+item.acctDate.split("T")[0]+"</td>";
						html += "<td align='right'>"+item.transCnt+" 笔</td>";
						html += "<td align='right'>"+fmoney(item.transAmt,2)+" 元</td>";
						html += "<td align='right'>"+fmoney(item.liqAmt,2)+" 元</td>";
						html += "</tr>";
					})
					  for(var i = 0 ;i<content.length ; i++)
                      {
	                    	html += "<tr>";
	   						html += "<td align='center'>"+content[i][0]+"</td>";
	   						html += "<td align='right'>"+content[i][1]+" 笔</td>";
	   						html += "<td align='right'>"+content[i][2]+" 元</td>";
	   						html += "</tr>";
                      }
					$("#result").html(html);
				}else{
					$("#nodata").show();
				}
              }
              else
              {
            	  $("#nodata").show();
              }
				
			}
		};  **/
	
	function actsubmit(){
		qryData();
	}
	
	function qryData(){
		$("#nodata").hide();
		var param = {};
		if( $("#merId").val()==null ||  $("#merId").val()=="")
		{
			param.merId = $("#merId1").val();

			//alert( $("#merId1").val());
		}
		else
		{
        	param.merId =  $("#merId").val();
		}
		param.startDate = $("#startDate").val();
		param.endDate = $("#endDate").val();
		
		if(isEmpty(param.startDate)){
			showErrMsg($("#startDate"), "请选择开始日期");
			return;
		}
		if(isEmpty(param.endDate)){
			showErrMsg($("#endDate"), "请选择结束日期");
			return;
		}
		if(checkEndDate(param.startDate, param.endDate)){
			showErrMsg($("#endDate"), "结束日期不能早于开始日期");
			return;
		}
		
		var succCallBack=function(data){
			$("#result").html("");
			if(data.retCode == "0000"){
              if(data.content!=null)
              {
				
				if(data.content.length > 0){
					//var content = data.content;
					var html = "";
					
					$.each(data.content, function(index, item){
						html += "<tr>";
						html += "<td align='center'>"+item.merId+"</td>";
						html += "<td align='center'>"+item.acctDate.split("T")[0]+"</td>";
						html += "<td align='right'>"+item.transCnt+" 笔</td>";
						html += "<td align='right'>"+fmoney(item.transAmt,2)+" 元</td>";
						html += "</tr>";
					})
					/****
					  for(var i = 0 ;i<content.length ; i++)
                      {
	                    	html += "<tr>";
	   						html += "<td align='center'>"+content[i][0]+"</td>";
	   						html += "<td align='right'>"+content[i][1]+" 笔</td>";
	   						html += "<td align='right'>"+content[i][2]+" 元</td>";
	   						html += "</tr>";
                      })***/
					$("#result").html(html);
				}else{
					$("#nodata").show();
				}
              }
              else
              {
            	  $("#nodata").show();
              }
				
			}
		};
		
		doAjaxRequest("${ctx}/console/as_qryMerAcctSumLog.action", param, succCallBack);
	}


	function initMerInfo(){
		var succCallBack=function(data){
			$("#merId").html("");
			if(data.retCode == "0000"){
				if(data.content.length > 0){
					var html = "<option value=''>请选择商户...</option>";
					$.each(data.content, function(index, item){
						html+="<option value='"+item.id+"'>"+item.merCode+" "+item.merName+"</option>";
					})
					$("#merId").html(html);
				}
				
			}
		};
		
		doAjaxRequest("${ctx}/console/mer_qryMerInfo.action", {}, succCallBack);
	}
	
</script>
</html>
