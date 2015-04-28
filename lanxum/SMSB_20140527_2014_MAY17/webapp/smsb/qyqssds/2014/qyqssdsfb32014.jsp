<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb3.web.QyqssdsFb3Form2014"%>
<%@ page import="java.util.HashMap"%>

<html>
<head>
<title>剩余财产计算和分配明细表</title>
<link href="../../../css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/qyqssdsnew.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/function.js"></script>
</head>

<script language="JavaScript">
		<%/*初始化*/%>	
	function doInit()
	{
		//页面初始明细行数
		//var maxSize = document.forms[0].gdmc.length;
		var maxSize = document.getElementsByName("gdmc").length;
		//初始化文本框onChange事件处理
		initNumText("ljje",35);
		initNumText("tzbl",35);
		initNumText("tze",35);
		initNumText("ccje",35);
		initNumText("gxje",35);
		<%QyqssdsFb3Form2014 fb3Form=(QyqssdsFb3Form2014)request.getAttribute("qyqssdsFb3Form2014");
		if (fb3Form!=null && fb3Form.getDataList().size()>0){
			//明细数据最大索引
			int maxIndex = fb3Form.getMaxIndex();%> 
		    var test='<%=maxIndex%>';          
			//如果明细数据最大索引超过页面默认明细行数,则补充差的行数
			if(maxSize < <%=maxIndex%>)
			{
				for(var i = 0; i<(<%=maxIndex%> - maxSize); i++)
				{
					doAddRow();
				}
			}
		<%
			for(int i=0;i<fb3Form.getDataList().size();i++){
				HashMap map=(HashMap)fb3Form.getDataList().get(i);
				String hc=(String)map.get("hc");
				String value=(String)map.get("ljje");
				%>
				obj = eval("document.getElementById('<%=hc%>_1')");
				obj.value = '<%=value%>';
				<% 
			}
		}
		%>
		
		var shzt ="<%=fb3Form.getSbShztbs()==null?"-1":fb3Form.getSbShztbs()%>";
		var sqlx ="<%=fb3Form.getSqlx()==null?"-1":fb3Form.getSqlx()%>";
		//网上申报的不允许修改，故输入框置为只读
		if(sqlx=="0"){
			//doCheckInput();
		}else{
		//申报审核状态为审核通过的不允许修改，故输入框置为只读
		if(shzt=="2"){
			doCheckInput();
		}
		}
	}		
		
	//验证审核状态，调整输入框的只读和可编辑
	function doCheckInput(){
		//取所有input标签对象
		 var all = document.getElementsByTagName("input");
		 for(var i = 0;i<all.length;i++){
			 //将input标签中的text类型置为只读
			 if(all[i].type=="text"){
				 all[i].readOnly=true;
				 //all[i].setAttribute("class","text-gray");
				 all[i].className="text-gray";
			 }
			 
		 }
	}
		<%/*保存*/%>
		function doSave()
		{
			var gdmcList=document.getElementsByName("gdmc");
			var tzblList=document.getElementsByName("tzbl");
			var tzeList=document.getElementsByName("tze");
			var ccjeList=document.getElementsByName("ccje");
			var gxjeList=document.getElementsByName("gxje");
			var gdmcFlag =gdmcList[0].value=="";
			for(var i =0;i<gdmcList.length;i++){
				var gdmcFlag =gdmcList[i].value;
				var tzblFlag =tzblList[i].value;
				var tzeFlag =tzeList[i].value;
				var ccjeFlag =ccjeList[i].value;
				var gxjeFlag =gxjeList[i].value;
				<%/*-校验行是否填写完整，要么全不填，要不就全填-*/%>
				var flag =((gdmcFlag=="")&&(tzblFlag=="")&&(tzeFlag=="")&&(ccjeFlag=="")&&(gxjeFlag==""))||((gdmcFlag!="")&&(tzblFlag!="")&&(tzeFlag!="")&&(ccjeFlag!="")&&(gxjeFlag!=""));
				if(flag){
					continue;
				}else{
					var rownum =13+i;
					alert(rownum+"行没有填完整，请重新填写！");
					return false;
				}
							
			}
			doSubmitForm('Save');
		
		}
		<%/*表内关系校验*/%>
		function doCheck()
		{
			var gdmcList=document.getElementsByName("gdmc");
			var tzblList=document.getElementsByName("tzbl");
			var tzeList=document.getElementsByName("tze");
			var ccjeList=document.getElementsByName("ccje");
			var gxjeList=document.getElementsByName("gxje");
			var gdmcFlag =gdmcList[0].value=="";
			for(var i =0;i<gdmcList.length;i++){
				var gdmcFlag =gdmcList[i].value;
				var tzblFlag =tzblList[i].value;
				var tzeFlag =tzeList[i].value;
				var ccjeFlag =ccjeList[i].value;
				var gxjeFlag =gxjeList[i].value;
				<%/*-校验行是否填写完整，要么全不填，要不就全填-*/%>
				var flag =((gdmcFlag=="")&&(tzblFlag=="")&&(tzeFlag=="")&&(ccjeFlag=="")&&(gxjeFlag==""))||((gdmcFlag!="")&&(tzblFlag!="")&&(tzeFlag!="")&&(ccjeFlag!="")&&(gxjeFlag!=""));
				if(flag){
					continue;
				}else{
					var rownum =13+i;
					alert(rownum+"行没有填完整，请重新填写！");
					return false;
				}
							
			}
			doSubmitForm('Check');
		}
		<%/*清除*/%>
		function doReset()
		{
			if(confirm("确认是否清除当前数据？"))
			{
		   		for(var i=1; i < 13; i++){
					obj = eval("document.getElementById('" + i+"_1')");
					if(obj.readOnly!=true)
					obj.value = "";
		   		}

			   	// 清除剩余财产分配信息
				//var gdmcArrLength = document.forms[0].gdmc.length;
			   	var gdmcArrLength = document.getElementsByName("gdmc").length;
			   	for(var j=13; j < 18; j++)
				{
					for(var k=0; k<gdmcArrLength; k++)
					{
						obj = eval("document.getElementById('" + j + "." + (k+1) + "_1')");
						
						obj.value = "";
						
					}							
			   	}
			}
		}
	
		<%/*删除*/%>
		function doDelete()
		{
			doSubmitForm('Delete');
		}
		
		<%/*返回*/%>
		function doExit()
		{
			doSubmitForm('Exit');
		}
		
			<%/*增加行*/%>
	function doAddRow()
	{
		//获取已有分支机构明细信息数量
		var mxnum = document.getElementsByName("gdmc").length;
		//获取需要添加行的表格
		var table = document.getElementById("table_mxxx");

		//然后创建行(TR对象)
		var NewTr = document.createElement("tr");

		var hcIndex =parseInt(document.forms[0].maxRowIndex.value)+1;
		
		var xzTd1=document.createElement("<td class='2-td2-left' >");
		xzTd1.innerHTML="<input type='checkbox' tabindex='-1'  name='chkZJ' value=''>";
		NewTr.appendChild(xzTd1);
		
		var xzTd2=document.createElement("<td class='2-td2-left'>");
		xzTd2.innerHTML=hcIndex-2;
		NewTr.appendChild(xzTd2);
		
		//填充该表格行
		for(var i=13; i<18; i++)
		{
			var NewTd;
			if(i == 17)
			{
				NewTd = document.createElement("<td  class='2-td2-center' nowrap>");
			}
			else
			{
				NewTd = document.createElement("<td  class='2-td2-left' nowrap>");
			}
			var end = "." + (mxnum + 1) + "_1";
			var id = i + end;
			//alert("id = " + id);
			switch(i)
			{
				case 13:
					NewTd.innerHTML = "<td><input type='text' name='gdmc' id=" + id + " value='' size='20' tabindex='23'></td>";
					break;
				case 14:
					NewTd.innerHTML = "<td  class='2-td2-left' nowrap><input type='text' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' name='tzbl' id=" + id + " style='text-align:right' value='' size='13' ></td>";
					break;
				case 15:
					NewTd.innerHTML = "<td  class='2-td2-left' nowrap><input type='text' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' name='tze' id=" + id + " style='text-align:right' value='' size='13' ></td>";
					break;
				case 16:
					NewTd.innerHTML = "<td  class='2-td2-left' nowrap><input type='text' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' name='ccje' id=" + id + " style='text-align:right' value='' size='13' ></td>";
					break;
				case 17:
					NewTd.innerHTML = "<td  class='2-td2-center' nowrap><input type='text' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' name='gxje' id=" + id + " style='text-align:right' value='' size='13' ></td>";
					break;		
			}
			//往新行里面填充单元格
			NewTr.appendChild(NewTd);
		}
		//alert(NewTr.innerHTML);
		
		//添加行
		var LastTr = table.rows[table.rows.length - 1];

		LastTr.parentNode.appendChild(NewTr);
		//设置跨行单元格的跨行数+1
		var fzjgqk = document.getElementById("syccfp");
		fzjgqk.rowSpan += 1;
		//最大行加 1 
		document.forms[0].maxRowIndex.value=parseInt(document.forms[0].maxRowIndex.value)+1;
	}
	
	/**
	* 删除行
	*/
	function RomoveRow()
	{
		//复选框
		//var arrChk=document.all("chkZJ");
		var arrChk = document.getElementsByName("chkZJ");
		var list=document.getElementById("table_mxxx");
		var flag =true;
		
		//初始情况下，只有一个Checkbox时，不形成数组，故直接引用
		//当有多个checkbox时，或由多个删除为一个时，其实还是在数组里，所以
		//特作如下判断
		if(arrChk!=null){
			//if(arrChk.length==1){
				//if(arrChk.checked==true){
					//if(window.confirm("该操作只是从页面上删除增加的数据行，确认要删除选中的数据行吗？")){
					//	list.deleteRow(15);
					//	document.forms[0].maxRowIndex.value=parseInt(document.forms[0].maxRowIndex.value)-1;
  					//return false;
					//}else{
					//	arrChk.checked=false;
				//	}
			//	}else{
				//	alert("请先选中要删除的数据行！");
				//	return false;
				//}
			//}
			
			if(arrChk.length>=1){
				for(var i=arrChk.length-1;i>=0;i--){
					if(arrChk[i].checked==true){
						flag=false;
						if(window.confirm("该操作只是从页面上删除增加的数据行，确认要删除选中的数据行吗？")){
							break;
						}else{
							return false;
						}
					}
				}
				
				if(flag){
					alert("请先选中要删除的数据行！");
					return false;
				}
			}
			
			if(arrChk.length>=1){
				for(var i=arrChk.length-1;i>=0;i--){
					if(arrChk[i].checked==true){
						list.deleteRow(i+15);
						document.forms[0].maxRowIndex.value=parseInt(document.forms[0].maxRowIndex.value)-1;
					}
				}
				ResetHc();
				return false;
			}

		}
		return false;
	}
	
	function ResetHc(){
		var list=document.getElementById("table_mxxx");
		
		var maxrow=parseInt(document.forms[0].maxRowIndex.value);
		for(var i=15;i<parseInt(maxrow);i++){
			list.rows(i).cells(1).innerHTML=parseInt(i)-parseInt(1);
		}
	}
	</script>


<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onLoad="doInit()">
<%@include file="../../include/header.jsp"%>
<br>

<html:form action="/webapp/smsb/qyqssds/2014/qyqssdsFb3Action2014">
	<html:hidden property="actionType" />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />
	<html:hidden property="sbShztbs" />
	<html:hidden property="sqlx" />
	
	<input type="hidden" name="maxRowIndex" value="15">
	<table width="96%" align="center" cellspacing="0" class="table-99"
		onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">剩余财产计算和分配明细表</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;申报日期:<bean:write
				name="qyqssdsFb3Form2014" property="tbrq" scope="request" filter="true" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额单位：元（列至角分）
			</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;计算机代码:<bean:write
				name="qyqssdsFb3Form2014" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;纳税人名称：<bean:write
				name="qyqssdsFb3Form2014" property="nsrmc" scope="request" filter="true" />&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">
			<TABLE class="table-99" align="center">
				<TR>
					<TD>
					<div id="Layer2" style="position:static;">
					<table id="table_mxxx" border="1" cellspacing="0"
						class="table-99" align="center">
						<tr>
							<td class="2-td1-left" nowrap>类别</td>
							<td colspan="2" class="2-td1-left" nowrap>行次</td>
							<td colspan="3" class="2-td1-left" nowrap>项目</td>
							<td colspan="2" class="2-td1-center" nowrap>金额</td>
						</tr>
						<tr>
							<td rowspan="12" class="2-td2-left" nowrap>剩余财产计算</td>
							<td colspan="2" class="2-td2-left" nowrap>1<input type="hidden" name="hc" value="1" id="hc_1"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>资产可变现价值或交易价格</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='1_1' value='' size='13' tabindex='1'></td>
						</tr>
						<tr>
							<td colspan="2" class="2-td2-left" nowrap>2<input type="hidden" name="hc" value="2" id="hc_2"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>清算费用</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='2_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td colspan="2" class="2-td2-left" nowrap>3<input type="hidden" name="hc" value="3" id="hc_3"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>职工工资</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='3_1' value='' size='13' tabindex='3'></td>
						</tr>
						<tr>
							<td colspan="2" class="2-td2-left" nowrap>4<input type="hidden" name="hc" value="4" id="hc_4"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>社会保险费用</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='4_1' value='' size='13' tabindex='4'></td>
						</tr>
						<tr>
							<td colspan="2" class="2-td2-left" nowrap>5<input type="hidden" name="hc" value="5" id="hc_5"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>法定补偿金</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='5_1' value='' size='13' tabindex='5'></td>
						</tr>
						<tr>
							<td colspan="2" class="2-td2-left" nowrap>6<input type="hidden" name="hc" value="6" id="hc_6"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>清算税金及附加</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='6_1' value='' size='13' tabindex='6'></td>
						</tr>
						<tr>
							<td colspan="2" class="2-td2-left" nowrap>7<input type="hidden" name="hc" value="7" id="hc_7"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>清算所得税额</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='7_1' value='' size='13' tabindex='7'></td>
						</tr>
						<tr>
							<td colspan="2" class="2-td2-left" nowrap>8<input type="hidden" name="hc" value="8" id="hc_8"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>以前年度欠税额</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='8_1' value='' size='13' tabindex='8'></td>
						</tr>
						<tr>
							<td colspan="2" class="2-td2-left" nowrap>9<input type="hidden" name="hc" value="9" id="hc_9"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>其他债务</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='9_1' value='' size='13' tabindex='9'></td>
						</tr>
						<tr>
							<td colspan="2" class="2-td2-left" nowrap>10<input type="hidden" name="hc" value="10" id="hc_10"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>剩余财产（1－2－…－9）</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='10_1' value='' size='13' tabindex='10'></td>
						</tr>
						<tr>
							<td colspan="2" class="2-td2-left" nowrap>11<input type="hidden" name="hc" value="11" id="hc_11"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>其中：累计盈余公积</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='11_1' value='' size='13' tabindex='11'></td>
						</tr>
						<tr>
							<td colspan="2" class="2-td2-left" nowrap>12<input type="hidden" name="hc" value="12" id="hc_12"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>累计未分配利润</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='12_1' value='' size='13' tabindex='12'></td>
						</tr>
						<tr>
							<td  id="syccfp" rowspan="6" class="2-td2-left" nowrap>剩余财产分配</td>
							<td  colspan="2" class="2-td2-left" nowrap>&nbsp;</td>
							<td  class="2-td2-left" nowrap>股东名称</td>
							<td  class="2-td2-left" nowrap>持有清算企业权益性投资比例（%）</td>
							<td  class="2-td2-left" nowrap>投资额</td>
							<td  class="2-td2-left" nowrap>分配的财产金额</td>
							<td  class="2-td2-center" nowrap>其中：确认为股息金额</td>
						</tr>
						<tr>
							<td  class="2-td2-left" nowrap>&nbsp;&nbsp;</td>
							<td  class="2-td2-left" nowrap>13</td>
							<td  class="2-td2-left" nowrap><input type='text' name='gdmc' id='13.1_1' value='' size='20' tabindex='13' ></td>
							<td  class="2-td2-left" nowrap><input type='text' name='tzbl' id='14.1_1' value='' size='13' tabindex='14' style="text-align:right"></td>
							<td  class="2-td2-left" nowrap><input type='text' name='tze' id='15.1_1' value='' size='13' tabindex='15' style="text-align:right"></td>
							<td  class="2-td2-left" nowrap><input type='text' name='ccje' id='16.1_1' value='' size='13' tabindex='16' style="text-align:right"></td>
							<td  class="2-td2-center" nowrap><input type='text' name='gxje' id='17.1_1' value='' size='13' tabindex='17' style="text-align:right"></td>
						</tr>
					</table>
					</div>
					</TD>
				</TR>
				<TR class="black9">
					<TD>
					
					<div align="center">
					<!-- 应客户要求进行屏蔽
					<a style="cursor:hand" id="previous"
					onclick="gotoPreviousTable()"><img name="xpage" value="上一张表"
					alt="填写上一张表"
					onMouseOver="MM_swapImage('previoustable','','<%=static_contextpath%>images/shangyiye2.jpg',1)"
					onMouseOut="MM_swapImgRestore()"
					src="<%=static_contextpath%>images/shangyiye1.jpg" id="previoustable"> </a>&nbsp;&nbsp;
					-->
					<input type="image" accesskey="a"
						onClick="doAddRow();return false;"
						onMouseOver="MM_swapImage('zengjia','','../../../images/add_2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" alt="增加行"
						src="../../../images/add_1.jpg" width="79"
						height="22" id="zengjia" style="cursor:hand">
					&nbsp;&nbsp;
					<input type="image" accesskey="a"
						onClick="RomoveRow();return false;"
						onMouseOver="MM_swapImage('shanchuhang','','../../../images/delete_2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" alt="删除行"
						src="../../../images/delete_1.jpg" width="79"
						height="22" id="shanchuhang" style="cursor:hand">
					&nbsp;&nbsp;<input type="image" style="cursor:hand" tabIndex="-1"
						onClick="doReset();return false;" accesskey="u" value="清除"
						alt="清除页面输入框信息"
						onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qc-u1.jpg" id="qingchu" />
					&nbsp;&nbsp; <input type="image" style="cursor:hand" tabIndex="-1"
						onClick="doSave();return false;" accesskey="s" value="保存" alt="保存"
						onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/bc-s1.jpg" id="baocun" />
					&nbsp;&nbsp; <input type="image" style="cursor:hand" tabIndex="-1"
						onClick="doCheck();return false;" accesskey="d" value="单表校验"
						alt="表内关系校验"
						onMouseOver="MM_swapImage('jiaoyan','','../../../images/b-bdjyd2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" src="../../../images/b-bdjyd1.jpg"
						id="jiaoyan" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="doDelete();return false;" accesskey="x" value="全部删除"
						alt="删除本表所有数据，且不可恢复"
						onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qbsc-x1.jpg" id="shanchu" />
					&nbsp;&nbsp; <input type="image" value="返回" alt="返回到企业所得税年报主页面"
						style="cursor:hand" tabIndex="-1" onClick="doExit();return false;"
						onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/fanhui1.jpg" id="fanhui" /></div>
					</TD>
				</TR>
			</TABLE>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>
	<%@ include file="../../include/footernew.jsp"%>
</html:form>
</body>
</html>
