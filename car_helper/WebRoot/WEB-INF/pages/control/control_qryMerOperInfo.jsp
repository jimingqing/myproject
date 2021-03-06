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
          	操作员信息
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
								<tr>
									<td width="10%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										操作员号：
									</td>
									<td width="20%" bgcolor="#FFFFFF">
										<input type="text" id="operCode" name="operCode" class="txt_input" value="">	
									</td>
								</tr>
								<tr>
									<td width="10%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										状态：
									</td>
									<td width="20%" bgcolor="#FFFFFF">
										<select id="operState" name="operState">
											<option value="all">全部</option>
											<option value="N">正常</option>
											<option value="C">关闭</option>
											<option value="L">锁定</option>
										</select>
									</td>
								</tr>

								<tr align="center" bgcolor="#FFFFFF" class="black_text_k5">
									<td height="42" colspan="6" align="center">
										<input type="button" value="查  询" class="btn2" onclick="actsubmit()">
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

	<div style="height:10px; width:auto;padding:10px;">
		<table id="header" width="1200" border=0 cellspacing="1" cellpadding="0" bgcolor="#C0C9E0" class="List">
			<thead>
			<tr>
				<td width="30" height="45" class="div">序号</td>
				<td width="150" class="div">商户名称</td>
				<td width="80" class="div">操作员号</td>
				<td width="60" class="div">权限组</td>
				<td width="60" class="div">状态</td>
				<td width="120" class="div">创建时间</td>
				<td width="120" class="div">登录IP</td>
				<td width="120" class="div">登录时间</td>
				<td width="80" class="div">密码错误次数</td>
				<td width="120" class="div">操作</td>
			</tr>
			<tr id="nodata" class="none">
				<td colspan="10" align="center" class="font14-bold">无数据</td>
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
		qryData();
	})
	
	function actsubmit(){
		qryData();
	}
	
	function initMerInfo(){
		var succCallBack=function(data){
			$("#merId").html("");
			if(data.retCode == "0000"){
				if(data.content.length > 0){
					var html = "<option value='all'>全部</option>";
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
		//param.merId = $("#merId").val();
		if( $("#merId").val()==null ||  $("#merId").val()=="")
		{
			param.merId = $("#merId1").val();
		}
		else
		{
        	param.merId =  $("#merId").val();
		}
		param.operCode = $("#operCode").val();
		param.operState = $("#operState").val();
		
		var succCallBack=function(data){
			$("#result").html("");
			if(data.retCode == "0000"){
				if(data.content.length > 0){
					var html = "";
					$.each(data.content, function(index, item){
						html += "<tr>";
						html += "<td align='center'>"+(index+1)+"</td>";
						html += "<td align='left'>"+item.merName+"</td>";
						html += "<td align='left'>"+item.operCode+"</td>";
						if(item.isAdmin == "Y"){
							html += "<td align='center'>管理员</td>";
						}else{
							html += "<td align='center'>非管理员</td>";
						}
						if(item.operState == "L"){
							html += "<td align='center'>锁住</td>";
						}else if(item.operState == "N"){
							html += "<td align='center'>正常</td>";
						}else{
							html += "<td align='center'>关闭</td>";
						}
						html += "<td align='center'>"+item.createTime.replace("T", " ")+"</td>";
						if(isEmpty(item.logIp)){
							html += "<td align='center'></td>";
						}else{
							html += "<td align='center'>"+item.logIp+"</td>";
						}
						if(isEmpty(item.logTime)){
							html += "<td align='center'></td>";
						}else{
							html += "<td align='center'>"+item.logTime.replace("T", " ")+"</td>";
						}
						html += "<td align='center'>"+item.pwdErrCnt+"</td>";
						html += "<td align='left'><a href='${ctx}/admin/qryMerOperInfoDetailPage.action?operId="+item.id+"'>详情</a> | <a href='${ctx}/admin/editMerOperInfoPage.action?operId="+item.id+"'>修改</a> | <a href='${ctx}/admin/resetOperPwdPage.action?operId="+item.id+"'>重置密码</a></td>";
						html += "</tr>";
					})
					$("#result").html(html);
				}else{
					$("#nodata").show();
				}
				
			}
		};
		
		doAjaxRequest("${ctx}/console/mer_qryMerOperInfo.action", param, succCallBack);
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
