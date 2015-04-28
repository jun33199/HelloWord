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
var items = xmlDoc.selectNodes("//fwZiyongList");
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

	//初始化
    function initPage()
    {
        MM_preloadImages('<%=static_contextpath%>images/shangyibu1.jpg', '<%=static_contextpath%>images/xiayibu1.jpg', '<%=static_contextpath%>images/b-scsq1.jpg', '<%=static_contextpath%>images/tuichu1.jpg', '<%=static_contextpath%>images/shangyibu1.jpg', '<%=static_contextpath%>images/xiayibu1.jpg', '<%=static_contextpath%>images/b-scsq1.jpg', '<%=static_contextpath%>images/tuichu1.jpg');

		setBaseInfo();

        //增加 自用房屋 的列
        var aryColumn = new Array();
        aryColumn[aryColumn.length] = new DTColumn(constCTSequence, "xh", 2, null);
        aryColumn[aryColumn.length] = new DTColumn(constCTHideTD,"pid",1,null,"", "pID", false, false, "20");
		aryColumn[aryColumn.length] = new DTColumn(constCTHideTD,"id",1,null,"", "ID", false, false, "16");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "fwzl", 150, null, "maxlength=100 onchange='modify()'", "房屋坐落", false, true, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "cqzsh", 150, null, "maxlength=100 onchange='modify()'", "产权证书号", false, false, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "fcyz", 70, null, "maxlength=100 onchange='DisableElement(5,6)'", "房产原值", false, false, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "swjggz", 70, null, "maxlength=100 onchange='DisableElement(6,5)'", " 税务机关估值", false, false, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "house_value", 70, null, "maxlength=100 onchange='modify()'", "其中免税原值", false, false, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "house_value", 70, null, "maxlength=100 onchange='modify()'", "其中应税原值", false, false, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "house_value", 70, null, "maxlength=100 onchange='modify()'", "年应纳税额", false, true, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTSelect, "house_value", 60, arySelect_isnot, "need_autoselect onchange='modify()'", "是否代缴", false, true, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "house_value", 100, null, "maxlength=100 onchange='modify()'", "备注", false, false, "100");


        dtList = new DynamicTable(TABLE_LIST, aryColumn, "dtList");
        if (dtList == null)  return;
        daList = new DataAwareTable(dtList);
        daList.setDataSource(aryDataSource);
        
    }


	function DisableElement(currElement, disElement)
	{
		modify();

		var i_row = daList.DynamicTable.CurrentRow;
		var data = TABLE_LIST.rows[i_row].cells[currElement].firstChild.value;
		if(getStringLength(data) > 0)
		{
			TABLE_LIST.rows[i_row].cells[disElement].firstChild.readOnly = true;
		}
		else
		{
			TABLE_LIST.rows[i_row].cells[disElement].firstChild.readOnly = false;
		}
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
		//alert("begin getFangtuInfo");
		var list = ["djbh","id","fwzl","cqzsh","fcyz","swjggz","qzmsyz","qzysyz","nynse","sfdj","bz","opFlag"];

		
		var parent = g_Doc.XMLDoc.selectSingleNode("/taxdoc");
	
		var rowid = 0;
		for(var i=0;i<aryDataSource.length;i++)
		{
			  if ( aryDataSource[i][daList.DynamicTable.Columns.length] != "5") {
			  	  
				  var child = g_Doc.XMLDoc.createElement("fwZiyongList");
				  
			      parent.appendChild(child);
			      
				  var tmp= g_Doc.XMLDoc.selectNodes("//fwZiyongList");
				  
				  for(var j=0;j<list.length;j++){
				  	
					 var tagName = list[j];
					 var value = aryDataSource[i][j+1];
					 
					 var node2 = g_Doc.XMLDoc.createElement( tagName );
					 
					 //alert(tmp);
					 //alert(rowid);
					 //alert(tmp[rowid]);
		             tmp[rowid].appendChild( node2 );
		             
					 var objCDATA = g_Doc.XMLDoc.createCDATASection(value)
					 node2.appendChild(objCDATA);
				 }
				 
				 rowid = rowid + 1;
			 }
				 
		}
		//alert("end getFangtuInfo");
	}	
	
</script>

<!-- 校验处理 -->
<script language="JavaScript">


	//对房产原值和税务机关估值进行校验，二者必填其一
	function oneoftwo(dataSource)
	{
		
		for (var i = 0; i < dataSource.length;i++)
		{
			var yz = getStringLength(trimString(dataSource[i][5]));
			var gz = getStringLength(trimString(dataSource[i][6]));
			
			if ( yz == 0 && gz == 0) {
				alert("房产原值和税务机关估值二者必填其一!");
				return false;
			}
			if (yz > 0)
			{
				var flag = checkNumber(trimString(dataSource[i][5]), 19, 2, true);
				if ( flag == false )
				{
					alert("房产原值必须为数字！");
					return false;
				}
			}
			if(gz > 0)
			{
				var flag1 = checkNumber(trimString(dataSource[i][6]), 19, 2, true);
				if ( flag1 == false )
				{
					alert("税务机关估值必须为数字！");
					return false;
				}
			}
		}
		return true;
	}

	//对免税原值进行校验
	function checkMSYZ(dataSource)
	{
		for (var i = 0; i < dataSource.length;i++)
		{
			var flag = checkNumber(trimString(dataSource[i][7]), 19, 2, true);
			if ( flag == false )
			{
				alert("免税原值必须为数字！");
				return false;
			}

		}
		return true;
	}

	//对应税原值进行校验
	function checkYSYZ(dataSource)
	{
		for (var i = 0; i < dataSource.length;i++)
		{
			var flag = checkNumber(trimString(dataSource[i][8]), 19, 2, true);
			if ( flag == false )
			{
				alert("应税原值必须为数字！");
				return false;
			}
			
			if ( remarkIsEmpty(dataSource, i, 11) ) {
				var ysyz = parseFloat(trimString(dataSource[i][8]));
	
				//应税原值=房产原值（税务机关估值）- 免税原值
				var zhi;
				if(getStringLength(trimString(dataSource[i][5])) > 0 && parseFloat(trimString(dataSource[i][5]))!=0)
				{
					zhi = parseFloat(trimString(dataSource[i][5]));
				}
				else
				{
					zhi = parseFloat(trimString(dataSource[i][6]));
				}
	
				var rel;
				if(getStringLength(trimString(dataSource[i][7])) > 0)
				{
					rel = zhi - parseFloat(trimString(dataSource[i][7]));
				}
				else
				{
					rel = zhi;
				}
	
				if( rel != ysyz )
				{
					alert("计算错误["+ (i-0+1) +"]：应税原值=房产原值（税务机关估值）- 免税原值 [" + rel + "]");
					return false;
				}
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
		
		
			//校验计算
			//按房产原值缴纳房产（城市房地产）税的年应纳税额＝应税原值×（1-30%）×1.2%
			//按税务机关估值缴纳房产（城市房地产）税的年应纳税额＝税务机关估值×1.2%
			if ( remarkIsEmpty(dataSource, i, 11 ) ) {
	
				var ysyz = parseFloat(trimString(dataSource[i][8]));
				var nynse = parseFloat(trimString(dataSource[i][9]));
					
				var zhi;
	
				if(getStringLength(trimString(dataSource[i][5])) > 0 && parseFloat(trimString(dataSource[i][5]))!=0)
				{
					zhi = parseFloat(trimString(dataSource[i][5]));
					var temp = handleFloat(ysyz*0.7*0.012);
					if ( temp != nynse ) {
						alert("计算错误：年应纳税额＝应税原值×（1-30%）×1.2% [" + temp + "]");
						return false;
					}
				}
				else
				{
					zhi = parseFloat(trimString(dataSource[i][6]));
					var temp = handleFloat(zhi*0.012);
					if ( temp != nynse ) {
						alert("计算错误：年应纳税额＝税务机关估值×1.2% [" + temp + "]");
						return false;
					}
				}
			}
		}
		return true;
	}

    function checkSpecial(dataSource)
	{
		var isCheck = oneoftwo(dataSource);
		if(!isCheck)
		{
			//alert("对房产原值和税务机关估值进行校验出错。");
			return false;
		}
		isCheck = checkMSYZ(dataSource);
		if(!isCheck)
		{
			//alert("对免税原值进行校验出错。");
			return false;
		}
		isCheck = checkYSYZ(dataSource);
		if(!isCheck)
		{
			//alert("对应税原值进行校验出错。");
			return false;
		}
		isCheck = checkNYNSE(dataSource);
		if(!isCheck)
		{
			//alert("对年应纳税额进行校验出错。");
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
	<input type="hidden" name="pageSize" id="pageSize" value="2"/>

<table width="770" height="580" border="0" cellpadding="0"
	cellspacing="0" align="center">
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
						<div align="left" id="nav"><span class="current">自用房屋</span>
						<span onclick="fw_chuzu()">出租房屋</span> <span
							onclick="fw_chengzu()">承租房屋</span> <span onclick="td_ziyong()">自用土地</span>
						<span onclick="td_chuzu()">出租土地</span> <span
							onclick="td_zhengzu()">承租土地</span></div>

						<!-- 自用房屋 -->

						<div id="DIV_LIST" style="OVERFLOW: auto;">
						<table cellspacing="0" class="table-99" id="TABLE_LIST">
							<tbody>
								<tr>
									<td width="2%" class="2-td1-left">序号</td>
									<td class="2-td1-left" style="display: none">登记编号</td>
									<td class="2-td1-left" style="display: none">唯一编号</td>
									<td width="18%" align="middle" class="2-td1-left">房屋坐落<strong><span class="bitian">*</span></strong></td>
									<td width="16%" align="middle" class="2-td1-left">产权证书号</td>
									<td width="11%" align="middle" class="2-td1-left">房产原值</td>
									<td width="12%" align="middle" class="2-td1-left">税务机关估值</td>
									<td width="9%" align="middle" class="2-td1-center">其中免税原值</td>
									<td width="15%" align="middle" class="2-td1-center">其中应税原值</td>
									<td width="6%" align="middle" class="2-td1-center">年应纳税额<strong><span class="bitian">*</span></strong></td>
									<td width="5%" align="middle" class="2-td1-center">是否代缴<strong><span class="bitian">*</span></strong></td>
									<td width="3%" align="middle" class="2-td1-center">备注</td>
								</tr>
							</tbody>
						</table>
						<table cellspacing="0" class="table-99" id="TABLE_SUM">
							<tbody>
								<tr>
									<td width="315" class="2-td1-left">小计</td>
									<td width="70" align="middle" bordercolor="#9BB4CA"
										bgcolor="#E7EEF1" class="2-td1-left">
										<fmt:formatNumber value="${fwzy_total.fcyz}" pattern="0.00"/>
									</td>
									<td width="70" align="middle" bordercolor="#9BB4CA"
										bgcolor="#E7EEF1" class="2-td1-left">
										<fmt:formatNumber value="${fwzy_total.swjggz}" pattern="0.00"/>
										</td>
									<td width="70" align="middle" bordercolor="#9BB4CA"
										bgcolor="#E7EEF1" class="2-td1-center">
										<fmt:formatNumber value="${fwzy_total.qzmsyz}" pattern="0.00"/>
										</td>
									<td width="70" align="middle" bordercolor="#9BB4CA"
										bgcolor="#E7EEF1" class="2-td1-center">
										<fmt:formatNumber value="${fwzy_total.qzysyz}" pattern="0.00"/>
										</td>
									<td width="70" align="middle" bordercolor="#9BB4CA"
										bgcolor="#E7EEF1" class="2-td1-center">
										<fmt:formatNumber value="${fwzy_total.nynse}" pattern="0.00"/>
										</td>
									<td align="middle" bordercolor="#9BB4CA" bgcolor="#E7EEF1"
										class="2-td1-center">&nbsp;</td>
								</tr>
							</tbody>
						</table>
						</div>
						<%@ include file="control.jsp"%></td>
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

