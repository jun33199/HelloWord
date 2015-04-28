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
    <title>�����еط�˰����걨�ɿ</title>
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

    <!-- ������˰ -->
    <html:form action="/webapp/smsb/zhsbPgbsjksypysAction.do" method="POST" >

      <html:hidden property="actionType" value="Query"/>
      <html:hidden property="sbbh" />
      <html:hidden property="ysjcdm" />
      <html:hidden property="forward" />
      <html:hidden property="presbbh" />
      <html:hidden property="sjly" />
      <html:hidden property="sbrq" />
      <table width="96%" align="center" cellspacing="0" class="table-99">
        <tr>
          <!-- ������˰ -->
          <td class="1-td1"  colspan="7"><font size="5" color="#999999">�� �� �� �� �� �� ��<br>˰�սɿ���(���о���ר��)</font></td>
        </tr>
        <tr>
          <td class="1-td2"  colspan="7" valign="top" width="99%">
            <br/>
            <table class="table-99" cellspacing="0" >
              <tr>
                <td width="8%" class="2-td1-left" nowrap><strong>˰������</strong></td>
                <td width="17%" class="2-td2-t-left"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="sklx"  size="40"/></div></td>
                <td width="15%" class="2-td1-left"><strong>���Ա��</strong></td>
                <td width="15%" class="2-td2-t-left"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="jkpzh"  size="40"/></div></td>
                <td width="14%" class="2-td1-left" nowrap>&nbsp;</td>
                <td width="31%" class="2-td2-t-center">&nbsp;</td>
              </tr>
              <tr>
                <td width="8%" class="2-td1-left" nowrap> <strong>�Ǽ�ע������</strong></td>
                <td width="17%" class="2-td2-left">&nbsp;</td>
                <td width="15%" class="2-td1-left"><strong>�����</strong></td>
                <td width="15%" class="2-td2-left">
                	<div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="tfrqn"  size="4"/><strong>��</strong><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="tfrqy"  size="2"/><strong>��</strong><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="tfrqr"  size="2"/><strong>��</strong></div>	
                </td>
                <td width="14%" class="2-td1-left" nowrap><strong>˰�����</strong></td>
                <td width="31%" class="2-td2-center"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="swjgzzjgmc"  size="40"/></div></td>
              </tr>
            </table>
            <br/>
            <table cellspacing="3" border="0" width="100%">
              <tr>
                <td width="55%" valign="top">
                  <table cellspacing="0" border="0" width="100%">
                    <tr>
                      <td rowspan="2" width="10%" class="2-td1-left"> <strong>�ɿλ���ˣ�</strong> </td>
                      <td width="10%" class="2-td1-left"><strong>ʶ���</strong></td>
                      <td width="30%" class="2-td2-t-left"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="jsjdm"  size="20"/></div></td>
                      <td width="20%" class="2-td1-left"><strong>��������</strong></td>
                      <td width="30%" class="2-td2-t-center"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="yhmc"  size="20"/></div></td>
                    </tr>
                    <tr>                      	
                      <td width="10%" class="2-td1-left"><strong>����</strong></td>
                      <td width="30%" class="2-td2-left"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="nsrmc"  size="50"/></div></td>
                      <td width="20%" class="2-td1-left"><strong>�˺�</strong></td>
                      <td width="30%" class="2-td2-center"><div align="left"><html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="zh"  size="20"/></div></td>
                    </tr>
                    <tr>                      	
                      <td colspan="2" width="20%" class="2-td1-left"><strong>�տ����</strong></td>
                      <td width="30%" class="2-td2-left"><div align="left">(<bean:write name="zhsbPgbsjksypysActionForm" property="skgkh"/>)<html:text styleClass="inputnoborder" style="color:#3C5564" readonly="true" property="gkzzjgmc"  size="20"/></div></td>
                      <td width="20%" class="2-td1-left"><strong>˰���޽�����</strong></td>
                      <td width="30%" class="2-td2-center"><div align="left"><html:text onchange="isDate(this,false)" property="xjrq"  size="20"/></div></td>
                    </tr>                    
                  </table>
                </td>
              </tr>
            </table>
            <table class="table-99" cellspacing="0" width="98%" border="1">
					    <tr>
					    	<td colspan="3" width="35%" class="2-td1-left"><div align="center">Ԥ���Ŀ</div></td>
					    	<td rowspan="2" width="15%" class="2-td1-left"><div align="center">ƷĿ����</div></td>
					    	<td rowspan="2" width="8%" class="2-td1-left"><div align="center">��˰����</div></td>
							  <td rowspan="2" width="8%" class="2-td1-left"><div align="center">��˰����<br>��������</div></td>
							  <td rowspan="2" width="8%" class="2-td1-left"><div align="center">˰�ʻ�λ˰��</div></td>
							  <td rowspan="2" width="10%" class="2-td1-left"><div align="center">˰������ʱ��</div></td>
							  <td rowspan="2" width="8%" class="2-td1-left"><div align="center">�ѽɻ�۳���</div></td>							  
							  <td rowspan="2" width="8%" class="2-td1-center"><div align="center">ʵ�ʽ��</div></td>
					   </tr>  					
					    <tr>
					    	<td width="10%" class="2-td1-left"><div align="center">��&nbsp;&nbsp;��</div></td>
					    	<td width="15%" class="2-td1-left"><div align="center">��&nbsp;&nbsp;��</div></td>
					    	<td width="10%" class="2-td1-left"><div align="center">��&nbsp;&nbsp;��</div></td>
					   </tr>              
				    <bean:define id="itemjksprint" name="zhsbPgbsjksypysActionForm" property="dataList"/>
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
                <td nowrap class="2-td1-left" height="28"><strong>���ϼ�</strong></td>
                <td colspan="8" nowrap class="2-td2-left" height="28"><strong>(��д)&nbsp;&nbsp;&nbsp;&nbsp;</strong><input type="text" name="xthjdx" value='<ttsoft:write name="zhsbPgbsjksypysActionForm" property="hjjedx"/>' class="inputnoborder" readonly size ="60"></td>
                <td nowrap class="2-td2-center" height="28">��<input type="text" name="xthj" value='<ttsoft:write name="zhsbPgbsjksypysActionForm" property="hjje"/>' class="inputnoborder" readonly size ="15"></td>
              </tr>
              <tr>
                <td colspan="2" nowrap class="2-td2-left" height="60"><strong>˰�����</strong><br><input type="text" name="dfswjg" value='<ttsoft:write name="zhsbPgbsjksypysActionForm" property="dfswjg"/>' class="inputnoborder" readonly size ="40"><br><div align="center"><strong>(����)</strong></div><br><div align="left"><strong>&nbsp;&nbsp;��Ʊ��</strong></div></td>
                <td colspan="2" nowrap class="2-td2-left" height="60"><strong>�ɿλ(��)</strong><br><input type="text" name="nsrmc" value='<ttsoft:write name="zhsbPgbsjksypysActionForm" property="nsrmc"/>' class="inputnoborder" readonly size ="40"><br><div align="center"><strong>(����)</strong></div><br><div align="left"><strong>&nbsp;&nbsp;������</strong></div></td>
                <td colspan="3" nowrap class="2-td2-left" height="60"><strong>���п��������ײ���ת�տλ�˻�</strong><p><div align="center"><strong>����(����)����</strong></div><br><div align="center"><strong>&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��</strong></div></td>
                <td colspan="3" nowrap class="2-td2-center" height="60"><div align="left"><strong>��ע</strong></div><br><input type="text" name="bz" value='<ttsoft:write name="zhsbPgbsjksypysActionForm" property="bz"/>' class="inputnoborder" readonly size ="40"></td>
              </tr>                          
            </table>
            <table width="94%" border="0" cellpadding="0" cellspacing="4">
              <tr valign="bottom">
                <td width="20%"> </td>
                <!--<td width="10%"><input type="image" accesskey="a" tabIndex="-1" onclick="javascript:addRow('SBJKWHMX');return false;" style="cursor:hand" onMouseOver="MM_swapImage('zj1','','<%=static_contextpath%>images/zj-a2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="����" id="zj1" src="<%=static_contextpath%>images/zj-a1.jpg" width="79" border="0" height="22"/></td> -->
                <!--<td width="10%"><input type="image" accesskey="d" tabIndex="-1" onclick="javascript:deleteRow('SBJKWHMX');return false;" style="cursor:hand"  onMouseOver="MM_swapImage('sc1','','<%=static_contextpath%>images/sc-d2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="ɾ��" id="sc1" src="<%=static_contextpath%>images/sc-d1.jpg" width="79" border="0" height="22"/></td> -->
                <td width="10%"><input type="image" accesskey="s" tabIndex="-1" onclick="doSubmitFormPro('Update');return false;" style="cursor:hand" onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="����" id="bc1" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" border="0" height="22"/></td>
                <td width="10%"><input type="image" accesskey="p" tabIndex="-1" onClick="goprint();return false;" style="cursor:hand;" onMouseOver="MM_swapImage('dy1','','<%=static_contextpath%>images/dy-p2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="��ӡ" id="dy1" src="<%=static_contextpath%>images/dy-p1.jpg" width="79" height="22"/></td>
                <td width="10%"><input type="image" accesskey="f" tabIndex="-1" onclick="doSubmitForm('Back');return false;" style="cursor:hand"  onMouseOver="MM_swapImage('fh1','','<%=static_contextpath%>images/fh-f2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="����" id="fh1" src="<%=static_contextpath%>images/fh-f1.jpg" width="79" height="22" /></td>
                <td width="10%"><input type="image" accesskey="c" tabIndex="-1"   onClick="tuichu(); return false;" style="cursor:hand;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22"/></td>
                <td width="0%"></td>
                <td width="20%">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <div id=szsmdm_epodDateLayer style="position: absolute; width: 142; height: 166; z-index: 9998; display: none" ><select id=sel onclick=selectClick("szsmdm","SBJKWHMX") onkeyup=selectMove("szsmdm","SBJKWHMX")
        onfocusout=selectClick("szsmdm","SBJKWHMX")
        onkeydown="if(window.event.keyCode==13) selectClick('szsmdm','SBJKWHMX')"></select></div>
        <%@ include file="./include/footer.jsp"%>
      </td>
    </tr>
  </table>

</html:form>
</body>
<script language="javascript" type="text/JavaScript">

//var SBJKWHMX_list=new DTable(SBJKWHMX,jsArr_SBJKWHMX);
//SBJKWHMX_list.tableTail=2;

//������˰
<ttsoft:write name="zhsbPgbsjksypysActionForm" property="scriptStr" filter="false"/>

hjArray.push(new Array('sjse','xthj','SBJKWHMX'));
/**
*�̳� smsb_common.js�е�addRow��Ϊֻ�ܼ����4��
*/
function addRow(tableid){
  eval("var tempDTable="+tableid+"_list");
  if(tempDTable.doGetRowLength()-tempDTable.tableHead-tempDTable.tableTail>=4){
    return tempDTable.doGetRowIndex(4);
  }
  tempDTable.appendRow();
  tempDTable.focusAt(tempDTable.CurrentRow,0);
  return tempDTable.doGetRowIndex(tempDTable.CurrentRow);
}
//ִ���ύ����
function doSubmitFormPro(action){
  //���¼������
  //if(!doSubmitCheck()) return false;
//  if (!check()) return false;
  doSubmitForm(action);
}
//���¼������
function check(){

  var succeed =true;
  var alertStr="";
  alertStr+=checkSzsmdm();
  alertStr+=checkHjsjje();
  if(alertStr!="") {
    succeed =false
    alert(alertStr);
  }
  return succeed;
}

//��鲢�ύ����Ҫ����걨���ڡ�˰����������
//��˰��Ŀ���롢˰���������ڡ���˰��������˰��ʵ�ʽ�˰��
function doSubmitCheck(){


  //���ÿһ�е�ֵ�Ƿ�Ϸ�
  for(var ii=0;ii<SBJKWHMX.rows.length;ii++){

    if(SBJKWHMX.rows[ii].all("sjse") && !isNum(SBJKWHMX.rows[ii].all("sjse"),0,null,true)) {
      //alert("ʵ�ʽ�˰��Ϸ�");
      return false;
    }
    if(SBJKWHMX.rows[ii].all("sjse") && SBJKWHMX.rows[ii].all("sjse").value==0) {
      alert("�Ѿ�ȡ�����걨��ʵ�ʽ�˰��Ӧ������");
      SBJKMX.rows[ii].all("sjse").focus();
      return false;
    }

  }
  return true;

}

//���˰��˰Ŀ����
function checkSzsmdm(){
  var alertStr="";
  var szsmdm=document.all('szsmdm');
  if(szsmdm)
  {//�ҵ�szsmdm �Ķ���
    if(szsmdm.length)
    {
      for(var i=0;i<szsmdm.length;i++)
      {
        if(szsmdm[i].value=="")
        {
          alertStr+="˰��˰Ŀ���벻��Ϊ��!\n"
          break;
        }//end if
      }//end for
    }//end if
    else
    {//ֻ����һ��szsmdm
      if(szsmdm.value=="")
      {
        alertStr+="˰��˰Ŀ���벻��Ϊ��!\n"
      }
    }
  }
  return alertStr;
}

function checkHjsjje(){

  var alertStr="";
  var tempTable;
  /*
  for(var i=0;i<tableArray.length;i++){
    eval("tempTable="+tableArray[i][0]);
    if(tempTable.all("sjje").value!=tempTable.all("xthj").value){
      alertStr+="*"+tableArray[i][1]+"* ��¼��ϼƽ���ϵͳ�ϼƽ���!\n"
    }
  }
  */
  return alertStr;
}
//����ϵͳ�ϼ�
//hj();


////////////////////////���￪ʼ��ӡ�ĳ���///////////////////
function goprint(){
  //������˰
//  var szdm = "<bean:write name="zhsbPgbsjksypysActionForm" property="szmc" />"; //˰�ִ���
  var sklx = "<bean:write name="zhsbPgbsjksypysActionForm" property="sklx" />"; //˰������
//  var tfrq = "<bean:write name="zhsbPgbsjksypysActionForm" property="lrrq" />"; //�����
  var tfrqn = "<bean:write name="zhsbPgbsjksypysActionForm" property="tfrqn" />"; //�������
  var tfrqy = "<bean:write name="zhsbPgbsjksypysActionForm" property="tfrqy" />"; //�������    
  var tfrqr = "<bean:write name="zhsbPgbsjksypysActionForm" property="tfrqr" />"; //�������  
//  var lsgx = "<bean:write name="zhsbPgbsjksypysActionForm" property="lsgx" />"; //������ϵ
  var zclx = "<bean:write name="zhsbPgbsjksypysActionForm" property="zclxmc" />"; //ע������
  var dnbh = "<bean:write name="zhsbPgbsjksypysActionForm" property="jkpzh" />"; //���Ա��
  var zsjg = "<bean:write name="zhsbPgbsjksypysActionForm" property="swjgzzjgmc" />"; //���ջ���

  var jkdwdm = "<bean:write name="zhsbPgbsjksypysActionForm" property="jsjdm" />"; //�ɿλ����
//  var jkdwdh = "<bean:write name="zhsbPgbsjksypysActionForm" property="jydzlxdm" />"; //�ɿλ�绰
  var jkdwqc = "<bean:write name="zhsbPgbsjksypysActionForm" property="nsrmc" />"; //�ɿλȫ��
  var jkdwkhyh = "<bean:write name="zhsbPgbsjksypysActionForm" property="yhmc" />"; //�ɿλ��������
  var jkdwzh = "<bean:write name="zhsbPgbsjksypysActionForm" property="zh" />"; //�ɿλ�˺�

  var yskmbm = "<bean:write name="zhsbPgbsjksypysActionForm" property="yskmdm" />"; //Ԥ���Ŀ����
  var yskmmc = "<bean:write name="zhsbPgbsjksypysActionForm" property="yskmmc" />"; //Ԥ���Ŀ����
//  var yskmjc = "<bean:write name="zhsbPgbsjksypysActionForm" property="ysjcmc" />"; //Ԥ���Ŀ����
  var skgk = "(<bean:write name="zhsbPgbsjksypysActionForm" property="skgkh"/>)<bean:write name="zhsbPgbsjksypysActionForm" property="gkzzjgmc" />"; //�տ����

  var sksssq = "<bean:write name="zhsbPgbsjksypysActionForm" property="skssksrq" />-<bean:write name="zhsbPgbsjksypysActionForm" property="skssjsrq" />"; //˰������ʱ��
  var skxjrq = "<bean:write name="zhsbPgbsjksypysActionForm" property="xjrq" />"; //˰���޽�����

  var jkjehj = "<bean:write name="zhsbPgbsjksypysActionForm" property="hjjedx" />"; //�ɿ���ϼ�(TBD)
  var hjje = "��<bean:write name="zhsbPgbsjksypysActionForm" property="hjje" />";//�ϼƽ��

  var jkdw = "<bean:write name="zhsbPgbsjksypysActionForm" property="nsrmc" />"; //�ɿλ(TBD)
  var swjg = "<bean:write name="zhsbPgbsjksypysActionForm" property="dfswjg" />"; //�ط�˰�����(TBD)

  var bz = "<bean:write name="zhsbPgbsjksypysActionForm" property="bz" />"; //��ע(TBD)

  var  jkmxfcbl = "<bean:write name="zhsbPgbsjksypysActionForm" property="mxFcbl"/>"; //�ɿ���ϸ�ֳɱ���(TBD)
  var  jkmxpmmc = "<bean:write name="zhsbPgbsjksypysActionForm" property="mxPmmc"/>"; //�ɿ���ϸƷĿ����(TBD)
  var  jkmxkssl = "<bean:write name="zhsbPgbsjksypysActionForm" property="mxKssl"/>"; //�ɿ���ϸ��˰����(TBD)
  var  jkmxjsje = "<bean:write name="zhsbPgbsjksypysActionForm" property="mxJsje"/>"; //�ɿ���ϸ��˰���(TBD)
  var  jkmxsjse = "<bean:write name="zhsbPgbsjksypysActionForm" property="mxSjse"/>"; //�ɿ���ϸʵ��˰��(TBD)


  //alert(szdm);


  //The second format!//�˴β���Ԥ�㼶��
  var jkmxyjkc = " ";

  //������˰
  var sksssq_qs = "<bean:write name="zhsbPgbsjksypysActionForm" property="skssksrq" />";
  var sksssq_jz = "<bean:write name="zhsbPgbsjksypysActionForm" property="skssjsrq" />";
  var jkmxslse = " ";


  //�жϴ�ӡ��ʽ/��ʽ
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


//��ӡ����Ʊ��Modified by lufeng 2003-12-19
function fnOpen(){
  return "machine";
}

function fnReturn()
{
  location.href="PG3_SBZS_000.htm";
}


//��д'../js/smsb_common.js'�е�tuchu()����  zzb  090407
	//�˳�
function tuichu(){
	if(returnStr==null || returnStr==""){
		//������˰
		returnStr="zhsbPgbsAction.do";
	}
	//��������ۺ��걨�����걨����ҳ�棬���˳����ۺ��걨ҳ��
	if(document.all.iszhsb && document.all.iszhsb.value=='1')
		//������˰
		returnStr="zhsbPgbsAction.do?actionType=Show";
	window.location=returnStr;
}

</script>
</html:html>


