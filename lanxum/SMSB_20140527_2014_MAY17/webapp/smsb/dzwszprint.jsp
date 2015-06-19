<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.sfgl.common.db.util.SfResourceLocator"%>
<%@ page import="com.ttsoft.bjtax.smsb.dzwsz.web.DzwszForm"%>
<%@ page import="com.ttsoft.bjtax.smsb.dzwsz.processor.DzwszBO"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Timestamp" %>

<%

    DzwszForm form = (DzwszForm)request.getAttribute("dzwszForm");
    List boList = form.getBoList();
    DzwszBO bo = form.getBo();
%>

<html>
<head>
<title>����ת��ר����˰֤</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/function.js"></script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="starPrint()">
<%@ include file="./include/header.jsp"%>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<applet name="printer" codebase="<%=static_contextpath%>printer" code="com.ttsoft.bjtax.webprint.SSDZZZZYWSZPrinter" width="0" height="0" archive="vprinter.jar">
</applet>
<html:form action="webapp/smsb/dzwszAction" method="POST">
<html:hidden property="actionType"/>
<html:hidden property="currentPage"/>
<br>
  <table width="96%" align="center" cellspacing="0" class="table-99" id="pauls">
    <tr>
      <td class="1-td1">��ѯ</td>
    </tr>
    <tr valign="top">
      <td class="1-td2">
      <br>
        <table cellspacing="0" border="1" class="table-99" width="98%">
          <tr>
            <td class="3-td1-left" nowrap>��������룺</td>
            <td class="3-td1-left" nowrap>
              <html:text property="jsjdm"/>
            </td>
            <td class="3-td1-left" nowrap>������ˮ�ţ�</td>
            <td class="3-td1-left" nowrap><b>
              <html:text property="jylsh" size="16" maxlength="16"/>
            </b></td>
            <td class="3-td1-left" nowrap>˰Ʊ����<b>��</b></td>
            <td class="3-td1-left" nowrap><b>
              <html:text property="sphm" size="18" maxlength="18"/>
            </b></td>
            <!--<td width="14%"><input type="image" accesskey="k" tabIndex="-1" onclick="hzQuery('2');return false;" style="cursor:hand;" onMouseOver="MM_swapImage('hzsbjks1','','<%=static_contextpath%>images/hzsbjks-k2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="�����걨�ɿ���" id="hzsbjks1" src="<%=static_contextpath%>images/hzsbjks-k1.jpg" width="112" height="22"></td>-->
            <td class="3-td1-centen" nowrap>
              <input type="image" accesskey="z" tabIndex="-1" onclick="doQuery();return false;" style="cursor:hand;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/chaxun2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="��ѯ" id="chaxun" src="<%=static_contextpath%>images/chaxun1.jpg">
                &nbsp;&nbsp;&nbsp;&nbsp;
              <input type="image" accesskey="c" tabIndex="-1" onClick="tuichu();return false;" style="cursor:hand" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" border="0" height="22">
            </td>
          </tr>
        </table>
        <br>
        <%
        if(bo!=null)
        {
        %>
        <table class="table-99" cellspacing="0" width="98%" border="0">
          <tr class="black9">
            <td align="center" colspan="4" class="2-td1">
              <div align="center"><font size="4pt"><b>���ӽ�˰����ƾ֤</b></font></div>
            </td>
          </tr>
          <tr>
            <td width="20%" class="2-td1-center" colspan="4">
              <%
              String sbrq = bo.getSbrq();
              %>
              �ɿ����ڣ�<%=sbrq.substring(0,4)%>��<%=sbrq.substring(4,6)%>��<%=sbrq.substring(6)%>��
            </td>
          </tr>
          <tr>
            <td width="20%" class="2-td2-left" nowrap>
              <div align="center"><strong>����������</strong>&nbsp;&nbsp;</div>
            </td>
            <td width="30%" class="2-td2-left" nowrap>
              <div align="center"><%=bo.getNsrmc()%>&nbsp;</div>
            </td>
            <td width="20%" class="2-td2-left" nowrap>
              <div align="center"><strong>���ջ�������</strong>&nbsp;&nbsp;</div>
            </td>
            <td width="30%" class="2-td2-center" nowrap>
              <div align="center"><%=bo.getSwjgzzjgmc()%>&nbsp;</div>
            </td>
          </tr>
          <tr>
            <td class="2-td2-left">
              <div align="center"><strong>�������ʺ�</strong>&nbsp;&nbsp;</div>
            </td>
            <td class="2-td2-left">
              <div align="center"><%=bo.getZh()%>&nbsp;</div>
            </td>
            <td width="20%" class="2-td2-left">
              <div align="center">&nbsp;&nbsp;</div>
            </td>
            <td width="30%" class="2-td2-center">
              <div align="center">&nbsp;</div>
            </td>
          </tr>
          <tr>
            <td width="20%" class="2-td2-left">
              <div align="center"><strong>�����˿�������</strong>&nbsp;&nbsp;</div>
            </td>
            <td width="30%" class="2-td2-left">
              <div align="center"><%=bo.getYhmc()%>&nbsp;</div>
            </td>
            <td class="2-td2-left">
              <div align="center">&nbsp;&nbsp;</div>
            </td>
            <td class="2-td2-center">
              <div align="center">&nbsp;&nbsp;</div>
            </td>
          </tr>
          <tr>
            <td width="20%" class="2-td2-left" rowspan="2">
              <div align="center"><strong>�ɿ��齻����ˮ��</strong>&nbsp;&nbsp;</div>
            </td>
            <td class="2-td2-left" rowspan="2">
              <div align="center">&nbsp;<%=bo.getJylsh()%>&nbsp;</div>
            </td>
            <td width="20%" class="2-td2-left">
              <div align="center"><strong>Сд���ϼƣ����</strong>&nbsp;&nbsp;</div>
            </td>
            <td width="30%" class="2-td2-center">
              <div align="center"><%=bo.getHjjexx()%>&nbsp;</div>
            </td>
          </tr>
          <tr>
            <td class="2-td2-left">
              <div align="center"><strong>��д���ϼƣ����</strong>&nbsp;&nbsp;</div>
            </td>
            <td class="2-td2-center">
              <div align="center"><%=bo.getHjjedx()%>&nbsp;&nbsp;</div>
            </td>
          </tr>
        </table>
        <br>
        <table class="table-99" cellspacing="0" width="98%" border="1">
          <tr>
            <td class="2-td1-left" nowrap><div align="center">˰���ѣ�������</div></td>
            <td class="2-td1-left" nowrap><div align="center">����ʱ��</div></td>
            <td class="2-td1-center" nowrap><div align="center">ʵ�ɽ��</div></td>
          </tr>
          <%
          List mxList = bo.getSzitem();
          for(int i=0;i<mxList.size();i++)
          {
            Map mxMap = (Map)mxList.get(i);
          %>
          <tr>
            <td class="2-td2-left"><%=mxMap.get("szmc")%>&nbsp;</td>
            <td class="2-td2-left">
              <%
              out.print(mxMap.get("skssksrq")+"-"+mxMap.get("skssjsrq"));
              %>
              &nbsp;
            </td>
            <td class="2-td2-center"><%=mxMap.get("sjje")%>&nbsp;</td>
          </tr>
          <%
          }
          %>
          <tr>
            <td class="2-td2-center" colspan="3">
              <div align="center"><strong>��ӡ����&nbsp;&nbsp;</strong>
                <%
                String dyrqPrint=bo.getDyrq().substring(0,4)+"  ��  "+bo.getDyrq().substring(4,6)+"  ��  "+bo.getDyrq().substring(6,8)+"  ��  ";
                out.print(dyrqPrint);
                %>
              </div>
            </td>
          </tr>
        </table>
        <br>
        <br>
        <table class="table-99" cellspacing="0" width="98%" >
          <tr class="black9">
            <td>
              <%
              if(form.getDy()==null || form.getDy().equals(""))
              {
              %>
              <input type="image" accesskey="z" tabIndex="-1" onclick="doPrint();return false;" style="cursor:hand;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/dayin2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="��ѯ" id="chaxun" src="<%=static_contextpath%>images/dayin1.jpg">
              <%
              }
              %>
            </td>
          </tr>
        </table>
        <table class="table-99" cellspacing="0" width="98%" >
          <tr class="blck9">
            <td align="right">&nbsp;
            </td>
          </tr>
          <tr class="black9">
            <td align="right">&nbsp;
            </td>
          </tr>
        </table>
        <%
        }
        %>
      </td>
    </tr>
  </table>
  <br>
  <table width="98%" align="center" cellspacing="0" valign="bottom">
    <tr align="center">
      <td>
        <%@ include file="./include/footer.jsp"%>
      </td>
    </tr>
  </table>
</html:form>
<SCRIPT LANGUAGE="JavaScript" type="text/JavaScript">
function doPrint(){
  if(confirm("�Ƿ��ӡ����ת��ר����˰֤��"))
  {
    document.forms[0].actionType.value='Print';
    document.forms[0].submit();
  }
}

function doQuery()
{
  if(document.forms[0].jsjdm.value=="")
  {
    alert("�������������룡");
    return false;
  }
  if(document.forms[0].jylsh.value=="" && document.forms[0].sphm.value=="")
  {
    alert("���������뽻����ˮ�Ż�˰Ʊ�����е�һ����Ϊ��ѯ������");
    return false;
  }
  document.forms[0].actionType.value='Query';
  document.forms[0].submit();
}

function starPrint()
{
<%
if(boList!=null && boList.size()>0)
{
  for(int i=0;i<boList.size();i++)
  {
    DzwszBO tmpBo = (DzwszBO)boList.get(i);
    String sbrq = tmpBo.getSbrq();

    String printSzmc = "";
    String printSssq = "";
    String printSjje = "";
    List tmpBoList = tmpBo.getSzitem();
    for (int j = 0; j < tmpBoList.size(); j++) {
      Map tmpMap = (Map)tmpBoList.get(j);
      printSzmc += tmpMap.get("szmc")+";;";
      printSssq += tmpMap.get("skssksrq")+"-"+tmpMap.get("skssjsrq")+";;";
      printSjje += tmpMap.get("sjje")+";;";
    }
    %>
    //����ֱ�
    var ndzb="<%=tmpBo.getNdzb()%>";
    //��˰֤��
    var wszh="<%=tmpBo.getWszh()%>";
    //�������
    var rqn="<%=sbrq.substring(0,4)%>";
    //�������
    var rqy="<%=sbrq.substring(4,6)%>";
    //�������
    var rqr="<%=sbrq.substring(6)%>";
    //˰��ǼǴ���
    var swdjdm="<%=tmpBo.getSwdjzh()%>";
    //���ջ���
    var zsjg="<%=tmpBo.getSwjgzzjgmc()%>";
    //��˰��ȫ��
    var nsrqc="<%=tmpBo.getNsrmc()%>";
    //�տ�����
    var skyh="<%=tmpBo.getYhmc()%>";
    //˰���ѣ���
    var SZ="<%=printSzmc%>";
    //˰������ʱ��
    var skssrq="<%=printSssq%>";
    //ʵ�ɽ��
    var sjje="<%=printSjje%>";
    //���ϼƣ���д��
    var  jehj="<%=tmpBo.getHjjedx()%>";
    //��ע1
    var  bz1="<%=tmpBo.getBz1()%>";
    //��ע1
    var  bz2="<%=tmpBo.getBz2()%>";
    //��ע1
    var  bz3="<%=tmpBo.getBz3()%>";


    document.printer.setNDZB(ndzb);
    document.printer.setWSZH(wszh);
    document.printer.setRQN(rqn);
    document.printer.setRQY(rqy);
    document.printer.setRQR(rqr);
    document.printer.setSWDJDM(swdjdm);
    document.printer.setZSJG(zsjg);
    document.printer.setNSRQC(nsrqc);
    document.printer.setSKYH(skyh);
    document.printer.setSZ(SZ);
    document.printer.setSKSSRQ(skssrq);
    document.printer.setSJJE(sjje);
    document.printer.setJEHJ(jehj);
    document.printer.setBZ1(bz1);
    document.printer.setBZ2(bz2);
    document.printer.setBZ3(bz3);

    document.printer.startPrint();
    <%
  }
}
%>
}
</SCRIPT>
</body>
</html>
