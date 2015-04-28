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
<title>自然人个人所得税申报表</title>
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
/*取两日期间隔天数*/
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
// 税种税目代码，税种税目名称，税率，速算扣除数，应纳税起始数，应纳税终止数，不含税所得税率，不含税所得起始，不含税所得终止，不含税所得速算扣除数
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

//计算人民币合计
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

<%/*取得适用税率*/%>
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

//取得不含税的税率
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

//取得税率
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

//取奖金税率
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

//取调资税率
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

//取不含税的调资税率
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

//取调资速算扣除数
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

//取不含税的调资速算扣除数
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

//自动计算（根据情况调用不同计算函数）
function computer(obj)
{
	var row = getRow(obj);
	itemsize = mxtable.rows.length-4;
	//取得所需数据
	if(itemsize > 1)
	{
		//var srermb = document.forms[0].srermb[row].value;
		//var zhrmb = document.forms[0].zhrmb[row].value;
		var sdksrq = document.forms[0].sdksrq[row].value;	//所得开始日期
		var sdjsrq = document.forms[0].sdjsrq[row].value;	//所得结束日期
		var rmbhj = document.forms[0].rmbhj[row].value;		//人民币合计
		var jfye_obj = document.forms[0].jfye[row];			//减费用额对象
		var ynssde_obj = document.forms[0].ynssde[row];		//应纳税所得额对象
		var szsm_obj = document.forms[0].szsmdm[row];		//税种税目对象
		var yjzgyjnsk = document.forms[0].yjzgyjnsk[row].value;	//原居住国应缴纳税款
		var fdkce = document.forms[0].fdkce[row].value;		//法定扣除额
		var ynse_obj = document.forms[0].ynse[row];			//应纳税额对象
		var ykjse = document.forms[0].ykjse[row].value;		//已扣缴税额
		var ybtsk_obj = document.forms[0].ybtsk[row];		//应补退税款
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
	//各项数据为空是默认为0
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
				//计算工资
				computerGz(sdksrq,sdjsrq,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj);
			}
			else if(szsm_obj.options[szsm_obj.selectedIndex].value.indexOf("050150")==0)
			{
				jfye_obj.value = jfye_tmp = 0;
				jfye_obj.readOnly=true;
				jfye_obj.className="inputnoborder";
				//计算奖金
				computerJj(rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj);
			}
			else if(szsm_obj.options[szsm_obj.selectedIndex].value.indexOf("050151")==0)
			{
				//计算调资
				computerTiaozi(sdksrq,sdjsrq,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj);
			}
			else if(szsm_obj.options[szsm_obj.selectedIndex].value.indexOf("050152")==0)
			{
				//计算退职
				computerTz(sdksrq,sdjsrq,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj);
			}
			return;
		case "0503":
			jfye_obj.readOnly=false;
			jfye_obj.className="input";
			if(jfye_tmp==null||jfye_tmp=="")
				jfye_obj.value = jfye_tmp = 800;
			//计算劳务报酬
			computerLwbc(jfye_obj,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj);
			return;
		case "0504":
			jfye_obj.readOnly=false;
			jfye_obj.className="input";
			if(jfye_tmp==null||jfye_tmp=="")
				jfye_obj.value = jfye_tmp = 800;
			//计算稿酬所得
			computerGcsd(jfye_obj,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj);
			return;
		case "0505":
			jfye_obj.readOnly=false;
			jfye_obj.className="input";
			if(jfye_tmp==null||jfye_tmp=="")
				jfye_obj.value = jfye_tmp = 800;
			//特许权使用费所得
			computerTxLxYbzl(jfye_obj,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj);
			return;
		case "0507":
			jfye_obj.readOnly=false;
			jfye_obj.className="input";
			if(jfye_tmp==null||jfye_tmp=="")
				jfye_obj.value = jfye_tmp = 800;
			if(szsm_obj.options[szsm_obj.selectedIndex].value.indexOf("050702")==0)
			{
				//一般财产租赁(同特许权使用费所得)
				computerTxLxYbzl(jfye_obj,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj);
			}
			if(szsm_obj.options[szsm_obj.selectedIndex].value.indexOf("050701")==0)
			{
				//个人私房出租
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
				股息、利息、红利所得
				财产转让所得
				偶然所得
				经国务院财政部门确定征税的其他所得
			*/
			computerOther(rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj);
			return;
		default :
			return;
	}
}

//计算工资
function computerGz(sdksrq,sdjsrq,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj)
{
	//原居住国应缴纳税款为0
	if(yjzgyjnsk == 0)
	{
		switch (document.forms[0].fdfsdm.value)
		{
			//个人负担
			case "1":
				//取得不含税税率和速算扣除数
				var sl = getBhsl(szsm_obj,(rmbhj - fdkce - jfye_tmp));
				if(sl == null || sl == "")
					return;
				var sskcs = sz[szsm_obj.selectedIndex][9];
				//应纳税所得额=收入额-法定扣除项目金额-标准扣除费用
				var ynssde = Math.round((rmbhj - fdkce - jfye_tmp - sskcs) * 100 /(1-sl))/100;
				//如果计算出应纳税所得额小于0则设为0
				if(ynssde < 0)
				{
					ynssde = 0;
				}
				//如果应纳税所得额文本框有值则取文本框值，否则给文本框赋计算所得值
				else if(ynssde_obj.value == null || ynssde_obj.value == "")
				{
					ynssde_obj.value = ynssde;
				}
				else
				{
					ynssde = ynssde_obj.value;
				}
				//根据税种税目和应纳税所得额取含税的税率
				sl = getsl(szsm_obj,ynssde);
				if(sl == null || sl == "")
					return;
				//计算应纳税额
				var ynse = Math.round((ynssde * sl - sz[szsm_obj.selectedIndex][3])*100)/100;
				if(document.forms[0].sfczbs.value!="1")
				{
					//如果常驻
					if(sdksrq != null && trim(sdksrq) != "" && sdjsrq != null && trim(sdjsrq) != "")
						//实际工作日
						var sjgzr = DateDiff("d",sdksrq.substring(0,4)+"-"+sdksrq.substring(4,6)+"-"+sdksrq.substring(6),sdjsrq.substring(0,4)+"-"+sdjsrq.substring(4,6)+"-"+sdjsrq.substring(6))+1;
					else
						return;
					//所得期间按月间隔天数
					var days = fnComputerDay(sdksrq.substring(0,4),sdksrq.substring(4,6));
					ynse = Math.round(ynse/days*sjgzr*100)/100;
				}
				else
				{
					//非常驻
					if(sdksrq != null && trim(sdksrq) != "" && sdjsrq != null && trim(sdjsrq) != "")
						//实际工作日
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
			雇主为雇员负担一定比例的工资应纳的税款或负担一定比例的实际应纳税款：
			*/
			case "2":
				//取得不含税税率和速算扣除数、负担比率
				var sl = getBhsl(szsm_obj,(rmbhj - jfye_tmp));
				if(sl == null || sl == "")
					return;
				var sskcs = sz[szsm_obj.selectedIndex][9];
				var fdbl = parseFloat(document.forms[0].dwfdbl.value);
				//应纳税所得额=（未含雇主负担的税款收入额-费用扣除标准-速算扣除数*负担比例）÷（1-税率*负担比例）
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
				//取含税税率
				sl = getsl(szsm_obj,ynssde);
				if(sl == null || sl == "")
					return;
				//应纳税额=应纳税所得额*适用税率-速算扣除数
				var ynse = Math.round((ynssde * sl - sz[szsm_obj.selectedIndex][3])*100)/100;
				//判断是否常驻（同个人负担）
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
			公司全额负担：
			①中的税率是指不含税所得按不含税级距对应的税率，②中的税率是指含税所得按含税级距对应的税率
			*/
			case "3":
				//应纳税所得额=不含税收入额-法定扣除项目金额-费用扣除标准-速算扣除数
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
				//取含税税率、速算扣除数
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
				//应纳税额=应纳税所得额*税率-速算扣除数
				var ynse = Math.round((ynssde * sl - sskcs)*100)/100;
				//判断是否常驻（同个人负担）
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
		//有原居住国应缴纳税款
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

//计算奖金
function computerJj(rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj)
{
	switch (document.forms[0].fdfsdm.value)
	{
		/*
		个人负担：
		*/
		case "1":
			//取不含税税率、速算扣除数计算应纳税所得额
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
			//取含税税率、速算扣除数计算应纳税额
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
		公司全额负担
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
			//取含税奖金税率
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
			if(rmbhj <= 3360)	//收入额小于3360
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
				//取含税税率
				var sl = getsl(szsm_obj,ynssde);
				if(sl == null || sl == "")
					return;
				ynse = Math.round(ynssde*sl*100)/100;
			}
			else
			{
				if(rmbhj <= 21000)	//收入额小于21000
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
				else if(rmbhj <= 49500)	//收入额小于49500
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
		公司全额负担
		*/
		case "3":
			//取不含税税率计算应纳税额
			var sl = getsl(szsm_obj,rmbhj);
			if(sl == null || sl == "")
				return;
			if(rmbhj <= 1000)
			{
				ynse = Math.round(rmbhj * sl*100)/100;
				ynse_obj.value = ynse;
			}
			else if(rmbhj > 1000 && rmbhj <= 4000)	//收入额在1000和4000之间
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
			else if(rmbhj > 4000 && rmbhj <= 20000)	//收入额在4000和20000之间
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
			else if(rmbhj > 20000)	//收入额大于20000
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
计算稿酬所得
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
一般财产租赁(同特许权使用费所得)
*/
function computerTxLxYbzl(jfye_obj,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj)
{
	var ynssde;
	if(rmbhj <= 4000)	//收入额小于4000
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
个人私房出租
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
	股息、利息、红利所得
	财产转让所得
	偶然所得
	经国务院财政部门确定征税的其他所得
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
退职
*/
function computerTz(sdksrq,sdjsrq,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj)
{
	//如果填写所得期间，计算实际工作日
	if(sdksrq != null && trim(sdksrq) != "" && sdjsrq != null && trim(sdjsrq) != "")
		var sjgzr = DateDiff("d",sdksrq.substring(0,4)+"-"+sdksrq.substring(4,6)+"-"+sdksrq.substring(6),sdjsrq.substring(0,4)+"-"+sdjsrq.substring(4,6)+"-"+sdjsrq.substring(6))+1;
	else
		return;
	//计算月收入
	var ysr = (rmbhj-fdkce)/(sjgzr/365);
	var ynssde = 0;
    switch (document.forms[0].fdfsdm.value)
	{
		case "1":
			//取不含税税率、速算扣除数
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
			//取得分配比例
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
	//取调资税率计算应纳税额
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
调资
*/
function computerTiaozi(sdksrq,sdjsrq,rmbhj,ynssde_obj,szsm_obj,yjzgyjnsk,fdkce,ynse_obj,ykjse,ybtsk_obj)
{
	itemsize = mxtable.rows.length-4;
	//如果没有工资信息则退出
	if(itemsize<=1)
		return;
	//如果填写所得期间，计算实际工作日
	if(sdksrq != null && trim(sdksrq) != "" && sdjsrq != null && trim(sdjsrq) != "")
		var sjgzr = DateDiff("M",sdksrq.substring(0,4)+"-"+sdksrq.substring(4,6)+"-"+sdksrq.substring(6),sdjsrq.substring(0,4)+"-"+sdjsrq.substring(4,6)+"-"+sdjsrq.substring(6))+1;
	else
		return;
	var tzqynssde = 0;
	var tzqybtsk = 0;
	var tzqynse = 0;
	var getGz = false;
	//取工资数据
	for(i=0;i<itemsize;i++)
	{
		if(document.forms[0].szsmdm[i].value.indexOf("05011")==0)
		{
			getGz = true;
			tzqrmbhj = document.forms[0].rmbhj[i].value;	//调资前人民币合计
			tzqybtsk = document.forms[0].ybtsk[i].value;	//调资前应补退税款
			tzqynse = document.forms[0].ynse[i].value;		//调资前应纳税额
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
	//如果有工资数据
	if(getGz)
	{
		//计算收入额
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
		alert("您没有申报以前月份工资！");
		return;
	}
}

//取得参数对象所在行数
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

//添加一行
function doAdd()
{
	itemsize = mxtable.rows.length-4;
	if(itemsize>=4)
	{
		alert("当前申报最多录入4条明细数据！");
		return;
	}
	var row = mxtable.insertRow(mxtable.rows.length-1);

	<%/*所得项目*/%>
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

	<%/*所得期间*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.noWrap = true;
	cell.innerHTML = "<input name=\"sdksrq\" size=\"8\" maxlength=\"8\" value=\"<%=skssksrq%>\" onchange=\"if(isDate(this)){computer(this);return true;}else{return false;}\">-<input name=\"sdjsrq\" size=\"8\" maxlength=\"8\" value=\"<%=skssjsrq%>\" onchange=\"if(isDate(this)){computer(this);return true;}else{return false;}\">";

	<%/*原居住国应缴纳税款*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name=\"yjzgyjnsk\" size=\"7\" maxlength=\"16\" onchange=\"if(checkvalue(this,0)&&formatCurrency(this,0)){computer(this);return true;}else{return false;}\">"));

	<%/*法定扣除额*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name=\"fdkce\" size=\"7\" maxlength=\"16\" onchange=\"if(checkvalue(this,0)&&formatCurrency(this,0)){computer(this);return true;}else{return false;}\">"));

	<%/*人民币*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name=\"srermb\" size=\"7\" maxlength=\"16\" onchange=\"if(checkvalue(this,0)&&formatCurrency(this,0)){computerRmbhj(this);return true;}else{return false;}\">"));

	<%/*折合人民币*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name=\"zhrmb\" size=\"7\" maxlength=\"16\" onfocus=\"zhrmb_now = this;fillWhpj();hideAllSbb();whpjdiv.style.display='';\" onchange=\"if(checkvalue(this,0)&&formatCurrency(this,0)){computerRmbhj(this);return true;}else{return false;}\">"));

	<%/*人民币合计*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name=\"rmbhj\" size=\"7\" maxlength=\"16\" onchange=\"if(checkvalue(this,0)&&formatCurrency(this,0)){computer(this);return true;}else{return false;}\">"));

	<%/*减费用额*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name=\"jfye\" onchange=\"if(checkvalue(this,0)&&formatCurrency(this,1)){jfye_tmp=this.value;computer(this);return true;}else{return false;}\" size=\"4\" maxlength=\"16\" value=\""+jfye_def+"\">"));

	<%/*应纳税所得额*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name=\"ynssde\" size=\"7\" maxlength=\"16\" onchange=\"if(checkvalue(this,0)&&formatCurrency(this,1)){computer(this);return true;}else{return false;}\">"));

	<%/*应纳税额*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name=\"ynse\" size=\"7\" maxlength=\"16\" onchange=\"if(checkvalue(this,0)&&formatCurrency(this,1)){computer(this);return true;}else{return false;}\">"));

	<%/*已扣缴税款*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name=\"ykjse\" size=\"7\" maxlength=\"16\" onchange=\"if(checkvalue(this,0)&&formatCurrency(this,0)){computer(this);return true;}else{return false;}\">"));

	<%/*应补（退）税款*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name=\"ybtsk\" size=\"7\" maxlength=\"16\" onchange=\"if(checkvalue(this,0)&&formatCurrency(this,1)){computer(this);return true;}else{return false;}\">"));

	<%/*删除*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-center";
	cell.noWrap = true;
	cell.innerHTML = "<input type=\"hidden\" name=\"bzdm\"><input type=\"hidden\" name=\"je\"><input type=\"hidden\" name=\"whpj\"><input type=\"hidden\" name=\"zh\"><a href=\"#\" id=\"delIndex\" onclick=\"doDelete(this);return false;\">删除</a>";

	return false;
}

<%/*返回*/%>
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
<%/*删除*/%>
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
保存
*/
function doSave()
{
	itemsize = mxtable.rows.length-4;
	if(itemsize==0)
	{
		alert("请填写明细数据！");
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
				alert("请填写所得期间！");
				return false;
			}
			if(ybtsk==null||ybtsk==""||parseFloat(ybtsk)==0)
			{
				alert("已经取消0申报，本条数据不保存，请删除或修改！");
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
				alert("请填写所得期间！");
				return false;
			}
			if(ybtsk==null||ybtsk==""||parseFloat(ybtsk)==0)
			{
				alert("已经取消0申报，本条数据不保存，请删除或修改！");
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
				alert("请填写所得期间！");
				return false;
			}
			if(ybtsk==null||ybtsk==""||parseFloat(ybtsk)==0)
			{
				alert("已经取消0申报，本条数据不保存，请删除或修改！");
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
		alert("日期格式错误！");
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
    		<jsp:param name="name" value="自然人个人所得税申报表" />
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
    <td class="1-td1">自然人个人所得税申报表
	<html:hidden property="actionType"/></td>
  </tr>
  <tr>
     <td width="100%" class="1-td2" align="center">
	<br>
      <table align="center" cellspacing="0" class="table-99">
              <tr class="black9">
                <td><div align="right">税款所属日期&nbsp;&nbsp;</div>
                </td>
                <td nowrap>
		<div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="skssksrq"/>&nbsp;至&nbsp;<shenbao:write name="zrrsbForm" property="skssjsrq"/></div>
                </td>
                <td nowrap><div align="right">填表日期&nbsp;&nbsp;</div>
                </td>
                <td nowrap>
		<div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="sbrq"/></div>
                </td>
                <td colspan="2" nowrap>
                	<div align="right">金额单位：人民币元&nbsp;&nbsp;</div>
                </td>
              </tr>
              <tr>
                <td class="2-td2-t-left" nowrap>
                      <div align="right">纳税人编码&nbsp;&nbsp;</div>
		</td>
                <td class="2-td2-t-left" nowrap>
                      <div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="jsjdm"/></div>
                </td>
		<td class="2-td2-t-left" nowrap>
                	<div align="right">纳税人姓名&nbsp;&nbsp;</div>
		</td>
                <td class="2-td2-t-left" nowrap>
                      <div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="nsrmc"/></div>
                </td>
		<td class="2-td2-t-left"><div align="right">抵华日期&nbsp;&nbsp;</div>
		</td>
		<td class="2-td2-t-center">
		<div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="dhrq"/></div>
		</td>
              </tr>
              <tr>
		<td class="2-td2-left" nowrap>
		<div align="right">国籍&nbsp;&nbsp;</div>
		</td>
                <td class="2-td2-left" nowrap>
                      <div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="gjmc"/></div>
                </td>
		<td class="2-td2-left" nowrap>
			<div align="right">证件类型&nbsp;&nbsp;</div>
		</td>
                <td class="2-td2-left" nowrap>
                      <div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="zjlxmc"/></div>
                </td>
		<td class="2-td2-left" nowrap>
			<div align="right">证件号码&nbsp;&nbsp;</div>
		</td>
                <td class="2-td2-center" nowrap>
                      <div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="zjhm"/></div>
                </td>
              </tr>
              <tr>
		<td class="2-td2-left" nowrap>
		<div align="right">负担方式&nbsp;&nbsp;</div>
		</td>
                <td class="2-td2-left" nowrap>
                      <div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="fdfsmc"/><html:hidden name="zrrsbForm" property="fdfsdm"/></div>
                </td>
		<td class="2-td2-left" nowrap>
		<div align="right">负担比例&nbsp;&nbsp;</div>
		</td>
                <td class="2-td2-left" nowrap>
                      <div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="dwfdbl"/>%<html:hidden name="zrrsbForm" property="dwfdbl"/></div>
                </td>
		<td class="2-td2-left" nowrap>
		<div align="right">租房费扣除额&nbsp;&nbsp;</div>
		</td>
                <td class="2-td2-center" nowrap>
                      <div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="zffkce"/></div>
                </td>
	</tr>
	<tr>
		<td class="2-td2-left"><div align="right">在中国境内住址&nbsp;&nbsp;</div>
		</td>
		<td class="2-td2-left" colspan="3">
                <div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="jnzz"/></div>
		</td>
		<td class="2-td2-left"><div align="right">是否常驻&nbsp;&nbsp;</div>
		</td>
		<td class="2-td2-center">
		<div align="left">&nbsp;&nbsp;<%if(zrrForm.getSfczbs()!=null && zrrForm.getSfczbs().equals("1")){%>是<%}else{%>否<%}%>
		<html:hidden name="zrrsbForm" property="sfczbs"/></div>
		</td>
	</tr>
	<tr>
		<td class="2-td2-left">
		<div align="right">职业&nbsp;&nbsp;</div></td>
		<td class="2-td2-left">
		<div align="left">&nbsp;&nbsp;<shenbao:write name="zrrsbForm" property="zymc"/></div></td>
		<td class="2-td2-left">
		<div align="right">服务单位&nbsp;&nbsp;</div>
		<td class="2-td2-left">
		<div align="left">&nbsp;&nbsp;<html:text name="zrrsbForm" property="fwdw"/></div>
		</td>
		<td class="2-td2-left">
		<div align="right">服务地点&nbsp;&nbsp;</div>
		<td class="2-td2-center">
		<div align="left">&nbsp;&nbsp;<html:text name="zrrsbForm" property="fwdd"/></div>
		</td>
	<tr>
	<tr>
		<td class="2-td2-left">
		<div align="right">银行帐号&nbsp;&nbsp;</div></td>
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
		<div align="right">银行名称&nbsp;&nbsp;</div>
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
    <td rowspan="3" class="2-td1-left">所得项目</td>
    <td rowspan="3" class="2-td1-left">所得期间</td>
    <td rowspan="3" class="2-td1-left">原居住国应缴纳税款</td>
    <td rowspan="3" class="2-td1-left">法定扣除额</td>
    <td colspan="3" class="2-td1-left">收入额</td>
    <td rowspan="3" width="40" class="2-td1-left">减费用额</td>
    <td rowspan="3" class="2-td1-left">应纳税所得额</td>
    <td rowspan="3" class="2-td1-left">应纳税额</td>
    <td rowspan="3" class="2-td1-left">已扣缴税款</td>
    <td rowspan="3" class="2-td1-left">应补（退）税款</td>
    <td nowrap rowspan="3" class="2-td1-center" id="delIndex">&nbsp;&nbsp;</td>
  </tr>
  <tr>
    <td rowspan="2" class="2-td1-left">人民币</td>
    <td class="2-td1-left">外币</td>
    <td rowspan="2" class="2-td1-left">人民币合计</td>
  </tr>
  <tr>
    <td class="2-td1-left">折合人民币</td>
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
	<logic:equal name="zrrsbForm" property="gjmc" value="中国">
	<input name="jfye" size="4" value="1200" maxlength="16" onchange="if(checkvalue(this,0)&&formatCurrency(this,1)){jfye_tmp=this.value;computer(this);return true;}else{return false;}">
	</logic:equal>
	<logic:notEqual name="zrrsbForm" property="gjmc" value="中国">
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
    <td nowrap class="2-td2-center"><input type="hidden" name="bzdm"><input type="hidden" name="je"><input type="hidden" name="whpj"><input type="hidden" name="zh"><a href="#" id="delIndex" onclick="doDelete(this);return false;">删除</a> </td>
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
    <td nowrap class="2-td2-center"><input type="hidden" name="bzdm" value="<%=mx.getBzdmStr()%>"><input type="hidden" name="je" value="<%=mx.getJeStr()%>"><input type="hidden" name="whpj" value="<%=mx.getWhpjStr()%>"><input type="hidden" name="zh" value="<%=mx.getZhStr()%>"><a href="#" id="delIndex" onclick="doDelete(this);return false;">删除</a> </td>
  </tr>
<%
	}
}
%>
  <tr>
    <td colspan="13">
     <br>
      <div align="center">
		<img src="<%=static_contextpath%>images/tianjia1.jpg" onmouseover="this.src='<%=static_contextpath%>images/tianjia2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/tianjia1.jpg'" alt="增加" onclick="doAdd();" style="cursor:hand">
        &nbsp;&nbsp;&nbsp;&nbsp;
		<img src="<%=static_contextpath%>images/baocun1.jpg" onmouseover="this.src='<%=static_contextpath%>images/baocun2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/baocun1.jpg'" alt="保存" onclick="return doSave()" style="cursor:hand">
        &nbsp;&nbsp;&nbsp;&nbsp;
		<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onclick="doReturn()" style="cursor:hand">
        &nbsp;&nbsp;&nbsp;&nbsp;
		<a href="zrrJks.do">
		<img src="<%=static_contextpath%>images/b-ckbqjks1.jpg" onmouseover="this.src='<%=static_contextpath%>images/b-ckbqjks2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/b-ckbqjks1.jpg'" alt="维护缴款书" style="cursor:hand" border="0">
		</a>
       </div>
    </td>
  </tr>
</table>
</div>
<script language="javascript">
/*
计算折合人民币
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
计算折合折合人民币合计
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
取得参数对象所在行数
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
填充外汇牌价
*/
function fillWhpj()
{
	if(bc.length > getRow(zhrmb_now) && bc[getRow(zhrmb_now)] != null)	//如果本次提交数据大于行数且本次提交数据不为空
	{
		var bzdmArray = bc[getRow(zhrmb_now)][0];	//币种代码数组
		var jeArray = bc[getRow(zhrmb_now)][1];		//金额数组
		var whpjArray = bc[getRow(zhrmb_now)][2];	//外汇牌价数组
		var zhArray = bc[getRow(zhrmb_now)][3];		//折合数组
		itemsize = whpjtable.rows.length-3;
		//清空外汇牌价表格
		for(i=0;i<itemsize;i++)
		{
			whpjtable.deleteRow(1);
		}
		//如果有已填写数据且大于1条
		if(bzdmArray.length>1)
		{
			//生成已填写数据表格
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
        else	//只有一条已填写数据
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
	else	//没有已填写数据，清空表格后生成一条空行
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

<%/*返回*/%>
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
	//计算对应项的人民币合计
	computerRmbhj(zhrmb_now);
	var whpj_bzdm = new Array();	//存放币种代码的数组
	var whpj_je = new Array();		//存放金额的数组
	var whpj_whpj = new Array();	//存放外汇牌价的数组
	var whpj_zh = new Array();		//存放折合金额的数组
	itemsize = whpjtable.rows.length-3;
	if(itemsize>1)	//如果只有一条数据
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
		hideAllSbb();	//隐藏所有表格
		zrrmx.style.display='';	//显示自然人明细表格
		return true;
	}
		//把本次申报的外汇牌价数据保存到数组（对应明细行数）
		bc[getRow(zhrmb_now)] = [whpj_bzdm,whpj_je,whpj_whpj,whpj_zh];
		itemsize = mxtable.rows.length-4;
		//把外汇牌价信息格式化成字符串放入表格隐藏域
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
删除参数对象对应的一行
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
添加一行外汇牌价表格
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
	cell.innerHTML = "<a href=\"#\" id=\"delWhpjIndex\" onclick=\"doDeleteWhpj(this);return false;\">删除</a>";
}
</script>
<div id="whpjdiv" style="display:none">
<table id="whpjtable" border="0" cellpadding="0" cellspacing="0" class="table-99">
  <tr>
    <td class="2-td1-left">货币名称</td>
    <td class="2-td1-left">金额</td>
    <td class="2-td1-left">外汇牌价</td>
    <td class="2-td1-left">折合人民币</td>
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
    <td class="2-td2-center" nowrap><a href="#" id="delWhpjIndex" onclick="doDeleteWhpj(this);return false;">删除</a></td>
  </tr>
  <tr>
    <td class="2-td2-left" colspan="3">合计</td>
    <td class="2-td2-left"><input name="zhrmbhj" size="16" maxlength="16" readonly class="inputnoborder" value="0.00"></td>
    <td class="2-td2-center">&nbsp;&nbsp;</td>
  </tr>
  <tr>
    <td colspan="5">
     <br>
      <div align="center">
		<img src="<%=static_contextpath%>images/tianjia1.jpg" onmouseover="this.src='<%=static_contextpath%>images/tianjia2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/tianjia1.jpg'" alt="增加" onclick="doAddWhpj();" style="cursor:hand">
        &nbsp;&nbsp;&nbsp;&nbsp;
		<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onclick="doReturnWhpj()" style="cursor:hand">

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