<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@page import="com.creationstar.bjtax.qsgl.util.ActionUtil"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page
	import="com.creationstar.bjtax.qsgl.VisionLogic.form.JmsbxxForm"%>
<%
			JmsbxxForm jmsbxxForm = (JmsbxxForm) session
			.getAttribute("jmsbxxForm");
	if (jmsbxxForm == null)
		jmsbxxForm = (JmsbxxForm) request.getAttribute("jmsbxxForm");
	

    String qtjmlybeizhu = jmsbxxForm.getQtjmlybeizhu();

    String qsjmlbmc = jmsbxxForm.getQsjmlbmc();
	
%>


<HTML>
<HEAD>
<TITLE>��ӡ�����걨��</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<META content="MSHTML 5.00.2919.6307" name=GENERATOR>
</HEAD>
<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0"
	marginwidth="0">

<html:form action="/jmsb/printJmsbxx.do">

	<bean:define id="sbzb" name="jmsbxxForm" property="voSbzb"
		type="com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb" />

	<%
		
		String grNsrmc = ActionUtil.getNsrmc(jmsbxxForm.getNsrList(), null);
	%>


	<logic:equal name="sbzb" property="yhbs"
		value="<%=Constants.ZB_YHBS_GR%>">
		<bean:define id="grxx" name="jmsbxxForm" property="voGrxx"
			type="com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx" />
	</logic:equal>

	<logic:notEqual name="sbzb" property="yhbs"
		value="<%=Constants.ZB_YHBS_GR%>">
		<bean:define id="fgrxx" name="jmsbxxForm" property="voFgrxx"
			type="com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx" />
	</logic:notEqual>

	<bean:define id="tufwxx" name="jmsbxxForm" property="voTufwxx"
		type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx" />



	<P ALIGN="RIGHT"><font size="2">��ţ�<bean:write
		name="jmsbxxForm" property="printSbbh" /></font></P>
	<B> <logic:equal name="sbzb" property="yhbs"
		value="<%=Constants.ZB_YHBS_GR%>">
		<P ALIGN="CENTER">�����еط�˰�����˰�����걨��-����</P>
	</logic:equal> <logic:notEqual name="sbzb" property="yhbs"
		value="<%=Constants.ZB_YHBS_GR%>">
		<P ALIGN="CENTER">�����еط�˰�����˰�����걨��-�Ǹ���</P>
	</logic:notEqual> </B>

	<P ALIGN="CENTER"><font size="2">�걨ʱ�䣺 <%=DataConvert.TS2JksDate((Timestamp) sbzb.getSbrq())%>
	 </font></p>

	<TABLE BORDER=1 CELLSPACING=0 Align="center" CELLPADDING=5 WIDTH=707>


		<!--����-->
		<logic:equal name="sbzb" property="yhbs"
			value="<%=Constants.ZB_YHBS_GR%>">

			<TR>
				<TD VALIGN="MIDDLE" COLSPAN="2">
					<font size="2">��˰������</font>
				</TD>
				<TD VALIGN="TOP" COLSPAN="4" >
					<font size="2"><%=grNsrmc%>&nbsp;</font>
				</TD>
			</TR>

			<TR>
				<TD VALIGN="MIDDLE" COLSPAN="2">
					<font size="2">˰���������루��λ��</font>
				</TD>
				<TD VALIGN="TOP" COLSPAN="2">
					<font size="2">&nbsp;</font>
				</TD>
				<TD VALIGN="TOP" >
					<font size="2">��ϵ�绰</font>
				</TD>
				<TD VALIGN="TOP" >
					<font size="2"><bean:write
					name="grxx" property="lxdh" />&nbsp;</font>
				</TD>
			</TR>

				<TD VALIGN="TOP"  COLSPAN="2">
					<font size="2">��˰������</font>
				</TD>
				<TD VALIGN="TOP" COLSPAN="4" >
					<font size="2">����&nbsp;</font>
				</TD>

			<TR>
				<TD VALIGN="TOP" COLSPAN="2">
					<font size="2">��˰�˸��˺Ϸ�<br>�����֤������</font>
				</TD>
				<TD VALIGN="TOP" COLSPAN="2">
					<font size="2"><bean:write
					name="grxx" property="sfzjlxmc" />&nbsp;</font>
				</TD>
				<TD VALIGN="TOP" >
					<font size="2">��˰�˸��˺Ϸ�<br>���֤������</font>
				</TD>
				<TD VALIGN="TOP" >
					<font size="2"><bean:write
					name="grxx" property="sfzjhm" />&nbsp;</font>
				</TD>
			</TR>
		</logic:equal>

		<!--�Ǹ���-->
		<logic:notEqual name="sbzb" property="yhbs"
			value="<%=Constants.ZB_YHBS_GR%>">
			<TR>
				<TD VALIGN="MIDDLE" COLSPAN="2">
					<font size="2">��˰������</font>
				</TD>
				<TD VALIGN="TOP" COLSPAN="4">
					<font size="2"><bean:write name="fgrxx"
					property="nsrmc" />&nbsp;</font></font>
				</TD>
			</TR>

			<TR>
				<TD VALIGN="MIDDLE" COLSPAN="2">
					<font size="2">˰���������루��λ��</font>
				</TD>
				<TD VALIGN="TOP" COLSPAN="2">
					<font size="2"><bean:write
					name="fgrxx" property="jsjdm" />&nbsp;</font>
				</TD>
				<TD VALIGN="TOP" >
					<font size="2">��ϵ�绰</font>
				</TD>
				<TD VALIGN="TOP" >
					<font size="2"><bean:write
					name="fgrxx" property="lxdh" />&nbsp;</font>
				</TD>
			</TR>
			<TR>
				<TD VALIGN="TOP" COLSPAN="2">
					<font size="2">��˰������</font>
				</TD>
				<TD VALIGN="TOP"  COLSPAN="4">
					<font size="2"><bean:write
						name="fgrxx" property="nsrlxmc" />&nbsp;</font>
				</TD>
				
			</TR>

			<TR>
				<TD VALIGN="TOP" COLSPAN="2">
					<font size="2">��˰�˸��˺Ϸ�<br>�����֤������</font>
				</TD>
				<TD VALIGN="TOP" COLSPAN="2">
					<font size="2">&nbsp;</font>
				</TD>
				<TD VALIGN="TOP">
					<font size="2">��˰�˸��˺Ϸ�<br>���֤������
					</font>
				</TD>
				<TD VALIGN="TOP" >
					<font size="2">&nbsp;</font>
				</TD>
			</TR>
		</logic:notEqual>

			
			



		<!--���ط�����Ϣ-->
		<TR>


			<TD VALIGN="MIDDLE" ROWSPAN=6 width="10%">

				<p ALIGN="CENTER"><font size="2">����</font></p>
				<p ALIGN="CENTER"><font size="2">����</font></p>
				<p ALIGN="CENTER"><font size="2">Ȩ��</font></p>
				<p ALIGN="CENTER"><font size="2">ת��</font></p>
			</TD>
			
			<TD VALIGN="TOP" ><font size="2">��ͬ����Լ��ǩ��ʱ��</font></TD>
			<TD VALIGN="TOP" COLSPAN="4"><font size="2"> <%=DataConvert.TimeStamp2String((Timestamp) tufwxx
							.getHtqdsj())%>&nbsp;</font></TD>
			
		</TR>

		<TR>
			<TD VALIGN="TOP" ><font size="2">���ء����������ַ</font></TD>
			<TD VALIGN="TOP" COLSPAN="4" style="word-break:break-all"><font
				size="2"><bean:write name="tufwxx" property="tdfwzldz" />&nbsp;</font></TD>
		</TR>

		<TR>
			<TD VALIGN="TOP" ><font size="2">���ء�����Ȩ��ת������</font></TD>
			<TD VALIGN="TOP" COLSPAN="4"><font size="2"><bean:write name="tufwxx"
				property="tdfwqszymc" />&nbsp;</font></TD>
		</TR>

		<TR>
			<TD VALIGN="TOP" ><font size="2">���ء�����Ȩ��ת�����</font></TD>
			<TD VALIGN="TOP" COLSPAN="2" style="word-break:break-all"><font
				size="2">���أ�<%=DataConvert.BigDecimal2String(tufwxx.getTdfwqszymj())%>&nbsp;�O</font>
			</TD>
			<TD VALIGN="TOP" COLSPAN="2"  style="word-break:break-all"><font
				size="2">���ݽ��������<%=DataConvert.BigDecimal2String(tufwxx.getFwjzmj())%>&nbsp;�O</font>
			</TD>
		</TR>

		

		<TR>
			<TD VALIGN="MIDDLE" ROWSPAN="2">
				<DIV ALIGN="CENTER"><font size="2">�ɽ��۸�</font></DIV>
			</TD>
			<TD VALIGN="TOP"  COLSPAN="3">
				<DIV ALIGN="left" style="word-break:break-all"><font size="2"><%=DataConvert.BigDecimal2String((BigDecimal) tufwxx
							.getCjjgrmb())%>&nbsp;Ԫ������ң�</FONT></DIV>
			</TD>
			<TD VALIGN="TOP" ROWSPAN="2" ><font size="2">˰����غ˶��۸�
				<DIV ALIGN="left" style="word-break:break-all"><%=DataConvert.BigDecimal2String((BigDecimal) tufwxx
							.getPgjgrmb())%>&nbsp;Ԫ������ң�</font></DIV>
			</TD>
		</TR>

		<TR>
			<TD VALIGN="TOP" style="word-break:break-all" ><font
				size="2"><%=DataConvert.BigDecimal2String((BigDecimal) tufwxx
							.getCjjgwb())%>&nbsp;Ԫ����ң�</font></TD>
			
			<TD VALIGN="TOP"><font size="2">���ʣ�<%=DataConvert.BigDecimal2String((BigDecimal) tufwxx
							.getHldm(), 5)%>&nbsp;</font></TD>
			<TD VALIGN="TOP" style="word-break:break-all" ><font size="2">�ۺ�:<%=DataConvert.BigDecimal2String((BigDecimal) tufwxx
							.getZhjgrmb())%>Ԫ������ң�&nbsp;</font></TD>
		</TR>
		
		
		<TR>
			<TD VALIGN="MIDDLE" align="center"><font size="2">�����˰����˰����</FONT></TD>
			<TD VALIGN="TOP" COLSPAN="5"><font size="2"> <%-- %><bean:write
				name="jmsbxxForm" property="qsjmlbmc" /><% --%><%=qsjmlbmc %>&nbsp;</font></TD>
		</TR>


		<% if(qtjmlybeizhu != null && !("".equals(qtjmlybeizhu))){ %>
		<TR>
			<TD VALIGN="MIDDLE" align="center"><font size="2">������������&nbsp;</FONT></TD>
			<TD VALIGN="TOP" COLSPAN="5"><font size="2"> <bean:write name="jmsbxxForm"
				property="qtjmlybeizhu" />&nbsp;</font></TD>
		</TR>
		<% }%>

		<TR>
			<TD VALIGN="TOP" COLSPAN="6"><font size="2"> <b>˰����������ʾ����ʵ��������걨</b><br>
			&nbsp;&nbsp;&nbsp;&nbsp;�������������˰��˰��Ȩ���������ṩ�����ء�����Ȩ��ת�Ʊ䶯������������ĸ�������Ӧ��ʵ��Ч���������ټ�˰���ݣ����ݡ��л����񹲺͹�˰�����չ���������ʮ������һ��Ĺ涨����˰������������ڸ�������������Ԫ���µķ���������ٵ���˰�걨�����ɻ����ٽ�Ӧ��˰��ģ����ݡ��л����񹲺͹�˰�����չ���������ʮ������һ��Ĺ涨����˰�����׷���䲻�ɻ����ٽɵ�˰����ɽ𣬲������ɻ����ٽɵ�˰��ٷ�֮��ʮ�����屶���µķ�����ɷ���ģ�����׷���������Ρ�
			
			</font></TD>
		</TR>

		
		<TR>
			<TD VALIGN="MIDDLE" COLSPAN="3"  HEIGHT=80><font size="2">
			��˰��ǩ�£�<br><br><br>
			���˴���ǩ�£�</font></TD>

			<TD VALIGN="MIDDLE" COLSPAN="3" HEIGHT=80><font size="2">�걨��ǩ�£�<br><br><br>
			����Ȩί���ˣ�</font></TD>
		</TR>

	</TABLE>

</html:form>
</BODY>
</HTML>
