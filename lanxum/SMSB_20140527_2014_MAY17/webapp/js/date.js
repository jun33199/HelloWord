/******************************************************************
	Author:       
	Create Date : 2004-04-25
	Function    : JS日期控件

	Explain and Example	:	
			1.第一个参数为是否为空，第二个参数为控件名，第三个参数为Title，后面为初始值
				new DateControl(true, "StartDate", "起始时间", "2002-01-02");
			
			2.可以为空，反映在第一个参数上，如果为空不作输入检查
				new DateControl(false, "CanNullStartDate", "起始时间", "2002-01-02");
			
			3.当没有第三个参数时，而且不许为空时，默认为当前时间
				new DateControl(true, "EndDate", "结束时间"); 

			4.可以为空，没有时间显示
				new DateControl(false, "CanNullEndDate", "结束时间"); 

 ******************************************************************/


var _g_dt_imgCBB = "images/cbb_drop.gif";
var _g_dt_imgCurDay = "/view/js/control/date/images/curDayTip.gif";
var _g_dt_imgSelDay = "/view/js/control/date/images/selDayBg.gif";

var _g_dt_ShowingDTPicker = null;
var _g_dt_theMonths = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月");
var _g_dt_theWeeks = new Array("日", "一", "二", "三", "四", "五", "六");
var _g_dt_days = new Array(42);

function DateControl(isNotNull, ctn, title, dateStr) {
	if ( dateStr == null || dateStr == "undefined") {
	  var ds = "";
	  if(isNotNull) {
  		curDt = new Date();
  		month = curDt.getMonth() + 1; 
  		day = curDt.getDate();
  		ds = curDt.getFullYear() + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day)
		}
		
		_dt_drawDTPicker(isNotNull, ctn, title, ds);
	} else {
		_dt_drawDTPicker(isNotNull, ctn, title, dateStr); 
	}
}
   

function _dt_drawDTPicker(isNotNull, ctn, title, dateStr) {
	
	document.write("<div id='div_dt_main_" + ctn + "' style='position:relative;'><input type='text' id='" + ctn + "' name='" + ctn + "' isDTPicker=true caption='"+title+"' onblur=\"javascript:_dt_check("+isNotNull+", '" + ctn + "');touchOnblur(this);\" onKeyPress=\"javaScript:Keypress(this);\" value='" + dateStr + "' title='" + title + "' size='12' key='false' minLength='0' maxLength='10'>" );
	
	document.write("<img id='dt_cbbdrop_" + ctn + "' src='" + _g_dt_imgCBB + "' border='0' width='16' height='17' onclick=\"javascript:_dt_showDTTable('" + ctn + "');\" style='margin: -2, -18' >");
	document.write("</div><div id='div_dtTable_" + ctn + "' style='position:absolute; visibility:hidden; left:0;  top:22; z-index:50000;'></div>");
	//document.write("</div><div id='div_dtTable_" + ctn + "' style='position:absolute; visibility:hidden; left:0;  top:22; z-index:50000;filter :\'progid:DXImageTransform.Microsoft.Shadow(direction=135,color=#AAAAAA,strength=4)\' ;display: none'></div>");
	
	/*var str = "<div id='div_dt_main_" + ctn + "' style='position:relative;'><input type='text' id='" + ctn + "' name='" + ctn + "' caption='"+title+"' onblur=\"javascript:_dt_check("+isNotNull+", '" + ctn + "');touchOnblur(this);\" onKeyPress=\"javaScript:Keypress(this);\" value='" + dateStr + "' title='" + title + "' size='12' key='false' minLength='0' maxLength='10'>";
	str += "<img id='dt_cbbdrop_" + ctn + "' src='" + _g_dt_imgCBB + "' border='0' width='16' height='17' onclick=\"javascript:_dt_showDTTable('" + ctn + "');\" style='margin: -2, -18' >";
	str + "</div><div id='div_dtTable_" + ctn + "' style='position:absolute; visibility:hidden; left:0;  top:22; z-index:50000;'></div>";
	
	document.body.insertAdjacentHTML("afterBegin", str);*/
}

function _dt_drawDTTable(ctn) {
	var strHTML;
	
	var year = _dt_getDTYear(ctn);
	var month = _dt_getDTMonth(ctn);
	var day = _dt_getDTDay(ctn);
	var i = 0;
	document.all("div_dtTable_" + ctn).innerHTML = "<iframe scr='' name='_dt_iframe_" + ctn + "' width='200' height='140' border=0 NORESIZE=NORESIZE SCROLLING=no MARGINWIDTH=0 MARGINHEIGHT=0 FRAMESPACING=0 FRAMEBORDER=0></iframe>";

	strHTML = "<html><head></head><body leftmargin=0 topmargin=0 style='font-size:9pt; font-family:宋体; border-width:1px; border-style:solid; border-color:#000000'><table border='0' width='100%' height='100%' cellpadding='0' cellspacing='0' style='font-size:9pt;' bgcolor='#ffffff'>";
	strHTML += "<tr>";
	strHTML += "<td valign='top'>";
	strHTML += "<table border='0' width='100%' height='25' cellpadding='0' cellspacing='0' bgcolor='#004080'> ";

	//draw year and month
	strHTML += "<tr align='right'><td width=3></td><td align='left' style='font-size:9pt'><input type='button' style='height:18px; width:24px;font-size:9pt' title='向后翻一个月' value='《 ' onclick=\"javascript:window.parent._dt_subMonth('" + ctn + "');\"></td>";
	strHTML += "<td style='font-size:9pt; color:#ffffff;'><input id='dt_input_year_" + ctn + "' type='text' size='4' key='false' minLength='0' maxLength='4' title='输入年份' value='" + year + "' style='font-size:9pt;text-align:right; width:32px; border-left: #000000 solid 1; border-right: #000000 solid 1; border-top: #000000 solid 1; border-bottom: #000000 solid 1;' onblur=\"javascript:window.parent._dt_onYearChange('" + ctn + "');\"></td>";
	strHTML += "<td align='left' width='20'><input type='button' title='向前翻一年' value='▲' onClick=\"window.parent._dt_addYear('" + ctn + "')\" style='height:10;width:17;font-size:4pt'><br><input type='button' title='向后翻一年' value='▼' onClick=\"window.parent._dt_subYear('" + ctn + "')\" style='height:10;width:17;font-size:4pt'>";
	strHTML += "<td align='center'><select id='dt_cbb_month_" + ctn + "' onchange=\"javascript:window.parent._dt_onMonthChange('" + ctn + "');\" style='width:60px; font-size:9pt'>";

	for ( i = 0; i < 12; i ++ ) {
		strHTML += "<option value='" + (i+1) + "'";
		if ( i+1 == month )
			strHTML += " selected";
		strHTML +=">" + _g_dt_theMonths[i] + "</option>";
	}
	strHTML += "</select></td><td style='font-size:9pt'><input type='button'  style='height:18px; width:24px;font-size:9pt' title='向前翻一个月' value=' 》' onclick=\"javascript:window.parent._dt_addMonth('" + ctn + "');\">&nbsp;</td><td align='right'><input type='button'  style='height:18px; width:18px;font-size:7pt' onclick='javascript:window.parent._dt_closeDTTable(\"" + ctn + "\");' value='X' title='关闭'></td><td width=2></td></tr>";
	strHTML += "</table></td></tr><tr><td>";
	
	//draw weeks
	strHTML += "<table id='_dt_dttable_" + ctn + "' style='font-size:9pt' border='0' width='100%' height='100%' cellpadding='0' cellspacing='0' > ";
	strHTML += "<thead><tr>";
	for ( i = 0; i < 7; i ++ )
  	strHTML += "<td valign='bottom' align='center' height='16' style='font-size:9pt; color:#004080;'>" + _g_dt_theWeeks[i] + "</td>";
	strHTML += "</tr><tr><td height='1' colspan='7' bgcolor='#000000'></td></tr>";
	strHTML += "</thead>";
	strHTML += "<tbody></tbody>";
	strHTML += "<tfoot><tr><tr><td colspan='7' height='1' bgcolor='#000000'></td></tr>";
	var curDay = new Date();
	strHTML += "<tr><td valign='bottom' colspan='7' style='cursor:hand;font-color:#000000; font-weight:bold; font-size:9pt; verical-align:bottom;' title='选择今天' onclick=\"javascript:window.parent._dt_setDT('" + ctn + "', '" + curDay.getFullYear() + "', '" + (curDay.getMonth() + 1) + "', '" + curDay.getDate() +"');window.parent._dt_closeDTTable('" + ctn + "');\"><img align='middle' src='" + _g_dt_imgCurDay + "' border='0' width='40' height='11'>&nbsp;今天:" + curDay.getFullYear() + "-" + ((curDay.getMonth() < 9) ? "0" + (curDay.getMonth() + 1) : (curDay.getMonth() + 1)) + "-" + ((curDay.getDate() < 10) ? "0" + curDay.getDate() : curDay.getDate()) +"</td></tr>";
	strHTML += "</tfoot></table></body></html>";
	window.frames("_dt_iframe_" + ctn).document.open();
	window.frames("_dt_iframe_" + ctn).document.write(strHTML);
	window.frames("_dt_iframe_" + ctn).document.close();
	_dt_drawDTDays(ctn, year, month, day);
}

function _dt_showDTTable(ctn) {
	var x,y;

	//x = window.event.clientX - window.event.offsetX - 80;
	//y = window.event.clientY - window.event.offsetY + 17;
	ctrlTb = document.all("div_dtTable_" + ctn);

	
	//定位div_dtTable_在屏幕上的坐标ctrlTb.style.left=x,ctrlTb.style.top=y
	var e = window.event.srcElement; 
    
	var t = e.offsetTop,  h = e.clientHeight, l = e.offsetLeft;
	while (e = e.offsetParent){t += e.offsetTop; l += e.offsetLeft;}
    
    var cw = ctrlTb.clientWidth, ch = ctrlTb.clientHeight;
    var dw = document.body.clientWidth, dl = document.body.scrollLeft, dt = document.body.scrollTop;
    
    if (document.body.clientHeight + dt - t - h >= ch) y = t + h  ;
    else y  = (t - dt < ch) ? ( t + h  ) : t - ch ;
    if (dw + dl - l >= cw) x = l; else x = (dw >= cw) ? dw - cw + dl : dl;
    
	x = x - 120;
	y = y - 11;
	//alert("x="+x+" y="+y);
	
    /*
	clWidth = window.document.body.clientWidth;
	if ( clWidth < 200 ) {
		x = 1;
	} else if ( x + 200 + 1 > clWidth) {
		x = clWidth - 200 - 1;
	}
	clHeight = window.document.body.clientHeight;
	if (clHeight < 140) {
		y = 1;
	} else if ( y + 140 + 1 > clHeight){
		y = clHeight - 140 - 1;
	}
    */
	if ( _g_dt_ShowingDTPicker != null ) {
		if( ctn != _g_dt_ShowingDTPicker ) {
			document.all("div_dtTable_" + _g_dt_ShowingDTPicker).innerHTML = "";
			document.all("div_dtTable_" + _g_dt_ShowingDTPicker).style.visibility = "hidden";
		}
	}

	
  
  //该日期控件在分层控件中
  if(_checkINTab(ctrlTb)) {

  	//针对在分层控件中的日期控件
  	var _haveTabControl  = false;
  	var _haveButtonPanel = false;
  	
  	var _curTables = document.getElementsByTagName("TABLE");
  	
  	for(var i = 0; i < _curTables.length; i++) {
  	  if(_curTables[i].isTabControl != null && _curTables[i].isTabControl == true) {
  	    _haveTabControl = true;
  	    break;
  	  }
  	}
  	if(document.getElementById("_ButtonPanel") != null) {
  	  _haveButtonPanel = true;
  	}
  	
  	if(_haveTabControl) {
		
  		if ( ctrlTb.style.visibility == "hidden") {
  			_dt_drawDTTable(ctn);
  			ctrlTb.style.visibility = "visible";
			//alert(window.event.screenX);
			//alert(window.event.screenY);
            //x = window.event.screenX-window.event.clientX;// - 292;
			x = x - 40;
            y = window.event.screenY-window.event.clientY-64;// - 117;
  
    		if(_haveButtonPanel) {
				//x = window.event.screenX - 312;
		        //y = window.event.screenY - 139;
    		}
		    //alert(x+":"+y);
    		ctrlTb.style.left = x + "px";
    		ctrlTb.style.top = y + "px";
  			
  			_g_dt_ShowingDTPicker = ctn;
  		} else {
  			ctrlTb.innerHTML = "";
  			ctrlTb.style.visibility = "hidden";
  			_g_dt_ShowingDTPicker = null;
  		}
  		
  		return true;
  	}
  }

	//普通页面中的日期控件
	if ( ctrlTb.style.visibility == "hidden") {
		_dt_drawDTTable(ctn);
		ctrlTb.style.visibility = "visible";
		ctrlTb.style.left = x + "px";
		ctrlTb.style.top = y+7 + "px";

		//alert("ctrlTb.style.left="+x+"px ctrlTb.style.top="+y+"px");

		_g_dt_ShowingDTPicker = ctn;
	} else {
		ctrlTb.innerHTML = "";
		ctrlTb.style.visibility = "hidden";
		_g_dt_ShowingDTPicker = null;
	}

}


//如果当前控件的父类中有"tabcontrol"，说明日期控件在分层控件中
function _checkINTab(obj) {

  //循环				  
  var i = 1;
  var curObj = obj;
  while(i < 25) {//循环十五次，如果当前控件的一个父类为"tabcontrol"，执行相应的定位方式
    if(curObj.parentElement != null && curObj.parentElement.isTabControl == true) {
      return true;
    } else if(curObj.parentElement != null) {
      curObj = curObj.parentElement;
    } else if(curObj.parentElement == null) {
      return false;
    }
    i++;
  }
  return false;
}


function _dt_closeDTTable(ctn) {
  
	ctrlTb = document.all("div_dtTable_" + ctn);
	ctrlTb.innerHTML = "";
	ctrlTb.style.visibility = "hidden";
	_g_dt_ShowingDTPicker = null;
	
}

function _dt_getDTYear(ctn) {
	if ( document.all(ctn).value.length > 0 ) {
		year = parseInt(document.all(ctn).value.substr(0, 4));
		if ( !isNaN(year) ) 
			return year;
	}
	return new Date().getYear();
}

function _dt_getDTMonth(ctn) {
	var value = document.all(ctn).value;
	if ( value.length > 0 ) {
		pos = value.indexOf('-');
		if ( pos > 0 ) {
			month = parseInt(value.substr(pos+1, 2));
			
			if(month == 0) {//如果month为"08"和"09",经过parseInt()返回的为0[此为javascript的BUG]
			  month = parseInt(value.substr(pos+2));//校正
			}
			if ( !isNaN(month) )
				return month;
		}
	}
	return new Date().getMonth()+1;
}

function _dt_getDTDay(ctn) {
	var value = document.all(ctn).value;
	if ( value.length > 0 ) {
		pos = value.indexOf('-');
		if ( pos > 0 ) {
			dpos = value.indexOf('-', pos+1);
			if (dpos > 0) {
				day = parseInt(value.substr(dpos+1, 2));
				
				if(day == 0) {//如果day为"08"和"09",经过parseInt()返回的为0[此为javascript的BUG]
				  day = parseInt(value.substr(dpos+2));//校正
				}
				
				if ( !isNaN(day) )
					return day;
			}
		}
	}
	
	return new Date().getDate();
//	var dt = new Date(_dt_GetStdDate(document.all(ctn).value).replace("-", "/"))
//	return dt.getDate();
}

function _dt_setDTYear(ctn, year) {
	_dt_setDT(ctn, year, _dt_getDTMonth(ctn), _dt_getDTDay(ctn));
}
function _dt_setDTMonth(ctn, month) {
	var year = _dt_getDTYear(ctn);
	var day = _dt_getDTDay(ctn);
	var maxDays = _dt_getDayCount(year, month);
	day = day < maxDays ? day : maxDays;
	_dt_setDT(ctn, year, month, day);
}
function _dt_setDTDay(ctn, day) {
	_dt_setDT(ctn, _dt_getDTYear(ctn), _dt_getDTMonth(ctn), day);
}

function _dt_setDT(ctn, year, month, day) {
	document.all(ctn).value = _dt_GetStdDate(year + "/" + (month < 10 ? "0" + month : month) + "/" + (day < 10 ? "0" + day : day));
}
function _dt_onYearChange(ctn) {
	var year = parseInt(window.frames("_dt_iframe_" + ctn).document.all("dt_input_year_" + ctn).value);
	if (!isNaN(year)) {
		_dt_setDTYear(ctn, year);
		_dt_drawDTDays(ctn, year, _dt_getDTMonth(ctn), _dt_getDTDay(ctn));
	} else {
		alert("非法的年份格式，请重新输入年份")
		window.frames("_dt_iframe_" + ctn).document.all("dt_input_year_" + ctn).focus();
	}
}

function _dt_onMonthChange(ctn) {
	var month = parseInt(window.frames("_dt_iframe_" + ctn).document.all("dt_cbb_month_" + ctn).value)
	if (month == 0)
		return;
	if ( !isNaN(month) )	{
		_dt_setDTMonth(ctn, month);
		_dt_drawDTDays(ctn, _dt_getDTYear(ctn), month, _dt_getDTDay(ctn));
	}
}

function _dt_addYear(ctn) {
  
	var year = parseInt(window.frames("_dt_iframe_" + ctn).document.all("dt_input_year_" + ctn).value);
	if (isNaN(year)) {
		alert("非法的年份格式，请重新输入年份")
		window.frames("_dt_iframe_" + ctn).document.all("dt_input_year_" + ctn).focus();
		return;
	}
	window.frames("_dt_iframe_" + ctn).document.all("dt_input_year_" + ctn).value = year + 1;
	_dt_setDTYear(ctn, year+1);
	_dt_drawDTDays(ctn, year + 1, _dt_getDTMonth(ctn), _dt_getDTDay(ctn));
}

function _dt_subYear(ctn) {
	var year = parseInt(window.frames("_dt_iframe_" + ctn).document.all("dt_input_year_" + ctn).value);
	if (isNaN(year)) {
		alert("非法的年份格式，请重新输入年份")
		window.frames("_dt_iframe_" + ctn).document.all("dt_input_year_" + ctn).focus();
		return;
	}
	window.frames("_dt_iframe_" + ctn).document.all("dt_input_year_" + ctn).value = year - 1;
	_dt_setDTYear(ctn, year-1);
	_dt_drawDTDays(ctn, year - 1, _dt_getDTMonth(ctn), _dt_getDTDay(ctn));
}

function _dt_addMonth(ctn) {
	var month = parseInt(window.frames("_dt_iframe_" + ctn).document.all("dt_cbb_month_" + ctn).value);
	var year = parseInt(window.frames("_dt_iframe_" + ctn).document.all("dt_input_year_" + ctn).value);

	if ( isNaN(year) || isNaN(month) )
		return;

	if ( month == 12 ) {
		year ++;
		month = 1;
	} else {
		month ++;
	}
	window.frames("_dt_iframe_" + ctn).document.all("dt_cbb_month_" + ctn).value = month;
	_dt_setDT(ctn, year, month, _dt_getDTDay(ctn));
	_dt_drawDTDays(ctn, year, month, _dt_getDTDay(ctn));
}

function _dt_subMonth(ctn) {
	var month = parseInt(window.frames("_dt_iframe_" + ctn).document.all("dt_cbb_month_" + ctn).value);
	var year = parseInt(window.frames("_dt_iframe_" + ctn).document.all("dt_input_year_" + ctn).value);

	if ( isNaN(year) || isNaN(month) )
		return;

	if ( month == 1 ) {
		year --;
		month = 12;
	} else {
		month --;
	}
	window.frames("_dt_iframe_" + ctn).document.all("dt_cbb_month_" + ctn).value = month;
	_dt_setDT(ctn, year, month, _dt_getDTDay(ctn));
	_dt_drawDTDays(ctn, year, month, _dt_getDTDay(ctn));
}
function _dt_drawDTDays(ctn, year, month, day) {
	var dayCount = _dt_getDayCount(year, month);
	var dayIndex = 0;
	var preMonthDayCount = 0, preMonthShowDayCount = 0, nextMonthShowDayCount = 0;
	var startDate;
	var table = window.frames("_dt_iframe_" + ctn).document.all("_dt_dttable_" + ctn).tBodies[0];

	if (month > 1)	{
		startDate = new Date(year, month-1, 1);
		preMonthDayCount = _dt_getDayCount(year, month-1);
	} else {
		startDate = new Date(year-1, 12, 1);
		preMonthDayCount = _dt_getDayCount(year-1, 12);
	}
	var week = startDate.getDay();
	var strHTML = "";

	preMonthShowDayCount = week;
	if ( week > 0 ) { 	
		if (month == 1) {			
			for ( i = week; i > 0; i -- )
				_g_dt_days[dayIndex++] = 31 - i + 1;
		} else {
			for ( i = week; i > 0; i -- )
					_g_dt_days[dayIndex++] = preMonthDayCount - i + 1;
		}
	}
	for ( i = 0; i < dayCount; i ++ ) {
		_g_dt_days[dayIndex++] = i + 1;
	}
	nextMonthShowDayCount = 42 - dayIndex;
	for ( i = 0; i < nextMonthShowDayCount; i ++ ) {
		_g_dt_days[dayIndex++] = i + 1;
	}

	//clear all rows
	while ( table.rows.length > 0 ) {
		table.deleteRow()
	}

	for ( i = 0; i < 6; i++ ) {
		aRow = table.insertRow();
		for ( j = 0; j < 7; j++ ) {
			cell = aRow.insertCell();
			cell.align = "center";
			
			cell.onmousemove = new Function("this.style.cursor='hand'; if ( this.style.color != '#ffffff' ) this.style.backgroundColor='#4aff73';");
			cell.onmouseout = new Function("if ( this.style.color != '#ffffff' ) this.style.backgroundColor='#ffffff';");
			cell.style.fontSize="9pt";
            
		    var selyear = parseInt(window.frames("_dt_iframe_" + ctn).document.all("dt_input_year_" + ctn).value);
	        var selmonth = parseInt(window.frames("_dt_iframe_" + ctn).document.all("dt_cbb_month_" + ctn).value);
			if ( (i*7 + j) < preMonthShowDayCount ) {
				cell.style.color = "#808080";
				cell.onclick = new Function("_dt_on_selectDay('" + ctn + "', -1, " + _g_dt_days[i*7 + j] + ");");
                //取得实际年份和月份
                if ( selmonth == 1 ) {
					selyear --;
					selmonth = 12;
				} else {
					selmonth --;
				}
				
			}
			else if ( (42 - (i*7 + j)) <= nextMonthShowDayCount ) {
				cell.style.color = "#909090";
				cell.onclick = new Function("_dt_on_selectDay('" + ctn + "', 1, " + _g_dt_days[i*7 + j] + ");");
                //取得实际年份和月份
				if ( selmonth == 12 ) {
					selyear ++;
					selmonth = 1;
				} else {
					selmonth ++;
				}
			}
			else if ( _g_dt_days[i*7 + j] == day ) {
			/*	cell.style.color = "#ffffff";
				cell.style.backgroundColor = "#004080";
				cell.onclick = new Function("_dt_closeDTTable('" + ctn + "');");
				//cell.style.backgroundImage="url('" + _g_dt_imgSelDay + "')";*/
				cell.style.color = "#ffffff";
				cell.style.backgroundColor = "#004080";
				cell.onclick = new Function("_dt_on_selectDay('" + ctn + "', 0, " + _g_dt_days[i*7 + j] + ");");

			}
			else {
				cell.style.color = (j == 0 || j == 6) ? "#f00000" : "#000000";
				cell.onclick = new Function("_dt_on_selectDay('" + ctn + "', 0, " + _g_dt_days[i*7 + j] + ");");

			}
			cell.innerText = _g_dt_days[i*7 + j];
			cell.title=selyear+"年"+selmonth+"月"+_g_dt_days[i*7 + j]+"日";
		}
	}

	//return strHTML;
}

function _dt_on_selectDay(ctn, flag, day) {
	var year = parseInt(window.frames("_dt_iframe_" + ctn).document.all("dt_input_year_" + ctn).value);
	var month = parseInt(window.frames("_dt_iframe_" + ctn).document.all("dt_cbb_month_" + ctn).value);
	if ( isNaN(year) || isNaN(month) )
		return;
	if ( flag == -1 ) {
		if ( month == 1 ) {
			year --;
			month = 12;
		} else {
			month --;
		}
	}
	else if ( flag == 1 ) {
		if ( month == 12 ) {
			year ++;
			month = 1;
		} else {
			month ++;
		}
	}
	_dt_setDT(ctn, year, month, day);
	_dt_closeDTTable(ctn);
	document.all(ctn).focus();
}

function _dt_getDayCount(year, month) {
	switch(month) { 
	case 1:case 3:case 5:case 7:case 8:case 10:case 12:
		return 31;
	case 4:case 6:case 9:case 11:
		return 30;
	case 2:
		return (((year%4)==0) && ((year%10)!=0) ) || ((year%100)==0) ? 29 : 28;
	default:
		return 31;
	}
}

function _dt_check(isNotNull, ctn) {

	if ( !_dt_CheckDate(isNotNull, formatDateStr(document.all(ctn)), false ) ) {
		document.all(ctn).focus();
		return false;
	}
	return true;
	
}


//定义格式。允许用户输入三种格式：2002-12-03 and 2002-12-3 and 20021203，格式化为：2002-12-03
//																2002-02-03		 2002-2-2	     20020203
function formatDateStr( dateObj ) {
	var dateStr = dateObj.value;
	var pos  = dateStr.indexOf("-");
	var year = "";
	if( pos==-1 && dateStr.length==8 ) {
		dateStr = dateStr.substring(0, 4) +"-"+ dateStr.substring(4, 6) +"-"+ dateStr.substring(6);
	} else if(pos==4) {
		year = dateStr.substring(0, 4);
		var surpStr = dateStr.substring(pos+1);//其余字段	
		pos = surpStr.indexOf("-");
		if( pos==1 ) {
			if(surpStr.length==3) {
				dateStr = year+ "-0" +surpStr.substring(0,1)+ "-0" +surpStr.substring(2);
			} else if(surpStr.length==4) {
				dateStr = year+ "-0" +surpStr.substring(0,1)+ "-" +surpStr.substring(2);
			}
		} else if(pos==2) {
			if(surpStr.length==4) {
				dateStr = year+ "-" +surpStr.substring(0,2)+ "-0" +surpStr.substring(3);
			}
		}
	}
	
	if(dateObj.value != dateStr) {
	  dateObj.value = dateStr;
	}
	return dateStr;
}



function _dt_CheckDate(isNotNull, dateStr, onlyCheck ) {
  
	var standStr
	var newDateObj
	var strErr, idxMonth, idxDay
	var year, month, day

	strErr1 = "标准的日期格式如：2002-02-21    2002-2-21    20020221"
	if ( dateStr.length == 0 ) {	
	  if(isNotNull) {
	    if(onlyCheck != true) {
  		  alert("错误：日期不能为空，请输入标准格式的日期\r\n\r\n" + strErr1 )
  	  }
  		return false
		} else {
		  return true  
		}
	}
	standStr = dateStr.replace("-","/")
	if ( dateStr.indexOf("-", 0) != 2 && dateStr.indexOf("-", 0) != 4 )	{
    if(onlyCheck != true) {
  		alert("错误：日期输入不规范！\r\n\r\n" + strErr1)
  	}
		return false
	}
	idxMonth = dateStr.indexOf("-", 0) + 1
	idxDay = dateStr.indexOf("-", idxMonth) + 1

	year = dateStr.substring(0, idxMonth - 1)
	month = dateStr.substring(idxMonth, idxDay - 1)
	if ( month < 1 || month > 12 ) {
    if(onlyCheck != true) {
    	alert("错误：输入的月份无效\r\n")
    }
		return false
	}	
	if (dateStr.indexOf(" ", idxDay) != -1)
		day = dateStr.substring(idxDay, dateStr.indexOf(" ", idxDay))
	else
		day = dateStr.substring(idxDay)
	newDateObj = new Date( standStr )
	if ( newDateObj == "NaN" ) {
    if(onlyCheck != true) {
    	alert("错误：日期输入不规范！中间可能有空格或者字符\r\n\r\n" + strErr1)
    }
		return false;
	}
	if ( day != newDateObj.getDate()) {
    if(onlyCheck != true) {
    	alert("错误：日期不是正常公历日期！\r\n\r\n     公元" + parseInt(year) + "年" + parseInt(month) + "月没有" + day + "号！")
    }
		return false;
	}
	return true;
}

function _dt_GetStdDate( dateStr ) {
	var newDateObj
	var month, day

	newDateObj = new Date( dateStr.replace("-", "/") )
	if ( newDateObj == "NaN" )
		return dateStr;

	month = newDateObj.getMonth() + 1
	day = newDateObj.getDate()

	return newDateObj.getFullYear() + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
}
