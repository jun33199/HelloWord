<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.ReceivePlslForm"%>
<%@ page import="java.util.ArrayList"%>
<HTML><HEAD><TITLE>录入土地房屋基础信息表</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<script language="javascript" src="<%=static_file%>/js/inputchecker.js"></script>
<script language="javascript" src="../js/qscommon.js"></script>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>



<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
  <jsp:include page="/include/Header.jsp" flush="true">
   <jsp:param name="subtitle" value="批量受理>批量申报受理"/>
    <jsp:param name="helpURL" value=""/>
  </jsp:include>

<script language=JavaScript>
function doSubmitForm(operationType)
{
	document.forms[0].operationType.value=operationType;
	document.forms[0].submit();
}
 </script>

<html:form action="/plsl/plslReceive.do">
<html:hidden property="operationType"/>

<%

ReceivePlslForm aform = (ReceivePlslForm)session.getAttribute("receivePlslForm");

ArrayList alist = aform.getSptsList();
ArrayList blist = aform.getSpjeList();
ArrayList clist = aform.getSpyyList();
ArrayList dlist = aform.getCqbceList();
ArrayList elist = aform.getCqsyeList();
ArrayList flist = aform.getGfcjjgList();
ArrayList glist = aform.getGfsyeList();
ArrayList hlist = aform.getNsrfgrdmList();
ArrayList ilist = aform.getNsrfgrmcList();
ArrayList jlist = aform.getNsrgrList();
ArrayList klist = aform.getFgrspList();
ArrayList llist = aform.getTufwxxList();
ArrayList nlist = aform.getOkList();
%>

  <TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=770>
    <TBODY>
  <TR>
  <% if(nlist==null||nlist.size()==0)
  {
  %>
  			<td align=center>
            <IMG alt=退出 height=22 id=tuichu name=tuichu
            onclick="doSubmitForm('Return')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
            src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand" width=79>
			</td>

  <%
  }
 else
  {
  %>
    <TD  align=right width="50%">
        <DIV align=right>
          <IMG name="baocun"
            onClick= "doSubmitForm('Receive');"
            onMouseOver="MM_swapImage('baocun','','<%=static_file%>images/qs_qrslbbc2.jpg',1)"
            onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/qs_qrslbbc1.jpg"
             id="baocun" alt="确认受理并保存" style="cursor:hand">&nbsp;
            </DIV>
			<BR>
	</TD>

<TD align=left>
        <DIV align=left>
          <IMG name="bu"
            onClick= "doSubmitForm('Return');"
            onMouseOver="MM_swapImage('bu','','<%=static_file%>images/tuichu2.jpg',1)"
            onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
            id="bu" alt="退出" style="cursor:hand">
         </DIV>
		 <BR>
	</TD>
  <%
  }
  %>

	</TR>

</TBODY>
</TABLE>


<table>
<%
if(klist!=null&&klist.size()>0)
{

%>
错误:以下申报纳税人应先办理减免税申报审核手续再办理税款缴纳手续,已从导入列表中删除
          <table cellspacing="0" class="table-99">
            <tr>
              <td class="2-td1-left" width="5%">纳税人名称</td>
              <td class="2-td1-left" width="10%">房地产项目名称</td>
              <td class="2-td1-left" width="15%">房地产坐落地址</td>
            </tr>
<logic:iterate id="fgrsp" indexId="index" length="length" name="receivePlslForm" property="fgrspList"
            type="com.creationstar.bjtax.qsgl.model.bo.PlsbErrBo">
            <tr>
              <td class="2-td2-left">
              &nbsp;<bean:write name="fgrsp" property="nsrmc"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="fgrsp" property="fdcxmmc"/>
              </td>
              <td class="2-td2-center"><div align="left">
              &nbsp;<bean:write name="fgrsp" property="fdcxmdz"/></div>
              </td>
            </tr>
      </logic:iterate>
          </table>
<%
}
%>


<%
if(alist!=null&&alist.size()>0)
{

%>
提示:以下申报没有指定审批结果表编号的减免信息
          <table cellspacing="0" class="table-99">
            <tr>
              <td class="2-td1-left" width="5%">纳税人名称</td>
              <td class="2-td1-left" width="10%">房地产项目名称</td>
              <td class="2-td1-left" width="15%">房地产坐落地址</td>
            </tr>
<logic:iterate id="spti" indexId="index" length="length" name="receivePlslForm" property="sptsList"
            type="com.creationstar.bjtax.qsgl.model.bo.PlsbErrBo">
            <tr>
              <td class="2-td2-left">
              &nbsp;<bean:write name="spti" property="nsrmc"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="spti" property="fdcxmmc"/>
              </td>
              <td class="2-td2-center"><div align="left">
              &nbsp;<bean:write name="spti" property="fdcxmdz"/></div>
              </td>
            </tr>
      </logic:iterate>
          </table>
<%
}
%>

<%
if(jlist!=null&&jlist.size()>0)
{

%>
错误:以下申报个人信息中的纳税人名称和登记中获取的不一致,已从导入列表中删除
          <table cellspacing="0" class="table-99">
            <tr>
              <td class="2-td1-left" width="5%">纳税人名称</td>
              <td class="2-td1-left" width="10%">房地产项目名称</td>
              <td class="2-td1-left" width="15%">房地产坐落地址</td>
            </tr>
<logic:iterate id="nsrgr" indexId="index" length="length" name="receivePlslForm" property="nsrgrList"
            type="com.creationstar.bjtax.qsgl.model.bo.PlsbErrBo">
            <tr>
              <td class="2-td2-left">
              &nbsp;<bean:write name="nsrgr" property="nsrmc"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="nsrgr" property="fdcxmmc"/>
              </td>
              <td class="2-td2-center"><div align="left">
              &nbsp;<bean:write name="nsrgr" property="fdcxmdz"/></div>
              </td>
            </tr>
      </logic:iterate>
          </table>
<%
}
%>


<%
if(hlist!=null&&hlist.size()>0)
{

%>
错误:以下申报非个人信息中的计算机代码错误,已从导入列表中删除
          <table cellspacing="0" class="table-99">
            <tr>
              <td class="2-td1-left" width="5%">纳税人名称</td>
              <td class="2-td1-left" width="10%">房地产项目名称</td>
              <td class="2-td1-left" width="15%">房地产坐落地址</td>
            </tr>
<logic:iterate id="nsrfgrdm" indexId="index" length="length" name="receivePlslForm" property="nsrfgrdmList"
            type="com.creationstar.bjtax.qsgl.model.bo.PlsbErrBo">
            <tr>
              <td class="2-td2-left">
              &nbsp;<bean:write name="nsrfgrdm" property="nsrmc"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="nsrfgrdm" property="fdcxmmc"/>
              </td>
              <td class="2-td2-center"><div align="left">
              &nbsp;<bean:write name="nsrfgrdm" property="fdcxmdz"/></div>
              </td>
            </tr>
      </logic:iterate>
          </table>
<%
}
%>



<%
if(ilist!=null&&ilist.size()>0)
{

%>
错误:以下申报非个人信息中的纳税人名称错误,已从导入列表中删除
          <table cellspacing="0" class="table-99">
            <tr>
              <td class="2-td1-left" width="5%">纳税人名称</td>
              <td class="2-td1-left" width="10%">房地产项目名称</td>
              <td class="2-td1-left" width="15%">房地产坐落地址</td>
            </tr>
<logic:iterate id="nsrfgrmc" indexId="index" length="length" name="receivePlslForm" property="nsrfgrmcList"
            type="com.creationstar.bjtax.qsgl.model.bo.PlsbErrBo">
            <tr>
              <td class="2-td2-left">
              &nbsp;<bean:write name="nsrfgrmc" property="nsrmc"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="nsrfgrmc" property="fdcxmmc"/>
              </td>
              <td class="2-td2-center"><div align="left">
              &nbsp;<bean:write name="nsrfgrmc" property="fdcxmdz"/></div>
              </td>
            </tr>
      </logic:iterate>
          </table>
<%
}
%>






<%

if(blist!=null&&blist.size()>0)
{

%>
错误:以下申报审批结果表的减免信息减免金额和库中不符,已从导入列表中删除
          <table cellspacing="0" class="table-99">
            <tr>
              <td class="2-td1-left" width="5%">纳税人名称</td>
              <td class="2-td1-left" width="10%">房地产项目名称</td>
              <td class="2-td1-left" width="15%">房地产坐落地址</td>
            </tr>
<logic:iterate id="spje" indexId="index" length="length" name="receivePlslForm" property="spjeList"
            type="com.creationstar.bjtax.qsgl.model.bo.PlsbErrBo">
            <tr>
              <td class="2-td2-left">
              &nbsp;<bean:write name="spje" property="nsrmc"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="spje" property="fdcxmmc"/>
              </td>
              <td class="2-td2-center"><div align="left">
              &nbsp;<bean:write name="spje" property="fdcxmdz"/></div>
              </td>
            </tr>
      </logic:iterate>
          </table>

<%
}
%>

<%

if(clist!=null&&clist.size()>0)
{

%>
错误:以下申报审批减免信息已经使用,已从导入列表中删除
          <table cellspacing="0" class="table-99">
            <tr>
              <td class="2-td1-left" width="5%">纳税人名称</td>
              <td class="2-td1-left" width="10%">房地产项目名称</td>
              <td class="2-td1-left" width="15%">房地产坐落地址</td>
            </tr>
<logic:iterate id="spyy" indexId="index" length="length" name="receivePlslForm" property="spyyList"
            type="com.creationstar.bjtax.qsgl.model.bo.PlsbErrBo">
            <tr>
              <td class="2-td2-left">
              &nbsp;<bean:write name="spyy" property="nsrmc"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="spyy" property="fdcxmmc"/>
              </td>
              <td class="2-td2-center"><div align="left">
              &nbsp;<bean:write name="spyy" property="fdcxmdz"/></div>
              </td>
            </tr>
      </logic:iterate>
          </table>

<%
}
%>


<%

if(llist!=null&&llist.size()>0)
{

%>
错误:以下申报中的土地房屋信息中的合同签订时间大于当前时间,已从导入列表中删除
          <table cellspacing="0" class="table-99">
            <tr>
              <td class="2-td1-left" width="5%">纳税人名称</td>
              <td class="2-td1-left" width="10%">房地产项目名称</td>
              <td class="2-td1-left" width="15%">房地产坐落地址</td>
            </tr>
<logic:iterate id="tu" indexId="index" length="length" name="receivePlslForm" property="tufwxxList"
            type="com.creationstar.bjtax.qsgl.model.bo.PlsbErrBo">
            <tr>
              <td class="2-td2-left">
              &nbsp;<bean:write name="tu" property="nsrmc"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="tu" property="fdcxmmc"/>
              </td>
              <td class="2-td2-center"><div align="left">
              &nbsp;<bean:write name="tu" property="fdcxmdz"/></div>
              </td>
            </tr>
      </logic:iterate>
          </table>

<%
}
%>



<%

if(dlist!=null&&dlist.size()>0)
{

%>
错误:以下申报拆迁信息中的拆迁补偿额和库中已有的不相符,已从导入列表中删除
          <table cellspacing="0" class="table-99">
            <tr>
              <td class="2-td1-left" width="5%">纳税人名称</td>
              <td class="2-td1-left" width="10%">房地产项目名称</td>
              <td class="2-td1-left" width="15%">房地产坐落地址</td>
            </tr>
<logic:iterate id="cqbce" indexId="index" length="length" name="receivePlslForm" property="cqbceList"
            type="com.creationstar.bjtax.qsgl.model.bo.PlsbErrBo">
            <tr>
              <td class="2-td2-left">
              &nbsp;<bean:write name="cqbce" property="nsrmc"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="cqbce" property="fdcxmmc"/>
              </td>
              <td class="2-td2-center"><div align="left">
              &nbsp;<bean:write name="cqbce" property="fdcxmdz"/></div>
              </td>
            </tr>
      </logic:iterate>
          </table>
<%
}
%>

<%

if(elist!=null&&elist.size()>0)
{

%>
错误:以下申报拆迁信息中的拆迁使用额大于库中的剩余额,已从导入列表中删除
          <table cellspacing="0" class="table-99">
            <tr>
              <td class="2-td1-left" width="5%">纳税人名称</td>
              <td class="2-td1-left" width="10%">房地产项目名称</td>
              <td class="2-td1-left" width="15%">房地产坐落地址</td>
            </tr>
<logic:iterate id="cqsye" indexId="index" length="length" name="receivePlslForm" property="cqsyeList"
            type="com.creationstar.bjtax.qsgl.model.bo.PlsbErrBo">
            <tr>
              <td class="2-td2-left">
              &nbsp;<bean:write name="cqsye" property="nsrmc"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="cqsye" property="fdcxmmc"/>
              </td>
              <td class="2-td2-center"><div align="left">
              &nbsp;<bean:write name="cqsye" property="fdcxmdz"/></div>
              </td>
            </tr>
      </logic:iterate>
          </table>
<%
}
%>


<%

if(flist!=null&&flist.size()>0)
{

%>
错误:以下申报公有住房信息中的成交价格和库中已有的不相符,已从导入列表中删除
          <table cellspacing="0" class="table-99">
            <tr>
              <td class="2-td1-left" width="5%">纳税人名称</td>
              <td class="2-td1-left" width="10%">房地产项目名称</td>
              <td class="2-td1-left" width="15%">房地产坐落地址</td>
            </tr>
<logic:iterate id="gfcjjg" indexId="index" length="length" name="receivePlslForm" property="gfcjjgList"
            type="com.creationstar.bjtax.qsgl.model.bo.PlsbErrBo">
            <tr>
              <td class="2-td2-left">
              &nbsp;<bean:write name="gfcjjg" property="nsrmc"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="gfcjjg" property="fdcxmmc"/>
              </td>
              <td class="2-td2-center"><div align="left">
              &nbsp;<bean:write name="gfcjjg" property="fdcxmdz"/></div>
              </td>
            </tr>
      </logic:iterate>
          </table>
<%
}
%>



<%

if(glist!=null&&glist.size()>0)
{

%>
错误:以下申报公有住房信息中的本次抵扣额大于库中的剩余额,已从导入列表中删除
          <table cellspacing="0" class="table-99">
            <tr>
              <td class="2-td1-left" width="5%">纳税人名称</td>
              <td class="2-td1-left" width="10%">房地产项目名称</td>
              <td class="2-td1-left" width="15%">房地产坐落地址</td>
            </tr>
<logic:iterate id="gfsye" indexId="index" length="length" name="receivePlslForm" property="gfsyeList"
            type="com.creationstar.bjtax.qsgl.model.bo.PlsbErrBo">
            <tr>
              <td class="2-td2-left">
              &nbsp;<bean:write name="gfsye" property="nsrmc"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="gfsye" property="fdcxmmc"/>
              </td>
              <td class="2-td2-center"><div align="left">
              &nbsp;<bean:write name="gfsye" property="fdcxmdz"/></div>
              </td>
            </tr>
      </logic:iterate>
          </table>
<%
}
%>

</table>

</html:form>
  <%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>


