<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.ActionUtil"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.model.bo.HdtzsBo"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Hdjmmx"%>
<%@ page import="com.ttsoft.framework.util.Currency"%>

<HTML><HEAD><TITLE>��˰�˶�֪ͨ��(��˰)</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<META content="MSHTML 5.00.2919.6307" name=GENERATOR></HEAD>
<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<BR>
<TABLE align=center border=0  height="25%" width=600 >
 <TBODY>
<html:form action="/qssb/printHdtzs.do">
<bean:define id="hdtzsbo" name="hdtzsForm" property="hdtzsbo" type="com.creationstar.bjtax.qsgl.model.bo.HdtzsBo"/>
<bean:define id="voTufwxx" name="hdtzsbo" property="voTufwxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"/>
<tr>
	<td colspan=2 align=center>
	<FONT FACE="����_GB2312"  SIZE=4><div ALIGN="CENTER">��˰�˶�֪ͨ��</div><br>
	</td>
	</tr>
<tr>
	<td colspan=2 align=right>
	<FONT FACE="����_GB2312"  SIZE=2> 
	
	<%if (hdtzsbo.isBz())
	  {
	%>
	��ţ�
	<%
	  }
	%>
	
	<%
	String printHdtzsh=hdtzsbo.getVoHdtzs().getHdtzsh();
	//Q212����ͨ����˰�����֣�ͨ[2005]����14��
	if (printHdtzsh.startsWith("Q")){
	   printHdtzsh=printHdtzsh.substring(4);
	}
	out.println(printHdtzsh);
	%>
	<!--bean:write name="hdtzsbo" property="voHdtzs.hdtzsh" /-->
	</td>
	</tr>
<tr>
	<td colspan=2>
	<FONT FACE="����_GB2312"  SIZE=3><P>������<U>&nbsp; <%=hdtzsbo.getVoHdtzs().getSqr() %>  &nbsp;�� </U></P></td></tr>
<tr><td colspan=2>&nbsp;</td></tr>
<tr>
	<td colspan=2>
	<P style="word-break:break-all">&nbsp;&nbsp;&nbsp;&nbsp;<FONT SIZE=3>
	<%if (hdtzsbo.isBz())
	  {
	%>
	�����ܵ���Ʒ�������أ���Ŀ���ƣ�
	<%
	    }
	%>
	<%if (!hdtzsbo.isBz())
	  {
	%>
	�����õ���Ʒ����Ŀ���ƣ�
	<%
	    }
	%>
	<U>&nbsp; <%=hdtzsbo.getVoHdtzs().getSpfxmmc() %>&nbsp;</U>��</FONT><FONT SIZE=3>
		�����ַ��<U>&nbsp; <%=hdtzsbo.getVoHdtzs().getZldi()%> &nbsp; </U></FONT>
	
		<P style="word-break:break-all">
		<FONT SIZE=3>
		���ط���Ȩ��ת�����������ʹ��Ȩ�����<U><%=DataConvert.BigDecimal2String(voTufwxx.getTdfwqszymj())  %>&nbsp;</U>�O
			,���ݽ��������<U><%=DataConvert.BigDecimal2String(voTufwxx.getFwjzmj())  %>&nbsp;</U>�O
		
		</P>
		
		<P style="word-break:break-all">
		<%
		if (!hdtzsbo.isBz())
		{
		%>
			
			<FONT SIZE=3>
		�ɽ��۸�Сд����<U>&nbsp; <%=DataConvert.BigDecimal2String(hdtzsbo.getVoHdtzs().getCjjg())  %>&nbsp;</U>Ԫ
		
				����˰���ݣ�Сд����<U>&nbsp;  <%=DataConvert.BigDecimal2String(hdtzsbo.getVoHdtzs().getJsyj())%>&nbsp;</U> Ԫ��������˰��Сд����<U>&nbsp; <%=DataConvert.BigDecimal2String(hdtzsbo.getVoHdtzs().getJzqs())%> &nbsp;</U> Ԫ��
		<%
		    }
		%>
		</P>
		
	</td></tr>
<tr><td colspan=2>&nbsp;</td></tr>
<%if (hdtzsbo.isBz())
  {
%>
<tr>
	<td colspan=2>
	<P style="word-break:break-all"><FONT SIZE=3>&nbsp;&nbsp;&nbsp;&nbsp;���ݣ�<U>&nbsp; <%=ActionUtil.getBzQkLy(session.getServletContext(),hdtzsbo.getFwlxdm(),hdtzsbo.getVoHdtzs().getSbbh())%> &nbsp;</U>
	<!-- ���й� -->���߹涨�����ڲ�����˰��Χ��
	</FONT> </P>
	</td></tr>
<tr><td colspan=2>&nbsp;&nbsp;&nbsp;&nbsp;�ش�֤����</td></tr>
<%
   }
   else
   {
   HashMap nrMap = (HashMap)hdtzsbo.getJmnrMap();
   Hdjmmx cqjm = null;
   Hdjmmx gyjm = null;
   Hdjmmx ptjm = null;
   Hdjmmx jjsyjm = null;
   if (nrMap != null)
   {
      cqjm = (Hdjmmx)nrMap.get(Constants.JMZC_CQFW);
      gyjm = (Hdjmmx)nrMap.get(Constants.JMZC_GYZF);
      ptjm = (Hdjmmx)nrMap.get(Constants.JMZC_PTZZ);
      jjsyjm = (Hdjmmx)nrMap.get(Constants.JMZC_JJSYZF);
   }
%>
<%if (gyjm != null)
  {
%>
<tr>
	<td colspan=2>
	<P style="word-break:break-all"><FONT SIZE=3>&nbsp;&nbsp;&nbsp;&nbsp;���ݣ�<U>��������������������ӡ���������ѹ�����ס�����г���ʵʩ
�취��֪ͨ���͡������в����ֱ����еط�˰��ֱ����й�����Դ�ͷ��ݹ���ֹ����ѹ�����ס�����г����й���˰���ߵ�֪ͨ��
</U>�涨�����ڼ�˰�����п۳������ѹ�����ס������ <U>&nbsp; <%=DataConvert.BigDecimal2String(gyjm.getJmje())%>&nbsp; </U> Ԫ��</FONT> </P>
	</td></tr>
<tr><td colspan=2>&nbsp;</td></tr>
<%
   }
%>

<%if (jjsyjm != null)
  {
%>
<tr>
	<td colspan=2>
	<P style="word-break:break-all"><FONT SIZE=3>&nbsp;&nbsp;&nbsp;&nbsp;���ݣ�<U>�����ڹ᳹���췢��2008��131���ļ�����ٽ����з��ز��г�������չ
��ʵʩ������۾����졲2009��43�ţ�		
</U>�涨�����ڼ�˰�����п۳������ѹ���������ס������ <U>&nbsp; <%=DataConvert.BigDecimal2String(jjsyjm.getJmje())%>&nbsp; </U> Ԫ��</FONT> </P>
	</td></tr>
<tr><td colspan=2>&nbsp;</td></tr>
<%
   }
%>

<%if (cqjm != null)
  {
%>
<tr>
	<td colspan=2>
	<P style="word-break:break-all"><FONT SIZE=3>&nbsp;&nbsp;&nbsp;&nbsp;���ݣ�<U>����������˰����涨���������ڣ�������<��������100����>�򡶹�����ȷ��˰���ߺ�ִ�����й������֪ͨ��<����˰��2004��16��>
</U>����˰���߹涨����׼������˰��Сд����<U>&nbsp; <%=DataConvert.BigDecimal2String(cqjm.getJmje())%>&nbsp;</U> Ԫ��</FONT> </P>
	</td></tr>
<tr><td colspan=2>&nbsp;</td></tr>
<%
   }
%>

<%if (ptjm != null)
  {
%>
<tr>
	<td colspan=2>
	<P style="word-break:break-all"><FONT SIZE=3>&nbsp;&nbsp;&nbsp;&nbsp;���ݣ�<U>�������в����ֵġ������еط�˰���ת��������������˰��ֹ��ڵ������ز��г�����˰�����ߵ�֪ͨ��������˰��1999��1201�ţ�����ת���������������������޸ı�������˰����涨�ľ�����֪ͨ��������˰��2002��1926�ţ�
</U>����˰���߹涨����׼������˰��Сд����<U>&nbsp; <%=DataConvert.BigDecimal2String(ptjm.getJmje())%> &nbsp;</U> Ԫ��</FONT> </P>
	</td></tr>
<tr><td colspan=2>&nbsp;</td></tr>
<%
   }
%>
<%if (cqjm != null)
  {
%>
<tr>
	<td colspan=2>
	<P style="word-break:break-all"><FONT SIZE=3>&nbsp;&nbsp;&nbsp;&nbsp;��˰����غ˶���ʵ��Ӧ����˰��Сд����<U> &nbsp;&nbsp;<%=DataConvert.BigDecimal2String(hdtzsbo.getVoHdtzs().getSjyz())%>&nbsp;
</U>Ԫ��������Ҵ�д����<U>&nbsp;&nbsp;<%=Currency.convert(hdtzsbo.getVoHdtzs().getSjyz())%>&nbsp;&nbsp;</U> ��</FONT> </P>
	</td></tr>
<tr><td colspan=2>&nbsp;&nbsp;&nbsp;&nbsp;�ش�֤����</td></tr>
<%
   }
   }
%>


<tr>
	<td colspan=2>
	<br><br><br><br><br><br>
	<%
		if (hdtzsbo.isBz())
  		{
	%>
	<P style="word-break:break-all">&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;�գ��£�</P>
	<BR><br><br><br>
	<%
		}
		else
		{
    %>
	<P style="word-break:break-all"><!-- �����˽���ǩ�������--><br>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��</P>
	<br><br><br><br>
	<%
		}
	%>
	</td></tr>

<tr>
	<td valign="top" width="10%">
	<P>˵��:</P>
	</td>
	<td>
	<P style="word-break:break-all">��֤��һʽ���ݣ�һ����˰��������汸�飬���ݽ���˰�ˣ�����һ���ɹ��������������Ű������ء�����Ȩ��ת�Ʊ���Ǽ�����ʱ���档</p>
	</td></tr>
<tr><td colspan=2>&nbsp;</td></tr>
<tr>
	<td colspan=2>
	<P>��֤���������⣬�뼰ʱ���ҵ�λ��ϵ��</P>
	</td></tr>
<tr><td colspan=2>&nbsp;</td></tr>
<tr>
	<td colspan=2>
	<P> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����ˣ�&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;  ��ϵ�绰��</P>
	</td></tr>
</html:form>
</TBODY></TABLE>

</BODY>
</HTML>
