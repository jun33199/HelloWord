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
	
	/*������ҵ��ʶ 1-�� 0-��*/
	String wzqyFlag=(String)request.getAttribute("WZQY_FLAG");
%>

<html>

<%@ include file="./common/header.jsp"%>

<script>

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


//Load xml ��Ϣ
xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
xmlDoc.async = false;
xmlDoc.loadXML(strXml);

//��Ԫ��
var rootNode = xmlDoc.documentElement;

//��������
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


	//����add
	function add(handler)
	{
	    yhlistlent = yhlistlent + 1;
	    var oTable = eval(handler);
	    oTable.appendRow();
	    oTable.focusAt(oTable.getCurrentRow(), 1);
	    var curRow = oTable.getCurrentRow();
	    TABLE_LIST.rows[curRow].cells[3].firstChild.value = "������" + zlqx_name;
	}

    function initPage()
    {
        MM_preloadImages('<%=static_contextpath%>images/shangyibu1.jpg', '<%=static_contextpath%>images/xiayibu1.jpg', '<%=static_contextpath%>images/b-scsq1.jpg', '<%=static_contextpath%>images/tuichu1.jpg', '<%=static_contextpath%>images/shangyibu1.jpg', '<%=static_contextpath%>images/xiayibu1.jpg', '<%=static_contextpath%>images/b-scsq1.jpg', '<%=static_contextpath%>images/tuichu1.jpg');

		setBaseInfo();
		
        //���� ���÷��� ����
        var aryColumn = new Array();
        aryColumn[aryColumn.length] = new DTColumn(constCTSequence, "xh", 2, null);
		aryColumn[aryColumn.length] = new DTColumn(constCTHideTD,"pid",1,null,"", "pID", false, false, "20");
		aryColumn[aryColumn.length] = new DTColumn(constCTHideTD,"id",1,null,"", "ID", false, false, "16");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "tdzl", 150, null, "maxlength=200 onchange='modify()'", "��������", false, true, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "tdzsh", 120, null, "maxlength=30 onchange='modify()'", "����֤���", false, false, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "tdmj", 70, null, "maxlength=20 onchange='modify()'", "�������", false, false, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "qzmsmj", 70, null, "maxlength=20 onchange='modify()'", " ������˰���", false, false, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "qzysmj", 70, null, "maxlength=20 onchange='modify()'", "����Ӧ˰���", false, false, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTSelect, "mpfmse", 70, arySelect_sl, "need_autoselect onchange='modify()'", "ÿƽ����˰��", false, true, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "nynse", 70, null, "maxlength=20 onchange='modify()'", "��Ӧ��˰��", false, true, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTSelect, "sfdj", 60, arySelect_isnot, "need_autoselect onchange='modify()'", "�Ƿ����", false, true, "100");
        <%if (wzqyFlag.equals("1")){%>
        aryColumn[aryColumn.length] = new DTColumn(constCTSelect, "sfjnws", 60, arySelect_isnot, "need_autoselect onchange='modify()'", "�Ƿ��������Ͷ����ҵ����ʹ�÷�", false, true, "100");
      	<%}else{%>
      	aryColumn[aryColumn.length] = new DTColumn(constCTHideTD,"sfjnws",1,null,"", "sfjnws", false, false, "16");
      	<%}%>
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "bz", 100, null, "maxlength=200 onchange='modify()'", "��ע", false, false, "100");


        dtList = new DynamicTable(TABLE_LIST, aryColumn, "dtList");
        if (dtList == null)  return;
        daList = new DataAwareTable(dtList);
        daList.setDataSource(aryDataSource);

    }


	function calculateYSMJ()
	{
		modify();

		var i_row = daList.DynamicTable.CurrentRow;
		//�������
		var data1 = TABLE_LIST.rows[i_row].cells[5].firstChild.value;
		if(getStringLength(trimString(data1)) == 0){
			return;
		}
		else
		{
			var flag = checkNumber(trimString(data1), 19, 2, true);
			if ( flag == false )
			{
				alert("�����������Ϊ���֣�");
				return;
			}
		}

		//��˰���
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
				alert("��˰�������Ϊ���֣�");
				return;
			}
		}

		//����Ӧ˰���
		var data4 = handleFloat(parseFloat(data1)-parseFloat(data2));
		TABLE_LIST.rows[i_row].cells[7].firstChild.value = data4;

		//ÿƽ����˰��
		var data3 = TABLE_LIST.rows[i_row].cells[8].firstChild.value;
		if(getStringLength(trimString(data3)) == 0){
			return;
		}
		else
		{
			var flag3 = checkNumber(trimString(data3), 19, 2, true);
			if ( flag3 == false )
			{
				alert("ÿƽ����˰�����Ϊ���֣�");
				return;
			}
		}

		//������Ӧ��˰��
		TABLE_LIST.rows[i_row].cells[9].firstChild.value = handleFloat(data4*parseFloat(data3));
	}

	function calculateNYNSE()
	{
		modify();

		var i_row = daList.DynamicTable.CurrentRow;
		//Ӧ˰���
		var data4 = TABLE_LIST.rows[i_row].cells[7].firstChild.value;
		if(getStringLength(trimString(data4)) == 0){
			return;
		}
		else
		{
			var flag4 = checkNumber(trimString(data4), 19, 2, true);
			if ( flag4 == false )
			{
				alert("Ӧ˰�������Ϊ���֣�");
				return;
			}
		}

		//ÿƽ����˰��
		var data3 = TABLE_LIST.rows[i_row].cells[8].firstChild.value;
		if(getStringLength(trimString(data3)) == 0){
			return;
		}
		else
		{
			var flag3 = checkNumber(trimString(data3), 19, 2, true);
			if ( flag3 == false )
			{
				alert("ÿƽ����˰�����Ϊ���֣�");
				return;
			}
		}

		//������Ӧ��˰��
		TABLE_LIST.rows[i_row].cells[9].firstChild.value = handleFloat(parseFloat(data4)*parseFloat(data3));
	}

    function showAlertMessages()
    {

    }

	//���´���XmlBuild.js�е�doSubmitXML����
	function doSubmitXML(objForm,aType,isSign,xmldata,ifsubmit)
	{	
		return fangtu_doSubmitXML(objForm,aType,isSign,xmldata,ifsubmit);
	}
	
	
	
	
	//���ɷ�������xml
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


<!-- У�鴦�� -->
<script language="JavaScript">


	//�������������У��
	function checkTDMJ(dataSource)
	{
		for (var i = 0; i < dataSource.length;i++)
		{
			var flag = checkNumber(trimString(dataSource[i][5]), 19, 2, true);
			if ( flag == false )
			{
				alert("�����������Ϊ���֣�");
				return false;
			}
			if(dataSource[i][5]>=50000000){
				if (!confirm("��������������Ϊ"+dataSource[i][5]+"ƽ���ף���ȷ�ϣ�")){
					return false;
				}
			}
		}
		return true;
	}

	//��������˰�������У��
	function checkQZMSMJ(dataSource)
	{
		for (var i = 0; i < dataSource.length;i++)
		{
			var flag = checkNumber(trimString(dataSource[i][6]), 19, 2, true);
			if ( flag == false )
			{
				alert("������˰�������Ϊ���֣�");
				return false;
			}
		}
		return true;
	}

	//������Ӧ˰�������У��
	function checkQZYSMJ(dataSource)
	{
		for (var i = 0; i < dataSource.length;i++)
		{
			var flag = checkNumber(trimString(dataSource[i][7]), 19, 2, true);
			if ( flag == false )
			{
				alert("����Ӧ˰�������Ϊ���֣�");
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
					alert("����Ӧ˰���=�������-������˰��� ("+temp+")");
					return false;
				}
			}
		}
		return true;
	}

	//��ÿƽ����˰�����У��
	function checkMPFMSE(dataSource)
	{
		for (var i = 0; i < dataSource.length;i++)
		{
			var flag = checkNumber(trimString(dataSource[i][8]), 19, 2, true);
			if ( flag == false )
			{
				alert("ÿƽ����˰�����Ϊ���֣�");
				return false;
			}
		}
		return true;
	}

	//����Ӧ��˰�����У��
	function checkNYNSE(dataSource)
	{
		for (var i = 0; i < dataSource.length;i++)
		{
			var flag = checkNumber(trimString(dataSource[i][9]), 19, 2, true);
			if ( flag == false )
			{
				alert("��Ӧ��˰�����Ϊ���֣�");
				return false;
			}

			if ( remarkIsEmpty(dataSource, i, 12 ) ) {
				var qzysmj = parseFloat(trimString(dataSource[i][7]));
				var mpfmse = parseFloat(trimString(dataSource[i][8]));
				var temp = handleFloat(qzysmj*mpfmse);
				var nynse = parseFloat(trimString(dataSource[i][9]));
				if ( temp != nynse )
				{
					alert("��Ӧ��˰��=����Ӧ˰���*ÿƽ����˰�� ("+temp+")");
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
				<font size=6pt color="#ff0000"><strong>˵����</strong></font><br>
				<font size=6pt color="#ff0000"><strong>1���ڱ�ҳ�������ԵǼ��µķ��ݡ�����˰Դ��Ϣ�����޸��ѵǼǵ�δ���˹���˰Դ��Ϣ��</strong></font><br>
				<font size=6pt color="#ff0000"><strong>2�������Ҫ�鿴���޸ľ�˰�������˺��˰Դ��Ϣ������빦�ܲ˵����ǼǷ������ر����� ��</strong></font><br>
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
						<span onclick="fw_ziyong()">���÷���</span>
						<span onclick="fw_chuzu()">���ⷿ��</span>
						<span onclick="fw_chengzu()">���ⷿ��</span>
						<span class="current">��������</span>
						<span onclick="td_chuzu()">��������</span>
						<span onclick="td_zhengzu()">��������</span>
                    </div>
					<!-- ���÷��� -->

						<div id="DIV_LIST" style="OVERFLOW: auto;">
							<table cellspacing="0" class="table-99" id="TABLE_LIST">
								<tbody>
								<tr>
									<td width="2%" class="2-td1-left">���</td>
									<td class="2-td1-left" style="display: none">�ǼǱ��</td>
									<td class="2-td1-left" style="display: none">Ψһ���</td>
									<td width="18%" align="middle" class="2-td1-left">
									��������<strong><span class="bitian">*</span></strong></td>
									<td width="12%" align="middle" class="2-td1-left">
									����֤���</td>
									<td width="11%" align="middle" class="2-td1-left">
									�������</td>
									<td width="12%" align="middle" class="2-td1-left">
									������˰���</td>
									<td width="9%" align="middle" class="2-td1-center">
									����Ӧ˰���</td>
									<td width="15%" align="middle" class="2-td1-center">
									ÿƽ����˰��<strong><span class="bitian">*</span></strong></td>
									<td width="6%" align="middle" class="2-td1-center">
									��Ӧ��˰��<strong><span class="bitian">*</span></strong></td>
									<td width="5%" align="middle" class="2-td1-center">
									�Ƿ����<strong><span class="bitian">*</span></strong></td>
									<%if (wzqyFlag.equals("1")){%>
									<td width="15%" align="middle" class="2-td1-center">
									�Ƿ��������Ͷ����ҵ����ʹ�÷�<strong><span class="bitian">*</span></strong></td>
									<%}else{%>
									<td class="2-td1-left" style="display: none">�Ƿ��������Ͷ����ҵ����ʹ�÷�</td>
									<%}%>
									<td width="3%" align="middle" class="2-td1-center">
									��ע</td>
								</tr>
							</tbody>
							</table>
							<table cellspacing="0" class="table-99" id="TABLE_SUM">
								<tbody>
								<tr>
									<td width="285" class="2-td1-left">С��</td>
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