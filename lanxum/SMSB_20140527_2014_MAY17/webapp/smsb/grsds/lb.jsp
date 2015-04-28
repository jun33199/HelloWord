<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%
	String sbheader_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<html>
<head>
<title>生产、经营所得税纳税个人列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="<%=sbheader_contextpath%>js/swapImages.js"></script>
<script language=JavaScript>
function doReturn()
{
	document.forms[0].action = "quit.do";
	document.forms[0].submit();
}

function addRow() {
	var tzzTab = document.all.tzzTab;
	var tr = tzzTab.insertRow(tzzTab.rows.length-2);
	var td1 = tr.insertCell(0);
	var td2 = tr.insertCell(1);
	var td3 = tr.insertCell(2);
	var td4 = tr.insertCell(3);
	var td5 = tr.insertCell(4);
	var td6 = tr.insertCell(5);
	td1.innerHTML = "test";
	td2.innerHTML = "test";
	td3.innerHTML = "test";
	td4.innerHTML = "test";
	td5.innerHTML = "test";
	td6.innerHTML = "test";
}

var zjlxSel = '<select name="tzf_zfzjlxdm" style="width:90%">';
<logic:iterate id="zjlx" name="grsdsForm" property="zjlxList">
	var key = '<bean:write name="zjlx" property="dm"/>';
	var val = '<bean:write name="zjlx" property="name"/>';
	zjlxSel += '<option value='+key+'>'+val+'</option>';
</logic:iterate>
zjlxSel += '</select>';
//增加一行
function addMx(){
	//alert("增加");
	var body = document.getElementById("tzzTab");
	var rowCount = body.rows.length;
	var newRow = body.insertRow(rowCount-2);	//新增一行
	
	C0 = newRow.insertCell(0);
	C0.setAttribute("className","2-td2-left");
	C0.innerHTML="<input type='text' name='tzf_name' style='width:90%'>";
	
	C1 = newRow.insertCell(1);
	C1.setAttribute("className","2-td2-left");
	C1.innerHTML=zjlxSel;
	
	C2 = newRow.insertCell(2);
	C2.setAttribute("className","2-td2-left");
	C2.innerHTML="<input type='text' name='tzf_zfzjhm' style='width:90%'>";
	
	C3 = newRow.insertCell(3);
	C3.setAttribute("className","2-td2-left");
	C3.innerHTML="<input type='text' name='tzf_fpbl' style='width:90%'>";
	
	C4 = newRow.insertCell(4);
	C4.setAttribute("className","2-td2-left");
	C4.innerHTML="未填写完成";
	
	C5 = newRow.insertCell(5);
	C5.setAttribute("className","2-td2-center");
	C5.innerHTML="<a style='cursor:hand;text-decoration:underline'  tabIndex='-1' onclick='javascript:return addSave(this);return false;'>确认添加</a>";
}

//对新增加的信息保存
function addSave(obj){
	
	var td_obj = obj.parentNode;
	var tr_obj = td_obj.parentNode;
	
	var jsjdm = document.all.jsjdm.value;
	var gr_tzzxm = tr_obj.childNodes[0].childNodes[0].value;	//投资者姓名
	var gr_sfzjlx = tr_obj.childNodes[1].childNodes[0].value;	//身份证件类型
	var gr_sfzjhm = tr_obj.childNodes[2].childNodes[0].value;	//身份证件号码
	var gr_fpbl = tr_obj.childNodes[3].childNodes[0].value;	    //分配比例
	

	//验证身份证件号码
	var reg = /^[A-Za-z0-9]+$/
	if(reg.test(gr_sfzjhm) == false) {
		alert("身份证件号码格式不正确！");
		return false;
	}
	
	//ajax
	var xmlhttp_request1;
    if(window.ActiveXObject){
	       xmlhttp_request1=new ActiveXObject("Microsoft.XMLHTTP");
	    }else if(window.XMLHttpRequest){
	       xmlhttp_request1=new XMLHttpRequest();
	     } else {
	       return;
	     }
     xmlhttp_request1.open("GET", "/smsb/webapp/smsb/grsds/grsdsAction.do?actionType=AddSave&gr_tzzxm="+gr_tzzxm+"&gr_sfzjlx="+gr_sfzjlx+"&gr_sfzjhm="+gr_sfzjhm+"&gr_fpbl="+gr_fpbl+"&jsjdm="+jsjdm,true);
     
	     xmlhttp_request1.onreadystatechange =function zdyprocessAjaxResponse()
	     {
	    	 if (xmlhttp_request1.readyState == 4) {
         	 if (xmlhttp_request1.status == 200) { 
         		var xmlDOM1 = xmlhttp_request1.responseXML;
         		var result = xmlDOM1.selectSingleNode("/re").text;
  				if("SUCCESS"!=result)
  				{
  					alert("添加投资者失败，请重新确认投资者信息！");
  				}else{
  					alert("添加成功！");
  					changeAdd(tr_obj);
  				}
         		
         	 }
	    	}
	     };
	     
	     xmlhttp_request1.send(null);
}

//将添加 的一行转为正常显示
function changeAdd(tr_obj)
{
	var jsjdm = document.all.jsjdm.value;		//计算机代码
	var name = tr_obj.childNodes[0].childNodes[0].value;	//姓名
	var sfzjlx = tr_obj.childNodes[1].childNodes[0].value;	//身份证件类型 
	var sfzjhm = tr_obj.childNodes[2].childNodes[0].value;	//身份证件号码
	var fpbl =  tr_obj.childNodes[3].childNodes[0].value;	//分配比例
	var cz =  tr_obj.childNodes[5].childNodes[0].value;		//操作
	
	tr_obj.childNodes[0].innerHTML="<a style='cursor:hand;text-decoration:underline'  tabIndex='-1' href='grsdsJbxxb2014.dc?actionType=Show&jsjdm="+jsjdm+"&sfzjlx="+sfzjlx+"&sfzjhm="+sfzjhm+"'>"+name+"</a>";	//投资者姓名
	tr_obj.childNodes[1].childNodes[0].disabled=true;
	tr_obj.childNodes[2].innerHTML="<a style='cursor:hand;text-decoration:underline'  tabIndex='-1' href='grsdsJbxxb2014.dc?actionType=Show&jsjdm="+jsjdm+"&sfzjlx="+sfzjlx+"&sfzjhm="+sfzjhm+"'>"+sfzjhm+"</a>";
	tr_obj.childNodes[3].innerHTML=fpbl;
	tr_obj.childNodes[5].innerHTML="<a style='cursor:hand;text-decoration:underline'  tabIndex='-1' onclick='javascript:return deleteMx(this);return false;'>删除</a>"
}

//删除
function deleteMx(obj){	
	var td_obj = obj.parentNode;
	var tr_obj = td_obj.parentNode;
	
	var jsjdm = document.all.jsjdm.value;
	var gr_sfzjlx = tr_obj.childNodes[1].childNodes[0].value;	//身份证件类型
	var gr_sfzjhm = tr_obj.childNodes[2].childNodes[0].innerText;	//身份证件号码
	//ajax
	var xmlhttp_request1;
    if(window.ActiveXObject){
	       xmlhttp_request1=new ActiveXObject("Microsoft.XMLHTTP");
	    }else if(window.XMLHttpRequest){
	       xmlhttp_request1=new XMLHttpRequest();
	     } else {
	       return;
	     }
     xmlhttp_request1.open("GET", "/smsb/webapp/smsb/grsds/grsdsAction.do?actionType=Delete&gr_sfzjlx="+gr_sfzjlx+"&gr_sfzjhm="+gr_sfzjhm+"&jsjdm="+jsjdm,true);
	     xmlhttp_request1.onreadystatechange =function zdyprocessAjaxResponse()
	     {
	    	 if (xmlhttp_request1.readyState == 4) {
         	if (xmlhttp_request1.status == 200) { 
         		var xmlDOM1 = xmlhttp_request1.responseXML;
         		
         		var result = xmlDOM1.selectSingleNode("/re").text;
  				if("SUCCESS"!=result)
  				{
  					alert("删除投资者失败，请与管理员联系！");
  				}else{
  					alert("删除成功！");
  					var tbody=tr_obj.parentNode;
  					tbody.removeChild(tr_obj);
  				}
         	}
	    	 }
	     };
     xmlhttp_request1.send(null);
}
</script>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">
<table width="97%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2>

    	<jsp:include page="../include/header.jsp" flush="true" >
    		<jsp:param name="name" value="生产、经营所得税纳税个人列表" />
		<jsp:param name="help_url" value="help/wssb/qyzhsb/gzsx/gzsx-000.htm"/>
    	</jsp:include>

    	<table width="80%" height="61%" border="0" cellpadding="0" cellspacing="0">
	  <tr>
	    <td valign="top">
	     <br><br><br><br>
	      <html:errors/>
	     <table cellspacing="0" class="table-99" width="90%">
		<!-- begin ========================================================================================--->
			<tr>
				<td class="1-td1" >生产、经营所得税纳税个人列表</td>
			</tr>
            <tr>
            	<td class="1-td2">
            	<br/>
            	<font style="font-weight: bold; float: left; font-size: 10; color: black;">来源于税务登记的投资者信息：</font>
            	<div  style="float: left;">
            		&nbsp;&nbsp;&nbsp;计算机代码：<input type="text" id="jsjdm">
            	</div>
            	<form>
       			<table id="tzzTab" cellspacing="0" width="98%">
       				<tr>
       					<td class="2-td1-left">姓名：</td>
       					<td class="2-td1-center">身份证件类型：</td>
       					<td class="2-td1-right">身份证件号码：</td>
       					<td class="2-td1-right">分配比例(%)：</td>
       					<td class="2-td1-right">填写状态：</td>
       					<td class="2-td1-right">操作：</td>
       				</tr>
       				<logic:iterate id="tzf" name="grsdsForm" property="tzfList">
       				<tr>
       					<td class="2-td2-left"><a href="/smsb/webapp/smsb/grsds/grsdsAction.do?actionType=InitJbxx"><bean:write name="tzf" property="tzzxm"/></a></td>
       					<td class="2-td2-center">
							<html:select name="tzf" property="zjlxdm" style="width:90%" disabled="true">
								<html:option value=""></html:option>
								<bean:define id="zjlxList" name="grsdsForm" property="zjlxList"/>
								<html:options collection="zjlxList" property="dm" labelProperty="name"/>
							</html:select>
						</td>
       					<td class="2-td2-right"><a href="/smsb/webapp/smsb/grsds/grsdsAction.do?actionType=InitJbxx"><bean:write name="tzf" property="zjhm"/></a></td>
       					<td class="2-td2-right"><bean:write name="tzf" property="fpbl"/></td>
       					<td class="2-td2-right"><bean:write name="tzf" property="txzt"/></td>
       					<td class="2-td2-right">
							<a tabIndex="-1" href="javascript:void(0)" style="cursor: hand; text-decoration: underline;" onclick="javascript:return deleteMx(this);return false;">删除</a>
						</td>
       					
       				</tr>
       				</logic:iterate>
       			<TR>
                <TD align=right nowrap colspan="6">
                	<br/>
					<IMG id=firstPage onmouseover=MM_swapImage('firstPage','','<%=static_contextpath%>images/diyiye2.jpg',1) onmouseout=MM_swapImgRestore() src=<%=static_contextpath%>images/diyiye1.jpg width=79 height=22>&nbsp;
					<IMG id=previousPage onmouseover=MM_swapImage('previousPage','','<%=static_contextpath%>images/shangyiye2.jpg',1) onmouseout=MM_swapImgRestore() src=<%=static_contextpath%>images/shangyiye1.jpg width=79 height=22>&nbsp;
					<IMG id=nextPage onmouseover=MM_swapImage('nextPage','','<%=static_contextpath%>images/xaiyiye2.jpg',1) onmouseout=MM_swapImgRestore() src=<%=static_contextpath%>images/xaiyiye1.jpg width=79 height=22>&nbsp;
					<IMG id=lastPage onmouseover=MM_swapImage('lastPage','','<%=static_contextpath%>images/zuimoye2.jpg',1) onmouseout=MM_swapImgRestore() src=<%=static_contextpath%>images/zuimoye1.jpg width=79 height=22>&nbsp; 
               	</TD>
               	</TR>
               	
       			<TR>
                <TD colspan="6">
                  <DIV align=center>
                  <br/>
                  <input type="image" value="新增" onclick="addMx(); return false;"
                    onMouseOver="MM_swapImage('xinzeng1','','<%=static_contextpath%>images/xinzeng2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/xinzeng1.jpg"
                    width="79" height="22" id="xinzeng1" alt="新增">
                  <input type="image" value="返回"
                    onMouseOver="MM_swapImage('fanhui1','','<%=static_contextpath%>images/fanhui2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/fanhui1.jpg"
                    width="79" height="22" id="fanhui1" alt="返回">
				  <input type="image" value="退出"
                    onMouseOver="MM_swapImage('tuichu1','','<%=static_contextpath%>images/tuichu2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/tuichu1.jpg"
                    width="79" height="22" id="tuichu1" alt="退出">
				  </DIV></TD>
				  </TR>
       			</table>
       			</form>
       			</td>
            </tr>
	    <!-- end ==========================================================================================--->
	        <tr>
	          <td class="1-td2-center"><br>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
				 <tr>
				    <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				    <td width="20%" align="center" class="black9"><strong><font color="#0000FF">注意事项</font></strong></td>
				     <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				  </tr>
				</table>
				<table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
				   <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
				      <td height="47"  >
				        <p style ="color:red">
				            &nbsp;&nbsp;&nbsp;&nbsp;谨声明：此表是根据《中华人民共和国个人所得税法》及其实施条例和国家相关法律法规规定填写的，是真实的、完整的、可靠的。<br>
				         </p>
				       </td>
				    </tr>
				</table>
	  			<br>
	          </td>
	         </tr>
	     </table>
	    </td>
	  </tr>
    	</table>
<br>
    </td>
  </tr>
<tr>
  <td valign="bottom" align="center">
	<%@ include file="../include/footer.jsp" %>
  </td>
</tr>
 </table>
</body>
</html>