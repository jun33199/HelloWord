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
<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.PlslForm2"%>


<%String sfesf="00";
  String setz="2";
%>
<HTML><HEAD><TITLE>�����еط�˰�����˰�걨��ʾ��</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>

<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType){
  document.all.operationType.value=operationType;
  if(operationType=="Modify" )
{
    document.forms[0].operationType.value='Redirect';
    document.forms[0].subaction.value='modify';
  }
  else if (operationType=="Delete" )
 {
     if (!confirm("��ȷ��ɾ�������걨��¼��"))
     {
        return false;
     }
  }

   document.forms[0].submit();
}

//˰�����Ϊ���״ι���90�O������ͨס����ʱ�����ط���Ȩ��ת������Ϊ���������������Ƿ���ַ���¼
function checkSelectOne()
{
  if(document.forms[0].setz.value=="5")
  {
  	var a=document.getElementById("lx").innerHTML;
  	var b=document.getElementById("mj").innerHTML;
  	if(a.substring(6,10)!="��������")
  	{
  		alert("���ء�����Ȩ��ת������Ϊ������������ʱ��˰������ſ���ѡ���״ι���90�O������ͨס����");
  		document.forms[0].setz.value="2";
  	}

  	var ce = parseFloat(b.substring(6,b.length-2))-90;
    if(ce>0)
    {
    	alert("���ݽ������С�ڵ���90�Oʱ��˰������ſ���ѡ���״ι���90�O������ͨס����");
    	document.forms[0].setz.value="2";
    }
  	var ce1 = parseFloat(b.substring(6,b.length-2))-0;
    if(ce1==0)
    {
    	alert("���ݽ����������0�Oʱ��˰������ſ���ѡ���״ι���90�O������ͨס����");
    	document.forms[0].setz.value="2";
    }

  }
}
</SCRIPT>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="��������>�����еط�˰�����˰�걨��ʾ��"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>
<SCRIPT language=javascript>
</SCRIPT>
<br>
 <html:form action="/plsl_swry/plslDetail.do">

 <TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>

         <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>�����еط�˰�����˰�걨��ʾ��</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
              <html:hidden property="operationType" value=""/>

            <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>
			  <BR>
			  <TR>
			  	<TD class=2-td2-t-left width="12%">��˰�걨���</TD>


			  	<TD class=2-td2-t-left width="43%" colspan=3><DIV align=left>&nbsp; </DIV></TD>

			  </TR>
			  <TR>
			  	<TD class=2-td2-left width="12%">�ɿʽ&nbsp;</TD>
			  	<TD class=2-td2-left width="43%"> <DIV align=left>
                                  &nbsp;
				<TD class=2-td2-left width="22%">�������ع����������&nbsp;</TD>
				<TD class=2-td2-center width="23%"><DIV align=left>&nbsp;

				<logic:equal name="plslForm2" property="tdbm"  value="1">
				<bean:write name="plslForm2" property="tufwxx.fwtdbmdm" />
				</logic:equal>
				</DIV></TD>
			  </TR>
			  </TBODY>
			  </TABLE>
			  <br>
            <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
               <TR>
                <TD class=2-td2-t-left width="10%">˰�����</TD>
				<TD class=2-td2-t-left><select name="setz" onchange="checkSelectOne()"><option value="2" checked>ȫ������</option>
						<option value="1">��������</option>
				<logic:equal name="plslForm2" property="person"  value="1">
						<option value="5">�״ι���90�O������ͨס��</option>
                        <option value="6">�������÷�</option>
				</logic:equal></select>
							<font color=red>���ڷ�����ͨ��׼סլ��׼���걨���ݣ���˰�۸���ݴ���ȷ����</font></TD>
				<TD class=2-td2-t-center>&nbsp;
                    <input type="checkbox" name="sfesf" value="01">
                    �Ƿ�Ϊ���ַ�</TD>
			</TABLE>
			<TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>

<logic:equal name="plslForm2" property="person"  value="1">
              <TR>
                <TD class=2-td2-t-left width="10%" rowspan = "3">
                  <DIV align=right>������</DIV>
                   <DIV align=right>д����</DIV></TD>

                  <TD class=2-td2-t-left width="20%">
                   <DIV align=right>��˰������&nbsp; </DIV>
                </TD>

                <DIV align=right>&nbsp; </DIV>
                  <TD class=2-td2-t-left width="20%">
                    <DIV align=left>&nbsp;<bean:write name="plslForm2" property="grxx.nsrmc" /> </DIV></TD>
                <TD class=2-td2-t-left width="25%">
                  <DIV align=right>��ϵ�绰&nbsp; </DIV></TD>
                <TD colspan="2"  class=2-td2-t-center width="25%">
                   <DIV align=left>&nbsp;<bean:write name="plslForm2" property="grxx.lxdh" /> </DIV></TD>
                  </TR>
               <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>���֤������&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=left>
                   &nbsp; <bean:write name="plslForm2" property="grxx.sfzjlxmc"/>
                    </DIV></TD>
                <TD   class=2-td2-left >
                  <DIV align=right>���֤������&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center>
                   <DIV align=left>&nbsp;<bean:write name="plslForm2" property="grxx.sfzjhm"/> </DIV></TD>
                  </TR>
               <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>����&nbsp; </DIV>
                </TD>
                <TD class=2-td2-center colspan="4">
                  <DIV align=left>
                    &nbsp;<bean:write name="plslForm2" property="grxx.gjmc"/>
                    </DIV></TD>
                  </TR>
</logic:equal>



<logic:equal name="plslForm2" property="person"  value="0">
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
                        &nbsp;<bean:write name = "plslForm2" property = "fgrxx.jsjdm" />
                    </DIV>
                  </TD>

                <TD class=2-td2-t-left width="20%">
                  <DIV align=right>��˰������&nbsp; </DIV></TD>

                <TD colspan="2"  class=2-td2-t-center width="25%">
                   <DIV align=left>
                       &nbsp;<bean:write name = "plslForm2" property = "fgrxx.nsrlxmc" />
                   </DIV>
                </TD>
             </TR>
             <TR>

                <TD  class=2-td2-left >
                  <DIV align=right>��˰������&nbsp;</DIV></TD>

                <TD colspan="4"  class=2-td2-center style="word-break:break-all" >
                   <DIV align=left>
                       &nbsp;<bean:write name = "plslForm2" property = "fgrxx.nsrmc" />
                   </DIV>
                 </TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>��������&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=left>
                      &nbsp;<bean:write name = "plslForm2" property = "fgrxx.khyhmc" />
                  </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=right>�����˺�&nbsp;</DIV></TD>
                <TD colspan="2" class=2-td2-center >
                   <DIV align=left>
                       &nbsp;<bean:write name = "plslForm2" property = "fgrxx.yhzh" />
                   </DIV>
                 </TD>
                </TR>
                <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>��ϵ������&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                   <DIV align=left>
                       &nbsp;<bean:write name = "plslForm2" property = "fgrxx.lxrxm" />
                   </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=right>��ϵ�绰&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center>
                   <DIV align=left>
                       &nbsp;<bean:write name = "plslForm2" property = "fgrxx.lxdh" />
                   </DIV>
                </TD>
                </TR>
</logic:equal>


<logic:equal name="plslForm2" property="sp"  value="1">
				<bean:define id="spxx" name="plslForm2" property="spjgxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx"/>
               <TR>
                <TD class=2-td2-left rowspan = "2">
                  <DIV align=right>˰�����</DIV>
                  <DIV align=right>��˼���˰</DIV></TD>
                 <TD class=2-td2-left >
                   <DIV align=right>����˰�����ֺ�&nbsp; </DIV>
                </TD>
                <TD colspan="4" class=2-td2-center >
                 <DIV align=left>&nbsp;<bean:write name="plslForm2" property="spjgxx.hdtzszh"/> </DIV></TD>
              </TR>
               <TR>
                  <TD class=2-td2-left >
                   <DIV align=right>����˰���&nbsp; </DIV>
                </TD>
                  <TD colspan="4" class=2-td2-center >
                   <DIV align=left>&nbsp;<%=DataConvert.BigDecimal2String(spxx.getJmsje())%></DIV>
                </TD>
                  </TR>
</logic:equal>

<logic:equal name="plslForm2" property="sp"  value="0">
               <TR>
                <TD class=2-td2-left rowspan = "2">
                  <DIV align=right>˰�����</DIV>
                  <DIV align=right>��˼���˰</DIV></TD>
                 <TD class=2-td2-left >
                   <DIV align=right>����˰�����ֺ�&nbsp; </DIV>
                </TD>
                <TD colspan="4" class=2-td2-center >
                 <DIV align=left>&nbsp;</DIV></TD>
              </TR>
               <TR>
                  <TD class=2-td2-left >
                   <DIV align=right>����˰���&nbsp; </DIV>
                </TD>
                  <TD colspan="4" class=2-td2-center >
                   <DIV align=left>&nbsp;</DIV>
                </TD>
                  </TR>
</logic:equal>

                 <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>��ע&nbsp; </DIV>
                </TD>
                <TD colspan="5" class=2-td2-center >
                  <DIV align=left>&nbsp;<bean:write name="plslForm2" property="tufwxx.bz" />
				</DIV></TD>
                </TR>

	<!-- ���ݻ�����Ϣ-->
    		    <bean:define id="fwtdVo" name="plslForm2" property="tufwxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx" />
				  <tr>
				    <TD class=2-td2-left  rowspan = "7">
				      <DIV align=right>	���ط���</DIV>
				      <DIV align=right>Ȩ��ת��</DIV>
                      </TD>
				      <TD class=2-td2-left >
				        <DIV align=right>���ز���Ŀ����&nbsp;</DIV></TD>
				        <TD colspan="4" class=2-td2-center style="word-break:break-all">
				          <DIV align=left>&nbsp;<bean:write name="fwtdVo" property="fdcxmmc" /> </DIV></TD>
				  </TR>
				  <TR>
			          <TD class=2-td2-left >
			            <DIV align=right>��Լǩ��ʱ��&nbsp; </DIV>
			          </TD>
			          <TD class=2-td2-left >
			            <DIV align=left>
                                    &nbsp;<%=DataConvert.TimeStamp2String(fwtdVo.getHtqdsj())%>
									  <%sfesf=fwtdVo.getSfesf();
									    setz=fwtdVo.getSetz();
									  %>
                                    </DIV></TD>

			            <TD class=2-td2-left >
			              <DIV align=right>����&nbsp;</DIV></TD>
			              <TD colspan="2"  class=2-td2-center >
			                <DIV align=left>
			                  &nbsp;<bean:write  name="fwtdVo" property="flmc"/>&nbsp
			                </DIV></TD>
		          </TR>

		          <TR>
		            <TD class=2-td2-left >
		              <DIV align=right>���ء�����Ȩ��ת������&nbsp; </DIV> </TD>
		              <TD class=2-td2-left >
		                <DIV align=left id="lx">
		                  &nbsp;<bean:write  name="fwtdVo" property="tdfwqszymc"/>&nbsp; </DIV></TD>
		                  <TD class=2-td2-left >
		                    <DIV align=right>�������&nbsp;</DIV></TD>
		                    <TD colspan="2"  class=2-td2-center >
		                      <DIV align=left>
		                        &nbsp;<bean:write  name="fwtdVo" property="fwlxmc"/>&nbsp; </DIV></TD>
		          </TR>
		          <TR>
		            <TD  class=2-td2-left >
		              <DIV align=right>���ء����������ַ&nbsp; </DIV>
		            </TD>
		            <TD colspan="4"  class=2-td2-center style="word-break:break-all">
		              <DIV align=left>&nbsp;<bean:write name="fwtdVo" property="tdfwzldz" /></DIV></TD>
		            </TR>
                    <TR>
                      <TD class=2-td2-left >
                        <DIV align=right>���ء�����Ȩ��ת�����&nbsp; </DIV>
                      </TD>
                      <TD  class=2-td2-left >
                        <DIV align=left>&nbsp;���أ�
                          <%=DataConvert.BigDecimal2String(fwtdVo.getTdfwqszymj())%>
                          �O </DIV></TD>
                        <TD class=2-td2-left >
                          <DIV align=right>���ݽ������&nbsp;</DIV></TD>
                          <TD colspan="2"  class=2-td2-center >
                            <DIV align=left id="mj">
                           &nbsp;<%=DataConvert.BigDecimal2String(fwtdVo.getFwjzmj())%>
                              �O</DIV></TD>
                    </TR>
                    <TR>
	                    <TD class=2-td2-left rowspan = "2">
	                      <DIV align=right>�ɽ��۸������۸�&nbsp;</DIV>
	                      <TD  class=2-td2-left >
	                        <DIV align=left>
                                  &nbsp;<%=DataConvert.BigDecimal2String(fwtdVo.getCjjgrmb())%>
                                  Ԫ(�����) </DIV></TD>
	                        <TD class=2-td2-left >
	                          <DIV align=right>˰����غ˶��۸�&nbsp; </DIV></TD>
	                          <TD  colspan="2"  class=2-td2-center >
	                            <DIV align=left>
                                     &nbsp; <%=DataConvert.BigDecimal2String(fwtdVo.getPgjgrmb())%>
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


      <logic:iterate id="cqdata" indexId="cqindex" length="length" name="plslForm2" property="cqxxList"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq">

                <TR>
                  <TD class=2-td2-left  rowspan = "4">
                  <DIV align=right>	��Ǩ</DIV>
                   <DIV align=right>���</DIV>
                  </TD>

                  <TD class=2-td2-center style="word-break:break-all">
                   <DIV align=right>����Ǩ���������ַ&nbsp; </DIV>
                </TD>
                <TD colspan="4" class=2-td2-center >
                  <DIV align=left>
                      &nbsp;<bean:write name = "cqdata" property = "zldz"/>
                  </DIV>
                </TD>
                </TR>
                <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>��ǨЭ�����&nbsp; </DIV>
                </TD>
                <TD colspan="4" class=2-td2-center >
                <DIV align=left>
                    &nbsp;<bean:write name = "cqdata" property = "cqxyh"/>
                </DIV>
                </TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left >
                   <DIV align=right> ��Ǩ������&nbsp; </DIV>
                </TD>
                <TD  class=2-td2-center  colspan=4>
                  <DIV align=left>
                      &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getCqbce())%>
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
                     &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getBcsybce())%>
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

      <logic:iterate id="gydata" indexId="gfindex" length="length" name="plslForm2" property="gyzfxxList"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf">

               <TR>
                   <TD class=2-td2-left  rowspan = "6">
                  <DIV align=right>	�ѹ�����ס��</DIV>
                   <DIV align=right>���г������</DIV>
				   <br>

				   </TD>
                  <TD   class=2-td2-left >
                   <DIV  align=right>���ۺ�ͬ����&nbsp;</DIV>
                </TD>
                <TD colspan="4" class=2-td2-center >
                  <DIV align=left>&nbsp;<bean:write name="gydata" property="yggyzfqszsh" /></DIV></TD>
                  </TR>
                <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>�����ַ&nbsp; </DIV>
                </TD>
                 <TD colspan="4" class=2-td2-center style="word-break:break-all">
                  <DIV align=left>&nbsp;<bean:write name="gydata" property="zldz" /></DIV></TD>
                  </TR>
                  <TR>
                 <TD  class=2-td2-left >
                   <DIV align=right>���ۺ�ͬ����Լ��ǩ��ʱ��&nbsp;</DIV>
                </TD>
                <TD colspan="4" class=2-td2-center >
                  <DIV align=left>
                  &nbsp;<%=DataConvert.TimeStamp2String(gydata.getQdsj())%>
                  </DIV></TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left >
                   <DIV align=right> �������&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                   <DIV align=left>
                     &nbsp;<%=DataConvert.BigDecimal2String(gydata.getJzmj())%>
                     �O </DIV></TD>
                <TD class=2-td2-left >
                  <DIV align=right>�ɽ��۸�&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center >
                  <DIV align=left>
                     &nbsp;<%=DataConvert.BigDecimal2String(gydata.getCjjg())%>
                    Ԫ(�����)</DIV></TD>
                  </TR>
                 <TR>
                <TD class=2-td2-left >
                  <DIV align=right>���εֿ۶�&nbsp;</DIV></TD>
                <TD colspan="4"  class=2-td2-center >
                  <DIV align=left>
                     &nbsp;<%=DataConvert.BigDecimal2String(gydata.getBcdke())%>
                    Ԫ(�����)</DIV></TD>
                  </TR>
                   <TR>
                   <TD class=2-td2-left >
                  <DIV align=right>ʣ���&nbsp;</DIV></TD>
                <TD colspan="4"  class=2-td2-center >
                  <DIV align=left>
                     &nbsp;<%=DataConvert.BigDecimal2String(gydata.getSye())%>
                    Ԫ(�����)</DIV></TD>
                  </TR>

       </logic:iterate>



           <!-- ��ʾ����������Ϣ-->
      <logic:iterate id="fwjhBo" indexId="fjindex" length="length" name="plslForm2" property="fwjhxxList"
            type="com.creationstar.bjtax.qsgl.model.bo.PldrBo2">

              <%
					if(fwjhBo.grxx!=null)
					{
				%>

                <bean:define id="dfgrxx" name="fwjhBo" property="grxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx"/>
              <%
					}

			  else
			  {
			%>
                <bean:define id="dffgrxx" name="fwjhBo" property="fgrxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx"/>
              <%
					}
				%>

              <bean:define id="dffwtdxx" name="fwjhBo" property="tufwxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"/>
              <!--TR>
                <TD class=2-td2-left >�Է��ɿʽ</TD>
                <TD class=2-td2-left > <DIV align=left>
                  <bean:write name="fwjhBo" property="jkfsmc" /></td>
                  <TD class=2-td2-left >�Է��������ع����������</TD>
                  <TD colspan="3" class=2-td2-center ><DIV align=left><bean:write name="fwjhBo" property="fcjslh" />&nbsp;</DIV></TD>
                </TR-->
                <!--����������Ϣ��ʾ-->
              <%
					if(fwjhBo.grxx!=null)
					{
				%>
                  <TR>
                    <TD class=2-td2-left  rowspan = "3">
                      <DIV align=right>�Է�������</DIV>
                      <DIV align=right>д����</DIV></TD>
                      <TD class=2-td2-left >
                        <DIV align=right>��˰������&nbsp; </DIV>
                      </TD>
                      <DIV align=right>&nbsp; </DIV>
                      <TD class=2-td2-left style="word-break:break-all">
                        <DIV align=left>&nbsp; <bean:write name="dfgrxx" property="nsrmc" /></DIV></TD>
                        <TD class=2-td2-left >
                          <DIV align=right>��ϵ�绰&nbsp; </DIV></TD>
                          <TD colspan="2"  class=2-td2-center >
                            <DIV align=left>&nbsp;<bean:write name="dfgrxx" property="lxdh" /></DIV></TD>
                          </TR>
                  <TR>
                    <TD class=2-td2-left >
                      <DIV align=right>���֤������&nbsp; </DIV>
                    </TD>
                    <TD class=2-td2-left >
                      <DIV align=left>
                        &nbsp;<bean:write name="dfgrxx" property="sfzjlxmc" />
                      </DIV></TD>
                      <TD   class=2-td2-left >
                        <DIV align=right>���֤������&nbsp;</DIV></TD>
                        <TD colspan="2"  class=2-td2-center >
                          <DIV align=left>&nbsp;<bean:write name="dfgrxx" property="sfzjhm" /></DIV></TD>
                        </TR>

                <TR>
                  <TD class=2-td2-left >
                    <DIV align=right>����&nbsp; </DIV>
                  </TD>
                  <TD class=2-td2-left >
                    <DIV align=left>
                      &nbsp;<bean:write name="dfgrxx" property="gjmc" />
                    </DIV></TD>
                    <TD   class=2-td2-left >
                      <DIV align=right>&nbsp;</DIV></TD>
                      <TD colspan="2"  class=2-td2-center >
                        <DIV align=left>&nbsp;</DIV></TD>
                      </TR>
              <%
					}
			  else
			  {
			%>
            <!--�����Ǹ�����Ϣ��ʾ-->
              <TR>
                <TD class=2-td2-left rowspan = "4">
              <DIV align=right>�Է��Ǹ���</DIV>
              <DIV align=right>��д����</DIV></TD>
              <TD class=2-td2-left >
                <DIV align=right>���������&nbsp; </DIV>
              </TD>
              <DIV align=right>&nbsp; </DIV>
              <TD class=2-td2-left >
                <DIV align=left>&nbsp; <bean:write name="dffgrxx" property="jsjdm" /></DIV></TD>
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
                    &nbsp;<bean:write name="dffgrxx" property="nsrmc" />
                  </DIV></TD>
                  <TD   class=2-td2-left >
                    <DIV align=right>��˰������&nbsp;</DIV></TD>
                    <TD colspan="2"  class=2-td2-center >
                         <DIV align=left>&nbsp;<bean:write name="dffgrxx" property="nsrlxmc" /></DIV></TD>
                        </TR>

            <TR>
              <TD class=2-td2-left >
                <DIV align=right>��������&nbsp; </DIV>
              </TD>
              <TD class=2-td2-left >
                <DIV align=left>
                  &nbsp;<bean:write name="dffgrxx" property="khyhmc" />
                </DIV></TD>
                <TD   class=2-td2-left >
                  <DIV align=right>�����˺�&nbsp;</DIV></TD>
                  <TD colspan="2"  class=2-td2-center >
                    <DIV align=left>&nbsp;<bean:write name="dffgrxx" property="yhzh" /></DIV></TD>
                  </TR>


              <TR>
                <TD class=2-td2-left >
                  <DIV align=right>��ϵ������&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=left>
                    &nbsp;<bean:write name="dffgrxx" property="lxrxm" />
                  </DIV></TD>
                  <TD   class=2-td2-left >
                    <DIV align=right>��ϵ�绰&nbsp;</DIV></TD>
                    <TD colspan="2"  class=2-td2-center >
                      <DIV align=left>&nbsp;<bean:write name="dffgrxx" property="lxdh" /></DIV></TD>
                    </TR>
              <%
					}
				%>

              <TR>
                <TD class=2-td2-left rowspan = "7">
                  <DIV align=right>	�������ط���</DIV>
                  <DIV align=right>Ȩ��ת��</DIV>

                  </TD>


                  <TD class=2-td2-left >
                    <DIV align=right>���ز���Ŀ����&nbsp;</DIV></TD>
                    <TD colspan="4" class=2-td2-center style="word-break:break-all">
                      <DIV align=left>&nbsp;<bean:write name="dffwtdxx" property="fdcxmmc" /></DIV></TD>
                    </TR>
	            <TR>
	              <TD class=2-td2-left >
	                <DIV align=right>��Լǩ��ʱ��&nbsp; </DIV>
	              </TD>
	              <TD class=2-td2-left >
	                <DIV align=left>&nbsp;<%=DataConvert.TimeStamp2String(dffwtdxx.getHtqdsj())%> </DIV></TD>
	                <TD class=2-td2-left >
	                  <DIV align=right>����&nbsp;</DIV></TD>
	                  <TD colspan="2"  class=2-td2-center >
	                    <DIV align=left>
	                      &nbsp;<bean:write  name="dffwtdxx" property="flmc"/>

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
                        &nbsp;<bean:write name="dffwtdxx" property="tdfwzldz" />
                      </DIV>
                    </DIV></TD>
                  </TR>
                  <TR>
                    <TD class=2-td2-left >
                      <DIV align=right>���ء�����Ȩ��ת�����&nbsp; </DIV>
                    </TD>
                    <TD  class=2-td2-left >
                      <DIV align=left>���أ�
                        &nbsp;<%=DataConvert.BigDecimal2String(dffwtdxx.getTdfwqszymj())%>
                        �O </DIV></TD>
                        <TD class=2-td2-left >
                          <DIV align=right>���ݽ������&nbsp;</DIV></TD>
                          <TD colspan="2"  class=2-td2-center >
                            <DIV align=left>
                              &nbsp;<%=DataConvert.BigDecimal2String(dffwtdxx.getFwjzmj())%>
                              �O</DIV></TD>
                            </TR>
                <TR>
                  <TD class=2-td2-left rowspan = "2">
                    <DIV align=right>�ɽ��۸������۸�</DIV>
                    <TD  class=2-td2-left >
                      <DIV align=left>
                        &nbsp;<%=DataConvert.BigDecimal2String(dffwtdxx.getCjjgrmb())%>
                        Ԫ(�����) </DIV></TD>
                        <TD class=2-td2-left >
                          <DIV align=right>˰����غ˶��۸�&nbsp; </DIV></TD>
                          <TD  colspan="2"  class=2-td2-center >
                            <DIV align=left>
                              &nbsp;<%=DataConvert.BigDecimal2String(dffwtdxx.getPgjgrmb())%>
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
      </logic:iterate>




                  <BR>


			<BR>
</TBODY></TABLE>


            <BR>
			<BR>

	<table>


	<logic:equal name="plslForm2" property="back"  value="dr">
	<tr >
			<td align=center>
			<IMG alt=���� height=22 id=baocun name=baocun
            onclick="doSubmitForm('Save')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
            src="<%=static_file%>images/baocun1.jpg" style="CURSOR: hand" width=79>
            <IMG alt=�˳� height=22 id=tuichu name=tuichu
            onclick="doSubmitForm('Return')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
            src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand" width=79>
			</td>
	</tr>
	</logic:equal>

	<logic:equal name="plslForm2" property="back"  value="cx">
		<tr>
		<logic:equal name="plslForm2" property="del"  value="1">
			<td  class="black9" align=center>
                <input type="image" name="delete" value="ɾ��" alt="ɾ��"
                onclick = "javascript:return doSubmitForm('Delete');"
                onMouseOver="MM_swapImage('shanchu','','<%=static_file%>images/shanchu2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shanchu1.jpg"
                width="79" height="22" id="shanchu">
            </td>
		</logic:equal>

			<td align=center>
            <IMG alt=�˳� height=22 id=tuichu name=tuichu
            onclick="doSubmitForm('Returncx')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
            src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand" width=79>
			</td>
		</tr>
	</logic:equal>


    </table>
			<BR>

</html:form>
<script language="javascript" type='text/JavaScript'>
var sfesf="<%=sfesf%>";
var setz="<%=setz%>";
var objesf=document.all.sfesf;
var objsetz=document.all.setz;
if(objesf.value==sfesf){
	objesf.checked=true;
}
for(i=0;i<objsetz.length;i++){
	if(objsetz[i].value==setz){
		objsetz[i].selected=true;
	}
}
</script>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
