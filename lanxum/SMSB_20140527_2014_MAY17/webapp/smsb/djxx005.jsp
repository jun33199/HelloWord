<%@ page contentType="text/html; charset=gb2312" language="java"  errorPage=""%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="com.ttsoft.framework.util.JspUtil"  %>
<%@ page import="com.ttsoft.bjtax.dj.model.JBSJ"%>
<%@ page import="com.ttsoft.bjtax.dj.model.QYRY"%>
<%@ page import="com.ttsoft.bjtax.dj.model.TZF"%>
<%@ page import="com.ttsoft.bjtax.dj.model.SWDL"%>
<%@ page import="com.ttsoft.bjtax.dj.model.ZJG"%>
<%@ page import="com.ttsoft.bjtax.dj.DjCodeConstant"%>
<%@ page import="com.ttsoft.bjtax.dj.util.CodeTableKey"%>
<%@ page import="com.ttsoft.bjtax.dj.util.CodeTableUtil"%>
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
    JBSJ jbsj = djBO.getJbsj();	
    ZJG zjg = djBO.getZjg();
    SWDL swdl = djBO.getSwdl();
    if(swdl == null){
        swdl = new SWDL();
    }

    if(zjg == null){
        zjg = new ZJG();
    }
    List tzfList = djBO.getTzfList();
    List qyryList = djBO.getLxrList();
    QYRY frdb = new QYRY();
    QYRY cwry = new QYRY();
    QYRY bsry = new QYRY();
    for(int i = 0; i < qyryList.size(); i++){
        QYRY qyry = (QYRY)qyryList.get(i);
        if(DjCodeConstant.ZWDM_BSRY.equals(qyry.getZwdm())){
            bsry = qyry;
        }else if(DjCodeConstant.ZWDM_CWRY.equals(qyry.getZwdm())){
            cwry = qyry;
        }else if(DjCodeConstant.ZWDM_FRDB.equals(qyry.getZwdm())){
            frdb = qyry;
        }
    }
    String flag = "否";
    String ggh = JspUtil.displayValue(jbsj.getGdsgghbs());
    if(DjCodeConstant.GDSGGHBS_GDSGGH.equals(ggh)){
        flag = "是";
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
<script LANGUAGE="javascript" src="<%=static_file%>/js/inputchecker.js"></script>
<script language="javascript" src="<%=static_file%>/js/djzhcx.js"></script>
<script language="javascript" src="<%=static_file%>/js/swapImages.js"></script>
<script language="javascript">
    function initPage(){ MM_preloadImages('<%=static_file%>/images/chaxun2.jpg','<%=static_file%>/images/tuichu2.jpg','<%=static_file%>/images/diyiye2.jpg','<%=static_file%>/images/shangyiye2.jpg','<%=static_file%>/images/zuimoye2.jpg','<%=static_file%>/images/xaiyiye2.jpg','<%=static_file%>/images/b-dcexcelb2.jpg');
    }
    //跳转到分支机构
    function doFzjgxx(){
        document.forms[0].actionType.value = "Fzjgxx";
        document.forms[0].submit();
    }
    //跳转到总机构信息
    function doZjgxx(){
        document.forms[0].actionType.value = "Zjgxx";
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
    <td valign="top">
      <table cellspacing="0"  class="table-99">
        <tr>
          <td class="1-td1" > <div align="center"> 登记详细信息</div></td>
        </tr>
        <tr>
          <td class="1-td2" valign=top>
		     <table width="100%" border="0" cellpadding="0" cellspacing="0" background="<%=static_file%>/images/biaopqian-bg.jpg">
              <tr>
                <td align="center" class="black9">&nbsp; </td>
                  <td align="center" class="black9"><div align="left">
                  <img width="114" height="42" src="<%=static_file%>/images/x-w-jbxx-1.jpg">
                  &nbsp;&nbsp;<img onclick="doFzjgxx()" onmouseover="this.style.cursor='hand'" onmouseout="this.style.cursor='default'" width="114" height="42" border="0" src="<%=static_file%>/images/x-w-jbxx-55.jpg">
                  &nbsp;&nbsp;<img onclick="doZjgxx()" onmouseover="this.style.cursor='hand'" onmouseout="this.style.cursor='default'" width="114" height="42" border="0" src="<%=static_file%>/images/zjg2.jpg">
                  &nbsp;&nbsp;<img onclick="doYhzhxx()" onmouseover="this.style.cursor='hand'" onmouseout="this.style.cursor='default'" width="114" height="42" border="0" src="<%=static_file%>/images/biaopqian-yinghzh-01.jpg">
                  &nbsp;&nbsp;<img onclick="doBgxx()" onmouseover="this.style.cursor='hand'" onmouseout="this.style.cursor='default'" width="114" height="42" border="0" src="<%=static_file%>/images/biaoqian-bg1.jpg">
                  &nbsp;&nbsp;<img onclick="doQtztxx()" onmouseover="this.style.cursor='hand'" onmouseout="this.style.cursor='default'" width="114" height="42" border="0" src="<%=static_file%>/images/biaoqian-qtztxx1.jpg">
                </div></td>
              </tr>
		</div>
		</table>

			 <table cellspacing="0"  class="table-99">
                <tr bordercolor="#9BB4CA">
                  <td class="2-td2-t-left">
                  <div align="right"><strong>计算机代码</strong></div></td>
                  <td width="17%" class="2-td2-t-left">
                  <div align="left"><%=JspUtil.displayValue(jbsj.getJsjdm())%>&nbsp;</div></td>
                  <td width="14%" class="2-td2-t-left">
                  <div align="right"><strong>纳税人状态</strong></div></td>
                  <td width="14%" class="2-td2-t-left">
                  <div align="left"><%=JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.NSRZT,jbsj.getNsrzt()))%>&nbsp;</div></td>
                  <td class="2-td2-t-left">
                  <div align="right"><strong>登记注册类型</strong></div></td>
                  <td class="2-td2-t-center">
                  <div align="left"><%=JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.DJZCLX,jbsj.getDjzclxdm()))%>&nbsp;</div></td>
                </tr>
                <tr bordercolor="#9BB4CA">
                  <td class="2-td2-left">
                  <div align="right"><strong>组织机构代码</strong></div></td>
                  <td class="2-td2-left">
                  <div align="left"><%=JspUtil.displayValue(jbsj.getZzjgdm())%>&nbsp;</div></td>
                  <td class="2-td2-left">
                  <div align="right"><strong>批准成立证照或文件号</strong></div></td>
                  <td class="2-td2-left"><div align="left"><%=JspUtil.displayValue(jbsj.getYyzzh())%>&nbsp;</div></td>
                  <td class="2-td2-left">
                  <div align="right"><strong>税务登记证号码</strong></div></td>
                  <td class="2-td2-center"><div align="left"><%=JspUtil.displayValue(jbsj.getSwdjzh())%>&nbsp;</div></td>
                </tr>
                <tr bordercolor="#9BB4CA">
                  <td width="14%" class="2-td2-left">
                  <div align="right"><strong>纳税人名称</strong></div></td>
                  <td colspan="5" class="2-td2-center">
                  <div align="left"> <%=JspUtil.displayValue(jbsj.getNsrmc())%>&nbsp;</div></td>
                </tr>
                <tr bordercolor="#9BB4CA">
                  <td width="14%" class="2-td2-left">
                  <div align="right"><strong>主管税务机关</strong></div></td>
                  <td colspan="1" class="2-td2-left">
                  <div align="left"> <%=JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.SWJGZZJG,jbsj.getSwjgzzjgdm().substring(0,2).concat("00")))%>&nbsp;</div></td>
                  <td width="18%" class="2-td2-left">
                  <div align="right"><strong>主管税务所</strong></div></td>
                  <td colspan="1" class="2-td2-left">
                  <div align="left"><%=JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.SWJGZZJG,jbsj.getSwjgzzjgdm()))%>&nbsp;</div></td>
                  <td class="2-td2-left">
                  <div align="right"><strong>所处街乡</strong></div></td>
                  <td height="22" colspan="1" class="2-td2-center">
                  <div align="left"><%=JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.SCJX,jbsj.getScjxdm()))%>&nbsp;</div></td>
                </tr>
                <tr bordercolor="#9BB4CA">
                  <td class="2-td2-left"><div align="right">
                  <strong>隶属关系</strong></div></td>
                  <td class="2-td2-left">
                  <div align="left"><%=JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.LSGX,jbsj.getLsgxdm()))%>&nbsp;</div></td>
                  <td class="2-td2-left">
                  <div align="right"><strong>国家标准行业</strong></div></td>
                  <td class="2-td2-left">
                  <div align="left"><%=JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.GJBZHY,jbsj.getGjbzhydm()))%>&nbsp;</div></td>
                  <td class="2-td2-left">
                  <div align="right"><strong>会计制度</strong></div></td>
                  <td class="2-td2-center"><div align="left"><%=JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.KJZD,jbsj.getKjzddm()))%>&nbsp;</div></td>
                </tr>
                <tr bordercolor="#9BB4CA">
                  <td class="2-td2-left">
                  <div align="right"><strong>主管部门</strong></div></td>
                  <td colspan="1" class="2-td2-left">
                  <div align="left"><%=JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.ZGBM,jbsj.getZgbmdm()))%>&nbsp;</div></td>
                  <td class="2-td2-left">
                  <div align="right"><strong>核发登记证种类</strong></div></td>
                  <td class="2-td2-left"><div align="left"><%=JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.SWDJZZL,jbsj.getDjzzldm()))%>&nbsp;</div></td>
                  <td class="2-td2-left">
                  <div align="right"><strong>核算形式或预算方式</strong></div></td>
                  <td class="2-td2-center"><div align="left"><%=JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.HSYS,jbsj.getHsxsdm()))%>&nbsp;</div></td>
                </tr>
               <tr bordercolor="#9BB4CA">
                  <td class="2-td2-left">
                  <div align="right"><strong>税务登记类型</strong></div></td>
                  <td class="2-td2-left">
                  <div align="left"><%=JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.SWDJLX,jbsj.getSwdjlx()))%>&nbsp;</div></td>
                  <td class="2-td2-left">
                  <div align="right"><strong>总机构计算机代码</strong></div></td>
                  <td class="2-td2-left"><%=JspUtil.displayValue(zjg.getZjgjsjdm())%>&nbsp;</td>
                  <td class="2-td2-left">
                  <div align="right"><strong>是否共管户</strong></div></td>
                  <td class="2-td2-center"><div align="left"><%=flag%>&nbsp;</div></td>
                </tr>
                <tr bordercolor="#9BB4CA">
                  <td width="14%" class="2-td2-left">
                  <div align="right"><strong>税务登记日期</strong></div></td>
                  <td colspan="1" class="2-td2-center">
                  <div align="left"> <%=JspUtil.displayValue(jbsj.getKydjrq())%>&nbsp;</div></td>
                  <td width="14%" class="2-td2-left">
                  <div align="right"><strong>验换证人员</strong></div></td>
                  <td colspan="1" class="2-td2-left">
                  <div align="left"> <%=JspUtil.displayValue(jbsj.getYhzry())%>&nbsp;</div></td>
                  <td width="18%" class="2-td2-left">
                  <div align="right"><strong>验换证日期</strong></div></td>
                  <td colspan="1" class="2-td2-left">
                  <div align="left"><%=JspUtil.displayValue(jbsj.getYhzrq())%>&nbsp;</div></td>
                </tr>
              </table>
            <table  class="table-99" cellspacing="=0">
              <tr>
                <td > <hr width="100%" size="1" class="hr1" > </td>
                <td width="75" align="center" class="black9">
                <strong>基本信息 </strong></td>
                <td> <hr width="100%" size="1" class="hr1" > </td>
              </tr>
            </table>
        <table cellspacing="0" class="table-99">
          <tr class="black9">
            <td width="9%" rowspan="2" class="2-td2-t-left" nowrap>注册地</td>
            <td class="2-td2-t-left">
            <div align="right">
              地址</div>
            </td>
            <td colspan="3" class="2-td2-t-center">
            <div align="left">
              <%=JspUtil.displayValue(jbsj.getZcdz())%>&nbsp;
            </div>
            <div align="left">
            </div>
            </td>
          </tr>
          <tr bordercolor="#9BB4CA" class="black9">
            <td width="7%" class="2-td2-left">
            <div align="right">
              邮政编码</div>
            </td>
            <td width="23%" class="2-td2-left">
            <div align="left">
              <%=JspUtil.displayValue(jbsj.getZcdzyb())%>&nbsp;
            </div>
            </td>
            <td width="24%" colspan="-1" class="2-td2-left">
            <div align="right">
              联系电话</div>
            </td>
            <td width="37%" class="2-td2-center">
            <div align="left">
            </div>
            <div align="left">
               <%=JspUtil.displayValue(jbsj.getZcdzlxdh())%>&nbsp;
            </div>
            </td>
          </tr>
          <tr class="black9">
            <td width="9%" rowspan="2" class="2-td2-left" nowrap>经营地</td>
            <td class="2-td2-left">
            <div align="right">
              地址</div>
            </td>
            <td colspan="3" class="2-td2-center">
            <div align="left">
            <%=JspUtil.displayValue(jbsj.getJydz())%>&nbsp;
            </div>
            <div align="left">
            </div>
            </td>
          </tr>

          <tr class="black9">
            <td class="2-td2-left">
            <div align="right">
              邮政编码</div>
            </td>
            <td class="2-td2-left">
            <div align="left">
            <%=JspUtil.displayValue(jbsj.getJydzyb())%>&nbsp;
            </div>
            </td>
            <td class="2-td2-left">
            <div align="right">
              联系电话</div>
            </td>
            <td class="2-td2-center">
            <div align="left">
            <%=JspUtil.displayValue(jbsj.getJydzlxdm())%>&nbsp;
            </div>
            </td>
          </tr>
          <tr bordercolor="#9BB4CA" class="black9">
            <td width="9%" class="2-td2-left" nowrap>经营范围</td>
            <td colspan="4" class="2-td2-center" width="100">
                <p style='width:800px;word-break:break-all;'>
				<%=JspUtil.displayValue(jbsj.getJyfw())%>&nbsp;
				</p>
            </td>
          </tr>
          <tr class="black9">
            <td width="15%" rowspan="2" class="2-td2-left" nowrap>
            <div align="center" nowrap>
              注册资本或投资总额</div>
            </td>
            <td width="13%" class="2-td2-left">
            <div align="right">
              币种</div>
            </td>
            <td colspan="3" class="2-td2-center">
            <div align="left">
            <%=JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.BZ,jbsj.getZczbbzdm()))%>&nbsp;
            </div>
            <div align="left">
            </div>
            </td>
          </tr>
          <tr class="black9">
            </td>
            <td rowspan="1" class="2-td2-left">
            <div align="right">
              金额（万元）</div>
            </td>
            <td rowspan="1" class="2-td2-left" colspan="1">
            <div align="left">
            <%=JspUtil.displayValue(jbsj.getZczbje())%>&nbsp;
            </div>
            </td>
            <td width="24%" colspan="-1" class="2-td2-left">
            <div align="right">
              自然人投资比例总和（%）</div>
            </td>
            <td width="37%" class="2-td2-center">
            <div align="left">
            </div>
            <div align="left">
            <%=JspUtil.displayValue(jbsj.getZrrtzblhj())%>&nbsp;
            </div>
            </td>
          </tr>
          <tr class="black9">
            <td width="9%" class="2-td2-left" nowrap>网站网址</td>
            <td colspan="5" class="2-td2-center">
            <div align="left">
              <%=JspUtil.displayValue(jbsj.getQyzy())%>&nbsp;
            </div>
            </td>
          </tr>
        </table>
            <table  cellspacing="0" class="table-99">
              <tr>
                <td > <hr width="100%" size="1" class="hr1" > </td>
                <td width="86" align="center" class="black9">
                <strong> 联系人信息</strong></td>
                <td> <hr width="100%" size="1" class="hr1" > </td>
              </tr>
            </table>
            <table cellspacing="0" border="0" class="table-99" id=TABLE_LIST>
              <tr>
                <td width="11%" class="2-td1-left">　</td>
                <td width="9%" class="2-td1-left">姓名</td>
                <td width="12%" class="2-td1-left">证件类型</td>
                <td width="21%" class="2-td1-left">证件号码</td>
                <td width="20%" class="2-td1-left">固定电话</td>
                <td width="10%" class="2-td1-left">移动电话</td>
                <td width="17%" class="2-td1-center">个人电子邮箱（E-mail)</td>
              </tr>
              <tr>
                <td class="2-td2-left">法人代表(负责人)</td>
                <td nowrap class="2-td2-left"><div align="left"><%=JspUtil.displayValue(frdb.getXm())%>&nbsp;</div></td>
                <td nowrap class="2-td2-left"><div align="left"><%=JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.ZJLX,frdb.getZjlxdm()))%>&nbsp;</div></td>
                <td nowrap class="2-td2-left"><div align="left"><%=JspUtil.displayValue(frdb.getZjhm())%>&nbsp;</div></td>
                <td class="2-td2-left"> <div align="left"><%=JspUtil.displayValue(frdb.getGddh())%>&nbsp;</div></td>
                <td class="2-td2-left"> <div align="left"><%=JspUtil.displayValue(frdb.getYddh())%>&nbsp;</div></td>
                <td class="2-td2-center"><%=JspUtil.displayValue(frdb.getDzyx())%>&nbsp; </td>
              </tr>
              <tr>
                <td class="2-td2-left">财务负责人</td>
                <td nowrap class="2-td2-left"><div align="left"><%=JspUtil.displayValue(cwry.getXm())%>&nbsp;</div></td>
                <td nowrap class="2-td2-left"><div align="left"><%=JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.ZJLX,cwry.getZjlxdm()))%>&nbsp;</div></td>
                <td nowrap class="2-td2-left"> <div align="left"><%=JspUtil.displayValue(cwry.getZjhm())%>&nbsp;</div></td>
                <td class="2-td2-left"> <div align="left"><%=JspUtil.displayValue(cwry.getGddh())%>&nbsp;</div></td>
                <td class="2-td2-left"> <div align="left"><%=JspUtil.displayValue(cwry.getYddh())%>&nbsp;</div></td>
                <td class="2-td2-center"><%=JspUtil.displayValue(cwry.getDzyx())%>&nbsp; </td>
              </tr>
              <tr>
                <td class="2-td2-left">办税人员</td>
                <td nowrap class="2-td2-left"> <div align="left"><%=JspUtil.displayValue(bsry.getXm())%>&nbsp;</div></td>
                <td nowrap class="2-td2-left"><div align="left"><%=JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.ZJLX,bsry.getZjlxdm()))%>&nbsp;</div></td>
                <td nowrap class="2-td2-left"> <div align="left"><%=JspUtil.displayValue(bsry.getZjhm())%>&nbsp;
                  </div></td>
                <td class="2-td2-left"> <div align="left"><%=JspUtil.displayValue(bsry.getGddh())%>&nbsp;</div></td>
                <td class="2-td2-left"> <div align="left"><%=JspUtil.displayValue(bsry.getYddh())%>&nbsp;</div></td>
                <td class="2-td2-center"><%=JspUtil.displayValue(bsry.getDzyx())%>&nbsp; </td>
              </tr>
            </table>
            <table  cellspacing="0" class="table-99">
              <tr>
                <td  > <hr width="100%" size="1" class="hr1" > </td>
                <td width="87" align="center" class="black9"><strong> 税务代理信息</strong></td>
                <td > <hr width="100%" size="1" class="hr1" > </td>
              </tr>
            </table>
            <table cellspacing="0" border="0" class="table-99"   id=TABLE_LIST>
              <tr>
                <td width="12%" class="2-td1-left">　</td>
                <td width="11%" class="2-td1-left">名称</td>
                <td width="20%" class="2-td1-left">税务登记证号</td>
                <td width="19%" class="2-td1-left">固定电话</td>
                <td width="14%" class="2-td1-left">移动电话</td>
                <td width="24%" class="2-td1-center">联系电子邮箱（E-mail)</td>
              </tr>
              <tr>
                <td class="2-td2-left">税务代理</td>
                <td nowrap class="2-td2-left"> <div align="left"> <%=JspUtil.displayValue(swdl.getMc())%>&nbsp;</div></td>
                <td nowrap class="2-td2-left"> <div align="left"><%=JspUtil.displayValue(swdl.getSwdjzh())%>&nbsp;</div></td>
                <td class="2-td2-left"> <div align="left"><%=JspUtil.displayValue(swdl.getGddh())%>&nbsp;</div></td>
                <td class="2-td2-left"> <div align="left"><%=JspUtil.displayValue(swdl.getSwdlyddh())%>&nbsp;</div></td>
                <td class="2-td2-center"><%=JspUtil.displayValue(swdl.getDzyx())%>&nbsp;</td>
              </tr>
            </table>
              <table cellspacing="0" border=0 class="table-99">
                <tr>
                  <td > <hr width="100%" size="1" class="hr1" > </td>
                  <td width="123" align="center" class="black9"><strong>投资方登记信息</strong></td>
                  <td > <hr width="100%" size="1" class="hr1" ></td>
                </tr>
              </table>
              <div id="Layer2" style=" overflow:auto;  marginwidth: 0; scrolling: auto; ">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99" id='Table_Master' style="TABLE-LAYOUT: fixed">
              <tr>
                <td width="7%" nowrap bordercolor="#9DB9D2" bgcolor="#E7EEF1" class="2-td1-left">
                  <div align="center">序号</div></td>
                <td width="38%" nowrap bordercolor="#9DB9D2" bgcolor="#E7EEF1" class="2-td1-left">
                  <div align="center">投资方名称</div></td>
                <td width="14%" nowrap bordercolor="#9DB9D2" bgcolor="#E7EEF1" class="2-td1-left">
                  <div align="center">证件类型</div></td>
                <td width="24%" nowrap bordercolor="#9DB9D2" bgcolor="#E7EEF1" class="2-td1-left">
                  <div align="center">证件号码</div></td>
                <td width="17%" nowrap bordercolor="#9DB9D2" bgcolor="#E7EEF1" class="2-td1-center">
                  <div align="center">所占投资比例(%)</div></td>
              </tr>
              <%for(int i = 0; i < tzfList.size(); i++){
                  TZF tzf = (TZF)tzfList.get(i);
              %>
              <tr>
                <td nowrap bordercolor="#9DB9D2" bgcolor="#E7EEF1" class="2-td2-left"><%=i + 1%></td>
                <td nowrap bordercolor="#9DB9D2" bgcolor="#E7EEF1" class="2-td2-left"><div align="left"><%=JspUtil.displayValue(tzf.getTzfmc())%>&nbsp;</div></td>
                <td nowrap bordercolor="#9DB9D2" bgcolor="#E7EEF1" class="2-td2-left"><%=JspUtil.displayValue(CodeTableUtil.getTextByValue(CodeTableKey.ZJLX,tzf.getZjlxdm()))%>&nbsp;</td>
                <td nowrap bordercolor="#9DB9D2" bgcolor="#E7EEF1" class="2-td2-left"><%=JspUtil.displayValue(tzf.getZjhm())%>&nbsp;</td>
                <td nowrap bordercolor="#9DB9D2" bgcolor="#E7EEF1" class="2-td2-center"><%=JspUtil.displayValue(tzf.getTzbl())%>&nbsp;</td>
              </tr>
              <%}%>
            </table>
              <table cellspacing="0" border=0 class="table-99">
                <tr>
                  <td > <hr width="100%" size="1" class="hr1" > </td>
                  <td width="123" align="center" class="black9"><strong>受理信息</strong></td>
                  <td > <hr width="100%" size="1" class="hr1" ></td>
                </tr>
              </table>
            <table cellspacing="0" border="0" class="table-99"   id=TABLE_LIST>
              <tr>
                <td width="50%" class="2-td1-left">受理人</td>
                <td width="50%" class="2-td1-center">受理日期</td>
              </tr>
              <tr>
                <td class="2-td2-left"><%=JspUtil.displayValue(jbsj.getQrry())%></td>
                <td class="2-td2-center"><%=JspUtil.displayValue(jbsj.getQrrq())%>&nbsp;</td>
              </tr>
            </table>
			</div>
            <table width="100%" border="0" align="center">
              <tr>
                <td width="35%" align="center">
                     <img onclick="javascript:window.close();" src="<%=static_file%>/images/guanbi1.jpg" name="Image31" width="79" height="22" border="0" id="Image31" onMouseOver="MM_swapImage('Image31','','<%=static_file%>/images/guanbi2.jpg',1)" onMouseOut="MM_swapImgRestore()">
                </td>
              </tr>
            </table>
           </td>
        </tr>
      </table>
      <p>　</p>
  </tr>
</table>
</body>
</html:form>
</html:html>
