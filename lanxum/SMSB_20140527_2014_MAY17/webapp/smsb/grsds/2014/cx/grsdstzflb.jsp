<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<html>
<head>
<title>生产、经营所得税纳税个人列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/swapImages.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../js/smsb_common.js"></script>
<script language=JavaScript>

var sumPage=<bean:write name="grsdstzflbForm2014cx" property="sumPage"/>
var currentPage=<bean:write name="grsdstzflbForm2014cx" property="currentPage"/>

var zjlxSel = '<select name="tzf_zfzjlxdm" style="width:90%">';
<logic:iterate id="zjlx" name="grsdstzflbForm2014cx" property="sfzjlxList">
	var value = '<bean:write name="zjlx" property="value"/>';
	var text = '<bean:write name="zjlx" property="text"/>';
	zjlxSel += '<option value='+value+'>'+text+'</option>';
</logic:iterate>
zjlxSel += '</select>';

function doReturn()
{
	document.forms[0].action = "quit.do";
	document.forms[0].submit();
}





//添加信息
function addMxInSYS(obj){
	var body = document.getElementById("tzflist");
	var rowCount = body.rows.length;
	var newRow = body.insertRow(rowCount++);	//新增一行
	
	var jsjdm = document.getElementById("jsjdm").value;		//计算机代码
	var name = obj.gr_tzzxm;	//姓名
	var sfzjlx = obj.gr_sfzjlx;	//身份证件类型 
	var sfzjhm = obj.gr_sfzjhm;	//身份证件号码
	var fpbl = obj.gr_fpbl;	//分配比例
	var txzt ;
	if(obj.gr_txzt=="0"){
		txzt="未填写完成";
	}else{
		txzt="已填写完成";
	}
	
	
	C0 = newRow.insertCell(0);
	C0.setAttribute("className","2-td2-left");
	C0.innerHTML="<a style='cursor:hand;text-decoration:underline'  tabIndex='-1' href='/smsb/webapp/smsb/grsds/grsdsJbxxAction2014cx.do?actionType=Show&qyxx_jsjdm="+jsjdm+"&grxx_sfzjlx="+sfzjlx+"&grxx_sfzjhm="+sfzjhm+"'>"+name+"</a>";	//投资者姓名
	
	
	C1 = newRow.insertCell(1);
	C1.setAttribute("className","2-td2-center");
	C1.innerHTML=zjlxSel;
	C1.childNodes[0].value= sfzjlx;
	C1.childNodes[0].disabled=true;
	
	C2 = newRow.insertCell(2);
	C2.setAttribute("className","2-td2-right");
	C2.innerHTML="<a style='cursor:hand;text-decoration:underline'  tabIndex='-1' href='/smsb/webapp/smsb/grsds/grsdsJbxxAction2014cx.do?actionType=Show&qyxx_jsjdm="+jsjdm+"&grxx_sfzjlx="+sfzjlx+"&grxx_sfzjhm="+sfzjhm+"'>"+sfzjhm+"</a>";
	
	C3 = newRow.insertCell(3);
	C3.setAttribute("className","2-td2-right");
	C3.innerHTML=fpbl;
	
	C4 = newRow.insertCell(4);
	C4.setAttribute("className","2-td2-right");
	C4.innerHTML=txzt;
	
	
}




//根据 条件 查询
function query(){
	var query_jsjdm = document.getElementById("jsjdm").value;
	var query_sfzjlx = document.getElementById("sfzjlx").value;
	var query_sfzjhm = document.getElementById("sfzjhm").value;
	var xmlhttp_request1;
    if(window.ActiveXObject){
	       xmlhttp_request1=new ActiveXObject("Microsoft.XMLHTTP");
	    }else if(window.XMLHttpRequest){
	       xmlhttp_request1=new XMLHttpRequest();
	     } else {
	       return;
	     }
     xmlhttp_request1.open("GET", "/smsb/webapp/smsb/grsds/grsdsTzflbAction2014cx.do?actionType=Query&jsjdm="+query_jsjdm+"&sfzjlx="+query_sfzjlx+"&sfzjhm="+query_sfzjhm,true);
	     xmlhttp_request1.onreadystatechange =function zdyprocessAjaxResponse()
	     {
	    	 if (xmlhttp_request1.readyState == 4) {
         	if (xmlhttp_request1.status == 200) { 
         		try{
         			var resp = eval("("+xmlhttp_request1.responseText+")");
         			var msg = resp.msg;
         			var data = resp.datels;
         			sumPage = resp.sumPage;
         			currentPage = 1;
  					if( msg)
  					{
  						alert(msg);
  					}else{
  						//删除原内容
  						var table=document.getElementById("tzflist");
  						 var len=table.rows.length;
  						 for(var i=1;i<len;i++){
  							table.deleteRow(1);
  					 	}
  						//添加新内容
  						for(var i=0;i<data.length;i++)
  							addMxInSYS(data[i]);
  					}
         		}catch(e){
         			return;
         		}
         		}
	    	 }
	     };
     xmlhttp_request1.send(null);
}

function getPageInf(obj){
	if(obj=='first'){
		if(currentPage==1){
			alert("您已经是第一页");
			return false;
		}else{
			currentPage=1;
		}
	}
	if(obj=='previous'){
		if(currentPage==1){
			alert("您已经是第一页");
			return false;
		}else{
			currentPage--;
		}
	}
	if(obj=='next'){
		if(currentPage==sumPage){
			alert("您已经是最后一页");
			return false;
		}else{
			currentPage++;
		}
	}
	if(obj=='last'){
		if(currentPage==sumPage){
			alert("您已经是最后一页");
			return false;
		}else{
			currentPage=sumPage;
		}
	}
	doPage();
}



//分页查询
function doPage(){
	
	var xmlhttp_request1;
    if(window.ActiveXObject){
	       xmlhttp_request1=new ActiveXObject("Microsoft.XMLHTTP");
	    }else if(window.XMLHttpRequest){
	       xmlhttp_request1=new XMLHttpRequest();
	     } else {
	       return;
	     }
     xmlhttp_request1.open("GET", "/smsb/webapp/smsb/grsds/grsdsTzflbAction2014cx.do?actionType=Page&currentPage="+currentPage,true);
	     xmlhttp_request1.onreadystatechange =function zdyprocessAjaxResponse()
	     {
	    	 if (xmlhttp_request1.readyState == 4) {
         	 if (xmlhttp_request1.status == 200) {      		
         		try{
         			var resp = eval("("+xmlhttp_request1.responseText+")");
         			var msg = resp.msg;
         			var date = resp.datels;
      
  					if( msg)
  					{
  						alert(msg);
  					}else{
  						//删除原内容
  						var table=document.getElementById("tzflist");
  						 var len=table.rows.length;
  						 for(var i=1;i<len;i++){
  							table.deleteRow(1);
  					 	}
  						//添加新内容
  						for(var i=0;i<date.length;i++)
  							addMxInSYS(date[i]);
  					}
         		}catch(e){
         			return;
         		}
         	}
	    	}
	     };
     xmlhttp_request1.send(null);
}



function load(){
	var msg="<bean:write name='grsdstzflbForm2014cx' property='msg'/>";
	if(msg){
		alert(msg);
	}
	
	
}
</script>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="load()">
		<jsp:include page="../../../include/header.jsp" flush="true" >
    		<jsp:param name="name" value="生产、经营所得税纳税个人列表" />
			<jsp:param name="help_url" value="help/wssb/qyzhsb/gzsx/gzsx-000.htm"/>
    	</jsp:include>
<html:form action="/webapp/smsb/grsds/grsdsTzflbAction2014cx.do">
<table width="80%" height="50%" border="0" align="center" cellpadding="0" cellspacing="0" >
<bean:define id="zjlxList" name="grsdstzflbForm2014cx" property="sfzjlxList"/>
	<tr>
		<td class="1-td1" >生产、经营所得税纳税个人列表</td>
	</tr>
	<tr>
		<td class="1-td2" valign="top">
			<br/>
				<div style="overflow: auto; width:98%; margin:0">
				<table id="tab_jdjdm" cellSpacing="0" cellPadding="0" width="100%" border="0"  align = "center">
					<tr>
						<td class="2-td2-t-center" width="50%"><STRONG>计算机代码：</STRONG></td>
						<td class="2-td2-t-right" width="50%"><html:text styleId="jsjdm" maxlength="8" style="width:60%" name="grsdstzflbForm2014cx"  property="jsjdm" />
						</td>
					</tr>
					<tr>
						<td class="2-td2-t-center" width="50%"><STRONG>身份证件类型：</STRONG></td>
						<td class="2-td2-t-right" width="50%">
							<html:select name="grsdstzflbForm2014cx" property="sfzjlx" style="width:60%" >
								<html:option value=""></html:option>
								
								<html:options collection="zjlxList" property="value" labelProperty="text"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<td class="2-td2-t-center" width="50%"><STRONG>身份证件号码：</STRONG></td>
						<td class="2-td2-t-right" width="50%"><html:text styleId="sfzjhm"  name="grsdstzflbForm2014cx" style="width:60%" property="sfzjhm" />
						</td>
					</tr>
				</table>
				</div>
			<br/>
			
		<div  align="center">
			<span>
				<a style="cursor:hand"  tabIndex="-1" onClick="query();return false;"><img name="chaxun" value="查询" alt="查询" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>/images/chaxun2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/chaxun1.jpg" width="79" height="22" id="chaxun"></a>&nbsp;&nbsp;
				<a style="cursor:hand"  tabIndex="-1" onClick="tuichu();return false;"><img name="return" value="退出" alt="退出" onMouseOver="MM_swapImage('return','','<%=static_contextpath%>/images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/tuichu1.jpg"  width="79" height="22" id="return"></a> 
			</span>			
		</div>
			
			
			<div>
			<table width="99%" border="0" cellpadding="0" cellspacing="0">
			  <tr><td>&nbsp;</td></tr>
              <tr>
                <td width="700"> <hr width="100%" size="1" class="hr1" >
                </td>
                <td width="100" align="center" class="black9"><strong>投资者信息</strong></td>
                <td width="700"> <hr width="100%" size="1" class="hr1" >
                </td>
              </tr>
              <tr><td>&nbsp;</td></tr>
         	</table>
			</div>
			
			<div style="overflow: auto; width:98%; height:180px;padding:6px">			
		
				<table id="tzflist" cellSpacing="0" cellPadding="0" width="100%" border="0"  align = "center">
					<tr>
						<td class="2-td1-left" width="20%">姓名</td>
						<td class="2-td1-center" width="20%">身份证件类型</td>
						<td class="2-td1-right" width="20%">身份证件号码</td>
						<td class="2-td1-right" width="20%">分配比例(%)</td>
						<td class="2-td1-right" width="20%">填写状态</td>
					</tr>
					<logic:iterate id="tzf" name="grsdstzflbForm2014cx" property="grList">
       				<tr>
       				
       					<td class="2-td2-left"><a style="cursor:hand;text-decoration:underline" href="/smsb/webapp/smsb/grsds/grsdsJbxxAction2014cx.do?actionType=Show&qyxx_jsjdm=<bean:write name='grsdstzflbForm2014cx' property='jsjdm'/>&grxx_sfzjlx=<bean:write name='tzf' property='gr_sfzjlx'/>&grxx_sfzjhm=<bean:write name='tzf' property='gr_sfzjhm'/>"><bean:write name="tzf" property="gr_tzzxm"/></a></td>
       					<td class="2-td2-center">
							<html:select name="tzf" property="gr_sfzjlx" style="width:90%" disabled="true">
								<html:option value=""></html:option>
								
								<html:options collection="zjlxList" property="value" labelProperty="text"/>
							</html:select>
						</td>
       					<td class="2-td2-right"><a style="cursor:hand;text-decoration:underline" href="/smsb/webapp/smsb/grsds/grsdsJbxxAction2014cx.do?actionType=Show&qyxx_jsjdm=<bean:write name='grsdstzflbForm2014cx' property='jsjdm'/>&grxx_sfzjlx=<bean:write name='tzf' property='gr_sfzjlx'/>&grxx_sfzjhm=<bean:write name='tzf' property='gr_sfzjhm'/>"><bean:write name="tzf" property="gr_sfzjhm"/></a></td>
       					<td class="2-td2-right"><bean:write name="tzf" property="gr_fpbl"/></td>
       					<td class="2-td2-right">
       						<bean:define id="gr_txzt" name="tzf" property="gr_txzt"/>
       					<%if("1".equals(gr_txzt)){ %>
       						已填写完成
       					<% }else{%>
       						未填写完成
       					<%} %>
       					</td>
       					
       					
       				</tr>
       				</logic:iterate>					
				</table>
			</div>
		</td>
	</tr>
		
</table>
<div align="right" style="width:90%">
	<span align=center width="80%">
		<a style="cursor:hand"  tabIndex="-1" onClick="getPageInf('previous');return false;"><img name="Shangyiye" value="上一页" alt="上一页" onMouseOver="MM_swapImage('Shangyiye','','<%=static_contextpath%>/images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/shangyiye1.jpg" width="79" height="22" id="Shangyiye"></a>&nbsp;
		<a style="cursor:hand"  tabIndex="-1" onClick="getPageInf('next');return false;"><img name="Xiayiye" value="下一页" alt="下一页" onMouseOver="MM_swapImage('Xiayiye','','<%=static_contextpath%>/images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/xaiyiye1.jpg" width="79" height="22" id="Xiayiye"></a>&nbsp;	
		<a style="cursor:hand"  tabIndex="-1" onClick="getPageInf('first');return false;"><img  name="Diyiye" value="第一页" alt="第一页" onMouseOver="MM_swapImage('Diyiye','','<%=static_contextpath%>/images/diyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/diyiye1.jpg"  width="79" height="22" id="Diyiye"></a>&nbsp;	
		<a style="cursor:hand"  tabIndex="-1" onClick="getPageInf('last');return false;"><img  name="Zuimoye" value="最末页" alt="最末页" onMouseOver="MM_swapImage('Zuimoye','','<%=static_contextpath%>/images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/zuimoye1.jpg"  width="79" height="22" id="Zuimoye"></a> 
	</span>
</div>

<br/><br/>
</html:form>
<%@ include file="../../../include/footernew.jsp"%>
</body>
</html>