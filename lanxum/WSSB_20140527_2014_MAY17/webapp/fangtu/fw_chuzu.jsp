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
var items = xmlDoc.selectNodes("//fwChuzuList");
var aryDataSource=new Array();
for(i=0;i<items.length;i++){
	var ary1=new Array();
	ary1[ary1.length]="";
	for(j=0;j<items[i].childNodes.length-1;j++){		
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

        //���� ���ⷿ�� ����
        var aryColumn = new Array();
        aryColumn[aryColumn.length] = new DTColumn(constCTSequence, "xh", 2, null);
        aryColumn[aryColumn.length] = new DTColumn(constCTHideTD,"pid",1,null,"", "pID", false, false, "20");
		aryColumn[aryColumn.length] = new DTColumn(constCTHideTD,"id",1,null,"", "ID", false, false, "16");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "fwzl", 150, null, "maxlength=200 onchange='modify()'", "��������", false, true, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "cqzsh", 150, null, "maxlength=30 onchange='modify()'", "��Ȩ֤���", false, false, "100");
		aryColumn[aryColumn.length] = new DTColumn(constCTInput, "czfwyz", 100, null, "maxlength=20 onchange='smodify()'", "���ⷿ��ԭֵ", false, false, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "nzjsr", 100, null, "maxlength=20 onchange='smodify()'", "���������", false, true, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "nynse", 100, null, "maxlength=20 onchange='modify()'", " ��Ӧ��˰��", false, true, "100");
		aryColumn[aryColumn.length] = new DTColumn(constCTSelect, "xgrczbs", 60, arySelect_isnot, "need_autoselect onchange='selectXgrczbs()'", "�Ƿ��г��۸�����˳������ھ�ס��ס��", false, true, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "bz", 100, null, "maxlength=200 onchange='modify()'", "��ע", false, false, "100");


        dtList = new DynamicTable(TABLE_LIST, aryColumn, "dtList");
        if (dtList == null)  return;
        daList = new DataAwareTable(dtList);
        daList.setDataSource(aryDataSource);

    }

	/*ѡ�� ����˳��� ʱ����*/
	function selectXgrczbs(){
		var i_row = daList.DynamicTable.CurrentRow;

		var data = TABLE_LIST.rows[i_row].cells[8].firstChild.value;
		var nzjsr = TABLE_LIST.rows[i_row].cells[6].firstChild.value;
		
		if(data=="0"){
			/*0---��ʾ ��*/
			var temp = handleFloat(parseFloat(trimString(nzjsr))*0.04);
			TABLE_LIST.rows[i_row].cells[7].firstChild.value = temp;
			alert("��������ҵ��λ����������Լ�������֯���г��۸�����˳������ھ�ס��ס��������4����˰�����շ���˰��\n\n�ļ����ݣ���˰��2008��24��");
		}else{
			/*1---��ʾ ��*/
			var temp = handleFloat(parseFloat(trimString(nzjsr))*0.12);
			TABLE_LIST.rows[i_row].cells[7].firstChild.value = temp;
		}
		
		modify();
		
	}
	
	function smodify()
	{
		daList.modifyRow( daList.DynamicTable.CurrentRow);

		//modifyNYNSE();
	}

    function showAlertMessages()
    {

    }

	function modifyNYNSE()
	{
		var i_row = daList.DynamicTable.CurrentRow;
		var data1 = TABLE_LIST.rows[i_row].cells[5].firstChild.value;
		var nzjsr_f = checkNumber(data1, 19, 2);
		if(!nzjsr_f)
		{
			alert("������������Ϊ���֣�");
			return;
		}
		var temp = handleFloat(parseFloat(trimString(data1))*0.12);
		TABLE_LIST.rows[i_row].cells[6].firstChild.value = temp;
	}
	
	
	//���´���XmlBuild.js�е�doSubmitXML����
	function doSubmitXML(objForm,aType,isSign,xmldata,ifsubmit)
	{	
		return fangtu_doSubmitXML(objForm,aType,isSign,xmldata,ifsubmit);
	}
	
	
	
	
	//���ɷ�������xml
	function getFangtuInfo(objForm)
	{   
		var list = ["djbh","id","fwzl","cqzsh","czfwyz","nzjsr","nynse","xgrczbs","bz","opFlag"];
		
		var parent = g_Doc.XMLDoc.selectSingleNode("/taxdoc");
	
		var rowid = 0;
		for(var i=0;i<aryDataSource.length;i++)
		{
			if ( aryDataSource[i][daList.DynamicTable.Columns.length] != "5") {
				  var child = g_Doc.XMLDoc.createElement("fwChuzuList");
				  
			      parent.appendChild(child);
			      
				  var tmp= g_Doc.XMLDoc.selectNodes("//fwChuzuList");
				  
				  for(var j=0;j<list.length;j++){
				  	
					 var tagName = list[j];
					 var value = aryDataSource[i][j+1];
					 
					 var node2 = g_Doc.XMLDoc.createElement( tagName );
		             tmp[rowid].appendChild( node2 );
					 var objCDATA = g_Doc.XMLDoc.createCDATASection(value)
					 node2.appendChild(objCDATA);
				  }
				 
				 rowid = rowid + 1;
			 }
				 
		}
	}	
</script>

<!-- У�鴦�� -->
<script language="JavaScript">


	function check_nynse (dataSource)
	{
		for (var i = 0; i < dataSource.length;i++)
		{
			var czfwyz_f= checkNumber(trimString(dataSource[i][5]), 19, 2, true);
			var nzjsr_f = checkNumber(trimString(dataSource[i][6]), 19, 2, true);
			var nynse_f = checkNumber(trimString(dataSource[i][7]), 19, 2, true);
			if ( czfwyz_f == false )
			{
				alert("�� "+parseInt(i+1)+" �� ���ⷿ��ԭֵ�����Ϊ���֣�");
				return false;
			}
			if ( nzjsr_f == false )
			{
				alert("�� "+parseInt(i+1)+" �� ������������Ϊ���֣�");
				return false;
			}
			if ( nynse_f == false )
			{
				alert("�� "+parseInt(i+1)+" �� ��Ӧ��˰�����Ϊ���֣�");
				return false;
			}
			
			if ( remarkIsEmpty(dataSource, i, 9 ) ) {
				var xgrczbs=dataSource[i][8];
				
				if(xgrczbs=="0"){
					/* �� */
					if(parseInt(dataSource[i][5])==0||trimString(dataSource[i][5])==""){
						alert("�� "+parseInt(i+1)+" �� ѡ���г��۸�����˳������ھ�ס��ס������ѡ���ǡ��󣬱�����д���ⷿ��ԭֵ��");
						return false;
					}
					
					var temp = handleFloat(parseFloat(trimString(dataSource[i][6]))*0.04);
					var nynse = parseFloat(trimString(dataSource[i][7]));
					if ( temp != nynse )
					{
						alert("�� "+parseInt(i+1)+" �� ��Ӧ��˰��=���������*4% ("+temp+")�����г��۸�����˳������ھ�ס��ס����˰��Ϊ4%��");
						return false;
					}
						
				}else{
					/* �� */
					var temp = handleFloat(parseFloat(trimString(dataSource[i][6]))*0.12);
					var nynse = parseFloat(trimString(dataSource[i][7]));
					if ( temp != nynse )
					{
						alert("�� "+parseInt(i+1)+" �� ��Ӧ��˰��=���������*12% ("+temp+")");
						return false;
					}
				}
				
			}
		}
		return true;
	  
	}

	function checkSpecial(dataSource)
	{
		var isCheck = check_nynse(dataSource);
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
						<span class="current">���ⷿ��</span>
						<span onclick="fw_chengzu()">���ⷿ��</span>
						<span onclick="td_ziyong()">��������</span>
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
									<td width="18%" align="middle" class="2-td1-left">��������<strong><span class="bitian">*</span></strong></td>
									<td width="16%" align="middle" class="2-td1-left">
									��Ȩ֤���</td>
									<td width="16%" align="middle" class="2-td1-left">
									���ⷿ��ԭֵ</td>
									<td width="11%" align="middle" class="2-td1-left">
									���������<strong><span class="bitian">*</span></strong></td>
									<td width="12%" align="middle" class="2-td1-left">
									��Ӧ��˰��<strong><span class="bitian">*</span></strong></td>
									<td width="16%" align="middle" class="2-td1-left">
									�Ƿ��г��۸�����˳������ھ�ס��ס��</td>
									<td width="3%" align="middle" class="2-td1-center">
									��ע</td>
								</tr>
							</tbody>
							</table>
							<table cellspacing="0" class="table-99" id="TABLE_SUM">
								<tbody>
								<tr>
									<td width="390" class="2-td1-left">С��</td>
									<td width="120" align="middle" bordercolor="#9BB4CA" bgcolor="#E7EEF1" class="2-td1-left">
										<fmt:formatNumber value="${fwcz_total.nzjsr}" pattern="0.00"/>
									</td>
									<td width="125" align="middle" bordercolor="#9BB4CA" bgcolor="#E7EEF1" class="2-td1-left">
										<fmt:formatNumber value="${fwcz_total.nynse}" pattern="0.00"/>
									</td>
									<td align="middle" bordercolor="#9BB4CA" bgcolor="#E7EEF1" class="2-td1-center">
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