<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/import.jsp"%>
<html>
<head>
<title>微信营销平台</title>
<%@ include file="/include/main_resources.jsp"%>
</head>

<body leftmargin="0" topmargin="0">

<table border="0" cellspacing="0" cellpadding="0" width="100%">
<tr>
	<td valign="top" style="width:100%">
	
<!--查询区域外框开始-->
<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="9" align="left" valign="top"><img src="${ctx}/skin/ui/images/right_yellow_top_left.gif" width="9" height="25"></td>
    <td width="1019" align="left" valign="bottom" background="${ctx}/skin/ui/images/right_yellow_top_background.gif" class="black_blod">
	
	<!--查询标题开始-->
      <table width="500" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="25">&nbsp;</td>
          <td width="475" align="left" valign="bottom" class="black_blod" style="color:#fff;font-size:14px;font-weight:bold;">
          	操作员添加
          </td>
        </tr>
      </table>
	<!--查询标题结束-->
	  
	  </td>
    <td width="10" align="right" valign="top"><img src="${ctx}/skin/ui/images/right_yellow_top_right.gif" width="10" height="25"></td>
  </tr>
  <tr>
    <td align="right" valign="top" style="background-image:url(${ctx}/skin/ui/images/right_yellow_left_line.gif); background-position:left; background-repeat:repeat-y;">&nbsp;</td>
    <td height="80" align="left" valign="top" bgcolor="#F0F0F0">

	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="left" valign="top" background="${ctx}/skin/ui/images/right_yellow_left_jiao_back.gif"><img src="${ctx}/skin/ui/images/right_yellow_left_jiao.gif" width="28" height="9"></td>
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
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>操作员号：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="operCode" id="operCode" class="txt_input" maxlength="20" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>登录密码：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="password" name="operPwd" id="operPwd" class="txt_input" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>确认登录密码：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="password" name="confirmOperPwd" id="confirmOperPwd" class="txt_input" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										操作员姓名：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="operName" id="operName" class="txt_input" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										操作员电话：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="operTel" id="operTel" class="txt_input" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										操作员手机：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="operMp" id="operMp" class="txt_input" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										操作员Email：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="operEmail" id="operEmail" class="txt_input" size="30" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										是否管理员：
									</td>
									<td width="77%" bgcolor="#FFFFFF" style="padding:3px;">
										<label><input type="radio" value="Y" name="isAdmin">管理员</label>&nbsp;&nbsp;&nbsp;&nbsp;
										<label><input type="radio" value="N" name="isAdmin" checked="checked">非管理员</label>
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										状态标志：
									</td>
									<td width="77%" bgcolor="#FFFFFF" style="padding:3px;">
										<label><input type="radio" value="N" name="operState">正常</label>&nbsp;&nbsp;&nbsp;&nbsp;
										<label><input type="radio" value="C" name="operState" checked="checked">关闭</label>
									</td>
								</tr>
								
							<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										权限组：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select name="grpId" id="grpId">
											<option value="00">管理组</option>
												<%--<option value="01">财务经办组</option>
												<option value="02">财务审核组</option>
												<option value="03">运行客户组</option>
										--%></select>
									</td>
								</tr>
								
								<tr align="center" bgcolor="#FFFFFF" class="black_text_k5">
									<td height="42" colspan="2" align="center">
										<input type="button" value="提  交" class="btn2" onclick="actSubmit();">
									</td>
								</tr>
							</table>
<!--查询区域表格结束-->

    </td>
    <td align="left" valign="top" style="background-image:url(${ctx}/skin/ui/images/right_yellow_right_line.gif); background-position:right; background-repeat:repeat-y;">&nbsp;</td>
  </tr>
  <tr>
    <td align="left" valign="bottom" background="${ctx}/skin/ui/images/right_yellow_bottom_background.gif"><img src="${ctx}/skin/ui/images/right_yellow_bottom_left.gif" width="7" height="6"></td>
    <td background="${ctx}/skin/ui/images/right_yellow_bottom_background.gif"></td>
    <td align="right" valign="top" background="${ctx}/skin/ui/images/right_yellow_bottom_background.gif"><img src="${ctx}/skin/ui/images/right_yellow_bottom_right.gif" width="7" height="6"></td>
  </tr>
</table>

<!--查询区域外框开始结束-->


	</td>
</tr>
</table>

</body>
<script type="text/javascript">

	$(function(){	
		initMerInfo();
		initMerGrpAuthInfo();
		$("#operCode").blur(function(){
			showErrMsg();
			showOkMsg();
			<c:if test="${userInfoVo.merCode eq '880001' }">
				if($("#merId").val()==""){
					showErrMsg($("#operCode"), "请选择商户");
					return;
				}
			</c:if>
			var param = {};
			param.operCode=$(this).val();
			<c:if test="${userInfoVo.merCode eq '880001' }">
			param.merId=$("#merId").val();
			</c:if>
			<c:if test="${userInfoVo.merCode ne '880001' }">
			param.merId="${userInfoVo.merId }";
			</c:if>
			var succCallBack=function(data){
				if(data.retCode == "1001"){
					showErrMsg($("#operCode"), "操作员号错误，请重新输入");		
				}else if(data.retCode == "1301"){
					showErrMsg($("#operCode"), "操作员已存在，请重新输入");		
				}else{
					showOkMsg($("#operCode"),"");
				}
			};
			
			doAjaxRequest("${ctx}/console/mer_checkOperCode.action", param, succCallBack);
		});
	})
	
	function initMerGrpAuthInfo(){
		var succCallBack=function(data){
			if(data.retCode == "0000"){
				if(data.content.length > 0){
					var html = "";
					$.each(data.content, function(index, item){
						html+="<option value='"+item.id+"'>"+item.grpName+"</option>";
					})
					$("#grpId").append(html);
				}
			}
		};
		
		doAjaxRequest("${ctx}/console/au_qryMerGrpAuthInfo.action", {}, succCallBack);
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
	
	function actSubmit(){
		showErrMsg();
		var operCode = $("#operCode").val();
		var operPwd = $("#operPwd").val();
		var confirmOperPwd = $("#confirmOperPwd").val();
		var operName = $("#operName").val();
		var operTel = $("#operTel").val();
		var operMp = $("#operMp").val();
		var operEmail = $("#operEmail").val();
		var operState = $("input[name='operState']:checked").val();
		var grpId = $("#grpId").val();
		var isAdmin = $("input[name='isAdmin']:checked").val();
		
		if(isEmpty(operCode)){
			showErrMsg($("#operCode"), "请输入操作员");
			$("#operCode").focus();
			return;
		}
		if(operCode.length > 20){
			showErrMsg($("#operCode"), "请正确输入操作员号");
			$("#operCode").focus();
			return;
		}
		
		if(isEmpty(operPwd)){
			showErrMsg($("#operPwd"), "请输入操作员登录密码");
			$("#operPwd").focus();
			return;
		}
		if(isEmpty(confirmOperPwd)){
			showErrMsg($("#confirmOperPwd"), "请输入确认登录密码");
			$("#confirmOperPwd").focus();
			return;
		}
		if(confirmOperPwd != operPwd){
			showErrMsg($("#confirmOperPwd"), "确认登录密码与登录密码不一致，请重新输入");
			$("#confirmOperPwd").focus();
			return;
		}
		if(isEmpty(grpId)){
			showErrMsg($("#grpId"), "请选择权限组");
			$("#grpId").focus();
			return;
		}
		
		if(confirm("确认添加操作员信息！")){
			var param = {};
			if( $("#merId").val()==null || $("#merId").val()=="")
			{
				param.merId = $("#merId1").val();
			}
			else
			{
	        	param.merId =  $("#merId").val();
			}
			
			param.operCode =  operCode ;
			param.operPwd =  operPwd ;
			param.operName =  operName ;
			param.operTel =  operTel ;
			param.operMp =  operMp ;
			param.operEmail =  operEmail ;
			param.operState =  operState ;
			param.grpId =  grpId ;
			param.isAdmin = isAdmin;
			
			
			var succCallBack=function(data){
				if(data.retCode == "0000"){
					alert("新增操作员信息成功");
				}else{
					alert("新增操作员信息失败");
				}
			};
			
			var errCallBack = function(){
				alert("新增操作员信息失败");
			};
			
			doAjaxRequest("${ctx}/console/mer_addMerOperInfo.action", param, succCallBack, errCallBack);
		}
	}
</script>
</html>
