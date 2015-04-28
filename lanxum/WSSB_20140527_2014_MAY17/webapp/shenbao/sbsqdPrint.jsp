<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.shenbao.print.web.JksqdPrintForm"%>

<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
	JksqdPrintForm jf = (JksqdPrintForm)request.getAttribute("jksqdPrintForm");
	String rqstr = jf.getSbrq();
%>

<html>
<head>
<title>电子缴库专用缴款书</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<applet name="printer" codebase="<%=static_contextpath%>printer" code="com.ttsoft.bjtax.webprint.DZJKZYJKSPrinter" width="0" height="0" archive="vprinter.jar">
</applet>

<html:form action="/jksqdPrintAction" method="POST">
<html:hidden property="actionType"/>
<html:hidden property="h_jsjdm"/>
<html:hidden property="h_sbbh"/>
<html:errors />
<br>
	<table width="96%" align="center" cellspacing="0" class="table-99" id="pauls">
		<tr>
			<td class="z-ta1-td2" valign="top"> <br>
				<table width="98%" cellpadding="5" cellspacing="5" class="table-99" >
					<tr class="black9">
						<td align="center">
						    <div align="center"><font size="4pt"><b>电子缴库专用缴款书</b></font></div><br>
							<br><div align="center">
								<%
								out.println(rqstr.substring(0,4) + " 年 " + rqstr.substring(4,6) + " 月 " + rqstr.substring(6) + " 日");
								%>
								</div><br>
						</td>
					</tr>
			    </table>
			    
				<table width="98%" cellpadding="5" cellspacing="5" class="table-99" >
					<tr class="black9">
						<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;申报序号：<u><bean:write name='jksqdPrintForm' property='sbbh'/></u>
						<td align="right">金额单位：人民币元&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
			    </table>
				<table class="table-99" cellspacing="0" width="98%" border="1">
				<tr>
					<td    class="2-td2-t-left">
							<div align="center"><strong>纳税人计算机代码</strong>&#160;&#160;</div>
						</td>
						<td  colspan="3" class="2-td2-t-center">
								<div align="center"><bean:write name='jksqdPrintForm' property='jsjdm'/>&nbsp;</div>
						</td>
				</tr>
					<tr>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>纳税人名称</strong>&nbsp;</div>
						</td>
						<td width="30%" class="2-td2-left">
							<div align="center"><bean:write name='jksqdPrintForm' property='nsrmc'/>&nbsp;</div>
						</td>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>征收机关代码</strong>&nbsp;&nbsp;</div>
						</td>
						<td width="30%" class="2-td2-center">
							<div align="center">21100000000</div>
						</td>
					</tr>
					<tr>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>付款人名称</strong>&nbsp;&nbsp;</div>
						</td>
						<td width="30%" class="2-td2-left">
							<div align="center"><input type="text" name="nsrmc" value="<bean:write name='jksqdPrintForm' property='nsrmc'/>"/>&nbsp;</div>
						</td>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>征收机关名称</strong>&nbsp;&nbsp;</div>
						</td>
						<td width="30%" class="2-td2-center">
							<div align="center"><bean:write name='jksqdPrintForm' property='swjgzzjgmc'/>&nbsp;</div>
						</td>
					</tr>
					<tr>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>付款人开户银行名称</strong>&nbsp;&nbsp;</div>
						</td>
						<td width="30%" class="2-td2-left">
							<div align="center"><input type="text" name="yhmc" value="<bean:write name='jksqdPrintForm' property='yhmc'/>"/>&nbsp;</div>
						</td>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>收款国库名称</strong>&nbsp;&nbsp;</div>
						</td>
						<td width="30%" class="2-td2-center">
							<div align="center"><bean:write name='jksqdPrintForm' property='gkzzjgmc'/>&nbsp;</div>
						</td>
					</tr>
					<tr>
						<td class="2-td2-left">
							<div align="center"><strong>付款人帐号</strong>&nbsp;&nbsp;</div>
						</td>
						<td class="2-td2-left">
							<div align="center"><input type="text" name="zh" value="<bean:write name='jksqdPrintForm' property='zh'/>"/>&nbsp;</div>
						</td>
						<td class="2-td2-left">
							<div align="center"><strong>国库清算行行号</strong>&nbsp;&nbsp;</div>
						</td>
						<td class="2-td2-center">
							<div align="center">011100000003</div>
						</td>
					</tr>
				</table>
				<br>
				<table class="table-99" cellspacing="0" width="98%" border="1">
					<tr>
							  <td class="2-td1-left"><div align="center">纳税项目代码</div></td>
							  <td class="2-td1-left"><div align="center">课税数量</div></td>
							  <td class="2-td1-left"><div align="center">计税金额</div></td>
							  <td class="2-td1-center"><div align="center">实际缴税额</div></td>
					</tr>
				    <bean:define id="itemjksprint" name="jksqdPrintForm" property="szitem"/>
				    <logic:iterate id="itemprint" name="itemjksprint" indexId="index">
				        <tr>
				          <td class="2-td2-left"><%=(((Map)itemprint).get("szdm") + " " + ((Map)itemprint).get("szmc"))%>&nbsp;</td>
				          <td class="2-td2-left">&nbsp;</td>
				          <td class="2-td2-left">&nbsp;</td>
				          <td class="2-td2-center"><%=((Map)itemprint).get("sjje")%>&nbsp;</td>
				        </tr>
				    </logic:iterate>
					
					<tr>
						<td colspan="4" class="2-td2-center">
	                           <table width="100%">
	                               <tr>
	                                   <td class="inputnoborder" width="70%">
	                                       <strong>&nbsp;&nbsp;金额合计(大写):</strong><bean:write name='jksqdPrintForm' property='hjjedx'/>&nbsp;
	                                   </td>
	                                   <td class="inputnoborder" width="30%" >
	                                       <strong>金额合计(小写):￥</strong><bean:write name='jksqdPrintForm' property='hjjexx'/>&nbsp;
	                                   </td>
	                               </tr>
	                           </table>
						</td>
					</tr>
					<tr>
						<td class="2-td2-left">
							<div align="center"><strong> <br>付款人 盖章 <br>   经办人（章） <br></strong>&nbsp;&nbsp;</div>
						</td>
						<td class="2-td2-left">
							<div align="center"><strong> <br>税务机关 <br>（章） <br></strong>&nbsp;&nbsp;</div>
						</td>
						<td class="2-td2-left">
							<div align="center"><strong> <br>银行记账员  <br>盖章 <br></strong>&nbsp;&nbsp;</div>
						</td>
						<td class="2-td2-center">
							<div align="left"><strong>&nbsp;&nbsp;备注： <br> <br></strong>&nbsp;&nbsp;</div>
						</td>
					</tr>
				</table>
				<br>
				<br>
				<table class="table-99" cellspacing="0" width="98%" >
					<tr class="black9">
						<td>
							<img style="cursor:hand" onClick="printTab()" src="<%=static_contextpath%>/images/dayin1.jpg" width="79" height="22" id="dayin1" alt="打印"/>&nbsp;&nbsp;
						</td>
						<td>
							<img style="cursor:hand" onClick="window.close()" src="<%=static_contextpath%>/images/guanbi1.jpg" width="79" height="22" id="guanbi" alt="关闭" />
						</td>
					</tr>
				</table>
				<table class="table-99" cellspacing="0" width="98%" >
					<tr class="black9">
						<td align="right">&nbsp;
							
					  </td>
					</tr>
					<tr class="black9">
						<td align="right">&nbsp;
							
					  </td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<br>
</html:form>

<SCRIPT LANGUAGE="JavaScript" type="text/JavaScript">
<!--
    function printTab(){
	var rqn="<%=rqstr.substring(0,4)%>";
	var rqy="<%=rqstr.substring(4,6)%>";
	var rqr="<%=rqstr.substring(6)%>";
	var ysb="√";
	var sbxh="<%=jf.getSbbh()%>";
	var wsb="";
    var fkrjsjdm = "<%=jf.getJsjdm()%>";//付款人计算机代码
    var zsjgdm = ""; //征收机关代码
    
	var nsrmc = "<%=jf.getNsrmc()%>";//纳税人名称
    var zsjgmc = "<%=jf.getSwjgzzjgmc()%>";//征收机关名称
	
	var fkrmc = document.forms[0].nsrmc.value;//付款人名称
	var fkrkhyhmc = document.forms[0].yhmc.value;//付款人开户银行名称
	var fkrzh = document.forms[0].zh.value;//付款人账号
	
    //var fkrkhyhmc = "<%=jf.getYhmc()==null?"":jf.getYhmc()%>";//付款人开户银行名称
	//var fkrmc = "<%=jf.getNsrmc()%>";//付款人名称
	//var fkrzh = "<%=jf.getZh() == null?"":jf.getZh()%>";//付款人账号
	
    var skgkmc = "<%=jf.getGkzzjgmc()%>";//收款国库名称
    
    var gkqshhh = ""; //国库清算行行号
    <%
        List ar = jf.getSzitem();
    	String szstr = "",jestr = "";
    	Map sz;
    	for (int i = 0;i < ar.size();i++)
    	{
    	    sz = (Map)ar.get(i);
    	    szstr += sz.get("szdm");
    	    szstr += " ";
    	    szstr += sz.get("szmc");
    	    szstr += ";;";
    	    
    	    jestr += sz.get("sjje");
    	    jestr += ";;";
    	}
    	if (szstr.length() > 0)
    	{
    	    szstr = szstr.substring(0,szstr.length() - 2);
    	    jestr = jestr.substring(0,jestr.length() - 2);
    	}
    
    %>
    var nsxmdm ="<%=szstr%>";//纳税项目代码 
    var kssl = "";//课税数量
    var jsje = "";//计税金额
    var sjse = "<%=jestr%>"; //实缴金额
    var jehjdx = "<%=jf.getHjjedx()%>";  //金额合计（大写）
    var jehjxx = "￥<%=jf.getHjjexx()%>";  //金额合计（小写）
    var bz = ""; //备注
     document.printer.setRQN(rqn);
     document.printer.setRQY(rqy);
     document.printer.setRQR(rqr);
     document.printer.setYSB(ysb);
     document.printer.setSBXH(sbxh);
     document.printer.setWSB(wsb);
    document.printer.setFKRJSJDM(fkrjsjdm);
    document.printer.setZSJGDM(zsjgdm);
	document.printer.setNSRMC(nsrmc);
    document.printer.setFKRMC(fkrmc);
    document.printer.setZSJGMC(zsjgmc);
    document.printer.setFKRKHYHMC(fkrkhyhmc);
    document.printer.setSKGKMC(skgkmc);
    document.printer.setFKRZH(fkrzh);
    document.printer.setGKQSHHH(gkqshhh);
    document.printer.setNSXMDM(nsxmdm);
    document.printer.setKSSL(kssl);
    document.printer.setJSJE(jsje);
    document.printer.setSJSE(sjse);
    document.printer.setJEHJDX(jehjdx);
    document.printer.setJEHJXX(jehjxx);
    document.printer.setBZ(bz);
    document.printer.startPrint();
    }
//-->
</SCRIPT>
</body>
</html>
