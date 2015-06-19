/******************************************************************
	Author:       
	Create Date : 2004-04-25
	Function    : JS���ڿؼ�

	Explain and Example	:	
			1.��һ������Ϊ�Ƿ�Ϊ�գ��ڶ�������Ϊ�ؼ���������������ΪTitle������Ϊ��ʼֵ
				new DateControl(true, "StartDate", "��ʼʱ��", "2002-01-02");
			
			2.����Ϊ�գ���ӳ�ڵ�һ�������ϣ����Ϊ�ղ���������
				new DateControl(false, "CanNullStartDate", "��ʼʱ��", "2002-01-02");
			
			3.��û�е���������ʱ�����Ҳ���Ϊ��ʱ��Ĭ��Ϊ��ǰʱ��
				new DateControl(true, "EndDate", "����ʱ��"); 

			4.����Ϊ�գ�û��ʱ����ʾ
				new DateControl(false, "CanNullEndDate", "����ʱ��"); 

 ******************************************************************/


var _g_dt_imgCBB = "images/cbb_drop.gif";
var _g_dt_imgCurDay = "/view/js/control/date/images/curDayTip.gif";
var _g_dt_imgSelDay = "/view/js/control/date/images/selDayBg.gif";

var _g_dt_ShowingDTPicker = null;
var _g_dt_theMonths = new Array("һ��", "����", "����", "����", "����", "����", "����", "����", "����", "ʮ��", "ʮһ��", "ʮ����");
var _g_dt_theWeeks = new Array("��", "һ", "��", "��", "��", "��", "��");
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

	strHTML = "<html><head></head><body leftmargin=0 topmargin=0 style='font-size:9pt; font-family:����; border-width:1px; border-style:solid; border-color:#000000'><table border='0' width='100%' height='100%' cellpadding='0' cellspacing='0' style='font-size:9pt;' bgcolor='#ffffff'>";
	strHTML += "<tr>";
	strHTML += "<td valign='top'>";
	strHTML += "<table border='0' width='100%' height='25' cellpadding='0' cellspacing='0' bgcolor='#004080'> ";

	//draw year and month
	strHTML += "<tr align='right'><td width=3></td><td align='left' style='font-size:9pt'><input type='button' style='height:18px; width:24px;font-size:9pt' title='���һ����' value='�� ' onclick=\"javascript:window.parent._dt_subMonth('" + ctn + "');\"></td>";
	strHTML += "<td style='font-size:9pt; color:#ffffff;'><input id='dt_input_year_" + ctn + "' type='text' size='4' key='false' minLength='0' maxLength='4' title='�������' value='" + year + "' style='font-size:9pt;text-align:right; width:32px; border-left: #000000 solid 1; border-right: #000000 solid 1; border-top: #000000 solid 1; border-bottom: #000000 solid 1;' onblur=\"javascript:window.parent._dt_onYearChange('" + ctn + "');\"></td>";
	strHTML += "<td align='left' width='20'><input type='button' title='��ǰ��һ��' value='��' onClick=\"window.parent._dt_addYear('" + ctn + "')\" style='height:10;width:17;font-size:4pt'><br><input type='button' title='���һ��' value='��' onClick=\"window.parent._dt_subYear('" + ctn + "')\" style='height:10;width:17;font-size:4pt'>";
	strHTML += "<td align='center'><select id='dt_cbb_month_" + ctn + "' onchange=\"javascript:window.parent._dt_onMonthChange('" + ctn + "');\" style='width:60px; font-size:9pt'>";

	for ( i = 0; i < 12; i ++ ) {
		strHTML += "<option value='" + (i+1) + "'";
		if ( i+1 == month )
			strHTML += " selected";
		strHTML +=">" + _g_dt_theMonths[i] + "</option>";
	}
	strHTML += "</select></td><td style='font-size:9pt'><input type='button'  style='height:18px; width:24px;font-size:9pt' title='��ǰ��һ����' value=' ��' onclick=\"javascript:window.parent._dt_addMonth('" + ctn + "');\">&nbsp;</td><td align='right'><input type='button'  style='height:18px; width:18px;font-size:7pt' onclick='javascript:window.parent._dt_closeDTTable(\"" + ctn + "\");' value='X' title='�ر�'></td><td width=2></td></tr>";
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
	strHTML += "<tr><td valign='bottom' colspan='7' style='cursor:hand;font-color:#000000; font-weight:bold; font-size:9pt; verical-align:bottom;' title='ѡ�����' onclick=\"javascript:window.parent._dt_setDT('" + ctn + "', '" + curDay.getFullYear() + "', '" + (curDay.getMonth() + 1) + "', '" + curDay.getDate() +"');window.parent._dt_closeDTTable('" + ctn + "');\"><img align='middle' src='" + _g_dt_imgCurDay + "' border='0' width='40' height='11'>&nbsp;����:" + curDay.getFullYear() + "-" + ((curDay.getMonth() < 9) ? "0" + (curDay.getMonth() + 1) : (curDay.getMonth() + 1)) + "-" + ((curDay.getDate() < 10) ? "0" + curDay.getDate() : curDay.getDate()) +"</td></tr>";
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

	
	//��λdiv_dtTable_����Ļ�ϵ�����ctrlTb.style.left=x,ctrlTb.style.top=y
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

	
  
  //�����ڿؼ��ڷֲ�ؼ���
  if(_checkINTab(ctrlTb)) {

  	//����ڷֲ�ؼ��е����ڿؼ�
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

	//��ͨҳ���е����ڿؼ�
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


//�����ǰ�ؼ��ĸ�������"tabcontrol"��˵�����ڿؼ��ڷֲ�ؼ���
function _checkINTab(obj) {

  //ѭ��				  
  var i = 1;
  var curObj = obj;
  while(i < 25) {//ѭ��ʮ��Σ������ǰ�ؼ���һ������Ϊ"tabcontrol"��ִ����Ӧ�Ķ�λ��ʽ
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
			
			if(month == 0) {//���monthΪ"08"��"09",����parseInt()���ص�Ϊ0[��Ϊjavascript��BUG]
			  month = parseInt(value.substr(pos+2));//У��
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
				
				if(day == 0) {//���dayΪ"08"��"09",����parseInt()���ص�Ϊ0[��Ϊjavascript��BUG]
				  day = parseInt(value.substr(dpos+2));//У��
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
		alert("�Ƿ�����ݸ�ʽ���������������")
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
		alert("�Ƿ�����ݸ�ʽ���������������")
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
		alert("�Ƿ�����ݸ�ʽ���������������")
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
                //ȡ��ʵ����ݺ��·�
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
                //ȡ��ʵ����ݺ��·�
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
			cell.title=selyear+"��"+selmonth+"��"+_g_dt_days[i*7 + j]+"��";
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


//�����ʽ�������û��������ָ�ʽ��2002-12-03 and 2002-12-3 and 20021203����ʽ��Ϊ��2002-12-03
//																2002-02-03		 2002-2-2	     20020203
function formatDateStr( dateObj ) {
	var dateStr = dateObj.value;
	var pos  = dateStr.indexOf("-");
	var year = "";
	if( pos==-1 && dateStr.length==8 ) {
		dateStr = dateStr.substring(0, 4) +"-"+ dateStr.substring(4, 6) +"-"+ dateStr.substring(6);
	} else if(pos==4) {
		year = dateStr.substring(0, 4);
		var surpStr = dateStr.substring(pos+1);//�����ֶ�	
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

	strErr1 = "��׼�����ڸ�ʽ�磺2002-02-21    2002-2-21    20020221"
	if ( dateStr.length == 0 ) {	
	  if(isNotNull) {
	    if(onlyCheck != true) {
  		  alert("�������ڲ���Ϊ�գ��������׼��ʽ������\r\n\r\n" + strErr1 )
  	  }
  		return false
		} else {
		  return true  
		}
	}
	standStr = dateStr.replace("-","/")
	if ( dateStr.indexOf("-", 0) != 2 && dateStr.indexOf("-", 0) != 4 )	{
    if(onlyCheck != true) {
  		alert("�����������벻�淶��\r\n\r\n" + strErr1)
  	}
		return false
	}
	idxMonth = dateStr.indexOf("-", 0) + 1
	idxDay = dateStr.indexOf("-", idxMonth) + 1

	year = dateStr.substring(0, idxMonth - 1)
	month = dateStr.substring(idxMonth, idxDay - 1)
	if ( month < 1 || month > 12 ) {
    if(onlyCheck != true) {
    	alert("����������·���Ч\r\n")
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
    	alert("�����������벻�淶���м�����пո�����ַ�\r\n\r\n" + strErr1)
    }
		return false;
	}
	if ( day != newDateObj.getDate()) {
    if(onlyCheck != true) {
    	alert("�������ڲ��������������ڣ�\r\n\r\n     ��Ԫ" + parseInt(year) + "��" + parseInt(month) + "��û��" + day + "�ţ�")
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
