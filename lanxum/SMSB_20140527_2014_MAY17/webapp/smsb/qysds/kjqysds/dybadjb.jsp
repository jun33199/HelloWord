<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.kjqysds.badjb.web.BadjbForm"%>
<%@ page import="com.ttsoft.bjtax.sfgl.common.db.util.SfResourceLocator"%>

<%
	//静态文件目录
	String static_contextpath = SfResourceLocator.getContextPath(request);

	// 获取BadjbForm
	BadjbForm form = (BadjbForm) request.getAttribute("badjbForm");
	// 填报时间
	String tbrq = form.getTbrq();
	String year = tbrq.substring(0, 4);
	String month = tbrq.substring(5, 7);
	String day = tbrq.substring(8);
%>
<html>
<head>
    <meta http-equiv=Content-Type content="text/html; charset=gb2312" />
	<script language="JavaScript" type="text/JavaScript" src="../../../js/treatImage.js"></script>

    <title>扣缴企业所得税合同备案登记表</title>

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
        mso-font-kerning:1.0pt;
        mso-no-proof:yes;}
        p.MsoHeader, li.MsoHeader, div.MsoHeader
        {mso-style-link:" Char Char1";
        margin:0cm;
        margin-bottom:.0001pt;
        text-align:center;
        mso-pagination:none;
        tab-stops:center 207.65pt right 415.3pt;
        layout-grid-mode:char;
        border:none;
        mso-border-bottom-alt:solid windowtext .75pt;
        padding:0cm;
        mso-padding-alt:0cm 0cm 1.0pt 0cm;
        font-size:9.0pt;
        font-family:"Times New Roman";
        mso-fareast-font-family:宋体;
        mso-font-kerning:1.0pt;
        mso-no-proof:yes;}
        p.MsoFooter, li.MsoFooter, div.MsoFooter
        {mso-style-link:" Char Char";
        margin:0cm;
        margin-bottom:.0001pt;
        mso-pagination:none;
        tab-stops:center 207.65pt right 415.3pt;
        layout-grid-mode:char;
        font-size:9.0pt;
        font-family:"Times New Roman";
        mso-fareast-font-family:宋体;
        mso-font-kerning:1.0pt;
        mso-no-proof:yes;}
        p.MsoAcetate, li.MsoAcetate, div.MsoAcetate
        {mso-style-link:" Char Char2";
        margin:0cm;
        margin-bottom:.0001pt;
        text-align:justify;
        text-justify:inter-ideograph;
        mso-pagination:none;
        font-size:9.0pt;
        font-family:"Times New Roman";
        mso-fareast-font-family:宋体;
        mso-font-kerning:1.0pt;
        mso-no-proof:yes;}
        span.CharChar2
        {mso-style-name:" Char Char2";
        mso-style-locked:yes;
        mso-style-link:批注框文本;
        mso-ansi-font-size:9.0pt;
        mso-bidi-font-size:9.0pt;
        mso-font-kerning:1.0pt;
        mso-no-proof:yes;}
        span.CharChar1
        {mso-style-name:" Char Char1";
        mso-style-locked:yes;
        mso-style-link:页眉;
        mso-ansi-font-size:9.0pt;
        mso-bidi-font-size:9.0pt;
        mso-font-kerning:1.0pt;
        mso-no-proof:yes;}
        span.CharChar
        {mso-style-name:" Char Char";
        mso-style-locked:yes;
        mso-style-link:页脚;
        mso-ansi-font-size:9.0pt;
        mso-bidi-font-size:9.0pt;
        mso-font-kerning:1.0pt;
        mso-no-proof:yes;}
        ins
        {mso-style-type:export-only;
        text-decoration:none;}
        span.msoIns
        {mso-style-type:export-only;
        mso-style-name:"";
        text-decoration:underline;
        text-underline:single;}
        /* Page Definitions */
        @page Section1
        {size:595.3pt 841.9pt;
        margin:72.0pt 2.0cm 72.0pt 2.0cm;
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
    <![endif]--><!--[if gte mso 9]><xml>
        <o:shapedefaults v:ext="edit" spidmax="3074"/>
    </xml><![endif]--><!--[if gte mso 9]><xml>
        <o:shapelayout v:ext="edit">
            <o:idmap v:ext="edit" data="2"/>
        </o:shapelayout></xml><![endif]-->
</head>

<script>
// 打印
function doPrint()
{
	document.all.printDiv.style.display = "none";
	window.print();
	document.all.printDiv.style.display = "";
}
</script>

<html:form action="/webapp/smsb/qysds/kjqysds/badjbAction.do" method="post">

<body lang=ZH-CN style='tab-interval:21.0pt;text-justify-trim:punctuation'>
    <div class=Section1 style='layout-grid:15.6pt' align="center">
        <p class=MsoNormal align=center style='text-align:center'>
            <b><span style='font-size:16.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;color:black'>扣缴企业所得税合同备案登记表</span></b>
        </p>
        <p class=MsoNormal style='text-indent:24.0pt;mso-char-indent-count:2.0'>
            <span lang=EN-US style='font-size:12.0pt;font-family:宋体;color:black;mso-bidi-font-weight: bold'></span>
        </p>
        <p class=MsoNormal align=center style='text-indent:21.0pt;mso-char-indent-count:2.0;text-align:center;'>
            <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                编号：<bean:write name="badjbForm" property="badjbbh" scope="request" filter="true" />
                <span lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></span>
                填报日期：<span lang=EN-US><span style='mso-spacerun:yes'>&nbsp;<%=year%></span></span>
                年<span lang=EN-US><span style='mso-spacerun:yes'>&nbsp;<%=month%></span></span>
                月<span lang=EN-US><span style='mso-spacerun:yes'>&nbsp;<%=day%></span></span>
                日<span lang=EN-US></span>
            </span>
        </p>

        <table width="645" border=0 align="center" cellpadding=0 cellspacing=0 >
            <tr>
			  <td width=15% rowspan=6 style='border:solid windowtext 1.0pt;mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt'>
				<p class=MsoNormal align=center style='text-align:center'> <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>扣缴<span lang=EN-US></span></span>
				</p>
                    <p class=MsoNormal align=center style='text-align:center'>
                        <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>义务人</span>
                    </p>
                </td>
                <td width="85%" colspan="2" valign=top style='border:solid windowtext 1.0pt;border-left:none;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt'>
                    <p class=MsoNormal>
                        <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                            <span>计算机代码：<bean:write name="badjbForm" property="jsjdm" scope="request" filter="true" /></span>
                        </span>
                    </p>
                </td>
            </tr>
            <tr style='mso-yfti-irow:1;page-break-inside:avoid;height:14.25pt'>
				<td colspan="2" valign=top style='border-top:none;
                    border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
                    mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
                    mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt'>
					<p class=MsoNormal>
						<span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                            中文名称：<bean:write name="badjbForm" property="kjywrxx.kjrmc_cn" scope="request" filter="true" />
                        </span>
					</p>
                </td>
            </tr>
            <tr style='mso-yfti-irow:2;page-break-inside:avoid;height:14.25pt'>
				<td colspan="2" valign=top style='border-top:none;
                    border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
                    mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
                    mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt'>
					<p class=MsoNormal>
						<span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                            英文名称：<bean:write name="badjbForm" property="kjywrxx.kjrmc_en" scope="request" filter="true" />
                        </span>
					</p>
                </td>
            </tr>
            <tr style='mso-yfti-irow:3;page-break-inside:avoid;height:14.25pt'>
				<td colspan="2" valign=top style='border-top:none;
					border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
					mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt'>
					<p class=MsoNormal>
						<span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                            扣缴义务人纳税识别号：<bean:write name="badjbForm" property="kjywrxx.kjrnssbh" scope="request" filter="true" />
                        </span>
					</p>
                </td>
            </tr>
            <tr style='mso-yfti-irow:4;page-break-inside:avoid;height:14.25pt'>
                <td width="50%" valign=top style='border-top:none;
                    border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
                    mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
                    mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt'>
                    <p class=MsoNormal>
                        <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                            地址：<bean:write name="badjbForm" property="kjywrxx.kjrdz_cn" scope="request" filter="true" />
                        </span>
                    </p>
                </td>
                <td valign=top style='border-top:none;border-left:
                    none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
                    mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
                    mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt'>
                    <p class=MsoNormal style='margin-left:.6pt'>
                        <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                            邮编：<bean:write name="badjbForm" property="kjywrxx.kjryzbm" scope="request" filter="true" />
                        </span>
                    </p>
                </td>
            </tr>
            <tr style='mso-yfti-irow:5;page-break-inside:avoid;height:14.25pt'>
                <td colspan="2" valign=top style='border-top:none;
                    border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
                    mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
                    mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt'>
                    <p class=MsoNormal>
                        <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;'>
                            财务负责人：<bean:write name="badjbForm" property="kjywrxx.kjrcwfzr" scope="request" filter="true" />
                            <span lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;</span></span>
                            联系人：<bean:write name="badjbForm" property="kjywrxx.kjrlxr" scope="request" filter="true" />
                            <span lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;</span></span>
                            电话：<bean:write name="badjbForm" property="kjywrxx.kjrlxdh" scope="request" filter="true" />
                            <span lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;</span></span>
                            传真：<bean:write name="badjbForm" property="kjywrxx.kjrczhm" scope="request" filter="true" />
                        </span>
                    </p>
                </td>
            </tr>
            <tr style='mso-yfti-irow:6;page-break-inside:avoid;height:15.0pt'>
                <td width=84 rowspan=6 style='border:solid windowtext 1.0pt;
                    border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
                    padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt'>
                    <p class=MsoNormal align=center style='text-align:center'>
                        <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>非居民</span>
                    </p>
                    <p class=MsoNormal align=center style='text-align:center'>
                        <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>企业</span>
                    </p>
                    <p class=MsoNormal align=center style='text-align:center;text-indent:21.0pt;mso-char-indent-count:2.0'>
                        <span lang=EN-US style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'></span>
                    </p>
                </td>
                <td colspan="2" valign=top style='border-top:none;
                    border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
                    mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
                    mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt'>
                    <p class=MsoNormal>
                        <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                            中文名称：<bean:write name="badjbForm" property="fjmqyxx.fjmmc_cn" scope="request" filter="true" />
                        </span>
                    </p>
                </td>
            </tr>
            <tr style='mso-yfti-irow:7;page-break-inside:avoid;height:14.25pt'>
                <td colspan="2" valign=top style='border-top:none;
                    border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
                    mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
                    mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt'>
                    <p class=MsoNormal>
                        <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                            英文名称：<bean:write name="badjbForm" property="fjmqyxx.fjmmc_en" scope="request" filter="true" />
                        </span>
                    </p>
                </td>
            </tr>
            <tr style='mso-yfti-irow:8;page-break-inside:avoid;height:12.5pt'>
                <td valign=top style='border-top:none;border-left:
                    none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
                    mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
                    mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:12.5pt'>
                    <p class=MsoNormal>
                        <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                            国别：<bean:write name="badjbForm" property="fjmqyxx.fjmgbmc" scope="request" filter="true" />
                        </span>
                    </p>
                </td>
                <td valign=top style='border-top:none;
                    border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
                    mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
                    mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:12.5pt'>
                    <p class=MsoNormal> 
						<span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
							<span>国家或地区：</span>港澳台<input type="checkbox" id="gat" onclick="this.checked=!this.checked" />
							<span lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;</span></span> 
							外国<span lang=EN-US><span style='mso-spacerun:yes'><input type="checkbox" id="wg" onclick="this.checked=!this.checked" /></span></span>
						</span>
						<script>
						<%
							if("01".equals(form.getFjmqyxx().getFjmgjdq()))
							{
						%>
								document.getElementById("gat").checked = true;
								document.getElementById("wg").checked = false;
						<%
							}
							else if("02".equals(form.getFjmqyxx().getFjmgjdq()))
							{
						%>
								document.getElementById("gat").checked = false;
								document.getElementById("wg").checked = true;
						<%
							}
						%>
						</script>
                    </p>
                </td>
            </tr>
            <tr style='mso-yfti-irow:9;page-break-inside:avoid;height:15.75pt'>
                <td colspan="2" valign=top style='border-top:none;
                    border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
                    mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
                    mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:15.75pt'>
                    <p class=MsoNormal align=left style='text-align:left'>
                        <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                            其居民国地址（中文）：<bean:write name="badjbForm" property="fjmqyxx.fjmdz_cn" scope="request" filter="true" />
                        </span>
                    </p>
                </td>
            </tr>
                <tr style='mso-yfti-irow:10;page-break-inside:avoid;height:19.5pt'>
                    <td colspan="2" valign=top style='border-top:none;
                        border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
                        mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:19.5pt'>
                        <p class=MsoNormal><span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                            其居民国地址（英文）：<bean:write name="badjbForm" property="fjmqyxx.fjmdz_en" scope="request" filter="true" /></span>
                        </p>
                    </td>
                </tr>
                <tr style='mso-yfti-irow:11;page-break-inside:avoid;height:19.5pt'>
                    <td colspan="2" valign=top style='border-top:none;
                        border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
                        mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:19.5pt'>
                        <p class=MsoNormal>
                            <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                                财务负责人：<bean:write name="badjbForm" property="fjmqyxx.fjmcwfzr" scope="request" filter="true" />
                                <span lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;</span></span>
                                联系人：<bean:write name="badjbForm" property="fjmqyxx.fjmlxr" scope="request" filter="true" />
                                <span lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;</span></span>
                                电话：<bean:write name="badjbForm" property="fjmqyxx.fjmlxdh" scope="request" filter="true" />
                                <span lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;</span></span>
                                传真：<bean:write name="badjbForm" property="fjmqyxx.fjmczhm" scope="request" filter="true" />
                            </span>
                        </p>
                    </td>
                </tr>
                <tr style='mso-yfti-irow:12;page-break-inside:avoid;height:19.5pt'>
                    <td width=84 rowspan=9 style='border:solid windowtext 1.0pt;
                        border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
                        padding:0cm 5.4pt 0cm 5.4pt;height:19.5pt'>
                        <p class=MsoNormal align=center style='text-align:center'>
                            <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>合同</span>
                        </p>
                        <p class=MsoNormal align=center style='text-align:center'>
                            <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>信息</span>
                        </p>
                        <p class=MsoNormal align=center style='text-align:center;text-indent:21.0pt;mso-char-indent-count:2.0'>
                            <span lang=EN-US style='mso-bidi-font-size:10.5pt;font-family:宋体;color:red;mso-bidi-font-weight:bold'></span>
                        </p>
                    </td>
                    <td colspan="2" valign=top style='border-top:none;
                        border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
                        mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:19.5pt'>
                        <p class=MsoNormal>
                            <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                                合同或协议名称：<bean:write name="badjbForm" property="htxx.htmc" scope="request" filter="true" />
                            </span>
                        </p>
                    </td>
                </tr>
                <tr style='mso-yfti-irow:13;page-break-inside:avoid;height:13.75pt'>
                    <td colspan="2" valign=top style='border-top:none;
                        border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
                        mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:13.75pt'>
                        <p class=MsoNormal>
                            <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                                合同编号：<bean:write name="badjbForm" property="htxx.htbh" scope="request" filter="true" />
                            </span>
                        </p>
                    </td>
                </tr>
                <tr style='mso-yfti-irow:14;page-break-inside:avoid;height:14.25pt'>
                    <td colspan="2" valign=top style='border-top:none;
                        border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
                        mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt'>
                        <p class=MsoNormal>
                            <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                                合同签约日期：<bean:write name="badjbForm" property="htxx.qyrq" scope="request" filter="true" />
                            </span>
                        </p>
                    </td>
                </tr>
                <tr style='mso-yfti-irow:15;page-break-inside:avoid;height:13.5pt'>
                    <td colspan="2" valign=top style='border-top:none;
                        border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
                        mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:13.5pt'>
                        <p class=MsoNormal>
                            <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                                合同有效期限：<bean:write name="badjbForm" property="htxx.htyxq" scope="request" filter="true" />
                            </span>
                        </p>
                    </td>
                </tr>
                <tr style='mso-yfti-irow:16;page-break-inside:avoid;height:15.55pt'>
                    <td colspan="2" valign=top style='border-top:none;
                        border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
                        mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:15.55pt'>
                        <p class=MsoNormal>
                            <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                                合同金额：<bean:write name="badjbForm" property="htxx.htje" scope="request" filter="true" />
                            </span>
                        </p>
                    </td>
                </tr>
                <tr style='mso-yfti-irow:17;page-break-inside:avoid;height:15.55pt'>
                    <td colspan="2" valign=top style='border-top:none;
                        border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
                        mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:15.55pt'>
                        <p class=MsoNormal>
                            <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                                币种：<bean:write name="badjbForm" property="htxx.bzmc" scope="request" filter="true" />
                            </span>
                        </p>
                    </td>
                </tr>
                <tr style='mso-yfti-irow:18;page-break-inside:avoid;height:15.55pt'>
                    <td colspan="2" valign=top style='border-top:none;
                        border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
                        mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:15.55pt'>
                        <p class=MsoNormal>
                            <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                                支付项目：<bean:write name="badjbForm" property="htxx.zfxm" scope="request" filter="true" />
                            </span>
                        </p>
                    </td>
                </tr>
                <tr style='mso-yfti-irow:19;page-break-inside:avoid;height:15.55pt'>
                    <td colspan="2" valign=top style='border-top:none;
                        border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
                        mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:15.55pt'>
                        <p class=MsoNormal>
                            <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                                付款次数：<bean:write name="badjbForm" property="htxx.fkcs" scope="request" filter="true" />
                            </span>
                        </p>
                    </td>
                </tr>
                <tr style='mso-yfti-irow:20;page-break-inside:avoid;height:15.55pt'>
                    <td colspan="2" valign=top style='border-top:none;
                        border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
                        mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:15.55pt'>
                        <p class=MsoNormal>
                            <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                                其他资料名称：<bean:write name="badjbForm" property="htxx.qtzlmc" scope="request" filter="true" />
                            </span>
                        </p>
                    </td>
                </tr>
                <tr style='mso-yfti-irow:23;height:21.0pt'>
                    <td colspan="3" valign=top style='border:none;
                        border-bottom:solid windowtext 1.0pt;mso-border-top-alt:solid windowtext .5pt;
                        mso-border-top-alt:solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;
                        padding:0cm 5.4pt 0cm 5.4pt;height:21.0pt'>
                        <p class=MsoNormal>
                            <span lang=EN-US style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'></span>
                        </p>
                        <p class=MsoNormal>
                            <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>以下由主管税务机关填写</span>
                        </p>
                    </td>
                </tr>
                <tr style='mso-yfti-irow:24;height:35.4pt'>
                    <td colspan="3" valign=top style='border:solid windowtext 1.0pt;
                        border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
                        padding:0cm 5.4pt 0cm 5.4pt;height:35.4pt'>
                        <p class=MsoNormal>
                            <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>税务机关确认接收合同或协议（复印件）份数：</span>
                        </p>
                    </td>
                </tr>
                <tr style='mso-yfti-irow:25;mso-yfti-lastrow:yes;height:80.9pt'>
                    <td colspan="2" valign=top style='border:solid windowtext 1.0pt;
                        border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
                        padding:0cm 5.4pt 0cm 5.4pt;height:80.9pt'>
                        <p class=MsoNormal style='line-height:150%'>
                            <span style='mso-bidi-font-size:10.5pt;line-height:150%;font-family:宋体;color:black;mso-bidi-font-weight:bold'>情况说明：</span>
                        </p>
                        <p class=MsoNormal align=right style='text-align:right;line-height:150%'>
                            <span lang=EN-US style='mso-bidi-font-size:10.5pt;line-height:150%;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                                <span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp; </span>
                            </span>
                        </p>
                        <p class=MsoNormal align=right style='text-align:right;line-height:150%'>
                            <span lang=EN-US style='mso-bidi-font-size:10.5pt;line-height:150%;font-family:宋体;color:black;mso-bidi-font-weight:bold'>&nbsp;</span>
                        </p>
                    </td>
                    <td valign=top style='border-top:none;
                        border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
                        mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
                        mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:80.9pt'>
                        <p class=MsoNormal style='line-height:150%'>
                            <span style='mso-bidi-font-size:10.5pt;line-height:150%;font-family:宋体;color:black;mso-bidi-font-weight:bold'>接收人：</span>
                        </p>
                        <p class=MsoNormal align=right style='text-align:right;line-height:150%'>
                            <span lang=EN-US style='mso-bidi-font-size:10.5pt;line-height:150%;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                                <span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                            </span>
                        </p>
                        <p class=MsoNormal align=center style='text-align:center;line-height:150%'>
                            <span lang=EN-US style='mso-bidi-font-size:10.5pt;line-height:150%;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                                <span style='mso-spacerun:yes'>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </span>
                            </span>
							<span lang=EN-US style='mso-bidi-font-size:10.5pt;line-height:150%;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                                <span style='mso-spacerun:yes'>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </span>
                            </span>
							<span lang=EN-US style='mso-bidi-font-size:10.5pt;line-height:150%;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                                <span style='mso-spacerun:yes'>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </span>
                            </span>
							<span lang=EN-US style='mso-bidi-font-size:10.5pt;line-height:150%;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                                <span style='mso-spacerun:yes'>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </span>
                            </span>
							<span lang=EN-US style='mso-bidi-font-size:10.5pt;line-height:150%;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                                <span style='mso-spacerun:yes'>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </span>
                            </span>
							<span lang=EN-US style='mso-bidi-font-size:10.5pt;line-height:150%;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                                <span style='mso-spacerun:yes'>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </span>
                            </span>
							<span lang=EN-US style='mso-bidi-font-size:10.5pt;line-height:150%;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                                <span style='mso-spacerun:yes'>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </span>
                            </span>
							<span lang=EN-US style='mso-bidi-font-size:10.5pt;line-height:150%;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                                <span style='mso-spacerun:yes'>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（公       章）
                                </span>
                            </span>
                            <span style='mso-bidi-font-size:10.5pt;line-height:150%;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
								<span lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></span>
								<span style='text-align:right'>
									&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
								</span>
                            </span>
                        </p>
                    </td>
                </tr>
				<tr style='mso-yfti-irow:23;height:21.0pt'>
                    <td colspan="3" valign=top style='border:none;mso-border-top-alt:solid windowtext .5pt;
                        mso-border-top-alt:solid windowtext 1.5pt;mso-border-bottom-alt:solid windowtext .5pt;
                        padding:8pt 5.4pt 0cm 5.4pt;height:21.0pt'>
                        <p class=MsoNormal>
							<span lang=EN-US style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'></span>
                        </p>
                        <p class=MsoNormal>
                            <span style='mso-bidi-font-size:10.5pt;font-family:宋体;color:black;mso-bidi-font-weight:bold'>
                                备注：支付项目是指合同规定的具体项目名称。
                            </span>
                        </p>
                    </td>
                </tr>
				<tr style='mso-yfti-irow:23;height:21.0pt'>
                    <td colspan="3" align="center" style='border:none;mso-border-top-alt:solid windowtext .5pt;
                        mso-border-top-alt:solid windowtext 1.5pt;mso-border-bottom-alt:solid windowtext .5pt;
                        padding:8pt 5.4pt 0cm 5.4pt;height:21.0pt'>
						<div id="printDiv">
							<input type="image"
								style="cursor:hand" tabIndex="-1" onClick="doPrint();return false;"
								accesskey="s" value="打印" alt="打印备案登记表"
								onMouseOver="MM_swapImage('dayin','','<%=static_contextpath%>images/dayin2.jpg',1)"
								onMouseOut="MM_swapImgRestore()"
								src="<%=static_contextpath%>images/dayin1.jpg"
								id="dayin" />
							&nbsp;&nbsp;
							<input type="image" value="关闭" alt="关闭"
								style="cursor:hand" tabIndex="-1" onClick="window.close()"
								onMouseOver="MM_swapImage('guanbi','','<%=static_contextpath%>images/guanbi2.jpg',1)"
								onMouseOut="MM_swapImgRestore()"
								src="<%=static_contextpath%>images/guanbi1.jpg"
								id="guanbi" />
						</div>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html:form>
</html>

