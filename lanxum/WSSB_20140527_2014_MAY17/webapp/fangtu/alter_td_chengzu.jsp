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
	//��ǩ����ص�
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

//����xml
function parseXmlOnLoad()
{
	var xslPath="fangtu/alter_td_chengzu.xsl";
	if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
	  
    }

    return true;
}
//Load xml ��Ϣ
xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
xmlDoc.async = false;
xmlDoc.loadXML(strXml);

//��Ԫ��
var rootNode = xmlDoc.documentElement;

//��������
var items = xmlDoc.selectNodes("/taxdoc/tdChengzuList");


var tableCount = items.length;

//��ֵ��ҳ��
function initPage() {
	setBaseInfo();
	parseXmlOnLoad();
	
}

//����ύbutton
function doAlterSubmit() {

//	TODO: У�鹤��
//    if(isCheck == false){
//        return;
//    }
    
	$("actionType").value = "Show";

    var saveConfirm=confirm("ȷ�ϱ��浱ǰ�޸ģ�");
    if(saveConfirm==true)
    {
		return SaveExec();  
    }	
}

//��ҳ
function doTurnPageSubmit() {

//	TODO: У�鹤��
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

//���´���XmlBuild.js�е�doSubmitXML����, !Ҫ���ڵ�ǰ��ҳ����!
function doSubmitXML(objForm,aType,isSign,xmldata,ifsubmit)
{	
	return fangtu_doSubmitXML(objForm,aType,isSign,xmldata,ifsubmit);
}
	
//�����Լ���xml����, !Ҫ���ڵ�ǰ��ҳ����!
function getFangtuInfo(objForm) {
	//data vo base info
	var list = ["opFlag","deleteFlag", "updateFlag"];
	// reg vo info
	var regList = ["djbh","id","czrmc","zjlxdm","czrzjhm","cztdzl","zlqxdm","tdmj", "sfjnws","bz","opFlag"];
	// alter vo info
	var alterList = ["jsjdm", "djbh", "jcbh", "id", "bglx", "bgqczrmc", "bgqzjlxdm", "bgqczrzjhm", "bgqcztdzl", "bgqzlqxdm","bgqtdmj", "bgqsfjnws","bgqbz", "bghczrmc", "bghzjlxdm", "bghczrzjhm", "bghcztdzl", "bghzlqxdm", "bghtdmj","bghsfjnws", "bghbz", "fhbs", "fhr", "fhrq", "tbrq", "swjgzzjgdm", "qxdm", "lrr", "lrrq", "cjr", "cjrq"];


	toXml("tdChengzuList", tableCount, list, regList, alterList);
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


	//���
    var oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = currentCount + 1; 

	//���ѡ��Ϊ��
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
	oCell.innerHTML = '<input type="hidden" name="list[' + currentCount + '].updateFlag" value="0" />';
//	oCell.innerHTML += '<input type="hidden" name="list[' + currentCount + '].alterVO.ysfcyzbgyy" value="0" />';
	oCell.innerHTML += '<input type="hidden" name="list[' + currentCount + '].opFlag" value="new" />';

	//ɾ��
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="checkbox" name="list[' + currentCount + '].deleteFlag" value="ON" onclick="delete_m(this)" id="ac_' + currentCount + '" />';


	//״̬
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.id = "ad_" + currentCount;
    oCell.innerHTML = "����";
    
	//״̬
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.id = "ad_" + currentCount;
    oCell.innerHTML = "����";
      										
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghczrmc" />';
	
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    //oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghzjlxdm" />';
	var select_zjlx = '<select name="list[' + currentCount + '].alterVO.bghzjlxdm" >'
    	+ option_zjlx
    	+ '</select>'
    	;
	oCell.innerHTML = select_zjlx;	
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghczrzjhm" />';
	
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghcztdzl" value="������' + zlqx_name + '"/>';	
    
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    var select_zlqx = '<select name="list[' + currentCount + '].alterVO.bghzlqxdm" >'
    	+ option_zlqx
    	+ '</select>'
    	;
	oCell.innerHTML = select_zlqx;	
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghtdmj" />';
	
		//�Ƿ��������Ͷ����ҵ����ʹ�÷�
	oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    var select_sfjnws = '<select name="list[' + currentCount + '].alterVO.bghsfjnws" >'
    	+ option_sfdj
    	+ '</select>'
    	;
	oCell.innerHTML = select_sfjnws;
	
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
	//����, ���ݲ�ͬ�ı������, У��
	var oTable = $("theTable");
	var oTBody = oTable.tBodies[0];
	//alert(oTBody);
	
	var oRows = oTBody.rows;
	for (var i=0; i<oRows.length; i++ ) {
		//alert(oRows[i].name);
		switch (oRows[i].name) {
			case "old_uncheck":
				
				//δ���˵ĵǼ�����, ���ڶ��е�����,���[���]ѡ���,ҪУ��ڶ���
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
				//�Ѹ��˵ĵǼ�����, ���ڶ��е�����,���[���]ѡ���,ҪУ��ڶ���
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
				//���������ӱ������, ֱ�Ӽ���һ�е�����,���[ɾ��]ѡ���,������У��
				var cur_number = getElementNumber( oRows[i].id );
				if ( ! $("ac_" + cur_number).checked ) {
					//alert("handle new ..");
					var row_msg = Number(cur_number)+1;
					if ( ! check1( cur_number, row_msg) )
						return false;
				}
				
				break;
			case "old_new":
				//�������ӱ������, ֱ�Ӽ���һ�е�����,���[���]ѡ���,����У��
				var cur_number = getElementNumber( oRows[i].id );
				if ( $("ab_" + cur_number).checked ) {
					//alert("handle old_new ..");
					var row_msg = Number(cur_number)+1;
					if ( ! check2( cur_number, row_msg) )
						return false;
				}				
				break;
			case "delete":
				//����ɾ�����������
				break;
		} //end switch
	}
	//return false;
	
	doAlterSubmit();
}

function check1( cur_number, row_msg ) {

	//�����������䲻��Ϊ��
	if ( ! checkTDZL( cur_number, row_msg) )
		return false;

	//�������������У��
	if ( ! checkTDMJ( cur_number, row_msg ) ) 
		return false;

	//�����֤�Ž���У��
	if ( ! checkIdentity( cur_number, row_msg ) ) 
		return false;

	return true;		
}

function check2( cur_number, row_msg ){

	if ( ! check1( cur_number, row_msg) )
		return false;
		
	return true;		
}

//�����������䲻��Ϊ��
function checkTDZL( cur_number, row_msg) {
	var tdzl = getStringLength(trimString( $F("list["+cur_number+"].alterVO.bghcztdzl") ));
	if ( tdzl == 0 ) {
		alert("��"+ row_msg +"�� �����������䲻��Ϊ��");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghcztdzl");
		focus_it[0].focus();
		return false;	
	}
	return true;
}

//�������������У��
function checkTDMJ( cur_number, row_msg) {

	if ( getStringLength(trimString( $F("list["+cur_number+"].alterVO.bghtdmj") )) == 0 ) {
		alert("��"+ row_msg +"�� �����������Ϊ��");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghtdmj");
		focus_it[0].focus();
		return false;	
	}
	
	if ( ! checkNumber($F("list["+cur_number+"].alterVO.bghtdmj"), 19,2,true) ) {
		alert("��"+ row_msg +"�� �����������Ϊ���֣�");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghtdmj");
		focus_it[0].focus();
		return false;
	}
	
	if( $F("list["+cur_number+"].alterVO.bghtdmj")>=50000000){
		if (!confirm("��������������Ϊ"+$F("list["+cur_number+"].alterVO.bghtdmj")+"ƽ���ף���ȷ�ϣ�")){
			return false;
		}
	}
	
	return true;
}
//�����֤�Ž���У��
function checkIdentity( cur_number, row_msg) {
	var zjlxdm = trimString( $F("list["+cur_number+"].alterVO.bghzjlxdm") );
	if ( zjlxdm == '02' ) {
		var hmLen = getStringLength(trimString( $F("list["+cur_number+"].alterVO.bghczrzjhm") ));
		if ( hmLen != 0 ) {
			if ( ! checkIdentityCard( trimString( $F("list["+cur_number+"].alterVO.bghczrzjhm") ) ) ) {
				alert("��"+ row_msg +"�����֤�Ų��Ϸ���");
				var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghczrzjhm");
				focus_it[0].focus();
				return false;	
			}
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
								<span onclick="change_alter(1)">���÷���</span>
								<span onclick="change_alter(2)">���ⷿ��</span> 
								<span onclick="change_alter(3)">���ⷿ��</span> 
								<span onclick="change_alter(4)">��������</span> 
								<span onclick="change_alter(5)">��������</span>
								<span class="current">��������</span>
							</div>
							<!-- �������� -->
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
