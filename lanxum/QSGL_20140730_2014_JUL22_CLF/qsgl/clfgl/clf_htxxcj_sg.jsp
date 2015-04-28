<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.ttsoft.common.util.SessionKey"%>
<HTML>
	<HEAD>
		<TITLE>存量房信息采集</TITLE>
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
			<jsp:param name="subtitle" value="存量房管理>存量房信息采集"/>
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
								采集方式选择：二维码扫描<html:radio name="clfxxcjForm" property="cjfsdm" value="01" onclick="changeCjfs(this);"></html:radio> 手工采集<html:radio name="clfxxcjForm" property="cjfsdm" value="02"></html:radio>
							</div>							
						存量房买卖合同信息表</td>
					</tr>
				    <tr><td class=1-td2>
					<html:form action="/clfgl/clfxxcj.do" type="POST">
					<html:hidden name="clfxxcjForm" property="operationType"/>
					<html:hidden name="clfxxcjForm" property="hasHdxx"/><!-- 是否有核定信息 -->
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
										<td colspan="7" align="center" class=2-td1-center>一、交易房屋信息</td>
									</tr>
									<tr>
										<td class=2-td2-left align="left" width="20%" nowrap="nowrap">
										<logic:notEqual name="clfxxcjForm"  property="xxly" value="01">
										法院（公证书）编号
										</logic:notEqual>
										<logic:equal name="clfxxcjForm"  property="xxly" value="01">
										合同编号
										</logic:equal>										
										<font color="red">*</font></td>
										<td class=2-td2-left width="15%" nowrap="nowrap">
											<div align="left">
												<html:text name="clfxxcjForm" property="htbh" size="45"/>
												<IMG alt=查询
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
										法院（公证书）日期
										</logic:notEqual>
										<logic:equal name="clfxxcjForm"  property="xxly" value="01">
										网上签约日期
										</logic:equal>										
										
										<font color="red">*</font></td>
										<td class=2-td2-center width="45%" colspan="3">
											<div align="left">
												<html:text name="clfxxcjForm" property="htwsqyrq" onchange="isDate(this,false,'“法院（公证书）日期”');" maxlength='8'/>日期格式为:yyyyMMdd
											</div>
										</td>																				
									</tr>
									
									<logic:equal name="clfxxcjForm"  property="xxly" value="01">
										<tr>
											<td class=2-td2-left align="left">房屋权属转移类型<font color="red">*</font></td>
											<td class=2-td2-center colspan="5"><div align="left">房屋买卖</div></td>									
										</tr>
									</logic:equal>
										
								<logic:notEqual name="clfxxcjForm"  property="xxly" value="01">
									<tr>
										<td class=2-td2-left align="left">房屋权属转移类型<font color="red">*</font></td>
										<td class=2-td2-left><div align="left">
											<html:select  name="clfxxcjForm" property="fwqszylx" style='width:37%'>
												<html:option value="05">房屋交换</html:option>
												<html:option value="04">房屋赠与</html:option>
												<html:option value="06">法院判定</html:option>
												<html:option value="03">房屋买卖</html:option>
											</html:select></div></td>	
																						
										<td class=2-td2-left align="left">房屋性质</td>
										<td class=2-td2-center class=2-td2-center colspan="3">
						                	<div align="left"><bean:define id="dto" name="clfxxcjForm" property="codeList_fwxz"/>
						                  		<html:select name="clfxxcjForm" property="fwxzdm" style='width:31%' >
						                    	<html:options collection="dto" labelProperty="fwxzmc" property="fwxzdm"/>
						                  </html:select></div>											
										</td>											
									</tr>
									
									<tr>
										<td class=2-td2-left align="left">权属转移房屋地址区县<font color="red">*</font></td>
										<td class=2-td2-left>
											<div align="left">
													<%-- <html:text name="clfxxcjForm" property="fwzlqx"/> --%>
													<html:select name="clfxxcjForm" property="fwzlqx" style='width:37%'>
														<html:option value="2">东城区</html:option>
														<html:option value="3">西城区</html:option>
														<html:option value="4">崇文区</html:option>
														<html:option value="5">宣武区</html:option>
														<html:option value="6">朝阳区</html:option>
														<html:option value="7">海淀区</html:option>
														<html:option value="8">丰台区</html:option>
														<html:option value="9">石景山区</html:option>
														<html:option value="10">通州区</html:option>
														<html:option value="11">房山区</html:option>
														<html:option value="12">顺义区</html:option>
														<html:option value="13">门头沟区</html:option>
														<html:option value="14">大兴区</html:option>
														<html:option value="15">怀柔区</html:option>
														<html:option value="16">密云县</html:option>
														<html:option value="17">昌平区</html:option>
														<html:option value="18">延庆县</html:option>
														<html:option value="19">平谷区</html:option>
													</html:select>											
											</div></td>	
											
											<td class=2-td2-center colspan="4">&nbsp;</td>
									</tr>
									</logic:notEqual>
									<tr>
										<td class=2-td2-left align="left">权属转移房屋详细地址（含区县）<font color="red">*</font></td>
										<td class=2-td2-center colspan="5">
											<div align="left">
													<html:text name="clfxxcjForm" property="fwzldz" maxlength="500" size="90.5%"/>
											</div></td>										
									</tr>
									<tr>
										<td class=2-td2-left align="left">房屋所有权证号<font color="red">*</font></td>
										<td class=2-td2-left>
											<div align="left">
													<html:text name="clfxxcjForm" property="fwcqzh" size="53%"/>
											</div></td>	
										<td class=2-td2-left align="left">房屋所有权证填发日期<font color="red">*</font></td>
										<td class=2-td2-center colspan="3">
										<div align="left">
											<html:text name="clfxxcjForm" property="fwsyqztfrq" onchange="isDate(this,false,'“房屋所有权证填发日期”');" maxlength='8'/>日期格式为:yyyyMMdd
										</div></td>										
									</tr>
									<tr>
										<td class=2-td2-left align="left">本次交易是否为首次上市已购公房<font color="red">*</font></td>
										<td class=2-td2-left>
											<div align="left">
												<html:select name="clfxxcjForm" property="sfwscsssggf" style='width:37%'>
													<html:option value="1">是</html:option>
													<html:option value="0">否</html:option>
												</html:select>
											</div>
										</td>									
										<td class=2-td2-left align="left">房屋建筑面积<font color="red">*</font></td>
										<td class=2-td2-center colspan="3">
										
											<div align="left">
												<html:text name="clfxxcjForm" property="fwjzmj"/>
											</div></td>
									</tr>

									<tr>
										<td class=2-td2-left align="left">建筑结构<font color="red">*</font></td>
										<td class=2-td2-left>
											<div align="left">
												<html:select name="clfxxcjForm" property="jzjgdm" style='width:37%'>
													<html:option value="1">钢结构</html:option>
													<html:option value="2">钢筋混凝土结构</html:option>
													<html:option value="6">钢、钢筋混凝土结构</html:option>																										
													<html:option value="3">混合结构</html:option>
													<html:option value="4">砖木结构</html:option>
													<html:option value="5">其他结构</html:option>
												</html:select>											 
											</div>
										</td>	
										<td class=2-td2-left align="left">房屋划分用途<font color="red">*</font></td>
										<td class=2-td2-center colspan="3">
											<div align="left">
											<logic:notEqual name="clfxxcjForm"  property="xxly" value="01">
													<html:select name="clfxxcjForm" property="ghyt" style='width:31%'>
														<html:option value="21">经济适用住房</html:option>
														<html:option value="1">普通住宅</html:option>
														<html:option value="3">别墅</html:option>																										
														<html:option value="2">公寓</html:option>
														<html:option value="4">商业</html:option>
														<html:option value="5">写字楼</html:option>
														<html:option value="6">工业厂房</html:option>
														<html:option value="7">车库</html:option>
														<html:option value="10">其它</html:option>
													</html:select>											
											</logic:notEqual>
											<logic:equal name="clfxxcjForm"  property="xxly" value="01">
												<bean:write name="clfxxcjForm" property="ghyt"/>&nbsp;										
											</logic:equal>											
											</div>
										</td>																		
									</tr>	
									
									<tr>
										<td class=2-td2-left align="left">所在楼层<font color="red">*</font></td>
										<td class=2-td2-left>
											<div align="left">
												<input id="szlc_show" onchange="isSzlc_zlc('szlc');"/>
												&nbsp;&nbsp;&nbsp;&nbsp;总层数<font color="red">*</font>		
												<input id="zlc_show" onchange="isSzlc_zlc('zlc');"/>								
											</div>
											
											</td>
										<td class=2-td2-left align="left">权属转移房屋总价<font color="red">*</font></td>
										<td class=2-td2-center colspan="3">
											<div align="left">
												<html:text name="clfxxcjForm" property="htzj"/>										
											</div>
										</td>																	
									</tr>
									<tr>
										<td class=2-td2-left align="left">外币种</td>
										<td class=2-td2-left>
						                	<div align="left"><bean:define id="bzdto" name="clfxxcjForm" property="codeList_bz"/>
						                  		<html:select property="bzdm" style='width:37%' >
						                    	<html:options collection="bzdto" labelProperty="bzmc" property="bzdm"/>
						                  </html:select></div>											
										</td>
										<td class=2-td2-left align="left">外币金额</td>
										<td class=2-td2-left width="15%">
										    <div align="left">
												<html:text name="clfxxcjForm" property="wbje"/>											
											</div></td>	
										<td class=2-td2-left align="left" width="5%">汇率</td>	
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
										<td colspan="7" class=2-td1-center>二、卖方信息</td>
									</tr>
   		                            <tr >
    		                            <td colspan="7" align="center" class="2-td2-center">
    		                            <img onClick="addLabelCol('labelsample');" alt="新增" style="cursor:hand" onMouseOver="MM_swapImage('add','','<%=static_file%>images/xinzeng2.jpg',1)" onMouseOut="MM_swapImgRestore()" id="add" src="<%=static_file%>images/xinzeng1.jpg" width="79" height="22"  >
    		                            <img onClick="removeLabelCol('sellTab');" alt="删除" style="cursor:hand" onMouseOver="MM_swapImage('del','','<%=static_file%>images/shanchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" id="del" src="<%=static_file%>images/shanchu1.jpg" width="79" height="22"  >
    		                                <!-- <input onclick="getMMFXXContext('sellTab');" type="button" value="【提交】" name="delButton"> -->
    		                            </td>
   		                            </tr>									
								</table>
								<br>
								<table  border=0 cellSpacing=0 class=tabled-99 width="100%" id="buyTab">
									<tr>
										<td colspan="7" class=2-td1-center>三、买方信息</td>
									</tr>	
   		                            <tr >
    		                            <td colspan="7" align="center" class="2-td2-center">
   		                                <img onClick="addLabelCol('labelsample');" alt="新增" style="cursor:hand" onMouseOver="MM_swapImage('add','','<%=static_file%>images/xinzeng2.jpg',1)" onMouseOut="MM_swapImgRestore()" id="add" src="<%=static_file%>images/xinzeng1.jpg" width="79" height="22"  >
    		                            <img onClick="removeLabelCol('buyTab');" alt="删除" style="cursor:hand" onMouseOver="MM_swapImage('del','','<%=static_file%>images/shanchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" id="del" src="<%=static_file%>images/shanchu1.jpg" width="79" height="22"  >
    		                                <!-- <input onclick="getMMFXXContext('buyTab');" type="button" value="【提交】" name="delButton"> -->
    		                            </td>
   		                            </tr>																																
								</table>
								<br>
								<table  border=0 cellSpacing=0 class=tabled-99 width="100%">
								   	<tr>
										<td colspan="4" class=2-td1-center>四、房地产中介机构信息</td>
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
											<IMG alt=保存 height=22 id=baocun name=baocun onclick="doSubmitForm('Save')" onmouseout=MM_swapImgRestore() onmouseover="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)" src="<%=static_file%>images/baocun1.jpg" style='CURSOR: hand' width=79>
										</logic:notEqual>
											<IMG alt=清除页面所填信息，重新录入   height=22 id='chongzhi' name='chongzhi' onclick="doSubmitForm('reSet')" onmouseout=MM_swapImgRestore() onmouseover="MM_swapImage('chongzhi','','<%=static_file%>images/chongzhi2.jpg',1)" src="<%=static_file%>images/chongzhi1.jpg" style='CURSOR: hand' width=79>		
										
										<logic:equal name="clfxxcjForm" property="saveIsSuccess" value="1">
											
											<logic:equal name="clfxxcjForm" property="hasMAuthorise" value="true">
													<IMG alt=删除该采集信息  height=22 id=shanchu name=shanchu onclick="doSubmitForm('Delete')" onmouseout=MM_swapImgRestore() onmouseover="MM_swapImage('shanchu','','<%=static_file%>images/shanchu2.jpg',1)" src="<%=static_file%>images/shanchu1.jpg" style='CURSOR: hand' width=79>
													<IMG alt=保存已修改信息  height=22 id=b-bcbg name=b-bcbg onclick="doSubmitForm('Update')" onmouseout=MM_swapImgRestore() onmouseover="MM_swapImage('b-bcbg','','<%=static_file%>images/b-bcbg2.jpg',1)" src="<%=static_file%>images/b-bcbg1.jpg" style='CURSOR: hand' width=79>
											</logic:equal>
											
											
											<IMG alt=下一步  height=22 id=xiayibu name=xiayibu  onclick="doSubmitForm('ToPrint')" onmouseout=MM_swapImgRestore() onmouseover="MM_swapImage('xiayibu','','<%=static_file%>images/xiayibu2.jpg',1)" src="<%=static_file%>images/xiayibu1.jpg" style='CURSOR: hand' width=79>
										</logic:equal>
										
											<%-- <IMG alt=转卖方税款征收  height=22 id=mfskzs1 name=mfskzs1 onclick="doSubmitForm('ToSellerQSZS')" onmouseout=MM_swapImgRestore() onmouseover="MM_swapImage('mfskzs1','','<%=static_file%>images/mfskzs2.jpg',1)" src="<%=static_file%>images/mfskzs1.jpg" style='CURSOR: hand' width=130>
											<IMG alt=转发票代开  height=22 id=fpdk1 name=fpdk1 onclick="doSubmitForm('ToFPDK')" onmouseout=MM_swapImgRestore() onmouseover="MM_swapImage('fpdk1','','<%=static_file%>images/fpdk2.jpg',1)" src="<%=static_file%>images/fpdk1.jpg" style='CURSOR: hand' width=100>
											<IMG alt=转契税申报  height=22 id=qssb1 name=qssb1 onclick="doSubmitForm('ToQSSB')" onmouseout=MM_swapImgRestore() onmouseover="MM_swapImage('qssb1','','<%=static_file%>images/qssb2.jpg',1)" src="<%=static_file%>images/qssb1.jpg" style='CURSOR: hand' width=100> --%>
											<IMG alt=退出  height=22 id=tuichu1 name=tuichu1 onclick="doSubmitForm('Return')" onmouseout=MM_swapImgRestore() onmouseover="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)" src="<%=static_file%>images/tuichu1.jpg" style='CURSOR: hand' width=79>
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
				<td class=2-td2-left  width="20%">名称（姓名）</td>
				<td class=2-td2-left width="20%">类别</td>								
				<td class=2-td2-left>证件类型</td>
				<td class=2-td2-left>证件号码</td>								
				<td class=2-td2-left>权利人份额</td>
				<td class=2-td2-left>联系人电话</td>
				<td class=2-td2-center>选择</td>
   		    </tr>
   		    			
   		    <tr id="labelsample" height="24">
				<td class=2-td2-left  width="20%"><input id="nsr_mc" type="text"></td>
				<td class=2-td2-left width="20%">
					<select id="nsr_lb">
						<option value="1">个人</option>
						<option value="0">非个人</option>
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

	    //此函数是保存完成之后用于显示已保存信息的函数
	    function showSaveData(){
 	    	//卖方信息
	    	var sellerInfo = '<bean:write name="clfxxcjForm" property="all_sellerInfo"/>';
	    	parseSaveBuyorSellInfo(sellerInfo,"sellTab",document.forms[0].all_sellerInfo);
	    	
	    	//买方信息
	    	var buyyerInfo = '<bean:write name="clfxxcjForm" property="all_buyerInfo"/>';
			parseSaveBuyorSellInfo(buyyerInfo,"buyTab",document.forms[0].all_buyerInfo);
			
			//显示所在楼层和总楼层
			setSZLC_ZLC();
 
	    	
	    }

	    function initPage(){
	    	//如果保存成功或者保存失败
		    if(saveIsSuccess != "0"){
		    	showSaveData();
		    }else{
	    		document.all.htbh.focus();
		    }
	    }

	  
	  
	  //解析保存完成之后返回时的买卖方信息
	  function parseSaveBuyorSellInfo(buyorSellInfo,tableId,hidPropertyObj){
		  
		  if(buyorSellInfo != null && buyorSellInfo != ""){
			  if(buyorSellInfo.indexOf("^")>0){
				  var infoArr = buyorSellInfo.split("^");
				  
				  for(var index =0; index < infoArr.length;index ++){
					  var tempInfo = infoArr[index];
					  //调用解析方法，解析每个买卖方的信息并显示
					  setBuyorSellInfo(tempInfo,tableId,hidPropertyObj);
				  }
			  }else{
				  setBuyorSellInfo(buyorSellInfo,tableId,hidPropertyObj);
			  }
		  }
		  
		  
	  }
	  
	  //设置买卖双方信息
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
				//alert("信息组数"+count);
				
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
						oneGroupInfo.push(value_2);//证件类型代码
						oneGroupInfo.push(value_3);
						oneGroupInfo.push(value_4);
						oneGroupInfo.push(value_5);
						
						//alert("RETURN::"+DSzjdm);
						
						//设置并显示每组信息
						isSuccess = setOneGroupInfo(oneGroupInfo,tableobj);
						
						if(!isSuccess){
							alert("显示买卖人信息出错！");
							return isSuccess;
						}
						
						//拼接提交到后台的信息
						var tempOneGroupValue = value_0 +"~"+value_1+"~"+value_2+"~"+value_3+"~"+value_4+"~"+value_5;
						if(zsIndex == 0){
							submitInfo = tempOneGroupValue;
						}else{
							submitInfo = submitInfo+"^"+tempOneGroupValue;
						}
						//重置信息
						oneGroupInfo = new Array();
					}
					//设置提交隐藏域的值
					if(hidPropertyObj != null && hidPropertyObj !=""){
						hidPropertyObj.value = submitInfo;
					}
				}
			}
			
			return true;
	  }
	  
	  //把每组信息设置到td的innerHTML中显示
	  function setOneGroupInfo(arr,tableobj){
		  
	  			if(tableobj == null || tableobj == ""){
	  				return false;
	  			}
	  			var tabLength = tableobj.rows.length;
	  			
	  			//初始化表头
	  			if(tabLength == 2){
		    		var otr = tableobj.insertRow(tabLength-1);
		    		//第一行第一列  ”名称（姓名）“
		    		var mc_Cell=otr.insertCell();
		    		mc_Cell.innerHTML="名称（姓名）";
		    		mc_Cell.className ="2-td2-left";
		    		mc_Cell.WIDTH="30%";
		    		
		    		//第一行第二列  “类别”
		    		var lb_Cell=otr.insertCell();
		    		lb_Cell.innerHTML="类别";
		    		lb_Cell.className ="2-td2-left";
		    		lb_Cell.WIDTH="10%";
		    		
		    		//第一行第三列 “证件类型”
		    		var zjlx_Cell=otr.insertCell();
		    		zjlx_Cell.innerHTML="证件类型";
		    		zjlx_Cell.className ="2-td2-left";
		    		zjlx_Cell.WIDTH="10%";	
		    		
		    		//第一行第四列 “证件号码”
		    		var zjhm_Cell=otr.insertCell();
		    		zjhm_Cell.innerHTML="证件号码";
		    		zjhm_Cell.className ="2-td2-left";
		    		zjhm_Cell.WIDTH="20%";	
		    		
		    		//第一行第五列 “权利人份额”
		    		var fe_Cell=otr.insertCell();
		    		fe_Cell.innerHTML="权利人份额";
		    		fe_Cell.className ="2-td2-left";
		    		fe_Cell.WIDTH="10%";	
		    		
		    		//第一行第六列 “联系人电话”
		    		var lxdh_Cell=otr.insertCell();
		    		lxdh_Cell.innerHTML="联系人电话";
		    		lxdh_Cell.className ="2-td2-left";
		    		lxdh_Cell.WIDTH="20%";
		    		
		    		//第一行第七列 “选择”
		    		var xz_Cell=otr.insertCell();
		    		xz_Cell.innerHTML="选择";
		    		xz_Cell.className ="2-td2-center";
		    		xz_Cell.WIDTH="20%";
		    		
	  			}
	  			
	    		//新建行
	    		tabLength = tableobj.rows.length;//重新再算一次rows长度
	    		var otr_n = tableobj.insertRow(tabLength-1);
	    		
	    		//第n(n>=2)行第一列 
	    		var mc_value_Cell=otr_n.insertCell();
	    		mc_value_Cell.className ="2-td2-left";
	    		fnAddInput(mc_value_Cell,arr[0],"nsr_mc","text");

	    		//第n(n>=2)行第二列  
	    		var lb_value_Cell=otr_n.insertCell();
	    		lb_value_Cell.className ="2-td2-left";
	    		var valuesArr = new Array(['1','个人'],['0','非个人']);
	    		fnAddSelect(lb_value_Cell,"nsr_lb",valuesArr,arr[1])

	    		//第n(n>=2)行第三列
	    		var zjlx_value_Cell=otr_n.insertCell();
	    		zjlx_value_Cell.className ="2-td2-left";
	    		var zjArr = getZjArr();
	    		fnAddSelect(zjlx_value_Cell,"nsr_zjlx",zjArr,arr[2]);
	    		
	    		
	    		//第n(n>=2)行第四列 
	    		var zjhm_value_Cell=otr_n.insertCell();	    		
	    		zjhm_value_Cell.className ="2-td2-left";
	    		fnAddInput(zjhm_value_Cell,arr[3],"nsr_zjhm","text");

	    		//第n(n>=2)行第五列 
	    		var fe_value_Cell=otr_n.insertCell();
	    		fe_value_Cell.className ="2-td2-left";
	    		fnAddInput(fe_value_Cell,arr[4],"nsr_qlrfe","text");
	    		
	    		//第n(n>=2)行第六列 
	    		var lxdh_value_Cell=otr_n.insertCell();
	    		lxdh_value_Cell.className ="2-td2-left";
	    		fnAddInput(lxdh_value_Cell,arr[5],"nsr_lxdh","text");
	    		
	    		//第n(n>=2)行第七列 
	    		var checkbox_value_Cell=otr_n.insertCell();
	    		checkbox_value_Cell.className ="2-td2-center";
	    		fnAddInput(checkbox_value_Cell,arr[5],"labelFlag","checkbox");	    		
	    		
	    		return true;
	  }
	  
	  //在指定元素下创建一个imput输入框
	  function fnAddInput(cellObj,newItemValue,newItemId,inputType)
	  {
		  var oNewItem = document.createElement("INPUT");
		  //如果是输入框
		  if(inputType=="text"){
		  	oNewItem.value=newItemValue;
		  }
		  oNewItem.id=newItemId;
		  oNewItem.name=newItemId;
		  oNewItem.type=inputType;
		  cellObj.insertAdjacentElement("afterBegin",oNewItem);
		  
		  
		  
		  
	  }
	  
	  //在指定元素下创建一个select下来框
	  function fnAddSelect(cellObj,newItemId,valuesArr,value)
	  {
		 
	     //创建一个select标签
		  var oNewItem = document.createElement("SELECT");
		  oNewItem.id=newItemId;
		  
		  for(var index = 0; index < valuesArr.length; index ++)
		  {
			  var oValueArr = valuesArr[index];
			  var optionValue=oValueArr[0];//option的值
			  var optionName=oValueArr[1];//option的名称
			  
			  //创建一个option标签
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

	  //获得证件数组
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
	  
	  
	//获得证件名称	  
	function getZjmc(zjdm){
         <logic:iterate id="item" name="clfxxcjForm"  property ="zjList" indexId="index">
			var zjdm_1 = '<bean:write name="item" property="zjlxdm"/>';
			var zjmc_1 = '<bean:write name="item" property="zjlxmc"/>';
			
			if(zjdm == zjdm_1){
				
			return zjmc_1;
			
			}
		</logic:iterate> 
		
		return "其他";
	} 	
	
	/**
		校验数据库必填项
	*/
	function doCheckNotNullItems(){
		
		var isSuccess = true;
		var obj = null;
		
		//合同编号
		obj = document.forms[0].htbh;
		if(obj.value ==null || obj.value==""){
			alert("“法院（公证书）编号”为空，请检查！");
			obj.focus();
			return false;
		}
		//法院（公证书）日期
		obj = document.forms[0].htwsqyrq;
		if(obj.value == null || obj.value ==""){
			alert("“法院（公证书）日期”为空,请检查！");
			obj.focus();
			return false;			
		
		}
		//房屋权属转移类型
		
		//权属转移房屋地址区县
/* 		obj = document.forms[0].fwzlqx;
		if(obj.value == null || obj.value ==""){
			alert("“权属转移房屋地址区县”为空,请检查！");
			obj.focus();
			return false;			
		
		} */
		
		//权属转移房屋详细地址（含区县）
		obj = document.forms[0].fwzldz;
		if(obj.value == null || obj.value == ""){
			alert("“权属转移房屋详细地址（含区县）”为空,请检查！");
			obj.focus();
			return false;
		}
		//房屋所有权证号
		obj = document.forms[0].fwcqzh;
		if(obj.value == null || obj.value == ""){
			alert("”房屋所有权证号“为空,请检查！");
			obj.focus();
			return false;
		}		
		
		//房屋所有权证填发日期
		obj = document.forms[0].fwsyqztfrq;
		if(obj.value == null || obj.value == ""){
			alert("”房屋所有权证填发日期“为空,请检查！");
			obj.focus();
			return false;
		}		
		
		//本次交易是否为首次上市已购公房
			
		//房屋建筑面积
		obj = document.forms[0].fwjzmj;
		if(obj.value == null || obj.value ==""){
			alert("“房屋建筑面积”为空,请检查！");
			obj.focus();
			return false;		
		}else{
			if(!checkNumber(obj.value,16,3,true))
        	{
            	alert("“房屋建筑面积”必须为数字，\n小数点后的长度最多为3位，\n且输入值需大于等于零！");
            	obj.select();
            	return false;
        	}
			
		}
		
		//建筑结构
		
		//房屋划分用途
		 
		//所在楼层/总层数
		obj = document.forms[0].szlc;
		if(obj.value == null || obj.value ==""){
				//alert("“所在楼层/总层数”为空,请检查！");
				if(!isSzlc_zlc("all")){
					return false;
				}
			
		}
		
		//权属转移房屋总价
		obj = document.forms[0].htzj;
		if(obj.value == null || obj.value  ==""){
			alert("“权属转移房屋总价”为空,请检查！");
			obj.focus();
			return false;			
		
			
		}else{
			if(!checkNumber(obj.value,15,2,true))
        	{
            	alert("“权属转移房屋总价”必须为数字，\n小数点后的长度最多为2位，\n且输入值需大于等于零！");
            	obj.select();
            	return false;
        	}
			
		}
		
		//检查外币种、外币金额、汇率
		if(!checkWb()){
			return false;
		}		
		
		
		
		//卖方信息
		obj = document.forms[0].all_sellerInfo;
		if(obj.value == null || obj.value  ==""){
			alert("“卖方信息”为空,请检查！");
			return false;			
		}		
		//买方信息
		obj = document.forms[0].all_buyerInfo;
		if(obj.value == null || obj.value  ==""){
			alert("“买方信息”为空,请检查！");
			return false;			
		}	
		
		//房地产中介机构信息
		
		
		return isSuccess;
	}
	
	function checkWb(){
		var objBzdm = document.forms[0].bzdm;
		var objWbje = document.forms[0].wbje;
		var objHl = document.forms[0].hl;
		//外币种
		//外币种为空，但是外币金额已经填写，则提示选择外币种
		if((objWbje.value !=null&&objWbje.value!=""&&objWbje.value!="0") && (objBzdm.value == null||objBzdm.value =="")){
        	alert("”外币金额“已经填写，请选择“外币种”！");
        	objBzdm.focus();
        	return false;			
		}		
		
		//外币金额
		if(objWbje.value != null && objWbje.value  !=""){
			if(!checkNumber(objWbje.value,15,2,true))
        	{
            	alert("“外币金额”必须为数字，\n小数点后的长度最多为2位，\n且输入值需大于等于零！");
            	objWbje.select();
            	return false;
        	}
		}
		
		//外币金额为空，但是币种已经选择，则提示要填写金额
		if((objBzdm.value !=null&&objBzdm.value!="") && (objWbje.value == null||objWbje.value ==""||objWbje.value =="0"||objWbje.value =="0.00")){
        	alert("”外币种“已经选择，请填写“外币金额”！");
        	objWbje.select();
        	return false;			
		}
		
		//汇率
		if(objHl.value != null && objHl.value!=""&&objHl.value  !="0"){
			if(!checkNumber(objHl.value,15,6,true))
        	{
            	alert("“汇率”必须为数字，\n小数点后的长度最多为6位，\n且输入值需大于等于零！");
            	objHl.select();
            	return false;
        	}
			
			//如果外币金额为空，则提示填写外币金额
			if(objWbje.value == null||objWbje.value ==""||objWbje.value =="0"||objWbje.value =="0.00"){
	        	alert("”汇率“已经填写，请填写“外币金额”！");
	        	objWbje.select();
	        	return false;				
			}
		}
		//如果汇率为空，但是外币金额已经填写，则提示填写汇率
		if((objWbje.value == null&&objWbje.value ==""&&objWbje.value =="0"&&objWbje.value =="0.00") && (objHl.value == null||objHl.value =="")){
        	alert("”外币金额“已经填写，请填写“汇率”！");
        	objHl.select();
        	return false;			
		}
		return true;
	}
	
	//所在楼层和总楼层格式输入是否正确
	function isSzlc_zlc(type){
		if(window.event.keyCode!=13 && window.event.keyCode!=0 && obj==window.event.srcElement) return true;
		
		var szlc_showOBJ = document.all.szlc_show;//所在楼层
	 	
 
		
		var zlc_showOBJ = document.all.zlc_show;//总楼层
		
		//alert("szlc_showOBJ ++"+szlc_showOBJ.value +"  zlc_showOBJ++"+zlc_showOBJ.value);
		
		//判断所在楼层
		if((type == "all" || type == "szlc") && szlc_showOBJ.value == ""){
			alert("所在楼层为必填项，不能为空");
			szlc_showOBJ.focus();
			document.forms[0].szlc.value="";
			return false;
		}
		
		//总楼层
		if((type == "all" || type == "zlc") && zlc_showOBJ.value == ""){
			alert("总楼层为必填项，不能为空");
			zlc_showOBJ.focus();
			document.forms[0].szlc.value="";
			return false;			
		}
		
		var obj ="";// obj = 所在楼层 +"/"+总楼层
		if( szlc_showOBJ.value != "" && zlc_showOBJ.value != ""){
			obj = szlc_showOBJ.value + "/" + zlc_showOBJ.value;
		}
		 //判斷輸入樓層是否符合要求
		if(type == "szlc"){
			var reg= /^((-{0,1}[0-9])|(-{0,1}[0-9]+-{0,2}[0-9]+))$/; 
	  	 	if (!reg.test(document.getElementById("szlc_show").value)) 
				{ 
	  	 		alert('所在楼层格式不正确'); 
	  	 		return false;
	  	 		}
		}

		 
		
		//提交后台
		document.forms[0].szlc.value = obj;
		
		//alert("obj++"+document.forms[0].szlc.value);

    return true;
    
		
	}
	
  /*
	* 保存返回或者查询之后显示所在楼层和总楼层
	*/
	function setSZLC_ZLC(){
	  	var objStr = document.forms[0].szlc;
	  	
	  	//为空
	  	if(objStr.value == ""){
	  		return false;
	  	}
	  	
	  	//没有斜线分割“/”
		var index = objStr.value.indexOf("/");
		if(index ==-1){
			return  false;
		}
		
		//如果长度 != 2
		var szlcArr = objStr.value.split('/');
		if(szlcArr.length!=2){
			return  false;		
		}	
		
		//显示
		document.all.szlc_show.value=szlcArr[0];//所在楼层
		document.all.zlc_show.value=szlcArr[1];//总楼层		
		
		return true;
		
	}
	
	
	
	
	
	
	//function  : 检查输入的字符串是否可以转化为Date类型
	//Parameters: strData:String
	//return    : true: 通过检查
//				  false: 未通过检查。
	function isDate(obj,empty,inputName)
	{
	  if(window.event.keyCode!=13 && window.event.keyCode!=0 && obj==window.event.srcElement) return true;

		var strDate=obj.value;
		var succeed=true;
		var alertStr="";

		if(strDate.length==0){
			  if(empty!=null){
					alertStr+="输入值必须不为空!\n"
					succeed=false;
				}

			}
		if (strDate.length!=8 && succeed) {
					alertStr+="日期格式不正确!\n"
					succeed=false;
		}
		var strYear = strDate.substr(0,4);
		var strMonth = strDate.substr(4,2);
	  var strDay = strDate.substr(6,2);
	  var objDate = new Date(strMonth+'-'+strDay+'-'+strYear);
		if (isNaN(objDate) && succeed){
					alertStr+="日期格式不正确!\n"
					succeed=false;
		}
	    if(strYear*1<1900 && succeed) {
					alertStr+="日期格式不正确!\n"
					succeed=false;
		}
	    if ((strYear*1 != objDate.getYear()*1)&&(strYear*1 != objDate.getYear()*1+1900) && succeed) {
					alertStr+="日期格式不正确!\n"
					succeed=false;
		}
	    if (strMonth*1 != objDate.getMonth()*1+1 && succeed){
					alertStr+="日期格式不正确!\n"
					succeed=false;
		}
	    if (strDay*1 != objDate.getDate()*1 && succeed) {
					alertStr+="日期格式不正确!\n"
					succeed=false;
		} //don't call getDay function.
		if(alertStr!="") {
				alert(inputName+alertStr+"格式应为yyyyMMdd(如20130101)");
				window.event.returnValue=false;
				obj.focus();
				obj.select();
			}

	    return succeed;
			}
	
	  
	  
	  
	  function checkSubmit(operationType){
		
		  //提交表单
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
				//获得买卖双方信息
				var isSucc = getMMFXXContext('sellTab');
				if(isSucc  == false){
					return false;
				}
				
				isSucc = getMMFXXContext('buyTab');
				
				if(isSucc  == false){
					return false;
				}				
				
				if(doCheckNotNullItems()){
					if(window.confirm("该操作将修改数据库中的数据,且不能自动恢复,请确认")){
						truthBeTold = true;
					}
				}
			}
			
			if(operationType=="Delete"){
				if(!canModify()){
					return false;
				}
				if(window.confirm("该操作将从系统中删除当前采集信息,且不能自动恢复,请确认")){
					truthBeTold = true;
				}				
			}
			
			if(operationType=="Query"){
				var htbh = document.all.htbh;
				if(htbh.value == null || htbh.value == ""){
					alert("请填写”法院（公证书）编号”或二维码扫描时的“合同编号”进行查询“");
					htbh.focus();
					return false;
				}
				
				document.all.sbbh.value="";
				
			}
			
			if(operationType=="reSet"){
				if(!window.confirm("该操作将清空页面所有信息，请确认！")){
					return false;
				}
				operationType ="ToSGCJ";
			}
			
			
			
			
			if(!truthBeTold){
				return false;
			}
			
			document.forms[0].target="";
			document.all.operationType.value=operationType;
			document.all.xxly.value="02";//手工采集
			document.forms[0].submit();
		}
	  
	  function canModify(){
			//信息来源
			var xxlyVal = document.all.xxly.value;
			//已经核定
			var hasHdxxVal = document.all.hasHdxx.value;
			
			//alert(xxlyVal.length+"::"+hasHdxxVal);
			
			if(xxlyVal == "01"){
				alert("二维码扫描数据，请勿删除或修改！");
				return false;
				
			}
			
			
			if(hasHdxxVal == "Y"){
				alert("该信息已经核定，请先删除核定信息，再做删除或修改！");
				return false;
			}			
			
		  
		  return true;
	  }
	  
	  //确认跳转或者退出
	  	function sureTurn(type){
		  	var msg ="";
	  		var goToMsg ="跳转到其他页面";
	  		var returnMsg ="退出当前页面";
	  		
	  		if(type =="goTo"){
	  			msg = goToMsg;
	  		}else if (type == "return"){
	  			msg = returnMsg;
	  		}
	  		
	  		//如果有数据未保存则提示
			if(saveIsSuccess!="1"){
				return window.confirm("填写信息尚未保存，是否要"+msg+",请确认");
			}else{
				 return true;
			}
	  	}

	    </script> 
</HTML>
