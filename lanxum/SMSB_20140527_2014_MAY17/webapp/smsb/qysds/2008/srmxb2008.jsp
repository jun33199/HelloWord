<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.util.HashMap"%>
<%@ page import=" com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.srmxb.web.SrmxbForm2008" %>


<html>
<head>
<title>收入明细表</title>
<link href="../../../css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="../../../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/qysdsnew.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/function.js"></script>
</head>
<script language=JavaScript>
<%/*初始化*/%>
function doInit()
{
     //初始化文本框onChange事件处理
		initNumText("je",26);
   <%
     SrmxbForm2008 srmxbForm = (SrmxbForm2008)request.getAttribute("srmxbForm2008");
     if(srmxbForm!=null && srmxbForm.getSrmxb().size()>0)
     {
       for(int i=0;i<srmxbForm.getSrmxb().size();i++)
       {
         HashMap map = new HashMap();
         map = (HashMap)srmxbForm.getSrmxb().get(i);
         String hc = (String)map.get("hc");
         String je =(String)map.get("je");
         %>
          var obj = document.getElementById('<%=hc%>_1');
          obj.value = '<%=je%>';
         <%
       }
     }
   %>
}

<%/*保存*/%>
	function doSave()
	{
		doSubmitForm('Save');
		
	}
	
	<%/*清除*/%>
	function doReset()
	{
	   if(confirm("确认是否清除当前数据？"))
	    {
	      <%
		   	for(int i=1; i<=26; i++){
		   	%>
				obj = eval("document.getElementById('<%=i%>_1')");
				obj.value = "";
				<%
		   	}
		   	%>
	    }
	}
	
	<%/*删除*/%>
	function doDelete()
	{
		doSubmitForm('Delete');
	}
	<%/*审核*/%>
	function doChecked()
	{
	  doSubmitForm('Check');
	  
	}
		
		<%/*返回*/%>
		function doExit()
		{
			doSubmitForm('Exit');
		}

</script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="doInit()">
	<%@include file="../../include/header.jsp"%>
 <br>
    <html:form  action="/webapp/smsb/qysds/2008/srmxbAction2008.do" method="post">	
	<html:hidden property="actionType" />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />
	<table width="96%" align="center" cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1"  colspan="7">收入明细表</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;申报日期:<bean:write
				name="srmxbForm2008" property="sbrq" scope="request" filter="true" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额单位：元（列至角分）
			</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;计算机代码:<bean:write
				name="srmxbForm2008" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;纳税人名称：<bean:write
				name="srmxbForm2008" property="nsrmc" scope="request" filter="true" />&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td class="1-td2"  colspan="7">
				<TABLE class="table-99" align="center">
				<TR>
				<TD>
				<div id="Layer2" style="position:static;">
				<table id="srmxb_list" border="1" cellspacing="0" class="table-99" align="center">
                  <tr> 
                    <td class="2-td1-left" nowrap>行次</td>
                    <td class="2-td1-left" nowrap>项目</td>
                    <td class="2-td1-center" nowrap>金额</td>       
					<td class="2-td1-left" nowrap>行次</td>
                    <td class="2-td1-left" nowrap>项目</td>
                    <td class="2-td1-center" nowrap>金额</td>
                  </tr>
				  <tr> 
                   <td class="2-td2-left" nowrap>1</td>
					<td class="2-td2-left-qysds1" nowrap>一、销售（营业）收入合计（2＋13）</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='1_1' value='' size='13' tabindex='1'>
                   <input type="hidden" name="hc" value="1"/>
                   </td>
					<td class="2-td2-left" nowrap>13</td>
					<td class="2-td2-left-qysds2" nowrap>（二）视同销售收入（14＋15＋16）</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='13_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="13"/>
                   </td>
                  </tr>  
				  <tr> 
                    <td class="2-td2-left" nowrap>2</td>
					<td class="2-td2-left-qysds2" nowrap>（一）营业收入合计（3＋8）</td>
                    <td class="2-td2-center" nowrap>
                    <input type='text'  name='je' id='2_1' value='' size='13' tabindex='1'>
                    <input type="hidden" name="hc" value="2"/>
                    </td>					
				   <td class="2-td2-left" nowrap>14</td>
					<td class="2-td2-left-qysds3" nowrap>（1）非货币性交易视同销售收入</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='14_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="14"/>
                   </td>
                  </tr>    
                  <tr> 
                    <td class="2-td2-left" nowrap>3</td>
					<td class="2-td2-left-qysds2" nowrap>1.主营业务收入（4＋5＋6＋7）</td>
                    <td class="2-td2-center" nowrap>
                    <input type='text'  name='je' id='3_1' value='' size='13' tabindex='1'>
                    <input type="hidden" name="hc" value="3"/>
                    </td>
					<td class="2-td2-left" nowrap>15</td>
					<td class="2-td2-left-qysds3" nowrap>（2）货物、财产、劳务视同销售收入</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='15_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="15"/>
                   </td>
                  </tr>                  
                   
                  <tr> 
                    <td class="2-td2-left" nowrap>4</td>
					<td class="2-td2-left-qysds3" nowrap>（1）销售货物</td>
                    <td class="2-td2-center" nowrap>
                    <input type='text'  name='je' id='4_1' value='' size='13' tabindex='1'>
                    <input type="hidden" name="hc" value="4"/>
                    </td>
					 <td class="2-td2-left" nowrap>16</td>
					<td class="2-td2-left-qysds3" nowrap>（3）其他视同销售收入</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='16_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="16"/>
                   </td>
                  </tr>    
                   <tr> 
				   <td class="2-td2-left" nowrap>5</td>
					<td class="2-td2-left-qysds3" nowrap>（2）提供劳务</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='5_1' value='' size='13' tabindex='1'>
                   <input type="hidden" name="hc" value="5"/>
                   </td>
                    <td class="2-td2-left" nowrap>17</td>
					<td class="2-td2-left-qysds1" nowrap>二、营业外收入 （18＋19＋20＋21＋22＋23＋24＋25＋26）</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='17_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="17"/>
                   </td>
				  
                  </tr>    
                  <tr> 
                    <td class="2-td2-left" nowrap>6</td>
					<td class="2-td2-left-qysds3" nowrap>（3）让渡资产使用权</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='6_1' value='' size='13' tabindex='1'>
                   <input type="hidden" name="hc" value="6"/>
                   </td>
				   <td class="2-td2-left" nowrap>18</td>
					<td class="2-td2-left-qysds2" nowrap>1.固定资产盘盈</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='18_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="18"/>
                   </td>
                  </tr>    
                  <tr> 
                    <td class="2-td2-left" nowrap>7</td>
					<td class="2-td2-left-qysds3" nowrap>（4）建造合同</td>
                    <td class="2-td2-center" nowrap>
                    <input type='text'  name='je' id='7_1' value='' size='13' tabindex='1'>
                    <input type="hidden" name="hc" value="7"/>
                    </td>
				   <td class="2-td2-left" nowrap>19</td>
					<td class="2-td2-left-qysds2" nowrap>2.处置固定资产净收益</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='19_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="19"/>
                   </td>
                  </tr>    
				  <tr> 
                    <td class="2-td2-left" nowrap>8</td>
					<td class="2-td2-left-qysds2" nowrap>2.其他业务收入（9＋10＋11＋12）</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='8_1' value='' size='13' tabindex='1'>
                   <input type="hidden" name="hc" value="8"/>
                   </td>
					 <td class="2-td2-left" nowrap>20</td>
					<td class="2-td2-left-qysds2" nowrap>3.非货币性资产交易收益</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='20_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="20"/>
                   </td>
                  </tr>  
                  <tr> 
                    <td class="2-td2-left" nowrap>9</td>
					<td class="2-td2-left-qysds3" nowrap>（1）材料销售收入</td>
                    <td class="2-td2-center" nowrap>
                    <input type='text'  name='je' id='9_1' value='' size='13' tabindex='1'>
                    <input type="hidden" name="hc" value="9"/>
                    </td>
					<td class="2-td2-left" nowrap>21</td>
					<td class="2-td2-left-qysds2" nowrap>4.出售无形资产收益</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='21_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="21"/>
                   </td>
                  </tr>    
                  <tr> 
                    <td class="2-td2-left" nowrap>10</td>
					<td class="2-td2-left-qysds3" nowrap>（2）代购代销手续费收入</td>
                    <td class="2-td2-center" nowrap>
                    <input type='text'  name='je' id='10_1' value='' size='13' tabindex='1'>
                    <input type="hidden" name="hc" value="10"/>
                    </td>
					<td class="2-td2-left" nowrap>22</td>
					<td class="2-td2-left-qysds2" nowrap>5.罚款净收入</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='22_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="22"/>
                   </td>
                  </tr>    
				   <tr> 
                    <td class="2-td2-left" nowrap>11</td>
					<td class="2-td2-left-qysds3" nowrap>（3）包装物出租收入</td>
                    <td class="2-td2-center" nowrap>
                    <input type='text'  name='je' id='11_1' value='' size='13' tabindex='1'>
                    <input type="hidden" name="hc" value="11"/>
                    </td>
                    <td class="2-td2-left" nowrap>23</td>
					<td class="2-td2-left-qysds2" nowrap>6.债务重组收益</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='23_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="23"/>
                   </td>
                  </tr>  
				   <tr> 
                    <td class="2-td2-left" nowrap>12</td>
					<td class="2-td2-left-qysds3" nowrap>（4）其他</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='12_1' value='' size='13' tabindex='1'>
                   <input type="hidden" name="hc" value="12"/>
                   </td>
                    <td class="2-td2-left" nowrap>24</td>
					<td class="2-td2-left-qysds2" nowrap>7.政府补助收入</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='24_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="24"/>
                   </td>
                  </tr>  
				   <tr> 
                    <td class="2-td2-left" nowrap>&nbsp;</td>
					<td class="2-td2-left-qysds3" nowrap>&nbsp;</td>
                   <td class="2-td2-center" nowrap>
                   &nbsp;
                   </td>
                    <td class="2-td2-left" nowrap>25</td>
					<td class="2-td2-left-qysds2" nowrap>8.捐赠收入</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='25_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="25"/>
                   </td>
                  </tr>  
				   <tr> 
                    <td class="2-td2-left" nowrap>&nbsp;</td>
					<td class="2-td2-left-qysds3" nowrap>&nbsp;</td>
                    <td class="2-td2-center" nowrap>
                    &nbsp;
                    </td>
                    <td class="2-td2-left" nowrap>26</td>
					<td class="2-td2-left-qysds2" nowrap>9.其他</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='26_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="26"/>
                   </td>
                  </tr>  
                </table>
			        </div>
				</TD>
				</TR>
				<TR class="black9">
				<TD>
				<div align="center">
						<a style="cursor:hand" id="previous"
						onclick="gotoPreviousTable()"><img name="xpage" value="上一张表"
						alt="填写上一张表"
						onMouseOver="MM_swapImage('previoustable','','<%=static_contextpath%>images/shangyiye2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/shangyiye1.jpg" id="previoustable"> </a>&nbsp;&nbsp;
						<a style="cursor:hand" id="next"
						onclick="gotoNextTable()"><img name="spage" value="下一张表"
						alt="填写下一张表"
						onMouseOver="MM_swapImage('nexttable','','<%=static_contextpath%>images/xaiyiye2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/xaiyiye1.jpg" id="nexttable"> </a>&nbsp;&nbsp;
						<input type="image" style="cursor:hand"
						tabIndex="-1" onClick="doReset();return false;" accesskey="u"
						value="清除" alt="清除页面输入框信息"
						onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qc-u1.jpg" 
						id="qingchu" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1" onClick="doSave();return false;"
						accesskey="s" value="保存" alt="保存"
						onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/bc-s1.jpg" 
						id="baocun" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="doChecked();return false;" accesskey="d"
						value="单表校验" alt="表内关系校验"
						onMouseOver="MM_swapImage('jiaoyan','','../../../images/b-bdjyd2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" src="../../../images/b-bdjyd1.jpg"
						 id="jiaoyan" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="doDelete();return false;" accesskey="x"
						value="全部删除" alt="删除本表所有数据，且不可恢复"
						onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qbsc-x1.jpg" 
						id="shanchu" /> &nbsp;&nbsp; <input type="image" value="返回" alt="返回到企业所得税年报主页面"
						style="cursor:hand" tabIndex="-1" onClick="doExit();return false;"
						onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/fanhui1.jpg" 
						id="fanhui" /></div>
				</TD>
				</TR>
				</TABLE>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>
<%@ include file="../../include/footernew.jsp"%>
</html:form>
</body>
</html>