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
          	修改商品分类信息
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
										商品分类名称：
									</td>
									<td width="35%" bgcolor="#FFFFFF">
										<input type="text" id="catName" name="catName" class="txt_input" size="30" value="">	
									</td>
								</tr>
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="15%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										商品分类描述：
									</td>
									<td width="35%" bgcolor="#FFFFFF">
										<input type="text" id="catDesc" name="catDesc" class="txt_input" size="30" value="">	
									</td>
								</tr>
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="15%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										顺序编号：
									</td>
									<td width="35%" bgcolor="#FFFFFF">
										<input type="text" id="porder" name="porder" class="txt_input" size="10" value="">	
									</td>
								</tr>
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="15%" height="28" align="right" valign="bottom" bgcolor="#D9DFEE">
										状态：
									</td>
									<td width="35%" bgcolor="#FFFFFF">
										<select id="state" name="state">
											<option value="N">正常</option>
											<option value="C">关闭</option>
										</select>
									</td>
								</tr>

								<tr align="center" bgcolor="#FFFFFF" class="black_text_k5">
									<td height="42" colspan="2" align="center">
										<input type="button" value="提  交" class="btn2" onclick="editData()">
										<input type="button" value="返  回" class="btn2 mgl-20" onclick="javascript:history.go(-1);">
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
	
	$(function(){
		var param = {};
		param.catId = "${param.catId}";
		var succCallBack=function(data){
			if(data.retCode == "0000"){
				$("#catName").val(data.content.catName);
				$("#catDesc").val(data.content.catDesc);
				$("#porder").val(data.content.porder);
				$("#state").val(data.content.state);
			}
		};
		
		doAjaxRequest("${ctx}/console/gd_qryGoodsCatDetail.action", param, succCallBack);
	})
	
	function editData(){
		var catName = $("#catName").val();
		var porder = $("#porder").val();
		
		if(isEmpty(catName)){
			showErrMsg($("#catName"), "请填写分类名称");
			$("#catName").focus();
			return;
		}
		if(!isNumber(porder)){
			showErrMsg($("#porder"), "请填写顺序编号");
			$("#porder").focus();
			return;
		}
		
		if(confirm("确认修改商品分类！")){
			var param = {};
			param.catId = "${param.catId}";
			param.catName = $("#catName").val();
			param.catDesc = $("#catDesc").val();
			param.porder = $("#porder").val();
			param.state = $("#state").val();
			var succCallBack=function(data){
				if(data.retCode == "0000"){
					alert("修改商品分类信息成功");
				}else{
					alert("修改商品分类信息失败");
				}
			};
			
			doAjaxRequest("${ctx}/console/gd_editGoodsCat.action", param, succCallBack);
		}
	}
	
</script>
</html>
