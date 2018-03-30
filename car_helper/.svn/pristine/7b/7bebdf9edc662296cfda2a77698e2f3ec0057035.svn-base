function check_sel_type_mer(year,month,day)
	{
                                                 
		sel_year = document.mx.year1.value ;
		sel_month = document.mx.month1.value ;
		sel_day = document.mx.day1.value ;
		
		if( sel_year == year && sel_month == month && sel_day == day )
		{
			for (var i=0;i<document.mx.mer_date_flag.length;i++)
			{
				if ( document.mx.mer_date_flag.options[i].value == 'today' )
					document.mx.mer_date_flag.options[i].selected = true ;
				else
					document.mx.mer_date_flag.options[i].selected = false ;
	    	}
	    }
	    else
	    {
			for (var i=0;i<document.mx.mer_date_flag.length;i++)
			{
				if ( document.mx.mer_date_flag.options[i].value == 'history' )
					document.mx.mer_date_flag.options[i].selected = true ;
				else
					document.mx.mer_date_flag.options[i].selected = false ;
	    	}
	    }
	}


	function check_sel_type_bk(year,month,day)
	{
                                                 
		sel_year = document.mx.year3.value ;
		sel_month = document.mx.month3.value ;
		sel_day = document.mx.day3.value ;
		
		if( sel_year == year && sel_month == month && sel_day == day )
		{
			for (var i=0;i<document.mx.bk_date_flag.length;i++)
			{
				if ( document.mx.bk_date_flag.options[i].value == 'today' )
					document.mx.bk_date_flag.options[i].selected = true ;
				else
					document.mx.bk_date_flag.options[i].selected = false ;
	    	}
	    }
	    else
	    {
			for (var i=0;i<document.mx.bk_date_flag.length;i++)
			{
				if ( document.mx.bk_date_flag.options[i].value == 'history' )
					document.mx.bk_date_flag.options[i].selected = true ;
				else
					document.mx.bk_date_flag.options[i].selected = false ;
	    	}
	    }
	}


function check()
{
	if(document.mx.flag[0].checked)
	{
		if( document.mx.ord_id.value.length <1) 
		{
			alert("您忘了输商户订单号！");
			document.mx.ord_id.focus();
			return false;
		}
		return true ;
	}
	else if(document.mx.flag[1].checked)
	{
		if( document.mx.sys_seq_id.value.length <1) 
		{
			alert("您忘了输系统流水号！");
			document.mx.sys_seq_id.focus();
			return false;
		}
		return true ;
	
	}
	else if(document.mx.flag[2].checked)
	{
		if( document.mx.bk_seq_id1.value.length <1) 
		{
			alert("您忘了输银行流水号1！");
			document.mx.bk_seq_id1.focus();
			return false;
		}
		return true ;

	}

}


function changeFlag(form1)
{
	if(form1.flag[0].checked)
	{
		condtion1.style.display='block';
		condtion2.style.display='none';
		condtion3.style.display='none';
		//document.mx.query_flag.value="1";
	}
	else if(form1.flag[1].checked)
	{
		condtion1.style.display='none';
		condtion2.style.display='block';
		condtion3.style.display='none';
		//document.mx.query_flag.value="2";
	}
	else if(form1.flag[2].checked)
	{
		condtion1.style.display='none';
		condtion2.style.display='none';
		condtion3.style.display='block';
		//document.mx.query_flag.value="3";
	}
	else
	{
		form1.flag[0].checked=true;
		condtion1.style.display='block';
		condtion2.style.display='none';
		condtion3.style.display='none';
	}
}
                       
                              
