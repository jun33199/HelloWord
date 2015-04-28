<%@ page import="java.util.*" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.code.CodeManager" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.constant.CodeConstants" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.util.SfHashList"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>

<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>减免税申报表</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/DTable.js"></script>
<script language=JavaScript src="../js/zhsb.js"></script>
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/function.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/Bolan.js"></script>
<script language=JavaScript src="../js/MathString.js"></script>
<script language=JavaScript src="../js/Stack.js"></script>
<script language=JavaScript src="../js/InputSelect.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
<%@ include file="include/header.jsp" %>
<html:form action="/webapp/smsb/jmssbAction.do" method="POST">
<html:hidden property="actionType"/>
<html:hidden property="swjgzzjgdm"/>
<html:hidden property="fsdm"/>
<html:hidden property="cjrq"/>
<html:hidden property="lrr"/>
<html:hidden property="lrrq"/>
<html:hidden property="jzbz"/>
<html:hidden property="nd"/>
<html:hidden property="iszhsb"/>
<html:hidden property="qxdm"/>

<INPUT TYPE="hidden" NAME="szsmdm_focus">
<INPUT TYPE="hidden" NAME="jmxmjdm_focus">
<TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9' onkeydown="jsjdmQuery()">
<tr>
<td valign="top"> <br>
    <table align="center" cellspacing="0" class="table-99">
    <tr>
       <td class="1-td1">北京市地方税务局减免税申报表</td>
    </tr>
    <tr>
       <td class="1-td2"> <br>

       <table cellspacing="0" class="table-99">
       <tr class="black9">
            <td nowrap align="left">申报日期：
               <html:text property="sbrq" maxlength="8" size="12"/>
            </td>
            <td align="center" nowrap>
                <div align="right">金额单位：元</div>
            </td>
       </tr>
       </table>

       <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
        <tr class="black9">
         <td nowrap class="2-td2-t-left"><div align="right">税务计算机代码&nbsp;&nbsp;</div></td>
         <td nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;
                    <html:text property="jsjdm" size="8" maxlength="8" onchange="doQuery(); return false;" />
                  </div></td>
         <td nowrap class="2-td2-t-left"><div align="right">单位名称（公章）&nbsp;&nbsp;</div></td>
         <td nowrap class="2-td2-t-center"><div align="left">&nbsp;&nbsp;<ttsoft:write name="jmssbForm" property="nsrmc"/></div></td>
        </tr>
        <tr>
        	<td nowrap class="2-td2-t-center" colspan="4">
        	当期销售（营业）额<font color="red">*</font>：<html:text property="dqxse" size="17" maxlength="18" onkeydown="if(event.keyCode==13)return false;" styleClass="inputalignright"></html:text>元；当期利润总额<font color="red">*</font>：<html:text property="dqlrze" size="17" onkeydown="if(event.keyCode==13)return false;" styleClass="inputalignright"></html:text>元；企业人数<font color="red">*</font>： <html:text property="qyrs" size="17" onkeydown="if(event.keyCode==13)return false;" styleClass="inputalignright"></html:text>人 &nbsp;&nbsp;其中：安置相关人员<font color="red">*</font>：  <html:text property="azrs" size="17" maxlength="18" onkeydown="if(event.keyCode==13)return false;"  styleClass="inputalignright"></html:text>人。
        	</td>
        </tr>
       </table>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99" id="JM"  onkeydown='return dokeydown(this.id);'  onmouseup='return dokeyUp(this.id)'>
	<ttsoft:smsbtable form="jmssbForm" property="dataList" tableId="JM" hasTitle="true"/><DIV id=divSfTemp></DIV>

        <tr>
        	<td nowrap class="2-td2-left" colspan="11">合计</td>
	        <td nowrap class="2-td2-center" align="center">
			<input type="text" name="hj" class="inbright "  readOnly tabIndex="-1"/>
		</td>
        </tr>
       </table>

 	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
        <tr>
          	<td nowrap  width="10%" class="2-td2-left">注意</td>
          	<td nowrap  width="90%" class="2-td2-center"><div  align="left">&nbsp;
			<font color="red"><ttsoft:write name="jmssbForm" property="warnStr" />
			<html:hidden property="nsrmc"/></font></div>
		</td>
        </tr>
  	</table><br>


	<div align="center">
         <input type="image" accesskey="a" tabIndex="-1" onclick="javascript:if(document.forms[0].jsjdm.value=='' || document.forms[0].nsrmc.value==''){ return false;}else{addRow('JM'); return false;}" onMouseOver="MM_swapImage('tianjia','','<%=static_contextpath%>images/zj-a2.jpg',1)" onMouseOut="MM_swapImgRestore()"  src="<%=static_contextpath%>images/zj-a1.jpg" width="79" height="22" id="tianjia" alt="增加" style="cursor:hand"/>
              &nbsp;&nbsp;&nbsp;&nbsp;  <input type="image" accesskey="s" tabIndex="-1"  onClick="doSave(); return false;" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/bc-s1.jpg" alt="保存"  width="79" height="22" id="baocun" style="cursor:hand" >
                  &nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="d" tabIndex="-1" onClick="javascript:deleteRow('JM',null); return false;"  onMouseOver="MM_swapImage('sc2','','<%=static_contextpath%>images/sc-d2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="删除" id="sc2" src="<%=static_contextpath%>images/sc-d1.jpg" width="79" height="22">
                   &nbsp;&nbsp;&nbsp;&nbsp;<input type="image" accesskey="x" tabIndex="-1"  onClick="doDelete(); return false;"onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg ',1)" onMouseOut="MM_swapImgRestore()" alt="清除" src="<%=static_contextpath%>images/qbsc-x1.jpg" width="79" height="22" id="shanchu" style="cursor:hand">
                  &nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="c" tabIndex="-1" onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor:hand">
        </div><br>

      	</td>
      </tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<hr width="100%" size="1"></td>
						<td width="99" align="center" class="black9"><strong><font
								color="#0000FF">注 意 事 项</font> </strong></td>
						<td>
							<hr width="100%" size="1"></td>
					</tr>
				</table>
				<table width="100%" border="1" align="center" cellpadding="1"
					cellspacing="1" bordercolor="#FFFFFF" class="black9">
					<tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
						<td>&nbsp;&nbsp;<font color="red">1.“安置相关人员”填写要求：此处填写安置退役士兵、随军家属、军队转业干部、支持和促进就业、残疾人、员工制家政服务员的人数。按现行税收政策规定，如企业适用多种税收优惠政策，可以选择最优惠政策，但不能重复享受（或累加执行）。凡遇此情况的企业请按照所选择的优惠政策对应填写安置人数；</font> <br>
						    &nbsp;&nbsp;<font color="red">2.当期销售额项填写本月实现销售收入，当期利润总额项填写本年累计利润额。</font> <br></td>
					</tr>
				</table></td>
		</tr>
      </table>
			<div id="szsmdm_epodDateLayer" style="position: absolute; width: 142; height: 166; z-index: 9998; display: none" onclick="this.style.display='none'"><select id=sel size="11" onclick='selectClick("szsmdm","JM")' onkeyup='selectMove("szsmdm","JM")' onfocusout='selectClick("szsmdm","JM")'></select></div>
			<div id="jmxmjdm_epodDateLayer" style="position: absolute; width: 142; height: 166; z-index: 9998; display: none" onclick="this.style.display='none'"><select id=sel size="11" onclick='selectClick1("jmxmjdm","JM")' onkeyup='selectMove("jmxmjdm","JM")' onfocusout='selectClick1("jmxmjdm","JM")'></select></div>
			<div id="jmxmjdm_showText" style="position: absolute; width: 200; height: 100; z-index: 9998; display: none" onclick="this.style.display='none'" ></div>
			</html:form>
      <br>
<%@ include file="include/footer.jsp" %>
    </td>
 </tr>
</table>
<script language="JavaScript">

function fnOnLoad()

{

    document.forms[0].jsjdm.focus();
}


hjArray[0]=new Array("jmse","hj","JM");

computeSameSum("jmse","hj");

<ttsoft:write name="jmssbForm" property="scriptStr" filter="false"/>
var JM_list=new DTable(JM,jsArr_JM);
JM_list.tableTail=1;
function doSubmit(method){
	document.all.actionType.value=method;
	document.all.jmssbForm.submit();
	return false;
}

function jsjdmQuery(){
      if(event.keyCode==13) event.keyCode = 9;
}

function panduan(a){
var oRow = getObjRow(a);
if(oRow.all("szsmdm").value==null||oRow.all("szsmdm").value==""){
alert("请先输入减免税项目代码");
oRow.all("kssl").value="";
}
}
function chkjsjdm()
{
	var jsjdm;
	jsjdm = document.forms[0].jsjdm.value;
	if(jsjdm==null || jsjdm==""){
        alert("计算机代码不允许是空！");
        return false;
        }
        return true;
}

function setpanduan(a){

var oRow = getObjRow(a);

var kssl = oRow.all("kssl");
var szsmdms= oRow.all("szsmdm");
for(k=0;k<=slnum;k++){
if(szsmdms.value==szsmArray[k].szsmdm)
{
   if(szsmArray[k].asljbs==1 || szsmArray[k].asljbs==2 || szsmArray[k].asljbs==4 || szsmArray[k].asljbs==5)
   { kssl.disabled=false; }
   else
   { kssl.disabled=true;}
}

}

}


function doSave()
{
   var ok=1;
   var jsjdm;
   var nsrmc;
   var skssksrq;
   var skssjsrq;
   var jsje;
   var jmse;
   var szsmdm;
   var k;
   var kssl;
   //add by tangchangfu 无税减免税项目 2014-05-04
   var jmxmjdm;
   var jmxmksrq;
   var jmxmjsrq;//add end
   var pks = new Array();
   
	jsjdm = document.forms[0].jsjdm.value;

        nsrmc = document.forms[0].nsrmc.value;
        
        //add by tangchangfu 2014-05-04 无税减免税项目 
        if(!checkData()){
        	return false;
        }//add end

	if((jsjdm==null || jsjdm=="")||(nsrmc==null || nsrmc=="")){
        alert("页面信息填写不正确！");
        //document.forms[0].jsjdm.focus();
        }
        else{
        if(document.forms[0].szsmdm){

        if(document.forms[0].szsmdm.length){
        
          for(i = 0; i < document.forms[0].szsmdm.length; i++){
        	 //验证减免分类、减免税种、减免项目不能完全相同 
        	 var pk = document.forms[0].jmlx[i].value
        	    + "-" + document.forms[0].szsmdm[i].value
        	    + "-" + document.forms[0].jmxmjdm[i].value;
        	 for(var jj=0; jj<pks.length; jj++) {
        		  if(pk == pks[jj]) {
        			  alert("第" + eval(i+1) + "行数据重复了，减免分类、减免税种（税目）代码、减免项目及代码不能完全相同！");
        			  return false;
        		  }
        	  }
        	  pks[i]  = pk;
        	  
          skssksrq = document.forms[0].skssksrq[i].value;
          skssjsrq = document.forms[0].skssjsrq[i].value;
          jsje= document.forms[0].jsje[i].value;
          jmse= document.forms[0].jmse[i].value;
          szsmdm=document.forms[0].szsmdm[i].value;
          kssl=document.forms[0].kssl[i].value;
        //add by tangchangfu 无税减免税项目 2014-05-04
          jmxmjdm = document.forms[0].jmxmjdm[i].value;
          jmxmksrq = document.forms[0].jmxmksrq[i].value;
          jmxmjsrq = document.forms[0].jmxmjsrq[i].value;//add end
          
           if(szsmdm==""||szsmdm==null)
          {  k=i+1;
           alert("减免税的第"+k+"行的减免税种税目没有填写，请填写");
           ok=0;
           return false; //add by tangchangfu 无税减免税项目 2014-05-04 为了避免提示一片信息
          }
          if(skssksrq==""||skssksrq==null)
          { k=i+1;
           alert("减免税的第"+k+"行的减免开始日期没有填写，请填写");
           ok=0;
           return false; //add by tangchangfu 无税减免税项目 2014-05-04 为了避免提示一片信息
          }
            if(skssjsrq==""||skssjsrq==null)
          {  k=i+1;
           alert("减免税的第"+k+"行的减免结束日期没有填写，请填写");
           ok=0;
           return false; //add by tangchangfu 无税减免税项目 2014-05-04 为了避免提示一片信息
          }
             if(skssjsrq<skssksrq)
          {  k=i+1;
           alert("减免税的第"+k+"行的减免结束日期小于减免开始日期，请重新填写");
           ok=0;
           return false; //add by tangchangfu 无税减免税项目 2014-05-04 为了避免提示一片信息
          }
           if(jsje==""||jsje==null)
          { k=i+1;
           alert("减免税的第"+k+"行的计税金额没有填写，请填写");
           ok=0;
           return false; //add by tangchangfu 无税减免税项目 2014-05-04 为了避免提示一片信息
          }
            if(jmse==""||jmse==null)
          {  k=i+1;
           alert("减免税的第"+k+"行的减免税额没有填写，请填写");
           ok=0;
           return false; //add by tangchangfu 无税减免税项目 2014-05-04 为了避免提示一片信息
          }
           if(kssl==""||kssl==null)
          {
             k=i+1;
             if(document.forms[0].kssl[i].className=="inputalignright"){
             var truth = window.confirm("减免税的第"+k+"行的减免课税数量没有填写，你是否要求添入默认值0");
             if(truth)	{document.forms[0].kssl[i].value="0";}
             else{
              alert("请您填写相应的课税数量");
              document.forms[0].kssl[i].value="";
              ok=0;
              return false; //add by tangchangfu 无税减免税项目 2014-05-04 为了避免提示一片信息
             }
             }
          }
         //add by tangchangfu 无税减免税项目 2014-05-04
   			if(jmxmjdm == "" || jmxmjdm == null){
   				k=i+1;
   	           alert("减免税的第"+k+"行的减免项目及代码没有填写，请填写!\n如果没有对应的下拉选项可以选择，则表示选择了没有减免优惠的税种税目，请删除该行再保存！");
   	           ok=0;
   	        return false; //add by tangchangfu 无税减免税项目 2014-05-04 为了避免提示一片信息
   			}else{
   				//add by tangchangfu 2014-06-24 无税减免税项目二期
  				if(checkJmxmjdm(szsmdm,jmxmjdm) == false){
  					k=i+1;
  					alert("减免税的第"+k+"行的减免项目及代码选择不正确,请重新选择！");
  					ok=0;
  					 return false; //add by tangchangfu 2014-06-24 无税减免税项目二期
  				}
  			}
   
   			if(jmxmksrq == "" || jmxmksrq == null){
   				k=i+1;
    	        alert("减免税的第"+k+"行的减免项目开始日期没有填写，请填写!");
    	        ok=0;
    	        return false; //add by tangchangfu 无税减免税项目 2014-05-04 为了避免提示一片信息
   			}
/*    			if(jmxmjsrq == "" || jmxmjsrq == null){
   				k=i+1;
    	        alert("减免税的第"+k+"行的减免项目结束日期没有填写，请填写!");
    	        ok=0;
    	        return false; //add by tangchangfu 无税减免税项目 2014-05-04 为了避免提示一片信息
   			} *///add end
           
        }
       }
        else
        {

          skssksrq = document.forms[0].skssksrq.value;
          skssjsrq = document.forms[0].skssjsrq.value;
          jsje= document.forms[0].jsje.value;
          jmse= document.forms[0].jmse.value;
          szsmdm=document.forms[0].szsmdm.value;
          kssl=document.forms[0].kssl.value;
          
          //add by tangchangfu 无税减免税项目 2014-05-04
          jmxmjdm = document.forms[0].jmxmjdm.value;
          jmxmksrq = document.forms[0].jmxmksrq.value;
          jmxmjsrq = document.forms[0].jmxmjsrq.value;//add end
          
           if(szsmdm==""||szsmdm==null)
          {
           alert("减免税的减免税种税目没有填写，请填写");
           ok=0;
           return false; //add by tangchangfu 无税减免税项目 2014-05-04 为了避免提示一片信息
          }
          if(skssksrq==""||skssksrq==null)
          {alert("减免税的减免开始日期没有填写，请填写");
           ok=0;
           return false; //add by tangchangfu 无税减免税项目 2014-05-04 为了避免提示一片信息
          }
          if(skssjsrq==""||skssjsrq==null)
          {alert("减免税的减免结束日期没有填写，请填写");
           ok=0;
           return false; //add by tangchangfu 无税减免税项目 2014-05-04 为了避免提示一片信息
          }
          if(skssjsrq<skssksrq)
          {
           alert("减免税的减免结束日期小于减免开始日期，请重新填写");
           ok=0;
           return false; //add by tangchangfu 无税减免税项目 2014-05-04 为了避免提示一片信息
          }
          if(jsje==""||jsje==null)
          {alert("减免税的计税金额没有填写，请填写");
           ok=0;
           return false; //add by tangchangfu 无税减免税项目 2014-05-04 为了避免提示一片信息
          }
          if(jmse==""||jmse==null)
          {alert("减免税的减免税额没有填写，请填写");
           ok=0;
           return false; //add by tangchangfu 无税减免税项目 2014-05-04 为了避免提示一片信息
          }
           if(kssl==""||kssl==null)
          {
             k=i+1;
            if(document.forms[0].kssl.className=="inputalignright"){
             var truth = window.confirm("减免税的减免课税数量没有填写，你是否要求添入默认值0");
             if(truth)	{document.forms[0].kssl.value="0";}
             else{
                 alert("请您填写相应的课税数量");
                 document.forms[0].kssl.value="";
                 ok=0;
                 return false; //add by tangchangfu 无税减免税项目 2014-05-04 为了避免提示一片信息
             }
            }
          }
           //add by tangchangfu 无税减免税项目 2014-05-04
  			if(jmxmjdm == "" || jmxmjdm == null){
  	           alert("减免项目及代码没有填写，请填写!\n如果没有对应的下拉选项可以选择，则表示选择了没有减免优惠的税种税目，请删除该行再保存！");
  	           ok=0;
  	         return false; //add by tangchangfu 无税减免税项目 2014-05-04 为了避免提示一片信息
  			}else{
  				if(checkJmxmjdm(szsmdm,jmxmjdm) == false){//add by tangchangfu 2014-06-24 无税减免税项目二期
  					alert("减免项目及代码选择不正确,请重新选择！");
  					ok=0;
  		  	        return false; //add by tangchangfu 2014-06-24 无税减免税项目二期
  				}
  			}
  
  			if(jmxmksrq == "" || jmxmksrq == null){
   	        alert("减免项目开始日期没有填写，请填写!");
   	        ok=0;
   	     return false; //add by tangchangfu 无税减免税项目 2014-05-04 为了避免提示一片信息
  			}
/*   			if(jmxmjsrq == "" || jmxmjsrq == null){
   	        alert("减免项目结束日期没有填写，请填写!");
   	        ok=0;
   	     return false; //add by tangchangfu 无税减免税项目 2014-05-04 为了避免提示一片信息
  			} *///add end
           
         }
        }
        if(ok==1)
	{doSubmitForm("Save");
         return false;
        }
        else
        {alert("内容填写不完全，请填写");}
        }
}

function doQuery()
{
        var jsjdm;
	jsjdm = document.forms[0].jsjdm.value;
	if(jsjdm==null || jsjdm==""){
        alert("计算机代码不允许是空！");
        //document.forms[0].jsjdm.focus();
        }
	doSubmitForm("Query");

	return false;
}

/**
 * 检查填写是否正确
 *add by tangchangfu 2014-04-04 无税减免税项目
 */
function checkData(){
	var dqxseObj = document.forms[0].dqxse;//当期销售（营业）额
	var dqlrzeObj = document.forms[0].dqlrze;//当期利润总额
	
	var azrsObj = document.forms[0].azrs;//安置人数
	var qyrsObj = document.forms[0].qyrs;//企业人数
	
	
	if(dqxseObj.value != ""){
		if(!fnIsIntNum1(dqxseObj.value)){
			alert("当期销售（营业）额格式不对，必须大于或等于0，请确认！");
			dqxseObj.focus();
			dqxseObj.select();
			return false;
		}else if(!isNum(dqxseObj,0,null,1,15,2)){
			//alert("");
			dqxseObj.focus();
			dqxseObj.select();
			return false;
		}
		
	}else{
		alert("当期销售（营业）额是必填项，请填写！");
		dqxseObj.focus();
		return false;
		
	}
	
	
	
	if(dqlrzeObj.value != ""){
		if(!isNumber(dqlrzeObj.value)){
			alert("当期利润总额格式不对，必须是数字，请确认！");
			dqlrzeObj.focus();
			dqlrzeObj.select();
			return false;
		}else if(!isNum(dqlrzeObj,null,null,1,15,2)){
			dqlrzeObj.focus();
			dqlrzeObj.select();
			return false;
		}
	}else{
		alert("当期利润总额是必填项，请填写！");
		dqlrzeObj.focus();
		return false;
		
	}
	
	//安置人数 是否为空
	if(azrsObj.value != ""){
		if(!isDigit(azrsObj.value)){
			alert("安置人数格式不对，必须为整数！");
			azrsObj.focus();
			azrsObj.select();
			return false;
		}
	}else{
		alert("安置人数是必填项，请填写！");
		azrsObj.focus();
		return false;
	}
	
	//企业人数是否为空
	if(qyrsObj.value != ""){
		if(!isDigit(qyrsObj.value)){
			alert("企业人数格式不对，必须为整数！");
			qyrsObj.focus();
			qyrsObj.select();
			return false;
		}else{
			if(azrsObj.value !="" && (parseInt(azrsObj.value) > parseInt(qyrsObj.value))){
				alert("安置人数不对，不能大于企业人数！");
				azrsObj.focus();
				azrsObj.select();
				return false;
			}
		}
	}else{
		alert("企业人数为必填项，请填写！");
		qyrsObj.focus();
		return false;
	}
	
	return true;
}

//判断减免项目代码是否正确、并且所选减免代码必须属于选择税目
function checkJmxmjdm(szsmdm,jmxmjdm){
	//如果页面预加载减免项目及代码存在，则执行以下操作
	if(jmxmjdm_name_all && jmxmjdm_value_all){
		var szdm = szsmdm.substr(0,2);//获得税种代码
		var re=new RegExp("^"+szdm,"i");//定义匹配税目的正则表达式
		//如果税种代码不为空，则执行判断操作
		if(szdm){
			for(var index =0; index < jmxmjdm_name_all.length; index ++){
					if(re.test(jmxmjdm_name_all[index])==true){
						if(jmxmjdm_value_all[index] == jmxmjdm){
							return true;
						}
				}
			}
		}
	}
	return false;
}

//parseFloat

function fnIsIntNum1(strNum)
{
 var strCheckNum = strNum + "";
 if(strCheckNum.length < 1)         //空字符串
  return false;
 else if(isNaN( Number(strCheckNum) ))         //不是数值
  return false;
 else if(parseInt( Number(strCheckNum) ) < 0)       //不是正数
  return false;

 return true;
}


function doDelete()
{ var jsjdm;
	jsjdm = document.forms[0].jsjdm.value;
	var nsrmc;
	jsjdm = document.forms[0].jsjdm.value;
        nsrmc = document.forms[0].nsrmc.value;
	if((jsjdm==null || jsjdm=="")||(nsrmc==null || nsrmc=="")){
        alert("页面信息填写不正确！");
        //document.forms[0].jsjdm.focus();
        return false;
        }else{
	doSubmitForm("Delete");
        return false;
        }
}
function compare(name1,name2){

	var obj1=getObjByName(name1);
	var obj2=getObjByName(name2);
	if(obj1  && obj2){
		if(parseFloat(obj1.value)>parseFloat(obj2.value)){
			return 1;
		}else if(parseFloat(obj1.value)==parseFloat(obj2.value)){
			return 0;
		}else{
			return -1;
		}
	}else{
		alert("对象未找到");
	}
}
function getObjByName(name){
	var obj = document.forms[0].all(name);
	if(!obj) return null;
	//if(name=="mx_bs")
	if(obj.length){
		//本节点为select
		if(obj.type=="select-one" || obj.type=="select-multiple") return obj;
		//子节点为select或checkbox
		var visibleNode = document.all(name+"_epx_visible_1");
    if(!visibleNode) return;//未显示对象返回null
		var subNode = visibleNode.childNodes[0];
    if(!subNode) return;
		if(subNode.type=="textarea" || subNode.type=="text" || subNode.type=="hidden" || subNode.type=="select-one" || subNode.type=="select-multiple" || subNode.type=="checkbox") return subNode;
	  //子节点为radio
		var radioObj = getRadioObj(obj);
		if(radioObj!="not radio") return radioObj;
		//other
	}//end if

	return obj;
}

function makeReadOnly2(){
  for(var i=1;i<JM.rows.length-1;i++){
	row =JM.rows[i]//JM_list.doGetRow(i);
    if(row.all("szsmdm")){
	var values = get_subobj("szsmlist",row.all("szsmdm").value);
	var fields = get_obj("szsmlist_fields");
	//setTheRow(fields,values,row);
	setTheRowOld(fields,values,row);
	document.all("jsjdm").focus();
		//setday("szsmdm","JM",row.all("szsmdm"));
	}
  }
}
//表示是否需要带出附加税
//var isadditions = false;
isadditions = true;
//根据数组名称得到数组对象
function get_obj(objname){
  return eval(objname);
}

//根据关键值返回第一个1维数组
function get_subobj(arrayName,key){
	var obj = get_obj(arrayName);
	for(var ii=0;ii<obj.length;ii++){
		if(obj[ii][0]==key) return obj[ii];
  }
  return null;
}

//根据关键值返回2维数组
function get_subobjs(arrayName,key){
  var obj = get_obj(arrayName);
  var tempArray = new Array();
  for(var ii=0;ii<obj.length;ii++){
		if(obj[ii][0]==key) tempArray.push(obj[ii]);
  }
  return tempArray;
}
//设置税款所属日期
function setSkssrqBySzsm(row){
	var szsmdm = row.all("szsmdm").value;
	for(var i=0;i<szsmDate.length;i++){
		var szsmmx = szsmDate[i];
		if(szsmmx[0]==szsmdm){
			row.all("skssksrq").value=szsmmx[1];
			row.all("skssjsrq").value=szsmmx[2];
		}
	}
}
//带出当前行-根据字段对象、值对象、行对象
function setTheRow(fields,values,row){
	if(!(fields && values && row)) return;
	//填写本行各值
	for(var i=0;i<fields.length;i++){
		var obj = row.all(fields[i]);
		if(!obj) continue;
		obj.value=values[i];
	}
	setSkssrqBySzsm(row);
	publicMethod();
	//判断是否是课税金额可填
	var obj = row.all("asljbs");
	if(!obj) return;
	if(obj.value!="1" && obj.value!="2" && obj.value!="4" && obj.value!="5" ){
                row.all("kssl").value="";
		row.all("kssl").readOnly = true;
		row.all("kssl").className = "inputnoborder";
		row.all("kssl").tabIndex=-1;
	}else{
		row.all("kssl").readOnly = false;
		row.all("kssl").className = "inputalignright";
       //         if(row.all("kssl").value=="")
       //         {
       //           alert("课税数量请您填写，如果不输入的话保存时将提示您是否要求设置为0");
       //         }
	}
	row.all.kssl.outerHTML=row.all.kssl.outerHTML;
}

//带出当前行-根据数组名、触发对象
function setRow(arrayName,obj){
		var fields = get_obj(arrayName+"_fields");
		var values = get_subobj(arrayName,obj.value);
		var row = getObjRow(obj);
		setTheRow(fields,values,row);
}

//-------------------------------------------

//带出当前行及附加税(附加税代码不可改,不会触发此方法)
function setAdditions(nameHead,tableid,obj){
	//alert("setAdditions");
	/*****外籍个人用来显示币种代码列表******/
	if(nameHead=="bzdm"){

		var values = get_subobj("bzlist",obj.value);
		var fields = get_obj("bzlist_fields");
		var row = getObjRow(obj);
		//var oldCode = row.all("bzdm_old").value;
		//if(obj.value==oldCode) return;
         //不合法的数据使值不变（或清空：values内全部赋空值，不return，会连带清空所带附加税）
		//if(!values){
			//obj.value=oldCode;
		//	return;
		//}
		setTheRow(fields,values,row);
		//焦点位置标志
		document.all(nameHead+"_focus").value = "0";
		//关闭div
	    document.all(nameHead+"_epodDateLayer").style.display = 'none';

		tempSelect.srcObj.select();
		return;
	}
	/*****外籍个人用来显示币种代码列表******/
	var values = get_subobj("szsmlist",obj.value);
	var fields = get_obj("szsmlist_fields");
	var row = getObjRow(obj);
	//原税目代码

	var oldCode = row.all("szsmdm_old").value;
		//alert(row.rowIndex+" :"+obj.value+" :"+oldCode)
		//值未变时不作任何操作
	if(obj.value==oldCode) return;

	//不合法的数据使值不变（或清空：values内全部赋空值，不return，会连带清空所带附加税）
	if(!values){
		obj.value=oldCode;
		return;
	}

	//设置当前行
	setTheRow(fields,values,row);
	//删除该税目代码
	delSzsmList(nameHead,obj.value)

	//焦点位置标志
	document.all(nameHead+"_focus").value = "0";
	//关闭div
	document.all(nameHead+"_epodDateLayer").style.display = 'none';
	tempSelect.srcObj.select();


	if(!isadditions){
		delSonRow(nameHead,tableid,oldCode);
		return;
	}

	//查找附加税(szsmlist_add：税种税目代码和附加税的对应关系)
	var list = get_subobjs("szsmlist_add",obj.value);//附加税对象
	var addCode;
	for(var ii=0;ii<list.length;ii++){
		var addCode = list[ii][1];//附加税代码
		if(findRowIndex(tableid,addCode)>=0) continue;
		var row = addSonRow(tableid);//新增一行，并返回行对象
		var values = get_subobj("szsmlist",addCode);
		setTheRow(fields,values,row);
		//delSzsmList(addCode)//maybe not needed
	}
	//本行税目代码改变时,判断是否删除之前的附加税
	delSonRow(nameHead,tableid,oldCode);
}

//删除附加税(在删除本行之后调用)
function delSonRow(nameHead,tableid,father){
	if(!father || father==null || father=="") return;
	addSzsmList(nameHead,father);
	if(!isadditions) return;
	var sons = getSons(father);
	for(var ii=0;ii<sons.length;ii++){
		var rowIndex = findRowIndex(tableid,sons[ii]);
		if(rowIndex<0) continue;
		//如果附加税存在，查找其父
		fathers = getFathers(sons[ii]);
		for(var jj=0;jj<fathers.length;jj++){
			if(findRowIndex(tableid,fathers[jj])>=0){
				rowIndex = -1;
				break;
			}
		}
		//所有父不存在时删除该行
		if(rowIndex>=0) delRowByIndex(tableid,rowIndex);
	}
}

//根据税目找附加税(一维数组)
function getSons(father){
	if(!isadditions) return new Array();
  var obj = get_obj("szsmlist_add");
  var tempArray = new Array();
  for(var ii=0;ii<obj.length;ii++){
		if(obj[ii][0]==father) tempArray.push(obj[ii][1]);
  }
  return tempArray;
}

//根据附加税找税目(一维数组)
function getFathers(son){
	if(!isadditions) return new Array();
  var obj = get_obj("szsmlist_add");
  var tempArray = new Array();
  for(var ii=0;ii<obj.length;ii++){
		if(obj[ii][1]==son) tempArray.push(obj[ii][0]);
  }
  return tempArray;
}

//新增一行(附加税),返回行对象
function addSonRow(tableid){
	var index =addRow(tableid);
	return eval(tableid).rows[index];
}

//根据行号删除行
function delRowByIndex(tableid,rowIndex){
	var rows = eval(tableid).rows;
	rows[rowIndex].removeNode(true);
}

//查找税目是否存在,存在时返回行号,不存在时返回-1
function findRowIndex(tableid,code){
	var rows = eval(tableid).rows;
	for(var ii=0;ii<rows.length;ii++){
		var obj = rows[ii].all("szsmdm");
		if(!obj) continue;
		if(obj.value==code) return ii;
	}
	return -1;
}


var valuelist;
var namelist;
var valuelist_del;
var namelist_del;
var valuelist_all;
var namelist_all;

//根据数组前缀找数组
function getArrayObjs(nameHead){
	valuelist = new Array();
	namelist = new Array();
	valuelist_del = eval(nameHead+"_value_del");
	namelist_del = eval(nameHead+"_name_del");
	valuelist_all = eval(nameHead+"_value_all");
	namelist_all = eval(nameHead+"_name_all");
	//数组的复制
	for(var ii=0;ii<valuelist_all.length;ii++){
		valuelist.push(valuelist_all[ii]);
		namelist.push(namelist_all[ii]);
	}
}

//数组对象的赋值
function setArrayObjs(nameHead){
	eval(nameHead+"_value = valuelist;");
	eval(nameHead+"_name = namelist;");
}

//删除一条税目
function delSzsmList(nameHead,key){
	valuelist = eval(nameHead+"_value");
	namelist = eval(nameHead+"_name");
	valuelist_del = eval(nameHead+"_value_del");
	namelist_del = eval(nameHead+"_name_del");

	for(var ii=0;ii<valuelist.length;ii++){
		if(valuelist[ii]==key) {
			valuelist_del.push(valuelist.splice(ii,1));
			namelist_del.push(namelist.splice(ii,1));
			break;
		}
  }
}

//增加一条税目
function addSzsmList(nameHead,key){
	//alert("addSzsmList");
	getArrayObjs(nameHead);
	//删除valuelist_del中的该元素
	for(var ii=0;ii<valuelist_del.length;ii++){
		if(valuelist_del[ii]==key) {
			//alert("key:"+key);
			valuelist_del.splice(ii,1);
			namelist_del.splice(ii,1);
			break;
		}
  }
	//valuelist_del转为字符串
	var delStr = ","+valuelist_del.toString()+",";

	//重设列表
	for(var ii=0;ii<valuelist.length;ii++){
		if(delStr.indexOf(","+valuelist[ii]+",")<0) continue;
		valuelist.splice(ii,1);
		namelist.splice(ii,1);
  }
	setArrayObjs(nameHead);
}

//下拉表点击时提交
function selectClick(nameHead,tableid){
	//使不重复执行
	if(tempSelect.selectObj.style.display=='none') return;
	tempSelect.enterTips(13,tempSelect.srcObj);
	setAdditions(nameHead,tableid,tempSelect.srcObj);
	window.event.returnValue=false;
	//alert(tempSelect.srcObj.outerHTML);
	//tempSelect.srcObj.focus();
	//tempSelect.srcObj.select;
	//alert(tempSelect.srcObj.focus);

}


function selectClick1(nameHead,tableid){
	//使不重复执行
	if(tempSelect.selectObj.style.display=='none') return;
	tempSelect.enterTips(13,tempSelect.srcObj);
	window.event.returnValue=false;
	//alert(tempSelect.srcObj.outerHTML);
	//tempSelect.srcObj.focus();
	//tempSelect.srcObj.select;
	//alert(tempSelect.srcObj.focus);

}


//下拉表移动时判断
function selectMove(nameHead,tableid){
	var keyCode = window.event.keyCode;
	if(keyCode==13){
		selectClick(nameHead,tableid);
	}else if(keyCode==38 || keyCode==40){

		tempSelect.srcObj.value = tempSelect.selectObj.value;//document.all(nameHead+"_epodDateLayer").all.sel.value;
	}
}

//onblur事件触发
function resetRow(nameHead,tableid,obj){
	//如果焦点在select上，直接return
	try{
		if(document.all(nameHead+"_focus").value==nameHead) return;

		//如果税目代码变化，重设值
		var row = getObjRow(obj);
		if(row.all(nameHead).value!=row.all(nameHead+"_old").value ) {
			setAdditions(nameHead,tableid,obj);
		}
		//如果没有select显示，使之关闭
		var s = tempSelect.selectObj.style;
		if(s.display!='none') {
			//s.display='none' ;
			//tempSelect.selectObj.parentElement.style.display='none';
			//modify by huipeijie for do not select by mouse
		}
	}catch(e){

		//alert("resetRow "+e);
	}
}

//onkeydown事件触发
function doEnterDown(nameHead,tableid,obj){

	try{

	if(window.event.keyCode!=13) return;
	if(!tempSelect) return;
	//enter->tab
	window.event.keyCode = 9;
	//执行带出
	tempSelect.enterTips(13,tempSelect.srcObj);
	setAdditions(nameHead,tableid,obj);
	}catch(e){
		alert(e+"doEnterDown");
	}

}

//----Huipj------------------
//onkeyup触发显示或提交
function setday(nameHead,tableid,obj)
{
	if(obj!=srcobj &&　srcobj) return;
	anchor(obj,nameHead);
	tempSelect = new InputSelect(event.srcElement,document.all(nameHead+"_epodDateLayer").all.sel,eval(nameHead+"_name_all"),eval(nameHead+"_value_all"));
	tempSelect.showtips(obj);
	if(window.event.keyCode==40){
		//焦点移向列表
		if(document.all(nameHead+"_epodDateLayer").all.sel.options.length<1) return;
		document.all(nameHead+"_epodDateLayer").all.sel.focus();
		document.all(nameHead+"_epodDateLayer").all.sel.options[0].selected = true;
		obj.value = tempSelect.selectObj.value;
		document.all(nameHead+"_focus").value = nameHead;
	}else if(window.event.keyCode==13){
		//执行带出;
		tempSelect.enterTips(window.event.keyCode,tempSelect.srcObj);
		(nameHead,tableid,obj);
	}
}

//显示div
function anchor(tt,nameHead){

  /*var dads = .style;
  var th = tt;
  var ttop  = tt.offsetTop;
  var thei  = tt.clientHeight;
  var tleft = tt.offsetLeft;
  var ttyp  = tt.type;
  while (tt = tt.offsetParent){ttop+=tt.offsetTop; tleft+=tt.offsetLeft;}

  dads.top  = (ttyp=="image")? ttop+thei : ttop+thei+6;
  dads.left = tleft;
  dads.zindex = '0';
  dads.display = '';*/

	var txtHeight = tt.clientHeight;
	var txtTop = getPosTop(tt);//obj.offsetTop;
	var txtLeft = getPosLeft(tt);
	var oDiv = document.all(nameHead+"_epodDateLayer");
	var parentHeight = document.body.clientHeight;//oParent.clientHeight;

	//oDiv.style.display = '';
	if(txtTop+oDiv.clientHeight > (parentHeight+document.body.scrollTop)){
		oDiv.style.top = txtTop - oDiv.offsetHeight - 5;//10 --> border or margin
	}else{
		oDiv.style.top = txtTop + txtHeight + 5;
	}
	oDiv.style.left = txtLeft - oDiv.clientLeft;
	oDiv.style.zindex = '0';

}
function getPosTop(obj){
	var pos = obj.offsetTop;;
	while(obj.tagName!="BODY"){
		obj = obj.offsetParent;
		pos += obj.offsetTop;
	}
	return pos;
}
function getPosLeft(obj){
	var pos = obj.offsetLeft;;
	while(obj.tagName!="BODY"){
		obj = obj.offsetParent;
		pos += obj.offsetLeft;
	}
	return pos;
}

function setTheRowOld(fields,values,row){
	if(!(fields && values && row)) return;
	//填写本行各值
	for(var i=0;i<fields.length;i++){
		var obj = row.all(fields[i]);
		if(!obj) continue;
		obj.value=values[i];
	}
	//setSkssrqBySzsm(row);
	publicMethod();
	//判断是否是课税金额可填
	var obj = row.all("asljbs");
	if(!obj) return;
	if(obj.value!="1" && obj.value!="2" && obj.value!="4" && obj.value!="5"){
                row.all("kssl").value="";
		row.all("kssl").readOnly = true;
		row.all("kssl").className = "inputnoborder";
		row.all("kssl").tabIndex=-1;
	}else{
		row.all("kssl").readOnly = false;
		row.all("kssl").className = "inputalignright";
                if(row.all("kssl").value=="")
                {
                  alert("课税数量请您填写，如果不输入的话保存时将提示您是否要求设置为0");
                }
	}
	row.all.kssl.outerHTML=row.all.kssl.outerHTML;
}

/**
 * 通过税种税目代码过滤减免税项目及代码下拉，只显示当前税种代码所匹配的下拉，其他的隐藏
 *add by tangchangfu 无税减免税项目 2014-04-18
 */
function filterJmxmjdmBySzsmdm(jmxmjdmObj){
	//如果行对象或传递过来的税种未定义或者不是对象，直接返回
	if(!jmxmjdmObj){
		return false;
	}
	//不允许用BackSpace键做删除操作
	if(event.keyCode == 8 && jmxmjdmObj.value !=""){
		event.returnValue = false;
		return false;
	}
	
	//获取当前行对象 
	var trObj = jmxmjdmObj.parentNode.parentNode.parentNode;
	
	//获得当前行税种税目代码对象（在当前行的第二个单元格）,从0开始计数，即程序中第二格应该写为cells[1]
	var szsmdmObj = trObj.cells[1].childNodes[0].childNodes[0];
	
	//获得税种税目代码对象的值
	var szsmdmObj_value = szsmdmObj.value;
	
	//如果税种税目代码为空，则直接返回 无税减免税项目二期 add by tangchangfu  2014-06-24
	if(szsmdmObj_value ==""){
		return false;
	}
	
	
	//截取税种代码前两位作为过滤条件
	var sub_first_2_szsmdm = szsmdmObj_value.substr(0,2);
	
	
	//获得当前行对应减免项目所有下拉选项
	//执行过滤
	if(jmxmjdmObj.value =="" || jmxmjdmObj.value.length==1||jmxmjdmObj.value.length==2){
		jmxmjdmObj.value = sub_first_2_szsmdm;
	}	
	
	var nameHead ="jmxmjdm";
	//如果点击鼠标
	srcobj = jmxmjdmObj;
  	if(event.keyCode == 0 && jmxmjdmObj.value !=""){
  		//再次选择，获得选择之前的值
  		var old_value = jmxmjdmObj.value;
  		
		jmxmjdmObj.value = sub_first_2_szsmdm;
		setday(nameHead,"JM",jmxmjdmObj);
		jmxmjdmObj.value = old_value;
		
		checkedOldSelected(nameHead,old_value);
		
		//return false;
	}else{
		setday(nameHead,"JM",jmxmjdmObj);
	}
	
	if(jmxmjdmObj.value == sub_first_2_szsmdm){
		jmxmjdmObj.value ="";
		
	}
	if(jmxmjdmObj.value ==""){
		checkThisSzsmHasJmxmjdm(nameHead);
	}
}

//检查当前税种税目是否含有对应的减免项目及代码
function  checkThisSzsmHasJmxmjdm(nameHead){
	//var oDiv = document.all(nameHead+"_epodDateLayer");
	
	//获得减免项目及代码下拉
	var JmxmjdmSelectObj = document.all(nameHead+"_epodDateLayer").all.sel;
	
	//查看其下是否有选项，如果没有，则提示删除
	var JmxmjdmSelectObj_childsArr = JmxmjdmSelectObj.childNodes;
	if(JmxmjdmSelectObj_childsArr && JmxmjdmSelectObj_childsArr.length ==0){
		alert("没有对应的下拉选项可以选择，该税种税目没有对应减免优惠，请删除该行再保存！");
		event.returnValue = false;
		return false;
	}
	
}

/**
 * 选中已过滤的下拉菜单中指定值
 */
function checkedOldSelected(nameHead,selectedValue){
	//获得减免项目及代码下拉
	var JmxmjdmSelectObj = document.all(nameHead+"_epodDateLayer").all.sel;
	var JmxmjdmSelectObj_childsArr = JmxmjdmSelectObj.childNodes;
	for(var index =0; index < JmxmjdmSelectObj_childsArr.length; index ++){
		var optionObj = JmxmjdmSelectObj_childsArr[index];
		if(optionObj && optionObj.value == selectedValue){
			optionObj.selected =true;			
		}
		
		
	}
	
	
}



function showJmxmjdmInDiv(obj,type){
	if(obj && obj.value != ""){
		anchor_showText(obj,"jmxmjdm",type);
	}
}

//显示div
function anchor_showText(tt,nameHead,type){
	var txtHeight = tt.clientHeight;
	var txtTop = getPosTop(tt);//obj.offsetTop;
	var txtLeft = getPosLeft(tt);
	var oDiv = document.all(nameHead+"_showText");
	var parentHeight = document.body.clientHeight;//oParent.clientHeight;
	if(type=="show"){
		oDiv.style.display = '';
		oDiv.style.backgroundColor ="azure";
	}else if (type=="display"){
		oDiv.style.display = 'none';
	}
	if(txtTop+oDiv.clientHeight > (parentHeight+document.body.scrollTop)){
		oDiv.style.top = txtTop - oDiv.offsetHeight - 5;//10 --> border or margin
	}else{
		//oDiv.style.top = txtTop + txtHeight + 5;
		oDiv.style.top = txtTop - oDiv.offsetHeight - 5;
	}
	oDiv.style.left = txtLeft - oDiv.clientLeft;
	oDiv.style.zindex = '0';
	oDiv.innerHTML=tt.value;
	//alert("222");
}

</script>
<script>
makeReadOnly2();
</script>

</td>
</tr>
</table>
<script language="javascript"> 
/****如果该纳税人为非正常户，则显示提示信息****/
/****20050817 Huxiaofeng****/
var nsrzt = "<ttsoft:write name="jmssbForm" property="nsrzt" filter="false"/>";
checkNsrzt();
/**************end *******/
</script>
</body>
</html:html>

