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
          	订单审核
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
									<td width="10%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										商户：
									</td>
									<td width="20%" bgcolor="#FFFFFF">
										${userInfoVo.merName }
									</td>
									<td width="10%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										经办时间：
									</td>
									<td width="20%" bgcolor="#FFFFFF">
										<input name="startDate" id="startDate" type="text" class="txt_input" size="8" value="" readonly="readonly" onClick="WdatePicker()">
										-
										<input name="endDate" id="endDate" type="text" class="txt_input" size="8" value="" readonly="readonly" onClick="WdatePicker()">	
									</td>
									<td width="10%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										状态：
									</td>
									<td width="20%" bgcolor="#FFFFFF">
										<select id="procState" name="procState">
											<option value="">全部</option>
											<option value="H" selected="selected">待审核</option>
											<option value="A">审核通过</option>
											<option value="F">审核失败</option>
										</select>
									</td>
								</tr>
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="10%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										付款用户类型：
									</td>
									<td width="20%" bgcolor="#FFFFFF" colspan="5">
										<select id="userType" name="userType">
											<option value="">全部</option>
											<option value="R">注册用户</option>
											<option value="U">非注册用户</option>
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
		<table id="header" width="1300" border=0 cellspacing="1" cellpadding="0" bgcolor="#C0C9E0" class="List">
			<thead>
			<tr>
				<td width="50" height="45" class="div" valign="middle">
					<label>全选<input type="checkbox" id="all" onclick="selectAll(this)"/></label>
				</td>
				<td width="120" class="div">商户名称</td>
				<td width="100" class="div">操作员编号</td>
				<td width="120" class="div">提交时间</td>
				<td width="80" class="div">订单金额</td>
				<td width="80" class="div">审核状态</td>
				<td width="100" class="div">用户类型</td>
				<td width="100" class="div">用户编号</td>
				<td width="80" class="div">用户姓名</td>
				<td width="80" class="div">银行名称</td>
				<td width="80" class="div">银行账号</td>
				<td width="120" class="div">订单描述</td>
			</tr>
			<tr id="nodata" class="none">
				<td colspan="11" align="center" class="font14-bold">无数据</td>
			</tr>
			</thead>
			<tbody class="List" id="result">
			</tbody>
		</table>
		
		<div id="pager">
			<%@ include file="pager.jsp" %>
		</div>
		
		<div id="action" class="pd-15 none">
			<input type="button" value="审核通过" class="btn2" onclick="audit()">
			<input type="button" value="审核不通过" class="btn2" onclick="cancel()">
			<!-- span class="mgl-20">不通过原因：<input type="text" value="" class="txt_input"></span-->
		</div>
	</div>
</body>

<script type="text/javascript">
	
	$(function(){
		qryData();
		
	})
	
	function actsubmit(){
		curpage = 1;
		qryData();
	}
	
	function audit(){
		if($("#result input[name='handleId']:checked").length == 0){
			alert("请选择要审核的订单记录");
			return;
		}
		if(confirm("确认审核通过")){
			var handleIds = "";
			$.each($("#result input[name='handleId']:checked"),function(index,item){
				handleIds += $(item).val()+",";
			})
			var param = {};
			param.type = "1";
			param.handleIds = handleIds;
			var succCallBack=function(data){
				if(data.retCode == "0000"){
					alert("审核订单成功");
					qryData();
				}else{
					alert("审核订单失败");
				}
			}
			doAjaxRequest("${ctx}/console/ha_auditOrder.action", param, succCallBack);
		}
		
	}
	
	function cancel(){
		if($("#result input[name='handleId']:checked").length == 0){
			alert("请选择要审核的订单记录");
			return;
		}
		if(confirm("确认审核通过")){
			var handleIds = "";
			$.each($("#result input[name='handleId']:checked"),function(index,item){
				handleIds += $(item).val()+",";
			})
			var param = {};
			param.type = "0";
			param.handleIds = handleIds;
			var succCallBack=function(data){
				if(data.retCode == "0000"){
					alert("审核订单成功");
					qryData();
				}else{
					alert("审核订单失败");
				}
			}
			doAjaxRequest("${ctx}/console/ha_auditOrder.action", param, succCallBack);
		}
	}
	
	function selectAll(obj){
		//alert($(obj).attr("checked"))
		if($(obj).attr("checked")){
			$.each($("#result input[name='handleId']"),function(index,item){
				$(item).attr("checked",true);
			})
		}else{
			$.each($("#result input[name='handleId']"),function(index,item){
				$(item).attr("checked",false);
			})
		}
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
	}
	
	function qryData(){
		$("#all").attr("checked", false);
		$("#nodata").hide();
		
		var param = {};
		param.userType = $("#userType").val();
		param.procState = $("#procState").val();
		param.startDate = $("#startDate").val();
		param.endDate = $("#endDate").val();
		param.curPage = curpage;
		var succCallBack=function(data){
			$("#result").html("");
			if(data.retCode == "0000"){
				if($("#procState").val()=="H"){
					$("#action").show();
				}else{
					$("#action").hide();
				}
				initPager(data.content.pager);
				if(data.content.datalist.length > 0){
					var html = "";
					$.each(data.content.datalist, function(index, item){
						html += "<tr>";
						html += "<td align='center'><input type='checkbox' name='handleId' value='"+item.id+"'/></td>";
						html += "<td align='left'>${userInfoVo.merName }</td>";
						html += "<td align='left'>"+item.operCode+"</td>";
						html += "<td align='center'>"+item.handleDate+"</td>";
						html += "<td align='right'>"+item.procCont8+"</td>";
						if(item.procStat=="H"){
							html += "<td align='center'>待审核</td>";
						}else if(item.procStat=="A"){
							html += "<td align='center'>审核通过</td>";
						}else{
							html += "<td align='center'>审核失败</td>";
						}
						if(item.procCont2 == "R"){
							html += "<td align='center'>注册用户</td>";
						}else{
							html += "<td align='center'>非注册用户</td>";
						}
						html += "<td align='center'>"+item.procCont3+"</td>";
						html += "<td align='center'>"+item.procCont4+"</td>";
						html += "<td align='center'>"+item.procCont6+"</td>";
						html += "<td align='center'>"+item.procCont7+"</td>";
						html += "<td align='left'>"+item.procCont9+"</td>";
						html += "</td>";
					})
					$("#result").html(html);
				}else{
					$("#nodata").show();
				}
			}else{
				$("#nodata").show();
			}
		};
		
		doAjaxRequest("${ctx}/console/ha_qryOrderHandle.action", param, succCallBack);
	}
	
	function resetPwd(userid){
		if(confirm("确认重置用户密码")){
			var param = {};
			param.userId = userid;
			var succCallBack=function(data){
				if(data.retCode == "0000"){
					alert("重置用户密码成功");
				}else{
					alert("重置用户密码失败");
				}
			};
			doAjaxRequest("${ctx}/console/usr_resetUserPwd.action", param, succCallBack);
		}
	}
</script>
</html>
