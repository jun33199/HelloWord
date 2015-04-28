<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<html>
<head>
<title>个人所得税申报</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<script language=JavaScript>
function doReturn()
{
	document.forms[0].action = "quit.do";
	document.forms[0].submit();
}
</script>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">

<table width="97%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2>

    	<jsp:include page="../../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="个人所得税申报" />
		<jsp:param name="help_url" value="help/wssb/qyzhsb/gzsx/gzsx-000.htm"/>
    	</jsp:include>

    	<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
	  <tr>
	    <td valign="top">
	     <br>
	      <html:errors/>
	     <table cellspacing="0" class="table-99">
		<!-- begin ========================================================================================--->
            <tr>
            	<td>
            	<form>
       			<table cellspacing="0" width="100%">
       				<tr>
       					<td class="1-td1" colspan="7">个人所得税申报</td>
       				</tr>
       				<tr>
       					<td class="2-td2-left" rowspan="2">投资者信息</td>
       					<td class="2-td2-center">姓名：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       					<td class="2-td2-right">身份证件类型：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       					<td class="2-td2-right">身份证件号码：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center">国籍（地区）：</td>
       					<td class="2-td2-right" colspan="3"><input type="text"/></td>
       					<td class="2-td2-right">纳税人识别号：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-left" rowspan="2">被投资单位信息</td>
       					<td class="2-td2-center">名称：</td>
       					<td class="2-td2-right" colspan="3"><input type="text"/></td>
       					<td class="2-td2-right">纳税人识别号：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center">类型：</td>
       					<td class="2-td2-right" colspan="5">
       						<input type="checkbox">个体工商户
       						<input type="checkbox">承包、承租经营者
       						<input type="checkbox">个人独资企业
       						<input type="checkbox">合伙企业人
       					</td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">项目</td>
       					<td class="2-td2-right">行次</td>
       					<td class="2-td2-right" colspan="2">金额</td>
       					<td class="2-td2-right">补充资料</td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">一、收入总额</td>
       					<td class="2-td2-right">1</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       					<td class="2-td2-right" rowspan="51">
       						1、年平均职工人数：<input type="text">&nbsp;人<br/>
       						2、工资总额：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text">&nbsp;元<br/>
       						3、投资者人数：&nbsp;&nbsp;&nbsp;&nbsp;<input type="text">&nbsp;人<br/>
       					</td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">减：成本</td>
       					<td class="2-td2-right">2</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">营业费用：</td>
       					<td class="2-td2-right">3</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">管理费用：</td>
       					<td class="2-td2-right">4</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">财务费用：</td>
       					<td class="2-td2-right">5</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">营业税金及附加：</td>
       					<td class="2-td2-right">6</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">营业外支出：</td>
       					<td class="2-td2-right">7</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">二、利润总和</td>
       					<td class="2-td2-right">8</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">三、纳税调整增加额</td>
       					<td class="2-td2-right">9</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">1、超过规定标准扣除的项目</td>
       					<td class="2-td2-right">10</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">（1）职工福利费</td>
       					<td class="2-td2-right">11</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">（2）职工教育经费</td>
       					<td class="2-td2-right">12</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">（3）公会经费</td>
       					<td class="2-td2-right">13</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">（4）利息支出</td>
       					<td class="2-td2-right">14</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">（5）业务招待费</td>
       					<td class="2-td2-right">15</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">（6）广告费和业务宣传费</td>
       					<td class="2-td2-right">16</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">（7）教育和公益事业捐赠</td>
       					<td class="2-td2-right">17</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">（8）住房公积金</td>
       					<td class="2-td2-right">18</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">（9）实惠保险费</td>
       					<td class="2-td2-right">19</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">（10）折旧费用</td>
       					<td class="2-td2-right">20</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">（11）无形资产摊销</td>
       					<td class="2-td2-right">21</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">（12）资产损失</td>
       					<td class="2-td2-right">22</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">（13）其他</td>
       					<td class="2-td2-right">23</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">2、不允许扣除的项目</td>
       					<td class="2-td2-right">24</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">（1）资本性支出</td>
       					<td class="2-td2-right">25</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">（2）无形资产受让、开发支出</td>
       					<td class="2-td2-right">26</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">（3）税收滞纳金、罚金、罚款</td>
       					<td class="2-td2-right">27</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">（4）赞助支出、非教育和公益事业捐赠</td>
       					<td class="2-td2-right">28</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">（5）灾害事故损失赔偿</td>
       					<td class="2-td2-right">29</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">（6）计提的各种准备金</td>
       					<td class="2-td2-right">30</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">（7）投资者工资薪金</td>
       					<td class="2-td2-right">31</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">（8）与收入无关的支出</td>
       					<td class="2-td2-right">32</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">其中：投资者家庭费用</td>
       					<td class="2-td2-right">33</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">四、纳税调整减少额</td>
       					<td class="2-td2-right">34</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">1、国债利息收入</td>
       					<td class="2-td2-right">35</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">2、其他</td>
       					<td class="2-td2-right">36</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">五、以前年度损益调整</td>
       					<td class="2-td2-right">37</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">六、经纳税调整后的生产经营所得</td>
       					<td class="2-td2-right">38</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">减：弥补以前年度损失</td>
       					<td class="2-td2-right">39</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">乘：分配比例%</td>
       					<td class="2-td2-right">40</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">七：允许扣除的其他费用</td>
       					<td class="2-td2-right">41</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">八、投资者减除费用</td>
       					<td class="2-td2-right">42</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">九、应纳税所得额</td>
       					<td class="2-td2-right">43</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">十、税率（%）</td>
       					<td class="2-td2-right">44</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">十一、速算扣除数</td>
       					<td class="2-td2-right">45</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">十二、应纳税额</td>
       					<td class="2-td2-right">46</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">减：减免税额</td>
       					<td class="2-td2-right">47</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">十三、全年应缴税额</td>
       					<td class="2-td2-right">48</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">加：期初未缴税额</td>
       					<td class="2-td2-right">49</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">减：全年已预缴税额</td>
       					<td class="2-td2-right">50</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">十四、应补（退）税额</td>
       					<td class="2-td2-right">51</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="7">
       						<img src="<%=static_contextpath%>images/tuichu1.jpg" onmouseover="this.src='<%=static_contextpath%>images/tuichu2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/tuichu1.jpg'" alt="退出" onclick="doReturn();" style="cursor:hand">
       					</td>
       				</tr>
       			</table>
       			</form>
       			</td>
            </tr>
	    <!-- end ==========================================================================================--->
			<tr>
	          <td class="1-td2-center"><br>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
				 <tr>
				    <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				    <td width="20%" align="center" class="black9"><strong><font color="#0000FF">注意事项</font></strong></td>
				     <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				  </tr>
				</table>
				<table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
				   <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
				      <td height="47"  >
				        <p style ="color:red">
				            &nbsp;&nbsp;&nbsp;&nbsp;谨声明：此表是根据《中华人民共和国个人所得税法》及其实施条例和国家相关法律法规规定填写的，是真实的、完整的、可靠的。<br>
				         </p>
				       </td>
				    </tr>
				</table>
	  			<br>
	          </td>
	         </tr>
	     </table>
	    </td>
	  </tr>
    	</table>
<br>
    </td>
  </tr>
<tr>
  <td valign="bottom" align="center">
	<%@ include file="../include/footer.jsp" %>
  </td>
</tr>
 </table>
</body>
</html>