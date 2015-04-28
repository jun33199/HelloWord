<%@page contentType="text/html; charset=GBK"%>
<%@ page import="com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfqxwhItem"%>
<%@ page import="java.util.ArrayList"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/control.tld" prefix="control"%>


<%@include file="/include/include.jsp"%>

<HTML>

<HEAD><TITLE>存量房修改权限维护</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/judge.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/calculate.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>/js/function.js" type=text/JavaScript></SCRIPT>
<script language=JavaScript src="../js/clfgl_DynamicTable.js" type="text/javascript"></script>

<script language="JavaScript" src="../js/list.js" type=text/JavaScript></script>
<script LANGUAGE="javascript" src="../js/DTColumn.js" type=text/JavaScript></script>
<script LANGUAGE="javascript" src="../js/DTColumn_Check.js" type=text/JavaScript></script>
<script LANGUAGE="javascript" src="../js/DynamicTable.js" type=text/JavaScript></script>
<script LANGUAGE="javascript" src="../js/DataAwareTable.js" type=text/JavaScript></script>
<script LANGUAGE="javascript" src="../js/Const.js" type=text/JavaScript></script>

<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">

<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="存量房管理&gt;存量房修改权限维护"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<html:form action="/clfgl/clfxgqxwh.do" type="POST">
<html:hidden property="operationType"/>
<html:hidden property="dyz"></html:hidden>
<html:hidden name="ClfqxwhForm" property="allNewAddInfo"></html:hidden>
<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width="80%">
  <TBODY>
  <TR>
    <TD vAlign=top>
      <table align="center" cellspacing="0" class="table-99">
	  <TBODY>
        <tr>
          <td class="1-td1"><a>存量房修改权限维护</a></td>
        </tr>
        <tr>
          <td class="1-td2"> 
            <br>
            <table cellspacing="0" border="0" class="table-99" id="TABLE_LIST">
              <tr>
                <td width="8%" class="2-td1-left">序号</td>
				<td width="40%" class="2-td1-left">税务人员登陆用户名</td>
                <td width="40%" class="2-td1-left">名称（姓名）</td>
				<td width="17%" class="2-td1-center">操作</td>
              </tr>
              <logic:iterate id="dataList"  indexId="index" name="ClfqxwhForm" property="jycsList">
              <tr>
                <td class="2-td2-left"><%=index.intValue()+1%></td>
                <td class="2-td2-left"><bean:write name="dataList" property="dyz"/>&nbsp;</td>
                <td class="2-td2-left"><bean:write name="dataList" property="cslxms"/>&nbsp;</td>
                <td class="2-td2-center"><input type="button" onclick="deL('<bean:write name="dataList" property="dyz"/>')" value="删除"></td>
              </tr>
              </logic:iterate>
            </table>
            
            
            <br>
			<table  border=0 cellSpacing=0 class=tabled-99 width="100%" id="newAddTab">
				<tr>
					<td colspan="7" class=2-td1-center>新增操作人员</td>
				</tr>
                            <tr >
	                            <td colspan="7" align="center" class="2-td2-center">
	                            <img onClick="addLabelCol('labelsample');" alt="新增" style="cursor:hand" onMouseOver="MM_swapImage('add','','<%=static_file%>images/xinzeng2.jpg',1)" onMouseOut="MM_swapImgRestore()" id="add" src="<%=static_file%>images/xinzeng1.jpg" width="79" height="22"  >
	                            <img onClick="removeLabelCol('newAddTab');" alt="删除" style="cursor:hand" onMouseOver="MM_swapImage('del','','<%=static_file%>images/shanchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" id="del" src="<%=static_file%>images/shanchu1.jpg" width="79" height="22"  >
	                                <!-- <input onclick="getMMFXXContext('sellTab');" type="button" value="【提交】" name="delButton"> -->
	                            </td>
                            </tr>									
			</table> 
			
			<table style="display:none"  >
   		    <tr id="labelsample_title" height="24">
				<td class=2-td2-left  width="20%">税务人员登陆用户名</td>
				<td class=2-td2-left width="20%">名称（姓名）</td>								
				<td class=2-td2-left>是否开通权限</td>
				<td class=2-td2-center>选择删除项</td>
   		    </tr>
   		    			
   		    <tr id="labelsample" height="24">
   		    <td class=2-td2-left  width="20%"><input id="swry_ID" type="text"></td>
				<td class=2-td2-left  width="20%"><input id="swry_mc" type="text"></td>
				<td class=2-td2-left width="20%">
					<select id="swry_kt">
						<option value="0">开通</option>
						<option value="1">暂不开通</option>
					</select>
				</td>								
				<td class=2-td2-center align="center" width="5%" ><input type="checkbox" name="labelFlag" id="labelFlag" value="" ></td>
   		    </tr>
   	    </table>			           
            
            

              <br><hr>

      		<DIV align=center>
      			<img onclick="doSubmitForm('Save')" alt="保存"  onMouseOver="MM_swapImage('baocun1','','<%=static_file%>images/baocun2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/baocun1.jpg" name="baocun" width="79" height="22" id="baocun1"  style="cursor:hand">
      			<img onclick="doSubmitForm('Return')" alt="退出"  onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg" name="back" width="79" height="22" id="tuichu1"  style="cursor:hand">
      		</DIV>
      		
      		<hr width="99%" class="hr1" size=1>
      		
      		<table border="0" cellspacing="0" class="table-99">
              <tr>
                <td><hr width="100%" class="hr1" size=1></td>
              </tr>
            </table>

           <table width="75%" border="0" cellspacing="0" class="table-99">
              <tr>
                <td width="22%" class="2-td2-t-left">
                  <div align="right">录入人</div></td>
                <td width="31%" class="2-td2-t-left">
                  <div align="left">&nbsp;
                    <bean:write name="ClfqxwhForm" property="lrr" />
                </div></td>
                <td width="16%" class="2-td2-t-left">
                  <div align="right">录入日期</div></td>
                <td width="31%" class="2-td2-t-center">
                  <div align="left">&nbsp;
                <bean:write name="ClfqxwhForm" property="lrrq" />
                </div></td>
              </tr>
            </table>
      <BR>
</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>

<style type="text/css">

</style>

<script language=JavaScript type='text/JavaScript'>


function deL(obj)
{
	if(obj == null || obj == "" ){
		alert('删除失败，删除对象不存在，请重试！');
		return false;
		
	}
	
	
	if(!confirm("该操作将从系统中删除该条记录，请确认 是否继续...")){
		return false;
	}
	
	document.forms[0].dyz.value= obj;
	
	doSubmitForm("Delete");
	
}




function doSubmitForm(operationType)
{
	if(operationType=="Save")
	{
		if(!getMMFXXContext('newAddTab')){
			return false;
		}
	}
		
    document.all.operationType.value = operationType;
    document.forms[0].submit();
}
</script>













