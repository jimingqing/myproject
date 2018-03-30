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
          	新增用户信息
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
										<span class="errflag">*</span>用户密码：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="password" name="pwd" id="pwd" class="txt_input" value="">
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
								
								<tr align="left" valign="bottom" class="black_text_k5" style="display: none;">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>开户银行：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select name="openBankName" id="openBankName"></select>
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5" style="display: none;">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										<span class="errflag">*</span>开户银行账号：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="openBankCard" id="openBankCard" class="txt_input" size="30" value="">
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

   $("#merId").append("<option value='${userInfoVo.merId}'>${userInfoVo.merName}</option>");
/**
 function queryMerInfo()
 {
	 doAjaxRequest("${ctx}/console/mer_qryMerInfo.action", null, queryMerInfoSucess);
 }

 function queryMerInfoSucess(retData)
 {
	    if(retData.retCode=="0000")
	    {
		    var  content =  retData.content;
		    for(var i = 0 ; i <content.length; i++)
		    {
			    
		        $("#merinfo").append("<option value="+content[i].id+">"+content[i].merName+"</option>");
		    }
	    }
	    else
	    {
		     alert(retData.retMsg);
	    }
 }
 **/


	$(function(){
		//queryMerInfo();	
		initMerInfo();
		initProvCityArea('1', '', 'provId');
		//initBankInfo();
		$("#phoneNo").blur(function(){
			if($(this).val()==""){
				return;
			}
			
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
					showErrMsg($("#phoneNo"), "手机号码已注册，请更换手机号码");		
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
			var cityId = $(this).val();
			if(cityId==""){
				$("#cityId").html("<option value=''>请选择地区...</option>");
			}else{
				initProvCityArea('3', cityId, 'areaId');
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
				}else{
					if(level==3){
						$("#areaId").hide();
					}
				}
				
			}
		};
		
		doAjaxRequest("${ctx}/console/pc_qryProvCityInfo.action", param, succCallBack);
	}
	
	function initBankInfo(){
		var succCallBack=function(data){
			$("#openBankName").html("");
			if(data.retCode == "0000"){
				if(data.content.length > 0){
					var html = "<option value=''>请选择银行...</option>";
					$.each(data.content, function(index, item){
						html+="<option value='"+item.id+"'>"+item.bankName+"</option>";
					})
					$("#openBankName").html(html);
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
		var openBankName = $("#openBankName").val();
		var openBankCard = $("#openBankCard").val();
		
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
		/**
		if(isEmpty(openBankName)){
			showErrMsg($("#openBankName"), "请选择开户银行");
			$("#openBankName").focus();
			return;
		}
		if(isEmpty(openBankCard)){
			showErrMsg($("#openBankCard"), "请填写开户银行账号");
			$("#openBankCard").focus();
			return;
		}
		*/
		if(confirm("确认添加用户信息！")){
			var param = {};
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
			param.openBankName =  openBankName ;
			param.openBankCard =  openBankCard ;
			param.pwd = $("#pwd").val();
			if($("#merId1").val()==null)
			{
			  param.merId = $("#merId").val();
			}
			else
			{
				 param.merId = $("#merId1").val();
			}
			
			var succCallBack=function(data){
				if(data.retCode == "0000"){
					alert("新增用户信息成功");
				}else{
					alert("新增用户信息失败");
				}
			};
			
			var errCallBack = function(){
				alert("新增用户信息失败");
			};
			
			doAjaxRequest("${ctx}/console/usr_addUserInfo.action", param, succCallBack, errCallBack);
		}
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
