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
          <td width="475" align="left" valign="bottom" class="black_blod" style="color:#fff;font-size:14px;font-weight:bold;" >
          	重置操作员密码
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
	<input  type="hidden"   id="merId"    value="${userInfoVo.merId}"></input>
	   <!--查询区域表格开始-->

  <table width="100%"  border="0" cellpadding="0" cellspacing="1" class="blue_kuang" style="margin-top:7px;">
							
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										商户：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										${userInfoVo.merName }
									</td>
								</tr>
								
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										操作员号：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										${userInfoVo.operCode }
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										操作员密码：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="password" name="operPwd" id="operPwd" class="txt_input" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										确认密码：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="password" name="confirmOperPwd" id="confirmOperPwd" class="txt_input" value="">
									</td>
								</tr>
								
								<tr align="center" bgcolor="#FFFFFF" class="black_text_k5">
									<td height="42" colspan="2" align="center">
										<input type="button" value="提  交" class="btn2" onclick="actSubmit('edit');">
										<input type="button" value="密码重置" class="btn2" onclick="actSubmit('reset');" style="display: none;">
										<input type="button" value="返  回" class="btn2" onclick="javascript:history.go(-1); ">
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

	function actSubmit(type){
		showErrMsg();
		var operId = "${param.operId}";
		var operPwd = $("#operPwd").val();
		var confirmOperPwd = $("#confirmOperPwd").val();
		
		if(type=="edit"){
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
		}
		if(type=="edit"){
			if(confirm("确认修改操作员密码！")){
				var param = {};
				param.operId = "${param.operId}" ;
				param.operPwd = operPwd ;
				param.type = type;
				param.merId = $("#merId").val();
				//alert(param.merId);
				var succCallBack=function(data){
					if(data.retCode == "0000"){
						alert("修改成功");
					}else{
						alert("修改失败");
					}
				};
				
				var errCallBack = function(){
					alert("修改失败");
				};
				
				doAjaxRequest("${ctx}/console/mer_editOperPwd.action", param, succCallBack, errCallBack);
			}
		}else{
			if(confirm("确认重置操作员密码！")){
				var param = {};
				param.operId = "${param.operId}" ;
				param.type = type;
				
				var succCallBack=function(data){
					if(data.retCode == "0000"){
						alert("重置密码成功，密码是："+data.content);
					}else{
						alert("重置密码失败");
					}
				};
				
				var errCallBack = function(){
					alert("重置密码失败");
				};
				
				doAjaxRequest("${ctx}/console/mer_editOperPwd.action", param, succCallBack, errCallBack);
			}
		}
	}
</script>
</html>
