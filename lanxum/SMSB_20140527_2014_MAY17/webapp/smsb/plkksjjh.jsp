<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.ttsoft.bjtax.smsb.lwpk.web.PKTaskForm"%>
<%@ page import="com.ttsoft.bjtax.smsb.lwpk.model.PKTaskModel"%>
<html:html>
<head>
<title>批量扣款定时计划维护 </title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>
<script language="javascript" src="../js/gmit_selectcontrol.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>

</head>
<%
	PKTaskForm pktask = (PKTaskForm)request.getAttribute("pktaskForm");
	List pktaskList = pktask.getPkTaskList();
	List cxlxList = pktask.getCxlxList();
	List cxlxmcList = pktask.getCxlxmcList();
%>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
 <%@ include file="./include/header.jsp"%>
<html:form action="/webapp/smsb/pktaskAction.do" method="POST" >
<html:hidden property="actionType" />
  <table width="100%" border="0" cellspacing="0" class="table-99" align="center">
    <tr>
      <td class="1-td1" colspan=4>批量扣款定时计划维护</td>
    </tr>
    <tr>
                <TD class=2-td2-left WIDTH="35%" align=center>
                 <SPAN>请您输入年份：</SPAN>
				  <INPUT style="vertical-align:middle" id="nd" value="<bean:write name="pktaskForm" property="nd" />" name="nd"/> <SPAN>(格式：YYYY 如：2013)<FONT SIZE="" COLOR="#FF0000">*</FONT></SPAN> 
				 </TD>
                 <TD class=2-td2-left style="border-left-style:none" WIDTH="20%">
				  <SPAN >月份：</SPAN>
			  <SELECT style="vertical-align:middle" name="yd">  
			
                <logic:iterate id="cxyditem" name="pktaskForm" property="cxydList" >
                <%
                if(pktask.getYd().equals(cxyditem)){
                	if(pktask.getYd().equals("00")){
                		%>
                		<OPTION selected value='<bean:write name="cxyditem" />'>全部</OPTION> 
                		<%
                	}else{
                %>
                	<OPTION selected value='<bean:write name="cxyditem" />'><bean:write name="pktaskForm" property="yd" /></OPTION> 
                <%
                	}
                }else{
                	if(cxyditem.equals("00")){
                		%>
                		<OPTION value='<bean:write name="cxyditem" />'>全部</OPTION> 
                		<%
                	}else{
                	
                %>
                	<OPTION value='<bean:write name="cxyditem" />'><bean:write name="cxyditem" /></OPTION> 
                <%
                	}
                }
                %>
                
              
                </logic:iterate>
                 </TD>
                 <TD class=2-td2-left style="border-left-style:none" WIDTH="15%">
				 <SPAN >类型：</SPAN>
				 <SELECT style="vertical-align:middle" name="cxrwlx"> 
				 <%
			 		 for(int i=0;i<cxlxList.size();i++){
			 			 if(pktask.getCxrwlx().trim().equals(cxlxList.get(i))){
			 				%>
			 				<option value="<%= cxlxList.get(i)%>" selected><%= cxlxmcList.get(i) %></option>
			 				<%
			 			 }else{
				 		%>	
				 		 <option value="<%= cxlxList.get(i)%>"><%= cxlxmcList.get(i) %></option>
				 		<%
			 			 }
				 	}
				 %>
                  </SELECT>
                 </TD>
                 <TD class=2-td2-right WIDTH="30%" >
				  <image id=chax style="vertical-align:middle"
				  onmouseover="MM_swapImage('chax','','<%=static_contextpath%>images/chaxun2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() value=查询 alt=查询 
                  src="<%=static_contextpath%>images/chaxun1.jpg" width=79 height=22 
                  name=query onclick="to_Query()">
                  <input type=hidden name=isinit id=isinit value='<bean:write name="pktaskForm" property="isinit" />'/>
                  <%
                  if(pktask.getIsinit().trim().equals("false")){
                 %>
                	  
                 <image id=init style="vertical-align:middle"
                  value=初始化 alt=初始化 
                  src="../images/csh2.jpg" width=79 height=22 
                  name=init >
                	  
                <%
                  }else{
             	%>
				  <image id=init style="vertical-align:middle"
				  onmouseover="MM_swapImage('init','','../images/csh1.jpg',1)" 
                  onmouseout=MM_swapImgRestore() value=初始化 alt=初始化 
                  src="../images/csh2.jpg" width=79 height=22 
                  name=init onclick="to_Init()" >
                	  
                <%
                  }
                %>
				  </TD>
				  </TR>
<tr>
        <table  cellspacing="0" class="table-99">
    
        <table name=tablist id = tablist width="100%" border="0" cellspacing="0" class="table-99" align="center" >
          <TR>
                <TH class=2-td2-left width="22%">日期 </TH>
                <TH class=2-td2-left width="13%">时间 </TH>
				<TH class=2-td2-left width="20%">类型 </TH>
				<TH class=2-td2-left width="15%">执行结果 </TH>
                <TH class=2-td2-center width="30%">删除/修改 </TH>
		 </TR>
           <logic:iterate id="item" name="pktaskForm" property="pkTaskList" type="com.ttsoft.bjtax.smsb.lwpk.model.PKTaskModel" >
           	 <TR id=<bean:write name="item" property="xh"/> disabled=disabled>
           	 
           	 <input type="hidden" name="xh" value='<bean:write name="item" property="xh"/>'/>
               
                <TD class=2-td2-left><INPUT id="zxrq<bean:write name="item" property="xh"/>" name="zxrq" 
                value="<bean:write name="item" property="zxrq"/>" readonly></TD>
                <TD class=2-td2-left><SELECT id="zxsj<bean:write name="item" property="xh"/>" name="zxsj">
                <logic:iterate id="zxsjitem" name="pktaskForm" property="zxsjList" >
                <%
            if(item.getSj().equals(zxsjitem)){
                %>
                <OPTION selected ><bean:write name="item" property="sj" /></OPTION> 
                <%
                }else{
                %>
                	<OPTION><bean:write name="zxsjitem" /></OPTION> 
                <%
                }
                %>
                </logic:iterate>
                 </SELECT> 
                 
                 </TD>
                 
                 
				<TD class=2-td2-left>
				<SELECT name='zxrwlx'> 
				<%
					if(item.getRwlx().equals("01")){
				%>
				<OPTION  value='01' selected} >生成待扣信息时间</OPTION> 
				<OPTION  value='02' >发送扣款信息时间</OPTION> 
				<%
				}else{
				%>
				<OPTION  value='01' } >生成待扣信息时间</OPTION> 
				<OPTION  value='02' selected>发送扣款信息时间</OPTION> 
				<%
				}
				%>
				</SELECT> </TD>
				<TD class=2-td2-left name="zxjg"><bean:write name="item" property="zxjg"/></TD>
                <TD class=2-td2-center >
                <%
                	if(item.getZxzt().trim().equals("00")){
                %>
                		
                 <image id='<bean:write name="item" property="xh"/>sc' 
                  onmouseover="MM_swapImage('<bean:write name="item" property="xh"/>sc','','<%=static_contextpath%>images/shanchu2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() value=删除 alt=删除 
                  src="<%=static_contextpath%>images/shanchu1.jpg" width=79 height=22 
                  onclick="to_Delete('<bean:write name="item" property="xh"/>')"> 
				 <image id='<bean:write name="item" property="xh"/>xg' 
                  onmouseover="MM_swapImage('<bean:write name="item" property="xh"/>xg','','<%=static_contextpath%>images/xiugai2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() value=修改 alt=修改 
                  src="<%=static_contextpath%>images/xiugai1.jpg" width=79 height=22 
                  name=update onclick="to_updateimage('<bean:write name="item" property="xh"/>')">
                  
                <% 
                	}else{
                		
                %>
                		
                <image id='<bean:write name="item" property="xh"/>sc' 
                  value=删除 alt=删除 
                  src="<%=static_contextpath%>images/shanchu1.jpg" width=79 height=22 
                  name=shanchu > 
				 <image id='<bean:write name="item" property="xh"/>xg' 
                  value=修改 alt=修改 
                  src="<%=static_contextpath%>images/xiugai1.jpg" width=79 height=22 
                  name=update >
                		
                <% 
                	}
                
                %>
 
                  <image id='<bean:write name="item" property="xh"/>qx' 
                  onmouseover="MM_swapImage('<bean:write name="item" property="xh"/>qx','','<%=static_contextpath%>images/quxiao2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() value=取消 alt=取消 
                  src="<%=static_contextpath%>images/quxiao1.jpg" width=79 height=22 
                  style="display:none" onclick='to_updateimage("<bean:write name="item" property="xh"/>")'>
				  <image id='<bean:write name="item" property="xh"/>bc' 
                  onmouseover="MM_swapImage('<bean:write name="item" property="xh"/>bc','','<%=static_contextpath%>images/baocun2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() value=保存 alt=保存 
                  src="<%=static_contextpath%>images/baocun1.jpg" width=79 height=22 
                  onclick="to_SaveModify('<bean:write name="item" property="xh"/>')" style="display:none">
				 </TD>
		 </TR>
            </logic:iterate>
        </table>
        <br>
        <table width="94%" border="0" cellpadding="0" cellspacing="4">
          <tr valign="bottom">
            <td align=center>
            <%
            	if(pktaskList.size()==0){
            %>
            	<image id=tuichu1 
                  onmouseover="MM_swapImage('tuichu1','','<%=static_contextpath%>/images/tuichu2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() value=退出 alt=退出 
                  src="<%=static_contextpath%>images/tuichu1.jpg" width=79 height=22 onClick="tuichu();return false;"> 
            <%
            	}else{
            %>
            	<image id=baocun onmouseover="MM_swapImage('baocun','','<%=static_contextpath%>images/tianjia2.jpg',1)"  
                  onmouseout=MM_swapImgRestore() value=添加 alt=添加 
                  src="<%=static_contextpath%>images/tianjia1.jpg" width=79 height=22 
                  type=image onclick = "to_insertrow()"/>&nbsp;&nbsp;
				   <image id=dayin1 
                  onmouseover="MM_swapImage('dayin1','','<%=static_contextpath%>images/dayin2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() value=打印 alt=打印 
                  src="<%=static_contextpath%>images/dayin1.jpg" width=79 height=22 
                  onclick="to_print()">
				  &nbsp;&nbsp; <image id=tuichu2 
                  onmouseover="MM_swapImage('tuichu2','','<%=static_contextpath%>images/tuichu2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() value=退出 alt=退出 
                  src="<%=static_contextpath%>images/tuichu1.jpg" width=79 height=22 onClick="tuichu();return false;"> 
            		
            		
            <%
            	}
            
            %>
            
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table width="99%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td> <hr width="100%" size="1" > </td>
      <td width="99" align="center" class="black9">
        <strong><font color="#0000FF">注 意 事 项</font></strong></td>
      <td> <hr width="100%" size="1" >
      </td>
    </tr>
  </table>
  <table width="99%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
    <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
      <td height="47">
        &nbsp;&nbsp;1.计算机代码格式：8位数字或字母；<br>
       
      </td>
    </tr>
  </table>
  <p>&nbsp;</p></td>
</tr>
</table>
<br>
<br>
<%@ include file="./include/footer.jsp"%>
</td>
</tr>
</table>
</html:form>

<script type="text/javascript">
window.onload = function check_data(){
	<%
	if(pktaskList.size()==0 && request.getSession().getAttribute("is_plkksjjh_show").equals("false")){
	%>
		alert("未查询到符合条件的数据！");
	<%
	session.removeAttribute("is_plkksjjh_show");
	}
	%>
}



//修改与保存图片的片换
function to_updateimage(xh){
	var shanchu = document.getElementById(xh+'sc');
	var xiugai = document.getElementById(xh+'xg');
	var quxiao = document.getElementById(xh+'qx');
	var baocun = document.getElementById(xh+'bc');
	var rq_readonly = document.getElementById('zxrq'+xh);
	//点击取消按钮变回原来按钮
	if(xiugai.style.display=="none"){
		document.getElementById(xh).disabled="disabled";
		shanchu.style.display="inline";
		xiugai.style.display="inline";
		quxiao.style.display="none";
		baocun.style.display="none";

		rq_readonly.readOnly = "true";
	//按钮变成取消和保存
	}else{
		document.getElementById(xh).disabled=false;
		quxiao.style.display="inline";
		baocun.style.display="inline";
		shanchu.style.display="none";
		xiugai.style.display="none";

		rq_readonly.readOnly = false;
	}
	
}


//查询时间计划列表
function to_Query(){
	var nd = document.getElementById("nd").value;
	if(isFullDate(nd,1,"noempty",true)==false){
		//alert("年份格式不正确！");
		return false;
	}
	document.forms[0].actionType.value = "Query";
    document.forms[0].submit();
}

//添加一行数据
function to_insertrow(){  
	var i=<%=pktaskList.size() %>;
	  i++;
	  R = document.getElementById("tablist").insertRow(); 
	  C = R.insertCell();
	  C.className="2-td2-left";
	  C.innerHTML = "<INPUT id='addzxrq' value='' name='zxrq'>" ;
	  
	  C = R.insertCell()  ;
	  C.className="2-td2-left";
	  C.innerHTML = "<SELECT id = 'addzxsj' name='zxsj'><logic:iterate id='zxsjitem2' name='pktaskForm' property='zxsjList' ><OPTION value=<bean:write name='zxsjitem2' />><bean:write name='zxsjitem2' /></OPTION></logic:iterate></SELECT>"  ;

	  C = R.insertCell()   ;
	  C.className="2-td2-left";
	  C.innerHTML = "<SELECT name='zxrwlx'> <OPTION value='02'>发送扣款信息时间</OPTION>  <OPTION selected  value='01'> 生成扣款信息时间</OPTION> </SELECT>"  ;

	  C = R.insertCell()   ;
	  C.className="2-td2-left";
	  C.innerHTML = "未执行"  ;

	  
	  C = R.insertCell()  ; 
	  C.className="2-td2-center";
	  C.innerHTML = 
	  "<image id=quxaio value=取消  alt=取消 src=<%=static_contextpath%>images/quxiao2.jpg width=79 height=22 onclick=tablist.deleteRow("+i+") > <image id=baocun value=保存  alt=保存 src=<%=static_contextpath%>images/baocun2.jpg width=79 height=22 onclick='to_SaveAdd()'>" ;
	  
	 
	 } 
	 
//取消新插入的行
function to_deleteRow(obj){
	window.location.reload();

}
//保存修改后的数据
function to_SaveModify(xh){
	 var val = document.getElementById("zxrq"+xh).value;
	 var zxsj = document.getElementById("zxsj"+xh).value;
	 var rep =/^(\d{4})-(\d{2})-(\d{2})$/;
	if(val.length==0){
		alert("日期不能为空！");
		return false;
	}
	if(rep.test(val)==false){
		alert("日期格式不正确，应为YYYY-MM-DD！");
		return false;
	}
	var nian = val.substring(0,4);
	var yue = val.substring(5,7);
	var tian = val.substring(8,10);
	var xiaoshi = zxsj.substring(0,2);

	var year = new Date().getFullYear();
	var month = new Date().getMonth()+1;
	var day = new Date().getDate();
	var hours = new Date().getHours();
	
	 if(nian<year){
		alert("增加的年度不能小于当前年！");
		return false;
	}else if(nian>year){
		
	}else{
		if(yue<month){
			alert("月份不能小于当前月！");
			return false;
		}else if(yue>month){
			
		}else{
			if(day<10){
				day = "0"+day;
				alert(day);
				
			}
			if(tian<day){
				alert("日不能小于当前日！");
				return false;
			}else if(tian>day){
				
			}else{
				if(hours<10){
					hours = "0"+hours;
					alert(hours);
				}
				if(xiaoshi<hours){
					alert("时间不能小于当前时间！");
					return false;
				}
			}
		}
	} 
	if(confirm("你确认要修改吗？")){
		document.forms[0].actionType.value = "SaveModify";
	    document.forms[0].submit();
	}else{
		return false;
	}
	
}
//保存新增加的数据
function to_SaveAdd(){
	 var val = document.getElementById("addzxrq").value;
	 var zxsj = document.getElementById("addzxsj").value;
	 var rep =/^(\d{4})-(\d{2})-(\d{2})$/;
	if(val.length==0){
		alert("日期不能为空！");
		return false;
	}
	if(rep.test(val)==false){
		alert("日期格式不正确，应为YYYY-MM-DD！");
		return false;
	}
	var nian = val.substring(0,4);
	var yue = val.substring(5,7);
	var tian = val.substring(8,10);
	var xiaoshi = zxsj.substring(0,2);

	var year = new Date().getFullYear();
	var month = new Date().getMonth()+1;
	var day = new Date().getDate();
	var hours = new Date().getHours();
	
	 if(nian<year){
		alert("增加的年度不能小于当前年！");
		return false;
	}else if(nian>year){
		
	}else{
		if(yue<month){
			alert("月份不能小于当前月！");
			return false;
		}else if(yue>month){
			
		}else{
			if(day<10){
				day = "0"+day;
				alert(day);
				
			}
			if(tian<day){
				alert("日不能小于当前日！");
				return false;
			}else if(tian>day){
				
			}else{
				if(hours<10){
					hours = "0"+hours;
					alert(hours);
				}
				if(xiaoshi<hours){
					alert("时间不能小于当前时间！");
					return false;
				}
			}
		}
	} 
	document.forms[0].actionType.value = "SaveAdd";
    document.forms[0].submit();
}
//删除数据
function to_Delete(xh){
	if( confirm("删除后数据是不可恢复的，你确认要删除吗？")){
		document.getElementById(xh).disabled=false;
		document.forms[0].actionType.value = "Delete";
	    document.forms[0].submit();
	}else{
		document.getElementById(xh).disabled="disabled";
	}
	
}
//初始化时间计划表
function to_Init(){
	var nd = document.getElementById("nd").value;
	var date = new Date().getYear();
	
	if(confirm("你确认要初始化"+nd+"年的时间计划吗？")){
		if(nd<date){
			alert("初始化的年度小于当前年度，不能初始化！");
			return false;
		}
		if(isFullDate(nd,1,"noempty",true)==false){
			alert("年度格式不正确！");
			return false;
		}
		document.forms[0].actionType.value = "Init";
		document.forms[0].submit();
	}else{
		return false;
	}
	
	
}
//跳转到打印页面
function to_print(){
	document.forms[0].actionType.value = "Print";
    document.forms[0].submit();
}
 


</script>




</body>
</html:html>
