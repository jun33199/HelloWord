<%
String static_contextpath_control = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
						<br />
						<table width="75%" border="0" class="table-99">
							<tbody>
							<tr>
								<td width="59%">
								<div align="left">
									<img onmouseover="MM_swapImage('q3','','<%=static_contextpath_control%>images/diyitiao2.jpg',1)" onmouseout="MM_swapImgRestore()" value="��һ��" id="q3" onclick="first('daList')" src="<%=static_contextpath_control%>images/diyitiao1.jpg" width="79" height="22" />
									<img onmouseover="MM_swapImage('qq3','','<%=static_contextpath_control%>images/shangyitiao2.jpg',1)" onmouseout="MM_swapImgRestore()" value="��һ��" id="qq3" src="<%=static_contextpath_control%>images/shangyitiao1.jpg" onclick="previous('daList')" width="79" height="22" />
									<img onmouseover="MM_swapImage('qqq3','','<%=static_contextpath_control%>images/xiayitiao2.jpg',1)" onmouseout="MM_swapImgRestore()" value="��һ��" id="qqq3" src="<%=static_contextpath_control%>images/xiayitiao1.jpg" width="79" onclick="next('daList')" height="22" />
									<img onmouseover="MM_swapImage('last','','<%=static_contextpath_control%>images/zuimotiao2.jpg',1)" onmouseout="MM_swapImgRestore()" value="��һ��" id="last" src="<%=static_contextpath_control%>images/zuimotiao1.jpg" width="79" height="22" onclick="lastRow('daList')" />
									</div>
								</td>
								<td width="6%">
                                </td>
								<td width="14%">
								    <img onmouseover="MM_swapImage('Submit522','','<%=static_contextpath_control%>images/xinzeng2.jpg',1)" onmouseout="MM_swapImgRestore()" value="��һ��" id="Submit522" src="<%=static_contextpath_control%>images/xinzeng1.jpg" width="79" onclick="add('daList')" height="22" />
								</td>
								<td width="21%">
                                    <div align="left">
                                        <img onmouseover="MM_swapImage('del','','<%=static_contextpath_control%>images/shanchu2.jpg',1)" onmouseout="MM_swapImgRestore()" value="��һ��" id="del" src="<%=static_contextpath_control%>images/shanchu1.jpg" width="79" height="22" onclick="deleteRow('daList')" />
								    </div>
								</td>
							</tr>
						</tbody>
						</table>
											
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
							doSave();
						}
						function page_last() {
							page_go( $("totalPageNum").value );
						}
						</script>
					<div>
					<img width="79" height="22" border="0" 
								src="<%=static_contextpath_control%>/images/b-bz-sy1.jpg" 
								name="b-bz-sy" id="b-bz-sy" 
								onmouseout="MM_swapImgRestore()" 
								onmouseover="MM_swapImage('b-bz-sy','','<%=static_contextpath_control%>/images/b-bz-sy2.jpg',1)" 
								onclick="page_go(1)" 
								/>
					
					<img width="79" height="22" border="0" 
								src="<%=static_contextpath_control%>/images/shangyiye1.jpg" 
								name="shangyiye" id="shangyiye" 
								onmouseout="MM_swapImgRestore()" 
								onmouseover="MM_swapImage('shangyiye','','<%=static_contextpath_control%>/images/shangyiye2.jpg',1)" 
								onclick="page_prev()" 
								/>	
					
					<img width="79" height="22" border="0" 
								src="<%=static_contextpath_control%>/images/xaiyiye1.jpg" 
								name="xiayiye" id="xiayiye" 
								onmouseout="MM_swapImgRestore()" 
								onmouseover="MM_swapImage('xiayiye','','<%=static_contextpath_control%>/images/xaiyiye2.jpg',1)" 
								onclick="page_next()" 
								/>	
					<img width="79" height="22" border="0" 
								src="<%=static_contextpath_control%>/images/b-bz-my1.jpg" 
								name="b-bz-my" id="b-bz-my" 
								onmouseout="MM_swapImgRestore()"
								onmouseover="MM_swapImage('b-bz-my','','<%=static_contextpath_control%>/images/b-bz-my2.jpg',1)" 
								onclick="page_last()" 
								/>	
					
					��&nbsp;<span id="sp_totalItemsNum"></span>&nbsp;������
					&nbsp;&nbsp;��&nbsp;<span id="sp_currentPageNum"></span>&nbsp;ҳ
					&nbsp;&nbsp;��&nbsp;<span id="sp_totalPageNum"></span>&nbsp;ҳ
					</div>
											
					<table width="99%" height="37" border="0">
						<tbody>
						<tr>
							<td height="26" nowrap="" align="center">
							
							<img width="79" height="22" border="0" 
								src="images/xgbc1.jpg" 
								name="xgbc" id="xgbc" 
								onmouseout="MM_swapImgRestore()" 
								onmouseover="MM_swapImage('xgbc','','images/xgbc2.jpg',1)" 
								onclick="doSave()" />
								
<img width="79" height="22" border="0" 
	src="<%=static_contextpath_control%>images/dayin1.jpg" 
	onmouseout="MM_swapImgRestore()"  id="dayin"
	onmouseover="MM_swapImage('dayin','','<%=static_contextpath_control%>images/dayin2.jpg',1)" 
	onclick="doPrint('PrintDJ')" 
/>

								
							<img width="79" height="22" border="0" 
								src="<%=static_contextpath_control%>images/tuichu1.jpg" 
								name="tuichu" id="tuichu" 
								onmouseout="MM_swapImgRestore()" 
								onmouseover="MM_swapImage('tuichu','','<%=static_contextpath_control%>images/tuichu2.jpg',1)" onclick="doReturn()" />
							</td>
						</tr>
					</tbody>
					</table>
					<table width="99%" border="0" cellpadding="0" cellspacing="0">
						<tbody>
						<tr>
							<td><hr width="100%" size="1" class="hr1" /></td>
							<td width="87" align="center" class="black9">
							<strong><font color="#0000FF">ע������</font></strong></td>
							<td><hr width="100%" size="1" class="hr1" /></td>
						</tr>
					</tbody>
					</table>
					<table width="99%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
						<tbody>
						<tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
							<td height="47">
							<p><font color="#57937D">&nbsp;&nbsp;&nbsp;&nbsp; 1������<font color="#FF0000">*</font>����Ϊ��ѡ����
							<br />
&nbsp;&nbsp;&nbsp;&nbsp; 2������������ʺ���Ϣ����˰��Ӧ��ʵ������û�У���Ӧ��ɾ�������ݣ��������һ������ť��д��һ����Ϣ��<br />
&nbsp;&nbsp;&nbsp;&nbsp; 3���������һ������ť�ɽ����ֱ��ͣ�ڵ�һ����¼�ϣ��������һ������ť�ɽ����ֱ��ͣ�������е���һ����¼�ϣ�&nbsp;&nbsp; 
							<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �������һ������ť�ɽ����ֱ��ͣ�������е���һ����¼�ϣ� �������ĩ������ť�ɽ����ֱ��ͣ�����һ����¼�ϣ�<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��Ҳ����ֱ���������ѡ��ÿһ�м�¼��<br />
&nbsp;&nbsp;&nbsp;&nbsp; <br />
							</font></p>
							</td>
						</tr>
					</tbody>
					</table>