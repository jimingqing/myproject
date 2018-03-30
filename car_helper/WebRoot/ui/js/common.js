
var url_prefix = "";

/**
* ajax请求数据
*/
function doAjaxRequestWithModal(url, data, succCallBack, errCallBack){
$.blockUI({
	message: "<img src='../ui/images/ajax-loader.gif'/>处理中，请稍候……",
	css: {color:'#fff',border:'3px solid #aaa',backgroundColor:'#000',fontSize:'16px',height:'50px'},
	overlayCSS: { opacity:'0.0' }
});
	$.ajax({
		type		:	"post",
		url			: 	url_prefix+url,
		data		:	data,
		timeout	:	30000,
		dataType:	"json",
		success	:   function(data){
			if (succCallBack != null){
				succCallBack(data);
			}
$.unblockUI();
		},
		error		:	function(XMLHttpRequest, textStatus, errorThrown){
			if (errCallBack != null){
				errCallBack(data);
			}
$.unblockUI();
		}
	});

}

/**
* ajax请求数据，同步执行
*/
function doAjaxRequestForSyn(url, data, succCallBack, errCallBack){
//$.blockUI({
//	message: "处理中，请稍候……"
//});
	$.ajax({
		type		:	"post",
		url			: 	url_prefix+url,
		data		:	data,
		timeout		:	30000,
		dataType	:	"json",
		async		:	false,
		success		:   function(data){
			if (succCallBack != null){
				succCallBack(data);
			}
//$.unblockUI();
		},
		error		:	function(XMLHttpRequest, textStatus, errorThrown){
			if (errCallBack != null){
				errCallBack(data);
			}
//$.unblockUI();
		}
	});

}

/**
* ajax请求数据
*/
function doAjaxRequest(url, data, succCallBack, errCallBack){
//$.blockUI({
//	message: "处理中，请稍候……"
//});
	$.ajax({
		type		:	"post",
		url			: 	url_prefix+url,
		data		:	data,
		timeout	:	30000,
		dataType:	"json",
		success	:   function(data){
			if (succCallBack != null){
				succCallBack(data);
			}
//$.unblockUI();
		},
		error		:	function(XMLHttpRequest, textStatus, errorThrown){
			if (errCallBack != null){
				errCallBack(data);
			}
//$.unblockUI();
		}
	});

}

/**
* ajax请求数据，提交文件
*/
function doAjaxRequestWithFile(url, data, fileElementId, succCallBack, errCallBack){
	$.ajaxFileUpload(
		{
			url		:	url, 
			secureuri:	false,
			fileElementId:fileElementId,
			timeout	:	30000,
			dataType: 	'json',
			data	:	data,
			success: function (data, status)
			{
				if (succCallBack != null){
					succCallBack(data);
				}
			},
			error: function (data, status, e)
			{
				if (errCallBack != null){
					errCallBack(data);
				}
			}

		}
	);
}

function getUrlParam(paramName) {
	var paramValue = "";
	var url = location.href;
	if(url.indexOf("?") != -1){
  	var paramString = url.substring(url.indexOf("?") + 1);
   	var parameters = paramString.split("&");
   	for (i = 0; i < parameters.length; i++){
      var paramAray = parameters[i].split('=');
      if(paramAray.length < 2) { continue; }
      if(paramName == paramAray[0]){
      	paramValue = decodeURI(paramAray[1]);
        break;
      }
   	}
  }
  return paramValue;
}
/**
* 判断是否为空
*/
function isEmpty(val){
	if(val == null || val == ""){
		return true;
	}
	return false;
}
function idDigit(e){
	if ($.browser.msie) {   
        if ( ((event.keycode > 47) && (event.keycode < 58)) ||   
              (event.keycode == 8) ) {   
            return true;   
        } else {   
            return false;   
        }   
    } else {   
        if ( ((e.which > 47) && (e.which < 58)) ||   
              (e.which == 8) ) {   
            return true;   
        } else {   
            return false;   
        }   
    }
		
}

function isChinese(temp){  
	var re = /[^\u4e00-\u9fa5]/;  
	if(re.test(temp)) 
		return false;  
	return true;  
} 

/**
* 判断是否为手机号码
*/
function isMobile(val){
	return /^((13\d{9})|(15\d{9})|(18\d{9}))$/.test(val);
}
/**
* 判断是否为数字字母
*/
function isLetterOrNumber(val){
	return /^[0-9A-Za-z]+$/.test(val);
}
function isNumber(val){
	return /^[0-9]+$/.test(val);
}
/**
* 判断是否为用户密码
*/
function isRightPassword(val){
	return /^[0-9A-Za-z-_]+$/.test(val);
}
/**
* 生成分页控件
*/
function generatePagerBar(container, pageCount, pageIndex, linkUrl){
  
	  
$(container).empty();
	  var preIdx = parseInt(pageIndex) - 1;
	  var prePage = (pageIndex == 1)? 1 : preIdx;
	  var nextIdx = parseInt(pageIndex) + 1;
	  var nextPage = (pageIndex == pageCount)? pageCount : nextIdx;
	  var toPage = nextPage + 1;
		if (toPage > pageCount) toPage = pageCount;
	  
		var pageHtml = "&nbsp;<a href='" + linkUrl + prePage + "'>上一页</a>&nbsp;"
	  				+ "&nbsp;<a href='" + linkUrl + nextPage + "'>下一页</a>&nbsp;"
	  				+ "&nbsp;第&nbsp;" + pageIndex + "&nbsp;/&nbsp;" + pageCount + "&nbsp;页&nbsp;"
	  				+ "&nbsp;<input type='text' value='" + toPage + "' id='_toPageIndex' size='3' maxlength='" + (pageCount + "").length + "' />&nbsp;"
	  				+ "&nbsp;<a href='javascript:void(0);' onclick=' if (checkToPageIndex()) {location.href=\"" + linkUrl + "\" + $(\"#_toPageIndex\").val();}'>跳至</a>&nbsp;";
	  
	  $(container).html(pageHtml);
}
	
function generatePageBarByShowMethod(pageIndex, pageCount, container, showMethod){
	  $(container).empty();
	  
	  var preIdx = parseInt(pageIndex) - 1;
	  var prePage = (pageIndex == 1)? 1 : preIdx;
	  var nextIdx = parseInt(pageIndex) + 1;
	  var nextPage = (pageIndex == pageCount)? pageCount : nextIdx;
var toPage = nextPage + 1;
if (toPage > pageCount) toPage = pageCount;
var pageHtml = "&nbsp;<a href='javascript:void(0);' onclick='" + showMethod + "(" + prePage + ");'>上一页</a>&nbsp;"
	  				+ "&nbsp;<a href='javascript:void(0);' onclick='" + showMethod + "(" + nextPage + ");'>下一页</a>&nbsp;"
	  				+ "&nbsp;第&nbsp;" + pageIndex + "&nbsp;/&nbsp;" + pageCount + "&nbsp;页&nbsp;"
	  				+ "&nbsp;<input type='text' value='" + toPage + "' id='_toPageIndex' size='5' maxlength='" + (pageCount + "").length + "' />&nbsp;"
	  				+ "&nbsp;<a href='javascript:void(0);' onclick=' if (checkToPageIndex()) {" + showMethod + "($(\"#_toPageIndex\").val());}'>跳至</a>&nbsp;";
	  
	  $(container).html(pageHtml);
	}
	
	function checkToPageIndex(){
		var toPage = $("#_toPageIndex").val();
		if ($.trim(toPage) == ""){
			alert("请输入跳至页码");
			return false;
		} else if (!/^\d+$/.test(toPage)){
			alert("请输入正确的跳至页码");
			return false;
		}
		return true;
	}

function getCityNameById(cityId){
		if ("01" == cityId){
			return "合肥";
		} else if ("02" == cityId){
			return "芜湖";
		} else if ("03" == cityId){
			return "蚌埠";
		} else if ("04" == cityId){
			return "滁州";
		} else if ("05" == cityId){
			return "安庆";
		} else if ("06" == cityId){
			return "六安";
		} else if ("07" == cityId){
			return "黄山";
		} else if ("08" == cityId){
			return "宣城";
		} else if ("09" == cityId){
			return "淮南";
		} else if ("10" == cityId){
			return "宿州";
		} else if ("11" == cityId){
			return "马鞍山";
		} else if ("12" == cityId){
			return "铜陵";
		} else if ("13" == cityId){
			return "淮北";
		} else if ("14" == cityId){
			return "阜阳";
		} else if ("15" == cityId){
			return "池州";
		} else if ("16" == cityId){
			return "亳州";
		}
		return "合肥";
	}
/**
* 获得汽车牌照前缀
*/ 
function getCarLicenseNameById(cityId){
		if ("01" == cityId){
			return "皖A";
		} else if ("02" == cityId){
			return "皖B";
		} else if ("03" == cityId){
			return "皖C";
		} else if ("04" == cityId){
			return "皖M";
		} else if ("05" == cityId){
			return "皖H";
		} else if ("06" == cityId){
			return "皖N";
		} else if ("07" == cityId){
			return "皖J";
		} else if ("08" == cityId){
			return "皖P";
		} else if ("09" == cityId){
			return "皖D";
		} else if ("10" == cityId){
			return "皖L";
		} else if ("11" == cityId){
			return "皖E";
		} else if ("12" == cityId){
			return "皖G";
		} else if ("13" == cityId){
			return "皖F";
		} else if ("14" == cityId){
			return "皖K";
		} else if ("15" == cityId){
			return "皖R";
		} else if ("16" == cityId){
			return "皖S";
		}
		return "皖A";
	}

	
/**
* 判断是否为身份证
*/
function isIdCard(idCard){
	//判断位数、格式是否正确
	var isRight = /^\d{17}[0-9Xx]$|^\d{15}$/.test(idCard);
	//判断日期是否正确
	if (isRight){
		var birthYear = "";
		var birthMonth = "";
		var birthDay = "";
		if(idCard.length == 15){//15位的身份证
			birthYear = "19" + idCard.substring(6, 8);
			birthMonth = idCard.substring(8, 10);
			birthDay = idCard.substring(10, 12);
		} else {//18位的身份证
			birthYear = idCard.substring(6, 10);
			birthMonth = idCard.substring(10, 12);
			birthDay = idCard.substring(12, 14);
		}
		var dtTest = new Date(Date.parse(birthYear + "/" + birthMonth + "/" + birthDay));
		//年
		var testYear = dtTest.getFullYear() + "";
		//月
		var testMonth = dtTest.getMonth() + 1;
		if(testMonth < 10){
			testMonth = "0" + testMonth;
		}
		//日
		var testDay = dtTest.getDate();
		if(testDay < 10){
			testDay = "0" + testDay;
		}
		//日期是否一致，如不一致说明出生日期输入有误
		if (birthYear != testYear || birthMonth != testMonth || birthDay != testDay){
			isRight = false
		}
	}
	return isRight;
}

/**
* 判断是否为姓名
*/
function isChineseName(val){
	return /^[0-9A-Za-z\u4e00-\u9fa5]+$/.test(val);
}
function isMoney(val){
	return /^\d+(\.\d+)?$/.test(val);
}
/**
 * 格式化金额
 * 12345格式化为12,345.00
 * 12345.6格式化为12,345.60
 * 12345.67格式化为12,345.67
 * @param s 金额
 * @param n 小数点后位数
 * @returns {String}
 */
function fmoney(s, n)
{
	if (s == null || s == "" || s == undefined || s == "null" )
	{
		return "0.00";
	}
	if (n == null || n == undefined)
	{
		n = 2;
	}
		
	n = n > 0 && n <= 20 ? n : 2;
	s = (parseFloat((s + "").replace(/[^\d\.-]/g, ""))).toFixed(n) + "";
	var l = s.split(".")[0].split("").reverse(),
	r = s.split(".")[1];
	t = "";
	for(i = 0; i < l.length; i ++ )
	{
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	return t.split("").reverse().join("") + "." + r;
}
/**
* 判断是否为空
*/
function convertNull(val){
	if(val == null || val == "null"){
		return "";
	}
	return val;
}

function choseTab(index) {
	if (index == 0) {
		window.location.href = "sjqb.html";
	} else if (index == 1) {
		window.location.href = "zfyy.html";
	} else if (index == 2) {
		window.location.href = "sfyh.html";
	} else if (index == 3) {
		window.location.href = "zcsj.html";
	}
}

function daysBetween(DateOne, DateTwo) {
	var OneMonth = DateOne.substring(5, DateOne.lastIndexOf('-'));
	var OneDay = DateOne.substring(DateOne.length, DateOne.lastIndexOf('-') + 1);
	var OneYear = DateOne.substring(0, DateOne.indexOf('-'));

	var TwoMonth = DateTwo.substring(5, DateTwo.lastIndexOf('-'));
	var TwoDay = DateTwo.substring(DateTwo.length, DateTwo.lastIndexOf('-') + 1);
	var TwoYear = DateTwo.substring(0, DateTwo.indexOf('-'));

	var cha = ((Date.parse(OneMonth + '/' + OneDay + '/' + OneYear) - Date.parse(TwoMonth + '/' + TwoDay + '/' + TwoYear)) / 86400000);
	return Math.abs(cha);
}

function checkEndDate(start, end) {
	var re=new RegExp("-","g"); 
	var s = start.replace(re,"");
	var e = end.replace(re,"");
	if ((s-e)<1) {
		return false;
	}
	return true;
}

function initDateNotification() {
    var today = new Date();
    //getMonth() 从 Date 对象返回月份 (0 ~ 11)。因此获取下一月需要+2。
    var format_date = today.getFullYear() + "-" + (parseInt(today.getMonth()) + 2) + "-" + today.getDate();
    $("#notify_date").text(format_date);
    $("#notify_date_btn").bind("click", function () {
        $("#chose_notify_date").bae_calendar({event:"choseDate", date:$("#receive_date").text(), chooseOldDate:false});
        slideToPage("page_home", "page_date", "left");
    })
}
function choseDate(date) {
    $("#receive_date").text(date);
    slideToPage("page_date", "page_home", "right")
}
function slideToPage(pageFrom, pageTo, direction, callback) {
    var animation;
    var from = $("#" + pageFrom), to = $("#" + pageTo);
    if (direction == 'left')
        animation = "slideLeft";
    else
        animation = "slideRight";

    from.addClass(animation + " out");
    to.addClass(animation + " in current");
    to.bind("webkitAnimationEnd", function () {
        from.removeClass("current " + animation + " out");
        to.unbind("webkitAnimationEnd");
        to.removeClass(animation + " in");
        if (typeof callback == "function") {
            callback();
        }
    });
}
function numRand(up, down) {
	var rand = parseInt(Math.random() * (up - down + 1) + down);
	return rand;
}

function showErrMsg(obj, msg, flag){
	if(("undefined" == typeof flag) || flag){
		$.each($("table .errMsg"),function(index, item){
			$(item).remove();
		})
	}
	
	if(obj!=null && msg!=null){
		$(obj).parent().append("<span class='errMsg'>"+msg+"</span>");
	}
}

function showOkMsg(obj, msg){
	$.each($("table .okMsg"),function(index, item){
		$(item).remove();
	})
	if(obj!=null && msg!=null){
		$(obj).parent().append("<span class='okMsg'>"+msg+"</span>");
	}
}