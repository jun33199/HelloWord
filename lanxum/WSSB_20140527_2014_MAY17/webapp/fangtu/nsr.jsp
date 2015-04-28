<%
String static_contextpath_nsr = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
com.ttsoft.common.model.UserData nsr_userdata = (com.ttsoft.common.model.UserData)session.getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA);
%>
					<table width="75%" border="0" class="table-99">
						<tbody>
						<tr>
							<td class="2-td1-left">
							<div align="left">
								<span class="black9">税务计算机代码
								<%if(nsr_userdata.yhlx.equals("01")){%>
									<input id="jsjdm"  type="text" name="jsjdm" size="20" onkeydown="if (event.keyCode==13) queryTaxpayer_SM();"/>
									<span class="bitian">*</span></span>
									<img name="query" src="<%=static_contextpath_nsr%>images/b-chaxun.gif" width="35" height="19" border="0" onclick="queryTaxpayer_SM()">
								<%}%>
								<%if(nsr_userdata.yhlx.equals("02")){%>
									<input id="jsjdm"  type="text" name="jsjdm" disabled="true" size="20" onkeydown="if (event.keyCode==13) queryTaxpayer();"/>
									<span class="bitian">*</span></span>
									<img name="query" src="<%=static_contextpath_nsr%>images/b-chaxun.gif" width="35" height="19" border="0" onclick="queryTaxpayer()">
								<%}%>
								
							</div>
							</td>
							<td class="2-td1-left">
							<div align="left">
								<span class="black9">纳税人名称 
								<input id="taxpayerName" type="text" name="taxpayerName" size="20" disabled="true"/>
								</span><span class="black9">纳税人识别号
								<input id="taxpayerId" type="text" name="taxpayerId" size="20" disabled="true"/>
								
								</span>
								<br/>
								<span class="black9">填表日期
								<input id="inputDate" type="text" name="inputDate" size="20" disabled="true"/>
								</span></div>
							</td>
						</tr>
					</tbody>
					</table>
					
										
					<div id="TABLE_CARD" style="display: none;">
					</div>
					<div id="DIV_CARD" style="display: none;">
					</div>