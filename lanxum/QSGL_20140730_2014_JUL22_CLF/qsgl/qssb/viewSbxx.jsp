<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/control.tld" prefix="control"%>

<%@page import="com.ttsoft.common.util.SessionKey"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.SbState"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf"%>



<HTML><HEAD><TITLE>�����еط�˰�����˰�걨��ʾ��</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType){
  document.all.operationType.value=operationType;
  if(operationType=="Modify" ){
    document.forms[0].operationType.value='Redirect';
    document.forms[0].subaction.value='modify';
  }
  else if (operationType=="Delete" ){
     if (!confirm("��ȷ��ɾ�������걨��¼��"))
     {
        return false;
     }
  }

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

</SCRIPT>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="��˰�걨>�����еط�˰�����˰�걨��ʾ��"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>


<br>
 <html:form action="/qssb/viewSbxx.do">
 <bean:define id="sbxxBo" name="sbxxForm" property="data" type="com.creationstar.bjtax.qsgl.model.bo.SbxxBo"/>
 <TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width="98%">
  <TBODY>
  <TR>
    <TD vAlign=top>

         <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <%if (!sbxxBo.isBZ())
          {
          %>
          <TD class=1-td1>�����еط�˰�����˰�걨��</TD></TR>
          <%
          }
          else
          {
          %>
          <TD class=1-td1>�����еط�˰��ֲ�����˰�ɼ���</TD></TR>
          <%
          }
          %>
        <TR>
          <TD class=1-td2 vAlign=top>
              <html:hidden property="operationType" value=""/>
              <html:hidden property="subaction" value=""/>
              <html:hidden property="fwindex"/>
              <html:hidden property="fwtype"/>
            <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>
			  <BR>

<bean:define id="sbzb" name="sbxxForm" property="voSbzb" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb" />
<logic:equal name="sbxxForm" property="bl" value="true">

     <TR>
         			  	<TD class=2-td2-t-left width="12%">�걨����</TD>
			  	<TD class=2-td2-t-center width="43%" colspan=3><DIV align=left>&nbsp<%=DataConvert.TimeStamp2String(sbzb.getSbrq())%> </DIV></TD>
     </TR>
 			  <TR>
          <%if (!sbxxBo.isBZ())
          {
          %>
		  		<TD class=2-td2-left width="15%">���κ�</TD>
				<html:hidden name="sbxxForm" property="pch"/>
			  	<TD class=2-td2-left width="35%" ><DIV align=left>&nbsp<bean:write name="sbxxForm" property="voSbzb.drpch" /> </DIV></TD>

			  	<TD class=2-td2-left width="15%">��˰�걨���</TD>
				<html:hidden name="sbxxForm" property="sbbh"/>
			  	<TD class=2-td2-center width="35%" ><DIV align=left>&nbsp<bean:write name="sbxxForm" property="voSbzb.sbbh" /> </DIV></TD>
          <%
          }
          else
          {
          %>
			  	<TD class=2-td2-left width="30%">������˰��Ϣ�ɼ����</TD>
							  	<html:hidden name="sbxxForm" property="sbbh"/>
			  	<TD class=2-td2-center width="70%" colspan=3><DIV align=left>&nbsp<bean:write name="sbxxForm" property="voSbzb.sbbh" /> </DIV></TD>
          <%
          }
          %>
			  </TR>
</logic:equal>
<logic:equal name="sbxxForm" property="voSbzb.blbs" value="0">
			  <TR>
          <%if (!sbxxBo.isBZ())
          {
          %>
		  		<TD class=2-td2-t-left width="15%">���κ�</TD>
				<html:hidden name="sbxxForm" property="pch"/>
			  	<TD class=2-td2-t-left width="35%" ><DIV align=left>&nbsp<bean:write name="sbxxForm" property="voSbzb.drpch" /> </DIV></TD>

			  	<TD class=2-td2-t-left width="15%">��˰�걨���</TD>
				<html:hidden name="sbxxForm" property="sbbh"/>
			  	<TD class=2-td2-t-center width="35%" ><DIV align=left>&nbsp<bean:write name="sbxxForm" property="voSbzb.sbbh" /> </DIV></TD>
          <%
          }
          else
          {
          %>
			  	<TD class=2-td2-t-left width="30%">������˰��Ϣ�ɼ����</TD>
							  	<html:hidden name="sbxxForm" property="sbbh"/>
			  	<TD class=2-td2-t-center width="70%" colspan=3><DIV align=left>&nbsp<bean:write name="sbxxForm" property="voSbzb.sbbh" /> </DIV></TD>
          <%
          }
          %>
			  </TR>
</logic:equal>

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
    		  	<input type="hidden" name="bzqs" value="<%=sbxxBo.isBZ()%>">
              <%if (sbxxBo.isPerson()) {%>
               <html:hidden property="person" value="true"/>
  <TR>
    <TD class=2-td2-left width="70%"  colspan="4"><DIV align=left>������д����</DIV></TD>
    <TD class=2-td2-center width="30%" colspan="2" >  &nbsp; </TD>

</TR>

        <TR>
    <TD  colspan="6" valign="top" width="100%">

<table border="0" cellpadding="0" cellspacing="0" class="table-99" width="100%">
  <tr>
	<td width="20%" class="2-td2-left">
	<%if (sbxxBo.isBZ()) {%> ����������
     <% } else {%>��˰������<%}%>
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
				<%}%>

              <%if (!sbxxBo.isPerson()){ %>
               <html:hidden property="person" value="false"/>
                <TR>
                <TD class=2-td2-t-left width="15%" rowspan = "4">
                  <DIV align=right>�Ǹ�����</DIV>
                   <DIV align=right>д����</DIV>
                  <TD class=2-td2-t-left width="20%">
                   <DIV align=right>��˰���������&nbsp; </DIV>
                </TD>
                <DIV align=right>&nbsp; </DIV>
                  <TD class=2-td2-t-left width="20%">
                    <DIV align=left>
                        &nbsp;<bean:write name = "sbxxForm" property = "voFgrxx.jsjdm" />
                    </DIV>
                  </TD>
           <%if (sbxxBo.isBZ()){%>
                <TD class=2-td2-t-left width="20%">
                  <DIV align=right>����������&nbsp; </DIV></TD>

          <% } else { %>
                <TD class=2-td2-t-left width="20%">
                  <DIV align=right>��˰������&nbsp; </DIV></TD>

          <%  }  %>



                <TD colspan="2"  class=2-td2-t-center width="25%">
                   <DIV align=left>
                       &nbsp;<bean:write name = "sbxxForm" property = "voFgrxx.nsrlxmc" />
                   </DIV>
                </TD>
             </TR>
             <TR>

           <%if (sbxxBo.isBZ()){ %>
                <TD  class=2-td2-left >
                  <DIV align=right>����������&nbsp;</DIV></TD>
          <% } else  { %>
                <TD  class=2-td2-left >
                  <DIV align=right>��˰������&nbsp;</DIV></TD>
          <% } %>


                <TD colspan="4"  class=2-td2-center style="word-break:break-all" >
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
                <TD colspan="2" class=2-td2-center >
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
                <TD colspan="2"  class=2-td2-center>
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
                <TD class=2-td2-left rowspan = "2">
                  <DIV align=right>˰�����</DIV>
                  <DIV align=right>��˼���˰</DIV></TD>
                 <TD class=2-td2-left >
                   <DIV align=right>����˰�����ֺ�&nbsp; </DIV>
                </TD>
                <TD colspan="4" class=2-td2-center >
                 <DIV align=left>&nbsp;<bean:write name="sbxxForm" property="voSpjgxx.hdtzszh"/> </DIV></TD>
              </TR>
               <TR>
                  <TD class=2-td2-left >
                   <DIV align=right>����˰���&nbsp; </DIV>
                </TD>
                  <TD colspan="4" class=2-td2-center >
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
                <TD colspan="5" class=2-td2-center >
                  <DIV align=left>&nbsp;<bean:write name="sbxxForm" property="voSbzb.bz" /></DIV></TD>
                </TR>

	<!-- ���ݻ�����Ϣ-->
    		    <bean:define id="fwtdVo" name="sbxxForm" property="voTufwxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx" />
				  <tr>


				    <%if (!sbxxBo.isBZ())
			          {
			          %>
			          <TD class=2-td2-left  rowspan = "9">
			          <%
			          }
			          else
			          {
			          %>
			          <TD class=2-td2-left  rowspan = "7">
			          <%
			          }
			          %>

				      <DIV align=right>	���ط���</DIV>
				      <DIV align=right>Ȩ��ת��</DIV>
				      <div align=right>
			<control:operation pageid="viewSbxx" operationid="modify" checkername="<%=SessionKey.OPERATION_CHECKER%>" defaulthandle="REQUIRED">
					<logic:equal name="sbxxBo" property="state" value="<%=Constants.ZB_ZTBS_BC%>">
				      <IMG alt=�޸� height=22 id=modFwxx name="btnModFwxx"
            onclick="doRedirect('modfwxx');" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('xiugai','','<%=static_file%>images/xiugai2.jpg',1)"
            src="<%=static_file%>images/xiugai1.jpg" style="CURSOR: hand" width=79>
                    </logic:equal>
			</control:operation>
                      </div>
                      </TD>
				      <TD class=2-td2-left >
				        <DIV align=right>���ز���Ŀ����&nbsp;</DIV></TD>
				        <TD colspan="3" class=2-td2-center style="word-break:break-all">
				          <DIV align=left><bean:write name="fwtdVo" property="fdcxmmc" /> </DIV></TD>
                    </tr>
                    <tr>
				      <TD class=2-td2-left >
				        <DIV align=right>�Ƿ�Ϊ���ַ�&nbsp;</DIV></TD>
				        <TD class=2-td2-left style="word-break:break-all">
				          <DIV align=left>
                              <logic:equal name="fwtdVo" property="sfesf" value="00">�Ƕ��ַ�</logic:equal>
                              <logic:equal name="fwtdVo" property="sfesf" value="01">���ַ�</logic:equal>
                          </DIV>
                      </TD>
				      <TD class=2-td2-left >
				        <DIV align=right>˰�����&nbsp;</DIV></TD>
				        <TD class=2-td2-center style="word-break:break-all">
				          <DIV align=left>

		   <%if (sbzb.getSetz()==null||sbzb.getSetz().equals("0"))
				{
					%>

						  û�н���˰�����
			    <%
			    }
						  else
						  {
			    %>
		   <%if (sbzb.getSetz().equals("1"))
				{
					%>
						  ��������
			    <%
			    }
			    %>
		   <%if (sbzb.getSetz().equals("2"))
				{
					%>
						  ȫ������
			    <%
			    }
                //���Ӿ������÷�ѡ�� fujx
                if (sbzb.getSetz().equals("6"))
				{
					%>
						  �������÷�
			    <%
			    }

						  }
			    %>

						  </DIV></TD>


				  </TR>



				  <TR>
			          <TD class=2-td2-left >
			            <DIV align=right>��Լǩ��ʱ��&nbsp; </DIV>
			          </TD>
			          <TD class=2-td2-left >
			            <DIV align=left>
                                    <%=DataConvert.TimeStamp2String(fwtdVo.getHtqdsj())%>
                                    </DIV></TD>

           <%if (!sbxxBo.isBZ())
          {
          %>
			            <TD class=2-td2-left >
			              <DIV align=right>����ԭ��&nbsp;</DIV></TD>
          <%
          }
          else
          {
          %>
			            <TD class=2-td2-left >
			              <DIV align=right>����&nbsp;</DIV></TD>
			    <%
			    }
			    %>

			              <TD colspan="2"  class=2-td2-center >
			                <DIV align=left>
			                  <bean:write  name="fwtdVo" property="flmc"/>&nbsp
			                </DIV></TD>
		          </TR>
		          <TR>
		            <TD class=2-td2-left >
		              <DIV align=right>���ء�����Ȩ��ת������&nbsp; </DIV> </TD>
		              <TD class=2-td2-left >
		                <DIV align=left>
		                  <bean:write  name="fwtdVo" property="tdfwqszymc"/>&nbsp; </DIV></TD>
		                  <TD class=2-td2-left >
		                    <DIV align=right>�������&nbsp;</DIV></TD>
		                    <TD colspan="2"  class=2-td2-center >
		                      <DIV align=left>
		                        <bean:write  name="fwtdVo" property="fwlxmc"/>&nbsp; </DIV></TD>
		          </TR>
		          <TR>
		            <TD  class=2-td2-left >
		              <DIV align=right>���ء����������ַ&nbsp; </DIV>
		            </TD>
		            <TD colspan="4"  class=2-td2-center style="word-break:break-all">
		              <DIV align=left><bean:write name="fwtdVo" property="tdfwzldz" /></DIV></TD>
		            </TR>
                    <TR>
                      <TD class=2-td2-left >
                        <DIV align=right>���ء�����Ȩ��ת�����&nbsp; </DIV>
                      </TD>
                      <TD  class=2-td2-left >
                        <DIV align=left>���أ�
                          <%=DataConvert.BigDecimal2String(fwtdVo.getTdfwqszymj())%>
                          �O </DIV></TD>
                        <TD class=2-td2-left >
                          <DIV align=right>���ݽ������&nbsp;</DIV></TD>
                          <TD colspan="2"  class=2-td2-center >
                            <DIV align=left>
                           <%=DataConvert.BigDecimal2String(fwtdVo.getFwjzmj())%>
                              �O</DIV></TD>
                    </TR>


		          <TR>
		            <TD class=2-td2-left >
		              <DIV align=right>�ݻ���&nbsp; </DIV> </TD>
		              <TD class=2-td2-left >
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
	%>&nbsp;
						  </DIV></TD>
		                  <TD class=2-td2-left >
                              <!--�޸����ؼ���Ϊ������������-->
		                    <DIV align=right>��������&nbsp;</DIV></TD>
		                    <TD colspan="2"  class=2-td2-center >
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
	    //����2014��10�±�����ͨס����׼���������������Ϊ�±�׼��ÿƽ�׼۸����ޡ�ÿ�׷��ݼ۸������������������ ��
		//�˴�ֻ�޸�����������ʾ��modified by gaoyh to 20141020
	    if(fwtdVo.getTdjc().equals("21"))
		{	
		%>
	   	5����&nbsp;
	    <% 
		}
	    if(fwtdVo.getTdjc().equals("22"))
		{	
		%>
	   	5-6��&nbsp;
	    <% 
		}
	    if(fwtdVo.getTdjc().equals("23"))
		{	
		%>
	   	6����&nbsp;
	    <% 
		}
	}
	    %>

		&nbsp; </DIV></TD>
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
	                          <TD  colspan="2"  class=2-td2-center >
	                            <DIV align=left>
                                      <%=DataConvert.BigDecimal2String(fwtdVo.getPgjgrmb())%>
                                      Ԫ(�����) </DIV></TD>
                      </TR>
                      <TR>
                        <TD class=2-td2-left >
                          <DIV align=left>
                             <%=DataConvert.BigDecimal2String(fwtdVo.getCjjgwb())%>
                            Ԫ(���) </DIV></TD>
                          <TD class=2-td2-left >
                            <DIV align=left>
                             ���֣� <bean:write name="fwtdVo" property="bzmc" />
                            </DIV>
                            <DIV align=left>
                              ����:&nbsp;<%=DataConvert.BigDecimal2String(fwtdVo.getHldm(),5)%>
                               </DIV></TD>
                            <TD class=2-td2-center  >
                              <DIV align=left>
                                <%=DataConvert.BigDecimal2String(fwtdVo.getZhjgrmb())%>
                                �ۺ�Ԫ(�����) </DIV></TD>

                       </tr>

          <%
          }
          %>


          <%if (!sbxxBo.isBZ())
          {
          %>
      <logic:iterate id="cqdata" indexId="cqindex" length="length" name="sbxxForm" property="cqList"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq">

                <TR>
                  <TD class=2-td2-left  rowspan = "4">
                  <DIV align=right>	��Ǩ</DIV>
                   <DIV align=right>���</DIV>
				      <div align=right>
			<control:operation pageid="viewSbxx" operationid="modify" checkername="<%=SessionKey.OPERATION_CHECKER%>" defaulthandle="OMIT">
					<logic:equal name="sbxxBo" property="state" value="<%=Constants.ZB_ZTBS_BC%>">
				      <IMG alt=�޸� height=22 id=modCqxx name="btnModCqxx"
            onclick="doRedirect('modcqxx','<%=cqindex.intValue()%>',1);" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('xiugai','','<%=static_file%>images/xiugai2.jpg',1)"
            src="<%=static_file%>images/xiugai1.jpg" style="CURSOR: hand" width=79>
                    </logic:equal>
			</control:operation>
                      </div>
                  </TD>
                  <TD class=2-td2-center style="word-break:break-all">
                   <DIV align=right>����Ǩ���������ַ&nbsp; </DIV>
                </TD>
                <TD colspan="4" class=2-td2-center >
                  <DIV align=left>
                      <bean:write name = "cqdata" property = "zldz"/>
                  </DIV>
                </TD>
                </TR>
                <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>��ǨЭ�����&nbsp; </DIV>
                </TD>
                <TD colspan="4" class=2-td2-center >
                <DIV align=left>
                    <bean:write name = "cqdata" property = "cqxyh"/>
                </DIV>
                </TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left >
                   <DIV align=right> ��Ǩ������&nbsp; </DIV>
                </TD>
                <TD  class=2-td2-center  colspan=4>
                  <DIV align=left>
                      <%=DataConvert.BigDecimal2String(cqdata.getCqbce())%>
                     Ԫ(�����)
                  </DIV>
                </TD>
               </TR>
               <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>����ʹ�ò�����&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                   <DIV align=left>
                     <%=DataConvert.BigDecimal2String(cqdata.getBcsybce())%>
                      Ԫ(�����)
                   </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=right>��Ǩ����ʣ���&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center >
                  <DIV align=left>
                      &nbsp;
                      <%=DataConvert.BigDecimal2String(cqdata.getCqbcsye())%>
                      Ԫ(�����)
                  </DIV>
                  </TD>
                  </TR>
       </logic:iterate>

      <logic:iterate id="gydata" indexId="gfindex" length="length" name="sbxxForm" property="gyzfList"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf">

               <TR>
                   <TD class=2-td2-left  rowspan = "7">
                  <DIV align=right>	�ѹ�����ס��</DIV>
                  <DIV align=right>	/��������ס��</DIV>
                   <DIV align=right>���г������</DIV>
				      <div align=right >
			<control:operation pageid="viewSbxx" operationid="modify" checkername="<%=SessionKey.OPERATION_CHECKER%>" defaulthandle="REQUIRED">
       			<logic:equal name="sbxxBo" property="state" value="<%=Constants.ZB_ZTBS_BC%>">
				      <IMG alt=�޸� height=22 id=modGyzf name="btnModGyzf"
            onclick="doRedirect('modgyzf','<%=gfindex.intValue()%>',2);" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('xiugai','','<%=static_file%>images/xiugai2.jpg',1)"
            src="<%=static_file%>images/xiugai1.jpg" style="CURSOR: hand" width=79>
                </logic:equal>
			</control:operation>
                      </div>
				   <br>
				   </TD>
                  <TD   class=2-td2-left >
                   <DIV  align=right>���ۺ�ͬ����&nbsp;</DIV>
                </TD>
                <TD colspan="4" class=2-td2-center >
                  <DIV align=left><bean:write name="gydata" property="yggyzfqszsh" /></DIV></TD>
                  </TR>
								<TR>
                 <TD class=2-td2-left >
                   <DIV align=right>����Ȩ��֤���&nbsp; </DIV>
                </TD>
                 <TD colspan="4" class=2-td2-center style="word-break:break-all">
                  <DIV align=left><bean:write name="gydata" property="fwqszsh" />&nbsp;</DIV></TD>
                  </TR>                  
                <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>�����ַ&nbsp; </DIV>
                </TD>
                 <TD colspan="4" class=2-td2-center style="word-break:break-all">
                  <DIV align=left><bean:write name="gydata" property="zldz" />&nbsp;</DIV></TD>
                  </TR>
                  <TR>
                 <TD  class=2-td2-left >
                   <DIV align=right>���ۺ�ͬ����Լ��ǩ��ʱ��&nbsp;</DIV>
                </TD>
                <TD colspan="4" class=2-td2-center >
                  <DIV align=left>
                  <%=DataConvert.TimeStamp2String(gydata.getQdsj())%>
                  </DIV></TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left >
                   <DIV align=right> �������&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                   <DIV align=left>
                     <%=DataConvert.BigDecimal2String(gydata.getJzmj())%>
                     �O </DIV></TD>
                <TD class=2-td2-left >
                  <DIV align=right>�ɽ��۸�&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center >
                  <DIV align=left>
                     <%=DataConvert.BigDecimal2String(gydata.getCjjg())%>
                    Ԫ(�����)</DIV></TD>
                  </TR>
                 <TR>
                <TD class=2-td2-left >
                  <DIV align=right>���εֿ۶�&nbsp;</DIV></TD>
                <TD colspan="4"  class=2-td2-center >
                  <DIV align=left>
                     <%=DataConvert.BigDecimal2String(gydata.getBcdke())%>
                    Ԫ(�����)</DIV></TD>
                  </TR>
                   <TR>
                   <TD class=2-td2-left >
                  <DIV align=right>ʣ���&nbsp;</DIV></TD>
                <TD colspan="4"  class=2-td2-center >
                  <DIV align=left>
                     <%=DataConvert.BigDecimal2String(gydata.getSye())%>
                    Ԫ(�����)</DIV></TD>
                  </TR>

       </logic:iterate>



           <!-- ��ʾ����������Ϣ-->
            <logic:equal name="sbxxForm" property="addedFwjhxx" value="true">
              <bean:define id="fwjhBo" name="sbxxForm" property="voFwjh" type="com.creationstar.bjtax.qsgl.model.bo.FwjhxxBo"/>

              <logic:equal name="fwjhBo" property="jhperson"  value="1">
                <bean:define id="dffgrxx" name="sbxxForm" property="voFwjh.fgrxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx"/>
              </logic:equal>

              <bean:define id="dffwtdxx" name="sbxxForm" property="voFwjh.tufwxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"/>
              <!--TR>
                <TD class=2-td2-left >�Է��ɿʽ</TD>
                <TD class=2-td2-left > <DIV align=left>
                  <bean:write name="fwjhBo" property="jkfsmc" /></td>
                  <TD class=2-td2-left >�Է��������ع����������</TD>
                  <TD colspan="3" class=2-td2-center ><DIV align=left><bean:write name="fwjhBo" property="fcjslh" />&nbsp;</DIV></TD>
                </TR-->


                <!--����������Ϣ��ʾ-->
                <logic:equal name="fwjhBo" property="jhperson"  value="0">


  <TR>
    <TD class=2-td2-left width="70%"  colspan="4"><DIV align=left>�Է�������д����</DIV></TD>
    <TD class=2-td2-center width="30%" colspan="2" >  &nbsp; </TD>

</TR>

        <TR>
    <TD  colspan="6" valign="top" width="100%">

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

 <logic:iterate id="jhnsr" indexId="index" length="length" name="fwjhBo" property="nsrList">
  <tr>
	<td width="20%" class="2-td2-left"><bean:write name="jhnsr" property="nsrmc" /></td>
    <td width="20%" class="2-td2-left"><bean:write name="jhnsr" property="lxdh" />&nbsp;</td>
    <td width="20%" class="2-td2-left"><bean:write name="jhnsr" property="sfzjlxmc" /></td>
    <td width="20%" class="2-td2-left"><bean:write name="jhnsr" property="sfzjhm" /></td>
    <td width="10%" class="2-td2-left"><bean:write name="jhnsr" property="gjmc" /></td>
    <td width="10%" class="2-td2-center">
<logic:equal name="jhnsr" property="cqrlx" value="1">
    ���в�Ȩ��
</logic:equal>
<logic:equal name="jhnsr" property="cqrlx" value="0">
    ����Ȩ��
</logic:equal>
    </td>
  </tr>
  </logic:iterate>

</table>
</TD>
</TR>


            </logic:equal>

            <!--�����Ǹ�����Ϣ��ʾ-->

            <logic:equal name="fwjhBo" property="jhperson"  value="1">

              <TR>
                <TD class=2-td2-left rowspan = "4">
              <DIV align=right>�Է��Ǹ���</DIV>
              <DIV align=right>��д����</DIV></TD>
              <TD class=2-td2-left >
                <DIV align=right>���������&nbsp; </DIV>
              </TD>
              <DIV align=right>&nbsp; </DIV>
              <TD class=2-td2-left >
                <DIV align=left> <bean:write name="dffgrxx" property="jsjdm" />&nbsp</DIV></TD>
                <TD class=2-td2-left >
                  <DIV align=right>&nbsp; </DIV></TD>
                  <TD colspan="2"  class=2-td2-center >
                    <DIV align=left> &nbsp;</DIV></TD>
                  </TR>
              <TR>
                <TD class=2-td2-left >
                  <DIV align=right>��˰������&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=left>
                    <bean:write name="dffgrxx" property="nsrmc" />&nbsp;
                  </DIV></TD>
                  <TD   class=2-td2-left >
                    <DIV align=right>��˰������&nbsp;</DIV></TD>
                    <TD colspan="2"  class=2-td2-center >
                         <DIV align=left><bean:write name="dffgrxx" property="nsrlxmc" />&nbsp;</DIV></TD>
                        </TR>

            <TR>
              <TD class=2-td2-left >
                <DIV align=right>��������&nbsp; </DIV>
              </TD>
              <TD class=2-td2-left >
                <DIV align=left>
                  <bean:write name="dffgrxx" property="khyhmc" />&nbsp;
                </DIV></TD>
                <TD   class=2-td2-left >
                  <DIV align=right>�����˺�&nbsp;</DIV></TD>
                  <TD colspan="2"  class=2-td2-center >
                    <DIV align=left><bean:write name="dffgrxx" property="yhzh" />&nbsp;</DIV></TD>
                  </TR>


              <TR>
                <TD class=2-td2-left >
                  <DIV align=right>��ϵ������&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=left>
                    <bean:write name="dffgrxx" property="lxrxm" />&nbsp;
                  </DIV></TD>
                  <TD   class=2-td2-left >
                    <DIV align=right>��ϵ�绰&nbsp;</DIV></TD>
                    <TD colspan="2"  class=2-td2-center >
                      <DIV align=left><bean:write name="dffgrxx" property="lxdh" />&nbsp</DIV></TD>
                    </TR>

              </logic:equal>
              <TR>
                <TD class=2-td2-left rowspan = "7">
                  <DIV align=right>	�������ط���</DIV>
                  <DIV align=right>Ȩ��ת��</DIV>
                  				      <div align=right>
			<control:operation pageid="viewSbxx" operationid="modify" checkername="<%=SessionKey.OPERATION_CHECKER%>" defaulthandle="REQUIRED">
					<logic:equal name="sbxxBo" property="state" value="<%=Constants.ZB_ZTBS_BC%>">
				      <IMG alt=�޸� height=22 id=modFwxx name="btnModFwxx"
            onclick="doRedirect('modfwjh');" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('xiugai','','<%=static_file%>images/xiugai2.jpg',1)"
            src="<%=static_file%>images/xiugai1.jpg" style="CURSOR: hand" width=79>
                    </logic:equal>
			</control:operation>
                      </div>

                  </TD>


                  <TD class=2-td2-left >
                    <DIV align=right>���ز���Ŀ����&nbsp;</DIV></TD>
                    <TD colspan="4" class=2-td2-center style="word-break:break-all">
                      <DIV align=left><bean:write name="dffwtdxx" property="fdcxmmc" /></DIV></TD>
                    </TR>
	            <TR>
	              <TD class=2-td2-left >
	                <DIV align=right>��Լǩ��ʱ��&nbsp; </DIV>
	              </TD>
	              <TD class=2-td2-left >
	                <DIV align=left><%=DataConvert.TimeStamp2String(dffwtdxx.getHtqdsj())%> </DIV></TD>
	                <TD class=2-td2-left >
	                  <DIV align=right>����&nbsp;</DIV></TD>
	                  <TD colspan="2"  class=2-td2-center >
	                    <DIV align=left>
	                      <bean:write  name="dffwtdxx" property="flmc"/>

	                    </DIV></TD>
	                  </TR>
              <!--TR>
                <TD class=2-td2-left>
                  <DIV align=right>���ء�����Ȩ��ת������&nbsp; </DIV> </TD>
                  <TD class=2-td2-left >
                    <DIV align=left>

                      <bean:write name="dffwtdxx" property="tdfwqszymc" />
                    </DIV></TD>
                    <TD class=2-td2-left >
                      <DIV align=right>�������&nbsp;</DIV></TD>
                      <TD colspan="2"  class=2-td2-center >
                        <DIV align=left>

                          <bean:write name="dffwtdxx" property="fwlxmc" />
                        </DIV></TD>
                      </TR-->
                  <TR>
                    <TD  class=2-td2-left style="word-break:break-all">
                      <DIV align=right>���ء����������ַ&nbsp; </DIV>
                    </TD>
                    <TD colspan="4"  class=2-td2-center >
                      <DIV align=left>
                        <bean:write name="dffwtdxx" property="tdfwzldz" />
                      </DIV>
                    </DIV></TD>
                  </TR>
                  <TR>
                    <TD class=2-td2-left >
                      <DIV align=right>���ء�����Ȩ��ת�����&nbsp; </DIV>
                    </TD>
                    <TD  class=2-td2-left >
                      <DIV align=left>���أ�
                        <%=DataConvert.BigDecimal2String(dffwtdxx.getTdfwqszymj())%>
                        �O </DIV></TD>
                        <TD class=2-td2-left >
                          <DIV align=right>���ݽ������&nbsp;</DIV></TD>
                          <TD colspan="2"  class=2-td2-center >
                            <DIV align=left>
                              <%=DataConvert.BigDecimal2String(dffwtdxx.getFwjzmj())%>
                              �O</DIV></TD>
                            </TR>

 	            <TR>
	              <TD class=2-td2-left >
	                <DIV align=right>�ݻ���&nbsp; </DIV>
	              </TD>
	              <TD class=2-td2-left >
	                <DIV align=left>
	<%if(dffwtdxx.getRjl().equals("00"))
	{
	%>
	1.0����
	<%
	}
	if(dffwtdxx.getRjl().equals("01"))
	{
	%>
	1.0���Ϻ�1.0
	<%
	}
	%>&nbsp;</DIV></TD>
	                <TD class=2-td2-left >
                        <!--�޸����ؼ���Ϊ��������-->
	                  <DIV align=right>��������&nbsp;</DIV></TD>
	                  <TD colspan="2"  class=2-td2-center >
	                    <DIV align=left>
    <%if(dffwtdxx.getTdjc().equals("00"))
	{
	%>
	&nbsp;
	<%
	}
    if(dffwtdxx.getTdjc().equals("01"))
	{
	%>
	��������&nbsp;
	<%
	}
	if(dffwtdxx.getTdjc().equals("02"))
	{
	%>
	�������Ļ�֮��&nbsp;
	<%
	}
	%>
	<%if(dffwtdxx.getTdjc().equals("03"))
	{
	%>
	�Ļ����廷֮��&nbsp;
	<%
	}
	if(dffwtdxx.getTdjc().equals("04"))
	{
	%>
	�廷����&nbsp;
	<%
	}
    if(dffwtdxx.getTdjc().equals("11"))
	{	
    %>
   	�Ļ��ڱ�������&nbsp;
    <% 
	}
    if(dffwtdxx.getTdjc().equals("12"))
	{	
	%>
   	�Ļ����ϲ�����&nbsp;
    <% 
	}
    if(dffwtdxx.getTdjc().equals("13"))
	{	
	%>
   	�Ļ����廷��������&nbsp;
    <% 
	}
     if(dffwtdxx.getTdjc().equals("14"))
	{	
	%>
   	�Ļ����廷�ϲ�����&nbsp;
    <% 
	}
    if(dffwtdxx.getTdjc().equals("15"))
	{	
	%>
   	�廷��������������&nbsp;
    <% 
	}
    if(dffwtdxx.getTdjc().equals("16"))
	{	
	%>
   	�廷�������ϲ�����&nbsp;
    <% 
	}
    if(dffwtdxx.getTdjc().equals("17"))
	{	
	%>
   	���������&nbsp;
    <% 
	}
    //����2014��10�±�����ͨס����׼���������������Ϊ�±�׼��ÿƽ�׼۸����ޡ�ÿ�׷��ݼ۸������������������ ��
	//�˴�ֻ�޸�����������ʾ��modified by gaoyh to 20141020
    if(dffwtdxx.getTdjc().equals("21"))
	{	
	%>
   	5����&nbsp;
    <% 
	}
    if(dffwtdxx.getTdjc().equals("22"))
	{	
	%>
   	5-6��&nbsp;
    <% 
	}
    if(dffwtdxx.getTdjc().equals("23"))
	{	
	%>
   	6����&nbsp;
    <% 
	}
    %>


		&nbsp;
	                    </DIV></TD>
	                  </TR>


                <TR>
                  <TD class=2-td2-left rowspan = "2">
                    <DIV align=right>�ɽ��۸������۸�</DIV>
                    <TD  class=2-td2-left >
                      <DIV align=left>
                        <%=DataConvert.BigDecimal2String(dffwtdxx.getCjjgrmb())%>
                        Ԫ(�����) </DIV></TD>
                        <TD class=2-td2-left >
                          <DIV align=right>˰����غ˶��۸�&nbsp; </DIV></TD>
                          <TD  colspan="2"  class=2-td2-center >
                            <DIV align=left>
                              <%=DataConvert.BigDecimal2String(dffwtdxx.getPgjgrmb())%>
                          Ԫ(�����) </DIV></TD>
                        </TR>
                <TR>
                  <TD class=2-td2-left >
                    <DIV align=left>
                      <%=DataConvert.BigDecimal2String(dffwtdxx.getCjjgwb())%>
                      Ԫ(���) </DIV></TD>
                      <TD class=2-td2-left >
                        <DIV align=left>
                          ���֣�<bean:write name="dffwtdxx" property="bzmc" />

                        </DIV>
                        <DIV align=left>
                          ����:&nbsp;<%=DataConvert.BigDecimal2String(dffwtdxx.getHldm(),5)%>
                           </DIV></TD>
                          <TD class=2-td2-center  colspan="2" >
                            <DIV align=left>
                              <%=DataConvert.BigDecimal2String(dffwtdxx.getZhjgrmb())%>
                              �ۺ�Ԫ(�����) </DIV></TD>
                            </TR>
      </logic:equal>

    <%
    } //end sbxxBo.isBZ()
    %>









                  </TBODY></TABLE><BR>

			<control:operation pageid="viewSbxx" operationid="todo" checkername="<%=SessionKey.OPERATION_CHECKER%>" defaulthandle="REQUIRED">
			<logic:equal name="sbxxBo" property="state" value="<%=Constants.ZB_ZTBS_BC%>">
            <IMG alt=¼���Ǩ���  id=Cqxx name=Submit53
            onclick="doRedirect('addcqxx');" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('Cqxx','','<%=static_file%>images/q_lrcq2.jpg',1)"
            src="<%=static_file%>images/q_lrcq1.jpg" style="CURSOR: hand" >

             <IMG alt=¼���ѹ�����ס��/��������ס��������� id=Gyzf name=Submit43
            onclick="doRedirect('addgyzf');" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('Gyzf','','<%=static_file%>images/q_lryggyzfssqk2.jpg',1)"
            src="<%=static_file%>images/q_lryggyzfssqk1.jpg" style="CURSOR: hand" >

            </logic:equal>
			</control:operation>











            <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>
			  <BR>
			  <TR>
			  	<TD class=2-td2-t-left width="12%">��ǰ״̬</TD>
			  	<TD class=2-td2-t-left width="25%" >
			  	  <DIV align=left>&nbsp<%=SbState.getStateName(sbxxBo.getVoSbzb().getZtbs())%> </DIV></TD>
			  	<TD class=2-td2-t-left width="15%" >
			  	  <DIV align=left>&nbsp ����ǰ�ϴ�״̬</DIV></TD>
			  	<TD class=2-td2-t-left width="25%" >
			  	  <DIV align=left>&nbsp<%=SbState.getCancelStateName(sbxxBo.getVoSbzb().getZtbs(),sbxxBo.getVoSbzb().getBljmsbs())%> </DIV></TD>
			  	<TD class=2-td2-t-center width="20%" >
			  	  <DIV align=left>&nbsp
			  	  <% if (!sbxxBo.getState().equals(Constants.ZB_ZTBS_BC) && !sbxxBo.getState().equals(Constants.ZB_ZTBS_ZF) && !sbxxBo.getState().equals(Constants.ZB_ZTBS_YRK))
			  	     {
			  	  %>
			<control:operation pageid="viewSbxx" operationid="rollback" checkername="<%=SessionKey.OPERATION_CHECKER%>" defaulthandle="REQUIRED">
			  	              		<img alt=���������ϴ�״̬   id=rollback name=rollback
		onclick="doSubmitForm('RollBack')" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('rollback','','<%=static_file%>images/q_cxfhsczt2.jpg',1)"
		src="<%=static_file%>images/q_cxfhsczt1.jpg" style="CURSOR: hand">

			</control:operation>
			  	  <%
			  	     }
			  	  %>
			  	  </DIV></TD>

			  </TR>
			  </TBODY>
			  </TABLE>
			<BR>
            <DIV align=center>
            <!--input type=button name="btnInputFwjbxx" value="¼�뷿�ݻ�����Ϣ" onclick="doSubmitForm('Fwjbxx')">
            <input type=button name="btnInputCqxx" value="¼���Ǩ���" onclick = "doSubmitForm('Cqxx');">
            <input type=button name="btnInputGyzf" value="¼���ѹ�����ס���������" onclick = "doSubmitForm('Gyzf');">
            <input type=button name="btnInputJmsb" value="¼������걨��" onclick = "doSubmitForm(Jmsb);"><br><br-->

	  	  <% if (!sbxxBo.getState().equals(Constants.ZB_ZTBS_ZF) && !sbxxBo.getState().equals(Constants.ZB_ZTBS_YRK))
	  	     {
	  	  %>
			<control:operation pageid="viewSbxx" operationid="printSbb" checkername="<%=SessionKey.OPERATION_CHECKER%>" defaulthandle="REQUIRED">
            		<img alt=��ӡ   id=Printsbb name=Printsbb
		onclick="doPrintSbb()" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('Printsbb','','<%=static_file%>images/q_dysbb2.jpg',1)"
		src="<%=static_file%>images/q_dysqb1.jpg" style="CURSOR: hand">

			</control:operation>
	  	  <%
	  	     }
	  	  %>

	  	  <% if ((sbxxBo.getState().equals(Constants.ZB_ZTBS_JS_TY)) ||
	  	         (sbxxBo.getState().equals(Constants.ZB_ZTBS_DY_HD))
	  	         )
	  	     {
	  	  %>
			<control:operation pageid="viewSbxx" operationid="printHdtzs" checkername="<%=SessionKey.OPERATION_CHECKER%>" defaulthandle="REQUIRED">
			            		<img alt=��ӡ�˶�֪ͨ��   id=Printhdtzs name=Printhdtzs
		onclick="doPrintHdtzs()" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('Printhdtzs','','<%=static_file%>images/q_dyhdtzs2.jpg',1)"
		src="<%=static_file%>images/q_dyhdtzs1.jpg" style="CURSOR: hand">

			</control:operation>
	  	  <%
	  	     }
	  	  %>
	  	  <% if (sbxxBo.getState().equals(Constants.ZB_ZTBS_BC))
	  	     {
	  	  %>
			<control:operation pageid="viewSbxx" operationid="cancel" checkername="<%=SessionKey.OPERATION_CHECKER%>" defaulthandle="REQUIRED">
			            		<img alt=����   id=cancel name=cancel
		onclick="doSubmitForm('Cancel')" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('cancel','','<%=static_file%>images/q_zf2.jpg',1)"
		src="<%=static_file%>images/q_zf1.jpg" style="CURSOR: hand">

			</control:operation>
	  	  <%
	  	     }
	  	  %>

			<control:operation pageid="viewSbxx" operationid="modify" checkername="<%=SessionKey.OPERATION_CHECKER%>" defaulthandle="REQUIRED">
			<logic:equal name="sbxxBo" property="state" value="<%=Constants.ZB_ZTBS_BC%>">
            <IMG alt=�޸� height=22 id=baocun name="btnSave"
            onclick="doSubmitForm('Modify');" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('baocun','','<%=static_file%>images/xiugai2.jpg',1)"
            src="<%=static_file%>images/xiugai1.jpg" style="CURSOR: hand" width=79>
            </logic:equal>
			</control:operation>
			<control:operation pageid="viewSbxx" operationid="delete" checkername="<%=SessionKey.OPERATION_CHECKER%>" defaulthandle="REQUIRED">
			<logic:equal name="sbxxBo" property="state" value="<%=Constants.ZB_ZTBS_BC%>">
            <IMG alt=ɾ�� height=22 id=delete name="btnDelete"
            onclick="doSubmitForm('Delete');" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('delete','','<%=static_file%>images/shanchu2.jpg',1)"
            src="<%=static_file%>images/shanchu1.jpg" style="CURSOR: hand" width=79>
            </logic:equal>
			</control:operation>
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
