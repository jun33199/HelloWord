<%@ page contentType="text/html; charset=gb2312" language="java"  errorPage="" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="com.ttsoft.framework.util.JspUtil"%>
<%@ page import="com.ttsoft.bjtax.dj.model.ZJG"%>
<%@ page import="com.ttsoft.bjtax.smsb.wrkcx.processor.DjxxxxBO"%>
<%@ page import="com.ttsoft.bjtax.smsb.wrkcx.processor.ConstantKey"%>
<%@ page import="com.ttsoft.bjtax.smsb.wrkcx.web.WebConstantKey"%>
<html:html>
<%
    String static_file = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
    String head_title = (String)request.getAttribute(ConstantKey.HEAD_TITLE); //title\u00D6\u00B5
    String position = (String)request.getAttribute(ConstantKey.HEAD_POSITION);//\u00B5±\u00C7°\u00CE\u00BB\u00D6\u00C3
    java.util.Vector alertMessages = (java.util.Vector)request.getAttribute(com.ttsoft.framework.util.JspUtil.REQUEST_KEY_ALERT_MESSAGE);
    response.setHeader("pragma", "no-cache");
    response.setHeader("Cache-control", "no-cache, no-store");
    response.setHeader("Expires", "0");
    String actionURL = (String)request.getAttribute(WebConstantKey.RAK_REQUEST_URI);
    DjxxxxBO djBO = (DjxxxxBO)session.getAttribute(WebConstantKey.SESSION_KEY_DJXXXXBO);
    ZJG zjg = djBO.getZjg();
    if(zjg == null){
        zjg = new ZJG();
    }
%>

<head>
<title><%=head_title%></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="<%=static_file%>/js/swapImages.js"></script>
<style type="text/css">
<!--
@import url(<%=static_file%>/css/text.css);
-->
</style>
<link href="<%=static_file%>/css/swdj.css" rel="stylesheet" type="text/css">
<script language="javascript">
function showAlertMessages()
{
<%
    if (alertMessages!=null && alertMessages.size()>0)
    {
        String alertMsg = "";
        for (int i=0;i<alertMessages.size();i++)
        {
            if(alertMsg.length()>0)
            {
                alertMsg += "\n";
            }
            alertMsg += (String)alertMessages.elementAt(i);
        }
%>
        alert("<%=com.ttsoft.framework.util.JspUtil.displayValue(alertMsg,com.ttsoft.framework.util.JspUtil.ESCAPE_FOR_JAVASCRIPT)%>");
<%  }%>
}

function avoidEnter(){
   var e = window.event;
   if(e.srcElement.name){
       if(e.keyCode){
          if(e.keyCode == 13){
          	return false;
          }
       }
   }
   return true;
}
</script>
</head>
<body  bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onLoad="showAlertMessages();initPage();"  onkeydown="return avoidEnter()">
<table width="100%" border="0" cellpadding="0" cellspacing="0" background="<%=static_file%>/images/q-top-bg-01.jpg">
  <tr>
    <td><img width="495" height="58" src="<%=static_file%>/images/q-top-tu-01.jpg"/></td>
    <td align="right"><img width="284" height="58" src="<%=static_file%>/images/q-top-tu-02.jpg"/></td>
  </tr>
</table>

<table width="100%" height="23" border="0" cellpadding="0" cellspacing="0" background="<%=static_file%>/images/q-top-bg-02.jpg" class="black9">
  <tr >
    <td width="81%" class='titleBar'><%=position%></td>
  </tr>
</table>
<script language="javascript" src="<%=static_file%>/js/gmit_selectcontrol.js"></script>
<script language="javascript" src="<%=static_file%>/js/djzhcx.js"></script>
<script LANGUAGE="javascript" src="<%=static_file%>/js/inputchecker.js"></script>
<script language="javascript">
    function initPage(){ MM_preloadImages('<%=static_file%>/images/chaxun2.jpg','<%=static_file%>/images/tuichu2.jpg','<%=static_file%>/images/diyiye2.jpg','<%=static_file%>/images/shangyiye2.jpg','<%=static_file%>/images/zuimoye2.jpg','<%=static_file%>/images/xaiyiye2.jpg','<%=static_file%>/images/b-dcexcelb2.jpg');
    }
    //跳转到分支机构
    function doJbsj(){
        document.forms[0].actionType.value = "Jbsj";
        document.forms[0].submit();
    }
    //跳转到分支机构
    function doFzjgxx(){
        document.forms[0].actionType.value = "Fzjgxx";
        document.forms[0].submit();
    }
    //跳转到银行帐号信息
    function doYhzhxx(){
        document.forms[0].actionType.value = "Yhzhxx";
        document.forms[0].submit();
    }
    //跳转到变更信息
    function doBgxx(){
        document.forms[0].actionType.value = "Bgxx";
        document.forms[0].submit();
    }
    //跳转到其他状态信息
    function doQtztxx(){
        document.forms[0].actionType.value = "Qtztxx";
        document.forms[0].submit();
    }
</script>
<html:form action="/webapp/smsb/nsrxxAction.do" method="post">
<html:hidden property="actionType"/>
<html:hidden property="backAction"/>
<table  width="100%" height='61%' border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
     <!-- InstanceBeginEditable name="02" -->
    <td valign=top>
      <font color="#57937D">
      <table align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1">登记详细信息</td>
        </tr>
        <tr>
          <td class="1-td2">
		   <table width="100%" border="0" cellpadding="0" cellspacing="0" background="<%=static_file%>/images/biaopqian-bg.jpg">
              <tr>
                <td align="center" class="black9">&nbsp; </td>
                  <td align="center" class="black9"><div align="left">
                  <img onclick="doJbsj()" onmouseover="this.style.cursor='hand'" onmouseout="this.style.cursor='default'" width="114" height="42" border="0" src="<%=static_file%>/images/x-w-jbxx-11.jpg">
                  &nbsp;&nbsp;<img onclick="doFzjgxx()" onmouseover="this.style.cursor='hand'" onmouseout="this.style.cursor='default'" width="114" height="42" border="0" src="<%=static_file%>/images/x-w-jbxx-55.jpg">
                  &nbsp;&nbsp;<img width="114" height="42" border="0" src="<%=static_file%>/images/zjg.jpg">
                  &nbsp;&nbsp;<img onclick="doYhzhxx()" onmouseover="this.style.cursor='hand'" onmouseout="this.style.cursor='default'" width="114" height="42" border="0" src="<%=static_file%>/images/biaopqian-yinghzh-01.jpg">
                  &nbsp;&nbsp;<img onclick="doBgxx()" onmouseover="this.style.cursor='hand'" onmouseout="this.style.cursor='default'" width="114" height="42" border="0" src="<%=static_file%>/images/biaoqian-bg1.jpg">
                  &nbsp;&nbsp;<img onclick="doQtztxx()" onmouseover="this.style.cursor='hand'" onmouseout="this.style.cursor='default'" width="114" height="42" border="0" src="<%=static_file%>/images/biaoqian-qtztxx1.jpg">
                </div></td>
              </tr>
            </table>
              <table cellspacing="0" class="table-99">
                <tr bgcolor="#E7EEF1" id='codeArea' style='display:block'>
                  <td height="21" nowrap class="2-td1-left"> <div align="right">计算机代码</div></td>
                  <td colspan="3" nowrap class="2-td1-center"> <div align="left">
                      <%=JspUtil.displayValue(zjg.getZjgjsjdm())%>&nbsp;</div></td>
                </tr>
                <tr bgcolor="#E7EEF1">
                  <td height="21" nowrap class="2-td2-left"><div align="right">税务登记证号</div></td>
                  <td colspan="3" nowrap class="2-td2-center"> <div align="left">
                      <%=JspUtil.displayValue(zjg.getZjgswdjzh())%>&nbsp;</div></td>
                </tr>
                <tr>
                  <td width="21%" nowrap class="2-td2-left"> <div align="right">名称</div></td>
                  <td colspan="3" class="2-td2-center"> <div align="left"><%=JspUtil.displayValue(zjg.getNsrmc())%>&nbsp;</div>
                    <div align="right"></div>
                    <div align="left"> </div></td>
                </tr>
                <tr>
                  <td nowrap class="2-td2-left"><div align="right">地址</div></td>
                  <td colspan="3" class="2-td2-center"> <div align="left"><%=JspUtil.displayValue(zjg.getScjydz())%>&nbsp;</div></td>
                </tr>
                <tr>
                  <td nowrap class="2-td2-left"><div align="right">联系电话</div></td>
                  <td colspan="3" class="2-td2-center"> <div align="left"><%=JspUtil.displayValue(zjg.getScjydzlxdh())%>&nbsp;</div></td>
                </tr>
                <tr>
                  <td nowrap class="2-td2-left"> <div align="right">法人代表名称</div></td>
                  <td colspan="3" class="2-td2-left"> <div align="left"><%=JspUtil.displayValue(zjg.getFrdbxm())%>&nbsp;</div></td>
         </tr>
              </table>

            <table width="100%" border="0" align="center">
              <tr>
                <td width="29%" align="center">
                     <img onclick="javascript:window.close();" src="<%=static_file%>/images/guanbi1.jpg" name="Image31" width="79" height="22" border="0" id="Image31" onMouseOver="MM_swapImage('Image31','','<%=static_file%>/images/guanbi2.jpg',1)" onMouseOut="MM_swapImgRestore()">                </td>
              </tr>
            </table>
            </td>
        </tr>
      </table>
      </font>
<p>&nbsp;</p>
      <p>&nbsp;</p></td>
    <!-- InstanceEndEditable -->
  </tr>
</table>
</body>
</html:form>
</html:html>