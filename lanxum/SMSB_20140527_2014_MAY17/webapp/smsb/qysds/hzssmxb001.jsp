<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.hzssmxb.web.HzssmxbForm"%>
<%@ page import="java.util.HashMap"%>

<html>
<head>
<title>坏帐损失明细表</title>
<link href="../../css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript"
	src="../../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/smsb_common.js"></script>
<script language="JavaScript" src="../../js/DTable.js"></script>
<script language=JavaScript src="../../js/reader.js"></script>
<script language=JavaScript src="../../js/compute.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/qysdsnew.js"></script>
</head>

<script language="JavaScript">
	
	<%/*初始化*/%>
	function doInit()
	{
	//初始化文本框onChange事件处理
		initNumText("kjje",6);
		initNumText("ssje",6);
		initNumText("nstze",6);
		
	<%
	HzssmxbForm hzssmxbForm=(HzssmxbForm)request.getAttribute("hzssmxbForm");
	if (hzssmxbForm!=null && hzssmxbForm.getDataList().size()>0){
		for(int i=0;i<hzssmxbForm.getDataList().size();i++){
			HashMap map=(HashMap)hzssmxbForm.getDataList().get(i);
			int hc=Integer.parseInt((String)map.get("hc"));
			String kjje=(String)map.get("kjje");
			String ssje=(String)map.get("ssje");
			String nstze=(String)map.get("nstze");
			%>
			obj=eval("document.getElementById('<%=hc%>_1')");
			obj.value='<%=kjje%>';
			
			obj=eval("document.getElementById('<%=hc%>_2')");
			obj.value='<%=ssje%>';
			
			obj=eval("document.getElementById('<%=hc%>_3')");
			obj.value='<%=nstze%>';
			<% 
		}
	}
	%>
	}
	
	<%/*保存*/%>
	function doSave()
	{
		doSubmitForm('Save');
	}
	<%/*校验*/%>
	function doCheck()
	{
		doSubmitForm('Check');
	}
	
	<%/*清除*/%>
	function doReset()
	{
	    if(confirm("确认是否清除当前数据？"))
	    {
		   	for(var i=1; i<=6; i++){
				obj = eval("document.getElementById('"+i+"_1')");
				     if (obj.readOnly!=true)
					 obj.value = "";
				obj = eval("document.getElementById('"+i+"_2')");
				     if (obj.readOnly!=true)
					 obj.value = "";
				obj = eval("document.getElementById('"+i+"_3')");
				     if (obj.readOnly!=true)
					 obj.value = "";
		   	}
	    }
	}
	
	<%/*删除*/%>
	function doDelete()
	{
		doSubmitForm('Delete');
	}
	<%/*返回*/%>
		function doExit()
		{
			doSubmitForm('Exit');
		}
		

</script>


<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onLoad="doInit()">
<%@ include file="../include/header.jsp"%>
<br>

<html:form action="/webapp/smsb/qysds/hzssmxbAction">
	<html:hidden property="actionType" />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />
	
	<table width="96%" align="center" cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">坏帐损失明细表</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;申报日期:<bean:write
				name="hzssmxbForm" property="sbrq" scope="request" filter="true" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额单位：元（列至角分）
			</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;计算机代码:<bean:write
				name="hzssmxbForm" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;纳税人名称：<bean:write
				name="hzssmxbForm" property="nsrmc" scope="request" filter="true" />&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">
			<TABLE class="table-99" align="center">
				<TR>
					<TD>
					<div id="Layer2" style="position:static; overflow: auto;">
					<table id="wrklistTable" border="1" cellspacing="0"
						class="table-99" align="center">
						<tr>
							<td rowspan="2" class="2-td1-left">行&nbsp;&nbsp;次</td>
							<td rowspan="2" class="2-td1-left">项目</td>
							<td class="2-td1-left">会计金额</td>
							<td class="2-td1-left">税收金额</td>
							<td class="2-td1-center">纳税调整额</td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">1</td>
							<td class="2-td2-left" align="center">2</td>
							<td class="2-td2-center" align="center">3</td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">1<input type='hidden'
								name='hc' id='hc_1' value='1'></td>
							<td class="2-td2-left-qysds1" >期初坏帐准备金额</td>
							<td class="2-td2-left" ><input type='text'
								name='kjje' id='1_1' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='ssje' id='1_2' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center" ><input type='text'
								name='nstze' id='1_3' value='*' tabindex='-1' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
						</tr>
						<tr>
							<td class="2-td2-left" >2<input type='hidden'
								name='hc' id='hc_2' value='2'></td>
							<td class="2-td2-left-qysds1" >本期核销的坏帐损失</td>
							<td class="2-td2-left" ><input type='text'
								name='kjje' id='2_1' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='ssje' id='2_2' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center" ><input type='text'
								name='nstze' id='2_3' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" >3<input type='hidden'
								name='hc' id='hc_3' value='3'></td>
							<td class="2-td2-left-qysds1" >本期收回已核销的坏帐损失</td>
							<td class="2-td2-left" ><input type='text'
								name='kjje' id='3_1' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='ssje' id='3_2' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center" ><input type='text'
								name='nstze' id='3_3' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" >4<input type='hidden'
								name='hc' id='hc_4' value='4'></td>
							<td class="2-td2-left-qysds1" >计提坏帐准备的应收帐款余额</td>
							<td class="2-td2-left" ><input type='text'
								name='kjje' id='4_1' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='ssje' id='4_2' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center" ><input type='text'
								name='nstze' id='4_3' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" >5<input type='hidden'
								name='hc' id='hc_5' value='5'></td>
							<td class="2-td2-left-qysds1" >本期增(减)提的坏帐准备</td>
							<td class="2-td2-left" ><input type='text'
								name='kjje' id='5_1' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='ssje' id='5_2' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center" ><input type='text'
								name='nstze' id='5_3' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" >6<input type='hidden'
								name='hc' id='hc_6' value='6'></td>
							<td class="2-td2-left-qysds1" >期末坏帐准备余额</td>
							<td class="2-td2-left" ><input type='text'
								name='kjje' id='6_1' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='ssje' id='6_2' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center" ><input type='text'
								name='nstze' id='6_3' value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
						</tr>
					</table>
					</div>
					</TD>
				</TR>
				<TR class="black9">
					<TD>
					<div align="center">
					<a style="cursor:hand" id="previous"
						onclick="gotoPreviousTable()"><img name="spage" value="上一张表"
						alt="填写上一张表"
						onMouseOver="MM_swapImage('previoustable','','<%=static_contextpath%>images/shangyiye2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/shangyiye1.jpg" id="previoustable">
					</a>&nbsp;&nbsp;
					<logic:equal name="hzssmxbForm" property="isLastTable" value="no">
						 <a style="cursor:hand" id="next"
							onclick="gotoNextTable()"><img name="xpage" value="下一张表"
							alt="填写下一张表"
							onMouseOver="MM_swapImage('nexttable','','<%=static_contextpath%>images/xaiyiye2.jpg',1)"
							onMouseOut="MM_swapImgRestore()"
							src="<%=static_contextpath%>images/xaiyiye1.jpg" id="nexttable"> 
						</a>&nbsp;&nbsp;
					</logic:equal>
					
					<input type="image" style="cursor:hand"
						tabIndex="-1" onClick="doReset();return false;" accesskey="u"
						value="清除" alt="清除页面输入框信息"
						onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qc-u1.jpg" 
						id="qingchu" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1" onClick="doSave();return false;"
						accesskey="s" value="保存" alt="保存"
						onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/bc-s1.jpg" 
						id="baocun" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="doCheck();return false;" accesskey="d"
						value="单表校验" alt="表内关系校验"
						onMouseOver="MM_swapImage('jiaoyan','','../../images/b-bdjyd2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" src="../../images/b-bdjyd1.jpg"
						 id="jiaoyan" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="doDelete();return false;" accesskey="x"
						value="全部删除" alt="删除本表所有数据，且不可恢复"
						onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qbsc-x1.jpg" 
						id="shanchu" /> &nbsp;&nbsp; <input type="image" value="返回" alt="返回到企业所得税年报主页面"
						style="cursor:hand" tabIndex="-1" onClick="doExit();return false;"
						onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/fanhui1.jpg" 
						id="fanhui" /></div>
					</TD>
				</TR>
			</TABLE>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>
	<%@ include file="../include/footernew.jsp"%>
</html:form>

</body>
</html>
