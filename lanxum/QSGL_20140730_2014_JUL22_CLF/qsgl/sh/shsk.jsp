<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.model.bo.HdtzsBo"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Hdjmmx"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.ActionUtil"%>
<%@ page import="java.util.*"%>

<HTML><HEAD><TITLE>复核及收款</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/qscommon.js" type=text/JavaScript></SCRIPT>
<script language="javascript" src="../js/gmit_selectcontrol.js"></script>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>
        <bean:define id="shForm" name="shskForm" type="com.creationstar.bjtax.qsgl.VisionLogic.form.ShskForm"/>
	<bean:define id="shskbo" name="shskForm" property="data" type="com.creationstar.bjtax.qsgl.model.bo.ShskBo"/>

<script language=JavaScript type='text/JavaScript'>
	<% if (shskbo.isPersonChecked()) { %>
    var arySelect_grxx = <%=ActionUtil.displaySelectDataSource(shForm.getNsrList(),null,"jsjdm","nsrmc")%>;//个人信息选择框
	<% }	  %>


function doSubmitForm(operationType){
  document.all.operationType.value=operationType;
  document.forms[0].submit();
}


function doPrintJks()
{
    if(confirm("该操作将修改数据库中的数据,且不能自动恢复,请确认") )
    {
    document.all.operationType.value="PrintJks";
    document.forms[0].submit();
    }
}

function doPrintWsz()
{
    if(confirm("该操作将修改数据库中的数据,且不能自动恢复,请确认") )
    {

    document.all.operationType.value="PrintWsz";
    document.forms[0].submit();
    }
}
//改变打印产权人选项时，修改产权人列表相应产权人不可选择
function changeSel()
{
	  	  <% if (shskbo.isPersonChecked()) { %>
  var v=document.forms[0].jsjdm.value;
  if (v!=null){
	var len= grTable.rows.length-1;
    if (len<=1){
		document.forms[0].xsjsjdm.checked=false;
		document.forms[0].xsjsjdm.disabled=true;
		return;
	}
       for (var i=0;i<document.forms[0].xsjsjdm.length;i++)
  {
		document.forms[0].xsjsjdm[i].disabled=false;
		document.forms[0].xsjsjdm[i].checked=true;
	if (document.forms[0].xsjsjdm[i].value==v)
	{
		document.forms[0].xsjsjdm[i].checked=false;
		document.forms[0].xsjsjdm[i].disabled=true;
	}
  }

  }
  	  	  <% }	  %>

}
	function initPage()
	{
        // 纳税人名称* 联系电话* 身份证件类型* 身份证件号码* 国籍* 是否主产权人*

	  	  <% if (shskbo.isPersonChecked()) { %>

         _addOptionsToSelect(document.forms[0].jsjdm,arySelect_grxx);
         document.forms[0].jsjdm.options(0).selected=true;
         changeSel();
	  	  <% }	  %>

    }
</SCRIPT>


<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0" onLoad="initPage();">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="审核&gt;复核及收款"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>复核及收款</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>   <BR>

        <html:form action="/sh/shsk.do">
        <html:hidden property="operationType"/>
        <html:hidden property="jkfsdm"/>
   		<TABLE border="0" cellSpacing=0 class=table-99>
		  <TBODY>
		   <TR>
			<TD class="2-td1-left" width = "25%">申报表号</TD>
			<TD class="2-td1-left" width = "13%">纳税人名称</TD>
			<TD class="2-td1-left" width = "30%">土地、房屋坐落地址</TD>
			<TD class="2-td1-left" width = "12%">成交价格</TD>
			<TD class="2-td1-center" width = "20%">备注</TD>
		  </TR>
		   <TR>
			  <td class="2-td2-left">
				<bean:write name="shskForm" property="sbbh"/>
			  </td>
			  <td class="2-td2-left">
 	  	  <% if (!shskbo.isPerson()) { %>
 	  	  <bean:write name="shskForm" property="nsrmc"/>
	          <%  } else if (shskbo.isPersonChecked()) { %>

	              <select name="jsjdm"  onChange="changeSel()">
           </select>
	          <%  } else  {  %>
   			 <%=ActionUtil.getNsrmc(shForm.getNsrList(),null)%>
                <%      }      %>

			  </td>
			  <td class="2-td2-left"><bean:write name="shskForm" property="tdfwzldz"/></td>
			  <td class="2-td2-left"><bean:write name="shskForm" property="cjjgrmb"/></td>
			  <td class="2-td2-center"><bean:write name="shskForm" property="bz"/>&nbsp;</td>
			</TR>
	  	  <% if (shskbo.isPersonChecked()) { %>

		   <TR>
			<TD class="2-td2-center" width = "100%"  colspan="5">选择要打印的产权人</TD>
</tr>
		  <TR>
			<TD valign="top" width = "100%"  colspan="5">
<table id="grTable" border="0" cellpadding="0" cellspacing="0" class="table-99">
  <tr>
	<td width="15%" class="2-td2-left">
	纳税人名称
	</td>
    <td width="20%" class="2-td2-left">联系电话</td>
    <td width="20%" class="2-td2-left">身份证件类型</td>
    <td width="20%" class="2-td2-left">身份证件号码</td>
    <td width="10%" class="2-td2-left">国籍</td>
    <td width="10%" class="2-td2-left">产权人类型</td>
     <td width="5%" class="2-td2-center">&nbsp;</td>
  </tr>
 <logic:iterate id="nsrdata" indexId="index" length="length" name="shForm" property="nsrList">
  <tr>
	<td width="15%" class="2-td2-left"><bean:write name="nsrdata" property="nsrmc" /></td>
    <td width="20%" class="2-td2-left"><bean:write name="nsrdata" property="lxdh" />&nbsp;</td>
    <td width="20%" class="2-td2-left"><bean:write name="nsrdata" property="sfzjlxmc" /></td>
    <td width="20%" class="2-td2-left"><bean:write name="nsrdata" property="sfzjhm" /></td>
    <td width="10%" class="2-td2-left"><bean:write name="nsrdata" property="gjmc" /></td>
    <td width="10%" class="2-td2-left">
<logic:equal name="nsrdata" property="cqrlx" value="1">
    共有产权人
</logic:equal>
<logic:equal name="nsrdata" property="cqrlx" value="0">
    主产权人
</logic:equal>
    </td>
        <td width="5%" class="2-td2-center">

  <input type="checkbox" name="xsjsjdm" value="<bean:write name="nsrdata" property="jsjdm" />">

        </td>
  </tr>
  </logic:iterate>

</table>
</TD>
</tr>
  	  	  <% }	  %>


		 </TABLE><br>



      <bean:define id="hdtzsList" name="shskForm" property="hdList" type="java.util.ArrayList"/>
      <%
        if ((hdtzsList != null) && (hdtzsList.size() > 0))
        {
      %>
		 <TABLE border="0" cellSpacing=0 class=table-99>
		  <TBODY>
		   <TR>
			<TD class="2-td1-left" width = "25%">核定书号</TD>
			<TD class="2-td1-left" width = "10%">计税依据</TD>
			<TD class="2-td1-left" width = "10%">拆迁减免税额</TD>
			<TD class="2-td1-left" width = "10%">普通住房减免税额</TD>
			<TD class="2-td1-left" width = "10%">减免税额</TD>
			<TD class="2-td1-center" width = "20%">核定日期</TD>
		  </TR>
      <logic:iterate id="hdtzs" indexId="hdindex" length="length" name="shskForm" property="hdList"
            type="com.creationstar.bjtax.qsgl.model.bo.HdtzsBo">
		   <TR>
			  <td class="2-td2-left">&nbsp;
				<bean:write name="hdtzs" property="voHdtzs.hdtzsh"/>
			  </td>
			  <td class="2-td2-left">&nbsp;
			  <%=DataConvert.BigDecimal2String(hdtzs.getVoHdtzs().getJsyj())%>
				</td>
			  <%
                   HashMap nrMap = (HashMap)hdtzs.getJmnrMap();
                   Hdjmmx cqjm = null;
                   Hdjmmx ptjm = null;
                   if (nrMap != null)
                   {
                      cqjm = (Hdjmmx)nrMap.get(Constants.JMZC_CQFW);
                      ptjm = (Hdjmmx)nrMap.get(Constants.JMZC_PTZZ);
                   }
              %>
              <%
                   if (cqjm != null)
                   {
			  %>
			  <td class="2-td2-left">&nbsp;<%=DataConvert.BigDecimal2String(cqjm.getJmje())%></td>
			  <%
			       }
			       else
			       {
			  %>
			  <td class="2-td2-left">&nbsp;0.00</td>
			  <%
			       }
			  %>
         <%
            if (ptjm != null)
             {
			  %>
			  <td class="2-td2-left">&nbsp;<%=DataConvert.BigDecimal2String(ptjm.getJmje())%></td>
			  <%
			       }
			       else
			       {
			  %>
			  <td class="2-td2-left">&nbsp;0.00</td>
			  <%
			       }
			  %>
			  <td class="2-td2-left">&nbsp;
				<%=DataConvert.BigDecimal2String(hdtzs.getSpjmse())%>
			  </td>
			  <td class="2-td2-center">&nbsp;<%=DataConvert.TimeStamp2String(hdtzs.getVoHdtzs().getCjrq())%></td>
			</TR>
	</logic:iterate>
		 </TABLE><br>
    <%
    }
    %>

	<TABLE border="0" cellSpacing=0 class=table-89>
		<TBODY>
		<TR>
			<TD class="2-td2-t-left" width="9%">税率</TD>
		<TD width="11%" class="2-td2-t-left">&nbsp;<bean:write name="shskForm" property="sl"/></TD>
		<TD width="19%" class="2-td2-t-left">计税依据</TD>
		<TD width="12%" class="2-td2-t-left" >&nbsp;<bean:write name="shskForm" property="jsyj"/></TD>
		<TD width="19%" class="2-td2-t-left">计征税额</TD>
		<TD width="30%" class="2-td2-t-center" >&nbsp;<bean:write name="shskForm" property="jsje"/></TD>
		</TR>
		<TR><TD class="2-td2-left" width="20%" VALIGN="TOP" COLSPAN = "1" >
			拆迁减免金额</TD>
		<TD class="2-td2-left" width="20%" VALIGN="TOP" COLSPAN="1">&nbsp;<bean:write name="shskForm" property="cqjmje"/></TD>
		<TD class="2-td2-left" width="22%" VALIGN="TOP">普通住宅减税金额</TD>
		<TD class="2-td2-center" width="20%" VALIGN="TOP">&nbsp;<bean:write name="shskForm" property="ptzzjsje"/></TD>
	<TD class="2-td2-left" width="22%" VALIGN="TOP">经济适用房减税金额</TD>
		<TD class="2-td2-center" width="20%" VALIGN="TOP">&nbsp;<bean:write name="shskForm" property="jjsyfjsje"/></TD>
    </TR>
		<TR><TD class="2-td2-left" width="20%" VALIGN="TOP" COLSPAN=2>应纳税额</TD>
		<TD class="2-td2-center" width="80%" VALIGN="TOP" COLSPAN=4>&nbsp;<bean:write name="shskForm" property="ynse"/></TD>
		</TR>
		<TR><TD class="2-td2-left" WIDTH="9%" VALIGN="TOP">备注</TD>
		<TD class="2-td2-center" WIDTH="91%" VALIGN="TOP" COLSPAN=5>&nbsp;<bean:write name="shskForm" property="bz"/>&nbsp;</TD>
		</TR>
	</TABLE><br>

	<DIV align=center>
	  	  <% if (!shskbo.isChecked())
	  	     {
	  	  %>

	  	  			            		<img alt=复核   id=fuhe name=fuhe
		onclick="doSubmitForm('Check')" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('fuhe','','<%=static_file%>images/b_fh2.jpg',1)"
		src="<%=static_file%>images/b_fh1.jpg" style="CURSOR: hand">
          <%
             }
             else if(shskbo.getYnse().doubleValue()>0)
             {
          %>

          				 <% if (!Constants.WSZ_JSFS_ZXJN.equals(shskbo.getJsfsdm()))
	  	     					{
	  	 						 %>
	  	  			            		<img alt=打印完税证   id=printwsz name=printwsz
		onclick="doPrintWsz()" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('printwsz','','<%=static_file%>images/q_dywsz2.jpg',1)"
		src="<%=static_file%>images/q_dywsz1.jpg" style="CURSOR: hand">

					          <%

					             }
					          %>


						  	  			            		<img alt=打印缴款书   id=printjks name=printjks
		onclick="doPrintJks()" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('printjks','','<%=static_file%>images/q_dyjks2.jpg',1)"
		src="<%=static_file%>images/q_dyjks1.jpg" style="CURSOR: hand">


          <%

             }
          %>
      <IMG alt=退出 height=22 id=tuichu name=tuichu
      onclick="doSubmitForm('Return');"
          onMouseOver="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="退出" style="cursor:hand">
    </DIV><BR>

</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
