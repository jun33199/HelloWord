<%@page pageEncoding="GBK"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
			String static_contextpath = com.ttsoft.common.util.ResourceLocator
			.getStaticFilePath(request);
	request.getAttribute("Msg");
	
	/*外资企业标识 1-是 0-否*/
	String wzqyFlag=(String)request.getAttribute("WZQY_FLAG");
%>

<html>

<%@ include file="./common/header.jsp"%>

<script>

var yhlistlent = 1;

<%
	//与签名相关的
	String containerName = "";
	
	com.ttsoft.common.model.UserData userdata = (com.ttsoft.common.model.UserData)session.getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA);
	if (userdata.getCaflag())
	{
		containerName = userdata.getCert().getContainerName();
	}
	else
	{
		containerName = "";
	}
%>

g_objSI.Container = "<%=containerName%>";
var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION")==null?"":request.getAttribute("CA_XML_SCHEME_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';


//Load xml 信息
xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
xmlDoc.async = false;
xmlDoc.loadXML(strXml);

//根元素
var rootNode = xmlDoc.documentElement;

//设置数组
var items = xmlDoc.selectNodes("//tdZiyongList");
var aryDataSource=new Array();
for(i=0;i<items.length;i++){
	var ary1=new Array();
	ary1[ary1.length]="";
	for(j=0;j<items[i].childNodes.length-2;j++){		
		ary1[ary1.length]=items[i].childNodes[j].text;
	}
	ary1[ary1.length] = constCanEditStatus;
	aryDataSource[aryDataSource.length]=ary1;
}


	//覆盖add
	function add(handler)
	{
	    yhlistlent = yhlistlent + 1;
	    var oTable = eval(handler);
	    oTable.appendRow();
	    oTable.focusAt(oTable.getCurrentRow(), 1);
	    var curRow = oTable.getCurrentRow();
	    TABLE_LIST.rows[curRow].cells[3].firstChild.value = "北京市" + zlqx_name;
	}

    function initPage()
    {
        MM_preloadImages('<%=static_contextpath%>images/shangyibu1.jpg', '<%=static_contextpath%>images/xiayibu1.jpg', '<%=static_contextpath%>images/b-scsq1.jpg', '<%=static_contextpath%>images/tuichu1.jpg', '<%=static_contextpath%>images/shangyibu1.jpg', '<%=static_contextpath%>images/xiayibu1.jpg', '<%=static_contextpath%>images/b-scsq1.jpg', '<%=static_contextpath%>images/tuichu1.jpg');

		setBaseInfo();
		
        //增加 自用房屋 的列
        var aryColumn = new Array();
        aryColumn[aryColumn.length] = new DTColumn(constCTSequence, "xh", 2, null);
		aryColumn[aryColumn.length] = new DTColumn(constCTHideTD,"pid",1,null,"", "pID", false, false, "20");
		aryColumn[aryColumn.length] = new DTColumn(constCTHideTD,"id",1,null,"", "ID", false, false, "16");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "tdzl", 150, null, "maxlength=200 onchange='modify()'", "土地坐落", false, true, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "tdzsh", 120, null, "maxlength=30 onchange='modify()'", "土地证书号", false, false, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "tdmj", 70, null, "maxlength=20 onchange='modify()'", "土地面积", false, false, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "qzmsmj", 70, null, "maxlength=20 onchange='modify()'", " 其中免税面积", false, false, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "qzysmj", 70, null, "maxlength=20 onchange='modify()'", "其中应税面积", false, false, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTSelect, "mpfmse", 70, arySelect_sl, "need_autoselect onchange='modify()'", "每平方米税额", false, true, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "nynse", 70, null, "maxlength=20 onchange='modify()'", "年应纳税额", false, true, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTSelect, "sfdj", 60, arySelect_isnot, "need_autoselect onchange='modify()'", "是否代缴", false, true, "100");
        <%if (wzqyFlag.equals("1")){%>
        aryColumn[aryColumn.length] = new DTColumn(constCTSelect, "sfjnws", 60, arySelect_isnot, "need_autoselect onchange='modify()'", "是否缴纳外商投资企业土地使用费", false, true, "100");
      	<%}else{%>
      	aryColumn[aryColumn.length] = new DTColumn(constCTHideTD,"sfjnws",1,null,"", "sfjnws", false, false, "16");
      	<%}%>
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "bz", 100, null, "maxlength=200 onchange='modify()'", "备注", false, false, "100");


        dtList = new DynamicTable(TABLE_LIST, aryColumn, "dtList");
        if (dtList == null)  return;
        daList = new DataAwareTable(dtList);
        daList.setDataSource(aryDataSource);

    }


	function calculateYSMJ()
	{
		modify();

		var i_row = daList.DynamicTable.CurrentRow;
		//土地面积
		var data1 = TABLE_LIST.rows[i_row].cells[5].firstChild.value;
		if(getStringLength(trimString(data1)) == 0){
			return;
		}
		else
		{
			var flag = checkNumber(trimString(data1), 19, 2, true);
			if ( flag == false )
			{
				alert("土地面积必须为数字！");
				return;
			}
		}

		//免税面积
		var data2 = TABLE_LIST.rows[i_row].cells[6].firstChild.value;
		if(getStringLength(trimString(data2)) == 0){
			TABLE_LIST.rows[i_row].cells[6].firstChild.value = 0.0;
			data2 = 0.0;
			TABLE_LIST.rows[i_row].cells[7].firstChild.value = data1;
		}
		else
		{
			var flag2 = checkNumber(trimString(data2), 19, 2, true);
			if ( flag2 == false )
			{
				alert("免税面积必须为数字！");
				return;
			}
		}

		//计算应税面积
		var data4 = handleFloat(parseFloat(data1)-parseFloat(data2));
		TABLE_LIST.rows[i_row].cells[7].firstChild.value = data4;

		//每平方米税额
		var data3 = TABLE_LIST.rows[i_row].cells[8].firstChild.value;
		if(getStringLength(trimString(data3)) == 0){
			return;
		}
		else
		{
			var flag3 = checkNumber(trimString(data3), 19, 2, true);
			if ( flag3 == false )
			{
				alert("每平方米税额必须为数字！");
				return;
			}
		}

		//计算年应纳税额
		TABLE_LIST.rows[i_row].cells[9].firstChild.value = handleFloat(data4*parseFloat(data3));
	}

	function calculateNYNSE()
	{
		modify();

		var i_row = daList.DynamicTable.CurrentRow;
		//应税面积
		var data4 = TABLE_LIST.rows[i_row].cells[7].firstChild.value;
		if(getStringLength(trimString(data4)) == 0){
			return;
		}
		else
		{
			var flag4 = checkNumber(trimString(data4), 19, 2, true);
			if ( flag4 == false )
			{
				alert("应税面积必须为数字！");
				return;
			}
		}

		//每平方米税额
		var data3 = TABLE_LIST.rows[i_row].cells[8].firstChild.value;
		if(getStringLength(trimString(data3)) == 0){
			return;
		}
		else
		{
			var flag3 = checkNumber(trimString(data3), 19, 2, true);
			if ( flag3 == false )
			{
				alert("每平方米税额必须为数字！");
				return;
			}
		}

		//计算年应纳税额
		TABLE_LIST.rows[i_row].cells[9].firstChild.value = handleFloat(parseFloat(data4)*parseFloat(data3));
	}

    function showAlertMessages()
    {

    }

	//重新创建XmlBuild.js中的doSubmitXML函数
	function doSubmitXML(objForm,aType,isSign,xmldata,ifsubmit)
	{	
		return fangtu_doSubmitXML(objForm,aType,isSign,xmldata,ifsubmit);
	}
	
	
	
	
	//生成房土数据xml
	function getFangtuInfo(objForm)
	{   
		var list = ["djbh","id","tdzl","tdzsh","tdmj","qzmsmj","qzysmj","mpfmse","nynse","sfdj","sfjnws","bz","opFlag"];
		
		var parent = g_Doc.XMLDoc.selectSingleNode("/taxdoc");
	
		var rowid = 0;
		for(var i=0;i<aryDataSource.length;i++)
		{
			  if ( aryDataSource[i][daList.DynamicTable.Columns.length] != "5") {
				  var child = g_Doc.XMLDoc.createElement("tdZiyongList");
				  
			      parent.appendChild(child);
			      
				  var tmp= g_Doc.XMLDoc.selectNodes("//tdZiyongList");
				  
				  for(var j=0;j<list.length;j++){
				  	
					 var tagName = list[j];
					 var value = aryDataSource[i][j+1];
					 
					 var node2 = g_Doc.XMLDoc.createElement( tagName );
		             tmp[rowid].appendChild( node2 );
					 var objCDATA = g_Doc.XMLDoc.createCDATASection(value)
					 node2.appendChild(objCDATA);
					 //alert("tagName-"+tagName+"----------value-"+value);
				 }
				 
				 rowid = rowid + 1;
			 }
				 
		}
	}	
</script>


<!-- 校验处理 -->
<script language="JavaScript">


	//对土地面积进行校验
	function checkTDMJ(dataSource)
	{
		for (var i = 0; i < dataSource.length;i++)
		{
			var flag = checkNumber(trimString(dataSource[i][5]), 19, 2, true);
			if ( flag == false )
			{
				alert("土地面积必须为数字！");
				return false;
			}
			if(dataSource[i][5]>=50000000){
				if (!confirm("您输入的土地面积为"+dataSource[i][5]+"平方米，请确认！")){
					return false;
				}
			}
		}
		return true;
	}

	//对其中免税面积进行校验
	function checkQZMSMJ(dataSource)
	{
		for (var i = 0; i < dataSource.length;i++)
		{
			var flag = checkNumber(trimString(dataSource[i][6]), 19, 2, true);
			if ( flag == false )
			{
				alert("其中免税面积必须为数字！");
				return false;
			}
		}
		return true;
	}

	//对其中应税面积进行校验
	function checkQZYSMJ(dataSource)
	{
		for (var i = 0; i < dataSource.length;i++)
		{
			var flag = checkNumber(trimString(dataSource[i][7]), 19, 2, true);
			if ( flag == false )
			{
				alert("其中应税面积必须为数字！");
				return false;
			}
			
			if ( remarkIsEmpty(dataSource, i, 12 ) ) {
			
				var tdmj = parseFloat(trimString(dataSource[i][5]));
				var qzmsmj;
				if(getStringLength(trimString(dataSource[i][6])) > 0)
				{
					qzmsmj= parseFloat(trimString(dataSource[i][6]));
				}
				else
				{
					qzmsmj = 0.0;
				}
				var temp = handleFloat(tdmj-qzmsmj);
				var qzysmj = parseFloat(trimString(dataSource[i][7]));
				if ( temp != qzysmj )
				{
					alert("其中应税面积=土地面积-其中免税面积 ("+temp+")");
					return false;
				}
			}
		}
		return true;
	}

	//对每平方米税额进行校验
	function checkMPFMSE(dataSource)
	{
		for (var i = 0; i < dataSource.length;i++)
		{
			var flag = checkNumber(trimString(dataSource[i][8]), 19, 2, true);
			if ( flag == false )
			{
				alert("每平方米税额必须为数字！");
				return false;
			}
		}
		return true;
	}

	//对年应纳税额进行校验
	function checkNYNSE(dataSource)
	{
		for (var i = 0; i < dataSource.length;i++)
		{
			var flag = checkNumber(trimString(dataSource[i][9]), 19, 2, true);
			if ( flag == false )
			{
				alert("年应纳税额必须为数字！");
				return false;
			}

			if ( remarkIsEmpty(dataSource, i, 12 ) ) {
				var qzysmj = parseFloat(trimString(dataSource[i][7]));
				var mpfmse = parseFloat(trimString(dataSource[i][8]));
				var temp = handleFloat(qzysmj*mpfmse);
				var nynse = parseFloat(trimString(dataSource[i][9]));
				if ( temp != nynse )
				{
					alert("年应纳税额=其中应税面积*每平方米税额 ("+temp+")");
					return false;
				}
			}
		}
		return true;
	}

    function checkSpecial(dataSource)
	{
		var isCheck = checkTDMJ(dataSource);
		if (!isCheck) {
			return false;
		}
		isCheck = checkQZMSMJ(dataSource);
		if (!isCheck) {
			return false;
		}
		isCheck = checkQZYSMJ(dataSource);
		if (!isCheck) {
			return false;
		}
		isCheck = checkMPFMSE(dataSource);
		if (!isCheck) {
			return false;
		}
		isCheck = checkNYNSE(dataSource);
		if (!isCheck) {
			return false;
		}
    }



</script>
<form method="post" action="fangtu.dc">

	<input type="hidden" name="actionType" id="actionType" value="Show" /> 
	<input type="hidden" name="dataSource_gm" value="" /> 
	<input type="hidden" name="cat" value="" id="cat" /> 
	<input type="hidden" name="destCat" value="" id="destCat" /> 
	<input type="hidden" name="xsltVersion" value="" id=xsltVersion /> 
	<input type="hidden" name="schemaVersion" value="" id="schemaVersion" /> 
	<input type="hidden" name="ywlx" value="" id="ywlx" /> 
	<input type="hidden" name="ywczlx" value="" id="ywczlx" />
	    
	<input type="hidden" name="totalPageNum" id="totalPageNum" />
	<input type="hidden" name="totalItemsNum"  id="totalItemsNum" />
	<input type="hidden" name="currentPageNum" id="currentPageNum" />
	<input type="hidden" name="pageSize" id="pageSize" />

	<table width="770" height="580" border="0" cellpadding="0" cellspacing="0" align="center">
		<tbody>
		<tr>
			<td>
				<font size=6pt color="#ff0000"><strong>说明：</strong></font><br>
				<font size=6pt color="#ff0000"><strong>1、在本页面您可以登记新的房屋、土地税源信息，或修改已登记但未复核过的税源信息；</strong></font><br>
				<font size=6pt color="#ff0000"><strong>2、如果需要查看或修改经税务所复核后的税源信息，请进入功能菜单“登记房屋土地变更情况 ”</strong></font><br>
			</td>
		</tr>
		<tr>
			<td valign="top">
			<table align="center" cellspacing="0" class="table-99">
				<tbody>
				<tr>
					<td class="1-td1"><strong><%=head_title%></strong></td>
				</tr>
				<tr>
					<td class="1-td2"><br />
					<%@ include file="nsr.jsp"%>
					<div align="left" id="nav">
						<span onclick="fw_ziyong()">自用房屋</span>
						<span onclick="fw_chuzu()">出租房屋</span>
						<span onclick="fw_chengzu()">承租房屋</span>
						<span class="current">自用土地</span>
						<span onclick="td_chuzu()">出租土地</span>
						<span onclick="td_zhengzu()">承租土地</span>
                    </div>
					<!-- 自用房屋 -->

						<div id="DIV_LIST" style="OVERFLOW: auto;">
							<table cellspacing="0" class="table-99" id="TABLE_LIST">
								<tbody>
								<tr>
									<td width="2%" class="2-td1-left">序号</td>
									<td class="2-td1-left" style="display: none">登记编号</td>
									<td class="2-td1-left" style="display: none">唯一编号</td>
									<td width="18%" align="middle" class="2-td1-left">
									土地坐落<strong><span class="bitian">*</span></strong></td>
									<td width="12%" align="middle" class="2-td1-left">
									土地证书号</td>
									<td width="11%" align="middle" class="2-td1-left">
									土地面积</td>
									<td width="12%" align="middle" class="2-td1-left">
									其中免税面积</td>
									<td width="9%" align="middle" class="2-td1-center">
									其中应税面积</td>
									<td width="15%" align="middle" class="2-td1-center">
									每平方米税额<strong><span class="bitian">*</span></strong></td>
									<td width="6%" align="middle" class="2-td1-center">
									年应纳税额<strong><span class="bitian">*</span></strong></td>
									<td width="5%" align="middle" class="2-td1-center">
									是否代缴<strong><span class="bitian">*</span></strong></td>
									<%if (wzqyFlag.equals("1")){%>
									<td width="15%" align="middle" class="2-td1-center">
									是否缴纳外商投资企业土地使用费<strong><span class="bitian">*</span></strong></td>
									<%}else{%>
									<td class="2-td1-left" style="display: none">是否缴纳外商投资企业土地使用费</td>
									<%}%>
									<td width="3%" align="middle" class="2-td1-center">
									备注</td>
								</tr>
							</tbody>
							</table>
							<table cellspacing="0" class="table-99" id="TABLE_SUM">
								<tbody>
								<tr>
									<td width="285" class="2-td1-left">小计</td>
									<td width="70" align="middle" class="2-td1-left">
										<fmt:formatNumber value="${tdzy_total.tdmj}" pattern="0.00"/></td>
									<td width="70" align="middle" class="2-td1-left">
										<fmt:formatNumber value="${tdzy_total.qzmsmj}" pattern="0.00"/></td>
									<td width="70" align="middle" class="2-td1-center">
										<fmt:formatNumber value="${tdzy_total.qzysmj}" pattern="0.00"/></td>
									<td width="70" align="middle" class="2-td1-center">
										&nbsp;
									</td>
									<td width="70" align="middle" class="2-td1-center">
										<fmt:formatNumber value="${tdzy_total.nynse}" pattern="0.00"/></td>
									<td align="middle" class="2-td1-center">
									&nbsp;</td>
								</tr>
							</tbody>
							</table>
						</div>
						<%@ include file="control.jsp"%>
					</td>
				</tr>
			</tbody>
			</table>
			</td>
		</tr>
	</tbody>
	</table>
</form>

<html:form styleId="regPrint" method="post" action="/fangtuPrintAction">
	<input type="hidden" name="jsjdm" id="print_jsjdm"/>
	<input type="hidden" name="actionType" value="PrintDJ"/>
</html:form>

<%@ include file="./common/footer.jsp"%>

</html>