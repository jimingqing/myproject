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
          	修改操作员信息
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
	<input type="hidden" value="" id="merId">
	   <!--查询区域表格开始-->

  <table width="100%"  border="0" cellpadding="0" cellspacing="1" class="blue_kuang" style="margin-top:7px;">
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>商户：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										${userInfoVo.merName }
									</td>
								</tr>
																
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>操作员号：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										${userInfoVo.operCode }
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
										状态标志：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select id="operState">
											<option value="N">正常</option>
											<option value="C">关闭</option>
										</select>
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										权限组：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select name="grpId" id="grpId">
											<option value="00">管理组</option>
										</select>
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
									</td>
									<td id="auth" width="77%" bgcolor="#FFFFFF">
									</td>
								</tr>
								
								<tr align="center" bgcolor="#FFFFFF" class="black_text_k5">
									<td height="42" colspan="2" align="center">
										<input type="button" value="提  交" class="btn2" onclick="actSubmit();">
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

	$(function(){	
		initMerOperInfo();
		$("#operCode").blur(function(){
			showErrMsg();
			showOkMsg();
			if($("#merId").val()==""){
				showErrMsg($("#operCode"), "请选择商户");
				return;
			}
			var param = {};
			param.operCode=$(this).val();
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
		$("#grpId").change(function(){
			initGrpAuth();
		})
	})
	
	function initGrpAuth(){
		var param = {};
		param.grpId = $("#grpId").val();
		var succCallBack=function(data){
			if(data.retCode == "0000"){
				var html = "";
				var tree = data.content;
				if(tree.children.length>0){
					$.each(tree.children,function(index,item){
						html += "<fieldset style='border:1px solid #999;margin:10px;padding:10px;'>";
						html += "<legend>"+item.text+"</legend>";
						if(item.children.length>0){
							$.each(item.children,function(index2,item2){
								html+="<p>";
								html+="&nbsp;&nbsp;&nbsp;&nbsp;"+item2.text+"： ";
								$.each(item2.children,function(index3,item3){
									html+="  【";
									html+=item3.text;
									html+="】  ";
									
								})
								html+="</p>";
							})
						}
						html += "</fieldset>";
					})
				}
				
				$("#auth").html(html);
			}
		};
		
		doAjaxRequest("${ctx}/console/au_qryAuthInfoByGrpId.action", param, succCallBack);
	}
	
	function initMerOperInfo(){
		var param = {};
		param.operId = "${param.operId}";
		var succCallBack=function(data){
			if(data.retCode == "0000"){
				var oper = data.content;
				$("#operCode").val(oper.operCode);
				$("#operName").val(oper.operName);
				$("#operTel").val(oper.operTel);
				$("#operMp").val(oper.operMp);
				$("#operEmail").val(oper.operEmail);
				$("#operState").val(oper.operState);
				$("#grpId").val(oper.grpId);
				$("#merId").val(oper.merId);
				initGrpAuth();
			}
		};
		
		doAjaxRequest("${ctx}/console/mer_qryMerOperInfoDetail.action", param, succCallBack);
	}
	
	function actSubmit(){
		showErrMsg();
		var operName = $("#operName").val();
		var operTel = $("#operTel").val();
		var operMp = $("#operMp").val();
		var operEmail = $("#operEmail").val();
		var operState = $("#operState").val();
		var grpId = $("#grpId").val();
		
		if(isEmpty(grpId)){
			showErrMsg($("#grpId"), "请选择权限组");
			$("#grpId").focus();
			return;
		}
		
		if(confirm("确认修改操作员信息！")){
			var param = {};
			param.merId = $("#merId").val();
			param.operId =  "${param.operId}" ;
			param.operName =  operName ;
			param.operTel =  operTel ;
			param.operMp =  operMp ;
			param.operEmail =  operEmail ;
			param.operState =  operState ;
			param.grpId = grpId;
			
			var succCallBack=function(data){
				if(data.retCode == "0000"){
					alert("修改操作员信息成功");
				}else{
					alert("修改操作员信息失败");
				}
			};
			
			var errCallBack = function(){
				alert("修改操作员信息失败");
			};
			
			doAjaxRequest("${ctx}/console/mer_editMerOperInfo.action", param, succCallBack, errCallBack);
		}
	}
</script>
</html>
