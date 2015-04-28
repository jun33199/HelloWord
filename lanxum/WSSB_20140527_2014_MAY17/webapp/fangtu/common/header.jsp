<%@taglib uri="/WEB-INF/dc-html.tld" prefix="dchtml"%>

<%@ page import="com.ttsoft.common.model.UserData"%>
<%@ page import="com.ttsoft.common.util.SessionKey"%>

<%
	//由应用安全取得用户信息
	UserData userData = (UserData) session
			.getAttribute(SessionKey.USER_DATA);
	//取得总控退出登陆url
	String loginUrl = (String) session
			.getAttribute(SessionKey.PARAM_LOGOUT);
	//取得总控主菜单url
	String menuUrl = (String) session
			.getAttribute(SessionKey.PARAM_MENU);

	String static_contextpath_header = com.ttsoft.common.util.ResourceLocator
			.getStaticFilePath(request);

	String helpurl = request.getParameter("helpurl");
	if (helpurl == null || helpurl.equals("")) {
		helpurl = (String) session.getAttribute("sfgl-helpurl");
	} else {
		session.setAttribute("sfgl-helpurl", helpurl);
	}
	
	//取得当前页标题
	String head_title = (String)request.getAttribute("PAGE_TITLE"); //title值
	String position = (String)request.getAttribute("PAGE_POSITION");//当前位置 

	
%>

<%
	response.setHeader("pragma", "no-cache");
	response.setHeader("Cache-control", "no-cache, no-store");
	response.setHeader("Expires", "0");
%>

<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<SCRIPT LANGUAGE="jscript" src="../js/common.js"></SCRIPT>
<script language="JavaScript" id="viewreader" src=""></script>
<script language="JavaScript">
	returnStr = "<%=menuUrl%>";
</SCRIPT>

<title><%=head_title%></title>

<style type="text/css">
<!--
@import url(../css/text.css);
@import url(../css/error.css);
-->
</style>

<link href="<%=static_contextpath_header%>css/sssp.css" rel="stylesheet"
	type="text/css">
<link href="css/fangtu.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="<%=static_contextpath_header%>css/swdj.css" rel="stylesheet"
	type="text/css" />
<script language="javascript" src="gojs/gongyong.js"></script>
<script language="javascript" src="gojs/swapImages.js"></script>
<script language="javascript" src="gojs/menus.js"></script>
<script language="javascript" src="gojs/common.js"></script>
<script language="javascript" src="gojs/Const.js"></script>
<script language="javascript" src="gojs/DTColumn.js"></script>
<script language="javascript" src="gojs/DTColumn_Check.js"></script>
<script language="javascript" src="gojs/DynamicTable.js"></script>
<script language="javascript" src="gojs/DataAwareTable.js"></script>
<script language="javascript" src="gojs/gmit_selectcontrol.js"></script>
<script language="javascript" src="gojs/DynamicCard.js"></script>
<script language="javascript" src="gojs/DataAwareCard.js"></script>
<script language="javascript" src="gojs/DataAwarePage.js"></script>
<script language="javascript" src="gojs/inputchecker.js"></script>
<script language="javascript" src="gojs/fangtu.js"></script>
<script language="javascript" src="gojs/prototype.js"></script>
<script language="javascript" src="gojs/dl_calculate.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath_header%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="gojs/XmlBuild.js"></script>
<script language="javascript" src="gojs/fangtu_reg.js"></script>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onLoad="initPage();">


<table width="100%" border="0" cellpadding="0" cellspacing="0"
	background="<%=static_contextpath_header%>/images/q-top-bg-01.jpg">

	<tr>

		<td><img width="495" height="58"
			src="<%=static_contextpath_header%>/images/q-top-tu-01.jpg" /></td>

		<td align="right"><img width="284" height="58"
			src="<%=static_contextpath_header%>/images/q-top-tu-02.jpg" /></td>

	</tr>

</table>

<table width="100%" height="23" border="0" cellpadding="0"
	cellspacing="0"
	background="<%=static_contextpath_header%>images/q-top-bg-02.jpg"
	class="black9">
	<tr background="<%=static_contextpath_header%>images/q-top-bg-02.jpg">
		<td width="81%" class='titleBar'><%=position%></td>
		<td align="center" valign="bottom" nowrap>&nbsp;&nbsp;<a
			href="<%=loginUrl%>" onMouseOut="MM_swapImgRestore()"
			onMouseOver="MM_swapImage('Image8','','<%=static_contextpath_header%>images/t-zhuxiao2.jpg',1)"><img
			src="<%=static_contextpath_header%>images/t-zhuxiao1.jpg" alt="退出登录"
			name="Image8" width="30" height="16" border="0"></a>&nbsp;<a
			href="#" onMouseOut="MM_swapImgRestore()"
			onMouseOver="MM_swapImage('Image9','','<%=static_contextpath_header%>images/t-help2.jpg',1);MM_showHideLayers('Layer1','','show')">
		<img src="<%=static_contextpath_header%>images/t-help1.jpg" alt="帮助"
			name="Image9" width="30" height="16" border="0"></a>



		<div id="Layer1"
			style="Z-INDEX: 1; WIDTH: 100px; POSITION: absolute; HEIGHT: 10px; left: 87%; top: 100px; visibility: hidden; margin-right: 13% "
			onMouseOut="MM_showHideLayers('Layer1','','hide')">
		<table width="80" cellspacing=0
			onMouseOver="MM_showHideLayers('Layer1','','show')" class="black9">
			<tbody>
				<tr>
					<td class="2-td2-t-center"><a
						href="<%=static_contextpath_header%>help/sfgl<%=helpurl%>"
						target="_blank">帮助</a></td>
				</tr>
				<tr>
					<td class="2-td2-center">
					<div align=center><a href="javascript:popUp()">关于</a></div>
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		</td>
	</tr>
</table>

<script language="javascript">
    <!--

    function popUp() {
	    var server = '<%=request.getServerName()%>';
	    var port   = '<%=request.getServerPort()%>';
        props = window.open(staticFile + '//about/about.htm', 'poppage', 'toolbars=0,scrollbars=0,location=0,statusbara=0,menubars=0,resizable=0,width=500,height=267');
    }

    //-->
</script>
<script>
var swjgzzjgdm ="<%=userData.ssdwdm%>";
//坐落区县代码
var zlqx = swjgzzjgdm.substring(0,2);
//坐落区县名称
var zlqx_name = "";

	var arySelect_zlqx = [
		<bean:size id="size_zlqx" name="zlqx_list"/>
		<%
			Integer list_zlqx = (Integer) pageContext.getAttribute("size_zlqx");
			int counter_zlqx = 1; 
		%>
		<logic:iterate id="item" name="zlqx_list">
			[
			"<bean:write name="item" property="value"/>",
			"<bean:write name="item" property="label"/>"
			]
			<%
				if ( list_zlqx != null )
					if ( list_zlqx.intValue() > counter_zlqx++ ) out.println("\t,\n");
			%>
			
		</logic:iterate>
	];
	
	for ( var i=0; i < arySelect_zlqx.length; i++ ) {
		var ar = arySelect_zlqx[i];
		if ( ar[0] == zlqx ) {
			zlqx_name = ar[1];
			break;
		}
	}
	
		//构造一个坐落区县的option
	var option_zlqx = ''
	<logic:iterate id="item_zlqx" name="zlqx_list">
	+ '<option value="<bean:write name="item_zlqx" property="value"/>"><bean:write name="item_zlqx" property="label"/></option>'
	</logic:iterate>
	;
	
		//证件类型
	var arySelect_zjlx = [
		<bean:size id="size2" name="zjlx_list"/>
		<%
			Integer listsize_zjlx = (Integer) pageContext.getAttribute("size2");
			int counter_zjlx = 1; 
		%>
		<logic:iterate id="item" name="zjlx_list">
			[
			"<bean:write name="item" property="zjlxdm"/>",
			"<bean:write name="item" property="zjlxmc"/>"
			]
			<%
				if ( listsize_zjlx != null )
					if ( listsize_zjlx.intValue() > counter_zjlx++ ) out.println("\t,\n");
			%>
			
		</logic:iterate>
	];
	
		//构造一个证件类型的option
	var option_zjlx = ""
	<logic:iterate id="item" name="zjlx_list">
	+ '<option value="<bean:write name="item" property="zjlxdm"/>"><bean:write name="item" property="zjlxmc"/></option>'
	</logic:iterate>
	;
//构造一个是否代缴的option
var option_sfdj = ''
+ '<option value="1">否</option>'
+ '<option value="0">是</option>'
;	

//每平方米税额
var option_mpfmse=
	'<option value="30.00">30.00</option>'
	+'<option value="24.00">24.00</option>'
	+'<option value="18.00">18.00</option>'
	+'<option value="12.00">12.00</option>'
	+'<option value="3.00">3.00</option>'
	+'<option value="1.50">1.50</option>';
	
</script>
<dchtml:errors />
<div id="jsErrorDiv" class="error1"></div>