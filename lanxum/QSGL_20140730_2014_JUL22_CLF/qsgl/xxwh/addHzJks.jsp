<%@ page contentType="text/html; charset=GBK"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.hzWsz2JksForm" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ include file="/include/include.jsp"%>

<HTML><HEAD><TITLE>��¼�������ɵĽɿ���</TITLE>
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
        alert("���Ա�Ų���Ϊ�գ�����������");
        document.forms[0].jkpzh.focus();
        return false;
    }
    if(document.forms[0].tfrq.value.length < 1)
    {
        alert("����ڲ���Ϊ�գ�����������");
        document.forms[0].tfrq.focus();
        return false;
    }
    if(document.forms[0].tfrq.value.length != 8)
    {
        alert("����ڸ�ʽ���ԣ�����������");
        document.forms[0].tfrq.focus();
        return false;
    }
    if(document.forms[0].sksskssq.value.length < 1)
    {
        alert("˰��������ʼ���ڲ���Ϊ�գ�����������");
        document.forms[0].sksskssq.focus();
        return false;
    }
    if(document.forms[0].sksskssq.value.length != 8)
    {
        alert("˰��������ʼ���ڸ�ʽ���ԣ�����������");
        document.forms[0].sksskssq.focus();
        return false;
    }
    if(document.forms[0].skssjssq.value.length < 1)
    {
        alert("˰��������ֹ���ڲ���Ϊ�գ�����������");
        document.forms[0].skssjssq.focus();
        return false;
    }
    if(document.forms[0].skssjssq.value.length != 8)
    {
        alert("˰��������ֹ���ڸ�ʽ���ԣ�����������");
        document.forms[0].skssjssq.focus();
        return false;
    }
    if(document.forms[0].jkjehj.value.length < 1)
    {
        alert("���ϼƲ���Ϊ�գ�����������");
        document.forms[0].jkjehj.focus();
        return false;
    }
    if(document.forms[0].mxkssl[0].value.length < 1)
    {
        alert("��˰��������Ϊ�գ�����������");
        document.forms[0].mxkssl[0].focus();
        return false;
    }
    if(document.forms[0].mxjsje[0].value.length < 1)
    {
        alert("��˰����Ϊ�գ�����������");
        document.forms[0].mxjsje[0].focus();
        return false;
    }
    if(document.forms[0].mxsjse[0].value.length < 1)
    {
        alert("ʵ��˰���Ϊ�գ�����������");
        document.forms[0].mxsjse[0].focus();
        return false;
    }

    for(i = 1; i < 4; i++)
    {
        if(document.forms[0].mxkssl[i].value.length < 1 &&
        (document.forms[0].mxjsje[i].value.length > 0 ||
        document.forms[0].mxsjse[i].value.length > 0) )
        {
            alert("��˰��������Ϊ�գ�����������");
            document.forms[0].mxkssl[i].focus();
            return false;
        }

        if(document.forms[0].mxjsje[i].value.length < 1 &&
        (document.forms[0].mxkssl[i].value.length > 0 ||
        document.forms[0].mxsjse[i].value.length > 0) )
        {
            alert("��˰����Ϊ�գ�����������");
            document.forms[0].mxjsje[i].focus();
            return false;
        }

        if(document.forms[0].mxsjse[i].value.length < 1 &&
        (document.forms[0].mxjsje[i].value.length > 0 ||
        document.forms[0].mxkssl[i].value.length > 0) )
        {
            alert("ʵ��˰���Ϊ�գ�����������");
            document.forms[0].mxsjse[i].focus();
            return false;
        }
    }
    return true;

}

</script>


<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0" >

<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="��Ϣά��&gt;��¼�������ɵĽɿ���"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<TABLE align=center  border=0 cellPadding=0 cellSpacing=0 >
	<TBODY>
		<TR>
		  <TD align=center  class=0><B><FONT SIZE="6" COLOR="">��˰�ɿ���</FONT></B></TD>
	    </TR>
 </TBODY></TABLE><BR>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height=580 width=770>
<TBODY>
	   <TR>
		<TD  class=2-td2-t-center> <DIV align=center><B><FONT SIZE="4" COLOR="">�л����񹲺͹�</FONT></B></DIV><BR>
		  <DIV align=center><U>&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;˰&nbsp;&nbsp;&nbsp;&nbsp;</U>&nbsp;˰�սɿ���</DIV></TD>
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
             	<TD class=0 width="10%">������ϵ:</TD>
             	<TD align=left class=0 width="22%"><bean:write name="hzWsz2JksForm" property="lsgx" /></TD>
             	<TD class=0 width="10%">˰������:</TD>
             	<TD align=left class=0 width="22%"><bean:write name="hzWsz2JksForm" property="sklx" /></TD>
             	<TD class=0 width="10%">���Ա��:</TD>
				<TD align=left class=0 width="22%"><html:text name="hzWsz2JksForm" property="jkpzh" maxlength="20"/><font color="red">*</font></TD>
               </TR>
               <TR>
             	<TD class=0 width="10%">ע������:</TD>
             	<TD align=left class=0 width="22%"><bean:write name="hzWsz2JksForm" property="zclx" /></TD>
             	<TD class=0 width="10%">�����:</TD>
             	<TD align=left class=0 width="22%"><html:text name="hzWsz2JksForm" property="tfrq" maxlength="8" size="10"/>(yyyymmdd)<font color="red">*</font></TD>
             	<TD class=0 width="10%">���ջ���:</TD>
				<TD align=left class=0 width="22%"><bean:write name="hzWsz2JksForm" property="zsjg" /></TD>
                </TR></TBODY></TABLE><BR>

		    <TABLE border=1 cellSpacing=0 class=table-99 width="75%">
		    <TR>
		    	<TD rowspan=4 class=2-td2-t-left width="5%">�ɿλ(��)</TD>
		    	<TD class=2-td2-t-left width="8%">����</TD>
		    	<TD align=left class=2-td2-t-left width="12%"><bean:write name="hzWsz2JksForm" property="jkdwdm" />&nbsp;</TD>
		    	<TD class=2-td2-t-left width="5%">�绰</TD>
		    	<TD align=left class=2-td2-t-left width="15%"><bean:write name="hzWsz2JksForm" property="jkdwdh" />&nbsp;</TD>
				<TD rowspan=3 class=2-td2-t-left width="10%">Ԥ���Ŀ</TD>
		    	<TD class=2-td2-t-left width="5%">����</TD>
		    	<TD align=left class=2-td2-t-center width="30%"><bean:write name="hzWsz2JksForm" property="yskmbm" />&nbsp;</TD>
		    </TR>
		    <TR>

		    	<TD class=2-td2-left width="8%">ȫ��</TD>
		    	<TD colspan=3 class=2-td2-left width="12%"><bean:write name="hzWsz2JksForm" property="swjg" />&nbsp;</TD>
		    	<TD class=2-td2-left width="5%">����</TD>
		    	<TD class=2-td2-center width="30%"><bean:write name="hzWsz2JksForm" property="yskmmc" />&nbsp;</TD>
		    </TR>
		    <TR>
		    	<TD class=2-td2-left width="8%">��������</TD>
		    	<TD align=left colspan="3 " class=2-td2-left width="12%"><bean:write name="hzWsz2JksForm" property="jkdwkhyh" />&nbsp;</TD>
		    	<TD class=2-td2-left width="5%">����</TD>
		    	<TD align=left class=2-td2-center width="30%"><bean:write name="hzWsz2JksForm" property="yskmjc" />&nbsp;</TD>
		    </TR>
		    <TR>
		    	<TD class=2-td2-left width="5%">�ʺ�</TD>
		    	<TD align=left colspan="3" class=2-td2-left width="15%"><bean:write name="hzWsz2JksForm" property="jkdwzh" />&nbsp;</TD>
		    	<TD class=2-td2-left width="5%">�տ����</TD>
		    	<TD align=left colspan="2" class=2-td2-center width="30%">(<bean:write name="hzWsz2JksForm" property="jhh" />)<bean:write name="hzWsz2JksForm" property="skgk" />&nbsp;</TD>
		    </TR>
			 <TR>
		    	<TD  colspan="5" align=left class=2-td2-left width="5%"><div align="left">˰������ʱ��(yyyymmdd):&nbsp;<html:text name="hzWsz2JksForm" property="sksskssq" maxlength="8" size="12"/><font color="red">*</font>&nbsp;��&nbsp;<html:text name="hzWsz2JksForm" property="skssjssq" maxlength="8" size="12"/><font color="red">*</font>&nbsp;</div></TD>
		    	<TD  colspan="3" class=2-td2-center width="10%">
                    <div align="left">���ϼƣ�&nbsp;<html:text name="hzWsz2JksForm" property="jkjehj" size="18"/><font color="red">*</font></div>
                </TD>
		    </TR>
            <TR>
		    	<TD  colspan="3" align=left class=2-td2-left width="5%">
                     <div align="left">���ܷ�ʽ:&nbsp;
                        <html:select property = "hzfsdm">
						  <html:option value = "<%=Constants.WSZ_HZFS_GR%>">�����˻���</html:option>
						  <!--<html:option value = "<%=Constants.WSZ_HZFS_ZSD%>">�����յ����</html:option>
						  <html:option value = "<%=Constants.WSZ_HZFS_SWS%>">��˰����أ���������</html:option>-->
                        </html:select>
                    </div>
                </TD>
		    	<TD  colspan="2" class=2-td2-left width="10%">
                    <div align="left">��˰��ʽ:&nbsp;
                        <html:select property="jsfsdm" >
                            <html:option value="<%=Constants.WSZ_JSFS_XJ%>">�ֽ�</html:option>
                            <html:option value="<%=Constants.WSZ_JSFS_SK%>">ˢ��</html:option>
                            <html:option value="<%=Constants.WSZ_JSFS_ZP%>">֧Ʊ</html:option>
                        </html:select>
                    </div>
                </TD>
                <TD  colspan="3" class=2-td2-center width="10%">
                    <div align="left">�Ƿ�һ�λ��ܳ����Žɿ���:&nbsp;
                    <html:select property = "fenpiao">
						<html:option value = "0" >��</html:option>
                        <html:option value = "1" >��</html:option>
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
                width=120><STRONG>Ʊ֤��Ϣ�б�</STRONG></TD>
                <TD>
                  <HR class=hr1 SIZE=1 width="100%">
                </TD></TR></TBODY></TABLE>

          <TABLE border=1 cellSpacing=0 class=table-99 width="75%">
           <TBODY>
		   <TR>
           	<TD colspan="2" class=2-td2-t-left width="20%">��Ŀ����</TD>
           	<TD class=2-td2-t-left width="20%">��˰����</TD>
           	<TD class=2-td2-t-left width="20%">��˰������������</TD>
           	<TD class=2-td2-t-center width="20%">ʵ��˰��</TD>
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
                  <DIV align=left>�ɿλ(��):</DIV>
				  <DIV align=left><bean:write name="hzWsz2JksForm" property="swjg" /></DIV>
				  <DIV align=left>������(��):</DIV></TD>
                <TD class=2-td2-t-left width="25%">
                   <DIV align=left>�ط�˰�����:</DIV>
				  <DIV align=left><bean:write name="hzWsz2JksForm" property="swjg" /></DIV>
				  <DIV align=left>��Ʊ��:</DIV></TD>
                <TD class=2-td2-t-left width="5%">
                  <DIV align=left>��ע:</DIV></TD>
				 <TD  class=2-td2-t-center >
                 <DIV align=left>&nbsp;<html:textarea name="hzWsz2JksForm" property="bz" cols="40" rows="3"/>&nbsp;</DIV></TD>
                </TD></TR></TBODY></TABLE><BR>

                    <DIV align=center>
                    <IMG alt=���� height=22 id=baocun name="btnSave"
                        onclick="doSubmitForm('Save');" onmouseout=MM_swapImgRestore()
                        onmouseover="MM_swapImage('baocun','','/StaticFile/images/baocun2.jpg',1)"
                        src="/StaticFile/images/baocun1.jpg" style="CURSOR: hand" width=79>
                    <IMG alt=�˳� height=22 name=tuichu
                      onclick="doSubmitForm('Return');"
                      onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
                      onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
                      width="79" id="tuichu1" style="cursor:hand">

                    </DIV><BR>
</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
