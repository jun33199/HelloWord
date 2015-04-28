<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<HTML><HEAD><TITLE>数据迁移</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/judge.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/calculate.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType)
{
  document.all.operationType.value=operationType;
  if(operationType=="Save" )
  {
    if(document.forms[0].xxzl.value=="" || document.forms[0].qxdm.value==""
       || document.forms[0].blbs.value=="" || document.forms[0].ztbs.value==""
       || document.forms[0].jsfsdm.value=="" )
    {
        alert("请输入条件！");
     	document.forms[0].qxdm.focus();
    	return false;
    }
  }else  if(operationType=="Update" )
  {
    if(document.forms[0].xxzl.value=="" || document.forms[0].qxdm.value==""
       || document.forms[0].jsfsdm.value=="" )
    {
        alert("请输入条件！");
     	document.forms[0].qxdm.focus();
    	return false;
    }
  }else  if(operationType=="Qyhz" )
  {
    if(document.forms[0].qxdm.value=="" )
    {
        alert("请输入条件！");
     	document.forms[0].qxdm.focus();
    	return false;
    }
  }
  /*else  if(operationType=="Return" )
  {
  document.forms[0].submit();
  return true;
  }*/
  //alert("operationType=="+operationType);
  return true;
}
</SCRIPT>



<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="契税申报&gt;数据迁移"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

				</td>
              </tr>
               </TBODY>
     </TABLE>
     
<TABLE align=center border=0 cellPadding=0 cellSpacing=0  width=100%>
  <TBODY>
  <TR>
    <TD vAlign=top width=100%>
      <TABLE align=center cellSpacing=0 class=table-99 width=100%>
        <TBODY>
        <TR>
          <TD class=1-td1 width=100%>数据迁移</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top width=100%>
            <html:form action="/sjqy/sjqy.do">
            <input type = "hidden" name = "operationType" value = "" >
             <TABLE border="0" cellSpacing=0 class=table-89 width=100%>
              <TBODY>
<tr>
                <td class="2-td2-t-left">信息种类</td>
                <td class="2-td2-t-left" >
                    <div align=left>
		  			 <html:select property="xxzl" >
                        <html:option value="0">个人</html:option>
                        <html:option value="1">非个人</html:option>
                        <html:option value="2">不征</html:option>
                       </html:select>
					</div>
                </td>
                <td class="2-td2-t-left">补录表识</td>
                <td class="2-td2-t-left" >
                    <div align=left>
		  			 <html:select property="blbs" >
                        <html:option value="2">小程序导入</html:option>
                        <html:option value="1">补录</html:option>
                        <html:option value="0">非补录</html:option>
                       </html:select>
					</div>
                </td>
                <td class="2-td2-t-left">最大处理记录数</td>
                <td class="2-td2-t-center">
                    <div align=left>
		  			 <html:text property="cc" maxlength="6" tabindex="0"/>
					</div>
                </td>

</tr>
               <tr>
                <td class="2-td2-left">区县</td>
                <td class="2-td2-left">
					<div align=left>
		  			     <html:select property="qxdm" >
                        <html:option value="01">东城</html:option>
                        <html:option value="05">朝阳</html:option>
                        <html:option value="11">昌平</html:option>
                        <html:option value="12">通州</html:option>                        
                       </html:select>
					</div>
				</td>
                <td class="2-td2-left">缴税方式</td>
                <td class="2-td2-left">
                    <div align=left>
                                          <bean:define id="data1" name="sjqyForm" property="jsfsList"  />
                                          <html:select property="jsfsdm" >
                                            <html:options collection="data1" labelProperty="jsfsmc" property="jsfsdm" />
                                          </html:select>
                    
					</div>
                </td>
				<td class="2-td2-left">状态标识</td>
                <td class="2-td2-center">
					<div align=left>
		  			     <html:select property="ztbs" >
                        <html:option value="1">打印申报表</html:option>
                        <html:option value="4">打印核定通知书</html:option>
                        <html:option value="5">即时办理审核同意</html:option>
                        <html:option value="7">已复核</html:option>
                        <html:option value="99">已入库</html:option>
                       </html:select>
					</div>
				</td>
              </tr>
     </TABLE><BR>

      <DIV align=center>
        
        <input type="submit" name="Submit1" value="开始迁移" onclick="doSubmitForm('Save');" >
        
          &nbsp;&nbsp;
        <input type="submit" name="Submit2" value="生成完税证和专用缴款书" onclick="doSubmitForm('Update');" >
          &nbsp;&nbsp;

        <!--input type="submit" name="Submit3" value="迁移汇总生成缴款书" onclick="doSubmitForm('Qyhz');" -->
          &nbsp;&nbsp;
        <input type="submit" name="SubmitTc" value="退  出" onclick="doSubmitForm('Return');" >
          &nbsp;&nbsp;
      </DIV><BR>
 
 	<table cellspacing="0" class="table-99">
 	  <tr>
		<td><hr class="hr1"></td>
		<td width="140" class="b-black10">
			<div align="center">迁移错误结果信息</div></td>
		<td><hr class="hr1"></td>
             </tr>
          </table>
            <logic:notEqual name="sjqyForm" property="qybs" value="0">

      <TABLE border="0" cellSpacing=0 class=table-99>
      <TBODY>
       <TR>       	
            <logic:notEqual name="sjqyForm" property="qybs" value="3">
       	<TD class="2-td1-left" width = "5%" >申报表号</TD>
        <TD class="2-td1-left" width = "10%" >号码（证件号码/计算机代码）</TD>
        
        <logic:equal name="sjqyForm" property="qybs" value="1">
        <TD class="2-td1-left" width = "10%" >纳税人名称</TD>
        <TD class="2-td1-left" width = "10%" >类型（证件类型/纳税人类型）</TD>
        <TD class="2-td1-left" width = "20%" >土地、房屋座落地址</TD>
        </logic:equal>
        
        <logic:equal name="sjqyForm" property="qybs" value="2">
        <TD class="2-td1-left" width = "5%" >机关代码</TD>
        <TD class="2-td1-left" width = "5%" >完税证号</TD>
        <TD class="2-td1-left" width = "5%" >实缴税额</TD>
        </logic:equal>

        <TD class="2-td1-left" width = "%5" >受理人</TD>
          </logic:notEqual>
          
        <logic:equal name="sjqyForm" property="qybs" value="3">
        <TD class="2-td1-left" width = "10%" >缴款款书号</TD>
        <TD class="2-td1-left" width = "5%" >机关代码</TD>
        <TD class="2-td1-left" width = "10%" >计算机代码</TD>
        <TD class="2-td1-left" width = "20%" >课税数量</TD>
        <TD class="2-td1-left" width = "20%" >实缴税额</TD>
        </logic:equal>
        
        <TD class="2-td1-left" width = "%5" >错误代码</TD>
        <TD class="2-td1-center" width = "15%" >错误信息</TD>
      </TR>
      <logic:iterate id="data" indexId="index" length="length" name="sjqyForm" property="ret"
            type="com.creationstar.bjtax.qsgl.model.bo.SjqyErrBo">
            <tr>
            <logic:notEqual name="sjqyForm" property="qybs" value="3">

              <td class="2-td2-left">                  
                  <bean:write name="data" property="sbbh"/>
              </td>
              <td class="2-td2-left">
                  <bean:write name="data" property="sfzjhm"/></a>
              </td>
              
        <logic:equal name="sjqyForm" property="qybs" value="1">
              <td class="2-td2-left" style="word-break:break-all">
                  <bean:write name="data" property="nsrmc"/></a>
              </td>
              <td class="2-td2-left">
                  <bean:write name="data" property="sfzjlx"/></a>
              </td>
              <td class="2-td2-left" style="word-break:break-all">
                  <bean:write name="data" property="fdcxmdz"/>
              </td>
        </logic:equal>
              
        <logic:equal name="sjqyForm" property="qybs" value="2">
        <TD class="2-td2-left" ><bean:write name="data" property="swjgdm"/></TD>
        <TD class="2-td2-left" ><bean:write name="data" property="wszh"/></TD>
        <TD class="2-td2-left" ><bean:write name="data" property="ynse"/></TD>
        </logic:equal>

              <td class="2-td2-left">
                  <bean:write name="data" property="slr"/>
              </td>
          </logic:notEqual>

        <logic:equal name="sjqyForm" property="qybs" value="3">
        <TD class="2-td2-left"  ><bean:write name="data" property="wszh"/></TD>
         <TD class="2-td2-left" ><bean:write name="data" property="swjgdm"/></TD>
        <TD class="2-td2-left"  ><bean:write name="data" property="sfzjhm"/></TD>
        <TD class="2-td2-left"  ><bean:write name="data" property="sbbh"/></TD>
        <TD class="2-td2-left"  ><bean:write name="data" property="ynse"/></TD>
        </logic:equal>
              
             <td class="2-td2-left">
                  <bean:write name="data" property="errcode"/>
              </td>
              <td class="2-td2-center">
                  <bean:write name="data" property="errmsg"/>
              </td>
            </tr>
      </logic:iterate>
          </table>
          
          </logic:notEqual>
          
          <br>
 
 
      
</html:form>

  <%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>

