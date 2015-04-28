<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>个体工商户汇总缴款书</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/function.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file="./include/header.jsp"%>

<html:form action="/webapp/smsb/gtgshHzjksAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden property="ypys" />
<html:hidden property="lrr" />
<html:hidden property="jkpzh" />
<html:hidden property="jsjdm" />
<html:hidden property="lw"/>


<html:hidden property="sbbh" />
<html:hidden property="sbhzdh" />
<html:hidden property="hjje" size="2" />
<html:hidden property="hjzs" size="2" />

<TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9' height="300">
 <tr>
    <td valign="top" height="300"> <br>
      <table align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1"  colspan="2">收现缴款书汇总</td>
        </tr>
        <tr>
                <td class="1-td2" > <br>
			<table border="0" width="98%">
                    <tr class="black9">
                      <td colspan="5">&nbsp;</td>
                      <td>征收机关名称：</td>
                      <td colspan="2"><bean:write name="gtgshHzjksForm" property="swjgzzjgmc"/><html:hidden property="swjgzzjgmc"/> <html:hidden property="swjgzzjgdm"/></td>
                    </tr>
                    <tr class="black9">
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr class="black9">
                      <td width="10%">选择汇总类型：</td>
                      <td width="16%">
                        <ttsoft:select property="hzlx" codekey="WSZ_HZLX"/>
                        </td>
                      <td width="10%"> 汇总起始日期：</td>
                      <td width="9%"><b>
                        <html:text property="hzksrq" size="10" onkeydown="if(event.keyCode==13) event.keyCode=9;" />
                        </b></td>
                      <td width="11%">汇总截止日期<b>：</b></td>
                      <td width="12%"><b>
                        <html:text property="hzjsrq" size="10" onkeydown="if(event.keyCode==13) event.keyCode=9;" />
                        </b></td>
                      <!--<td width="14%"><input type="image" accesskey="k" tabIndex="-1" onclick="hzQuery('2');return false;" style="cursor:hand;" onMouseOver="MM_swapImage('hzsbjks1','','<%=static_contextpath%>images/hzsbjks-k2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="汇总申报缴款书" id="hzsbjks1" src="<%=static_contextpath%>images/hzsbjks-k1.jpg" width="112" height="22"></td>-->
                      <td width="18%"><input type="image" accesskey="z" tabIndex="-1" onclick="hzQuery('1');return false;" style="cursor:hand;" onMouseOver="MM_swapImage('hzjks1','','<%=static_contextpath%>images/hzjks-z2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="汇总缴款书" id="hzjks1" src="<%=static_contextpath%>images/hzjks-z1.jpg" width="112" height="22"></td>
                    </tr>
                  </table>
             <br>
             <logic:equal name="gtgshHzjksForm" value="01" property="lw">
               <table width="100%" border="0" cellpadding="0" cellspacing="0" height="97">
                <%int intCount =0; %>
               <bean:define id="bhslist" name="gtgshHzjksForm" property="bhList"/>
               <logic:iterate indexId="index" name="bhslist" id="itemMap">
               <bean:define id="item" name="itemMap"/>
                 <tr>
                   <%
                   if(intCount==0){
                   %>
                   <td nowrap class="2-td2-t-left" height="23">申报汇总单号</td>
                   <td height="23" nowrap class="2-td2-t-left">
                     <ttsoft:write name="item" property="sbhzdh"/>
                   </td>
                   <td nowrap class="2-td2-t-left" height="23">申报编号</td>
                   <td height="23" nowrap class="2-td2-t-left">
                     <ttsoft:write name="item" property="sbbh"/>
                   </td>
                   <td height="23" nowrap class="2-td2-t-center">
                     <img onclick="printFunc('<ttsoft:write name="item" property="sbbh"/>','<bean:write name="gtgshHzjksForm" property="jsjdm" />')" style="cursor:hand;" onMouseOver="MM_swapImage('ckxxsj<%=intCount%>','','<%=static_contextpath%>images/b-ckxxsj2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="查看详细数据" id="ckxxsj<%=intCount%>" src="<%=static_contextpath%>images/b-ckxxsj1.jpg" width="95" height="22">
                   </td>
                   <%
                   }else{
                   %>
                    <td nowrap class="2-td2-left" height="23">申报汇总单号</td>
                   <td height="23" nowrap class="2-td2-left">
                     <ttsoft:write name="item" property="sbhzdh"/>
                   </td>
                   <td nowrap class="2-td2-left" height="23">申报编号</td>
                   <td height="23" nowrap class="2-td2-left">
                     <ttsoft:write name="item" property="sbbh"/>
                   </td>
                   <td height="23" nowrap class="2-td2-center">
                     <img onclick="printFunc('<ttsoft:write name="item" property="sbbh"/>','<bean:write name="gtgshHzjksForm" property="jsjdm" />')" style="cursor:hand;" onMouseOver="MM_swapImage('ckxxsj<%=intCount%>','','<%=static_contextpath%>images/b-ckxxsj2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="查看详细数据" id="ckxxsj<%=intCount%>" src="<%=static_contextpath%>images/b-ckxxsj1.jpg" width="95" height="22">
                   </td>
                   <%  	   
                   }
                   %>
                 </tr>
                 <%intCount++;%>
                 <!--  
                 <tr>
                   <td colspan="4" nowrap class="2-td2-center" height="23">
                     <img onclick="printFunc('<bean:write name="gtgshHzjksForm" property="sbbh"/>','<bean:write name="gtgshHzjksForm" property="jsjdm" />')" style="cursor:hand;" onMouseOver="MM_swapImage('ckxxsj','','<%=static_contextpath%>images/b-ckxxsj2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="查看详细数据" id="ckxxsj" src="<%=static_contextpath%>images/b-ckxxsj1.jpg" width="95" height="22">
                   </td>
                 </tr>
                 -->
                </logic:iterate>
               </table>
             </logic:equal>
             
             <logic:notEqual name="gtgshHzjksForm" value="01" property="lw">
               <table width="100%" border="0" cellpadding="0" cellspacing="0" height="97">
                 <%int intZCount =0; %>
                 <bean:define id="bhslist" name="gtgshHzjksForm" property="bhList"/>
                 <logic:iterate indexId="index" name="bhslist" id="itemMap">
                 <bean:define id="item" name="itemMap"/>
                 <%
                  if(intZCount==0){
                 %>
                 <tr>
                   <td nowrap class="2-td2-t-left" height="23">申报汇总单号</td>
                   <td height="23" colspan="2" nowrap class="2-td2-t-center"><ttsoft:write name="item" property="sbhzdh"/>&nbsp;</td>
                 </tr>
                 <%
                   }else{
                 %>
                 <tr>
                   <td nowrap class="2-td2-left" height="23">申报汇总单号</td>
                   <td height="23" colspan="2" nowrap class="2-td2-center"><ttsoft:write name="item" property="sbhzdh"/>&nbsp;</td>
                 </tr>
                 <% 
                  }
                 %>
                  
                 <tr>
                   <td nowrap class="2-td2-left" height="23">缴款凭证号</td>
                   <td nowrap class="2-td2-left" height="23">实缴金额</td>
                   <td nowrap class="2-td2-center" height="23">&nbsp;</td>
                 </tr>
                 
                  <% 
                    List dataList=(List)((HashMap)item).get("dataList");
                    int intCount =0;
                    for(int k=0;k<dataList.size();k++){
                    	HashMap flwMap=(HashMap)dataList.get(k);
                    %>
                    <tr>
                     <td nowrap class="2-td2-left" height="18"><%=flwMap.get("jkpzh")%>&nbsp;</td>
                     <td nowrap class="2-td2-left" height="18"><%=flwMap.get("sjse")%>&nbsp;</td>
                     <!--<td nowrap class="2-td2-center" height="18"><img onclick="chakanDetail('<ttsoft:write name="item" property="jkpzh"/>')" style="cursor:hand;" onMouseOver="MM_swapImage('ckxxsj<%=intCount%>','','<%=static_contextpath%>images/b-ckxxsj2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="查看详细数据" id="ckxxsj<%=intCount%>" src="<%=static_contextpath%>images/b-ckxxsj1.jpg" width="95" height="22"></td>-->
                     <td nowrap class="2-td2-center" height="18"><img onclick="printFunc('<%=flwMap.get("jkpzh")%>','<bean:write name="gtgshHzjksForm" property="jsjdm" />')" style="cursor:hand;" onMouseOver="MM_swapImage('ckxxsj<%=intCount%>','','<%=static_contextpath%>images/b-ckxxsj2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="查看详细数据" id="ckxxsj<%=intCount%>" src="<%=static_contextpath%>images/b-ckxxsj1.jpg" width="95" height="22"></td>
                    </tr>
                    <%
                    intCount++;
                    }
                    %>
                 <!--The end of loop-->
                 <tr>
                   <!--<td nowrap class="2-td2-left" height="28">共 <bean:write name="gtgshHzjksForm" property="hjzs"/> 张<html:hidden property="hjzs" size="2" /></td>-->
                   <td nowrap class="2-td2-left" height="28">共 <%=intCount %> 张</td>
                   <td nowrap class="2-td2-left" height="28">计算合计：<ttsoft:write name="item" property="hjje"/></td>
                   <td nowrap class="2-td2-center" height="28">&nbsp;</td>
                 </tr>
                 
                 <%
                   intZCount++;
                 %>
                 </logic:iterate> 
                 
               </table>
             </logic:notEqual>
             <br>
             <table border="0" width="100%">
               <tr>
                  <td width="35%"> </td>
                  <td width="9%"><input type="image" accesskey="c" tabIndex="-1" onClick="tuichu();return false;" style="cursor:hand" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" border="0" height="22"></td>
                  <td width="33%">&nbsp;</td>
               </tr>
             </table>
			<br>
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
</body>
<script language=JavaScript >
function hzQuery(hzfs){
  //alert(hzfs);
  if (isDate(document.gtgshHzjksForm.hzksrq,"") && isDate(document.gtgshHzjksForm.hzjsrq)){
    if (compare(document.gtgshHzjksForm.hzksrq,document.gtgshHzjksForm.hzjsrq)>=0){
      if(hzfs=="2"){
        //一票多税
        document.gtgshHzjksForm.ypys.value=hzfs;
        document.forms[0].target="";
        doSubmitForm('Save');
      }
      if (hzfs=="1"){
        //一票一税
        document.gtgshHzjksForm.ypys.value=hzfs;
        document.forms[0].target="";
        doSubmitForm('Save');
      }
    }
    else {
      alert("起始日期必须小于或等于截止日期！");
    }
  }
}

function printFunc(pzh,dm){
  if (pzh!="" && dm!=""){
    document.forms[0].jkpzh.value=pzh;
    document.forms[0].jsjdm.value=dm;
    document.forms[0].sbbh.value=pzh;
    document.forms[0].target="blank";
    //alert(document.gtgshCxjksForm.headJkpzh.value);
    //alert(document.gtgshCxjksForm.headJsjdm.value);
    doSubmitForm('Print');
  }
}

function chakanDetail(hm){
  if (hm!="") {
    document.gtgshHzjksForm.jkpzh.value=hm;
    //一票一税
    if (document.gtgshHzjksForm.ypys.value=="1"){
      //document.gtgshHzjksForm.target="blank";
      doSubmitForm('Ypys');
    }
    //一票多税
    if (document.gtgshHzjksForm.ypys.value=="2"){
      //document.gtgshHzjksForm.target="blank";
      doSubmitForm('Ypds');
    }
  }
}

</script>

</html:html>
