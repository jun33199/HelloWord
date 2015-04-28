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
<TITLE>打印减免申报表</TITLE>
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



	<P ALIGN="RIGHT"><font size="2">表号：<bean:write
		name="jmsbxxForm" property="printSbbh" /></font></P>
	<B> <logic:equal name="sbzb" property="yhbs"
		value="<%=Constants.ZB_YHBS_GR%>">
		<P ALIGN="CENTER">北京市地方税务局契税减免申报表-个人</P>
	</logic:equal> <logic:notEqual name="sbzb" property="yhbs"
		value="<%=Constants.ZB_YHBS_GR%>">
		<P ALIGN="CENTER">北京市地方税务局契税减免申报表-非个人</P>
	</logic:notEqual> </B>

	<P ALIGN="CENTER"><font size="2">申报时间： <%=DataConvert.TS2JksDate((Timestamp) sbzb.getSbrq())%>
	 </font></p>

	<TABLE BORDER=1 CELLSPACING=0 Align="center" CELLPADDING=5 WIDTH=707>


		<!--个人-->
		<logic:equal name="sbzb" property="yhbs"
			value="<%=Constants.ZB_YHBS_GR%>">

			<TR>
				<TD VALIGN="MIDDLE" COLSPAN="2">
					<font size="2">纳税人名称</font>
				</TD>
				<TD VALIGN="TOP" COLSPAN="4" >
					<font size="2"><%=grNsrmc%>&nbsp;</font>
				</TD>
			</TR>

			<TR>
				<TD VALIGN="MIDDLE" COLSPAN="2">
					<font size="2">税务计算机代码（单位）</font>
				</TD>
				<TD VALIGN="TOP" COLSPAN="2">
					<font size="2">&nbsp;</font>
				</TD>
				<TD VALIGN="TOP" >
					<font size="2">联系电话</font>
				</TD>
				<TD VALIGN="TOP" >
					<font size="2"><bean:write
					name="grxx" property="lxdh" />&nbsp;</font>
				</TD>
			</TR>

				<TD VALIGN="TOP"  COLSPAN="2">
					<font size="2">纳税人性质</font>
				</TD>
				<TD VALIGN="TOP" COLSPAN="4" >
					<font size="2">个人&nbsp;</font>
				</TD>

			<TR>
				<TD VALIGN="TOP" COLSPAN="2">
					<font size="2">纳税人个人合法<br>性身份证件种类</font>
				</TD>
				<TD VALIGN="TOP" COLSPAN="2">
					<font size="2"><bean:write
					name="grxx" property="sfzjlxmc" />&nbsp;</font>
				</TD>
				<TD VALIGN="TOP" >
					<font size="2">纳税人个人合法<br>身份证件号码</font>
				</TD>
				<TD VALIGN="TOP" >
					<font size="2"><bean:write
					name="grxx" property="sfzjhm" />&nbsp;</font>
				</TD>
			</TR>
		</logic:equal>

		<!--非个人-->
		<logic:notEqual name="sbzb" property="yhbs"
			value="<%=Constants.ZB_YHBS_GR%>">
			<TR>
				<TD VALIGN="MIDDLE" COLSPAN="2">
					<font size="2">纳税人名称</font>
				</TD>
				<TD VALIGN="TOP" COLSPAN="4">
					<font size="2"><bean:write name="fgrxx"
					property="nsrmc" />&nbsp;</font></font>
				</TD>
			</TR>

			<TR>
				<TD VALIGN="MIDDLE" COLSPAN="2">
					<font size="2">税务计算机代码（单位）</font>
				</TD>
				<TD VALIGN="TOP" COLSPAN="2">
					<font size="2"><bean:write
					name="fgrxx" property="jsjdm" />&nbsp;</font>
				</TD>
				<TD VALIGN="TOP" >
					<font size="2">联系电话</font>
				</TD>
				<TD VALIGN="TOP" >
					<font size="2"><bean:write
					name="fgrxx" property="lxdh" />&nbsp;</font>
				</TD>
			</TR>
			<TR>
				<TD VALIGN="TOP" COLSPAN="2">
					<font size="2">纳税人性质</font>
				</TD>
				<TD VALIGN="TOP"  COLSPAN="4">
					<font size="2"><bean:write
						name="fgrxx" property="nsrlxmc" />&nbsp;</font>
				</TD>
				
			</TR>

			<TR>
				<TD VALIGN="TOP" COLSPAN="2">
					<font size="2">纳税人个人合法<br>性身份证件种类</font>
				</TD>
				<TD VALIGN="TOP" COLSPAN="2">
					<font size="2">&nbsp;</font>
				</TD>
				<TD VALIGN="TOP">
					<font size="2">纳税人个人合法<br>身份证件号码
					</font>
				</TD>
				<TD VALIGN="TOP" >
					<font size="2">&nbsp;</font>
				</TD>
			</TR>
		</logic:notEqual>

			
			



		<!--土地房屋信息-->
		<TR>


			<TD VALIGN="MIDDLE" ROWSPAN=6 width="10%">

				<p ALIGN="CENTER"><font size="2">土地</font></p>
				<p ALIGN="CENTER"><font size="2">房屋</font></p>
				<p ALIGN="CENTER"><font size="2">权属</font></p>
				<p ALIGN="CENTER"><font size="2">转移</font></p>
			</TD>
			
			<TD VALIGN="TOP" ><font size="2">合同（契约）签订时间</font></TD>
			<TD VALIGN="TOP" COLSPAN="4"><font size="2"> <%=DataConvert.TimeStamp2String((Timestamp) tufwxx
							.getHtqdsj())%>&nbsp;</font></TD>
			
		</TR>

		<TR>
			<TD VALIGN="TOP" ><font size="2">土地、房屋座落地址</font></TD>
			<TD VALIGN="TOP" COLSPAN="4" style="word-break:break-all"><font
				size="2"><bean:write name="tufwxx" property="tdfwzldz" />&nbsp;</font></TD>
		</TR>

		<TR>
			<TD VALIGN="TOP" ><font size="2">土地、房屋权属转移类型</font></TD>
			<TD VALIGN="TOP" COLSPAN="4"><font size="2"><bean:write name="tufwxx"
				property="tdfwqszymc" />&nbsp;</font></TD>
		</TR>

		<TR>
			<TD VALIGN="TOP" ><font size="2">土地、房屋权属转移面积</font></TD>
			<TD VALIGN="TOP" COLSPAN="2" style="word-break:break-all"><font
				size="2">土地：<%=DataConvert.BigDecimal2String(tufwxx.getTdfwqszymj())%>&nbsp;㎡</font>
			</TD>
			<TD VALIGN="TOP" COLSPAN="2"  style="word-break:break-all"><font
				size="2">房屋建筑面积：<%=DataConvert.BigDecimal2String(tufwxx.getFwjzmj())%>&nbsp;㎡</font>
			</TD>
		</TR>

		

		<TR>
			<TD VALIGN="MIDDLE" ROWSPAN="2">
				<DIV ALIGN="CENTER"><font size="2">成交价格</font></DIV>
			</TD>
			<TD VALIGN="TOP"  COLSPAN="3">
				<DIV ALIGN="left" style="word-break:break-all"><font size="2"><%=DataConvert.BigDecimal2String((BigDecimal) tufwxx
							.getCjjgrmb())%>&nbsp;元（人民币）</FONT></DIV>
			</TD>
			<TD VALIGN="TOP" ROWSPAN="2" ><font size="2">税务机关核定价格：
				<DIV ALIGN="left" style="word-break:break-all"><%=DataConvert.BigDecimal2String((BigDecimal) tufwxx
							.getPgjgrmb())%>&nbsp;元（人民币）</font></DIV>
			</TD>
		</TR>

		<TR>
			<TD VALIGN="TOP" style="word-break:break-all" ><font
				size="2"><%=DataConvert.BigDecimal2String((BigDecimal) tufwxx
							.getCjjgwb())%>&nbsp;元（外币）</font></TD>
			
			<TD VALIGN="TOP"><font size="2">汇率：<%=DataConvert.BigDecimal2String((BigDecimal) tufwxx
							.getHldm(), 5)%>&nbsp;</font></TD>
			<TD VALIGN="TOP" style="word-break:break-all" ><font size="2">折合:<%=DataConvert.BigDecimal2String((BigDecimal) tufwxx
							.getZhjgrmb())%>元（人民币）&nbsp;</font></TD>
		</TR>
		
		
		<TR>
			<TD VALIGN="MIDDLE" align="center"><font size="2">申请减税、免税理由</FONT></TD>
			<TD VALIGN="TOP" COLSPAN="5"><font size="2"> <%-- %><bean:write
				name="jmsbxxForm" property="qsjmlbmc" /><% --%><%=qsjmlbmc %>&nbsp;</font></TD>
		</TR>


		<% if(qtjmlybeizhu != null && !("".equals(qtjmlybeizhu))){ %>
		<TR>
			<TD VALIGN="MIDDLE" align="center"><font size="2">其它减免理由&nbsp;</FONT></TD>
			<TD VALIGN="TOP" COLSPAN="5"><font size="2"> <bean:write name="jmsbxxForm"
				property="qtjmlybeizhu" />&nbsp;</font></TD>
		</TR>
		<% }%>

		<TR>
			<TD VALIGN="TOP" COLSPAN="6"><font size="2"> <b>税务机关真诚提示您如实办理减免申报</b><br>
			&nbsp;&nbsp;&nbsp;&nbsp;您有依法申请减税免税的权利。您所提供的土地、房屋权属转移变动情况及申请减免的附送资料应真实有效。如编造虚假计税依据，根据《中华人民共和国税收征收管理法》第六十四条第一款的规定，由税务机关责令限期改正，并处五万元以下的罚款；如进行虚假的纳税申报，不缴或者少缴应纳税款的，根据《中华人民共和国税收征收管理法》第六十三条第一款的规定，由税务机关追缴其不缴或者少缴的税款、滞纳金，并处不缴或者少缴的税款百分之五十以上五倍以下的罚款；构成犯罪的，依法追究刑事责任。
			
			</font></TD>
		</TR>

		
		<TR>
			<TD VALIGN="MIDDLE" COLSPAN="3"  HEIGHT=80><font size="2">
			纳税人签章：<br><br><br>
			法人代表签章：</font></TD>

			<TD VALIGN="MIDDLE" COLSPAN="3" HEIGHT=80><font size="2">申报人签章：<br><br><br>
			（授权委托人）</font></TD>
		</TR>

	</TABLE>

</html:form>
</BODY>
</HTML>
