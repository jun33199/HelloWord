<%@ page import="java.util.Iterator,
                 java.util.Map"%>
<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Language" content="zh-cn">
<title>���ڶ����������ѯ</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/function.js"></script>
</head>
<body  leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="InitSaveOption();initRemoveOption();">
<%@ include file="./include/header.jsp"%>

<html:form action="/webapp/smsb/dqdehrkcxAction.do" method="POST" >
<html:hidden property="actionType" />
<html:hidden property="pgNum" />
<html:hidden property="length" />
<html:hidden property="pgSum" />
<%
    com.ttsoft.bjtax.smsb.gghsb.web.DqdehrkcxForm form = (com.ttsoft.bjtax.smsb.gghsb.web.DqdehrkcxForm)request.getAttribute("dqdehrkcxForm");
    %>
<table width="780" height="302" border="0" cellpadding="0" cellspacing="0"  align="center">
  <tr>
    <td>
      	<table align="center" cellspacing="0"  class="table-99"  width="98%">
	        <tr>
	            <td class="1-td1">���ڶ����������ѯ</td>
	        </tr>
	        <tr>
            <td class="1-td2">
            <br>
            <table cellspacing="0" border="1" class="table-99" width="98%">
              <tr>
                <td class="3-td1-left" nowrap><div align="right">���������:&nbsp;&nbsp;</div></td>
                <td class="3-td1-left" nowrap><div align="left">&nbsp;&nbsp;<html:text property="jsjdm" size="20" onkeydown="if(window.event.keyCode==13) { window.event.keyCode=9 }" /></div></td>
                <td class="3-td1-left" nowrap><div align="right">��˰��״̬:&nbsp;&nbsp;</div></td>
                <td class="3-td1-centen" nowrap><div align="left">&nbsp;
                <bean:define id="nsrztList" name="dqdehrkcxForm" property="nsrztList"/>
                   <html:select property="nsrzt">
                        <html:options collection="nsrztList" property="value" labelProperty="label"/>
                   </html:select></div>
                   </td>
              </tr>

              <tr>
                <td class="2-td2-left" nowrap><div align="right">����:&nbsp;&nbsp;</div></td>
                <td class="2-td2-left" nowrap><div align="left">&nbsp;
                                      <bean:define id="qxItems" name="dqdehrkcxForm" property="qxList"/>
                                      <html:select property="qx" onchange="changeRemoveOption();" style="width:200px">
                                         <html:options collection="qxItems" property="value" labelProperty="label"/>
                                       </html:select></div></td>
                <td class="2-td2-left" nowrap><div align="right">����˰����:&nbsp;&nbsp;</div></td>
                <td class="2-td2-center" nowrap><div align="left">&nbsp;
                <bean:define id="swjsList" name="dqdehrkcxForm" property="swjsList"/>
                                       <html:select property="swjs" style="width:200px">
                                         <html:options collection="swjsList" property="value" labelProperty="label"/>
                                       </html:select></div></td>
              </tr>
              <tr>
                <td class="2-td2-left" nowrap><div align="right">��������:&nbsp;&nbsp;</div></td>
                <td class="2-td2-left" nowrap><div align="left">&nbsp;
                <bean:define id="jxList" name="dqdehrkcxForm" property="jxList"/>
                                       <html:select property="jx" style="width:200px">
                                       <html:options collection="jxList" property="value" labelProperty="label"/>
                                       </html:select></div></td>
                <td class="2-td2-left" nowrap rowspan="4"><div align="right">�Ǽ�ע������:&nbsp;&nbsp;</div></td>
                <td class="2-td2-center" nowrap rowspan="4"><div align="left">&nbsp;
                                       <ttsoft:define id="djzclxItems" codekey="ZQWH_DJZCLX"/>
                                       <html:select property="dkzclx" multiple="true" size="5">
                                           <html:option value=""/>
                                           <html:options collection="djzclxItems" property="value" labelProperty="label"/>
                                       </html:select></div></td>
              </tr>
			  <tr>
                <td class="2-td2-left" nowrap><div align="right">��ⷽʽ:&nbsp;&nbsp;</div></td>
                <td class="2-td2-left" nowrap><div align="left">&nbsp;
                <bean:define id="rkfsList" name="dqdehrkcxForm" property="rkfsList"/>
                                       <html:select property="rkfs">
                                       <html:options collection="rkfsList" property="value" labelProperty="label"/>
                                       </html:select></div></td>
              </tr>
              <tr>
                <td class="2-td2-left" nowrap><div align="right">˰��:&nbsp;&nbsp;</div></td>
                <td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;<ttsoft:define id="szItems" codekey="CODE2"/><html:select property="sz">
                                         <html:option value="0">*ȫ��˰��</html:option>
                                        <html:options collection="szItems" property="value" labelProperty="label"/>
                                       </html:select></div></td>
              </tr>

              <tr>
			    <td class="2-td2-left" nowrap><div align="right">��˰�ڼ�:&nbsp;&nbsp;</div></td>
                <td class="2-td2-left" nowrap><div align="left">&nbsp;
                <bean:define id="nsqjList" name="dqdehrkcxForm" property="nsqjList"/>
                                       <html:select property="nsqj">
                                       <html:options collection="nsqjList" property="value" labelProperty="label"/>
                                       </html:select></div></td>
              </tr>

              <tr>
                <td class="2-td2-left" nowrap><div align="right">������ʼ����:&nbsp;&nbsp;</div></td>
                <td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;<html:text property="fromzq" size="20" onchange="isFullDate1(this.value,0,'',true);" onkeydown="if(window.event.keyCode==13) { window.event.keyCode=9 }" /><font color="#FF0000">&nbsp;*</font></div></td>
                <td class="2-td2-left" nowrap><div align="right">���ڽ�ֹ����:&nbsp;&nbsp;</div></td>
                <td class="2-td2-center" nowrap><div align="left">&nbsp;&nbsp;<html:text property="endzq" size="20" onchange="isFullDate1(this.value,0,'',true);" onkeydown="if(window.event.keyCode==13) { window.event.keyCode=9 }" /><font color="#FF0000">&nbsp;*</font></div></td>
              </tr>
              <tr>
								<td class="2-td2-left" nowrap><div align="right">������:&nbsp;&nbsp;</div></td>
                <td class="2-td2-left" nowrap><div align="left">&nbsp;
								<bean:define id="rkqkList" name="dqdehrkcxForm" property="rkqkList"/>
                                       <html:select property="rkqk">
                                       <html:options collection="rkqkList" property="value" labelProperty="label"/>
                                       </html:select></div></td>
                <td colspan="2" class="2-td2-center"><div align="right"><input name="I2" type="image" accesskey="q" value="��ѯ"  onclick='doSelectSubmit();return false;' onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="��ѯ" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun">&nbsp;&nbsp;</div></td>
              </tr>
            </table>
            <br>
            <div align="left">&nbsp;&nbsp;��¼��:<ttsoft:write name='dqdehrkcxForm' property='jls'/>&nbsp;&nbsp;��Ӧ��˰��ϼ�:<ttsoft:write name='dqdehrkcxForm' property='hdjehj'/>&nbsp;&nbsp;�ɿ���ϼ�:<ttsoft:write name='dqdehrkcxForm' property='jkjehj'/></div><br>
            <div id="Layer2" style="position:static; overflow: auto;  width: 778px; height: 200px">
            <table id="dqdehrkcxlistTable" cellspacing="0" border="1" class="table-99">
              <tr>
                <td  class="2-td1-left" nowrap height="1">���������</td>
                <td  class="2-td1-left" nowrap height="1">��˰������</td>
                <td  class="2-td1-left" nowrap height="1">˰��</td>
                <td class="2-td1-left"  nowrap height="1">˰Ŀ</td>
		<td  class="2-td1-left" nowrap height="1">��Ӧ��˰��</td>
		<td  class="2-td1-left" nowrap height="1">��˰�ڼ�</td>
                <td  class="2-td1-left" nowrap height="1">��Ӧ��˰��</td>
                <td  class="2-td1-left" nowrap height="1">�ɿ�����</td>
                <td class="2-td1-left"  nowrap height="1">�ɿ���</td>
                <td  class="2-td1-left" nowrap height="1">�������</td>
                <td class="2-td1-left"  nowrap height="1">˰����ⷽʽ</td>
                <td class="2-td1-left"  nowrap height="1">��Ӫ��ַ</td>
                <td class="2-td1-center"  nowrap height="1">��Ӫ�绰</td>
              </tr>
              <bean:define id="dqdehrkcxlist" name="dqdehrkcxForm" property="dataList"/>
              <%
                int pgSum = form.getPgSum();
                int pgNum = form.getPgNum();
                int Length = String.valueOf(pgSum).length();
              %>
              <logic:iterate id="items" name="dqdehrkcxlist" indexId="index" >
                <bean:define id="item" name="items"/>
               <tr>
                <td  class="2-td2-left" nowrap><ttsoft:write name="item" property="jsjdm"/>&nbsp</td>
                <td  class="2-td2-left" ><p style='width:100px;word-break;'><ttsoft:write name="item" property="nsrmc"/>&nbsp;</p></td>
                <td  class="2-td2-left" ><p style='width:100px;word-break;'><ttsoft:write name="item" property="hdszmc"/>&nbsp;</p></td>
                <td class="2-td2-left"  ><p style='width:100px;word-break;'><ttsoft:write name="item" property="smmc"/>&nbsp;</p></td>
                <td  class="2-td2-left" nowrap><ttsoft:write name="item" property="hdje"/>&nbsp;</td>
                <td  class="2-td2-left" nowrap><ttsoft:write name="item" property="nsqj"/>&nbsp;</td>
                <td  class="2-td2-left" nowrap><ttsoft:write name="item" property="qynse"/>&nbsp;</td>
                <td class="2-td2-left"  nowrap><ttsoft:write name="item" property="jkrq"/>&nbsp;</td>
                <td class="2-td2-left"  nowrap><ttsoft:write name="item" property="jkje"/>&nbsp;</td>
                <td class="2-td2-left"  nowrap><ttsoft:write name="item" property="rkrq"/>&nbsp;</td>
                <td class="2-td2-left"  nowrap><ttsoft:write name="item" property="skrkfsmc"/>&nbsp;</td>
                <td class="2-td2-left"  nowrap><ttsoft:write name="item" property="jydz"/>&nbsp;</td>
                <td class="2-td2-center"  nowrap><ttsoft:write name="item" property="jydh"/>&nbsp;</td>
               </tr>
              </logic:iterate>
            </table>
            </div>
            <table  class="table-99">
              <tr class="black9">
                <td align="right">��(<input type="text" name="gotoPG" value="<%=pgNum%>" size="<%=Length%>" onChange="doGotoPG()">/<%=pgSum%>)ҳ
                  <%if(pgNum > 1 ) {%>
                    <img src="<%=static_contextpath%>images/diyiye1.jpg" alt="��һҳ" id="first" onMouseOver="MM_swapImage('first','','<%=static_contextpath%>images/diyiye2.jpg',1)" onMouseOut="MM_swapImage('first','','<%=static_contextpath%>images/diyiye1.jpg',1)" onclick="goToPage('1');return false;" style="cursor:hand">
                    <img src="<%=static_contextpath%>images/shangyiye1.jpg" alt="��һҳ" id="backward" onMouseOver="MM_swapImage('backward','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImage('backward','','<%=static_contextpath%>images/shangyiye1.jpg',1)" onclick="goToPage('<%=pgNum-1%>');return false;" style="cursor:hand">
                  <%}%>
                  <%if(pgNum < pgSum) {%>
                    <img src="<%=static_contextpath%>images/xaiyiye1.jpg" alt="��һҳ" id="forward" onMouseOver="MM_swapImage('forward','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImage('forward','','<%=static_contextpath%>images/xaiyiye1.jpg',1)" onclick="goToPage('<%=pgNum+1%>');return false;" style="cursor:hand">
                    <img src="<%=static_contextpath%>images/zuimoye1.jpg" alt="��ĩҳ" id="last" onMouseOver="MM_swapImage('last','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImage('last','','<%=static_contextpath%>images/zuimoye1.jpg',1)" onclick="goToPage('<%=pgSum%>');return false;" style="cursor:hand">
                  <%}%>
                </td>
              </tr>
            </table>
            <table border="0" width="100%">
              <tr>
                <td width="22%">&nbsp; </td>
                <td width="15%"><input name="toexcel" type="image" accesskey="s"  onClick="doToExcelSubmit();return false;"  onMouseOver="MM_swapImage('toexcel1','','<%=static_contextpath%>images/b-bcdexcel2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="���浽Excel" id="toexcel1" src="<%=static_contextpath%>images/b-bcdexcel1.jpg" width="100" height="22"></td>
                <td width="15%"><input name="tuichu" type="image" accesskey="c"   onClick="tuichu();return false;"           onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)"            onMouseOut="MM_swapImgRestore()" value="�˳�"        id="tc1"      src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22"></td>
                <td width="18%">&nbsp;</td>
              </tr>
            </table>
            <br>
            <table width="99%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td><hr width="100%" size="1" ></td>
                    <td width="99" align="center" class="black9">
                        <strong><font color="#0000FF">ע �� �� ��</font></strong>
                    </td>
                    <td><hr width="100%" size="1" ></td>
                </tr>
            </table>

            <table width="99%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
                <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
                    <td ><br>
                        &nbsp;&nbsp;1.�϶���ȵĸ�ʽΪYYYY,��2005��<br>
                        &nbsp;&nbsp;2.�Ǽ�ע�����Ϳ��Զ�ѡ�������ǰ�סShift��+����϶���ѡ�к���ȡ����ֻ�ܵ�ѡ�����ȡ����<br>
                        &nbsp;&nbsp;3.��ʼ���ںͽ�ֹ����Ϊ�������ʽΪYYYYMMDD,��20050101��ǰ�߲��ܴ��ں��ߡ�<br><br>
                    </td>
                </tr>
            </table>

            </td>
   	    </tr>
	    </table>
    </td>
  </tr>
 </table>
</html:form>



<%@ include file="./include/footer.jsp"%>
<script language="JavaScript">

//�����ύ
function doSelectSubmit()
{
    //���������
    //var nd = document.all.nd;
    var fromzq =document.all.fromzq;
    var endzq = document.all.endzq;
    var jsjdm = document.all.jsjdm;
    if(trim(jsjdm.value) != "" && trim(jsjdm.value).length!=8 && trim(jsjdm.value).length!=7) {
        alert("�����������룬��ʽΪ8λ����")
        jsjdm.focus();
        return false;
    //}else if(trim(nd.value) != "" && false == isFullDate1(nd.value,1,null,true)){
    //    nd.focus();
    //    return false;
    }else if(false == isFullDate1(fromzq.value,0,'',true)){
        fromzq.focus();
        return false;
    }else if(false == isFullDate1(endzq.value,0,'',true)){
        endzq.focus();
        return false;
    }else if(fromzq.value.substr(0,4) != endzq.value.substr(0,4)){
        alert("���ڲ��ܿ���");
        return false;
    }else if(fromzq.value > endzq.value){
        alert("������ʼ���ڲ��ܴ������ڽ�ֹ����");
        return false;
    }else{
        document.forms[0].style.cursor="wait";
        document.all.pgNum.value = 1;
        doSubmitForm('Query');
        return true;
    }

}

function goToPage(pgNum) {
  document.forms[0].style.cursor="wait";
  document.all.pgNum.value = pgNum;
  doSubmitForm('Query');
}

function doGotoPG() {
  var gotoPG = document.all.gotoPG;
  var thisPG = document.all.pgNum;
  if(!fnIsIntNum(gotoPG.value)){
    gotoPG.value=thisPG.value;
    return;
  }
  if(parseInt(gotoPG.value)>parseInt(document.all.pgSum.value)){
    gotoPG.value=thisPG.value;
    return;
   }
  if(gotoPG.value!=thisPG.value) {
    document.forms[0].style.cursor="wait";	    
    thisPG.value = gotoPG.value;
    doSubmitForm('Query');
  }
  return;
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
		/*if(err!=""){
			if(isThrow){
				alert(alertStr);
			}
			return false;
		}
		return true;*/
	}else if(dateKind==1){//4λ��
		err = "";
		if((strDate.charAt(0)=="0") || strDate.length!=4 || (!isDigit(strDate))){
			err = "���ǺϷ�����ݣ�";
		}
		//alert(err);
	}

	if(err!=""){
		if(isThrow){
			alert(err);
		}
		return false;
	}
	return true;
}

function MyArray (size){
	for (var X=0; X<=size;X++)
	this[X]=0;
	this.length=size;
	return this;
}

var swjsOptionArray;
var jxOptionArray;

//�������;���ѡ��
function InitSaveOption(){
    var swjsSelect = document.all.swjs;
    swjsOptionArray = new MyArray(swjsSelect.options.length);
    for(var i =0;i<swjsSelect.options.length;i++){
        var swjsOption = new Option(swjsSelect.options[i].text,swjsSelect.options[i].value);
        swjsOptionArray[i] = swjsOption;
    }
    var jxSelect = document.all.jx;
    jxOptionArray = new MyArray(jxSelect.options.length);
    for(var i =0;i<jxSelect.options.length;i++){
        var jxOption = new Option(jxSelect.options[i].text,jxSelect.options[i].value);
        jxOptionArray[i] = jxOption;
    }
}

//ɾ������Ľ���;���
function initRemoveOption(){
    var qxSelect = document.all.qx;
    var qxOption = qxSelect.options[qxSelect.selectedIndex];
    if(qxOption == null || qxOption.value=='9000'){
        return;
    }
    //ɾ��˰�����
    var swjsSelect = document.all.swjs;
    for(var i =swjsSelect.options.length-1;i>=0;i--){
        var optionValue = swjsSelect.options[i].value;
        if( optionValue != '0' && optionValue.substr(0,2) != qxOption.value.substr(0,2)){
            swjsSelect.remove(i);
        }
    }
    //ɾ������
    var jxSelect = document.all.jx;
    for(var i =jxSelect.options.length-1;i>=0;i--){
        var optionValue = jxSelect.options[i].value;
        if( optionValue != '0' && optionValue.substr(0,2) != qxOption.value.substr(0,2)){
            jxSelect.remove(i);
        }
    }
}

/**�޸����ظı����;��� */
function changeRemoveOption(){
    var qxSelect = document.all.qx;
    var qxOption = qxSelect.options[qxSelect.selectedIndex];
    if(qxOption == null ){
        return;
    }else{
        //ɾ��˰�����
        var swjsSelect = document.all.swjs;
        for(var i =swjsSelect.options.length-1;i>=0;i--){
            var optionValue = swjsSelect.options[i].value;
            swjsSelect.remove(i);
        }
        //ɾ������
        var jxSelect = document.all.jx;
        for(var i =jxSelect.options.length-1;i>=0;i--){
            var optionValue = jxSelect.options[i].value;
            jxSelect.remove(i);
        }
        if(qxOption.value=='9000'){
            //˰�����
            var swjsSelect = document.all.swjs;
            for(var i =0;i<swjsOptionArray.length;i++){
                var swjsOption = swjsOptionArray[i];
                swjsSelect.options.add(swjsOption);
            }
            //����
            var jxSelect = document.all.jx;
            for(var i =0;i<jxOptionArray.length;i++){
                var jxOption = jxOptionArray[i];
                jxSelect.options.add(jxOption);
            }
        }else{
            //˰�����
            var swjsSelect = document.all.swjs;
            for(var i =0;i<swjsOptionArray.length;i++){
                var swjsOption = swjsOptionArray[i];
                if(swjsOption.value == '0' || swjsOption.value.substr(0,2) == qxOption.value.substr(0,2)){
                    swjsSelect.options.add(swjsOption);
                }
            }
            //����
            var jxSelect = document.all.jx;
            for(var i =0;i<jxOptionArray.length;i++){
                var jxOption = jxOptionArray[i];
                if(jxOption.value == '0' || jxOption.value.substr(0,2) == qxOption.value.substr(0,2)){
                    jxSelect.options.add(jxOption);
                }
            }
        }
    }
}

//����Excel�ύ
function doToExcelSubmit(){
    var fromzq =document.all.fromzq;
    var endzq = document.all.endzq;
    var jsjdm = document.all.jsjdm;
    if(trim(jsjdm.value) != "" && trim(jsjdm.value).length!=8 && trim(jsjdm.value).length!=7) {
        alert("�����������룬��ʽΪ8λ����")
        jsjdm.focus();
        return false;
    //}else if(trim(nd.value) != "" && false == isFullDate1(nd.value,1,null,true)){
    //    nd.focus();
    //    return false;
    }else if(false == isFullDate1(fromzq.value,0,'',true)){
        fromzq.focus();
        return false;
    }else if(false == isFullDate1(endzq.value,0,'',true)){
        endzq.focus();
        return false;
    }else if(fromzq.value.substr(0,4) != endzq.value.substr(0,4)){
        alert("���ڲ��ܿ���");
        return false;
    }else if(fromzq.value > endzq.value){
        alert("������ʼ���ڲ��ܴ������ڽ�ֹ����");
        return false;
    }else{      	    
    	document.all.pgNum.value = 1;
    	doSubmitForm('SaveExcel');
    	return true;
    }
}
</script>
</body>
</html:html>
