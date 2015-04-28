<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=gb2312" %>
<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>作废个体工商户汇总缴款书</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/DTable.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
<%@ include file="./include/header.jsp"%>


<html:form action="/webapp/smsb/gtgshZfjksAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden property="lrr" />
<html:hidden property="jsjdm" />
<html:hidden property="sbhzdh" />
<html:hidden property="hzrq" />
<html:hidden property="jkpzh" />
<html:hidden property="sbbh" />
<html:hidden property="lw" />

<TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9' height="300">
 <tr>
    <td valign="top" height="300"> <br>
      <table align="center" cellspacing="0" class="table-99" height="226" width="1%">
        <tr>
          <td class="1-td1"  colspan="2" height="28" >作废个体工商户汇总缴款书</td>
        </tr>
        <tr>
          <td class="1-td2" align="right" colspan="2" height="171" >
          <p style="text-align: right">征收机关名称：<bean:write name="gtgshZfjksForm" property="swjgzzjgmc"/><html:hidden property="swjgzzjgdm" /><br>
            </p>
            <table cellspacing="0" class="table-99" width="99%" height="27">
              <tr class="black9">
                <td align="left" colspan="2"  height="11" >
              &nbsp;<html:radio property="hzfs" value="1" onClick="befQuery()"/>按本人汇总的缴款书
              <html:radio property="hzfs" value="0" onClick="befQuery()"/>按本单位汇总的缴款书&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
              </tr>
            </table><br>

             <b>
             <table width="100%" border="0" cellpadding="0" cellspacing="0" id="jkslist" onmouseup='dokeyUp(this.id);showDetail(this.id,"detlist")' class="table-99">
	              <tr>
	                <td nowrap class="2-td2-t-left">汇总单号</td>
	                <td nowrap class="2-td2-t-left">缴款凭证张数</td>
	                <td nowrap class="2-td2-t-left">实缴金额合计</td>
	                <td nowrap class="2-td2-t-center">&nbsp;</td>
	              </tr>
	              <!--The loop begin-->
	              <%int intCount =0; %>
                <bean:define id="jkslist" name="gtgshZfjksForm" property="dataList"/>
                <logic:iterate indexId="index" name="jkslist" id="itemMap">
          		<bean:define id="item" name="itemMap"/>
	              <tr id="<ttsoft:write name="item" property="sbhzdh"/>" style="cursor:hand;">
	                <td nowrap class="2-td2-left"><ttsoft:write name="item" property="sbhzdh"/>&nbsp;</td>
	                <td nowrap class="2-td2-left"><ttsoft:write name="item" property="zs"/>&nbsp;</td>
	                <td nowrap class="2-td2-left"><ttsoft:write name="item" property="sjse"/>&nbsp;</td>
	                <td nowrap class="2-td2-center"><img onclick="cxhzFunc('<ttsoft:write name="item" property="jsjdm"/>','<ttsoft:write name="item" property="sbhzdh"/>','<ttsoft:write name="item" property="sbbh"/>');" style="cursor:hand" onMouseOver="MM_swapImage('ds<%=intCount%>','','<%=static_contextpath%>images/b-cx2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="作废" id="ds<%=intCount%>" src="<%=static_contextpath%>images/b-cx1.jpg" width="79" border="0" height="22"></td>
	              </tr>
	              <%intCount++;%>
                </logic:iterate>
                <!--The end of loop-->
             </table>
          </td>
        </tr>
        <tr><td height="1" ></td></tr>
        <tr>
          <td nowrap >
            <logic:equal value="01" name="gtgshZfjksForm" property="lw">
            <table  width="100%" id="detlist" border='0' cellpadding='0' cellspacing='0' align='left' class='black9'>
              <tr>
                <td width="31%" nowrap class="2-td1-left" >申报编号</td>
                <td width="30%" nowrap class="2-td1-left" >实缴金额</td>
                <td width="19%" nowrap class="2-td1-center" >&nbsp;
                </td>
              </tr>
              <%int detCount = 0; %>
              <bean:define id="detlist" name="gtgshZfjksForm" property="detailList"/>
              <logic:iterate indexId="index2" name="detlist" id="itemDetMap">
              <bean:define id="item2" name="itemDetMap"/>
              <tr id="<ttsoft:write name="item2" property="sbhzdh"/>" style="display:none">
                <td nowrap class="2-td2-left" ><ttsoft:write name="item2" property="sbbh"/>&nbsp;</td>
                <td nowrap class="2-td2-left" ><ttsoft:write name="item2" property="sjse"/>&nbsp;</td>
                <!--<img onclick="chakanDetail('<ttsoft:write name="item2" property="ypys"/>','<ttsoft:write name="item2" property="jkpzh"/>')" style="cursor:hand" onMouseOver="MM_swapImage('ckxxsj<%=detCount%>','','<%=static_contextpath%>images/b-ckxxsj2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="查看详细数据" id="ckxxsj<%=detCount%>" src="<%=static_contextpath%>images/b-ckxxsj1.jpg" width="95" height="22"></td>-->
                <td nowrap class="2-td2-center" >
                  <img onclick="printSbjkdFunc('<ttsoft:write name="item2" property="sbbh"/>','<bean:write name="gtgshZfjksForm" property="jsjdm" />')" style="cursor:hand" onMouseOver="MM_swapImage('ckxxsj<%=detCount%>','','<%=static_contextpath%>images/b-ckxxsj2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="查看详细数据" id="ckxxsj<%=detCount%>" src="<%=static_contextpath%>images/b-ckxxsj1.jpg" width="95" height="22">
                </td>
              </tr>
              <% detCount++; %>
            </logic:iterate>
            </table>
            </logic:equal>
            <logic:equal value="00" name="gtgshZfjksForm" property="lw">
            <table  width="100%" id="detlist" border='0' cellpadding='0' cellspacing='0' align='left' class='black9'>
              <tr>
                <td width="31%" nowrap class="2-td1-left" >缴款凭证号</td>
                <td width="30%" nowrap class="2-td1-left" >实缴金额</td>
                <td width="19%" nowrap class="2-td1-center" >&nbsp;
                </td>
              </tr>
              <%int detCount = 0; %>
              <bean:define id="detlist" name="gtgshZfjksForm" property="detailList"/>
              <logic:iterate indexId="index2" name="detlist" id="itemDetMap">
              <bean:define id="item2" name="itemDetMap"/>
              <tr id="<ttsoft:write name="item2" property="sbhzdh"/>" style="display:none">
                <td nowrap class="2-td2-left" ><ttsoft:write name="item2" property="jkpzh"/>&nbsp;</td>
                <td nowrap class="2-td2-left" ><ttsoft:write name="item2" property="sjse"/>&nbsp;</td>
                <!--<img onclick="chakanDetail('<ttsoft:write name="item2" property="ypys"/>','<ttsoft:write name="item2" property="jkpzh"/>')" style="cursor:hand" onMouseOver="MM_swapImage('ckxxsj<%=detCount%>','','<%=static_contextpath%>images/b-ckxxsj2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="查看详细数据" id="ckxxsj<%=detCount%>" src="<%=static_contextpath%>images/b-ckxxsj1.jpg" width="95" height="22"></td>-->
                <td nowrap class="2-td2-center" >
                  <img onclick="printFunc('<ttsoft:write name="item2" property="jkpzh"/>','<bean:write name="gtgshZfjksForm" property="jsjdm" />')" style="cursor:hand" onMouseOver="MM_swapImage('ckxxsj<%=detCount%>','','<%=static_contextpath%>images/b-ckxxsj2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="查看详细数据" id="ckxxsj<%=detCount%>" src="<%=static_contextpath%>images/b-ckxxsj1.jpg" width="95" height="22">
                </td>
              </tr>
              <% detCount++; %>
            </logic:iterate>
            </table>
          </logic:equal>

          </td>
        </tr>
        <tr>
          <td>
            <table cellspacing="0" class="table-99" width="99%" height="27">
              <tr class="black9">
                <td width="40%">&nbsp;</td>
                <td width="10%"><input type="image" accesskey="c" tabIndex="-1" onClick="tuichu();return false;" style="cursor:hand" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" border="0" height="22"></td>
                <td width="40%">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <br>

    <!-- </td>
 </tr>
</table> -->
</html:form>


<%@ include file="./include/footer.jsp"%>
	</td>
 </tr>
</table>
<p>
</body>
<script language=JavaScript >
var jsArr_jkslist= new Array();
var jkslist_list=new DTable(jkslist,jsArr_jkslist);

function cxhzFunc(dm,hzdh,sbbh){
  if (dm!="" && hzdh!="" && sbbh!=""){
    document.gtgshZfjksForm.jsjdm.value=dm;
    document.gtgshZfjksForm.sbhzdh.value=hzdh;
    document.gtgshZfjksForm.sbbh.value=sbbh;
    document.forms[0].target="";
    //alert(document.gtgshZfjksForm.jsjdm.value+"\n"+document.gtgshZfjksForm.sbhzdh.value+"\n"+document.gtgshZfjksForm.hzrq.value);
    doSubmitForm('Delete');
  }
}

function befQuery(){
  document.forms[0].target="";
  document.forms[0].actionType.value="Query";
  document.forms[0].submit();
  //doSubmitForm('Query')
}

function printFunc(pzh,dm){
  if (pzh!="" && dm!=""){
    document.forms[0].jkpzh.value=pzh;
    document.forms[0].jsjdm.value=dm;
    document.forms[0].target="blank";
    //alert(document.gtgshCxjksForm.headJkpzh.value);
    //alert(document.gtgshCxjksForm.headJsjdm.value);
    doSubmitForm('Print');
  }
}

function printSbjkdFunc(pzh,dm){
  if (pzh!="" && dm!=""){
    document.forms[0].sbbh.value=pzh;
    document.forms[0].jsjdm.value=dm;
    document.forms[0].target="blank";
    //alert(document.gtgshCxjksForm.headJkpzh.value);
    //alert(document.gtgshCxjksForm.headJsjdm.value);
    doSubmitForm('Print');
  }
}

function chakanDetail(bs,hm){
  if (bs!="" && hm!="") {
    document.gtgshZfjksForm.jkpzh.value=hm;
    //alert(bs+"<>"+hm);
    doSubmitForm(bs);
  }
}

function showDetail(zbTable,mxTable){
  var row=jkslist_list.doGetRow(jkslist_list.CurrentRow);
  var detlist=document.all(mxTable);
  for(var i=1;i<detlist.rows.length;i++){
    if(detlist.rows[i].id==row.id){
      detlist.rows[i].style.display='';
    }else{
      detlist.rows[i].style.display='none';
    }
  }
}
</script>
</html:html>
