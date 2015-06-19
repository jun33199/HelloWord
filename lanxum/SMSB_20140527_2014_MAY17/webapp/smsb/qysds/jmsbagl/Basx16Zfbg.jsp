<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant" %>
<%@ page import="com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx16.web.Basx16Form" %>
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
   		Basx16Form basx = (Basx16Form)request.getAttribute("basx16Form");
		String zfyy = basx.getZfyy();
  	// �������˰�����������
	    if(session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JMSBAZFYY) != null)
	    {
	        out.print("var arySelect_zfyy = new Array(");
	        out.print("[\"\",\"\"]");
	        String[][] zfyydm = (String[][])session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JMSBAZFYY);
	        for(int i = 0 ; i < zfyydm.length ; i++) 
	        {	        	
	         	out.print(",[\"" + zfyydm[i][0] + "\",\"" +zfyydm[i][1] +"\"]");
	        }
	        out.println(");");
	    }
   
   		String alertString=(request.getAttribute("ALERT_STR")==null)?"":(String)request.getAttribute("ALERT_STR");
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
		
		_addOptionsToSelect(document.forms[0].zfyy, arySelect_zfyy);
		<%
		if(null != zfyy && !"".equals(zfyy))
		{
		%>
				_selectOptionByValue(document.forms[0].zfyy, "<%=zfyy%>");
		<%
		}
	    %>
	   	var zfyyValue=document.forms[0].zfyy.value;
	   	if(zfyyValue!=null && zfyyValue== '4'){
	   		document.getElementById("qtzfyyDiv").style.display ="block";
	   		
	   	}
		var alertStr ='<%=alertString%>';
		if(alertStr!=null && alertStr.length>0){
			alert(alertStr);
			back();
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
	    	new_col.innerHTML="&nbsp\;&nbsp\;��&nbsp\;&nbsp\;";  
	    }else{
	    	new_col.innerHTML="&nbsp\;&nbsp\;��&nbsp\;&nbsp\;";  
	    }
	    
	}
	
	
	
	<%/*����*/%>
	function back()
	{
		doSubmitForm('BackZfbg');
	}
	
	function qtzfyyControl(){
		var zfyyValue=document.forms[0].zfyy.value;
		var qtzfyy=document.forms[0].qtzfyy;
		if(zfyyValue=='4'){
			document.getElementById("qtzfyyDiv").style.display ="block";
		}else{
			document.getElementById("qtzfyyDiv").style.display ="none";
		}
	}
	
	function destory()
	{
		var zfyyValue=document.forms[0].zfyy.value;
		var zfyyContent;
		if(zfyyValue=='4'){
			zfyyContent=document.forms[0].qtzfyy.value;
		}else{
			zfyyContent=document.forms[0].zfyy.options[document.forms[0].zfyy.selectedIndex].innerText;
		}
		if(zfyyContent.length<=0){
			alert("��ѡ�����д����ԭ��");
			return;
		}else{
			if(window.confirm("�Ƿ񽫴˼���˰������Ϣ���ϣ�")){
			document.forms[0].zfsm.value=zfyyContent;
			document.forms[0].zfbglx.value="0";
			doSubmitForm('Operate');
			}
		}
		
	}
	
	function modify()
	{
	if(window.confirm("�Ƿ�ִ�б��������")){
		document.forms[0].zfbglx.value="1";
		doSubmitForm('Operate');
		}
	}
	
  </script>
  
  
  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  onload="doInit()">
    <%@ include file="../../include/header.jsp" %> 
     
	  
      <html:form action="/webapp/smsb/qysds/jmsbagl/Basx16Action.do" method="post">
        <html:hidden property="actionType" />
        <html:hidden property="basqwsh" />
        <html:hidden property="zl" />
        <html:hidden property="zfsm" />
        <html:hidden property="zfbglx" />
        <table width="75%" align="center" cellspacing="0" height="480">
          <tr>
            <td class="1-td1" colspan="7" height="30">
              ���ϼ������ҵ�⹺��������۾ɻ�̯�����ޱ�������
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
                      	&nbsp;<bean:write name="basx16Form" property="basqbh"/>
                      </td>
                      <td  class="2-td2-t-left">
                          <strong>���������</strong>               
                      </td>
                      <td  class="2-td2-t-left">
                      	&nbsp;<bean:write name="basx16Form" property="jsjdm"/>
                      </td>                  
                      <td  class="2-td2-t-left">                       
                          <strong>��˰������</strong>
                      </td>
                      <td width="22%"  class="2-td2-t-center">
                      &nbsp;<bean:write name="basx16Form" property="nsrmc"/>
                      </td>
                    </tr>
                    <tr>
                      <td  class="2-td2-left">                        
                          <strong>����˰����</strong>    
                      </td>
                      <td  width="22%"class="2-td2-left">
                      	&nbsp;<bean:write name="basx16Form" property="zgsws"/>
                      </td>
                      <td  class="2-td2-left">
                          <strong>��������</strong>               
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx16Form" property="jjlx"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>������ҵ</strong>
                      </td>
                      <td width="22%"  class="2-td2-center">
                      	&nbsp;<bean:write name="basx16Form" property="sshy"/>
                      </td>
                    </tr>
                   <tr>
                      <td  class="2-td2-left">
                         <strong>��ϵ��</strong>
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx16Form" property="lxr"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>��ϵ�绰</strong>
                      </td>
                      <td width="22%"  class="2-td2-center" colspan="3">
                       &nbsp;<bean:write name="basx16Form" property="lxdh"/>
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
                	&nbsp;<bean:write name="basx16Form" property="qsrq"/>
                </td>
                <td class="2-td2-t-left" width="10%">
                  ��ֹʱ��
                </td>
                <td class="2-td2-t-left" width="15%">
                	&nbsp;<bean:write name="basx16Form" property="jzrq"/>
                </td>                
            
                <td class="2-td2-t-left" width="10%">
                  ����˰��
                </td>
                <td class="2-td2-t-left" width="15%">
                	&nbsp;<bean:write name="basx16Form" property="bajmse"/>&nbsp;Ԫ
                </td>
                <td class="2-td2-t-left" width="10%">
                  �������
                </td>
                <td class="2-td2-t-center" width="15%">
                	&nbsp;<bean:write name="basx16Form" property="bajmbl"/>&nbsp;%
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
                		<bean:write name="basx16Form" property="jmszczxqk"/>
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
                  <td>
              </tr>
            </table>
                  
            	  <br>
            		<table class="table-99" cellspacing="0">
                      <tr>
                      <td width="40%" class="2-td2-t-left">
                        <div align="left">
                          ��ҵ���������ƾ֤����<font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td width="60%"  class="2-td2-t-center">
                        <div align="left">
                          <bean:write property="pzmc" name="basx16Form" />
                          
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="2-td2-left">
                        <div align="left">
                          �⹺��������۾ɣ�̯����������<font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td  class="2-td2-center">
                        <div align="left">
                           <bean:write property="sdtxnx" name="basx16Form" />
                          ��
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="2-td2-left">
                        <div align="left">
                          �����۾ɣ�̯���������<font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div align="left" >
                     
                          <bean:write property="jtzjqsnd" name="basx16Form" />&nbsp;��
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="2-td2-left">
                        <div align="left">
                          �����۾ɣ�̯�������ֹ <font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div align="left">
                         <bean:write property="jtzjzznd" name="basx16Form" />&nbsp;��
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td width="40%" class="2-td2-left">
                        <div align="left">
                          ÿ��ɿ۳����۾ɣ�̯������<font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td  class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
                        <bean:write property="kkczje" name="basx16Form" />&nbsp;Ԫ
                          
                      </td>
                    </tr>
                    <tr>
                      <td width="40%" class="2-td2-left">
                        <div align="left">
                          �̶��ʲ��������ʲ���ԭֵ<font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td  class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
                          <bean:write property="gdzcyz" name="basx16Form" />&nbsp;Ԫ
                      </td>
                    </tr>
                    <tr>
                      <td width="40%" class="2-td2-left">
                        <div align="left">
                          �Ѽ����۾ɣ�̯����������<font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td  class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
                          <bean:write property="yjtzjnx" name="basx16Form" />
                          ��
                          
                      </td>
                    </tr>
                    <tr>
                      <td width="40%" class="2-td2-left">
                        <div align="left">
                          �Ѽ�����۾ɣ�̯������ <font color="#FF0000">
                            *
                          </font>                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
                          
                         <bean:write property="yjtzje" name="basx16Form" />&nbsp;Ԫ
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
                  <table class="table-99" cellspacing="0">
                  	<tr>
                  		<td class="2-td2-t-left" width="100">
                  			����ԭ��
                  			<font color="#FF0000">
                            *
                          </font>
                  		</td>
                  		<td class="2-td2-t-center">
                  			<div align="left">
                  			<html:select name="basx16Form" styleId="zfyySelect"  onchange="qtzfyyControl()" property="zfyy"/>
                  		</div>
                  		</td>
                  	</tr>
                  </table>
                <div id="qtzfyyDiv" style="display: none">                 
                   <table class="table-99" cellspacing="0">
                  	<tr>
                  		<td class="2-td2-left" width="100">
                  			��������ԭ��
                  			<font color="#FF0000">
                            *
                          </font>
                  		</td>
                  		<td class="2-td2-center">
                  			<div align="left">                  			
                  			<html:textarea property="qtzfyy" rows="5" cols="70"></html:textarea>
                  		</div>
                  		</td>
                  	</tr>
                  </table>
 				</div>
                  <br/>
                  <table width="100%" border="0" align="center">
                    <tr align="center">      
	                    <td>
	      				  	<a style="cursor:hand" onClick="destory()" onMouseOut="MM_swapImgRestore()"
	                        onMouseOver="MM_swapImage('zf','','<%=static_contextpath%>images//b-zf2.jpg',1)">
	                        <img src="<%=static_contextpath%>images//b-zf1.jpg" name="zf" width="79" height="22" border="0" id="zf">
	                        </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                        <a style="cursor:hand" onClick="modify()" onMouseOut="MM_swapImgRestore()"
	                        onMouseOver="MM_swapImage('bg','','<%=static_contextpath%>images//b-bg2.jpg',1)">
	                        <img src="<%=static_contextpath%>images//b-bg1.jpg" name="bg" width="95" height="22" border="0" id="bg">
	                        </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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