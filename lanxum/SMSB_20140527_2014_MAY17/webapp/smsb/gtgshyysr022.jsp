<%@ page contentType="text/html; charset=gb2312" language="java"   errorPage="" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
            <%
              int listLen = 0;
           %>
<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>���幤�̻�Ӫҵ�����걨��</title>
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

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="showhj()">
<%@ include file="include/header.jsp" %>
<script language="JavaScript">
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

function computeSameSumGT(addFrmItem,resultFrmItem,tableId){

//alert("addFrmItem :"+addFrmItem +"resultFrmItem :"+resultFrmItem+"tableId :"+tableId )
    //������
	var oTab=document;
	if(tableId!=null)
		{
		oTab=eval("document.all('"+tableId+"')");
		}

	//������ı��Ԫ����
	var addItem = eval("oTab.all('"+addFrmItem+"')");
	//������ı��Ԫ������
	var length = getLength(addItem);
	//���
	var result_sum = 0;
    if (length != null)
    {
		if(length == -1){
			result_sum = parseFloatPro(addItem.value);
		}else{
			if(length == 1)
			{
				result_sum = parseFloatPro(addItem[0].value);
			}
			else
			{
				// �ж���
			   for (var i=addItem.length-1; i>=0; i--)
			   {
			        if(i==6) continue;
					result_sum += parseFloatPro(addItem[i].value);
			   }
			}
		}

    }
	//��ֵ
    eval("oTab.all('"+resultFrmItem+"')").value=formatNumber(result_sum);
		if(oTab.all.xthjdx){
			oTab.all.xthjdx.innerText=getChineseMoney(formatNumber(result_sum));
		}
}

function doSave(){

 var jsjdm;
   var nsrmc;
	jsjdm = document.forms[0].jsjdm.value;
        nsrmc = document.forms[0].nsrmc.value;
	if((jsjdm==null || jsjdm=="")||(nsrmc==null || nsrmc=="")){
        alert("ҳ����Ϣ��д����ȷ��");
        return false;
        //document.forms[0].jsjdm.focus();
        }
        else{


var i;
var dqkjfpje_5_valye= parseFloat(document.all("dqkjfpje")[5].value);
var dqwkjfpje_5_value=parseFloat(document.all("dqwkjfpje")[5].value);
var dqyysrjehj_5_value=parseFloat(document.all("dqyysrjehj")[5].value);
var jzyysr_5_value=parseFloat(document.all("jzyysr")[5].value);

var dqkjfpje_6_valye= parseFloat(document.all("dqkjfpje")[6].value);
var dqwkjfpje_6_value=parseFloat(document.all("dqwkjfpje")[6].value);
var dqyysrjehj_6_value=parseFloat(document.all("dqyysrjehj")[6].value);
var jzyysr_6_value=parseFloat(document.all("jzyysr")[6].value);
var ok=1;
for(i=0;i<12;i++)
{
  var oY=document.all("jzyysr")[i];
  var oyvalue=parseFloat(oY.value);
  var oN=document.all("dqyysrjehj")[i];
  var onvalue=parseFloat(oN.value);
  if((oyvalue>onvalue)||(isNaN(onvalue)&&isNumber(oyvalue)))
	{
	  ok=0;
	  alert("��"+(i+1)+"�еĵ���Ӫҵ�������еļ���Ӫҵ˰���벻�ܴ��ڵ���Ӫҵ������ϼ�");
	}
}
if((dqkjfpje_6_valye>dqkjfpje_5_valye)||(isNaN(dqkjfpje_5_valye)&&isNumber(dqkjfpje_6_valye)))
	{
	  ok=0;
       alert("���ڿ��߷�Ʊ����еķ���ҵ����ʳ���벻�ܴ��ڷ����ܶ�");
	}
if((dqwkjfpje_6_value>dqwkjfpje_5_value)||(isNaN(dqwkjfpje_5_value)&&isNumber(dqwkjfpje_6_value)))
	{
	  ok=0;
       alert("����δ���߷�Ʊ����еķ���ҵ����ʳ���벻�ܴ��ڷ����ܶ�");
	}
if((dqyysrjehj_6_value>dqyysrjehj_5_value)||(isNaN(dqyysrjehj_5_value)&&isNumber(dqyysrjehj_6_value)))
	{
	  ok=0;
       alert("����Ӫҵ�������еĺϼ��еķ���ҵ����ʳ���벻�ܴ��ڷ����ܶ�");
	}
if((jzyysr_6_value>jzyysr_5_value)||(isNaN(jzyysr_5_value)&&isNumber(jzyysr_6_value)))
	{
	  ok=0;
       alert("����Ӫҵ�������е����м���Ӫҵ˰�����еķ���ҵ����ʳ���벻�ܴ��ڷ����ܶ�");
	}
 if(ok==1)
	{    doSubmitForm("Save");
         return false;
  }
        }
}
function showhj(){
	 //if(document.forms[0].actionType.value=="Show")
document.forms[0].jsjdm.focus();
  if(document.forms[0].actionType.value=="Query")
  {   computeSameSumGT("jzyysr","jzyysrhj");
      computeSameSumGT("dqwkjfpje","dqwkjfpjehj");
      computeSameSumGT("dqkjfpje","dqkjfpjehj");
      computeSameSumGT("dqyysrjehj","dqyysrjehjhj");
  }
}


function doQuery()
{
        var jsjdm;
	jsjdm = document.forms[0].jsjdm.value;
	if(jsjdm==null || jsjdm==""){
        alert("��������벻�����ǿգ�");
        return false;
        //document.forms[0].jsjdm.focus();
        }else{
	doSubmitForm("Query");

	return false;
        }
}

function jsjdmQuery(){
    if(event.keyCode==13) event.keyCode = 9;
}

function dqkjfpjefujs(a){
if(isNum(a,null,null,1,15,2)){
  var mid = a.id;
  var midvalues=parseFloat(a.value);

  var oY=document.all("dqkjfpje")[6];
  var oyvalue=parseFloat(oY.value);

   if(midvalues < oyvalue)
  {
   alert("������ʳ���벻�ܴ��ڷ����ܶ�");

  }

   computeSameSumGT("dqkjfpje","dqkjfpjehj");
}
}
function dqwkjfpjefujs(a){
if(isNum(a,null,null,1,15,2)){
  var mid = a.id;
  var midvalues=parseFloat(a.value);

  var oY=document.all("dqwkjfpje")[6];
  var oyvalue=parseFloat(oY.value);

   if(midvalues < oyvalue)
  {
   alert("������ʳ���벻�ܴ��ڷ����ܶ�");

  }

   computeSameSumGT("dqwkjfpje","dqwkjfpjehj");
}
}
function dqyysrjehjfujs(a){
if(isNum(a,null,null,1,15,2)){
  var mid = a.id;
  var midvalues=parseFloat(a.value);

  var oY=document.all("dqyysrjehj")[6];
  var oyvalue=parseFloat(oY.value);

   var oYye=document.all("jzyysr")[5];
   var oyvalueye=parseFloat(oYye.value);

   if(midvalues < oyvalue)
  {
   alert("������ʳ���벻�ܴ��ڷ����ܶ�");

  }
     if(midvalues < oyvalueye)
  {
  alert("����Ӫҵ�������еļ���Ӫҵ˰���벻�ܴ��ڵ���Ӫҵ������ϼ�");
  }
   computeSameSumGT("dqyysrjehj","dqyysrjehjhj");
}
}
function jzyysrfujs(a){
if(isNum(a,null,null,1,15,2)){
  var mid = a.id;
  var midvalues=parseFloat(a.value);


  var oYye=document.all("jzyysr")[6];
  var oyvalueye=parseFloat(oYye.value);

 var oY=document.all("dqyysrjehj")[5];
  var oyvalue=parseFloat(oY.value);

   if(midvalues < oyvalueye)
  {
   alert("������ʳ���벻�ܴ��ڷ����ܶ�");

  }

   if(midvalues > oyvalue)
  {
  alert("����Ӫҵ�������еļ���Ӫҵ˰���벻�ܴ��ڵ���Ӫҵ������ϼ�");
   a.value=0;
  }

   computeSameSumGT("jzyysr","jzyysrhj");
}
}

function jzyysrjs(a){
if(isNum(a,null,null,1,15,2)){
  var mid = a.id;
  var midvalues=parseFloat(a.value);
  var i=mid.substring(mid.indexOf("_")+1,mid.length);
  var othId = "dqyysrjehj_"+i;
  var oY=document.all("dqyysrjehj")[i];
  var oyvalue=parseFloat(oY.value);

  if(midvalues > oyvalue)
  {
  alert("����Ӫҵ�������еļ���Ӫҵ˰���벻�ܴ��ڵ���Ӫҵ������ϼ�");
   //a.value=0;
  }

   computeSameSumGT("jzyysr","jzyysrhj");
}
}
</script>
  <script language="JavaScript" type="text/JavaScript">


function dqkjfpjejs(a){
if(isNum(a,0,null,1,15,2)){

  computeSameSumGT("dqkjfpje","dqkjfpjehj");

}
}
function dqwkjfpjejs(a){
if(isNum(a,0,null,1,15,2)){

  computeSameSumGT("dqwkjfpje","dqwkjfpjehj");

}
}
function dqyysrjehjjs(a){
if(isNum(a,0,null,1,15,2)){
  var mid = a.id;
  var midvalues=parseFloat(a.value);

  var val;
  var oRow = getObjRow(a);
  val = oRow.all("jzyysr").value;

  if(midvalues<val)
  {alert("����Ӫҵ�������еļ���Ӫҵ˰���벻�ܴ��ڵ���Ӫҵ������ϼ�"); }
  computeSameSumGT("dqyysrjehj","dqyysrjehjhj");

}
}
function dqkjfpjeysjs(a){
if(isNum(a,null,null,1,15,2)){
  var mid = a.id;
  var midvalues=parseFloat(a.value);

  var oY=document.all("dqkjfpje")[5];
  var oyvalue=parseFloat(oY.value);

   if(midvalues > oyvalue)
  {
   alert("������ʳ���벻�ܴ��ڷ����ܶ�");
   //a.value=0;
  }

   computeSameSumGT("dqkjfpje","dqkjfpjehj");
}
}
function dqwkjfpjeysjs(a){
if(isNum(a,null,null,1,15,2)){
  var mid = a.id;
  var midvalues=parseFloat(a.value);

  var oY=document.all("dqwkjfpje")[5];
  var oyvalue=parseFloat(oY.value);

   if(midvalues > oyvalue)
  {
   alert("������ʳ���벻�ܴ��ڷ����ܶ�");
   //a.value=0;
  }

   computeSameSumGT("dqwkjfpje","dqwkjfpjehj");
}
}
function dqyysrjehjysjs(a){
if(isNum(a,null,null,1,15,2)){
  var mid = a.id;
  var midvalues=parseFloat(a.value);

  var oY=document.all("dqyysrjehj")[5];
  var oyvalue=parseFloat(oY.value);

  var oYye=document.all("jzyysr")[6];
  var oyvalueye=parseFloat(oYye.value);

  if(midvalues < oyvalueye)
  {
   alert("����Ӫҵ�������еļ���Ӫҵ˰���벻�ܴ��ڵ���Ӫҵ������ϼ�");
   //a.value=0;
  }

   if(midvalues > oyvalue)
  {
   alert("������ʳ���벻�ܴ��ڷ����ܶ�");
   //a.value=0;
  }

   computeSameSumGT("dqyysrjehj","dqyysrjehjhj");
}
}
function jzyysrysjs(a){
if(isNum(a,null,null,1,15,2)){
  var mid = a.id;
  var midvalues=parseFloat(a.value);

  var oY=document.all("dqyysrjehj")[6];
  var oyvalue=parseFloat(oY.value);

  var oYye=document.all("jzyysr")[5];
  var oyvalueye=parseFloat(oYye.value);

   if(midvalues > oyvalue)
  {
   alert("����Ӫҵ�������еļ���Ӫҵ˰���벻�ܴ��ڵ���Ӫҵ������ϼ�");
   //a.value=0;
  }
    if(midvalues > oyvalueye)
  {
   alert("������ʳ���벻�ܴ��ڷ����ܶ�");
   //a.value=0;
  }
   computeSameSumGT("jzyysr","jzyysrhj");
}
}
function ql(){

 var i;
  if(confirm("�Ƿ������ԭҳ��������Ϣ")){
 for(i=0;i<listLens;i++)
{

 document.all("jzyysr")[i].value=0;
 document.all("dqyysrjehj")[i].value=0;
 document.all("dqkjfpje")[i].value=0;
 document.all("dqwkjfpje")[i].value=0;

}
computeSameSumGT("jzyysr","jzyysrhj");
computeSameSumGT("dqwkjfpje","dqwkjfpjehj");
computeSameSumGT("dqkjfpje","dqkjfpjehj");
computeSameSumGT("dqyysrjehj","dqyysrjehjhj");
}
}


</script>
<input type="hidden" name="VisitCounter" value="0">

<html:form action="/webapp/smsb/gtgshyysrAction.do" method="POST">
<html:hidden property="actionType"/>
<html:hidden property="swjgzzjgdm"/>
<html:hidden property="fsdm"/>
<html:hidden property="cjrq"/>
<html:hidden property="lrrdm"/>
<html:hidden property="lrrq"/>
<html:hidden property="nd"/>
<html:hidden property="iszhsb"/>
<html:hidden property="qxdm"/>
<html:hidden property="tempSbrq"/>

      <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0" bordercolor="#496C8B" bgcolor="#FFFFFF" class="table-98" onkeydown="jsjdmQuery()">
        <tr>
          <td class="1-td1" colspan="2">���幤�̻�Ӫҵ�����걨��</td>
        </tr>
        <tr bgcolor="#FFFFFF">

          <td width="80%" valign="top" class="1-td2">
  <br>
                <table cellspacing="0" class="table-99">
                    <tr class="black9">
                 <td nowrap> <div align="right">�걨����&nbsp;&nbsp;</div></td>
                <td nowrap><div align="left">&nbsp;
                    <html:text property="sbrq" size="10" maxlength="8" onchange="getStartEndDate(this,document.forms[0].skssksrq,document.forms[0].skssjsrq,2)"/>
                  </div></td>
                <td nowrap>˰���������ڣ�
                
               <html:text property="skssksrq" size="11" maxlength="8" onchange="compareSkssrqDate('sbrq','skssksrq','skssjsrq',0,this,2,0)"/>��
               <html:text property="skssjsrq" size="11" maxlength="8" onchange="compareSkssrqDate('sbrq','skssksrq','skssjsrq',0,this,2,1)"/>
                 </td>
                      <td align="center" nowrap> <div align="right">��λ�������Ԫ</div></td>
                    </tr>
                  </table>

            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
              <tr class="black9">
                <td nowrap class="2-td2-t-left" ><div align="right">���������&nbsp;&nbsp;</div></td>
                <td nowrap class="2-td2-t-left"><div align="left"> &nbsp;
                    <html:text property="jsjdm" size="8" maxlength="8" onchange="doQuery();return false;" />
                  </div>
                <td nowrap class="2-td2-t-left" ><div align="center">˰��Ǽ�֤��&nbsp;&nbsp;</div></td>
                <td nowrap class="2-td2-t-center"><div align="left">&nbsp;&nbsp;<ttsoft:write name="gtgshyysrForm" property="swdjzh"/><html:hidden property="swdjzh"/>
                  </div></tr>
              <tr>
                <td nowrap class="2-td2-left" > <div align="right">��˰������&nbsp;&nbsp;</div></td>
                <td nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;<ttsoft:write name="gtgshyysrForm" property="nsrmc"/><html:hidden property="nsrmc"/>
                  </div></td>

                   <td colspan="3" nowrap class="2-td2-center"><div align="left">&nbsp;

                  </div></td>
              </tr>
            </table>
                  <br>
                  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
              <tr>
                <td colspan="2" rowspan="2" nowrap class="2-td1-left">ƷĿ����</td>
                <td  rowspan="2" nowrap class="2-td1-left">���ڿ��߷�Ʊ���
                </td>
                <td  rowspan="2" nowrap class="2-td1-left">����δ���߷�Ʊ���
                </td>
                <td colspan="2" nowrap class="2-td1-center">����Ӫҵ������</td>
              </tr>
              <tr>
                <td nowrap class="2-td2-left">�ϼ�</td>
                <td  nowrap class="2-td2-center">���м���Ӫҵ˰����</td>
              </tr>
            <bean:define id="items" name="gtgshyysrForm" property="dataList"/>
            <logic:iterate id="item" name="items" indexId="index">
            <% int iIndex = index.intValue();
               listLen++;
            %>

            <%if(iIndex==5){%>
            <tr>
                <td  rowspan="2" nowrap class="2-td2-left"><div align="left">�޷���ҵ&nbsp;&nbsp;
                </div></td>
                <td  nowrap class="2-td2-left"><div align="left"><ttsoft:write name="item" property="szsmmc"/>
                <input name="szsmmc" type="hidden" value="<ttsoft:write name="item" property="szsmmc"/>" id="szsmmc_<%=iIndex%>">
                <input name="szsmdm" type="hidden" value="<ttsoft:write name="item" property="szsmdm"/>" id="szsmdm_<%=iIndex%>">
                </div></td>
                <td nowrap class="2-td2-left"><input name="dqkjfpje" type="text" value="<ttsoft:write name="item" property="dqkjfpje"/>" size="10" id="dqkjfpje_<%=iIndex%>" onchange="dqkjfpjefujs(this);" class="inputalignright"></td>
                <td nowrap class="2-td2-left"><input name="dqwkjfpje" type="text" value="<ttsoft:write name="item" property="dqwkjfpje"/>" size="10" id="dqwkjfpje_<%=iIndex%>" onchange="dqwkjfpjefujs(this)" class="inputalignright"></td>
                <td nowrap class="2-td2-left"><input name="dqyysrjehj" type="text" value="<ttsoft:write name="item" property="dqyysrjehj"/>" size="10" id="dqyysrjehj_<%=iIndex%>" onchange="dqyysrjehjfujs(this)" class="inputalignright"></td>
                <td nowrap class="2-td2-center"><input name="jzyysr" type="text" value="<ttsoft:write name="item" property="jzyysr"/>" size="10" id="jzyysr_<%=iIndex%> " onchange="jzyysrfujs(this)" class="inputalignright">
                </td>
            </tr>
            <%}else if(iIndex==9){%>
            <tr>
                <td  rowspan="3" nowrap class="2-td2-left"><div align="center">
		    <p>��</p>
                    <p>��</p>
                    <p>��</p>
                    <p>ҵ</p></div></td>
                <td class="2-td2-left"><div align="left"><ttsoft:write name="item" property="szsmmc"/><input name="szsmmc" type="hidden" value="<ttsoft:write name="item" property="szsmmc"/>" id="szsmmc_<%=iIndex%>"><input name="szsmdm" type="hidden" value="<ttsoft:write name="item" property="szsmdm"/>" id="szsmdm_<%=iIndex%>"></div></td>
                 <td nowrap class="2-td2-left"><input name="dqkjfpje" type="text" value="<ttsoft:write name="item" property="dqkjfpje"/>" size="10" id="dqkjfpje_<%=iIndex%>" onchange="dqkjfpjejs(this)" class="inputalignright"></td>
                <td nowrap class="2-td2-left"><input name="dqwkjfpje" type="text" value="<ttsoft:write name="item" property="dqwkjfpje"/>" size="10" id="dqwkjfpje_<%=iIndex%>" onchange="dqwkjfpjejs(this)" class="inputalignright"></td>
                <td nowrap class="2-td2-left"><input name="dqyysrjehj" type="text" value="<ttsoft:write name="item" property="dqyysrjehj"/>" size="10" id="dqyysrjehj_<%=iIndex%>" onchange="dqyysrjehjjs(this)" class="inputalignright"></td>
                <td nowrap class="2-td2-center"><input name="jzyysr" type="text" value="<ttsoft:write name="item" property="jzyysr"/>" size="10" id="jzyysr_<%=iIndex%> " onchange="jzyysrjs(this)" class="inputalignright">
                </td>
            </tr>
            <%}else if(iIndex==6){%>
	    <tr>
                <td class="2-td2-left"><div align="left"><ttsoft:write name="item" property="szsmmc"/><input name="szsmmc" type="hidden" value="<ttsoft:write name="item" property="szsmmc"/>" id="szsmmc_<%=iIndex%>"><input name="szsmdm" type="hidden" value="<ttsoft:write name="item" property="szsmdm"/>" id="szsmdm_<%=iIndex%>"></div></td>
                <td nowrap class="2-td2-left"><input name="dqkjfpje" type="text" value="<ttsoft:write name="item" property="dqkjfpje"/>" size="10" id="dqkjfpje_<%=iIndex%>" onchange="dqkjfpjeysjs(this)" class="inputalignright"></td>
                <td nowrap class="2-td2-left"><input name="dqwkjfpje" type="text" value="<ttsoft:write name="item" property="dqwkjfpje"/>" size="10" id="dqwkjfpje_<%=iIndex%>" onchange="dqwkjfpjeysjs(this)" class="inputalignright"></td>
                <td nowrap class="2-td2-left"><input name="dqyysrjehj" type="text" value="<ttsoft:write name="item" property="dqyysrjehj"/>" size="10" id="dqyysrjehj_<%=iIndex%>" onchange="dqyysrjehjysjs(this)" class="inputalignright"></td>
                <td nowrap class="2-td2-center" align="center"> <input name="jzyysr" type="text" value="<ttsoft:write name="item" property="jzyysr"/>" size="10" id="jzyysr_<%=iIndex%>" onchange="jzyysrysjs(this)" class="inputalignright">
                </td>
              </tr>
            <%}else if(iIndex==10||iIndex==11){%>
	    <tr>
                <td class="2-td2-left"><div align="left"><ttsoft:write name="item" property="szsmmc"/><input name="szsmmc" type="hidden" value="<ttsoft:write name="item" property="szsmmc"/>" id="szsmmc_<%=iIndex%>"><input name="szsmdm" type="hidden" value="<ttsoft:write name="item" property="szsmdm"/>" id="szsmdm_<%=iIndex%>"></div></td>
                 <td nowrap class="2-td2-left"><input name="dqkjfpje" type="text" value="<ttsoft:write name="item" property="dqkjfpje"/>" size="10" id="dqkjfpje_<%=iIndex%>" onchange="dqkjfpjejs(this)" class="inputalignright"></td>
                <td nowrap class="2-td2-left"><input name="dqwkjfpje" type="text" value="<ttsoft:write name="item" property="dqwkjfpje"/>" size="10" id="dqwkjfpje_<%=iIndex%>" onchange="dqwkjfpjejs(this)" class="inputalignright"></td>
                <td nowrap class="2-td2-left"><input name="dqyysrjehj" type="text" value="<ttsoft:write name="item" property="dqyysrjehj"/>" size="10" id="dqyysrjehj_<%=iIndex%>" onchange="dqyysrjehjjs(this)" class="inputalignright"></td>
                <td nowrap class="2-td2-center"><input name="jzyysr" type="text" value="<ttsoft:write name="item" property="jzyysr"/>" size="10" id="jzyysr_<%=iIndex%> " onchange="jzyysrjs(this)" class="inputalignright">
                </td>
              </tr>
            <%}else{%>
            <tr>
                <td colspan="2" nowrap class="2-td2-left"><div align="left"><ttsoft:write name="item" property="szsmmc"/><input name="szsmmc" type="hidden" value="<ttsoft:write name="item" property="szsmmc"/>" id="szsmmc_<%=iIndex%>"><input name="szsmdm" type="hidden" value="<ttsoft:write name="item" property="szsmdm"/>" id="szsmdm_<%=iIndex%>"></div></td>
                <td nowrap class="2-td2-left"><input name="dqkjfpje" type="text" value="<ttsoft:write name="item" property="dqkjfpje"/>" size="10" id="dqkjfpje_<%=iIndex%>" onchange="dqkjfpjejs(this)" class="inputalignright"></td>
                <td nowrap class="2-td2-left"><input name="dqwkjfpje" type="text" value="<ttsoft:write name="item" property="dqwkjfpje"/>" size="10" id="dqwkjfpje_<%=iIndex%>" onchange="dqwkjfpjejs(this)" class="inputalignright"></td>
                <td nowrap class="2-td2-left"><input name="dqyysrjehj" type="text" value="<ttsoft:write name="item" property="dqyysrjehj"/>" size="10" id="dqyysrjehj_<%=iIndex%>" onchange="dqyysrjehjjs(this)" class="inputalignright"></td>
                <td nowrap class="2-td2-center"><input name="jzyysr" type="text" value="<ttsoft:write name="item" property="jzyysr"/>" size="10" id="jzyysr_<%=iIndex%> " onchange="jzyysrjs(this)" class="inputalignright">
            </tr>
            <%}%>
            </logic:iterate>

              <tr>
                <td colspan="2" nowrap class="2-td2-left"><div align="left">���ܼ�</div>
                  </td>
                <td nowrap class="2-td2-left">&nbsp;<input type="text" name="dqkjfpjehj" class="inbright"  readOnly  tabIndex="-1"/></td>
                <td nowrap class="2-td2-left">&nbsp;<input type="text" name="dqwkjfpjehj" class="inbright "  readOnly tabIndex="-1"/></td>
                <td nowrap class="2-td2-left">&nbsp;<input type="text" name="dqyysrjehjhj" class="inbright "  readOnly tabIndex="-1"/></td>
                <td nowrap class="2-td2-center">&nbsp; <input type="text" name="jzyysrhj" class="inbright "  readOnly tabIndex="-1"/></td>
              </tr>
            </table>
		   <table>
			&nbsp;&nbsp;&nbsp;&nbsp;
                   </table>
                   <input type="image" accesskey="q" tabIndex="-1" onClick="doList();return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="��ѯ" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun" style="cursor:hand">
                   &nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="u" tabIndex="-1"  onClick="ql();return false; " onMouseOver="MM_swapImage('qingchu','',' <%=static_contextpath%>images/qc-u2.jpg ',1)" onMouseOut="MM_swapImgRestore()" src=" <%=static_contextpath%>images/qc-u1.jpg " alt="���"  width="79" height="22" id="qingchu" style="cursor:hand" >
                   &nbsp;&nbsp;&nbsp;&nbsp;<input type="image" accesskey="s" tabIndex="-1" onClick="doSave();return false;" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/bc-s1.jpg" alt="����"  width="79" height="22" id="baocun" style="cursor:hand" >
                   &nbsp;&nbsp;&nbsp;&nbsp;<input type="image" accesskey="c" tabIndex="-1"  onClick="tuichu();return false;"onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor:hand">

                  <br> <br> </td>
          </table></td>
        </tr>
      </table>

</html:form>


  <%@ include file="include/footer.jsp" %>

<script language="JavaScript" type="text/JavaScript">
var listLens=<%=listLen%>;

function doList(){
	document.gtgshyysrForm.actionType.value="List";
	document.gtgshyysrForm.submit();
}

</script>
<script language="javascript"> 
/****�������˰��Ϊ��������������ʾ��ʾ��Ϣ****/
/****20050817 Huxiaofeng****/
var nsrzt = <ttsoft:write name="gtgshyysrForm" property="nsrzt" filter="false"/>;
checkNsrzt();
/**************end *******/
</script>
</body>
</html:html>