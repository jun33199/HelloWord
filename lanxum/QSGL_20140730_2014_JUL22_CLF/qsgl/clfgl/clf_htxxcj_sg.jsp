<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.ttsoft.common.util.SessionKey"%>
<HTML>
	<HEAD>
		<TITLE>��������Ϣ�ɼ�</TITLE>
	    <META content="text/html; charset=gb2312" http-equiv=Content-Type>
	    <SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
	    <SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
	    <script language=JavaScript src="../js/clfgl_util.js" type="text/javascript"></script>
	    <script language=JavaScript src="../js/clfgl_DynamicTable.js" type="text/javascript"></script>
	    <script language="javascript" src="../js/qscommon.js"></script>
	    <LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>   
	    

	</HEAD>
	
	<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0"  onLoad="initPage();" >
	    <jsp:include page="/include/Header.jsp" flush="true">
			<jsp:param name="subtitle" value="����������>��������Ϣ�ɼ�"/>
	        <jsp:param name="helpURL" value=""/>
	    </jsp:include>

	    
	<TABLE align=center border=0 cellPadding=0 cellSpacing=0  width=100%>
		<tr>
			<td vAlign=top>
				<TABLE align=center border=0 cellSpacing=0 height="100%" width=100%>
				<tr>
				<td vAlign=top>
				<TABLE align=center cellSpacing=0 class=table-99>
				    <tr>
						<td class=1-td1>
							<div style="lineBreak:strict" align="left">
								�ɼ���ʽѡ�񣺶�ά��ɨ��<html:radio name="clfxxcjForm" property="cjfsdm" value="01" onclick="changeCjfs(this);"></html:radio> �ֹ��ɼ�<html:radio name="clfxxcjForm" property="cjfsdm" value="02"></html:radio>
							</div>							
						������������ͬ��Ϣ��</td>
					</tr>
				    <tr><td class=1-td2>
					<html:form action="/clfgl/clfxxcj.do" type="POST">
					<html:hidden name="clfxxcjForm" property="operationType"/>
					<html:hidden name="clfxxcjForm" property="hasHdxx"/><!-- �Ƿ��к˶���Ϣ -->
					<html:hidden name="clfxxcjForm" property="xxly"/>
					<html:hidden name="clfxxcjForm" property="cjfsdm"/>
					
					<html:hidden name="clfxxcjForm" property="saveIsSuccess"/>
					<html:hidden name="clfxxcjForm" property="keyStr"/>
					<html:hidden name="clfxxcjForm" property="sbbh"/>
					<html:hidden name="clfxxcjForm" property="szlc"/>
					
					<html:hidden name="clfxxcjForm" property="all_sellerInfo"/>
					<html:hidden name="clfxxcjForm" property="all_buyerInfo"/>
					
						<table  border=0 cellSpacing=0 class=tabled-99 width="100%">
									
									<tr>
										<td colspan="7" align="center" class=2-td1-center>һ�����׷�����Ϣ</td>
									</tr>
									<tr>
										<td class=2-td2-left align="left" width="20%" nowrap="nowrap">
										<logic:notEqual name="clfxxcjForm"  property="xxly" value="01">
										��Ժ����֤�飩���
										</logic:notEqual>
										<logic:equal name="clfxxcjForm"  property="xxly" value="01">
										��ͬ���
										</logic:equal>										
										<font color="red">*</font></td>
										<td class=2-td2-left width="15%" nowrap="nowrap">
											<div align="left">
												<html:text name="clfxxcjForm" property="htbh" size="45"/>
												<IMG alt=��ѯ
														height=22 id=chaxun1 name=chaxun1
														onclick="doSubmitForm('Query');"
														onmouseout=MM_swapImgRestore()
														onmouseover="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
														src="<%=static_file%>images/chaxun1.jpg"
														style="CURSOR: hand" width=79>
											</div>
										</td>	
										<td class=2-td2-left align="left" width="20%" nowrap="nowrap">
										<logic:notEqual name="clfxxcjForm"  property="xxly" value="01">
										��Ժ����֤�飩����
										</logic:notEqual>
										<logic:equal name="clfxxcjForm"  property="xxly" value="01">
										����ǩԼ����
										</logic:equal>										
										
										<font color="red">*</font></td>
										<td class=2-td2-center width="45%" colspan="3">
											<div align="left">
												<html:text name="clfxxcjForm" property="htwsqyrq" onchange="isDate(this,false,'����Ժ����֤�飩���ڡ�');" maxlength='8'/>���ڸ�ʽΪ:yyyyMMdd
											</div>
										</td>																				
									</tr>
									
									<logic:equal name="clfxxcjForm"  property="xxly" value="01">
										<tr>
											<td class=2-td2-left align="left">����Ȩ��ת������<font color="red">*</font></td>
											<td class=2-td2-center colspan="5"><div align="left">��������</div></td>									
										</tr>
									</logic:equal>
										
								<logic:notEqual name="clfxxcjForm"  property="xxly" value="01">
									<tr>
										<td class=2-td2-left align="left">����Ȩ��ת������<font color="red">*</font></td>
										<td class=2-td2-left><div align="left">
											<html:select  name="clfxxcjForm" property="fwqszylx" style='width:37%'>
												<html:option value="05">���ݽ���</html:option>
												<html:option value="04">��������</html:option>
												<html:option value="06">��Ժ�ж�</html:option>
												<html:option value="03">��������</html:option>
											</html:select></div></td>	
																						
										<td class=2-td2-left align="left">��������</td>
										<td class=2-td2-center class=2-td2-center colspan="3">
						                	<div align="left"><bean:define id="dto" name="clfxxcjForm" property="codeList_fwxz"/>
						                  		<html:select name="clfxxcjForm" property="fwxzdm" style='width:31%' >
						                    	<html:options collection="dto" labelProperty="fwxzmc" property="fwxzdm"/>
						                  </html:select></div>											
										</td>											
									</tr>
									
									<tr>
										<td class=2-td2-left align="left">Ȩ��ת�Ʒ��ݵ�ַ����<font color="red">*</font></td>
										<td class=2-td2-left>
											<div align="left">
													<%-- <html:text name="clfxxcjForm" property="fwzlqx"/> --%>
													<html:select name="clfxxcjForm" property="fwzlqx" style='width:37%'>
														<html:option value="2">������</html:option>
														<html:option value="3">������</html:option>
														<html:option value="4">������</html:option>
														<html:option value="5">������</html:option>
														<html:option value="6">������</html:option>
														<html:option value="7">������</html:option>
														<html:option value="8">��̨��</html:option>
														<html:option value="9">ʯ��ɽ��</html:option>
														<html:option value="10">ͨ����</html:option>
														<html:option value="11">��ɽ��</html:option>
														<html:option value="12">˳����</html:option>
														<html:option value="13">��ͷ����</html:option>
														<html:option value="14">������</html:option>
														<html:option value="15">������</html:option>
														<html:option value="16">������</html:option>
														<html:option value="17">��ƽ��</html:option>
														<html:option value="18">������</html:option>
														<html:option value="19">ƽ����</html:option>
													</html:select>											
											</div></td>	
											
											<td class=2-td2-center colspan="4">&nbsp;</td>
									</tr>
									</logic:notEqual>
									<tr>
										<td class=2-td2-left align="left">Ȩ��ת�Ʒ�����ϸ��ַ�������أ�<font color="red">*</font></td>
										<td class=2-td2-center colspan="5">
											<div align="left">
													<html:text name="clfxxcjForm" property="fwzldz" maxlength="500" size="90.5%"/>
											</div></td>										
									</tr>
									<tr>
										<td class=2-td2-left align="left">��������Ȩ֤��<font color="red">*</font></td>
										<td class=2-td2-left>
											<div align="left">
													<html:text name="clfxxcjForm" property="fwcqzh" size="53%"/>
											</div></td>	
										<td class=2-td2-left align="left">��������Ȩ֤�����<font color="red">*</font></td>
										<td class=2-td2-center colspan="3">
										<div align="left">
											<html:text name="clfxxcjForm" property="fwsyqztfrq" onchange="isDate(this,false,'����������Ȩ֤����ڡ�');" maxlength='8'/>���ڸ�ʽΪ:yyyyMMdd
										</div></td>										
									</tr>
									<tr>
										<td class=2-td2-left align="left">���ν����Ƿ�Ϊ�״������ѹ�����<font color="red">*</font></td>
										<td class=2-td2-left>
											<div align="left">
												<html:select name="clfxxcjForm" property="sfwscsssggf" style='width:37%'>
													<html:option value="1">��</html:option>
													<html:option value="0">��</html:option>
												</html:select>
											</div>
										</td>									
										<td class=2-td2-left align="left">���ݽ������<font color="red">*</font></td>
										<td class=2-td2-center colspan="3">
										
											<div align="left">
												<html:text name="clfxxcjForm" property="fwjzmj"/>
											</div></td>
									</tr>

									<tr>
										<td class=2-td2-left align="left">�����ṹ<font color="red">*</font></td>
										<td class=2-td2-left>
											<div align="left">
												<html:select name="clfxxcjForm" property="jzjgdm" style='width:37%'>
													<html:option value="1">�ֽṹ</html:option>
													<html:option value="2">�ֽ�������ṹ</html:option>
													<html:option value="6">�֡��ֽ�������ṹ</html:option>																										
													<html:option value="3">��Ͻṹ</html:option>
													<html:option value="4">שľ�ṹ</html:option>
													<html:option value="5">�����ṹ</html:option>
												</html:select>											 
											</div>
										</td>	
										<td class=2-td2-left align="left">���ݻ�����;<font color="red">*</font></td>
										<td class=2-td2-center colspan="3">
											<div align="left">
											<logic:notEqual name="clfxxcjForm"  property="xxly" value="01">
													<html:select name="clfxxcjForm" property="ghyt" style='width:31%'>
														<html:option value="21">��������ס��</html:option>
														<html:option value="1">��ͨסլ</html:option>
														<html:option value="3">����</html:option>																										
														<html:option value="2">��Ԣ</html:option>
														<html:option value="4">��ҵ</html:option>
														<html:option value="5">д��¥</html:option>
														<html:option value="6">��ҵ����</html:option>
														<html:option value="7">����</html:option>
														<html:option value="10">����</html:option>
													</html:select>											
											</logic:notEqual>
											<logic:equal name="clfxxcjForm"  property="xxly" value="01">
												<bean:write name="clfxxcjForm" property="ghyt"/>&nbsp;										
											</logic:equal>											
											</div>
										</td>																		
									</tr>	
									
									<tr>
										<td class=2-td2-left align="left">����¥��<font color="red">*</font></td>
										<td class=2-td2-left>
											<div align="left">
												<input id="szlc_show" onchange="isSzlc_zlc('szlc');"/>
												&nbsp;&nbsp;&nbsp;&nbsp;�ܲ���<font color="red">*</font>		
												<input id="zlc_show" onchange="isSzlc_zlc('zlc');"/>								
											</div>
											
											</td>
										<td class=2-td2-left align="left">Ȩ��ת�Ʒ����ܼ�<font color="red">*</font></td>
										<td class=2-td2-center colspan="3">
											<div align="left">
												<html:text name="clfxxcjForm" property="htzj"/>										
											</div>
										</td>																	
									</tr>
									<tr>
										<td class=2-td2-left align="left">�����</td>
										<td class=2-td2-left>
						                	<div align="left"><bean:define id="bzdto" name="clfxxcjForm" property="codeList_bz"/>
						                  		<html:select property="bzdm" style='width:37%' >
						                    	<html:options collection="bzdto" labelProperty="bzmc" property="bzdm"/>
						                  </html:select></div>											
										</td>
										<td class=2-td2-left align="left">��ҽ��</td>
										<td class=2-td2-left width="15%">
										    <div align="left">
												<html:text name="clfxxcjForm" property="wbje"/>											
											</div></td>	
										<td class=2-td2-left align="left" width="5%">����</td>	
										<td class=2-td2-center id="hlShow">
											<div align="left">
												<html:text name="clfxxcjForm" property="hl" onchange="checkWb()"/>
											</div>										
										</td>																										
									</tr>																																			
								</table>																	
								<br>
								<table  border=0 cellSpacing=0 class=tabled-99 width="100%" id="sellTab">
									<tr>
										<td colspan="7" class=2-td1-center>����������Ϣ</td>
									</tr>
   		                            <tr >
    		                            <td colspan="7" align="center" class="2-td2-center">
    		                            <img onClick="addLabelCol('labelsample');" alt="����" style="cursor:hand" onMouseOver="MM_swapImage('add','','<%=static_file%>images/xinzeng2.jpg',1)" onMouseOut="MM_swapImgRestore()" id="add" src="<%=static_file%>images/xinzeng1.jpg" width="79" height="22"  >
    		                            <img onClick="removeLabelCol('sellTab');" alt="ɾ��" style="cursor:hand" onMouseOver="MM_swapImage('del','','<%=static_file%>images/shanchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" id="del" src="<%=static_file%>images/shanchu1.jpg" width="79" height="22"  >
    		                                <!-- <input onclick="getMMFXXContext('sellTab');" type="button" value="���ύ��" name="delButton"> -->
    		                            </td>
   		                            </tr>									
								</table>
								<br>
								<table  border=0 cellSpacing=0 class=tabled-99 width="100%" id="buyTab">
									<tr>
										<td colspan="7" class=2-td1-center>��������Ϣ</td>
									</tr>	
   		                            <tr >
    		                            <td colspan="7" align="center" class="2-td2-center">
   		                                <img onClick="addLabelCol('labelsample');" alt="����" style="cursor:hand" onMouseOver="MM_swapImage('add','','<%=static_file%>images/xinzeng2.jpg',1)" onMouseOut="MM_swapImgRestore()" id="add" src="<%=static_file%>images/xinzeng1.jpg" width="79" height="22"  >
    		                            <img onClick="removeLabelCol('buyTab');" alt="ɾ��" style="cursor:hand" onMouseOver="MM_swapImage('del','','<%=static_file%>images/shanchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" id="del" src="<%=static_file%>images/shanchu1.jpg" width="79" height="22"  >
    		                                <!-- <input onclick="getMMFXXContext('buyTab');" type="button" value="���ύ��" name="delButton"> -->
    		                            </td>
   		                            </tr>																																
								</table>
								<br>
								<table  border=0 cellSpacing=0 class=tabled-99 width="100%">
								   	<tr>
										<td colspan="4" class=2-td1-center>�ġ����ز��н������Ϣ</td>
									</tr>
									<tr>
										<td class=2-td2-center>
											<html:text name="clfxxcjForm" property="fdczjjgmc" maxlength="500" size="100%"/>
										</td>
									</tr>
								</table><br>
								<table>
									<tr>
										<td id="ShowButtons" colspan="4">
										<logic:notEqual name="clfxxcjForm" property="saveIsSuccess" value="1">
											<IMG alt=���� height=22 id=baocun name=baocun onclick="doSubmitForm('Save')" onmouseout=MM_swapImgRestore() onmouseover="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)" src="<%=static_file%>images/baocun1.jpg" style='CURSOR: hand' width=79>
										</logic:notEqual>
											<IMG alt=���ҳ��������Ϣ������¼��   height=22 id='chongzhi' name='chongzhi' onclick="doSubmitForm('reSet')" onmouseout=MM_swapImgRestore() onmouseover="MM_swapImage('chongzhi','','<%=static_file%>images/chongzhi2.jpg',1)" src="<%=static_file%>images/chongzhi1.jpg" style='CURSOR: hand' width=79>		
										
										<logic:equal name="clfxxcjForm" property="saveIsSuccess" value="1">
											
											<logic:equal name="clfxxcjForm" property="hasMAuthorise" value="true">
													<IMG alt=ɾ���òɼ���Ϣ  height=22 id=shanchu name=shanchu onclick="doSubmitForm('Delete')" onmouseout=MM_swapImgRestore() onmouseover="MM_swapImage('shanchu','','<%=static_file%>images/shanchu2.jpg',1)" src="<%=static_file%>images/shanchu1.jpg" style='CURSOR: hand' width=79>
													<IMG alt=�������޸���Ϣ  height=22 id=b-bcbg name=b-bcbg onclick="doSubmitForm('Update')" onmouseout=MM_swapImgRestore() onmouseover="MM_swapImage('b-bcbg','','<%=static_file%>images/b-bcbg2.jpg',1)" src="<%=static_file%>images/b-bcbg1.jpg" style='CURSOR: hand' width=79>
											</logic:equal>
											
											
											<IMG alt=��һ��  height=22 id=xiayibu name=xiayibu  onclick="doSubmitForm('ToPrint')" onmouseout=MM_swapImgRestore() onmouseover="MM_swapImage('xiayibu','','<%=static_file%>images/xiayibu2.jpg',1)" src="<%=static_file%>images/xiayibu1.jpg" style='CURSOR: hand' width=79>
										</logic:equal>
										
											<%-- <IMG alt=ת����˰������  height=22 id=mfskzs1 name=mfskzs1 onclick="doSubmitForm('ToSellerQSZS')" onmouseout=MM_swapImgRestore() onmouseover="MM_swapImage('mfskzs1','','<%=static_file%>images/mfskzs2.jpg',1)" src="<%=static_file%>images/mfskzs1.jpg" style='CURSOR: hand' width=130>
											<IMG alt=ת��Ʊ����  height=22 id=fpdk1 name=fpdk1 onclick="doSubmitForm('ToFPDK')" onmouseout=MM_swapImgRestore() onmouseover="MM_swapImage('fpdk1','','<%=static_file%>images/fpdk2.jpg',1)" src="<%=static_file%>images/fpdk1.jpg" style='CURSOR: hand' width=100>
											<IMG alt=ת��˰�걨  height=22 id=qssb1 name=qssb1 onclick="doSubmitForm('ToQSSB')" onmouseout=MM_swapImgRestore() onmouseover="MM_swapImage('qssb1','','<%=static_file%>images/qssb2.jpg',1)" src="<%=static_file%>images/qssb1.jpg" style='CURSOR: hand' width=100> --%>
											<IMG alt=�˳�  height=22 id=tuichu1 name=tuichu1 onclick="doSubmitForm('Return')" onmouseout=MM_swapImgRestore() onmouseover="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)" src="<%=static_file%>images/tuichu1.jpg" style='CURSOR: hand' width=79>
										</td>
									</tr>
								
								</table>                                                                                                                                  
					</html:form>
						<%@ include file="../include/Bottom.jsp" %>
					</td>
					</tr>
					</TABLE>
					</td></tr>				
				</TABLE>
			</td>
		</tr>
	</TABLE>
	
			<table style="display:none"  >
   		    <tr id="labelsample_title" height="24">
				<td class=2-td2-left  width="20%">���ƣ�������</td>
				<td class=2-td2-left width="20%">���</td>								
				<td class=2-td2-left>֤������</td>
				<td class=2-td2-left>֤������</td>								
				<td class=2-td2-left>Ȩ���˷ݶ�</td>
				<td class=2-td2-left>��ϵ�˵绰</td>
				<td class=2-td2-center>ѡ��</td>
   		    </tr>
   		    			
   		    <tr id="labelsample" height="24">
				<td class=2-td2-left  width="20%"><input id="nsr_mc" type="text"></td>
				<td class=2-td2-left width="20%">
					<select id="nsr_lb">
						<option value="1">����</option>
						<option value="0">�Ǹ���</option>
					</select>
				</td>								
				<td class=2-td2-left>
					<select id="nsr_zjlx">
						<logic:iterate id="item" name="clfxxcjForm"  property ="zjList" indexId="index">
							<option value="<bean:write name="item" property="zjlxdm"/>"><bean:write name="item" property="zjlxmc"/></option>
						</logic:iterate> 
					</select>				
				</td>
				<td class=2-td2-left><input id="nsr_zjhm" type="text"></td>								
				<td class=2-td2-left><input id="nsr_qlrfe" type="text"></td>
				<td class=2-td2-left><input id="nsr_lxdh" type="text"></td>
				<td class=2-td2-center align="center" width="5%" ><input type="checkbox" name="labelFlag" id="labelFlag" value="" ></td>
   		    </tr>
   	    </table>

	</BODY>
		    <script type="text/javascript">
		    var saveIsSuccess='<bean:write name="clfxxcjForm" property="saveIsSuccess"/>';

	    //�˺����Ǳ������֮��������ʾ�ѱ�����Ϣ�ĺ���
	    function showSaveData(){
 	    	//������Ϣ
	    	var sellerInfo = '<bean:write name="clfxxcjForm" property="all_sellerInfo"/>';
	    	parseSaveBuyorSellInfo(sellerInfo,"sellTab",document.forms[0].all_sellerInfo);
	    	
	    	//����Ϣ
	    	var buyyerInfo = '<bean:write name="clfxxcjForm" property="all_buyerInfo"/>';
			parseSaveBuyorSellInfo(buyyerInfo,"buyTab",document.forms[0].all_buyerInfo);
			
			//��ʾ����¥�����¥��
			setSZLC_ZLC();
 
	    	
	    }

	    function initPage(){
	    	//�������ɹ����߱���ʧ��
		    if(saveIsSuccess != "0"){
		    	showSaveData();
		    }else{
	    		document.all.htbh.focus();
		    }
	    }

	  
	  
	  //�����������֮�󷵻�ʱ����������Ϣ
	  function parseSaveBuyorSellInfo(buyorSellInfo,tableId,hidPropertyObj){
		  
		  if(buyorSellInfo != null && buyorSellInfo != ""){
			  if(buyorSellInfo.indexOf("^")>0){
				  var infoArr = buyorSellInfo.split("^");
				  
				  for(var index =0; index < infoArr.length;index ++){
					  var tempInfo = infoArr[index];
					  //���ý�������������ÿ������������Ϣ����ʾ
					  setBuyorSellInfo(tempInfo,tableId,hidPropertyObj);
				  }
			  }else{
				  setBuyorSellInfo(buyorSellInfo,tableId,hidPropertyObj);
			  }
		  }
		  
		  
	  }
	  
	  //��������˫����Ϣ
	  function setBuyorSellInfo(info,tableId,hidPropertyObj){
		  		var isSuccess = false;
		  
	  			if(info == null || info ==""){
	  				return isSuccess;
	  			}
		  
	    	var tableobj=document.getElementById(tableId);
			var AllGroupInfoArr = info.split("~");
			var submitInfo ="";
			
			if(AllGroupInfoArr != null && AllGroupInfoArr !=""){
				var count = AllGroupInfoArr.length/6;
				//alert("��Ϣ����"+count);
				
				if(count!= null && count!=0){
					var oneGroupInfo = new Array();
					for(zsIndex =0; zsIndex<count;zsIndex++){
						var value_0 = AllGroupInfoArr[0+(zsIndex*6)];
						var value_1 = AllGroupInfoArr[1+(zsIndex*6)];
						var value_2 = AllGroupInfoArr[2+(zsIndex*6)];
						var value_3 = AllGroupInfoArr[3+(zsIndex*6)];
						var value_4 = AllGroupInfoArr[4+(zsIndex*6)];
						var value_5 = AllGroupInfoArr[5+(zsIndex*6)];
					
						oneGroupInfo.push(value_0);
						oneGroupInfo.push(value_1);
						oneGroupInfo.push(value_2);//֤�����ʹ���
						oneGroupInfo.push(value_3);
						oneGroupInfo.push(value_4);
						oneGroupInfo.push(value_5);
						
						//alert("RETURN::"+DSzjdm);
						
						//���ò���ʾÿ����Ϣ
						isSuccess = setOneGroupInfo(oneGroupInfo,tableobj);
						
						if(!isSuccess){
							alert("��ʾ��������Ϣ����");
							return isSuccess;
						}
						
						//ƴ���ύ����̨����Ϣ
						var tempOneGroupValue = value_0 +"~"+value_1+"~"+value_2+"~"+value_3+"~"+value_4+"~"+value_5;
						if(zsIndex == 0){
							submitInfo = tempOneGroupValue;
						}else{
							submitInfo = submitInfo+"^"+tempOneGroupValue;
						}
						//������Ϣ
						oneGroupInfo = new Array();
					}
					//�����ύ�������ֵ
					if(hidPropertyObj != null && hidPropertyObj !=""){
						hidPropertyObj.value = submitInfo;
					}
				}
			}
			
			return true;
	  }
	  
	  //��ÿ����Ϣ���õ�td��innerHTML����ʾ
	  function setOneGroupInfo(arr,tableobj){
		  
	  			if(tableobj == null || tableobj == ""){
	  				return false;
	  			}
	  			var tabLength = tableobj.rows.length;
	  			
	  			//��ʼ����ͷ
	  			if(tabLength == 2){
		    		var otr = tableobj.insertRow(tabLength-1);
		    		//��һ�е�һ��  �����ƣ���������
		    		var mc_Cell=otr.insertCell();
		    		mc_Cell.innerHTML="���ƣ�������";
		    		mc_Cell.className ="2-td2-left";
		    		mc_Cell.WIDTH="30%";
		    		
		    		//��һ�еڶ���  �����
		    		var lb_Cell=otr.insertCell();
		    		lb_Cell.innerHTML="���";
		    		lb_Cell.className ="2-td2-left";
		    		lb_Cell.WIDTH="10%";
		    		
		    		//��һ�е����� ��֤�����͡�
		    		var zjlx_Cell=otr.insertCell();
		    		zjlx_Cell.innerHTML="֤������";
		    		zjlx_Cell.className ="2-td2-left";
		    		zjlx_Cell.WIDTH="10%";	
		    		
		    		//��һ�е����� ��֤�����롱
		    		var zjhm_Cell=otr.insertCell();
		    		zjhm_Cell.innerHTML="֤������";
		    		zjhm_Cell.className ="2-td2-left";
		    		zjhm_Cell.WIDTH="20%";	
		    		
		    		//��һ�е����� ��Ȩ���˷ݶ
		    		var fe_Cell=otr.insertCell();
		    		fe_Cell.innerHTML="Ȩ���˷ݶ�";
		    		fe_Cell.className ="2-td2-left";
		    		fe_Cell.WIDTH="10%";	
		    		
		    		//��һ�е����� ����ϵ�˵绰��
		    		var lxdh_Cell=otr.insertCell();
		    		lxdh_Cell.innerHTML="��ϵ�˵绰";
		    		lxdh_Cell.className ="2-td2-left";
		    		lxdh_Cell.WIDTH="20%";
		    		
		    		//��һ�е����� ��ѡ��
		    		var xz_Cell=otr.insertCell();
		    		xz_Cell.innerHTML="ѡ��";
		    		xz_Cell.className ="2-td2-center";
		    		xz_Cell.WIDTH="20%";
		    		
	  			}
	  			
	    		//�½���
	    		tabLength = tableobj.rows.length;//��������һ��rows����
	    		var otr_n = tableobj.insertRow(tabLength-1);
	    		
	    		//��n(n>=2)�е�һ�� 
	    		var mc_value_Cell=otr_n.insertCell();
	    		mc_value_Cell.className ="2-td2-left";
	    		fnAddInput(mc_value_Cell,arr[0],"nsr_mc","text");

	    		//��n(n>=2)�еڶ���  
	    		var lb_value_Cell=otr_n.insertCell();
	    		lb_value_Cell.className ="2-td2-left";
	    		var valuesArr = new Array(['1','����'],['0','�Ǹ���']);
	    		fnAddSelect(lb_value_Cell,"nsr_lb",valuesArr,arr[1])

	    		//��n(n>=2)�е�����
	    		var zjlx_value_Cell=otr_n.insertCell();
	    		zjlx_value_Cell.className ="2-td2-left";
	    		var zjArr = getZjArr();
	    		fnAddSelect(zjlx_value_Cell,"nsr_zjlx",zjArr,arr[2]);
	    		
	    		
	    		//��n(n>=2)�е����� 
	    		var zjhm_value_Cell=otr_n.insertCell();	    		
	    		zjhm_value_Cell.className ="2-td2-left";
	    		fnAddInput(zjhm_value_Cell,arr[3],"nsr_zjhm","text");

	    		//��n(n>=2)�е����� 
	    		var fe_value_Cell=otr_n.insertCell();
	    		fe_value_Cell.className ="2-td2-left";
	    		fnAddInput(fe_value_Cell,arr[4],"nsr_qlrfe","text");
	    		
	    		//��n(n>=2)�е����� 
	    		var lxdh_value_Cell=otr_n.insertCell();
	    		lxdh_value_Cell.className ="2-td2-left";
	    		fnAddInput(lxdh_value_Cell,arr[5],"nsr_lxdh","text");
	    		
	    		//��n(n>=2)�е����� 
	    		var checkbox_value_Cell=otr_n.insertCell();
	    		checkbox_value_Cell.className ="2-td2-center";
	    		fnAddInput(checkbox_value_Cell,arr[5],"labelFlag","checkbox");	    		
	    		
	    		return true;
	  }
	  
	  //��ָ��Ԫ���´���һ��imput�����
	  function fnAddInput(cellObj,newItemValue,newItemId,inputType)
	  {
		  var oNewItem = document.createElement("INPUT");
		  //����������
		  if(inputType=="text"){
		  	oNewItem.value=newItemValue;
		  }
		  oNewItem.id=newItemId;
		  oNewItem.name=newItemId;
		  oNewItem.type=inputType;
		  cellObj.insertAdjacentElement("afterBegin",oNewItem);
		  
		  
		  
		  
	  }
	  
	  //��ָ��Ԫ���´���һ��select������
	  function fnAddSelect(cellObj,newItemId,valuesArr,value)
	  {
		 
	     //����һ��select��ǩ
		  var oNewItem = document.createElement("SELECT");
		  oNewItem.id=newItemId;
		  
		  for(var index = 0; index < valuesArr.length; index ++)
		  {
			  var oValueArr = valuesArr[index];
			  var optionValue=oValueArr[0];//option��ֵ
			  var optionName=oValueArr[1];//option������
			  
			  //����һ��option��ǩ
			  var optionObj = document.createElement("OPTION");
			  optionObj.innerHTML=optionName;
			  optionObj.value=optionValue;
			  if(value == optionValue){
				  optionObj.selected=true; 
			  }
			  
			  //
			  oNewItem.insertAdjacentElement("beforeEnd",optionObj);
		  }
		  cellObj.insertAdjacentElement("afterBegin",oNewItem);
	  }	  

	  //���֤������
	  function getZjArr(){
		  var zjArr = new Array();
	         <logic:iterate id="item" name="clfxxcjForm"  property ="zjList" indexId="index">
				var zjdm_1 = '<bean:write name="item" property="zjlxdm"/>';
				var zjmc_1 = '<bean:write name="item" property="zjlxmc"/>';
				
				var oneZj = new Array();
				oneZj.push(zjdm_1);
				oneZj.push(zjmc_1);
				
				//
				zjArr.push(oneZj);
			</logic:iterate> 
			
			return zjArr;
		}  
	  
	  
	//���֤������	  
	function getZjmc(zjdm){
         <logic:iterate id="item" name="clfxxcjForm"  property ="zjList" indexId="index">
			var zjdm_1 = '<bean:write name="item" property="zjlxdm"/>';
			var zjmc_1 = '<bean:write name="item" property="zjlxmc"/>';
			
			if(zjdm == zjdm_1){
				
			return zjmc_1;
			
			}
		</logic:iterate> 
		
		return "����";
	} 	
	
	/**
		У�����ݿ������
	*/
	function doCheckNotNullItems(){
		
		var isSuccess = true;
		var obj = null;
		
		//��ͬ���
		obj = document.forms[0].htbh;
		if(obj.value ==null || obj.value==""){
			alert("����Ժ����֤�飩��š�Ϊ�գ����飡");
			obj.focus();
			return false;
		}
		//��Ժ����֤�飩����
		obj = document.forms[0].htwsqyrq;
		if(obj.value == null || obj.value ==""){
			alert("����Ժ����֤�飩���ڡ�Ϊ��,���飡");
			obj.focus();
			return false;			
		
		}
		//����Ȩ��ת������
		
		//Ȩ��ת�Ʒ��ݵ�ַ����
/* 		obj = document.forms[0].fwzlqx;
		if(obj.value == null || obj.value ==""){
			alert("��Ȩ��ת�Ʒ��ݵ�ַ���ء�Ϊ��,���飡");
			obj.focus();
			return false;			
		
		} */
		
		//Ȩ��ת�Ʒ�����ϸ��ַ�������أ�
		obj = document.forms[0].fwzldz;
		if(obj.value == null || obj.value == ""){
			alert("��Ȩ��ת�Ʒ�����ϸ��ַ�������أ���Ϊ��,���飡");
			obj.focus();
			return false;
		}
		//��������Ȩ֤��
		obj = document.forms[0].fwcqzh;
		if(obj.value == null || obj.value == ""){
			alert("����������Ȩ֤�š�Ϊ��,���飡");
			obj.focus();
			return false;
		}		
		
		//��������Ȩ֤�����
		obj = document.forms[0].fwsyqztfrq;
		if(obj.value == null || obj.value == ""){
			alert("����������Ȩ֤����ڡ�Ϊ��,���飡");
			obj.focus();
			return false;
		}		
		
		//���ν����Ƿ�Ϊ�״������ѹ�����
			
		//���ݽ������
		obj = document.forms[0].fwjzmj;
		if(obj.value == null || obj.value ==""){
			alert("�����ݽ��������Ϊ��,���飡");
			obj.focus();
			return false;		
		}else{
			if(!checkNumber(obj.value,16,3,true))
        	{
            	alert("�����ݽ������������Ϊ���֣�\nС�����ĳ������Ϊ3λ��\n������ֵ����ڵ����㣡");
            	obj.select();
            	return false;
        	}
			
		}
		
		//�����ṹ
		
		//���ݻ�����;
		 
		//����¥��/�ܲ���
		obj = document.forms[0].szlc;
		if(obj.value == null || obj.value ==""){
				//alert("������¥��/�ܲ�����Ϊ��,���飡");
				if(!isSzlc_zlc("all")){
					return false;
				}
			
		}
		
		//Ȩ��ת�Ʒ����ܼ�
		obj = document.forms[0].htzj;
		if(obj.value == null || obj.value  ==""){
			alert("��Ȩ��ת�Ʒ����ܼۡ�Ϊ��,���飡");
			obj.focus();
			return false;			
		
			
		}else{
			if(!checkNumber(obj.value,15,2,true))
        	{
            	alert("��Ȩ��ת�Ʒ����ܼۡ�����Ϊ���֣�\nС�����ĳ������Ϊ2λ��\n������ֵ����ڵ����㣡");
            	obj.select();
            	return false;
        	}
			
		}
		
		//�������֡���ҽ�����
		if(!checkWb()){
			return false;
		}		
		
		
		
		//������Ϣ
		obj = document.forms[0].all_sellerInfo;
		if(obj.value == null || obj.value  ==""){
			alert("��������Ϣ��Ϊ��,���飡");
			return false;			
		}		
		//����Ϣ
		obj = document.forms[0].all_buyerInfo;
		if(obj.value == null || obj.value  ==""){
			alert("������Ϣ��Ϊ��,���飡");
			return false;			
		}	
		
		//���ز��н������Ϣ
		
		
		return isSuccess;
	}
	
	function checkWb(){
		var objBzdm = document.forms[0].bzdm;
		var objWbje = document.forms[0].wbje;
		var objHl = document.forms[0].hl;
		//�����
		//�����Ϊ�գ�������ҽ���Ѿ���д������ʾѡ�������
		if((objWbje.value !=null&&objWbje.value!=""&&objWbje.value!="0") && (objBzdm.value == null||objBzdm.value =="")){
        	alert("����ҽ��Ѿ���д����ѡ������֡���");
        	objBzdm.focus();
        	return false;			
		}		
		
		//��ҽ��
		if(objWbje.value != null && objWbje.value  !=""){
			if(!checkNumber(objWbje.value,15,2,true))
        	{
            	alert("����ҽ�����Ϊ���֣�\nС�����ĳ������Ϊ2λ��\n������ֵ����ڵ����㣡");
            	objWbje.select();
            	return false;
        	}
		}
		
		//��ҽ��Ϊ�գ����Ǳ����Ѿ�ѡ������ʾҪ��д���
		if((objBzdm.value !=null&&objBzdm.value!="") && (objWbje.value == null||objWbje.value ==""||objWbje.value =="0"||objWbje.value =="0.00")){
        	alert("������֡��Ѿ�ѡ������д����ҽ���");
        	objWbje.select();
        	return false;			
		}
		
		//����
		if(objHl.value != null && objHl.value!=""&&objHl.value  !="0"){
			if(!checkNumber(objHl.value,15,6,true))
        	{
            	alert("�����ʡ�����Ϊ���֣�\nС�����ĳ������Ϊ6λ��\n������ֵ����ڵ����㣡");
            	objHl.select();
            	return false;
        	}
			
			//�����ҽ��Ϊ�գ�����ʾ��д��ҽ��
			if(objWbje.value == null||objWbje.value ==""||objWbje.value =="0"||objWbje.value =="0.00"){
	        	alert("�����ʡ��Ѿ���д������д����ҽ���");
	        	objWbje.select();
	        	return false;				
			}
		}
		//�������Ϊ�գ�������ҽ���Ѿ���д������ʾ��д����
		if((objWbje.value == null&&objWbje.value ==""&&objWbje.value =="0"&&objWbje.value =="0.00") && (objHl.value == null||objHl.value =="")){
        	alert("����ҽ��Ѿ���д������д�����ʡ���");
        	objHl.select();
        	return false;			
		}
		return true;
	}
	
	//����¥�����¥���ʽ�����Ƿ���ȷ
	function isSzlc_zlc(type){
		if(window.event.keyCode!=13 && window.event.keyCode!=0 && obj==window.event.srcElement) return true;
		
		var szlc_showOBJ = document.all.szlc_show;//����¥��
	 	
 
		
		var zlc_showOBJ = document.all.zlc_show;//��¥��
		
		//alert("szlc_showOBJ ++"+szlc_showOBJ.value +"  zlc_showOBJ++"+zlc_showOBJ.value);
		
		//�ж�����¥��
		if((type == "all" || type == "szlc") && szlc_showOBJ.value == ""){
			alert("����¥��Ϊ���������Ϊ��");
			szlc_showOBJ.focus();
			document.forms[0].szlc.value="";
			return false;
		}
		
		//��¥��
		if((type == "all" || type == "zlc") && zlc_showOBJ.value == ""){
			alert("��¥��Ϊ���������Ϊ��");
			zlc_showOBJ.focus();
			document.forms[0].szlc.value="";
			return false;			
		}
		
		var obj ="";// obj = ����¥�� +"/"+��¥��
		if( szlc_showOBJ.value != "" && zlc_showOBJ.value != ""){
			obj = szlc_showOBJ.value + "/" + zlc_showOBJ.value;
		}
		 //�Д�ݔ��ǌ��Ƿ����Ҫ��
		if(type == "szlc"){
			var reg= /^((-{0,1}[0-9])|(-{0,1}[0-9]+-{0,2}[0-9]+))$/; 
	  	 	if (!reg.test(document.getElementById("szlc_show").value)) 
				{ 
	  	 		alert('����¥���ʽ����ȷ'); 
	  	 		return false;
	  	 		}
		}

		 
		
		//�ύ��̨
		document.forms[0].szlc.value = obj;
		
		//alert("obj++"+document.forms[0].szlc.value);

    return true;
    
		
	}
	
  /*
	* ���淵�ػ��߲�ѯ֮����ʾ����¥�����¥��
	*/
	function setSZLC_ZLC(){
	  	var objStr = document.forms[0].szlc;
	  	
	  	//Ϊ��
	  	if(objStr.value == ""){
	  		return false;
	  	}
	  	
	  	//û��б�߷ָ/��
		var index = objStr.value.indexOf("/");
		if(index ==-1){
			return  false;
		}
		
		//������� != 2
		var szlcArr = objStr.value.split('/');
		if(szlcArr.length!=2){
			return  false;		
		}	
		
		//��ʾ
		document.all.szlc_show.value=szlcArr[0];//����¥��
		document.all.zlc_show.value=szlcArr[1];//��¥��		
		
		return true;
		
	}
	
	
	
	
	
	
	//function  : ���������ַ����Ƿ����ת��ΪDate����
	//Parameters: strData:String
	//return    : true: ͨ�����
//				  false: δͨ����顣
	function isDate(obj,empty,inputName)
	{
	  if(window.event.keyCode!=13 && window.event.keyCode!=0 && obj==window.event.srcElement) return true;

		var strDate=obj.value;
		var succeed=true;
		var alertStr="";

		if(strDate.length==0){
			  if(empty!=null){
					alertStr+="����ֵ���벻Ϊ��!\n"
					succeed=false;
				}

			}
		if (strDate.length!=8 && succeed) {
					alertStr+="���ڸ�ʽ����ȷ!\n"
					succeed=false;
		}
		var strYear = strDate.substr(0,4);
		var strMonth = strDate.substr(4,2);
	  var strDay = strDate.substr(6,2);
	  var objDate = new Date(strMonth+'-'+strDay+'-'+strYear);
		if (isNaN(objDate) && succeed){
					alertStr+="���ڸ�ʽ����ȷ!\n"
					succeed=false;
		}
	    if(strYear*1<1900 && succeed) {
					alertStr+="���ڸ�ʽ����ȷ!\n"
					succeed=false;
		}
	    if ((strYear*1 != objDate.getYear()*1)&&(strYear*1 != objDate.getYear()*1+1900) && succeed) {
					alertStr+="���ڸ�ʽ����ȷ!\n"
					succeed=false;
		}
	    if (strMonth*1 != objDate.getMonth()*1+1 && succeed){
					alertStr+="���ڸ�ʽ����ȷ!\n"
					succeed=false;
		}
	    if (strDay*1 != objDate.getDate()*1 && succeed) {
					alertStr+="���ڸ�ʽ����ȷ!\n"
					succeed=false;
		} //don't call getDay function.
		if(alertStr!="") {
				alert(inputName+alertStr+"��ʽӦΪyyyyMMdd(��20130101)");
				window.event.returnValue=false;
				obj.focus();
				obj.select();
			}

	    return succeed;
			}
	
	  
	  
	  
	  function checkSubmit(operationType){
		
		  //�ύ��
		  doSubmitForm(operationType);
	  }
	  
	  
	  function doSubmitForm(operationType){
			var truthBeTold;
			switch (operationType)
			{
			case 'Save':
				truthBeTold = false;
				break;
			case 'Update':
				truthBeTold = false;
				break;				
			case 'Delete':
				truthBeTold = false;
				break;					
			case 'Query':
				truthBeTold = true;
				break;				
			case 'ToPrint':
				truthBeTold = true;
				break;			
			case 'ToSellerQSZS':
				truthBeTold = sureTurn("goTo");
				break;	
			case 'ToFPDK':
				truthBeTold = sureTurn("goTo");
				break;
			case 'ToQSSB':
				truthBeTold = sureTurn("goTo");
				break;
			case 'ToSGCJ':
				truthBeTold = sureTurn("goTo");
				break;	
			case 'Show':
				truthBeTold = sureTurn("goTo");
				break;					
			case 'reSet':
				truthBeTold = true;
				break;				
			case 'Return':
					truthBeTold = sureTurn("return");;
				break;
			default:
				break;
			}

			
			if(operationType=="Update"){
				if(!canModify()){
					return false;
				}			
			}
			
			
			if(operationType=="Save"|| operationType=="Update"){
				//�������˫����Ϣ
				var isSucc = getMMFXXContext('sellTab');
				if(isSucc  == false){
					return false;
				}
				
				isSucc = getMMFXXContext('buyTab');
				
				if(isSucc  == false){
					return false;
				}				
				
				if(doCheckNotNullItems()){
					if(window.confirm("�ò������޸����ݿ��е�����,�Ҳ����Զ��ָ�,��ȷ��")){
						truthBeTold = true;
					}
				}
			}
			
			if(operationType=="Delete"){
				if(!canModify()){
					return false;
				}
				if(window.confirm("�ò�������ϵͳ��ɾ����ǰ�ɼ���Ϣ,�Ҳ����Զ��ָ�,��ȷ��")){
					truthBeTold = true;
				}				
			}
			
			if(operationType=="Query"){
				var htbh = document.all.htbh;
				if(htbh.value == null || htbh.value == ""){
					alert("����д����Ժ����֤�飩��š����ά��ɨ��ʱ�ġ���ͬ��š����в�ѯ��");
					htbh.focus();
					return false;
				}
				
				document.all.sbbh.value="";
				
			}
			
			if(operationType=="reSet"){
				if(!window.confirm("�ò��������ҳ��������Ϣ����ȷ�ϣ�")){
					return false;
				}
				operationType ="ToSGCJ";
			}
			
			
			
			
			if(!truthBeTold){
				return false;
			}
			
			document.forms[0].target="";
			document.all.operationType.value=operationType;
			document.all.xxly.value="02";//�ֹ��ɼ�
			document.forms[0].submit();
		}
	  
	  function canModify(){
			//��Ϣ��Դ
			var xxlyVal = document.all.xxly.value;
			//�Ѿ��˶�
			var hasHdxxVal = document.all.hasHdxx.value;
			
			//alert(xxlyVal.length+"::"+hasHdxxVal);
			
			if(xxlyVal == "01"){
				alert("��ά��ɨ�����ݣ�����ɾ�����޸ģ�");
				return false;
				
			}
			
			
			if(hasHdxxVal == "Y"){
				alert("����Ϣ�Ѿ��˶�������ɾ���˶���Ϣ������ɾ�����޸ģ�");
				return false;
			}			
			
		  
		  return true;
	  }
	  
	  //ȷ����ת�����˳�
	  	function sureTurn(type){
		  	var msg ="";
	  		var goToMsg ="��ת������ҳ��";
	  		var returnMsg ="�˳���ǰҳ��";
	  		
	  		if(type =="goTo"){
	  			msg = goToMsg;
	  		}else if (type == "return"){
	  			msg = returnMsg;
	  		}
	  		
	  		//���������δ��������ʾ
			if(saveIsSuccess!="1"){
				return window.confirm("��д��Ϣ��δ���棬�Ƿ�Ҫ"+msg+",��ȷ��");
			}else{
				 return true;
			}
	  	}

	    </script> 
</HTML>
