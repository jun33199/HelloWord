<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@page import="com.ttsoft.bjtax.shenbao.taglib.SbzlTag"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.FriendHelper"%>
<%@page import="com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.Grsds2014Constant"%>
<html>
<head>
<title>��������Ӫ����˰��˰�����б�</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="gojs/XmlBuild.js"></script>
<script language=JavaScript>

var strXSLTVersion = '<%=request.getAttribute(Grsds2014Constant.XSLT_GRSDS_VERSION)==null?"":request.getAttribute(Grsds2014Constant.XSLT_GRSDS_VERSION)%>';
var strXml = '<%=request.getAttribute(Grsds2014Constant.XSLT_GRSDS_DATE)==null?"":request.getAttribute(Grsds2014Constant.XSLT_GRSDS_DATE)%>';
var sfzjlxdms;
var sumPage;
var currentPage;

function doSubmit(actionType){
	var form=document.forms[0];
	if(actionType=='Return'){				//�˳�
		document.forms[0].action="quit.do";
		document.forms[0].submit();
		return true;
	}else if(actionType=='Fanhui'){	
		document.forms[0].action="gzsx.do?actionType=Show";
		document.forms[0].submit();
		return true;
	}else{
		document.forms[0].actionType.value="Return";
		document.forms[0].submit();
		return true;
	}
}
//����xml
function parseXmlOnLoad()
{
	//alert("parseXmlOnLoad");
	var urlXSL="/XSLTWEB/model/040000/XSLT/" +strXSLTVersion+".xsl";	//grsdsjbxx2014
    if (strXml != "")
    {
        if (! parseXml(strXml,urlXSL,"result"))
            return false;
	xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
	xmlDoc.async = false;
	xmlDoc.loadXML(strXml);
    }
	
    sfzjlxdms= xmlDoc.selectNodes("/taxdoc/sfzjlxList/sfzjlx");
	
	setInput(xmlDoc);
	
	<%
		String reason=request.getParameter("reason");
		if(reason!=null&&!"".equals(reason)){
	%>
		alert("<%=reason%>");
	<%}%>
	
    return true;
}
function setInput(xmlDoc)
{
	
	var sumPageVar = xmlDoc.selectSingleNode("/taxdoc/sumPage").text;
	if(isNaN(sumPageVar)||sumPageVar=="")
	{
		sumPage= 1;
	}else
	{
		sumPage= parseInt(sumPageVar)
	}
	currentPage = xmlDoc.selectSingleNode("/taxdoc/currentPage").text;
	var grvoList = xmlDoc.selectNodes("/taxdoc/GrList/grvo");
	var jsjdm = xmlDoc.selectSingleNode("/taxdoc/jsjdm").text;
	var body = document.getElementById("infbody");
	var rowCount = body.rows.length;
	
	//var C = new Array();
	var num = grvoList.length;
	for(var i=0;i<num;i++)
	{
		var newRow = body.insertRow(rowCount++);	//����һ��
		
		C0 = newRow.insertCell(0);
		C0.setAttribute("className","2-td2-left");
		C0.innerHTML="&nbsp;<a style='cursor:hand;text-decoration:underline'  tabIndex='-1' href='grsdsJbxxb2014.dc?actionType=Show&query_jsjdm="+jsjdm+"&query_sfzjlx="+grvoList[i].selectSingleNode("gr_sfzjlx").text+"&query_sfzjhm="+grvoList[i].selectSingleNode("gr_sfzjhm").text+"'>"+grvoList[i].selectSingleNode("gr_zrrxm").text+"</a>";	//Ͷ��������
		
		C1 = newRow.insertCell(1);
		C1.setAttribute("className","2-td2-left");
		C1.innerHTML="<select name='tzf_zfzjlxdm' style='width:90%' disabled='true'></select>";
		var sfzjlxdm = grvoList[i].selectSingleNode("gr_sfzjlx").text;	//���֤������
		addSfzjlxdmsSelect(C1.childNodes[0]);
		C1.childNodes[0].value=sfzjlxdm;
		
		C2 = newRow.insertCell(2);
		C2.setAttribute("className","2-td2-left");
		C2.innerHTML="&nbsp;<a style='cursor:hand;text-decoration:underline'  tabIndex='-1' href='grsdsJbxxb2014.dc?actionType=Show&query_jsjdm="+jsjdm+"&query_sfzjlx="+grvoList[i].selectSingleNode("gr_sfzjlx").text+"&query_sfzjhm="+grvoList[i].selectSingleNode("gr_sfzjhm").text+"'>"+grvoList[i].selectSingleNode("gr_sfzjhm").text+"</a>";	//���֤������
		
		C3 = newRow.insertCell(3);
		C3.setAttribute("className","2-td2-left");
		C3.innerHTML=grvoList[i].selectSingleNode("gr_fpbl").text;	//�������
		
		C4 = newRow.insertCell(4);
		C4.setAttribute("className","2-td2-left");
		var txzt = grvoList[i].selectSingleNode("gr_txzt").text;//��д״̬
		if(txzt==0){
			C4.innerHTML="δ��д���";
		}else{
			C4.innerHTML="����д���";
		}
		
		C5 = newRow.insertCell(5);
		C5.setAttribute("className","2-td2-center");
		C5.innerHTML="&nbsp;<a style='cursor:hand;text-decoration:underline'  tabIndex='-1' onclick='javascript:return deleteMx(this);return false;'>ɾ��</a>";	//ɾ��

	}
}


	
	
	//ɾ��
	function deleteMx(obj){	
		
		var td_obj = obj.parentNode;
		var tr_obj = td_obj.parentNode;
		
		var jsjdm = document.getElementById("jsjdm").value;
		var gr_sfzjlx = tr_obj.childNodes[1].childNodes[0].value;	//���֤������
		var gr_sfzjhm = tr_obj.childNodes[2].childNodes[0].innerText;	//���֤������
		
		//ajax
		var xmlhttp_request1;
	    if(window.ActiveXObject){
  	       xmlhttp_request1=new ActiveXObject("Microsoft.XMLHTTP");
  	    }else if(window.XMLHttpRequest){
  	       xmlhttp_request1=new XMLHttpRequest();
  	     } else {
  	       return;
  	     }
	     xmlhttp_request1.open("GET", "/shenbao/grsdsSb2014.dc?actionType=Delete&gr_sfzjlx="+gr_sfzjlx+"&gr_sfzjhm="+gr_sfzjhm+"&jsjdm="+jsjdm,true);
	     xmlhttp_request1.send(null);
 	     xmlhttp_request1.onreadystatechange =function zdyprocessAjaxResponse()
 	     {
 	    	 if (xmlhttp_request1.readyState == 4) {
	         	if (xmlhttp_request1.status == 200) { 
	         		var xmlDOM1 = xmlhttp_request1.responseXML;
	         		
	         		var result = xmlDOM1.selectSingleNode("/re/result").text;
	  				if("SUCCESS"!=result)
	  				{
	  					alert("ɾ��Ͷ����ʧ�ܣ��������Ա��ϵ��");
	  				}else{
	  					alert("ɾ���ɹ���");
	  					//sumPage = xmlDOM1.selectSingleNode("/re/sumPage").text;
	  					
	  					var sumPageVar = xmlDOM1.selectSingleNode("/re/sumPage").text;
	  					if(isNaN(sumPageVar)||sumPageVar=="")
	  					{
	  						sumPage= 1;
	  					}else
	  					{
	  						sumPage= parseInt(sumPageVar)
	  					}
	  					
	  					
	  					var tbody=tr_obj.parentNode;
	  					tbody.removeChild(tr_obj);
	  				}
	         		
	         	}
 	    	 }
 	     };
	}
	
	//����һ��
	function addMx(){
		//alert("����");
		var body = document.getElementById("infbody");
		var rowCount = body.rows.length;
		var newRow = body.insertRow(rowCount++);	//����һ��
		
		C0 = newRow.insertCell(0);
		C0.setAttribute("className","2-td2-left");
		C0.innerHTML="<input type='text' name='tzf_name' style='width:90%'>";
		
		C1 = newRow.insertCell(1);
		C1.setAttribute("className","2-td2-left");
		C1.innerHTML="<select name='tzf_zfzjlxdm' style='width:90%'></select>";
		addSfzjlxdmsSelect(C1.childNodes[0]);
		
		C2 = newRow.insertCell(2);
		C2.setAttribute("className","2-td2-left");
		C2.innerHTML="<input type='text' name='tzf_zfzjhm' style='width:90%'>";
		
		C3 = newRow.insertCell(3);
		C3.setAttribute("className","2-td2-left");
		C3.innerHTML="<input type='text' name='tzf_fpbl' style='width:90%'>";
		
		C4 = newRow.insertCell(4);
		C4.setAttribute("className","2-td2-left");
		C4.innerHTML="δ��д���";
		
		C5 = newRow.insertCell(5);
		C5.setAttribute("className","2-td2-center");
		C5.innerHTML="<a style='cursor:hand;text-decoration:underline'  tabIndex='-1' onclick='javascript:return addSave(this);return false;'>ȷ�����</a>";
	
	}
	
	//Ϊselect�������֤�����ʹ����
	function addSfzjlxdmsSelect(obj)
	{
		for(var j=0;j<sfzjlxdms.length;j++){
			oOption = document.createElement("option");
        	oOption.text= sfzjlxdms[j].selectSingleNode("text").text;
        	oOption.value= sfzjlxdms[j].selectSingleNode("value").text;
        	obj.add(oOption);
		}
	}
	
	//�������ӵ���Ϣ����
	function addSave(obj){
		
		var td_obj = obj.parentNode;
		var tr_obj = td_obj.parentNode;
		
		var jsjdm = document.getElementById("jsjdm").value;
		var gr_tzzxm = tr_obj.childNodes[0].childNodes[0].value;	//Ͷ��������
		var gr_sfzjlx = tr_obj.childNodes[1].childNodes[0].value;	//���֤������
		var gr_sfzjhm = tr_obj.childNodes[2].childNodes[0].value;	//���֤������
		var gr_fpbl = tr_obj.childNodes[3].childNodes[0].value;	    //�������
		
		if(isNaN(gr_fpbl)){
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
	     xmlhttp_request1.open("GET", "/shenbao/grsdsSb2014.dc?actionType=AddSave&gr_tzzxm="+gr_tzzxm+"&gr_sfzjlx="+gr_sfzjlx+"&gr_sfzjhm="+gr_sfzjhm+"&gr_fpbl="+gr_fpbl+"&jsjdm="+jsjdm,true);
	     
 	     xmlhttp_request1.onreadystatechange =function zdyprocessAjaxResponse()
 	     {
 	    	 if (xmlhttp_request1.readyState == 4) {
	         	if (xmlhttp_request1.status == 200) { 
	         		var xmlDOM1 = xmlhttp_request1.responseXML;
	         		
	         		var result = xmlDOM1.selectSingleNode("/re/result").text;
	  				if("SUCCESS"!=result)
	  				{
	  					alert("���Ͷ����ʧ�ܣ�������ȷ��Ͷ������Ϣ��");
	  				}else{
	  					alert("��ӳɹ���");
	  					//sumPage = xmlDOM1.selectSingleNode("/re/sumPage").text;
	  					
	  					var sumPageVar = xmlDOM1.selectSingleNode("/re/sumPage").text;
	  					if(isNaN(sumPageVar)||sumPageVar=="")
	  					{
	  						sumPage= 1;
	  					}else
	  					{
	  						sumPage= parseInt(sumPageVar)
	  					}
	  					
	  					changeAdd(tr_obj);
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
		
		tr_obj.childNodes[0].innerHTML="&nbsp;<a style='cursor:hand;text-decoration:underline'  tabIndex='-1' href='grsdsJbxxb2014.dc?actionType=Show&query_jsjdm="+jsjdm+"&query_sfzjlx="+sfzjlx+"&query_sfzjhm="+sfzjhm+"'>"+name+"</a>";	//Ͷ��������
		tr_obj.childNodes[1].childNodes[0].disabled=true;
		tr_obj.childNodes[2].innerHTML="&nbsp;<a style='cursor:hand;text-decoration:underline'  tabIndex='-1' href='grsdsJbxxb2014.dc?actionType=Show&query_jsjdm="+jsjdm+"&query_sfzjlx="+sfzjlx+"&query_sfzjhm="+sfzjhm+"'>"+sfzjhm+"</a>";
		tr_obj.childNodes[3].innerHTML=fpbl;
		tr_obj.childNodes[5].innerHTML="&nbsp;<a style='cursor:hand;text-decoration:underline'  tabIndex='-1' onclick='javascript:return deleteMx(this);return false;'>ɾ��</a>"
	}
	
	function getPageInf(obj){
		if(obj=='first'){
			if(currentPage==1||parseInt(currentPage)==1){
				alert("���Ѿ��ǵ�һҳ");
				return false;
			}else{
				currentPage=1;
			}
		}
		if(obj=='previous'){
			if(currentPage==1||parseInt(currentPage)==1){
				alert("���Ѿ��ǵ�һҳ");
				return false;
			}else{
				currentPage--;
			}
		}
		if(obj=='next'){
			if(currentPage>=sumPage||parseInt(currentPage)>=sumPage){
				alert("���Ѿ������һҳ");
				return false;
			}else{
				currentPage++;
			}
		}
		if(obj=='last'){
			if(currentPage>=sumPage||parseInt(currentPage)>=sumPage){
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
	     xmlhttp_request1.open("GET", "/shenbao/grsdsSb2014.dc?actionType=Page&currentPage="+parseInt(currentPage),true);
		     xmlhttp_request1.onreadystatechange =function zdyprocessAjaxResponse()
		     {
		    	 if (xmlhttp_request1.readyState == 4) {
	         	 if (xmlhttp_request1.status == 200) {      		
	         		try{
	         			var text = xmlhttp_request1.responseText;
	         			
	         			var xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
	         			xmlDoc.async = false;
	         			xmlDoc.loadXML(text);
	         			
		  				var body = document.getElementById("infbody");
		  				
		  				//ɾ��ԭ��¼
		         		var len=body.rows.length;
 						 for(var i=0;i<len;i++){
 							body.deleteRow(0);
 					 	}
 						 //����¼�¼
 						 setInput(xmlDoc);
		  				
	         		}catch(e){
	         			return;
	         		}
	         	}
		    	}
		     };
	     xmlhttp_request1.send(null);
	}
	
</script>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  onLoad="return parseXmlOnLoad();">
	 
<form name="grsdsjbxx" action="grsdsSb2014.dc">
<input name="actionType" type="hidden" id="actionType" value="Show">
<table width="97%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
     <td align="left" valign="top" >
    	<jsp:include page="../../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="��������Ӫ����˰��˰�����б�" />
		<jsp:param name="help_url" value="help/wssb/qyzhsb/gzsx/gzsx-000.htm"/>
    	</jsp:include>
	 </td>
    </tr>
    	
    <tr><td><html:errors/></td></tr>
    
 	<!-- xsl��ʽ�� -->
	<tr><td><br/><div id="result" align="center" ></div></td></tr>
    
    <!-- ��ť -->
     <tr>
	  <td align="center" valign="top">
	  		
		 	<div  align="center">
		  		<table width="80%" align="center">
		    	<TR >
				<TD align="right">
					<a style="cursor:hand"  tabIndex="-1" onClick="getPageInf('previous');return false;"><img name="Shangyiye" value="��һҳ" alt="��һҳ" onMouseOver="MM_swapImage('Shangyiye','','<%=static_contextpath%>/images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/shangyiye1.jpg" width="79" height="22" id="Shangyiye"></a>&nbsp;
					<a style="cursor:hand"  tabIndex="-1" onClick="getPageInf('next');return false;"><img name="Xiayiye" value="��һҳ" alt="��һҳ" onMouseOver="MM_swapImage('Xiayiye','','<%=static_contextpath%>/images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/xaiyiye1.jpg" width="79" height="22" id="Xiayiye"></a>&nbsp;	
					<a style="cursor:hand"  tabIndex="-1" onClick="getPageInf('first');return false;"><img  name="Diyiye" value="��һҳ" alt="��һҳ" onMouseOver="MM_swapImage('Diyiye','','<%=static_contextpath%>/images/diyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/diyiye1.jpg"  width="79" height="22" id="Diyiye"></a>&nbsp;	
					<a style="cursor:hand"  tabIndex="-1" onClick="getPageInf('last');return false;"><img  name="Zuimoye" value="��ĩҳ" alt="��ĩҳ" onMouseOver="MM_swapImage('Zuimoye','','<%=static_contextpath%>/images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/zuimoye1.jpg"  width="79" height="22" id="Zuimoye"></a> 
				</TD>
				</TR>
		    	</table>
			</div>
	   </td>
	</tr>
    <tr>
	  <td align="center" valign="bottom">
	  		<br/>
		 	<div  align="center">
		  		<table>
		    	<TR >
				<TD>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return addMx();return false;"><img name="add" value="����" alt="����" onMouseOver="MM_swapImage('zengjia','','<%=static_contextpath%>/images/tianjia2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/tianjia1.jpg" width="79" height="22" id="add"></a>&nbsp;&nbsp;
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Fanhui');return false;"><img name="fanhui" value="����" alt="����" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>/images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/fanhui1.jpg" width="79" height="22" id="fanhui"></a>&nbsp;&nbsp;		
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Return');return false;"><img  name="tuichu" value="�˳�" alt="�˳�" onMouseOver="MM_swapImage('tuichu','','<%=static_contextpath%>/images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/tuichu1.jpg"  width="79" height="22" id="tuichu"></a> 
				</TD>
				</TR>
		    	</table>
			</div>
	   </td>
	</tr>
    
  	<!-- ע������ -->
   	<tr>
	   <td class="1-td2-center" valign="bottom"><br/>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
				 <tr>
				    <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				    <td width="20%" align="center" class="black9"><strong><font color="#0000FF">ע������</font></strong></td>
				     <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				  </tr>
				</table>
				<table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
				   <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
				      <td height="47"  >
				        <p style ="color:red">
				            &nbsp;&nbsp;&nbsp;&nbsp;���������˱��Ǹ��ݡ��л����񹲺͹���������˰��������ʵʩ�����͹�����ط��ɷ���涨��д�ģ�����ʵ�ġ������ġ��ɿ��ġ�<br>
				         </p>
				       </td>
				    </tr>
				</table>
	  			<br>
	   </td>
	</tr>
	
	<!-- �� -->
	<tr>
  	<td valign="bottom" align="center">
		<%@ include file="../../include/bottom.jsp" %>
  	</td>
	</tr>
 </table>
 </form>
</body>
</html>