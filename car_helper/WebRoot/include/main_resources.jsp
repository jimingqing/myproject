<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
 /**
  * Descr: 资源页面
  * Update: 
  */
%>

<link href="${ctx}/ui/css/style.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/ui/js/jquery-1.8.0.min.js" type="text/javascript"></script>
<script src="${ctx}/ui/js/main_top.js" type="text/javascript"></script>
<script src="${ctx}/ui/js/border.js" type="text/javascript"></script>
<script src="${ctx}/ui/js/left_menu.js" type="text/javascript"></script>
<script src="${ctx}/ui/js/jquery.blockUI.js" type="text/javascript"></script>
<script src="${ctx}/ui/js/common.js" type="text/javascript"></script>
<script src="${ctx}/ui/js/sorttable.js" type="text/javascript"></script>
<script src="${ctx}/ui/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctx}/ui/js/poshytip-1.2/jquery.poshytip.min.js" type="text/javascript"></script>

<script type="text/javascript">
	var ctx = "${ctx}";
	if("${sessionScope.userInfoVo.operCode }"==""){
		parent.location.href = "${ctx}/login.jsp";
	}
</script>
