<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>北京市地方税务局申报缴款单</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/reader.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>
<script language="JavaScript" src="../js/MathString.js"></script>

<script language="JavaScript">
var tableArray =new Array();
function fnReturn()
{
	location.href="PG3_SBZS_000.htm"
}

</script>


</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
 <%@ include file="./include/header.jsp"%>
<html:form action="/webapp/smsb/gtgshJksYpdsAction.do" method="POST" >
<html:hidden property="actionType" />
<html:hidden property="forward" />

<html:hidden property="hzlx" />
<html:hidden property="hzksrq" />
<html:hidden property="hzjsrq" />
<html:hidden property="ypys" />
<html:hidden property="sbhzdh" />
<html:hidden property="gtgshJsjdm" />

      <table width="96%" align="center" cellspacing="0" class="table-99">
                <tr> 
                  
            <td class="1-td1"  colspan="7">中 华 人 民 共 和 国 税 收 电 子 转 帐 专 用 完 税 凭 
              证<ttsoft:write name="gtgshJksYpdsForm" property="jkpzh"/>号</td>
                </tr>
                <tr> 
                  <td class="1-td2"  colspan="7" valign="top"> <br>
					<table cellpadding="2" border="0" cellspacing="2" width="100%">
                      <tr> 
					  	<td width="3%">&nbsp;</td>
						<td width="77%"><div align="left" class="black9">填发日期：<ttsoft:write name="gtgshJksYpdsForm" property="lrrq"/>&nbsp;</div></td>
						<td width="20%">&nbsp;</td>
					  </tr>
					</table>
					
              <table class="table-99" cellspacing="0">
                <tr> 
                  <td width="12%" class="2-td2-t-left"> <strong>税务登记代码</strong> 
                  </td>
                  <td width="37%" class="2-td2-t-left"><ttsoft:write name="gtgshJksYpdsForm" property="jsjdm"/>&nbsp;</td>
                  <td width="10%" class="2-td2-t-left"><strong>征收机关</strong></td>
                  <td width="41%" class="2-td2-t-center"><ttsoft:write name="gtgshJksYpdsForm" property="swjgzzjgmc"/>&nbsp;</td>
                </tr>
                <tr>
                  <td class="2-td2-left"><strong>纳税人全称</strong></td>
                  <td class="2-td2-left"><ttsoft:write name="gtgshJksYpdsForm" property="nsrmc"/><html:hidden property="nsrmc"/>&nbsp;</td>
                  <td class="2-td2-left"><strong>收款银行(邮局)</strong></td>
                  <td class="2-td2-center"><ttsoft:write name="gtgshJksYpdsForm" property="yhmc"/>&nbsp;</td>
                </tr>
              </table>
					<br>
             <bean:define id="gzlist" name="gtgshJksYpdsForm" property="dataList"/>
              <logic:iterate id="items" name="gzlist" indexId="index">
							 <bean:define id="item" name="items"/>
							  <bean:define id="formlist" name="item" property="dataList"/>
                  <br>
                <table class="table_99" cellspacing="0" width="98%" id='d<ttsoft:write name="item" property="jkpzh"/>' >
                <tr> 
								 <input type="hidden" name='d<ttsoft:write name="item" property="jkpzh"/>_focus'>
                  <td nowrap class="2-td1-left">税种</td>
                  <td nowrap class="2-td1-left"><b><ttsoft:write name="item" property="szmc"/>&nbsp
                    </b></td>
                  <td class="2-td1-left"> <strong>税款所属日期</strong> </td>
                  <td colspan="2" class="2-td1-center"><div align="left">从 
                     <ttsoft:write name="item" property="skssksrq"/>
                      至 
                     <ttsoft:write name="item" property="skssjsrq"/>
                    </div></td>
                </tr>
                <tr> 
                  <td width="16%" nowrap class="2-td1-Bleft">纳税项目代码</td>
                  <td width="32%" nowrap class="2-td1-Bleft">纳税项目名称</td>
                  <td width="17%" nowrap class="2-td1-Bleft">课税数量</td>
                  <td width="19%" nowrap class="2-td1-Bleft">计税金额</td>
                  <td width="16%" nowrap class="2-td1-Bcenter">实际缴税额</td>
                </tr>
								<logic:iterate id="mxitems" name="formlist" >
							  <bean:define id="mxitem" name="mxitems"/>
								<tr id='tr_d<ttsoft:write name="item" property="jkpzh"/>'  height="18">
								<td nowrap class="2-td2-left" align="center">
								<input type="textfield" name="szsmdm" value='<ttsoft:write name="mxitem" property="szsmdm"/>' readOnly class="inputnoborder" size="15" >
								</td>
								<td nowrap class="2-td2-left" align="center">
								<input type="textfield" name="szsmmc" value='<ttsoft:write name="mxitem" property="szsmmc"/>'  readOnly class="inputnoborder" size="30" >
								</td>
								<td nowrap class="2-td2-left" align="center">
								<input type="textfield" name="kssl" value='<ttsoft:write name="mxitem" property="kssl"/>' readOnly class="inbright" size="15"  ></td>
								<td nowrap class="2-td2-left" align="center">
								<input type="textfield" name="jsje" value='<ttsoft:write name="mxitem" property="jsje"/>' readOnly class="inbright" size="15" >
								</td>
								<td nowrap class="2-td2-center" align="center">
								<input type="textfield" name="sjse" value='<ttsoft:write name="mxitem" property="sjse"/>' readOnly class="inbright" size="15" >
								</td>
								<input type="hidden" name="jkpzh" value='<ttsoft:write name="mxitem" property="jkpzh"/>'> 
								
								<input type="hidden" name="jsjdm" value='<ttsoft:write name="mxitem" property="jsjdm"/>'>
								<input type="hidden" name="yskmdm" value='<ttsoft:write name="mxitem" property="yskmdm"/>'>
								<input type="hidden" name="ysjcdm" value='<ttsoft:write name="mxitem" property="ysjcdm"/>'>
								<input type="hidden" name="skssksrq" value='<ttsoft:write name="mxitem" property="skssksrq"/>'>
								<input type="hidden" name="skssjsrq" value='<ttsoft:write name="mxitem" property="skssjsrq"/>'>
								<input type="hidden" name="rkje" value='<ttsoft:write name="mxitem" property="rkje"/>'>
								<input type="hidden" name="sbbh" value='<ttsoft:write name="mxitem" property="sbbh"/>'>
								<input type="hidden" name="sjfc" value='<ttsoft:write name="mxitem" property="sjfc"/>'>
								<input type="hidden" name="qjfc" value='<ttsoft:write name="mxitem" property="qjfc"/>'>
								<input type="hidden" name="szsmdm_old" value='<ttsoft:write name="mxitem" property="szsmdm"/>'>						
								<input type="hidden" name="nd" value='<ttsoft:write name="mxitem" property="nd"/>'>
								
								</tr>

	              </logic:iterate>
                <DIV id=divSfTemp></DIV>

                <tr> 
                  <td nowrap class="2-td2-left" height="29"> <strong>合计</strong></td>
                  <td nowrap class="2-td2-left">&nbsp;</td>
                  <td nowrap class="2-td2-left" height="29">&nbsp;</td>
                  <td nowrap class="2-td2-left" height="29">&nbsp;</td>
                  <td nowrap class="2-td2-center" height="29"><input type="text" name="xthj" value='<ttsoft:write name="item" property="sjje"/>' class="inbright" size ="15"></td>
                </tr>
                <tr> 
                  <td nowrap class="2-td2-left" height="29"><strong>金额合计(大写)</strong></td>
                  <td height="29" colspan="4" nowrap class="2-td2-center" id="xthjdx">系统带出</td>
                </tr>
                <!--<tr> 
                  <td nowrap  colspan="5" class="2-td2-center">
												<table border="0" width="100%">
													<tr>
													<td width="47%"  nowrap ><div align="right"><img onClick='addRow("d<ttsoft:write name="item" property="jkpzh"/>")'  onMouseOver="MM_swapImage('zj1','','<%=static_contextpath%>images/tianjia2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="增加" id="zj1" src="<%=static_contextpath%>images/tianjia1.jpg" width="79" height="22"></div></td>
													<td width="26%" nowrap ><div align="center"><img onClick='deleteRow("d<ttsoft:write name="item" property="jkpzh"/>",null,"d<ttsoft:write name="item" property="jkpzh"/>")'  onMouseOver="MM_swapImage('sc2','','<%=static_contextpath%>images/shanchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="删除" id="sc2" src="<%=static_contextpath%>images/shanchu1.jpg" width="79" height="22"></div></td>
													<td width="24%"  nowrap >&nbsp;</td>
													<td width="3%"  nowrap >&nbsp;</td>
													</tr>
												</table>
									  </td>
                </tr>-->
              </table>
							<div id=d<ttsoft:write name="item" property="jkpzh"/>_epodDateLayer style="position: absolute; width: 20; height: 166; z-index: 9998; display: none" ><select id=sel onclick=selectClick("d<ttsoft:write name="item" property="jkpzh"/>","d<ttsoft:write name="item" property="jkpzh"/>") onkeyup=selectMove("d<ttsoft:write name="item" property="jkpzh"/>","d<ttsoft:write name="item" property="jkpzh"/>")  onfocusout=selectClick("d<ttsoft:write name="item" property="jkpzh"/>","d<ttsoft:write name="item" property="jkpzh"/>") onkeydown=if(window.event.keyCode==13)selectClick("d<ttsoft:write name="item" property="jkpzh"/>","d<ttsoft:write name="item" property="jkpzh"/>")></select></div>	
							<script language="javascript"> 

							var jsArr_d<ttsoft:write name="item" property="jkpzh"/>='<tr id=tr_d<ttsoft:write name="item" property="jkpzh"/>><td nowrap class="2-td2-left" align="center"><input type="textfield" name="szsmdm" value=""  size="15" onKeyUp=setday("d<ttsoft:write name="item" property="jkpzh"/>","d<ttsoft:write name="item" property="jkpzh"/>",this) onKeyDown=doEnterDown("d<ttsoft:write name="item" property="jkpzh"/>","d<ttsoft:write name="item" property="jkpzh"/>",this)  onblur=resetRow("d<ttsoft:write name="item" property="jkpzh"/>","d<ttsoft:write name="item" property="jkpzh"/>",this) onfocus=setday("d<ttsoft:write name="item" property="jkpzh"/>","d<ttsoft:write name="item" property="jkpzh"/>",this) ></td><td nowrap class="2-td2-left" align="center"><input type="textfield" name="szsmmc" value="" class="inputonborder "  size="30"  readOnly tabIndex="-1" ></td><td nowrap class="2-td2-left" align="center"><input type="textfield" name="kssl" value=""  size="15" onkeydown="isNum(this,0,null,null,null,0)" onchange="isNum(this,0,null,null,null,0)"></td><td nowrap class="2-td2-left" align="center">	<input type="textfield" name="jsje" value=""  size="15" onkeydown="isNum(this,0)" onchange="isNum(this,0)"></td><td nowrap class="2-td2-center" align="center" ><input type="textfield" name="sjse" value="" size="15" onkeydown="isNum(this,0)" onchange="if(isNum(this,0)){hj();}"></td><input type="hidden" name="jkpzh" value=<ttsoft:write name="mxitem" property="jkpzh"/>><input type="hidden" name="jsjdm" value=<ttsoft:write name="mxitem" property="jsjdm"/>><input type="hidden" name="yskmdm" value=<ttsoft:write name="mxitem" property="yskmdm"/>><input type="hidden" name="ysjcdm" value=<ttsoft:write name="mxitem" property="ysjcdm"/>><input type="hidden" name="skssksrq" value=<ttsoft:write name="mxitem" property="skssksrq"/>><input type="hidden" name="skssjsrq" value=<ttsoft:write name="mxitem" property="skssjsrq"/>><input type="hidden" name="rkje" value=<ttsoft:write name="mxitem" property="rkje"/>><input type="hidden" name="sbbh" value=<ttsoft:write name="mxitem" property="sbbh"/>><input type="hidden" name="sjfc" value=<ttsoft:write name="mxitem" property="sjfc"/>><input type="hidden" name="qjfc" value=<ttsoft:write name="mxitem" property="qjfc"/>><input type="hidden" name="szsmdm_old" ><input type="hidden" name="nd" ></tr>'; 
							var d<ttsoft:write name="item" property="jkpzh"/>_list=new DTable(d<ttsoft:write name="item" property="jkpzh"/>,jsArr_d<ttsoft:write name="item" property="jkpzh"/>);	
							d<ttsoft:write name="item" property="jkpzh"/>_list.tableTail=3;
							d<ttsoft:write name="item" property="jkpzh"/>_list.tableHead=2;

							hjArray.push(new Array('sjse','xthj','d<ttsoft:write name="item" property="jkpzh"/>'));
							tableArray.push(new Array('d<ttsoft:write name="item" property="jkpzh"/>','<ttsoft:write name="item" property="szmc"/>'));
							</script>
			  <br>
				
              </logic:iterate>

              <br>
		
              <table width="94%" border="0" cellpadding="0" cellspacing="4">
                <tr valign="bottom"> 
                  <td width="40%"> &nbsp;</td>
                  <!--<td width="8%"><img onclick="" style="cursor:hand" onMouseOver="MM_swapImage('dy1','','<%=static_contextpath%>images/dayin2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="打印" id="dy1" src="<%=static_contextpath%>images/dayin1.jpg" width="79" border="0" height="22"></td>-->
                  <td width="10%"><input type="image" accesskey="f" tabIndex="-1" onclick="doSubmitForm('<bean:write name="gtgshJksYpdsForm" property="fhbs"/>');return false;" style="cursor:hand" onMouseOver="MM_swapImage('fh1','','<%=static_contextpath%>images/fh-f2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="返回" id="fh1" src="<%=static_contextpath%>images/fh-f1.jpg" width="79" border="0" height="22">&nbsp;&nbsp;</td>
				  <td width="10%"><input type="image" accesskey="c" tabIndex="-1" onClick="tuichu();return false;" style="cursor:hand" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" border="0" height="22">&nbsp;&nbsp;</td>
                  <td width="40%">&nbsp;</td>
                </tr>
              </table>
	     </td>
		</tr>
	  </table>
<%@ include file="./include/footer.jsp"%>
 
 </td>
  </tr>
</table>

</html:form>
</body>
  <script language="javascript">
				<ttsoft:write name="gtgshJksYpdsForm" property="scriptStr" filter="false"/>
	function doSubmitFormPro(action){
	if (!check()) return false;
	doSubmitForm(action);
  }
	function check(){
	 	 var succeed =true;
		 var alertStr="";
     alertStr+=checkSzsmdm();
		 alertStr+=checkHjsjje();
		 if(alertStr!="") {
			 succeed =false
       alert(alertStr);
		 }
	 	 return succeed;
	}
	function checkSzsmdm(){
	 var alertStr="";
	 var szsmdm=document.all('szsmdm');
   if(szsmdm){//找到szsmdm 的对象
				if(szsmdm.length){
					for(var i=0;i<szsmdm.length;i++){
						if(szsmdm[i].value==""){
							alertStr+="税种税目代码不能为空!\n"
							break;
						}//end if
					}//end for
				}//end if
				else{//只存在一个szsmdm
							if(szsmdm.value==""){
								alertStr+="税种税目代码不能为空!\n"
							}
				}
	 }
	 return alertStr;
	}

	function checkHjsjje(){
		
		var alertStr="";
		var tempTable;
		/*
		for(var i=0;i<tableArray.length;i++){
			eval("tempTable="+tableArray[i][0]);
			if(tempTable.all("sjje").value!=tempTable.all("xthj").value){
				alertStr+="*"+tableArray[i][1]+"* 的录入合计金额和系统合计金额不符!\n"
			}
		}
		*/
   return alertStr;
	}
	hj();
	</script>


</html:html>