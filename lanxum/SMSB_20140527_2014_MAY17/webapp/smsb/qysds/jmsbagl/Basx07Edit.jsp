<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant" %>
<%@ page import="com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx07.web.Basx07Form" %>
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
  		Basx07Form basx = (Basx07Form)request.getAttribute("basx07Form");
  	// �������˰�����������
	    if(session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JSZRLXDM) != null)
	    {
	        out.print("var arySelect_lyzl = new Array(");
	        out.print("[\"\",\"\"]");
	        String[][] lyzldm = (String[][])session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JSZRLXDM);
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
		var zlo = document.forms[0].zlo.value.split(";");
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
		for(row_indexo=0;row_indexo<zlo.length;row_indexo++){
			var new_rowo=Table2.insertRow(Table2.rows.length);
			zlnro = zlo[row_indexo].substring(0,zlo[row_indexo].indexOf("|"));
			zlsfkysco = zlo[row_indexo].substring(zlo[row_indexo].length-1,zlo[row_indexo].length);
		    addzlmc(row_indexo,zlnro,new_rowo);
		    if(document.forms[0].czlx.value=="1")addzlxz(row_indexo,row_indexo,new_rowo,"1");
		    if(zlsfkysco == "1"){
		    	addzlan("1",row_indexo,new_rowo);
		    }else{
		    	addzlan("2",row_indexo,new_rowo);
		    }
		}
		_addOptionsToSelect(document.forms[0].lyzlSelect, arySelect_lyzl);
		document.forms[0].lyzlSelect.value = document.all("jszrlxdm").value;
		
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
   	    var zlmc=zlmc.replace(/(^\s*)|(\s*$)/g, "");			
		var len=document.all.jnjwbs;
	    for(i=0;i<len.length;i++){
		 	if(len[i].checked && zlmc != "" && len[i].value == "0"){
			 row_index++;  
	    	 var new_row=Table1.insertRow(Table1.rows.length); 
	    	 addzlmc(row_index,zlmc,new_row);
	    	 if(document.forms[0].czlx.value=="1")addzlxz(row_index,row_index,new_row,"2");
	    	 addzlan("2",row_index,new_row);
	    	 document.getElementById("zlmc").value = "";
	    	 document.getElementById("TABLE1").style.display = "block";
          	 document.getElementById("TABLE2").style.display = "none";
		   }else if(len[i].checked && zlmc != "" && len[i].value == "1"){
			 row_indexo++;  
	    	 var new_rowo=Table2.insertRow(Table2.rows.length); 
	    	 addzlmc(row_indexo,zlmc,new_rowo);
	    	 if(document.forms[0].czlx.value=="1")addzlxz(row_indexo,row_indexo,new_rowo,"2");
	    	 addzlan("2",row_indexo,new_rowo);
	    	 document.getElementById("zlmc").value = "";
	    	 document.getElementById("TABLE1").style.display = "none";
          	 document.getElementById("TABLE2").style.display = "block";
		   }else if(zlmc == "" && len[i].checked && len[i].value == "0"){
		   document.getElementById("zlmc").value = "";
			 alert("����˰���ֻܾ򱱾��еط�˰���Ҫ���ύ�������������ݲ���Ϊ��");
		   }else if(zlmc == "" && len[i].checked && len[i].value == "1") {
		   document.getElementById("zlmc").value = "";
		     alert("����˰���ֻܾ򱱾��еط�˰���Ҫ���ύ�������������ݲ���Ϊ��");
		   }   
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
	    
	    if (document.getElementsByName("jnjwbs")[0].checked) {
          new_col.setAttribute("id",row_index+'_jn');
        }
        if (document.getElementsByName("jnjwbs")[1].checked) {
          new_col.setAttribute("id",row_index+'_jw');
        }
	    
	    
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
	    var len=document.all.jnjwbs;
	    for(i=0;i<len.length;i++){
	    	if(len[i].checked && len[i].value == "0"){
		    var zlstring = "";
    	    var k; 
	        k=Table1.rows(rname).rowIndex;
	        Table1.deleteRow(k);
	        var zl = document.forms[0].zl.value.split(";"); 
	        for(var n=0;n<zl.length;n++){
	    	if(k!=n){
	    		zlstring += zl[n]+"\;";
	    	}
	          }
	        document.all.zl.value=zlstring.substring(0,zlstring.length-1);	
	        document.getElementById("TABLE1").style.display = "block";
            document.getElementById("TABLE2").style.display = "none";
	    	
	    	}
	    	
	    	if(len[i].checked && len[i].value == "1"){
	    	
		     var zlostring = "";
    	     var j; 
	         j=Table2.rows(rname).rowIndex;
	         Table2.deleteRow(j);
	         var zlo = document.forms[0].zlo.value.split(";"); 
	         for(var n=0;n<zlo.length;n++){
	    	   if(i!=n){
	    		zlostring += zlo[n]+"\;";
	    	  }
	        }
	        document.all.zlo.value=zlostring.substring(0,zlostring.length-1);		
	    	document.getElementById("TABLE1").style.display = "none";
          	 document.getElementById("TABLE2").style.display = "block";
	    	}	    	
	    
	    }
    } 
    }
    <%/*���������嵥*/%>
	function zlqd(){
		var zlstring = document.all.zl.value;
		var zl = document.forms[0].zl.value.split(";");
		var i = zl.length+1;
		for(;i<=row_index;i++){
			if(document.getElementById(i+'_jn')!=null){
				var temp=document.getElementById(i+'_jn').innerHTML;
				zlstring+="\;"+temp+"|0";
			}
		}
		
		document.all.zl.value=zlstring;
		
	}
	function zlqdo(){
		var zlstring = document.all.zlo.value;
		var zlo = document.forms[0].zlo.value.split(";");
		var i = zlo.length+1;
		for(;i<=row_index;i++){
			if(document.getElementById(i+'_jw')!=null){
				var temp=document.getElementById(i+'_jw').innerHTML;
				zlstring+="\;"+temp+"|0";
			}
		}
		document.all.zlo.value=zlstring;
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
		var jszrlx = document.all("jszrlx").value;
		if(jszrlx == "") {
			alert("����ת�����Ͳ���Ϊ�գ�");
			document.forms[0].jszrlx.focus();
			return false
		}
		var jszrsd = document.all("jszrsd").value;
		if(jszrsd == "") {
			alert("ȡ�ü���ת�����ò���Ϊ�գ�");
			document.forms[0].jszrsd.focus();
			return false
		}	
		var jszrhtmc = document.all("jszrhtmc").value;
		if(jszrhtmc == "") {
			alert("����ѧ�������������϶��Ǽǵļ���ת�ú�ͬ���Ʋ���Ϊ�գ�");
			document.forms[0].jszrhtmc.focus();
			return false
		}		
			return true;
		
	}
	<%/*��������*/%>
	function jssq()
	{
		if(checkZbxx() && checkYm()&& checkZlRadio()){
			zlqd();
			zlqdo();
			if(window.confirm("�Ƿ���ܸ���˰�˵ļ���˰�������룿")){
				//�������������򱣴��ҳ�����
				document.forms[0].jszrlxdm.value = document.forms[0].jszrlx.value;
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
				    document.forms[0].jszrlxdm.value = document.forms[0].jszrlx.value;
	      			document.forms[0].actionType.value="Jssq";
					document.forms[0].submit();
	      		
      		}
		}
	}	
	
	<%/*�޸ı���*/%>
  	function bg()
	{
		if(checkZbxx() && checkYm()&& checkZlRadio()){
			zlqd();
			if(window.confirm("�Ƿ�ȷ�����?")){
				//�������������򱣴��ҳ�����
				    document.forms[0].jszrlxdm.value = document.forms[0].jszrlx.value;
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
	
function jszylxzl1(){
 if (document.getElementsByName("jnjwbs")[0].checked) {
          //alert(document.getElementsByName("bm")[0].value);
          document.getElementById("TABLE1").style.display = "block";
          document.getElementById("TABLE2").style.display = "none";
        }
        if (document.getElementsByName("jnjwbs")[1].checked) {
          document.getElementById("TABLE1").style.display = "none";
          document.getElementById("TABLE2").style.display = "block";
        }

    }
function changejszrsd(){
	var jszrsd = document.all("jszrsd").value;
	if(jszrsd == ""){
		alert("ȡ�ü���ת�����ò���Ϊ�գ�");
		document.forms[0].jszrsd.focus();
		return false;
	}else{
		flg=0;
		zfgs = 0;
		cmp="0123456789.";
		for (var i=0;i<jszrsd.length;i++){  
			tst=jszrsd.substring(i,i+1);
			if (cmp.indexOf(tst)<0){
				flg++; 
			}else{
				if(tst == "."){
					zfgs++;
				}
			}
		} 
		if (flg!=0 || zfgs > 1){ 
			alert("ȡ�ü���ת�����ø�ʽ����ȷ��");
			document.all.jszrsd.value=""; 
			document.forms[0].jszrsd.focus();
			return false; 
		}
	}
	formate(document.forms[0].jszrsd);
	var jszrsd1 = jszrsd;
	jszrsd = round2(jszrsd1);
	document.all("jszrsd").value = jszrsd;
}    
  </script>
  
  
  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  onload="doInit()">
    <%@ include file="../../include/header.jsp" %> 
     
	  
      <html:form action="/webapp/smsb/qysds/jmsbagl/Basx07Action.do" method="post">
        <html:hidden property="actionType" />
        <html:hidden property="basqwsh" />
        <html:hidden property="basqbh" />
        <html:hidden property="band" />
        <html:hidden property="jsjdm" />
        <html:hidden property="xh" />
        <html:hidden property="jszrlxdm" />
        <html:hidden property="zl" />
        <html:hidden property="zlo" />        
        <html:hidden property="czlx" />
        <html:hidden property="clbs" />        
        <table width="75%" align="center" cellspacing="0" height="480">
          <tr>
            <td class="1-td1" colspan="7" height="30">
              ¼����������ļ���ת�����ü�����ҵ����˰��������
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
                      	&nbsp;<bean:write name="basx07Form" property="basqbh"/>
                      </td>
                      <td  class="2-td2-t-left">
                          <strong>���������</strong>               
                      </td>
                      <td  class="2-td2-t-left">
                      	&nbsp;<bean:write name="basx07Form" property="jsjdm"/>
                      </td>                  
                      <td  class="2-td2-t-left">                       
                          <strong>��˰������</strong>
                      </td>
                      <td width="22%"  class="2-td2-t-center">
                      &nbsp;<bean:write name="basx07Form" property="nsrmc"/>
                      </td>
                    </tr>
                    <tr>
                      <td  class="2-td2-left">                        
                          <strong>����˰����</strong>    
                      </td>
                      <td  width="22%"class="2-td2-left">
                      	&nbsp;<bean:write name="basx07Form" property="zgsws"/>
                      </td>
                      <td  class="2-td2-left">
                          <strong>��������</strong>               
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx07Form" property="jjlx"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>������ҵ</strong>
                      </td>
                      <td width="22%"  class="2-td2-center">
                      	&nbsp;<bean:write name="basx07Form" property="sshy"/>
                      </td>
                    </tr>
                   <tr>
                      <td  class="2-td2-left">
                         <strong>��ϵ��</strong>
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx07Form" property="lxr"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>��ϵ�绰</strong>
                      </td>
                      <td width="22%"  class="2-td2-center" colspan="3">
                       &nbsp;<bean:write name="basx07Form" property="lxdh"/>
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
				    <table width="95%" cellspacing=0 border=0 id="Table2" style="display: none">                   
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
				<input type="hidden" name="mx_mxflag">
				<input type="hidden" name="mx_mxid">
				<tr>
					<td width="40%" class="2-td2-t-left">
					<div align="left">����ת������<font color="red">
					* </font></div>
					</td>
					<td colspan="3" class="2-td2-t-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left"><html:select
						name="basx07Form" styleId="lyzlSelect" property="jszrlx"
						style="width:222px" /></div>
					</td>
				</tr>
				<tr>
					<td width="20%" class="2-td2-left">
					<div align="left">����ת������<font color="red">
					* </font></div>
					</td>
					<td colspan="3" class="2-td2-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left">
					<%
					  if("1".equals(basx.getClbs()) && "0".equals(basx.getJnjwbs())) {
					%>
					  <html:radio property="jnjwbs" value="0" onclick="jszylxzl1()">���ڼ���ת��</html:radio>
					  <html:radio property="jnjwbs" value="1" onclick="jszylxzl1()" disabled="true">���⼼��ת��</html:radio>						  
					<% 
					  } else if("1".equals(basx.getClbs()) && "1".equals(basx.getJnjwbs())){
					%>
					  <html:radio property="jnjwbs" value="0" onclick="jszylxzl1()" disabled="true">���ڼ���ת��</html:radio>
					  <html:radio property="jnjwbs" value="1" onclick="jszylxzl1()">���⼼��ת��</html:radio>						  					
					<% 
					  }else {
					%>
					
					 <html:radio property="jnjwbs" value="0" onclick="jszylxzl1()">���ڼ���ת��</html:radio>
					 <html:radio property="jnjwbs" value="1" onclick="jszylxzl1()">���⼼��ת��</html:radio>					
					<% 
					  }
					%>
					</div>
					</td>
					</td>
				</tr>
				<tr>				
				<tr>
					<td width="20%" class="2-td2-left">
					<div align="left">ȡ�ü���ת������ <font color="#FF0000"> * </font></div>
					</td>
					<td colspan="3" class="2-td2-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left"><html:text
						property="jszrsd" maxlength="17" size="30" tabindex="-1" onchange="changejszrsd()">
					</html:text> Ԫ
					</td>
					</div>
				</tr>
				<tr>
					<td width="20%" class="2-td2-left">
					<div align="left">����ѧ�������������϶��Ǽǵļ���ת�ú�ͬ���� <font color="#FF0000"> * </font></div>
					</td>
					<td colspan="3" class="2-td2-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left"><html:text
						property="jszrhtmc" maxlength="200" size="30" tabindex="-1">
					</html:text>
					</td>
					</div>
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