<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@page import="com.creationstar.bjtax.qsgl.VisionLogic.form.BlAllSbWszForm"%>

<HTML><HEAD><TITLE>��¼</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>

<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<script language="javascript" src="../js/qscommon.js"></script>

<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
<META content="MSHTML 5.00.2919.6307" name=GENERATOR></HEAD>

<SCRIPT language=JavaScript>
function checkSubmit(operationType)
{
	document.forms[0].operationType.value=operationType;
	document.forms[0].submit();
}

//�ı���˺ͷǸ��˵����
function checkSelectBl()
{
	if(document.forms[0].bllx[0].checked)
	{
		document.all.operationType.value="ChangeBllx";
		document.forms[0].submit();
	}

}


//��̬���һ��
function doAdd()
{
	var row = JmsTable.insertRow(JmsTable.rows.length);
	var itemsize = JmsTable.rows.length-3;

	<%/*�걨���*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name='sbbhList' size='20' maxlength='19'>"));


	<%/*��˰֤��*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name='wszList' size='20' maxlength='<%=Constants.PZZLCD%>'>"));

	<%/*����ֱ�*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(document.createElement("<input name='ndzbList' size='4' maxlength='4'>"));

	<%/*Ʊ֤����*/%>
	var sel = document.createElement("<select name=\"pzzlList\">");

	var opt = document.createElement("<OPTION>");
	opt.value = "<%=Constants.WSZ_PZZLDM_SG%>";
	opt.text = "�ֹ�Ʊ";
	sel.options.add(opt);

	var opt = document.createElement("<OPTION>");
	opt.value = "<%=Constants.WSZ_PZZLDM%>";
	opt.text = "����Ʊ";
	sel.options.add(opt);

	var cell = row.insertCell();
	cell.className = "2-td2-left";
	cell.appendChild(sel);


	<%/*ɾ��*/%>
	var cell = row.insertCell();
	cell.className = "2-td2-center";
	cell.innerHTML = "<a href=\"#\" id=\"delIndex\" onclick=\"doDelete(this);return false;\">ɾ��</a>";

	return false;
}

<%/*ɾ��*/%>
function doDelete(obj)
{
	if(confirm("ȷ��ɾ��?"))
	{
		var delRows = JmsTable.rows.length-3;
		if(delRows==1)
		{
			alert("���ݲ���ȫ��ɾ��!")
			return false;
		}
		for(var i=0;i<delRows;i++)
		{
			if(delIndex[i] == obj)
       				JmsTable.deleteRow(i+3);
         }

    }
	else
	{
       	return false;
    }
}



<%/*����*/%>
function doSave()
{
	itemsize = JmsTable.rows.length-3;
        if(itemsize == 0)
	{
		if(confirm("������ձ����걨���ϣ�ȷ�ϣ�"))
		{
			document.forms[0].operationType.value = "Delete";
			document.forms[0].submit();
			return true;
		}
		else
		{
			return false;
		}
	}
	else
	{
        if(itemsize > 1)
		{
			for(var i=0;i<itemsize;i++)
			{

				if(document.forms[0].sbbhList[i].value == null ||
					document.forms[0].sbbhList[i].value == "" ||
                                   	document.forms[0].wszList[i].value == null ||
                                   	document.forms[0].wszList[i].value == "" ||
												document.forms[0].ndzbList[i].value==null||
												document.forms[0].ndzbList[i].value==""
                  )
                {
						alert("��������д��Ϣ��");
						return false;
				}

				if(document.forms[0].sbbhList[i].value.length!=19)
				{
						alert("�걨��ų���ӦΪ19λ");
						document.forms[0].sbbhList[i].focus();
						return false;
				}
				if(document.forms[0].wszList[i].value.length > '<%=Constants.PZZLCD%>')
				{
						alert("���ֽɿ���ų���ӦΪ��Ӧλ��");
						document.forms[0].wszList[i].focus();
						return false;
				}
				if(!FN_QSCheckNumberDigit(document.forms[0].ndzbList[i].value,0,"����ֱ�"))
				{

						return false;
				}
				if(document.forms[0].ndzbList[i].value.length!=4)
				{
						alert("����ֱ�ӦΪ4λ");
						return false;
				}


				var sbbh_i = document.forms[0].sbbhList[i].value;
                var wsz_i = document.forms[0].wszList[i].value;
				for(var j=i+1;j<itemsize;j++)
				{
					var sbbh_j = document.forms[0].sbbhList[j].value;
                    var wsz_j = document.forms[0].wszList[j].value;
					if(sbbh_i == sbbh_j && wsz_i == wsz_j)
					{
						alert("�������ݵ��걨��ź���˰֤����ͬ��");
						return false;
					}
				}


            }
        }
        else if(itemsize == 1)
		{

			if(document.forms[0].sbbhList.length == null)
			{

                		if(document.forms[0].sbbhList.value == null ||
                       			document.forms[0].sbbhList.value == "" ||
                       			document.forms[0].wszList.value == null ||
								document.forms[0].wszList.value == "" ||
                       			document.forms[0].ndzbList.value == null ||
							   document.forms[0].ndzbList.value == "")
                       		{
                       			alert("��������д�걨���ϣ�");
                               		return false;
                       		}

						if(document.forms[0].sbbhList.value.length!=19)
						{
								alert("�걨��ų���ӦΪ19λ");
								document.forms[0].sbbhList.focus();
								return false;
						}
						if(document.forms[0].wszList.value.length > '<%=Constants.PZZLCD%>')
						{
								alert("���ֽɿ���ų���ӦΪ��Ӧλ��");
								document.forms[0].wszList.focus();
								return false;
						}

						if(!FN_QSCheckNumberDigit(document.forms[0].ndzbList.value,0,"����ֱ�"))
							{

								return false;
							}
						if(document.forms[0].ndzbList.value.length!=4)
							{
								alert("����ֱ�ӦΪ4λ");
								return false;
							}

			}
			else
			{

                		if(document.forms[0].sbbhList[0].value == null ||
                       			document.forms[0].sbbhList[0].value == "" ||
                       			document.forms[0].wszList[0].value == null ||
                       			document.forms[0].wszList[0].value == "" ||
								document.forms[0].ndzbList[0].value == null ||
                       			document.forms[0].ndzbList[0].value == "")
                       		{
                       			alert("��������д�걨���ϣ�");
                               		return false;
                       		}

							if(document.forms[0].sbbhList[0].value.length!=19)
							{
									alert("�걨��ų���ӦΪ19λ");
									document.forms[0].sbbhList[0].focus();
									return false;
							}
							if(document.forms[0].wszList[0].value.length > '<%=Constants.PZZLCD%>')
							{
									alert("���ֽɿ���ų���ӦΪ��Ӧλ��");
									document.forms[0].wszList[i].focus();
									return false;
							}

							if(!FN_QSCheckNumberDigit(document.forms[0].ndzbList[0].value,0,"����ֱ�"))
							{

								return false;
							}

						if(document.forms[0].ndzbList[0].value.length!=4)
							{
							alert("����ֱ�ӦΪ4λ");
							return false;
							}
			}
		}
		if(confirm("ȷ�ϱ���"))
		{
			document.forms[0].operationType.value = "Confirm";
			document.forms[0].submit();
       			return true;
    	}
		else
		{
       			return false;
    	}
	}
}



</SCRIPT>
<!----��ҳ���ͷ�ļ��ͱ�ҳ������·��----->

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="��¼>��¼ҵ�����"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>
<SCRIPT language=javascript>

</SCRIPT>
 <TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>

         <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>��¼ҵ�����</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
          <html:form action="/qsbl/blSbWsz.do">
              <html:hidden property="operationType"/>


            <TABLE  id="JmsTable" border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>
			  <BR>
          <TR>
            <TD class=2-td2-t-left >��¼����</TD>
            <TD class=2-td2-t-center colspan=4>
			<DIV align=left>
            <html:radio property="bllx" value="0" onclick="checkSelectBl()"/>�ǻ���
            <html:radio property="bllx" value="1" onclick="checkSelectBl()"/>����
             </DIV>
			 </TD>
          </TR>

			  <TR>
			  	<TD class=2-td2-left >�ɿ����</TD>
			  	<html:hidden property="jksh"/>
			  	<TD class=2-td2-center colspan="4">
				<DIV align=left>&nbsp;
				<bean:write name="blAllSbWszForm" property="jksh" />
				</DIV>
				</TD>
			</tr>

				<tr>
				<TD class=2-td2-left width="30%">�걨���</TD>
				<TD class=2-td2-left width="30%">���ֽɿ����</TD>
				<TD class=2-td2-left width="15%">����ֱ�</TD>
				<TD class=2-td2-left width="15%">Ʊ֤����</TD>
				<TD class=2-td2-center width="10%">&nbsp;</TD>
				</tr>

<%
BlAllSbWszForm aform = (BlAllSbWszForm)session.getAttribute("blAllSbWszForm");

String[] a = aform.getSbbhList();
String[] b = aform.getWszList();
String[] c = aform.getNdzbList();
String[] d = aform.getPzzlList();
if(a != null && a.length > 0)
{
	for(int i=0;i<a.length;i++)
	{
%>

			<tr>
				<TD class=2-td2-left>
				<input name="sbbhList" value="<%=a[i]%>" size="20" maxlength="19"/></td>

				<TD class=2-td2-left >
				<input name="wszList" value="<%=b[i]%>" size="20" maxlength="<%=Constants.PZZLCD%>"/></td>

				<TD class=2-td2-left>
				<input name="ndzbList" value="<%=c[i]%>" size="4" maxlength="4"/></td>


				<TD class=2-td2-left>
				<select  name="pzzlList" >
                    <%
					             if(d[i].equals(Constants.WSZ_PZZLDM_SG))
                                 {
					%>
								<option value="<%=Constants.WSZ_PZZLDM_SG%>" selected>�ֹ�Ʊ</option>
								<option value="<%=Constants.WSZ_PZZLDM%>">����Ʊ</option>
                <%
                                 }
                                 else
                                 {
					%>
  								<option value="<%=Constants.WSZ_PZZLDM_SG%>" >�ֹ�Ʊ</option>
								<option value="<%=Constants.WSZ_PZZLDM%>" selected>����Ʊ</option>
                 <% }%>
				</select>
				</TD>

				<td nowrap class="2-td2-center">
				<a href="#" id="delIndex" onclick="doDelete(this);return false;">ɾ��</a></td>
			 </TR>
<%
    }
}
else
{
%>

			<tr>
				<TD class=2-td2-left>
				<DIV align=center><html:text  property="sbbhList" size="20" maxlength="19"/></DIV></td>

				<TD class=2-td2-left >
				<DIV align=center><html:text  property="wszList" size="20" maxlength="<%=Constants.PZZLCD%>"/></DIV></td>

				<TD class=2-td2-left>
				<DIV align=center><html:text  property="ndzbList" size="4" maxlength="4"/></DIV></td>


				<TD class=2-td2-left>
				<DIV align=center>
				<html:select  property="pzzlList">
								<html:option value="<%=Constants.WSZ_PZZLDM_SG%>">�ֹ�Ʊ</html:option>
								<html:option value="<%=Constants.WSZ_PZZLDM%>">����Ʊ</html:option>
				</html:select>
				</DIV>
				</TD>

				<td nowrap class="2-td2-center">
				<DIV align=center><a href="#" id="delIndex" onclick="doDelete(this);return false;">ɾ��</a> </DIV></td>
			 </TR>
<% }%>
			</TBODY>
			</TABLE>


			<br>

            <DIV align=center>


            <IMG alt=��� height=22 id=tianjia name=Submit6656
            onclick="doAdd('')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('tianjia','','<%=static_file%>images/tianjia2.jpg',1)"
            src="<%=static_file%>images/tianjia1.jpg" style="CURSOR: hand" width=79>

            <IMG alt=���� height=22 id=baocun name=Submit63
            onclick="doSave()" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
            src="<%=static_file%>images/baocun1.jpg" style="CURSOR: hand" width=79>

            <IMG alt=���� height=22 id=tuichu name=fanhui
            onclick="checkSubmit('Return');" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('fanhui','','<%=static_file%>images/fanhui2.jpg',1)"
            src="<%=static_file%>images/fanhui1.jpg" style="CURSOR: hand" width=79>
            </DIV><BR>


<%@ include file="../include/Bottom.jsp" %>
	</TBODY>
	</TABLE>

     </html:form>

</BODY></HTML>

