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
<title>�����ۿʱ�ƻ�ά�� </title>
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
      <td class="1-td1" colspan=4>�����ۿʱ�ƻ�ά��</td>
    </tr>
    <tr>
                <TD class=2-td2-left WIDTH="35%" align=center>
                 <SPAN>����������ݣ�</SPAN>
				  <INPUT style="vertical-align:middle" id="nd" value="<bean:write name="pktaskForm" property="nd" />" name="nd"/> <SPAN>(��ʽ��YYYY �磺2013)<FONT SIZE="" COLOR="#FF0000">*</FONT></SPAN> 
				 </TD>
                 <TD class=2-td2-left style="border-left-style:none" WIDTH="20%">
				  <SPAN >�·ݣ�</SPAN>
			  <SELECT style="vertical-align:middle" name="yd">  
			
                <logic:iterate id="cxyditem" name="pktaskForm" property="cxydList" >
                <%
                if(pktask.getYd().equals(cxyditem)){
                	if(pktask.getYd().equals("00")){
                		%>
                		<OPTION selected value='<bean:write name="cxyditem" />'>ȫ��</OPTION> 
                		<%
                	}else{
                %>
                	<OPTION selected value='<bean:write name="cxyditem" />'><bean:write name="pktaskForm" property="yd" /></OPTION> 
                <%
                	}
                }else{
                	if(cxyditem.equals("00")){
                		%>
                		<OPTION value='<bean:write name="cxyditem" />'>ȫ��</OPTION> 
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
				 <SPAN >���ͣ�</SPAN>
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
                  onmouseout=MM_swapImgRestore() value=��ѯ alt=��ѯ 
                  src="<%=static_contextpath%>images/chaxun1.jpg" width=79 height=22 
                  name=query onclick="to_Query()">
                  <input type=hidden name=isinit id=isinit value='<bean:write name="pktaskForm" property="isinit" />'/>
                  <%
                  if(pktask.getIsinit().trim().equals("false")){
                 %>
                	  
                 <image id=init style="vertical-align:middle"
                  value=��ʼ�� alt=��ʼ�� 
                  src="../images/csh2.jpg" width=79 height=22 
                  name=init >
                	  
                <%
                  }else{
             	%>
				  <image id=init style="vertical-align:middle"
				  onmouseover="MM_swapImage('init','','../images/csh1.jpg',1)" 
                  onmouseout=MM_swapImgRestore() value=��ʼ�� alt=��ʼ�� 
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
                <TH class=2-td2-left width="22%">���� </TH>
                <TH class=2-td2-left width="13%">ʱ�� </TH>
				<TH class=2-td2-left width="20%">���� </TH>
				<TH class=2-td2-left width="15%">ִ�н�� </TH>
                <TH class=2-td2-center width="30%">ɾ��/�޸� </TH>
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
				<OPTION  value='01' selected} >���ɴ�����Ϣʱ��</OPTION> 
				<OPTION  value='02' >���Ϳۿ���Ϣʱ��</OPTION> 
				<%
				}else{
				%>
				<OPTION  value='01' } >���ɴ�����Ϣʱ��</OPTION> 
				<OPTION  value='02' selected>���Ϳۿ���Ϣʱ��</OPTION> 
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
                  onmouseout=MM_swapImgRestore() value=ɾ�� alt=ɾ�� 
                  src="<%=static_contextpath%>images/shanchu1.jpg" width=79 height=22 
                  onclick="to_Delete('<bean:write name="item" property="xh"/>')"> 
				 <image id='<bean:write name="item" property="xh"/>xg' 
                  onmouseover="MM_swapImage('<bean:write name="item" property="xh"/>xg','','<%=static_contextpath%>images/xiugai2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() value=�޸� alt=�޸� 
                  src="<%=static_contextpath%>images/xiugai1.jpg" width=79 height=22 
                  name=update onclick="to_updateimage('<bean:write name="item" property="xh"/>')">
                  
                <% 
                	}else{
                		
                %>
                		
                <image id='<bean:write name="item" property="xh"/>sc' 
                  value=ɾ�� alt=ɾ�� 
                  src="<%=static_contextpath%>images/shanchu1.jpg" width=79 height=22 
                  name=shanchu > 
				 <image id='<bean:write name="item" property="xh"/>xg' 
                  value=�޸� alt=�޸� 
                  src="<%=static_contextpath%>images/xiugai1.jpg" width=79 height=22 
                  name=update >
                		
                <% 
                	}
                
                %>
 
                  <image id='<bean:write name="item" property="xh"/>qx' 
                  onmouseover="MM_swapImage('<bean:write name="item" property="xh"/>qx','','<%=static_contextpath%>images/quxiao2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() value=ȡ�� alt=ȡ�� 
                  src="<%=static_contextpath%>images/quxiao1.jpg" width=79 height=22 
                  style="display:none" onclick='to_updateimage("<bean:write name="item" property="xh"/>")'>
				  <image id='<bean:write name="item" property="xh"/>bc' 
                  onmouseover="MM_swapImage('<bean:write name="item" property="xh"/>bc','','<%=static_contextpath%>images/baocun2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() value=���� alt=���� 
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
                  onmouseout=MM_swapImgRestore() value=�˳� alt=�˳� 
                  src="<%=static_contextpath%>images/tuichu1.jpg" width=79 height=22 onClick="tuichu();return false;"> 
            <%
            	}else{
            %>
            	<image id=baocun onmouseover="MM_swapImage('baocun','','<%=static_contextpath%>images/tianjia2.jpg',1)"  
                  onmouseout=MM_swapImgRestore() value=��� alt=��� 
                  src="<%=static_contextpath%>images/tianjia1.jpg" width=79 height=22 
                  type=image onclick = "to_insertrow()"/>&nbsp;&nbsp;
				   <image id=dayin1 
                  onmouseover="MM_swapImage('dayin1','','<%=static_contextpath%>images/dayin2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() value=��ӡ alt=��ӡ 
                  src="<%=static_contextpath%>images/dayin1.jpg" width=79 height=22 
                  onclick="to_print()">
				  &nbsp;&nbsp; <image id=tuichu2 
                  onmouseover="MM_swapImage('tuichu2','','<%=static_contextpath%>images/tuichu2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() value=�˳� alt=�˳� 
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
        <strong><font color="#0000FF">ע �� �� ��</font></strong></td>
      <td> <hr width="100%" size="1" >
      </td>
    </tr>
  </table>
  <table width="99%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
    <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
      <td height="47">
        &nbsp;&nbsp;1.����������ʽ��8λ���ֻ���ĸ��<br>
       
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
		alert("δ��ѯ���������������ݣ�");
	<%
	session.removeAttribute("is_plkksjjh_show");
	}
	%>
}



//�޸��뱣��ͼƬ��Ƭ��
function to_updateimage(xh){
	var shanchu = document.getElementById(xh+'sc');
	var xiugai = document.getElementById(xh+'xg');
	var quxiao = document.getElementById(xh+'qx');
	var baocun = document.getElementById(xh+'bc');
	var rq_readonly = document.getElementById('zxrq'+xh);
	//���ȡ����ť���ԭ����ť
	if(xiugai.style.display=="none"){
		document.getElementById(xh).disabled="disabled";
		shanchu.style.display="inline";
		xiugai.style.display="inline";
		quxiao.style.display="none";
		baocun.style.display="none";

		rq_readonly.readOnly = "true";
	//��ť���ȡ���ͱ���
	}else{
		document.getElementById(xh).disabled=false;
		quxiao.style.display="inline";
		baocun.style.display="inline";
		shanchu.style.display="none";
		xiugai.style.display="none";

		rq_readonly.readOnly = false;
	}
	
}


//��ѯʱ��ƻ��б�
function to_Query(){
	var nd = document.getElementById("nd").value;
	if(isFullDate(nd,1,"noempty",true)==false){
		//alert("��ݸ�ʽ����ȷ��");
		return false;
	}
	document.forms[0].actionType.value = "Query";
    document.forms[0].submit();
}

//���һ������
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
	  C.innerHTML = "<SELECT name='zxrwlx'> <OPTION value='02'>���Ϳۿ���Ϣʱ��</OPTION>  <OPTION selected  value='01'> ���ɿۿ���Ϣʱ��</OPTION> </SELECT>"  ;

	  C = R.insertCell()   ;
	  C.className="2-td2-left";
	  C.innerHTML = "δִ��"  ;

	  
	  C = R.insertCell()  ; 
	  C.className="2-td2-center";
	  C.innerHTML = 
	  "<image id=quxaio value=ȡ��  alt=ȡ�� src=<%=static_contextpath%>images/quxiao2.jpg width=79 height=22 onclick=tablist.deleteRow("+i+") > <image id=baocun value=����  alt=���� src=<%=static_contextpath%>images/baocun2.jpg width=79 height=22 onclick='to_SaveAdd()'>" ;
	  
	 
	 } 
	 
//ȡ���²������
function to_deleteRow(obj){
	window.location.reload();

}
//�����޸ĺ������
function to_SaveModify(xh){
	 var val = document.getElementById("zxrq"+xh).value;
	 var zxsj = document.getElementById("zxsj"+xh).value;
	 var rep =/^(\d{4})-(\d{2})-(\d{2})$/;
	if(val.length==0){
		alert("���ڲ���Ϊ�գ�");
		return false;
	}
	if(rep.test(val)==false){
		alert("���ڸ�ʽ����ȷ��ӦΪYYYY-MM-DD��");
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
		alert("���ӵ���Ȳ���С�ڵ�ǰ�꣡");
		return false;
	}else if(nian>year){
		
	}else{
		if(yue<month){
			alert("�·ݲ���С�ڵ�ǰ�£�");
			return false;
		}else if(yue>month){
			
		}else{
			if(day<10){
				day = "0"+day;
				alert(day);
				
			}
			if(tian<day){
				alert("�ղ���С�ڵ�ǰ�գ�");
				return false;
			}else if(tian>day){
				
			}else{
				if(hours<10){
					hours = "0"+hours;
					alert(hours);
				}
				if(xiaoshi<hours){
					alert("ʱ�䲻��С�ڵ�ǰʱ�䣡");
					return false;
				}
			}
		}
	} 
	if(confirm("��ȷ��Ҫ�޸���")){
		document.forms[0].actionType.value = "SaveModify";
	    document.forms[0].submit();
	}else{
		return false;
	}
	
}
//���������ӵ�����
function to_SaveAdd(){
	 var val = document.getElementById("addzxrq").value;
	 var zxsj = document.getElementById("addzxsj").value;
	 var rep =/^(\d{4})-(\d{2})-(\d{2})$/;
	if(val.length==0){
		alert("���ڲ���Ϊ�գ�");
		return false;
	}
	if(rep.test(val)==false){
		alert("���ڸ�ʽ����ȷ��ӦΪYYYY-MM-DD��");
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
		alert("���ӵ���Ȳ���С�ڵ�ǰ�꣡");
		return false;
	}else if(nian>year){
		
	}else{
		if(yue<month){
			alert("�·ݲ���С�ڵ�ǰ�£�");
			return false;
		}else if(yue>month){
			
		}else{
			if(day<10){
				day = "0"+day;
				alert(day);
				
			}
			if(tian<day){
				alert("�ղ���С�ڵ�ǰ�գ�");
				return false;
			}else if(tian>day){
				
			}else{
				if(hours<10){
					hours = "0"+hours;
					alert(hours);
				}
				if(xiaoshi<hours){
					alert("ʱ�䲻��С�ڵ�ǰʱ�䣡");
					return false;
				}
			}
		}
	} 
	document.forms[0].actionType.value = "SaveAdd";
    document.forms[0].submit();
}
//ɾ������
function to_Delete(xh){
	if( confirm("ɾ���������ǲ��ɻָ��ģ���ȷ��Ҫɾ����")){
		document.getElementById(xh).disabled=false;
		document.forms[0].actionType.value = "Delete";
	    document.forms[0].submit();
	}else{
		document.getElementById(xh).disabled="disabled";
	}
	
}
//��ʼ��ʱ��ƻ���
function to_Init(){
	var nd = document.getElementById("nd").value;
	var date = new Date().getYear();
	
	if(confirm("��ȷ��Ҫ��ʼ��"+nd+"���ʱ��ƻ���")){
		if(nd<date){
			alert("��ʼ�������С�ڵ�ǰ��ȣ����ܳ�ʼ����");
			return false;
		}
		if(isFullDate(nd,1,"noempty",true)==false){
			alert("��ȸ�ʽ����ȷ��");
			return false;
		}
		document.forms[0].actionType.value = "Init";
		document.forms[0].submit();
	}else{
		return false;
	}
	
	
}
//��ת����ӡҳ��
function to_print(){
	document.forms[0].actionType.value = "Print";
    document.forms[0].submit();
}
 


</script>




</body>
</html:html>
