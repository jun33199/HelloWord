<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Language" content="zh-cn">
<link href="../css/beijing.css" rel="stylesheet" type="text/css">
<title>��֪����ά��</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/function.js"></script>
</head>
<body  leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0"  onload="init()">
<%@ include file="./include/header.jsp"%>
<%
    java.util.List dqjsList = com.ttsoft.bjtax.smsb.gzwh.GzwhHelper.getDqjsSelect(userData.getSsdwdm());
    pageContext.setAttribute("DqjsList",dqjsList);
%>
<html:form action="/webapp/smsb/gzsxwhAction.do" method="POST" >
<html:hidden property="actionType" />
<html:hidden property="saveType" />
<html:hidden property="modifyIndex" />
<html:hidden property="pgNum" />
<html:hidden property="length" />
<html:hidden property="pgSum" />
<html:hidden property="nsrmc" size="20" readonly="true" tabindex="-1" />
<html:hidden property="strNow" />

<table width="820" height="302" border="0" cellpadding="0" cellspacing="0"  align="center">
  <tr>
    <td>
    	<br>
      	<table align="center" cellspacing="0"  class="table-99"  width="98%">
	       <tr>
	          <td class="1-td1">��֪����ά��</td>
	        </tr>
	     
	        <tr>
	          <td class="1-td2" >
			  	  <br>
            <table cellspacing="0" class="table-99" width="98%">
              <tr>
		<td width="16%" class="3-td1-left" align="center">
			<div><B>���ͷ�ʽ</B></div>
		</td>
		<td colspan="3" class="3-td1-centen" colspan="3">	
			<div align="left">
				<html:radio value="1" property="chooseTypeRadio" onclick="init()" />��������뷽ʽ
                <html:radio value="2" property="chooseTypeRadio" onclick="init()" />Ⱥ����ʽ
				<html:radio value="3" property="chooseTypeRadio" onclick="init()" />��������뵼�뷽ʽ
			</div>
		</td>
              </tr>
   
              <tr id="df" style="display:block">
                <td width="16%" class="2-td2-left">
                  <div align="center">������������</div></td>
                <td width="38%" class="2-td2-left"><div align="left"><html:text property="jsjdm" size="20" onchange="return doGetNsrmcSubmit()" onkeydown="if(window.event.keyCode==13) { window.event.keyCode=9 }"/></div></td>
                <td width="16%" class="2-td2-left"><div align="center">&nbsp;��˰������&nbsp;</div></td>
                <td id=tdNsrmc width="30%" class="2-td2-center">
                	<div align="left"><ttsoft:write name='gzsxwhForm' property='nsrmc'/>
                	</div>
                </td>
              </tr>
			
              <tr id="qf1" style="display:block">
                <td class="2-td2-left" width="16%"><div align="center">ѡ����ҵ����</div></td>
                <td class="2-td2-left" width="38%"><div align="left"><ttsoft:define id="djzclxItems" codekey="ZQWH_DJZCLX"/>
                		<html:select property="qylx" style="width:220px">
                                	 <html:option value="0">* ������ҵ����</html:option>
                                         <html:options collection="djzclxItems" property="value" labelProperty="label"/>
                                       </html:select></div>
                <td class="2-td2-left" width="16%"><div align="center">&nbsp;&nbsp;ѡ����ҵ���&nbsp;&nbsp;</div></td>
                <td class="2-td2-center" width="30%"><div align="left"><ttsoft:define id="gjbzhyItems" codekey="ZBZL_GJBZHY"/>
                			<html:select property="hylb" style="width:220px">
                                	 <html:option value="0">* ������ҵ���</html:option>
                                         <html:options collection="gjbzhyItems" property="value" labelProperty="label"/>
                                       </html:select></div></td>
              </tr>
              
			  <!-- 20040308 Shi Yanfeng �����������뷽ʽ -->
			  <tr id="dr" style="display:block">
                <td width="16%" class="2-td2-left" nowrap>
                  <div align="center">ѡ��������</div></td>
                <td width="84%" class="2-td2-left" colspan="3">
                <div id="divFile" align="left">
					<bean:define id="phList" name="gzsxwhForm" property="phList" />
                    <html:select property="ph" >
                          <html:options collection="phList" property="ph" labelProperty="ph"/>
                    </html:select>
				</div></td>
                <!-- <td width="16%" class="2-td2-left"><div align="right">&nbsp;</div></td>
                <td  width="38%" class="2-td2-center"><div align="left">&nbsp;</div></td> -->
              </tr>
              
              
			  <!-- 20040308 Shi Yanfeng �����������뷽ʽ -->
              <tr id="qf2" style="display:block">
                <td class="2-td2-left" width="16%"><div align="center">ѡ���������</div></td>
                <td class="2-td2-left" width="38%">
                	<div align="left">
                	<%--<ttsoft:define id="swjgzzjgItems" codekey="GZWH_SWJGZZJG"/>--%>
                		<html:select property="dqjs" style="width:220px">
                              <%--<html:option value="0">* ���е�������</html:option>--%>
                              <%--<html:options collection="swjgzzjgItems" property="value" labelProperty="label"/>--%>
                             <html:options collection="DqjsList" property="value" labelProperty="label"/>
                		</html:select>
                	</div>
                </td>
                
                
                
                
                <td class="2-td2-left" width="16%"><div align="center">ѡ���Ϸ�ʽ</div></td>
                <td class="2-td2-center" width="30%"><div align="left">
                	<html:select size="1" property="jhfs">
                       <html:option value="0">��</html:option>
					   <html:option value="1">��</html:option>
					 </html:select></div></td>
              </tr>
        
              <tr>
                <td class="2-td2-left" width="16%"><div align="center">ѡ���֪����</div></td>
                <td class="2-td2-left" width="38%"><div align="left"><html:select size="1" property="gzlx">
                                	   <html:option value="0">������Ϣ</html:option>
					   <html:option value="1">�쳣��Ϣ</html:option>
					 </html:select></div></td>
                <td class="2-td2-left" width="16%">&nbsp;</td>
                <td class="2-td2-center" width="30%">&nbsp;</td>
              </tr>
            
              <tr>
                <td class="2-td2-left" width="16%"><div align="center">��֪��ʼ����</div></td>
                <td class="2-td2-left" width="38%"><div align="left"><html:text property="gzqsrq" size="20"/></div></td>
                <td class="2-td2-left" width="16%"><div align="center">��֪��ֹ����</div></td>
                <td class="2-td2-center" width="30%"><div align="left"><html:text property="gzjzrq" size="20"/></div></td>
              </tr>
              <tr>
                <td colspan="3" class="2-td2-left">&nbsp;</td>
                <td class="2-td2-center"><input name="I2" type="image" accesskey="q" value="��ѯ"  onclick='doSelectSubmit();return false;' onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="��ѯ" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun"></td>
              </tr>
            </table>

            <br>
 
            <table id="gzsxlistTable" border="1" cellpadding="0" cellspacing="0" class="table-99" height="1" width="99%">
              <tr>
                <td width="5%" class="2-td1-left">ɾ��</td>
                <td width="13%" class="2-td1-left" height="1">��֪�������</td>
                <td width="12%" class="2-td1-left" height="1">��֪��������</td>
                <td width="16%" class="2-td1-left" height="1">��֪������ϸ��Ϣ</td>
                <td width="8%" class="2-td1-left" height="1">��֪����</td>
                <td width="13%" class="2-td1-left" height="1">��֪��ʼ����</td>
                <td width="12%" class="2-td1-left" height="1">��֪��ֹ����</td>
                <td width="9%" class="2-td1-left" height="1">¼����</td>
                <td width="12%" class="2-td1-center" height="1">&nbsp; </td>
              </tr>
              <bean:define id="gzlist" name="gzsxwhForm" property="dataList"/>
              <%
                com.ttsoft.bjtax.smsb.gzwh.web.GzsxwhForm form = (com.ttsoft.bjtax.smsb.gzwh.web.GzsxwhForm)request.getAttribute("gzsxwhForm");
                int pgLength = form.getLength();
                int pgSum = form.getPgSum();
                int pgNum = form.getPgNum();
                int start = pgLength*(pgNum-1);
                int Length = String.valueOf(pgSum).length();
                //System.out.println("client pages=="+pgSum);
                //System.out.println("client size=="+form.getDataList().size());
              %>
              <% int rowIndex = start;%>
              <logic:iterate id="items" name="gzlist" indexId="index" >
                <bean:define id="item" name="items"/>
               <tr>
                <td width="5%" class="2-td2-left"><input type="checkbox" name="deleteCheckbox" value="<ttsoft:write name="item" property="modifyIndex"/>">&nbsp</td>
                <td width="13%" class="2-td2-left" height="23"><ttsoft:write name="item" property="jsjdm"/>&nbsp</td>
                <td width="12%" class="2-td2-left" height="23"><ttsoft:write name="item" property="nsrmc"/>&nbsp</td>
                <td width="16%" class="2-td2-left" height="23"><ttsoft:write name="item" property="gzsxnr"/>&nbsp</td>
                <td width="8%" class="2-td2-left" height="23"><ttsoft:write name="item" property="fzcbs"/>&nbsp</td>
                <td width="13%" class="2-td2-left" height="23"><ttsoft:write name="item" property="gzsxksrq"/>&nbsp</td>
                <td width="12%" class="2-td2-left" height="23"><ttsoft:write name="item" property="gzsxjsrq"/>&nbsp</td>
                <td width="9%" class="2-td2-left" height="23"><ttsoft:write name="item" property="lrr"/>&nbsp</td>
                <td width="12%" class="2-td2-center" height="23"><input name="xiugai" type="image" value="<ttsoft:write name="item" property="modifyIndex"/>" onClick="doModifySubmit(this.value);return false;" onMouseOver="MM_swapImage('xiugai<ttsoft:write name="item" property="modifyIndex"/>','','<%=static_contextpath%>images/xiugai2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="�޸�" src="<%=static_contextpath%>images/xiugai1.jpg" width="79" height="22" id="xiugai<ttsoft:write name="item" property="modifyIndex"/>"></td>
               </tr>
              </logic:iterate>
            </table>
            <br>
            <table  class="table-99">
              <tr class="black9">
                <td align="right">��(<input type="text" name="gotoPG" value="<%=pgNum%>" size="<%=Length%>" onchange="doGotoPG()">/<%=pgSum%>)ҳ
                  <%if(pgNum > 1 ) {%>
                    <img src="<%=static_contextpath%>images/diyiye1.jpg" alt="��һҳ" id="first" onMouseOver="MM_swapImage('first','','<%=static_contextpath%>images/diyiye2.jpg',1)" onMouseOut="MM_swapImage('first','','<%=static_contextpath%>images/diyiye1.jpg',1)" onclick="goToPage('1');return false;" style="cursor:hand">
                    <img src="<%=static_contextpath%>images/shangyiye1.jpg" alt="��һҳ" id="backward" onMouseOver="MM_swapImage('backward','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImage('backward','','<%=static_contextpath%>images/shangyiye1.jpg',1)" onclick="goToPage('<%=pgNum-1%>');return false;" style="cursor:hand">
                  <%}%>
                  <%if(pgNum < pgSum) {%>
                    <img src="<%=static_contextpath%>images/xaiyiye1.jpg" alt="��һҳ" id="forward" onMouseOver="MM_swapImage('forward','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImage('forward','','<%=static_contextpath%>images/xaiyiye1.jpg',1)" onclick="goToPage('<%=pgNum+1%>');return false;" style="cursor:hand">
                    <img src="<%=static_contextpath%>images/zuimoye1.jpg" alt="��ĩҳ" id="last" onMouseOver="MM_swapImage('last','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImage('last','','<%=static_contextpath%>images/zuimoye1.jpg',1)" onclick="goToPage('<%=pgSum%>');return false;" style="cursor:hand">
                  <%}%>
                </td>
              </tr>
            </table>
            <br>
            <table border="0" width="100%">
              <tr>
                <td width="22%">&nbsp; </td>
                <td width="15%"><input name="shanchu1" type="image" accesskey="d" value="ɾ��"  onClick="doDeleteSubmit();return false;" onMouseOver="MM_swapImage('shanchu1','','<%=static_contextpath%>images/sc-d2.jpg',1)" onMouseOut="MM_swapImgRestore()"  src="<%=static_contextpath%>images/sc-d1.jpg" width="79" height="22" id="shachu1"></td>
                <td width="15%"><input name="shanchu" type="image" accesskey="x" value="ȫ��ɾ��"  onClick="doDeleteAllSubmit();return false;" onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)" onMouseOut="MM_swapImgRestore()"  src="<%=static_contextpath%>images/qbsc-x1.jpg" width="79" height="22" id="shachu"></td>
                <td width="15%"><input name="xinzeng" type="image" accesskey="a" value="����"  onClick="doAddSubmit();return false;" onMouseOver="MM_swapImage('xinzeng','','<%=static_contextpath%>images/zj-a2.jpg',1)" onMouseOut="MM_swapImgRestore()"  src="<%=static_contextpath%>images/zj-a1.jpg" width="79" height="22" id="xinzeng"></td>
                <td width="15%"><input name="tuichu" type="image" accesskey="c" onClick="tuichu();return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22">
                </td>
                <td width="18%">&nbsp;</td>
              </tr>
            </table> </td>
	   	    </tr>
	    </table>
    </td>
  </tr>
 </table>
</html:form>
</body>
<%@ include file="./include/footer.jsp"%>
<script language="JavaScript">
function init()
{
	
  var df = document.all("df");
  var qf1 = document.all("qf1");
  var qf2 = document.all("qf2");
  var dr  = document.all("dr");
  var oRadio = document.all("chooseTypeRadio");
  if(oRadio[0].checked==true){

    df.style.display="block";
    document.forms[0].jsjdm.disabled=false;
    qf1.style.display="none";
    document.forms[0].qylx.disabled=true;
    document.forms[0].qylx.options[0].selected=true;
    document.forms[0].hylb.disabled=true;
    document.forms[0].hylb.options[0].selected=true;
    qf2.style.display="none";
    document.forms[0].dqjs.disabled=true;
    document.forms[0].dqjs.options[0].selected=true;
    document.forms[0].jhfs.disabled=true;
    document.forms[0].jhfs.options[0].selected=true;
	dr.style.display="none";
  }else if(oRadio[1].checked==true){
    df.style.display="none";
    document.forms[0].jsjdm.disabled=true;
    document.forms[0].jsjdm.value="";
    document.forms[0].nsrmc.value="";
    document.all.tdNsrmc.innerText="  ";
    qf1.style.display="block";
    document.forms[0].qylx.disabled=false;
    document.forms[0].hylb.disabled=false;
    qf2.style.display="block";
    document.forms[0].dqjs.disabled=false;
    document.forms[0].jhfs.disabled=false;
	dr.style.display="none";
  }else if(oRadio[2].checked==true){
    df.style.display="none";
    document.forms[0].jsjdm.disabled=true;
    qf1.style.display="none";
    document.forms[0].qylx.disabled=true;
    document.forms[0].qylx.options[0].selected=true;
    document.forms[0].hylb.disabled=true;
    document.forms[0].hylb.options[0].selected=true;
    qf2.style.display="none";
    document.forms[0].dqjs.disabled=true;
    document.forms[0].dqjs.options[0].selected=true;
    document.forms[0].jhfs.disabled=true;
    document.forms[0].jhfs.options[0].selected=true;
	dr.style.display="block";
  }

  //hideModify();
}
function hideModify()
{
  var oRadio = document.all("chooseTypeRadio");
  strnow = <ttsoft:write name="gzsxwhForm" property="strNow" />;  //getNowTime();
  for(i=1;i<gzsxlistTable.rows.length;i++){
    if( compare(gzsxlistTable.rows[i].cells[5].innerText,strnow)<=0 || oRadio[2].checked==true){
      gzsxlistTable.rows[i].cells[8].innerText = " ";
    }
  }
}
hideModify();
//�õ���ǰ����
//function getNowTime()
//{
//  now = new Date();
//  intMonth =parseInt(now.getMonth())+1;
//  strMonth = "";
//  strDate = "";
//  if(intMonth<10){
//   strMonth = "0"+ intMonth;
//  }else{
//   strMonth = intMonth;
//  }
//  if(parseInt(now.getDate())<10){
//   strDate = "0"+ now.getDate();
//  }else{
//   strDate = now.getDate();
//  }
//  strnow = "" + now.getYear() + strMonth + strDate;
//  return strnow;
//}
function doGetNsrmcSubmit(){
  var oRadio = document.all("chooseTypeRadio");
  if(oRadio[0].checked==true
     && document.forms[0].chooseTypeRadio[0].disabled == false){
    strJsjdm = document.forms[0].jsjdm.value+ "";
    if(strJsjdm == "*"){
      document.all.tdNsrmc.innerText="  ";
      return ;
    }else if( trim(strJsjdm).length!=8 && trim(strJsjdm).length!=7) {
      document.all.tdNsrmc.innerText="  ";
      alert("ѡ�������������룬����������������룬��ʽΪ8λ����")
      return ;
    }
  }else{
      document.all.tdNsrmc.innerText="  ";
    return false;
  }
  if(doSubmitForm("GetNsrmc")==false){
    return false;
  }
  return false;
}
//�����ύ
function doSelectSubmit()
{
  //ѡ�������������룬���������check
  var oRadio = document.all("chooseTypeRadio");  
  if(oRadio[0].checked==true){
    strJsjdm = document.forms[0].jsjdm.value;
    if(strJsjdm == "*"){
      document.forms[0].nsrmc.value="";
      //return true;
    }else if( trim(strJsjdm).length!=8 && trim(strJsjdm).length!=7 ){
      alert("ѡ�������������룬����������������룬��ʽΪ8λ����");
      document.forms[0].nsrmc.value="";
      return false;
    }
  }
  //������ʼ����Check
  strGzqsrq =document.all.gzqsrq.value;
  if(isFullDate(strGzqsrq,0,null,false)==false){
    alert("��֪��ʼ���ڸ�ʽ����ȷ��");
    return false;
  }
  //���ڽ�������Check
  strGzjzrq =document.all.gzjzrq.value;
  if(isFullDate(strGzjzrq,0,null,false)==false){
    alert("��֪��ֹ���ڸ�ʽ����ȷ��");
    return false;
  }
  //������ʼ���ں����ڽ������ڹ�ϵCheck
  if( compare(strGzjzrq,strGzqsrq)<0 ){
    alert("������ʼ����ӦС�����ڽ������ڡ�");
    return false;
  }
  document.all.pgNum.value = 1;
  if(doSubmitForm("Query")==false){
    return false;
  }
}
//�����ύ
function doAddSubmit()
{
  document.forms[0].actionType.value = "Add";
  document.forms[0].saveType.value = "Insert";
  document.forms[0].submit();
}
//�޸��ύ
function doModifySubmit(thisIndex)
{
  document.forms[0].actionType.value = "Modify";
  document.forms[0].saveType.value = "Update";
  document.forms[0].modifyIndex.value = thisIndex;
  document.forms[0].submit();
}
function compare(value1,value2){
  if(parseInt(value1)<parseInt(value2)){
    return -1;
  }else if(parseInt(value1)==parseInt(value2)){
    return 0;
  }else{
    return 1;
  }
}

function goToPage(pgNum) {
  document.all.pgNum.value = pgNum;
  doSubmitForm('Query');
}

function doGotoPG() {
  var gotoPG = document.all.gotoPG;
  var thisPG = document.all.pgNum;
  if(!fnIsIntNum(gotoPG.value)){
    gotoPG.value=thisPG.value;
    return;
  }
  if(parseInt(gotoPG.value)>parseInt(document.all.pgSum.value)){
    gotoPG.value=thisPG.value;
    return;
   }
  if(gotoPG.value!=thisPG.value) {
    thisPG.value = gotoPG.value;
    doSubmitForm('Query');
  }
//  return;
}

function doDeleteAllSubmit(){
  var checkbox =document.all("deleteCheckBox");
  if(checkbox){
  }else{
    alert("û�п�ɾ��������");
    return false;
  }
  if(window.confirm("�ò������޸����ݿ��е�����,�Ҳ����Զ��ָ�,��ȷ��")){
    doSubmitForm("DeleteAll");
  }
}

//checkboxes check
function checkBoxesCheck(checkboxesName)
{
  var checkbox =document.all(checkboxesName);
  if(checkbox){
    if(checkbox.length){
      for(i=0;i<checkbox.length;i++){
        if(checkbox[i].checked == true){
          return true;
        }
      }
      alert("������ѡ��һ��");
      return false;
    }else{
      if(checkbox.checked == true){
        return true;
      }else{
        alert("������ѡ��һ��");
        return false;
      }
    }
  }
  alert("û�п�ɾ��������");
  return false;
}
//ɾ���ύ
function doDeleteSubmit()
{
  if(checkBoxesCheck("deleteCheckBox")==false){
    return false;
  }
//  if(doSubmitForm("Delete")==false){
//    return false;
//  }
  doSubmitForm("Delete");
}
function cometomiancx(){
//alert("������ѯ��ҳ��");
document.all.actionType.value="cometomain";
document.forms[0].submit();
}
</script>
</html:html>
