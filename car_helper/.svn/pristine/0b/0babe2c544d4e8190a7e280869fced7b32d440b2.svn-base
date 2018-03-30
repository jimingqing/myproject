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
          <td width="475" align="left" valign="bottom" class="black_blod" style="color:#fff;font-size:14px;font-weight:bold;">新增权限</td>
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
										权限级别：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select name="authLevel" id="authLevel">
											<option value="1">一级</option>
											<option value="2">二级</option>
											<option value="3">三级</option>
										</select>
									</td>
								</tr>
								
								<tr id="parent" align="left" valign="bottom" class="black_text_k5 none">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										父权限：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select name="parentId" id="parentId" >
										</select>
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>权限描述：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="authDesc" id="authDesc" class="txt_input" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>顺序编号：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="porder" id="porder" class="txt_input" value="" size="5" maxlength="10">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										状态标志：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select name="state" id="state">
											<option value="N">正常</option>
											<option value="C">关闭</option>
										</select>
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										是否为默认对操作员开放：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select name="isDefault" id="isDefault">
											<option value="Y">是</option>
											<option value="N">否</option>
										</select>
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										是否对商户开放：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select name="isToMer" id="isToMer">
											<option value="Y">是</option>
											<option value="N">否</option>
										</select>
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										是否作为功能显示在控台：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select name="isDisplay" id="isDisplay">
											<option value="Y">是</option>
											<option value="N">否</option>
										</select>
									</td>
								</tr>

								<tr align="left" valign="bottom" class="black_text_k5">
									<td height="30" align="right" bgcolor="D9DFEE">访问地址：</td>
									<td bgcolor="#FFFFFF">
										<input name="action" id="action" type="text" class="txt_input" size="50" value="">
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
		$("#authLevel").change(function(){ 
			var level = $(this).val();
			if(level == "1"){
				$("#parentId").html("");
				$("#parent").hide();
			}else{
				qryAuth(level);
				$("#parent").show();
			}
		})
	})
	
	function qryAuth(level){
		var param = {};
		param.authLevel = level-1;
		var succCallBack=function(data){
			$("#parentId").html("");
			if(data.retCode == "0000"){
				if(data.content.length > 0){
					var html = "<option value=''>请选择...</option>";
					$.each(data.content, function(index, item){
						html+="<option value='"+item.id+"'>"+item.authDesc+"</option>";
					})
					$("#parentId").html(html);
				}
				
			}
		};
		
		doAjaxRequest("${ctx}/console/au_qryParentAuth.action", param, succCallBack);
	}
	
	function showErrMsg(obj, msg){
		$.each($("table .errMsg"),function(index, item){
			$(item).remove();
		})
		if(obj!=null && msg!=null){
			$(obj).parent().append("<span class='errMsg'>"+msg+"</span>");
		}
		
	}
	
	function actSubmit(){
		showErrMsg();
		var authLevel = $("#authLevel").val();
		var parentId = $("#parentId").val();
		var authDesc = $("#authDesc").val();
		var porder = $("#porder").val();
		var state = $("#state").val();
		var isDefault = $("#isDefault").val();
		var isToMer = $("#isToMer").val();
		var isDisplay = $("#isDisplay").val();
		var action = $("#action").val();
		
		if((authLevel==2 || authLevel==3) && isEmpty(parentId)){
			showErrMsg($("#parentId"), "请选择父权限");
			return;
		}
		if(isEmpty(authDesc)){
			showErrMsg($("#authDesc"), "请填写权限描述");
			return;
		}
		if(isEmpty(porder)){
			showErrMsg($("#porder"), "请填写顺序编号");
			return;
		}
		if(authLevel==3 && isEmpty(action)){
			showErrMsg($("#action"), "请填写访问地址");
			return;
		}
		

		if(confirm("确认新增权限！")){
			var param = {};
			param.authLevel = authLevel;
			param.parentId = parentId;
			param.authDesc = authDesc;
			param.porder = porder;
			param.state = state;
			param.isDefault = isDefault;
			param.isToMer = isToMer;
			param.isDisplay = isDisplay;
			param.action = action;
			
			var succCallBack=function(data){
				if(data.retCode == "0000"){
					alert(data.retMsg);
				}else{
					alert(data.retMsg);
				}
			};
			
			var errCallBack = function(){
				alert("添加权限信息失败");
			};
			
			doAjaxRequest("${ctx}/console/au_addAuthInfo.action", param, succCallBack, errCallBack);
		}
	}
</script>
</html>
