<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.nstzmxjsb.web.NstzmxjsbForm" %>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.syax.creports.Constants"%>
<html>
<head>
<title>纳税调整减少项目明细表</title>
<link href="../../css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="../../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/qysdsnew.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/function.js"></script>
</head>
<script language=JavaScript>

/*初始化*/
function doInit()
{
        document.forms[0].nstzjsMax.value='3';
	   
        //初始化文本框onChange事件处理
		initNumText("je",18);
		initNumText("nstzjs_je",parseInt(document.forms[0].nstzjsMax.value));
  
   //删除行，最大子行
    var list=document.getElementById("nstzjs_list");
    for(var i=parseInt(document.forms[0].nstzjsMax.value);i>3;i--){
			list.deleteRow( parseInt(18+parseInt(i-1)) );
		}
	    
	<%
	NstzmxjsbForm nstzmxjsbForm=(NstzmxjsbForm)request.getAttribute("nstzmxjsbForm");
	if (nstzmxjsbForm!=null && nstzmxjsbForm.getDataList().size()>0){
		for(int i=0;i<nstzmxjsbForm.getDataList().size();i++){
			HashMap map=(HashMap)nstzmxjsbForm.getDataList().get(i);
			int hc=Integer.parseInt((String)map.get("hc"));
			String je=(String)map.get("je");
			%>
			obj = eval("document.getElementById('<%=hc%>_1')");
			obj.value = '<%=je%>';
			
			<% 
		}
	}
	
	if(nstzmxjsbForm!=null && nstzmxjsbForm.getNstzjs_List().size()>3)
	{
	  //动态子行数
			int dynamicRows=nstzmxjsbForm.getNstzjs_List().size()-3;
			for(int i=0;i<dynamicRows;i++)
			{
			%>
			  DynamicAddNstzjs();
			<%
			}
	}
	
	//赋值
		if(nstzmxjsbForm!=null ){
			for(int i=0;i<nstzmxjsbForm.getNstzjs_List().size();i++){
				HashMap map=(HashMap)nstzmxjsbForm.getNstzjs_List().get(i);
				String strL1=(String)map.get("xm");
				String strL2=(String)map.get("nstzjs_je");
				
				%>
				setNstzjsJEValue('<%=i%>','<%=strL1%>','<%=strL2%>');
				<%
			}
		}
	%>
}
function DynamicAddNstzjs()
{
   var list=document.getElementById("nstzjs_list");
     //当前“其他准备金”最大子行数
		var hc=document.forms[0].nstzjsMax.value;

		var newRow=list.insertRow(18+parseInt(hc));
		//行次
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp";

		//项目
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left-qysds2';
		newCell.innerHTML="<input type='text' name='xm' value='' size='30' tabindex='2'>";
		
		//金额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='nstzjs_je' value='' size='13' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//复选框
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1' tabindex='-1' tabindex='-1'  name='chk' value=''>";
		
		//当前“纳税调增项目”最大子行数加1
		document.forms[0].nstzjsMax.value=parseInt(document.forms[0].nstzjsMax.value)+1;
}
function setNstzjsJEValue(i,xm,je)
{
   var qekc_L1=document.all("xm");
   qekc_L1[parseInt(i)].value=xm
		
   var qekc_L2=document.all("nstzjs_je");
   qekc_L2[parseInt(i)].value=je
}
<%/*保存*/%>
	function doSave()
	{
		var list="<%=Constants.QYSDS_CONTROL_CHAR_FOR_JS%>";
		if (!Char_Vaildate(document.forms[0],list)){
			return false;	
		}
		doSubmitForm('Save');
		
	}
	
	<%/*清除*/%>
	function doReset()
	{
	    if(confirm("确认是否清除当前数据？"))
	    {
	    <%
		   	for(int i=1; i<=18; i++)
		   	{
		   	%>
				obj = eval("document.getElementById('<%=i%>_1')");
				obj.value = "";
				<%
		   	}
		   	%>
		   	clean('xm','nstzjs_je',document.forms[0].nstzjsMax.value);
	    }
	}
	<%/*用于清除动态子行的内容*/%>
	function clean(L1,L2,maxRow){
		var arr1=document.all(L1);
		var arr2=document.all(L2);	
		
		for (var i=0;i<parseInt(maxRow);i++){					
			
				arr1[parseInt(i)].value=""	;		
			
				arr2[parseInt(i)].value=""	;			
		}	
	}
	
	<%/*删除*/%>
	function doDelete()
	{
		doSubmitForm('Delete');
	}
	function doChecked()
	{
	   var list="<%=Constants.QYSDS_CONTROL_CHAR_FOR_JS%>";
		if (!Char_Vaildate(document.forms[0],list)){
			return false;	
		}
	   doSubmitForm('Check');
	  
	}
	<%/*增加其他纳税调减项目行*/%>
	function InsertQTNSTJRow()
	{
	   var list=document.getElementById("nstzjs_list");
	   //当前“其他纳税调减项目”最大子行数
	   var hc=document.forms[0].nstzjsMax.value;
	   var newRow=list.insertRow(18+parseInt(hc));
	   
	   //行次
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp";
		
		//项目
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left-qysds2';
		newCell.innerHTML="<input type='text' name='xm' id='xm_"+parseInt(hc)+1+"' value='' size='30' tabindex='2'>";
	 
	    //金额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='nstzjs_je' id='nstzjs_je_"+parseInt(hc)+1+"' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' value='' size='13' tabindex='2'>";
		
		//复选框
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='chk' value=''>";
		
		//当前“其他纳税调减项目”最大子行数加1
		document.forms[0].nstzjsMax.value=parseInt(document.forms[0].nstzjsMax.value)+1;
	}
	<%/*删除其他纳税调减项目行*/%>
	function RomoveQTNSTJRow()
	{
	   //复选框
		var arrChk=document.all("chk");
		var list=document.getElementById("nstzjs_list");
		
		//当前“其他准备金”最大子行数
		var hc=document.forms[0].nstzjsMax.value;
		if(parseInt(document.forms[0].nstzjsMax.value)>3){
			for(var i=parseInt(parseInt(document.forms[0].nstzjsMax.value)-1);i>=3;i--){
				if(arrChk.length==undefined){
					if(arrChk.checked){
						if(txt_null("muti",i)){
							if(window.confirm("其他纳税调减项目子行第 "+parseInt(parseInt(i)+1) +" 行内容不为空，确认删除吗？"))
							{
								list.deleteRow( parseInt(18+parseInt(i)) );
								document.forms[0].nstzjsMax.value=parseInt(document.forms[0].nstzjsMax.value)-1;
							}else{
								arrChk.checked=false;
							}
						}else{
							list.deleteRow( parseInt(18+parseInt(i)) );
							document.forms[0].nstzjsMax.value=parseInt(document.forms[0].nstzjsMax.value)-1;
						}
					}
				}else{
					if(arrChk[i-3].checked)
					{
						if(txt_null("muti",i))
						{
							if(window.confirm("其他纳税调减项目子行第 "+parseInt(parseInt(i)+1) +" 行内容不为空，确认删除吗？"))
							{
								list.deleteRow( parseInt(18+parseInt(i)) );
								document.forms[0].nstzjsMax.value=parseInt(document.forms[0].nstzjsMax.value)-1;
							}else{
								arrChk[i-3].checked=false;
							}
						}else{
							list.deleteRow( parseInt(18+parseInt(i)) );
							document.forms[0].nstzjsMax.value=parseInt(document.forms[0].nstzjsMax.value)-1;
						}
					}
				}
				
			}
		}	
}
function txt_null(s,i)
{
   if (document.all("xm")[i].value!="")   
   {
		return true;
   }   
   if   (document.all("nstzjs_je")[i].value!="")   
   {
		return true;
   }  
   return false;
}
<%/*返回*/%>
		function doExit()
		{
			doSubmitForm('Exit');
		}	
</script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
		marginheight="0" onLoad="doInit()">
		<%@ include file="../include/header.jsp"%>
 <br>
    <html:form 
			action="/webapp/smsb/qysds/nstzmxjsbAction.do" method="post">
			
		<html:hidden property="actionType" />
		<html:hidden property="nextTableURL" />
	    <html:hidden property="previousTableURL" />
        <input type="hidden" name="nstzjsMax" value="3"/>
	<table width="96%" align="center" cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1"  colspan="7">纳税调整减少项目明细表</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;申报日期:<bean:write
				name="nstzmxjsbForm" property="sbrq" scope="request" filter="true" />
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
				name="nstzmxjsbForm" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;纳税人名称：<bean:write
				name="nstzmxjsbForm" property="nsrmc" scope="request" filter="true" />&nbsp;
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
				<div id="Layer2" style="position:static; ">
				<table id="nstzjs_list" border="1" cellspacing="0" class="table-99" align="center" >
                  <tr> 
                    <td class="2-td1-left" nowrap>行次</td>
                    <td class="2-td1-left" nowrap>项目</td>
                    <td class="2-td1-center" nowrap>金额</td>       
                  </tr>
				  <tr> 
                   <td class="2-td2-left" nowrap>1</td>
					<td class="2-td2-left-qysds1" nowrap>工效挂钩企业用"工资基金结余"发放的工资　</td>
                   <td class="2-td2-center" nowrap><input type='text'  name='je' id='1_1' value='' size='13' tabindex='2'>
				   <input type="hidden" name="hc" value="1"/>
				   </td>
				  </tr> 
				  <tr> 
				       <td class="2-td2-left" nowrap>2</td>
					<td class="2-td2-left-qysds1" nowrap> 以前年度结转在本年度扣除的广告费支出</td>
                    <td class="2-td2-center" nowrap><input type='text'  name='je' id='2_1' value='' size='13' tabindex='2'>
					 <input type="hidden" name="hc" value="2"/>
					</td>		
				  </tr> 
				   <tr>
				       <td class="2-td2-left" nowrap>3</td>
					<td class="2-td2-left-qysds1" nowrap>以前年度结转在本年度扣除的股权投资转让损失　</td>
                    <td class="2-td2-center" nowrap><input type='text'  name='je' id='3_1' value='' size='13' tabindex='2'>
					 <input type="hidden" name="hc" value="3"/>
					</td>
				   </tr>
				   <tr>
				       <td class="2-td2-left" nowrap>4</td>
					<td class="2-td2-left-qysds1" nowrap> 在应付福利费中列支的基本医疗保险</td>
                    <td class="2-td2-center" nowrap><input type='text'  name='je' id='4_1' value='' size='13' tabindex='2'>
					 <input type="hidden" name="hc" value="4"/>
					</td>
				   </tr>
				   <tr>
				        <td class="2-td2-left" nowrap>5</td>
					<td class="2-td2-left-qysds1" nowrap>在应付福利费中列支的补充医疗保险（可税前扣除的部分）</td>
                   <td class="2-td2-center" nowrap><input type='text'  name='je' id='5_1' value='' size='13' tabindex='2'>
				    <input type="hidden" name="hc" value="5"/>
				   </td>
				   </tr>
				   <tr>
				      <td class="2-td2-left" nowrap>6</td>
					<td class="2-td2-left-qysds1" nowrap> 以前年度进行了纳税调整增加、在本年度发生了减提的各项准备金（7行+…15行）</td>
                   <td class="2-td2-center" nowrap><input type='text'  name='je' id='6_1' value='' size='13' tabindex='2'>
				    <input type="hidden" name="hc" value="6"/>
				   </td>
				   </tr>
				    <tr>
				        <td class="2-td2-left" nowrap>7</td>
					<td class="2-td2-left-qysds2" nowrap>其中：坏帐准备</td>
                    <td class="2-td2-center" nowrap><input type='text'  name='je' id='7_1' value='' size='13' tabindex='2'>
					 <input type="hidden" name="hc" value="7"/>
					</td>
				   </tr>
				   <tr>
				       <td class="2-td2-left" nowrap>8</td>
					<td class="2-td2-left-qysds2" nowrap>存货跌价准备</td>
                   <td class="2-td2-center" nowrap><input type='text'  name='je' id='8_1' value='' size='13' tabindex='2'>
				    <input type="hidden" name="hc" value="8"/>
				   </td>
				   </tr>
				   <tr>
				       <td class="2-td2-left" nowrap>9</td>
					<td class="2-td2-left-qysds2" nowrap>固定资产减值准备　</td>
                    <td class="2-td2-center" nowrap><input type='text'  name='je' id='9_1' value='' size='13' tabindex='2'>
					 <input type="hidden" name="hc" value="9"/>
					</td>
				   </tr>
				   <tr>
				      <td class="2-td2-left" nowrap>10</td>
					<td class="2-td2-left-qysds2" nowrap>无形资产减值准备</td>
                    <td class="2-td2-center" nowrap><input type='text'  name='je' id='10_1' value='' size='13' tabindex='2'>
					 <input type="hidden" name="hc" value="10"/>
					</td>
				   </tr>
				   <tr>
				       <td class="2-td2-left" nowrap>11</td>
					<td class="2-td2-left-qysds2" nowrap>在建工程减值准备</td>
                    <td class="2-td2-center" nowrap><input type='text'  name='je' id='11_1' value='' size='13' tabindex='2'>
					 <input type="hidden" name="hc" value="11"/>
					</td>
				   </tr>
				   
				   <tr>
					<td class="2-td2-left" nowrap>12</td>
					<td class="2-td2-left-qysds2" nowrap> 自营证券跌价准备</td>
                   <td class="2-td2-center" nowrap><input type='text'  name='je' id='12_1' value='' size='13' tabindex='2'>
				    <input type="hidden" name="hc" value="12"/>
				   </td>
                  </tr>  
                  
				  <tr>                   			
				   <td class="2-td2-left" nowrap>13</td>
					<td class="2-td2-left-qysds2" nowrap>呆账准备</td>
                   <td class="2-td2-center" nowrap><input type='text'  name='je' id='13_1' value='' size='13' tabindex='2'>
				    <input type="hidden" name="hc" value="13"/>
				   </td>
                  </tr> 
                     
                  <tr>                    
					<td class="2-td2-left" nowrap>14</td>
					<td class="2-td2-left-qysds2" nowrap>保险责任准备金提转差</td>
                   <td class="2-td2-center" nowrap><input type='text'  name='je' id='14_1' value='' size='13' tabindex='2'>
				    <input type="hidden" name="hc" value="14"/>
				   </td>
                  </tr>                  
                   
                  <tr>                    
					 <td class="2-td2-left" nowrap>15</td>
					<td class="2-td2-left-qysds2" nowrap> 其他准备</td>
                   <td class="2-td2-center" nowrap><input type='text'  name='je' id='15_1' value='' size='13' tabindex='2'>
				    <input type="hidden" name="hc" value="15"/>
				   </td>
                  </tr> 
                     
                   <tr> 				  
                    <td class="2-td2-left" nowrap>16</td>
					<td class="2-td2-left-qysds1" nowrap> 本期已转销售收入的预售收入的预计利润（房地产业务填报）</td>
                   <td class="2-td2-center" nowrap><input type='text'  name='je' id='16_1' value='' size='13' tabindex='2'>
				    <input type="hidden" name="hc" value="16"/>
				   </td>				  
                  </tr> 
                     
                  <tr>                    
				   <td class="2-td2-left" nowrap>17</td>
					<td class="2-td2-left-qysds1" nowrap>其他纳税调减项目</td>
                   <td class="2-td2-center" nowrap><input type='text'  name='je' id='17_1' value='' size='13' tabindex='2'>
				    <input type="hidden" name="hc" value="17"/>
				   </td>
				   <td class="2-td2-left">
					<input type="button" value="增加" tabindex='-1' onclick="InsertQTNSTJRow()">
												</td>
												<td class="2-td2-center">
													<input type="button" value='删除' tabindex='-1' onClick="RomoveQTNSTJRow()">
												</td>
                  </tr>  
                    
                  <tr>                    
				   <td class="2-td2-left" nowrap>18</td>
					<td class="2-td2-left-qysds2" nowrap><input type='text'  name='xm' id='xm_1' value='' size='30' tabindex='2'></td>
                   <td class="2-td2-center" nowrap><input type='text'  name='nstzjs_je' id='nstzjs_je_1'   value='' size='13' tabindex='2'>
				    
				   </td>
                  </tr>    
				  <tr> 
                    
					 <td class="2-td2-left" nowrap>19</td>
					<td class="2-td2-left-qysds2" nowrap><input type='text'  name='xm' id='xm_2'  value='' size='30' tabindex='2'></td>
                   <td class="2-td2-center" nowrap><input type='text'  name='nstzjs_je' id='nstzjs_je_2' value='' size='13' tabindex='2'>
				    
				   </td>
                  </tr>  
                  <tr> 
                   
					<td class="2-td2-left" nowrap>20</td>
					<td class="2-td2-left-qysds2" nowrap><input type='text'  name='xm'  value='' id='xm_3' size='30' tabindex='2'></td>
                   <td class="2-td2-center" nowrap><input type='text'  name='nstzjs_je' id='nstzjs_je_3' value='' size='13' tabindex='2'>
				
				   </td>
                  </tr>    
                  <tr> 
                    
					<td class="2-td2-left" nowrap>21</td>
					<td class="2-td2-left-qysds1" nowrap>合计（1行+…6行+16行+17行）</td>
                   <td class="2-td2-center" nowrap><input type='text'  name='je' id='18_1' value='' size='13' tabindex='2'>
				    <input type="hidden" name="hc" value="18"/>
				   </td>
                  </tr>    
				   <tr> 
                   
					
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
						onMouseOver="MM_swapImage('jiaoyan','','../../images/b-bdjyd2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" src="../../images/b-bdjyd1.jpg"
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

    </td>
  </tr>
</table>
<%@ include file="../include/footernew.jsp"%>
</html:form>

</body>
</html>