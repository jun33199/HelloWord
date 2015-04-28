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

<HTML><HEAD><TITLE>契税核定通知书(缴税)</TITLE>
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
	<FONT FACE="仿宋_GB2312"  SIZE=4><div ALIGN="CENTER">契税核定通知书</div><br>
	</td>
	</tr>
<tr>
	<td colspan=2 align=right>
	<FONT FACE="仿宋_GB2312"  SIZE=2> 
	
	<%if (hdtzsbo.isBz())
	  {
	%>
	编号：
	<%
	  }
	%>
	
	<%
	String printHdtzsh=hdtzsbo.getVoHdtzs().getHdtzsh();
	//Q212京（通）地税契核字（通[2005]）第14号
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
	<FONT FACE="仿宋_GB2312"  SIZE=3><P>申请人<U>&nbsp; <%=hdtzsbo.getVoHdtzs().getSqr() %>  &nbsp;： </U></P></td></tr>
<tr><td colspan=2>&nbsp;</td></tr>
<tr>
	<td colspan=2>
	<P style="word-break:break-all">&nbsp;&nbsp;&nbsp;&nbsp;<FONT SIZE=3>
	<%if (hdtzsbo.isBz())
	  {
	%>
	您承受的商品房（土地）项目名称：
	<%
	    }
	%>
	<%if (!hdtzsbo.isBz())
	  {
	%>
	您受让的商品房项目名称：
	<%
	    }
	%>
	<U>&nbsp; <%=hdtzsbo.getVoHdtzs().getSpfxmmc() %>&nbsp;</U>，</FONT><FONT SIZE=3>
		坐落地址：<U>&nbsp; <%=hdtzsbo.getVoHdtzs().getZldi()%> &nbsp; </U></FONT>
	
		<P style="word-break:break-all">
		<FONT SIZE=3>
		土地房屋权属转移面积：土地使用权面积：<U><%=DataConvert.BigDecimal2String(voTufwxx.getTdfwqszymj())  %>&nbsp;</U>㎡
			,房屋建筑面积：<U><%=DataConvert.BigDecimal2String(voTufwxx.getFwjzmj())  %>&nbsp;</U>㎡
		
		</P>
		
		<P style="word-break:break-all">
		<%
		if (!hdtzsbo.isBz())
		{
		%>
			
			<FONT SIZE=3>
		成交价格（小写）：<U>&nbsp; <%=DataConvert.BigDecimal2String(hdtzsbo.getVoHdtzs().getCjjg())  %>&nbsp;</U>元
		
				，计税依据（小写）：<U>&nbsp;  <%=DataConvert.BigDecimal2String(hdtzsbo.getVoHdtzs().getJsyj())%>&nbsp;</U> 元，计征契税（小写）：<U>&nbsp; <%=DataConvert.BigDecimal2String(hdtzsbo.getVoHdtzs().getJzqs())%> &nbsp;</U> 元。
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
	<P style="word-break:break-all"><FONT SIZE=3>&nbsp;&nbsp;&nbsp;&nbsp;根据：<U>&nbsp; <%=ActionUtil.getBzQkLy(session.getServletContext(),hdtzsbo.getFwlxdm(),hdtzsbo.getVoHdtzs().getSbbh())%> &nbsp;</U>
	<!-- 的有关 -->政策规定，属于不征契税范围。
	</FONT> </P>
	</td></tr>
<tr><td colspan=2>&nbsp;&nbsp;&nbsp;&nbsp;特此证明。</td></tr>
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
	<P style="word-break:break-all"><FONT SIZE=3>&nbsp;&nbsp;&nbsp;&nbsp;根据：<U>《北京市人民政府关于印发北京市已购公有住房上市出售实施
办法的通知》和《北京市财政局北京市地方税务局北京市国土资源和房屋管理局关于已购公有住房上市出售有关契税政策的通知》
</U>规定，已在计税依据中扣除出售已购公有住房收入 <U>&nbsp; <%=DataConvert.BigDecimal2String(gyjm.getJmje())%>&nbsp; </U> 元。</FONT> </P>
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
	<P style="word-break:break-all"><FONT SIZE=3>&nbsp;&nbsp;&nbsp;&nbsp;根据：<U>《关于贯彻国办发〔2008〕131号文件精神促进本市房地产市场健康发展
的实施意见》［京建办〔2009〕43号］		
</U>规定，已在计税依据中扣除出售已购经济适用住房收入 <U>&nbsp; <%=DataConvert.BigDecimal2String(jjsyjm.getJmje())%>&nbsp; </U> 元。</FONT> </P>
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
	<P style="word-break:break-all"><FONT SIZE=3>&nbsp;&nbsp;&nbsp;&nbsp;根据：<U>《北京市契税管理规定》第七条第（二）项<市政府第100号令>或《关于明确契税政策和执行中有关问题的通知》<京财税【2004】16号>
</U>减免税政策规定，核准减免契税（小写）：<U>&nbsp; <%=DataConvert.BigDecimal2String(cqjm.getJmje())%>&nbsp;</U> 元。</FONT> </P>
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
	<P style="word-break:break-all"><FONT SIZE=3>&nbsp;&nbsp;&nbsp;&nbsp;根据：<U>《北京市财政局的、北京市地方税务局转发财政部、国家税务局关于调整房地产市场若干税收政策的通知》（京财税【1999】1201号）、《转发北京市人民政府关于修改北京市契税管理规定的决定的通知》（京财税【2002】1926号）
</U>减免税政策规定，核准减免契税（小写）：<U>&nbsp; <%=DataConvert.BigDecimal2String(ptjm.getJmje())%> &nbsp;</U> 元。</FONT> </P>
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
	<P style="word-break:break-all"><FONT SIZE=3>&nbsp;&nbsp;&nbsp;&nbsp;经税务机关核定，实际应征契税（小写）：<U> &nbsp;&nbsp;<%=DataConvert.BigDecimal2String(hdtzsbo.getVoHdtzs().getSjyz())%>&nbsp;
</U>元，（人民币大写）：<U>&nbsp;&nbsp;<%=Currency.convert(hdtzsbo.getVoHdtzs().getSjyz())%>&nbsp;&nbsp;</U> 。</FONT> </P>
	</td></tr>
<tr><td colspan=2>&nbsp;&nbsp;&nbsp;&nbsp;特此证明。</td></tr>
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
	<P style="word-break:break-all">&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日（章）</P>
	<BR><br><br><br>
	<%
		}
		else
		{
    %>
	<P style="word-break:break-all"><!-- 申请人接收签名或盖章--><br>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日</P>
	<br><br><br><br>
	<%
		}
	%>
	</td></tr>

<tr>
	<td valign="top" width="10%">
	<P>说明:</P>
	</td>
	<td>
	<P style="word-break:break-all">本证明一式三份，一份由税务机关留存备查，两份交纳税人，其中一份由国土、房产管理部门办理土地、房屋权属转移变更登记手续时留存。</p>
	</td></tr>
<tr><td colspan=2>&nbsp;</td></tr>
<tr>
	<td colspan=2>
	<P>本证明如有问题，请及时与我单位联系。</P>
	</td></tr>
<tr><td colspan=2>&nbsp;</td></tr>
<tr>
	<td colspan=2>
	<P> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;经办人：&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;  联系电话：</P>
	</td></tr>
</html:form>
</TBODY></TABLE>

</BODY>
</HTML>
