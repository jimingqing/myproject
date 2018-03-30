<%@page import="com.yrtech.wx.capp.framework.core.web.ProductInit"%>
<%@page import="com.yrtech.wx.capp.portal.util.Constants"%>
<%@page import="com.yrtech.wx.capp.framework.core.cache.DataCacheManager"%>
<%@page import="com.yrtech.wx.capp.portal.model.AuthInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/import.jsp"%>
<html>
<head>
<title>易收付商务管理控台</title>
<%@ include file="/include/main_resources.jsp"%>
</head>

<%
ProductInit.initOperAuthInfo();
Map<Integer, AuthInfo> map = (Map<Integer, AuthInfo>)DataCacheManager.get(Constants.CACHE_KEY_AUTHINFO);
List<AuthInfo> oneLevelList = new ArrayList<AuthInfo>();
List<AuthInfo> twoLevelList = new ArrayList<AuthInfo>();
List<AuthInfo> threeLevelList = new ArrayList<AuthInfo>();
for(AuthInfo o:map.values()){
	if(o.getAuthLevel()==1){
		oneLevelList.add(o);
	}
	if(o.getAuthLevel()==2){
		twoLevelList.add(o);
	}
	else if(o.getAuthLevel()==3){
		threeLevelList.add(o);
	}
}
request.setAttribute("one_level", oneLevelList);
request.setAttribute("two_level", twoLevelList);
request.setAttribute("three_level", threeLevelList);
%>

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
          <td width="475" align="left" valign="bottom" class="black_blod" style="color:#fff;font-size:14px;font-weight:bold;">修改权限组</td>
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
										权限组名称：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select name="grpId" id="grpId">
											<option value="00">管理组</option>
											<option value="01">财务经办组</option>
											<option value="02">财务审核组</option>
											<option value="03">运行客户组</option>
										</select>
									</td>
								</tr>
								
								<tr id="authlist" align="left" valign="bottom" class="black_text_k5" style="TEXT-INDENT:0;">
									<td width="23%" height="30" align="right" valign="top" bgcolor="#D9DFEE">
										权限列表：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
									<c:forEach var="item1" items="${one_level}">
										<fieldset style="border:1px solid #999;margin:10px;padding:10px;">  
							                <legend><label><input type="checkbox" id="${item1.id}" name="" value="${item1.id}" style="vertical-align: bottom;" onclick="checkAuth(this,'1','${item1.id}')"/><c:out value="${item1.authDesc}"/></label></legend>  
							            	<c:forEach var="item2" items="${two_level}">
							            		<c:if test="${item2.parentId == item1.id}">
							            			<fieldset style="border:1px solid #999;margin:10px;padding:10px;float:left;min-height:150px;min-width:130px;">
							            				<legend><label><input type="checkbox" id="${item1.id}_${item2.id}" name="" value="${item2.id}" style="vertical-align: bottom;" onclick="checkAuth(this,'2', '${item1.id}_${item2.id}')"/><c:out value="${item2.authDesc}"/></label></legend>	
								            			<c:forEach var="item3" items="${three_level}">
								            				<c:if test="${item3.parentId == item2.id}">
								            					<div style="padding-top:5px;">
								            					<label style="padding-left:15px;margin-top:10px;"><input type="checkbox" id="${item1.id}_${item2.id}_${item3.id}" name="" value="${item3.id}" style="vertical-align: bottom;" onclick="checkAuth(this,'3', '${item1.id}_${item2.id}_${item3.id}')"/><c:out value="${item3.authDesc}"/></label>
																</div>
								            				</c:if>
								            			</c:forEach>
							            			</fieldset> 
							            		</c:if>
							            	</c:forEach>
							        	</fieldset> 
									</c:forEach>
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
	<br/>
	<br/>
</body>
<script type="text/javascript">

	$(function(){
		initGrpAuth();
		$("#grpId").change(function(){ 
			initGrpAuth();
		})
	})
	
	function initGrpAuth(){
		var succCallBack=function(data){
			if(data.retCode == "0000"){
				$.each(data.content, function(index,item){
					if(item.grpId==$("#grpId").val()){
						if(item.state=="N"){
							$("#authlist input[value='"+item.authId+"']").attr("checked",true);
						}else{
							$("#authlist input[value='"+item.authId+"']").attr("checked",false);
						}
					}
					
				})
			}
		};
		
		doAjaxRequest("${ctx}/console/au_qryGrpAuthInfo.action", {}, succCallBack);
	}

	function actSubmit(){
		var ids = "";
		$.each($("#authlist input:checked"), function(index,item){
			if(index==0){
				ids += $(item).val();
			}else{
				ids += ","+$(item).val();
			}
			
		})

		if(confirm("确认修改组权限！")){
			var param={};
			param.grpId = $("#grpId").val();
			param.authId = ids;
			
			var succCallBack=function(data){
				if(data.retCode == "0000"){
					alert("修改组权限信息成功");
				}else{
					alert("修改组权限信息失败");
				}
			};
			
			var errCallBack = function(){
				alert("修改组权限信息失败");
			};
			
			doAjaxRequest("${ctx}/console/au_editGrpAuthInfo.action", param, succCallBack, errCallBack);
		}
	}
	
	function checkAuth(obj, level, id){
		if($(obj).attr("checked")){
			$.each($("#authlist input[id^='"+id+"_']"),function(index, item){
				$(item).attr("checked", true);
			})
		}else{
			$.each($("#authlist input[id^='"+id+"_']"),function(index, item){
				$(item).attr("checked", false);
			})
		}
		
		if(level=="2"){
			var ids = id.split("_");
			$("#authlist input[id='"+ids[0]+"']").eq(0).attr("checked", true);
		}
		if(level=="3"){
			var ids = id.split("_");
			$("#authlist input[id='"+ids[0]+"']").eq(0).attr("checked", true);
			$("#authlist input[id='"+ids[0]+"_"+ids[1]+"']").eq(0).attr("checked", true);
		}
	}
</script>
</html>
