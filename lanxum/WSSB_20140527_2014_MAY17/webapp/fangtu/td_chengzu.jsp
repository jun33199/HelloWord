<%@page pageEncoding="GBK"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

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
var items = xmlDoc.selectNodes("//tdChengzuList");
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
	    TABLE_LIST.rows[curRow].cells[6].firstChild.value = "������" + zlqx_name;
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

        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "czrmc", 100, null, "maxlength=100 onchange='modify()'", "����������", false, false, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTSelect, "house_number", 100, arySelect_zjlx, "need_autoselect onchange='modify()'", "֤������", false, true, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "czrzjhm", 70, null, "maxlength=20 onchange='modify()'", "������֤������", false, false, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "cztdzl", 150, null, "maxlength=200 onchange='modify()'", "������������", false, true, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTSelect, "house_number", 70, arySelect_zlqx, "need_autoselect onchange='modify()'", "��������", false, true, "70");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "tdmj", 70, null, "maxlength=20 onchange='modify()'", "�������", false, true, "100");
        <%if (wzqyFlag.equals("1")){%>
        aryColumn[aryColumn.length] = new DTColumn(constCTSelect, "sfjnws", 60, arySelect_isnot, "need_autoselect onchange='modify()'", "�Ƿ��������Ͷ����ҵ����ʹ�÷�", false, true, "100");
        <%}else{%>
      	aryColumn[aryColumn.length] = new DTColumn(constCTHideTD,"sfjnws",1,null,"", "sfjnws", false, false, "16");
      	<%}%>
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "bgqbz", 100, null, "maxlength=200 onchange='modify()'", "��ע", false, false, "100");

        dtList = new DynamicTable(TABLE_LIST, aryColumn, "dtList");
        if (dtList == null)  return;
        daList = new DataAwareTable(dtList);
        daList.setDataSource(aryDataSource);

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
		var list = ["djbh","id","czrmc","zjlxdm","czrzjhm","cztdzl","zlqxdm","tdmj","sfjnws","bz","opFlag"];
		
		var parent = g_Doc.XMLDoc.selectSingleNode("/taxdoc");
	
		var rowid = 0;
		for(var i=0;i<aryDataSource.length;i++)
		{
			  if ( aryDataSource[i][daList.DynamicTable.Columns.length] != "5") {
				  var child = g_Doc.XMLDoc.createElement("tdChengzuList");
				  
			      parent.appendChild(child);
			      
				  var tmp= g_Doc.XMLDoc.selectNodes("//tdChengzuList");
				  
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
	function check_identityCard (dataSource)
	{
		for (var i = 0; i < dataSource.length;i++)
		{
			if ( dataSource[i][4] == '02' )
			{
				if ( dataSource[i][5] != "" ) {
					if ( ! checkIdentityCard( dataSource[i][5] ) ) {
						alert("���֤�Ų��Ϸ���");
						return false;
					}
				}
			}
		}
		return true;
	  
	}

	//�������������У��
	function checkTDMJ(dataSource)
	{
		for (var i = 0; i < dataSource.length;i++)
		{
			var flag = checkNumber(trimString(dataSource[i][8]), 19, 2, true);
			if ( flag == false )
			{
				alert("�����������Ϊ���֣�");
				return false;
			}
			if(dataSource[i][8]>=50000000){
				if (!confirm("��������������Ϊ"+dataSource[i][8]+"ƽ���ף���ȷ�ϣ�")){
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
		isCheck = check_identityCard(daList.getData());
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
						<span onclick="td_ziyong()">��������</span>
						<span onclick="td_chuzu()">��������</span>
						<span class="current">��������</span>
                    </div>
					<!-- ���÷��� -->

						<div id="DIV_LIST" style="OVERFLOW: auto;">
							<table cellspacing="0" class="table-99" id="TABLE_LIST">
								<tbody>
								<tr>
									<td width="2%" class="2-td1-left">���</td>
									<td class="2-td1-left" style="display: none">�ǼǱ��</td>
									<td class="2-td1-left" style="display: none">Ψһ���</td>
									<td width="11%" align="middle" class="2-td1-left">
									����������</td>
									<td width="11%" align="middle" class="2-td1-left">
									֤������<strong><span class="bitian">*</span></strong></td>
									<td width="15%" align="middle" class="2-td1-left">
									������֤������</td>
									<td width="12%" align="middle" class="2-td1-left">
									������������<strong><span class="bitian">*</span></strong></td>
									<td width="12%" align="middle" class="2-td1-left">
									��������<strong><span class="bitian">*</span></strong></td>
									<td width="11%" align="middle" class="2-td1-center">
									�������</td>
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