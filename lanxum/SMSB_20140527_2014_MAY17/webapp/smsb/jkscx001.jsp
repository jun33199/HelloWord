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

    //˰����ش���
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
    // ����˰�����
  var arySelect_swjgzzjgdm_ju=<%=JspUtil.displaySelectDataSource(pf.getZgswjg().values(),false)%>;
// ����˰����
  var arySelect_swjgzzjg_suo=<%=JspUtil.displaySelectDataSource(pf.getZgsws().values(),false)%>;

 // ����˰�����
  var arySelect_szlist=<%=com.ttsoft.bjtax.smsb.util.JspUtil.displaySelectDataSource(pf.getSzList(),false)%>;
// ����˰����
  var arySelect_smlist=<%=com.ttsoft.bjtax.smsb.util.JspUtil.displaySelectDataSource(pf.getSmList(),false)%>;


</script>

<html:html>
<head>
<title>�걨�ɿ�����ϸ��ѯ </title>
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
      <td class="1-td1"  colspan="7">�ɿ�����ϸ��ѯ </td>
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
            <td width="20%" class="2-td2-t-left"><div align="right">����˰�����</div></td>
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
            <td width="20%" class="2-td2-t-left"><div align="right">����˰����</div></td>
            <td width="30%" class="2-td2-t-center">
              <div align="left">
                <html:select property="swsdm" style="width:160px">
                </html:select>
              </div>
            </td>
          </tr>
          <tr>
            <td  class="2-td2-left"><div align="right">�걨������</div></td>
            <td class="2-td2-left"> <div align="left"><html:text property="sbrqq" size="10" maxlength="10"/></div></td>
            <td  class="2-td2-left"><div align="right">�걨����ֹ</div></td>
            <td  class="2-td2-center"> <div align="left"><html:text property="sbrqz" size="10" maxlength="10"/></div></td>
          </tr>
          <tr>
            <td class="2-td2-left"><div align="right">˰��</div></td>
            <td  class="2-td2-left">
              <div align="left">
                <html:select property="szdm" onchange="addFilterWithNull(this.value,document.forms[0].szsmdm,2,arySelect_smlist);">
                  <option></option>
                </html:select>
              </div>
            </td>
            <td  class="2-td2-left"> <div align="right">˰Ŀ</div></td>
            <td  class="2-td2-center">
              <div align="left">
                <html:select property="szsmdm" style="width:160px">
                </html:select>
              </div>
            </td>
          </tr>
          <tr>
            <td  class="2-td2-left"><div align="right">���ұ�׼��ҵ</div></td>
            <td  class="2-td2-left">
              <div align="left">
                <html:select property="gjbzhydm">
                  <option></option>
                  <html:options collection="gjbzhy" property="gjbzhydm" labelProperty="optionText"/>
                </html:select>
              </div>
            </td>
            <td  class="2-td2-left"><div align="right">����</div></td>
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
            <td  class="2-td2-left"><div align="right">���������</div></td>
            <td  class="2-td2-left"><div align="left"> <html:text property="jsjdm" size="10" maxlength="8"/></div></td>
            <td  class="2-td2-left"><div align="right">�ɿ�ƾ֤��</div></td>
            <td  class="2-td2-center"><div align="left"> <html:text property="jkpzh" size="20" maxlength="20"/></div></td>
          </tr>
          <tr>
            <td  class="2-td2-left"><div align="right">˰Ʊ����</div></td>
            <td  class="2-td2-left"><div align="left"> <html:text property="sphm" size="18" maxlength="18"/></div></td>
            <td  class="2-td2-left"><div align="right">&nbsp;</div></td>
            <td  class="2-td2-center"><div align="left">&nbsp;</div></td>
          </tr>
          <tr>
            <td  class="2-td2-left"><div align="right">˰������</div></td>
            <td  class="2-td2-left">
              <div align="left">
                <html:select property="sklxdm">
                  <option></option>
                  <html:options collection="sklx" property="value" labelProperty="label"/>
                </html:select>
              </div>
            </td>
            <td  class="2-td2-left"><div align="right">�Ǽ�ע������</div></td>
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
            <td  class="2-td2-left"><div align="right">Ԥ���Ŀ</div></td>
            <td  class="2-td2-left">
              <div align="left">
                <html:select property="yskmdm">
                  <option></option>
                  <html:options collection="yskm"  property="value" labelProperty="label"/>
                </html:select>
              </div>
            </td>
            <td  class="2-td2-left"><div align="right">Ԥ�㼶��</div></td>
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
            <td  class="2-td2-left"><div align="right">�걨��ʽ</div></td>
            <td  class="2-td2-left">
              <div align="left">
                <html:select property="sbfsdm">
                  <option></option>
                  <html:options collection="sbfs"  property="value" labelProperty="label"/>
                </html:select>
              </div>
            </td>
            <td  class="2-td2-left"><div align="right">����˰�����</div></td>
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
            <td  class="2-td2-left"><div align="right">ʵ�ɽ��</div></td>
            <td  class="2-td2-left">
              <div align="left">
                <html:select property="op_sjje">
                  <html:option value="=">����</html:option>
                  <html:option value="<>">������</html:option>
                  <html:option value=">">����</html:option>
                  <html:option value=">=">���ڵ���</html:option>
                  <html:option value="<">С��</html:option>
                  <html:option value="<=">С�ڵ���</html:option>
                </html:select>
                <input name="sjje" type="text" value="<bean:write name="jkscxForm" property="sjje"/>" id="sjje" size="16" onchange="return(checkvalue(this,1)&&formatCurrency(this,1));" class="inputalignright" tabIndex="2">
              </div>
            </td>
            <td  class="2-td2-left"><div align="right">��˰���</div></td>
            <td  class="2-td2-center">
              <div align="left">
                <html:select property="op_jsje">
                  <html:option value="=">����</html:option>
                  <html:option value="<>">������</html:option>
                  <html:option value=">">����</html:option>
                  <html:option value=">=">���ڵ���</html:option>
                  <html:option value="<">С��</html:option>
                  <html:option value="<=">С�ڵ���</html:option>
                </html:select>
                <input name="jsje" type="text" value="<bean:write name="jkscxForm" property="jsje"/>" id="jsje" size="16" onchange="return(checkvalue(this,1)&&formatCurrency(this,1));" class="inputalignright" tabIndex="2">
              </div>
            </td>
          </tr>
          <tr>
            <td  class="2-td2-left"><div align="right">����ʵ�ɽ��</div></td>
            <td  class="2-td2-left">
              <div align="left">
                <html:select property="op_zbsjje">
                  <html:option value="=">����</html:option>
                  <html:option value="<>">������</html:option>
                  <html:option value=">">����</html:option>
                  <html:option value=">=">���ڵ���</html:option>
                  <html:option value="<">С��</html:option>
                  <html:option value="<=">С�ڵ���</html:option>
                </html:select>
                <input name="zbsjje" type="text" value="<bean:write name="jkscxForm" property="zbsjje"/>" id="zbsjje" size="16" onchange="return(checkvalue(this,1)&&formatCurrency(this,1));" class="inputalignright" tabIndex="2">
              </div>
            </td>
            <td  class="2-td2-left"><div align="right">��˰����</div></td>
            <td  class="2-td2-center">
              <div align="left">
                <html:select property="op_kssl">
                  <html:option value="=">����</html:option>
                  <html:option value="<>">������</html:option>
                  <html:option value=">">����</html:option>
                  <html:option value=">=">���ڵ���</html:option>
                  <html:option value="<">С��</html:option>
                  <html:option value="<=">С�ڵ���</html:option>
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
              <input name="I2" type="image" accesskey="q" value="��ѯ"  onclick='doQuery();return false;' onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="��ѯ" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun">
            </td>
          </tr>
        </table><br>
        <table width="100%" border="0" cellspacing="0" align="center" >
          <tr>
            <td width="43%" ><hr width="100%" class="hr1" size=1></td>
            <td width="14%" align="left" class="black9" >
              <div align="center"><strong>��ѯ����б�</div>
            </td>
            <td width="43%" ><hr width="100%" class="hr1" size=1></td>
          </tr>
        </table><br>
        <div align="right">
          (��<bean:write name="jkscxForm" property="nextPage"/>ҳ/��<bean:write name="jkscxForm" property="totalpage"/>ҳ)
          <!--��ҳ���ܿ�ʼ-->
          <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('first');return false;"><img name="beginpage" value="��һҳ" alt="��һҳ" onMouseOver="MM_swapImage('diyiye','','<%=static_contextpath%>images/diyiye2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/diyiye1.jpg" width="79" height="22" id="diyiye"></a>
          <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('previous');return false;"><img name="frontpage" value="ǰҳ" alt="ǰҳ" onMouseOver="MM_swapImage('shangyiye','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/shangyiye1.jpg" width="79" height="22" id="shangyiye"></a>
          <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('next');return false;"><img name="backpage" value="��ҳ" alt="��ҳ" onMouseOver="MM_swapImage('xiayiye','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/xaiyiye1.jpg"  width="79" height="22" id="xiayiye"></a>
          <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('last');return false;"><img name="endpage" value="���һҳ" alt="���һҳ" onMouseOver="MM_swapImage('zuimoye','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/zuimoye1.jpg" width="79" height="22" id="zuimoye"></a>&nbsp;&nbsp;&nbsp;&nbsp;
          <!--��ҳ���ܽ���-->
        </div>
        <table width="100%" border="0" cellspacing="0" class="table-99" align="center" >
          <tr>
            <td class='2-td1-left'>���</td>
            <td class='2-td1-left'>���������</td>
            <td class='2-td1-left'>�ɿ�ƾ֤��</td>
            <td class='2-td1-left'>˰Ʊ����</td>
            <td class='2-td1-left'>˰������</td>
            <td class='2-td1-left'>�걨��ʽ</td>
            <td class='2-td1-left'>����</td>
            <td class='2-td1-left'>�Ǽ�ע������</td>
            <td class='2-td1-left'>����˰�����</td>
            <td class='2-td1-left'>���ջ���</td>
            <td class='2-td1-left'>���ұ�׼��ҵ</td>
            <td class='2-td1-left'>Ԥ���Ŀ</td>
            <td class='2-td1-left'>Ԥ�㼶��</td>
            <td class='2-td1-left'>˰��</td>
            <td class='2-td1-left'>ʵ�ɽ��</td>
            <td class='2-td1-left'>˰Ŀ</td>
            <td class='2-td1-left'>��˰����</td>
            <td class='2-td1-left'>��˰���</td>
            <td class='2-td1-left'>ʵ��˰��</td>
            <td class='2-td1-left'>�걨����</td>
            <td class='2-td1-left'>˰��������ʼ����</td>
            <td class='2-td1-left'>˰��������������</td>
            <td class='2-td1-left'>��˰������</td>
            <td class='2-td1-center'>����ʶ</td>
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
              <b>�ɿ�����ϸ��Ϣ</b>
            </td>
          <tr>
            <td class='2-td1-left'>˰����������</td>
            <td class='2-td1-left'>�걨���</td>
            <td class='2-td1-left'>��˰������</td>
            <td class='2-td1-left'>˰Ʊ����</td>
            <td class='2-td1-left'>���������</td>
            <td class='2-td1-left'>����</td>
            <td class='2-td1-left'>�ʺ�</td>
            <td class='2-td1-left'>���ջ���</td>
            <td class='2-td1-left'>�걨����</td>
            <td class='2-td1-left'>ʵ�ɽ��</td>
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
              <input name="toexcel" type="image" accesskey="s"  onClick="doExport();return false;"  onMouseOver="MM_swapImage('toexcel1','','<%=static_contextpath%>images/b-bcdexcel2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="���浽Excel" id="toexcel1" src="<%=static_contextpath%>images/b-bcdexcel1.jpg" width="100" height="22">
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
        <strong><font color="#0000FF">ע �� �� ��</font></strong></td>
      <td> <hr width="100%" size="1" >
      </td>
    </tr>
  </table>
  <table width="99%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
    <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
      <td height="47">
        &nbsp;&nbsp;1.����������ʽ��8λ���ֻ���ĸ��<br>
        &nbsp;&nbsp;2.�أ�ʱ���¼���ʽΪ��YYYYMMDD�� ���磬20030312<br>
        &nbsp;&nbsp;3.�걨������һ��Ҫ�����걨����ֹ��<br>
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
    alert("��ѯ�������걨������ֹ���ڲ���Ϊ�գ�");
    return false;
  }
  if(!isFullDate1(document.forms[0].sbrqq.value,0,null,false)
  && !isFullDate1(document.forms[0].sbrqz.value,0,null,false)){
    alert("��ѯ�������걨������ֹ���ڸ�ʽ����ȷ��");
    return false;
  }

  doSubmitForm("Query");
}

function doExport(){
  if(!window.confirm("ȷ��Ҫ����Excel��ѯ����ļ���")){
    return false;
  }
  doSubmitForm("Export");
}

function isFullDate1(strDate,dateKind,empty,isThrow){
  var year,mon,days;
  var err = "";
  if(dateKind==0){//����8λ������
    err = "";

    if(strDate.length==0){
      if(empty!=null){
        err="���ڱ��벻Ϊ��!\n";
      }
    }else{
      if(strDate.length!=8){
        err="���ڸ�ʽ����ȷ��������8λ����!\n";
      }else{
        var strYear = strDate.substr(0,4);
        var strMonth = strDate.substr(4,2);
        var strDay = strDate.substr(6,2);
        var objDate = new Date(strMonth+'-'+strDay+'-'+strYear);
        if (isNaN(objDate)){
          err="���ڸ�ʽ����ȷ!\n"
        }
        if(strYear*1<1900) {
          err="���ڸ�ʽ����ȷ!\n"
        }
        if ((strYear*1 != objDate.getYear()*1)&&(strYear*1 != objDate.getYear()*1+1900)) {
          err="���ڸ�ʽ����ȷ!\n"
        }
        if (strMonth*1 != objDate.getMonth()*1+1){
          err="���ڸ�ʽ����ȷ!\n"
        }
        if (strDay*1 != objDate.getDate()*1) {
          err="���ڸ�ʽ����ȷ!\n"
        } //don't call getDay function.
      }
    }
  }else if(dateKind==1){//4λ��
    err = "";
    if((strDate.charAt(0)=="0") || strDate.length!=4 || (!isDigit(strDate))){
      err = "���ǺϷ�����ݣ�";
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
    alert("û���κ��Ѳ�ѯ����,���Ȳ�ѯ...");
    return false;
  }
  if (type == "first")
  {
    if(tmpNextPage==1){
      alert("���β�ѯ�ѵ����һҳ,�뷵��...");
      return false;
    }else{
      document.forms[0].nextPage.value="1";
    }
  }
  else if (type == "previous")
  {
    if(tmpNextPage==1){
      alert("���β�ѯ�ѵ����һҳ,�뷵��...");
      return false;
    }else{
      document.forms[0].nextPage.value =(tmpNextPage-1)+"";
    }
  }
  else if (type == "next")
  {
    if(tmpNextPage>=tmpTotalPage){
      alert("���β�ѯ�ѵ������һҳ,�뷵��...");
      return false;
    }else{
      document.forms[0].nextPage.value =(tmpNextPage+1)+"";
    }
  }
  else if (type == "last")
  {
    if(tmpTotalPage==1){
      alert("���β�ѯֻ����һҳ,�뷵��...");
      return false;
    }else if(tmpNextPage>=tmpTotalPage){
      alert("���β�ѯ�ѵ������һҳ,�뷵��...");
      return false;
    }
    else{
      document.forms[0].nextPage.value=document.forms[0].totalpage.value;
    }
  }
  //�ύ��ѯ
  doSubmitForm(temp);
  return false;
}

//�ж�
//���� obj ��Ҫ����ֵ�Ķ���
//���� par
//			0 -- ���ֵ���Ϸ����Ϊ0
//			1 -- ��У���Ƿ�С��0
//			2 -- ���ܴ���100
//			3 -- ���ܴ���1
//ע��trim()�����Ѱ����ڹ��ú�������
function checkvalue(obj,par)
{
  var tmpValue = trim(obj.value);
  var tmp = "";//tmpValue == "" ||
  if( isNaN(tmpValue))
  {
    alert("����������");
    obj.focus();
    obj.select();
    return false;
  }
  return true;
}

// ���ַ�����ʽ��Ϊ���Ҹ�ʽ
// parΪ0���Զ����0.00
// parΪ1�����Զ����
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
        alert("С������ܴ�����λ��");
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
      alert("С������ܴ�����λ��");
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
    alert("����������ֱ���С��10000000000000��");
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
