<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.web.QyqssdsBaForm" %>

<html>
<head>
<title>中华人民共和国企业清算所得税备案表</title>
<link href="../../../css/text.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../../../js/treatImage.js"></script>
<script language=JavaScript src="../../../js/compute.js"></script>
<script language=JavaScript src="../../../js/function.js"></script>
<script language=JavaScript src="../../../js/smsb_common.js"></script>
<script language=JavaScript src="../../../js/Bolan.js"></script>
<script language=JavaScript src="../../../js/MathString.js"></script>
<script language=JavaScript src="../../../js/Stack.js"></script>
<script language=JavaScript src="../../../js/reader.js"></script>
<script language="JavaScript" type="text/javascript" src="../../../js/My97DatePicker/WdatePicker.js"></script>


</head>
	<%
		com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.web.QyqssdsBaForm form = (com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.web.QyqssdsBaForm) request
				.getAttribute("qyqssdsBaForm2014");
	//申请类型 0:网上，1：上门
	String sqlx = form.getSqlx();
	//备案审核标识
	String bashbs = form.getBaShztbs();
	//申报审核标识
	String sbshbs = form.getSbShztbs();
	%>
	<script type="text/javascript" language="JavaScript">

function doShow(){
	 //保存按钮
	 var baocun = document.getElementById("baocundiv");
	 //接受申请按钮
	 var jieshoushenqing = document.getElementById("jieshoushenqingdiv");
	 //拒绝申请按钮
	 var jujueshenqing = document.getElementById("jujueshenqingdiv");
	 //删除按钮
	 var shanchu = document.getElementById("shanchudiv");
	 //撤销按钮
	 var chexiao = document.getElementById("chexiaodiv");
	 //查询按钮
	 var chaxun = document.getElementById("chaxun");

	 //备案审核标识
	 var shbs="<%=form.getBaShztbs()==null ?"0":form.getBaShztbs()%>";
	 //申报审核标识
	 var sbshbs ="<%=form.getSbShztbs()==null?"0":form.getSbShztbs()%>";
	 var sqlx ="<%=form.getSqlx()==null?"2":form.getSqlx()%>";
	 //操作类型 13审核，14查看，15添加
	 var czlx ="<%=form.getCzlx()%>";
	 var qsllry=document.getElementById("qsllry");
	 var lxdh=document.getElementById("lxdh");
	 chaxun.style.display="none";
	 //网上模块的不能修改，即保存按钮不可用
	baocun.style.display="none";
	chexiao.style.display="none";
	 if(sqlx=="0"){
	 	 qsllry.disabled =true;
		 lxdh.disabled =true;
		 for(var i=0;i<2;i++){
		 	document.getElementsByName("jyqxjm")[i].disabled=true;
			document.getElementsByName("gdjyjs")[i].disabled=true;
			document.getElementsByName("yfdxgb")[i].disabled=true;
			document.getElementsByName("yfxgpc")[i].disabled=true;
			document.getElementsByName("yfgdqs")[i].disabled=true;
		    document.getElementsByName("qtyy")[i].disabled=true;
		 }
		 if(shbs=="2" || shbs=="3"){
			 //审核成功的删除和拒绝申请和接受申请按钮不可用
			 jieshoushenqing.style.display="none";
			// shanchu.style.display="none";
			 jujueshenqing.style.display="none";
		 }
		 
	 }else if(sqlx=="1"){
		 //上门申报不需要接受申请和拒绝申请按钮
		 jieshoushenqing.style.display="none";
		 jujueshenqing.style.display="none";
		 if(shbs=="2"){
		 	 qsllry.disabled =true;
			 lxdh.disabled =true;
		 for(var i=0;i<2;i++){
		 	document.getElementsByName("jyqxjm")[i].disabled=true;
			document.getElementsByName("gdjyjs")[i].disabled=true;
			document.getElementsByName("yfdxgb")[i].disabled=true;
			document.getElementsByName("yfxgpc")[i].disabled=true;
			document.getElementsByName("yfgdqs")[i].disabled=true;
		    document.getElementsByName("qtyy")[i].disabled=true;
		 }
			 //审核成功的保存和删除按钮不可用
			 baocun.style.display="none";
			 //shanchu.style.display="none";
			 //申报审核标识为成功的话，不能撤销
			 if(sbshbs=="2"){
				 chexiao.style.display="none";
			 }
		 }
		 if(shbs=="4"){
			 chexiao.style.display="none";
		 }
	 }//zhangj added start

	 	//jujueshenqing.style.display="none";	 
	    if(czlx=="15"){
	     shanchu.style.display="none";
	    }
	    
	   if("2"==shbs){
	   		if("true"==document.all.saveSuccess.value){
	   			doPrintHz();	   			
	   		}	
	   		document.all.saveSuccess.value="false";   		
	   }else{
	   		document.getElementById("printHz").style.display="none";
	   }
	 //zhangj added end
}

<%/*查找*/%>
function doQuery()
{
	if(trim(document.forms[0].jsjdm.value) == ""){
		alert("请输入纳税企业计算机代码！");
		document.forms[0].jsjdm.focus();
		return false;
	}

	doSubmitForm('Query');
	return false;
}


//响应计算机代码、年度的修改
function doChange(){

  var jsjdm;
  var sknd;
  jsjdm = document.forms[0].jsjdm.value;
  sknd = document.forms[0].sknd.value;

  if(jsjdm=="" ){

    return false;
  
  }else{
    doQuery();  
  }
	
}
//计算机代码进行了修改需重新进行查询  故修改查询标志
//add by wangcy  2013-12-06
function doChangeQuery(){
	document.forms[0].isQuery.value="0";
}

//响应计算机代码的回车查询
function jsjdmQuery(){
	if(event.keyCode==13) doQuery();
}

//保存
function doAccept()
{  
  
  var jsjdm;
  var nsrmc;
  jsjdm = document.forms[0].jsjdm.value;
  nsrmc = document.forms[0].nsrmc.value;
  var shbs="<%=form.getBaShztbs()==null ?"0":form.getBaShztbs()%>";
  var qsllry=document.forms[0].qsllry.value;
  var lxdh=document.forms[0].lxdh.value;
	if("2"==shbs){
	alert("该企业的企业清算所得税备案审核已通过，不能重复审核！");
	}else if(""==qsllry || qsllry==null){
	alert("管理人或清算组联络人员不能为空！");
	}else if(""==lxdh || lxdh==null){
	alert("联系电话不能为空！");
	}else if(jsjdm==""){
    alert("请填写计算机代码，确认信息无误后再进行保存！");
    document.forms[0].jsjdm.focus();
		document.forms[0].jsjdm.select();
    return false;
 	 }else{
	   var jyqxjm = document.getElementsByName("jyqxjm")[0];
		var gdjyjs = document.getElementsByName("gdjyjs")[0];
		var yfdxgb = document.getElementsByName("yfdxgb")[0];
		var yfxgpc = document.getElementsByName("yfxgpc")[0];
		var yfgdqs = document.getElementsByName("yfgdqs")[0];
		var qtyy = document.getElementsByName("qtyy")[0];
		if(jyqxjm.checked==false&&gdjyjs.checked==false&&yfdxgb.checked==false&&yfxgpc.checked==false&&yfgdqs.checked==false&&qtyy.checked==false){
			alert("企业节能项目信息必选一个！");
			return false;
		}
		if(nsrmc==""){
			alert("该纳税人的基本信息还没带出，请查询带出基本信息后再进行保存！\n计算机代码框内敲“回车”键或点击查询按钮可执行查询。");
			document.forms[0].jsjdm.focus();
			return false;
		}
		var isQuery=document.forms[0].isQuery.value;
	  	if(isQuery!="1"){
	  		alert("计算机代码或税款年度进行了修改,请重新执行查询动作！");
	  		return false;
	  	}
		////判断所有单选按钮中是否有被选中的，如果都没被选中，那么就不能保存
	  	if(confirm("请如实填写基本信息表，如果填写错误可能会导致某些报表重复录入")){
	  		doSubmit();
	    }
    return false;
    
  }
}
	function doSubmit()
	{
		
		document.all.saveSuccess.value="true";
		doSubmitForm("Accept");
		
		
	}
	//接受申请
	/*
	function doAccept(){
		
		doSubmitForm("Accept");
	}*/
	//拒绝申请
	function doRefuse(){
	var shbs="<%=form.getBaShztbs()==null ?"0":form.getBaShztbs()%>";
	if("1"==shbs){
	doSubmitForm("Refuse");
	}else{
	alert("该企业的企业清算所得税备案不是提交未审核状态，不能执行审核驳回！");
	}
		
	}
	//删除
	function doDelete(){
	var shbs="<%=form.getBaShztbs()==null ?"0":form.getBaShztbs()%>";
	if(""==shbs){
		alert("该企业的企业清算所得税备案未提交，不能作废！");
	}else{
		doSubmitForm("Delete");
	}
		
	}
	//撤销
	function doCancle(){
		doSubmitForm("Cancle");
	}

	

//在跳转到申报资料之前，必须提示是否保存已经录入数据
function toSbzl(returnStr)
{
	window.location=returnStr;    

}
<%/*返回*/%>
function doBack()
{
doSubmitForm("Back");
//window.history.back(-1); 
	
}
function doPrintHz(){
	document.forms[0].target="_blank";	
	doSubmitForm("PrintHz");
	document.forms[0].target="";
}


/**	//退出
function tuichu(){
	if(returnStr==null || returnStr==""){
		returnStr="zhsbAction.do";
	}
	//如果是由综合申报进入申报资料页面，则退出到综合申报页面
	if(document.all.iszhsb && document.all.iszhsb.value=='1')
		returnStr="zhsbAction.do?actionType=Show";
	window.location=returnStr;
}**/
</script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onload="doShow()">
	<%@ include file="../../include/header.jsp"%>

	<br>



	<html:form action="/webapp/smsb/qyqssds/2014/qyqssdsBaAction2014.do" method="post">
		<html:hidden property="actionType" />
		<html:hidden property="jsjdm" />
		<html:hidden property="nsrsbh" />
		<html:hidden property="nsrmc" />
		<html:hidden property="ssjjlxdm" />
		<html:hidden property="ssjjlxmc" />
		<html:hidden property="jydz" />
		<html:hidden property="sshydm" />
		<html:hidden property="sshymc" />
		<html:hidden property="cjr" />
		<html:hidden property="cjrq" />
		<html:hidden property="lrr" />
		<html:hidden property="lrrq" />
		<html:hidden property="xtjb" />
		<html:hidden property="bbmsf" />
		<html:hidden property="qsbaksrq" />
		<html:hidden property="iszhsb" />
		<html:hidden property="isQuery" />
		<html:hidden property="tbrq" />
		<html:hidden property="saveSuccess" />
		<table width="96%" align="center" cellspacing="0" class="table-99">
			<tr>
				<td class="1-td1" colspan="7">中华人民共和国企业清算所得税备案表</td>
			</tr>

			<tr>
				<td class="1-td2" colspan="7">
					<TABLE class="table-99" align="center">
						<TR>
							<TD>
								<div id="Layer2" style="position:static;">
									<table id="wrklistTable" border="1" cellspacing="0"
										class="table-99" align="center">

										<tr>
											<td class="2-td2-t-left" nowrap>计算机代码&nbsp;</td>
											<td class="2-td2-t-left" nowrap><div align="center">&nbsp;<ttsoft:write name="qyqssdsBaForm2014" property="jsjdm"  scope="request" /></div>
											</td>
											<td class="2-td2-t-left" nowrap>纳税人识别号&nbsp;</td>
											<td class="2-td2-t-center" nowrap>
												<div align="center">
													&nbsp;&nbsp;
													<ttsoft:write name="qyqssdsBaForm2014" property="nsrsbh"
														scope="request" />
												</div></td>
										</tr>
										
										<tr>
											<td class="2-td2-left" nowrap>清算起始日期&nbsp;</td>
											<td  class="2-td2-left" nowrap>
													<div align="center">
													&nbsp;&nbsp;
													<ttsoft:write name="qyqssdsBaForm2014" property="qsbaksrq"
														scope="request" />
												</div>
												<!-- <html:text property="qsbaksrq" onClick="WdatePicker()" ></html:text>-->
											</td>
											<td class="2-td2-left" nowrap>纳税人名称&nbsp;</td>
											<td class="2-td2-center" nowrap>
												<div align="center">
													&nbsp;&nbsp;
													<ttsoft:write name="qyqssdsBaForm2014" property="nsrmc"
														scope="request" />
												</div></td>
										</tr>
										
										<tr>
											<td class="2-td2-left" nowrap>管理人或清算组联络人员&nbsp;</td>
											<td class="2-td2-left" nowrap><input type='text'
												name='qsllry' id='qsllry'
												value='<ttsoft:write name="qyqssdsBaForm2014" property="qsllry"  scope="request" />'
												size='20' tabindex='2'>
											</td>
											<td class="2-td2-left" nowrap>联系电话&nbsp;</td>
											<td class="2-td2-center" nowrap><input type='text'
												name='lxdh' id='lxdh'
												value='<ttsoft:write name="qyqssdsBaForm2014" property="lxdh"  scope="request" />'
												size='20' tabindex='2'>
											</td>
										</tr>
										
										<tr>
										<td colspan="2" class="2-td2-left" nowrap="nowrap" style="text-align:right">企业章程规定的经营期限届满&nbsp;</td>
										
										<td colspan="2" class="2-td2-center" nowrap="nowrap" style="text-align:left">
										<html:radio name="qyqssdsBaForm2014" property="jyqxjm" value="Y">是</html:radio>
										<html:radio name="qyqssdsBaForm2014" property="jyqxjm" value="N" >否</html:radio>
											 <!--<html:hidden property="jyqxjm" />
											 <input type="Radio" name="jyqxjm_show" id="jyqxjm_Y" value="Y"/>是 
											 <input type="Radio" name="jyqxjm_show" id="jyqxjm_N" value="N" />否 
											 -->
										</td>
										
									</tr>
									
									
	
									<tr>
										<td colspan="2" class="2-td2-left" nowrap="nowrap" style="text-align:right">企业股东会、股东大会或类似机构决议解散&nbsp;</td>
										
										<td colspan="2" class="2-td2-center" nowrap="nowrap" style="text-align:left">
										<html:radio name="qyqssdsBaForm2014" property="gdjyjs" value="Y">是</html:radio>
										<html:radio name="qyqssdsBaForm2014" property="gdjyjs" value="N">否</html:radio>
											<!--<html:hidden property="gdjyjs" />
											<input type="Radio" name="gdjyjs_show" id="gdjyjs_Y" value="Y" />是
											<input type="Radio" name="gdjyjs_show" id="gdjyjs_N" value="N"/>否
											-->
										</td>
										
									</tr>

									<tr>
										<td colspan="2" class="2-td2-left" nowrap="nowrap" style="text-align:right">企业依法被吊销营业执照、责令关闭或者被撤销&nbsp;</td>
										
										<td colspan="2" class="2-td2-center" nowrap="nowrap" style="text-align:left">
										<html:radio name="qyqssdsBaForm2014" property="yfdxgb" value="Y">是</html:radio>
										<html:radio name="qyqssdsBaForm2014" property="yfdxgb" value="N">否</html:radio>
											<!--<html:hidden property="yfdxgb" />
											<input type="Radio" name="yfdxgb_show" id="yfdxgb_Y" value="Y"/>是
											<input type="Radio" name="yfdxgb_show" id="yfdxgb_N" value="N"/>否
											-->
										</td>
										
									</tr>

									<tr>
										<td colspan="2" class="2-td2-left" nowrap="nowrap" style="text-align:right">企业被人民法院依法予以解散或宣告破产&nbsp;</td>
										
										<td colspan="2" class="2-td2-center" nowrap="nowrap" style="text-align:left">
										<html:radio name="qyqssdsBaForm2014" property="yfxgpc" value="Y">是</html:radio>
										<html:radio name="qyqssdsBaForm2014" property="yfxgpc" value="N">否</html:radio>
											<!--<html:hidden property="yfxgpc" />
											<input type="Radio" name="yfxgpc_show" id="yfxgpc_Y" value="Y"/>是
											<input type="Radio" name="yfxgpc_show" id="yfxgpc_N" value="N"/>否
											-->
										</td>
										
									</tr>

									<tr>
										<td colspan="2" class="2-td2-left" nowrap="nowrap" style="text-align:right">有关法律、行政法规规定清算&nbsp;</td>
										
										<td colspan="2" class="2-td2-center" nowrap="nowrap" style="text-align:left">
										<html:radio name="qyqssdsBaForm2014" property="yfgdqs" value="Y">是</html:radio>
										<html:radio name="qyqssdsBaForm2014" property="yfgdqs" value="N">否</html:radio>
											<!--<html:hidden property="yfgdqs" />
											<input type="Radio" name="yfgdqs_show" id="yfgdqs_Y" value="Y"/>是
											<input type="Radio" name="yfgdqs_show" id="yfgdqs_N" value="N"/>否
											-->
										</td>
										
									</tr>

									<tr>
										<td colspan="2" class="2-td2-left" nowrap="nowrap" style="text-align:right">企业因其他原因解散或进行清算&nbsp;</td>
										
										<td colspan="2" class="2-td2-center" nowrap="nowrap" style="text-align:left">
										<html:radio name="qyqssdsBaForm2014" property="qtyy" value="Y">是</html:radio>
										<html:radio name="qyqssdsBaForm2014" property="qtyy" value="N">否</html:radio>
											<!--<html:hidden property="qtyy" />
											<input type="Radio" name="qtyy_show" id="qtyy_Y" value="Y"/>是
											<input type="Radio" name="qtyy_show" id="qtyy_N" value="N"/>否
											-->
										</td>
										
									</tr>

									<tr>
										<td  class="2-td2-left" nowrap="nowrap">审核状态提示&nbsp;</td>
										
										<td colspan="3" class="2-td2-center" nowrap="nowrap">
											<div align="left" style="color:red;">
												&nbsp;<ttsoft:write name="qyqssdsBaForm2014" property="baShztMessage" scope="request" />
											</div>
										</td>
										
									</tr>
										
										
									</table>
								</div></TD>
						</TR>
						<TR class="black9" align="center">
							<TD align="center"><br>
							<div  style="overflow:hidden; display:block; clear:both; text-align:center; ">
								<div  style="display:inline-block; clear:both;margin:0 auto; text-align:center;">
								<span id="printHz">
									&nbsp;&nbsp;&nbsp;&nbsp; <img onclick="doPrintHz()"
										onMouseOver="MM_swapImage('printHz','','<%=static_contextpath%>images/dayin2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" value="打印回执" id="printHz"
										src="<%=static_contextpath%>images/dayin1.jpg" width="79"
										height="22" style="cursor:hand" /> &nbsp;&nbsp;&nbsp;&nbsp; 
								</span>
								<span id="chaxun">
									&nbsp;&nbsp;&nbsp;&nbsp; <img onclick="doQuery();return false;"
										onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg ',1)"
										onMouseOut="MM_swapImgRestore()" value="查询" id="chaxun"
										src="<%=static_contextpath%>images/cx-q1.jpg" width="79"
										height="22" style="cursor:hand" /> &nbsp;&nbsp;&nbsp;&nbsp; 
										</span>
										<span id="baocundiv" >
										<img onclick="doSave();return false;"
										onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" value="保存" id="baocun"
										src="<%=static_contextpath%>images/bc-s1.jpg" width="79"
										height="22" style="cursor:hand" />&nbsp;&nbsp;&nbsp;&nbsp; 
										</span> 
										<span id="jieshoushenqingdiv" >
										<a style="cursor:hand" onClick="doAccept();return false;" onMouseOut="MM_swapImgRestore()"
				                        onMouseOver="MM_swapImage('jishoushenqing','','../../../images/b_shtg2.jpg',1)">
				                        <img src="../../../images/b_shtg1.jpg" name="jishoushenqing" width="79" height="22" border="0" id="jishoushenqing">
				                        </a>
				                        &nbsp;&nbsp;&nbsp;&nbsp;
				                        </span>
				                        <span id="jujueshenqingdiv" >
				                        <a style="cursor:hand" onClick="doRefuse();return false;" onMouseOut="MM_swapImgRestore()"
				                        onMouseOver="MM_swapImage('jujueshenqing','','../../../images/b_shbh2.jpg',1)">
				                        <img src="../../../images/b_shbh1.jpg" name="jujueshenqing" width="79" height="22" border="0" id="jujueshenqing">
				                        </a>
				                        &nbsp;&nbsp;&nbsp;&nbsp;
										</span>
										<!-- 删除按钮做作废按钮使用 -->
										<span id="shanchudiv" >
										<a style="cursor:hand" onClick="doDelete()" onMouseOut="MM_swapImgRestore()"
				                        onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/b-zf2.jpg',1)">
				                        <img src="<%=static_contextpath%>images/b-zf1.jpg" name="shanchu" width="79" height="22" border="0" id="shanchu">
				                        </a>
										&nbsp;&nbsp;&nbsp;&nbsp;
										</span>
										<span id="chexiaodiv" >
										<a style="cursor:hand" onClick="doCancle()" onMouseOut="MM_swapImgRestore()"
				                        onMouseOver="MM_swapImage('chexiao','','<%=static_contextpath%>images/b-cx2.jpg',1)">
				                        <img src="<%=static_contextpath%>images/b-cx1.jpg" name="chexiao" width="79" height="22" border="0" id="chexiao">
				                        </a>
				                        &nbsp;&nbsp;&nbsp;&nbsp;
				                        </span>
				                        <span >
										<img onclick="doBack()"
										onMouseOver="MM_swapImage('back','','<%=static_contextpath%>images/fanhui2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" value="退出" id="back"
										src="<%=static_contextpath%>images/fanhui1.jpg" width="79"
										height="22" style="cursor:hand" /></span>
								</div>
								</div>
								</TD>
								
						</TR>
						<!--  
						<TR class="black9">
							<TD><br>
								<div align="left">
									<a
										href="javascript:toSbzl('/smsb/webapp/smsb/qyqssds/2014/qyqssdsMainAction2014.do?actionType=Show')">企业清算所得税申报表(2014版)</a>
								</div></TD>
						</TR>
						-->
					</TABLE></td>
			</tr>
		</table>


		<%@ include file="../../include/footer.jsp"%>
	</html:form>

</body>
</html>
