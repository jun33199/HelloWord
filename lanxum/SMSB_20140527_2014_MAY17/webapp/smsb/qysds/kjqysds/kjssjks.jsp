<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjssjks.web.KjssjksForm"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page contentType="text/html; charset=GBK" %>
<%
KjssjksForm kjssjksForm=(KjssjksForm)request.getAttribute("kjssjksForm");
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
<title>�����еط�˰��ֿ۽���ҵ����˰˰�սɿ���</title>
<link href="../../../css/text.css" rel="stylesheet" type="text/css">
<link href="../../../css/top-box.css" rel="stylesheet" type="text/css"> 
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../../../js/treatImage.js"></script>
<script language="JavaScript" src="../../../js/smsb_common.js"></script> 
<script language="JavaScript" src="../../../js/DTable.js"></script> 
<script language="JavaScript" src="../../../js/reader.js"></script>
<script language="JavaScript" src="../../../js/InputSelect.js"></script>
<script language="JavaScript" src="../../../js/function.js"></script>


</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
<!-- <table  width="100%" height="61%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
   <td colspan=2> -->
<%@ include file="../../include/header.jsp"%>


<html:form action="/webapp/smsb/qysds/kjqysds/kjssjksAction.do" method="POST" >

<html:hidden property="actionType" value="Show"/>
<html:hidden property="yhdm" />
<html:hidden property="yhmc" />
<html:hidden property="jsjdm" />
<html:hidden property="nsrmc" />
<html:hidden property="sklxdm" value="200"/>
<html:hidden property="sklxmc" value="�Ĵ����"/>
<html:hidden property="skssksrq" />
<html:hidden property="skssjsrq" />
<html:hidden property="szsmdm" />
<html:hidden property="szsmmc" />
<html:hidden property="szdm" />
<html:hidden property="szmc" />
<html:hidden property="sjse" />
<html:hidden property="badjxh" />
<html:hidden property="bgbxh" />
<html:hidden property="htmc" />
<html:hidden property="htbh" />
<html:hidden property="jsje" />
<html:hidden property="sl" />


      <table width="96%" align="center" cellspacing="0" class="table-99">
                <tr> 
                  <td class="1-td1"  colspan="7">˰�սɿ���</td>
                </tr>
                <tr> 
                  <td class="1-td2"  colspan="7"> <br> 
				  	<table width="93%" cellspacing="0" class="table-99">
                      <tr class="black9"> 
                        <td width="11%" align="center" nowrap> <div align="right">�걨���ڣ�</div></td>
                        <td width="11%" align="center" nowrap><div align="left">                         
							<html:text property="sbrq"    size="10" onchange="if(isDate(this,false)) changeDateQ();"  onkeydown="if(event.keyCode==13) event.keyCode=9;" />
                          </div></td>
                        <td width="21%" align="center" nowrap> <div align="right">˰�����ͣ� 
                            &nbsp; </div></td>
                        <td width="39%" align="center" nowrap><div align="left"> 
							�Ĵ����
                          </div></td>
						  
                        <td width="14%" align="center" nowrap> <div align="center">��λ��Ԫ</div></td>
                        <td width="4%" align="center" nowrap>&nbsp;</td>
                      </tr>
                    </table>
                    <br> <table width="94%" border="0" cellpadding="0" cellspacing="0">
                      <tr class="black9"> 
                        <td width="17%" nowrap class="2-td2-t-left">˰����������</td>
                        <td width="35%" nowrap class="2-td2-t-left">
						<bean:write name="kjssjksForm"
						property="jsjdm" scope="request" filter="true" />
						</td>
                        <td width="24%" nowrap class="2-td2-t-left">��λ����</td>
                        <td width="24%" nowrap class="2-td2-t-center">
						<bean:write name="kjssjksForm"
						property="nsrmc" scope="request" filter="true" />
						</td>
                      </tr>
                      <tr> 
                        <td nowrap class="2-td2-left">�ɿ���������</td>
                        <td nowrap class="2-td2-left" id="bankName">&nbsp;
						</td>
                        <td nowrap class="2-td2-left">�ɿ������ʺ�</td>
                        <td nowrap class="2-td2-center">
						  <bean:define id="bankList" name="kjssjksForm" property="bankList" />
                              <html:select property="zh" onchange="setBankName(this)">
                                 <html:options collection="bankList" property="zh" labelProperty="zh"/>
                              </html:select>
						  </td>
                      </tr>
                    </table>
                    <table cellspacing="0" class="table-99">
              	<tr> 
                	<td><hr class="hr1"></td>
              	</tr>
            	</table>
				<table width="94%" border="0" cellpadding="0" cellspacing="0" height="114" id="SBJKMX" >
				
					  
                      <tr class="black9"> 
                        <td width="17%" nowrap class="2-td2-t-left">��ͬ����</td>
                        <td width="35%" nowrap class="2-td2-t-left">
						<bean:write name="kjssjksForm"
						property="htmc" scope="request" filter="true" />
						</td>
                        <td width="24%" nowrap class="2-td2-t-left">��ͬ���</td>
                        <td width="24%" nowrap class="2-td2-t-center">
						<bean:write name="kjssjksForm"
						property="htbh" scope="request" filter="true" />
						</td>
                      </tr>
                      <tr class="black9"> 
                        <td width="17%" nowrap class="2-td2-left">˰������</td>
                        <td width="35%" nowrap class="2-td2-left">
						<bean:write name="kjssjksForm"
						property="szmc" scope="request" filter="true" />
						</td>
                        <td width="24%" nowrap class="2-td2-left">��Ŀ����</td>
                        <td width="24%" nowrap class="2-td2-center">
						<bean:write name="kjssjksForm"
						property="szsmmc" scope="request" filter="true" />
						</td>
                      </tr>
                      <tr class="black9"> 
                        <td width="17%" nowrap class="2-td2-left">˰��������ʼ����</td>
                        <td width="35%" nowrap class="2-td2-left">
						<bean:write name="kjssjksForm"
						property="skssksrq" scope="request" filter="true" />
						</td>
                        <td width="24%" nowrap class="2-td2-left">˰��������������</td>
                        <td width="24%" nowrap class="2-td2-center">
						<bean:write name="kjssjksForm"
						property="skssjsrq" scope="request" filter="true" />
						</td>
                      </tr>
                      <tr class="black9"> 
                        <td width="17%" nowrap class="2-td2-left">��˰���</td>
                        <td width="35%" nowrap class="2-td2-left">&nbsp;
						<bean:write name="kjssjksForm"
						property="jsje" scope="request" filter="true" />
						</td>
                        <td width="24%" nowrap class="2-td2-left">ʵ�ʽ�˰��</td>
                        <td width="24%" nowrap class="2-td2-center">&nbsp;
						<bean:write name="kjssjksForm"
						property="sjse" scope="request" filter="true" />
						</td>
                      </tr>					  
                    </table>
			        <table width="94%" border="0" cellpadding="0" cellspacing="4">
                      <tr valign="bottom"> 
                        <td width="35%"> </td>
                        <td width="10%"><input type="image" accesskey="s" tabIndex="-1" onClick="doSubmitCheck('Save'); return false;"  onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="����" id="bc1" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" height="22"></td>
                        <td width="10%"><input type="image" accesskey="f" onClick="back();return false;" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="����" id="fanhui" src="<%=static_contextpath%>images/fanhui1.jpg" width="79" height="22" style="cursor:hand"></td>
                        <td width="10%"><input type="image" accesskey="c" tabIndex="-1"   onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22"></td>
                        <td width="35%">&nbsp;</td>
                      </tr>
                    </table>
      <br>
	     </td>
		</tr>
	  </table>

</html:form>


<%@ include file="../../include/footer.jsp"%>
    <!-- </td> 
  </tr>
</table> -->
<script language="javascript"> 
function change(){
	if(!document.all)
		return
	if (event.srcElement.id=="foldheader") {
		var srcIndex = event.srcElement.sourceIndex
		var nested = document.all[srcIndex+1]
		if (nested.style.display=="none") {
			nested.style.display=''
			event.srcElement.style.listStyleImage="url(<%=static_contextpath%>images/zbotton-jian2.gif)"
		}
		else {
			nested.style.display="none"
			event.srcElement.style.listStyleImage="url(<%=static_contextpath%>images/zbotton-jia2.gif)"
		}	
	}
}

document.onclick=change		

<ttsoft:write name="kjssjksForm" property="bankJsArray" filter="false"/>

function setBankName(obj)
{
	
	for (var i=0; i<bankInfo.length; i++)
	{
		if (bankInfo[i][0] == obj.value)
		{
			document.all.yhmc.value = bankInfo[i][1];
			document.all.yhdm.value = bankInfo[i][2];
			bankName.innerText = " " + bankInfo[i][1];
			break;
		}
	}
	/********������û������������˻���ʱ�򣬽�������Ϣ�ÿ�**********/
	/********20040209 Shi Yanfeng*********************/
	if(bankInfo.length==0){
		document.all.yhmc.value ='';
		document.all.yhdm.value ='';
	}

}
setBankName(document.all.zh);

//��ת���ɿ���ά������
function doJkswh(){
	//����¼����������
	if(document.forms[0].jsjdm.value=='' || document.forms[0].nsrmc.value=='') return false;
	document.all.actionType.value='Jkswh';
	document.forms[0].submit();
}
function fnOnLoad(){}

//��鲢�ύ����Ҫ����걨���ڡ�˰����������
//��˰��Ŀ���롢˰���������ڡ���˰��������˰��ʵ�ʽ�˰��
function doSubmitCheck(ope){
	//�걨����
	if(!isDate(document.forms[0].sbrq,false)) return false;
	
	doSubmitForm(ope);
}

//����
function back(){
	history.back();
	self.location.reload();
}

</script>


</body>
</html:html>