<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>

<html>
<head>
<title>数据交换XML报文下载</title>
	<link href="../css/text.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" type="text/JavaScript" src="../js/smsb_common.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="../js/treatImage.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="../js/gmit_selectcontrol.js"></script>  
</head>

<script language=JavaScript>
	
	<%
    // 构造分局部门列表    
    if(session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_DEPT) != null)
    {
        out.print("var arySelect_dept = new Array(");
        out.print("[\"\",\"\"]");
        String[][] dept = (String[][])session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_DEPT);
        for(int i = 0 ; i < dept.length ; i++) 
        {
         	out.print(",[\"" + dept[i][0] + "\",\"" +dept[i][1] +"\"]");
        }
        out.println(");");
    }

	// 构造分局部门列表    
	if(session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BWLX) != null) 
	{
	    out.print("var arySelect_bwlx = new Array(");
	    out.print("[\"\",\"\"]");
	    String[][] bwlx = (String[][])session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BWLX);
	    for(int i = 0 ; i < bwlx.length ; i++)
	    {
	     	out.print(",[\"" + bwlx[i][0] + "\",\"" +bwlx[i][1] +"\"]");
	    }
	    out.println(");");
	}
	%>
	
	// 初始化页面信息
	function doInit()
	{
		// 初始化分局机构
		_addOptionsToSelect(document.forms[0].deptSelect, arySelect_dept);
		// 初始化分局机构
		_addOptionsToSelect(document.forms[0].bwlxSelect, arySelect_bwlx);
	}
	
	
	// 回车查询纳税人信息
	function doJsjdmQuery()
	{
		if(event.keyCode==13) doQuery();
	}
	
	
	function doQuery()
	{
	
		var ksrq=document.forms[0].skssksrq.value;
		var jsrq=document.forms[0].skssjsrq.value;		
		var fjjg=document.forms[0].deptSelect.value;		
		var bwlx=document.forms[0].bwlxSelect.value;  
		
		// 扣缴人计算机代码
		if (ksrq==null || ksrq.length==0){
			alert('请输入税款所属开始日期');
			return;				
		}   
		if (jsrq==null || jsrq.length==0){
			alert('请输入税款所属结束日期');
			return;	
		}   
		if(!checkDate(ksrq)){
			alert('税款所属开始日期格式输入错误');
			return;			
		}
		if(!checkDate(jsrq)){
			alert('税款所属结束日期格式输入错误');
			return;		
		}
		if(ksrq>jsrq){
			alert('税款所属结束日期不能早于税款所属开始日期');
			return;		
		}				
		if(bwlx.length<=0){
			alert('请选择报文类型');
			return;		
		}
		
		
		document.forms[0].actionType.value = "QueryTZXX";
	    document.forms[0].submit();
  
	}
	
	
	function checkDate(strDate)
{
    if (strDate.length!=8) return false;
    var strYear = strDate.substr(0,4);
    //var strSep1 = strDate.substr(4,1);
    var strMonth = strDate.substr(4,2);
    //var strSep2 = strDate.substr(7,1);
    var strDay = strDate.substr(6,2);
    var objDate = new Date(strMonth+"-"+strDay+"-"+strYear);
    if (isNaN(objDate)) return false; 
    if(strYear*1<1900) return false;
    if ((strYear*1 != objDate.getYear()*1)&&(strYear*1 != objDate.getYear()*1+1900)) return false;
    
    if (strMonth*1 != objDate.getMonth()*1+1) return false; 
    
    if (strDay*1 != objDate.getDate()*1) return false; 
    return true;
}
	
	
</script>


<%@ include file="./include/header.jsp"%>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="doInit()">
<html:form action="/webapp/smsb/sjjhAction.do" method="post">
<html:hidden property="actionType" />

	<table width="1000" align="center" cellspacing="0" height="350">
	    <tr>
	        <td class="1-td1" height="40" valign="middle">
	        	数据交换XML报文下载 
	        </td>
	    </tr>    
	    <tr>
	        <td class="1-td2" align="left" valign="top">
	        	<br/>
        		<table class="table-98" cellspacing="0" >
        			<tr>
        				<td class="2-td2-t-left" height="30">
        					<div align="left">
        						&nbsp;税款所属开始日期：	 
        					</div>  					       					
        				</td>
        				<td class="2-td2-t-center" height="30">
	        				<div align="left">
	        					&nbsp;
	        					<html:text property="skssksrq" size="20" maxlength="8" onclick="this.select()"/>
	        					<font color="red"> *</font>
	        				</div>
        				</td>
        			</tr>
        			<tr>
        				<td class="2-td2-left" align="left" height="30">
							<div align="left">
								&nbsp;税款所属结束日期：
							</div>	        						        					       					
        				</td>
        				<td class="2-td2-center" align="left" height="30">	        					
        					<div align="left">
        						&nbsp;
        						<html:text property="skssjsrq" size="20" maxlength="8" onclick="this.select()"/>
        						<font color="red"> *</font>
        					</div>	        						        					
        				</td>
        			</tr>
        			
        			<tr>
        				<td class="2-td2-left" align="left" height="30">
        					<div align="left">
        						&nbsp;企业所属地税分局：
        					</div>
        					
        				</td>
        				<td class="2-td2-center" align="left" height="30">
        					<div align="left">
					            &nbsp;
					            <html:select name="sjjhForm" styleId="deptSelect" property="dept" />
					            <font color="red"></font>
				            </div>
        				</td>
        			</tr>
        			<tr>
        				<td class="2-td2-left" align="left" height="30">
        					<div align="left">
	        					&nbsp;数据交换报文类型：
				            </div>	        					
        				</td>
        				<td class="2-td2-center" align="left" height="30">
        					<div align="left">
					            &nbsp;
					            <html:select name="sjjhForm" styleId="bwlxSelect" property="bwlx" />
					            <font color="red"> *</font>
				            </div>
        				</td>
        			</tr>	        			
        			<tr>
        				<td  align="center" height="40" colspan="2">	        				
        					<input type="button" value="下载XML报文" onclick="doQuery()" />&nbsp;&nbsp;&nbsp;&nbsp;	  
        					<input type="button" value="返回" onclick="tuichu();return false;" />	        		
        				</td>
        			</tr>
        		</table>	        	
			</td>
	    </tr> 
	</table>
    <br/>
    <br/>
     	 	<br/>
    <br/>
<%@ include file="./include/footernew.jsp"%>
</html:form>

</body>
</html>
	