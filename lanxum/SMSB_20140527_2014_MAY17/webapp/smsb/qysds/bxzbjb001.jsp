<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.bxzbjb.web.BxzbjForm"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.syax.creports.Constants"%>

<html>
<head>
<title>保险准备金提转差纳税调整表</title>
<link href="../../css/text.css" rel="stylesheet" type="text/css">

</head>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/smsb_common.js"></script>
<script language="JavaScript" src="../../js/DTable.js"></script>
<script language=JavaScript src="../../js/reader.js"></script>
<script language=JavaScript src="../../js/compute.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/qysdsnew.js"></script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onLoad="doInit()">
<%@ include file="../include/header.jsp"%>
<script language="JavaScript">
	
	<%/*初始化*/%>
	function doInit()
	{
	    //初始化文本框onChange事件处理
		initNumText("sjfse",32);
		initNumText("sfgdkce",32);
		initNumText("tze",32);
		                 
		initNumText("qtzbj_sjfse",parseInt(document.forms[0].otherZbjMax.value));
		initNumText("qtzbj_sfgdkce",parseInt(document.forms[0].otherZbjMax.value));
		initNumText("qtzbj_tze",parseInt(document.forms[0].otherZbjMax.value));
		initNumText("qt_sjfse",parseInt(document.forms[0].otherMax.value));
		initNumText("qt_sfgdkce",parseInt(document.forms[0].otherMax.value));
		initNumText("qt_tze",parseInt(document.forms[0].otherMax.value));
		//删除子行，最大子行数置零
		var list=document.getElementById("bxzbj_list");
		for(var i=parseInt(document.forms[0].otherZbjMax.value);i>2;i--){
			list.deleteRow( parseInt(27+parseInt(i-1)) );
		}
		for(var i=parseInt(document.forms[0].otherMax.value);i>4;i--){
			list.deleteRow( parseInt(35+parseInt(i-1)) );
		}
		  document.forms[0].otherZbjMax.value='2';
		  document.forms[0].otherMax.value='4';
		
		<%
		BxzbjForm bxzbjForm=(BxzbjForm)request.getAttribute("bxzbjForm");
		if (bxzbjForm!=null && bxzbjForm.getDataList().size()>0)
		{
			/*显示固定行列内容*/
			for(int i=0;i<bxzbjForm.getDataList().size();i++)
			{
				HashMap map=(HashMap)bxzbjForm.getDataList().get(i);
				int hc=Integer.parseInt((String)map.get("hc"));
				String sjfse=(String)map.get("sjfse");
				String sfgdkce=(String)map.get("sfgdkce");
				String tze=(String)map.get("tze");
				%>
				obj = eval("document.getElementById('sjfse_<%=hc%>')");
				obj.value = '<%=sjfse%>';
				
				obj = eval("document.getElementById('sfgdkce_<%=hc%>')");
				obj.value = '<%=sfgdkce%>';
				
				obj = eval("document.getElementById('tze_<%=hc%>')");
				obj.value = '<%=tze%>';
				<% 
			}
		}
		
		
		/*显示其他准备金（26主行次）子行列内容*/
		if (bxzbjForm!=null && bxzbjForm.getQtzbjList().size()>2)
		{
			/*存在增加行时，先循环插入空行，再将值赋给所有子行显示（包括静态子行、动态子行）*/
			int dynamicRows=bxzbjForm.getQtzbjList().size()-2;
			for(int i=0;i<dynamicRows;i++)
			{
				%>
				DynamicAddQtzbj();
				<%
			    }
		   }
		
			//赋值
			if(bxzbjForm!=null)
			{
			for(int i=0;i<bxzbjForm.getQtzbjList().size();i++)
			{
				Map map=(HashMap)bxzbjForm.getQtzbjList().get(i);
				String xm=(String)map.get("qtzbj_xm");
				String sjfse=(String)map.get("qtzbj_sjfse");
				String sfgdkce=(String)map.get("qtzbj_sfgdkce");
				String tze=(String)map.get("qtzbj_tze");
				%>
			      setQtzbjValue('<%=i%>','<%=xm%>','<%=sjfse%>','<%=sfgdkce%>','<%=tze%>');
				<%
			}
		}
		/*显示其他（32主行次）子行列内容*/
		if (bxzbjForm!=null && bxzbjForm.getQtList().size()>4)
		{
		/*存在增加行时，先循环插入空行，再将值赋给所有子行显示（包括静态子行、动态子行）*/
		    int dynamicRows=bxzbjForm.getQtList().size()-4;
			
			for(int i=0;i<dynamicRows;i++)
			{
			%>
				DynamicAddQt();
				<%
			    }
		   }
			
			//赋值
			if(bxzbjForm!=null)
			{
			  for(int i=0;i<bxzbjForm.getQtList().size();i++)
			  {
		         Map map=(HashMap)bxzbjForm.getQtList().get(i);
				 String xm=(String)map.get("qt_xm");
				 String sjfse=(String)map.get("qt_sjfse");
				 String sfgdkce=(String)map.get("qt_sfgdkce");
				 String tze=(String)map.get("qt_tze");
				%>
				setQtValue('<%=i%>','<%=xm%>','<%=sjfse%>','<%=sfgdkce%>','<%=tze%>');
				<%
			}
		}
		%>
	}
	
	//32主行次 子行内容 从ActionForm中取List数据插入到table	
	function DynamicAddQtzbj()
	{
		var list=document.getElementById("bxzbj_list");
		//当前“其他准备金”最大子行数
		var hc=document.forms[0].otherZbjMax.value;
		var newRow=list.insertRow(27+parseInt(hc));
		
		//行次
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp";

		//项目名称
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left-qysds2';
		newCell.innerHTML="<input type='text' name='qtzbj_xm'  value='' size='25' tabindex='2'>";
		
		
		//实际发生额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='qtzbj_sjfse'  value='' size='13' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//税法规定扣除额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='qtzbj_sfgdkce'   value='' size='13' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//调整额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		//newCell.innerHTML="<input type='text' name='qtzbj_tze'  id='qtzbj_tze_"+parseInt(hc)+1+"' value='"+tze+"' size='13' tabindex='2'>";
		newCell.innerHTML="<input type='text' name='qtzbj_tze'   value='' size='13' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//复选框
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='chk' value=''>";
		
		//当前“其他准备金”最大子行数加1
		document.forms[0].otherZbjMax.value=parseInt(document.forms[0].otherZbjMax.value)+1;
		
	}
	//26主行次____其他准备金子行赋值
	function setQtzbjValue(i,L1,L2,L3,L4){
		var qtzbj_xm=document.all("qtzbj_xm");
		qtzbj_xm[parseInt(i)].value=L1
		
		var qtzbj_sjfse=document.all("qtzbj_sjfse");
		qtzbj_sjfse[parseInt(i)].value=L2
		
		var qtzbj_sfgdkce=document.all("qtzbj_sfgdkce");
		qtzbj_sfgdkce[parseInt(i)].value=L3
		
		var qtzbj_tze=document.all("qtzbj_tze");
		qtzbj_tze[parseInt(i)].value=L4
		
	 }
		
	//32主行次 子行内容 从ActionForm中取List数据插入到table	
	function DynamicAddQt()
	{
		var list=document.getElementById("bxzbj_list");

		//当前“其他准备金”最大子行数
		var qtzbjhc=document.forms[0].otherZbjMax.value;

		//当前“其他”最大子行数
		var qthc=document.forms[0].otherMax.value;
		
		var newRow=list.insertRow(33+parseInt(qtzbjhc)+parseInt(qthc));
		
		//行次
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp";

		//项目名称
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left-qysds3';
		//去掉id,value置空以下同
		newCell.innerHTML="<input type='text' name='qt_xm'  value='' size='25' tabindex='2'>";
		
		//实际发生额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='qt_sjfse'  value='' size='13' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//税法规定扣除额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='qt_sfgdkce'   value='' size='13' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//调整额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='qt_tze'   value='' size='13' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//复选框
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='qtchk' value=''>";
		
		//当前“其他准备金”最大子行数加1
		document.forms[0].otherMax.value=parseInt(document.forms[0].otherMax.value)+1;
	}
	//32主行次____"其他"子行赋值
	function setQtValue(i,L1,L2,L3,L4){
		var qt_xm=document.all("qt_xm");
		qt_xm[parseInt(i)].value=L1
		
		var qt_sjfse=document.all("qt_sjfse");
		qt_sjfse[parseInt(i)].value=L2
		
		var qt_sfgdkce=document.all("qt_sfgdkce");
		qt_sfgdkce[parseInt(i)].value=L3
		
		var qt_tze=document.all("qt_tze");
		qt_tze[parseInt(i)].value=L4
		
	 }
	
	<%/*返回*/%>
		function doExit()
		{
			doSubmitForm('Exit');
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
	<%/*校验*/%>
	function doCheck()
	{
	   var list="<%=Constants.QYSDS_CONTROL_CHAR_FOR_JS%>";
		if (!Char_Vaildate(document.forms[0],list)){
			return false;	
		}
	   doSubmitForm('Check');
	  
	}
	
	<%/*清除*/%>
	function doReset()
	{
	    if(confirm("确认是否清除当前数据？"))
	    {
		   	for(var i=1; i<=32; i++)
		   	{
				obj = eval("document.forms[0].sjfse_" + i);
				     
					 obj.value = "";
				obj = eval("document.forms[0].sfgdkce_" + i);
				    
					 obj.value = "";
				obj = eval("document.forms[0].tze_" + i);
				     
					 obj.value = "";	
		    }
		    clean('qtzbj_xm','qtzbj_sjfse','qtzbj_sfgdkce','qtzbj_tze',document.forms[0].otherZbjMax.value);
			clean('qt_xm','qt_sjfse','qt_sfgdkce','qt_tze',document.forms[0].otherMax.value);		 
	    }
	}
	<%/*用于清除动态子行的内容*/%>
	function clean(L1,L2,L3,L4,maxRow)
	{
		var arr1=document.all(L1);
		var arr2=document.all(L2);	
		var arr3=document.all(L3);	
		var arr4=document.all(L4);	
		for (var i=0;i<parseInt(maxRow);i++)
		{					
			if(arr1[parseInt(i)].readOnly!=true)
				arr1[parseInt(i)].value= ""	;		
			if(arr2[parseInt(i)].readOnly!=true)
				arr2[parseInt(i)].value= ""	;			
			if(arr3[parseInt(i)].readOnly!=true)
				arr3[parseInt(i)].value= ""	;			
			if(arr4[parseInt(i)].readOnly!=true)
				arr4[parseInt(i)].value= ""	;			
		}	
	}
	
	
	<%/*删除*/%>
	function doDelete()
	{
		doSubmitForm('Delete');
	}
	
	<%/*增加行--其他准备金*/%>
	function InsertQTZBJRow()
	{
		var list=document.getElementById("bxzbj_list");

		//当前“其他准备金”最大子行数
		var hc=document.forms[0].otherZbjMax.value;

		var newRow=list.insertRow(27+parseInt(hc));
		
		//行次
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp";

		//项目名称
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left-qysds2';
		newCell.innerHTML="<input type='text' name='qtzbj_xm' value='' size='25' tabindex='2'>";
		
		//实际发生额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='qtzbj_sjfse'  value='' size='13' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//税法规定扣除额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='qtzbj_sfgdkce' value='' size='13' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//调整额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='qtzbj_tze' size='13' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//复选框
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='chk' value=''>";
		
		//当前“其他准备金”最大子行数加1
		document.forms[0].otherZbjMax.value=parseInt(document.forms[0].otherZbjMax.value)+1;
		
	}
	
	//删除其他准备金子行
	function RomoveQTZBJRow( )
	{
		//复选框
		var arrChk=document.all("chk");
		var list=document.getElementById("bxzbj_list");
		
		//当前“其他准备金”最大子行数
		var hc=document.forms[0].otherZbjMax.value;
		if(parseInt(document.forms[0].otherZbjMax.value)>2){
			for(var i=parseInt(parseInt(document.forms[0].otherZbjMax.value)-1);i>=2;i--){
				if(arrChk.length==undefined){
					if(arrChk.checked){
						if(txt_null("muti",i)){
							if(window.confirm("其他准备金子行第 "+parseInt(parseInt(i)+1) +" 行内容不为空，确认删除吗？")){
								list.deleteRow( parseInt(27+parseInt(i)) );
								document.forms[0].otherZbjMax.value=parseInt(document.forms[0].otherZbjMax.value)-1;
							}else{
								arrChk.checked=false;
							}
						}else{
							list.deleteRow( parseInt(27+parseInt(i)) );
							document.forms[0].otherZbjMax.value=parseInt(document.forms[0].otherZbjMax.value)-1;
						}
					}
				}else{
					if(arrChk[i-2].checked){
						if(txt_null("muti",i)){
							if(window.confirm("其他准备金子行第 "+parseInt(parseInt(i)+1) +" 行内容不为空，确认删除吗？")){
								list.deleteRow( parseInt(27+parseInt(i)) );
								document.forms[0].otherZbjMax.value=parseInt(document.forms[0].otherZbjMax.value)-1;
							}else{
								arrChk[i-2].checked=false;
							}
						}else{
							list.deleteRow( parseInt(27+parseInt(i)) );
							document.forms[0].otherZbjMax.value=parseInt(document.forms[0].otherZbjMax.value)-1;
						}
					}
				}
				
			}
		}	
		
	}
	

	//判断其他准备金子行内容是否为空
	//不为空，返回true
	//为空返回false
	function txt_null(s,i){
		if (s=="1"){   
			if (document.all("qtzbj_xm").value!="" && document.all("qtzbj_xm").value!=undefined)  {
				return true;
			} 
			if   (document.all("qtzbj_sjfse").value!="" && document.all("qtzbj_sjfse").value!=undefined)   {
				return true;
			}  
			if   (document.all("qtzbj_sfgdkce").value!="" && document.all("qtzbj_sfgdkce").value!=undefined)   {
				return true;
			}  
			if   (document.all("qtzbj_tze").value!="" && document.all("qtzbj_tze").value!=undefined)   {
				return true;
			}  
		}else{   
			if   (document.all("qtzbj_xm")[i].value!="")   {
				return true;
			}   
			if   (document.all("qtzbj_sjfse")[i].value!="")   {
				return true;
			}  
			if   (document.all("qtzbj_sfgdkce")[i].value!="")   {
				return true;
			}  
			if   (document.all("qtzbj_tze")[i].value!="")   {
				return true;
			}   
		}
		return false;
	}
	
	//判断其他准备金子行内容是否为空
	//不为空，返回true
	//为空返回false
	function txt_qt_null(s,i){
		if (s=="1"){   
			if (document.all("qt_xm").value!="" && document.all("qt_xm").value!=undefined)  {
				return true;
			} 
			if   (document.all("qt_sjfse").value!="" && document.all("qt_sjfse").value!=undefined)   {
				return true;
			}  
			if   (document.all("qt_sfgdkce").value!="" && document.all("qt_sfgdkce").value!=undefined)   {
				return true;
			}  
			if   (document.all("qt_tze").value!="" && document.all("qt_tze").value!=undefined)   {
				return true;
			}  
		}else{   
			if   (document.all("qt_xm")[i].value!="")   {
				return true;
			}   
			if   (document.all("qt_sjfse")[i].value!="")   {
				return true;
			}  
			if   (document.all("qt_sfgdkce")[i].value!="")   {
				return true;
			}  
			if   (document.all("qt_tze")[i].value!="")   {
				return true;
			}   
		}
		return false;
	}
	
	
	function InsertQTRow()
	{
		var list=document.getElementById("bxzbj_list");

		//当前“其他准备金”最大子行数
		var qtzbjhc=document.forms[0].otherZbjMax.value;

		//当前“其他准备金”最大子行数
		var qthc=document.forms[0].otherMax.value;
		
		var newRow=list.insertRow(33+parseInt(qtzbjhc)+parseInt(qthc));
		
		//行次
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp;";

		//项目名称
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left-qysds3';
		newCell.innerHTML="<input type='text' name='qt_xm' value='' size='25'  tabindex='2'>";
		
		//实际发生额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='qt_sjfse' value='' size='13' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//税法规定扣除额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='qt_sfgdkce' value='' size='13' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//调整额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='qt_tze' value='' size='13' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//复选框
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='qtchk' value=''>";
		
		//当前“其他准备金”最大子行数加1
		document.forms[0].otherMax.value=parseInt(document.forms[0].otherMax.value)+1;
	}
	
	
	function RomoveQTRow()
	{
		//复选框
		var arrChk=document.all("qtchk");
		var list=document.getElementById("bxzbj_list");
		var zbjhc=document.forms[0].otherZbjMax.value;
		if(parseInt(document.forms[0].otherMax.value)>4){
					for(var i=parseInt(parseInt(document.forms[0].otherMax.value)-1);i>=4;i--){

				if(arrChk.length==undefined){
					if(arrChk.checked){
						if(txt_qt_null("muti",i)){
							if(window.confirm("其他子行第 "+parseInt(parseInt(i)+1) +" 行内容不为空，确认删除吗？")){
							    list.deleteRow( parseInt(33+parseInt(zbjhc)+parseInt(i)) );
							    document.forms[0].otherMax.value=parseInt(document.forms[0].otherMax.value)-1;

									}else{
							arrChk[i].checked=false;
						}
					}else{
						list.deleteRow( parseInt(33+parseInt(zbjhc)+parseInt(i)) );
						document.forms[0].otherMax.value=parseInt(document.forms[0].otherMax.value)-1;
					}
				}
				
	
			 }else{
				if(arrChk[i-4].checked){
				if(txt_qt_null('muti',i)){
				if(window.confirm("其他准备金子行第 "+parseInt(parseInt(i)+1) +" 行内容不为空，确认删除吗？")){
								list.deleteRow( parseInt(33+parseInt(zbjhc)+parseInt(i)) );
								document.forms[0].otherMax.value=parseInt(document.forms[0].otherMax.value)-1;
							}else{
								arrChk[i-4].checked=false;
							}
	
						}else{
							list.deleteRow( parseInt(33+parseInt(zbjhc)+parseInt(i)) );
							document.forms[0].otherMax.value=parseInt(document.forms[0].otherMax.value)-1;
									}
					}
				}
				
			}
		}	
	}
	
	
</script>

<br>

<html:form action="/webapp/smsb/qysds/bxzbjAction">
	<html:hidden property="actionType" />
	<%/*其他准备金子行最大行号*/%>
	<input type='hidden' name='otherZbjMax' value='2' />
	<%/*其他子行最大行号*/%>
	<input type='hidden' name='otherMax' value='4' />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />
		
	<table width="96%" align="center" cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">保险准备金提转差纳税调整表</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;申报日期:<bean:write
				name="bxzbjForm" property="sbrq" scope="request" filter="true" />
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
				name="bxzbjForm" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;纳税人名称：<bean:write
				name="bxzbjForm" property="nsrmc" scope="request" filter="true" />&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">
			<TABLE class="table-99" align="center">
				<TR>
					<TD>
					<div id="Layer2" style="position:static; overflow: auto; ">
					<table id="bxzbj_list" border="1" cellspacing="0" class="table-99"
						align="center">
						<tr>
							<td class="2-td1-left">行&nbsp;&nbsp;次</td>
							<td class="2-td1-left">项&nbsp;&nbsp;&nbsp;&nbsp;目</td>
							<td class="2-td1-left">实际发生额</td>
							<td class="2-td1-left">税法规定扣除额</td>
							<td class="2-td1-center">调整额</td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="1" />
							<td class="2-td2-left">1</td>
							<td class="2-td2-left-qysds1">一、未到期责任准备金</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_1' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_1' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze' id='tze_1'
								value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="2" />
							<td class="2-td2-left">2</td>
							<td class="2-td2-left-qysds2">未到期责任准备金提取数</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_2' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_2' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze' id='tze_2'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="3" />
							<td class="2-td2-left">3</td>
							<td class="2-td2-left-qysds2">未到期责任准备金转回数</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_3' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_3' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze' id='tze_3'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="4" />
							<td class="2-td2-left">4</td>
							<td class="2-td2-left-qysds2">未到期责任准备金提转差（2－3）</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_4' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_4' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze' id='tze_4'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="5" />
							<td class="2-td2-left">5</td>
							<td class="2-td2-left-qysds1">二、长期责任准备金</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_5' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_5' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze' id='tze_5'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="6" />
							<td class="2-td2-left">6</td>
							<td class="2-td2-left-qysds2">长期责任准备金提取数</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_6' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_6' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze' id='tze_6'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="7" />
							<td class="2-td2-left">7</td>
							<td class="2-td2-left-qysds2">长期责任准备金转回数</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_7' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_7' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze' id='tze_7'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="8" />
							<td class="2-td2-left">8</td>
							<td class="2-td2-left-qysds2">长期责任准备金提转差（6－7）</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_8' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_8' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze' id='tze_8'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="9" />
							<td class="2-td2-left">9</td>
							<td class="2-td2-left-qysds1">三、未决赔款准备金</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_9' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_9' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze' id='tze_9'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="10" />
							<td class="2-td2-left">10</td>
							<td class="2-td2-left-qysds2">未决赔款准备金提取数</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_10' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_10' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze'
								id='tze_10' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="11" />
							<td class="2-td2-left">11</td>
							<td class="2-td2-left-qysds2">未决赔款准备金转回数</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_11' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_11' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze'
								id='tze_11' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="12" />
							<td class="2-td2-left">12</td>
							<td class="2-td2-left-qysds2">未决赔款准备金提转差（10-11）</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_12' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_12' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze'
								id='tze_12' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="13" />
							<td class="2-td2-left">13</td>
							<td class="2-td2-left-qysds1">四、寿险责任准备金</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_13' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_13' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze'
								id='tze_13' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="14" />
							<td class="2-td2-left">14</td>
							<td class="2-td2-left-qysds2">寿险责任准备金提取数</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_14' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_14' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze'
								id='tze_14' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="15" />
							<td class="2-td2-left">15</td>
							<td class="2-td2-left-qysds2">寿险责任准备金转回数</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_15' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_15' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze'
								id='tze_15' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="16" />
							<td class="2-td2-left">16</td>
							<td class="2-td2-left-qysds2">寿险责任准备金提转差（14－15）</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_16' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_16' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze'
								id='tze_16' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="17" />
							<td class="2-td2-left">17</td>
							<td class="2-td2-left-qysds1">五、长期健康险责任准备金</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_17' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_17' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze'
								id='tze_17' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="18" />
							<td class="2-td2-left">18</td>
							<td class="2-td2-left-qysds2">长期健康险责任准备金提取数</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_18' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_18' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze'
								id='tze_18' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="19" />
							<td class="2-td2-left">19</td>
							<td class="2-td2-left-qysds2">长期健康险责任准备金转回数</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_19' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_19' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze'
								id='tze_19' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="20" />
							<td class="2-td2-left">20</td>
							<td class="2-td2-left-qysds2">长期健康险责任准备金提转差（18－19）</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_20' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_20' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze'
								id='tze_20' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="21" />
							<td class="2-td2-left">21</td>
							<td class="2-td2-left-qysds1">六、保险保障基金</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_21' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_21' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze'
								id='tze_21' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="22" />
							<td class="2-td2-left">22</td>
							<td class="2-td2-left-qysds2">财产保险、意外伤害保险和短期健康保险业务</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_22' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_22' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze'
								id='tze_22' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="23" />
							<td class="2-td2-left">23</td>
							<td class="2-td2-left-qysds2">有保证利率的长期人寿保险和长期健康保险</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_23' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_23' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze'
								id='tze_23' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="24" />
							<td class="2-td2-left">24</td>
							<td class="2-td2-left-qysds2">无保证利率的长期人寿保险和长期健康保险</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_24' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_24' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze'
								id='tze_24' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="25" />
							<td class="2-td2-left">25</td>
							<td class="2-td2-left-qysds2">其他</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_25' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_25' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze'
								id='tze_25' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="26" />
							<td class="2-td2-left">26</td>
							<td class="2-td2-left-qysds1">七、其他责任准备金</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_26' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_26' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='tze' id='tze_26'
								value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type="button" value="增加" tabIndex="-1"
								onclick="InsertQTZBJRow()"></td>

							<td class="2-td2-center"><input type="button" value='删除' tabIndex="-1"
								onClick="RomoveQTZBJRow()"></td>
						</tr>
						<tr>
							<td class="2-td2-left">27</td>
							<td class="2-td2-left-qysds2"><input type='text' name='qtzbj_xm'
								id='qtzbj_xm_1' value='' size='25' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='qtzbj_sjfse'
								id='qtzbj_sjfse_1' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='qtzbj_sfgdkce'
								id='qtzbj_sfgdkce_1' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='qtzbj_tze'
								id='qtzbj_tze_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">28</td>
							<td class="2-td2-left-qysds2"><input type='text' name='qtzbj_xm'
								id='qtzbj_xm_2' value='' size='25' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='qtzbj_sjfse'
								id='qtzbj_sjfse_2' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='qtzbj_sfgdkce'
								id='qtzbj_sfgdkce_2' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='qtzbj_tze'
								id='qtzbj_tze_2' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="27" />
							<td class="2-td2-left">29</td>
							<td class="2-td2-left-qysds1">总计（4+8+12+16+20+21+26）</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_27' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_27' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze'
								id='tze_27' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="28" />
							<td class="2-td2-left">30</td>
							<td class="2-td2-left-qysds1">八、补充资料</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_28' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_28' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze'
								id='tze_28' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="29" />
							<td class="2-td2-left">31</td>
							<td class="2-td2-left-qysds2">公司总资产</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_29' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_29' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze'
								id='tze_29' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="30" />
							<td class="2-td2-left">32</td>
							<td class="2-td2-left-qysds2">自留保费收入</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_30' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_30' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze'
								id='tze_30' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="31" />
							<td class="2-td2-left">33</td>
							<td class="2-td2-left-qysds2">赔款支出</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_31' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_31' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze'
								id='tze_31' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<input type="hidden" name="hc" value="32">
							<td class="2-td2-left">34</td>
							<td class="2-td2-left-qysds2">其他</td>
							<td class="2-td2-left"><input type='text' name='sjfse'
								id='sjfse_32' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='sfgdkce'
								id='sfgdkce_32' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tze'
								id='tze_32' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type="button" value="增加" tabIndex="-1"
								onclick="InsertQTRow()"></td>

							<td class="2-td2-center"><input type="button" value='删除' tabIndex="-1"
								onClick="RomoveQTRow()"></td>
						</tr>
						<tr>
							<td class="2-td2-left">35</td>
							<td class="2-td2-left-qysds3"><input type='text' name='qt_xm' value=''
								id='qt_xm_1' size='25' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='qt_sjfse'
								id='qt_sjfse_1' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='qt_sfgdkce'
								id='qt_sfgdkce_1' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='qt_tze'
								id='qt_tze_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">36</td>
							<td class="2-td2-left-qysds3"><input type='text' name='qt_xm' value=''
								id='qt_xm_2' size='25' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='qt_sjfse'
								id='qt_sjfse_2' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='qt_sfgdkce'
								id='qt_sfgdkce_2' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='qt_tze'
								id='qt_tze_2' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">37</td>
							<td class="2-td2-left-qysds3"><input type='text' name='qt_xm' value=''
								id='qt_xm_3' size='25' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='qt_sjfse'
								id='qt_sjfse_3' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='qt_sfgdkce'
								id='qt_sfgdkce_3' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='qt_tze'
								id='qt_tze_3' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">38</td>
							<td class="2-td2-left-qysds3"><input type='text' name='qt_xm' value=''
								id='qt_xm_4' size='25' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='qt_sjfse'
								id='qt_sjfse_4' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='qt_sfgdkce'
								id='qt_sfgdkce_4' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='qt_tze'
								id='qt_tze_4' value='' size='13' tabindex='2'></td>
						</tr>
					</table>
					</div>
					</TD>
				</TR>
				<TR class="black9">
					<TD>
					<div align="center">
					<a style="cursor:hand" id="previous"
						onclick="gotoPreviousTable()"><img name="spage" value="上一张表"
						alt="填写上一张表"
						onMouseOver="MM_swapImage('previoustable','','<%=static_contextpath%>images/shangyiye2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/shangyiye1.jpg" id="previoustable">
					</a>&nbsp;&nbsp;
					<logic:equal name="bxzbjForm" property="isLastTable" value="no">
						 <a style="cursor:hand" id="next"
							onclick="gotoNextTable()"><img name="xpage" value="下一张表"
							alt="填写下一张表"
							onMouseOver="MM_swapImage('nexttable','','<%=static_contextpath%>images/xaiyiye2.jpg',1)"
							onMouseOut="MM_swapImgRestore()"
							src="<%=static_contextpath%>images/xaiyiye1.jpg" id="nexttable"> 
						</a>&nbsp;&nbsp;
					</logic:equal>
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
						onClick="doCheck();return false;" accesskey="d"
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
	<%@ include file="../include/footernew.jsp"%>
</html:form>
</body>
</html>
