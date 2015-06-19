<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page contentType="text/html; charset=gb2312" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>

<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>
��Ȼ�˸�˰�걨
</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/reader.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/Stack.js"></script>


</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
<%@ include file="./include/header.jsp"%>

<html:form action="/webapp/smsb/wjgrAction.do" method="POST">
<html:hidden property="actionType" value="Show" />

<html:hidden property="zydm" />
<html:hidden property="fdfs" />
<html:hidden property="yhdm" />
<html:hidden property="yhmc" />
<html:hidden property="sfczbs" />
<html:hidden property="gjdm" />
<html:hidden property="zjlxdm" />

<table align="center" cellspacing="0" class="table-99">
  <tr>
    <td class="1-td1">��Ȼ�˸�˰�걨</td>
  </tr>
  <tr>
     <td class="1-td2">
            <table cellspacing="0" class="table-99" width="100%">
              <tr class="2-td2-left">
              <td align="left" nowrap>
                      �걨���ڣ� <html:text property="sbrq" onchange="setSkssrq(this)" size="10" onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                </td>
                <td align="left" nowrap>
                      ˰���������ڣ�<html:text property="skssksrq" onchange="isDate(this,false)" size="10" onkeydown="if(event.keyCode==13) event.keyCode=9;"/>&nbsp;��&nbsp;<html:text property="skssjsrq" onchange="isDate(this,false)" size="10" onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                </td>

                <td align="center" nowrap>
                    <div align="right">��λ�������Ԫ</div>
                </td>
              </tr>

            </table>
         <br>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
<tr class="black9">
<td class="2-td2-t-left" nowrap><div align="left">&nbsp;&nbsp;����
                          </div>
</td>
<td class="2-td2-t-left" nowrap><div align="left">&nbsp;&nbsp;<!-- <ttsoft:select property="gjdm" codekey="GJDQ" onkeydown="if(event.keyCode==13) event.keyCode=9;" onchange="clearZjhm()" /> -->
<ttsoft:dmmc property="gjdm" codekey="GJDQ" />
                          </div>
</td>
<td class="2-td2-t-left" nowrap><div align="left">&nbsp;&nbsp;֤������
                          </div>
</td>
<td class="2-td2-t-left" nowrap><div align="left">&nbsp;&nbsp;<!-- <ttsoft:select property="zjlxdm" codekey="ZJLX" onkeydown="if(event.keyCode==13) event.keyCode=9;" onchange="clearZjhm()" /> -->
<ttsoft:dmmc property="zjlxdm" codekey="ZJLX" />
                          </div>
</td>
<td class="2-td2-t-left" nowrap><div align="left">&nbsp;&nbsp;֤������
                          </div>
</td>
<td class="2-td2-t-center" nowrap><div align="left"><html:text property="zjhm" maxlength="30" tabindex="-1"  styleClass="inputnoborder"    size="30" readonly="true" style="color:#3C5564"/>
                          </div>
</td>
</tr>
<tr class="black9">
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;��������
                          </div>
</td>
<td class="2-td2-left" nowrap ><div align="left" id="bankName">&nbsp;
                          </div>
</td>
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;�ʺ�
                          </div>
</td>
<td class="2-td2-left" nowrap><div align="left">
<bean:define id="bankList" name="wjgrActionForm" property="bankList" />
                              <html:select property="zh" onchange="setBankName(this)"  onkeydown="if(event.keyCode==13) event.keyCode=9;">
                                 <html:options collection="bankList" property="zh" labelProperty="zh"/>
                              </html:select>
                          </div>
</td>
<td class="2-td2-left" nowrap><div align="left">&nbsp;���������
                          </div>
</td>
<td class="2-td2-center" nowrap><div align="left"><html:text property="jsjdm"   size="10" onchange="checkZjhm(this)" onkeydown="jsjdmQuery()" maxlength="8"/>
                          </div>
</td>
</tr>
<tr class="black9">
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;��˰������
                          </div>
</td>
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;<html:text property="nsrmc" tabindex="-1"  styleClass="inputnoborder"    size="30" readonly="true" style="color:#3C5564"/>
                          </div>
</td>
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;�Ƿ�פ
                          </div>
</td>
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;<ttsoft:dmmc property="sfczbs" codekey="SFCZBS" />
                          </div>
</td>
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;�ֻ�����
                          </div>
</td>
<td class="2-td2-center" nowrap><div align="left">&nbsp;&nbsp;<html:text property="dhrq" tabindex="-1"  styleClass="inputnoborder"    size="30" readonly="true" style="color:#3C5564"/>
                          </div>
</td>
</tr>
<tr class="black9">
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;�ⷿ�ѿ۳���
                          </div>
</td>
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;<html:text property="zffkcs" tabindex="-1"  styleClass="inputnoborder"    size="30" readonly="true" style="color:#3C5564"/>
                          </div>
</td>
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;������ʽ
                          </div>
</td>
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;
<ttsoft:dmmc property="fdfs" codekey="SKFDQK"/>
                          </div>
</td>
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;��λ��������(%)
                          </div>
</td>
<td class="2-td2-center" nowrap><div align="left">&nbsp;&nbsp;<html:text property="dwfdbl" tabindex="-1"  styleClass="inputnoborder"    size="30" readonly="true" style="color:#3C5564"/>
                          </div>
</td>
</tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
<tr class="black9">
<td class="2-td2-left"><div align="left">&nbsp;&nbsp;���й�����סַ<!-- <br>&nbsp;&nbsp;Address in China -->
                          </div>
</td>
<td class="2-td2-center"><div align="left">
                   <html:text property="jnzz" tabindex="-1"  styleClass="inputnoborder"    size="60" readonly="true" style="color:#3C5564"/>
                          </div>
</td>
</tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
<tr class="black9">
<td class="2-td2-left" nowrap>ְҵ <!-- Profession --> </TD><td class="2-td2-left" nowrap>
<ttsoft:dmmc property="zydm" codekey="ZYDM" />
</td>
<td class="2-td2-left" nowrap>����λ <!-- Employer --> </TD><td class="2-td2-left" nowrap><div align="left"> <html:text property="fwdw"   size="20" onkeydown="if(event.keyCode==13) event.keyCode=9;" maxlength="50"/></div>
</td>
<td class="2-td2-left" nowrap>����ص�<!--  Working locationg --></TD><td class="2-td2-center" nowrap><div align="left"> <html:text property="fwdd"   size="20" onkeydown="if(event.keyCode==13) event.keyCode=9;" maxlength="50"/></div>
</td>
</tr>
</table>
<br>
<table width="100%" border="0" cellpadding="0" cellspacing="0"  id="ZRRGRSDSMX" onkeydown=dokeydown(this.id,'szsmdm')  onmouseup='return dokeyUp(this.id)' class="table-99">
  <tr>
    <td rowspan="2" class="2-td1-left">������Ŀ<!-- <br>Categaries of in come --></td>
    <td rowspan="2" class="2-td1-left">���ÿ�ʼ����<!-- <br>Income period --></td>
    <td rowspan="2" class="2-td1-left">���ý�������<!-- <br>Income period --></td>
    <td rowspan="2" class="2-td1-left">ԭ��ס��˰��<!-- <br>Income period --></td>
    <td rowspan="2" class="2-td1-left">�����۳���<!-- <br>Income period --></td>
    <td colspan="3" class="2-td1-left">����� <!-- Receipts --></td>
    <td rowspan="2" class="2-td1-left">�����ö� <!-- <br> Deductions --></td>
    <td rowspan="2" class="2-td1-left">Ӧ��˰���ö� <!-- <br> Taxable income --></td>
    <td rowspan="2" class="2-td1-left">˰�� <!-- <br> Tax rate --></td>
    <td rowspan="2" class="2-td1-left">����۳��� <!-- <br> Quick calculation deduction --></td>
    <td rowspan="2" class="2-td1-left">Ӧ��˰�� <!-- <br> Income tax --></td>
    <td rowspan="2" class="2-td1-left">�ѿ۽�˰�� <!-- <br> Tax withheld --></td>
    <td rowspan="2" class="2-td1-center">Ӧ�����ˣ�˰��<!--  <br> Amount of income tax due or over paid --></td>
  </tr>
  <tr>
    <td class="2-td1-left">�����<!-- <br> RMB --></td>
    <td class="2-td1-left">�ۺ������ <!-- Foreign currency --></td>
    <td class="2-td1-left">����Һϼ�<!-- <br> Total --></td>
  </tr>
  <!-- <tr>
    <td class="2-td1-left">��������</td>
    <td class="2-td1-left">���</td>
    <td class="2-td1-left">����Ƽ�</td>
    <td class="2-td1-left">�ۺ������</td>
  </tr> -->
  <ttsoft:smsbtable form="wjgrActionForm" property="dataList"  tableId="ZRRGRSDSMX" hasTitle="false"/>
  <DIV id=divSfTemp></DIV>

</table>

   </td>
   </tr>
</table>
<!--ҳ�水ť������-->
<br>
<table class="table-99">
<tr>
    <td align="center">

        <input type="image" accesskey="w" tabIndex="-1" onclick="javascript:doJkswh(); return false;"  onMouseOver="MM_swapImage('whjks1','','<%=static_contextpath%>images/whjks-w2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="ά���ɿ���" id="whjks1" src="<%=static_contextpath%>images/whjks-w1.jpg" width="110" height="22">
        &nbsp;&nbsp;
         <input type="image" accesskey="a" tabIndex="-1" onclick="javascript:add4Row('ZRRGRSDSMX');return false;"  onMouseOver="MM_swapImage('zj1','','<%=static_contextpath%>images/zj-a2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="����" id="zj1" src="<%=static_contextpath%>images/zj-a1.jpg" width="79" height="22">
        &nbsp;&nbsp;
        <input type="image" accesskey="d" tabIndex="-1" onClick="javascript:deleteRow('ZRRGRSDSMX',null,'szsmdm'); return false;"  onMouseOver="MM_swapImage('sc2','','<%=static_contextpath%>images/sc-d2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="ɾ��" id="sc2" src="<%=static_contextpath%>images/sc-d1.jpg" width="79" height="22">
        &nbsp;&nbsp;
        <input type="image" accesskey="s" tabIndex="-1" onClick="doSubmitCheck('Save'); return false;"  onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="����" id="bc1" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" height="22">
        &nbsp;&nbsp;
        <input type="image" accesskey="c" tabIndex="-1"   onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22">
    </td>
</tr>
</table>
<div id=szsmdm_epodDateLayer style="position: absolute; width: 142; height: 166; z-index: 9998; display: none" ><select id=sel onclick=selectClick("szsmdm","ZRRGRSDSMX") onkeyup=selectMove("szsmdm","ZRRGRSDSMX")   onkeydown="if(window.event.keyCode==13) selectClick('szsmdm','ZRRGRSDSMX')"></select></div>
<INPUT TYPE="hidden" NAME="szsmdm_focus">


</td>
</tr>
</table>
</html:form>
<%@ include file="./include/footer.jsp"%>
<!--js�ű���-->
<script language="javascript" type="text/JavaScript">
//����ҳ��ʱ��������Ϊ���������¼��
// ҳ����뽹��ȷ��
function fnOnLoad()
{
    document.forms[0].jsjdm.focus();
}

var ZRRGRSDSMX_list=new DTable(ZRRGRSDSMX,jsArr_ZRRGRSDSMX);
ZRRGRSDSMX_list.tableHead=2;
//��ϸ���ݲ����Գ���4��
function add4Row(obj){
    if(ZRRGRSDSMX.rows.length>5){
        //�Ѿ���4���걨����
        return false;
    }
    if(document.forms[0].zjhm.value=='' || document.forms[0].nsrmc.value==''){
        //û��¼�������Ϣ
        return false;
    }
    //����4��
    addRow('ZRRGRSDSMX');
    return false;
}

//��ϸ�б�ʹ�õ�js����
<ttsoft:write name="wjgrActionForm" property="scriptStr" filter="false"/>
//������������
<ttsoft:write name="wjgrActionForm" property="bankJsArray" filter="false"/>
function setBankName(obj){
    for (var i=0; i<bankInfo.length; i++){
        if (bankInfo[i][0] == obj.value){
            document.all.yhmc.value = bankInfo[i][1];
            document.all.yhdm.value = bankInfo[i][2];
            bankName.innerText = " " + bankInfo[i][1];
            break;
        }
    }
    if(obj.value==''){

        document.all.yhmc.value = '';
        document.all.yhdm.value = '';
        bankName.innerText = " ";
    }
}
setBankName(document.all.zh);
//����˰����������
function setSkssrq(obj){
    //���¼��ֵ����ȷ��
    if(isDate(obj,false)){
        //�õ��±��͵�˰����������
        getStartEndDate(obj,document.forms[0].skssksrq,document.forms[0].skssjsrq,2);
    }
}
//���˰���������ڵ���ȷ��
function checkSkssrq(obj){
    if(isDate(obj,false)){
        if(parseFloat(document.forms[0].skssksrq.value)>parseFloat(document.forms[0].skssjsrq.value)){

        }
    }
}
//����������֤�����ͱ��ʱ�����֤������
function clearZjhm(){
    document.forms[0].zjhm.value="";
}
//��������֤������
function checkZjhm(obj){
    //������֤����
    //if(document.forms[0].zjlxdm.value=='02'&&!checkIdentityCard(obj.value,null)) {
    //	obj.value='';
    //	obj.focus();
    //	return false;
    //}
    if(obj.value!='') doSubmitForm('Query');
}
//��ת���ɿ���ά������
function doJkswh(){
    //����¼����������
    if(document.forms[0].zjhm.value=='' || document.forms[0].nsrmc.value=='')
      return false;
    document.all.actionType.value='Jkswh';
    document.forms[0].submit();
}
//��Ӧ���������Ļس���ѯ
function jsjdmQuery(){
    if(event.keyCode==13) event.keyCode = 9;
}
//��Ӧ���������Ļس���ѯ
function enter2Tab(){
    if(event.keyCode==13) event.keyCode = 9;
}
//���¼����ĺϷ��ԣ�Ȼ���ύ
function doSubmitCheck(ope){
    //���˰����������
    if(parseFloat(document.forms[0].skssksrq.value)>parseFloat(document.forms[0].skssjsrq.value)){
        alert("˰��������ʼ���ڲ��ܴ���˰��������������");
        document.forms[0].skssksrq.select();
        return false;
    }
    if(ZRRGRSDSMX.rows.length<3 || document.forms[0].zjhm.value==''){
        //����ϸ����
        return false;
    }
    //���������ϸ���ݵĺϷ���
    for(var i=0;i<ZRRGRSDSMX.rows.length;i++){
        if(ZRRGRSDSMX.rows[i].all("szsmdm")){
            //˰��˰Ŀ���벻����Ϊ��
            if(ZRRGRSDSMX.rows[i].all("szsmdm").value==''){
                alert("˰��˰Ŀ���벻��Ϊ��");
                ZRRGRSDSMX.rows[i].all("szsmdm").focus();
                return false;
            }
            //���˰����������
            if(parseFloat(ZRRGRSDSMX.rows[i].all("sdksrq").value)>parseFloat(ZRRGRSDSMX.rows[i].all("sdjsrq").value)){
                alert("���ÿ�ʼ���ڲ��ܴ������ý�������");
                ZRRGRSDSMX.rows[i].all("sdksrq").focus();
                return false;
            }
            //������걨
            if(ZRRGRSDSMX.rows[i].all("ybtsk").value=='' || ZRRGRSDSMX.rows[i].all("ybtsk").value=='0'){
                alert("�Ѿ�ȡ�����걨���������ݲ����棬��ɾ�����޸�");
                ZRRGRSDSMX.rows[i].all("ybtsk").focus();
                return false;
            }

//start add by qianchao 2005.11.1

            if(ZRRGRSDSMX.rows[i].all("ynse").value=='' || ZRRGRSDSMX.rows[i].all("ynse").value=='0'){
                alert("Ӧ��˰���Ϊ��");
                ZRRGRSDSMX.rows[i].all("ynse").focus();
                return false;
            }

//end add by qianchao 2005.11.1
        }
    }
    doSubmitForm(ope);
}

//��������Һϼƣ��ϼ�����ң�����ң��ۺ������
function hjRMB(obj){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    if(isNum(oRow.all("srermb"),0,null,null,null,null) && isNum(oRow.all("zhrmb"),0,null,null,null,null)){
        //�õ������
        var rmb = checkEmpty(oRow.all("srermb"));
        //�õ��ۺ������
        var zhrmb = checkEmpty(oRow.all("zhrmb"));
        var rmbhj = parseFloat(rmb)+parseFloat(zhrmb);
        oRow.all("rmbhj").value=rmbhj;
        //����Ӧ��˰���ö�
        comYnssde(obj);
    }
}
//���¼��֪�Ƿ�Ϊ'',���Ϊ����¼��ֵ��Ϊ0
function checkEmpty(obj){
    var value = obj.value;
    if(value==''){
        value=0;
        obj.value=0;
    }
    return value;

}
//��˰ĿΪ05011����˰Ŀ��Ӧ��˰��ļ��㣬�����ǰ�һ���¼���ģ���������ڼ�����������һ���µģ���Ӧ��˰�Ҫ���Լ���������µó�ʵ��Ӧ��˰������Ӧ��˰�Ҫ���޸ģ���ֹ���Ǽ��������ʱ����ֹ��޸��Ա�֤���ݱ��浽���ݿ��У�
function comYnseByMonths(obj){
    //�Ƿ�פ��ʾ
    var sfczbs = document.forms[0].sfczbs.value;
    if(sfczbs=='0'){
        //��פ�İ����¼���,�ǳ�פ�ı������еļ��㷽��;
        return false;
    }
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    //�������·���
    var months=getBMonths(oRow.all("sdksrq").value,oRow.all("sdjsrq").value)+1;
    oRow.all("ynse").value = oRow.all("ynse").value*months;
}

//����Ӧ��˰���ö�
function comYnssde(obj){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    if(isNum(oRow.all("rmbhj"),0,null,null,null,null)){
    //�õ�˰��˰Ŀ����
    var szsmdm = oRow.all("szsmdm").value;
    var szsmSub = szsmdm.substr(0,5);
    var szsmSub4 = szsmdm.substr(0,4);
    //�õ�������ʽ
    var fdfs = document.forms[0].fdfs.value;
    if(szsmSub=='05011'){
        //if(szsmdm!='050110'){
            //��050110�¹���
            if(fdfs=='3'){
                //���˸���			/*Ӧ��˰���ö�=���������ע�������룩-�����۳���Ŀ��ע����˰ǰ�۳�������������Ŀ��-��׼�۳����ã��й���1000�����������⼮�ˡ��۰�̨4000�����ǼǱ��еĹ������*/
                //�����
                var zhrmb =  checkEmpty(oRow.all("rmbhj"));
                //�����ö�
                var jfye = checkEmpty(oRow.all("jfye"));
                //�����۳���Ŀ���
                var fdkce = checkEmpty(oRow.all("fdkce"));
                //����Ӧ��˰���ö�
                oRow.all("ynssde").value = roundV(parseFloat(zhrmb)-parseFloat(fdkce)-parseFloat(jfye));
                var index = fixBhs(obj);
                if(index!='-1'){
                    //˰��
                    var sl = szsmlist[index][12];
                    if(sl == '') sl=0;
                    //����۳���
                    var sskcs = szsmlist[index][13];
                    if(sskcs == '') sskcs=0;
                    //�Ƚ�ԭ��ס��Ӧ����˰��
                    compareYnssde(obj,sl,sskcs);
                }
                //�õ�����˰�ʺ�����۳���
                fixSysl(obj);
                //����Ӧ��˰��
                /*Ӧ��˰��=Ӧ��˰���ö�* ����˰�ʣ���˰�ʱ�--����н�����ã�-����۳�������˰�ʱ�--����н�����ã�*/
                var ynssde = oRow.all("ynssde").value;
                //����˰��
                var sl = checkEmpty(oRow.all("sl"));
                //����۳���
                var sskcs = checkEmpty(oRow.all("sskcs"));
                oRow.all("ynse").value = roundV(parseFloat(ynssde)*parseFloat(sl)-parseFloat(sskcs));
                //����˰���Ƿ�פ��ʶΪ���ʱ��˰Ŀ05011���µ�˰Ŀ�ļ����Ӧ��˰�ԭ����ʽ�����Ӧ��˰��/�������ڼ�������ڣ������ڼ俪ʼ���ڣ�������������Ӧ������Ӧ��˰��ѿۣ�
                comYnse05011(obj);

                //���������ڼ����Ӧ��˰��

                comYnseByMonths(obj);
                //����Ӧ��˰��
                /*Ӧ��˰��=Ӧ��˰��-�ѿۻ��ѽ�˰�ע��ָ�����ѿ۽ɻ��ѽ���˰�*/
                //Ӧ��˰��
                var ynse = checkEmpty(oRow.all("ynse"));
                //�ѿۻ��ѽ�˰��
                var ykjse = checkEmpty(oRow.all("ykjse"));
                oRow.all("ybtsk").value = parseFloat(ynse)-parseFloat(ykjse);


            }else if(fdfs=='1'){
                //��˾ȫ���				/*Ӧ��˰���ö�=������˰�����-�����۳���Ŀ���-���ÿ۳���׼-����۳������£�1-˰�ʣ����е�˰����ָ����˰���ð�����˰�����Ӧ��˰��*/
                //�õ�����˰�����˰��˰Ŀ����index
                var index = fixBhs(obj);
                //alert(index)
                if(index!='-1'){
                    //�õ������
                    var rmbhj =  checkEmpty(oRow.all("rmbhj"));
                    //�����ö�
                    var jfye = checkEmpty(oRow.all("jfye"));
                    //�����۳���Ŀ���
                    var fdkce = checkEmpty(oRow.all("fdkce"));
                    //˰��
                    var sl = szsmlist[index][12];
                    if(sl == '') sl=0;
                    //����۳���
                    var sskcs = szsmlist[index][13];
                    if(sskcs == '') sskcs=0;
                    //alert(sl+" : "+sskcs)
                    //����Ӧ��˰���ö�
                    oRow.all("ynssde").value = roundV((parseFloat(rmbhj)-parseFloat(fdkce)-parseFloat(jfye)-parseFloat(sskcs))/(1-parseFloat(sl)));
                    //�Ƚ�ԭ��ס��Ӧ����˰��
                    compareYnssde(obj,sl,sskcs);
                    //����Ӧ��˰��
                    //�õ�����˰�ʺ�����۳���
                    fixSysl(obj);
                    /*Ӧ��˰��=Ӧ��˰���ö�*˰��-����۳���*/
                    oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*parseFloat(oRow.all("sl").value)-parseFloat(oRow.all("sskcs").value));
                    //����˰���Ƿ�פ��ʶΪ���ʱ��˰Ŀ05011���µ�˰Ŀ�ļ����Ӧ��˰�ԭ����ʽ�����Ӧ��˰��/�������ڼ�������ڣ������ڼ俪ʼ���ڣ�������������Ӧ������Ӧ��˰��ѿۣ�
                    comYnse05011(obj);
                    //���������ڼ����Ӧ��˰��

                    comYnseByMonths(obj);
                    //Ӧ��˰��
                    var ynse = checkEmpty(oRow.all("ynse"));

                    //�ѿۻ��ѽ�˰��
                    var ykjse = checkEmpty(oRow.all("ykjse"));
                    oRow.all("ybtsk").value = parseFloat(ynse)-parseFloat(ykjse);

                }
            }else if(fdfs=='2'){
                //��λ��������
                /*Ӧ��˰���ö�=��δ������������˰�������-���ÿ۳���׼-����۳���*�����������£�1-˰��*����������*/
                //�õ�����˰�����˰��˰Ŀ����index
                var index = fixBhs(obj);
                if(index!='-1'){
                    //��������
                    var fdbl = document.forms[0].dwfdbl.value;
                    //�õ������
                    var rmbhj =  checkEmpty(oRow.all("rmbhj"));
                    //�����ö�
                    var jfye = checkEmpty(oRow.all("jfye"));
                    //˰��
                    var sl = szsmlist[index][12];
                    if(sl == '') sl=0;
                    //����۳���
                    var sskcs = szsmlist[index][13];
                    if(sskcs == '') sskcs=0;

                    //����Ӧ��˰���ö�

                    oRow.all("ynssde").value = roundV((parseFloat(rmbhj)-parseFloat(jfye)-parseFloat(sskcs)*(parseFloat(fdbl)/100))/(1-parseFloat(sl)*(parseFloat(fdbl)/100)));


                    //�Ƚ�ԭ��ס��Ӧ����˰��
                    compareYnssde(obj,sl,sskcs);
                    //����Ӧ��˰��
                    /*Ӧ��˰��=Ӧ��˰���ö�*����˰��-����۳���*/
                    //�õ�����˰�ʺ�����۳���
                    fixSysl(obj);
                    //����˰��
                    var sysl = checkEmpty(oRow.all("sl"));
                    //ʹ������۳���
                    var sysscks = checkEmpty(oRow.all("sskcs"));
                    //Ӧ��˰���ö�
                    var ynssde2 = checkEmpty(oRow.all("ynssde"));
                    //����Ӧ��˰��
                    oRow.all("ynse").value =roundV(parseFloat(ynssde2)*parseFloat(sysl)-parseFloat(sysscks));

                    //����˰���Ƿ�פ��ʶΪ���ʱ��˰Ŀ05011���µ�˰Ŀ�ļ����Ӧ��˰�ԭ����ʽ�����Ӧ��˰��/�������ڼ�������ڣ������ڼ俪ʼ���ڣ�������������Ӧ������Ӧ��˰��ѿۣ�
                    comYnse05011(obj);

                    //���������ڼ����Ӧ��˰��

                    comYnseByMonths(obj);
                    //Ӧ��˰��
                    var ynse = checkEmpty(oRow.all("ynse"));
                    //�ѿۻ��ѽ�˰��
                    var ykjse = checkEmpty(oRow.all("ykjse"));
                    oRow.all("ybtsk").value = parseFloat(ynse)-parseFloat(ykjse);

                }

            }
        //}
    }
    //050150���½������շֺ졢���ս�
    com050150(obj);
    //0503���񱨳�
    com0503(obj);
    //0504�������
    com0504(obj);
    //0505����Ȩʹ�÷�����
    com0505(obj);
    //0506��Ϣ����Ϣ����������
    com0506(obj);
    //050702һ��Ʋ�����
    com050702(obj);
    //050701����˽������
    com050701(obj);
    //0508�Ʋ�ת������
    com0508(obj);
    //0509żȻ����
    com0509(obj);
    //0510������Ժ��������ȷ����˰����������
    com0510(obj)
    //050151������
    com050151(obj)
    //050152����ְ
    com050152(obj)
    }
}
//�����ֵ�ͺϷ���
function checkNum1(obj){
    isNum(obj,0,null,null,null,null);
}
//�Ƚ�ԭ��ס��Ӧ����˰��
function compareYnssde(obj,sl,sskcs){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    //�õ�������ʽ
    var fdfs = document.forms[0].fdfs.value;
    //�����
    var zhrmb =  checkEmpty(oRow.all("rmbhj"));
    //�����ö�
    var jfye = checkEmpty(oRow.all("jfye"));
    //�����۳���Ŀ���
    var fdkce = checkEmpty(oRow.all("fdkce"));
    //ԭ��ס��˰��
    var yjzgsk = checkEmpty(oRow.all("yjzgsk"));
    //��׼Ӧ��˰���ö�
    var ynssde1 = checkEmpty(oRow.all("ynssde"));
    if(parseFloat(yjzgsk)>0){
        //ԭ��ס��˰�����0
        var sre = parseFloat(zhrmb)-parseFloat(yjzgsk);
        //��ԭ��ס��˰������Ӧ��˰���ö�
        var ynssde = roundV((parseFloat(sre)-parseFloat(jfye)-parseFloat(sskcs))/(1-parseFloat(sl)));
        //alert(ynssde+" : "+ynssde1+" : "+sl+" : "+sskcs);
        if(parseFloat(ynssde)>parseFloat(ynssde1)){
            //��ԭ��ס��˰������Ӧ��˰���ö���ڱ�׼Ӧ��˰���ö������ð�ԭ��ס��˰������Ӧ��˰���ö�
            oRow.all("ynssde").value =ynssde;
        }
    }

}
//����˰���Ƿ�פ��ʶΪ���ʱ��˰Ŀ05011���µ�˰Ŀ�ļ����Ӧ��˰�ԭ����ʽ�����Ӧ��˰��/�����ڼ����ڵ��·ݵ���������*�������ڼ�������ڣ������ڼ俪ʼ���ڣ�������������Ӧ������Ӧ��˰��ѿۣ�

function comYnse05011(obj){
    //�Ƿ�פ��ʾ
    var sfczbs = document.forms[0].sfczbs.value;
    if(sfczbs=='0'){
        //�õ���ǰ��
        var oRow = getObjRow(obj);
        //��������
        var days = getDaysOfMonth(oRow.all("sdksrq").value);
        //����
        var wlts = getBDays(oRow.all("sdksrq").value,oRow.all("sdjsrq").value)+1;
        //��ǰӦ��˰��
        //alert(oRow.all("ynse").value+" : "+days+" : "+wlts)
        var ynse = roundV(oRow.all("ynse").value/days*wlts);
        oRow.all("ynse").value = ynse;
        //alert(days+" : "+ wlts+" : "+ynse)
    }
}

//����Ӧ��˰��
function comYbtsk(obj){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    if(isNum(oRow.all("ynse"),0,null,null,null,null) && isNum(oRow.all("ykjse"),0,null,null,null,null)){
        /*Ӧ��˰��=Ӧ��˰��-�ѿۻ��ѽ�˰�ע��ָ�����ѿ۽ɻ��ѽ���˰�*/
        //Ӧ��˰��
        var ynse = checkEmpty(oRow.all("ynse"));
        //�ѿۻ��ѽ�˰��
        var ykjse = checkEmpty(oRow.all("ykjse"));
        oRow.all("ybtsk").value = parseFloat(ynse)-parseFloat(ykjse);
    }
}
//����С�������λ
function roundV(value){
    return Math.round(value*100)/100;
}
//�õ�����˰�����˰��˰Ŀ����index
function fixBhs(obj){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    //�õ������
    var rmbhj =  checkEmpty(oRow.all("rmbhj"));
    //�����ö�
    var jfye = checkEmpty(oRow.all("jfye"));
    //�����۳���Ŀ���
    var fdkce = checkEmpty(oRow.all("fdkce"));
    //���㲻��˰����
    var bhs = roundV(parseFloat(rmbhj)-parseFloat(jfye)-parseFloat(fdkce));
    for(i=0;i<szsmlist.length;i++){
        var szsmSub = szsmlist[i][0].substr(0,5);
    //�ҵ����ڲ���˰������ʼ��ʼ��С�ڲ���˰������ʼ��ֹ����˰��˰Ŀ

        if(szsmSub=="05011" && parseFloat(szsmlist[i][10])<parseFloat(bhs) && parseFloat(szsmlist[i][11])>=parseFloat(bhs)){
            return i;
        }
    }
    //δ��ѯ����Ӧ�Ĳ���˰�����˰��˰Ŀ
    return -1;
}
//�õ�����˰�����˰��˰Ŀ����index
function fixBhs(obj){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    //�õ������
    var rmbhj =  checkEmpty(oRow.all("rmbhj"));
    //�����ö�
    var jfye = checkEmpty(oRow.all("jfye"));
    //�����۳���Ŀ���
    var fdkce = checkEmpty(oRow.all("fdkce"));
    //���㲻��˰����
    var bhs = roundV(parseFloat(rmbhj)-parseFloat(jfye)-parseFloat(fdkce));
    for(i=0;i<szsmlist.length;i++){
        var szsmSub = szsmlist[i][0].substr(0,5);
    //�ҵ����ڲ���˰������ʼ��ʼ��С�ڲ���˰������ʼ��ֹ����˰��˰Ŀ

        if(szsmSub=="05011" && parseFloat(szsmlist[i][10])<parseFloat(bhs) && parseFloat(szsmlist[i][11])>=parseFloat(bhs)){
            return i;
        }
    }
    //δ��ѯ����Ӧ�Ĳ���˰�����˰��˰Ŀ
    return -1;
}
//�õ�����˰�����˰��˰Ŀ����index
function fixBhs050150(obj){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    //�õ������
    var rmbhj =  checkEmpty(oRow.all("rmbhj"));
    //�����ö�
    var jfye = checkEmpty(oRow.all("jfye"));
    //�����۳���Ŀ���
    var fdkce = checkEmpty(oRow.all("fdkce"));
    //���㲻��˰����
    //var bhs = roundV(parseFloat(rmbhj)-parseFloat(jfye)-parseFloat(fdkce));
    var bhs = roundV(parseFloat(rmbhj));
    for(i=0;i<szsmlist.length;i++){
        var szsmSub = szsmlist[i][0].substr(0,5);
    //�ҵ����ڲ���˰������ʼ��ʼ��С�ڲ���˰������ʼ��ֹ����˰��˰Ŀ

        if(szsmSub=="05011" && parseFloat(szsmlist[i][10])<parseFloat(bhs) && parseFloat(szsmlist[i][11])>=parseFloat(bhs)){
            return i;
        }
    }
    //δ��ѯ����Ӧ�Ĳ���˰�����˰��˰Ŀ
    return -1;
}
//�õ�����˰�����˰��˰Ŀ����index
function fixBhs050151(obj,sre){

    for(i=0;i<szsmlist.length;i++){
        var szsmSub = szsmlist[i][0].substr(0,5);
    //�ҵ����ڲ���˰������ʼ��ʼ��С�ڲ���˰������ʼ��ֹ����˰��˰Ŀ

        if(szsmSub=="05011" && parseFloat(szsmlist[i][10])<parseFloat(sre) && parseFloat(szsmlist[i][11])>=parseFloat(sre)){
            return i;
        }
    }
    //δ��ѯ����Ӧ�Ĳ���˰�����˰��˰Ŀ
    return -1;
}
//�õ�����˰�ʺ�����۳���
function fixSysl(obj){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    var ynssde = oRow.all("ynssde").value;
    for(i=0;i<szsmlist.length;i++){
        var szsmSub = szsmlist[i][0].substr(0,5);
    //�ҵ�����Ӧ��˰��ʼ��С��Ӧ��˰��ֹ����˰��˰Ŀ
    //if(szsmSub=="05011") alert(szsmSub+" : "+ynssde+" : "+szsmlist[i][4]+" : "+szsmlist[i][5])
        if(szsmSub=="05011" && parseFloat(szsmlist[i][4])<parseFloat(ynssde) && parseFloat(szsmlist[i][5])>=parseFloat(ynssde)){
            //����˰��˰Ŀ����
            oRow.all("szsmdm").value = szsmlist[i][0];
            //������ǰ˰����������
            var oks = oRow.all("sdksrq").value;
            var ojs = oRow.all("sdjsrq").value;
            //����˰����������
            //oRow.all("sdksrq").value = szsmlist[i][7];
            //oRow.all("sdjsrq").value = szsmlist[i][8];
            //����˰��
            //oRow.all("sl").value = szsmlist[i][3];
            //��������۳���
            //oRow.all("sskcs").value = szsmlist[i][6];
            resetRow("szsmdm","ZRRGRSDSMX",oRow.all("szsmdm"))
            //oRow.all("szsmdm").select();
            //����˰����������
            oRow.all("sdksrq").value = oks;
            oRow.all("sdjsrq").value = ojs;
            return i;
        }
    }
}

//�õ�����˰�ʺ�����۳������б�index
function fixSysli(obj){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    var ynssde = oRow.all("ynssde").value;
    for(i=0;i<szsmlist.length;i++){
        var szsmSub = szsmlist[i][0].substr(0,5);
    //�ҵ�����Ӧ��˰��ʼ��С��Ӧ��˰��ֹ����˰��˰Ŀ
    //if(szsmSub=="05011") alert(szsmSub+" : "+ynssde+" : "+szsmlist[i][4]+" : "+szsmlist[i][5])
        if(szsmSub=="05011" && parseFloat(szsmlist[i][4])<parseFloat(ynssde) && parseFloat(szsmlist[i][5])>=parseFloat(ynssde)){
            return i;
        }
    }
    //δ�ҵ��ʺ�˰��
    return -1;
}
//050150���½������շֺ졢���ս�
function com050150(obj){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    //�õ�˰��˰Ŀ����
    var szsmdm = oRow.all("szsmdm").value;
    //�õ�������ʽ
    var fdfs = document.forms[0].fdfs.value;
    if(szsmdm=='050150'){
        if(fdfs=='3'){
            //1�����˸�����
            //Ӧ��˰���ö�=�����
            //�õ������
            var rmbhj =  checkEmpty(oRow.all("rmbhj"));
            oRow.all("ynssde").value = rmbhj;
            /*Ӧ��˰��=Ӧ��˰���ö�* ˰�ʣ���˰�ʱ�--����н�����ã�-����۳�������˰�ʱ�--����н�����ã�*/
            var hi = fixSysli(obj);
            if(hi!='-1'){
                //����˰��
                var sysl = szsmlist[hi][3];
                //ʹ������۳���
                var sysscks = szsmlist[hi][6];
                //Ӧ��˰���ö�
                var ynssde2 = checkEmpty(oRow.all("ynssde"));
                //����Ӧ��˰��
                oRow.all("ynse").value =roundV(parseFloat(ynssde2)*parseFloat(sysl)-parseFloat(sysscks));
            }
            /*Ӧ��˰��=Ӧ��˰��-�ѿۻ��ѽ�˰�ע��ָ�����ѿ۽ɻ��ѽ���˰�*/
            var ynse3=checkEmpty(oRow.all("ynse"));
            var ykjse3 =checkEmpty(oRow.all("ykjse"));
            oRow.all("ybtsk").value=roundV(parseFloat(ynse3)-parseFloat(ykjse3));
        }else if(fdfs=='1'){
            //��˾ȫ���
            /*Ӧ��˰���ö�=�������-����۳������£�1-˰�ʣ�*/
            var bi1 =fixBhs050150(obj);
            if(bi1!='-1'){
                //�õ������
                var rmbhj =  checkEmpty(oRow.all("rmbhj"));
                //˰��
                var sl = szsmlist[bi1][12];
                if(sl == '') sl=0;
                //����۳���
                var sskcs = szsmlist[bi1][13];
                if(sskcs == '') sskcs=0;
                //����Ӧ��˰���ö�
                oRow.all("ynssde").value=roundV((parseFloat(rmbhj)-parseFloat(sskcs))/(1-parseFloat(sl)));
            }
            /*Ӧ��˰��=Ӧ��˰���ö�*˰��-����۳���*/
            var hi = fixSysli(obj);
            if(hi!='-1'){
                //����˰��
                var sysl = szsmlist[hi][3];
                //ʹ������۳���
                var sysscks = szsmlist[hi][6];
                //Ӧ��˰���ö�
                var ynssde = checkEmpty(oRow.all("ynssde"));
                //����Ӧ��˰��
                oRow.all("ynse").value =roundV(parseFloat(ynssde)*parseFloat(sysl)-parseFloat(sysscks));
            }
        }
    }
}
//050151������
function com050151(obj){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    //�õ�˰��˰Ŀ����
    var szsmdm = oRow.all("szsmdm").value;
    //�õ�������ʽ
    var fdfs = document.forms[0].fdfs.value;
    if(szsmdm=='050151'){

        //�õ������
        var rmbhj =  checkEmpty(oRow.all("rmbhj"));
        //�����ö�
        var jfye = checkEmpty(oRow.all("jfye"));
        //�ѿۻ��ѽ�˰��
        var ykjse = checkEmpty(oRow.all("ykjse"));
        //�����۳���Ŀ���
        var fdkce = checkEmpty(oRow.all("fdkce"));
        //����Ӧ��˰���ö��ǰ������Һϼ�/�������·���������ǰ��Ӧ��˰���ö�
        //oRow.all("ynssde").value=roundV(parseFloat(rmbhj)-parseFloat(jfye)-parseFloat(fdkce));
        var iYq = getYqybjseI();
        //alert(iYq)
        if(iYq==-1) return;
        //����ǰӦ��˰���ö�
        var tzqynssde = ZRRGRSDSMX.rows[iYq].all("ynssde").value;
        if(tzqynssde=='') tzqynssde=0;
        //����ǰӦ��
        var tzqybtsk = ZRRGRSDSMX.rows[iYq].all("ybtsk").value;
        if(tzqybtsk=='') tzqybtsk=0;
        //����ǰ����Һϼ�
        var tzqrmbhj = ZRRGRSDSMX.rows[iYq].all("rmbhj").value;
        if(tzqrmbhj=='') tzqrmbhj=0;
        //����ǰӦ��˰��
        var tzqynse = ZRRGRSDSMX.rows[iYq].all("ynse").value;
        if(tzqynse=='') tzqynse=0;
        //�������·���
        var months=getBMonths(oRow.all("sdksrq").value,oRow.all("sdjsrq").value)+1;
        if(parseFloat(months)<0) months=1;
        if(fdfs=='3'){
            //2004034 Shi Yanfeng
            //һ������:(���˸���)
            //1.���ʺ��Ӧ��Ӧ��˰���ö�=(ԭ����+���ʶ�/�����漰���·�)-���ÿ۳���
            //2.���ʺ��Ӧ��Ӧ��˰��=���ʺ��Ӧ��Ӧ��˰���ö�*����˰��-����۳���
            //3.Ӧ��˰��=(���ʺ��Ӧ��Ӧ��˰��-ԭ���ʶ�Ӧ��Ӧ��˰��)*�����漰���·�
            //2004034 Shi Yanfeng
            //���˸���
            //���ʵ���������һ�е�����Һϼƣ�����˰Ŀ������Һϼ�/�������·ݣ����������������������ö�
            var sre = roundV((parseFloat(tzqrmbhj)+parseFloat(rmbhj/months))-fdkce-jfye);

            //����˰Ŀ��Ӧ��˰���ö���ʵ������
            oRow.all("ynssde").value=sre;
            //�õ���˰Ŀ��Ӧ��˰���ö��Һ�˰���������۳�����˰��
            var index = fixSysli(obj);
            if(index!='-1'){
                //����˰��
                var sl = szsmlist[index][3];
                oRow.all("sl").value =sl;
                //ʹ������۳���
                var sscks = szsmlist[index][6];
                oRow.all("sskcs").value =sscks;
                //��ʱ��Ӧ��˰�Ӧ��˰���ö��˰�ʣ�����۳�����
                var tempYnse = roundV(oRow.all("ynssde").value*sl - sscks);
                //���ʵ�Ӧ��˰�����һ�е�Ӧ��˰���ʱ��Ӧ��˰��������·ݣ�
                //oRow.all("ynse").value = roundV((tzqynse - tempYnse)*months);
                oRow.all("ynse").value = tempYnse;
                //����Ӧ������Ӧ��˰�����ǰӦ�������������·���
                //oRow.all("ybtsk").value = roundV(oRow.all("ynse").value-tzqybtsk);
                oRow.all("ybtsk").value = roundV((oRow.all("ynse").value-tzqynse)*months);
            }
        }else if(fdfs=='1'){
            //2004034 Shi Yanfeng
            //��������:(ȫ����˾����)
            //1.���ʺ��Ӧ��Ӧ��˰���ö�=[(ԭ����+���ʶ�/�����漰���·�)-���ÿ۳���-����۳���]/1-˰��
            //2.���ʺ��Ӧ��Ӧ��˰��=���ʺ��Ӧ��Ӧ��˰���ö�*����˰��-����۳���
            //3.Ӧ��˰��=(���ʺ��Ӧ��Ӧ��˰��-ԭ���ʶ�Ӧ��Ӧ��˰��)*�����漰���·�
            //2004034 Shi Yanfeng
            //��λ����
            //���ʵ���������һ�е�����Һϼƣ�����˰Ŀ������Һϼ�/�������·ݣ����������������������ö�
            var sre = roundV((parseFloat(tzqrmbhj)+parseFloat(rmbhj/months))-fdkce-jfye);
            //���ݵ��ʵ��������Ҳ���˰�����˰�ʺ�����۳���
            var bhsi = fixBhs050151(obj,sre);
            //alert(tzqrmbhj+" : "+sre)
            if(bhsi!=-1){
                //˰��
                var sl = szsmlist[bhsi][12];
                if(sl == '') sl=0;
                //����۳���
                var sskcs = szsmlist[bhsi][13];
                if(sskcs == '') sskcs=0;
                //alert(sl+" : "+sskcs)
                //����˰Ŀ��Ӧ��˰���ö�����ʵ���������۳�����/(1��˰��)
                oRow.all("ynssde").value=roundV((sre-sskcs)/(1-sl));
                //�õ���˰Ŀ��Ӧ��˰���ö��Һ�˰���������۳�����˰��
                var index = fixSysli(obj);

                if(index!='-1'){
                    //����˰��
                    var sysl = szsmlist[index][3];
                    oRow.all("sl").value =sysl;
                    //ʹ������۳���
                    var sysskcs = szsmlist[index][6];
                    oRow.all("sskcs").value =sysskcs;
                    //��ʱ��Ӧ��˰�Ӧ��˰���ö��˰�ʣ�����۳�����
                    var tempYnse = roundV(oRow.all("ynssde").value*sysl - sysskcs);
                    //���ʵ�Ӧ��˰�����һ�е�Ӧ��˰���ʱ��Ӧ��˰��������·ݣ�
                    //oRow.all("ynse").value = roundV((tzqynse - tempYnse)*months);
                    oRow.all("ynse").value = tempYnse;
                    //����Ӧ������Ӧ��˰�����ǰӦ�������������·���
                    //oRow.all("ybtsk").value = roundV(oRow.all("ynse").value-tzqybtsk);
                    //oRow.all("ybtsk").value = roundV(oRow.all("ynse").value-ykjse);
                    oRow.all("ybtsk").value = roundV((oRow.all("ynse").value-tzqynse)*months);
                }
            }
        }else if(fdfs=='2'){
            //��λ��������
            ////2004034 Shi Yanfeng
            //����:(��˾����Ϊ20%)
            //1.���ʺ��Ӧ��Ӧ��˰���ö�=[(ԭ����+���ʶ�/�����漰���·�)-���ÿ۳���-����۳���*��������]/(1-˰��*��������)
            //2.���ʺ��Ӧ��Ӧ��˰��=���ʺ��Ӧ��Ӧ��˰���ö�*����˰��-����۳���
            //3.Ӧ��˰��=(���ʺ��Ӧ��Ӧ��˰��-ԭ���ʶ�Ӧ��Ӧ��˰��)*�����漰���·�
            //2004034 Shi Yanfeng

            //���ʵ���������һ�е�����Һϼƣ�����˰Ŀ������Һϼ�/�������·ݣ����������������������ö�
            var sre = roundV((parseFloat(tzqrmbhj)+parseFloat(rmbhj/months))-fdkce-jfye);
            //���ݵ��ʵ��������Ҳ���˰�����˰�ʺ�����۳���
            var bhsi = fixBhs050151(obj,sre);
            if(bhsi!=-1){
                //˰��
                var sl = szsmlist[bhsi][12];
                if(sl == '') sl=0;
                //����۳���
                var sskcs = szsmlist[bhsi][13];
                if(sskcs == '') sskcs=0;
                //alert(sl+" : "+sskcs)
                //��������
                var fdbl = document.forms[0].dwfdbl.value;
                //����˰Ŀ��Ӧ��˰���ö�����ʵ���������۳���������������/(1��˰�ʡ���������)
                oRow.all("ynssde").value=roundV((sre-sskcs*(fdbl/100))/(1-sl*(fdbl/100)));
                //�õ���˰Ŀ��Ӧ��˰���ö��Һ�˰���������۳�����˰��
                var index = fixSysli(obj);
                if(index!='-1'){
                    //����˰��
                    var sysl = szsmlist[index][3];
                    oRow.all("sl").value =sysl;
                    //ʹ������۳���
                    var sysskcs = szsmlist[index][6];
                    oRow.all("sskcs").value =sysskcs;
                    //��ʱ��Ӧ��˰�Ӧ��˰���ö��˰�ʣ�����۳�����
                    var tempYnse = roundV(oRow.all("ynssde").value*sysl - sysskcs);
                    //���ʵ�Ӧ��˰�����һ�е�Ӧ��˰���ʱ��Ӧ��˰��������·ݣ�
                    //oRow.all("ynse").value = roundV((tzqynse - tempYnse)*months);
                    oRow.all("ynse").value = tempYnse;

                    //����Ӧ������Ӧ��˰�����ǰӦ�������������·���
                    //oRow.all("ybtsk").value = roundV(oRow.all("ynse").value-tzqybtsk);
                    //oRow.all("ybtsk").value = roundV(oRow.all("ynse").value-ykjse);
                    oRow.all("ybtsk").value = roundV((oRow.all("ynse").value-tzqynse)*months);
                }
            }
        }



    }
}
//��ǰ�·ݹ��ʵ���������Ӧ����˰�����������Ӧ��˰���������ǰ��ʵ��Ӧ��˰����������·���
//tzdyyn ��������Ӧ��
//����ǰ��ʵ��Ӧ��˰�����ǰһ��05011%��Ӧ��˰������·ݵ���05011%�������ڼ�
function getYqybjse(tzdyyn){
    var war = '��¼�����ǰ�Ĺ���н��';
    if(ZRRGRSDSMX.rows.length<=3){
        //ֻ��һ���걨��Ϣ��ʾ¼�����ǰ�Ĺ���н��
        alert(war);
        return 0;
    }
    //�����Ƿ���05011%���걨
    for(var i=0;i<ZRRGRSDSMX.rows.length;i++){
        if(ZRRGRSDSMX.rows[i].all("szsmdm")){
            //���걨����
            //ƥ��˰��˰Ŀ�����Ƿ�Ϊ05011%
            var szsmdm = ZRRGRSDSMX.rows[i].all("szsmdm").value;
            var szsmSub = szsmdm.substr(0,5);
            if(szsmSub == '05011'){
                //����ǰ��ʵ��Ӧ��˰��
                var yynse=ZRRGRSDSMX.rows[i].all("ynse").value;
                if(yynse=='') yynse=0;
                //�������·���
                //var months=getBMonths(ZRRGRSDSMX.rows[i].all("sdksrq").value,ZRRGRSDSMX.rows[i].all("sdjsrq").value);
                //if(parseFloat(months)<0) return 0;
                //var yqybjse = roundV(parseFloat(tzdyyn)-parseFloat(yynse))*(parseFloat(months)+1);
                var yqybjse = roundV(parseFloat(tzdyyn)-parseFloat(yynse));
                return yqybjse;
            }
        }
    }
    alert(war);
    return 0;
}
//����ǰ��ʵ��Ӧ��˰�����ǰһ��05011%��Ӧ��˰������·ݵ���05011%�������ڼ�
//
function getYqybjseI(){
    var war = '��¼�����ǰ�Ĺ���н��';
    if(ZRRGRSDSMX.rows.length<=3){
        //ֻ��һ���걨��Ϣ��ʾ¼�����ǰ�Ĺ���н��
        alert(war);
        return -1;
    }
    //�����Ƿ���05011%���걨
    for(var i=0;i<ZRRGRSDSMX.rows.length;i++){
        if(ZRRGRSDSMX.rows[i].all("szsmdm")){
            //���걨����
            //ƥ��˰��˰Ŀ�����Ƿ�Ϊ05011%
            var szsmdm = ZRRGRSDSMX.rows[i].all("szsmdm").value;
            var szsmSub = szsmdm.substr(0,5);
            if(szsmSub == '05011'){
                //�����б�
                return i;
            }
        }
    }
    //δ�ҵ�05011
    alert(war);
    return -1;
}
//050152����ְ
function com050152(obj){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    //�õ�˰��˰Ŀ����
    var szsmdm = oRow.all("szsmdm").value;
    //�õ�������ʽ
    var fdfs = document.forms[0].fdfs.value;
    if(szsmdm=='050152'){
        //�õ������
        var rmbhj =  checkEmpty(oRow.all("rmbhj"));
        //�����ö�
        var jfye = checkEmpty(oRow.all("jfye"));
        //�ѿۻ��ѽ�˰��
        var ykjse = checkEmpty(oRow.all("ykjse"));
        //�����룽����ְ��/(ʵ���ڻ�����ʱ��/365)  ����ʱ���ڻ�����ʱ�䣽���ý�ֹ���ڣ�������ʼ���ڣ������������յ������ϵ������������£����죬�������ꣲ����ȣ�
        //�ڻ���������
        var zhgzts = getBDays(oRow.all("sdksrq").value,oRow.all("sdjsrq").value)+1;

        var ysr = roundV(parseFloat(rmbhj)/(parseFloat(zhgzts)/365));
        if(fdfs=='3'){
            //���˸���
            //Ӧ��˰���ö������������������ÿ۳���׼
            oRow.all("ynssde").value=roundV(parseFloat(ysr)-parseFloat(jfye));
            //��Ӧ��˰��������룭���ÿ۳���׼����˰�ʣ�����۳���
            //��ְ�ѹ���Ӧ����˰���Ӧ��˰�����ʵ���ڻ�����ʱ��/365��
            var index = fixSysli(obj);
            if(index!='-1'){
                //����˰��
                var sl = szsmlist[index][3];
                oRow.all("sl").value =sl;
                //ʹ������۳���
                var sskcs = szsmlist[index][6];
                oRow.all("sskcs").value =sskcs;
                var yynse = roundV((parseFloat(ysr)-jfye)*parseFloat(sl)-parseFloat(sskcs));
                //
                oRow.all("ynse").value = roundV(parseFloat(yynse)*(parseFloat(zhgzts)/365));
                //Ӧ��˰��=Ӧ��˰��-�ѿۻ��ѽ�˰��
                oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
            }
        }else if(fdfs=='1'){
            //��λ����
            //���ݵ��ʵ��������Ҳ���˰�����˰�ʺ�����۳���
            var tempYsr = ysr-jfye;
            var bhsi = fixBhs050151(obj,tempYsr);
            if(bhsi!=-1){
                //˰��
                var sl = szsmlist[bhsi][12];
                if(sl == '') sl=0;
                //����۳���
                var sskcs = szsmlist[bhsi][13];
                if(sskcs == '') sskcs=0;
                ////˰Ŀ��Ӧ��˰���ö���������-�����ö����۳�����/(1��˰��)
                oRow.all("ynssde").value=roundV((ysr-jfye-sskcs)/(1-sl));
                var index = fixSysli(obj);
                if(index!='-1'){
                    //����˰��
                    var sl = szsmlist[index][3];
                    oRow.all("sl").value =sl;
                    //ʹ������۳���
                    var sskcs = szsmlist[index][6];
                    oRow.all("sskcs").value =sskcs;
                    //��Ӧ��˰���Ӧ��˰���ö��˰�ʣ�����۳���
                    var yynse = roundV(parseFloat(oRow.all("ynssde").value)*parseFloat(sl)-parseFloat(sskcs));

                    //alert(zhgzts+" : "+yynse)
                    oRow.all("ynse").value = roundV(parseFloat(yynse)*(parseFloat(zhgzts)/365));
                    //Ӧ��˰��=Ӧ��˰��-�ѿۻ��ѽ�˰��
                    oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
                }

            }
        }else if(fdfs=='2'){
            //��λ��������
            //���ݵ��ʵ��������Ҳ���˰�����˰�ʺ�����۳���
            var tempYsr = ysr-jfye;
            var bhsi = fixBhs050151(obj,tempYsr);
            if(bhsi!=-1){
                //˰��
                var sl = szsmlist[bhsi][12];
                if(sl == '') sl=0;
                //����۳���
                var sskcs = szsmlist[bhsi][13];
                if(sskcs == '') sskcs=0;
                //��������
                var fdbl = document.forms[0].dwfdbl.value;
                ////˰Ŀ��Ӧ��˰���ö�����ʵ������-�����ö����۳���*�������ʣ�/(1��˰��*��������)
                oRow.all("ynssde").value=roundV((ysr-jfye-sskcs*(fdbl/100))/(1-sl*(fdbl/100)));
                var index = fixSysli(obj);
                if(index!='-1'){
                    //����˰��
                    var sl = szsmlist[index][3];
                    oRow.all("sl").value =sl;
                    //ʹ������۳���
                    var sskcs = szsmlist[index][6];
                    oRow.all("sskcs").value =sskcs;
                    //��Ӧ��˰���Ӧ��˰���ö��˰�ʣ�����۳���
                    var yynse = roundV(parseFloat(oRow.all("ynssde").value)*parseFloat(sl)-parseFloat(sskcs));
                    oRow.all("ynse").value = roundV(parseFloat(yynse)*(parseFloat(zhgzts)/365));
                    //Ӧ��˰��=Ӧ��˰��-�ѿۻ��ѽ�˰��
                    oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
                }

            }
        }


    }
}
//0503���񱨳�
function com0503(obj){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    //�õ�˰��˰Ŀ����
    var szsmdm = oRow.all("szsmdm").value;
    var szsmSub = szsmdm.substr(0,4);
    //�õ�������ʽ
    var fdfs = document.forms[0].fdfs.value;
    if(szsmSub=='0503'){
        //�õ�0503���񱨳�����˰�ʺ�����۳������б�index

        //�õ������
        var rmbhj =  checkEmpty(oRow.all("rmbhj"));
        //�����ö�
        var jfye = checkEmpty(oRow.all("jfye"));
        //�ѿۻ��ѽ�˰��
        var ykjse = checkEmpty(oRow.all("ykjse"));
        if(fdfs=='3'){
            //һ�����˸���˰���
            if(parseFloat(rmbhj)<=1000){
                /*�����1000Ԫ���µģ�Ӧ��˰��=�����*3%*/
                oRow.all("ynse").value = roundV(parseFloat(rmbhj)*0.03);
            }else if(4000>=parseFloat(rmbhj)){
                /*�����1000-4000Ԫ��*/
                //Ӧ��˰���ö�=����������<=4000��-��׼�۳����ã�����800��
                oRow.all("ynssde").value=roundV(parseFloat(rmbhj)-parseFloat(jfye));

                //Ӧ��˰��=Ӧ��˰���ö�*20%
                oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2);
                //Ӧ��˰��=Ӧ��˰��-�ѿۻ��ѽ�˰�ע��ָ�����ѿ۽ɻ��ѽ���˰�
                oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
            }else if(20000>=parseFloat(rmbhj)){
                //4000-20000
                //�����ö� = �����*20%
                oRow.all("jfye").value=roundV(parseFloat(rmbhj)*0.2);
                //Ӧ��˰���ö�=����������>4000��-��׼�۳����ã����������*20%��
                oRow.all("ynssde").value=roundV(parseFloat(rmbhj)-parseFloat(rmbhj)*0.2);
                //Ӧ��˰��=Ӧ��˰���ö�*˰�ʣ�20��������˰�ʱ�--���񱨳����ã�-����۳�����800������˰�ʱ�--���񱨳����ã�
                //oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2 -800);
                oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2 );
                //Ӧ��˰��=Ӧ��˰��-�ѿۻ��ѽ�˰�ע��ָ�����ѿ۽ɻ��ѽ���˰�
                oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
            }else if(50000>=parseFloat(rmbhj)){
                //�ӳ����գ�
                //�����20000-50000Ԫ�ģ�
                //Ӧ��˰��=[�����*��1-20%��]*30%-2000
                oRow.all("ynse").value = roundV(parseFloat(rmbhj)*0.8*0.3 - 2000);
            }else if(50000<parseFloat(rmbhj)){
                //������50000Ԫ��
                //Ӧ��˰��=[�����*��1-20%��]*40%-7000
                oRow.all("ynse").value = roundV(parseFloat(rmbhj)*0.8*0.4 - 7000);
            }
            //����ƥ������˰��˰Ŀ
            fixSysl0503(obj);

        }else if(fdfs=='1'){
            //��˾����˰���
            if(parseFloat(rmbhj)<=3360){
                //����˰�����3360Ԫ��
                //Ӧ��˰���ö�=������˰�����-800���£�1-20%��
                oRow.all("ynssde").value=roundV((parseFloat(rmbhj)-800)/0.8);
                //Ӧ��˰��=Ӧ��˰���ö�*20%
                oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2);
            }else if(parseFloat(rmbhj)<=21000){
                //3360<����˰����� 21000 Ԫ��
                //Ӧ��˰���ö�=����˰����*20��21
                oRow.all("ynssde").value=roundV((parseFloat(rmbhj)*20)/21);
                //Ӧ��˰��=Ӧ��˰���ö�*20%
                oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2);
            }else if(parseFloat(rmbhj)<=49500){
                //21000<����˰����� 49500 Ԫ��
                //Ӧ��˰���ö�=������˰����-2000��*20��19
                oRow.all("ynssde").value=roundV(((parseFloat(rmbhj)-2000)*20)/19);
                //Ӧ��˰��=Ӧ��˰���ö�*30%-2000
                oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.3-2000);

            }else if(parseFloat(rmbhj)>49500){
                //����˰���볬��495000  Ԫ��
                //Ӧ��˰���ö�=������˰����-7000��*20��17
                oRow.all("ynssde").value=roundV(((parseFloat(rmbhj)-7000)*20)/17);
                //Ӧ��˰��=Ӧ��˰���ö�*40%-7000
                oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.4-7000);
            }
            //����ƥ������˰��˰Ŀ
            fixSysl0503(obj);
        }

    }
}
//�õ�0503���񱨳�����˰�ʺ�����۳������б�index
function fixSysl0503(obj){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    var ynssde = oRow.all("ynssde").value;
    for(i=0;i<szsmlist.length;i++){
        var szsmSub = szsmlist[i][0].substr(0,4);
    //�ҵ�����Ӧ��˰��ʼ��С��Ӧ��˰��ֹ����˰��˰Ŀ
    //if(szsmSub=="0503") alert(szsmlist[i][0]+" : "+ynssde+" : "+szsmlist[i][4]+" : "+szsmlist[i][5])
        if(szsmSub=="0503" && parseFloat(szsmlist[i][4])<parseFloat(ynssde) && parseFloat(szsmlist[i][5])>=parseFloat(ynssde)){

            //����˰��˰Ŀ����
            oRow.all("szsmdm").value = szsmlist[i][0];
            //����˰����������
            //oRow.all("sdksrq").value = szsmlist[i][7];
            //oRow.all("sdjsrq").value = szsmlist[i][8];
            //����˰��
            //oRow.all("sl").value = szsmlist[i][3];
            //��������۳���
            //oRow.all("sskcs").value = szsmlist[i][6];
            //������ǰ˰����������
            var oks = oRow.all("sdksrq").value;
            var ojs = oRow.all("sdjsrq").value;
            var ojfye = oRow.all("jfye").value;

            resetRow("szsmdm","ZRRGRSDSMX",oRow.all("szsmdm"));
            //����˰����������
            oRow.all("sdksrq").value = oks;
            oRow.all("sdjsrq").value = ojs;
            oRow.all("jfye").value = ojfye;
            //oRow.all("szsmdm").select();
            return i;
        }
    }
    //δ�ҵ��ʺ�˰��
    return -1;
}
//�õ�0503���񱨳겻��˰�����˰��˰Ŀ����index
function fixBhs0503(obj){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    //�õ������
    var rmbhj =  checkEmpty(oRow.all("rmbhj"));
    //�����ö�
    var jfye = checkEmpty(oRow.all("jfye"));
    //���㲻��˰����
    var bhs = roundV(parseFloat(rmbhj)-parseFloat(jfye));
    for(i=0;i<szsmlist.length;i++){
        var szsmSub = szsmlist[i][0].substr(0,4);
    //�ҵ����ڲ���˰������ʼ��ʼ��С�ڲ���˰������ʼ��ֹ����˰��˰Ŀ

        if(szsmSub=="0503" && parseFloat(szsmlist[i][10])<parseFloat(bhs) && parseFloat(szsmlist[i][11])>=parseFloat(bhs)){
            return i;
        }
    }
    //δ��ѯ����Ӧ�Ĳ���˰�����˰��˰Ŀ
    return -1;
}
//0504�������
function com0504(obj){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    //�õ�˰��˰Ŀ����
    var szsmdm = oRow.all("szsmdm").value;
    var szsmSub = szsmdm.substr(0,4);
    //�õ�������ʽ
    var fdfs = document.forms[0].fdfs.value;
    if(szsmSub=='0504'){
        //�õ������
        var rmbhj =  checkEmpty(oRow.all("rmbhj"));
        //�����ö�
        var jfye = checkEmpty(oRow.all("jfye"));
        //�ѿۻ��ѽ�˰��
        var ykjse = checkEmpty(oRow.all("ykjse"));
        if(parseFloat(rmbhj)<=4000){
            //Ӧ��˰���ö�=���������<=4000ע�����������ÿ�γ��桢����ȡ�õ�����Ϊһ�Σ�-��׼�۳����ã�����800��
            oRow.all("ynssde").value=roundV(parseFloat(rmbhj)-parseFloat(jfye));
            //Ӧ��˰��=Ӧ��˰���ö�*20%*��1-30%��
            oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2*0.7);
            //Ӧ��˰��=Ӧ��˰��-�ѿۻ��ѽ�˰��
            oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
        }else if(parseFloat(rmbhj)>4000){
            //�����ö� = �����*20%
            oRow.all("jfye").value=roundV(parseFloat(rmbhj)*0.2);
            //Ӧ��˰���ö�=���������>4000ע�����������ÿ�γ��桢����ȡ�õ�����Ϊһ�Σ�-��׼�۳����ã����������*20%��
            oRow.all("ynssde").value=roundV(parseFloat(rmbhj)-parseFloat(rmbhj)*0.2);
            //Ӧ��˰��=Ӧ��˰���ö�*20%*��1-30%��
            oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2*0.7);
            //Ӧ��˰��=Ӧ��˰��-�ѿۻ��ѽ�˰�ע��ָ�����ѿ۽ɻ��ѽ���˰�
            oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
        }
    }
}
//0505�������
function com0505(obj){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    //�õ�˰��˰Ŀ����
    var szsmdm = oRow.all("szsmdm").value;
    var szsmSub = szsmdm.substr(0,4);
    //�õ�������ʽ
    var fdfs = document.forms[0].fdfs.value;
    if(szsmSub=='0505'){
        //�õ������
        var rmbhj =  checkEmpty(oRow.all("rmbhj"));
        //�����ö�
        var jfye = checkEmpty(oRow.all("jfye"));
        //�ѿۻ��ѽ�˰��
        var ykjse = checkEmpty(oRow.all("ykjse"));
        if(parseFloat(rmbhj)<=4000){
            //Ӧ��˰���ö�=���������<=4000ע������Ȩʹ�÷����ã���һ������Ȩ��һ�����ʹ����ȡ�õ�����Ϊһ�Σ�-��׼�۳����ã�����800��
            oRow.all("ynssde").value=roundV(parseFloat(rmbhj)-parseFloat(jfye));
            //Ӧ��˰��=Ӧ��˰���ö�*20%
            oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2);
            //Ӧ��˰��=Ӧ��˰��-�ѿۻ��ѽ�˰��
            oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
        }else if(parseFloat(rmbhj)>4000){

            //�����ö� = �����*20%
            oRow.all("jfye").value=roundV(parseFloat(rmbhj)*0.2);
        //Ӧ��˰���ö�=���������>4000ע������Ȩʹ�÷����ã���һ������Ȩ��һ�����ʹ����ȡ�õ�����Ϊһ�Σ�-��׼�۳����ã����������*20%��
            oRow.all("ynssde").value=roundV(parseFloat(rmbhj)-parseFloat(rmbhj)*0.2);
            //Ӧ��˰��=Ӧ��˰���ö�*20%
            oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2);
            //Ӧ��˰��=Ӧ��˰��-�ѿۻ��ѽ�˰�ע��ָ�����ѿ۽ɻ��ѽ���˰�
            oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
        }
    }
}
//0506��Ϣ����Ϣ����������
function com0506(obj){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    //�õ�˰��˰Ŀ����
    var szsmdm = oRow.all("szsmdm").value;
    var szsmSub = szsmdm.substr(0,4);
    //�õ�������ʽ
    var fdfs = document.forms[0].fdfs.value;
    if(szsmSub=='0506'){
        //�õ������
        var rmbhj =  checkEmpty(oRow.all("rmbhj"));
        //�����ö�
        var jfye = checkEmpty(oRow.all("jfye"));
        //�ѿۻ��ѽ�˰��
        var ykjse = checkEmpty(oRow.all("ykjse"));
        //�����ö� = 0
        oRow.all("jfye").value=0;
        //Ӧ��˰���ö�=���������ע����Ϣ����Ϣ���������ã���֧����Ϣ����Ϣ������ʱȡ�õ�����Ϊһ�Σ�-��׼�۳����ã�����0��
        oRow.all("ynssde").value=roundV(parseFloat(rmbhj));
        //Ӧ��˰��=Ӧ��˰���ö�*20%
        oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2);
        //Ӧ��˰��=Ӧ��˰��-�ѿۻ��ѽ�˰��
        oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));

    }
}
//050702һ��Ʋ�����
function com050702(obj){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    //�õ�˰��˰Ŀ����
    var szsmdm = oRow.all("szsmdm").value;
    var szsmSub = szsmdm.substr(0,4);
    //�õ�������ʽ
    var fdfs = document.forms[0].fdfs.value;
    if(szsmdm=='050702'){
        //�õ������
        var rmbhj =  checkEmpty(oRow.all("rmbhj"));
        //�����ö�
        var jfye = checkEmpty(oRow.all("jfye"));
        //�ѿۻ��ѽ�˰��
        var ykjse = checkEmpty(oRow.all("ykjse"));
        if(parseFloat(rmbhj)<=4000){			//Ӧ��˰���ö�=���������<=4000ע������Ȩʹ�÷����ã���һ������Ȩ��һ�����ʹ����ȡ�õ�����Ϊһ�Σ�-��׼�۳����ã�����800��
            oRow.all("ynssde").value=roundV(parseFloat(rmbhj)-parseFloat(jfye));
            //Ӧ��˰��=Ӧ��˰���ö�*20%
            oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2);
            //Ӧ��˰��=Ӧ��˰��-�ѿۻ��ѽ�˰��
            oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
        }else if(parseFloat(rmbhj)>4000){

            //�����ö� = �����*20%
            oRow.all("jfye").value=roundV(parseFloat(rmbhj)*0.2);	//Ӧ��˰���ö�=���������>4000ע������Ȩʹ�÷����ã���һ������Ȩ��һ�����ʹ����ȡ�õ�����Ϊһ�Σ�-��׼�۳����ã����������*20%��
            oRow.all("ynssde").value=roundV(parseFloat(rmbhj)-parseFloat(rmbhj)*0.2);
            //Ӧ��˰��=Ӧ��˰���ö�*20%
            oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2);
            //Ӧ��˰��=Ӧ��˰��-�ѿۻ��ѽ�˰�ע��ָ�����ѿ۽ɻ��ѽ���˰�
            oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
        }
    }
}
//050701����˽������
function com050701(obj){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    //�õ�˰��˰Ŀ����
    var szsmdm = oRow.all("szsmdm").value;
    var szsmSub = szsmdm.substr(0,4);
    //�õ�������ʽ
    var fdfs = document.forms[0].fdfs.value;
    if(szsmdm=='050701'){
        //�õ������
        var rmbhj =  checkEmpty(oRow.all("rmbhj"));
        //�����ö�
        var jfye = checkEmpty(oRow.all("jfye"));
        //�ѿۻ��ѽ�˰��
        var ykjse = checkEmpty(oRow.all("ykjse"));
        if(parseFloat(rmbhj)<=4000){			//Ӧ��˰���ö�=���������<=4000ע������Ȩʹ�÷����ã���һ������Ȩ��һ�����ʹ����ȡ�õ�����Ϊһ�Σ�-��׼�۳����ã�����800��
            oRow.all("ynssde").value=roundV(parseFloat(rmbhj)-parseFloat(jfye));
            //Ӧ��˰��=Ӧ��˰���ö�*10%
            oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.1);
            //Ӧ��˰��=Ӧ��˰��-�ѿۻ��ѽ�˰��
            oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
        }else if(parseFloat(rmbhj)>4000){

        //�����ö� = �����*20%
            oRow.all("jfye").value=roundV(parseFloat(rmbhj)*0.2);	//Ӧ��˰���ö�=���������>4000ע������Ȩʹ�÷����ã���һ������Ȩ��һ�����ʹ����ȡ�õ�����Ϊһ�Σ�-��׼�۳����ã����������*20%��
            oRow.all("ynssde").value=roundV(parseFloat(rmbhj)-parseFloat(rmbhj)*0.2);
            //Ӧ��˰��=Ӧ��˰���ö�*10%
            oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.1);
            //Ӧ��˰��=Ӧ��˰��-�ѿۻ��ѽ�˰�ע��ָ�����ѿ۽ɻ��ѽ���˰�
            oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
        }
    }
}
//0508�Ʋ�ת������
function com0508(obj){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    //�õ�˰��˰Ŀ����
    var szsmdm = oRow.all("szsmdm").value;
    var szsmSub = szsmdm.substr(0,4);
    //�õ�������ʽ
    var fdfs = document.forms[0].fdfs.value;
    if(szsmSub=='0508'){
        //�õ������
        var rmbhj =  checkEmpty(oRow.all("rmbhj"));
        //�����ö�
        var jfye = checkEmpty(oRow.all("jfye"));
        //�ѿۻ��ѽ�˰��
        var ykjse = checkEmpty(oRow.all("ykjse"));
        //�����ö� = 0
        oRow.all("jfye").value=0;
        //Ӧ��˰���ö�=���������ע����Ϣ����Ϣ���������ã���֧����Ϣ����Ϣ������ʱȡ�õ�����Ϊһ�Σ�-��׼�۳����ã�����0��
        oRow.all("ynssde").value=roundV(parseFloat(rmbhj));
        //Ӧ��˰��=Ӧ��˰���ö�*20%
        oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2);
        //Ӧ��˰��=Ӧ��˰��-�ѿۻ��ѽ�˰��
        oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));

    }
}
//0509żȻ����
function com0509(obj){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    //�õ�˰��˰Ŀ����
    var szsmdm = oRow.all("szsmdm").value;
    var szsmSub = szsmdm.substr(0,4);
    //�õ�������ʽ
    var fdfs = document.forms[0].fdfs.value;
    if(szsmSub=='0509'){
        //�õ������
        var rmbhj =  checkEmpty(oRow.all("rmbhj"));
        //�����ö�
        var jfye = checkEmpty(oRow.all("jfye"));
        //�ѿۻ��ѽ�˰��
        var ykjse = checkEmpty(oRow.all("ykjse"));
        //�����ö� = 0
        oRow.all("jfye").value=0;
        //Ӧ��˰���ö�=���������ע����Ϣ����Ϣ���������ã���֧����Ϣ����Ϣ������ʱȡ�õ�����Ϊһ�Σ�-��׼�۳����ã�����0��
        oRow.all("ynssde").value=roundV(parseFloat(rmbhj));
        //Ӧ��˰��=Ӧ��˰���ö�*20%
        oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2);
        //Ӧ��˰��=Ӧ��˰��-�ѿۻ��ѽ�˰��
        oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));

    }
}
//0510������Ժ��������ȷ����˰����������
function com0510(obj){
    //�õ���ǰ��
    var oRow = getObjRow(obj);
    //�õ�˰��˰Ŀ����
    var szsmdm = oRow.all("szsmdm").value;
    var szsmSub = szsmdm.substr(0,4);
    //�õ�������ʽ
    var fdfs = document.forms[0].fdfs.value;
    if(szsmSub=='0510'){
        //�õ������
        var rmbhj =  checkEmpty(oRow.all("rmbhj"));
        //�����ö�
        var jfye = checkEmpty(oRow.all("jfye"));
        //�ѿۻ��ѽ�˰��
        var ykjse = checkEmpty(oRow.all("ykjse"));
        //�����ö� = 0
        oRow.all("jfye").value=0;
        //Ӧ��˰���ö�=���������ע����Ϣ����Ϣ���������ã���֧����Ϣ����Ϣ������ʱȡ�õ�����Ϊһ�Σ�-��׼�۳����ã�����0��
        oRow.all("ynssde").value=roundV(parseFloat(rmbhj));
        //Ӧ��˰��=Ӧ��˰���ö�*20%
        oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2);
        //Ӧ��˰��=Ӧ��˰��-�ѿۻ��ѽ�˰��
        oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));

    }
}

function bzdmClass(){
  this.bzlist_fields="";
    this.bzlist="";
    this.bzdm_value="";
    this.bzdm_name="";
    this.bzdm_value_all="";
    this.bzdm_name_all="";
    this.bzdm_name_del="";
    this.bzdm_value_del="";
    this.row="";
    return this;
}
function showbzdm(obj){
    if(window.event.keyCode!=120) return;
  var dialogClass=new bzdmClass();
    dialogClass["bzlist_fields"]=bzlist_fields;
    dialogClass["bzlist"]=bzlist;
    dialogClass["bzdm_value"]=bzdm_value;
    dialogClass["bzdm_name"]=bzdm_name;
    dialogClass["bzdm_value_all"]=bzdm_value_all;
    dialogClass["bzdm_name_all"]=bzdm_name_all;
    dialogClass["bzdm_name_del"]=bzdm_name_del;
    dialogClass["bzdm_value_del"]=bzdm_value_del;
    //dialogClass["jsArr_BZDMMX"]=jsArr_BZDMMX;
    //dialogClass["BZDMMX_list"]=BZDMMX_list;
    dialogClass["row"]=getObjRow(obj);
    var value=window.showModalDialog("bzdm.html",dialogClass,"");
    if(value!=null){
    obj.value=value;
    }
    hjRMB(obj);
}
//var vvvv=Math.round(1000.4)
//alert(vvvv)
</script>
</body>
</html:html>
