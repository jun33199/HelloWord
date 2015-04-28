<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.sfgl.common.db.util.SfResourceLocator"%>
<html>
<head>
<title>打印送达回证</title>
<link href="../../../css/text.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../../../js/date.js">
    	</script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/qysdsnew.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/function.js"></script>
</head>
  <script language="JavaScript">  
	function onKeyDown() { // 禁止刷新，回退
	 if ((event.keyCode == 8 && event.srcElement.type ==undefined) || (event.keyCode == 116)) {
	    event.keyCode = 0;
	    event.returnValue = false;
	   }
	}
	document.onkeydown = onKeyDown;
  
<%/*返回*/%>
function back(){
	document.forms[0].actionType.value = "Back";
	document.forms[0].submit();
}

function doPrint(){
	if(window.confirm("是否打印？")){        
		
		var temp = document.getElementById("printDiv");
		temp.style.display="none";		
		window.print(); 
		temp.style.display="block"; 
	 }    
}
</script>


<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" align="center">


<html:form action="/webapp/smsb/qysds/jmsbagl/QysdsJmsbajlPrintAction.do" method="post">
	<html:hidden property="actionType" />
	<html:hidden property="basqwsh" />
	<html:hidden property="basqbh" />
	<html:hidden property="jmbasxdm" />
	<html:hidden property="nsrmc" />
	<html:hidden property="czlx" />
	
	<table align="center" id="printArea">
	
	<tr>
		<td>
		<br>
		<br>
		<div align="center">
			<span style="font-size: 22.0pt; font-family: 仿宋_GB2312; letter-spacing: 1.0pt">
				<bean:write name="qysdsJmsbajlPrintForm" property="swjgzzjgmc" /><br>税务文书送达回证
			</span>
		</div>
	<br>
	<br>
	<div align="center">
	<table width="640" height="800" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse; border: medium none; margin-left: .15pt" id="table1">
	<tr>
		<td class="2-td2-t-printleft">
			<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
				送达文书名称
			</span>
		</td>
		<td class="2-td2-t-printcenter">
			<table>				
				<tr>
					<td>
						<div align="left">
							<span style="font-family: 宋体">
								<bean:write name="qysdsJmsbajlPrintForm" property="swjgzzjgmc" />减免税备案表<br>
								（<bean:write name="qysdsJmsbajlPrintForm" property="basqbh" />）<br>
								及减免税备案资料清单
							</span>
						</div>	
					</td>
				</tr>
			</table>			
		</td>
	</tr>
	<tr>
		<td class="2-td2-printleft" height="80">
			<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
				受送达人
			</span>
		</td>
		<td class="2-td2-printcenter">
			<span style="font-family: 宋体">
				<bean:write name="qysdsJmsbajlPrintForm" property="nsrmc" />
			</span>
		</td>
	</tr>
	<tr>
		<td class="2-td2-printleft" height="80">
			<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312"> 
				送达地点
			</span>
		</td>
		<td class="2-td2-printcenter">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="2-td2-printleft">
			<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
				受送达人签名或盖章
			</span>
		</td>
		<td class="2-td2-printcenter">
			<table height="80">
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
							年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;时&nbsp;&nbsp;分
						</span>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td class="2-td2-printleft">
			<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
				代收人代收理由、签名或盖章
			</span>
		</td>
		<td class="2-td2-printcenter">
			<table height="80">
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
							年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;时&nbsp;&nbsp;分
						</span>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td class="2-td2-printleft">
			<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
				受送达人拒收理由
			</span>
		</td>
		<td class="2-td2-printcenter">
			<table height="80">
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
							年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;时&nbsp;&nbsp;分
						</span>
					</td>
				</tr>
			</table>
		</td>
	<tr>
		<td class="2-td2-printleft">
			<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
				见证人签名或盖章
			</span>
		</td>
		<td class="2-td2-printcenter">
			<table height="80">
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
							年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;时&nbsp;&nbsp;分
						</span>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td class="2-td2-printleft">
			<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
				送达人签名或盖章
			</span>
		</td>
		<td class="2-td2-printcenter">
			<table height="80">
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
							年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;时&nbsp;&nbsp;分
						</span>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td class="2-td2-printleft">
			<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
				填发税务机关
			</span>
		</td>
		<td class="2-td2-printcenter">
			<table height="80">
				<tr>
					<td>
						<div align="right">
							<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
							（签章）
							</span>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
							年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;时&nbsp;&nbsp;分
						</span>
					</td>
				</tr>
			</table>			
		</td>
	</tr>
	</table>
	</div>
	</td>
	</tr>
	</table>
	</div>
	<br>
	
	<div id="printDiv" align="center">
	<table width="100%" border="0" align="center">
		<tr align="center">

			<td width="20%">&nbsp;</td>

			<td>
				<a style="cursor:hand" onClick="doPrint()" onMouseOut="MM_swapImgRestore()"
	            onMouseOver="MM_swapImage('dayin','','<%=SfResourceLocator.getContextPath(request)%>images//dayin2.jpg',1)">
	            <img src="<%=SfResourceLocator.getContextPath(request)%>images//dayin1.jpg" name="dayin" width="79" height="22" border="0" id="dayin">
	            </a>
	            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td>
				<a style="cursor:hand" onClick="back()" onMouseOut="MM_swapImgRestore()"
	            onMouseOver="MM_swapImage('fanhui','','<%=SfResourceLocator.getContextPath(request)%>images//fanhui2.jpg',1)">
	            <img src="<%=SfResourceLocator.getContextPath(request)%>	images//fanhui1.jpg" name="fanhui" width="79" height="22" border="0" id="fanhui">
	            </a>
	            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="20%">&nbsp;</td>
		</tr>
	</table>
	</div>
	
	
</html:form>
</body>
</html>
