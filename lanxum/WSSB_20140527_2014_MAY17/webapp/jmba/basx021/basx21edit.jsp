<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="shtml" %>
<%@page import="com.ttsoft.bjtax.shenbao.zhsb.SessionKey"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import="com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm"%>
<%@page import="com.ttsoft.bjtax.shenbao.constant.CodeTable"%>

<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
		<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
		<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>
		<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/date.js"></script>
		<script language="JavaScript" type="text/javascript" src="js1/WdatePicker.js"></script>
		<script language="JavaScript" src="js/calculate.js"></script>
		<link href="css/jmba.css" rel="stylesheet" type="text/css">
		<style>
		input {
		    font-size: 9pt;
		    text-align: right;
		}
		</style>
		
		
		<script language="JavaScript">	
		<%
		    String containerName = "";
		    com.ttsoft.common.model.UserData userdata = (com.ttsoft.common.model.UserData)session.getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA);
		    if (userdata.getCaflag())
		    {
		        containerName = userdata.getCert().getContainerName();
		    }
		    else
		    {
		        containerName = "";
		    }
		%>
		
			//初始化js变量
			g_objSI.Container = "<%=containerName%>";
			var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
			var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
			var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION")==null?"":request.getAttribute("CA_XML_SCHEME_VERSION")%>';
			var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
		
			//页面加载后根据xml装载信息
			function parseXmlOnLoad()
			{
				var xslPath='/XSLTWEB/model/030021/XSLT/basx21edit.xsl';
				if (strXml != "")
			    {
			        if(! parseXml(strXml,xslPath,"result"))
			            return false;
			    }
			    return true;
			}
/*------------------------校验-------------------------------------*/
 			//校验页面元素
 			function checkYm()
 			{
 				var mzqsnd = document.getElementById("mzqsnd");
 				if(mzqsnd.value == "" )
 				{
 					alert("免征起始年度不能为空!");
 					document.forms[0].mzqsnd.focus();
 					return false;
 				}
 				if(mzqsnd.value.length != 4 || isNaN(mzqsnd.value))
 				{
 					alert("起始免征年度必须为4位数字");
 					mzqsnd.focus();
 					return false;
 				}
 				//if(mzqsnd.value > 2015)
 				//{
 					//alert("起始免征年度不能早于截止免征年度");
 					//mzqsnd.focus();
 					//return false;
 				//}
		 		return true;	
			}
 			
/*--------------------------按钮-------------------------------------*/
 			//返回
			function doReturn()
			{
				document.forms[0].action = "/shenbao/jmbaz.dc";
				document.forms[0].actionType.value="Show";
				document.forms[0].submit();
			}
			
			//保存
			function doSave(value)
			{
				//数据检查
				if(!checkYm())
				{
					return false;
   				}

   				if(!confirm("是否保存录入数据?"))
				{
					return false;
				}	
   				
   				//业务操作类型
				var old  = document.forms[0].ywczlx.value;
				document.forms[0].actionType.value="Save";
				
				if (g_objSI.Container != '')
				{ 
					if (!doSubmitXML(document.forms[0],"Save",true,"",true))
					{  
						document.forms[0].ywczlx.value = old;
						return false;
					}
				}
				else{  
					if(!doSubmitXML(document.forms[0],"Save",false,"",true))
					{  
						document.forms[0].ywczlx.value = old;
						return false;
					}			   
				}
				return true;
			}
			
			//生成备案表
			function doEditZb()
			{
				if(!checkYm() )
				{
					return false;
   				}
   				if(!confirm("是否生成备案表?"))
				{
					return false;
				}	
				
   				//操作类型
				var old  = document.forms[0].ywczlx.value;
				document.forms[0].actionType.value="EditZb";
				
				if (g_objSI.Container != '')
				{ 
					if (!doSubmitXML(document.forms[0],"EditZb",true,"",true))
					{  
						document.forms[0].ywczlx.value = old;
						return false;
					}
				}
				else
				{  
					if(!doSubmitXML(document.forms[0],"EditZb",false,"",true))
					{  
						document.forms[0].ywczlx.value = old;
						return false;
					}			   
				}
				return true;
			}
/*-----------------------------------------------------------*/
 			
 		//生成xml类型数据
		 function getPostXml(objForm)
		{
			var retstr;
			//基本数据
			getBasicXx("xsltVersion","/taxdoc");
			getBasicXx("schemaVersion","/taxdoc");
			getBasicXx("ywlx","/taxdoc");
			getBasicXx("ywczlx","/taxdoc");
			//纳税人信息
			applendElement("/taxdoc","nsrxx",["jsjdm","nsrmc","swjgzzjgdm"]);
		
			applendElement("/taxdoc","jmsbajl",["basqbh","basqwsh","band","jmbasxdm","jmbasxmc","ztdm","ztmc","szdm","szmc","lrrq"]);
			//申报数据
			getSbsj(objForm);	
			
			retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
			retstr = retstr.substr(0,retstr.length-2);
			return retstr;
		}
 		//生成申报数据
		function getSbsj(objForm)
		{   
		    var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc/jmsbajl");
			var objTemp = g_Doc.XMLDoc.createElement("qysdsjmba");
			objNode.appendChild(objTemp);
			getChildren(objTemp,"xh");
			getChildren(objTemp,"mzqsnd");
			getChildren(objTemp,"mzzznd");	
		}
 		
		function getChildren(temp,strTag)
		{
			var element=document.getElementById(strTag);
			var objTemp=temp;
			var node=g_Doc.XMLDoc.createElement(strTag);
			objTemp.appendChild(node);
			var strValue="";
			if(element!=null)
			{ 	
				strValue=formString(element.value);		
				var objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
				node.appendChild(objCDATA);
			}	
		}
		</script>

		<title>
	      	生产和装配伤残人员专门用品企业备案事项
	    </title>
	    
    </head>
    <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="parseXmlOnLoad()">
    	<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
   		 <tr>
   		 <td align="center" colspan=2 valign="top">
   		 
    			<jsp:include page="../../include/SbHeader.jsp" flush="true" >
    				<jsp:param name="name" value="录入纳税人企业所得税减免税备案申请" />
					<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    			</jsp:include>
    			
        <html:errors/>
        
        <form name="Form1" method="post" action="/shenbao/basx021.dc">
        <input name="actionType" type="hidden" id="actionType"></input>
        <table width="770" border="0" cellpadding="0" cellspacing="0" align="center" >
        <tr>
          <td valign="top" class="title">
            <br>
            <table width="75%" cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
              <tr>
                <td class="1-td1">
                 	 录入生产和装配伤残人员专门用品企业备案事项
                </td>
              </tr>
              
              <tr>
              <td class="1-td2">
                  
	                  <!-- form主体 -->
	                  <br>                 
						<div id="result"></div>
	                  <br>
              
              		<!-- 按钮 -->
	            	<table width="100%" border="0" align="center">
	                    <tr align="center"> 
	                    
	                    <!-- 保存 --> 
	                      <td>						                     
	                      	<img src="<%=static_contextpath%>images/baocun1.jpg" 
	                      		 onmouseover="this.src='<%=static_contextpath%>images/baocun2.jpg'" 
	                      		 onmouseout="this.src='<%=static_contextpath%>images/baocun1.jpg'" 
	                      		 alt="保存" onclick="return doSave()" style="cursor:hand">
	                      </td>
	                      
	                      <!-- 生成备案表 -->
	                      <td>
	                      	<img src="<%=static_contextpath%>images/b_scbab1.jpg" 
	                      		 onmouseover="this.src='<%=static_contextpath%>images/b_scbab2.jpg'" 
	                      		 onmouseout="this.src='<%=static_contextpath%>images/b_scbab1.jpg'" 
	                      		 alt="生成备案表" onclick="return doEditZb()" style="cursor:hand">
	                      </td>
	                      
	                      <!-- 返回 -->
	                      <td>
	                        <img src="<%=static_contextpath%>images/fanhui1.jpg" 
	                        	 onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" 
	                        	 onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" 
	                        	 alt="返回" onclick="doReturn()" style="cursor:hand">
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
    </form>
    
    <tr><td valign="bottom" align="center"><br>
	<jsp:include page="../../include/bottom.jsp" flush="true">
	</jsp:include></td>
	</tr>
  </body>

</html>
	