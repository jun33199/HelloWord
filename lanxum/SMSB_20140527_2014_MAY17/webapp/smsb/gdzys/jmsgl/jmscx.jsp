<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ttsoft.framework.util.JspUtil"%>
<%@ page import="com.ttsoft.common.util.CodeConstants"%>
<%@ page import="com.ttsoft.bjtax.smsb.lwpk.model.PKJBSJModel"%>
<%@ page import="com.ttsoft.bjtax.smsb.lwpk.model.PLKKDHCXModel"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ttsoft.bjtax.smsb.gdzys.jmsgl.web.GdzysJmscxForm"%>

<html:html>
<head>
<title>����˰��ѯ</title>
<link href="../../css/text.css" rel="stylesheet" type="text/css">
<link href="../../css/top-box.css" rel="stylesheet" type="text/css">
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../../js/treatImage.js"></script>
<script language="JavaScript" src="../../js/smsb_common.js"></script>
<script language="JavaScript" src="../../js/DTable.js"></script>
<script language="JavaScript" src="../../js/InputSelect.js"></script>
<script language="JavaScript" src="../../js/function.js"></script>
<script language="javascript" src="../../js/gmit_selectcontrol.js"></script>
</head>
<%
 	int datasize=((GdzysJmscxForm)(request.getAttribute("gdzysJmscxForm"))).getDataList().size();
%>
<SCRIPT LANGUAGE="JavaScript">
	function doit(ope) {
		doSubmitForm(ope);

	}
	function moren(){
		var size=<%= datasize%>;
		if(size!=0){
			document.getElementById("mydiv").style.display="";
		}
	    if(size==1){
			document.getElementById("div0").style.display="";
		}
		
	}
	function showDiv(str){
		var size=<%= datasize%>;
		for(i=0;i<size;i++){
			  if(i==str)
			   {
			       document.getElementById("div"+str).style.display="";//��ʾ
			   }
			   else
			   {
			       document.getElementById("div"+i).style.display='none';//����
			      
			   }
		}
		
	}
	
	//ȥ���ַ��������пո�
	function trim(str){
		var aa=str.replace(/\s/g,"");
		return aa;
	}
	
	//��֤�Ƿ��������붼�ǿ�
	function validate(){
		var nsrmc=document.getElementById("nsrmc").value;
		var jsjdm=document.getElementById("jsjdm").value;
		var pzzdwh=document.getElementById("pzzdwh").value;
		var sbxlh=document.getElementById("sbxlh").value;
		if(trim(nsrmc)==""&&trim(jsjdm)==""&&trim(pzzdwh)==""&&trim(sbxlh)==""){
			alert("��˰�����ƣ���������룬��׼ռ���ĺţ��걨���к����߲��ܶ�Ϊ��");
			return false;
		}else{
			document.forms[0].submit();
		}
		
	}
	
	//���߼���˰֤��
	function cjjms(str,sbb,zfbz,jmszmbh){
		if(zfbz=="1"){
			alert("����˰֤�������ϣ����ܳ���");
			return false;
		}
		document.forms[str].action = "/smsb/webapp/smsb/gdzys/gdzysJmscxAction.do?actionType=PrintView&sbxlh="+sbb+"&jmszmbh="+jmszmbh;
		//window.open("/smsb/webapp/smsb/gdzys/gdzysJmscxAction.do?actionType=PrintView&sbxlh="+sbb);
		document.forms[str].target="_blank";
		document.forms[str].submit();
	}
</SCRIPT>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onload="moren();">
	<%@ include file="../../include/header.jsp"%>
	<html:form action="/webapp/smsb/gdzys/gdzysJmscxAction.do"
		method="POST">
		<html:hidden property="actionType" value="Query" />
		<div id="ycy"></div>
		<table width="80%" align="center" cellspacing="0">
			<tr>
				<td class="1-td1" colspan="7">����˰��ѯ</td>
			</tr>
			<tr>
				<td class="1-td2" colspan="7"><br>
					<table cellspacing="0" class="table-99" style="empty-cells:show; border-collapse:collapse;" >
						<tr>
							<td  class="2-td2-t-left">��˰������</td>
							<td  class="2-td2-t-left">
								<div align="left">
									<html:text property="nsrmc" size="15" maxlength="50" />
									</FONT>
								</div>
							</td>
							<td  class="2-td2-t-left">���������</td>
							<td  class="2-td2-t-left">
								<div align="left">
									<html:text property="jsjdm" size="15" maxlength="50"/>
								</div>
							</td>

							<td class="2-td2-t-left">��׼ռ���ĺ�</td>
							<td class="2-td2-t-left">
								<div align="left">
									<html:text property="pzzdwh" size="20" maxlength="50"/>
								</div>
							</td>
						</tr>
						<tr>
							<td  class="2-td2-t-left">�걨���к�</td>
							<td  class="2-td2-t-left">
								<div align="left">
									<html:text property="sbxlh" size="15" maxlength="50" />
									</FONT>
								</div>
							</td>
							<td  class="2-td2-t-left">״̬</td>
							<td  class="2-td2-t-left">
								<div align="left">
								<%
									String lsta=((GdzysJmscxForm)request.getAttribute("gdzysJmscxForm")).getStatus();
								    lsta=lsta==null?"0":lsta;
									
								%>
									<html:select  property="status" value="<%=lsta %>">
										<html:option  value="ȫ��">ȫ��</html:option>
										<html:option  value="0">����</html:option>
										<html:option  value="1">�ѳ���</html:option>
									</html:select>
								</div>
							</td>
							<td class="2-td2-t-left" colspan="2"></td>							
							

							
						</tr>
						
							<table width="100%" border="0" cellpadding="0" cellspacing="4">
							 <tr valign="bottom">
							 <td width="50%" align="right" >
								<div>								 
									<input name="I2" type="image" accesskey="q" value="��ѯ"
										onclick="validate();return false;"
										onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" alt="��ѯ"
										src="<%=static_contextpath%>images/cx-q1.jpg" width="79" border="0"
										height="22" id="chaxun" >
								 </div>
							 </td>
							 <td width="50%" align="left" >
								<div>
									<input type="image" accesskey="c" tabIndex="-1"
										onClick="tuichu();return false;" 
										onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" alt="�˳�" id="tc1"
										src="<%=static_contextpath%>images/tc-c1.jpg" width="79"
										border="0" height="22">
								 </div>
							 </td> 
						
							 </tr>
							</table>
						
					</table></td>
			</tr>
		</table>
		</html:form>

		<br>
		<logic:equal name="gdzysJmscxForm" property="cxJgTsFlag" value="1">
			  	 <DIV style="FONT-SIZE: 13px; HEIGHT: 15px" 
                 align=center><FONT SIZE="2" COLOR="#FF0000">û�������ѯ�����Ľ����¼��</STRONG>
                 </DIV>	
				</logic:equal>			
		<div id="mydiv" style="display:none">
		<table width="80%" align="center" cellspacing="0">
			<tr>
				<td class="1-td1" colspan="7">����˰��ѯ��Ϣ���</td>
			</tr>
			<tr>
				<td class="1-td2" colspan="7"><br>
					<table cellspacing="0" class="table-99">
						<tr>
							<td class=2-td1-left width="4%">ѡ��</td>
							<TD class=2-td1-left width="14%">�걨���к�</TD>
							<TD class=2-td1-left width="14%">��˰������</TD>
							<TD class=2-td1-left width="14%">���������</TD>
							<TD class=2-td1-left width="14%">��˰���</TD>
							<TD class=2-td1-left width="14%">�������</TD>
							<TD class=2-td1-left width="12%">����˰��</TD>
							<TD class=2-td1-center width="14%">�걨ʱ��</TD>
						</tr>
						<logic:iterate id="jmslist" name="gdzysJmscxForm"
							property="dataList" indexId="index">
							<tr>
								<td class=2-td2-t-left align="center" nowrap>&nbsp;<input type="radio" name="radioButton" onclick="showDiv(<%=index %>)" /></td>
								<td class="2-td2-t-left" align="center" nowrap>&nbsp;<ttsoft:write
										name="jmslist" property="sbbxlh" /></td>
								<td class="2-td2-t-left" align="center" nowrap>&nbsp;<ttsoft:write
										name="jmslist" property="nsrmc" /></td>
								<td class="2-td2-t-left" align="center" nowrap>&nbsp;<ttsoft:write
										name="jmslist" property="jsjdm" /></td>
								<td class="2-td2-t-left" align="center" nowrap>&nbsp;<ttsoft:write
										name="jmslist" property="jsmj" /></td>
								<td class="2-td2-t-left" align="center" nowrap>&nbsp;<ttsoft:write
										name="jmslist" property="jmmj" /></td>
								<td class="2-td2-t-left" align="center" nowrap>&nbsp;<ttsoft:write
										name="jmslist" property="jmse" /></td>
								<td class="2-td2-t-center" align="center" nowrap>&nbsp;<ttsoft:write
										name="jmslist" property="cjrq" /></td>
							</tr>
						</logic:iterate>
					</table></td>
			</tr>
		</table>
		</div>
		<br>


			<logic:iterate id="jmslist" name="gdzysJmscxForm" property="dataList" indexId="ind">
				<bean:define id="aaa" name="jmslist" />
				<bean:define id="bbb" name="jmslist" />
				<bean:define id="ccc" name="jmslist" />
				<bean:define id="ddd" name="jmslist"/>
				<bean:define id="eee" name="jmslist"/>
				<div id="div<%= ind%>" style="display:none">
		<form action="/webapp/smsb/gdzys/gdzysJmscxAction.do" method="post" id="<%=Integer.parseInt(ind.toString())+1%>">
				<table width="80%" align="center" cellspacing="0">
					<tr>
					<tr>
						<td class="1-td1" colspan="7">˰Դ��ϸ��Ϣ</td>
					</tr>
					</tr>
					<tr>
						<td class="1-td2" colspan="7"><br>
							<table cellspacing="0" class="table-99" style="empty-cells:show; border-collapse:collapse;">
								<tr>������Ϣ��
								</tr>
								<TR>
									<td class=2-td1-left width="20%">�걨���к�</td>
									<TD class=2-td2-t-left width="20%"><ttsoft:write
											name="jmslist" property="sbbxlh" /></TD>
									<TD class=2-td1-left width="20%">��˰������</TD>
									<TD class=2-td2-t-center width="20%"><ttsoft:write
											name="jmslist" property="nsrlx" /></TD>
								</TR>
								<TR>
									<td class=2-td1-left width="20%">�Ƿ��о�����</td>
									<TD class=2-td2-t-left width="20%"><ttsoft:write
											name="jmslist" property="sfsjsp" /></TD>
									<TD class=2-td1-left width="20%">˰Դ����</TD>
									<TD class=2-td2-t-center width="20%"><ttsoft:write
											name="jmslist" property="sylxmc" /></TD>
								</TR>
								<TR>
									<td class=2-td1-left width="20%">��˰������</td>
									<TD class=2-td2-t-left width="20%"><ttsoft:write
											name="jmslist" property="nsrmc" /></TD>
									<TD class=2-td1-left width="20%">���������</TD>
									<TD class=2-td2-t-center width="20%"><ttsoft:write
											name="jmslist" property="jsjdm" /></TD>
								</TR>
								<TR>
									<td class=2-td1-left width="20%">��˰��ʶ���</td>
									<TD class=2-td2-t-left width="20%"><ttsoft:write
											name="jmslist" property="swdjzh" /></TD>
									<TD class=2-td1-left width="20%">��˰��������ҵ</TD>
									<TD class=2-td2-t-center width="20%"><ttsoft:write
											name="jmslist" property="nsrsshy" /></TD>
								</TR>

								<TR>
									<td class=2-td1-left width="20%">��˰����ϸ��ַ</td>
									<TD class=2-td2-t-left width="20%"><ttsoft:write
											name="jmslist" property="nsrxxdz" /></TD>
									<TD class=2-td1-left width="20%">��ҵ�Ǽ�ע������</TD>
									<TD class=2-td2-t-center width="20%"><ttsoft:write
											name="jmslist" property="qydjzclx" /></TD>
								</TR>
								<TR>
									<td class=2-td1-left width="20%">��������</td>
									<TD class=2-td2-t-left width="20%"><ttsoft:write
											name="jmslist" property="khyhmc" /></TD>
									<TD class=2-td1-left width="20%">�����˺�</TD>
									<TD class=2-td2-t-center width="20%"><ttsoft:write
											name="jmslist" property="yhzh" /></TD>
								</TR>
								<TR>
									<td class=2-td1-left width="20%">��ϵ������</td>
									<TD class=2-td2-t-left width="20%"><ttsoft:write
											name="jmslist" property="lxrxm" /></TD>
									<TD class=2-td1-left width="20%">��ϵ�绰</TD>
									<TD class=2-td2-t-center width="20%"><ttsoft:write
											name="jmslist" property="lxdh" /></TD>
								</TR>
							</table></td>
					</tr>
				</table>
				<table width="80%" align="center" cellspacing="0">
					<tr></tr>

					<tr>
						<td class="1-td2" colspan="7"><br>
							<table cellspacing="0" class="table-99" style="empty-cells:show; border-collapse:collapse;">
								<tr>������Ϣ��
								</tr>

	                            <tr>
											<TD class=2-td1-left width="20%">������Ŀ����</TD>
									       <TD class=2-td2-t-center width="20%" colspan="3"><ttsoft:write
											name="jmslist" property="jsxmmc" /></TD>
									
								</TR>
								<TR>
									<td class=2-td1-left width="20%">���������ַ</td>
									<TD class=2-td2-t-left width="80%" colspan="3""><ttsoft:write
											name="jmslist" property="tdzldz" /></TD>
								</tr>
							
								<TR>
								<TD class=2-td1-left width="20%">��׼ռ���ĺ�</TD>
									<TD class=2-td2-t-left width="20%"><ttsoft:write
											name="jmslist" property="pzzdwh" /></TD>
									<td class=2-td1-left width="20%">��׼ռ�����������ũת�����:ƽ���ף�</td>
									<TD class=2-td2-t-center width="20%"><ttsoft:write
											name="jmslist" property="pzjdmj" /></TD>
									
								</TR>
								<TR>
									<td class=2-td1-left width="20%">ʵ��ռ����������˰���һ��:ƽ���ף�</td>
									<TD class=2-td2-t-left width="20%"><ttsoft:write
											name="jmslist" property="sjzdmj" /></TD>
									<TD class=2-td1-left width="20%">��׼ʱ��/ʵ��ռ��ʱ��</TD>
									<TD class=2-td2-t-center width="20%"><ttsoft:write
											name="jmslist" property="zdsj" /></TD>
								</TR>
							</table></td>
					</tr>
				</table>
				<table width="80%" align="center" cellspacing="0">
					<tr>

					</tr>
					<tr>
						<td class="1-td2" colspan="7"><br>
							<table cellspacing="0" class="table-99" style="empty-cells:show; border-collapse:collapse;">
								<tr>
									<td class=2-td1-left width="9%">���</td>
									<td class=2-td1-left width="13%">ռ����;</td>
									<td class=2-td1-left width="13%">����˰�Ԫ/ƽ���ף�</td>
									<td class=2-td1-left width="13%">��˰�����ƽ���ף�</td>
									<td class=2-td1-left width="13%">����˰�Ԫ��</td>
									<td class=2-td1-left width="13%">���������ƽ���ף�</td>
									<td class=2-td1-left width="13%">����˰�Ԫ��</td>
									<td class=2-td1-center width="13%">Ӧ��˰�ƽ���ף�</td>

								</tr>

								<%
									List data = (ArrayList) ((HashMap) aaa)
														.get("landInfoList");
								double jmmjhj=0.0;
								double jsmjhj=0.0;double jmsehj=0.0;
								double jzsehj=0.0;double ynsehj=0.0;

												for (int i = 0; i < data.size(); i++) {
													String zdytmc = (String) ((HashMap) data.get(i))
															.get("zdytmc");
													String syse = (String) ((HashMap) data.get(i))
															.get("syse");
													String jsmj = (String) ((HashMap) data.get(i))
															.get("jsmj");
													String jzse = (String) ((HashMap) data.get(i))
															.get("jzse");
													String jmmj = (String) ((HashMap) data.get(i))
															.get("jmmj");
													String jmse = (String) ((HashMap) data.get(i))
															.get("jmse");
													String ynse = (String) ((HashMap) data.get(i))
															.get("ynse");
													
								%>
								<tr>
									<td class=2-td2-t-left width="9%"><%=i + 1%></td>
									<td class=2-td2-t-left width="13%"><%=zdytmc%></td>
									<td class=2-td2-t-left width="13%"><%=syse%></td>
									<td class=2-td2-t-left width="13%"><%=jsmj%></td>
									<td class=2-td2-t-left width="13%"><%=jzse%></td>
									<td class=2-td2-t-left width="13%"><%=jmmj%></td>
									<td class=2-td2-t-left width="13%"><%=jmse%></td>
									<td class=2-td2-t-center width="13%"><%=ynse%></td>
								</tr>
								<%
									}
								%>
																<tr>
									<!-- <td class=2-td2-left width="9%">�ϼ�</td>
									<td class=2-td2-center width="13%"></td>
									<td class=2-td2-right width="13%"></td> -->
									<td class="2-td2-left" colspan="3">�ϼ�</td>
									<td class=2-td2-left width="13%"><ttsoft:write
											name="jmslist" property="jsmj" /></td>
									<td class=2-td2-left width="13%"><ttsoft:write
											name="jmslist" property="jzse" /></td>
									<td class=2-td2-left width="13%"><ttsoft:write
											name="jmslist" property="jmmj" /></td>
									<td class=2-td2-left width="13%"><ttsoft:write
											name="jmslist" property="jmse" /></td>
									<td class=2-td2-center width="13%"><ttsoft:write
											name="jmslist" property="ynse" /></td>

								</tr>

							</table></td>
					</tr>
				</table>
				<table width="80%" align="center" cellspacing="0">
					<tr>

					</tr>
					<tr>
						<td class="1-td2" colspan="7"><br>
							<table cellspacing="0" class="table-99" style="empty-cells:show; border-collapse:collapse;">
								<tr>������Ϣ��
								</tr>
								<tr>
									<td class=2-td1-left width="10%">���</td>
									<td class=2-td1-left width="30%">����˰���</td>
									<td class=2-td1-left width="30%">������������ƽ���ף�</td>
									<td class=2-td1-left width="30%">�������˰�Ԫ��</td>
								</tr>
								<%
									List style = (ArrayList) ((HashMap) bbb)
														.get("styleList");

												for (int j = 0; j < style.size(); j++) {
													String jmslbmc = (String) ((HashMap) style.get(j))
															.get("jmslbmc");
													String jmmj = (String) ((HashMap) style.get(j))
															.get("jmmj");
													String jmse = (String) ((HashMap) style.get(j))
															.get("jmse");
								%>
								<tr>
									<td class=2-td2-t-left width="10%"><%=j + 1%></td>
									<td class=2-td2-t-left width="30%"><%=jmslbmc%></td>
									<td class=2-td2-t-left width="30%"><%=jmmj%></td>
									<td class=2-td2-t-center width="30%"><%=jmse%></td>
								</tr>
								<%
									}
								%>
							</table></td>
					</tr>
				</table>
				
				<table width="80%" align="center" cellspacing="0">
					<tr>

					</tr>
					<tr>
						<td class="1-td2" colspan="7"><br>
							<table cellspacing="0" class="table-99">
								<tr>�걨��������:
								</tr>
								<tr>
									<textarea style="width=100%"  rows="3" disabled="disabled"><ttsoft:write name="jmslist" property="sbjmly" /></textarea>
								</tr>
							</table></td>
					</tr>
				</table>
				<table width="80%" align="center" cellspacing="0">
					<tr>

					</tr>
					<tr>
						<td class="1-td2" colspan="7"><br>
							<table cellspacing="0" class="table-99">
								<tr>��ע:
								</tr>
								<tr>
									<textarea style="width=100%"  rows="3" disabled="disabled"><ttsoft:write name="jmslist" property="bzms" /></textarea>
								</tr>
							</table></td>
					</tr>
				</table>
				
				<table width="80%" align="center" cellspacing="0">
			 		<tr>
				
					</tr>
					<tr>
			    		<td class="1-td2" colspan="7"><br>
						<table cellspacing="0" class="table-99" style="empty-cells:show; border-collapse:collapse;">
						<tr>
							<td class=2-td1-left width="10%">������</td>
							<td class=2-td2-t-left width="10%"><ttsoft:write name="jmslist" property="cjr"/></td>
							<td class=2-td1-left width="10%">����ʱ��</td>
							<td class=2-td2-t-left width="10%"><ttsoft:write name="jmslist" property="cjrq"/></td>
							<td class=2-td1-left width="10%">����ȷ����</td>
							<td class=2-td2-t-left width="10%"><ttsoft:write name="jmslist" property="qrr"/></td>
							<td class=2-td1-left width="10%">����ȷ��ʱ��</td>
							<td class=2-td2-t-left width="10%"><ttsoft:write name="jmslist" property="qrsj"/></td>
							<td class=2-td1-left width="10%">����ȷ��״̬</td>
							<td class=2-td2-t-center width="10%"><ttsoft:write name="jmslist" property="qrzt"/></td>
						</tr>
						</table>
			     </td>
					</tr>
                 </table>
                 <%
                 		String sjflag=(String)((HashMap)ddd).get("sfsjsp");
                 		if(sjflag.equals("��")){
                 		
                 %>
                 <table width="80%" align="center" cellspacing="0">
			 		<tr>
				
					</tr>
					<tr>
			    		<td class="1-td2" colspan="7"><br>
						<table cellspacing="0" class="table-99" style="empty-cells:show; border-collapse:collapse;">
						<tr>
							<td class=2-td1-left width="15%">�о�ȷ����</td>
							<td class=2-td2-t-left width="15%"><ttsoft:write name="jmslist" property="sjqrr"/></td>
							<td class=2-td1-left width="20%">�о�ȷ��ʱ��</td>
							<td class=2-td2-t-left width="20%"><ttsoft:write name="jmslist" property="sjqrrq"/></td>
							<td class=2-td1-left width="15%">�о�ȷ��״̬</td>
							<td class=2-td2-t-center width="15%"><ttsoft:write name="jmslist" property="sjqrzt"/></td>
						</tr>
						</table>
			     </td>
					</tr>
                 </table>
                 <%
                 		}
                 %>
                 
				<table width="80%" align="center" cellspacing="0">
			 		<tr>
				
					</tr>
					<tr>
			    		<td class="1-td2" colspan="7"><br>
						<table cellspacing="0" class="table-99">
						<tr>
							
						</tr>
						</table>
			     </td>
					</tr>
                 </table>
                 <table width="80%" align="center" cellspacing="0">
			<tr>
			    <td class="1-td2" colspan="6"><br>
					<table cellspacing="0" class="table-99">
						<tr>
                 	       <td colspan="3">
                 	       <div align="center">
                 	     
                 	       
                 	        <input type="image" accesskey="c" tabIndex="-1"
										onClick="cjjms('<%=Integer.parseInt(ind.toString())+1%>','<ttsoft:write name="jmslist" property="sbbxlh"/>','<ttsoft:write name="jmslist" property="zfbz" />','<ttsoft:write name="jmslist" property="jmszmbh" />');return false;" style="cursor: hand"
										onMouseOver="MM_swapImage('cjjmszm<%=ind %>','','<%=static_contextpath%>images/gdzys/gdzys_cjjmszm2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" alt="���߼���˰֤��" id="cjjmszm<%=ind %>"
										src="<%=static_contextpath%>images/gdzys/gdzys_cjjmszm1.jpg" width="95"
										border="0" height="22">
                 	       </div>
                 	       </td>
                 	 
                 	       <div>
                 	       <td colspan="3">
                 	       <div align="center">
                 	       <input type="image" accesskey="c" tabIndex="-1"
										onClick="tuichu();return false;" style="cursor: hand"
										onMouseOver="MM_swapImage('tc11<%=ind %>','','<%=static_contextpath%>images/tc-c2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" alt="�˳�" id="tc11<%=ind %>"
										src="<%=static_contextpath%>images/tc-c1.jpg" width="79"
										border="0" height="22">
									</div>
										</td>
							</div>
						</tr>
					</table>
			     </td>
			</tr>
</table> 
				</form>
			</div>
			
			</logic:iterate>
		<%@ include file="../../include/footer.jsp"%>
</body>
</html:html>