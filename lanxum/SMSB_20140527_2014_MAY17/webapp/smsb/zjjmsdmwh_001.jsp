<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<%@page import ="com.ttsoft.bjtax.smsb.sgsswszmlr.common.Constant" %>
<%@page import ="com.ttsoft.bjtax.smsb.constant.CodeConstant" %>
<style type="text/css">
<!--
@import url(../css/text.css);
-->
</style>

<html:html>
<head>
<title>�ּܾ���˰����ά��</title>
<SCRIPT LANGUAGE="jscript" src="../js/treatImage.js"></SCRIPT>
<SCRIPT LANGUAGE="jscript" src="../js/smsb_common.js"></SCRIPT>
<script language=JavaScript src="../js/function.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
<%@ include file="include/header.jsp"%>
<html:form action="/webapp/jccswh/zjjmsdmwhAction.do" method="POST">
<html:hidden property="actionType"/>

<html:hidden property="query_szdm"/>
<html:hidden property="query_jmslxdldm"/>
<html:hidden property="query_jmslxxldm"/>

<html:hidden property="modifyKey_jmslxdm"/>
<html:hidden property="updateType"/>
<html:hidden property="pageSize"/>
<html:hidden property="nextPage"/>
<html:hidden property="totalpage"/>



<table width="94%" align="center" cellspacing="0" class="table-99">
   <tr>
      <td class="1-td1">�ּܾ���˰����ά����ѯ</td>
   </tr>
   <tr>
      <td class="1-td2"  colspan="7">
   <br>
   <table width="70%" cellspacing="0" class="table-99">
      <tr class="black9">
      	 <td width="12%" align="center" nowrap class="2-td2-t-left"> 
			<div align="right">����˰���ʹ���
            &nbsp;
			</div></td>
            <td class="2-td2-t-left" width="19%" align="center" nowrap><div align="left">
			<html:text property="query_jmslxdm"  size="25"  maxlength="50" tabIndex="-1"/>
			</div></td>
         <td width="12%" align="center" nowrap class="2-td2-t-left"> 
		 <div align="center">�ĺ�</div></td>
            <td class="2-td2-t-left" width="11%" align="center" nowrap>
			<div align="left">
            <html:text property="query_wh"  size="25" maxlength="200" tabIndex="-1"/>
			</div></td>
         <td width="13%" align="center" class="2-td2-t-left" nowrap> 
         	<div align="center" >˰��</div>
         	</td>
	     <td class="2-td2-t-center" width="32%" align="center" nowrap>
	     	<div align="left">
	     	   <select id="selectedSzdm" style="width=300px">
						<logic:iterate id="item" name="zjjmsdmwhForm" property="smsmList" indexId="index">
							<option value='<bean:write name='item' property='szsmdm'/>'>
								<bean:write name="item" property="szsmmc" />
							</option>
						</logic:iterate>
				</select>
	     	</div></td>
      </tr>
      <tr class="black9">
      	 <td width="12%" align="center" nowrap class="2-td2-left"> 
			<div align="right">����˰������ʼ���
            &nbsp;
			</div></td>
            <td class="2-td2-left" width="19%" align="center" nowrap><div align="left">
			<html:text property="query_jmszcqsnd"  size="25"  maxlength="50" tabIndex="-1"/>
			</div></td>
         <td width="12%" align="center" nowrap class="2-td2-left"> 
		 <div align="center">����˰���ʹ������</div></td>
            <td class="2-td2-left" width="11%" align="center" nowrap>
			<div align="left">
				<select id="selectedDL" onchange="doFilterJmxzxldm_byDLDM(this)" style="width=100%">
						<logic:iterate id="item" name="zjjmsdmwhForm" property="jmxzdlList"
							indexId="index">
							<option value='<bean:write name="item" property="jmxzdm"/>'>
								<bean:write name="item" property="jmxzmc" />
							</option>
						</logic:iterate>
				</select>
			</div></td>
         <td width="13%" align="center" class="2-td2-left" nowrap> 
         	<div align="center" >����˰����С�����</div>
         	</td>
	     <td class="2-td2-center" width="32%" align="left" nowrap>
		     <div align="left">
		     		<select id="selectedXL" style="width=300px">
						<logic:iterate id="item" name="zjjmsdmwhForm" property="jmxzxlList"
							indexId="index">
							<option value='<bean:write name="item" property="jmxzdm"/>'>
								<bean:write name="item" property="jmxzmc" />
							</option>
						</logic:iterate>
					</select>
			</div>
		</td>
      </tr>
      <tr class="black9">
      	 <td width="12%" align="center" nowrap class="2-td2-left"> 
			<div align="right">¼�뿪ʼ����
            &nbsp;
			</div></td>
            <td class="2-td2-left" width="19%" align="center" nowrap><div align="left">
			<html:text property="query_lrrqKS"  size="25"  maxlength="8" tabIndex="-1"/>
			</div></td>
         <td width="12%" align="center" nowrap class="2-td2-left"> 
		 <div align="center">¼���������</div></td>
            <td class="2-td2-left" width="11%" align="center" nowrap>
			<div align="left">
            <html:text property="query_lrrqJS"  size="25" maxlength="8" tabIndex="-1"/>
			</div></td>
      	<td width="12%" align="center" nowrap class="2-td2-left"> 
      		<div align="center">��Ч��ʶ
            &nbsp;
			</div></td>
            <td class="2-td2-center" align="center" nowrap><div align="left">
				<html:select property="query_yxbs" style="width=200px">
					<html:option value="">ȫ��</html:option>
					<html:option value="0">��Ч</html:option>
					<html:option value="9">��Ч</html:option>
				</html:select>
			</div></td>
      </tr>
   </table>
   <br>
   <div align="center">
   <input type="image" accesskey="q" tabIndex="-1" onClick="doQuery(); return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg ',1)" onMouseOut="MM_swapImgRestore()" alt="��ѯ" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;
   <input type="image" accesskey="c" tabIndex="-1" onClick="tuichu(); return false;" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor: hand">&nbsp;&nbsp;&nbsp;&nbsp;
   <img src="<%=static_contextpath%>images/fanhui1.jpg" value="���ص�����ҳ��" name="fanhui" width="79" height="22" border="0" onClick="doFanHui();return false;" id="fanhui" alt="���ص�����ҳ��" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()">
</div>
<br><br>
   <table width="99%" cellpadding="0" cellspacing="0" class="table-99">
      <tr>
      	 <td nowrap class="2-td1-left" width="2%"><div align = "center">���</div></td>
      	 <td nowrap class="2-td1-left" width="5%"><div align = "center">˰��</div></td>
      	  <td nowrap class="2-td1-left" width="5%"><div align = "center">�������ʴ���</div></td>
      	 <td nowrap class="2-td1-left" width="5%"><div align = "center">��������С��</div></td>
	     <td nowrap class="2-td1-left" width="5%"><div align = "center">����˰���ʹ���</div></td>
	     <td nowrap class="2-td1-left" width="20%"><div align = "center">����˰��������</div></td>
	     <td nowrap class="2-td1-left" width="5%"><div align = "center">��ʼ���</div></td>
	     <td nowrap class="2-td1-left" width="5%"><div align = "center">�ĺ�</div></td>
	     <td nowrap class="2-td1-left" width="5%"><div align = "center">¼����</div></td>
	     <td nowrap class="2-td1-left" width="5%"><div align = "center">¼��\�޸�����</div></td>
	     <td nowrap class="2-td1-left" width="5%"><div align = "center">ע�����</div></td>
	     <td nowrap class="2-td1-left" width="5%"><div align = "center">��Ч���</div></td>
	     <td nowrap class="2-td1-left" width="5%"><div align = "center">��ע</div></td>
	     <td nowrap class="2-td1-center" width="5%"><div align = "center">����</div></td>
      </tr>  
      <logic:iterate id="item1" name="zjjmsdmwhForm" property="queryList_onePage" >
      <tr onclick="changeStyle(this)" id="<bean:write name='item1' property='jmslxdm'/>">
         <td class="2-td2-left"  align="center" style="	font-size: 10pt;font-weight: bold;">
         <bean:write name='item1' property='indexId'/>&nbsp;</td>
         <td class="2-td2-left"  align="center"><bean:write name='item1' property='sz'/>&nbsp;</td>
         <td class="2-td2-left"  align="center"><bean:write name='item1' property='jmslxdldmmc'/>&nbsp;</td>
         <td class="2-td2-left"  align="center"><bean:write name='item1' property='jmslxxldmmc'/>&nbsp;</td>
         <td class="2-td2-left" align="center"><bean:write name='item1' property='jmslxdm'/>&nbsp;</td>
         <td class="2-td2-textleft"  align="center"><bean:write name='item1' property='jmslxmc'/>&nbsp;</td>
         <td class="2-td2-left" align="center"><bean:write name='item1' property='jmszcqsnd'/>&nbsp;</td>
         <td class="2-td2-textleft" align="center"><bean:write name='item1' property='wh'/>&nbsp;</td>
         <td class="2-td2-left"  align="center"><bean:write name='item1' property='lrr'/>&nbsp;</td>
         <td class="2-td2-left" align="center"><bean:write name='item1' property='lrrq'/>&nbsp;</td>
         <td class="2-td2-left"  align="center">
         	<logic:present name="item1" property="zxbz">
				 <logic:equal  name="item1" property="zxbz" value="0">
				 ����
				 </logic:equal>
				 <logic:notEqual name="item1" property="zxbz" value="0" >
				 <font color="red">��ע��</font>
				 </logic:notEqual>
         	</logic:present>
         	<logic:notPresent name="item1" property="zxbz">
         	����
         	</logic:notPresent>
		 &nbsp;</td>
		 <td class="2-td2-left"  align="center">
		 <logic:present name="item1" property="yxbs">
			 <logic:equal  name="item1" property="yxbs" value="0">
			 ��Ч
			 </logic:equal>
			 <logic:notEqual name="item1" property="yxbs" value="0" >
			 <font color="red">��Ч</font>
			 </logic:notEqual>
		</logic:present>
		<logic:notPresent name="item1" property="yxbs">
		��Ч
		</logic:notPresent>
		 &nbsp;</td>
		 <td class="2-td2-textleft" align="center"><bean:write name='item1' property='bz'/>&nbsp;</td>
		 
		 <td class="2-td2-center" align="center" nowrap="nowrap">
		 		<logic:present name="item1" property="yxbs">
					 <logic:equal  name="item1" property="yxbs" value="0">
					  	<a href="#" title="������ӽ����޸�" onclick="do2ShowMXorUpdate('<bean:write name="item1" property="jmslxdm"/>');">�޸�</a>
					 </logic:equal>
				</logic:present>
				<logic:notPresent name="item1" property="yxbs">
			 		<a href="#" title="������ӽ����޸�" onclick="do2ShowMXorUpdate('<bean:write name="item1" property="jmslxdm"/>');">�޸�</a>
				</logic:notPresent>
				
	         	
			 <a href="#" title="�������ע��������Ϣ" onclick="doChangeYxbs('<bean:write name="item1" property="jmslxdm"/>');">
				 <logic:present name="item1" property="yxbs">
					 <logic:equal  name="item1" property="yxbs" value="0">�ó���Ч</logic:equal>
					 <logic:notEqual name="item1" property="yxbs" value="0" >�ó���Ч </logic:notEqual>
				</logic:present>
				<logic:notPresent name="item1" property="yxbs">�ó���Ч</logic:notPresent></a>
		 </td>						
      </tr>
      </logic:iterate>  
</table>
<br>
<div align="right">
   (��<bean:write name="zjjmsdmwhForm" property="nextPage"/>ҳ/��<bean:write name="zjjmsdmwhForm" property="totalpage"/>ҳ)
   <!--��ҳ���ܿ�ʼ-->
   <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('first');return false;"><img name="beginpage" value="��һҳ" alt="��һҳ" onMouseOver="MM_swapImage('diyiye','','<%=static_contextpath%>images/diyiye2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/diyiye1.jpg" width="79" height="22" id="diyiye"></a>
   <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('previous');return false;"><img name="frontpage" value="ǰҳ" alt="ǰҳ" onMouseOver="MM_swapImage('shangyiye','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/shangyiye1.jpg" width="79" height="22" id="shangyiye"></a>
   <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('next');return false;"><img name="backpage" value="��ҳ" alt="��ҳ" onMouseOver="MM_swapImage('xiayiye','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/xaiyiye1.jpg"  width="79" height="22" id="xiayiye"></a>
   <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('last');return false;"><img name="endpage" value="���һҳ" alt="���һҳ" onMouseOver="MM_swapImage('zuimoye','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/zuimoye1.jpg" width="79" height="22" id="zuimoye"></a>&nbsp;&nbsp;&nbsp;&nbsp;
   <!--��ҳ���ܿ�ʼ-->
</div>
<br>
<br>
<table width="99%" border="0" cellpadding="0" cellspacing="0">
   <tr>
      <td>
         <hr width="100%" size="1" >
      </td>
	  <td width="99" align="center" class="black9">
	     <strong><font color="#0000FF">ע �� �� ��</font></strong>
	  </td>
	  <td><hr width="100%" size="1" ></td>
   </tr>
</table>
<table width="99%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
   <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
      <td height="47"  > &nbsp;&nbsp;1��¼������Ϊ��λ���֣���ʽΪ��yyyymmdd��,��20140101��</td>
   </tr>
</table>
   
</table>
</html:form>
<%@ include file="include/footer.jsp"%>

<script language="JavaScript">

//����ҳ��ʱ��������Ϊ����˰���ʹ���
function fnOnLoad()
{
	
	//ѡ���޸ļ�¼
	var selected_tr_id =  document.forms[0].modifyKey_jmslxdm.value;
	if(selected_tr_id != null && selected_tr_id != ""){
		var selected_tr_obj = document.all(selected_tr_id);
		changeStyle(selected_tr_obj);
	}
	
	//��ʾ��ʾ��Ϣ
	var actionType = document.forms[0].actionType.value;
	var saveIsSucc = '<bean:write name="zjjmsdmwhForm" property="saveIsSucc" />';
	var message = '<bean:write name="zjjmsdmwhForm" property="message" />';
	
	if(message!=""){
		alert(message);
	}else{
		if(actionType=="Save" || actionType=="Update"){
			if(saveIsSucc == '<%=Constant.CONS_SGLR_SAVEISSUCC_Y%>'){
				alert("����ɹ�!");
			}
		}else{
			// ҳ����뽹��ȷ��
			document.forms[0].query_jmslxdm.focus();
		}
	}
	
	//ѡ����������
	selectedAll();
	
}

/**
 * ��ʼ��ʱ��ѡ����������
 */
function  selectedAll(){
	//��ȡ����ѯ�����е�����ֵ
		var szdm = document.forms[0].query_szdm.value;
		var jmslxdldm = document.forms[0].query_jmslxdldm.value;
		var jmslxxldm = document.forms[0].query_jmslxxldm.value;
	
	
	
	//���ݼ������ʴ���������С�ಢѡ��
		document.all("selectedDL").value=jmslxdldm;
		doFilterJmxzxldm_byDLDM(document.all("selectedDL"));
	
	
	//��������ֵѡ�������б��е�ֵ
		document.all("selectedSzdm").value= szdm;
		document.all("selectedXL").value=jmslxxldm;
}



/**
 * �鿴��ϸ ���� �޸���Ϣ
 */
	function do2ShowMXorUpdate(jmslxdm){
		if(jmslxdm !=""){
			document.forms[0].modifyKey_jmslxdm.value = jmslxdm;
			doSubmitForm("ShowOne");
			return false;
		}
	}
	
	function doChangeYxbs(jmslxdm){
		if(jmslxdm != ""){
			if(confirm("�Ƿ�ı����Ϣ����Ч״̬����ȷ�ϣ�")){
				document.forms[0].modifyKey_jmslxdm.value = jmslxdm;
				document.forms[0].updateType.value = "<%=CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_YXBS%>";
				doSubmitForm("Update");
				return false;
			}else{
				return false;
			}
		}
	}
	

	// ��ѯ����У�鼰��ת 
	function doQuery() {
		//����ѯֵ
		if(!doCheck()){
			return false;
		}
		getSelectedValues();
		document.forms[0].target="";
		document.forms[0].nextPage.value = 1;
		doSubmitForm("Query");
		return false;
	}
	
	function doFanHui(){
		getSelectedValues();
		doSubmitForm("Show");
	}
	
	/**
	*ִ�м��
	*/
	function doCheck(){
		var lrrqKS = document.all("query_lrrqKS");
		var lrrqJS = document.all("query_lrrqJS");
		
		//У�鿪ʼ�����������Ƿ�Ϸ�
		if(lrrqKS.value != "" && !isDate(lrrqKS,null)){
			return false;
		}
		
		if(lrrqJS.value !="" && !isDate(lrrqJS,null)){
			return false;
		}
		
		return true;
		
	}
	
	/**
	*��ѡ���и�Ϊѡ��״̬
	*
	*/
	var selectedTR_OBJ;//��¼�ϴα�ѡ�е�TR ����
	function changeStyle(trObj){
		if(trObj && selectedTR_OBJ != trObj){
			selectedTR_OBJ =  trObj
			//��ǰ���ó�ѡ��״̬
			var cNodes = trObj.childNodes;
			for(var index=0;index < cNodes.length; index ++){
				//alert("1--"+cNodes[index].tagName);
				//alert("2--"+cNodes[index].className);
				if(cNodes[index].className == "2-td2-left"){
					cNodes[index].className=cNodes[index].className+"-center1-selected";
				}else{
					cNodes[index].className=cNodes[index].className+"-selected";
				}
				
			}
			
			//�������ó�δѡ��״̬
			var re1 = /-center1-selected/g;
			var re =/-selected/g;
			var tableObj = trObj.parentNode;//���table����
			var allOtherTrs = tableObj.childNodes;//��õ�ǰtable�µ�������
			//index =1 ���Ա����н��в���
			for(var index =1; index < allOtherTrs.length; index ++ ){
				if(trObj != allOtherTrs[index]){
					//��õ�ǰTR �µ� ���� TD
					var tempTDs = allOtherTrs[index].childNodes;
					//����ÿ�е�TD���������Ǵ�ѡ��״̬��Ϊ��ѡ��״̬
					for(var cIndex =0; cIndex < tempTDs.length; cIndex++){
						var cTDObj = tempTDs[cIndex];
						// �� "" �滻 "-center-selected"��
						// �� "" �滻 "-selected"��
						cTDObj.className = cTDObj.className.replace(re1, "").replace(re, "");   
						//alert(cTDObj.className);
					}
				}
			}
			
		}
	}
	
	/**
	*��ø�����ѯ������ֵ
	*/
	function getSelectedValues(){
		
		var szdm = document.all("selectedSzdm").value;
		var jmslxdldm =document.all("selectedDL").value;
		var jmslxxldm =document.all("selectedXL").value;
		
		document.forms[0].query_szdm.value=szdm;
		document.forms[0].query_jmslxdldm.value=jmslxdldm;
		document.forms[0].query_jmslxxldm.value=jmslxxldm;
		
		
	}
	
	
	
	
	
	
	// ��ҳ
	function fn_ChangePage(type)
	{
		//��ȡ��һ�β�������
		temp=document.all.actionType.value;
		//���õ�ǰ��������
		if(temp=="Query" || temp=="ChangePage"){
			//temp="ChangePage";
			temp="Query";
		} else{
			temp="Show";
		}
		//
		var tmpNextPage=document.forms[0].nextPage.value*1;
		var tmpTotalPage=document.forms[0].totalpage.value*1;
		//alert("tmpNextPage:"+tmpNextPage+"|"+"tmpTotalPage:"+tmpTotalPage+"|"+"type:"+type);
		if(temp=="Show"){
			alert("û���κ��Ѳ�ѯ����,���Ȳ�ѯ...");
			return false;
		}
		if (type == "first")
		{
			if(tmpNextPage==1){
				alert("���β�ѯ�ѵ����һҳ,�뷵��...");
				return false;
			}else{
					document.forms[0].nextPage.value="1";
			}
		}
		else if (type == "previous")
		{
			if(tmpNextPage==1){
				alert("���β�ѯ�ѵ����һҳ,�뷵��...");
				return false;
			}else{
					document.forms[0].nextPage.value =(tmpNextPage-1)+"";
			}
		}
		else if (type == "next")
		{
			if(tmpNextPage>=tmpTotalPage){
				alert("���β�ѯ�ѵ������һҳ,�뷵��...");
				return false;
			}else{
					document.forms[0].nextPage.value =(tmpNextPage+1)+"";
			}
		}
		else if (type == "last")
		{
			if(tmpTotalPage==1){
				alert("���β�ѯֻ����һҳ,�뷵��...");
				return false;
			}else if(tmpNextPage>=tmpTotalPage){
				alert("���β�ѯ�ѵ������һҳ,�뷵��...");
				return false;
			}
			else{
				document.forms[0].nextPage.value=document.forms[0].totalpage.value;
			}
		}
		//�ύ��ѯ
		doSubmitForm(temp);
		return false;
	}
	
</script>
<script type="text/javascript">
	/**
	*ͨ������˰���ʴ���������С�����
	*/
	function doFilterJmxzxldm_byDLDM(obj){
		//��ǰѡ������ֵ
		var select_dldm= obj.value;
		
		//��õ�ǰ�е�����td�����±�Ϊ2
		var jmxzdm_xl_td_Obj = obj.parentNode.parentNode.parentNode.cells[5];
		
		//��õ�ǰ�м�������С��
		var jmxzxlObj = jmxzdm_xl_td_Obj.childNodes[0].childNodes[0];
		//ɾ����ǰС��select��ǩ����
		jmxzdm_xl_td_Obj.childNodes[0].removeChild(jmxzxlObj);
		//����ѡ�������������С�������˵�
		var valuesArr_jmxz_xl = getJmxz_dl_xl_Arr("jmxz_xl",select_dldm);//query_jmslxxldm
		select_oNewItem = fnAddSelect(jmxzdm_xl_td_Obj.childNodes[0],"selectedXL", valuesArr_jmxz_xl, "");
		//alert(jmxzxlObj.id);
		
		
		
	}
	
	//��ü������ʴ��ࡢС��Arr
	function getJmxz_dl_xl_Arr(xzType,jmxzdm_dl) {
		var jmxzArr = new Array();
		
		//����С��
		if(xzType=="jmxz_xl"){
			
			//���ӿ���
			if(jmxzdm_dl != ""){
				var blankJmxz_xl = new Array();
				blankJmxz_xl.push("");
				blankJmxz_xl.push("");
				jmxzArr.push(blankJmxz_xl);
			}
			
			
			<logic:iterate id="item" name="zjjmsdmwhForm"  property ="jmxzxlList" indexId="index">
			var jmxzdm_xl_1 = '<bean:write name="item" property="jmxzdm"/>';
			var jmxzmc_xl_1 = '<bean:write name="item" property="jmxzmc"/>';
	
			var oneJmxz_xl = new Array();
			oneJmxz_xl.push(jmxzdm_xl_1);
			oneJmxz_xl.push(jmxzmc_xl_1);
			//�������ʴ��಻Ϊ�գ���ǰ�����ǹ��˲�������ѡ����࣬������ӦС��
			if(jmxzdm_dl != ""){
				//alert(jmxzdm_dl+"---"+jmxzdm_xl_1.substr(1,4));
				if(jmxzdm_dl==jmxzdm_xl_1.substr(0,4)){
					jmxzArr.push(oneJmxz_xl);
				}
			}else{
				jmxzArr.push(oneJmxz_xl);
			}
			</logic:iterate>
		}

		return jmxzArr;
	}
	
	//��ָ��Ԫ���´���һ��select������
	function fnAddSelect(cellObj, newItemId, valuesArr, value) {

		//����һ��select��ǩ
		var oNewItem = document.createElement("SELECT");
		oNewItem.id = newItemId;
		//if(newItemId == "sglr_select_zssmdm"){
			oNewItem.style.width="300px";
		//}

		for ( var index = 0; index < valuesArr.length; index++) {
			var oValueArr = valuesArr[index];
			var optionValue = oValueArr[0];//option��ֵ
			var optionName = oValueArr[1];//option������

			//����һ��option��ǩ
			var optionObj = document.createElement("OPTION");
			optionObj.innerHTML = optionName;
			optionObj.value = optionValue;
			if (value == optionValue) {
				optionObj.selected = true;
			}

			//
			oNewItem.insertAdjacentElement("beforeEnd", optionObj);
		}
		cellObj.insertAdjacentElement("afterBegin", oNewItem);

		return oNewItem;
	}
	
	




</script>
</body>
</html:html>


