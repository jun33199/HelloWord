<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.FtxxForm"%>
<%
	FtxxForm ftxxForm = (FtxxForm)session.getAttribute("ftxxForm");
%>
<HTML><HEAD><TITLE>���ط��ݻ�����Ϣ�޸ı�</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
  <script language="javascript" src="<%=static_file%>/js/inputchecker.js"></script>
  <script language="javascript" src="../js/qscommon.js"></script>
</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType){
  document.all.operationType.value=operationType;
  if(operationType=="save"){

	  
	//add by hazhengze 20090108 start 
	//����ڷ���Ȩ��ת������Ϊ�����������ݽ���ʱ������ѡ������������
	  if ((document.forms[0].tdfwqszylxdm.value == "03") || (document.forms[0].tdfwqszylxdm.value == "04")|| (document.forms[0].tdfwqszylxdm.value == "05")){
			if(document.forms[0].tdjc.value=="00"){
				alert("���ݽ��ױ���ѡ��������������ѡ��");
				return false;
			}
	  }
	//add by hazhengze 20090108 end


    if(!check_Mj()){
		if (!confirm("�����������1��ƽ���ף��Ƿ�ȷ��?"))
		{
		   return false;
		}
    }
    if(!check_Jg()){
		if (!confirm("�����ܼ�С��5��Ԫ���Ƿ�ȷ��?"))
		{
		   return false;
		}
    }

    document.forms[0].operationType.value='Save';
    fnSave();
  }else if(operationType=="quit"){
    document.forms[0].operationType.value='Return';
    document.forms[0].submit();
  }
  else
  {
    document.forms[0].submit();
  }
}


function fnSave()//����
    {


    if(document.forms[0].fdcxmmc.value == "")
    {
        alert("���ز���Ŀ���Ʋ���Ϊ�գ����������룡");
        document.forms[0].fdcxmmc.focus();
        return false;
    }
    if (document.forms[0].hyqdsj.value == "" || !checkDate(document.forms[0].hyqdsj.value))
  	{
  	   alert("��Լǩ��ʱ�䲻��Ϊ�ջ��ʽ����ȷ�����������룡");
  	   document.forms[0].hyqdsj.focus();
  	   return false;
  	}

        if (!cmpDate(document.forms[0].hyqdsj.value,document.forms[0].xtdqsj.value))
  	{
  	   alert("��Լǩ��ʱ�䲻�ܴ��ڵ�ǰʱ�䣬���������룡");
  	   document.forms[0].hyqdsj.focus();
  	   return false;
  	}

	if(document.forms[0].tdfwzldz.value == "")
        {
            alert("���ء����������ַ����Ϊ�գ����������룡");
            document.forms[0].tdfwzldz.focus();
            return false;
        }

    //����Ȩ��ת������=����ת�û��߳���,ֻ����д���������������Բ������ֻ��һ��
    if ((document.forms[0].tdfwqszylxdm.value == "01") || (document.forms[0].tdfwqszylxdm.value == "02"))
    {
	    if (!FN_QSCheckNumberDigit(document.forms[0].tdfwqszymj.value,2,"�������"))
	    {
	      document.forms[0].tdfwqszymj.focus();
	      return false;
	    }
	    if (document.forms[0].fwjzmj.value !="")
	    {
		    if (!FN_QSCheckNumberDigit(document.forms[0].fwjzmj.value,2,"���ݽ������"))
		    {
		      document.forms[0].fwjzmj.focus();
		      return false;
		    }
	    }
    }
    else
    {
    	if (!FN_QSCheckNumberDigit(document.forms[0].fwjzmj.value,2,"���ݽ������"))
		    {
		      document.forms[0].fwjzmj.focus();
		      return false;
		    }
		  if (document.forms[0].tdfwqszymj.value !="")
	    {
		    if (!FN_QSCheckNumberDigit(document.forms[0].tdfwqszymj.value,2,"���������"))
		    {
		      document.forms[0].tdfwqszymj.focus();
		      return false;
		    }
	    }

	}

	//˰�����Ϊ���״ι���90�O������ͨס����ʱ�����ء�����Ȩ��ת�����ͱ���Ϊ�����������������ݽ����������С�ڵ���90ƽ����
	if(document.forms[0].setz.value=="5")
  {
  	if(document.forms[0].tdfwqszylxdm.value!="03")
  	{
    	alert("˰�����Ϊ���״ι���90�O������ͨס����ʱ�����ء�����Ȩ��ת�����ͱ���Ϊ������������");
    	return false;
  	}

    var ce = parseFloat(document.forms[0].fwjzmj.value)-90;
    if(ce>0)
    {
    	alert("˰�����Ϊ���״ι���90�O������ͨס����ʱ�����ݽ����������С�ڵ���90�O");
    	return false;
    }
  }



    //�����Ϊ�ձ���Ϊ����
    if(document.forms[0].cjjgyrmb.value != "")
    {
	    if (!FN_QSCheckNumberDigit(document.forms[0].cjjgyrmb.value,2,"����ҳɽ��۸�"))
	    {
	      document.forms[0].cjjgyrmb.focus();
	      return false;
	    }
    }

    if(document.forms[0].pgjg.value != "")
    {
	    if (!FN_QSCheckNumberDigit(document.forms[0].pgjg.value,2,"�����˰����غ˶��۸�"))
	    {
	      document.forms[0].pgjg.focus();
	      return false;
	    }
    }

    if(document.forms[0].cjjgywb.value != "")
    {
	    if (!FN_QSCheckNumberDigit(document.forms[0].cjjgywb.value,2,"��ҳɽ��۸�"))
	    {
	      document.forms[0].cjjgywb.focus();
	      return false;
	    }
    }

    if(document.forms[0].zhyrmb.value != "")
    {
	    if (!FN_QSCheckNumberDigit(document.forms[0].zhyrmb.value,2,"�ۺ�����Ҽ۸�"))
	    {
	      document.forms[0].zhyrmb.focus();
	      return false;
	    }
    }

    if(document.forms[0].hn.value != "")
    {
	    if (!FN_QSCheckNumberDigit(document.forms[0].hn.value,5,"����"))
	    {
	      document.forms[0].hn.focus();
	      return false;
	    }
    }

    //����ɽ�����Ҽ۸�Ϊ�գ�˰����غ˶��۸����Ҽ۸��ܶ�Ϊ��
    if(document.forms[0].cjjgyrmb.value == ""){
      if(document.forms[0].pgjg.value == ""){
        if(document.forms[0].cjjgywb.value == "")
        {
          alert("�ɽ�����Ҽ۸�˰����غ˶��۸����Ҽ۸��ܶ�Ϊ�գ�");
          document.forms[0].cjjgyrmb.focus();
          return false;
        }
        else if(document.forms[0].cjjgywb.value != "")
        {

          if(document.forms[0].bzdm.value == "")
          {
            alert("���ֲ���Ϊ�գ������룡");
            document.forms[0].bzdm.focus();
            return false;

          }
          if(document.forms[0].hn.value == "")
          {
            alert("���ʲ���Ϊ�գ������룡");
            document.forms[0].hn.focus();
            return false;

          }
          if(document.forms[0].zhyrmb.value == "")
          {
            alert("�ۺ�����Ҳ���Ϊ�գ������룡");
            document.forms[0].zhyrmb.focus();
            return false;
          }
        }
      }
    }
    if(document.forms[0].cjjgywb.value != "")
    {

      if(document.forms[0].bzdm.value == "")
      {
        alert("���ֲ���Ϊ�գ������룡");
        document.forms[0].bzdm.focus();
        return false;

      }
      if(document.forms[0].hn.value == "")
      {
        alert("���ʲ���Ϊ�գ������룡");
        document.forms[0].hn.focus();
        return false;

      }
      if(document.forms[0].zhyrmb.value == "")
      {
        alert("�ۺ�����Ҳ���Ϊ�գ������룡");
        document.forms[0].zhyrmb.focus();
        return false;
      }
    }






    document.forms[0].flmc.value = document.forms[0].fldm.options[document.forms[0].fldm.selectedIndex].text;
	if(document.forms[0].tdfwqszylxdm.value == "05" || document.forms[0].tdfwqszylxdm.value == "03") //���Ƿ��ݽ���
	{
      document.forms[0].fwlbmc.value = document.forms[0].fwlbdm.options[document.forms[0].fwlbdm.selectedIndex].text;
	}
	else
	{
        document.forms[0].fwlbdm.value = "";
        document.forms[0].fwlbmc.value = "";
	}

    if (document.forms[0].changelxdm.value == "true")
    {
        document.forms[0].tdfwqszylxmc.value = document.forms[0].tdfwqszylxdm.options[document.forms[0].tdfwqszylxdm.selectedIndex].text;
    }

    if(document.forms[0].esf_state.disabled==false)
    {
        if(document.forms[0].esf_state.checked==false)
        {
            document.forms[0].sfesf.value = '00';
        }
        else
        {
            document.forms[0].sfesf.value = '01';
        }
    }else
    {
        document.forms[0].sfesf.value = '00';
    }

    document.forms[0].bzmc.value = document.forms[0].bzdm.options[document.forms[0].bzdm.selectedIndex].text;
    document.forms[0].submit();
}
//�ж��Ƿ��������ַ�
//parameter strValue �ַ�������
//return true���������ַ���false��û�������ַ�
function hasChineseChar(strValue) {
    if (strValue==null) {
        return false;
    }
    for (var i=0;i<strValue.length;i++) {
        var code = strValue.charCodeAt(i);
        if (code*1>255) {
            return true;
        }
    }
    return false;
}

function checkSelect()
{
    if(document.forms[0].tdfwqszylxdm.value=="03"||document.forms[0].tdfwqszylxdm.value=="05")
    {
       document.forms[0].fwlbdm.disabled=false ;
    }
    else
    {
        document.forms[0].fwlbdm.disabled=true ;
    }
  if((document.forms[0].tdfwqszylxdm.value=="01") || (document.forms[0].tdfwqszylxdm.value=="02"))
  {
      document.forms[0].esf_state.checked = false;
      document.forms[0].esf_state.disabled=true ;
  }
  else
  {
    document.forms[0].esf_state.disabled=false ;
  }
}

//˰�����Ϊ���״ι���90�O������ͨס����ʱ�����ط���Ȩ��ת������Ϊ���������������Ƿ���ַ���¼
function checkSelectOne()
{
  if(document.forms[0].setz.value=="5")
  {
    document.forms[0].tdfwqszylxdm.value="03";
    document.forms[0].fwlbdm.disabled=false;
    document.forms[0].sfesf.disabled=false ;
  }
  else
  {
    document.forms[0].fwlbdm.disabled=true;
    document.forms[0].sfesf.disabled=true ;
    checkSelect();
  }
}

//���ַ�
function esfOnclik()
{
	changeFwszqy();
	//alert(document.forms[0].sfesf.checked);
	//alert(document.forms[0].xtdqsj.value);
	
	//alert(document.forms[0].hyqdsj.value);
	//!cmpDate(document.forms[0].hyqdsj.value,document.forms[0].xtdqsj.value)
	
	
	
}

function hyqdsjChange()
{
	//alert(document.forms[0].sfesf.checked);
	//alert(document.forms[0].xtdqsj.value);
	
	//alert(document.forms[0].hyqdsj.value);
	
	changeFwszqy();
	//cmpDate(document.forms[0].hyqdsj.value,document.forms[0].xtdqsj.value)
}
function changeFwszqy()
{
	//����2014��10�±�����ͨס����׼���������������Ϊ�±�׼��ÿƽ�׼۸����ޡ�ÿ�׷��ݼ۸������������������ ��
	//�˴�ֻ�޸�����������ʾ��modified by gaoyh to 20141020
	var   objSelect   =  document.forms[0].tdjc; 
	
	/********************************
	var flag=false;
	if(!document.forms[0].esf_state.checked){
		var tsbrq = sbrq;
		if(sbrq == "") tsbrq = document.forms[0].xtdqsj.value;
		if(tsbrq>="20111210"){
		     flag=true;
		}else{
			flag=false;
		}
	}else{
		 if (document.forms[0].hyqdsj.value == "" )
		    {
			 objSelect.options.length=0;
		    objSelect.options[0] =  	new   Option("��ѡ��","00");
		      return false;
		    }
		 if ( !checkDate(document.forms[0].hyqdsj.value))
		    {
		      alert("��Լǩ��ʱ���ʽ����ȷ�����������룡");
		      document.forms[0].hyqdsj.focus();
		      return false;
		    }
        if(document.forms[0].hyqdsj.value>="20111210"){
        	flag=true;
		}else{
			flag=false;
		}
        
        
	}
   	
	
	
	var selectItem = objSelect.value;

    objSelect.options.length=0;

   
    objSelect.options[0] =  	new   Option("��ѡ��","00");
    
	if(flag){
 	
	    objSelect.options[1] =  	new   Option("�Ļ��ڱ�������","11");
        objSelect.options[2] =  	new   Option("�Ļ����ϲ�����","12");
        objSelect.options[3] =  	new   Option("�Ļ����廷��������","13");
        objSelect.options[4] =  	new   Option("�Ļ����廷�ϲ�����","14");
        objSelect.options[5] =  	new   Option("�廷��������������","15");
        objSelect.options[6] =  	new   Option("�廷�������ϲ�����","16");
        objSelect.options[7] =  	new   Option("���������","17");
    }else{
		   objSelect.options[1] =  	new   Option("��������","01");
	        objSelect.options[2] =  	new   Option("�������Ļ�֮��","02");
	        objSelect.options[3] =  	new   Option("�Ļ����廷֮��","03");
	        objSelect.options[4] =  	new   Option("�廷����","04");

    }
	********************************/
	/*******************************
	var flag=01;
	if(!document.forms[0].sfesf.checked){
		var tsbrq = sbrq;
		if(sbrq == "") tsbrq = document.forms[0].xtdqsj.value;
		//alert("tsbrq==>>"+tsbrq);
		if(tsbrq>="20141008"){
		     flag=03;
		}else if(tsbrq>="20111210" && tsbrq<"20141008"){
		     flag=02;
		}else{
			flag=01;
		}
	}else{
		 if (document.forms[0].hyqdsj.value == "" )
		    {
			 objSelect.options.length=0;
		    objSelect.options[0] =  	new   Option("��ѡ��","00");
		      return 01;
		    }
		 if ( !checkDate(document.forms[0].hyqdsj.value))
		    {
		      alert("��Լǩ��ʱ���ʽ����ȷ�����������룡");
		      document.forms[0].hyqdsj.focus();
		      return 01;
		    }
		 
		 if(document.forms[0].hyqdsj.value>="20141008"){
		     flag=03;
		 }else if(document.forms[0].hyqdsj.value>="20111210" && document.forms[0].hyqdsj.value<"20141008"){
		     flag=02;
		 }else{
			flag=01;
		}

	}
	*******************************/
	
	var flag=03;
	if(!document.forms[0].sfesf.checked){
		var tsbrq = sbrq;
		if(sbrq == "") tsbrq = document.forms[0].xtdqsj.value;
		//alert("tsbrq==>>"+tsbrq);
	}else{
		 if (document.forms[0].hyqdsj.value == "" )
		    {
			  objSelect.options.length=0;
		      objSelect.options[0] =  	new   Option("��ѡ��","00");
		      return 01;
		    }
		 if ( !checkDate(document.forms[0].hyqdsj.value))
		    {
		      alert("��Լǩ��ʱ���ʽ����ȷ�����������룡");
		      document.forms[0].hyqdsj.focus();
		      return 01;
		    }
	}
	
	var selectItem = objSelect.value;

    objSelect.options.length=0;


    objSelect.options[0] =  	new   Option("��ѡ��","00");
    
    if(flag=="03"){
	    objSelect.options[1] =  	new   Option("5����","21");
        objSelect.options[2] =  	new   Option("5-6��","22");
        objSelect.options[3] =  	new   Option("6����","23");
    }
	if(flag=="02"){
	    objSelect.options[1] =  	new   Option("�Ļ��ڱ�������","11");
        objSelect.options[2] =  	new   Option("�Ļ����ϲ�����","12");
        objSelect.options[3] =  	new   Option("�Ļ����廷��������","13");
        objSelect.options[4] =  	new   Option("�Ļ����廷�ϲ�����","14");
        objSelect.options[5] =  	new   Option("�廷��������������","15");
        objSelect.options[6] =  	new   Option("�廷�������ϲ�����","16");
        objSelect.options[7] =  	new   Option("���������","17");
    }
	if(flag=="01"){
		    objSelect.options[1] =  	new   Option("��������","01");
	        objSelect.options[2] =  	new   Option("�������Ļ�֮��","02");
	        objSelect.options[3] =  	new   Option("�Ļ����廷֮��","03");
	        objSelect.options[4] =  	new   Option("�廷����","04");

    }
	
	objSelect.value = "00";
	for(var i=0;i<objSelect.options.length;i++){
		if(objSelect.options[i].value == selectItem){
			 objSelect.value = selectItem;
			 break;			
		}
	}	
	
}
var sbrq='<bean:write name="ftxxForm" property="sbrq" />';
</SCRIPT>

<BODY bgColor=#ffffff leftMargin=0
onload="checkSelect();MM_preloadImages('<%=static_file%>imagess/dayin2.jpg','<%=static_file%>imagess/fanhui2.jpg','<%=static_file%>imagess/tuichu2.jpg','<%=static_file%>imagess/diyitiao2.jpg','<%=static_file%>imagess/shangyitiao2.jpg','<%=static_file%>imagess/zuimotiao2.jpg','<%=static_file%>imagess/xinzeng2.jpg','<%=static_file%>imagess/shanchu2.jpg')"
topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="��˰�걨&gt;���ط��ݻ�����Ϣ�޸ı�"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>


<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=770>
  <TBODY>
    <TR>
      <TD vAlign=top>

    <TABLE align=center cellSpacing=0 class=table-99>
      <TBODY>
      <TR>
        <TD class=1-td1>��˰�걨��-���ݡ����ػ�����Ϣ</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
            <html:form action="/qssb/modifyFwxx.do">
            <html:hidden property="xtdqsj"/>
              <html:hidden property="operationType" value=""/>
              <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
                <TBODY>
                  <BR>
          <TR>
            <TD class=2-td2-t-left width="25%">
              <DIV align=right>�걨���&nbsp; </DIV>
            </TD>
            <TD class=2-td2-t-left>
              <DIV align=left>&nbsp;<bean:write name="ftxxForm" property="sbbh" /> </DIV></TD>
              <TD class=2-td2-t-left>
                <DIV align=right>���ء�����Ψһ��ʶ&nbsp; </DIV>
              </TD>
                    <TD  colspan="3" class=2-td2-t-center width="15%">
                      <DIV align=left>&nbsp;<bean:write name="ftxxForm" property="tdfwwybz" /></DIV></TD>
                    </TR>
              <TR>


          <TR>
            <TD class=2-td2-left>
              <DIV align=right>˰�����&nbsp; </DIV>
            </TD>
            <TD class=2-td2-left colspan=3>
                <div align="left">
                <html:select property = "setz" onchange="checkSelectOne()">
                    <html:option value = "<%=Constants.SETZ_QEZS%>" >ȫ������</html:option>
                    <html:option value = "<%=Constants.SETZ_JBZS%>" >��������</html:option>
									<%if(ftxxForm.getPerson().equals("true")){%>
										<html:option value = "<%=Constants.SETZ_SCGMPTZF%>" >�״ι���90�O������ͨס��</html:option>
									<html:option value = "<%=Constants.SETZ_JJSYF%>" >�������÷�</html:option>
                                        <%}%>
                </html:select><FONT color=red>���ڷ�����ͨ��׼סլ��׼���걨���ݣ���˰�۸���ݴ���ȷ����</FONT>
                </div>
            </td>
            <td class=2-td2-center>
                <logic:equal name="ftxxForm" property="sfesf" value="00">
                    <input type="checkbox" name="esf_state"  onclick="esfOnclik()">
                </logic:equal>
                <logic:equal name="ftxxForm" property="sfesf" value="01">
                    <input type="checkbox" name="esf_state" checked="checked" onclick="esfOnclik()">
                </logic:equal>
                <html:hidden property="sfesf" name="ftxxForm"/>
                    �Ƿ�Ϊ���ַ�
            </TD>
        </TR>

                <TD class=2-td2-left width="8%" rowspan = "8">
                  <DIV align=right>	���ط���</DIV>
                  <DIV align=right>Ȩ��ת��</DIV></TD>
                  <TD class=2-td2-left width="15%">
                    <DIV align=right>���ز���Ŀ����&nbsp;</DIV></TD>
                    <TD colspan="4" class=2-td2-center width="15%">
                      <DIV align=left><html:text property="fdcxmmc" maxlength="100"/><FONT color=red>*</FONT> </DIV></TD>
                    </TR>
                <TR>
                  <TD class=2-td2-left width="15%";>
                    <DIV align=right>��Լǩ��ʱ��&nbsp; </DIV>
                  </TD>
                  <TD class=2-td2-left width="19%">
                    <DIV align=left><html:text property="hyqdsj" onchange="javascript:hyqdsjChange()" /><FONT color=red>*</FONT></DIV></TD>


<logic:equal name="ftxxForm" property="bzqs"  value="false">
                    <TD class=2-td2-left width="19%">
                      <DIV align=right>����ԭ��&nbsp;</DIV></TD>
</logic:equal>
<logic:equal name="ftxxForm" property="bzqs"  value="true">

                    <TD class=2-td2-left width="19%">
                      <DIV align=right>����&nbsp;</DIV></TD>
</logic:equal>


                      <TD colspan="2"  class=2-td2-center width="33%">
                        <DIV align=left>
                          <bean:define id="data1" name="ftxxForm" property="flList"/>
                          <html:select property="fldm">
                            <html:options collection="data1" labelProperty="qstdfwytmc" property="qstdfwytdm"/>
                            </html:select><FONT color=red>*</FONT>
                            <html:hidden property="flmc"/>


                          </DIV></TD>
                      </TR>
                <TR>
                  <TD class=2-td2-left width="15%";>
                    <DIV align=right>���ء�����Ȩ��ת������&nbsp; </DIV> </TD>
                    <TD class=2-td2-left width="19%">
                      <DIV align=left>

					         <bean:define id="data1" name="ftxxForm" property="tdfwqszylxList" />
					         <html:select property="tdfwqszylxdm" onchange="checkSelect()" >
					           <html:options collection="data1" labelProperty="qszyxsmc" property="qszyxsdm" />
			                 </html:select><FONT color=red>*</FONT>

			    	       <html:hidden property="tdfwqszylxmc"/>

                             </DIV></TD>
                                <TD class=2-td2-left width="19%">
                                  <DIV align=right>�������&nbsp;</DIV></TD>
                                  <TD colspan="2"  class=2-td2-center width="33%">
                                    <DIV align=left>

                                       <input type="hidden" name="changelxdm" value="true">
                                       <bean:define id="data1" name="ftxxForm" property="fwlbList"/>
                                       <html:select property="fwlbdm" disabled="true">
                                     <html:options collection="data1" labelProperty="fwlxmc" property="fwlxdm"/>
                                        </html:select>


                                        <html:hidden property="fwlbmc"/>


                                      </DIV></TD>
                                          </TR>
                    <TR>
                      <TD  class=2-td2-left width="25%";>
                        <DIV align=right>���ء����������ַ&nbsp; </DIV>
                      </TD>
                      <TD colspan="4"  class=2-td2-center width="15%">
                        <DIV align=left><html:text property="tdfwzldz" maxlength="200"/><FONT color=red>*</FONT> </DIV></TD>
                      </TR>
                  <TR>
                    <TD class=2-td2-left width="25%";>
                      <DIV align=right>���ء�����Ȩ��ת�����&nbsp; </DIV>
                    </TD>
                    <TD  class=2-td2-left width="15%">
                      <DIV align=left>���أ�<html:text property="tdfwqszymj" maxlength="14"/>�O</DIV></TD>
                      <TD class=2-td2-left width="17%">
                        <DIV align=right>���ݽ������&nbsp;</DIV></TD>
                        <TD colspan="2"  class=2-td2-center width="32%">
                          <DIV align=left><html:text property="fwjzmj" maxlength="14"/>�O</DIV></TD>
                        </TR>


          <TR>
            <TD class=2-td2-left width="25%";>
              <DIV align=right>�ݻ���&nbsp; </DIV>
            </TD>
            <TD  class=2-td2-left width="15%">
              <DIV align=left>
			  				  	 <html:select property="rjl" >
                        <html:option value="01">1.0���Ϻ�1.0</html:option>
                        <html:option value="00">1.0����</html:option>
                      </html:select>

			  </DIV></TD>
              <TD class=2-td2-left width="17%">
                  <!--�޸����ؼ���Ϊ������������-->
                <DIV align=right>������������&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="32%">
                  <DIV align=left>
			           <html:select property="tdjc" >
                          

                      </html:select>
				  </DIV></TD>
           </TR>






                    <TR>
                      <TD class=2-td2-left width="8%" rowspan = "2">
                        <DIV align=right>�ɽ��۸������۸� <FONT color=red>*</FONT></DIV>
                        <TD  class=2-td2-left width="5%">
                          <DIV align=left><html:text property="cjjgyrmb" maxlength="15"/> Ԫ(�����) </DIV></TD>
                          <TD class=2-td2-left width="17%">
                            <DIV align=right>˰����غ˶��۸�&nbsp; </DIV></TD>
                            <TD  colspan="2"  class=2-td2-center width="32%">
                              <DIV align=left><html:text property="pgjg" maxlength="15"/>Ԫ(�����) </DIV></TD>
                            </TR>
                      <TR>
                        <TD class=2-td2-left width="15%";>
                          <DIV align=left><html:text property="cjjgywb" maxlength="15" onchange="fnConvertWb(this,hn,zhyrmb)"/>Ԫ(���) </DIV></TD>
                          <TD class=2-td2-left width="25%">
                            <DIV align=left>
                                     <bean:define id="data1" name="ftxxForm" property="bzList" />
                              <html:select property="bzdm" onchange="doSubmitForm('GetHL')">
                                <html:options collection="data1" labelProperty="bzmc" property="bzdm" />
                            </html:select>
                          <html:hidden property="bzmc"/>
                          </DIV>
                            <DIV align=left>����:&nbsp;<html:text property="hn" maxlength="15" onchange="fnConvertWb(cjjgywb,this,zhyrmb)"/> </DIV></TD>
                            <TD class=2-td2-center  width="17%">
                              <DIV align=left><html:text property="zhyrmb" maxlength="5" />�ۺ�Ԫ(�����) </DIV></TD>
                            </TR>

       </TBODY></TABLE><BR>

      <DIV align=center>
        <IMG name="baocun"
          onClick= "doSubmitForm('save');"
          onMouseOver="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/baocun1.jpg"
          width="79" height="22" id="baocun" alt="����" style="cursor:hand">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

        <IMG name="tuichu1"
          onclick="doSubmitForm('quit');"
          onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="�˳�" style="cursor:hand">
      </DIV><BR>

                                                                                    </html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
<script language="javascript">


	if(document.forms[0].tdfwqszylxdm.value=="03"||document.forms[0].tdfwqszylxdm.value=="05")
    {
       document.forms[0].fwlbdm.disabled=false ;
    }
    //fnConvertWb(document.all.cjjgywb,document.all.hn,document.all.zhyrmb);
 changeFwszqy();
 document.forms[0].tdjc.value='<bean:write name="ftxxForm" property="tdjc" />';
</SCRIPT>
