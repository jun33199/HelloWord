<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant" %>
<%@ page import="com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx13b.web.Basx13BForm" %>
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
  <%Basx13BForm basx = (Basx13BForm)request.getAttribute("basx13BForm");
  //ȡ�ñ������
	String allTze[] = basx.getTze().split(";");
	String allKdke[] = basx.getKdke().split(";");
	String allSjdke[] = basx.getSjdke().split(";");
	String allJzdke[] = basx.getJzdke().split(";");
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
		
		var zfsm="<bean:write name="basx13BForm" property="zfsm"/>";
		if(zfsm!=null && zfsm.length>0){
			document.getElementById("zfsmDiv").style.display ="block";
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
	
	<%/*��������*/%>
	function wsdy()
	{
		doSubmitForm('Print');
	}
	
	<%/*����*/%>
	function back()
	{
		doSubmitForm('Back');
	}
	
  </script>
  
  
  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  onload="doInit()">
    <%@ include file="../../include/header.jsp" %> 
     
	  
      <html:form action="/webapp/smsb/qysds/jmsbagl/Basx13BAction.do" method="post">
        <html:hidden property="actionType" />
        <html:hidden property="basqwsh" />
        <html:hidden property="zl" />
        <html:hidden property="czlx" />
        <table width="75%" align="center" cellspacing="0" height="480">
          <tr>
            <td class="1-td1" colspan="7" height="30">
              �鿴��ҵͶ����ҵ�ֿ�Ӧ��˰���ö������ 
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
                      	&nbsp;<bean:write name="basx13BForm" property="basqbh"/>
                      </td>
                      <td  class="2-td2-t-left">
                          <strong>���������</strong>               
                      </td>
                      <td  class="2-td2-t-left">
                      	&nbsp;<bean:write name="basx13BForm" property="jsjdm"/>
                      </td>                  
                      <td  class="2-td2-t-left">                       
                          <strong>��˰������</strong>
                      </td>
                      <td width="22%"  class="2-td2-t-center">
                      &nbsp;<bean:write name="basx13BForm" property="nsrmc"/>
                      </td>
                    </tr>
                    <tr>
                      <td  class="2-td2-left">                        
                          <strong>����˰����</strong>    
                      </td>
                      <td  width="22%"class="2-td2-left">
                      	&nbsp;<bean:write name="basx13BForm" property="zgsws"/>
                      </td>
                      <td  class="2-td2-left">
                          <strong>��������</strong>               
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx13BForm" property="jjlx"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>������ҵ</strong>
                      </td>
                      <td width="22%"  class="2-td2-center">
                      	&nbsp;<bean:write name="basx13BForm" property="sshy"/>
                      </td>
                    </tr>
                   <tr>
                      <td  class="2-td2-left">
                         <strong>��ϵ��</strong>
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx13BForm" property="lxr"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>��ϵ�绰</strong>
                      </td>
                      <td width="22%"  class="2-td2-center" colspan="3">
                       &nbsp;<bean:write name="basx13BForm" property="lxdh"/>
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
                	&nbsp;<bean:write name="basx13BForm" property="qsrq"/>
                </td>
                <td class="2-td2-t-left" width="10%">
                  ��ֹʱ��
                </td>
                <td class="2-td2-t-left" width="15%">
                	&nbsp;<bean:write name="basx13BForm" property="jzrq"/>
                </td>                
            
                <td class="2-td2-t-left" width="10%">
                  ����˰��
                </td>
                <td class="2-td2-t-left" width="15%">
                	&nbsp;<bean:write name="basx13BForm" property="bajmse"/>&nbsp;Ԫ
                </td>
                <td class="2-td2-t-left" width="10%">
                  �������
                </td>
                <td class="2-td2-t-center" width="15%">
                	&nbsp;<bean:write name="basx13BForm" property="bajmbl"/>&nbsp;%
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
                		<bean:write name="basx13BForm" property="jmszczxqk"/>
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
                      <td rowspan="5" width="30%" class="2-td2-t-left">
                        <div align="center">
                          ��Ͷ����ҵ
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td width="30%"  class="2-td2-t-left">
                        <div align="left">
                          ���������
                        </div>
                      </td>
                      <td class="2-td2-t-center">
                        <div align="left">
                        	<bean:write name="basx13BForm" property="btzqyjsjdm"/>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="2-td2-left">
                        <div align="left">
                          ˰��Ǽ�֤��
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div align="left">
                        	<bean:write name="basx13BForm" property="btzqyswdjzh"/>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="2-td2-left">
                        <div align="left">
                          ��˰������
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div align="left">
                        	<bean:write name="basx13BForm" property="btzqymc"/>
                        </div>
                      </td>
                    </tr>
                  	 <tr>
                      <td width="10%" class="2-td2-left">
                        <div align="left">
                          ��С���¼�����ҵ����
                        </div>
                      </td>
                      <td class="2-td2-center">
                      	<div align="left">
	                        <bean:write name="basx13BForm" property="gxjslymc"/>
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
                          Ͷ�����
                      </td>
                      <td class="2-td2-left" width="11%">
                          ��������Ͷ�ʶ�
                      </td>
                      <td class="2-td2-left" width="11%">
                          ����ɵֿ�Ӧ��˰���ö�
                      </td>
                      <td class="2-td2-left" width="11%">
                          ����ʵ�ʵֿ�Ӧ��˰���ö�
                      </td>
                      <td class="2-td2-center" width="11%">
                          ��ת�Ժ���ȵֿ�Ӧ��˰���ö�
                      </td>                                                                                                        
                    <% int band = Integer.parseInt(basx.getMr_band());
					if(band<2009){
						//band = 2009;
					}
					int n = 0;
					for(int i = 2006;i<=band;i++){
					%>
					<tr>
                      <td class="2-td2-left" >
                        <div align="center">
                          <%=i %>
                        </div>
                      </td>
                       <td class="2-td2-left">
                        <div align="center">
                          <%=allTze[n] %>
                        </div>
                      </td>
                      <td  class="2-td2-left">
                        <div id="mx_zsfsdm_epx_visible_1" align="center">
                          <%=allKdke[n]%>
                        </div>
                      </td>
                       <td class="2-td2-left">
                        <div align="center">
                          <%=allSjdke[n] %>
                        </div>
                      </td>
                      <td  class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="center">
                          <%=allJzdke[n] %>
                        </div>
                      </td>                      
                    </tr>
                    <% n++;}%>                       
                  </table>
                 <br/>
                 <div id="zfsmDiv" style="display: none">        
                 <table class="table-99" cellspacing="0">
                    <tr>
                      <td width="20%" class="2-td2-t-left">
                        <div align="center">
                          	����ԭ��
                        </div>
                      </td>
                      <td width="80%" class="2-td2-t-center">
                        <div align="left">
                        	<bean:write name="basx13BForm" property="zfsm"/>
                        </div>
                      </td>
                    </tr>
                  </table>
                  <br/>
                  </div>
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
	      				  	<a style="cursor:hand" onClick="wsdy()" onMouseOut="MM_swapImgRestore()"
	                        onMouseOver="MM_swapImage('dayinws','','<%=static_contextpath%>images//b_scdyws2.jpg',1)">
	                        <img src="<%=static_contextpath%>images//b_scdyws1.jpg" name="dayinws" width="95" height="22" border="0" id="dayinws">
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
            </td>
          </tr>
        </table>
        <br>
        <%@ include file="../../include/footer.jsp" %>
      </html:form>
  </body>

</html>