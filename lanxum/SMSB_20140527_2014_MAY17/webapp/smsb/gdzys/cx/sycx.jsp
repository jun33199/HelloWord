<%@page import="com.ttsoft.bjtax.smsb.gdzys.cx.web.SycxForm"%>
<%@ page import="com.ttsoft.bjtax.smsb.gdzys.cx.model.Symodel"%>
<%@ page import="com.ttsoft.bjtax.smsb.gdzys.cx.model.Symxmodel"%>
<%@ page import="com.ttsoft.bjtax.smsb.gdzys.cx.model.Syjmmodel"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
	<title>˰Դ��ѯ</title>
	<link href="../css/text.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="../js/treatImage.js"></script>
	<script language="JavaScript" src="../js/DTable.js"></script>
	<script language="JavaScript" src="../js/InputSelect.js"></script>
	<script language="JavaScript" src="../js/function.js"></script>
	<script language="javascript" src="../js/gmit_selectcontrol.js"></script>
	<script language="JavaScript" src="../js/smsb_common.js"></script>
	
<script language="JavaScript">

	
	//��ѯ��js,��֤��ѯ��������ȫ��
	function doQuery()
	{
		//if(document.forms[0].fjdm.value.length <=0)
		//{
		//	alert("����ѡ��һ��˰��������ܲ�ѯ");	
		//	return false;
		//}
		
		//��ѯ�־�ȫ��
		//var fjdm = document.forms[0].fjdm;
		//var swsdm = document.forms[0].swsdm;
		//if((swsdm.value==null||swsdm.value=="")&&(fjdm.value!=null&&fjdm.value!=""))
		//	{
			//	alert(swsdm.value);
			//	alert(fjdm.value);
			//	swsdm.value=fjdm.value;
			//}
		
		document.forms[0].maxPage.value=-1;				//��ҳ��
		document.forms[0].currentPageXx.value=1;		//��ǰҳ��
		document.forms[0].action="/smsb/webapp/smsb/sycxAction.do";
		document.forms[0].actionType.value = "Queryinf";
		document.forms[0].submit();
	
	}
	
	
	
	//�����б���ϸ��ѯ
	function doMxQuery(obj)
	{
		
		document.forms[0].action="/smsb/webapp/smsb/sycxAction.do";
		document.forms[0].sbbxlh.value =  document.getElementById(obj.id).innerHTML;
		document.forms[0].actionType.value = "Queryinfxx";
		document.forms[0].submit();
		//document.forms[0].sbxlh.value="";
	}
	
	//��ҳ
	function doTurnPage(para)
	{
   	 	var maxPage = document.forms[0].maxPage.value;				//��ҳ��  
       var pageSizeXx = document.forms[0].pageSizeXx.value;			//ÿҳ��ʾ����
       var currentPageXx = document.forms[0].currentPageXx.value;	//��ǰҳ��
       if(para == 'first')											//��һҳ
		{
   	   if(currentPageXx==1)
   		   {
   		   	alert('��ǰΪ��һҳ��');
				return ;
   		   }
   	   else{
   		 currentPageXx=1;
   	   		}
   		}
       if(para == 'last')											//���һҳ
	   {
   	   if(currentPageXx==maxPage)
		   {
		   	alert('��ǰΪ���ҳ��');
			return ;
		   }
   	   else{
   		   currentPageXx = maxPage;
   	  		 }
	  
	   }
      if(para == 'previous')										//ǰһҳ
	   {
   	   if(currentPageXx==1)
		   {
		   	alert('��ǰΪ��һҳ��');
			return ;
		   }
   	   else{
     		 currentPageXx--;
     	   }
	   }
      if(para == 'next')											//��һҳ
	   {
   	   if(currentPageXx==maxPage)
		   {
		   	alert('��ǰΪ���ҳ��');
			return ;
		   }
   	   else{
   		   currentPageXx++;
   	   		}
	   }
      //�ύ��ѯ
      document.forms[0].currentPageXx.value=currentPageXx;
      document.forms[0].fjdm.value=document.forms[0].fjdmhidden.value;
      document.forms[0].swsdm.value=document.forms[0].swsdmhidden.value;
      document.forms[0].sylxdm.value=document.forms[0].sylxdmhidden.value;
      document.forms[0].nsrlx.value=document.forms[0].nsrlxhidden.value;
      document.forms[0].jsjdm.value=document.forms[0].jsjdmhidden.value;
      document.forms[0].starttime.value=document.forms[0].starttimehidden.value;
      document.forms[0].endtime.value=document.forms[0].endtimehidden.value;
      document.forms[0].action="/smsb/webapp/smsb/sycxAction.do";
		document.forms[0].actionType.value = "Queryinf";
		document.forms[0].submit();  
    }
	
	//���ݷ־ֻ�ȡ˰������Ϣ
	function selectfj()
	{
		//׼��Ҫ�ı������
		var fjdm = document.forms[0].fjdm;		//�õ��־ִ������������
		var swsdm = document.forms[0].swsdm;		//�õ�˰�������������
		
		//--����http����
		if(window.ActiveXObject)
		{
	  	   		xmlhttp_request=new ActiveXObject("Microsoft.XMLHTTP");
	  	 }else if(window.XMLHttpRequest)
	  	    	{
	  	      		 xmlhttp_request=new XMLHttpRequest();
	  	    	 } else 
	  	    	 {
	  	       		return;
	  	   		 }
		
		//--������������ݲ�����
		if(fjdm.value==null || fjdm.value=="")		//�Ƿ���ѡ���˷־�
			{
				//alert("����ѡ��һ���־ֲ���ѡ��˰��˰����");
				return;
			}
		 xmlhttp_request.open("GET", "/smsb/webapp/smsb/sycxAction.do?actionType=Querysws&fjdm="+fjdm.value, true);
	     xmlhttp_request.send(null);
	  	     
		
		//--�յ���Ӧ����֮���ҳ���޸Ĵ���
		xmlhttp_request.onreadystatechange =function dealAjaxResponse()
	  	    {
				var xmlDOM;
				//�Ա���״̬�ж�
		        if (xmlhttp_request.readyState == 4)
		         {
		        	if (xmlhttp_request.status == 200) 
		        	{
		        		xmlDOM = xmlhttp_request.responseXML;									//�����Ӧ����
		        		var swsdmlength = swsdm.options.length;									//ԭ�б�����
		        		var swsdmlengthNew = xmlDOM.getElementsByTagName("swsid").length;		//���б�����
		        		
		        		//�Ƴ�˰����������ԭ�е�option
		        		for(var i=0;i<swsdmlength;i++)
		        		{
		     	           swsdm.options.remove(i);
		     	         }
		        		
		        		var tempOption;
		        		
		        		//���һ����Ϣ��Ĭ����ʾ
		        		if(swsdmlengthNew==1)
		        			{
		        			tempOption = document.createElement("option");
			        		tempOption.text=xmlDOM.getElementsByTagName("swsmc")[0].firstChild.data;;
			        		tempOption.value=xmlDOM.getElementsByTagName("swsid")[0].firstChild.data;;
			        		tempOption.selected=true;
			        		swsdm.add(tempOption);
		        			}
		        		else
		        		{
		        			//����Ĭ�ϵ�option
		        			tempOption = document.createElement("option");
		        			tempOption.text="��ѡ��";
		        			tempOption.value="";
		        			tempOption.selected=true;
		        			swsdm.add(tempOption);
		        		
		        			//���˰�����б�
		        		 	for (k=0; k<swsdmlengthNew;k++) 
		        		 	{
		        				 tempOption = document.createElement("option");
		        			 	tempOption.text = xmlDOM.getElementsByTagName("swsmc")[k].firstChild.data;
		        			 	tempOption.value= xmlDOM.getElementsByTagName("swsid")[k].firstChild.data;
		        			 	swsdm.add(tempOption);
		        			}
		        		}
		        		
		        	}
		         }
				
			}
	}
	
	//��֤YYYYMMDD�������ڣ�δ��֤��
	function doDate1Blur(obj)
	{
		var date1 = obj.value;
		//if(email == "")
		//{
		//	alert("�������ݲ���Ϊ��");
		//	return false;
		//}
		var date1reg=/^([0-9]{4})((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))$/;
		if(date1 != "" && !date1reg.test(date1) )
		{
			alert("����ʱ����Ϣ�����밴��ʽYYYYMMDD����ʱ��");
			obj.value="";
			return false;
		}
		return true;
	}
	
</script>
</head>


 <body bgcolor="#FFFFFF" marginwidth="0" marginheight="0" style="margin: 0">
 <!-----------------------------jsp�ﵥ����jspHeader----logo------------------------------------------------------------------------------------------------------------>
<%@include file="../../include/header.jsp"%>
<!-----------------------------------jspHeader����-------------------------------------------------------------------------------------------------------------------------->


 <table  align ="center" width="100%" height="75%" border="0" cellpadding="0" cellspacing="0" class = "black9">
<tr>
	<td valign="top"><br>
	 	<table align="center" cellspacing="0" class="table-99">
	 	
<!-- -----------------theme-------------------------------------------------------------- -->
<tr>
	<td class="1-td1" >˰Դ��ѯ</td>
</tr>	
<!---------------------theme------------------------------------------------------------------------>

<!-- ---------------------------------------------------context----------------------------------------------------------------------------------------------------------------- -->
<!-------------------------------------------------------�Ȳ�ѯ------------------------------------------------------>
<tr>
	<%SycxForm sycxForm = (SycxForm)request.getAttribute("sycxForm"); %>
	<td class = "1-td2">		
	<html:form action="/webapp/smsb/gdzydjcxAction.do" >
	<html:hidden property="actionType"/>
	<input name="sbbxlh" type ="hidden" id ="sbbxlh"/>
	<!-- ��ҳ���� -->
	<input name="maxPage" id ="maxPage" type ="hidden" value= <bean:write name="sycxForm" property="maxPage"/> >							<!-- ��ҳ�� -->
  	<input name="pageSizeXx"  id ="pageSizeXx" type ="hidden" value= <bean:write name="sycxForm" property="pageSizeXx"/> >					<!-- ÿҳ���� -->
  	<input name="currentPageXx"  id ="currentPageXx" type ="hidden" value= <bean:write name="sycxForm" property="currentPageXx"/> >			<!-- ��ǰҳ�� -->
	<input name="fjdmhidden" type ="hidden" id ="fjdmhidden" value="<bean:write name="sycxForm" property="fjdm"/>" >						<!-- �־ִ��� -->
	<input name="swsdmhidden" type ="hidden" id ="swsdmhidden" value="<bean:write name="sycxForm" property="swsdm"/>" >						<!-- ˰�������� -->
	<input name="sylxdmhidden" type ="hidden" id ="sylxdmhidden" value="<bean:write name="sycxForm" property="sylxdm"/>" >						<!-- ˰Դ���� -->
	<input name="nsrlxhidden" type ="hidden" id ="nsrlxhidden" value="<bean:write name="sycxForm" property="nsrlx"/>" >						<!-- ��˰������ -->
	<input name="jsjdmhidden" type ="hidden" id ="jsjdmhidden" value="<bean:write name="sycxForm" property="jsjdm"/>" >						<!-- ��������� -->
	<input name="starttimehidden" type ="hidden" id ="starttimehidden" value="<bean:write name="sycxForm" property="starttime"/>" >			<!-- ��ʼʱ�� -->
	<input name="endtimehidden" type ="hidden" id ="endtimehidden" value="<bean:write name="sycxForm" property="endtime"/>" >				<!-- ��ֹʱ�� -->
	
			 <table width="99%" border="0" cellpadding="0" cellspacing="0">
              <tr>
              	<tr><td>&nbsp;</td></tr>
                <td width="700"> <hr width="100%" size="1" class="hr1" >
                </td>
                <td width="100" align="center" class="black9"><strong>��ѯ����</strong></td>
                <td width="700"> <hr width="100%" size="1" class="hr1" >
                </td>
                <tr><td>&nbsp;</td></tr>
              </tr>
            </table>
			
		<!-- *��ѯ����  -- ¼��*-->
			<table align="center" width="99%" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF"  >
			  <tr>
			  	<td  class="2-td2-t-left" width="10%">ѡ��־�</td>
			    <td  class="2-td2-t-center" width="25%">
			    <div align=left>	
			    	<SELECT onchange=selectfj() id="fjdm" name="fjdm"  >
				    	
				    		<%List fjList = sycxForm.getFjlist();
				    		if(fjList.size()==1){%>
				    		
				    		<!-- �����һ����ֱ����ʾ -->
				    	<logic:iterate id="fjlistinf" name="sycxForm" property="fjlist">
				    		<OPTION  value=<bean:write name="fjlistinf" property="swdwid"/>  selected>
				    		<bean:write name="fjlistinf" property="swdwmc"/>
				    		</OPTION>
				    	</logic:iterate>
				    		
				    		<%}else{%>
				   
				    		<!-- ����Ĭ��ֵ -->
				    		<OPTION  value="" <%=(sycxForm.getFjdm()==null || "".equals(sycxForm.getFjdm())) ? "selected" : ""%> >��ѡ��</OPTION>
				    		
				    		<!-- ��ӿ�ѡ�б� -->
				    	<logic:iterate id="fjlistinf" name="sycxForm" property="fjlist">
				    		<bean:define id="tempswdwid" name="fjlistinf" property="swdwid" />
				    	
				    		<OPTION  value=<bean:write name="fjlistinf" property="swdwid"/> 
				    				<%=(sycxForm.getFjdm() != null && tempswdwid.equals(sycxForm.getFjdm())) ? "selected" : "" %> >
				    		<bean:write name="fjlistinf" property="swdwmc"/>
				    		</OPTION>
				    	</logic:iterate>
				    		<%} %>
                	</SELECT> 
                </div>
			    </td>
			    
			    <td  class="2-td2-t-right" width="10%">ѡ��˰����</td>
			    <td  class="2-td2-t-right" width="25%">
			    <div align=left>
			    	<SELECT onchange=is_nsrlx_display() id="swsdm" name="swsdm"  > 
			    	
			    	<%List swsList = sycxForm.getSwslist();
			    		if(swsList.size()==1){%>
			    		
			    		<!-- �����һ����ֱ����ʾ -->
			    	<logic:iterate id="swslistinf" name="sycxForm" property="swslist">
			    		<OPTION  value=<bean:write name="swslistinf" property="swdwid"/>  selected>
			    		<bean:write name="swslistinf" property="swdwmc"/>
			    		</OPTION>
			    	</logic:iterate>
			    		
			    		<%}else{%>
			    	
			    		<!-- ����Ĭ��ֵ -->		    		
			    		<OPTION  value="" <%=(sycxForm.getSwsdm()==null || "".equals(sycxForm.getSwsdm())) ? "selected" : ""%> >��ѡ��</OPTION>	
			    		
			    		<!-- ��ӿ�ѡ�б� -->		    	
			    	<logic:iterate id="swslistinf" name="sycxForm" property="swslist">
			    		<bean:define id="tempswdwid" name="swslistinf" property="swdwid" />
			    	
			    		<OPTION  value=<bean:write name="swslistinf" property="swdwid"/> 
			    			<%=(sycxForm.getSwsdm() != null && tempswdwid.equals(sycxForm.getSwsdm())) ? "selected" : "" %>>
			    		<bean:write name="swslistinf" property="swdwmc"/>
			    		</OPTION>
			    	</logic:iterate>
			    		<% }%>
                	</SELECT> 
                </div>
			    </td>
			    
			    <td  class="2-td2-t-right" width="10%">˰Դ����</td>
			    <td  class="2-td2-t-right" width="20%">
			    <div align=left>
			    <SELECT  id=sylxdm  name=sylxdm style="width:35%">
			    	<OPTION value="" <%if(sycxForm.getSylxdm()==null || sycxForm.getSylxdm().trim().equals("")) {%>selected<%} %> >ȫ��</OPTION>
			    	<OPTION value=0 <%if(sycxForm.getSylxdm()!=null && sycxForm.getSylxdm().trim().equals("0")) {%>selected<%} %> >ȫ��</OPTION>
                    <OPTION value=1 <%if(sycxForm.getSylxdm()!=null && sycxForm.getSylxdm().trim().equals("1")) {%>selected<%} %> >ȫ��</OPTION>
                    <OPTION value=2 <%if(sycxForm.getSylxdm()!=null && sycxForm.getSylxdm().trim().equals("2")) {%>selected<%} %> >��������</OPTION>
			    </SELECT> 
			    </div>
			    </td>
			  </tr>
			  
			  <tr>
			  	<td  class="2-td2-left" width="10%">��ʼ����(��ʽYYYYMMDD)</td>
			    <td  class="2-td2-center" width="25%">
			    <div align=left>
			    		<input name="starttime" type="text" style="width:60%" 
			    			   onkeydown="if(event.keyCode==13) event.keyCode=9" 	
			    			   onblur="javascript: doDate1Blur(this)"
			    			   value=<bean:write name="sycxForm" property="starttime"/>  >
			    </div>
			    </td>
			    <td  class="2-td2-right" width="10%">��ֹ����(��ʽYYYYMMDD)</td>
			    <td  class="2-td2-right" width="25%">
			    <div align=left>
			    		<input name="endtime" type="text"  style="width:60%" 
			    			   onkeydown="if(event.keyCode==13) event.keyCode=9" 
			    			   onblur="javascript: doDate1Blur(this)"
			    			   value=<bean:write name="sycxForm" property="endtime"/>  >
			    </div>
			    </td>
			    <td  class="2-td2-right" width="10%">��˰������</td>
			    <td  class="2-td2-right" width="20%">
			    <div align=left>
			    	<SELECT  id=nsrlx   name=nsrlx style="width:35%"> 
			    		<OPTION value="" <%if(sycxForm.getNsrlx()==null || sycxForm.getNsrlx().trim().equals("")) {%>selected<%} %> >ȫ��</OPTION>
			    		<OPTION value=0 <%if(sycxForm.getNsrlx()!=null && sycxForm.getNsrlx().trim().equals("0")) {%>selected<%} %> >��λ</OPTION> 
			    		<OPTION value=1 <%if(sycxForm.getNsrlx()!=null && sycxForm.getNsrlx().trim().equals("1")) {%>selected<%} %> >����</OPTION>
			    	</SELECT> 
			    </div> 
                </td>
			  </tr>
			  
			  <tr>
			    <td class="2-td2-left">���������</td>
			    <td class="2-td2-center" colspan="5">
			    <div align=left>
			    	<input name="jsjdm" type="text" style="width:16.5%" 
			    	onkeydown="if(event.keyCode==13) event.keyCode=9;" 
			    	value=<bean:write name="sycxForm" property="jsjdm"/> >
			    </div>
			    </td>
			  </tr>   
			</table>

			<table width="99%" height="60" border="0">
			  <tr>
			  	<td> 
			  		<div align="center">
			  				
							<!--*��ѯ��ť*-->
			  				<img src="<%=static_contextpath%>images/chaxun1.jpg" 
								 onmouseover="this.src='<%=static_contextpath%>images/chaxun2.jpg'" 
							     onmouseout="this.src='<%=static_contextpath%>images/chaxun1.jpg'" 
							     alt="��ѯ" onclick="doQuery();" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;

							<!--*�˳���ť*-->
			  				<img src="<%=static_contextpath%>images/tuichu1.jpg" 
								 onmouseover="this.src='<%=static_contextpath%>images/tuichu2.jpg'" 
								 onmouseout="this.src='<%=static_contextpath%>images/tuichu1.jpg'" 
								 alt="�˳�" onclick="tuichu();" style="cursor:hand">
					</div>
			  	</td>
			  </tr>
  			</table>
				

<!--------------------------------------------��һ�����------����鵽�����������ʾ�б�------------------------------------------->
	<%
	if(sycxForm.getTotalnum()>0)
	{
	%>
		<table width="99%" border="0" cellpadding="0" cellspacing="0">
			  <tr><td>&nbsp;</td></tr>
              <tr>
                <td width="700"> <hr width="100%" size="1" class="hr1" >
                </td>
                <td width="100" align="center" class="black9"><strong>��Ҫ��Ϣ</strong></td>
                <td width="700"> <hr width="100%" size="1" class="hr1" >
                </td>
              </tr>
              <tr><td>&nbsp;</td></tr>
         </table>
         
  		<!--*��ѯ�����Ҫ*-->
  		<table align="center" width="99%" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
  			<tr>
  				<td class="2-td1-left"   width="10%" noWrap>�걨���к�</td>
  				<td class="2-td1-center" width="10%" noWrap>��˰������</td>
  				<td class="2-td1-right"  width="10%" noWrap>���������</td>
				<td class="2-td1-right"  width="10%" noWrap>��׼ռ���ĺ�</td>
				<td class="2-td1-right"  width="10%" noWrap>��˰���</td>
				<td class="2-td1-right"  width="10%" noWrap>�������</td>
				<td class="2-td1-right"  width="10%" noWrap>Ӧ��˰��</td>
				<td class="2-td1-right"  width="10%" noWrap>�걨״̬</td>
				<td class="2-td1-right"  width="10%" noWrap>�걨ʱ��</td>
				<td class="2-td1-right"  width="10%" noWrap>��ע</td>
  			</tr>
  			
  				<%
  				int k=0;
  				%>
				<logic:iterate id="gdinf" name="sycxForm" property="infList">
				<%
				Symodel symodeltemp = (Symodel)(sycxForm.getInfList().get(k));
				k++; %>
  			<tr>
  				<td class="2-td2-left" noWrap><a id=sbbxlhResult<%=k %>  style="cursor:hand ;text-decoration:underline;" onclick = "doMxQuery(this)"><bean:write name="gdinf" property="sbbxlh"/></a></td>
  				<td class="2-td2-center" noWrap><bean:write name="gdinf" property="nsrmc"/>&nbsp;</td>
  				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="jsjdm"/>&nbsp;</td>
				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="zdpwh"/>&nbsp;</td>
				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="jsmj"/>&nbsp;</td>
				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="jmmj"/>&nbsp;</td>
				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="ynse"/>&nbsp;</td>
				<td class="2-td2-right"> 

  					<%if(symodeltemp.getSbzt().equals("40")) {%>
  						 �о���ȷ��			
					<%}if(symodeltemp.getSbzt().equals("30")) {%>
  						˰������ȷ��			
					<%}if(symodeltemp.getSbzt().equals("10")){ %>	
						 �ѳ��� 
					<% }%>	
		  	 
				&nbsp;
				</td>
				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="cjsj"/>&nbsp;</td>
				<td class="2-td2-right" > <bean:write name="gdinf" property="bz"/>&nbsp;</td>
  			</tr>
				</logic:iterate>
  		</table>
  		<table width="99%" border="0" cellpadding="0" cellspacing="0">
  		
  		<!-- ��ҳ��Ϣ -->
			  <tr><td >&nbsp;</td></tr>
              <tr>
                <td width="7%">
                	<img src="<%=static_contextpath%>images/diyiye1.jpg" 
					 onmouseover="this.src='<%=static_contextpath%>images/diyiye2.jpg'" 
					 onmouseout="this.src='<%=static_contextpath%>images/diyiye1.jpg'" 
					 alt="��һҳ" onclick="doTurnPage('first');" style="cursor:hand">
				</td>
                <td width="7%">
					<img src="<%=static_contextpath%>images/shangyiye1.jpg" 
					 onmouseover="this.src='<%=static_contextpath%>images/shangyiye2.jpg'" 
					 onmouseout="this.src='<%=static_contextpath%>images/shangyiye1.jpg'" 
					 alt="��һҳ" onclick="doTurnPage('previous');" style="cursor:hand">
				</td>
                <td width="7%">
					<img src="<%=static_contextpath%>images/xaiyiye1.jpg" 
					 onmouseover="this.src='<%=static_contextpath%>images/xaiyiye2.jpg'" 
					 onmouseout="this.src='<%=static_contextpath%>images/xaiyiye1.jpg'" 
					 alt="��һҳ" onclick="doTurnPage('next');" style="cursor:hand">
				</td>
                <td width="7%">
					<img src="<%=static_contextpath%>images/zuimoye1.jpg" 
					 onmouseover="this.src='<%=static_contextpath%>images/zuimoye2.jpg'" 
					 onmouseout="this.src='<%=static_contextpath%>images/zuimoye1.jpg'" 
					 alt="��ĩҳ" onclick="doTurnPage('last');" style="cursor:hand">
				</td>
                <td width="52%">&nbsp;</td>
                <td width="20%" align="right">
                <font size="2px"><strong>��ǰΪ��(<bean:write name="sycxForm" property="currentPageXx"/>)ҳ����(<bean:write name="sycxForm" property="maxPage"/>)ҳ</strong></font>
                </td>
              </tr>
         </table>
<% }%>



<!------------------------------------------------------��2�����-----���δ�鵽�����ʾ���½�---------------------------------------------->
  		
  		<% if(sycxForm.getTotalnum()==0)
  		{
  		%>
  		<table  align="center" width="99%" height = "80" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
		<tr>
			<td class = "2-td2-t-center" >
			<font size="3px" color = "red"> û�в鵽��Ӧ�����������ȷ�ϲ�ѯ���� &nbsp;&nbsp;&nbsp;&nbsp;
			</font>
			</td>
		</tr>
		</table>
		<% }%>
			
</html:form>
</td></tr>
  <!-- ---------------------------------------------------context------------------------------------------------ -->
  		</table>
  	</td>
</tr>

<!-------------------------------------------------------------bottom-jsp:include------------------------------------------------------------------>
<tr>
	<td>
		<%@ include file="../../include/footernew.jsp"%>
	</td>
</tr>
<!-------------------------------------------------------------------bottom------------------------------------------------------------->

</table> 

 </body>
</html:html>