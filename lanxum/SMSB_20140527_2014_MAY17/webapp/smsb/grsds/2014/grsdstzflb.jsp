<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<html>
<head>
<title>��������Ӫ����˰��˰�����б�</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/swapImages.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../js/smsb_common.js"></script>
<script language=JavaScript>

var sumPage=<bean:write name="grsdstzflbForm2014" property="sumPage"/>
var currentPage=<bean:write name="grsdstzflbForm2014" property="currentPage"/>

var zjlxSel = '<select name="tzf_zfzjlxdm" style="width:90%">';
<logic:iterate id="zjlx" name="grsdstzflbForm2014" property="sfzjlxList">
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



//����һ��
function addMx(){
	//alert("����");
	var body = document.getElementById("tzflist");
	var rowCount = body.rows.length;
	var newRow = body.insertRow(rowCount++);	//����һ��
	
	C0 = newRow.insertCell(0);
	C0.setAttribute("className","2-td2-left");
	C0.innerHTML="<input type='text' name='tzf_name' style='width:90%'>";
	
	C1 = newRow.insertCell(1);
	C1.setAttribute("className","2-td2-center");
	C1.innerHTML=zjlxSel;
	
	C2 = newRow.insertCell(2);
	C2.setAttribute("className","2-td2-right");
	C2.innerHTML="<input type='text' name='tzf_zfzjhm' style='width:90%'>";
	
	C3 = newRow.insertCell(3);
	C3.setAttribute("className","2-td2-right");
	C3.innerHTML="<input type='text' name='tzf_fpbl' style='width:90%'>";
	
	C4 = newRow.insertCell(4);
	C4.setAttribute("className","2-td2-right");
	C4.innerHTML="δ��д���";
	
	C5 = newRow.insertCell(5);
	C5.setAttribute("className","2-td2-right");
	C5.innerHTML="<a style='cursor:hand;text-decoration:underline'  tabIndex='-1' onclick='javascript:return addSave(this);return false;'>ȷ�����</a>";
}

function addMxInSYS(obj){
	var body = document.getElementById("tzflist");
	var rowCount = body.rows.length;
	var newRow = body.insertRow(rowCount++);	//����һ��
	
	var jsjdm = document.getElementById("jsjdm").value;		//���������
	var name = obj.gr_tzzxm;	//����
	var sfzjlx = obj.gr_sfzjlx;	//���֤������ 
	var sfzjhm = obj.gr_sfzjhm;	//���֤������
	var fpbl = obj.gr_fpbl;	//�������
	var txzt ;
	if(obj.gr_txzt=="0"){
		txzt="δ��д���";
	}else{
		txzt="����д���";
	}
	
	
	C0 = newRow.insertCell(0);
	C0.setAttribute("className","2-td2-left");
	C0.innerHTML="&nbsp;<a style='cursor:hand;text-decoration:underline'  tabIndex='-1' href='/smsb/webapp/smsb/grsds/grsdsJbxxAction2014.do?actionType=Show&qyxx_jsjdm="+jsjdm+"&grxx_sfzjlx="+sfzjlx+"&grxx_sfzjhm="+sfzjhm+"'>"+name+"</a>";	//Ͷ��������
	
	
	C1 = newRow.insertCell(1);
	C1.setAttribute("className","2-td2-center");
	C1.innerHTML=zjlxSel;
	C1.childNodes[0].value= sfzjlx;
	C1.childNodes[0].disabled=true;
	
	C2 = newRow.insertCell(2);
	C2.setAttribute("className","2-td2-right");
	C2.innerHTML="<a style='cursor:hand;text-decoration:underline'  tabIndex='-1' href='/smsb/webapp/smsb/grsds/grsdsJbxxAction2014.do?actionType=Show&qyxx_jsjdm="+jsjdm+"&grxx_sfzjlx="+sfzjlx+"&grxx_sfzjhm="+sfzjhm+"'>"+sfzjhm+"</a>";
	
	C3 = newRow.insertCell(3);
	C3.setAttribute("className","2-td2-right");
	C3.innerHTML=fpbl;
	
	C4 = newRow.insertCell(4);
	C4.setAttribute("className","2-td2-right");
	C4.innerHTML=txzt;
	
	C5 = newRow.insertCell(5);
	C5.setAttribute("className","2-td2-right");
	C5.innerHTML="<a style='cursor:hand;text-decoration:underline'  tabIndex='-1' onclick='javascript:return deleteMx(this);return false;'>ɾ��</a>";
}

//�������ӵ���Ϣ����
function addSave(obj){
	
	var td_obj = obj.parentNode;
	var tr_obj = td_obj.parentNode;
	
	var jsjdm = document.getElementById("jsjdm").value;
	if(!jsjdm){
		alert("���������������������Ͷ���ߣ�");
		return false;
	}
	var gr_tzzxm = tr_obj.childNodes[0].childNodes[0].value;	//Ͷ��������
	var gr_sfzjlx = tr_obj.childNodes[1].childNodes[0].value;	//���֤������
	var gr_sfzjhm = tr_obj.childNodes[2].childNodes[0].value;	//���֤������
	var gr_fpbl = tr_obj.childNodes[3].childNodes[0].value;	    //�������
	
	if(""==gr_fpbl||isNaN(gr_fpbl)){
		alert("���������ʽ����ȷ��");
		tr_obj.childNodes[3].childNodes[0].value="0.0000";
		return false;
	}else{
		gr_fpbl = parseFloat(gr_fpbl).toFixed(4);
		tr_obj.childNodes[3].childNodes[0].value=gr_fpbl;
		if(gr_fpbl>999){
			alert("���������������ȷ��д���������");
			return false;
		}
	}
	
	
	//��֤���֤������
	var reg = /^[A-Za-z0-9]+$/
	if(reg.test(gr_sfzjhm) == false) {
		alert("���֤�������ʽ����ȷ��");
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
     xmlhttp_request1.open("GET", "/smsb/webapp/smsb/grsds/grsdsTzflbAction2014.do?actionType=AddSave&gr_tzzxm="+gr_tzzxm+"&gr_sfzjlx="+gr_sfzjlx+"&gr_sfzjhm="+gr_sfzjhm+"&gr_fpbl="+gr_fpbl+"&jsjdm="+jsjdm,true);
     
	     xmlhttp_request1.onreadystatechange =function zdyprocessAjaxResponse()
	     {
	    	 if (xmlhttp_request1.readyState == 4) {
         	 if (xmlhttp_request1.status == 200) { 
         		 try{
         			var resp = eval("("+xmlhttp_request1.responseText+")");
        
		         	var msg = resp.msg;
		         	var result=resp.result;
		  			if(!result)
		  			{
		  				if(msg){
		  					alert(msg);
		  				}
		  			}else{
		  				changeAdd(tr_obj);	
		  				alert("��ӳɹ�");
		  			}
         		 }catch(e){
         			 alert("���ʧ��,�������Ա��ϵ");
         		 }
         	 }
	    	}
	     };
	     
	     xmlhttp_request1.send(null);
}

//����� ��һ��תΪ������ʾ
function changeAdd(tr_obj)
{
	var jsjdm = document.getElementById("jsjdm").value;		//���������
	var name = tr_obj.childNodes[0].childNodes[0].value;	//����
	var sfzjlx = tr_obj.childNodes[1].childNodes[0].value;	//���֤������ 
	var sfzjhm = tr_obj.childNodes[2].childNodes[0].value;	//���֤������
	var fpbl =  tr_obj.childNodes[3].childNodes[0].value;	//�������
	var cz =  tr_obj.childNodes[5].childNodes[0].value;		//����
	
	
	
	tr_obj.childNodes[0].innerHTML="&nbsp;<a style='cursor:hand;text-decoration:underline'  tabIndex='-1' href='/smsb/webapp/smsb/grsds/grsdsJbxxAction2014.do?actionType=Show&qyxx_jsjdm="+jsjdm+"&grxx_sfzjlx="+sfzjlx+"&grxx_sfzjhm="+sfzjhm+"'>"+name+"</a>";		//Ͷ��������
	tr_obj.childNodes[1].childNodes[0].disabled=true;
	tr_obj.childNodes[2].innerHTML="<a style='cursor:hand;text-decoration:underline'  tabIndex='-1' href='/smsb/webapp/smsb/grsds/grsdsJbxxAction2014.do?actionType=Show&qyxx_jsjdm="+jsjdm+"&grxx_sfzjlx="+sfzjlx+"&grxx_sfzjhm="+sfzjhm+"'>"+sfzjhm+"</a>";   //
	tr_obj.childNodes[3].innerHTML=fpbl;
	tr_obj.childNodes[5].innerHTML="&nbsp;<a style='cursor:hand;text-decoration:underline'  tabIndex='-1' onclick='javascript:return deleteMx(this);return false;'>ɾ��</a>"
}

//ɾ��
function deleteMx(obj){	
	var td_obj = obj.parentNode;
	var tr_obj = td_obj.parentNode;
	
	var jsjdm = document.getElementById("jsjdm").value;
	var gr_sfzjlx = tr_obj.childNodes[1].childNodes[0].value;	//���֤������
	var gr_sfzjhm = tr_obj.childNodes[2].innerText;	//���֤������
	//ajax
	var xmlhttp_request1;
    if(window.ActiveXObject){
	       xmlhttp_request1=new ActiveXObject("Microsoft.XMLHTTP");
	    }else if(window.XMLHttpRequest){
	       xmlhttp_request1=new XMLHttpRequest();
	     } else {
	       return;
	     }
     xmlhttp_request1.open("GET", "/smsb/webapp/smsb/grsds/grsdsTzflbAction2014.do?actionType=Delete&gr_sfzjlx="+gr_sfzjlx+"&gr_sfzjhm="+gr_sfzjhm+"&jsjdm="+jsjdm,true);
	     xmlhttp_request1.onreadystatechange =function zdyprocessAjaxResponse()
	     {
	    	 if (xmlhttp_request1.readyState == 4) {
         	 if (xmlhttp_request1.status == 200) {      		
         		try{
         			var resp = eval("("+xmlhttp_request1.responseText+")");
        
		         	var msg = resp.msg;
		         	var result=resp.result;
		  			if(!result)
		  			{
		  				if(msg){
		  					alert(msg);
		  				}
		  			}else{
		  				var tbody=tr_obj.parentNode;
	  					tbody.removeChild(tr_obj);	
		  				alert("ɾ���ɹ�");
		  			}
         		 }catch(e){
         			 alert("ɾ��ʧ��,�������Ա��ϵ");
         		 }	
         	}
	    	}
	     };
     xmlhttp_request1.send(null);
}

//���ݼ�������� ��ѯ
function onLeaveJsjdm(){
	var query_jsjdm = document.getElementById("jsjdm").value;
	var xmlhttp_request1;
    if(window.ActiveXObject){
	       xmlhttp_request1=new ActiveXObject("Microsoft.XMLHTTP");
	    }else if(window.XMLHttpRequest){
	       xmlhttp_request1=new XMLHttpRequest();
	     } else {
	       return;
	     }
     xmlhttp_request1.open("GET", "/smsb/webapp/smsb/grsds/grsdsTzflbAction2014.do?actionType=Query&jsjdm="+query_jsjdm,true);
	     xmlhttp_request1.onreadystatechange =function zdyprocessAjaxResponse()
	     {
	    	 if (xmlhttp_request1.readyState == 4) {
         	if (xmlhttp_request1.status == 200) { 
         		try{
         			var resp = eval("("+xmlhttp_request1.responseText+")");
         			var msg = resp.msg;
         			var date = resp.datels;
         			sumPage = resp.sumPage;
         			currentPage = 1;
  					if( msg)
  					{
  						alert(msg);
  					}else{
  						//ɾ��ԭ����
  						var table=document.getElementById("tzflist");
  						 var len=table.rows.length;
  						 for(var i=1;i<len;i++){
  							table.deleteRow(1);
  					 	}
  						//���������
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

function getPageInf(obj){
	if(obj=='first'){
		if(currentPage==1){
			alert("���Ѿ��ǵ�һҳ");
			return false;
		}else{
			currentPage=1;
		}
	}
	if(obj=='previous'){
		if(currentPage==1){
			alert("���Ѿ��ǵ�һҳ");
			return false;
		}else{
			currentPage--;
		}
	}
	if(obj=='next'){
		if(currentPage==sumPage){
			alert("���Ѿ������һҳ");
			return false;
		}else{
			currentPage++;
		}
	}
	if(obj=='last'){
		if(currentPage==sumPage){
			alert("���Ѿ������һҳ");
			return false;
		}else{
			currentPage=sumPage;
		}
	}
	doPage();
}



//��ҳ��ѯ
function doPage(){
	
	var xmlhttp_request1;
    if(window.ActiveXObject){
	       xmlhttp_request1=new ActiveXObject("Microsoft.XMLHTTP");
	    }else if(window.XMLHttpRequest){
	       xmlhttp_request1=new XMLHttpRequest();
	     } else {
	       return;
	     }
     xmlhttp_request1.open("GET", "/smsb/webapp/smsb/grsds/grsdsTzflbAction2014.do?actionType=Page&currentPage="+currentPage,true);
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
  						//ɾ��ԭ����
  						var table=document.getElementById("tzflist");
  						 var len=table.rows.length;
  						 for(var i=1;i<len;i++){
  							table.deleteRow(1);
  					 	}
  						//���������
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
	var msg="<bean:write name='grsdstzflbForm2014' property='msg'/>";
	if(msg){
		alert(msg);
	}
}
</script>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="load()">
		<jsp:include page="../../include/header.jsp" flush="true" >
    		<jsp:param name="name" value="��������Ӫ����˰��˰�����б�" />
			<jsp:param name="help_url" value="help/wssb/qyzhsb/gzsx/gzsx-000.htm"/>
    	</jsp:include>
<html:form action="/webapp/smsb/grsds/grsdsTzflbAction2014.do">
<table width="80%" height="50%" border="0" align="center" cellpadding="0" cellspacing="0" >
		
	<tr>
		<td class="1-td1" >��������Ӫ����˰��˰�����б�</td>
	</tr>
	<tr>
		<td class="1-td2" valign="top">
			<br/>
				<div style="overflow: auto; width:98%; margin:0">
				<table id="tab_jdjdm" cellSpacing="0" cellPadding="0" width="100%" border="0"  align = "center">
					<tr>
						<td class="2-td2-t-center" width="50%"><STRONG>��������룺</STRONG></td>
						<td class="2-td2-t-right" width="50%"><html:text styleId="jsjdm" maxlength="8" onchange="onLeaveJsjdm()" name="grsdstzflbForm2014"  property="jsjdm" />
						</td>
					</tr>
				</table>
				</div>
			<br/>
			<div align="left"><FONT color="#000000" size="1"><STRONG>&nbsp;&nbsp;��Դ��˰��Ǽǵ�Ͷ������Ϣ��</STRONG></FONT></div>
			
			<div style="overflow: auto; width:98%; height:180px;padding:6px">			
		
				<table id="tzflist" cellSpacing="0" cellPadding="0" width="100%" border="0"  align = "center">
					<tr>
						<td class="2-td1-left" width="20%">����</td>
						<td class="2-td1-center" width="20%">���֤������</td>
						<td class="2-td1-right" width="20%">���֤������</td>
						<td class="2-td1-right" width="20%">�������(%)</td>
						<td class="2-td1-right" width="10%">��д״̬</td>
						<td class="2-td1-right" width="10%">����</td>
					</tr>
					<logic:iterate id="tzf" name="grsdstzflbForm2014" property="grList">
       				<tr>
       				
       					<td class="2-td2-left">&nbsp;<a style="cursor:hand;text-decoration:underline" href="/smsb/webapp/smsb/grsds/grsdsJbxxAction2014.do?actionType=Show&qyxx_jsjdm=<bean:write name='grsdstzflbForm2014' property='jsjdm'/>&grxx_sfzjlx=<bean:write name='tzf' property='gr_sfzjlx'/>&grxx_sfzjhm=<bean:write name='tzf' property='gr_sfzjhm'/>"><bean:write name="tzf" property="gr_tzzxm"/></a></td>
       					<td class="2-td2-center">
							<html:select name="tzf" property="gr_sfzjlx" style="width:90%" disabled="true">
								<html:option value=""></html:option>
								<bean:define id="zjlxList" name="grsdstzflbForm2014" property="sfzjlxList"/>
								<html:options collection="zjlxList" property="value" labelProperty="text"/>
							</html:select>
						</td>
       					<td class="2-td2-right"><a style="cursor:hand;text-decoration:underline" href="/smsb/webapp/smsb/grsds/grsdsJbxxAction2014.do?actionType=Show&qyxx_jsjdm=<bean:write name='grsdstzflbForm2014' property='jsjdm'/>&grxx_sfzjlx=<bean:write name='tzf' property='gr_sfzjlx'/>&grxx_sfzjhm=<bean:write name='tzf' property='gr_sfzjhm'/>"><bean:write name="tzf" property="gr_sfzjhm"/></a></td>
       					<td class="2-td2-right">&nbsp;<bean:write name="tzf" property="gr_fpbl"/></td>
       					<td class="2-td2-right">
       						<bean:define id="gr_txzt" name="tzf" property="gr_txzt"/>
       					<%if("1".equals(gr_txzt)){ %>
       						����д���
       					<% }else{%>
       						δ��д���
       					<%} %>
       					</td>
       					<td class="2-td2-right">
							&nbsp;<a style="cursor:hand;text-decoration:underline" tabIndex="-1" onclick="javascript:return deleteMx(this);return false;">ɾ��</a>
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
		<a style="cursor:hand"  tabIndex="-1" onClick="getPageInf('previous');return false;"><img name="Shangyiye" value="��һҳ" alt="��һҳ" onMouseOver="MM_swapImage('Shangyiye','','<%=static_contextpath%>/images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/shangyiye1.jpg" width="79" height="22" id="Shangyiye"></a>&nbsp;
		<a style="cursor:hand"  tabIndex="-1" onClick="getPageInf('next');return false;"><img name="Xiayiye" value="��һҳ" alt="��һҳ" onMouseOver="MM_swapImage('Xiayiye','','<%=static_contextpath%>/images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/xaiyiye1.jpg" width="79" height="22" id="Xiayiye"></a>&nbsp;	
		<a style="cursor:hand"  tabIndex="-1" onClick="getPageInf('first');return false;"><img  name="Diyiye" value="��һҳ" alt="��һҳ" onMouseOver="MM_swapImage('Diyiye','','<%=static_contextpath%>/images/diyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/diyiye1.jpg"  width="79" height="22" id="Diyiye"></a>&nbsp;	
		<a style="cursor:hand"  tabIndex="-1" onClick="getPageInf('last');return false;"><img  name="Zuimoye" value="��ĩҳ" alt="��ĩҳ" onMouseOver="MM_swapImage('Zuimoye','','<%=static_contextpath%>/images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/zuimoye1.jpg"  width="79" height="22" id="Zuimoye"></a> 
	</span>
</div>
<div  align="center">
	<span>
		<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return addMx();return false;"><img name="add" value="����" alt="����" onMouseOver="MM_swapImage('zengjia','','<%=static_contextpath%>/images/tianjia2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/tianjia1.jpg" width="79" height="22" id="add"></a>&nbsp;&nbsp;
		<a style="cursor:hand"  tabIndex="-1" onClick="tuichu();return false;"><img  name="return" value="�˳�" alt="�˳�" onMouseOver="MM_swapImage('return','','<%=static_contextpath%>/images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/tuichu1.jpg"  width="79" height="22" id="return"></a> 
	</span>			
</div>
<br/><br/>
</html:form>
<%@ include file="../../include/footernew.jsp"%>
</body>
</html>