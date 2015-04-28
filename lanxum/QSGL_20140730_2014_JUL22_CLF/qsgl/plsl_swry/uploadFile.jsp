<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>

<HTML><HEAD><TITLE>导入Xml文件</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<script language="JavaScript" type="text/JavaScript" src="<%=static_file%>js/judge.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_file%>js/calculate.js"></script>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>


<script language=JavaScript type='text/JavaScript'>

function doSubmitForm(operationType)
{

    if(operationType == 'Upload')
    {
          if(document.forms[0].mofile.value.length < 1)
        {
            alert("请输入导入文件的位置");
            document.forms[0].mofile.focus();
            return false;
        }

	    document.forms[0].jkfsmc.value = document.forms[0].jkfsdm.options[document.forms[0].jkfsdm.selectedIndex].text;
        document.all.operationType.value = operationType;

	}

    document.all.operationType.value = operationType;
	document.forms[0].submit();

}

</script></HEAD>

<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
<jsp:param name="subtitle" value="批量受理&gt;导入Xml文件"/>
<jsp:param name="helpURL" value=""/>
</jsp:include>
<TR>
  <td vAlign=top>
    <TABLE align=center cellSpacing=0 class=table-99>
      <TR>
        <td class=1-td1>导入Xml文件</td>
      </TR>
      <TR>
        <td class=1-td2 vAlign=top>
          <html:form action="/plsl_swry/importFile.do" enctype="multipart/form-data">
            <html:hidden property="operationType"/>
            <br>
            <table cellspacing="0" class="table-60">
              <tr>
                <td class="2-td2-t-left">选择文件</td>
                <td class="2-td2-t-center">
                  <html:file name="queryPlslForm2" property="mofile" size="30"/><FONT color=red>*</FONT>
                </td>
              </tr>
              <tr>
                <td class="2-td2-left">导入批次号</td>
                <td class="2-td2-center">
                  <bean:write name="queryPlslForm2" property="msDrpch"/>
				  <html:hidden property="msDrpch"/>
                </td>
              </tr>
			  <html:hidden property="msBLDrpch"/>
              <!--tr>
                <td class="2-td2-left">追加导入批次号</td>
                <td class="2-td2-center">
                  <html:select property="msBLDrpch" style="width:300">
                    <html:option value="">请选择</html:option>
                    <html:options property="msBLDrpchs"/>
                  </html:select>
                </td>
              </tr-->

              <tr>
                <td class="2-td2-left">缴款方式</td>
                <td class="2-td2-center">
                                          <bean:define id="data" name="queryPlslForm2" property="jkfsList"  />
                                          <html:select property="jkfsdm">
                                            <html:options collection="data" labelProperty="jsfsmc" property="jsfsdm" />
                                          </html:select>
                                          <html:hidden property="jkfsmc"/>
                </td>
              </tr>

            </table>
            <br>
            <DIV align=center>
              <IMG name="import"
                onMouseOver="MM_swapImage('dr','','<%=static_file%>images/b-dr2.jpg',1)"
                onMouseOut="MM_swapImgRestore()"
                src="<%=static_file%>images/b-dr1.jpg"
                onclick="doSubmitForm('Upload');"
                width="79"
                height="22"
                id="dr"
                alt="上传"
                style="cursor:hand">
              &nbsp;&nbsp;&nbsp;&nbsp;

              <logic:notEqual name="queryPlslForm2" property="size" scope="session" value="0">
              <IMG
                name="jxsl"
                onMouseOver="MM_swapImage('con','','<%=static_file%>images/b-sqsl1.jpg',1)"
                onMouseOut="MM_swapImgRestore()"
                src="<%=static_file%>images/b-sqsl1.jpg"
                onclick="doSubmitForm('Continue');"
                width="79"
                height="22"
                id="jxsl"
                alt="继续受理"
                style="cursor:hand">
              &nbsp;&nbsp;&nbsp;&nbsp;
            </logic:notEqual>
              <IMG
                name="back"
                onclick="doSubmitForm('Return');"
                onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
                onMouseOut="MM_swapImgRestore()"
                src="<%=static_file%>images/tuichu1.jpg"
                width="79"
                height="22"
                id="tuichu1"
                alt="退出"
                style="cursor:hand">
            </DIV>
            <BR>
            <table border="0" cellSpacing=0 class=table-99>
              <TBODY>
                <logic:equal name="queryPlslForm2" property="mbIsSucceed" scope="session" value="false">
                  <tr>
                    <td class="2-td1-left" width="8%">导入批次号</td>
                    <td class="2-td1-left" width="18%">纳税人名称</td>
                    <td class="2-td1-left" width="15%">计算机代码</td>
                    <td class="2-td1-left" width="%18">房地产项目名称</td>
                    <td class="2-td1-left" width="20%">座落地址</td>
                    <td class="2-td1-center" width="15%">成交价格</td>
                  </tr>
                  <logic:iterate id="data" indexId="index" length="length" name="queryPlslForm2" property="mlErrRecords">
                    <tr>
                      <td class="2-td2-left">
                        <bean:write name="data" property="drpch"/>
</a>                      </td>
                      <td class="2-td2-left" style="word-break:break-all">
                        <bean:write name="data" property="drczr"/>
</a>                      </td>
                      <td class="2-td2-left" style="word-break:break-all">
                        <bean:write name="data" property="nsrjsjdm"/>
</a>                      </td>
                      <td class="2-td2-left" style="word-break:break-all">
                        <bean:write name="data" property="fdcxmmc"/>
</a>                      </td>
                      <td class="2-td2-left" style="word-break:break-all">
                        <bean:write name="data" property="fdcdz"/>
</a>                      </td>
                      <td class="2-td2-left" style="word-break:break-all">
                        <bean:write name="data" property="cjjg"/>
</a>                      </td>
                    </tr>
                  </logic:iterate>


            </table>
</logic:equal>
</html:form>
        <%@include file="../include/Bottom.jsp"%>





</BODY>
</HTML>
