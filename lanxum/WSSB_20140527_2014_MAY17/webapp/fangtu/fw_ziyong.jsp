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

	//��ʼ��
    function initPage()
    {
        MM_preloadImages('<%=static_contextpath%>images/shangyibu1.jpg', '<%=static_contextpath%>images/xiayibu1.jpg', '<%=static_contextpath%>images/b-scsq1.jpg', '<%=static_contextpath%>images/tuichu1.jpg', '<%=static_contextpath%>images/shangyibu1.jpg', '<%=static_contextpath%>images/xiayibu1.jpg', '<%=static_contextpath%>images/b-scsq1.jpg', '<%=static_contextpath%>images/tuichu1.jpg');

		setBaseInfo();

        //���� ���÷��� ����
        var aryColumn = new Array();
        aryColumn[aryColumn.length] = new DTColumn(constCTSequence, "xh", 2, null);
        aryColumn[aryColumn.length] = new DTColumn(constCTHideTD,"pid",1,null,"", "pID", false, false, "20");
		aryColumn[aryColumn.length] = new DTColumn(constCTHideTD,"id",1,null,"", "ID", false, false, "16");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "fwzl", 150, null, "maxlength=100 onchange='modify()'", "��������", false, true, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "cqzsh", 150, null, "maxlength=100 onchange='modify()'", "��Ȩ֤���", false, false, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "fcyz", 70, null, "maxlength=100 onchange='DisableElement(5,6)'", "����ԭֵ", false, false, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "swjggz", 70, null, "maxlength=100 onchange='DisableElement(6,5)'", " ˰����ع�ֵ", false, false, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "house_value", 70, null, "maxlength=100 onchange='modify()'", "������˰ԭֵ", false, false, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "house_value", 70, null, "maxlength=100 onchange='modify()'", "����Ӧ˰ԭֵ", false, false, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "house_value", 70, null, "maxlength=100 onchange='modify()'", "��Ӧ��˰��", false, true, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTSelect, "house_value", 60, arySelect_isnot, "need_autoselect onchange='modify()'", "�Ƿ����", false, true, "100");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput, "house_value", 100, null, "maxlength=100 onchange='modify()'", "��ע", false, false, "100");


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


	//���´���XmlBuild.js�е�doSubmitXML����
	function doSubmitXML(objForm,aType,isSign,xmldata,ifsubmit)
	{	
		return fangtu_doSubmitXML(objForm,aType,isSign,xmldata,ifsubmit);
	}
	
	
	
	
	//���ɷ�������xml
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

<!-- У�鴦�� -->
<script language="JavaScript">


	//�Է���ԭֵ��˰����ع�ֵ����У�飬���߱�����һ
	function oneoftwo(dataSource)
	{
		
		for (var i = 0; i < dataSource.length;i++)
		{
			var yz = getStringLength(trimString(dataSource[i][5]));
			var gz = getStringLength(trimString(dataSource[i][6]));
			
			if ( yz == 0 && gz == 0) {
				alert("����ԭֵ��˰����ع�ֵ���߱�����һ!");
				return false;
			}
			if (yz > 0)
			{
				var flag = checkNumber(trimString(dataSource[i][5]), 19, 2, true);
				if ( flag == false )
				{
					alert("����ԭֵ����Ϊ���֣�");
					return false;
				}
			}
			if(gz > 0)
			{
				var flag1 = checkNumber(trimString(dataSource[i][6]), 19, 2, true);
				if ( flag1 == false )
				{
					alert("˰����ع�ֵ����Ϊ���֣�");
					return false;
				}
			}
		}
		return true;
	}

	//����˰ԭֵ����У��
	function checkMSYZ(dataSource)
	{
		for (var i = 0; i < dataSource.length;i++)
		{
			var flag = checkNumber(trimString(dataSource[i][7]), 19, 2, true);
			if ( flag == false )
			{
				alert("��˰ԭֵ����Ϊ���֣�");
				return false;
			}

		}
		return true;
	}

	//��Ӧ˰ԭֵ����У��
	function checkYSYZ(dataSource)
	{
		for (var i = 0; i < dataSource.length;i++)
		{
			var flag = checkNumber(trimString(dataSource[i][8]), 19, 2, true);
			if ( flag == false )
			{
				alert("Ӧ˰ԭֵ����Ϊ���֣�");
				return false;
			}
			
			if ( remarkIsEmpty(dataSource, i, 11) ) {
				var ysyz = parseFloat(trimString(dataSource[i][8]));
	
				//Ӧ˰ԭֵ=����ԭֵ��˰����ع�ֵ��- ��˰ԭֵ
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
					alert("�������["+ (i-0+1) +"]��Ӧ˰ԭֵ=����ԭֵ��˰����ع�ֵ��- ��˰ԭֵ [" + rel + "]");
					return false;
				}
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
		
		
			//У�����
			//������ԭֵ���ɷ��������з��ز���˰����Ӧ��˰�Ӧ˰ԭֵ����1-30%����1.2%
			//��˰����ع�ֵ���ɷ��������з��ز���˰����Ӧ��˰�˰����ع�ֵ��1.2%
			if ( remarkIsEmpty(dataSource, i, 11 ) ) {
	
				var ysyz = parseFloat(trimString(dataSource[i][8]));
				var nynse = parseFloat(trimString(dataSource[i][9]));
					
				var zhi;
	
				if(getStringLength(trimString(dataSource[i][5])) > 0 && parseFloat(trimString(dataSource[i][5]))!=0)
				{
					zhi = parseFloat(trimString(dataSource[i][5]));
					var temp = handleFloat(ysyz*0.7*0.012);
					if ( temp != nynse ) {
						alert("���������Ӧ��˰�Ӧ˰ԭֵ����1-30%����1.2% [" + temp + "]");
						return false;
					}
				}
				else
				{
					zhi = parseFloat(trimString(dataSource[i][6]));
					var temp = handleFloat(zhi*0.012);
					if ( temp != nynse ) {
						alert("���������Ӧ��˰�˰����ع�ֵ��1.2% [" + temp + "]");
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
			//alert("�Է���ԭֵ��˰����ع�ֵ����У�����");
			return false;
		}
		isCheck = checkMSYZ(dataSource);
		if(!isCheck)
		{
			//alert("����˰ԭֵ����У�����");
			return false;
		}
		isCheck = checkYSYZ(dataSource);
		if(!isCheck)
		{
			//alert("��Ӧ˰ԭֵ����У�����");
			return false;
		}
		isCheck = checkNYNSE(dataSource);
		if(!isCheck)
		{
			//alert("����Ӧ��˰�����У�����");
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
						<div align="left" id="nav"><span class="current">���÷���</span>
						<span onclick="fw_chuzu()">���ⷿ��</span> <span
							onclick="fw_chengzu()">���ⷿ��</span> <span onclick="td_ziyong()">��������</span>
						<span onclick="td_chuzu()">��������</span> <span
							onclick="td_zhengzu()">��������</span></div>

						<!-- ���÷��� -->

						<div id="DIV_LIST" style="OVERFLOW: auto;">
						<table cellspacing="0" class="table-99" id="TABLE_LIST">
							<tbody>
								<tr>
									<td width="2%" class="2-td1-left">���</td>
									<td class="2-td1-left" style="display: none">�ǼǱ��</td>
									<td class="2-td1-left" style="display: none">Ψһ���</td>
									<td width="18%" align="middle" class="2-td1-left">��������<strong><span class="bitian">*</span></strong></td>
									<td width="16%" align="middle" class="2-td1-left">��Ȩ֤���</td>
									<td width="11%" align="middle" class="2-td1-left">����ԭֵ</td>
									<td width="12%" align="middle" class="2-td1-left">˰����ع�ֵ</td>
									<td width="9%" align="middle" class="2-td1-center">������˰ԭֵ</td>
									<td width="15%" align="middle" class="2-td1-center">����Ӧ˰ԭֵ</td>
									<td width="6%" align="middle" class="2-td1-center">��Ӧ��˰��<strong><span class="bitian">*</span></strong></td>
									<td width="5%" align="middle" class="2-td1-center">�Ƿ����<strong><span class="bitian">*</span></strong></td>
									<td width="3%" align="middle" class="2-td1-center">��ע</td>
								</tr>
							</tbody>
						</table>
						<table cellspacing="0" class="table-99" id="TABLE_SUM">
							<tbody>
								<tr>
									<td width="315" class="2-td1-left">С��</td>
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

