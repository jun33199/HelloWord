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
	var xslPath="fangtu/alter_td_ziyong.xsl";
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
var items = xmlDoc.selectNodes("/taxdoc/tdZiyongList");


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


										
	//��������
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghtdzl" value="������' + zlqx_name + '"/>';		
	
	//����֤���
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghtdzsh" />';
	
	//�������
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghtdmj" />';
	
	//������˰���
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghqzmsmj" />'
    	+ '<br/><!-- 0-����,1-����,2-���� -->'
    	+ 'ԭ��'
    	+ '<select name="list[' + currentCount + '].alterVO.jmsyzbgyy" id="bb_' + currentCount + '" onchange="selectZhengCe(this, 3)">'
		+ '<option value="">---</option>'
		+ '<option value="0">����</option>'
		+ '<option value="1">��Ǩ</option>'
		+ '<option value="2">��ɽ</option>'
		+ '<option value="3">����</option>'
		+ '</select>'
		+ '<div style="display: none" id="bZCDiv_' + currentCount + '">'
		+ '�ļ��ţ�'
		+ '<select name="list[' + currentCount + '].alterVO.jmzcdm" id="bZC_' + currentCount + '">'
		+ '<option value="">��ѡ��</option>'
		+ zc_options
		+ '</select>'
		+ '<br/>'
		+ '<span class="query_zhengce" onclick="queryZhengce(' + currentCount + ')">�鿴����˰����</span>'
		+ '</div>'
		+ '<br/>'
		+ '����˰������'
		+ '<input name="list[' + currentCount + '].alterVO.jmsqxq" id="bb_' + currentCount + '"/>'
		+ '<br/>'
		+ '����˰����ֹ��'
		+ '<input name="list[' + currentCount + '].alterVO.jmsqxz" id="bb_' + currentCount + '"/>'
		;
			
	//����Ӧ˰���
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghqzysmj" />'
		+ '<br/>'
		+ 'ԭ��'
		+ '<select name="list[' + currentCount + '].alterVO.ysmjbgyy" >'
		+ '	<option value="">---</option>'
		+ '	<option value="0">����</option>'
		+ '	<option value="1">����</option>'
		+ '	<option value="2">ԭֵ���</option>'
		+ '</select>'
		;
			
	//ÿƽ����˰��
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
		oCell.innerHTML ='<select name="list[' + currentCount + '].alterVO.bghmpfmse" >'
    	+ option_mpfmse
    	+ '</select>';
    	
	//��Ӧ��˰��
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghnynse" />';
	
	//�Ƿ����
	oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    var select_sfdj = '<select name="list[' + currentCount + '].alterVO.bghsfdj" >'
    	+ option_sfdj
    	+ '</select>'
    	;
	oCell.innerHTML = select_sfdj;
		
		//�Ƿ��������Ͷ����ҵ����ʹ�÷�
	oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    var select_sfjnws = '<select name="list[' + currentCount + '].alterVO.bghsfjnws" >'
    	+ option_sfdj
    	+ '</select>'
    	;
	oCell.innerHTML = select_sfjnws;
	
	//��ע
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
					if ( ! check2( cur_number, row_msg) )
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

	//�������䲻��Ϊ��
	if ( ! checkTDZL( cur_number, row_msg) )
		return false;

	//�������������У��
	if ( ! checkTDMJ( cur_number, row_msg ) ) 
		return false;

	//����˰�������У��
	if ( ! checkMSMJ( cur_number, row_msg ) ) 
		return false;

	//��Ӧ˰�������У��
	if ( ! checkYSMJ( cur_number, row_msg ) ) 
		return false;

	//��ÿƽ����˰��
	if ( ! checkMPFMSE( cur_number, row_msg ) ) 
		return false;

	//��Ӧ��˰��
	if ( ! checkNYNSE( cur_number, row_msg) )
		return false;

	return true;		
}

function check2( cur_number, row_msg ){

	if ( ! check1( cur_number, row_msg) )
		return false;
		
	//check ����˰������, ����˰����ֹ ����
	if ( ! checkJMDate( cur_number, row_msg) )
		return false;
		
	return true;		
}

//�������䲻��Ϊ��
function checkTDZL( cur_number, row_msg) {
	var tdzl = getStringLength(trimString( $F("list["+cur_number+"].alterVO.bghtdzl") ));
	if ( tdzl == 0 ) {
		alert("��"+ row_msg +"�� �������䲻��Ϊ��");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghtdzl");
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
	
	if($F("list["+cur_number+"].alterVO.bghtdmj")>=50000000){
		if (!confirm("��������������Ϊ"+$F("list["+cur_number+"].alterVO.bghtdmj")+"ƽ���ף���ȷ�ϣ�")){
			return false;
		}
	}
	return true;
}
//��ÿƽ����˰�����У��
function checkMPFMSE( cur_number, row_msg) {

	if ( ! checkNumber($F("list["+cur_number+"].alterVO.bghmpfmse"), 19,2,true) ) {
		alert("��"+ row_msg +"�� ÿƽ����˰�����Ϊ���֣�");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghmpfmse");
		focus_it[0].focus();
		return false;
	}
	return true;
}
//����˰�������У��
function checkMSMJ( cur_number, row_msg) {

	if ( ! checkNumber($F("list["+cur_number+"].alterVO.bghqzmsmj"), 19,2,true) ) {
		alert("��"+ row_msg +"�� ��˰�������Ϊ���֣�");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghqzmsmj");
		focus_it[0].focus();
		return false;
	}
	return true;
}

//��Ӧ˰�������У��
function checkYSMJ( cur_number, row_msg) {

	if ( ! checkNumber($F("list["+cur_number+"].alterVO.bghqzysmj"), 19,2,true) ) {
		alert("��"+ row_msg +"�� Ӧ˰�������Ϊ���֣�");
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
			alert("��"+ row_msg +"�� ����Ӧ˰���=�������-������˰��� ("+temp+")");
			var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghqzysmj");
			focus_it[0].focus();
			return false;
		}
	}
			
	return true;
}

//��Ӧ��˰��
function checkNYNSE( cur_number, row_msg) {
	
	if ( ! checkNumber($F("list["+cur_number+"].alterVO.bghnynse"), 19,2,true) ) {
		alert("��"+ row_msg +"�� ��Ӧ��˰�����Ϊ���֣�");
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
			alert("��"+ row_msg +"�� ��Ӧ��˰��=����Ӧ˰���*ÿƽ����˰�� ("+temp+")");
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
								<span onclick="change_alter(1)">���÷���</span>
								<span onclick="change_alter(2)">���ⷿ��</span> 
								<span onclick="change_alter(3)">���ⷿ��</span> 
								<span class="current">��������</span> 
								<span onclick="change_alter(5)">��������</span>
								<span onclick="change_alter(6)">��������</span>
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
