<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/import.jsp"%>

<html>
<head>
<title>易收付商务管理控台</title>
<%@ include file="/include/main_resources.jsp"%>
</head>
<style>
.blue_kuang { border:1px; }
.blue_kuang td{ height:30px;background:#FFFFFF; padding-left:5px;font-size:12px; }
.blue_kuang .title { background:#D9DFEE; }
.border-none { border: 0; }
.tab_title { font-size:14px; font-weight:bold; padding:5px 10px;}
</style>
<body leftmargin="0" topmargin="0">

	<div style="height:10px; width:auto;padding:10px;">
		<div class="tab_title">商户操作员信息</div>
		<table id="header" width="100%" border=0 cellspacing="1" cellpadding="0" class="blue_kuang">
			
			<tr>
				<td width="15%" align="right" class="title">商户号：</td>
				<td width="35%" id="merCode">${userInfoVo.merCode }</td>
				<td width="15%" align="right" class="title">商户名称：</td>
				<td width="35%" id="merName">${userInfoVo.merName }</td>
			</tr>
			<tr>
				<td width="15%" align="right" class="title">操作员号：</td>
				<td width="35%" id="operCode"></td>
				<td width="15%" align="right" class="title">操作员状态：</td>
				<td width="35%" id="operStat"></td>
			</tr>
			<tr>
				<td width="15%" align="right" class="title">操作员姓名：</td>
				<td width="35%" id="operName"></td>
				<td width="15%" align="right" class="title">操作员电话：</td>
				<td width="35%" id="operTel"></td>
			</tr>
			<tr>
				<td width="15%" align="right" class="title">操作员手机：</td>
				<td width="35%" id="operMp"></td>
				<td width="15%" align="right" class="title">操作员email：</td>
				<td width="35%" id="operEmail"></td>
			</tr>
			
			<tr >
				<td width="15%" align="right" class="title" >权限组：</td>
				<td width="35%" id="grpId" colspan="3"></td>
			</tr>
			<tr style="display: none;">
				<td width="15%" align="right" class="title">权限信息：</td>
				<td width="35%" id="authInfo1" colspan="3"></td>
			</tr>
		</table>
		
		<p style="width:100%;text-align:center;">
		<c:if test="${param.type != '1' }">
			<input type="button" value="返  回" class="btn2" onclick="javascript:history.go(-1);">
		</c:if>
		</p>
	</div>
</body>

<script type="text/javascript">
	
	$(function(){
		initMerOperInfo();
	})
	
	function initMerOperInfo(){
		var param = {};
		param.operId = "${param.operId}";
		var succCallBack=function(data){
			if(data.retCode == "0000"){
				var oper = data.content;
				$("#operCode").text(oper.operCode);
				if(oper.operName!=null){
					$("#operName").text(oper.operName);
				}
				if(oper.operTel!=null){
					$("#operTel").text(oper.operTel);
				}
				if(oper.operMp!=null){
					$("#operMp").text(oper.operMp);
				}
				if(oper.operEmail!=null){
					$("#operEmail").text(oper.operEmail);
				}
				if(oper.operState!=null){
					if(oper.operState=="N"){
						$("#operStat").text("正常");
					}else{
						$("#operStat").text("关闭");
					}
				}
				if(oper.grpId=="00"){
					$("#grpId").text("管理组");
				}else if(oper.grpId=="01"){
					$("#grpId").text("财务经办组");
				}else if(oper.grpId=="02"){
					$("#grpId").text("财务审核组");
				}else if(oper.grpId=="03"){
					$("#grpId").text("运行客户组");
				}
				initGrpAuth(oper.grpId);
			}
		};
		
		doAjaxRequest("${ctx}/console/mer_qryMerOperInfoDetail.action", param, succCallBack);
	}
	
	function initGrpAuth(grpId){
		var param = {};
		param.grpId = grpId;
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
				
				$("#authInfo").html(html);
			}
		};
		
		doAjaxRequest("${ctx}/console/au_qryAuthInfoByGrpId.action", param, succCallBack);
	}
	
</script>
</html>
