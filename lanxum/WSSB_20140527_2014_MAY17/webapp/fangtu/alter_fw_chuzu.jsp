<%@ page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
			String static_contextpath = com.ttsoft.common.util.ResourceLocator
			.getStaticFilePath(request);
	request.getAttribute("Msg");
%>
<html>

<%@ include file="./common/header.jsp"%>

<script language="javascript" src="gojs/fangtu_alter.js"></script>
<script type="text/javascript">
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

//解析xml
function parseXmlOnLoad()
{
	var xslPath="fangtu/alter_fw_chuzu.xsl";
	if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
	  
    }

    return true;
}
//Load xml 信息
xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
xmlDoc.async = false;
xmlDoc.loadXML(strXml);

//根元素
var rootNode = xmlDoc.documentElement;

//设置数组
var items = xmlDoc.selectNodes("/taxdoc/fwChuzuList");


var tableCount = items.length;

//初值化页面
function initPage() {
	setBaseInfo();
	parseXmlOnLoad();
	
}

//变更提交button
function doAlterSubmit() {

//	TODO: 校验工作
//    if(isCheck == false){
//        return;
//    }
    
	$("actionType").value = "Show";

    var saveConfirm=confirm("确认保存当前修改！");
    if(saveConfirm==true)
    {
		return SaveExec();  
    }	
}

//翻页
function doTurnPageSubmit() {

//	TODO: 校验工作
//    if(isCheck == false){
//        return;
//    }
    
	$("actionType").value = "Show";

	return TurnPage();
}

function doSubmitPageXML(objForm,aType,isSign,xmldata,ifsubmit)
{	
	return fangtu_doSubmitPageXML(objForm,aType,isSign,xmldata,ifsubmit);
}

//重新创建XmlBuild.js中的doSubmitXML函数, !要放在当前的页面中!
function doSubmitXML(objForm,aType,isSign,xmldata,ifsubmit)
{	
	return fangtu_doSubmitXML(objForm,aType,isSign,xmldata,ifsubmit);
}
	
//构造自己的xml数据, !要放在当前的页面中!
function getFangtuInfo(objForm) {
	//data vo base info
	var list = ["opFlag","deleteFlag", "updateFlag"];
	// reg vo info
	var regList = ["djbh","id","fwzl","cqzsh","czfwyz","nzjsr","nynse","xgrczbs","bz","opFlag"];
	// alter vo info
	var alterList = ["jsjdm", "djbh", "jcbh", "id", "bglx", "bgqfwzl", "bgqcqzsh", "bgqczfwyz","bgqnzjsr", "bgqnynse","bgqxgrczbs","bgqbz", "bghfwzl", "bghcqzsh","bghczfwyz", "bghnzjsr", "bghnynse", "bghxgrczbs","bghbz", "fhbs", "fhr", "fhrq", "tbrq", "swjgzzjgdm", "qxdm", "lrr", "lrrq", "cjr", "cjrq"];


	toXml("fwChuzuList", tableCount, list, regList, alterList);
}

</script>
<script type="text/javascript">
function add_row() {
    var args = add_row.arguments;
    var element = $(args[0]);
	var currentCount = tableCount++;
	
    var oRow = document.createElement("TR");


    element.tBodies[0].appendChild(oRow);
    oRow.bgColor = "lightskyblue";
    oRow.id = "aa_" + (currentCount);
    oRow.name = "new";


	//序号
    var oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = currentCount + 1; 

	//变更选择为空
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
	oCell.innerHTML = '<input type="hidden" name="list[' + currentCount + '].updateFlag" value="0" />';
//	oCell.innerHTML += '<input type="hidden" name="list[' + currentCount + '].alterVO.ysfcyzbgyy" value="0" />';
	oCell.innerHTML += '<input type="hidden" name="list[' + currentCount + '].opFlag" value="new" />';

	//删除
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="checkbox" name="list[' + currentCount + '].deleteFlag" value="ON" onclick="delete_m(this)" id="ac_' + currentCount + '" />';


	//状态
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.id = "ad_" + currentCount;
    oCell.innerHTML = "新增";
    
	//状态
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.id = "ad_" + currentCount;
    oCell.innerHTML = "新增";
      										
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghcqzsh" />';
	
	oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghczfwyz" />';
	
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghfwzl" value="北京市' + zlqx_name + '"/>';	
	
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghnzjsr" />';
	
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghnynse" />';
	
	//是否代缴
	oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    var select_sfdj = '<select name="list[' + currentCount + '].alterVO.bghxgrczbs" >'
    	+ option_sfdj
    	+ '</select>'
    	;
	oCell.innerHTML = select_sfdj;
	
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghbz" />';
	
    changeColor(oRow, "lightskyblue" );
    
    //alert( oRow.innerHTML );

}
</script>
<script type="text/javascript">
function doAlterCheck() {
	//alert("doAlterCheck()");
	//遍历, 根据不同的变更类型, 校验
	var oTable = $("theTable");
	var oTBody = oTable.tBodies[0];
	//alert(oTBody);
	
	var oRows = oTBody.rows;
	for (var i=0; i<oRows.length; i++ ) {
		//alert(oRows[i].name);
		switch (oRows[i].name) {
			case "old_uncheck":
				
				//未复核的登记数据, 检查第二行的数据,如果[变更]选择后,要校验第二行
				var j = i+1;
				var cur_number = getElementNumber( oRows[i].id );
				if ( oRows[j].style.display != "none" ) {
					//alert("handle old_uncheck..");
					var row_msg = Number(cur_number)+1;
					if ( ! check2( cur_number, row_msg) )
						return false;
				}
				
				i++;
				
				break;
			case "update":
			case "old":
				//已复核的登记数据, 检查第二行的数据,如果[变更]选择后,要校验第二行
				var j = i+1;
				var cur_number = getElementNumber( oRows[i].id );
				if ( $("ab_" + cur_number).checked ) {
					//alert("handle old/update..");
					var row_msg = Number(cur_number)+1;
					if ( ! check2( cur_number, row_msg) )
						return false;
				}
				i++;
				break;
			case "new":
				//新增的增加变更数据, 直接检查第一行的数据,如果[删除]选择后,不进行校验
				var cur_number = getElementNumber( oRows[i].id );
				if ( ! $("ac_" + cur_number).checked ) {
					//alert("handle new ..");
					var row_msg = Number(cur_number)+1;
					if ( ! check1( cur_number, row_msg) )
						return false;
				}
				
				break;
			case "old_new":
				//处理增加变更数据, 直接检查第一行的数据,如果[变更]选择后,进行校验
				var cur_number = getElementNumber( oRows[i].id );
				if ( $("ab_" + cur_number).checked ) {
					//alert("handle old_new ..");
					var row_msg = Number(cur_number)+1;
					if ( ! check2( cur_number, row_msg) )
						return false;
				}				
				break;
			case "delete":
				//对于删除变更不处理
				break;
		} //end switch
	}
	//return false;
	
	doAlterSubmit();
}

function check1( cur_number, row_msg ) {

	//房屋坐落不能为空
	if ( ! checkFWZL( cur_number, row_msg) )
		return false;

	//对年租金收入进行校验
	if ( ! checkNZJ( cur_number, row_msg ) ) 
		return false;
		
	//对年应纳税额进行校验
	if ( ! checkNYNSE( cur_number, row_msg ) ) 
		return false;

	return true;		
}

function check2( cur_number, row_msg ){

	if ( ! check1( cur_number, row_msg) )
		return false;
		
	return true;		
}

//房屋坐落不能为空
function checkFWZL( cur_number, row_msg) {
	var tdzl = getStringLength(trimString( $F("list["+cur_number+"].alterVO.bghfwzl") ));
	if ( tdzl == 0 ) {
		alert("第"+ row_msg +"行 房屋坐落不能为空");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghfwzl");
		focus_it[0].focus();
		return false;	
	}
	return true;
}

//对年租金收入进行校验
function checkNZJ( cur_number, row_msg) {

	if ( getStringLength(trimString( $F("list["+cur_number+"].alterVO.bghnzjsr") )) == 0 ) {
		alert("第"+ row_msg +"行 年租金收入不能为空");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghnzjsr");
		focus_it[0].focus();
		return false;	
	}
	
	if ( ! checkNumber($F("list["+cur_number+"].alterVO.bghnzjsr"), 19,2,true) ) {
		alert("第"+ row_msg +"行 年租金收入必须为数字！");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghnzjsr");
		focus_it[0].focus();
		return false;
	}
	return true;
}
//对年应纳税额进行校验
function checkNYNSE( cur_number, row_msg) {

	if ( getStringLength(trimString( $F("list["+cur_number+"].alterVO.bghnynse") )) == 0 ) {
		alert("第"+ row_msg +"行 年应纳税额不能为空");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghnynse");
		focus_it[0].focus();
		return false;	
	}
	
	if ( ! checkNumber($F("list["+cur_number+"].alterVO.bghnynse"), 19,2,true) ) {
		alert("第"+ row_msg +"行 年应纳税额必须为数字！");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghnynse");
		focus_it[0].focus();
		return false;
	}
	
	if ( getStringLength(trimString( $F("list["+cur_number+"].alterVO.bghbz") )) == 0 ) {
		
		/**
		 * modify by wofei 2009-2-6
		 * 如果选择了向个人出租，税率为4%
		 * 修改前此段只有else中的代码
		 */
		var bghxgrczbs=$F("list["+cur_number+"].alterVO.bghxgrczbs");
		
		if(bghxgrczbs=="0"){
			/* 选择了向个人出租，必须填写出租房屋原值 */
			if ( getStringLength(trimString( $F("list["+cur_number+"].alterVO.bghczfwyz") )) == 0 ) {
				alert("第"+ row_msg +"行 出租房屋原值不能为空（选择按市场价格向个人出租用于居住的住房后，必须填写原值）");
				var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghczfwyz");
				focus_it[0].focus();
				return false;	
			}
			
			if ( ! checkNumber($F("list["+cur_number+"].alterVO.bghczfwyz"), 19,2,true) ) {
				alert("第"+ row_msg +"行 出租房屋原值必须为数字！");
				var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghczfwyz");
				focus_it[0].focus();
				return false;
			}
			
			if(parseInt($F("list["+cur_number+"].alterVO.bghczfwyz"))==0){
				alert("第"+ row_msg +"行 出租房屋原值不能为空（选择按市场价格向个人出租用于居住的住房后，必须填写原值）");
				var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghczfwyz");
				focus_it[0].focus();
				return false;	
			}
			
			var temp = handleFloat(parseFloat( $F("list["+cur_number+"].alterVO.bghnzjsr") * 0.04 ));
			var nynse = parseFloat(trimString( $F("list["+cur_number+"].alterVO.bghnynse")));
			if ( temp != nynse )
			{
				alert("第"+ row_msg +"行 年应纳税额=年租金收入*4% ("+temp+")，选择按市场价格向个人出租用于居住的住房后，税率为4%。");
				var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghnynse");
				focus_it[0].focus();
				return false;
			}
		}else{
			var temp = handleFloat(parseFloat( $F("list["+cur_number+"].alterVO.bghnzjsr") * 0.12 ));
			var nynse = parseFloat(trimString( $F("list["+cur_number+"].alterVO.bghnynse")));
			if ( temp != nynse )
			{
				alert("第"+ row_msg +"行 年应纳税额=年租金收入*12% ("+temp+")");
				var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghnynse");
				focus_it[0].focus();
				return false;
			}
		}
	}
			
	return true;
}

/*选择 向个人出租 时触发*/
function selectXgrczbs(obj){
	if(obj.value=="0"){
		/*0---表示 是，仅作提示，不做计算*/
		alert("仅对企事业单位、社会团体以及其他组织按市场价格向个人出租用于居住的住房，减按4％的税率征收房产税。\n\n文件依据：财税【2008】24号");
	}else if(obj.value=="1"){
		/*1---表示 否*/
		
	}
}

</script>

<form method="post" action="fangtuAlter.dc">
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
	
	<table width="100%" height="580" border="0" cellpadding="0"
		cellspacing="0" align="center">
		<tbody>
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
								<span onclick="change_alter(1)">自用房屋</span>
								<span class="current">出租房屋</span> 
								<span onclick="change_alter(3)">承租房屋</span> 
								<span onclick="change_alter(4)">自用土地</span> 
								<span onclick="change_alter(5)">出租土地</span>
								<span onclick="change_alter(6)">承租土地</span>
							</div>
							<!-- 出租房屋 -->
							<div id="result"></div>
							<%@ include file="control_alter.jsp" %>
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
	<input type="hidden" name="actionType" value="PrintBG"/>
</html:form>
<%@ include file="./common/footer.jsp"%>

</html>
