<%@page contentType="text/html; charset=GBK"%>
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
if (sehbl==null ){
  sehbl="";
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
    if (confirm("是否打印？")) {
      document.all.buttondiv1.style.display = "none";
      document.all.buttondiv2.style.display = "none";
      
      window.print();
      
      document.all.buttondiv1.style.display = "block";
      document.all.buttondiv2.style.display = "block";
    }
  }
  function doReturn() {
    document.forms[0].action = "/shenbao/jmbaz.dc";
    document.forms[0].actionType.value = "ViewZb";
    document.forms[0].basxdm.value = document.forms[0].jmbasxdm.value;
    document.forms[0].submit();
  }
</script>
<title>减免税备案表文书打印预览</title>
<link href="css/jmba.css" rel="stylesheet"	type="text/css">
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

<table border="0" cellpadding="0" cellspacing="0" height="800">
<tr>
	<td valign="top">
		<div class="Section1" style="layout-grid: 15.6pt">
<p class="MsoNormal" align="center"
	style="text-align: center; line-height: 30.0pt"><b><span
	style="font-family: 宋体"><%=jgmc%>减免税备案表</span></b></p>
<p class="MsoNormal" align="center"
	style="text-align: center; line-height: 30.0pt"></p>
<table class="MsoNormalTable" border=1 cellspacing=0 cellpadding=0 width=593
 style="margin-left:19.6pt;border-collapse:collapse"
	height="700">
	<tr style="page-break-inside: avoid; height: 34.05pt">
	  <td class="2-td2-t-left" width="13%">
	    <p class="MsoNormal" align="center" style="text-align: center; margin-left: 1.5pt">
	      <b>
	        <span style="font-family: 宋体">
	          提交人
	        </span>
	      </b>
	  </td>
	  <td  colspan="2" width="45%">
	    <div align="center">
	      <bean:write name="jmbavo" property="nsrxx.nsrmc" />
	    </div>
	  </td>
	  <td class="2-td2-t-left" width="17%">
	  	<div align="center"><b><span style="font-family: 宋体">计算机代码</span></b></div>
	  </td>
	  <td width="25%">
	    <p align="center" class="MsoNormal" style="text-align: center">
	      <span style="font-family: 宋体">
	        <bean:write name="jmbavo" property="nsrxx.jsjdm" />
	      </span>
	  </td>
	</tr>
	<tr style="page-break-inside: avoid; height: 22.3pt">
	  <td  width="13%">
	    <p class="MsoNormal" align="center" style="text-align: center">
	      <b>
	        <span style="font-family: 宋体">
	          税种
	        </span>
	      </b>
	  </td>
	  <td width="32%">
	    <p class="MsoNormal" align="center" style="text-align: center">
	      <b>
	        <span style="font-family: 宋体">
	          减免税事项
	        </span>
	      </b>
	  </td>
	  <td colspan="2" width="30%">
	    <p class="MsoNormal" align="center" style="text-align: center">
	      <b>
	        <span style="font-family: 宋体">
	          起止时间
	        </span>
	      </b>
	  </td>
	  <td width="25%">
	    <p class="MsoNormal" align="center" style="text-align: center">
	      <b>
	        <span style="font-family: 宋体">
	          备案减免税额或比例
	        </span>
	      </b>
	  </td>
	</tr>
	<tr style="page-break-inside: avoid; height: 35.0pt">
	  <td>
	    <div align="center">
	      <bean:write name="jmbazb" property="szmc" />
	    </div>
	  </td>
	  <td>
	    <p class="MsoNormal" align="center" style="text-align: center">
	    	<div align="left">
	      <span style="font-family: 宋体">
	        <bean:write name="jmbazb" property="jmbasxmc" />
	      </span>
	    </div>
	  </td>
	  <td colspan="2">
	    <p class="MsoNormal" align="center" style="text-align: center">
	      <span style="font-family: 宋体">
	        起始日期：<%=jmbazb.getQsrq().substring(0,4)%>年<%=jmbazb.getQsrq().substring(5,7)%>月<%=jmbazb.getQsrq().substring(8,10)%>日
	      </span>
	    </p>
	    <p class="MsoNormal" align="center" style="text-align: center">
	      <span style="font-family: 宋体">
	        截至日期：<%=jmbazb.getJzrq().substring(0,4)%>年<%=jmbazb.getJzrq().substring(5,7)%>月<%=jmbazb.getJzrq().substring(8,10)%>日
	      </span>
	  </td>
	  <td>
	    <p class="MsoNormal" align="center" style="text-align: center">
	      <span style="font-family: 宋体">
	        <%=sehbl%>
	      </span>
	  </td>
	</tr>
	<tr style="page-break-inside: avoid; height: 470.4pt">
  <td width=593 colspan=5>
    <table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
      <tr>
        <td colspan="2" height="70%" valign="top" >
        	<div align="left">
	          <p class="MsoNormal" style="line-height: 30.0pt">
	            <b>
	              <span style="font-size: 14.0pt; font-family: 宋体">
	                减免税政策的执行情况：
	              </span>
	            </b>
	          </p>
	          <p class="MsoNormal" style="text-indent: 28.0pt; line-height: 30.0pt">
	            <span style="word-break: break-all; word-wrap:break-word;font-size: 12.0pt; font-family: 宋体 ">
	              <bean:write name="jmbazb" property="fhwjmc" />
	            </span>
	          </p>
        	</div>
        </td>
      </tr>
      <tr>
      	<td width="60%"  height="30%" valign="top">
      	</td>
        <td  width="40%" height="30%" valign="top">
        	<div align="left">
	           <b>
	              <span style='font-size:14.0pt;font-family:宋体'>
	                提交人（签章）
	              </span>
	              <br>	       
	              <span style="font-size: 14.0pt; font-family: 宋体">
	                <%=jmbazb.getLrrq().substring(0,4)%>年<%=jmbazb.getLrrq().substring(5,7)%>月<%=jmbazb.getLrrq().substring(8,10)%>日
	              </span>
	            </b>
	          
	        </div>
        </td>
      </tr>
    </table>
  </td>
</tr>
</table>
</div>

	</td>
</tr>
<tr>
	<td valign="bottom">
		<div align="center">
			<span style="font-size: 10.0pt; font-family: 宋体">第1页 共2页</span>
		</div>
	</td>
</tr>
</table>




<div style="PAGE-BREAK-AFTER: always"></div>
<br/>
<br/>
<div class="Section2" style="layout-grid: 15.6pt">
<p class="MsoNormal" align="center" style="text-align: center"><b>
<span style="font-family: 宋体">减免税备案资料清单</span></b></p>
<p class="MsoNormal" align="center" style="text-align: center"></p>
<table class="MsoNormalTable" border="1" cellspacing="0" cellpadding="0"
	style="border-collapse: collapse; border: medium none; margin-left: 22.25pt"
	width="605">
	<tr style="page-break-inside: avoid">
		<td width="8%"  valign="top"
			style="width: 41.4pt;  solid windowtext; padding-left: 5.4pt; padding-right: 5.4pt; padding-top: 0cm; padding-bottom: 0cm">
		<p class="MsoNormal" align="center" style="text-align: center"><b>
		<span style="font-family: 宋体">序号</span></b>
		</td>
		<td width="92%" colspan="3" valign="center"
			width="552">
		<p class="MsoNormal" align="center" style="text-align: center"><b>
		<span style="font-family: 宋体">资料名称</span></b>
		</td>
	</tr>

<logic:iterate id="element" name="jmbazb" property="bajlzlqd" indexId="index">
	<tr style="page-break-inside: avoid">
		<td valign="top"			
			height="22">
		<p class="MsoNormal" align="center" style="text-align: center"><b>
		<span lang="EN-US"><%=(index.intValue()+1)%></span></b>
		</td>
		<td  colspan="3" valign="top"
			width="552"
			height="22">
		<p class="MsoNormal" align="center"><span style="font-family: 宋体">
		<bean:write name="element" property="zlqd"/></span>
		</td>
	</tr>

</logic:iterate>


	
	<tr style="page-break-inside: avoid; height: 18.65pt">
		<td width=605 colspan="4" valign="top">
		<p class="MsoNormal"><b><span style="font-family: 宋体">
		&nbsp;&nbsp;&nbsp;&nbsp;声明：以上提供的情况及报送的材料真实可靠，本纳税单位及法定代表人负责承担相应责任。</span></b>
		</td>
	</tr>
	<tr style="page-break-inside: avoid; height: 134.4pt">
		<td  colspan="2" valign="top" width="50%">
			<p class="MsoNormal">
				<b>
					<span style="font-family: 宋体">经办人：</span>
				</b>
			</p>
			<br/>
			<br/>
			<br/>
			<p class="MsoNormal">
				<b>
					<span style="font-family: 宋体">纳税人（签章）：</span>
				</b>
			</p>
			<p class="MsoNormal" align="center"	style="text-align: center; line-height: 30.0pt"></p>
			<p class="MsoNormal" align="center"	style="text-align: center; text-indent: 112.25pt">
				<b>
					<span lang="EN-US">	&nbsp; </span>
					<span lang="EN-US">	&nbsp; </span>
				</b>
			</p>			
		</td>
		<td valign="top" colspan="2" width="50%">
		<p class="MsoNormal"><b><span style="font-family: 宋体">经办人：</span></b></p>		
		<br/>
		<p class="MsoNormal"><b><span style="font-family: 宋体">所长：</span></b></p>
		<br/>
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
</table>

<table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0"
	style="border-collapse: collapse; border: medium none; margin-left: 22.25pt"
	width="597" id="table1">
	<tr>
		<td><div align="left"><span style="font-family: 宋体">
		&nbsp;&nbsp;&nbsp;&nbsp;注：本表及《减免税备案表》一式两份，一份由税务机关交纳税人作为收到备案材料的证明，一份由税务机关留存归档。 </span></div></td>
	</tr>
</table>
</br>
<span style="font-size: 10.0pt; font-family: 宋体">第2页 共2页</span>

</br>
<table width="100%" border="0" align="center">
	<tr align="center">

		<td width="20%">&nbsp;</td>

		<td>
		<div id="buttondiv1" name="buttondiv1" >
			<img src="<%=static_contextpath%>images/dayin1.jpg" onmouseover="this.src='<%=static_contextpath%>images/dayin2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/dayin1.jpg'" alt="打印" onclick="doPrint()" style="cursor:hand"></td>
			</div>
		<td>
		<div id="buttondiv2" name="buttondiv2" >
		<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onclick="doReturn()" style="cursor:hand">
		</div>
		</td>
		<td width="20%">&nbsp;</td>
	</tr>
</table>
</form>
<p class="MsoNormal" style="text-indent: 21.1pt; margin-left: 14.15pt"><b>
<span style="font-family: 宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></b>
</div>

</body>

</html>
