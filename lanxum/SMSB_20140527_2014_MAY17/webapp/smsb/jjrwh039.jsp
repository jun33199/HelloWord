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

<title>�ڼ���ά��</title>

<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/list.js">
</script>
<script language="JavaScript">
<!--
//--------------- ���³��������������� -------------------------//
/*
* Version: 0.9.00
*/
<%
String cyear = (String)request.getAttribute("CYEAR");
%>
<!--
//--------------- ���³��������������� -------------------------//
/*
* Version: 0.9.10
*/
//���
CYEAR = <%=cyear%>;;
//���յ�id
CHOLYID = "holiday-id";
//���յ�class
CHOLYCLS = "caln-td-holiday";
//�Ǽ��յ�id
CNOHOLYID = "no-holiday-id";
//�Ǽ��յ�class
CNOHOLYCLS = "caln-td-no-holiday";
//����������ʽ
CALNTABCLS = "caln-table";
//--------------- ���ϳ��������������� -------------------------//
//��������
var holyIndex = 0;
var holyStr;
//Ҫ�󣺰����������Ա���������ڸ�ʽ�� 20030609
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
* Notes: ��굥���¼��������Ƿ����
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
* Notes: ִ���������
* Version: 0.9.10
* Parame: kind 0,Ĭ������;
*/
function tab_refresh(kind){
	if(kind==0){
		holyArr = new Array();
	}
	//ȫ��Ĭ�ϼ���
	outCaln(CYEAR);
}

/*
* Notes: ���ȫ��Ĭ�ϼ���
* Version: 0.9.00
*/
function outCaln(intYear){
	var monTab = "";

	for(i=0;i<12;i++){
		//��ȡ�ַ���
		monTab = outCalnTable(intYear,i+1);
		//�����class=CALNTABCLS�����ڸ��ı����ʽ
		monTab = "<table border='1' bordercolordark='#000000' bordercolorlight='#FFFFFF' width='100%' height='180' border='1' cellpadding='0' cellspacing='0'>" + monTab + "</table>";
		//��̬���
		eval("caln_mon_"+(i+1)).innerHTML = monTab;
	}
}

/*
* Notes: ����ָ�������£���ȡ���ձ������Ľ���ַ�����
*        ����ַ�����<tr><td>��ɣ�
*        ʹ��ʱ��Ҫ��<table></table>�������ַ���������<table>����ַ���</table>
* Parames:
* 		intYear ��ݣ�4λ�꣬eg: 1999
* 		intMonth �·ݣ��� 1 ��ʼ
*/
function outCalnTable(intYear,intMonth){
var outStr = "";
//ĳ�¿�ʼ��������
var startWeek;
//ĳ�µ�����
var Days;

var index,trNum,strTmp;
//for(i=0;i<12;i++){
	index = 0;
	trNum = 1;
	startWeek = CL_getWeek(new Date(CYEAR,intMonth-1,1));
	Days = CL_getDays(CYEAR,intMonth,-1);

	//print
	//��λ��ָ��������
	outStr += "<tr align=center>";
	for(k=0;k<startWeek;k++){
		outStr += "<td class='caln-td-nodate'>&nbsp;</td>";
		index++;
	}

	//�Ƿ����м��ռ�¼
	if(holyArr.length){//�м��ռ�¼�����ݼ�¼���
		holyStr = holyArr[holyIndex];
		for(j=1;j<=Days;j++){
			tmp = (index+j) % 7;
			strTmp = calnDateFormat(intYear,intMonth,j);//���ڸ�ʽ�� 20030609
			if(tmp == 0){//����
				if(holyStr==strTmp){//������ͬ�����б��
					//��ȡ��һ����¼
					holyIndex++;
					//�����һ��ֵ�뵱ǰֵ��ͬ������ȡ��һ��
					while(holyStr == holyArr[holyIndex]){
						holyIndex++;
					}
					holyStr = holyArr[holyIndex];

					//ҳ������ַ���
					outStr += "<td class='"+CHOLYCLS+"' onClick='setHoliday(this)'><input type='hidden' name='"+CHOLYID+"' value='"+strTmp+"'>"+j+"</td>"+"</tr>\r\n<tr align=center>";
				}else{
					outStr += "<td class='"+CNOHOLYCLS+"' onClick='setHoliday(this)'><input type='hidden' name='"+CNOHOLYID+"' value='"+strTmp+"'>"+j+"</td>"+"</tr>\r\n<tr align=center>";
				}
				//������ 1 ,
				trNum++;
			}else{
				if(holyStr==strTmp){
					holyIndex++;
					//�����һ��ֵ�뵱ǰֵ��ͬ������ȡ��һ��
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

	}else{//���Ĭ�ϼ��գ��������������
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

	//����<td>
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
	//����<tr>
	while(trNum<5){
		outStr += "<tr align=center><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>\r\n";
	}
//}
//alert(outStr);
	return outStr;
}


/*
* Notes: ��ȡָ�����ڶ�Ӧ��������
*/
function CL_getWeek(oDate,kind){
	var bak;
	var enWeekArr = new Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
	var cnWeekArr = new Array("��", "һ", "��", "��", "��", "��", "��");
	var week = oDate.getDay();//getUTCDay();

	switch(kind){
		case 0://Ӣ��
			bak = enWeekArr[week];
			break;
		case 1://����
			bak = cnWeekArr[week];
			break;
		case 2://����
			bak = week;
			break;
		default:
			bak = week
	}

	return bak;
}

/*
* Notes: ��ȡָ���·ݵ�����
* Parames:
* 		intMonth �� 1 ��ʼ
*/
function CL_getDays(intYear,intMonth){
    var dtmDate = new Date(intYear,intMonth,-1);
    var intDay = dtmDate.getDate() + 1;

    return intDay;
}

/*
* Notes: ����ָ�������ڸ�ʽ 8λ���֣��� 20030608
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
          <td class="1-td1">�ڼ���ά��</td>
        </tr>
		<tr>
          <td class=1-td2>ѡ�����  <html:text property="whnf" onkeydown="if(window.event.keyCode==13) {doShowSubmit();return false;}" /> <input type="button" name="klll" value="��ʾ" onclick="doShowSubmit();return false;">
</td></tr>
        <tr>
          <td valign="top" class="1-td2">
            <p>&nbsp; </p>
            <div align="center">
              <table width="80%" border="1" align="center">
                <tr>
                  <td width="10%" rowspan="2"> <div align="center">���</div></td>
                  <td width="9%" rowspan="2"> <div align="center">�·�</div></td>
                  <td colspan="7"> <div align="center"></div></td>
                  <td width="9%" rowspan="2"> <div align="center">�·�</div></td>
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
                  <td>ע��<br>
                    �������������һ����������ѡ������ʱ��������˳��һ�졣<br>
                    ����Ĭ�ϼ��ղ����͵������ղ����ڵ�������桯��������������������ť�Ժ󱣴����ݿ⡣</td>
                </tr>
              </table>
              <br>
            </div>
            <div align="center">
              <input type="image" src="<%=static_contextpath%>images/mrjr-d1.jpg" accesskey="d" alt="Ĭ�ϼ���" name="Image2" onclick="tab_refresh(0);;return false;" border="0"  onMouseOver="this.src ='<%=static_contextpath%>images/mrjr-d2.jpg';" onMouseOut="this.src ='<%=static_contextpath%>images/mrjr-d1.jpg';">
              &nbsp;&nbsp;&nbsp;
              <input type="image" src="<%=static_contextpath%>images/bc-s1.jpg" accesskey="s" alt="����" name="Image2" onclick="doSaveSubmit();return false;" border="0" id="Image2" onMouseOver="this.src ='<%=static_contextpath%>images/bc-s2.jpg';" onMouseOut="this.src ='<%=static_contextpath%>images/bc-s1.jpg';">
              &nbsp;&nbsp;&nbsp;
              <input type="image" name="Submit42" accesskey="k" value="����" onClick="doCreateCalendarSubmit();return false;" onMouseOver="MM_swapImage('Submit423','','<%=static_contextpath%>images/sczqrl-k2.jpg',1)" onMouseOut="MM_swapImgRestore()"  src="<%=static_contextpath%>images/sczqrl-k1.jpg" width="116" height="22"  id="Submit423" alt="����" >
              &nbsp;&nbsp;&nbsp; <input type="image" src="<%=static_contextpath%>images/fh-f1.jpg" accesskey="f" alt="����" name="Image2" onclick='doSubmitForm("Return");return false;' border="0" id="Image2" onMouseOver="this.src ='<%=static_contextpath%>images/fh-f2.jpg';" onMouseOut="this.src ='<%=static_contextpath%>images/fh-f1.jpg';"></a></div>
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
//��ʾ�ύ
function doShowSubmit()
{
  //ά�����Check
  var strDate =document.all.whnf.value;
  if(isFullDate(strDate,1,"Notempty",true)==false){
    return false;
  }
//  if(doSubmitForm("Show")==false){
//    return false;
//  }
  doSubmitForm("Show");
}
//�����ύ
function doSaveSubmit()
{
  //ά�����Check
  var strDate =document.all.whnf.value;
  if(isFullDate(strDate,1,"Notempty",true)==false){
    return false;
  }
//  if(doSubmitForm("Save")==false){
//    return false;
//  }
  doSubmitForm("Save");
}
//������������
function doCreateCalendarSubmit()
{
  //ά�����Check
  var strDate =document.all.whnf.value;
  if(isFullDate(strDate,1,"Notempty",true)==false){
    return false;
  }
  if(window.confirm("�ò������޸����ݿ��е�����,�Ҳ����Զ��ָ�,��ȷ��")){
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
