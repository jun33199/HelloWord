<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dtd HTML 4.01 transitional//EN">
<html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">

<title>节假日维护</title>

<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/list.js">
</script>
<script language="JavaScript">
<!--
//--------------- 以下常量，可自由设置 -------------------------//
/*
* Version: 0.9.00
*/
<%
String cyear = (String)request.getAttribute("CYEAR");
%>
<!--
//--------------- 以下常量，可自由设置 -------------------------//
/*
* Version: 0.9.10
*/
//年份
CYEAR = <%=cyear%>;;
//假日的id
CHOLYID = "holiday-id";
//假日的class
CHOLYCLS = "caln-td-holiday";
//非假日的id
CNOHOLYID = "no-holiday-id";
//非假日的class
CNOHOLYCLS = "caln-td-no-holiday";
//日历表格的样式
CALNTABCLS = "caln-table";
//--------------- 以上常量，可自由设置 -------------------------//
//假日数组
var holyIndex = 0;
var holyStr;
//要求：按日期升序，以便输出；日期格式： 20030609
var holyArr = new Array();
<%
String[] holiday = (String[])request.getAttribute("holiday");
if(holiday!=null){
  out.println("holyArr = new Array( "+ holiday.length +")" );
  for(int i=0;i<holiday.length;i++)
  {
    out.println("holyArr["+i+"] = \""+holiday[i]+"\";");
  }
  out.println("holyArr = holyArr.sort();");
}
%>
//Sort
//holyArr = holyArr.sort();

/*
* Notes: 鼠标单击事件，设置是否假期
* Version: 0.9.10
*/
function setHoliday(obj){
	var oItem = obj.children.item(0);
	if(obj.className==CHOLYCLS){
		obj.className = CNOHOLYCLS;
		oItem.id = CNOHOLYID;
                oItem.name = CNOHOLYID;
	}else if(obj.className==CNOHOLYCLS){
		obj.className = CHOLYCLS;
		oItem.id = CHOLYID;
                oItem.name = CHOLYID;
	}
}

/*
* Notes: 执行输出日历
* Version: 0.9.10
* Parame: kind 0,默认日期;
*/
function tab_refresh(kind){
	if(kind==0){
		holyArr = new Array();
	}
	//全年默认假期
	outCaln(CYEAR);
}

/*
* Notes: 输出全年默认假期
* Version: 0.9.00
*/
function outCaln(intYear){
	var monTab = "";

	for(i=0;i<12;i++){
		//获取字符串
		monTab = outCalnTable(intYear,i+1);
		//最好用class=CALNTABCLS，便于更改表格样式
		monTab = "<table border='1' bordercolordark='#000000' bordercolorlight='#FFFFFF' width='100%' height='180' border='1' cellpadding='0' cellspacing='0'>" + monTab + "</table>";
		//动态输出
		eval("caln_mon_"+(i+1)).innerHTML = monTab;
	}
}

/*
* Notes: 根据指定的年月，获取按照表格输出的结果字符串，
*        结果字符串有<tr><td>组成，
*        使用时需要用<table></table>括起结果字符串，即：<table>结果字符串</table>
* Parames:
* 		intYear 年份；4位年，eg: 1999
* 		intMonth 月份；从 1 开始
*/
function outCalnTable(intYear,intMonth){
var outStr = "";
//某月开始的星期数
var startWeek;
//某月的天数
var Days;

var index,trNum,strTmp;
//for(i=0;i<12;i++){
	index = 0;
	trNum = 1;
	startWeek = CL_getWeek(new Date(CYEAR,intMonth-1,1));
	Days = CL_getDays(CYEAR,intMonth,-1);

	//print
	//定位到指定的星期
	outStr += "<tr align=center>";
	for(k=0;k<startWeek;k++){
		outStr += "<td class='caln-td-nodate'>&nbsp;</td>";
		index++;
	}

	//是否已有假日纪录
	if(holyArr.length){//有假日纪录，根据纪录输出
		holyStr = holyArr[holyIndex];
		for(j=1;j<=Days;j++){
			tmp = (index+j) % 7;
			strTmp = calnDateFormat(intYear,intMonth,j);//日期格式： 20030609
			if(tmp == 0){//换行
				if(holyStr==strTmp){//日期相同，进行标记
					//获取下一个纪录
					holyIndex++;
					//如果下一个值与当前值相同，则再取下一个
					while(holyStr == holyArr[holyIndex]){
						holyIndex++;
					}
					holyStr = holyArr[holyIndex];

					//页面输出字符串
					outStr += "<td class='"+CHOLYCLS+"' onClick='setHoliday(this)'><input type='hidden' name='"+CHOLYID+"' value='"+strTmp+"'>"+j+"</td>"+"</tr>\r\n<tr align=center>";
				}else{
					outStr += "<td class='"+CNOHOLYCLS+"' onClick='setHoliday(this)'><input type='hidden' name='"+CNOHOLYID+"' value='"+strTmp+"'>"+j+"</td>"+"</tr>\r\n<tr align=center>";
				}
				//行数加 1 ,
				trNum++;
			}else{
				if(holyStr==strTmp){
					holyIndex++;
					//如果下一个值与当前值相同，则再取下一个
					while(holyStr == holyArr[holyIndex]){
						holyIndex++;
					}
					holyStr = holyArr[holyIndex];
					outStr += "<td class='"+CHOLYCLS+"' onClick='setHoliday(this)'><input type='hidden' name='"+CHOLYID+"' value='"+strTmp+"'>"+j+"</td>";
				}else{
					outStr += "<td class='"+CNOHOLYCLS+"' onClick='setHoliday(this)'><input type='hidden' name='"+CNOHOLYID+"' value='"+strTmp+"'>"+j+"</td>";
				}
			}//end [if-else][tmp==0]
		}//end [for]

	}else{//输出默认假日，即标记星期六日
		for(j=1;j<=Days;j++){
			tmp = (index+j) % 7;
			strTmp = calnDateFormat(intYear,intMonth,j);
			if(tmp == 0){
				outStr += "<td class='"+CHOLYCLS+"' onClick='setHoliday(this)'><input type='hidden' name='"+CHOLYID+"' value='"+strTmp+"'>"+j+"</td>"+"</tr>\r\n<tr align=center>";
				trNum++;
			}else if(tmp == 1){
				outStr += "<td class='"+CHOLYCLS+"' onClick='setHoliday(this)'><input type='hidden' name='"+CHOLYID+"' value='"+strTmp+"'>"+j+"</td>";
			}else{
				outStr += "<td class='"+CNOHOLYCLS+"' onClick='setHoliday(this)'><input type='hidden' name='"+CNOHOLYID+"' value='"+strTmp+"'>"+j+"</td>";
			}
		}//end [for]
	}//end [if-else][holyArr.length]

	//补足<td>
	tmp = (index+j-1) % 7;
	if(tmp!=0){
		for(td=tmp;td<7;td++){
			if(td+1==7){
				outStr += "<td class='caln-td-nodate'>&nbsp;</td></tr>\r\n";
			}else{
				outStr += "<td class='caln-td-nodate'>&nbsp;</td>";
			}
		}
	}
	//补足<tr>
	while(trNum<5){
		outStr += "<tr align=center><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>\r\n";
	}
//}
//alert(outStr);
	return outStr;
}


/*
* Notes: 获取指定日期对应的星期数
*/
function CL_getWeek(oDate,kind){
	var bak;
	var enWeekArr = new Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
	var cnWeekArr = new Array("日", "一", "二", "三", "四", "五", "六");
	var week = oDate.getDay();//getUTCDay();

	switch(kind){
		case 0://英文
			bak = enWeekArr[week];
			break;
		case 1://中文
			bak = cnWeekArr[week];
			break;
		case 2://数字
			bak = week;
			break;
		default:
			bak = week
	}

	return bak;
}

/*
* Notes: 获取指定月份的天数
* Parames:
* 		intMonth 从 1 开始
*/
function CL_getDays(intYear,intMonth){
    var dtmDate = new Date(intYear,intMonth,-1);
    var intDay = dtmDate.getDate() + 1;

    return intDay;
}

/*
* Notes: 返回指定的日期格式 8位数字，即 20030608
*/
function calnDateFormat(intYear,intMonth,intDay){
	var bak;

	bak = intYear.toString();
	if(intMonth<10){
		bak += "0" + intMonth;
	}else{
		bak += intMonth;
	}
	if(intDay<10){
		bak += "0" + intDay;
	}else{
		bak += intDay;
	}

	return bak;
}
-->
</script>

<link href="../css/text.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.caln-td-holiday {
	COLOR: #FF0000;
}
.caln-td-no-holiday {
	COLOR: #000000;
}
.caln-td-nodate {
	BACKGROUND-COLOR: #cccccc;
}
.caln-table{
	MARGIN: 0px; PADDING-BOTTOM: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px;
	WIDTH: 100%; HEIGHT: 200px;
}
-->
</style>
</head>
<%@ include file="./include/header.jsp"%>
<body  leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0"  onload="tab_refresh(1)">
<html:form action="/webapp/smsb/jjrwhAction.do" method="POST">
<html:hidden property="actionType" />
<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top"> <br>
      <table align="center" cellspacing="0" class="table-99" border="0">
        <tr class="black9">
          <td class="1-td1">节假日维护</td>
        </tr>
		<tr>
          <td class=1-td2>选择年份  <html:text property="whnf" onkeydown="if(window.event.keyCode==13) {doShowSubmit();return false;}" /> <input type="button" name="klll" value="显示" onclick="doShowSubmit();return false;">
</td></tr>
        <tr>
          <td valign="top" class="1-td2">
            <p>&nbsp; </p>
            <div align="center">
              <table width="80%" border="1" align="center">
                <tr>
                  <td width="10%" rowspan="2"> <div align="center">年度</div></td>
                  <td width="9%" rowspan="2"> <div align="center">月份</div></td>
                  <td colspan="7"> <div align="center"></div></td>
                  <td width="9%" rowspan="2"> <div align="center">月份</div></td>
                  <td colspan="7"> <div align="center"></div></td>
                </tr>
                <tr>
                  <td > <div align="center">S</div></td>
                  <td > <div align="center">M</div></td>
                  <td > <div align="center">T</div></td>
                  <td > <div align="center">W</div></td>
                  <td > <div align="center">T</div></td>
                  <td > <div align="center">F</div></td>
                  <td > <div align="center">S</div></td>
                  <td > <div align="center">S</div></td>
                  <td > <div align="center">M</div></td>
                  <td > <div align="center">T</div></td>
                  <td > <div align="center">W</div></td>
                  <td > <div align="center">T</div></td>
                  <td > <div align="center">F</div></td>
                  <td > <div align="center">S</div></td>
                </tr>
                <tr>
                  <td rowspan="6"> <div align="center">
				  		<script>
							document.write(CYEAR);
							var oYearVal = document.all('whnf')
							if(oYearVal){
								oYearVal.value=CYEAR;
							}
						</script></div></td>
                  <td> <div align="center">1</div></td>
                        <td colspan="7" id="caln_mon_1">
						</td>
                        <td> <DIV align=center>2</DIV></td>
                        <td colspan="7" id="caln_mon_2"></td>
                      </tr>
                      <tr>
                        <td> <DIV align=center>3</DIV></td>
                        <td colspan="7" id="caln_mon_3"></td>
                        <td> <DIV align=center>4</DIV></td>
                        <td colspan="7" id="caln_mon_4"></td>
                      </tr>
                      <tr>
                        <td> <DIV align=center>5</DIV></td>
                        <td colspan="7" id="caln_mon_5"></td>
                        <td> <DIV align=center>6</DIV></td>
                        <td colspan="7" id="caln_mon_6"></td>
                      </tr>
                      <tr>
                        <td> <DIV align=center>7</DIV></td>
                        <td colspan="7" id="caln_mon_7"></td>
                        <td> <DIV align=center>8</DIV></td>
                        <td colspan="7" id="caln_mon_8"></td>
                      </tr>
                      <tr>
                        <td> <DIV align=center>9</DIV></td>
                        <td colspan="7" id="caln_mon_9"></td>
                        <td> <DIV align=center>10</DIV></td>
                        <td colspan="7" id="caln_mon_10"></td>
                      </tr>
                      <tr>
                        <td> <DIV align=center>11</DIV></td>
                        <td colspan="7" id="caln_mon_11"></td>
                        <td> <DIV align=center>12</DIV></td>
                        <td colspan="7" id="caln_mon_12"></td>
                      </tr>
              </table>
              <table width="80%" border="0">
                <tr>
                  <td>注：<br>
                    当征期最后一天属于以上选中日期时，将往后顺延一天。<br>
                    默认假日操作和调整假日操作在点击‘保存’，‘生成征期日历’按钮以后保存数据库。</td>
                </tr>
              </table>
              <br>
            </div>
            <div align="center">
              <input type="image" src="<%=static_contextpath%>images/mrjr-d1.jpg" accesskey="d" alt="默认假日" name="Image2" onclick="tab_refresh(0);;return false;" border="0"  onMouseOver="this.src ='<%=static_contextpath%>images/mrjr-d2.jpg';" onMouseOut="this.src ='<%=static_contextpath%>images/mrjr-d1.jpg';">
              &nbsp;&nbsp;&nbsp;
              <input type="image" src="<%=static_contextpath%>images/bc-s1.jpg" accesskey="s" alt="保存" name="Image2" onclick="doSaveSubmit();return false;" border="0" id="Image2" onMouseOver="this.src ='<%=static_contextpath%>images/bc-s2.jpg';" onMouseOut="this.src ='<%=static_contextpath%>images/bc-s1.jpg';">
              &nbsp;&nbsp;&nbsp;
              <input type="image" name="Submit42" accesskey="k" value="保存" onClick="doCreateCalendarSubmit();return false;" onMouseOver="MM_swapImage('Submit423','','<%=static_contextpath%>images/sczqrl-k2.jpg',1)" onMouseOut="MM_swapImgRestore()"  src="<%=static_contextpath%>images/sczqrl-k1.jpg" width="116" height="22"  id="Submit423" alt="保存" >
              &nbsp;&nbsp;&nbsp; <input type="image" src="<%=static_contextpath%>images/fh-f1.jpg" accesskey="f" alt="返回" name="Image2" onclick='doSubmitForm("Return");return false;' border="0" id="Image2" onMouseOver="this.src ='<%=static_contextpath%>images/fh-f2.jpg';" onMouseOut="this.src ='<%=static_contextpath%>images/fh-f1.jpg';"></a></div>
            <br> <br>
             </td>
        </tr>
      </table>
      <br></td>
    </tr>
</table>

</html:form>
<%@ include file="./include/footer.jsp"%>
<script language="javascript">
//显示提交
function doShowSubmit()
{
  //维护年份Check
  var strDate =document.all.whnf.value;
  if(isFullDate(strDate,1,"Notempty",true)==false){
    return false;
  }
//  if(doSubmitForm("Show")==false){
//    return false;
//  }
  doSubmitForm("Show");
}
//保存提交
function doSaveSubmit()
{
  //维护年份Check
  var strDate =document.all.whnf.value;
  if(isFullDate(strDate,1,"Notempty",true)==false){
    return false;
  }
//  if(doSubmitForm("Save")==false){
//    return false;
//  }
  doSubmitForm("Save");
}
//生成征期日历
function doCreateCalendarSubmit()
{
  //维护年份Check
  var strDate =document.all.whnf.value;
  if(isFullDate(strDate,1,"Notempty",true)==false){
    return false;
  }
  if(window.confirm("该操作将修改数据库中的数据,且不能自动恢复,请确认")){
    document.forms[0].actionType.value = "CreateCalendar";
    document.forms[0].submit();
  }
}

function fnOnLoad()
{
   document.forms[0].whnf.focus();
}
fnOnLoad();
</script>
</body>
</html>
