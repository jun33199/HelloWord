<%@page contentType="text/html; charset=GBK"%>
<%@ page import="com.ttsoft.common.util.SessionKey"%>
<html>
<head>
<title>核对税务登记信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
	//取得总控主菜单url
	String menuUrl = (String)session.getAttribute(SessionKey.PARAM_MENU);
	String xmlstr = (String)request.getAttribute("xml");
%>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
var mainURL = "<%=menuUrl%>";
var xml = new ActiveXObject("Microsoft.XMLDOM"); 
xml.loadXML('<%=xmlstr%>');
</script>
<style type="text/css">
#win {
	position: absolute;
	border: 1px solid #217AC1;
	width: 50%;
	height: 50%;
	top: 25%;
	left: 25%;
	background-color: white;
	display: none;
}

#bg {
	filter: alpha(opacity = 50);
	background-color: #99CCCC;
	position: absolute;
	top: 0px;
	left: 0px;
	display: none;
}
</style>
<script type="text/javascript" src="js/nsrjcxxhd.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>/js/dj.js"></script>
<script language="javascript" src="<%=static_contextpath%>/js/gmit_selectcontrol.js"></script>
<script language="javascript" src="<%=static_contextpath%>/js/inputchecker.js"></script>
</head>
<body onkeydown="if(event.keyCode == 13) return false;" bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">
<table width="97%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2>

    	<jsp:include page="../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="核对税务登记信息" />
		<jsp:param name="help_url" value="help/wssb/qyzhsb/gzsx/gzsx-000.htm"/>
    	</jsp:include>

    	<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
	  <tr>
	    <td valign="top">
	     <br>
	     <table cellspacing="0" width="100%" class="table-99">
		<!-- begin ========================================================================================--->
            <tr>
            	<td>
       			<table cellspacing="0" width="100%">
       				<tr height="40px">
       					<td class="1-td1">
       					<font style=" height: 40px; line-height: 40px; overflow: hidden; font-weight: bold; ">核对税务登记信息</font>
       					</td>
       				</tr>
       				
<tr>
	<td class="2-td2-center" align="center" style="background-color: #FFFFFF;">
		<table class=table-99 border=0 cellSpacing=0 align=center>
					<tr height="8px"><td></td></tr>
					<tr><td class="2-td1-center"><div align="left"><font style="font-size: 14px; height: 22px; line-height: 22px; overflow: hidden; font-weight: bold; color: #6666FF;">&gt;&gt;基础信息</font></div></td></tr>
       				<tr>
       					<td style="padding: 0">
       						<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
       							<tr>
			       					<th class="2-td2-left" width="120px" height="30px;"><div align="right">计算机代码&nbsp;</div></th>
			       					<td class="2-td2-center" width="120px"><div align="left">&nbsp;<font id="jsjdm" style="font-size: 20px; font-weight: bold; color: #0099FF;"></font></div></td>
			       					<th class="2-td2-right" width="120px"><div align="right">纳税人名称&nbsp;</div></th>
			       					<td class="2-td2-right"><div align="left">&nbsp;<font id="nsrmc" style="font-size: 18px; font-weight: bold; color: #0099FF;"></font></div></td>
		       					</tr>
       						</table>
       					</td>
       				</tr>
       				<tr>
       					<td style="padding: 0">
       						<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
       							<tr>
			       					<th class="2-td2-left" width="120px"><div align="right">纳税人识别号&nbsp;</div></th>
			       					<td class="2-td2-center"><div align="left">&nbsp;<font id="swdjzh"></font></div></td>
		       					</tr>
       						</table>
       					</td>
       				</tr>       				
       				<tr>
       					<td style="padding: 0">
       						<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
       							<tr>
			       					<th class="2-td2-left" width="120px"><div align="right">登记注册类型&nbsp;</div></th>
			       					<td class="2-td2-center"><div align="left">&nbsp;<font id="djzclxdm"></font></div></td>
			       					<th class="2-td2-right" width="120px"><div align="right">批准设立机关&nbsp;</div></th>
			       					<td class="2-td2-right"><div align="left">&nbsp;<font id="pzcljgdm"></font></div></td>
			       				</tr>
	       					</table>
       					</td>
       				</tr>
       				<tr>
						<td style="padding: 0">
							<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<th class="2-td2-left" width="120px"><div align="right">组织机构代码&nbsp;</div></th>
									<td class="2-td2-center"><div align="left">&nbsp;<font id="zzjgdm"></font></div></td>
									<th class="2-td2-right" width="150px"><div align="right">批准设立证明或文件号&nbsp;</div></th>
									<td class="2-td2-right"><div align="left">&nbsp;<font id="yyzzh"></font></div></td>
								</tr>
							</table>
						</td>
					</tr>
       				<tr>
       					<td style="padding: 0">
							<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
								<tr>
			       					<th class="2-td2-left" width="120px" nowrap="nowrap"><div align="right">开业（设立）日期&nbsp;</div></th>
			       					<td class="2-td2-center" style="min-width: 120px;"><div align="left">&nbsp;<font id="kyslrq"></font></div></td>
			       					<th class="2-td2-right" width="120px" nowrap="nowrap"><div align="right">生产经营期限&nbsp;</div></th>
			       					<td class="2-td2-right" width="12.5%"><div align="left">&nbsp;<font id="xcjyqx"></font></div></td>
			       					<th class="2-td2-right" width="100px" nowrap="nowrap"><div align="right">证照名称&nbsp;</div></th>
			       					<td class="2-td2-right" style="min-width: 120px;" nowrap="nowrap"><div align="left">&nbsp;<font id="zjmc"></font></div></td>
			       					<th class="2-td2-right" width="200px" nowrap="nowrap"><div align="right">证照号码&nbsp;</div></th>
			       					<td class="2-td2-right" width="220px"><div align="left">&nbsp;<font id="zjhm"></font></div></td>
		       					</tr>
	       					</table>
       					</td>
       				</tr>
       				<tr>
       					<td style="padding: 0">
							<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
								<tr>       			
			       					<th class="2-td2-left" width="120px"><div align="right">注册地址&nbsp;</div></th>
			       					<td class="2-td2-center"><div align="left">&nbsp;<font id="zcdz"></font></div></td>
			       					<th class="2-td2-right" width="100px"><div align="right">邮政编码&nbsp;</div></th>
			       					<td class="2-td2-right" width="100px"><input id="zcdzyb" type="text" style="width: 95%;"/></td>
			       					<th class="2-td2-right" width="100px"><div align="right">联系电话&nbsp;</div></th>
			       					<td class="2-td2-right" width="120px"><input id="zcdzlxdh" type="text" style="width: 95%;"/></td>
			       				</tr>
			       			</table>
			       		</td>
       				</tr>
       				<tr>
       					<td style="padding: 0">
							<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
								<tr>        		
			       					<th class="2-td2-left" width="120px"><div align="right">生产经营地址&nbsp;</div></th>
			       					<td class="2-td2-center"><input id="jydz" type="text" style="width: 99%;"/></td>
			       					<th class="2-td2-right" width="100px"><div align="right">邮政编码&nbsp;</div></th>
			       					<td class="2-td2-right" width="100px"><input id="jydzyb" type="text" style="width: 95%;"/></td>
			       					<th class="2-td2-right" width="100px"><div align="right">联系电话&nbsp;</div></th>
			       					<td class="2-td2-right" width="120px"><input id="jydzlxdm" type="text" style="width: 95%;"/></td>
			       				</tr>
			       			</table>
			       		</td>
       				</tr>
       				<tr>
       					<td style="padding: 0">
							<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
								<tr>         				
			       					<th class="2-td2-left" width="120px"><div align="right">核算方式&nbsp;</div></th>
									<td class="2-td2-center" width="20%"><div align="left">&nbsp;<font id="hsxsdm"></font></div></td>
			       					<th class="2-td2-right"><div align="right">从业人数&nbsp;</div></th>
			       					<td class="2-td2-right"><div align="left">&nbsp;
			       						<span style="text-decoration: underline;">&nbsp;<font id="cyrs"></font>&nbsp;</span>
			       						其中外籍人数
			       						<span style="text-decoration: underline;">&nbsp;<font id="wjrs"></font>&nbsp;</span></div>
			       					</td>
			       				</tr>
			       			</table>
			       		</td>
       				</tr>
       				<tr>
       					<td style="padding: 0">
							<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
								<tr>         				
			       					<th class="2-td2-left" width="120px"><div align="right">单位性质&nbsp;</div></th>
			       					<td class="2-td2-center"><div align="left">&nbsp;<font id="dwxz"></font></div></td>
			       				</tr>
			       			</table>
			       		</td>
       				</tr>
       				<tr>
       					<td style="padding: 0">
							<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
								<tr>          				
			       					<th class="2-td2-left" width="120px"><div align="right">网站网址&nbsp;</div></th>
			       					<td class="2-td2-center"><div align="left">&nbsp;<font id="qyzy"></font></div></td>
			       					<th class="2-td2-right" width="120px"><div align="right">国标行业&nbsp;</div></th>
			       					<td class="2-td2-right"><div align="left">&nbsp;<font id="gjbzhydm"></font></div></td>
			       				</tr>
			       			</table>
			       		</td>
       				</tr>
       				<tr>
       					<td style="padding: 0">
							<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
								<tr>        			
			       					<th class="2-td2-left" width="120px"><div align="right">经营范围&nbsp;</div></th>
			       					<td class="2-td2-center"><div align="left">&nbsp;<font id="jyfw"></font></div></td>
			       				</tr>
			       			</table>
			       		</td>
       				</tr>       				
       				<tr>
       					<td style="padding: 0">
							<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
								<tr>         				
			       					<th class="2-td2-left" width="120px"><div align="right">适用会计制度&nbsp;</div></th>
			       					<td class="2-td2-center"><div align="left">&nbsp;<font id="kjzddm"></font></div></td>
			       				</tr>
			       			</table>
			       		</td>
       				</tr>
					<tr>
       					<td style="padding: 0">
							<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
								<tr> 
			       					<th class="2-td2-left" width="25%">税务代理人名称</th>
			       					<th class="2-td2-center" width="25%">纳税人识别号</th>
			       					<th class="2-td2-right" width="25%">联系电话</th>
			       					<th class="2-td2-right" width="25%">电子邮箱</th>
			       				</tr>
			       			</table>
			       		</td>
       				</tr>
       				<tr>
       					<td style="padding: 0">
							<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
								<tr> 
			       					<td class="2-td2-left" width="25%">&nbsp;<input id="swdlmc" type="text" style="width: 90%;" />&nbsp;<font color="red">*</font></td>
			       					<td class="2-td2-center" width="25%">&nbsp;<input id="swdlswdjzh" type="text" style="width: 90%;" />&nbsp;<font color="red">*</font></td>
			       					<td class="2-td2-right" width="25%">&nbsp;<input id="swdlgddh" type="text" style="width: 90%;" /></td>
			       					<td class="2-td2-right" width="25%">&nbsp;<input id="swdldzyx" type="text" style="width: 90%;" /></td>
			       				</tr>
			       			</table>
			       		</td>
       				</tr>
       				<tr>
       					<td style="padding: 0">
							<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
								<tr> 
			       					<th class="2-td2-left" width="20%">注册资本或投资总额</th>
			       					<th class="2-td2-center" width="15%">币种</th>
			       					<th class="2-td2-right" width="15%">金额</th>
			       					<th class="2-td2-right" width="12.5%">币种</th>
			       					<th class="2-td2-right" width="12.5%">金额</th>
			       					<th class="2-td2-right" width="12.5%">币种</th>
			       					<th class="2-td2-right" width="12.5%">金额</th>
			       				</tr>
								<tr> 
									<td class="2-td2-left" width="20%">&nbsp;<font id="zczbje"></font></td>
			       					<td class="2-td2-center" width="15%">&nbsp;<font id="zczbbzdm_one"></font></td>
			       					<td class="2-td2-right" width="15%">&nbsp;<font id="zczbje_one"></font></td>
			       					<td class="2-td2-right" width="12.5%">&nbsp;<font id="zcbbzdm_two"></font></td>
			       					<td class="2-td2-right" width="12.5%">&nbsp;<font id="zczbje_two"></font></td>
			       					<td class="2-td2-right" width="12.5%">&nbsp;<font id="zcbbzdm_three"></font></td>
			       					<td class="2-td2-right" width="12.5%">&nbsp;<font id="zczbje_three"></font></td>
			       				</tr>
			       			</table>
			       		</td>
       				</tr>     
       				<tr>
       					<td style="padding: 0">
							<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
								<tr> 
			       					<th class="2-td2-left" width="20%">自然人投资比例（%）</th>
			       					<td class="2-td2-center" width="13%"><div align="left">&nbsp;<font id="zrrtzblhj"></font></div></td>
			       					<th class="2-td2-right" width="20%">外资投资比例（%）</th>
			       					<th class="2-td2-right" width="13%"><div align="left">&nbsp;<font id="wzztzblhj"></font></div></th>
			       					<th class="2-td2-right" width="20%">国有投资比例（%）</th>
			       					<td class="2-td2-right" width="14%"><div align="left">&nbsp;<font id="gytzblhj"></font></div></td>
			       				</tr>
			       			</table>
			       		</td>
       				</tr>  				
		</table>

		<table class=table-99 border=0 cellSpacing=0 align=center>
		<tr height="10px"><td></td></tr>		
		<tr><td class="2-td1-center"><div align="left"><font style="font-size: 14px; height: 22px; line-height: 22px; overflow: hidden; font-weight: bold; color: #6666FF;">&gt;&gt;企业人员</font></div></td></tr>
       				<tr>
       					<td style="padding: 0">
							<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
								<tr>          				
			       					<th class="2-td2-left" rowspan="2" width="220px">项目/内容/联系人</th>
									<th class="2-td2-center" rowspan="2" width="120px">姓名</th>
									<th class="2-td2-right" colspan="2">身份证件</th>
									<th class="2-td2-right" rowspan="2" width="120px">固定电话</th>
									<th class="2-td2-right" rowspan="2" width="120px">移动电话</th>
									<th class="2-td2-right" rowspan="2" width="120px">电子邮箱</th>
								</tr>
								<tr>
			       					<th class="2-td2-right" style="min-width: 250px;">种类</th>
									<th class="2-td2-right" style="min-width: 250px;">号码</th>
								</tr>
			       				<tr>
			       					<th class="2-td2-left" width="220px">法定代表人<br/>（负责人）</th>
			       					<td class="2-td2-center">&nbsp;<font id="frxm"></font></td>
									<td class="2-td2-right">&nbsp;<font id="frzjlxdm"></font></td>
									<td class="2-td2-right">&nbsp;<font id="frzjhm"></font></td>
									<td class="2-td2-right" width="120px"><input type="text" id="frgddh" style="width: 95%;"/></td>
									<td class="2-td2-right" width="120px"><input type="text" id="fryddh" style="width: 95%;"/></td>
									<td class="2-td2-right" width="180px"><input type="text" id="frdzyx" style="width: 95%;"/></td>
			       				</tr>
			       				<tr>
			       					<th class="2-td2-left" width="220px">财务负责人</th>
			       					<td class="2-td2-center"><input type="text" id="cwfzrxm" style="width: 95%;"/></td>
									<td class="2-td2-right"><select id="cwfzrzjlxdm" style="width: 95%;"><option value="0">--请选择--</option></select></td>
									<td class="2-td2-right"><input type="text" id="cwfzrzjhm" style="width: 95%;"/></td>
									<td class="2-td2-right" width="120px"><input type="text" id="cwfzrgddh" style="width: 95%;"/></td>
									<td class="2-td2-right" width="120px"><input type="text" id="cwfzryddh" style="width: 95%;"/></td>
									<td class="2-td2-right" width="180px"><input id="cwfzrdzyx" type="text" style="width: 95%;"/></td>
			       				</tr>
			       				<tr>
			       					<th class="2-td2-left" width="220px">办税人</th>
			       					<td class="2-td2-center"><input id="bsrxm" type="text" style="width: 95%;" /></td>
									<td class="2-td2-right"><select id="bsrzjlxdm" style="width: 95%;"><option>--请选择--</option></select></td>
									<td class="2-td2-right"><input id="bsrzjhm" type="text" style="width: 95%;" /></td>
									<td class="2-td2-right" width="120px"><input id="bsrgddh" type="text" style="width: 95%;"/></td>
									<td class="2-td2-right" width="120px"><input id="bsryddh" type="text" style="width: 95%;"/></td>
									<td class="2-td2-right" width="180px"><input id="bsrdzyx" type="text" style="width: 95%;"/></td>
			       				</tr>
							</table>
						</td>
       				</tr>
		</table>

		<table class=table-99 border=0 cellSpacing=0 align=center>	
		<tr height="10px"><td></td></tr>				
		<tr><td class="2-td1-center"><div align="left"><font style="font-size: 14px; height: 22px; line-height: 22px; overflow: hidden; font-weight: bold; color: #6666FF;">&gt;&gt;投资方</font></div></td></tr>	
       				<tr>
       					<td style="padding: 0">
							<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
								<tr> 
			       					<th class="2-td2-left">投资方名称</th>
			       					<th class="2-td2-center">投资方经济性质</th>
			       					<th class="2-td2-right">投资比例（%）</th>
			       					<th class="2-td2-right">证件种类</th>
			       					<th class="2-td2-right" colspan="2">证件号码</th>
			       					<th class="2-td2-right" colspan="2">国籍或地址</th>
			       				</tr>
			       				<tbody id="tzfTbody">
			       				</tbody>
			       				<tr>
			       					<td class="2-td2-center" colspan="8">
			       						<b>
			       							<a href="javascript:void(0)" onclick="tzfPagination('previous');return false;" style="text-decoration:none">上一页</a>
			       							<a href="javascript:void(0)" onclick="tzfPagination('next');return false;" style="text-decoration:none">下一页</a>
			       							第<font id="tzfPageSize"></font>页 &nbsp;&nbsp;
			       							共<font id="tzfTotalPages"></font>页
			       						</b>
			       					</td>			       					
			       				</tr>
			       			</table>
			       		</td>
       				</tr>
       		</table>

			<table class=table-99 border=0 cellSpacing=0 align=center>		
			<tr height="10px"><td></td></tr>		
			
			<tr><td class="2-td1-center"><div align="left"><font style="font-size: 14px; height: 22px; line-height: 22px; overflow: hidden; font-weight: bold; color: #6666FF;">&gt;&gt;分支机构</font></div></td></tr>
					<tr>
       					<td style="padding: 0">
							<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
								<tr> 
			       					<th class="2-td2-center">纳税人识别号</th>
			       					<th class="2-td2-right">分支机构名称</th>
			       					<th class="2-td2-right">注册地址</th>
			       				</tr>
			       				<tbody id="fzjgTbody">
			       				</tbody>
			       				<tr>
			       					<td class="2-td2-center" colspan="3">
			       						<b>
			       							<a href="javascript:void(0)" onclick="fzjgPagination('previous');return false;" style="text-decoration:none">上一页</a>
			       							<a href="javascript:void(0)" onclick="fzjgPagination('next');return false;" style="text-decoration:none">下一页</a>
			       							第<font id="fzjgPageSize"></font>页 &nbsp;&nbsp;
			       							共<font id="fzjgTotalPages"></font>页
			       						</b>
			       					</td>			       					
			       				</tr>
			       			</table>
			       		</td>
       				</tr>
       		</table>

			<table class=table-99 border=0 cellSpacing=0 align=center>		
			<tr height="10px"><td></td></tr>		
			<tr><td class="2-td1-center"><div align="left"><font style="font-size: 14px; height: 22px; line-height: 22px; overflow: hidden; font-weight: bold; color: #6666FF;">&gt;&gt;总机构</font></div></td></tr>       		
       				<tr>
       					<td style="padding: 0">
							<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
								<tr> 
			       					<th class="2-td2-left" width="120px"><div align="right">总机构名称&nbsp;</div></th>
			       					<td class="2-td2-center"><div align="left">&nbsp;<font id="zjgnsrmc"></font></div></td>
			       					<th class="2-td2-right" width="120px"><div align="right">纳税人识别号&nbsp;</div></th>
			       					<td class="2-td2-right"><div align="left">&nbsp;<font id="zjgswdjzh"></font></div></td>
			       				</tr>
			       			</table>
			       		</td>
       				</tr>
       				<tr>
       					<td style="padding: 0">
							<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
								<tr>
			       					<th class="2-td2-left" width="120px"><div align="right">注册地址&nbsp;</div></th>
			       					<td class="2-td2-center"><div align="left">&nbsp;<font id="zjgscjydz"></font></div></td>
			       				</tr>
								<tr>
			       					<th class="2-td2-left" width="120px"><div align="right">经营范围&nbsp;</div></th>
			       					<td class="2-td2-center"><div align="left">&nbsp;<font id="zjgjyfw"></font></div></td>
			       				</tr>			       				
			       			</table>
			       		</td>
       				</tr>
					<tr>
       					<td colspan="2" style="padding: 0">
							<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
								<tr>
			       					<th class="2-td2-left" width="120px"><div align="right">法定代表人姓名&nbsp;</div></th>
			       					<td class="2-td2-center"><div align="left">&nbsp;<font id="zjgfrdbxm"></font></div></td>
			       					<th class="2-td2-right" width="120px"><div align="right">联系电话&nbsp;</div></th>
			       					<td class="2-td2-right"><div align="left">&nbsp;<font id="zjgscjydzlxdh"></font></div></td>
			       					<th class="2-td2-right" width="120px"><div align="right">注册地址邮政编码&nbsp;</div></th>
			       					<td class="2-td2-right"><div align="left">&nbsp;<font id="zjgzcdzyb"></font></div></td>
			       				</tr>
			       			</table>
			       		</td>
			       	</tr>       				
		</table>

		<table class=table-99 border=0 cellSpacing=0 align=center>
		<tr height="10px"><td></td></tr>					
		<tr><td class="2-td1-center"><div align="left"><font style="font-size: 14px; height: 22px; line-height: 22px; overflow: hidden; font-weight: bold; color: #6666FF;">&gt;&gt;代扣代缴</font></div></td></tr>  		 				
       				<tr>
       					<td style="padding: 0">
							<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
			       				<tr>
			       					<th class="2-td2-left" id="dkdjQkTd" width="20%">代扣代缴代收代缴税款业务情况</th>
			       					<td>
										<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
											<tr>
						       					<th class="2-td2-center" width="40%">代扣代缴、代收代缴税款业务内容</th>
						       					<th class="2-td2-right" width="40%">代扣代缴、代收代缴税种</th>
											</tr>
						       				<tbody id="dkdjTbody">
						       				</tbody>
										</table>
			       					</td>
			       				</tr>
			       				<tr>
			       					<td class="2-td2-center" colspan="2">
			       						<b>
			       							<a href="javascript:void(0)" onclick="dkdjPagination('previous');return false;" style="text-decoration:none">上一页</a>
			       							<a href="javascript:void(0)" onclick="dkdjPagination('next');return false;" style="text-decoration:none">下一页</a>
			       							第<font id="dkdjPageSize"></font>页 &nbsp;&nbsp;
			       							共<font id="dkdjTotalPages"></font>页
			       						</b>
			       					</td>
			       				</tr>		       				
					       </table>
					     </td>
       				</tr>
       				<tr height="8px"><td></td></tr>	
		</table>
		</td>
		</tr> 				
       				<tr height="40px">
       					<td class="2-td2-center">
				    		<input type="image" value="保存"
				                 onMouseOver="MM_swapImage('baocun1','','<%=static_contextpath%>images/baocun2.jpg',1)"
				                 onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/baocun1.jpg"
				                 onclick = "save();return false;"
				                 width="79" height="22" id="baocun1" alt="保存">&nbsp;&nbsp;&nbsp;		                 
				    		<input type="image" value="退出"
				                 onMouseOver="MM_swapImage('tuichu1','','<%=static_contextpath%>images/tuichu2.jpg',1)"
				                 onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/tuichu1.jpg"
				                 onclick = "tuichu();return false;"
				                 width="79" height="22" id="tuichu1" alt="退出">				                
    					</td>
       				</tr>
       			</table>
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
				            &nbsp;&nbsp;&nbsp;&nbsp;说明：财务负责人、办税人员、生产经营地址、联系电话、邮政编码、电子邮箱、税务代理人名称及其纳税人识别号发生变化的，由纳税人在网上主动变更，不需提供变更材料；涉及其他变更事项的，应到主管税务机关提交相关资料办理变更手续。<br>
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
	<%@ include file="../include/bottom.jsp" %>
  </td>
</tr>
 </table>
</body>
</html>