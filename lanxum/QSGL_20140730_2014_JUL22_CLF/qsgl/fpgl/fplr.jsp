<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.ActionUtil"%>

<HTML>

<HEAD><TITLE>��Ʊ�Ŷ�¼��</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/judge.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/calculate.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>


<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="��Ʊ����&gt;��Ʊ�Ŷ�¼��"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<html:form action="/fpgl/fplr.do">
<html:hidden property="operationType"/>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <table align="center" cellspacing="0" class="table-99">
	  <TBODY>
        <tr>
          <td class="1-td1">��Ʊ�Ŷ�¼��</a></td>
        </tr>
        <tr>
          <td class="1-td2"> <br>
            <table width="75%" border="0" cellspacing="0" class="table-99" id='TABLE_LIST'>
              <tr >
                <td width="10%" class="2-td1-left">���</td>
                <td width="25%" class="2-td1-left">��Ʊ����</td>
                <td width="15%" class="2-td1-left">��Ʊ��ʼ����</td>
                <td width="15%" class="2-td1-left">��Ʊ��ֹ����</td>
                <td width="15%" class="2-td1-center">����</td>
              </tr>
            </table>
            
            <br>
            
            <hr width="99%" class="hr1" size=1>
			<table width="75%" border="0" cellspacing="0" class="table-99">
              <tr>
                <td width="22%" class="2-td2-t-left">
                  <div align="right">¼����</div></td>
                <td width="31%" class="2-td2-t-left">
                  <div align="left">
                    <bean:write name="FplrForm" property="lrr" />&nbsp;
                </div></td>
                <td width="16%" class="2-td2-t-left">
                  <div align="right">¼������</div></td>
                <td width="31%" class="2-td2-t-center">
                  <div align="left">
                    <bean:write name="FplrForm" property="lrrq" />&nbsp;
                </div></td>
              </tr>
            </table>     
            
            <br>

      <DIV align=center>
      	<img onClick='onSave(TABLE_LIST.rows.length-2)' alt="����" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image121','','<%=static_file%>images/baocun2.jpg',1)"  src="<%=static_file%>images/baocun1.jpg" name="Image121" width="79" height="22" border="0" id="Image121" >
      	<img onClick="onAdd(TABLE_LIST.rows.length-2);" alt="����" style="cursor:hand" onMouseOver="MM_swapImage('add','','<%=static_file%>images/xinzeng2.jpg',1)" onMouseOut="MM_swapImgRestore()" id="add" src="<%=static_file%>images/xinzeng1.jpg" width="79" height="22"  >
        <img onClick="deleteRow()" alt="ɾ��" style="cursor:hand" onMouseOver="MM_swapImage('del','','<%=static_file%>images/shanchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" id="del" src="<%=static_file%>images/shanchu1.jpg" width="79" height="22"  >
      	<img onclick="doSubmitForm('Return');" onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg" name="back" width="79" height="22" id="tuichu1" alt="�˳�" style="cursor:hand">
      </DIV>
      <BR>
</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
<script language=JavaScript type='text/JavaScript'>

function doSubmitForm(operationType)
{
    document.all.operationType.value = operationType;
    document.forms[0].submit();
}

//���Ŷ��Ƿ��ཻ
//���ཻ���� true
//�ཻ����false
function checkHDCross()
{
	var fpzldmArr = new Array();
    var qs = new Array();
    var jz = new Array();
    
     //��ȡ��Ʊ�������    
    var tab = document.getElementById('TABLE_LIST');
    for(var i =1 ; i < tab.rows.length; i++)
    {
    	var text=tab.rows[i].cells[1].childNodes[0].options[tab.rows[i].cells[1].childNodes[0].selectedIndex].value;
    	fpzldmArr.push(text);
    }
		
    if(document.forms[0].fpqzhm==null)
        return true;

    var len = document.forms[0].fpqzhm.length;
    if(len==null)
        len=1;
        
    for(var i=0; i<len; i++)
    {
        if(document.forms[0].fpqzhm[i]==null)
            qs.push(document.all.fpqzhm.value);
        else
            qs.push(document.forms[0].fpqzhm[i].value);

        if(document.forms[0].fpjzhm[i]==null)
            jz.push(document.all.fpjzhm.value);
        else
            jz.push(document.forms[0].fpjzhm[i].value);
    }

    for(var i=0; i<len; i++)
    {
        var m = 0;
        //remove������һ��ֵ
        var qsh = qs[0];
        qs.shift();
        var jzh = jz[0];
        jz.shift();
        var dm = fpzldmArr[0];
        fpzldmArr.shift();

        for(var m=0; m<qs.length; m++)
        {
        	if(dm == fpzldmArr[m])
        	{
	        	if(((qsh*1>=qs[m]*1)&&(qsh*1<=jz[m]*1)) || ((jzh*1>=qs[m]*1)&&(jzh*1<=jz[m]*1)) || ((qsh*1>=qs[m]*1)&&(jzh*1<=jz[m]*1)) || ((qsh*1<=qs[m]*1)&&(jzh*1>=jz[m]*1)))
	            {
	                break;
	            }
        	}
        }
        if(m<qs.length )
        {
            alert("�����" + (m+i+2) + "�����ֹ�¼��ĺŶβ����ཻ��");
            return false;
        }
    }
    return true;
}

function checkMX(n)
{
	var tab = document.getElementById('TABLE_LIST');
    for(var i=0; i<=parseInt(n); i++)
    {
        //��Ʊ����
        if(document.forms[0].fpzldm.options!=null && document.forms[0].fpzldm.options[document.forms[0].fpzldm.selectedIndex].value=="")
        {
            alert("��ѡ����Ӧ�ķ�Ʊ���࣡");
            document.forms[0].fpzldm.focus();
            return false;
        }
        if(document.forms[0].fpzldm[i].options!=null && document.forms[0].fpzldm[i].options[document.forms[0].fpzldm[i].selectedIndex].value=="")
        {
            alert("��ѡ����Ӧ�ķ�Ʊ���࣡");
            document.forms[0].fpzldm[i].focus();
            return false;
        }
        
        //��ʼ����
        var fpqzhm =  document.forms[0].fpqzhm[i];
        if(fpqzhm==null)
            var fpqzhm = document.forms[0].fpqzhm;
        if(!checkFormNumber(fpqzhm,"��ʼ����"))
        {
            fpqzhm.focus();
            return false;
        }

        //����
        var sl =  document.forms[0].sl[i];
        if(sl==null)
            var sl = document.forms[0].sl;
        if(!checkFormNumber(sl,"����"))
        {
            sl.focus();
            return false;
        }
        if(sl.value == 0)
        {
        	alert("��������Ϊ��0��! ");
            sl.focus();
            return false;
        }
        
        //��Ʊ�������
        var fpzldms=tab.rows[i+1].cells[1].childNodes[0].options[tab.rows[i+1].cells[1].childNodes[0].selectedIndex].value;
        //��Ʊ��ʼ����
        var fpqzhms=tab.rows[i+1].cells[2].childNodes[0].value;
        //��õ�ǰѭ������ʼ����Ķ��󣬲�����У�飬�������Ƿ�ϸ�
        var tempFpqshmOBJ=i+1;
        
        //alert("tempFpqshmOBJ:"+tempFpqshmOBJ+" fpzldms:"+fpzldms+" fpqzhm:"+fpqzhms);
        var res = checkFphmCD(tempFpqshmOBJ,fpzldms,fpqzhms,tempFpqshmOBJ);
		
		if(!res)
		{
			tempFpqshmOBJ.focus();
			return false;
		}
    }
    if(!checkHDCross())
    {
         return false;
    }
    return true;
}

//����������ֵ���ȷ��
function checkFormNumber(ob,name)
{
	ob.required = "true";//��֤�ǿ�
	ob.captionName = name;
	if(!checker_checkInput(ob))
	{
		return false;
	}
	else if
	(!checkNumber(ob.value,null,null))
	{//��֤������ȷ��
		alert("��"+ob.captionName+"��������ȷ���֣�");
		ob.select();
		return false;
	}
	else
	{
		return true;
	}
}

//����Ϊ����ʼ���룬��������ֹ���루��Ҫ�����Ķ��󣩣�λ������������ݣ�
//�ĸ�������Ϊ����
//onkeyup�¼���������countFPjzhm(fpqzhm,sl,fpjzhm,ws)
function countFPjzhm(fpqzhm,sl,fpjzhm,ws)
{
    fpjzhm.innerText = fpqzhm.value*1 + sl.value*1 - 1;
    if(fpjzhm.innerText=='NaN')
        fpjzhm.innerText=0;
	
    addZero(fpjzhm,ws);
}

//fpjzhm��ֹ����Ϊ��ǰ�����Ľ�ֹ�����label����
function addZero(fpjzhm,ws)
{	
    if(fpjzhm.innerText.length<=parseInt(ws))
    {
        var len = parseInt(ws) - parseInt(fpjzhm.innerText.length);
        for(i=0;i<len;i++)
        {
            fpjzhm.innerText ="0"+fpjzhm.innerText;
        }
    }
    else
    {
        alert("��ʼ���롢��ֹ�����λ�����ܴ���" + ws + "��");
        return false;
    }
    return fpjzhm;
}

//�����ֹ����
function getFpjzhm()
{
    var label_fpjzhm = document.all.label_fpjzhm; //��ֹ����
    var fpqzhm = document.forms[0].fpqzhm; //��ʼ����
    var sl = document.forms[0].sl; //����
    var len = fpqzhm.length;
    var obj = window.event.srcElement;
    var index = parseInt(obj.defaultValue) - 1; //����ֵ

    if(len!=null)
    {
        var label_fpjzhm = document.all.label_fpjzhm[index]; //��ֹ����
        var fpqzhm = document.forms[0].fpqzhm[index]; //��ʼ����
        var sl = document.forms[0].sl[index]; //����
    }
    
    var ws = fpqzhm.value.length;
    countFPjzhm(fpqzhm,sl,label_fpjzhm,ws);

    var fpjzhm = document.forms[0].fpjzhm[index];
    if(fpjzhm==null)
        fpjzhm = document.forms[0].fpjzhm;

    fpjzhm.value = label_fpjzhm.innerText;
}

</script>
<SCRIPT language="JavaScript">
    //����TABLE_LIST�Ĳ���
    var PZZL_ARRAY = new Array();
    var Info;
    var currentRow;

    if(TABLE_LIST!=null)
        InitialPage();

    function InitialPage()
    {
		currentRow=0;
		addRow(false);
	}

	 function addRow(boolNewRecord)
    {
		var otr=TABLE_LIST.insertRow();
		otr.bgColor="#FFFFFF";

		rowlength = TABLE_LIST.rows.length-1;
        column=TABLE_LIST.rows.length-1;
		//��1�� ���
		var xh_Cell=otr.insertCell();
		xh_Cell.align="center";
		xh_Cell.innerText=rowlength;
		xh_Cell.onclick = selectRow;
		xh_Cell.onmouseover=domouseover;
		xh_Cell.onmouseout=domouseout;
		xh_Cell.className="2-td2-left";

        
        //��2�� ��Ʊ����
       var cz_cell = otr.insertCell();
        cz_cell.align="center";
        objSelect = document.createElement("<select name='fpzldm' style='width:165px'></select>");
        selectedIndex = 0;
        <logic:iterate id="dto" name="FplrForm" property="fpzlList" indexId="index">
        	var op = new Option();
        	op.value='<bean:write name="dto" property="fpzldm"/>';
			op.innerText='<bean:write name="dto" property="fpzlmc"/>';
			objSelect.insertBefore(op);
		</logic:iterate>
		objSelect.selectedIndex=selectedIndex;
        cz_cell.appendChild(objSelect);

		//��3�� ��ʼ����
		var qshm_cell = otr.insertCell();
        qshm_cell.align="center";
        objInput = document.createElement("<INPUT type='text' border=0 size=10 name='fpqzhm' onchange='getFphmcd(this,"+rowlength+")'>");
        objInput.defaultValue = rowlength;
        objInput.onfocus=selectRow;
        objInput.onkeyup=getFpjzhm;
        qshm_cell.appendChild(objInput);

		//��4�� ��������
		var jzhm_cell = otr.insertCell();
		jzhm_cell.align="center";
		objInput = document.createElement("<label id='label_fpjzhm'></label>");
		objInput.innerText = " ";
		jzhm_cell.appendChild(objInput);
		objInput = document.createElement("<INPUT type='hidden' border=0 size=10 name='fpjzhm'>");
		jzhm_cell.appendChild(objInput);

		//��5�� ����
		var sl_cell = otr.insertCell();
		sl_cell.align="center";
		objInput = document.createElement("<INPUT border=0 size=8 name='sl'>");
        objInput.defaultValue = rowlength;
        objInput.onfocus=selectRow;
		objInput.onkeyup=getFpjzhm;
		sl_cell.appendChild(objInput);

        selectRow(rowlength);
	}

    function deleteRow()
    {
			if (currentRow<=0) return;
			i=currentRow-1;
			TABLE_LIST.deleteRow(currentRow);
			if (TABLE_LIST.rows.length==1)
			{
				currentRow=0;
				return;
			}
			for (i=TABLE_LIST.rows.length-1;i>0;i--)
			{
				var obj = TABLE_LIST.rows(i).cells(0);
				obj.innerText=i;

                var len = document.forms[0].fpqzhm.length;
                if(len==null)
                {
                    document.forms[0].fpqzhm.defaultValue=1;
                    document.forms[0].sl.defaultValue=1;
                }
                else
                {
                    document.forms[0].fpqzhm[i-1].defaultValue=i;
                    document.forms[0].sl[i-1].defaultValue=i;
                }
			}

			var i=(currentRow<TABLE_LIST.rows.length?currentRow:TABLE_LIST.rows.length-1);
			selectRow(i);
			resizeTable();
    }

    function resizeRow(rowIndex)
    {
			for(j=1;j<TABLE_LIST.rows[rowIndex].cells.length;j++)
			{
				var obj=TABLE_LIST.rows(rowIndex).cells(j).children(0);
				if ((obj==null)||(typeof(obj)=="undefined"))
				{
					continue;
				}
			}
    }

    function resizeTable()
    {
			for (i=1;i< TABLE_LIST.rows.length;i++)	
			{
				resizeRow(i);
			}
    }

    function selectRow(index)
    {
    	if ((currentRow>0)&&(currentRow<TABLE_LIST.rows.length))
    	{
    		for(j=1;j<TABLE_LIST.rows.length;j++)
    		{
    			TABLE_LIST.rows[j].cells[0].style.background="#ECF2F4";
    			for (i=1;i<TABLE_LIST.rows[j].cells.length;i++)
    			{
    				TABLE_LIST.rows[j].cells[i].className="2-td2-left";
    				TABLE_LIST.rows[j].cells[i].style.background="#ECF2F4";
    			}
    			TABLE_LIST.rows[j].cells[TABLE_LIST.rows[j].cells.length-1].className="2-td2-center";
    		}
    	}

		if (index==currentRow)
		{
			return;
		}

		if (index == null)	
		{
			var obj = window.event.srcElement;
			currentRow = obj.innerText*1;  //����ת��
            if(obj.innerText=='' || obj.innerText==null)
                  currentRow = obj.defaultValue*1;  //����ת��
		}	
		else
		{
			currentRow = index;
		}

		TABLE_LIST.rows[currentRow].cells[0].style.background='#aabbcc';
		for (var i=1;i<TABLE_LIST.rows[currentRow].cells.length;i++)
		{
			TABLE_LIST.rows[currentRow].cells[i].style.background='#aabbcc';
		}
		//updateCard(index);
	}

	function domouseover()
	{
		this.style.cursor="hand";
	}

	function domouseout()
	{
		this.style.cursor="default";
	}
	
	function onAdd(n)
	{
    	if(checkMX(n))
        	addRow(true);
	}

	function onSave(n)
	{
    	if(checkMX(n))
    	if(confirm("��Ʊ�Ŷ�¼����޷������˿⣬����ϸ�˶ԣ�"))
    	{	
    		doSubmitForm('Save');
    	}else
    	{
    		return false;
    	}
	}
	
	//ͨ����Ʊ���������ҵ��涨�ķ�Ʊ���볤�ȣ�������ķ�Ʊ���볤�ȱȽ�
	function getFphmcd(fphmOBJ,currentRowNo)
	{
		var currentFpzldm ="";
		var tab = document.getElementById('TABLE_LIST');
		
		//��õ�ǰ��Ʊ��ʼ����
		var currentFpqshm =fphmOBJ.value;
		
		for(var i =1 ; i < tab.rows.length; i++)
		{
			//����б��
			var tempRowNo = i;
			//��÷�Ʊ�������
			var text=tab.rows[i].cells[1].childNodes[0].options[tab.rows[i].cells[1].childNodes[0].selectedIndex].value;
			//��÷�Ʊ��ʼ����
			var tempFpqshm=tab.rows[i].cells[2].childNodes[0].value;

			//alert("��ʱѭ����Ʊ++++++"+tempRowNo+"||"+tempFpqshm);
			if((currentFpqshm == tempFpqshm) && (currentRowNo == tempRowNo))
			{
				currentFpzldm = text;
				break;
			}
		}
		
		var res = checkFphmCD(tempRowNo,currentFpzldm,currentFpqshm,fphmOBJ);
		if(!res)
		{
			fphmOBJ.select();
			fphmOBJ.focus();
		}
	}
	
	
	//��÷�Ʊ��ʼ���볤��
	function checkFphmCD(tempRowNo,fpzldm,fpqshm,fphmOBJ)
	{
		//alert("��Ʊλ��++++++"+fpzldm +"||"+ fpqshm.length);
		<logic:iterate id="dto" name="FplrForm" property="fpzlList" indexId="index">
			var tempFpzldm = <bean:write name="dto" property="fpzldm"/>;
			var tempFpcd = <bean:write name="dto" property="fphmcd"/>;
			
			//alert("��ʱѭ����Ʊλ��++++++"+tempFpdm+"||"+tempFpcd);
			if(fpzldm == tempFpzldm)
			{
				if(fpqshm.length != tempFpcd)
				{
					alert("����� "+tempRowNo+" �������෢Ʊ�ĺ��볤��Ϊ "+tempFpcd+" λ��");
					return false;
				}
			}
		</logic:iterate>
		
		return true;
	}
	
	
	
	
	
	function checkFpzlcd(obj)
	{
	
/*		var fpzldm =  document.forms[0].fpzldm[i];
        if(sl==null)
            var fpzldm = document.forms[0].fpzldm;*/
      
      var fpzldmHTMLText = obj.parentNode.parentNode.cells[1].innerHTML;
      
      alert("fpzldmHTMLText::"+fpzldmHTMLText);
      
      
      
/*		var value = obj.parentNode.parentNode.cells[1].value;
      alert("value::"+value);
      
      var tempobjArr1 = obj.parentNode.parentNode.cells.tags;
      
           alert("tempobjArr1.length+++"+tempobjArr1.length);
       		for(var index = 0; index < tempobjArr1.length; index ++){
				if(tempobjArr1[index].type == "select"){
					alert(tempobjArr1[index].value);
				}
		}*/
      
      
      
      
      
       var tempobjArr = obj.parentNode.parentNode.cells.tags;
       
       		 alert("tempobjArr.length+++"+tempobjArr);
       		 alert("tempobjArr.value+++"+tempobjArr.value);
       		 
  /*     		 
            alert("tempobjArr.length+++"+tempobjArr.length);
       		for(var index = 0; index < tempobjArr.length; index ++){
				if(tempobjArr[index].type == "select"){
					alert(tempobjArr[index].value);
				}
				
				
		}*/
		
		     
            
            
            
            
		//getFpzlcd(fpzldm.value);
	}
	
	function getFpzlcd(fpzldm)
	{
		if(fpzldm!="")
		{
			//����XMLHttpRequest����		
			http_request = false;		
			if (window.XMLHttpRequest)
			{
				alert("00000000000");
				http_request = new XMLHttpRequest();
				if (http_request.overrideMimeType)
				{
					alert("11111111");
					http_request.overrideMimeType('text/xml');
				}
			} 
			else if (window.ActiveXObject) 
			{ 
				// IE		
				try {		
						http_request = new ActiveXObject("Msxml2.XMLHTTP");	
						alert("2222222");
					} 
					catch (e) 
					{		
				try {		
					http_request = new ActiveXObject("Microsoft.XMLHTTP");	
					alert("333333333");
					} 
					catch (e) 
					{
					
					}		
				}	
			}
			if (!http_request) 
			{
				alert('���ܴ��� XMLHTTP ʵ��');		
				return false;		
			}		
			http_request.onreadystatechange=function()
	  		{
	  			if (http_request.readyState==4 && http_request.status==200)
	  			{
			  		http_request.responseText;
			  		alert( "+++++++++++++++"+http_request.responseText);
					var res = http_request.responseText;
					//var response = res.getElementByTagName("response").innerHTML; 
					alert(res);
				}
			}

			//onreadyStateChange�¼���ָ��һ���¼�������������XMLHttpRequest�����ִ�н��		
			alert(http_request.readyState);
			//alert(url);
			http_request.open('POST', "/fpgl/fplr.do?operationType=Show1&fpzldm="+fpzldm, true);//�������󷽷���Ŀ�꣬��������Ϊ�첽�ύ		
			http_request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");//�����趨ĳ�������HTTPͷ��Ϣ		
			http_request.send(null);//��������	

			return false;		
		}
		else
		{		       
			alert("�����뷢Ʊ������룡");		     
		}
	}

</script>

