<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft"%>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>

<html>
<head>
<title>��˰�˻�����Ϣ�ǼǱ�</title>
<link href="../../../css/text.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../../../js/treatImage.js"></script>
<script language=JavaScript src="../../../js/compute.js"></script>
<script language=JavaScript src="../../../js/function.js"></script>
<script language=JavaScript src="../../../js/smsb_common.js"></script>
<script language=JavaScript src="../../../js/Bolan.js"></script>
<script language=JavaScript src="../../../js/MathString.js"></script>
<script language=JavaScript src="../../../js/Stack.js"></script>
<script language=JavaScript src="../../../js/reader.js"></script>



</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onload="show()">
<%@ include file="../../include/header.jsp"%>

<br>

<%
			com.ttsoft.bjtax.smsb.sbzl.qysdsnb2013.jbxxb.web.JbxxbForm2013 form = (com.ttsoft.bjtax.smsb.sbzl.qysdsnb2013.jbxxb.web.JbxxbForm2013) request
			.getAttribute("jbxxbForm2013");

	String cwkjzddm = form.getCwkjzddm();
	String cwkjzddm_old = form.getCwkjzddm_old();
	//String gzglxsdm = form.getGzglxsdm();
	//String gzglxsdm_old = form.getGzglxsdm_old();

	//String jmlxdm = form.getJmlxdm();
	//String jmlxdm_old = form.getJmlxdm_old();
%>
<script type="text/javascript" language="JavaScript">



function show(){

<%if( cwkjzddm==null||"".equals(cwkjzddm)){ %>

document.getElementsByName("cwkjzddm")[0].checked = true;

<%}else{ %>
	<% if(!cwkjzddm.equals("99")){%>
		document.getElementById("cwkjzddm<%=cwkjzddm%>").checked = true;
	<%}%>

<%} %>

	doYwts();
}


<% /*����*/ %>
function doQuery()
{
	if(trim(document.forms[0].jsjdm.value) == ""){
		alert("��������˰��ҵ��������룡");
		document.forms[0].jsjdm.focus();
		return false;
	}

	if(trim(document.forms[0].sknd.value) == ""){
		alert("��������ȣ�");
		document.forms[0].sknd.focus();
		return false;
	}
	
	if(!isDigit(document.forms[0].sknd.value)||document.forms[0].sknd.value.length!=4){
		alert("��ȱ���Ϊ��λ���֣����������룡");
		document.forms[0].sknd.focus();
		document.forms[0].sknd.select();
		return false;
	}

	if(parseInt(document.forms[0].sknd.value)<2009){
		alert("������ʹ�õ���ҵ����˰�걨����¼��ϵͳΪ2009�棬�ð汾֧�ֵ���С˰�����Ϊ2009��\n2008����2008����ǰ��˰�������ʹ�þɰ汾ϵͳ��");
		document.forms[0].sknd.focus();
		document.forms[0].sknd.select();
		return false;	
	}
	
	doSubmitForm('Query');
	return false;
}


//��Ӧ��������롢��ȵ��޸�
function doChange(){

  var jsjdm;
  var sknd;
  jsjdm = document.forms[0].jsjdm.value;
  sknd = document.forms[0].sknd.value;

  if(jsjdm=="" || sknd==""){

    return false;
  
  }else{

    doQuery();  

  }
	
}
//���������������޸������½��в�ѯ  ���޸Ĳ�ѯ��־
//add by wangcy  2013-12-06
function doChangeQuery(){
	document.forms[0].isQuery.value="0";
}

//��Ӧ���������Ļس���ѯ
function jsjdmQuery(){
	if(event.keyCode==13) doQuery();
}

//����
function doSave()
{  
  
  var jsjdm;
  var nsrmc;
  jsjdm = document.forms[0].jsjdm.value;
  nsrmc = document.forms[0].nsrmc.value;
  var ckzd=document.getElementsByName("cwkjzddm");
  
  var flag=false;
  for(var i=0;i<3;i++){
  	if(ckzd[i].checked){
  		flag=true;
  	}
  }
  if(!flag){
  	alert("��ҵ���Ϊ��ѡ���ѡ��");
  	return false;
  }
  var zcze=document.forms[0].zcze;
  var zczbje=document.forms[0].zczbje;	
 		if(zcze.value.length<=0){
		alert("�ʲ��ܶ�Ϊ������������ʲ��ܶ");
		zcze.focus();
		zcze.select();
		return false;
	}		
	if(zczbje.value.length<=0){
		alert("ע���ʱ����Ϊ�����������ע���ʱ���");
		zczbje.focus();
		zczbje.select();
		return false;
	}		
  if(!isDigit(document.forms[0].sknd.value)||document.forms[0].sknd.value.length!=4){
			alert("��ȱ���Ϊ��λ���֣����������룡");
			document.forms[0].sknd.focus();
			document.forms[0].sknd.select();
			return false;
	}
	

		
  if(jsjdm==""){
    alert("����д��������룬ȷ����Ϣ������ٽ��б��棡");
    document.forms[0].jsjdm.focus();
		document.forms[0].jsjdm.select();
    return false;
  }else{
		if(nsrmc==""){
			alert("����˰�˵Ļ�����Ϣ��û���������ѯ����������Ϣ���ٽ��б��棡\n�������������á��س�����������ѯ��ť��ִ�в�ѯ��");
			document.forms[0].jsjdm.focus();
			return false;
		}
		var isQuery=document.forms[0].isQuery.value;
	  	if(isQuery!="1"){
	  		alert("����������˰����Ƚ������޸�,������ִ�в�ѯ������");
	  		return false;
	  	}
		if(parseInt(document.forms[0].sknd.value)<2013){
			alert("������ʹ�õ���ҵ����˰�걨����¼��ϵͳΪ2013�棬�ð汾֧�ֵ���С˰�����Ϊ2013��\n2012����2012����ǰ��˰�������ʹ�þɰ汾ϵͳ��");
			document.forms[0].sknd.focus();
			document.forms[0].sknd.select();
			return false;	
		}
		if(!doYwts()){
		
			return false;
		}	
  	
	  	if(confirm("����ʵ��д������Ϣ�������д������ܻᵼ��ĳЩ�����ظ�¼��")){
	  		if(parseInt(document.forms[0].sknd.value)>=2013){
	  			doSubmit2013();
	  		}
	    }
    return false;
    
  }
}
	/**
	*���˰�����Ϊ2013���Ժ������ܵطֵ��ж�
	*������ܵ�20140201���Ժ���ԵǼǻ�����Ϣ
	*/
	function doSubmit2013()
	{
		var sybs=document.forms[0].sybs.value;//˰Դ��ʶ
	  	var bbmsf_old=document.forms[0].bbmsf.value;//�ϵ���Ҫbbmsf�û���д�ı�
  		var bbmsf_new="";//�µ���Ҫ��дbbmsf��
  		var ckzddm="";//�����ƴ���
  		var ckzd=document.getElementsByName("cwkjzddm");
		for(var i=0;i<3;i++){
			if(ckzd[i].checked){
			   ckzddm=ckzd[i].value;
			}
		}
		//����ѡ��Ĳ������ƶ����·���Ҫ��д�ı�	
		if(ckzddm==<%=CodeConstant.CWKJZD01 %>){
			bbmsf_new="<%= CodeConstant.BBMSF_2009_10%>";
		}else if(ckzddm==<%=CodeConstant.CWKJZD02 %>){
			bbmsf_new="<%= CodeConstant.BBMSF_2009_20%>";
		}else if(ckzddm==<%=CodeConstant.CWKJZD03 %>){
			bbmsf_new="<%= CodeConstant.BBMSF_2009_30%>";
		}
		
		if(sybs!=<%=CodeConstant.CODE_QYSDS_ZGFWJD_OTHER %>){
			//˰Դ��ʶΪ�ܵĵĽ��д���
	  		if(sybs==<%=CodeConstant.CODE_QYSDS_ZGFWJD_KSSZJG %>){
	  			var tbrq=document.forms[0].tbrq.value;
	  			var sbrq_q="20140201";
	  			if(tbrq<sbrq_q){
	  				alert("����2014��2��1�գ�����֮����д������Ϣ�ǼǱ�!");
	  				return false;
	  			}else{
	  				bbmsf_new+="<%= CodeConstant.BBMSF_2012_17%>";
	  				doSumbit2013Ts(bbmsf_old,bbmsf_new);
	  			}
	  		}
	  		//˰Դ��ʶΪ�صĽ��д���
	  		if(sybs==<%=CodeConstant.CODE_QYSDS_ZGFWJD_DLNSR %> || sybs==<%=CodeConstant.CODE_QYSDS_ZGFWJD_ZFJGJZBSZJG%>){
	  			doSumbit2013Ts(bbmsf_old,bbmsf_new);
	  		}
  		}
	}
	
	//�ύ2013�Ժ����ݽ����ж��Ƿ���ʾ
	function doSumbit2013Ts(bbmsf_old,bbmsf_new){
		var queryFlag=document.forms[0].queryFlag.value;
		var ywts="����ҵ�ѽ���"+document.forms[0].sknd.value+"�����ҵ����˰��������걨�����α������������걨���ݣ��Ƿ����?";
		if(queryFlag =="1" && bbmsf_new!=bbmsf_old){
  			if(confirm(ywts)){
  				doSubmitForm("Save");
  			}
  		}else{
  			doSubmitForm("Save");
  		}
	}
	
 	function checkZczb(){
		 var zczbje=document.forms[0].zczbje;		
		 
		if(!checkNumber(zczbje.value,15,2,true)){
			alert("ע���ʱ�������Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ����ڵ����㣡");
			zczbje.focus();
			zczbje.select();
			return false;
		}
		if(zczbje.value>100000){
			alert("ע���ʱ�����10��Ԫ�����ʵ��");
		}	
		return true;
	 }
	 
	 function checkZcze(){
		var zcze=document.forms[0].zcze;		
		
		if(!checkNumber(zcze.value,15,2,true)){
			alert("�ʲ��ܶ����Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ����ڵ����㣡");
			zcze.focus();
			zcze.select();
			return false;
		}
		if(zcze.value>100000){
			alert("�ʲ��ܶ����10��Ԫ�����ʵ��");
		}		
		return true;
	 }

//function  : ��������ַ����Ƿ����������
//parameters: DigitString: String,������ַ�����ֵ
//			  totalLength: int, ���ֵĳ������ƣ�������С���㣩
//			  decimalLength: int��С���ĳ�������
//            totalLength-decimalLength �������ֵ�����
//            isPositive �Ƿ�Ϊ�Ǹ��� true��ʾһ��Ҫ�ǷǸ�����false��ʾһ��Ҫ�Ǹ�����null��ʾ���� add by zhangp 2003-09-25
//return    : false: �ַ����а�������������ַ�
//			  true : else
function checkNumber(DigitString, totalLength, decimalLength,isPositive)
{
    var re = "";
   

	if(isPositive == "true" || isPositive == true)
	{//����ǷǸ���
	  if(isNaN(DigitString*1) || DigitString*1<0)
			return false;
	}
	if(isPositive == "false" || isPositive == false)
	{//����Ǹ���
		if(isNaN(DigitString*1) || DigitString*1>=0)
			return false;
	}

    if (isNaN(DigitString))
    {
      return false;
    }
    if (decimalLength!=null && decimalLength != 0)
    {   //С����Ϊ��
        re = re+"\\.[\\d]{1,"+ decimalLength +"}"
    }

    if (totalLength == null)
    {   //δ�趨�ܳ���
        re= "\\d*"+re;
    }
    else
    {   //�趨�ܳ���
        //��С������Ϊ�յ�����£������ж����ֵĳ���
        var intergerLength = totalLength;
        if (decimalLength!=null)
        {   //��С�����ֲ�Ϊ���жϣ�������λ����С����λ���Ƿ���ȷ
            intergerLength = totalLength-decimalLength;
        }
        re="([\\d]{0,"+ intergerLength +"}"+re+"|[\\d]{0,"+intergerLength+"})";
    }
    re = new RegExp("^[-|+]?"+re+"$","g");

    //���ַ���Ϊ�ա�λ�����ԡ�����ƥ�������ʱ
    if(re.exec(DigitString) == null)
    {
        return false;
    }
    return true;
}

//����ת���걨����֮ǰ��������ʾ�Ƿ񱣴��Ѿ�¼������
function toSbzl(returnStr)
{
			window.location=returnStr;    

}

//���˰Դ��ʶΪ�����ܵصĽ�����ʾ
function doYwts(){
	

	//���˰����ȴ��ڵ���2013�� ��˰Դ��ʶ�����ܺͷֵĽ�����ʾ
	var sybs=document.forms[0].sybs.value;//˰Դ��ʶ
	if(parseInt(document.forms[0].sknd.value)>=2013){
		if(sybs!=<%=CodeConstant.CODE_QYSDS_ZGFWJD_OTHER %>){
		  	//˰Դ��ʶΪ�ֵĽ��д���
		  	if(sybs==<%=CodeConstant.CODE_QYSDS_ZGFWJD_KSSFZJG %>){
		  		alert("����ҵֻ�����ҵ����˰��֧���������˰�걨��");
		  		return false;
		  	}
	  	}else{
	  		alert("����ҵ����ҵ����˰���ɵط�˰��ֹ�Ͻ��");
	  		return false;
	  	}
	}
	return true;
}
/**	//�˳�
function tuichu(){
	if(returnStr==null || returnStr==""){
		returnStr="zhsbAction.do";
	}
	//��������ۺ��걨�����걨����ҳ�棬���˳����ۺ��걨ҳ��
	if(document.all.iszhsb && document.all.iszhsb.value=='1')
		returnStr="zhsbAction.do?actionType=Show";
	window.location=returnStr;
}**/

</script>

<html:form action="/webapp/smsb/qysds/2013/jbxxbAction2013.do" method="post">
	<html:hidden property="actionType" />
	<html:hidden property="nsrsbh"/>
	<html:hidden property="nsrmc"/>
	<html:hidden property="sbnd"/>
	<html:hidden property="ssjjlxdm"/>
	<html:hidden property="ssjjlxmc"/>
	<html:hidden property="jydz"/>
	<html:hidden property="sshydm"/>
	<html:hidden property="sshymc"/>
	<html:hidden property="zsfsdm"/>
	<html:hidden property="zsfsmc"/>
	<html:hidden property="cwkjzddm_old"/>
	<html:hidden property="swjgzzjgdm"/>
	<html:hidden property="swjgzzjgmc"/>
	<html:hidden property="cjr"/>
	<html:hidden property="cjrq"/>
	<html:hidden property="lrr"/>
	<html:hidden property="lrrq"/>
	<html:hidden property="xtjb"/>
	<html:hidden property="bbmsf"/>
	<html:hidden property="skssksrq"/>
	<html:hidden property="skssjsrq"/>
	<html:hidden property="iszhsb"/>
	<html:hidden property="sybs"/>
	<html:hidden property="isQuery"/>
	<html:hidden property="tbrq"/>
	<html:hidden property="queryFlag"/>
	<table width="96%" align="center" cellspacing="0" class="table-99">
		<tr>
			<td class="1-td1" colspan="7">��˰�˻�����Ϣ�ǼǱ�</td>
		</tr>

		<tr>
			<td class="1-td2" colspan="7">
			<TABLE class="table-99" align="center">
				<TR>
					<TD>
					<div id="Layer2" style="position:static;">
					<table id="wrklistTable" border="1" cellspacing="0"
						class="table-99" align="center">

						<tr>
							<td class="2-td2-t-left" nowrap>˰�����</td>
							<td class="2-td2-t-left" nowrap><input
								type='text' name='sknd' id='sknd'
								value='<ttsoft:write name="jbxxbForm2013" property="sknd"  scope="request" />'
								size='4' tabindex='2' onKeyDown="jsjdmQuery()" onchange="doChangeQuery()"></td>
							<td class="2-td2-t-left" nowrap>˰Դ��ʶ</td>
							<td class="2-td2-t-center" nowrap="nowrap">
							<div align="left">&nbsp;&nbsp;<ttsoft:write
								name="jbxxbForm2013" property="sybsmc" scope="request" />							
							</div>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>���������</td>
							<td class="2-td2-left" nowrap><input type='text'
								name='jsjdm' id='jsjdm'
								value='<ttsoft:write name="jbxxbForm2013" property="jsjdm"  scope="request" />'
								size='13' tabindex='2' onKeyDown="jsjdmQuery()" onchange="doChangeQuery()" ></td>
							<td class="2-td2-left" nowrap>��˰������</td>
							<td class="2-td2-center" nowrap>
							<div align="left">&nbsp;&nbsp;<ttsoft:write
								name="jbxxbForm2013" property="nsrmc" scope="request" />							
							</div>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>������������</td>
							<td class="2-td2-left" nowrap>
							<div align="left">&nbsp;&nbsp;<ttsoft:write
								name="jbxxbForm2013" property="ssjjlxmc" scope="request" /></div>
							</td>
							<td class="2-td2-left" nowrap>��ϵ�绰</td>
							<td class="2-td2-center" nowrap><input type='text'
								name='lxdh' id='lxdh'
								value='<ttsoft:write name="jbxxbForm2013" property="lxdh"  scope="request" />'
								size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>������ҵ</td>
							<td class="2-td2-left" nowrap >
							<div align="left">&nbsp;&nbsp;<ttsoft:write
								name="jbxxbForm2013" property="sshymc" scope="request" /></div>
							</td>
							<td class="2-td2-left" nowrap>��ҵ����˰���շ�ʽ</td>
							<td class="2-td2-center" nowrap>
							<div align="left">&nbsp;&nbsp;<ttsoft:write
								name="jbxxbForm2013" property="zsfsmc" scope="request" /></div>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>��ҵ��𣨱�ѡ��</td>
							<td colspan="3" class="2-td2-center" nowrap>����ѡ�񣺣��Ա����п��ƣ�<br>
							<input type="Radio" name="cwkjzddm"
								id="cwkjzddm<%=CodeConstant.CWKJZD01 %>"
								value="<%=CodeConstant.CWKJZD01 %>">һ����ҵ�� <input
								type="Radio" name="cwkjzddm" id="cwkjzddm<%=CodeConstant.CWKJZD02 %>"
								value="<%=CodeConstant.CWKJZD02	%>">������ҵ����ƶȣ� <input
								type="Radio" name="cwkjzddm" id="cwkjzddm<%=CodeConstant.CWKJZD03 %>"
								value="<%=CodeConstant.CWKJZD03 %>">��ҵ��λ��������塢������ҵ��λ����ƶ�
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>ע���ʱ�����λ����Ԫ��</td>
							<td class="2-td2-left" nowrap >
								<input type='text' name='zczbje' id='zczbje' onblur='checkZczb()'
								value='<ttsoft:write name="jbxxbForm2013" property="zczbje"  scope="request" />'
								size='13' tabindex='1'>
							</td>
							<td class="2-td2-left" nowrap>�ʲ��ܶ��λ����Ԫ��</td>
							<td class="2-td2-center" nowrap>
								<input type='text' name='zcze' id='zcze' onblur='checkZcze()'
								value='<ttsoft:write name="jbxxbForm2013" property="zcze"  scope="request" />'
								size='13' tabindex='2'>
							</td>
						</tr>
						<!--2009�� ��Ҫ��˲�������
						<tr>
							<td class="2-td2-left" nowrap>���ʹ�����ʽ</td>
							<td colspan="3" class="2-td2-center" nowrap>����ѡ��<br>
							<input type="Radio" name="gzglxsdm"
								id="gzglxsdm<%=CodeConstant.GZGLXS01 %>"
								value="<%=CodeConstant.GZGLXS01 %>">�ǹ�Ч�ҹ� <input
								type="Radio" name="gzglxsdm" id="gzglxsdm<%=CodeConstant.GZGLXS03 %>"
								value="<%=CodeConstant.GZGLXS03 %>">��Ч�ҹ����Ա����п��ƣ�</td>
						</tr>
						
						<tr>
							<td colspan="4" class="2-td2-center" nowrap>��˰�����ܼ�����ҵ����˰������</td>
						</tr>
						
						<tr>
							<td colspan="4" class="2-td2-center" nowrap>����ѡ��<br>
							<input type="Radio" name="jmlxdm" id="jmlxdm<%=CodeConstant.JMLXNO %>"
								value="<%=CodeConstant.JMLXNO %>">1�� û���Żݣ� <input
								type="Radio" name="jmlxdm" id="jmlxdm<%=CodeConstant.JMLX9010 %>"
								value="<%=CodeConstant.JMLX9010 %>">2�� ���¼�����ҵ�� <input
								type="Radio" name="jmlxdm" id="jmlxdm<%=CodeConstant.JMLX9020 %>"
								value="<%=CodeConstant.JMLX9020 %>">3�� �����ҵ�� <input
								type="Radio" name="jmlxdm" id="jmlxdm<%=CodeConstant.JMLX9030 %>"
								value="<%=CodeConstant.JMLX9030 %>">4�� ���ɵ�·��ҵ�� <input
								type="Radio" name="jmlxdm" id="jmlxdm<%=CodeConstant.JMLX9090 %>"
								value="<%=CodeConstant.JMLX9090 %>">5������������ҵ�� <input
								type="Radio" name="jmlxdm" id="jmlxdm<%=CodeConstant.JMLX9070 %>"
								value="<%=CodeConstant.JMLX9070 %>">6���Ͷ���ҵ������ҵ�� <input
								type="Radio" name="jmlxdm" id="jmlxdm<%=CodeConstant.JMLXOTHER %>"
								value="<%=CodeConstant.JMLXOTHER %>">7�������Żݣ�</td>
						</tr>
						-->
					</table>
					</div>
					</TD>
				</TR>
				<TR class="black9">
					<TD><br>
					<div align="center">
					 
                   &nbsp;&nbsp;&nbsp;&nbsp;
                   <img onclick="doQuery();return false;"   onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg ',1)" onMouseOut="MM_swapImgRestore()" value="��ѯ" id="tc1" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" style="cursor:hand"/>
                  &nbsp;&nbsp;&nbsp;&nbsp;
                  <img onclick="doSave();return false;"  onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="����" id="tc1" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" height="22" style="cursor:hand"/>
                  &nbsp;&nbsp;&nbsp;&nbsp;
                  <img onclick="tuichu()"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor:hand"/>
                  </div>
					</TD>
				</TR>
				<TR class="black9">
					<TD><br>
						<div align="left">
						 <a href="javascript:toSbzl('/smsb/webapp/smsb/qysds/2009/qysdsMainAction2009.do?actionType=Show')">����������ҵ����˰����걨��(2013��)</a>
	                     </div>
					</TD>
				</TR>
			</TABLE>
			</td>
		</tr>
	</table>
	<br>
	<div align="left">
					<font size="2">&nbsp;&nbsp;&nbsp;�Ҫ����˰������걨��ҵ����˰ʱ����˱�</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;�˵����</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1����˰�����ƣ��˰��Ǽ�֤������˰�˵�ȫ�ơ�</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2����������룺��д��˰���غ˷������չ����롣</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3����ϵ�绰����д��˰�˵�λ��˰��Ա��ϵ�绰�����ֻ����룩��</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4�������������͡�������ҵ������˰��ǼǱ��е��й�������д��</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5����ҵ����˰���շ�ʽ��ѡ��˶����յ���ҵ����ָ��˰����ظ�����������Ӫ���������ƺ�����������չ涨�ı�׼������Ȩ�޺ͷ������˶�Ӧ˰�����ʣ������ʣ���Ӧ��˰���һ�����շ�ʽ��</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6����ҵ���</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;һ����ҵ����ָ��������ҵ����ҵ��λ��������塢������ҵ��λ�������ҵ��</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;������ҵ����ִָ�С�������ҵ����ƶȡ�������ҵ���׼�򡷵���ҵ���С��������С����չ�˾��֤ȯ��˾������Ͷ�ʹ�˾�����޹�˾��������˾������˾���䵱��˾�Ƚ�����ҵ��</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ҵ��λ��������塢������ҵ��λ����ִָ�С���ҵ��λ���׼�򡷻�����Ӫ����֯����ƶȡ�����ҵ��λ��</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7��ע���ʱ�����ҵ���˰�����ҵ����Ӫҵִ���ϵ�ע���ʱ���д����ҵ��λ���ա���ҵ��λ����֤�顷�Ŀ����ʽ���д��������尴�ա�������巨�˵Ǽ�֤�顷��ע���ʽ���д��������ҵ���˵�λ���ա�������ҵ��λ�Ǽ�֤�顷�Ŀ����ʽ���д����</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;8���ʲ��ܶָ��ҵӵ�л���Ƶ�ȫ���ʲ������������ʲ�������Ͷ�ʡ��̶��ʲ������μ������ʲ������������ʲ��ȣ���Ϊ��ҵ�ʲ���ծ����ʲ��ܼ��</font><br> 
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;9���������е���ѡ����ڷ���ѡ��ġ��л����̡���</font>
					</div>
	<br>
	<br>

	<%@ include file="../../include/footer.jsp"%>
</html:form>

</body>
</html>
