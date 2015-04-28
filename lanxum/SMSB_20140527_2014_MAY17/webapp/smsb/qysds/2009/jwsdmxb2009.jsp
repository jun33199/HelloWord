<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.jwsdmxb.web.JwsdmxbForm2009"%>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.syax.creports.Constants"%>

<html>
<head>
<title>境外所得税抵免计算明细表</title>
<link href="../../../css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/qysdsnew.js"></script>
</head>

<script language="JavaScript">
	<%/*初始化页面*/%>
	function doInit()
	{
		
		doInitNumText();
		
		<%
		JwsdmxbForm2009 jwsdmxbForm=(JwsdmxbForm2009)request.getAttribute("jwsdmxbForm2009");

		/**/
		if(jwsdmxbForm!=null && jwsdmxbForm.getZjdmList().size()>4){
			/*存在增加行时，先循环插入空行，再将值赋给所有子行显示（包括静态子行、动态子行）*/
			
			//动态子行数
			int dynamicZjdmRows=jwsdmxbForm.getZjdmList().size()-4;
			for(int i=0;i<dynamicZjdmRows;i++){
				%>
				InsertZjdmRow();
				<%
			}
		}
		
		/**/
		if(jwsdmxbForm!=null && jwsdmxbForm.getJjdmList().size()>4){
			/*存在增加行时，先循环插入空行，再将值赋给所有子行显示（包括静态子行、动态子行）*/
			
			//动态子行数
			int dynamicJjdmRows=jwsdmxbForm.getJjdmList().size()-4;
			for(int i=0;i<dynamicJjdmRows;i++){
				%>
				InsertJjdmRow();
				<%
			}
		}
		%>
		pageControl(document.forms[0].jwsddk);
		<%
		//赋值
		if(jwsdmxbForm!=null ){
			for(int i=0;i<jwsdmxbForm.getZjdmList().size();i++){
				HashMap map=(HashMap)jwsdmxbForm.getZjdmList().get(i);
				String hc =(String)map.get("zjhc");
				
				String strL1 =(String)map.get("ZJDM_L1")==null?"":(String)map.get("ZJDM_L1");
				String strL2 =(String)map.get("ZJDM_L2")==null?"":(String)map.get("ZJDM_L2");
				String strL3 =(String)map.get("ZJDM_L3")==null?"":(String)map.get("ZJDM_L3");
				String strL4 =(String)map.get("ZJDM_L4")==null?"":(String)map.get("ZJDM_L4");
				String strL5 =(String)map.get("ZJDM_L5")==null?"":(String)map.get("ZJDM_L5");
				String strL6 =(String)map.get("ZJDM_L6")==null?"":(String)map.get("ZJDM_L6");
				String strL7 =(String)map.get("ZJDM_L7")==null?"":(String)map.get("ZJDM_L7");
				String strL8 =(String)map.get("ZJDM_L8")==null?"":(String)map.get("ZJDM_L8");
				String strL9 =(String)map.get("ZJDM_L9")==null?"":(String)map.get("ZJDM_L9");
				String strL10=(String)map.get("ZJDM_L10")==null?"":(String)map.get("ZJDM_L10");
				String strL11=(String)map.get("ZJDM_L11")==null?"":(String)map.get("ZJDM_L11");
				String strL12=(String)map.get("ZJDM_L12")==null?"":(String)map.get("ZJDM_L12");
				String strL13=(String)map.get("ZJDM_L13")==null?"":(String)map.get("ZJDM_L13");
				String strL14=(String)map.get("ZJDM_L14")==null?"":(String)map.get("ZJDM_L14");
				String strL15=(String)map.get("ZJDM_L15")==null?"":(String)map.get("ZJDM_L15");
				String strL16=(String)map.get("ZJDM_L16")==null?"":(String)map.get("ZJDM_L16");
				String strL17=(String)map.get("ZJDM_L17")==null?"":(String)map.get("ZJDM_L17");
				%>
				setZjdmValue('<%=hc%>','<%=strL1%>','<%=strL2%>','<%=strL3%>','<%=strL4%>','<%=strL5%>','<%=strL6%>','<%=strL7%>','<%=strL8%>','<%=strL9%>','<%=strL10%>','<%=strL11%>','<%=strL12%>','<%=strL13%>','<%=strL14%>','<%=strL15%>','<%=strL16%>','<%=strL17%>');
				<%
			}
			
			for(int i=0;i<jwsdmxbForm.getJjdmList().size();i++){
				HashMap map=(HashMap)jwsdmxbForm.getJjdmList().get(i);
				String hc =(String)map.get("jjhc");
				
				String strL1 =(String)map.get("JJDM_L1")==null?"":(String)map.get("JJDM_L1");
				String strL2 =(String)map.get("JJDM_L2")==null?"":(String)map.get("JJDM_L2");
				String strL3 =(String)map.get("JJDM_L3")==null?"":(String)map.get("JJDM_L3");
				String strL4 =(String)map.get("JJDM_L4")==null?"":(String)map.get("JJDM_L4");
				String strL5 =(String)map.get("JJDM_L5")==null?"":(String)map.get("JJDM_L5");
				String strL6 =(String)map.get("JJDM_L6")==null?"":(String)map.get("JJDM_L6");
				String strL7 =(String)map.get("JJDM_L7")==null?"":(String)map.get("JJDM_L7");
				String strL8 =(String)map.get("JJDM_L8")==null?"":(String)map.get("JJDM_L8");
				String strL9 =(String)map.get("JJDM_L9")==null?"":(String)map.get("JJDM_L9");
				String strL10=(String)map.get("JJDM_L10")==null?"":(String)map.get("JJDM_L10");
				String strL11=(String)map.get("JJDM_L11")==null?"":(String)map.get("JJDM_L11");
				String strL12=(String)map.get("JJDM_L12")==null?"":(String)map.get("JJDM_L12");

				%>
				setJjdmValue('<%=hc%>','<%=strL1%>','<%=strL2%>','<%=strL3%>','<%=strL4%>','<%=strL5%>','<%=strL6%>','<%=strL7%>','<%=strL8%>','<%=strL9%>','<%=strL10%>','<%=strL11%>','<%=strL12%>');
				<%
			}
			
			for(int i=0;i<jwsdmxbForm.getHjList().size();i++){
					HashMap map=(HashMap)jwsdmxbForm.getHjList().get(i);
					int hc=Integer.parseInt((String)map.get("hjhc"));
					String hjje=(String)map.get("hjje");
					%>
					obj = eval("document.getElementById('HJ_<%=hc%>')");
					obj.value = '<%=hjje%>';
					<% 
			}
				
		}
		
		%>
		
	}
	
	//直接抵免 
	function setZjdmValue(hc,L1,L2,L3,L4,L5,L6,L7,L8,L9,L10,L11,L12,L13,L14,L15,L16,L17){
		var indexHc=parseInt(hc)-1;
		var zjdmL1=document.all("ZJDM_L1");
		zjdmL1[parseInt(indexHc)].value=L1;
		
		var zjdmL2=document.all("ZJDM_L2");
		zjdmL2[parseInt(indexHc)].value=L2;
		
		var zjdmL3=document.all("ZJDM_L3");
		zjdmL3[parseInt(indexHc)].value=L3;
		
		var zjdmL4=document.all("ZJDM_L4");
		zjdmL4[parseInt(indexHc)].value=L4;
		
		var zjdmL5=document.all("ZJDM_L5");
		zjdmL5[parseInt(indexHc)].value=L5;
		
		var zjdmL6=document.all("ZJDM_L6");
		zjdmL6[parseInt(indexHc)].value=L6;
		
		var zjdmL7=document.all("ZJDM_L7");
		zjdmL7[parseInt(indexHc)].value=L7;
		
		var zjdmL8=document.all("ZJDM_L8");
		zjdmL8[parseInt(indexHc)].value=L8;
		
		var zjdmL9=document.all("ZJDM_L9");
		zjdmL9[parseInt(indexHc)].value=L9;
		
		var zjdmL10=document.all("ZJDM_L10");
		zjdmL10[parseInt(indexHc)].value=L10;
		
		var zjdmL11=document.all("ZJDM_L11");
		zjdmL11[parseInt(indexHc)].value=L11;
		
		var zjdmL12=document.all("ZJDM_L12");
		zjdmL12[parseInt(indexHc)].value=L12;
		
		var zjdmL13=document.all("ZJDM_L13");
		zjdmL13[parseInt(indexHc)].value=L13;
		
		var zjdmL14=document.all("ZJDM_L14");
		zjdmL14[parseInt(indexHc)].value=L14;
		
		var zjdmL15=document.all("ZJDM_L15");
		zjdmL15[parseInt(indexHc)].value=L15;
		
		var zjdmL16=document.all("ZJDM_L16");
		zjdmL16[parseInt(indexHc)].value=L16;
		
		var zjdmL17=document.all("ZJDM_L17");
		zjdmL17[parseInt(indexHc)].value=L17;
		
	}
	
	//间接抵免 
	function setJjdmValue(hc,L1,L2,L3,L4,L5,L6,L7,L8,L9,L10,L11,L12){
		var indexHc=parseInt(hc)-1;
		var jjdmL1=document.all("JJDM_L1");
		jjdmL1[parseInt(indexHc)].value=L1;
		
		var jjdmL2=document.all("JJDM_L2");
		jjdmL2[parseInt(indexHc)].value=L2;
		
		var jjdmL3=document.all("JJDM_L3");
		jjdmL3[parseInt(indexHc)].value=L3;
		
		var jjdmL4=document.all("JJDM_L4");
		jjdmL4[parseInt(indexHc)].value=L4;
		
		var jjdmL5=document.all("JJDM_L5");
		jjdmL5[parseInt(indexHc)].value=L5;
		
		var jjdmL6=document.all("JJDM_L6");
		jjdmL6[parseInt(indexHc)].value=L6;
		
		var jjdmL7=document.all("JJDM_L7");
		jjdmL7[parseInt(indexHc)].value=L7;
		
		var jjdmL8=document.all("JJDM_L8");
		jjdmL8[parseInt(indexHc)].value=L8;
		
		var jjdmL9=document.all("JJDM_L9");
		jjdmL9[parseInt(indexHc)].value=L9;
		
		var jjdmL10=document.all("JJDM_L10");
		jjdmL10[parseInt(indexHc)].value=L10;
		
		var jjdmL11=document.all("JJDM_L11");
		jjdmL11[parseInt(indexHc)].value=L11;
		
		var jjdmL12=document.all("JJDM_L12");
		jjdmL12[parseInt(indexHc)].value=L12;
		
	}
	
	//插入子行
	function InsertZjdmRow(){	
		var list=document.getElementById("jwsd_list");
		
		var maxrow=document.forms[0].maxZjdmRowIndex.value;
		
		//插入新的行
		var newRow=list.insertRow(parseInt(maxrow));

		//国家或地区
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';         
		newCell.innerHTML="<input type='text' name='ZJDM_L1' value='' size='8' tabindex='1'>";
							
		//境外所得
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='ZJDM_L2' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//境外所得换算含税所得
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='ZJDM_L3' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//弥补以前年度亏损
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='ZJDM_L4' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//免税所得
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='ZJDM_L5' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//弥补亏损前境外应税所得额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='ZJDM_L6' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//可弥补境内亏损
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='ZJDM_L7' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//境外应纳税所得额 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='ZJDM_L8' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//税率(%)
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='ZJDM_L9' value='25' readOnly='true' class='text-noborder' style='text-align:center' size='1' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='-1'>%";
		
		//境外所得应纳税额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='ZJDM_L10' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//境外所得可抵免税额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='ZJDM_L11' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//境外所得税款抵免限额 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='ZJDM_L12'  value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//本年可抵免的境外所得税款 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='text' name='ZJDM_L13'  value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//未超过境外所得税款抵免限额的余额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='ZJDM_L14'  value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";

		//本年可抵免以前年度所得税额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='ZJDM_L15'  value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//前五年境外所得已缴税款未抵免余额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='ZJDM_L16'  value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//定率抵免
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='ZJDM_L17'  value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//复选框
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='chkZJ' value=''>";
		
		var td_zjdm=document.getElementById("td_zjdm");
		td_zjdm.rowSpan=td_zjdm.rowSpan+1;
		
		//最大行加 1 
		document.forms[0].maxZjdmRowIndex.value=parseInt(document.forms[0].maxZjdmRowIndex.value)+1;
		document.forms[0].maxJjdmRowIndex.value=parseInt(document.forms[0].maxJjdmRowIndex.value)+1;
		
		pageControl(document.forms[0].jwsddk);
		
	}
	
	//插入间接抵免行
	function InsertJjdmRow(){	
		var list=document.getElementById("jwsd_list");
		
		var maxrow=document.forms[0].maxJjdmRowIndex.value;

		//插入新的行
		var newRow=list.insertRow(parseInt(maxrow));

		//国家或地区
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';         
		newCell.innerHTML="<input type='text' name='JJDM_L1' value='' size='8' tabindex='1'>";
							
		//境外所得
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='JJDM_L2' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//境外所得换算含税所得
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='JJDM_L3' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//弥补以前年度亏损
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="*";
		
		//免税所得
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="*";
		
		//弥补亏损前境外应税所得额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='JJDM_L4' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//可弥补境内亏损
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='JJDM_L5' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//境外应纳税所得额 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='JJDM_L6' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//税率(%)
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='JJDM_L7' value='25' readOnly='true' class='text-noborder' style='text-align:center' size='1' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='-1'>%";
		
		//境外所得应纳税额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='JJDM_L8' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//境外所得可抵免税额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='JJDM_L9' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//境外所得税款抵免限额 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='JJDM_L10'  value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//本年可抵免的境外所得税款 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='text' name='JJDM_L11'  value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//未超过境外所得税款抵免限额的余额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="*";

		//本年可抵免以前年度所得税额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="*";
		
		//前五年境外所得已缴税款未抵免余额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="*";
		
		//定率抵免
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='JJDM_L12'  value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'>";
		
		//复选框
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='chkJJ' value=''>";
		
		var td_jjdm=document.getElementById("td_jjdm");
		td_jjdm.rowSpan=td_jjdm.rowSpan+1;
		
		//最大行加 1 
		document.forms[0].maxJjdmRowIndex.value=parseInt(document.forms[0].maxJjdmRowIndex.value)+1;
		
		pageControl(document.forms[0].jwsddk);
		
	}
	
	function RomoveZjdmRow()
	{
		//复选框
		var arrChk=document.all("chkZJ");
		var list=document.getElementById("jwsd_list");
		var flag =true;
		
		//初始情况下，只有一个Checkbox时，不形成数组，故直接引用
		//当有多个checkbox时，或由多个删除为一个时，其实还是在数组里，所以
		//特作如下判断
		if(arrChk!=null){
			if(arrChk.length==undefined){
				if(arrChk.checked){
					if(window.confirm("该操作只是从页面上删除增加的数据行，确认要删除选中的数据行吗？")){
						list.deleteRow(6);
						var td_zjdm=document.getElementById("td_zjdm");
						td_zjdm.rowSpan=td_zjdm.rowSpan-1;
						document.forms[0].maxZjdmRowIndex.value=parseInt(document.forms[0].maxZjdmRowIndex.value)-1;
						document.forms[0].maxJjdmRowIndex.value=parseInt(document.forms[0].maxJjdmRowIndex.value)-1;
					}else{
						arrChk.checked=false;
					}
				}else{
					alert("请先选中要删除的数据行！");
					return false;
				}
			}
			
			if(arrChk.length>=1){
				for(var i=arrChk.length-1;i>=0;i--){
					if(arrChk[i].checked){
						flag=false;
						if(window.confirm("该操作只是从页面上删除增加的数据行，确认要删除选中的数据行吗？")){
							break;
						}else{
							return false;
						}
					}
				}
				
				if(flag){
					alert("请先选中要删除的数据行！");
					return false;
				}
			}
			
			if(arrChk.length>=1){
				for(var i=arrChk.length-1;i>=0;i--){
					if(arrChk[i].checked){
							list.deleteRow(i+6);
							var td_zjdm=document.getElementById("td_zjdm");
							td_zjdm.rowSpan=td_zjdm.rowSpan-1;
							document.forms[0].maxZjdmRowIndex.value=parseInt(document.forms[0].maxZjdmRowIndex.value)-1;
							document.forms[0].maxJjdmRowIndex.value=parseInt(document.forms[0].maxJjdmRowIndex.value)-1;
					}
				}
			}

		}
	
		return false;
	}
	
	function RomoveJjdmRow()
	{
		//复选框
		var arrChk=document.all("chkJJ");
		var list=document.getElementById("jwsd_list");
		var flag =true;
		
		//初始情况下，只有一个Checkbox时，不形成数组，故直接引用
		//当有多个checkbox时，或由多个删除为一个时，其实还是在数组里，所以
		//特作如下判断
		if(arrChk!=null){
			if(arrChk.length==undefined){
				if(arrChk.checked){
					if(window.confirm("该操作只是从页面上删除增加的数据行，确认要删除选中的数据行吗？")){
						list.deleteRow(parseInt(document.forms[0].maxJjdmRowIndex.value)-1);
						var td_jjdm=document.getElementById("td_jjdm");
						td_jjdm.rowSpan=td_jjdm.rowSpan-1;
						document.forms[0].maxJjdmRowIndex.value=parseInt(document.forms[0].maxJjdmRowIndex.value)-1;
					}else{
						arrChk.checked=false;
					}
				}else{
					alert("请先选中要删除的数据行！");
					return false;
				}
			}
			
			if(arrChk.length>=1){
				for(var i=arrChk.length-1;i>=0;i--){
					if(arrChk[i].checked){
						
						flag=false;
						if(window.confirm("该操作只是从页面上删除增加的数据行，确认要删除选中的数据行吗？")){
							break;
						}else{
							return false;
						}
						
					}
				}
				
				if(flag){
					alert("请先选中要删除的数据行！");
					return false;
				}
			}
			
			if(arrChk.length>=1){
				for(var i=arrChk.length-1;i>=0;i--){
					if(arrChk[i].checked){
						list.deleteRow(i+parseInt(document.forms[0].maxZjdmRowIndex.value)+4);
						var td_jjdm=document.getElementById("td_jjdm");
						td_jjdm.rowSpan=td_jjdm.rowSpan-1;
						document.forms[0].maxJjdmRowIndex.value=parseInt(document.forms[0].maxJjdmRowIndex.value)-1;
					}
				}
			}
		}
		
		return false;
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
	<%/*单表校验*/%>
	function doCheck()
	{
		var list="<%=Constants.QYSDS_CONTROL_CHAR_FOR_JS%>";
		if (!Char_Vaildate(document.forms[0],list)){
			return false;	
		}
		doSubmitForm('Check');
	}
	<%/*清除页面数据*/%>
	function doReset()
	{
	    if(confirm("确认是否清除当前数据？"))
	    {
	    	var maxZjRow=parseInt(document.forms[0].maxZjdmRowIndex.value)-2;
	    	var arr = new Array();	    	
	    	for(var i=0;i<17;i++){
	    		arr[i]=document.all('ZJDM_L'+(i+1));
	    		for(var j=0;j<parseInt(maxZjRow);j++){	    			
	    			if(arr[i][j].readOnly!=true){
		    			arr[i][j].value="";	  
	    			}	    			  			    	
	    		}
	    	}
				
				var maxJjRow=parseInt(document.forms[0].maxJjdmRowIndex.value)-parseInt(document.forms[0].maxZjdmRowIndex.value);
				for(var i=0;i<12;i++){
	    		arr[i]=document.all('JJDM_L'+(i+1));
	    		for(var j=0;j<parseInt(maxJjRow);j++){	    			
	    			if(arr[i][j].readOnly!=true){
		    			arr[i][j].value="";	  
	    			}	    			  			    	
	    		}
	    	}
	    	
	    	for(var i=31;i<=45;i++){
	    		var obj=document.getElementById("HJ_"+i);
	    		obj.value="";
	    	}
	    }
	}	
	<%/*删除*/%>
	function doDelete()
	{	
		doSubmitForm('Delete');
	}
	
	<%/*返回*/%>
	function doExit()
	{
		doSubmitForm('Exit');
	}
	
	<%/*单元格输入数据格式的控制*/%>
	function doInitNumText(){
	    for(var i=2;i<18;i++){
	    	var L ='ZJDM_L'+i;
	    	initNumText(L);
	    }	
	    
	    for(var i=2;i<13;i++){
	    	var L ='JJDM_L'+i;
	    	initNumText(L);
	    }
	    
	    initNumText("hjje");
	   
	}
	
	/**
	 * 根据选择的抵扣方法进行页面控制，参数说明如下
	 * dkff：抵扣方法 01-定绿抵扣 02-分国不分项
	 * 业务说明
	 *     选择定率抵扣，列11至列16各行不允许录入
	 *     选择分国不分项，列17各行不允许录入
	 */
	function pageControl(obj){
		var dkff=obj.value;
		var arr = new Array();
		if(dkff=="01"){
			/*--------直接抵免--------*/
			//直接抵免数据行数
			var zjRow= parseInt(document.forms[0].maxZjdmRowIndex.value)-2;
			for(var i=11;i<=16;i++){
    		arr[i]=document.all('ZJDM_L'+i);
    		for(var j=0;j<parseInt(zjRow);j++){	    			
    			arr[i][j].readOnly=true;	   		
    			arr[i][j].className="text-gray";	  	
    			arr[i][j].value="";
    			arr[i][j].tabIndex="-1";		    	
    		}
	  	}
	  	
			/*--------直接抵免--------*/
			//间接抵免数据行数
			var jjRow=parseInt(document.forms[0].maxJjdmRowIndex.value)-parseInt(document.forms[0].maxZjdmRowIndex.value);
			for(var i=9;i<=11;i++){
    		arr[i]=document.all('JJDM_L'+i);
    		for(var j=0;j<parseInt(jjRow);j++){	    			
    			arr[i][j].readOnly=true;	   		
    			arr[i][j].className="text-gray";	  	
    			arr[i][j].value="";
    			arr[i][j].tabIndex="-1";		    	
    		}
	  	}
			
			/*--------合计行----------*/
			for(var i=39;i<=44;i++){
				var hj=document.getElementById("HJ_"+i);
				hj.readOnly=true;	   		
    		hj.className="text-gray";	  	
    		hj.value="";
    		hj.tabIndex="-1";		 
			}
			
			/*---激活---列17*/
			var arr17=document.all('ZJDM_L17');
  		for(var j=0;j<parseInt(zjRow);j++){	    			
  			arr17[j].readOnly=false;	   		
  			arr17[j].className="";	  	
  			//arr17[j].value="";
  			arr17[j].tabIndex="1";		    	
  		}
    	
    	var arr17=document.all('JJDM_L12');
  		for(var j=0;j<parseInt(jjRow);j++){	    			
  			arr17[j].readOnly=false;	   		
  			arr17[j].className="";	  	
  			//arr17[j].value="";
  			arr17[j].tabIndex="1";		    	
  		}
  		
  		var hj45=document.getElementById("HJ_45");
  		hj45.readOnly=false;	   		
  		hj45.className="";	  	
  		//hj45.value="";
  		hj45.tabIndex="1";		 
  		
		}else if(dkff=="02"){
			/*--------直接抵免--------*/
			//直接抵免数据行数
			var zjRow= parseInt(document.forms[0].maxZjdmRowIndex.value)-2;
			for(var i=11;i<=16;i++){
    		arr[i]=document.all('ZJDM_L'+i);
    		for(var j=0;j<parseInt(zjRow);j++){	    			
    			arr[i][j].readOnly=false;	   		
    			arr[i][j].className="";	  	
    			//arr[i][j].value="";
    			arr[i][j].tabIndex="1";		    	
    		}
	  	}
	  	
			/*--------直接抵免--------*/
			//间接抵免数据行数
			var jjRow=parseInt(document.forms[0].maxJjdmRowIndex.value)-parseInt(document.forms[0].maxZjdmRowIndex.value);
			for(var i=9;i<=11;i++){
    		arr[i]=document.all('JJDM_L'+i);
    		for(var j=0;j<parseInt(jjRow);j++){	    			
    			arr[i][j].readOnly=false;	   		
    			arr[i][j].className="";	  	
    			//arr[i][j].value="";
    			arr[i][j].tabIndex="1";		    	
    		}
	  	}
			
			/*--------合计行----------*/
			for(var i=39;i<=44;i++){
				var hj=document.getElementById("HJ_"+i);
				hj.readOnly=false;	   		
    		hj.className="";	  	
    		//hj.value="";
    		hj.tabIndex="1";		 
			}
			
			/*---Disable---列17*/
			var arr17=document.all('ZJDM_L17');
  		for(var j=0;j<parseInt(zjRow);j++){	    			
  			arr17[j].readOnly=true;	   		
  			arr17[j].className="text-gray";	  	
  			arr17[j].value="";
  			arr17[j].tabIndex="-1";		    	
  		}
    	
    	var arr17=document.all('JJDM_L12');
  		for(var j=0;j<parseInt(jjRow);j++){	    			
  			arr17[j].readOnly=true;	   		
  			arr17[j].className="text-gray";	  	
  			arr17[j].value="";
  			arr17[j].tabIndex="-1";		    	
  		}
  		
  		var hj45=document.getElementById("HJ_45");
  		hj45.readOnly=true;	   		
  		hj45.className="text-gray";	  	
  		hj45.value="";
  		hj45.tabIndex="-1";	
		}else{
			alert("程序错误，传入的抵扣参数不正确！");	
		}
	}

	
</script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" style="margin:0" onload="doInit()">
<%@include file="../../include/header.jsp"%>

<html:form action="/webapp/smsb/qysds/2009/jwsdmxbAction2009.do">
	<html:hidden property="actionType" />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />
	<input type="hidden" name="maxZjdmRowIndex" value="6">
	<input type="hidden" name="maxJjdmRowIndex" value="10">
	
	<table width="96%" align="center" cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">境外所得税抵免计算明细表</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;申报日期:<bean:write
				name="jwsdmxbForm2009" property="sbrq" scope="request" filter="true" />
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
			<td class="1-td2">&nbsp;&nbsp;计算机代码:<bean:write name="jwsdmxbForm2009"
				property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;纳税人名称：<bean:write
				name="jwsdmxbForm2009" property="nsrmc" scope="request" filter="true" />&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			境外所得已纳税款抵扣方法：<html:select property="jwsddk" onchange="pageControl(this)" >
				<html:option value="<%=CodeConstant.JWSDDK01%>">定率抵扣</html:option>
				<html:option value="<%=CodeConstant.JWSDDK02%>">分国不分项抵扣</html:option>
			</html:select></td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">
			<TABLE class="table-99" align="center">
				<TR>
					<TD>
					<table id="jwsd_list" border="1" cellspacing="0" class="table-99"
						align="center">
						<tr>
							<td class="2-td1-left">抵免方式</td>
							<td class="2-td1-left">国家或地区</td>
							<td class="2-td1-left">境外所得</td>
							<td class="2-td1-left">境外所得换算含税所得</td>
							<td class="2-td1-left">弥补以前年度亏损</td>
							<td class="2-td1-left">免税所得</td>
							<td class="2-td1-left">弥补亏损前境外应税所得额</td>
							<td class="2-td1-left">可弥补境内亏损</td>
							<td class="2-td1-left">境外应纳税所得额</td>
							<td class="2-td1-left">税率(%)</td>
							<td class="2-td1-left">境外所得应纳税额</td>
							<td class="2-td1-left">境外所得可抵免税额</td>
							<td class="2-td1-left">境外所得税款抵免限额</td>
							<td class="2-td1-left">本年可抵免的境外所得税款</td>
							<td class="2-td1-left">未超过境外所得税款抵免限额的余额</td>
							<td class="2-td1-left">本年可抵免以前年度所得税额</td>
							<td class="2-td1-left">前五年境外所得已缴税款未抵免余额</td>
							<td class="2-td1-center">定率抵免</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>列次</td>
							<td class="2-td2-left" nowrap>1</td>
							<td class="2-td2-left" nowrap>2</td>
							<td class="2-td2-left" nowrap>3</td>
							<td class="2-td2-left" nowrap>4</td>
							<td class="2-td2-left" nowrap>5</td>
							<td class="2-td2-left" nowrap>6（3－4－5）</td>
							<td class="2-td2-left" nowrap>7</td>
							<td class="2-td2-left" nowrap>8（6－7）</td>
							<td class="2-td2-left" nowrap>9</td>
							<td class="2-td2-left" nowrap>10（8×9）</td>
							<td class="2-td2-left" nowrap>11</td>
							<td class="2-td2-left" nowrap>12</td>
							<td class="2-td2-left" nowrap>13</td>
							<td class="2-td2-left" nowrap>14（12－13）</td>
							<td class="2-td2-left" nowrap>15</td>
							<td class="2-td2-left" nowrap>16</td>
							<td class="2-td2-center" nowrap>17</td>
						</tr>
						<tr>
							<td id='td_zjdm' class="2-td2-left" rowspan=4 style="layout-flow:vertical-ideographic" nowrap>直接抵免</td>
							<input type='hidden' name='zjhc' id='zjhc_1' value='1'>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L1'
								id='1_1' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L2'
								id='1_2' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L3'
								id='1_3' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L4'
								id='1_4' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L5'
								id='1_5' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L6'
								id='1_6' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L7'
								id='1_7' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L8'
								id='1_8' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L9'
								id='1_9' value='25' readOnly='true' class='text-noborder' style='text-align:center' size='1' tabindex='-1'>%</td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L10'
								id='1_10' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L11'
								id='1_11' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L12'
								id='1_12' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L13'
								id='1_13' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L14'
								id='1_14' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L15'
								id='1_15' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L16'
								id='1_16' value='' size='8' tabindex='1'></td>
							<td class="2-td2-center" nowrap><input type='text' name='ZJDM_L17'
								id='1_17' value='' size='8' tabindex='1'></td>
						</tr>
						<tr>
							<input type='hidden' name='zjhc' id='zjhc_2' value='2'>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L1'
								id='2_1' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L2'
								id='2_2' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L3'
								id='2_3' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L4'
								id='2_4' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L5'
								id='2_5' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L6'
								id='2_6' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L7'
								id='2_7' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L8'
								id='2_8' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L9'
								id='2_9' value='25' readOnly='true' class='text-noborder' style='text-align:center' size='1' tabindex='-1'>%</td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L10'
								id='2_10' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L11'
								id='2_11' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L12'
								id='2_12' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L13'
								id='2_13' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L14'
								id='2_14' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L15'
								id='2_15' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L16'
								id='2_16' value='' size='8' tabindex='1'></td>
							<td class="2-td2-center" nowrap><input type='text' name='ZJDM_L17'
								id='2_17' value='' size='8' tabindex='1'></td>
						</tr>
						<tr>

							<input type='hidden' name='zjhc' id='zjhc_3' value='3'>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L1'
								id='3_1' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L2'
								id='3_2' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L3'
								id='3_3' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L4'
								id='3_4' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L5'
								id='3_5' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L6'
								id='3_6' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L7'
								id='3_7' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L8'
								id='3_8' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L9'
								id='3_9' value='25' readOnly='true' class='text-noborder' style='text-align:center' size='1' tabindex='-1'>%</td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L10'
								id='3_10' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L11'
								id='3_11' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L12'
								id='3_12' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L13'
								id='3_13' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L14'
								id='3_14' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L15'
								id='3_15' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L16'
								id='3_16' value='' size='8' tabindex='1'></td>
							<td class="2-td2-center" nowrap><input type='text' name='ZJDM_L17'
								id='3_17' value='' size='8' tabindex='1'></td>
						</tr>
						<tr>
							<input type='hidden' name='zjhc' id='zjhc_4' value='4'>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L1'
								id='4_1' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L2'
								id='4_2' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L3'
								id='4_3' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L4'
								id='4_4' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L5'
								id='4_5' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L6'
								id='4_6' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L7'
								id='4_7' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L8'
								id='4_8' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L9'
								id='4_9' value='25' readOnly='true' class='text-noborder' style='text-align:center' size='1' tabindex='-1'>%</td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L10'
								id='4_10' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L11'
								id='4_11' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L12'
								id='4_12' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L13'
								id='4_13' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L14'
								id='4_14' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L15'
								id='4_15' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L16'
								id='4_16' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='ZJDM_L17'
								id='4_17' value='' size='8' tabindex='1'></td>
							<td ><input name="button" type="button" tabindex='-1'
							value="增加" onClick="InsertZjdmRow()"></td>
							<td><input name="button" type="button" tabindex='-1'
								value='删除' onClick="RomoveZjdmRow()"></td>
						</tr>
						<tr>

							<td id='td_jjdm' class="2-td2-left" rowspan=4 style="layout-flow:vertical-ideographic" nowrap>间接抵免</td>
							<input type='hidden' name='jjhc' id='jjhc_1' value='1'>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L1'
								id='jj_1_1' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L2'
								id='jj_1_2' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L3'
								id='jj_1_3' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap>*</td>
							<td class="2-td2-left" nowrap>*</td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L4'
								id='jj_1_4' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L5'
								id='jj_1_5' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L6'
								id='jj_1_6' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L7'
								id='jj_1_7' value='25' readOnly='true' class='text-noborder' style='text-align:center' size='1' tabindex='-1'>%</td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L8'
								id='jj_1_8' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L9'
								id='jj_1_9' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L10'
								id='jj_1_10' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L11'
								id='jj_1_11' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap>*</td>
							<td class="2-td2-left" nowrap>*</td>
							<td class="2-td2-left" nowrap>*</td>
							<td class="2-td2-center" nowrap><input type='text' name='JJDM_L12'
								id='jj_1_12' value='' size='8' tabindex='1'></td>
						</tr>
						<tr>

							<input type='hidden' name='jjhc' id='jjhc_2' value='2'>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L1'
								id='jj_2_1' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L2'
								id='jj_2_2' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L3'
								id='jj_2_3' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap>*</td>
							<td class="2-td2-left" nowrap>*</td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L4'
								id='jj_2_4' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L5'
								id='jj_2_5' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L6'
								id='jj_2_6' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L7'
								id='jj_2_7' value='25' readOnly='true' class='text-noborder' style='text-align:center' size='1' tabindex='-1'>%</td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L8'
								id='jj_2_8' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L9'
								id='jj_2_9' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L10'
								id='jj_2_10' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L11'
							id='jj_2_11' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap>*</td>
							<td class="2-td2-left" nowrap>*</td>
							<td class="2-td2-left" nowrap>*</td>
							<td class="2-td2-center" nowrap><input type='text' name='JJDM_L12'
								id='jj_2_12' value='' size='8' tabindex='1'></td>
						</tr>
						<tr>
							<input type='hidden' name='jjhc' id='jjhc_3' value='3'>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L1'
								id='jj_3_1' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L2'
								id='jj_3_2' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L3'
								id='jj_3_3' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap>*</td>
							<td class="2-td2-left" nowrap>*</td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L4'
								id='jj_3_4' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L5'
								id='jj_3_5' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L6'
								id='jj_3_6' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L7'
								id='jj_3_7' value='25' readOnly='true' class='text-noborder' style='text-align:center' size='1' tabindex='-1'>%</td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L8'
								id='jj_3_8' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L9'
								id='jj_3_9' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L10'
								id='jj_3_10' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L11'
							id='jj_3_11' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap>*</td>
							<td class="2-td2-left" nowrap>*</td>
							<td class="2-td2-left" nowrap>*</td>
							<td class="2-td2-center" nowrap><input type='text' name='JJDM_L12'
								id='jj_3_12' value='' size='8' tabindex='1'></td>
						</tr>
						<tr>
							<input type='hidden' name='jjhc' id='jjhc_4' value='4'>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L1'
								id='jj_4_1' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L2'
								id='jj_4_2' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L3'
								id='jj_4_3' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap>*</td>
							<td class="2-td2-left" nowrap>*</td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L4'
								id='jj_4_4' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L5'
								id='jj_4_5' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L6'
								id='jj_4_6' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L7'
								id='jj_4_7' value='25' readOnly='true' class='text-noborder' style='text-align:center' size='1' tabindex='-1'>%</td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L8'
								id='jj_4_8' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L9'
								id='jj_4_9' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L10'
								id='jj_4_10' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L11'
								id='jj_4_11' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap>*</td>
							<td class="2-td2-left" nowrap>*</td>
							<td class="2-td2-left" nowrap>*</td>
							<td class="2-td2-left" nowrap><input type='text' name='JJDM_L12'
								id='jj_4_12' value='' size='8' tabindex='1'></td>
							<td><input name="button" type="button" tabindex='-1'
								value="增加" onClick="InsertJjdmRow()"></td>
							<td><input name="button" type="button" tabindex='-1'
								value='删除' onClick="RomoveJjdmRow()"></td>
						</tr>
						<tr>
							<td class="2-td2-left"  nowrap>合计</td>
							
							<td class="2-td2-left" nowrap>*</td>
								
							<input type='hidden' name='hjhc' id='hjhc_31' value='31'>
							<td class="2-td2-left" nowrap><input type='text' name='hjje'
								id='HJ_31' value='' size='8' tabindex='1'></td>
								
							<input type='hidden' name='hjhc' id='hjhc_32' value='32'>
							<td class="2-td2-left" nowrap><input type='text' name='hjje'
								id='HJ_32' value='' size='8' tabindex='1'></td>
								
							<input type='hidden' name='hjhc' id='hjhc_33' value='33'>
							<td class="2-td2-left" nowrap><input type='text' name='hjje'
								id='HJ_33' value='' size='8' tabindex='1'></td>
								
							<input type='hidden' name='hjhc' id='hjhc_34' value='34'>
							<td class="2-td2-left" nowrap><input type='text' name='hjje'
								id='HJ_34' value='' size='8' tabindex='1'></td>
								
							<input type='hidden' name='hjhc' id='hjhc_35' value='35'>
							<td class="2-td2-left" nowrap><input type='text' name='hjje'
								id='HJ_35' value='' size='8' tabindex='1'></td>
								
							<input type='hidden' name='hjhc' id='hjhc_36' value='36'>
							<td class="2-td2-left" nowrap><input type='text' name='hjje'
								id='HJ_36' value='' size='8' tabindex='1'></td>
								
							<input type='hidden' name='hjhc' id='hjhc_37' value='37'>
							<td class="2-td2-left" nowrap><input type='text' name='hjje'
								id='HJ_37' value='' size='8' tabindex='1'></td>

							<td class="2-td2-left" nowrap>*</td>
								
							<input type='hidden' name='hjhc' id='hjhc_38' value='38'>
							<td class="2-td2-left" nowrap><input type='text' name='hjje'
								id='HJ_38' value='' size='8' tabindex='1'></td>
							
							<input type='hidden' name='hjhc' id='hjhc_39' value='39'>
							<td class="2-td2-left" nowrap><input type='text' name='hjje'
								id='HJ_39' value='' size='8' tabindex='1'></td>
								
							<input type='hidden' name='hjhc' id='hjhc_40' value='40'>
							<td class="2-td2-left" nowrap><input type='text' name='hjje'
								id='HJ_40' value='' size='8' tabindex='1'></td>
							
							<input type='hidden' name='hjhc' id='hjhc_41' value='41'>
							<td class="2-td2-left" nowrap><input type='text' name='hjje'
								id='HJ_41' value='' size='8' tabindex='1'></td>
								
							<input type='hidden' name='hjhc' id='hjhc_42' value='42'>
							<td class="2-td2-left" nowrap><input type='text' name='hjje'
								id='HJ_42' value='' size='8' tabindex='1'></td>
								
							<input type='hidden' name='hjhc' id='hjhc_43' value='43'>
							<td class="2-td2-left" nowrap><input type='text' name='hjje'
								id='HJ_43' value='' size='8' tabindex='1'></td>
								
							<input type='hidden' name='hjhc' id='hjhc_44' value='44'>
							<td class="2-td2-left" nowrap><input type='text' name='hjje'
								id='HJ_44' value='' size='8' tabindex='1'></td>
								
							<input type='hidden' name='hjhc' id='hjhc_45' value='45'>
							<td class="2-td2-center" nowrap><input type='text' name='hjje'
								id='HJ_45' value='' size='8' tabindex='1'></td>
								
							
						</tr>
					</table>
					</TD>
				</TR>
				<TR class="black9">
					<TD>

					<div align="center"><a style="cursor:hand" id="previous"
				onclick="gotoPreviousTable()"><img name="spage" value="上一张表"
				alt="填写上一张表"
				onMouseOver="MM_swapImage('previoustable','','<%=static_contextpath%>images/shangyiye2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				src="<%=static_contextpath%>images/shangyiye1.jpg" id="previoustable">
			</a>&nbsp;&nbsp;
			
			 <a style="cursor:hand" id="next"
				onclick="gotoNextTable()"><img name="xpage" value="下一张表"
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
						onClick="doCheck();return false;" accesskey="d" value="单表校验"
						alt="表内关系校验"
						onMouseOver="MM_swapImage('jiaoyan','','../../../images/b-bdjyd2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" src="../../../images/b-bdjyd1.jpg"
						 id="jiaoyan" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="doDelete();return false;" accesskey="x" value="全部删除"
						alt="删除本表所有数据，且不可恢复"
						onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qbsc-x1.jpg" 
						id="shanchu" /> &nbsp;&nbsp; <input type="image" value="返回"
						alt="返回到企业所得税年报主页面" style="cursor:hand" tabIndex="-1"
						onClick="doExit();return false;"
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

	<%@ include file="../../include/footernew.jsp"%>
</html:form>

</body>
</html>


