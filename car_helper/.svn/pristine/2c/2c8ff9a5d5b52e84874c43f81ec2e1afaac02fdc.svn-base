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
				<table width="98%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="9" align="left" valign="top">
							<img src="${ctx}/ui/images/right_yellow_top_left.gif" width="9" height="25">
						</td>
						<td width="1019" align="left" valign="bottom"
							background="${ctx}/ui/images/right_yellow_top_background.gif"
							class="black_blod">
							<table width="500" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="5"></td>
									<td width="475" align="left" valign="bottom" class="black_blod" style="color:#fff;font-size:14px;font-weight:bold;">
										查询用户信息
									</td>
								</tr>
							</table>
						</td>
						<td width="10" align="left" valign="top">
							<img src="${ctx}/ui/images/right_yellow_top_right.gif" width="10" height="25">
						</td>
					</tr>
					<tr>
						<td align="right" valign="top" style="background-image:url(${ctx}/ui/images/right_yellow_left_line.gif); background-position:left; background-repeat:repeat-y;">&nbsp;</td>
						<td height="80" align="left" valign="top" bgcolor="#F0F0F0">

							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td align="left" valign="top" background="${ctx}/ui/images/right_yellow_left_jiao_back.gif">
										<img src="${ctx}/ui/images/right_yellow_left_jiao.gif" width="28" height="9">
									</td>
								</tr>
							</table>

							<table width="100%" border="0" cellpadding="0" cellspacing="1" class="blue_kuang" style="margin-top:2px;">

								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="10%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										商户：
									</td>
									<td width="20%" bgcolor="#FFFFFF">
										${userInfoVo.merName }
									</td>
									<td width="10%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										用户编号：
									</td>
									<td width="20%" bgcolor="#FFFFFF">
										<input type="text" id="userCode" name="userCode" class="txt_input" value="">	
									</td>
									<td width="10%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										状态：
									</td>
									<td width="20%" bgcolor="#FFFFFF">
										<select id="userState" name="userState">
											<option value="">全部</option>
											<option value="D">未激活</option>
											<option value="A">待审核</option>
											<option value="N">正常</option>
											<option value="C">关闭</option>
											<option value="L">锁定</option>
										</select>
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="10%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										手机号码：
									</td>
									<td width="20%" bgcolor="#FFFFFF">
										<input type="text" id="phoneNo" name="phoneNo" class="txt_input" value="">
									</td>
									<td width="10%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										用户类型：
									</td>
									<td width="20%" bgcolor="#FFFFFF">
										<select id="userType" name="userType">
											<option value="">全部</option>
											<option value="R">收款用户</option>
											<option value="P">付款用户</option>
										</select>
									</td>
									<td width="10%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										
									</td>
									<td width="20%" bgcolor="#FFFFFF">
										
									</td>
								</tr>

								<tr align="center" bgcolor="#FFFFFF" class="black_text_k5">
									<td height="42" colspan="6" align="center">
										<input type="button" value="查  询" class="btn2" onclick="actsubmit()">
									</td>
								</tr>
							</table>
						</td>
						<td align="left" valign="top"
							style="background-image:url(${ctx}/ui/images/right_yellow_right_line.gif); background-position:left; background-repeat:repeat-y;">&nbsp;</td>
					</tr>
					<tr>
						<td align="left" valign="bottom" background="${ctx}/ui/images/right_yellow_bottom_background.gif">
							<img src="${ctx}/ui/images/right_yellow_bottom_left.gif" width="7" height="6">
						</td>
						<td background="${ctx}/ui/images/right_yellow_bottom_background.gif"></td>
						<td align="center" valign="top" background="${ctx}/ui/images/right_yellow_bottom_background.gif">
							<img src="${ctx}/ui/images/right_yellow_bottom_right.gif" width="7" height="6">
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<div style="height:10px; width:auto;padding:10px;">
		<table id="header" width="1200" border=0 cellspacing="1" cellpadding="0" bgcolor="#C0C9E0" class="List">
			<thead>
			<tr>
				<td width="30" height="45" class="div"></td>
				<td width="120" class="div">商户名称</td>
				<td width="100" class="div">用户编号</td>
				<td width="100" class="div">用户名称</td>
				<td width="70" class="div">手机号码</td>
				<td width="80" class="div">用户类型</td>
				<td width="60" class="div">账户余额</td>
				<td width="50" class="div">状态</td>
				<td width="120" class="div">注册时间</td>
				<td width="140" class="div">操作</td>
			</tr>
			<tr id="nodata" class="none">
				<td colspan="10" align="center" class="font14-bold">无数据</td>
			</tr>
			</thead>
			<tbody class="List" id="result">
			</tbody>
		</table>
		
		<div id="pager">
			<%@ include file="pager.jsp" %>
		</div>
		
	</div>
</body>

<script type="text/javascript">
	
	$(function(){
		qryData();
	})
	
	function actsubmit(){
		curpage = 1;
		qryData();
	}
	
	function initMerInfo(){
		var succCallBack=function(data){
			$("#merId").html("");
			if(data.retCode == "0000"){
				if(data.content.length > 0){
					var html = "<option value=''>全部</option>";
					$.each(data.content, function(index, item){
						html+="<option value='"+item.id+"'>"+item.merCode+" "+item.merName+"</option>";
					})
					$("#merId").html(html);
				}
				
			}
		};
		
		doAjaxRequest("${ctx}/console/mer_qryMerInfo.action", {}, succCallBack);
	}
	
	function qryData(){
		$("#nodata").hide();
		var param = {};
		param.merId = $("#merId").val();
		param.userCode = $("#userCode").val();
		param.phoneNo = $("#phoneNo").val();
		param.state = $("#userState").val();
		param.type = $("#userType").val();
		param.curPage = curpage;
		var succCallBack=function(data){
			$("#result").html("");
			if(data.retCode == "0000"){
				initPager(data.content.pager);
				if(data.content.datalist.length > 0){
					var html = "";
					$.each(data.content.datalist, function(index, item){
						html += "<tr>";
						html += "<td align='center'><input type='radio' name='ids' value='"+item.userCode+"'></td>";
						html += "<td align='left'>"+item.merName+"</td>";
						html += "<td align='left'>"+item.userCode+"</td>";
						html += "<td align='left'>"+item.userName+"</td>";
						html += "<td align='left'>"+item.phoneNo+"</td>";
						if(item.userType == "R"){
							html += "<td align='center'>收款用户</td>";
						}else{
							html += "<td align='center'>付款用户</td>";
						}
						html += "<td align='right'>"+item.acctBalStr+"</td>";
						if(item.userState == "L"){
							html += "<td align='center'>锁定</td>";
						}else if(item.userState == "N"){
							html += "<td align='center'>正常</td>";
						}else if(item.userState == "C"){
							html += "<td align='center'>关闭</td>";
						}else if(item.userState == "D"){
							html += "<td align='center'>未激活</td>";
						}else if(item.userState == "A"){
							html += "<td align='center'>待审核</td>";
						}
						if(item.openTime == null){
							html += "<td align='left'></td>";
						}else{
							html += "<td align='center'>"+item.openTime.replace("T", " ")+"</td>";
						}
						if(item.userState == "A"){
							html += "<td align='left'><a href='${ctx}/admin/qryUserInfoDetailPage.action?userId="+item.id+"'>详情</a> | <a href='${ctx}/admin/auditUserInfoDetailPage.action?userId="+item.id+"'>用户审核</a> | <a href='${ctx}/admin/editUserInfoPage.action?userId="+item.id+"'>修改</a> | <a href='javascript:;' onclick='resetPwd(\""+item.id+"\")'>重置密码</a> | <a href='${ctx}/admin/editUserAcctBalPage.action?userId="+item.id+"'>修改余额</a></td>";
						}else{
							html += "<td align='left'><a href='${ctx}/admin/qryUserInfoDetailPage.action?userId="+item.id+"'>详情</a> | <a href='${ctx}/admin/editUserInfoPage.action?userId="+item.id+"'>修改</a> | <a href='javascript:;' onclick='resetPwd(\""+item.id+"\")'>重置密码</a> | <a href='${ctx}/admin/editUserAcctBalPage.action?userId="+item.id+"'>修改余额</a></td>";
						}
						
						html += "</tr>";
					})
					$("#result").html(html);
				}else{
					$("#nodata").show();
				}
			}else{
				$("#nodata").show();
			}
		};
		
		doAjaxRequest("${ctx}/console/usr_qryUserInfo.action", param, succCallBack);
	}
	
	function resetPwd(userid){
		if(confirm("确认重置用户密码")){
			var param = {};
			param.userId = userid;
			var succCallBack=function(data){
				if(data.retCode == "0000"){
					alert("重置用户密码成功");
				}else{
					alert("重置用户密码失败");
				}
			};
			doAjaxRequest("${ctx}/console/usr_resetUserPwd.action", param, succCallBack);
		}
	}
	
	function editAcctBal(userid){
		
	}
</script>
</html>
