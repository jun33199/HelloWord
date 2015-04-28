<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjqysdsbgb.web.KjqysdsbgbForm" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.ttsoft.bjtax.smsb.model.kjqysds.BAHTXX" %>


<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>中华人民共和国扣缴企业所得税报告表</title>
<link href="../../../css/text.css" rel="stylesheet" type="text/css">
<link href="../../../css/top-box.css" rel="stylesheet" type="text/css"> 
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../../../js/treatImage.js"></script>
<script language="JavaScript" src="../../../js/smsb_common.js"></script> 
<script language="JavaScript" src="../../../js/DTable.js"></script> 
<script language="JavaScript" src="../../../js/reader.js"></script>
<script language="JavaScript" src="../../../js/InputSelect.js"></script>
<script language="JavaScript" src="../../../js/function.js"></script>


</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
<!-- <table  width="100%" height="61%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
   <td colspan=2> -->
<%@ include file="../../include/header.jsp"%>
<%
//获取KjqysdsbgbForm
KjqysdsbgbForm form = (KjqysdsbgbForm) request.getAttribute("kjqysdsbgbForm");
//总条数
int totalCount = form.getZts() == null||form.getZts().equals("") ? 0 : Integer.parseInt(form.getZts());
//总页数
int totalPage = form.getZys() == null ||form.getZys().equals("")? 0 : Integer.parseInt(form.getZys());
//当前页
int currentPage = form.getDqy()== null||form.getDqy().equals("") ? 0 : Integer.parseInt(form.getDqy());
System.out.println("---页面打印信息---" + "\n总条数 = " + totalCount + "\n总页数 = " + totalPage + "\n当前页 = " + currentPage);

List list =  form.getQysdsbgbList();
if(list !=null )
{
	System.out.println("List_size = " + list.size());
}
%>


<html:form action="/webapp/smsb/qysds/kjqysds/kjqysdsbgbAction.do" method="POST" focus="jsjdm">

<html:hidden property="actionType" value="Show"/>
<html:hidden property="dqy"/>
<html:hidden property="badjxh"/>
<html:hidden property="bgbxh"/>
<html:hidden property="oldzt"/>

      <table width="96%" align="center" cellspacing="0" class="table-99" onkeydown="enter()">
                <tr> 
                  <td class="1-td1"  colspan="7">扣缴企业所得税报告表查询</td>
                </tr>
                <tr> 
                  <td class="1-td2"  colspan="7"> 
                    <br> <table width="94%" border="0" cellpadding="0" cellspacing="0" >
                      <tr class="black9"> 
                        <td width="17%" nowrap class="2-td2-t-left">计算机代码</td>
                        <td width="35%" nowrap class="2-td2-t-left">
						<html:text tabindex='1' property="jsjdm"   size="20"/>
						</td>
                        <td width="24%" nowrap class="2-td2-t-left">当前状态</td>
                        <td width="24%" nowrap class="2-td2-t-center">&nbsp;
						<html:select tabindex='2' property="dqzt" onchange="ButtonUpdate()">
							<html:option value="">===请选择状态===</html:option>
							<html:option value="01">已备案未填写报告表</html:option>
							<html:option value="02">已填写报告表未开具缴款书</html:option>
							<html:option value="03">已开具缴款书</html:option>
						</html:select>
						</td>
                      </tr>
                    </table>
                    <br> 
            <table border="0" width="100%">
              <tr align="center">
              <td width="25%">&nbsp; </td>
                
                <td align="center"><span id="showI1"><input name="I1" type="image" accesskey="q" value="查询"  onclick='jsjdmQuery();return false;' onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)" 
						onMouseOut="MM_swapImgRestore()" alt="查询" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" 
						id="chaxun"></span></td>
				<td align="center"><span id="showI2" style="display:none"><input name="I2" type="image" accesskey="t"
						onClick="doSaveBgb();return false;"
						onMouseOver="MM_swapImage('tianxiebgb','','<%=static_contextpath%>images/b-scbb2.jpg',1)"
						alt="填写报告表" onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/b-scbb1.jpg" 
						width="79" height="22" border="0" id='tianxiebgb'></span></td>
				<td align="center"><span id="showI3" style="display:none"><input name="I3" type="image" accesskey="x"
						onClick="doUpdateBgb();return false;"
						onMouseOver="MM_swapImage('xiugai','','<%=static_contextpath%>images/xiugai2.jpg',1)"
						alt="修改报告表" onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/xiugai1.jpg"
						width="79" height="22" border="0" id='xiugai'></span></td>
				 <td align="center"><span id="showI4" style="display:none"><input name="I4" type="image" accesskey="s"
						onClick="doDelete();return false;"
						onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/shanchu2.jpg',1)"
						alt="删除" onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/shanchu1.jpg"
						width="79" height="22" border="0" id='shanchu'></span></td>
				<td align="center"><span id="showI5" style="display:none"><input name="I5" type="image" accesskey="j"
						onClick="doShenchenjks();return false;"
						onMouseOver="MM_swapImage('shengchenjks','','<%=static_contextpath%>images/scjks2.jpg',1)"
						alt="生成缴款书" onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/scjks1.jpg"
						width="90" height="22" border="0" id='shengchenjks'></span></td>   
				<td align="center"><span id="showI6" style="display:none"><input name="I6" type="image" accesskey="j"
						onClick="doShenchenjks();return false;"
						onMouseOver="MM_swapImage('jkswh','','<%=static_contextpath%>images/whjks-w2.jpg',1)"
						alt="缴款书维护" onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/whjks-w1.jpg"
						width="90" height="22" border="0" id='jkswh'></span></td>   
               <td align="center"><span id="showI7"><input name="I7" type="image" accesskey="c" value="退出"  onClick='tuichu();return false;' border=0 onMouseOver="MM_swapImage('tuichu','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="退出" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" id="tuichu"></span></td>
              
                <td width="25%">&nbsp;</td>
              </tr>  
            </table>		
             
           <br>
            <table id="kjqysdsbgblistTable" border="1" cellpadding="0" cellspacing="0" class="table-99" height="1" width="99%">
              <tr>
              	<td width="5%"  class="2-td1-left" height="1">&nbsp;</td>
                <td width="20%" class="2-td1-left" height="1">备案登记号</td>
                <td width="20%" class="2-td1-left" height="1">合同或协议名称</td>
                <td width="20%" class="2-td1-left" height="1">合同编号</td>
                <td width="16%" class="2-td1-center" height="1">合同金额</td>
              </tr>
              <%
              	if(list!=null){
              		for(int i=0;i<list.size();i++){
              		Map map=(Map)list.get(i);
              		String badjxh=(String)map.get("badjxh");
              		String bgbxh=(String)map.get("bgbxh");
              		BAHTXX bahtxx=(BAHTXX)map.get("bahtxx");
              		String str=badjxh+","+bgbxh;
              	%>
              	<tr>
              	<td width="5%"  class="2-td2-left" height="1"><input type="radio" name="id" value="<%=str%>"></td>
                <td width="20%" class="2-td2-left" height="1"><%=badjxh %></td>
                <td width="20%" class="2-td2-left" height="1"><%=bahtxx.getHtmc() %></td>
                <td width="20%" class="2-td2-left" height="1"><%=bahtxx.getHtbh() %></td>
                <td width="16%" class="2-td2-center" height="1"><%=bahtxx.getHtje()%></td>
              </tr>   	
              	
              	<%
              		}
              	}
              %>
              </table>
              <table border="0" cellpadding="0" cellspacing="0" class="table-99" width="99%">
              <tr> 
                <td align="center" class="2-td2-left">&nbsp;&nbsp;记录数：<%=totalCount%>&nbsp;&nbsp;&nbsp;
                          当前页/总页数：<%=currentPage%>/<%=totalPage%> &nbsp;</td>
                <td align="center" class="2-td2-right">
					<div align="right">
						<img onclick="doFy(1)" onMouseOver="MM_swapImage('dy1','','<%=static_contextpath%>/images/diyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="第一页" id="dy1" src="<%=static_contextpath%>/images/diyiye1.jpg" width="79" height="22" style="cursor:hand">
                        <img onclick="doFy(<%=currentPage-1%>)" onMouseOver="MM_swapImage('sy1','','<%=static_contextpath%>/images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="上一页" id="sy1" src="<%=static_contextpath%>/images/shangyiye1.jpg" width="79" height="22" style="cursor:hand">
                        <img onclick="doFy(<%=currentPage+1%>)" onMouseOver="MM_swapImage('xy1','','<%=static_contextpath%>/images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="下一页" id="xy1" src="<%=static_contextpath%>/images/xaiyiye1.jpg" width="79" height="22" style="cursor:hand">
                        <img onclick="doFy(<%=totalPage%>)" onMouseOver="MM_swapImage('zm1','','<%=static_contextpath%>/images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="最末页" id="zm1" src="<%=static_contextpath%>/images/zuimoye1.jpg" width="79" height="22" style="cursor:hand">
                    </div>
				</td>
              </tr>
           </table>
      <br>
	     </td>
		</tr>
	  </table>
</html:form>


<%@ include file="../../include/footer.jsp"%>
<script language="javascript"> 
//翻页操作
function doFy(pageNo)
{
	//alert("pageNo = " + pageNo);
	var currentPage = <%=currentPage%>;
	var totalPage = <%=totalPage%>;

	//翻页查询
    //直接输入跳转页号的查询：进行输入页号的校验：
	if(totalPage > 0)
	{
		if(currentPage == 1 && pageNo <=1)
		{
			alert("当前已经是第一页，无法进行翻页。");
			return false;
		}

		if(currentPage == totalPage && pageNo >= totalPage)
		{
			alert("当前已经是最末页，无法进行翻页。");
			return false;
		}
	}
	else
	{
		return;
	}


	if(document.forms[0].jsjdm.value==""){
		alert("请输入计算机代码!");
		document.forms[0].jsjdm.focus();
		return false;
	}
	document.forms[0].dqy.value=pageNo;
	doSubmitForm('Query1');
}
function jsjdmQuery()
	{
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			return false;
		}
		if(document.forms[0].dqzt.value==""){
			alert("请选择当前状态!");
			document.forms[0].dqzt.focus();
			return false;
		}
		doSubmitForm('Query1');
	}
function fnOnLoad(){
	if(document.forms[0].dqzt.value=="03"){
		document.getElementById("showI2").style.display="block";
		document.getElementById("showI3").style.display="none";
		document.getElementById("showI4").style.display="none";
		document.getElementById("showI5").style.display="none";
		document.getElementById("showI6").style.display="block";
	}
	else if(document.forms[0].dqzt.value=="02"){
		document.getElementById("showI2").style.display="block";
		document.getElementById("showI3").style.display="block";
		document.getElementById("showI4").style.display="block";
		document.getElementById("showI5").style.display="block";
		document.getElementById("showI6").style.display="none";
	}
	else if(document.forms[0].dqzt.value=="01"){
		document.getElementById("showI2").style.display="block";
		document.getElementById("showI3").style.display="none";
		document.getElementById("showI4").style.display="none";
		document.getElementById("showI5").style.display="none";
		document.getElementById("showI6").style.display="none";
	}
	else{
		document.getElementById("showI2").style.display="none";
		document.getElementById("showI3").style.display="none";
		document.getElementById("showI4").style.display="none";
		document.getElementById("showI5").style.display="none";
		document.getElementById("showI6").style.display="none";
	}
	<%
	String otherMsg = (String)request.getAttribute(CodeConstant.SMSB_OTHER_SUCCESS);	

	if(otherMsg==null)
		otherMsg="";
	%>
	if("<%=otherMsg%>"!=null && !"<%=otherMsg%>"==""){
		alert("<%=otherMsg%>");
	}
}
function doSaveBgb(){
	if(!dqztEqual()){
		return false;
	}
	if(document.forms[0].id){
		var id=document.forms[0].id;  
		var len=0;
		var flag=false;//用于存储有没有选择的变量,false为有选，true没选 
		if(id.length==undefined)
		{ 
			len=1; 
		} 
		else 
		{ 
			len=id.length; 
		}
		if(len==1) 
		{ 
			if(id.checked) //当单选框被选中时 
			{ 
				flag=true; 
				str=id.value.split(","); 
	        	document.forms[0].badjxh.value=str[0];
	        	document.forms[0].bgbxh.value=str[1];  
			} 
		} 
		else //另外一种情况有多个单选框时 
		{ 
			for(i=0;i<id.length;i++) //for循环 
			{ 
				if(id[i].checked==true) //当被选中时为true 
				{ 
					flag=true; 
					str=id[i].value.split(",");  
		        	document.forms[0].badjxh.value=str[0];
		        	document.forms[0].bgbxh.value=str[1]; 
		        	break;
				} 
			} 
		} 
		if(!flag) 
		{ 
			alert("对不起，你还没选择!"); 
			return false;
		}
		if(document.forms[0].dqzt.value=="02"||document.forms[0].dqzt.value=="03"){
			document.forms[0].bgbxh.value=""; 
			if(!window.confirm("补填报告表,请确认!")){
				return false;
			}
		}     
		  doSubmitForm('Query');
	}
	return;
}
function doDelete(){
	if(!dqztEqual()){
		return false;
	}
	if(document.forms[0].id){
		var id=document.forms[0].id;  
		var len=0;
		var flag=false;//用于存储有没有选择的变量,false为有选，true没选 
		if(id.length==undefined)
		{ 
			len=1; 
		} 
		else 
		{ 
			len=id.length; 
		}
		if(len==1) 
		{ 
			if(id.checked) //当单选框被选中时 
			{ 
				flag=true; 
				str=id.value.split(",");   
	        	document.forms[0].badjxh.value=str[0];
	        	document.forms[0].bgbxh.value=str[1];  
			} 
		} 
		else //另外一种情况有多个单选框时 
		{ 
			for(i=0;i<id.length;i++) //for循环 
			{ 
				if(id[i].checked==true) //当被选中时为true 
				{ 
					flag=true; 
					str=id[i].value.split(",");   
		        	document.forms[0].badjxh.value=str[0];
		        	document.forms[0].bgbxh.value=str[1]; 
		        	break;
				} 
			} 
		} 
		if(!flag) 
		{ 
			alert("对不起，你还没选择!"); 
			return false;
		}    
		doSubmitForm('Delete');
	}
	return;
	
}
function ButtonUpdate(){
	
	if(document.forms[0].dqzt.value=="03"){
		document.getElementById("showI2").style.display="block";
		document.getElementById("showI3").style.display="none";
		document.getElementById("showI4").style.display="none";
		document.getElementById("showI5").style.display="none";
		document.getElementById("showI6").style.display="block";
	}
	else if(document.forms[0].dqzt.value=="02"){
		document.getElementById("showI2").style.display="block";
		document.getElementById("showI3").style.display="block";
		document.getElementById("showI4").style.display="block";
		document.getElementById("showI5").style.display="block";
		document.getElementById("showI6").style.display="none";
	}
	else if(document.forms[0].dqzt.value=="01"){
		document.getElementById("showI2").style.display="block";
		document.getElementById("showI3").style.display="none";
		document.getElementById("showI4").style.display="none";
		document.getElementById("showI5").style.display="none";
		document.getElementById("showI6").style.display="none";
	}
	else{
		document.getElementById("showI2").style.display="none";
		document.getElementById("showI3").style.display="none";
		document.getElementById("showI4").style.display="none";
		document.getElementById("showI5").style.display="none";
		document.getElementById("showI6").style.display="none";
	}
	document.forms[0].dqy.value=0;
}

function doShenchenjks(){
	if(!dqztEqual()){
		return false;
	}
	if(document.forms[0].id){
		var id=document.forms[0].id;  
		var len=0;
		var flag=false;//用于存储有没有选择的变量,false为有选，true没选 
		if(id.length==undefined)
		{ 
			len=1; 
		} 
		else 
		{ 
			len=id.length; 
		}
		if(len==1) 
		{ 
			if(id.checked) //当单选框被选中时 
			{ 
				flag=true; 
				str=id.value.split(",");   
	        	document.forms[0].badjxh.value=str[0];
	        	document.forms[0].bgbxh.value=str[1]; 
			} 
		} 
		else //另外一种情况有多个单选框时 
		{ 
			for(i=0;i<id.length;i++) //for循环 
			{ 
				if(id[i].checked==true) //当被选中时为true 
				{ 
					flag=true; 
					str=id[i].value.split(",");   
		        	document.forms[0].badjxh.value=str[0];
		        	document.forms[0].bgbxh.value=str[1]; 
		        	break;
				} 
			} 
		} 
		if(!flag) 
		{ 
			alert("对不起，你还没选择!"); 
			return false;
		} 
		  
		document.forms[0].action="kjssjksAction.do";
		document.forms[0].actionType.value="Query";
		doSubmitForm('Query');				
	}
	return;
	
}

function doUpdateBgb(){
	if(!dqztEqual()){
		return false;
	}
	if(document.forms[0].id){
		var id=document.forms[0].id;  
		var len=0;
		var flag=false;//用于存储有没有选择的变量,false为有选，true没选 
		if(id.length==undefined)
		{ 
			len=1; 
		} 
		else 
		{ 
			len=id.length; 
		}
		if(len==1) 
		{ 
			if(id.checked) //当单选框被选中时 
			{ 
				flag=true; 
				str=id.value.split(","); 
	        	document.forms[0].badjxh.value=str[0];
	        	document.forms[0].bgbxh.value=str[1];  
			} 
		} 
		else //另外一种情况有多个单选框时 
		{ 
			for(i=0;i<id.length;i++) //for循环 
			{ 
				if(id[i].checked==true) //当被选中时为true 
				{ 
					flag=true; 
					str=id[i].value.split(",");  
		        	document.forms[0].badjxh.value=str[0];
		        	document.forms[0].bgbxh.value=str[1]; 
		        	break;
				} 
			} 
		} 
		if(!flag) 
		{ 
			alert("对不起，你还没选择!"); 
			return false;
		}     
		  doSubmitForm('Query');
	}
	return;
}
<%/*响应回车跳格*/%>
function enter(){
	if(event.keyCode==13) event.keyCode = 9;
}

function dqztEqual(){
	if(document.forms[0].oldzt.value!=document.forms[0].dqzt.value){
		if(document.forms[0].oldzt.value=="01"){
			alert("此数据属于已备案未填写报告表状态,与当前状态不一致不能进行此操作,请先查询!");
		}
		else if(document.forms[0].oldzt.value=="02"){
			alert("此数据属于已填写报告表未开具缴款书状态,与当前状态不一致不能进行此操作,请先查询!");
		}
		else if(document.forms[0].oldzt.value=="03"){
			alert("此数据属于已开具缴款书状态,与当前状态不一致不能进行此操作,请先查询!");
		}
		return false;
	}
	return true;
}

</script>


</body>
</html:html>