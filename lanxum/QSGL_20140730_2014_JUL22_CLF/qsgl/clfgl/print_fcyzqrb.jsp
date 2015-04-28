<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<HTML>
<HEAD>
<TITLE>房产原值查询结果确认表</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js"
	type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js"
	type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>
<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0
	marginheight="0" marginwidth="0">

	<div class="Noprn">
		<br> 
	</div>
	<table border="0" align="center" cellspacing="0" class="table-99">
		<tr>
			<td colspan="15">
				<div align="center">
					<P ALIGN="CENTER"><font size="6">房屋原值查询结果</font></P>
				</div>
			</td>
		</tr>
	</table>

	<html:form action="/clfgl/mfskzs.do" type="POST">
		<TABLE align=center border=0 cellPadding=0 cellSpacing=0 width=100%>
			<tr>
				<td vAlign=top>
					<TABLE align=center border=0 cellSpacing=0 height="100%" width=90%>
						<tr>
							<td vAlign=top>
								<TABLE align=center cellSpacing=0 class=table-99>
									<tr>
										<td>
											<table border=2 cellSpacing=1 width="100%">
												<tr>
													<td VALIGN="MIDDLE" width="45%"><div ALIGN="CENTER"><font size="5">纳税人姓名(证件名称:证件号码)</font></div></td>
													<td VALIGN="MIDDLE" width="55%"><div align="CENTER"><font size="5"><bean:write name="mfskzsForm" property="nsrmc_his" filter="false"/></font></div></td>
												</tr>
												<tr>
													<td VALIGN="MIDDLE"><div ALIGN="CENTER"><font size="5">房屋所有权证号</font></div></td>
													<td VALIGN="MIDDLE"><div align="CENTER"><font size="5"><bean:write name="mfskzsForm" property="fwsyqzh_his" filter="false"/></font></div></td>
												</tr>
												<tr>
													<td VALIGN="MIDDLE"><div ALIGN="CENTER"><font size="5">房屋地址</font></div></td>
													<td VALIGN="MIDDLE"><div align="CENTER"><font size="5"><bean:write name="mfskzsForm" property="tdfwzldz_his" /></font></div></td>
												</tr>
												<tr>
													<td VALIGN="MIDDLE"><div ALIGN="CENTER"><font size="5">上次交易契税计税价格</font></div></td>
													<td VALIGN="MIDDLE"><div align="CENTER"><font size="5"><bean:write name="mfskzsForm" property="jsje_his" /></font></div></td>
												</tr>
												<tr>
													<td VALIGN="MIDDLE"><div ALIGN="CENTER"><font size="5">申报日期</font></div></td>
													<td VALIGN="MIDDLE"><div align="CENTER"><font size="5"><bean:write name="mfskzsForm" property="sbrq_his" /></font></div></td>
												</tr>
												<tr>
													<td VALIGN="MIDDLE"><div ALIGN="CENTER"><font size="5">应纳税额</font></div></td>
													<td VALIGN="MIDDLE"><div align="CENTER"><font size="5"><bean:write name="mfskzsForm" property="ynse_his" /></font></div></td>
												</tr>																																		
											</table>
										</td>
									</tr>
									<tr>
										<td>
										<P>										
		           <table align=center border=0 cellPadding=0 cellSpacing=0 width=100%>
		           	<tr>
		           		<td VALIGN="RIGHT" width="10%">&nbsp;</td>
		           	</tr>
		           	<tr>
		           		<td VALIGN="RIGHT" width="10%">&nbsp;</td>
		           	</tr>		           			           			           	
		           	<tr>
		           		<td VALIGN="RIGHT" width="10%"><div ALIGN="RIGHT"><font size="5"><bean:write name="mfskzsForm" property="zsjg" />&nbsp;<bean:write name="mfskzsForm" property="tfrq" /></font></div></td>
		           	</tr>
		           	<tr>
		           		<td VALIGN="RIGHT" width="10%">&nbsp;</td>
		           	</tr>
		           	<tr>
		           		<td VALIGN="RIGHT" width="10%">&nbsp;</td>
		           	</tr>	
		           	<tr>
		           		<td VALIGN="RIGHT" width="10%">&nbsp;</td>
		           	</tr>
		           	<tr>
		           		<td VALIGN="RIGHT" width="10%">&nbsp;</td>
		           	</tr>
		           	<tr>
		           		<td VALIGN="RIGHT" width="10%">&nbsp;</td>
		           	</tr>	
		           	<tr>
		           		<td VALIGN="RIGHT" width="10%">&nbsp;</td>
		           	</tr>		           			           	
		           	<tr>
		           		<td VALIGN="LEFT" width="10%"><div ALIGN="LEFT"><font size="5">纳税人声明：本人认可查询结果。</font></div></td>
		           	</tr>
		           	<tr>
		           		<td VALIGN="RIGHT" width="10%">&nbsp;</td>
		           	</tr>
		           	<tr>
		           		<td VALIGN="RIGHT" width="10%">&nbsp;</td>
		           	</tr>	           		
		           	<tr>
		           		<td VALIGN="RIGHT" width="10%"><div ALIGN="RIGHT"><font size="5">纳税人签名:_______________</font></div></td>
		           	</tr>
	             </table>
										</td>
									</tr>										
								</TABLE></td>
						</tr>
					</TABLE></td>
			</tr>
		</TABLE>
	</html:form>
	<div class="Noprn">
		<br>
	</div>
	<div id="dayin" align="center" class="Noprn">
		<img alt="执行打印操作" style="cursor: hand" onclick="printTab()"
			src="<%=static_file%>images/dayin1.jpg" width="79" height="22"
			id="dayin1" />&nbsp;&nbsp; <img alt="退出打印页面" style="cursor: hand"
			onclick="window.close()" src="<%=static_file%>images/guanbi1.jpg"
			width="79" height="22" id="guanbi" />&nbsp;&nbsp;
	</div>
</BODY>


<script type="text/javascript">
		var HKEY_Root,HKEY_Path,HKEY_Key;                                 
		HKEY_Root="HKEY_CURRENT_USER";                                    
		HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";	
		//设置网页打印的页眉页脚为空  
		function PageSetup_Null()  
		{  
		   try  
		   {  
		        var Wsh=new ActiveXObject("WScript.Shell");  
		        HKEY_Key="header";  
		        Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");  
		        HKEY_Key="footer";  
		        Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");  
		    }  
		    catch(e)  
		    {}  
		  
		}	
		function printTab() 
		{
			document.all.dayin.style.display = "none";
			PageSetup_Null();
			window.print();
			document.all.dayin.style.display = "";
		}
</script>
</HTML>
