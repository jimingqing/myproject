<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
      <table id="pager_table"  width="95%" border="0" align="left" cellpadding="0" cellspacing="0">
        <tr>
          <td height="6"><img src="${ctx }/ui/images/spacer.gif" width="1" height="1" /></td>
        </tr>
        <tr>
          <td height="33">
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="right-font08">
              <tr>
                <td width="50%">第 <span class="right-text09" id="curpage">1</span> 页 | 共 <span class="right-text09" id="totalpage"></span> 页</td>
                <td width="49%" align="right">
                	[ <a href="javascript:;" class="right-font08" onclick="goFirst()">首页</a> | <a href="javascript:;" class="right-font08" onclick="goPrev()">上一页</a> | <a href="javascript:;" class="right-font08" onclick="goNext()">下一页</a> | <a href="javascript:;" class="right-font08" onclick="goLast()">末页</a> ] 转至：</td>
                <td width="1%"><table width="20" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="1%"><input id="num" name="num" type="text" class="right-textfield03" size="1" style="text-align:center;" onkeyup="filterNumChar(this)"/></td>
                      <td width="87%"><input name="button" type="button" class="right-button02" value="跳转" onclick="goPage()"/>
                      </td>
                    </tr>
                </table></td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
<script language="JavaScript">
	var curpage = 1;
	var totalpage = 1;
	
	function initPager(pager){
		curpage = pager.currentPage;
		totalpage = pager.totalPages;
		$("#totalpage").text(totalpage);
		$("#curpage").text(curpage);
		if(totalpage<2){
			$("#pager_table").hide();
		}else{
			$("#pager_table").show();
		}
		$("#pager_table").attr("width",$("#header").width());
	}
	
	function goFirst(){
		if(curpage > 1){
			curpage = 1;
			qryData();
		}
	}
	
	function goPrev(){
		if(curpage > 1){
			curpage = curpage-1;
			qryData();
		}
	}
	
	function goNext(){
		if(curpage < totalpage){
			curpage = curpage+1;
			qryData();
		}
	}
	
	function goLast(){
		if(curpage < totalpage){
			curpage = totalpage;
			qryData();
		}
	}
	
	function filterNumChar(obj){
		$(obj).val($(obj).val().onlyNum());
	}
	String.prototype.onlyNum = function() {return this.replace(/\D+/g, '');};
	
	function goPage(){
		//alert(totalpage)
		var num = $("#num").val();
		if(isEmpty(num)){
			alert("请输入页数！");
			return;
		}
		if((num*1)<1 || (num*1)>totalpage){
			alert("页数错误！");
			return;
		}
		curpage = num;
		qryData();
	}
</script>