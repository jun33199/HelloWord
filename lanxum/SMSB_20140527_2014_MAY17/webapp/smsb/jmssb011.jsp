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
<title>����˰�걨��</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/DTable.js"></script>
<script language=JavaScript src="../js/zhsb.js"></script>
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/function.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/Bolan.js"></script>
<script language=JavaScript src="../js/MathString.js"></script>
<script language=JavaScript src="../js/Stack.js"></script>
<script language=JavaScript src="../js/InputSelect.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
<%@ include file="include/header.jsp" %>
<html:form action="/webapp/smsb/jmssbAction.do" method="POST">
<html:hidden property="actionType"/>
<html:hidden property="swjgzzjgdm"/>
<html:hidden property="fsdm"/>
<html:hidden property="cjrq"/>
<html:hidden property="lrr"/>
<html:hidden property="lrrq"/>
<html:hidden property="jzbz"/>
<html:hidden property="nd"/>
<html:hidden property="iszhsb"/>
<html:hidden property="qxdm"/>

<INPUT TYPE="hidden" NAME="szsmdm_focus">
<INPUT TYPE="hidden" NAME="jmxmjdm_focus">
<TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9' onkeydown="jsjdmQuery()">
<tr>
<td valign="top"> <br>
    <table align="center" cellspacing="0" class="table-99">
    <tr>
       <td class="1-td1">�����еط�˰��ּ���˰�걨��</td>
    </tr>
    <tr>
       <td class="1-td2"> <br>

       <table cellspacing="0" class="table-99">
       <tr class="black9">
            <td nowrap align="left">�걨���ڣ�
               <html:text property="sbrq" maxlength="8" size="12"/>
            </td>
            <td align="center" nowrap>
                <div align="right">��λ��Ԫ</div>
            </td>
       </tr>
       </table>

       <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
        <tr class="black9">
         <td nowrap class="2-td2-t-left"><div align="right">˰����������&nbsp;&nbsp;</div></td>
         <td nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;
                    <html:text property="jsjdm" size="8" maxlength="8" onchange="doQuery(); return false;" />
                  </div></td>
         <td nowrap class="2-td2-t-left"><div align="right">��λ���ƣ����£�&nbsp;&nbsp;</div></td>
         <td nowrap class="2-td2-t-center"><div align="left">&nbsp;&nbsp;<ttsoft:write name="jmssbForm" property="nsrmc"/></div></td>
        </tr>
        <tr>
        	<td nowrap class="2-td2-t-center" colspan="4">
        	�������ۣ�Ӫҵ����<font color="red">*</font>��<html:text property="dqxse" size="17" maxlength="18" onkeydown="if(event.keyCode==13)return false;" styleClass="inputalignright"></html:text>Ԫ�����������ܶ�<font color="red">*</font>��<html:text property="dqlrze" size="17" onkeydown="if(event.keyCode==13)return false;" styleClass="inputalignright"></html:text>Ԫ����ҵ����<font color="red">*</font>�� <html:text property="qyrs" size="17" onkeydown="if(event.keyCode==13)return false;" styleClass="inputalignright"></html:text>�� &nbsp;&nbsp;���У����������Ա<font color="red">*</font>��  <html:text property="azrs" size="17" maxlength="18" onkeydown="if(event.keyCode==13)return false;"  styleClass="inputalignright"></html:text>�ˡ�
        	</td>
        </tr>
       </table>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99" id="JM"  onkeydown='return dokeydown(this.id);'  onmouseup='return dokeyUp(this.id)'>
	<ttsoft:smsbtable form="jmssbForm" property="dataList" tableId="JM" hasTitle="true"/><DIV id=divSfTemp></DIV>

        <tr>
        	<td nowrap class="2-td2-left" colspan="11">�ϼ�</td>
	        <td nowrap class="2-td2-center" align="center">
			<input type="text" name="hj" class="inbright "  readOnly tabIndex="-1"/>
		</td>
        </tr>
       </table>

 	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
        <tr>
          	<td nowrap  width="10%" class="2-td2-left">ע��</td>
          	<td nowrap  width="90%" class="2-td2-center"><div  align="left">&nbsp;
			<font color="red"><ttsoft:write name="jmssbForm" property="warnStr" />
			<html:hidden property="nsrmc"/></font></div>
		</td>
        </tr>
  	</table><br>


	<div align="center">
         <input type="image" accesskey="a" tabIndex="-1" onclick="javascript:if(document.forms[0].jsjdm.value=='' || document.forms[0].nsrmc.value==''){ return false;}else{addRow('JM'); return false;}" onMouseOver="MM_swapImage('tianjia','','<%=static_contextpath%>images/zj-a2.jpg',1)" onMouseOut="MM_swapImgRestore()"  src="<%=static_contextpath%>images/zj-a1.jpg" width="79" height="22" id="tianjia" alt="����" style="cursor:hand"/>
              &nbsp;&nbsp;&nbsp;&nbsp;  <input type="image" accesskey="s" tabIndex="-1"  onClick="doSave(); return false;" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/bc-s1.jpg" alt="����"  width="79" height="22" id="baocun" style="cursor:hand" >
                  &nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="d" tabIndex="-1" onClick="javascript:deleteRow('JM',null); return false;"  onMouseOver="MM_swapImage('sc2','','<%=static_contextpath%>images/sc-d2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="ɾ��" id="sc2" src="<%=static_contextpath%>images/sc-d1.jpg" width="79" height="22">
                   &nbsp;&nbsp;&nbsp;&nbsp;<input type="image" accesskey="x" tabIndex="-1"  onClick="doDelete(); return false;"onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg ',1)" onMouseOut="MM_swapImgRestore()" alt="���" src="<%=static_contextpath%>images/qbsc-x1.jpg" width="79" height="22" id="shanchu" style="cursor:hand">
                  &nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="c" tabIndex="-1" onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor:hand">
        </div><br>

      	</td>
      </tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<hr width="100%" size="1"></td>
						<td width="99" align="center" class="black9"><strong><font
								color="#0000FF">ע �� �� ��</font> </strong></td>
						<td>
							<hr width="100%" size="1"></td>
					</tr>
				</table>
				<table width="100%" border="1" align="center" cellpadding="1"
					cellspacing="1" bordercolor="#FFFFFF" class="black9">
					<tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
						<td>&nbsp;&nbsp;<font color="red">1.�����������Ա����дҪ�󣺴˴���д��������ʿ�����������������תҵ�ɲ���֧�ֺʹٽ���ҵ���м��ˡ�Ա���Ƽ�������Ա��������������˰�����߹涨������ҵ���ö���˰���Ż����ߣ�����ѡ�����Ż����ߣ��������ظ����ܣ����ۼ�ִ�У����������������ҵ�밴����ѡ����Ż����߶�Ӧ��д����������</font> <br>
						    &nbsp;&nbsp;<font color="red">2.�������۶�����д����ʵ���������룬���������ܶ�����д�����ۼ�����</font> <br></td>
					</tr>
				</table></td>
		</tr>
      </table>
			<div id="szsmdm_epodDateLayer" style="position: absolute; width: 142; height: 166; z-index: 9998; display: none" onclick="this.style.display='none'"><select id=sel size="11" onclick='selectClick("szsmdm","JM")' onkeyup='selectMove("szsmdm","JM")' onfocusout='selectClick("szsmdm","JM")'></select></div>
			<div id="jmxmjdm_epodDateLayer" style="position: absolute; width: 142; height: 166; z-index: 9998; display: none" onclick="this.style.display='none'"><select id=sel size="11" onclick='selectClick1("jmxmjdm","JM")' onkeyup='selectMove("jmxmjdm","JM")' onfocusout='selectClick1("jmxmjdm","JM")'></select></div>
			<div id="jmxmjdm_showText" style="position: absolute; width: 200; height: 100; z-index: 9998; display: none" onclick="this.style.display='none'" ></div>
			</html:form>
      <br>
<%@ include file="include/footer.jsp" %>
    </td>
 </tr>
</table>
<script language="JavaScript">

function fnOnLoad()

{

    document.forms[0].jsjdm.focus();
}


hjArray[0]=new Array("jmse","hj","JM");

computeSameSum("jmse","hj");

<ttsoft:write name="jmssbForm" property="scriptStr" filter="false"/>
var JM_list=new DTable(JM,jsArr_JM);
JM_list.tableTail=1;
function doSubmit(method){
	document.all.actionType.value=method;
	document.all.jmssbForm.submit();
	return false;
}

function jsjdmQuery(){
      if(event.keyCode==13) event.keyCode = 9;
}

function panduan(a){
var oRow = getObjRow(a);
if(oRow.all("szsmdm").value==null||oRow.all("szsmdm").value==""){
alert("�����������˰��Ŀ����");
oRow.all("kssl").value="";
}
}
function chkjsjdm()
{
	var jsjdm;
	jsjdm = document.forms[0].jsjdm.value;
	if(jsjdm==null || jsjdm==""){
        alert("��������벻�����ǿգ�");
        return false;
        }
        return true;
}

function setpanduan(a){

var oRow = getObjRow(a);

var kssl = oRow.all("kssl");
var szsmdms= oRow.all("szsmdm");
for(k=0;k<=slnum;k++){
if(szsmdms.value==szsmArray[k].szsmdm)
{
   if(szsmArray[k].asljbs==1 || szsmArray[k].asljbs==2 || szsmArray[k].asljbs==4 || szsmArray[k].asljbs==5)
   { kssl.disabled=false; }
   else
   { kssl.disabled=true;}
}

}

}


function doSave()
{
   var ok=1;
   var jsjdm;
   var nsrmc;
   var skssksrq;
   var skssjsrq;
   var jsje;
   var jmse;
   var szsmdm;
   var k;
   var kssl;
   //add by tangchangfu ��˰����˰��Ŀ 2014-05-04
   var jmxmjdm;
   var jmxmksrq;
   var jmxmjsrq;//add end
   var pks = new Array();
   
	jsjdm = document.forms[0].jsjdm.value;

        nsrmc = document.forms[0].nsrmc.value;
        
        //add by tangchangfu 2014-05-04 ��˰����˰��Ŀ 
        if(!checkData()){
        	return false;
        }//add end

	if((jsjdm==null || jsjdm=="")||(nsrmc==null || nsrmc=="")){
        alert("ҳ����Ϣ��д����ȷ��");
        //document.forms[0].jsjdm.focus();
        }
        else{
        if(document.forms[0].szsmdm){

        if(document.forms[0].szsmdm.length){
        
          for(i = 0; i < document.forms[0].szsmdm.length; i++){
        	 //��֤������ࡢ����˰�֡�������Ŀ������ȫ��ͬ 
        	 var pk = document.forms[0].jmlx[i].value
        	    + "-" + document.forms[0].szsmdm[i].value
        	    + "-" + document.forms[0].jmxmjdm[i].value;
        	 for(var jj=0; jj<pks.length; jj++) {
        		  if(pk == pks[jj]) {
        			  alert("��" + eval(i+1) + "�������ظ��ˣ�������ࡢ����˰�֣�˰Ŀ�����롢������Ŀ�����벻����ȫ��ͬ��");
        			  return false;
        		  }
        	  }
        	  pks[i]  = pk;
        	  
          skssksrq = document.forms[0].skssksrq[i].value;
          skssjsrq = document.forms[0].skssjsrq[i].value;
          jsje= document.forms[0].jsje[i].value;
          jmse= document.forms[0].jmse[i].value;
          szsmdm=document.forms[0].szsmdm[i].value;
          kssl=document.forms[0].kssl[i].value;
        //add by tangchangfu ��˰����˰��Ŀ 2014-05-04
          jmxmjdm = document.forms[0].jmxmjdm[i].value;
          jmxmksrq = document.forms[0].jmxmksrq[i].value;
          jmxmjsrq = document.forms[0].jmxmjsrq[i].value;//add end
          
           if(szsmdm==""||szsmdm==null)
          {  k=i+1;
           alert("����˰�ĵ�"+k+"�еļ���˰��˰Ŀû����д������д");
           ok=0;
           return false; //add by tangchangfu ��˰����˰��Ŀ 2014-05-04 Ϊ�˱�����ʾһƬ��Ϣ
          }
          if(skssksrq==""||skssksrq==null)
          { k=i+1;
           alert("����˰�ĵ�"+k+"�еļ��⿪ʼ����û����д������д");
           ok=0;
           return false; //add by tangchangfu ��˰����˰��Ŀ 2014-05-04 Ϊ�˱�����ʾһƬ��Ϣ
          }
            if(skssjsrq==""||skssjsrq==null)
          {  k=i+1;
           alert("����˰�ĵ�"+k+"�еļ����������û����д������д");
           ok=0;
           return false; //add by tangchangfu ��˰����˰��Ŀ 2014-05-04 Ϊ�˱�����ʾһƬ��Ϣ
          }
             if(skssjsrq<skssksrq)
          {  k=i+1;
           alert("����˰�ĵ�"+k+"�еļ����������С�ڼ��⿪ʼ���ڣ���������д");
           ok=0;
           return false; //add by tangchangfu ��˰����˰��Ŀ 2014-05-04 Ϊ�˱�����ʾһƬ��Ϣ
          }
           if(jsje==""||jsje==null)
          { k=i+1;
           alert("����˰�ĵ�"+k+"�еļ�˰���û����д������д");
           ok=0;
           return false; //add by tangchangfu ��˰����˰��Ŀ 2014-05-04 Ϊ�˱�����ʾһƬ��Ϣ
          }
            if(jmse==""||jmse==null)
          {  k=i+1;
           alert("����˰�ĵ�"+k+"�еļ���˰��û����д������д");
           ok=0;
           return false; //add by tangchangfu ��˰����˰��Ŀ 2014-05-04 Ϊ�˱�����ʾһƬ��Ϣ
          }
           if(kssl==""||kssl==null)
          {
             k=i+1;
             if(document.forms[0].kssl[i].className=="inputalignright"){
             var truth = window.confirm("����˰�ĵ�"+k+"�еļ����˰����û����д�����Ƿ�Ҫ������Ĭ��ֵ0");
             if(truth)	{document.forms[0].kssl[i].value="0";}
             else{
              alert("������д��Ӧ�Ŀ�˰����");
              document.forms[0].kssl[i].value="";
              ok=0;
              return false; //add by tangchangfu ��˰����˰��Ŀ 2014-05-04 Ϊ�˱�����ʾһƬ��Ϣ
             }
             }
          }
         //add by tangchangfu ��˰����˰��Ŀ 2014-05-04
   			if(jmxmjdm == "" || jmxmjdm == null){
   				k=i+1;
   	           alert("����˰�ĵ�"+k+"�еļ�����Ŀ������û����д������д!\n���û�ж�Ӧ������ѡ�����ѡ�����ʾѡ����û�м����Żݵ�˰��˰Ŀ����ɾ�������ٱ��棡");
   	           ok=0;
   	        return false; //add by tangchangfu ��˰����˰��Ŀ 2014-05-04 Ϊ�˱�����ʾһƬ��Ϣ
   			}else{
   				//add by tangchangfu 2014-06-24 ��˰����˰��Ŀ����
  				if(checkJmxmjdm(szsmdm,jmxmjdm) == false){
  					k=i+1;
  					alert("����˰�ĵ�"+k+"�еļ�����Ŀ������ѡ����ȷ,������ѡ��");
  					ok=0;
  					 return false; //add by tangchangfu 2014-06-24 ��˰����˰��Ŀ����
  				}
  			}
   
   			if(jmxmksrq == "" || jmxmksrq == null){
   				k=i+1;
    	        alert("����˰�ĵ�"+k+"�еļ�����Ŀ��ʼ����û����д������д!");
    	        ok=0;
    	        return false; //add by tangchangfu ��˰����˰��Ŀ 2014-05-04 Ϊ�˱�����ʾһƬ��Ϣ
   			}
/*    			if(jmxmjsrq == "" || jmxmjsrq == null){
   				k=i+1;
    	        alert("����˰�ĵ�"+k+"�еļ�����Ŀ��������û����д������д!");
    	        ok=0;
    	        return false; //add by tangchangfu ��˰����˰��Ŀ 2014-05-04 Ϊ�˱�����ʾһƬ��Ϣ
   			} *///add end
           
        }
       }
        else
        {

          skssksrq = document.forms[0].skssksrq.value;
          skssjsrq = document.forms[0].skssjsrq.value;
          jsje= document.forms[0].jsje.value;
          jmse= document.forms[0].jmse.value;
          szsmdm=document.forms[0].szsmdm.value;
          kssl=document.forms[0].kssl.value;
          
          //add by tangchangfu ��˰����˰��Ŀ 2014-05-04
          jmxmjdm = document.forms[0].jmxmjdm.value;
          jmxmksrq = document.forms[0].jmxmksrq.value;
          jmxmjsrq = document.forms[0].jmxmjsrq.value;//add end
          
           if(szsmdm==""||szsmdm==null)
          {
           alert("����˰�ļ���˰��˰Ŀû����д������д");
           ok=0;
           return false; //add by tangchangfu ��˰����˰��Ŀ 2014-05-04 Ϊ�˱�����ʾһƬ��Ϣ
          }
          if(skssksrq==""||skssksrq==null)
          {alert("����˰�ļ��⿪ʼ����û����д������д");
           ok=0;
           return false; //add by tangchangfu ��˰����˰��Ŀ 2014-05-04 Ϊ�˱�����ʾһƬ��Ϣ
          }
          if(skssjsrq==""||skssjsrq==null)
          {alert("����˰�ļ����������û����д������д");
           ok=0;
           return false; //add by tangchangfu ��˰����˰��Ŀ 2014-05-04 Ϊ�˱�����ʾһƬ��Ϣ
          }
          if(skssjsrq<skssksrq)
          {
           alert("����˰�ļ����������С�ڼ��⿪ʼ���ڣ���������д");
           ok=0;
           return false; //add by tangchangfu ��˰����˰��Ŀ 2014-05-04 Ϊ�˱�����ʾһƬ��Ϣ
          }
          if(jsje==""||jsje==null)
          {alert("����˰�ļ�˰���û����д������д");
           ok=0;
           return false; //add by tangchangfu ��˰����˰��Ŀ 2014-05-04 Ϊ�˱�����ʾһƬ��Ϣ
          }
          if(jmse==""||jmse==null)
          {alert("����˰�ļ���˰��û����д������д");
           ok=0;
           return false; //add by tangchangfu ��˰����˰��Ŀ 2014-05-04 Ϊ�˱�����ʾһƬ��Ϣ
          }
           if(kssl==""||kssl==null)
          {
             k=i+1;
            if(document.forms[0].kssl.className=="inputalignright"){
             var truth = window.confirm("����˰�ļ����˰����û����д�����Ƿ�Ҫ������Ĭ��ֵ0");
             if(truth)	{document.forms[0].kssl.value="0";}
             else{
                 alert("������д��Ӧ�Ŀ�˰����");
                 document.forms[0].kssl.value="";
                 ok=0;
                 return false; //add by tangchangfu ��˰����˰��Ŀ 2014-05-04 Ϊ�˱�����ʾһƬ��Ϣ
             }
            }
          }
           //add by tangchangfu ��˰����˰��Ŀ 2014-05-04
  			if(jmxmjdm == "" || jmxmjdm == null){
  	           alert("������Ŀ������û����д������д!\n���û�ж�Ӧ������ѡ�����ѡ�����ʾѡ����û�м����Żݵ�˰��˰Ŀ����ɾ�������ٱ��棡");
  	           ok=0;
  	         return false; //add by tangchangfu ��˰����˰��Ŀ 2014-05-04 Ϊ�˱�����ʾһƬ��Ϣ
  			}else{
  				if(checkJmxmjdm(szsmdm,jmxmjdm) == false){//add by tangchangfu 2014-06-24 ��˰����˰��Ŀ����
  					alert("������Ŀ������ѡ����ȷ,������ѡ��");
  					ok=0;
  		  	        return false; //add by tangchangfu 2014-06-24 ��˰����˰��Ŀ����
  				}
  			}
  
  			if(jmxmksrq == "" || jmxmksrq == null){
   	        alert("������Ŀ��ʼ����û����д������д!");
   	        ok=0;
   	     return false; //add by tangchangfu ��˰����˰��Ŀ 2014-05-04 Ϊ�˱�����ʾһƬ��Ϣ
  			}
/*   			if(jmxmjsrq == "" || jmxmjsrq == null){
   	        alert("������Ŀ��������û����д������д!");
   	        ok=0;
   	     return false; //add by tangchangfu ��˰����˰��Ŀ 2014-05-04 Ϊ�˱�����ʾһƬ��Ϣ
  			} *///add end
           
         }
        }
        if(ok==1)
	{doSubmitForm("Save");
         return false;
        }
        else
        {alert("������д����ȫ������д");}
        }
}

function doQuery()
{
        var jsjdm;
	jsjdm = document.forms[0].jsjdm.value;
	if(jsjdm==null || jsjdm==""){
        alert("��������벻�����ǿգ�");
        //document.forms[0].jsjdm.focus();
        }
	doSubmitForm("Query");

	return false;
}

/**
 * �����д�Ƿ���ȷ
 *add by tangchangfu 2014-04-04 ��˰����˰��Ŀ
 */
function checkData(){
	var dqxseObj = document.forms[0].dqxse;//�������ۣ�Ӫҵ����
	var dqlrzeObj = document.forms[0].dqlrze;//���������ܶ�
	
	var azrsObj = document.forms[0].azrs;//��������
	var qyrsObj = document.forms[0].qyrs;//��ҵ����
	
	
	if(dqxseObj.value != ""){
		if(!fnIsIntNum1(dqxseObj.value)){
			alert("�������ۣ�Ӫҵ�����ʽ���ԣ�������ڻ����0����ȷ�ϣ�");
			dqxseObj.focus();
			dqxseObj.select();
			return false;
		}else if(!isNum(dqxseObj,0,null,1,15,2)){
			//alert("");
			dqxseObj.focus();
			dqxseObj.select();
			return false;
		}
		
	}else{
		alert("�������ۣ�Ӫҵ�����Ǳ��������д��");
		dqxseObj.focus();
		return false;
		
	}
	
	
	
	if(dqlrzeObj.value != ""){
		if(!isNumber(dqlrzeObj.value)){
			alert("���������ܶ��ʽ���ԣ����������֣���ȷ�ϣ�");
			dqlrzeObj.focus();
			dqlrzeObj.select();
			return false;
		}else if(!isNum(dqlrzeObj,null,null,1,15,2)){
			dqlrzeObj.focus();
			dqlrzeObj.select();
			return false;
		}
	}else{
		alert("���������ܶ��Ǳ��������д��");
		dqlrzeObj.focus();
		return false;
		
	}
	
	//�������� �Ƿ�Ϊ��
	if(azrsObj.value != ""){
		if(!isDigit(azrsObj.value)){
			alert("����������ʽ���ԣ�����Ϊ������");
			azrsObj.focus();
			azrsObj.select();
			return false;
		}
	}else{
		alert("���������Ǳ��������д��");
		azrsObj.focus();
		return false;
	}
	
	//��ҵ�����Ƿ�Ϊ��
	if(qyrsObj.value != ""){
		if(!isDigit(qyrsObj.value)){
			alert("��ҵ������ʽ���ԣ�����Ϊ������");
			qyrsObj.focus();
			qyrsObj.select();
			return false;
		}else{
			if(azrsObj.value !="" && (parseInt(azrsObj.value) > parseInt(qyrsObj.value))){
				alert("�����������ԣ����ܴ�����ҵ������");
				azrsObj.focus();
				azrsObj.select();
				return false;
			}
		}
	}else{
		alert("��ҵ����Ϊ���������д��");
		qyrsObj.focus();
		return false;
	}
	
	return true;
}

//�жϼ�����Ŀ�����Ƿ���ȷ��������ѡ��������������ѡ��˰Ŀ
function checkJmxmjdm(szsmdm,jmxmjdm){
	//���ҳ��Ԥ���ؼ�����Ŀ��������ڣ���ִ�����²���
	if(jmxmjdm_name_all && jmxmjdm_value_all){
		var szdm = szsmdm.substr(0,2);//���˰�ִ���
		var re=new RegExp("^"+szdm,"i");//����ƥ��˰Ŀ��������ʽ
		//���˰�ִ��벻Ϊ�գ���ִ���жϲ���
		if(szdm){
			for(var index =0; index < jmxmjdm_name_all.length; index ++){
					if(re.test(jmxmjdm_name_all[index])==true){
						if(jmxmjdm_value_all[index] == jmxmjdm){
							return true;
						}
				}
			}
		}
	}
	return false;
}

//parseFloat

function fnIsIntNum1(strNum)
{
 var strCheckNum = strNum + "";
 if(strCheckNum.length < 1)         //���ַ���
  return false;
 else if(isNaN( Number(strCheckNum) ))         //������ֵ
  return false;
 else if(parseInt( Number(strCheckNum) ) < 0)       //��������
  return false;

 return true;
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
function compare(name1,name2){

	var obj1=getObjByName(name1);
	var obj2=getObjByName(name2);
	if(obj1  && obj2){
		if(parseFloat(obj1.value)>parseFloat(obj2.value)){
			return 1;
		}else if(parseFloat(obj1.value)==parseFloat(obj2.value)){
			return 0;
		}else{
			return -1;
		}
	}else{
		alert("����δ�ҵ�");
	}
}
function getObjByName(name){
	var obj = document.forms[0].all(name);
	if(!obj) return null;
	//if(name=="mx_bs")
	if(obj.length){
		//���ڵ�Ϊselect
		if(obj.type=="select-one" || obj.type=="select-multiple") return obj;
		//�ӽڵ�Ϊselect��checkbox
		var visibleNode = document.all(name+"_epx_visible_1");
    if(!visibleNode) return;//δ��ʾ���󷵻�null
		var subNode = visibleNode.childNodes[0];
    if(!subNode) return;
		if(subNode.type=="textarea" || subNode.type=="text" || subNode.type=="hidden" || subNode.type=="select-one" || subNode.type=="select-multiple" || subNode.type=="checkbox") return subNode;
	  //�ӽڵ�Ϊradio
		var radioObj = getRadioObj(obj);
		if(radioObj!="not radio") return radioObj;
		//other
	}//end if

	return obj;
}

function makeReadOnly2(){
  for(var i=1;i<JM.rows.length-1;i++){
	row =JM.rows[i]//JM_list.doGetRow(i);
    if(row.all("szsmdm")){
	var values = get_subobj("szsmlist",row.all("szsmdm").value);
	var fields = get_obj("szsmlist_fields");
	//setTheRow(fields,values,row);
	setTheRowOld(fields,values,row);
	document.all("jsjdm").focus();
		//setday("szsmdm","JM",row.all("szsmdm"));
	}
  }
}
//��ʾ�Ƿ���Ҫ��������˰
//var isadditions = false;
isadditions = true;
//�����������Ƶõ��������
function get_obj(objname){
  return eval(objname);
}

//���ݹؼ�ֵ���ص�һ��1ά����
function get_subobj(arrayName,key){
	var obj = get_obj(arrayName);
	for(var ii=0;ii<obj.length;ii++){
		if(obj[ii][0]==key) return obj[ii];
  }
  return null;
}

//���ݹؼ�ֵ����2ά����
function get_subobjs(arrayName,key){
  var obj = get_obj(arrayName);
  var tempArray = new Array();
  for(var ii=0;ii<obj.length;ii++){
		if(obj[ii][0]==key) tempArray.push(obj[ii]);
  }
  return tempArray;
}
//����˰����������
function setSkssrqBySzsm(row){
	var szsmdm = row.all("szsmdm").value;
	for(var i=0;i<szsmDate.length;i++){
		var szsmmx = szsmDate[i];
		if(szsmmx[0]==szsmdm){
			row.all("skssksrq").value=szsmmx[1];
			row.all("skssjsrq").value=szsmmx[2];
		}
	}
}
//������ǰ��-�����ֶζ���ֵ�����ж���
function setTheRow(fields,values,row){
	if(!(fields && values && row)) return;
	//��д���и�ֵ
	for(var i=0;i<fields.length;i++){
		var obj = row.all(fields[i]);
		if(!obj) continue;
		obj.value=values[i];
	}
	setSkssrqBySzsm(row);
	publicMethod();
	//�ж��Ƿ��ǿ�˰������
	var obj = row.all("asljbs");
	if(!obj) return;
	if(obj.value!="1" && obj.value!="2" && obj.value!="4" && obj.value!="5" ){
                row.all("kssl").value="";
		row.all("kssl").readOnly = true;
		row.all("kssl").className = "inputnoborder";
		row.all("kssl").tabIndex=-1;
	}else{
		row.all("kssl").readOnly = false;
		row.all("kssl").className = "inputalignright";
       //         if(row.all("kssl").value=="")
       //         {
       //           alert("��˰����������д�����������Ļ�����ʱ����ʾ���Ƿ�Ҫ������Ϊ0");
       //         }
	}
	row.all.kssl.outerHTML=row.all.kssl.outerHTML;
}

//������ǰ��-��������������������
function setRow(arrayName,obj){
		var fields = get_obj(arrayName+"_fields");
		var values = get_subobj(arrayName,obj.value);
		var row = getObjRow(obj);
		setTheRow(fields,values,row);
}

//-------------------------------------------

//������ǰ�м�����˰(����˰���벻�ɸ�,���ᴥ���˷���)
function setAdditions(nameHead,tableid,obj){
	//alert("setAdditions");
	/*****�⼮����������ʾ���ִ����б�******/
	if(nameHead=="bzdm"){

		var values = get_subobj("bzlist",obj.value);
		var fields = get_obj("bzlist_fields");
		var row = getObjRow(obj);
		//var oldCode = row.all("bzdm_old").value;
		//if(obj.value==oldCode) return;
         //���Ϸ�������ʹֵ���䣨����գ�values��ȫ������ֵ����return�������������������˰��
		//if(!values){
			//obj.value=oldCode;
		//	return;
		//}
		setTheRow(fields,values,row);
		//����λ�ñ�־
		document.all(nameHead+"_focus").value = "0";
		//�ر�div
	    document.all(nameHead+"_epodDateLayer").style.display = 'none';

		tempSelect.srcObj.select();
		return;
	}
	/*****�⼮����������ʾ���ִ����б�******/
	var values = get_subobj("szsmlist",obj.value);
	var fields = get_obj("szsmlist_fields");
	var row = getObjRow(obj);
	//ԭ˰Ŀ����

	var oldCode = row.all("szsmdm_old").value;
		//alert(row.rowIndex+" :"+obj.value+" :"+oldCode)
		//ֵδ��ʱ�����κβ���
	if(obj.value==oldCode) return;

	//���Ϸ�������ʹֵ���䣨����գ�values��ȫ������ֵ����return�������������������˰��
	if(!values){
		obj.value=oldCode;
		return;
	}

	//���õ�ǰ��
	setTheRow(fields,values,row);
	//ɾ����˰Ŀ����
	delSzsmList(nameHead,obj.value)

	//����λ�ñ�־
	document.all(nameHead+"_focus").value = "0";
	//�ر�div
	document.all(nameHead+"_epodDateLayer").style.display = 'none';
	tempSelect.srcObj.select();


	if(!isadditions){
		delSonRow(nameHead,tableid,oldCode);
		return;
	}

	//���Ҹ���˰(szsmlist_add��˰��˰Ŀ����͸���˰�Ķ�Ӧ��ϵ)
	var list = get_subobjs("szsmlist_add",obj.value);//����˰����
	var addCode;
	for(var ii=0;ii<list.length;ii++){
		var addCode = list[ii][1];//����˰����
		if(findRowIndex(tableid,addCode)>=0) continue;
		var row = addSonRow(tableid);//����һ�У��������ж���
		var values = get_subobj("szsmlist",addCode);
		setTheRow(fields,values,row);
		//delSzsmList(addCode)//maybe not needed
	}
	//����˰Ŀ����ı�ʱ,�ж��Ƿ�ɾ��֮ǰ�ĸ���˰
	delSonRow(nameHead,tableid,oldCode);
}

//ɾ������˰(��ɾ������֮�����)
function delSonRow(nameHead,tableid,father){
	if(!father || father==null || father=="") return;
	addSzsmList(nameHead,father);
	if(!isadditions) return;
	var sons = getSons(father);
	for(var ii=0;ii<sons.length;ii++){
		var rowIndex = findRowIndex(tableid,sons[ii]);
		if(rowIndex<0) continue;
		//�������˰���ڣ������丸
		fathers = getFathers(sons[ii]);
		for(var jj=0;jj<fathers.length;jj++){
			if(findRowIndex(tableid,fathers[jj])>=0){
				rowIndex = -1;
				break;
			}
		}
		//���и�������ʱɾ������
		if(rowIndex>=0) delRowByIndex(tableid,rowIndex);
	}
}

//����˰Ŀ�Ҹ���˰(һά����)
function getSons(father){
	if(!isadditions) return new Array();
  var obj = get_obj("szsmlist_add");
  var tempArray = new Array();
  for(var ii=0;ii<obj.length;ii++){
		if(obj[ii][0]==father) tempArray.push(obj[ii][1]);
  }
  return tempArray;
}

//���ݸ���˰��˰Ŀ(һά����)
function getFathers(son){
	if(!isadditions) return new Array();
  var obj = get_obj("szsmlist_add");
  var tempArray = new Array();
  for(var ii=0;ii<obj.length;ii++){
		if(obj[ii][1]==son) tempArray.push(obj[ii][0]);
  }
  return tempArray;
}

//����һ��(����˰),�����ж���
function addSonRow(tableid){
	var index =addRow(tableid);
	return eval(tableid).rows[index];
}

//�����к�ɾ����
function delRowByIndex(tableid,rowIndex){
	var rows = eval(tableid).rows;
	rows[rowIndex].removeNode(true);
}

//����˰Ŀ�Ƿ����,����ʱ�����к�,������ʱ����-1
function findRowIndex(tableid,code){
	var rows = eval(tableid).rows;
	for(var ii=0;ii<rows.length;ii++){
		var obj = rows[ii].all("szsmdm");
		if(!obj) continue;
		if(obj.value==code) return ii;
	}
	return -1;
}


var valuelist;
var namelist;
var valuelist_del;
var namelist_del;
var valuelist_all;
var namelist_all;

//��������ǰ׺������
function getArrayObjs(nameHead){
	valuelist = new Array();
	namelist = new Array();
	valuelist_del = eval(nameHead+"_value_del");
	namelist_del = eval(nameHead+"_name_del");
	valuelist_all = eval(nameHead+"_value_all");
	namelist_all = eval(nameHead+"_name_all");
	//����ĸ���
	for(var ii=0;ii<valuelist_all.length;ii++){
		valuelist.push(valuelist_all[ii]);
		namelist.push(namelist_all[ii]);
	}
}

//�������ĸ�ֵ
function setArrayObjs(nameHead){
	eval(nameHead+"_value = valuelist;");
	eval(nameHead+"_name = namelist;");
}

//ɾ��һ��˰Ŀ
function delSzsmList(nameHead,key){
	valuelist = eval(nameHead+"_value");
	namelist = eval(nameHead+"_name");
	valuelist_del = eval(nameHead+"_value_del");
	namelist_del = eval(nameHead+"_name_del");

	for(var ii=0;ii<valuelist.length;ii++){
		if(valuelist[ii]==key) {
			valuelist_del.push(valuelist.splice(ii,1));
			namelist_del.push(namelist.splice(ii,1));
			break;
		}
  }
}

//����һ��˰Ŀ
function addSzsmList(nameHead,key){
	//alert("addSzsmList");
	getArrayObjs(nameHead);
	//ɾ��valuelist_del�еĸ�Ԫ��
	for(var ii=0;ii<valuelist_del.length;ii++){
		if(valuelist_del[ii]==key) {
			//alert("key:"+key);
			valuelist_del.splice(ii,1);
			namelist_del.splice(ii,1);
			break;
		}
  }
	//valuelist_delתΪ�ַ���
	var delStr = ","+valuelist_del.toString()+",";

	//�����б�
	for(var ii=0;ii<valuelist.length;ii++){
		if(delStr.indexOf(","+valuelist[ii]+",")<0) continue;
		valuelist.splice(ii,1);
		namelist.splice(ii,1);
  }
	setArrayObjs(nameHead);
}

//��������ʱ�ύ
function selectClick(nameHead,tableid){
	//ʹ���ظ�ִ��
	if(tempSelect.selectObj.style.display=='none') return;
	tempSelect.enterTips(13,tempSelect.srcObj);
	setAdditions(nameHead,tableid,tempSelect.srcObj);
	window.event.returnValue=false;
	//alert(tempSelect.srcObj.outerHTML);
	//tempSelect.srcObj.focus();
	//tempSelect.srcObj.select;
	//alert(tempSelect.srcObj.focus);

}


function selectClick1(nameHead,tableid){
	//ʹ���ظ�ִ��
	if(tempSelect.selectObj.style.display=='none') return;
	tempSelect.enterTips(13,tempSelect.srcObj);
	window.event.returnValue=false;
	//alert(tempSelect.srcObj.outerHTML);
	//tempSelect.srcObj.focus();
	//tempSelect.srcObj.select;
	//alert(tempSelect.srcObj.focus);

}


//�������ƶ�ʱ�ж�
function selectMove(nameHead,tableid){
	var keyCode = window.event.keyCode;
	if(keyCode==13){
		selectClick(nameHead,tableid);
	}else if(keyCode==38 || keyCode==40){

		tempSelect.srcObj.value = tempSelect.selectObj.value;//document.all(nameHead+"_epodDateLayer").all.sel.value;
	}
}

//onblur�¼�����
function resetRow(nameHead,tableid,obj){
	//���������select�ϣ�ֱ��return
	try{
		if(document.all(nameHead+"_focus").value==nameHead) return;

		//���˰Ŀ����仯������ֵ
		var row = getObjRow(obj);
		if(row.all(nameHead).value!=row.all(nameHead+"_old").value ) {
			setAdditions(nameHead,tableid,obj);
		}
		//���û��select��ʾ��ʹ֮�ر�
		var s = tempSelect.selectObj.style;
		if(s.display!='none') {
			//s.display='none' ;
			//tempSelect.selectObj.parentElement.style.display='none';
			//modify by huipeijie for do not select by mouse
		}
	}catch(e){

		//alert("resetRow "+e);
	}
}

//onkeydown�¼�����
function doEnterDown(nameHead,tableid,obj){

	try{

	if(window.event.keyCode!=13) return;
	if(!tempSelect) return;
	//enter->tab
	window.event.keyCode = 9;
	//ִ�д���
	tempSelect.enterTips(13,tempSelect.srcObj);
	setAdditions(nameHead,tableid,obj);
	}catch(e){
		alert(e+"doEnterDown");
	}

}

//----Huipj------------------
//onkeyup������ʾ���ύ
function setday(nameHead,tableid,obj)
{
	if(obj!=srcobj &&��srcobj) return;
	anchor(obj,nameHead);
	tempSelect = new InputSelect(event.srcElement,document.all(nameHead+"_epodDateLayer").all.sel,eval(nameHead+"_name_all"),eval(nameHead+"_value_all"));
	tempSelect.showtips(obj);
	if(window.event.keyCode==40){
		//���������б�
		if(document.all(nameHead+"_epodDateLayer").all.sel.options.length<1) return;
		document.all(nameHead+"_epodDateLayer").all.sel.focus();
		document.all(nameHead+"_epodDateLayer").all.sel.options[0].selected = true;
		obj.value = tempSelect.selectObj.value;
		document.all(nameHead+"_focus").value = nameHead;
	}else if(window.event.keyCode==13){
		//ִ�д���;
		tempSelect.enterTips(window.event.keyCode,tempSelect.srcObj);
		(nameHead,tableid,obj);
	}
}

//��ʾdiv
function anchor(tt,nameHead){

  /*var dads = .style;
  var th = tt;
  var ttop  = tt.offsetTop;
  var thei  = tt.clientHeight;
  var tleft = tt.offsetLeft;
  var ttyp  = tt.type;
  while (tt = tt.offsetParent){ttop+=tt.offsetTop; tleft+=tt.offsetLeft;}

  dads.top  = (ttyp=="image")? ttop+thei : ttop+thei+6;
  dads.left = tleft;
  dads.zindex = '0';
  dads.display = '';*/

	var txtHeight = tt.clientHeight;
	var txtTop = getPosTop(tt);//obj.offsetTop;
	var txtLeft = getPosLeft(tt);
	var oDiv = document.all(nameHead+"_epodDateLayer");
	var parentHeight = document.body.clientHeight;//oParent.clientHeight;

	//oDiv.style.display = '';
	if(txtTop+oDiv.clientHeight > (parentHeight+document.body.scrollTop)){
		oDiv.style.top = txtTop - oDiv.offsetHeight - 5;//10 --> border or margin
	}else{
		oDiv.style.top = txtTop + txtHeight + 5;
	}
	oDiv.style.left = txtLeft - oDiv.clientLeft;
	oDiv.style.zindex = '0';

}
function getPosTop(obj){
	var pos = obj.offsetTop;;
	while(obj.tagName!="BODY"){
		obj = obj.offsetParent;
		pos += obj.offsetTop;
	}
	return pos;
}
function getPosLeft(obj){
	var pos = obj.offsetLeft;;
	while(obj.tagName!="BODY"){
		obj = obj.offsetParent;
		pos += obj.offsetLeft;
	}
	return pos;
}

function setTheRowOld(fields,values,row){
	if(!(fields && values && row)) return;
	//��д���и�ֵ
	for(var i=0;i<fields.length;i++){
		var obj = row.all(fields[i]);
		if(!obj) continue;
		obj.value=values[i];
	}
	//setSkssrqBySzsm(row);
	publicMethod();
	//�ж��Ƿ��ǿ�˰������
	var obj = row.all("asljbs");
	if(!obj) return;
	if(obj.value!="1" && obj.value!="2" && obj.value!="4" && obj.value!="5"){
                row.all("kssl").value="";
		row.all("kssl").readOnly = true;
		row.all("kssl").className = "inputnoborder";
		row.all("kssl").tabIndex=-1;
	}else{
		row.all("kssl").readOnly = false;
		row.all("kssl").className = "inputalignright";
                if(row.all("kssl").value=="")
                {
                  alert("��˰����������д�����������Ļ�����ʱ����ʾ���Ƿ�Ҫ������Ϊ0");
                }
	}
	row.all.kssl.outerHTML=row.all.kssl.outerHTML;
}

/**
 * ͨ��˰��˰Ŀ������˼���˰��Ŀ������������ֻ��ʾ��ǰ˰�ִ�����ƥ�������������������
 *add by tangchangfu ��˰����˰��Ŀ 2014-04-18
 */
function filterJmxmjdmBySzsmdm(jmxmjdmObj){
	//����ж���򴫵ݹ�����˰��δ������߲��Ƕ���ֱ�ӷ���
	if(!jmxmjdmObj){
		return false;
	}
	//��������BackSpace����ɾ������
	if(event.keyCode == 8 && jmxmjdmObj.value !=""){
		event.returnValue = false;
		return false;
	}
	
	//��ȡ��ǰ�ж��� 
	var trObj = jmxmjdmObj.parentNode.parentNode.parentNode;
	
	//��õ�ǰ��˰��˰Ŀ��������ڵ�ǰ�еĵڶ�����Ԫ��,��0��ʼ�������������еڶ���Ӧ��дΪcells[1]
	var szsmdmObj = trObj.cells[1].childNodes[0].childNodes[0];
	
	//���˰��˰Ŀ��������ֵ
	var szsmdmObj_value = szsmdmObj.value;
	
	//���˰��˰Ŀ����Ϊ�գ���ֱ�ӷ��� ��˰����˰��Ŀ���� add by tangchangfu  2014-06-24
	if(szsmdmObj_value ==""){
		return false;
	}
	
	
	//��ȡ˰�ִ���ǰ��λ��Ϊ��������
	var sub_first_2_szsmdm = szsmdmObj_value.substr(0,2);
	
	
	//��õ�ǰ�ж�Ӧ������Ŀ��������ѡ��
	//ִ�й���
	if(jmxmjdmObj.value =="" || jmxmjdmObj.value.length==1||jmxmjdmObj.value.length==2){
		jmxmjdmObj.value = sub_first_2_szsmdm;
	}	
	
	var nameHead ="jmxmjdm";
	//���������
	srcobj = jmxmjdmObj;
  	if(event.keyCode == 0 && jmxmjdmObj.value !=""){
  		//�ٴ�ѡ�񣬻��ѡ��֮ǰ��ֵ
  		var old_value = jmxmjdmObj.value;
  		
		jmxmjdmObj.value = sub_first_2_szsmdm;
		setday(nameHead,"JM",jmxmjdmObj);
		jmxmjdmObj.value = old_value;
		
		checkedOldSelected(nameHead,old_value);
		
		//return false;
	}else{
		setday(nameHead,"JM",jmxmjdmObj);
	}
	
	if(jmxmjdmObj.value == sub_first_2_szsmdm){
		jmxmjdmObj.value ="";
		
	}
	if(jmxmjdmObj.value ==""){
		checkThisSzsmHasJmxmjdm(nameHead);
	}
}

//��鵱ǰ˰��˰Ŀ�Ƿ��ж�Ӧ�ļ�����Ŀ������
function  checkThisSzsmHasJmxmjdm(nameHead){
	//var oDiv = document.all(nameHead+"_epodDateLayer");
	
	//��ü�����Ŀ����������
	var JmxmjdmSelectObj = document.all(nameHead+"_epodDateLayer").all.sel;
	
	//�鿴�����Ƿ���ѡ����û�У�����ʾɾ��
	var JmxmjdmSelectObj_childsArr = JmxmjdmSelectObj.childNodes;
	if(JmxmjdmSelectObj_childsArr && JmxmjdmSelectObj_childsArr.length ==0){
		alert("û�ж�Ӧ������ѡ�����ѡ�񣬸�˰��˰Ŀû�ж�Ӧ�����Żݣ���ɾ�������ٱ��棡");
		event.returnValue = false;
		return false;
	}
	
}

/**
 * ѡ���ѹ��˵������˵���ָ��ֵ
 */
function checkedOldSelected(nameHead,selectedValue){
	//��ü�����Ŀ����������
	var JmxmjdmSelectObj = document.all(nameHead+"_epodDateLayer").all.sel;
	var JmxmjdmSelectObj_childsArr = JmxmjdmSelectObj.childNodes;
	for(var index =0; index < JmxmjdmSelectObj_childsArr.length; index ++){
		var optionObj = JmxmjdmSelectObj_childsArr[index];
		if(optionObj && optionObj.value == selectedValue){
			optionObj.selected =true;			
		}
		
		
	}
	
	
}



function showJmxmjdmInDiv(obj,type){
	if(obj && obj.value != ""){
		anchor_showText(obj,"jmxmjdm",type);
	}
}

//��ʾdiv
function anchor_showText(tt,nameHead,type){
	var txtHeight = tt.clientHeight;
	var txtTop = getPosTop(tt);//obj.offsetTop;
	var txtLeft = getPosLeft(tt);
	var oDiv = document.all(nameHead+"_showText");
	var parentHeight = document.body.clientHeight;//oParent.clientHeight;
	if(type=="show"){
		oDiv.style.display = '';
		oDiv.style.backgroundColor ="azure";
	}else if (type=="display"){
		oDiv.style.display = 'none';
	}
	if(txtTop+oDiv.clientHeight > (parentHeight+document.body.scrollTop)){
		oDiv.style.top = txtTop - oDiv.offsetHeight - 5;//10 --> border or margin
	}else{
		//oDiv.style.top = txtTop + txtHeight + 5;
		oDiv.style.top = txtTop - oDiv.offsetHeight - 5;
	}
	oDiv.style.left = txtLeft - oDiv.clientLeft;
	oDiv.style.zindex = '0';
	oDiv.innerHTML=tt.value;
	//alert("222");
}

</script>
<script>
makeReadOnly2();
</script>

</td>
</tr>
</table>
<script language="javascript"> 
/****�������˰��Ϊ��������������ʾ��ʾ��Ϣ****/
/****20050817 Huxiaofeng****/
var nsrzt = "<ttsoft:write name="jmssbForm" property="nsrzt" filter="false"/>";
checkNsrzt();
/**************end *******/
</script>
</body>
</html:html>

