<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="java.util.*"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants" %>
<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.AddCqxxForm" %>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.*" %>

<HTML><HEAD><TITLE>拆迁信息录入</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<script language="JavaScript" type="text/JavaScript" src="<%=static_file%>js/judge.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_file%>js/calculate.js"></script>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
<%
AddCqxxForm form = (AddCqxxForm)session.getAttribute("addCqxxForm");
HashMap szqxMap = form.getSzqxMap();
com.ttsoft.common.model.UserData userdata = (com.ttsoft.common.model.UserData)session.getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA);
String qxdm = userdata.getSsdwdm().substring(0,2);
ArrayList zjlxList = form.getZjlx();
%>

<SCRIPT language=javascript>
<!--
var zjlx = new Array(<%=zjlxList.size()+2%>);
<%
for(int i=0;i<zjlxList.size();i++)
{
	Zjlx zjlx = (Zjlx)zjlxList.get(i);
	out.println("zjlx["+i+"] = [\""+zjlx.getZjlxdm()+"\",\""+zjlx.getZjlxmc()+"\"];");
}
%>
zjlx[<%=zjlxList.size()%>]=["zzjgdm","组织机构"];
zjlx[<%=zjlxList.size()+1%>]=["yyzzh","营业执照号"];

function popUp(){
    var server = '192.100.99.100';
    var port   = '80';
    props=window.open('<%=static_file%>' + '/about/about.htm','poppage','toolbars=0,scrollbars=0,location=0,statusbara=0,menubars=0,resizable=0,width=500,height=267');
}
function doSubmitForm(operationType)
{
  if(operationType=="Save")
  {
    document.forms[0].bcqrlxmc.value = document.forms[0].bcqrlxdm.options[document.forms[0].bcqrlxdm.selectedIndex].text;
    document.forms[0].zjlxmc.value = document.forms[0].zjlxdm.options[document.forms[0].zjlxdm.selectedIndex].text;
    document.forms[0].bclxmc.value = document.forms[0].bclxdm.options[document.forms[0].bclxdm.selectedIndex].text;

    if(document.forms[0].cqrmc.value=="")
    {
      alert("拆迁人名称不可为空！");
      return false;
    }

    if(document.forms[0].bcqrmc.value=="")
    {
      alert("被拆迁人名称不可为空！");
      return false;
    }

    if(document.forms[0].cqxxdz.value=="")
    {
      alert("被拆迁详细地址不可为空！");
      return false;
    }
    if(document.forms[0].cqxmmc.value=="")
    {
      alert("拆迁项目名称不可为空！");
      return false;
    }

    if(document.forms[0].bclxdm.value=="<%=Constants.BCLX_HB%>")
    {
      if(document.forms[0].bcje.value=="")
      {
        alert("补偿金额不可为空！");
        return false;
      }else if (!FN_QSCheckNumberDigit(document.forms[0].bcje.value,2,"补偿金额"))
      {
        document.forms[0].bcje.focus();
        return false;
      }
    }
    if(document.forms[0].bclxdm.value=="<%=Constants.BCLX_HH%>")
    {
      if(document.forms[0].bcje.value=="")
      {
        alert("补偿金额不可为空！");
        return false;
      }
      if(document.forms[0].bcmj.value=="")
      {
        alert("补偿面积不可为空！");
        return false;
      }
      if (!FN_QSCheckNumberDigit(document.forms[0].bcje.value,2,"补偿金额"))
      {
        document.forms[0].bcje.focus();
        return false;
      }
      if (!FN_QSCheckNumberDigit(document.forms[0].bcmj.value,2,"补偿面积"))
      {
        document.forms[0].bcmj.focus();
        return false;
      }
    }
    if(document.forms[0].bclxdm.value=="<%=Constants.BCLX_SW%>")
    {
      if(document.forms[0].bcmj.value=="")
      {
        alert("补偿面积不可为空！");
        return false;
      }else if (!FN_QSCheckNumberDigit(document.forms[0].bcmj.value,2,"补偿面积"))
      {
        document.forms[0].bcmj.focus();
        return false;
      }
    }
  }
  document.forms[0].operationType.value=operationType;
  document.forms[0].submit();
}

function changeBclx()
{
  if(document.forms[0].bclxdm.value=="2")
  {
    document.forms[0].bcmj.disabled=false;
    document.forms[0].bcje.value="";
    document.forms[0].bcje.disabled=true;
  }
  if(document.forms[0].bclxdm.value=="3")
  {
    document.forms[0].bcmj.disabled=false;
    document.forms[0].bcje.disabled=false;
  }
  if(document.forms[0].bclxdm.value=="1")
  {
    document.forms[0].bcmj.value="";
    document.forms[0].bcmj.disabled=true;
    document.forms[0].bcje.disabled=false;
  }
}

function fillZjlx()
{
  var optsize = document.forms[0].zjlxdm.length;
  for(var i=0;i<optsize;i++)
  {
    document.forms[0].zjlxdm.options.remove(0);
  }
  for(var i=0;i<zjlx.length;i++)
  {
    var newOpt = document.createElement("<OPTION>");
    newOpt.value = zjlx[i][0];
    newOpt.text = zjlx[i][1];
    document.forms[0].zjlxdm.options.add(newOpt);
  }
}


function filterZjlx()
{
  if(document.forms[0].bcqrlxdm.options[document.forms[0].bcqrlxdm.selectedIndex].value == "0")
  {
    fillZjlx();
    for(var i=0;i<document.forms[0].zjlxdm.length;i++)
    {
      if(document.forms[0].zjlxdm.options[i].value =="01" ||document.forms[0].zjlxdm.options[i].value =="yyzzh" ||document.forms[0].zjlxdm.options[i].value == "zzjgdm")
      {
        document.forms[0].zjlxdm.options.remove(i);
        i--;
      }
    }
  }
  if(document.forms[0].bcqrlxdm.options[document.forms[0].bcqrlxdm.selectedIndex].value == "1")
  {
    fillZjlx();
    for(var i=0;i<document.forms[0].zjlxdm.length;i++)
    {
      if(document.forms[0].zjlxdm.options[i].value !="yyzzh" && document.forms[0].zjlxdm.options[i].value != "zzjgdm" && document.forms[0].zjlxdm.options[i].value != "")
      {
        document.forms[0].zjlxdm.options.remove(i);
        i--;
      }
    }
  }
}
//-->
</SCRIPT>

</HEAD>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0" onload="filterZjlx()">
 <jsp:include page="/include/Header.jsp" flush="true">
   <jsp:param name="subtitle" value="拆迁信息&gt;录入"/>
   <jsp:param name="helpURL" value=""/>
 </jsp:include>
<br>
<table align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <td vAlign=top>
      <table align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <td class=1-td1>拆迁信息录入</td></TR>
        <TR>
          <td class=1-td2 vAlign=top>
            <html:form action="/cqxx/addCqxx.do">
            <input type="hidden" name="operationType" value="">
            <br>
             <table border="0" cellSpacing=0 class=table-60>
              <TBODY>
               <TR>
                <td class=2-td1-center width="50%">
                  拆迁人名称&nbsp;
                </td>
                <td class="2-td2-t-right" width="50%">
                  <DIV align=left><html:text property="cqrmc" maxlength=""/><font color="red">*</font></DIV>
                </td>
              </TR>
               <TR>
                <td class="2-td1-Bcenter">
                  拆迁范围&nbsp;
                </td>
                <td class="2-td2-right">
                  <DIV align=left>
                    <html:text property="cqfw" maxlength=""/>
                  </DIV>
                </td>
              </TR>
               <TR>
                <td class=2-td1-Bcenter width="50%">
                  被拆迁人名称&nbsp;
                </td>
                <td class="2-td2-right" width="50%">
                  <DIV align=left><html:text property="bcqrmc" maxlength="" /><font color="red">*</font></DIV>
                </td>
              </TR>
               <TR>
                <td class=2-td1-Bcenter width="50%">
                  被拆迁人类型&nbsp;
                </td>
                <td class="2-td2-right" width="50%">
                  <html:hidden property="bcqrlxmc"/>
                  <DIV align=left>
                    <html:select property="bcqrlxdm" onchange="filterZjlx()">
                      <html:option value="0">个人</html:option>
                      <html:option value="1">非个人</html:option>
                    </html:select>
                  </DIV>
                </td>
              </TR>
               <TR>
                <td class=2-td1-Bcenter width="50%">
                  被拆迁人证件类型&nbsp;
                </td>
                <td class="2-td2-right" width="50%">
                  <html:hidden property="zjlxmc">
                  </html:hidden>
                  <DIV align=left>
                    <bean:define id="list" name="addCqxxForm" property="zjlx" />
                    <html:select property="zjlxdm">
                      <html:options collection="list" labelProperty="zjlxmc" property="zjlxdm"/>
                    </html:select>
                  </DIV>
                </td>
              </TR>
               <TR>
                <td class=2-td1-Bcenter width="50%">
                  被拆迁人证件号码&nbsp;
                </td>
                <td class="2-td2-right" width="50%">
                  <DIV align=left><html:text property="zjhm" maxlength="" /></DIV>
                </td>
              </TR>
              <tr>
                <td class="2-td1-Bcenter">被拆迁详细地址</td>
                <td class="2-td2-right">
                  <DIV align=left>
                    <html:text property="cqxxdz" maxlength="" /><font color="red">*</font>
                  </DIV>
                </td>
              </tr>
               <TR>
                <td class=2-td1-Bcenter width="50%">
                  补偿类型&nbsp;
                </td>
                <td class="2-td2-right" width="50%">
                  <html:hidden property="bclxmc">
                  </html:hidden>
                  <DIV align=left>
                    <html:select property="bclxdm" onchange="changeBclx()">
                      <option value="<%=Constants.BCLX_HB%>" selected="selected">货币</option>
                      <option value="<%=Constants.BCLX_HH%>" >混合</option>
                      <option value="<%=Constants.BCLX_SW%>">实物</option>
                    </html:select>
                  </DIV>
                </td>
              </TR>
              <tr>
                <td class="2-td1-Bcenter">补偿金额</td>
                <td class="2-td2-right">
                  <DIV align=left>
                    <html:text property="bcje" maxlength="" />
                  </DIV>
                </td>
              </tr>
              <tr>
                <td class="2-td1-Bcenter">补偿面积</td>
                <td class="2-td2-right">
                  <DIV align=left>
                    <html:text property="bcmj" maxlength="" />
                  </DIV>
                </td>
              </tr>
              <tr>
                <td class="2-td1-Bcenter">补偿房屋明细座落地址</td>
                <td class="2-td2-right">
                  <DIV align=left>
                    <html:text property="bcfwdz" maxlength="" />
                  </DIV>
                </td>
              </tr>
              <tr>
                <td class="2-td1-Bcenter">拆迁项目名称</td>
                <td class="2-td2-right">
                  <DIV align=left>
                    <html:text property="cqxmmc" maxlength="" /><font color="red">*</font>
                  </DIV>
                </td>
              </tr>
              <tr>
                <td class="2-td1-Bcenter">拆迁面积</td>
                <td class="2-td2-right">
                  <DIV align=left>
                    <html:text property="cqmj" maxlength="" />
                  </DIV>
                </td>
              </tr>
              <tr>
                <td class="2-td1-Bcenter">拆迁许可证号</td>
                <td class="2-td2-right">
                  <DIV align=left>
                    <html:text property="cqxkzh" maxlength="" />
                  </DIV>
                </td>
              </tr>
              <tr>
                <td class="2-td1-Bcenter">共居人姓名</td>
                <td class="2-td2-right">
                  <DIV align=left>
                    <html:text property="gjrmc" maxlength="50" />
                  </DIV>
                </td>
              </tr>
              <tr>
                <td class="2-td1-Bcenter">所在区县</td>
                <td class="2-td2-right">
                  <DIV align=left>
                    <select name="szqx">
                      <%
                      Object[] qxdmArray = szqxMap.keySet().toArray();
                      for(int i=0;i<qxdmArray.length;i++)
                      {
                        String qxdmTmp = (String)qxdmArray[i];
                        if(qxdmTmp.equals(qxdm))
                        {
                          out.print("<option value=\""+qxdmTmp+"\" selected>"+szqxMap.get(qxdmArray[i])+"</option>");
                        }
                        else
                        {
                          out.print("<option value=\""+qxdmTmp+"\">"+szqxMap.get(qxdmArray[i])+"</option>");
                        }
                      }
                      %>
                    </selct>
                  </DIV>
                </td>
              </tr>
            </table>
            <BR>
      <DIV align=center>
        <IMG name="query"
          onMouseOver="MM_swapImage('baocun1','','<%=static_file%>images/baocun2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/baocun1.jpg"
          onclick = "doSubmitForm('Save');"
          width="79" height="22" id="chaxun1" alt="保存" style="cursor:hand">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <IMG name="back"
          onclick="doSubmitForm('Return');"
          onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="退出" style="cursor:hand">
      </DIV><BR>

</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
