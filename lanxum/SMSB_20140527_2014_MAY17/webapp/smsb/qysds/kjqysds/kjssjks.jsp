<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjssjks.web.KjssjksForm"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page contentType="text/html; charset=GBK" %>
<%
KjssjksForm kjssjksForm=(KjssjksForm)request.getAttribute("kjssjksForm");
%>


<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>北京市地方税务局扣缴企业所得税税收缴款书</title>
<link href="../../../css/text.css" rel="stylesheet" type="text/css">
<link href="../../../css/top-box.css" rel="stylesheet" type="text/css"> 
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../../../js/treatImage.js"></script>
<script language="JavaScript" src="../../../js/smsb_common.js"></script> 
<script language="JavaScript" src="../../../js/DTable.js"></script> 
<script language="JavaScript" src="../../../js/reader.js"></script>
<script language="JavaScript" src="../../../js/InputSelect.js"></script>
<script language="JavaScript" src="../../../js/function.js"></script>


</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
<!-- <table  width="100%" height="61%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
   <td colspan=2> -->
<%@ include file="../../include/header.jsp"%>


<html:form action="/webapp/smsb/qysds/kjqysds/kjssjksAction.do" method="POST" >

<html:hidden property="actionType" value="Show"/>
<html:hidden property="yhdm" />
<html:hidden property="yhmc" />
<html:hidden property="jsjdm" />
<html:hidden property="nsrmc" />
<html:hidden property="sklxdm" value="200"/>
<html:hidden property="sklxmc" value="四代解缴"/>
<html:hidden property="skssksrq" />
<html:hidden property="skssjsrq" />
<html:hidden property="szsmdm" />
<html:hidden property="szsmmc" />
<html:hidden property="szdm" />
<html:hidden property="szmc" />
<html:hidden property="sjse" />
<html:hidden property="badjxh" />
<html:hidden property="bgbxh" />
<html:hidden property="htmc" />
<html:hidden property="htbh" />
<html:hidden property="jsje" />
<html:hidden property="sl" />


      <table width="96%" align="center" cellspacing="0" class="table-99">
                <tr> 
                  <td class="1-td1"  colspan="7">税收缴款书</td>
                </tr>
                <tr> 
                  <td class="1-td2"  colspan="7"> <br> 
				  	<table width="93%" cellspacing="0" class="table-99">
                      <tr class="black9"> 
                        <td width="11%" align="center" nowrap> <div align="right">申报日期：</div></td>
                        <td width="11%" align="center" nowrap><div align="left">                         
							<html:text property="sbrq"    size="10" onchange="if(isDate(this,false)) changeDateQ();"  onkeydown="if(event.keyCode==13) event.keyCode=9;" />
                          </div></td>
                        <td width="21%" align="center" nowrap> <div align="right">税款类型： 
                            &nbsp; </div></td>
                        <td width="39%" align="center" nowrap><div align="left"> 
							四代解缴
                          </div></td>
						  
                        <td width="14%" align="center" nowrap> <div align="center">金额单位：元</div></td>
                        <td width="4%" align="center" nowrap>&nbsp;</td>
                      </tr>
                    </table>
                    <br> <table width="94%" border="0" cellpadding="0" cellspacing="0">
                      <tr class="black9"> 
                        <td width="17%" nowrap class="2-td2-t-left">税务计算机代码</td>
                        <td width="35%" nowrap class="2-td2-t-left">
						<bean:write name="kjssjksForm"
						property="jsjdm" scope="request" filter="true" />
						</td>
                        <td width="24%" nowrap class="2-td2-t-left">单位名称</td>
                        <td width="24%" nowrap class="2-td2-t-center">
						<bean:write name="kjssjksForm"
						property="nsrmc" scope="request" filter="true" />
						</td>
                      </tr>
                      <tr> 
                        <td nowrap class="2-td2-left">缴款银行名称</td>
                        <td nowrap class="2-td2-left" id="bankName">&nbsp;
						</td>
                        <td nowrap class="2-td2-left">缴款银行帐号</td>
                        <td nowrap class="2-td2-center">
						  <bean:define id="bankList" name="kjssjksForm" property="bankList" />
                              <html:select property="zh" onchange="setBankName(this)">
                                 <html:options collection="bankList" property="zh" labelProperty="zh"/>
                              </html:select>
						  </td>
                      </tr>
                    </table>
                    <table cellspacing="0" class="table-99">
              	<tr> 
                	<td><hr class="hr1"></td>
              	</tr>
            	</table>
				<table width="94%" border="0" cellpadding="0" cellspacing="0" height="114" id="SBJKMX" >
				
					  
                      <tr class="black9"> 
                        <td width="17%" nowrap class="2-td2-t-left">合同名称</td>
                        <td width="35%" nowrap class="2-td2-t-left">
						<bean:write name="kjssjksForm"
						property="htmc" scope="request" filter="true" />
						</td>
                        <td width="24%" nowrap class="2-td2-t-left">合同编号</td>
                        <td width="24%" nowrap class="2-td2-t-center">
						<bean:write name="kjssjksForm"
						property="htbh" scope="request" filter="true" />
						</td>
                      </tr>
                      <tr class="black9"> 
                        <td width="17%" nowrap class="2-td2-left">税种名称</td>
                        <td width="35%" nowrap class="2-td2-left">
						<bean:write name="kjssjksForm"
						property="szmc" scope="request" filter="true" />
						</td>
                        <td width="24%" nowrap class="2-td2-left">项目名称</td>
                        <td width="24%" nowrap class="2-td2-center">
						<bean:write name="kjssjksForm"
						property="szsmmc" scope="request" filter="true" />
						</td>
                      </tr>
                      <tr class="black9"> 
                        <td width="17%" nowrap class="2-td2-left">税款所属起始日期</td>
                        <td width="35%" nowrap class="2-td2-left">
						<bean:write name="kjssjksForm"
						property="skssksrq" scope="request" filter="true" />
						</td>
                        <td width="24%" nowrap class="2-td2-left">税款所属截至日期</td>
                        <td width="24%" nowrap class="2-td2-center">
						<bean:write name="kjssjksForm"
						property="skssjsrq" scope="request" filter="true" />
						</td>
                      </tr>
                      <tr class="black9"> 
                        <td width="17%" nowrap class="2-td2-left">计税金额</td>
                        <td width="35%" nowrap class="2-td2-left">&nbsp;
						<bean:write name="kjssjksForm"
						property="jsje" scope="request" filter="true" />
						</td>
                        <td width="24%" nowrap class="2-td2-left">实际缴税额</td>
                        <td width="24%" nowrap class="2-td2-center">&nbsp;
						<bean:write name="kjssjksForm"
						property="sjse" scope="request" filter="true" />
						</td>
                      </tr>					  
                    </table>
			        <table width="94%" border="0" cellpadding="0" cellspacing="4">
                      <tr valign="bottom"> 
                        <td width="35%"> </td>
                        <td width="10%"><input type="image" accesskey="s" tabIndex="-1" onClick="doSubmitCheck('Save'); return false;"  onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="保存" id="bc1" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" height="22"></td>
                        <td width="10%"><input type="image" accesskey="f" onClick="back();return false;" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="返回" id="fanhui" src="<%=static_contextpath%>images/fanhui1.jpg" width="79" height="22" style="cursor:hand"></td>
                        <td width="10%"><input type="image" accesskey="c" tabIndex="-1"   onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22"></td>
                        <td width="35%">&nbsp;</td>
                      </tr>
                    </table>
      <br>
	     </td>
		</tr>
	  </table>

</html:form>


<%@ include file="../../include/footer.jsp"%>
    <!-- </td> 
  </tr>
</table> -->
<script language="javascript"> 
function change(){
	if(!document.all)
		return
	if (event.srcElement.id=="foldheader") {
		var srcIndex = event.srcElement.sourceIndex
		var nested = document.all[srcIndex+1]
		if (nested.style.display=="none") {
			nested.style.display=''
			event.srcElement.style.listStyleImage="url(<%=static_contextpath%>images/zbotton-jian2.gif)"
		}
		else {
			nested.style.display="none"
			event.srcElement.style.listStyleImage="url(<%=static_contextpath%>images/zbotton-jia2.gif)"
		}	
	}
}

document.onclick=change		

<ttsoft:write name="kjssjksForm" property="bankJsArray" filter="false"/>

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
	/********20040209 Shi Yanfeng*********************/
	if(bankInfo.length==0){
		document.all.yhmc.value ='';
		document.all.yhdm.value ='';
	}

}
setBankName(document.all.zh);

//跳转到缴款书维护界面
function doJkswh(){
	//必须录入计算机代码
	if(document.forms[0].jsjdm.value=='' || document.forms[0].nsrmc.value=='') return false;
	document.all.actionType.value='Jkswh';
	document.forms[0].submit();
}
function fnOnLoad(){}

//检查并提交，需要检查申报日期、税务计算机代码
//纳税项目代码、税款所属日期、课税数量、计税金额、实际缴税额
function doSubmitCheck(ope){
	//申报日期
	if(!isDate(document.forms[0].sbrq,false)) return false;
	
	doSubmitForm(ope);
}

//返回
function back(){
	history.back();
	self.location.reload();
}

</script>


</body>
</html:html>