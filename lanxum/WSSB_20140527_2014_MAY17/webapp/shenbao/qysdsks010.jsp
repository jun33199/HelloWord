<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%
    String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>
北京市企业所得税季度纳税申报表
</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type = "text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>

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
g_objSI.Container = "<%=containerName%>";
var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMAVersion = '<%=request.getAttribute("CA_XML_SCHEMA_VERSION")==null?"":request.getAttribute("CA_XML_SCHEMA_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var xmlDoc;

//解析xml
function parseXmlOnLoad()
{
	var urlXSL="/XSLTWEB/model/010009/XSLT/" +strXSLTVersion+".xsl";
    if (strXml != "")
    {
        if (! parseXml(strXml,urlXSL,"result"))
            return false;
	xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
	xmlDoc.async = false;
	xmlDoc.loadXML(strXml);
    }

    return true;
}


//生成申报数据xml
function getSbsj(objForm)
{
	var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc");
	var objTemp = g_Doc.XMLDoc.createElement("sbsj");
	objNode.appendChild(objTemp);

	for(var i=20;i<32;i++)
	{
		strName=objForm.elements(i).name;
		strValue=objForm.elements(i).value;
		node2=g_Doc.XMLDoc.createElement(strName);
		objTemp.appendChild(node2);
		var objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
		node2.appendChild(objCDATA);
	}
}
//验证数据的合法性
function formatData(obj)
{
	return (checkvalue(obj,1) && formatCurrency(obj,0) && compute(obj));
}
function formatData2(obj)
{
	return (checkvalue(obj,0) && formatCurrency(obj,0) && compute(obj));
}
function formatData3(obj)
{
	return (checkvalue(obj,0) && formatCurrency(obj,0) && jmsdseChangeCompute(obj));
}
function formatData4(obj)
{
	if(checkvalue(obj,0)&&formatCurrency(obj,0))
    {
        return jmsdseChangeCompute(obj);
    }
    else
    {
        return false;
    }
}
<%/*保存*/%>
function doSave()
{
		var old  = document.forms[0].ywczlx.value;
    if(parseFloat(document.forms[0].sjybsdse.value) > 9999999999999.99 ||
       parseFloat(document.forms[0].sjybsdse.value) < -9999999999999.99)
    {
        alert("实际应补（退）所得税额的数值超过允许的范围！");
        return false;
    }
    if(parseFloat(document.forms[0].sjybsdse.value)!=0)
    {
        if(confirm(confirmSave))
        {	
            return SaveExec(old);
			
        }
    }
    else if(confirm(confirmSaveZero))
    {
        return SaveExec(old);
    }
    return false;
}
function SaveExec(old)
{
    if (document.forms[0].ywczlx.value == 0)
    {
        document.forms[0].ywczlx.value = 1;
    }
    else if (document.forms[0].ywczlx.value == 1)
    {
        document.forms[0].ywczlx.value = 2;
    }
    else
    {
        alert("未知的操作类型：" + document.forms[0].ywczlx.value);
        return false;
    }

		document.forms[0].actionType.value="Save";
		if (g_objSI.Container != '')
		{
				if (!doSubmitXML(document.forms[0],"Save",true,"",true))
					{
							document.forms[0].ywczlx.value = old;
							return false;
					}	
		}else
		{
				if (!doSubmitXML(document.forms[0],"Save",false,"",true))
					{
							document.forms[0].ywczlx.value = old;
							return false;
					}	
		}
		return true;
}
<%/*返回*/%>
function doReturn()
{
    if(confirm(confirmReturn))
    {
		document.forms[0].actionType.value="Return";
			document.forms[0].submit();
				return true;
    }else
    {
    		return false;
    }
}
<%/*删除*/%>
function doDelete()
{
		var old  = document.forms[0].ywczlx.value;
    if((document.forms[0].ywczlx.value == 1) && confirm(confirmDelete))
    {
				document.forms[0].actionType.value="Delete";
  			changeLocalYwczlx("3");
				if (g_objSI.Container != '')
				{
						if (!doSubmitXML(document.forms[0],"Delete",true,xmlDoc.xml,true))
						{
							changeLocalYwczlx(old);
							return false;
						}
				}else
				{	
						if (!doSubmitXML(document.forms[0],"Delete",false,xmlDoc.xml,true))
						{
							changeLocalYwczlx(old);
							return false;
						}	
				}
				return true;
   }else
   {
   			return false;
   }
}
function changeLocalYwczlx(ywczlx)
{
	 var rootNode = xmlDoc.documentElement;
    var objCDATA =xmlDoc.createCDATASection(ywczlx);
		rootNode.selectSingleNode("//ywczlx").text = "";
		rootNode.selectSingleNode("//ywczlx").appendChild(objCDATA);
}
<%/*自动计算*/%>
function jmsdseChangeCompute(obj)
{
   var sjybsdse = 0.00;
   sjybsdse = parseFloat(document.forms[0].ynsdse.value) + parseFloat(document.forms[0].qcwjsdse.value) -  parseFloat(document.forms[0].jmsdse.value) + parseFloat(document.forms[0].cbyqndse.value) - parseFloat(document.forms[0].sjyjnssdse.value) - parseFloat(document.forms[0].bqyjsdse.value);

   document.forms[0].sjybsdse.value = Math.round(sjybsdse*100)/100;
   formatCurrency(document.forms[0].sjybsdse, 0);
   return true;
}
function computeSl(jd, lrze)
{
    if (jd == "1")
    {
        var yearLrze = parseFloat(lrze) * 4;
    }
    else if (jd == "2")
    {
        var yearLrze = parseFloat(lrze) * 2;
    }
    else if (jd == "3")
    {
        var yearLrze = Math.round(parseFloat(lrze) * 4 / 3 * 100) / 100;
    }
    else if (jd == "4")
    {
        var yearLrze = parseFloat(lrze);
    }

    if(yearLrze <= 30000.00)
    {
        sl = 0.18;
    }
    else if(yearLrze > 30000.00  &&  yearLrze <= 100000.00)
    {
        sl = 0.27;
    }
    else
    {
        sl = 0.33
    }

    return sl;
}

<%/*自动计算*/%>
function compute(obj)
{
   var jd = document.forms[0].jd.value;
   var jmzg = document.forms[0].jmzg.value;
   var cyl = document.forms[0].cyl.value;
   var qyzslx = document.forms[0].qyzslx.value;
   var jmsdse = document.forms[0].jmsdse.value;
   var ybjmsl = document.forms[0].ybjmsl.value;
   var xzqy = document.forms[0].xzqy.value;
   var sl = document.forms[0].sl.value;
   var lrze = 0.00;
   var ynsdse = 0.00;
   var bkhlrze =0.00;
   var sjybsdse = 0.00;
   if(qyzslx == "1")//高新技术企业
   {
      lrze = document.forms[0].lrze.value;

   if(obj == eval("document.forms[0].lrze")||obj == eval("document.forms[0].srze")||obj == eval("document.forms[0].mbyqndks"))
   {
   if(lrze == null||lrze == ''||lrze < 0)
   {
       document.forms[0].mbyqndks.value = 0.00;
   }
      bkhlrze = parseFloat(lrze) - parseFloat(document.forms[0].mbyqndks.value);
   }else{
      bkhlrze = document.forms[0].bkhlrze.value;
   }
		ynsdse = parseFloat(bkhlrze)*parseFloat(sl);

	/*  if(obj == eval("document.forms[0].lrze")||obj == eval("document.forms[0].srze"))
	  {
		ynsdse = parseFloat(lrze)*parseFloat(sl);
	  }
	  else
	  {
		ynsdse = document.forms[0].ynsdse.value;
	  }*/
      if(jmzg =="1"){
        if(xzqy =="1")
        {
           jmsdse = ynsdse * 0.10;
        }else{
		   jmsdse = Math.round(ynsdse*parseFloat(ybjmsl)*100)/100;
        }
      }
   }
   else if(qyzslx == "2")  //纯益率征收
   {
	   if(obj == eval("document.forms[0].srze"))
	   {
		lrze =  Math.round(parseFloat(document.forms[0].srze.value)*parseFloat(cyl)*100)/100;
	   }
	   else
	   {
			lrze = document.forms[0].lrze.value;
		}
	  /*if(obj == eval("document.forms[0].lrze")||obj == eval("document.forms[0].srze"))
	  {
		ynsdse = parseFloat(lrze)*parseFloat(sl);
	  }
	  else
	  {
		ynsdse = document.forms[0].ynsdse.value;
	  }*/
   if(obj == eval("document.forms[0].lrze")||obj == eval("document.forms[0].srze")||obj == eval("document.forms[0].mbyqndks"))
   {
   if(lrze == null||lrze == ''||lrze < 0)
   {
       document.forms[0].mbyqndks.value = 0.00;
   }
      bkhlrze = parseFloat(lrze) - parseFloat(document.forms[0].mbyqndks.value);
   }else{
      bkhlrze = document.forms[0].bkhlrze.value;
   }
      sl = computeSl(jd, bkhlrze);
		ynsdse = parseFloat(bkhlrze)*parseFloat(sl);
      if(jmzg =="1"){
        if(xzqy =="1")
        {
           jmsdse = ynsdse * 0.10;
        }else{
		   jmsdse = Math.round(ynsdse*parseFloat(ybjmsl)*100)/100;
        }
      }
   }
   else if(qyzslx == "3")//正常申报
   {
      lrze = document.forms[0].lrze.value;
      /*sl = computeSl(jd, lrze);
	  if(obj == eval("document.forms[0].lrze")||obj == eval("document.forms[0].srze"))
	  {
		ynsdse = parseFloat(lrze)*parseFloat(sl);
	  }
	  else
	  {
		ynsdse = document.forms[0].ynsdse.value;
	  }*/

   if(obj == eval("document.forms[0].lrze")||obj == eval("document.forms[0].srze")||obj == eval("document.forms[0].mbyqndks"))
   {
   if(lrze == null||lrze == ''||lrze < 0)
   {
       document.forms[0].mbyqndks.value = 0.00;
   }
      bkhlrze = parseFloat(lrze) - parseFloat(document.forms[0].mbyqndks.value);
   }else{
      bkhlrze = document.forms[0].bkhlrze.value;
   }
      sl = computeSl(jd, bkhlrze);
		ynsdse = parseFloat(bkhlrze)*parseFloat(sl);

      if(jmzg =="1"){
        if(xzqy =="1")
        {
           jmsdse = ynsdse * 0.10;
        }else{
		   jmsdse = Math.round(ynsdse*parseFloat(ybjmsl)*100)/100;
        }
      }
   }
   else if(qyzslx == "5")//高新技术和纯益率企业
   {
	   if(obj == eval("document.forms[0].srze"))
	   {
		lrze =  Math.round(parseFloat(document.forms[0].srze.value)*parseFloat(cyl)*100)/100;
	   }
	   else
	   {
			lrze = document.forms[0].lrze.value;
		}

	  /*if(obj == eval("document.forms[0].lrze")||obj == eval("document.forms[0].srze"))
	  {
		ynsdse = parseFloat(lrze)*parseFloat(sl);
	  }
	  else
	  {
		ynsdse = document.forms[0].ynsdse.value;
	  }*/

   if(obj == eval("document.forms[0].lrze")||obj == eval("document.forms[0].srze")||obj == eval("document.forms[0].mbyqndks"))
   {
   if(lrze == null||lrze == ''||lrze < 0)
   {
       document.forms[0].mbyqndks.value = 0.00;
   }
      bkhlrze = parseFloat(lrze) - parseFloat(document.forms[0].mbyqndks.value);
   }else{
      bkhlrze = document.forms[0].bkhlrze.value;
   }
		ynsdse = parseFloat(bkhlrze)*parseFloat(sl);

      if(jmzg =="1"){
        if(xzqy =="1")
        {
           jmsdse = ynsdse * 0.10;
        }else{
		   jmsdse = Math.round(ynsdse*parseFloat(ybjmsl)*100)/100;
        }
      }
   }
   else
   {
      lrze = document.forms[0].lrze.value;
      bkhlrze = document.forms[0].bkhlrze.value;
      ynsdse = document.forms[0].ynsdse.value;
   }

   if(ynsdse == null||ynsdse == ''||ynsdse < 0)
   {
       ynsdse = 0.00;
   }
   if(jmsdse == null||jmsdse == ''||jmsdse < 0)
   {
       jmsdse = 0.00;
   }

   if(lrze == null||lrze == ''||lrze < 0)
   {
       document.forms[0].mbyqndks.value = 0.00;
   }

   var sjyjnsdse = document.forms[0].sjyjnssdse.value;
   if(sjyjnsdse == null|| sjyjnsdse == '')
   {
       sjyjnsdse = 0.00;
   }
   document.forms[0].jmsdse.value = Math.round(jmsdse*100)/100;
   document.forms[0].lrze.value = lrze;
   sjybsdse = parseFloat(ynsdse) + parseFloat(document.forms[0].qcwjsdse.value) -  parseFloat(document.forms[0].jmsdse.value) + parseFloat(document.forms[0].cbyqndse.value) - parseFloat(document.forms[0].sjyjnssdse.value) - parseFloat(document.forms[0].bqyjsdse.value);
   document.forms[0].sl.value = sl;
   document.forms[0].bkhlrze.value = Math.round(bkhlrze*100)/100;
   document.forms[0].ynsdse.value = Math.round(ynsdse*100)/100;
   document.forms[0].sjybsdse.value = Math.round(sjybsdse*100)/100;
   formatCurrency(document.forms[0].bkhlrze,0);
   formatCurrency(document.forms[0].ynsdse,0);
   formatCurrency(document.forms[0].jmsdse,0);
   formatCurrency(document.forms[0].lrze,0);
   formatCurrency(document.forms[0].mbyqndks,0);
   formatCurrency(document.forms[0].sjybsdse, 0);

   return true;
}
function checkJksh(obj)
{
    if(obj.value==null || obj.value.length==0)
    {
        return true;
    }
    else if(obj.value.length<15||obj.value.length>16)
    {
        alert("缴款书号输入值错误！(缴款书号必须为15位或16位的数值)");
        obj.focus();
        obj.select();
        return false;
    }
    else
    {
        for(var loc=0;loc<obj.value.length;loc++)
        {
            if(obj.value.charAt(loc) < '0' || obj.value.charAt(loc) > '9')
            {
                 alert("缴款书号输入值错误！(缴款书号必须为15位或16位的数值)");
                 obj.focus();
                 obj.select();
                 return false;
            }
        }
    }
    return true;
}
</script>

<link href="<%=static_contextpath%>/css/text.css" rel="stylesheet" type="text/css">
<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
</head>

<body bgcolor="#ffffff" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onLoad="return parseXmlOnLoad();">

<table width="98%" height="90%" border='0' cellpadding='0' cellspacing='0' align='center' class='black9'>
  <tr>
    <td align="center" valign="top">
        <jsp:include page="../include/SbHeader.jsp" flush="true" >
            <jsp:param name="name" value="北京市企业所得税季度纳税申报表" />
        <jsp:param name="help_url" value="help/wssb/sbzl/lygfzwhztz/qysdsjd/qysdsjd-000.htm"/>
        </jsp:include>
    </td>
  </tr>
  <tr>
	<td align="center" valign="top"><html:errors/>
	</td>
  </tr>
  <tr>
	<td valign="middle">
	   <form name="form1" method="post" action="qysdsks.dc">
	<input name="actionType" type="hidden" id="actionType" value="Show">
		<table id="maintable" align="center" cellspacing="0" class="table-99">
			 <tr>
				 <td class="1-td1">企业所得税季度纳税申报表</td>
			 </tr>
			 <tr>
				<td class="1-td2"><div id="result" align="center"></div></td>
			 </tr>
			 <tr><td class="1-td2">
		        <table>
		        <br>
			    <tr align="center">
                  <td align="center">
                  <div align="center">
					<img src="<%=static_contextpath%>images/baocun1.jpg" onmouseover="this.src='<%=static_contextpath%>images/baocun2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/baocun1.jpg'" alt="保存" onclick="doSave();" style="cursor:hand">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<img src="<%=static_contextpath%>images/shanchu1.jpg" onmouseover="this.src='<%=static_contextpath%>images/shanchu2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/shanchu1.jpg'" alt="删除" onclick="doDelete();" style="cursor:hand">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onclick="doReturn()" style="cursor:hand">
                  </div>
                  </td>

					</tr>
				</table>
			 </td></tr>
		</table>
	  </form>
	</td></tr>
 <tr>
	<td valign="bottom" align="center">
    <br><br>
	<%@ include file="../include/bottom.jsp" %>
    </td>
  </tr>
</table>

</body>
</html>
