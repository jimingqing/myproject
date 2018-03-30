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
          	订单录入
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
										用户类型：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<label><input type="radio" id="userType" name="userType" value="R" checked="checked"/>注册用户</label>
										<label><input type="radio" id="userType" name="userType" value="U"/>非注册用户</label>
									</td>
								</tr>
								
								<tr id="tr_code" align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										用户手机号码：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="userCode" id="userCode" class="txt_input" value="">
										<!--input type="button" value="搜索" class="btn2" style="margin:0;padding:2px 5px;" onclick="searchUser();"-->
									</td>
								</tr>
								
								<tr id="tr_name" align="left" valign="bottom" class="black_text_k5 none">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										用户姓名：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="userName" id="userName" class="txt_input" value="">
									</td>
								</tr>
								
								<tr id="tr_bank" align="left" valign="bottom" class="black_text_k5 none">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										银行名称：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select id="bankCode"></select>
									</td>
								</tr>
								
								<tr id="tr_acct" align="left" valign="bottom" class="black_text_k5 none">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										用户银行账号：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="openBankCard" id="openBankCard" class="txt_input" value="" size="30">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										订单金额：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="ordAmt" id="ordAmt" class="txt_input" value="">元
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="top" bgcolor="#D9DFEE">
										订单描述：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<textarea rows="10" cols="50" id="ordDesc" style="border: 1px solid #A9A9A9;"></textarea>
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
	var verifyFlag = false;
	$(function(){	
		initBankInfo();
		$("#userCode").blur(function(){ 
			showErrMsg();
			showOkMsg();
			checkUserCode();
			
		})
		$.each($("input[name=userType]"),function(idx,item){
			$(item).click(function(){
				if($(this).val()=="R"){
					$("#tr_code").show();
					$("#tr_name").hide();
					$("#tr_acct").hide();
					$("#tr_bank").hide();
					verifyFlag = false;
					checkUserCode();
				}else{
					$("#tr_code").hide();
					$("#tr_name").show();
					$("#tr_acct").show();
					$("#tr_bank").show();
					verifyFlag = true;
				}
			})
		})
	})
	
	
	function initBankInfo(){
		var succCallBack=function(data){
			$("#bankCode").html("");
			if(data.retCode == "0000"){
				if(data.content.length > 0){
					var html = "<option value=''>请选择银行...</option>";
					$.each(data.content, function(index, item){
						html+="<option value='"+item.id+"'>"+item.bankName+"</option>";
					})
					$("#bankCode").html(html);
				}
			}
		};
		
		doAjaxRequest("${ctx}/console/bk_qryBankInfoList.action", {}, succCallBack);
	}
	
	function checkUserCode(){
		var param = {};
		param.userCode = $("#userCode").val();
		if(param.userCode=="") return;
		doAjaxRequest("${ctx}/console/usr_qryUserInfoByCode.action", param, function(data){
			if(data.retCode == "0000"){
				var userInfo = data.content;
				if(userInfo.bankRels.length >0){
					verifyFlag = true;
					showOkMsg($("#userCode"),"【姓名："+userInfo.userName+"】  【支付卡号：**** "+(userInfo.bankRels!=null?userInfo.bankRels[0].bankCardNo:"")+"】");
				}else{
					verifyFlag = false;
					showErrMsg($("#userCode"),"【姓名："+userInfo.userName+"】  【支付卡号：无效】");
				}
				
			}else{
				verifyFlag = false;
				showErrMsg($("#userCode"), "用户手机号码错误");
			}
			
		});
	}
	
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
		var userType = $("input[name='userType']:checked").val();
		var userCode = $("#userCode").val();
		var userName = $("#userName").val();
		var openBankCard = $("#openBankCard").val();
		var ordAmt = $("#ordAmt").val();
		var ordDesc = $("#ordDesc").val();
		var bankCode = $("#bankCode").val();
		if(userType=="R"){
			if(isEmpty(userCode)){
				showErrMsg($("#userCode"), "请填写用户手机号码");
				return;
			}
		}else{
			if(isEmpty(userName)){
				showErrMsg($("#userName"), "请填写用户姓名");
				return;
			}
			if(isEmpty(bankCode)){
				showErrMsg($("#bankCode"), "请选择银行");
				return;
			}
			if(isEmpty(openBankCard)){
				showErrMsg($("#openBankCard"), "请填写银行账号");
				return;
			}
			if(!isNumber(openBankCard)){
				showErrMsg($("#openBankCard"), "请正确填写银行账号");
				return;
			}
		}
		if(isEmpty(ordAmt)){
			showErrMsg($("#ordAmt"), "请填写订单金额");
			return;
		}
		if(!isMoney(ordAmt)){
			showErrMsg($("#ordAmt"), "请正确填写订单金额");
			return;
		}
		
		if(!verifyFlag){
			alert("请检查用户手机号码");
			$("#userCode").focus();
			return;
		}

		if(confirm("确认提交订单！")){
			var param = {};
			param.userType = userType;
			if(userType=="R"){
				param.userCode = userCode;
			}else{
				param.userName = userName;
				param.bankCode = bankCode;
				param.bankName = $("#bankCode option:selected").text();
				param.openBankCard = openBankCard;
			}
			param.ordAmt = ordAmt;
			param.ordDesc = ordDesc;
			
			var succCallBack=function(data){
				if(data.retCode == "0000"){
					alert(data.retMsg);
				}else{
					alert(data.retMsg);
				}
			};
			
			var errCallBack = function(){
				alert("订单提交失败");
			};
			
			doAjaxRequest("${ctx}/console/ord_addOrderHandle.action", param, succCallBack, errCallBack);
			
		}
	}
	
	function searchUser(){
		window.showModalDialog('${ctx}/admin/searchGoodsPage.action',window,'dialogWidth:800px;dialogHeight:400px;location=no;');
	}
</script>
</html>
