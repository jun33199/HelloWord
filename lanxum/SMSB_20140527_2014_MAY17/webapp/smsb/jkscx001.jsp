<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.ttsoft.framework.util.JspUtil"%>
<%@ page import="com.ttsoft.bjtax.smsb.jkscx.web.JkscxForm"%>
<%@ page import="com.ttsoft.common.util.CodeConstants"%>

<%
    JkscxForm pf=(JkscxForm)session.getAttribute("jkscxForm");

    //税务机关代码
    String swjgdm=pf.getSwsdm();
    String swjgdm_default="";
    String swsdm_default="";
     swjgdm_default=pf.getSwjgdm();
     swsdm_default=pf.getSwsdm();
    String szdm_default = pf.getSzdm();
    String smdm_default = pf.getSzsmdm();
    if (smdm_default == null) smdm_default="";

%>

<script language="JavaScript">
    // 主管税务机关
  var arySelect_swjgzzjgdm_ju=<%=JspUtil.displaySelectDataSource(pf.getZgswjg().values(),false)%>;
// 主管税务所
  var arySelect_swjgzzjg_suo=<%=JspUtil.displaySelectDataSource(pf.getZgsws().values(),false)%>;

 // 主管税务机关
  var arySelect_szlist=<%=com.ttsoft.bjtax.smsb.util.JspUtil.displaySelectDataSource(pf.getSzList(),false)%>;
// 主管税务所
  var arySelect_smlist=<%=com.ttsoft.bjtax.smsb.util.JspUtil.displaySelectDataSource(pf.getSmList(),false)%>;


</script>

<html:html>
<head>
<title>申报缴款书明细查询 </title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>
<script language="javascript" src="../js/gmit_selectcontrol.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
 <%@ include file="./include/header.jsp"%>
<html:form action="/webapp/smsb/jkscxAction.do" method="POST" >
  <html:hidden property="actionType" />
  <html:hidden property="pageSize"/>
  <html:hidden property="nextPage"/>
  <html:hidden property="totalpage"/>
  <html:hidden property="message"/>
  <html:hidden property="mxsphm"/>
  <table width="96%" align="center" cellspacing="0" class="table-99">
    <tr>
      <td class="1-td1"  colspan="7">缴款书明细查询 </td>
    </tr>
    <tr>
      <td class="1-td2"  colspan="7"><br>
        <table cellspacing="0" class="table-99">
          <tr class="black9">
            <td align="left" nowrap>
              <div align="left"></div>
            </td>
            <td align="right" nowrap>
              <div align="right"></div>
            </td>
          </tr>
        </table><br>
        <table  cellspacing="0" class="table-99">
          <tr>
            <td width="20%" class="2-td2-t-left"><div align="right">主管税务机关</div></td>
            <td width="30%" class="2-td2-t-left">
              <div align="left">
                <!--option></option-->
                <%if (pf.getYhjb().equals(CodeConstants.JBDM_SJ)){%>
                <html:select property="swjgdm" onchange="addFilterWithNull(this.value,document.forms[0].swsdm,2,arySelect_swjgzzjg_suo);">
                </html:select>
                <%}else{%>
                <html:select property="swjgdm" onchange="addFilterWithNUll(this.value,document.forms[0].swsdm,2,arySelect_swjgzzjg_suo);">
                </html:select>
                <%}%>
              </div>
            </td>
            <td width="20%" class="2-td2-t-left"><div align="right">主管税务所</div></td>
            <td width="30%" class="2-td2-t-center">
              <div align="left">
                <html:select property="swsdm" style="width:160px">
                </html:select>
              </div>
            </td>
          </tr>
          <tr>
            <td  class="2-td2-left"><div align="right">申报日期起</div></td>
            <td class="2-td2-left"> <div align="left"><html:text property="sbrqq" size="10" maxlength="10"/></div></td>
            <td  class="2-td2-left"><div align="right">申报日期止</div></td>
            <td  class="2-td2-center"> <div align="left"><html:text property="sbrqz" size="10" maxlength="10"/></div></td>
          </tr>
          <tr>
            <td class="2-td2-left"><div align="right">税种</div></td>
            <td  class="2-td2-left">
              <div align="left">
                <html:select property="szdm" onchange="addFilterWithNull(this.value,document.forms[0].szsmdm,2,arySelect_smlist);">
                  <option></option>
                </html:select>
              </div>
            </td>
            <td  class="2-td2-left"> <div align="right">税目</div></td>
            <td  class="2-td2-center">
              <div align="left">
                <html:select property="szsmdm" style="width:160px">
                </html:select>
              </div>
            </td>
          </tr>
          <tr>
            <td  class="2-td2-left"><div align="right">国家标准行业</div></td>
            <td  class="2-td2-left">
              <div align="left">
                <html:select property="gjbzhydm">
                  <option></option>
                  <html:options collection="gjbzhy" property="gjbzhydm" labelProperty="optionText"/>
                </html:select>
              </div>
            </td>
            <td  class="2-td2-left"><div align="right">银行</div></td>
            <td  class="2-td2-center">
              <div align="left">
                <html:select property="yhdm">
                  <option></option>
                  <html:options collection="yh" property="yhdm" labelProperty="optionText"/>
                </html:select>
              </div>
            </td>
          </tr>
          <tr>
            <td  class="2-td2-left"><div align="right">计算机代码</div></td>
            <td  class="2-td2-left"><div align="left"> <html:text property="jsjdm" size="10" maxlength="8"/></div></td>
            <td  class="2-td2-left"><div align="right">缴款凭证号</div></td>
            <td  class="2-td2-center"><div align="left"> <html:text property="jkpzh" size="20" maxlength="20"/></div></td>
          </tr>
          <tr>
            <td  class="2-td2-left"><div align="right">税票号码</div></td>
            <td  class="2-td2-left"><div align="left"> <html:text property="sphm" size="18" maxlength="18"/></div></td>
            <td  class="2-td2-left"><div align="right">&nbsp;</div></td>
            <td  class="2-td2-center"><div align="left">&nbsp;</div></td>
          </tr>
          <tr>
            <td  class="2-td2-left"><div align="right">税款类型</div></td>
            <td  class="2-td2-left">
              <div align="left">
                <html:select property="sklxdm">
                  <option></option>
                  <html:options collection="sklx" property="value" labelProperty="label"/>
                </html:select>
              </div>
            </td>
            <td  class="2-td2-left"><div align="right">登记注册类型</div></td>
            <td  class="2-td2-center">
              <div align="left">
                <html:select property="djzclxdm">
                  <option></option>
                  <html:options collection="djzclx" property="djzclxdm" labelProperty="optionText"/>
                </html:select>
              </div>
            </td>
          </tr>
          <tr>
            <td  class="2-td2-left"><div align="right">预算科目</div></td>
            <td  class="2-td2-left">
              <div align="left">
                <html:select property="yskmdm">
                  <option></option>
                  <html:options collection="yskm"  property="value" labelProperty="label"/>
                </html:select>
              </div>
            </td>
            <td  class="2-td2-left"><div align="right">预算级次</div></td>
            <td  class="2-td2-center">
              <div align="left">
                <html:select property="ysjcdm">
                  <option></option>
                  <html:options collection="ysjc"  property="value" labelProperty="label"/>
                </html:select>
              </div>
            </td>
          </tr>
          <tr>
            <td  class="2-td2-left"><div align="right">申报方式</div></td>
            <td  class="2-td2-left">
              <div align="left">
                <html:select property="sbfsdm">
                  <option></option>
                  <html:options collection="sbfs"  property="value" labelProperty="label"/>
                </html:select>
              </div>
            </td>
            <td  class="2-td2-left"><div align="right">征收税务机关</div></td>
            <td  class="2-td2-center">
              <div align="left">
                <html:select property="zsswjgdm">
                  <option></option>
                  <html:options collection="zsswjg" property="optionValue" labelProperty="optionText"/>
                </html:select>
              </div>
            </td>
          </tr>
          <tr>
            <td  class="2-td2-left"><div align="right">实缴金额</div></td>
            <td  class="2-td2-left">
              <div align="left">
                <html:select property="op_sjje">
                  <html:option value="=">等于</html:option>
                  <html:option value="<>">不等于</html:option>
                  <html:option value=">">大于</html:option>
                  <html:option value=">=">大于等于</html:option>
                  <html:option value="<">小于</html:option>
                  <html:option value="<=">小于等于</html:option>
                </html:select>
                <input name="sjje" type="text" value="<bean:write name="jkscxForm" property="sjje"/>" id="sjje" size="16" onchange="return(checkvalue(this,1)&&formatCurrency(this,1));" class="inputalignright" tabIndex="2">
              </div>
            </td>
            <td  class="2-td2-left"><div align="right">计税金额</div></td>
            <td  class="2-td2-center">
              <div align="left">
                <html:select property="op_jsje">
                  <html:option value="=">等于</html:option>
                  <html:option value="<>">不等于</html:option>
                  <html:option value=">">大于</html:option>
                  <html:option value=">=">大于等于</html:option>
                  <html:option value="<">小于</html:option>
                  <html:option value="<=">小于等于</html:option>
                </html:select>
                <input name="jsje" type="text" value="<bean:write name="jkscxForm" property="jsje"/>" id="jsje" size="16" onchange="return(checkvalue(this,1)&&formatCurrency(this,1));" class="inputalignright" tabIndex="2">
              </div>
            </td>
          </tr>
          <tr>
            <td  class="2-td2-left"><div align="right">主表实缴金额</div></td>
            <td  class="2-td2-left">
              <div align="left">
                <html:select property="op_zbsjje">
                  <html:option value="=">等于</html:option>
                  <html:option value="<>">不等于</html:option>
                  <html:option value=">">大于</html:option>
                  <html:option value=">=">大于等于</html:option>
                  <html:option value="<">小于</html:option>
                  <html:option value="<=">小于等于</html:option>
                </html:select>
                <input name="zbsjje" type="text" value="<bean:write name="jkscxForm" property="zbsjje"/>" id="zbsjje" size="16" onchange="return(checkvalue(this,1)&&formatCurrency(this,1));" class="inputalignright" tabIndex="2">
              </div>
            </td>
            <td  class="2-td2-left"><div align="right">课税数量</div></td>
            <td  class="2-td2-center">
              <div align="left">
                <html:select property="op_kssl">
                  <html:option value="=">等于</html:option>
                  <html:option value="<>">不等于</html:option>
                  <html:option value=">">大于</html:option>
                  <html:option value=">=">大于等于</html:option>
                  <html:option value="<">小于</html:option>
                  <html:option value="<=">小于等于</html:option>
                </html:select>
                <input name="kssl" type="text" value="<bean:write name="jkscxForm" property="kssl"/>" id="kssl" size="16" onchange="return(checkvalue(this,1)&&formatCurrency(this,1));" class="inputalignright" tabIndex="2">
              </div>
            </td>
            <td  class="2-td2-left"><div align="right"></div></td>
            <td  class="2-td2-left">
              <div align="left">

              </div>
            </td>
          </tr>
          <tr>
            <td colspan="4"  class="2-td2-center">
              <input name="I2" type="image" accesskey="q" value="查询"  onclick='doQuery();return false;' onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="查询" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun">
            </td>
          </tr>
        </table><br>
        <table width="100%" border="0" cellspacing="0" align="center" >
          <tr>
            <td width="43%" ><hr width="100%" class="hr1" size=1></td>
            <td width="14%" align="left" class="black9" >
              <div align="center"><strong>查询结果列表</div>
            </td>
            <td width="43%" ><hr width="100%" class="hr1" size=1></td>
          </tr>
        </table><br>
        <div align="right">
          (第<bean:write name="jkscxForm" property="nextPage"/>页/共<bean:write name="jkscxForm" property="totalpage"/>页)
          <!--翻页功能开始-->
          <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('first');return false;"><img name="beginpage" value="第一页" alt="第一页" onMouseOver="MM_swapImage('diyiye','','<%=static_contextpath%>images/diyiye2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/diyiye1.jpg" width="79" height="22" id="diyiye"></a>
          <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('previous');return false;"><img name="frontpage" value="前页" alt="前页" onMouseOver="MM_swapImage('shangyiye','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/shangyiye1.jpg" width="79" height="22" id="shangyiye"></a>
          <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('next');return false;"><img name="backpage" value="后页" alt="后页" onMouseOver="MM_swapImage('xiayiye','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/xaiyiye1.jpg"  width="79" height="22" id="xiayiye"></a>
          <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('last');return false;"><img name="endpage" value="最后一页" alt="最后一页" onMouseOver="MM_swapImage('zuimoye','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/zuimoye1.jpg" width="79" height="22" id="zuimoye"></a>&nbsp;&nbsp;&nbsp;&nbsp;
          <!--翻页功能结束-->
        </div>
        <table width="100%" border="0" cellspacing="0" class="table-99" align="center" >
          <tr>
            <td class='2-td1-left'>序号</td>
            <td class='2-td1-left'>计算机代码</td>
            <td class='2-td1-left'>缴款凭证号</td>
            <td class='2-td1-left'>税票号码</td>
            <td class='2-td1-left'>税款类型</td>
            <td class='2-td1-left'>申报方式</td>
            <td class='2-td1-left'>银行</td>
            <td class='2-td1-left'>登记注册类型</td>
            <td class='2-td1-left'>主管税务机关</td>
            <td class='2-td1-left'>征收机关</td>
            <td class='2-td1-left'>国家标准行业</td>
            <td class='2-td1-left'>预算科目</td>
            <td class='2-td1-left'>预算级次</td>
            <td class='2-td1-left'>税种</td>
            <td class='2-td1-left'>实缴金额</td>
            <td class='2-td1-left'>税目</td>
            <td class='2-td1-left'>课税数量</td>
            <td class='2-td1-left'>计税金额</td>
            <td class='2-td1-left'>实缴税额</td>
            <td class='2-td1-left'>申报日期</td>
            <td class='2-td1-left'>税款所属开始日期</td>
            <td class='2-td1-left'>税款所属结束日期</td>
            <td class='2-td1-left'>纳税人名称</td>
            <td class='2-td1-center'>入库标识</td>
          </tr>
          <logic:iterate id="item" name="jkscxForm" property="dataList" >
          <tr>
            <td class="2-td2-left" align="center"><bean:write name='item' property='index'/></td>
            <td class="2-td2-left" align="center"><bean:write name='item' property='jsjdm'/></td>
            <td class="2-td2-left" align="center"><bean:write name='item' property='jkpzh'/></td>
            <td class="2-td2-left" align="center"><a href="#" onclick="doViewMx('<bean:write name='item' property='sphm'/>');return false;"><bean:write name='item' property='sphm'/></a></td>
            <td class="2-td2-left" align="center">&nbsp;<bean:write name='item' property='sklx'/></td>
            <td class="2-td2-left" align="center">&nbsp;<bean:write name='item' property='sbfs'/></td>
            <td class="2-td2-left" align="center">&nbsp;<bean:write name='item' property='yh'/></td>
            <td class="2-td2-left" align="center">&nbsp;<bean:write name='item' property='djzclx'/></td>
            <td class="2-td2-left" align="center">&nbsp;<bean:write name='item' property='zgswjg'/></td>
            <td class="2-td2-left" align="center">&nbsp;<bean:write name='item' property='zsswjg'/></td>
            <td class="2-td2-left" align="center">&nbsp;<bean:write name='item' property='gjbzhy'/></td>
            <td class="2-td2-left" align="center">&nbsp;<bean:write name='item' property='yskmmc'/></td>
            <td class="2-td2-left" align="center">&nbsp;<bean:write name='item' property='ysjcmc'/></td>
            <td class="2-td2-left" align="center"><bean:write name='item' property='szmc'/></td>
            <td class="2-td2-left" align="center">&nbsp;<bean:write name='item' property='zbsjje'/></td>
            <td class="2-td2-left" align="center">&nbsp;<bean:write name='item' property='szsmmc'/></td>
            <td class="2-td2-left" align="center">&nbsp;<bean:write name='item' property='kssl'/></td>
            <td class="2-td2-left" align="center">&nbsp;<bean:write name='item' property='jsje'/></td>
            <td class="2-td2-left" align="center">&nbsp;<bean:write name='item' property='sjje'/></td>
            <td class="2-td2-left" align="center"><bean:write name='item' property='sbrq'/></td>
            <td class="2-td2-left" align="center">&nbsp;<bean:write name='item' property='skssksrq'/></td>
            <td class="2-td2-left" align="center">&nbsp;<bean:write name='item' property='skssjsrq'/></td>
            <td class="2-td2-left" align="center">&nbsp;<bean:write name='item' property='nsrmc'/></td>
            <td class="2-td2-center" align="center"><bean:write name='item' property='rkbs'/></td>
          </tr>
          </logic:iterate>
        </table>
        <br>
        <table width="100%" border="0" cellspacing="0" class="table-99" align="center">
          <tr>
            <td class='2-td1-center' colspan="17">
              <b>缴款书明细信息</b>
            </td>
          <tr>
            <td class='2-td1-left'>税款类型名称</td>
            <td class='2-td1-left'>申报编号</td>
            <td class='2-td1-left'>纳税人名称</td>
            <td class='2-td1-left'>税票号码</td>
            <td class='2-td1-left'>计算机代码</td>
            <td class='2-td1-left'>银行</td>
            <td class='2-td1-left'>帐号</td>
            <td class='2-td1-left'>征收机关</td>
            <td class='2-td1-left'>申报日期</td>
            <td class='2-td1-left'>实缴金额</td>
          </tr>
          <logic:iterate id="item" name="jkscxForm" property="mxList" >
          <tr>
            <td class='2-td2-left'><bean:write name='item' property='sklxmc'/></td>
            <td class='2-td2-left'><bean:write name='item' property='sbbh'/></td>
            <td class='2-td2-left'><bean:write name='item' property='nsrmc'/></td>
            <td class='2-td2-left'><bean:write name='item' property='sphm'/></td>
            <td class='2-td2-left'><bean:write name='item' property='jsjdm'/></td>
            <td class='2-td2-left'><bean:write name='item' property='yhmc'/></td>
            <td class='2-td2-left'><bean:write name='item' property='zh'/></td>
            <td class='2-td2-left'><bean:write name='item' property='swjgzzjgmc'/></td>
            <td class='2-td2-left'><bean:write name='item' property='sbrq'/></td>
            <td class='2-td2-center'><bean:write name='item' property='sjjexx'/></td>
          </tr>
          </logic:iterate>
        </table>
        <br>
        <table width="94%" border="0" cellpadding="0" cellspacing="4">
          <tr valign="bottom">
            <td width="30%">
              <input name="toexcel" type="image" accesskey="s"  onClick="doExport();return false;"  onMouseOver="MM_swapImage('toexcel1','','<%=static_contextpath%>images/b-bcdexcel2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="保存到Excel" id="toexcel1" src="<%=static_contextpath%>images/b-bcdexcel1.jpg" width="100" height="22">
            </td>
            <td width="40%" align="center">
              <input type="image" accesskey="c"   onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="\u9000\u51FA" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="21">
            </td>
            <td width="30%"></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table width="99%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td> <hr width="100%" size="1" > </td>
      <td width="99" align="center" class="black9">
        <strong><font color="#0000FF">注 意 事 项</font></strong></td>
      <td> <hr width="100%" size="1" >
      </td>
    </tr>
  </table>
  <table width="99%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
    <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
      <td height="47">
        &nbsp;&nbsp;1.计算机代码格式：8位数字或字母；<br>
        &nbsp;&nbsp;2.ＸＸ时间的录入格式为“YYYYMMDD” 例如，20030312<br>
        &nbsp;&nbsp;3.申报日期起一定要早于申报日期止；<br>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p></td>
</tr>
</table>
<br>
<br>
<%@ include file="./include/footer.jsp"%>
</td>
</tr>
</table>
</html:form>
<script language="JavaScript">
function doViewMx(sphm)
{
  document.forms[0].mxsphm.value = sphm;
  doSubmitForm("ViewMx");
}

function doQuery(ope){
  if(document.forms[0].sbrqq.value==""||document.forms[0].sbrqz.value==""){
    alert("查询条件中申报日期起止日期不能为空！");
    return false;
  }
  if(!isFullDate1(document.forms[0].sbrqq.value,0,null,false)
  && !isFullDate1(document.forms[0].sbrqz.value,0,null,false)){
    alert("查询条件中申报日期起止日期格式不正确！");
    return false;
  }

  doSubmitForm("Query");
}

function doExport(){
  if(!window.confirm("确定要导出Excel查询结果文件？")){
    return false;
  }
  doSubmitForm("Export");
}

function isFullDate1(strDate,dateKind,empty,isThrow){
  var year,mon,days;
  var err = "";
  if(dateKind==0){//检验8位年月日
    err = "";

    if(strDate.length==0){
      if(empty!=null){
        err="日期必须不为空!\n";
      }
    }else{
      if(strDate.length!=8){
        err="日期格式不正确，必须是8位数字!\n";
      }else{
        var strYear = strDate.substr(0,4);
        var strMonth = strDate.substr(4,2);
        var strDay = strDate.substr(6,2);
        var objDate = new Date(strMonth+'-'+strDay+'-'+strYear);
        if (isNaN(objDate)){
          err="日期格式不正确!\n"
        }
        if(strYear*1<1900) {
          err="日期格式不正确!\n"
        }
        if ((strYear*1 != objDate.getYear()*1)&&(strYear*1 != objDate.getYear()*1+1900)) {
          err="日期格式不正确!\n"
        }
        if (strMonth*1 != objDate.getMonth()*1+1){
          err="日期格式不正确!\n"
        }
        if (strDay*1 != objDate.getDate()*1) {
          err="日期格式不正确!\n"
        } //don't call getDay function.
      }
    }
  }else if(dateKind==1){//4位年
    err = "";
    if((strDate.charAt(0)=="0") || strDate.length!=4 || (!isDigit(strDate))){
      err = "不是合法的年份！";
    }
  }

  if(err!=""){
    if(isThrow){
      alert(err);
    }
    return false;
  }
  return true;
}

//\u8FDB\u5165\u9875\u9762\u65F6\u5C06\u7126\u70B9\u8BBE\u4E3A\u8BA1\u7B97\u673A\u4EE3\u7801\u5F55\u5165
// \u9875\u9762\u8FDB\u5165\u7126\u70B9\u786E\u5B9A
function fnOnLoad()
{
  document.forms[0].sbrqq.focus();
  if(document.forms[0].message.value!=""){
    alert(document.forms[0].message.value);
  }

  //\u8BBE\u7F6E\u7A0E\u52A1\u673A\u6784\u7EC4\u7EC7\u4EE3\u7801\u9ED8\u8BA4\u503C
  _addOptionsToSelect(document.forms[0].swjgdm,arySelect_swjgzzjgdm_ju);
  _selectOptionByValue(document.forms[0].swjgdm,"<%=swjgdm_default%>");
  addFilterWithNull(document.forms[0].swjgdm.value,document.forms[0].swsdm,2,arySelect_swjgzzjg_suo);
  // \u8BBE\u7F6E\u7A0E\u52A1\u5C40\u9ED8\u8BA4\u663E\u793A\u7A7A\u767D
  //document.forms[0].swjgdm.options.selectedIndex = 0;
  _selectOptionByValue(document.forms[0].swsdm,"<%=swsdm_default%>");
  //\u8BBE\u7F6E\u7A0E\u79CD\u4EE3\u7801\u9ED8\u8BA4\u503C
  _addOptionsToSelect(document.forms[0].szdm,arySelect_szlist);
  _selectOptionByValue(document.forms[0].szdm,"<%=szdm_default%>");
  addFilterWithNull(document.forms[0].szdm.value,document.forms[0].szsmdm,2,arySelect_smlist);
  // \u8BBE\u7F6E\u7A0E\u79CD\u9ED8\u8BA4\u663E\u793A\u7A7A\u767D
  //document.forms[0].szdm.options.selectedIndex = 0;
  _selectOptionByValue(document.forms[0].szsmdm,"<%=smdm_default%>");
}


function fn_ChangePage(type)
{
  //\u83B7\u53D6\u4E0A\u4E00\u6B21\u64CD\u4F5C\u7C7B\u578B
  temp=document.forms[0].actionType.value;
  //\u8BBE\u7F6E\u5F53\u524D\u64CD\u4F5C\u7C7B\u578B
  if(temp=="Query"||temp=="ChangePage"||temp=="Export"){
    temp="ChangePage";
  }else{
    temp="Show";
  }
  //
  var tmpNextPage=document.forms[0].nextPage.value*1;
  var tmpTotalPage=document.forms[0].totalpage.value*1;
  //alert("tmpNextPage:"+tmpNextPage+"|"+"tmpTotalPage:"+tmpTotalPage+"|"+"type:"+type);
  if(temp=="Show"){
    alert("没有任何已查询数据,请先查询...");
    return false;
  }
  if (type == "first")
  {
    if(tmpNextPage==1){
      alert("本次查询已到达第一页,请返回...");
      return false;
    }else{
      document.forms[0].nextPage.value="1";
    }
  }
  else if (type == "previous")
  {
    if(tmpNextPage==1){
      alert("本次查询已到达第一页,请返回...");
      return false;
    }else{
      document.forms[0].nextPage.value =(tmpNextPage-1)+"";
    }
  }
  else if (type == "next")
  {
    if(tmpNextPage>=tmpTotalPage){
      alert("本次查询已到达最后一页,请返回...");
      return false;
    }else{
      document.forms[0].nextPage.value =(tmpNextPage+1)+"";
    }
  }
  else if (type == "last")
  {
    if(tmpTotalPage==1){
      alert("本次查询只存在一页,请返回...");
      return false;
    }else if(tmpNextPage>=tmpTotalPage){
      alert("本次查询已到达最后一页,请返回...");
      return false;
    }
    else{
      document.forms[0].nextPage.value=document.forms[0].totalpage.value;
    }
  }
  //提交查询
  doSubmitForm(temp);
  return false;
}

//判断
//参数 obj 需要检验值的对象
//参数 par
//			0 -- 如果值不合法则改为0
//			1 -- 不校验是否小于0
//			2 -- 不能大于100
//			3 -- 不能大于1
//注：trim()函数已包含在公用函数库中
function checkvalue(obj,par)
{
  var tmpValue = trim(obj.value);
  var tmp = "";//tmpValue == "" ||
  if( isNaN(tmpValue))
  {
    alert("请输入数字");
    obj.focus();
    obj.select();
    return false;
  }
  return true;
}

// 将字符串格式化为货币格式
// par为0，自动填充0.00
// par为1，不自动填充
function formatCurrency(obj,par)
{
  var tmpValue = trim(obj.value);
  var pointIndex = tmpValue.indexOf(".");
  if(pointIndex < 0)
  {
    if(tmpValue == null || tmpValue == "" || tmpValue == ".")
    {
      switch (par)
      {
        case 0:
        tmpValue = "0.00";
        break;
        case 1:
        tmpValue = "";
        break;
        default:
        break;
      }
    }
    else
    {
      tmpValue += ".00";
    }
  }
  else if(pointIndex == 0)
  {
    if(tmpValue.length > 1)
    {
      if(tmpValue.substring(1).length > 2)
      {
        alert("小数点后不能大于两位！");
        obj.focus();
        obj.select();
        return false;
      }
      else if(tmpValue.substring(1).length == 1)
      {
        tmpValue = "0" + tmpValue + "0";
      }
      else
      {
        tmpValue = "0" + tmpValue;
      }
    }
    else
    {
      switch (par)
      {
        case 0:
        tmpValue = "0.00";
        break;
        case 1:
        tmpValue = "";
        break;
        default:
        break;
      }
    }
  }
  else
  {
    afterpoint = (tmpValue.length-1) - pointIndex;
    if(afterpoint == 0)
    {
      tmpValue += "00";
    }
    else if(afterpoint > 2)
    {
      alert("小数点后不能大于两位！");
      obj.focus();
      obj.select();
      return false;
    }
    else if(afterpoint == 1)
    {
      tmpValue += "0";
    }
  }
  if(tmpValue > 9999999999999.99)
  {
    alert("您输入的数字必须小于10000000000000！");
    obj.focus();
    obj.select();
    return false;
  }
  obj.value = tmpValue;
  //return obj;
  return true;
}


	//\u589E\u52A0\u9009\u9879
/*	  function AddItem(){
	var ln = document.all.queryDjzclx.length;
	var textArr = new Array();
	var valueArr = new Array();
	var textArr1 = new Array();
	var valueArr1 = new Array();

    for(var i = 1 ;i < document.all.queryDjzclx.length; i++){
		textArr1[i] = document.all.queryDjzclx.options[i].text;
		valueArr1[i] = document.all.queryDjzclx.options[i].value;
	}
	for(var j = 1 ; j < document.all.queryDjzclx2.length; j++){
		textArr1[i+j-1] = document.all.queryDjzclx2.options[j].text;
		valueArr1[i+j-1] = document.all.queryDjzclx2.options[j].value;
	}


for (var i = 1; i < document.all.queryDjzclx.length; i++)
{
  textArr[i] = document.all.queryDjzclx.options[i].text;
  valueArr[i] = document.all.queryDjzclx.options[i].value;
  if(document.all.queryDjzclx.options[i].selected==true){
  	var bool = false;
	for(var j = 1 ; j < document.all.queryDjzclx2.length; j++){
		if(valueArr[i] == document.all.queryDjzclx2.options[j].value){
			bool = true;
		}
	}
	if(!bool){
  		document.all.queryDjzclx2.add(new Option(textArr[i],valueArr[i]));
	}
  }
}

	var ln1 = document.all.queryDjzclx.length;
	while (ln1--)
	{
		if(ln1 > 0){
			document.all.queryDjzclx.options[ln1] = null;
		}
	}

	for(var i = 1 ; i < textArr1.length ; i++ ){
	    var bool2 = false;
		for(var j = 1 ;j < document.all.queryDjzclx2.length; j++){
			if(document.all.queryDjzclx2.options[j].value == valueArr1[i]){
			    bool2 = true;
			}
		}
		if(!bool2){
			document.all.queryDjzclx.add(new Option(textArr1[i],valueArr1[i]));
		}
	}

	var tempArry = new Array();
	var tempJ = 0 ;
	for(var i = 1 ; i < document.all.queryDjzclx2.length; i++){
		if(document.all.queryDjzclx2.options[i].value != ""){
			if(document.all.queryDjzclx2.options[i].value != null ){
				tempArry[tempJ] = document.all.queryDjzclx2.options[i].value;
				tempJ++;
			}
		}
	}

	tempArry.sort();

	var ln11 = document.all.queryDjzclx2.length;
	while(ln11--){
		if(ln11 > 0){
			document.all.queryDjzclx2.options[ln11] = null;
		}
	}

	for(var i = 0 ; i < tempArry.length; i++){
		document.all.queryDjzclx2.add(new Option(tempArry[i],tempArry[i]));
	}
}
	//\u51CF\u5C11\u9009\u9879
	  function DeleteItem(){
	var ln = document.all.queryDjzclx2.length;
	var textArr = new Array();
	var valueArr = new Array();
	var textArr1 = new Array();
	var valueArr1 = new Array();

    for(var i = 1 ;i < document.all.queryDjzclx2.length; i++){
		textArr1[i] = document.all.queryDjzclx2.options[i].text;
		valueArr1[i] = document.all.queryDjzclx2.options[i].value;
	}
	for(var j = 1 ; j < document.all.queryDjzclx.length; j++){
		textArr1[i+j-1] = document.all.queryDjzclx.options[j].text;
		valueArr1[i+j-1] = document.all.queryDjzclx.options[j].value;
	}


for (var i = 1; i < document.all.queryDjzclx2.length; i++)
{
  textArr[i] = document.all.queryDjzclx2.options[i].text;
  valueArr[i] = document.all.queryDjzclx2.options[i].value;
  if(document.all.queryDjzclx2.options[i].selected==true){
  	var bool = false;
	for(var j = 1 ; j < document.all.queryDjzclx.length; j++){
		if(valueArr[i] == document.all.queryDjzclx.options[j].value){
			bool = true;
		}
	}
	if(!bool){
  		document.all.queryDjzclx.add(new Option(textArr[i],valueArr[i]));
	}
  }
}

	var ln1 = document.all.queryDjzclx2.length;
	while (ln1--)
	{
		if(ln1 > 0){
			document.all.queryDjzclx2.options[ln1] = null;
		}
	}

	for(var i = 1 ; i < textArr1.length ; i++ ){
	    var bool2 = false;
		for(var j = 1 ;j < document.all.queryDjzclx.length; j++){
			if(document.all.queryDjzclx.options[j].value == valueArr1[i]){
			    bool2 = true;
			}
		}
		if(!bool2){
			document.all.queryDjzclx2.add(new Option(textArr1[i],valueArr1[i]));
		}
	}

	var tempArry = new Array();
	var tempJ = 0 ;
	for(var i = 1 ; i < document.all.queryDjzclx.length; i++){
		if(document.all.queryDjzclx.options[i].value != ""){
			if(document.all.queryDjzclx.options[i].value != null){
				tempArry[tempJ] = document.all.queryDjzclx.options[i].value;
				tempJ++;
			}
		}
	}

	tempArry.sort();

	var ln11 = document.all.queryDjzclx.length;
	while(ln11--){
		if(ln11 > 0){
			document.all.queryDjzclx.options[ln11] = null;
		}
	}

	for(var i = 0 ; i < tempArry.length; i++){
		document.all.queryDjzclx.add(new Option(tempArry[i],tempArry[i]));
	}
}
*/
</script>
</body>
</html:html>
