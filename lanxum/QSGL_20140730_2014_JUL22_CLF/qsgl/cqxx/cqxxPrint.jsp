<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="java.util.*"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants" %>
<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.CqxxbForm" %>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.*" %>
<html xmlns:o="urn:schemas-microsoft-com:office:office"
xmlns:w="urn:schemas-microsoft-com:office:word"
xmlns="http://www.w3.org/TR/REC-html40">

<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<meta name=ProgId content=Word.Document>
<meta name=Generator content="Microsoft Word 11">
<meta name=Originator content="Microsoft Word 11">
<link rel=File-List href="cqxxPrint.files/filelist.xml">
<%
CqxxbForm form = (CqxxbForm)session.getAttribute("cqxxbForm");
Cqxxb cqxx = form.getCqxx();
%>


<title>拆 迁 情 况 表</title>
<!--[if gte mso 9]><xml>
 <o:DocumentProperties>
  <o:Author>bjlt</o:Author>
  <o:LastAuthor>wuyz</o:LastAuthor>
  <o:Revision>2</o:Revision>
  <o:TotalTime>15</o:TotalTime>
  <o:Created>2006-04-27T08:28:00Z</o:Created>
  <o:LastSaved>2006-04-27T08:28:00Z</o:LastSaved>
  <o:Pages>1</o:Pages>
  <o:Words>26</o:Words>
  <o:Characters>149</o:Characters>
  <o:Company>bjlt</o:Company>
  <o:Lines>1</o:Lines>
  <o:Paragraphs>1</o:Paragraphs>
  <o:CharactersWithSpaces>174</o:CharactersWithSpaces>
  <o:Version>11.6568</o:Version>
 </o:DocumentProperties>
</xml><![endif]--><!--[if gte mso 9]><xml>
 <w:WordDocument>
  <w:SpellingState>Clean</w:SpellingState>
  <w:GrammarState>Clean</w:GrammarState>
  <w:PunctuationKerning/>
  <w:DrawingGridVerticalSpacing>7.8 磅</w:DrawingGridVerticalSpacing>
  <w:DisplayHorizontalDrawingGridEvery>0</w:DisplayHorizontalDrawingGridEvery>
  <w:DisplayVerticalDrawingGridEvery>2</w:DisplayVerticalDrawingGridEvery>
  <w:ValidateAgainstSchemas/>
  <w:SaveIfXMLInvalid>false</w:SaveIfXMLInvalid>
  <w:IgnoreMixedContent>false</w:IgnoreMixedContent>
  <w:AlwaysShowPlaceholderText>false</w:AlwaysShowPlaceholderText>
  <w:Compatibility>
   <w:SpaceForUL/>
   <w:BalanceSingleByteDoubleByteWidth/>
   <w:DoNotLeaveBackslashAlone/>
   <w:ULTrailSpace/>
   <w:DoNotExpandShiftReturn/>
   <w:AdjustLineHeightInTable/>
   <w:SelectEntireFieldWithStartOrEnd/>
   <w:UseWord2002TableStyleRules/>
   <w:UseFELayout/>
  </w:Compatibility>
  <w:BrowserLevel>MicrosoftInternetExplorer4</w:BrowserLevel>
 </w:WordDocument>
</xml><![endif]--><!--[if gte mso 9]><xml>
 <w:LatentStyles DefLockedState="false" LatentStyleCount="156">
 </w:LatentStyles>
</xml><![endif]-->
<style>
<!--
 /* Font Definitions */
 @font-face
	{font-family:宋体;
	panose-1:2 1 6 0 3 1 1 1 1 1;
	mso-font-alt:SimSun;
	mso-font-charset:134;
	mso-generic-font-family:auto;
	mso-font-pitch:variable;
	mso-font-signature:3 135135232 16 0 262145 0;}
@font-face
	{font-family:"\@宋体";
	panose-1:2 1 6 0 3 1 1 1 1 1;
	mso-font-charset:134;
	mso-generic-font-family:auto;
	mso-font-pitch:variable;
	mso-font-signature:3 135135232 16 0 262145 0;}
 /* Style Definitions */
 p.MsoNormal, li.MsoNormal, div.MsoNormal
	{mso-style-parent:"";
	margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	mso-pagination:none;
	font-size:10.5pt;
	mso-bidi-font-size:12.0pt;
	font-family:"Times New Roman";
	mso-fareast-font-family:宋体;
	mso-font-kerning:1.0pt;}
span.GramE
	{mso-style-name:"";
	mso-gram-e:yes;}
 /* Page Definitions */
 @page
	{mso-page-border-surround-header:no;
	mso-page-border-surround-footer:no;}
@page Section1
	{size:595.3pt 841.9pt;
	margin:72.0pt 90.0pt 72.0pt 90.0pt;
	mso-header-margin:42.55pt;
	mso-footer-margin:49.6pt;
	mso-paper-source:0;
	layout-grid:15.6pt;}
div.Section1
	{page:Section1;}
-->
</style>
<!--[if gte mso 10]>
<style>
 /* Style Definitions */
 table.MsoNormalTable
	{mso-style-name:普通表格;
	mso-tstyle-rowband-size:0;
	mso-tstyle-colband-size:0;
	mso-style-noshow:yes;
	mso-style-parent:"";
	mso-padding-alt:0cm 5.4pt 0cm 5.4pt;
	mso-para-margin:0cm;
	mso-para-margin-bottom:.0001pt;
	mso-pagination:widow-orphan;
	font-size:10.0pt;
	font-family:"Times New Roman";
	mso-ansi-language:#0400;
	mso-fareast-language:#0400;
	mso-bidi-language:#0400;}
</style>
<![endif]-->
</head>

<body lang=ZH-CN style='tab-interval:21.0pt;text-justify-trim:punctuation' onload="window.print()">
<center>
<div class=Section1 style='layout-grid:15.6pt'>

<p class=MsoNormal align=center style='text-align:center'><span
style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
"Times New Roman"'>拆</span> <span style='font-family:宋体;mso-ascii-font-family:
"Times New Roman";mso-hansi-font-family:"Times New Roman"'>迁</span> <span
style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
"Times New Roman"'>情</span> <span class=GramE><span style='font-family:宋体;
mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>况</span></span>
<span style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
"Times New Roman"'>表</span></p>
<html:form action="/cqxx/cqxxQuery.do">
<p class=MsoNormal align=center style='text-align:center'><span lang=EN-US><o:p>&nbsp;</o:p></span></p>
<bean:define id="data" name="cqxxbForm" property="cqxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Cqxxb"/>
<table class=MsoNormalTable border=1 cellspacing=0 cellpadding=0
 style='margin-left:14.4pt;border-collapse:collapse;border:none;mso-border-alt:
 solid black .5pt;mso-padding-alt:0cm 5.4pt 0cm 5.4pt;mso-border-insideh:.5pt solid black;
 mso-border-insidev:.5pt solid black'>
 <tr style='page-break-inside:avoid;height:22.5pt'>
  <td width=37 rowspan=3 style='width:27.75pt;border-top:solid black 1.5pt;
  border-left:solid black 1.5pt;border-bottom:double windowtext 1.5pt;
  border-right:solid black 1.0pt;mso-border-top-alt:solid black 1.5pt;
  mso-border-left-alt:solid black 1.5pt;mso-border-bottom-alt:double windowtext 1.5pt;
  mso-border-right-alt:solid black .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:
  22.5pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>被拆迁人情况</span></p>
  </td>
  <td width=131 style='width:98.25pt;border-top:solid black 1.5pt;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-left-alt:solid black .5pt;mso-border-top-alt:black 1.5pt;
  mso-border-left-alt:black .5pt;mso-border-bottom-alt:windowtext .5pt;
  mso-border-right-alt:windowtext .5pt;mso-border-style-alt:solid;padding:0cm 5.4pt 0cm 5.4pt;
  height:22.5pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>被拆迁人名称</span></p>
  </td>
  <td width=360 colspan=3 style='width:270.0pt;border-top:solid black 1.5pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.5pt;
  mso-border-left-alt:solid windowtext .5pt;mso-border-top-alt:black 1.5pt;
  mso-border-left-alt:windowtext .5pt;mso-border-bottom-alt:windowtext .5pt;
  mso-border-right-alt:black 1.5pt;mso-border-style-alt:solid;padding:0cm 5.4pt 0cm 5.4pt;
  height:22.5pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>
    <o:p>&nbsp;<bean:write name="data" property="bcqrmc"/></o:p></span></p>
  </td>
 </tr>
 <tr style='page-break-inside:avoid;height:22.1pt'>
  <td width=131 style='width:98.25pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid black .5pt;
  mso-border-alt:solid windowtext .5pt;mso-border-left-alt:solid black .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.1pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>证件类型</span></p>
  </td>
  <td width=96 style='width:72.0pt;border-top:none;border-left:none;border-bottom:
  solid windowtext 1.0pt;border-right:solid black 1.0pt;mso-border-top-alt:
  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;mso-border-right-alt:solid black .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:22.1pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>
    <o:p>&nbsp;<bean:write name="data" property="zjlxmc"/></o:p></span></p>
  </td>
  <td width=73 style='width:54.75pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;mso-border-right-alt:solid black .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.1pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>证件号码</span></p>
  </td>
  <td width=191 style='width:143.25pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid black 1.5pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;mso-border-right-alt:solid black 1.5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.1pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>
    <o:p>&nbsp;<bean:write name="data" property="zjhm"/></o:p></span></p>
  </td>
 </tr>
 <tr style='page-break-inside:avoid;height:47.05pt'>
  <td width=131 style='width:98.25pt;border-top:none;border-left:none;
  border-bottom:double windowtext 1.5pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid black .5pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid black .5pt;
  mso-border-bottom-alt:double windowtext 1.5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:47.05pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>被拆迁详细地址</span></p>
  </td>
  <td width=360 colspan=3 style='width:270.0pt;border-top:none;border-left:
  none;border-bottom:double windowtext 1.5pt;border-right:solid black 1.5pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:47.05pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>
    <o:p>&nbsp;<bean:write name="data" property="cqxxdz"/></o:p></span></p>
  </td>
 </tr>
 <tr style='page-break-inside:avoid;height:22.5pt'>
  <td width=37 rowspan=3 style='width:27.75pt;border-top:none;border-left:solid black 1.5pt;
  border-bottom:double windowtext 1.5pt;border-right:solid black 1.0pt;
  mso-border-top-alt:double windowtext 1.5pt;mso-border-top-alt:double windowtext 1.5pt;
  mso-border-left-alt:solid black 1.5pt;mso-border-bottom-alt:double windowtext 1.5pt;
  mso-border-right-alt:solid black .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:
  22.5pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>拆迁单位情况</span></p>
  </td>
  <td width=131 style='width:98.25pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid black .5pt;
  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid black .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.5pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>拆迁单位名称</span></p>
  </td>
  <td width=360 colspan=3 style='width:270.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.5pt;
  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid black 1.5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.5pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>
    <o:p>&nbsp;<bean:write name="data" property="cqrmc"/></o:p></span></p>
  </td>
 </tr>
 <tr style='page-break-inside:avoid;height:24.4pt'>
  <td width=131 style='width:98.25pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid black .5pt;
  mso-border-alt:solid windowtext .5pt;mso-border-left-alt:solid black .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:24.4pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>拆迁许可证号</span></p>
  </td>
  <td width=96 style='width:72.0pt;border-top:none;border-left:none;border-bottom:
  solid windowtext 1.0pt;border-right:solid black 1.0pt;mso-border-top-alt:
  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;mso-border-right-alt:solid black .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:24.4pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>
    <o:p>&nbsp;<bean:write name="data" property="cqxkzh"/></o:p></span></p>
  </td>
  <td width=73 style='width:54.75pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;mso-border-right-alt:solid black .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:24.4pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>所在区县</span></p>
  </td>
  <td width=191 style='width:143.25pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid black 1.5pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;mso-border-right-alt:solid black 1.5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:24.4pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>
    <o:p>&nbsp;<bean:write name="data" property="szqx"/></o:p></span></p>
  </td>
 </tr>
 <tr style='page-break-inside:avoid;height:36.25pt'>
  <td width=131 style='width:98.25pt;border-top:none;border-left:none;
  border-bottom:double windowtext 1.5pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid black .5pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid black .5pt;
  mso-border-bottom-alt:double windowtext 1.5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:36.25pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>拆迁范围</span></p>
  </td>
  <td width=360 colspan=3 style='width:270.0pt;border-top:none;border-left:
  none;border-bottom:double windowtext 1.5pt;border-right:solid black 1.5pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:36.25pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>
    <o:p>&nbsp;<bean:write name="data" property="cqfw"/></o:p></span></p>
  </td>
 </tr>
 <tr style='page-break-inside:avoid;height:25.45pt'>
  <td width=37 rowspan=3 style='width:27.75pt;border-top:none;border-left:solid black 1.5pt;
  border-bottom:solid black 1.5pt;border-right:solid black 1.0pt;mso-border-top-alt:
  double windowtext 1.5pt;mso-border-top-alt:double windowtext 1.5pt;
  mso-border-left-alt:solid black 1.5pt;mso-border-bottom-alt:solid black 1.5pt;
  mso-border-right-alt:solid black .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:
  25.45pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>补偿情况</span></p>
  </td>
  <td width=131 style='width:98.25pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid black .5pt;
  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid black .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:25.45pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>补偿类型</span></p>
  </td>
  <td width=360 colspan=3 style='width:270.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.5pt;
  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid black 1.5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:25.45pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>
    <o:p>&nbsp;<bean:write name="data" property="bclxmc"/></o:p></span></p>
  </td>
 </tr>
 <tr style='page-break-inside:avoid;height:26.8pt'>
  <td width=131 style='width:98.25pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid black .5pt;
  mso-border-alt:solid windowtext .5pt;mso-border-left-alt:solid black .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:26.8pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>补偿金额</span></p>
  </td>
  <td width=96 style='width:72.0pt;border-top:none;border-left:none;border-bottom:
  solid windowtext 1.0pt;border-right:solid black 1.0pt;mso-border-top-alt:
  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;mso-border-right-alt:solid black .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:26.8pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>
    <o:p>&nbsp;<%=DataConvert.BigDecimal2String(cqxx.getBcje())%></o:p></span></p>
  </td>
  <td width=73 style='width:54.75pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;mso-border-right-alt:solid black .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:26.8pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>补偿面积</span></p>
  </td>
  <td width=191 style='width:143.25pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid black 1.5pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;mso-border-right-alt:solid black 1.5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:26.8pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>
    <o:p>&nbsp;<%=DataConvert.BigDecimal2String(cqxx.getBcmj())%></o:p></span></p>
  </td>
 </tr>
 <tr style='page-break-inside:avoid;height:38.5pt'>
  <td width=131 style='width:98.25pt;border-top:none;border-left:none;
  border-bottom:solid black 1.5pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid black .5pt;
  mso-border-top-alt:windowtext .5pt;mso-border-left-alt:black .5pt;mso-border-bottom-alt:
  black 1.5pt;mso-border-right-alt:windowtext .5pt;mso-border-style-alt:solid;
  padding:0cm 5.4pt 0cm 5.4pt;height:38.5pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>补偿房屋坐落地址</span></p>
  </td>
  <td width=360 colspan=3 style='width:270.0pt;border-top:none;border-left:
  none;border-bottom:solid black 1.5pt;border-right:solid black 1.5pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:38.5pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>
    <o:p>&nbsp;<bean:write name="data" property="bcfwdz"/></o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-lastrow:yes;page-break-inside:avoid;height:25.9pt'>
  <td width=528 colspan=5 style='width:396.0pt;border:solid black 1.5pt;
  border-top:none;mso-border-top-alt:solid black 1.5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:25.9pt'>
  <p class=MsoNormal><span style='font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>录入时间：</span><span lang=EN-US><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;<%=DataConvert.TimeStamp2String(cqxx.getLrrq())%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span></span><span style='font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>录入机构：<bean:write name="data" property="swjgzzjgmc"/></span></p>
  </td>
 </tr>
</table>
</html:form>
<p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>

</div>
</center>
</body>

</html>
