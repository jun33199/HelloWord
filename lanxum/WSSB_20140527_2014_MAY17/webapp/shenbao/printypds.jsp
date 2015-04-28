<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>


<%String static_file = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);%>
<html>
<head>
<title>北京市地方税务局银行端查询缴税凭证</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<style type="text/css">
.bg-top-left{
	line-height: 26px;
	/*color: #3E6071;*/
	text-align: left;
	border-top: 2px solid #000000;
	border-left: 2px solid #000000;
	vertical-align: center;
	/*background-color: #F3F5F8;*/
}
.bg-top-center{
	line-height: 26px;
	/*color: #3E6071;*/
	text-align: left;
	border-top: 2px solid #000000;
	border-left: 1px solid #000000;
	vertical-align: center;
	/*background-color: #F3F5F8;*/
}
.bg-top-right{
	line-height: 26px;
	/*color: #3E6071;*/
	text-align: left;
	border-top: 2px solid #000000;
	border-right: 2px solid #000000;
	border-left: 1px solid #000000;
	vertical-align: center;
	/*background-color: #F3F5F8;*/
}
.bg-left{
	line-height: 26px;
	/*color: #3E6071;*/
	text-align: left;
	border-top: 1px solid #000000;
	border-left: 2px solid #000000;
	vertical-align: center;
	/*background-color: #F3F5F8;*/
}

.bg-center{
	line-height: 26px;
	/*color: #3E6071;*/
	text-align: left;
	border-top: 1px solid #000000;
	border-left: 1px solid #000000;
	vertical-align: center;
	/*background-color: #F3F5F8;*/
}

.bg-right{
	line-height: 26px;
	/*color: #3E6071;*/
	text-align: left;
	border-top: 1px solid #000000;
	border-left: 1px solid #000000;
	border-right: 2px solid #000000;
	vertical-align: center;
	/*background-color: #F3F5F8;*/
}

.td6{
	text-align: left;
	vertical-align: center;
	border-top: 1px solid #000000;
	border-left: 2px solid #000000;
	border-right: 2px solid #000000;
	/*background-color: #F3F5F8;*/	
}
.td6-bg-bt-left{
	line-height: 26px;
	/*color: #3E6071;*/
	text-align: center;	
	vertical-align: center;
	border-bottom: 1px solid #000000;
	border-right: 1px solid #000000;
	/*background-color: #F3F5F8;*/
}
.td6-bg-bt-center{
	line-height: 26px;
	/*color: #3E6071;*/
	text-align: center;	
	vertical-align: top;	
	border-bottom: 1px solid #000000;
	/*background-color: #F3F5F8;*/
}
.td6-td-left{
	line-height: 26px;
	text-align: left;
	/*color: #3E6071;*/
	border-right: 1px solid #000000;
	/*background-color: #F3F5F8;*/
	vertical-align: center;
}
.td6-bg-center{
	line-height: 26px;
	/*color: #3E6071;*/
	text-align: left;	
	vertical-align: center;
	/*background-color: #F3F5F8;*/
}
.td6-td-left-buttom{
	border-right: 1px solid #000000;
}

.td-hj-xx{
	line-height: 26px;
	/*color: #3E6071;*/
	text-align: left;
	border-top: 1px solid #000000;
	vertical-align: center;
	/*background-color: #F3F5F8;*/
}
.td-hj-dx{
	line-height: 26px;
	/*color: #3E6071;*/
	text-align: left;
	vertical-align: center;
	/*background-color: #F3F5F8;*/
}
.td-last-left{
	line-height: 26px;
	/*color: #3E6071;*/
	text-align: left;
	border-top: 1px solid #000000;
	border-left: 2px solid #000000;
	border-bottom: 2px solid #000000;
	vertical-align: center;
	/*background-color: #F3F5F8;*/
}
.td-last-center{
	line-height: 26px;
	/*color: #3E6071;*/
	text-align: left;
	border-top: 1px solid #000000;
	border-left: 1px solid #000000;
	border-right: 1px solid #000000;
	border-bottom: 2px solid #000000;
	vertical-align: center;
	/*background-color: #F3F5F8;*/
}
.td-last-right{
	line-height: 26px;
	/*color: #3E6071;*/
	text-align: left;
	border-top: 1px solid #000000;	
	border-right: 2px solid #000000;
	border-bottom: 2px solid #000000;
	vertical-align: center;
	/*background-color: #F3F5F8;*/
}
.font1{
	font-size: 18pt;
	font-family:宋体;
	font-weight:bold;	
}
.font2{
	font-size: 11pt;
	font-family:宋体;
	line-height:120%;
}
.font3{
	font-size: 10pt;
	font-family:宋体;
	line-height:110%;
}
.td-bt1{
	/*color: #3E6071;*/
	text-align: center;
	/*background-color: #F3F5F8;*/
}
</style>
<script language="JavaScript">
var xmlStr = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION")==null?"":request.getAttribute("CA_XML_SCHEME_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
var xslDoc;

var contentHeight;
function doReturn()
{
    document.forms[0].actionType.value = "ListJks";
    document.forms[0].submit();
}


function parseXml()
{
		xmlDoc.async = false;
		xmlDoc.loadXML(xmlStr);
		try{
			var zwbs = xmlDoc.selectSingleNode("//zwbs").text;
			//zwbs="10000000000000000000";
			var subFirstZwbs = zwbs.substring(0,1);
		    var subEndZwbs = zwbs.substring(19);
		    var flag = parseFloat(subFirstZwbs) + parseFloat(subEndZwbs);
			
			if(flag>0)
		    {
				document.all("tsDiv").style.display = "none";
				document.all("dyDiv").style.display = "none";
				document.all("dyTd").align="center";
			}
		}catch(e){}

		//解xml报文
		document.getElementById("sbxh_font_1").innerText = xmlDoc.selectSingleNode("/taxdoc/sbsj/sbbh").text;
		document.getElementById("sbxh_font_2").innerText = xmlDoc.selectSingleNode("/taxdoc/sbsj/sbbh").text;
		document.getElementById("tkrq_font").innerText = xmlDoc.selectSingleNode("/taxdoc/sbsj/sbrq").text;
		document.getElementById("jsjdm").innerText = xmlDoc.selectSingleNode("/taxdoc/nsrxx/jsjdm").text;
		document.getElementById("nsrmc").innerText = xmlDoc.selectSingleNode("/taxdoc/nsrxx/nsrmc").text;
		document.getElementById("swjgmc").innerText = xmlDoc.selectSingleNode("/taxdoc/sbsj/swjgzzjgmc").text;
		document.getElementById("fkrmc_input").value = xmlDoc.selectSingleNode("/taxdoc/nsrxx/nsrmc").text;
		document.getElementById("fkrmc_font").innerText = xmlDoc.selectSingleNode("/taxdoc/nsrxx/nsrmc").text;
		document.getElementById("yhmc_font").innerText = xmlDoc.selectSingleNode("/taxdoc/sbsj/yhmc").text;
		document.getElementById("yhmc_input").value = xmlDoc.selectSingleNode("/taxdoc/sbsj/yhmc").text;
		document.getElementById("zh_input").value = xmlDoc.selectSingleNode("/taxdoc/sbsj/zh").text;
		document.getElementById("zh_font").innerText = xmlDoc.selectSingleNode("/taxdoc/sbsj/zh").text;
		document.getElementById("jehj_font").innerText = "￥" + xmlDoc.selectSingleNode("/taxdoc/sbsj/hjsjje").text;
		document.getElementById("hjsjjedx").innerText = xmlDoc.selectSingleNode("/taxdoc/sbsj/hjsjjedx").text;
		
		document.getElementById("xjrq").innerText = xmlDoc.selectSingleNode("/taxdoc/sbsj/xjrq").text;
		
		
		var nxxmbody = document.getElementById("nxxmBody");
		var test="";
		var nxxmList = xmlDoc.selectNodes("/taxdoc/sbsj/nxxm");
		for(var i=0;i<nxxmList.length;i++)
		{
			var newRow = nxxmbody.insertRow(i);	//新增一行
			
			C0 = newRow.insertCell(0);
			C0.setAttribute("className","td6-td-left");
			C0.height="20px";
			C0.colSpan=2;
			C0.innerHTML= '<font class="font3">&nbsp;&nbsp;'+nxxmList[i].selectSingleNode("nxxmmc").text+'</font>'
			
			C1 = newRow.insertCell(1);
			C1.setAttribute("className","td6-td-center");
			C1.setAttribute("colspan","1");
			C1.innerHTML= '<font class="font3">&nbsp;&nbsp;'+nxxmList[i].selectSingleNode("sjje").text+'</font>'
			
		}
		
		contentHeight = nxxmList.length*20;
}

var defaultHeight = 771;
function rePrintHeight(){
	var h1 = document.all.contentTable.offsetHeight;
	if(h1>defaultHeight){
		if((16*20-contentHeight)>(h1-defaultHeight)){
			document.all.zsxmTr.style.height=480-(h1-defaultHeight);
		}
	}
}

function Exit(){
	if(returnStr==null || returnStr==""){
		returnStr="/";
	}
	window.location=returnStr;
}


function doPrint() {
	
	var factory = document.all.factory;
	if(factory!=null){
		if(!factory.object){
			alert("打印控件没有正确安装!");
			return;
		}else{
			try {
				//如果找不到factory.printing.header的值,则表示没有打印机,并且会报错
				factory.printing.header = "";
			    factory.printing.footer = "";
			}catch(e){
					alert("请正确安装打印机");
					return;
			}
	    document.all.header.style.display = "none";
		document.all.footer.style.display = "none";
		document.all.dayinDiv.style.display = "none";
		
		document.all.inputFkrmc.style.display = "none";
		document.all.textFkrmc.style.display = "";
		
		document.all.inputYhmc.style.display = "none";
		document.all.textYhmc.style.display = "";
		
		document.all.inputFkrzh.style.display = "none";
		document.all.textFkrzh.style.display = "";
		
		document.all.sbxh1.style.display = "none";
		document.all.sbxh2.style.display = "";
		
		document.all.jsjdm1.style.display = "none";
		document.all.jsjdm2.style.display = "";
		
		document.all.textFkrzh.innerText=document.all.fkrzh.value;
		document.all.textYhmc.innerText=document.all.yhmc_input.value;
		document.all.textFkrmc.innerText=document.all.fkrmc_input.value;
		
		rePrintHeight();
		
		//bdhtml=window.document.body.innerHTML;
		//sprnstr="<!--startprint-->";
		//eprnstr="<!--endprint-->";
		//prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);   
		//prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));   
		//window.document.body.innerHTML=prnhtml;
		//window.print();
		//document.all.factory.printing.header = "";
		//document.all.factory.printing.footer = "";
	    
		document.all.factory.printing.leftMargin = 0.1;
		document.all.factory.printing.topMargin = 0;
		document.all.factory.printing.rightMargin = 0.1;
		document.all.factory.printing.bottomMargin = 0;
		document.all.factory.DoPrint(false); //设置为false，直接打印
		
		//如果是三方协议用户,或者主管税务机关发生变更的用户,则不可以进行编辑
		
		document.all.inputFkrmc.style.display = "";
		document.all.textFkrmc.style.display = "none";
			
		document.all.inputYhmc.style.display = "";		
		document.all.textYhmc.style.display = "none";
			
		document.all.inputFkrzh.style.display = "";
		document.all.textFkrzh.style.display = "none";
	
	
		document.all.sbxh1.style.display = "";
		document.all.sbxh2.style.display = "none";
		
		document.all.jsjdm1.style.display = "";
		document.all.jsjdm2.style.display = "none";
		
		document.all.dayinDiv.style.display = "";
	    document.all.header.style.display = "";
		document.all.footer.style.display = "";
		
		document.all.zsxmTr.style.height=480;
	}
}
}
</script>
<link href="<%=static_file%>css/text.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor="#FFFFFF" style="margin: 0px"  onLoad="parseXml()">

<table  width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
  <td colspan=2>
  <div id="header">
    	<jsp:include page="../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="打印银行端缴税凭证" />
		<jsp:param name="help_url" value="help/wssb/qyzhsb/jksmx/jksmxypds-000.htm"/>
    	</jsp:include>
	
	<html:errors />
</div>
<!--startprint-->
<form name="mxsbForm" method="POST" action="listJks.dc">

<object id="factory" style="display:none" classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814" 
codebase="<%=static_file%>printer/smsx.cab" viewastext></object>


<!--startprint-->
<input type="hidden" name="actionType">
<input type="hidden" name="sbbh" >
 

<table width="680" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td valgin="top">
      	<table align="center" cellspacing="0" cellpadding="0" class="table-99">
   			<tr height="40">
      			<td>&nbsp;</td>
      		</tr>      	
	       <tr height="35">
	          <td>
              	<table width="98%" cellspacing="0" cellpadding="0" height="100%" >
                	<tr>
                    	<td class="td-bt1"><font class="font1">银行端查询缴税凭证</font></td>
                    </tr>
                </table>
              </td>
	       </tr>
	       <tr>
	        <td class="td-bt2">
            <table cellspacing="0" border="0" style="width:98%" align="center">
             	<tr height="15px">
            		<td  colspan="2">
            		</td>
            	</tr>           	     
            	<tr>
            		<td >
           				<div id="sbxh1">
           					<font class="font2">银行端查询缴税凭证序号(申报序号)：</font><font id="sbxh_font_1" class="font3">&nbsp;</font> 
           				</div>
           				<div id="sbxh2" style="display: none;">
           					<font class="font2">银行端查询缴税凭证序号：</font><font  id="sbxh_font_2" class="font3">&nbsp;</font>
           				</div>
              		</td>
              		<td align="right"  ><font class="font2">填开日期：</font><font id="tkrq_font" class="font3"></font></td>
            	</tr>
            </table>
             <table id="contentTable" cellspacing="0" border="0" style="width:98%" align="center" >
              <tr height="38">
				<td class="bg-top-left" colspan="1">
					<div id="jsjdm1">
						<font class="font2">纳税人识别号<br>(计算机代码)</font>
					</div>
					<div id="jsjdm2" style="display: none;">
						<font class="font2">纳税人识别号</font>
					</div>
				</td>		
				<td class="bg-top-center" colspan="2">
                	<div align="left">
                    	<font class="font3" id="jsjdm">&nbsp;</font>
                    </div>                             	
				</td>
        		<td class="bg-top-center" colspan="1" >                	
                    	<font class="font2"> 纳税人名称</font>
                </td>
        		<td class="bg-top-right" colspan="2">					
                    <div align="left">
                    	<p class="font3" id="nsrmc">&nbsp;</p>
                    </div>   
				</td>
        	</tr>           
            <tr height="38">
				<td class="bg-left" align="left" colspan="1"><font class="font2">税务机构代码</font></td>		
				<td class="bg-center"  colspan="2">
                	<div align="left">
                    	<font class="font3" id='swjgdm' >21100000000&nbsp;</font>
                    </div>
				</td>
        		<td class="bg-center" colspan="1" ><font class="font2">税务机关名称</font></td>
        		<td class="bg-right" colspan="2">
                	<div align="left"><font class="font3" id='swjgmc'>&nbsp;</font></div>
				</td>
        	</tr>
            <tr height="38">
				<td class="bg-left" align="center" colspan="1"><font class="font2">付款人名称</font></td>		
				<td class="bg-center" colspan="2">
                	 <div align="left" id="inputFkrmc">
                      <input  id="fkrmc_input" name="fkrmc" style="width: 180px" ></input>
                    </div>
                    <div align="left" id="textFkrmc" style="display:none" class="font3">
                       <p class="font3" id="fkrmc_font">&nbsp;</p>
                    </div>
				</td>
        		<td class="bg-center"  colspan="1"><font class="font2">付款人开户银行名称</font></td>
        		<td class="bg-right" colspan="2">
					<div align="left" id="inputYhmc">
                       <input id="yhmc_input" name="yhmc_input" style="width: 200px" />
                    </div>
                    <div align="left" id="textYhmc" style="display:none" class="font3">
                       <font id="yhmc_font" class="font3">&nbsp;</font>
                    </div>
				</td>
        	</tr>
             <tr height="38">
				<td class="bg-left" align="center" colspan="1"><font class="font2">付款人账号</font></td>
				<td class="bg-right" colspan="5">
                	<div align="left" id="inputFkrzh">
                       <input id="zh_input" name="fkrzh" style="width: 180px"  />
                    </div>
                    <div align="left" id="textFkrzh" style="display:none" class="font3">
                      <font class="font3" id="zh_font">&nbsp;</font>
                    </div>
				</td>
        	</tr>
            
            <tr height="480" id="zsxmTr">            	
            	<td colspan="6" class="td6">
            		<table border="0" cellpadding="0" cellspacing="0" style="width: 100%; height: 100%">
            			<tr height="35">
			                <td class="td6-bg-bt-left" colspan="2"><font class="font2">征收项目</font></td>
			        		<td class="td6-bg-bt-center" colspan="1"><font class="font2">应缴税额</font></td>
            			</tr> 		          			
            			<tbody id = "nxxmBody">
            			</tbody>
            			<tr>
            				<td colspan="2" class="td6-td-left-buttom">&nbsp;</td>
            				<td colspan="1" ></td>
            			</tr>
            			<tr height="20px">
            				<td colspan="2" class="td-hj-xx">&nbsp;</td>
        					<td class="td-hj-xx" colspan="1">
			                	<div align="center">
			                    	<font class="font2">金额合计（小写）：</font><font class="font3" id="jehj_font">&nbsp;</font>
			                    </div>
                			</td>
            			</tr>
            			<tr height="20px">
            				<td class="td-hj-dx"  colspan="3">
			                	<div align="left">
			                    	<font class="font2">金额合计(大写)：</font><font class="font3" id="hjsjjedx">  &nbsp;</font>
			                    </div>
                			</td>
                		</tr>                		
            			<tr style="height: 0px">
			               	<td style="width:30%"></td>
			                <td style="width:30%"></td>
			                <td style="width:40%"></td>
	            		</tr>
            		</table>
            	</td>   	
            </tr>
           
            <tr height="140">
            	<td class="td-last-left" colspan="2">
                	<div align="center">
                    	<font class="font2">付款人（章）<br>
                       	 经办人（章）</font>
                    </div>
                </td>
                <td class="td-last-center" colspan="2">
                	<div align="center">
                    	<font class="font2">银行记账员<br>（章）</font>
                    </div>
                </td>
        		<td class="td-last-right" colspan="2">
                	<div align="left">
                    	<font class="font2">限缴日期：</font>
                    	<font class="font3" id="xjrq"></font>
                    </div>
                </td>
            </tr>
            <tr>
               	<td style="width:12%"></td>
                <td style="width:14%"></td>
                <td style="width:9%"></td>
                <td style="width:17%"></td>
                <td style="width:14%"></td>
                <td style="width:12%"></td>

            </tr>
       </table>
       <div align="left">
       <table border="0" cellpadding="2" cellspacing="2" class="font2">
	      	<tr><td>&nbsp;注意事项:</td><td>银行端查询缴税凭证序号即为申报序号</td><tr>
	      	<tr><td>&nbsp;</td><td>纳税人识别号即为计算机代码</td><tr>
      	</table>
      </div>	
      <div id="dayinDiv" align="center">
      <table border="0" width="98%" align="center">      	
      	<tr>
      		<td align="left" id="dyTd">
      			<table border="0">
      				<tr>
      					<td>
      						<div id="tsDiv">
      							<font style="color: red">(请插入空白A4纸张，然后再打印!)</font>
      						</div>
      					</td>
      					<td>
      						<div id="dyDiv">
						     	<img alt="打印" src="<%=static_file%>images/dayin1.jpg" 
						     		 name="dayin" value="打印"
						     		 onMouseOver="MM_swapImage('dayin','','<%=static_file%>images/dayin2.jpg',1)"
						             onMouseOut="MM_swapImgRestore()" style="cursor: hand" 
						     		 onClick="doPrint();return false;" >
		     				</div>
		    			</td>
			      		<td >
                     		 <div align="center" id="bt">
                     		 	<img src="<%=static_file%>images/fanhui1.jpg" 
                     		 	overSrc="<%=static_file%>images/fanhui2.jpg" alt="返回" 
                     		 	onClick="doReturn()" style="cursor:hand"/>
                     		 </div><!--********************-->	
			      		</td>
			      		
      				</tr>
      			</table>
      		</td>
      	</tr>
      	
      </table>
       
      </div> 
      <br>        
	   	</tr>
	    </table>
	    <br>
    </td>
  </tr>
   </table>
   <!--endprint-->
<div id="footer">
	<%@ include file="../include/bottom.jsp"%>
</div>
</form>
</body>
</html>
