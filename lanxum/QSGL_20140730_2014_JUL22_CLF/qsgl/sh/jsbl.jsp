<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.SbState"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.ActionUtil"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<HTML><HEAD><TITLE>�����еط�˰�����˰�걨��ʾ��</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType){
  document.all.operationType.value=operationType;

   document.forms[0].submit();
}

function doRedirect(subaction,index,type){
  document.forms[0].fwindex.value = index;
  document.forms[0].fwtype.value = type;
  document.all.operationType.value="Redirect";
  document.forms[0].subaction.value=subaction;

  document.forms[0].submit();
}

function doPrintSbb()
{
    window.open("/qsgl/qssb/printSbxx.do?sbbh="+document.forms[0].sbbh.value,"print","toolbar=no,location=yes,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=640,height=480");
}
function doPrintHdtzs()
{
    window.open("/qsgl/qssb/printHdtzs.do?sbbh="+document.forms[0].sbbh.value,"print","toolbar=no,location=yes,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=640,height=480");
}
function doUpdateHdtzsfwhm()
{
    window.open("/qsgl/qssb/modHdtzsfwhm.do?sbbh="+document.forms[0].sbbh.value);
    document.getElementById("Printhdtzs").style.display="block";
}

</SCRIPT>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="���>�����еط�˰�����˰�걨��ʾ�� "/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<br>
 <TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=98%>
  <TBODY>
  <TR>
    <TD vAlign=top>

         <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>�����еط�˰�����˰�걨��</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
            <html:form action="/sh/jsbl.do">
              <html:hidden property="operationType" value=""/>
              <html:hidden property="subaction" value=""/>
              <html:hidden property="fwindex"/>
              <html:hidden property="fwtype"/>
            <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>
			  <BR>
			  <TR>

			  	<TD class=2-td2-t-left width="15%">���κ�</TD>
			  	<html:hidden name="sbxxForm" property="pch"/>
			  	<TD class=2-td2-t-left width="35%"><DIV align=left>&nbsp<bean:write name="sbxxForm" property="voSbzb.drpch" /> </DIV></TD>

			  	<TD class=2-td2-t-left width="15%">��˰�걨���</TD>
			  	<html:hidden name="sbxxForm" property="sbbh"/>
			  	<TD class=2-td2-t-center width="35%"><DIV align=left>&nbsp;<bean:write name="sbxxForm" property="voSbzb.sbbh" /> </DIV></TD>

			  </TR>
			  <TR>
			  	<TD class=2-td2-left width="15%">�ɿʽ</TD>
			  	<TD class=2-td2-left width="35%"> <DIV align=left>
                                  &nbsp;<bean:write name="sbxxForm" property="voSbzb.jsfsmc"/>
				<TD class=2-td2-left width="15%">�������ع����������</TD>
				<TD class=2-td2-center width="35%"><DIV align=left>&nbsp;<bean:write name="sbxxForm" property="voSbzb.fwtdbmdm" />  </DIV></TD>
			  </TR>
			  </TBODY>
			  </TABLE>

			<TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>
              <bean:define id="sbxxBo" name="sbxxForm" property="data" type="com.creationstar.bjtax.qsgl.model.bo.SbxxBo"/>
              <%if (sbxxBo.isPerson())
              {
              %>

    <TR>
    <TD class=2-td2-left width="70%"  colspan="5"><DIV align=left>������д����</DIV></TD>
    <TD class=2-td2-center width="30%" colspan="3" >  &nbsp; </TD>

</TR>

        <TR>
    <TD  colspan="8" valign="top" width="100%">

<table border="0" cellpadding="0" cellspacing="0" class="table-99" width="100%">
  <tr>
	<td width="20%" class="2-td2-left">
	��˰������
	</td>
    <td width="20%" class="2-td2-left">��ϵ�绰</td>
    <td width="20%" class="2-td2-left">���֤������</td>
    <td width="20%" class="2-td2-left">���֤������</td>
    <td width="10%" class="2-td2-left">����</td>
    <td width="10%" class="2-td2-center">��Ȩ������</td>
  </tr>

 <logic:iterate id="nsrdata" indexId="index" length="length" name="sbxxForm" property="nsrList">
  <tr>
	<td width="20%" class="2-td2-left"><bean:write name="nsrdata" property="nsrmc" /></td>
    <td width="20%" class="2-td2-left"><bean:write name="nsrdata" property="lxdh" />&nbsp;</td>
    <td width="20%" class="2-td2-left"><bean:write name="nsrdata" property="sfzjlxmc" /></td>
    <td width="20%" class="2-td2-left"><bean:write name="nsrdata" property="sfzjhm" /></td>
    <td width="10%" class="2-td2-left"><bean:write name="nsrdata" property="gjmc" /></td>
    <td width="10%" class="2-td2-center">
<logic:equal name="nsrdata" property="cqrlx" value="1">
    ���в�Ȩ��
</logic:equal>
<logic:equal name="nsrdata" property="cqrlx" value="0">
    ����Ȩ��
</logic:equal>
    </td>
  </tr>
  </logic:iterate>

</table>
</TD>
</TR>


				<%
				}
				%>

              <%if (!sbxxBo.isPerson())
              {
              %>
                <TR>
                <TD class=2-td2-t-left width="15%" rowspan = "4">
                  <DIV align=right>�Ǹ�����</DIV>
                   <DIV align=right>д����</DIV>
                  <TD class=2-td2-t-left width="18%">
                   <DIV align=right>��˰���������&nbsp; </DIV>
                </TD>
                <DIV align=right>&nbsp; </DIV>
                  <TD class=2-td2-t-left width="25%">
                    <DIV align=left>
                        &nbsp;<bean:write name = "sbxxForm" property = "voFgrxx.jsjdm" />
                    </DIV>
                  </TD>
                <TD class=2-td2-t-left width="19%">
                  <DIV align=right>˰��������&nbsp; </DIV></TD>
                <TD colspan="4"  class=2-td2-t-center width="33%">
                   <DIV align=left>
                       &nbsp;<bean:write name = "sbxxForm" property = "voFgrxx.nsrlxmc" />
                   </DIV>
                </TD>
             </TR>
             <TR>
                <TD  class=2-td2-left >
                  <DIV align=right>��˰������&nbsp;</DIV></TD>
                <TD colspan="5"  class=2-td2-center  style="word-break:break-all">
                   <DIV align=left>
                       &nbsp;<bean:write name = "sbxxForm" property = "voFgrxx.nsrmc" />
                   </DIV>
                 </TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>��������&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=left>
                      &nbsp;<bean:write name = "sbxxForm" property = "voFgrxx.khyhmc" />
                  </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=right>�����˺�&nbsp;</DIV></TD>
                <TD colspan="3" class=2-td2-center >
                   <DIV align=left>
                       &nbsp;<bean:write name = "sbxxForm" property = "voFgrxx.yhzh" />
                   </DIV>
                 </TD>
                </TR>
                <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>��ϵ������&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                   <DIV align=left>
                       &nbsp;<bean:write name = "sbxxForm" property = "voFgrxx.lxrxm" />
                   </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=right>��ϵ�绰&nbsp;</DIV></TD>
                <TD colspan="4"  class=2-td2-center >
                   <DIV align=left>
                       &nbsp;<bean:write name = "sbxxForm" property = "voFgrxx.lxdh" />
                   </DIV>
                </TD>
                </TR>
          <%
          }
          %>

              <%if (!sbxxBo.isBZ())
              {
              %>
               <TR>
                <TD class=2-td2-left  rowspan = "2">
                  <DIV align=right>˰�����</DIV>
                  <DIV align=right>��˼���˰</DIV></TD>
                 <TD class=2-td2-left >
                   <DIV align=right>����˰�����ֺ�&nbsp; </DIV>
                </TD>
                <TD colspan="6" class=2-td2-center >
                 <DIV align=left>&nbsp;<bean:write name="sbxxForm" property="voSpjgxx.hdtzszh"/> </DIV></TD>
              </TR>
               <TR>
                  <TD class=2-td2-left >
                   <DIV align=right>����˰���&nbsp; </DIV>
                </TD>
                  <TD colspan="6" class=2-td2-center >
                   <DIV align=left>&nbsp;<%=DataConvert.BigDecimal2String(sbxxBo.getVoSpjgxx().getJmsje())%></DIV>
                </TD>
                  </TR>

	          <%
	          }
	          %>
                 <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>��ע&nbsp; </DIV>
                </TD>
                <TD colspan="7" class=2-td2-center >
                  <DIV align=left>&nbsp;<bean:write name="sbxxForm" property="voSbzb.bz" /></DIV></TD>
                </TR>

	<!-- ���ݻ�����Ϣ-->
    		    <bean:define id="fwtdVo" name="sbxxForm" property="voTufwxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"/>
				  <tr>


				    <%if (!sbxxBo.isBZ())
			          {
			          %>
			          <TD class=2-td2-left rowspan = "8">
			          <%
			          }
			          else
			          {
			          %>
			          <TD class=2-td2-left rowspan = "6">
			          <%
			          }
			          %>

				      <DIV align=right>	���ط���</DIV>
				      <DIV align=right>Ȩ��ת��</DIV>
				      <div align=right>
                      </TD>
				      <TD class=2-td2-left >
				        <DIV align=right>���ز���Ŀ����&nbsp;</DIV></TD>
				        <TD colspan="6" class=2-td2-center style="word-break:break-all">
				          <DIV align=left><bean:write name="fwtdVo" property="fdcxmmc" /> </DIV></TD>
				  </TR>
				  <TR>
			          <TD class=2-td2-left >
			            <DIV align=right>��Լǩ��ʱ��&nbsp; </DIV>
			          </TD>
			          <TD class=2-td2-left >
			            <DIV align=left>
                                    <%=DataConvert.TimeStamp2String(fwtdVo.getHtqdsj())%>
                                    </DIV></TD>
               <%if (sbxxBo.isBZ())
              {
              %>
			            <TD class=2-td2-left >
			              <DIV align=right>����&nbsp;</DIV></TD>

	            <%
	            }
	            %>
               <%if (!sbxxBo.isBZ())
              {
              %>
			            <TD class=2-td2-left >
			              <DIV align=right>����ԭ��&nbsp;</DIV></TD>

	            <%
	            }
	            %>
			              <TD colspan="4"  class=2-td2-center >
			                <DIV align=left>
			                  <bean:write  name="fwtdVo" property="flmc"/>&nbsp
			                </DIV></TD>
		          </TR>
		          <TR>
		            <TD class=2-td2-left >
		              <DIV align=right>���ء�����Ȩ��ת������&nbsp; </DIV> </TD>
		              <TD class=2-td2-left >
		                <DIV align=left>
		                  <bean:write  name="fwtdVo" property="tdfwqszymc"/></DIV></TD>
		                  <TD class=2-td2-left >
		                    <DIV align=right>�������&nbsp;</DIV></TD>
		                    <TD colspan="4"  class=2-td2-center >
		                      <DIV align=left>
		                        <bean:write  name="fwtdVo" property="fwlxmc"/>&nbsp;</DIV></TD>
		          </TR>
		          <TR>
		            <TD  class=2-td2-left >
		              <DIV align=right>���ء����������ַ&nbsp; </DIV>
		            </TD>
		            <TD colspan="6"  class=2-td2-center style="word-break:break-all">
		              <DIV align=left><bean:write name="fwtdVo" property="tdfwzldz" /></DIV></TD>
		            </TR>



                    <TR>
                      <TD class=2-td2-left >
                        <DIV align=right>���ء�����Ȩ��ת�����&nbsp; </DIV>
                      </TD>
                      <TD  class=2-td2-left >
                        <DIV align=left>
                          <%=DataConvert.BigDecimal2String(fwtdVo.getTdfwqszymj())%>
                          m2 </DIV></TD>
                        <TD class=2-td2-left >
                          <DIV align=right>���ݽ������&nbsp;</DIV></TD>
                          <TD colspan="4"  class=2-td2-center >
                            <DIV align=left>
                              <%=DataConvert.BigDecimal2String(fwtdVo.getFwjzmj())%>
                              m2</DIV></TD>
                    </TR>

                    <TR>
                      <TD class=2-td2-left >
                        <DIV align=right>�ݻ���&nbsp; </DIV>
                      </TD>
                      <TD  class=2-td2-left >
                        <DIV align=left>
	<%
	if(fwtdVo.getRjl()==null||fwtdVo.getRjl().equals(""))
	{
	%>
	<FONT color=red>��ѡ���ݻ���</FONT>
	<%
	}
    else
	{
		if(fwtdVo.getRjl().equals("00"))
		{
		%>
		1.0����
		<%
		}
		if(fwtdVo.getRjl().equals("01"))
		{
		%>
		1.0���Ϻ�1.0
		<%
		}
	}
	%>&nbsp; </DIV></TD>
                        <TD class=2-td2-left >
                            <!--�޸����ؼ���Ϊ��������-->
                          <DIV align=right>��������&nbsp;</DIV></TD>
                          <TD colspan="4"  class=2-td2-center >
                            <DIV align=left>
    	<%if(fwtdVo.getTdjc()==null||fwtdVo.getTdjc().equals(""))
	{
	%>
	<FONT color=red>��ѡ����������</FONT>&nbsp;
	<%
	}
	else
	{
        if(fwtdVo.getTdjc().equals("00"))
		{
		%>
		&nbsp;
		<%
		}
		if(fwtdVo.getTdjc().equals("01"))
		{
		%>
		��������&nbsp;
		<%
		}
		if(fwtdVo.getTdjc().equals("02"))
		{
		%>
		�������Ļ�֮��&nbsp;
		<%
		}
		%>
		<%if(fwtdVo.getTdjc().equals("03"))
		{
		%>
		�Ļ����廷֮��&nbsp;
		<%
		}
		if(fwtdVo.getTdjc().equals("04"))
		{
		%>
		�廷����&nbsp;
		<%
		}
	     if(fwtdVo.getTdjc().equals("11"))
	 	{	
	     %>
	    	�Ļ��ڱ�������&nbsp;
	     <% 
	 	}
	     if(fwtdVo.getTdjc().equals("12"))
	 	{	
	 	%>
	    	�Ļ����ϲ�����&nbsp;
	     <% 
	 	}
	     if(fwtdVo.getTdjc().equals("13"))
	 	{	
	 	%>
	    	�Ļ����廷��������&nbsp;
	     <% 
	 	}
	      if(fwtdVo.getTdjc().equals("14"))
	 	{	
	 	%>
	    	�Ļ����廷�ϲ�����&nbsp;
	     <% 
	 	}
	     if(fwtdVo.getTdjc().equals("15"))
	 	{	
	 	%>
	    	�廷��������������&nbsp;
	     <% 
	 	}
	     if(fwtdVo.getTdjc().equals("16"))
	 	{	
	 	%>
	    	�廷�������ϲ�����&nbsp;
	     <% 
	 	}
	     if(fwtdVo.getTdjc().equals("17"))
	 	{	
	 	%>
	    	���������&nbsp;
	     <% 
	 	}
	}
	%>

		&nbsp;</DIV></TD>
                    </TR>



			<%if (!sbxxBo.isBZ())
		          {
		          %>
                    <TR>
	                    <TD class=2-td2-left rowspan = "2">
	                      <DIV align=right>�ɽ��۸������۸�</DIV>
	                      <TD  class=2-td2-left >
	                        <DIV align=left>
                                 <%=DataConvert.BigDecimal2String(fwtdVo.getCjjgrmb())%>
                                  Ԫ(�����) </DIV></TD>
	                        <TD class=2-td2-left >
	                          <DIV align=right>˰����غ˶��۸�&nbsp; </DIV></TD>
	                          <TD  colspan="4"  class=2-td2-center >
	                            <DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getPgjgrmb())%>&nbsp; Ԫ(�����) </DIV></TD>
                      </TR>
                      <TR>
                        <TD class=2-td2-left >
                          <DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getCjjgwb())%> Ԫ(���) </DIV></TD>
                          <TD class=2-td2-left >
                            <DIV align=left>
                              ���֣�<bean:write name="fwtdVo" property="bzmc" />
                            </DIV>
                            <DIV align=left>���ʣ�<%=DataConvert.BigDecimal2String(fwtdVo.getHldm(),5)%></DIV></TD>
                            <TD class=2-td2-center colspan=4 >
                              <DIV align=left>
                                <%=DataConvert.BigDecimal2String(fwtdVo.getZhjgrmb())%>
                                �ۺ�Ԫ(�����) </DIV></TD>

                       </tr>
			<%
			}
			%>

      <logic:iterate id="cqdata" indexId="cqindex" length="length" name="sbxxForm" property="cqList"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq">

                <TR>
                  <TD class=2-td2-left rowspan = "4">
                  <DIV align=right>	��Ǩ</DIV>
                   <DIV align=right>���</DIV>
                  </TD>
                  <TD class=2-td2-center >
                   <DIV align=right>����Ǩ���������ַ&nbsp; </DIV>
                </TD>
                <TD colspan="6" class=2-td2-center style="word-break:break-all">
                  <DIV align=left>
                      &nbsp;<bean:write name = "cqdata" property = "zldz"/>
                  </DIV>
                </TD>
                </TR>
                <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>��ǨЭ�����&nbsp; </DIV>
                </TD>
                <TD colspan="6" class=2-td2-center >
                <DIV align=left>
                    &nbsp;<bean:write name = "cqdata" property = "cqxyh"/>
                </DIV>
                </TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left >
                   <DIV align=right> ��Ǩ������&nbsp; </DIV>
                </TD>
                <TD  class=2-td2-left >
                  <DIV align=left>
                     &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getCqbce())%> Ԫ(�����)
                  </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=right>���ο�ʹ�ò�����&nbsp;</DIV></TD>
               <TD colspan="4"  class=2-td2-center >
                <DIV align=left>
                   &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getBcksybce())%> Ԫ(�����)
                </DIV>
               </TD>
               </TR>
               <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>����ʹ�ò�����&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                   <DIV align=left>
                      &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getBcsybce())%> Ԫ(�����)
                   </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=right>��Ǩ����ʣ���&nbsp;</DIV></TD>
                <TD colspan="4"  class=2-td2-center >
                  <DIV align=left>
                      &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getCqbcsye())%> Ԫ(�����)
                  </DIV>
                  </TD>
                  </TR>
       </logic:iterate>

      <logic:iterate id="gydata" indexId="gfindex" length="length" name="sbxxForm" property="gyzfList"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf">

               <TR>
                   <TD class=2-td2-left rowspan = "6">
                  <DIV align=right>	�ѹ�����ס��</DIV>
                  <DIV align=right>	/��������ס��</DIV>
                   <DIV align=right>���г������</DIV>
				   <br>
				   </TD>
                  <TD   class=2-td2-left >
                   <DIV  align=right>���ۺ�ͬ����&nbsp;</DIV>
                </TD>
                <TD colspan="6" class=2-td2-center >
                  <DIV align=left><bean:write name="gydata" property="yggyzfqszsh" /></DIV></TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>����Ȩ��֤���&nbsp; </DIV>
                </TD>
                 <TD colspan="6" class=2-td2-center style="word-break:break-all">
                  <DIV align=left><bean:write name="gydata" property="fwqszsh" />&nbsp;</DIV></TD>
                  </TR>
                <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>�����ַ&nbsp; </DIV>
                </TD>
                 <TD colspan="6" class=2-td2-center style="word-break:break-all">
                  <DIV align=left><bean:write name="gydata" property="zldz" />&nbsp;</DIV></TD>
                  </TR>
                  <TR>
                 <TD  class=2-td2-left >
                   <DIV align=right>���ۺ�ͬ����Լ��ǩ��ʱ��&nbsp;</DIV>
                </TD>
                <TD colspan="6" class=2-td2-center >
                  <DIV align=left><%=DataConvert.TimeStamp2String(gydata.getQdsj())%></DIV></TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left >
                   <DIV align=right> �������&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                   <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getJzmj())%>m2 </DIV></TD>
                <TD class=2-td2-left >
                  <DIV align=right>�ɽ��۸�&nbsp;</DIV></TD>
                <TD colspan="4"  class=2-td2-center >
                  <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getCjjg())%> Ԫ(�����)</DIV></TD>
                  </TR>
                 <TR>
                <TD class=2-td2-left >
                  <DIV align=right>���εֿ۶�&nbsp;</DIV></TD>
                <TD colspan="1"  class=2-td2-left >
                  <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getBcdke())%> Ԫ(�����)</DIV></TD>
                   <TD class=2-td2-left >
                  <DIV align=right>ʣ���&nbsp;</DIV></TD>
                <TD colspan="4"  class=2-td2-center >
                  <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getSye())%> Ԫ(�����)</DIV></TD>
                  </TR>

       </logic:iterate>
                  </TBODY></TABLE><BR>

            <DIV align=center>

	  	  <% if ((sbxxBo.getState().equals(Constants.ZB_ZTBS_JS_TY)))
	  	     {
	  	  %>

	  	  <img alt=��α����   id=Fwhm name=Fwhm
		onclick="doUpdateHdtzsfwhm()" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('Fwhm','','<%=static_file%>images/b-fwhm2.jpg',1)"
		src="<%=static_file%>images/b-fwhm1.jpg" style="CURSOR: hand">

	  	  			            		<img alt=��ӡ�˶�֪ͨ�� id=Printhdtzs name=Printhdtzs
		onclick="doPrintHdtzs()" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('Printhdtzs','','<%=static_file%>images/q_dyhdtzs2.jpg',1)"
		src="<%=static_file%>images/q_dyhdtzs1.jpg" style="CURSOR: hand;display:none">

	  	  <%
	  	     }
	  	     else
	  	     {
	  	  %>
	  	  			            		<img alt=���ͬ��   id=tongyi name=tongyi
		onclick="doSubmitForm('Confirm')" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('tongyi','','<%=static_file%>images/q_shty2.jpg',1)"
		src="<%=static_file%>images/q_shty1.jpg" style="CURSOR: hand">
          &nbsp;&nbsp;&nbsp;&nbsp;
	  	  			            		<img alt=��˲�ͬ��   id=butongyi name=butongyi
		onclick="doSubmitForm('Reject')" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('butongyi','','<%=static_file%>images/q_shbty2.jpg',1)"
		src="<%=static_file%>images/q_shbty1.jpg" style="CURSOR: hand">

          &nbsp;&nbsp;&nbsp;&nbsp;
         <%
             }
         %>

            <IMG alt=�˳� height=22 id=tuichu name=tuichu
            onclick="doSubmitForm('Return')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
            src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand" width=79>
            </DIV><BR>

</html:form>
<script language="javascript" type='text/JavaScript'>

</script>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
