<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.web.QyqssdsBaForm" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.db.util.SfResourceLocator"%>
<HTML>
<HEAD>
<TITLE>��ҵ��������˰�������ִ</TITLE>

<style>
@media Print {
	.Noprn {
		DISPLAY: none
	}
}
</style>


<style type="text/css">

</style>
<object ID='wb' WIDTH=0 HEIGHT=0
	CLASSID='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2'></object>
<object id='factory' style="display:none"   classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814" viewastext codebase="ScriptX.cab#Version=5,0,4,185">
     </object>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>

</HEAD>
	<%
		String static_contextpath = SfResourceLocator.getContextPath(request);
		com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.web.QyqssdsBaForm form = (com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.web.QyqssdsBaForm) request
				.getAttribute("qyqssdsBaForm2014");
		String nsrmc =form.getNsrmc();
	%>
<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0
	marginheight="0" marginwidth="0">
	<!--startprint-->
	<table width="80%" border="0" align="center" cellspacing="0"
		class="table-99">
		
		<tr>
			<td colspan="15">
				<div align="center">
				<br><br>
				<h2>
					��ҵ��������˰�������ִ
				</h2>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div align="left">
				<br><br>
				<font size="4">
					<U>
					<%=nsrmc%>:
					</U>
				</font>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div align="left">
				<font size="4">
				&nbsp;&nbsp;&nbsp;&nbsp;�㵥λ����ҵ��������˰���������������������Ϥ�����㵥λ�����������ڼ���Ϊһ����˰��ȣ����������������ü���Ӧ������˰�������������֮����15���ڣ�������˰����ر���
				���л����񹲺͹���ҵ��������˰��˰�걨�������丽���͸������ϣ�����˰�
				</font>
				</div>
			</td>
		</tr>

		<tr>
			<td>
			<div align="right">
			<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
			<font size="4">
				˰����أ�ǩ�£�<br><br>
				�� &nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;<br><br><br>
			</font>	
			</div>
			</td>		
		</tr>
		<tr>
			<td>
			<div align="center">
				<font size="3">
				������ִһʽ���ݣ�һ�ݷ�����˰�ˣ�һ��������˰��������档��
				</font>
			</div>
			</td>
		</tr>
	</table>
<!--endprint-->
	<div class="Noprn">
		<br>
	</div>
	<div id="dayin" align="center" class="Noprn"><br><br>
		<img alt="ִ�д�ӡ����" style="cursor: hand" onclick="printHz()"
			src="<%=static_contextpath%>images/dayin1.jpg" width="79" height="22"
			id="dayin1" />&nbsp;&nbsp; <img alt="�˳���ӡҳ��" style="cursor: hand"
			onclick="window.close()" src="<%=static_contextpath%>images/guanbi1.jpg"
			width="79" height="22" id="guanbi" />&nbsp;&nbsp;
	</div>
</BODY>
<script type="text/javascript">
	var HKEY_Root, HKEY_Path, HKEY_Key;
	HKEY_Root = "HKEY_CURRENT_USER";
	HKEY_Path = "\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
	//������ҳ��ӡ��ҳüҳ��Ϊ��    
	function PageSetup_Null() {
		try {
			var Wsh = new ActiveXObject("WScript.Shell");
			HKEY_Key = "header";
			Wsh.RegWrite(HKEY_Root + HKEY_Path + HKEY_Key, "");
			HKEY_Key = "footer";
			Wsh.RegWrite(HKEY_Root + HKEY_Path + HKEY_Key, "");
		} catch (e) {
		}
	}
	function printTab() {
		//document.all.dayin.style.display = "none";
 		//factory.printing.header = "" ;  
  
  		//factory.printing.footer = "";  
  
 		//factory.printing.portrait = false;  
  
  		//factory.printing.leftMargin =10.00;  
 
  		//factory.printing.topMargin =10.00;  
 
	  	//factory.printing.rightMargin =10.00;  
 		//factory.printing.bottomMargin =10.00; 
		
		//window.print();
	 PageSetup_Null();
     window.print();
		//document.all.dayin.style.display = "";
	}
	function printHz(){
		printTab();
//		if(confirm("��ȷ��Ҫ��ӡ�ڶ��ݻ�ִ��")){
//			printTab();
//		}
	}

</script>
</HTML>