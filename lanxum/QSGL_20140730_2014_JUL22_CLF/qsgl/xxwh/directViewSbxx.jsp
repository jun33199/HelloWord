<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/control.tld" prefix="control"%>

<%@page import="com.ttsoft.common.util.SessionKey"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.SbState"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf"%>



<HTML><HEAD><TITLE>北京市地方税务局契税申报显示表</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(){
 if(confirm("您确认退出吗？")){
   window.close();
 }
}
</SCRIPT>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="契税申报>北京市地方税务局契税申报显示表"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>
<SCRIPT language=javascript>
</SCRIPT>
<br>
 <html:form action="/xxwh/directViewSbxx.do">
 <bean:define id="sbxxBo" name="sbxxForm" property="data" type="com.creationstar.bjtax.qsgl.model.bo.SbxxBo"/>
 <bean:define id="sbzb" name="sbxxForm" property="voSbzb" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb" />
 <TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>

         <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <%if (!sbxxBo.isBZ())
          {
          %>
          <TD class=1-td1>北京市地方税务局契税申报表</TD></TR>
          <%
          }
          else
          {
          %>
          <TD class=1-td1>北京市地方税务局不征契税采集表</TD></TR>
          <%
          }
          %>
        <TR>
          <TD class=1-td2 vAlign=top>
              <html:hidden property="operationType" value=""/>
              <html:hidden property="subaction" value=""/>
              <html:hidden property="fwindex"/>
              <html:hidden property="fwtype"/>
            <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>
			  <BR>
			  <TR>
          <%if (!sbxxBo.isBZ())
          {
          %>
			  	<TD class=2-td2-t-left width="12%">纳税申报表号</TD>
          <%
          }
          else
          {
          %>
			  	<TD class=2-td2-t-left width="12%">不征契税信息采集表号</TD>
          <%
          }
          %>
			  	<html:hidden name="sbxxForm" property="sbbh"/>
			  	<TD class=2-td2-t-left width="43%" colspan=3><DIV align=left>&nbsp<bean:write name="sbxxForm" property="voSbzb.sbbh" /> </DIV></TD>

			  </TR>
			  <TR>
			  	<TD class=2-td2-left width="12%">缴款方式</TD>
			  	<TD class=2-td2-left width="43%"> <DIV align=left>
                                  &nbsp;<bean:write name="sbxxForm" property="voSbzb.jsfsmc"/>
				<TD class=2-td2-left width="22%">房屋土地管理部门受理号</TD>
				<TD class=2-td2-center width="23%"><DIV align=left>&nbsp;<bean:write name="sbxxForm" property="voSbzb.fwtdbmdm" />  </DIV></TD>
			  </TR>
			  </TBODY>
			  </TABLE>

			<TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>
    		  	<input type="hidden" name="bzqs" value="<%=sbxxBo.isBZ()%>">
              <%if (sbxxBo.isPerson())
              {
              %>
               <html:hidden property="person" value="true"/>

              <TR>
                <TD class=2-td2-t-left width="10%" rowspan = "3">
                  <DIV align=right>个人填</DIV>
                   <DIV align=right>写部分</DIV></TD>

          <%if (sbxxBo.isBZ())
          {
          %>
                  <TD class=2-td2-t-left width="20%">
                   <DIV align=right>申请人名称&nbsp; </DIV>
                </TD>

          <%
          }
            else
          {
          %>
                  <TD class=2-td2-t-left width="20%">
                   <DIV align=right>纳税人名称&nbsp; </DIV>
                </TD>

          <%
          }
          %>
                <DIV align=right>&nbsp; </DIV>
                  <TD class=2-td2-t-left width="20%">
                    <DIV align=left><bean:write name="sbxxForm" property="voGrxx.nsrmc" /> </DIV></TD>
                <TD class=2-td2-t-left width="25%">
                  <DIV align=right>联系电话&nbsp; </DIV></TD>
                <TD colspan="2"  class=2-td2-t-center width="25%">
                   <DIV align=left><bean:write name="sbxxForm" property="voGrxx.lxdh" />&nbsp; </DIV></TD>
                  </TR>
               <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>身份证件类型&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=left>
                    <bean:write name="sbxxForm" property="voGrxx.sfzjlxmc"/>
                    </DIV></TD>
                <TD   class=2-td2-left >
                  <DIV align=right>身份证件号码&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center>
                   <DIV align=left><bean:write name="sbxxForm" property="voGrxx.sfzjhm"/> </DIV></TD>
                  </TR>
               <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>国籍&nbsp; </DIV>
                </TD>
                <TD class=2-td2-center colspan="4">
                  <DIV align=left>
                    <bean:write name="sbxxForm" property="voGrxx.gjmc"/>&nbsp;
                    </DIV></TD>
                  </TR>
				<%
				}
				%>

              <%if (!sbxxBo.isPerson())
              {
              %>
               <html:hidden property="person" value="false"/>
                <TR>
                <TD class=2-td2-t-left width="15%" rowspan = "4">
                  <DIV align=right>非个人填</DIV>
                   <DIV align=right>写部分</DIV>
                  <TD class=2-td2-t-left width="20%">
                   <DIV align=right>纳税计算机代码&nbsp; </DIV>
                </TD>
                <DIV align=right>&nbsp; </DIV>
                  <TD class=2-td2-t-left width="20%">
                    <DIV align=left>
                        &nbsp;<bean:write name = "sbxxForm" property = "voFgrxx.jsjdm" />
                    </DIV>
                  </TD>
           <%if (sbxxBo.isBZ())
          {
          %>
                <TD class=2-td2-t-left width="20%">
                  <DIV align=right>申请人类型&nbsp; </DIV></TD>

          <%
          }
           else
          {
          %>
                <TD class=2-td2-t-left width="20%">
                  <DIV align=right>纳税人类型&nbsp; </DIV></TD>

          <%
          }
          %>



                <TD colspan="2"  class=2-td2-t-center width="25%">
                   <DIV align=left>
                       &nbsp;<bean:write name = "sbxxForm" property = "voFgrxx.nsrlxmc" />
                   </DIV>
                </TD>
             </TR>
             <TR>

           <%if (sbxxBo.isBZ())
          {
          %>
                <TD  class=2-td2-left >
                  <DIV align=right>申请人名称&nbsp;</DIV></TD>
          <%
          }
          else
          {
          %>
                <TD  class=2-td2-left >
                  <DIV align=right>纳税人名称&nbsp;</DIV></TD>
          <%
          }
          %>


                <TD colspan="4"  class=2-td2-center style="word-break:break-all" >
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
                <TD colspan="2" class=2-td2-center >
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
                <TD colspan="2"  class=2-td2-center>
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
                <TD class=2-td2-left rowspan = "2">
                  <DIV align=right>税务机关</DIV>
                  <DIV align=right>审核减免税</DIV></TD>
                 <TD class=2-td2-left >
                   <DIV align=right>减免税文书字号&nbsp; </DIV>
                </TD>
                <TD colspan="4" class=2-td2-center >
                 <DIV align=left>&nbsp;<bean:write name="sbxxForm" property="voSpjgxx.hdtzszh"/> </DIV></TD>
              </TR>
               <TR>
                  <TD class=2-td2-left >
                   <DIV align=right>减免税金额&nbsp; </DIV>
                </TD>
                  <TD colspan="4" class=2-td2-center >
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
                <TD colspan="5" class=2-td2-center >
                  <DIV align=left>&nbsp;<bean:write name="sbxxForm" property="voSbzb.bz" /></DIV></TD>
                </TR>

	<!-- 房屋基本信息-->
    		    <bean:define id="fwtdVo" name="sbxxForm" property="voTufwxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx" />
				  <tr>
				    
				    
				     <%if (!sbxxBo.isBZ())
			          {
			          %>
						  	<TD class=2-td2-left  rowspan = "9">
			          <%
			          }
			          else
			          {
			          %>
						  	<TD class=2-td2-left  rowspan = "7">
			          <%
			          }
			          %>
				    
				      <DIV align=right>	土地房屋</DIV>
				      <DIV align=right>权属转移</DIV>
				      <div align=right>
			<control:operation pageid="viewSbxx" operationid="modify" checkername="<%=SessionKey.OPERATION_CHECKER%>" defaulthandle="REQUIRED">
					<logic:equal name="sbxxBo" property="state" value="<%=Constants.ZB_ZTBS_BC%>">

                    </logic:equal>
			</control:operation>
                      </div>
                      </TD>
				      <TD class=2-td2-left >
				        <DIV align=right>房地产项目名称&nbsp;</DIV></TD>
				        <TD colspan="3" class=2-td2-center style="word-break:break-all">
				          <DIV align=left><bean:write name="fwtdVo" property="fdcxmmc" /> </DIV></TD>
                    </tr>
                    <tr>
				      <TD class=2-td2-left >
				        <DIV align=right>是否为二手房&nbsp;</DIV></TD>
				        <TD class=2-td2-left style="word-break:break-all">
				          <DIV align=left>
                              <logic:equal name="fwtdVo" property="sfesf" value="00">非二手房</logic:equal>
                              <logic:equal name="fwtdVo" property="sfesf" value="01">二手房</logic:equal>
                          </DIV>
                      </TD>
				      <TD class=2-td2-left >
				        <DIV align=right>税额调整&nbsp;</DIV></TD>
				        <TD class=2-td2-center style="word-break:break-all">
				          <DIV align=left>

		   <%if (sbzb.getSetz()==null||sbzb.getSetz().equals("0"))
				{
					%>

						  没有进行税额调整
			    <%
			    }
						  else
						  {
			    %>
		   <%if (sbzb.getSetz().equals("1"))
				{
					%>
						  减半征收
			    <%
			    }
			    %>
		   <%if (sbzb.getSetz().equals("2"))
				{
					%>
						  全额征收
			    <%
			    }
						  }
			    %>

						  </DIV></TD>




				  </TR>
				  <TR>
			          <TD class=2-td2-left >
			            <DIV align=right>合约签订时间&nbsp; </DIV>
			          </TD>
			          <TD class=2-td2-left >
			            <DIV align=left>
                                    <%=DataConvert.TimeStamp2String(fwtdVo.getHtqdsj())%>
                                    </DIV></TD>

           <%if (!sbxxBo.isBZ())
          {
          %>
			            <TD class=2-td2-left >
			              <DIV align=right>购房原因&nbsp;</DIV></TD>
          <%
          }
          else
          {
          %>
			            <TD class=2-td2-left >
			              <DIV align=right>分类&nbsp;</DIV></TD>
			    <%
			    }
			    %>

			              <TD colspan="2"  class=2-td2-center >
			                <DIV align=left>
			                  <bean:write  name="fwtdVo" property="flmc"/>&nbsp
			                </DIV></TD>
		          </TR>
		          <TR>
		            <TD class=2-td2-left >
		              <DIV align=right>土地、房屋权属转移类型&nbsp; </DIV> </TD>
		              <TD class=2-td2-left >
		                <DIV align=left>
		                  <bean:write  name="fwtdVo" property="tdfwqszymc"/>&nbsp; </DIV></TD>
		                  <TD class=2-td2-left >
		                    <DIV align=right>房屋类别&nbsp;</DIV></TD>
		                    <TD colspan="2"  class=2-td2-center >
		                      <DIV align=left>
		                        <bean:write  name="fwtdVo" property="fwlxmc"/>&nbsp; </DIV></TD>
		          </TR>
		          <TR>
		            <TD  class=2-td2-left >
		              <DIV align=right>土地、房屋座落地址&nbsp; </DIV>
		            </TD>
		            <TD colspan="4"  class=2-td2-center style="word-break:break-all">
		              <DIV align=left><bean:write name="fwtdVo" property="tdfwzldz" /></DIV></TD>
		            </TR>
                    <TR>
                      <TD class=2-td2-left >
                        <DIV align=right>土地、房屋权属转移面积&nbsp; </DIV>
                      </TD>
                      <TD  class=2-td2-left >
                        <DIV align=left>土地：
                          <%=DataConvert.BigDecimal2String(fwtdVo.getTdfwqszymj())%>
                          ㎡ </DIV></TD>
                        <TD class=2-td2-left >
                          <DIV align=right>房屋建筑面积&nbsp;</DIV></TD>
                          <TD colspan="2"  class=2-td2-center >
                            <DIV align=left>
                           <%=DataConvert.BigDecimal2String(fwtdVo.getFwjzmj())%>
                              ㎡</DIV></TD>
                    </TR>


                    <TR>
                      <TD class=2-td2-left >
                        <DIV align=right>容积率&nbsp; </DIV>
                      </TD>
                      <TD  class=2-td2-left >
                        <DIV align=left>土地：
	  	<%if(fwtdVo.getRjl().equals("00"))
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
	%>&nbsp; </DIV></TD>
                        <TD class=2-td2-left >
                          <DIV align=right>土地级次&nbsp;</DIV></TD>
                          <TD colspan="2"  class=2-td2-center >
                            <DIV align=left>
	<%if(fwtdVo.getTdjc().equals("01"))
	{
	%>
	一级&nbsp;
	<%
	}
	if(fwtdVo.getTdjc().equals("02"))
	{
	%>
	二级&nbsp;
	<%
	}
	%>
	<%if(fwtdVo.getTdjc().equals("03"))
	{
	%>
	三级&nbsp;
	<%
	}
	if(fwtdVo.getTdjc().equals("04"))
	{
	%>
	四级&nbsp;
	<%
	}
	%>
	<%if(fwtdVo.getTdjc().equals("05"))
	{
	%>
	五级&nbsp;
	<%
	}
	if(fwtdVo.getTdjc().equals("06"))
	{
	%>
	六级&nbsp;
	<%
	}
	%>
	<%if(fwtdVo.getTdjc().equals("07"))
	{
	%>
	七级&nbsp;
	<%
	}
	if(fwtdVo.getTdjc().equals("08"))
	{
	%>
	八级&nbsp;
	<%
	}
	%>

	<%if(fwtdVo.getTdjc().equals("09"))
	{
	%>
	九级&nbsp;
	<%
	}
	if(fwtdVo.getTdjc().equals("10"))
	{
	%>
	十级&nbsp;
	<%
	}
	%>

	<%if(fwtdVo.getTdjc().equals("11"))
	{
	%>
	六至十级&nbsp;
	<%
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
	                          <TD  colspan="2"  class=2-td2-center >
	                            <DIV align=left>
                                      <%=DataConvert.BigDecimal2String(fwtdVo.getPgjgrmb())%>
                                      元(人民币) </DIV></TD>
                      </TR>
                      <TR>
                        <TD class=2-td2-left >
                          <DIV align=left>
                             <%=DataConvert.BigDecimal2String(fwtdVo.getCjjgwb())%>
                            元(外币) </DIV></TD>
                          <TD class=2-td2-left >
                            <DIV align=left>
                             币种： <bean:write name="fwtdVo" property="bzmc" />
                            </DIV>
                            <DIV align=left>
                              汇率:&nbsp;<%=DataConvert.BigDecimal2String(fwtdVo.getHldm(),5)%>
                               </DIV></TD>
                            <TD class=2-td2-center  >
                              <DIV align=left>
                                <%=DataConvert.BigDecimal2String(fwtdVo.getZhjgrmb())%>
                                折合元(人民币) </DIV></TD>

                       </tr>

				<%
					}
					%>


          <%if (!sbxxBo.isBZ())
          {
          %>
      <logic:iterate id="cqdata" indexId="cqindex" length="length" name="sbxxForm" property="cqList"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq">

                <TR>
                  <TD class=2-td2-left  rowspan = "4">
                  <DIV align=right>	拆迁</DIV>
                   <DIV align=right>情况</DIV>
				      <div align=right>
			<control:operation pageid="viewSbxx" operationid="modify" checkername="<%=SessionKey.OPERATION_CHECKER%>" defaulthandle="OMIT">
					<logic:equal name="sbxxBo" property="state" value="<%=Constants.ZB_ZTBS_BC%>">

                    </logic:equal>
			</control:operation>
                      </div>
                  </TD>
                  <TD class=2-td2-center style="word-break:break-all">
                   <DIV align=right>被拆迁房屋坐落地址&nbsp; </DIV>
                </TD>
                <TD colspan="4" class=2-td2-center >
                  <DIV align=left>
                      <bean:write name = "cqdata" property = "zldz"/>
                  </DIV>
                </TD>
                </TR>
                <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>拆迁协议号码&nbsp; </DIV>
                </TD>
                <TD colspan="4" class=2-td2-center >
                <DIV align=left>
                    <bean:write name = "cqdata" property = "cqxyh"/>
                </DIV>
                </TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left >
                   <DIV align=right> 拆迁补偿额&nbsp; </DIV>
                </TD>
                <TD  class=2-td2-center  colspan=4>
                  <DIV align=left>
                      <%=DataConvert.BigDecimal2String(cqdata.getCqbce())%>
                     元(人民币)
                  </DIV>
                </TD>
               </TR>
               <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>本次使用补偿额&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                   <DIV align=left>
                     <%=DataConvert.BigDecimal2String(cqdata.getBcsybce())%>
                      元(人民币)
                   </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=right>拆迁补偿剩余额&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center >
                  <DIV align=left>
                      &nbsp;
                      <%=DataConvert.BigDecimal2String(cqdata.getCqbcsye())%>
                      元(人民币)
                  </DIV>
                  </TD>
                  </TR>
       </logic:iterate>

      <logic:iterate id="gydata" indexId="gfindex" length="length" name="sbxxForm" property="gyzfList"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf">

               <TR>
                   <TD class=2-td2-left  rowspan = "6">
                  <DIV align=right>	已购公有住房</DIV>
                   <DIV align=right>上市出售情况</DIV>
				      <div align=right >
			<control:operation pageid="viewSbxx" operationid="modify" checkername="<%=SessionKey.OPERATION_CHECKER%>" defaulthandle="REQUIRED">
       			<logic:equal name="sbxxBo" property="state" value="<%=Constants.ZB_ZTBS_BC%>">

                </logic:equal>
			</control:operation>
                      </div>
				   <br>
				   </TD>
                  <TD   class=2-td2-left >
                   <DIV  align=right>出售合同号码&nbsp;</DIV>
                </TD>
                <TD colspan="4" class=2-td2-center >
                  <DIV align=left><bean:write name="gydata" property="yggyzfqszsh" /></DIV></TD>
                  </TR>
                <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>座落地址&nbsp; </DIV>
                </TD>
                 <TD colspan="4" class=2-td2-center style="word-break:break-all">
                  <DIV align=left><bean:write name="gydata" property="zldz" />&nbsp;</DIV></TD>
                  </TR>
                  <TR>
                 <TD  class=2-td2-left >
                   <DIV align=right>出售合同（契约）签订时间&nbsp;</DIV>
                </TD>
                <TD colspan="4" class=2-td2-center >
                  <DIV align=left>
                  <%=DataConvert.TimeStamp2String(gydata.getQdsj())%>
                  </DIV></TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left >
                   <DIV align=right> 建筑面积&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                   <DIV align=left>
                     <%=DataConvert.BigDecimal2String(gydata.getJzmj())%>
                     ㎡ </DIV></TD>
                <TD class=2-td2-left >
                  <DIV align=right>成交价格&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center >
                  <DIV align=left>
                     <%=DataConvert.BigDecimal2String(gydata.getCjjg())%>
                    元(人民币)</DIV></TD>
                  </TR>
                 <TR>
                <TD class=2-td2-left >
                  <DIV align=right>本次抵扣额&nbsp;</DIV></TD>
                <TD colspan="4"  class=2-td2-center >
                  <DIV align=left>
                     <%=DataConvert.BigDecimal2String(gydata.getBcdke())%>
                    元(人民币)</DIV></TD>
                  </TR>
                   <TR>
                   <TD class=2-td2-left >
                  <DIV align=right>剩余额&nbsp;</DIV></TD>
                <TD colspan="4"  class=2-td2-center >
                  <DIV align=left>
                     <%=DataConvert.BigDecimal2String(gydata.getSye())%>
                    元(人民币)</DIV></TD>
                  </TR>

       </logic:iterate>



           <!-- 显示房产交换信息-->
            <logic:equal name="sbxxForm" property="addedFwjhxx" value="true">
              <bean:define id="fwjhBo" name="sbxxForm" property="voFwjh" type="com.creationstar.bjtax.qsgl.model.bo.FwjhxxBo"/>

              <logic:equal name="fwjhBo" property="jhperson"  value="0">
                <bean:define id="dfgrxx" name="sbxxForm" property="voFwjh.grxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx"/>
              </logic:equal>
              <logic:equal name="fwjhBo" property="jhperson"  value="1">
                <bean:define id="dffgrxx" name="sbxxForm" property="voFwjh.fgrxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx"/>
              </logic:equal>

              <bean:define id="dffwtdxx" name="sbxxForm" property="voFwjh.tufwxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"/>
              <!--TR>
                <TD class=2-td2-left >对方缴款方式</TD>
                <TD class=2-td2-left > <DIV align=left>
                  <bean:write name="fwjhBo" property="jkfsmc" /></td>
                  <TD class=2-td2-left >对方房屋土地管理部门受理号</TD>
                  <TD colspan="3" class=2-td2-center ><DIV align=left><bean:write name="fwjhBo" property="fcjslh" />&nbsp;</DIV></TD>
                </TR-->
                <!--交换个人信息显示-->
                <logic:equal name="fwjhBo" property="jhperson"  value="0">
                  <TR>
                    <TD class=2-td2-left  rowspan = "3">
                      <DIV align=right>对方个人填</DIV>
                      <DIV align=right>写部分</DIV></TD>
                      <TD class=2-td2-left >
                        <DIV align=right>纳税人名称&nbsp; </DIV>
                      </TD>
                      <DIV align=right>&nbsp; </DIV>
                      <TD class=2-td2-left style="word-break:break-all">
                        <DIV align=left> <bean:write name="dfgrxx" property="nsrmc" />&nbsp</DIV></TD>
                        <TD class=2-td2-left >
                          <DIV align=right>联系电话&nbsp; </DIV></TD>
                          <TD colspan="2"  class=2-td2-center >
                            <DIV align=left><bean:write name="dfgrxx" property="lxdh" />&nbsp; </DIV></TD>
                          </TR>
                  <TR>
                    <TD class=2-td2-left >
                      <DIV align=right>身份证件类型&nbsp; </DIV>
                    </TD>
                    <TD class=2-td2-left >
                      <DIV align=left>
                        <bean:write name="dfgrxx" property="sfzjlxmc" />
                      </DIV></TD>
                      <TD   class=2-td2-left >
                        <DIV align=right>身份证件号码&nbsp;</DIV></TD>
                        <TD colspan="2"  class=2-td2-center >
                          <DIV align=left><bean:write name="dfgrxx" property="sfzjhm" /></DIV></TD>
                        </TR>

                <TR>
                  <TD class=2-td2-left >
                    <DIV align=right>国籍&nbsp; </DIV>
                  </TD>
                  <TD class=2-td2-left >
                    <DIV align=left>
                      <bean:write name="dfgrxx" property="gjmc" />
                    </DIV></TD>
                    <TD   class=2-td2-left >
                      <DIV align=right>&nbsp;</DIV></TD>
                      <TD colspan="2"  class=2-td2-center >
                        <DIV align=left>&nbsp;</DIV></TD>
                      </TR>
            </logic:equal>

            <!--交换非个人信息显示-->

            <logic:equal name="fwjhBo" property="jhperson"  value="1">

              <TR>
                <TD class=2-td2-left rowspan = "4">
              <DIV align=right>对方非个人</DIV>
              <DIV align=right>填写部分</DIV></TD>
              <TD class=2-td2-left >
                <DIV align=right>计算机代码&nbsp; </DIV>
              </TD>
              <DIV align=right>&nbsp; </DIV>
              <TD class=2-td2-left >
                <DIV align=left> <bean:write name="dffgrxx" property="jsjdm" />&nbsp</DIV></TD>
                <TD class=2-td2-left >
                  <DIV align=right>&nbsp; </DIV></TD>
                  <TD colspan="2"  class=2-td2-center >
                    <DIV align=left> &nbsp;</DIV></TD>
                  </TR>
              <TR>
                <TD class=2-td2-left >
                  <DIV align=right>纳税人名称&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=left>
                    <bean:write name="dffgrxx" property="nsrmc" />&nbsp;
                  </DIV></TD>
                  <TD   class=2-td2-left >
                    <DIV align=right>纳税人类型&nbsp;</DIV></TD>
                    <TD colspan="2"  class=2-td2-center >
                         <DIV align=left><bean:write name="dffgrxx" property="nsrlxmc" />&nbsp;</DIV></TD>
                        </TR>

            <TR>
              <TD class=2-td2-left >
                <DIV align=right>开户银行&nbsp; </DIV>
              </TD>
              <TD class=2-td2-left >
                <DIV align=left>
                  <bean:write name="dffgrxx" property="khyhmc" />&nbsp;
                </DIV></TD>
                <TD   class=2-td2-left >
                  <DIV align=right>银行账号&nbsp;</DIV></TD>
                  <TD colspan="2"  class=2-td2-center >
                    <DIV align=left><bean:write name="dffgrxx" property="yhzh" />&nbsp;</DIV></TD>
                  </TR>


              <TR>
                <TD class=2-td2-left >
                  <DIV align=right>联系人姓名&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=left>
                    <bean:write name="dffgrxx" property="lxrxm" />&nbsp;
                  </DIV></TD>
                  <TD   class=2-td2-left >
                    <DIV align=right>联系电话&nbsp;</DIV></TD>
                    <TD colspan="2"  class=2-td2-center >
                      <DIV align=left><bean:write name="dffgrxx" property="lxdh" />&nbsp</DIV></TD>
                    </TR>

              </logic:equal>
              <TR>
                <TD class=2-td2-left rowspan = "7">
                  <DIV align=right>	交换土地房屋</DIV>
                  <DIV align=right>权属转移</DIV>
                  				      <div align=right>
			<control:operation pageid="viewSbxx" operationid="modify" checkername="<%=SessionKey.OPERATION_CHECKER%>" defaulthandle="REQUIRED">
					<logic:equal name="sbxxBo" property="state" value="<%=Constants.ZB_ZTBS_BC%>">

                    </logic:equal>
			</control:operation>
                      </div>

                  </TD>


                  <TD class=2-td2-left >
                    <DIV align=right>房地产项目名称&nbsp;</DIV></TD>
                    <TD colspan="4" class=2-td2-center style="word-break:break-all">
                      <DIV align=left><bean:write name="dffwtdxx" property="fdcxmmc" /></DIV></TD>
                    </TR>
	            <TR>
	              <TD class=2-td2-left >
	                <DIV align=right>合约签订时间&nbsp; </DIV>
	              </TD>
	              <TD class=2-td2-left >
	                <DIV align=left><%=DataConvert.TimeStamp2String(dffwtdxx.getHtqdsj())%> </DIV></TD>
	                <TD class=2-td2-left >
	                  <DIV align=right>分类&nbsp;</DIV></TD>
	                  <TD colspan="2"  class=2-td2-center >
	                    <DIV align=left>
	                      <bean:write  name="dffwtdxx" property="flmc"/>

	                    </DIV></TD>
	                  </TR>
              <!--TR>
                <TD class=2-td2-left>
                  <DIV align=right>土地、房屋权属转移类型&nbsp; </DIV> </TD>
                  <TD class=2-td2-left >
                    <DIV align=left>

                      <bean:write name="dffwtdxx" property="tdfwqszymc" />
                    </DIV></TD>
                    <TD class=2-td2-left >
                      <DIV align=right>房屋类别&nbsp;</DIV></TD>
                      <TD colspan="2"  class=2-td2-center >
                        <DIV align=left>

                          <bean:write name="dffwtdxx" property="fwlxmc" />
                        </DIV></TD>
                      </TR-->
                  <TR>
                    <TD  class=2-td2-left style="word-break:break-all">
                      <DIV align=right>土地、房屋座落地址&nbsp; </DIV>
                    </TD>
                    <TD colspan="4"  class=2-td2-center >
                      <DIV align=left>
                        <bean:write name="dffwtdxx" property="tdfwzldz" />
                      </DIV>
                    </DIV></TD>
                  </TR>
                  <TR>
                    <TD class=2-td2-left >
                      <DIV align=right>土地、房屋权属转移面积&nbsp; </DIV>
                    </TD>
                    <TD  class=2-td2-left >
                      <DIV align=left>土地：
                        <%=DataConvert.BigDecimal2String(dffwtdxx.getTdfwqszymj())%>
                        ㎡ </DIV></TD>
                        <TD class=2-td2-left >
                          <DIV align=right>房屋建筑面积&nbsp;</DIV></TD>
                          <TD colspan="2"  class=2-td2-center >
                            <DIV align=left>
                              <%=DataConvert.BigDecimal2String(dffwtdxx.getFwjzmj())%>
                              ㎡</DIV></TD>
                            </TR>
                <TR>
                  <TD class=2-td2-left rowspan = "2">
                    <DIV align=right>成交价格（评估价格）</DIV>
                    <TD  class=2-td2-left >
                      <DIV align=left>
                        <%=DataConvert.BigDecimal2String(dffwtdxx.getCjjgrmb())%>
                        元(人民币) </DIV></TD>
                        <TD class=2-td2-left >
                          <DIV align=right>税务机关核定价格&nbsp; </DIV></TD>
                          <TD  colspan="2"  class=2-td2-center >
                            <DIV align=left>
                              <%=DataConvert.BigDecimal2String(dffwtdxx.getPgjgrmb())%>
                          元(人民币) </DIV></TD>
                        </TR>
                <TR>
                  <TD class=2-td2-left >
                    <DIV align=left>
                      <%=DataConvert.BigDecimal2String(dffwtdxx.getCjjgwb())%>
                      元(外币) </DIV></TD>
                      <TD class=2-td2-left >
                        <DIV align=left>
                          币种：<bean:write name="dffwtdxx" property="bzmc" />

                        </DIV>
                        <DIV align=left>
                          汇率:&nbsp;<%=DataConvert.BigDecimal2String(dffwtdxx.getHldm(),5)%>
                           </DIV></TD>
                          <TD class=2-td2-center  colspan="2" >
                            <DIV align=left>
                              <%=DataConvert.BigDecimal2String(dffwtdxx.getZhjgrmb())%>
                              折合元(人民币) </DIV></TD>
                            </TR>
      </logic:equal>

    <%
    } //end sbxxBo.isBZ()
    %>

                  </TBODY></TABLE><BR>
         <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>
			  <BR>
			  <TR>
			  	<TD class=2-td2-t-left width="12%">当前状态</TD>
			  	<TD class=2-td2-t-left width="25%" >
			  	  <DIV align=left>&nbsp<%=SbState.getStateName(sbxxBo.getVoSbzb().getZtbs())%> </DIV></TD>
			  	<TD class=2-td2-t-left width="15%" >
			  	  <DIV align=left>&nbsp 处理前上次状态</DIV></TD>
			  	<TD class=2-td2-t-left width="25%" >
			  	  <DIV align=left>&nbsp<%=SbState.getCancelStateName(sbxxBo.getVoSbzb().getZtbs(),sbxxBo.getVoSbzb().getBljmsbs())%> </DIV></TD>
			  	<TD class=2-td2-t-center width="20%" >
			  	  <DIV align=left>&nbsp
			  	  <% if (!sbxxBo.getState().equals(Constants.ZB_ZTBS_BC) && !sbxxBo.getState().equals(Constants.ZB_ZTBS_ZF) && !sbxxBo.getState().equals(Constants.ZB_ZTBS_YRK))
			  	     {
			  	  %>

			  	  <%
			  	     }
			  	  %>
			  	  </DIV></TD>

			  </TR>
			  </TBODY>
			  </TABLE>
			<BR>
            <DIV align=center>
            <!--input type=button name="btnInputFwjbxx" value="录入房屋基础信息" onclick="doSubmitForm('Fwjbxx')">
            <input type=button name="btnInputCqxx" value="录入拆迁情况" onclick = "doSubmitForm('Cqxx');">
            <input type=button name="btnInputGyzf" value="录入已购公有住房上市情况" onclick = "doSubmitForm('Gyzf');">
            <input type=button name="btnInputJmsb" value="录入减免申报表" onclick = "doSubmitForm(Jmsb);"><br><br-->

	  	  <% if (!sbxxBo.getState().equals(Constants.ZB_ZTBS_ZF) && !sbxxBo.getState().equals(Constants.ZB_ZTBS_YRK))
	  	     {
	  	  %>

	  	  <%
	  	     }
	  	  %>

	  	  <% if ((sbxxBo.getState().equals(Constants.ZB_ZTBS_JS_TY)) ||
	  	         (sbxxBo.getState().equals(Constants.ZB_ZTBS_DY_HD))
	  	         )
	  	     {
	  	  %>

	  	  <%
	  	     }
	  	  %>
	  	  <% if (sbxxBo.getState().equals(Constants.ZB_ZTBS_BC))
	  	     {
	  	  %>

	  	  <%
	  	     }
	  	  %>



            <IMG alt=退出 height=22 id=tuichu name=tuichu
            onclick="doSubmitForm()" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
            src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand" width=79>
            </DIV><BR>

</html:form>
<script language="javascript" type='text/JavaScript'>

</script>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
