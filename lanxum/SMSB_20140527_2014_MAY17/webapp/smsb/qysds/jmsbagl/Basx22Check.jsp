<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant" %>
<%@ page import="com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx22.web.Basx22Form" %>
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
		
		if(document.getElementsByName("ZRHTXM")[0].checked)
		{
			document.getElementById("disp2").style.display = "inline";
		}
		if(document.getElementsByName("ZRHTXMYH")[0].checked)
		{
			document.getElementById("disp3").style.display = "inline";
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
        <html:hidden property="zl" />
        <html:hidden property="shzl" />
        <html:hidden property="sqzt" />
        <html:hidden property="czlx" />
        <table width="75%" align="center" cellspacing="0" height="480">
          <tr>
            <td class="1-td1" colspan="7" height="30">
              ��˽��ܷ���˾ʵʩ��ͬ��Դ������Ŀ�����ñ�������
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
                  ��ʼʱ��
                </td>
                <td class="2-td2-t-left" width="15%"> 
                  	&nbsp;<bean:write name="basx22Form" property="qsrq"/>
                </td>
                <td class="2-td2-t-left" width="10%">
                  ��ֹʱ��
                </td>
                <td class="2-td2-t-left" width="15%">
                	&nbsp;<bean:write name="basx22Form" property="jzrq"/>
                </td>                
            
                <td class="2-td2-t-left" width="10%">
                  ����˰��
                </td>
                <td class="2-td2-t-left" width="15%">
                  	&nbsp;<bean:write name="basx22Form" property="bajmse"/>
                </td>
                <td class="2-td2-t-left" width="10%">
                  �������
                </td>
                <td class="2-td2-t-center" width="15%">
	                &nbsp;<bean:write name="basx22Form" property="bajmbl"/>
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
                		<bean:write name="basx22Form" property="jmszczxqk"/>
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
                  <td>
              </tr>
            </table>
                  
            	  </br>
			<table class="table-99" cellspacing="0">
				<tr>
					<td width="20%" class="2-td2-t-left" colspan="2">
					<div align="left">��Ŀ����<font color="red">
					* </font></div>
					</td>
					<td class="2-td2-t-center" width="10%">
					<div id="mx_zsfsdm_epx_visible_1" align="left"><bean:write
						name="basx22Form" property="jnjpjsgzxmmc" /></div>
					</td>
				</tr>
				<tr>
					<td width="20%" class="2-td2-left" colspan="2">
					<div align="left">ȡ�õ�һ����������֤����������<font color="red">
					* </font></div>
					</td>
					<td class="2-td2-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left"><bean:write
						name="basx22Form" property="dybzlmc" /></div>
					</td>
				</tr>
				<tr>
					<td width="20%" class="2-td2-left" colspan="2">
					<div align="left">ȡ�õ�һ��������Ӫ�����ʱ��<font color="red">
					* </font></div>
					</td>
					<td class="2-td2-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left"><bean:write
						name="basx22Form" property="dybrq" />&nbsp;��</div>
					</td>
				</tr>
				<tr>
					<td width="20%" class="2-td2-left" colspan="2">
					<div align="left">�������<font color="red">
					* </font></div>
					</td>
					<td class="2-td2-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left">�ӣ� <bean:write
						name="basx22Form" property="mzqsnd" /> �������� <bean:write
						name="basx22Form" property="mzzznd" /> ����ֹ</div>
					</td>
				</tr>
				<tr>
					<td width="20%" class="2-td2-left" colspan="2">
					<div align="left">�����������<font color="red">
					* </font></div>
					</td>
					<td class="2-td2-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left">�ӣ� <bean:write
						name="basx22Form" property="jbzsqsnd" /> �������� <bean:write
						name="basx22Form" property="jbzszznd" /> ����ֹ</div>
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
            </td>
          </tr>
        </table>
        <br>
        <%@ include file="../../include/footer.jsp" %>
      </html:form>
  </body>

</html>