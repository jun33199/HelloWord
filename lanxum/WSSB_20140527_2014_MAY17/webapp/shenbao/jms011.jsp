<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import ="java.util.Date"%>
<%@page import="com.ttsoft.bjtax.shenbao.sbzl.jms.JmsActionConstant"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.client.JmsInfo"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.client.JmsSzsm"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.domain.Jm"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.domain.Jmfl"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.client.JmsxmInfor"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.domain.Szsmyfjs"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.client.JmsSkssrq"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.client.JmsSkny"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import="com.ttsoft.bjtax.shenbao.constant.CodeConstant"%>
<%@page import="com.ttsoft.bjtax.shenbao.sbzl.jms.SessionKey"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.DateUtilPro"%>
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator
			.getStaticFilePath(request);
	List jmsbList = (List) session.getAttribute(SessionKey.SESSION_JMSB_LIST);
	List szJsList = (List) session.getAttribute(SessionKey.SESSION_JMSZ_LIST);
	List smJsList = (List) session.getAttribute(SessionKey.SESSION_JMSM_LIST);
	List jmflList = (List) session.getAttribute(SessionKey.SESSION_JMFL_LIST);
	List fjsList = (List) session.getAttribute(SessionKey.SESSION_FJS_LIST);
	List bqysbList = (List) session.getAttribute(SessionKey.SESSION_BQYSB_LIST);
	List jmxmNewList = (List) session.getAttribute(SessionKey.SESSION_JMXM_LIST);
	List skssrqList = (List) session.getAttribute(SessionKey.SESSION_SKSSRQ_LIST);
	List sknyList = (List) session.getAttribute(SessionKey.SESSION_SKSNY_LIST);
%>
<html>
	<head>
		<title>减免税申报表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<script language="JavaScript" type="text/JavaScript"
			src="js/function.js"></script>
		<script language="JavaScript" type="text/JavaScript"
			src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
		<script language="JavaScript" type="text/JavaScript"
			src="<%=static_contextpath%>js/XmlBuild.js"></script>
		<script language="JavaScript" type="text/JavaScript"
			src="js/selectOption.js"></script>

		<script language="JavaScript">

<%/*税种代码数组*/%>
var sz = new Array(<%=szJsList.size()%>);
var sm = new Array(<%=smJsList.size()%>);
var fjs = new Array(<%=fjsList.size()%>);
var jmfl= new Array(<%=jmflList.size()%>);
var jm= new Array(<%=bqysbList.size()%>);
var jmxm= new Array(<%=jmxmNewList.size()%>);
var skssrq= new Array(<%=skssrqList.size()%>);
var skny= new Array(<%=sknyList.size()%>);
<%
int canDel=1;
if (jmsbList != null && jmsbList.size() != 0) 
{
	canDel=0;
}
for(int i=0;i<szJsList.size();i++)
{
	JmsSzsm sz = (JmsSzsm)szJsList.get(i);
	out.println("sz["+i+"] = [\""+sz.getSzsmdm()+"\",\""+sz.getSzsmmc()+"\","+sz.isBottom()+"];");
}
for(int i=0;i<smJsList.size();i++)
{
	JmsSzsm szsm = (JmsSzsm)smJsList.get(i);
	out.println("sm["+i+"] = [\""+szsm.getSzsmdm()+"\",\""+szsm.getSzsmmc()+"\","+szsm.isAslj()+"];");
}
for(int i=0;i<fjsList.size();i++)
{
	Szsmyfjs fjs = (Szsmyfjs)fjsList.get(i);
	out.println("fjs["+i+"] = [\""+fjs.getSzsmdm()+"\",\""+fjs.getFjsszsmdm()+"\"];");
}
for(int i=0;i<jmflList.size();i++)
{
	Jmfl tmpJmfl = (Jmfl)jmflList.get(i);
	out.println("jmfl["+i+"] = [\""+tmpJmfl.getJmfldm()+"\",\""+tmpJmfl.getJmflmc()+"\"];");
}

for(int i=0;i<jmxmNewList.size();i++)
{
	JmsxmInfor tmpJmxm = (JmsxmInfor)jmxmNewList.get(i);
	out.println("jmxm["+i+"] = [\""+tmpJmxm.getSzdm()+"\",\""+tmpJmxm.getJmslxdm()+"\",\""+tmpJmxm.getWh()+"\"];");
}

for(int i=0;i<bqysbList.size();i++)
{
	Jm tmpJm = (Jm)bqysbList.get(0);
	
	String dqxse = "";
	String dqlrze = "";
	String qyrs = "";
	String azrs = "";
//	String azbl = "";
	if(String.valueOf(tmpJm.getDqxse()) != null && !String.valueOf(tmpJm.getDqxse()).equals("")&& !String.valueOf(tmpJm.getDqxse()).equals("null"))
	{
		dqxse = String.valueOf(tmpJm.getDqxse());
	}
	if(String.valueOf(tmpJm.getDqlrze()) != null && !String.valueOf(tmpJm.getDqlrze()).equals("")&& !String.valueOf(tmpJm.getDqlrze()).equals("null"))
	{
		dqlrze = String.valueOf(tmpJm.getDqlrze());
	}
	if(String.valueOf(tmpJm.getQyrs()) != null && !String.valueOf(tmpJm.getQyrs()).equals("")&& !String.valueOf(tmpJm.getQyrs()).equals("null"))
	{
		qyrs = String.valueOf(tmpJm.getQyrs());
	}
	if(String.valueOf(tmpJm.getAzrs()) != null && !String.valueOf(tmpJm.getAzrs()).equals("")&& !String.valueOf(tmpJm.getAzrs()).equals("null"))
	{
		azrs = String.valueOf(tmpJm.getAzrs());
	}
//	if(String.valueOf(tmpJm.getAzbl()) != null && !String.valueOf(tmpJm.getAzbl()).equals("") && !String.valueOf(tmpJm.getAzbl()).equals("null"))
//	{
//		azbl = String.valueOf(tmpJm.getAzbl());
//	}

	out.println("jm = [\""+dqxse+"\",\""+dqlrze+"\",\""+qyrs+"\",\""+azrs+"\"];");
}

for(int i=0;i<skssrqList.size();i++)
{
	JmsSkssrq tmpSkssrq = (JmsSkssrq)skssrqList.get(i);
	out.println("skssrq["+i+"] = [\""+i+"\",\""+tmpSkssrq.getSkssksrq()+"\",\""+tmpSkssrq.getSkssjsrq()+"\"];");
}
for(int i=0;i<sknyList.size();i++)
{
	JmsSkny tmpSksny = (JmsSkny)sknyList.get(i);
	out.println("skny["+i+"] = [\""+tmpSksny.getSkny()+"\"];");
}

%>
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
%>
g_objSI.Container = "<%=containerName%>";
var strXml = '<%=request.getAttribute("CA_XML_DATA") == null ? ""
					: request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION") == null ? ""
							: request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION") == null ? ""
							: request.getAttribute("CA_XML_SCHEME_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN") == null ? ""
					: request.getAttribute("REQUEST_TOKEN")%>';
var canDelete=<%=canDel%>;
//解析xml
function parseXmlOnLoad()
{
	var xslPath="/XSLTWEB/model/010008/XSLT/" +strXSLTVersion+".xsl";
	if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
	computerHj();
	insertJmfl();
	insertJmsz();
	hiddenSkrq();
	if(jm == null || jm.length == 0)
	{
		insertSkssrq();
		setSkssjsrq();
	}
	
    return true;
}


function getPostXml(objForm)
{	
	var retstr;
	//基本数据
	getBasicXx("xsltVersion","/taxdoc");
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	//纳税人信息
	applendElement("/taxdoc","nsrxx",["jsjdm","nsrmc","qylxdh","swjgzzjgdm","dqxse","dqlrze","qyrs","azrs"]);
	//核定信息
	//applendElement("/taxdoc","hdxx",["jmzg","ybjmsl","qyzslx","cyl","xzqy","dezsse","zsffdm"]);
	//申报信息
	applendElement("/taxdoc","sbxx",["fsdm","jd","nd","sbrq","sbxgrq","done"]);
	//申报数据
	getSbsj(objForm);	
	
	//去掉末尾自动添加的回车
	retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
	retstr = retstr.substr(0,retstr.length-2);
	return retstr;
}

function getSbsj(objForm)
{
	//alert("hc:"+objForm.hc.length);
	var JmsTable=document.getElementById("JmsTable");
	var rows = JmsTable.rows;
	var length = rows.length;
	
	if(length == 3)
	{
		objForm.jmflmc.value=objForm.jmfldm.options[objForm.jmfldm.selectedIndex].text;
		objForm.szmc.value=objForm.szdm.options[objForm.szdm.selectedIndex].text;
		objForm.szsmmc.value=objForm.szsmdm.options[objForm.szsmdm.selectedIndex].text;
		objForm.jmxmjdm.value=objForm.jmxmdm.options[objForm.jmxmdm.selectedIndex].text;
	}
	
	for(var i=0;i<objForm.hc.length;i++)
	{
		objForm.jmflmc[i].value=objForm.jmfldm[i].options[objForm.jmfldm[i].selectedIndex].text;
		objForm.szmc[i].value=objForm.szdm[i].options[objForm.szdm[i].selectedIndex].text;
		objForm.szsmmc[i].value=objForm.szsmdm[i].options[objForm.szsmdm[i].selectedIndex].text;
		//objForm.jmxmjdm[i].value=objForm.jmxmdm[i].options[objForm.jmxmdm[i].selectedIndex].text;
		//objForm.skssksrq[i].value=objForm.skssksrq[i].options[objForm.skssksrq[i].selectedIndex].text;
	}
	var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc");
	var objTemp = g_Doc.XMLDoc.createElement("sbsjlist");
	objNode.appendChild(objTemp);
	var total=document.forms(0).length-3;
	//alert(total);
	outer:
	for(var j=18;j<total;)
	{
		objNode=g_Doc.XMLDoc.createElement("sbsj");
		objTemp.appendChild(objNode);
		var count = 18;
		if(skssrq == null || skssrq.length == 0)
		{
			count = count-2;
		}
		for(var i=j;i<j+count;i++)
		{
			var strName=objForm.elements(i).name;
			strValue=objForm.elements(i).value;
			//alert(i+">"+strName+":"+strValue);
			node2=g_Doc.XMLDoc.createElement(strName);
			objNode.appendChild(node2);
			var objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
			node2.appendChild(objCDATA);
		}
		j+=count;
	}	
	getChildren(objTemp,"hj");
	getChildren(objTemp,"ifjmzg");
	var rootNode = g_Doc.XMLDoc.documentElement;
	var objCDATA =g_Doc.XMLDoc.createCDATASection("true");
	rootNode.selectSingleNode("//ifjmzg").text = "";
	rootNode.selectSingleNode("//ifjmzg").appendChild(objCDATA);	
	getChildren(objTemp,"jmzghc");
}

function insertJmfl()
{
	if(document.forms[0].hc.length>1)
	{
		for(var k=0;k<document.forms[0].hc.length;k++)
		{
			var obj=document.forms[0].jmfldm[k];
			//alert(obj.value);
			for(var i=0;i<jmfl.length;i++)
			{
				var yValue=jmfl[i][0];
				var yName=jmfl[i][1];
				var b=true;
				if(yValue==obj.value)
				{
					b=false;
				}
				if(b)
				{
					var item=new Option(yName,yValue);
					obj.options.add(item);
				}					
			}
		}
	}else
	{
		var obj=document.forms[0].jmfldm;
			//alert(obj.value);
			for(var i=0;i<jmfl.length;i++)
			{
				var yValue=jmfl[i][0];
				var yName=jmfl[i][1];
				var b=true;
				if(yValue==obj.value)
				{
					b=false;
				}
				if(b)
				{
					var item=new Option(yName,yValue);
					obj.options.add(item);
				}					
			}
	}
}

function insertJmsz()
{
	if(document.forms[0].hc.length>1)
	{
		for(var k=0;k<document.forms[0].hc.length;k++)
		{
			var obj=document.forms[0].szdm[k];
			var showValue=obj.value;
			//alert(showValue);
			var ite=new Option("","");
			obj.remove(ite);
			for(var i=0;i<sz.length;i++)
			{
				var yValue=sz[i][0];
				var yName=sz[i][1];
				var item=new Option(yName,yValue);
				obj.options.add(item);				
			}
			obj.value=showValue;
		}	
	}else
	{
		var obj=document.forms[0].szdm;
			var showValue=obj.value;
			//alert(showValue);
			var ite=new Option("","");
			obj.remove(ite);
			for(var i=0;i<sz.length;i++)
			{
				var yValue=sz[i][0];
				var yName=sz[i][1];
				var item=new Option(yName,yValue);
				obj.options.add(item);				
			}
			obj.value=showValue;
		
	}
	
}

<%/*生成特定税款所属开始日期*/%>
function insertSkssrq()
{
	if(document.forms[0].hc.length>1)
	{
		for(var k=0;k<document.forms[0].hc.length;k++)
		{
			if(skssrq != null && skssrq.length > 0)
			{
				var obj=document.forms[0].skssksrq[k];
				var ite=new Option("","");
				obj.remove(ite);
				for(var i=0;i<skssrq.length;i++)
				{
					var yValue=skssrq[i][1];
					var yName=skssrq[i][1];
					var b=true;
					if(yValue==obj.value)
					{
						b=false;
					}
					if(b)
					{
						var item=new Option(yName,yValue);
						obj.options.add(item);
					}
				}
			}
		}
	}
	else
	{
		if(skssrq != null && skssrq.length > 0)
		{
			var obj=document.forms[0].skssksrq;
			var ite=new Option("","");
			obj.remove(ite);
			for(var i=0;i<skssrq.length;i++)
			{
				var yValue=skssrq[i][1];
				var yName=skssrq[i][1];
				var b=true;
				if(yValue==obj.value)
				{
					b=false;
				}
				if(b)
				{
					var item=new Option(yName,yValue);
					obj.options.add(item);
				}
			}
		}
	}
}

<%/*控制特定税款所属结束日期*/%>
function setSkssjsrq()
{
	if(document.forms[0].hc.length>1)
	{
		for(var k=0;k<document.forms[0].hc.length;k++)
		{
			if(skssrq != null && skssrq.length > 0)
			{
				var obj=document.forms[0].skssjsrq[k];
				var ite=new Option("","");
				obj.remove(ite);
				for(var i=0;i<skssrq.length;i++)
				{
					var yValue=skssrq[i][2];
					var yName=skssrq[i][2];
					if(document.forms[0].skssksrq[k].value == skssrq[i][1])
					{
						var item=new Option(yName,yValue);
						obj.options.add(item);
					}
				}
			}
		}
	}
	else
	{
		if(skssrq != null && skssrq.length > 0)
		{
			var obj=document.forms[0].skssjsrq;
			var ite=new Option("","");
			obj.remove(ite);
			for(var i=0;i<skssrq.length;i++)
			{
				var yValue=skssrq[i][2];
				var yName=skssrq[i][2];
				if(document.forms[0].skssksrq.value == skssrq[i][1])
				{
					var item=new Option(yName,yValue);
					obj.options.add(item);
				}
			}
		}
	}
}

<%/*隐藏税款所属日期*/%>
function hiddenSkrq()
{
	var JmsTable=document.getElementById("JmsTable");
    itemsize = JmsTable.rows.length-2;
	if(itemsize > 1)
	{
/*		for(var index =0; index < JmsTable.rows.length; index ++ ){
			alert("---"+JmsTable.rows[index].childNodes[7].innerHTML);
		
		}*/
		if(skssrq == null || skssrq.length == 0)
		{
			document.getElementById("skssrqId").style.display="none";
			document.getElementById("skjsrqId").style.display="none";
				
/*			var skssksrqArr = document.getElementById("skssksrq");
			alert(skssksrqArr.length);
			for(var index =0; index < skssksrqArr.length; index ++)
			{
				var tempSub = skssksrqArr[index];
				alert(tempSub.tagName+"--"+tempSub.id);
			}*/
			
			//alert("6:"+TRobj.childNodes[6].innerHTML);
			//alert("7:"+TRobj.childNodes[7].innerHTML);
			//alert("8:"+TRobj.childNodes[8].childNodes[0].id);
			
			for(var outerIndex =0; outerIndex<JmsTable.rows.length; outerIndex ++)
			{
				var trObj = JmsTable.rows[outerIndex];
				for(var index =0; index < trObj.childNodes.length; index ++)
				{
					var tdObj = trObj.childNodes[index];
					//alert(index+"::"+tdObj.childNodes[0].tagName+"--"+tdObj.childNodes[0].id);
					var tdObj_childArr = tdObj.childNodes;
					for(var indexInner =0; indexInner < tdObj_childArr.length; indexInner ++)
					{
						if(tdObj_childArr[indexInner].id=="skssksrq" || tdObj_childArr[indexInner].id=="skssjsrq")
						{
							trObj.removeChild(tdObj);
							index --;
						}
					}
				}
			}
			document.getElementById("hjId").colSpan = "9";
		}
	}
	else if(itemsize == 1)
	{
		if(skssrq == null || skssrq.length == 0)
		{
			document.getElementById("skssrqId").style.display="none";
			document.getElementById("skjsrqId").style.display="none";
			
			var skssksrqOBJ = document.forms[0].skssksrq;
			var skssjsrqOBJ = document.forms[0].skssjsrq;
			
			if(skssksrqOBJ && skssjsrqOBJ)
			{
				var delKsTdOBJ = skssksrqOBJ.parentNode;
				var delJsTdOBJ = skssjsrqOBJ.parentNode;	
				delKsTdOBJ.parentNode.removeChild(delKsTdOBJ);
				delJsTdOBJ.parentNode.removeChild(delJsTdOBJ);
			}			
			document.getElementById("hjId").colSpan = "9";
		}
	}
	else
	{
		if(skssrq == null || skssrq.length == 0)
		{
			document.getElementById("skssrqId").style.display="none";
			document.getElementById("skjsrqId").style.display="none";
			var skssksrqOBJ = document.forms[0].skssksrq[0];
			var skssjsrqOBJ = document.forms[0].skssjsrq[0];
			if(skssksrqOBJ && skssjsrqOBJ)
			{
				var delKsTdOBJ = skssksrqOBJ.parentNode;
				var delJsTdOBJ = skssjsrqOBJ.parentNode;				
				delKsTdOBJ.parentNode.removeChild(delKsTdOBJ);
				delJsTdOBJ.parentNode.removeChild(delJsTdOBJ);
			}
			document.getElementById("hjId").colSpan = "9";
		}
	}
}


function formatSzsmdm(obj)
{
	//alert("szsmdm");
	setKssl(obj);
	makeFjs(obj);
}
function formatKsslJsje(obj)
{
	//alert("kssljsje");
	return (checkvalue(obj,0)&&formatCurrency(obj,0));
}

function formatDqje(obj)
{
	return (checkvalue(obj,0)&&formatCurrency(obj,0));
}

function formatLrje(obj)
{
	return (checkLrValue(obj,0)&&formatCurrency(obj,0));
}

function formatJmse(obj)
{
	//alert("jmse");
	return (checkvalue(obj,0)&&formatCurrency(obj,0)&&computerHj());
}

function tipAzrs()
{
	alert("      此处填写安置退役士兵、随军家属、军队转业干部、支持和促进就业、残疾人、员工制家政服务员的人数。\n      按现行税收政策规定，如企业适用多种税收优惠政策，可以选择最优惠政策，但不能重复享受（或累加执行）。\n      凡遇此情况的企业请按照所选择的优惠政策对应填写安置人数。");
}


<%/*生成税种税目及减免项目及代码*/%>
function makeSzsm(obj)
{
	var JmsTable=document.getElementById("JmsTable");
	itemsize = JmsTable.rows.length-2;
	var szdm = obj.value;
	
	if(itemsize > 1)
	{
		var row = 0;
		for(var i=0;i<itemsize;i++)
		{
			if(document.forms[0].szdm[i] == obj)
			{
				row = i;
				break;
			}
		}
		if(sz[obj.selectedIndex][2])
		{
			var optsize = document.forms[0].szsmdm[row].length;
			for(var i=0;i<optsize;i++)
			{
				document.forms[0].szsmdm[row].options.remove(0);
			}
			for(var i=0;i<sm.length;i++)
			{
				var szsmdm = sm[i][0];
				if(szsmdm.indexOf(szdm) == 0)
				{
					var newOpt = document.createElement("<OPTION>");
					newOpt.value = sm[i][0];
					newOpt.text = sm[i][1];
					if(szsmdm == obj.value)
					{
						newOpt.selected = true;
					}
					document.forms[0].szsmdm[row].options.add(newOpt);
				}
			}
			
			/*减免项目及代码 begin*/
			var jmxmsize = document.forms[0].jmxmdm[row].length;
			for(var i=0;i<jmxmsize;i++)
			{
				document.forms[0].jmxmdm[row].options.remove(0);
			}
			for(var i=0;i<jmxm.length;i++)
			{
				var jmxmdm = jmxm[i][0];
				if(jmxmdm.indexOf(szdm.substr(0,2)) == 0)
				{
					var newOpt = document.createElement("<OPTION>");
					newOpt.value = jmxm[i][1];
					newOpt.text = jmxm[i][2];
					if(jmxmdm == obj.value)
					{
						newOpt.selected = true;
					}
					document.forms[0].jmxmdm[row].options.add(newOpt);
				}
			}
			/*减免项目及代码 end*/
			
			setKssl(document.forms[0].szsmdm[row]);
			makeFjs(document.forms[0].szsmdm[row]);
			if(document.forms[0].jmxmdm[row].length == 0)
			{
				alert("您所选税种税目对应的“减免项目及代码”不存在，请删除该税种，再进行申报！");
				alert("您所选税种税目（"+document.forms[0].szsmdm[row].value +"）不存在对应的减免政策，即“减免项目及代码”项为空，请删除该税种税目！");
			}
		}
		else
		{
			var optsize = document.forms[0].szsmdm[row].length;
			for(var i=0;i<optsize;i++)
			{
				document.forms[0].szsmdm[row].options.remove(0);
			}
			
			/*减免项目及代码 begin*/
			var jmxmsize = document.forms[0].jmxmdm[row].length;
			for(var i=0;i<jmxmsize;i++)
			{
				document.forms[0].jmxmdm[row].options.remove(0);
			}
			/*减免项目及代码 end*/
		}
	}
	else if(itemsize == 1)
	{
		if(document.forms[0].jmse.length == null)
        {
			if(sz[obj.selectedIndex][2])
			{
				var optsize = document.forms[0].szsmdm.length;
				//alert("optsize:"+optsize);
				for(var i=0;i<optsize;i++)
				{
					document.forms[0].szsmdm.options.remove(0);
    			}
				for(var i=0;i<sm.length;i++)
				{
					var szsmdm = sm[i][0];
					if(szsmdm.indexOf(szdm) == 0)
					{
						var newOpt = document.createElement("<OPTION>");
						newOpt.value = sm[i][0];
						newOpt.text = sm[i][1];
						if(szsmdm == obj.value)
						{
							newOpt.selected = true;
						}
						document.forms[0].szsmdm.options.add(newOpt);
					}
				}
				
				/*减免项目及代码 begin*/
				var jmxmsize = document.forms[0].jmxmdm.length;
				//alert("jmxmsize："+jmxmsize);
				for(var i=0;i<jmxmsize;i++)
				{
					document.forms[0].jmxmdm.options.remove(0);
				}
				for(var i=0;i<jmxm.length;i++)
				{
					var jmxmdm = jmxm[i][0];
					if(jmxmdm.indexOf(szdm.substr(0,2)) == 0)
					{
						var newOpt = document.createElement("<OPTION>");
						newOpt.value = jmxm[i][1];
						newOpt.text = jmxm[i][2];
						if(jmxmdm == obj.value)
						{
							newOpt.selected = true;
						}
						document.forms[0].jmxmdm.options.add(newOpt);
					}
				}
				
				/*减免项目及代码 end*/
				
				setKssl(document.forms[0].szsmdm);
				makeFjs(document.forms[0].szsmdm);
				if(document.forms[0].jmxmdm.length == 0)
				{
					alert("您所选税种税目（"+document.forms[0].szsmdm.value +"）不存在对应的减免政策，即“减免项目及代码”项为空，请删除该税种税目！");
				}
			}
			else
			{
				var optsize = document.forms[0].szsmdm.length;
				for(var i=0;i<optsize;i++)
				{
					document.forms[0].szsmdm.options.remove(0);
				}
				
				/*减免项目及代码 begin*/
				var jmxmsize = document.forms[0].jmxmdm.length;
				for(var i=0;i<jmxmsize;i++)
				{
					document.forms[0].jmxmdm.options.remove(0);
				}
				/*减免项目及代码 end*/
			}
        }
		else
		{
			if(sz[obj.selectedIndex][2])
			{
				var optsize = document.forms[0].szsmdm[0].length;
				for(var i=0;i<optsize;i++)
				{
					document.forms[0].szsmdm[0].options.remove(0);
    			}
				for(var i=0;i<sm.length;i++)
				{
					var szsmdm = sm[i][0];
					if(szsmdm.indexOf(szdm) == 0)
					{
						var newOpt = document.createElement("<OPTION>");
						newOpt.value = sm[i][0];
						newOpt.text = sm[i][1];
						if(szsmdm == obj.value)
						{
							newOpt.selected = true;
						}
						document.forms[0].szsmdm.options.add(newOpt);
					}
				}
				
				/*减免项目及代码 begin*/
				var jmxmsize = document.forms[0].jmxmdm[0].length;
				for(var i=0;i<jmxmsize;i++)
				{
					document.forms[0].jmxmdm[0].options.remove(0);
				}
				for(var i=0;i<jmxm.length;i++)
				{
					var jmxmdm = jmxm[i][0];
					if(jmxmdm.indexOf(szdm.substr(0,2)) == 0)
					{
						var newOpt = document.createElement("<OPTION>");
						newOpt.value = jmxm[i][1];
						newOpt.text = jmxm[i][2];
						if(jmxmdm == obj.value)
						{
							newOpt.selected = true;
						}
						document.forms[0].jmxmdm.options.add(newOpt);
					}
				}
				/*减免项目及代码 end*/
				
				setKssl(document.forms[0].szsmdm[0]);
				makeFjs(document.forms[0].szsmdm[0]);
				if(document.forms[0].jmxmdm[0].length == 0)
				{
					alert("您所选税种税目（"+document.forms[0].szsmdm[0].value +"）不存在对应的减免政策，即“减免项目及代码”项为空，请删除该税种税目！");
				}
			}
			else
			{
				var optsize = document.forms[0].szsmdm[0].length;
				for(var i=0;i<optsize;i++)
				{
					document.forms[0].szsmdm[0].options.remove(0);
				}
				
				/*减免项目及代码 begin*/
				var jmxmsize = document.forms[0].jmxmdm[0].length;
				for(var i=0;i<jmxmsize;i++)
				{
					document.forms[0].jmxmdm[0].options.remove(0);
				}
				/*减免项目及代码 end*/
			}
		}
	}
}

<%/*控制课税数量*/%>
function setKssl(obj)
{
	var JmsTable=document.getElementById("JmsTable");
	itemsize = JmsTable.rows.length-2;
	var szsmdm = obj.value;
	var aslj = false;
	for(var i=0;i<sm.length;i++)
    	{
        	if(szsmdm == sm[i][0])
		{
			aslj = sm[i][2];
			break;
        	}
	}
	if(itemsize > 1)
	{
		var row = 0;
		for(var i=0;i<itemsize;i++)
		{
			if(document.forms[0].szsmdm[i] == obj)
			{
				row = i;
				break;
			}
		}
		if(!aslj)
		{
			document.forms[0].kssl[row].className = "inputnoborder";
			document.forms[0].kssl[row].readOnly = true;
			document.forms[0].kssl[row].value = "";
			document.forms[0].kssl[row].tabIndex = "-1";
		}
		else
		{
			document.forms[0].kssl[row].readOnly = false;
			document.forms[0].kssl[row].className = "input";
		}
	}
	else if(itemsize == 1)
	{
        	if(!aslj)
		{
			if(document.forms[0].kssl.length == null)
			{
				document.forms[0].kssl.className = "inputnoborder";
				document.forms[0].kssl.readOnly = true;
				document.forms[0].kssl.value = "";
				document.forms[0].kssl.tabIndex = "-1";
			}
			else
            		{
				document.forms[0].kssl[0].className = "inputnoborder";
				document.forms[0].kssl[0].readOnly = true;
				document.forms[0].kssl[0].value = "";
				document.forms[0].kssl[0].tabIndex = "-1";
			}
		}
		else
		{
			if(document.forms[0].kssl.length == null)
			{
				document.forms[0].kssl.className = "input";
				document.forms[0].kssl.readOnly = false;
			}
			else
            		{
				document.forms[0].kssl[0].className = "input";
				document.forms[0].kssl[0].readOnly = false;
			}
		}
	}
}

// 生成附加税
function makeFjs(obj)
{
    szsmdm = obj.value;
	var JmsTable=document.getElementById("JmsTable");
    itemsize = JmsTable.rows.length-2;
    for(var i=0;i<fjs.length;i++)
    {
		if(szsmdm == fjs[i][0])
		{
			var canadd = true;
        	    	for(var j=0;j<itemsize;j++)
            	    	{
				if(document.forms[0].szsmdm[j].value == fjs[i][1])
				{
					canadd = false;
					break;
				}
            	    	}
        		if(canadd)
			{
            			doAdd();
				for(var j=0;j<sz.length;j++)
				{
					if(fjs[i][1].indexOf(sz[j][0])==0)
					{
						//alert(document.forms[0].szdm+"---"+itemsize);
						document.forms[0].szdm[itemsize].selectedIndex = j;
						break;
					}
				}
				for(var k=0;k<sm.length;k++)
				{
					if(sm[k][0].indexOf(sz[j][0]) == 0)
					{
						var newOpt = document.createElement("<OPTION>");
						newOpt.value = sm[k][0];
						newOpt.text = sm[k][1];
						if(sm[k][0] == sz[j][0])
						{
							newOpt.selected = true;
						}
						document.forms[0].szsmdm[itemsize].options.add(newOpt);
					}
				}
				for(var j=0;j<document.forms[0].szsmdm[itemsize].length;j++)
				{
					if(document.forms[0].szsmdm[itemsize].options[j].value == fjs[i][1])
					{
						document.forms[0].szsmdm[itemsize].selectedIndex = j;
						break;
					}
				}
				
				/*减免项目及代码 begin*/
				for(var k=0;k<jmxm.length;k++)
				{
					if(jmxm[k][0].indexOf(fjs[i][1].substr(0,2)) == 0)
					{
						var newOpt = document.createElement("<OPTION>");
						newOpt.value = jmxm[k][1];
						newOpt.text = jmxm[k][2];
						document.forms[0].jmxmdm[itemsize].options.add(newOpt);
					}
				}
				if(document.forms[0].jmxmdm[itemsize].length == 0)
				{
					alert("您所选税种税目（"+document.forms[0].szsmdm[itemsize].value +"）不存在对应的减免政策，即“减免项目及代码”项为空，请删除该税种税目！");
				}
				/*减免项目及代码 end*/
            			document.forms[0].kssl[itemsize].className = "inputnoborder";
            			document.forms[0].kssl[itemsize].readOnly = true;
            			document.forms[0].kssl[itemsize].value = "";
            			document.forms[0].kssl[itemsize].tabIndex = "-1";
				itemsize++;
			}
        	}
	}
	hiddenSkrq();
}

function doAdd()
{
	var JmsTable=document.getElementById("JmsTable");
	var row = JmsTable.insertRow(JmsTable.rows.length-1);
	var itemsize = JmsTable.rows.length-1;
	var jmflmc;
	<%/*序号*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	<%/*减免类型*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.innerHTML = "<select name=\"jmfldm\"><%
for(int i=0;i<jmflList.size();i++)
{
	Jmfl tmpJmfl = (Jmfl)jmflList.get(i);
	if(tmpJmfl.getJmfldm().equals(CodeConstant.JMLX_SP))
	{
		out.print("<option value=\\\""+tmpJmfl.getJmfldm()+"\\\" selected>"+tmpJmfl.getJmflmc()+"</option>");
	}
	else
	{
		out.print("<option value=\\\""+tmpJmfl.getJmfldm()+"\\\">"+tmpJmfl.getJmflmc()+"</option>");
	}
}
%></select><input type=\"hidden\" name=\"jmflmc\" id=\"jmflmc\" value=\"1\"></input>";

	<%/*税种*/%>
	var sel = document.createElement("<select name=\"szdm\" style=\"width:150\" onchange=\"makeSzsm(this);\" onclick=\"makeSzsm(this);\">");
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
	cell.appendChild(document.createElement("<input type=\"hidden\" name=\"szmc\" id=\"szmc\"></input>"));
	<%/*税目*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<SELECT name=\"szsmdm\" style=\"width:150\"  onchange=\"setKssl(this);makeFjs(this);\"></select>"));
	cell.appendChild(document.createElement("<input type=\"hidden\" name=\"szsmmc\" id=\"szsmmc\"></input>"));
	
	<%/*减免税申报调整 BEGIN tujb 20140401*/%>
	<%/*减免项目及代码*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<SELECT name=\"jmxmdm\" id=\"jmxmdm\" style=\"width:150\" onclick=\"FixWidth(this);\" onmouseover=\"helpor_net_show(this);\" onmouseout=\"helpor_net_hide();\"></select>"));
	cell.appendChild(document.createElement("<input type=\"hidden\" name=\"jmxmjdm\" id=\"jmxmjdm\"></input>"));
	<%/*减免项目开始日期*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name='jmxmksrq' size='10' maxlength='8'>"));
	<%/*减免项目结束日期*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name='jmxmjsrq' size='10' maxlength='8'>"));
	<%/*税款所属开始日期*/%>
	//cell.appendChild(document.createElement("<SELECT name=\"ksrq\"></select>"));
	//cell.appendChild(document.createElement("<input type=\"hidden\" name=\"skssksrq\" id=\"skssksrq\"></input>"));
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.innerHTML = "<select name=\"skssksrq\" id=\"skssksrq\" onchange=\"setSkssjsrq();\"><%
	for(int i=0;i<skssrqList.size();i++)
	{
		JmsSkssrq tmpSkssrq = (JmsSkssrq)skssrqList.get(i);
		if(tmpSkssrq.getSkssksrq().equals(""))
		{
			out.print("<option value=\\\""+tmpSkssrq.getSkssksrq()+"\\\" selected>"+tmpSkssrq.getSkssksrq()+"</option>");
		}
		else
		{
			out.print("<option value=\\\""+tmpSkssrq.getSkssksrq()+"\\\">"+tmpSkssrq.getSkssksrq()+"</option>");
		}
	}
	%></select>";
	<%/*税款所属结束日期*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.innerHTML = "<select name=\"skssjsrq\" id=\"skssjsrq\"></select>";
	<%/*减免税申报调整 END tujb 20140401*/%>
	
	<%/*课税数量*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input type=\"hidden\" name=\"aslj\" id=\"aslj\"></input>"));
	cell.appendChild(document.createElement("<input name='kssl' size='8' maxlength='16' onchange=\"return (checkvalue(this,0)&&formatCurrency(this,0))\">"));

	<%/*计税金额*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name='jsje' size='10' maxlength='16' onchange=\"return (checkvalue(this,0)&&formatCurrency(this,0))\">"));

	<%/*减免税额*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name='jmse' size='10' maxlength='16' onchange=\"computerHj();return (checkvalue(this,0)&&formatCurrency(this,0));\">"));

	<%/*删除*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-center";
	cell.innerHTML = "<input type=\"hidden\" name=\"cjsj\"><a href=\"#\" id=\"delIndex\" onclick=\"doDelete(this);return false;\">删除</a>";

	<%/*生成序号*/%>
	refreshSerialNumber();
	setSkssjsrq();

	return false;
}

function  setJmlx(obj,row)
{
	var JmsTable=document.getElementById("JmsTable");
	itemsize = JmsTable.rows.length-2;
	if(itemsize > 1)
	{
		document.forms[0].jmfldm[row].value = obj.value;
	}
	else if(itemsize == 1)
	{
		document.forms[0].jmfldm.value = obj.value;
	}
}

function getAzbl()
{
	var qyrs  = document.forms[0].qyrs;
	var azrs  = document.forms[0].azrs;
	if(!checkFormNumber(qyrs,"企业人数"))
    {
         qyrs.focus();
         return false;
    }
    if(!checkFormNumber(azrs,"安置人数"))
    {
         azrs.focus();
         return false;
    }
    if((qyrs.value !='NaN' && qyrs.value !="") && (azrs.value !='NaN' && azrs.value !=""))
    {
    	//document.forms[0].azbl.value = Math.round(parseFloat(azrs.value)/parseFloat(qyrs.value)*100)/100;
    	if(azrs.value - qyrs.value > 0)
    	{
    		alert("安置人数不能大于企业人数！");
    	}
   }
    
}

//检查输入数字的正确性
function checkFormNumber(ob,name)
{
	//alert("ob:"+ob+"value:"+ob.value);
	ob.required = "true";//验证非空
	ob.captionName = name;
	if(!checkNumber(ob.value,null,null))
	{//验证数字正确性
		alert("“"+ob.captionName+"”不是正确数字！");
		ob.select();
		return false;
	}else{
		return true;
	}
}

//function  : 检查输入字符串是否由数字组成
//parameters: DigitString: String,待检查字符串的值
//			  totalLength: int, 数字的长度限制（不包括小数点）
//			  decimalLength: int，小数的长度限制
//            totalLength-decimalLength 整数部分的限制
//            isPositive 是否为非负数 true表示一定要是非负数，false表示一定要是负数，null表示任意 add by zhangp 2003-09-25
//return    : false: 字符串中包含除数字外的字符
//			  true : else
function checkNumber(DigitString, totalLength, decimalLength,isPositive)
{
    var re = "";
    
    if(isPositive == "true" || isPositive == true)
    {//如果是非负数
        if(isNaN(DigitString*1) || DigitString*1<0)
            return false;
    }
    if(isPositive == "false" || isPositive == false)
    {//如果是负数
        if(isNaN(DigitString*1) || DigitString*1>=0)
            return false;
    }

    
    if (decimalLength!=null && decimalLength != 0)
    {   //小数不为空
        re = re+"\\.[\\d]{1,"+ decimalLength +"}"
    }

    if (totalLength == null)
    {   //未设定总长度
        re= "\\d*"+re;
    }
    else
    {   //设定总长度
        //当小数部分为空的情况下，就是判断数字的长度
        var intergerLength = totalLength;
        if (decimalLength!=null)
        {   //当小数部分不为空判断，整数的位数和小数的位数是否正确
            intergerLength = totalLength-decimalLength;
        }
        re="([\\d]{0,"+ intergerLength +"}"+re+"|[\\d]{0,"+intergerLength+"})";
    }
    re = new RegExp("^"+re+"$","g");

    //当字符串为空、位数不对、不能匹配成数字时
    if(re.exec(DigitString) == null)
    {
        return false;
    }
    return true;
}


<%/*保存*/%>
function doSave()
{

	//验证减免分类、减免税种、减免项目不能完全相同
	var jmfldms = document.getElementsByName("jmfldm");
	var szsmdms = document.getElementsByName("szsmdm");
	var jmxmdms = document.getElementsByName("jmxmdm");
	var pks = new Array();
	for(var i=0; i<jmfldms.length; i++) {
		var pk = jmfldms[i].value + "-" + szsmdms[i].value + "-" + jmxmdms[i].value;
		for(var j=0; j<pks.length; j++) {
			if(pk == pks[j]) {
				alert("减免分类、减免税种、减免项目不能完全相同！");
				return false;
			}
		}
		pks[i] = pk;
	}
	
	var old  = document.forms[0].ywczlx.value;
	var JmsTable=document.getElementById("JmsTable");
	var qyrs  = document.forms[0].qyrs.value;
	var azrs  = document.forms[0].azrs.value;
	var dqxse = document.forms[0].dqxse.value;
	var dqlrze = document.forms[0].dqlrze.value;
	
	if(!checkFormNumber(document.forms[0].qyrs,"企业人数"))
    {
         document.forms[0].qyrs.focus();
         return false;
    }
    if(!checkFormNumber(document.forms[0].azrs,"安置人数"))
    {
         document.forms[0].azrs.focus();
         return false;
    }
    if(dqxse == null || dqxse =="")
    {
    	alert("请填写“当期销售额”，单位：元 ");
    	document.forms[0].dqxse.focus();
        return false;
    }
    if(dqlrze == null || dqlrze =="")
    {
    	alert("请填写“当期利润总额”，单位：元 ");
    	document.forms[0].dqlrze.focus();
        return false;
    }
    if(qyrs == null || qyrs =="")
    {
    	alert("请填写“企业人数”，单位：人 ");
    	document.forms[0].qyrs.focus();
        return false;
    }
    if(azrs == null || azrs =="")
    {
    	alert("请填写“其中：安置”，单位：人 ");
    	document.forms[0].azrs.focus();
        return false;
    }
    if(azrs - qyrs > 0)
    {
    	alert("安置人数不能大于企业人数！");
    	document.forms[0].azrs.focus();
        return false;
    }
	
	itemsize = JmsTable.rows.length-2;
    if(itemsize == 0)
	{
		alert("对不起，您的申报资料为空！");
		return false;
	}
	else
	{
        	if(itemsize > 1)
		{
			for(var i=0;i<itemsize;i++)
			{
				if(document.forms[0].jsje[i].value == null ||
					document.forms[0].jsje[i].value == "" ||
                    document.forms[0].jmse[i].value == null ||
                    document.forms[0].jmse[i].value == "")
                {
					alert("请完整填写申报资料！");
					return false;
				}
				else
				{
					var strValue = Math.round((parseFloat(document.forms[0].jsje[i].value)-parseFloat(document.forms[0].jmse[i].value))*100)/100;
					if(parseFloat(strValue) < 0)
					{
						alert("减免金额必须小于或等于计税金额，请检查第"+(i+1)+"行减免金额！");
                        return false;
					}
				}
				
				if(document.forms[0].szsmdm[i].value == null ||
                                   	document.forms[0].szsmdm[i].value == "")
				{
						alert("您没有选择减免税项目！");
						return false;
				}
				var szsmdm_i = document.forms[0].szsmdm[i].value;
				var jmlx_i = document.forms[0].jmfldm[i].value;
				var jmxm_i = document.forms[0].jmxmjdm[i].value;
				for(var j=i+1;j<itemsize;j++)
				{
					var szsmdm_j = document.forms[0].szsmdm[j].value;
                    var jmlx_j = document.forms[0].jmfldm[j].value;
                    var jmxm_j = document.forms[0].jmxmjdm[j].value;
					if(szsmdm_i == szsmdm_j && jmlx_i == jmlx_j && jmxm_i == jmxm_j)
					{
						//alert("您申报了相同的减免税项目！");
						//alert("减免分类、减免税种、减免项目不能完全相同！");
						//return false;
					}
				}
				var aslj = false;
				for(var j=0;j<sm.length;j++)
    				{
        				if(szsmdm_i == sm[j][0])
					{
						aslj = sm[j][2];
						break;
        				}
				}
				
				if(document.forms[0].jmxmksrq[i].value == null || document.forms[0].jmxmksrq[i].value == "")
				{
					alert("请正确填写减免项目开始日期，YYYYMMDD，例如：20140601");
            		document.forms[0].jmxmksrq[i].focus();
            		return false;
				}
				else
				{
					if(!checkDate2(document.forms[0].jmxmksrq[i].value))
        			{
            			alert("请正确填写减免项目开始日期，YYYYMMDD，例如：20140601");
            			document.forms[0].jmxmksrq[i].focus();
            			return false;
        			}
    			}
    			if(document.forms[0].jmxmjsrq[i].value!='')
				{
					if(!checkDate2(document.forms[0].jmxmjsrq[i].value))
        			{
            			alert("请正确填写减免项目结束日期，YYYYMMDD，例如：20140601");
            			document.forms[0].jmxmjsrq[i].focus();
            			return false;
        			}
    			}
    			if(document.forms[0].jmxmdm[i].value == null || document.forms[0].jmxmdm[i].value == "")
				{
					alert("减免项目及代码不能为空，请重新选择第"+(i+1)+"行“减免税种”获取“减免项目及代码”数据！");
            		return false;
				}
    			
				if(aslj && (document.forms[0].kssl[i].value == null ||
					document.forms[0].kssl[i].value == "" ||
					parseFloat(document.forms[0].kssl[i].value) == 0))
				{
					alert("请填写课税数量！");
					return false;
				}
				if(document.forms[0].jmxmdm[i].length == 0)
				{
					alert("您所选税种税目（"+document.forms[0].szsmdm[i].value +"）不存在对应的减免政策，即“减免项目及代码”项为空，请删除该税种税目，再进行保存！");
					return false;
				}
                        }
                }
                else if(itemsize == 1)
		{
                        var aslj = false;
			for(var j=0;j<sm.length;j++)
    			{
        			if(document.forms[0].szsmdm.value == sm[j][0])
				{
					aslj = sm[j][2];
					break;
        			}
			}
			if(document.forms[0].kssl.length == null)
			{
                		if(document.forms[0].jsje.value == null ||
                       			document.forms[0].jsje.value == "" ||
                       			document.forms[0].jmse.value == null ||
                       			document.forms[0].jmse.value == "")
                       		{
                       			alert("请完整填写申报资料！");
                               	return false;
                       		}
                       		else
				{
					
					var strValue = Math.round((parseFloat(document.forms[0].jsje.value)-parseFloat(document.forms[0].jmse.value))*100)/100;
					if(parseFloat(strValue) < 0)
					{
						alert("减免金额必须小于或等于计税金额，请检查减免金额！");
                        return false;
					}
				}
                       		
                       		if(document.forms[0].szsmdm.value == null ||
                       			document.forms[0].szsmdm.value == "")
				{
					alert("您没有选择减免税项目！");
					return false;
				}
				
				if(document.forms[0].jmxmksrq.value == null || document.forms[0].jmxmksrq.value == "")
				{
					alert("请正确填写减免项目开始日期，YYYYMMDD，例如：20140601");
            		document.forms[0].jmxmksrq.focus();
            		return false;
				}
				else
				{
					if(!checkDate2(document.forms[0].jmxmksrq.value))
        			{
            			alert("请正确填写减免项目开始日期，YYYYMMDD，例如：20140601");
            			document.forms[0].jmxmksrq.focus();
            			return false;
        			}
    			}
    			if(document.forms[0].jmxmjsrq.value!='')
				{
					if(!checkDate2(document.forms[0].jmxmjsrq.value))
        			{
            			alert("请正确填写减免项目结束日期，YYYYMMDD，例如：20140601");
            			document.forms[0].jmxmjsrq.focus();
            			return false;
        			}
    			}
    			
    			if(document.forms[0].jmxmdm.value == null || document.forms[0].jmxmdm.value == "")
				{
					alert("减免项目及代码不能为空，请重新选择“减免税种”获取减免项目及代码数据！");
            		return false;
				}
				
				if(aslj && (document.forms[0].kssl.value == null ||
					document.forms[0].kssl.value == "" ||
					parseFloat(document.forms[0].kssl.value) == 0))
				{
					alert("请填写课税数量！");
					return false;
				}
				if(document.forms[0].jmxmdm.length == 0)
				{
					alert("您所选税种税目（"+document.forms[0].szsmdm.value +"）不存在对应的减免政策，即“减免项目及代码”项为空，请删除该税种税目，再进行保存！");
					return false;
				}
			}
			else
			{
                		if(document.forms[0].jsje[0].value == null ||
                       			document.forms[0].jsje[0].value == "" ||
                       			document.forms[0].jmse[0].value == null ||
                       			document.forms[0].jmse[0].value == "" )
                       		{
                       			alert("请完整填写申报资料！");
                               		return false;
                       		}
                       		else
				{
					var strValue = Math.round((parseFloat(document.forms[0].jsje[0].value)-parseFloat(document.forms[0].jmse[0].value))*100)/100;
					if(parseFloat(strValue) < 0)
					{
						alert("减免金额必须小于或等于计税金额，请检查减免金额！");
                        return false;
					}
				}
                       		if(document.forms[0].szsmdm[0].value == null ||
                       			document.forms[0].szsmdm[0].value == "")
				{
					alert("您没有选择减免税项目！");
					return false;
				}

				
				if(document.forms[0].jmxmksrq[0].value == null || document.forms[0].jmxmksrq[0].value == "")
				{
					alert("请正确填写减免项目开始日期，YYYYMMDD，例如：20140601");
            		document.forms[0].jmxmksrq[0].focus();
            		return false;
				}
				else
				{
					if(!checkDate2(document.forms[0].jmxmksrq[0].value))
        			{
            			alert("请正确填写减免项目开始日期，YYYYMMDD，例如：20140601");
            			document.forms[0].jmxmksrq[0].focus();
            			return false;
        			}
    			}
    			if(document.forms[0].jmxmjsrq[0].value!='')
				{
					if(!checkDate2(document.forms[0].jmxmjsrq[0].value))
        			{
            			alert("请正确填写减免项目结束日期，YYYYMMDD，例如：20140601");
            			document.forms[0].jmxmjsrq[0].focus();
            			return false;
        			}
    			}
    			if(document.forms[0].jmxmdm[0].value == null || document.forms[0].jmxmdm[0].value == "")
				{
					alert("减免项目及代码不能为空，请重新选择“减免税种”获取减免项目及代码数据！");
            		return false;
				}
				if(aslj && (document.forms[0].kssl[0].value == null ||
					document.forms[0].kssl[0].value == "" ||
					parseFloat(document.forms[0].kssl[0].value) == 0))
				{
					alert("请填写课税数量！");
					return false;
				}
				if(document.forms[0].jmxmdm[0].length == 0)
				{
					alert("您所选税种税目（"+document.forms[0].szsmdm[0].value +"）不存在对应的减免政策，即“减免项目及代码”项为空，请删除该税种税目，再进行保存！");
					return false;
				}
			}
		}
		if(confirm(confirmSave))
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
				alert("未知的操作类型：" + document.forms[0].ywczlx.value);
				return false;
			  }
			document.forms[0].actionType.value = "Save";
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
			   if(!doSubmitXML(document.forms[0],"Save",false,"",true))
				{
				    document.forms[0].ywczlx.value = old;
					return false;
			   }
				
			}
       		return true;
    	}
		else
		{
       		return false;
    	}
	}
}

function doDeleteAll()
{
	if(canDelete==1)
	{
		alert("对不起，您没有进行减免税申报");
		return false;
	}
	var old=document.forms[0].ywczlx.value;
		if(confirm("您将删除本次申报资料，确认？"))
		{
			changeYwczlx("3");
			document.forms[0].actionType.value = "Delete";
			if (g_objSI.Container != '')
			{
				if (!doSubmitXML(document.forms[0],"Delete",true,g_Doc.parseXmlDoc.xml,true))
				{
					changeYwczlx(old)
					return false;
				}
			}
			else
			{
			   if(!doSubmitXML(document.forms[0],"Delete",false,g_Doc.parseXmlDoc.xml,true))
				{
				   changeYwczlx(old);
					return false;
			   }
			}
			return true;
		}
		else
		{
			return false;
		}	

}
<%/*清除*/%>
function doClear()
{
   	return true;
}
<%/*返回*/%>
function doReturn()
{
	if(confirm(confirmReturn))
	{
       	document.forms[0].actionType.value = "Return";
		//document.forms[0].submit();
		if (!doSubmitXML(document.forms[0],"Return",false,"return",true))
		{
		  return false;
		}
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
		var JmsTable=document.getElementById("JmsTable");
		var delRows = JmsTable.rows.length-1;
		for(var i=0;i<delRows;i++)
		{
			if(delIndex[i] == obj)
       				JmsTable.deleteRow(i);
                }
		refreshSerialNumber();
		computerHj();
    	}
	else
	{
       		return false;
    	}
}

function refreshSerialNumber()
{
	var JmsTable=document.getElementById("JmsTable");
	var rows = JmsTable.rows;
	var length = rows.length;
	for (var i=1; i<length-1; i++)
	{
		rows[i].cells[0].innerHTML = i+"<input type=\"hidden\" name=\"hc\" id=\"hc\" value='"+i+"'></input>";
	}
}
function computerHj()
{
	var JmsTable=document.getElementById("JmsTable");
    itemsize = JmsTable.rows.length-2;
	if(itemsize > 1)
	{
		var total = 0;
		for(var i=0;i<itemsize;i++)
		{
			if(document.forms[0].jmse[i].value != null && document.forms[0].jmse[i].value != "")
			{
				if(checkvalue(document.forms[0].jmse[i],0) && formatCurrency(document.forms[0].jmse[i],0))
				{
					total = parseFloat(total) + parseFloat(document.forms[0].jmse[i].value);
				}
			}
		}
		document.forms[0].hj.value = Math.round(total*100)/100;
		formatCurrency(document.forms[0].hj,0);
	}
	else if(itemsize == 1)
	{
		if(document.forms[0].jmse.length == null)
		{
			if(document.forms[0].jmse.value != null && document.forms[0].jmse.value != "")
			{
				if(checkvalue(document.forms[0].jmse,0) && formatCurrency(document.forms[0].jmse,0))
				{
					document.forms[0].hj.value = Math.round(document.forms[0].jmse.value*100)/100;
					formatCurrency(document.forms[0].hj,0);
				}
				else
				{
					document.forms[0].hj.value = "0.00";
				}
			}
			else
			{
				document.forms[0].hj.value = "0.00";
			}
		}
		else
		{
			if(document.forms[0].jmse[0].value != null && document.forms[0].jmse[0].value != "")
			{
				if(checkvalue(document.forms[0].jmse[0],0) && formatCurrency(document.forms[0].jmse[0],0))
				{
					document.forms[0].hj.value = Math.round(document.forms[0].jmse[0].value*100)/100;
					formatCurrency(document.forms[0].hj,0);
				}
				else
				{
					document.forms[0].hj.value = "0.00";
				}
			}
			else
			{
				document.forms[0].hj.value = "0.00";
			}
                }
	}
	else
	{
		document.forms[0].hj.value = "0.00";
	}
	return true;
}




//判断
//参数 obj 需要检验值的对象
//参数 par
//			0 -- 如果值不合法则改为0
//			1 -- 不校验是否小于0
//			2 -- 不能大于100
//			3 -- 不能大于1
//注：trim()函数已包含在公用函数库中
function checkLrValue(obj,par)
{
	var tmpValue = trim(obj.value);
	var tmp = "";
	while(tmpValue.length>0 && tmpValue.charAt(0)=='0')
	{
		tmpValue = tmpValue.substring(1);
	}
	if(tmpValue.length > 0)
	{
		switch (par)
		{
			case 0:
				if(tmpValue == null || isNaN(tmpValue))
				{
					//for(var loc=0;loc<tmpValue.length;loc++)
					//{
					//	if(tmpValue.charAt(loc) >= '0' && tmpValue.charAt(loc) <= '9')
					//	{
					//		tmp += tmpValue.charAt(loc);
					//	}
					//}
					alert("输入值错误！");
					obj.focus();
					obj.select();
					return false;
				}
				else
				{
					tmp = tmpValue;
				}
				break;
			case 1:
				if(tmpValue == null || isNaN(tmpValue))
				{
					//for(var loc=0;loc<tmpValue.length;loc++)
					//{
					//	if((tmpValue.charAt(loc) >= '0' && tmpValue.charAt(loc) <= '9') ||
					//	(loc == 0 && tmpValue.charAt(loc) == '-'))
					//	{
					//		tmp += tmpValue.charAt(loc);
					//	}
					//}
					alert("输入值错误！");
					obj.focus();
					obj.select();
					return false;
				}
				else
				{
					tmp = tmpValue;
				}
				break;
			case 2:
				if(tmpValue == null || isNaN(tmpValue) || tmpValue < 0)
				{
					//for(var loc=0;loc<tmpValue.length;loc++)
					//{
					//	if(tmpValue.charAt(loc) >= '0' && tmpValue.charAt(loc) <= '9')
					//	{
					//		tmp += tmpValue.charAt(loc);
					//	}
					//}
					alert("输入值错误！");
					obj.focus();
					obj.select();
					return false;
				}
				else
				{
					if(parseFloat(tmpValue) <= 0 || parseFloat(tmpValue) > 100)
					{
						alert("请输入(0－100]之间的数字！");
						obj.focus();
						obj.select();
						return false;
					}
				}
				break;
			case 3:
				if(tmpValue == null || isNaN(tmpValue) || tmpValue < 0)
				{
					alert("输入值错误！");
					obj.focus();
					obj.select();
					return false;
				}
				else
				{
					if(parseFloat(tmpValue) <= 0 || parseFloat(tmpValue) >= 1 || tmpValue.length > 3)
					{
						alert("请输入(0－1)之间并且小数点后不能超过2位的小数！");
						obj.focus();
						obj.select();
						return false;
					}
				}
				break;
			default:
				//if(tmp.length>1 && tmp.charAt(0)=='0')
				//{
				//	tmp = tmp.substring(1);
				//}
				break;
		}
	}
	else if(tmpValue.length == 0)
	{
		switch (par)
		{
			case 2:
			case 3:
				alert("输入值不能为0！");
				obj.focus();
				obj.select();
				return false;
		}
	}
	//obj.value = tmp;
	//return obj;
	return true;
}



function checkDate2(strDate)
{
    if (strDate.length!=8) return false;
    var strYear = strDate.substr(0,4);
    var strMonth = strDate.substr(4,2);
    var strDay = strDate.substr(6,2);
    strSep="-"
    var objDate = new Date(strMonth+strSep+strDay+strSep+strYear);
    if (isNaN(objDate)) return false; //不是日期类型
    //日期类型范围 1900<年<2100
    if(strYear*1<1900) return false;
    if ((strYear*1 != objDate.getYear()*1)&&(strYear*1 != objDate.getYear()*1+1900)) return false; //输入的年不对
    if(strSep!="-") return false; //年月分割符必须是'-'
    if (strMonth*1 != objDate.getMonth()*1+1) return false; //输入的月份不对
    if(strSep!="-") return false; //年月分割符必须是'-'
    if (strDay*1 != objDate.getDate()*1) return false; //don't call getDay function. //输入的日期不对
    return true;
}

//显示文本框
function helpor_net_show(obj)
{
	var showValue = obj.value;
	if(showValue != null || showValue !="")
	{
		for(var i=0;i<jmxm.length;i++)
		{
			var jmxmdmValue = jmxm[i][1];
			var jmxmdmText = jmxm[i][2];
			if(jmxmdmValue == showValue)
			{
				document.all.tooltip2.innerHTML='<textarea rows="5" cols="40" style="border:1px; solid #3399ff; overflow:hidden;background-color:seashell" >'+jmxmdmText+'</textarea>';
				document.all.tooltip2.style.pixelLeft=event.clientX+document.body.scrollLeft+50;
				document.all.tooltip2.style.pixelTop=event.clientY+document.body.scrollTop-100;
				document.all.tooltip2.style.visibility="visible";
			}
		}
	}
}
//隐藏文本框
function helpor_net_hide()
{
	document.all.tooltip2.style.visibility="hidden";
}

</script>
		<link href="<%=static_contextpath%>css/text.css" rel="stylesheet"
			type="text/css">
		<style>
input {
	font-size: 9pt;
	text-align: right;
}
</style>

	</head>

	<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
		marginheight="0" style="margin: 0" onload="parseXmlOnLoad();">
		<table width="98%" height="100%" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr>
				<td align="center" colspan=2 valign="top">
					<jsp:include page="../include/SbHeader.jsp" flush="true">
						<jsp:param name="name" value="减免税申报表" />
						<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm" />
					</jsp:include>
					<html:errors />

					<form name="form1" method="POST" action="/shenbao/jms.dc">
						<input name="actionType" type="hidden" id="actionType">
				</td>
			</tr>
			<tr>
				<td>
					<TABLE width="100%" border='0' cellpadding='0' cellspacing='0'
						align='left' class='black9'>
						<tr>
							<td valign="top">
								<br>
								<table align="center" cellspacing="0" class="table-99">
									<tr>
										<td class="1-td1">
											北京市地方税务局减免税申报表
										</td>
									</tr>
									<tr>
										<td class="1-td2">
											<br>
											<div id="result"></div>
											<br>
											<div align="center">
												<img src="<%=static_contextpath%>images/tianjia1.jpg"
													onmouseover="this.src='<%=static_contextpath%>images/tianjia2.jpg'"
													onmouseout="this.src='<%=static_contextpath%>images/tianjia1.jpg'"
													alt="增加" onclick="doAdd();hiddenSkrq();" style="cursor: hand">
												&nbsp;&nbsp;&nbsp;&nbsp;
												<img src="<%=static_contextpath%>images/baocun1.jpg"
													onmouseover="this.src='<%=static_contextpath%>images/baocun2.jpg'"
													onmouseout="this.src='<%=static_contextpath%>images/baocun1.jpg'"
													alt="保存" onclick="return doSave()" style="cursor: hand">
												&nbsp;&nbsp;&nbsp;&nbsp;
												<img src="<%=static_contextpath%>images/shanchu1.jpg"
													onmouseover="this.src='<%=static_contextpath%>images/shanchu2.jpg'"
													onmouseout="this.src='<%=static_contextpath%>images/shanchu1.jpg'"
													alt="删除" onclick="doDeleteAll()" style="cursor: hand">
												&nbsp;&nbsp;&nbsp;&nbsp;
												<img src="<%=static_contextpath%>images/fanhui1.jpg"
													onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'"
													onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'"
													alt="返回" onclick="doReturn()" style="cursor: hand">
											</div>
											<br>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					</form>
				</td>
			</tr>
			<tr>
				<td valign="bottom" align="center">
					<table width="99%" border="0" cellpadding="0" cellspacing="0" align="center">
          				<tr>
              				<td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
             	 			<td width="20%" align="center" class="black9"><strong><font color="#0000FF">注意事项</font></strong></td>
              				<td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
          				</tr>
      				</table>
      				<table width="99%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
          				<tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
              				<td height="47"  >
               			 	<p style ="color:red">
                    		&nbsp;&nbsp;&nbsp;&nbsp;1.当期销售额项填写本月实现销售收入，当期利润总额项填写本年累计利润。<br>
                    		&nbsp;&nbsp;&nbsp;&nbsp;2.“安置相关人员”填写要求：此处填写安置退役士兵、随军家属、军队转业干部、支持和促进就业、残疾人、员工制家政服务员的人数。按现行税收政策规定，如企业适用多种税收优惠政策，可以选择最优惠政策，但不能重复享受（或累加执行）。凡遇此情况的企业请按照所选择的优惠政策对应填写安置人数。
                			</p>
              				</td>
          				</tr>
      				</table>
					<br>
					<%@ include file="../include/bottom.jsp"%>
				</td>
			</tr>
		</table>
		<div id="tooltip2" style="position:absolute;visibility:hidden;clip:rect(0px,400px,500px,0px);width:290px;background-color:seashell"></div>
	</body>
</html>