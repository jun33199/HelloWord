<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.ActionUtil"%>
<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.JmsbForm"%>
<%
JmsbForm aForm = (JmsbForm) session.getAttribute("jmsbForm");
%>

<HTML>
<HEAD>
<TITLE>��˰�����걨������Ϣ¼���</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>

<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js"
	type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js"
	type=text/JavaScript></SCRIPT>
<script language="javascript" src="../js/qscommon.js"></script>
<script language="javascript" src="../js/gmit_selectcontrol.js"></script>

<script LANGUAGE="javascript" src="../js/Const.js"></script>
<script LANGUAGE="javascript" src="../js/DTColumn.js"></script>
<script LANGUAGE="javascript" src="../js/DynamicTable.js"></script>
<script LANGUAGE="javascript" src="../js/DataAwareTable.js"></script>
<script LANGUAGE="javascript" src="../js/DTColumn_Check.js"></script>

<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
<META content="MSHTML 5.00.2919.6307" name=GENERATOR>
</HEAD>

<SCRIPT language=JavaScript>
function checkSubmit(operationType)
{

	document.forms[0].operationType.value=operationType;


	if(operationType=="Save" )
	{
       var checknsr=checkNsr();
       if (checknsr==false){
              return false;
       }

	if (document.forms[0].sbrq.value == ""){
  	   alert("�걨���ڲ���Ϊ�գ����������룡");
  	   document.forms[0].sbrq.focus();
  	   return false;
  	}
	var check2 = document.getElementsByName("qsjmlbSelect");
	var isCheck = false;
	
    for (i=0; i<check2.length; i++)
 	{
 	
 		
	   	if(check2[i].checked)
	   	{
	   		isCheck = true;
	   		break;
	  	}
 	}
	if(!isCheck){
		alert("��ѡ��������ɣ�");
		return false;
	}
  	
  	if(!checkDate(document.forms[0].sbrq.value)){
  	   alert("�걨���ڲ���ȷ����ȷ��ʽΪyyyymmdd�����ܴ���2100��С��1900�꣬���������룡");
  	   document.forms[0].sbrq.focus();
  	   return false;
  	}
  	
  	if (!cmpDate(document.forms[0].sbrq.value,document.forms[0].xtdqsj.value))
  	{
  	   alert("�걨���ڲ��ܴ��ڵ�ǰʱ�䣬���������룡");
  	   document.forms[0].sbrq.focus();
  	   return false;
  	}
	  	
     if (document.forms[0].fdcxmmc.value == "")
	  	{
	  	   alert("���ز���Ŀ���Ʋ���Ϊ�գ����������룡 ");
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
		
     if (document.forms[0].tdfwzldz.value == "")
	  	{
	  	   alert("���ء����������ַ����Ϊ�գ����������룡");
	  	   document.forms[0].tdfwzldz.focus();
	  	   return false;
	  	}
 
    //����Ȩ��ת������=����ת�û��߳���,ֻ����д���������������Բ������ֻ��һ��
    if ((document.forms[0].tdfwqszylx.value == "01") || (document.forms[0].tdfwqszylx.value == "02"))
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
		    if (!FN_QSCheckNumberDigit(document.forms[0].pgjg.value,2,"����������۸�"))
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
 
 	//����ɽ�����Ҽ۸�Ϊ�գ������۸����Ҽ۸��ܶ�Ϊ��
    if(document.forms[0].cjjgyrmb.value == ""){
      if(document.forms[0].pgjg.value == ""){
        if(document.forms[0].cjjgywb.value == "")
        {
          alert("�ɽ�����Ҽ۸������۸����Ҽ۸��ܶ�Ϊ�գ�");
          document.forms[0].cjjgyrmb.focus();
          return false;
        }
        else if(document.forms[0].cjjgywb.value != "")
        {

          if(document.forms[0].bz.value == "")
          {
            alert("���ֲ���Ϊ�գ������룡");
            document.forms[0].bz.focus();
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

      if(document.forms[0].bz.value == "")
      {
        alert("���ֲ���Ϊ�գ������룡");
        document.forms[0].bz.focus();
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


			document.forms[0].ztbs.value=<%=Constants.JMSBB_ZTBS_BC%>
			document.forms[0].yhbs.value=<%=Constants.ZB_YHBS_GR%>
			document.forms[0].flmc.value = document.forms[0].fl.options[document.forms[0].fl.selectedIndex].text;
			document.forms[0].tdfwqszylxmc.value = document.forms[0].tdfwqszylx.options[document.forms[0].tdfwqszylx.selectedIndex].text;
			//document.forms[0].qsjmlbmc.value = document.forms[0].qsjmlb.options[document.forms[0].qsjmlb.selectedIndex].text;

			<%-- %>if(document.forms[0].tdfwqszylx.value=="03")
			{
					document.forms[0].fwlbmc.value = document.forms[0].fwlb.options[document.forms[0].fwlb.selectedIndex].text;
			}
			else
			{
					document.forms[0].fwlb.value = "";
					document.forms[0].fwlbmc.value = "";
			}<% --%>
		    //��������س��û���ת��
		    if ((document.forms[0].tdfwqszylx.value == "01") || (document.forms[0].tdfwqszylx.value == "02")  ) 
		    {
		        document.forms[0].fwjzmj.value = 0;
		    }
			<%-- %>document.forms[0].bzmc.value = document.forms[0].bz.options[document.forms[0].bz.selectedIndex].text;<% --%>

  }
  document.forms[0].submit();
}
function linkPage(para)
{
}

function checkNsr()
{
	daList.saveAllData();
    //���б�ʽ���ݽ��м��

	isCheck = DataSourceCheck(daList);
    if(isCheck == false){
        return false;
    }
    //��textArea�е��ִܷŵ�hidden����
    document.forms[0].dataSource_gm.value=daList.formatData(constRowSeparator,constColSeparator);
    return true;
	//ת����һ��ҳ��
}

function checkSelect(jmlbdm,jmxzdm)
{
	var isShowQtjmly = false;
				 
    //ѡ�������������ɵĿ������������������
	 if(jmlbdm == <%=Constants.CXXJM_JMXMDM_QT%>){
	 	isShowQtjmly = true;
	 }

 	 		  	
  	if(isShowQtjmly){
  		//����firefox
		if(document.all)  
			document.getElementById('qtjmly').style.display   =  "block";  
		else  
			document.getElementById('qtjmly').style.display   =   "table-row";
  	} else{
  		document.getElementById('qtjmly').style.display   =  "none";
  		
  	}
  	document.forms[0].qsjmxzdm.value = jmxzdm;

	if(document.forms[0].qtjmlybeizhu.value == ""){
		checkJmxzdm(document.forms[0].qsjmxzdm);
  	}
}


</SCRIPT>

<SCRIPT LANGUAGE=javascript>
//��Ȩ������
<%=aForm.getCqrJs()%>

    var arySelect_zjlx =<%=ActionUtil.displaySelectDataSource(aForm.getSfzjlxList(),null,"zjlxdm","zjlxmc")%>; // ֤��
    var arySelect_gj = <%=ActionUtil.displaySelectDataSource(aForm.getGjdqList(),null,"gjdqdm","gjdqmc")%>;//����

    function addRow()
    {
            daList.appendRow();
	        daList.focusAt(daList.getCurrentRow(),1);
            var zjValue=daList.getCellAt(daList.getCurrentRow(),3);
            zjValue.value="02";
            var gjValue=daList.getCellAt(daList.getCurrentRow(),5);
            gjValue.value="CHN";
    }


    function modify()
    {
           daList.modifyRow(daList.DynamicTable.CurrentRow);
    }

    function deleteRow(numindex)
    {
	       daList.saveAllData();
      var dataSource_del=daList.getData();
        var numRow_del=dataSource_del.length;
        if (numRow_del ==0 ){
            return;
        }
 		if  (numRow_del ==1){
		   alert ("��Ȩ�˲���Ϊ�գ�");
		   return;
		}

		daList.moveto(numindex);
        var flagConfirm=confirm("ȷ��ɾ����ǰ��¼��");
        if(flagConfirm==true){
           daList.deleteRow(daList.DynamicTable.CurrentRow);
        }
    }


	function initPage()
	{
        //���* ��˰������* ��ϵ�绰* ���֤������* ���֤������* ����* �Ƿ�����Ȩ��*
        var aryColumn = new Array();
        aryColumn[aryColumn.length] = new DTColumn(constCTSequence,"xh",2,null);
        aryColumn[aryColumn.length] = new DTColumn(constCTInput,"nsrmc",200,null,"","��˰������",false,true,"200");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput,"lxdh",100,null,"","��ϵ�绰",false,false,"20");
        aryColumn[aryColumn.length] = new DTColumn(constCTSelect,"sfzjlxdm",100,arySelect_zjlx,"need_autoselect","���֤������",false,true,"2");
         aryColumn[aryColumn.length] = new DTColumn(constCTInput,"sfzjhm",150,null,"","���֤������",false,true,"30");
        aryColumn[aryColumn.length] = new DTColumn(constCTSelect,"gjdm",100,arySelect_gj,"need_autoselect","����",false,true,"3");
        aryColumn[aryColumn.length] = new DTColumn(constCTRadioButton,"zcqr",50,null,"maxlength=1","����Ȩ��",false,false,"1");
        aryColumn[aryColumn.length] = new DTColumn(constCTDelButton,"delIndex",10,null,"","ɾ��",false,false,"20");

        dtList = new DynamicTable(TABLE_LIST,aryColumn);
        if (dtList == null)  return;
        daList = new DataAwareTable(dtList);

        daList.setDataSource(aryDataSource);

        var jmxzdm = '<bean:write name="jmsbForm" property="qsjmxzdm"/>';
        var qsjmlb = '<bean:write name="jmsbForm" property="qsjmlb"/>';
        
        if(qsjmlb){
        	var check2 = document.getElementsByName("qsjmlbSelect");
        	for(var i=0;i<check2.length;i++){
				if(check2[i].value==qsjmlb){
					check2[i].checked = true;
					break;
				}
            }
        	checkSelect(qsjmlb,jmxzdm);
        }
	}

    function checkSpecial(dataSource)
    {
		var aryResult=[-1,-1];

        var numRow=dataSource.length;
		var j=100;
        if(numRow>0){
            for(var i=0;i<numRow;i++)
            {
                
			if ((dataSource[i][3]=="02") && (dataSource[i][5]=="CHN") )
          {
            if (!checkIdentityCard(dataSource[i][4]))
            {
              alert("���֤�����ʽ���ԣ�");              
		      aryResult=[0,4];
		      return aryResult;
            }
          }
				if(dataSource[i][6]==constTrue)
                {
                    j=6;
                }
            }
        }

        if(j==100)
        {
            alert("��ѡ������Ȩ�ˣ�");  
            aryResult=[0,6];
		    return aryResult;
        }
		return aryResult;
	}

    function checkJBXX(dataSource)
    {
    }

    function checkJmxzdm(obj){
    	document.forms[0].qtjmlybeizhu.value = obj.options[obj.selectedIndex].text;
    }

</SCRIPT>


<!----��ҳ���ͷ�ļ��ͱ�ҳ������·��----->

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0
	marginheight="0" marginwidth="0" onload="initPage()">
<jsp:include page="/include/Header.jsp" flush="true">
	<jsp:param name="subtitle" value="��˰�����걨¼��>��˰�����걨������Ϣ¼���" />
	<jsp:param name="helpURL" value="" />
</jsp:include>
<SCRIPT language=javascript>

</SCRIPT>
<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%"
	width=98%>
	<TBODY>
		<TR>
			<TD vAlign=top>

			<TABLE align=center cellSpacing=0 class=table-99>
				<TBODY>
					<TR>
						<TD class=1-td1>�����еط�˰�����˰�����걨��-����</TD>
					</TR>
					<TR>
						<TD class=1-td2 vAlign=top><html:form
							action="/jmsb/addJmsbGr.do">
							<html:hidden property="operationType" />
							<html:hidden property="xtdqsj" />
							<html:hidden property="ymczlxdm" />
							<html:hidden property="ztbs" value="<%=Constants.ZB_ZTBS_BC%>" />
							<html:hidden property="yhbs" value="<%=Constants.ZB_YHBS_GR%>" />
							<input type="hidden" name="dataSource_gm" value="">

							<TABLE border=0 cellSpacing=0 class=tabled-99 width="98%">
								<TBODY>
									<BR>
									<TR>
										<TD class=2-td1-left width="30%">��˰�����걨���</TD>
										<html:hidden name="jmsbForm" property="sbbh" />
										<TD class=2-td1-center width="70%" colspan="3">
										<DIV align=left>&nbsp;&nbsp;<bean:write name="jmsbForm"
											property="sbbh" /></DIV>
										</TD>

									</TR>
									<TR>

										<TD class=2-td2-left width="20%">�������ع����������</TD>
										<TD class=2-td2-left width="35%">
										<DIV align=left><html:text property="fwtdbmslh"
											size="30" maxlength="30" /></DIV>
										</TD>
										<TD class=2-td2-left width="22%">�걨����(yyyymmdd)</TD>
										<TD class=2-td2-center width="23%">
										<DIV align=left>&nbsp;<html:text name="jmsbForm"
											property="sbrq" /><FONT color=red>*</FONT></DIV>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
							<BR>
							<TABLE border=0 cellSpacing=0 class=tabled-99 width="98%"
								cellpadding="0">
								<TBODY>
									<TR>
										<TD class=2-td2-t-left colspan="4" width="70%">
										<DIV align=left>&nbsp;&nbsp;������д����</DIV>
										</TD>
										<TD class=2-td2-t-center width="30%" colspan="2">
										<DIV align=right><img
											onMouseOver="MM_swapImage('add','','<%=static_file%>/images/xinzeng2.jpg',1)"
											onMouseOut="MM_swapImgRestore()" value="����" id="Submit522"
											name="addnsr" src="<%=static_file%>/images/xinzeng1.jpg"
											width="79" onClick="addRow()" height="22"> &nbsp;&nbsp;
										</DIV>
										</TD>

									</TR>
									<TR>
										<TD colspan="6" valign="top">

										<table id="TABLE_LIST" border="0" cellpadding="0"
											cellspacing="0">
											<tr>
												<td width="2%" class="2-td1-left">���</td>
												<td width="25%" class="2-td1-left">��˰������<span
													class="bitian">*</span></td>
												<td width="13%" class="2-td1-left">��ϵ�绰</td>
												<td width="13%" class="2-td1-left">���֤������<span
													class="bitian">*</span></td>
												<td width="20%" class="2-td1-left">���֤������<span
													class="bitian">*</span></td>
												<td width="5%" class="2-td1-left">����<span
													class="bitian">*</span></td>
												<td width="7%" class="2-td1-left">�Ƿ�����Ȩ��<span
													class="bitian">*</span></td>
												<td width="5%" class="2-td1-center">&nbsp;&nbsp;</td>
											</tr>



										</table>
										</TD>
									</TR>


									<TR>
										<TD class=2-td2-left width="8%" rowspan="7">
										<DIV align=right>���ط���</DIV>
										<DIV align=right>Ȩ��ת��</DIV>
										</TD>
										<TD class=2-td2-left width="15%">
										<DIV align=right>���ز���Ŀ����&nbsp;</DIV>
										</TD>
										<TD colspan="4" class=2-td2-center width="15%">
										<DIV align=left><html:text property="fdcxmmc" size="60"
											maxlength="100" /><FONT color=red>*</FONT></DIV>
										</TD>
									</TR>
									<TR>
										<TD class=2-td2-left width="15%";>
										<DIV align=right>��Լǩ��ʱ��&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left width="19%">
										<DIV align=left><html:text property="hyqdsj" size="15" /><FONT
											color=red>*</FONT><br>
										(yyyymmdd)</DIV>
										</TD>


										<TD class=2-td2-left width="19%">
										<DIV align=right>����&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="33%">
										<DIV align=left><bean:define id="list" name="jmsbForm"
											property="flList" /> <html:select property="fl">
											<html:options collection="list" labelProperty="qstdfwytmc"
												property="qstdfwytdm" />
										</html:select><FONT color=red>*</FONT> <html:hidden property="flmc" /></DIV>
										</TD>


									</TR>

									<TR>
										<TD class=2-td2-left width="25%";>
										<DIV align=right>���ء����������ַ&nbsp;</DIV>
										</TD>
										<TD colspan="4" class=2-td2-center width="15%">
										<DIV align=left><html:text property="tdfwzldz" size="60"
											maxlength="200" /><FONT color=red>*</FONT></DIV>
										</TD>
									</TR>


									<TR>
										<TD class=2-td2-left width="15%";>
										<DIV align=right>���ء�����Ȩ��ת������&nbsp;</DIV>
										</TD>
										<!-- TD class=2-td2-left width="19%" colspan="4"-->
										<TD colspan="4" class=2-td2-center width="19%">
										<DIV align=left><html:hidden property="tdfwqszylxmc" />

										<bean:define id="list" name="jmsbForm"
											property="tdfwqszylxList" /> <html:select
											property="tdfwqszylx" onchange="checkSelect()">
											<html:options collection="list" labelProperty="qszyxsmc"
												property="qszyxsdm" />
										</html:select><FONT color=red>*</FONT></DIV>
										</TD>


										<!-- TD class=2-td2-left width="19%">
										<DIV align=right>�������&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="33%">
										<DIV align=left><html:hidden property="fwlbmc" /> <bean:define
											id="list" name="jmsbForm" property="fwlbList" /> <html:select
											property="fwlb" disabled="true">
											<html:options collection="list" labelProperty="fwlxmc"
												property="fwlxdm" />
										</html:select></DIV>
										</TD-->
									</TR>




									<TR>
										<TD class=2-td2-left width="25%";>
										<DIV align=right>���ء�����Ȩ��ת�����&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left width="15%">
										<DIV align=left>���أ�<html:text property="tdfwqszymj"
											size="15" maxlength="14" />�O</DIV>
										</TD>
										<TD class=2-td2-left width="17%">
										<DIV align=right>���ݽ������&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="33%">
										<DIV align=left><html:text property="fwjzmj" size="20"
											maxlength="14" />�O</DIV>
										</TD>
									</TR>


									<!-- TR>
										<TD class=2-td2-left width="25%";>
										<DIV align=right>�ݻ���&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left width="15%">
										<DIV align=left><html:select property="rjl">
											<html:option value="01">1.0���Ϻ�1.0</html:option>
											<html:option value="00">1.0����</html:option>
										</html:select></DIV>
										</TD>
										<TD class=2-td2-left width="17%">
										<DIV align=right>���ؼ���&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="33%">
										<DIV align=left><html:select property="tdjc">
											<html:option value="01">һ��</html:option>
											<html:option value="02">����</html:option>
											<html:option value="03">����</html:option>
											<html:option value="04">�ļ�</html:option>
											<html:option value="05">�弶</html:option>
											<html:option value="06">����</html:option>
											<html:option value="07">�߼�</html:option>
											<html:option value="08">�˼�</html:option>
											<html:option value="09">�ż�</html:option>
											<html:option value="10">ʮ��</html:option>
											<html:option value="11">����ʮ��</html:option>
										</html:select></DIV>
										</TD>
									</TR-->


									<TR>
										<TD class=2-td2-left width="8%" rowspan="2">
										<DIV align=right>�ɽ��۸� <FONT color=red>*</FONT></DIV>
										<TD class=2-td2-left width="5%">
										<DIV align=left><html:text property="cjjgyrmb" size="20"
											maxlength="15" /><br>
										Ԫ(�����)</DIV>
										</TD>
										<TD class=2-td2-left width="17%">
										<DIV align=right>˰����غ˶��۸�&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="32%">
										<DIV align=left><html:text property="pgjg" size="20"
											maxlength="15" /> <br>
										Ԫ(�����)</DIV>
										</TD>
									</TR>

									<TR>
										<TD class=2-td2-left width="15%">
										<DIV align=left><html:text property="cjjgywb" size="20"
											maxlength="15" onchange="fnConvertWb(this,hn,zhyrmb)" /><br>
										Ԫ(���)</DIV>
										</TD>
										<TD class=2-td2-center width="33%">
										<DIV align=left><html:hidden property="bzmc" /> <bean:define
											id="list" name="jmsbForm" property="bzList" /> <html:select
											property="bz" onchange="checkSubmit('GetHL')">
											<html:options collection="list" labelProperty="bzmc"
												property="bzdm" />
										</html:select></DIV>
										<DIV align=left>
										����:<html:text property="hn" size="15" maxlength="15"
											onchange="fnConvertWb(cjjgywb,this,zhyrmb)" /></DIV>
										</TD>

										<TD class=2-td2-center colspan="2" width="25%">
										<DIV align=left><html:text property="zhyrmb" size="20"
											maxlength="15" /><br>
										�ۺ�Ԫ(�����)</DIV>
										</TD>

									</tr>

									<TR>
										<TD class=2-td2-left width="15%";>
										<DIV align=right>�����˰����˰����<FONT color=red>*</FONT>&nbsp;</DIV>
										</TD>
										<TD colspan="5" class=2-td2-center width="85%">
										<DIV align=left><html:hidden property="qsjmlbmc" /> 
										<html:hidden property="qsjmlb" /><bean:define
											id="list" name="jmsbForm" property="qsjmlbList" /> 

											<logic:iterate id="item" name="list">
											   <input type="radio" name="qsjmlbSelect" onclick="checkSelect('<bean:write name="item" property="qsjmlbdm"/>','<bean:write name="item" property="jmxzdm"/>')" value='<bean:write name="item" property="qsjmlbdm"/>'>
												 <bean:write name="item"  property="qsjmlbmc"/>
												<br>
											</logic:iterate>
										
										
										</DIV>
										</TD>
									</TR>

									<TR id="qtjmly" style="display: none">
										<TD class=2-td2-left width="15%";>
										<DIV align=right>������������<FONT color=red>*</FONT>&nbsp;</DIV>
										</TD>
										<TD colspan="5" class=2-td2-center width="85%">
										<DIV align=left><html:hidden property="qtjmlybeizhu" />
										
										<bean:define id="jmxzList" name="jmsbForm" property="qsjmxzList" /> 
										
										<html:select property="qsjmxzdm" onchange="checkJmxzdm(this);">
										
											<logic:iterate id="jmxz" name="jmxzList">
											   <option value='<bean:write name="jmxz" property="jmxzdm"/>'>
											   <bean:write name="jmxz" property="jmxzwh"/> <bean:write name="jmxz" property="jmxzbz"/>
											   </option>
											</logic:iterate>
										
										
										</html:select>
										</DIV>
										</TD>
									</TR>
									
									<TR>
										<TD class=2-td2-left width="15%">
										<DIV align=right>��ע&nbsp;</DIV>
										</TD>
										<TD colspan="5" class=2-td2-center width="85%">
										<DIV align=left><html:textarea property="beizhu"
											cols="45" rows="5" /></DIV>
										</TD>
									</TR>

								</TBODY>
							</TABLE>
							<BR>

							<DIV align=center><IMG alt=���� height=22 id=baocun
								name=Submit63 onclick="checkSubmit('Save')"
								onmouseout=MM_swapImgRestore()
								onmouseover="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
								src="<%=static_file%>images/baocun1.jpg" style="CURSOR: hand"
								width=79> <IMG alt=�˳� height=22 id=tuichu name=tuichu
								onclick="checkSubmit('Return');" onmouseout=MM_swapImgRestore()
								onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
								src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand"
								width=79></DIV>
							<BR>

						</html:form> <%@ include file="../include/Bottom.jsp"%>
</BODY>
</HTML>
<script language=JavaScript type='text/JavaScript'>
    <%-- %>fnConvertWb(document.all.cjjgywb,document.all.hn,document.all.zhyrmb);<% --%>
</script>
