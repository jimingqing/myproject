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
          	修改权限信息
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
									<td width="15%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										权限级别：
									</td>
									<td width="35%" bgcolor="#FFFFFF">
										<select id="authLevel" name="authLevel">
											<option value="all">全部</option>
											<option value="1">一级</option>
											<option value="2">二级</option>
											<option value="3">三级</option>
										</select>
									</td>
									<td width="15%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										状态：
									</td>
									<td width="35%" bgcolor="#FFFFFF">
										<select id="state" name="state">
											<option value="all">全部</option>
											<option value="N">正常</option>
											<option value="C">关闭</option>
										</select>
									</td>
								</tr>
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="15%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										权限名称：
									</td>
									<td width="35%" bgcolor="#FFFFFF" colspan="3">
										<input type="text" id="authDesc" name="authDesc" class="txt_input" size="30" value="">	
									</td>
								</tr>

								<tr align="center" bgcolor="#FFFFFF" class="black_text_k5">
									<td height="42" colspan="4" align="center">
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
		<table id="header" width="auto" border=0 cellspacing="1" cellpadding="0" bgcolor="#C0C9E0" class="List">
			<thead>
			<tr>
				<td width="30" height="45" class="div">序号</td>
				<td width="100" class="div">权限名称</td>
				<td width="80" class="div">权限级别</td>
				<td width="80" class="div">顺序编号</td>
				<td width="100" class="div">父权限名称</td>
				<td width="130" class="div">是否对操作员开放</td>
				<td width="120" class="div">是否对商户开放</td>
				<td width="120" class="div">是否显示在控台</td>
				<td width="80" class="div">状态</td>
				<td width="100" class="div">访问地址</td>
				<td width="100" class="div">操作</td>
			</tr>
			<tr id="nodata" class="none">
				<td colspan="11" align="center" class="font14-bold">无数据</td>
			</tr>
			</thead>
			<tbody class="List" id="result">
			</tbody>
		</table>
		<br/>
		
	</div>
</body>

<script type="text/javascript">
	
	$(function(){
		qryData();
	})
	
	function actsubmit(){
		qryData();
	}
	
	function qryData(){
		$("#nodata").hide();
		var param = {};
		param.authLevel = $("#authLevel").val();
		param.state = $("#state").val();
		param.authDesc = $("#authDesc").val();
		var succCallBack=function(data){
			$("#result").html("");
			if(data.retCode == "0000"){
				if(data.content.length > 0){
					var html = "";
					$.each(data.content, function(index, item){
						html += "<tr>";
						html += "<td align='center'>"+(index+1)+"</td>";
						html += "<td align='left'>"+item.authDesc+"</td>";
						if(item.authLevel==1){
							html += "<td align='center'>一级</td>";
						}else if(item.authLevel==2){
							html += "<td align='center'>二级</td>";
						}else{
							html += "<td align='center'>三级</td>";
						}
						html += "<td align='center'>"+item.porder+"</td>";
						html += "<td align='left'>"+item.parentName+"</td>";
						if(item.isDefault == "Y"){
							html += "<td align='center'>是</td>";
						}else{
							html += "<td align='center'>否</td>";
						}
						if(item.isToMer == "Y"){
							html += "<td align='center'>是</td>";
						}else{
							html += "<td align='center'>否</td>";
						}
						if(item.isDisplay == "Y"){
							html += "<td align='center'>是</td>";
						}else{
							html += "<td align='center'>否</td>";
						}
						if(item.state == "N"){
							html += "<td align='center'>正常</td>";
						}else{
							html += "<td align='center'>关闭</td>";
						}
						html += "<td align='center'>"+item.action+"</td>";
						html += "<td width='150' align='left'><a href='${ctx}/admin/editAuthDetailPage.action?authId="+item.id+"' onclick=''>修改</a> | <a href='javascript:;' onclick='delAuth(this, \""+item.id+"\")'>删除</a></td>";
						html += "</td>";
					})
					$("#result").html(html);
				}else{
					$("#nodata").show();
				}
				
			}
		};
		
		doAjaxRequest("${ctx}/console/au_qryAuth.action", param, succCallBack);
	}
	
	function delAuth(obj, id){
		if(confirm("确认删除权限！")){
			var param = {};
			param.authId = id;
			
			var succCallBack=function(data){
				if(data.retCode == "0000"){
					$(obj).parent().parent().remove();
					alert("删除成功！");
				}else{
					alert("删除失败！");
				}
			};
			doAjaxRequest("${ctx}/console/au_removeAuth.action", param, succCallBack);
		}
	}
</script>
</html>
