<%
String static_contextpath_alter = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>

							<br />
							<span onclick="add_row('theTable')" class="op_row">[增加]</span> <br />
<script>
						function page_prev() {
							var cur = $("currentPageNum").value;
							page_go( cur - 1 );
						}
						function page_next(cur_page) {
							var cur = $("currentPageNum").value;
							page_go( cur + 1 );
						}
						function page_go( cur_page ) {
							$("currentPageNum").value = cur_page;
							doTurnPageSubmit();
						}
						function page_last() {
							page_go( $("totalPageNum").value );
						}
						</script>
					<div>
					<img width="79" height="22" border="0" 
								src="<%=static_contextpath_alter%>/images/b-bz-sy1.jpg" 
								name="b-bz-sy" id="b-bz-sy" 
								onmouseout="MM_swapImgRestore()" 
								onmouseover="MM_swapImage('b-bz-sy','','<%=static_contextpath_alter%>/images/b-bz-sy2.jpg',1)" 
								onclick="page_go(1)" 
								/>
					
					<img width="79" height="22" border="0" 
								src="<%=static_contextpath_alter%>/images/shangyiye1.jpg" 
								name="shangyiye" id="shangyiye" 
								onmouseout="MM_swapImgRestore()" 
								onmouseover="MM_swapImage('shangyiye','','<%=static_contextpath_alter%>/images/shangyiye2.jpg',1)" 
								onclick="page_prev()" 
								/>	
					
					<img width="79" height="22" border="0" 
								src="<%=static_contextpath_alter%>/images/xaiyiye1.jpg" 
								name="xiayiye" id="xiayiye" 
								onmouseout="MM_swapImgRestore()" 
								onmouseover="MM_swapImage('xiayiye','','<%=static_contextpath_alter%>/images/xaiyiye2.jpg',1)" 
								onclick="page_next()" 
								/>	
					<img width="79" height="22" border="0" 
								src="<%=static_contextpath_alter%>/images/b-bz-my1.jpg" 
								name="b-bz-my" id="b-bz-my" 
								onmouseout="MM_swapImgRestore()"
								onmouseover="MM_swapImage('b-bz-my','','<%=static_contextpath_alter%>/images/b-bz-my2.jpg',1)" 
								onclick="page_last()" 
								/>	
					
					共&nbsp;<span id="sp_totalItemsNum"></span>&nbsp;条数据
					&nbsp;&nbsp;第&nbsp;<span id="sp_currentPageNum"></span>&nbsp;页
					&nbsp;&nbsp;共&nbsp;<span id="sp_totalPageNum"></span>&nbsp;页
					</div>
							<table width="99%" height="37" border="0">
								<tbody>
									<tr>
										<td height="26" nowrap="" align="center">
										
										<img
											width="79" height="22" border="0"
											src="images/xgbc1.jpg"
											name="xgbc" id="xgbc" 
											onmouseout="MM_swapImgRestore()"
											onmouseover="MM_swapImage('xgbc','','images/xgbc2.jpg',1)"
											onclick="doAlterCheck();" />
<img width="79" height="22" border="0" 
	src="<%=static_contextpath_alter%>images/dayin1.jpg" 
	onmouseout="MM_swapImgRestore()"  id="dayin"
	onmouseover="MM_swapImage('dayin','','<%=static_contextpath_alter%>images/dayin2.jpg',1)" 
	onclick="doPrint('PrintDJ')" 
/>										
									
										<img
											width="79" height="22" border="0"
											src="<%=static_contextpath_alter%>images/tuichu1.jpg"
											name="tuichu" id="tuichu" 
											onmouseout="MM_swapImgRestore()"
											onmouseover="MM_swapImage('tuichu','','<%=static_contextpath_alter%>images/tuichu2.jpg',1)"
											onclick="doReturn()" />
										</td>
									</tr>
								</tbody>
							</table>
							<table width="99%" border="0" cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td>
										<hr width="100%" size="1" class="hr1" />
										</td>
										<td width="87" align="center" class="black9"><strong><font
											color="#0000FF">注意事项</font></strong></td>
										<td>
										<hr width="100%" size="1" class="hr1" />
										</td>
									</tr>
								</tbody>
							</table>
							<table width="99%" border="1" align="center" cellpadding="1"
								cellspacing="1" bordercolor="#FFFFFF" class="black9">
								<tbody>
									<tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
										<td height="47">
										<p><font color="#57937D">&nbsp;&nbsp;&nbsp;&nbsp;
										1、带‘<font color="#FF0000">*</font>’项为必选（填）项； <br />
										&nbsp;&nbsp;&nbsp;&nbsp;
										2、点击“第一条”按钮可将光标直接停在第一条记录上，点击“上一条”按钮可将光标直接停在所在行的上一条记录上，&nbsp;&nbsp;
										<br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										点击“下一条”按钮可将光标直接停在所在行的下一条记录上， 点击“最末条”按钮可将光标直接停在最后一条记录上；<br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										您也可以直接用鼠标点击选中每一行记录；<br />
										&nbsp;&nbsp;&nbsp;&nbsp; <br />
										</font></p>
										</td>
									</tr>
								</tbody>
							</table>