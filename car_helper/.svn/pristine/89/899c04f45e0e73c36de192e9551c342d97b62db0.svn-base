<%@page import="com.yrtech.wx.capp.framework.core.web.ProductInit"%>
<%@page import="com.yrtech.wx.capp.portal.util.Constants"%>
<%@page import="com.yrtech.wx.capp.framework.core.cache.DataCacheManager"%>
<%@page import="com.yrtech.wx.capp.portal.model.AuthInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/import.jsp"%>
<html>
<head>
<title>易收付商务管理控台</title>
<%@ include file="/include/main_resources.jsp" %>
</head>

<%
/**ProductInit.initOperAuthInfo();
Map<Integer, AuthInfo> map = (Map<Integer, AuthInfo>)DataCacheManager.get(Constants.CACHE_KEY_AUTHINFO);
List<AuthInfo> oneLevelList = new ArrayList<AuthInfo>();
for(AuthInfo o:map.values()){
	if(o.getAuthLevel()==1){
		oneLevelList.add(o);
	}
}
request.setAttribute("one_level", oneLevelList);*/
%>

<body style="margin:0;">
<jsp:useBean id="now" class="java.util.Date" />
	<form name="form_1" method="post">

<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="262" height="62" align="left" valign="bottom" background="${ctx }/ui/images/background_process_background.gif"><img src="${ctx }/ui/images/background_process_logo.gif" width="262" height="62"></td>
    <td align="center" valign="bottom" background="${ctx }/ui/images/background_process_background.gif"><table width="95%"  border="0" cellspacing="2" cellpadding="0">
						<tr>
							<td align="right" class="aside">
								商户名：<c:out value="${sessionScope.userInfoVo.merName}"/>&nbsp;&nbsp;&nbsp;&nbsp; 
								操作员：<c:out value="${sessionScope.userInfoVo.operCode}"/>&nbsp;&nbsp;&nbsp;&nbsp; 
								登录时间： <fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp; 
								<a class="aside" href="#" onClick="operLogout();">退出</a>
							</td>
						</tr>
						<tr>
							<td id="onelist" align="left" class="aside" colspan="2">
							</td>
						</tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="7" background="${ctx }/ui/images/background_process_top_line.gif"></td>
  </tr>
</table>

	</form>

</body>
<script language="javascript" type="text/javascript">
	$(document).ready(function() {
		//$(window.parent.document).find("#leftFrame").attr("src",$("#onelist a").eq(0).attr("href"));
	})
	function operLogout(){
		var succCallBack=function(data){
			parent.location.href = "${ctx}/login.jsp";
		};
		var errCallBack=function(data){
			parent.location.href = "${ctx}/login.jsp";
		};
		doAjaxRequest("${ctx}/console/operLogout.action", {}, succCallBack, errCallBack);
	}
</script>
</html>
