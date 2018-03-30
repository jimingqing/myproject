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
          	修改用户信息
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
										用户基本信息
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										用户类型：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										付款用户
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>用户姓名：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="userName" id="userName" class="txt_input" value="">
									</td>
								</tr>
																
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>手机号码：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="phoneNo" id="phoneNo" class="txt_input" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>证件信息：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select name="certType" id="certType">
											<option value="">请选择</option>
											<option value="01">身份证</option>
											<option value="02">护照</option>
											<option value="03">军官证</option>
											<option value="04">回乡证</option>
											<option value="05">工商登记号</option>
											<option value="99">其他证件</option>
										</select>
										证件号：<input type="text" name="certId" id="certId" class="txt_input" size="30" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										用户性别：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select name="userSex" id="userSex">
											<option value="">请选择</option>
											<option value="M">男</option>
											<option value="F">女</option>
										</select>
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										电子邮箱：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="email" id="email" class="txt_input" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>所在地区：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select id="provId">
											<option value="">请选择省份...</option>
										</select>&nbsp;&nbsp;
										<select id="cityId">
											<option value="">请选择城市...</option>
										</select>&nbsp;&nbsp;&nbsp;&nbsp;
										<select id="areaId">
											<option value="">请选择地区...</option>
										</select>
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>详细地址：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="userAddr" id="userAddr" class="txt_input" size="50" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										邮编：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="userZip" id="userZip" class="txt_input" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>所属公司名称：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="deptName" id="deptName" class="txt_input" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										职位：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="position" id="position" class="txt_input" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										用户状态：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select name="userState" id="userState">
											<option value="N">正常</option>
											<option value="L">锁定</option>
											<option value="C">关闭</option>
										</select>
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5 none">
									<td width="23%" height="30" align="right" valign="middle" bgcolor="#D9DFEE">
										已开通快捷支付银行卡信息：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<ul id="bankRel"></ul>
										<input type="button" value="新增" class="btn3 mgl-20" onclick="addBankRel();">
									</td>
								</tr>
								
								<tr id="bank_name" align="left" valign="bottom" class="black_text_k5 none">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>开户银行：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select name="newBankName" id="newBankName"></select>
									</td>
								</tr>
								
								<tr id="bank_card" align="left" valign="bottom" class="black_text_k5 none">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>开户银行账号：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="newBankCard" id="newBankCard" class="txt_input" size="30" value="">
									</td>
								</tr>
								
								<tr align="center" bgcolor="#FFFFFF" class="black_text_k5">
									<td height="42" colspan="2" align="center">
										<input type="button" value="提  交" class="btn2" onclick="actSubmit();">&nbsp;&nbsp;&nbsp;&nbsp;
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
	var userId = "";
	var cityId = "";
	var areaId = "";
	var bankRelId = "";
	var addBankFlag = false;
	$(function(){	
		initProvCityArea('1', '', 'provId');
		initUserInfo();
		$("#phoneNo").blur(function(){
			showErrMsg();
			showOkMsg();
			if(!isMobile($(this).val())){
				showErrMsg($("#phoneNo"), "请正确填写手机号码");
				return;
			}
			var param = {};
			param.phoneNo=$(this).val();
			var succCallBack=function(data){
				if(data.retCode == "0000"){
					if(data.content == userId){
						showOkMsg($("#phoneNo"),"");
					}else{
						showErrMsg($("#phoneNo"), "手机号码已注册，请更换手机号码");
					}
							
				}else{
					showOkMsg($("#phoneNo"),"");
				}
			};
			
			doAjaxRequest("${ctx}/console/usr_checkPhoneNo.action", param, succCallBack);
		});
		$("#provId").change(function(){ 
			var provId = $(this).val();
			$("#cityId").html("<option value=''>请选择城市...</option>");
			$("#areaId").html("<option value=''>请选择地区...</option>");
			initProvCityArea('2', provId, 'cityId');
		})
		$("#cityId").change(function(){ 
			var city = $(this).val();
			if(city==""){
				if(cityId!=""){
					initProvCityArea('3', cityId, 'areaId');
				}else{
					$("#cityId").html("<option value=''>请选择地区...</option>");
				}
				
			}else{
				initProvCityArea('3', city, 'areaId');
			}
		})
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
					}else if(level==2){
						html = "<option value=''>请选择城市...</option>";
					}else{
						html = "<option value=''>请选择地区...</option>";
					}
					if(level==3){
						$("#areaId").show();
					}
					$.each(data.content, function(index, item){
						html+="<option value='"+item.areaId+"'>"+item.name+"</option>";
					})
					$("#"+selectid).html(html);
					if(level==2 && cityId!=""){
						$("#"+selectid).val(cityId);
					}
					if(level==3 && areaId!=""){
						$("#"+selectid).val(areaId);
					}
				}else{
					if(level==3){
						$("#areaId").hide();
					}
				}
				
			}
		};
		
		doAjaxRequest("${ctx}/console/pc_qryProvCityInfo.action", param, succCallBack);
	}
	
	function initUserInfo(){
		var param = {};
		param.userId = "${param.userId}";
		var succCallBack=function(data){
			if(data.retCode == "0000"){
				var userInfo = data.content;
				userId = userInfo.id;
				$("#userName").val(userInfo.userName==null?"":userInfo.userName);
				$("#phoneNo").val(userInfo.phoneNo==null?"":userInfo.phoneNo);
				$("#certId").val(userInfo.certId);
				$("#certType").val(userInfo.certType);
				$("#userSex").val(userInfo.userSex);
				$("#provId").val(userInfo.userProv);
				cityId = userInfo.userCity;
				areaId = userInfo.userArea;
				$("#provId").change();
				$("#cityId").change();
				$("#email").val(userInfo.email);
				$("#userAddr").val(userInfo.userAddr);
				$("#userZip").val(userInfo.userZip);
				$("#deptName").val(userInfo.deptName);
				$("#position").val(userInfo.position);
				$("#userState").val(userInfo.userState);
				/*var li = "";
				$.each(userInfo.bankRels, function(index, item){
					if(isEmpty($.trim(item.protFileName))||isEmpty(item.protFileName)){
						if(item.state == "C"){
							li+="<li><label style='color:red;'><input type='radio' name='bankRelId' value='"+item.id+"|"+item.bankId+"|"+item.state+"'>"+item.bankName+" 【卡号：**** "+item.bankCardNo+"】</label></li>";
						}else{
							li+="<li><label><input type='radio' name='bankRelId' value='"+item.id+"|"+item.bankId+"|"+item.state+"'>"+item.bankName+" 【卡号：**** "+item.bankCardNo+"】</label></li>";
						}
					}else{
						if(item.state == "C"){
							li+="<li><label style='color:red;'><input type='radio' name='bankRelId' value='"+item.id+"|"+item.bankId+"|"+item.state+"'>"+item.bankName+" 【卡号：**** "+item.bankCardNo+"】</label><a href='"+item.protFileName+"'>协议文件</a></li>";
						}else{
							li+="<li><label><input type='radio' name='bankRelId' value='"+item.id+"|"+item.bankId+"|"+item.state+"'>"+item.bankName+" 【卡号：**** "+item.bankCardNo+"】</label><a href='"+item.protFileName+"'>协议文件</a></li>";
						}
						
					}
					
				})
				$("#bankRel").html(li);
				$.each($("input[name='bankRelId']"), function(index, item){
					
					$(item).click(function(){
						if(!isEmpty($("#newBankCard").val())){
							if(confirm("确认取消新增开户银行信息")){
								$("#bank_name").hide();
								$("#bank_card").hide();
								$("#newBankName").val("");
								$("#newBankCard").val("");
							}else{
								$("input[name='bankRelId']:checked").attr("checked",false);
								return;
							}
						}
						if($(this).parent().find("div[id='bankInfo']").length > 0){
							return;
						}
						if($("#openBankCard").length>0 && !isEmpty($("#openBankCard").val())){
							if(!confirm("确认取消修改快捷支付银行卡信息")){
								$("#bankInfo").parent().find("input[name='bankRelId']").eq(0).attr("checked",true);
								return;
							}
						}
						$("#editbtn").remove();
						$("#bankInfo").remove();
						$("#bank_name").hide();
						$("#bank_card").hide();
						var bankId=$(this).val().split("|")[1];
						bankRelId = $(this).val().split("|")[0];
						var state = $(this).val().split("|")[2];
						var btn = "<span id='editbtn'><input type='button' value='修改' class='btn3 mgl-20' onclick='editBankRel(this, "+bankId+");'>";
						if(state == "N"){
							btn += "<input type='button' value='解绑' class='btn3 mgl-20' onclick='removeBankRel(this, "+bankRelId+", \""+$(this).parent().text()+"\");'></span>";
						}else{
							btn += "<input type='button' value='绑定' class='btn3 mgl-20' onclick='resetBankRel(this, "+bankRelId+", \""+$(this).parent().text()+"\");'></span>";
						}
						
						$(this).parent().parent().append(btn);
					})
				})*/
			}
		};
		
		doAjaxRequest("${ctx}/console/usr_qryUserInfoDetail.action", param, succCallBack);
	}
	
	function editBankRel(obj, bankId){
		$(obj).attr("disabled", true);
		addBankFlag = false;
		var bankInfo = "<div id='bankInfo' class='mgl-20 mgb-10'>";
		bankInfo += "开户银行：<select name='openBankName' id='openBankName'></select>&nbsp;&nbsp;";
		bankInfo += "银行账号：<input type='text' name='openBankCard' id='openBankCard' class='txt_input' size='30' value='''>";	
		bankInfo += "</div>";
		$(obj).parent().parent().append(bankInfo);
		initBankInfo(bankId);
	}
	
	function addBankRel(){
		if($("#openBankCard").length>0 && !isEmpty($("#openBankCard").val())){
			if(!confirm("确认取消修改快捷支付银行卡信息")){
				return;
			}
		}
		$("#editbtn").remove();
		$("#bankInfo").remove();
		addBankFlag = true;
		$.each($("input[name='bankRelId']"), function(index, item){
			$(item).attr("checked", false);
		})
		$("#bank_name").show();
		$("#bank_card").show();
		
		doAjaxRequest("${ctx}/console/bk_qryBankInfoList.action", {}, function(data){
			$("#newBankName").html("");
			if(data.retCode == "0000"){
				if(data.content.length > 0){
					var html = "<option value=''>请选择银行...</option>";
					$.each(data.content, function(index, item){
						html+="<option value='"+item.id+"'>"+item.bankName+"</option>";
					})
					$("#newBankName").html(html);
				}
				
			}
		});
	}
	
	function removeBankRel(obj, bankRelId, info){
		if(confirm("确定解除绑定："+info+" 快捷支付银行卡")){
			var param = {};
			param.bankRelId = bankRelId;
			param.type = "C";
			doAjaxRequest("${ctx}/console/usr_editUserBankRelState.action", param, function(data){
				if(data.retCode == "0000"){
					$(obj).attr("value", "绑定");	
					alert("解除绑定成功");
				}else{
					alert("解除绑定失败");
				}
			});
		}
	}
	
	function resetBankRel(obj, bankRelId, info){
		if(confirm("确定绑定："+info+" 快捷支付银行卡")){
			var param = {};
			param.bankRelId = bankRelId;
			param.type = "N";
			doAjaxRequest("${ctx}/console/usr_editUserBankRelState.action", param, function(data){
				if(data.retCode == "0000"){
					$(obj).attr("value", "解绑");	
					alert("绑定成功");
				}else{
					alert("绑定失败");
				}
			});
		}
	}
	
	function initBankInfo(bankId){
		var succCallBack=function(data){
			$("#openBankName").html("");
			if(data.retCode == "0000"){
				if(data.content.length > 0){
					var html = "<option value=''>请选择银行...</option>";
					$.each(data.content, function(index, item){
						html+="<option value='"+item.id+"'>"+item.bankName+"</option>";
					})
					$("#openBankName").html(html);
					if(!isEmpty(bankId)){
						$("#openBankName").val(bankId);
					}
				}
				
			}
		};
		
		doAjaxRequest("${ctx}/console/bk_qryBankInfoList.action", {}, succCallBack);
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
	
	function actSubmit(){
		showErrMsg();
		var userName = $("#userName").val();
		var phoneNo = $("#phoneNo").val();
		var certType = $("#certType").val();
		var certId = $("#certId").val();
		var userSex = $("#userSex").val();
		var email = $("#email").val();
		var provId = $("#provId").val();
		var cityId = $("#cityId").val();
		var areaId = $("#areaId").val();
		
		var userAddr = $("#userAddr").val();
		var userZip = $("#userZip").val();
		var deptName = $("#deptName").val();
		var position = $("#position").val();
		var state = $("#userState").val();
		var openBankName = $("#openBankName").val();
		var openBankCard = $("#openBankCard").val();
		var newBankName = $("#newBankName").val();
		var newBankCard = $("#newBankCard").val();
		
		if(isEmpty(userName)){
			showErrMsg($("#userName"), "请填写用户姓名");
			$("#userName").focus();
			return;
		}
		if(!isMobile(phoneNo)){
			showErrMsg($("#phoneNo"), "请正确填写手机号码");
			$("#phoneNo").focus();
			return;
		}
		if(isEmpty(certType)){
			showErrMsg($("#certType"), "请选择证件类型");
			$("#certType").focus();
			return;
		}
		if(isEmpty(certId)){
			showErrMsg($("#certId"), "请填写证件号码");
			$("#certId").focus();
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
		if(isEmpty(userAddr)){
			showErrMsg($("#userAddr"), "请填写详细地址");
			$("#userAddr").focus();
			return;
		}
		
		if(isEmpty(deptName)){
			showErrMsg($("#deptName"), "请填写所在单位名称");
			$("#deptName").focus();
			return;
		}
		if(addBankFlag){
			if(isEmpty(newBankName)){
				showErrMsg($("#newBankName"), "请选择开户银行");
				$("#newBankName").focus();
				return;
			}
			if(isEmpty(newBankCard)){
				showErrMsg($("#newBankCard"), "请填写开户银行账号");
				$("#newBankCard").focus();
				return;
			}
		}
		if($("#openBankName").length > 0){
			if(isEmpty(openBankName)){
				showErrMsg($("#openBankName"), "请选择开户银行");
				$("#openBankName").focus();
				return;
			}
		}
		if($("#openBankCard").length > 0){
			if(isEmpty(openBankCard)){
				showErrMsg($("#openBankCard"), "请填写开户银行账号");
				$("#openBankCard").focus();
				return;
			}
		}

		if(confirm("确认修改用户信息！")){
			var param = {};
			param.userId = userId;
			param.userName =  userName ;
			param.phoneNo =  phoneNo ;
			param.provId =  provId ;
			param.cityId =  cityId ;
			param.areaId =  areaId ;
			param.certType =  certType ;
			param.certId =  certId ;
			param.userSex =  userSex ;
			param.email =  email ;

			param.userAddr =  userAddr ;
			param.userZip =  userZip ;
			param.deptName =  deptName ;
			param.position =  position ;
			param.state = state;
			if(addBankFlag){
				param.bankId = newBankName;
				param.openBankName =  newBankName ;
				param.openBankCard =  newBankCard ;
			}else{
				param.bankId = openBankName;
				param.openBankName =  openBankName ;
				param.openBankCard =  openBankCard ;
			}
			var flag = false;
			doAjaxRequestForSyn("${ctx}/console/usr_checkPhoneNo.action", param, function(data){
				if(data.retCode == "0000"){
					if(data.content == userId){
						showOkMsg($("#phoneNo"),"");
					}else{
						showErrMsg($("#phoneNo"), "手机号码已注册，请更换手机号码");
						flag = true;
					}
					
				}else{
					showOkMsg($("#phoneNo"),"");
				}
			});
			if(flag) return;
			if(addBankFlag && 
					(!isEmpty(newBankName) || !isEmpty(newBankCard))){
				doAjaxRequestForSyn("${ctx}/console/usr_checkUserBankRelInfo.action", param, function(data){
					if(data.retCode == "1106"){
						showErrMsg($("#newBankCard"), "填写开户银行账号信息已存在，请重新填写", false);	
						flag = true;
					}
					if(data.retCode == "1001"){
						showErrMsg($("#newBankCard"), data.retMsg, false);	
						flag = true;
					}
				});
			}
			if(flag) return;
			if(!addBankFlag && $("#bankInfo").length>0){
				param.bankRelId = bankRelId;
				doAjaxRequestForSyn("${ctx}/console/usr_checkUserBankRelInfo.action", param, function(data){
					if(data.retCode == "1106"){
						showErrMsg($("#openBankCard"), "填写开户银行账号信息已存在，请重新填写", false);	
						flag = true;
					}
					if(data.retCode == "1001"){
						showErrMsg($("#openBankCard"), data.retMsg, false);	
						flag = true;
					}
				});
			}
			if(flag) return;
			
			var succCallBack=function(data){
				if(data.retCode == "0000"){
					alert("修改用户信息成功");
				}else{
					alert("修改用户信息失败");
				}
			};
			
			var errCallBack = function(){
				alert("修改用户信息失败");
			};
			
			doAjaxRequest("${ctx}/console/usr_editUserInfo.action", param, succCallBack, errCallBack);
		}
	}
</script>
</html>
