<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<html>
<head>
<title>��������˰�걨</title>
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
    		<jsp:param name="name" value="��������˰�걨" />
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
       					<td class="1-td1" colspan="7">��������˰�걨</td>
       				</tr>
       				<tr>
       					<td class="2-td2-left" rowspan="2">Ͷ������Ϣ</td>
       					<td class="2-td2-center">������</td>
       					<td class="2-td2-right"><input type="text"/></td>
       					<td class="2-td2-right">���֤�����ͣ�</td>
       					<td class="2-td2-right"><input type="text"/></td>
       					<td class="2-td2-right">���֤�����룺</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center">��������������</td>
       					<td class="2-td2-right" colspan="3"><input type="text"/></td>
       					<td class="2-td2-right">��˰��ʶ��ţ�</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-left" rowspan="2">��Ͷ�ʵ�λ��Ϣ</td>
       					<td class="2-td2-center">���ƣ�</td>
       					<td class="2-td2-right" colspan="3"><input type="text"/></td>
       					<td class="2-td2-right">��˰��ʶ��ţ�</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center">���ͣ�</td>
       					<td class="2-td2-right" colspan="5">
       						<input type="checkbox">���幤�̻�
       						<input type="checkbox">�а������⾭Ӫ��
       						<input type="checkbox">���˶�����ҵ
       						<input type="checkbox">�ϻ���ҵ��
       					</td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��Ŀ</td>
       					<td class="2-td2-right">�д�</td>
       					<td class="2-td2-right" colspan="2">���</td>
       					<td class="2-td2-right">��������</td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">һ�������ܶ�</td>
       					<td class="2-td2-right">1</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       					<td class="2-td2-right" rowspan="51">
       						1����ƽ��ְ��������<input type="text">&nbsp;��<br/>
       						2�������ܶ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text">&nbsp;Ԫ<br/>
       						3��Ͷ����������&nbsp;&nbsp;&nbsp;&nbsp;<input type="text">&nbsp;��<br/>
       					</td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">�����ɱ�</td>
       					<td class="2-td2-right">2</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">Ӫҵ���ã�</td>
       					<td class="2-td2-right">3</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">������ã�</td>
       					<td class="2-td2-right">4</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">������ã�</td>
       					<td class="2-td2-right">5</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">Ӫҵ˰�𼰸��ӣ�</td>
       					<td class="2-td2-right">6</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">Ӫҵ��֧����</td>
       					<td class="2-td2-right">7</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">���������ܺ�</td>
       					<td class="2-td2-right">8</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">������˰�������Ӷ�</td>
       					<td class="2-td2-right">9</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">1�������涨��׼�۳�����Ŀ</td>
       					<td class="2-td2-right">10</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��1��ְ��������</td>
       					<td class="2-td2-right">11</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��2��ְ����������</td>
       					<td class="2-td2-right">12</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��3�����ᾭ��</td>
       					<td class="2-td2-right">13</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��4����Ϣ֧��</td>
       					<td class="2-td2-right">14</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��5��ҵ���д���</td>
       					<td class="2-td2-right">15</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��6�����Ѻ�ҵ��������</td>
       					<td class="2-td2-right">16</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��7�������͹�����ҵ����</td>
       					<td class="2-td2-right">17</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��8��ס��������</td>
       					<td class="2-td2-right">18</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��9��ʵ�ݱ��շ�</td>
       					<td class="2-td2-right">19</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��10���۾ɷ���</td>
       					<td class="2-td2-right">20</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��11�������ʲ�̯��</td>
       					<td class="2-td2-right">21</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��12���ʲ���ʧ</td>
       					<td class="2-td2-right">22</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��13������</td>
       					<td class="2-td2-right">23</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">2��������۳�����Ŀ</td>
       					<td class="2-td2-right">24</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��1���ʱ���֧��</td>
       					<td class="2-td2-right">25</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��2�������ʲ����á�����֧��</td>
       					<td class="2-td2-right">26</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��3��˰�����ɽ𡢷��𡢷���</td>
       					<td class="2-td2-right">27</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��4������֧�����ǽ����͹�����ҵ����</td>
       					<td class="2-td2-right">28</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��5���ֺ��¹���ʧ�⳥</td>
       					<td class="2-td2-right">29</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��6������ĸ���׼����</td>
       					<td class="2-td2-right">30</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��7��Ͷ���߹���н��</td>
       					<td class="2-td2-right">31</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��8���������޹ص�֧��</td>
       					<td class="2-td2-right">32</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">���У�Ͷ���߼�ͥ����</td>
       					<td class="2-td2-right">33</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">�ġ���˰�������ٶ�</td>
       					<td class="2-td2-right">34</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">1����ծ��Ϣ����</td>
       					<td class="2-td2-right">35</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">2������</td>
       					<td class="2-td2-right">36</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">�塢��ǰ����������</td>
       					<td class="2-td2-right">37</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��������˰�������������Ӫ����</td>
       					<td class="2-td2-right">38</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">�����ֲ���ǰ�����ʧ</td>
       					<td class="2-td2-right">39</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">�ˣ��������%</td>
       					<td class="2-td2-right">40</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">�ߣ�����۳�����������</td>
       					<td class="2-td2-right">41</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">�ˡ�Ͷ���߼�������</td>
       					<td class="2-td2-right">42</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">�š�Ӧ��˰���ö�</td>
       					<td class="2-td2-right">43</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">ʮ��˰�ʣ�%��</td>
       					<td class="2-td2-right">44</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">ʮһ������۳���</td>
       					<td class="2-td2-right">45</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">ʮ����Ӧ��˰��</td>
       					<td class="2-td2-right">46</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">��������˰��</td>
       					<td class="2-td2-right">47</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">ʮ����ȫ��Ӧ��˰��</td>
       					<td class="2-td2-right">48</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">�ӣ��ڳ�δ��˰��</td>
       					<td class="2-td2-right">49</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">����ȫ����Ԥ��˰��</td>
       					<td class="2-td2-right">50</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3">ʮ�ġ�Ӧ�����ˣ�˰��</td>
       					<td class="2-td2-right">51</td>
       					<td class="2-td2-right" colspan="2"><input type="text"></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="7">
       						<img src="<%=static_contextpath%>images/tuichu1.jpg" onmouseover="this.src='<%=static_contextpath%>images/tuichu2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/tuichu1.jpg'" alt="�˳�" onclick="doReturn();" style="cursor:hand">
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
				    <td width="20%" align="center" class="black9"><strong><font color="#0000FF">ע������</font></strong></td>
				     <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				  </tr>
				</table>
				<table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
				   <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
				      <td height="47"  >
				        <p style ="color:red">
				            &nbsp;&nbsp;&nbsp;&nbsp;���������˱��Ǹ��ݡ��л����񹲺͹���������˰��������ʵʩ�����͹�����ط��ɷ���涨��д�ģ�����ʵ�ġ������ġ��ɿ��ġ�<br>
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