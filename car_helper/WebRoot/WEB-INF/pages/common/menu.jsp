<%@page import="com.yrtech.wx.capp.portal.util.Constants"%>
<%@page import="com.yrtech.wx.capp.framework.core.cache.DataCacheManager"%>
<%@page import="com.yrtech.wx.capp.portal.model.AuthInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/import.jsp"%>
<html>
<head>
<title>车商助手商务管理控台</title>
<%@ include file="/include/main_resources.jsp"%>
</head>

<%
List<AuthInfo> list = (List<AuthInfo>)session.getAttribute(Constants.SESSION_AUTH_INFO);
List<AuthInfo> twoLevelList = new ArrayList<AuthInfo>();

List<AuthInfo> threeLevelList = new ArrayList<AuthInfo>();
for(AuthInfo o: list){
	if(o.getAuthLevel()==2){
		twoLevelList.add(o);
	}
	else if(o.getAuthLevel()==3){
		threeLevelList.add(o);
	}
}
request.setAttribute("two_level", twoLevelList);
request.setAttribute("three_level", threeLevelList);
%>

<body leftmargin="0" topmargin="0" bottommargin="0" rightmargin="0">

	<!-- check the bean menu_auth is exist -->

	<table width="165" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="165" height="15" align="left" valign="top" background="${ctx }/ui/images/background_left_background.gif">
				<img src="${ctx }/ui/images/background_left_top.gif" width="170" height="15">
			</td>
		</tr>
		<tr>
			<td height="392" align="center" valign="top" background="${ctx }/ui/images/background_left_background.gif">
				<table width="150" border="0" cellspacing="4" cellpadding="0">
					<tr>
						<td align="left" valign="top">
						<c:forEach var="item"  items="${two_level}" varStatus="status">
							<div id='KB${status.index+1 }Parent' class='parent' style='height:20px;padding-top:10px;padding-left:10px;'>
								<a onclick="expandIt('KB${status.index+1 }');" class="left_menu_top" style="line-height:25px;font-size:14px;"> 
									<img src='${ctx}/ui/images/plus.gif' style="vertical-align:top;padding-top:5px;" />${item.authDesc }
								</a>
							</div>
							<c:forEach var="item1" items="${three_level}" varStatus="status1">
								<c:if test="${item1.parentId eq item.id }">
								<div name='KB${status.index+1 }child'  class="child" style="padding-top:5px;padding-left:0px;display:none;">
									<a href='${ctx }/admin/${item1.action}' target='mainFrame' class="menu">${item1.authDesc }</a>
								</div>
								</c:if>
							</c:forEach>
						</c:forEach>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="6" valign="top">
				<img src="${ctx }/ui/images/background_left_bottom.gif" width="170" height="12">
			</td>
		</tr>
	</table>
</body>
<script language="javascript" type="text/javascript">
	function expandIt(id) {
		if ($("div[name='" + id + "child']:eq(0)").is(":hidden")) {
			$.each($("div[name='"+id+"child']"),function(index, item){
				$(item).show();
			});
		} else {
			$.each($("div[name='"+id+"child']"),function(index, item){
				$(item).hide();
			});
		}
	}
</script>
</html>
