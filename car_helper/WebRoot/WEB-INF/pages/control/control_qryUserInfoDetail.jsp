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
		<div class="tab_title">用户基本信息</div>
		<table id="header" width="100%" border=0 cellspacing="1" cellpadding="0" class="blue_kuang">
			
			<tr>
				<td width="15%" align="right" class="title">用户名称：</td>
				<td width="35%" id="userName"></td>
				<td width="15%" align="right" class="title">用户编号：</td>
				<td width="35%" id="userCode"></td>
			</tr>
			<tr>
				<td width="15%" align="right" class="title">性别：</td>
				<td width="35%" id="sex"></td>
				<td width="15%" align="right" class="title">电子邮箱：</td>
				<td width="35%" id="email"></td>
			</tr>
			<tr>
				<td width="15%" align="right" class="title">单位名称：</td>
				<td width="35%" id="deptName"></td>
				<td width="15%" align="right" class="title">职位：</td>
				<td width="35%" id="position"></td>
			</tr>
			<tr>
				<td width="15%" align="right" class="title">所在地区：</td>
				<td width="35%" id="userArea"></td>
				<td width="15%" align="right" class="title">详细地址：</td>
				<td width="35%" id="userAddr"></td>
			</tr>
			<tr>
				<td width="15%" align="right" class="title">用户邮编：</td>
				<td width="35%" id="userZip"></td>
				<td width="15%" align="right" class="title">注册日期：</td>
				<td width="35%" id="openTime"></td>
			</tr>
			<tr>
				<td width="15%" align="right" class="title">证件类型：</td>
				<td width="35%" id="certType"></td>
				<td width="15%" align="right" class="title">证件号码：</td>
				<td width="35%" id="certId"></td>
			</tr>
			<tr>
				<td width="15%" align="right" class="title">用户类型：</td>
				<td width="35%" id="userType"></td>
				<td width="15%" align="right" class="title">用户状态：</td>
				<td width="35%" id="userState"></td>
			</tr>
			
			<%--<tr>
				<td width="15%" align="right" class="title">账户余额：</td>
				<td width="35%" id="acctBal" colspan="3"></td>
			</tr>
			--%>
			<tr>
				<td width="15%" align="right" class="title">身份证照片：</td>
				<td width="35%" id="certPic" colspan="3"></td>
			</tr>
			
		</table>
		<br/>
		<%--<div class="tab_title">用户开通快捷支付银行卡信息</div>
		<table id="header" width="600" border=0 cellspacing="1" cellpadding="0" class="blue_kuang list">
			<thead>
				<tr>
					<td width="30" align="center" class="div">序号</td>
					<td align="center" class="div">银行名称</td>
					<td align="center" class="div">银行卡账号</td>
					<td align="center" class="div">开通时间</td>
					<td align="center" class="div">状态</td>
				</tr>
			</thead>
			<tbody id="userBankInfo"></tbody>
		</table>
		
		<p style="width:100%;text-align:center;">
		<input type="button" value="返  回" class="btn2" onclick="javascript:history.go(-1);">
		</p>
	</div>
--%></body>

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
		param.userId = "${param.userId}";
		var succCallBack=function(data){
			$("#result").html("");
			if(data.retCode == "0000"){
				var userInfo = data.content;
				$("#merCode").text(userInfo.merCode);
				$("#merName").text(userInfo.merName);
				$("#userCode").text(userInfo.userCode);
				$("#userName").text(userInfo.userName==null?"":userInfo.userName);
				$("#sex").text(userInfo.sex==null?"":userInfo.sex);
				$("#email").text(userInfo.email==null?"":userInfo.email);
				$("#deptName").text(userInfo.deptName==null?"":userInfo.deptName);
				$("#position").text(userInfo.position==null?"":userInfo.position);
				$("#userArea").append(userInfo.userProvName==null?"":userInfo.userProvName+" ");
				$("#userArea").append(userInfo.userCityName==null?"":userInfo.userCityName+" ");
				$("#userArea").append(userInfo.userAreaName==null?"":userInfo.userAreaName);
				$("#userAddr").text(userInfo.userAddr==null?"":userInfo.userAddr);
				$("#userZip").text(userInfo.userZip==null?"":userInfo.userZip);
				if(userInfo.openTime!=null){
					$("#openTime").text(userInfo.openTime.split("T")[0]);
				}
				if(userInfo.userState == "N"){
					$("#userState").text("正常");
				}else if(userInfo.userState == "C"){
					$("#userState").text("关闭");
				}else if(userInfo.userState == "L"){
					$("#userState").text("锁定");
				}else if(userInfo.userState == "D"){
					$("#userState").text("未激活");
				}else if(userInfo.userState == "A"){
					$("#userState").text("待审核");
				}else{
					$("#userState").text();
				}
				if(userInfo.certType == "01"){
					$("#certType").text("身份证");
				}else if(userInfo.certType == "02"){
					$("#certType").text("护照");
				}else if(userInfo.certType == "03"){
					$("#certType").text("军官证");
				}else if(userInfo.certType == "04"){
					$("#certType").text("回乡证");
				}else if(userInfo.certType == "05"){
					$("#certType").text("工商登记号");
				}else{
					$("#certType").text("其他");
				}
				$("#certId").text(userInfo.certId==null?"":userInfo.certId);
				if(userInfo.certPicPath != null){
					$("#certPic").html("<img src='${ctx}/mer_file/cert_images/"+userInfo.certPicPath+"' width='200'/>");
				}
				
				if(userInfo.userType == "P"){
					$("#userType").text("付款用户");
				}else if(userInfo.userState == "R"){
					$("#userType").text("收款用户");
				}
				$("#acctBal").text(userInfo.acctBalStr);
				if(userInfo.bankRels.length>0){
					var html = "";
					$.each(userInfo.bankRels, function(index, item){
						html += "<tr>";
						html += "<td align='center'>"+(index+1)+"</td>";
						html += "<td align='center'>"+item.bankName+"</td>";
						html += "<td align='center'> **** "+item.bankCardNo+"</td>";
						html += "<td align='center'>"+item.createTime.replace("T", " ")+"</td>";
						if(item.state=="N"){
							html += "<td align='center'>已绑定</td>";
						}else{
							html += "<td align='center'>未绑定</td>";
						}
						html += "</tr>";
						
					})
					$("#userBankInfo").html(html);
				}else{
					$("#userBankInfo").html("<tr><td colspan='5' align='center'>未开通快捷支付</td></tr>");
				}
				
			}
		};
		
		doAjaxRequest("${ctx}/console/usr_qryUserInfoDetail.action", param, succCallBack);
	}
	
</script>
</html>
