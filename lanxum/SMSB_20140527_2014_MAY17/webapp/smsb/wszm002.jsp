<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="com.ttsoft.bjtax.smsb.wszm.web.WszmForm"%>
<%@ page import="com.ttsoft.framework.util.StringUtil"%>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="com.ttsoft.framework.util.Currency"%>


<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
        WszmForm form = (WszmForm)request.getAttribute("wszmForm");
        ArrayList fpList=form.getDyDataList();
        DecimalFormat deFormat = new DecimalFormat("#0.00");
        double zsumSjse=0.00;
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>北京市地方税务局完税证明打印预览</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/reader.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="init()">
<%@ include file="./include/header.jsp"%>
<logic:equal name="wszmForm" property="bccgbz" value="1">
<applet name="printer" codebase="<%=static_contextpath%>printer" code="com.ttsoft.bjtax.webprint.SSDZZZZYWSZMPrinter" width="0" height="0" archive="vprinter.jar">
</applet>
</logic:equal>

 <!--如果是需要分票的缴款书 -->
 <logic:equal name="wszmForm" property="sspzlxdm" value="<%=CodeConstant.WSZM_JKS%>">
 <% 
  //System.out.println("页面fpList.size()="+fpList.size());
  String  tempHeadWszh=form.getHeadWszh();
  //System.out.println("页面tempHeadWszh="+tempHeadWszh);
  
  for(int i=0;i<fpList.size();i++){
	  ArrayList eveList=(ArrayList)fpList.get(i); 
 %>
 <table width="80%" border="2" align="center" cellpadding="0" cellspacing="4" bordercolor="#9DB9D2">
        <tr>
          <td height="147" bgcolor="#F0F0F0">
          <div align="center">
        <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="28%" align="right">&nbsp;</td>
            <td width="41%" align="right"> <div align="center"><span class="black9"><font size="5" color="#999999">中华人民共和国</font> 
                </span> </div>
              <p align="center"><font color="#2C556D"> 
                税&nbsp;收&nbsp;完&nbsp;税&nbsp;证&nbsp;明</font></p></td>
            <td width="31%" valign="baseline"><p>&nbsp;</p>
               <%
               String ndzb_t="";
               String wspzh_t="";
               if((form.getBccgbz()!=null&&form.getBccgbz().equals("1"))||(form.getBccgbz()!=null&&form.getBccgbz().equals("0")&&form.getActionType().equals("Success"))){
            	   String wszhs[]=form.getWszmwszhs().split("<#r#>");
            	   String ndzbs[]=form.getWszmndzbs().split("<#r#>");
            	   wspzh_t=wszhs[i];
            	   ndzb_t=ndzbs[i];
            	   // System.out.println("1ym##################="+wspzh_t);
					//System.out.println("1ym##################="+ndzb_t);
               }else{
            	   String tHeadWszh=Integer.toString(Integer.parseInt(tempHeadWszh)+i);
            	   if(tHeadWszh.length()>=tempHeadWszh.length()){
            		   wspzh_t=tHeadWszh;
            	   }else{
            		   StringBuffer str0=new StringBuffer();
            		   int xc=tempHeadWszh.length()-tHeadWszh.length();
            		   for(int t=0;t<xc;t++){
            			   str0.append("0");
            		   }
            		   wspzh_t=str0.toString()+tHeadWszh;
            	   }
            		   ndzb_t=form.getNdzb();
               }
               %>
              <p><font color="#2C556D" size="">(<%=ndzb_t%>)京地证<font color="#d32e2e"><%=wspzh_t%></font></font></p>
              <p>&nbsp;</p></td>
          </tr>
        </table>
      </div>
            
      <p align="center" class="black9"><font color="#999999"></font></p></td>
        </tr>
        <tr>
          <td><table width="100%" cellpadding="4" cellspacing="0" class="black9" id="AutoNumber4" style="border-collapse: collapse">
        <tr> 
          <td width="33%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">&nbsp;&nbsp;</td>
          <td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"><font color="#2C556D">填发日期：</font><font color="#d32e2e"><bean:write name="wszmForm" property="tfrq" /></font></td>
          <td width="32%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"><font color="#2C556D">税务机关：</font><font color="#d32e2e"><bean:write name="wszmForm" property="swjgzzjgmc" /></font></td>
        </tr>
      </table>
            
      <table width="100%" height="30" border="2" cellpaddng="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber3" style="border-collapse: collapse">
        <tr> 
          <td width="90" height="30" align="center" nowrap>纳税人识别号</td>
          <td width="313" height="30"><font color="#d32e2e"><bean:write name="wszmForm" property="nsrsbh" /></font></td>
          <td width="87" height="30" align="center" nowrap><font color="#2C556D">纳税人名称</font></td>
          <td width="252" height="30"><font color="#d32e2e"><bean:write name="wszmForm" property="nsrmc" /></font></td>
        </tr>
      </table>
      <table width="100%" height="305" border="2" cellpadding="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber2" style="border-collapse: collapse">
        <tr> 
          <td width="160" style="padding-top: 0" align="center" nowrap>原凭证号</td>
          <td width="160" style="padding-top: 0" align="center" nowrap>税&nbsp;&nbsp;种</td>
          <td width="160" style="padding-top: 0" align="center" nowrap>品目名称</td>
          <td width="170" style="padding-top: 0" height="34" align="center" nowrap>税款所属时期</td>
          <td width="160" style="padding-top: 0" height="34" align="center" nowrap>入(退)库日期</td>
          <td width="160" style="padding-top: 0" height="34" align="center" nowrap>实缴(退)金额</td>
        </tr>
        
        <%
        //设置格式化数字
        
        double sumSjse=0.00;
        for(int j=0;j<eveList.size();j++){
        	HashMap map=(HashMap)eveList.get(j);
        	sumSjse=sumSjse+StringUtil.getDouble((String)map.get("sjse"), 0.00);
        %>
        <tr>
  
          <td style="padding-top: 0"><font color="#d32e2e"><%=map.get("ywszh")%><%=map.get("ypzh")%></font></td>
          <td style="padding-top: 0"><font color="#d32e2e"><%=map.get("szmc")%></font></td>
          <td style="padding-top: 0"><font color="#d32e2e"><%=map.get("szsmmc")%></font></td>
          <td style="padding-top: 0" ><font color="#d32e2e"><%=map.get("skssrq")%></font></td>
          <td style="padding-top: 0" ><font color="#d32e2e"><%=map.get("ypzrkrq")%></font></td>
          <td style="padding-top: 0" ><div align="right"><font color="#d32e2e"><%=map.get("sjse")%></font></div></td>
          
        </tr>
        <%
        }
        zsumSjse=zsumSjse+sumSjse;
        %>
                
        <tr> 
          <td width="80" style="padding-top: 0" height="30"><font color="#2C556D">金额合计</font></td>
          <td colspan="4" style="padding-top: 0" height="30"><font color="#2C556D">（大写）</font><font color="#d32e2e"><%=Currency.convert(sumSjse)%></font></td>
          <td style="padding-top: 0" height="30" nowrap><div align="right"><font color="#d32e2e">￥<%=deFormat.format(sumSjse)%></font></div></td>
        </tr>
      </table>
      <table width="100%" border="2" cellpadding="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber5" style="border-collapse: collapse">
        <tr> 
          <td width="20%" height="103"><font color="#2C556D">税务机关： </font> 
            <p><font color="#d32e2e"><bean:write name="wszmForm" property="swjgzzjgmc" />&nbsp;</font>
            <p></td>
          <td width="20%"><font color="#2C556D">填票人：</font> 
          	<p><font color="#d32e2e"><bean:write name="wszmForm" property="lrr" />&nbsp;</font>
          	<p></td>
          <td width="60%" ><font color="#2C556D">备注：</font>
            <p><div align="left" id="bz<%=i%>">&nbsp;</div>
            <p>&nbsp;</td>
        </tr>
      </table></td>
        </tr>
      </table>
 <%
  }
 %>
 
 </logic:equal>
    
    
 <!--如果不是需要分票的缴款书 -->   
 <logic:notEqual name="wszmForm" property="sspzlxdm" value="<%=CodeConstant.WSZM_JKS%>">
    <table width="80%" border="2" align="center" cellpadding="0" cellspacing="4" bordercolor="#9DB9D2">
        <tr>
          <td height="147" bgcolor="#F0F0F0">
          <div align="center">
        <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="28%" align="right">&nbsp;</td>
            <td width="41%" align="right"> <div align="center"><span class="black9"><font size="5" color="#999999">中华人民共和国</font> 
                </span> </div>
              <p align="center"><font color="#2C556D"> 
                税&nbsp;收&nbsp;完&nbsp;税&nbsp;证&nbsp;明</font></p></td>
            <td width="31%" valign="baseline"><p>&nbsp;</p>
              <p><font color="#2C556D" size="">(<bean:write name="wszmForm" property="ndzb" />)京地证<font color="#d32e2e"><bean:write name="wszmForm" property="headWszh" /></font></font></p>
              <p>&nbsp;</p></td>
          </tr>
        </table>
      </div>
            
      <p align="center" class="black9"><font color="#999999"></font></p></td>
        </tr>
        <tr>
          <td><table width="100%" cellpadding="4" cellspacing="0" class="black9" id="AutoNumber4" style="border-collapse: collapse">
        <tr> 
          <td width="33%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">&nbsp;&nbsp;</td>
          <td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"><font color="#2C556D">填发日期：</font><font color="#d32e2e"><bean:write name="wszmForm" property="tfrq" /></font></td>
          <td width="32%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"><font color="#2C556D">税务机关：</font><font color="#d32e2e"><bean:write name="wszmForm" property="swjgzzjgmc" /></font></td>
        </tr>
      </table>
            
      <table width="100%" height="30" border="2" cellpaddng="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber3" style="border-collapse: collapse">
        <tr> 
          <td width="90" height="30" align="center" nowrap>纳税人识别号</td>
          <td width="313" height="30"><font color="#d32e2e"><bean:write name="wszmForm" property="nsrsbh" /></font></td>
          <td width="87" height="30" align="center" nowrap><font color="#2C556D">纳税人名称</font></td>
          <td width="252" height="30"><font color="#d32e2e"><bean:write name="wszmForm" property="nsrmc" /></font></td>
        </tr>
      </table>
      <table width="100%" height="305" border="2" cellpadding="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber2" style="border-collapse: collapse">
        <tr> 
          <td width="160" style="padding-top: 0" align="center" nowrap>原凭证号</td>
          <td width="160" style="padding-top: 0" align="center" nowrap>税&nbsp;&nbsp;种</td>
          <td width="160" style="padding-top: 0" align="center" nowrap>品目名称</td>
          <td width="170" style="padding-top: 0" height="34" align="center" nowrap>税款所属时期</td>
          <td width="160" style="padding-top: 0" height="34" align="center" nowrap>入(退)库日期</td>
          <td width="160" style="padding-top: 0" height="34" align="center" nowrap>实缴(退)金额</td>
        </tr>
        <!--The loop begin-->
        <bean:define id="dataList" name="wszmForm" property="dyDataList"/>
        <logic:iterate indexId="index" name="dataList" id="itemMap">
        <bean:define id="item" name="itemMap"/>
        <tr>
          <td style="padding-top: 0"><font color="#d32e2e"><ttsoft:write name="item" property="ywszh"/><ttsoft:write name="item" property="ypzh"/></font></td>
          <td style="padding-top: 0"><font color="#d32e2e"><ttsoft:write name="item" property="szmc"/></font></td>
          <td style="padding-top: 0"><font color="#d32e2e"><ttsoft:write name="item" property="szsmmc"/></font></td>
          <td style="padding-top: 0" ><font color="#d32e2e"><ttsoft:write name="item" property="skssrq" /></font></td>
          <td style="padding-top: 0" ><font color="#d32e2e"><ttsoft:write name="item" property="ypzrkrq"/></font></td>
          <td style="padding-top: 0" ><div align="right"><font color="#d32e2e"><ttsoft:write name="item" property="sjse"/></font></div></td>
        </tr>
		</logic:iterate>
        <!--The end of loop-->
        
        <tr> 
          <td width="80" style="padding-top: 0" height="30"><font color="#2C556D">金额合计</font></td>
          <td colspan="4" style="padding-top: 0" height="30"><font color="#2C556D">（大写）</font><font color="#d32e2e"><bean:write name="wszmForm" property="hjjedx2" /></font></td>
          <td style="padding-top: 0" height="30" nowrap><div align="right"><font color="#d32e2e">￥<bean:write name="wszmForm" property="hjje2" /></font></div></td>
        </tr>
      </table>
      <table width="100%" border="2" cellpadding="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber5" style="border-collapse: collapse">
        <tr> 
          <td width="20%" height="103"><font color="#2C556D">税务机关： </font> 
            <p><font color="#d32e2e"><bean:write name="wszmForm" property="swjgzzjgmc" />&nbsp;</font>
            <p></td>
          <td width="20%"><font color="#2C556D">填票人：</font> 
          	<p><font color="#d32e2e"><bean:write name="wszmForm" property="lrr" />&nbsp;</font>
          	<p></td>
          <td width="60%" ><font color="#2C556D">备注：</font>
            <p><div align="left" id="bz">&nbsp;</div>
            <p>&nbsp;</td>
        </tr>
      </table></td>
        </tr>
      </table>
 </logic:notEqual>   
      
      
<br>

<html:form action="/webapp/smsb/wszmAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden name="wszmForm" property="sspzlxdm" />
<html:hidden name="wszmForm" property="headWszh" />
<html:hidden name="wszmForm" property="ndzb" />
<html:hidden name="wszmForm" property="pzzldm" />
<html:hidden name="wszmForm" property="hjje" />
<html:hidden name="wszmForm" property="ysphm" />
<html:hidden name="wszmForm" property="ypzh" />
<html:hidden name="wszmForm" property="ywszh" />
<html:hidden name="wszmForm" property="ypzzldm" />
<html:hidden name="wszmForm" property="yndzb" />
<html:hidden name="wszmForm" property="swjgzzjgdm" />
<html:hidden name="wszmForm" property="nsrsbh" />
<html:hidden name="wszmForm" property="nsrmc" />
<html:hidden name="wszmForm" property="tfrq" />
<html:hidden name="wszmForm" property="bccgbz" />
<html:hidden name="wszmForm" property="wszmsjm" />
<html:hidden name="wszmForm" property="wszmwszhs" />
<html:hidden name="wszmForm" property="wszmndzbs" />
                  <div align="center">
                  	<logic:notEqual name="wszmForm" property="actionType" value="Success">
						<input type="image" tabIndex="-1" src="<%=static_contextpath%>images/dy-p1.jpg" onmouseover="this.src='<%=static_contextpath%>images/dy-p2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/dy-p1.jpg'" alt="打印" onclick="doSubmitForm('SavePrint');return false;" style="cursor:hand">
					</logic:notEqual>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="image" accesskey="e" tabIndex="-1" onclick="window.close();return false;" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('tuichu1','','<%=static_contextpath%>images/guanbi2.jpg',1)" alt="关闭" src="<%=static_contextpath%>images/guanbi1.jpg" width="79" height="22" id="tuichu1" style="cursor: hand">
                  </div>
                  <br>
</html:form>

	
<%@ include file="./include/footer.jsp"%>

</body>
<script type="text/javascript">

function init()
{
 <logic:equal name="wszmForm" property="sspzlxdm" value="<%=CodeConstant.WSZM_JKS%>">
	var wszmsjmOBJ = document.all.wszmsjm;
	var sjm="";
	var zsjm="";
	
	var dycs = '<bean:write name="wszmForm" property="dycs" />';
	var ysphm = '<bean:write name="wszmForm" property="ysphm" />';
	var bz = "";
	var sspzlxdm = '<bean:write name="wszmForm" property="sspzlxdm" />'; //凭证来源
	
    <%
    String sumSjseStr="";
    //double zsumSjse=0.00;
	//System.out.println("##k1####fpList.size()="+fpList.size());
    for(int i=0;i<fpList.size();i++){
    	ArrayList eveList=(ArrayList)fpList.get(i); 
         double sumSjse=0.00;
         for(int j=0;j<eveList.size();j++){
         	HashMap map=(HashMap)eveList.get(j);
              	sumSjse=sumSjse+StringUtil.getDouble((String)map.get("sjse"), 0.00);
         }
        // zsumSjse=zsumSjse+sumSjse;
         
         if(sumSjseStr.length()>0){
        	 sumSjseStr=sumSjseStr+"<#r#>";
         }
         sumSjseStr=sumSjseStr+deFormat.format(sumSjse);
         
    %>
	bz = "<font color='#d32e2e'>税票号码:"+ysphm+" <BR> 第 "+dycs+" 次打印。&nbsp;&nbsp;";
	//赋值给隐藏域
	//if(sjm != ""){
		//wszmsjmOBJ.value=sjm;
	  if(wszmsjmOBJ.value == "")
	  {
		var tempsjm='<%=StringUtil.randomString(10)%>';
		bz = bz +" 随机验证码：" + tempsjm;
		
		if(zsjm.length>0){
			zsjm=zsjm+'<#r#>';
		}
		zsjm=zsjm+tempsjm;
	  }else{
		 // alert("test2");
		  sjm = wszmsjmOBJ.value;
		 // alert(sjm);
		  var temp=sjm.split("<#r#>");
		  
		  //alert(temp.length);
		  //alert(temp[<%=i%>]);
		  bz = bz +" 随机验证码：" +temp[<%=i%>] ;
	  }	
		//alert(zsjm);
		
	//}
	bz=bz +"<BR>共<%=fpList.size()%>张&nbsp;&nbsp;第<%=i+1%>张<BR>票面总金额:￥<%=deFormat.format(zsumSjse)%>";
	bz= bz +""+"</font>";
	// alert(bz);
	if(bz != ""){
		eval("document.all.bz"+'<%=i%>').innerHTML = bz;
	}
	<%
     }
	%>
	document.all.hjje.value="<%=sumSjseStr%>";
	//alert(document.all.hjje.value);
	
	if(wszmsjmOBJ.value == "")
	{
		wszmsjmOBJ.value = zsjm
	}
	
 </logic:equal>
	
 <logic:notEqual name="wszmForm" property="sspzlxdm" value="<%=CodeConstant.WSZM_JKS%>">
	var wszmsjmOBJ = document.all.wszmsjm;
	var sjm="";
	if(wszmsjmOBJ.value == "")
	{
		sjm = '<%=StringUtil.randomString(10)%>';
	}
	else
	{
		sjm = wszmsjmOBJ.value;
	}
	var dycs = '<bean:write name="wszmForm" property="dycs" />';
	var ysphm = '<bean:write name="wszmForm" property="ysphm" />';
	var bz = "";
	var sspzlxdm = '<bean:write name="wszmForm" property="sspzlxdm" />' //凭证来源
	if(sspzlxdm == '<%=CodeConstant.WSZM_SRTHS%>')
	{
		bz = "<font color='#d32e2e'>收入退还书<BR><BR>第 "+dycs+" 次打印。&nbsp;&nbsp;";
	}
	else if(sspzlxdm == '<%=CodeConstant.WSZM_JKS%>')
	{
		bz = "<font color='#d32e2e'>税票号码:"+ysphm+" <BR><BR> 第 "+dycs+" 次打印。&nbsp;&nbsp;";
	}
	else
	{
		bz = "<font color='#d32e2e'>第 "+dycs+" 次打印。&nbsp;&nbsp;";
	}
	
	//赋值给隐藏域
	if(sjm != ""){
		wszmsjmOBJ.value=sjm;
		bz = bz +" 随机验证码：" + sjm;
	
	}
	bz= bz +"<BR>共1张&nbsp;&nbsp;第1张<BR>票面总金额:￥<bean:write name="wszmForm" property="hjje2" />"+"</font>";
	
	if(bz != ""){
		document.all.bz.innerHTML = bz;
	}
 </logic:notEqual>	
 StartPrint();
}

function doClose()
{
	window.close();
}

function StartPrint()
{
	var bccgbz = document.all.bccgbz.value; //保存成功标志
	if(bccgbz == 1)
	{
		goprint();
	}
}

function goprint()
{
	<logic:equal name="wszmForm" property="sspzlxdm" value="<%=CodeConstant.WSZM_JKS%>">
	  onPrintJks();
	</logic:equal>
	<logic:notEqual name="wszmForm" property="sspzlxdm" value="<%=CodeConstant.WSZM_JKS%>">
	 //判断打印是否成功
	 onPrint();
	</logic:notEqual>	
}

function onPrintJks()
{
 <%
  if(form.getSspzlxdm().equals(CodeConstant.WSZM_JKS)&&form.getBccgbz().equals("1")){
	  
   String sjms[]=form.getWszmsjm().split("<#r#>");
   String wszhs[]=form.getWszmwszhs().split("<#r#>");
   String ndzbs[]=form.getWszmndzbs().split("<#r#>");
   
   for(int i=0;i<fpList.size();i++){
		 ArrayList eveList=(ArrayList)fpList.get(i); 
		 double sumSjse=0.00;
		 String mxYpzh = "";
 		 String mxYwszh = "";
 		 String mxSz = "";
         String mxPmmc = "";
         String mxSkssrq ="";
         String mxSjse = "";
         String mxRkrq = "";
		 for(int j=0;j<eveList.size();j++){
			 HashMap map=(HashMap)eveList.get(j);
             sumSjse=sumSjse+StringUtil.getDouble((String)map.get("sjse"), 0.00);
             mxYpzh += map.get("ypzh") + ";;";
			 mxYwszh += map.get("ywszh") + ";;";
			 mxSz += map.get("szmc") + ";;";
			 mxPmmc += map.get("szsmmc") + ";;";
			 mxSkssrq += map.get("skssrq") + ";;";
			 mxSjse += map.get("sjse") + ";;";
			 mxRkrq += map.get("ypzrkrq") + ";;";
		 }
   %>	
	var wszh = '<%=wszhs[i]%>'; //完税证号
	var ndzb = '<%=ndzbs[i]%>'; //年度字别
	var tfrq = '<bean:write name="wszmForm" property="tfrq"/>'; 
	//填发日期年
	var rqn=tfrq.substring(0,4);
	//填发日期月
	var rqy=tfrq.substring(4,6);
	//填发日期日
	var rqr=tfrq.substring(6,8);
	var zsjg = '<bean:write name="wszmForm" property="swjgzzjgmc"/>'; //征收机关
	var nsrsbh = '<bean:write name="wszmForm" property="nsrsbh"/>'; //纳税人识别号
	var nsrsmc = '<bean:write name="wszmForm" property="nsrmc"/>'; //纳税人识别号
	var hjje = '￥<%=deFormat.format(sumSjse)%>'; //合计金额
	var hjjedx = '<%=Currency.convert(sumSjse)%>'; //合计金额大写
	var sspzlxdm = '<bean:write name="wszmForm" property="sspzlxdm" />'; //凭证来源
	var tpr = '<bean:write name="wszmForm" property="lrr" />'; //填票人
	
	var bz3 = " ";
	bz3 = '税票号码：<bean:write name="wszmForm" property="ysphm" />';
	var bz1 = '<bean:write name="wszmForm" property="dycs" />' ;//打印次数
	var bz2 = '<%=sjms[i]%>' ;//随机码
	var bz4 = '票面总金额：￥<%=deFormat.format(zsumSjse)%>';//
		
    var mxYpzh = '<%=mxYpzh%>' //明细，原凭证号
	
	var mxSz = '<%=mxSz%>'; //明细，税种
	var mxPmmc = '<%=mxPmmc%>'; //明细，品目名称
	var mxSkssrq = '<%=mxSkssrq%>'; //明细，税款所属日期
	var mxRkrq = '<%=mxRkrq%>'; //明细，入(退)库日期
	var mxSjse = '<%=mxSjse%>'; //明细，实缴(退)金额
	document.printer.setNDZB(ndzb);
	document.printer.setWSZH(wszh);
	document.printer.setRQN(rqn);
	document.printer.setRQY(rqy);
	document.printer.setRQR(rqr);
	document.printer.setSWDJDM(nsrsbh);
	document.printer.setZSJG(zsjg);
	document.printer.setNSRQC(nsrsmc);
	document.printer.setJEHJ(hjje);
	document.printer.setJEHJDX(hjjedx);
	document.printer.setTPR(tpr);
	document.printer.setBZ1("第 "+bz1+" 次打印  共<%=fpList.size()%>张第<%=i+1%>张");
	document.printer.setBZ2("随机验证码："+bz2);
    document.printer.setBZ3(bz3);
    document.printer.setBZ4(bz4);
	document.printer.setOLD_WSPZH(mxYpzh);
	document.printer.setSZ(mxSz);
	document.printer.setPMMC(mxPmmc);
	document.printer.setSKSSRQ(mxSkssrq);
	document.printer.setRK_TK_RQ(mxRkrq);
	document.printer.setSJJE(mxSjse);
	document.printer.startPrint();
	<%
     }
	%>
    fnOpen();
   <%    
   }
   %>
	return false;
}


function onPrint()
{
	var wszh = '<bean:write name="wszmForm" property="headWszh"/>'; //完税证号
	var ndzb = '<bean:write name="wszmForm" property="ndzb"/>'; //年度字别
	var tfrq = '<bean:write name="wszmForm" property="tfrq"/>'; 
	//填发日期年
	var rqn=tfrq.substring(0,4);
	//填发日期月
	var rqy=tfrq.substring(4,6);
	//填发日期日
	var rqr=tfrq.substring(6,8);
	var zsjg = '<bean:write name="wszmForm" property="swjgzzjgmc"/>'; //征收机关
	var nsrsbh = '<bean:write name="wszmForm" property="nsrsbh"/>'; //纳税人识别号
	var nsrsmc = '<bean:write name="wszmForm" property="nsrmc"/>'; //纳税人识别号
	var hjje = '￥<bean:write name="wszmForm" property="hjje2" />'; //合计金额
	var hjjedx = '<bean:write name="wszmForm" property="hjjedx2" />'; //合计金额大写
	var sspzlxdm = '<bean:write name="wszmForm" property="sspzlxdm" />'; //凭证来源
	var tpr = '<bean:write name="wszmForm" property="lrr" />'; //填票人
	
	var bz3 = " ";
	if(sspzlxdm == '<%=CodeConstant.WSZM_SRTHS%>')
	{
		bz3 = '收入退还书';
	}
	if(sspzlxdm == '<%=CodeConstant.WSZM_JKS%>')
	{
		bz3 = '税票号码：<bean:write name="wszmForm" property="ysphm" />';
	}
	
	var bz1 = '<bean:write name="wszmForm" property="dycs" />' ;//打印次数
	var bz2 = '<bean:write name="wszmForm" property="wszmsjm" />' ;//随机码
	var bz4 = '票面总金额：￥<bean:write name="wszmForm" property="hjje2" />';//
	var mxYpzh = "";
	if((sspzlxdm == '<%=CodeConstant.WSZM_LSWSZ%>')
		||(sspzlxdm == '<%=CodeConstant.WSZM_GTWSZ%>')
		||(sspzlxdm == '<%=CodeConstant.WSZM_QSWSZ%>')
		||(sspzlxdm == '<%=CodeConstant.WSZM_CCWSZ%>'))
	{
		var mxYpzh = '<bean:write name="wszmForm" property="mxYwszh" />' //明细，原完税证号
	}
	else
	{
		var mxYpzh = '<bean:write name="wszmForm" property="mxYpzh" />' //明细，原凭证号
	}
	
	var mxSz = '<bean:write name="wszmForm" property="mxSz" />'; //明细，税种
	var mxPmmc = '<bean:write name="wszmForm" property="mxPmmc" />'; //明细，品目名称
	var mxSkssrq = '<bean:write name="wszmForm" property="mxSkssrq" />'; //明细，税款所属日期
	var mxRkrq = '<bean:write name="wszmForm" property="mxRkrq" />'; //明细，入(退)库日期
	var mxSjse = '<bean:write name="wszmForm" property="mxSjse" />'; //明细，实缴(退)金额
				
	document.printer.setNDZB(ndzb);
	document.printer.setWSZH(wszh);
	document.printer.setRQN(rqn);
	document.printer.setRQY(rqy);
	document.printer.setRQR(rqr);
	document.printer.setSWDJDM(nsrsbh);
	document.printer.setZSJG(zsjg);
	document.printer.setNSRQC(nsrsmc);
	document.printer.setJEHJ(hjje);
	document.printer.setJEHJDX(hjjedx);
	document.printer.setTPR(tpr);
	document.printer.setBZ1("第 "+bz1+" 次打印  共1张第1张");
	document.printer.setBZ2("随机验证码："+bz2);
	if((sspzlxdm == '<%=CodeConstant.WSZM_SRTHS%>') || (sspzlxdm == '<%=CodeConstant.WSZM_JKS%>'))
	{
		document.printer.setBZ3(bz3);
	}
	document.printer.setBZ4(bz4);
	document.printer.setOLD_WSPZH(mxYpzh);
	document.printer.setSZ(mxSz);
	document.printer.setPMMC(mxPmmc);
	document.printer.setSKSSRQ(mxSkssrq);
	document.printer.setRK_TK_RQ(mxRkrq);
	document.printer.setSJJE(mxSjse);
		
	document.printer.startPrint();
	
	fnOpen();
	return false;
}

function fnOpen()
{
   var ret = window.showModalDialog("printConfirm.html", "", 
      "dialogHeight: 200px;dialogWidth: 300px;status:0");
   //alert(ret);
   if(!ret)  {
     alert("请确认！");
	 fnOpen();
   }
   if(ret=="yes"){
   	//alert("old number="+ret);
   	//打印成功
   	printSuccess();
   	return ret;
   }
   if(ret=="yes_second"){
   	//alert("old number="+ret);
   	goprint();
   	return ret;
   }
   if(ret=="no_second"){
   	//alert("get number="+ret);
   	rePrint();
   	return ret;
   }

}


function rePrint()
{
	if (document.wszmForm.headWszh.value!="") {
		//alert('Reprint');
		doSubmitForm('Reprint');
	}
}

function printSuccess()
{
	if (document.wszmForm.headWszh.value!="") {
		//alert('Success');
		doSubmitForm('Success');
	}
}

</script>
</html:html>