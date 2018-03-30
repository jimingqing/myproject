<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/import.jsp"%>

<html>
<head>
<title>易收付商务管理控台</title>
<%@ include file="/include/main_resources.jsp"%>
</head>
<style>
.blue_kuang { border:1px; }
.blue_kuang td{ height:30px;background:#FFFFFF; padding-left:5px;font-size:12px; }
.blue_kuang .title { background:#D9DFEE; }
.border-none { border: 0; }
.tab_title { font-size:14px; font-weight:bold; padding:5px 10px;}
</style>
<body leftmargin="0" topmargin="0">

	<div style="height:10px; width:auto;padding:10px;">
		<div class="tab_title">订单明细</div>
		<table id="header" width="100%" border=0 cellspacing="1" cellpadding="0" class="blue_kuang">
			<tr>
				<td width="15%" align="right" class="title">订单编号：</td>
				<td width="35%" id="ordId"></td>
				<td width="15%" align="right" class="title">订单金额：</td>
				<td width="35%" id="ordAmt"></td>
			</tr>
			<tr>
				<td width="15%" align="right" class="title">提交时间：</td>
				<td width="35%" id="createTime"></td>
				<td width="15%" align="right" class="title">交易结果：</td>
				<td width="35%" id="ordState"></td>
			</tr>
			<tr>
				<td width="15%" align="right" class="title">银行卡流水号：</td>
				<td width="35%" id="bankSystemNum"></td>
			</tr>
		</table>
		<br/>
		<p style="width:100%;text-align:center;">
		<input type="button" value="返  回" class="btn2" onclick="javascript:history.go(-1);">
		</p>
	</div>
</body>

<script type="text/javascript">
	
	$(function(){
		qryData();
	})
	
	function qryData(){
		$("#nodata").hide();
		var param = {};
		param.ordId = "${param.ordId}";
		param.merId = "${param.merId}";
		//alert(param.ordId)
		var succCallBack=function(data){
			$("#result").html("");
			if(data.retCode == "0000"){
				var ordLog = data.content;
				$("#ordId").text(ordLog.ordId);
				$("#ordState").text(ordLog.ordState);
				$("#ordAmt").text(ordLog.ordAmt);
				$("#createTime").text(ordLog.bankDate);
		//	$("#bankSystemNum").text(ordLog.bankSystemNum);
				if(ordLog.payUserCode!=null){
					$("#payUserCode").text(ordLog.payUserCode);
				}
				$("#bankSystemNum").text(ordLog.bankSystemNum);
				
				if(ordLog.ordItems.length>0){
					var html = "";
					$.each(ordLog.ordItems, function(index, item){
						html += "<tr>";
						html += "<td align='center'>"+(index+1)+"</td>";
						html += "<td align='left'>"+item.itemName+"</td>";
						html += "<td align='center'>"+fmoney(item.itemAmt,2)+"</td>";
						html += "<td align='center'>"+item.itemNum+"</td>";
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
	
</script>
</html>
