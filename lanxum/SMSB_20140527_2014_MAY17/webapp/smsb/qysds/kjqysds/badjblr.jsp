<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import ="java.util.HashMap"%>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.kjqysds.badjb.web.BadjbForm"%>

<%
	// ��ȡBadjbForm
	BadjbForm form = (BadjbForm) request.getAttribute("badjbForm");
	// �Ǿ������
	String fjmgb = form.getFjmqyxx().getFjmgb();
	System.out.println("fjmgb = " +fjmgb);
	// ����
	String bzdm = form.getHtxx().getBz();
	System.out.println("bzdm = " +bzdm);
	System.out.println("flag = " + form.getModifyFlag());

%>
<html>
<head>
<title>¼��۽���ҵ����˰��ͬ�����ǼǱ�</title>
	<link href="../../../css/text.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" type="text/JavaScript" src="../../../js/smsb_common.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="../../../js/treatImage.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="../../../js/gmit_selectcontrol.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="../../../js/function.js"></script>
</head>

<script language=JavaScript>
<%
    // ��������б�
    if(session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GJDQ) != null)
    {
        out.print("var arySelect_gjdq = new Array(");
        String[][] gjdq = (String[][])session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GJDQ);
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
    if(session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BZ) != null)
    {
        out.print("var arySelect_bz = new Array(");
        String[][] bz = (String[][])session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BZ);
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
// ��ʼ��ҳ����Ϣ
function doInit()
{
	// ��ʼ�����������˵�
	_addOptionsToSelect(document.forms[0].fjmgbSelect, arySelect_gjdq);
	// ��ʼ�����������˵�
	_addOptionsToSelect(document.forms[0].bzSelect, arySelect_bz);

	//���������˵���ʼֵ
	<%
		if(null != fjmgb && !"".equals(fjmgb))
		{
	%>
			_selectOptionByValue(document.forms[0].fjmgbSelect, "<%=fjmgb%>");
	<%
		}
		if(null != bzdm && !"".equals(bzdm))
		{
	%>
			_selectOptionByValue(document.forms[0].bzSelect, "<%=bzdm%>");
	<%
		}
	%>

	// ��ʼ����ѡ��
	changeRadio(document.forms[0].fjmgbSelect.value);
}

//�ûس�ʱ�ƶ����㣨����������������⣩
function moveFocus(){
	if(event.keyCode==13 && document.activeElement.id != "htqtzlmc") event.keyCode = 9;
}

// �س���ѯ��˰����Ϣ
function doJsjdmQuery()
{
	if(event.keyCode==13) 
	{
		doQuery();
	}
}

// ��ѯ�۽�����Ϣ
function doQuery()
{
	//alert("doQuery");
	document.forms[0].actionType.value = "QueryKjrInfo";
    document.forms[0].submit();
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
	// �۽��˼��������
	if(document.forms[0].jsjdm.value == null || document.forms[0].jsjdm.value == "")
	{
		alert("������۽������˼�������룡");
		document.forms[0].jsjdm.focus();
		return false;
	}
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

// У�����ڸ�ʽ
function checkDateInput(obj)
{
	if(obj.value != null && obj.value !="")
	{
		isDate(obj);
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

// ���õ�ѡ��
function changeRadio(value)
{
	// ���ѡ�����(HKG)������(MAC)��̨��(TWN)����ѡ�и۰�̨�����ѡ�����
	if(value == "HKG" || value == "MAC" || value == "TWN")
	{
		document.getElementById("gat").checked = true;
		//document.getElementsByName("fjmqyxx.fjmgjdq")[0].value = "01";
		document.getElementById("fjmqyxx.fjmgjdq").value = "01";
	}
	else
	{
		document.getElementById("wg").checked = true;
		//document.getElementsByName("fjmqyxx.fjmgjdq")[0].value = "02";
		document.getElementById("fjmqyxx.fjmgjdq").value = "02";
	}
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


<%@ include file="../../include/header.jsp"%>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="doInit()">
<html:form action="/webapp/smsb/qysds/kjqysds/badjbAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden property="fjmqyxx.fjmgjdq" />
<html:hidden property="modifyFlag" />
<html:hidden property="badjxh" />

<table width="1000" align="center" cellspacing="0" onkeydown="moveFocus()">
    <tr>
        <td class="1-td1">¼��۽���ҵ����˰��ͬ�����ǼǱ� </td>
    </tr>
    <tr>
        <td class="1-td2">��ţ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            �����:<bean:write name="badjbForm" property="tbrq" scope="request" filter="true" /><html:hidden property="tbrq" />
        </td>
    </tr>
    <tr>
        <td class="1-td2">
            <table class="table-99" align="center">
                <TR>
                    <TD> <div id="Layer2" style="position:static; ">
                        <table class="table-99" border="1" cellspacing="0" align="center">
                            <tr>
                                <td width="10%" rowspan="6" class="2-td2-t-left">�۽�������</td>
                                <td width="18%" class="2-td2-t-left-qysds1">��������룺</td>
                                <td colspan="7" class="2-td2-t-center">
                                    <div align="left">
                                        &nbsp;<html:text property="jsjdm" size="20" maxlength="8" onfocus="this.select()" onKeyDown="doJsjdmQuery()"/>&nbsp;&nbsp;<input type="button" value="��ѯ" tabIndex="-1" onclick="doQuery()" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="2-td2-left-qysds1">�������ƣ�</td>
                                <td colspan="7" class="2-td2-center">
									<div id="kjywrmc_cn" align="left">
										&nbsp;<bean:write name="badjbForm" property="kjywrxx.kjrmc_cn" scope="request" filter="true" />
									</div>
								</td>
                            </tr>
                            <tr>
                                <td class="2-td2-left-qysds1">Ӣ�����ƣ�</td>
                                <td colspan="7" class="2-td2-center">
									<div align="left">
										&nbsp;<html:text property="kjywrxx.kjrmc_en" styleId="kjywrmc_en" size="80" onfocus="this.select()"/>
									</div>
								</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">�۽���������˰ʶ��ţ�</td>
                                    <td colspan="7" class="2-td2-center"><div align="left">&nbsp;<bean:write name="badjbForm" property="kjywrxx.kjrnssbh" scope="request" filter="true" /></div></td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">��ַ��</td>
                                    <td colspan="3" class="2-td2-left"><div align="left">&nbsp;<bean:write name="badjbForm" property="kjywrxx.kjrdz_cn" scope="request" filter="true" /></div></td>
                                    <td width="9%" class="2-td2-left">�ʱࣺ</td>
                                    <td colspan="3" class="2-td2-center"><div align="left">&nbsp;<bean:write name="badjbForm" property="kjywrxx.kjryzbm" scope="request" filter="true" /></div></td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">�������ˣ�</td>
                                    <td width="15%" class="2-td2-left">
										<div align="left">
											&nbsp;<html:text property="kjywrxx.kjrcwfzr" size="18" styleId="kjywrcwfzr" onfocus="this.select()"/>
										</div>
									</td>
                                    <td width="7%" class="2-td2-left">��ϵ�ˣ�</td>
                                    <td width="15%" class="2-td2-left">
										<div align="left">
											&nbsp;<html:text property="kjywrxx.kjrlxr" size="18" styleId="kjywrlxr" onfocus="this.select()"/>
										</div>
									</td>
                                    <td class="2-td2-left">�绰��</td>
                                    <td width="8%" class="2-td2-left">
										<div align="left">
											&nbsp;<html:text property="kjywrxx.kjrlxdh" size="15" styleId="kjywrdh" onfocus="this.select()"/>
										</div>
									</td>
                                    <td width="7%" class="2-td2-left">���棺</td>
                                    <td width="8%" class="2-td2-center">
										<div align="left">
											&nbsp;<html:text property="kjywrxx.kjrczhm" size="15" styleId="kjywrcz" onfocus="this.select()" />
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td rowspan="6" class="2-td2-left">�Ǿ�����ҵ</td>
                                    <td class="2-td2-left-qysds1">�������ƣ�</td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<html:text property="fjmqyxx.fjmmc_cn" size="80" styleId="fjmqymc_cn" onfocus="this.select()"/>
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">Ӣ�����ƣ�</td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<html:text property="fjmqyxx.fjmmc_en" size="80" styleId="fjmqymc_en" onfocus="this.select()"/>
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">����</td>
                                    <td colspan="2" class="2-td2-left">
                                        <div align="left">
                                            &nbsp;<html:select name="badjbForm" styleId="fjmgbSelect" property="fjmqyxx.fjmgb" onChange="changeRadio(this.value)"/>
                                        </div>
                                    </td>
                                    <td class="2-td2-left">���һ������</td>
                                    <td colspan="4" class="2-td2-center">
                                        �۰�̨<input type="radio" name="gjdq" id="gat" value="01" disabled="true"/>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        ���<input type="radio" name="gjdq" id="wg" value="02" disabled="true"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">��������ַ�����ģ���</td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<html:text property="fjmqyxx.fjmdz_cn" size="80" styleId="fjmqyjmgdz_cn" onfocus="this.select()"/>
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">��������ַ��Ӣ�ģ���</td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<html:text property="fjmqyxx.fjmdz_en" size="80" styleId="fjmqyjmgdz_en" onfocus="this.select()"/>
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">�������ˣ�</td>
                                    <td class="2-td2-left">
										<div align="left">
											&nbsp;<html:text property="fjmqyxx.fjmcwfzr" size="18" styleId="fjmqycwfzr" onfocus="this.select()"/>
										</div>
									</td>
                                    <td class="2-td2-left">��ϵ�ˣ�</td>
                                    <td class="2-td2-left">
										<div align="left">
											&nbsp;<html:text property="fjmqyxx.fjmlxr" size="18" styleId="fjmqylxr" onfocus="this.select()"/>
										</div>
									</td>
                                    <td class="2-td2-left">�绰��</td>
                                    <td class="2-td2-left">
										<div align="left">
											&nbsp;<html:text property="fjmqyxx.fjmlxdh" size="15" styleId="fjmqydh" onfocus="this.select()" />
										</div>
									</td>
                                    <td width="5%" class="2-td2-left">���棺</td>
                                    <td class="2-td2-center">
										<div align="left">
											&nbsp;<html:text property="fjmqyxx.fjmczhm" size="15" styleId="fjmqycz" onfocus="this.select()"/>
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td rowspan="9" class="2-td2-left">��ͬ��Ϣ</td>
                                    <td class="2-td2-left-qysds1">��ͬ��Э�����ƣ� </td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<html:text property="htxx.htmc" size="80" styleId="htxymc" onfocus="this.select()"/>
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">��ͬ��ţ�</td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<html:text property="htxx.htbh" size="80" styleId="htxybh" onfocus="this.select()"/>
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">��ͬǩԼ���ڣ� </td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<html:text property="htxx.qyrq" styleId="htqyrq" size="70" onblur="checkDateInput(this)"/>&nbsp;<font color="red">��������д��ʽ��YYYYMMDD��</font>
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">��ͬ��Ч���ޣ�</td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<html:text property="htxx.htyxq" styleId="htyxqx" size="70" onblur="checkDateInput(this);"/>&nbsp;<font color="red">��������д��ʽ��YYYYMMDD��</font>
										</div>
									</td>
                                    </tr>
                                    <tr>
                                        <td class="2-td2-left-qysds1">��ͬ��</td>
                                        <td colspan="7" class="2-td2-center">
											<div align="left">
											&nbsp;<html:text property="htxx.htje" size="80" styleId="htzje" onfocus="this.select()" onblur="checkNumInput(this, 1)"/>
										</div>
									</td>
                                    </tr>
                                    <tr>
                                        <td class="2-td2-left-qysds1">���֣�</td>
                                        <td colspan="7" class="2-td2-center">
											<div align="left">
												&nbsp;<html:select styleId="bzSelect" property="htxx.bz" />
											</div>
										</td>
                                    </tr>
                                    <tr>
                                        <td class="2-td2-left-qysds1">֧����Ŀ��</td>
                                        <td colspan="7" class="2-td2-center">
											<div align="left">
											&nbsp;<html:text property="htxx.zfxm" size="80" styleId="htzfxm" onfocus="this.select()"/>
										</div>
									</td>
                                    </tr>
                                    <tr>
                                        <td class="2-td2-left-qysds1">���������</td>
                                        <td colspan="7" class="2-td2-center">
											<div align="left">
											&nbsp;<html:text property="htxx.fkcs" size="80" styleId="htfkcs" onfocus="this.select()" onblur="checkNumInput(this, 2)"/>
										</div>
									</td>
                                    </tr>
                                    <tr>
                                        <td class="2-td2-left-qysds1">�����������ƣ�</td>
                                        <td colspan="7" class="2-td2-center">
											<div align="left">
												&nbsp;<html:textarea property="htxx.qtzlmc" rows="3" cols="80%" styleId="htqtzlmc" onfocus="this.select()"/>
											</div>
										</td>
                                    </tr>
                                </table>
                            </div>
                        </TD>
                    </TR>
                    <TR class="black9">
                        <TD> 
							<br/>
							<div align="center">
                            <input type="image" style="cursor:hand"
                                tabIndex="-1" onClick="doReset();return false;" accesskey="R"
                                value="����" alt="���ҳ���������Ϣ"
                                onMouseOver="MM_swapImage('chuntian','','<%=static_contextpath%>images/chongtian2.jpg',1)"
                                onMouseOut="MM_swapImgRestore()"
                                src="<%=static_contextpath%>images/chongtian1.jpg"
                                id="chuntian" />
                            &nbsp;&nbsp;
                            <input type="image"
                                style="cursor:hand" tabIndex="-1" onClick="if(checkValidity()){doSubmitForm('Save')};return false;"
                                accesskey="s" value="����" alt="����"
                                onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
                                onMouseOut="MM_swapImgRestore()"
                                src="<%=static_contextpath%>images/bc-s1.jpg"
                                id="baocun" />
                            &nbsp;&nbsp;
                            <input type="image" value="����" alt="���ص���ҳ��"
                                style="cursor:hand" tabIndex="-1" onClick="tuichu();return false;"
                                onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
                                onMouseOut="MM_swapImgRestore()"
                                src="<%=static_contextpath%>images/fanhui1.jpg"
                                id="fanhui" />
                            </div>
							<br/>
                        </TD>
                    </TR>
                </TABLE>
            </td>
        </tr>
    </table>
    <br/>
    <br/>
    <br/>
<%@ include file="../../include/footernew.jsp"%>
</html:form>

</body>
</html>
