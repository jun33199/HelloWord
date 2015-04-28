<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%
  String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>

<html>
<head>
<title>
印花税年度纳税申报表
</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type = "text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>

<script language=JavaScript>
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
var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION")==null?"":request.getAttribute("CA_XML_SCHEME_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';

//解析xml
function parseXmlOnLoad()
{
    var xslPath="/XSLTWEB/model/010015/XSLT/" +strXSLTVersion+".xsl";
    if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    return true;
}
//生成申报数据
function getSbsj(objForm)
{
  var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc");
  var objTemp = g_Doc.XMLDoc.createElement("sbsjlist");
  objNode.appendChild(objTemp);
  var total=document.forms(0).length-3;
  outer:
  for(var j=13;j<total;)
  {
    objNode=g_Doc.XMLDoc.createElement("sbsj");
    objTemp.appendChild(objNode);
    for(var i=j;i<j+6;i++)
    {
      var strName=objForm.elements(i).name;
      //alert(strName);
      strValue=objForm.elements(i).value;
      node2=g_Doc.XMLDoc.createElement(strName);
      objNode.appendChild(node2);
      var objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
      node2.appendChild(objCDATA);
    }
    j+=6;
  } 
  getChildren(objTemp,"hjfs");
  getChildren(objTemp,"hjjsje");
  getChildren(objTemp,"hjynse");
}

<%/*保存*/%>
function doSave()
{
  var old  = document.forms[0].ywczlx.value;
  if(checkZero())
  {
    if(confirm(confirmSave))
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
      if (g_objSI.Container != '')
      {
        if (!doSubmitXML(document.forms[0],"Save",true,"",true))
        {
          document.forms[0].ywczlx.value = old;
          return false;
        }
      }
      else
      {
        if (!doSubmitXML(document.forms[0],"Save",false,"",true))
        {
          document.forms[0].ywczlx.value = old;
          return false;
        }
      }
      return true;
    }
    else
    {
      return false;
    }
  }
  else if(confirm(confirmSaveZero))
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
    //document.forms[0].submit();
    //return true;
    if (g_objSI.Container != '')
      {
        if (!doSubmitXML(document.forms[0],"Save",true,"",true))
        {
          document.forms[0].ywczlx.value = old;
          return false;
        }
      }
      else
      {
        if (!doSubmitXML(document.forms[0],"Save",false,"",true))
        {
          document.forms[0].ywczlx.value = old;
          return false;
        }
      }
      return true;
  }
    else
    {
         return false;
    }
}
//对数据进行验证
function formatFs1(obj)
{ 
  return(checkvalue(obj,0)&&checkFs(obj)&&computerFsHJ(obj)); 
}
function formatFs2(obj,i)
{
  //alert(i+"......."+document.forms[0].fs[i].value);
  return(checkvalue(obj,0)&&checkFs(obj)&&computerFsChange(obj,i)&&computerFsHJ(obj)); 
}
function formatJsje(obj,i)
{
  //alert(i+"......."+document.forms[0].jsje[i].value);
  return (checkvalue(obj, 0)&&formatCurrency(obj,0)&&checkJsje(obj)&&computerJsjeChange(obj, i));
}
function formatSl(obj,i)
{   
  //alert(i+"......."+document.forms[0].sl[i].value);
  return (checkvalue(obj,0)&&checkSl(obj)&&checkJsje(obj)&&computerJsjeChange(obj,i))
}

function formatYnse(obj)
{
  return (checkvalue(obj,0)&&formatCurrency(obj,0)&&computerYnseHj(obj));
}

<%/*清除*/%>
function doClear()
{
    return true;
}
<%/*返回*/%>
function doReturn()
{
    if(confirm(confirmReturn))
    {
        //document.forms[0].submit();
    if (!doSubmitXML(document.forms[0],"Return",false,"return",true))
    {
      return false;
    }
        return true;
    }
    else
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
    changeYwczlx("3");
    if (g_objSI.Container != '')
      {
        if (!doSubmitXML(document.forms[0],"Delete",true,g_Doc.parseXmlDoc.xml,true))
        {
          changeYwczlx(old);
          return false;
        }
      }
      else
      {
        if (!doSubmitXML(document.forms[0],"Delete",false,g_Doc.parseXmlDoc.xml,true))
        {
          changeYwczlx(old);
          return false;
        }
         return true;
      }
    }
    else
    {
        return false;
    }
}
<%/*自动计算1*/%>
function computerJsjeChange(obj, i)
{
        var jsje = document.forms[0].jsje[i].value;
        var sl = document.forms[0].sl[i].value;
        if(jsje == null || jsje == "" )
        {
            jsje = 0.00;
        }
        else if(!checkvalue(document.forms[0].jsje[i],0))
        {
            jsje = 0.00;
        }
        if(sl == null || sl == "" )
        {
            sl = 0.00;
        }
        else if(!checkvalue(document.forms[0].sl[i],0))
        {
            sl = 0.00;
        }

        var ynse = parseFloat(jsje) * parseFloat(sl);
  document.forms[0].ynse[i].value = Math.round(ynse*100)/100;
        formatCurrency(document.forms[0].ynse[i],0);
        computerYnseHj(obj);
        var hjjsje = 0;
        for(t=0; t<12; t++)
        {
             var tmpJsje = document.forms[0].jsje[t].value;
             if(tmpJsje != null && tmpJsje != "" )
             {
                   if(checkvalue(document.forms[0].jsje[t], 0))
                   {
                       hjjsje = parseFloat(hjjsje) + parseFloat(tmpJsje);
                   }
             }

        }
             var tmpJsje2 = document.forms[0].jsje[14].value;
             if(tmpJsje2 != null && tmpJsje2 != "" )
             {
                   if(checkvalue(document.forms[0].jsje[14], 0))
                   {
                       hjjsje = parseFloat(hjjsje) + parseFloat(tmpJsje2);
                   }
             }
             if(Math.round(hjjsje*100)/100>9999999999999.99)
             {
               alert("计税金额的合计超过允许的最大值！");
               obj.focus();
               obj.select();
               return false;
             }
             else
             {
                document.forms[0].hjjsje.value = Math.round(hjjsje*100)/100;
                formatCurrency(document.forms[0].hjjsje, 0);
             }
}
function computerFsChange(obj,i)
{
       var fs = document.forms[0].fs[i].value;
       var sl = document.forms[0].sl[i].value;
       if(fs == null || fs == "")
        {
            fs = 0;
        }
        else if(!checkvalue(document.forms[0].fs[i],0))
        {
            fs = 0;
        }
        if(sl == null || sl == "" )
        {
            sl = 0;
        }
        else if(!checkvalue(document.forms[0].sl[i],0))
        {
            sl = 0.00;
        }
       var ynse = parseFloat(fs) *parseFloat(sl);
       document.forms[0].ynse[i].value =   Math.round(ynse*100)/100;
       formatCurrency(document.forms[0].ynse[i], 0);
       computerYnseHj(obj);
       return true;
}

function computerFsHJ(obj)
{
       var hjfs = 0;
       for(i=0; i<15; i++)
       {
          var fs = document.forms[0].fs[i].value;
          if(fs!=null && valueTrim(fs)!=0)
          {
              if(checkvalue(document.forms[0].fs[i], 0))
              {
                     hjfs = parseFloat(hjfs) + parseFloat(fs);
              }
          }
       }
       if(parseFloat(hjfs)>9999999999999)
       {
               alert("份数的合计超过允许的最大值！");
               obj.focus();
               obj.select();
               return false;
       }
       else
       {
             document.forms[0].hjfs.value = hjfs;
       }
}
function computerYnseHj(obj)
{

       var hjynse = 0;

       for(i=0; i<15; i++)
       {
          var ynse = document.forms[0].ynse[i].value;
          if(ynse!=null && ynse!="")
          {
              if(checkvalue(document.forms[0].ynse[i], 0))
              {
                     hjynse = parseFloat(hjynse) + parseFloat(ynse);
              }
          }
       }

       if(parseFloat(Math.round(hjynse*100)/100)>9999999999999.99)
       {
               alert("已纳税额的合计超过允许的最大值！");
               obj.focus();
               obj.select();
               return false;
       }
       else
       {
               document.forms[0].hjynse.value = Math.round(hjynse*100)/100;
               formatCurrency(document.forms[0].hjynse);
       }
}
//删除字符串前后的空格
function valueTrim(str)
{
   if (str == "") return "";

    while(str.substring(0,1)==' ')
        str=str.substring(1,str.length);
    while(str.substring(str.length-1,str.length)==' ')
    str=str.substring(0,str.length-1);
    return str;
}

function checkFs(obj)
{
    var value = valueTrim(obj.value);
    if(value!=null&&value.length!=0)
    {
        for(var loc=0;loc<value.length;loc++)
  {
    if(value.charAt(loc) < '0' || value.charAt(loc) > '9')
    {
                       alert("份数必须为正整数！");
                       obj.focus();
                       obj.select();
                       return false;
    }
  }
        if(parseFloat(value)>9999999999999)
        {
                alert("输入的数值太大！");
                obj.focus();
                obj.select();
                return false;
       }
    }
     return true;
}

function checkJsje(obj)
{
   if(obj.value!=null&&valueTrim(obj.value).length!=0)
   {
     if(parseFloat(obj.value)>9999999999999.99)
     {
                alert("输入的数值太大！");
                obj.focus();
                obj.select();
                return false;
     }
   }
     return true;
}
function checkZero()
{
    for(var i=0; i<document.forms[0].ynse.length; i++)
    {
         if(parseFloat(document.forms[0].ynse[i].value)==0)
         {
              return false;
         }
    }
    return true;
}
function checkSl(obj)
{
  var tmpValue = obj.value;
  var pointIndex = tmpValue.indexOf(".");
  if(pointIndex < 0)
  {
    if(tmpValue == null || tmpValue == "" || tmpValue == ".")
    {
      tmpValue = "0.00";
    }
    else
    {
      tmpValue += ".00";
    }
  }
  else if(pointIndex == 0)
  {
    if(tmpValue.length > 1)
    {
      if(tmpValue.substring(1).length > 5)
      {
        alert("小数点后不能大于五位！");
        obj.focus();
        obj.select();
        return false;
      }
      else if(tmpValue.substring(1).length == 1)
      {
        tmpValue = "0" + tmpValue + "0";
      }
      else
      {
        tmpValue = "0" + tmpValue;
      }
    }
    else
    {
      tmpValue = "0.00";
    }
  }
  else
  {
    afterpoint = (tmpValue.length-1) - pointIndex;
    if(afterpoint == 0)
    {
      tmpValue += "00";
    }
    else if(afterpoint > 5)
    {
      //tmpValue = tmpValue*100;
      //tmpValue = Math.floor(tmpValue);
      //var m = tmpValue % 100;
      //tmpValue = tmpValue/100;
      //if(m == 0)
      //{
      //  tmpValue += ".00";
      //}
      alert("小数点后不能大于五位！");
      obj.focus();
      obj.select();
      return false;
    }
    else if(afterpoint == 1)
    {
      tmpValue += "0";
    }
  }
  if(tmpValue > 99999.99999)
  {
    alert("您输入的数字过大！");
    obj.focus();
    obj.select();
    return false;
  }
  obj.value = tmpValue;
  //return obj;
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
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="parseXmlOnLoad();">
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2>

      <jsp:include page="../include/SbHeader.jsp" flush="true" >
        <jsp:param name="name" value="印花税年度纳税申报表" />
        <jsp:param name="help_url" value="help/wssb/sbzl/yhs/yhs-000.htm"/>
      </jsp:include>
     </td>
  </tr>

  <tr>
    <td valign="top">
            <html:errors/>
    </td>
  </tr>
  <form name="form1" method="POST" action="/shenbao/yhs.dc">
  <tr>
    <td>
      <br>
      <table  align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1">印花税年度纳税申报表</td>
        </tr>
        <tr>
          <td class="1-td2">
            <table align="center" cellspacing="0" class="table-99">
             <tr class="black9">
               <td align="center" nowrap>
                 <div id="result"></div>
              </td>
             </tr>
            </table>  
            <br>
            <table>
              <tr>
                <td>
					<img src="<%=static_contextpath%>images/baocun1.jpg" onmouseover="this.src='<%=static_contextpath%>images/baocun2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/baocun1.jpg'" alt="保存" onclick="return doSave()" style="cursor:hand">
					&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
                <td>
					<img src="<%=static_contextpath%>images/shanchu1.jpg" onmouseover="this.src='<%=static_contextpath%>images/shanchu2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/shanchu1.jpg'" alt="删除" onclick="doDelete();" style="cursor:hand">
                    &nbsp;&nbsp;&nbsp;&nbsp;
                </td>
                <td>
					<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onclick="doReturn()" style="cursor:hand">
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
  </form>
  <tr>
    <td valign="bottom" align="center">
      <%@ include file="../include/bottom.jsp" %>
    </td>
  </tr>
</table>
</body>
</html>