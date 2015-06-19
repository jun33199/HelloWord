<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%@page import="java.util.*"%>
<%@page import="com.ttsoft.bjtax.shenbao.zrrsb.web.ZrrsbForm"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.domain.Szsm"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import="com.ttsoft.bjtax.shenbao.constant.CodeConstant"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.domain.Wbhs"%>
<%@page import="com.ttsoft.bjtax.shenbao.zrrsb.SessionKey"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.domain.Zrrgrsdsmx"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.TinyTools"%>
<%@page import="com.ttsoft.bjtax.dj.model.ZRRYHZH"%>

<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
	ZrrsbForm zrrForm = (ZrrsbForm)request.getAttribute("zrrsbForm");
	List szsmList = zrrForm.getGsszsmList();
	List zrrmxList = zrrForm.getZrrgrsdsmxList();
	List bzList = zrrForm.getBzList();
	List yhList = zrrForm.getYhList();
	String skssksrq = TinyTools.Date2String(zrrForm.getSkssksrq(),"yyyyMMdd");
	String skssjsrq = TinyTools.Date2String(zrrForm.getSkssjsrq(),"yyyyMMdd");
%>
<html>
<head>
<title>��Ȼ�˸�������˰�걨��</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/inputchecker.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript">
//started added by qianchao 2006-2-8
<%
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
		containerName = "";
%>
g_objSI.Container = "<%=containerName%>";
//ended   added by qianchao 2006-2-8

</script>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
<%
/*ȡ�����ڼ������*/
%>
<script language="vbscript">
function DateDiffA(pre,date1, date2)
  DateDiffA = DateDiff(pre, CDate(date1), CDate(date2))
end function
</script>
<script language="JavaScript">
var zhrmb_now;
<logic:equal name="zrrsbForm" property="gjdm" value="CHN">
var jfye_def = 1200;
var jfye_tmp=1200;
</logic:equal>
<logic:notEqual name="zrrsbForm" property="gjdm" value="CHN">
var jfye_def = 4000;
var jfye_tmp=4000;
</logic:notEqual>
var bc = new Array();
<%
if(zrrmxList!=null && zrrmxList.size()!=0)
{
	for(int i=0;i<zrrmxList.size();i++)
	{
		Zrrgrsdsmx mx = (Zrrgrsdsmx)zrrmxList.get(i);
		if(mx.getJeStr()!=null && mx.getJeStr()!="")
		{
%>
	var tmp_je = "<%=mx.getJeStr()%>";
        var tmp_zh = "<%=mx.getZhStr()%>";
	var tmp_bzdm = "<%=mx.getBzdmStr()%>";
	var tmp_whpj = "<%=mx.getWhpjStr()%>";
	bc[<%=i%>] = [tmp_bzdm.split(','),tmp_je.split(','),tmp_whpj.split(','),tmp_zh.split(',')];
<%
		}
	}
}
%>
var sz = new Array(<%=szsmList.size()%>);
var bz = new Array(<%=bzList.size()%>);
var yh = new Array(<%=yhList.size()%>);
<%
//      0           1       2       3           4          5           6              7            8              9
// ˰��˰Ŀ���룬˰��˰Ŀ���ƣ�˰�ʣ�����۳�����Ӧ��˰��ʼ����Ӧ��˰��ֹ��������˰����˰�ʣ�����˰������ʼ������˰������ֹ������˰��������۳���
for(int i=0;i<szsmList.size();i++)
{
	Szsm szsm = (Szsm)szsmList.get(i);
	out.println("sz["+i+"] = [\""+szsm.getSzsmdm()+"\",\""+szsm.getSzsmmc()+"\","+szsm.getSl()+","+szsm.getSskcs()+","+szsm.getYnsqss()+","+szsm.getYnszzs()+","+szsm.getBhsdssl()+","+szsm.getBhsdsqs()+","+szsm.getBhsdszz()+","+szsm.getBhsdskcs()+"];");
}
for(int i=0;i<bzList.size();i++)
{
	Wbhs bz = (Wbhs)bzList.get(i);
	out.println("bz["+i+"] = [\""+bz.getBzdm()+"\",\""+bz.getWhpj()+"\"];");
}
for(int i=0;i<yhList.size();i++)
{
	ZRRYHZH yhTmp = (ZRRYHZH)yhList.get(i);
	out.println("yh["+i+"] = [\""+yhTmp.getKhyhmc()+"\",\""+yhTmp.getYhdm()+"\",\""+yhTmp.getZh()+"\"];");
}
%>

function hideAllSbb()
{
	zrrmx.style.display='none';
	whpjdiv.style.display='none';
}

function DateDiff(pre,date1, date2)
{
    return DateDiffA(pre,date1, date2);
}

//��������Һϼ�
function computerRmbhj(obj)
{
	var row = getRow(obj);
	itemsize = mxtable.rows.length-4;
	if(itemsize > 1)
	{
		var srermb = document.forms[0].srermb[row].value;
		var zhrmb = document.forms[0].zhrmb[row].value;
		var rmbhj_obj = document.forms[0].rmbhj[row];
	}
	else
	{
		if(document.forms[0].je.length == null)
		{
			var srermb = document.forms[0].srermb.value;
			var zhrmb = document.forms[0].zhrmb.value;
			var rmbhj_obj = document.forms[0].rmbhj;
		}
		else
		{
			var srermb = document.forms[0].srermb[0].value;
			var zhrmb = document.forms[0].zhrmb[0].value;
			var rmbhj_obj = document.forms[0].rmbhj[0];
		}
	}
	if(srermb == null || srermb == "")
		srermb = 0;
	if(zhrmb == null || zhrmb == "")
		zhrmb = 0;
	rmbhj_obj.value = Math.round((parseFloat(srermb) + parseFloat(zhrmb))*100)/100;
	formatCurrency(rmbhj_obj,0);
	computer(obj);
}

<%/*ȡ������˰��*/%>
function getBhsl(obj,ynssde)
{
	var szsmdm = obj.options[obj.selectedIndex].value.substring(0,5);
	if(ynssde < 0)
		ynssde = 0;
	for(i=0;i<sz.length;i++)
	{
		if(sz[i][0].indexOf(szsmdm)==0 && ynssde>sz[i][7] && ynssde<=sz[i][8])
		{
			obj.selectedIndex = i;
			return sz[i][6];
		}
	}
}

//ȡ�ò���˰��˰��
function getBhjjsl(ynssde)
{
	var szsmdm = "05011";
	if(ynssde < 0)
		ynssde = 0;
	for(i=0;i<sz.length;i++)
	{
		if(sz[i][0].indexOf(szsmdm)==0 && ynssde>sz[i][7] && ynssde<=sz[i][8])
		{
			return sz[i][6];
		}
	}
}

//ȡ��˰��
function getsl(obj,ynssde)
{
	var szsmdm = obj.options[obj.selectedIndex].value.substring(0,5);
	if(ynssde < 0)
		ynssde = 0;
	for(i=0;i<sz.length;i++)
	{
		if(sz[i][0].indexOf(szsmdm)==0 && ynssde>sz[i][4] && ynssde<=sz[i][5])
		{
			obj.selectedIndex = i;
			return sz[i][2];
		}
	}
}

//ȡ����˰��
function getjjsl(ynssde)
{
	var szsmdm = "05011";
	if(ynssde < 0)
		ynssde = 0;
	for(i=0;i<sz.length;i++)
	{
		if(sz[i][0].indexOf(szsmdm)==0 && ynssde>sz[i][4] && ynssde<=sz[i][5])
		{
			return sz[i][2];
		}
	}
}

//ȡ����˰��
function getTzsl(szsmdm,ynssde)
{
	if(ynssde < 0)
		ynssde = 0;
	for(i=0;i<sz.length;i++)
	{
		if(sz[i][0].indexOf(szsmdm)==0 && ynssde>sz[i][4] && ynssde<=sz[i][5])
		{
			return sz[i][2];
		}
	}
}

//ȡ����˰�ĵ���˰��
function getBhTzsl(szsmdm,ynssde)
{
	if(ynssde < 0)
		ynssde = 0;
	for(i=0;i<sz.length;i++)
	{
		if(sz[i][0].indexOf(szsmdm)==0 && ynssde>sz[i][7] && ynssde<=sz[i][8])
		{
			return sz[i][6];
		}
	}
}

//ȡ��������۳���
function getTzsskcs(szsmdm,sl)
{
	for(i=0;i<sz.length;i++)
	{
		if(sz[i][0].indexOf(szsmdm)==0 && sz[i][2]==sl)
		{
			return sz[i][3];
		}
	}
}

//ȡ����˰�ĵ�������۳���
function getBhTzsskcs(szsmdm,sl)
{
	for(i=0;i<sz.length;i++)
	{
		if(sz[i][0].indexOf(szsmdm)==0 && sz[i][6]==sl)
		{
			return sz[i][9];
		}
	}
}

//�Զ����㣨����������ò�ͬ���㺯����
function computer(obj)
{
	var row = getRow(obj);
	itemsize = mxtable.rows.length-4;
	//ȡ����������
	if(itemsize > 1)
	{
		//var srermb = document.forms[0].srermb[row].value;
		//var zhrmb = document.forms[0].zhrmb[row].value;
		var sdksrq = document.forms[0].sdksrq[row].value;	//���ÿ�ʼ����
		var sdjsrq = document.forms[0].sdjsrq[row].value;	//���ý�������
		var rmbhj = document.forms[0].rmbhj[row].value;		//����Һϼ�
		var jfye_obj = document.forms[0].jfye[row];			//�����ö����
		var ynssde_obj = document.forms[0].ynssde[row];		//Ӧ��˰���ö����
		var szsm_obj = document.forms[0].szsmdm[row];		//˰��˰Ŀ����
		var yjzgyjnsk = document.forms[0].yjzgyjnsk[row].value;	//ԭ��ס��Ӧ����˰��
		var fdkce = document.forms[0].fdkce[row].value;		//�����۳���
		var ynse_obj = document.forms[0].ynse[row];			//Ӧ��˰�����
		var ykjse = document.forms[0].ykjse[row].value;		//�ѿ۽�˰��
		var ybtsk_obj = document.forms[0].ybtsk[row];		//Ӧ����˰��
	}
	else if(itemsize == 1)
	{
		if(document.forms[0].ynssde.length == null)
		{
			//var srermb = document.forms[0].srermb.value;
			//var zhrmb = document.forms[0].zhrmb.value;
			var sdksrq = document.forms[0].sdksrq.value;
			var sdjsrq = document.forms[0].sdjsrq.value;
			var rmbhj = document.forms[0].rmbhj.value;
			var jfye_obj = document.forms[0].jfye;
			var ynssde_obj = document.forms[0].ynssde;
			var szsm_obj = document.forms[0].szsmdm;
			var yjzgyjnsk = document.forms[0].yjzgyjnsk.value;
			var fdkce = document.forms[0].fdkce.value;
			var ynse_obj = document.forms[0].ynse;
			var ykjse = document.forms[0].ykjse.value;
			var ybtsk_obj = document.forms[0].ybtsk;
		}
		else
		{
			//var srermb = document.forms[0].srermb[0].value;
			//var zhrmb = document.forms[0].zhrmb[0].value;
			var sdksrq = document.forms[0].sdksrq[0].value;
			var sdjsrq = document.forms[0].sdjsrq[0].value;
			var rmbhj = document.forms[0].rmbhj[0].value;
			var jfye_obj = document.forms[0].jfye[0];
			var ynssde_obj = document.forms[0].ynssde[0];
			var szsm_obj = document.forms[0].szsmdm;
			var yjzgyjnsk = document.forms[0].yjzgyjnsk[0].value;
			var fdkce = document.forms[0].fdkce[0].value;
			var ynse_obj = document.forms[0].ynse[0];
			var ykjse = document.forms[0].ykjse[0].value;
			var ybtsk_obj = document.forms[0].ybtsk[0];
		}
	}
	//��������Ϊ����Ĭ��Ϊ0
	if(rmbhj==null||rmbhj=="")
		rmbhj = 0;
	if(yjzgyjnsk==null||yjzgyjnsk=="")
		yjzgyjnsk = 0;
	if(fdkce==null||fdkce=="")
		fdkce = 0;
	if(ykjse==null||ykjse=="")
		ykjse = 0;
	if(jfye_obj.value==null||jfye_obj.value=="")
		jfye_tmp = 0;
	switch (szsm_obj.options[szsm_obj.selectedIndex].value.substring(0,4))
	{
		case "0501":
			jfye_obj.readOnly=false;
			jfye_obj.className="input";
			if(jfye_tmp==null||jfye_tmp=="")
				jfye_obj.value = jfye_tmp = jfye_def;
			if(szsm_obj.options[szsm_obj.selectedIndex].value.indexOf("05011")==0)
			{
				//���㹤��
				computerGz(sdksrq,sdjsrq,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj);
			}
			else if(szsm_obj.options[szsm_obj.selectedIndex].value.indexOf("050150")==0)
			{
				jfye_obj.value = jfye_tmp = 0;
				jfye_obj.readOnly=true;
				jfye_obj.className="inputnoborder";
				//���㽱��
				computerJj(rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj);
			}
			else if(szsm_obj.options[szsm_obj.selectedIndex].value.indexOf("050151")==0)
			{
				//�������
				computerTiaozi(sdksrq,sdjsrq,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj);
			}
			else if(szsm_obj.options[szsm_obj.selectedIndex].value.indexOf("050152")==0)
			{
				//������ְ
				computerTz(sdksrq,sdjsrq,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj);
			}
			return;
		case "0503":
			jfye_obj.readOnly=false;
			jfye_obj.className="input";
			if(jfye_tmp==null||jfye_tmp=="")
				jfye_obj.value = jfye_tmp = 800;
			//�������񱨳�
			computerLwbc(jfye_obj,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj);
			return;
		case "0504":
			jfye_obj.readOnly=false;
			jfye_obj.className="input";
			if(jfye_tmp==null||jfye_tmp=="")
				jfye_obj.value = jfye_tmp = 800;
			//����������
			computerGcsd(jfye_obj,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj);
			return;
		case "0505":
			jfye_obj.readOnly=false;
			jfye_obj.className="input";
			if(jfye_tmp==null||jfye_tmp=="")
				jfye_obj.value = jfye_tmp = 800;
			//����Ȩʹ�÷�����
			computerTxLxYbzl(jfye_obj,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj);
			return;
		case "0507":
			jfye_obj.readOnly=false;
			jfye_obj.className="input";
			if(jfye_tmp==null||jfye_tmp=="")
				jfye_obj.value = jfye_tmp = 800;
			if(szsm_obj.options[szsm_obj.selectedIndex].value.indexOf("050702")==0)
			{
				//һ��Ʋ�����(ͬ����Ȩʹ�÷�����)
				computerTxLxYbzl(jfye_obj,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj);
			}
			if(szsm_obj.options[szsm_obj.selectedIndex].value.indexOf("050701")==0)
			{
				//����˽������
				computerGrsfcz(jfye_obj,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj);
			}
			return;
		case "0506":
		case "0508":
		case "0509":
		case "0510":
			jfye_obj.value = jfye_tmp = 0;
			jfye_obj.readOnly=true;
			jfye_obj.className="inputnoborder";
			/*
				��Ϣ����Ϣ����������
				�Ʋ�ת������
				żȻ����
				������Ժ��������ȷ����˰����������
			*/
			computerOther(rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj);
			return;
		default :
			return;
	}
}

//���㹤��
function computerGz(sdksrq,sdjsrq,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj)
{
	//ԭ��ס��Ӧ����˰��Ϊ0
	if(yjzgyjnsk == 0)
	{
		switch (document.forms[0].fdfsdm.value)
		{
			//���˸���
			case "1":
				//ȡ�ò���˰˰�ʺ�����۳���
				var sl = getBhsl(szsm_obj,(rmbhj - fdkce - jfye_tmp));
				if(sl == null || sl == "")
					return;
				var sskcs = sz[szsm_obj.selectedIndex][9];
				//Ӧ��˰���ö�=�����-�����۳���Ŀ���-��׼�۳�����
				var ynssde = Math.round((rmbhj - fdkce - jfye_tmp - sskcs) * 100 /(1-sl))/100;
				//��������Ӧ��˰���ö�С��0����Ϊ0
				if(ynssde < 0)
				{
					ynssde = 0;
				}
				//���Ӧ��˰���ö��ı�����ֵ��ȡ�ı���ֵ��������ı��򸳼�������ֵ
				else if(ynssde_obj.value == null || ynssde_obj.value == "")
				{
					ynssde_obj.value = ynssde;
				}
				else
				{
					ynssde = ynssde_obj.value;
				}
				//����˰��˰Ŀ��Ӧ��˰���ö�ȡ��˰��˰��
				sl = getsl(szsm_obj,ynssde);
				if(sl == null || sl == "")
					return;
				//����Ӧ��˰��
				var ynse = Math.round((ynssde * sl - sz[szsm_obj.selectedIndex][3])*100)/100;
				if(document.forms[0].sfczbs.value!="1")
				{
					//�����פ
					if(sdksrq != null && trim(sdksrq) != "" && sdjsrq != null && trim(sdjsrq) != "")
						//ʵ�ʹ�����
						var sjgzr = DateDiff("d",sdksrq.substring(0,4)+"-"+sdksrq.substring(4,6)+"-"+sdksrq.substring(6),sdjsrq.substring(0,4)+"-"+sdjsrq.substring(4,6)+"-"+sdjsrq.substring(6))+1;
					else
						return;
					//�����ڼ䰴�¼������
					var days = fnComputerDay(sdksrq.substring(0,4),sdksrq.substring(4,6));
					ynse = Math.round(ynse/days*sjgzr*100)/100;
				}
				else
				{
					//�ǳ�פ
					if(sdksrq != null && trim(sdksrq) != "" && sdjsrq != null && trim(sdjsrq) != "")
						//ʵ�ʹ�����
						var sjgzy = DateDiff("M",sdksrq.substring(0,4)+"-"+sdksrq.substring(4,6)+"-"+sdksrq.substring(6),sdjsrq.substring(0,4)+"-"+sdjsrq.substring(4,6)+"-"+sdjsrq.substring(6))+1;
					else
						return;
					ynse = Math.round(ynse*sjgzy*100)/100;
				}
				if(ynse < 0)
				{
					ynse = 0;
				}
				if(ynse_obj.value == null || ynse_obj.value == "")
				{
					ynse_obj.value = ynse;
				}
				else
				{
					ynse = ynse_obj.value;
				}
				var ybtsk = ynse - ykjse;
				if(ynssde < 0)
				{
					ynssde = 0;
				}
				else if(ynssde_obj.value == null || ynssde_obj.value == "")
				{
					ynssde_obj.value = ynssde;
				}
				else
				{
					ynssde = ynssde_obj.value;
				}
				if(ybtsk < 0)
					return;
				else if(ybtsk_obj.value == null || ybtsk_obj.value == "")
					ybtsk_obj.value = ybtsk;
				return;
			/*
			����Ϊ��Ա����һ�������Ĺ���Ӧ�ɵ�˰��򸺵�һ��������ʵ��Ӧ��˰�
			*/
			case "2":
				//ȡ�ò���˰˰�ʺ�����۳�������������
				var sl = getBhsl(szsm_obj,(rmbhj - jfye_tmp));
				if(sl == null || sl == "")
					return;
				var sskcs = sz[szsm_obj.selectedIndex][9];
				var fdbl = parseFloat(document.forms[0].dwfdbl.value);
				//Ӧ��˰���ö�=��δ������������˰�������-���ÿ۳���׼-����۳���*�����������£�1-˰��*����������
				var ynssde = Math.round((rmbhj - jfye_tmp - sskcs * fdbl/100)*100/(1-sl*fdbl/100))/100;
				if(ynssde < 0)
				{
					ynssde = 0;
				}
				else if(ynssde_obj.value == null || ynssde_obj.value == "")
				{
					ynssde_obj.value = ynssde;
				}
				else
				{
					ynssde = ynssde_obj.value;
				}
				//ȡ��˰˰��
				sl = getsl(szsm_obj,ynssde);
				if(sl == null || sl == "")
					return;
				//Ӧ��˰��=Ӧ��˰���ö�*����˰��-����۳���
				var ynse = Math.round((ynssde * sl - sz[szsm_obj.selectedIndex][3])*100)/100;
				//�ж��Ƿ�פ��ͬ���˸�����
				if(document.forms[0].sfczbs.value!="1")
				{
					if(sdksrq != null && trim(sdksrq) != "" && sdjsrq != null && trim(sdjsrq) != "")
						var sjgzr = DateDiff("d",sdksrq.substring(0,4)+"-"+sdksrq.substring(4,6)+"-"+sdksrq.substring(6),sdjsrq.substring(0,4)+"-"+sdjsrq.substring(4,6)+"-"+sdjsrq.substring(6))+1;
					else
						return;
					var days = fnComputerDay(sdksrq.substring(0,4),sdksrq.substring(4,6));
					ynse = Math.round(ynse/days*sjgzr*100)/100;
				}
				else
				{
					if(sdksrq != null && trim(sdksrq) != "" && sdjsrq != null && trim(sdjsrq) != "")
						var sjgzy = DateDiff("M",sdksrq.substring(0,4)+"-"+sdksrq.substring(4,6)+"-"+sdksrq.substring(6),sdjsrq.substring(0,4)+"-"+sdjsrq.substring(4,6)+"-"+sdjsrq.substring(6))+1;
					else
						return;
					ynse = Math.round(ynse*sjgzy*100)/100;
				}
				if(ynse < 0)
				{
					ynse = 0;
				}
				if(ynse_obj.value == null || ynse_obj.value == "")
				{
					ynse_obj.value = ynse;
				}
				else
				{
					ynse = ynse_obj.value;
				}
				var ybtsk = ynse - ykjse;
				if(ybtsk < 0)
					return;
				else if(ybtsk_obj.value == null || ybtsk_obj.value == "")
					ybtsk_obj.value = ybtsk;
				return;
			/*
			��˾ȫ�����
			���е�˰����ָ����˰���ð�����˰�����Ӧ��˰�ʣ����е�˰����ָ��˰���ð���˰�����Ӧ��˰��
			*/
			case "3":
				//Ӧ��˰���ö�=����˰�����-�����۳���Ŀ���-���ÿ۳���׼-����۳���
				var ynssde = rmbhj - fdkce - jfye_tmp;
				if(ynssde < 0)
				{
					ynssde = 0;
				}
				else if(ynssde_obj.value == null || ynssde_obj.value == "")
				{
					ynssde_obj.value = ynssde;
				}
				else
				{
					ynssde = ynssde_obj.value;
				}
				//ȡ��˰˰�ʡ�����۳���
				var sl = getsl(szsm_obj,ynssde);
				var sskcs = sz[szsm_obj.selectedIndex][3];
				if(sl == null || sl == "" || isNaN(sl))
				{
					sl = 0;
				}
				if(sskcs == null || sskcs == "" || isNaN(sskcs))
				{
					sskcs = 0;
				}
				//Ӧ��˰��=Ӧ��˰���ö�*˰��-����۳���
				var ynse = Math.round((ynssde * sl - sskcs)*100)/100;
				//�ж��Ƿ�פ��ͬ���˸�����
				if(document.forms[0].sfczbs.value!="1")
				{
					if(sdksrq != null && trim(sdksrq) != "" && sdjsrq != null && trim(sdjsrq) != "")
						var sjgzr = DateDiff("d",sdksrq.substring(0,4)+"-"+sdksrq.substring(4,6)+"-"+sdksrq.substring(6),sdjsrq.substring(0,4)+"-"+sdjsrq.substring(4,6)+"-"+sdjsrq.substring(6))+1;
					else
						return;
					var days = fnComputerDay(sdksrq.substring(0,4),sdksrq.substring(4,6));
					ynse = Math.round(ynse/days*sjgzr*100)/100;
				}
				else
				{
					if(sdksrq != null && trim(sdksrq) != "" && sdjsrq != null && trim(sdjsrq) != "")
						var sjgzy = DateDiff("M",sdksrq.substring(0,4)+"-"+sdksrq.substring(4,6)+"-"+sdksrq.substring(6),sdjsrq.substring(0,4)+"-"+sdjsrq.substring(4,6)+"-"+sdjsrq.substring(6))+1;
					else
						return;
					ynse = Math.round(ynse*sjgzy*100)/100;
				}
				if(ynse < 0)
				{
					ynse = 0;
				}
				if(ynse_obj.value == null || ynse_obj.value == "")
				{
					ynse_obj.value = ynse;
				}
				else
				{
					ynse = ynse_obj.value;
				}
				var ybtsk = ynse - ykjse;
				if(ybtsk < 0)
					return;
				else if(ybtsk_obj.value == null || ybtsk_obj.value == "")
					ybtsk_obj.value = ybtsk;
				return;
			default:
				return;
		}
	}
	else
	{
		//��ԭ��ס��Ӧ����˰��
		var x1 = rmbhj - yjzgyjnsk;
		var sl = getBhsl(szsm_obj,(x1-jfye_tmp));
		if(sl == null || sl == "")
			return;
		var x2 = Math.round((x1 - jfye_tmp - sz[szsm_obj.selectedIndex][9])*100/(1-sl))/100;
		var x4 = rmbhj - jfye_tmp;
		if(x2 > x4)
			var ynssde = x2;
		else
			var ynssde = x4;
		if(ynssde < 0)
		{
			ynssde = 0;
		}
		else if(ynssde_obj.value == null || ynssde_obj.value == "")
		{
			ynssde_obj.value = ynssde;
		}
		else
		{
			ynssde = ynssde_obj.value;
		}
		sl = getsl(szsm_obj,ynssde);
		if(sl == null || sl == "")
			return;
		var ynse = Math.round((ynssde*sl - sz[szsm_obj.selectedIndex][3])*100)/100;
		if(ynse < 0)
		{
			ynse = 0;
		}
		if(ynse_obj.value == null || ynse_obj.value == "")
		{
			ynse_obj.value = ynse;
		}
		else
		{
			ynse = ynse_obj.value;
		}
		var ybtsk = ynse - ykjse;
		if(ybtsk < 0)
			return;
		else if(ybtsk_obj.value == null || ybtsk_obj.value == "")
			ybtsk_obj.value = ybtsk;
		return;
	}
}

//���㽱��
function computerJj(rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj)
{
	switch (document.forms[0].fdfsdm.value)
	{
		/*
		���˸�����
		*/
		case "1":
			//ȡ����˰˰�ʡ�����۳�������Ӧ��˰���ö�
			var sl = getBhjjsl(rmbhj);
			if(sl == null || sl == "")
				return;
			var sskcs = getBhTzsskcs("05011",sl);
			var ynssde = Math.round((rmbhj - sskcs) * 100 /(1-sl))/100;
			if(ynssde < 0)
			{
				ynssde = 0;
			}
			else if(ynssde_obj.value == null || ynssde_obj.value == "")
			{
				ynssde_obj.value = ynssde;
			}
			else
			{
				ynssde = ynssde_obj.value;
			}
			//ȡ��˰˰�ʡ�����۳�������Ӧ��˰��
			sl = getjjsl(ynssde);
			if(sl == null || sl == "")
				return;
			var ynse = Math.round((ynssde * sl - getTzsskcs("05011",sl))*100)/100;
			if(ynse < 0)
			{
				ynse = 0;
			}
			if(ynse_obj.value == null || ynse_obj.value == "")
			{
				ynse_obj.value = ynse;
			}
			else
			{
				ynse = ynse_obj.value;
			}
			var ybtsk = ynse - ykjse;
			if(ybtsk < 0)
				return;
			else if(ybtsk_obj.value == null || ybtsk_obj.value == "")
				ybtsk_obj.value = ybtsk;
			return;
		/*
		��˾ȫ���
		*/
		case "3":
			var ynssde = rmbhj;
			if(ynssde < 0)
			{
				ynssde = 0;
			}
			else if(ynssde_obj.value == null || ynssde_obj.value == "")
			{
				ynssde_obj.value = ynssde;
			}
			else
			{
				ynssde = ynssde_obj.value;
			}
			//ȡ��˰����˰��
			sl = getjjsl(ynssde);
			if(sl == null || sl == "")
				return;
			var ynse = Math.round((ynssde * sl - getTzsskcs("05011",sl))*100)/100;
			if(ynse < 0)
			{
				ynse = 0;
			}
			if(ynse_obj.value == null || ynse_obj.value == "")
			{
				ynse_obj.value = ynse;
			}
			else
			{
				ynse = ynse_obj.value;
			}
			var ybtsk = ynse - ykjse;
			if(ybtsk < 0)
				return;
			else if(ybtsk_obj.value == null || ybtsk_obj.value == "")
				ybtsk_obj.value = ybtsk;
			return;
		default:
			return;
	}
}

function computerLwbc(jfye_obj,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj)
{
	var ynse = 0;
	switch (document.forms[0].fdfsdm.value)
	{
		case "1":
			if(rmbhj <= 3360)	//�����С��3360
			{
				var ynssde = Math.round((rmbhj -800) * 100 / 0.8)/100;
				if(ynssde < 0)
				{
					ynssde = 0;
				}
				else if(ynssde_obj.value == null || ynssde_obj.value == "")
				{
					ynssde_obj.value = ynssde;
				}
				else
				{
					ynssde = ynssde_obj.value;
				}
				//ȡ��˰˰��
				var sl = getsl(szsm_obj,ynssde);
				if(sl == null || sl == "")
					return;
				ynse = Math.round(ynssde*sl*100)/100;
			}
			else
			{
				if(rmbhj <= 21000)	//�����С��21000
				{
					var ynssde = Math.round(rmbhj * 2000 / 21)/100;
					if(ynssde < 0)
					{
						ynssde = 0;
					}
					else if(ynssde_obj.value == null || ynssde_obj.value == "")
					{
						ynssde_obj.value = ynssde;
					}
					else
					{
						ynssde = ynssde_obj.value;
					}
					var sl = getsl(szsm_obj,ynssde);
					if(sl == null || sl == "")
						return;
					ynse = Math.round(ynssde*sl*100)/100;
				}
				else if(rmbhj <= 49500)	//�����С��49500
				{
					var ynssde = Math.round((rmbhj-2000) * 2000 / 19)/100;
					if(ynssde < 0)
					{
						ynssde = 0;
					}
					else if(ynssde_obj.value == null || ynssde_obj.value == "")
					{
						ynssde_obj.value = ynssde;
					}
					else
					{
						ynssde = ynssde_obj.value;
					}
					var sl = getsl(szsm_obj,ynssde);
					if(sl == null || sl == "")
						return;
					ynse = Math.round(ynssde*sl*100)/100-2000;
				}
				else
				{
					var ynssde = Math.round((rmbhj-7000) * 2000 / 17)/100;
					if(ynssde < 0)
					{
						ynssde = 0;
					}
					else if(ynssde_obj.value == null || ynssde_obj.value == "")
					{
						ynssde_obj.value = ynssde;
					}
					else
					{
						ynssde = ynssde_obj.value;
					}
					var sl = getsl(szsm_obj,ynssde);
					if(sl == null || sl == "")
						return;
					ynse = Math.round(ynssde*sl*100)/100-7000;
				}
			}
			if(ynse < 0)
			{
				ynse = 0;
			}
			if(ynse_obj.value == null || ynse_obj.value == "")
			{
				ynse_obj.value = ynse;
			}
			else
			{
				ynse = ynse_obj.value;
			}
			var ybtsk = ynse - ykjse;
			if(ybtsk < 0)
				return;
			else if(ybtsk_obj.value == null || ybtsk_obj.value == "")
				ybtsk_obj.value = ybtsk;
			return;
		/*
		��˾ȫ���
		*/
		case "3":
			//ȡ����˰˰�ʼ���Ӧ��˰��
			var sl = getsl(szsm_obj,rmbhj);
			if(sl == null || sl == "")
				return;
			if(rmbhj <= 1000)
			{
				ynse = Math.round(rmbhj * sl*100)/100;
				ynse_obj.value = ynse;
			}
			else if(rmbhj > 1000 && rmbhj <= 4000)	//�������1000��4000֮��
			{
				var ynssde = rmbhj - 800;
				if(ynssde < 0)
				{
					ynssde = 0;
				}
				else if(ynssde_obj.value == null || ynssde_obj.value == "")
				{
					ynssde_obj.value = ynssde;
				}
				else
				{
					ynssde = ynssde_obj.value;
				}
				ynse = Math.round(ynssde * sl*100)/100;
			}
			else if(rmbhj > 4000 && rmbhj <= 20000)	//�������4000��20000֮��
			{
				jfye_obj.value = Math.round(rmbhj*0.2*100)/100;
				var ynssde = Math.round((rmbhj - rmbhj * 0.2)*100)/100;
				if(ynssde < 0)
				{
					ynssde = 0;
				}
				else if(ynssde_obj.value == null || ynssde_obj.value == "")
				{
					ynssde_obj.value = ynssde;
				}
				else
				{
					ynssde = ynssde_obj.value;
				}
				ynse = Math.round(ynssde * 0.2*100)/100;
			}
			else if(rmbhj > 20000)	//��������20000
			{
				var ynssde = Math.round(rmbhj*0.8*100)/100;
				if(ynssde < 0)
				{
					ynssde = 0;
				}
				else if(ynssde_obj.value == null || ynssde_obj.value == "")
				{
					ynssde_obj.value = ynssde;
				}
				else
				{
					ynssde = ynssde_obj.value;
				}
				if(rmbhj <= 50000)
					ynse = Math.round(rmbhj*0.8*0.3*100)/100 - 2000;
				else
					ynse = Math.round(rmbhj*0.8*0.4*100)/100 - 7000;
			}
			if(ynse < 0)
			{
				ynse = 0;
			}
			if(ynse_obj.value == null || ynse_obj.value == "")
			{
				ynse_obj.value = ynse;
			}
			else
			{
				ynse = ynse_obj.value;
			}
			var ybtsk = ynse - ykjse;
			if(ybtsk < 0)
				return;
			else if(ybtsk_obj.value == null || ybtsk_obj.value == "")
				ybtsk_obj.value = ybtsk;
			return;
		default:
			return;
	}
}

/*
����������
*/
function computerGcsd(jfye_obj,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj)
{
	var ynssde;
	if(rmbhj <= 4000)
	{
		ynssde = rmbhj-800;
	}
	else
	{
		jfye_obj.value = Math.round(rmbhj*0.2*100)/100;
		ynssde = Math.round((rmbhj-rmbhj*0.2)*100)/100;
	}
	if(ynssde < 0)
	{
		ynssde = 0;
	}
	else if((ynssde_obj.value == null || ynssde_obj.value == "")&&ynssde!=0)
	{
		ynssde_obj.value = ynssde;
	}
	else
	{
		ynssde = ynssde_obj.value;
	}
	var ynse = Math.round(ynssde*0.2*(1-0.3)*100)/100;
	if(ynse < 0)
	{
		ynse = 0;
	}
	if((ynse_obj.value == null || ynse_obj.value == "")&&ynse!=0)
	{
		ynse_obj.value = ynse;
	}
	else
	{
		ynse = ynse_obj.value;
	}
	var ybtsk = ynse - ykjse;
	if(ybtsk < 0)
		return;
	else if((ybtsk_obj.value == null || ybtsk_obj.value == "")&&ybtsk!=0)
		ybtsk_obj.value = ybtsk;
	return;
}

/*
һ��Ʋ�����(ͬ����Ȩʹ�÷�����)
*/
function computerTxLxYbzl(jfye_obj,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj)
{
	var ynssde;
	if(rmbhj <= 4000)	//�����С��4000
	{
		ynssde = rmbhj-800;
	}
	else
	{
		jfye_obj.value = Math.round(rmbhj*0.2*100)/100;
		ynssde = Math.round((rmbhj-rmbhj*0.2)*100)/100;
	}
	if(ynssde < 0)
	{
		ynssde = 0;
	}
	else if((ynssde_obj.value == null || ynssde_obj.value == "")&&ynssde!=0)
	{
		ynssde_obj.value = ynssde;
	}
	else
	{
		ynssde = ynssde_obj.value;
	}
	var ynse = Math.round(ynssde*0.2*100)/100;
	if(ynse < 0)
	{
		ynse = 0;
	}
	if((ynse_obj.value == null || ynse_obj.value == "")&&ynse!=0)
	{
		ynse_obj.value = ynse;
	}
	else
	{
		ynse = ynse_obj.value;
	}
	var ybtsk = ynse - ykjse;
	if(ybtsk < 0)
		return;
	else if((ybtsk_obj.value == null || ybtsk_obj.value == "")&&ybtsk!=0)
		ybtsk_obj.value = ybtsk;
	return;
}

/*
����˽������
*/
function computerGrsfcz(jfye_obj,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj)
{
	var ynssde;
	if(rmbhj <= 4000)
	{
		ynssde = rmbhj-800;
	}
	else
	{
		jfye_obj.value = Math.round(rmbhj*0.2*100)/100;
		ynssde = Math.round((rmbhj-rmbhj*0.2)*100)/100;
	}
	if(ynssde < 0)
	{
		ynssde = 0;
	}
	else if((ynssde_obj.value == null || ynssde_obj.value == "")&&ynssde!=0)
	{
		ynssde_obj.value = ynssde;
	}
	else
	{
		ynssde = ynssde_obj.value;
	}
	var ynse = Math.round(ynssde*0.1*100)/100;
	if(ynse < 0)
	{
		ynse = 0;
	}
	if((ynse_obj.value == null || ynse_obj.value == "")&&ynse!=0)
	{
		ynse_obj.value = ynse;
	}
	else
	{
		ynse = ynse_obj.value;
	}
	var ybtsk = ynse - ykjse;
	if(ybtsk < 0)
		return;
	else if((ybtsk_obj.value == null || ybtsk_obj.value == "")&&ybtsk!=0)
		ybtsk_obj.value = ybtsk;
	return;
}

/*
	��Ϣ����Ϣ����������
	�Ʋ�ת������
	żȻ����
	������Ժ��������ȷ����˰����������
*/
function computerOther(rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj)
{
	var ynssde = rmbhj;
	if(ynssde < 0)
	{
		ynssde = 0;
	}
	else if((ynssde_obj.value == null || ynssde_obj.value == "")&&ynssde!=0)
	{
		ynssde_obj.value = ynssde;
	}
	else
	{
		ynssde = ynssde_obj.value;
	}
	var ynse = Math.round(ynssde*0.2*100)/100;
	if(ynse < 0)
	{
		ynse = 0;
	}
	if((ynse_obj.value == null || ynse_obj.value == "")&&ynse!=0)
	{
		ynse_obj.value = ynse;
	}
	else
	{
		ynse = ynse_obj.value;
	}
	var ybtsk = ynse - ykjse;
	if(ybtsk < 0)
		return;
	else if((ybtsk_obj.value == null || ybtsk_obj.value == "")&&ybtsk!=0)
		ybtsk_obj.value = ybtsk;
	return;
}

/*
��ְ
*/
function computerTz(sdksrq,sdjsrq,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj)
{
	//�����д�����ڼ䣬����ʵ�ʹ�����
	if(sdksrq != null && trim(sdksrq) != "" && sdjsrq != null && trim(sdjsrq) != "")
		var sjgzr = DateDiff("d",sdksrq.substring(0,4)+"-"+sdksrq.substring(4,6)+"-"+sdksrq.substring(6),sdjsrq.substring(0,4)+"-"+sdjsrq.substring(4,6)+"-"+sdjsrq.substring(6))+1;
	else
		return;
	//����������
	var ysr = (rmbhj-fdkce)/(sjgzr/365);
	var ynssde = 0;
    switch (document.forms[0].fdfsdm.value)
	{
		case "1":
			//ȡ����˰˰�ʡ�����۳���
			var sl = getBhTzsl("05011",(ysr - fdkce - jfye_tmp));
			if(sl == null || sl == "")
				return;
			var sskcs = getBhTzsskcs("05011",sl);
			ynssde = Math.round((ysr - jfye_tmp - sskcs) * 100 /(1-sl))/100;
			break;
		case "2":
			var sl = getBhTzsl("05011",(ysr - jfye_tmp));
			if(sl == null || sl == "")
				return;
			var sskcs = getBhTzsskcs("05011",sl);
			//ȡ�÷������
			var fdbl = parseFloat(document.forms[0].dwfdbl.value);
			var ynssde = Math.round((ysr - jfye_tmp - sskcs * fdbl/100) * 100 /(1-sl*fdbl/100))/100;
			break;
		case "3":
			var ynssde = Math.round((ysr - jfye_tmp)*100)/100;
			break;
		default:
			break;
	}
	if(ynssde < 0)
	{
		ynssde = 0;
	}
	if((ynssde_obj.value == null || ynssde_obj.value == "")&&parseFloat(ynssde)!=0)
	{
		ynssde_obj.value = ynssde;
	}
	else
	{
		ynssde = ynssde_obj.value;
	}
	//ȡ����˰�ʼ���Ӧ��˰��
	var sl = getTzsl("05011",ynssde);
	if(sl == null || sl == "")
		return;
	var ynse = Math.round((ynssde*sl-getTzsskcs("05011",sl))*(sjgzr/365)*100)/100;
	if(ynse < 0)
	{
		ynse = 0;
	}
	if((ynse_obj.value == null || ynse_obj.value == "")&&parseFloat(ynse)!=0)
	{
		ynse_obj.value = ynse;
	}
	else
	{
		ynse = ynse_obj.value;
	}
	var ybtsk = ynse - ykjse;
	if(ybtsk < 0)
		return;
	else if(ybtsk_obj.value == null || ybtsk_obj.value == "")
		ybtsk_obj.value = ybtsk;
	return;
}

/*
����
*/
function computerTiaozi(sdksrq,sdjsrq,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj)
{
	itemsize = mxtable.rows.length-4;
	//���û�й�����Ϣ���˳�
	if(itemsize<=1)
		return;
	//�����д�����ڼ䣬����ʵ�ʹ�����
	if(sdksrq != null && trim(sdksrq) != "" && sdjsrq != null && trim(sdjsrq) != "")
		var sjgzr = DateDiff("M",sdksrq.substring(0,4)+"-"+sdksrq.substring(4,6)+"-"+sdksrq.substring(6),sdjsrq.substring(0,4)+"-"+sdjsrq.substring(4,6)+"-"+sdjsrq.substring(6))+1;
	else
		return;
	var tzqynssde = 0;
	var tzqybtsk = 0;
	var tzqynse = 0;
	var getGz = false;
	//ȡ��������
	for(i=0;i<itemsize;i++)
	{
		if(document.forms[0].szsmdm[i].value.indexOf("05011")==0)
		{
			getGz = true;
			tzqrmbhj = document.forms[0].rmbhj[i].value;	//����ǰ����Һϼ�
			tzqybtsk = document.forms[0].ybtsk[i].value;	//����ǰӦ����˰��
			tzqynse = document.forms[0].ynse[i].value;		//����ǰӦ��˰��
			if(tzqrmbhj==null||tzqrmbhj=="")
				tzqrmbhj=0;
			if(tzqybtsk==null||tzqybtsk=="")
				tzqybtsk=0;
			if(tzqynse==null||tzqynse=="")
				tzqynse=0;
			break;
		}
	}
	var ynssde = 0;
	var ynse = 0;
	//����й�������
	if(getGz)
	{
		//���������
		var sre = parseFloat(tzqrmbhj) + parseFloat(rmbhj/sjgzr) - jfye_tmp;
        switch (document.forms[0].fdfsdm.value)
		{
			case "1":
				var sl = getBhTzsl("05011",sre);
				if(sl == null || sl == "")
					return;
				var sskcs = getBhTzsskcs("05011",sl);
				ynssde = Math.round((sre - fdkce - sskcs) * 100 /(1-sl))/100;
				break;
			case "2":
				var sl = getBhTzsl("05011",sre);
				if(sl == null || sl == "")
					return;
				var sskcs = getBhTzsskcs("05011",sl);
				var fdbl = parseFloat(document.forms[0].dwfdbl.value);
				var ynssde = Math.round((sre - sskcs * fdbl/100) * 100 /(1-sl*fdbl/100))/100;
				break;
			case "3":
				var ynssde = Math.round(sre*100)/100;
				break;
			default:
				break;
		}
		if(ynssde<0)
			ynssde = 0;
		if((ynssde_obj.value == null || ynssde_obj.value == "")&&ynssde!=0)
		{
			ynssde_obj.value = ynssde;
		}
		else
		{
			ynssde = ynssde_obj.value;
		}
		var sl = getTzsl("05011",ynssde);
		if(sl == null || sl == "")
			return;
		ynse = Math.round((ynssde * sl - getTzsskcs("05011",sl))*100)/100;
		//ynse = Math.round((tzqybtsk - ynse)*sjgzr*100)/100;
		if(ynse < 0)
		{
			ynse = 0;
		}
		if((ynse_obj.value == null || ynse_obj.value == "")&&ynse!=0)
		{
			ynse_obj.value = ynse;
		}
		else
		{
			ynse = ynse_obj.value;
		}
		var ybtsk = Math.round(((ynse - tzqynse)*sjgzr)*100)/100;
		if(ybtsk < 0)
			return;
		else if((ybtsk_obj.value == null || ybtsk_obj.value == "")&&ybtsk!=0)
			ybtsk_obj.value = ybtsk;
		return;
	}
	else
	{
		alert("��û���걨��ǰ�·ݹ��ʣ�");
		return;
	}
}

//ȡ�ò���������������
function getRow(obj)
{
	itemsize = mxtable.rows.length-4;
	if(itemsize > 1)
	{
		var row = 0;
		for(var i=0;i<itemsize;i++)
		{
			if(eval("document.forms[0]." + obj.name + "[i]") == obj)
			{
				return i;
			}
		}
	}
	else
		return 0;
}

//���һ��
function doAdd()
{
	itemsize = mxtable.rows.length-4;
	if(itemsize>=4)
	{
		alert("��ǰ�걨���¼��4����ϸ���ݣ�");
		return;
	}
	var row = mxtable.insertRow(mxtable.rows.length-1);

	<%/*������Ŀ*/%>
	var sel = document.createElement("<select name=\"szsmdm\" onchange=\"jfye_tmp=null;computer(this);\">");
	for (var i=0; i<sz.length; i++)
	{
		var opt = document.createElement("<OPTION>");
		opt.value = sz[i][0];
		opt.text = sz[i][1];
		sel.options.add(opt);
	}

	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(sel);

	<%/*�����ڼ�*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.noWrap = true;
	cell.innerHTML = "<input name=\"sdksrq\" size=\"8\" maxlength=\"8\" value=\"<%=skssksrq%>\" onchange=\"if(isDate(this)){computer(this);return true;}else{return false;}\">-<input name=\"sdjsrq\" size=\"8\" maxlength=\"8\" value=\"<%=skssjsrq%>\" onchange=\"if(isDate(this)){computer(this);return true;}else{return false;}\">";

	<%/*ԭ��ס��Ӧ����˰��*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name=\"yjzgyjnsk\" size=\"7\" maxlength=\"16\" onchange=\"if(checkvalue(this,0)&&formatCurrency(this,0)){computer(this);return true;}else{return false;}\">"));

	<%/*�����۳���*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name=\"fdkce\" size=\"7\" maxlength=\"16\" onchange=\"if(checkvalue(this,0)&&formatCurrency(this,0)){computer(this);return true;}else{return false;}\">"));

	<%/*�����*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name=\"srermb\" size=\"7\" maxlength=\"16\" onchange=\"if(checkvalue(this,0)&&formatCurrency(this,0)){computerRmbhj(this);return true;}else{return false;}\">"));

	<%/*�ۺ������*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name=\"zhrmb\" size=\"7\" maxlength=\"16\" onfocus=\"zhrmb_now = this;fillWhpj();hideAllSbb();whpjdiv.style.display='';\" onchange=\"if(checkvalue(this,0)&&formatCurrency(this,0)){computerRmbhj(this);return true;}else{return false;}\">"));

	<%/*����Һϼ�*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name=\"rmbhj\" size=\"7\" maxlength=\"16\" onchange=\"if(checkvalue(this,0)&&formatCurrency(this,0)){computer(this);return true;}else{return false;}\">"));

	<%/*�����ö�*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name=\"jfye\" onchange=\"if(checkvalue(this,0)&&formatCurrency(this,1)){jfye_tmp=this.value;computer(this);return true;}else{return false;}\" size=\"4\" maxlength=\"16\" value=\""+jfye_def+"\">"));

	<%/*Ӧ��˰���ö�*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name=\"ynssde\" size=\"7\" maxlength=\"16\" onchange=\"if(checkvalue(this,0)&&formatCurrency(this,1)){computer(this);return true;}else{return false;}\">"));

	<%/*Ӧ��˰��*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name=\"ynse\" size=\"7\" maxlength=\"16\" onchange=\"if(checkvalue(this,0)&&formatCurrency(this,1)){computer(this);return true;}else{return false;}\">"));

	<%/*�ѿ۽�˰��*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name=\"ykjse\" size=\"7\" maxlength=\"16\" onchange=\"if(checkvalue(this,0)&&formatCurrency(this,0)){computer(this);return true;}else{return false;}\">"));

	<%/*Ӧ�����ˣ�˰��*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name=\"ybtsk\" size=\"7\" maxlength=\"16\" onchange=\"if(checkvalue(this,0)&&formatCurrency(this,1)){computer(this);return true;}else{return false;}\">"));

	<%/*ɾ��*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-center";
	cell.noWrap = true;
	cell.innerHTML = "<input type=\"hidden\" name=\"bzdm\"><input type=\"hidden\" name=\"je\"><input type=\"hidden\" name=\"whpj\"><input type=\"hidden\" name=\"zh\"><a href=\"#\" id=\"delIndex\" onclick=\"doDelete(this);return false;\">ɾ��</a>";

	return false;
}

<%/*����*/%>
function doReturn()
{
	if(confirm(confirmReturn))
	{
       		document.forms[0].actionType.value = "Return";
		document.forms[0].submit();
       		return true;
    	}
	else
	{
       		return false;
    	}
}
<%/*ɾ��*/%>
function doDelete(obj)
{
	if(confirm(confirmDelete))
	{
		var delRows = mxtable.rows.length-3;
		for(var i=0;i<delRows;i++)
		{
			if(delIndex[i] == obj)
			{
       				mxtable.deleteRow(i+2);
				bc[i-1] = null;
			}
	        }
    	}
	else
	{
       		return false;
    	}
}

/*
����
*/
function doSave()
{
	itemsize = mxtable.rows.length-4;
	if(itemsize==0)
	{
		alert("����д��ϸ���ݣ�");
		return false;
	}
	else if(itemsize>1)
	{
		for(i=0;i<itemsize;i++)
		{
			var sdksrq = document.forms[0].sdksrq[i].value;
			var sdjsrq = document.forms[0].sdjsrq[i].value;
			var ybtsk = document.forms[0].ybtsk[i].value;
			if(sdksrq==null||sdksrq==""||sdjsrq==null||sdjsrq=="")
			{
				alert("����д�����ڼ䣡");
				return false;
			}
			if(ybtsk==null||ybtsk==""||parseFloat(ybtsk)==0)
			{
				alert("�Ѿ�ȡ��0�걨���������ݲ����棬��ɾ�����޸ģ�");
				return false;
			}
		}
	}
	else
        {
		if(document.forms[0].sdksrq.length == null)
		{
			var sdksrq = document.forms[0].sdksrq.value;
			var sdjsrq = document.forms[0].sdjsrq.value;
			var ybtsk = document.forms[0].ybtsk.value;
			if(sdksrq==null||sdksrq==""||sdjsrq==null||sdjsrq=="")
			{
				alert("����д�����ڼ䣡");
				return false;
			}
			if(ybtsk==null||ybtsk==""||parseFloat(ybtsk)==0)
			{
				alert("�Ѿ�ȡ��0�걨���������ݲ����棬��ɾ�����޸ģ�");
				return false;
			}
		}
		else
		{
			var sdksrq = document.forms[0].sdksrq[0].value;
			var sdjsrq = document.forms[0].sdjsrq[0].value;
			var ybtsk = document.forms[0].ybtsk[0].value;
			if(sdksrq==null||sdksrq==""||sdjsrq==null||sdjsrq=="")
			{
				alert("����д�����ڼ䣡");
				return false;
			}
			if(ybtsk==null||ybtsk==""||parseFloat(ybtsk)==0)
			{
				alert("�Ѿ�ȡ��0�걨���������ݲ����棬��ɾ�����޸ģ�");
				return false;
			}
		}
	}
	document.forms[0].actionType.value = "Save";
		//started added by qianchao 2006-2-8
		if (g_objSI.Container != '')
		{
	        if (!doSecuritySubmit(document.forms[0]))
	        {
		        return false;
	        }
	    }
	    else
	    {
	        document.forms[0].submit();
	    }
		//ended   added by qianchao 2006-2-8
	
	return true;
}
function isDate(obj)
{
	var date = obj.value;
	if(!checkDate(date))
	{
		alert("���ڸ�ʽ����");
		obj.focus();
		obj.select();
		return false;
	}
	else
	{
		return true;
	}
}

function setYh()
{
	if(document.forms[0].yhzh.selectedIndex == 0)
	{
		document.forms[0].yhdm.value = "";
		document.forms[0].yhmc.value = "";
	}
	else
	{
		document.forms[0].yhdm.value = yh[document.forms[0].yhzh.selectedIndex-1][1];
		document.forms[0].yhmc.value = yh[document.forms[0].yhzh.selectedIndex-1][0];
	}
}
</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">
<table width="98%" height="100%" border='0' cellpadding='0' cellspacing='0' align='center' class='black9'>
  <tr>
    <td align="center" valign="top">
    	<jsp:include page="../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="��Ȼ�˸�������˰�걨��" />
		<jsp:param name="help_url" value="help/wssb/sbzl/zrrsb/zrrsb-000.htm"/>
    	</jsp:include>
    </td>
  </tr>
<tr>
<td align="center">
          <html:errors/>
</td>
</tr>
<tr>
<td valign="middle" align="center">
<br>
<html:form method="POST" action="zrrsb.do">
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
  <tr>
    <td class="1-td1">��Ȼ�˸�������˰�걨��
	<html:hidden property="actionType"/></td>
  </tr>
  <tr>
     <td width="100%" class="1-td2" align="center">
	<br>
      <table align="center" cellspacing="0" class="table-99">
              <tr class="black9">
                <td><div align="right">˰����������&nbsp;&nbsp;</div>
                </td>
                <td nowrap>
		<div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="skssksrq"/>&nbsp;��&nbsp;<shenbao:write name="zrrsbForm" property="skssjsrq"/></div>
                </td>
                <td nowrap><div align="right">�������&nbsp;&nbsp;</div>
                </td>
                <td nowrap>
		<div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="sbrq"/></div>
                </td>
                <td colspan="2" nowrap>
                	<div align="right">��λ�������Ԫ&nbsp;&nbsp;</div>
                </td>
              </tr>
              <tr>
                <td class="2-td2-t-left" nowrap>
                      <div align="right">��˰�˱���&nbsp;&nbsp;</div>
		</td>
                <td class="2-td2-t-left" nowrap>
                      <div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="jsjdm"/></div>
                </td>
		<td class="2-td2-t-left" nowrap>
                	<div align="right">��˰������&nbsp;&nbsp;</div>
		</td>
                <td class="2-td2-t-left" nowrap>
                      <div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="nsrmc"/></div>
                </td>
		<td class="2-td2-t-left"><div align="right">�ֻ�����&nbsp;&nbsp;</div>
		</td>
		<td class="2-td2-t-center">
		<div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="dhrq"/></div>
		</td>
              </tr>
              <tr>
		<td class="2-td2-left" nowrap>
		<div align="right">����&nbsp;&nbsp;</div>
		</td>
                <td class="2-td2-left" nowrap>
                      <div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="gjmc"/></div>
                </td>
		<td class="2-td2-left" nowrap>
			<div align="right">֤������&nbsp;&nbsp;</div>
		</td>
                <td class="2-td2-left" nowrap>
                      <div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="zjlxmc"/></div>
                </td>
		<td class="2-td2-left" nowrap>
			<div align="right">֤������&nbsp;&nbsp;</div>
		</td>
                <td class="2-td2-center" nowrap>
                      <div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="zjhm"/></div>
                </td>
              </tr>
              <tr>
		<td class="2-td2-left" nowrap>
		<div align="right">������ʽ&nbsp;&nbsp;</div>
		</td>
                <td class="2-td2-left" nowrap>
                      <div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="fdfsmc"/><html:hidden name="zrrsbForm" property="fdfsdm"/></div>
                </td>
		<td class="2-td2-left" nowrap>
		<div align="right">��������&nbsp;&nbsp;</div>
		</td>
                <td class="2-td2-left" nowrap>
                      <div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="dwfdbl"/>%<html:hidden name="zrrsbForm" property="dwfdbl"/></div>
                </td>
		<td class="2-td2-left" nowrap>
		<div align="right">�ⷿ�ѿ۳���&nbsp;&nbsp;</div>
		</td>
                <td class="2-td2-center" nowrap>
                      <div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="zffkce"/></div>
                </td>
	</tr>
	<tr>
		<td class="2-td2-left"><div align="right">���й�����סַ&nbsp;&nbsp;</div>
		</td>
		<td class="2-td2-left" colspan="3">
                <div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="jnzz"/></div>
		</td>
		<td class="2-td2-left"><div align="right">�Ƿ�פ&nbsp;&nbsp;</div>
		</td>
		<td class="2-td2-center">
		<div align="left">&nbsp;&nbsp;<%if(zrrForm.getSfczbs()!=null && zrrForm.getSfczbs().equals("1")){%>��<%}else{%>��<%}%>
		<html:hidden name="zrrsbForm" property="sfczbs"/></div>
		</td>
	</tr>
	<tr>
		<td class="2-td2-left">
		<div align="right">ְҵ&nbsp;&nbsp;</div></td>
		<td class="2-td2-left">
		<div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="zymc"/></div></td>
		<td class="2-td2-left">
		<div align="right">����λ&nbsp;&nbsp;</div>
		<td class="2-td2-left">
		<div align="left">&nbsp;&nbsp;<html:text name="zrrsbForm" property="fwdw"/></div>
		</td>
		<td class="2-td2-left">
		<div align="right">����ص�&nbsp;&nbsp;</div>
		<td class="2-td2-center">
		<div align="left">&nbsp;&nbsp;<html:text name="zrrsbForm" property="fwdd"/></div>
		</td>
	<tr>
	<tr>
		<td class="2-td2-left">
		<div align="right">�����ʺ�&nbsp;&nbsp;</div></td>
		<td class="2-td2-left">
		<div align="left">&nbsp;&nbsp;
		<select name="yhzh" onchange="setYh()">
			<option value="" selected></option>
		<%
			for(int i=0;i<yhList.size();i++)
			{
				ZRRYHZH yhTmp = (ZRRYHZH)yhList.get(i);
				out.println("<option value=\""+yhTmp.getZh()+"\">"+yhTmp.getZh()+"</option>");
			}
		%>
		</select>
		</div></td>
		<td class="2-td2-left">
		<div align="right">��������&nbsp;&nbsp;</div>
		<td class="2-td2-left">
		<div align="left">&nbsp;&nbsp;<input name="yhmc" readonly class="inputnoborder"></div>
		</td>
		<td class="2-td2-left">
		<div align="right">&nbsp;&nbsp;<input type="hidden" name="yhdm"></div>
		<td class="2-td2-center">
		<div align="left">&nbsp;&nbsp;</div>
		</td>
	<tr>
</table>
<br>
<div id="zrrmx">
<table id="mxtable" border="0" cellpadding="0" cellspacing="0" class="table-99">
  <tr>
    <td rowspan="3" class="2-td1-left">������Ŀ</td>
    <td rowspan="3" class="2-td1-left">�����ڼ�</td>
    <td rowspan="3" class="2-td1-left">ԭ��ס��Ӧ����˰��</td>
    <td rowspan="3" class="2-td1-left">�����۳���</td>
    <td colspan="3" class="2-td1-left">�����</td>
    <td rowspan="3" width="40" class="2-td1-left">�����ö�</td>
    <td rowspan="3" class="2-td1-left">Ӧ��˰���ö�</td>
    <td rowspan="3" class="2-td1-left">Ӧ��˰��</td>
    <td rowspan="3" class="2-td1-left">�ѿ۽�˰��</td>
    <td rowspan="3" class="2-td1-left">Ӧ�����ˣ�˰��</td>
    <td nowrap rowspan="3" class="2-td1-center" id="delIndex">&nbsp;&nbsp;</td>
  </tr>
  <tr>
    <td rowspan="2" class="2-td1-left">�����</td>
    <td class="2-td1-left">���</td>
    <td rowspan="2" class="2-td1-left">����Һϼ�</td>
  </tr>
  <tr>
    <td class="2-td1-left">�ۺ������</td>
  </tr>
<%
if(zrrmxList == null || zrrmxList.size() == 0)
{
%>
  <tr>
    <td class="2-td2-left">
	<select name="szsmdm" onchange="jfye_tmp=null;computer(this);">
<%
for(int i=0;i<szsmList.size();i++)
{
	Szsm szsm = (Szsm)szsmList.get(i);
%>
	<option value="<%=szsm.getSzsmdm()%>"><%=szsm.getSzsmmc()%></option>
<%
}
%>
	</select>
    </td>
    <td nowrap class="2-td2-left">
	<input name="sdksrq" size="8" value="<%=skssksrq%>" maxlength="8" onchange="if(isDate(this)){computer(this);return true;}else{return false;}">-<input name="sdjsrq" size="8" maxlength="8" value="<%=skssjsrq%>" onchange="if(isDate(this)){computer(this);return true;}else{return false;}">
    </td>
    <td class="2-td2-left">
	<input name="yjzgyjnsk" size="7" maxlength="16" onchange="if(checkvalue(this,0)&&formatCurrency(this,0)){computer(this);return true;}else{return false;}">
    </td>
    <td class="2-td2-left">
	<input name="fdkce" size="7" maxlength="16" onchange="if(checkvalue(this,0)&&formatCurrency(this,0)){computer(this);return true;}else{return false;}">
    </td>
    <td class="2-td2-left">
	<input name="srermb" size="7" maxlength="16" onchange="if(checkvalue(this,0)&&formatCurrency(this,0)){computerRmbhj(this);return true;}else{return false;}">
    </td>
    <td class="2-td2-left">
	<input name="zhrmb" size="7" maxlength="16" onfocus="zhrmb_now = this;fillWhpj();hideAllSbb();whpjdiv.style.display='';" onchange="if(checkvalue(this,0)&&formatCurrency(this,0)){computerRmbhj(this);return true;}else{return false;}">
    </td>
    <td class="2-td2-left">
	<input name="rmbhj" size="7" maxlength="16" onchange="if(checkvalue(this,0)&&formatCurrency(this,0)){computer(this);return true;}else{return false;}">
    </td>
    <td class="2-td2-left">
	<logic:equal name="zrrsbForm" property="gjmc" value="�й�">
	<input name="jfye" size="4" value="1200" maxlength="16" onchange="if(checkvalue(this,0)&&formatCurrency(this,1)){jfye_tmp=this.value;computer(this);return true;}else{return false;}">
	</logic:equal>
	<logic:notEqual name="zrrsbForm" property="gjmc" value="�й�">
	<input name="jfye" size="4" value="4000" maxlength="16" onchange="if(checkvalue(this,0)&&formatCurrency(this,1)){jfye_tmp=this.value;computer(this);return true;}else{return false;}">
	</logic:notEqual>
    </td>
    <td class="2-td2-left">
	<input name="ynssde" size="7" maxlength="16" onchange="if(checkvalue(this,0)&&formatCurrency(this,1)){computer(this);return true;}else{return false;}">
    </td>
    <td class="2-td2-left">
	<input name="ynse" size="7" maxlength="16" onchange="if(checkvalue(this,0)&&formatCurrency(this,1)){computer(this);return true;}else{return false;}">
    </td>
    <td class="2-td2-left">
	<input name="ykjse" size="7" maxlength="16" onchange="if(checkvalue(this,0)&&formatCurrency(this,0)){computer(this);return true;}else{return false;}">
    </td>
    <td class="2-td2-left">
	<input name="ybtsk" size="7" maxlength="16" onchange="if(checkvalue(this,0)&&formatCurrency(this,1)){computer(this);return true;}else{return false;}">
    </td>
    <td nowrap class="2-td2-center"><input type="hidden" name="bzdm"><input type="hidden" name="je"><input type="hidden" name="whpj"><input type="hidden" name="zh"><a href="#" id="delIndex" onclick="doDelete(this);return false;">ɾ��</a> </td>
  </tr>
<%
}
else
{
	for(int i=0;i<zrrmxList.size();i++)
	{
		Zrrgrsdsmx mx = (Zrrgrsdsmx)zrrmxList.get(i);
%>
  <tr>
    <td class="2-td2-left">
	<select name="szsmdm" onchange="jfye_tmp=null;computer(this);">
<%
		for(int j=0;j<szsmList.size();j++)
		{
			Szsm szsm = (Szsm)szsmList.get(j);
			if(szsm.getSzsmdm().equals(mx.getSzsmdm()))
			{
%>
	<option selected value="<%=szsm.getSzsmdm()%>"><%=szsm.getSzsmmc()%></option>
<%
			}
			else
			{
%>
	<option value="<%=szsm.getSzsmdm()%>"><%=szsm.getSzsmmc()%></option>
<%
			}
}
%>
	</select>
    </td>
    <td nowrap class="2-td2-left">
	<input name="sdksrq" size="8" maxlength="8" value="<%=mx.getSdksrqStr()%>" onchange="if(isDate(this)){computer(this);return true;}else{return false;}">-<input name="sdjsrq" size="8" maxlength="8" value=<%=mx.getSdjsrqStr()%> onchange="if(isDate(this)){computer(this);return true;}else{return false;}">
    </td>
    <td class="2-td2-left">
	<input name="yjzgyjnsk" size="7" maxlength="16" value="<%=JspUtil.format(mx.getYjzgsk())%>" onchange="if(checkvalue(this,0)&&formatCurrency(this,0)){computer(this);return true;}else{return false;}">
    </td>
    <td class="2-td2-left">
	<input name="fdkce" size="7" maxlength="16" value="<%=JspUtil.format(mx.getFdkce())%>" onchange="if(checkvalue(this,0)&&formatCurrency(this,0)){computer(this);return true;}else{return false;}">
    </td>
    <td class="2-td2-left">
	<input name="srermb" size="7" maxlength="16" value="<%=JspUtil.format(mx.getSrermb())%>" onchange="if(checkvalue(this,0)&&formatCurrency(this,0)){computerRmbhj(this);return true;}else{return false;}">
    </td>
    <td class="2-td2-left">
	<input name="zhrmb" size="7" maxlength="16" value="<%=JspUtil.format(mx.getZhrmb())%>" onfocus="zhrmb_now = this;fillWhpj();hideAllSbb();whpjdiv.style.display='';" onchange="if(checkvalue(this,0)&&formatCurrency(this,0)){computerRmbhj(this);return true;}else{return false;}">
    </td>
    <td class="2-td2-left">
	<input name="rmbhj" size="7" maxlength="16" value="<%=JspUtil.format(mx.getRmbhj())%>" onchange="if(checkvalue(this,0)&&formatCurrency(this,0)){computer(this);return true;}else{return false;}">
    </td>
    <td class="2-td2-left">
	<input name="jfye" size="4" value="<%=JspUtil.format(mx.getJfye())%>" maxlength="16" onchange="if(checkvalue(this,0)&&formatCurrency(this,1)){jfye_tmp=this.value;computer(this);return true;}else{return false;}">
    </td>
    <td class="2-td2-left">
	<input name="ynssde" size="7" maxlength="16" value="<%=JspUtil.format(mx.getYnssde())%>" onchange="if(checkvalue(this,0)&&formatCurrency(this,1)){computer(this);return true;}else{return false;}">
    </td>
    <td class="2-td2-left">
	<input name="ynse" size="7" maxlength="16" value="<%=JspUtil.format(mx.getYnse())%>" onchange="if(checkvalue(this,0)&&formatCurrency(this,1)){computer(this);return true;}else{return false;}">
    </td>
    <td class="2-td2-left">
	<input name="ykjse" size="7" maxlength="16" value="<%=JspUtil.format(mx.getYkjse())%>" onchange="if(checkvalue(this,0)&&formatCurrency(this,0)){computer(this);return true;}else{return false;}">
    </td>
    <td class="2-td2-left">
	<input name="ybtsk" size="7" maxlength="16" value="<%=JspUtil.format(mx.getYbtsk())%>" onchange="if(checkvalue(this,0)&&formatCurrency(this,1)){computer(this);return true;}else{return false;}">
    </td>
    <td nowrap class="2-td2-center"><input type="hidden" name="bzdm" value="<%=mx.getBzdmStr()%>"><input type="hidden" name="je" value="<%=mx.getJeStr()%>"><input type="hidden" name="whpj" value="<%=mx.getWhpjStr()%>"><input type="hidden" name="zh" value="<%=mx.getZhStr()%>"><a href="#" id="delIndex" onclick="doDelete(this);return false;">ɾ��</a> </td>
  </tr>
<%
	}
}
%>
  <tr>
    <td colspan="13">
     <br>
      <div align="center">
		<img src="<%=static_contextpath%>images/tianjia1.jpg" onmouseover="this.src='<%=static_contextpath%>images/tianjia2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/tianjia1.jpg'" alt="����" onclick="doAdd();" style="cursor:hand">
        &nbsp;&nbsp;&nbsp;&nbsp;
		<img src="<%=static_contextpath%>images/baocun1.jpg" onmouseover="this.src='<%=static_contextpath%>images/baocun2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/baocun1.jpg'" alt="����" onclick="return doSave()" style="cursor:hand">
        &nbsp;&nbsp;&nbsp;&nbsp;
		<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="����" onclick="doReturn()" style="cursor:hand">
        &nbsp;&nbsp;&nbsp;&nbsp;
		<a href="zrrJks.do">
		<img src="<%=static_contextpath%>images/b-ckbqjks1.jpg" onmouseover="this.src='<%=static_contextpath%>images/b-ckbqjks2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/b-ckbqjks1.jpg'" alt="ά���ɿ���" style="cursor:hand" border="0">
		</a>
       </div>
    </td>
  </tr>
</table>
</div>
<script language="javascript">
/*
�����ۺ������
*/
function fun_computerZhrmb(i)
{
	itemsize = whpjtable.rows.length-3;
	if(itemsize > 1)
	{
		var je = document.forms[0].whpj_je[i].value;
		var zhrmb = document.forms[0].whpj_zhrmb[i];
		var whpj = document.forms[0].whpj_whpj[i].value = bz[document.forms[0].whpj_bzdm[i].selectedIndex][1];
	}
	else
	{
		if(document.forms[0].whpj_je.length == null)
		{
			var je = document.forms[0].whpj_je.value;
			var zhrmb = document.forms[0].whpj_zhrmb;
			var whpj = document.forms[0].whpj_whpj.value = bz[document.forms[0].whpj_bzdm.selectedIndex][1];
		}
		else
		{
			var je = document.forms[0].whpj_je[0].value;
			var zhrmb = document.forms[0].whpj_zhrmb[0];
			var whpj = document.forms[0].whpj_whpj[0].value = bz[document.forms[0].whpj_bzdm.selectedIndex][1];
		}
    	}
    	if(je != null && je != "")
    	{
		zhrmb.value = Math.round(je * whpj * 100)/100;
		formatCurrency(zhrmb,0);
		computerZhrmbhj();
    	}
}

/*
�����ۺ��ۺ�����Һϼ�
*/
function computerZhrmbhj()
{
	itemsize = whpjtable.rows.length-3;
	var total = 0;
	if(itemsize > 1)
	{
		for(i=0;i<itemsize;i++)
		{
			if(document.forms[0].whpj_zhrmb[i].value != null &&
			trim(document.forms[0].whpj_zhrmb[i].value) != "" &&
			!isNaN(document.forms[0].whpj_zhrmb[i].value))
			{
				total = parseFloat(total) + parseFloat(document.forms[0].whpj_zhrmb[i].value);
			}
		}
	}
	else if(itemsize == 1)
	{
		if(document.forms[0].whpj_zhrmb.length == null)
		{
			if(document.forms[0].whpj_zhrmb.value != null &&
			trim(document.forms[0].whpj_zhrmb.value) != "" &&
			!isNaN(document.forms[0].whpj_zhrmb.value))
				total = parseFloat(total) + parseFloat(document.forms[0].whpj_zhrmb.value)
		}
		else
		{
			if(document.forms[0].whpj_zhrmb[0].value != null &&
			trim(document.forms[0].whpj_zhrmb[0].value) != "" &&
			!isNaN(document.forms[0].whpj_zhrmb[0].value))
				total = parseFloat(total) + parseFloat(document.forms[0].whpj_zhrmb[0].value)
		}
	}
	document.forms[0].zhrmbhj.value = Math.round(total*100)/100;
	formatCurrency(document.forms[0].zhrmbhj,0);
}

/*
ȡ�ò���������������
*/
function getWhpjRow(obj)
{
	itemsize = whpjtable.rows.length-3;
	if(itemsize > 1)
	{
		var row = 0;
		for(var i=0;i<itemsize;i++)
		{
			if(eval("document.forms[0]." + obj.name + "[i]") == obj)
			{
				row = i;
				break;
			}
		}
		return row;
	}
	else
		return 0;
}

/*
�������Ƽ�
*/
function fillWhpj()
{
	if(bc.length > getRow(zhrmb_now) && bc[getRow(zhrmb_now)] != null)	//��������ύ���ݴ��������ұ����ύ���ݲ�Ϊ��
	{
		var bzdmArray = bc[getRow(zhrmb_now)][0];	//���ִ�������
		var jeArray = bc[getRow(zhrmb_now)][1];		//�������
		var whpjArray = bc[getRow(zhrmb_now)][2];	//����Ƽ�����
		var zhArray = bc[getRow(zhrmb_now)][3];		//�ۺ�����
		itemsize = whpjtable.rows.length-3;
		//�������Ƽ۱��
		for(i=0;i<itemsize;i++)
		{
			whpjtable.deleteRow(1);
		}
		//���������д�����Ҵ���1��
		if(bzdmArray.length>1)
		{
			//��������д���ݱ��
			for(i=0;i<bzdmArray.length;i++)
			{
				doAddWhpj();
			}
			for(i=0;i<bzdmArray.length;i++)
			{
				for(j=0;j<document.forms[0].whpj_bzdm[i].options.length;j++)
				{
					if(document.forms[0].whpj_bzdm[i].options[j].value==bzdmArray[i])
					{
						document.forms[0].whpj_bzdm[i].selectedIndex = j;
						break;
					}
				}
				if(jeArray[i]!=null)
					document.forms[0].whpj_je[i].value = jeArray[i];
				if(whpjArray[i]!=null)
					document.forms[0].whpj_whpj[i].value = whpjArray[i];
				if(zhArray[i]!=null)
					document.forms[0].whpj_zhrmb[i].value = zhArray[i];
			}
		}
        else	//ֻ��һ������д����
		{
			doAddWhpj();
            for(j=0;j<document.forms[0].whpj_bzdm.options.length;j++)
            {
                if(document.forms[0].whpj_bzdm.options[j].value==bzdmArray[0])
				{
                    document.forms[0].whpj_bzdm.selectedIndex = j;
					break;
				}
            }
            if(jeArray[0]!=null)
				document.forms[0].whpj_je.value = jeArray[0];
			if(whpjArray[0]!=null)
				document.forms[0].whpj_whpj.value = whpjArray[0];
			if(zhArray[0]!=null)
				document.forms[0].whpj_zhrmb.value = zhArray[0];
		}
	}
	else	//û������д���ݣ���ձ�������һ������
	{
		itemsize = whpjtable.rows.length-3;
		for(i=0;i<itemsize;i++)
		{
			whpjtable.deleteRow(1);
		}
		doAddWhpj();
	}
	computerZhrmbhj();
}

<%/*����*/%>
function doReturnWhpj()
{
	if(parseFloat(document.forms[0].zhrmbhj.value)!=0)
	{
		zhrmb_now.value = document.forms[0].zhrmbhj.value;
	}
	else
	{
		zhrmb_now.value = "";
	}
	//�����Ӧ�������Һϼ�
	computerRmbhj(zhrmb_now);
	var whpj_bzdm = new Array();	//��ű��ִ��������
	var whpj_je = new Array();		//��Ž�������
	var whpj_whpj = new Array();	//�������Ƽ۵�����
	var whpj_zh = new Array();		//����ۺϽ�������
	itemsize = whpjtable.rows.length-3;
	if(itemsize>1)	//���ֻ��һ������
	{
		for(i=0;i<document.forms[0].whpj_je.length;i++)
		{
			if(document.forms[0].whpj_je[i].value != null &&
			trim(document.forms[0].whpj_je[i].value) != "" &&
			document.forms[0].whpj_zhrmb[i].value != null &&
    			trim(document.forms[0].whpj_zhrmb[i].value) != "")
			{
				whpj_bzdm[i] = document.forms[0].whpj_bzdm[i].value;
				whpj_je[i] = document.forms[0].whpj_je[i].value;
				whpj_whpj[i] = document.forms[0].whpj_whpj[i].value;
				whpj_zh[i] = document.forms[0].whpj_zhrmb[i].value;
			}
		}
	}
	else if(itemsize==1)
	{
		if(document.forms[0].whpj_je.length!=null)
		{
			if(document.forms[0].whpj_je[0].value != null &&
			trim(document.forms[0].whpj_je[0].value) != "" &&
			document.forms[0].whpj_zhrmb[0].value != null &&
    			trim(document.forms[0].whpj_zhrmb[0].value) != "")
			{
				whpj_bzdm[0] = document.forms[0].whpj_bzdm.value;
				whpj_je[0] = document.forms[0].whpj_je[0].value;
				whpj_whpj[0] = document.forms[0].whpj_whpj[0].value;
				whpj_zh[0] = document.forms[0].whpj_zhrmb[0].value;
			}
		}
		else
		{
			if(document.forms[0].whpj_je.value != null &&
			trim(document.forms[0].whpj_je.value) != "" &&
			document.forms[0].whpj_zhrmb.value != null &&
    			trim(document.forms[0].whpj_zhrmb.value) != "")
			{
				whpj_bzdm[0] = document.forms[0].whpj_bzdm.value;
				whpj_je[0] = document.forms[0].whpj_je.value;
				whpj_whpj[0] = document.forms[0].whpj_whpj.value;
				whpj_zh[0] = document.forms[0].whpj_zhrmb.value;
			}
		}
	}
	else
    {
		hideAllSbb();	//�������б��
		zrrmx.style.display='';	//��ʾ��Ȼ����ϸ���
		return true;
	}
		//�ѱ����걨������Ƽ����ݱ��浽���飨��Ӧ��ϸ������
		bc[getRow(zhrmb_now)] = [whpj_bzdm,whpj_je,whpj_whpj,whpj_zh];
		itemsize = mxtable.rows.length-4;
		//������Ƽ���Ϣ��ʽ�����ַ���������������
		if(itemsize > 1)
		{
			document.forms[0].bzdm[getRow(zhrmb_now)].value = whpj_bzdm.toString();
			document.forms[0].je[getRow(zhrmb_now)].value = whpj_je.toString();
			document.forms[0].whpj[getRow(zhrmb_now)].value = whpj_whpj.toString();
			document.forms[0].zh[getRow(zhrmb_now)].value = whpj_zh.toString();
		}
		else
		{
			if(document.forms[0].je.length == null)
			{
				document.forms[0].bzdm.value = whpj_bzdm.toString();
				document.forms[0].je.value = whpj_je.toString();
				document.forms[0].whpj.value = whpj_whpj.toString();
				document.forms[0].zh.value = whpj_zh.toString();
			}
			else
			{
				document.forms[0].bzdm[0].value = whpj_bzdm.toString();
				document.forms[0].je[0].value = whpj_je.toString();
				document.forms[0].whpj[0].value = whpj_whpj.toString();
				document.forms[0].zh[0].value = whpj_zh.toString();
			}
		}
	hideAllSbb();
	zrrmx.style.display='';
	return true;
}

function gotopage(page)
{
	itemsize = whpjtable.rows.length-3;
	if(page<1)
		page=1;
	var startpage = (page-1)*20;
	var endpage = page *20;
	for(i=0;i<itemsize;i++)
	{
		if(i>=startpage && i<endpage)
			whpjrow[i].style.display = '';
		else
			whpjrow[i].style.display='none';
	}
}

/*
ɾ�����������Ӧ��һ��
*/
function doDeleteWhpj(obj)
{
	if(confirm(confirmDelete))
	{
		var delRows = whpjtable.rows.length;
		for(var i=0;i<delRows;i++)
		{
			if(delWhpjIndex[i] == obj)
			{
       				whpjtable.deleteRow(i);
			}
	        }
		computerZhrmbhj();
    	}
	else
	{
       		return false;
    	}
}

/*
���һ������Ƽ۱��
*/
function doAddWhpj()
{
	itemsize = whpjtable.rows.length-3;
	var row = whpjtable.insertRow(whpjtable.rows.length-2);

	var sel = document.createElement("<select name=\"whpj_bzdm\" onchange=\"fun_computerZhrmb(getWhpjRow(this))\">");
	for (var i=0; i<bz.length; i++)
	{
		var opt = document.createElement("<OPTION>");
		opt.value = bz[i][0];
		opt.text = bz[i][0];
		sel.options.add(opt);
	}

	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(sel);

	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.noWrap = true;
	cell.innerHTML = "<input name=\"whpj_je\" size=\"16\" maxlength=\"16\" onchange=\"if(checkvalue(this,0)&&formatCurrency(this,0)){fun_computerZhrmb(getWhpjRow(this));return true;}else{return false;}\">";

	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.noWrap = true;
	cell.innerHTML = "<input name=\"whpj_whpj\" size=\"16\" maxlength=\"16\" value=\""+bz[0][1]+"\" readonly class=\"inputnoborder\">";

	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.noWrap = true;
	cell.innerHTML = "<input name=\"whpj_zhrmb\" size=\"16\" maxlength=\"16\" onchange=\"if(checkvalue(this,0)&&formatCurrency(this,0)){computerZhrmbhj();return true;}else{return false;}\">";

	var cell = row.insertCell();
	cell.className = "2-td2-center";
	cell.noWrap = true;
	cell.innerHTML = "<a href=\"#\" id=\"delWhpjIndex\" onclick=\"doDeleteWhpj(this);return false;\">ɾ��</a>";
}
</script>
<div id="whpjdiv" style="display:none">
<table id="whpjtable" border="0" cellpadding="0" cellspacing="0" class="table-99">
  <tr>
    <td class="2-td1-left">��������</td>
    <td class="2-td1-left">���</td>
    <td class="2-td1-left">����Ƽ�</td>
    <td class="2-td1-left">�ۺ������</td>
    <td class="2-td1-center" id="delWhpjIndex">&nbsp;&nbsp;</td>
  </tr>
  <tr>
    <td class="2-td2-left">
	<select name="whpj_bzdm" onchange="fun_computerZhrmb(0)">
<%
	for(int i=0;i<bzList.size();i++)
	{
		Wbhs bz = (Wbhs)bzList.get(i);
		out.print("<option value='"+bz.getBzdm()+"'>"+bz.getBzdm()+"</option>");
	}
%>
	</select>
    </td>
    <td class="2-td2-left">
    <input name="whpj_je" size="16" maxlength="16" onchange="if(checkvalue(this,0)&&formatCurrency(this,0)){fun_computerZhrmb(getWhpjRow(this));return true;}else{return false;}">
    </td>
    <td class="2-td2-left">
    <input name="whpj_whpj" size="16" maxlength="16" value="<%=JspUtil.format(((Wbhs)bzList.get(0)).getWhpj())%>" readonly class="inputnoborder">
    </td>
    <td class="2-td2-left"><input name="whpj_zhrmb" size="16" maxlength="16" onchange="if(checkvalue(this,0)&&formatCurrency(this,0)){computerZhrmbhj();return true;}else{return false;}"></td>
    <td class="2-td2-center" nowrap><a href="#" id="delWhpjIndex" onclick="doDeleteWhpj(this);return false;">ɾ��</a></td>
  </tr>
  <tr>
    <td class="2-td2-left" colspan="3">�ϼ�</td>
    <td class="2-td2-left"><input name="zhrmbhj" size="16" maxlength="16" readonly class="inputnoborder" value="0.00"></td>
    <td class="2-td2-center">&nbsp;&nbsp;</td>
  </tr>
  <tr>
    <td colspan="5">
     <br>
      <div align="center">
		<img src="<%=static_contextpath%>images/tianjia1.jpg" onmouseover="this.src='<%=static_contextpath%>images/tianjia2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/tianjia1.jpg'" alt="����" onclick="doAddWhpj();" style="cursor:hand">
        &nbsp;&nbsp;&nbsp;&nbsp;
		<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="����" onclick="doReturnWhpj()" style="cursor:hand">

       </div>
    </td>
  </tr>
</table>
</div>
<br>
   </td>
   </tr>
</table>
</html:form>
</td>
</tr>
<tr><td valign="bottom" align="center">
<br>
<%@ include file="../include/bottom.jsp" %>
 </td>
</tr>
</table>
</body>
</html>