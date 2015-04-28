<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@page import="com.creationstar.bjtax.qsgl.util.ActionUtil"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.SbxxForm"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb"%>


<%
    String hstatic_file = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
	SbxxForm sbxxForm = (SbxxForm)session.getAttribute("sbxxForm");

	if (sbxxForm==null) sbxxForm = (SbxxForm)request.getAttribute("sbxxForm");
    String inputStr = sbxxForm.getInputStr();
	//修改inputStr为null或者为""的问题
    if(inputStr==null || inputStr==""){
	SbxxForm form_sbxx = (SbxxForm)request.getAttribute("sbxxForm");
    inputStr = form_sbxx.getInputStr();
	}
%>


<HTML><HEAD><TITLE>打印申报表</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<META content="MSHTML 5.00.2919.6307" name=GENERATOR>
<SCRIPT language=JavaScript src="<%=hstatic_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<link href="../include/ImgStyle.css" rel="stylesheet" type="text/css">
</HEAD>
<%@ include file="../include/QRCodeHeader.jsp" %>

<script language=JavaScript type='text/JavaScript'>
//调用ie打印
function printSbxx(){
    var str = '<%=inputStr%>';
    //调用库中的方法
  var obj=new ActiveXObject("hyQRBarCode.QRCode");
  var fielName='<%=sbxxForm.getSbbh()%>';
  var rtn=obj.EnBarCodePic(str,QR_TEMP_DIR+"\\"+fielName);
  var src="";
  if(rtn=="-1"){
    alert("调用动态库出错,数据检查异常!");
  }else if(rtn=="-2"){
    alert("调用动态库出错,压缩异常!");
  }else if(rtn=="-3"){
    alert("调用动态库出错,生成图片异常!");
  }else if(rtn=="-4"){
    alert("调用动态库出错,其它异常!");
  }else{
    src=rtn;
    //alert(src);
    //增加判断是一个图片还是两个图片
    var index = src.indexOf(",");
    //如果等于-1，说明只有一个图片；否则有两个图片，最多只允许有两个图片
    if(index==-1){
        //var tmp="<div class='bodrimg' id='newPreview' style='display:inline;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src="+src+", sizingMethod=scale);' onClick=''></div>";
       //采用默认大小
        var tmp = "<img src="+src+">";
       // tmp = tmp +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="+src+">";
    }else{
        //采用默认大小
        var tmp = "<img src="+src+">";
        tmp = tmp +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="+src.substring(index+1)+">";
         //var tmp="<div class='bodrimg' id='newPreview' style='display:inline;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src="+src.substring(0,index)+", sizingMethod=scale);' onClick=''></div>";
         //tmp=tmp+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div class='bodrimg' id='newPreview' style='display:inline;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src="+src.substring(index+1)+", sizingMethod=scale);' onClick=''></div>";
    }
		  var obj=document.getElementById("picshow");
          obj.innerHTML=tmp;
          obj.style.display="block";
  }
        document.getElementById("bottom_sign").style.display="none";
        //调用打印
        window.print();
        //关闭窗口
        window.close();

}

</script>



<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">

<html:form action="/qssb/printSbxx.do">

<bean:define id="sbzb" name="sbxxForm" property="voSbzb" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb"/>

<%
  String lblNsrmc = "纳税人";
  String lblLx = "纳税人类型";
  if (sbzb.getBljmsbs().equals(Constants.ZB_BLJMSBS_BZ))
  {
     lblNsrmc = "申请人";
     lblLx = "申请人类型";
  }
  String grNsrmc=ActionUtil.getNsrmc(sbxxForm.getNsrList(),null);
 %>


<logic:equal name="sbzb" property="yhbs"  value="<%=Constants.ZB_YHBS_GR%>">
				<bean:define id="grxx" name="sbxxForm" property="voGrxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx"/>
</logic:equal>

<logic:notEqual name="sbzb" property="yhbs"  value="<%=Constants.ZB_YHBS_GR%>">
<bean:define id="fgrxx" name="sbxxForm" property="voFgrxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx"/>
</logic:notEqual>

<bean:define id="tufwxx" name="sbxxForm" property="voTufwxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"/>


<p align="left">
    <BR>

    <div id="picshow"></div></p>

<P ALIGN="RIGHT" ><font size="2">表号：<bean:write name="sbxxForm" property="printSbbh" /></font></P>
<p align="left">
    <DIV id="bottom_sign" style="display:block">
      <img alt=打印   id=Print name=Submit33
		onclick="printSbxx()" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('Print','','<%=hstatic_file%>images/q_dysbb2.jpg',1)"
		src="<%=hstatic_file%>images/q_dysqb1.jpg" style="CURSOR: hand">
        <IMG alt=退出 height=22 id=tuichu name=tuichu
		onclick="window.close();" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('tuichu','','<%=hstatic_file%>images/tuichu2.jpg',1)"
		src="<%=hstatic_file%>images/tuichu1.jpg" style="CURSOR: hand" width=79>
      </div></p>
<B>
<logic:equal name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">

				<logic:equal name="sbzb" property="yhbs"  value="<%=Constants.ZB_YHBS_GR%>">
						<P ALIGN="CENTER">北京市地方税务局不征契税信息采集表-个人</P>
				</logic:equal>

				<logic:notEqual name="sbzb" property="yhbs"  value="<%=Constants.ZB_YHBS_GR%>">
						<P ALIGN="CENTER">北京市地方税务局不征契税信息采集表-非个人</P>
				</logic:notEqual>

</logic:equal>


<logic:notEqual name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">

				<logic:equal name="sbzb" property="yhbs"  value="<%=Constants.ZB_YHBS_GR%>">
						<P ALIGN="CENTER">北京市地方税务局契税纳税申报表-个人</P>
				</logic:equal>

				<logic:notEqual name="sbzb" property="yhbs"  value="<%=Constants.ZB_YHBS_GR%>">
						<P ALIGN="CENTER">北京市地方税务局契税纳税申报表-非个人</P>
				</logic:notEqual>

</logic:notEqual>


								</B >

								<P ALIGN="CENTER"><font size="2">申报日期：
								<%=DataConvert.TimeStamp2String((Timestamp)sbzb.getSbrq())%>
<logic:notEqual name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">
								 缴税方式：
                <bean:write name="sbzb" property="jsfsmc" />&nbsp;
</logic:notEqual>
														</font></p>

								<TABLE BORDER=1 CELLSPACING=0 Align="center" CELLPADDING=5 WIDTH=707>


<!--个人-->
<logic:equal name="sbzb" property="yhbs"  value="<%=Constants.ZB_YHBS_GR%>">

<TR>
	<TD VALIGN="MIDDLE" width="10%" ROWSPAN="2">
		<div ALIGN="CENTER"><font size="2">个人填</font></div>
		<div ALIGN="CENTER"><font size="2">写部分</font></div>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2" width="20%">
		<font size="2"><%=lblNsrmc%></font>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2" width="25%">
		<font size="2"><%=grNsrmc%>&nbsp;</font>

<!--bean:write name="grxx" property="nsrmc" /-->
	</TD>
	<TD VALIGN="TOP" COLSPAN="2" width="20%">
	<div VALIGN="TOP"><font size="2">联系电话</font></div></TD>
	<TD  VALIGN="TOP" COLSPAN="2" width="25%">
		<font size="2"><bean:write name="grxx" property="lxdh" />&nbsp;</font>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="2">
		<font size="2">身份证件类型</font>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2">
		<font size="2"><bean:write name="grxx" property="sfzjlxmc" />&nbsp;</font>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2">
		<font size="2">身份证件号码</font>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2">
		<font size="2"><bean:write name="grxx" property="sfzjhm" />&nbsp;</font>
	</TD>

</TR>
</logic:equal>

<!--非个人-->
<logic:notEqual name="sbzb" property="yhbs"  value="<%=Constants.ZB_YHBS_GR%>">
<TR>
	<TD  VALIGN="MIDDLE" width="10%" ROWSPAN=4>
		<DIV ALIGN="CENTER"><font size="2">非个人</font></DIV>
		<DIV ALIGN="CENTER"><font size="2">填写</font></DIV>
		<DIV ALIGN="CENTER"><font size="2">部分</font></DIV>
	</TD>
	<TD VALIGN="TOP" width="15%" COLSPAN="2"><font size="2">税务计算机代码</font></TD>
	<TD VALIGN="TOP" width="30%" COLSPAN="2"><font size="2"><bean:write name="fgrxx" property="jsjdm" />&nbsp;</font></TD>
	<TD VALIGN="TOP" width="15%" COLSPAN="2"><font size="2"><%=lblLx%></font></TD>
	<TD VALIGN="TOP" width="30%" COLSPAN="2"><font size="2"><bean:write name="fgrxx" property="nsrlxmc" />&nbsp;</font></TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2"><%=lblNsrmc%></font></TD>
	<TD VALIGN="TOP" COLSPAN="6" style="word-break:break-all" COLSPAN="3">
		<font size="2"><bean:write name="fgrxx" property="nsrmc" />&nbsp;</font>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">开户银行</font></TD>
	<TD VALIGN="TOP" COLSPAN="2" style="word-break:break-all">
		<font size="2"><bean:write name="fgrxx" property="khyhmc" />&nbsp;</font>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">银行帐号</font></TD>
	<TD VALIGN="TOP" COLSPAN="2" style="word-break:break-all">
		<font size="2"><bean:write name="fgrxx" property="yhzh" />&nbsp;</font>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">联系人姓名</font></TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2"><bean:write name="fgrxx" property="lxrxm" />&nbsp;</font></TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">联系电话</font></TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2"><bean:write name="fgrxx" property="lxdh" />&nbsp;</font></TD>
</TR>
</logic:notEqual>

<!--土地房屋信息-->
<TR>
<logic:equal name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">
	<TD VALIGN="MIDDLE" ROWSPAN=6>
</logic:equal>

<logic:notEqual name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">
	<TD VALIGN="MIDDLE" ROWSPAN=8>
</logic:notEqual>

		<p ALIGN="CENTER"><font size="2">土地</font></p>
		<p ALIGN="CENTER"><font size="2">房屋</font></p>
		<p ALIGN="CENTER"><font size="2">权属</font></p>
		<p ALIGN="CENTER"><font size="2">转移</font></p>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">房地产项目名称</font></TD>
	<TD VALIGN="TOP" COLSPAN="6" style="word-break:break-all">
		<font size="2"><bean:write name="tufwxx" property="fdcxmmc" />&nbsp;</font>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="3"><font size="2">合同（契约）签订时间</font></TD>
	<TD VALIGN="TOP"><font size="2"> <%=DataConvert.TimeStamp2String((Timestamp)tufwxx.getHtqdsj())%>&nbsp;</font>　</TD>
<logic:equal name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">分类</font></TD>
</logic:equal>
<logic:notEqual name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">购房原因</font></TD>
</logic:notEqual>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2"> <bean:write name="tufwxx" property="flmc" />&nbsp;</font></TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="3"><font size="2">土地、房屋座落地址</font></TD>
	<TD VALIGN="TOP" COLSPAN="5" style="word-break:break-all"><font size="2"><bean:write name="tufwxx" property="tdfwzldz" />&nbsp;</font></TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="3"><font size="2">土地、房屋权属转移类型</font></TD>
	<TD VALIGN="TOP" nowrap="nowrap"><font size="2"><bean:write name="tufwxx" property="tdfwqszymc" />&nbsp;</font></TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">房屋类别</font></TD>
	<TD VALIGN="TOP" COLSPAN="2" nowrap="nowrap"><font size="2"><bean:write name="tufwxx" property="fwlxmc" />&nbsp;</font></TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="3"><font size="2">土地、房屋权属转移面积</font></TD>
	<TD VALIGN="TOP" COLSPAN="3" style="word-break:break-all">
		<font size="2">土地：<%=DataConvert.BigDecimal2String(tufwxx.getTdfwqszymj(),3)%>&nbsp;㎡</font>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2" style="word-break:break-all">
		<font size="2">房屋建筑面积：<%=DataConvert.BigDecimal2String(tufwxx.getFwjzmj(),3)%>&nbsp;㎡</font>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="3"><font size="2">容积率</font></TD>
	<TD VALIGN="TOP" nowrap="nowrap"><font size="2">
	<%
	if(tufwxx.getRjl()==null||tufwxx.getRjl().equals(""))
	{
	%>
	&nbsp;
	<%
	}
	else
	{

		if(tufwxx.getRjl().equals("00"))
		{
		%>
		1.0以下&nbsp;
		<%
		}
		if(tufwxx.getRjl().equals("01"))
		{
		%>
		1.0以上含1.0&nbsp;
		<%
		}
	}
	%>

	</font></TD>
<!--修改土地级次为房屋所在区域-->
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">所在区域</font></TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">
	<%
   if(tufwxx.getTdjc()==null||tufwxx.getTdjc().equals(""))
	{
	%>
	&nbsp;
	<%
	}
	else
	{
        if(tufwxx.getTdjc().equals("00"))
		{
		%>
		&nbsp;
		<%
		}
		if(tufwxx.getTdjc().equals("01"))
		{
		%>
		三环以内&nbsp;
		<%
		}
		if(tufwxx.getTdjc().equals("02"))
		{
		%>
		三环至四环之间&nbsp;
		<%
		}
		%>
		<%if(tufwxx.getTdjc().equals("03"))
		{
		%>
		四环至五环之间&nbsp;
		<%
		}
		if(tufwxx.getTdjc().equals("04"))
		{
		%>
		五环以外&nbsp;
		<%
		}
	     if(tufwxx.getTdjc().equals("11"))
	 	{	
	     %>
	    	四环内北部地区&nbsp;
	     <% 
	 	}
	     if(tufwxx.getTdjc().equals("12"))
	 	{	
	 	%>
	    	四环内南部地区&nbsp;
	     <% 
	 	}
	     if(tufwxx.getTdjc().equals("13"))
	 	{	
	 	%>
	    	四环至五环北部地区&nbsp;
	     <% 
	 	}
	      if(tufwxx.getTdjc().equals("14"))
	 	{	
	 	%>
	    	四环至五环南部地区&nbsp;
	     <% 
	 	}
	     if(tufwxx.getTdjc().equals("15"))
	 	{	
	 	%>
	    	五环至六环北部地区&nbsp;
	     <% 
	 	}
	     if(tufwxx.getTdjc().equals("16"))
	 	{	
	 	%>
	    	五环至六环南部地区&nbsp;
	     <% 
	 	}
	     if(tufwxx.getTdjc().equals("17"))
	 	{	
	 	%>
	    	六环外地区&nbsp;
	     <% 
		}
	    //由于2014年10月北京普通住房标准调整，故作此需改为新标准：每平米价格上限、每套房屋价格、所在区域三方面调整 ，
		//此处只修改所在区域显示。modified by gaoyh to 20141020
	    if(tufwxx.getTdjc().equals("21"))
		{	
		%>
	   	5环内&nbsp;
	    <% 
		}
	    if(tufwxx.getTdjc().equals("22"))
		{	
		%>
	   	5-6环&nbsp;
	    <% 
		}
	    if(tufwxx.getTdjc().equals("23"))
		{	
		%>
	   	6环外&nbsp;
	    <% 
		}
	}
	    %>
	</font></TD>
</TR>

<%-- 如果非不征情况才显示成交价格 --%>
<logic:notEqual name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">
<TR>
	<TD VALIGN="MIDDLE" ROWSPAN="2" width="10%">
		<DIV ALIGN="CENTER"><font size="2">成交价格</font></DIV>
		<DIV ALIGN="CENTER"><font size="2">（评估价格）</font></DIV>
	</TD>
	<TD VALIGN="TOP" COLSPAN="3">
		<DIV ALIGN="left" style="word-break:break-all">
			<font size="2"><%=DataConvert.BigDecimal2String((BigDecimal)tufwxx.getCjjgrmb())%>&nbsp;元（人民币）</FONT>
		</DIV>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">税务机关核定价格</font></TD>
	<TD VALIGN="TOP" COLSPAN="2">
		<DIV ALIGN="left" style="word-break:break-all">
			<font size="2"><%=DataConvert.BigDecimal2String((BigDecimal)tufwxx.getPgjgrmb())%>&nbsp;元（人民币）</font>
		</DIV>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" style="word-break:break-all" COLSPAN="2">
		<font size="2"><%=DataConvert.BigDecimal2String((BigDecimal)tufwxx.getCjjgwb())%>&nbsp;元（外币）</font>
	</TD>
	<TD VALIGN="TOP" width="5%">
		<div ALIGN="RIGHT"><font size="2">币种</font></div>
	</TD>
	<TD VALIGN="TOP"><font size="2"><bean:write name="tufwxx" property="bzmc" />&nbsp;</font></TD>
	<TD VALIGN="TOP"><font size="2">汇率</font></TD>
	<TD VALIGN="TOP"><font size="2"><%=DataConvert.BigDecimal2String((BigDecimal)tufwxx.getHldm(),5)%>&nbsp;</font></TD>
	<TD VALIGN="TOP" style="word-break:break-all">
		<font size="2">折合:<%=DataConvert.BigDecimal2String((BigDecimal)tufwxx.getZhjgrmb())%>元（人民币）&nbsp;</font>
	</TD>
</TR>
</logic:notEqual>

<!--拆迁信息-->
<logic:notEqual name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">

<logic:iterate id="data" indexId="index" length="length" name="sbxxForm" property="cqList"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq">
<TR>
	<TD VALIGN="MIDDLE" ROWSPAN="4">
		<p ALIGN="CENTER"><font size="2">拆迁</font></p>
		<p ALIGN="CENTER"><font size="2">情况</FONT></p>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">被拆迁房屋坐落地址</font></TD>
	<TD VALIGN="TOP" COLSPAN="6" style="word-break:break-all">
		<font size="2"><bean:write name="data" property="zldz" />&nbsp;</font>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">拆迁协议号码</font></TD>
	<TD VALIGN="TOP" COLSPAN="6"><font size="2"><bean:write name="data" property="cqxyh" />&nbsp;</font></TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">拆迁补偿额</font></TD>
	<TD VALIGN="TOP" COLSPAN="6">
		<DIV ALIGN="left" style="word-break:break-all">
			<font size="2"><%=DataConvert.BigDecimal2String((BigDecimal)data.getCqbce())%>&nbsp;元（人民币）</font>
		</DIV>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">本次使用补偿额</font></TD>
	<TD VALIGN="TOP" COLSPAN="6">
		<DIV ALIGN="left" style="word-break:break-all">
			<font size="2"><%=DataConvert.BigDecimal2String((BigDecimal)data.getBcsybce())%>&nbsp;元</font>
		</DIV>
	</TD>
	<!--TD VALIGN="TOP" COLSPAN="2"><font size="2">拆迁补偿剩余额</font></TD>
	<TD VALIGN="TOP" COLSPAN="2">
		<DIV ALIGN="left" style="word-break:break-all">
			<font size="2"><%=DataConvert.BigDecimal2String((BigDecimal)data.getCqbcsye())%>&nbsp;元</font>
		</DIV>
	</TD-->
</TR>
</logic:iterate>
</logic:notEqual>



<!--已购公有住房-->

<logic:notEqual name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">
<logic:iterate id="data1" indexId="index" length="length" name="sbxxForm" property="gyzfList"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf">
<TR>
	<TD VALIGN="MIDDLE" ROWSPAN="6">
		<p ALIGN="CENTER"><font size="2">已购公</font></p>
		<p ALIGN="CENTER"><font size="2">有住房</font></p>
		<p ALIGN="CENTER"><font size="2">/经济适</font></p>
		<p ALIGN="CENTER"><font size="2">用住房</font></p>
		<p ALIGN="CENTER"><font size="2">上市出</font></p>
		<p ALIGN="CENTER"><font size="2">售情况</font></p>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">出售合同号码</font></TD>
	<TD VALIGN="TOP" COLSPAN="6"><font size="2"> <bean:write name="data1" property="yggyzfqszsh" />&nbsp;</font></TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">房屋权属证书号</font></TD>
	<TD VALIGN="TOP" COLSPAN="6" style="word-break:break-all">
		<font size="2"><bean:write name="data1" property="fwqszsh" />&nbsp;</font>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">座落地址</font></TD>
	<TD VALIGN="TOP" COLSPAN="6" style="word-break:break-all">
		<font size="2"><bean:write name="data1" property="zldz" />&nbsp;</font>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="3"><font size="2">出售合同（契约）签订时间</font></TD>
	<TD VALIGN="TOP" COLSPAN="5">
		<font size="2"><%=DataConvert.TimeStamp2String((Timestamp)data1.getQdsj())%>&nbsp;</font>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">建筑面积</font></TD>
	<TD VALIGN="TOP" COLSPAN="2">
		<DIV ALIGN="left" style="word-break:break-all">
			<font size="2"> <%=DataConvert.BigDecimal2String((BigDecimal)data1.getJzmj())%>&nbsp;㎡</font>
		</DIV>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">成交价格</font></TD>
	<TD VALIGN="TOP" COLSPAN="2">
		<DIV ALIGN="left" style="word-break:break-all">
			<font size="2"><%=DataConvert.BigDecimal2String((BigDecimal)data1.getCjjg())%>&nbsp;元（人民币）</font>
		</DIV>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="2">
		<DIV ALIGN="left"><font size="2">本次抵扣额</font></DIV>
	</TD>
	<TD VALIGN="TOP" COLSPAN="6">
		<DIV ALIGN="left" style="word-break:break-all"><font size="2"><%=DataConvert.BigDecimal2String((BigDecimal)data1.getBcdke())%>&nbsp;元</font></TD>
	<!--TD VALIGN="TOP" COLSPAN="2"><font size="2">剩余额</font></TD>
	<TD VALIGN="TOP" COLSPAN="2">
		<DIV ALIGN="left" style="word-break:break-all">
			<font size="2"><%=DataConvert.BigDecimal2String((BigDecimal)data1.getSye())%>&nbsp;元</font>
		</DIV>
	</TD-->
</TR>
</logic:iterate>
</logic:notEqual>


<logic:notEqual name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">

<!--房屋交换信息-->

<logic:equal name="sbxxForm" property="addedFwjhxx" value="true">
  <bean:define id="fwjhBo" name="sbxxForm" property="voFwjh" type="com.creationstar.bjtax.qsgl.model.bo.FwjhxxBo"/>

  <logic:equal name="fwjhBo" property="jhperson"  value="0">
    <bean:define id="dfgrxx" name="sbxxForm" property="voFwjh.voZcqrxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx"/>
  </logic:equal>
  <logic:equal name="fwjhBo" property="jhperson"  value="1">
    <bean:define id="dffgrxx" name="sbxxForm" property="voFwjh.fgrxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx"/>
  </logic:equal>

  <bean:define id="dffwtdxx" name="sbxxForm" property="voFwjh.tufwxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"/>


  <!--TR>
    <TD VALIGN="TOP" >对方缴款方式</TD>
    <TD VALIGN="TOP" COLSPAN=6 > <DIV align=left>
      <bean:write name="fwjhBo" property="jkfsmc" />&nbsp;</td>
      <TD VALIGN="TOP" COLSPAN=9>对方房屋土地管理部门受理号</TD>
      <TD colspan="7" VALIGN="TOP">
      <DIV align=left><bean:write name="fwjhBo" property="fcjslh" />&nbsp;
      </DIV></TD>
    </TR-->


    <!--交换个人信息显示-->
    <logic:equal name="fwjhBo" property="jhperson"  value="0">
      <TR>
        <TD VALIGN="MIDDLE" rowspan="3">
	        <P ALIGN="CENTER"><font size="2">对方个</font></P>
	        <P ALIGN="CENTER"><font size="2">人填写</font></P>
	        <P ALIGN="CENTER"><font size="2">部分</font></P>
        </TD>
        <TD VALIGN="TOP" COLSPAN="2">
        	<DIV align=right><font size="2">纳税人名称&nbsp;</font></DIV>
        </TD>
          <DIV align=right>&nbsp;</DIV>
        <TD VALIGN="TOP" COLSPAN="2">
        	<DIV align=left style="word-break:break-all">
        		<font size="2">
        		<%=ActionUtil.getNsrmc(fwjhBo.getNsrList(),null)%>
        		&nbsp</font>
        	</DIV>
        </TD>
        <TD VALIGN="TOP" COLSPAN="2">
        	<DIV align=right><font size="2">联系电话&nbsp;</font> </DIV></TD>
        <TD VALIGN="TOP" COLSPAN="2">
          <DIV align=left><font size="2"><bean:write name="dfgrxx" property="lxdh" />&nbsp;</font> </DIV>
        </TD>
      </TR>

      <TR>
        <TD VALIGN="TOP" COLSPAN="2">
          <DIV align=right><font size="2">身份证件类型&nbsp;</font> </DIV>
        </TD>
        <TD VALIGN="TOP" COLSPAN="2">
          <DIV align=left>
            <font size="2"><bean:write name="dfgrxx" property="sfzjlxmc" />&nbsp;</font>
          </DIV>
        </TD>
        <TD VALIGN="TOP" COLSPAN="2">
        	<DIV align=right><font size="2">身份证件号码&nbsp;</font></DIV></TD>
        <TD VALIGN="TOP" COLSPAN="2">
          <DIV align=left><font size="2"><bean:write name="dfgrxx" property="sfzjhm" />&nbsp;</font></DIV>
        </TD>
      </TR>

    <TR>
      <TD VALIGN="TOP" COLSPAN="2">
        <DIV align=right><font size="2">国籍&nbsp;</font></DIV>
      </TD>
      <TD VALIGN="TOP" COLSPAN="6">
        <DIV align=left>
         <font size="2"><bean:write name="dfgrxx" property="gjmc" />&nbsp;</font>
        </DIV>
      </TD>
    </TR>
</logic:equal>

<!--交换非个人信息显示-->

<logic:equal name="fwjhBo" property="jhperson"  value="1">
	  <TR>
	    <TD VALIGN="MIDDLE"  rowspan = "4">
		    <P ALIGN="CENTER"><font size="2">对方非</font></P>
		    <P ALIGN="CENTER"><font size="2">个人填</font></P>
		    <P ALIGN="CENTER"><font size="2">写部分</font></P>
			</TD>
		  <TD VALIGN="TOP"  COLSPAN="2">
		    <DIV align=left><font size="2">计算机代码&nbsp;</font></DIV>
		  </TD>
		  <TD VALIGN="TOP" COLSPAN="6">
		    <DIV align=left><font size="2"> <bean:write name="dffgrxx" property="jsjdm" />&nbsp</font></DIV>
		  </TD>
	  </TR>

	  <TR>
	    <TD VALIGN="TOP" COLSPAN="2">
	      <DIV align=left><font size="2">纳税人名称&nbsp;</font></DIV>
	    </TD>
	    <TD VALIGN="TOP" COLSPAN="2">
	      <DIV align=left style="word-break:break-all">
	        <font size="2"><bean:write name="dffgrxx" property="nsrmc" />&nbsp;</font>
	      </DIV>
	    </TD>
	    <TD VALIGN="TOP" COLSPAN="2">
	    	<DIV align=left><font size="2">纳税人类型&nbsp;</font></DIV>
	    </TD>
	    <TD VALIGN="TOP" COLSPAN="2">
	    	<DIV align=left style="word-break:break-all">
	    		<font size="2"><bean:write name="dffgrxx" property="nsrlxmc" />&nbsp;</font>
	    	</DIV>
	    </TD>
	  </TR>

		<TR>
		  <TD VALIGN="TOP" COLSPAN="2">
		    <DIV align="left"><font size="2">开户银行&nbsp;</font> </DIV>
		  </TD>
		  <TD VALIGN="TOP" COLSPAN="2">
		    <DIV align="left" style="word-break:break-all">
		      <font size="2"><bean:write name="dffgrxx" property="khyhmc" />&nbsp;</font>
		    </DIV>
		  </TD>
		  <TD VALIGN="TOP" COLSPAN="2" style="word-break:break-all">
		    <DIV align="left"><font size="2">银行账号&nbsp;</font></DIV></TD>
		  <TD VALIGN="TOP" COLSPAN="2">
		  	<DIV align="left" style="word-break:break-all">
		  		<font size="2"><bean:write name="dffgrxx" property="yhzh"/>&nbsp;</font>
		  	</DIV>
		  </TD>
		</TR>

	  <TR>
	    <TD VALIGN="TOP" COLSPAN="2">
	      <DIV align=left><font size="2">联系人姓名&nbsp;</font> </DIV>
	    </TD>
	    <TD VALIGN="TOP" COLSPAN="2">
	      <DIV align=left>
	        <font size="2"><bean:write name="dffgrxx" property="lxrxm" />&nbsp;</font>
	      </DIV>
	    </TD>
	    <TD VALIGN="TOP" COLSPAN="2">
	    	<DIV align=left><font size="2">联系电话&nbsp;</font></DIV>
	    </TD>
	    <TD VALIGN="TOP" COLSPAN="2">
	    	<DIV align=left><font size="2"><bean:write name="dffgrxx" property="lxdh"/>&nbsp</font></DIV>
	    </TD>
		</TR>
  </logic:equal>

	  <TR>
	    <TD VALIGN="MIDDLE"  rowspan = "7">
		    <P ALIGN="CENTER"><font size="2">交换</font></P>
		    <P ALIGN="CENTER"><font size="2">土地</font></P>
		    <P ALIGN="CENTER"><font size="2">房屋</font></P>
		    <P ALIGN="CENTER"><font size="2">权属</font></P>
		    <P ALIGN="CENTER"><font size="2">转移</font></P>
			</TD>
	    <TD VALIGN="TOP" COLSPAN="2">
	    	<DIV align="left"><font size="2">房地产项目名称&nbsp;</font></DIV></TD>
	    <TD VALIGN="TOP" COLSPAN="6">
	    	<DIV align="left" style="word-break:break-all">
	    		<font size="2"><bean:write name="dffwtdxx" property="fdcxmmc" />&nbsp;</font>
	    	</DIV>
	    </TD>
	  </TR>

  <TR>
    <TD VALIGN="TOP" COLSPAN="2">
      <DIV align=left><font size="2">合约签订时间&nbsp;</font></DIV>
    </TD>
    <TD VALIGN="TOP" COLSPAN="2">
      <DIV align=left><font size="2"><%=DataConvert.TimeStamp2String(dffwtdxx.getHtqdsj())%> &nbsp;</font></DIV>
    </TD>
    <TD VALIGN="TOP" COLSPAN="2">
      <DIV align=left><font size="2">分类&nbsp;</font></DIV></TD>
    <TD VALIGN="TOP" COLSPAN="2">
      <DIV align=left>
        <font size="2"><bean:write name="dffwtdxx" property="flmc"/>&nbsp;</font>
      </DIV>
    </TD>
  </TR>

  <!--TR>
    <TD class=2-td2-left width="15%";>
      <DIV align=right>土地、房屋权属转移类型&nbsp; </DIV> </TD>
      <TD class=2-td2-left width="19%">
        <DIV align=left>

          <bean:write name="dffwtdxx" property="tdfwqszymc" />
        </DIV></TD>
        <TD class=2-td2-left width="19%">
          <DIV align=right>房屋类别&nbsp;</DIV></TD>
          <TD colspan="2"  class=2-td2-center width="33%">
            <DIV align=left>

              <bean:write name="dffwtdxx" property="fwlxmc" />
            </DIV></TD>
          </TR-->
      <TR>
        <TD COLSPAN="3">
          <DIV align=left><font size="2">土地、房屋座落地址&nbsp;</font></DIV>
        </TD>
        <TD colspan="5">
          <DIV align=left style="word-break:break-all">
            <font size="2"><bean:write name="dffwtdxx" property="tdfwzldz" />&nbsp;</font>
          </DIV>
        </TD>
      </TR>

      <TR>
        <TD VALIGN="TOP" COLSPAN="3">
          <DIV align=left><font size="2">土地、房屋权属转移面积&nbsp;</font></DIV>
        </TD>
        <TD VALIGN="TOP" COLSPAN="3">
          <DIV align=left style="word-break:break-all">
          	<font size="2">土地：<%=DataConvert.BigDecimal2String(dffwtdxx.getTdfwqszymj())%>㎡ &nbsp;</font>
          </DIV>
        </TD>
        <TD VALIGN="TOP" COLSPAN="2">
              <DIV align=left style="word-break:break-all"><font size="2">房屋建筑面积&nbsp;
                  <%=DataConvert.BigDecimal2String(dffwtdxx.getFwjzmj())%>
                  ㎡&nbsp;</font></DIV>
        </TD>
      </TR>

  <TR>
    <TD VALIGN="TOP" COLSPAN="2">
      <DIV align=left><font size="2">容积率&nbsp;</font></DIV>
    </TD>
    <TD VALIGN="TOP" COLSPAN="2">
      <DIV align=left><font size="2">
	  	<%if(dffwtdxx.getRjl().equals("00"))
	{
	%>
	1.0以下&nbsp;
	<%
	}
	if(dffwtdxx.getRjl().equals("01"))
	{
	%>
	1.0以上含1.0&nbsp;
	<%
	}
	%>

	  &nbsp;</font></DIV>
    </TD>
    <TD VALIGN="TOP" COLSPAN="2">
        <!--修改土地级次为所属区域-->
      <DIV align=left><font size="2">所属区域&nbsp;</font></DIV></TD>
    <TD VALIGN="TOP" COLSPAN="2">
      <DIV align=left>
        <font size="2">
      <%if(dffwtdxx.getTdjc().equals("00"))
	{
	%>
	&nbsp;
	<%
	}
    if(dffwtdxx.getTdjc().equals("01"))
	{
	%>
	三环以内&nbsp;
	<%
	}
	if(dffwtdxx.getTdjc().equals("02"))
	{
	%>
	三环至四环之间&nbsp;
	<%
	}
	%>
	<%if(dffwtdxx.getTdjc().equals("03"))
	{
	%>
	四环至五环之间&nbsp;
	<%
	}
	if(dffwtdxx.getTdjc().equals("04"))
	{
	%>
	五环以外&nbsp;
	<%
	}
    if(dffwtdxx.getTdjc().equals("11"))
	{	
    %>
   	四环内北部地区&nbsp;
    <% 
	}
    if(dffwtdxx.getTdjc().equals("12"))
	{	
	%>
   	四环内南部地区&nbsp;
    <% 
	}
    if(dffwtdxx.getTdjc().equals("13"))
	{	
	%>
   	四环至五环北部地区&nbsp;
    <% 
	}
     if(dffwtdxx.getTdjc().equals("14"))
	{	
	%>
   	四环至五环南部地区&nbsp;
    <% 
	}
    if(dffwtdxx.getTdjc().equals("15"))
	{	
	%>
   	五环至六环北部地区&nbsp;
    <% 
	}
    if(dffwtdxx.getTdjc().equals("16"))
	{	
	%>
   	五环至六环南部地区&nbsp;
    <% 
	}
    if(dffwtdxx.getTdjc().equals("17"))
	{	
	%>
   	六环外地区&nbsp;
    <% 
	}
    //由于2014年10月北京普通住房标准调整，故作此需改为新标准：每平米价格上限、每套房屋价格、所在区域三方面调整 ，
	//此处只修改所在区域显示。modified by gaoyh to 20141020
    if(dffwtdxx.getTdjc().equals("21"))
	{	
	%>
   	5环内&nbsp;
    <% 
	}
    if(dffwtdxx.getTdjc().equals("22"))
	{	
	%>
   	5-6环&nbsp;
    <% 
	}
    if(dffwtdxx.getTdjc().equals("23"))
	{	
	%>
   	6环外&nbsp;
    <% 
	}
    %>


		&nbsp;</font>
      </DIV>
    </TD>
  </TR>


    <TR>
      <TD VALIGN="MIDDLE" rowspan="2">
        <P ALIGN="CENTER"><font size="2">成交价格</font></P>
        <P ALIGN="CENTER" ><font size="2">（评估价格）</FONT></p>
      </TD>

      <TD VALIGN="TOP" align="left" COLSPAN="3" style="word-break:break-all">
      	<DIV align="left" style="word-break:break-all">
      		<font size="2"><%=DataConvert.BigDecimal2String(dffwtdxx.getCjjgrmb())%>元(人民币)</font>
      	</DIV>
      </TD>
      <TD VALIGN="TOP" COLSPAN="2">
      	<DIV align=left><font size="2">税务机关核定价格&nbsp;</font> </DIV>
      </TD>
      <TD VALIGN="TOP" colspan="2" style="word-break:break-all">
      	<DIV align="left" style="word-break:break-all"><font size="2"><%=DataConvert.BigDecimal2String(dffwtdxx.getPgjgrmb())%>
              元(人民币)</font> </DIV>
      </TD>
    </TR>

    <TR>
      <TD VALIGN="TOP" COLSPAN="2">
        <DIV align=left style="word-break:break-all"><font size="2"><%=DataConvert.BigDecimal2String(dffwtdxx.getCjjgwb())%>
          元(外币)</font>
        </DIV>
      </TD>

          <TD VALIGN="TOP">
            <DIV align=left >
              <font size="2">币种</font>
            </DIV>
            </TD>
            <TD VALIGN="TOP"><font size="2"><bean:write name="dffwtdxx" property="bzmc" />&nbsp;</font>
            </TD>
            <TD VALIGN="TOP">
            <DIV align=left>
              <font size="2">汇率
               </font></DIV>
            </td>
            <TD  VALIGN="TOP">
             <DIV align=left>
              <font size="2"><%=DataConvert.BigDecimal2String(dffwtdxx.getHldm(),5)%>
               </font></DIV>
             </td>
              <TD VALIGN="TOP">
                <DIV align=left style="word-break:break-all">
                  <font size="2">折合:<%=DataConvert.BigDecimal2String(dffwtdxx.getZhjgrmb())%>
                  元(人民币)</font> </DIV></TD>
                </TR>
</logic:equal>

</logic:notEqual>

  <TR>
    <TD vAlign=top colSpan=10><B><FONT size=2>以下由税务机关人员填写：</FONT></B></TD>
    </TR>

<bean:define id="jghd" name="sbxxForm" property="voJghdxx" type="com.creationstar.bjtax.qsgl.model.bo.JghdsjBo"/>
<!--logic:notEqual name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>"-->

  <TR>
    <TD vAlign=top colSpan=1><FONT size=2>计税依据</FONT></TD>
    <TD vAlign=top colSpan=3>
      <DIV style="WORD-BREAK: break-all" align=left><FONT
      size=2><%=DataConvert.BigDecimal2String(jghd.getJsyj())%>&nbsp;元</FONT> </DIV></TD>
    <TD vAlign=top ><FONT size=2>税率</FONT></TD>
    <TD vAlign=top >
      <DIV style="WORD-BREAK: break-all" align=left>
      <FONT  size=2>&nbsp;<%=DataConvert.doubleFormatPercent(jghd.getSl().doubleValue(),0,100)%> %</FONT> </DIV></TD>
    <TD vAlign=top colSpan=1><FONT size=2>计征税额</FONT></TD>
    <TD vAlign=top colSpan=3>
      <DIV style="WORD-BREAK: break-all" align=left><FONT
      size=2><%=DataConvert.BigDecimal2String(jghd.getJzse())%>&nbsp;元</FONT> </DIV></TD>

      </TR>

  <TR>
      <!--修改增加经济适用房减税金额字段begin-->
    <TD vAlign=top colSpan=1><FONT size=2>拆迁减免金额</FONT></TD>
    <TD vAlign=top colSpan=2>
      <DIV style="WORD-BREAK: break-all" align=left><FONT
      size=2><%=DataConvert.BigDecimal2String(jghd.getCqjmje())%>&nbsp;元</FONT> </DIV></TD>
    <TD vAlign=top colSpan=1><FONT size=2>普通住宅减税金额</FONT></TD>
    <TD vAlign=top colSpan=2>
      <DIV style="WORD-BREAK: break-all" align=left><FONT
      size=2><%=DataConvert.BigDecimal2String(jghd.getPtzzjmje())%>&nbsp;元</FONT> </DIV></TD>
 <TD vAlign=top colSpan=1><FONT size=2>经济适用房减税金额</FONT></TD>
    <TD vAlign=top colSpan=2>
      <DIV style="WORD-BREAK: break-all" align=left><FONT
      size=2><%=DataConvert.BigDecimal2String(jghd.getJjsyfjmje())%>&nbsp;元</FONT> </DIV></TD>
      <!--修改增加经济适用房减税金额字段end-->
      </TR>



<!--/logic:notEqual-->

<bean:define id="spjgxx" name="sbxxForm" property="voSpjgxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx"/>
<logic:notEqual name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">
<TR>
	<TD VALIGN="MIDDLE" ROWSPAN="2">
		<DIV ALIGN="CENTER"><font size="2">税务机</font></DIV>
		<DIV ALIGN="CENTER"><font size="2">关审核</font></DIV>
		<DIV ALIGN="CENTER"><font size="2">减免税</font></DIV>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">减免税文书字号</font></TD>
	<TD VALIGN="TOP" COLSPAN="6">
		<DIV ALIGN="Left" style="word-break:break-all"><font size="2">
			<bean:write name="spjgxx" property="hdtzszh"/>&nbsp;</font>
		</DIV>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">减免税金额</TD>
	<TD VALIGN="TOP" COLSPAN="6"><font size="2"><%=DataConvert.BigDecimal2String(spjgxx.getJmsje())%>元（人民币）</font></TD>
</TR>
</logic:notEqual>

   <TR>
    <TD vAlign=center align=middle><FONT size=2>应征税额</FONT></TD>
    <TD vAlign=top colSpan=8><DIV style="WORD-BREAK: break-all" align=left><FONT
      size=2><%=DataConvert.BigDecimal2String(jghd.getYnse())%>&nbsp;元</FONT> </DIV></TD></TR>

<TR>
	<TD VALIGN="MIDDLE" align="center"><font size="2">备注</FONT></TD>
	<TD VALIGN="TOP" COLSPAN="8"><font size="2">　<bean:write name="sbzb" property="bz" />&nbsp;</font></TD>
</TR>

<%
  String lblSqr = "纳税人（代理人）签章";
  String lblSqr1 = "法人代表签章";
  if (sbzb.getBljmsbs().equals(Constants.ZB_BLJMSBS_BZ))
  {
     lblSqr = "申请人签章";
     lblSqr1 = "审核人签章及日期";
  }
%>
<TR>
	<TD VALIGN="MIDDLE" COLSPAN="5" HEIGHT=50><font size="2"><%=lblSqr%></font></TD>
<!--TD WIDTH="49%" VALIGN="MIDDLE" COLSPAN=12 HEIGHT=39>法人代表签章</TD-->
	<TD VALIGN="MIDDLE" COLSPAN="4" HEIGHT=50><font size="2"><%=lblSqr1%></font></TD>
</TR>

</TABLE>

</html:form>
</BODY>
</HTML>
