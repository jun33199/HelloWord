<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant" %>
<%@ page import="com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx13a.web.Basx13AForm" %>
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
	<%/*��ʼ��*/%> 
	function doInit(){
	    //��ʼ������
		var zl = document.forms[0].zl.value.split(";");
		for(row_index=0;row_index<zl.length;row_index++){
			var new_row=Table1.insertRow(Table1.rows.length);
			zlnr = zl[row_index].substring(0,zl[row_index].indexOf("|"));
			xh = zl[row_index].substring(zl[row_index].indexOf("|")+1,zl[row_index].length);
		    addzlmc(row_index,zlnr,new_row);
		    addzlan(xh,row_index,new_row);
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
	function addzlan(xh,row_index,new_row){
	    var new_col=new_row.insertCell(1);
	    if(row_index == 0){
	    	new_col.className="2-td2-t-center";
	    }else{
	    	new_col.className="2-td2-center";
	    }
	    var dxk = "<input type=\"radio\" name="+xh+" value=\"0\">��</input>&nbsp\;<input type=\"radio\"  name="+xh+" value=\"1\" checked>��</input>";
	    new_col.innerHTML=dxk;  
	    
	}
	
	<%/*��������ʱ�ж��Ƿ���ѡ�񣬾ܾ�����ʱ�ж��Ƿ�ȫ��δѡ��*/%>
	function checkFXK(sqzt){
		var zlqd = "";
		var xzlx = 0;
		var zl = document.forms[0].zl.value.split(";");
		var i=0;
		for(;i<zl.length;i++){
			xh = zl[i].substring(zl[i].indexOf("|")+1,zl[i].length);
			if(checkRadio(document.all(xh))){
				xzlx += 1 ;
				zlqd += xh+",1\;";
			}else{
				zlqd += xh+",2\;";
			}
		}
		//�ж�����������룬�������嵥����ȫѡ��
	    if(sqzt == 4){
	    	if(xzlx != i){
	    		alert("�����嵥����ѡ�����ȫ��Ϊ�вſ��Խ��н������룡");
	    		return false;
	    	}
	    }
	    document.forms[0].shzl.value = zlqd.substring(0,zlqd.length-1);
		return true;
	}
	<%/*Ч�鵥ѡ�����ѡ���Ƿ���true��ѡ��񷵻�false*/%>
	function checkRadio(obj){
	for(var i=0;i<obj.length;j++){
		if(obj[i].checked == true && obj[i].value == "0")
		{
			return true;
		}else{
			return false;
		}
	}  
}
	<%/*���ܾܾ�����*/%>
	function check(sqzt)
	{
		//�������嵥�Ƿ�ѡ�����Ч��
		if(checkFXK(sqzt)){
			var infor="";
			if(sqzt=="4"){
				infor="�Ƿ���ܸ���˰�˵ļ���˰�������룿";
			}else if(sqzt=="5"){
				infor="�Ƿ�ܾ�����˰�˵ļ���˰�������룿";
			}
			if(window.confirm(infor)){
				document.forms[0].sqzt.value = sqzt;
	      		if(window.confirm("�Ƿ���ת�������ӡԤ��ҳ��")){
	      			document.forms[0].actionType.value="WSShPrint";
					document.forms[0].submit();
	      		}else{
	      			document.forms[0].actionType.value="Check";
					document.forms[0].submit();
	      		}
      		}
		}
	}
	
	<%/*����*/%>
	function back()
	{
		document.forms[0].actionType.value="Back";
		document.forms[0].submit();
	}
	
  </script>
  
  
  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  onload="doInit()">
    <%@ include file="../../include/header.jsp" %> 
     
	  
      <html:form action="/webapp/smsb/qysds/jmsbagl/Basx13AAction.do" method="post">
        <html:hidden property="actionType" />
        <html:hidden property="basqwsh" />
        <html:hidden property="zl" />
        <html:hidden property="shzl" />
        <html:hidden property="sqzt" />
        <html:hidden property="czlx" />
        <table width="75%" align="center" cellspacing="0" height="480">
          <tr>
            <td class="1-td1" colspan="7" height="30">
              ��˴�ҵͶ����ҵͶ�ʡ��ֿ�Ӧ��˰���ö��ʸ񱸰�����
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
                      	&nbsp;<bean:write name="basx13AForm" property="basqbh"/>
                      </td>
                      <td  class="2-td2-t-left">
                          <strong>���������</strong>               
                      </td>
                      <td  class="2-td2-t-left">
                      	&nbsp;<bean:write name="basx13AForm" property="jsjdm"/>
                      </td>                  
                      <td  class="2-td2-t-left">                       
                          <strong>��˰������</strong>
                      </td>
                      <td width="22%"  class="2-td2-t-center">
                      &nbsp;<bean:write name="basx13AForm" property="nsrmc"/>
                      </td>
                    </tr>
                    <tr>
                      <td  class="2-td2-left">                        
                          <strong>����˰����</strong>    
                      </td>
                      <td  width="22%"class="2-td2-left">
                      	&nbsp;<bean:write name="basx13AForm" property="zgsws"/>
                      </td>
                      <td  class="2-td2-left">
                          <strong>��������</strong>               
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx13AForm" property="jjlx"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>������ҵ</strong>
                      </td>
                      <td width="22%"  class="2-td2-center">
                      	&nbsp;<bean:write name="basx13AForm" property="sshy"/>
                      </td>
                    </tr>
                   <tr>
                      <td  class="2-td2-left">
                         <strong>��ϵ��</strong>
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx13AForm" property="lxr"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>��ϵ�绰</strong>
                      </td>
                      <td width="22%"  class="2-td2-center" colspan="3">
                       &nbsp;<bean:write name="basx13AForm" property="lxdh"/>
                      </td>
                    </tr>
                  </table>
                  
                  
                  </br>
                  <table cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">            
              <tr>
                <td class="2-td2-t-left" width="10%">
                  ��ʼʱ��
                </td>
                <td class="2-td2-t-left" width="15%"> 
                  	&nbsp;<bean:write name="basx13AForm" property="qsrq"/>
                </td>
                <td class="2-td2-t-left" width="10%">
                  ��ֹʱ��
                </td>
                <td class="2-td2-t-left" width="15%">
                	&nbsp;<bean:write name="basx13AForm" property="jzrq"/>
                </td>                
            
                <td class="2-td2-t-left" width="10%">
                  ����˰��
                </td>
                <td class="2-td2-t-left" width="15%">
                  	&nbsp;<bean:write name="basx13AForm" property="bajmse"/>
                </td>
                <td class="2-td2-t-left" width="10%">
                  �������
                </td>
                <td class="2-td2-t-center" width="15%">
	                &nbsp;<bean:write name="basx13AForm" property="bajmbl"/>
                </td>
                
              </tr>
              <tr>
                <td class="2-td2-left">
                  ����˰����
                  <br/>
                  ִ�������
                </td>
                <td class="2-td2-center" colspan="7">
                	<div align="left">
                		&nbsp;&nbsp;&nbsp;
                		<bean:write name="basx13AForm" property="jmszczxqk"/>
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
                  
                  </br>
                  <td>
              </tr>
            </table>
                  
            	  </br>
            		<table class="table-99" cellspacing="0">
                    <tr>
                      <td width="50%" class="2-td2-t-left">
                        <div align="left">
                          ��С���¼�����ҵ����<font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td class="2-td2-t-center">
                        <div align="left">
                        <bean:write name="basx13AForm" property="gxjsqylymc"/>
                        
                        </div>
                      </td>
                    </tr>
                    
                    <tr>
                      <td width="50%" class="2-td2-left">
                        <div align="left">
                          ����Ȩ������׼�϶��Ĵ�ҵͶ����ҵ֤����ļ�������
                          <font color="#FF0000">
                            *
                          </font>                          
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div align="left">
						<bean:write name="basx13AForm" property="ctqyzsjbh"/>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="2-td2-left">
                        <div align="left">
                          ��Ͷ�ʵ���С���¼�����ҵ�ĸ��¼�����ҵ���Ƽ�֤����
                          <font color="#FF0000">
                            *
                          </font>                          
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
                       
                       <bean:write name="basx13AForm" property="btzqymcjzsbh"/>
                        </div>
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
	      				  	<a style="cursor:hand" onClick="check('4')" onMouseOut="MM_swapImgRestore()"
	                        onMouseOver="MM_swapImage('jishoushenqing','','<%=static_contextpath%>images//b_jssq2.jpg',1)">
	                        <img src="<%=static_contextpath%>images//b_jssq1.jpg" name="jishoushenqing" width="79" height="22" border="0" id="jishoushenqing">
	                        </a>
	                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                        <a style="cursor:hand" onClick="check('5')" onMouseOut="MM_swapImgRestore()"
	                        onMouseOver="MM_swapImage('jujueshenqing','','<%=static_contextpath%>images//b_jjsq2.jpg',1)">
	                        <img src="<%=static_contextpath%>images//b_jjsq1.jpg" name="jujueshenqing" width="79" height="22" border="0" id="jujueshenqing">
	                        </a>
	                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                        <a style="cursor:hand" onClick="back()" onMouseOut="MM_swapImgRestore()"
	                        onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images//fanhui2.jpg',1)">
	                        <img src="<%=static_contextpath%>images//fanhui1.jpg" name="fanhui" width="79" height="22" border="0" id="fanhui">
	                        </a>
	                    </td>
                    </tr>
                  </table> 
                  <br>
          
        <br>
        <%@ include file="../../include/footer.jsp" %>
      </html:form>
  </body>

</html>