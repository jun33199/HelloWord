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
	var xslPath="fangtu/alter_fw_ziyong.xsl";
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
var items = xmlDoc.selectNodes("/taxdoc/fwZiyongList");


var tableCount = items.length;

//��ֵ��ҳ��
function initPage() {
	//alert("begin initPage");
	setBaseInfo();
	//alert("end setBaseInfo");
	parseXmlOnLoad();
	//alert("end initPage");
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
	var regList = ["djbh","id","fwzl","cqzsh","fcyz","swjggz","qzmsyz","qzysyz","nynse","sfdj","bz","opFlag"];
	
	// alter vo info
	var alterList = ["jsjdm", "djbh", "jcbh", "id", "bglx", "ysfcyzbgyy", "jmsyzbgyy", "jmzcdm", "jmsqxq", "jmsqxz", "bgqfwzl", "bgqcqzsh", "bgqfcyz", "bgqswjggz", "bgqqzmsyz", "bgqqzysyz", "bgqnynse", "bgqsfdj", "bgqbz", "bghfwzl", "bghcqzsh", "bghfcyz", "bghswjggz", "bghqzmsyz", "bghqzysyz", "bghnynse", "bghsfdj", "bghbz", "fhbs", "fhr", "fhrq", "tbrq", "swjgzzjgdm", "qxdm", "lrr", "lrrq", "cjr", "cjrq"];
	
	toXml("fwZiyongList", tableCount, list, regList, alterList);
	
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
    oCell.innerHTML = '<input type="checkbox" name="list[' + currentCount + '].deleteFlag" value="1" onclick="delete_m(this)" id="ac_' + currentCount + '" />';


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
										
	//��Ȩ֤���
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghcqzsh" />';
	
	//��������
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghfwzl" value="������' + zlqx_name + '"/>';
	    
	    
	//����ԭֵ
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghfcyz" />';
	
	//˰����ع�ֵ
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghswjggz" />';
	
	//������˰ԭֵ
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = ''
    	+ '<input type="input" name="list[' + currentCount + '].alterVO.bghqzmsyz" />'
    	+ '<br/><!-- 0-����,1-����,2-���� -->'
    	+ 'ԭ��'
    	+ '<select name="list[' + currentCount + '].alterVO.jmsyzbgyy" id="bb_' + currentCount + '" onchange="selectZhengCe(this)">'
		+ '<option value="">---</option>'
		+ '<option value="0">����</option>'
		+ '<option value="1">����</option>'
		+ '<option value="2">����</option>'
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
	//����Ӧ˰ԭֵ
    oCell = document.createElement("TD");
    oRow.appendChild(oCell);
    oCell.innerHTML = '<input type="input" name="list[' + currentCount + '].alterVO.bghqzysyz" />'
		+ '<br/>'
		+ 'ԭ��'
		+ '<select name="list[' + currentCount + '].alterVO.ysfcyzbgyy" >'
		+ '<option value="">---</option>'
		+ '<option value="0">����</option>'
		+ '<option value="1">����</option>'
		+ '<option value="2">ԭֵ���</option>'
		+ '</select>'
		;
			
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
		//alert(oRows[i].id);
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
				//alert("cur_number:" + cur_number);
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
	//alert("checkFWZL");
	if ( ! checkFWZL( cur_number, row_msg) )
		return false;

	//�Է���ԭֵ��˰����ع�ֵ����У�飬���߱�����һ
	//alert("checkOneoftwo");	
	if ( ! checkOneoftwo( cur_number, row_msg ) ) 
		return false;

	//����˰ԭֵ����У��
	//alert("checkMSYZ");	
	if ( ! checkMSYZ( cur_number, row_msg) )
		return false;
		
	//��Ӧ˰ԭֵ����У��
	//alert("checkYSYZ");	
	if ( ! checkYSYZ( cur_number, row_msg) )
		return false;

	//��Ӧ��˰��
	//alert("checkNYNSE");	
	if ( ! checkNYNSE( cur_number, row_msg) )
		return false;

	//�������ԭֵ�仯�󣬱�����ԭ��
	if ( ! checkCause( cur_number, row_msg) )
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
function checkFWZL( cur_number, row_msg) {
	var fwzl = getStringLength(trimString( $F("list["+cur_number+"].alterVO.bghfwzl") ));
	if ( fwzl == 0 ) {
		alert("��"+ row_msg +"�� �������䲻��Ϊ��");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghfwzl");
		focus_it[0].focus();
		return false;	
	}
	return true;
}



//����˰ԭֵ����У��
function checkMSYZ( cur_number, row_msg) {
	
	if ( ! checkNumber($F("list["+cur_number+"].alterVO.bghqzmsyz"), 19,2,true) ) {
		alert("��"+ row_msg +"�� ��˰ԭֵ����Ϊ���֣�");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghqzmsyz");
		focus_it[0].focus();
		return false;
	}
	return true;	
}

//��Ӧ˰ԭֵ����У��
function checkYSYZ( cur_number, row_msg) {
	//alert("begin checkYSYZ");
	if ( ! checkNumber($F("list["+cur_number+"].alterVO.bghqzysyz"), 19,2,true) ) {
		alert("��"+ row_msg +"�� Ӧ˰ԭֵ����Ϊ���֣�");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghqzysyz");
		focus_it[0].focus();
		return false;
	}
	
	if ( getStringLength(trimString( $F("list["+cur_number+"].alterVO.bghbz") )) == 0 ) {
		var ysyz = parseFloat( $F("list["+cur_number+"].alterVO.bghqzysyz") );
		var zhi;
		if ( getStringLength(trimString( $F("list["+cur_number+"].alterVO.bghfcyz") )) != 0
				&& parseFloat( $F("list["+cur_number+"].alterVO.bghfcyz") ) != 0 ) {
			zhi = parseFloat( $F("list["+cur_number+"].alterVO.bghfcyz") );
		}
		else {
			zhi = parseFloat( $F("list["+cur_number+"].alterVO.bghswjggz") );
		}
		
		var rel;
		if ( $F("list["+cur_number+"].alterVO.bghqzmsyz") == "" ) {
			rel = zhi;
		} else {
			rel = zhi - parseFloat($F("list["+cur_number+"].alterVO.bghqzmsyz"));
		}
		
		if ( rel != ysyz ) {
			alert("��"+ row_msg +"�� �������Ӧ˰ԭֵ=����ԭֵ��˰����ع�ֵ��- ��˰ԭֵ [" + rel + "]");
			var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghqzysyz");
			focus_it[0].focus();
			return false;
		}
	}
		//alert("end checkYSYZ");
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
	//������ԭֵ���ɷ��������з��ز���˰����Ӧ��˰�Ӧ˰ԭֵ����1-30%����1.2%
	//��˰����ع�ֵ���ɷ��������з��ز���˰����Ӧ��˰�˰����ع�ֵ��1.2%
	if ( getStringLength(trimString( $F("list["+cur_number+"].alterVO.bghbz") )) == 0 ) {
		var ysyz = parseFloat( $F("list["+cur_number+"].alterVO.bghqzysyz") );
		var nynse = parseFloat( $F("list["+cur_number+"].alterVO.bghnynse") );
		var zhi;
		if ( parseFloat( $F("list["+cur_number+"].alterVO.bghfcyz") ) > 0 ) {
			zhi = parseFloat( $F("list["+cur_number+"].alterVO.bghfcyz") );
			var temp = handleFloat(ysyz*0.7*0.012);
			if ( temp != nynse ) {
				alert("���������Ӧ��˰�Ӧ˰ԭֵ����1-30%����1.2% [" + temp + "]");
				return false;
			}			
		}
		else {
			zhi = parseFloat( $F("list["+cur_number+"].alterVO.bghswjggz") );
			var temp = handleFloat(zhi*0.012);
			if ( temp != nynse ) {
				alert("���������Ӧ��˰�˰����ع�ֵ��1.2% [" + temp + "]");
				return false;
			}
		}
	}		
	return true;	
}		

//�Է���ԭֵ��˰����ع�ֵ����У�飬���߱�����һ
function checkOneoftwo( cur_number, row_msg )
{
	
	var yz = getStringLength(trimString( $F("list["+cur_number+"].alterVO.bghfcyz") ));
	var gz = getStringLength(trimString( $F("list["+cur_number+"].alterVO.bghswjggz") ));
		
	if ( (yz == 0 && gz == 0) ) {
		alert("��"+ row_msg +"�� ����ԭֵ��˰����ع�ֵ���߱�����һ!");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghfcyz");
		focus_it[0].focus();
		return false;
	}
	
	if (yz > 0) {
		if ( ! checkNumber($F("list["+cur_number+"].alterVO.bghfcyz"), 19,2,true) ) {
			alert("��"+ row_msg +"�� ����ԭֵ����Ϊ���֣�");
			var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghfcyz");
			focus_it[0].focus();
			return false;
		}	
	}
	if(gz > 0) {
		if ( ! checkNumber($F("list["+cur_number+"].alterVO.bghswjggz"), 19,2,true) ) {
			alert("��"+ row_msg +"�� ˰����ع�ֵ����Ϊ���֣�");
			var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghswjggz");
			focus_it[0].focus();
			return false;
		}	
	}
	if ( (yz > 0 && gz > 0) ) {
		if ( parseFloat( $F("list["+cur_number+"].alterVO.bghfcyz")) != 0
				&& parseFloat( $F("list["+cur_number+"].alterVO.bghswjggz")) != 0 ) {
			alert("��"+ row_msg +"�� ����ԭֵ��˰����ع�ֵ����ֻ����һ��!");
			var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.bghfcyz");
			focus_it[0].focus();
			return false;
		}
	}
	return true;
}

function checkCause( cur_number, row_msg )
{
	try {
		//�������ԭֵ�仯�󣬱�����ԭ��
		var bghfcyz = parseFloat(trimString( $F("list["+cur_number+"].alterVO.bghfcyz") ));
		
		var bgqfcyz = -1;
		if ( $("list["+cur_number+"].alterVO.bgqfcyz") ) {
			bgqfcyz = parseFloat(trimString( $F("list["+cur_number+"].alterVO.bgqfcyz") ));
		}
		
		if ( bgqfcyz != bghfcyz) {
			if ( $F("list["+cur_number+"].alterVO.ysfcyzbgyy") == "" ) {
				alert("��"+ row_msg +"�� �������ԭֵ�仯�󣬱�����ԭ��!");
				var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.ysfcyzbgyy");
				focus_it[0].focus();
				return false;
			}
		}
	}
	catch (e) { 
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
								<span class="current">���÷���</span>
								<span onclick="change_alter(2)">���ⷿ��</span> 
								<span onclick="change_alter(3)">���ⷿ��</span> 
								<span onclick="change_alter(4)">��������</span> 
								<span onclick="change_alter(5)">��������</span>
								<span onclick="change_alter(6)">��������</span>
							</div>
							<!-- ���÷��� -->
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
