<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.ttsoft.framework.util.JspUtil"%>
<%@ page import="com.ttsoft.common.util.CodeConstants"%>
<%@ page import="java.util.*" %>
<%
//DhkscxForm form=(DhkscxForm)request.getAttribute("dhkscxForm");
//PKJBSJModel dhcxmodel = form.getPkjbsjModel();
//List plkkdhcxModelList = form.getPlkkdhcxlist();

%>
<html:html>
<head>
<title>������˰�ۺϲ�ѯ</title>
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
var swsdm='<bean:write name="plkscxForm" property="cxdqjs"/>';
var yhjb='<bean:write name="plkscxForm" property="yhjb"/>';
//var queryFlag='<bean:write name="plkscxForm" property="queryFlag"/>';
function doit(ope){
	 if(document.forms[0].nd.value==""){
			alert("��ѯ��������Ȳ���Ϊ�գ�");
			return false;
     }
	if(document.forms[0].ystart.value==""){
		alert("��ѯ�����пۿ���ʼ�·ݲ���Ϊ�գ�");
		return false;
	}
	if(document.forms[0].yend.value==""){
		alert("��ѯ�����пۿ���ֹ�·ݲ���Ϊ�գ�");
		return false;
	}
	if(document.forms[0].ystart.value > document.forms[0].yend.value){
		alert("��ʼ�·ݲ��ܴ�����ֹ�·ݣ�");
		return false;
	}
	document.forms[0].currentPage.value = "1";
    //doSubmitForm(ope);
	document.forms[0].actionType.value = "Query";
	document.forms[0].submit();   
}


  // ҳ����뽹��ȷ��
   function fnOnLoad()
  {
	  //alert(swsdm);
	   //function ajaxgetsws(false);
    //document.forms[0].dqjs.focus();
   ajaxgetsws('1');
   /*
   alert(swsdm);
   var obj1 = document.getElementById("cxdqjs");
   for(var i=0;i<obj1.options.length;i++)
   {
	   
       if(obj1.options[i].value==swsdm)
       {
    	   alert(obj1.options[i].value);
    	   obj1.options[i].selected=true;
           break;
       }
   }
   */
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
	   //alert("����ѡ���־�");
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
	  	     xmlhttp_request.open("GET", "plkscxAction.do?actionType=getsws&dqjs="+obj.value, true);
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

 //��ҳ
function doTurnPage(para){
        	 var currentPages = document.forms[0].currentPage.value;
            var maxPages = document.forms[0].maxPage.value;
            var queryFlag = document.forms[0].queryFlag.value;
               if(queryFlag == false){
                alert("���Ȳ�ѯ��");
                return;
    		}
    		if(para == 'first'){
                if(currentPages == 1){
    				alert('��ǰΪ��һҳ��');
    				return ;
                }
                document.forms[0].currentPage.value = 1;
    		}else if(para == 'previous'){
                if(currentPages == 1){
    				alert('��ǰΪ��һҳ��');
    				return ;
                }
                document.forms[0].currentPage.value = parseInt(currentPages) - 1;
    		}else if(para == 'next'){
                if(currentPages == maxPages){
    				alert('��ǰΪ���һҳ��');
    				return ;
                }
                document.forms[0].currentPage.value = parseInt(currentPages) + 1;
    		}else if(para == 'last'){
                if(currentPages == maxPages){
    				alert('��ǰΪ���һҳ��');
    				return ;
                }
                document.forms[0].currentPage.value = maxPages;
    		}else if(para == 'go'){
                switchNumb = document.forms[0].switchPage.value;
                currentNumb = document.forms[0].currentPage.value;
                if(!checkNumber(trimString(switchNumb))){
    				alert("��������Ƿ������ַ����������������룡");
    				return;
                }
                if(switchNumb == currentNumb){
    				alert("��ǰ���ǵ�" + switchNumb + "ҳ��");
    				return;
                }
                if(parseInt(switchNumb) > parseInt(tPage)){
    				alert("���ҳ����" + tPage + "ҳ��������ת��" + switchNumb + "ҳ��");
    				return;
                }
                if(switchNumb <= '0'){
    				alert("���������0��ҳ�棡");
                    return;
                }
                document.forms[0].currentPage.value = switchNumb;
    		}
    		document.forms[0].actionType.value = "Query";
    		document.forms[0].submit();   
   }
//����Excel�ύ
function doToExcelSubmit(){
 if(document.all.queryFlag.value=='true'){
	 document.forms[0].actionType.value = "Export";
     document.forms[0].submit();   
 }else{
	alert("���Ȳ�ѯ��");
    return false;
 }
	
}

//��ѯ�ۿ���ϸ��Ϣ
function queryKkDetail(cgsbFlag,yhdm,fjdm,swsdm,kkrq){
	document.forms[0].cgsbFlag.value=cgsbFlag;
	document.forms[0].fjdmxx.value=fjdm;
	document.forms[0].swsdmxx.value=swsdm;
	document.forms[0].yhdmxx.value=yhdm;
	document.forms[0].kksjxx.value=kkrq;
	
	document.forms[0].target="";
	document.forms[0].currentPageXx.value = "1";
	document.forms[0].actionType.value="QueryKkDetail";
	document.forms[0].submit();
}

</SCRIPT>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
 <%@ include file="./include/header.jsp"%>
<html:form action="/webapp/smsb/plkscxAction.do" method="POST" >
<html:hidden property="actionType"/>
<html:hidden property="currentPage"/>
<html:hidden property="maxPage"/>
<html:hidden property="totalRowCount"/>
<html:hidden property="queryFlag"/>
<html:hidden property="cgsbFlag"/>

<html:hidden property="currentPageXx"/>
<html:hidden property="maxPageXx"/>
<html:hidden property="totalRowCountXx"/>


<html:hidden property="fjdmxx"/>
<html:hidden property="swsdmxx"/>
<html:hidden property="yhdmxx"/>
<html:hidden property="kksjxx"/>

    <logic:equal value="Query" name="plkscxForm" property="actionType">
    <bean:define id="plkklist" name="plkscxForm" property="dataList"/>
    <% 
    List dataList=(List)plkklist;
    if(dataList.size()==0){
    %>
	    <table width="96%" align="center" cellspacing="0" >
			<tr>
				<td><FONT SIZE="2" COLOR="#FF0000">û�в�ѯ�����������Ľ����</FONT></td>
			</tr>
		</table>
	<% 
    }
	%>	
	</logic:equal>
	 
	<table width="96%" align="center" cellspacing="0" class="table-99">
		<tr>
			<td class="1-td1"  colspan="7">������˰�ۺϲ�ѯ </td>
		</tr>
		<tr>
			<td class="1-td2"  colspan="7">
			   <TABLE class=table-99 bgColor=#f3f3f3 border=0>
              <TBODY>
              <TR>
                <TD vAlign=top width="6%" height=20><IMG height=9 
                  src="<%=static_contextpath%>images/w-01.gif" width=9></TD>
                <TD class=black9 align=left width="32%">�����������ѡ������������ѯ��Ϣ</TD>
                <TD align=left width="68%"></TD></TR></TBODY></TABLE>
                
            <TABLE class=table-99 id=table_LIST cellSpacing=0 border=0>
              <TBODY>
              <TR>
                <TD class=2-td2-t-left width="5%">�־�</TD>
                <TD class=2-td2-t-left width="18%">
                 <div align=left > 
                 
                  <bean:define id="tempdqjs" name="plkscxForm" property="dqjs"/>
              
                 
                 <select name="dqjs"  style="width:80%" id="dqjs" onchange="ajaxgetsws('false')">
                 
                 <logic:equal value="50" name="plkscxForm" property="yhjb">
                    <option value="" <%=tempdqjs.equals("")?"selected":""%>></option>
                 </logic:equal>
                   <% 
                     //System.out.println("1#############tempdqjs="+tempdqjs);
                     
                     %>
                    <logic:iterate id="data" indexId="index" length="length" name="plkscxForm" property="swdwlist" type="com.ttsoft.bjtax.smsb.model.client.swdwmodel">
                    <bean:define id="tempswdwid" name="data" property="swdwid"/>
                     <% 
                     //System.out.println("1#############tempswdwid="+tempswdwid);
                     
                     %>
                 
                      <option value="<bean:write name="data" property="swdwid"/>"  <%=tempdqjs.equals(tempswdwid)?"selected":""%>>
                      
                          <bean:write name="data" property="swdwmc"/>
                      </option>
                   </logic:iterate>
                </select>
                </div>
                  
                  
                  </TD>
                <TD class=2-td2-t-left width="9%">˰����</TD>
                <TD class=2-td2-t-left width="20%">
                  <div align="left">
                  <select name="cxdqjs"  style="width:80%" id="cxdqjs" >
                    <option value=""></option>
                   </select>
                   </div>
                  </TD>
                <TD class=2-td2-t-left width="10%">��������</TD>
                <TD class=2-td2-t-left width="22%">
                  <div align="left"> 
                  <bean:define id="tempyhdm" name="plkscxForm" property="yhdm"/>
                 <select name="yhdm"  style="width:80%" id="yhdm" >
                   <option value="" <%=tempyhdm.equals("")?"selected":""%>></option>
                    <logic:iterate id="data" indexId="index" length="length" name="plkscxForm" property="yhlist" type="com.ttsoft.bjtax.smsb.model.client.yhdmmodel">
                      <bean:define id="tempyhdmi" name="data" property="yhdm"/>
                       <option value="<bean:write name="data" property="yhdm"/>" <%=tempyhdm.equals(tempyhdmi)?"selected":""%>>
                          <bean:write name="data" property="yhmc"/>
                      </option>
                   </logic:iterate>
                </select>
                </div>
			  </TD>

			 <TD class=2-td2-t-center width="8%" rowspan="2">
			       <input name="I2" type="image" accesskey="q" value="��ѯ" onclick="doit('Query');return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="��ѯ" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun" align="middle">
             </TD>

			  </TR>
              <TR>
                <TD class=2-td2-left>���</TD>
                <TD class=2-td2-left>
                  <DIV align=left><html:text property="nd" size="20" /><FONT SIZE="" COLOR="#FF0000">*</FONT></DIV>
				</TD>
				 <TD class=2-td2-left>�ۿ���ʼ�·�</TD>
                <TD class=2-td2-left>
                  <DIV align=left><html:text property="ystart" size="20" /><span>MM�磺01<FONT SIZE="" COLOR="#FF0000">*</FONT></span></DIV>
				</TD>
				 <TD class=2-td2-left>�ۿ���ֹ�·�</TD>
                <TD class=2-td2-left>
                  <DIV align=left><html:text property="yend" size="20" /><span>MM�磺12<FONT SIZE="" COLOR="#FF0000">*</FONT></span></DIV>
				</TD>
          </TR>
		  </TBODY>
		  </TABLE>
		  </TD>
		  </TR>
		  
		          <TR bgColor=#ffffff>
          <TD class=1-td2 vAlign=top align=middle width="82%"><BR>
            <DIV class=black9 id=layerView align=left><STRONG>������λ��Ϊ��<SPAN 
            class=tishi><bean:write name="plkscxForm" property="currentPage"/></SPAN>ҳ����<SPAN class=tishi><bean:write name="plkscxForm" property="maxPage"/></SPAN>ҳ��<SPAN 
            class=tishi><bean:write name="plkscxForm" property="totalRowCount"/></SPAN>����¼��</STRONG></DIV>
			<DIV style="FONT-SIZE: 13px" align=center width="50%"><STRONG>��ѯ����б�</STRONG></DIV>
            <TABLE class=table-99 id=table_LIST cellSpacing=0 border=0>
              <TBODY>
<!-- ��ʼ-->
              <TR>
                <TD class=2-td1-left width="4%" rowspan="2">���</TD>
                <TD class=2-td1-left width="10%" rowspan="2">�ۿ�����</TD>
                <TD class=2-td1-left width="10%" rowspan="2">�־� </TD>
                <TD class=2-td1-left width="10%" rowspan="2">˰����</TD>
                <TD class=2-td1-left width="10%" rowspan="2">�ۿ�ʱ��</TD>
				<TD class=2-td1-left width="28%" colspan="4">����</TD>
                <TD class=2-td1-center width="28%" colspan="4">���</TD>
				</TR>
				<TR>
                <TD class=2-td1-left width="7%">�ɹ�����</TD>
                <TD class=2-td1-left width="7%">ʧ�ܱ���</TD>
				<TD class=2-td1-left width="7%">�ܱ���</TD>
				<TD class=2-td1-left width="7%">�ɹ���</TD>
                <TD class=2-td1-left width="7%">�ɹ����</TD>
                <TD class=2-td1-left width="7%">ʧ�ܽ��</TD>
				<TD class=2-td1-left width="7%">�ܽ��</TD>
				<TD class=2-td1-center width="7%">�ɹ���</TD>
                
			  </TR>
<!-- ����-->
      <logic:iterate id="item" name="plkscxForm" property="dataList" type="com.ttsoft.bjtax.smsb.lwpk.model.PLKKPLCXModel" indexId="indexid">
              <TR id=ROW_LIST>
                <TD class=2-td2-left>&nbsp;<%=indexid.intValue()+1%></TD>
                <TD class=2-td2-left>&nbsp;<bean:write name='item' property='yh'/></TD>
                <TD class=2-td2-left>&nbsp;<bean:write name='item' property='fj'/></TD>
                <TD class=2-td2-left>&nbsp;<bean:write name='item' property='sws'/></A></TD>
                <TD class=2-td2-left>&nbsp;<bean:write name='item' property='kksj'/></TD>
                
                <logic:equal name="item" value="0" property="cgbs">
                <TD class=2-td2-left>&nbsp;<bean:write name='item' property='cgbs'/></TD>
                </logic:equal>
                <logic:notEqual name="item" value="0" property="cgbs">
                <TD class=2-td2-left>&nbsp;<a href="javascript:queryKkDetail('20','<ttsoft:write name="item" property="yhdm"/>','<ttsoft:write name="item" property="fjdm"/>','<ttsoft:write name="item" property="swsdm"/>','<ttsoft:write name="item" property="kksj"/>')"><bean:write name='item' property='cgbs'/></a></TD>
                </logic:notEqual>
                
                <logic:equal name="item" value="0" property="sbbs">
                <TD class=2-td2-left>&nbsp;<bean:write name='item' property='sbbs'/></TD>
                </logic:equal>
                <logic:notEqual name="item" value="0" property="sbbs">
                <TD class=2-td2-left>&nbsp;<a href="javascript:queryKkDetail('21','<ttsoft:write name="item" property="yhdm"/>','<ttsoft:write name="item" property="fjdm"/>','<ttsoft:write name="item" property="swsdm"/>','<ttsoft:write name="item" property="kksj"/>')"><bean:write name='item' property='sbbs'/></a></TD>
                </logic:notEqual>
                
                <TD class=2-td2-left>&nbsp;<bean:write name='item' property='zbs'/></A></TD>
                <TD class=2-td2-left>&nbsp;<bean:write name='item' property='bscgl'/></TD>
                <TD class=2-td2-left>&nbsp;<bean:write name='item' property='cgje'/></TD>
				<TD class=2-td2-left>&nbsp;<bean:write name='item' property='sbje'/></TD>
                <TD class=2-td2-left>&nbsp;<bean:write name='item' property='zje'/></TD>
               <TD class=2-td2-center>&nbsp;<bean:write name='item' property='jecgl'/></TD>
			  </TR>
		</logic:iterate>	  
			  
              </TBODY>
         </TABLE>
			  <DIV class=black9 id=layerView align=right><STRONG>�ܳɹ�������<SPAN class=tishi><bean:write name="plkscxForm" property="zcgbs"/></SPAN> ��ʧ�ܱ�����<SPAN class=tishi><bean:write name="plkscxForm" property="zsbbs"/></SPAN> �ܱ�����<SPAN class=tishi><bean:write name="plkscxForm" property="zbs"/></SPAN> �ܽ�<SPAN class=tishi><bean:write name="plkscxForm" property="zje"/></SPAN>Ԫ  �ܳɹ���<SPAN class=tishi><bean:write name="plkscxForm" property="cgZje"/></SPAN>Ԫ  ��ʧ�ܽ�<SPAN class=tishi><bean:write name="plkscxForm" property="sbZje"/></SPAN>Ԫ</STRONG></DIV><BR><BR>
            <TABLE class=table-99 cellSpacing=0>
              <TBODY>
              <TR>
                <TD align=middle width="50%">
                  <DIV align=left><IMG id=first 
                  onmouseover="MM_swapImage('first','','<%=static_contextpath%>images/diyiye2.jpg',1)" 
                  onclick="doTurnPage('first')" onmouseout=MM_swapImgRestore() height=22 
                  src="<%=static_contextpath%>images/diyiye1.jpg" width=79 name=first> <IMG 
                  id=previous 
                  onmouseover="MM_swapImage('previous','','<%=static_contextpath%>images/shangyiye2.jpg',1)" 
                  onclick="doTurnPage('previous')" onmouseout=MM_swapImgRestore() 
                  height=22 src="<%=static_contextpath%>images/shangyiye1.jpg" width=79 
                  name=previous> <IMG id=next 
                  onmouseover="MM_swapImage('next','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" 
                  onclick="doTurnPage('next')" onmouseout=MM_swapImgRestore() height=22 
                  src="<%=static_contextpath%>images/xaiyiye1.jpg" width=79 border=0 
                  name=next> <IMG id=last 
                  onmouseover="MM_swapImage('last','','<%=static_contextpath%>images/zuimoye2.jpg',1)" 
                  onclick="doTurnPage('last')" onmouseout=MM_swapImgRestore() height=22 
                  src="<%=static_contextpath%>images/zuimoye1.jpg" width=79 border=0 
                  name=last> </DIV></TD>
                <TD width="15%">
                  <!--  
                  <DIV align=left>
                  <INPUT onkeydown=enterToPage(this.value) 
                  align=middle size=10 name=switchPage value="1"> <SPAN class=black9><FONT 
                  size=2>ҳ</FONT></SPAN>
                  </DIV>
                  -->
                  </TD>
          
                  </TR></TBODY>
              </TABLE>
		       <br>
				  
				<table width="100%" border="0" cellpadding="0" cellspacing="4">
					<tr valign="bottom">
						<td width="50%" align="right">
							<input name="toexcel" type="image" accesskey="s"  onClick="doToExcelSubmit();return false;"  onMouseOver="MM_swapImage('toexcel1','','<%=static_contextpath%>images/b-bcdexcel2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="���浽Excel" id="toexcel1" src="<%=static_contextpath%>images/b-bcdexcel1.jpg" width="100" height="22" >
						</td>
                        <td width="50%" align="left">	
							<input type="image" accesskey="c"   onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22">
						</td>
                        
                      </tr>
				</table>
				<br>
         <BR><BR></TD></TR>
		  <!-- 
		  <TR>
          <TD vAlign=bottom align=middle>
		  
				<br>

				<table width="100%" border="0" cellpadding="0" cellspacing="4">
					<tr valign="bottom">
						<td width="50%" align="right">
							<input name="toexcel" type="image" accesskey="s"  onClick="doToExcelSubmit();return false;"  onMouseOver="MM_swapImage('toexcel1','','<%=static_contextpath%>images/b-bcdexcel2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="���浽Excel" id="toexcel1" src="<%=static_contextpath%>images/b-bcdexcel1.jpg" width="100" height="22" >
						</td>
                        <td width="50%" align="left">	
							<input type="image" accesskey="c"   onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22">
						</td>
                        
                      </tr>
				</table>
			</td>
		</tr>
		 -->
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