<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@include file="../include/QRCodeHeader.jsp"%>
<%@page import="com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl.ClfxxcjForm"  %>
<HTML>
	<HEAD>
		<TITLE>存量房信息采集</TITLE>
	    <META content="text/html; charset=gb2312" http-equiv=Content-Type>
	    <SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
	    <SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
	    <script language=JavaScript src="../js/clfgl_util.js" type="text/javascript"></script>
	    <script language="javascript" src="../js/qscommon.js"></script>
	    <LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>   
	    

	</HEAD>
	<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0"  onLoad="initPage();" >
	    <jsp:include page="/include/Header.jsp" flush="true">
			<jsp:param name="subtitle" value="存量房管理>存量房信息采集"/>
	        <jsp:param name="helpURL" value=""/>
	    </jsp:include>

	    
	<TABLE align=center border=0 cellPadding=0 cellSpacing=0  width=100%>
		<tr>
			<td vAlign=top>
				<TABLE align=center border=0 cellSpacing=0 height="100%" width=100%>
				<tr>
				<td vAlign=top>
				<TABLE align=center cellSpacing=0 class=table-99>
				    <tr>
						<td class=1-td1>
							<div style="lineBreak:strict" align="left">
								采集方式选择：二维码扫描<html:radio name="clfxxcjForm" property="cjfsdm" value="01"></html:radio> 手工采集<html:radio name="clfxxcjForm" property="cjfsdm" value="02" onclick="changeCjfs(this);"></html:radio>
							</div>
							
							存量房买卖合同信息表
						</td>
					</tr>
				    <tr><td class=1-td2><br>
					<html:form action="/clfgl/clfxxcj.do" type="POST">
					<html:hidden name="clfxxcjForm" property="operationType"/>
					<html:hidden name="clfxxcjForm" property="xxly"/>
					<html:hidden name="clfxxcjForm" property="saveIsSuccess" value="0"/>
					<html:hidden name="clfxxcjForm" property="keyStr"/>
					<html:hidden name="clfxxcjForm" property="sbbh"/>
					<html:hidden name="clfxxcjForm" property="bbbs"/>
					<html:hidden name="clfxxcjForm" property="piccode"/>
					<html:hidden name="clfxxcjForm" property="UNEpiccode"/>
					<html:hidden name="clfxxcjForm" property="htbh"/>
					<html:hidden name="clfxxcjForm" property="htwsqyrq"/>
					<html:hidden name="clfxxcjForm" property="fwzlqx"/>
					<html:hidden name="clfxxcjForm" property="fwzldz"/>
					<html:hidden name="clfxxcjForm" property="fwqszylx"/>
					<html:hidden name="clfxxcjForm" property="fwqszylxmc"/>
					<html:hidden name="clfxxcjForm" property="sfwscsssggf"/>
					<html:hidden name="clfxxcjForm" property="sfwscsssggfmc"/>
					<html:hidden name="clfxxcjForm" property="fwcqzh"/>
					<html:hidden name="clfxxcjForm" property="fwsyqztfrq"/>
					<html:hidden name="clfxxcjForm" property="fwjzmj"/>
					<html:hidden name="clfxxcjForm" property="jzjgdm"/>
					<html:hidden name="clfxxcjForm" property="jzjgmc"/>
					<html:hidden name="clfxxcjForm" property="ghyt"/>
					<html:hidden name="clfxxcjForm" property="htzj"/>
					<html:hidden name="clfxxcjForm" property="bzdm"/>
					<html:hidden name="clfxxcjForm" property="bzmc"/>
					<html:hidden name="clfxxcjForm" property="hl"/>
					<html:hidden name="clfxxcjForm" property="wbje"/>
					<html:hidden name="clfxxcjForm" property="szlc"/>
					<html:hidden name="clfxxcjForm" property="all_sellerInfo"/>
					<html:hidden name="clfxxcjForm" property="all_buyerInfo"/>
					<html:hidden name="clfxxcjForm" property="fdczjjgmc"/>
					<html:hidden name="clfxxcjForm" property="fwxzdm"/>
					
							
						<table  border=0 cellSpacing=0 class=tabled-99 width="100%">
									
									<tr>
										<td colspan="7" align="center" class=2-td1-center>一、交易房屋信息</td>
									</tr>
									<tr>
										<td class=2-td2-left align="left" width="20%">合同编号</td>
										<td class=2-td2-left width="30%"> <div align="left"><input type="text" id="htbhShow"></td></div>
										<td class=2-td2-left align="left" width="20%">合同网上签约日期</td>
										<td class=2-td2-center colspan="4" width="30%"><div id="htwsqyrqShow" >&nbsp;</div></td>
									</tr>
									<tr>
										<td class=2-td2-left align="left">交易房屋地址</td>
										<td colspan="6" class=2-td2-center><div id="fwzldzShow" >&nbsp;</div></td>
									</tr>
									<tr>
										<td class=2-td2-left align="left">房屋权属转移类型</td>
										<td id="fwqszylxmcShow" class=2-td2-left>&nbsp;</td>
										<td class=2-td2-left align="left">本次交易是否为首次上市已购公房</td>
										<td id="sfwscsssggfmcShow" class=2-td2-center colspan="4">&nbsp;</td>
									</tr>
									<tr>
										<td class=2-td2-left align="left">房屋所有权证号</td>
										<td id="fwcqzhShow" class=2-td2-left>&nbsp;</td>
										<td class=2-td2-left align="left">房屋所有权证填发日期</td>
										<td id="fwsyqztfrqShow" class=2-td2-center colspan="4">&nbsp;</td>
									</tr>
									<tr>
										<td class=2-td2-left align="left">房屋建筑面积</td>
										<td id="fwjzmjShow" class=2-td2-left>&nbsp;</td>
										<td class=2-td2-left align="left">建筑结构</td>
										<td id="jzjgmcShow" class=2-td2-center colspan="4">&nbsp;</td>									
									</tr>	
									<tr>
										<td class=2-td2-left align="left">房屋划分用途</td>
										<td id="ghytShow" class=2-td2-left>&nbsp;</td>
										<td rowspan="2" class=2-td2-left align="left">成交总价</td>
										<td class=2-td2-left align="left" width="5%">人民币</td>	
										<td class=2-td2-center id="rmbjeShow" colspan="3">&nbsp;</td>							
									</tr>
									<tr>
										<td class=2-td2-left align="left">所在楼层/总层数</td>
										<td id="szlcShow" class=2-td2-left>&nbsp;</td>
										<td class=2-td2-left align="left">外币</td>
										<td class=2-td2-left id="wbjeShow" width="18%">&nbsp;</td>
										<td class=2-td2-left align="left" width="5%">汇率</td>	
										<td class=2-td2-center id="hlShow">&nbsp;</td>								
									</tr>																							
								</table>
								<br>
								<table  border=0 cellSpacing=0 class=tabled-99 width="100%" id="sellTab">
									<tr>
										<td colspan="6" class=2-td1-center>二、卖方信息</td>
									</tr>
<!-- 									<tr>
										<td class=2-td2-left  width="20%">名称（姓名）</td>
										<td id="sell_mcShow" class=2-td2-left width="30%">&nbsp;</td>
										<td class=2-td2-left width="20%">类别</td>
										<td id="sell_lbShow" class=2-td2-center width="30%">&nbsp;</td>									
									</tr>
									<tr>
										<td class=2-td2-left>证件类型</td>
										<td id="sell_zjlxShow" class=2-td2-left>&nbsp;</td>
										<td class=2-td2-left>证件号码</td>
										<td id="sell_zjhmShow" class=2-td2-center>&nbsp;</td>									
									</tr>
									<tr>
										<td class=2-td2-left>权利人份额</td>
										<td id="sell_qlrfeShow" class=2-td2-left>&nbsp;</td>
										<td class=2-td2-left>联系人电话</td>
										<td id="sell_lxdhShow" class=2-td2-center>&nbsp;</td>									
									</tr> -->																
								</table>
								<br>
								<table  border=0 cellSpacing=0 class=tabled-99 width="100%" id="buyTab">
									<tr>
										<td colspan="6" class=2-td1-center>三、买方信息</td>
									</tr>								
<!-- 								    <tr>
										<td class=2-td2-left width="20%" >名称（姓名）</td>
										<td id="buy_mcShow" class=2-td2-left width="30%">&nbsp;</td>
										<td class=2-td2-left width="20%">类别</td>
										<td id="buy_lbShow" class=2-td2-center width="30%">&nbsp;</td>									
									</tr>
									<tr>
										<td class=2-td2-left>证件类型</td>
										<td id="buy_zjlxShow" class=2-td2-left>&nbsp;</td>
										<td class=2-td2-left>证件号码</td>
										<td id="buy_zjhmShow" class=2-td2-center>&nbsp;</td>									
									</tr>
									<tr>
										<td class=2-td2-left>权利人份额</td>
										<td id="buy_qlrfeShow" class=2-td2-left>&nbsp;</td>
										<td class=2-td2-left>联系人电话</td>
										<td id="buy_lxdhShow" class=2-td2-center>&nbsp;</td>									
									</tr> -->																
								</table>
								<br>
								<table  border=0 cellSpacing=0 class=tabled-99 width="100%">
								   	<tr>
										<td colspan="4" class=2-td1-center>四、房地产中介机构信息</td>
									</tr>
									<tr>
										<td id="fdczjjgmcShow" class=2-td2-center>&nbsp;</td>
									</tr>
								</table><br>
								<table>
									<tr>
										<td id="ShowButtons" colspan="4">
										</td>
<!-- 										
										<td id="ShowAfterScan" colspan="4">
										</td> -->
									</tr>
								
								</table>
<!-- 									<div id="saveOrUndo">

									</div> -->
<%--                                     <logic:equal name="clfxxcjForm" property="saveIsSuccess" value="1">
   										<IMG alt=打印代开发票申请表  height=22 id=dydkfpsqb name=dydkfpsqb
            								 onclick="doSubmitForm('ToPrint');" onmouseout=MM_swapImgRestore()
                                             onmouseover="MM_swapImage('dydkfpsqb','','<%=static_file%>images/dydkfpsqb2.jpg',1)"
                                             src="<%=static_file%>images/dydkfpsqb1.jpg" style="CURSOR: hand" width=150>                                  
                                    </logic:equal>
                                    
   										<IMG alt=转卖方税款征收  height=22 id=mfskzs name=mfskzs
            								 onclick="doSubmitForm('ToSellerQSZS');" onmouseout=MM_swapImgRestore()
                                             onmouseover="MM_swapImage('mfskzs','','<%=static_file%>images/mfskzs2.jpg',1)"
                                             src="<%=static_file%>images/mfskzs1.jpg" style="CURSOR: hand" width=130> 
                                             
   										<IMG alt=转发票代开  height=22 id=fpdk name=fpdk
            								 onclick="doSubmitForm('ToFPDK');" onmouseout=MM_swapImgRestore()
                                             onmouseover="MM_swapImage('fpdk','','<%=static_file%>images/fpdk2.jpg',1)"
                                             src="<%=static_file%>images/fpdk1.jpg" style="CURSOR: hand" width=150> 
                                             
                                        <IMG alt=转契税申报  height=22 id=qssb name=qssb
            								 onclick="doSubmitForm('ToQSSB');" onmouseout=MM_swapImgRestore()
                                             onmouseover="MM_swapImage('qssb','','<%=static_file%>images/qssb2.jpg',1)"
                                             src="<%=static_file%>images/qssb1.jpg" style="CURSOR: hand" width=100> 
                                             
   										<IMG alt=退出  height=22 id=tuichu name=tuichu
            								 onclick="doSubmitForm('Return');" onmouseout=MM_swapImgRestore()
                                             onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
                                             src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand" width=79> --%>                                                                                                                                    
					</html:form>
						<%@ include file="../include/Bottom.jsp" %>
					</td>
					</tr>
					</TABLE>
					</td></tr>				
				</TABLE>
			</td>
		</tr>
	</TABLE>

	</BODY>
		    <script type="text/javascript">
		    var isSaved =false;
		    var saveIsSuccess="";
		    saveIsSuccess = '<bean:write name="clfxxcjForm" property="saveIsSuccess"/>';

	    //此函数是保存完成之后用于显示已保存信息的函数
	    function showSaveData(){
	    	isSaved = true;
			//二维码扫描数据
			var res_UNEpiccode = '<bean:write name="clfxxcjForm" property="UNEpiccode"/>';
			putObjectValue(document.forms[0].UNEpiccode,"",res_UNEpiccode);
			
			
	    	var res_sbbh = '<bean:write name="clfxxcjForm" property="sbbh"/>';
	    	putObjectValue(document.forms[0].sbbh,'',res_sbbh);
	    	//第1段：头标志符
	    	var res_bbbs = '<bean:write name="clfxxcjForm" property="bbbs"/>';
	    	putObjectValue(document.forms[0].bbbs,"",res_bbbs);
	    	//第2段：合同编号
	    	var res_htbh = '<bean:write name="clfxxcjForm" property="htbh"/>';
	    	putObjectValue(document.forms[0].htbh,"",res_htbh);
	    	document.getElementById("htbhShow").value=res_htbh; 
 	    	//第3段：合同网上签约日期
 	    	var res_htwsqyrq = '<bean:write name="clfxxcjForm" property="htwsqyrq"/>';
 	    	putObjectValue(document.forms[0].htwsqyrq,'',transTime("合同网上签约日期",res_htwsqyrq,"yyyymmdd"));
	    	putObjectValue("",'htwsqyrqShow',transTime("合同网上签约日期",res_htwsqyrq,"yyyy年mm月dd日"));
	    	//第4段：交易房屋地址区县
	    	var res_fwzlqx = '<bean:write name="clfxxcjForm" property="fwzlqx"/>';
	    	putObjectValue(document.forms[0].fwzlqx,'',res_fwzlqx);
	    	//第5段：交易房屋地址
	    	var res_fwzldz = '<bean:write name="clfxxcjForm" property="fwzldz"/>';
	    	putObjectValue(document.forms[0].fwzldz,"",res_fwzldz);
	    	putObjectValue("","fwzldzShow","<div align=left>"+res_fwzldz+"</div>");
	    	//第6段：房屋权属转移类型
	    	var res_fwqszylxmc = "房屋买卖";
	    	putObjectValue(document.forms[0].fwqszylx,"",res_fwqszylxmc);
	    	putObjectValue(document.forms[0].fwqszylxmc,"fwqszylxmcShow",res_fwqszylxmc);
	    	
	    	//第7段：是否为首次上市已购公房
	    	var res_sfwscsssggf ='<bean:write name="clfxxcjForm" property="sfwscsssggf"/>';
	    	putObjectValue(document.forms[0].sfwscsssggf,"",res_sfwscsssggf);
	    	putObjectValue(document.forms[0].sfwscsssggfmc,"sfwscsssggfmcShow",getsfscssgf_mc(res_sfwscsssggf));	
	    	//第8段：房屋产权证号
	    	var res_fwcqzh ='<bean:write name="clfxxcjForm" property="fwcqzh"/>';
	    	putObjectValue(document.forms[0].fwcqzh,"fwcqzhShow",res_fwcqzh);
	    	
	    	//第9段：房屋所有权证填发日期
	    	var res_fwsyqztfrq ='<bean:write name="clfxxcjForm" property="fwsyqztfrq"/>';
	    	putObjectValue(document.forms[0].fwsyqztfrq,"",transTime("房屋所有权证填发日期",res_fwsyqztfrq,"yyyymmdd"));
	    	putObjectValue("","fwsyqztfrqShow",transTime("房屋所有权证填发日期",res_fwsyqztfrq,"yyyy年mm月dd日"));		
	    	//第10段：房屋建筑面积
	    	var res_fwjzmj = '<bean:write name="clfxxcjForm" property="fwjzmj"/>';
	    	if(delFuhao(res_fwjzmj) != "0.00"){
	    		putObjectValue(document.forms[0].fwjzmj,"",delFuhao(res_fwjzmj));
	    		putObjectValue("","fwjzmjShow",delFuhao(res_fwjzmj)+"平方米");	
	    	}
	    	//第11段：建筑结构
	    	var res_jzjgdm = '<bean:write name="clfxxcjForm" property="jzjgdm"/>';
	    	putObjectValue(document.forms[0].jzjgdm,"",res_jzjgdm);	
	    	putObjectValue(document.forms[0].jzjgmc,"jzjgmcShow",getjzjg_mc(res_jzjgdm));	
	    	//第12段：规划用途
	    	var res_ghyt = '<bean:write name="clfxxcjForm" property="ghyt"/>';
	    	putObjectValue(document.forms[0].ghyt,"ghytShow",res_ghyt);	
	    	//第13段：合同总价
	    	//合同总价即为人民币金额 
	    	var res_htzj = '<bean:write name="clfxxcjForm" property="htzj"/>';
	    	if(delFuhao(res_htzj) != "0.00"){
	    		putObjectValue(document.forms[0].htzj,"",delFuhao(res_htzj));
	    		putObjectValue("","rmbjeShow",delFuhao(res_htzj)+"元");
	    	}
	    	//第14段：所在楼层，总层数
	    	var res_szlc = '<bean:write name="clfxxcjForm" property="szlc"/>';
	    	putObjectValue(document.forms[0].szlc,"szlcShow",res_szlc);	
	    	//第15段：外币种
 	    	var res_bzdm ='<bean:write name="clfxxcjForm" property="bzdm"/>';
	    	putObjectValue(document.forms[0].bzdm,"",res_bzdm);	 
	    	//第16段：外币金额
	    	var res_wbje = '<bean:write name="clfxxcjForm" property="wbje"/>';
	    	putObjectValue(document.forms[0].wbje,"",delFuhao(res_wbje));
	    	putObjectValue("","wbjeShow",delFuhao(res_wbje)+"元");	
	    	//第17段：汇率
	    	var res_hl ='<bean:write name="clfxxcjForm" property="hl"/>';
	    	putObjectValue(document.forms[0].hl,"hlShow",res_hl);	
/* 	    	//第18段：卖方信息
	    	var sellerArr = tmpObj.split("~");	
	    	if(tmpObj != null && tmpObj !=""){
	    		isSuccess = setBuyorSellInfo(tmpObj,"sellTab",document.forms[0].all_sellerInfo);
	    		
	    		if(!isSuccess){
	    			return false;
	    		}
	    	}
	    	
	    	//第19段：买方信息
	    	index=19+0;
	    	tmpObj=transObjArray[index];
	    	if(tmpObj != null && tmpObj !=""){
	    		isSuccess = setBuyorSellInfo(tmpObj,"buyTab",document.forms[0].all_buyerInfo);
	    		if(!isSuccess){
	    			return false;
	    		}
	    		
	    		//扫描成功
	    		sm_success = true;
	    	} */
	    	
/* 	    	//卖方信息
	    	var sellerInfo = '<bean:write name="clfxxcjForm" property="all_sellerInfo"/>';
	    	parseSaveBuyorSellInfo(sellerInfo,"sellTab",document.forms[0].all_sellerInfo);
	    	alert(sellerInfo);
	    	
	    	//买方信息
	    	var buyyerInfo = '<bean:write name="clfxxcjForm" property="all_buyerInfo"/>';
	    	
			parseSaveBuyorSellInfo(buyyerInfo,"buyTab",document.forms[0].all_buyerInfo);
 */
 
 	    	

	    	
	    	var transObjArray=res_UNEpiccode.split("^");
	    	var index =0;
	    	//第18段：卖方信息
	    	index=18+0;
	    	tmpObj=transObjArray[index];
	    	var sellerArr = tmpObj.split("~");
	    	//alert("第18段：卖方信息::::"+sellerArr);	
	    	if(tmpObj != null && tmpObj !=""){
	    		isSuccess = setBuyorSellInfo(tmpObj,"sellTab",document.forms[0].all_sellerInfo);
	    		
	    		if(!isSuccess){
	    			return false;
	    		}
	    	}
	    	
	    	
	    	
	    	//第19段：买方信息
	    	index=19+0;
	    	tmpObj=transObjArray[index];
	    	if(tmpObj != null && tmpObj !=""){
	    		isSuccess = setBuyorSellInfo(tmpObj,"buyTab",document.forms[0].all_buyerInfo);
	    		if(!isSuccess){
	    			return false;
	    		}
	    		
	    		//扫描成功
	    		sm_success = true;
	    	}
	    	
	    	
	    	//第20段：房地产中介机构名称	
	    	var res_fdczjjgmc = '<bean:write name="clfxxcjForm" property="fdczjjgmc"/>';
	    	putObjectValue(document.forms[0].fdczjjgmc,"fdczjjgmcShow",res_fdczjjgmc); 
	    	
	    	
	    	//21 :房屋性质代码(新增字段)
	    	var fwxzdm ='<bean:write name="clfxxcjForm" property="fwxzdm"/>';
	    	putObjectValue(document.forms[0].fwxzdm,"",fwxzdm);//房屋性质代码(新增字段)
	    	
	    }
	    
	    
	    
	    var sm_success = false;//已完成扫描
	    function initPage(){
	    	//alert("saveIsSuccess:::"+saveIsSuccess);
	    	//alert(saveIsSuccess != "0");
	    	//alert(saveIsSuccess != 0);
	    	//alert(saveIsSuccess != '0');
	    	
	    	//alert(saveIsSuccess == 0);
	    	ctrl_Buttons("BeforeScan");
	    	//如果保存成功或者保存失败
		    if(saveIsSuccess != "0"){
		    	//alert("----------111111111");
		    	showSaveData();
		    }else{
		    	//alert("----------22222222222");
		    	//需要进行扫描时，定位扫描位置
	    		document.all.htbhShow.focus();
		    }
	    }
	    
	  //扫描枪扫入数据后自动触发键盘事件
	    function document.onkeydown(){
		  //alert("hell0+++++++++++++");
	      //IE7.0以下触发的是回车
	      if(event.keyCode==13){
	    	  //alert("hell0++-------------");
	    	  if(sm_success == false ){
	        		scanPic();//解析二维码
	        		
			      //显示复位按钮
			      if(sm_success){
			      	ctrl_Buttons("AfterScan");
			      }
	    	  }
	        return false;
	      }
	      
	      if(event.keyCode==8){
	    	  //要是没有扫描成功，则可以用退格键删除已输入信息
	    	  if(sm_success == false ){
	    		  return true;
	    	  }
	    	  //如果其他情况
	    	  return false;
	      }

	      

	    }
	  
	  function ctrl_Buttons(type){
		  if(type == "BeforeScan"){
			  //document.all.saveOrUndo.style.display = "";

			  display_td_ShowBeforeScan();
			  
			  return false;
			  
		  }
		  
		  if(type == "AfterScan"){
			  //显示复位图标
			  //document.all.saveOrUndo.style.display = "none"; 
			  
			 //ShowBeforeScan
				display_td_ShowAfterScan();

			 
			  if(saveIsSuccess != "0"){
				  display_td_ShowBeforeScan();
			  }
			  return false;
		  }
	  }
	  
	  //公共按钮
	  
	  var public_button_html="";
 	  //var ToSellerQSZS_button_html ="<IMG alt=转卖方税款征收  height=22 id=mfskzs1 name=mfskzs1 onclick=\"doSubmitForm('ToSellerQSZS')\" onmouseout=MM_swapImgRestore() onmouseover=\"MM_swapImage('mfskzs1','','<%=static_file%>images/mfskzs2.jpg',1)\" src=\"<%=static_file%>images/mfskzs1.jpg\" style='CURSOR: hand' width=130>";
	 //var ToFPDK_button_html="<IMG alt=转发票代开  height=22 id=fpdk1 name=fpdk1 onclick=\"doSubmitForm('ToFPDK')\" onmouseout=MM_swapImgRestore() onmouseover=\"MM_swapImage('fpdk1','','<%=static_file%>images/fpdk2.jpg',1)\" src=\"<%=static_file%>images/fpdk1.jpg\" style='CURSOR: hand' width=100>";
      //var ToQSSB_button_html="<IMG alt=转契税申报  height=22 id=qssb1 name=qssb1 onclick=\"doSubmitForm('ToQSSB')\" onmouseout=MM_swapImgRestore() onmouseover=\"MM_swapImage('qssb1','','<%=static_file%>images/qssb2.jpg',1)\" src=\"<%=static_file%>images/qssb1.jpg\" style='CURSOR: hand' width=100>";
      var tuichu_button_html="<IMG alt=退出  height=22 id=tuichu1 name=tuichu1 onclick=\"doSubmitForm('Return')\" onmouseout=MM_swapImgRestore() onmouseover=\"MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)\" src=\"<%=static_file%>images/tuichu1.jpg\" style='CURSOR: hand' width=79>";
			
      // public_button_html = ToSellerQSZS_button_html +"\n"+ ToFPDK_button_html +"\n"+ ToQSSB_button_html +"\n"+ tuichu_button_html;
       //public_button_html = ToSellerQSZS_button_html +"\n"+ ToQSSB_button_html +"\n"+ tuichu_button_html; 
       public_button_html = tuichu_button_html; 
		//保存按钮，需要在扫描成功之后和保存失败时显示、页面初始化时也不显示
		
 	  var baocun_botton_html="<IMG alt=保存 height=22 id=baocun name=baocun onclick=\"doSubmitForm('Save')\" onmouseout=MM_swapImgRestore() onmouseover=\"MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)\" src=\"<%=static_file%>images/baocun1.jpg\" style='CURSOR: hand' width=79>";
             
         //复位按钮，除了扫描不成功外，其他情况都显示    、页面初始化时也不显示
      var fuwei_botton_html="<IMG alt=清除扫描信息，重新扫描 height=22 id='fuwei' name='fuwei' onclick=\"qkxx()\" onmouseout=MM_swapImgRestore() onmouseover=\"MM_swapImage('fuwei','','<%=static_file%>images/fuwei2.jpg',1)\" src=\"<%=static_file%>images/fuwei1.jpg\" style='CURSOR: hand' width=79>";
			
		//转打印代开发票申请表 只能是保存成功之后才显示
	  //var ToPrint_button_html="<IMG alt=打印代开发票申请表  height=22 id=dydkfpsqb name=dydkfpsqb  onclick=\"doSubmitForm('ToPrint')\" onmouseout=MM_swapImgRestore() onmouseover=\"MM_swapImage('dydkfpsqb','','<%=static_file%>images/dydkfpsqb2.jpg',1)\" src=\"<%=static_file%>images/dydkfpsqb1.jpg\" style='CURSOR: hand' width=150>";
	    //转核定信息页面
	  var ToPrint_button_html="<IMG alt=下一步  height=22 id=xiayibu name=xiayibu  onclick=\"doSubmitForm('ToPrint')\" onmouseout=MM_swapImgRestore() onmouseover=\"MM_swapImage('xiayibu','','<%=static_file%>images/xiayibu2.jpg',1)\" src=\"<%=static_file%>images/xiayibu1.jpg\" style='CURSOR: hand' width=79>";
				
	  
	  function display_td_ShowBeforeScan(){
/* 		  
		  document.all.ShowBeforeScan.style.display = "none"; 
		  document.all.ShowAfterScan.style.display = "";  */
		 // document.all.htbhShow.readOnly=false;
		 // document.all.htbhShow.disabled=false;
		 set_htbhShow_readOnly_disabled(false);
		  //显示按钮
/* 		   document.all.ShowAfterScan.innerHTML=public_button_html;  */
			
			if(saveIsSuccess == "0"){
		 		 document.all.ShowButtons.innerHTML=public_button_html;
			}
			
			if(saveIsSuccess == "error"){
				 // document.all.htbhShow.readOnly=true;
				 // document.all.htbhShow.disabled=true;
				 set_htbhShow_readOnly_disabled(true);
				document.all.ShowButtons.innerHTML=baocun_botton_html +"\n"+ fuwei_botton_html+"\n"+public_button_html;
				
			}
			
			if(saveIsSuccess == "1"){
				 // document.all.htbhShow.readOnly=true;
				  //document.all.htbhShow.disabled=true;
				  set_htbhShow_readOnly_disabled(true);
				document.all.ShowButtons.innerHTML=fuwei_botton_html+"\n"+ToPrint_button_html+"\n"+public_button_html;
				
			}
			
			
		  
		  
		  
	  }
	  function display_td_ShowAfterScan(){
		 // document.all.htbhShow.readOnly=true;
		  //document.all.htbhShow.disabled=true;
		  set_htbhShow_readOnly_disabled(true);
		  //显示按钮
		  var showAfterScan_button_html ="";
		  
		  //扫描成功，显示复位按钮
		  if(sm_success){
			  showAfterScan_button_html =  baocun_botton_html +"\n"+ fuwei_botton_html; 
		  }
		  
		  document.all.ShowButtons.innerHTML=showAfterScan_button_html +"\n"+ public_button_html; 
		  
	  }
	  
	  function set_htbhShow_readOnly_disabled(val){
		  
		  document.all.htbhShow.readOnly=val;
		  document.all.htbhShow.disabled=val;
	  }
	  
	  
	  
	  function display_td_ShowAfterScan1(){
/* 		  document.all.ShowBeforeScan.style.display = ""; 
		  document.all.ShowAfterScan.style.display = "none";  */
		  document.all.htbhShow.readOnly=false;
		  document.all.htbhShow.disabled=false;
		  
		  //显示按钮
		  var showAfterScan_button_html ="";
		  
		  
		  if(saveIsSuccess != "1"){
			  if(saveIsSuccess =="error"){
			   	showAfterScan_button_html = baocun_botton_html +"\n"+ fuwei_botton_html; 
			  }else{
				  //扫描成功显示复位按钮
				  if(sm_success){
				  	showAfterScan_button_html = fuwei_botton_html; 
				  }
			  }
			  
		  }
		  
		  if(saveIsSuccess == "1"){
			   showAfterScan_button_html =  fuwei_botton_html +"\n"+ ToPrint_button_html; 
		  }
		   //document.all.ShowBeforeScan.innerHTML=showAfterScan_button_html +"\n"+ public_button_html; 
		   document.all.ShowButtons.innerHTML=showAfterScan_button_html +"\n"+ public_button_html; 
	  }
	  
	  
	  //调用动态库，识别图片，把识别出来的内容显示到页面上
	    function scanPic(){
	      //alert(">>>扫描完毕开始解析！");
	      var istr=document.forms[0].htbhShow.value;
	      var obj=new ActiveXObject("hyQRBarCode.QRCode");
	    	//alert("obj="+obj);
	    	var transObj=obj.DeBarCodeString(istr);
	    	//transObj="^object_000002_000002^C533761^2011-12-22^7^海淀区车道沟南里14号楼1层103^00000002^0^X京房权证海字第178966号^2010-06-26^53.60^2^住宅^1000000^1/21^^^^龙丽君~1~1~371082197905142128~20%~13439509057~龙1~1~1~371082197905142128~20%~13439509057~龙3~1~1~371082197905142128~60%~13439509057^杨晶~1~1~420281198209250435~50%~13141388733~杨1~1~1~420281198209250435~30%~13141388733~杨2~1~1~420281198209250435~10%~13141388733~杨3~1~1~420281198209250435~10%~13141388733^北京链家房地产经纪有限公司";
	    	//alert("transObj="+transObj);
	    	if(transObj == null || transObj ==""){
	    		alert("无扫描信息，无法解析，请重新扫描！");
	    		return false;
	    	}
	    	
	    	//判定是否空业务条形码
	    	var transObjArray=transObj.split("^");
	    	
<%-- 	    	if(transObjArray.length==1&&transObj=="<%=com.creationstar.bjtax.qsgl.util.Constants.QRCODE_DEFAULT_NULL%>"){
	    		alert("扫描条形码内容为空，请手工录入申报表！");
	    		return false;
	    	} 
--%>

	    	//检查二维码明文是否正确；
	    	//alert("开始检查二维码头数据的格式");
	    	QRCodeCheckResult=checkQRCodeHeader(transObj);
	    	//alert("QRCodeCheckResult="+QRCodeCheckResult);
	    	if(QRCodeCheckResult=="1"){
	    		//alert("11111111111+++++++++++"+transObj);
	    		alert("扫描图片失败，请重新扫描或手工录入信息！");
 /* 	            fuwei();
	    		QRInputFocus(); */ 
	    		return false;
	    	}else if(QRCodeCheckResult=="2"){//如果明文不为"object_"开头则表示条码图片扫描不全，提示继续扫描
	    		//alert("22222222222222222+++++++++++"+transObj);
	    		alert("请扫描全部二维条形码图片，否则无法解码！");
/* 	    		fuwei();
	    		QRInputFocus(); */
	    		return false;
	    	}else if(QRCodeCheckResult=="3"){
	    		//alert("333333333+++++++++++"+transObj);
 	          translate(transObj);//新房
/*	          translate_xf(transObj);//设置为新房
	          document.forms[0].smbs.value='1'; */
	    	}else if(QRCodeCheckResult=="4"){
	            //alert("44444444444+++++++++++"+transObj);
 	    		translate(transObj);//2手房
 	    		/*	            translate_esf(transObj);//设置为二手房
	            document.forms[0].smbs.value='1'; */
	    	}
	    	//清空录入域并重新置焦点
	    	//document.all.htbh.value="";
	    	document.all.htbhShow.focus();
	    	
	    	//
	    	//设置标志位
/* 	    	document.forms[0].qrScanSbFlag.value="1"; */
	    	return false;
	    }

	    function translate(translateObj){	
	    	//清除信息
	    	undo();
	    	
	    	var isSuccess = false;
	    	var transObjArray=translateObj.split("^");
	    	putObjectValue(document.forms[0].UNEpiccode,"",translateObj);
	    	//第1段：头标志符（不作处理）
	    	index=0;
	    	tmpObj=transObjArray[index];
	    	
	    	//alert("第0段：::::"+tmpObj);		
	    	//第1段：头标志符（不作处理）
	    	index=1+0;
	    	tmpObj=transObjArray[index];
	    	putObjectValue(document.forms[0].bbbs,"",tmpObj);
	    	//alert("第1段：头标志符::::"+tmpObj);	
	    	//第2段：合同编号
	    	index=2+0;
	    	tmpObj=transObjArray[index];
	    	putObjectValue(document.forms[0].htbh,"",tmpObj);
	    	document.getElementById("htbhShow").value=tmpObj; 
	    	//alert("第2段：合同编号::::"+tmpObj);
	    	//第3段：合同网上签约日期
	    	index=3+0;
	    	tmpObj=transObjArray[index];
	    	tmpObj=formatDateStr(tmpObj);
	    	putObjectValue(document.forms[0].htwsqyrq,'',transTime("合同网上签约日期",tmpObj,"yyyymmdd"));
	    	putObjectValue("",'htwsqyrqShow',transTime("合同网上签约日期",tmpObj,"yyyy年mm月dd日"));
	    	//alert("第3段：合同网上签约日期::::"+tmpObj);
	    	//第4段：交易房屋地址区县（不作处理）
	    	index=4+0;
	    	tmpObj=transObjArray[index];
	    	tmpObj=formatDateStr(tmpObj);
	    	putObjectValue(document.forms[0].fwzlqx,'',tmpObj);
	    	//alert("第4段：交易房屋地址区县::::"+tmpObj);
	    	//第5段：交易房屋地址
	    	index=5+0;
	    	tmpObj=transObjArray[index];
	    	putObjectValue(document.forms[0].fwzldz,"",tmpObj);
	    	putObjectValue("","fwzldzShow","<div align=left>"+tmpObj+"</div>");
	    	//alert("第5段：交易房屋地址::::"+tmpObj);
	    	//第6段：房屋权属转移类型
	    	index=6+0;
	    	tmpObj=transObjArray[index];
	    	putObjectValue(document.forms[0].fwxzdm,"",tmpObj);//房屋性质代码(新增字段)
	    	//alert("第6段：房屋权属转移类型::::"+tmpObj);
	    	tmpObj="03";
	    	putObjectValue(document.forms[0].fwqszylx,"",tmpObj);//房屋权属转移类型固定为03
	    	putObjectValue(document.forms[0].fwqszylxmc,"fwqszylxmcShow","房屋买卖");
	    	
	    	
	    /* 	tmpObj="03";
	    	tmpObj_page=document.forms[0].tdfwqszylx;
	    	for(i=0;i<tmpObj_page.options.length;i++){
	    		if(tmpObj_page.options.value=tmpObj){
	    			tmpObj_page.style.color="#ADADAD";
	    			tmpObj_page.selectedIndex=i;
	    			tmpObj_page.value=tmpObj;
	    			tmpObj_page.onclick=function qr_readonly_tdfwqszylx(){alert("请勿修改！");return false;};//置位只读
	    			checkSelect();
	    		}
	    	} */
	    	//第7段：是否为首次上市已购公房
	    	index=7+0;
	    	tmpObj=transObjArray[index];
	    	//TODO
	    	putObjectValue(document.forms[0].sfwscsssggf,"",tmpObj);
	    	putObjectValue(document.forms[0].sfwscsssggfmc,"sfwscsssggfmcShow",getsfscssgf_mc(tmpObj));
	    	//alert("第7段：是否为首次上市已购公房::::"+tmpObj);	
	    	//第8段：房屋产权证号
	    	index=8+0;
	    	tmpObj=transObjArray[index];
	    	putObjectValue(document.forms[0].fwcqzh,"fwcqzhShow",tmpObj);
	    	//alert("第8段：房屋产权证号::::"+tmpObj);	
	    	//第9段：房屋所有权证填发日期
	    	index=9+0;
	    	tmpObj=transObjArray[index];
	    	//alert("房屋所有权证填发日期111"+tmpObj);
	    	putObjectValue(document.forms[0].fwsyqztfrq,"",transTime("房屋所有权证填发日期",tmpObj,"yyyymmdd"));
	    	putObjectValue("","fwsyqztfrqShow",transTime("房屋所有权证填发日期",tmpObj,"yyyy年mm月dd日"));
	    	//alert("第9段：房屋所有权证填发日期::::"+tmpObj);		
	    	//第10段：房屋建筑面积
	    	index=10+0;
	    	tmpObj=transObjArray[index];
	    	putObjectValue(document.forms[0].fwjzmj,"",delFuhao(tmpObj));
	    	putObjectValue("","fwjzmjShow",delFuhao(tmpObj)+"平方米");
	    	//alert("第10段：房屋建筑面积::::"+tmpObj);	
	    	//第11段：建筑结构
	    	index=11+0;
	    	tmpObj=transObjArray[index];
	    	//TODO
	    	putObjectValue(document.forms[0].jzjgdm,"",tmpObj);
	    	putObjectValue(document.forms[0].jzjgmc,"jzjgmcShow",getjzjg_mc(tmpObj));
	    	//alert("第11段：建筑结构::::"+tmpObj);		
	    	//第12段：规划用途
	    	index=12+0;
	    	tmpObj=transObjArray[index];
	    	putObjectValue(document.forms[0].ghyt,"ghytShow",tmpObj);
	    	//alert("第12段：规划用途::::"+tmpObj);		
	    	//第13段：合同总价
	    	index=13+0;
	    	tmpObj=transObjArray[index];
	    	//合同总价即为人民币金额 
	    	putObjectValue(document.forms[0].htzj,"",delFuhao(tmpObj));
	    	putObjectValue("","rmbjeShow",delFuhao(tmpObj)+"元");
	    	//alert("第13段：合同总价::::"+tmpObj);	
	    	//第14段：所在楼层，总层数
	    	index=14+0;
	    	tmpObj=transObjArray[index];
	    	putObjectValue(document.forms[0].szlc,"szlcShow",tmpObj);
	    	//alert("第14段：所在楼层，总层数::::"+tmpObj);		
	    	//第15段：外币种
	    	index=15+0;
	    	tmpObj=transObjArray[index];
	    	putObjectValue(document.forms[0].bzdm,"",tmpObj);
	    	//alert("第15段：外币种::::"+tmpObj);		
	    	//第16段：外币金额
	    	index=16+0;
	    	tmpObj=transObjArray[index];
	    	putObjectValue(document.forms[0].wbje,"wbjeShow",delFuhao(tmpObj));
	    	putObjectValue("","wbjeShow",delFuhao(tmpObj)+"元");
	    	//alert("第16段：外币金额::::"+tmpObj);		
	    	//第17段：汇率
	    	index=17+0;
	    	tmpObj=transObjArray[index];
	    	putObjectValue(document.forms[0].hl,"hlShow",tmpObj);
	    	//alert("第17段：汇率::::"+tmpObj);	
	    	//第18段：卖方信息
	    	index=18+0;
	    	tmpObj=transObjArray[index];
	    	var sellerArr = tmpObj.split("~");
	    	//alert("第18段：卖方信息::::"+sellerArr);	
	    	if(tmpObj != null && tmpObj !=""){
	    		isSuccess = setBuyorSellInfo(tmpObj,"sellTab",document.forms[0].all_sellerInfo);
	    		
	    		if(!isSuccess){
	    			return false;
	    		}
	    	}
	    	
/* 	    	//@@名称（姓名）
	    	putObjectValue(document.forms[0].sell_mc,"sell_mcShow",sellerArr[0]);
	    	//@@类别
	    	putObjectValue(document.forms[0].sell_lb,"",sellerArr[1]);
	    	putObjectValue(document.forms[0].sell_lb_mc,"sell_lbShow","类别名称TODO");
	    	//@@证件类型
	    	putObjectValue(document.forms[0].sell_zjlx,"",sellerArr[2]);
	    	putObjectValue(document.forms[0].sell_zjlx_mc,"sell_zjlxShow","证件类型名称TODO");	    	
	    	//@@证件号码
	    	putObjectValue(document.forms[0].sell_zjhm,"sell_zjhmShow",sellerArr[3]);
	    	//@@权利人份额
	    	putObjectValue(document.forms[0].sell_qlrfe,"sell_qlrfeShow",sellerArr[4]);
	    	//@@联系人电话
	    	putObjectValue(document.forms[0].sell_lxdh,"sell_lxdhShow",sellerArr[5]); */
	    	
	    	
	    	
	    	//第19段：买方信息
	    	index=19+0;
	    	tmpObj=transObjArray[index];
	    	if(tmpObj != null && tmpObj !=""){
	    		isSuccess = setBuyorSellInfo(tmpObj,"buyTab",document.forms[0].all_buyerInfo);
	    		if(!isSuccess){
	    			return false;
	    		}
	    		
	    		//扫描成功
	    		sm_success = true;
	    	}
	    	
/* 	    	var mfxxArr = tmpObj.split("~");
	    	alert("第19段：买方信息::::"+mfxxArr);	
	    	alert("第19段：买方信息::::tmpObj "+mfxxArr);
	    	//@@名称（姓名）
	    	putObjectValue(document.forms[0].buy_mc,"buy_mcShow",mfxxArr[0]);
	    	//@@类别
	    	putObjectValue(document.forms[0].buy_lb,"",mfxxArr[1]);
	    	putObjectValue(document.forms[0].buy_lb_mc,"buy_lbShow","类别名称TODO");
	    	//@@证件类型
	    	putObjectValue(document.forms[0].buy_zjlx,"",mfxxArr[2]);
	    	putObjectValue(document.forms[0].buy_zjlx_mc,"buy_zjlxShow","证件类型名称TODO");	    	
	    	//@@证件号码
	    	putObjectValue(document.forms[0].buy_zjhm,"buy_zjhmShow",mfxxArr[3]);
	    	//@@权利人份额
	    	putObjectValue(document.forms[0].buy_qlrfe,"buy_qlrfeShow",mfxxArr[4]);
	    	//@@联系人电话
	    	putObjectValue(document.forms[0].buy_lxdh,"buy_lxdhShow",mfxxArr[5]);	 */    	
	    	
	    	//@
	    /*   	if(mfxx[1] == "2"){
	      		//纳税人名称(2009.2.9修改为将纳税人名称输入到隐藏域----yangxiao)
	            document.forms[0].smnsrmc.value=mfxx[0];

	      		for(i=0;i<document.getElementsByName("nsrmc");i++){
	      			document.getElementsByName("nsrmc")[i].value=mfxx[0];
	      		}
	      		var ele_nsrmc_show=document.getElementById('nsrmc_show');
	      		new_text=document.createTextNode(mfxx[0]);
	      		ele_nsrmc_show.appendChild(new_text);
	      		//证件号码(2009.2.9修改为不写入，需要手动输入----yangxiao)
	      		//document.forms[0].jsjdm.value=mfxx[3];
	      		//联系人（没有）

	      		//联系人电话
	      		document.forms[0].lxdh.value=mfxx[5]; */
	      		
	      		
	      		
	      		
	      		

	    	//第20段：房地产中介机构名称	
	    index=20+0;
	    tmpObj=transObjArray[index];
	    putObjectValue(document.forms[0].fdczjjgmc,"fdczjjgmcShow",tmpObj);
	    //alert("第20段：房地产中介机构名称::::"+tmpObj);		
	    }
	    
	  //置值方法
	    function putObjectValue(hiddProperty,td_id,obj_value){
	    	if(hiddProperty != null && hiddProperty != ""){
	    		hiddProperty.value=obj_value;
	    	}
	    	if(td_id != "" && obj_value !=""){
		    		document.getElementById(td_id).innerHTML=obj_value; 
	    	}
	    	return true;
	    }
	  
	  //解析保存完成之后返回时的值
	  function parseSaveBuyorSellInfo(buyorSellInfo,tableId,hidPropertyObj){
		  
		  //alert("-----------0000000");
		  if(buyorSellInfo != null && buyorSellInfo != ""){
			  //alert("-----------1111");
			  // alert("infoArr 长度：："+buyorSellInfo);
			  if(buyorSellInfo.indexOf("^")>0){
				  var infoArr = buyorSellInfo.split("^");
				  //alert("infoArr 长度：："+infoArr.length);
				  
				  for(var index =0; index < infoArr.length;index ++){
					  var tempInfo = infoArr[index];
					 // alert("-----------每个人的信息tempInfo----"+tempInfo);
					  //调用解析方法，解析每个买卖方的信息并显示
					  setBuyorSellInfo(tempInfo,tableId,hidPropertyObj);
					  //alert("-----------22222222222");
				  }
			  }else{
				  setBuyorSellInfo(buyorSellInfo,tableId,hidPropertyObj);
			  }
		  }
		  
		  
	  }
	  
	  //设置买卖双方信息
	  function setBuyorSellInfo(info,tableId,hidPropertyObj){
		  		var isSuccess = false;
		  
	  			if(info == null || info ==""){
	  				return isSuccess;
	  			}
		  
	    	var tableobj=document.getElementById(tableId);
			var AllGroupInfoArr = info.split("~");
			var submitInfo ="";
			
			if(AllGroupInfoArr != null && AllGroupInfoArr !=""){
				var count = AllGroupInfoArr.length/6;
				//alert("信息组数"+count);
				
				if(count!= null && count!=0){
					var oneGroupInfo = new Array();
					for(zsIndex =0; zsIndex<count;zsIndex++){
						var value_0 = AllGroupInfoArr[0+(zsIndex*6)];
						var value_1 = AllGroupInfoArr[1+(zsIndex*6)];
						var value_2 = AllGroupInfoArr[2+(zsIndex*6)];
						var value_3 = AllGroupInfoArr[3+(zsIndex*6)];
						var value_4 = AllGroupInfoArr[4+(zsIndex*6)];
						var value_5 = AllGroupInfoArr[5+(zsIndex*6)];
					
						oneGroupInfo.push(value_0);
						oneGroupInfo.push(value_1);
						var DSzjdm=DSzjdm = getZjlxJwToDs(value_2);
						oneGroupInfo.push(DSzjdm);//证件类型代码
						oneGroupInfo.push(value_3);
						oneGroupInfo.push(value_4);
						oneGroupInfo.push(value_5);
						
						//alert("RETURN::"+DSzjdm);
						
						//设置并显示每组信息
						isSuccess = setOneGroupInfo(oneGroupInfo,tableobj);
						
						if(!isSuccess){
							alert("显示买卖人信息出错！");
							return isSuccess;
						}
						
						//拼接提交到后台的信息
						var tempOneGroupValue = value_0 +"~"+value_1+"~"+DSzjdm+"~"+value_3+"~"+value_4+"~"+value_5;
						if(zsIndex == 0){
							submitInfo = tempOneGroupValue;
						}else{
							submitInfo = submitInfo+"^"+tempOneGroupValue;
						}
						//重置信息
						oneGroupInfo = new Array();
					}
					//设置提交隐藏域的值
					if(hidPropertyObj != null && hidPropertyObj !=""){
						hidPropertyObj.value = submitInfo;
					}
				}
			}
			
			return true;
	  }
	  
	  //建委证件类型代码转地税证件类型代码
	  function getZjlxJwToDs(dm){
		  var res = zjlxJwToDs(dm);// this function in ../js/qscommon.js
		  
		  //如果没有返回转换结果，则做如下处理
		  if (res == null || res == ""){
			  res ="90";//其他
		  }
		  
		  return res;
	  }
	  
	  
	  
	  //把每组信息设置到td的innerHTML中显示
	  function setOneGroupInfo1(arr,tableobj){
		  
	  			if(tableobj == null || tableobj == ""){
	  				return false;
	  			}
	    		var otr = tableobj.insertRow();
	    		//第一行第一列  ”名称（姓名）“
	    		var mc_Cell=otr.insertCell();
	    		mc_Cell.innerHTML="名称（姓名）";
	    		mc_Cell.className ="2-td2-left";
	    		mc_Cell.WIDTH="20%";
	    		//第一行第二列 
	    		var mc_value_Cell=otr.insertCell();
	    		mc_value_Cell.innerHTML=arr[0];
	    		mc_value_Cell.className ="2-td2-left";
	    		mc_value_Cell.WIDTH="30%";
	    		//第一行第三列  “类别”
	    		var lb_Cell=otr.insertCell();
	    		lb_Cell.innerHTML="类别";
	    		lb_Cell.className ="2-td2-left";
	    		lb_Cell.WIDTH="20%";
	    		//第一行第四列  
	    		var lb_value_Cell=otr.insertCell();
	    		lb_value_Cell.innerHTML=nsrlb_mc(arr[1]);
	    		lb_value_Cell.className ="2-td2-center";
	    		lb_value_Cell.WIDTH="30%";
	    		
	    		
	    		//第二行第一列 “证件类型”
	    		var otr2 = tableobj.insertRow();
	    		var zjlx_Cell=otr2.insertCell();
	    		zjlx_Cell.innerHTML="证件类型";
	    		zjlx_Cell.className ="2-td2-left";
	    		zjlx_Cell.WIDTH="20%";	    		
	    		//第二行第二列
	    		var zjlx_value_Cell=otr2.insertCell();
	    		zjlx_value_Cell.innerHTML=nsrzjlb_mc(arr[2]);
	    		zjlx_value_Cell.className ="2-td2-left";
	    		zjlx_value_Cell.WIDTH="30%";	    		
	    		//第二行第一列 “证件号码”
	    		var zjhm_Cell=otr2.insertCell();
	    		zjhm_Cell.innerHTML="证件号码";
	    		zjhm_Cell.className ="2-td2-left";
	    		zjhm_Cell.WIDTH="20%";		    		
	    		//第二行第一列 
	    		var zjhm_value_Cell=otr2.insertCell();
	    		zjhm_value_Cell.innerHTML=arr[3];
	    		zjhm_value_Cell.className ="2-td2-center";
	    		zjhm_value_Cell.WIDTH="30%";
	    		
	    		
	    		//第三行第一列 “权利人份额”
	    		var otr3 = tableobj.insertRow();
	    		var fe_Cell=otr3.insertCell();
	    		fe_Cell.innerHTML="权利人份额";
	    		fe_Cell.className ="2-td2-left";
	    		fe_Cell.WIDTH="20%";
	    		//第三行第二列 
	    		var fe_value_Cell=otr3.insertCell();
	    		fe_value_Cell.innerHTML=arr[4];
	    		fe_value_Cell.className ="2-td2-left";
	    		fe_value_Cell.WIDTH="30%";	    		
	    		//第三行第三列 “联系人电话”
	    		var lxdh_Cell=otr3.insertCell();
	    		lxdh_Cell.innerHTML="联系人电话";
	    		lxdh_Cell.className ="2-td2-left";
	    		lxdh_Cell.WIDTH="20%";	    		
	    		//第三行第四列 
	    		var lxdh_value_Cell=otr3.insertCell();
	    		lxdh_value_Cell.innerHTML=arr[5];
	    		lxdh_value_Cell.className ="2-td2-center";
	    		lxdh_value_Cell.WIDTH="30%";	
	    		
	    		return true;
	  }
	  
	  //把每组信息设置到td的innerHTML中显示
	  function setOneGroupInfo(arr,tableobj){
		  
	  			if(tableobj == null || tableobj == ""){
	  				return false;
	  			}
	  			var tabLength = tableobj.rows.length;
	  			
	  			//初始化表头
	  			if(tabLength == 1){
		    		var otr = tableobj.insertRow();
		    		//第一行第一列  ”名称（姓名）“
		    		var mc_Cell=otr.insertCell();
		    		mc_Cell.innerHTML="名称（姓名）";
		    		mc_Cell.className ="2-td2-left";
		    		mc_Cell.WIDTH="30%";
		    		
		    		//第一行第二列  “类别”
		    		var lb_Cell=otr.insertCell();
		    		lb_Cell.innerHTML="类别";
		    		lb_Cell.className ="2-td2-left";
		    		lb_Cell.WIDTH="10%";
		    		
		    		//第一行第三列 “证件类型”
		    		var zjlx_Cell=otr.insertCell();
		    		zjlx_Cell.innerHTML="证件类型";
		    		zjlx_Cell.className ="2-td2-left";
		    		zjlx_Cell.WIDTH="10%";	
		    		
		    		//第一行第四列 “证件号码”
		    		var zjhm_Cell=otr.insertCell();
		    		zjhm_Cell.innerHTML="证件号码";
		    		zjhm_Cell.className ="2-td2-left";
		    		zjhm_Cell.WIDTH="20%";	
		    		
		    		//第一行第五列 “权利人份额”
		    		var fe_Cell=otr.insertCell();
		    		fe_Cell.innerHTML="权利人份额";
		    		fe_Cell.className ="2-td2-left";
		    		fe_Cell.WIDTH="10%";	
		    		
		    		//第一行第六列 “联系人电话”
		    		var lxdh_Cell=otr.insertCell();
		    		lxdh_Cell.innerHTML="联系人电话";
		    		lxdh_Cell.className ="2-td2-center";
		    		lxdh_Cell.WIDTH="20%";
	  			}
	  			
	    		//第n(n>=2)行第一列 
	    		var otr_n = tableobj.insertRow();
	    		var mc_value_Cell=otr_n.insertCell();
	    		mc_value_Cell.innerHTML=arr[0];
	    		mc_value_Cell.className ="2-td2-left";
	    		//mc_value_Cell.WIDTH="30%";

	    		//第n(n>=2)行第二列  
	    		var lb_value_Cell=otr_n.insertCell();
	    		lb_value_Cell.innerHTML=nsrlb_mc(arr[1]);
	    		lb_value_Cell.className ="2-td2-left";
	    		//lb_value_Cell.WIDTH="30%";
	    		
	    		
    		
	    		//第n(n>=2)行第三列
	    		var zjlx_value_Cell=otr_n.insertCell();
	    		//zjlx_value_Cell.innerHTML=nsrzjlb_mc(arr[2]); 
	    		//zjlx_value_Cell.innerHTML=getZjlxmc(arr[2]);
	    		zjlx_value_Cell.innerHTML=getZjmc(arr[2]);
	    		zjlx_value_Cell.className ="2-td2-left";
	    		//zjlx_value_Cell.WIDTH="30%";
	    		
	    		
	    		//第n(n>=2)行第四列 
	    		var zjhm_value_Cell=otr_n.insertCell();
	    		zjhm_value_Cell.innerHTML=arr[3];
	    		zjhm_value_Cell.className ="2-td2-left";
	    		//zjhm_value_Cell.WIDTH="30%";
	    		
	    		

	    		//第n(n>=2)行第五列 
	    		var fe_value_Cell=otr_n.insertCell();
	    		fe_value_Cell.innerHTML=arr[4];
	    		fe_value_Cell.className ="2-td2-left";
	    		//fe_value_Cell.WIDTH="30%";	    		
	    		
	    		//第n(n>=2)行第六列 
	    		var lxdh_value_Cell=otr_n.insertCell();
	    		lxdh_value_Cell.innerHTML=arr[5];
	    		lxdh_value_Cell.className ="2-td2-center";
	    		//lxdh_value_Cell.WIDTH="30%";	
	    		
	    		return true;
	  }
	  
	  
function getZjmc(zjdm){
		//alert(1111111111);
         <logic:iterate id="item" name="clfxxcjForm"  property ="zjList" indexId="index">
			var zjdm_1 = '<bean:write name="item" property="zjlxdm"/>';
			var zjmc_1 = '<bean:write name="item" property="zjlxmc"/>';
			
			if(zjdm == zjdm_1){
				
			return zjmc_1;
			
			}
		</logic:iterate> 
		
		return "其他";
	} 
	
	//清空信息
	function qkxx(){
		if(window.confirm("该操作将清空已扫描信息,请确认")){
			undo();
			return false;
		}else{
			return false;
		}
	
	}
	
	function  undo(){
		saveIsSuccess = "0";
		//document.forms[0].saveIsSuccess.value="0";
		//resetSaveIsSuccess(saveIsSuccess);
		//alert("页面上saveIsSuccess 的值为:::"+saveIsSuccess);
		//var ss = '<bean:write name="clfxxcjForm" property="saveIsSuccess"/>';
		//alert("服务器saveIsSuccess 的值为:::"+ss);
		
		
		sm_success = false;
		
		//alert("重置所有显示信息");
		document.getElementById("htbhShow").innerText="";
		document.getElementById("htwsqyrqShow").innerHTML="&nbsp;";
		document.getElementById("fwzldzShow").innerHTML="&nbsp;";
		document.getElementById("fwqszylxmcShow").innerHTML="&nbsp;";
		document.getElementById("sfwscsssggfmcShow").innerHTML="&nbsp;";
		document.getElementById("fwcqzhShow").innerHTML="&nbsp;";
		document.getElementById("fwsyqztfrqShow").innerHTML="&nbsp;";
		document.getElementById("fwjzmjShow").innerHTML="&nbsp;";
		document.getElementById("jzjgmcShow").innerHTML="&nbsp;";
		document.getElementById("ghytShow").innerHTML="&nbsp;";
		document.getElementById("rmbjeShow").innerHTML="&nbsp;";
		document.getElementById("szlcShow").innerHTML="&nbsp;";
		document.getElementById("wbjeShow").innerHTML="&nbsp;";
		document.getElementById("hlShow").innerHTML="&nbsp;";	
		document.getElementById("fdczjjgmcShow").innerHTML="&nbsp;";	
		
	
	/* 	document.getElementById("sell_mcShow").innerHTML="";
		document.getElementById("sell_lbShow").innerHTML="";
		document.getElementById("sell_zjlxShow").innerHTML="";
		document.getElementById("sell_zjhmShow").innerHTML="";
		document.getElementById("sell_qlrfeShow").innerHTML="";
		document.getElementById("sell_lxdhShow").innerHTML="";
		document.getElementById("buy_mcShow").innerHTML="";
		document.getElementById("buy_lbShow").innerHTML="";
		document.getElementById("buy_zjlxShow").innerHTML="";
		document.getElementById("buy_zjhmShow").innerHTML="";
		document.getElementById("buy_qlrfeShow").innerHTML="";
		document.getElementById("buy_lxdhShow").innerHTML=""; */
		//删除买卖人信息
		
		//删除买卖信息表的所有行
		resetTab("sellTab");
		resetTab("buyTab");
		
/* 		var sell_tableobj=document.getElementById("sellTab");
		if(sell_tableobj.rows.length != 1 && sell_tableobj.rows.length >=2){
			for(var index1 =sell_tableobj.rows.length ; index1 >1 ;index1 --){
				sell_tableobj.deleteRow(sell_tableobj.rows[index1]);
			}
		} */
		
/* 		var sell_tableobj=document.getElementById("sellTab");
		if(sell_tableobj.rows.length != 1 && sell_tableobj.rows.length >=2){
		     var rowNum=sell_tableobj.rows.length;
		     for (i=1;i<rowNum;)
		     {
		    	 sell_tableobj.deleteRow(i);
		         rowNum=rowNum-1;
		     }

		}
		
		var buy_tableobj=document.getElementById("buyTab");
		if(buy_tableobj.rows.length != 1 && buy_tableobj.rows.length >=2){
		     var rowNum=buy_tableobj.rows.length;
		     for (i=1;i<rowNum;)
		     {
		    	 buy_tableobj.deleteRow(i);
		         rowNum=rowNum-1;
		     }

		} */
		
		//transTime("时间1","20130101");
		//transTime("时间2","2013-01-01");
		//transTime("时间3","2013-01-01  22");
		
		ctrl_Buttons("BeforeScan");
		document.getElementById("htbhShow").focus();
	}
	
	//重置买卖方显示信息表，不删除第0行标题，即删除时从1开始，由于删除一行之后table的行会自动往上移，所以i不自增或者自减操作，即for循环中第三个运算为空
	function resetTab(tabId){
		var tableobj=document.getElementById(tabId);
		if(tableobj.rows.length != 1 && tableobj.rows.length >=2){
		     var rowNum=tableobj.rows.length;
		     for (i=1;i<rowNum;)
		     {
		    	 tableobj.deleteRow(i);
		    	 //删除之后，循环次数减1
		         rowNum=rowNum-1;
		     }

		}		
		
		
	}	
	
	/**
		校验数据库必填项
	*/
	function doCheckNotNullItems(){
		
		var isSuccess = true;
		//合同编号
		var htbh = document.forms[0].htbh.value;
		if(htbh ==null || htbh==""){
			alert("合同编号为空，请检查！");
			return false;
		}
		
		//房屋坐落地址
		var fwzldz = document.forms[0].fwzldz.value;
		if(fwzldz == null || fwzldz == ""){
			alert("房屋坐落地址为空,请检查！");
			return false;
		}
			
		//房屋建筑面积
		var fwjzmj = document.forms[0].fwjzmj.value;
		if(fwjzmj == null || fwjzmj ==""){
			
			alert("房屋建筑面积为空,请检查！");
			return false;		
		}
		//总层数
		 
		//所在楼层
		var szlc = document.forms[0].szlc.value;
		if(szlc == null || szlc ==""){
			alert("所在楼层为空,请检查！");
		
		return false;
		
		}
		
		//合同网上签约日期
		var  htwsqyrq = document.forms[0].htwsqyrq.value;
		if(htwsqyrq == null || htwsqyrq ==""){
			alert("合同网上签约日期,请检查！");
			
			return false;			
		
		}
		
		//合同总价
		var htzj = document.forms[0].htzj.value;
		if(htzj == null || htzj  ==""){
			alert("合同总价为空,请检查！");
			
			return false;			
		
			
		}
		
		//卖方信息
		var all_sellerInfo = document.forms[0].all_sellerInfo.value;
		if(all_sellerInfo == null || all_sellerInfo  ==""){
			alert("卖方信息为空,请检查！");
			
			return false;			
		
			
		}		
		//买方信息
		var all_buyerInfo = document.forms[0].all_buyerInfo.value;
		if(all_buyerInfo == null || all_buyerInfo  ==""){
			alert("买方信息为空,请检查！");
			
			return false;			
		
			
		}		
		return isSuccess;
	}
	  
	  
	  
	  function checkSubmit(operationType){
		
		  //提交表单
		  doSubmitForm(operationType);
	  }
	  
	  function doSubmitForm(operationType){
			var truthBeTold;
			switch (operationType)
			{
			case 'Save':
				truthBeTold = false;
				break;
			case 'ToPrint':
				truthBeTold = true;
				break;			
			case 'ToSellerQSZS':
				truthBeTold = sureTurn("goTo");
				break;	
			case 'ToFPDK':
				truthBeTold = sureTurn("goTo");
				break;
			case 'ToQSSB':
				truthBeTold = sureTurn("goTo");
				break;		
			case 'ToSGCJ':
				truthBeTold = sureTurn("goTo");
				break;					
			case 'Return':
					truthBeTold = sureTurn("return");;
				break;
			default:
				break;
			}
			if(operationType=="Save"){
				if(doCheckNotNullItems()){
					if(window.confirm("该操作将修改数据库中的数据,且不能自动恢复,请确认")){
						truthBeTold = true;
					}
				}
			}
			
			if(!truthBeTold){
				return false;
			}
			
			document.forms[0].target="";
			document.all.operationType.value=operationType;
			document.all.xxly.value="01";//通过二维码扫描采集
			document.forms[0].submit();
		}
	  
	  //确认跳转或者退出
	  	function sureTurn(type){
		  	var msg ="";
	  		var goToMsg ="跳转到其他页面";
	  		var returnMsg ="退出当前页面";
	  		
	  		if(type =="goTo"){
	  			msg = goToMsg;
	  		}else if (type == "return"){
	  			msg = returnMsg;
	  		}
	  		
	  		//如果有数据未保存则提示
			if(sm_success && saveIsSuccess!="1"){
				return window.confirm("已扫描的数据尚未保存，是否要"+msg+",请确认");
			}else{
				 return true;
			}
	  	}
	    </script>  
</HTML>
