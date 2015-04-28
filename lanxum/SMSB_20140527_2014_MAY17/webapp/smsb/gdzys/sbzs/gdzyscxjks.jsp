<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.ttsoft.framework.util.JspUtil"%>
<%@ page import="com.ttsoft.common.util.CodeConstants"%>
<%@ page import="com.ttsoft.bjtax.smsb.lwpk.model.PKJBSJModel" %>
<%@ page import="com.ttsoft.bjtax.smsb.lwpk.model.PLKKDHCXModel" %>
<%@ page import="java.util.*" %>
<%


%>
<html:html>
<head>
<title>耕地占用税撤销缴款书</title>
<link href="../../css/text.css" rel="stylesheet" type="text/css">
<link href="../../css/top-box.css" rel="stylesheet" type="text/css">
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../../js/treatImage.js"></script>
<script language="JavaScript" src="../../js/smsb_common.js"></script>
<script language="JavaScript" src="../../js/DTable.js"></script>
<script language="JavaScript" src="../../js/InputSelect.js"></script>
<script language="JavaScript" src="../../js/function.js"></script>
<script language="javascript" src="../../js/gmit_selectcontrol.js"></script>
</head>
<SCRIPT LANGUAGE="JavaScript">
	 function doit(ope){
		 
		 if(document.forms[0].nsrmc.value==""
				 &&document.forms[0].jsjdm.value==""
				 &&document.forms[0].pzjdwh.value==""
				 &&document.forms[0].sbbxlh.value==""){
				alert("查询条件纳税人名称、计算机代码、批准占地文号、申报序列号不能全为空！");
				return false;
	     }
		doSubmitForm(ope);
	}
	 function doCkJks(){
		 var cxJgTsFlag = "<bean:write name="gdzysCxjksForm" property="cxJgTsFlag" />";
		 if(cxJgTsFlag=='1'||(cxJgTsFlag=='0'&&document.gdzysCxjksForm.actionType.value=='Show')){
				alert("请选择要出具的缴款书数据！");
				return false;
		 }else{
		 var xuanze=document.getElementsByName("xuanze");
		 for(var i=0;i<xuanze.length;i++){
		   if (xuanze[i].checked)
			{
			    //alert(xuanze[i].value);
			    var temp=xuanze[i].value.split("#");
				document.gdzysCxjksForm.cxSbbxlh.value=temp[0];
				document.gdzysCxjksForm.cxJkpzh.value=temp[1];
			    break;
			}
		 }
		 document.gdzysCxjksForm.target="_blank";
		 doSubmitForm("CkJks");
		 document.gdzysCxjksForm.target="";
		 }
	 }
	 function doDelJks(){
		 var cxJgTsFlag = "<bean:write name="gdzysCxjksForm" property="cxJgTsFlag" />";
		 if(cxJgTsFlag=='1'||(cxJgTsFlag=='0'&&document.gdzysCxjksForm.actionType.value=='Show')){
				alert("请选择要撤销的缴款书数据！");
				return false;
		 }else{
		 var xuanze=document.getElementsByName("xuanze");
		 for(var i=0;i<xuanze.length;i++){
		   if (xuanze[i].checked)
			{
			   //alert(xuanze[i].value);
			    var temp=xuanze[i].value.split("#");
				document.gdzysCxjksForm.cxSbbxlh.value=temp[0];
				document.gdzysCxjksForm.cxJkpzh.value=temp[1];
				document.gdzysCxjksForm.cxSbbh.value=temp[2];
				document.gdzysCxjksForm.cxJsjdm.value=temp[3];
			    break;
			}
		 }
		 if (confirm("该操作将删除页面中一行的数据,且不能自动恢复,请确认")){
	          document.gdzysCxjksForm.target="";
	          doSubmitForm("DelJks");
	     }
		 }
		 //document.gdzysCxjksForm.target="_blank";
		 //doSubmitForm("DelJks");
		// document.gdzysCxjksForm.target="";
	 }
	 
	 
	//进入页面时将焦点设为查询年度录入
  // 页面进入焦点确定
   function fnOnLoad()
  {

  }
</SCRIPT>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
  <%@ include file="../../include/header.jsp" %> 
<html:form action="/webapp/smsb/gdzys/gdzysCxjksAction.do" method="POST" >
  <html:hidden property="actionType" />
  <html:hidden property="cxSbbxlh" />
  <html:hidden property="cxJkpzh" />
  <html:hidden property="cxSbbh" />
  <html:hidden property="cxJsjdm" />
   
  
	<table  width="80%"  align="center" cellspacing="0" >
		<tr>
			<td class="1-td1"  colspan="7">撤销缴款书 </td>
		</tr>
		<tr>
			<td class="1-td2"  colspan="7">
				<br>
				<table  cellspacing="0" class="table-99">
				    <tr> 
					  <td width="25%" class="2-td2-t-left">纳税人名称</td>
					  <td width="15%" class="2-td2-t-left">
					  	<div align="left">
					     <html:text property="nsrmc" size="20" maxlength="10"/></FONT>           
            			</div>
					  </td>
					  <td width="25%" class="2-td2-t-left">计算机代码</td>
					  <td width="15%" class="2-td2-t-left">				    
						<div align="left">
              				<html:text property="jsjdm" size="15" />
                      </div>
                       </td>
                       <td rowspan="2" width="20%" class="2-td2-t-center">
                       <div>
                        <input name="I2" type="image" accesskey="q" value="查询"  onclick="doit('Query');return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="查询" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun" align="middle">
                      </div>
                       </td>
					</tr>
					<tr> 
					  <td width="25%" class="2-td2-left">批准占地文号</td>
					  <td width="15%" class="2-td2-left">
					  <div align="left">
					  <html:text property="pzjdwh" size="20"/>
				      </div>
					  </td>
					  <td width="25%" class="2-td2-left">申报序列号</td>
					  <td width="15%" class="2-td2-left">
					  <div align="left">
						<html:text property="sbbxlh" size="15" />
					  </div>
					  </td>
					</tr>
				</table>
				<br>
	
			<DIV style="FONT-SIZE: 13px; HEIGHT: 15px" 
            align=center><STRONG>查询结果列表</STRONG>
            </DIV>
            
            <DIV id="">
            <TABLE class=table-99 id=table_LIST cellSpacing=0 border=0>
              <TBODY>
              <TR>
                <TD class=2-td1-left width="5%">选择</TD>
                <TD class=2-td1-left width="8%">申报序列号</TD>
                <TD class=2-td1-left width="8%">缴款凭证号</TD>
                <TD class=2-td1-left width="8%">缴款书类别</TD>
                <TD class=2-td1-left width="10%">纳税人名称</TD>
				<TD class=2-td1-left width="8%">计算机代码</TD>
                <TD class=2-td1-left width="15%">课税数量</TD>
                <TD class=2-td1-center width="10%">实缴金额</TD>
                </TR>
              <logic:iterate id="jkslist" name="gdzysCxjksForm" property="dataList" indexId="indexid">
                  <bean:define id="item" name="jkslist"/>
                     <tr>
                        <td class="2-td2-left" align="center" nowrap><input type="radio" name="xuanze" value="<ttsoft:write name="jkslist" property="sbbxlh"/>#<ttsoft:write name="jkslist" property="jkpzh"/>#<ttsoft:write name="jkslist" property="sbbh"/>#<ttsoft:write name="jkslist" property="jsjdm"/>" <%=indexid.intValue()==0?"checked":""%>/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="sbbxlh"/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="jkpzh"/></td>
						 <%
					    String splx=(String)((HashMap)item).get("splx");
					    %>
					    <% 
					    if(splx.equals("1")){
					    %>
					     <td class="2-td2-left" align="center" nowrap>&nbsp;正税税票</td>
					    <% 
					    }else{
					    %>
					     <td class="2-td2-left" align="center" nowrap>&nbsp;滞纳金税票</td>
					    <% 
					    }
					    %>
						
						<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="nsrmc"/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="jsjdm"/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="kssl"/></td>
						<td class="2-td2-center" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="sjje"/></td>
					</tr>
			 </logic:iterate>
			  </TBODY></TABLE>
  
			  </DIV>
			  	<logic:equal name="gdzysCxjksForm" property="cxJgTsFlag" value="1">
			  	 <DIV style="FONT-SIZE: 13px; HEIGHT: 15px" 
                 align=center><FONT SIZE="2" COLOR="#FF0000">没有满足查询条件的结果记录！</STRONG>
                 </DIV>	
				</logic:equal>				
				<br>
				<table width="100%" border="0" cellpadding="0" cellspacing="4">
					<tr valign="bottom">
						<td width="40%" align="right">
							<input name="tockjks" type="image" accesskey="s"  onClick="doCkJks();return false;"  onMouseOver="MM_swapImage('tockjks','','<%=static_contextpath%>images/gdzys/gdzys_ckjks2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="查看缴款书" id="tockjks" src="<%=static_contextpath%>images/gdzys/gdzys_ckjks1.jpg" width="100" height="22" >
						</td>
						<td width="10%" align="center">
							<input name="tocxjks" type="image" accesskey="s"  onClick="doDelJks();return false;"  onMouseOver="MM_swapImage('tocxjks','','<%=static_contextpath%>images/gdzys/gdzys_cxjks2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="撤销缴款书" id="tocxjks" src="<%=static_contextpath%>images/gdzys/gdzys_cxjks1.jpg" width="100" height="22" >
						</td>
                        <td width="40%" align="left">	
							<input type="image" accesskey="c"   onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22">
						</td>
                        
                      </tr>
				</table>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>
 <%@ include file="../../include/footer.jsp" %>
    </td>
  </tr>
</table>
</html:form>
</body>
</html:html>