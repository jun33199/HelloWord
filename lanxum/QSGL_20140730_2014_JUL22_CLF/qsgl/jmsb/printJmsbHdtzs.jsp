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
<TITLE>��˰�˶�֪ͨ��</TITLE>
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
				<td colspan=2 align=center><FONT FACE="����_GB2312" SIZE=4>
				<div ALIGN="CENTER">��˰�˶�֪ͨ��</div>
				<br></td>
			</tr>
			<tr>
				<td colspan=2 align=right><FONT FACE="����_GB2312" SIZE=2>


				<%
					String printHdtzsh_str = hdtzsbo.getVoHdtzs().getHdtzsh();
					String printHdtzsh = printHdtzsh_str;
					//ͨ��˰�����֣�ͨ[2005]����14��
					//int index = printHdtzsh_str.indexOf("��˰������");

					//printHdtzsh = "<U>" + printHdtzsh_str.substring(0, index) + "</U>"
							//+ printHdtzsh_str.substring(index);

					out.println(printHdtzsh);
				%> <!--bean:write name="hdtzsbo" property="voHdtzs.hdtzsh" /--></FONT></td>
			</tr>
			<tr>
				<td colspan=2><FONT FACE="����_GB2312" SIZE=3>
				<P>������<U>&nbsp; <%=hdtzsbo.getVoHdtzs().getSqr()%> &nbsp;�� </U></P>
				</FONT></td>
			</tr>
			<tr>
				<td colspan=2>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=2>
				<P style="word-break:break-all">&nbsp;&nbsp;&nbsp;&nbsp;<FONT
					SIZE=3> �����õ���Ʒ����Ŀ���ƣ� <U>&nbsp; <%=hdtzsbo.getVoHdtzs().getSpfxmmc()%>&nbsp;</U>��
				�����ַ��<U>&nbsp; <%=hdtzsbo.getVoHdtzs().getZldi()%> &nbsp; </U>
				<P style="word-break:break-all">���ط���Ȩ��ת�����������ʹ��Ȩ�����<U><%=DataConvert.BigDecimal2String(voTufwxx.getTdfwqszymj())%>&nbsp;</U>�O
				,���ݽ��������<U><%=DataConvert.BigDecimal2String(voTufwxx.getFwjzmj())%>&nbsp;</U>�O

				</P>

				<P style="word-break:break-all">�ɽ��۸�Сд����<U>&nbsp; <%=DataConvert.BigDecimal2String(hdtzsbo.getVoHdtzs()
							.getCjjg())%>&nbsp;</U>Ԫ ����˰���ݣ�Сд����<U>&nbsp; <%=DataConvert.BigDecimal2String(hdtzsbo.getVoHdtzs()
							.getJsyj())%>&nbsp;</U> Ԫ��������˰��Сд����<U>&nbsp; <%=DataConvert.BigDecimal2String(hdtzsbo.getVoHdtzs()
							.getJzqs())%> &nbsp;</U> Ԫ��</P>
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
						stringBuf_jmzcyj.append("��");
					}
					
					Hdjmmx hdjmmx = (Hdjmmx) jmnrMap.get(iterator_jmnr.next());

					HashMap jmsbbMap = (HashMap) ActionUtil.getCodeMaps(session
					.getServletContext(), Constants.JMZCMAP);
					Qsjmlb qsjmlbVo = (Qsjmlb) jmsbbMap.get(hdjmmx.getZcbh());

					//��������
					if(Constants.CXXJM_JMXMDM_QT.equals(hdjmmx.getZcbh())){
						
						//�����������������ݸ��ݱ�ע��д
						stringBuf_jmzcyj.append(hdjmmx.getBz());
					
					}else{
					
					

						stringBuf_jmzcyj.append(qsjmlbVo.getQsjmlbzcyj());
					}
					

				}
			%>

			<tr>
				<td colspan=2>
				<P style="word-break:break-all"><FONT SIZE=3>&nbsp;&nbsp;&nbsp;&nbsp;���ݣ�<U>&nbsp;
				<%=stringBuf_jmzcyj.toString()%> &nbsp;</U> ����˰���߹涨����׼������˰��Сд����<U>&nbsp;
				<%=DataConvert.BigDecimal2String(hdtzsbo.getVoHdtzs()
							.getJzqs())%> &nbsp;</U> Ԫ��</FONT></P>
				</td>
			</tr>			
			<tr>
				<td colspan=2>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=2>
				<P style="word-break:break-all"><FONT SIZE=3>&nbsp;&nbsp;&nbsp;&nbsp;��˰����غ˶���ʵ��Ӧ����˰��Сд����<U>&nbsp;
				0.00 &nbsp;</U> Ԫ��������Ҵ�д����<U>&nbsp; ��Բ��</U>��</FONT></P>
				</td>
			</tr>			
			<tr>
				<td colspan=2>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=2>&nbsp;&nbsp;&nbsp;&nbsp;�ش�֤����</td>
			</tr>
			<tr>
				<td colspan=2><br>
				<br>
				<br>
				<br>
				<br>
				<br>

				<P style="word-break:break-all"><!-- �����˽���ǩ�������--><br>
				&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��</P>
				<br>
				<br>
				<br>
				<br>

				</td>
			</tr>

			<tr>
				<td valign="top" width="10%">
				<P>˵��:</P>
				</td>
				<td>
				<P style="word-break:break-all">��֤��һʽ���ݣ�һ����˰��������汸�飬���ݽ���˰�ˣ�����һ���ɹ��������������Ű������ء�����Ȩ��ת�Ʊ���Ǽ�����ʱ���档</p>
				</td>
			</tr>
			<tr>
				<td colspan=2>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=2>
				<P>��֤���������⣬�뼰ʱ���ҵ�λ��ϵ��</P>
				</td>
			</tr>
			<tr>
				<td colspan=2>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=2>
				<P>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����ˣ�&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; ��ϵ�绰��</P>
				</td>
			</tr>
		</html:form>
	</TBODY>
</TABLE>

</BODY>
</HTML>
