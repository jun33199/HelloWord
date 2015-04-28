<%@page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="shtml" %>
<%@ taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ page import ="java.util.HashMap"%>
<%@ page import="com.ttsoft.bjtax.shenbao.sbzl.kjqysds.KjqysdsConstant"%>
<%@ page import="com.ttsoft.common.model.UserData"%>

<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
	System.out.println("menu url is ::" + (String)request.getSession().getAttribute(com.ttsoft.common.util.SessionKey.PARAM_MENU));
%>
<html>
<head>
<title>�۽���ҵ����˰�����ǼǱ�</title>
	<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" type = "text/JavaScript" src="js/function.js"></script>
	<script language="JavaScript" type = "text/JavaScript" src="js/gmit_selectcontrol.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="gojs/KjqysdsXmlBuild.js"></script>
	<style>
		.1-t-td2 {
			font-size: 9pt;
			color: #3C5564;
			background-color: f3f3f3;
			text-align: center;
			border-top: 1px solid #336699;
			border-right: 1px solid #336699;
			border-bottom: 1px solid #336699;
			border-left: 1px solid #336699;
			clip:  rect(-1px auto auto auto);
		}
		.2-td2-left-qysds1 {
			font-size: 9pt;
			line-height: 26px;
			color: #3E6071;
			text-align: left;
			border-top: 0px solid;
			border-right: 0px solid;
			border-bottom: 1px solid #9BB4CA;
			border-left: 1px solid #9BB4CA;
			background-color: #F3F5F8;
			padding-left: 20px;

		}
		.2-td2-t-left-qysds1 {
			font-size: 9pt;
			line-height: 26px;
			color: #3E6071;
			text-align: left;
			border-top: 1px solid #9BB4CA;
			border-right: 0px solid #9BB4CA;
			border-bottom: 1px solid #9BB4CA;
			border-left: 1px solid #9BB4CA;
			background-color: #F3F5F8;
			vertical-align: middle;
			padding-left: 20px;

		}
	</style>
</head>

<script language=JavaScript>
<%
	String containerName = "";
    UserData userdata = (UserData) session.getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA);
    if (userdata.getCaflag())
    {
		containerName = userdata.getCert().getContainerName();
    }
    else
    {
	    containerName = "";
    }

    // ��������б�
    if(session.getAttribute(KjqysdsConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GJDQ) != null)
    {
        out.print("var arySelect_gjdq = new Array(");
        String[][] gjdq = (String[][])session.getAttribute(KjqysdsConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GJDQ);
        for(int i = 0 ; i < gjdq.length ; i++)
        {
            if(i != 0)
            {
				if(gjdq[i][0] != null)
				{
					out.print(",[\"" + gjdq[i][0] + "\",\"" +gjdq[i][1] +"\"]");
				}
            }
            else
            {
                out.print("[\"" + gjdq[i][0] + "\",\"" +gjdq[i][1] +"\"]");
            }
        }
        out.println(");");
    }

	// ��������б�
    if(session.getAttribute(KjqysdsConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BZ) != null)
    {
        out.print("var arySelect_bz = new Array(");
        String[][] bz = (String[][])session.getAttribute(KjqysdsConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BZ);
        for(int i = 0 ; i < bz.length ; i++)
        {
            if(i != 0)
            {
                out.print(",[\"" + bz[i][0] + "\",\"" +bz[i][1] +"\"]");
            }
            else
            {
                out.print("[\"" + bz[i][0] + "\",\"" +bz[i][1] +"\"]");
            }
        }
        out.println(");");
    }
%>
g_objSI.Container = "<%=containerName%>";
var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMAVersion = '<%=request.getAttribute("CA_XML_SCHEMA_VERSION")==null?"":request.getAttribute("CA_XML_SCHEMA_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var xmlDoc;

//����xml
function parseXmlOnLoad()
{
	//var urlXSL="/XSLTWEB/model/010031/XSLT/" +strXSLTVersion+".xsl";
	var urlXSL="/XSLTWEB/model/010032/XSLT/badjblr.xsl";
    if (strXml != "")
    {
        if (! parseXml(strXml,urlXSL,"badjb_output"))
            return false;
    }

	doInit()
	//alert(document.getElementById("badjb_output").innerHTML);
	//document.getElementById("badjb_output").style.display = "";
    return true;
}

// ��ʼ��ҳ����Ϣ
function doInit()
{
	// ��ʼ�����������˵�
	_addOptionsToSelect(document.forms[0].fjmgbSelect, arySelect_gjdq);
	// ��ʼ�����������˵�
	_addOptionsToSelect(document.forms[0].bzSelect, arySelect_bz);

	//���������˵���ʼֵ
	var fjmgbdm = document.forms[0].fjmgbdm.value;
	if(null != fjmgbdm && "" != fjmgbdm)
	{
		_selectOptionByValue(document.forms[0].fjmgbSelect, fjmgbdm);
	}

	var bzdm = document.forms[0].bzdm.value;
	if(null != bzdm && "" != bzdm)
	{
		_selectOptionByValue(document.forms[0].bzSelect, bzdm);
	}
	// ��ʼ����ѡ��
	changeRadio(document.forms[0].fjmgbSelect.value);
}

// �س���ѯ��˰����Ϣ
function doJsjdmQuery()
{
	if(event.keyCode==13) doQuery();
}

// ��ѯ�۽�����Ϣ
function doQuery()
{
	document.forms[0].actionType.value = "QueryKjrInfo";
    document.forms[0].submit();
}

// ���ҳ����Ϣ
function doReset()
{	
	document.forms[0].actionType.value = "Show";
    document.forms[0].submit();
}

// У��ҳ��¼����ĺϷ���
function checkValidity()
{
	// �۽���Ӣ������
	if(document.getElementById("kjywrmc_en").value == null || document.getElementById("kjywrmc_en").value == "")
	{
		alert("������۽�������Ӣ�����ƣ�");
		document.getElementById("kjywrmc_en").focus();
		return false;
	}
	//�޸�ʱ��2010-12-06  author:guoxj
	//�۽�Ӣ�����ƹ���������ʾ
	if(document.getElementById("kjywrmc_en").value!=null)
	{
		var kjywrmc = document.getElementById("kjywrmc_en").value;
	     if(kjywrmc.length>50){
			 alert("����۽�������Ӣ�����ƹ�����");
			document.getElementById("kjywrmc_en").focus();
			return false;
		}else{
		   return true;
		}
	}
	// �۽��˲�������
	if(document.getElementById("kjywrcwfzr").value == null || document.getElementById("kjywrcwfzr").value == "")
	{
		alert("������۽������˲������ˣ�");
		document.getElementById("kjywrcwfzr").focus();
		return false;
	}
	// �۽�����ϵ��
	if(document.getElementById("kjywrlxr").value == null || document.getElementById("kjywrlxr").value == "")
	{
		alert("������۽���������ϵ�ˣ�");
		document.getElementById("kjywrlxr").focus();
		return false;
	}
	// �۽��˵绰
	if(document.getElementById("kjywrdh").value == null || document.getElementById("kjywrdh").value == "")
	{
		alert("������۽������˵绰��");
		document.getElementById("kjywrdh").focus();
		return false;
	}
	// �۽��˴���
	if(document.getElementById("kjywrcz").value == null || document.getElementById("kjywrcz").value == "")
	{
		alert("������۽������˴��棡");
		document.getElementById("kjywrcz").focus();
		return false;
	}

	// �Ǿ�����ҵ��������
	if(document.getElementById("fjmqymc_cn").value == null || document.getElementById("fjmqymc_cn").value == "")
	{
		alert("������Ǿ�����ҵ�������ƣ�");
		document.getElementById("fjmqymc_cn").focus();
		return false;
	}
	// �Ǿ�����ҵӢ������
	if(document.getElementById("fjmqymc_en").value == null || document.getElementById("fjmqymc_en").value == "")
	{
		alert("������Ǿ�����ҵӢ�����ƣ�");
		document.getElementById("fjmqymc_en").focus();
		return false;
	}
	// �Ǿ�����ҵ���һ����
	if(document.getElementById("gat").checked == false && document.getElementById("wg").checked == false)
	{
		alert("��ѡ��Ǿ�����ҵ�Ĺ��һ������");
		document.getElementById("gat").focus();
		return false;
	}
	// �Ǿ�����ҵ�������ַ�����ģ�
	if(document.getElementById("fjmqyjmgdz_cn").value == null || document.getElementById("fjmqyjmgdz_cn").value == "")
	{
		alert("������Ǿ�����ҵ���������ĵ�ַ��");
		document.getElementById("fjmqyjmgdz_cn").focus();
		return false;
	}
	// �Ǿ�����ҵ�������ַ��Ӣ�ģ�
	if(document.getElementById("fjmqyjmgdz_en").value == null || document.getElementById("fjmqyjmgdz_en").value == "")
	{
		alert("������Ǿ�����ҵ������Ӣ�ĵ�ַ��");
		document.getElementById("fjmqyjmgdz_en").focus();
		return false;
	}
	// �Ǿ�����ҵ��������
	if(document.getElementById("fjmqycwfzr").value == null || document.getElementById("fjmqycwfzr").value == "")
	{
		alert("������Ǿ�����ҵ�������ˣ�");
		document.getElementById("fjmqycwfzr").focus();
		return false;
	}
	// �Ǿ�����ҵ��ϵ��
	if(document.getElementById("fjmqylxr").value == null || document.getElementById("fjmqylxr").value == "")
	{
		alert("������Ǿ�����ҵ��ϵ�ˣ�");
		document.getElementById("fjmqylxr").focus();
		return false;
	}
	// �Ǿ�����ҵ�绰
	if(document.getElementById("fjmqydh").value == null || document.getElementById("fjmqydh").value == "")
	{
		alert("������Ǿ�����ҵ�绰��");
		document.getElementById("fjmqydh").focus();
		return false;
	}	
	// �Ǿ�����ҵ����
	if(document.getElementById("fjmqycz").value == null || document.getElementById("fjmqycz").value == "")
	{
		alert("������Ǿ�����ҵ���棡");
		document.getElementById("fjmqycz").focus();
		return false;
	}

	// ��ͬ��Э������
	if(document.getElementById("htxymc").value == null || document.getElementById("htxymc").value == "")
	{
		alert("�������ͬ��Э�����ƣ�");
		document.getElementById("htxymc").focus();
		return false;
	}
	// ��ͬ���
	if(document.getElementById("htxybh").value == null || document.getElementById("htxybh").value == "")
	{
		alert("�������ͬ��ţ�");
		document.getElementById("htxybh").focus();
		return false;
	}
	// ��ͬǩԼ����
	if(document.getElementById("htqyrq").value == null || document.getElementById("htqyrq").value == "")
	{
		alert("�������ͬǩԼ���ڣ�");
		document.getElementById("htqyrq").focus();
		return false;
	}
	// У���ͬǩԼ����
	//if(!checkQyrq(document.getElementById("htqyrq")))
	//{
		//return false;
	//}
	// ��ͬ��Ч����
	if(document.getElementById("htyxqx").value == null || document.getElementById("htyxqx").value == "")
	{
		alert("�������ͬ��Ч���ޣ�");
		document.getElementById("htyxqx").focus();
		return false;
	}
	// ��ͬ���
	if(document.getElementById("htzje").value == null || document.getElementById("htzje").value == "")
	{
		alert("�������ͬ��");
		document.getElementById("htzje").focus();
		return false;
	}
	// ֧����Ŀ
	if(document.getElementById("htzfxm").value == null || document.getElementById("htzfxm").value == "")
	{
		alert("�������֧ͬ����Ŀ��");
		document.getElementById("htzfxm").focus();
		return false;
	}
	// �������
	if(document.getElementById("htfkcs").value == null || document.getElementById("htfkcs").value == "")
	{
		alert("�������ͬ���������");
		document.getElementById("htfkcs").focus();
		return false;
	}
	// ������������
	if(document.getElementById("htqtzlmc").value == null || document.getElementById("htqtzlmc").value == "")
	{
		alert("�������ͬ�����������ƣ�");
		document.getElementById("htqtzlmc").focus();
		return false;
	}
	// �жϺ�ͬǩԼ�������ͬ��Ч�ڹ�ϵ
	if(!compareHtDate())
	{
		return false;
	}

	return true;
}

/**
 * ��������¼���ʽ
 * obj ��ҪУ��Ķ���
 * type У�����ͣ�1-��С����У��|2-����У��
 */
function checkNumInput(obj, type)
{
	if(type == 1)
	{
		//�ж�����������Ƿ����Ҫ��
		if(!isNum(obj , null, null, null, null, 2)){
			return false;			
		}
		//��ʽ������
		//formate(obj);
	}
	else if(type == 2)
	{
		if((obj.value != null && obj.value != "")){
			if(!fnIsIntNum(obj.value))
			{
				alert("�������Ϣ�����Ϲ淶�����������1��������");
				obj.select();
				obj.focus();
				return;
			}
			else{
				obj.value = Number(obj.value);
			}
		}
	}
}

// У��绰����
function isTel(object)
{
	// + ���Ҵ���(2��3λ)-����(2��3λ)-�绰����(7��8λ)-�ֻ���(2-4λ)"
    var s = document.getElementById(object.id).value; 
    var pattern =/^(([0\+]\d{2,3}-)?(0\d{2,4})-)(\d{7,8})(-(\d{2,4}))?$/;
	var mobile = /^(((13[0-9]{1})|159|(15[0-9]{1}))+\d{8})$/;
    //var pattern =/(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/; 
    if(s!="")
    {
		if(!pattern.exec(s) && !mobile.test(s))
        {
			alert('��������ȷ�ĵ绰������ֻ�����:�绰�����ʽ������Ҵ���(2��3λ)-����(2��4λ)-�绰����(7��8λ)-�ֻ���(2-4λ)"');
            object.select();
            object.focus();
			return;
        }
    }
}

// У�����ڸ�ʽ
function checkDateInput(obj)
{
	if(obj.value != null && obj.value !="")
	{
		isDate(obj);
	}
}

// У���ͬǩԼ����
function checkQyrq(obj)
{
	var strDate1 = obj.value;
    var strYear1 = strDate1.substr(0,4);
    var strMonth1 = strDate1.substr(4,2);
    var strDay1 = strDate1.substr(6,2);

	strDate1 = strYear1 + "/" + strMonth1 + "/" + strDay1;
	
	strDate1 = new Date(strDate1);
	strDate2 = new Date();
	//alert("strDate1 = " + strDate1 + "\nstrDate2 = " + strDate2);

	if(strDate1 > strDate2)
	{
		alert("��ͬǩԼ���ڲ��ܴ��ڵ�ǰʱ�䣡");
		obj.select();
		obj.focus();
		return false;
	}

	var times = strDate2.getTime() - strDate1.getTime();
	// ���Ժ��뼶������������������������
	var days = parseInt(times / (1000 * 60 * 60 * 24));
	//alert("�������: " + days);

    if(days > 30)
	{
		alert("��ͬǩԼ�����뵱ǰʱ�����30�����ϣ������Ű����ҵ��");
		return false;
	}

	return true;
}

// �ύ
function doSubmit(actionType){
	var form=document.forms[0];
	if(actionType=='Reset'){
       clearInput();
       //setInput();
	}
	else if(actionType=='Save')
	{
		if(confirm("��ǰ�����Ὣ�������ĵǼǱ���Ϣ�ϴ���˰����أ��Ƿ������")){
			doSave();
		}
	}
	else if(actionType=='Delete')
	{
		if(confirm("��ǰ�����Ὣɾ�����ݿ����ݣ��Ҳ��ɻָ����Ƿ������")){
			doDelete();
		}
	}
	else if(actionType=='Exit'){
		doExit();
	}
	else if(actionType=='Return'){
		doReturn();
	}
}

// ����
function doSave(){
	var old  = document.forms[0].ywczlx.value;
    return SaveExec(old);
}

function SaveExec(old)
{
    if (document.forms[0].ywczlx.value == 0)
    {
        document.forms[0].ywczlx.value = 1;
    }
    else if (document.forms[0].ywczlx.value == 1)
    {
        document.forms[0].ywczlx.value = 2;
    }
    else
    {
        alert("δ֪�Ĳ������ͣ�" + document.forms[0].ywczlx.value);
        return false;
    }

	document.forms[0].actionType.value="Save";
	if (g_objSI.Container != '')
	{
		if (!doSubmitXML(document.forms[0],"Save",true,"",true))
		{
			document.forms[0].ywczlx.value = old;
			return false;
		}	
	}
	else
	{
		if (!doSubmitXML(document.forms[0],"Save",false,"",true))
		{
			document.forms[0].ywczlx.value = old;
			return false;
		}	
	}
	return true;
}

// ����
function doReturn()
{
    if(confirm(confirmReturn))
    {
		document.forms[0].actionType.value="Return";
		document.forms[0].submit();
		return true;
    }
	else
    {
    	return false;
    }
}

// �˳�
function doExit()
{
    if(confirm("�Ƿ��˳���"))
    {
		//document.forms[0].actionType.value="Return";
		document.forms[0].action = "quit.do";
		document.forms[0].submit();
		return true;
    }
	else
    {
    	return false;
    }
}

// ���õ�ѡ��
function changeRadio(value)
{
	// ���ѡ�����(HKG)������(MAC)��̨��(TWN)����ѡ�и۰�̨�����ѡ�����
	if(value == "HKG" || value == "MAC" || value == "TWN")
	{
		document.getElementById("gat").checked = true;
		document.getElementById("fjmgjdq").value = "01";
	}
	else
	{
		document.getElementById("wg").checked = true;
		document.getElementById("fjmgjdq").value = "02";
	}
}

function moveFocus(){
	if(event.keyCode==13 && document.activeElement.id != "htqtzlmc") event.keyCode = 9;
}


// ���ڱȽ�
function compareHtDate()
{
	var strDate1 = document.forms[0].htyxqx.value;		
	var strYear1 = strDate1.substr(0,4);		
	var strMonth1 = strDate1.substr(4,2);
 	var strDay1 = strDate1.substr(6,2);
 	 	
 	var strDate2 = document.forms[0].htqyrq.value;
 	var strYear2 = strDate2.substr(0,4);		
	var strMonth2 = strDate2.substr(4,2);
 	var strDay2 = strDate2.substr(6,2);

	var htyxq = new Date(strMonth1+'-'+strDay1+'-'+strYear1);
	var htqyrq = new Date(strMonth2+'-'+strDay2+'-'+strYear2);
	
	if(!(htyxq > htqyrq)){
		alert("��ͬ��Ч���ޱ�����ں�ͬǩԼ���ڣ����޸ģ�");
		window.event.returnValue=false;
		//document.forms[0].htyxqx.focus();
		//document.forms[0].htyxqx.select();
		return false;
	}

	return true;
}
</script>


<!--%@ include file="../../include/KjqysdsHeader.jsp"%-->

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="return parseXmlOnLoad();" onkeydown="moveFocus()">
<form name="badjbForm" action="badjb.dc">
<input name="actionType" type="hidden" id="actionType" value=""/>
<input name="currentPage" type="hidden" id="currentPage" value="1"/>


<jsp:include page="../../include/KjqysdsHeader.jsp" flush="true" >
	<jsp:param name="name" value="������ҵ����˰��ͬ�����ǼǱ�" />
    <jsp:param name="help_url" value=""/>
</jsp:include>
<br/>
<table width="1000" align="center" cellspacing="0">
    <tr>
        <td class="1-td1">�۽���ҵ����˰�����ǼǱ� </td>
    </tr>
	<tr>
		<td class="1-td2">
			<div id="badjb_output">&nbsp;</div>
		</td>
	</tr>
	<TR class="black9">
		<TD> 
			<br/>
			<div align="center">
            &#160;&#160;
			<img style="cursor:hand"
				tabIndex="-1" onClick="doReset();return false;" accesskey="u"
                value="���" alt="���ҳ���������Ϣ"
                onMouseOver="MM_swapImage('chongtian','','<%=static_contextpath%>images/chongtian2.jpg',1)"
                onMouseOut="MM_swapImgRestore()"
                src="<%=static_contextpath%>images/chongtian1.jpg"
                id="chongtian" />
            &#160;&#160;
            <img
				style="cursor:hand" tabIndex="-1" onClick="if(checkValidity()){doSubmit('Save')};return false;"
                accesskey="s" value="����" alt="����"
                onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
                onMouseOut="MM_swapImgRestore()"
                src="<%=static_contextpath%>images/bc-s1.jpg"
                id="baocun" />
            &#160;&#160;
            <img value="����" alt="���ص��޸Ĺ���ҳ��"
				style="cursor:hand" tabIndex="-1" onClick="javascript:return doSubmit('Return');return false;"
                onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
                onMouseOut="MM_swapImgRestore()"
                src="<%=static_contextpath%>images/fanhui1.jpg"
                id="fanhui" />
			&#160;&#160;
            <img value="�˳�" alt="�˻ص���ҳ��"
				style="cursor:hand" tabIndex="-1" onClick="javascript:return doSubmit('Exit');return false;"
                onMouseOver="MM_swapImage('tuichu','','<%=static_contextpath%>images/tuichu2.jpg',1)"
                onMouseOut="MM_swapImgRestore()"
                src="<%=static_contextpath%>images/tuichu1.jpg"
                id="tuichu" />
		</div></TD>
	</TR>
</table>
    <br/>
    <br/>
    <br/>
<jsp:include page="../../include/bottom.jsp" flush="true"/>
</form>

</body>
</html>
