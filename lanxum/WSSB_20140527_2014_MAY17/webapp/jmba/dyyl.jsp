<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="shtml" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import="com.ttsoft.bjtax.shenbao.zhsb.SessionKey"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import="com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO"%>
<%@page import="com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO"%>
<%@page import="com.ttsoft.bjtax.shenbao.constant.CodeTable"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.domain.Swjgzzjg"%>
<%@page import="com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil"%>

<jsp:useBean id="jmbavo" scope="request" class="com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO"/>

<%
  pageContext.setAttribute("jmbazb", jmbavo.getJmsbajl().get(0));
JmbaZbVO jmbazb=(JmbaZbVO)jmbavo.getJmsbajl().get(0);
String sehbl=jmbazb.getBajmse();
if (sehbl==null || sehbl.equals("")){
  sehbl=jmbazb.getBajmbl();
}


String jgmc=((Swjgzzjg)(CodeTableUtil.getCodeTableMap(request, CodeTable.SWJJZZJG_MAP).get(jmbavo.getNsrxx().getSwjgzzjgdm().substring(0,2)+"00"))).getSwjgzzjgmc();
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="JavaScript" type="text/JavaScript">
     
      
        function doPrint() {
	if(confirm("是否打印？")){
        window.print();
    }
      }
function doReturn()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="EditZb";
	document.forms[0].basxdm.value=document.forms[0].jmbasxdm.value;
	document.forms[0].submit();
} 
       
      </script>
<title>北京市地方税务局</title>
<style>
<!--
div.Section1
	{page:Section1;}
 p.MsoNormal
	{mso-style-parent:"";
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	font-size:10.5pt;
	font-family:"Times New Roman";
	margin-left:0cm; margin-right:0cm; margin-top:0cm}
 table.MsoNormalTable
	{mso-style-parent:"";
	font-size:10.0pt;
	font-family:"Times New Roman";
	}
div.Section2
	{page:Section2;}
-->
</style>
<STYLE>  
          div.page   {   page-break-after:   always   }  
</STYLE>  

</head>

<body style="text-align: center">
<form name="form1" method="POST" action="/shenbao/jmbaz.dc">
		<input	name="actionType" type="hidden" id="actionType"> 
         <input name="basxdm" type="hidden" id="basxdm"></input>
<shtml:hidden name="jmbazb" property="jmbasxdm"  />
<shtml:hidden name="jmbazb" property="basqwsh"  />
<div class="Section1" style="layout-grid: 15.6pt">
<p class="MsoNormal" align="center"
	style="text-align: center; line-height: 30.0pt"><b><span
	style="font-family: 宋体"><%=jgmc%>减免税备案表</span></b></p>
<p class="MsoNormal" align="center"
	style="text-align: center; line-height: 30.0pt"></p>
<table class="MsoNormalTable" border="1" cellspacing="0" cellpadding="0"
	width="671"
	style="width: 503.2pt; border-collapse: collapse; border: medium none; margin-left: 19.6pt"
	height="459">
	<tr style="page-break-inside: avoid; height: 34.05pt">
		<td width="132"
			style="width: 99.2pt; height: 34.05pt; border: 1.0pt solid windowtext; padding-left: 5.4pt; padding-right: 5.4pt; padding-top: 0cm; padding-bottom: 0cm">
		<p class="MsoNormal" align="center"
			style="text-align: center; margin-left: 1.5pt"><b><span
			style="font-family: 宋体">提交人</span></b>
		</td>
		<td width="180"
			style="width: 134.7pt; height: 34.05pt; border-left: medium none; border-right: 1.0pt solid windowtext; border-top: 1.0pt solid windowtext; border-bottom: 1.0pt solid windowtext; padding-left: 5.4pt; padding-right: 5.4pt; padding-top: 0cm; padding-bottom: 0cm">
		<p class="MsoNormal" align="center" style="text-align: center">
	<bean:write name="jmbavo" property="nsrxx.nsrmc"  />
		
		
		</td>
		<td width="208"
			style="width: 155.9pt; height: 34.05pt; border-left: medium none; border-right: 1.0pt solid windowtext; border-top: 1.0pt solid windowtext; border-bottom: 1.0pt solid windowtext; padding-left: 5.4pt; padding-right: 5.4pt; padding-top: 0cm; padding-bottom: 0cm">
		<p class="MsoNormal" align="center" style="text-align: center"><b>
		<span style="font-family: 宋体">计算机代码</span></b>
		</td>
		<td width="151"
			style="width: 4.0cm; height: 34.05pt; border-left: medium none; border-right: 1.0pt solid windowtext; border-top: 1.0pt solid windowtext; border-bottom: 1.0pt solid windowtext; padding-left: 5.4pt; padding-right: 5.4pt; padding-top: 0cm; padding-bottom: 0cm">
		<p align="center" class="MsoNormal" style="text-align: center"><span
			style="font-family: 宋体">
				<bean:write name="jmbavo" property="nsrxx.jsjdm"  />
			
			</span>
		</td>
	</tr>
	<tr style="page-break-inside: avoid; height: 22.3pt">
		<td width="132"
			style="width: 99.2pt; height: 22.3pt; border-left: 1.0pt solid windowtext; border-right: 1.0pt solid windowtext; border-top: medium none; border-bottom: 1.0pt solid windowtext; padding-left: 5.4pt; padding-right: 5.4pt; padding-top: 0cm; padding-bottom: 0cm">
		<p class="MsoNormal" align="center" style="text-align: center"><b>
		<span style="font-family: 宋体">税种</span></b>
		</td>
		<td width="180"
			style="width: 134.7pt; height: 22.3pt; border-left: medium none; border-right: 1.0pt solid windowtext; border-top: medium none; border-bottom: 1.0pt solid windowtext; padding-left: 5.4pt; padding-right: 5.4pt; padding-top: 0cm; padding-bottom: 0cm">
		<p class="MsoNormal" align="center" style="text-align: center"><b>
		<span style="font-family: 宋体">减免税事项</span></b>
		</td>
		<td width="208"
			style="width: 155.9pt; height: 22.3pt; border-left: medium none; border-right: 1.0pt solid windowtext; border-top: medium none; border-bottom: 1.0pt solid windowtext; padding-left: 5.4pt; padding-right: 5.4pt; padding-top: 0cm; padding-bottom: 0cm">
		<p class="MsoNormal" align="center" style="text-align: center"><b>
		<span style="font-family: 宋体">起止时间</span></b>
		</td>
		<td width="151"
			style="width: 4.0cm; height: 22.3pt; border-left: medium none; border-right: 1.0pt solid windowtext; border-top: medium none; border-bottom: 1.0pt solid windowtext; padding-left: 5.4pt; padding-right: 5.4pt; padding-top: 0cm; padding-bottom: 0cm">
		<p class="MsoNormal" align="center" style="text-align: center"><b>
		<span style="font-family: 宋体">备案减免税额或比例</span></b>
		</td>
	</tr>
	<tr style="page-break-inside: avoid; height: 35.0pt">
		<td width="132"
			style="width: 99.2pt; height: 35.0pt; border-left: 1.0pt solid windowtext; border-right: 1.0pt solid windowtext; border-top: medium none; border-bottom: 1.0pt solid windowtext; padding-left: 5.4pt; padding-right: 5.4pt; padding-top: 0cm; padding-bottom: 0cm">
		<p class="MsoNormal" align="center"><span style="font-family: 宋体">
			<bean:write name="jmbazb" property="szmc"  />
</span>
		</td>
		<td width="180"
			style="width: 134.7pt; height: 35.0pt; border-left: medium none; border-right: 1.0pt solid windowtext; border-top: medium none; border-bottom: 1.0pt solid windowtext; padding-left: 5.4pt; padding-right: 5.4pt; padding-top: 0cm; padding-bottom: 0cm">
		<p class="MsoNormal" align="center" style="text-align: center"><span
			style="font-family: 宋体">
			<bean:write name="jmbazb" property="jmbasxmc"  />

			</span>
		</td>
		<td width="208"
			style="width: 155.9pt; height: 35.0pt; border-left: medium none; border-right: 1.0pt solid windowtext; border-top: medium none; border-bottom: 1.0pt solid windowtext; padding-left: 5.4pt; padding-right: 5.4pt; padding-top: 0cm; padding-bottom: 0cm">
		<p class="MsoNormal" align="center" style="text-align: center"><span
			style="font-family: 宋体">起始日期：</span>
			<span lang="en-us">	<%=jmbazb.getQsrq().substring(0,4)%>
</span><span
			style="font-family: 宋体">年</span>
			<span lang="en-us"><%=jmbazb.getQsrq().substring(5,7)%></span><span
			style="font-family: 宋体">月</span>
			<span lang="en-us"><%=jmbazb.getQsrq().substring(8,10)%></span><span
			style="font-family: 宋体">日</span></p>
		<p class="MsoNormal" align="center" style="text-align: center"><span
			style="font-family: 宋体">截至日期：</span><span lang="en-us"><%=jmbazb.getJzrq().substring(0,4)%></span><span
			style="font-family: 宋体">年</span><span lang="en-us"><%=jmbazb.getJzrq().substring(5,7)%></span><span
			style="font-family: 宋体">月</span><span lang="en-us"><%=jmbazb.getJzrq().substring(8,10)%></span><span
			style="font-family: 宋体">日</span>
		</td>
		<td width="151"
			style="width: 4.0cm; height: 35.0pt; border-left: medium none; border-right: 1.0pt solid windowtext; border-top: medium none; border-bottom: 1.0pt solid windowtext; padding-left: 5.4pt; padding-right: 5.4pt; padding-top: 0cm; padding-bottom: 0cm">
		<p class="MsoNormal" align="center" style="text-align: center"><span
			style="font-family: 宋体">			<%=sehbl%>
</span>
		</td>
	</tr>
	<tr style="page-break-inside: avoid; height: 408.4pt">
		<td width="671" colspan="4" valign="top"
			style="width: 503.2pt; height: 337px; border-left: 1.0pt solid windowtext; border-right: 1.0pt solid windowtext; border-top: medium none; border-bottom: 1.0pt solid windowtext; padding-left: 5.4pt; padding-right: 5.4pt; padding-top: 0cm; padding-bottom: 0cm">
		<p class="MsoNormal" style="line-height: 30.0pt"><b> <span
			style="font-size: 14.0pt; font-family: 宋体">减免税政策的执行情况：</span></b></p>
		<p class="MsoNormal" style="text-indent: 28.0pt; line-height: 30.0pt">
		<span style="font-size: 14.0pt; font-family: 宋体">我单位发生企业所得税</span><u><span
			lang="EN-US" style="font-size: 14.0pt">&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span lang="EN-US"
			style="font-size: 14.0pt"><bean:write name="jmbazb" property="jmbasxmc"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><span
			style="font-size: 14.0pt; font-family: 宋体">减免税事项，符合</span><u><span
			lang="EN-US" style="font-size: 14.0pt">&nbsp;
		&nbsp;&nbsp;&nbsp;<bean:write name="jmbazb" property="fhwjmc"  /> &nbsp;</span></u><span
			style="font-size: 14.0pt; font-family: 宋体">文件的相关规定，现提出减免税备案申请。</span><b><span
			lang="EN-US" style="font-size: 14.0pt">&nbsp;&nbsp;</span></b></p>
		<p class="MsoNormal" style="line-height: 30.0pt"><b> <span
			lang="EN-US" style="font-size: 14.0pt">&nbsp;</span></b></p>
		<p class="MsoNormal" style="line-height: 30.0pt"><b> <span
			lang="EN-US" style="font-size: 14.0pt">&nbsp;</span></b></p>
		<p class="MsoNormal" align="center"
			style="text-align: center; text-indent: 292.35pt; line-height: 30.0pt">
		<b><span style="font-size: 14.0pt; font-family: 宋体">提交人（签章）</span></b></p>
		<p class="MsoNormal" align="center"
			style="text-align: center; text-indent: 313.55pt; line-height: 30.0pt">
		</p>
		<p class="MsoNormal" align="center"
			style="text-align: center; text-indent: 313.55pt; line-height: 30.0pt">
		<b><span style="font-size: 14.0pt; font-family: 宋体"><%=jmbazb.getLrrq().substring(0,4)%>年</span><span
			lang="EN-US" style="font-size: 14.0pt">&nbsp; <%=jmbazb.getLrrq().substring(5,7)%></span><span
			style="font-size: 14.0pt; font-family: 宋体">月</span><span lang="EN-US"
			style="font-size: 14.0pt">&nbsp;&nbsp; </span><span
			style="font-size: 14.0pt; font-family: 宋体"><%=jmbazb.getLrrq().substring(8,10)%>日</span></b></p>
		</td>
	</tr>
</table>
</div>

		<div class="page">  


<div class="Section2" style="layout-grid: 15.6pt">
<p class="MsoNormal" align="center" style="text-align: center"><b>
<span style="font-family: 宋体">减免税备案资料清单</span></b></p>
<p class="MsoNormal" align="center" style="text-align: center"></p>
<table class="MsoNormalTable" border="1" cellspacing="0" cellpadding="0"
	style="border-collapse: collapse; border: medium none; margin-left: 22.25pt"
	width="675">
	<tr style="page-break-inside: avoid">
		<td width="55" valign="top"
			style="width: 41.4pt; border: 1.0pt solid windowtext; padding-left: 5.4pt; padding-right: 5.4pt; padding-top: 0cm; padding-bottom: 0cm">
		<p class="MsoNormal" align="center" style="text-align: center"><b>
		<span style="font-family: 宋体">序号</span></b>
		</td>
		<td colspan="2" valign="top"
			style="width: 588px; border-left: medium none; border-right: 1.0pt solid windowtext; border-top: 1.0pt solid windowtext; border-bottom: 1.0pt solid windowtext; padding-left: 5.4pt; padding-right: 5.4pt; padding-top: 0cm; padding-bottom: 0cm">
		<p class="MsoNormal" align="center" style="text-align: center"><b>
		<span style="font-family: 宋体">资料名称</span></b>
		</td>
	</tr>

<logic:iterate id="element" name="jmbazb" property="bajlzlqd" indexId="index">
	<tr style="page-break-inside: avoid">
		<td width="55" valign="top"
			style="width: 41.4pt; border-left: 1.0pt solid windowtext; border-right: 1.0pt solid windowtext; border-top: medium none; border-bottom: 1.0pt solid windowtext; padding-left: 5.4pt; padding-right: 5.4pt; padding-top: 0cm; padding-bottom: 0cm"
			height="22">
		<p class="MsoNormal" align="center" style="text-align: center"><b>
		<span lang="EN-US"><%=(index.intValue()+1)%></span></b>
		</td>
		<td colspan="2" valign="top"
			style="width: 588px; border-left: medium none; border-right: 1.0pt solid windowtext; border-top: medium none; border-bottom: 1.0pt solid windowtext; padding-left: 5.4pt; padding-right: 5.4pt; padding-top: 0cm; padding-bottom: 0cm"
			height="22">
		<p class="MsoNormal" align="center"><span style="font-family: 宋体">
		<bean:write name="element" property="zlqd"/></span>
		</td>
	</tr>

</logic:iterate>


	<tr style="page-break-inside: avoid">
		<td colspan="3" valign="top"
			style="width: 659px; border-left: 1.0pt solid windowtext; border-right: 1.0pt solid windowtext; border-top: medium none; border-bottom: 1.0pt solid windowtext; padding-left: 5.4pt; padding-right: 5.4pt; padding-top: 0cm; padding-bottom: 0cm">
		</td>
	</tr>
	<tr style="page-break-inside: avoid; height: 18.65pt">
		<td colspan="3" valign="top"
			style="width: 659px; height: 18.65pt; border-left: 1.0pt solid windowtext; border-right: 1.0pt solid windowtext; border-top: medium none; border-bottom: 1.0pt solid windowtext; padding-left: 5.4pt; padding-right: 5.4pt; padding-top: 0cm; padding-bottom: 0cm">
		<p class="MsoNormal"><b><span style="font-family: 宋体">
		声明：以上提供的情况及报送的材料真实可靠，本纳税单位及法定代表人负责承担相应责任。</span></b>
		</td>
	</tr>
	<tr style="page-break-inside: avoid; height: 94.4pt">
		<td width="334" colspan="2" valign="top"
			style="width: 250.25pt; height: 94.4pt; border-left: 1.0pt solid windowtext; border-right: 1.0pt solid windowtext; border-top: medium none; border-bottom: 1.0pt solid windowtext; padding-left: 5.4pt; padding-right: 5.4pt; padding-top: 0cm; padding-bottom: 0cm">
		<p class="MsoNormal"><b><span style="font-family: 宋体">经办人：</span></b></p>
		<p class="MsoNormal"><b><span style="font-family: 宋体">纳税人（签章）：</span></b></p>
		<p class="MsoNormal" align="center"
			style="text-align: center; line-height: 30.0pt"></p>
		<p class="MsoNormal" align="center"
			style="text-align: center; text-indent: 112.25pt"><b><span
			style="font-family: 宋体"></span><span lang="EN-US">
		&nbsp; </span><span style="font-family: 宋体"></span><span lang="EN-US">
		&nbsp; </span><span style="font-family: 宋体"></span></b></p>
		<p class="MsoNormal" align="center"
			style="text-align: center; text-indent: 105.2pt; line-height: 30.0pt">
		</td>
		<td valign="top"
			style="width: 310px; height: 94.4pt; border-left: medium none; border-right: 1.0pt solid windowtext; border-top: medium none; border-bottom: 1.0pt solid windowtext; padding-left: 5.4pt; padding-right: 5.4pt; padding-top: 0cm; padding-bottom: 0cm">
		<p class="MsoNormal"><b><span style="font-family: 宋体">经办人：</span></b></p>
		<p class="MsoNormal"><b><span style="font-family: 宋体">税务机关（签章）：</span></b></p>
		<p class="MsoNormal" align="center"
			style="text-align: center; line-height: 30.0pt"><b><span
			lang="EN-US" style="color: blue">&nbsp;</span></b></p>
		<p class="MsoNormal" align="center"
			style="text-align: center; text-indent: 112.25pt"><b><span
			style="font-family: 宋体"></span><span lang="EN-US">
		&nbsp; </span><span style="font-family: 宋体"></span><span lang="EN-US">
		&nbsp; </span><span style="font-family: 宋体"></span></b></p>
		<p class="MsoNormal" align="center"
			style="text-align: center; text-indent: 105.2pt; line-height: 30.0pt">
		</td>
	</tr>
	<tr height="0">
		<td width="55" style="border: medium none"></td>
		<td width="278" style="border: medium none"></td>
		<td width="325" style="border: medium none"></td>
	</tr>
</table>

<table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0"
	style="border-collapse: collapse; border: medium none; margin-left: 22.25pt"
	width="675" id="table1">
	<tr>
		<td><span style="font-family: 宋体">
		注：本表及《减免税备案表》一式两份，一份由税务机关交纳税人作为收到备案材料的证明，一份由税务机关留存归档。 </span></td>
	</tr>
</table>
</br>
</br>
<table width="100%" border="0" align="center">
	<tr align="center">

		<td width="20%">&nbsp;</td>

		<td>
			<img src="<%=static_contextpath%>images/dayin1.jpg" onmouseover="this.src='<%=static_contextpath%>images/dayin2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/dayin1.jpg'" alt="打印" onclick="doPrint()" style="cursor:hand"></td>
		<td><img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onclick="doReturn()" style="cursor:hand"></td>
		<td width="20%">&nbsp;</td>
	</tr>
</table>
</form>
<p class="MsoNormal" style="text-indent: 21.1pt; margin-left: 14.15pt"><b>
<span style="font-family: 宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></b>
</div>

</body>

</html>
