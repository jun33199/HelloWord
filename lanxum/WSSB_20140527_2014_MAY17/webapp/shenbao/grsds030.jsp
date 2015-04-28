<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@page import="com.ttsoft.bjtax.shenbao.sbzl.grsds.web.GrsdsForm"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.domain.Grsdsmx"%>
<%@page import="com.ttsoft.bjtax.shenbao.constant.SzsmdmConstant"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.domain.Szsm"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.JspUtil"%>

<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>

<html>
<head>
<title>��������˰�걨��ϸ����¼��</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
<script language=JavaScript src="<%=static_contextpath%>js/inputchecker.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
<script language=JavaScript>

<%

GrsdsForm myForm = (GrsdsForm)request.getAttribute("grsdsForm");
List szsms = myForm.getSzsmList();

// ˰��˰Ŀ˰��
StringBuffer szsmsl = new StringBuffer("var szsmsl= [");
// ˰��˰Ŀ����˰����ֹ��
StringBuffer szsmynszzs = new StringBuffer("var szsmynszzs = [");

for (int i=0; i<szsms.size(); i++)
{
	Szsm szsm = (Szsm)szsms.get(i);
	if (szsm.getSzsmdm().length() == 6)
	{
		szsmsl.append("[\"" + szsm.getSzsmdm() + "\", " + szsm.getSl() + "], ");
	}
	if (szsm.getYnsqss() != null)
	{
		szsmynszzs.append("[\"" + szsm.getSzsmdm() + "\", " + szsm.getYnsqss() + ", " + szsm.getYnszzs() + "], ");
	}
}

szsmsl.append("[]];");
szsmynszzs.append("[]];");

out.println(szsmsl.toString());
out.println(szsmynszzs.toString());
%>
function setSl(szsmdm)
{
	var myForm = document.forms[0];
	var length = myForm.zjhm.length;

	var slObj = null;

	if (length == null)
	{
		slObj = myForm.sl;
	}
	else
	{
		var index = 0;
		for (var i=0; i<length; i++)
		{
			if (myForm.szsmdm[i] == szsmdm)
			{
				index = i;
				break;
			}
		}

		slObj = myForm.sl[index];
	}

	if (szsmdm.value.length != 6)
	{
//		alert("����ѡ��˰�֣�");
		szsmdm.selectedIndex += 1;
	}

	if (szsmdm.value == "")
	{
		alert("����ѡ��н���");
		szsmdm.selectedIndex += 1;
	}

	for (var i=0; i<szsmsl.length; i++)
	{
		if (szsmsl[i][0] == szsmdm.value)
		{
			if (szsmsl[i][1] == null)
			{
				slObj.value = "";
			}
			else
			{
				slObj.value = szsmsl[i][1];
			}
		}
	}
}

function eSetSl()
{
	var szsmdm = event.srcElement;

	setSl(szsmdm);
}

function doSave()
{
	if (Table_Master.rows.length == 1)
	{
		if (confirm(confirmDelete))
		{
			document.forms[0].actionType.value = "Delete";
			document.forms[0].submit();
			return false;
		}
		else
		{
			return false;
		}
	}

	if (!checkValidate())
	{
		return false;
	}

	if (!checkRepeated())
	{
		return false;
	}

	if (!confirm(confirmSave))
	{
		return false;
	}

	document.forms[0].actionType.value = "Save";
	document.forms[0].submit();
}

function checkRepeated()
{
	var myForm = document.forms[0];
	var length = myForm.zjhm.length;

	if (length == null || length == 1)
	{
		return true;
	}
	else
	{
		for (var i=1; i<length; i++)
		{
			for (var j=0; j<i; j++)
			{
				if (myForm.gjdm[i].value == myForm.gjdm[j].value
				 && myForm.zjlxdm[i].value == myForm.zjlxdm[j].value
				 && myForm.zjhm[i].value == myForm.zjhm[j].value
				 && myForm.szsmdm[i].value == myForm.szsmdm[j].value)
				{
					alert("��" + (i+1) + "�����" + (j+1) + "���ظ�");
					return false;
				}
			}
		}
	}
	return true;
}

function checkValidate()
{
	var myForm = document.forms[0];

	if (myForm.bqdwzrs.value == "")
	{
		alert("����д���ڵ�λ��������");
		myForm.bqdwzrs.focus();
		return false;
	}else if(!(fnIsIntNum(myForm.bqdwzrs.value) || parseFloat(myForm.bqdwzrs.value)==0.0))
	{
		alert("���ڵ�λ����������д������");
		myForm.bqdwzrs.focus();
		myForm.bqdwzrs.select();
		return false;
	}else if(myForm.bqdwzrs.value > 9999999999999.99)
	{
		alert("���ڵ�λ��������д���ֹ���");
		myForm.bqdwzrs.focus();
		myForm.bqdwzrs.select();
		return false;
        }

	if (myForm.bqfdwzrs.value == "")
	{
		alert("����д���ڷǵ�λ��������");
		myForm.bqfdwzrs.focus();
		return false;
	}else if(!(fnIsIntNum(myForm.bqfdwzrs.value) || parseFloat(myForm.bqfdwzrs.value)==0.0))
	{
		alert("���ڷǵ�λ����������д������");
		myForm.bqfdwzrs.focus();
		myForm.bqfdwzrs.select();
		return false;
	}else if(myForm.bqfdwzrs.value > 9999999999999.99)
	{
		alert("���ڷǵ�λ��������д���ֹ���");
		myForm.bqfdwzrs.focus();
		myForm.bqfdwzrs.select();
		return false;
	}

	var length = myForm.zjhm.length;
	if (length == null)
	{
		if (!checkZjhm(myForm.zjlxdm, myForm.zjhm)
		 || !checkXm(myForm.xm)
		 || !checkSzsmdm(myForm.szsmdm)
		 || !checkSre(myForm.sre)
		 || !checkYnssde(myForm.szsmdm, myForm.ynssde)
		 || !checkJmje(myForm.jmje)
		 || !checkBqskse(myForm.bqskse)
		 || !checkWszh(myForm.wszh)
		)
		{
			return false;
		}
	}
	else
	{
		for (var i=0; i<length; i++)
		{
			if (!checkZjhm(myForm.zjlxdm[i], myForm.zjhm[i])
			 || !checkXm(myForm.xm[i])
			 || !checkSzsmdm(myForm.szsmdm[i])
			 || !checkSre(myForm.sre[i])
			 || !checkYnssde(myForm.szsmdm[i], myForm.ynssde[i])
			 || !checkJmje(myForm.jmje[i])
			 || !checkBqskse(myForm.bqskse[i])
			 || !checkWszh(myForm.wszh[i])
			)
			{
				return false;
			}
		}
	}
	return true;
}

function checkZjhm(zjlxdm, zjhm)
{
	if (zjlxdm.value == "2")
	{
		if (!checkIdentityCard(zjhm.value))
		{
			alert("���֤���벻����Ҫ��");
			zjhm.focus();
			return false;
		}
	}
	else
	{
		if (zjhm.value == "")
		{
			alert("֤���������¼��");
			zjhm.focus();
			return false;
		}
	}
	return true;
}

function checkXm(obj)
{
	if (obj.value == "")
	{
		alert("��������Ϊ��");
		obj.focus();
		return false;
	}
	return true;
}

function checkSzsmdm(obj)
{
	if (obj.value.length != 6)
	{
		alert("����ѡ��˰�֣�");
		obj.focus();
		return false;
	}
	return true;
}

function checkMoney(obj, name)
{
	if (obj.value == "")
	{
		alert(name + "����Ϊ��");
		obj.focus();
		return false;
	}
	else if (obj.value <0)
	{
		alert(name + "����Ϊ����");
		obj.focus();
		return false;
	}
	return true;
}

function checkSre(obj)
{
	return checkMoney(obj, "�����");
}

function checkYnssde(szsmdm, ynssde)
{
	if (!checkMoney(ynssde, "Ӧ��˰���ö�"))
	{
		return false;
	}

	for (var i=0; i<szsmynszzs.length; i++)
	{
		if (szsmynszzs[i][0] == szsmdm.value)
		{
			var ynsqss = szsmynszzs[i][1];
			var ynszzs = szsmynszzs[i][2];
			if (ynssde.value > ynsqss && ynssde.value <= ynszzs)
			{
				return true;
			}
			else
			{
				alert("Ӧ��˰���öΧӦ����" + ynsqss + "��" + ynszzs + "֮��");
				ynssde.focus();
				return false;
			}
		}
	}
	return true;
}

function checkJmje(obj)
{
	return checkMoney(obj, "����˰��");
}

function checkBqskse(obj)
{
	return checkMoney(obj, "����ʵ��˰��");
}

function checkWszh(obj)
{
	if (obj.value.length != 8)
	{
		alert("��˰֤�ű���Ϊ8λ");
		obj.focus();
		return false;
	}
	return true;
}

function doReturn()
{
	if (confirm(confirmReturn))
	{
		document.forms[0].actionType.value = "Return";
		document.forms[0].submit();
	}
}

function getSelectTemplate(name)
{
	return eval("document.forms[0].template_"+name);
}

function createSelect(name, template)
{
	var sel = document.createElement("<SELECT name='" + name + "'>");
	for (var i=0; i<template.options.length; i++)
	{
		var option = document.createElement("<OPTION>");
		var tempOption = template.options[i];
		option.text = tempOption.text;
		option.value = tempOption.value;
		sel.options.add(option);
	}
	sel.selectedIndex = template.selectedIndex;
	return sel;
}

function createInput(name)
{
	return document.createElement("<INPUT name='" + name + "'>");
}

function doAdd()
{
	var row = Table_Master.insertRow();

	<%/*���*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";

	<%/*����*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(createSelect("gjdm", getSelectTemplate("gjdm")));

	<%/*֤������*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(createSelect("zjlxdm", getSelectTemplate("zjlxdm")));

	<%/*֤������*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input type='text' name='zjhm' size='18' maxlength='30' class='inputnormal'>"));

	<%/*����*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input type='text' name='xm' size='8' maxlength='15' class='inputnormal'>"));

	<%/*ְҵ*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(createSelect("zydm", getSelectTemplate("zydm")));

	<%/*������Ŀ*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	var szsmSelect = createSelect("szsmdm", getSelectTemplate("szsmdm"));
	szsmSelect.onchange = eSetSl;
	cell.appendChild(szsmSelect);

	<%/*�����*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input type='text' name='sre' onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size='8' maxlength='16'>"));

	<%/*Ӧ��˰���ö�*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input type='text' name='ynssde' onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size='8' maxlength='16'>"));

	<%/*˰��*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input type='text' name='sl' readonly class='inputnoborder' size='3' tabindex='-1'>"));

	setSl(szsmSelect);

	<%/*����˰��*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input type='text' name='jmje' onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size='8' maxlength='16'>"));

	<%/*����ʵ��˰��*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input type='text' name='bqskse' onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size='8' maxlength='16'>"));

	<%/*��˰ƾ֤��*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input type='text' name='wszh' onchange='return (checkvalue(this,0))' size='8' maxlength='8' class='inputnormal'>"));

	<%/*ɾ��*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-center";
	cell.innerHTML = "<a href='#' id='deleteMe' onclick='deleteRow(this);return false;'>ɾ��</a>";

	resetIndex();

	return false;
}

function deleteRow(obj)
{
	if (Table_Master.rows.length == 1)
	{
		alert("Can not delete the last row!!!");
		return false;
	}

	if (!confirm("ȷ��Ҫɾ����"))
	{
		return false;
	}

	var index = findSelfIndex(obj);
	Table_Master.deleteRow(index+1);

	resetIndex();
	return false;
}

function findSelfIndex(obj)
{
	var length = deleteMe.length;
	if (length == null)
	{
		return 0;
	}

	for (var i=0; i<deleteMe.length; i++)
	{
		if (deleteMe[i] == obj)
		{
			return i;
		}
	}
}

function resetIndex()
{
	var rows = Table_Master.rows;
	var length = rows.length;
	for (var i=1; i<length; i++)
	{
		rows[i].cells[0].innerText = i;
	}
}
</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">

<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" valign="top">

    	<jsp:include page="../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="��������˰�걨��ϸ����¼��" />
    	</jsp:include>
    </td>
  </tr>
<!-- self-->
<tr>
 <td>
<html:form method="POST" action="/grsds.do">
	<html:hidden property="actionType"/>
<html:errors/>

<div style="display:none">
<select name="template_gjdm">
<logic:iterate name="grsdsForm" property="gjList" id="data">
	<option value='<bean:write name="data" property="gjdqdm"/>'><bean:write name="data" property="gjdqmc"/></option>
</logic:iterate>
</select>

<select name="template_zjlxdm">
<logic:iterate name="grsdsForm" property="zjlxList" id="data">
	<option value='<bean:write name="data" property="zjlxdm"/>'><bean:write name="data" property="zjlxmc"/></option>
</logic:iterate>
</select>

<select name="template_zydm">
<logic:iterate name="grsdsForm" property="zyList" id="data">
	<option value='<bean:write name="data" property="zydm"/>'><bean:write name="data" property="zymc"/></option>
</logic:iterate>
</select>

<select name="template_szsmdm">
<logic:iterate name="grsdsForm" property="szsmList" id="data">
	<option value='<bean:write name="data" property="szsmdm"/>'><bean:write name="data" property="szsmmc"/></option>
</logic:iterate>
</select>

</div>

<table width="100%"  border="0" align="center" cellspacing="0" bgcolor="#FFFFFF" class="table-99">
        <tr>
          <td class="1-td1">�����еط�˰��ָ�������˰��ϸ�걨��</td>
        </tr>
        <tr>
          <td class="1-td2"> <br>
            <table cellspacing="0" class="table-99">
                    <tr class="black9">
                      <td width="47%" align="left" nowrap>�걨˰������ʱ�ڣ�<bean:write name="grsdsForm" property="sbsssq"/> <html:hidden property="sbsssq"/></td>
                      <td width="36%" align="left" nowrap>�걨���ڣ�<bean:write name="grsdsForm" property="sbrq"/></td>
                      <td width="17%" align="center" nowrap> <div align="right">��λ�������Ԫ</div></td>
                    </tr>
                  </table>

                  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
                    <tr class="black9">
                      <td nowrap class="2-td2-left"><div align="right">˰����������&nbsp;&nbsp;</div></td>
                      <td nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;<bean:write name="grsdsForm" property="jsjdm"/><html:hidden name="grsdsForm" property="jsjdm"/></div></td>
                      <td nowrap class="2-td2-left"><div align="right">�۽�������&nbsp;&nbsp;</div></td>
                      <td nowrap class="2-td2-center"><div align="left">&nbsp;&nbsp;<bean:write name="grsdsForm" property="nsrmc"/><html:hidden name="grsdsForm" property="nsrmc"/></div></td>
                    </tr>
                    <tr>
                      <td nowrap class="2-td2-left"><div align="right">���ڵ�λ������&nbsp;&nbsp;</div></td>
                      <td nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;<input type="text" name="bqdwzrs" maxlength="16" onchange="return (checkvalue(this,0))" value='<bean:write name="grsdsForm" property="bqdwzrs"/>'></div></td>
                      <td nowrap class="2-td2-left"><div align="right">���ڷǵ�λ������&nbsp;&nbsp;</div></td>
                      <td nowrap class="2-td2-center"><div align="left">&nbsp;&nbsp;<input type="text" name="bqfdwzrs" maxlength="16" onchange="return (checkvalue(this,0))" value='<bean:write name="grsdsForm" property="bqfdwzrs"/>'></div></td>
                    </tr>
                  </table>

                  <br>
                  <table id="Table_Master" width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
                    <tr>
                      <td width="3%" nowrap class="2-td1-left">���</td>
                      <td width="4%" nowrap class="2-td1-left">���� </td>
                      <td width="7%" nowrap class="2-td1-left">֤������</td>
                      <td width="10%" nowrap class="2-td1-left">֤������</td>
                      <td width="8%" nowrap class="2-td1-left">����</td>
                      <td width="1%" nowrap class="2-td1-left">ְҵ</td>
                      <td width="1%" nowrap class="2-td1-left">������Ŀ</td>
                      <td width="2%" nowrap class="2-td1-left">�����</td>
                      <td width="4%" nowrap class="2-td1-left">Ӧ��˰���ö�</td>
                      <td width="14%" nowrap class="2-td1-left">˰��</td>
                      <td width="10%" nowrap class="2-td1-left">����˰��</td>
                      <td width="4%" nowrap class="2-td1-left">����ʵ��˰��</td>
                      <td width="4%" nowrap class="2-td1-left">��˰ƾ֤��</td>
                      <td width="6%" nowrap class="2-td1-center">&nbsp;</td>
                    </tr>

<bean:define id="gjList" name="grsdsForm" property="gjList" />
<bean:define id="zjlxList" name="grsdsForm" property="zjlxList" />
<bean:define id="zyList" name="grsdsForm" property="zyList" />
<bean:define id="szsmList" name="grsdsForm" property="szsmList" />

<%
GrsdsForm form = (GrsdsForm)request.getAttribute("grsdsForm");

// �������ݣ����ɱ༭�����Ϊ��ɫ
List deMxList = form.getDeMxList();
int i=0;
if (deMxList != null && deMxList.size() != 0)
{
	for (; i<deMxList.size(); i++)
	{
		Grsdsmx mx = (Grsdsmx)deMxList.get(i);
%>
                    <tr>
                      <td nowrap class="2-td2-left"><font color="red"><%=i+1%></font></td>
                      <td nowrap class="2-td2-left">
                        <html:select disabled="true" property="gjdm" value="<%=mx.getGjdm()%>">
						  <html:options collection="gjList" property="gjdqdm" labelProperty="gjdqmc"/>
						</html:select>
                      </td>
                      <td nowrap class="2-td2-left">
						<html:select disabled="true" property="zjlxdm" value="<%=mx.getZjlxdm()%>">
						  <html:options collection="zjlxList" property="zjlxdm" labelProperty="zjlxmc"/>
						</html:select>
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="zjhm" value="<%=mx.getZjhm()%>" readonly class="noborder-txtleft" tabindex="-1" size="18" maxlength="30">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="xm" value="<%=mx.getXm()%>" readonly class="noborder-txtleft" tabindex="-1" size="8" maxlength="15">
                      </td>
                      <td nowrap class="2-td2-left">
						<html:select disabled="true" property="zydm" value="<%=mx.getZydm()%>">
						  <html:options collection="zyList" property="zydm" labelProperty="zymc"/>
						</html:select>
                      </td>
                      <td nowrap class="2-td2-left">
						<html:select property="szsmdm" disabled="true" value="<%=mx.getSzsmdm()%>">
						  <html:options collection="szsmList" property="szsmdm" labelProperty="szsmmc"/>
						</html:select>
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="sre" readonly class="inputnoborder" tabindex="-1" value="<%=mx.getSre()%>" onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size="8" maxlength="16">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="ynssde" readonly class="inputnoborder" tabindex="-1" value="<%=mx.getYnssde()%>" onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size="8" maxlength="16">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="sl" value="<%if (mx.getSl() != null) out.print(mx.getSl());%>" readonly class="inputnoborder" tabindex="-1" size="3">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="jmje" value="<%=JspUtil.format(mx.getJmje())%>" readonly class="inputnoborder" tabindex="-1" onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size="8" maxlength="16">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="bqskse" value="<%=JspUtil.format(mx.getBqskse())%>" readonly class="inputnoborder" tabindex="-1" onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size="8" maxlength="16">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="wszh" onchange='return (checkvalue(this,0))' size="8" maxlength="8" value="<%=mx.getWszh()%>" class="inputnormal">
                      </td>
                      <td nowrap class="2-td2-center">
                         <a href="#" id="deleteMe" onclick="deleteRow(this); return false;">ɾ��</a>
                      </td>
                    </tr>
<%
	}
}
%>
<%
List mxList = form.getMxList();
int j=0;
if (mxList != null && mxList.size() != 0)
{
	for (; j<mxList.size(); j++)
	{
		// �������ݣ����ɱ༭
		Grsdsmx mx = (Grsdsmx)mxList.get(j);
		if (mx.getSzsmdm().equals(SzsmdmConstant.GRSDS_GXDE))
		{
%>
                    <tr>
                      <td nowrap class="2-td2-left"><%=j+1+i%></td>
                      <td nowrap class="2-td2-left">
                        <html:select disabled="true" property="gjdm" value="<%=mx.getGjdm()%>">
						  <html:options collection="gjList" property="gjdqdm" labelProperty="gjdqmc"/>
						</html:select>
                      </td>
                      <td nowrap class="2-td2-left">
						<html:select disabled="true" property="zjlxdm" value="<%=mx.getZjlxdm()%>">
						  <html:options collection="zjlxList" property="zjlxdm" labelProperty="zjlxmc"/>
						</html:select>
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="zjhm" value="<%=mx.getZjhm()%>" readonly class="noborder-txtleft" tabindex="-1" size="18" maxlength="30">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="xm" value="<%=mx.getXm()%>" readonly class="noborder-txtleft" tabindex="-1" size="8" maxlength="15">
                      </td>
                      <td nowrap class="2-td2-left">
						<html:select disabled="true" property="zydm" value="<%=mx.getZydm()%>">
						  <html:options collection="zyList" property="zydm" labelProperty="zymc"/>
						</html:select>
                      </td>
                      <td nowrap class="2-td2-left">
						<html:select property="szsmdm" disabled="true" value="<%=mx.getSzsmdm()%>">
						  <html:options collection="szsmList" property="szsmdm" labelProperty="szsmmc"/>
						</html:select>
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="sre" readonly class="inputnoborder" tabindex="-1" value="<%=JspUtil.format(mx.getSre())%>" onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size="8" maxlength="16">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="ynssde" readonly class="inputnoborder" tabindex="-1" value="<%=JspUtil.format(mx.getYnssde())%>" onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size="8" maxlength="16">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="sl" value="<%if (mx.getSl() != null) out.print(mx.getSl());%>" readonly class="inputnoborder" tabindex="-1" size="3">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="jmje" value="<%=JspUtil.format(mx.getJmje())%>" readonly class="inputnoborder" tabindex="-1" onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size="8" maxlength="16">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="bqskse" value="<%=JspUtil.format(mx.getBqskse())%>" readonly class="inputnoborder" tabindex="-1" onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size="8" maxlength="16">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="wszh" onchange='return (checkvalue(this,0))' size="8" maxlength="8" value="<%=mx.getWszh()%>" class="inputnormal">
                      </td>
                      <td nowrap class="2-td2-center">
                         <a href="#" id="deleteMe" onclick="deleteRow(this); return false;">ɾ��</a>
                      </td>
                    </tr>
<%
		}
		else
		{
%>
                    <tr>
                      <td nowrap class="2-td2-left"><%=j+1+i%></td>
                      <td nowrap class="2-td2-left">
						<html:select property="gjdm" value="<%=mx.getGjdm()%>">
						  <html:options collection="gjList" property="gjdqdm" labelProperty="gjdqmc"/>
						</html:select>
                      </td>
                      <td nowrap class="2-td2-left">
						<html:select property="zjlxdm" value="<%=mx.getZjlxdm()%>">
						  <html:options collection="zjlxList" property="zjlxdm" labelProperty="zjlxmc"/>
						</html:select>
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="zjhm" value="<%=mx.getZjhm()%>" size="18" maxlength="30" class="inputnormal">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="xm" value="<%=mx.getXm()%>" size="8" maxlength="15" class="inputnormal">
                      </td>
                      <td nowrap class="2-td2-left">
						<html:select property="zydm" value="<%=mx.getZydm()%>">
						  <html:options collection="zyList" property="zydm" labelProperty="zymc"/>
						</html:select>
                      </td>
                      <td nowrap class="2-td2-left">
						<html:select property="szsmdm" onchange="eSetSl();" value="<%=mx.getSzsmdm()%>">
						  <html:options collection="szsmList" property="szsmdm" labelProperty="szsmmc"/>
						</html:select>
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="sre" value="<%=JspUtil.format(mx.getSre())%>" onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size="8" maxlength="16">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="ynssde" value="<%=JspUtil.format(mx.getYnssde())%>" onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size="8" maxlength="16">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="sl" value="<%if (mx.getSl() != null) out.print(mx.getSl());%>" readonly class="inputnoborder" tabindex="-1" size="3">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="jmje" value="<%=JspUtil.format(mx.getJmje())%>" onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size="8" maxlength="16">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="bqskse" value="<%=JspUtil.format(mx.getBqskse())%>" onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size="8" maxlength="16">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="wszh" onchange='return (checkvalue(this,0))' size="8" maxlength="8" value="<%=mx.getWszh()%>" class="inputnormal">
                      </td>
                      <td nowrap class="2-td2-center">
                         <a href="#" id="deleteMe" onclick="deleteRow(this); return false;">ɾ��</a>
                      </td>
                    </tr>
<%
		}
	}
}
else
{
%>
                    <tr>
                      <td nowrap class="2-td2-left"><%=j+1+i%></td>
                      <td nowrap class="2-td2-left">
						<html:select property="gjdm" >
						  <html:options collection="gjList" property="gjdqdm" labelProperty="gjdqmc"/>
						</html:select>
                      </td>
                      <td nowrap class="2-td2-left">
						<html:select property="zjlxdm">
						  <html:options collection="zjlxList" property="zjlxdm" labelProperty="zjlxmc"/>
						</html:select>
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="zjhm" value="" size="18" maxlength="30" class="inputnormal">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="xm" value="" size="8" maxlength="15" class="inputnormal">
                      </td>
                      <td nowrap class="2-td2-left">
						<html:select property="zydm" value="">
						  <html:options collection="zyList" property="zydm" labelProperty="zymc"/>
						</html:select>
                      </td>
                      <td nowrap class="2-td2-left">
						<html:select property="szsmdm" onchange="eSetSl();">
						  <html:options collection="szsmList" property="szsmdm" labelProperty="szsmmc"/>
						</html:select>
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="sre" value="" onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size="8" maxlength="16">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="ynssde" value="" onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size="8" maxlength="16">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="sl" value="" readonly class="inputnoborder" tabindex="-1" size="3">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="jmje" value="" onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size="8" maxlength="16">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="bqskse" value="" onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size="8" maxlength="16">
                      </td>
                      <td nowrap class="2-td2-left">
                        <input type="text" name="wszh" onchange='return (checkvalue(this,0))' size="8" maxlength="8" value="" class="inputnormal">
                      </td>
                      <td nowrap class="2-td2-center">
                         <a href="#" id="deleteMe" onclick="deleteRow(this); return false;">ɾ��</a>
                      </td>
                    </tr>
<%
}
%>
          </td>
        </tr>
	   </table>
<%
if (deMxList != null && deMxList.size() != 0)
{
%>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
		<tr>
			<td colspan="14" class="2-td2-center"><div align="left">
<font color="red">&nbsp;&nbsp;ע�⣺���Ϊ����ɫ������Ϊ���ڶ������ݣ�����û�б��������ݿ���</font></div>
			</td>
		</tr>
<table>
<%
}
%>

    		<table border="0" align="center" width="100%" cellpadding="0" cellspacing = "0">
				<tr align="center"><td><br>
			<img src="<%=static_contextpath%>images/tianjia1.jpg" onmouseover="this.src='<%=static_contextpath%>images/tianjia2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/tianjia1.jpg'" alt="����" onclick="doAdd()" style="cursor:hand">
                      &nbsp;&nbsp;&nbsp;
			<img src="<%=static_contextpath%>images/baocun1.jpg" onmouseover="this.src='<%=static_contextpath%>images/baocun2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/baocun1.jpg'" alt="����" onclick="return doSave()" style="cursor:hand">
                      &nbsp;&nbsp;&nbsp;
			<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="����" onclick="doReturn()" style="cursor:hand">
				</td></tr>
			</table>
	</tr>
</table>
</html:form>
<!--self end-->

    </td>
  </tr>
<tr>
  <td valign="bottom" align="center">
	<%@ include file="../include/bottom.jsp" %>
  </td>
</tr>
 </table>
</body>

</html>
