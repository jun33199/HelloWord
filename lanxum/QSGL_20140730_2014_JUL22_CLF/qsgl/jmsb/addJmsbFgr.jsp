<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>

<HTML>
<HEAD>
<TITLE>��˰�����걨�Ǹ�����Ϣ¼���</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<script language="javascript" src="../js/qscommon.js"></script>
<script language="javascript" src="<%=static_file%>/js/inputchecker.js"></script>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js"
	type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
<META content="MSHTML 5.00.2919.6307" name=GENERATOR>
</HEAD>


<SCRIPT language=JavaScript>
function checkSubmit(operationType)
{

  document.forms[0].operationType.value=operationType;

  if(operationType=="GetNsrxx" )
  {


  }

  if(operationType=="Save" )
  {	
	 
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
	 
	  	 

  	if (document.forms[0].jsjdm.value == "")
  	{
  	   alert("��������벻��Ϊ�գ����������룡");
  	   document.forms[0].jsjdm.focus();
  	   return false;
  	}

  	  	if (document.forms[0].nsrmc.value == "")
  	{
  	   alert("��˰�����Ʋ���Ϊ�գ����ȡ�Ǽ���Ϣ��");
  	   document.all.imgGetNsrxx.focus();
  	   return false;
  	}

        if (document.forms[0].lxrxm.value == "")
  	{
  	   alert("��ϵ����������Ϊ�գ����������룡");
  	   document.forms[0].lxrxm.focus();
  	   return false;
  	}

    if (document.forms[0].fdcxmmc.value == ""){
  	   alert("���ز���Ŀ���Ʋ���Ϊ�գ����������룡");
  	   document.forms[0].fdcxmmc.focus();
  	   return false;
  	}
  	
    if (document.forms[0].hyqdsj.value == "" || !checkDate(document.forms[0].hyqdsj.value)){
  	   alert("��Լǩ��ʱ�䲻��Ϊ�ջ��ʽ����ȷ�����������룡");
  	   document.forms[0].hyqdsj.focus();
  	   return false;
  	}
  	
    if (!cmpDate(document.forms[0].hyqdsj.value,document.forms[0].xtdqsj.value)){
  	   alert("��Լǩ��ʱ�䲻�ܴ��ڵ�ǰʱ�䣬���������룡");
  	   document.forms[0].hyqdsj.focus();
  	   return false;
  	}

    if (document.forms[0].tdfwzldz.value == ""){
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
    if(document.forms[0].cjjgyrmb.value != ""){
	    if (!FN_QSCheckNumberDigit(document.forms[0].cjjgyrmb.value,2,"����ҳɽ��۸�"))
	    {
	      document.forms[0].cjjgyrmb.focus();
	      return false;
	    }
    }

    if(document.forms[0].pgjg.value != ""){
	    if (!FN_QSCheckNumberDigit(document.forms[0].pgjg.value,2,"����������۸�"))
	    {
	      document.forms[0].pgjg.focus();
	      return false;
	    }
    }

    if(document.forms[0].cjjgywb.value != ""){
	    if (!FN_QSCheckNumberDigit(document.forms[0].cjjgywb.value,2,"��ҳɽ��۸�"))
	    {
	      document.forms[0].cjjgywb.focus();
	      return false;
	    }
    }

    if(document.forms[0].zhyrmb.value != ""){
	    if (!FN_QSCheckNumberDigit(document.forms[0].zhyrmb.value,2,"�ۺ�����Ҽ۸�"))
	    {
	      document.forms[0].zhyrmb.focus();
	      return false;
	    }
    }

    if(document.forms[0].hn.value != ""){
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
			document.forms[0].yhbs.value=<%=Constants.ZB_YHBS_FGR%>
			//document.forms[0].zjlxmc.value = document.forms[0].sfzjlx.options[document.forms[0].sfzjlx.selectedIndex].text;
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
			<%--document.forms[0].bzmc.value = document.forms[0].bz.options[document.forms[0].bz.selectedIndex].text;--%>
            if (document.forms[0].khyhdm.value != "")
            {
			   document.forms[0].khyhmc.value = document.forms[0].khyhdm.options[document.forms[0].khyhdm.selectedIndex].text;
			}
			document.forms[0].nsrlxmc.value = document.forms[0].nsrlx.options[document.forms[0].nsrlx.selectedIndex].text;

  }
  document.forms[0].submit();
}
function initPage()
{
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

function checkJmxzdm(obj){
	document.forms[0].qtjmlybeizhu.value = obj.options[obj.selectedIndex].text;
}

</SCRIPT>
<!----��ҳ���ͷ�ļ��ͱ�ҳ������·��----->
<BODY bgColor=#ffffff leftMargin=0
	onload="MM_preloadImages(
              '<%=static_file%>imagess/dayin2.jpg',
              '<%=static_file%>imagess/fanhui2.jpg',
              '<%=static_file%>imagess/tuichu2.jpg',
              '<%=static_file%>imagess/diyitiao2.jpg',
              '<%=static_file%>imagess/shangyitiao2.jpg',
              '<%=static_file%>imagess/zuimotiao2.jpg',
              '<%=static_file%>imagess/xinzeng2.jpg',
              '<%=static_file%>imagess/shanchu2.jpg');initPage();"
	topMargin=0 marginheight="0" marginwidth="0">


<jsp:include page="/include/Header.jsp" flush="true">
	<jsp:param name="subtitle" value="��˰�����걨¼��>��˰�����걨�Ǹ�����Ϣ¼���" />
	<jsp:param name="helpURL" value="" />
</jsp:include>
<SCRIPT language=javascript>

</SCRIPT>
<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%"
	width="80%">
	<TBODY>
		<TR>
			<TD vAlign=top>
			<TABLE align=center cellSpacing=0 class=table-99>
				<TBODY>
					<TR>
						<TD class="1-td1">�����еط�˰�����˰�����걨��-�Ǹ���</TD>
					</TR>
					<TR>
						<TD class="1-td2" vAlign=top><html:form
							action="/jmsb/addJmsbFgr.do">
							<html:hidden property="operationType" />
							<html:hidden property="xtdqsj" />
							<html:hidden property="ymczlxdm" />
							<html:hidden property="ztbs" value="<%=Constants.ZB_ZTBS_BC%>" />
							<html:hidden property="yhbs" value="<%=Constants.ZB_YHBS_FGR%>" />


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
							<TABLE align=center cellSpacing=0 class=table-99 width="98%">
								<TBODY>
									<TR>
										<TD class=2-td2-t-left width="8%" rowspan="5">
										<DIV align=right>�Ǹ�����</DIV>
										<DIV align=right>д����</DIV>
										</TD>


									</tr>

									<tr>
										<TD class=2-td2-t-left width="15%">
										<DIV align=right>��˰���������&nbsp;</DIV>
										</TD>

										<TD class=2-td2-t-left width="25%">
										<DIV align=left><html:text property="jsjdm" size="10"
											maxlength="8" /> <FONT color=red>*</FONT> <span
											id="getDJimage1" style="visibility:visible"> <input
											type="image" name="imgGetNsrxx" value="��ȡ�Ǽ���Ϣ" alt="��ȡ�Ǽ���Ϣ"
											onClick="javascript:return checkSubmit('GetNsrxx');"
											onMouseOver="MM_swapImage('b-hqdjxx1','','<%=static_file%>images/b-hqdjxx2.jpg',1)"
											onMouseOut="MM_swapImgRestore()"
											src="<%=static_file%>images/b-hqdjxx1.jpg" width="79"
											height="22" id="b-hqdjxx1"></span></DIV>
										</TD>



										<TD class=2-td2-t-left width="19%">
										<DIV align=right>��˰������&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-t-center width="33%">
										<DIV align=left><html:hidden property="nsrlxmc" /> <bean:define
											id="list" name="jmsbForm" property="nsrlxList" /> <html:select
											property="nsrlx">
											<html:options collection="list" labelProperty="nsrlxmc"
												property="nsrlxdm" />
										</html:select></DIV>
										</TD>
									</TR>



									<TR>
										<TD class=2-td2-left width="19%">
										<DIV align=right>��˰������&nbsp;</DIV>
										</TD>
										<TD colspan="4" class=2-td2-center width="33%">
										<DIV align=left><bean:write name="jmsbForm"
											property="nsrmc" />&nbsp;<html:hidden property="nsrmc" /></DIV>
										</TD>
									</TR>

									<TR>
										<TD class=2-td2-left width="15%">
										<DIV align=right>��������&nbsp;</DIV>
										</TD>
										<TD class=2-td2-center width="85%" colspan="4">
										<DIV align=left><html:hidden property="khyhmc" /> <bean:define
											id="list" name="jmsbForm" property="khyhList" /> <html:select
											property="khyhdm">
											<html:options collection="list" labelProperty="khyhmc"
												property="yhdm" />
										</html:select></DIV>
										</TD>


										<!--TD class=2-td2-left >
                  <DIV align=right>�����˺�&nbsp;</DIV></TD>
                <TD class=2-td2-center width="33%">
                   <DIV align=left>&nbsp;<label id="labelYhzh"/>

										<bean:define id="list" name="jmsbForm" property="khyhList"/>
                    <html:select property="yhzh">
                    <html:options collection="list" labelProperty="zh" property="zh"/>
                    </html:select>
                    <html:hidden property="yhzh" />
                    
                    </DIV>
										</TD-->

									</TR>


									<TR>
										<TD class=2-td2-left width="15%">
										<DIV align=right>��ϵ������&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left width="33%">
										<DIV align=left><html:text property="lxrxm" size="10"
											maxlength="50" /> <FONT color=red>*</FONT></DIV>
										</TD>


										<TD class=2-td2-center width="19%">
										<DIV align=right>��ϵ�绰&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="33%">
										<DIV align=left><html:text property="lxdh" size="10"
											maxlength="20" /></DIV>
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
										<DIV align=left><html:text property="hyqdsj" size="15" />(yyyymmdd)<FONT
											color=red>*</FONT></DIV>
										</TD>
										<TD class=2-td2-left width="19%">
										<DIV align=right>����&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="33%">
										<DIV align=left><bean:define id="list" name="jmsbForm"
											property="flList" /> <html:select property="fl">
											<html:options collection="list" labelProperty="qstdfwytmc"
												property="qstdfwytdm" />
										</html:select> <html:hidden property="flmc" /></DIV>
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
										<!--TD class=2-td2-left width="19%"-->
										<TD colspan="4" class=2-td2-center width="19%">
										<DIV align=left><html:hidden property="tdfwqszylxmc" />

										<bean:define id="list" name="jmsbForm"
											property="tdfwqszylxList" /> <html:select
											property="tdfwqszylx" onchange="checkSelect()">
											<html:options collection="list" labelProperty="qszyxsmc"
												property="qszyxsdm" />
										</html:select></DIV>
										</TD>

										<!--TD class=2-td2-left width="19%">
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
										<TD class=2-td2-left width="25%">
										<DIV align=right>���ء�����Ȩ��ת�����&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left width="15%">
										<DIV align=left>���أ�<html:text property="tdfwqszymj"
											size="15" maxlength="14" />�O</DIV>
										</TD>
										<TD class=2-td2-left width="17%">
										<DIV align=right>���ݽ������&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="32%">
										<DIV align=left><html:text property="fwjzmj" size="15"
											maxlength="14" />�O</DIV>
										</TD>
									</TR>

									<!--TR>
										<TD class=2-td2-left width="25%">
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
										<TD colspan="2" class=2-td2-center width="32%">
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
										<DIV align=right>�ɽ��۸�<FONT color=red>*</FONT></DIV>
										<TD class=2-td2-left width="5%">
										<DIV align=left><html:text property="cjjgyrmb" size="15"
											maxlength="15"  /><br>
										Ԫ(�����)</DIV>
										</TD>
										<TD class=2-td2-left width="17%">
										<DIV align=right>˰����غ˶��۸�&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="32%">
										<DIV align=left><html:text property="pgjg" size="15"
											maxlength="15"  /><br>
										Ԫ(�����)</DIV>
										</TD>
									</TR>

									<TR>
										<TD class=2-td2-left width="15%">
										<DIV align=left><html:text property="cjjgywb" size="15"
											maxlength="15" onchange="fnConvertWb(this,hn,zhyrmb)" /><br>
										Ԫ(���)</DIV>
										</TD>
										<TD class=2-td2-left width="25%">
										<DIV align=left><html:hidden property="bzmc" /> <bean:define
											id="list" name="jmsbForm" property="bzList" /> <html:select
											property="bz" onchange="checkSubmit('GetHL')">
											<html:options collection="list" labelProperty="bzmc"
												property="bzdm" />
										</html:select> ����:&nbsp;<html:text property="hn" size="15" maxlength="15"
											onchange="fnConvertWb(cjjgywb,this,zhyrmb)" /></DIV>
										</TD>


										<TD class=2-td2-center width="17%" colSpan=2>
										<DIV align=left><html:text property="zhyrmb" size="15"
											maxlength="15" /><br>
										�ۺ�Ԫ(�����)</DIV>
										</TD>
									</TR>

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
										<DIV align=left>
										<html:hidden property="qtjmlybeizhu" />
										
										<bean:define id="jmxzList" name="jmsbForm" property="qsjmxzList" /> 
										
										<select name="qsjmxzdm" onchange="checkJmxzdm(this);">
										
											<logic:iterate id="jmxz" name="jmxzList">
											   <option value='<bean:write name="jmxz" property="jmxzdm"/>'>
											   <bean:write name="jmxz" property="jmxzwh"/> <bean:write name="jmxz" property="jmxzbz"/>
											   </option>
											</logic:iterate>
										
										
										</select></DIV>
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




