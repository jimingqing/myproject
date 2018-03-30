<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/import.jsp"%>
<html>
<head>
<title>易收付商务管理控台--修改商品</title>
<%@ include file="/include/main_resources.jsp"%>
<script src="${ctx}/ui/js/ajaxfileupload.js" type="text/javascript"></script>
<style type="text/css">
.ht-28{height:28px;}
</style>
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
          	修改商品信息
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
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										商品分类：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select name="catId" id="catId">
										</select>
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										商品名称：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="goodsName" id="goodsName" class="txt_input" value="">
									</td>
								</tr>
								
								<tr id="parent" align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										商品编号：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="goodsCode" id="goodsCode" class="txt_input" value="">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="top" bgcolor="#D9DFEE">
										商品描述：
									</td>
									<td width="77%" height="80" bgcolor="#FFFFFF">
										<textarea rows="10" cols="50" name="goodsDesc" id="goodsDesc" class="font-14" style="border: 1px solid #A9A9A9;"></textarea>
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										商品价格：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="price" id="price" class="txt_input" value="" size="10" > 元
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										库存数量：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input type="text" name="store" id="store" class="txt_input" value="" size="5" maxlength="10">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										状态标志：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select name="state" id="state">
											<option value="N">正常</option>
											<option value="C">关闭</option>
										</select>
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										是否上架：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<select name="marketable" id="marketable">
											<option value="Y">是</option>
											<option value="N" selected="selected">否</option>
										</select>
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="30" align="right" valign="bottom" bgcolor="#D9DFEE">
										有效时间：
									</td>
									<td width="77%" bgcolor="#FFFFFF">
										<input name="expDate" id="expDate" type="text" class="Wdate" size="25" value="" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
									</td>
								</tr>
								
								<tr align="left" valign="bottom" class="black_text_k5">
									<td width="23%" height="auto" align="right" valign="top" bgcolor="#D9DFEE">
										商品图片：
									</td>
									<td width="77%" bgcolor="#FFFFFF" style="padding:10px;">
										<p>小图：<input name="smallPicName" id="spicfile" type="file" class="txt_input ht-28" size="50" value="" onchange="previewImage(this, 'preview_s', 'img_s');">
											<br/><div id="preview_s"></div>
										</p>
										<p>大图：<input name="largePicName" id="lpicfile" type="file" class="txt_input ht-28" size="50" value="" onchange="previewImage(this, 'preview_l', 'img_l')">
											<br/><div id="preview_l"></div>
										</p>
									</td>
								</tr>

								<tr align="center" bgcolor="#FFFFFF" class="black_text_k5">
									<td height="42" colspan="2" align="center">
										<input type="button" value="提  交" class="btn2" onclick="actSubmit();">
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
		qryGoodsCat();
	})
	
	function initGoodsDetail(){
		var param = {};
		param.goodsId = "${param.goodsId}";
		var succCallBack=function(data){
			if(data.retCode == "0000"){
				var goods = data.content;
				$("#catId").val(goods.catId);
				$("#goodsName").val(goods.goodsName);
				$("#goodsCode").val(goods.goodsCode);
				$("#goodsDesc").text(goods.brief);
				$("#price").val(goods.priceStr);
				$("#store").val(goods.store);
				$("#state").val(goods.state);
				$("#marketable").val(goods.marketable);
				if(goods.expDate!=null){
					$("#expDate").val(goods.expDate.replace("T", " "));	
				}
				$("#preview_s").html("<img src='"+goods.smallPicName+"'/>");
				$("#preview_l").html("<img src='"+goods.largePicName+"'/>");
			}
		};
		
		doAjaxRequest("${ctx}/console/gd_qryGoodsDetail.action", param, succCallBack);
	}
	
	//得到图片的完整路径  
	function getFullPath(obj) {    
	    if (obj) {  
	        //ie  
	        if (window.navigator.userAgent.indexOf("MSIE") >= 1) {  
	            obj.select();  
	            return document.selection.createRange().text;  
	        }  
	        //firefox  
	        else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {  
	            if (obj.files) {  
	                return obj.files.item(0).getAsDataURL();  
	            }  
	            return obj.value;  
	        }  
	        return obj.value;  
	    }  
	}
	
	function imgFileUpload(obj){
		var img = $(obj).parent().find("div[name='imgDiv']").eq(0);
		$(obj).uploadPreview({ 
			width: 170, height: 170, imgDiv: $("#imgDiv"), imgType: ["bmp", "gif", "png", "jpg"] 
		});
	}
	
	function qryGoodsCat(){
		var succCallBack=function(data){
			$("#catId").html("");
			if(data.retCode == "0000"){
				if(data.content.length > 0){
					var html = "<option value=''>请选择...</option>";
					$.each(data.content, function(index, item){
						html+="<option value='"+item.id+"'>"+item.catName+"</option>";
					})
					$("#catId").html(html);
					initGoodsDetail();
				}
				
			}
		};
		
		doAjaxRequest("${ctx}/console/gd_qryGoodsCat.action", {}, succCallBack);
	}
	
	function actSubmit(){
		var catId = $("#catId").val();
		var goodsName = $("#goodsName").val();
		var goodsCode = $("#goodsCode").val();
		var goodsDesc = $("#goodsDesc").val();
		var state = $("#state").val();
		var price = $("#price").val();
		var store = $("#store").val();
		var marketable = $("#marketable").val();
		var expDate = $("#expDate").val();
		var goodsDesc = $("#goodsDesc").val();
		
		if(isEmpty(catId)){
			showErrMsg($("#catId"), "请选择商品分类");
			$("#catId").focus();
			return;
		}
		if(isEmpty(goodsName)){
			showErrMsg($("#goodsName"), "请填写商品名称");
			$("#goodsName").focus();
			return;
		}
		if(isEmpty(goodsCode)){
			showErrMsg($("#goodsCode"), "请填写商品编号");
			$("#goodsCode").focus();
			return;
		}
		if(isEmpty(price)){
			showErrMsg($("#price"), "请填写商品价格");
			$("#price").focus();
			return;
		}
		if(isEmpty(store)){
			showErrMsg($("#store"), "请填写库存数量");
			$("#store").focus();
			return;
		}
		if(!isNumber(store)){
			showErrMsg($("#store"), "请正确填写库存数量");
			$("#store").focus();
			return;
		}
		
		if(confirm("确认修改商品信息！")){
			var param = {};
			param.goodsId = "${param.goodsId}";
			param.catId = catId;
			param.goodsName = goodsName;
			param.goodsCode = goodsCode;
			param.goodsDesc = goodsDesc;
			param.state = state;
			param.price = price;
			param.store = store;
			param.marketable = marketable;
			param.expDate = expDate;
			param.goodsDesc = goodsDesc;
			
			var succCallBack=function(data){
				if(data.retCode == "0000"){
					alert("修改商品信息成功");
				}else{
					alert("修改商品信息失败");
				}
			};
			
			var errCallBack = function(){
				alert("修改商品信息失败");
			};
			
			doAjaxRequestWithFile("${ctx}/console/gd_editGoods.action", param, ["spicfile","lpicfile"], succCallBack, errCallBack);
		}
	}
	
	//图片上传预览    IE是用了滤镜。
    function previewImage(file, imgdiv, img)
    {
      var MAXWIDTH  = 260; 
      var MAXHEIGHT = 180;
      var imgType = ["gif", "jpeg", "jpg", "bmp", "png"];
      var div = document.getElementById(imgdiv);
      if (!RegExp("\.(" + imgType.join("|") + ")$", "i").test(file.value.toLowerCase())) {
          alert("图片类型必须是" + imgType.join("，") + "中的一种");
          file.value = "";
          return false;
      }
      if (file.files && file.files[0])
      {
          div.innerHTML ="<img id='"+img+"' style='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);'>";
          var img = document.getElementById(img);
          img.onload = function(){
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            img.width  =  rect.width;
            img.height =  rect.height;
//             img.style.marginLeft = rect.left+'px';
            //img.style.marginTop = rect.top+'px';
          }
          var reader = new FileReader();
          reader.onload = function(evt){img.src = evt.target.result;}
          reader.readAsDataURL(file.files[0]);
      }
      else //兼容IE
      {
        var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
        file.select();
        window.parent.document.body.focus();
        var src = document.selection.createRange().text;
        div.innerHTML ="<img id='"+img+"' style='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);'>";
        var img = document.getElementById(img);
        img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
        var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
        status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
        div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;"+sFilter+src+"\"'></div>";
      }
    }
    function clacImgZoomParam( maxWidth, maxHeight, width, height ){
        var param = {top:0, left:0, width:width, height:height};
        if( width>maxWidth || height>maxHeight )
        {
            rateWidth = width / maxWidth;
            rateHeight = height / maxHeight;
            
            if( rateWidth > rateHeight )
            {
                param.width =  maxWidth;
                param.height = Math.round(height / rateWidth);
            }else
            {
                param.width = Math.round(width / rateHeight);
                param.height = maxHeight;
            }
        }
        
        param.left = Math.round((maxWidth - param.width) / 2);
        param.top = Math.round((maxHeight - param.height) / 2);
        return param;
    }
</script>
</html>

