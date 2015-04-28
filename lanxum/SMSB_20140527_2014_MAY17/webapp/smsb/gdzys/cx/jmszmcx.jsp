<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ttsoft.framework.util.JspUtil"%>
<%@ page import="com.ttsoft.common.util.CodeConstants"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ttsoft.bjtax.smsb.gdzys.cx.web.GdzysJmszmCxForm"%>

<html:html>
<head>
<title>减免税证明查询</title>
<link href="../../css/text.css" rel="stylesheet" type="text/css">
<link href="../../css/top-box.css" rel="stylesheet" type="text/css">
<link href="../../css/beijing.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../../js/treatImage.js"></script>
<script language="JavaScript" src="../../js/smsb_common.js"></script>
<script language="JavaScript" src="../../js/DTable.js"></script>
<script language="JavaScript" src="../../js/InputSelect.js"></script>
<script language="JavaScript" src="../../js/function.js"></script>
<script language="javascript" src="../../js/gmit_selectcontrol.js"></script>
</head>
<%
	int datasize = ((GdzysJmszmCxForm) request
				.getAttribute("gdzysJmszmCxForm")).getDataList().size();
%>
<SCRIPT LANGUAGE="JavaScript">
var swsdm='<bean:write name="gdzysJmszmCxForm" property="cxdqjs"/>';
var yhjb='<bean:write name="gdzysJmszmCxForm" property="yhjb"/>';
function doit(ope){
	var startd=document.getElementsByName("ystart")[0].value+"";
	var endd=document.getElementsByName("yend")[0].value+"";
	var sflag=true;
	var eflag=true;
	if(startd!==""){
		sflag=isDate(startd);
	}
	if(endd!==""){
		eflag=isDate(endd);
	}
	if(sflag&&eflag){
	//验证输入的日期格式是否正确
	document.forms[0].currentPage.value = "1";
    //doSubmitForm(ope);
	document.forms[0].actionType.value = "Query";
	document.forms[0].submit(); 
	}
}
//验证日期格式函数
function isDate(dateStr) { 

	var datePat = /^(\d{4})(\d{1,2})(\d{1,2})$/; 
	var matchArray = dateStr.match(datePat); // is the format ok? 

	if (matchArray == null) { 
	alert("日期格式不正确，正确格式：19800606"); 
	return false; 
	} 

	year = matchArray[1]; 
	month = matchArray[2]; // parse date into variables 
	day = matchArray[3]; 

	if (month < 1 || month > 12) { // check month range 
	alert("月份必须是1月-12月之间"); 
	return false; 
	} 

	if (day < 1 || day > 31) { 
	alert("日必须是1-31日之间"); 
	return false; 
	} 

	if ((month==4 || month==6 || month==9 || month==11) && day==31) { 
	alert("Month "+month+" doesn't have 31 days!") 
	return false; 
	} 

	if (month == 2) { // check for february 29th 
	var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 
	== 0)); 
	if (day > 29 || (day==29 && !isleap)) { 
	alert( year + " 年 2 月没有 " + day + " 日"); 
	return false; 
	} 
	} 
	return true; // date is valid 

	} 



  // 页面进入焦点确定
   function fnOnLoad()
  {
	var datasize=<%=datasize%>;
	if(datasize!=0){
		document.getElementById("mydiv").style.display="";
	}
   ajaxgetsws('1');
   
  }
   function ajaxgetsws(flag){
	   var xmlhttp_request;
	   var obj = document.getElementById("dqjs");
	   if(obj.value.length==0){
		   var obj1 = document.getElementById("cxdqjs");
		   obj1.style.width='80%';
		   var len1;
		   while(len1!=0){
		         len1=obj1.options.length;
		         for(var i=0;i<len1;i++)
		           obj1.options.remove(i);
		           len1=len1/2;
		         }
	   //alert("请先选定分局");
	   return false;
	   }
	   
	   //alert(obj.value);
	   if(window.ActiveXObject){
	  	       xmlhttp_request=new ActiveXObject("Microsoft.XMLHTTP");
	  	    }else if(window.XMLHttpRequest){
	  	       xmlhttp_request=new XMLHttpRequest();
	  	     } else {
	  	       return;
	  	   }
	  	     xmlhttp_request.open("GET", "/smsb/webapp/smsb/gdzys/gdzysJmszmCxAction.do?actionType=Getsws&dqjs="+obj.value, true);
	       	 xmlhttp_request.send(null);
	  	     xmlhttp_request.onreadystatechange =function zdyprocessAjaxResponse(){
	  	      var xmlDOM;
	           var obj1 = document.getElementById("cxdqjs");
	           obj1.style.width='';
	           //var obj2 = document.getElementById("jxdm");
	           var oOption;
	           var length1;
	           //var length2;
	           var len1;
	           //var len2;
	           var k;
	          if (xmlhttp_request.readyState == 4) {
	          if (xmlhttp_request.status == 200) {
	         xmlDOM = xmlhttp_request.responseXML;
	         length1 = xmlDOM.getElementsByTagName("gV").length;
	         length2 = xmlDOM.getElementsByTagName("sV").length;
	         len1=1;
	         //len2=1;
	         while(len1!=0){
	         len1=obj1.options.length;
	         for(var i=0;i<len1;i++)
	           obj1.options.remove(i);
	           len1=len1/2;
	         }
	  
	         if(yhjb=='50'||yhjb=='40'){
		         oOption = document.createElement("option");
		         oOption.text="";
		         oOption.value="";
		         if(flag==true&&swsdm==oOption.value){
			            oOption.selected=true;
			     }
		         obj1.add(oOption);
	         }
	         for (k=0; k<length1;k++) {
	           oOption = document.createElement("option");
	           oOption.text= xmlDOM.getElementsByTagName("gU")[k].firstChild.data;
	           oOption.value= xmlDOM.getElementsByTagName("gV")[k].firstChild.data;
	           if(flag==true&&swsdm==oOption.value){
	            oOption.selected=true;
	           }
	           obj1.add(oOption);
	         }
	       
	       }
	     }
	  	 };
}

 //翻页
function doTurnPage(para){
        	 var currentPages = document.forms[0].currentPage.value;
            var maxPages = document.forms[0].maxPage.value;
            var queryFlag = document.forms[0].queryFlag.value;
               if(queryFlag == false){
                alert("请先查询！");
                return;
    		}
    		if(para == 'first'){
                if(currentPages == 1){
    				alert('当前为第一页！');
    				return ;
                }
                document.forms[0].currentPage.value = 1;
    		}else if(para == 'previous'){
                if(currentPages == 1){
    				alert('当前为第一页！');
    				return ;
                }
                document.forms[0].currentPage.value = parseInt(currentPages) - 1;
    		}else if(para == 'next'){
                if(currentPages == maxPages){
    				alert('当前为最后一页！');
    				return ;
                }
                document.forms[0].currentPage.value = parseInt(currentPages) + 1;
    		}else if(para == 'last'){
                if(currentPages == maxPages){
    				alert('当前为最后一页！');
    				return ;
                }
                document.forms[0].currentPage.value = maxPages;
    		}else if(para == 'go'){
                switchNumb = document.forms[0].switchPage.value;
                currentNumb = document.forms[0].currentPage.value;
                if(!checkNumber(trimString(switchNumb))){
    				alert("您输入的是非数字字符，请您重新新输入！");
    				return;
                }
                if(switchNumb == currentNumb){
    				alert("当前就是第" + switchNumb + "页！");
    				return;
                }
                if(parseInt(switchNumb) > parseInt(tPage)){
    				alert("最大页数是" + tPage + "页，不能跳转到" + switchNumb + "页！");
    				return;
                }
                if(switchNumb <= '0'){
    				alert("请输入大于0的页面！");
                    return;
                }
                document.forms[0].currentPage.value = switchNumb;
    		}
    		document.forms[0].actionType.value = "Query";
    		document.forms[0].submit();   
   }


</SCRIPT>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onload="fnOnLoad()">
	<%@ include file="../../include/header.jsp"%>
	<html:form action="/webapp/smsb/gdzys/gdzysJmszmCxAction.do"
		method="POST">
		<html:hidden property="actionType" />
		<html:hidden property="currentPage" />
		<html:hidden property="maxPage" />
		<html:hidden property="totalRowCount" />
		<html:hidden property="queryFlag" />
		<html:hidden property="cgsbFlag" />

		<html:hidden property="currentPageXx" />
		<html:hidden property="maxPageXx" />
		<html:hidden property="totalRowCountXx" />


		<html:hidden property="fjdmxx" />
		<html:hidden property="swsdmxx" />
		<html:hidden property="yhdmxx" />
		<html:hidden property="kksjxx" />

		<logic:equal value="Query" name="gdzysJmszmCxForm"
			property="actionType">
			<bean:define id="plkklist" name="gdzysJmszmCxForm"
				property="dataList" />
			<%
				List dataList = (List) plkklist;
							if (dataList.size() == 0) {
			%>
			<table width="96%" align="center" cellspacing="0">
				<tr>
					<td><FONT SIZE="2" COLOR="#FF0000">没有查询到符合条件的结果！</FONT></td>
				</tr>
			</table>
			<%
				}
			%>
		</logic:equal>

		<table width="80%" align="center" cellspacing="0" class="table-99">
			<tr>
				<td class="1-td1" colspan="7">减免税证明查询</td>
			</tr>
			<tr>
				<td class="1-td2" colspan="7">
					<TABLE class=table-99 bgColor=#f3f3f3 border=0>
						<TBODY>
							<TR>
								<TD vAlign=top width="6%" height=20><IMG height=9
									src="<%=static_contextpath%>images/w-01.gif" width=9></TD>
								<TD class=black9 align=left width="32%">您可以输入或选择以下条件查询信息</TD>
								<TD align=left width="68%"></TD>
							</TR>
						</TBODY>
					</TABLE>

					<TABLE class=table-99 id=table_LIST cellSpacing=0 border=0>
						<TBODY>
							<TR>
								<TD class=2-td2-t-left width="5%">分局</TD>
								<TD class=2-td2-t-left width="18%">
									<div align=left>

										<bean:define id="tempdqjs" name="gdzysJmszmCxForm"
											property="dqjs" />


										<select name="dqjs" style="width: 80%" id="dqjs"
											onchange="ajaxgetsws('false')">

											<logic:equal value="50" name="gdzysJmszmCxForm"
												property="yhjb">
												<option value="" <%=tempdqjs.equals("") ? "selected" : ""%>></option>
											</logic:equal>
											<%
												//System.out.println("1#############tempdqjs="+tempdqjs);
											%>
											<logic:iterate id="data" indexId="index" length="length"
												name="gdzysJmszmCxForm" property="swdwlist"
												type="com.ttsoft.bjtax.smsb.model.client.swdwmodel">
												<bean:define id="tempswdwid" name="data" property="swdwid" />
												<%
													//System.out.println("1#############tempswdwid="+tempswdwid);
												%>

												<option value="<bean:write name="data" property="swdwid"/>"
													<%=tempdqjs.equals(tempswdwid) ? "selected" : ""%>>

													<bean:write name="data" property="swdwmc" />
												</option>
											</logic:iterate>
										</select>
									</div>


								</TD>
								<TD class=2-td2-t-left width="9%">税务所</TD>
								<TD class=2-td2-t-left width="20%">
									<div align="left">
										<select name="cxdqjs" style="width: 80%" id="cxdqjs">
											<option value=""></option>
										</select>
									</div>
								</TD>
								<td class=2-td2-t-left>类型</td>
								<td class=2-td2-t-left><div align="left">
										<html:select property="status">
											<html:option value="全部">全部</html:option>
											<html:option value="0">正常</html:option>
											<html:option value="1">已撤销</html:option>
										</html:select>
									</div></td>


							</TR>
							<TR>

								<TD class=2-td2-left>起始日期</TD>
								<TD class=2-td2-left>
									<DIV align=left>
										<html:text property="ystart" size="20" />
										<span>YYYYMMDD<FONT SIZE="" COLOR="#FF0000"></FONT></span>
									</DIV>
								</TD>
								<TD class=2-td2-left>终止日期</TD>
								<TD class=2-td2-left>
									<DIV align=left>
										<html:text property="yend" size="20" />
										<span>YYYYMMDD<FONT SIZE="" COLOR="#FF0000"></FONT></span>
									</DIV>
								</TD>
								<td class=2-td2-left nowrap="nowrap">减免税证明编号</td>
								<td class=2-td2-left align="left">
									<div align="left">
										<html:text property="jmszmbh" size="20"></html:text>
									</div>
								</td>

							</TR>

						</TBODY>
					</TABLE>
					<table width="60%" border="0" cellpadding="0" cellspacing="4">
						<tr valign="top">
							<td>
								<div align="right">
									<input name="I2" type="image" accesskey="q" value="查询"
										onclick="doit('Query');return false;"
										onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" alt="查询"
										src="<%=static_contextpath%>images/cx-q1.jpg" width="79"
										height="22" id="chaxun" align="middle">
								</div>
							</td>
							<td>
								<div align="left">
									<input type="image" accesskey="c"
										onClick="tuichu(); return false;"
										onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" value="退出" id="tc1"
										src="<%=static_contextpath%>images/tc-c1.jpg" width="79"
										height="22">
								</div>
							</td>
						</tr>
					</table>
				</TD>
			</TR>

			<TR bgColor=#ffffff>

				<TD class=1-td2 vAlign=top align=middle width="82%"><BR>
					<div id="mydiv" style="display: none">
						<DIV class=black9 id=layerView align=left>
							<STRONG>您现在位置为第<SPAN class=tishi><bean:write
										name="gdzysJmszmCxForm" property="currentPage" /></SPAN>页，共<SPAN
								class=tishi><bean:write name="gdzysJmszmCxForm"
										property="maxPage" /></SPAN>页，<SPAN class=tishi><bean:write
										name="gdzysJmszmCxForm" property="totalRowCount" /></SPAN>条记录。
							</STRONG>
						</DIV>
						<DIV style="FONT-SIZE: 13px" align=center width="50%">
							<STRONG>查询结果列表</STRONG>
						</DIV>

						<TABLE class=table-99 id=table_LIST cellSpacing=0 border=0>
							<TBODY>
								<!-- 开始-->
								<TR>
									<TD class=2-td1-left width="8%" nowrap="nowrap">申报序列号</TD>
									<TD class=2-td1-left width="8%" nowrap="nowrap">减免税证明编号</TD>
									<TD class=2-td1-left width="8%" nowrap="nowrap">计算机代码</TD>
									<TD class=2-td1-left width="8%" nowrap="nowrap">纳税人名称</TD>
									<TD class=2-td1-left width="10%" nowrap="nowrap">开具日期</TD>
									<TD class=2-td1-left width="10%" nowrap="nowrap">开具机关</TD>
									<TD class=2-td1-left width="7%" nowrap="nowrap">打印次数</TD>
									<td class=2-td1-left width="12%" nowrap="nowrap" > 打印日期</td>
									<TD class=2-td1-left width="7%" nowrap="nowrap">打印人员</TD>
									<TD class=2-td1-left width="7%" nowrap="nowrap">类型</TD>
									<TD class=2-td1-center width="7%" nowrap="nowrap">是否记账</TD>

								</TR>
								<!-- 结束-->
								<logic:iterate id="item" name="gdzysJmszmCxForm"
									property="dataList" indexId="indexid">
									<TR id=ROW_LIST>
										<TD class=2-td2-left>&nbsp;<ttsoft:write name="item"
												property="sbbxlh" /></TD>
										<TD class=2-td2-left>&nbsp;<ttsoft:write name="item"
												property="jmszmbh" /></TD>
										<TD class=2-td2-left>&nbsp;<ttsoft:write name="item"
												property="jsjdm" /></TD>
										<TD class=2-td2-left>&nbsp;<ttsoft:write name="item"
												property="nsrmc" /></TD>
										<TD class=2-td2-left>&nbsp;<ttsoft:write name="item"
												property="kjrq" /></A></TD>
										<TD class=2-td2-left>&nbsp;<ttsoft:write name="item"
												property="kjjg" /></TD>
										<TD class=2-td2-left>&nbsp;<ttsoft:write name="item"
												property="dycs" /></TD>
										<td class=2-td2-left>&nbsp;<ttsoft:write name="item"
												property="dyrq"/></td>
										<TD class=2-td2-left>&nbsp;<ttsoft:write name="item"
												property="dyry" /></TD>
										<TD class=2-td2-left>&nbsp;<ttsoft:write name="item"
												property="lx" /></TD>
										<TD class=2-td2-center>&nbsp;<ttsoft:write name="item"
												property="jzbz" /></TD>
									</TR>
								</logic:iterate>

							</TBODY>
						</TABLE>
						<TABLE class=table-99 cellSpacing=0>
							<TBODY>
								<TR align="center">
									<TD align="center" width="50%">
										<DIV align="center">
											<IMG id=first
												onmouseover="MM_swapImage('first','','<%=static_contextpath%>images/diyiye2.jpg',1)"
												onclick="doTurnPage('first')" onmouseout=MM_swapImgRestore()
												height=22 src="<%=static_contextpath%>images/diyiye1.jpg"
												width=79 name=first> <IMG id=previous
												onmouseover="MM_swapImage('previous','','<%=static_contextpath%>images/shangyiye2.jpg',1)"
												onclick="doTurnPage('previous')"
												onmouseout=MM_swapImgRestore() height=22
												src="<%=static_contextpath%>images/shangyiye1.jpg" width=79
												name=previous> <IMG id=next
												onmouseover="MM_swapImage('next','','<%=static_contextpath%>images/xaiyiye2.jpg',1)"
												onclick="doTurnPage('next')" onmouseout=MM_swapImgRestore()
												height=22 src="<%=static_contextpath%>images/xaiyiye1.jpg"
												width=79 border=0 name=next> <IMG id=last
												onmouseover="MM_swapImage('last','','<%=static_contextpath%>images/zuimoye2.jpg',1)"
												onclick="doTurnPage('last')" onmouseout=MM_swapImgRestore()
												height=22 src="<%=static_contextpath%>images/zuimoye1.jpg"
												width=79 border=0 name=last>
										</DIV>
									</TD>

								</TR>
							</TBODY>
						</TABLE>
						<br>

						<table width="100%" border="0" cellpadding="0" cellspacing="4">
							<tr valign="bottom">
								<td width="100%" align="center"></td>

							</tr>
						</table>
						<br> <BR>
						<BR>
					</div></TD>
			</TR>

		</table>
		<br>
		<br>
		<br>

		<%@ include file="../../include/footer.jsp"%>
		</td>
		</tr>


		</table>

	</html:form>
</body>
</html:html>