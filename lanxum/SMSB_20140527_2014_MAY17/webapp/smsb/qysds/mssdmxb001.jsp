<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.mssdmxb.web.MssdmxbForm"%>
<%@ page import="java.util.HashMap"%>

<html>
<head>
<title>免税所得及减免税明细表</title>
<link href="../../css/text.css" rel="stylesheet" type="text/css">
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
	src="../../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/qysdsnew.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/function.js"></script>
</head>
<script language="JavaScript">
	
	<%/*初始化*/%>
	function doInit()
	{
	<%/*规正页面输入*/%>
	initNumText("je",64);
	<%
	MssdmxbForm mssdmxbForm=(MssdmxbForm)request.getAttribute("mssdmxbForm");
	if (mssdmxbForm!=null && mssdmxbForm.getDataList().size()>0){
		for(int i=0;i<mssdmxbForm.getDataList().size();i++){
			HashMap map=(HashMap)mssdmxbForm.getDataList().get(i);
			int hc=Integer.parseInt((String)map.get("hc"));
			String je=(String)map.get("je");
			%>
			obj = eval("document.getElementById('<%=hc%>_1')");
			obj.value = '<%=je%>';	
			<% 
		}
	}
	String jmlx=mssdmxbForm.getJmlx();
	%>
	pb('<%=jmlx%>');
	InitTabindex(document.forms[0]);
	}
		
	<%/*屏蔽文本框*/%>
	function pb(jmlx){
			
			<%/*没有优惠*/%>
			if(jmlx=='<%=CodeConstant.JMLXNO%>'||jmlx=='null'){
				for(var j=10;j<62;j++){
					obj = eval("document.getElementById('"+j+"_1')");
					if(obj.readOnly!=true){
						obj.readOnly=true;
						obj.className='text-gray';			
					}
				}
	 		}
	 		
			<%/*软件产业及集成电路*/%>
			if(jmlx=='<%=CodeConstant.JMLX9020%>'||jmlx=='<%=CodeConstant.JMLX9030%>'){
				for(var j=10;j<62;j++){
					obj = eval("document.getElementById('"+j+"_1')");
					if(obj.readOnly!=true){
						obj.readOnly=true;
						obj.className='text-gray';			
					}
				}
			
				obj = eval("document.getElementById('10_1')");
				obj.readOnly=false;
				obj.className="";
				
				obj = eval("document.getElementById('11_1')");
				obj.readOnly=false;
				obj.className="";
				
				obj = eval("document.getElementById('13_1')");
				obj.readOnly=false;
				obj.className="";
	 		}
	 		
	 		<%/*福利企业*/%>
			if(jmlx=='<%=CodeConstant.JMLX9090%>'){			
				for(var j=10;j<62;j++){
					obj = eval("document.getElementById('"+j+"_1')");
					if(obj.readOnly!=true){
						obj.readOnly=true;	
						obj.className='text-gray';				
					}
				}
			
				obj = eval("document.getElementById('10_1')");
				obj.readOnly=false;
				obj.className="";
				
				obj = eval("document.getElementById('42_1')");
				obj.readOnly=false;
				obj.className="";
				
				obj = eval("document.getElementById('43_1')");
				obj.readOnly=false;
				obj.className="";
	 		}
	 		
	 		<%/*劳服企业*/%>	 		
			if(jmlx=='<%=CodeConstant.JMLX9070%>'){
				for(var j=10;j<62;j++){
					obj = eval("document.getElementById('"+j+"_1')");
					if(obj.readOnly!=true){								
						obj.readOnly=true;
						obj.className='text-gray';			
					}		
				}
		 		
				obj = eval("document.getElementById('10_1')");
				obj.readOnly=false;
				obj.className="";
				
				obj = eval("document.getElementById('42_1')");
				obj.readOnly=false;
				obj.className="";
				
				obj = eval("document.getElementById('44_1')");
				obj.readOnly=false;
				obj.className="";
	 		}
	 		
			<%/*高新技术企业*/%>
			if(jmlx=='<%=CodeConstant.JMLX9010%>'){
				for(var j=10;j<62;j++){
					obj = eval("document.getElementById('"+j+"_1')");
					if(obj.readOnly!=true){
						obj.readOnly=true;
						obj.className='text-gray';	
					}
				}
			
				obj = eval("document.getElementById('10_1')");
				obj.readOnly=false;
				obj.className="";
				
				obj = eval("document.getElementById('11_1')");
				obj.readOnly=false;
				obj.className="";
				
				obj = eval("document.getElementById('12_1')");
				obj.readOnly=false;
				obj.className="";
	 		}
	 		
	 		<%/*无减免*/%>
			if(jmlx=='<%=CodeConstant.JMLXNO%>'){
				for(var j=10;j<62;j++){
				obj = eval("document.getElementById('"+j+"_1')");
					if(obj.readOnly!=true){
						obj.readOnly=true;
						obj.className='text-gray';
					}
				}
			}
			
			<%/*其他减免类型*/%>
			if(jmlx=='<%=CodeConstant.JMLXOTHER%>'){
				obj = eval("document.getElementById('12_1')");
				obj.readOnly=true;
				obj.className='text-gray';
				obj = eval("document.getElementById('13_1')");
				obj.readOnly=true;
				obj.className='text-gray';
				obj = eval("document.getElementById('43_1')");
				obj.readOnly=true;
				obj.className='text-gray';
				obj = eval("document.getElementById('44_1')");
				obj.readOnly=true;
				obj.className='text-gray';
	 		}
	 }
	 	
	<%/*保存*/%>
	function doSave()
	{
		doSubmitForm('Save');
	}
	
	function doCheck()
	{
		doSubmitForm('Check');
	}
	
	<%/*清除*/%>
	function doReset()
	{
	    if(confirm("确认是否清除当前数据？"))
	    {
		   	for(var i=1; i < 65; i++){
				obj1 = eval("document.getElementById('" + i + "_1')");
				if(obj1.readOnly!=true)
					obj1.value = "";
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
</script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" style="margin: 0" onLoad="doInit()">
<%@ include file="../include/header.jsp"%>

<html:form action="/webapp/smsb/qysds/mssdmxbAction.do">
	<html:hidden property="actionType" />
	<html:hidden property="jmlx" />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />
		
	<table width="96%" align="center" cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">免税所得及减免税明细表</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;申报日期:<bean:write
				name="mssdmxbForm" property="sbrq" scope="request" filter="true" />
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
				name="mssdmxbForm" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;纳税人名称：<bean:write
				name="mssdmxbForm" property="nsrmc" scope="request" filter="true" />&nbsp;
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
					<div id="Layer2" style="position:static; ">
					<table id="wrklistTable" border="1" cellspacing="0"
						class="table-99" align="center">
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
							<td class="2-td2-left-qysds1" nowrap>一、免税所得（2+3+…9）</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_1' value='1'> <input type='text' name='je' id='1_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>34</td>
							<td class="2-td2-left-qysds2" nowrap>(六)文教卫生减免（35+36+…41）</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_34' value='34'> <input type='text' name='je' id='34_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>2</td>
							<td class="2-td2-left-qysds2" nowrap>国债利息所得</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_2' value='2'> <input type='text' name='je' id='2_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>35</td>
							<td class="2-td2-left-qysds2" nowrap>其中：1、青少年活动中心</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_35' value='35'> <input type='text' name='je' id='35_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>3</td>
							<td class="2-td2-left-qysds2" nowrap>免税的补贴收入</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_3' value='3'> <input type='text' name='je' id='3_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>36</td>
							<td class="2-td2-left-qysds3" nowrap>2、非盈利医疗机构</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_36' value='36'> <input type='text' name='je' id='36_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>4</td>
							<td class="2-td2-left-qysds2" nowrap>
							免税的纳入预算管理的基金、收费或附加</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_4' value='4'> <input type='text' name='je' id='4_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>37</td>
							<td class="2-td2-left-qysds3" nowrap>3、中央电视台</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_37' value='37'> <input type='text' name='je' id='37_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>5</td>
							<td class="2-td2-left-qysds2" nowrap>免于补税的投资收益</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_5' value='5'> <input type='text' name='je' id='5_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>38</td>
							<td class="2-td2-left-qysds3" nowrap>4、学校培训班收入</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_38' value='38'> <input type='text' name='je' id='38_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>6</td>
							<td class="2-td2-left-qysds2" nowrap>免税的技术转让收益</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_6' value='6'> <input type='text' name='je' id='6_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>39</td>
							<td class="2-td2-left-qysds3" nowrap>5、高校后勤减免</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_39' value='39'> <input type='text' name='je' id='39_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>7</td>
							<td class="2-td2-left-qysds2" nowrap>免税的治理"三费"收益</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_7' value='7'> <input type='text' name='je' id='7_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>40</td>
							<td class="2-td2-left-qysds3" nowrap>6、29届奥运会免税
							</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_40' value='40'> <input type='text' name='je' id='40_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>8</td>
							<td class="2-td2-left-qysds2" nowrap>
							种植业、养殖业及农林产品初加工所得</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_8' value='8'> <input type='text' name='je' id='8_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>41</td>
							<td class="2-td2-left-qysds3" nowrap>7、其他</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_41' value='41'> <input type='text' name='je' id='41_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>9</td>
							<td class="2-td2-left-qysds2" nowrap>其他免税所得</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_9' value='9'> <input type='text' name='je' id='9_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>42</td>
							<td class="2-td2-left-qysds2" nowrap>(七)促进就业减免（43+44+…50）</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_42' value='42'> <input type='text' name='je' id='42_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>10</td>
							<td class="2-td2-left-qysds1" nowrap>
							二、减免税（11+17+18+26+31+34+42+51+52+53+54+60+61）</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_10' value='10'> <input type='text' name='je' id='10_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>43</td>
							<td class="2-td2-left-qysds2" nowrap>其中：1、民政福利企业</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_43' value='43'> <input type='text' name='je' id='43_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>11</td>
							<td class="2-td2-left-qysds2" nowrap>(一)高新技术企业及技术进步（12+13+…16）</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_11' value='11'> <input type='text' name='je' id='11_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>44</td>
							<td class="2-td2-left-qysds3" nowrap>2、劳服企业</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_44' value='44'> <input type='text' name='je' id='44_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>12</td>
							<td class="2-td2-left-qysds2" nowrap>其中：1、高新技术开发区</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_12' value='12'> <input type='text' name='je' id='12_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>45</td>
							<td class="2-td2-left-qysds3" nowrap>
							3、下岗失业人员再就业</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_45' value='45'> <input type='text' name='je' id='45_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>13</td>
							<td class="2-td2-left-qysds3" nowrap>2、软件、集成电路</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_13' value='13'> <input type='text' name='je' id='13_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>46</td>
							<td class="2-td2-left-qysds3" nowrap>4、随军家属企业</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_46' value='46'> <input type='text' name='je' id='46_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>14</td>
							<td class="2-td2-left-qysds3" nowrap>3、非盈利科研机构</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_14' value='14'> <input type='text' name='je' id='14_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>47</td>
							<td class="2-td2-left-qysds3" nowrap>5、军转干部企业</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_47' value='47'> <input type='text' name='je' id='47_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>15</td>
							<td class="2-td2-left-qysds3" nowrap>
							4、科研机构转制为企业5年免税</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_15' value='15'> <input type='text' name='je' id='15_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>48</td>
							<td class="2-td2-left-qysds3" nowrap>
							6、国有企业主辅分离企业</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_48' value='48'> <input type='text' name='je' id='48_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>16</td>
							<td class="2-td2-left-qysds3" nowrap>5、其他</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_16' value='16'> <input type='text' name='je' id='16_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>49</td>
							<td class="2-td2-left-qysds3" nowrap>7、老年服务机构</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_49' value='49'> <input type='text' name='je' id='49_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>17</td>
							<td class="2-td2-left-qysds2" nowrap>(二)基础设施建设减免</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_17' value='17'> <input type='text' name='je' id='17_1'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" nowrap>50</td>
							<td class="2-td2-left-qysds3" nowrap>8 、其他减免</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_50' value='50'> <input type='text' name='je' id='50_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>18</td>
							<td class="2-td2-left-qysds2" nowrap>(三)各类区域优惠减免（19+20+…25）</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_18' value='18'> <input type='text' name='je' id='18_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>51</td>
							<td class="2-td2-left-qysds2" nowrap>(八)资源综合利用减免</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_51' value='51'> <input type='text' name='je' id='51_1'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>19</td>
							<td class="2-td2-left-qysds2" nowrap>其中：1、西部大开发减免</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_19' value='19'> <input type='text' name='je' id='19_1'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" nowrap>52</td>
							<td class="2-td2-left-qysds2" nowrap>(九)劳改劳教减免</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_52' value='52'> <input type='text' name='je' id='52_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>20</td>
							<td class="2-td2-left-qysds3" nowrap>2、西气东输</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_20' value='20'> <input type='text' name='je' id='20_1'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" nowrap>53</td>
							<td class="2-td2-left-qysds2" nowrap>(十)军队企业减免</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_53' value='53'> <input type='text' name='je' id='53_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>21</td>
							<td class="2-td2-left-qysds3" nowrap>
							3、东北老工业基地减免</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_21' value='21'> <input type='text' name='je' id='21_1'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" nowrap>54</td>
							<td class="2-td2-left-qysds2" nowrap>(十一)金融类减免（55+56+…59）</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_54' value='54'> <input type='text' name='je' id='54_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>22</td>
							<td class="2-td2-left-qysds3" nowrap>4、民族自治区域减免
							</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_22' value='22'> <input type='text' name='je' id='22_1'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" nowrap>55</td>
							<td class="2-td2-left-qysds2" nowrap>其中：1、农信社</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_55' value='55'> <input type='text' name='je' id='55_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>23</td>
							<td class="2-td2-left-qysds3" nowrap>5、老少边穷减免</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_23' value='23'> <input type='text' name='je' id='23_1'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" nowrap>56</td>
							<td class="2-td2-left-qysds3" nowrap>2、国有独资银行</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_56' value='56'> <input type='text' name='je' id='56_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>24</td>
							<td class="2-td2-left-qysds3" nowrap>
							6、天津滨海新区的高新技术企业</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_24' value='24'> <input type='text' name='je' id='24_1'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" nowrap>57</td>
							<td class="2-td2-left-qysds3" nowrap>3、开放式基金</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_57' value='57'> <input type='text' name='je' id='57_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>25</td>
							<td class="2-td2-left-qysds3" nowrap>7、其他</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_25' value='25'> <input type='text' name='je' id='25_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>58</td>
							<td class="2-td2-left-qysds3" nowrap>4、封闭式基金</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_58' value='58'> <input type='text' name='je' id='58_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>26</td>
							<td class="2-td2-left-qysds2" nowrap>(四)农业减免（27+28+29+30）</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_26' value='26'> <input type='text' name='je' id='26_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>59</td>
							<td class="2-td2-left-qysds3" nowrap>5、其他</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_59' value='59'> <input type='text' name='je' id='59_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>27</td>
							<td class="2-td2-left-qysds3" nowrap>
							1、农业产业化龙头企业免税</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_27' value='27'> <input type='text' name='je' id='27_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>60</td>
							<td class="2-td2-left-qysds2" nowrap>(十二)自然灾害减免</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_60' value='60'> <input type='text' name='je' id='60_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>28</td>
							<td class="2-td2-left-qysds3" nowrap>
							2、农业产前、产中、产后服务业</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_28' value='28'> <input type='text' name='je' id='28_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>61</td>
							<td class="2-td2-left-qysds2" nowrap>(十三)其他减免</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_61' value='61'> <input type='text' name='je' id='61_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>29</td>
							<td class="2-td2-left-qysds3" nowrap>3、远洋捕捞业免税</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_29' value='29'> <input type='text' name='je' id='29_1'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" nowrap>62</td>
							<td class="2-td2-left-qysds1" nowrap>三、抵免所得税额（63+64）</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_62' value='62'> <input type='text' name='je' id='62_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>30</td>
							<td class="2-td2-left-qysds3" nowrap>4、其他</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_30' value='30'> <input type='text' name='je' id='30_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>63</td>
							<td class="2-td2-left-qysds2" nowrap>购买国产设备投资抵免所得税</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_63' value='63'> <input type='text' name='je' id='63_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>31</td>
							<td class="2-td2-left-qysds2" nowrap>(五)第三产业减免（32+33）</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_31' value='31'> <input type='text' name='je' id='31_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>64</td>
							<td class="2-td2-left-qysds2" nowrap>其他</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_64' value='64'> <input type='text' name='je' id='64_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>32</td>
							<td class="2-td2-left-qysds2" nowrap>其中：1、新办的服务型企业</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_32' value='32'> <input type='text' name='je' id='32_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>65</td>
							<td class="2-td2-left-qysds1" nowrap>四、经济特区、上海浦东新区低税率</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_65' value='65'> <input type='text' name='je' id='65_1'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'tabindex='2'></td>
							
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>33</td>
							<td class="2-td2-left-qysds3" nowrap>2、其他减免</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_33' value='33'> <input type='text' name='je' id='33_1'
								value='' size='13' tabindex='1'></td>
						</tr>
					</table>
					</div>
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
						onMouseOver="MM_swapImage('jiaoyan','','../../images/b-bdjyd2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" src="../../images/b-bdjyd1.jpg"
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



	<%@ include file="../include/footernew.jsp"%>
</html:form>


</body>
</html>


