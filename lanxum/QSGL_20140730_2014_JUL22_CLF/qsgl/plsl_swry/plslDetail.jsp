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
<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.PlslForm2"%>


<%String sfesf="00";
  String setz="2";
%>
<HTML><HEAD><TITLE>北京市地方税务局契税申报显示表</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>

<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType){
  document.all.operationType.value=operationType;
  if(operationType=="Modify" )
{
    document.forms[0].operationType.value='Redirect';
    document.forms[0].subaction.value='modify';
  }
  else if (operationType=="Delete" )
 {
     if (!confirm("你确定删除这条申报记录吗"))
     {
        return false;
     }
  }

   document.forms[0].submit();
}

//税额调整为“首次购买90㎡以下普通住房”时，土地房屋权属转移类型为“房屋买卖”，是否二手房可录
function checkSelectOne()
{
  if(document.forms[0].setz.value=="5")
  {
  	var a=document.getElementById("lx").innerHTML;
  	var b=document.getElementById("mj").innerHTML;
  	if(a.substring(6,10)!="房屋买卖")
  	{
  		alert("土地、房屋权属转移类型为“房屋买卖”时，税额调整才可以选择“首次购买90㎡以下普通住房”");
  		document.forms[0].setz.value="2";
  	}

  	var ce = parseFloat(b.substring(6,b.length-2))-90;
    if(ce>0)
    {
    	alert("房屋建筑面积小于等于90㎡时，税额调整才可以选择“首次购买90㎡以下普通住房”");
    	document.forms[0].setz.value="2";
    }
  	var ce1 = parseFloat(b.substring(6,b.length-2))-0;
    if(ce1==0)
    {
    	alert("房屋建筑面积大于0㎡时，税额调整才可以选择“首次购买90㎡以下普通住房”");
    	document.forms[0].setz.value="2";
    }

  }
}
</SCRIPT>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="批量受理>北京市地方税务局契税申报显示表"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>
<SCRIPT language=javascript>
</SCRIPT>
<br>
 <html:form action="/plsl_swry/plslDetail.do">

 <TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>

         <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>北京市地方税务局契税申报显示表</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
              <html:hidden property="operationType" value=""/>

            <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>
			  <BR>
			  <TR>
			  	<TD class=2-td2-t-left width="12%">纳税申报表号</TD>


			  	<TD class=2-td2-t-left width="43%" colspan=3><DIV align=left>&nbsp; </DIV></TD>

			  </TR>
			  <TR>
			  	<TD class=2-td2-left width="12%">缴款方式&nbsp;</TD>
			  	<TD class=2-td2-left width="43%"> <DIV align=left>
                                  &nbsp;
				<TD class=2-td2-left width="22%">房屋土地管理部门受理号&nbsp;</TD>
				<TD class=2-td2-center width="23%"><DIV align=left>&nbsp;

				<logic:equal name="plslForm2" property="tdbm"  value="1">
				<bean:write name="plslForm2" property="tufwxx.fwtdbmdm" />
				</logic:equal>
				</DIV></TD>
			  </TR>
			  </TBODY>
			  </TABLE>
			  <br>
            <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
               <TR>
                <TD class=2-td2-t-left width="10%">税额调整</TD>
				<TD class=2-td2-t-left><select name="setz" onchange="checkSelectOne()"><option value="2" checked>全额征收</option>
						<option value="1">减半征收</option>
				<logic:equal name="plslForm2" property="person"  value="1">
						<option value="5">首次购买90㎡以下普通住房</option>
                        <option value="6">经济适用房</option>
				</logic:equal></select>
							<font color=red>对于符合普通标准住宅标准的申报数据，计税价格根据此项确定。</font></TD>
				<TD class=2-td2-t-center>&nbsp;
                    <input type="checkbox" name="sfesf" value="01">
                    是否为二手房</TD>
			</TABLE>
			<TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>

<logic:equal name="plslForm2" property="person"  value="1">
              <TR>
                <TD class=2-td2-t-left width="10%" rowspan = "3">
                  <DIV align=right>个人填</DIV>
                   <DIV align=right>写部分</DIV></TD>

                  <TD class=2-td2-t-left width="20%">
                   <DIV align=right>纳税人名称&nbsp; </DIV>
                </TD>

                <DIV align=right>&nbsp; </DIV>
                  <TD class=2-td2-t-left width="20%">
                    <DIV align=left>&nbsp;<bean:write name="plslForm2" property="grxx.nsrmc" /> </DIV></TD>
                <TD class=2-td2-t-left width="25%">
                  <DIV align=right>联系电话&nbsp; </DIV></TD>
                <TD colspan="2"  class=2-td2-t-center width="25%">
                   <DIV align=left>&nbsp;<bean:write name="plslForm2" property="grxx.lxdh" /> </DIV></TD>
                  </TR>
               <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>身份证件类型&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=left>
                   &nbsp; <bean:write name="plslForm2" property="grxx.sfzjlxmc"/>
                    </DIV></TD>
                <TD   class=2-td2-left >
                  <DIV align=right>身份证件号码&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center>
                   <DIV align=left>&nbsp;<bean:write name="plslForm2" property="grxx.sfzjhm"/> </DIV></TD>
                  </TR>
               <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>国籍&nbsp; </DIV>
                </TD>
                <TD class=2-td2-center colspan="4">
                  <DIV align=left>
                    &nbsp;<bean:write name="plslForm2" property="grxx.gjmc"/>
                    </DIV></TD>
                  </TR>
</logic:equal>



<logic:equal name="plslForm2" property="person"  value="0">
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
                        &nbsp;<bean:write name = "plslForm2" property = "fgrxx.jsjdm" />
                    </DIV>
                  </TD>

                <TD class=2-td2-t-left width="20%">
                  <DIV align=right>纳税人类型&nbsp; </DIV></TD>

                <TD colspan="2"  class=2-td2-t-center width="25%">
                   <DIV align=left>
                       &nbsp;<bean:write name = "plslForm2" property = "fgrxx.nsrlxmc" />
                   </DIV>
                </TD>
             </TR>
             <TR>

                <TD  class=2-td2-left >
                  <DIV align=right>纳税人名称&nbsp;</DIV></TD>

                <TD colspan="4"  class=2-td2-center style="word-break:break-all" >
                   <DIV align=left>
                       &nbsp;<bean:write name = "plslForm2" property = "fgrxx.nsrmc" />
                   </DIV>
                 </TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>开户银行&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=left>
                      &nbsp;<bean:write name = "plslForm2" property = "fgrxx.khyhmc" />
                  </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=right>银行账号&nbsp;</DIV></TD>
                <TD colspan="2" class=2-td2-center >
                   <DIV align=left>
                       &nbsp;<bean:write name = "plslForm2" property = "fgrxx.yhzh" />
                   </DIV>
                 </TD>
                </TR>
                <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>联系人姓名&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                   <DIV align=left>
                       &nbsp;<bean:write name = "plslForm2" property = "fgrxx.lxrxm" />
                   </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=right>联系电话&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center>
                   <DIV align=left>
                       &nbsp;<bean:write name = "plslForm2" property = "fgrxx.lxdh" />
                   </DIV>
                </TD>
                </TR>
</logic:equal>


<logic:equal name="plslForm2" property="sp"  value="1">
				<bean:define id="spxx" name="plslForm2" property="spjgxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx"/>
               <TR>
                <TD class=2-td2-left rowspan = "2">
                  <DIV align=right>税务机关</DIV>
                  <DIV align=right>审核减免税</DIV></TD>
                 <TD class=2-td2-left >
                   <DIV align=right>减免税文书字号&nbsp; </DIV>
                </TD>
                <TD colspan="4" class=2-td2-center >
                 <DIV align=left>&nbsp;<bean:write name="plslForm2" property="spjgxx.hdtzszh"/> </DIV></TD>
              </TR>
               <TR>
                  <TD class=2-td2-left >
                   <DIV align=right>减免税金额&nbsp; </DIV>
                </TD>
                  <TD colspan="4" class=2-td2-center >
                   <DIV align=left>&nbsp;<%=DataConvert.BigDecimal2String(spxx.getJmsje())%></DIV>
                </TD>
                  </TR>
</logic:equal>

<logic:equal name="plslForm2" property="sp"  value="0">
               <TR>
                <TD class=2-td2-left rowspan = "2">
                  <DIV align=right>税务机关</DIV>
                  <DIV align=right>审核减免税</DIV></TD>
                 <TD class=2-td2-left >
                   <DIV align=right>减免税文书字号&nbsp; </DIV>
                </TD>
                <TD colspan="4" class=2-td2-center >
                 <DIV align=left>&nbsp;</DIV></TD>
              </TR>
               <TR>
                  <TD class=2-td2-left >
                   <DIV align=right>减免税金额&nbsp; </DIV>
                </TD>
                  <TD colspan="4" class=2-td2-center >
                   <DIV align=left>&nbsp;</DIV>
                </TD>
                  </TR>
</logic:equal>

                 <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>备注&nbsp; </DIV>
                </TD>
                <TD colspan="5" class=2-td2-center >
                  <DIV align=left>&nbsp;<bean:write name="plslForm2" property="tufwxx.bz" />
				</DIV></TD>
                </TR>

	<!-- 房屋基本信息-->
    		    <bean:define id="fwtdVo" name="plslForm2" property="tufwxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx" />
				  <tr>
				    <TD class=2-td2-left  rowspan = "7">
				      <DIV align=right>	土地房屋</DIV>
				      <DIV align=right>权属转移</DIV>
                      </TD>
				      <TD class=2-td2-left >
				        <DIV align=right>房地产项目名称&nbsp;</DIV></TD>
				        <TD colspan="4" class=2-td2-center style="word-break:break-all">
				          <DIV align=left>&nbsp;<bean:write name="fwtdVo" property="fdcxmmc" /> </DIV></TD>
				  </TR>
				  <TR>
			          <TD class=2-td2-left >
			            <DIV align=right>合约签订时间&nbsp; </DIV>
			          </TD>
			          <TD class=2-td2-left >
			            <DIV align=left>
                                    &nbsp;<%=DataConvert.TimeStamp2String(fwtdVo.getHtqdsj())%>
									  <%sfesf=fwtdVo.getSfesf();
									    setz=fwtdVo.getSetz();
									  %>
                                    </DIV></TD>

			            <TD class=2-td2-left >
			              <DIV align=right>分类&nbsp;</DIV></TD>
			              <TD colspan="2"  class=2-td2-center >
			                <DIV align=left>
			                  &nbsp;<bean:write  name="fwtdVo" property="flmc"/>&nbsp
			                </DIV></TD>
		          </TR>

		          <TR>
		            <TD class=2-td2-left >
		              <DIV align=right>土地、房屋权属转移类型&nbsp; </DIV> </TD>
		              <TD class=2-td2-left >
		                <DIV align=left id="lx">
		                  &nbsp;<bean:write  name="fwtdVo" property="tdfwqszymc"/>&nbsp; </DIV></TD>
		                  <TD class=2-td2-left >
		                    <DIV align=right>房屋类别&nbsp;</DIV></TD>
		                    <TD colspan="2"  class=2-td2-center >
		                      <DIV align=left>
		                        &nbsp;<bean:write  name="fwtdVo" property="fwlxmc"/>&nbsp; </DIV></TD>
		          </TR>
		          <TR>
		            <TD  class=2-td2-left >
		              <DIV align=right>土地、房屋座落地址&nbsp; </DIV>
		            </TD>
		            <TD colspan="4"  class=2-td2-center style="word-break:break-all">
		              <DIV align=left>&nbsp;<bean:write name="fwtdVo" property="tdfwzldz" /></DIV></TD>
		            </TR>
                    <TR>
                      <TD class=2-td2-left >
                        <DIV align=right>土地、房屋权属转移面积&nbsp; </DIV>
                      </TD>
                      <TD  class=2-td2-left >
                        <DIV align=left>&nbsp;土地：
                          <%=DataConvert.BigDecimal2String(fwtdVo.getTdfwqszymj())%>
                          ㎡ </DIV></TD>
                        <TD class=2-td2-left >
                          <DIV align=right>房屋建筑面积&nbsp;</DIV></TD>
                          <TD colspan="2"  class=2-td2-center >
                            <DIV align=left id="mj">
                           &nbsp;<%=DataConvert.BigDecimal2String(fwtdVo.getFwjzmj())%>
                              ㎡</DIV></TD>
                    </TR>
                    <TR>
	                    <TD class=2-td2-left rowspan = "2">
	                      <DIV align=right>成交价格（评估价格）&nbsp;</DIV>
	                      <TD  class=2-td2-left >
	                        <DIV align=left>
                                  &nbsp;<%=DataConvert.BigDecimal2String(fwtdVo.getCjjgrmb())%>
                                  元(人民币) </DIV></TD>
	                        <TD class=2-td2-left >
	                          <DIV align=right>税务机关核定价格&nbsp; </DIV></TD>
	                          <TD  colspan="2"  class=2-td2-center >
	                            <DIV align=left>
                                     &nbsp; <%=DataConvert.BigDecimal2String(fwtdVo.getPgjgrmb())%>
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


      <logic:iterate id="cqdata" indexId="cqindex" length="length" name="plslForm2" property="cqxxList"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq">

                <TR>
                  <TD class=2-td2-left  rowspan = "4">
                  <DIV align=right>	拆迁</DIV>
                   <DIV align=right>情况</DIV>
                  </TD>

                  <TD class=2-td2-center style="word-break:break-all">
                   <DIV align=right>被拆迁房屋坐落地址&nbsp; </DIV>
                </TD>
                <TD colspan="4" class=2-td2-center >
                  <DIV align=left>
                      &nbsp;<bean:write name = "cqdata" property = "zldz"/>
                  </DIV>
                </TD>
                </TR>
                <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>拆迁协议号码&nbsp; </DIV>
                </TD>
                <TD colspan="4" class=2-td2-center >
                <DIV align=left>
                    &nbsp;<bean:write name = "cqdata" property = "cqxyh"/>
                </DIV>
                </TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left >
                   <DIV align=right> 拆迁补偿额&nbsp; </DIV>
                </TD>
                <TD  class=2-td2-center  colspan=4>
                  <DIV align=left>
                      &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getCqbce())%>
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
                     &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getBcsybce())%>
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

      <logic:iterate id="gydata" indexId="gfindex" length="length" name="plslForm2" property="gyzfxxList"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf">

               <TR>
                   <TD class=2-td2-left  rowspan = "6">
                  <DIV align=right>	已购公有住房</DIV>
                   <DIV align=right>上市出售情况</DIV>
				   <br>

				   </TD>
                  <TD   class=2-td2-left >
                   <DIV  align=right>出售合同号码&nbsp;</DIV>
                </TD>
                <TD colspan="4" class=2-td2-center >
                  <DIV align=left>&nbsp;<bean:write name="gydata" property="yggyzfqszsh" /></DIV></TD>
                  </TR>
                <TR>
                 <TD class=2-td2-left >
                   <DIV align=right>座落地址&nbsp; </DIV>
                </TD>
                 <TD colspan="4" class=2-td2-center style="word-break:break-all">
                  <DIV align=left>&nbsp;<bean:write name="gydata" property="zldz" /></DIV></TD>
                  </TR>
                  <TR>
                 <TD  class=2-td2-left >
                   <DIV align=right>出售合同（契约）签订时间&nbsp;</DIV>
                </TD>
                <TD colspan="4" class=2-td2-center >
                  <DIV align=left>
                  &nbsp;<%=DataConvert.TimeStamp2String(gydata.getQdsj())%>
                  </DIV></TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left >
                   <DIV align=right> 建筑面积&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                   <DIV align=left>
                     &nbsp;<%=DataConvert.BigDecimal2String(gydata.getJzmj())%>
                     ㎡ </DIV></TD>
                <TD class=2-td2-left >
                  <DIV align=right>成交价格&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center >
                  <DIV align=left>
                     &nbsp;<%=DataConvert.BigDecimal2String(gydata.getCjjg())%>
                    元(人民币)</DIV></TD>
                  </TR>
                 <TR>
                <TD class=2-td2-left >
                  <DIV align=right>本次抵扣额&nbsp;</DIV></TD>
                <TD colspan="4"  class=2-td2-center >
                  <DIV align=left>
                     &nbsp;<%=DataConvert.BigDecimal2String(gydata.getBcdke())%>
                    元(人民币)</DIV></TD>
                  </TR>
                   <TR>
                   <TD class=2-td2-left >
                  <DIV align=right>剩余额&nbsp;</DIV></TD>
                <TD colspan="4"  class=2-td2-center >
                  <DIV align=left>
                     &nbsp;<%=DataConvert.BigDecimal2String(gydata.getSye())%>
                    元(人民币)</DIV></TD>
                  </TR>

       </logic:iterate>



           <!-- 显示房产交换信息-->
      <logic:iterate id="fwjhBo" indexId="fjindex" length="length" name="plslForm2" property="fwjhxxList"
            type="com.creationstar.bjtax.qsgl.model.bo.PldrBo2">

              <%
					if(fwjhBo.grxx!=null)
					{
				%>

                <bean:define id="dfgrxx" name="fwjhBo" property="grxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx"/>
              <%
					}

			  else
			  {
			%>
                <bean:define id="dffgrxx" name="fwjhBo" property="fgrxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx"/>
              <%
					}
				%>

              <bean:define id="dffwtdxx" name="fwjhBo" property="tufwxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"/>
              <!--TR>
                <TD class=2-td2-left >对方缴款方式</TD>
                <TD class=2-td2-left > <DIV align=left>
                  <bean:write name="fwjhBo" property="jkfsmc" /></td>
                  <TD class=2-td2-left >对方房屋土地管理部门受理号</TD>
                  <TD colspan="3" class=2-td2-center ><DIV align=left><bean:write name="fwjhBo" property="fcjslh" />&nbsp;</DIV></TD>
                </TR-->
                <!--交换个人信息显示-->
              <%
					if(fwjhBo.grxx!=null)
					{
				%>
                  <TR>
                    <TD class=2-td2-left  rowspan = "3">
                      <DIV align=right>对方个人填</DIV>
                      <DIV align=right>写部分</DIV></TD>
                      <TD class=2-td2-left >
                        <DIV align=right>纳税人名称&nbsp; </DIV>
                      </TD>
                      <DIV align=right>&nbsp; </DIV>
                      <TD class=2-td2-left style="word-break:break-all">
                        <DIV align=left>&nbsp; <bean:write name="dfgrxx" property="nsrmc" /></DIV></TD>
                        <TD class=2-td2-left >
                          <DIV align=right>联系电话&nbsp; </DIV></TD>
                          <TD colspan="2"  class=2-td2-center >
                            <DIV align=left>&nbsp;<bean:write name="dfgrxx" property="lxdh" /></DIV></TD>
                          </TR>
                  <TR>
                    <TD class=2-td2-left >
                      <DIV align=right>身份证件类型&nbsp; </DIV>
                    </TD>
                    <TD class=2-td2-left >
                      <DIV align=left>
                        &nbsp;<bean:write name="dfgrxx" property="sfzjlxmc" />
                      </DIV></TD>
                      <TD   class=2-td2-left >
                        <DIV align=right>身份证件号码&nbsp;</DIV></TD>
                        <TD colspan="2"  class=2-td2-center >
                          <DIV align=left>&nbsp;<bean:write name="dfgrxx" property="sfzjhm" /></DIV></TD>
                        </TR>

                <TR>
                  <TD class=2-td2-left >
                    <DIV align=right>国籍&nbsp; </DIV>
                  </TD>
                  <TD class=2-td2-left >
                    <DIV align=left>
                      &nbsp;<bean:write name="dfgrxx" property="gjmc" />
                    </DIV></TD>
                    <TD   class=2-td2-left >
                      <DIV align=right>&nbsp;</DIV></TD>
                      <TD colspan="2"  class=2-td2-center >
                        <DIV align=left>&nbsp;</DIV></TD>
                      </TR>
              <%
					}
			  else
			  {
			%>
            <!--交换非个人信息显示-->
              <TR>
                <TD class=2-td2-left rowspan = "4">
              <DIV align=right>对方非个人</DIV>
              <DIV align=right>填写部分</DIV></TD>
              <TD class=2-td2-left >
                <DIV align=right>计算机代码&nbsp; </DIV>
              </TD>
              <DIV align=right>&nbsp; </DIV>
              <TD class=2-td2-left >
                <DIV align=left>&nbsp; <bean:write name="dffgrxx" property="jsjdm" /></DIV></TD>
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
                    &nbsp;<bean:write name="dffgrxx" property="nsrmc" />
                  </DIV></TD>
                  <TD   class=2-td2-left >
                    <DIV align=right>纳税人类型&nbsp;</DIV></TD>
                    <TD colspan="2"  class=2-td2-center >
                         <DIV align=left>&nbsp;<bean:write name="dffgrxx" property="nsrlxmc" /></DIV></TD>
                        </TR>

            <TR>
              <TD class=2-td2-left >
                <DIV align=right>开户银行&nbsp; </DIV>
              </TD>
              <TD class=2-td2-left >
                <DIV align=left>
                  &nbsp;<bean:write name="dffgrxx" property="khyhmc" />
                </DIV></TD>
                <TD   class=2-td2-left >
                  <DIV align=right>银行账号&nbsp;</DIV></TD>
                  <TD colspan="2"  class=2-td2-center >
                    <DIV align=left>&nbsp;<bean:write name="dffgrxx" property="yhzh" /></DIV></TD>
                  </TR>


              <TR>
                <TD class=2-td2-left >
                  <DIV align=right>联系人姓名&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left >
                  <DIV align=left>
                    &nbsp;<bean:write name="dffgrxx" property="lxrxm" />
                  </DIV></TD>
                  <TD   class=2-td2-left >
                    <DIV align=right>联系电话&nbsp;</DIV></TD>
                    <TD colspan="2"  class=2-td2-center >
                      <DIV align=left>&nbsp;<bean:write name="dffgrxx" property="lxdh" /></DIV></TD>
                    </TR>
              <%
					}
				%>

              <TR>
                <TD class=2-td2-left rowspan = "7">
                  <DIV align=right>	交换土地房屋</DIV>
                  <DIV align=right>权属转移</DIV>

                  </TD>


                  <TD class=2-td2-left >
                    <DIV align=right>房地产项目名称&nbsp;</DIV></TD>
                    <TD colspan="4" class=2-td2-center style="word-break:break-all">
                      <DIV align=left>&nbsp;<bean:write name="dffwtdxx" property="fdcxmmc" /></DIV></TD>
                    </TR>
	            <TR>
	              <TD class=2-td2-left >
	                <DIV align=right>合约签订时间&nbsp; </DIV>
	              </TD>
	              <TD class=2-td2-left >
	                <DIV align=left>&nbsp;<%=DataConvert.TimeStamp2String(dffwtdxx.getHtqdsj())%> </DIV></TD>
	                <TD class=2-td2-left >
	                  <DIV align=right>分类&nbsp;</DIV></TD>
	                  <TD colspan="2"  class=2-td2-center >
	                    <DIV align=left>
	                      &nbsp;<bean:write  name="dffwtdxx" property="flmc"/>

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
                        &nbsp;<bean:write name="dffwtdxx" property="tdfwzldz" />
                      </DIV>
                    </DIV></TD>
                  </TR>
                  <TR>
                    <TD class=2-td2-left >
                      <DIV align=right>土地、房屋权属转移面积&nbsp; </DIV>
                    </TD>
                    <TD  class=2-td2-left >
                      <DIV align=left>土地：
                        &nbsp;<%=DataConvert.BigDecimal2String(dffwtdxx.getTdfwqszymj())%>
                        ㎡ </DIV></TD>
                        <TD class=2-td2-left >
                          <DIV align=right>房屋建筑面积&nbsp;</DIV></TD>
                          <TD colspan="2"  class=2-td2-center >
                            <DIV align=left>
                              &nbsp;<%=DataConvert.BigDecimal2String(dffwtdxx.getFwjzmj())%>
                              ㎡</DIV></TD>
                            </TR>
                <TR>
                  <TD class=2-td2-left rowspan = "2">
                    <DIV align=right>成交价格（评估价格）</DIV>
                    <TD  class=2-td2-left >
                      <DIV align=left>
                        &nbsp;<%=DataConvert.BigDecimal2String(dffwtdxx.getCjjgrmb())%>
                        元(人民币) </DIV></TD>
                        <TD class=2-td2-left >
                          <DIV align=right>税务机关核定价格&nbsp; </DIV></TD>
                          <TD  colspan="2"  class=2-td2-center >
                            <DIV align=left>
                              &nbsp;<%=DataConvert.BigDecimal2String(dffwtdxx.getPgjgrmb())%>
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
      </logic:iterate>




                  <BR>


			<BR>
</TBODY></TABLE>


            <BR>
			<BR>

	<table>


	<logic:equal name="plslForm2" property="back"  value="dr">
	<tr >
			<td align=center>
			<IMG alt=保存 height=22 id=baocun name=baocun
            onclick="doSubmitForm('Save')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
            src="<%=static_file%>images/baocun1.jpg" style="CURSOR: hand" width=79>
            <IMG alt=退出 height=22 id=tuichu name=tuichu
            onclick="doSubmitForm('Return')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
            src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand" width=79>
			</td>
	</tr>
	</logic:equal>

	<logic:equal name="plslForm2" property="back"  value="cx">
		<tr>
		<logic:equal name="plslForm2" property="del"  value="1">
			<td  class="black9" align=center>
                <input type="image" name="delete" value="删除" alt="删除"
                onclick = "javascript:return doSubmitForm('Delete');"
                onMouseOver="MM_swapImage('shanchu','','<%=static_file%>images/shanchu2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shanchu1.jpg"
                width="79" height="22" id="shanchu">
            </td>
		</logic:equal>

			<td align=center>
            <IMG alt=退出 height=22 id=tuichu name=tuichu
            onclick="doSubmitForm('Returncx')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
            src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand" width=79>
			</td>
		</tr>
	</logic:equal>


    </table>
			<BR>

</html:form>
<script language="javascript" type='text/JavaScript'>
var sfesf="<%=sfesf%>";
var setz="<%=setz%>";
var objesf=document.all.sfesf;
var objsetz=document.all.setz;
if(objesf.value==sfesf){
	objesf.checked=true;
}
for(i=0;i<objsetz.length;i++){
	if(objsetz[i].value==setz){
		objsetz[i].selected=true;
	}
}
</script>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
