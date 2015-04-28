<%@ page contentType="text/html; charset=gb2312" language="java"   errorPage="" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.code.CodeManager" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.constant.CodeConstants" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.util.SfHashList"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<jsp:useBean type="com.ttsoft.bjtax.smsb.sbzl.grsds.web.CzzsjdForm" scope="request" id="czzsjdForm"/><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>查帐征收个人独资企业和合伙企业投资者个人所得税季度申报表</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/function.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/Bolan.js"></script>
<script language=JavaScript src="../js/MathString.js"></script>
<script language=JavaScript src="../js/Stack.js"></script>
<script language=JavaScript src="../js/reader.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  onLoad="showhj()">
<%@ include file="include/header.jsp" %>
<html:form action="/webapp/smsb/czzsjdAction.do" method="post">
<html:hidden property="actionType"/>
<html:hidden property="swdjzh"/>
<html:hidden property="swjgzzjgdm"/>
<html:hidden property="lrr"/>
<html:hidden property="fsdm"/>
<html:hidden property="cjrq"/>
<html:hidden property="jd"/>
<html:hidden property="nsrmc"/>
<html:hidden property="nd"/>
<html:hidden property="lrrq"/>
<html:hidden property="iszhsb"/>
<html:hidden property="qxdm"/>



<TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9' onkeydown="jsjdmQuery()">
 <tr>
    <td valign="top"> <br>
      <table align="center" cellspacing="0" class="table-99">

              <tr>
                <td class="1-td1">北京市查帐征收个人独资企业和合伙企业投资者个人所得税季度申报表</td>
              </tr>
              <tr>
                <td class="1-td2"> <br> <table cellspacing="0" class="table-99">
                    <tr class="black9">
                       <td>
                       申报日期<html:text property="sbrq" size="11" maxlength="8" onchange="getTotalDate(this,document.forms[0].skssksrq,document.forms[0].skssjsrq,1)"/>
                       </td>
                       <td align="left" nowrap>税款所属日期：
                       <!-- <html:text property="skssksrq" size="11" maxlength="8" onchange="compareDate('skssksrq','skssjsrq',0,this)"/>至
                        <html:text property="skssjsrq" size="11" maxlength="8" onchange="compareDate('skssksrq','skssjsrq',0,this)"/>-->
                       <html:text property="skssksrq" size="11" maxlength="8" onchange="compareSkssrqDate('sbrq','skssksrq','skssjsrq',0,this,1,0)"/>至
                       <html:text property="skssjsrq" size="11" maxlength="8" onchange="compareSkssrqDate('sbrq','skssksrq','skssjsrq',0,this,1,1)"/>
                        </td>
                      <td align="center" nowrap> <div align="right">金额单位：元</div></td>
                    </tr>
                  </table>
                  <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr class="black9">
                      <td nowrap class="2-td2-t-left"><div align="right">税务计算机代码&nbsp;&nbsp;</div></td>
                      <td nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;
                         <html:text property="jsjdm" size="8" maxlength="8" onchange="chkCompute(this);return false;" />
                        </div></td>
                      <td nowrap class="2-td2-t-left"><div align="right">单位名称（公章）&nbsp;&nbsp;</div></td>
                      <td nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<ttsoft:write name="czzsjdForm" property="nsrmc"  scope="request" /></div></td>
                      <td nowrap class="2-td2-t-left"><div align="right">利润总额&nbsp;&nbsp;</div></td>
                      <td class="2-td2-t-center"><div align="left">&nbsp;&nbsp;
                        <html:text  property="lrze" maxlength="20" size="20" onchange="lrzeChange(this)" styleclass="inputalignright"/>
                        </div></td>
                    </tr>
                  </table> <br>
                  <table width="100%" border="0" cellpadding="0" cellspacing="0" id="CZZSJBTZZSJ" >
                  <ttsoft:smsbtable form="czzsjdForm" property="dataList"  tableId="CZZSJBTZZSJ" hasTitle="true"/>
				  <DIV id=divSfTemp></DIV>
				  </br>
                    <tr>
                      <td nowrap class="2-td2-left"  colspan="7">合计</td>
                      <td nowrap class="2-td2-center"><input type="text" name="hj123456789" class="inbright"  readOnly tabIndex="-1"/></td>
                    </tr>
                  </table> <table cellspacing="0" class="table-99">
                    <tr class="black9">
                      <td align="center" nowrap> <div align="left">

					  </div></td>
                      <td align="center" nowrap> <div>
                          <div align="right">收款单位（人）签章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
                        </div></td>
                    </tr>
                  </table> <br>
                 <input type="image" accesskey="q" tabIndex="-1" onClick="doQuery();return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/chaxun2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="查询" src="<%=static_contextpath%>images/chaxun1.jpg" width="79" height="22" id="chaxun" style="cursor:hand">
                  &nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="s" tabIndex="-1"  onClick="doSave(); return false;" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/bc-s1.jpg" alt="保存"  width="79" height="22" id="baocun" style="cursor:hand" >
                   &nbsp;&nbsp;&nbsp;&nbsp;<input type="image" accesskey="d" tabIndex="-1"  onClick=" doDelete();return false;"onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/sc-d2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="删除" src="<%=static_contextpath%>images/sc-d1.jpg" width="79" height="22" id="shanchu" style="cursor:hand">
                  &nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="c" tabIndex="-1"  onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor:hand">
                  <br>
                  <br> </td>
              </tr>
            </table>
      <br>
<%@ include file="include/footer.jsp" %>

    </td>
 </tr>
</table>

</html:form>

<script language="JavaScript">

function showhj(){
 //if(document.forms[0].actionType.value=="Show")
	document.forms[0].jsjdm.focus();

  if(document.forms[0].actionType.value=="Query")
  {  computeSameSum("sjyjse","hj123456789");
     document.forms[0].jsjdm.focus();
  }
}

var slnum = 0;
var szsmArray = new Array();
function szsm(sl, ynszzs,ynsqss,sskcs){
 this.sl = sl;
 this.ynszzs = ynszzs;
 this.ynsqss=ynsqss;
 this.sskcs=sskcs;
}

<%
SfHashList szsmlist=CodeManager.getCodeList("CZZS_SZSM",CodeConstants.CODE_MAP_MAPLIST);
List arrayForm = szsmlist.getRecords();
for(int i=0; i < arrayForm.size() ;i++){
  HashMap map = (HashMap)arrayForm.get(i);
%>
  szsmArray[<%=i %>] = new szsm(<%=map.get("sl") %>, <%=map.get("ynszzs")%> ,<%=map.get("ynsqss")%>,<%=map.get("sskcs")%>);
  slnum=<%=i%>;
<%
}
%>

function jsjdmQuery(){

	if(event.keyCode==13) event.keyCode = 9;

}


function doSave()
{
         var jsjdm;
	jsjdm = document.forms[0].jsjdm.value;
	if(jsjdm==null || jsjdm==""){
        alert("计算机代码不允许是空！");
		return false;
        //document.forms[0].jsjdm.focus();
    }
	if(document.forms[0].qcwjsdse&&(jsjdm!=null||jsjdm!="")){
	 doSubmitForm("Save");
        return false;
    }else{
		alert("你没权限操作,界面信息填写不正确");
	}

}

function doQuery()
{
     var jsjdm;
	jsjdm = document.forms[0].jsjdm.value;
	if(jsjdm==null || jsjdm==""){
        alert("计算机代码不允许是空！");
				return false;

        //document.forms[0].jsjdm.focus();
    }else{
		doSubmitForm("Query");
		return false;
	}


}

function doDelete()
{
        var jsjdm;
	jsjdm = document.forms[0].jsjdm.value;
	if(jsjdm==null || jsjdm==""){
        alert("计算机代码不允许是空！");
		        return false;
        //document.forms[0].jsjdm.focus();
        }
	if(document.forms[0].qcwjsdse&&(jsjdm!=null||jsjdm!="")){
	doSubmitForm("Delete");
        return false;
       }else
       {alert("你没权限操作,界面信息填写不正确");}


}
function doReturn()
{
	document.forms[0].actionType.value = "Return";
	document.forms[0].submit();

}
function chkCompute(a)
{
	var jsjdm;
	jsjdm = document.forms[0].jsjdm.value;
	if(jsjdm==null || jsjdm==""){
        alert("计算机代码不允许是空！");
        //document.forms[0].jsjdm.focus();
		return false;
        }
        doQuery();
		return false;
}




</script>
<script>
function lrzeChange(a){
if(isNum(a,0,null,1,15,2)){
if(document.forms[0].qcwjsdse){
	 var jd = parseFloat(document.forms[0].jd.value);
	 if(document.forms[0].qcwjsdse.length == null){
                        var tmp ;



                	document.forms[0].ynssde.value = Math.round(document.forms[0].lrze.value * document.forms[0].fpbl.value)/100;
                        tmp = document.forms[0].ynssde.value;
			var k = 0;
			for(k=0;k<=slnum;k++){

				if(tmp>=szsmArray[k].ynsqss && tmp<szsmArray[k].ynszzs){
                                        document.forms[0].sysl.value=szsmArray[k].sl;
					document.forms[0].sskcs.value=szsmArray[k].sskcs;
				}
                                if(tmp>=szsmArray[slnum].ynsqss)
                               {
                                    document.forms[0].sysl.value=szsmArray[slnum].sl;
                                    document.forms[0].sskcs.value=szsmArray[slnum].sskcs;
                               }

			}

			if((round(tmp*document.forms[0].sysl.value)-round(document.forms[0].sskcs.value))>0)
                        {document.forms[0].ynsdse.value=round(document.forms[0].ynssde.value*document.forms[0].sysl.value-document.forms[0].sskcs.value);}
                        else
                        { document.forms[0].ynsdse.value=0;}
                        if(( document.forms[0].sjyjse.value=round(document.forms[0].ynsdse.value)+round(document.forms[0].qcwjsdse.value)-round(document.forms[0].yjnsdse.value))<0)
                       { document.forms[0].sjyjse.value=0; }
                       else
                        {document.forms[0].sjyjse.value=round(document.forms[0].ynsdse.value)+round(document.forms[0].qcwjsdse.value)-round(document.forms[0].yjnsdse.value);}


                computeSameSum("sjyjse","hj123456789");

	 }else{
         	var tmp ;

		for(i = 0; i < document.forms[0].ynssde.length; i++){

                	document.forms[0].ynssde[i].value = Math.round(document.forms[0].lrze.value * document.forms[0].fpbl[i].value)/100;
                        tmp = document.forms[0].ynssde[i].value;
			var k = 0;
			for(k=0;k<=slnum;k++){

				if(tmp>=szsmArray[k].ynsqss && tmp<szsmArray[k].ynszzs){
                                        document.forms[0].sysl[i].value=szsmArray[k].sl;
					document.forms[0].sskcs[i].value=szsmArray[k].sskcs;
				}
                                if(tmp>=szsmArray[slnum].ynsqss)
                               {
                                    document.forms[0].sysl[i].value=szsmArray[slnum].sl;
                                    document.forms[0].sskcs[i].value=szsmArray[slnum].sskcs;
                               }

			}

			if((round(tmp*document.forms[0].sysl[i].value)-round(document.forms[0].sskcs[i].value))>0)
                        {document.forms[0].ynsdse[i].value=round(document.forms[0].ynssde[i].value*document.forms[0].sysl[i].value-document.forms[0].sskcs[i].value);}
                        else
                        { document.forms[0].ynsdse[i].value=0;}
                        if(( document.forms[0].sjyjse[i].value=round(document.forms[0].ynsdse[i].value)+round(document.forms[0].qcwjsdse[i].value)-round(document.forms[0].yjnsdse[i].value))<0)
                       { document.forms[0].sjyjse[i].value=0; }
                       else
                        {document.forms[0].sjyjse[i].value=round(document.forms[0].ynsdse[i].value)+round(document.forms[0].qcwjsdse[i].value)-round(document.forms[0].yjnsdse[i].value);}

		}
                computeSameSum("sjyjse","hj123456789");
	}
}
else{
  alert("你没权限操作");
  document.forms[0].lrze.value="";
 }
}
}
mathArray[0] = new MathString("sjyjse=ynsdse+qcwjsdse-yjnsdse");
function zhengshu(obj){
	var oRow = getObjRow(obj);
	if(oRow.all("sjyjse").value < 0)
		oRow.all("sjyjse").value = 0;
}
</script>
</td>
</tr>
</table>
</body>
</html>

