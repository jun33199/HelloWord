<%@ page import="java.util.Map" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.code.CodeManager" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.constant.CodeConstants" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.util.SfHashList"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>�������ո��幤�̻�����˰��ȣ��·ݣ��걨��</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/function.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/Bolan.js"></script>
<script language=JavaScript src="../js/MathString.js"></script>
<script language=JavaScript src="../js/Stack.js"></script>
<script language=JavaScript src="../js/reader.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="show()">

<%@ include file="include/header.jsp" %>
  <script language="JavaScript" type="text/JavaScript">
var slnum = 0;
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
  slnum=<%=i%>;
<%
}
%>
function show(){
	// if(document.forms[0].actionType.value=="Show")
document.forms[0].jsjdm.focus();
if(document.all("je")[0].value==null||document.all("je")[0].value==""){
  document.all("je")[0].value = 0;
  document.all("je")[1].value = 0;
  document.all("je")[2].value = 0;
  document.all("je")[3].value = 0;
  document.all("je")[4].value = 0;
  document.all("je")[5].value = 0;
  document.all("je")[6].value=szsmArray[0].sl;
  document.all("je")[7].value=szsmArray[0].sskcs;
  document.all("je")[8].value=0;
  document.all("je")[9].value=0;
  document.all("je")[10].value=0;
  document.all("je")[11].value=0;
  //document.all("je")[12].value=0;
}
}

function cleannum(){
 if(confirm("�Ƿ������ԭҳ��������Ϣ")){
  document.all("je")[0].value = 0;
  document.all("je")[1].value = 0;
  document.all("je")[2].value = 0;
  document.all("je")[3].value = 0;
  document.all("je")[4].value = 0;
  document.all("je")[5].value = 0;
  document.all("je")[6].value=szsmArray[0].sl;
  document.all("je")[7].value=szsmArray[0].sskcs;
  document.all("je")[8].value=0;
  document.all("je")[9].value=0;
  document.all("je")[10].value=0;
  document.all("je")[11].value=0;
 }
}

function jisuan(a){
if(isNum(a,0,null,1,15,2)){
var jsjdm;
jsjdm = document.forms[0].jsjdm.value;
	if(jsjdm==null || jsjdm==""){
        alert("��������벻�����ǿգ�");
        document.all("je")[0].value = 0;
        document.all("je")[1].value = 0;
        document.all("je")[2].value = 0;
        document.all("je")[3].value = 0;
        document.all("je")[4].value = 0;
        document.all("je")[5].value = 0;
        document.all("je")[6].value=szsmArray[0].sl;
        document.all("je")[7].value=szsmArray[0].sskcs;
        document.all("je")[8].value=0;
        document.all("je")[9].value=0;
        document.all("je")[10].value=0;
        document.all("je")[11].value=0;
        //document.forms[0].jsjdm.focus();
        }else{
document.all("je")[4].value=parseFloat(document.all("je")[0].value)-parseFloat(document.all("je")[1].value)-parseFloat(document.all("je")[2].value)-parseFloat(document.all("je")[3].value);

if(document.all("je")[4].value > 0)
{     tmp=parseFloat(document.all("je")[4].value);
      for(k=0;k<=slnum;k++){
      if(tmp>=szsmArray[k].ynsqss && tmp<szsmArray[k].ynszzs){
                                        document.all("je")[5].value=parseFloat(szsmArray[k].sl);
                                        document.all("je")[6].value=parseFloat(szsmArray[k].sskcs);
				}
                                if(tmp>=szsmArray[slnum].ynsqss)
                               {
                                    document.all("je")[5].value=parseFloat(szsmArray[k].sl);
                                    document.all("je")[6].value=parseFloat(szsmArray[k].sskcs);
                               }

      }

    document.all("je")[7].value=round(parseFloat(document.all("je")[4].value)*parseFloat(document.all("je")[5].value)-parseFloat(document.all("je")[6].value));

    document.all("je")[9].value=parseFloat(document.all("je")[7].value)-parseFloat(document.all("je")[8].value);

    document.all("je")[11].value=parseFloat(document.all("je")[9].value)-parseFloat(document.all("je")[10].value);

}
if(document.all("je")[4].value <= 0)
{

  document.all("je")[5].value=szsmArray[0].sl;
  document.all("je")[6].value=szsmArray[0].sskcs;
  document.all("je")[7].value=round(parseFloat(document.all("je")[4].value)*parseFloat(document.all("je")[5].value)-parseFloat(document.all("je")[6].value));
  document.all("je")[9].value=parseFloat(document.all("je")[7].value)-parseFloat(document.all("je")[8].value);
  document.all("je")[11].value=parseFloat(document.all("je")[9].value)-parseFloat(document.all("je")[10].value);

}/*
if(document.all("je")[8].value < 0)
{
  document.all("je")[9].value=0;
  document.all("je")[11].value=0;
}
if(document.all("je")[9].value < 0)
{
  document.all("je")[9].value=0;
  document.all("je")[11].value=0;


}
if(document.all("je")[11].value < 0)
{

  document.all("je")[11].value=0;
}*/
}
}
}
function doQuery()
{
        var jsjdm;
	jsjdm = document.forms[0].jsjdm.value;
	if(jsjdm==null || jsjdm==""){
        alert("��������벻�����ǿգ�");
        //document.forms[0].jsjdm.focus();
	return false;
        }else{
	doSubmitForm("Query");
        return false;
        }
}
function checkVisitCount()
{
    var val = parseInt(VisitCounter.value);
    if (val > 0)
    {
        window.location = "shenbao/page_guoqi.jsp";
        return true;
    }
    val = val + 1;
    VisitCounter.value = val;
    return true;
}

function jsjdmQuery(){
       if(event.keyCode==13) event.keyCode = 9;
}
function doSave()
{  var jsjdm;
   var nsrmc;
	jsjdm = document.forms[0].jsjdm.value;
        nsrmc = document.forms[0].nsrmc.value;
	if((jsjdm==null || jsjdm=="")||(nsrmc==null || nsrmc=="")){
        alert("ҳ����Ϣ��д����ȷ��");
        return false;
        //document.forms[0].jsjdm.focus();
        }
        else{
	doSubmitForm("Save");
        return false;
        }
}




function doDelete()
{ var jsjdm;
	jsjdm = document.forms[0].jsjdm.value;
	var nsrmc;
	jsjdm = document.forms[0].jsjdm.value;
        nsrmc = document.forms[0].nsrmc.value;
	if((jsjdm==null || jsjdm=="")||(nsrmc==null || nsrmc=="")){
        alert("ҳ����Ϣ��д����ȷ��");
        //document.forms[0].jsjdm.focus();
        return false;
        }else{
	doSubmitForm("Delete");
        return false;
        }
}
</script>

		<html:form action="/webapp/smsb/gtgshsdsAction.do" method="POST">
		<html:hidden property="actionType"/>
		<html:hidden property="swjgzzjgdm"/>
		<html:hidden property="fsdm"/>
		<html:hidden property="cjrq"/>
		<html:hidden property="lrrq"/>
		<html:hidden property="lrr"/>
		<html:hidden property="scjyrq"/>
                <html:hidden property="nd"/>
				<html:hidden property="iszhsb"/>
				<html:hidden property="qxdm"/>


      <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0" bordercolor="#496C8B" bgcolor="#FFFFFF" class="table-98" onkeydown="jsjdmQuery()">
        <tr><td class="1-td1" colspan="2">�������ո��幤�̻�����˰��ȣ��·ݣ��걨��</td></tr>
        <tr bgcolor="#FFFFFF">

          <td width="80%" valign="top" class="1-td2">
  <br>
                <table cellspacing="0" class="table-99">
                    <tr class="black9">
                      <td align="center" nowrap> <div align="right">��λ������� Ԫ</div></td>
                    </tr>
                  </table>
                  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
                    <tr class="black9">
                           <td nowrap class="2-td2-t-left" width="15%"><div align="right">�������</div></td>

                <td nowrap class="2-td2-t-left"> <html:text property="sbrq" maxlength="8" size="10" onchange="chkSbrq(this)"/>
                      <td nowrap class="2-td2-t-left" width="15%"><div align="right">˰��������ȣ��·ݣ�</div></td>
                      <td nowrap class="2-td2-t-center">
                  <!--<html:text property="skssksrq" maxlength="8" size="10" onchange="compareDate('skssksrq','skssjsrq',0,this)"/>
                  ��
                  <html:text property="skssjsrq" size="10" maxlength="8" onchange="compareDate('skssksrq','skssjsrq',0,this)"/>-->
                  <html:text property="skssksrq" maxlength="8" size="10" onchange="changeDates(this,0)"/>
                  ��
                  <html:text property="skssjsrq" size="10" maxlength="8" onchange="changeDates(this,1)"/>
                  </td></tr>

                  </table>
                  <br>
                  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
              <tr>
                <td colspan="2" nowrap class="2-td2-t-left">��������� </td>
                <td colspan="3" nowrap class="2-td2-t-left"> <html:text property="jsjdm" size="8" maxlength="8" onchange="doQuery();return false;" onKeyDown="jsjdmQuery()"/>
                </td>
                <td colspan="2" nowrap class="2-td2-t-left">���� </td>
                <td colspan="3" nowrap class="2-td2-t-center">&nbsp;<ttsoft:write name="gtgshsdsForm" property="nsrmc"/><html:hidden property="nsrmc"/></td></tr>
<tr>
                      <td nowrap class="2-td2-left" width="15%" colspan="2" > <div align="right">��˰�����֤������</div></td>

                <td nowrap class="2-td2-left" colspan="3" >&nbsp;<ttsoft:dmmc property="zjlxdm" codekey="ZJLX"/><html:hidden property="zjlxdm"/> </td>
                      <td nowrap class="2-td2-left" width="15%" colspan="2" > <div align="right">��˰�����֤������</div></td>

                <td nowrap class="2-td2-center" colspan="3" > &nbsp;<ttsoft:write name="gtgshsdsForm" property="zjhm"/><html:hidden property="zjhm"/> </td>
                    </tr>
					<tr>
                <td colspan="2" nowrap class="2-td2-left">ҵ������</td>
                <td colspan="3" nowrap class="2-td2-left">&nbsp;<ttsoft:write name="gtgshsdsForm" property="xm"/><html:hidden property="xm"/> </td>
                <td colspan="2" nowrap class="2-td2-left">��ַ</td>
                <td colspan="3" nowrap class="2-td2-center">&nbsp;<ttsoft:write name="gtgshsdsForm" property="dz"/><html:hidden property="dz"/></td></tr>
              <tr>
                <td class="2-td2-left">ҵ�� </td>
                <td class="2-td2-left">&nbsp;<ttsoft:dmmc property="gjbzhydm" codekey="ZBZL_GJBZHY"/><html:hidden property="gjbzhydm"/> </td>
                <td class="2-td2-left">��ʼ������Ӫ���� </td>
                <td class="2-td2-left">&nbsp;<ttsoft:write name="gtgshsdsForm" property="scjyrq"/><html:hidden property="scjyrq"/></td>
                <td class="2-td2-left">�����ʺ� </td>
                <td class="2-td2-left">&nbsp;<ttsoft:write name="gtgshsdsForm" property="zh"/><html:hidden property="zh"/><html:hidden property="yhdm"/> </td>
                <td  class="2-td2-left">�ʱ� </td>
                <td  class="2-td2-left">&nbsp;<ttsoft:write name="gtgshsdsForm" property="qyyb"/><html:hidden property="qyyb"/> </td>
                <td  class="2-td2-left">�绰 </td>
                <td  class="2-td2-center">&nbsp;<ttsoft:write name="gtgshsdsForm" property="lxdh"/><html:hidden property="lxdh"/> </td></tr>
              <tr>
                <td colspan="5" nowrap class="2-td2-left">��Ŀ</td>
                <td colspan="5" nowrap class="2-td2-left">���</td></tr>
							<bean:define id="items" name="gtgshsdsForm" property="dataList"/>
							<logic:iterate id="item" name="items" type="java.util.Map">
							<%
								String hc = (String)item.get("hc");
								if(hc.equals("1")){
							%>
              <tr>
                <td rowspan="5" nowrap class="2-td2-left">Ӧ��˰���ö�ļ��� </td>
                <td colspan="4" nowrap class="2-td2-left"><div align="left"><ttsoft:write name="item" property="xmmc"/><input name="hc" type="hidden" value="<ttsoft:write name="item" property="hc"/>" id="hc_<%=hc%>"><input name="xmmc" type="hidden" value="<ttsoft:write name="item" property="xmmc"/>" id="xmmc_<%=hc%>">
                  </div></td>
                <td colspan="5" nowrap class="2-td2-center"> <input name="je" type="text" value="<ttsoft:write name="item" property="je"/>" id="je_<%=hc%>" size="10" onchange="jisuan(this)" class="inputalignright">
                </td>
							</tr>
							<%
								}else if(hc.equals("6")){
              %>
              <tr>
                <td rowspan="7" nowrap class="2-td2-left">Ӧ�ɸ�������˰��ļ��� </td>
                <td colspan="4" nowrap class="2-td2-left"><div align="left"><ttsoft:write name="item" property="xmmc"/><input name="hc" type="hidden" value="<ttsoft:write name="item" property="hc"/>" id="hc_<%=hc%>"><input name="xmmc" type="hidden" value="<ttsoft:write name="item" property="xmmc"/>" id="xmmc_<%=hc%>"></div></td>
                <td colspan="5" nowrap class="2-td2-center"><input name="je" type="text" value="<ttsoft:write name="item" property="je"/>" id="je_<%=hc%>" size="10" onchange="jisuan(this)" class="inbright" Readonly tabIndex="-1"></td>
							</tr>
							<%
								}else{
              %>
              <tr>
                <td colspan="4" nowrap class="2-td2-left"><div align="left"><ttsoft:write name="item" property="xmmc"/><input name="hc" type="hidden" value="<ttsoft:write name="item" property="hc"/>" id="hc_<%=hc%>"><input name="xmmc" type="hidden" value="<ttsoft:write name="item" property="xmmc"/>" id="xmmc_<%=hc%>" >
                  </div></td>
                <td colspan="5" nowrap class="2-td2-center"><input name="je" type="text" value="<ttsoft:write name="item" property="je"/>" id="je_<%=hc%>" size="10" onchange="jisuan(this)"
                 <%if(hc.equals("7")||hc.equals("5")||hc.equals("8")||hc.equals("10")||hc.equals("12")) {out.print(" class=\"inbright\" ");   out.print(" readonly==\"Readonly\" tabIndex=\"-1\"");}else{out.print(" class=\"inputalignright\" "); }%> ></td>
							</tr>
              <%
								}
							%>
							</logic:iterate>
            </table>
		   <table>
			&nbsp;&nbsp;&nbsp;&nbsp;
                   </table>
                    <input type="image" accesskey="q" tabIndex="-1" onClick="doQuery(); return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg ',1)" onMouseOut="MM_swapImgRestore()" alt="��ѯ" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun" style="cursor:hand">
                   &nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="u" tabIndex="-1"  onClick="cleannum();return false; " onMouseOver="MM_swapImage('qingchu','',' <%=static_contextpath%>images/qc-u2.jpg ',1)" onMouseOut="MM_swapImgRestore()" src=" <%=static_contextpath%>images/qc-u1.jpg " alt="���"  width="79" height="22" id="qingchu" style="cursor:hand" >
                   &nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="s" tabIndex="-1" onClick="doSave();return false;" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/bc-s1.jpg" alt="����"  width="79" height="22" id="baocun" style="cursor:hand" >
                   &nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="x" tabIndex="-1"  onClick="doDelete();return false;"onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="ɾ��" src="<%=static_contextpath%>images/qbsc-x1.jpg " width="79" height="22" id="shanchu" style="cursor:hand">
                   &nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="c" tabIndex="-1" onClick="tuichu();return false;"onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor:hand">
                  <br> <br> </td></tr>
          </table></td>
        </tr>
      </table>
</html:form>
  <%@ include file="include/footer.jsp" %>
  </td>
</tr>

<script language="JavaScript" type="text/JavaScript">



function doSubmit(method){
	document.all.actionType.value=method;
	document.all.gtgshsdsForm.submit();
	return false;
}

function newitem(szdm,szmc,szsmdm,szsmmc,zqrqq,zqrqz,skssksrq,skssjsrq,kssl,sl,sskcs,jfye,aslj,jsjs)
{



      // ��������
      personTaxForm.operateType.value="create";
      // ˰��˰Ŀ����
      personTaxForm.szsmdm.value=szsmdm;
      // ˰��˰Ŀ����
      personTaxForm.szsmmc.value=szsmmc;
      // ����������
      personTaxForm.zqrqq.value=zqrqq;
      // ��������ֹ
      personTaxForm.zqrqz.value=zqrqz;
      // ˰��������ʼ����
      personTaxForm.skssksrq.value=skssksrq;
      // ˰��������������
      personTaxForm.skssjsrq.value=skssjsrq;
      // ����۳���
      personTaxForm.sskcs.value=sskcs;
      // ˰��
      personTaxForm.sl.value=sl;
      // �����ö�
      if (personTaxForm.jfye.value == "")
      {
          personTaxForm.jfye.value=jfye;
      }
      // �������Ʊ��
      personTaxForm.aslj.value=aslj;
      // ��˰����
      personTaxForm.jsjs.value=jsjs;

      /***�ύ***/
      personTaxForm.submit();

        /**@todo:����Ȳ�Ҫ�ˣ�Ҫ��ʱ���ټ�**/
        // ˰�ִ���
        // personTaxForm.szdm.value=szdm;
        // ˰������
        // personTaxForm.szmc.value=szmc;

}


function modifyitem(gjdm, szsmdm)
{
    if(confirm("�Ƿ��޸ĸ�����¼��"))
    {
        personTaxForm.operateType.value="modify";
        personTaxForm.szsmdm.value = szsmdm;
        personTaxForm.gjdm.value = gjdm;
        personTaxForm.submit();
    }
    return false;
}


function delitem(gjdm, szsmdm)
{
    if(confirm("�Ƿ�ɾ��������¼��"))
    {
        personTaxForm.operateType.value = "delete";
        personTaxForm.gjdm.value = gjdm;
        personTaxForm.szsmdm.value = szsmdm;
        personTaxForm.submit();
    }
    return false;
}


function cancelitem(szsmdm)
{
    if(confirm("�Ƿ�ȡ���޸ĸ�����¼��"))
    {
        personTaxForm.operateType.value="delete";
        personTaxForm.szsmdm.value = szsmdm;
        personTaxForm.submit();
    }
    return false;
}


function updateitem(szsmdm)
{
    if(verify())
    {
        // ��������
        personTaxForm.operateType.value="update";
        // ˰��˰Ŀ����
        personTaxForm.szsmdm.value = szsmdm;
        // ���Ҵ���
        personTaxForm.gjdm.value = personTaxForm.gjdm_input.value;
        // ����
        personTaxForm.rs.value = personTaxForm.rs_input.value;
        // �����
        personTaxForm.sre.value = personTaxForm.sre_input.value;
        // �ѿ۽�˰��
        personTaxForm.ykjse.value = personTaxForm.ykjse_input.value;
        /**�ύ**/
        personTaxForm.submit();
    }
    return false;
}


function submititem()
{

  if(confirm("�Ƿ��ύ�걨�����"))
  {
    personTaxForm.operateType.value="submit";
    return true;
  }

  return false;
}


function showMX()
{

        personTaxForm.action="personTaxList.do";
        return true;

    return false;
}


function quit()
{

      /**personTaxForm.action="init.do";**/
        personTaxForm.operateType.value="quit";
        return true;

    return false;
}


function isNumberFloat(inputString)
{
    str = parseFloat(inputString);
    return !isNaN(str) && (str == inputString);
}


function selectHZ()
{
    personTaxForm.operateType.value="selectHZ";

    return true;
}


function verify()
{
    // �����
    if (!isNumber(personTaxForm.sre_input.value))
    {
        window.alert("��������������!");
        personTaxForm.sre_input.focus();
        return false;
    }
    sre1 = stringToFloat(personTaxForm.sre_input.value);
    if (sre1 <= 0)
    {
        window.alert("�����������0");
        personTaxForm.sre_input.focus();
        return false;
    }
    if(personTaxForm.szsmdm_ipnut.value=="05033")
    {
        if (sre1 > (eval(personTaxForm.rs_input.value))*1000)
        {
            window.alert("ÿ������������С�ڵ���1000");
            personTaxForm.szsmdm_ipnut.focus();
            return false;
        }
    }

    // �ѿ۽�˰��
    if (!isNumber(personTaxForm.ykjse_input.value))
    {
        if (personTaxForm.ykjse_input.value == "")
        {
            personTaxForm.ykjse_input.value = "0.00";
        }
        else
        {
            window.alert("�ѿ۽�˰�����������!");
            personTaxForm.ykjse_input.focus();
            return false;
        }
    }
    ykjse1 = stringToFloat(personTaxForm.ykjse_input.value);
    if (ykjse1 < 0)
    {
        window.alert("�ѿ۽�˰����벻С��0!");
        personTaxForm.ykjse_input.focus();
        return false;
    }
    // ����
    if (!isInt(personTaxForm.rs_input.value))
    {
        window.alert("��������������!");
        personTaxForm.rs_input.focus();
        return false;
    }
    rs1 = stringToInt(personTaxForm.rs_input.value);
    if (rs1 <= 0)
    {
        window.alert("�����������0!");
        personTaxForm.rs_input.focus();
        return false;
    }

    // ��������ѿ۽�˰��������һ�����
    if(parseInt(personTaxForm.sre_input.value) >= 1000000000000 || parseInt(personTaxForm.ykjse_input.value) >= 1000000000000)
    {
        alert("������������������12λ����!");
        return false;
    }

    // ��֤�Ƿ��ظ��걨ͬһ˰Ŀ


    return true;
}


function computeitem()
{
    // Ӧ��˰���ö�: ( ����� > �����ö� ) ?  (����� �� �����ö�) : 0
    if (!isNumber(personTaxForm.sre_input.value))
    {
        return false;
    }

    // ȡ�ù�������
    gjdm = personTaxForm.gjdm_input.value;

    // ȡ��ÿһ���˵ļ����ö�
    var jfyePer;
    if ((gjdm != null) && (gjdm != "") && (gjdm != "CHN"))
    {
        jfyePer = stringToFloat(personTaxForm.jfyePerOther.value);
    }
    else
    {
        jfyePer = stringToFloat(personTaxForm.jfyePer.value);
    }
    if (!isInt(personTaxForm.rs_input.value))
    {
        personTaxForm.jfye_result.value = jfyePer;
        return false;
    }

    // ȡ������
    var rsValue = stringToInt(personTaxForm.rs_input.value);
    if (rsValue <= 0)
    {
        personTaxForm.jfye_result.value = jfyePer;
        return false;
    }
    if (jfyePer < 1)
    {
        // ������
        jfye1 = stringToInt(personTaxForm.sre_input.value) * jfyePer;
    }
    else
    {
        jfye1 = rsValue * jfyePer;
    }
    personTaxForm.jfye_result.value = handleFloat(jfye1);
    sre1 = stringToFloat(personTaxForm.sre_input.value);
    ynssde1 = (sre1 > jfye1) ? (sre1 - jfye1) : 0.0;
    personTaxForm.ynssde_result.value = handleFloat(ynssde1);

    // ȡ��ÿ���˵�����۳���
    var sskcs1;
    if (rsValue <= 0)
    {
        sskcs1 = stringToFloat(personTaxForm.sskcsPer.value);
    }
    else
    {
        sskcs1 = stringToFloat(personTaxForm.sskcsPer.value) * rsValue;
        personTaxForm.sskcs_result.value = handleFloat(sskcs1);
    }

    // Ӧ��˰��:    Ӧ��˰���ö� * ˰�� �� ����۳���
    ynse1 = ynssde1 * (personTaxForm.compute_sl.value) - sskcs1;
    ynse1 = (ynse1 >= 0) ? ynse1 : 0.00;
    personTaxForm.ynse_result.value = handleFloat(ynse1);

    // ����Ӧ��˰��: Ӧ��˰�� �� ����˰�� �� �ѿ۽�˰��
    if (!isNumber(personTaxForm.ykjse_input.value))
    {
        ykjse1 = 0.0;
    }
    else
    {
        ykjse1 = stringToFloat(personTaxForm.ykjse_input.value);
    }
    jmse1 = 0.0;
    bqyjse1 = ynse1 - jmse1 - ykjse1;
    bqyjse1 = (bqyjse1>0.0) ? bqyjse1 : 0.0;
    personTaxForm.bqyjse_result.value = handleFloat(bqyjse1);

    // �༭����Ӧ��˰�����: �༭ǰ����Ӧ��˰����� �� �༭ǰ�༭�б���Ӧ��˰�� �� �༭��༭�б���Ӧ��˰��
    oldBqynsehz = stringToFloat(personTaxForm.bqynsehzOldValue.value);
    oldEditValue = stringToFloat(personTaxForm.editOldValue.value);
    bqynsehz_1 = oldBqynsehz - oldEditValue + bqyjse1;
    personTaxForm.bqynsehz_result.value = handleFloat(bqynsehz_1);

    return true;
}
// �ַ�����������
function stringToFloat(inputString)
{
  return parseFloat(inputString);
}

// �ַ���������
function stringToInt(inputString)
{
  return parseInt(inputString);
}

// �Ƿ�������
function isInt(inputString)
{
    str = parseInt(inputString);
    return !isNaN(str) && (str == inputString);
}

// �Ƿ�����
function isNumber(inputString)
{
    str = parseFloat(inputString);
    return !isNaN(str) && (str == inputString);
}

// �������뺯��
function handleFloat(inputNum)
{
  inputString=inputNum.toString();
  for(i=1; i<=inputString.length; i++)
  {
    if(inputString.substring(i-1,i)==".")
    {
      if(inputString.length > i+2)
      {
        return ((parseFloat(inputString)+0.0050001).toString()).substring(0,i+2);
      }
      break;
    }
  }
  return inputString;
}
// ���ǰ����
function ClearZero(object)
{
    InString = object.value;
    len = InString.length;
    if (len < 2)
    {
        return true;
    }

    index0 = InString.indexOf('0');
    if (index0 == 0)
    {
        indexDot = InString.indexOf('.');
        if ((indexDot == -1) || (indexDot > 1))
        {
            object.value = InString.substring(1,2);
            return false;
        }
    }

    return true;
}

function fnReturn()
{
	location.href="PG3_SBZS_023.htm"
}

function chkSbrq(obj){
	var val = obj.value;
	var month = "";
        if(isDate(obj,"notnull")){
		month = val.substring(4,6);
		if(month=="01"){
			getStartEndDate(obj,document.forms[0].skssksrq,document.forms[0].skssjsrq,1);
		}else{
			getStartEndDate(obj,document.forms[0].skssksrq,document.forms[0].skssjsrq,2);
		}
	}
}
function changeDates(obj,kind){
	var val = document.forms[0].sbrq.value;
	var month = "";
        if(isDate(obj,"notnull")){
		month = val.substring(4,6);
		if(month=="01"){
                      if(kind==0)
		      {	compareSkssrqDate('sbrq','skssksrq','skssjsrq',0,obj,0,0);}
                      else
                      { compareSkssrqDate('sbrq','skssksrq','skssjsrq',0,obj,0,1);}
		}else{
			 if(kind==0)
		      {	compareSkssrqDate('sbrq','skssksrq','skssjsrq',0,obj,2,0);}
                      else
                      { compareSkssrqDate('sbrq','skssksrq','skssjsrq',0,obj,2,1);}
		}
	}
}

</script>
<script language="javascript"> 
/****�������˰��Ϊ��������������ʾ��ʾ��Ϣ****/
/****20050817 Huxiaofeng****/
var nsrzt = <ttsoft:write name="gtgshsdsForm" property="nsrzt" filter="false"/>;
checkNsrzt();
/**************end *******/
</script>
</body>
</html:html>