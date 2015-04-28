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
<title>个人所得税申报明细数据录入</title>
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

// 税种税目税率
StringBuffer szsmsl = new StringBuffer("var szsmsl= [");
// 税种税目与纳税征终止数
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
//		alert("不能选择税种！");
		szsmdm.selectedIndex += 1;
	}

	if (szsmdm.value == "")
	{
		alert("不能选择工薪定额！");
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
					alert("第" + (i+1) + "行与第" + (j+1) + "行重复");
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
		alert("请填写本期单位总人数！");
		myForm.bqdwzrs.focus();
		return false;
	}else if(!(fnIsIntNum(myForm.bqdwzrs.value) || parseFloat(myForm.bqdwzrs.value)==0.0))
	{
		alert("本期单位总人数请填写整数！");
		myForm.bqdwzrs.focus();
		myForm.bqdwzrs.select();
		return false;
	}else if(myForm.bqdwzrs.value > 9999999999999.99)
	{
		alert("本期单位总人数填写数字过大！");
		myForm.bqdwzrs.focus();
		myForm.bqdwzrs.select();
		return false;
        }

	if (myForm.bqfdwzrs.value == "")
	{
		alert("请填写本期非单位总人数！");
		myForm.bqfdwzrs.focus();
		return false;
	}else if(!(fnIsIntNum(myForm.bqfdwzrs.value) || parseFloat(myForm.bqfdwzrs.value)==0.0))
	{
		alert("本期非单位总人数请填写整数！");
		myForm.bqfdwzrs.focus();
		myForm.bqfdwzrs.select();
		return false;
	}else if(myForm.bqfdwzrs.value > 9999999999999.99)
	{
		alert("本期非单位总人数填写数字过大！");
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
			alert("身份证号码不符合要求");
			zjhm.focus();
			return false;
		}
	}
	else
	{
		if (zjhm.value == "")
		{
			alert("证件号码必须录入");
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
		alert("姓名不能为空");
		obj.focus();
		return false;
	}
	return true;
}

function checkSzsmdm(obj)
{
	if (obj.value.length != 6)
	{
		alert("不能选择税种！");
		obj.focus();
		return false;
	}
	return true;
}

function checkMoney(obj, name)
{
	if (obj.value == "")
	{
		alert(name + "不能为空");
		obj.focus();
		return false;
	}
	else if (obj.value <0)
	{
		alert(name + "不能为负数");
		obj.focus();
		return false;
	}
	return true;
}

function checkSre(obj)
{
	return checkMoney(obj, "收入额");
}

function checkYnssde(szsmdm, ynssde)
{
	if (!checkMoney(ynssde, "应纳税所得额"))
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
				alert("应纳税所得额范围应该在" + ynsqss + "与" + ynszzs + "之间");
				ynssde.focus();
				return false;
			}
		}
	}
	return true;
}

function checkJmje(obj)
{
	return checkMoney(obj, "减免税额");
}

function checkBqskse(obj)
{
	return checkMoney(obj, "本期实扣税额");
}

function checkWszh(obj)
{
	if (obj.value.length != 8)
	{
		alert("完税证号必须为8位");
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

	<%/*序号*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";

	<%/*国籍*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(createSelect("gjdm", getSelectTemplate("gjdm")));

	<%/*证件类型*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(createSelect("zjlxdm", getSelectTemplate("zjlxdm")));

	<%/*证件号码*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input type='text' name='zjhm' size='18' maxlength='30' class='inputnormal'>"));

	<%/*姓名*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input type='text' name='xm' size='8' maxlength='15' class='inputnormal'>"));

	<%/*职业*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(createSelect("zydm", getSelectTemplate("zydm")));

	<%/*所得项目*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	var szsmSelect = createSelect("szsmdm", getSelectTemplate("szsmdm"));
	szsmSelect.onchange = eSetSl;
	cell.appendChild(szsmSelect);

	<%/*收入额*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input type='text' name='sre' onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size='8' maxlength='16'>"));

	<%/*应纳税所得额*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input type='text' name='ynssde' onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size='8' maxlength='16'>"));

	<%/*税率*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input type='text' name='sl' readonly class='inputnoborder' size='3' tabindex='-1'>"));

	setSl(szsmSelect);

	<%/*减免税额*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input type='text' name='jmje' onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size='8' maxlength='16'>"));

	<%/*本期实扣税额*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input type='text' name='bqskse' onchange='return (checkvalue(this,0) && formatCurrency(this,0))' size='8' maxlength='16'>"));

	<%/*完税凭证号*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input type='text' name='wszh' onchange='return (checkvalue(this,0))' size='8' maxlength='8' class='inputnormal'>"));

	<%/*删除*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-center";
	cell.innerHTML = "<a href='#' id='deleteMe' onclick='deleteRow(this);return false;'>删除</a>";

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

	if (!confirm("确定要删除吗？"))
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
    		<jsp:param name="name" value="个人所得税申报明细数据录入" />
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
          <td class="1-td1">北京市地方税务局个人所得税明细申报表</td>
        </tr>
        <tr>
          <td class="1-td2"> <br>
            <table cellspacing="0" class="table-99">
                    <tr class="black9">
                      <td width="47%" align="left" nowrap>申报税款所属时期：<bean:write name="grsdsForm" property="sbsssq"/> <html:hidden property="sbsssq"/></td>
                      <td width="36%" align="left" nowrap>申报日期：<bean:write name="grsdsForm" property="sbrq"/></td>
                      <td width="17%" align="center" nowrap> <div align="right">金额单位：人民币元</div></td>
                    </tr>
                  </table>

                  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
                    <tr class="black9">
                      <td nowrap class="2-td2-left"><div align="right">税务计算机代码&nbsp;&nbsp;</div></td>
                      <td nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;<bean:write name="grsdsForm" property="jsjdm"/><html:hidden name="grsdsForm" property="jsjdm"/></div></td>
                      <td nowrap class="2-td2-left"><div align="right">扣缴人名称&nbsp;&nbsp;</div></td>
                      <td nowrap class="2-td2-center"><div align="left">&nbsp;&nbsp;<bean:write name="grsdsForm" property="nsrmc"/><html:hidden name="grsdsForm" property="nsrmc"/></div></td>
                    </tr>
                    <tr>
                      <td nowrap class="2-td2-left"><div align="right">本期单位总人数&nbsp;&nbsp;</div></td>
                      <td nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;<input type="text" name="bqdwzrs" maxlength="16" onchange="return (checkvalue(this,0))" value='<bean:write name="grsdsForm" property="bqdwzrs"/>'></div></td>
                      <td nowrap class="2-td2-left"><div align="right">本期非单位总人数&nbsp;&nbsp;</div></td>
                      <td nowrap class="2-td2-center"><div align="left">&nbsp;&nbsp;<input type="text" name="bqfdwzrs" maxlength="16" onchange="return (checkvalue(this,0))" value='<bean:write name="grsdsForm" property="bqfdwzrs"/>'></div></td>
                    </tr>
                  </table>

                  <br>
                  <table id="Table_Master" width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
                    <tr>
                      <td width="3%" nowrap class="2-td1-left">序号</td>
                      <td width="4%" nowrap class="2-td1-left">国籍 </td>
                      <td width="7%" nowrap class="2-td1-left">证件类型</td>
                      <td width="10%" nowrap class="2-td1-left">证件号码</td>
                      <td width="8%" nowrap class="2-td1-left">姓名</td>
                      <td width="1%" nowrap class="2-td1-left">职业</td>
                      <td width="1%" nowrap class="2-td1-left">所得项目</td>
                      <td width="2%" nowrap class="2-td1-left">收入额</td>
                      <td width="4%" nowrap class="2-td1-left">应纳税所得额</td>
                      <td width="14%" nowrap class="2-td1-left">税率</td>
                      <td width="10%" nowrap class="2-td1-left">减免税额</td>
                      <td width="4%" nowrap class="2-td1-left">本期实扣税额</td>
                      <td width="4%" nowrap class="2-td1-left">完税凭证号</td>
                      <td width="6%" nowrap class="2-td1-center">&nbsp;</td>
                    </tr>

<bean:define id="gjList" name="grsdsForm" property="gjList" />
<bean:define id="zjlxList" name="grsdsForm" property="zjlxList" />
<bean:define id="zyList" name="grsdsForm" property="zyList" />
<bean:define id="szsmList" name="grsdsForm" property="szsmList" />

<%
GrsdsForm form = (GrsdsForm)request.getAttribute("grsdsForm");

// 定额数据，不可编辑，序号为红色
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
                         <a href="#" id="deleteMe" onclick="deleteRow(this); return false;">删除</a>
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
		// 定额数据，不可编辑
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
                         <a href="#" id="deleteMe" onclick="deleteRow(this); return false;">删除</a>
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
                         <a href="#" id="deleteMe" onclick="deleteRow(this); return false;">删除</a>
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
                         <a href="#" id="deleteMe" onclick="deleteRow(this); return false;">删除</a>
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
<font color="red">&nbsp;&nbsp;注意：序号为红颜色的数据为定期定额数据，并且没有保存在数据库中</font></div>
			</td>
		</tr>
<table>
<%
}
%>

    		<table border="0" align="center" width="100%" cellpadding="0" cellspacing = "0">
				<tr align="center"><td><br>
			<img src="<%=static_contextpath%>images/tianjia1.jpg" onmouseover="this.src='<%=static_contextpath%>images/tianjia2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/tianjia1.jpg'" alt="增加" onclick="doAdd()" style="cursor:hand">
                      &nbsp;&nbsp;&nbsp;
			<img src="<%=static_contextpath%>images/baocun1.jpg" onmouseover="this.src='<%=static_contextpath%>images/baocun2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/baocun1.jpg'" alt="保存" onclick="return doSave()" style="cursor:hand">
                      &nbsp;&nbsp;&nbsp;
			<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onclick="doReturn()" style="cursor:hand">
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
