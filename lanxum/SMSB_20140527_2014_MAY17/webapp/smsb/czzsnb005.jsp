<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.code.CodeManager" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.constant.CodeConstants" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.util.SfHashList"%>

<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>�������ո��˶�����ҵ�ͺϻ���ҵͶ���߸�������˰����걨��</title>

<script language="JavaScript" src="../js/list.js"></script>
<script language="JavaScript" src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/compute.js"></script>
<script language="JavaScript" src="../js/function.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/Bolan.js"></script>
<script language="JavaScript" src="../js/MathString.js"></script>
<script language="JavaScript" src="../js/Stack.js"></script>
<script language="JavaScript" src="../js/reader.js"></script>

<script language="JavaScript">
//��Ӧ���������Ļس���ѯ
function jsjdmQuery(){
 	if(event.keyCode==13) event.keyCode = 9;
}

function doQuery()
{
	if(trim(document.forms[0].jsjdm.value) == ""){
		alert("��������˰�˼������");
		return false;
	}

	document.forms[0].nsrmc.value = "";
	doSubmitForm("Query");
	return false;
}


function doSave()
{
	if(!check_jsjdm())
		return false;

	doSubmitForm("Save");
	return false;
}

function doDelete()
{
	if(!check_jsjdm())
		return false;

	doSubmitForm("Delete");
    return false;
}

function check_jsjdm(){

	if(trim(document.forms[0].jsjdm.value) == "" || document.forms[0].nsrmc.value == ""){
		alert("��������˰�˵ļ������");
		return false;
	}
	return true;
}

</script>


<script language="JavaScript">
var szsmArray = new Array();
function szsm(sl, ynszzs,ynsqss,sskcs){
	this.sl = sl;
	this.ynszzs = ynszzs;
	this.ynsqss=ynsqss;
	this.sskcs=sskcs;
}

<%
SfHashList szsmlist=CodeManager.getCodeList("CZZS_SZSM",CodeConstants.CODE_MAP_MAPLIST);
List arrayForm = szsmlist.getRecords();
for(int i=0; i < arrayForm.size() ;i++){
	HashMap map = (HashMap)arrayForm.get(i);
%>
szsmArray[<%=i %>] = new szsm(<%=map.get("sl") %>, <%=map.get("ynszzs")%> ,<%=map.get("ynsqss")%>,<%=map.get("sskcs")%>);

<% } %>

function getNameById(id)
{
  	var index = -1;
  	for(var i=0;i<id.length;i++){
     	if(id.charAt(i) == '_'){
          	index = i;
     	}
  	}

  	if(index < 0){
  		alert("ID:"+id+",����ȷ��")
  		return false;
  	}
  	return id.substring(0, index);
}

function getHcByID(id)
{
  	var index = -1;
  	for(var i=0;i<id.length;i++){
     	if(id.charAt(i) == '_'){
          	index = i;
     	}
  	}

  	if(index < 0){
  		alert("ID:"+id+",����ȷ��")
  		return false;
  	}
  	return parseInt(id.substring(index+1,id.length));
}

// ��õ�ǰ����
function getCellObject(){
   	var id = trim(window.event.srcElement.id + "");
  	//var hc = getHcByID(id)
  	//var obj = eval("document.forms[0].qybqljs_" + hc);

  	var obj = eval("document.forms[0]." + id);
  	return obj;
}

// ������ҵ�걨
function compute_qynb()
{
   	var id = trim(window.event.srcElement.id + "");

	if(getHcByID(id) == 49){
		return isNum(getCellObject(), 0, null, null, 13, 0);
	}
	if(!isNum(getCellObject(), null, null, null, 15, 2)) return false;

	//������ҵ�걨����
	mathArray = mathString_CZZSNBQY;
   	computeFunction(null, id, null);

	//�������Ӧ��˰���ö������Ϊ��������˰����Ϊ
	var cell38 = eval("document.forms[0].qybqljs_38");
	if(cell38.value < 0.00){
		cell38.value = 0.00;
	}

	//��������걨����
	compute_grnb();
}


//��������걨����
function compute_grnb()
{
	if(document.forms[0].tzzrens == null) return false;

	//ȡ��Ͷ��������
	var num = document.forms[0].tzzrens.value;

	for(var i = 1; i <= num; i++){

		mathArray = mathString_CZZSNBGR;

		computeFunction(null, "grbqljs" + i + "_39", null);

		var cell40 = eval("document.forms[0].grbqljs" + i + "_40");
		var cell41 = eval("document.forms[0].grbqljs" + i + "_41");
		var cell42 = eval("document.forms[0].grbqljs" + i + "_42");

		if( cell40.value < 0.00 ){
			cell40.value = 0.00;
		}

		var sl = 0.00;
		var ks = 0.00;
		for(var j = 0; j < szsmArray.length; j++){
			var item = szsmArray[j];
			if( cell40.value >= item.ynsqss){
				sl = item.sl;
				ks = item.sskcs;
			}
		}
//		cell41.value = sl;
//		cell42.value = ks;

		cell41.value = "";
		cell42.value = "";


		computeFunction(null, "grbqljs" + i + "_40", null);
	}
}

function check_qynb()
{
	var id = trim(window.event.srcElement.id + "");

	if(getNameById(id) == "bl")
		return isNum(getCellObject(), 0, 100, null, 10, 5);
	else
		return isNum(getCellObject(), 0, null, null, 15, 2);
}

function check_grnb()
{
	if(!isNum(getCellObject(), 0, null, null, 15, 2)) return false;

	//��������걨����
	compute_grnb();

}
function showhj(){

document.forms[0].jsjdm.focus();
}
</script>

<style type="text/css">
<!--
@import url(../css/text.css);
-->
</style>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="showhj()">

<%@ include file="./include/header.jsp"%>

<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0" onkeydown="jsjdmQuery()">
<tr>
<td valign="top"><br>

<html:form action="/webapp/smsb/czzsnbAction.do" method="POST">
<html:hidden property="actionType"/>
<html:hidden property="swjgzzjgdm"/>
<html:hidden property="qxdm"/>
<html:hidden property="fsdm"/>
<html:hidden property="cjrq"/>
<html:hidden property="lrrq"/>
<html:hidden property="lrr"/>
<html:hidden property="nd"/>
<html:hidden property="nsrmc"/>
<html:hidden property="iszhsb"/>

<table align="center" cellspacing="0" class="table-99">
<tr>
	<td class="1-td1">�������ո��˶�����ҵ�ͺϻ���ҵͶ���߸�������˰����걨��</td>
</tr>
<tr>
	<td valign="top" class="1-td2"><br>

  	<table  width="100%" border="0" cellpadding="0" class="table-99">
    <tr class="black9">
      	<td width="23%" align="center" nowrap>�걨���ڣ�
      		<html:text property="sbrq" size="8" maxlength="8"
      		  onchange="getStartEndDate(this,document.forms[0].skssksrq,document.forms[0].skssjsrq, 1)"
      		/>
      	</td>
      	<td width="54%" align="center" nowrap>
      		<div align="left">˰���������ڣ�
          	<!--<html:text property="skssksrq" size="8"  maxlength="8"  onchange="compareDate('skssksrq','skssjsrq',0,this)" />��
          	<html:text property="skssjsrq" size="8" maxlength="8"   onchange="compareDate('skssksrq','skssjsrq',0,this)" />-->
                 <html:text property="skssksrq" size="11" maxlength="8" onchange="compareSkssrqDate('sbrq','skssksrq','skssjsrq',0,this,0,0)"/>��
                 <html:text property="skssjsrq" size="11" maxlength="8" onchange="compareSkssrqDate('sbrq','skssksrq','skssjsrq',0,this,0,1)"/>
        	</div>
        </td>
      	<td width="23%" align="right" nowrap>��λ�������Ԫ</td>
  	</tr>
	<tr>
  		<td colspan="3">
    	<table width="100%" border="0" cellpadding="0" cellspacing="0">
      	<tr>
        	<td width="15%" nowrap class="2-td2-t-left">
        		<div align="right"><strong>˰����������&nbsp;&nbsp;</strong></div>
        	</td>
        	<td width="15%" nowrap class="2-td2-t-left">
        		<div align="left">
           		<html:text property="jsjdm" size="8" maxlength="8" onchange="doQuery();return false;"  />
          		</div>
          	</td>
        	<td width="15%" nowrap class="2-td2-t-left">
        		<div align="right"><strong>��λ����(����)</strong>&nbsp;&nbsp;</div>
        	</td>
        	<td width="30%" nowrap class="2-td2-t-left">
        		<div align="left">&nbsp;<ttsoft:write name="czzsnbForm" property="nsrmc"/></div>
        	</td>
        	<td width="10%" nowrap class="2-td2-t-left">
        		<div align="right"><strong>��ϵ�绰</strong>&nbsp;&nbsp;</div>
        	</td>
        	<td width="15%" nowrap class="2-td2-t-center">
        		<div align="left">&nbsp;<ttsoft:write name="czzsnbForm" property="jydzlxdm"/></div>
        	</td>
      	</tr>
    	</table>
    	</td>
	</tr>
	</table>

 	<table  width="100%" border="0" cellpadding="0" class="table-99" >
  	<tr>
    	<td width="44%"><hr width="100%" size="1" class="hr1" ></td>
      	<td width="9%" class="black9">
      		<div align="center"><strong>�� λ �� Ϣ</strong></div>
      	</td>
    	<td width="47%"><hr width="100%" size="1" class="hr1" ></td>
  	</tr>
	</table>

  	<table width='100%' border='0' cellpadding='0' cellspacing='0'>
    <ttsoft:smsbtable form="czzsnbForm" property="qyList" tableId="CZZSNBQY" hasTitle="true" tabIndexStart="11"/>
  	</table><br>

	<jsp:useBean id="czzsnbForm" type="com.ttsoft.bjtax.smsb.sbzl.grsds.web.CzzsnbForm" scope="request" />

<script language="javascript">
	// ������ҵ�걨��onchange�¼�
	for(var i=1; i < 39; i++){
	  	eval("document.forms[0].qybqljs_" + i).onchange = compute_qynb;
	}
	for(var i=51; i < 55; i++){
		eval("document.forms[0].qybqljs_" + i).onchange = check_qynb;
		eval("document.forms[0].bl_" + i).onchange = check_qynb;
	}

	// ������ҵ��������ҵ����������������
<%
	Iterator items = czzsnbForm.getQyfpbl().iterator();
	while(items.hasNext()){
		HashMap item = (HashMap)items.next();
%>
		blObj = eval("document.forms[0].bl_<%=item.get("qyhc") %>");
		blObj.value = "<%=item.get("bl") %>";
		qybqljsObj =  eval("document.forms[0].qybqljs_<%=item.get("qyhc") %>");
		qybqljsObj.value = "<%=item.get("qybqljs") %>";
<%
	}
%>

</script>

	<input type="hidden" name="tzzrens" value="<%=czzsnbForm.getGrList().size() %>" />

	<% if (czzsnbForm.getGrList().size() > 0) { %>

  	<table cellspacing=0  class="table-99" >
    <tr>
    	<td> <hr width="100%" size="1" class="hr1" ></td>
      	<td width="99" class="black9">
      		<div align="center"><strong>Ͷ �� �� �� Ϣ</strong></div>
      	</td>
      	<td ><hr width="100%" size="1" class="hr1" > </td>
  	</tr>
  	</table>

	<%
	int sequence = 1;
	int tabIndex = 100;
	HashMap grsbxm = czzsnbForm.getGrsbxm();
	Iterator rsList = czzsnbForm.getGrList().values().iterator();
	while(rsList.hasNext()){
		HashMap grInfo = (HashMap)rsList.next();
		HashMap grsbsj = (HashMap)grInfo.get("grsbsj"); //�����걨����
		String jmsp = (String)grInfo.get("jmsp"); //����������־
	%>
		<table class="table-99" cellspacing="0">
		<tr>
	 		<td class="2-td1-left"><strong>Ͷ��������</strong></td>
	 		<td colspan=2  class="2-td2-t-left">&nbsp;<%=grInfo.get("tzzxm") %></td>
	 		<td class="2-td1-left"><strong>Ͷ����֤������</strong></td>
	 		<td colspan=2  class="2-td2-t-left">&nbsp;<%=com.ttsoft.bjtax.sfgl.common.code.CodeUtils.getCodeBeanLabel("ZJLX", (String)grInfo.get("zjlxdm")) %></td>
	 		<td class="2-td1-left"><strong>Ͷ����֤������</strong></td>
	 		<td colspan=2  class="2-td2-t-center">&nbsp;<%=grInfo.get("zjhm") %></td>
		</tr>
		<tr>
	 		<td class="2-td1-left">��Ŀ</td><td class="2-td1-left">�д�</td><td class="2-td1-left">�����ۼ���</td>
			<td class="2-td1-left">��Ŀ</td><td class="2-td1-left">�д�</td><td class="2-td1-left">�����ۼ���</td>
			<td class="2-td1-left">��Ŀ</td><td class="2-td1-left">�д�</td><td class="2-td1-center">�����ۼ���</td>
		</tr>
	<% 	for(int j = 39; j < 43; j++){ %>
		<tr>
			<%
			for(int i = 0; i < 9; i = i+4){
				if(grsbxm.get(String.valueOf(i + j)) != null){
			%>
				<td nowrap class="2-td2-left">
					<div align="left">&nbsp;<%=grsbxm.get(String.valueOf(i + j)) %></div>
					<input type="hidden" name="zjlxdm" id="zjlxdm<%=sequence %>_<%=i + j %>" value="<%=grInfo.get("zjlxdm") %>" />
					<input type="hidden" name="zjhm" id="zjhm<%=sequence %>_<%=i + j %>" value="<%=grInfo.get("zjhm") %>" />
					<input type="hidden" name="tzzxm" id="tzzxm<%=sequence %>_<%=i + j %>" value="<%=grInfo.get("tzzxm") %>" />
					<input type="hidden" name="grxmmc" id="grxmmc<%=sequence %>_<%=i + j %>" value="<%=grsbxm.get(String.valueOf(i + j)) %>" />
					<input type="hidden" name="grhc" id="grhc<%=sequence %>_<%=i + j %>" value="<%=i + j %>" />
				</td>
				<td nowrap class="2-td2-left"><div align="center"><%=i + j %></div></td>
				<td nowrap <% if(i == 8){ %> class="2-td2-center" <% } else { %> class="2-td2-left" <% } %> >
					<div align="center">
					<input type="textfield" size="12" maxlength="12"
						name="grbqljs"
						id="grbqljs<%=sequence %>_<%=i + j %>"
					<%

						String bqljs = "";
						if(grsbsj.get(String.valueOf(i+j)) != null){
							bqljs = (String)grsbsj.get(String.valueOf(i+j));
						}
						if((i+j) == 39){
							bqljs = grInfo.get("fpbl") != null ? (String)grInfo.get("fpbl").toString() : "";
						}
						//if(((i + j) < 44 && (i + j) != 40) || ((i + j) == 44 && "0".equals(jmsp))){
						if(((i + j) < 43 && (i + j) != 40) || ((i + j) == 44 && "0".equals(jmsp))){
					%>
							readonly class="inbright"	tabIndex="-1"
					<%	}else{ 	%>
							tabIndex="<%=sequence*tabIndex + (i + j) %>" class="inputalignright"
					<%	}	%>
						value="<%=bqljs %>" />
					</div>
				</td>
			<%	}else{ %>
				<td nowrap class="2-td2-left">&nbsp;</td>
				<td nowrap class="2-td2-left">&nbsp;</td>
				<td nowrap class="2-td2-center">&nbsp;</td>
			<%	} %>
		<%	} // for%>
		</tr>
	<% } //for %>



		</table><BR>
<%
	sequence = sequence + 1;
	} // while
	} //grList.size() > 0
%>

<script language="javascript">
	// ���ø����걨��onchange�¼� 
	//ȡ��Ͷ�������� 
	var num = document.forms[0].tzzrens.value;
	for(var j = 1; j <= num; j++){
		for(var i=39; i < 48; i++){
			var obj = eval("document.forms[0].grbqljs" + j + "_" + i);
			if(obj.readonly == true) continue;
			//obj.onchange = check_grnb;
		}
	}


	var mathString_CZZSNBGR = new Array();
	for(var i = 1; i <= num; i++){
		//mathString_CZZSNBGR[(i -1) * 4 + 0] = new MathString("grbqljs" + i + "_40=grbqljs" + i + "_39*qybqljs_38/100");
		//mathString_CZZSNBGR[(i -1) * 4 + 1] = new MathString("grbqljs" + i + "_43=grbqljs" + i + "_40*grbqljs" + i + "_41-grbqljs" + i + "_42");
		//mathString_CZZSNBGR[(i -1) * 4 + 2] = new MathString("grbqljs" + i + "_45=grbqljs" + i + "_43-grbqljs" + i + "_44");
		//mathString_CZZSNBGR[(i -1) * 4 + 3] = new MathString("grbqljs" + i + "_48=grbqljs" + i + "_45+grbqljs" + i + "_46-grbqljs" + i + "_47");
		
		
		mathString_CZZSNBGR[(i -1) * 4 + 0] = new MathString("grbqljs" + i + "_40=grbqljs" + i + "_39*qybqljs_38/100");
		mathString_CZZSNBGR[(i -1) * 4 + 1] = new MathString("grbqljs" + i + "_43=grbqljs" + i + "_40");
		mathString_CZZSNBGR[(i -1) * 4 + 2] = new MathString("grbqljs" + i + "_45=grbqljs" + i + "_43-grbqljs" + i + "_44");
		mathString_CZZSNBGR[(i -1) * 4 + 3] = new MathString("grbqljs" + i + "_48=grbqljs" + i + "_45+grbqljs" + i + "_46-grbqljs" + i + "_47");
	}

</script>

  	<table cellpadding="1" cellspacing="2" border="0" width="100%">
    <tr>
      	<td width="35%">
      		<div align="center" >
           	 <input type="image" accesskey="q" tabIndex="-1"  onClick="doQuery();return false;"
           		 onMouseOver="MM_swapImage('chaxun','',' <%=static_contextpath%>images/cx-q2.jpg ',1)"
           		 onMouseOut="MM_swapImgRestore()" value="��ѯ" src=" <%=static_contextpath%>images/cx-q1.jpg"
           		 width="79" height="22" id="chaxun" style="cursor:hand" />&nbsp;&nbsp;&nbsp;&nbsp;
           	<input type="image" accesskey="s" tabIndex="-1" onClick="doSave();return false;"
           		 onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
           		 onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/bc-s1.jpg"
           		 value="����"  width="79" height="22" id="baocun" style="cursor:hand" />&nbsp;&nbsp;&nbsp;&nbsp;
           	<input type="image" accesskey="d" tabIndex="-1" onClick="doDelete();return false;"
           		 onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/sc-d2.jpg',1)"
           		 onMouseOut="MM_swapImgRestore()" value="���" src="<%=static_contextpath%>images/sc-d1.jpg"
           		 width="79" height="22" id="shanchu" style="cursor:hand" />&nbsp;&nbsp;&nbsp;&nbsp;
           	 <input type="image" accesskey="c" tabIndex="-1" onClick="tuichu(); return false;"
           		onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)"
           		onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg"
           		width="79" height="22" style="cursor:hand" />
      		</div>
     	</td>
    </tr>
  	</table>

 	</td>
</tr>
</table><br>

</html:form>

<%@ include file="./include/footer.jsp"%>

</td>
</tr>
</table>
<SCRIPT LANGUAGE="JavaScript">
<!--
/****�������˰��Ϊ��������������ʾ��ʾ��Ϣ****/
/****20050817 HU xiaofeng****/
var nsrzt = <ttsoft:write name="czzsnbForm" property="nsrzt" filter="false"/>;
checkNsrzt();
/*************end*****************/
</SCRIPT>

</body>
</html:html>