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
          	订单查询
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
		<form>
		
		
		                     <tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										商户名称：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<c:if test="${userInfoVo.merCode eq '880001' }">
											<select id="merId"></select>
										</c:if>
										
										<c:if test="${userInfoVo.merCode ne '880001' }">
										     <select id="merId1">
										     <option value="${userInfoVo.merId}">${userInfoVo.merName }
										     </option>
										     </select>
										</c:if>
									</td>
								</tr>
								
								
							<!--					<tr align="left" valign="bottom" class="black_text_k5">
									<td width="10%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										商户：
									</td>
									<td width="20%" bgcolor="#FFFFFF"><%--
										<input type="text" id="deptName" name="deptName" class="txt_input" value="">	
									--%>
									
                                     <select id="deptName" name="deptName">
                                     </select>
									 
									</td> -->		
									<%--<td width="10%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										所在城市、区县：
									</td>
									<td width="20%" bgcolor="#FFFFFF">
										<select id="cityId"></select>
										<select id="areaId"></select>
									</td>
									--%><%--<td width="10%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										手机号码：
									</td>
									<td width="20%" bgcolor="#FFFFFF">
										<input type="text" id="payUserCode" name="payUserCode" class="txt_input" value="">
									</td>
								--%></tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="10%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										提交时间：
									</td>
									<td width="20%" bgcolor="#FFFFFF">
										<input name="startDate" id="startDate" type="text" class="txt_input" size="10" value="" readonly="readonly" onClick="WdatePicker()">
										-
										<input name="endDate" id="endDate" type="text" class="txt_input" size="10" value="" readonly="readonly" onClick="WdatePicker()">
									</td>
									</tr>
									<tr>
									<td width="10%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										订单金额：
									</td>
									<td width="20%" bgcolor="#FFFFFF">
										<select id="qryType">
											<option value="eq">等于</option>
											<option value="gt">大于</option>
											<option value="le">小于</option>
										</select>
										<input type="text" id="ordAmt" name="ordAmt" class="txt_input" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="10%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										交易状态：
									</td>
									<td width="20%" bgcolor="#FFFFFF">
										<select id="ordState" name="ordState">
											<option value="">全部</option>
											<option value="I">初始</option>
											<option value="S">成功</option>
											<option value="C">撤销</option>
											<option value="F">失败</option>
										</select>
									</td>
									<%--<td width="10%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										交易方式：
									</td>
									<td width="20%" bgcolor="#FFFFFF">
										<select id="ordOrg" name="ordOrg">
											<option value="">全部</option>
											<option value="C">客户端</option>
											<option value="S">控台导入</option>
										</select>
									</td>
									<td width="10%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										是否发货：
									</td>
									<td width="20%" bgcolor="#FFFFFF">
										<select id="shipState" name="shipState">
											<option value="">全部</option>
											<option value="Y">是</option>
											<option value="N">否</option>
										</select>
									</td>
								</tr>--%>

								<tr align="center" bgcolor="#FFFFFF" class="black_text_k5">
									<td height="42" colspan="6" align="center">
										<input type="button" value="查  询" class="btn2" onclick="actsubmit()">
										<input type="reset" value="重  置" class="btn2 mgl-20">
									</td>
								</tr>
							</table>
							</form>
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
		<table id="header" width="550" border=0 cellspacing="1" cellpadding="0" bgcolor="#C0C9E0" class="List">
			<thead>
			<tr>
				<td width="30" height="45" class="div">序号</td><%--
				  <td width="150" class="div">所在城市/区县</td>
				 --%><td width="100" class="div">订单编号</td>
			       <td width="80" class="div">手机号码</td>
				  <td width="60" class="div">交易金额</td>
				<td width="150" class="div">提交时间</td>
				<td width="60" class="div">交易状态</td>
				<td width="60" class="div">订单明细</td>
			</tr>
			<tr id="nodata" class="none">
				<td colspan="12" align="center" class="font14-bold">无数据</td>
			</tr>
			</thead>
			<tbody class="List" id="result">
			</tbody>
		</table>
		
		<div id="pager">
			<%@ include file="pager.jsp" %>
		</div>
		<p id="total_info" style="clear:both;"></p>
		<br/>
	</div>
</body>

<script type="text/javascript">
   

 
	$(function(){
		initMerInfo();
		initProvCityArea('2', '${userInfoVo.merProv}', 'cityId');
		$("#areaId").html("<option value=''>请选择地区...</option>");
		//initMerInfo();
		qryData();
	//	qryData();
		$("#cityId").change(function(){ 
			var city = $(this).val();
			if(city==""){
				$("#areaId").html("<option value=''>请选择地区...</option>");
			}else{
				initProvCityArea('3', city, 'areaId');
			}
		})
	})
	
/***
	$(function(){
		$("#deptName").append("<option value='${userInfoVo.merId}'>${userInfoVo.merName}</option>");
		
		var param = {};
		  doAjaxRequest("${ctx}/console/mer_qryMerInfo.action", param, qryMerInfosuccCallBack);
		})
		
		function qryMerInfosuccCallBack(retData)
		 {
		        var content = retData.content;
		       for(var i = 0 ; i <content.length ; i ++)
		       {
		           $("#deptName").append("<option value="+content[i].id+">"+content[i].merName+"</option>");
		       }
		 }
		 
		 })
	***/
	
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

	/**
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
	}*/

	function qryData(){
		$("#nodata").hide();
		var param = {};
		param.merId = $("#deptName").val();
		param.ordId = $("#ordId").val();
		param.ordState = $("#ordState").val();
		param.payUserCode = $("#payUserCode").val();
		param.payUserName = $("#payUserName").val();
		param.ordOrg = $("#ordOrg").val();
		param.startDate = $("#startDate").val();
		param.endDate = $("#endDate").val();
		param.cityId = $("#cityId").val();
		param.areaId = $("#areaId").val();
		param.qryType = $("#qryType").val();
		param.ordAmt = $("#ordAmt").val();
		//param.shipState = $("#shipState").val();


		if( $("#merId").val()==null ||  $("#merId").val()=="")
				{
					param.merId = $("#merId1").val();

				//	alert( $("#merId1").val());
				}
				else
				{
		        	param.merId =  $("#merId").val();
				}
				
		param.curPage = curpage;
		var succCallBack=function(data){
			$("#result").html("");
			if(data.retCode == "0000"){
				initPager(data.content.pager);
				if(data.content.datalist.length > 0){
					var html = "";
					$.each(data.content.datalist, function(index, item){
						//alert("aaaaaa");
						html += "<tr>";
						html += "<td align='center'>"+(data.content.pager.startRow+index+1)+"</td>";
						//html += "<td align='left'>"+item.userProv+item.userCity+item.userArea+"</td>";
						html += "<td align='center'>"+item.ordId+"</td>";
						html += "<td align='center'>"+item.userCode+"</td>";
						html += "<td align='right'>"+item.ordAmtStr+"</td>";
						html += "<td align='center'>"+item.createTime+"</td>";
						html += "<td align='center'>"+item.ordState+"</td>";
						html += "<td align='left'><a href='${ctx}/admin/qryOrdLogDetailPage.action?ordId="+item.ordId+"&merId="+item.merId+"'>订单明细</a></td>";
						html += "</tr>";
					})
					$("#result").html(html);
				}else{
					$("#nodata").show();
				}
				
				$("#total_info").html("总笔数：<span style='font-size:14px;'>"+data.content.pager.totalRows+"</span>, &nbsp;&nbsp;&nbsp;&nbsp;总金额：<span style='font-size:14px;'>"+fmoney(data.content.totalAmt,2)+"</span>元");
				
			}else{
				$("#nodata").show();
			}
		};
		
		
		doAjaxRequest("${ctx}/console/ord_qryOrdInfo.action", param, succCallBack);
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

	function actsubmit(){
		curpage = 1;
		qryData();
	}
</script>
</html>
