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
          	修改商户信息
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
									<td width="100%" height="30" colspan="2" bgcolor="#FFFFFF" align="center" class="ft-14-bold">
										商户基本信息
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>商户号：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="merCode" id="merCode" class="txt_input" value="" disabled="disabled">
									</td>
								</tr>
																
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>商户名称：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="merName" id="merName" class="txt_input" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										所在地区：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select id="provId">
											<option value="">请选择省份...</option>
										</select>&nbsp;&nbsp;
										<select id="cityId">
											<option value="">请选择城市...</option>
										</select>&nbsp;&nbsp;&nbsp;&nbsp;
										<!-- 
										<select id="areaId">
											<option value="">请选择地区...</option>
										</select>
										 -->
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>商户地址：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="merAddr" id="merAddr" class="txt_input" size="50" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>工商登记号：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="regCode" id="regCode" class="txt_input" size="30" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>组织机构代码：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="corpCode" id="corpCode" class="txt_input" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										商户邮编：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="merZip" id="merZip" class="txt_input" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										商户状态：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select name="merState" id="merState">
											<%--<option value="A">待审核</option>
											--%><option value="N">正常</option>
											<option value="C">关闭</option>
										</select>
									</td>
								</tr>
								<!--
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="100%" height="30" colspan="2" bgcolor="#FFFFFF" align="center" class="ft-14-bold">
										商户交易账户信息
									</td>
								</tr>
								
								<tr id="parent" align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>银联商户号：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="unMerId" id="unMerId" class="txt_input" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>开户银行：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select name="merBankName" id="merBankName"></select>
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>开户银行账号：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="merBankAcct" id="merBankAcct" class="txt_input" size="30" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										是否实时代扣：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select name="isRealWh" id="isRealWh">
											<option value="Y">是</option>
											<option value="N">否</option>
										</select>
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>账户日交易次数：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="dayTransCnt" id="dayTransCnt" class="txt_input" value="0">
										<span class="remark">0 为无限制</span>
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>单笔限制金额：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="oneLimitAmt" id="oneLimitAmt" class="txt_input" value="0">
										<span class="remark">0 为无限制</span>
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>总计限制金额：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="totalLimitAmt" id="totalLimitAmt" class="txt_input" value="0">
										<span class="remark">0 为无限制</span>
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										是否发送短信：
									</td>
									<td width="77%" bgcolor="#FFFFFF" >
										<select name="isSendSms" id="isSendSms">
											<option value="Y">是</option>
											<option value="N">否</option>
										</select>
										<span class="remark">说明：用户提交订单扣款成功后系统是否向用户手机推送一条短信</span>
									</td>
								</tr>
								  -->
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="100%" height="30" colspan="2" bgcolor="#FFFFFF" align="center" class="ft-14-bold">
										商户主联系人信息
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										联系人员姓名：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="merContName" id="merContName" class="txt_input" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										联系人员电话：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="merContTel" id="merContTel" class="txt_input" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										联系人员手机：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="merContMp" id="merContMp" class="txt_input" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										联系人员 Email：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="merContEmail" id="merContEmail" class="txt_input" value="">
									</td>
								</tr>

								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										联系人角色：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select name="merContRole" id="merContRole">
											<option value="">请选择...</option>
											<option value="F">财务</option>
											<option value="T">技术</option>
											<option value="O">运营</option>
											<option value="M">市场</option>
											<option value="S">销售</option>
										</select>
									</td>
								</tr>
								
								<tr align="center" bgcolor="#FFFFFF" class="black_text_k5">
									<td height="42" colspan="2" align="center">
										<input type="button" value="提  交" class="btn2" onclick="actSubmit();">
										<input type="button" value="返  回" class="btn2" onclick="javascript:history.go(-1);">
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
	var cityId = "";
	$(function(){	
		initProvCityArea('1', '', 'provId');
		initBankInfo();
		$("#merCode").blur(function(){
			if($(this).val()==""){
				return;
			}
			if($(this).val().indexOf("88")!=0){
				showErrMsg($("#merCode"), "请正确填写商户编号");
				return;
			}
			showErrMsg();
			showOkMsg();
			var param = {};
			param.merCode=$(this).val();
			var succCallBack=function(data){
				if(data.retCode == "1001"){
					showErrMsg($("#merCode"), "商户号错误，请重新填写");		
				}else if(data.retCode == "1301"){
					showErrMsg($("#merCode"), "商户号已存在，请重新填写");		
				}else{
					showOkMsg($("#merCode"),"");
				}
			};
			
			doAjaxRequest("${ctx}/console/mer_checkMerInfoExist.action", param, succCallBack);
		});
		$("#provId").change(function(){ 
			var provId = $(this).val();
			if(provId==""){
				$("#cityId").html("<option value=''>请选择城市...</option>");
			}else{
				initProvCityArea('2', provId, 'cityId');
			}
		})
		var param = {};
		param.merId = "${param.merId}";
		doAjaxRequest("${ctx}/console/mer_qryMerInfoDetail.action", param, function(data){
			if(data.retCode == "0000"){
				var merInfo = data.content;
				$("#merCode").val(merInfo.merCode);
				$("#merName").val(merInfo.merName);
				$.each($("#provId option"),function(index,item){
					if($(item).text()==merInfo.merProv){
						$(item).attr("selected", true);
					}
				})
				cityId = merInfo.merArea;
				$("#provId").change();
				$("#merAddr").val(merInfo.merAddr);
				$("#regCode").val(merInfo.regCode);
				$("#corpCode").val(merInfo.corpCode);
				$("#merZip").val(merInfo.merZip);
				$("#merZip").val(merInfo.merZip);
				$("#merState").val(merInfo.merState);
				$.each($("#merBankName option"),function(index,item){
					if($(item).text()==merInfo.merBankName){
						$(item).attr("selected", true);
					}
				})
				$("#unMerId").val(merInfo.unMerId);
				$("#merBankAcct").val(merInfo.merBankAcct);
				$("#isRealWh").val(merInfo.isRealWh);
				$("#dayTransCnt").val(merInfo.dayTransCnt);
				
				$("#oneLimitAmt").val(merInfo.oneLimitAmt);
				$("#totalLimitAmt").val(merInfo.totalLimitAmt);
				$("#isSendSms").val(merInfo.isSendSms);
				$("#merContName").val(merInfo.merContName);
				$("#merContTel").val(merInfo.merContTel);
				$("#merContMp").val(merInfo.merContMp);
				$("#merContEmail").val(merInfo.merContEmail);
				$("#merContRole").val(merInfo.merContRole);
			}
		});
		
	})
	
	function initProvCityArea(level, pid, selectid){
		var param = {};
		param.levelCode = level;
		param.pid = pid;
		var succCallBack=function(data){
			$("#"+selectid).html("");
			if(data.retCode == "0000"){
				if(data.content.length > 0){
					var html = "";
					if(level==1){
						html = "<option value=''>请选择省份...</option>";
					}else{
						html = "<option value=''>请选择城市...</option>";
					}
					$.each(data.content, function(index, item){
						html+="<option value='"+item.areaId+"'>"+item.name+"</option>";
					})
					$("#"+selectid).html(html);
					$.each($("#"+selectid+" option"),function(index,item){
						if($(item).text()==cityId){
							$(item).attr("selected", true);
						}
					})
				}
				
			}
		};
		
		doAjaxRequestForSyn("${ctx}/console/pc_qryProvCityInfo.action", param, succCallBack);
	}
	
	function initBankInfo(){
		var succCallBack=function(data){
			$("#merBankName").html("");
			if(data.retCode == "0000"){
				if(data.content.length > 0){
					var html = "<option value=''>请选择银行...</option>";
					$.each(data.content, function(index, item){
						html+="<option value='"+item.id+"'>"+item.bankName+"</option>";
					})
					$("#merBankName").html(html);
				}
				
			}
		};
		
		doAjaxRequestForSyn("${ctx}/console/bk_qryBankInfoList.action", {}, succCallBack);
	}
	
	function actSubmit(){
		showErrMsg();
		var merCode = $("#merCode").val();
		var merName = $("#merName").val();
		var provId = $("#provId").val();
		var cityId = $("#cityId").val();
		var merAddr = $("#merAddr").val();
		var regCode = $("#regCode").val();
		var corpCode = $("#corpCode").val();
		var merZip = $("#merZip").val();
		var merState = $("#merState").val();
		
		var unMerId = $("#unMerId").val();
		var merBankName = $("#merBankName option:selected").text();
		var merBankAcct = $("#merBankAcct").val();
		var isRealWh = $("#isRealWh").val();
		var dayTransCnt = $("#dayTransCnt").val();
		var oneLimitAmt = $("#oneLimitAmt").val();
		var totalLimitAmt = $("#totalLimitAmt").val();
		var isSendSms = $("#isSendSms").val();
		
		var merContName = $("#merContName").val();
		var merContTel = $("#merContTel").val();
		var merContMp = $("#merContMp").val();
		var merContEmail = $("#merContEmail").val();
		var merContRole = $("#merContRole").val();
		
		if(isEmpty(merCode)){
			showErrMsg($("#merCode"), "请填写商户号");
			$("#merCode").focus();
			return;
		}
		if(merCode.indexOf("88")!=0){
			showErrMsg($("#merCode"), "请正确填写商户号");
			$("#merCode").focus();
			return;
		}
		if(isEmpty(merName)){
			showErrMsg($("#merName"), "请填写商户名称");
			$("#merName").focus();
			return;
		}
		if(isEmpty(provId)){
			showErrMsg($("#provId"), "请选择省份");
			$("#provId").focus();
			return;
		}
		if(isEmpty(cityId)){
			showErrMsg($("#cityId"), "请选择城市");
			$("#cityId").focus();
			return;
		}
		if(isEmpty(merAddr)){
			showErrMsg($("#merAddr"), "请填写商户地址");
			$("#merAddr").focus();
			return;
		}
		
		if(isEmpty(regCode)){
			showErrMsg($("#regCode"), "请填写工商登记号");
			$("#regCode").focus();
			return;
		}
		if(isEmpty(corpCode)){
			showErrMsg($("#corpCode"), "请填写组织机构代码");
			$("#corpCode").focus();
			return;
		}
		if(isEmpty(merState)){
			showErrMsg($("#merState"), "请选择商户状态");
			$("#merState").focus();
			return;
		}
		/*****
		if(isEmpty(unMerId)){
			showErrMsg($("#unMerId"), "请填写银联商户号");
			$("#unMerId").focus();
			return;
		}
		if(isEmpty(merBankName)){
			showErrMsg($("#merBankName"), "请选择开户银行");
			$("#merBankName").focus();
			return;
		}
		if(isEmpty(merBankAcct)){
			showErrMsg($("#merBankAcct"), "请填写开户银行账号");
			$("#merBankAcct").focus();
			return;
		}
		if(isEmpty(isRealWh)){
			showErrMsg($("#isRealWh"), "请选择开户银行");
			$("#isRealWh").focus();
			return;
		}
		if(isEmpty(dayTransCnt)){
			showErrMsg($("#dayTransCnt"), "请填写账户日交易次数");
			$("#dayTransCnt").focus();
			return;
		}
		if(isEmpty(oneLimitAmt)){
			showErrMsg($("#oneLimitAmt"), "请填写单笔限制金额");
			$("#oneLimitAmt").focus();
			return;
		}
		
		if(isEmpty(totalLimitAmt)){
			showErrMsg($("#totalLimitAmt"), "请填写总计限制金额");
			$("#totalLimitAmt").focus();
			return;
		}
		***/

		if(confirm("确认修改商户信息！")){
			var param = {};
			param.merId = "${param.merId}";
			param.merCode =  merCode ;
			param.merName =  merName ;
			param.provId =  provId ;
			param.cityId =  cityId ;
			param.merAddr =  merAddr ;
			param.regCode =  regCode ;
			param.corpCode =  corpCode ;
			param.merZip =  merZip ;
			param.merState = merState;

			param.unMerId =  unMerId ;
			param.merBankName =  merBankName ;
			param.merBankAcct =  merBankAcct ;
			param.isRealWh =  isRealWh ;
			param.dayTransCnt =  dayTransCnt ;
			param.oneLimitAmt =  oneLimitAmt ;
			param.totalLimitAmt =  totalLimitAmt ;
			param.isSendSms =  isSendSms ;

			param.merContName =  merContName ;
			param.merContTel =  merContTel ;
			param.merContMp =  merContMp ;
			param.merContEmail =  merContEmail ;
			param.merContRole =  merContRole ;
			
			var succCallBack=function(data){
				if(data.retCode == "0000"){
					alert("修改商户信息成功");
				}else{
					alert("修改商户信息失败");
				}
			};
			
			var errCallBack = function(){
				alert("修改商户信息失败");
			};
			
			doAjaxRequest("${ctx}/console/mer_editMerInfo.action", param, succCallBack, errCallBack);
		}
	}
</script>
</html>
