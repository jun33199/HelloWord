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
	var xslPath="fangtu/alter_td_ziyong.xsl";
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
var items = xmlDoc.selectNodes("/taxdoc/tdZiyongList");


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
	var regList = ["djbh","id","tdzl","tdzsh","tdmj","qzmsmj","qzysmj","mpfmse","nynse","sfdj","sfjnws","bz","opFlag"];
	// alter vo info
	var alterList = ["jsjdm", "djbh", "jcbh", "id", "bglx", "ysmjbgyy", "jmsyzbgyy", "jmzcdm", "jmsqxq", "jmsqxz", "bgqtdzl", "bgqtdzsh", "bgqtdmj", "bgqqzmsmj", "bgqqzysmj", "bgqmpfmse", "bgqnynse", "bgqsfdj","bgqsfjnws","bgqbz", "bghtdzl", "bghtdzsh", "bghtdmj", "bghqzmsmj", "bghqzysmj", "bghmpfmse", "bghnynse", "bghsfdj","bghsfjnws","bghbz", "fhbs", "fhr", "fhrq", "tbrq", "swjgzzjgdm", "qxdm", "lrr", "lrrq", "cjr", "cjrq"];


	toXml("tdZiyongList", tableCount, list, regList, alterList);
}

var zc_options = "<%=(String)request.getAttribute("zc_options")%>";

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


										
	//土地坐落
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghtdzl" value="北京市' + zlqx_name + '"/>';		
	
	//土地证书号
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghtdzsh" />';
	
	//土地面积
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghtdmj" />';
	
	//其中免税面积
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghqzmsmj" />'
    	+ '<br/><!-- 0-法定,1-大修,2-其他 -->'
    	+ '原因：'
    	+ '<select name="list[' + currentCount + '].alterVO.jmsyzbgyy" id="bb_' + currentCount + '" onchange="selectZhengCe(this, 3)">'
		+ '<option value="">---</option>'
		+ '<option value="0">法定</option>'
		+ '<option value="1">搬迁</option>'
		+ '<option value="2">荒山</option>'
		+ '<option value="3">其他</option>'
		+ '</select>'
		+ '<div style="display: none" id="bZCDiv_' + currentCount + '">'
		+ '文件号：'
		+ '<select name="list[' + currentCount + '].alterVO.jmzcdm" id="bZC_' + currentCount + '">'
		+ '<option value="">请选择</option>'
		+ zc_options
		+ '</select>'
		+ '<br/>'
		+ '<span class="query_zhengce" onclick="queryZhengce(' + currentCount + ')">查看减免税内容</span>'
		+ '</div>'
		+ '<br/>'
		+ '减免税期限起：'
		+ '<input name="list[' + currentCount + '].alterVO.jmsqxq" id="bb_' + currentCount + '"/>'
		+ '<br/>'
		+ '减免税期限止：'
		+ '<input name="list[' + currentCount + '].alterVO.jmsqxz" id="bb_' + currentCount + '"/>'
		;
			
	//其中应税面积
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghqzysmj" />'
		+ '<br/>'
		+ '原因：'
		+ '<select name="list[' + currentCount + '].alterVO.ysmjbgyy" >'
		+ '	<option value="">---</option>'
		+ '	<option value="0">新增</option>'
		+ '	<option value="1">减少</option>'
		+ '	<option value="2">原值变更</option>'
		+ '</select>'
		;
			
	//每平方米税额
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
		oCell.innerHTML ='<select name="list[' + currentCount + '].alterVO.bghmpfmse" >'
    	+ option_mpfmse
    	+ '</select>';
    	
	//年应纳税额
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghnynse" />';
	
	//是否代缴
	oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    var select_sfdj = '<select name="list[' + currentCount + '].alterVO.bghsfdj" >'
    	+ option_sfdj
    	+ '</select>'
    	;
	oCell.innerHTML = select_sfdj;
		
		//是否缴纳外商投资企业土地使用费
	oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    var select_sfjnws = '<select name="list[' + currentCount + '].alterVO.bghsfjnws" >'
    	+ option_sfdj
    	+ '</select>'
    	;
	oCell.innerHTML = select_sfjnws;
	
	//备注
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
					if ( ! check2( cur_number, row_msg) )
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

	//土地坐落不能为空
	if ( ! checkTDZL( cur_number, row_msg) )
		return false;

	//对土地面积进行校验
	if ( ! checkTDMJ( cur_number, row_msg ) ) 
		return false;

	//对免税面积进行校验
	if ( ! checkMSMJ( cur_number, row_msg ) ) 
		return false;

	//对应税面积进行校验
	if ( ! checkYSMJ( cur_number, row_msg ) ) 
		return false;

	//对每平方米税额
	if ( ! checkMPFMSE( cur_number, row_msg ) ) 
		return false;

	//年应纳税额
	if ( ! checkNYNSE( cur_number, row_msg) )
		return false;

	return true;		
}

function check2( cur_number, row_msg ){

	if ( ! check1( cur_number, row_msg) )
		return false;
		
	//check 减免税期限起, 减免税期限止 日期
	if ( ! checkJMDate( cur_number, row_msg) )
		return false;
		
	return true;		
}

//土地坐落不能为空
function checkTDZL( cur_number, row_msg) {
	var tdzl = getStringLength(trimString( $F("list["+cur_number+"].alterVO.bghtdzl") ));
	if ( tdzl == 0 ) {
		alert("第"+ row_msg +"行 土地坐落不能为空");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghtdzl");
		focus_it[0].focus();
		return false;	
	}
	return true;
}

//对土地面积进行校验
function checkTDMJ( cur_number, row_msg) {

	if ( getStringLength(trimString( $F("list["+cur_number+"].alterVO.bghtdmj") )) == 0 ) {
		alert("第"+ row_msg +"行 土地面积不能为空");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghtdmj");
		focus_it[0].focus();
		return false;	
	}
	
	if ( ! checkNumber($F("list["+cur_number+"].alterVO.bghtdmj"), 19,2,true) ) {
		alert("第"+ row_msg +"行 土地面积必须为数字！");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghtdmj");
		focus_it[0].focus();
		return false;
	}
	
	if($F("list["+cur_number+"].alterVO.bghtdmj")>=50000000){
		if (!confirm("您输入的土地面积为"+$F("list["+cur_number+"].alterVO.bghtdmj")+"平方米，请确认！")){
			return false;
		}
	}
	return true;
}
//对每平方米税额进行校验
function checkMPFMSE( cur_number, row_msg) {

	if ( ! checkNumber($F("list["+cur_number+"].alterVO.bghmpfmse"), 19,2,true) ) {
		alert("第"+ row_msg +"行 每平方米税额必须为数字！");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghmpfmse");
		focus_it[0].focus();
		return false;
	}
	return true;
}
//对免税面积进行校验
function checkMSMJ( cur_number, row_msg) {

	if ( ! checkNumber($F("list["+cur_number+"].alterVO.bghqzmsmj"), 19,2,true) ) {
		alert("第"+ row_msg +"行 免税面积必须为数字！");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghqzmsmj");
		focus_it[0].focus();
		return false;
	}
	return true;
}

//对应税面积进行校验
function checkYSMJ( cur_number, row_msg) {

	if ( ! checkNumber($F("list["+cur_number+"].alterVO.bghqzysmj"), 19,2,true) ) {
		alert("第"+ row_msg +"行 应税面积必须为数字！");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghqzysmj");
		focus_it[0].focus();
		return false;
	}
	
	if ( getStringLength(trimString( $F("list["+cur_number+"].alterVO.bghbz") )) == 0 ) {
		var tdmj = parseFloat( $F("list["+cur_number+"].alterVO.bghtdmj"), 19,2,true );
		var qzmsmj = parseFloat($F("list["+cur_number+"].alterVO.bghqzmsmj"), 19,2,true);
		if ( isNaN(qzmsmj) ) qzmsmj = 0;
		var temp = handleFloat(tdmj-qzmsmj);
		var qzysmj = parseFloat(trimString( $F("list["+cur_number+"].alterVO.bghqzysmj") ));
		if ( temp != qzysmj )
		{
			alert("第"+ row_msg +"行 其中应税面积=土地面积-其中免税面积 ("+temp+")");
			var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghqzysmj");
			focus_it[0].focus();
			return false;
		}
	}
			
	return true;
}

//年应纳税额
function checkNYNSE( cur_number, row_msg) {
	
	if ( ! checkNumber($F("list["+cur_number+"].alterVO.bghnynse"), 19,2,true) ) {
		alert("第"+ row_msg +"行 年应纳税额必须为数字！");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghnynse");
		focus_it[0].focus();
		return false;
	}
	
	if ( getStringLength(trimString( $F("list["+cur_number+"].alterVO.bghbz") )) == 0 ) {
		var qzysmj = parseFloat( $F("list["+cur_number+"].alterVO.bghqzysmj") );
		var mpfmse = parseFloat( $F("list["+cur_number+"].alterVO.bghmpfmse") );
		if ( isNaN(mpfmse) ) mpfmse = 0;
		var temp = handleFloat(qzysmj*mpfmse);
		var nynse = parseFloat( $F("list["+cur_number+"].alterVO.bghnynse") );
		if ( temp != nynse )
		{
			if ( isNaN(temp) ) temp = 0.0;
			alert("第"+ row_msg +"行 年应纳税额=其中应税面积*每平方米税额 ("+temp+")");
			var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghnynse");
			focus_it[0].focus();
			return false;
		}
	}
				
	return true;	
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
								<span onclick="change_alter(2)">出租房屋</span> 
								<span onclick="change_alter(3)">承租房屋</span> 
								<span class="current">自用土地</span> 
								<span onclick="change_alter(5)">出租土地</span>
								<span onclick="change_alter(6)">承租土地</span>
							</div>
							<!-- 自用土地 -->
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
