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
	<title>税源查询</title>
	<link href="../css/text.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="../js/treatImage.js"></script>
	<script language="JavaScript" src="../js/DTable.js"></script>
	<script language="JavaScript" src="../js/InputSelect.js"></script>
	<script language="JavaScript" src="../js/function.js"></script>
	<script language="javascript" src="../js/gmit_selectcontrol.js"></script>
	<script language="JavaScript" src="../js/smsb_common.js"></script>
	
<script language="JavaScript">

	
	//查询的js,验证查询条件不能全空
	function doQuery()
	{
		//if(document.forms[0].fjdm.value.length <=0)
		//{
		//	alert("必须选择一个税务机构才能查询");	
		//	return false;
		//}
		
		//查询分局全部
		//var fjdm = document.forms[0].fjdm;
		//var swsdm = document.forms[0].swsdm;
		//if((swsdm.value==null||swsdm.value=="")&&(fjdm.value!=null&&fjdm.value!=""))
		//	{
			//	alert(swsdm.value);
			//	alert(fjdm.value);
			//	swsdm.value=fjdm.value;
			//}
		
		document.forms[0].maxPage.value=-1;				//总页码
		document.forms[0].currentPageXx.value=1;		//当前页码
		document.forms[0].action="/smsb/webapp/smsb/sycxAction.do";
		document.forms[0].actionType.value = "Queryinf";
		document.forms[0].submit();
	
	}
	
	
	
	//多条列表明细查询
	function doMxQuery(obj)
	{
		
		document.forms[0].action="/smsb/webapp/smsb/sycxAction.do";
		document.forms[0].sbbxlh.value =  document.getElementById(obj.id).innerHTML;
		document.forms[0].actionType.value = "Queryinfxx";
		document.forms[0].submit();
		//document.forms[0].sbxlh.value="";
	}
	
	//翻页
	function doTurnPage(para)
	{
   	 	var maxPage = document.forms[0].maxPage.value;				//总页码  
       var pageSizeXx = document.forms[0].pageSizeXx.value;			//每页显示条数
       var currentPageXx = document.forms[0].currentPageXx.value;	//当前页码
       if(para == 'first')											//第一页
		{
   	   if(currentPageXx==1)
   		   {
   		   	alert('当前为第一页！');
				return ;
   		   }
   	   else{
   		 currentPageXx=1;
   	   		}
   		}
       if(para == 'last')											//最后一页
	   {
   	   if(currentPageXx==maxPage)
		   {
		   	alert('当前为最后页！');
			return ;
		   }
   	   else{
   		   currentPageXx = maxPage;
   	  		 }
	  
	   }
      if(para == 'previous')										//前一页
	   {
   	   if(currentPageXx==1)
		   {
		   	alert('当前为第一页！');
			return ;
		   }
   	   else{
     		 currentPageXx--;
     	   }
	   }
      if(para == 'next')											//后一页
	   {
   	   if(currentPageXx==maxPage)
		   {
		   	alert('当前为最后页！');
			return ;
		   }
   	   else{
   		   currentPageXx++;
   	   		}
	   }
      //提交查询
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
	
	//根据分局获取税务所信息
	function selectfj()
	{
		//准备要改变的内容
		var fjdm = document.forms[0].fjdm;		//得到分局代码下拉框对象
		var swsdm = document.forms[0].swsdm;		//得到税务所下拉框对象
		
		//--生成http报文
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
		
		//--向报文中添加数据并发送
		if(fjdm.value==null || fjdm.value=="")		//是否已选择了分局
			{
				//alert("必须选择一个分局才能选择税务税务所");
				return;
			}
		 xmlhttp_request.open("GET", "/smsb/webapp/smsb/sycxAction.do?actionType=Querysws&fjdm="+fjdm.value, true);
	     xmlhttp_request.send(null);
	  	     
		
		//--收到响应报文之后的页面修改处理
		xmlhttp_request.onreadystatechange =function dealAjaxResponse()
	  	    {
				var xmlDOM;
				//对报文状态判断
		        if (xmlhttp_request.readyState == 4)
		         {
		        	if (xmlhttp_request.status == 200) 
		        	{
		        		xmlDOM = xmlhttp_request.responseXML;									//获得响应报文
		        		var swsdmlength = swsdm.options.length;									//原列表数量
		        		var swsdmlengthNew = xmlDOM.getElementsByTagName("swsid").length;		//新列表数量
		        		
		        		//移除税务所下拉框原有的option
		        		for(var i=0;i<swsdmlength;i++)
		        		{
		     	           swsdm.options.remove(i);
		     	         }
		        		
		        		var tempOption;
		        		
		        		//如果一条信息就默认显示
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
		        			//生成默认的option
		        			tempOption = document.createElement("option");
		        			tempOption.text="请选择";
		        			tempOption.value="";
		        			tempOption.selected=true;
		        			swsdm.add(tempOption);
		        		
		        			//添加税务所列表
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
	
	//验证YYYYMMDD类型日期，未验证空
	function doDate1Blur(obj)
	{
		var date1 = obj.value;
		//if(email == "")
		//{
		//	alert("输入内容不能为空");
		//	return false;
		//}
		var date1reg=/^([0-9]{4})((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))$/;
		if(date1 != "" && !date1reg.test(date1) )
		{
			alert("输入时间信息有误，请按格式YYYYMMDD输入时间");
			obj.value="";
			return false;
		}
		return true;
	}
	
</script>
</head>


 <body bgcolor="#FFFFFF" marginwidth="0" marginheight="0" style="margin: 0">
 <!-----------------------------jsp里单独的jspHeader----logo------------------------------------------------------------------------------------------------------------>
<%@include file="../../include/header.jsp"%>
<!-----------------------------------jspHeader结束-------------------------------------------------------------------------------------------------------------------------->


 <table  align ="center" width="100%" height="75%" border="0" cellpadding="0" cellspacing="0" class = "black9">
<tr>
	<td valign="top"><br>
	 	<table align="center" cellspacing="0" class="table-99">
	 	
<!-- -----------------theme-------------------------------------------------------------- -->
<tr>
	<td class="1-td1" >税源查询</td>
</tr>	
<!---------------------theme------------------------------------------------------------------------>

<!-- ---------------------------------------------------context----------------------------------------------------------------------------------------------------------------- -->
<!-------------------------------------------------------先查询------------------------------------------------------>
<tr>
	<%SycxForm sycxForm = (SycxForm)request.getAttribute("sycxForm"); %>
	<td class = "1-td2">		
	<html:form action="/webapp/smsb/gdzydjcxAction.do" >
	<html:hidden property="actionType"/>
	<input name="sbbxlh" type ="hidden" id ="sbbxlh"/>
	<!-- 分页条件 -->
	<input name="maxPage" id ="maxPage" type ="hidden" value= <bean:write name="sycxForm" property="maxPage"/> >							<!-- 总页数 -->
  	<input name="pageSizeXx"  id ="pageSizeXx" type ="hidden" value= <bean:write name="sycxForm" property="pageSizeXx"/> >					<!-- 每页条数 -->
  	<input name="currentPageXx"  id ="currentPageXx" type ="hidden" value= <bean:write name="sycxForm" property="currentPageXx"/> >			<!-- 当前页码 -->
	<input name="fjdmhidden" type ="hidden" id ="fjdmhidden" value="<bean:write name="sycxForm" property="fjdm"/>" >						<!-- 分局代码 -->
	<input name="swsdmhidden" type ="hidden" id ="swsdmhidden" value="<bean:write name="sycxForm" property="swsdm"/>" >						<!-- 税务所代码 -->
	<input name="sylxdmhidden" type ="hidden" id ="sylxdmhidden" value="<bean:write name="sycxForm" property="sylxdm"/>" >						<!-- 税源类型 -->
	<input name="nsrlxhidden" type ="hidden" id ="nsrlxhidden" value="<bean:write name="sycxForm" property="nsrlx"/>" >						<!-- 纳税人类型 -->
	<input name="jsjdmhidden" type ="hidden" id ="jsjdmhidden" value="<bean:write name="sycxForm" property="jsjdm"/>" >						<!-- 计算机代码 -->
	<input name="starttimehidden" type ="hidden" id ="starttimehidden" value="<bean:write name="sycxForm" property="starttime"/>" >			<!-- 起始时间 -->
	<input name="endtimehidden" type ="hidden" id ="endtimehidden" value="<bean:write name="sycxForm" property="endtime"/>" >				<!-- 截止时间 -->
	
			 <table width="99%" border="0" cellpadding="0" cellspacing="0">
              <tr>
              	<tr><td>&nbsp;</td></tr>
                <td width="700"> <hr width="100%" size="1" class="hr1" >
                </td>
                <td width="100" align="center" class="black9"><strong>查询条件</strong></td>
                <td width="700"> <hr width="100%" size="1" class="hr1" >
                </td>
                <tr><td>&nbsp;</td></tr>
              </tr>
            </table>
			
		<!-- *查询条件  -- 录入*-->
			<table align="center" width="99%" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF"  >
			  <tr>
			  	<td  class="2-td2-t-left" width="10%">选择分局</td>
			    <td  class="2-td2-t-center" width="25%">
			    <div align=left>	
			    	<SELECT onchange=selectfj() id="fjdm" name="fjdm"  >
				    	
				    		<%List fjList = sycxForm.getFjlist();
				    		if(fjList.size()==1){%>
				    		
				    		<!-- 如果是一条就直接显示 -->
				    	<logic:iterate id="fjlistinf" name="sycxForm" property="fjlist">
				    		<OPTION  value=<bean:write name="fjlistinf" property="swdwid"/>  selected>
				    		<bean:write name="fjlistinf" property="swdwmc"/>
				    		</OPTION>
				    	</logic:iterate>
				    		
				    		<%}else{%>
				   
				    		<!-- 设置默认值 -->
				    		<OPTION  value="" <%=(sycxForm.getFjdm()==null || "".equals(sycxForm.getFjdm())) ? "selected" : ""%> >请选择</OPTION>
				    		
				    		<!-- 添加可选列表 -->
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
			    
			    <td  class="2-td2-t-right" width="10%">选择税务所</td>
			    <td  class="2-td2-t-right" width="25%">
			    <div align=left>
			    	<SELECT onchange=is_nsrlx_display() id="swsdm" name="swsdm"  > 
			    	
			    	<%List swsList = sycxForm.getSwslist();
			    		if(swsList.size()==1){%>
			    		
			    		<!-- 如果是一条就直接显示 -->
			    	<logic:iterate id="swslistinf" name="sycxForm" property="swslist">
			    		<OPTION  value=<bean:write name="swslistinf" property="swdwid"/>  selected>
			    		<bean:write name="swslistinf" property="swdwmc"/>
			    		</OPTION>
			    	</logic:iterate>
			    		
			    		<%}else{%>
			    	
			    		<!-- 设置默认值 -->		    		
			    		<OPTION  value="" <%=(sycxForm.getSwsdm()==null || "".equals(sycxForm.getSwsdm())) ? "selected" : ""%> >请选择</OPTION>	
			    		
			    		<!-- 添加可选列表 -->		    	
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
			    
			    <td  class="2-td2-t-right" width="10%">税源类型</td>
			    <td  class="2-td2-t-right" width="20%">
			    <div align=left>
			    <SELECT  id=sylxdm  name=sylxdm style="width:35%">
			    	<OPTION value="" <%if(sycxForm.getSylxdm()==null || sycxForm.getSylxdm().trim().equals("")) {%>selected<%} %> >全部</OPTION>
			    	<OPTION value=0 <%if(sycxForm.getSylxdm()!=null && sycxForm.getSylxdm().trim().equals("0")) {%>selected<%} %> >全征</OPTION>
                    <OPTION value=1 <%if(sycxForm.getSylxdm()!=null && sycxForm.getSylxdm().trim().equals("1")) {%>selected<%} %> >全免</OPTION>
                    <OPTION value=2 <%if(sycxForm.getSylxdm()!=null && sycxForm.getSylxdm().trim().equals("2")) {%>selected<%} %> >有征有免</OPTION>
			    </SELECT> 
			    </div>
			    </td>
			  </tr>
			  
			  <tr>
			  	<td  class="2-td2-left" width="10%">起始日期(格式YYYYMMDD)</td>
			    <td  class="2-td2-center" width="25%">
			    <div align=left>
			    		<input name="starttime" type="text" style="width:60%" 
			    			   onkeydown="if(event.keyCode==13) event.keyCode=9" 	
			    			   onblur="javascript: doDate1Blur(this)"
			    			   value=<bean:write name="sycxForm" property="starttime"/>  >
			    </div>
			    </td>
			    <td  class="2-td2-right" width="10%">截止日期(格式YYYYMMDD)</td>
			    <td  class="2-td2-right" width="25%">
			    <div align=left>
			    		<input name="endtime" type="text"  style="width:60%" 
			    			   onkeydown="if(event.keyCode==13) event.keyCode=9" 
			    			   onblur="javascript: doDate1Blur(this)"
			    			   value=<bean:write name="sycxForm" property="endtime"/>  >
			    </div>
			    </td>
			    <td  class="2-td2-right" width="10%">纳税人类型</td>
			    <td  class="2-td2-right" width="20%">
			    <div align=left>
			    	<SELECT  id=nsrlx   name=nsrlx style="width:35%"> 
			    		<OPTION value="" <%if(sycxForm.getNsrlx()==null || sycxForm.getNsrlx().trim().equals("")) {%>selected<%} %> >全部</OPTION>
			    		<OPTION value=0 <%if(sycxForm.getNsrlx()!=null && sycxForm.getNsrlx().trim().equals("0")) {%>selected<%} %> >单位</OPTION> 
			    		<OPTION value=1 <%if(sycxForm.getNsrlx()!=null && sycxForm.getNsrlx().trim().equals("1")) {%>selected<%} %> >个人</OPTION>
			    	</SELECT> 
			    </div> 
                </td>
			  </tr>
			  
			  <tr>
			    <td class="2-td2-left">计算机代码</td>
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
			  				
							<!--*查询按钮*-->
			  				<img src="<%=static_contextpath%>images/chaxun1.jpg" 
								 onmouseover="this.src='<%=static_contextpath%>images/chaxun2.jpg'" 
							     onmouseout="this.src='<%=static_contextpath%>images/chaxun1.jpg'" 
							     alt="查询" onclick="doQuery();" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;

							<!--*退出按钮*-->
			  				<img src="<%=static_contextpath%>images/tuichu1.jpg" 
								 onmouseover="this.src='<%=static_contextpath%>images/tuichu2.jpg'" 
								 onmouseout="this.src='<%=static_contextpath%>images/tuichu1.jpg'" 
								 alt="退出" onclick="tuichu();" style="cursor:hand">
					</div>
			  	</td>
			  </tr>
  			</table>
				

<!--------------------------------------------第一种情况------如果查到多条结果就显示列表------------------------------------------->
	<%
	if(sycxForm.getTotalnum()>0)
	{
	%>
		<table width="99%" border="0" cellpadding="0" cellspacing="0">
			  <tr><td>&nbsp;</td></tr>
              <tr>
                <td width="700"> <hr width="100%" size="1" class="hr1" >
                </td>
                <td width="100" align="center" class="black9"><strong>概要信息</strong></td>
                <td width="700"> <hr width="100%" size="1" class="hr1" >
                </td>
              </tr>
              <tr><td>&nbsp;</td></tr>
         </table>
         
  		<!--*查询结果概要*-->
  		<table align="center" width="99%" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
  			<tr>
  				<td class="2-td1-left"   width="10%" noWrap>申报序列号</td>
  				<td class="2-td1-center" width="10%" noWrap>纳税人名称</td>
  				<td class="2-td1-right"  width="10%" noWrap>计算机代码</td>
				<td class="2-td1-right"  width="10%" noWrap>批准占地文号</td>
				<td class="2-td1-right"  width="10%" noWrap>计税面积</td>
				<td class="2-td1-right"  width="10%" noWrap>减免面积</td>
				<td class="2-td1-right"  width="10%" noWrap>应纳税额</td>
				<td class="2-td1-right"  width="10%" noWrap>申报状态</td>
				<td class="2-td1-right"  width="10%" noWrap>申报时间</td>
				<td class="2-td1-right"  width="10%" noWrap>备注</td>
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
  						 市局已确认			
					<%}if(symodeltemp.getSbzt().equals("30")) {%>
  						税务所已确认			
					<%}if(symodeltemp.getSbzt().equals("10")){ %>	
						 已初存 
					<% }%>	
		  	 
				&nbsp;
				</td>
				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="cjsj"/>&nbsp;</td>
				<td class="2-td2-right" > <bean:write name="gdinf" property="bz"/>&nbsp;</td>
  			</tr>
				</logic:iterate>
  		</table>
  		<table width="99%" border="0" cellpadding="0" cellspacing="0">
  		
  		<!-- 分页信息 -->
			  <tr><td >&nbsp;</td></tr>
              <tr>
                <td width="7%">
                	<img src="<%=static_contextpath%>images/diyiye1.jpg" 
					 onmouseover="this.src='<%=static_contextpath%>images/diyiye2.jpg'" 
					 onmouseout="this.src='<%=static_contextpath%>images/diyiye1.jpg'" 
					 alt="第一页" onclick="doTurnPage('first');" style="cursor:hand">
				</td>
                <td width="7%">
					<img src="<%=static_contextpath%>images/shangyiye1.jpg" 
					 onmouseover="this.src='<%=static_contextpath%>images/shangyiye2.jpg'" 
					 onmouseout="this.src='<%=static_contextpath%>images/shangyiye1.jpg'" 
					 alt="上一页" onclick="doTurnPage('previous');" style="cursor:hand">
				</td>
                <td width="7%">
					<img src="<%=static_contextpath%>images/xaiyiye1.jpg" 
					 onmouseover="this.src='<%=static_contextpath%>images/xaiyiye2.jpg'" 
					 onmouseout="this.src='<%=static_contextpath%>images/xaiyiye1.jpg'" 
					 alt="下一页" onclick="doTurnPage('next');" style="cursor:hand">
				</td>
                <td width="7%">
					<img src="<%=static_contextpath%>images/zuimoye1.jpg" 
					 onmouseover="this.src='<%=static_contextpath%>images/zuimoye2.jpg'" 
					 onmouseout="this.src='<%=static_contextpath%>images/zuimoye1.jpg'" 
					 alt="最末页" onclick="doTurnPage('last');" style="cursor:hand">
				</td>
                <td width="52%">&nbsp;</td>
                <td width="20%" align="right">
                <font size="2px"><strong>当前为第(<bean:write name="sycxForm" property="currentPageXx"/>)页，共(<bean:write name="sycxForm" property="maxPage"/>)页</strong></font>
                </td>
              </tr>
         </table>
<% }%>



<!------------------------------------------------------第2种情况-----如果未查到结果显示并新建---------------------------------------------->
  		
  		<% if(sycxForm.getTotalnum()==0)
  		{
  		%>
  		<table  align="center" width="99%" height = "80" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
		<tr>
			<td class = "2-td2-t-center" >
			<font size="3px" color = "red"> 没有查到相应结果，请重新确认查询条件 &nbsp;&nbsp;&nbsp;&nbsp;
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