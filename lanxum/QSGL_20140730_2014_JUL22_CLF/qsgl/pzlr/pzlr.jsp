<%@page contentType="text/html; charset=GBK"%>
<%@include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<HTML>
<HEAD>
<TITLE>�����������¼��</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<script language="javascript" src="./js/qscommon.js">
</script>
<script language="javascript" src="<%=static_file%>js/inputchecker.js"></script>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js"
	type=text/JavaScript>
</SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
<META content="MSHTML 5.00.2919.6307" name=GENERATOR>
</HEAD>
<SCRIPT language=JavaScript>

</SCRIPT>
<!----��ҳ���ͷ�ļ��ͱ�ҳ������·��----->
<BODY bgColor=#ffffff leftMargin=0>
	<jsp:include page="/include/Header.jsp" flush="true">
		<jsp:param name="subtitle" value="��������>����¼��" />
		<jsp:param name="helpURL" value="" />
	</jsp:include>
	<SCRIPT language=javascript>
//��ť�ύ����js
function checkSubmit(operationType){
    document.forms[0].operationType.value=operationType;
    if(operationType=="GetNsrxx" ){
        if(document.forms[0].jsjdm.value=="" || !checkJsjdm(document.forms[0].jsjdm.value)){
            alert("������������벻�Ϸ���");
            return false;
        }
    }else if(operationType=="QueryFwxx" ){
        if(document.forms[0].fdcxmmc.value==""){
            alert("�����뷿�ز���Ŀ���ƣ�");
            return false;
        }
        if(document.forms[0].nsrmc.value == ""){
            alert("��˰�����Ʋ���Ϊ�գ����ȡ�Ǽ���Ϣ��");
            return false;
        }
    }else if(operationType=="Save" ){
        if (document.forms[0].jsjdm.value == "")
  	{
  	   alert("�������������룡");
  	   document.forms[0].jsjdm.focus();
  	   return false;
  	}
    if (document.forms[0].nsrmc.value == "")
  	{
  	   alert("��˰�����Ʋ���Ϊ�գ����ȡ�Ǽ���Ϣ��");
  	   document.all.imgGetNsrxx.focus();
  	   return false;
  	}
     if (document.forms[0].fdcxmmc.value == "")
  	{
  	   alert("�����뷿�ز���Ŀ���ƣ�");
  	   document.forms[0].fdcxmmc.focus();
  	   return false;
  	}
     
    if (document.forms[0].jzmj.value == "")
  	{
  	   alert("�����뽨�������");
  	   //document.forms[0].jzmj.focus();
  	   return false;
  	}else if(!FN_QSCheckNumberDigit(document.forms[0].jzmj.value,3,"�������")){
        document.forms[0].jzmj.focus();
        return false;
    }
    

    if (document.forms[0].qsrq.value == "" || !checkDate(document.forms[0].qsrq.value))
  	{
  	   alert("ʹ�����޿�ʼʱ�����벻�Ϸ���");
  	   document.forms[0].qsrq.focus();
  	   return false;
  	}
     if (document.forms[0].jzrq.value == "" || !checkDate(document.forms[0].jzrq.value))
  	{
  	   alert("ʹ�����޽�ֹʱ�����벻�Ϸ���");
  	   document.forms[0].jzrq.focus();
  	   return false;
  	}
     if (document.forms[0].tdjb.value == "00" )
  	{
  	   alert("��ѡ����������");
  	   document.forms[0].tdjb.focus();
  	   return false;
  	}
     
     
     if (document.forms[0].pjjyjg.value == "")
   	{
   	   alert("������ƽ�����׼۸�");
   	   //document.forms[0].pjjyjg.focus();
   	   return false;
   	}else if(!FN_QSCheckNumberDigit(document.forms[0].pjjyjg.value,2,"���׼۸�")){
         document.forms[0].pjjyjg.focus();
         return false;
     }
     
     if (document.forms[0].fwmtjg.value == "")
    	{
    	   alert("�����뷿��ÿ�׼۸�");
    	   return false;
    	}else if(!FN_QSCheckNumberDigit(document.forms[0].fwmtjg.value,2,"ÿ�׼۸�")){
          document.forms[0].fwmtjg.focus();
          return false;
      }
     
     
    }else if(operationType == "Delete"){
        confirm("ȷ��Ҫɾ����");

    }

    document.forms[0].submit();
}

function changeTdjb(){
	var   objSelect   =  document.forms[0].tdjb;
	var   objPjjyjg   =  document.forms[0].pjjyjg;
	var   objFwmtjg   =  document.forms[0].fwmtjg;
	switch(objSelect.value){
	case 00:
		objPjjyjg.value="";
		break;
    /* 
	case '11':
		objPjjyjg.value="38880.00";
		break;
	case '12':
		objPjjyjg.value="34560.00";
		break;
	case '13':
		objPjjyjg.value="32400.00";
		break;
	case '14':
		objPjjyjg.value="28080.00";
		break;
	case '15':
		objPjjyjg.value="25920.00";
		break;
	case '16':
		objPjjyjg.value="21600.00";
		break;
	case '17':
		objPjjyjg.value="17280.00";
		break;
	 */
	case '21':
		objPjjyjg.value="39600.00";
		objFwmtjg.value="468.00";
		break;
	case '22':
		objPjjyjg.value="31680.00";
		objFwmtjg.value="374.40";
		break;
	case '23':
		objPjjyjg.value="23760.00";
		objFwmtjg.value="280.80";
		break;
	default :
		objPjjyjg.value="";
	}
	
}

</SCRIPT>
	<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%"
		width=770>
		<TBODY>
			<TR>
				<TD vAlign=top>
					<TABLE align=center cellSpacing=0 class=table-99>
						<TBODY>
							<TR>
								<TD class="1-td1">�����������¼��</TD>
							</TR>
							<TR>
								<TD class="1-td2" vAlign=top><html:form action="pzlr.do">
										<html:hidden property="operationType" />
										<html:hidden property="id" />

										<TABLE align=center cellSpacing=0 class=table-99 width="98%">
											<TBODY>
												<br />
												<TABLE align=center cellSpacing=0 class=table-99 width="98%">
													<TBODY>
														<tr>
															<TD class=2-td2-t-left width="15%">
																<DIV align=right>��������� &nbsp;</DIV>
															</TD>
															<TD class=2-td2-t-center width="25%">
																<DIV align=left>
																	<html:text property="jsjdm" size="10" maxlength="8" />

																	<span id="getDJimage1" style="visibility: visible">
																		<input type="image" name="imgGetNsrxx" value="��ȡ�Ǽ���Ϣ"
																		alt="��ȡ�Ǽ���Ϣ"
																		onClick="javascript:return checkSubmit('GetNsrxx');"
																		onMouseOver="MM_swapImage('b-hqdjxx1','','<%=static_file%>images/b-hqdjxx2.jpg',1)"
																		onMouseOut="MM_swapImgRestore()"
																		src="<%=static_file%>images/b-hqdjxx1.jpg" width="79"
																		height="22" id="b-hqdjxx1">
																	</span>
																</DIV>
															</TD>
														</TR>
														<TR>
															<TD class=2-td2-left width="19%">
																<DIV align=right>��˰������ &nbsp;</DIV>
															</TD>
															<TD colspan="4" class=2-td2-center width="33%">
																<DIV align=left>
																	<bean:write name="pzlrForm" property="nsrmc" />
																	&nbsp;
																	<html:hidden property="nsrmc" />
																</DIV>
															</TD>
														</TR>
														<TR>
															<TD class=2-td2-left width="15%">
																<DIV align=right>˰�������֯���� &nbsp;</DIV>
															</TD>
															<TD class=2-td2-center width="85%" colspan="4">
																<DIV align=left>
																	<bean:write name="pzlrForm" property="zzjgmc" />
																	&nbsp;

																</DIV>
															</TD>
														</TR>
													</tbody>
												</table>
												<br />
												<TABLE align=center cellSpacing=0 class=table-99 width="98%">
													<TBODY>
														<TR>
															<TD class=2-td2-t-left width="4%">
																<DIV align=right>���ز���Ŀ���� &nbsp;</DIV>
															</TD>
															<TD colspan="6" class=2-td2-t-center>
																<DIV align=left>
																	<html:text property="fdcxmmc" size="60" maxlength="100" />

																	<IMG name="query"
																		onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
																		onMouseOut="MM_swapImgRestore()"
																		src="<%=static_file%>images/chaxun1.jpg"
																		onclick="checkSubmit('QueryFwxx');" width="79"
																		height="22" id="chaxun1" alt="��ѯ" style="cursor: hand">
																</DIV>
															</TD>
														</TR>
														<!--�޸���ʾЧ����20081215beigin-->
														<TR>
															<td class=2-td2-left rowspan="3" width="4%">
																<DIV align=right>��ͨסլ</DIV>
																<DIV align=right>��Ϣ</DIV>
															<td class=2-td2-left rowspan="2" width="2%">
																<DIV align=right>�������&nbsp;</DIV>
															</td>
															<TD class=2-td2-left rowspan="2" nowrap="nowrap">
																<DIV align=left>
																	<html:text property="jzmj" size="15" maxlength="14" />
																	�O��С�������λ��
																</DIV>
															</TD>
															<td class=2-td2-left nowrap="nowrap">
																<DIV align=right>������������ÿƽ�׼۸�����&nbsp;</DIV>
															</td>
															<TD colspan="2" class=2-td2-center>
																<DIV align=left>
																	<html:text property="pjjyjg" size="15" maxlength="14"
																		readonly="true" />
																	Ԫ������ң�С�������λ
																</DIV>
															</TD>
															
														</TR>
														<!--���ӷ���ÿ�׼۸����޸ķ�����������added by gaoyh to 20141016 beigin-->
														<tr>
															<td class=2-td2-left nowrap="nowrap">
																<DIV align=right>������������ÿ�׼۸�����&nbsp;</DIV>
															</td>
															<TD colspan="2" class=2-td2-center>
																<DIV align=left>
																	<html:text property="fwmtjg" size="15" maxlength="14"
																		readonly="true" />
																	��Ԫ������ң�С�������λ
																</DIV>
															</TD>
														</tr>
														<TR>
															<TD class=2-td2-left nowrap="nowrap">
																<DIV align=right>�������� &nbsp;</DIV>
															</TD>
															<TD class=2-td2-left>
																<DIV align=left>
																	<html:select property="tdjb" onchange="changeTdjb()">
																		<html:option value="00">��ѡ��</html:option>
																		<!-- 
																		<html:option value="11">�Ļ��ڱ�������</html:option>
																		<html:option value="12">�Ļ����ϲ�����</html:option>
																		<html:option value="13">�Ļ����廷��������</html:option>
																		<html:option value="14">�Ļ����廷�ϲ�����</html:option>
																		<html:option value="15">�廷��������������</html:option>
																		<html:option value="16">�廷�������ϲ�����</html:option>
																		<html:option value="17">���������</html:option>
																		 -->
																		<!-- 
																	    <html:option value="21">�廷��</html:option>
																		<html:option value="22">�廷������</html:option>
																		<html:option value="23">������</html:option>
																		 -->
																		<html:option value="21">5����</html:option>
																		<html:option value="22">5-6��</html:option>
																		<html:option value="23">6����</html:option>
																	</html:select>
															<!--���ӷ���ÿ�׼۸����޸ķ�����������added by gaoyh to 20141016 end-->
																	<!--         
                          
                              <bean:define id="data1" name="pzlrForm" property="tdjcList"/>
                                    <html:select property="tdjb">
                                      <html:options collection="data1" labelProperty="fwszqymc" property="fwszqydm"/>
                                    </html:select>
                   -->
																</DIV>
															</TD>
															<TD class=2-td2-left>
																<DIV align=right>�ݻ��� &nbsp;</DIV>
															</TD>
															<TD colspan="2" class=2-td2-center>
																<DIV align=left>
																	<html:select property="rjl">
																		<html:option value="01">1.0���Ϻ�1.0</html:option>
																		<html:option value="00">1.0����</html:option>
																	</html:select>
																</DIV>
															</TD>


														</TR>
														<!--�޸���ʾЧ����20081215beigin-->



														<TR>
															<TD class=2-td2-left>
																<DIV align=right>ʹ�������� &nbsp;</DIV>
															</TD>
															<TD class=2-td2-left>
																<DIV align=left>
																	<html:text property="qsrq" size="15" maxlength="8" />
																	(yyyymmdd)
																</DIV>
															</TD>
															<TD class=2-td2-left>
																<DIV align=right>ʹ������ֹ &nbsp;</DIV>
															</TD>
															<TD colspan="3" class=2-td2-center>
																<DIV align=left>
																	<html:text property="jzrq" size="15" maxlength="8" />
																	(yyyymmdd)
																</DIV>
															</TD>
														</TR>
														<TR>
															<TD class=2-td2-left>
																<DIV align=right>�Ƿ���Բ�����Ǩ��Ϣ &nbsp;</DIV>
															</TD>
															<TD colspan="5" class=2-td2-center>
																<DIV align=left>
																	<html:radio property="czcq" value="0" />
																	����

																	<html:radio property="czcq" value="1" />
																	������

																</DIV>
															</TD>
														</TR>
														<TR>
															<TD class=2-td2-left>
																<DIV align=right>�Ƿ���Բ����ѹ�����ס��������Ϣ &nbsp;</DIV>
															</TD>
															<TD colspan="5" class=2-td2-center width="15%">
																<DIV align=left>
																	<html:radio property="czcsxx" value="0" />
																	����

																	<html:radio property="czcsxx" value="1" />
																	������

																</DIV>
															</TD>
														</TR>
														<TR>
															<TD class=2-td2-left>
																<DIV align=right>�Ƿ�����ֹ�¼�뽻����Ϣ &nbsp;</DIV>
															</TD>
															<TD colspan="5" class=2-td2-center width="15%">
																<DIV align=left>
																	<html:radio property="czfwjyxx" value="0" />
																	����

																	<html:radio property="czfwjyxx" value="1" />
																	������

																</DIV>
															</TD>
														</TR>
													</TBODY>
												</TABLE>
												<BR>
												<TABLE border=0 cellSpacing=0 class=tabled-99 width="98%">
													<TBODY>
														<BR>
														<TR>
															<TD class=2-td2-t-left>������Ա</TD>
															<TD class=2-td2-t-center>
																<DIV align=left>
																	&nbsp;&nbsp;
																	<bean:write name="pzlrForm" property="czry" />
																</DIV>
															</TD>
															<TD class=2-td2-t-left>¼������</TD>
															<TD class=2-td2-t-center>
																<DIV align=left>
																	&nbsp;&nbsp;
																	<bean:write name="pzlrForm" property="xtdqsj" />
																</DIV>
															</TD>
															<TD class=2-td2-t-left>����˰�����</TD>
															<TD class=2-td2-t-center>
																<DIV align=left>
																	&nbsp;&nbsp;
																	<bean:write name="pzlrForm" property="swjgzzjgmc" />

																</DIV>
															</TD>
														</TR>
													</TBODY>
												</TABLE>
												<BR>
												<DIV align=center>
													<IMG alt=���� height=22 id=baocun name=Submit63
														onclick="checkSubmit('Save')"
														onmouseout=MM_swapImgRestore()
														onmouseover="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
														src="<%=static_file%>images/baocun1.jpg"
														style="CURSOR: hand" width=79>
													<logic:equal name="pzlrForm" property="del_flag"
														value="true">

														<IMG alt=ɾ�� height=22 id=shanchu name=Submit64
															onclick="checkSubmit('Delete')"
															onmouseout=MM_swapImgRestore()
															onmouseover="MM_swapImage('shanchu','','<%=static_file%>images/shanchu2.jpg',1)"
															src="<%=static_file%>images/shanchu1.jpg"
															style="CURSOR: hand" width=79>

														<IMG alt=���� height=22 id=shengcheng name=Submit65
															onclick="checkSubmit('MakeXML')"
															onmouseout=MM_swapImgRestore()
															onmouseover="MM_swapImage('shengcheng','','<%=static_file%>images/b-scpzwj2.jpg',1)"
															src="<%=static_file%>images/b-scpzwj1.jpg"
															style="CURSOR: hand" width=79>
													</logic:equal>
													<IMG alt=�˳� height=22 id=tuichu name=tuichu
														onclick="checkSubmit('Return');"
														onmouseout=MM_swapImgRestore()
														onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
														src="<%=static_file%>images/tuichu1.jpg"
														style="CURSOR: hand" width=79>
												</DIV>
												</html:form>
												<%@include file="../include/Bottom.jsp"%>
</BODY>
</HTML>
<script language=JavaScript type='text/JavaScript'>
    <%-- %>fnConvertWb(document.all.cjjgywb,document.all.hn,document.all.zhyrmb);<% --%>
    document.forms[0].pjjyjg.style.color="#ADADAD";
    document.forms[0].fwmtjg.style.color="#ADADAD";
</script>
