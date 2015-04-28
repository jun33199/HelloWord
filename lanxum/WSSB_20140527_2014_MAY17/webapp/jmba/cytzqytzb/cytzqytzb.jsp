<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);


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
<html>
<head>
<title>创业投资企业抵扣应纳税所得额备案事项</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>

<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">

<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
<script language="JavaScript">

var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';

var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION")==null?"":request.getAttribute("CA_XML_SCHEME_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
function parseXmlOnLoad()
{
	var xslPath='jmba/cytzqytzb/cytzqytzb.xsl';

	if (strXml != "")
    {
        //alert(strXml);
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    return true;
}

function doReturn()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="Show";
	document.forms[0].submit();
}



function doAdd(){
    //alert("add");
    document.forms[0].actionType.value="Add";
    document.forms[0].submit();
}

function doCommit(){
	var old  = document.forms[0].ywczlx.value;
	if (confirm(confirmSave))
	{
		 if (document.forms[0].ywczlx.value == 0)
		{
			document.forms[0].ywczlx.value = 1;
		}
		else if (document.forms[0].ywczlx.value == 1)
		{
			document.forms[0].ywczlx.value = 2;
		}
		document.forms[0].actionType.value="Save";
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
			   if(!doSubmitXML(document.forms[0],"Save",false,"",true))
				{
					 document.forms[0].ywczlx.value = old;
					return false;
			   }
			}
		return true;
	}else
	{
		return false;
	}
}

function doDel(swjdjzh,lxdm){
    //alert(swjdjzh+"  "+lxdm);
    if(confirm("请确认删除")){
        document.forms[0].swdjzh.value=swjdjzh;
        document.forms[0].lxdm.value=lxdm;
        document.forms[0].actionType.value="Del";
        document.forms[0].submit();
    }
}

function doEditor(btzjsj,swjdjzh,lxdm,zcbabs){
    //alert(btzjsj +" "+ swjdjzh+" "+lxdm+" "+zcbabs);
    document.forms[0].btzjsjdm.value=btzjsj;
    document.forms[0].swdjzh.value=swjdjzh;
    document.forms[0].lxdm.value=lxdm;
    document.forms[0].zcbabs.value=zcbabs;
    document.forms[0].zcbashbj.value="1";
    document.forms[0].actionType.value="Editor";
    document.forms[0].submit();
}

function doZcba(wnwsh,btzjsj,swjdjzh,lxdm,zcbabs){
    alert(wnwsh);
    document.forms[0].btzjsjdm.value=btzjsj;
    document.forms[0].swdjzh.value=swjdjzh;
    document.forms[0].lxdm.value=lxdm;
    document.forms[0].wnwsh.value=wnwsh;
    document.forms[0].zcbabs.value=zcbabs;
    document.forms[0].zcbashbj.value="0";
    document.forms[0].actionType.value="Editor";
    document.forms[0].submit();
}


</script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="parseXmlOnLoad()">
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2 valign="top">
    	<jsp:include page="../../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="创业投资企业抵扣应纳税所得额备案事项" />
		<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    	</jsp:include>
        <html:errors/>

<form name="form1" method="POST" action="/shenbao/cytzqytzb14.dc">
    <input name="actionType" type="hidden" id="actionType">
    <input name="swdjzh" type="hidden" id="swdjzh">
    <input name="wnwsh" type="hidden" id="wnwsh">
    <input name="lxdm" type="hidden" id="lxdm">
    <input name="btzjsjdm" type="hidden" id="btzjsjdm">
    <input name="zcbabs" type="hidden" id="zcbabs">
    <input name="zcbashbj" type="hidden" id="zcbashbj">
    <input name="gxjsly" type="hidden" id="gxjsly">
<TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9'>
 <tr>
    <td valign="top">
      <table align="center" cellspacing="0" class="table-99">
<tr>
                <td class="1-td1">
                    创业投资企业抵扣应纳税所得额备案事项
                </td>
              </tr>
              <tr>
                <td class="1-td2">

                  <br>
				<div id="result"></div>
            </td>
    </tr>
    <tr>

                  <table width="100%" border="0" align="center">
                    <tr align="center">

                    	<td >
                      	<img src="<%=static_contextpath%>images/xinzeng1.jpg"  name="shanchu11" width="79" height="22" border="0" id="shanchu11"  alt="新增"

onclick="javascript:return doAdd();">
                      </td>


                      <td >
                      	<img src="<%=static_contextpath%>images/tijiao2.jpg" name="shanchu11" width="79" height="22" border="0" id="shanchu11" alt="提交"

onclick="javascript:return doCommit();">
                      </td>

                      <td >
                        <img onclick="javascript:return doReturn();" alt="退出" name="tc1" width="79" height="22" border="0" id="tc1" src="<%=static_contextpath%>images/fanhui2.jpg">
                      </td>
                    </tr>
                  </table>
            <br>
           <table width="99%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td>
                        <hr width="100%" size="1" style="color='#003399'">
                      </td>
                      <td width="99" align="center" class="black9">
                        <strong>
                          <font color="#0000FF">
                            注 意 事 项
                          </font>
                        </strong>
                      </td>
                      <td>
                        <hr width="100%" size="1" style="color='#003399'">
                      </td>
                    </tr>
                  </table>
                  <table width="99%" border="1" align="center" cellpadding="1" cellspacing="1"
                  bordercolor="#FFFFFF" class="black9">
                    <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
                      <td height="47">
												<br>&nbsp;&nbsp;<font color="#FF0000">企业在填写2009年数据时，应补填2006－2008年相关数据，从2010年开始，只填写当年数据。需要确认需求中关于此段的描述</font>
												<br>&nbsp;&nbsp;<font color="#FF0000">对应年抵扣额是否可录需要判断对应年-2 是否有投资额</font>
												<br>&nbsp;&nbsp;<font color="#FF0000">针对每个被投资企业的计算规则：抵扣数额上限=（含系统年度-3及之前年度 的投资额合计）*70% - （含系统年度-2及之前年度 的抵扣额合计）校验时允许有偏差（+-5）</font>
												<br>&nbsp;&nbsp;<font color="#FF0000">本次上线的主页面只展示最近四年的数据，以后年度增加时顺序往后推一年</font>
												<br>
                        <br>
                        &nbsp;&nbsp;1、纳税人计算机代码：手工录入创业投资企业投资的被投资企业计算机代码
                        <br>
                        &nbsp;&nbsp;2、纳税人名称：手工录入创业投资企业投资的被投资企业名称
                        <br>
                        &nbsp;&nbsp;3、2007年、2008年、2009年投资额：手工录入创业投资企业采取股权投资方式投资于未上市的中小高新技术企业的投资额
                        <br>
                        &nbsp;&nbsp;4、2008年抵扣应纳税所得额：手工录入创业投资企业在2008年按投资额的70%抵扣当年的应纳税所得额，为纳税人自行填写
                        <br>
                        &nbsp;&nbsp;5、2009年抵扣应纳税所得额：手工录入创业投资企业在2009年按投资额的70%抵扣当年的应纳税所得额，为纳税人自行填写
                        <br>
                        &nbsp;&nbsp;6、当年：为系统年度-1，自动带出
                        <br>
                        &nbsp;&nbsp;7、当年投资额：填写创业投资企业采取股权投资方式投资于未上市的中小高新技术企业的投资额，为纳税人自行填写
                        <br>
                        &nbsp;&nbsp;8、当年抵扣应纳税所得额：填写创业投资企业在当年按投资额的70%抵扣当年的应纳税所得额，为纳税人自行填写
                        <br>
                        &nbsp;&nbsp;注：企业在填写2009年数据时，应补填2006－2008年相关数据，从2010年开始，只填写当年数据。
                        <br>
                        <br>
                        &nbsp;&nbsp;3.1 以创业投资企业抵扣应纳税所得额资格备案填写完全后才能填写此表
                        <br>
                        &nbsp;&nbsp;3.2 纳税人只有提交备案申请并经税务机关审核成功后方可开放附表5第39行 ，允许在汇算清缴时填写数据，否则，对应行次系统默认为不允许编辑状态
                      </td>
                    </tr>
                  </table>
            <br> </td>
					</tr>
				</table>
    </tr>
  </table>
    </td>
 </tr>
</table>
</form>
 </td>
</tr>
<tr><td valign="bottom" align="center">
<br>
<jsp:include page="../../include/bottom.jsp" flush="true" >
</jsp:include>
 </td>
</tr>
</table>
</body>
</html>
