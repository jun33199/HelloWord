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
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <title>北京市地方税务局申报缴款单</title>
    <link href="../css/text.css" rel="stylesheet" type="text/css"/>
    <link href="../css/top-box.css" rel="stylesheet" type="text/css"/>
    <link href="css/beijing.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" type="text/JavaScript" src="../js/treatImage.js"></script>
    <script language="JavaScript" type="text/JavaScript" src="../js/smsb_common.js"></script>
    <script language="JavaScript" type="text/JavaScript" src="../js/DTable.js"></script>
    <script language="JavaScript" type="text/JavaScript" src="../js/reader.js"></script>
    <script language="JavaScript" type="text/JavaScript" src="../js/InputSelect.js"></script>
    <script language="JavaScript" type="text/JavaScript" src="../js/function.js"></script>

  </head>

  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
    <%@ include file="./include/header.jsp"%>

    <applet name="printer" code="com.ttsoft.bjtax.webprint.SSJKSPagePrinterNew" width="0" height="0" archive="<%=static_contextpath%>printer/vprinter.jar">
    </applet>

    <html:form action="/webapp/smsb/qsjksbjksypysAction.do" method="POST" >

      <html:hidden property="actionType" value="Query"/>
      <html:hidden property="sbbh" />
      <html:hidden property="ysjcdm" />
      <html:hidden property="forward" />
      <html:hidden property="presbbh" />
      <html:hidden property="sjly" />
      <html:hidden property="sbrq" />
      <table width="96%" align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1"  colspan="7"><font size="5" color="#999999">中 华 人 民 共 和 国<br>税收缴款书(银行经收专用)</font></td>
        </tr>
        <tr>
          <td class="1-td2"  colspan="7" valign="top" width="99%">
            <br/>
            <table class="table-99" cellspacing="0" >
              <tr>
                <td width="8%" class="2-td1-left" nowrap><strong>税款类型</strong></td>
                <td width="17%" class="2-td2-t-left"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="sklx"  size="40"/></div></td>
                <td width="15%" class="2-td1-left"><strong>电脑编号</strong></td>
                <td width="15%" class="2-td2-t-left"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="jkpzh"  size="40"/></div></td>
                <td width="14%" class="2-td1-left" nowrap>&nbsp;</td>
                <td width="31%" class="2-td2-t-center">&nbsp;</td>
              </tr>
              <tr>
                <td width="8%" class="2-td1-left" nowrap> <strong>登记注册类型</strong></td>
                <td width="17%" class="2-td2-left">&nbsp;</td>
                <td width="15%" class="2-td1-left"><strong>填发日期</strong></td>
                <td width="15%" class="2-td2-left">
                	<div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="tfrqn"  size="4"/><strong>年</strong><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="tfrqy"  size="2"/><strong>月</strong><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="tfrqr"  size="2"/><strong>日</strong></div>	
                </td>
                <td width="14%" class="2-td1-left" nowrap><strong>税务机关</strong></td>
                <td width="31%" class="2-td2-center"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="swjgzzjgmc"  size="40"/></div></td>
              </tr>
            </table>
            <br/>
            <table cellspacing="3" border="0" width="100%">
              <tr>
                <td width="55%" valign="top">
                  <table cellspacing="0" border="0" width="100%">
                    <tr>
                      <td rowspan="2" width="10%" class="2-td1-left"> <strong>缴款单位（人）</strong> </td>
                      <td width="10%" class="2-td1-left"><strong>识别号</strong></td>
                      <td width="30%" class="2-td2-t-left"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="jsjdm"  size="20"/></div></td>
                      <td width="20%" class="2-td1-left"><strong>开户银行</strong></td>
                      <td width="30%" class="2-td2-t-center"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="yhmc"  size="20"/></div></td>
                    </tr>
                    <tr>
                      <td width="10%" class="2-td1-left"><strong>名称</strong></td>
                      <td width="30%" class="2-td2-left"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="nsrmc"  size="50"/></div></td>
                      <td width="20%" class="2-td1-left"><strong>账号</strong></td>
                      <td width="30%" class="2-td2-center"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="zh"  size="20"/></div></td>
                    </tr>
                    <tr>                      	
                      <td colspan="2" width="20%" class="2-td1-left"><strong>收款国库</strong></td>
                      <td width="30%" class="2-td2-left"><div align="left">(<bean:write name="qsjksbjksypysActionForm" property="skgkh"/>)<html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="gkzzjgmc"  size="20"/></div></td>
                      <td width="20%" class="2-td1-left"><strong>税款限缴日期</strong></td>
                      <td width="30%" class="2-td2-center"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="xjrq"  size="20"/></div></td>
                    </tr>                    
                  </table>
                </td>
              </tr>
            </table>
				<table class="table-99" cellspacing="0" width="98%" border="1">
					    <tr>
					    	<td colspan="3" width="35%" class="2-td1-left"><div align="center">预算科目</div></td>
					    	<td rowspan="2" width="15%" class="2-td1-left"><div align="center">品目名称</div></td>
					    	<td rowspan="2" width="8%" class="2-td1-left"><div align="center">课税数量</div></td>
							  <td rowspan="2" width="8%" class="2-td1-left"><div align="center">计税金额或<br>销售收入</div></td>
							  <td rowspan="2" width="8%" class="2-td1-left"><div align="center">税率或单位税额</div></td>
							  <td rowspan="2" width="10%" class="2-td1-left"><div align="center">税款所属时期</div></td>
							  <td rowspan="2" width="8%" class="2-td1-left"><div align="center">已缴或扣除额</div></td>							  
							  <td rowspan="2" width="8%" class="2-td1-center"><div align="center">实际金额</div></td>
					   </tr>  					
					    <tr>
					    	<td width="10%" class="2-td1-left"><div align="center">编&nbsp;&nbsp;码</div></td>
					    	<td width="15%" class="2-td1-left"><div align="center">名&nbsp;&nbsp;称</div></td>
					    	<td width="10%" class="2-td1-left"><div align="center">级&nbsp;&nbsp;次</div></td>
					   </tr>              
				    <bean:define id="itemjksprint" name="qsjksbjksypysActionForm" property="dataList"/>
				    <logic:iterate id="itemprint" name="itemjksprint" indexId="index">
				        <tr>
				        	<td class="2-td2-left"><div align="center"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="yskmdm" /></div></td>
				        	<td class="2-td2-left"><div align="center"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="yskmmc" /></div></td>
				        	<td class="2-td2-left"><div align="center"><%=(((Map)itemprint).get("fcbl"))%>&nbsp;</div></td>
				        	<td class="2-td2-left"><div align="center"><%=(((Map)itemprint).get("szsmmc"))%>&nbsp;</div></td>
				        	<td class="2-td2-left"><div align="center"><%=(((Map)itemprint).get("kssl"))%>&nbsp;</div></td>
				        	
				          <td class="2-td2-left"><div align="center"><%=(((Map)itemprint).get("jsje"))%>&nbsp;</div></td>
				          <td class="2-td2-left"><div align="center">&nbsp;</div></td>
				          <td class="2-td2-left"><div align="center"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="skssksrq"  size="8"/>-<html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="skssjsrq"  size="8"/></div></td>
				          <td class="2-td2-left"><div align="center">&nbsp;</div></td>
				          <td class="2-td2-center"><div align="center"><%=((Map)itemprint).get("sjse")%>&nbsp;</div></td>
				        </tr>
				    </logic:iterate>              
              <tr>
                <td nowrap class="2-td1-left" height="28"><strong>金额合计</strong></td>
                <td colspan="8" nowrap class="2-td2-left" height="28"><strong>(大写)&nbsp;&nbsp;&nbsp;&nbsp;</strong><input type="text" name="xthjdx" value='<ttsoft:write name="qsjksbjksypysActionForm" property="hjjedx"/>' class="inputnoborder" readonly size ="60"></td>
                <td nowrap class="2-td2-center" height="28">￥<input type="text" name="xthj" value='<ttsoft:write name="qsjksbjksypysActionForm" property="hjje"/>' class="inputnoborder" readonly size ="15"></td>
              </tr>
              <tr>
                <td colspan="2" nowrap class="2-td2-left" height="60"><strong>税务机关</strong><br><input type="text" name="dfswjg" value='<ttsoft:write name="qsjksbjksypysActionForm" property="dfswjg"/>' class="inputnoborder" readonly size ="40"><br><div align="center"><strong>(盖章)</strong></div><br><div align="left"><strong>&nbsp;&nbsp;填票人</strong></div></td>
                <td colspan="2" nowrap class="2-td2-left" height="60"><strong>缴款单位(人)</strong><br><input type="text" name="nsrmc" value='<ttsoft:write name="qsjksbjksypysActionForm" property="nsrmc"/>' class="inputnoborder" readonly size ="40"><br><div align="center"><strong>(盖章)</strong></div><br><div align="left"><strong>&nbsp;&nbsp;经办人</strong></div></td>
                <td colspan="3" nowrap class="2-td2-left" height="60"><strong>上列款项已收妥并划转收款单位账户</strong><p><div align="center"><strong>国库(银行)盖章</strong></div><br><div align="center"><strong>&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日</strong></div></td>
                <td colspan="3" nowrap class="2-td2-center" height="60"><div align="left"><strong>备注</strong></div><br><input type="text" name="bz" value='<ttsoft:write name="qsjksbjksypysActionForm" property="bz"/>' class="inputnoborder" readonly size ="40"></td>
              </tr>                          
            </table>
            <table width="94%" border="0" cellpadding="0" cellspacing="4">
              <tr valign="bottom">
                <td width="20%"> </td>
                <td width="20%"><input type="image" accesskey="p" tabIndex="-1" onClick="goprint();return false;" style="cursor:hand;" onMouseOver="MM_swapImage('dy1','','<%=static_contextpath%>images/dy-p2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="打印" id="dy1" src="<%=static_contextpath%>images/dy-p1.jpg" width="79" height="22"/></td>
                <td width="20%"><input type="image" accesskey="f" tabIndex="-1" onclick="doSubmitForm('Back');return false;" style="cursor:hand"  onMouseOver="MM_swapImage('fh1','','<%=static_contextpath%>images/fh-f2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="返回" id="fh1" src="<%=static_contextpath%>images/fh-f1.jpg" width="79" height="22" /></td>
                <td width="20%"><input type="image" accesskey="c" tabIndex="-1"   onClick="tuichu(); return false;" style="cursor:hand;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22"/></td>
                <td width="0%"></td>
                <td width="20%">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
        <%@ include file="./include/footer.jsp"%>
      </td>
    </tr>
  </table>

</html:form>
</body>
<script language="javascript" type="text/JavaScript">


////////////////////////这里开始打印的程序！///////////////////
function goprint(){
//  var szdm = "<bean:write name="qsjksbjksypysActionForm" property="szmc" />"; //税种代码
  var sklx = "<bean:write name="qsjksbjksypysActionForm" property="sklx" />"; //税款类型
//  var tfrq = "<bean:write name="qsjksbjksypysActionForm" property="lrrq" />"; //填发日期
//  var lsgx = "<bean:write name="qsjksbjksypysActionForm" property="lsgx" />"; //隶属关系
  var dnbh = "<bean:write name="qsjksbjksypysActionForm" property="jkpzh" />"; //电脑编号
  var zclx = "<bean:write name="qsjksbjksypysActionForm" property="zclxmc" />"; //登记注册类型
  var tfrqn = "<bean:write name="qsjksbjksypysActionForm" property="tfrqn" />"; //填发日期年
  var tfrqy = "<bean:write name="qsjksbjksypysActionForm" property="tfrqy" />"; //填发日期月
  var tfrqr = "<bean:write name="qsjksbjksypysActionForm" property="tfrqr" />"; //填发日期日
  var zsjg = "<bean:write name="qsjksbjksypysActionForm" property="swjgzzjgmc" />"; //税务机关

  var jkdwdm = "<bean:write name="qsjksbjksypysActionForm" property="jsjdm" />"; //缴款单位识别号
//  var jkdwdh = "<bean:write name="qsjksbjksypysActionForm" property="jydzlxdm" />"; //缴款单位电话
  var jkdwqc = "<bean:write name="qsjksbjksypysActionForm" property="nsrmc" />"; //缴款单位名称
  var jkdwkhyh = "<bean:write name="qsjksbjksypysActionForm" property="yhmc" />"; //缴款单位开户银行
  var jkdwzh = "<bean:write name="qsjksbjksypysActionForm" property="zh" />"; //缴款单位账号
  var skgk = "(<bean:write name="qsjksbjksypysActionForm" property="skgkh"/>)<bean:write name="qsjksbjksypysActionForm" property="gkzzjgmc" />"; //收款国库
  var skxjrq = "<bean:write name="qsjksbjksypysActionForm" property="xjrq" />"; //税款限缴日期  

  var yskmbm = "<bean:write name="qsjksbjksypysActionForm" property="yskmdm" />"; //预算科目编码
  var yskmmc = "<bean:write name="qsjksbjksypysActionForm" property="yskmmc" />"; //预算科目名称
//  var yskmjc = "<bean:write name="qsjksbjksypysActionForm" property="ysjcmc" />"; //预算科目级次
  var  jkmxfcbl = "<bean:write name="qsjksbjksypysActionForm" property="mxFcbl"/>"; //缴款明细分成比例  
  var  jkmxpmmc = "<bean:write name="qsjksbjksypysActionForm" property="mxPmmc"/>"; //缴款明细品目名称(TBD)
  var  jkmxkssl = "<bean:write name="qsjksbjksypysActionForm" property="mxKssl"/>"; //缴款明细课税数量(TBD)
  var  jkmxjsje = "<bean:write name="qsjksbjksypysActionForm" property="mxJsje"/>"; //缴款明细计税金额(TBD)
  var sksssq = "<bean:write name="qsjksbjksypysActionForm" property="skssksrq" /> - <bean:write name="qsjksbjksypysActionForm" property="skssjsrq" />"; //税款所属时期  
  var  jkmxsjse = "<bean:write name="qsjksbjksypysActionForm" property="mxSjse"/>"; //缴款明细实缴税额(TBD)

  var jkjehj = "<bean:write name="qsjksbjksypysActionForm" property="hjjedx" />"; //缴款金额合计(TBD)
  var hjje = "￥<bean:write name="qsjksbjksypysActionForm" property="hjje" />";//合计金额
  var swjg = "<bean:write name="qsjksbjksypysActionForm" property="dfswjg" />"; //税务机关(TBD)
  var jkdw = "<bean:write name="qsjksbjksypysActionForm" property="nsrmc" />"; //缴款单位(TBD)
  var bz = "<bean:write name="qsjksbjksypysActionForm" property="bz" />"; //备注(TBD)

  //判断打印方式/格式
  var printBack = fnOpen();
  //alert(printBack);
  if (printBack=="machine"){
    //old
//    document.printer.setszdm(szdm);
    document.printer.setsklx(sklx); //The new
    document.printer.setdnbh(dnbh);
    document.printer.setzclx(zclx);    
//    document.printer.settfrq(tfrq);
    document.printer.settfrqn(tfrqn);
    document.printer.settfrqy(tfrqy);
    document.printer.settfrqr(tfrqr);            
//    document.printer.setlsgx(lsgx);
    document.printer.setzsjg(zsjg);
    document.printer.setjkdwdm(jkdwdm);
//    document.printer.setjkdwdh(jkdwdh);
    document.printer.setjkdwqc(jkdwqc);
    document.printer.setjkdwkhyh(jkdwkhyh);
    document.printer.setjkdwzh(jkdwzh);
    document.printer.setskgk(skgk);
    document.printer.setskxjrq(skxjrq);   
    document.printer.setyskmbm(yskmbm);
    document.printer.setyskmmc(yskmmc);
//    document.printer.setyskmjc(yskmjc);
    document.printer.setjkmxfcbl(jkmxfcbl);    
    document.printer.setjkmxpmmc(jkmxpmmc);
    document.printer.setjkmxkssl(jkmxkssl);
    document.printer.setjkmxjsje(jkmxjsje);
    document.printer.setsksssq(sksssq);    
    document.printer.setjkmxsjse(jkmxsjse);
    document.printer.setjkjehj(jkjehj);
    document.printer.setjkjehj_nu(hjje);
    document.printer.setswjg(swjg);
    document.printer.setjkdw(jkdw);
    document.printer.setbz(bz);
    document.printer.startPrint();
    //return false;
  }

}

//打印机打票。
function fnOpen(){
  return "machine";
}

function fnReturn()
{
  location.href="PG3_SBZS_000.htm";
}

</script>
</html:html>


