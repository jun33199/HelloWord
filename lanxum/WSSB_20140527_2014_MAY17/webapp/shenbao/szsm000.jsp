<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@page import="java.util.Date"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.FriendHelper"%>
<%@page import="com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil"%>
<%@page import="com.ttsoft.bjtax.shenbao.constant.CodeTable"%>
<%@page import="com.ttsoft.bjtax.shenbao.constant.CodeConstant"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.domain.Sklx"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.client.JkInfor"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.client.WtdwInfor"%>
<%@page import="com.ttsoft.bjtax.shenbao.szsm.web.SzsmForm"%>
<%@page import="com.ttsoft.bjtax.shenbao.zhsb.web.ZhsbForm"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import="com.ttsoft.bjtax.sfgl.common.model.Sfxy"%>
<%@page import="com.ttsoft.bjtax.shenbao.proxy.LWUtil"%>
<%@page import="com.ttsoft.common.model.UserData"%>
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
    SzsmForm szsmForm = (SzsmForm)request.getAttribute("szsmForm");
%>
<script>
var static_contextpath = "<%=static_contextpath%>";
var local = "szsm000.jsp";
var myForm ;
var sklxdm ;
</script>
<% 
	boolean can_zcjk = false;
	boolean can_bjqs = false;
	boolean can_sdjj = false;
	int int_cospan = 1;
	
	Map codeMap = CodeTableUtil.getCodeTableMap(request,CodeTable.SKLX_MAP);
	Sklx zcjk_sklx = (Sklx)codeMap.get(CodeConstant.SKLXDM_ZCJK);//正常缴款
	Sklx bjqs_sklx = (Sklx)codeMap.get(CodeConstant.SKLXDM_BJQS);//补缴欠税
	Sklx sdjj_sklx = (Sklx)codeMap.get(CodeConstant.SKLXDM_SDJJ);//四代解缴
	
	String zcjk_sbsybs= new String();
	String sdjj_sbsybs= new String();
	
	zcjk_sbsybs = zcjk_sklx.getSbsybs();
	sdjj_sbsybs = sdjj_sklx.getSbsybs();
	
	String actionType = szsmForm.getActionType();
	String sbsybs = szsmForm.getSbsybs();
	//System.out.println("actionType:"+actionType+" sbsybs:"+sbsybs);
	if(actionType != null && actionType.equals("Show"))
	{
		if(sbsybs != null &&sbsybs.equals(CodeConstant.SKLXDM_SDJJ)){
			zcjk_sbsybs="2";
			sdjj_sbsybs="1";
		}else if(sbsybs != null &&sbsybs.equals(CodeConstant.SKLXDM_ZCJK)){
			zcjk_sbsybs="1";
			sdjj_sbsybs="2";
		}
	}
	
	if(bjqs_sklx != null && bjqs_sklx.getSbsybs() != null && bjqs_sklx.getSbsybs().equals("1"))
	{
    	can_bjqs = true;
    	int_cospan++;
	}
	if(zcjk_sklx != null && zcjk_sbsybs != null && zcjk_sbsybs.equals("1"))
	{
    	can_zcjk = true;
    	int_cospan++;
	}
	if(sdjj_sklx != null && sdjj_sbsybs != null && sdjj_sbsybs.equals("1"))
	{
    	can_sdjj = true;
    	int_cospan++;
	}
%>
<html>
<head>
<title>税种税目选择</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<script language="javascript" src="js/szsmTree.js"></script>
<script language="JavaScript">

//页面加载时执行该函数
function init()
{
	init_select_sklx();

}

//初始化缴款方式按钮
function init_select_sklx()
{
	var selectSKLX_Arr = document.getElementsByName("rd_jk");
	var zcjk_sbsybs_value = '<%=zcjk_sbsybs%>';
	var sdjj_sbsybs_value = '<%=sdjj_sbsybs%>';
	
	for(index=0; index < selectSKLX_Arr.length; index++)
	{
		var sklxObj = selectSKLX_Arr[index];
		if(sklxObj.value == <%=CodeConstant.SKLXDM_ZCJK%> && zcjk_sbsybs_value == "1")
		{
			sklxObj.checked=true;
		}
		
		if(sklxObj.value == <%=CodeConstant.SKLXDM_SDJJ%> && sdjj_sbsybs_value == "1")
		{
			sklxObj.checked=true;
		}
	
	}
	
	
}

//变更税款类型时执行该方法，目的是重新加载页面，实现税款类型的变更以及控制与税款类型相关内容的显示和隐藏
function ChangeSKLX(val)
{
	if(val != null && val == <%=CodeConstant.SKLXDM_ZCJK%>)
	{
		var zcjk = "<%=CodeConstant.SKLXMC_ZCJK%>";
		if(!window.confirm("您选择的税款类型为："+ zcjk +"，是否继续？"))
		{
			return false;//不同意，返回
		}
	}
	
	if(val != null && val == <%=CodeConstant.SKLXDM_SDJJ%>)
	{
		var wtdz = "<%=CodeConstant.SKLXMC_DZJK%>";
		if(!window.confirm("您选择的税款类型为："+ wtdz +"，是否继续？"))
		{
			return false;//不同意，返回
		}
	}
	
	//给actionType赋值
	document.forms[0].actionType.value="Show";
	//给sbsybs赋值
	document.forms[0].sbsybs.value=val;
	//提交表单
	document.forms[0].submit();
}

function checkValidate()
{
<%
if(!can_zcjk && !can_bjqs && !can_sdjj)
{
%>
    return false;
<%
}

if(can_zcjk)
{
%>
    var element = document.forms[0].zcjk;
<%
}else if(can_bjqs)
{
%>
    var element = document.forms[0].bjqs;
<%
}else if(can_sdjj)
{
%>
    var element = document.forms[0].sdjj;
<%
}
%>
    if (element == null)
    {
        return false;
    }
    if (element.length == null)
    {
        if (element.checked <%if(can_zcjk){out.print(" || document.forms[0].zcjk.checked");} if(can_bjqs){out.print(" || document.forms[0].bjqs.checked");} if(can_sdjj){out.print(" || document.forms[0].sdjj.checked");}%>)
        {
            return true;
        }
    }

    for(var i=0; i<element.length; i++)
    {
        if (element[i].checked <%if(can_zcjk){out.print(" || document.forms[0].zcjk[i].checked");} if(can_bjqs){out.print(" || document.forms[0].bjqs[i].checked");} if(can_sdjj){out.print(" || document.forms[0].sdjj[i].checked");}%>)
        {
            return true;
        }
    }
    return false;
}

<%
SzsmForm form = (SzsmForm)request.getAttribute("szsmForm");
List jkInfor = form.getJkInfor();
StringBuffer jkinfor_zcjk = new StringBuffer("var jkinfor_zcjk = [");
StringBuffer jkinfor_bjqs = new StringBuffer("var jkinfor_bjqs = [");
StringBuffer jkinfor_sdjj = new StringBuffer("var jkinfor_sdjj = [");
for (int i=0; i<jkInfor.size(); i++)
{
    JkInfor jk = (JkInfor)jkInfor.get(i);
    String sklx = jk.getSklxdm();
    StringBuffer tmp = sklx.equals(CodeConstant.SKLXDM_ZCJK) ? jkinfor_zcjk :
                       sklx.equals(CodeConstant.SKLXDM_BJQS) ? jkinfor_bjqs :
                       sklx.equals(CodeConstant.SKLXDM_SDJJ) ? jkinfor_sdjj : null;
    if (tmp == null)
    {
        break;
    }
    tmp.append("[\"").append(jk.getSzsmdm()).append("\",\"").append(jk.getSzsmmc()).
        append("\",\"").append(JspUtil.format(jk.getSjse())).append("\"],");
}
jkinfor_zcjk.append("[\"\",\"\",\"\"]];");
jkinfor_bjqs.append("[\"\",\"\",\"\"]];");
jkinfor_sdjj.append("[\"\",\"\",\"\"]];");

out.println(jkinfor_zcjk.toString());
out.println(jkinfor_bjqs.toString());
out.println(jkinfor_sdjj.toString());

List toBeAlertSzsm = form.getToBeAlertSzsm();
String[] str;
//System.out.println("------toBeAlertSzsm-------"+toBeAlertSzsm.size());
StringBuffer szsmtmp = new StringBuffer("var szsmtmp = [");
for (int i=0; i<toBeAlertSzsm.size(); i++)
{
		szsmtmp=szsmtmp.append("[");
		str=(String[]) toBeAlertSzsm.get(i);
		for(int j=0;j<str.length;j++)
		{	
			szsmtmp=szsmtmp.append("\"").append(str[j]).append("\"");
			if(j!=str.length-1)
			{
				szsmtmp=szsmtmp.append(",");
			}else
			{
				szsmtmp=szsmtmp.append("]");
			}
		}
		if(i!=toBeAlertSzsm.size()-1)
		{
			szsmtmp=szsmtmp.append(",");
		}
}
szsmtmp=szsmtmp.append("];");
out.println(szsmtmp.toString());

//start 获取委托代征税种税目  tujb 201406
List wtdzszsm = form.getWtdzszsm();
//System.out.println("2 ------wtdzszsm-------"+wtdzszsm.size());
String[] strWtdzszsm;
StringBuffer wtdzszsmtmp = new StringBuffer("var wtdzszsmtmp = [");
for (int i=0; i<wtdzszsm.size(); i++)
{
		wtdzszsmtmp=wtdzszsmtmp.append("[");
		strWtdzszsm=(String[]) wtdzszsm.get(i);
		for(int j=0;j<strWtdzszsm.length;j++)
		{	
			wtdzszsmtmp=wtdzszsmtmp.append("\"").append(strWtdzszsm[j]).append("\"");
			if(j!=strWtdzszsm.length-1)
			{
				wtdzszsmtmp=wtdzszsmtmp.append(",");
			}else
			{
				wtdzszsmtmp=wtdzszsmtmp.append("]");
			}
		}
		if(i!=wtdzszsm.size()-1)
		{
			wtdzszsmtmp=wtdzszsmtmp.append(",");
		}
}
wtdzszsmtmp=wtdzszsmtmp.append("];");
out.println(wtdzszsmtmp.toString());
//end 获取委托代征税种税目  tujb 201406

//委托代征、代扣、代售、监督代售单位认定情况
List wtdwInfor = form.getWtdwInfor();
%>

//检查税种税目是否含有这6种 add by lijn20141222
//function checkSzsm2014(){
//	var szsms = document.getElementsByName("zcjk");
	
//	if(szsms.length==0){
//		szsms = document.getElementsByName("sdjj");
//	}
//	if(szsms.length==0){
//		return;
//	}

//	var special ="100030,100020,510030,510020,540030,540020";

//	for(var i=0;i<szsms.length;i++){
//		if(special.indexOf(szsms[i].value)>=0){
//			alert("2015年1月1日起，我市地税局委托国税局代征在国税局网上申报增值税、消费税纳税人应缴纳的城市维护建设税、教育费附加、地方教育附加（以下简称“一税两费”）。请您通过北京市国税局网上申报系统，在申报增值税、消费税时，一并申报“一税两费”。");
//		   return;
//		}
//	}
//}


function nextStep()
{
	
	
	
    var msg_head = "您本月已经申报过";
    var msg_body = new Array(3);
    var msg_end = "\n\n如该税票未交银行，请先用查看本期缴款书中的作废功能删除本期上次申报的数据后再重新申报。如该税票已交银行，请点击确定按钮后继续进行该税的第二次申报，生成新的缴款书！是否继续申报？";

    if(!checkValidate())
    {
        alert("请先选择税目！");
        return false;
    }
    
   // checkSzsm2014();
    
<%
if(can_zcjk)
{
%>
    var element = document.forms[0].zcjk;
    var compareArray = new Array();
    var con = 0;
    if (element.length != null)
    {
        for(var i=0;i<element.length;i++)
        {
            var canCompare = true;
            //alert(element[i].value.substring(0,2));
            for(var k=0;k<compareArray.length;k++)
            {
                //alert(compareArray[k].substring(0,2));
                if(element[i].checked && element[i].value.substring(0,2)==compareArray[k].substring(0,2))
                {
                    canCompare = false;
                    break;
                }
            }
            if(element[i].checked && canCompare)
            {
                compareArray[con] = element[i].value;
                con++;
            }
        }
    }
    for(var i=0;i<jkinfor_zcjk.length;i++)
    {
				if (jkinfor_zcjk[i][0] != "")
                {
                    if(msg_body[0] == null || msg_body[0] == "")
                    {
                        msg_body[0] = "\n正常：";
                    }
                    tmp_msg = "\n    " + jkinfor_zcjk[i][1] +
                        "(" + jkinfor_zcjk[i][0] + ")" + "\t\t" +
                        jkinfor_zcjk[i][2] + "元！";
                    msg_body[0] += tmp_msg;
                }
//        if (element.length == null)
//        {
//            if (element.checked && element.value.substring(0,2) == jkinfor_zcjk[i][0])
//            {
//                if(msg_body[0] == null || msg_body[0] == "")
//                {
//                    msg_body[0] = "\n<%=zcjk_sklx.getSklxmc()%>：";
//                }
//                tmp_msg = "\n    " + jkinfor_zcjk[i][1] +
//                        "(" + jkinfor_zcjk[i][0] + ")" + "\t\t" +
//                        jkinfor_zcjk[i][2] + "元！";
//                msg_body[0] += tmp_msg;
//            }
//        }
//        else
//        {
//            for(var j=0; j<compareArray.length; j++)
//            {
//                if (compareArray[j].substring(0,2) == jkinfor_zcjk[i][0])
//                {
//                    if(msg_body[0] == null || msg_body[0] == "")
//                    {
//                        msg_body[0] = "\n<%=zcjk_sklx.getSklxmc()%>：";
//                    }
//                    tmp_msg = "\n    " + jkinfor_zcjk[i][1] +
//                        "(" + jkinfor_zcjk[i][0] + ")" + "\t\t" +
//                        jkinfor_zcjk[i][2] + "元！";
//                    msg_body[0] += tmp_msg;
//                }
//            }
//        }
    }
    
    var element1 = document.getElementsByName("zcjk");
    if (element1.length != null)
    {
        for(var i=0;i<element1.length;i++)
        {  
            var tsnyArr = new Array();
						tsnyArr = getTsny(szsmtmp,element1[i].value);
						if(tsnyArr.length != 0 && element1[i].checked ){					 
							 var checkResult = doAlertCheck(tsnyArr);
							 if(checkResult == false){
									return  checkResult;
							 }						 
						}
        }
    }    
<%
}
%>
<%
if(can_bjqs)
{
%>
    var element = document.forms[0].bjqs;
    var compareArray = new Array();
    var con = 0;
    if (element.length != null)
    {
        for(var i=0;i<element.length;i++)
        {
            {
                var canCompare = true;
                //alert(element[i].value.substring(0,2));
                for(var k=0;k<compareArray.length;k++)
                {
                    //alert(compareArray[k].substring(0,2));
                    if(element[i].checked && element[i].value.substring(0,2)==compareArray[k].substring(0,2))
                    {
                        canCompare = false;
                        break;
                    }
                }
                if(element[i].checked && canCompare)
                {
                    compareArray[con] = element[i].value;
                    con++;
                }
            }
        }
    }
    for(var i=0;i<jkinfor_bjqs.length;i++)
    {
                if (jkinfor_bjqs[i][0] != "")
                {
                    if(msg_body[1] == null || msg_body[1] == "")
                    {
                        msg_body[1] = "\n<%=bjqs_sklx.getSklxmc()%>：";
                    }
                    tmp_msg = "\n    " + jkinfor_bjqs[i][1] +
                                        "(" + jkinfor_bjqs[i][0] + ")" +
                        "\t\t" + jkinfor_bjqs[i][2] + "元！";
                    msg_body[1] += tmp_msg;
                }
//        if (element.length == null)
//        {
//            if (element.checked && element.value.substring(0,2) == jkinfor_bjqs[i][0])
//            {
//                    if(msg_body[1] == null || msg_body[1] == "")
//                    {
//                        msg_body[1] = "\n<%=bjqs_sklx.getSklxmc()%>：";
//                    }
//                    tmp_msg = "\n    " + jkinfor_bjqs[i][1] +
//                                        "(" + jkinfor_bjqs[i][0] + ")" +
//                        "\t\t" + jkinfor_bjqs[i][2] + "元！";
//                    msg_body[1] += tmp_msg;
//            }
//        }
//        else
//        {
//            for(var j=0; j<compareArray.length; j++)
//            {
//                if (compareArray[j].substring(0,2) == jkinfor_bjqs[i][0])
//                {
//                    if(msg_body[1] == null || msg_body[1] == "")
//                    {
//                        msg_body[1] = "\n<%=bjqs_sklx.getSklxmc()%>：";
//                    }
//                    tmp_msg = "\n    " + jkinfor_bjqs[i][1] +
//                                        "(" + jkinfor_bjqs[i][0] + ")" +
//                        "\t\t" + jkinfor_bjqs[i][2] + "元！";
//                    msg_body[1] += tmp_msg;
//                }
//            }
//        }
    }
<%
}
%>
<%
if(can_sdjj)
{
%>
    var element = document.forms[0].sdjj;
    var compareArray = new Array();
    var con = 0;
    if (element.length != null)
    {
        for(var i=0;i<element.length;i++)
        {
            {
                var canCompare = true;
                //alert(element[i].value.substring(0,2));
                for(var k=0;k<compareArray.length;k++)
                {
                    //alert(compareArray[k].substring(0,2));
                    if(element[i].checked && element[i].value.substring(0,2)==compareArray[k].substring(0,2))
                    {
                        canCompare = false;
                        break;
                    }
                }
                if(element[i].checked && canCompare)
                {
                    compareArray[con] = element[i].value;
                    con++;
                }
            }
        }
    }
    for(var i=0;i<jkinfor_sdjj.length;i++)
    {
                if (jkinfor_sdjj[i][0] != "")
                {
                    if(msg_body[2] == null || msg_body[2] == "")
                    {
                        msg_body[2] = "\n<%=sdjj_sklx.getSklxmc()%>：";
                    }
                    tmp_msg = "\n    " + jkinfor_sdjj[i][1] +
                                        "(" + jkinfor_sdjj[i][0] + ")" +
                        "\t\t" + jkinfor_sdjj[i][2] + "元！";
                    msg_body[2] += tmp_msg;
                }
//        if (element.length == null)
//        {
//            if (element.checked && element.value.substring(0,2) == jkinfor_sdjj[i][0])
//            {
//                    if(msg_body[2] == null || msg_body[2] == "")
//                    {
//                        msg_body[2] = "\n<%=sdjj_sklx.getSklxmc()%>：";
//                    }
//                    tmp_msg = "\n    " + jkinfor_sdjj[i][1] +
//                                        "(" + jkinfor_sdjj[i][0] + ")" +
//                        "\t\t" + jkinfor_sdjj[i][2] + "元！";
//                    msg_body[2] += tmp_msg;
//            }
//        }
//        else
//        {
//            for(var j=0; j<compareArray.length; j++)
//            {
//                if (compareArray[j].substring(0,2) == jkinfor_sdjj[i][0])
//                {
//                    if(msg_body[2] == null || msg_body[2] == "")
//                    {
//                        msg_body[2] = "\n<%=sdjj_sklx.getSklxmc()%>：";
//                    }
//                    tmp_msg = "\n    " + jkinfor_sdjj[i][1] +
//                                        "(" + jkinfor_sdjj[i][0] + ")" +
//                        "\t\t" + jkinfor_sdjj[i][2] + "元！";
//                    msg_body[2] += tmp_msg;
//                }
//            }
//        }
    }
<%
}
%>
    var msg = msg_head;
    var msg_tmp = "";
    for(var i=0;i<msg_body.length;i++)
    {
        if(msg_body[i] != null && msg_body[i] != "")
        {
            msg_tmp += "\n" + msg_body[i];
        }
    }
    if(msg_tmp != null && msg_tmp != "")
    {
        msg += msg_tmp;
        msg += msg_end;
        if(true)
                  //  if(confirm(msg))
        {
		<%
            Sfxy sfxy=FriendHelper.getYhkkSfxy(request);
            UserData ud=(UserData)request.getSession(false).getAttribute("UserData");
            boolean isCA = ud.caflag;
            boolean isZhOK = false;
            boolean lwFlag=false;
            
            boolean ifhk = false;
            if(sfxy!=null)
            {
                isZhOK = ud.getSsdwdm().equals(sfxy.getSwjgzzjgdm());
                lwFlag=LWUtil.isLW(request.getSession().getServletContext(), ud.getSsdwdm(),sfxy.getYhdm());
                if(lwFlag&&isCA)
                {
                    if(isZhOK)
                    {       
                        out.println("alert(\"您签订了《网上申报实时缴税协议书》，将从"+sfxy.getYhmc()+"的"+sfxy.getZh()+"账户进行扣款!\");");
                        out.println("document.forms[0].action = \"zhsbWithSfxy.dc\";");
                        out.println("document.forms[0].submit();");
                    }
                    else
                    {
                        out.println("alert(\"您的主管税务机关发生变更，请与变更后的主管税务机关签订新的《网上申报实时缴税协议书》！\")");
                        out.println("return false;");
                    }
                }
                else
                {
                    out.println("document.forms[0].action = \"zhsbWithoutSfxy.dc\";");
                    out.println("document.forms[0].submit();");
                }
            }
            else
            {
                out.println("document.forms[0].action = \"zhsbWithoutSfxy.dc\";");
                out.println("document.forms[0].submit();");
            }
            ifhk = ((sfxy!=null) && lwFlag&&isCA);
			
			/*System.out.println(lwFlag+"lwFlag@@@@@@@@@@@@@@@@@");
			System.out.println(isCA+"isCA###############");
			System.out.println(ifhk+"ifhk**********************************************************");*/
        %>
        }
        else
        {
            return false;
        }
    }
    else
    {
		<%
		
			//Sfxy sfxy=FriendHelper.getYhkkSfxy(request);
			if(ifhk)
      {
			    out.println("alert(\"您签订了《网上申报实时缴税协议书》，将从"+sfxy.getYhmc()+"的"+sfxy.getZh()+"账户进行扣款!\");");
		  }
		%>
        document.forms[0].action = "<%=(!ifhk) ? "zhsbWithoutSfxy.dc":"zhsbWithSfxy.dc"%>";
        document.forms[0].submit();
    }
}

function doReturn()
{
    document.forms[0].action = "quit.do";
    document.forms[0].submit();
}

function adjust(szsmdm, szsmmc)
{
    var i=0;
    var ref = "";
    
<%  if(can_zcjk){%>
    	ref = document.forms[0].zcjk;
<%  }%>
<%  if(can_bjqs){%>
    	ref = document.forms[0].bjqs;
<%  }%>
<%  if(can_sdjj){%>
    	ref = document.forms[0].sdjj;
<%  }%>
    if (ref == null)
    {
        addRow(szsmdm, szsmmc);
        return;
    }
    var length = ref.length;
    var found = false;
    if (length == null)
    {
        if (ref.value == szsmdm)
        {
            found = true;
        }
    }
    else
    {
        for (i=0; i<length; i++)
        {
            if (ref[i].value == szsmdm)
            {
                found = true;
                break;
            }
        }
    }
    if (found)
    {
        Table_Master.deleteRow(i+3);
    }
    else
    {
        addRow(szsmdm, szsmmc);
    }
}

function addRow(szsmdm, szsmmc)
{
    var otr = Table_Master.insertRow();

    rowlength = Table_Master.rows.length-1;

    var cell = otr.insertCell();
    cell.bgColor = "F4F8F9";
    cell.innerText = "  " + szsmmc ;//+ "(" + szsmdm + ")";
<%
if(can_zcjk)
{
    List disabledItems = form.getToBeFilterdSzsm();
    out.print("    var disabledItems = [");
    if (disabledItems != null)
    {
        for (int i=0; i<disabledItems.size(); i++)
        {
            out.print("'" + ((com.ttsoft.bjtax.shenbao.model.domain.Szsm)disabledItems.get(i)).getSzsmdm() + "',");
        }
    }

    out.println("'-1'];");
%>
    var found = false;
    for (var i=0; i<disabledItems.length; i++)
    {
        if (szsmdm == disabledItems[i])
        {
            found = true;
            break;
        }
    }
    cell = otr.insertCell();
    cell.bgColor = "F1DFCF";

    if (found)
    {
        objInput = document.createElement("<INPUT type='checkbox' name='zcjk' value='" + szsmdm + "' disabled></INPUT>");
    }
    else
    {
        objInput = document.createElement("<INPUT type='checkbox' name='zcjk' value='" + szsmdm + "' checked></INPUT>");
    }
    cell.appendChild(objInput);
<%
}
if(can_bjqs)
{
%>
    cell = otr.insertCell();
    cell.bgColor = "CADBCE";
    objInput = document.createElement("<INPUT type='checkbox' name='bjqs' value='" + szsmdm + "'></INPUT>");
    cell.appendChild(objInput);
<%
}
if(can_sdjj)
{
	List disabledItems = form.getToBeFilterdSzsm();
    out.print("    var disabledItems = [");
    if (disabledItems != null)
    {
        for (int i=0; i<disabledItems.size(); i++)
        {
            out.print("'" + ((com.ttsoft.bjtax.shenbao.model.domain.Szsm)disabledItems.get(i)).getSzsmdm() + "',");
        }
    }

    out.println("'-1'];");
%>
    var found = false;
    for (var i=0; i<disabledItems.length; i++)
    {
        if (szsmdm == disabledItems[i])
        {
            found = true;
            break;
        }
    }
    cell = otr.insertCell();
    cell.bgColor = "DFD4DC";
    if (found)
    {
        objInput = document.createElement("<INPUT type='checkbox' name='sdjj' value='" + szsmdm + "' disabled></INPUT>");
    }
    else
    {
        objInput = document.createElement("<INPUT type='checkbox' name='sdjj' value='" + szsmdm + "' checked></INPUT>");
    }
    cell.appendChild(objInput);
<%
}
%>
}

function clearCheck()
{
    var myobj = document.forms[0].check_szsmdm;
    if (myobj == null)
    {
        return;
    }

    var length = document.forms[0].check_szsmdm.length;
    if (length == null)
    {
        if (!document.forms[0].check_szsmdm.disabled && document.forms[0].check_szsmdm.checked)
        {
            document.forms[0].check_szsmdm.checked = false;
        }
    }
    for (var i=0; i<length; i++)
    {
        var obj = document.forms[0].check_szsmdm[i];
        if (!obj.disabled && obj.checked)
        {
            obj.checked = false;
        }
    }
}

//根据关键值返回提示内容
function getTsny(arrayName,key){
  var obj = eval(arrayName);
  var tempArr = new Array();
  for(var ii=0;ii<obj.length;ii++){
		if(obj[ii][0]==key) tempArr.push(obj[ii]);
  }
  return tempArr;
}


//检查提示信息是否在提示有效日期内
function doAlertCheck(tsnyArr){	  
	  //alert("tsnyArr=="+tsnyArr);		
		//var szsmdm = tsnyArr[0][0];//税种税目代码
		//var txlxdm = tsnyArr[0][1];//提示类型代码	
		var tsny = tsnyArr[0][2];//提示内容			
		var tsksrq = tsnyArr[0][3];//提示开始日期
		var tsjsrq = tsnyArr[0][4];//提示结束日期	
		var nowdate = tsnyArr[0][5];//系统当前日期
		//var szsmmc = tsnyArr[0][6];//税种税目名称
		
			
		//判断系统当前日期是否在提示开始日期和提示结束日期范围内，是则提示tsny中的信息
		var inRangeOfTsrq = false;
		if(tsksrq != ""){
			if(parseFloat(nowdate) < parseFloat(tsksrq)){
				inRangeOfTsrq = true;
			}			
		}
		
		if(tsksrq == ""){
			if(tsjsrq != ""){
				if(parseFloat(nowdate) > parseFloat(tsjsrq)){
					inRangeOfTsrq = true;
				}			
			}		
		}

		if(tsjsrq != ""){
			if(parseFloat(nowdate) > parseFloat(tsjsrq)){
				inRangeOfTsrq = true;
			}			
		}
		
		if(tsjsrq == ""){
			if(tsksrq != ""){
				if(parseFloat(nowdate) < parseFloat(tsksrq)){
					inRangeOfTsrq = true;
				}			
			}	
		}
						
		//提示信息如下：		
		//如果当前日期在提示日期范围内，则提示
		if(inRangeOfTsrq == false){
			if(tsny != '' && tsny != null){
				if(!window.confirm(tsny+"\n\n"+"是否继续？点击“确定”继续进行申报，点击“取消”须删除该税种税目后继续申报。")){
					return false;//不同意，返回
				}
			}
		}		
}

function loadSZinfo(divObject){
var divObject = document.getElementById("szsmTree");
sklxdm = document.forms[0].sbsybs.value;
	loadRootSZ(divObject,sklxdm);
}

function loadRootSZ(divObject,sklxdm){
	divObject.innerHTML = onloadMsg;
	send_request("LoadRootSZ&sklxdm="+sklxdm+"","",divObject);
}

function ToggleDisplay(szsmdm,divitem, imgitem){
	loadRamoseSZSM(szsmdm,divitem,imgitem);
}

function loadRamoseSZSM(szsmdm,divitem,imgitem){
	sklxdm = document.forms[0].sbsybs.value;
	if ((divitem.style.display == "") || (divitem.style.display == "none")){
            divitem.style.display = "block";
            imgitem.src = "" + static_contextpath + "images/minus.gif";
     }else { 
            divitem.style.display = "none"; 
            imgitem.src = "" + static_contextpath + "images/plus.gif"; 
     }
	if(divitem.innerHTML.length!=0)return false;
	divitem.innerHTML = onloadMsg;
	send_request("LoadRamoseSZSM&szsmdm="+szsmdm+"&sklxdm="+sklxdm+"&local="+local,null,divitem);
}

function parseXMLOnLoad(){
	clearCheck();
	loadSZinfo();
	myForm = document.forms[0];
}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<STYLE TYPE='text/css'>
a:link {
    color: #6D929C;
    text-decoration: none;
}
a:visited {
    color: #6d929c;
    text-decoration: none;
}
a:hover {
    color: #00CC99;
    text-decoration: underline;
}
a:active {
    color: #A0A0A0;
    text-decoration: none;
}
DIV.indent {margin-left:15} 
           
</STYLE>
</head>
<body onLoad="parseXMLOnLoad();init();" bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" valign="top">

        <jsp:include page="../include/SbHeader.jsp" flush="true" >
            <jsp:param name="name" value="选择税种税目" />
        <jsp:param name="help_url" value="help/wssb/qyzhsb/xzszsmsbzl/xzszsmsbzl-000.htm"/>
        </jsp:include>
    </td>
  </tr>
<!-- self-->
<tr>
 <td>
<br>
<html:errors/>
<table width="100%" border="0" align="center" cellspacing="0" bgcolor="#FFFFFF" class="table-98">
          <tr>
            <td class="1-td1" colspan="2">选择税目</td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td width="45%" height="200" valign="top" class="1-td2"> <div align="center">
             <br>
                <table id="Table_Master" width="88%" border="0" cellpadding="0" cellspacing="1" bgcolor="#7E98A5" class="black9" >
<!--税目名称开始-->
                  <tr>
                    <td height="24" colspan="<%=int_cospan%>" bgcolor='#CCD8DF'>
                      <div align="center" class="b-black12"><b>:..::.</b><strong>税目名称</strong><b>::...</b>&nbsp;<strong>
                      </strong></div></td>
                  </tr>
<!--税目名称结束-->
<!--常用项目开始-->
                  <tr valign="middle" bgcolor="#E0E8ED">
                    <td height="24px" colspan="<%=int_cospan%>" style="line-height:24px"><span class="b-black10"><font size="2">&nbsp;</font></span><font size="2">□<span class="b-black10"><strong>常用项目</strong></span></font></td>
                  </tr>
<!--常用项目结束-->
<html:form action="szsm.do">
  <html:hidden property="actionType" />
    <html:hidden property="sbsybs" />
                <logic:iterate name="szsmForm" property="favoriteSzsm" id="data">
                  <tr>
                    <td bgcolor='#F4F8F9'>&nbsp;
                      <bean:write name="data" property="szsmmc"/><!--(<bean:write name="data" property="szsmdm"/>)-->
                    </td>
<%
if(can_zcjk)
{
    boolean found = false;
    List filter = form.getToBeFilterdSzsm();
    if (filter != null)
    {
        for (int i=0; i<filter.size(); i++)
        {
            if (((com.ttsoft.bjtax.shenbao.model.domain.Szsm)filter.get(i)).getSzsmdm().
                 equals(((com.ttsoft.bjtax.shenbao.model.domain.Sqsbtmp)data).getSzsmdm()))
            {
                found = true;
            }
        }
    }
    if (found)
    {
%>
                    <td bgcolor='F1DFCF'>
                      <input type='checkbox' name="zcjk" value='<bean:write name="data" property="szsmdm"/>' disabled>
                    </td>
<%
    }
    else
    {
%>
                    <td bgcolor='F1DFCF'>
                      <input type='checkbox' name="zcjk" value='<bean:write name="data" property="szsmdm"/>' checked>
                    </td>
<%
    }
}
if(can_bjqs)
{
%>
                    <td bgcolor='CADBCE'>
                      <input type='checkbox' name="bjqs" value='<bean:write name="data" property="szsmdm"/>'>
                    </td>
<%
}
if(can_sdjj)
{
	boolean found = false;
    List filter = form.getToBeFilterdSzsm();
    if (filter != null)
    {
        for (int i=0; i<filter.size(); i++)
        {
            if (((com.ttsoft.bjtax.shenbao.model.domain.Szsm)filter.get(i)).getSzsmdm().
                 equals(((com.ttsoft.bjtax.shenbao.model.domain.Sqsbtmp)data).getSzsmdm()))
            {
                found = true;
            }
        }
    }
    if (found)
    {
%>
                    <td bgcolor='DFD4DC'>
                      <input type='checkbox' name="sdjj" value='<bean:write name="data" property="szsmdm"/>' disabled>
                    </td>
<%
	}
	else
    {
%>
                    <td bgcolor='DFD4DC'>
                      <input type='checkbox' name="sdjj" value='<bean:write name="data" property="szsmdm"/>' checked>
                    </td>
<%    
	}
 }
%>
                  </tr>
                </logic:iterate>
<!--本期新增开始-->
                  <tr bgcolor="#E0E8ED">
                    <td height="22" colspan="<%=int_cospan%>"><span class="b-black10"><font size="2">&nbsp;</font></span><font size="2">□<strong></strong><span class="b-black10"><strong>本期新增</strong></span></font></td>
                  </tr>
<!--本期新增结束-->
                </table>
<br>
<!-- 图解开始 -->
              <table width="80%" border="0" cellspacing="0" cellpadding="0" class="black9">
                 <tr>
                 <td nowrap> <b><font size="2">图解：税款类型</font></b></td>
<% 
if(wtdwInfor != null && wtdwInfor.size() != 0) 
{%>                 <td>
                  	<input type="radio" name="rd_jk" id="rd_jk1" value="<%=CodeConstant.SKLXDM_ZCJK%>" onmousedown="ChangeSKLX(this.value);"/>正常缴款
              	<!--<input type="radio" name=""rd_jk" value="rd_bjqs" onclick="change()" />补缴欠税-->
               	  	<input type="radio" name="rd_jk" id="rd_jk2" value="<%=CodeConstant.SKLXDM_SDJJ%>" onmousedown="ChangeSKLX(this.value)" />委托代征
               	  </td>
<%
}
%>               	  
                 </tr>
                 <tr>
                  <td><div align="right">
               	  <strong>&nbsp;[</strong>&nbsp; </div></td>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="black9">
                      <tr valign="middle">
<%
if(can_zcjk)
{
%>
                        <td>
                          <div align="center">
                          
                            <table width="18" height="8" border="0" cellpadding="0" cellspacing="1" bgcolor="#7E98A5" class="black9" >
                              <tr bgcolor='F1DFCF'>
                                <td nowrap bgcolor="F8DEC6"></td>
                              </tr>
                            </table>
                            
                            
                          </div></td>
                        <td nowrap>
                          <div align="left"><%=zcjk_sklx.getSklxmc()%>&nbsp;</div></td>
<%
}
if(can_bjqs)
{
%>
                        <td nowrap>
                          <div align="center">
                            <table width="18" height="8" border="0" cellpadding="0" cellspacing="1" bgcolor="#7E98A5" class="black9" >
                              <tr bgcolor='CADBCE'>
                                <td bgcolor="C6E0CD"></td>
                              </tr>
                            </table>
                          </div></td>
                        <td nowrap>
                          <div align="left"><%=bjqs_sklx.getSklxmc()%>&nbsp;</div></td>
<%
}
if(can_sdjj)
{
%>
                        <td nowrap>
                          <div align="center">
                            <table width="18" height="8" border="0" cellpadding="0" cellspacing="1" bgcolor="#7E98A5" class="black9" >
                              <tr bgcolor='DFD4DC'>
                                <td bgcolor="E5D7E1"> </td>
                              </tr>
                            </table>
                          </div></td>
                        <td nowrap>
                          <div align="left">委托代征</div></td>
<%
}
%>
                      </tr>
                    </table></td>
                  <td><strong>]</strong></td>
                </tr>
              </table>
<!-- 图解结束 -->
                  <br>
					<img src="<%=static_contextpath%>images/jixu1.jpg" onMouseOver="this.src='<%=static_contextpath%>images/jixu2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/jixu1.jpg'" alt="继续" onClick="nextStep();" style="cursor:hand">
					&nbsp;&nbsp;
					<img src="<%=static_contextpath%>images/tuichu1.jpg" onMouseOver="this.src='<%=static_contextpath%>images/tuichu2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/tuichu1.jpg'" alt="退出" onClick="doReturn();" style="cursor:hand">
<br><br>

<a href="favorite.do?identifyCode=<%=FriendHelper.getUserData(request).yhid+new Date().getTime()%>">
		<img src="<%=static_contextpath%>images/b-szcysm1.jpg" onMouseOver="this.src='<%=static_contextpath%>images/b-szcysm2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/b-szcysm1.jpg'" alt="设置常用税目项目" style="cursor:hand"  border="0" >
		</a>

		&nbsp;&nbsp;<a href="listJks.dc?actionType=Show&identifyCode=<%=FriendHelper.getUserData(request).yhid+new Date().getTime()%>">
		<img src="<%=static_contextpath%>images/b-ckbqjks1.jpg" onMouseOver="this.src='<%=static_contextpath%>images/b-ckbqjks2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/b-ckbqjks1.jpg'" alt="维护缴款书" style="cursor:hand"  border="0" >
		</a>

              </div>
            </td>
            <td rowspan="2" width="55%" valign="top" align="center" class="1-td2"><br>
				<div  align="left" id="szsmTree"></div>
			</td>
        </tr>
        <tr>
            <td class="1-td2" valign="top">
<div align="left">
<br>
<div align="center" class="b-black12"><strong>申报明细资料录入</strong></div>
              <shenbao:sbzl   frompage="ReturnYs" />
</div>
            </td>
        </tr>
</html:form>
</table>
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">

              <tr>

                <td width="40%"> <hr width="100%" size="1" class="hr1" >

                </td>

                <td width="20%" align="center" class="black9"><strong><font color="#0000FF">注意事项</font></strong></td>

                <td width="40%"> <hr width="100%" size="1" class="hr1" >

                </td>

              </tr>

            </table>

            <table width="98%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">

              <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">

                <td>&nbsp;&nbsp;&nbsp;&nbsp;1.如果您申报营业税同时有附加税的情况，请在选择税目的时候只选择营业税的税目即可，附加税会自动带出；如果纳税户只申报附加税，则需要在选择税目中选择要申报的税目。<br>
                    &nbsp;&nbsp;&nbsp;&nbsp;2.如果您在本页面显示的可选择的税目中找不到你要申报的税目，可能是这个税目已经不在征期内或者您已经进行了无应纳税款的申报，您需要到税务机关进行申报。
              </tr>
            </table>
<!--self end-->
<br>
    </td>
  </tr>
<tr>
  <td valign="bottom" align="center">
    <%@ include file="../include/bottom.jsp" %>
  </td>
</tr>
 </table>
</body>
</html>
