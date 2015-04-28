<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.SbState"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.ActionUtil"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<HTML><HEAD><TITLE>北京市地方税务局契税申报显示表</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType){
  document.all.operationType.value=operationType;

   document.forms[0].submit();
}

function doRedirect(subaction,index,type){
  document.forms[0].fwindex.value = index;
  document.forms[0].fwtype.value = type;
  document.all.operationType.value="Redirect";
  document.forms[0].subaction.value=subaction;

  document.forms[0].submit();
}

function doPrintSbb()
{
    window.open("/qsgl/qssb/printSbxx.do?sbbh="+document.forms[0].sbbh.value,"print","toolbar=no,location=yes,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=640,height=480");
}
function doPrintHdtzs()
{
    window.open("/qsgl/qssb/printHdtzs.do?sbbh="+document.forms[0].sbbh.value,"print","toolbar=no,location=yes,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=640,height=480");
}
function doUpdateHdtzsfwhm()
{
    window.open("/qsgl/qssb/modHdtzsfwhm.do?sbbh="+document.forms[0].sbbh.value);
    document.getElementById("Printhdtzs").style.display="block";
}

</SCRIPT>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="审核>北京市地方税务局契税申报显示表 "/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<br>
 <TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=98%>
  <TBODY>
  <TR>
    <TD vAlign=top>

         <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>北京市地方税务局契税申报表</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
            <html:form action="/sh/jsbl.do">
              <html:hidden property="operationType" value=""/>
              <html:hidden property="subaction" value=""/>
              <html:hidden property="fwindex"/>
              <html:hidden property="fwtype"/>
            <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>
			  <BR>
			  <TR>

			  	<TD class=2-td2-t-left width="15%">批次号</TD>
			  	<html:hidden name="sbxxForm" property="pch"/>
			  	<TD class=2-td2-t-left width="35%"><DIV align=left>&nbsp<bean:write name="sbxxForm" property="voSbzb.drpch" /> </DIV></TD>

			  	<TD class=2-td2-t-left width="15%">纳税申报表号</TD>
			  	<html:hidden name="sbxxForm" property="sbbh"/>
			  	<TD class=2-td2-t-center width="35%"><DIV align=left>&nbsp;<bean:write name="sbxxForm" property="voSbzb.sbbh" /> </DIV></TD>

			  </TR>
			  <TR>
			  	<TD class=2-td2-left width="15%">缴款方式</TD>
			  	<TD class=2-td2-left width="35%"> <DIV align=left>
                                  &nbsp;<bean:write name="sbxxForm" property="voSbzb.jsfsmc"/>
				<TD class=2-td2-left width="15%">房屋土地管理部门受理号</TD>
				<TD class=2-td2-center width="35%"><DIV align=left>&nbsp;<bean:write name="sbxxForm" property="voSbzb.fwtdbmdm" />  </DIV></TD>
			  </TR>
			  </TBODY>
			  </TABLE>

			<TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>
              <bean:define id="sbxxBo" name="sbxxForm" property="data" type="com.creationstar.bjtax.qsgl.model.bo.SbxxBo"/>
              <%if (sbxxBo.isPerson())
              {
              %>

    <TR>
    <TD class=2-td2-left width="70%"  colspan="5"><DIV align=left>个人填写部分</DIV></TD>
    <TD class=2-td2-center width="30%" colspan="3" >  &nbsp; </TD>

</TR>

        <TR>
    <TD  colspan="8" valign="top" width="100%">

<table border="0" cellpadding="0" cellspacing="0" class="table-99" width="100%">
  <tr>
	<td width="20%" class="2-td2-left">
	纳税人名称
	</td>
    <td width="20%" class="2-td2-left">联系电话</td>
    <td width="20%" class="2-td2-left">身份证件类型</td>
    <td width="20%" class="2-td2-left">身份证件号码</td>
    <td width="10%" class="2-td2-left">国籍</td>
    <td width="10%" class="2-td2-center">产权人类型</td>
  </tr>

 <logic:iterate id="nsrdata" indexId="index" length="length" name="sbxxForm" property="nsrList">
  <tr>
	<td width="20%" class="2-td2-left"><bean:write name="nsrdata" property="nsrmc" /></td>
    <td width="20%" class="2-td2-left"><bean:write name="nsrdata" property="lxdh" />&nbsp;</td>
    <td width="20%" class="2-td2-left"><bean:write name="nsrdata" property="sfzjlxmc" /></td>
    <td width="20%" class="2-td2-left"><bean:write name="nsrdata" property="sfzjhm" /></td>
    <td width="10%" class="2-td2-left"><bean:write name="nsrdata" property="gjmc" /></td>
    <td width="10%" class="2-td2-center">
<logic:equal name="nsrdata" property="cqrlx" value="1">
    共有产权人
</logic:equal>
<logic:equal name="nsrdata" property="cqrlx" value="0">
    主产权人
</logic:equal>
    </td>
  </tr>
  </logic:iterate>

</table>
</TD>
</TR>


				<%
				}
				%>

              <%if (!sbxxBo.isPerson())
              {
              %>
                <TR>
                <TD class=2-td2-t-left width="15%" rowspan = "4">
                  <DIV align=right>非个人填</DIV>
                   <DIV align=right>写部分</DIV>
                  <TD class=2-td2-t-left width="18%">
                   <DIV align=right>纳税计算机代码&nbsp; </DIV>
                </TD>
                <DIV align=right>&nbsp; </DIV>
                  <TD class=2-td2-t-left width="25%">
                    <DIV align=left>
                        &nbsp;<bean:write name = "sbxxForm" property = "voFgrxx.jsjdm" />
                    </DIV>
                  </TD>
                <TD class=2-td2-t-left width="19%">
                  <DIV align=right>税务人类型&nbsp; </DIV></TD>
                <TD colspan="4"  class=2-td2-t-center width="33%">
                   <DIV align=left>
                       &nbsp;<bean:write name = "sbxxForm" property = "voFgrxx.nsrlxmc" />
                   </DIV>
                </TD>
             </TR>
             <TR>
                <TD  class=2-td2-left >
                  <DIV align=right>纳税人名称&nbsp;</DIV></TD>
                <TD colspan="5"  class=2-td2-center  style="word-break:break-all">
                   <DIV align=left>
                       &nbsp;<bean:write name = "sbxxForm" property = "voFgrxx.nsrmc" />
                   </DIV>
                 </TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>开户银行&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=left>
                      &nbsp;<bean:write name = "sbxxForm" property = "voFgrxx.khyhmc" />
                  </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=right>银行账号&nbsp;</DIV></TD>
                <TD colspan="3" class=2-td2-center >
                   <DIV align=left>
                       &nbsp;<bean:write name = "sbxxForm" property = "voFgrxx.yhzh" />
                   </DIV>
                 </TD>
                </TR>
                <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>联系人姓名&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                   <DIV align=left>
                       &nbsp;<bean:write name = "sbxxForm" property = "voFgrxx.lxrxm" />
                   </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=right>联系电话&nbsp;</DIV></TD>
                <TD colspan="4"  class=2-td2-center >
                   <DIV align=left>
                       &nbsp;<bean:write name = "sbxxForm" property = "voFgrxx.lxdh" />
                   </DIV>
                </TD>
                </TR>
          <%
          }
          %>

              <%if (!sbxxBo.isBZ())
              {
              %>
               <TR>
                <TD class=2-td2-left  rowspan = "2">
                  <DIV align=right>税务机关</DIV>
                  <DIV align=right>审核减免税</DIV></TD>
                 <TD class=2-td2-left >
                   <DIV align=right>减免税文书字号&nbsp; </DIV>
                </TD>
                <TD colspan="6" class=2-td2-center >
                 <DIV align=left>&nbsp;<bean:write name="sbxxForm" property="voSpjgxx.hdtzszh"/> </DIV></TD>
              </TR>
               <TR>
                  <TD class=2-td2-left >
                   <DIV align=right>减免税金额&nbsp; </DIV>
                </TD>
                  <TD colspan="6" class=2-td2-center >
                   <DIV align=left>&nbsp;<%=DataConvert.BigDecimal2String(sbxxBo.getVoSpjgxx().getJmsje())%></DIV>
                </TD>
                  </TR>

	          <%
	          }
	          %>
                 <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>备注&nbsp; </DIV>
                </TD>
                <TD colspan="7" class=2-td2-center >
                  <DIV align=left>&nbsp;<bean:write name="sbxxForm" property="voSbzb.bz" /></DIV></TD>
                </TR>

	<!-- 房屋基本信息-->
    		    <bean:define id="fwtdVo" name="sbxxForm" property="voTufwxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"/>
				  <tr>


				    <%if (!sbxxBo.isBZ())
			          {
			          %>
			          <TD class=2-td2-left rowspan = "8">
			          <%
			          }
			          else
			          {
			          %>
			          <TD class=2-td2-left rowspan = "6">
			          <%
			          }
			          %>

				      <DIV align=right>	土地房屋</DIV>
				      <DIV align=right>权属转移</DIV>
				      <div align=right>
                      </TD>
				      <TD class=2-td2-left >
				        <DIV align=right>房地产项目名称&nbsp;</DIV></TD>
				        <TD colspan="6" class=2-td2-center style="word-break:break-all">
				          <DIV align=left><bean:write name="fwtdVo" property="fdcxmmc" /> </DIV></TD>
				  </TR>
				  <TR>
			          <TD class=2-td2-left >
			            <DIV align=right>合约签订时间&nbsp; </DIV>
			          </TD>
			          <TD class=2-td2-left >
			            <DIV align=left>
                                    <%=DataConvert.TimeStamp2String(fwtdVo.getHtqdsj())%>
                                    </DIV></TD>
               <%if (sbxxBo.isBZ())
              {
              %>
			            <TD class=2-td2-left >
			              <DIV align=right>分类&nbsp;</DIV></TD>

	            <%
	            }
	            %>
               <%if (!sbxxBo.isBZ())
              {
              %>
			            <TD class=2-td2-left >
			              <DIV align=right>购房原因&nbsp;</DIV></TD>

	            <%
	            }
	            %>
			              <TD colspan="4"  class=2-td2-center >
			                <DIV align=left>
			                  <bean:write  name="fwtdVo" property="flmc"/>&nbsp
			                </DIV></TD>
		          </TR>
		          <TR>
		            <TD class=2-td2-left >
		              <DIV align=right>土地、房屋权属转移类型&nbsp; </DIV> </TD>
		              <TD class=2-td2-left >
		                <DIV align=left>
		                  <bean:write  name="fwtdVo" property="tdfwqszymc"/></DIV></TD>
		                  <TD class=2-td2-left >
		                    <DIV align=right>房屋类别&nbsp;</DIV></TD>
		                    <TD colspan="4"  class=2-td2-center >
		                      <DIV align=left>
		                        <bean:write  name="fwtdVo" property="fwlxmc"/>&nbsp;</DIV></TD>
		          </TR>
		          <TR>
		            <TD  class=2-td2-left >
		              <DIV align=right>土地、房屋座落地址&nbsp; </DIV>
		            </TD>
		            <TD colspan="6"  class=2-td2-center style="word-break:break-all">
		              <DIV align=left><bean:write name="fwtdVo" property="tdfwzldz" /></DIV></TD>
		            </TR>



                    <TR>
                      <TD class=2-td2-left >
                        <DIV align=right>土地、房屋权属转移面积&nbsp; </DIV>
                      </TD>
                      <TD  class=2-td2-left >
                        <DIV align=left>
                          <%=DataConvert.BigDecimal2String(fwtdVo.getTdfwqszymj())%>
                          m2 </DIV></TD>
                        <TD class=2-td2-left >
                          <DIV align=right>房屋建筑面积&nbsp;</DIV></TD>
                          <TD colspan="4"  class=2-td2-center >
                            <DIV align=left>
                              <%=DataConvert.BigDecimal2String(fwtdVo.getFwjzmj())%>
                              m2</DIV></TD>
                    </TR>

                    <TR>
                      <TD class=2-td2-left >
                        <DIV align=right>容积率&nbsp; </DIV>
                      </TD>
                      <TD  class=2-td2-left >
                        <DIV align=left>
	<%
	if(fwtdVo.getRjl()==null||fwtdVo.getRjl().equals(""))
	{
	%>
	<FONT color=red>请选择容积率</FONT>
	<%
	}
    else
	{
		if(fwtdVo.getRjl().equals("00"))
		{
		%>
		1.0以下
		<%
		}
		if(fwtdVo.getRjl().equals("01"))
		{
		%>
		1.0以上含1.0
		<%
		}
	}
	%>&nbsp; </DIV></TD>
                        <TD class=2-td2-left >
                            <!--修改土地级次为所属区域-->
                          <DIV align=right>所属区域&nbsp;</DIV></TD>
                          <TD colspan="4"  class=2-td2-center >
                            <DIV align=left>
    	<%if(fwtdVo.getTdjc()==null||fwtdVo.getTdjc().equals(""))
	{
	%>
	<FONT color=red>请选择所属区域</FONT>&nbsp;
	<%
	}
	else
	{
        if(fwtdVo.getTdjc().equals("00"))
		{
		%>
		&nbsp;
		<%
		}
		if(fwtdVo.getTdjc().equals("01"))
		{
		%>
		三环以内&nbsp;
		<%
		}
		if(fwtdVo.getTdjc().equals("02"))
		{
		%>
		三环至四环之间&nbsp;
		<%
		}
		%>
		<%if(fwtdVo.getTdjc().equals("03"))
		{
		%>
		四环至五环之间&nbsp;
		<%
		}
		if(fwtdVo.getTdjc().equals("04"))
		{
		%>
		五环以外&nbsp;
		<%
		}
	     if(fwtdVo.getTdjc().equals("11"))
	 	{	
	     %>
	    	四环内北部地区&nbsp;
	     <% 
	 	}
	     if(fwtdVo.getTdjc().equals("12"))
	 	{	
	 	%>
	    	四环内南部地区&nbsp;
	     <% 
	 	}
	     if(fwtdVo.getTdjc().equals("13"))
	 	{	
	 	%>
	    	四环至五环北部地区&nbsp;
	     <% 
	 	}
	      if(fwtdVo.getTdjc().equals("14"))
	 	{	
	 	%>
	    	四环至五环南部地区&nbsp;
	     <% 
	 	}
	     if(fwtdVo.getTdjc().equals("15"))
	 	{	
	 	%>
	    	五环至六环北部地区&nbsp;
	     <% 
	 	}
	     if(fwtdVo.getTdjc().equals("16"))
	 	{	
	 	%>
	    	五环至六环南部地区&nbsp;
	     <% 
	 	}
	     if(fwtdVo.getTdjc().equals("17"))
	 	{	
	 	%>
	    	六环外地区&nbsp;
	     <% 
	 	}
	}
	%>

		&nbsp;</DIV></TD>
                    </TR>



			<%if (!sbxxBo.isBZ())
		          {
		          %>
                    <TR>
	                    <TD class=2-td2-left rowspan = "2">
	                      <DIV align=right>成交价格（评估价格）</DIV>
	                      <TD  class=2-td2-left >
	                        <DIV align=left>
                                 <%=DataConvert.BigDecimal2String(fwtdVo.getCjjgrmb())%>
                                  元(人民币) </DIV></TD>
	                        <TD class=2-td2-left >
	                          <DIV align=right>税务机关核定价格&nbsp; </DIV></TD>
	                          <TD  colspan="4"  class=2-td2-center >
	                            <DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getPgjgrmb())%>&nbsp; 元(人民币) </DIV></TD>
                      </TR>
                      <TR>
                        <TD class=2-td2-left >
                          <DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getCjjgwb())%> 元(外币) </DIV></TD>
                          <TD class=2-td2-left >
                            <DIV align=left>
                              币种：<bean:write name="fwtdVo" property="bzmc" />
                            </DIV>
                            <DIV align=left>汇率：<%=DataConvert.BigDecimal2String(fwtdVo.getHldm(),5)%></DIV></TD>
                            <TD class=2-td2-center colspan=4 >
                              <DIV align=left>
                                <%=DataConvert.BigDecimal2String(fwtdVo.getZhjgrmb())%>
                                折合元(人民币) </DIV></TD>

                       </tr>
			<%
			}
			%>

      <logic:iterate id="cqdata" indexId="cqindex" length="length" name="sbxxForm" property="cqList"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq">

                <TR>
                  <TD class=2-td2-left rowspan = "4">
                  <DIV align=right>	拆迁</DIV>
                   <DIV align=right>情况</DIV>
                  </TD>
                  <TD class=2-td2-center >
                   <DIV align=right>被拆迁房屋坐落地址&nbsp; </DIV>
                </TD>
                <TD colspan="6" class=2-td2-center style="word-break:break-all">
                  <DIV align=left>
                      &nbsp;<bean:write name = "cqdata" property = "zldz"/>
                  </DIV>
                </TD>
                </TR>
                <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>拆迁协议号码&nbsp; </DIV>
                </TD>
                <TD colspan="6" class=2-td2-center >
                <DIV align=left>
                    &nbsp;<bean:write name = "cqdata" property = "cqxyh"/>
                </DIV>
                </TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left >
                   <DIV align=right> 拆迁补偿额&nbsp; </DIV>
                </TD>
                <TD  class=2-td2-left >
                  <DIV align=left>
                     &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getCqbce())%> 元(人民币)
                  </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=right>本次可使用补偿额&nbsp;</DIV></TD>
               <TD colspan="4"  class=2-td2-center >
                <DIV align=left>
                   &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getBcksybce())%> 元(人民币)
                </DIV>
               </TD>
               </TR>
               <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>本次使用补偿额&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                   <DIV align=left>
                      &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getBcsybce())%> 元(人民币)
                   </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=right>拆迁补偿剩余额&nbsp;</DIV></TD>
                <TD colspan="4"  class=2-td2-center >
                  <DIV align=left>
                      &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getCqbcsye())%> 元(人民币)
                  </DIV>
                  </TD>
                  </TR>
       </logic:iterate>

      <logic:iterate id="gydata" indexId="gfindex" length="length" name="sbxxForm" property="gyzfList"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf">

               <TR>
                   <TD class=2-td2-left rowspan = "6">
                  <DIV align=right>	已购公有住房</DIV>
                  <DIV align=right>	/经济适用住房</DIV>
                   <DIV align=right>上市出售情况</DIV>
				   <br>
				   </TD>
                  <TD   class=2-td2-left >
                   <DIV  align=right>出售合同号码&nbsp;</DIV>
                </TD>
                <TD colspan="6" class=2-td2-center >
                  <DIV align=left><bean:write name="gydata" property="yggyzfqszsh" /></DIV></TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>房屋权属证书号&nbsp; </DIV>
                </TD>
                 <TD colspan="6" class=2-td2-center style="word-break:break-all">
                  <DIV align=left><bean:write name="gydata" property="fwqszsh" />&nbsp;</DIV></TD>
                  </TR>
                <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>座落地址&nbsp; </DIV>
                </TD>
                 <TD colspan="6" class=2-td2-center style="word-break:break-all">
                  <DIV align=left><bean:write name="gydata" property="zldz" />&nbsp;</DIV></TD>
                  </TR>
                  <TR>
                 <TD  class=2-td2-left >
                   <DIV align=right>出售合同（契约）签订时间&nbsp;</DIV>
                </TD>
                <TD colspan="6" class=2-td2-center >
                  <DIV align=left><%=DataConvert.TimeStamp2String(gydata.getQdsj())%></DIV></TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left >
                   <DIV align=right> 建筑面积&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                   <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getJzmj())%>m2 </DIV></TD>
                <TD class=2-td2-left >
                  <DIV align=right>成交价格&nbsp;</DIV></TD>
                <TD colspan="4"  class=2-td2-center >
                  <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getCjjg())%> 元(人民币)</DIV></TD>
                  </TR>
                 <TR>
                <TD class=2-td2-left >
                  <DIV align=right>本次抵扣额&nbsp;</DIV></TD>
                <TD colspan="1"  class=2-td2-left >
                  <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getBcdke())%> 元(人民币)</DIV></TD>
                   <TD class=2-td2-left >
                  <DIV align=right>剩余额&nbsp;</DIV></TD>
                <TD colspan="4"  class=2-td2-center >
                  <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getSye())%> 元(人民币)</DIV></TD>
                  </TR>

       </logic:iterate>
                  </TBODY></TABLE><BR>

            <DIV align=center>

	  	  <% if ((sbxxBo.getState().equals(Constants.ZB_ZTBS_JS_TY)))
	  	     {
	  	  %>

	  	  <img alt=防伪号码   id=Fwhm name=Fwhm
		onclick="doUpdateHdtzsfwhm()" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('Fwhm','','<%=static_file%>images/b-fwhm2.jpg',1)"
		src="<%=static_file%>images/b-fwhm1.jpg" style="CURSOR: hand">

	  	  			            		<img alt=打印核定通知书 id=Printhdtzs name=Printhdtzs
		onclick="doPrintHdtzs()" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('Printhdtzs','','<%=static_file%>images/q_dyhdtzs2.jpg',1)"
		src="<%=static_file%>images/q_dyhdtzs1.jpg" style="CURSOR: hand;display:none">

	  	  <%
	  	     }
	  	     else
	  	     {
	  	  %>
	  	  			            		<img alt=审核同意   id=tongyi name=tongyi
		onclick="doSubmitForm('Confirm')" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('tongyi','','<%=static_file%>images/q_shty2.jpg',1)"
		src="<%=static_file%>images/q_shty1.jpg" style="CURSOR: hand">
          &nbsp;&nbsp;&nbsp;&nbsp;
	  	  			            		<img alt=审核不同意   id=butongyi name=butongyi
		onclick="doSubmitForm('Reject')" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('butongyi','','<%=static_file%>images/q_shbty2.jpg',1)"
		src="<%=static_file%>images/q_shbty1.jpg" style="CURSOR: hand">

          &nbsp;&nbsp;&nbsp;&nbsp;
         <%
             }
         %>

            <IMG alt=退出 height=22 id=tuichu name=tuichu
            onclick="doSubmitForm('Return')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
            src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand" width=79>
            </DIV><BR>

</html:form>
<script language="javascript" type='text/JavaScript'>

</script>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
