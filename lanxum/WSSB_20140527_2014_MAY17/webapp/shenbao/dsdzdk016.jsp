<%@page contentType="text/html; charset=GBK"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>

<html>
<head>
<title>�������۴��ɵ���</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<script language=JavaScript>

function savesbdata()
{
	if(checksave(<bean:write name="dsdzdkForm" property="errTag"/>))
	{
		alert("���ݲ���ȷ�����ܻ��ܡ������µ������ݡ�");
		return false;
	}

	if(confirm("��ȷ�����浱ǰ���ݲ�������?"))
	{
		document.forms[2].actionType.value = "Save";
		document.forms[2].submit();
        return true;
	}
	else
	{
		return false;
	}
}

function queryHz()
{
	var maxpage = document.forms[0].maxpage.value
	document.forms[2].actionType.value = "QueryHz";
	if(maxpage != 0)
	{
		if (confirm("��ȷ�������浱ǰ�ϴ���������?"))
		{
			document.forms[2].submit();
			return true;
		}
		else
		{
			return false;
		}
	}
	else
	{
		document.forms[2].submit();
		return true;
	}
}

function exit()
{
	if (confirm("��ȷ��Ҫ�˳���?"))
	{
		document.forms[2].actionType.value = "Return";
		document.forms[2].submit();
		return true;
	}
	else
	{
		return false;
	}

}

function upLoad()
{
    var maxpage = document.forms[0].maxpage.value;
    var filevalue = document.forms[1].theFile.value;
    if(filevalue == null || filevalue == "" )
    {
    	alert("����ѡ������ļ�!");
		return false;
    }
	else
	{
		var arrfile = filevalue.split(".");
		if(arrfile.length<2 || arrfile[arrfile.length-1] != "xls")
		{
			alert("ֻ���ϴ�excel�ļ�!");
			return false;
		}
	}

	if(maxpage >0)
	{
		if (confirm("��ȷ�������浱ǰ�ϴ���������?"))
		{
			document.forms[1].actionType.value = "Upload";
			document.forms[1].submit();
		}
		else
		{
			return false;
		}
	}
	else
	{
		document.forms[1].actionType.value = "Upload";
		document.forms[1].submit();
    }
    return true;
}

function splitPage(viewPage)
{
    document.forms[0].actionType.value   = "SplitPage";
    document.forms[0].viewPageType.value = viewPage;
    document.forms[0].optJspTag.value  = "DR";
	document.forms[0].submit();
    return true;
}

function checksplit(imageindex)
{
	var curpage = document.forms[0].curpage.value
	var maxpage = document.forms[0].maxpage.value
	if((curpage ==0 && maxpage ==0) || maxpage ==1 )
	{
		return true;
	}
	else
	{
		if(curpage == 1 )
		{
			if(imageindex == 1 || imageindex ==2 )
			{
				return true;
			}
			else
			{
				return false;
			}
    	}
		else
		{
			if(curpage == maxpage)
			{
				if(imageindex == 3 || imageindex ==4)
				{
					return true;
				}
				else
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
	}
    return true;
}

function checksave(errTag)
{
	var curpage = document.forms[0].curpage.value
	var maxpage = document.forms[0].maxpage.value
	if(maxpage ==0 || errTag == 1)
	{
		return true;
    }
	else
	{
		return false;
	}
}

</script>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">


<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2>

    	<jsp:include page="../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="�������۴��ɵ���" />
		<jsp:param name="help_url" value="help/wssb/sbzl/dsdzdk/dsdzdk-001.htm"/>
    	</jsp:include>

    	<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
	  <tr>
	    <td valign="top">
	     <br>
	     <%@//include file="../include/ShowErrMsg.jsp"%>
	      <html:errors/>



<table align="center" cellspacing="0" class="table-99" border="0">
        <tr class="black9">
          <td class="1-td1">���۴�������˰���걨��</td>
        </tr>
        <tr>
          <td valign="top" class="1-td2">
		    <br>
			<table cellspacing="0" class="table-99">
			   <tr class="black9">
				<td align="center" nowrap><div align="left">˰���������ڣ�
                   &nbsp;<shenbao:write name="dsdzdkForm" property="skssksrq"/>
                   ��
                   &nbsp;<shenbao:write name="dsdzdkForm" property="skssjsrq"/></div>
				</td>
				<td align="right" nowrap>��λ�������Ԫ</td>
			  </tr>
			 </table>

            <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td nowrap class="2-td2-t-left"><div align="right">���������&nbsp;&nbsp;</div></td>
                <td nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<bean:write name="dsdzdkForm" property="jsjdm"/></div></td>
                <td nowrap class="2-td2-t-left"><div align="right">��λ����&nbsp;&nbsp;</div></td>
                <td nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<bean:write name="dsdzdkForm" property="dwmc"/></div></td>
                <td nowrap class="2-td2-t-left"><div align="right">��ϵ�绰&nbsp;&nbsp;</div></td>
                <td nowrap class="2-td2-t-center"><div align="left">&nbsp;&nbsp;<bean:write name="dsdzdkForm" property="lxdh"/></div></td>
              </tr>
            </table>
            <br>
            <table width="100%" cellspacing=0 class="table-99">
              <tr>
                <td nowrap class="2-td1-left">���</td>
                <td nowrap class="2-td1-left">˰���ѣ�����</td>
                <td nowrap class="2-td1-left">��˰��Ŀ</td>
                <td nowrap class="2-td1-left">˰��(��λ˰��)</td>
                <td nowrap class="2-td1-left">������������ </td>
                <td nowrap class="2-td1-left">��˰���</td>
                <td nowrap class="2-td1-left">ʵ��˰��</td>
                <td nowrap class="2-td1-left">��˰֤��</td>
                <td nowrap class="2-td1-center">Ʊ֤�������</td>
              </tr>
<% int i=1; %>
<% com.ttsoft.bjtax.shenbao.sbzl.dsdzdk.web.DsdzdkForm form =  (com.ttsoft.bjtax.shenbao.sbzl.dsdzdk.web.DsdzdkForm)session.getAttribute("dsdzdkForm");
	int k =form.getPageindex();
	int max = form.getMaxpageindex();

%>
<logic:iterate id="dsdzdkmxItem" indexId="index" name="dsdzdkForm" property="dsdzdkmxItemList" scope="session">
              <tr>
                <td nowrap class="2-td2-left"> <%= (k-1)*10 + i++ %> </td>
                <td  nowrap class="2-td2-left"><div align="left">&nbsp;<bean:write name="dsdzdkmxItem" property="szdm"/></div></td>
                <td  nowrap class="2-td2-left"><div align="left">&nbsp;<bean:write name="dsdzdkmxItem" property="szsmdm"/></div></td>
                <td  nowrap class="2-td2-left"><div align="right">&nbsp;<bean:write name="dsdzdkmxItem" property="sl"/></div></td>
                <td  nowrap class="2-td2-left"><div align="left">&nbsp;<bean:write name="dsdzdkmxItem" property="bdzrmc"/></div></td>
                <td  nowrap class="2-td2-left"><div align="right"><shenbao:write name="dsdzdkmxItem" property="jsje"/>&nbsp;</div></td>
                <td  nowrap class="2-td2-left"><div align="right"><shenbao:write name="dsdzdkmxItem" property="sjse"/>&nbsp;</div></td>
                <td  nowrap class="2-td2-left"><div align="left">&nbsp;<bean:write name="dsdzdkmxItem" property="wszh"/></div></td>
                <td  nowrap class="2-td2-center"><div align="left">&nbsp;<bean:write name="dsdzdkmxItem" property="pzzldm"/></div></td>
              </tr>
</logic:iterate>

<% if(k==max && max!=0)
	{
%>
              <tr>
                <td class="2-td2-left">�ϼ�</td>
                <td class="2-td2-left">&nbsp;</td>
                <td class="2-td2-left">&nbsp;</td>
                <td class="2-td2-left">&nbsp;</td>
                <td class="2-td2-left">&nbsp;</td>
                <td class="2-td2-left"><div align="right"><shenbao:write name="dsdzdkForm" property="jsjehj"/>&nbsp;</div></td>
                <td class="2-td2-left"><div align="right"><shenbao:write name="dsdzdkForm" property="sjjehj"/>&nbsp;</div></td>
                <td class="2-td2-left">&nbsp;</td>
                <td class="2-td2-center">&nbsp;</td>
              </tr>
<%
	}
%>
            </table>
            <br>
	<html:form method="POST" action="/dsdzdk.do">
	<html:hidden property = "actionType" />
        <html:hidden property = "viewPageType" />
	<html:hidden property = "optJspTag" />
        <input type="hidden" name="curpage" value=<bean:write name="dsdzdkForm" property="pageindex"/>>
	<input type="hidden" name="maxpage" value=<bean:write name="dsdzdkForm" property="maxpageindex"/>>
	<table  class="table-99">
	  <tr class="black9"><td align="right">
            ��(<bean:write name="dsdzdkForm" property="pageindex"/>/<bean:write name="dsdzdkForm" property="maxpageindex"/>)ҳ
			<img src="<%=static_contextpath%>images/diyiye1.jpg" onmouseover="this.src='<%=static_contextpath%>images/diyiye2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/diyiye1.jpg'" alt="��һҳ" onclick="splitPage('FIRST')" style="cursor:hand">
			<img src="<%=static_contextpath%>images/shangyiye1.jpg" onmouseover="this.src='<%=static_contextpath%>images/shangyiye2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/shangyiye1.jpg'" alt="��һҳ" onclick="splitPage('FORWARD')" style="cursor:hand">
			<img src="<%=static_contextpath%>images/xaiyiye1.jpg" onmouseover="this.src='<%=static_contextpath%>images/xaiyiye2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/xaiyiye1.jpg'" alt="��һҳ" onclick="splitPage('NEXT')" style="cursor:hand">
			<img src="<%=static_contextpath%>images/zuimoye1.jpg" onmouseover="this.src='<%=static_contextpath%>images/zuimoye2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/zuimoye1.jpg'" alt="��ĩҳ" onclick="splitPage('LAST')" style="cursor:hand">
          </td></tr>
	</table>
	</html:form>


        <html:form method="POST" action="/dsdzdk.do" enctype="multipart/form-data">
        <html:hidden property = "actionType" />
        ��ѡ���ϴ����ļ�:
	<html:file property="theFile" /><br>
	<img src="<%=static_contextpath%>images/b-dr1.jpg" onmouseover="this.src='<%=static_contextpath%>images/b-dr2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/b-dr1.jpg'" alt="����" onclick="upLoad()" style="cursor:hand">
	</html:form>

	<html:form method="POST" action="/dsdzdk.do">
        <html:hidden property = "actionType" />
		<table>
			<tr><td align="center">
				<img src="<%=static_contextpath%>images/huizong1.jpg" onmouseover="this.src='<%=static_contextpath%>images/huizong2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/huizong1.jpg'" alt="����" onclick="savesbdata()" style="cursor:hand">
				&nbsp;&nbsp;&nbsp;&nbsp; 
				<img src="<%=static_contextpath%>images/b-cxhz1.jpg" onmouseover="this.src='<%=static_contextpath%>images/b-cxhz2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/b-cxhz1.jpg'" alt="��������" onclick="queryHz()" style="cursor:hand">
				&nbsp;&nbsp;&nbsp;&nbsp; 
				<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="����" onclick="exit()" style="cursor:hand">
		</td></tr>
		</table>
	</html:form>
             </td>
        </tr>
      </table>

	    </td>
	  </tr>
    	</table>
    </td>
  </tr>
  <tr>
    <td valign="bottom" align="center">
<br>
	<%@ include file="../include/bottom.jsp" %>
    </td>
  </tr>
 </table>
</body>
</html>