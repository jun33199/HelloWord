<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import ="java.util.HashMap"%>
<%@ page import ="java.util.List"%>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.kjqysds.badjb.web.BadjbForm"%>

<%
	System.out.println("1111111");
	// ��ȡBadjbForm
	BadjbForm form = (BadjbForm) request.getAttribute("badjbForm");


	// ��¼��
	int totalCount = form.getTotalCount();
	// ��ҳ��
	int totalPage = form.getTotalPage();
	// ��ǰҳ
	int currentPage = form.getCurrentPage();
	// ��¼����
	List recordList = form.getRecordList();
%>
<html>
<head>
    <title>�޸Ŀ۽���ҵ����˰��ͬ�����ǼǱ�</title>
    <link href="../../../css/text.css" rel="stylesheet" type="text/css">
    <script language="JavaScript" type="text/JavaScript" src="../../../js/smsb_common.js"></script>
    <script language="JavaScript" type="text/JavaScript" src="../../../js/treatImage.js"></script>
    <script language="JavaScript" type="text/JavaScript" src="../../../js/gmit_selectcontrol.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="../../../js/page.js"></script>
</head>

<script language="JavaScript">
// ��ʼ��ҳ����Ϣ
function doInit()
{

}

// ��ѯ�۽�����Ϣ
function doQuery()
{
	if(checkValidity()){
		document.forms[0].actionType.value = "QueryKjrRecords";
		document.forms[0].submit();
	}
}

// ���ҳ����Ϣ
function doReset()
{
    var step = false;
    if(step)
    {
        // �۽��˼��������
        document.forms[0].jsjdm.value = "";
        // �۽�����������
        document.getElementById("kjywrmc_cn").innerText = "";
        // �۽���Ӣ������
        document.forms[0].kjywrmc_en.value = "";
    }

    document.forms[0].actionType.value = "Show";
    document.forms[0].submit();
}

// У��ҳ��¼����ĺϷ���
function checkValidity()
{
	// �۽������˼��������
	if(document.forms[0].jsjdm.value == null || document.forms[0].jsjdm.value == ""){
		alert("������۽������˼�������룡");
		document.forms[0].jsjdm.focus();
		return false;
	}
    return true;
}

// ��ҳ����
function doFy(pageNo)
{
	//alert("pageNo = " + pageNo);
	var currentPage = <%=currentPage%>;
	var totalPage = <%=totalPage%>;

	//��ҳ��ѯ
    //ֱ��������תҳ�ŵĲ�ѯ����������ҳ�ŵ�У�飺
	if(totalPage > 0)
	{
		if(currentPage == 1 && pageNo <=1)
		{
			alert("��ǰ�Ѿ��ǵ�һҳ���޷����з�ҳ��");
			return false;
		}

		if(currentPage == totalPage && pageNo >= totalPage)
		{
			alert("��ǰ�Ѿ�����ĩҳ���޷����з�ҳ��");
			return false;
		}
	}
	else
	{
		return;
	}

	document.forms[0].actionType.value = "QueryKjrRecords";
	//��ǰҳ
	document.forms[0].currentPage.value = pageNo;
	//��ҳ��ָ��ǰҳ��
	document.forms[0].target = "_self";
	document.forms[0].submit();
}

// ��һ��
function doNext()
{
	
	if(isSelected(document.forms[0].dshRadio))
	{
		document.forms[0].badjxh.value = getSelectedValue(document.forms[0].dshRadio);
		document.forms[0].actionType.value = "Show";
		document.forms[0].submit();
	}
	else{
		alert("��ѡ��һ����¼���ٽ��д˲�����");
	}
}
//ɾ��
function doDelete(){
	if(isSelected(document.forms[0].dshRadio))
	{
		document.forms[0].badjxh.value = getSelectedValue(document.forms[0].dshRadio);
		document.forms[0].actionType.value = "Delete";
		doSubmitForm("Delete");
	}
	else{
		alert("��ѡ��һ����¼���ٽ��д˲�����");
	}
}

</script>

<%@ include file="../../include/header.jsp"%>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="doInit()">
<html:form action="/webapp/smsb/qysds/kjqysds/badjbAction.do" method="post">
<html:hidden property="actionType" />
<html:hidden property="badjxh" value=""/>
<html:hidden property="currentPage"/>
<html:hidden property="modifyFlag" value="true"/>


<table width="1000" align="center" cellspacing="0">
    <tr>
        <td class="1-td1"  colspan="9">�޸Ŀ۽���ҵ����˰��ͬ�����ǼǱ�</td>
    </tr>
    <tr>
        <td class="1-td2">
			<table class="table-99" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<table width="99%" border="0" align="center" cellpadding="1" cellspacing="0">
							<tr> 
								<td>
									<table class="table-100" border="1" cellspacing="0" cellpadding="0">
										<tr> 
											<td width="20%" class="2-td2-t-left">�۽������˼�������룺</td>
											<td width="30%" class="2-td2-t-left"> <div align="left">&nbsp;<html:text property="jsjdm" size="20" maxlength="8" onfocus="this.select()" /></div></td>
											<td width="20%" class="2-td2-t-left">�۽����������ƣ�</td>
											<td width="30%" class="2-td2-t-center">&nbsp;<bean:write name="badjbForm" property="kjywrxx.kjrmc_cn" scope="request" filter="true" /></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td align="center"> 
									<input name="query" type="image" id="chaxun" style="cursor:hand" accesskey="q" tabIndex="-1" onClick="doQuery();return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/chaxun2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="��ѯ" src="<%=static_contextpath%>images/chaxun1.jpg" alt="��ѯ���������ǼǱ���Ϣ" /> 
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table border="1" cellspacing="0" class="table-99" align="center" >
							<tr> 
								<td width="6%" align="center" class="2-td2-t-left">&nbsp;</td>
								<td width="22%" align="center" class="2-td2-t-left"><strong>��ͬ����</strong></td>
								<td width="22%" align="center" class="2-td2-t-left"><strong>��ͬ���</strong></td>
								<td width="10%" align="center" class="2-td2-t-left"><strong>��ͬǩԼ����</strong></td>
								<td width="10%" align="center" class="2-td2-t-left"><strong>��ͬ��Ч��</strong></td>
								<td width="10%" align="center" class="2-td2-t-left"><strong>��ͬ���</strong></td>
								<td width="10%" align="center" class="2-td2-t-left"><strong>�����</strong></td>
								<td width="10%" align="center" class="2-td2-t-center"><strong>���״̬</strong></td>
							</tr>
              <%
						// ��ʾ��ѯ���
						if(recordList != null && recordList.size() > 0)
						{
							for(int i = 0; i < recordList.size(); i++)
							{
								// ��ȡList��װ����
								HashMap map = (HashMap) recordList.get(i);
					%>
							<tr> 
								<td class="2-td2-left">&nbsp; <input type="radio" name="dshRadio" value="<%=(String) map.get("badjxh")%>"/></td>
								<td class="2-td2-left">&nbsp;<%=(String) map.get("htmc")%></td>
								<td class="2-td2-left">&nbsp;<%=(String) map.get("htbh")%></td>
								<td class="2-td2-left">&nbsp;<%=(String) map.get("htqyrq")%></td>
								<td class="2-td2-left">&nbsp;<%=(String) map.get("htyxq")%></td>
								<td class="2-td2-left">&nbsp;<%=(String) map.get("htje")%></td>
								<td class="2-td2-left">&nbsp;<%=(String) map.get("tbrq")%></td>
								<td class="2-td2-center">&nbsp;<%=(String) map.get("shzt")%></td>
							</tr>
              <%
							}
						}
					%>
            </table>
						<table class="table-100" border="0">
							<tr class="2-td2-t-left">
								<td align="left">&nbsp;&nbsp;��¼����<%=totalCount%>&nbsp;&nbsp;&nbsp; ��ǰҳ/��ҳ����<%=currentPage%>/<%=totalPage%> &nbsp; </td>
								<td align="right" >
									<img onclick="doFy(1)" onMouseOver="MM_swapImage('dy1','','<%=static_contextpath%>/images/diyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="��һҳ" id="dy1" src="<%=static_contextpath%>/images/diyiye1.jpg" width="79" height="22" style="cursor:hand" tabIndex="-1"/> 
									<img onclick="doFy(<%=currentPage-1%>)" onMouseOver="MM_swapImage('sy1','','<%=static_contextpath%>/images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="��һҳ" id="sy1" src="<%=static_contextpath%>/images/shangyiye1.jpg" width="79" height="22" style="cursor:hand" tabIndex="-1"/> 
									<img onclick="doFy(<%=currentPage+1%>)" onMouseOver="MM_swapImage('xy1','','<%=static_contextpath%>/images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="��һҳ" id="xy1" src="<%=static_contextpath%>/images/xaiyiye1.jpg" width="79" height="22" style="cursor:hand" tabIndex="-1"/> 
									<img onclick="doFy(<%=totalPage%>)" onMouseOver="MM_swapImage('zm1','','<%=static_contextpath%>/images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="��ĩҳ" id="zm1" src="<%=static_contextpath%>/images/zuimoye1.jpg" width="79" height="22" style="cursor:hand" tabIndex="-1"/> 
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>   
		</TD>
    </TR>
    <TR class="black9">
        <TD>
            <div align="center">
                <img style="cursor:hand"
                    tabIndex="-1" onClick="doNext();return false;" accesskey="n"
                    value="��һ��" alt="��һ������"
                    onMouseOver="MM_swapImage('xiayibu','','<%=static_contextpath%>images/xiayibu2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()"
                    src="<%=static_contextpath%>images/xiayibu1.jpg"
                    id="xiayibu" tabIndex="-1"/>
                    &nbsp;&nbsp;
                 <img style="cursor:hand"
                    tabIndex="-1" onClick="doDelete();return false;" accesskey="s"
                    value="ɾ��" alt="ɾ��"
                    onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/shanchu2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()"
                    src="<%=static_contextpath%>images/shanchu1.jpg"
                    id="shanchu" tabIndex="-1"/>
                &nbsp;&nbsp;
                <img value="����" alt="���ص���ҳ��"
                    style="cursor:hand" tabIndex="-1" onClick="tuichu();return false;"
                    onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()"
                    src="<%=static_contextpath%>images/fanhui1.jpg"
                    id="fanhui" tabIndex="-1"/>
            </div>
        </TD>
    </TR>
</TABLE>
<br/>
<br/>
<br/>
<%@ include file="../../include/footernew.jsp"%>
</html:form>

</body>
</html>
