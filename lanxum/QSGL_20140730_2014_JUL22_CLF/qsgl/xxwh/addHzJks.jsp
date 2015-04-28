<%@ page contentType="text/html; charset=GBK"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.hzWsz2JksForm" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ include file="/include/include.jsp"%>

<HTML><HEAD><TITLE>补录汇总生成的缴款书</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>

<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>


<script type="text/javascript">

function doSubmitForm(operationType)
{
    if(operationType == 'Save')
    {
        if(!checkData() )
        {
            return false;
        }

        document.forms[0].jsfsmc.value = document.forms[0].jsfsdm.options[document.forms[0].jsfsdm.selectedIndex].text;
        document.forms[0].hzfsmc.value = document.forms[0].hzfsdm.options[document.forms[0].hzfsdm.selectedIndex].text;

        for(i = 0; i < 4; i++)
        {
            document.forms[0].mxxmmc[i].value = document.forms[0].mxxmdm[i].options[document.forms[0].mxxmdm[i].selectedIndex].text;
        }
    }

    document.forms[0].operationType.value=operationType;
  	document.forms[0].submit();
}

function checkData()
{
    if(document.forms[0].jkpzh.value.length < 1)
    {
        alert("电脑编号不能为空，请重新输入");
        document.forms[0].jkpzh.focus();
        return false;
    }
    if(document.forms[0].tfrq.value.length < 1)
    {
        alert("填发日期不能为空，请重新输入");
        document.forms[0].tfrq.focus();
        return false;
    }
    if(document.forms[0].tfrq.value.length != 8)
    {
        alert("填发日期格式不对，请重新输入");
        document.forms[0].tfrq.focus();
        return false;
    }
    if(document.forms[0].sksskssq.value.length < 1)
    {
        alert("税款所属起始日期不能为空，请重新输入");
        document.forms[0].sksskssq.focus();
        return false;
    }
    if(document.forms[0].sksskssq.value.length != 8)
    {
        alert("税款所属起始日期格式不对，请重新输入");
        document.forms[0].sksskssq.focus();
        return false;
    }
    if(document.forms[0].skssjssq.value.length < 1)
    {
        alert("税款所属截止日期不能为空，请重新输入");
        document.forms[0].skssjssq.focus();
        return false;
    }
    if(document.forms[0].skssjssq.value.length != 8)
    {
        alert("税款所属截止日期格式不对，请重新输入");
        document.forms[0].skssjssq.focus();
        return false;
    }
    if(document.forms[0].jkjehj.value.length < 1)
    {
        alert("金额合计不能为空，请重新输入");
        document.forms[0].jkjehj.focus();
        return false;
    }
    if(document.forms[0].mxkssl[0].value.length < 1)
    {
        alert("课税数量不能为空，请重新输入");
        document.forms[0].mxkssl[0].focus();
        return false;
    }
    if(document.forms[0].mxjsje[0].value.length < 1)
    {
        alert("计税金额不能为空，请重新输入");
        document.forms[0].mxjsje[0].focus();
        return false;
    }
    if(document.forms[0].mxsjse[0].value.length < 1)
    {
        alert("实缴税额不能为空，请重新输入");
        document.forms[0].mxsjse[0].focus();
        return false;
    }

    for(i = 1; i < 4; i++)
    {
        if(document.forms[0].mxkssl[i].value.length < 1 &&
        (document.forms[0].mxjsje[i].value.length > 0 ||
        document.forms[0].mxsjse[i].value.length > 0) )
        {
            alert("课税数量不能为空，请重新输入");
            document.forms[0].mxkssl[i].focus();
            return false;
        }

        if(document.forms[0].mxjsje[i].value.length < 1 &&
        (document.forms[0].mxkssl[i].value.length > 0 ||
        document.forms[0].mxsjse[i].value.length > 0) )
        {
            alert("计税金额不能为空，请重新输入");
            document.forms[0].mxjsje[i].focus();
            return false;
        }

        if(document.forms[0].mxsjse[i].value.length < 1 &&
        (document.forms[0].mxjsje[i].value.length > 0 ||
        document.forms[0].mxkssl[i].value.length > 0) )
        {
            alert("实缴税额不能为空，请重新输入");
            document.forms[0].mxsjse[i].focus();
            return false;
        }
    }
    return true;

}

</script>


<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0" >

<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="信息维护&gt;补录汇总生成的缴款书"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<TABLE align=center  border=0 cellPadding=0 cellSpacing=0 >
	<TBODY>
		<TR>
		  <TD align=center  class=0><B><FONT SIZE="6" COLOR="">契税缴款书</FONT></B></TD>
	    </TR>
 </TBODY></TABLE><BR>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height=580 width=770>
<TBODY>
	   <TR>
		<TD  class=2-td2-t-center> <DIV align=center><B><FONT SIZE="4" COLOR="">中华人民共和国</FONT></B></DIV><BR>
		  <DIV align=center><U>&nbsp;&nbsp;&nbsp;&nbsp;契&nbsp;税&nbsp;&nbsp;&nbsp;&nbsp;</U>&nbsp;税收缴款书</DIV></TD>
		</TR>

        <TR>
        <TD class=1-td2><BR>
      <html:form action="/xxwh/addHzJks.do" >
        <html:hidden property = "operationType" />
        <html:hidden property="hzfsmc" />
        <html:hidden property="jsfsmc" />

            <TABLE border=0 cellSpacing=0 class=table-99 width="88%">
              <TBODY>
               <TR>
             	<TD class=0 width="10%">隶属关系:</TD>
             	<TD align=left class=0 width="22%"><bean:write name="hzWsz2JksForm" property="lsgx" /></TD>
             	<TD class=0 width="10%">税款类型:</TD>
             	<TD align=left class=0 width="22%"><bean:write name="hzWsz2JksForm" property="sklx" /></TD>
             	<TD class=0 width="10%">电脑编号:</TD>
				<TD align=left class=0 width="22%"><html:text name="hzWsz2JksForm" property="jkpzh" maxlength="20"/><font color="red">*</font></TD>
               </TR>
               <TR>
             	<TD class=0 width="10%">注册类型:</TD>
             	<TD align=left class=0 width="22%"><bean:write name="hzWsz2JksForm" property="zclx" /></TD>
             	<TD class=0 width="10%">填发日期:</TD>
             	<TD align=left class=0 width="22%"><html:text name="hzWsz2JksForm" property="tfrq" maxlength="8" size="10"/>(yyyymmdd)<font color="red">*</font></TD>
             	<TD class=0 width="10%">征收机关:</TD>
				<TD align=left class=0 width="22%"><bean:write name="hzWsz2JksForm" property="zsjg" /></TD>
                </TR></TBODY></TABLE><BR>

		    <TABLE border=1 cellSpacing=0 class=table-99 width="75%">
		    <TR>
		    	<TD rowspan=4 class=2-td2-t-left width="5%">缴款单位(人)</TD>
		    	<TD class=2-td2-t-left width="8%">代码</TD>
		    	<TD align=left class=2-td2-t-left width="12%"><bean:write name="hzWsz2JksForm" property="jkdwdm" />&nbsp;</TD>
		    	<TD class=2-td2-t-left width="5%">电话</TD>
		    	<TD align=left class=2-td2-t-left width="15%"><bean:write name="hzWsz2JksForm" property="jkdwdh" />&nbsp;</TD>
				<TD rowspan=3 class=2-td2-t-left width="10%">预算科目</TD>
		    	<TD class=2-td2-t-left width="5%">编码</TD>
		    	<TD align=left class=2-td2-t-center width="30%"><bean:write name="hzWsz2JksForm" property="yskmbm" />&nbsp;</TD>
		    </TR>
		    <TR>

		    	<TD class=2-td2-left width="8%">全称</TD>
		    	<TD colspan=3 class=2-td2-left width="12%"><bean:write name="hzWsz2JksForm" property="swjg" />&nbsp;</TD>
		    	<TD class=2-td2-left width="5%">名称</TD>
		    	<TD class=2-td2-center width="30%"><bean:write name="hzWsz2JksForm" property="yskmmc" />&nbsp;</TD>
		    </TR>
		    <TR>
		    	<TD class=2-td2-left width="8%">开户银行</TD>
		    	<TD align=left colspan="3 " class=2-td2-left width="12%"><bean:write name="hzWsz2JksForm" property="jkdwkhyh" />&nbsp;</TD>
		    	<TD class=2-td2-left width="5%">级次</TD>
		    	<TD align=left class=2-td2-center width="30%"><bean:write name="hzWsz2JksForm" property="yskmjc" />&nbsp;</TD>
		    </TR>
		    <TR>
		    	<TD class=2-td2-left width="5%">帐号</TD>
		    	<TD align=left colspan="3" class=2-td2-left width="15%"><bean:write name="hzWsz2JksForm" property="jkdwzh" />&nbsp;</TD>
		    	<TD class=2-td2-left width="5%">收款国库</TD>
		    	<TD align=left colspan="2" class=2-td2-center width="30%">(<bean:write name="hzWsz2JksForm" property="jhh" />)<bean:write name="hzWsz2JksForm" property="skgk" />&nbsp;</TD>
		    </TR>
			 <TR>
		    	<TD  colspan="5" align=left class=2-td2-left width="5%"><div align="left">税款所属时期(yyyymmdd):&nbsp;<html:text name="hzWsz2JksForm" property="sksskssq" maxlength="8" size="12"/><font color="red">*</font>&nbsp;至&nbsp;<html:text name="hzWsz2JksForm" property="skssjssq" maxlength="8" size="12"/><font color="red">*</font>&nbsp;</div></TD>
		    	<TD  colspan="3" class=2-td2-center width="10%">
                    <div align="left">金额合计：&nbsp;<html:text name="hzWsz2JksForm" property="jkjehj" size="18"/><font color="red">*</font></div>
                </TD>
		    </TR>
            <TR>
		    	<TD  colspan="3" align=left class=2-td2-left width="5%">
                     <div align="left">汇总方式:&nbsp;
                        <html:select property = "hzfsdm">
						  <html:option value = "<%=Constants.WSZ_HZFS_GR%>">按个人汇总</html:option>
						  <!--<html:option value = "<%=Constants.WSZ_HZFS_ZSD%>">按征收点汇总</html:option>
						  <html:option value = "<%=Constants.WSZ_HZFS_SWS%>">按税务机关（所）汇总</html:option>-->
                        </html:select>
                    </div>
                </TD>
		    	<TD  colspan="2" class=2-td2-left width="10%">
                    <div align="left">缴税方式:&nbsp;
                        <html:select property="jsfsdm" >
                            <html:option value="<%=Constants.WSZ_JSFS_XJ%>">现金</html:option>
                            <html:option value="<%=Constants.WSZ_JSFS_SK%>">刷卡</html:option>
                            <html:option value="<%=Constants.WSZ_JSFS_ZP%>">支票</html:option>
                        </html:select>
                    </div>
                </TD>
                <TD  colspan="3" class=2-td2-center width="10%">
                    <div align="left">是否一次汇总出多张缴款书:&nbsp;
                    <html:select property = "fenpiao">
						<html:option value = "0" >否</html:option>
                        <html:option value = "1" >是</html:option>
					</html:select>
                    </div>
                </TD>
		    </TR>
		    </TABLE>
			 <HR class=hr1 SIZE=1 width="99%">
            <TABLE cellSpacing=0 class=table-99>
              <TBODY>
              <TR>
                <TD>
                  <HR class=hr1 SIZE=1 width="100%">
                </TD>
                <TD align=middle class=black9
                width=120><STRONG>票证信息列表</STRONG></TD>
                <TD>
                  <HR class=hr1 SIZE=1 width="100%">
                </TD></TR></TBODY></TABLE>

          <TABLE border=1 cellSpacing=0 class=table-99 width="75%">
           <TBODY>
		   <TR>
           	<TD colspan="2" class=2-td2-t-left width="20%">项目名称</TD>
           	<TD class=2-td2-t-left width="20%">课税数量</TD>
           	<TD class=2-td2-t-left width="20%">计税金额或销售收入</TD>
           	<TD class=2-td2-t-center width="20%">实缴税额</TD>
           </TR>

<%for(int i = 0; i < 4; i++) {%>
           <TR>
           	<TD colspan="2" class=2-td2-left width="20%">
            <bean:define id="xmmcList" name="hzWsz2JksForm" property="szsmList"/>
              <html:select property="mxxmdm" >
                <html:options collection="xmmcList" labelProperty="qszyxsmc" property="qszyxsdm" />
              </html:select>
            <html:hidden property="mxxmmc" />
            </TD>
           	<TD class=2-td2-left width="20%"><html:text name="hzWsz2JksForm" property="mxkssl" /></TD>
           	<TD class=2-td2-left width="20%"><html:text name="hzWsz2JksForm" property="mxjsje" /></TD>
           	<TD class=2-td2-center width="20%"><html:text name="hzWsz2JksForm" property="mxsjse" /></TD>
           </TR>
<%}%>

		   </TBODY>
           </TABLE>
          <HR class=hr1 SIZE=1 width="99%">

            <TABLE border=0 cellSpacing=0 class=table-99 width="75%">
              <TBODY>
              <TR>
                <TD class=2-td2-t-left width="25%">
                  <DIV align=left>缴款单位(人):</DIV>
				  <DIV align=left><bean:write name="hzWsz2JksForm" property="swjg" /></DIV>
				  <DIV align=left>经办人(章):</DIV></TD>
                <TD class=2-td2-t-left width="25%">
                   <DIV align=left>地方税务机关:</DIV>
				  <DIV align=left><bean:write name="hzWsz2JksForm" property="swjg" /></DIV>
				  <DIV align=left>填票人:</DIV></TD>
                <TD class=2-td2-t-left width="5%">
                  <DIV align=left>备注:</DIV></TD>
				 <TD  class=2-td2-t-center >
                 <DIV align=left>&nbsp;<html:textarea name="hzWsz2JksForm" property="bz" cols="40" rows="3"/>&nbsp;</DIV></TD>
                </TD></TR></TBODY></TABLE><BR>

                    <DIV align=center>
                    <IMG alt=保存 height=22 id=baocun name="btnSave"
                        onclick="doSubmitForm('Save');" onmouseout=MM_swapImgRestore()
                        onmouseover="MM_swapImage('baocun','','/StaticFile/images/baocun2.jpg',1)"
                        src="/StaticFile/images/baocun1.jpg" style="CURSOR: hand" width=79>
                    <IMG alt=退出 height=22 name=tuichu
                      onclick="doSubmitForm('Return');"
                      onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
                      onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
                      width="79" id="tuichu1" style="cursor:hand">

                    </DIV><BR>
</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
