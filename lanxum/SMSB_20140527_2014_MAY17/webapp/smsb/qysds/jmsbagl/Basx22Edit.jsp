<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant" %>
<%@ page import="com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx22.web.Basx22Form" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.db.util.SfResourceLocator"%>
<html>  
  <head>
    <title>����˰������¼</title>
    <link href="../../../css/text.css" rel="stylesheet" type="text/css">
	
	<script language="JavaScript" type="text/javascript"
		src="../../../js/My97DatePicker/WdatePicker.js"></script>
    <script language=JavaScript src="../../../js/treatImage.js">
    </script>
    <script language=JavaScript src="../../../js/compute.js">
    </script>
    <script language=JavaScript src="../../../js/function.js">
    </script>
    <script language=JavaScript src="../../../js/smsb_common.js">
    </script>
    <script language=JavaScript src="../../../js/Bolan.js">
    </script>
    <script language=JavaScript src="../../../js/MathString.js">
    </script>
    <script language=JavaScript src="../../../js/Stack.js">
    </script>
    <script language=JavaScript src="../../../js/reader.js">
    </script>
    <script language=JavaScript src="../../../js/gmit_selectcontrol.js">
    </script>
    <script language="JavaScript" src="../../../js/qysdsnew.js">
    </script>
    <script language="JavaScript" src="../../../js/calculate.js">
    </script>
  </head>
    <script language="JavaScript">
	function onKeyDown() { // ��ֹˢ�£�����
	 if ((event.keyCode == 8 && event.srcElement.type ==undefined) || (event.keyCode == 116)) {
	    event.keyCode = 0;
	    event.returnValue = false;
	   }
	}
	document.onkeydown = onKeyDown;
  </script>
  <script type="text/javascript" language="JavaScript">
  <%
  		Basx22Form basx = (Basx22Form)request.getAttribute("basx22Form");
  	// �������˰�����������
	    if(session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JNJPJSGZXMLX) != null)
	    {
	        out.print("var arySelect_lyzl = new Array(");
	        out.print("[\"\",\"\"]");
	        String[][] lyzldm = (String[][])session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JNJPJSGZXMLX);
	        for(int i = 0 ; i < lyzldm.length ; i++) 
	        {
	         	out.print(",[\"" + lyzldm[i][0] + "\",\"" +lyzldm[i][1] +"\"]");
	        }
	        out.println(");");
	    }
	%>   
	<%/*��ʼ��*/%> 
	function doInit(){
	    //��ʼ������
		var zl = document.forms[0].zl.value.split(";");
		for(row_index=0;row_index<zl.length;row_index++){
			var new_row=Table1.insertRow(Table1.rows.length);
			zlnr = zl[row_index].substring(0,zl[row_index].indexOf("|"));
			zlsfkysc = zl[row_index].substring(zl[row_index].length-1,zl[row_index].length);
		    addzlmc(row_index,zlnr,new_row);
		    if(document.forms[0].czlx.value=="1")addzlxz(row_index,row_index,new_row,"1");
		    if(zlsfkysc == "1"){
		    	addzlan("1",row_index,new_row);
		    }else{
		    	addzlan("2",row_index,new_row);
		    }
		}
		
		_addOptionsToSelect(document.forms[0].lyzlSelect, arySelect_lyzl);
		document.forms[0].lyzlSelect.value = document.all("jnjpjsgzxmdm").value;
		
		//��ʼ����ť����Ϊ�޸�״̬ʱ��ʾ����
		var czlx = document.forms[0].czlx.value;

		//document.getElementById("disp1").style.display = "none";
		if(document.getElementsByName("ZRHTXM")[0].checked)
		{
			document.getElementById("disp2").style.display = "inline";
		}
		if(document.getElementsByName("ZRHTXMYH")[0].checked)
		{
			document.getElementById("disp3").style.display = "inline";
		}
		
		if(czlx == "2"){
			document.all.jssq.style.display = "none";
			document.all.bg.style.display = "none";
		}else if(czlx == "6"){
			document.all.jssq.style.display = "none";
			document.all.bc.style.display = "none";
		}else{
			document.all.bc.style.display = "none";
			document.all.bg.style.display = "none";
		}
		
	}
	
	<%/*�������*/%>
	function addzl(){	
		var zlmc=document.getElementById("zlmc").value;
   	    var zlmc=zlmc.replace(/(^\s*)|(\s*$)/g, "");  	    			  
		if(zlmc != ""){
			row_index++;  
	    	var new_row=Table1.insertRow(Table1.rows.length); 
	    	addzlmc(row_index,zlmc,new_row);
	    	if(document.forms[0].czlx.value=="1")addzlxz(row_index,row_index,new_row,"2");
	    	addzlan("2",row_index,new_row);
	    	document.getElementById("zlmc").value = "";
		}else{
			document.getElementById("zlmc").value = "";
			alert("����˰���ֻܾ򱱾��еط�˰���Ҫ���ύ�������������ݲ���Ϊ��");
		}

	}
	
	
	<%/*�����������*/%>
	function addzlmc(row_index,zlmc,new_row){
	    new_row.setAttribute("id", "row"+row_index);   
	    var new_col=new_row.insertCell();
	    if(row_index == 0){
	    	new_col.className="2-td2-t-left-textleft";
	    }else{
	    	new_col.className="2-td2-left-textleft";
	    }
	    new_col.setAttribute("id",row_index);
	    new_col.innerHTML=zlmc;
	}
	<%/*��ʼ������Ӱ�ť*/%>
	function addzlan(lx,row_index,new_row){
	    var new_col=new_row.insertCell();
	    if(row_index == 0){
	    	new_col.className="2-td2-t-center";
	    }else{
	    	new_col.className="2-td2-center";
	    }
	    if(lx == "1"){
	    	new_col.innerHTML="&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;";  
	    }else{
	    	new_col.innerHTML="<img src='"+"<%=SfResourceLocator.getContextPath(request)%>"+"images//shanchu2.jpg'  onclick=delete_row('row"+row_index+ "') name='shanchu' width='79' height='22' border='0' id='shanchu'>";
	    }
	    
	}
		<%/*��ʼ������Ӱ�ť*/%>
	function addzlxz(xh,row_index,new_row,lx){
	    var new_col=new_row.insertCell();
	    if(row_index == 0){
	    	new_col.className="2-td2-t-left-textleft";
	    }else{
	    	new_col.className="2-td2-left-textleft";
	    }
	    var dxk 
	    if(lx == "1"){	
	    	dxk = "<input type=\"radio\"   name=zlradio"+xh+" value=\"0\">��</input>&nbsp\;<input type=\"radio\"  id=lack_zl_radio_"+xh+" name=zlradio"+xh+"  value=\"1\" checked>��</input>";
	    }else{	    	
	    	dxk ="&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;";   
	    }
	    new_col.innerHTML=dxk;  
	    
	}
	
	function checkZlRadio(){
		var tag = document.getElementsByTagName('input'); 
		
		for(var i=0;i <tag.length;i++){ 
		  var tagid=tag[i].id;
		   if(tagid.indexOf("lack_zl_radio_")>=0){
		   		var zlRadio=document.getElementById(tagid);
		   		if(zlRadio.checked==true){
		   			alert("�����嵥����ѡ�����ȫ��Ϊ�вſ��Խ������룡");
		   			return false;
		   		}
		   }
		}
		return true;
	}
	<%/*ɾ��������*/%>
	function delete_row(rname){
	  if(window.confirm("�Ƿ�ɾ��������?")){	 
		var zlstring = "";
    	var i; 
	    i=Table1.rows(rname).rowIndex;
	    Table1.deleteRow(i);
	    var zl = document.forms[0].zl.value.split(";"); 
	    for(var n=0;n<zl.length;n++){
	    	if(i!=n){
	    		zlstring += zl[n]+"\;";
	    	}
	    }
	    document.all.zl.value=zlstring.substring(0,zlstring.length-1);
	  }
    } 
    <%/*���������嵥*/%>
	function zlqd(){
		var zlstring = document.all.zl.value;
		var zl = document.forms[0].zl.value.split(";");
		var i = zl.length+1;
		for(;i<=row_index;i++){
			if(document.getElementById(i)!=null){
				var temp=document.getElementById(i).innerHTML;
				zlstring+="\;"+temp+"|0";
			}
		}
		document.all.zl.value=zlstring;
	}
	<%/*����ƶ�*/%>
	function change(){
		var qdsr = document.all("qdsr").value;
		if(qdsr == ""){
			alert("�ۺ�������Դ�������Ϲ��Ҳ�ҵ���߹涨�Ĳ�Ʒ��ȡ�õ����벻��Ϊ�գ�");
			document.forms[0].qdsr.focus();
			return false;
		}else{
			if(!(checkvalue(document.all("qdsr"),0)&&formatCurrency(document.all("qdsr"),0))){
				return false;
			}
		}
		var qdsr = document.all("qdsr").value;
		var jjsr = round2(qdsr*0.1);
		document.all("jjsr").value = jjsr;
	}
	
	<%/*����˰��ͼ����������*/%>
	function sehblkz(lx){
		if(lx == "1"){
			document.forms[0].bajmbl.value = "";
			document.getElementById("bajmse").readOnly=false;
			document.getElementById("bajmbl").readOnly=true;
		}else{
			document.forms[0].bajmse.value = "";
			document.getElementById("bajmse").readOnly=true;
			document.getElementById("bajmbl").readOnly=false;
		}
	}
	<%/*Ч��������Ϣ*/%>
	function checkZbxx(){
		//Ч����ʼ��ֹ����
		var qsrq = document.forms[0].qsrq.value;
		var jzrq = document.forms[0].jzrq.value;
		if(qsrq == ""){
			alert("����д��ʼ���ڣ�");
			document.forms[0].qsrq.focus();
			return false;
		}
		if(jzrq == ""){
			alert("����д��ֹ���ڣ�");
			document.forms[0].jzrq.focus();
			return false;
		}
		//��ʼ���ڲ��ܴ��ڽ�ֹ����
		if(!checkDate(qsrq,jzrq)){
			return false;
		}
		//Ч����˰��ͱ���
		var bajmse = document.forms[0].bajmse.value;
		var bajmbl = document.forms[0].bajmbl.value;
		if(bajmse != ""){
			if(!checkvalue(document.all("bajmse"),0)){
				return false;
			}
		}
		if(bajmbl != ""){
			if(!checkvalue(document.all("bajmbl"),2)){
				return false;
			}
		}
		//if(bajmse == "" && bajmbl == ""){
		//	alert("����д����˰�����������");
		//	document.forms[0].bajmse.focus();
		//	return false;
		//}
		//����˰����ִ�����
		var jmszczxqk = document.forms[0].jmszczxqk.value;
		if(jmszczxqk == ""){
			alert("����д����˰����ִ�������");
			document.forms[0].jmszczxqk.focus();
			return false;
		}
		
		return true;
	}
	<%/*Ч���ӱ���Ϣ*/%>
	function checkYm(){
	    var bajmse = document.all("bajmse").value;
	    var bajmbl = document.all("bajmbl").value;
	    //if(bajmse.length == 0 && bajmbl.length == 0) {
	    //	alert("����˰������������������дһ��");
	    //   return false;
	    //}
	    if(bajmse.length > 0 && bajmbl.length > 0) {
	    	alert("����˰��ͼ������ֻ��ѡ��һ����д��");
	        return false;
	    }
		if(bajmse != ""){
			if(!checkvalue(document.all("bajmse"),0)){
				return false;
			}
		}
		if(bajmbl != ""){
			if(!checkvalue(document.all("bajmbl"),2)){
				return false;
			}
		}	    	
		if( document.all("jnjpjsgzxmlx").value == "") {
			alert("��Ŀ���Ʋ���Ϊ�գ�");
			document.forms[0].jnjpjsgzxmlx.focus();
			return false;
		}
		if(document.all("dybzlmc").value == "") {
			alert("ȡ�õ�һ����������֤���������ƣ�");
            document.forms[0].dybzlmc.focus();			
			return false;
		}	
		var dybrq=document.all.dybrq.value;
		var band=document.all.band.value;
		var reg=/^[0-9]{4}$/
		if(dybrq==""){
			alert('"ȡ�õ�һ��������Ӫ�����ʱ��"����Ϊ�գ�');
			document.all.dybrq.value="";
			document.all.dybrq.focus();
			return false;
			}
		if(dybrq != ""){
			if(!reg.test(dybrq)){
				alert('"ȡ�õ�һ��������Ӫ�����ʱ��"�������ʽ����ȷ��');
				document.all.dybrq.value="";
				document.all.dybrq.focus();
				return false;
				}
			if(parseInt(dybrq)>parseInt(band)){
				alert('"ȡ�õ�һ��������Ӫ�����ʱ��"��������"�������"��');
				document.all.dybrq.focus();
				document.all.dybrq.value="";				
				return false;
				}
		}
		var mzqsnd = document.all("mzqsnd").value;		
		var mzzznd = document.all("mzzznd").value;		
		var jbzsqsnd = document.all("jbzsqsnd").value;		
		var jbzszznd = document.all("jbzszznd").value;
		
		if(isNotNull(mzqsnd)){
			if(!isFullDate(mzqsnd,1,null,false)){
				alert("������ʼ��ȸ�ʽ����ȷ��");
				return false;
			}
		}
		if(isNotNull(mzzznd)){
			if(!isFullDate(mzzznd,1,null,false)){
				alert("������ֹ��ȸ�ʽ����ȷ��");
				return false;
			}
		}
		if(isNotNull(jbzsqsnd)){
			if(!isFullDate(jbzsqsnd,1,null,false)){
				alert("����������ʼ��ȸ�ʽ����ȷ��");
				return false;
			}
		}
		if(isNotNull(jbzszznd)){
			if(!isFullDate(jbzszznd,1,null,false)){
				alert("����������ֹ��ȸ�ʽ����ȷ��");
				return false;
			}
		}
		if(isNotNull(mzqsnd)&&isNotNull(mzzznd)){
			if(mzqsnd>mzzznd){
				alert("������ʼ��Ȳ��ܴ���������ֹ���");
				return false;
			}
		}
		if(isNotNull(jbzsqsnd)&&isNotNull(jbzszznd)){
			if(jbzsqsnd>jbzszznd){
				alert("����������ʼ��Ȳ��ܴ��ڼ���������ֹ���");
				return false;
			}
		}
		
		
		//У�鵥ѡ����Ϊyyn
		var checkZRHTXM_Y = document.getElementsByName("ZRHTXM")[0].checked;
		var checkZRHTXM_N = document.getElementsByName("ZRHTXM")[1].checked;
		var checkZRHTXMYH_Y  = document.getElementsByName("ZRHTXMYH")[0].checked;
		var checkZRHTXMYH_N = document.getElementsByName("ZRHTXMYH")[1].checked;
		var checkZRHTXMYHWJ_Y = document.getElementsByName("ZRHTXMYHWJ")[0].checked;
		
		var check1 = checkZRHTXM_N==true;
		var check2 = (checkZRHTXM_Y== true)&&(checkZRHTXMYH_N==true);
		var check3 = (checkZRHTXM_Y== true)&&(checkZRHTXMYH_Y==true)&&(checkZRHTXMYHWJ_Y==true)
		
		if(!(check1 || check2 || check3))
			{
				alert("��Ŀת�á����ü������Ż����ݲ�����Ҫ��");
				return false;
			}
		 return true;
		
	}
	<%/*��������*/%>
	function jssq()
	{
		if(checkZbxx() && checkYm()&& checkZlRadio()){
			zlqd();
			if(window.confirm("�Ƿ���ܸ���˰�˵ļ���˰�������룿")){
				//�������������򱣴��ҳ�����
				document.forms[0].jnjpjsgzxmdm.value = document.forms[0].jnjpjsgzxmlx.value;
	      		if(window.confirm("�Ƿ���ת�������ӡԤ��ҳ��")){
	      			document.forms[0].actionType.value="SMShPrint";
					document.forms[0].submit();
	      		}else{
	      			document.forms[0].actionType.value="Jssq";
					document.forms[0].submit();
	      		}
      		}
		}
	}
	
	<%/*�޸ı���*/%>
  	function save()
	{
		if(checkZbxx() && checkYm()&& checkZlRadio()){
			zlqd();
			if(window.confirm("�Ƿ�ȷ������?")){
				//�������������򱣴��ҳ�����
				    document.forms[0].jnjpjsgzxmdm.value = document.forms[0].jnjpjsgzxmlx.value;
	      			document.forms[0].actionType.value="Jssq";
					document.forms[0].submit();
	      		
      		}
		}
	}	
	
		<%/*���*/%>
	function bg()
	{
		if(checkZbxx() && checkYm()&& checkZlRadio()){
			zlqd();
			
				//�������������򱣴��ҳ�����
				document.forms[0].jnjpjsgzxmdm.value = document.forms[0].jnjpjsgzxmlx.value;
	      		if(window.confirm("�Ƿ�ȷ�����?")){
	      			
	      			document.forms[0].actionType.value="Bg";
					document.forms[0].submit();
	      		}
      		}
		
	}
	<%/*����*/%>
	function back()
	{
		doSubmitForm('Back');
	}
	
	<%/*������*/%>
	function addYear() {
	    var t = document.all("dybrq").value;
	    if( validateDybrq()) {
	    	document.all("mzqsnd").value = t;
		    document.all("mzzznd").value = parseInt(t) + parseInt("2");
		    document.all("jbzsqsnd").value = parseInt(document.all("mzzznd").value) + parseInt("1");
		    document.all("jbzszznd").value = parseInt(document.all("jbzsqsnd").value) + parseInt("2");
	    } else {
	      document.forms[0].dybrq.focus();
	      return false; 
	    }	
		
	}
	<%/*�ı�������*/%>
	function changeYear() {
	    var t = document.all("dybrq").value;
  	    var band = document.all("band").value;	    
	    if( validateDybrq() && t <= band) {
/*	    	document.all("mzqsnd").value = t;
		    document.all("mzzznd").value = parseInt(t) + parseInt("2");
		    document.all("jbzsqsnd").value = parseInt(document.all("mzzznd").value) + parseInt("1");
		    document.all("jbzszznd").value = parseInt(document.all("jbzsqsnd").value) + parseInt("2");
*/
            return true;		    
	    } else {
	      alert("���������������룡");
	      document.all.dybrq.value="";
	      document.forms[0].dybrq.focus();
	      return false; 
	    }	
		
	}	
	
	<%/*��֤�������*/%>
	function validateMznd() {
	  var mzqsnd = document.all("mzqsnd").value;
	  var mzzznd = document.all("mzzznd").value;
	
	  if(mzqsnd.length != 4) {
	   alert("������ʼ��ȱ�����4λ���� �磺2005");
	   document.all.mzqsnd.value="";
	   document.forms[0].mzqsnd.focus();
	 }
	  if(mzzznd.length != 4) {
	   alert("������ֹ��ȱ�����4λ���� �磺2005");
	   document.all.mzzznd.value="";
	   document.forms[0].mzzznd.focus();
	 }
	   if(mzqsnd > mzzznd) {
	  	alert("������ʼ��Ȳ��ܴ���������ֹ��ȣ�");
	  	document.all.mzqsnd.value="";	  	
	  }
	   return true;	 	  	  
	}

	<%/*��֤��֤���*/%>
	function validateJznd() {
	  var jbzsqsnd = document.all("jbzsqsnd").value;
	  var jbzszznd = document.all("jbzszznd").value;
	 
	  if(jbzsqsnd.length != 4) {
	   alert("������ʼ��ȱ�����4λ���� �磺2005");
	   document.all.jbzsqsnd.value="";
	   document.forms[0].jbzsqsnd.focus();
	 }
	 
	  if(jbzszznd.length != 4) {
	   alert("������ֹ��ȱ�����4λ���� �磺2005");
	   document.all.jbzszznd.value="";
	   document.forms[0].jbzszznd.focus();
	 }
	 
	 if(jbzsqsnd > jbzszznd) {
	  	alert("������ʼ��Ȳ��ܴ���������ֹ��ȣ�");
	  	document.all.jbzsqsnd.value="";
	  }
	 return true;	  	  
	}	
		
	function validateDybrq() {
  	var dybrq = document.all("dybrq").value;
  	var band = document.all("band").value;
  	if(!isInt(dybrq)) {
  	   alert("ȡ�õ�һ��������Ӫ�����ʱ�������4λ���� �磺2005");
  	   document.all.dybrq.value="";
  	   document.forms[0].dybrq.focus();
       return false;
  	}
  	if(dybrq.length != 4) {
  	   alert("ȡ�õ�һ��������Ӫ�����ʱ�������4λ���� �磺2005");
  	   document.all.dybrq.value="";
  	   document.forms[0].dybrq.focus();
       return false;
  	}
/* 	
    if(dybrq >= band || dybrq < band - 5) {
  	   alert("ȡ�õ�һ��������Ӫ�����ʱ������Ǵ��ڵ��ڱ�����ȼ�5С�ڱ������");
  	   document.all.dybrq.value="";
  	   document.forms[0].dybrq.focus();
       return false;
  	}
*/
  	return true;
  }
	
	//ZRHTXM��ʾ
	function clickZRHTXM_Y()
	{
		
		document.getElementById("disp2").style.display="inline";
		if(document.getElementsByName("ZRHTXMYH")[0].checked)
		{document.getElementById("disp3").style.display="inline";}
	}
	
	//ZRHTXM����
	function clickZRHTXM_N()
	{
		document.getElementsByName("ZRHTXMYH")[0].checked=false;
		document.getElementsByName("ZRHTXMYH")[1].checked=false;
	    document.getElementsByName("ZRHTXMYHWJ")[0].checked=false;
		document.getElementsByName("ZRHTXMYHWJ")[1].checked=false;
		document.getElementById("disp3").style.display="none";
		document.getElementById("disp2").style.display="none";	
	}
	
	//ZRHTXMYH��ʾ
	function clickZRHTXMYH_Y()
	{
		document.getElementById("disp3").style.display="inline";	
	}
	
	//ZRHTXMYH����
	function clickZRHTXMYH_N()
	{
		document.getElementsByName("ZRHTXMYHWJ")[0].checked=false;
		document.getElementsByName("ZRHTXMYHWJ")[1].checked=false;
		document.getElementById("disp3").style.display="none";	
	}
  </script>
  
  
  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  onload="doInit()">
    <%@ include file="../../include/header.jsp" %> 
     
	  
      <html:form action="/webapp/smsb/qysds/jmsbagl/Basx22Action.do" method="post">
        <html:hidden property="actionType" />
        <html:hidden property="basqwsh" />
        <html:hidden property="basqbh" />
        <html:hidden property="band" />
        <html:hidden property="jsjdm" />
        <html:hidden property="xh" />
        <html:hidden property="jnjpjsgzxmdm" />
        <html:hidden property="zl" />
        <html:hidden property="czlx" />
        <html:hidden property="clbs" />
        <table width="75%" align="center" cellspacing="0" height="480">
          <tr>
            <td class="1-td1" colspan="7" height="30">
              ¼����ܷ���˾ʵʩ��ͬ��Դ������Ŀ�����ñ�������
            </td>
          </tr>
          <br>
          <tr>
            <td class="1-td2" colspan="7" valign="top">
            <br>
            	<table cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
                    <tr>
                      <td  class="2-td2-t-left">
                          <strong>����������</strong>
                      </td>
                      <td  width="22%"class="2-td2-t-left">
                      	&nbsp;<bean:write name="basx22Form" property="basqbh"/>
                      </td>
                      <td  class="2-td2-t-left">
                          <strong>���������</strong>               
                      </td>
                      <td  class="2-td2-t-left">
                      	&nbsp;<bean:write name="basx22Form" property="jsjdm"/>
                      </td>                  
                      <td  class="2-td2-t-left">                       
                          <strong>��˰������</strong>
                      </td>
                      <td width="22%"  class="2-td2-t-center">
                      &nbsp;<bean:write name="basx22Form" property="nsrmc"/>
                      </td>
                    </tr>
                    <tr>
                      <td  class="2-td2-left">                        
                          <strong>����˰����</strong>    
                      </td>
                      <td  width="22%"class="2-td2-left">
                      	&nbsp;<bean:write name="basx22Form" property="zgsws"/>
                      </td>
                      <td  class="2-td2-left">
                          <strong>��������</strong>               
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx22Form" property="jjlx"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>������ҵ</strong>
                      </td>
                      <td width="22%"  class="2-td2-center">
                      	&nbsp;<bean:write name="basx22Form" property="sshy"/>
                      </td>
                    </tr>
                   <tr>
                      <td  class="2-td2-left">
                         <strong>��ϵ��</strong>
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx22Form" property="lxr"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>��ϵ�绰</strong>
                      </td>
                      <td width="22%"  class="2-td2-center" colspan="3">
                       &nbsp;<bean:write name="basx22Form" property="lxdh"/>
                      </td>
                    </tr>
                  </table>
                  
                  
                  </br>
                  <table cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">            
              <tr>
                <td class="2-td2-t-left" width="10%">
                  ��ʼʱ��<font color="#FF0000">*</font>
                </td>
                <td class="2-td2-t-left" width="15%"> 
                  	<html:text property="qsrq" size="12" onclick="WdatePicker()"></html:text>
                </td>
                <td class="2-td2-t-left" width="10%">
                  ��ֹʱ��<font color="#FF0000">*</font>
                </td>
                <td class="2-td2-t-left" width="15%">
                	<html:text property="jzrq" size="12" onclick="WdatePicker()"></html:text>
                </td>                
            
                <td class="2-td2-t-left" width="10%">
                  ����˰��
                </td>
                <td class="2-td2-t-left" width="15%">
                  	<html:text property="bajmse" size="10"/>Ԫ
                </td>
                <td class="2-td2-t-left" width="10%">
                  �������
                </td>
                <td class="2-td2-t-center" width="15%">
	                <html:text property="bajmbl" size="10"/>%
                </td>
                
              </tr>
              <tr>
                <td class="2-td2-left">
                  ����˰����
                  <br/>
                  ִ�����<font color="#FF0000"> *	</font>
                </td>
                <td class="2-td2-center" colspan="7">
                	<div align="left">
                		&nbsp;&nbsp;&nbsp;<html:textarea property="jmszczxqk" rows="5" cols="90"></html:textarea>
                	</div>
                  <td>
              </tr>
              <tr>
                <td class="2-td2-center" colspan="8">
                	<br/>
                  <div align="left">
                    &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
                    ����˰���������嵥��
                  </div>
                  
                  <table width="95%" cellspacing=0 border=0 id="Table1"> 
                  
                  </table>
                  
                  <table width="95%" cellspacing=0 border=0 id="Table2">
                  
                  <tr>
					<td width="79%" class="2-td2-left" colspan="2">
					<div align="left">�Ƿ�����ͬ��Դ������Ŀת�ú�����</div>
					</td>
					<td class="2-td2-center">
					&nbsp;
						<div  id = "disp1" style="display:inline;">
							<html:radio property="ZRHTXM" name="basx22Form" id="ZRHTXM_Y" value='Y' onclick="clickZRHTXM_Y()" >��</html:radio>
							<html:radio property="ZRHTXM" name="basx22Form" id="ZRHTXM_N" value='N' onclick="clickZRHTXM_N()" >��</html:radio>
						</div>
					</td>
					
				</tr>
				

				
				<tr >
					<td width="79%" class="2-td2-left" colspan="2">
					<div align="left">��Ŀת�ú�ͬ����Ŀԭ�����Ż�</div>
					</td>
					<td class="2-td2-center">
					&nbsp;
						<div  id = "disp2" style="display:none;">
							<html:radio property="ZRHTXMYH" name="basx22Form" id="ZRHTXMYH_Y" value='Y' onclick="clickZRHTXMYH_Y()" >��</html:radio>
							<html:radio property="ZRHTXMYH" name="basx22Form" id="ZRHTXMYH_N" value='N' onclick="clickZRHTXMYH_N()" >��</html:radio>
						</div>
					</td>
				</tr>
				
				
				
				<tr>
					<td width="79%" class="2-td2-left" colspan="2">
					<div align="left">��Ŀת�ú�ͬ����Ŀԭ�����Żݱ����ļ�</div>
					</td>
					<td class="2-td2-center">
					&nbsp;
						<div  id = "disp3" style="display:none;">
							<html:radio property="ZRHTXMYHWJ" name="basx22Form" id="ZRHTXMYHWJ_Y" value='Y' >��</html:radio>
							<html:radio property="ZRHTXMYHWJ" name="basx22Form" id="ZRHTXMYHWJ_N" value='N' >��</html:radio>
						</div>
					
					</td>
				</tr> 
			</table>                 
                  
                  </br>
                  <table width="95%" cellspacing=0 border=0>  
                  	<tr>
                  		<td class="2-td2-t-left">
                  			����˰���ֻܾ򱱾��еط�˰���Ҫ���ύ����������
                  		</td>
                  		<td class="2-td2-t-left">
                  			<input type="text" size="60"  name="zlmc" value="" />
                  		</td>
                  		<td class="2-td2-t-center">
                  			<img src="<%=static_contextpath%>images//tianjia2.jpg" onclick="addzl()" name="tianjia" width="79" height="22" border="0" id="tianjia">               			
                  		</td>
                  	</tr>
                  </table>	 
                  </br>
                  <td>
              </tr>
            </table>
                  
            	  </br>
			<table class="table-99" cellspacing="0">
				<tr>
					<td width="50%" class="2-td2-t-left" colspan="2">
					<div align="left">��Ŀ����<font color="red">
					* </font></div>
					</td>
					<td class="2-td2-t-center" width="50%">
					<div id="mx_zsfsdm_epx_visible_1" align="left"><html:select
						name="basx22Form" styleId="lyzlSelect" property="jnjpjsgzxmlx"
						style="width:222px" /></div>
					</td>
				</tr>

				<tr>
					<td width="20%" class="2-td2-left" colspan="2">
					<div align="left">ȡ�õ�һ����������֤����������<font color="red">
					* </font></div>
					</td>
					<td class="2-td2-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left"><html:text
						property="dybzlmc" maxlength="200" size="30" ></html:text></div>
					</td>
				</tr>
				<tr>
					<td width="20%" class="2-td2-left" colspan="2">
					<div align="left">ȡ�õ�һ��������Ӫ�����ʱ��<font color="red">
					* </font></div>
					</td>
					<td class="2-td2-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left"><html:text
						property="dybrq" maxlength="4" size="30" onchange="addYear()"></html:text>��
					</td>
				</tr>
				<tr>
					<td width="20%" class="2-td2-left" colspan="2">
					<div align="left">�������</div>
					</td>
					<td class="2-td2-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left">�� <html:text
						property="mzqsnd" maxlength="4" size="30"  size="5" readonly="readonly">
						</html:text>
					���� <html:text property="mzzznd" maxlength="4" size="30"  size="5" readonly="readonly"></html:text> ��</div>
					</td>
				</tr>
				<tr>
					<td width="20%" class="2-td2-left" colspan="2">
					<div align="left">�����������</div>
					</td>
					<td class="2-td2-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left">�� <html:text
						property="jbzsqsnd" maxlength="4" size="5" readonly="readonly"></html:text>
					���� <html:text property="jbzszznd" maxlength="4" size="5" readonly="readonly"></html:text> ��</div>
					</td>
				</tr>
				
				
				
				
				
			</table>
			
                 <br/>
                 <table class="table-99" cellspacing="0">
                    <tr>
                      <td width="13%" class="2-td2-t-left">
                        <div align="right">
                          ¼������
                        </div>
                      </td>
                      <td width="27%" class="2-td2-t-left">
                        <div align="left">
                        	<html:text property="mr_lrrq" readonly="readonly" style="background-color:#efefef"></html:text>
                        </div>
                      </td>
                      <td width="10%" class="2-td2-t-left">
                        <div align="right">
                          	�������
                        </div>
                      </td>
                      <td width="30%" class="2-td2-t-left">
                        <div align="left">
                        	<html:text property="mr_band" readonly="readonly" style="background-color:#efefef"></html:text>
                        </div>
                      </td>
                      <td width="10%" class="2-td2-t-left">
                        <div align="right">
                          ¼����
                        </div>
                      </td>
                      <td width="30%" class="2-td2-t-center">
                        <div align="left">
                        	<html:text property="mr_lrr" readonly="readonly" style="background-color:#efefef"></html:text>
                        </div>
                      </td>
                    </tr>
                  </table>
                  <br/>
                  <table width="100%" border="0" align="center">
                    <tr align="center">      
	                    <td>
	                    	<div id="jssq" style="display: inline;">
	      				  	<a style="cursor:hand" onClick="jssq()" onMouseOut="MM_swapImgRestore()"
	                        onMouseOver="MM_swapImage('jishoushenqing','','<%=static_contextpath%>images//b_jssq2.jpg',1)">
	                        <img src="<%=static_contextpath%>images//b_jssq1.jpg" name="jishoushenqing" width="79" height="22" border="0" id="jishoushenqing">
	                        </a>
	                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                        </div>
	                        <div id="bg" style="display: inline;">
	      				  	<a style="cursor:hand" onClick="bg()" onMouseOut="MM_swapImgRestore()"
	                        onMouseOver="MM_swapImage('bgzx','','<%=static_contextpath%>images//b-bgzx2.jpg',1)">
	                        <img src="<%=static_contextpath%>images//b-bgzx1.jpg" name="bgzx" width="95" height="22" border="0" id="bgzx">
	                        </a>
	                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                        </div>
	                        <div id="bc" style="display: inline;">
	      				  	<a style="cursor:hand" onClick="save()" onMouseOut="MM_swapImgRestore()"
	                        onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images//baocun2.jpg',1)">
	                        <img src="<%=static_contextpath%>images//baocun1.jpg" name="baocun" width="79" height="22" border="0" id="baocun">
	                        </a>
	                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                        </div>
	                        <a style="cursor:hand" onClick="back()" onMouseOut="MM_swapImgRestore()"
	                        onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images//fanhui2.jpg',1)">
	                        <img src="<%=static_contextpath%>images//fanhui1.jpg" name="fanhui" width="79" height="22" border="0" id="fanhui">
	                        </a>
	                    </td>
                    </tr>
                  </table> 
                  <br>
                  <br>
            </td>
          </tr>
        </table>
        <br>
        <%@ include file="../../include/footer.jsp" %>
      </html:form>
  </body>

</html>