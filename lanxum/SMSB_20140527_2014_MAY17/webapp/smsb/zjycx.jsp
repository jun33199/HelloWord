<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.ttsoft.framework.util.JspUtil"%>
<%@ page import="com.ttsoft.bjtax.smsb.zjyjmsb.cx.web.ZjyjmcxForm"%>
<%@ page import="com.ttsoft.common.util.CodeConstants"%>
<%
    ZjyjmcxForm zjycx=(ZjyjmcxForm)request.getAttribute("zjyjmcxForm");
%>
<html:html>
<head>
<title>再就业减免税申报表查询</title>
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
	 function doit(ope){
		if(document.forms[0].nd.value==""){
			alert("查询条件中年度不能为空！");
			return false;
		}
		doSubmitForm(ope);
	}
	
	//进入页面时将焦点设为查询年度录入
  // 页面进入焦点确定
   function fnOnLoad()
  {
    document.forms[0].nd.focus();
  }

	function fn_ChangePage(type)
	{
		//获取上一次操作类型
		temp=document.forms[0].actionType.value;
		//设置当前操作类型
		if(temp=="Query"||temp=="ChangePage"){
			temp="ChangePage";
		}else{
			temp="Show";
		}
		//
		var tmpNextPage=document.forms[0].nextPage.value*1;
		var tmpTotalPage=document.forms[0].totalpage.value*1;
		//alert("tmpNextPage:"+tmpNextPage+"|"+"tmpTotalPage:"+tmpTotalPage+"|"+"type:"+type);
		if(temp=="Show"){
			alert("没有任何已查询数据,请先查询...");
			return false;
		}
		if (type == "first")
		{
			if(tmpNextPage==1){
				alert("本次查询已到达第一页,请返回...");
				return false;
			}else{
					document.forms[0].nextPage.value="1";
			}
		}
		else if (type == "previous")
		{
			if(tmpNextPage==1){
				alert("本次查询已到达第一页,请返回...");
				return false;
			}else{
					document.forms[0].nextPage.value =(tmpNextPage-1)+"";
			}
		}
		else if (type == "next")
		{
			if(tmpNextPage>=tmpTotalPage){
				alert("本次查询已到达最后一页,请返回...");
				return false;
			}else{
					document.forms[0].nextPage.value =(tmpNextPage+1)+"";
			}
		}
		else if (type == "last")
		{
			if(tmpTotalPage==1){
				alert("本次查询只存在一页,请返回...");
				return false;
			}else if(tmpNextPage>=tmpTotalPage){
				alert("本次查询已到达最后一页,请返回...");
				return false;
			}
			else{
				document.forms[0].nextPage.value=document.forms[0].totalpage.value;
			}
		}
		//提交查询
		doit(temp);
		return false;
	}
	//导出Excel提交
function doToExcelSubmit(){

if(document.all.totalpage.value==""||document.all.totalpage.value==null||document.all.totalpage.value=="0")
	{
		alert("请先查询！");
		return false;
	}
	  if(document.forms[0].nd.value==""){
			alert("查询条件中年度不能为空！");
			return false;
		}
		if(!window.confirm("确定要导出Excel查询结果文件？")){
				return false;
			}
        doSubmitForm('SaveExcel');
        return true; 
}
//-->
</SCRIPT>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
 <%@ include file="./include/header.jsp"%>
<html:form action="/webapp/smsb/zjyjmcxAction.do" method="POST" >
  <html:hidden property="actionType" />
	<html:hidden property="pageSize"/>
	<html:hidden property="nextPage"/>
	<html:hidden property="totalpage"/>
	<html:hidden property="message"/>
	<table width="96%" align="center" cellspacing="0" class="table-99">
		<tr>
			<td class="1-td1"  colspan="7">再就业减免税申报表查询 </td>
		</tr>
		<tr>
			<td class="1-td2"  colspan="7">
				<br>
				<table  cellspacing="0" class="table-99">
				    <tr> 
					  <td width="15%" class="2-td2-t-left">区县分局</td>
					  <td width="35%" class="2-td2-t-left">
					  	<div align="left">
					     <bean:define id="qxItems" name="zjyjmcxForm" property="fjList"/>
                 <html:select property="queryfj" style="width:200px">
                    <html:options collection="qxItems" property="value" labelProperty="label"/>
                 </html:select>                      
            </div>
					  </td>
					  <td width="15%" class="2-td2-t-left">税务所</td>
					  <td width="35%" class="2-td2">				    
						<div align="left">
              <bean:define id="swsItems" name="zjyjmcxForm" property="swsList"/>
                 <html:select property="querysws" style="width:200px">
                  <html:options collection="swsItems" property="value" labelProperty="label"/>
                    </html:select>
                      </div>
                       </td>
					</tr>
					<tr> 
					  <td width="15%" class="2-td2-left">查询年度</td>
					  <td width="35%" class="2-td2-left">
					  <div align="left">
					  <html:text property="nd" size="10" maxlength="8"/>
					  <font color="#FF0000">&nbsp;*</font>
						</div>
					  </td>
					  <td width="15%" class="2-td2-left">查询季度</td>
					  <td width="35%" class="2-td2-center">
					  <div align="left">
						<bean:define id="jdItems" name="zjyjmcxForm" property="jdlist"/>
                <html:select property="jd" onchange="changeRemoveOption();" style="width:200px">
                  <html:options collection="jdItems" property="value" labelProperty="label"/>
                   </html:select>
					  </div>
					  </td>
					</tr>
					<tr> 
					  <td width="15%" class="2-td2-left">计算机代码</td>
					  <td width="35%" class="2-td2-left"> 
					  <div align="left">
					  <html:text property="jsjdm" size="10" maxlength="8"/>
					  <font color="#FF0000">&nbsp;</font>
					  </div>
					  </td> 
					  <td width="15%" class="2-td2-left">纳税人名称</td>
					  <td width="35%" class="2-td2-center"> 
					  <div align="left">
					  <html:text property="nsrmc" size="10" maxlength="8"/>
					  <font color="#FF0000">&nbsp;</font>
					  </div>
					  </td>
					</tr>
	            	<tr>
					<tr> 
					  <td width="15%" class="2-td2-left">减免税类型</td>
					  <td width="35%" class="2-td2-left"> 
					  <div align="left">
					  <bean:define id ="jmslbItems" name="zjyjmcxForm" property="jmslblist"/>
					  <html:select property="jmslb"  multiple="multiple" style="width:190px">
					  <html:options collection="jmslbItems" property="value" labelProperty="label"/>
					  </html:select>
					  
					  </div>
					  </td> 
					  <td width="15%" class="2-td2-left">减免税种类型</td>
					  <td width="35%" class="2-td2-center"> 
					  <bean:define id ="jmszItems" name="zjyjmcxForm" property="jmszArray"/>
					  <html:select property="jmsz"  >
					  <html:options collection="jmszItems"  property="value" labelProperty="label"/>
					  </html:select>
					  </div>
					  </td>
					</tr>
					<tr>
					  <td colspan="4"  class="2-td2-center" >
                     <input name="I2" type="image" accesskey="q" value="查询"  onclick="doit('Query');return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="查询" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun" align="right">
					
					<input name="toexcel" type="image" accesskey="s"  onClick="doToExcelSubmit();return false;"  onMouseOver="MM_swapImage('toexcel1','','<%=static_contextpath%>images/b-bcdexcel2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="保存到Excel" id="toexcel1" src="<%=static_contextpath%>images/b-bcdexcel1.jpg" width="100" height="22" align="right"></td>
					</tr>
				</table>
				<br>				
				<table border="0" bgcolor="f3f3f3" class="table-99">
              <tr>
                <td width="43%" ><hr width="100%" class="hr1" size=1></td>
                <td width="14%" align="left" class="black9" > <div align="center"><strong>查询结果列表</strong></div></td>
                <td width="43%" ><hr width="100%" class="hr1" size=1></td>
              </tr>
        </table>        
				<TABLE class="table-99" align="center">
				<TR class="black9">
				<TD>
				<div align="right">
					(第<bean:write name="zjyjmcxForm" property="nextPage"/>页/共<bean:write name="zjyjmcxForm" property="totalpage"/>页)
					<!--翻页功能开始-->
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('first');return false;"><img name="beginpage" value="第一页" alt="第一页" onMouseOver="MM_swapImage('diyiye','','<%=static_contextpath%>images/diyiye2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/diyiye1.jpg" width="79" height="22" id="diyiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('previous');return false;"><img name="frontpage" value="上一页" alt="上一页" onMouseOver="MM_swapImage('shangyiye','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/shangyiye1.jpg" width="79" height="22" id="shangyiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('next');return false;"><img name="backpage" value="下一页" alt="下一页" onMouseOver="MM_swapImage('xiayiye','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/xaiyiye1.jpg"  width="79" height="22" id="xiayiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('last');return false;"><img name="endpage" value="最末页" alt="最末页" onMouseOver="MM_swapImage('zuimoye','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/zuimoye1.jpg" width="79" height="22" id="zuimoye"></a>&nbsp;&nbsp;&nbsp;&nbsp;
					<!--翻页功能开始-->
				</div>
				</TD>
				</TR>
        <TR>
				<TD>
				<div id="Layer2" style="position:static; overflow: auto;  width: 970px; height: 230px">
				<table id="wrklistTable" border="1" cellspacing="0" class="table-99" align="center" >
					<tr>
						
						<td class="2-td1-left" nowrap>计算机代码</td>
						<td class="2-td1-left" nowrap>纳税人名称</td>
						<td class="2-td1-left" nowrap>吸纳下岗失业人数</td>
						<td class="2-td1-left" nowrap>减免税类别</td>		
						<td class="2-td1-left" nowrap>申报日期</td>
						<td class="2-td1-left" nowrap>营业税</td>
						<td class="2-td1-left" nowrap>城建税</td>
						<td class="2-td1-left" nowrap>教育费附加</td>					
						<td class="2-td1-left" nowrap>个人所得税</td>
						<td class="2-td1-left" nowrap>企业所得税</td>
						<td class="2-td1-left" nowrap>合计</td>
						<td class="2-td1-left" nowrap>主管税务所名称</td>
					</tr>
					<logic:iterate id="item" name="zjyjmcxForm" property="dataList" >
					<tr>
						<td class="2-td2-left" align="center" nowrap height="2">&nbsp;<bean:write name='item' property='jsjdm'/></td>
						<td class="2-td2-left" align="center" nowrap height="2">&nbsp;<bean:write name='item' property='nsrmc'/></td>
						<td class="2-td2-left" align="center" nowrap height="2">&nbsp;<bean:write name='item' property='xnxgsyrs'/></td>
						<td class="2-td2-left" align="center" nowrap height="2">&nbsp;<bean:write name='item' property='jmslb'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='xsjmsj'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='yys'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='cjs'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='jyffj'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='grsds'/></td>	
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='qysds'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='hj'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='swjgzzjgmc'/></td>

					</tr>
					</logic:iterate>
				</table>
				</div>
				</TD>
				</TR>
				<TR class="black9">
				<TD>
				<div  align="right">
					(第<bean:write name="zjyjmcxForm" property="nextPage"/>页/共<bean:write name="zjyjmcxForm" property="totalpage"/>页)
					<!--翻页功能开始-->
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('first');return false;"><img name="beginpage" value="第一页" alt="第一页" onMouseOver="MM_swapImage('diyiye','','<%=static_contextpath%>images/diyiye2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/diyiye1.jpg" width="79" height="22" id="diyiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('previous');return false;"><img name="frontpage" value="前页" alt="前页" onMouseOver="MM_swapImage('shangyiye','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/shangyiye1.jpg" width="79" height="22" id="shangyiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('next');return false;"><img name="backpage" value="后页" alt="后页" onMouseOver="MM_swapImage('xiayiye','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/xaiyiye1.jpg"  width="79" height="22" id="xiayiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('last');return false;"><img name="endpage" value="最后一页" alt="最后一页" onMouseOver="MM_swapImage('zuimoye','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/zuimoye1.jpg" width="79" height="22" id="zuimoye"></a>&nbsp;&nbsp;&nbsp;&nbsp;
					<!--翻页功能开始-->
				</div>
				</TD>
				</TR>
				</TABLE>
				<br>
				<table width="100%" border="0" cellpadding="0" cellspacing="4">
					<tr valign="bottom">
						<td width="40%">&nbsp; </td>
                        <td width="10%" align="ceter">	
							<input type="image" accesskey="c"   onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22">
						</td>
                        <td width="40%">&nbsp;</td>
                      </tr>
				</table>
			</td>
		</tr>
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