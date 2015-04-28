<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html; charset=gb2312" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft"%>
<%@ page import="com.ttsoft.bjtax.smsb.gdzys.jmsgl.web.GdzysJmsCancleForm"%>
<%@ page import="java.util.*" %>
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator
			.getStaticFilePath(request);
%>

<html>
<head>
<title>减免税证明打印</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
</head>
<%
	String jmszmbh = ((GdzysJmsCancleForm) request
			.getAttribute("gdzysJmsCancleForm")).getJmszmbh();
	System.out.println(jmszmbh + "##########测试打印");
	String sbxlh = ((GdzysJmsCancleForm) request
			.getAttribute("gdzysJmsCancleForm")).getSbbxlh();
%>

<body>
	<html:form action="/webapp/smsb/gdzys/gdzysJmszmPrintAction.do"
		method="POST">
		<table width="1000" align="center">

			<logic:iterate id="jmszmlist" name="gdzysJmsCancleForm"
				property="jmszmList">
				<bean:define id="xxx" name="jmszmlist"/>
				<tr>
					<div align="center">
						<font size="5"><ttsoft:write name="jmszmlist"
								property="swjgmc" /></font>
					</div>
				</tr>
				<tr align="center">
					<div align="center">
						<font size="6">耕地占用税减免税证明</font>
					</div>
				</tr>
				<tr align="center">
					<div align="center">
						<font size="5"><ttsoft:write name="jmszmlist"
								property="jmszmbh" />号</font>
					</div>
					<br>
				</tr>
				<tr align="center">
					<div align="left">
						<ttsoft:write name="jmszmlist" property="nsrmc" />
						（税务计算机代码：
						<ttsoft:write name="jmszmlist" property="jsjdm" />
						）
					</div>
					<br> &nbsp;&nbsp;&nbsp;&nbsp;
					<%
						List list=(ArrayList)(((HashMap)xxx).get("jmyjlist"));
					StringBuffer sb = new StringBuffer("");
					for(int i=0;i<list.size();i++){
						String text=(String)list.get(i);
						sb.append(text);
						if((i+1)<list.size()){
							sb.append(",");
						}
					}
						%>
					根据	<%=sb.toString() %>的规定，经审核，同意免征你单位耕地占用税
					<ttsoft:write name="jmszmlist" property="jmse" />
					元， 免税面积为
					<ttsoft:write name="jmszmlist" property="jmmj" />
					平方米。（计征税额为
					<ttsoft:write name="jmszmlist" property="jzse" />
					元，计征面积为
					<ttsoft:write name="jmszmlist" property="jsmj" />
					平方米。
					<ttsoft:write name="jmszmlist" property="pzjdwh" />
					）
				</tr>
				<br>
				<br>
				<br>
				<tr align="right">
					<div align="right">
						税务机关（签章）<br>
						<ttsoft:write name="jmszmlist" property="year" />
						年
						<ttsoft:write name="jmszmlist" property="month" />
						月
						<ttsoft:write name="jmszmlist" property="day" />
						日
					</div>
				</tr>
				<br>
				<br>
				<br>
		</table>
		</div>
		</tr>
		</logic:iterate>
		</table>
		<br>
		<div id="dayin" align="center">
              <img style="cursor: hand"
				onclick="window.close()"
				src="<%=static_contextpath%>/images/guanbi1.jpg" width="79"
				height="22" id="guanbi" />
		</div>

	</html:form>

</html>
