<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@include file="../include/QRCodeHeader.jsp"%>
<%@page import="com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl.ClfxxcjForm"  %>
<HTML>
	<HEAD>
		<TITLE>��������Ϣ�ɼ�</TITLE>
	    <META content="text/html; charset=gb2312" http-equiv=Content-Type>
	    <SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
	    <SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
	    <script language=JavaScript src="../js/clfgl_util.js" type="text/javascript"></script>
	    <script language="javascript" src="../js/qscommon.js"></script>
	    <LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>   
	    

	</HEAD>
	<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0"  onLoad="initPage();" >
	    <jsp:include page="/include/Header.jsp" flush="true">
			<jsp:param name="subtitle" value="����������>��������Ϣ�ɼ�"/>
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
								�ɼ���ʽѡ�񣺶�ά��ɨ��<html:radio name="clfxxcjForm" property="cjfsdm" value="01"></html:radio> �ֹ��ɼ�<html:radio name="clfxxcjForm" property="cjfsdm" value="02" onclick="changeCjfs(this);"></html:radio>
							</div>
							
							������������ͬ��Ϣ��
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
										<td colspan="7" align="center" class=2-td1-center>һ�����׷�����Ϣ</td>
									</tr>
									<tr>
										<td class=2-td2-left align="left" width="20%">��ͬ���</td>
										<td class=2-td2-left width="30%"> <div align="left"><input type="text" id="htbhShow"></td></div>
										<td class=2-td2-left align="left" width="20%">��ͬ����ǩԼ����</td>
										<td class=2-td2-center colspan="4" width="30%"><div id="htwsqyrqShow" >&nbsp;</div></td>
									</tr>
									<tr>
										<td class=2-td2-left align="left">���׷��ݵ�ַ</td>
										<td colspan="6" class=2-td2-center><div id="fwzldzShow" >&nbsp;</div></td>
									</tr>
									<tr>
										<td class=2-td2-left align="left">����Ȩ��ת������</td>
										<td id="fwqszylxmcShow" class=2-td2-left>&nbsp;</td>
										<td class=2-td2-left align="left">���ν����Ƿ�Ϊ�״������ѹ�����</td>
										<td id="sfwscsssggfmcShow" class=2-td2-center colspan="4">&nbsp;</td>
									</tr>
									<tr>
										<td class=2-td2-left align="left">��������Ȩ֤��</td>
										<td id="fwcqzhShow" class=2-td2-left>&nbsp;</td>
										<td class=2-td2-left align="left">��������Ȩ֤�����</td>
										<td id="fwsyqztfrqShow" class=2-td2-center colspan="4">&nbsp;</td>
									</tr>
									<tr>
										<td class=2-td2-left align="left">���ݽ������</td>
										<td id="fwjzmjShow" class=2-td2-left>&nbsp;</td>
										<td class=2-td2-left align="left">�����ṹ</td>
										<td id="jzjgmcShow" class=2-td2-center colspan="4">&nbsp;</td>									
									</tr>	
									<tr>
										<td class=2-td2-left align="left">���ݻ�����;</td>
										<td id="ghytShow" class=2-td2-left>&nbsp;</td>
										<td rowspan="2" class=2-td2-left align="left">�ɽ��ܼ�</td>
										<td class=2-td2-left align="left" width="5%">�����</td>	
										<td class=2-td2-center id="rmbjeShow" colspan="3">&nbsp;</td>							
									</tr>
									<tr>
										<td class=2-td2-left align="left">����¥��/�ܲ���</td>
										<td id="szlcShow" class=2-td2-left>&nbsp;</td>
										<td class=2-td2-left align="left">���</td>
										<td class=2-td2-left id="wbjeShow" width="18%">&nbsp;</td>
										<td class=2-td2-left align="left" width="5%">����</td>	
										<td class=2-td2-center id="hlShow">&nbsp;</td>								
									</tr>																							
								</table>
								<br>
								<table  border=0 cellSpacing=0 class=tabled-99 width="100%" id="sellTab">
									<tr>
										<td colspan="6" class=2-td1-center>����������Ϣ</td>
									</tr>
<!-- 									<tr>
										<td class=2-td2-left  width="20%">���ƣ�������</td>
										<td id="sell_mcShow" class=2-td2-left width="30%">&nbsp;</td>
										<td class=2-td2-left width="20%">���</td>
										<td id="sell_lbShow" class=2-td2-center width="30%">&nbsp;</td>									
									</tr>
									<tr>
										<td class=2-td2-left>֤������</td>
										<td id="sell_zjlxShow" class=2-td2-left>&nbsp;</td>
										<td class=2-td2-left>֤������</td>
										<td id="sell_zjhmShow" class=2-td2-center>&nbsp;</td>									
									</tr>
									<tr>
										<td class=2-td2-left>Ȩ���˷ݶ�</td>
										<td id="sell_qlrfeShow" class=2-td2-left>&nbsp;</td>
										<td class=2-td2-left>��ϵ�˵绰</td>
										<td id="sell_lxdhShow" class=2-td2-center>&nbsp;</td>									
									</tr> -->																
								</table>
								<br>
								<table  border=0 cellSpacing=0 class=tabled-99 width="100%" id="buyTab">
									<tr>
										<td colspan="6" class=2-td1-center>��������Ϣ</td>
									</tr>								
<!-- 								    <tr>
										<td class=2-td2-left width="20%" >���ƣ�������</td>
										<td id="buy_mcShow" class=2-td2-left width="30%">&nbsp;</td>
										<td class=2-td2-left width="20%">���</td>
										<td id="buy_lbShow" class=2-td2-center width="30%">&nbsp;</td>									
									</tr>
									<tr>
										<td class=2-td2-left>֤������</td>
										<td id="buy_zjlxShow" class=2-td2-left>&nbsp;</td>
										<td class=2-td2-left>֤������</td>
										<td id="buy_zjhmShow" class=2-td2-center>&nbsp;</td>									
									</tr>
									<tr>
										<td class=2-td2-left>Ȩ���˷ݶ�</td>
										<td id="buy_qlrfeShow" class=2-td2-left>&nbsp;</td>
										<td class=2-td2-left>��ϵ�˵绰</td>
										<td id="buy_lxdhShow" class=2-td2-center>&nbsp;</td>									
									</tr> -->																
								</table>
								<br>
								<table  border=0 cellSpacing=0 class=tabled-99 width="100%">
								   	<tr>
										<td colspan="4" class=2-td1-center>�ġ����ز��н������Ϣ</td>
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
   										<IMG alt=��ӡ������Ʊ�����  height=22 id=dydkfpsqb name=dydkfpsqb
            								 onclick="doSubmitForm('ToPrint');" onmouseout=MM_swapImgRestore()
                                             onmouseover="MM_swapImage('dydkfpsqb','','<%=static_file%>images/dydkfpsqb2.jpg',1)"
                                             src="<%=static_file%>images/dydkfpsqb1.jpg" style="CURSOR: hand" width=150>                                  
                                    </logic:equal>
                                    
   										<IMG alt=ת����˰������  height=22 id=mfskzs name=mfskzs
            								 onclick="doSubmitForm('ToSellerQSZS');" onmouseout=MM_swapImgRestore()
                                             onmouseover="MM_swapImage('mfskzs','','<%=static_file%>images/mfskzs2.jpg',1)"
                                             src="<%=static_file%>images/mfskzs1.jpg" style="CURSOR: hand" width=130> 
                                             
   										<IMG alt=ת��Ʊ����  height=22 id=fpdk name=fpdk
            								 onclick="doSubmitForm('ToFPDK');" onmouseout=MM_swapImgRestore()
                                             onmouseover="MM_swapImage('fpdk','','<%=static_file%>images/fpdk2.jpg',1)"
                                             src="<%=static_file%>images/fpdk1.jpg" style="CURSOR: hand" width=150> 
                                             
                                        <IMG alt=ת��˰�걨  height=22 id=qssb name=qssb
            								 onclick="doSubmitForm('ToQSSB');" onmouseout=MM_swapImgRestore()
                                             onmouseover="MM_swapImage('qssb','','<%=static_file%>images/qssb2.jpg',1)"
                                             src="<%=static_file%>images/qssb1.jpg" style="CURSOR: hand" width=100> 
                                             
   										<IMG alt=�˳�  height=22 id=tuichu name=tuichu
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

	    //�˺����Ǳ������֮��������ʾ�ѱ�����Ϣ�ĺ���
	    function showSaveData(){
	    	isSaved = true;
			//��ά��ɨ������
			var res_UNEpiccode = '<bean:write name="clfxxcjForm" property="UNEpiccode"/>';
			putObjectValue(document.forms[0].UNEpiccode,"",res_UNEpiccode);
			
			
	    	var res_sbbh = '<bean:write name="clfxxcjForm" property="sbbh"/>';
	    	putObjectValue(document.forms[0].sbbh,'',res_sbbh);
	    	//��1�Σ�ͷ��־��
	    	var res_bbbs = '<bean:write name="clfxxcjForm" property="bbbs"/>';
	    	putObjectValue(document.forms[0].bbbs,"",res_bbbs);
	    	//��2�Σ���ͬ���
	    	var res_htbh = '<bean:write name="clfxxcjForm" property="htbh"/>';
	    	putObjectValue(document.forms[0].htbh,"",res_htbh);
	    	document.getElementById("htbhShow").value=res_htbh; 
 	    	//��3�Σ���ͬ����ǩԼ����
 	    	var res_htwsqyrq = '<bean:write name="clfxxcjForm" property="htwsqyrq"/>';
 	    	putObjectValue(document.forms[0].htwsqyrq,'',transTime("��ͬ����ǩԼ����",res_htwsqyrq,"yyyymmdd"));
	    	putObjectValue("",'htwsqyrqShow',transTime("��ͬ����ǩԼ����",res_htwsqyrq,"yyyy��mm��dd��"));
	    	//��4�Σ����׷��ݵ�ַ����
	    	var res_fwzlqx = '<bean:write name="clfxxcjForm" property="fwzlqx"/>';
	    	putObjectValue(document.forms[0].fwzlqx,'',res_fwzlqx);
	    	//��5�Σ����׷��ݵ�ַ
	    	var res_fwzldz = '<bean:write name="clfxxcjForm" property="fwzldz"/>';
	    	putObjectValue(document.forms[0].fwzldz,"",res_fwzldz);
	    	putObjectValue("","fwzldzShow","<div align=left>"+res_fwzldz+"</div>");
	    	//��6�Σ�����Ȩ��ת������
	    	var res_fwqszylxmc = "��������";
	    	putObjectValue(document.forms[0].fwqszylx,"",res_fwqszylxmc);
	    	putObjectValue(document.forms[0].fwqszylxmc,"fwqszylxmcShow",res_fwqszylxmc);
	    	
	    	//��7�Σ��Ƿ�Ϊ�״������ѹ�����
	    	var res_sfwscsssggf ='<bean:write name="clfxxcjForm" property="sfwscsssggf"/>';
	    	putObjectValue(document.forms[0].sfwscsssggf,"",res_sfwscsssggf);
	    	putObjectValue(document.forms[0].sfwscsssggfmc,"sfwscsssggfmcShow",getsfscssgf_mc(res_sfwscsssggf));	
	    	//��8�Σ����ݲ�Ȩ֤��
	    	var res_fwcqzh ='<bean:write name="clfxxcjForm" property="fwcqzh"/>';
	    	putObjectValue(document.forms[0].fwcqzh,"fwcqzhShow",res_fwcqzh);
	    	
	    	//��9�Σ���������Ȩ֤�����
	    	var res_fwsyqztfrq ='<bean:write name="clfxxcjForm" property="fwsyqztfrq"/>';
	    	putObjectValue(document.forms[0].fwsyqztfrq,"",transTime("��������Ȩ֤�����",res_fwsyqztfrq,"yyyymmdd"));
	    	putObjectValue("","fwsyqztfrqShow",transTime("��������Ȩ֤�����",res_fwsyqztfrq,"yyyy��mm��dd��"));		
	    	//��10�Σ����ݽ������
	    	var res_fwjzmj = '<bean:write name="clfxxcjForm" property="fwjzmj"/>';
	    	if(delFuhao(res_fwjzmj) != "0.00"){
	    		putObjectValue(document.forms[0].fwjzmj,"",delFuhao(res_fwjzmj));
	    		putObjectValue("","fwjzmjShow",delFuhao(res_fwjzmj)+"ƽ����");	
	    	}
	    	//��11�Σ������ṹ
	    	var res_jzjgdm = '<bean:write name="clfxxcjForm" property="jzjgdm"/>';
	    	putObjectValue(document.forms[0].jzjgdm,"",res_jzjgdm);	
	    	putObjectValue(document.forms[0].jzjgmc,"jzjgmcShow",getjzjg_mc(res_jzjgdm));	
	    	//��12�Σ��滮��;
	    	var res_ghyt = '<bean:write name="clfxxcjForm" property="ghyt"/>';
	    	putObjectValue(document.forms[0].ghyt,"ghytShow",res_ghyt);	
	    	//��13�Σ���ͬ�ܼ�
	    	//��ͬ�ܼۼ�Ϊ����ҽ�� 
	    	var res_htzj = '<bean:write name="clfxxcjForm" property="htzj"/>';
	    	if(delFuhao(res_htzj) != "0.00"){
	    		putObjectValue(document.forms[0].htzj,"",delFuhao(res_htzj));
	    		putObjectValue("","rmbjeShow",delFuhao(res_htzj)+"Ԫ");
	    	}
	    	//��14�Σ�����¥�㣬�ܲ���
	    	var res_szlc = '<bean:write name="clfxxcjForm" property="szlc"/>';
	    	putObjectValue(document.forms[0].szlc,"szlcShow",res_szlc);	
	    	//��15�Σ������
 	    	var res_bzdm ='<bean:write name="clfxxcjForm" property="bzdm"/>';
	    	putObjectValue(document.forms[0].bzdm,"",res_bzdm);	 
	    	//��16�Σ���ҽ��
	    	var res_wbje = '<bean:write name="clfxxcjForm" property="wbje"/>';
	    	putObjectValue(document.forms[0].wbje,"",delFuhao(res_wbje));
	    	putObjectValue("","wbjeShow",delFuhao(res_wbje)+"Ԫ");	
	    	//��17�Σ�����
	    	var res_hl ='<bean:write name="clfxxcjForm" property="hl"/>';
	    	putObjectValue(document.forms[0].hl,"hlShow",res_hl);	
/* 	    	//��18�Σ�������Ϣ
	    	var sellerArr = tmpObj.split("~");	
	    	if(tmpObj != null && tmpObj !=""){
	    		isSuccess = setBuyorSellInfo(tmpObj,"sellTab",document.forms[0].all_sellerInfo);
	    		
	    		if(!isSuccess){
	    			return false;
	    		}
	    	}
	    	
	    	//��19�Σ�����Ϣ
	    	index=19+0;
	    	tmpObj=transObjArray[index];
	    	if(tmpObj != null && tmpObj !=""){
	    		isSuccess = setBuyorSellInfo(tmpObj,"buyTab",document.forms[0].all_buyerInfo);
	    		if(!isSuccess){
	    			return false;
	    		}
	    		
	    		//ɨ��ɹ�
	    		sm_success = true;
	    	} */
	    	
/* 	    	//������Ϣ
	    	var sellerInfo = '<bean:write name="clfxxcjForm" property="all_sellerInfo"/>';
	    	parseSaveBuyorSellInfo(sellerInfo,"sellTab",document.forms[0].all_sellerInfo);
	    	alert(sellerInfo);
	    	
	    	//����Ϣ
	    	var buyyerInfo = '<bean:write name="clfxxcjForm" property="all_buyerInfo"/>';
	    	
			parseSaveBuyorSellInfo(buyyerInfo,"buyTab",document.forms[0].all_buyerInfo);
 */
 
 	    	

	    	
	    	var transObjArray=res_UNEpiccode.split("^");
	    	var index =0;
	    	//��18�Σ�������Ϣ
	    	index=18+0;
	    	tmpObj=transObjArray[index];
	    	var sellerArr = tmpObj.split("~");
	    	//alert("��18�Σ�������Ϣ::::"+sellerArr);	
	    	if(tmpObj != null && tmpObj !=""){
	    		isSuccess = setBuyorSellInfo(tmpObj,"sellTab",document.forms[0].all_sellerInfo);
	    		
	    		if(!isSuccess){
	    			return false;
	    		}
	    	}
	    	
	    	
	    	
	    	//��19�Σ�����Ϣ
	    	index=19+0;
	    	tmpObj=transObjArray[index];
	    	if(tmpObj != null && tmpObj !=""){
	    		isSuccess = setBuyorSellInfo(tmpObj,"buyTab",document.forms[0].all_buyerInfo);
	    		if(!isSuccess){
	    			return false;
	    		}
	    		
	    		//ɨ��ɹ�
	    		sm_success = true;
	    	}
	    	
	    	
	    	//��20�Σ����ز��н��������	
	    	var res_fdczjjgmc = '<bean:write name="clfxxcjForm" property="fdczjjgmc"/>';
	    	putObjectValue(document.forms[0].fdczjjgmc,"fdczjjgmcShow",res_fdczjjgmc); 
	    	
	    	
	    	//21 :�������ʴ���(�����ֶ�)
	    	var fwxzdm ='<bean:write name="clfxxcjForm" property="fwxzdm"/>';
	    	putObjectValue(document.forms[0].fwxzdm,"",fwxzdm);//�������ʴ���(�����ֶ�)
	    	
	    }
	    
	    
	    
	    var sm_success = false;//�����ɨ��
	    function initPage(){
	    	//alert("saveIsSuccess:::"+saveIsSuccess);
	    	//alert(saveIsSuccess != "0");
	    	//alert(saveIsSuccess != 0);
	    	//alert(saveIsSuccess != '0');
	    	
	    	//alert(saveIsSuccess == 0);
	    	ctrl_Buttons("BeforeScan");
	    	//�������ɹ����߱���ʧ��
		    if(saveIsSuccess != "0"){
		    	//alert("----------111111111");
		    	showSaveData();
		    }else{
		    	//alert("----------22222222222");
		    	//��Ҫ����ɨ��ʱ����λɨ��λ��
	    		document.all.htbhShow.focus();
		    }
	    }
	    
	  //ɨ��ǹɨ�����ݺ��Զ����������¼�
	    function document.onkeydown(){
		  //alert("hell0+++++++++++++");
	      //IE7.0���´������ǻس�
	      if(event.keyCode==13){
	    	  //alert("hell0++-------------");
	    	  if(sm_success == false ){
	        		scanPic();//������ά��
	        		
			      //��ʾ��λ��ť
			      if(sm_success){
			      	ctrl_Buttons("AfterScan");
			      }
	    	  }
	        return false;
	      }
	      
	      if(event.keyCode==8){
	    	  //Ҫ��û��ɨ��ɹ�����������˸��ɾ����������Ϣ
	    	  if(sm_success == false ){
	    		  return true;
	    	  }
	    	  //����������
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
			  //��ʾ��λͼ��
			  //document.all.saveOrUndo.style.display = "none"; 
			  
			 //ShowBeforeScan
				display_td_ShowAfterScan();

			 
			  if(saveIsSuccess != "0"){
				  display_td_ShowBeforeScan();
			  }
			  return false;
		  }
	  }
	  
	  //������ť
	  
	  var public_button_html="";
 	  //var ToSellerQSZS_button_html ="<IMG alt=ת����˰������  height=22 id=mfskzs1 name=mfskzs1 onclick=\"doSubmitForm('ToSellerQSZS')\" onmouseout=MM_swapImgRestore() onmouseover=\"MM_swapImage('mfskzs1','','<%=static_file%>images/mfskzs2.jpg',1)\" src=\"<%=static_file%>images/mfskzs1.jpg\" style='CURSOR: hand' width=130>";
	 //var ToFPDK_button_html="<IMG alt=ת��Ʊ����  height=22 id=fpdk1 name=fpdk1 onclick=\"doSubmitForm('ToFPDK')\" onmouseout=MM_swapImgRestore() onmouseover=\"MM_swapImage('fpdk1','','<%=static_file%>images/fpdk2.jpg',1)\" src=\"<%=static_file%>images/fpdk1.jpg\" style='CURSOR: hand' width=100>";
      //var ToQSSB_button_html="<IMG alt=ת��˰�걨  height=22 id=qssb1 name=qssb1 onclick=\"doSubmitForm('ToQSSB')\" onmouseout=MM_swapImgRestore() onmouseover=\"MM_swapImage('qssb1','','<%=static_file%>images/qssb2.jpg',1)\" src=\"<%=static_file%>images/qssb1.jpg\" style='CURSOR: hand' width=100>";
      var tuichu_button_html="<IMG alt=�˳�  height=22 id=tuichu1 name=tuichu1 onclick=\"doSubmitForm('Return')\" onmouseout=MM_swapImgRestore() onmouseover=\"MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)\" src=\"<%=static_file%>images/tuichu1.jpg\" style='CURSOR: hand' width=79>";
			
      // public_button_html = ToSellerQSZS_button_html +"\n"+ ToFPDK_button_html +"\n"+ ToQSSB_button_html +"\n"+ tuichu_button_html;
       //public_button_html = ToSellerQSZS_button_html +"\n"+ ToQSSB_button_html +"\n"+ tuichu_button_html; 
       public_button_html = tuichu_button_html; 
		//���水ť����Ҫ��ɨ��ɹ�֮��ͱ���ʧ��ʱ��ʾ��ҳ���ʼ��ʱҲ����ʾ
		
 	  var baocun_botton_html="<IMG alt=���� height=22 id=baocun name=baocun onclick=\"doSubmitForm('Save')\" onmouseout=MM_swapImgRestore() onmouseover=\"MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)\" src=\"<%=static_file%>images/baocun1.jpg\" style='CURSOR: hand' width=79>";
             
         //��λ��ť������ɨ�費�ɹ��⣬�����������ʾ    ��ҳ���ʼ��ʱҲ����ʾ
      var fuwei_botton_html="<IMG alt=���ɨ����Ϣ������ɨ�� height=22 id='fuwei' name='fuwei' onclick=\"qkxx()\" onmouseout=MM_swapImgRestore() onmouseover=\"MM_swapImage('fuwei','','<%=static_file%>images/fuwei2.jpg',1)\" src=\"<%=static_file%>images/fuwei1.jpg\" style='CURSOR: hand' width=79>";
			
		//ת��ӡ������Ʊ����� ֻ���Ǳ���ɹ�֮�����ʾ
	  //var ToPrint_button_html="<IMG alt=��ӡ������Ʊ�����  height=22 id=dydkfpsqb name=dydkfpsqb  onclick=\"doSubmitForm('ToPrint')\" onmouseout=MM_swapImgRestore() onmouseover=\"MM_swapImage('dydkfpsqb','','<%=static_file%>images/dydkfpsqb2.jpg',1)\" src=\"<%=static_file%>images/dydkfpsqb1.jpg\" style='CURSOR: hand' width=150>";
	    //ת�˶���Ϣҳ��
	  var ToPrint_button_html="<IMG alt=��һ��  height=22 id=xiayibu name=xiayibu  onclick=\"doSubmitForm('ToPrint')\" onmouseout=MM_swapImgRestore() onmouseover=\"MM_swapImage('xiayibu','','<%=static_file%>images/xiayibu2.jpg',1)\" src=\"<%=static_file%>images/xiayibu1.jpg\" style='CURSOR: hand' width=79>";
				
	  
	  function display_td_ShowBeforeScan(){
/* 		  
		  document.all.ShowBeforeScan.style.display = "none"; 
		  document.all.ShowAfterScan.style.display = "";  */
		 // document.all.htbhShow.readOnly=false;
		 // document.all.htbhShow.disabled=false;
		 set_htbhShow_readOnly_disabled(false);
		  //��ʾ��ť
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
		  //��ʾ��ť
		  var showAfterScan_button_html ="";
		  
		  //ɨ��ɹ�����ʾ��λ��ť
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
		  
		  //��ʾ��ť
		  var showAfterScan_button_html ="";
		  
		  
		  if(saveIsSuccess != "1"){
			  if(saveIsSuccess =="error"){
			   	showAfterScan_button_html = baocun_botton_html +"\n"+ fuwei_botton_html; 
			  }else{
				  //ɨ��ɹ���ʾ��λ��ť
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
	  
	  
	  //���ö�̬�⣬ʶ��ͼƬ����ʶ�������������ʾ��ҳ����
	    function scanPic(){
	      //alert(">>>ɨ����Ͽ�ʼ������");
	      var istr=document.forms[0].htbhShow.value;
	      var obj=new ActiveXObject("hyQRBarCode.QRCode");
	    	//alert("obj="+obj);
	    	var transObj=obj.DeBarCodeString(istr);
	    	//transObj="^object_000002_000002^C533761^2011-12-22^7^����������������14��¥1��103^00000002^0^X����Ȩ֤���ֵ�178966��^2010-06-26^53.60^2^סլ^1000000^1/21^^^^������~1~1~371082197905142128~20%~13439509057~��1~1~1~371082197905142128~20%~13439509057~��3~1~1~371082197905142128~60%~13439509057^�~1~1~420281198209250435~50%~13141388733~��1~1~1~420281198209250435~30%~13141388733~��2~1~1~420281198209250435~10%~13141388733~��3~1~1~420281198209250435~10%~13141388733^�������ҷ��ز��������޹�˾";
	    	//alert("transObj="+transObj);
	    	if(transObj == null || transObj ==""){
	    		alert("��ɨ����Ϣ���޷�������������ɨ�裡");
	    		return false;
	    	}
	    	
	    	//�ж��Ƿ��ҵ��������
	    	var transObjArray=transObj.split("^");
	    	
<%-- 	    	if(transObjArray.length==1&&transObj=="<%=com.creationstar.bjtax.qsgl.util.Constants.QRCODE_DEFAULT_NULL%>"){
	    		alert("ɨ������������Ϊ�գ����ֹ�¼���걨��");
	    		return false;
	    	} 
--%>

	    	//����ά�������Ƿ���ȷ��
	    	//alert("��ʼ����ά��ͷ���ݵĸ�ʽ");
	    	QRCodeCheckResult=checkQRCodeHeader(transObj);
	    	//alert("QRCodeCheckResult="+QRCodeCheckResult);
	    	if(QRCodeCheckResult=="1"){
	    		//alert("11111111111+++++++++++"+transObj);
	    		alert("ɨ��ͼƬʧ�ܣ�������ɨ����ֹ�¼����Ϣ��");
 /* 	            fuwei();
	    		QRInputFocus(); */ 
	    		return false;
	    	}else if(QRCodeCheckResult=="2"){//������Ĳ�Ϊ"object_"��ͷ���ʾ����ͼƬɨ�費ȫ����ʾ����ɨ��
	    		//alert("22222222222222222+++++++++++"+transObj);
	    		alert("��ɨ��ȫ����ά������ͼƬ�������޷����룡");
/* 	    		fuwei();
	    		QRInputFocus(); */
	    		return false;
	    	}else if(QRCodeCheckResult=="3"){
	    		//alert("333333333+++++++++++"+transObj);
 	          translate(transObj);//�·�
/*	          translate_xf(transObj);//����Ϊ�·�
	          document.forms[0].smbs.value='1'; */
	    	}else if(QRCodeCheckResult=="4"){
	            //alert("44444444444+++++++++++"+transObj);
 	    		translate(transObj);//2�ַ�
 	    		/*	            translate_esf(transObj);//����Ϊ���ַ�
	            document.forms[0].smbs.value='1'; */
	    	}
	    	//���¼���������ý���
	    	//document.all.htbh.value="";
	    	document.all.htbhShow.focus();
	    	
	    	//
	    	//���ñ�־λ
/* 	    	document.forms[0].qrScanSbFlag.value="1"; */
	    	return false;
	    }

	    function translate(translateObj){	
	    	//�����Ϣ
	    	undo();
	    	
	    	var isSuccess = false;
	    	var transObjArray=translateObj.split("^");
	    	putObjectValue(document.forms[0].UNEpiccode,"",translateObj);
	    	//��1�Σ�ͷ��־������������
	    	index=0;
	    	tmpObj=transObjArray[index];
	    	
	    	//alert("��0�Σ�::::"+tmpObj);		
	    	//��1�Σ�ͷ��־������������
	    	index=1+0;
	    	tmpObj=transObjArray[index];
	    	putObjectValue(document.forms[0].bbbs,"",tmpObj);
	    	//alert("��1�Σ�ͷ��־��::::"+tmpObj);	
	    	//��2�Σ���ͬ���
	    	index=2+0;
	    	tmpObj=transObjArray[index];
	    	putObjectValue(document.forms[0].htbh,"",tmpObj);
	    	document.getElementById("htbhShow").value=tmpObj; 
	    	//alert("��2�Σ���ͬ���::::"+tmpObj);
	    	//��3�Σ���ͬ����ǩԼ����
	    	index=3+0;
	    	tmpObj=transObjArray[index];
	    	tmpObj=formatDateStr(tmpObj);
	    	putObjectValue(document.forms[0].htwsqyrq,'',transTime("��ͬ����ǩԼ����",tmpObj,"yyyymmdd"));
	    	putObjectValue("",'htwsqyrqShow',transTime("��ͬ����ǩԼ����",tmpObj,"yyyy��mm��dd��"));
	    	//alert("��3�Σ���ͬ����ǩԼ����::::"+tmpObj);
	    	//��4�Σ����׷��ݵ�ַ���أ���������
	    	index=4+0;
	    	tmpObj=transObjArray[index];
	    	tmpObj=formatDateStr(tmpObj);
	    	putObjectValue(document.forms[0].fwzlqx,'',tmpObj);
	    	//alert("��4�Σ����׷��ݵ�ַ����::::"+tmpObj);
	    	//��5�Σ����׷��ݵ�ַ
	    	index=5+0;
	    	tmpObj=transObjArray[index];
	    	putObjectValue(document.forms[0].fwzldz,"",tmpObj);
	    	putObjectValue("","fwzldzShow","<div align=left>"+tmpObj+"</div>");
	    	//alert("��5�Σ����׷��ݵ�ַ::::"+tmpObj);
	    	//��6�Σ�����Ȩ��ת������
	    	index=6+0;
	    	tmpObj=transObjArray[index];
	    	putObjectValue(document.forms[0].fwxzdm,"",tmpObj);//�������ʴ���(�����ֶ�)
	    	//alert("��6�Σ�����Ȩ��ת������::::"+tmpObj);
	    	tmpObj="03";
	    	putObjectValue(document.forms[0].fwqszylx,"",tmpObj);//����Ȩ��ת�����͹̶�Ϊ03
	    	putObjectValue(document.forms[0].fwqszylxmc,"fwqszylxmcShow","��������");
	    	
	    	
	    /* 	tmpObj="03";
	    	tmpObj_page=document.forms[0].tdfwqszylx;
	    	for(i=0;i<tmpObj_page.options.length;i++){
	    		if(tmpObj_page.options.value=tmpObj){
	    			tmpObj_page.style.color="#ADADAD";
	    			tmpObj_page.selectedIndex=i;
	    			tmpObj_page.value=tmpObj;
	    			tmpObj_page.onclick=function qr_readonly_tdfwqszylx(){alert("�����޸ģ�");return false;};//��λֻ��
	    			checkSelect();
	    		}
	    	} */
	    	//��7�Σ��Ƿ�Ϊ�״������ѹ�����
	    	index=7+0;
	    	tmpObj=transObjArray[index];
	    	//TODO
	    	putObjectValue(document.forms[0].sfwscsssggf,"",tmpObj);
	    	putObjectValue(document.forms[0].sfwscsssggfmc,"sfwscsssggfmcShow",getsfscssgf_mc(tmpObj));
	    	//alert("��7�Σ��Ƿ�Ϊ�״������ѹ�����::::"+tmpObj);	
	    	//��8�Σ����ݲ�Ȩ֤��
	    	index=8+0;
	    	tmpObj=transObjArray[index];
	    	putObjectValue(document.forms[0].fwcqzh,"fwcqzhShow",tmpObj);
	    	//alert("��8�Σ����ݲ�Ȩ֤��::::"+tmpObj);	
	    	//��9�Σ���������Ȩ֤�����
	    	index=9+0;
	    	tmpObj=transObjArray[index];
	    	//alert("��������Ȩ֤�����111"+tmpObj);
	    	putObjectValue(document.forms[0].fwsyqztfrq,"",transTime("��������Ȩ֤�����",tmpObj,"yyyymmdd"));
	    	putObjectValue("","fwsyqztfrqShow",transTime("��������Ȩ֤�����",tmpObj,"yyyy��mm��dd��"));
	    	//alert("��9�Σ���������Ȩ֤�����::::"+tmpObj);		
	    	//��10�Σ����ݽ������
	    	index=10+0;
	    	tmpObj=transObjArray[index];
	    	putObjectValue(document.forms[0].fwjzmj,"",delFuhao(tmpObj));
	    	putObjectValue("","fwjzmjShow",delFuhao(tmpObj)+"ƽ����");
	    	//alert("��10�Σ����ݽ������::::"+tmpObj);	
	    	//��11�Σ������ṹ
	    	index=11+0;
	    	tmpObj=transObjArray[index];
	    	//TODO
	    	putObjectValue(document.forms[0].jzjgdm,"",tmpObj);
	    	putObjectValue(document.forms[0].jzjgmc,"jzjgmcShow",getjzjg_mc(tmpObj));
	    	//alert("��11�Σ������ṹ::::"+tmpObj);		
	    	//��12�Σ��滮��;
	    	index=12+0;
	    	tmpObj=transObjArray[index];
	    	putObjectValue(document.forms[0].ghyt,"ghytShow",tmpObj);
	    	//alert("��12�Σ��滮��;::::"+tmpObj);		
	    	//��13�Σ���ͬ�ܼ�
	    	index=13+0;
	    	tmpObj=transObjArray[index];
	    	//��ͬ�ܼۼ�Ϊ����ҽ�� 
	    	putObjectValue(document.forms[0].htzj,"",delFuhao(tmpObj));
	    	putObjectValue("","rmbjeShow",delFuhao(tmpObj)+"Ԫ");
	    	//alert("��13�Σ���ͬ�ܼ�::::"+tmpObj);	
	    	//��14�Σ�����¥�㣬�ܲ���
	    	index=14+0;
	    	tmpObj=transObjArray[index];
	    	putObjectValue(document.forms[0].szlc,"szlcShow",tmpObj);
	    	//alert("��14�Σ�����¥�㣬�ܲ���::::"+tmpObj);		
	    	//��15�Σ������
	    	index=15+0;
	    	tmpObj=transObjArray[index];
	    	putObjectValue(document.forms[0].bzdm,"",tmpObj);
	    	//alert("��15�Σ������::::"+tmpObj);		
	    	//��16�Σ���ҽ��
	    	index=16+0;
	    	tmpObj=transObjArray[index];
	    	putObjectValue(document.forms[0].wbje,"wbjeShow",delFuhao(tmpObj));
	    	putObjectValue("","wbjeShow",delFuhao(tmpObj)+"Ԫ");
	    	//alert("��16�Σ���ҽ��::::"+tmpObj);		
	    	//��17�Σ�����
	    	index=17+0;
	    	tmpObj=transObjArray[index];
	    	putObjectValue(document.forms[0].hl,"hlShow",tmpObj);
	    	//alert("��17�Σ�����::::"+tmpObj);	
	    	//��18�Σ�������Ϣ
	    	index=18+0;
	    	tmpObj=transObjArray[index];
	    	var sellerArr = tmpObj.split("~");
	    	//alert("��18�Σ�������Ϣ::::"+sellerArr);	
	    	if(tmpObj != null && tmpObj !=""){
	    		isSuccess = setBuyorSellInfo(tmpObj,"sellTab",document.forms[0].all_sellerInfo);
	    		
	    		if(!isSuccess){
	    			return false;
	    		}
	    	}
	    	
/* 	    	//@@���ƣ�������
	    	putObjectValue(document.forms[0].sell_mc,"sell_mcShow",sellerArr[0]);
	    	//@@���
	    	putObjectValue(document.forms[0].sell_lb,"",sellerArr[1]);
	    	putObjectValue(document.forms[0].sell_lb_mc,"sell_lbShow","�������TODO");
	    	//@@֤������
	    	putObjectValue(document.forms[0].sell_zjlx,"",sellerArr[2]);
	    	putObjectValue(document.forms[0].sell_zjlx_mc,"sell_zjlxShow","֤����������TODO");	    	
	    	//@@֤������
	    	putObjectValue(document.forms[0].sell_zjhm,"sell_zjhmShow",sellerArr[3]);
	    	//@@Ȩ���˷ݶ�
	    	putObjectValue(document.forms[0].sell_qlrfe,"sell_qlrfeShow",sellerArr[4]);
	    	//@@��ϵ�˵绰
	    	putObjectValue(document.forms[0].sell_lxdh,"sell_lxdhShow",sellerArr[5]); */
	    	
	    	
	    	
	    	//��19�Σ�����Ϣ
	    	index=19+0;
	    	tmpObj=transObjArray[index];
	    	if(tmpObj != null && tmpObj !=""){
	    		isSuccess = setBuyorSellInfo(tmpObj,"buyTab",document.forms[0].all_buyerInfo);
	    		if(!isSuccess){
	    			return false;
	    		}
	    		
	    		//ɨ��ɹ�
	    		sm_success = true;
	    	}
	    	
/* 	    	var mfxxArr = tmpObj.split("~");
	    	alert("��19�Σ�����Ϣ::::"+mfxxArr);	
	    	alert("��19�Σ�����Ϣ::::tmpObj "+mfxxArr);
	    	//@@���ƣ�������
	    	putObjectValue(document.forms[0].buy_mc,"buy_mcShow",mfxxArr[0]);
	    	//@@���
	    	putObjectValue(document.forms[0].buy_lb,"",mfxxArr[1]);
	    	putObjectValue(document.forms[0].buy_lb_mc,"buy_lbShow","�������TODO");
	    	//@@֤������
	    	putObjectValue(document.forms[0].buy_zjlx,"",mfxxArr[2]);
	    	putObjectValue(document.forms[0].buy_zjlx_mc,"buy_zjlxShow","֤����������TODO");	    	
	    	//@@֤������
	    	putObjectValue(document.forms[0].buy_zjhm,"buy_zjhmShow",mfxxArr[3]);
	    	//@@Ȩ���˷ݶ�
	    	putObjectValue(document.forms[0].buy_qlrfe,"buy_qlrfeShow",mfxxArr[4]);
	    	//@@��ϵ�˵绰
	    	putObjectValue(document.forms[0].buy_lxdh,"buy_lxdhShow",mfxxArr[5]);	 */    	
	    	
	    	//@
	    /*   	if(mfxx[1] == "2"){
	      		//��˰������(2009.2.9�޸�Ϊ����˰���������뵽������----yangxiao)
	            document.forms[0].smnsrmc.value=mfxx[0];

	      		for(i=0;i<document.getElementsByName("nsrmc");i++){
	      			document.getElementsByName("nsrmc")[i].value=mfxx[0];
	      		}
	      		var ele_nsrmc_show=document.getElementById('nsrmc_show');
	      		new_text=document.createTextNode(mfxx[0]);
	      		ele_nsrmc_show.appendChild(new_text);
	      		//֤������(2009.2.9�޸�Ϊ��д�룬��Ҫ�ֶ�����----yangxiao)
	      		//document.forms[0].jsjdm.value=mfxx[3];
	      		//��ϵ�ˣ�û�У�

	      		//��ϵ�˵绰
	      		document.forms[0].lxdh.value=mfxx[5]; */
	      		
	      		
	      		
	      		
	      		

	    	//��20�Σ����ز��н��������	
	    index=20+0;
	    tmpObj=transObjArray[index];
	    putObjectValue(document.forms[0].fdczjjgmc,"fdczjjgmcShow",tmpObj);
	    //alert("��20�Σ����ز��н��������::::"+tmpObj);		
	    }
	    
	  //��ֵ����
	    function putObjectValue(hiddProperty,td_id,obj_value){
	    	if(hiddProperty != null && hiddProperty != ""){
	    		hiddProperty.value=obj_value;
	    	}
	    	if(td_id != "" && obj_value !=""){
		    		document.getElementById(td_id).innerHTML=obj_value; 
	    	}
	    	return true;
	    }
	  
	  //�����������֮�󷵻�ʱ��ֵ
	  function parseSaveBuyorSellInfo(buyorSellInfo,tableId,hidPropertyObj){
		  
		  //alert("-----------0000000");
		  if(buyorSellInfo != null && buyorSellInfo != ""){
			  //alert("-----------1111");
			  // alert("infoArr ���ȣ���"+buyorSellInfo);
			  if(buyorSellInfo.indexOf("^")>0){
				  var infoArr = buyorSellInfo.split("^");
				  //alert("infoArr ���ȣ���"+infoArr.length);
				  
				  for(var index =0; index < infoArr.length;index ++){
					  var tempInfo = infoArr[index];
					 // alert("-----------ÿ���˵���ϢtempInfo----"+tempInfo);
					  //���ý�������������ÿ������������Ϣ����ʾ
					  setBuyorSellInfo(tempInfo,tableId,hidPropertyObj);
					  //alert("-----------22222222222");
				  }
			  }else{
				  setBuyorSellInfo(buyorSellInfo,tableId,hidPropertyObj);
			  }
		  }
		  
		  
	  }
	  
	  //��������˫����Ϣ
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
				//alert("��Ϣ����"+count);
				
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
						oneGroupInfo.push(DSzjdm);//֤�����ʹ���
						oneGroupInfo.push(value_3);
						oneGroupInfo.push(value_4);
						oneGroupInfo.push(value_5);
						
						//alert("RETURN::"+DSzjdm);
						
						//���ò���ʾÿ����Ϣ
						isSuccess = setOneGroupInfo(oneGroupInfo,tableobj);
						
						if(!isSuccess){
							alert("��ʾ��������Ϣ����");
							return isSuccess;
						}
						
						//ƴ���ύ����̨����Ϣ
						var tempOneGroupValue = value_0 +"~"+value_1+"~"+DSzjdm+"~"+value_3+"~"+value_4+"~"+value_5;
						if(zsIndex == 0){
							submitInfo = tempOneGroupValue;
						}else{
							submitInfo = submitInfo+"^"+tempOneGroupValue;
						}
						//������Ϣ
						oneGroupInfo = new Array();
					}
					//�����ύ�������ֵ
					if(hidPropertyObj != null && hidPropertyObj !=""){
						hidPropertyObj.value = submitInfo;
					}
				}
			}
			
			return true;
	  }
	  
	  //��ί֤�����ʹ���ת��˰֤�����ʹ���
	  function getZjlxJwToDs(dm){
		  var res = zjlxJwToDs(dm);// this function in ../js/qscommon.js
		  
		  //���û�з���ת��������������´���
		  if (res == null || res == ""){
			  res ="90";//����
		  }
		  
		  return res;
	  }
	  
	  
	  
	  //��ÿ����Ϣ���õ�td��innerHTML����ʾ
	  function setOneGroupInfo1(arr,tableobj){
		  
	  			if(tableobj == null || tableobj == ""){
	  				return false;
	  			}
	    		var otr = tableobj.insertRow();
	    		//��һ�е�һ��  �����ƣ���������
	    		var mc_Cell=otr.insertCell();
	    		mc_Cell.innerHTML="���ƣ�������";
	    		mc_Cell.className ="2-td2-left";
	    		mc_Cell.WIDTH="20%";
	    		//��һ�еڶ��� 
	    		var mc_value_Cell=otr.insertCell();
	    		mc_value_Cell.innerHTML=arr[0];
	    		mc_value_Cell.className ="2-td2-left";
	    		mc_value_Cell.WIDTH="30%";
	    		//��һ�е�����  �����
	    		var lb_Cell=otr.insertCell();
	    		lb_Cell.innerHTML="���";
	    		lb_Cell.className ="2-td2-left";
	    		lb_Cell.WIDTH="20%";
	    		//��һ�е�����  
	    		var lb_value_Cell=otr.insertCell();
	    		lb_value_Cell.innerHTML=nsrlb_mc(arr[1]);
	    		lb_value_Cell.className ="2-td2-center";
	    		lb_value_Cell.WIDTH="30%";
	    		
	    		
	    		//�ڶ��е�һ�� ��֤�����͡�
	    		var otr2 = tableobj.insertRow();
	    		var zjlx_Cell=otr2.insertCell();
	    		zjlx_Cell.innerHTML="֤������";
	    		zjlx_Cell.className ="2-td2-left";
	    		zjlx_Cell.WIDTH="20%";	    		
	    		//�ڶ��еڶ���
	    		var zjlx_value_Cell=otr2.insertCell();
	    		zjlx_value_Cell.innerHTML=nsrzjlb_mc(arr[2]);
	    		zjlx_value_Cell.className ="2-td2-left";
	    		zjlx_value_Cell.WIDTH="30%";	    		
	    		//�ڶ��е�һ�� ��֤�����롱
	    		var zjhm_Cell=otr2.insertCell();
	    		zjhm_Cell.innerHTML="֤������";
	    		zjhm_Cell.className ="2-td2-left";
	    		zjhm_Cell.WIDTH="20%";		    		
	    		//�ڶ��е�һ�� 
	    		var zjhm_value_Cell=otr2.insertCell();
	    		zjhm_value_Cell.innerHTML=arr[3];
	    		zjhm_value_Cell.className ="2-td2-center";
	    		zjhm_value_Cell.WIDTH="30%";
	    		
	    		
	    		//�����е�һ�� ��Ȩ���˷ݶ
	    		var otr3 = tableobj.insertRow();
	    		var fe_Cell=otr3.insertCell();
	    		fe_Cell.innerHTML="Ȩ���˷ݶ�";
	    		fe_Cell.className ="2-td2-left";
	    		fe_Cell.WIDTH="20%";
	    		//�����еڶ��� 
	    		var fe_value_Cell=otr3.insertCell();
	    		fe_value_Cell.innerHTML=arr[4];
	    		fe_value_Cell.className ="2-td2-left";
	    		fe_value_Cell.WIDTH="30%";	    		
	    		//�����е����� ����ϵ�˵绰��
	    		var lxdh_Cell=otr3.insertCell();
	    		lxdh_Cell.innerHTML="��ϵ�˵绰";
	    		lxdh_Cell.className ="2-td2-left";
	    		lxdh_Cell.WIDTH="20%";	    		
	    		//�����е����� 
	    		var lxdh_value_Cell=otr3.insertCell();
	    		lxdh_value_Cell.innerHTML=arr[5];
	    		lxdh_value_Cell.className ="2-td2-center";
	    		lxdh_value_Cell.WIDTH="30%";	
	    		
	    		return true;
	  }
	  
	  //��ÿ����Ϣ���õ�td��innerHTML����ʾ
	  function setOneGroupInfo(arr,tableobj){
		  
	  			if(tableobj == null || tableobj == ""){
	  				return false;
	  			}
	  			var tabLength = tableobj.rows.length;
	  			
	  			//��ʼ����ͷ
	  			if(tabLength == 1){
		    		var otr = tableobj.insertRow();
		    		//��һ�е�һ��  �����ƣ���������
		    		var mc_Cell=otr.insertCell();
		    		mc_Cell.innerHTML="���ƣ�������";
		    		mc_Cell.className ="2-td2-left";
		    		mc_Cell.WIDTH="30%";
		    		
		    		//��һ�еڶ���  �����
		    		var lb_Cell=otr.insertCell();
		    		lb_Cell.innerHTML="���";
		    		lb_Cell.className ="2-td2-left";
		    		lb_Cell.WIDTH="10%";
		    		
		    		//��һ�е����� ��֤�����͡�
		    		var zjlx_Cell=otr.insertCell();
		    		zjlx_Cell.innerHTML="֤������";
		    		zjlx_Cell.className ="2-td2-left";
		    		zjlx_Cell.WIDTH="10%";	
		    		
		    		//��һ�е����� ��֤�����롱
		    		var zjhm_Cell=otr.insertCell();
		    		zjhm_Cell.innerHTML="֤������";
		    		zjhm_Cell.className ="2-td2-left";
		    		zjhm_Cell.WIDTH="20%";	
		    		
		    		//��һ�е����� ��Ȩ���˷ݶ
		    		var fe_Cell=otr.insertCell();
		    		fe_Cell.innerHTML="Ȩ���˷ݶ�";
		    		fe_Cell.className ="2-td2-left";
		    		fe_Cell.WIDTH="10%";	
		    		
		    		//��һ�е����� ����ϵ�˵绰��
		    		var lxdh_Cell=otr.insertCell();
		    		lxdh_Cell.innerHTML="��ϵ�˵绰";
		    		lxdh_Cell.className ="2-td2-center";
		    		lxdh_Cell.WIDTH="20%";
	  			}
	  			
	    		//��n(n>=2)�е�һ�� 
	    		var otr_n = tableobj.insertRow();
	    		var mc_value_Cell=otr_n.insertCell();
	    		mc_value_Cell.innerHTML=arr[0];
	    		mc_value_Cell.className ="2-td2-left";
	    		//mc_value_Cell.WIDTH="30%";

	    		//��n(n>=2)�еڶ���  
	    		var lb_value_Cell=otr_n.insertCell();
	    		lb_value_Cell.innerHTML=nsrlb_mc(arr[1]);
	    		lb_value_Cell.className ="2-td2-left";
	    		//lb_value_Cell.WIDTH="30%";
	    		
	    		
    		
	    		//��n(n>=2)�е�����
	    		var zjlx_value_Cell=otr_n.insertCell();
	    		//zjlx_value_Cell.innerHTML=nsrzjlb_mc(arr[2]); 
	    		//zjlx_value_Cell.innerHTML=getZjlxmc(arr[2]);
	    		zjlx_value_Cell.innerHTML=getZjmc(arr[2]);
	    		zjlx_value_Cell.className ="2-td2-left";
	    		//zjlx_value_Cell.WIDTH="30%";
	    		
	    		
	    		//��n(n>=2)�е����� 
	    		var zjhm_value_Cell=otr_n.insertCell();
	    		zjhm_value_Cell.innerHTML=arr[3];
	    		zjhm_value_Cell.className ="2-td2-left";
	    		//zjhm_value_Cell.WIDTH="30%";
	    		
	    		

	    		//��n(n>=2)�е����� 
	    		var fe_value_Cell=otr_n.insertCell();
	    		fe_value_Cell.innerHTML=arr[4];
	    		fe_value_Cell.className ="2-td2-left";
	    		//fe_value_Cell.WIDTH="30%";	    		
	    		
	    		//��n(n>=2)�е����� 
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
		
		return "����";
	} 
	
	//�����Ϣ
	function qkxx(){
		if(window.confirm("�ò����������ɨ����Ϣ,��ȷ��")){
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
		//alert("ҳ����saveIsSuccess ��ֵΪ:::"+saveIsSuccess);
		//var ss = '<bean:write name="clfxxcjForm" property="saveIsSuccess"/>';
		//alert("������saveIsSuccess ��ֵΪ:::"+ss);
		
		
		sm_success = false;
		
		//alert("����������ʾ��Ϣ");
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
		//ɾ����������Ϣ
		
		//ɾ��������Ϣ���������
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
		
		//transTime("ʱ��1","20130101");
		//transTime("ʱ��2","2013-01-01");
		//transTime("ʱ��3","2013-01-01  22");
		
		ctrl_Buttons("BeforeScan");
		document.getElementById("htbhShow").focus();
	}
	
	//������������ʾ��Ϣ����ɾ����0�б��⣬��ɾ��ʱ��1��ʼ������ɾ��һ��֮��table���л��Զ������ƣ�����i�����������Լ���������forѭ���е���������Ϊ��
	function resetTab(tabId){
		var tableobj=document.getElementById(tabId);
		if(tableobj.rows.length != 1 && tableobj.rows.length >=2){
		     var rowNum=tableobj.rows.length;
		     for (i=1;i<rowNum;)
		     {
		    	 tableobj.deleteRow(i);
		    	 //ɾ��֮��ѭ��������1
		         rowNum=rowNum-1;
		     }

		}		
		
		
	}	
	
	/**
		У�����ݿ������
	*/
	function doCheckNotNullItems(){
		
		var isSuccess = true;
		//��ͬ���
		var htbh = document.forms[0].htbh.value;
		if(htbh ==null || htbh==""){
			alert("��ͬ���Ϊ�գ����飡");
			return false;
		}
		
		//���������ַ
		var fwzldz = document.forms[0].fwzldz.value;
		if(fwzldz == null || fwzldz == ""){
			alert("���������ַΪ��,���飡");
			return false;
		}
			
		//���ݽ������
		var fwjzmj = document.forms[0].fwjzmj.value;
		if(fwjzmj == null || fwjzmj ==""){
			
			alert("���ݽ������Ϊ��,���飡");
			return false;		
		}
		//�ܲ���
		 
		//����¥��
		var szlc = document.forms[0].szlc.value;
		if(szlc == null || szlc ==""){
			alert("����¥��Ϊ��,���飡");
		
		return false;
		
		}
		
		//��ͬ����ǩԼ����
		var  htwsqyrq = document.forms[0].htwsqyrq.value;
		if(htwsqyrq == null || htwsqyrq ==""){
			alert("��ͬ����ǩԼ����,���飡");
			
			return false;			
		
		}
		
		//��ͬ�ܼ�
		var htzj = document.forms[0].htzj.value;
		if(htzj == null || htzj  ==""){
			alert("��ͬ�ܼ�Ϊ��,���飡");
			
			return false;			
		
			
		}
		
		//������Ϣ
		var all_sellerInfo = document.forms[0].all_sellerInfo.value;
		if(all_sellerInfo == null || all_sellerInfo  ==""){
			alert("������ϢΪ��,���飡");
			
			return false;			
		
			
		}		
		//����Ϣ
		var all_buyerInfo = document.forms[0].all_buyerInfo.value;
		if(all_buyerInfo == null || all_buyerInfo  ==""){
			alert("����ϢΪ��,���飡");
			
			return false;			
		
			
		}		
		return isSuccess;
	}
	  
	  
	  
	  function checkSubmit(operationType){
		
		  //�ύ��
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
					if(window.confirm("�ò������޸����ݿ��е�����,�Ҳ����Զ��ָ�,��ȷ��")){
						truthBeTold = true;
					}
				}
			}
			
			if(!truthBeTold){
				return false;
			}
			
			document.forms[0].target="";
			document.all.operationType.value=operationType;
			document.all.xxly.value="01";//ͨ����ά��ɨ��ɼ�
			document.forms[0].submit();
		}
	  
	  //ȷ����ת�����˳�
	  	function sureTurn(type){
		  	var msg ="";
	  		var goToMsg ="��ת������ҳ��";
	  		var returnMsg ="�˳���ǰҳ��";
	  		
	  		if(type =="goTo"){
	  			msg = goToMsg;
	  		}else if (type == "return"){
	  			msg = returnMsg;
	  		}
	  		
	  		//���������δ��������ʾ
			if(sm_success && saveIsSuccess!="1"){
				return window.confirm("��ɨ���������δ���棬�Ƿ�Ҫ"+msg+",��ȷ��");
			}else{
				 return true;
			}
	  	}
	    </script>  
</HTML>
