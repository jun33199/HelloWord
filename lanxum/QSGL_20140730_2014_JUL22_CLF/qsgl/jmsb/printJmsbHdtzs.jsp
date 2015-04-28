<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.ActionUtil"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.model.bo.HdtzsBo"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Hdjmmx"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Qsjmlb"%>
<%@ page import="com.ttsoft.framework.util.Currency"%>

<HTML>
<HEAD>
<TITLE>契税核定通知书</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<META content="MSHTML 5.00.2919.6307" name=GENERATOR>
</HEAD>
<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0"
	marginwidth="0">
<BR>
<TABLE align=center border=0 height="25%" width=600>
	<TBODY>
		<html:form action="/jmsb/printJmsbHdtzs.do">
			<bean:define id="hdtzsbo" name="jmsbHdtzsForm" property="hdtzsbo"
				type="com.creationstar.bjtax.qsgl.model.bo.HdtzsBo" />
			<bean:define id="voTufwxx" name="hdtzsbo" property="voTufwxx"
				type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx" />
			<tr>
				<td colspan=2 align=center><FONT FACE="仿宋_GB2312" SIZE=4>
				<div ALIGN="CENTER">契税核定通知书</div>
				<br></td>
			</tr>
			<tr>
				<td colspan=2 align=right><FONT FACE="仿宋_GB2312" SIZE=2>


				<%
					String printHdtzsh_str = hdtzsbo.getVoHdtzs().getHdtzsh();
					String printHdtzsh = printHdtzsh_str;
					//通地税契核字（通[2005]）第14号
					//int index = printHdtzsh_str.indexOf("地税契核字");

					//printHdtzsh = "<U>" + printHdtzsh_str.substring(0, index) + "</U>"
							//+ printHdtzsh_str.substring(index);

					out.println(printHdtzsh);
				%> <!--bean:write name="hdtzsbo" property="voHdtzs.hdtzsh" /--></FONT></td>
			</tr>
			<tr>
				<td colspan=2><FONT FACE="仿宋_GB2312" SIZE=3>
				<P>申请人<U>&nbsp; <%=hdtzsbo.getVoHdtzs().getSqr()%> &nbsp;： </U></P>
				</FONT></td>
			</tr>
			<tr>
				<td colspan=2>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=2>
				<P style="word-break:break-all">&nbsp;&nbsp;&nbsp;&nbsp;<FONT
					SIZE=3> 您受让的商品房项目名称： <U>&nbsp; <%=hdtzsbo.getVoHdtzs().getSpfxmmc()%>&nbsp;</U>，
				坐落地址：<U>&nbsp; <%=hdtzsbo.getVoHdtzs().getZldi()%> &nbsp; </U>
				<P style="word-break:break-all">土地房屋权属转移面积：土地使用权面积：<U><%=DataConvert.BigDecimal2String(voTufwxx.getTdfwqszymj())%>&nbsp;</U>㎡
				,房屋建筑面积：<U><%=DataConvert.BigDecimal2String(voTufwxx.getFwjzmj())%>&nbsp;</U>㎡

				</P>

				<P style="word-break:break-all">成交价格（小写）：<U>&nbsp; <%=DataConvert.BigDecimal2String(hdtzsbo.getVoHdtzs()
							.getCjjg())%>&nbsp;</U>元 ，计税依据（小写）：<U>&nbsp; <%=DataConvert.BigDecimal2String(hdtzsbo.getVoHdtzs()
							.getJsyj())%>&nbsp;</U> 元，计征契税（小写）：<U>&nbsp; <%=DataConvert.BigDecimal2String(hdtzsbo.getVoHdtzs()
							.getJzqs())%> &nbsp;</U> 元。</P>
				</FONT>
				</td>
			</tr>
			<tr>
				<td colspan=2>&nbsp;</td>
			</tr>

			<%
				HashMap jmnrMap = (HashMap) hdtzsbo.getJmnrMap();
				Iterator iterator_jmnr = jmnrMap.keySet().iterator();

				StringBuffer stringBuf_jmzcyj = new StringBuffer();
				stringBuf_jmzcyj.delete(0,stringBuf_jmzcyj.length());
				
				while (iterator_jmnr.hasNext()) {
					
					if (stringBuf_jmzcyj.length() > 0){
						stringBuf_jmzcyj.append("；");
					}
					
					Hdjmmx hdjmmx = (Hdjmmx) jmnrMap.get(iterator_jmnr.next());

					HashMap jmsbbMap = (HashMap) ActionUtil.getCodeMaps(session
					.getServletContext(), Constants.JMZCMAP);
					Qsjmlb qsjmlbVo = (Qsjmlb) jmsbbMap.get(hdjmmx.getZcbh());

					//政策依据
					if(Constants.CXXJM_JMXMDM_QT.equals(hdjmmx.getZcbh())){
						
						//其它类减免的理由依据根据备注填写
						stringBuf_jmzcyj.append(hdjmmx.getBz());
					
					}else{
					
					

						stringBuf_jmzcyj.append(qsjmlbVo.getQsjmlbzcyj());
					}
					

				}
			%>

			<tr>
				<td colspan=2>
				<P style="word-break:break-all"><FONT SIZE=3>&nbsp;&nbsp;&nbsp;&nbsp;根据：<U>&nbsp;
				<%=stringBuf_jmzcyj.toString()%> &nbsp;</U> 减免税政策规定，核准减免契税（小写）：<U>&nbsp;
				<%=DataConvert.BigDecimal2String(hdtzsbo.getVoHdtzs()
							.getJzqs())%> &nbsp;</U> 元。</FONT></P>
				</td>
			</tr>			
			<tr>
				<td colspan=2>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=2>
				<P style="word-break:break-all"><FONT SIZE=3>&nbsp;&nbsp;&nbsp;&nbsp;经税务机关核定，实际应征契税（小写）：<U>&nbsp;
				0.00 &nbsp;</U> 元，（人民币大写）：<U>&nbsp; 零圆整</U>。</FONT></P>
				</td>
			</tr>			
			<tr>
				<td colspan=2>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=2>&nbsp;&nbsp;&nbsp;&nbsp;特此证明。</td>
			</tr>
			<tr>
				<td colspan=2><br>
				<br>
				<br>
				<br>
				<br>
				<br>

				<P style="word-break:break-all"><!-- 申请人接收签名或盖章--><br>
				&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日</P>
				<br>
				<br>
				<br>
				<br>

				</td>
			</tr>

			<tr>
				<td valign="top" width="10%">
				<P>说明:</P>
				</td>
				<td>
				<P style="word-break:break-all">本证明一式三份，一份由税务机关留存备查，两份交纳税人，其中一份由国土、房产管理部门办理土地、房屋权属转移变更登记手续时留存。</p>
				</td>
			</tr>
			<tr>
				<td colspan=2>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=2>
				<P>本证明如有问题，请及时与我单位联系。</P>
				</td>
			</tr>
			<tr>
				<td colspan=2>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=2>
				<P>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;经办人：&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; 联系电话：</P>
				</td>
			</tr>
		</html:form>
	</TBODY>
</TABLE>

</BODY>
</HTML>
