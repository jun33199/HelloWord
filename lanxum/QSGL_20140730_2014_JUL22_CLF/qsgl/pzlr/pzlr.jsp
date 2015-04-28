<%@page contentType="text/html; charset=GBK"%>
<%@include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<HTML>
<HEAD>
<TITLE>批量软件配置录入</TITLE>
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
<!----本页面的头文件和本页帮助的路径----->
<BODY bgColor=#ffffff leftMargin=0>
	<jsp:include page="/include/Header.jsp" flush="true">
		<jsp:param name="subtitle" value="批量受理>配置录入" />
		<jsp:param name="helpURL" value="" />
	</jsp:include>
	<SCRIPT language=javascript>
//按钮提交出发js
function checkSubmit(operationType){
    document.forms[0].operationType.value=operationType;
    if(operationType=="GetNsrxx" ){
        if(document.forms[0].jsjdm.value=="" || !checkJsjdm(document.forms[0].jsjdm.value)){
            alert("计算机代码输入不合法！");
            return false;
        }
    }else if(operationType=="QueryFwxx" ){
        if(document.forms[0].fdcxmmc.value==""){
            alert("请输入房地产项目名称！");
            return false;
        }
        if(document.forms[0].nsrmc.value == ""){
            alert("纳税人名称不能为空，请获取登记信息！");
            return false;
        }
    }else if(operationType=="Save" ){
        if (document.forms[0].jsjdm.value == "")
  	{
  	   alert("请输入计算机代码！");
  	   document.forms[0].jsjdm.focus();
  	   return false;
  	}
    if (document.forms[0].nsrmc.value == "")
  	{
  	   alert("纳税人名称不能为空，请获取登记信息！");
  	   document.all.imgGetNsrxx.focus();
  	   return false;
  	}
     if (document.forms[0].fdcxmmc.value == "")
  	{
  	   alert("请输入房地产项目名称！");
  	   document.forms[0].fdcxmmc.focus();
  	   return false;
  	}
     
    if (document.forms[0].jzmj.value == "")
  	{
  	   alert("请输入建筑面积！");
  	   //document.forms[0].jzmj.focus();
  	   return false;
  	}else if(!FN_QSCheckNumberDigit(document.forms[0].jzmj.value,3,"建筑面积")){
        document.forms[0].jzmj.focus();
        return false;
    }
    

    if (document.forms[0].qsrq.value == "" || !checkDate(document.forms[0].qsrq.value))
  	{
  	   alert("使用期限开始时间输入不合法！");
  	   document.forms[0].qsrq.focus();
  	   return false;
  	}
     if (document.forms[0].jzrq.value == "" || !checkDate(document.forms[0].jzrq.value))
  	{
  	   alert("使用期限截止时间输入不合法！");
  	   document.forms[0].jzrq.focus();
  	   return false;
  	}
     if (document.forms[0].tdjb.value == "00" )
  	{
  	   alert("请选择所属区域！");
  	   document.forms[0].tdjb.focus();
  	   return false;
  	}
     
     
     if (document.forms[0].pjjyjg.value == "")
   	{
   	   alert("请输入平均交易价格！");
   	   //document.forms[0].pjjyjg.focus();
   	   return false;
   	}else if(!FN_QSCheckNumberDigit(document.forms[0].pjjyjg.value,2,"交易价格")){
         document.forms[0].pjjyjg.focus();
         return false;
     }
     
     if (document.forms[0].fwmtjg.value == "")
    	{
    	   alert("请输入房屋每套价格！");
    	   return false;
    	}else if(!FN_QSCheckNumberDigit(document.forms[0].fwmtjg.value,2,"每套价格")){
          document.forms[0].fwmtjg.focus();
          return false;
      }
     
     
    }else if(operationType == "Delete"){
        confirm("确定要删除？");

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
								<TD class="1-td1">批量软件配置录入</TD>
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
																<DIV align=right>计算机代码 &nbsp;</DIV>
															</TD>
															<TD class=2-td2-t-center width="25%">
																<DIV align=left>
																	<html:text property="jsjdm" size="10" maxlength="8" />

																	<span id="getDJimage1" style="visibility: visible">
																		<input type="image" name="imgGetNsrxx" value="获取登记信息"
																		alt="获取登记信息"
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
																<DIV align=right>纳税人名称 &nbsp;</DIV>
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
																<DIV align=right>税务机关组织机构 &nbsp;</DIV>
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
																<DIV align=right>房地产项目名称 &nbsp;</DIV>
															</TD>
															<TD colspan="6" class=2-td2-t-center>
																<DIV align=left>
																	<html:text property="fdcxmmc" size="60" maxlength="100" />

																	<IMG name="query"
																		onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
																		onMouseOut="MM_swapImgRestore()"
																		src="<%=static_file%>images/chaxun1.jpg"
																		onclick="checkSubmit('QueryFwxx');" width="79"
																		height="22" id="chaxun1" alt="查询" style="cursor: hand">
																</DIV>
															</TD>
														</TR>
														<!--修改显示效果。20081215beigin-->
														<TR>
															<td class=2-td2-left rowspan="3" width="4%">
																<DIV align=right>普通住宅</DIV>
																<DIV align=right>信息</DIV>
															<td class=2-td2-left rowspan="2" width="2%">
																<DIV align=right>建筑面积&nbsp;</DIV>
															</td>
															<TD class=2-td2-left rowspan="2" nowrap="nowrap">
																<DIV align=left>
																	<html:text property="jzmj" size="15" maxlength="14" />
																	㎡（小数点后三位）
																</DIV>
															</TD>
															<td class=2-td2-left nowrap="nowrap">
																<DIV align=right>房屋所在区域每平米价格上限&nbsp;</DIV>
															</td>
															<TD colspan="2" class=2-td2-center>
																<DIV align=left>
																	<html:text property="pjjyjg" size="15" maxlength="14"
																		readonly="true" />
																	元（人民币）小数点后两位
																</DIV>
															</TD>
															
														</TR>
														<!--增加房屋每套价格与修改房屋所在区域。added by gaoyh to 20141016 beigin-->
														<tr>
															<td class=2-td2-left nowrap="nowrap">
																<DIV align=right>房屋所在区域每套价格上限&nbsp;</DIV>
															</td>
															<TD colspan="2" class=2-td2-center>
																<DIV align=left>
																	<html:text property="fwmtjg" size="15" maxlength="14"
																		readonly="true" />
																	万元（人民币）小数点后两位
																</DIV>
															</TD>
														</tr>
														<TR>
															<TD class=2-td2-left nowrap="nowrap">
																<DIV align=right>所在区域 &nbsp;</DIV>
															</TD>
															<TD class=2-td2-left>
																<DIV align=left>
																	<html:select property="tdjb" onchange="changeTdjb()">
																		<html:option value="00">请选择</html:option>
																		<!-- 
																		<html:option value="11">四环内北部地区</html:option>
																		<html:option value="12">四环内南部地区</html:option>
																		<html:option value="13">四环至五环北部地区</html:option>
																		<html:option value="14">四环至五环南部地区</html:option>
																		<html:option value="15">五环至六环北部地区</html:option>
																		<html:option value="16">五环至六环南部地区</html:option>
																		<html:option value="17">六环外地区</html:option>
																		 -->
																		<!-- 
																	    <html:option value="21">五环内</html:option>
																		<html:option value="22">五环至六环</html:option>
																		<html:option value="23">六环外</html:option>
																		 -->
																		<html:option value="21">5环内</html:option>
																		<html:option value="22">5-6环</html:option>
																		<html:option value="23">6环外</html:option>
																	</html:select>
															<!--增加房屋每套价格与修改房屋所在区域。added by gaoyh to 20141016 end-->
																	<!--         
                          
                              <bean:define id="data1" name="pzlrForm" property="tdjcList"/>
                                    <html:select property="tdjb">
                                      <html:options collection="data1" labelProperty="fwszqymc" property="fwszqydm"/>
                                    </html:select>
                   -->
																</DIV>
															</TD>
															<TD class=2-td2-left>
																<DIV align=right>容积率 &nbsp;</DIV>
															</TD>
															<TD colspan="2" class=2-td2-center>
																<DIV align=left>
																	<html:select property="rjl">
																		<html:option value="01">1.0以上含1.0</html:option>
																		<html:option value="00">1.0以下</html:option>
																	</html:select>
																</DIV>
															</TD>


														</TR>
														<!--修改显示效果。20081215beigin-->



														<TR>
															<TD class=2-td2-left>
																<DIV align=right>使用期限起 &nbsp;</DIV>
															</TD>
															<TD class=2-td2-left>
																<DIV align=left>
																	<html:text property="qsrq" size="15" maxlength="8" />
																	(yyyymmdd)
																</DIV>
															</TD>
															<TD class=2-td2-left>
																<DIV align=right>使用期限止 &nbsp;</DIV>
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
																<DIV align=right>是否可以操作拆迁信息 &nbsp;</DIV>
															</TD>
															<TD colspan="5" class=2-td2-center>
																<DIV align=left>
																	<html:radio property="czcq" value="0" />
																	可以

																	<html:radio property="czcq" value="1" />
																	不可以

																</DIV>
															</TD>
														</TR>
														<TR>
															<TD class=2-td2-left>
																<DIV align=right>是否可以操作已购公有住房出售信息 &nbsp;</DIV>
															</TD>
															<TD colspan="5" class=2-td2-center width="15%">
																<DIV align=left>
																	<html:radio property="czcsxx" value="0" />
																	可以

																	<html:radio property="czcsxx" value="1" />
																	不可以

																</DIV>
															</TD>
														</TR>
														<TR>
															<TD class=2-td2-left>
																<DIV align=right>是否可以手工录入交易信息 &nbsp;</DIV>
															</TD>
															<TD colspan="5" class=2-td2-center width="15%">
																<DIV align=left>
																	<html:radio property="czfwjyxx" value="0" />
																	可以

																	<html:radio property="czfwjyxx" value="1" />
																	不可以

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
															<TD class=2-td2-t-left>操作人员</TD>
															<TD class=2-td2-t-center>
																<DIV align=left>
																	&nbsp;&nbsp;
																	<bean:write name="pzlrForm" property="czry" />
																</DIV>
															</TD>
															<TD class=2-td2-t-left>录入日期</TD>
															<TD class=2-td2-t-center>
																<DIV align=left>
																	&nbsp;&nbsp;
																	<bean:write name="pzlrForm" property="xtdqsj" />
																</DIV>
															</TD>
															<TD class=2-td2-t-left>所属税务机关</TD>
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
													<IMG alt=保存 height=22 id=baocun name=Submit63
														onclick="checkSubmit('Save')"
														onmouseout=MM_swapImgRestore()
														onmouseover="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
														src="<%=static_file%>images/baocun1.jpg"
														style="CURSOR: hand" width=79>
													<logic:equal name="pzlrForm" property="del_flag"
														value="true">

														<IMG alt=删除 height=22 id=shanchu name=Submit64
															onclick="checkSubmit('Delete')"
															onmouseout=MM_swapImgRestore()
															onmouseover="MM_swapImage('shanchu','','<%=static_file%>images/shanchu2.jpg',1)"
															src="<%=static_file%>images/shanchu1.jpg"
															style="CURSOR: hand" width=79>

														<IMG alt=生成 height=22 id=shengcheng name=Submit65
															onclick="checkSubmit('MakeXML')"
															onmouseout=MM_swapImgRestore()
															onmouseover="MM_swapImage('shengcheng','','<%=static_file%>images/b-scpzwj2.jpg',1)"
															src="<%=static_file%>images/b-scpzwj1.jpg"
															style="CURSOR: hand" width=79>
													</logic:equal>
													<IMG alt=退出 height=22 id=tuichu name=tuichu
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
