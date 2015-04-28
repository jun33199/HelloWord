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
<title>北京市地方税务局申报缴款单</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/reader.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>

<script language="JavaScript">
function fnReturn()
{
	location.href="PG3_SBZS_000.htm"
}

</script>


</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file="./include/header.jsp"%>

<applet name="printer" code="com.ttsoft.bjtax.webprint.SSJKSPagePrinter" width="0" height="0" archive="<%=static_contextpath%>printer/vprinter.jar">
</applet>
 
<html:form action="/webapp/smsb/gtgshJksYpysAction.do" method="POST" >

<html:hidden property="actionType" value="Query"/>
<html:hidden property="sbbh" />
<html:hidden property="ysjcdm" />
<html:hidden property="forward" />

<html:hidden property="hzlx" />
<html:hidden property="hzksrq" />
<html:hidden property="hzjsrq" />
<html:hidden property="ypys" />
<html:hidden property="sbhzdh" />
<html:hidden property="gtgshJsjdm" />

      <table width="96%" align="center" cellspacing="0" class="table-99">
                <tr> 
                  <td class="1-td1"  colspan="7">中 华 人 民 共 和 国<ttsoft:write name="gtgshJksYpysForm" property="szmc"/><html:hidden property="szmc"/> 税 收 缴 款 书</td>
                </tr>
                <tr> 
                  <td class="1-td2"  colspan="7" valign="top" width="99%"> 
                    <br>
					<table class="table-99" cellspacing="0" >
                      <tr> 
                        <td width="8%" class="2-td2-t-left" nowrap> <strong>缴款书号</strong> 
                        </td>
                        <td width="17%" class="2-td2-t-left"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="jkpzh"  size="20"/></div></td>
                        <td width="15%" class="2-td2-t-left"><strong>填发日期</strong></td>
                        <td width="15%" class="2-td2-t-left"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="lrrq"  size="20"/></div></td>
                        <td width="14%" class="2-td2-t-left" nowrap><strong>征收机关</strong></td>
                        <td width="31%" class="2-td2-t-center"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="swjgzzjgmc"  size="40"/></div></td>
                      </tr>
                    </table>
					<br>  
					<table cellspacing="3" border="0" width="100%">
                      <tr> 
                        <td width="55%" rowspan="2"  valign="top"> 
						   <table cellspacing="0" border="0" width="100%">
                            <tr> 
                              <td colspan="4" class="2-td2-t-center"> <strong>缴款单位（人） 
                                </strong> </td>
                            </tr>
                            <tr> 
                              <td width="18%" class="2-td2-left"> <strong>代码</strong> 
                              </td>
                              <td width="28%" class="2-td2-left"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="jsjdm"  size="20"/></div></td>
                              <td width="27%" class="2-td2-left"><strong>电话</strong></td>
                              <td width="27%" class="2-td2-center"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="jydzlxdm"  size="20"/></div></td>
                            </tr>
                            <tr> 
                              <td class="2-td2-left"><strong>全称</strong></td>
                              <td colspan="3" class="2-td2-center"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="nsrmc"  size="50"/></div></td>
                            </tr>
                            <tr> 
                              <td class="2-td2-left"><strong>开户银行</strong></td>
                              <td colspan="3" class="2-td2-center"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="yhmc"  size="50"/></div></td>
                            </tr>
                            <tr> 
                              <td class="2-td2-left"><strong>账号</strong></td>
                              <td colspan="3" class="2-td2-center"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="zh"  size="20"/></div></td>
                            </tr>
                          </table></td>
                        <td>
						<table cellspacing="0" border="0" width="100%">
                            <tr> 
                              <td colspan="4" class="2-td2-t-center"> <strong>预算科目</strong> 
                              </td>
                            </tr>
                            <tr> 
                              <td width="22%" class="2-td2-left"><strong>编码</strong></td>
                              <td width="78%" colspan="3" class="2-td2-center"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="yskmdm"  size="20"/></div></td>
                            </tr>
                            <tr> 
                              <td class="2-td2-left"><strong>名称</strong></td>
                              <td colspan="3" class="2-td2-center"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="yskmmc"  size="40"/></div></td>
                            </tr>
                            <tr> 
                              <td class="2-td2-left"><strong>级次</strong></td>
                              <td colspan="3" class="2-td2-center"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="ysjcmc"  size="20"/></div></td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr> 
                        <td width="45%">
						   <table cellspacing="0" border="0" width="100%">
                            <tr> 
                              <td width="22%"  class="2-td2-t-left"> <strong>收缴国库 
                                </strong> </td>
                              <td width="78%" class="2-td2-t-center"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="gkzzjgmc"  size="20"/>(<bean:write name="gtgshJksYpysForm" property="skgkh"/>)</div></td>
                            </tr>
                          </table></td>
                      </tr>
                    </table>
                    
				<table class="table_99" cellspacing="0" width="99%">
                  <tr> 
                  <td width="10%" class="2-td2-t-left"nowrap > <strong>税款所属日期</strong> 
                  </td>
                  <td width="45%" class="2-td2-t-left"><div align="left">从 
											<html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="skssksrq"  size="20"/>
                      至 
                      <html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="skssjsrq"  size="20"/>
                    </div></td>
                  <td width="10%" class="2-td2-t-left"nowrap><strong>税款限缴日期</strong></td>
                  <td width="35%" class="2-td2-t-center"><div align="left">
                      <html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="xjrq"  size="20"/>
                    </div></td>
                </tr>
              </table>
			  <br>
			<table width="96%" border="0" cellpadding="0" cellspacing="0"  id="SBJKWHMX" hasTitle="true">
				<tr>   
					<td nowrap class="2-td1-left"><div align="center" >纳税项目代码</div></td>
					<td nowrap class="2-td1-left"><div align="center" >税种税目名称</div></td>
					<td nowrap class="2-td1-left"><div align="center" >课税数量</div></td>
					<td nowrap class="2-td1-left"><div align="center" >计税金额</div></td>
					<td nowrap class="2-td1-center"><div align="center" >实缴金额</div></td>
                </tr>
  				<!--The loop begin-->
                <bean:define id="jkslist" name="gtgshJksYpysForm" property="dataList"/>
                <logic:iterate indexId="index" name="jkslist" id="itemMap">
          		<bean:define id="item" name="itemMap"/>
				<tr>   
                  <td nowrap class="2-td2-left" height="28"><ttsoft:write name="item" property="szsmdm"/>&nbsp;</td>
                  <td nowrap class="2-td2-left" height="28"><ttsoft:write name="item" property="szsmmc"/>&nbsp;</td>
                    <td nowrap class="2-td2-left" height="28"><ttsoft:write name="item" property="kssl"/>&nbsp;</td>
                  <td nowrap class="2-td2-left" height="28"><ttsoft:write name="item" property="jsje"/>&nbsp;</td>
                  <td nowrap class="2-td2-center" height="28"><ttsoft:write name="item" property="sjse"/><input type="hidden" name="sjse" value="<ttsoft:write name="item" property="sjse"/>" />&nbsp;</td>
                </tr>
				</logic:iterate>
                <!--The end of loop-->
                
				<tr> 
                  <td nowrap class="2-td2-left" height="28"><strong>合计</strong></td>
                  <td nowrap class="2-td2-left" height="28">&nbsp;</td>
                    <td nowrap class="2-td2-left" height="28"> 　</td>
                  <td nowrap class="2-td2-left" height="28"> 　</td>
                  <td nowrap class="2-td2-center" height="28"> <bean:write name="gtgshJksYpysForm" property="hjje" /></td>
                </tr>
                <tr> 
                  <td nowrap class="2-td2-left" height="28"><strong>金额合计(大写)</strong> 
                  </td>
                  <td height="28" colspan="4" nowrap class="2-td2-center" id="xthjdx">系统带出<input type="hidden" name="szsmdm_focus"></td>
                </tr>
       </table>
							 <input type="hidden" name='szsmdm_focus'>
	
                    <table width="94%" border="0" cellpadding="0" cellspacing="4">
                      <tr valign="bottom"> 
                        <td width="30%">&nbsp;</td>
                        <td width="10%"><input type="image" accesskey="p" tabIndex="-1" src="<%=static_contextpath%>images/dy-p1.jpg" onmouseover="this.src='<%=static_contextpath%>images/dy-p2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/dy-p1.jpg'" alt="打印" onclick="goprint();return false;" style="cursor:hand">&nbsp;&nbsp;</td>
                        <td width="10%"><input type="image" accesskey="f" tabIndex="-1" onclick="doSubmitForm('<bean:write name="gtgshJksYpysForm" property="fhbs"/>');return false;" style="cursor:hand" onMouseOver="MM_swapImage('fh1','','<%=static_contextpath%>images/fh-f2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="返回" id="fh1" src="<%=static_contextpath%>images/fh-f1.jpg" width="79" border="0" height="22">&nbsp;&nbsp;</td>
						<td width="10%"><input type="image" accesskey="c" tabIndex="-1" onClick="tuichu();return false;" style="cursor:hand" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" border="0" height="22">&nbsp;&nbsp;</td>
                        <td width="30%">&nbsp;</td>
                      </tr>
                    </table>
	     </td>
		</tr>

	  </table>

 <div id=szsmdm_epodDateLayer style="position: absolute; width: 142; height: 166; z-index: 9998; display: none" onclick="this.style.display='none'"><select id=sel multiple size="10" onclick=selectClick("szsmdm","SBJKWHMX") onkeyup=selectMove("szsmdm","SBJKWHMX")  onfocusout="selectClick()"></select></div>
 </html:form>

<%@ include file="./include/footer.jsp"%>

 </td>
  </tr>
</table>


</body>
    <script language="javascript">
computeSameSum('sjse','xthjdx','SBJKWHMX');

	
////////////////////////这里开始打印的程序！///////////////////
function goprint(){
    var szdm = "<bean:write name="gtgshJksYpysForm" property="szmc" />"; //税种代码
    var sklx = "<bean:write name="gtgshJksYpysForm" property="sklx" />"; //税款类型
    var tfrq = "<bean:write name="gtgshJksYpysForm" property="lrrq" />"; //填发日期
    var lsgx = "<bean:write name="gtgshJksYpysForm" property="lsgx" />"; //隶属关系
    var zclx = "<bean:write name="gtgshJksYpysForm" property="zclxmc" />"; //注册类型
    var dnbh = "<bean:write name="gtgshJksYpysForm" property="jkpzh" />"; //电脑编号
    var zsjg = "<bean:write name="gtgshJksYpysForm" property="swjgzzjgmc" />"; //征收机关

    var jkdwdm = "<bean:write name="gtgshJksYpysForm" property="jsjdm" />"; //缴款单位代码
    var jkdwdh = "<bean:write name="gtgshJksYpysForm" property="jydzlxdm" />"; //缴款单位电话
    var jkdwqc = "<bean:write name="gtgshJksYpysForm" property="nsrmc" />"; //缴款单位全称
    var jkdwkhyh = "<bean:write name="gtgshJksYpysForm" property="yhmc" />"; //缴款单位开户银行
    var jkdwzh = "<bean:write name="gtgshJksYpysForm" property="zh" />"; //缴款单位账号

    var yskmbm = "<bean:write name="gtgshJksYpysForm" property="yskmdm" />"; //预算科目编码
    var yskmmc = "<bean:write name="gtgshJksYpysForm" property="yskmmc" />"; //预算科目名称
    var yskmjc = "<bean:write name="gtgshJksYpysForm" property="ysjcmc" />"; //预算科目级次
    var skgk = "<bean:write name="gtgshJksYpysForm" property="gkzzjgmc" />(<bean:write name="gtgshJksYpysForm" property="skgkh"/>)"; //收款国库

    var sksssq = "<bean:write name="gtgshJksYpysForm" property="skssksrq" /> 至 <bean:write name="gtgshJksYpysForm" property="skssjsrq" />"; //税款所属时期
    var skxjrq = "<bean:write name="gtgshJksYpysForm" property="xjrq" />"; //税款限缴日期
	
	var jkjehj = "金额合计（大写）：<bean:write name="gtgshJksYpysForm" property="hjjedx" />"; //缴款金额合计(TBD)
    var hjje = "￥<bean:write name="gtgshJksYpysForm" property="hjje" />";//合计金额
    
    var jkdw = "<bean:write name="gtgshJksYpysForm" property="nsrmc" />"; //缴款单位(TBD)
    var swjg = "<bean:write name="gtgshJksYpysForm" property="swjgzzjgmc" />"; //地方税务机关(TBD)

    var bz = " "; //备注(TBD)
    
    
    var  jkmxpmmc = "<bean:write name="gtgshJksYpysForm" property="mxPmmc"/>"; //缴款明细品目名称(TBD)
    var  jkmxkssl = "<bean:write name="gtgshJksYpysForm" property="mxKssl"/>"; //缴款明细课税数量(TBD)
    var  jkmxjsje = "<bean:write name="gtgshJksYpysForm" property="mxJsje"/>"; //缴款明细计税金额(TBD)
    var  jkmxsjse = "<bean:write name="gtgshJksYpysForm" property="mxSjse"/>"; //缴款明细实缴税额(TBD)
    

	//alert(szdm);

   
    //The second format!//此次不打预算级次
    var jkmxyjkc = " ";
    
    var sksssq_qs = "<bean:write name="gtgshJksYpysForm" property="skssksrq" />";
    var sksssq_jz = "<bean:write name="gtgshJksYpysForm" property="skssjsrq" />";
    var jkmxslse = " ";
    
    //判断打印方式/格式
    var printBack = fnOpen();
    //alert(printBack);
    if (printBack=="machine"){
    	//old
	    document.printer.setszdm(szdm);
	    document.printer.setsklx(sklx); //The new 
	    document.printer.setdnbh(dnbh);
	    document.printer.settfrq(tfrq);
	    document.printer.setlsgx(lsgx);
	    document.printer.setzclx(zclx);
	    document.printer.setzsjg(zsjg);
	    document.printer.setjkdwdm(jkdwdm);
	    document.printer.setjkdwdh(jkdwdh);
	    document.printer.setjkdwqc(jkdwqc);
	    document.printer.setjkdwkhyh(jkdwkhyh);
	    document.printer.setjkdwzh(jkdwzh);
	    document.printer.setyskmbm(yskmbm);
	    document.printer.setyskmmc(yskmmc);
	    document.printer.setyskmjc(yskmjc);
	    document.printer.setskgk(skgk);
	    document.printer.setsksssq(sksssq);
	    document.printer.setskxjrq(skxjrq);
	    document.printer.setjkmxpmmc(jkmxpmmc);
	    document.printer.setjkmxkssl(jkmxkssl);
	    document.printer.setjkmxjsje(jkmxjsje);
	    document.printer.setjkmxsjse(jkmxsjse);
	    document.printer.setjkjehj(jkjehj);
	    document.printer.setjkjehj_nu(hjje);
	    document.printer.setjkdw(jkdw);
	    document.printer.setswjg(swjg);
	    document.printer.setbz(bz);
    	document.printer.startPrint();
    	//return false;
    }
    
}


//打印机打票。Modified by lufeng 2003-12-19
function fnOpen(){
   return "machine";
}

// 不需要打印手工票。Modified by lufeng 2003-12-19
//function fnOpen(){
//   var ret = window.showModalDialog("printChoose.html", "",
//      "dialogHeight: 150px;dialogWidth: 350px;status:0");
//   if (ret=="hand" || ret=="machine" || ret=="failure") {
//   	return ret;
//   }
//   else {
//   	 alert("请确认！");
//   	 return fnOpen();
//   	 //return "";
//   }
//}

</script>
</html:html>