<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant" %>
<%@ page import="com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx14b.web.Basx14BForm" %>
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
  		Basx14BForm basx = (Basx14BForm)request.getAttribute("basx14BForm");
  		int band = Integer.parseInt(basx.getBand());
  		int i = 2008;
  		if(basx.getGznd() != null && !basx.getGznd().equals("")){
  			i = Integer.parseInt(basx.getGznd());
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
		    if(zlsfkysc == "1"){
		    	addzlan("1",row_index,new_row);
		    }else{
		    	addzlan("2",row_index,new_row);
		    }
		}
		//����ר���豸���������򲻿ɱ༭
		document.all("zysblxdm").disabled="disabled";
		//����Ͷ����ȵ���Ϣ����Ѿ�����ɱ�����ʶ���Ϊ����ɣ��򲻿ɱ༭
		var band = document.all("band").value;
		var gznd = document.all("gznd").value;
		var tze_v = document.forms[0].tze.value.split(";");
		var kdke_v = document.forms[0].kdke.value.split(";");
		var sjdke_v = document.forms[0].sjdke.value.split(";");
		var jzdke_v = document.forms[0].jzdke.value.split(";");
		var bjkz_v = document.forms[0].bjkz.value.split(";");
		var num = 0;
		for(var n=gznd;n<=band;n++){
			var tze = "tze"+n;
			var kdke = "kdke"+n;
			var sjdke = "sjdke"+n;
			var jzdke = "jzdke"+n;
			var bjkz = "bjkz"+n;
			if(tze_v[num] == "null" || tze_v[num] == ""){
				document.all(tze).value = "0.00";
			}else{
				document.all(tze).value = tze_v[num];
				formatCurrency(document.all(tze),0);
			}
			if(kdke_v[num] == "null" || kdke_v[num] == ""){
				document.all(kdke).value = "0.00";
			}else{
				document.all(kdke).value = kdke_v[num];
				formatCurrency(document.all(kdke),0);
			}
			if(sjdke_v[num] == "null" || sjdke_v[num] == ""){
				document.all(sjdke).value = "0.00";
			}else{
				document.all(sjdke).value = sjdke_v[num];
				formatCurrency(document.all(sjdke),0);
			}
			if(jzdke_v[num] == "null" || jzdke_v[num] == ""){
				document.all(jzdke).value = "0.00";
			}else{
				document.all(jzdke).value = jzdke_v[num];
				formatCurrency(document.all(jzdke),0);
			}
			document.all(bjkz).value = bjkz_v[num];
			
			num++;
		}
		//��ʼ����ť����Ϊ�޸�״̬ʱ��ʾ����
		var czlx = document.forms[0].czlx.value;
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
		var value=zlmc.replace(/(^\s*)|(\s*$)/g, "");  
      	if(value.length==0){
      		alert("����˰���ֻܾ򱱾��еط�˰���Ҫ���ύ�������������ݲ���ȫ��Ϊ�ո�");
      		document.all.zlmc.focus();
      		return;
          	} 
		if(zlmc != ""){
			row_index++;  
	    	var new_row=Table1.insertRow(Table1.rows.length); 
	    	addzlmc(row_index,zlmc,new_row);
	    	addzlan("2",row_index,new_row);
	    	document.getElementById("zlmc").value = "";
		}else{
			alert("����˰���ֻܾ򱱾��еط�˰���Ҫ���ύ�������������ݲ���Ϊ��");
		}   
	    
	}
	<%/*�����������*/%>
	function addzlmc(row_index,zlmc,new_row){
	    new_row.setAttribute("id", "row"+row_index);   
	    var new_col=new_row.insertCell(0);
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
	    var new_col=new_row.insertCell(1);
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
	<%/*ɾ��������*/%>
	function delete_row(rname){
		if(window.confirm("�Ƿ�ɾ�������ϣ�")){
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
	function change(lx,xh){
		var tze = "tze"+xh;
		var kdke = "kdke"+xh;
		var sjdke = "sjdke"+xh;
		var jzdke = "jzdke"+xh;
		if(lx == "1"){
			var tze_v = document.all(tze).value;
			if(tze_v != ""){
				if(!(checkvalue(document.all(tze),0)&&formatCurrency(document.all(tze),0))){
					return false;
				}
			}
		}else{
			var sjdke_v = document.all(sjdke).value;
			if(sjdke_v != ""){
				if(!(checkvalue(document.all(sjdke),0)&&formatCurrency(document.all(sjdke),0))){
					return false;
				}
			}
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
		if(bajmse !="" && bajmbl!=""){
			alert("����˰��ͼ������ֻ��ѡ��һ����д��");
			document.forms[0].bajmse.focus();
			return false;
			}
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
		var zysbmc = document.all("zysbmc").value;
		if(zysbmc == "" ){
			alert("����ר���豸���Ʋ���Ϊ��!");
			document.forms[0].zysbmc.focus();
			return false;
		}
		var zysblxdm = document.all("zysblxdm").value;
		 if(zysblxdm == "" ){
			alert("ר���豸���಻��Ϊ��!");
			document.forms[0].zysblxdm.focus();
			return false;
		 }
		return true;
		
	}
	<%/*Ч��Ͷ����Ϣ*/%>
	function checkTzxx(){
		var band = document.all("band").value;
		var gznd = document.all("gznd").value;
		for(var n=gznd;n<=band;n++){
			var tze = "tze"+n;
			var kdke = "kdke"+n;
			var sjdke = "sjdke"+n;
			var jzdke = "jzdke"+n;
			if(n==gznd){
				//�жϸ���ĵ�������Ͷ�ʶ��Ƿ�Ϊ��
				if(document.all(tze).value == "" || document.all(tze).value == "0.00"){
					alert(n+"���깺���豸Ͷ�ʶ��������Ͷ�ʶ");
					document.all(tze).focus();
					return false;
				}else{
					//����ɵ����Ӧ��˰�� = ���깺���豸Ͷ�ʶ�*0.1
					document.all(kdke).value = document.all(tze).value * 0.1;
					//���ֽ�����������
					var num = round2(document.all(kdke).value);
					document.all(kdke).value = num;
					//������ת��ΪС�������λ
					formatCurrency(document.all(kdke),0);
				}
			}else{
				//���깺���豸Ͷ�ʶ�Ĭ��Ϊ0.00
				document.all(tze).value = "0.00";
				//����ĵ���ɵ����Ӧ��˰��=ȥ��Ľ�ת�Ժ���ȵ����Ӧ��˰��
				var qnjzdke = "jzdke"+(n-1);
				document.all(kdke).value = document.all(qnjzdke).value;
			}
			//�жϸ���ĵ���ʵ�ʵֿ�Ӧ��˰���ö��Ƿ�Ϊ��
			if(document.all(sjdke).value == ""){
				document.all(sjdke).value = "0.00";
			}
			//�жϸ���ĵ���ʵ�ʵֿ�Ӧ��˰���ö��Ƿ���ڵ���ɵֿ�Ӧ��˰���ö�
			if((document.all(kdke).value-document.all(sjdke).value)<0){
				alert(n+"����ʵ�ʵֿ�Ӧ��˰���ö����С�ڻ���ڵ���ɵֿ�Ӧ��˰���ö");
				document.all(sjdke).focus();
				return false;
			}else{
				//����������������ת�Ժ���ȵֿ�Ӧ��˰���ö�=����ɵֿ�Ӧ��˰���ö�-����ʵ�ʵֿ�Ӧ��˰���ö�
				document.all(jzdke).value = document.all(kdke).value-document.all(sjdke).value;
				//���ֽ�����������
				var num = round2(document.all(jzdke).value);
				document.all(jzdke).value = num;
				//������ת��ΪС�������λ
				formatCurrency(document.all(jzdke),0);
			}
			
		}
		return true;
		
	}
	
	
		<%/*Ч��Ͷ����Ϣ*/%>
	function calc(){
		var band = document.all("band").value;
		var gznd = document.all("gznd").value;
		for(var n=gznd;n<=band;n++){
			var tze = "tze"+n;
			var kdke = "kdke"+n;
			var sjdke = "sjdke"+n;
			var jzdke = "jzdke"+n;
			if(n==gznd){
				//�жϸ���ĵ�������Ͷ�ʶ��Ƿ�Ϊ��
				if(document.all(tze).value == "" || document.all(tze).value == "0.00"){					
					document.all(tze).value = "0.00"
					
				}else{
					//����ɵ����Ӧ��˰�� = ���깺���豸Ͷ�ʶ�*0.1
					document.all(kdke).value = document.all(tze).value * 0.1;
					//���ֽ�����������
					var num = round2(document.all(kdke).value);
					document.all(kdke).value = num;
					//������ת��ΪС�������λ
					formatCurrency(document.all(kdke),0);
				}
			}else{
				//���깺���豸Ͷ�ʶ�Ĭ��Ϊ0.00
				document.all(tze).value = "0.00";
				//����ĵ���ɵ����Ӧ��˰��=ȥ��Ľ�ת�Ժ���ȵ����Ӧ��˰��
				var qnjzdke = "jzdke"+(n-1);
				document.all(kdke).value = document.all(qnjzdke).value;
			}
			//�жϸ���ĵ���ʵ�ʵֿ�Ӧ��˰���ö��Ƿ�Ϊ��
			if(document.all(sjdke).value == ""){
				document.all(sjdke).value = "0.00";
			}
			//�жϸ���ĵ���ʵ�ʵֿ�Ӧ��˰���ö��Ƿ���ڵ���ɵֿ�Ӧ��˰���ö�
			if((document.all(kdke).value-document.all(sjdke).value)<0){
				alert(n+"����ʵ�ʵֿ�Ӧ��˰���ö����С�ڻ���ڵ���ɵֿ�Ӧ��˰���ö");
				document.all(sjdke).value="";
				document.all(sjdke).focus();
				
			}else{
				//����������������ת�Ժ���ȵֿ�Ӧ��˰���ö�=����ɵֿ�Ӧ��˰���ö�-����ʵ�ʵֿ�Ӧ��˰���ö�
				document.all(jzdke).value = document.all(kdke).value-document.all(sjdke).value;
				//���ֽ�����������
				var num = round2(document.all(jzdke).value);
				document.all(jzdke).value = num;
				//������ת��ΪС�������λ
				formatCurrency(document.all(jzdke),0);
			}
			
		}
	
		
	}
	
	
	<%/*����Ͷ����Ϣ*/%>
	function tzxx(){
		var band = document.all("band").value;
		var gznd = document.all("gznd").value;
		var tzestring = "";
		var kdkestring = "";
		var sjdkestring = "";
		var jzdkestring = "";
		var bjkzstring = "";
		for(var n=gznd;n<=band;n++){
			var tze = "tze"+n;
			var kdke = "kdke"+n;
			var sjdke = "sjdke"+n;
			var jzdke = "jzdke"+n;
			var bjkz = "bjkz"+n;
			tzestring = tzestring + "\;"+document.all(tze).value;
			kdkestring = kdkestring+"\;"+document.all(kdke).value;
			sjdkestring = sjdkestring+"\;"+document.all(sjdke).value;
			jzdkestring = jzdkestring+"\;"+document.all(jzdke).value;
			if(document.all(bjkz).value == ""){
				bjkzstring = bjkzstring +"\;1";
			}else{
				bjkzstring = bjkzstring +"\;"+document.all(bjkz).value;
			}
		}
		document.all.tze.value=tzestring.substring(1,tzestring.length);
		document.all.kdke.value=kdkestring.substring(1,kdkestring.length);
		document.all.sjdke.value=sjdkestring.substring(1,sjdkestring.length);
		document.all.jzdke.value=jzdkestring.substring(1,jzdkestring.length);
		document.all.bjkz.value=bjkzstring.substring(1,bjkzstring.length);
	}
	
	<%/*��������*/%>
	function jssq()
	{
		if(checkZbxx() && checkYm() && checkTzxx()){
			zlqd();
			if(window.confirm("�Ƿ���ܸ���˰�˵ļ���˰�������룿")){
				//��������Ͷ����Ϣ
				tzxx();
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
	function save()
	{
		if(checkZbxx() && checkYm()&& checkTzxx()){
			zlqd();
			if(window.confirm("�Ƿ�ȷ������?")){
					tzxx();
				//�������������򱣴��ҳ�����
				    document.forms[0].zysblx.value = document.forms[0].zysblxdm.value;
	      			document.forms[0].actionType.value="Jssq";
					document.forms[0].submit();
	      		
      		}
		}
	}
	
		function bg()
	{
		if(checkZbxx() && checkYm()&& checkTzxx()){
			zlqd();
			if(window.confirm("�Ƿ�ȷ�����?")){
					tzxx();
				//�������������򱣴��ҳ�����
				    document.forms[0].zysblx.value = document.forms[0].zysblxdm.value;
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
	
  </script>
  
  
  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  onload="doInit()">
    <%@ include file="../../include/header.jsp" %> 
     
	  
      <html:form action="/webapp/smsb/qysds/jmsbagl/Basx14BAction.do" method="post">
        <html:hidden property="actionType" />
        <html:hidden property="basqwsh" />
        <html:hidden property="basqbh" />
        <html:hidden property="band" />
        <html:hidden property="jsjdm" />
        <html:hidden property="xh" />
        <html:hidden property="zl" />
        <html:hidden property="czlx" />
        <html:hidden property="clbs" />
        <html:hidden property="tze" />
        <html:hidden property="kdke" />
        <html:hidden property="sjdke" />
        <html:hidden property="jzdke" />
        <html:hidden property="bjkz" />
        <html:hidden property="tzxxjg" />
        <html:hidden property="zysblx" />
        <table width="75%" align="center" cellspacing="0" height="480">
          <tr>
            <td class="1-td1" colspan="7" height="30">
              ¼����ҵ�������ڻ������������ܽ�ˮ����ȫ������ר���豸��Ͷ�ʵ���˰������  
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
                      	&nbsp;<bean:write name="basx14BForm" property="basqbh"/>
                      </td>
                      <td  class="2-td2-t-left">
                          <strong>���������</strong>               
                      </td>
                      <td  class="2-td2-t-left">
                      	&nbsp;<bean:write name="basx14BForm" property="jsjdm"/>
                      </td>                  
                      <td  class="2-td2-t-left">                       
                          <strong>��˰������</strong>
                      </td>
                      <td width="22%"  class="2-td2-t-center">
                      &nbsp;<bean:write name="basx14BForm" property="nsrmc"/>
                      </td>
                    </tr>
                    <tr>
                      <td  class="2-td2-left">                        
                          <strong>����˰����</strong>    
                      </td>
                      <td  width="22%"class="2-td2-left">
                      	&nbsp;<bean:write name="basx14BForm" property="zgsws"/>
                      </td>
                      <td  class="2-td2-left">
                          <strong>��������</strong>               
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx14BForm" property="jjlx"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>������ҵ</strong>
                      </td>
                      <td width="22%"  class="2-td2-center">
                      	&nbsp;<bean:write name="basx14BForm" property="sshy"/>
                      </td>
                    </tr>
                   <tr>
                      <td  class="2-td2-left">
                         <strong>��ϵ��</strong>
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx14BForm" property="lxr"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>��ϵ�绰</strong>
                      </td>
                      <td width="22%"  class="2-td2-center" colspan="3">
                       &nbsp;<bean:write name="basx14BForm" property="lxdh"/>
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
                  	<html:text property="bajmse"  size="10" />&nbsp;Ԫ
                </td>
                <td class="2-td2-t-left" width="10%">
                  �������
                </td>
                <td class="2-td2-t-center" width="15%">
	                <html:text property="bajmbl"  size="10" />&nbsp;%
                </td>
                
              </tr>
              <tr>
                <td class="2-td2-left">
                  ����˰����
                  <br/>
                  ִ�����<font color="#FF0000">*</font>
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
                  <br>
                  <table width="95%" cellspacing=0 border=0>  
                  	<tr>
                  		<td class="2-td2-t-left">
                  			����˰���ֻܾ򱱾��еط�˰���Ҫ���ύ����������
                  		</td>
                  		<td class="2-td2-t-left">
                  			<input type="text" size="80"  name="zlmc" value="" />
                  		</td>
                  		<td class="2-td2-t-center">
                  			<a style="cursor:hand" onClick="addzl()" onMouseOut="MM_swapImgRestore()"
	                        onMouseOver="MM_swapImage('tianjia','','<%=static_contextpath%>images//tianjia2.jpg',1)">
	                        <img src="<%=static_contextpath%>images//tianjia1.jpg" name="tianjia" width="79" height="22" border="0" id="tianjia">
	                        </a>
                  		</td>
                  	</tr>
                  </table>	 
                  <br>
                  <td>
              </tr>
            </table>
                  <br>
            		<table class="table-99" cellspacing="0">
                    <tr>
                      <td  width="50%" class="2-td2-t-left" colspan="2">
                       <div align="left">&nbsp;&nbsp;
                          	�������&nbsp;<font color="#FF0000">*</font>&nbsp;
                        </div>
                      </td>
                      <td class="2-td2-t-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
                          <html:text property="gznd" maxlength="4" size="17"  readonly="readonly" styleClass="txtInput"></html:text>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="2-td2-left" colspan="2">
                        <div align="left">&nbsp;&nbsp;
                          	����ר���豸����&nbsp;<font color="#FF0000">*</font>&nbsp;
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
                          <html:text property="zysbmc" maxlength="200" size="17"  readonly="readonly" styleClass="txtInput"></html:text>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="2-td2-left" colspan="2">
                        <div align="left">&nbsp;&nbsp;
                          	ר���豸����&nbsp;<font color="#FF0000">*</font>&nbsp;
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
                          <html:select name="basx14BForm" property="zysblxdm">
		  						<logic:iterate id="data" indexId="index" length="length" name="basx14BForm"
		  							property="zysblxList" type="com.ttsoft.bjtax.smsb.qysdsjmsbagl.vo.DmVo">
		    					<logic:equal name="data" property="level" value="1">
		      						<optgroup label="<bean:write name="data" property="mc"/>">
		      						</optgroup>
		   		 				</logic:equal>
		    					<logic:equal name="data" property="level" value="2">
		      						<option value="<bean:write name="data" property="dm"/>">&nbsp;&nbsp;
		        						<bean:write name="data" property="mc" />
		     						 </option>
		    					</logic:equal>
		  						</logic:iterate>
							</html:select>
                        </div>
                      </td>
                    </tr>                    
                  </table>
                   <br>
                  <table cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
                  	<tr>
                  		<td class="2-td2-t-center" colspan="5">
                  			��λ��Ԫ
                  		</td>
                  	</tr>
                     <tr>
                      <td class="2-td2-left" width="11%">
                         �������
                      </td>
                      <td class="2-td2-left" width="11%">
                          ���깺���豸Ͷ�ʶ�
                      </td>
                      <td class="2-td2-left" width="11%">
                          ����ɵ����Ӧ��˰��
                      </td>
                      <td class="2-td2-left" width="11%">
                          ����ʵ�ʵ����Ӧ��˰��
                      </td>
                      <td class="2-td2-center" width="11%">
                          ��ת�Ժ���ȵ����Ӧ��˰��
                      </td>                                                                                                        
                    <% 
                    boolean bool = true;
					for(;i<=band;i++){
						String tze = "";
						if(bool){
							tze= "<input type=\"text\" name=\"tze"+i+"\" size=\"15\"  value=\"\"  onblur=\"calc()\"  onchange=\"change('1',"+i+")\">";
						}else{
							tze= "<input type=\"text\" name=\"tze"+i+"\" size=\"15\"  value=\"\" readonly=\"readonly\">";
						}
						bool = false;
						String kdke = "<input type=\"text\" name=\"kdke"+i+"\" size=\"15\" readonly=\"readonly\" value=\"\">";
						String sjdke = "<input type=\"text\" name=\"sjdke"+i+"\" size=\"15\"  value=\"\"  onblur=\"calc()\" onchange=\"change('2',"+i+")\">";
						String jzdke = "<input type=\"text\" name=\"jzdke"+i+"\" size=\"15\" readonly=\"readonly\" value=\"\">";
						String bjkz = "<input type=\"hidden\" value=\"\" name=\"bjkz"+i+"\">";
					%>
					<tr>
                      <td class="2-td2-left" >
                        <div align="center">
                          <%=i %>
                        </div>
                      </td>
                       <td class="2-td2-left">
                        <div align="center">
                          <%=tze %>
                        </div>
                      </td>
                      <td  class="2-td2-left">
                        <div id="mx_zsfsdm_epx_visible_1" align="center">
                          <%=kdke %>
                        </div>
                      </td>
                       <td class="2-td2-left">
                        <div align="center">
                          <%=sjdke %>
                        </div>
                      </td>
                      <td  class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="center">
                          <%=jzdke %>
                          <%=bjkz %>
                        </div>
                      </td>                      
                    </tr>
                    <% }%>                       
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
                        	<html:text property="mr_lrrq" readonly="true" style="background-color:#efefef"></html:text>
                        </div>
                      </td>
                      <td width="10%" class="2-td2-t-left">
                        <div align="right">
                          	�������
                        </div>
                      </td>
                      <td width="30%" class="2-td2-t-left">
                        <div align="left">
                        	<html:text property="mr_band" readonly="true" style="background-color:#efefef"></html:text>
                        </div>
                      </td>
                      <td width="10%" class="2-td2-t-left">
                        <div align="right">
                          ¼����
                        </div>
                      </td>
                      <td width="30%" class="2-td2-t-center">
                        <div align="left">
                        	<html:text property="mr_lrr" readonly="true" style="background-color:#efefef"></html:text>
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
                  
            </td>
          </tr>
        </table>
        <br>
        <%@ include file="../../include/footer.jsp" %>
      </html:form>
  </body>

</html>