<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft"%>
<%@page import="java.util.*"%>
<%@page import="com.ttsoft.bjtax.smsb.zjyjmsb.web.ZjyjmsbForm"%>

<%

ZjyjmsbForm zjyjmsbForm = (ZjyjmsbForm)request.getAttribute("zjyjmsbForm");
int yf =zjyjmsbForm.getYf();
int djcelx =zjyjmsbForm.getDjcelx();
 boolean sjpd =zjyjmsbForm.isSjpd();
%>


<html>
<head>
<title>�پ�ҵ����˰�걨¼��</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>


    <link href="../css/text.css" rel="stylesheet" type="text/css">
    <link href="../css/top-box.css" rel="stylesheet" type="text/css">
    <style>
      input {
      font-size: 9pt;
      }
      .black9c {
	   font-size: 16pt;
	   color: #000000;
	   white-space: pre;
	   font-weight: bold;
     }
    </style>
    <link href="text.css" rel="stylesheet" type="text/css">
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
    <script language=JavaScript src="../js/Stack.js"></script>
    <script language=JavaScript src="../js/InputSelect.js"></script>
    <script language="JavaScript">
    	//�����ж�
    	var sjpd = <%=sjpd%>;
  // ҳ����뽹��ȷ��
   function fnOnLoad()
  { 
  	//alert(sjpd);
    chciceTable2();
    if(sjpd){
     document.forms[0].j1.disabled=true;
		 document.forms[0].j2.disabled=true;
		 document.forms[0].j3.disabled=true;
		 document.forms[0].j4.disabled=true;
		 document.forms[0].j5.disabled=true;
		 document.forms[0].j6.disabled=true;
		 document.forms[0].j7.disabled=true;
		 document.forms[0].j8.disabled=true;
		 document.forms[0].j9.disabled=true;
		 document.forms[0].j10.disabled=true;
		 document.forms[0].j14.disabled=true;
		 document.forms[0].j21.disabled=true;
		 document.forms[0].j22.disabled=true;
		 document.forms[0].j23.disabled=true;
		 document.forms[0].j24.disabled=true;
		 document.forms[0].j25.disabled=true;
		 document.forms[0].j26.disabled=true;
		 document.forms[0].j27.disabled=true;
		 document.forms[0].j28.disabled=true;
		 document.forms[0].j29.disabled=true;
		 document.forms[0].j30.disabled=true;
		 document.forms[0].j31.disabled=true;
		 document.forms[0].j32.disabled=true;
		 document.forms[0].j33.disabled=true;
		 document.forms[0].j34.disabled=true;
		 document.forms[0].qnyysr.disabled=true;
		 document.forms[0].qnjmse.disabled=true;
    }
  }
//�ж�
//���� obj ��Ҫ����ֵ�Ķ���
//���� par
//			0 -- ���ֵ���Ϸ����Ϊ0
//			1 -- ��У���Ƿ�С��0
//			2 -- ���ܴ���100
//			3 -- ���ܴ���1
//ע��trim()�����Ѱ����ڹ��ú�������
function checkvalue(obj,par)
{
	
	var tmpValue = trim(obj.value);
	var tmp = "";
	while(tmpValue.length>0 && tmpValue.charAt(0)=='0')
	{
		tmpValue = tmpValue.substring(1);
	}
	if(tmpValue.length > 0)
	{
		switch (par)
		{
			case 0:
				if(tmpValue == null || isNaN(tmpValue) || tmpValue < 0)
				{
					
					alert("����ֵ����");
					obj.focus();
					obj.select();
					return false;
				}
				else
				{
					tmp = tmpValue;
				}
				break;
			case 1:
				if(tmpValue == null || isNaN(tmpValue))
				{
					
					alert("����ֵ����");
					obj.focus();
					obj.select();
					return false;
				}
				else
				{
					tmp = tmpValue;
				}
				break;
			case 2:
				if(tmpValue == null || isNaN(tmpValue) || tmpValue < 0)
				{
					
					alert("����ֵ����");
					obj.focus();
					obj.select();
					return false;
				}
				else
				{
					if(parseFloat(tmpValue) <= 0 || parseFloat(tmpValue) > 100)
					{
						alert("������(0��100]֮������֣�");
						obj.focus();
						obj.select();
						return false;
					}
				}
				break;
			case 3:
				if(tmpValue == null || isNaN(tmpValue) || tmpValue < 0)
				{
					alert("����ֵ����");
					obj.focus();
					obj.select();
					return false;
				}
				else
				{
					if(parseFloat(tmpValue) <= 0 || parseFloat(tmpValue) >= 1 || tmpValue.length > 3)
					{
						alert("������(0��1)֮�䲢��С������ܳ���2λ��С����");
						obj.focus();
						obj.select();
						return false;
					}
				}
				break;
			default:
				
				break;
		}
	}
	else if(tmpValue.length == 0)
	{
		switch (par)
		{
			case 2:
			case 3:
				alert("����ֵ����Ϊ0��");
				obj.focus();
				obj.select();
				return false;
		}
	}
	
	return true;
}
// ���ַ�����ʽ��Ϊ���Ҹ�ʽ
// parΪ0���Զ����0.00
// parΪ1�����Զ����
function formatCurrency(obj,par)
{
	var tmpValue = trim(obj.value);
	var pointIndex = tmpValue.indexOf(".");
	if(pointIndex < 0)
	{
		if(tmpValue == null || tmpValue == "" || tmpValue == ".")
		{
			switch (par)
			{
			case 0:
				tmpValue = "0.00";
				break;
			case 1:
				tmpValue = "";
				break;
			default:
				break;
			}
		}
		else
		{
			tmpValue += ".00";
		}
	}
	else if(pointIndex == 0)
	{
		if(tmpValue.length > 1)
		{
			if(tmpValue.substring(1).length > 2)
			{
				alert("С������ܴ�����λ��");
				obj.focus();
				obj.select();
				return false;
			}
			else if(tmpValue.substring(1).length == 1)
			{
				tmpValue = "0" + tmpValue + "0";
			}
			else
			{
				tmpValue = "0" + tmpValue;
			}
		}
		else
		{
			switch (par)
			{
			case 0:
				tmpValue = "0.00";
				break;
			case 1:
				tmpValue = "";
				break;
			default:
				break;
			}
		}
	}
	else
	{
		afterpoint = (tmpValue.length-1) - pointIndex;
		if(afterpoint == 0)
		{
			tmpValue += "00";
		}
		else if(afterpoint > 2)
		{
			
			alert("С������ܴ�����λ��");
			obj.focus();
			obj.select();
			return false;
		}
		else if(afterpoint == 1)
		{
			tmpValue += "0";
		}
	}
	if(tmpValue > 9999999999999.99)
	{
		alert("����������ֱ���С��10000000000000��");
		obj.focus();
		obj.select();
		return false;
	}
	obj.value = tmpValue;
	//return obj;
	return true;
}

    function doQuery(){
     var jsjdm;
	   jsjdm = document.forms[0].jsjdm.value;
	   if(jsjdm==null || jsjdm==""){
        alert("��������벻�����ǿգ�");
        //document.forms[0].jsjdm.focus();
        }
	     doSubmitForm("Query");

	     return false;
  }
    function doDelete(){
    	var jsjdm;
	    jsjdm = document.forms[0].jsjdm.value;
	
	    if((jsjdm==null || jsjdm=="")){
        alert("ҳ����Ϣ��д����ȷ��");
        //document.forms[0].jsjdm.focus();
        return false;
        }else{
	       if(sjpd) {
	        alert("���걨�����ѳ���������ɾ��");
	         return false;
	          }
	      doSubmitForm("Delete");
        return false;
        }
    }
	function chciceTable1(){
			var qycelx = "<%=djcelx%>";
			if(qycelx==2){
			alert("���Ǹ��徭Ӫ�ߣ�����д�걨��(��)��(��)�걨��(��)");
  		}
  		else {
  		table1.style.display = "";
  		table2.style.display = "none";
			table3.style.display = "none";
			document.getElementById('biao1').className = 'black9c';
      document.getElementById('biao2').className = 'default';
      document.getElementById('biao3').className = 'default';
  		}
	}
	function chciceTable2(){
		
			table1.style.display = "none";
  		table2.style.display = "";
			table3.style.display = "none";
			document.getElementById('biao1').className = 'default';
      document.getElementById('biao2').className = 'black9c';
      document.getElementById('biao3').className = 'default';
  		
	}
	function chciceTable3(){
			var qycelx = "<%=djcelx%>";
			if(qycelx==1){
			alert("������ҵ�û�������д�걨��(һ)���걨��(��)");
  		}
  		else {
  		table1.style.display = "none";
  		table2.style.display = "none";
			table3.style.display = "";
			document.getElementById('biao1').className = 'default';
      document.getElementById('biao2').className = 'default';
      document.getElementById('biao3').className = 'black9c';
  		}
	}
	function doSave(){ 
	   if(sjpd) {
	     alert("���걨�����ѳ����������޸�");
	     return false;
	    }
    if(confirm("ȷ�������걨����?"))
	   {
	    document.forms[0].actionType.value = "Save";
			document.forms[0].submit();
       			return true;
	   }
	    else
	    {
       		return false;
    	}
	}
	
 function qylxisSelect()
	{ 
	var v =document.forms[0].qylx.value;
	
	if(v==1){
		 document.forms[0].j1.disabled=false;
		 document.forms[0].j2.disabled=false;
		 document.forms[0].j3.disabled=false;
		 document.forms[0].j4.disabled=false;
		 document.forms[0].j5.disabled=false;
		 document.forms[0].j6.disabled=false;
		 document.forms[0].j7.disabled=false;
		 document.forms[0].j8.disabled=false;
		 document.forms[0].j9.disabled=false;
		 document.forms[0].j10.disabled=false;
		 document.forms[0].j14.disabled=true;
		}
		if(v==2){
		 document.forms[0].j1.disabled=false;
		 document.forms[0].j2.disabled=true;
		 document.forms[0].j3.disabled=true;
		 document.forms[0].j4.disabled=true;
		 document.forms[0].j5.disabled=false;
		 document.forms[0].j6.disabled=false;
		 document.forms[0].j7.disabled=false;
		 document.forms[0].j8.disabled=false;
		 document.forms[0].j9.disabled=false;
		 document.forms[0].j10.disabled=false;
		 document.forms[0].j14.disabled=true;
		}
		
		if(v==3){
		 document.forms[0].j1.disabled=false;
		 document.forms[0].j2.disabled=true;
		 document.forms[0].j3.disabled=true;
		 document.forms[0].j4.disabled=true;
		 document.forms[0].j5.disabled=true;
		 document.forms[0].j6.disabled=true;
		 document.forms[0].j7.disabled=true;
		 document.forms[0].j8.disabled=true;
		 document.forms[0].j9.disabled=true;
		 document.forms[0].j10.disabled=true;
		 document.forms[0].j14.disabled=false;
		 }
		if(v==4){
		 document.forms[0].j1.disabled=false;
		 document.forms[0].j2.disabled=true;
		 document.forms[0].j3.disabled=true;
		 document.forms[0].j4.disabled=true;
		 document.forms[0].j5.disabled=true;
		 document.forms[0].j6.disabled=true;
		 document.forms[0].j7.disabled=true;
		 document.forms[0].j8.disabled=true;
		 document.forms[0].j9.disabled=true;
		 document.forms[0].j10.disabled=true;
		 document.forms[0].j14.disabled=false;
		 }
		if(v==5){
		 document.forms[0].j1.disabled=false;
		 document.forms[0].j2.disabled=true;
		 document.forms[0].j3.disabled=true;
		 document.forms[0].j4.disabled=true;
		 document.forms[0].j5.disabled=true;
		 document.forms[0].j6.disabled=true;
		 document.forms[0].j7.disabled=true;
		 document.forms[0].j8.disabled=true;
		 document.forms[0].j9.disabled=true;
		 document.forms[0].j10.disabled=true;
		 document.forms[0].j14.disabled=false;
		 }
	}
	function qylx1isSelect()
	{ 
	var lx1 =document.forms[0].qylx1.value;
	
	if(lx1==1){
		 document.forms[0].j21.disabled=false;
		 document.forms[0].j22.disabled=false;
		 document.forms[0].j23.disabled=false;
		 document.forms[0].j24.disabled=false;
		 document.forms[0].j25.disabled=false;
		 document.forms[0].j26.disabled=false;
		 document.forms[0].j27.disabled=false;
		 document.forms[0].j28.disabled=false;
		 document.forms[0].j29.disabled=false;
		 document.forms[0].j30.disabled=false;
		 document.forms[0].j31.disabled=true;
		 document.forms[0].j32.disabled=true;
		 document.forms[0].j33.disabled=true;
		 document.forms[0].j34.disabled=false;
		}

		
		if(lx1==2){
		 document.forms[0].j21.disabled=false;
		 document.forms[0].j22.disabled=true;
		 document.forms[0].j23.disabled=true;
		 document.forms[0].j24.disabled=true;
		 document.forms[0].j25.disabled=true;
		 document.forms[0].j26.disabled=true;
		 document.forms[0].j27.disabled=true;
		 document.forms[0].j28.disabled=true;
		 document.forms[0].j29.disabled=true;
		 document.forms[0].j30.disabled=true;
		 document.forms[0].j31.disabled=true;
		 document.forms[0].j32.disabled=true;
		 document.forms[0].j33.disabled=true;
		 document.forms[0].j34.disabled=false;
		 }
		if(lx1==3){
		 document.forms[0].j21.disabled=false;
		 document.forms[0].j22.disabled=false;
		 document.forms[0].j23.disabled=false;
		 document.forms[0].j24.disabled=false;
		 document.forms[0].j25.disabled=false;
		 document.forms[0].j26.disabled=false;
		 document.forms[0].j27.disabled=false;
		 document.forms[0].j28.disabled=false;
		 document.forms[0].j29.disabled=false;
		 document.forms[0].j30.disabled=false;
		 document.forms[0].j31.disabled=false;
		 document.forms[0].j32.disabled=false;
		 document.forms[0].j33.disabled=false;
		 document.forms[0].j34.disabled=true;
		}
	}
	 </script>
    <style type="text/css">


    </style>
</head>
 <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()" style="margin: 0" " >
  
  <table width="104%" height="100%" border='0' cellpadding='0' cellspacing='0' align='center' class='black9'>
   
   <tr>
      <td align="center" valign="top">
       <%@ include file="include/header.jsp" %>
        <jsp:param name="name" value="�پ�ҵ����˰�걨¼��" />
        	<jsp:param name="help_url" value="help/wssb/sbzl/zrrsb/zjyjmsb-000.htm"/>
        </jsp:include>
         <html:errors/>
         	
      </td>
   </tr>
  <tr>
  
<td width="104%" valign="middle" align="center">
    <br>
    <html:form  method="POST" action="/webapp/smsb/zjyjmsbAction.do">
	<html:hidden property="actionType"/>
<table width="104%" align="center" cellspacing="0" class="table-99">
  <tr>
   <td valign="top" class='1-td1'>
    <a id="biao1" href="#" onclick="javascript:chciceTable1()">�پ�ҵ�걨��(һ)</font></a>&nbsp;&nbsp;&nbsp;
    <a id="biao2" href="#" onclick="javascript:chciceTable2()">�پ�ҵ�걨��(��)</font></a>&nbsp;&nbsp;&nbsp;
    <a id="biao3" href="#" onclick="javascript:chciceTable3()">�پ�ҵ�걨��(��)</font></a>&nbsp;&nbsp;&nbsp;
   </td></tr>
	        <table width="104%" cellspacing="0" class="table-99">
                  <tr class="black9">
                  	  <td width="12%" nowrap class="2-td2-t-left"><div align="right">���������&nbsp;&nbsp;</div></td>
                      <td width="12%" nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<html:text  property="jsjdm" onchange="doQuery(); return false;"/></div></td>
				              <td width="12%" nowrap class="2-td2-t-left"><div align="right">��˰������&nbsp;&nbsp;</div></td>
                      <td width="19%" nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="nsrmc"/></div></td>
                      <td width="10%" nowrap class="2-td2-t-left"><div align="right">��ϵ�绰&nbsp;&nbsp;</div></td>
                      <td width="10%" nowrap class="2-td2-t-center"><div align="left">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="dh"/></div></td>
					            <td width="7%" nowrap class="2-td2-t-center"><div align="right">&nbsp;&nbsp;<html:text property="nd" size="4" readOnly="true"/>��</div></td>
					            <td width="7%" nowrap class="2-td2-t-center"><div align="right">&nbsp;&nbsp;<html:text property="jd" size="2" readOnly="true"/>����</div></td>
					          <td width="15%"  nowrap class="2-td2-center"><a href="#" id="queryIndex" onclick="doQuery();return false;">��ѯ</a></td>		
                 </tr>
           
	
	<span id = "table1"  style= "display:none">
    <table width="104%" align="center" cellspacing="0" class="table-99">
    	
        <tr>
          <td class="1-td1">�پ�ҵ����˰�걨��(֮һ)</td>
        </tr>
        <tr>
          <td width="100%" class="1-td2" align="center"> <br>
		 
              <table width="104%" cellspacing="0" class="table-99">
                  <tr class="black9">
                      <td width="12%" nowrap class="2-td2-t-left"><div align="right">���������&nbsp;&nbsp;</div></td>
                      <td width="15%" nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="jsjdm"/></div></td>
				              <td width="12%" nowrap class="2-td2-t-left"><div align="right">��˰������&nbsp;&nbsp;</div></td>
                      <td width="15%" nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="nsrmc"/></div></td>
                      <td width="10%" nowrap class="2-td2-t-left"><div align="right">��ϵ�绰&nbsp;&nbsp;</div></td>
                      <td width="10%" nowrap class="2-td2-t-center"><div align="left">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="dh"/></div></td>
					            <td width="7%" nowrap class="2-td2-t-center"><div align="right">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="nd"/>��</div></td>
					            <td width="7%" nowrap class="2-td2-t-center"><div align="right">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="jd"/>����</div>
					            </td>
					          <td width="10%" nowrap class="2-td2-t-left"><div align="right">���㵥λ���ˡ�Ԫ&nbsp;&nbsp;</div></td>		
                 </tr>
             </table>
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99" id="mxtable">
  <tr>
    <td width="12%" rowspan="3" class="2-td1-left">���</td>
    <td width="6%" rowspan="3" class="2-td1-left">�����¸�ʧҵ��Ա��(��)������</td>
    <td colspan="13" class="2-td1-left">����˰��</td>
  </tr>
  <tr>
    <td colspan="3" class="2-td1-left">Ӫҵ˰</td>
    <td colspan="3" class="2-td1-left">����ά������˰</td>
    <td colspan="3" class="2-td1-left">�����Ѹ���</td>
    <td colspan="3" class="2-td1-left">��������˰</td>
    <td width="10%" class="2-td1-left">��ҵ����˰</td>
  </tr>
  <tr>
    <td width="6%" class="2-td1-left" name="2"><%=yf%>��</td>
    <td width="6%" class="2-td1-left" name="3"><%=yf+1%>��</td>
    <td width="6%" class="2-td1-left" name="4"><%=yf+2%>��</td>
    <td width="6%" class="2-td1-left" name="5"><%=yf%>��</td>
    <td width="6%" class="2-td1-left" name="6"><%=yf+1%>��</td>
    <td width="6%" class="2-td1-left" name="7"><%=yf+2%>��</td>
    <td width="6%" class="2-td1-left" name="8"><%=yf%>��</td>
    <td width="6%" class="2-td1-left" name="9"><%=yf+1%>��</td>
    <td width="6%" class="2-td1-left" name="10"><%=yf+2%>��</td>
    <td width="6%" class="2-td1-left" name="11"><%=yf%>��</td>
    <td width="6%" class="2-td1-left" name="12"><%=yf+1%>��</td>
    <td width="6%" class="2-td1-left" name="13"><%=yf+2%>��</td>
    <td width="10%" class="2-td1-left" >������</td>
  </tr>
  <tr>
    <td class="2-td1-left">���</td>
    <td class="2-td1-left">1</td>
    <td class="2-td1-left">2</td>
    <td class="2-td1-left">3</td>
    <td class="2-td1-left">4</td>
    <td class="2-td1-left">5</td>
    <td class="2-td1-left">6</td>
    <td class="2-td1-left">7</td>
    <td class="2-td1-left">8</td>
    <td class="2-td1-left">9</td>
    <td class="2-td1-left">10</td>
    <td class="2-td1-left">11</td>
    <td class="2-td1-left">12</td>
    <td class="2-td1-left">13</td>
    <td class="2-td1-left">14</td>
  </tr>
  <tr>
    <td height="33" class="2-td2-left">
	    <select name="qylx"  onChange="qylxisSelect()">
	    <option value="1" <%if(zjyjmsbForm.getQylx().equals("1")){out.println("selected");}%>>�°���ҵ:������30%����</option>
	    <option value="2" <%if(zjyjmsbForm.getQylx().equals("2")){out.println("selected");}%>>�°���ҵ:��ҵ������30%����</option>
	    <option value="3" <%if(zjyjmsbForm.getQylx().equals("3")){out.println("selected");}%>>������ҵ:������30%����</option>
	    <option value="4" <%if(zjyjmsbForm.getQylx().equals("4")){out.println("selected");}%>>������ҵ:��ҵ������30%����</option>
	    <option value="5" <%if(zjyjmsbForm.getQylx().equals("5")){out.println("selected");}%>>��������뾭��ʵ��</option>
	    </select>
	  </td>                              
    <td class="2-td2-left">
	   <input name="j1" size="7" maxlength="16" <% if(zjyjmsbForm.getJ1()!=null){%> value="<%=zjyjmsbForm.getJ1()%>" 
	    <%}else{%> value="" <%}%> onChange="return (checkvalue(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j2" size="7" maxlength="16" <% if(zjyjmsbForm.getJ2()!=null){%> value="<%=zjyjmsbForm.getJ2()%>" 
	    <%}else{%> value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j3" size="7" maxlength="16" <% if(zjyjmsbForm.getJ3()!=null){%> value="<%=zjyjmsbForm.getJ3()%>" 
	    <%}else{%> value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j4" size="7" maxlength="16" <% if(zjyjmsbForm.getJ4()!=null){%> value="<%=zjyjmsbForm.getJ4()%>" 
	    <%}else{%> value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j5" size="7" maxlength="16" <% if(zjyjmsbForm.getJ5()!=null){%> value="<%=zjyjmsbForm.getJ5()%>" 
	    <%}else{%> value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j6" size="7" maxlength="16" <% if(zjyjmsbForm.getJ6()!=null){%> value="<%=zjyjmsbForm.getJ6()%>" 
	    <%}else{%> value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j7" size="7" maxlength="16" <% if(zjyjmsbForm.getJ7()!=null){%> value="<%=zjyjmsbForm.getJ7()%>" 
	    <%}else{%> value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j8" size="7" maxlength="16" <% if(zjyjmsbForm.getJ8()!=null){%> value="<%=zjyjmsbForm.getJ8()%>" 
	    <%}else{%> value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j9" size="7" maxlength="16" <% if(zjyjmsbForm.getJ9()!=null){%> value="<%=zjyjmsbForm.getJ9()%>" 
	    <%}else{%> value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j10" size="7" maxlength="16" <% if(zjyjmsbForm.getJ10()!=null){%> value="<%=zjyjmsbForm.getJ10()%>" 
	    <%}else{%> value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j11" size="7" maxlength="16" disabled></td>
    <td class="2-td2-left">
	   <input name="j12" size="7" maxlength="16" disabled></td>
    <td class="2-td2-left">
	   <input name="j13" size="7" maxlength="16" disabled></td>
    <td class="2-td2-left">
	   <input name="j14" size="7" maxlength="16" <% if(zjyjmsbForm.getJ14()!=null){%> value="<%=zjyjmsbForm.getJ14()%>" 
	    <%}else{%> value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
  </tr>
</table>
</td>
</table>
     <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
           <tr>
             <td width="40%"> <hr width="100%" size="1" class="hr1" >
             </td>
             <td width="20%" align="center" class="black9">
             	<strong><font color="#0000FF">ע������</font></strong>
             </td>
             <td width="40%"> <hr width="100%" size="1" class="hr1" >
             </td>
           </tr>
        </table>
        <table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
           <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
             <td height="47" >
             	<p>
             		&nbsp;&nbsp;&nbsp;&nbsp;1.������2005��12��31��ǰ��׼�������پ�ҵ����˰���ߵ���ҵ�����ÿ���Ƚ�����10������д��������˰�������ͱ��������걨����˰����ÿ���Ƚ�����10����������д���ͱ�����<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;2.����ֻ��д���������������پ�ҵ����˰���·ݵ����ݣ���������˰�޶��Ӧ��˰����йع涨������˰�걨��<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;3.�����������˰������ȡ���HTTP://WWW.TAX861.GOV.CN���ء�<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;4.�������¸�ʧҵ�������ˣ�����ֻ����������������������������۳���<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;5.�����14�С�������ҵ����˰����������ӦС�ڻ���ڡ���ҵ����˰������˰�걨����8�����ݡ�<br>
              </p>
             </td>
           </tr>
        </table>
</span>
<span id = "table2" style="display:">
    <table width="104%" align="center" cellspacing="0" class="table-99">
    	
        <tr>
          <td class="1-td1">�پ�ҵ����˰�걨��(֮��)</td>
        </tr>
        <tr>
          <td width="100%" class="1-td2" align="center"> <br>
              <table width="104%" cellspacing="0" class="table-99">
                  <tr class="black9">
                      <td width="12%" nowrap class="2-td2-t-left"><div align="right">���������&nbsp;&nbsp;</div></td>
                      <td width="15%" nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="jsjdm"/></div></td>
				              <td width="12%" nowrap class="2-td2-t-left"><div align="right">��˰������&nbsp;&nbsp;</div></td>
                      <td width="15%" nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="nsrmc"/></div></td>
                      <td width="10%" nowrap class="2-td2-t-left"><div align="right">��ϵ�绰&nbsp;&nbsp;</div></td>
                      <td width="10%" nowrap class="2-td2-t-center"><div align="left">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="dh"/></div></td>
					            <td width="7%" nowrap class="2-td2-t-center"><div align="right">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="nd"/>��</div></td>
					            <td width="7%" nowrap class="2-td2-t-center"><div align="right">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="jd"/>����</div>
					            </td>
					          <td width="10%" nowrap class="2-td2-t-left"><div align="right">���㵥λ���ˡ�Ԫ&nbsp;&nbsp;</div></td>		
                 </tr>
             </table>
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99" id="mxtable">
  <tr>
    <td width="12%" rowspan="3" class="2-td1-left">���</td>
    <td width="6%" rowspan="3" class="2-td1-left">�����¸�ʧҵ��Ա��(��)������</td>
    <td colspan="13" class="2-td1-left">����˰��</td>
  </tr>
  <tr>
    <td colspan="3" class="2-td1-left">Ӫҵ˰</td>
    <td colspan="3" class="2-td1-left">����ά������˰</td>
    <td colspan="3" class="2-td1-left">�����Ѹ���</td>
    <td colspan="3" class="2-td1-left">��������˰</td>
    <td width="10%" class="2-td1-left">��ҵ����˰</td>
  </tr>
  <tr>
    <td width="6%" class="2-td1-left" name="2"><%=yf%>��</td>
    <td width="6%" class="2-td1-left" name="3"><%=yf+1%>��</td>
    <td width="6%" class="2-td1-left" name="4"><%=yf+2%>��</td>
    <td width="6%" class="2-td1-left" name="5"><%=yf%>��</td>
    <td width="6%" class="2-td1-left" name="6"><%=yf+1%>��</td>
    <td width="6%" class="2-td1-left" name="7"><%=yf+2%>��</td>
    <td width="6%" class="2-td1-left" name="8"><%=yf%>��</td>
    <td width="6%" class="2-td1-left" name="9"><%=yf+1%>��</td>
    <td width="6%" class="2-td1-left" name="10"><%=yf+2%>��</td>
    <td width="6%" class="2-td1-left" name="11"><%=yf%>��</td>
    <td width="6%" class="2-td1-left" name="12"><%=yf+1%>��</td>
    <td width="6%" class="2-td1-left" name="13"><%=yf+2%>��</td>
    <td width="10%" class="2-td1-left" >������</td>
  </tr>
  <tr>
    <td class="2-td1-left">���</td>
    <td class="2-td1-left">1</td>
    <td class="2-td1-left">2</td>
    <td class="2-td1-left">3</td>
    <td class="2-td1-left">4</td>
    <td class="2-td1-left">5</td>
    <td class="2-td1-left">6</td>
    <td class="2-td1-left">7</td>
    <td class="2-td1-left">8</td>
    <td class="2-td1-left">9</td>
    <td class="2-td1-left">10</td>
    <td class="2-td1-left">11</td>
    <td class="2-td1-left">12</td>
    <td class="2-td1-left">13</td>
    <td class="2-td1-left">14</td>
  </tr>
  <tr>
    <td class="2-td2-left">
	    <select name="qylx1" onChange="qylx1isSelect()">
	    <option value="1" <%if(zjyjmsbForm.getQylx1().equals("1")){out.println("selected");}%>>��ҵ�����¸�ʧҵ��Ա</option>
	    <option value="2" <%if(zjyjmsbForm.getQylx1().equals("2")){out.println("selected");}%>>�������뾭��ʵ��</option>
	    <option value="3" <%if(zjyjmsbForm.getQylx1().equals("3")){out.println("selected");}%>>���徭Ӫ</option>
	    </select>
	  </td>
    <td class="2-td2-left">
	   <input name="j21" size="7" maxlength="16" <% if(zjyjmsbForm.getJ21()!=null){%> value="<%=zjyjmsbForm.getJ21()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j22" size="7" maxlength="16" <% if(zjyjmsbForm.getJ22()!=null){%> value="<%=zjyjmsbForm.getJ22()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j23" size="7" maxlength="16" <% if(zjyjmsbForm.getJ23()!=null){%> value="<%=zjyjmsbForm.getJ23()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j24" size="7" maxlength="16" <% if(zjyjmsbForm.getJ24()!=null){%> value="<%=zjyjmsbForm.getJ24()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j25" size="7" maxlength="16" <% if(zjyjmsbForm.getJ25()!=null){%> value="<%=zjyjmsbForm.getJ25()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j26" size="7" maxlength="16" <% if(zjyjmsbForm.getJ26()!=null){%> value="<%=zjyjmsbForm.getJ26()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j27" size="7" maxlength="16" <% if(zjyjmsbForm.getJ27()!=null){%> value="<%=zjyjmsbForm.getJ27()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j28" size="7" maxlength="16" <% if(zjyjmsbForm.getJ28()!=null){%> value="<%=zjyjmsbForm.getJ28()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j29" size="7" maxlength="16" <% if(zjyjmsbForm.getJ29()!=null){%> value="<%=zjyjmsbForm.getJ29()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j30" size="7" maxlength="16" <% if(zjyjmsbForm.getJ30()!=null){%> value="<%=zjyjmsbForm.getJ30()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j31" size="7" maxlength="16" <% if(zjyjmsbForm.getJ31()!=null){%> value="<%=zjyjmsbForm.getJ31()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" disabled></td>
    <td class="2-td2-left">
	   <input name="j32" size="7" maxlength="16" <% if(zjyjmsbForm.getJ32()!=null){%> value="<%=zjyjmsbForm.getJ32()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" disabled></td>
    <td class="2-td2-left">
	   <input name="j33" size="7" maxlength="16" <% if(zjyjmsbForm.getJ33()!=null){%> value="<%=zjyjmsbForm.getJ33()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" disabled></td>
    <td class="2-td2-left">
	   <input name="j34" size="7" maxlength="16" <% if(zjyjmsbForm.getJ34()!=null){%> value="<%=zjyjmsbForm.getJ34()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
  </tr>
</table>
</td>
</table>
</table>
   <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
           <tr>
             <td width="40%"> <hr width="100%" size="1" class="hr1" >
             </td>
             <td width="20%" align="center" class="black9">
             	<strong><font color="#0000FF">ע������</font></strong>
             </td>
             <td width="40%"> <hr width="100%" size="1" class="hr1" >
             </td>
           </tr>
        </table>
        <table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
           <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
             <td height="47" >
             	<p>
             		&nbsp;&nbsp;&nbsp;&nbsp;1.������2006��1��1�պ��º�׼�����پ�ҵ����˰���ߵ���ҵ�����г֡��پ�ҵ�Ż�֤����Ա���¸��徭Ӫ�����<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;2.��1����ҵ�Ͱ����걨�ĸ��廧��ÿ���Ƚ�����10������д��������˰�������ͱ��������걨����˰����ÿ���Ƚ�����10����������д���ͱ�����<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����д���걨�������������پ�ҵ����˰���·ݵ����ݣ���������˰�޶��ڵĵ���ֻ��д�ɼ����˰�<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��2�������걨�ĸ��廧��ÿ���걨ʱ��д����ֻ��д���걨�·ݵ����ݣ��걨����Ϊ���걨�·��ڿ����ܵļ���˰��޶��ڣ���<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��3�������걨�ĸ��廧��ÿ���걨ʱ��д�����·�Ϊ�걨�������·ݣ��걨����Ϊ�ϴ��걨�������걨ʱ����ڿ����ܵļ���˰��޶��ڣ���<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;3.����ֻ��д���������������پ�ҵ����˰���·ݵ����ݣ���������˰�޶��Ӧ��˰����йع涨������˰�걨��<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;4.�����������˰������ȡ���HTTP://WWW.TAX861.GOV.CN���ء�<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;5.�������¸�ʧҵ��Ա�����ˣ�����ֻ����������������������������۳���<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;6.�����徭Ӫ��������Ŀ�еġ������¸�ʧҵ��Ա�������������¸��徭Ӫ���¸�ʧҵ��Ա���ˡ�<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;7.�����14�С���ҵ����˰������ȣ��������ݵ���˰�ˣ�����ҵ����˰��Ȼ�����ɽ�����10������д����ȫ�����ݺ͵�1-13�е��ļ������ݣ����ϱ�����˰����أ��������걨����<br>
              </p>
             </td>
           </tr>
        </table>
</span>
<span id = "table3" style= "display:none">
    <table width="96%" align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1">�پ�ҵ����˰�걨��(֮��)</td>
        </tr>
        <tr>
          <td class="1-td2"> <br>
              <table width="100%" cellspacing="0" class="table-99">
                  <tr class="black9">
                      <td width="15%" nowrap class="2-td2-t-left"><div align="right">˰����������&nbsp;&nbsp;</div></td>
                      <td width="10%" nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="jsjdm"/></div></td>
                      <td width="10%" nowrap class="2-td2-t-left"><div align="right">��˰������&nbsp;&nbsp;</div></td>
                      <td width="20%" nowrap class="2-td2-t-center"><div align="left">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="nsrmc"/></div></td>
                      <td width="10%" nowrap class="2-td2-t-left"><div align="right">��ϵ�绰&nbsp;&nbsp;</div></td>
                      <td width="10%" nowrap class="2-td2-t-center"><div align="left">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="dh"/></div></td>
					            <td width="10%"  nowrap class="2-td2-t-center"><div align="right">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="nd"/>��</div></td>
					            <td width="15%" nowrap class="2-td2-t-left">  <div align="right">���㵥λ:Ԫ&nbsp;&nbsp;</div></td>		
					         </tr>
              </table>          
          </td>   
        </tr>
		<table align="center" cellspacing="0" class="table-99">
            <tr class="black9">
			  <td width="20%" class="2-td2-left">
		    <div align="right">ȫ��Ӫҵ����&nbsp;&nbsp;</div></td>
		      <td width="30%" class="2-td2-left">
		      <div align="left">&nbsp;&nbsp;<html:text name="zjyjmsbForm" property="qnyysr" onchange="return (checkvalue(this,0)&&formatCurrency(this,0))" value="<%=zjyjmsbForm.getQnyysr()%>"/></div></td>
		    <td width="20%" class="2-td2-left">
		    <div align="right">ȫ�����˰��&nbsp;&nbsp;</div></td>
		      <td width="30%" class="2-td2-center">
		      <div align="left">&nbsp;&nbsp;<html:text name="zjyjmsbForm" property="qnjmse" onchange="return (checkvalue(this,0)&&formatCurrency(this,0))" value="<%=zjyjmsbForm.getQnjmse()%>"/></div></td>
            </tr>
         </table>
    </table>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
           <tr>
             <td width="40%"> <hr width="100%" size="1" class="hr1" >
             </td>
             <td width="20%" align="center" class="black9">
             	<strong><font color="#0000FF">ע������</font></strong>
             </td>
             <td width="40%"> <hr width="100%" size="1" class="hr1" >
             </td>
           </tr>
        </table>
        <table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
           <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
             <td height="47" >
             	<p>
             	  &nbsp;&nbsp;&nbsp;&nbsp;1.�������¸�ʧҵ��Ա���¸��徭Ӫ��Ӫҵ��δ�ﵽӪҵ˰������ĸ��幤�̻�����ڴ���4��10��ǰ��д��������˰�������ͱ��������걨����˰���ڴ���4��10��ǰ������д���ͱ�����<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;2.�����������˰������ȡ���HTTP://WWW.TAX861.GOV.CN���ء�<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;3.���������ִ����ȵ�ȫ��������<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;4."ȫ��Ӫҵ����"����Ӧ�ȫ��Ӫҵ˰Ӧ˰���롣<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;5."ȫ�����˰��"����Ӧ�ȫ�����Ӫҵ˰˰�<br>
              </p>
             </td>
           </tr>
    </table>
  </span>
</table>
</html:form>
    <tr>
          <td colspan="13">
            <br>
            <div align="center">
		  	    <input type="image" accesskey="s" tabIndex="-1"  onClick="return doSave()" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)" 
		  	           onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/bc-s1.jpg" alt="����"  width="79" height="22" id="baocun" style="cursor:hand" >
                    &nbsp;&nbsp;&nbsp;&nbsp;
            <input type="image" accesskey="x" tabIndex="-1"  onClick="doDelete(); return false;"onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg ',1)" 
                   onMouseOut="MM_swapImgRestore()" alt="���" src="<%=static_contextpath%>images/qbsc-x1.jpg" width="79" height="22" id="shanchu" style="cursor:hand">      
			              &nbsp;&nbsp;&nbsp;&nbsp;
			      <input type="image" accesskey="c" tabIndex="-1" onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" 
			             onMouseOut="MM_swapImgRestore()" alt="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor:hand">
			     
          </div>
          </td>
    </tr>    
	
  </td>
  </tr>
   <tr>
     <td valign="bottom" align="center">
        <%@ include file="include/footer.jsp" %>
     </td>
   </tr>
</table> 
</body>
</html>
