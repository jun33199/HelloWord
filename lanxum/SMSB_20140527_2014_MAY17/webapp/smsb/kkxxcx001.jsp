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
<link href="../css/beijing.css" rel="stylesheet" type="text/css">
<title>�ۿ���Ϣ��ѯ</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/function.js"></script>
</head>
<body  leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="InitSaveOption();initRemoveOption();showKkbcgyyOption();">
<%@ include file="./include/header.jsp"%>
<html:form action="/webapp/smsb/kkxxcxAction.do" method="POST" >
<%
    com.ttsoft.bjtax.smsb.gghsb.web.KkxxcxForm form = (com.ttsoft.bjtax.smsb.gghsb.web.KkxxcxForm)request.getAttribute("kkxxcxForm");
    %>
<html:hidden property="actionType" />
<html:hidden property="pgNum" />
<html:hidden property="length" />
<html:hidden property="pgSum" />

<table width="780" height="302" border="0" cellpadding="0" cellspacing="0"  align="center">
  <tr>
    <td>
      	<table align="center" cellspacing="0"  class="table-99"  width="98%">
	       <tr>
               <td class="1-td1" >�ۿ���Ϣ��ѯ</td>
	        </tr>
	        <tr>
            <td class="1-td2">
		<br>
            <table cellspacing="0" class="table-99" width="98%">
              <tr>
                <td class="3-td1-left" nowrap><div align="right">���:&nbsp;&nbsp;</div></td>
                <td class="3-td1-left" nowrap><div align="left">&nbsp;&nbsp;<html:text property="nd" size="20" onchange="return isFullDate1(this.value,1,'',true)" /><font color="#FF0000">&nbsp;*</font></div></td>
               <td class="3-td1-left" nowrap><div align="right">����:&nbsp;&nbsp;</div></td>
                <td class="3-td1-centen" nowrap><div align="left">&nbsp;&nbsp;<html:select property="zq">
                    <html:option value="01"> 1��</html:option>
					<html:option value="02"> 2��</html:option>
					<html:option value="03"> 3��</html:option>
					<html:option value="04"> 4��</html:option>
					<html:option value="05"> 5��</html:option>
					<html:option value="06"> 6��</html:option>
					<html:option value="07"> 7��</html:option>
					<html:option value="08"> 8��</html:option>
                    <html:option value="09"> 9��</html:option>
					<html:option value="10">10��</html:option>
                    <html:option value="11">11��</html:option>
					<html:option value="12">12��</html:option>
                   </html:select></div>
                   </td>
              </tr>

              <tr>
                <td class="2-td2-left" nowrap><div align="right">����:&nbsp;&nbsp;</div></td>
                <td class="2-td2-left" nowrap><div align="left">&nbsp;
                                       <bean:define id ="qxItems" name="kkxxcxForm" property="qxList"/>
                                       <html:select property="qx" onchange="changeRemoveOption();">
                                         <html:options collection="qxItems" property="value" labelProperty="label"/>
                                       </html:select></div></td>
                <td class="2-td2-left" nowrap><div align="right">˰����:&nbsp;&nbsp;</div></td>
                <td class="2-td2-center" nowrap><div align="left">&nbsp;
                                       <bean:define id="swjslist" name="kkxxcxForm" property="swjsList"/>
                                       <html:select property="swjs">
                                         <html:options collection="swjslist" property="value" labelProperty="label"/>
                                       </html:select></div></td>
              </tr>
              <tr>
                <td class="2-td2-left" nowrap><div align="right">����:&nbsp;&nbsp;</div></td>
                <td class="2-td2-left" nowrap><div align="left">&nbsp;
                                       <bean:define id="jxlist" name="kkxxcxForm" property="jxList"/>
                                       <html:select property="jx">
                                           <html:options collection="jxlist" property="value" labelProperty="label"/>
                                       </html:select></div></td>
               <td class="2-td2-left" nowrap><div align="right">��������룺</div></td>
               <td class="2-td2-center" nowrap><div align="left">&nbsp;&nbsp;<html:text property="jsjdm" size="20"  /></div></td>
              </tr>

              <tr>
                <td class="2-td2-left" nowrap><div align="right">�ۿ��־:&nbsp;&nbsp;</div></td>
                <td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;<html:select property="kkbz" onchange="showKkbcgyyOption();">
                                         <html:option value="0">*����ԭʼ�ۿ���Ϣ</html:option>
                                         <html:option value="1">*�������пۿ���</html:option>
                                         <html:option value="2">&nbsp;���пۿ�ɹ���Ϣ</html:option>
                                         <html:option value="3">&nbsp;���пۿ�ɹ���Ϣ</html:option>
                                       </html:select></div></td>
                <td class="2-td2-left" nowrap><div align="right" id="kkbcgyy1">�ۿ�ʧ��ԭ��:&nbsp;&nbsp;</div>
                                              <div align="right" id="kkbcgyy11">&nbsp;&nbsp;</div>
                </td>
                <td class="2-td2-center" nowrap><div align="left" id="kkbcgyy2">&nbsp;&nbsp;<html:select  property="kkbcgyy">
                       <html:option value="0">* ����ԭ��</html:option>
					   <html:option value="10">�����ʺŲ�����</html:option>
					   <html:option value="20">�ʻ�����</html:option>
					   <html:option value="30">�ۿ���Ϣ��ȫ</html:option>
					   <html:option value="40">�ۿ��ʺſ����</html:option>
					   <html:option value="99">����ԭ��</html:option>
  					 </html:select></div>
                     <div align="left" id="kkbcgyy21">&nbsp;&nbsp;</div>
                     </td>
              </tr>
              <tr>
                <td colspan="3" class="2-td2-left">&nbsp;</td>
                <td class="2-td2-center"><input name="I2" type="image" accesskey="q" value="��ѯ"  onclick='doSelectSubmit();return false;' onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="��ѯ" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun"></td>
              </tr>
            </table>
            <br>
            <div align="left">&nbsp;&nbsp;��¼��:<ttsoft:write name='kkxxcxForm' property='jls'/>&nbsp;&nbsp;�����ϼ�:<ttsoft:write name='kkxxcxForm' property='hshj'/>&nbsp;&nbsp;�ۿ���ϼ�:<ttsoft:write name='kkxxcxForm' property='kkjehj'/>&nbsp;&nbsp;��Ӧ��˰��ϼ�:<ttsoft:write name='kkxxcxForm' property='ynjehj'/></div><br>
            <div id="Layer2" style="position:static; overflow: auto;  width: 778px; height: 230px">
            <table id="kkxxlistTable" cellspacing="0" border="1" class="table-99">
              <tr>
                <td  class="2-td1-left"  nowrap height="2">���������</td>
                <td  class="2-td1-left" nowrap height="2">��˰������</td>
                <td  class="2-td1-left" nowrap height="2">��˰����ϵ�绰</td>
                <td class="2-td1-left"  nowrap height="2">˰�������</td>
                <td  class="2-td1-left" nowrap height="2">�ۿ��־</td>
                <td  class="2-td1-left"  nowrap height="2">��˰����</td>
                <td class="2-td1-left"  nowrap height="2">�ۿ�ɹ�ԭ��</td>
                <td class="2-td1-left"  nowrap height="1">˰��</td>
                <td class="2-td1-left"  nowrap height="1">˰Ŀ</td>
                <td class="2-td1-left"  nowrap height="2">��Ӧ��˰��</td>
                <td class="2-td1-left"  nowrap height="2">���пۿ���</td>
                <td class="2-td1-left"   nowrap height="1">��������</td>
                <td class="2-td1-left"   nowrap height="1">�����ʺ�</td>
                <td class="2-td1-left"  nowrap height="2">˰��������ʼ����</td>
                <td class="2-td1-left"  nowrap height="2">˰��������������</td>
                <td class="2-td1-left"  nowrap height="2">Ԥ���Ŀ����</td>
                <td class="2-td1-left"  nowrap height="2">Ԥ�㼶������</td>
                <td class="2-td1-left"  nowrap height="2">�ۿλ����</td>
                <td class="2-td1-center"  nowrap height="2">�ɿ�ƾ֤��</td>
              </tr>
              <bean:define id="kkxxlist" name="kkxxcxForm" property="dataList"/>
              <%
                int pgSum = form.getPgSum();
                int pgNum = form.getPgNum();
                int Length = String.valueOf(pgSum).length();
              %>
              <logic:iterate id="items" name="kkxxlist" indexId="index" >
                <bean:define id="item" name="items"/>
               <tr>
                <td  class="2-td2-left" height="2"><ttsoft:write name="item" property="jsjdm"/>&nbsp</td>
                <td  class="2-td2-left" height="2"><p style='width:100px;word-break;'><ttsoft:write name="item" property="nsrmc"/>&nbsp</p></td>
                <td  class="2-td2-left" height="2"><ttsoft:write name="item" property="nsrlxdh"/>&nbsp</td>
                <td class="2-td2-left"  height="2"><ttsoft:write name="item" property="wsjc"/>&nbsp</td>
                <td  class="2-td2-left" height="2"><ttsoft:write name="item" property="kkbzmc"/>&nbsp</td>
                <td  class="2-td2-left" height="2"><ttsoft:write name="item" property="kkrq"/>&nbsp</td>
                <td class="2-td2-left"  height="2"><ttsoft:write name="item" property="bcgyymc"/>&nbsp</td>
                <td class="2-td2-left"  height="2"><p style='width:100px;word-break;'><ttsoft:write name="item" property="szmc"/>&nbsp</p></td>
                <td class="2-td2-left"  height="2"><p style='width:100px;word-break;'><ttsoft:write name="item" property="szsmmc"/>&nbsp</p></td>
                <td class="2-td2-left"  height="2"><ttsoft:write name="item" property="sjje"/>&nbsp</td>
                <td class="2-td2-left"  height="2"><ttsoft:write name="item" property="rkje"/>&nbsp</td>
                <td class="2-td2-left"  height="2"><ttsoft:write name="item" property="yhmc"/>&nbsp</td>
                <td class="2-td2-left"  height="2"><ttsoft:write name="item" property="zh"/>&nbsp</td>
                <td class="2-td2-left"  height="2"><ttsoft:write name="item" property="skssksrq"/>&nbsp</td>
                <td class="2-td2-left"  height="2"><ttsoft:write name="item" property="skssjsrq"/>&nbsp</td>
                <td class="2-td2-left"  height="2"><ttsoft:write name="item" property="yskmdm"/>&nbsp</td>
                <td class="2-td2-left"  height="2"><ttsoft:write name="item" property="ysjcmc"/>&nbsp</td>
                <td class="2-td2-left"  height="2"><ttsoft:write name="item" property="kkdwmc"/>&nbsp</td>
                <td class="2-td2-center"  height="2"><ttsoft:write name="item" property="jkpzh"/>&nbsp</td>
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
                    <td >
                        <br>&nbsp;&nbsp;���Ϊ�������ʽΪYYYY,��2005��<br><br>
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
function showKkbcgyyOption(){
    var kkbz = document.all.kkbz;
    var kkbcgyy = document.all.kkbcgyy;
    var kkbcgyy1 = document.all("kkbcgyy1");
    var kkbcgyy11 = document.all("kkbcgyy11");
    var kkbcgyy2 = document.all("kkbcgyy2");
    var kkbcgyy21 = document.all("kkbcgyy21");
    if(kkbz.value != '3'){
        kkbcgyy.disabled = true;
        kkbcgyy1.style.display="none";
        kkbcgyy2.style.display="none";
        kkbcgyy11.style.display="block";
        kkbcgyy21.style.display="block";
    }else{
        kkbcgyy.disabled = false;
        kkbcgyy1.style.display="block";
        kkbcgyy2.style.display="block";
        kkbcgyy11.style.display="none";
        kkbcgyy21.style.display="none";
    }
}

//����Excel�ύ
function doToExcelSubmit(){
    //���������
    var nd = document.all.nd;
    if(true == isFullDate1(nd.value,1,null,true)){
	if(document.all.pgSum.value==""||document.all.pgSum.value==null||document.all.pgSum.value=="0")
	{
		alert("���Ȳ�ѯ��");
		return false;
	}
        document.all.pgNum.value = 1;
        doSubmitForm('SaveExcel');
        return true;
    }else{
        return false;
    }
}
//�����ύ
function doSelectSubmit()
{
    //���������
    var nd = document.all.nd;
    if(true == isFullDate1(nd.value,1,null,true)){
        document.all.pgNum.value = 1;
        doSubmitForm('Query');
        return true;
    }else{
        return false;
    }
}

function goToPage(pgNum) {
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
    thisPG.value = gotoPG.value;
    doSubmitForm('Query');
  }
//  return;
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

</script>
</body>
</html:html>
