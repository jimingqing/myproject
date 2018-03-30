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
          	订单发货
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
										所属公司：
									</td>
									<td id="deptName" width="77%" bgcolor="#FFFFFF">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										所在城市/区县：
									</td>
									<td id="cityArea" width="77%" bgcolor="#FFFFFF">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										详细地址：
									</td>
									<td id="userAddr" width="77%" bgcolor="#FFFFFF">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										用户id：
									</td>
									<td id="userName" width="77%" bgcolor="#FFFFFF">
									</td>
								</tr>
																
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										订单编号：
									</td>
									<td id="ordId" width="77%" bgcolor="#FFFFFF">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										订单金额：
									</td>
									<td id="ordAmt" width="77%" bgcolor="#FFFFFF">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										订单状态：
									</td>
									<td id="ordState" width="77%" bgcolor="#FFFFFF">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										交易时间：
									</td>
									<td id="createTime" width="77%" bgcolor="#FFFFFF">
									</td>
								</tr>
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
<script type="text/javascript">
	$(function(){	
		qryData();
		initLogtCorp();
		
		$("#shipState").change(function(){
			if($(this).attr("checked") == "checked"){
				$("#logtCorpName").show();
				$("#logtCorpNo").show();
			}else{
				$("#logtCorpName").hide();
				$("#logtCorpNo").hide();
			}
		})
	})
	
	function qryData(){
		$("#nodata").hide();
		var param = {};
		param.ordId = "${param.ordId}";
		var succCallBack=function(data){
			$("#result").html("");
			if(data.retCode == "0000"){
				var ordLog = data.content;
				$("#deptName").text(ordLog.deptName);
				$("#cityArea").text(ordLog.userProv+ordLog.userCity+ordLog.userArea);
				$("#ordId").text(ordLog.ordId);
				$("#ordState").text(ordLog.ordState);
				$("#ordAmt").text(ordLog.ordAmtStr+"元");
				$("#createTime").text(ordLog.createTime);
				$("#userAddr").text(ordLog.userAddr);
				$("#userName").text(ordLog.acctName);
				if(ordLog.payUserCode!=null){
					$("#phoneNo").text(ordLog.payUserCode);
				}
				$("#acctNo").text("**** "+ordLog.acctNo);
				$("#transErrDesc").text(ordLog.bankRespMsg);
				
				if(ordLog.ordItems.length>0){
					var html = "";
					$.each(ordLog.ordItems, function(index, item){
						html += "<tr>";
						html += "<td align='center'>"+(index+1)+"</td>";
						html += "<td align='left'>"+item.itemName+"</td>";
						html += "<td align='center'>"+fmoney(item.itemAmt,2)+"</td>";
						html += "<td align='center'>"+item.itemNum+"</td>";
						html += "</tr>";
					})
					$("#data").html(html);
				}else{
					$("#data").html("<tr><td colspan='4' align='center'>无商品明细</td></tr>");
				}
			}
		};
		
		doAjaxRequest("${ctx}/console/ord_qryOrdInfoDetail.action", param, succCallBack);
	}
	
	function initLogtCorp(){
		$("#nodata").hide();
		var succCallBack=function(data){
			$("#result").html("");
			if(data.retCode == "0000"){
				var html = "<option value=''>请选择...</option>";
				$.each(data.content, function(index, item){
					html += "<option value='"+item.logtCode+"'>"+item.logtName+"</option>";
				})
				$("#logtCode").html(html);
			}
		};
		
		doAjaxRequest("${ctx}/console/ord_qryLogtCorp.action", {}, succCallBack);
	}
	
	function actSubmit(){
		showErrMsg();
		var ordId = "${param.ordId}";
		var shipState = $("#shipState").val();
		var logtCode = $("#logtCode").val();
		var shipNo = $("#shipNo").val();
		
		if($("#shipState").attr("checked") == "checked"){
			if(isEmpty(logtCode)){
				showErrMsg($("#logtCode"), "请选择快递公司");
				$("#logtCode").focus();
				return;
			}
			if(isEmpty(shipNo)){
				showErrMsg($("#shipNo"), "请填写快递单号");
				$("#shipNo").focus();
				return;
			}
		}else{
			showErrMsg($("#shipState"), "请选择发货");
			$("#shipState").focus();
			return;
		}

		if(confirm("确认提交发货信息！")){
			var param = {};
			param.ordId = ordId;
			param.shipState = shipState ;
			param.logtCode = logtCode ;
			param.shipNo = shipNo ;
			
			var succCallBack=function(data){
				if(data.retCode == "0000"){
					alert("处理成功");
				}else{
					alert("处理失败");
				}
			};
			
			var errCallBack = function(){
				alert("处理失败");
			};
			
			doAjaxRequest("${ctx}/console/ord_saveOrderShipInfo.action", param, succCallBack, errCallBack);
		}
	}
</script>
</html>
