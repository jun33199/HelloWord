<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft"%>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>

<html>
<head>
<title>��˰�˻�����Ϣ�ǼǱ�</title>
<!-- modify  20140829 -->
<html:base/>
<!-- end 20140829 -->
<link href="../../css/text.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../../js/treatImage.js"></script>
<script language=JavaScript src="../../js/compute.js"></script>
<script language=JavaScript src="../../js/function.js"></script>
<script language=JavaScript src="../../js/smsb_common.js"></script>
<script language=JavaScript src="../../js/Bolan.js"></script>
<script language=JavaScript src="../../js/MathString.js"></script>
<script language=JavaScript src="../../js/Stack.js"></script>
<script language=JavaScript src="../../js/reader.js"></script>



</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onload="show()">
<%@ include file="../include/header.jsp"%>

<br>

<%
			com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.jbxxb.web.JbxxbForm form = (com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.jbxxb.web.JbxxbForm) request
			.getAttribute("jbxxbForm");

	String cwkjzddm = form.getCwkjzddm();
	String cwkjzddm_old = form.getCwkjzddm_old();

	String gzglxsdm = form.getGzglxsdm();
	String gzglxsdm_old = form.getGzglxsdm_old();

	String jmlxdm = form.getJmlxdm();
	String jmlxdm_old = form.getJmlxdm_old();
%>
<script type="text/javascript" language="JavaScript">

function show(){

<%if( cwkjzddm==null||"".equals(cwkjzddm)){ %>

document.getElementsByName("cwkjzddm")[0].checked = true;

<%}else{ %>

document.getElementById("cwkjzddm<%=cwkjzddm%>").checked = true;


<%} %>


<%if(gzglxsdm==null||"".equals(gzglxsdm) ){%>

document.getElementsByName("gzglxsdm")[0].checked = true;

<%}else{%>

document.getElementById("gzglxsdm<%=gzglxsdm%>").checked = true;

<%}%>

<%if(jmlxdm==null||"".equals(jmlxdm) ){%>

document.getElementsByName("jmlxdm")[0].checked = true;

<%}else{%>

document.getElementById("jmlxdm<%=jmlxdm%>").checked = true;

<%}%>

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
	
	if(parseInt(document.forms[0].sknd.value)>=2008){
		alert("������ʹ�õ���ҵ����˰�걨����¼��ϵͳΪ2006�棬�ð汾֧�ֵ����˰�����Ϊ2007��\n2008����2008���Ժ��˰�������ʹ���°汾ϵͳ��");
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
  if(jsjdm=="" || nsrmc==""){
    alert("ҳ����Ϣ��д����ȷ��");
    return false;
  }else{
		
		if(!isDigit(document.forms[0].sknd.value)||document.forms[0].sknd.value.length!=4){
			alert("��ȱ���Ϊ��λ���֣����������룡");
			document.forms[0].sknd.focus();
			document.forms[0].sknd.select();
			return false;
		}
	
		if(parseInt(document.forms[0].sknd.value)>=2008){
			alert("������ʹ�õ���ҵ����˰�걨����¼��ϵͳΪ2006�棬�ð汾֧�ֵ����˰�����Ϊ2007��\n2008����2008���Ժ��˰�������ʹ���°汾ϵͳ��");
			document.forms[0].sknd.focus();
			document.forms[0].sknd.select();
			return false;	
		}
	
  	if(confirm("����ʵ��д������Ϣ�������д������ܻᵼ��ĳЩ�����ظ�¼��")){  
    	doSubmitForm("Save");
    }
    return false;
    
  }
}

//����ת���걨����֮ǰ��������ʾ�Ƿ񱣴��Ѿ�¼������
function toSbzl(returnStr)
{
			window.location=returnStr;    

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

<html:form action="/webapp/smsb/qysds/jbxxbAction.do" method="post">
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
	<html:hidden property="gzglxsdm_old"/>
	<html:hidden property="jmlxdm_old"/>
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
							<td class="2-td2-t-left" nowrap colspan="3"><input
								type='text' name='sknd' id='sknd'
								value='<ttsoft:write name="jbxxbForm" property="sknd"  scope="request" />'
								size='4' tabindex='2' onKeyDown="jsjdmQuery()"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>���������</td>
							<td class="2-td2-left" nowrap><input type='text'
								name='jsjdm' id='jsjdm'
								value='<ttsoft:write name="jbxxbForm" property="jsjdm"  scope="request" />'
								size='13' tabindex='2' onKeyDown="jsjdmQuery()"></td>
							<td class="2-td2-left" nowrap>��˰������</td>
							<td class="2-td2-center" nowrap>
							<div align="left">&nbsp;&nbsp;<ttsoft:write
								name="jbxxbForm" property="nsrmc" scope="request" />							
							</div>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>������������</td>
							<td class="2-td2-left" nowrap>
							<div align="left">&nbsp;&nbsp;<ttsoft:write
								name="jbxxbForm" property="ssjjlxmc" scope="request" /></div>
							</td>
							<td class="2-td2-left" nowrap>��ϵ�绰</td>
							<td class="2-td2-center" nowrap><input type='text'
								name='lxdh' id='lxdh'
								value='<ttsoft:write name="jbxxbForm" property="lxdh"  scope="request" />'
								size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>������ҵ</td>
							<td class="2-td2-left" nowrap>
							<div align="left">&nbsp;&nbsp;<ttsoft:write
								name="jbxxbForm" property="sshymc" scope="request" /></div>
							</td>
							<td class="2-td2-left" nowrap>��ҵ����˰���շ�ʽ</td>
							<td class="2-td2-center" nowrap>
							<div align="left">&nbsp;&nbsp;<ttsoft:write
								name="jbxxbForm" property="zsfsmc" scope="request" /></div>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>ִ�еĲ������ƶȣ���ѡ��</td>
							<td colspan="3" class="2-td2-center" nowrap>����ѡ��<br>
							<input type="Radio" name="cwkjzddm"
								id="cwkjzddm<%=CodeConstant.CWKJZD01 %>"
								value="<%=CodeConstant.CWKJZD01 %>">��ҵ��С��ҵ������ƶȣ� <input
								type="Radio" name="cwkjzddm" id="cwkjzddm<%=CodeConstant.CWKJZD02 %>"
								value="<%=CodeConstant.CWKJZD02	%>">������ҵ����ƶȣ� <input
								type="Radio" name="cwkjzddm" id="cwkjzddm<%=CodeConstant.CWKJZD03 %>"
								value="<%=CodeConstant.CWKJZD03 %>">��ҵ��λ��������塢������ҵ��λ����ƶȣ��Ա����п��ƣ�
							</td>
						</tr>
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
						 <a href="javascript:toSbzl('qysdsMainAction.do?actionType=Show')">����������ҵ����˰����걨��(2006��)</a>
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
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1����������룺��д��˰���غ˷������չ����롣</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2����˰�����ƣ��˰��Ǽ�֤������˰�˵�ȫ�ơ�</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3�������������͡�������ҵ������˰��ǼǱ��е��й�������д��</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4����ϵ�绰����д��˰�˵�λ��˰��Ա��ϵ�绰�����ֻ����룩��</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5����ҵ����˰���շ�ʽ��ѡ��˶����յ���ҵ����ָ��˰����ظ�����������Ӫ���������ƺ�����������չ涨�ı�׼������Ȩ�޺ͷ������˶�Ӧ˰�����ʣ������ʣ���Ӧ��˰���һ�����շ�ʽ��</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6����Ч�ҹ����÷�Χ���������ڰ��չ����ܶ��������ȵ��ھ���Ч���������ȡ�ְ��ƽ�������������ȵ����Ͷ��������������ȵ�ԭ�򣬾������ʲ��ල����ίԱ�������ֺ��Ͷ�����ᱣ�Ͼ���׼�Ĺ��м����пع���ҵ��</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7�����ܼ�����ҵ����˰�Żݵ���˰�ˣ��뾭˰�������׼����˱����ģ�����ѡ��2��3��4��5��6��7ѡ� </font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;8���������е���ѡ����ڷ��ϵ�ѡ��ǰ��"��"��
					</div>
	<br>
	<br>

	<%@ include file="../include/footer.jsp"%>
</html:form>

</body>
</html>
