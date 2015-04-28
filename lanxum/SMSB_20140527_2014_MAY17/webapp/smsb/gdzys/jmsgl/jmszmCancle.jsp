<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ttsoft.framework.util.JspUtil"%>
<%@ page import="com.ttsoft.common.util.CodeConstants"%>
<%@ page import="com.ttsoft.bjtax.smsb.lwpk.model.PKJBSJModel"%>
<%@ page import="com.ttsoft.bjtax.smsb.lwpk.model.PLKKDHCXModel"%>
<%@ page import="java.util.*"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.gdzys.jmsgl.web.GdzysJmsCancleForm"%>

<html:html>
<head>
<title>减免税证明查询</title>
<link href="../../css/text.css" rel="stylesheet" type="text/css">
<link href="../../css/top-box.css" rel="stylesheet" type="text/css">
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../../js/treatImage.js"></script>
<script language="JavaScript" src="../../js/smsb_common.js"></script>
<script language="JavaScript" src="../../js/DTable.js"></script>
<script language="JavaScript" src="../../js/InputSelect.js"></script>
<script language="JavaScript" src="../../js/function.js"></script>
<script language="javascript" src="../../js/gmit_selectcontrol.js"></script>
</head>
<%
	int datasize=((GdzysJmsCancleForm)request.getAttribute("gdzysJmsCancleForm")).getDataList().size();
	String selfswjgdm = ((GdzysJmsCancleForm) request
				.getAttribute("gdzysJmsCancleForm")).getSelfswjgdm();
		String canFlag = ((GdzysJmsCancleForm) (request
				.getAttribute("gdzysJmsCancleForm"))).getFlag();
		//System.out.println("自己的税务机关代码"+selfswjgdm);
%>
<SCRIPT LANGUAGE="JavaScript">
	function doit(ope) {
		doSubmitForm(ope);

	}
	
	
	//去掉字符串中所有空格
	function trim(str){
		var aa=str.replace(/\s/g,"");
		return aa;
	}
	
	//验证是否四项输入都是空
	function validate(){
			document.forms[0].submit();
		
	}
	function moren(){
		var size=<%=datasize%>;
	    if(size!=0){
	    	document.getElementById("mydiv").style.display="";
	    }
	}
	
	function chakan() {
		var chkObjs = document.getElementsByName("radioButton");
		var flag = false;
		for ( var i = 0; i < chkObjs.length; i++) {
			if (chkObjs[i].checked) {
				var bys = chkObjs[i].id.split(":");
				var sbbxlh = bys[0];
				var jmszmbh = bys[1];
				flag = true;
				var str = "/smsb/webapp/smsb/gdzys/gdzysJmscancleAction.do?sbbxlh="
						+ sbbxlh + "&&jmszmbh=" + jmszmbh + "&&actionType=View";
				window.open(str);

			}
		}
		if (!flag) {
			alert("没有选中");
		}

	}
	//撤销减免税
	function cancle() {
		var chkObjs = document.getElementsByName("radioButton");
		var flag = false;
		for ( var i = 0; i < chkObjs.length; i++) {
			if (chkObjs[i].checked) {
				flag = true;
				if (chkObjs[i].value == '000000') {
					var bys = chkObjs[i].id.split(":");
					var sbbxlh = bys[0];
					var jmszmbh = bys[1];
					var str = "/smsb/webapp/smsb/gdzys/gdzysJmscancleAction.do?sbbxlh="+ sbbxlh+ "&&jmszmbh="+ jmszmbh+ "&&actionType=Cancle";
					CheckCancle(str);
				} else {
					alert("减免税已记账，请先撤账再撤销减免税证明");
				}

			}
		}
		if (!flag) {
			alert("没有选中");
		}
	}

	//ajax
//以上为Javascript的脚本验证
    var xmlHttp;
	function createXMLHttpRequest()
	{
	
    	if(window.ActiveXObject)
    	{
    		//alert("IE");
        	xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    	}
   	 	else if(window.XMLHttpRequest)
    	{
    	
        	xmlHttp = new XMLHttpRequest();
    	}
	}
	function CheckCancle(str){
		createXMLHttpRequest();

		var url=str;
		xmlHttp.open("post",url,true);
	    xmlHttp.onreadystatechange=ShowResult;
		xmlHttp.send(null);
	
	}
	function ShowResult(){
		if(xmlHttp.readyState==4){
			if(xmlHttp.status==200){
				var rs=xmlHttp.responseText;
				//alert(rs);
				if(rs=="success"){
					document.forms[0].submit();
					alert("撤销减免税成功");
				}else if(rs=="faile"){
					alert("撤销减免税失败");
				}else if(rs=="have"){
					alert("减免税已经撤销，不能再次撤销");
				}
			}
		}
	}


</SCRIPT>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onload="moren();">
	<%@ include file="../../include/header.jsp"%>
	<html:form action="/webapp/smsb/gdzys/gdzysJmscancleAction.do"
		method="POST">
		<html:hidden property="actionType" value="Query" />
		<div id="ycy"></div>
		<table width="80%" align="center" cellspacing="0">
			<tr>
				<td class="1-td1" colspan="7">减免税证明查询</td>
			</tr>
			<tr>
				<td class="1-td2" colspan="7"><br>
					<table cellspacing="0" class="table-99" style="empty-cells:show; border-collapse:collapse;">
						<tr>
							<td  class="2-td2-t-left">纳税人名称</td>
							<td  class="2-td2-t-left">
								<div align="left">
									<html:text property="nsrmc" size="15" maxlength="50" />
									</FONT>
								</div>
							</td>
							<td  class="2-td2-t-left">计算机代码</td>
							<td  class="2-td2-t-left">
								<div align="left">
									<html:text property="jsjdm" size="15" maxlength="50"/>
								</div>
							</td>

							<td  class="2-td2-t-left">批准占地文号</td>
							<td  class="2-td2-t-left">
								<div align="left">
									<html:text property="pzzdwh" size="20" maxlength="50"/>
								</div>
							</td>
						</tr>
						<tr>
							<td  class="2-td2-t-left">申报序列号</td>
							<td  class="2-td2-t-left">
								<div align="left">
									<html:text property="sbbxlh" size="15" maxlength="50" />
									</FONT>
								</div>
							</td>
							<td  class="2-td2-t-left">状态</td>
							<td  class="2-td2-t-left">
								<div align="left">
								<%
									String lsta=((GdzysJmsCancleForm)request.getAttribute("gdzysJmsCancleForm")).getStatus();
								    lsta=lsta==null?"0":lsta;
									
								%>
									<html:select property="status" value="<%=lsta %>">
										<html:option value="全部">全部</html:option>
										<html:option value="0">正常</html:option>
										<html:option value="1">已撤销</html:option>
									</html:select>
								</div>
							</td>
							<td class="2-td2-t-left" colspan="2"></td>

						</tr>
						<table width="100%" border="0" cellpadding="0" cellspacing="4">
							 <tr valign="bottom">
							 <td width="50%" align="right" >
								<div>								 
									<input name="I2" type="image" accesskey="q" value="查询"
										onclick="validate();return false;"
										onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" alt="查询"
										src="<%=static_contextpath%>images/cx-q1.jpg" width="79" border="0"
										height="22" id="chaxun" >
								 </div>
							 </td>
							 <td width="50%" align="left" >
								<div>
									<input type="image" accesskey="c" tabIndex="-1"
										onClick="tuichu();return false;" 
										onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" alt="退出" id="tc1"
										src="<%=static_contextpath%>images/tc-c1.jpg" width="79"
										border="0" height="22">
								 </div>
							 </td> 
						
							 </tr>
							</table>
					</table></td>
			</tr>
		</table>
		<br>
		<logic:equal name="gdzysJmsCancleForm" property="cxJgTsFlag" value="1">
			  	 <DIV style="FONT-SIZE: 13px; HEIGHT: 15px" 
                 align=center><FONT SIZE="2" COLOR="#FF0000">没有满足查询条件的结果记录！</STRONG>
                 </DIV>	
				</logic:equal>			
		<div id="mydiv" style="display:none">
		<table width="80%" align="center" cellspacing="0">
			<tr>
				<td class="1-td1" colspan="7">减免税信息查询结果</td>
			</tr>
			<tr>
				<td class="1-td2" colspan="7"><br>
					<table cellspacing="0" class="table-99">
						<tr>
							<td class=2-td1-left width="4%"></td>
							<TD class=2-td1-left width="14%">减免税证明编号</TD>
							<TD class=2-td1-left width="14%">纳税人名称</TD>
							<TD class=2-td1-left width="14%">计算机代码</TD>
							<TD class=2-td1-left width="14%">计税面积</TD>
							<TD class=2-td1-left width="14%">减免面积</TD>
							<TD class=2-td1-left width="12%">减免税额</TD>
							<TD class=2-td1-center width="14%">开具时间</TD>
						</tr>
						<logic:iterate id="jmslist" name="gdzysJmsCancleForm"
							property="dataList" indexId="index">
							<tr>
								<td class=2-td2-t-left align="center" nowrap>&nbsp;<input
									type="radio" name="radioButton" onclick=""
									id='<ttsoft:write name="jmslist" property="sbbxlh"/>:<ttsoft:write name="jmslist" property="jmszmbh" />'
									value='<ttsoft:write name="jmslist" property="jzbz"/>' /></td>
								<td class="2-td2-t-left" align="center" nowrap>&nbsp;<ttsoft:write
										name="jmslist" property="jmszmbh" /></td>
								<td class="2-td2-t-left" align="center" nowrap>&nbsp;<ttsoft:write
										name="jmslist" property="nsrmc" /></td>
								<td class="2-td2-t-left" align="center" nowrap>&nbsp;<ttsoft:write
										name="jmslist" property="jsjdm" /></td>
								<td class="2-td2-t-left" align="center" nowrap>&nbsp;<ttsoft:write
										name="jmslist" property="jsmj" /></td>
								<td class="2-td2-t-left" align="center" nowrap>&nbsp;<ttsoft:write
										name="jmslist" property="jmmj" /></td>
								<td class="2-td2-t-left" align="center" nowrap>&nbsp;<ttsoft:write
										name="jmslist" property="jmse" /></td>
								<td class="2-td2-t-center" align="center" nowrap>&nbsp;<ttsoft:write
										name="jmslist" property="kjrq" /></td>
							</tr>
						</logic:iterate>
						<tr>
							<td></td>
							<td rowspan="2" width="10%" align="center" colspan="4">
							 <div>
									<input name="I2" type="image" accesskey="q" value="查看减免税证明"
										onclick="chakan();return false;"
										onMouseOver="MM_swapImage('ckjms','','<%=static_contextpath%>images/gdzys/gdzys_ckjmszm2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" alt="查看减免税证明"
										src="<%=static_contextpath%>images/gdzys/gdzys_ckjmszm1.jpg" width="95"
										height="22" id="ckjms" align="middle">
								</div>
								
							</td>
							<td rowspan="2" width="10%" align="left" colspan="4">
							<div>
									<input name="I2" type="image" accesskey="q" value="撤销减免税证明"
										onclick="cancle();return false;"
										onMouseOver="MM_swapImage('cxjms','','<%=static_contextpath%>images/gdzys/gdzys_cxjmszm2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" alt="撤销减免税证明"
										src="<%=static_contextpath%>images/gdzys/gdzys_cxjmszm1.jpg" width="95"
										height="22" id="cxjms" align="middle">
								</div>
								
							</td>
						</tr>
					</table></td>
			</tr>

		</table>
		</div>
		<br>
		<br>
		<table width="80%" align="center" cellspacing="0">
			<%
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						String now = df.format(new Date());
			%>
			<tr>
				<td class="1-td2" colspan="7"><br>
					<table cellspacing="0" class="table-99">
						<tr>
							<TD class=2-td1-left width="20%">操作人</TD>
							<TD class=2-td1-left width="30%"><ttsoft:write
									name="gdzysJmsCancleForm" property="localUser" /></TD>
							<TD class=2-td1-left width="20%">操作时间</TD>
							<TD class=2-td1-center width="30%"><%=now%></TD>
						</tr>
					</table></td>
			</tr>
			<tr>

			</tr>
		</table>


		<%@ include file="../../include/footer.jsp"%>
	</html:form>
</body>
</html:html>