<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.ttsoft.framework.util.JspUtil"%>
<%@ page import="com.ttsoft.bjtax.smsb.lwpk.web.DhkscxForm"%>
<%@ page import="com.ttsoft.common.util.CodeConstants"%>
<%@ page import="com.ttsoft.bjtax.smsb.lwpk.model.PKJBSJModel" %>
<%@ page import="com.ttsoft.bjtax.smsb.lwpk.model.PLKKDHCXModel" %>
<%@ page import="java.util.*" %>
<%
DhkscxForm form=(DhkscxForm)request.getAttribute("dhkscxForm");
PKJBSJModel dhcxmodel = form.getPkjbsjModel();
List plkkdhcxModelList = form.getPlkkdhcxlist();

%>
<html:html>
<head>
<title>单户批量扣税查询</title>
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
		 rep = /^[0-1]{1}[0-9]{1}$/;
		 if(document.forms[0].jsjdm.value==""){
				alert("查询条件中计算机代码不能为空！");
				return false;
			}
		 if(document.forms[0].jsjdm.value.length!=8){
			 alert("查询条件中计算机代码长度不正确！");
				return false;
		 }
		if(isFullDate(document.forms[0].nd.value,1,"noempty",true)==false){
			alert("查询条件中年度格式不正确！");
			return false;
		}
		if(document.forms[0].kkqsyf.value==""){
			alert("查询条件中起始月份不能为空！");
			return false;
		}
		if(rep.test(document.forms[0].kkqsyf.value)==false){
			alert("起始月份格式不正确！");
			return false;
		}
		if(document.forms[0].kkzzyf.value==""){
			alert("查询条件中终止月份不能为空！");
			return false;
		}
		if(rep.test(document.forms[0].kkzzyf.value)==false){
			alert("终止月份格式不正确！");
			return false;
		}
		if(document.forms[0].kkqsyf.value > document.forms[0].kkzzyf.value){
			alert("起始月份不能大于终止月份！");
			return false;
		}
		doSubmitForm(ope);
	}
	
	//进入页面时将焦点设为查询年度录入
  // 页面进入焦点确定
   function fnOnLoad()
  {
	<%
	if(request.getSession().getAttribute("is_show").equals("false") && plkkdhcxModelList == null){
		%>
		alert("未查询到符合条件的数据！");
		<%
		request.getSession().removeAttribute("is_show");
	}
	%>
    document.forms[0].jsjdm.focus();
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
 
<html:form action="/webapp/smsb/dhkscxAction.do" method="POST" >
  <html:hidden property="actionType" />
	<html:hidden property="pageSize"/>
	<html:hidden property="nextPage"/>
	<html:hidden property="totalpage"/>
	<html:hidden property="message"/>
	<table width="96%" align="center" cellspacing="0" class="table-99">
		<tr>
			<td class="1-td1"  colspan="7">单户批量扣税查询 </td>
		</tr>
		<tr>
			<td class="1-td2"  colspan="7">
				<br>
				<table  cellspacing="0" class="table-99">
				    <tr> 
					  <td width="25%" class="2-td2-t-left">计算机代码</td>
					  <td width="15%" class="2-td2-t-left">
					  	<div align="left">
					     <html:text property="jsjdm" size="10" maxlength="8"/> <FONT SIZE="" COLOR="#FF0000">*</FONT>           
            			</div>
					  </td>
					  <td width="25%" class="2-td2-t-left">年度</td>
					  <td width="15%" class="2-td2-t-left">				    
						<div align="left">
              				<html:text property="nd" size="8" maxlength="4"/><FONT SIZE="" COLOR="#FF0000">*</FONT>
                      </div>
                       </td>
                       <td rowspan="2" width="20%" class="2-td2-t-center">
                       <div>
                        <input name="I2" type="image" accesskey="q" value="查询"  onclick="doit('Query');return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="查询" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun" align="middle">
                      </div>
                       </td>
					</tr>
					<tr> 
					  <td width="25%" class="2-td2-left">扣款起始月份</td>
					  <td width="15%" class="2-td2-left">
					  <div align="left">
					  <html:text property="kkqsyf" size="10" maxlength="2"/>
					  <span>MM如：01<FONT SIZE="" COLOR="#FF0000">*</FONT></span>
						</div>
					  </td>
					  <td width="25%" class="2-td2-left">扣款终止月份</td>
					  <td width="15%" class="2-td2-left">
					  <div align="left">
						<html:text property="kkzzyf" size="10" maxlength="2"/>
						<span>MM如：12<FONT SIZE="" COLOR="#FF0000">*</FONT></span>
					  </div>
					  </td>
					</tr>
				</table>
				<br>
			<%
			if(dhcxmodel!=null){
			%>
			<DIV style="FONT-SIZE: 13px; HEIGHT: 15px" 
            align=center><STRONG>纳税人基本信息</STRONG></DIV>
            <DIV id="">
            <TABLE class=table-99 id=table_LIST cellSpacing=0 border=0>
              <TBODY>
              <TR>
                <TD class=2-td1-left width="20%">纳税人名称</TD>
                <TD class=2-td1-left width="14%">法定代表人（责任人）</TD>
				<TD class=2-td1-left width="13%">固定电话</TD>
                <TD class=2-td1-left width="13%">移动电话</TD>
                <TD class=2-td1-left width="20%">注册地址</TD>
                <TD class=2-td1-center width="20%">经营地址</TD>
              <TR id=ROW_LIST>
               <bean:define id="model" name="dhkscxForm" property="pkjbsjModel" />
                <TD class=2-td2-left>&nbsp;<bean:write name='model' property='nsrmc'/></TD>
                <TD class=2-td2-left>&nbsp;<bean:write name='model' property='zrr'/></TD>
                <TD class=2-td2-left>&nbsp;<bean:write name='model' property='gddh'/></TD>
                <TD class=2-td2-left>&nbsp;<bean:write name='model' property='yddh'/></TD>
                <TD class=2-td2-left>&nbsp;<bean:write name='model' property='zcdz'/></TD>
                <TD class=2-td2-center>&nbsp;<bean:write name='model' property='jydz'/></TD>
			  </TBODY></TABLE></DIV>
			  <br>
			<%
			}
			%>
			
			<%
            if(plkkdhcxModelList!=null){
         
            %>	
		
            <DIV style="FONT-SIZE: 13px; HEIGHT: 15px" 
            align=center><STRONG>查询结果列表</STRONG></DIV>
            <DIV id="">
            <TABLE class=table-99 id=table_LIST cellSpacing=0 border=0>
              <TBODY>
              <TR>
                <TD class=2-td1-left width="5%">序号</TD>
                <TD class=2-td1-left width="15%">税票号码</TD>
				<TD class=2-td1-left width="15%">税种</TD>
                <TD class=2-td1-left width="15%">实缴金额</TD>
                <TD class=2-td1-left width="15%">扣款时间</TD>
                <TD class=2-td1-left width="15%">扣款状态</TD>
                <TD class=2-td1-center width="20%">扣款结果描述</TD></TR>
              <logic:iterate id="item" name="dhkscxForm" property="plkkdhcxlist" type="com.ttsoft.bjtax.smsb.lwpk.model.PLKKDHCXModel" indexId="indexid">
					<tr>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<%=indexid.intValue()+1%></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='sphm'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='szmc'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='sjje'/></td>	
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='kksj'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='kkzt'/></td>
						<td class="2-td2-center" align="center" nowrap>&nbsp;<bean:write name='item' property='kksbyy'/></td>

					</tr>
			</logic:iterate>
			  </TBODY></TABLE>
  
			  </DIV>	
			
				<TABLE class="table-99" align="center">
				<TR class="black9">
				<TD>
				<div  align="left">
					<!--翻页功能开始-->
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('first');return false;"><img name="beginpage" value="第一页" alt="第一页" onMouseOver="MM_swapImage('diyiye','','<%=static_contextpath%>images/diyiye2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/diyiye1.jpg" width="79" height="22" id="diyiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('previous');return false;"><img name="frontpage" value="前页" alt="前页" onMouseOver="MM_swapImage('shangyiye','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/shangyiye1.jpg" width="79" height="22" id="shangyiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('next');return false;"><img name="backpage" value="后页" alt="后页" onMouseOver="MM_swapImage('xiayiye','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/xaiyiye1.jpg"  width="79" height="22" id="xiayiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('last');return false;"><img name="endpage" value="最后一页" alt="最后一页" onMouseOver="MM_swapImage('zuimoye','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/zuimoye1.jpg" width="79" height="22" id="zuimoye"></a>&nbsp;&nbsp;&nbsp;&nbsp;
					<!--翻页功能开始-->
				</div>
				</TD>
				<td align="right">
				 <DIV class=black9 id=layerView align=right><STRONG>您现在位置为第<SPAN 
            class=tishi><bean:write name="dhkscxForm" property="nextPage"/></SPAN>页，共<SPAN class=tishi><bean:write name="dhkscxForm" property="totalpage"/></SPAN>页，<SPAN 
            class=tishi><bean:write name="dhkscxForm" property="zts"/></SPAN>条记录。</STRONG></DIV>
				
				</td>
				</TR>
				</TABLE>
			 <%
			  }
              %>
				<br>
				<table width="100%" border="0" cellpadding="0" cellspacing="4">
					<tr valign="bottom">
						<td width="50%" align="right">
							<input name="toexcel" type="image" accesskey="s"  onClick="doToExcelSubmit();return false;"  onMouseOver="MM_swapImage('toexcel1','','<%=static_contextpath%>images/b-bcdexcel2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="保存到Excel" id="toexcel1" src="<%=static_contextpath%>images/b-bcdexcel1.jpg" width="100" height="22" >
						</td>
                        <td width="50%" align="left">	
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
<%@ include file="./include/footer.jsp"%>
    </td>
  </tr>
</table>
</html:form>
</body>
</html:html>