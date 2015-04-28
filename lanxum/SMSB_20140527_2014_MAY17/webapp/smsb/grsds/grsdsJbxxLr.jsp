<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<html>
<head>
<title>个人所得税基础信息</title>
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

    	<jsp:include page="../include/header.jsp" flush="true" >
    		<jsp:param name="name" value="个人所得税基本信息录入" />
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
       					<td class="1-td1" colspan="6">个人所得税基础信息</td>
       				</tr>
       				<tr>
       					<td class="2-td2-left">姓名：</td>
       					<td class="2-td2-center"><input type="text"/></td>
       					<td class="2-td2-right">身份证件类型：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       					<td class="2-td2-right">身份证件号码：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-left" rowspan="3">任职受雇单位名称及纳税人识别号：</td>
       					<td class="2-td2-center" colspan="3"><input type="text"/></td>
       					<td class="2-td2-right" colspan="2"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3"><input type="text"/></td>
       					<td class="2-td2-right" colspan="2"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3"><input type="text"/></td>
       					<td class="2-td2-right" colspan="2"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-left">“三费一金”缴纳情况：</td>
       					<td class="2-td2-center" colspan="3">
							<input type="checkbox">基本养老保险费
							<input type="checkbox">基本医疗保险费
							<input type="checkbox">失业保险费
							<input type="checkbox">住房公积金
						</td>
       					<td class="2-td2-right">电子邮箱：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-left">境内联系地址：</td>
       					<td class="2-td2-center" colspan="3">
       						省：<input type="text"/>&nbsp;&nbsp;
       						市：<input type="text"/>&nbsp;&nbsp;
       						区（县）：<input type="text"/>&nbsp;&nbsp;
						</td>
       					<td class="2-td2-right">邮政编码：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-left">联系电话：</td>
       					<td class="2-td2-center" colspan="3">
       						手机：<input type="text"/>&nbsp;&nbsp;
       						固定电话：<input type="text"/>
						</td>
       					<td class="2-td2-right">职业：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-left">职务：</td>
       					<td class="2-td2-center" colspan="3">
       						<input type="radio">高层
       						<input type="radio">中层
       						<input type="radio">普通
							&nbsp;&nbsp;&nbsp;&nbsp;（只选一）
						</td>
       					<td class="2-td2-right">学历：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-left">是否残疾人/烈属/孤老：</td>
       					<td class="2-td2-center" colspan="3">
       						<input type="checkbox">残疾
							<input type="checkbox">烈属
							<input type="checkbox">孤老
							<input type="checkbox">否
						</td>
       					<td class="2-td2-right">残疾等级情况：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-left">该栏仅由有境外所得税人填写</td>
       					<td class="2-td2-center">
       						<input type="radio" name="e">户籍所在地 
       						<input type="radio" name="e">经常居住地
       					</td>
       					<td class="2-td2-right" colspan="4">
       						省：<input type="text"/>&nbsp;&nbsp;
       						市：<input type="text"/>&nbsp;&nbsp;
       						区（县）：<input type="text"/>&nbsp;&nbsp;&nbsp;&nbsp;
       						邮政编码：<input type="text"/>
						</td>
       				</tr>
       				<tr>
       					<td class="2-td2-left" rowspan="7">该栏仅由投资者纳税人填写</td>
       					<td class="2-td2-center">投资者类型：</td>
       					<td class="2-td2-right" colspan="4">
       						<input type="checkbox">个体工商户
       						<input type="checkbox">个人独资企业投资者
       						<input type="checkbox">合伙企业人
       						<input type="checkbox">承包、承租经营者
       						<input type="checkbox">股东
       						<input type="checkbox">其他投资者
       						&nbsp;&nbsp;&nbsp;&nbsp;（可多选）
       					</td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" rowspan="4">被投资单位信息</td>
       					<td class="2-td2-right">名称：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       					<td class="2-td2-right">扣款义务人编码：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-right">地址：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       					<td class="2-td2-right">邮政编码：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-right">登记注册类型：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       					<td class="2-td2-right">行业：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-right">所得税征收方式：</td>
       					<td class="2-td2-right">
							<input type="radio">查账征收
       						<input type="radio">核定征收
							&nbsp;&nbsp;&nbsp;&nbsp;（只选一）
						</td>
       					<td class="2-td2-right">主管税务机关：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="5">以下由股东及其他投资者填写</td>
       				</tr>
       				<tr>
       					<td class="2-td2-center">公司股本（投资）总额</td>
       					<td class="2-td2-right" colspan="2"><input type="text"/></td>
       					<td class="2-td2-right">个人股本（投资）饿</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-left" rowspan="13">该栏仅由无住所纳税人填写</td>
       					<td class="2-td2-center">纳税人识别号：</td>
       					<td class="2-td2-right" colspan="4"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center">国籍（地区）：</td>
       					<td class="2-td2-right" colspan="2"><input type="text"/></td>
       					<td class="2-td2-right">出生地：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center">性别：</td>
       					<td class="2-td2-right" colspan="2"><input type="text"/></td>
       					<td class="2-td2-right">出生日期：</td>
       					<td class="2-td2-right">
							<input type="text" style="width: 70px;">&nbsp;年&nbsp;
							<input type="text" style="width: 70px;">&nbsp;月&nbsp;
							<input type="text" style="width: 70px;">&nbsp;日&nbsp;
						</td>
       				</tr>
       				<tr>
       					<td class="2-td2-center">劳动就业证号：</td>
       					<td class="2-td2-right" colspan="2"><input type="text"/></td>
       					<td class="2-td2-right">是否税收协定缔约国对方居民：</td>
       					<td class="2-td2-right">
       						<input type="radio"/>是
       						<input type="radio"/>否
       					</td>
       				</tr>
       				<tr>
       					<td class="2-td2-center">境内职务：</td>
       					<td class="2-td2-right" colspan="2"><input type="text"/></td>
       					<td class="2-td2-right">境外职务：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center">来华时间：</td>
       					<td class="2-td2-right" colspan="2"><input type="text"/></td>
       					<td class="2-td2-right">任职期限：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center">预计离境时间：</td>
       					<td class="2-td2-right" colspan="2"><input type="text"/></td>
       					<td class="2-td2-right">预计离境地点：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" rowspan="2">境内任职受雇单位</td>
       					<td class="2-td2-right">名称：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       					<td class="2-td2-right">扣款义务人编码：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-right">地址：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       					<td class="2-td2-right">邮政编码：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" rowspan="2">境内受聘签约地址</td>
       					<td class="2-td2-right">名称：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       					<td class="2-td2-right">扣款义务人编码：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-right">地址：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       					<td class="2-td2-right">邮政编码：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center">境外派遣单位</td>
       					<td class="2-td2-right">名称：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       					<td class="2-td2-right">地址：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center">支付地</td>
       					<td class="2-td2-right" colspan="2">
       						<input type="radio"/>境内支付
       						<input type="radio"/>境外支付
       						<input type="radio"/>境内、外同时支付
       						&nbsp;&nbsp;&nbsp;&nbsp;（只选一）
       					</td>
       					<td class="2-td2-right">境外支付国国别（地区）：</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="6">
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