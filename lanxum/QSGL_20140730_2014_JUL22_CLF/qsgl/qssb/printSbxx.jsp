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
	//�޸�inputStrΪnull����Ϊ""������
    if(inputStr==null || inputStr==""){
	SbxxForm form_sbxx = (SbxxForm)request.getAttribute("sbxxForm");
    inputStr = form_sbxx.getInputStr();
	}
%>


<HTML><HEAD><TITLE>��ӡ�걨��</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<META content="MSHTML 5.00.2919.6307" name=GENERATOR>
<SCRIPT language=JavaScript src="<%=hstatic_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<link href="../include/ImgStyle.css" rel="stylesheet" type="text/css">
</HEAD>
<%@ include file="../include/QRCodeHeader.jsp" %>

<script language=JavaScript type='text/JavaScript'>
//����ie��ӡ
function printSbxx(){
    var str = '<%=inputStr%>';
    //���ÿ��еķ���
  var obj=new ActiveXObject("hyQRBarCode.QRCode");
  var fielName='<%=sbxxForm.getSbbh()%>';
  var rtn=obj.EnBarCodePic(str,QR_TEMP_DIR+"\\"+fielName);
  var src="";
  if(rtn=="-1"){
    alert("���ö�̬�����,���ݼ���쳣!");
  }else if(rtn=="-2"){
    alert("���ö�̬�����,ѹ���쳣!");
  }else if(rtn=="-3"){
    alert("���ö�̬�����,����ͼƬ�쳣!");
  }else if(rtn=="-4"){
    alert("���ö�̬�����,�����쳣!");
  }else{
    src=rtn;
    //alert(src);
    //�����ж���һ��ͼƬ��������ͼƬ
    var index = src.indexOf(",");
    //�������-1��˵��ֻ��һ��ͼƬ������������ͼƬ�����ֻ����������ͼƬ
    if(index==-1){
        //var tmp="<div class='bodrimg' id='newPreview' style='display:inline;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src="+src+", sizingMethod=scale);' onClick=''></div>";
       //����Ĭ�ϴ�С
        var tmp = "<img src="+src+">";
       // tmp = tmp +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="+src+">";
    }else{
        //����Ĭ�ϴ�С
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
        //���ô�ӡ
        window.print();
        //�رմ���
        window.close();

}

</script>



<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">

<html:form action="/qssb/printSbxx.do">

<bean:define id="sbzb" name="sbxxForm" property="voSbzb" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb"/>

<%
  String lblNsrmc = "��˰��";
  String lblLx = "��˰������";
  if (sbzb.getBljmsbs().equals(Constants.ZB_BLJMSBS_BZ))
  {
     lblNsrmc = "������";
     lblLx = "����������";
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

<P ALIGN="RIGHT" ><font size="2">��ţ�<bean:write name="sbxxForm" property="printSbbh" /></font></P>
<p align="left">
    <DIV id="bottom_sign" style="display:block">
      <img alt=��ӡ   id=Print name=Submit33
		onclick="printSbxx()" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('Print','','<%=hstatic_file%>images/q_dysbb2.jpg',1)"
		src="<%=hstatic_file%>images/q_dysqb1.jpg" style="CURSOR: hand">
        <IMG alt=�˳� height=22 id=tuichu name=tuichu
		onclick="window.close();" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('tuichu','','<%=hstatic_file%>images/tuichu2.jpg',1)"
		src="<%=hstatic_file%>images/tuichu1.jpg" style="CURSOR: hand" width=79>
      </div></p>
<B>
<logic:equal name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">

				<logic:equal name="sbzb" property="yhbs"  value="<%=Constants.ZB_YHBS_GR%>">
						<P ALIGN="CENTER">�����еط�˰��ֲ�����˰��Ϣ�ɼ���-����</P>
				</logic:equal>

				<logic:notEqual name="sbzb" property="yhbs"  value="<%=Constants.ZB_YHBS_GR%>">
						<P ALIGN="CENTER">�����еط�˰��ֲ�����˰��Ϣ�ɼ���-�Ǹ���</P>
				</logic:notEqual>

</logic:equal>


<logic:notEqual name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">

				<logic:equal name="sbzb" property="yhbs"  value="<%=Constants.ZB_YHBS_GR%>">
						<P ALIGN="CENTER">�����еط�˰�����˰��˰�걨��-����</P>
				</logic:equal>

				<logic:notEqual name="sbzb" property="yhbs"  value="<%=Constants.ZB_YHBS_GR%>">
						<P ALIGN="CENTER">�����еط�˰�����˰��˰�걨��-�Ǹ���</P>
				</logic:notEqual>

</logic:notEqual>


								</B >

								<P ALIGN="CENTER"><font size="2">�걨���ڣ�
								<%=DataConvert.TimeStamp2String((Timestamp)sbzb.getSbrq())%>
<logic:notEqual name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">
								 ��˰��ʽ��
                <bean:write name="sbzb" property="jsfsmc" />&nbsp;
</logic:notEqual>
														</font></p>

								<TABLE BORDER=1 CELLSPACING=0 Align="center" CELLPADDING=5 WIDTH=707>


<!--����-->
<logic:equal name="sbzb" property="yhbs"  value="<%=Constants.ZB_YHBS_GR%>">

<TR>
	<TD VALIGN="MIDDLE" width="10%" ROWSPAN="2">
		<div ALIGN="CENTER"><font size="2">������</font></div>
		<div ALIGN="CENTER"><font size="2">д����</font></div>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2" width="20%">
		<font size="2"><%=lblNsrmc%></font>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2" width="25%">
		<font size="2"><%=grNsrmc%>&nbsp;</font>

<!--bean:write name="grxx" property="nsrmc" /-->
	</TD>
	<TD VALIGN="TOP" COLSPAN="2" width="20%">
	<div VALIGN="TOP"><font size="2">��ϵ�绰</font></div></TD>
	<TD  VALIGN="TOP" COLSPAN="2" width="25%">
		<font size="2"><bean:write name="grxx" property="lxdh" />&nbsp;</font>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="2">
		<font size="2">���֤������</font>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2">
		<font size="2"><bean:write name="grxx" property="sfzjlxmc" />&nbsp;</font>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2">
		<font size="2">���֤������</font>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2">
		<font size="2"><bean:write name="grxx" property="sfzjhm" />&nbsp;</font>
	</TD>

</TR>
</logic:equal>

<!--�Ǹ���-->
<logic:notEqual name="sbzb" property="yhbs"  value="<%=Constants.ZB_YHBS_GR%>">
<TR>
	<TD  VALIGN="MIDDLE" width="10%" ROWSPAN=4>
		<DIV ALIGN="CENTER"><font size="2">�Ǹ���</font></DIV>
		<DIV ALIGN="CENTER"><font size="2">��д</font></DIV>
		<DIV ALIGN="CENTER"><font size="2">����</font></DIV>
	</TD>
	<TD VALIGN="TOP" width="15%" COLSPAN="2"><font size="2">˰����������</font></TD>
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
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">��������</font></TD>
	<TD VALIGN="TOP" COLSPAN="2" style="word-break:break-all">
		<font size="2"><bean:write name="fgrxx" property="khyhmc" />&nbsp;</font>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">�����ʺ�</font></TD>
	<TD VALIGN="TOP" COLSPAN="2" style="word-break:break-all">
		<font size="2"><bean:write name="fgrxx" property="yhzh" />&nbsp;</font>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">��ϵ������</font></TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2"><bean:write name="fgrxx" property="lxrxm" />&nbsp;</font></TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">��ϵ�绰</font></TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2"><bean:write name="fgrxx" property="lxdh" />&nbsp;</font></TD>
</TR>
</logic:notEqual>

<!--���ط�����Ϣ-->
<TR>
<logic:equal name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">
	<TD VALIGN="MIDDLE" ROWSPAN=6>
</logic:equal>

<logic:notEqual name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">
	<TD VALIGN="MIDDLE" ROWSPAN=8>
</logic:notEqual>

		<p ALIGN="CENTER"><font size="2">����</font></p>
		<p ALIGN="CENTER"><font size="2">����</font></p>
		<p ALIGN="CENTER"><font size="2">Ȩ��</font></p>
		<p ALIGN="CENTER"><font size="2">ת��</font></p>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">���ز���Ŀ����</font></TD>
	<TD VALIGN="TOP" COLSPAN="6" style="word-break:break-all">
		<font size="2"><bean:write name="tufwxx" property="fdcxmmc" />&nbsp;</font>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="3"><font size="2">��ͬ����Լ��ǩ��ʱ��</font></TD>
	<TD VALIGN="TOP"><font size="2"> <%=DataConvert.TimeStamp2String((Timestamp)tufwxx.getHtqdsj())%>&nbsp;</font>��</TD>
<logic:equal name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">����</font></TD>
</logic:equal>
<logic:notEqual name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">����ԭ��</font></TD>
</logic:notEqual>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2"> <bean:write name="tufwxx" property="flmc" />&nbsp;</font></TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="3"><font size="2">���ء����������ַ</font></TD>
	<TD VALIGN="TOP" COLSPAN="5" style="word-break:break-all"><font size="2"><bean:write name="tufwxx" property="tdfwzldz" />&nbsp;</font></TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="3"><font size="2">���ء�����Ȩ��ת������</font></TD>
	<TD VALIGN="TOP" nowrap="nowrap"><font size="2"><bean:write name="tufwxx" property="tdfwqszymc" />&nbsp;</font></TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">�������</font></TD>
	<TD VALIGN="TOP" COLSPAN="2" nowrap="nowrap"><font size="2"><bean:write name="tufwxx" property="fwlxmc" />&nbsp;</font></TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="3"><font size="2">���ء�����Ȩ��ת�����</font></TD>
	<TD VALIGN="TOP" COLSPAN="3" style="word-break:break-all">
		<font size="2">���أ�<%=DataConvert.BigDecimal2String(tufwxx.getTdfwqszymj(),3)%>&nbsp;�O</font>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2" style="word-break:break-all">
		<font size="2">���ݽ��������<%=DataConvert.BigDecimal2String(tufwxx.getFwjzmj(),3)%>&nbsp;�O</font>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="3"><font size="2">�ݻ���</font></TD>
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
		1.0����&nbsp;
		<%
		}
		if(tufwxx.getRjl().equals("01"))
		{
		%>
		1.0���Ϻ�1.0&nbsp;
		<%
		}
	}
	%>

	</font></TD>
<!--�޸����ؼ���Ϊ������������-->
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">��������</font></TD>
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
		��������&nbsp;
		<%
		}
		if(tufwxx.getTdjc().equals("02"))
		{
		%>
		�������Ļ�֮��&nbsp;
		<%
		}
		%>
		<%if(tufwxx.getTdjc().equals("03"))
		{
		%>
		�Ļ����廷֮��&nbsp;
		<%
		}
		if(tufwxx.getTdjc().equals("04"))
		{
		%>
		�廷����&nbsp;
		<%
		}
	     if(tufwxx.getTdjc().equals("11"))
	 	{	
	     %>
	    	�Ļ��ڱ�������&nbsp;
	     <% 
	 	}
	     if(tufwxx.getTdjc().equals("12"))
	 	{	
	 	%>
	    	�Ļ����ϲ�����&nbsp;
	     <% 
	 	}
	     if(tufwxx.getTdjc().equals("13"))
	 	{	
	 	%>
	    	�Ļ����廷��������&nbsp;
	     <% 
	 	}
	      if(tufwxx.getTdjc().equals("14"))
	 	{	
	 	%>
	    	�Ļ����廷�ϲ�����&nbsp;
	     <% 
	 	}
	     if(tufwxx.getTdjc().equals("15"))
	 	{	
	 	%>
	    	�廷��������������&nbsp;
	     <% 
	 	}
	     if(tufwxx.getTdjc().equals("16"))
	 	{	
	 	%>
	    	�廷�������ϲ�����&nbsp;
	     <% 
	 	}
	     if(tufwxx.getTdjc().equals("17"))
	 	{	
	 	%>
	    	���������&nbsp;
	     <% 
		}
	    //����2014��10�±�����ͨס����׼���������������Ϊ�±�׼��ÿƽ�׼۸����ޡ�ÿ�׷��ݼ۸������������������ ��
		//�˴�ֻ�޸�����������ʾ��modified by gaoyh to 20141020
	    if(tufwxx.getTdjc().equals("21"))
		{	
		%>
	   	5����&nbsp;
	    <% 
		}
	    if(tufwxx.getTdjc().equals("22"))
		{	
		%>
	   	5-6��&nbsp;
	    <% 
		}
	    if(tufwxx.getTdjc().equals("23"))
		{	
		%>
	   	6����&nbsp;
	    <% 
		}
	}
	    %>
	</font></TD>
</TR>

<%-- ����ǲ����������ʾ�ɽ��۸� --%>
<logic:notEqual name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">
<TR>
	<TD VALIGN="MIDDLE" ROWSPAN="2" width="10%">
		<DIV ALIGN="CENTER"><font size="2">�ɽ��۸�</font></DIV>
		<DIV ALIGN="CENTER"><font size="2">�������۸�</font></DIV>
	</TD>
	<TD VALIGN="TOP" COLSPAN="3">
		<DIV ALIGN="left" style="word-break:break-all">
			<font size="2"><%=DataConvert.BigDecimal2String((BigDecimal)tufwxx.getCjjgrmb())%>&nbsp;Ԫ������ң�</FONT>
		</DIV>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">˰����غ˶��۸�</font></TD>
	<TD VALIGN="TOP" COLSPAN="2">
		<DIV ALIGN="left" style="word-break:break-all">
			<font size="2"><%=DataConvert.BigDecimal2String((BigDecimal)tufwxx.getPgjgrmb())%>&nbsp;Ԫ������ң�</font>
		</DIV>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" style="word-break:break-all" COLSPAN="2">
		<font size="2"><%=DataConvert.BigDecimal2String((BigDecimal)tufwxx.getCjjgwb())%>&nbsp;Ԫ����ң�</font>
	</TD>
	<TD VALIGN="TOP" width="5%">
		<div ALIGN="RIGHT"><font size="2">����</font></div>
	</TD>
	<TD VALIGN="TOP"><font size="2"><bean:write name="tufwxx" property="bzmc" />&nbsp;</font></TD>
	<TD VALIGN="TOP"><font size="2">����</font></TD>
	<TD VALIGN="TOP"><font size="2"><%=DataConvert.BigDecimal2String((BigDecimal)tufwxx.getHldm(),5)%>&nbsp;</font></TD>
	<TD VALIGN="TOP" style="word-break:break-all">
		<font size="2">�ۺ�:<%=DataConvert.BigDecimal2String((BigDecimal)tufwxx.getZhjgrmb())%>Ԫ������ң�&nbsp;</font>
	</TD>
</TR>
</logic:notEqual>

<!--��Ǩ��Ϣ-->
<logic:notEqual name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">

<logic:iterate id="data" indexId="index" length="length" name="sbxxForm" property="cqList"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq">
<TR>
	<TD VALIGN="MIDDLE" ROWSPAN="4">
		<p ALIGN="CENTER"><font size="2">��Ǩ</font></p>
		<p ALIGN="CENTER"><font size="2">���</FONT></p>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">����Ǩ���������ַ</font></TD>
	<TD VALIGN="TOP" COLSPAN="6" style="word-break:break-all">
		<font size="2"><bean:write name="data" property="zldz" />&nbsp;</font>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">��ǨЭ�����</font></TD>
	<TD VALIGN="TOP" COLSPAN="6"><font size="2"><bean:write name="data" property="cqxyh" />&nbsp;</font></TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">��Ǩ������</font></TD>
	<TD VALIGN="TOP" COLSPAN="6">
		<DIV ALIGN="left" style="word-break:break-all">
			<font size="2"><%=DataConvert.BigDecimal2String((BigDecimal)data.getCqbce())%>&nbsp;Ԫ������ң�</font>
		</DIV>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">����ʹ�ò�����</font></TD>
	<TD VALIGN="TOP" COLSPAN="6">
		<DIV ALIGN="left" style="word-break:break-all">
			<font size="2"><%=DataConvert.BigDecimal2String((BigDecimal)data.getBcsybce())%>&nbsp;Ԫ</font>
		</DIV>
	</TD>
	<!--TD VALIGN="TOP" COLSPAN="2"><font size="2">��Ǩ����ʣ���</font></TD>
	<TD VALIGN="TOP" COLSPAN="2">
		<DIV ALIGN="left" style="word-break:break-all">
			<font size="2"><%=DataConvert.BigDecimal2String((BigDecimal)data.getCqbcsye())%>&nbsp;Ԫ</font>
		</DIV>
	</TD-->
</TR>
</logic:iterate>
</logic:notEqual>



<!--�ѹ�����ס��-->

<logic:notEqual name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">
<logic:iterate id="data1" indexId="index" length="length" name="sbxxForm" property="gyzfList"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf">
<TR>
	<TD VALIGN="MIDDLE" ROWSPAN="6">
		<p ALIGN="CENTER"><font size="2">�ѹ���</font></p>
		<p ALIGN="CENTER"><font size="2">��ס��</font></p>
		<p ALIGN="CENTER"><font size="2">/������</font></p>
		<p ALIGN="CENTER"><font size="2">��ס��</font></p>
		<p ALIGN="CENTER"><font size="2">���г�</font></p>
		<p ALIGN="CENTER"><font size="2">�����</font></p>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">���ۺ�ͬ����</font></TD>
	<TD VALIGN="TOP" COLSPAN="6"><font size="2"> <bean:write name="data1" property="yggyzfqszsh" />&nbsp;</font></TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">����Ȩ��֤���</font></TD>
	<TD VALIGN="TOP" COLSPAN="6" style="word-break:break-all">
		<font size="2"><bean:write name="data1" property="fwqszsh" />&nbsp;</font>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">�����ַ</font></TD>
	<TD VALIGN="TOP" COLSPAN="6" style="word-break:break-all">
		<font size="2"><bean:write name="data1" property="zldz" />&nbsp;</font>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="3"><font size="2">���ۺ�ͬ����Լ��ǩ��ʱ��</font></TD>
	<TD VALIGN="TOP" COLSPAN="5">
		<font size="2"><%=DataConvert.TimeStamp2String((Timestamp)data1.getQdsj())%>&nbsp;</font>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">�������</font></TD>
	<TD VALIGN="TOP" COLSPAN="2">
		<DIV ALIGN="left" style="word-break:break-all">
			<font size="2"> <%=DataConvert.BigDecimal2String((BigDecimal)data1.getJzmj())%>&nbsp;�O</font>
		</DIV>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">�ɽ��۸�</font></TD>
	<TD VALIGN="TOP" COLSPAN="2">
		<DIV ALIGN="left" style="word-break:break-all">
			<font size="2"><%=DataConvert.BigDecimal2String((BigDecimal)data1.getCjjg())%>&nbsp;Ԫ������ң�</font>
		</DIV>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="2">
		<DIV ALIGN="left"><font size="2">���εֿ۶�</font></DIV>
	</TD>
	<TD VALIGN="TOP" COLSPAN="6">
		<DIV ALIGN="left" style="word-break:break-all"><font size="2"><%=DataConvert.BigDecimal2String((BigDecimal)data1.getBcdke())%>&nbsp;Ԫ</font></TD>
	<!--TD VALIGN="TOP" COLSPAN="2"><font size="2">ʣ���</font></TD>
	<TD VALIGN="TOP" COLSPAN="2">
		<DIV ALIGN="left" style="word-break:break-all">
			<font size="2"><%=DataConvert.BigDecimal2String((BigDecimal)data1.getSye())%>&nbsp;Ԫ</font>
		</DIV>
	</TD-->
</TR>
</logic:iterate>
</logic:notEqual>


<logic:notEqual name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">

<!--���ݽ�����Ϣ-->

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
    <TD VALIGN="TOP" >�Է��ɿʽ</TD>
    <TD VALIGN="TOP" COLSPAN=6 > <DIV align=left>
      <bean:write name="fwjhBo" property="jkfsmc" />&nbsp;</td>
      <TD VALIGN="TOP" COLSPAN=9>�Է��������ع����������</TD>
      <TD colspan="7" VALIGN="TOP">
      <DIV align=left><bean:write name="fwjhBo" property="fcjslh" />&nbsp;
      </DIV></TD>
    </TR-->


    <!--����������Ϣ��ʾ-->
    <logic:equal name="fwjhBo" property="jhperson"  value="0">
      <TR>
        <TD VALIGN="MIDDLE" rowspan="3">
	        <P ALIGN="CENTER"><font size="2">�Է���</font></P>
	        <P ALIGN="CENTER"><font size="2">����д</font></P>
	        <P ALIGN="CENTER"><font size="2">����</font></P>
        </TD>
        <TD VALIGN="TOP" COLSPAN="2">
        	<DIV align=right><font size="2">��˰������&nbsp;</font></DIV>
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
        	<DIV align=right><font size="2">��ϵ�绰&nbsp;</font> </DIV></TD>
        <TD VALIGN="TOP" COLSPAN="2">
          <DIV align=left><font size="2"><bean:write name="dfgrxx" property="lxdh" />&nbsp;</font> </DIV>
        </TD>
      </TR>

      <TR>
        <TD VALIGN="TOP" COLSPAN="2">
          <DIV align=right><font size="2">���֤������&nbsp;</font> </DIV>
        </TD>
        <TD VALIGN="TOP" COLSPAN="2">
          <DIV align=left>
            <font size="2"><bean:write name="dfgrxx" property="sfzjlxmc" />&nbsp;</font>
          </DIV>
        </TD>
        <TD VALIGN="TOP" COLSPAN="2">
        	<DIV align=right><font size="2">���֤������&nbsp;</font></DIV></TD>
        <TD VALIGN="TOP" COLSPAN="2">
          <DIV align=left><font size="2"><bean:write name="dfgrxx" property="sfzjhm" />&nbsp;</font></DIV>
        </TD>
      </TR>

    <TR>
      <TD VALIGN="TOP" COLSPAN="2">
        <DIV align=right><font size="2">����&nbsp;</font></DIV>
      </TD>
      <TD VALIGN="TOP" COLSPAN="6">
        <DIV align=left>
         <font size="2"><bean:write name="dfgrxx" property="gjmc" />&nbsp;</font>
        </DIV>
      </TD>
    </TR>
</logic:equal>

<!--�����Ǹ�����Ϣ��ʾ-->

<logic:equal name="fwjhBo" property="jhperson"  value="1">
	  <TR>
	    <TD VALIGN="MIDDLE"  rowspan = "4">
		    <P ALIGN="CENTER"><font size="2">�Է���</font></P>
		    <P ALIGN="CENTER"><font size="2">������</font></P>
		    <P ALIGN="CENTER"><font size="2">д����</font></P>
			</TD>
		  <TD VALIGN="TOP"  COLSPAN="2">
		    <DIV align=left><font size="2">���������&nbsp;</font></DIV>
		  </TD>
		  <TD VALIGN="TOP" COLSPAN="6">
		    <DIV align=left><font size="2"> <bean:write name="dffgrxx" property="jsjdm" />&nbsp</font></DIV>
		  </TD>
	  </TR>

	  <TR>
	    <TD VALIGN="TOP" COLSPAN="2">
	      <DIV align=left><font size="2">��˰������&nbsp;</font></DIV>
	    </TD>
	    <TD VALIGN="TOP" COLSPAN="2">
	      <DIV align=left style="word-break:break-all">
	        <font size="2"><bean:write name="dffgrxx" property="nsrmc" />&nbsp;</font>
	      </DIV>
	    </TD>
	    <TD VALIGN="TOP" COLSPAN="2">
	    	<DIV align=left><font size="2">��˰������&nbsp;</font></DIV>
	    </TD>
	    <TD VALIGN="TOP" COLSPAN="2">
	    	<DIV align=left style="word-break:break-all">
	    		<font size="2"><bean:write name="dffgrxx" property="nsrlxmc" />&nbsp;</font>
	    	</DIV>
	    </TD>
	  </TR>

		<TR>
		  <TD VALIGN="TOP" COLSPAN="2">
		    <DIV align="left"><font size="2">��������&nbsp;</font> </DIV>
		  </TD>
		  <TD VALIGN="TOP" COLSPAN="2">
		    <DIV align="left" style="word-break:break-all">
		      <font size="2"><bean:write name="dffgrxx" property="khyhmc" />&nbsp;</font>
		    </DIV>
		  </TD>
		  <TD VALIGN="TOP" COLSPAN="2" style="word-break:break-all">
		    <DIV align="left"><font size="2">�����˺�&nbsp;</font></DIV></TD>
		  <TD VALIGN="TOP" COLSPAN="2">
		  	<DIV align="left" style="word-break:break-all">
		  		<font size="2"><bean:write name="dffgrxx" property="yhzh"/>&nbsp;</font>
		  	</DIV>
		  </TD>
		</TR>

	  <TR>
	    <TD VALIGN="TOP" COLSPAN="2">
	      <DIV align=left><font size="2">��ϵ������&nbsp;</font> </DIV>
	    </TD>
	    <TD VALIGN="TOP" COLSPAN="2">
	      <DIV align=left>
	        <font size="2"><bean:write name="dffgrxx" property="lxrxm" />&nbsp;</font>
	      </DIV>
	    </TD>
	    <TD VALIGN="TOP" COLSPAN="2">
	    	<DIV align=left><font size="2">��ϵ�绰&nbsp;</font></DIV>
	    </TD>
	    <TD VALIGN="TOP" COLSPAN="2">
	    	<DIV align=left><font size="2"><bean:write name="dffgrxx" property="lxdh"/>&nbsp</font></DIV>
	    </TD>
		</TR>
  </logic:equal>

	  <TR>
	    <TD VALIGN="MIDDLE"  rowspan = "7">
		    <P ALIGN="CENTER"><font size="2">����</font></P>
		    <P ALIGN="CENTER"><font size="2">����</font></P>
		    <P ALIGN="CENTER"><font size="2">����</font></P>
		    <P ALIGN="CENTER"><font size="2">Ȩ��</font></P>
		    <P ALIGN="CENTER"><font size="2">ת��</font></P>
			</TD>
	    <TD VALIGN="TOP" COLSPAN="2">
	    	<DIV align="left"><font size="2">���ز���Ŀ����&nbsp;</font></DIV></TD>
	    <TD VALIGN="TOP" COLSPAN="6">
	    	<DIV align="left" style="word-break:break-all">
	    		<font size="2"><bean:write name="dffwtdxx" property="fdcxmmc" />&nbsp;</font>
	    	</DIV>
	    </TD>
	  </TR>

  <TR>
    <TD VALIGN="TOP" COLSPAN="2">
      <DIV align=left><font size="2">��Լǩ��ʱ��&nbsp;</font></DIV>
    </TD>
    <TD VALIGN="TOP" COLSPAN="2">
      <DIV align=left><font size="2"><%=DataConvert.TimeStamp2String(dffwtdxx.getHtqdsj())%> &nbsp;</font></DIV>
    </TD>
    <TD VALIGN="TOP" COLSPAN="2">
      <DIV align=left><font size="2">����&nbsp;</font></DIV></TD>
    <TD VALIGN="TOP" COLSPAN="2">
      <DIV align=left>
        <font size="2"><bean:write name="dffwtdxx" property="flmc"/>&nbsp;</font>
      </DIV>
    </TD>
  </TR>

  <!--TR>
    <TD class=2-td2-left width="15%";>
      <DIV align=right>���ء�����Ȩ��ת������&nbsp; </DIV> </TD>
      <TD class=2-td2-left width="19%">
        <DIV align=left>

          <bean:write name="dffwtdxx" property="tdfwqszymc" />
        </DIV></TD>
        <TD class=2-td2-left width="19%">
          <DIV align=right>�������&nbsp;</DIV></TD>
          <TD colspan="2"  class=2-td2-center width="33%">
            <DIV align=left>

              <bean:write name="dffwtdxx" property="fwlxmc" />
            </DIV></TD>
          </TR-->
      <TR>
        <TD COLSPAN="3">
          <DIV align=left><font size="2">���ء����������ַ&nbsp;</font></DIV>
        </TD>
        <TD colspan="5">
          <DIV align=left style="word-break:break-all">
            <font size="2"><bean:write name="dffwtdxx" property="tdfwzldz" />&nbsp;</font>
          </DIV>
        </TD>
      </TR>

      <TR>
        <TD VALIGN="TOP" COLSPAN="3">
          <DIV align=left><font size="2">���ء�����Ȩ��ת�����&nbsp;</font></DIV>
        </TD>
        <TD VALIGN="TOP" COLSPAN="3">
          <DIV align=left style="word-break:break-all">
          	<font size="2">���أ�<%=DataConvert.BigDecimal2String(dffwtdxx.getTdfwqszymj())%>�O &nbsp;</font>
          </DIV>
        </TD>
        <TD VALIGN="TOP" COLSPAN="2">
              <DIV align=left style="word-break:break-all"><font size="2">���ݽ������&nbsp;
                  <%=DataConvert.BigDecimal2String(dffwtdxx.getFwjzmj())%>
                  �O&nbsp;</font></DIV>
        </TD>
      </TR>

  <TR>
    <TD VALIGN="TOP" COLSPAN="2">
      <DIV align=left><font size="2">�ݻ���&nbsp;</font></DIV>
    </TD>
    <TD VALIGN="TOP" COLSPAN="2">
      <DIV align=left><font size="2">
	  	<%if(dffwtdxx.getRjl().equals("00"))
	{
	%>
	1.0����&nbsp;
	<%
	}
	if(dffwtdxx.getRjl().equals("01"))
	{
	%>
	1.0���Ϻ�1.0&nbsp;
	<%
	}
	%>

	  &nbsp;</font></DIV>
    </TD>
    <TD VALIGN="TOP" COLSPAN="2">
        <!--�޸����ؼ���Ϊ��������-->
      <DIV align=left><font size="2">��������&nbsp;</font></DIV></TD>
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
	��������&nbsp;
	<%
	}
	if(dffwtdxx.getTdjc().equals("02"))
	{
	%>
	�������Ļ�֮��&nbsp;
	<%
	}
	%>
	<%if(dffwtdxx.getTdjc().equals("03"))
	{
	%>
	�Ļ����廷֮��&nbsp;
	<%
	}
	if(dffwtdxx.getTdjc().equals("04"))
	{
	%>
	�廷����&nbsp;
	<%
	}
    if(dffwtdxx.getTdjc().equals("11"))
	{	
    %>
   	�Ļ��ڱ�������&nbsp;
    <% 
	}
    if(dffwtdxx.getTdjc().equals("12"))
	{	
	%>
   	�Ļ����ϲ�����&nbsp;
    <% 
	}
    if(dffwtdxx.getTdjc().equals("13"))
	{	
	%>
   	�Ļ����廷��������&nbsp;
    <% 
	}
     if(dffwtdxx.getTdjc().equals("14"))
	{	
	%>
   	�Ļ����廷�ϲ�����&nbsp;
    <% 
	}
    if(dffwtdxx.getTdjc().equals("15"))
	{	
	%>
   	�廷��������������&nbsp;
    <% 
	}
    if(dffwtdxx.getTdjc().equals("16"))
	{	
	%>
   	�廷�������ϲ�����&nbsp;
    <% 
	}
    if(dffwtdxx.getTdjc().equals("17"))
	{	
	%>
   	���������&nbsp;
    <% 
	}
    //����2014��10�±�����ͨס����׼���������������Ϊ�±�׼��ÿƽ�׼۸����ޡ�ÿ�׷��ݼ۸������������������ ��
	//�˴�ֻ�޸�����������ʾ��modified by gaoyh to 20141020
    if(dffwtdxx.getTdjc().equals("21"))
	{	
	%>
   	5����&nbsp;
    <% 
	}
    if(dffwtdxx.getTdjc().equals("22"))
	{	
	%>
   	5-6��&nbsp;
    <% 
	}
    if(dffwtdxx.getTdjc().equals("23"))
	{	
	%>
   	6����&nbsp;
    <% 
	}
    %>


		&nbsp;</font>
      </DIV>
    </TD>
  </TR>


    <TR>
      <TD VALIGN="MIDDLE" rowspan="2">
        <P ALIGN="CENTER"><font size="2">�ɽ��۸�</font></P>
        <P ALIGN="CENTER" ><font size="2">�������۸�</FONT></p>
      </TD>

      <TD VALIGN="TOP" align="left" COLSPAN="3" style="word-break:break-all">
      	<DIV align="left" style="word-break:break-all">
      		<font size="2"><%=DataConvert.BigDecimal2String(dffwtdxx.getCjjgrmb())%>Ԫ(�����)</font>
      	</DIV>
      </TD>
      <TD VALIGN="TOP" COLSPAN="2">
      	<DIV align=left><font size="2">˰����غ˶��۸�&nbsp;</font> </DIV>
      </TD>
      <TD VALIGN="TOP" colspan="2" style="word-break:break-all">
      	<DIV align="left" style="word-break:break-all"><font size="2"><%=DataConvert.BigDecimal2String(dffwtdxx.getPgjgrmb())%>
              Ԫ(�����)</font> </DIV>
      </TD>
    </TR>

    <TR>
      <TD VALIGN="TOP" COLSPAN="2">
        <DIV align=left style="word-break:break-all"><font size="2"><%=DataConvert.BigDecimal2String(dffwtdxx.getCjjgwb())%>
          Ԫ(���)</font>
        </DIV>
      </TD>

          <TD VALIGN="TOP">
            <DIV align=left >
              <font size="2">����</font>
            </DIV>
            </TD>
            <TD VALIGN="TOP"><font size="2"><bean:write name="dffwtdxx" property="bzmc" />&nbsp;</font>
            </TD>
            <TD VALIGN="TOP">
            <DIV align=left>
              <font size="2">����
               </font></DIV>
            </td>
            <TD  VALIGN="TOP">
             <DIV align=left>
              <font size="2"><%=DataConvert.BigDecimal2String(dffwtdxx.getHldm(),5)%>
               </font></DIV>
             </td>
              <TD VALIGN="TOP">
                <DIV align=left style="word-break:break-all">
                  <font size="2">�ۺ�:<%=DataConvert.BigDecimal2String(dffwtdxx.getZhjgrmb())%>
                  Ԫ(�����)</font> </DIV></TD>
                </TR>
</logic:equal>

</logic:notEqual>

  <TR>
    <TD vAlign=top colSpan=10><B><FONT size=2>������˰�������Ա��д��</FONT></B></TD>
    </TR>

<bean:define id="jghd" name="sbxxForm" property="voJghdxx" type="com.creationstar.bjtax.qsgl.model.bo.JghdsjBo"/>
<!--logic:notEqual name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>"-->

  <TR>
    <TD vAlign=top colSpan=1><FONT size=2>��˰����</FONT></TD>
    <TD vAlign=top colSpan=3>
      <DIV style="WORD-BREAK: break-all" align=left><FONT
      size=2><%=DataConvert.BigDecimal2String(jghd.getJsyj())%>&nbsp;Ԫ</FONT> </DIV></TD>
    <TD vAlign=top ><FONT size=2>˰��</FONT></TD>
    <TD vAlign=top >
      <DIV style="WORD-BREAK: break-all" align=left>
      <FONT  size=2>&nbsp;<%=DataConvert.doubleFormatPercent(jghd.getSl().doubleValue(),0,100)%> %</FONT> </DIV></TD>
    <TD vAlign=top colSpan=1><FONT size=2>����˰��</FONT></TD>
    <TD vAlign=top colSpan=3>
      <DIV style="WORD-BREAK: break-all" align=left><FONT
      size=2><%=DataConvert.BigDecimal2String(jghd.getJzse())%>&nbsp;Ԫ</FONT> </DIV></TD>

      </TR>

  <TR>
      <!--�޸����Ӿ������÷���˰����ֶ�begin-->
    <TD vAlign=top colSpan=1><FONT size=2>��Ǩ������</FONT></TD>
    <TD vAlign=top colSpan=2>
      <DIV style="WORD-BREAK: break-all" align=left><FONT
      size=2><%=DataConvert.BigDecimal2String(jghd.getCqjmje())%>&nbsp;Ԫ</FONT> </DIV></TD>
    <TD vAlign=top colSpan=1><FONT size=2>��ͨסլ��˰���</FONT></TD>
    <TD vAlign=top colSpan=2>
      <DIV style="WORD-BREAK: break-all" align=left><FONT
      size=2><%=DataConvert.BigDecimal2String(jghd.getPtzzjmje())%>&nbsp;Ԫ</FONT> </DIV></TD>
 <TD vAlign=top colSpan=1><FONT size=2>�������÷���˰���</FONT></TD>
    <TD vAlign=top colSpan=2>
      <DIV style="WORD-BREAK: break-all" align=left><FONT
      size=2><%=DataConvert.BigDecimal2String(jghd.getJjsyfjmje())%>&nbsp;Ԫ</FONT> </DIV></TD>
      <!--�޸����Ӿ������÷���˰����ֶ�end-->
      </TR>



<!--/logic:notEqual-->

<bean:define id="spjgxx" name="sbxxForm" property="voSpjgxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx"/>
<logic:notEqual name="sbzb" property="bljmsbs"  value="<%=Constants.ZB_BLJMSBS_BZ%>">
<TR>
	<TD VALIGN="MIDDLE" ROWSPAN="2">
		<DIV ALIGN="CENTER"><font size="2">˰���</font></DIV>
		<DIV ALIGN="CENTER"><font size="2">�����</font></DIV>
		<DIV ALIGN="CENTER"><font size="2">����˰</font></DIV>
	</TD>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">����˰�����ֺ�</font></TD>
	<TD VALIGN="TOP" COLSPAN="6">
		<DIV ALIGN="Left" style="word-break:break-all"><font size="2">
			<bean:write name="spjgxx" property="hdtzszh"/>&nbsp;</font>
		</DIV>
	</TD>
</TR>

<TR>
	<TD VALIGN="TOP" COLSPAN="2"><font size="2">����˰���</TD>
	<TD VALIGN="TOP" COLSPAN="6"><font size="2"><%=DataConvert.BigDecimal2String(spjgxx.getJmsje())%>Ԫ������ң�</font></TD>
</TR>
</logic:notEqual>

   <TR>
    <TD vAlign=center align=middle><FONT size=2>Ӧ��˰��</FONT></TD>
    <TD vAlign=top colSpan=8><DIV style="WORD-BREAK: break-all" align=left><FONT
      size=2><%=DataConvert.BigDecimal2String(jghd.getYnse())%>&nbsp;Ԫ</FONT> </DIV></TD></TR>

<TR>
	<TD VALIGN="MIDDLE" align="center"><font size="2">��ע</FONT></TD>
	<TD VALIGN="TOP" COLSPAN="8"><font size="2">��<bean:write name="sbzb" property="bz" />&nbsp;</font></TD>
</TR>

<%
  String lblSqr = "��˰�ˣ������ˣ�ǩ��";
  String lblSqr1 = "���˴���ǩ��";
  if (sbzb.getBljmsbs().equals(Constants.ZB_BLJMSBS_BZ))
  {
     lblSqr = "������ǩ��";
     lblSqr1 = "�����ǩ�¼�����";
  }
%>
<TR>
	<TD VALIGN="MIDDLE" COLSPAN="5" HEIGHT=50><font size="2"><%=lblSqr%></font></TD>
<!--TD WIDTH="49%" VALIGN="MIDDLE" COLSPAN=12 HEIGHT=39>���˴���ǩ��</TD-->
	<TD VALIGN="MIDDLE" COLSPAN="4" HEIGHT=50><font size="2"><%=lblSqr1%></font></TD>
</TR>

</TABLE>

</html:form>
</BODY>
</HTML>
