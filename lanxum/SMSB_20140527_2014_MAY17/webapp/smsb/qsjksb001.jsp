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
<title>补缴欠缴税款</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css"> 
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script> 
<script language="JavaScript" src="../js/DTable.js"></script> 
<script language="JavaScript" src="../js/reader.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>


</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">

<%@ include file="./include/header.jsp"%>


<html:form action="/webapp/smsb/qsjksbAction.do" method="POST" >

<html:hidden property="actionType" value="Show"/>
<html:hidden property="yhdm" />
<html:hidden property="yhmc" />
<html:hidden property="bankJsArray" />
<html:hidden property="sklxmc" />
<html:hidden property="mxstrings" />
<html:hidden property="xgqx" />

      <table width="96%" align="center" cellspacing="0" class="table-99">
                <tr> 
                  <td class="1-td1"  colspan="7">补缴欠缴税款</td>
                </tr>
                <tr> 
                  <td class="1-td2"  colspan="7"> <br> 
				  	<table width="93%" cellspacing="0" class="table-99">
                      <tr class="black9"> 
                        <td width="11%" align="center" nowrap> <div align="right">申报日期：</div></td>
                        <td width="11%" align="center" nowrap><div align="left">                         
							<html:text property="sbrq"    size="10" tabIndex="-1"  styleClass="inputnoborder"  readonly="true" style="color:#3C5564"/>
                          </div></td>
                        <td width="21%" align="center" nowrap> <div align="right"> 
                            &nbsp; </div></td>
                        <td width="39%" align="center" nowrap><div align="left"> 
                          &nbsp; </div></td>
						  
                        <td width="14%" align="center" nowrap> <div align="center">金额单位：元</div></td>
                        <td width="4%" align="center" nowrap>&nbsp;</td>
                      </tr>
                    </table>
                    <br> <table width="94%" border="0" cellpadding="0" cellspacing="0">
                      <tr class="black9"> 
                        <td width="17%" nowrap class="2-td2-t-left">税务计算机代码</td>
                        <td width="35%" nowrap class="2-td2-t-left">
						<html:text property="jsjdm" maxlength="8"  size="20" onchange="if(this.value!='') doSubmitForm('Query')" onKeyDown="jsjdmQuery()"/>
						</td>
                        <td width="24%" nowrap class="2-td2-t-left">单位名称</td>
                        <td width="24%" nowrap class="2-td2-t-center">
						<html:text property="nsrmc" tabIndex="-1" styleClass="inputnoborder" size="30" readonly="true" style="color:#3C5564"/>
						</td>
                      </tr>
                      <tr> 
                        <td nowrap class="2-td2-left">缴款银行名称</td>
                        <td nowrap class="2-td2-left" id="bankName">&nbsp;
						</td>
                        <td nowrap class="2-td2-left">缴款银行帐号</td>
                        <td nowrap class="2-td2-center">
						  <bean:define id="bankList" name="qsjksbForm" property="bankList" />
                              <html:select property="zh" onchange="setBankName(this)">
                                 <html:options collection="bankList" property="zh" labelProperty="zh"/>
                              </html:select>
						  </td>
                      </tr>
                    </table>
                    <br> 
				<table id="tablemx" width="94%" border="0" cellpadding="0" cellspacing="0" class="table-99">
                      <tr> 
                        <td width="2%" rowspan='2' height="15" nowrap class="2-td1-left">&nbsp;</td>
                        <td width="6%" rowspan='2' height="15" nowrap class="2-td1-left">税种</td>
                        <td width="10%" rowspan='2' height="15" nowrap class="2-td1-left">税目</td>
                        <td width="10%" rowspan='2' height="15" nowrap class="2-td1-left">形成类型</td>
                        <td width="16%"  colspan='2' height="15" nowrap class="2-td1-left">税款所属期</td>
                        <td width="8%" rowspan='2' height="15" nowrap class="2-td1-left">限缴日期</td>
                        <td width="4%" rowspan='2' height="15" nowrap class="2-td1-left">滞纳天数</td>
                        <td width="6%" rowspan='2' height="15" nowrap class="2-td1-left">欠税金额</td>
                        <td width="12%"  colspan='2' height="15" nowrap class="2-td1-left">本次申报补缴</td>
                        <td width="6%" rowspan='2' height="15" nowrap class="2-td1-left">合计</td>
                        <td width="1%" rowspan='2' height="15" nowrap class="2-td1-left" style="display:none">隐藏值</td>
                        <td width="2%" rowspan='2' height="15" nowrap class="2-td1-center">选中</td>
                      </tr>
                      <tr> 
                        <td width="8%" height="15" nowrap class="2-td1-left">起始日期</td>
                        <td width="8%" height="15" nowrap class="2-td1-left">截止日期</td>
                        <td width="6%" height="15" nowrap class="2-td1-left">正税（费）</td>
                        <td width="6%" height="15" nowrap class="2-td1-left">滞纳金</td>
                      </tr>
								<bean:define id="qsmxlist" name="qsjksbForm" property="dataList" type="java.util.ArrayList"/>
								<% int indexId=2;int nameId=2; %>
              	                <logic:iterate indexId="index" id="map" name="qsmxlist" type="java.util.HashMap">
                                <tr id="<%=indexId%>"> 
                                  <td align=middle class="2-td2-left">&nbsp;<%=indexId++ -1 %></td>
                                  <td align=middle class="2-td2-left">&nbsp;<%=map.get("szmc")%></td>
                                  <td align=middle class="2-td2-left">&nbsp;<%=map.get("szsmmc")%></td>
                                  <td align=middle class="2-td2-left">&nbsp;<%=map.get("qsxclxmc")%></td>
                                  <td align=middle class="2-td2-left" id="skssqsrq<%=nameId%>">&nbsp;<%=map.get("skssqsrq")%></td>
                                  <td align=middle class="2-td2-left">&nbsp;<%=map.get("skssjzrq")%></td>
                                  <td align=middle class="2-td2-left">&nbsp;<%=map.get("yskxjrq")%></td>
                                  <td align=middle class="2-td2-left">&nbsp;<%=map.get("znjts")%></td>
                                  <td align=middle class="2-td2-left" id="qjje<%=nameId%>">&nbsp;<%=map.get("qjje")%></td>
                                  <td align=middle class="2-td2-left" id="sbje<%=nameId%>">&nbsp;<input type="text" name="sbje<%=nameId%>" size="10" onChange="if(isNum(this,null,null,false,null,2))zhengSuan('<%=map.get("qjje")%>','<%=map.get("znjts")%>','<%=map.get("skssqsrq")%>','<%=map.get("znjsl")%>','<%=map.get("szsmdm")%>','<%=nameId%>',this)"></td>
                                  <td align=middle class="2-td2-left" id="znj<%=nameId%>">&nbsp;<input type="text" name="znj<%=nameId%>" size="10" value="0" onChange="if(isNum(this,null,null,false,null,2))heSuan('<%=map.get("qjje")%>','<%=map.get("znjts")%>','<%=map.get("skssqsrq")%>','<%=map.get("znjsl")%>','<%=map.get("szsmdm")%>','<%=nameId%>',this)"></td>
                                  <td align=middle class="2-td2-left" id="hj<%=nameId%>">&nbsp;<input type="text" name="hj<%=nameId%>" size="10" onChange="if(isNum(this,null,null,false,null,2))fanSuan('<%=map.get("qjje")%>','<%=map.get("znjts")%>','<%=map.get("skssqsrq")%>','<%=map.get("znjsl")%>','<%=map.get("szsmdm")%>','<%=nameId%>',this)"></td>
                                  <td align=middle class="2-td2-left" id="ycz<%=nameId%>" style="display:none">&nbsp;<input type="text" name="ycz<%=nameId%>" size="6"></td>
                                  <td align=middle class="2-td2-center" id="qsjksbcheck<%=nameId%>">&nbsp;<input type="Checkbox" disabled="true" name="qsjksbcheck<%=nameId%>" onclick="return selectOne('<%=map.get("qsycz")%>','<%=map.get("znjycz")%>','<%=nameId++%>',this)"></td>
                                </tr>
								</logic:iterate>                      							  		
                      <tr> 
                        <td nowrap class="2-td2-left" height="15">合计</td>
                        <td nowrap class="2-td2-left" height="15">&nbsp;</td>
                        <td nowrap class="2-td2-left" height="15">&nbsp;</td>
                        <td nowrap class="2-td2-left" height="15">&nbsp;</td>
                        <td nowrap class="2-td2-left" height="15">&nbsp;</td>
                        <td nowrap class="2-td2-left" height="15">&nbsp;</td>
                        <td nowrap class="2-td2-left" height="15">&nbsp;</td>
                        <td nowrap class="2-td2-left" height="15">&nbsp;</td>
                        <td nowrap class="2-td2-left" height="15">&nbsp;
                        <html:text property="qshj" size="10"  styleClass="inputnoborder" readonly="true" />
                        </td>
                        <td nowrap class="2-td2-left" height="15" id ="sbhj">&nbsp;
                        <input type="text" size="10"  name="sbhj" styleClass="inputnoborder" readonly="true" />
                        </td>
                        <td nowrap class="2-td2-left" height="15" id ="znjhj">&nbsp;
                        <input type="text" size="10" name="znjhj"  styleClass="inputnoborder" readonly="true" />
                        </td>
                        <td nowrap class="2-td2-left" height="15" id ="xthj">&nbsp
                        <input type="text" size="10"  name="xthj"  styleClass="inputnoborder" readonly="true" />
                        </td>
                        <td nowrap class="2-td2-left" height="15" style="display:none">&nbsp;</td>
                        <td nowrap class="2-td2-center" height="15">&nbsp;</td>
                      </tr>					  
                    </table>
               <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
           <tr>
             <td width="40%"> <hr width="100%" size="1" class="hr1" >
             </td>
             <td width="20%" align="center" class="black9">
             	<strong><font color="#0000FF">注意事项</font></strong>
             </td>
             <td width="40%"> <hr width="100%" size="1" class="hr1" >
             </td>
           </tr>
        </table>
        <table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
           <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
             <td height="47" >
             	<p>
             	  &nbsp;&nbsp;&nbsp;&nbsp;A.页面可以申报的欠税形成类型是：延期缴纳税款、申报欠税、检查欠税、定额欠税、缓缴欠税、市稽查局检查未入库、其他欠税。<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;B.滞纳金计算从2004-01-01开始计算，如果之前的按2004-01-01开始，如果之后则按实际限缴日期的第二天开始计算。<br>
              </p>
             </td>
           </tr>
    </table>
			        <table width="94%" border="0" cellpadding="0" cellspacing="4">
                      <tr valign="bottom"> 
                        <td width="51%"> </td>
                        <td width="9%"><input type="image" accesskey="s" tabIndex="-1" onClick="if(setValue())doSubmitCheck('Save'); return false;"  onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="保存" id="bc1" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" height="22"></td>                        
                        <td width="9%"><input type="image" accesskey="w" tabIndex="-1" onclick="javascript:doJkswh(); return false;"  onMouseOver="MM_swapImage('whjks1','','<%=static_contextpath%>images/whjks-w2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="维护缴款书" id="whjks1" src="<%=static_contextpath%>images/whjks-w1.jpg" width="110" height="22"></td>
                        <td width="15%"><input type="image" accesskey="c" tabIndex="-1"   onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22"></td>
                        <td width="0%">&nbsp;</td>
                      </tr>
                    </table>
      <br>
	     </td>
		</tr>
	  </table>
</html:form>


<%@ include file="./include/footer.jsp"%>
<script language="javascript"> 

//响应计算机代码的回车查询
function jsjdmQuery(){
	if(document.all.jsjdm.value!='' && window.event.keyCode==13){
		doSubmitForm('Query')
	}
	if(event.keyCode==13) event.keyCode = 9;
}


<ttsoft:write name="qsjksbForm" property="bankJsArray" filter="false"/>

function setBankName(obj)
{	
	for (var i=0; i<bankInfo.length; i++)
	{
		if (bankInfo[i][0] == obj.value)
		{
			document.all.yhmc.value = bankInfo[i][1];
			document.all.yhdm.value = bankInfo[i][2];
			bankName.innerText = " " + bankInfo[i][1];
			break;
		}
	}
	/********如果该用户不存在银行账户的时候，将银行信息置空**********/
	if(bankInfo.length==0){
		document.all.yhmc.value ='';
		document.all.yhdm.value ='';
	}

}
setBankName(document.all.zh);


/****如果该纳税人为非正常户，则显示提示信息****/
var nsrzt = <ttsoft:write name="qsjksbForm" property="nsrzt" filter="false"/>;
checkNsrzt();

	/****如果修改权限代码为0则滞纳金不可修改，为1则可修改****/
var xgqx=document.forms[0].xgqx.value;


//跳转到缴款书维护界面
function doJkswh(){
	//必须录入计算机代码
	if(document.forms[0].jsjdm.value=='' || document.forms[0].nsrmc.value=='') return false;
	document.all.actionType.value='Jkswh';
	document.forms[0].submit();
}

//检查并提交，需要检查申报日期、税务计算机代码
function doSubmitCheck(ope){
	//必须录入计算机代码
	if(document.forms[0].jsjdm.value==''|| document.forms[0].nsrmc.value=='') return false;
	//申报日期
	if(!isDate(document.forms[0].sbrq,false)) return false;
	
	doSubmitForm(ope);
}


//进入页面时将焦点设为计算机代码录入
// 页面进入焦点确定
function fnOnLoad()
{
    document.forms[0].jsjdm.focus();
    document.getElementsByName("sbhj")[1].value = 0;
	  document.getElementsByName("znjhj")[1].value= 0;
	  document.getElementsByName("xthj")[1].value = 0;
}

//根据录入的申报金额正算滞纳金与合计金额
function zhengSuan(a,b,c,d,e,f,obj){

	//验证申报金额数值整数位长度小于等于21
	var val=document.getElementsByName("sbje"+f)[1].value;
	var dian='.';
	var s = val.indexOf(dian);
	if(s=='-1'){
		s=val.length;
	}
	if(s>'21'){
		alert("申报金额超出数字设置最大长度");
		document.getElementsByName("sbje"+f)[1].value=0;
	}	
	
	//验证申报金额数值小于等于欠税金额
	var ce = formatNumber(parseFloat(a)-parseFloat(document.getElementsByName("sbje"+f)[1].value));
	if(parseFloat(ce) < 0){
		document.getElementsByName("sbje"+f)[1].value=0;
		document.getElementsByName("qsjksbcheck"+f)[1].disabled=true;
		alert("申报金额不能大于欠税金额");
	}else{	
		if(xgqx=='0'){
			document.getElementsByName("znj"+f)[1].disabled=true;
		}
		if(e.substring(4,6)=='92'){
			document.getElementsByName("znj"+f)[1].disabled=false;
		}
			document.getElementsByName("znj"+f)[1].value=Math.round(((document.getElementsByName("sbje"+f)[1].value)*b*d)*Math.pow(10,2))/Math.pow(10,2);

	document.getElementsByName("hj"+f)[1].value=formatNumber(parseFloat(document.getElementsByName("sbje"+f)[1].value)+parseFloat(document.getElementsByName("znj"+f)[1].value));
	var sb = formatNumber(parseFloat(document.getElementsByName("sbje"+f)[1].value)-parseFloat(0));
	if(parseFloat(sb) > 0){
		document.getElementsByName("qsjksbcheck"+f)[1].disabled=false;
	}else{
		document.getElementsByName("qsjksbcheck"+f)[1].disabled=true;
	}
	}
}


//根据录入的合计金额反算税额与滞纳金金额
function fanSuan(a,b,c,d,e,f,obj){
	
	//验证合计金额数值整数位长度小于等于21
	var val=document.getElementsByName("hj"+f)[1].value;
	var dian='.';
	var s = val.indexOf(dian);
	if(s=='-1'){
		s=val.length;
	}
	if(s>'21'){
		alert("合计金额超出数字设置最大长度");
		document.getElementsByName("hj"+f)[1].value=0;
	}	
	
		var hj=document.getElementsByName("hj"+f)[1].value;		
		var chushu=parseFloat(1)+parseFloat(b*d);
		var sbje=Math.round((parseFloat(hj)/(parseFloat(1)+parseFloat(b*d)))*Math.pow(10,2))/Math.pow(10,2);
		var ce=formatNumber(parseFloat(a)-parseFloat(sbje));

		
		if(parseFloat(ce) < 0){
			document.getElementsByName("sbje"+f)[1].value="";
			document.getElementsByName("znj"+f)[1].value="0";
			document.getElementsByName("qsjksbcheck"+f)[1].disabled=true;
			alert("依据合计金额计算的申报金额大于欠税金额");
		}else{
		if(xgqx=='0'){
			document.getElementsByName("znj"+f)[1].disabled=true;
		}
		if(e.substring(4,6)=='92'){
			document.getElementsByName("znj"+f)[1].disabled=false;
		}
			document.getElementsByName("sbje"+f)[1].value=sbje;
			document.getElementsByName("znj"+f)[1].value=formatNumber(parseFloat(hj)-parseFloat(sbje));			
			var sb = formatNumber(parseFloat(document.getElementsByName("sbje"+f)[1].value)-parseFloat(0));
			if(parseFloat(sb) > 0){
				document.getElementsByName("qsjksbcheck"+f)[1].disabled=false;
			}else{
				document.getElementsByName("qsjksbcheck"+f)[1].disabled=true;
			}
		}
		
	

}

//根据录入的滞纳金金额求合计金额
function heSuan(a,b,c,d,e,f,obj){
		if(e.substring(4,6)=='92'){
			alert(document.getElementsByName("znj"+f)[1].value);
			if(document.getElementsByName("znj"+f)[1].value!='0'&&document.getElementsByName("znj"+f)[1].value!=Math.round(((document.getElementsByName("sbje"+f)[1].value)*b*d)*Math.pow(10,2))/Math.pow(10,2)){				
			document.getElementsByName("znj"+f)[1].value=Math.round(((document.getElementsByName("sbje"+f)[1].value)*b*d)*Math.pow(10,2))/Math.pow(10,2);
			alert("修改数据不合法");
		}
		}	
	document.getElementsByName("hj"+f)[1].value=formatNumber(parseFloat(document.getElementsByName("sbje"+f)[1].value)+parseFloat(document.getElementsByName("znj"+f)[1].value));
}

//整理封装申报数据
function selectOne(a,b,c,obj){
	var sbhj =parseFloat(document.getElementsByName("sbhj")[1].value);
	var znjhj=parseFloat(document.getElementsByName("znjhj")[1].value);
	var xthj =parseFloat(document.getElementsByName("xthj")[1].value);
	if(document.getElementsByName("qsjksbcheck"+c)[1].checked==true){	
		var s1="";
		var s2="";
		s1=	a+":"+document.getElementsByName("sbje"+c)[1].value+":"+document.getElementsByName("sbje"+c)[1].value;
		s2=	b+":"+document.getElementsByName("sbje"+c)[1].value+":"+document.getElementsByName("znj"+c)[1].value;
		document.getElementsByName("ycz"+c)[1].value=s1+";"+s2;
		document.getElementsByName("sbje"+c)[1].disabled=true;
		document.getElementsByName("znj"+c)[1].disabled=true;
		document.getElementsByName("hj"+c)[1].disabled=true;
		document.getElementsByName("sbhj")[1].value = formatNumber(sbhj  + parseFloat(document.getElementsByName("sbje"+c)[1].value)) ;
		document.getElementsByName("znjhj")[1].value =formatNumber(znjhj + parseFloat(document.getElementsByName("znj"+c)[1].value)) ;
		document.getElementsByName("xthj")[1].value = formatNumber(xthj  + parseFloat(document.getElementsByName("hj"+c)[1].value)) ;
		znjhj = znjhj + document.getElementsByName("znj"+c)[1].value ;
		xthj  = xthj + document.getElementsByName("hj"+c)[1].value ;
	}else{
		document.getElementsByName("ycz"+c)[1].value="";
		document.getElementsByName("sbje"+c)[1].disabled=false;
	  document.getElementsByName("sbhj")[1].value = formatNumber( sbhj  - parseFloat(document.getElementsByName("sbje"+c)[1].value)) ;
		document.getElementsByName("znjhj")[1].value =  formatNumber(znjhj - parseFloat(document.getElementsByName("znj"+c)[1].value)) ;
		document.getElementsByName("xthj")[1].value =  formatNumber( xthj  - parseFloat(document.getElementsByName("hj"+c)[1].value) );
		if(xgqx=='0'){
			document.getElementsByName("znj"+c)[1].disabled=true;
		} else if(xgqx=='1'){
		document.getElementsByName("znj"+c)[1].disabled=false;
	}
		document.getElementsByName("hj"+c)[1].disabled=false;
	}
}

//将选中数据整理到mxstrings中
function setValue(){
		tableid="tablemx";
    var rows = eval(tableid).rows;
    var strtmp="";
	for(var ii=2;ii<=rows.length-2;ii++){
			if(document.getElementsByName("qsjksbcheck"+ii)[1].checked==true){				
			strtmp=strtmp+document.getElementsByName("ycz"+ii)[1].value+";";
		}
	}
	if(strtmp.length>0){
		document.all.mxstrings.value=strtmp;
		return true;		
	}else{
		return false;
	}
}
</script>

</body>
</html:html>