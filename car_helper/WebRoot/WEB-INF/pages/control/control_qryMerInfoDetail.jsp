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
		<div class="tab_title">商户基本信息</div>
		<table id="header" width="100%" border=0 cellspacing="1" cellpadding="0" class="blue_kuang">
			
			<tr>
				<td width="15%" align="right" class="title">商户号：</td>
				<td width="35%" id="merCode"></td>
				<td width="15%" align="right" class="title">商户名称：</td>
				<td width="35%" id="merName"></td>
			</tr>
			<tr>
				<td width="15%" align="right" class="title">地区：</td>
				<td width="35%" id="merArea"></td>
				<td width="15%" align="right" class="title">详细地址：</td>
				<td width="35%" id="merAddr"></td>
			</tr>
			<tr>
				<td width="15%" align="right" class="title">工商登记号：</td>
				<td width="35%" id="regCode"></td>
				<td width="15%" align="right" class="title">组织机构代码：</td>
				<td width="35%" id="corpCode"></td>
			</tr>
			<!--  
			<tr>
				<td width="15%" align="right" class="title">银联商户号：</td>
				<td width="35%" id="unMerId"></td>
				<td width="15%" align="right" class="title">邮编：</td>
				<td width="35%" id="merZip"></td>
			</tr>
			
			<tr>
				<td width="15%" align="right" class="title">开户银行：</td>
				<td width="35%" id="merBankName"></td>
				<td width="15%" align="right" class="title">开户银行账号：</td>
				<td width="35%" id="merBankAcct"></td>
			</tr>
			<tr>
				<td width="15%" align="right" class="title">账户日交易次数：</td>
				<td width="35%" id="dayTransCnt"></td>
				<td width="15%" align="right" class="title">单笔限制金额：</td>
				<td width="35%" id="oneLimitAmt"></td>
			</tr>
			<tr>
				<td width="15%" align="right" class="title">总计限制金额：</td>
				<td width="35%" id="totalLimitAmt"></td>
				<td width="15%" align="right" class="title">是否实时代扣：</td>
				<td width="35%" id="isRealWh"></td>
			</tr>
			<tr>
				<td width="15%" align="right" class="title">是否发送短信：</td>
				<td width="35%" id="isSendSms"></td>
				<td width="15%" align="right" class="title">商户状态：</td>
				<td width="35%" id="merState"></td>
			</tr>
			-->
		</table>
		<br/>
		
		<div class="tab_title">商户联系人信息</div>
		<table id="header" width="100%" border=0 cellspacing="1" cellpadding="0" class="blue_kuang">
			
			<tr>
				<td width="15%" align="right" class="title">联系人员姓名：</td>
				<td width="35%" id="merContName"></td>
				<td width="15%" align="right" class="title">联系人角色：</td>
				<td width="35%" id="merContRole"></td>
			</tr>
			
			<tr>
				<td width="15%" align="right" class="title">联系人员手机：</td>
				<td width="35%" id="merContMp"></td>
				<td width="15%" align="right" class="title">联系人员电话：</td>
				<td width="35%" id="merContTel"></td>
			</tr>
			
			<tr>
				<td width="15%" align="right" class="title">联系人员 Email：</td>
				<td width="35%" id="merContEmail"></td>
				<td width="15%" align="right" class="title"></td>
				<td width="35%"></td>
			</tr>
		</table>
		<p style="width:100%;text-align:center;">
		<input type="button" value="返  回" class="btn2" onclick="javascript:history.go(-1);">
		</p>
	</div>
</body>

<script type="text/javascript">
	
	$(function(){
		qryData();
	})
	
	function actsubmit(){
		qryData();
	}
	
	function qryData(){
		$("#nodata").hide();
		var param = {};
		param.merId = "${param.merId}";
		var succCallBack=function(data){
			$("#result").html("");
			if(data.retCode == "0000"){
				var merInfo = data.content;
				$("#merCode").text(merInfo.merCode);
				$("#merName").text(merInfo.merName);
				$("#merCode").text(merInfo.merCode);
				$("#merArea").text(merInfo.merProv+" "+merInfo.merArea);
				$("#merAddr").text(merInfo.merAddr);
				$("#regCode").text(merInfo.regCode);
				$("#corpCode").text(merInfo.corpCode);
				$("#unMerId").text(merInfo.unMerId);
				$("#merZip").text(merInfo.merZip);
				if(merInfo.merState == "N"){
					$("#merState").text("正常");
				}else if(merInfo.merState == "C"){
					$("#merState").text("关闭");
				}else if(merInfo.merState == "A"){
					$("#merState").text("待审核");
				}
				$("#merBankName").text(merInfo.merBankName);
				$("#merBankAcct").text(merInfo.merBankAcct);
				if(merInfo.dayTransCnt == 0){
					$("#dayTransCnt").text("不限");
				}else{
					$("#dayTransCnt").text(merInfo.dayTransCnt);
				}
				if(merInfo.oneLimitAmt == 0){
					$("#oneLimitAmt").text("不限");
				}else{
					$("#oneLimitAmt").text(merInfo.oneLimitAmt);
				}
				if(merInfo.totalLimitAmt == 0){
					$("#totalLimitAmt").text("不限");
				}else{
					$("#totalLimitAmt").text(merInfo.totalLimitAmt);
				}
				if(merInfo.isRealWh == "Y"){
					$("#isRealWh").text("实时代扣");
				}else{
					$("#isRealWh").text("非实时代扣");
				}
				if(merInfo.isSendSms == "Y"){
					$("#isSendSms").text("发送短信");
				}else{
					$("#isSendSms").text("不发送短信");
				}
				$("#merContName").text(merInfo.merContName);
				if(merInfo.merContRole == "F"){
					$("#merContRole").text("财务");
				}else if(merInfo.merContRole == "T"){
					$("#merContRole").text("技术");
				}else if(merInfo.merContRole == "O"){
					$("#merContRole").text("运营");
				}else if(merInfo.merContRole == "M"){
					$("#merContRole").text("市场");
				}else if(merInfo.merContRole == "S"){
					$("#merContRole").text("销售");
				}
				$("#merContMp").text(merInfo.merContMp);
				$("#merContTel").text(merInfo.merContTel);
				$("#merContEmail").text(merInfo.merContEmail);
			}
		};
		
		doAjaxRequest("${ctx}/console/mer_qryMerInfoDetail.action", param, succCallBack);
	}
	
</script>
</html>
