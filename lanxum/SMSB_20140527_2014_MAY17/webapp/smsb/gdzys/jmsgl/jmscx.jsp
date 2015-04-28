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
<title>减免税查询</title>
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
			       document.getElementById("div"+str).style.display="";//显示
			   }
			   else
			   {
			       document.getElementById("div"+i).style.display='none';//隐藏
			      
			   }
		}
		
	}
	
	//去掉字符串中所有空格
	function trim(str){
		var aa=str.replace(/\s/g,"");
		return aa;
	}
	
	//验证是否四项输入都是空
	function validate(){
		var nsrmc=document.getElementById("nsrmc").value;
		var jsjdm=document.getElementById("jsjdm").value;
		var pzzdwh=document.getElementById("pzzdwh").value;
		var sbxlh=document.getElementById("sbxlh").value;
		if(trim(nsrmc)==""&&trim(jsjdm)==""&&trim(pzzdwh)==""&&trim(sbxlh)==""){
			alert("纳税人名称，计算机代码，批准占地文号，申报序列号四者不能都为空");
			return false;
		}else{
			document.forms[0].submit();
		}
		
	}
	
	//出具减免税证明
	function cjjms(str,sbb,zfbz,jmszmbh){
		if(zfbz=="1"){
			alert("减免税证明已作废，不能出具");
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
				<td class="1-td1" colspan="7">减免税查询</td>
			</tr>
			<tr>
				<td class="1-td2" colspan="7"><br>
					<table cellspacing="0" class="table-99" style="empty-cells:show; border-collapse:collapse;" >
						<tr>
							<td  class="2-td2-t-left">纳税人名称</td>
							<td  class="2-td2-t-left">
								<div align="left">
									<html:text property="nsrmc" size="15" maxlength="50" />
									</FONT>
								</div>
							</td>
							<td  class="2-td2-t-left">计算机代码</td>
							<td  class="2-td2-t-left">
								<div align="left">
									<html:text property="jsjdm" size="15" maxlength="50"/>
								</div>
							</td>

							<td class="2-td2-t-left">批准占地文号</td>
							<td class="2-td2-t-left">
								<div align="left">
									<html:text property="pzzdwh" size="20" maxlength="50"/>
								</div>
							</td>
						</tr>
						<tr>
							<td  class="2-td2-t-left">申报序列号</td>
							<td  class="2-td2-t-left">
								<div align="left">
									<html:text property="sbxlh" size="15" maxlength="50" />
									</FONT>
								</div>
							</td>
							<td  class="2-td2-t-left">状态</td>
							<td  class="2-td2-t-left">
								<div align="left">
								<%
									String lsta=((GdzysJmscxForm)request.getAttribute("gdzysJmscxForm")).getStatus();
								    lsta=lsta==null?"0":lsta;
									
								%>
									<html:select  property="status" value="<%=lsta %>">
										<html:option  value="全部">全部</html:option>
										<html:option  value="0">正常</html:option>
										<html:option  value="1">已撤销</html:option>
									</html:select>
								</div>
							</td>
							<td class="2-td2-t-left" colspan="2"></td>							
							

							
						</tr>
						
							<table width="100%" border="0" cellpadding="0" cellspacing="4">
							 <tr valign="bottom">
							 <td width="50%" align="right" >
								<div>								 
									<input name="I2" type="image" accesskey="q" value="查询"
										onclick="validate();return false;"
										onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" alt="查询"
										src="<%=static_contextpath%>images/cx-q1.jpg" width="79" border="0"
										height="22" id="chaxun" >
								 </div>
							 </td>
							 <td width="50%" align="left" >
								<div>
									<input type="image" accesskey="c" tabIndex="-1"
										onClick="tuichu();return false;" 
										onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" alt="退出" id="tc1"
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
                 align=center><FONT SIZE="2" COLOR="#FF0000">没有满足查询条件的结果记录！</STRONG>
                 </DIV>	
				</logic:equal>			
		<div id="mydiv" style="display:none">
		<table width="80%" align="center" cellspacing="0">
			<tr>
				<td class="1-td1" colspan="7">减免税查询信息结果</td>
			</tr>
			<tr>
				<td class="1-td2" colspan="7"><br>
					<table cellspacing="0" class="table-99">
						<tr>
							<td class=2-td1-left width="4%">选择</td>
							<TD class=2-td1-left width="14%">申报序列号</TD>
							<TD class=2-td1-left width="14%">纳税人名称</TD>
							<TD class=2-td1-left width="14%">计算机代码</TD>
							<TD class=2-td1-left width="14%">计税面积</TD>
							<TD class=2-td1-left width="14%">减免面积</TD>
							<TD class=2-td1-left width="12%">减免税额</TD>
							<TD class=2-td1-center width="14%">申报时间</TD>
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
						<td class="1-td1" colspan="7">税源详细信息</td>
					</tr>
					</tr>
					<tr>
						<td class="1-td2" colspan="7"><br>
							<table cellspacing="0" class="table-99" style="empty-cells:show; border-collapse:collapse;">
								<tr>基本信息：
								</tr>
								<TR>
									<td class=2-td1-left width="20%">申报序列号</td>
									<TD class=2-td2-t-left width="20%"><ttsoft:write
											name="jmslist" property="sbbxlh" /></TD>
									<TD class=2-td1-left width="20%">纳税人类型</TD>
									<TD class=2-td2-t-center width="20%"><ttsoft:write
											name="jmslist" property="nsrlx" /></TD>
								</TR>
								<TR>
									<td class=2-td1-left width="20%">是否市局审批</td>
									<TD class=2-td2-t-left width="20%"><ttsoft:write
											name="jmslist" property="sfsjsp" /></TD>
									<TD class=2-td1-left width="20%">税源类型</TD>
									<TD class=2-td2-t-center width="20%"><ttsoft:write
											name="jmslist" property="sylxmc" /></TD>
								</TR>
								<TR>
									<td class=2-td1-left width="20%">纳税人名称</td>
									<TD class=2-td2-t-left width="20%"><ttsoft:write
											name="jmslist" property="nsrmc" /></TD>
									<TD class=2-td1-left width="20%">计算机代码</TD>
									<TD class=2-td2-t-center width="20%"><ttsoft:write
											name="jmslist" property="jsjdm" /></TD>
								</TR>
								<TR>
									<td class=2-td1-left width="20%">纳税人识别号</td>
									<TD class=2-td2-t-left width="20%"><ttsoft:write
											name="jmslist" property="swdjzh" /></TD>
									<TD class=2-td1-left width="20%">纳税人所属行业</TD>
									<TD class=2-td2-t-center width="20%"><ttsoft:write
											name="jmslist" property="nsrsshy" /></TD>
								</TR>

								<TR>
									<td class=2-td1-left width="20%">纳税人详细地址</td>
									<TD class=2-td2-t-left width="20%"><ttsoft:write
											name="jmslist" property="nsrxxdz" /></TD>
									<TD class=2-td1-left width="20%">企业登记注册类型</TD>
									<TD class=2-td2-t-center width="20%"><ttsoft:write
											name="jmslist" property="qydjzclx" /></TD>
								</TR>
								<TR>
									<td class=2-td1-left width="20%">开户银行</td>
									<TD class=2-td2-t-left width="20%"><ttsoft:write
											name="jmslist" property="khyhmc" /></TD>
									<TD class=2-td1-left width="20%">银行账号</TD>
									<TD class=2-td2-t-center width="20%"><ttsoft:write
											name="jmslist" property="yhzh" /></TD>
								</TR>
								<TR>
									<td class=2-td1-left width="20%">联系人姓名</td>
									<TD class=2-td2-t-left width="20%"><ttsoft:write
											name="jmslist" property="lxrxm" /></TD>
									<TD class=2-td1-left width="20%">联系电话</TD>
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
								<tr>土地信息：
								</tr>

	                            <tr>
											<TD class=2-td1-left width="20%">建设项目名称</TD>
									       <TD class=2-td2-t-center width="20%" colspan="3"><ttsoft:write
											name="jmslist" property="jsxmmc" /></TD>
									
								</TR>
								<TR>
									<td class=2-td1-left width="20%">土地坐落地址</td>
									<TD class=2-td2-t-left width="80%" colspan="3""><ttsoft:write
											name="jmslist" property="tdzldz" /></TD>
								</tr>
							
								<TR>
								<TD class=2-td1-left width="20%">批准占地文号</TD>
									<TD class=2-td2-t-left width="20%"><ttsoft:write
											name="jmslist" property="pzzdwh" /></TD>
									<td class=2-td1-left width="20%">批准占地面积（批文农转用面积:平方米）</td>
									<TD class=2-td2-t-center width="20%"><ttsoft:write
											name="jmslist" property="pzjdmj" /></TD>
									
								</TR>
								<TR>
									<td class=2-td1-left width="20%">实际占地面积（与计税面积一致:平方米）</td>
									<TD class=2-td2-t-left width="20%"><ttsoft:write
											name="jmslist" property="sjzdmj" /></TD>
									<TD class=2-td1-left width="20%">批准时间/实际占地时间</TD>
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
									<td class=2-td1-left width="9%">序号</td>
									<td class=2-td1-left width="13%">占地用途</td>
									<td class=2-td1-left width="13%">适用税额（元/平方米）</td>
									<td class=2-td1-left width="13%">计税面积（平方米）</td>
									<td class=2-td1-left width="13%">计征税额（元）</td>
									<td class=2-td1-left width="13%">减免面积（平方米）</td>
									<td class=2-td1-left width="13%">减免税额（元）</td>
									<td class=2-td1-center width="13%">应纳税额（平方米）</td>

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
									<!-- <td class=2-td2-left width="9%">合计</td>
									<td class=2-td2-center width="13%"></td>
									<td class=2-td2-right width="13%"></td> -->
									<td class="2-td2-left" colspan="3">合计</td>
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
								<tr>减免信息：
								</tr>
								<tr>
									<td class=2-td1-left width="10%">序号</td>
									<td class=2-td1-left width="30%">减免税类别</td>
									<td class=2-td1-left width="30%">分类减免面积（平方米）</td>
									<td class=2-td1-left width="30%">分类减免税额（元）</td>
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
								<tr>申报减免理由:
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
								<tr>备注:
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
							<td class=2-td1-left width="10%">创建人</td>
							<td class=2-td2-t-left width="10%"><ttsoft:write name="jmslist" property="cjr"/></td>
							<td class=2-td1-left width="10%">创建时间</td>
							<td class=2-td2-t-left width="10%"><ttsoft:write name="jmslist" property="cjrq"/></td>
							<td class=2-td1-left width="10%">区县确认人</td>
							<td class=2-td2-t-left width="10%"><ttsoft:write name="jmslist" property="qrr"/></td>
							<td class=2-td1-left width="10%">区县确认时间</td>
							<td class=2-td2-t-left width="10%"><ttsoft:write name="jmslist" property="qrsj"/></td>
							<td class=2-td1-left width="10%">区县确认状态</td>
							<td class=2-td2-t-center width="10%"><ttsoft:write name="jmslist" property="qrzt"/></td>
						</tr>
						</table>
			     </td>
					</tr>
                 </table>
                 <%
                 		String sjflag=(String)((HashMap)ddd).get("sfsjsp");
                 		if(sjflag.equals("是")){
                 		
                 %>
                 <table width="80%" align="center" cellspacing="0">
			 		<tr>
				
					</tr>
					<tr>
			    		<td class="1-td2" colspan="7"><br>
						<table cellspacing="0" class="table-99" style="empty-cells:show; border-collapse:collapse;">
						<tr>
							<td class=2-td1-left width="15%">市局确认人</td>
							<td class=2-td2-t-left width="15%"><ttsoft:write name="jmslist" property="sjqrr"/></td>
							<td class=2-td1-left width="20%">市局确认时间</td>
							<td class=2-td2-t-left width="20%"><ttsoft:write name="jmslist" property="sjqrrq"/></td>
							<td class=2-td1-left width="15%">市局确认状态</td>
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
										onMouseOut="MM_swapImgRestore()" alt="出具减免税证明" id="cjjmszm<%=ind %>"
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
										onMouseOut="MM_swapImgRestore()" alt="退出" id="tc11<%=ind %>"
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