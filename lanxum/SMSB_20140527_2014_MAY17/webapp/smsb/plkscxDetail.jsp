<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.ttsoft.framework.util.JspUtil"%>
<%@ page import="com.ttsoft.common.util.CodeConstants"%>
<%@ page import="java.util.*" %>

<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>


<html:html>
<head>
<title>批量扣税综合查询</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>
<script language="javascript" src="../js/gmit_selectcontrol.js"></script>
</head>
<SCRIPT LANGUAGE="JavaScript">


 //翻页
function doTurnPage(para){
        	var currentPages = document.forms[0].currentPageXx.value;
            var maxPages = document.forms[0].maxPageXx.value;
           
    		if(para == 'first'){
                if(currentPages == 1){
    				alert('当前为第一页！');
    				return ;
                }
                document.forms[0].currentPageXx.value = 1;
    		}else if(para == 'previous'){
                if(currentPages == 1){
    				alert('当前为第一页！');
    				return ;
                }
                document.forms[0].currentPageXx.value = parseInt(currentPages) - 1;
    		}else if(para == 'next'){
                if(currentPages == maxPages){
    				alert('当前为最后一页！');
    				return ;
                }
                document.forms[0].currentPageXx.value = parseInt(currentPages) + 1;
    		}else if(para == 'last'){
                if(currentPages == maxPages){
    				alert('当前为最后一页！');
    				return ;
                }
                document.forms[0].currentPageXx.value = maxPages;
    		}
    		document.forms[0].actionType.value = "QueryKkDetail";
    		document.forms[0].submit();   
   }

function to_back(){
	document.forms[0].actionType.value = "Query";
	document.forms[0].submit();   
}


function doToExcelSubmit(){
	document.forms[0].actionType.value = "ExportDetail";
	document.forms[0].submit();   
	
}	
 
</SCRIPT>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
 <%@ include file="./include/header.jsp"%>
<html:form action="/webapp/smsb/plkscxAction.do" method="POST" >
<html:hidden property="dqjs"/>
<html:hidden property="cxdqjs"/>
<html:hidden property="yhdm"/>
<html:hidden property="nd"/>
<html:hidden property="ystart"/>
<html:hidden property="yend"/>

<html:hidden property="actionType"/>
<html:hidden property="currentPage"/>
<html:hidden property="maxPage"/>
<html:hidden property="totalRowCount"/>
<html:hidden property="queryFlag"/>
<html:hidden property="cgsbFlag"/>

<html:hidden property="currentPageXx"/>
<html:hidden property="maxPageXx"/>
<html:hidden property="totalRowCountXx"/>

<html:hidden property="fjdmxx"/>
<html:hidden property="swsdmxx"/>
<html:hidden property="yhdmxx"/>
<html:hidden property="kksjxx"/>

	 
	<table width="96%" align="center" cellspacing="0" class="table-99">
		<tr>
			<td class="1-td1"  colspan="7">批量扣税综合查询</td>
		</tr>
		  <TR bgColor=#ffffff>
          <TD class=1-td2 vAlign=top align=middle width="82%"><BR>
            <DIV class=black9 id=layerView align=left><STRONG>您现在位置为第<SPAN 
            class=tishi><bean:write name="plkscxForm" property="currentPageXx"/></SPAN>页，共<SPAN class=tishi><bean:write name="plkscxForm" property="maxPageXx"/></SPAN>页，<SPAN 
            class=tishi><bean:write name="plkscxForm" property="totalRowCountXx"/></SPAN>条记录。</STRONG></DIV>
            
			<!--<DIV style="FONT-SIZE: 13px" align=center width="50%"><STRONG>查询结果列表</STRONG></DIV> -->
            <TABLE class=table-99 id=table_LIST cellSpacing=0 border=0>
              <TBODY>
<!-- 开始-->
           
				<TR>
				<TD class=2-td1-left width="5%">序号</TD>
				<TD class=2-td1-left width="5%">计算机代码</TD>
                <TD class=2-td1-left width="7%">纳税人名称</TD>
                <TD class=2-td1-left width="7%">法定代表人（责任人）</TD>
				<TD class=2-td1-left width="7%">固定电话</TD>
				<TD class=2-td1-left width="7%">移动电话</TD>
                <TD class=2-td1-left width="7%">注册地址</TD>
                <TD class=2-td1-left width="7%">经营地址</TD>
				<TD class=2-td1-left width="7%">税票号码</TD>
				<TD class=2-td1-left width="6%">税种</TD>
				<TD class=2-td1-left width="6%">实缴金额</TD>
				<TD class=2-td1-left width="7%">扣款时间</TD>
				<TD class=2-td1-left width="6%">扣款状态</TD>
				<TD class=2-td1-center width="5%">扣款结果描述</TD>
                
			  </TR>
<!-- 结束-->
       <bean:define id="kkxxlist" name="plkscxForm" property="dataList"/>
       <logic:iterate  name="kkxxlist" id="itemMap" indexId="indexid">
        <bean:define id="item" name="itemMap"/>
              <TR id=ROW_LIST>
                <TD class=2-td2-left>&nbsp;<%=indexid.intValue()+1%></TD>
                 <TD class=2-td2-left>&nbsp;<ttsoft:write name="item" property="jsjdm"/></TD>
                <TD class=2-td2-left>&nbsp;<ttsoft:write name="item" property="nsrmc"/></TD>
                <TD class=2-td2-left>&nbsp;<ttsoft:write name="item" property="xm"/></TD>
                <TD class=2-td2-left>&nbsp;<ttsoft:write name="item" property="gddh"/></TD>
                <TD class=2-td2-left>&nbsp;<ttsoft:write name="item" property="yddh"/></TD>
                <TD class=2-td2-left>&nbsp;<ttsoft:write name="item" property="zcdz"/></TD>
                <TD class=2-td2-left>&nbsp;<ttsoft:write name="item" property="jydz"/></TD>
                <TD class=2-td2-left>&nbsp;<ttsoft:write name="item" property="sphm"/></TD>
                <TD class=2-td2-left>&nbsp;<ttsoft:write name="item" property="szmc"/></TD>
                <TD class=2-td2-left>&nbsp;<ttsoft:write name="item" property="sjje"/></TD>
                <TD class=2-td2-left>&nbsp;<ttsoft:write name="item" property="kkrq"/></TD>
                <TD class=2-td2-left>&nbsp;<ttsoft:write name="item" property="kkjg"/></TD>
                <TD class=2-td2-center>&nbsp;<ttsoft:write name="item" property="yhkkjgms"/></TD>
			  </TR>
		</logic:iterate>	  
			  
              </TBODY>
         </TABLE>
            <TABLE class=table-99 cellSpacing=0>
              <TBODY>
              <TR>
                <TD align=middle width="50%">
                  <DIV align=left><IMG id=first 
                  onmouseover="MM_swapImage('first','','<%=static_contextpath%>images/diyiye2.jpg',1)" 
                  onclick="doTurnPage('first')" onmouseout=MM_swapImgRestore() height=22 
                  src="<%=static_contextpath%>images/diyiye1.jpg" width=79 name=first> <IMG 
                  id=previous 
                  onmouseover="MM_swapImage('previous','','<%=static_contextpath%>images/shangyiye2.jpg',1)" 
                  onclick="doTurnPage('previous')" onmouseout=MM_swapImgRestore() 
                  height=22 src="<%=static_contextpath%>images/shangyiye1.jpg" width=79 
                  name=previous> <IMG id=next 
                  onmouseover="MM_swapImage('next','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" 
                  onclick="doTurnPage('next')" onmouseout=MM_swapImgRestore() height=22 
                  src="<%=static_contextpath%>images/xaiyiye1.jpg" width=79 border=0 
                  name=next> <IMG id=last 
                  onmouseover="MM_swapImage('last','','<%=static_contextpath%>images/zuimoye2.jpg',1)" 
                  onclick="doTurnPage('last')" onmouseout=MM_swapImgRestore() height=22 
                  src="<%=static_contextpath%>images/zuimoye1.jpg" width=79 border=0 
                  name=last> </DIV></TD>
                <TD width="15%">
                  <!--  
                  <DIV align=left>
                  <INPUT onkeydown=enterToPage(this.value) 
                  align=middle size=10 name=switchPage value="1"> <SPAN class=black9><FONT 
                  size=2>页</FONT></SPAN>
                  </DIV>
                  -->
                  </TD>
          
                  </TR></TBODY>
              </TABLE>
		       <br>
				  
				<table width="100%" border="0" cellpadding="0" cellspacing="4">
					<tr valign="bottom">
						<td width="47%" align="right">
							<input name="toexcel" type="image" accesskey="s"  onClick="doToExcelSubmit();return false;"  onMouseOver="MM_swapImage('toexcel1','','<%=static_contextpath%>images/b-bcdexcel2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="保存到Excel" id="toexcel1" src="<%=static_contextpath%>images/b-bcdexcel1.jpg" width="100" height="22" >
						</td>
						<td width="6%" align="center">
							<input type="image" accesskey="z" tabIndex="-1" onclick="to_back();return false;" style="cursor:hand;" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="返回" id="fanhui" src="<%=static_contextpath%>images/fanhui1.jpg">
						</td>
                        <td width="47%" align="left">	
							<input type="image" accesskey="c"   onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22">
						</td>
                        
                      </tr>
				</table>
				<br>
         <BR><BR></TD></TR>
	</table>
	<br>
	<br>
	<br>
	  
<%@ include file="./include/footer.jsp"%>
    </td>
  </tr>
  
  
</table>

</html:form>
</body>
</html:html>