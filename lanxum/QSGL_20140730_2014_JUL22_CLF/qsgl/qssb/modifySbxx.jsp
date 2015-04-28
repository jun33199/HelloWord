<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.ActionUtil"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.SbxxForm"%>
<%
	SbxxForm sbxxForm = (SbxxForm)session.getAttribute("sbxxForm");
%>

<HTML><HEAD><TITLE>�걨��Ϣ�޸ı�</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<script language="javascript" src="../js/qscommon.js"></script>
<script language="javascript" src="../js/gmit_selectcontrol.js"></script>
<script LANGUAGE="javascript" src="../js/Const.js"></script>
<script LANGUAGE="javascript" src="../js/DTColumn.js"></script>
<script LANGUAGE="javascript" src="../js/DynamicTable.js"></script>
<script LANGUAGE="javascript" src="../js/DataAwareTable.js"></script>
<script LANGUAGE="javascript" src="../js/DTColumn_Check.js"></script>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>
  <bean:define id="sbxxBo" name="sbxxForm" property="data" type="com.creationstar.bjtax.qsgl.model.bo.SbxxBo"/>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType){
  document.all.operationType.value=operationType;
  if(operationType=="ReturnView" ){
    document.forms[0].operationType.value='Redirect';
    document.forms[0].subaction.value='returnView';
  }
  else if (operationType=="Save" )
  {
    if (document.forms[0].person.value == "true")
    {
	      
       var checknsr=checkNsr();
       
       if (checknsr==false){
              return false;
       }

          if (document.forms[0].jmsje.value != "")
          {
			   		if (!FN_QSCheckNumberDigit(document.forms[0].jmsje.value,2,"����˰���"))
			   				{
			      		document.forms[0].jmsje.focus();
			      		return false;
			   				}
          }


    }
    else
    {

		    if (document.all["voFgrxx.lxrxm"].value == "")
		  	{
		  	   alert("��ϵ����������Ϊ�գ����������룡");
		  	   document.all["voFgrxx.lxrxm"].focus();
		  	   return false;
		  	}

	    	document.all["voFgrxx.nsrlxmc"].value = document.all["voFgrxx.nsrlxdm"].options[document.all["voFgrxx.nsrlxdm"].selectedIndex].text;

	    	if(document.all["voFgrxx.khyhdm"].value != "")
	    	{
	    			document.all["voFgrxx.khyhmc"].value = document.all["voFgrxx.khyhdm"].options[document.all["voFgrxx.khyhdm"].selectedIndex].text;
	    	}
    }

	    if (document.all["data.BZ"].value == "false")
	    {
	        document.all["voSbzb.jsfsmc"].value = document.all["voSbzb.jsfsdm"].options[document.all["voSbzb.jsfsdm"].selectedIndex].text;
	    }
          if(document.forms[0].bl.value=="true")
          {

				if (document.forms[0].sbrq.value == "" || !checkDate(document.forms[0].sbrq.value))
				{
				   alert("�걨���ڲ���Ϊ�ջ��ʽ����ȷ�����������룡");
				   document.forms[0].sbrq.focus();
				   return false;
				}
		  }
  }

   document.forms[0].submit();
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
    //alert(document.forms[0].dataSource_gm.value);
    return true;
}


</SCRIPT>

<SCRIPT LANGUAGE=javascript>
              <%if (sbxxBo.isPerson())
              {
              %>

//��Ȩ������
<%=sbxxForm.getCqrJs()%>

    var arySelect_zjlx =<%=ActionUtil.displaySelectDataSource(sbxxForm.getSfzjlxList(),null,"zjlxdm","zjlxmc")%>; // ֤��
    var arySelect_gj = <%=ActionUtil.displaySelectDataSource(sbxxForm.getGjdqList(),null,"gjdqdm","gjdqmc")%>;//����

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
        var dataSource_del=daList.getData();
        var numRow_del=dataSource_del.length;
        var flagConfirm=confirm("ȷ��ɾ����ǰ��¼��");
        if(flagConfirm==true){
           daList.deleteRow(daList.DynamicTable.CurrentRow);
        }
    }

<% } %>
	function initPage()
	{
              <%if (sbxxBo.isPerson())
              {
              %>
        //��˰������* ��ϵ�绰* ���֤������* ���֤������* ����* �Ƿ�����Ȩ��*
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
     <% } %>   
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

</SCRIPT>


<BODY bgColor=#ffffff leftMargin=0 onload="MM_preloadImages(
              '<%=static_file%>imagess/dayin2.jpg',
              '<%=static_file%>imagess/fanhui2.jpg',
              '<%=static_file%>imagess/tuichu2.jpg',
              '<%=static_file%>imagess/diyitiao2.jpg',
              '<%=static_file%>imagess/shangyitiao2.jpg',
              '<%=static_file%>imagess/zuimotiao2.jpg',
              '<%=static_file%>imagess/xinzeng2.jpg',
              '<%=static_file%>imagess/shanchu2.jpg'); initPage();"
              topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="��˰�걨>�걨��Ϣ�޸ı�"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>
<SCRIPT language=javascript>
<!--

//-->
</SCRIPT>



										</TD>
                                      </TR>
  </TBODY>
</TABLE>

<br>
 <html:form action="/qssb/viewSbxx.do">
 <TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%"  width=98%>
  <TBODY>
  <TR>
    <TD vAlign=top>

         <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <%if (!sbxxBo.isBZ())
          {
          %>
          <TD class=1-td1>�����еط�˰�����˰�걨��</TD></TR>
          <%
          }
          else
          {
          %>
          <TD class=1-td1>�����еط�˰��ֲ�����˰�ɼ���</TD></TR>
          <%
          }
          %>
        <TR>
          <TD class=1-td2 vAlign=top>
              <html:hidden property="operationType" value=""/>
              <html:hidden property="subaction" value=""/>
              <html:hidden property="data.BZ"/>
			  <html:hidden property="bl"/>
<input type="hidden" name="dataSource_gm" value="">
            <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>
			  <BR>

 <logic:equal name="sbxxForm" property="voSbzb.blbs" value="1">
    <TR>
        				<TD class=2-td2-t-left width="12%">�걨����</TD>
				<TD class=2-td2-t-center width="43%" colspan=3><DIV align=left><html:text name="sbxxForm" property="sbrq" />  </DIV></TD>
    </TR>
    			  <TR>
          <%if (!sbxxBo.isBZ())
          {
          %>

			  	<TD class=2-td2-left width="15%">���κ�</TD>
							  	<html:hidden name="sbxxForm" property="voSbzb.drpch"/>
			  	<TD class=2-td2-left width="35%"><DIV align=left>&nbsp<bean:write name="sbxxForm" property="voSbzb.drpch" /> </DIV></TD>

			  	<TD class=2-td2-left width="15%">��˰�걨���</TD>
							  	<html:hidden name="sbxxForm" property="voSbzb.sbbh"/>
			  	<TD class=2-td2-center width="35%"><DIV align=left>&nbsp<bean:write name="sbxxForm" property="voSbzb.sbbh" /> </DIV></TD>
          <%
          }
          else
          {
          %>
			  	<TD class=2-td2-left width="15%">������˰��Ϣ�ɼ����</TD>
							  	<html:hidden name="sbxxForm" property="voSbzb.sbbh"/>
			  	<TD class=2-td2-center width="43%" colspan=3><DIV align=left>&nbsp<bean:write name="sbxxForm" property="voSbzb.sbbh" /> </DIV></TD>
          <%
          }
          %>
			  </TR>

</logic:equal>

 <logic:equal name="sbxxForm" property="voSbzb.blbs" value="0">
			  <TR>
          <%if (!sbxxBo.isBZ())
          {
          %>

			  	<TD class=2-td2-t-left width="15%">���κ�</TD>
							  	<html:hidden name="sbxxForm" property="voSbzb.drpch"/>
			  	<TD class=2-td2-t-left width="35%"><DIV align=left>&nbsp<bean:write name="sbxxForm" property="voSbzb.drpch" /> </DIV></TD>

			  	<TD class=2-td2-t-left width="15%">��˰�걨���</TD>
							  	<html:hidden name="sbxxForm" property="voSbzb.sbbh"/>
			  	<TD class=2-td2-t-center width="35%"><DIV align=left>&nbsp<bean:write name="sbxxForm" property="voSbzb.sbbh" /> </DIV></TD>
          <%
          }
          else
          {
          %>
			  	<TD class=2-td2-t-left width="15%">������˰��Ϣ�ɼ����</TD>
							  	<html:hidden name="sbxxForm" property="voSbzb.sbbh"/>
			  	<TD class=2-td2-t-center width="35%" colspan=3><DIV align=left>&nbsp<bean:write name="sbxxForm" property="voSbzb.sbbh" /> </DIV></TD>
          <%
          }
          %>
			  </TR>
</logic:equal>

			  <TR>
			  	<TD class=2-td2-left width="12%">�ɿʽ</TD>
			  	<TD class=2-td2-left width="43%"> <DIV align=left>&nbsp;
					<logic:notEqual name="sbxxBo" property="BZ" value="true">
                                  <bean:define id="data" name="sbxxForm" property="jkfsList"  />
                                  <html:select property="voSbzb.jsfsdm" >
                                    <html:options collection="data" labelProperty="jsfsmc" property="jsfsdm" />
                                  </html:select>
                                  <html:hidden property="voSbzb.jsfsmc"/>
                   </logic:notEqual>
				<TD class=2-td2-left width="22%">�������ع����������</TD>
				<TD class=2-td2-center width="23%"><DIV align=left><html:text name="sbxxForm" property="voSbzb.fwtdbmdm" />  </DIV></TD>
			  </TR>
			  </TBODY>
			  </TABLE>

			<TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>
              <%if (sbxxBo.isPerson())
              {
              %>
              <input type=hidden name="person" value="true">
            <TR>
    <TD class=2-td2-left colspan="4" width="70%"  ><DIV align=left>&nbsp;&nbsp;������д����</DIV></TD>
    <TD class=2-td2-center  width="30%" colspan="2" >  <DIV align=right> 
    <img onMouseOver="MM_swapImage('add','','<%=static_file%>/images/xinzeng2.jpg',1)" onMouseOut="MM_swapImgRestore()"  value="����" id="Submit522" src="<%=static_file%>/images/xinzeng1.jpg" width="79" onClick="addRow()" height="22">
&nbsp; </DIV> &nbsp;&nbsp;</TD>       
</TR>

         <TR>
    <TD  colspan="6" valign="top" width="100%">

<table id="TABLE_LIST" border="0" cellpadding="0" cellspacing="0" class="table-99">
  <tr>
    <td width="2%" class="2-td1-left" >���</td>
	<td width="25%" class="2-td1-left">
	<%if (sbxxBo.isBZ()) {%> ����������
     <% } else {%>��˰������<%}%>
<span class="bitian">*</span></td>
    <td width="13%" class="2-td1-left">��ϵ�绰</td>
    <td width="13%" class="2-td1-left">���֤������<span class="bitian">*</span></td>
    <td width="20%" class="2-td1-left">���֤������<span class="bitian">*</span></td>
    <td width="5%" class="2-td1-left">����<span class="bitian">*</span></td>
    <td width="7%" class="2-td1-left">�Ƿ�����Ȩ��<span class="bitian">*</span></td>
    <td width="5%" class="2-td1-center">&nbsp;&nbsp;</td>
  </tr>
  
</table>
</TD>
</TR>

 				<%}%>

              <%if (!sbxxBo.isPerson())
              {
              %>
              <input type=hidden name="person" value="false">
                <TR>
                <TD class=2-td2-t-left width="15%" rowspan = "4">
                  <DIV align=right>�Ǹ�����</DIV>
                   <DIV align=right>д����</DIV>
                  <TD class=2-td2-t-left width="15%">
                   <DIV align=right>��˰���������&nbsp; </DIV>
                </TD>
                <DIV align=right>&nbsp; </DIV>
                  <TD class=2-td2-t-left width="25%">
                    <DIV align=left>
                        &nbsp;<bean:write name = "sbxxForm" property = "voFgrxx.jsjdm" />
                    </DIV>
                  </TD>


          <%if (sbxxBo.isBZ())
          {
          %>
                <TD class=2-td2-t-left width="19%">
                  <DIV align=right>����������&nbsp; </DIV></TD>
          <%
          }
            else
          {
          %>

                <TD class=2-td2-t-left width="19%">
                  <DIV align=right>��˰������&nbsp; </DIV></TD>
          <%
          }
          %>



                <TD colspan="2"  class=2-td2-t-center width="33%">
                   <DIV align=left>
                    <bean:define id="nsrlxList" name="sbxxForm" property="nsrlxList"/>
                    <html:select property="voFgrxx.nsrlxdm" >
                      <html:options collection="nsrlxList" labelProperty="nsrlxmc" property="nsrlxdm" />
                    </html:select>
                    <html:hidden property="voFgrxx.nsrlxmc"/>
                   </DIV>
                </TD>
             </TR>
             <TR>

           <%if (sbxxBo.isBZ())
          {
          %>
                <TD  class=2-td2-left width="19%">
                  <DIV align=right>����������&nbsp;</DIV></TD>
          <%
          }
           else
          {
          %>
                <TD  class=2-td2-left width="19%">
                  <DIV align=right>��˰������&nbsp;</DIV></TD>
          <%
          }
          %>


                <TD colspan="4"  class=2-td2-center width="33%">
                   <DIV align=left style="word-break:break-all">
                       &nbsp;<bean:write name = "sbxxForm" property = "voFgrxx.nsrmc" />
                   </DIV>
                 </TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left width="15%";>
                   <DIV align=right>��������&nbsp; </DIV>
                </TD>
                <TD class=2-td2-center width="85%" colspan="4">
                  <DIV align=left>
                    <bean:define id="yhList" name="sbxxForm" property="khyhList"  />
                    <html:select property="voFgrxx.khyhdm">
                      <html:options collection="yhList" labelProperty="khyhmc" property="yhdm" />
                    </html:select></DIV></td>
                    <html:hidden property="voFgrxx.khyhmc" />
                </TD>

					<html:hidden property="voFgrxx.yhzh" />
                </TR>
                <TR>
                 <TD class=2-td2-left width="15%";>
                   <DIV align=right>��ϵ������&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left width="33%">
                   <DIV align=left>
                       &nbsp;<html:text name = "sbxxForm" property = "voFgrxx.lxrxm" maxlength="50"/>
                   </DIV>
                </TD>
                <TD class=2-td2-left width="19%">
                  <DIV align=right>��ϵ�绰&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="33%">
                   <DIV align=left>
                       &nbsp;<html:text name = "sbxxForm" property = "voFgrxx.lxdh" maxlength="100"/>
                   </DIV>
                </TD>
                </TR>
          <%
          }
          %>

               <TR>
                <TD class=2-td2-left width="15%" rowspan = "2">
                  <DIV align=right>˰�����</DIV>
                  <DIV align=right>��˼���˰</DIV>
                  <TD class=2-td2-left width="15%">
                   <DIV align=right>����˰�����ֺ�&nbsp; </DIV>
                </TD>
                  <TD colspan="4" class=2-td2-center width="30%">
                    <DIV align=left><html:text name="sbxxForm" property="voSpjgxx.hdtzszh" size="40" maxlength="40"/>
                                    <IMG name="query"
			          onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
			          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/chaxun1.jpg"
			          onclick = "doSubmitForm('GetJmsje');"
			          width="79" height="22" id="chaxun1" alt="��ѯ" style="cursor:hand"></DIV>
                  </TD>
                </TD>
              </TR>
               <TR>
                 <TD class=2-td2-left width="15%";>
                   <DIV align=right>����˰���&nbsp; </DIV>
                </TD>
              <TD colspan="4" class=2-td2-center width="25%">
                <DIV align=left><html:text name="sbxxForm" property="jmsje"  maxlength="15"/>
                </DIV>
				</TD>
                  </TR>
                 <TR>
                 <TD class=2-td2-left width="15%";>
                   <DIV align=right>��ע&nbsp; </DIV>
                </TD>
                <TD colspan="5" class=2-td2-center width="84">
                  <DIV align=left><html:textarea name="sbxxForm" property="voSbzb.bz"/></DIV></TD>
                </TR>


	<!-- ���ݻ�����Ϣ-->
    		    <bean:define id="fwtdVo" name="sbxxForm" property="voTufwxx"  type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"/>
				  <tr>
				    
				    
				    <%if (!sbxxBo.isBZ())
			          {
			          %>
			          <TD class=2-td2-left width="8%" rowspan = "7">
			          <%
			          }
			          else
			          {
			          %>
			          <TD class=2-td2-left width="8%" rowspan = "5">
			          <%
			          }
			          %>
				    
				      <DIV align=right>	���ط���</DIV>
                      <DIV align=right>Ȩ��ת��</DIV>
                  </TD>
                  <TD class=2-td2-left width="15%">
                      <DIV align=right>���ز���Ŀ����&nbsp;
                      </DIV>
                  </TD>
                      <TD colspan="3" class=2-td2-left width="15%" style="word-break:break-all">
                          <DIV align=left><bean:write name="fwtdVo" property="fdcxmmc" />
                          </DIV>
                      </TD>
                      <td width="13%" class=2-td2-center>
                          <DIV align=left>
                              <logic:equal name="fwtdVo" property="sfesf" value="00">�Ƕ��ַ�</logic:equal>
                              <logic:equal name="fwtdVo" property="sfesf" value="01">���ַ�</logic:equal>
                          </div>
                      </td>
                  </TR>
                  <TR>
                      <TD class=2-td2-left width="15%";>
                          <DIV align=right>��Լǩ��ʱ��&nbsp; </DIV>
                      </TD>
                      <TD class=2-td2-left width="19%">
                          <DIV align=left><%=DataConvert.TimeStamp2String(fwtdVo.getHtqdsj())%>&nbsp;
                          </DIV>
                      </TD>
                      <%if (!sbxxBo.isBZ())
                      {
                          %>
                          <TD class=2-td2-left width="19%">
                              <DIV align=right>����ԭ��&nbsp;</DIV></TD>
                              <%
                    }
          else
          {
          %>
			            <TD class=2-td2-left width="19%">
			              <DIV align=right>����&nbsp;</DIV></TD>
		      <%
			    }
			    %>

			              <TD colspan="2"  class=2-td2-center width="33%">
			                <DIV align=left>
			                  <bean:write  name="fwtdVo" property="flmc"/>&nbsp
			                </DIV></TD>
		          </TR>
		          <TR>
		            <TD class=2-td2-left width="15%";>
		              <DIV align=right>���ء�����Ȩ��ת������&nbsp; </DIV> </TD>
		              <TD class=2-td2-left width="19%">
		                <DIV align=left>
		                  <bean:write  name="fwtdVo" property="tdfwqszymc"/></DIV></TD>
		                  <TD class=2-td2-left width="19%">
		                    <DIV align=right>�������&nbsp;</DIV></TD>
		                    <TD colspan="2"  class=2-td2-center width="33%">
		                      <DIV align=left>
		                        <bean:write  name="fwtdVo" property="fwlxmc"/>&nbsp;</DIV></TD>
		          </TR>
		          <TR>
		            <TD  class=2-td2-left width="25%";>
		              <DIV align=right>���ء����������ַ&nbsp; </DIV>
		            </TD>
		            <TD colspan="4"  class=2-td2-center width="15%" style="word-break:break-all">
		              <DIV align=left><bean:write name="fwtdVo" property="tdfwzldz" /></DIV></TD>
		            </TR>
                    <TR>
                      <TD class=2-td2-left width="25%";>
                        <DIV align=right>���ء�����Ȩ��ת�����&nbsp; </DIV>
                      </TD>
                      <TD  class=2-td2-left width="15%">
                        <DIV align=left>���أ�
                          <%=DataConvert.BigDecimal2String(fwtdVo.getTdfwqszymj())%>
                          �O </DIV></TD>
                        <TD class=2-td2-left width="17%">
                          <DIV align=right>���ݽ������&nbsp;</DIV></TD>
                          <TD colspan="2"  class=2-td2-center width="32%">
                            <DIV align=left>
                              <%=DataConvert.BigDecimal2String(fwtdVo.getFwjzmj())%>
                              �O</DIV></TD>
                    </TR>
                    
                    <%if (!sbxxBo.isBZ())
			          {
			          %>
	                    <TR>
		                    <TD class=2-td2-left width="8%" rowspan = "2">
		                      <DIV align=right>�ɽ��۸������۸�</DIV>
		                      <TD  class=2-td2-left width="5%">
		                        <DIV align=left>
	                                  <%=DataConvert.BigDecimal2String(fwtdVo.getCjjgrmb())%>
	                                  Ԫ(�����) </DIV></TD>
		                        <TD class=2-td2-left width="17%">
		                          <DIV align=right>˰����غ˶��۸�&nbsp; </DIV></TD>
		                          <TD  colspan="2"  class=2-td2-center width="32%">
		                            <DIV align=left>
	                                      <%=DataConvert.BigDecimal2String(fwtdVo.getPgjgrmb())%>
	                                      Ԫ(�����) </DIV></TD>
	                      </TR>
	                      <TR>
	                        <TD class=2-td2-left width="15%";>
	                          <DIV align=left>
	                            <%=DataConvert.BigDecimal2String(fwtdVo.getCjjgwb())%>
	                            Ԫ(���) </DIV></TD>
	                          <TD class=2-td2-left width="25%">
	                            <DIV align=left>
	                              <bean:write name="fwtdVo" property="bzmc" />
	                            </DIV>
	                            <DIV align=left>���ʣ�&nbsp;<%=DataConvert.BigDecimal2String(fwtdVo.getHldm(),5)%> </DIV></TD>
	                            <TD  colspan="2" class=2-td2-center  width="17%">
	                              <DIV align=left>
	                                <%=DataConvert.BigDecimal2String(fwtdVo.getZhjgrmb())%>
	                                �ۺ�Ԫ(�����) </DIV></TD>
	
	                       </tr>
					<%
					    }
					    %>

      <logic:iterate id="cqdata" indexId="index" length="length" name="sbxxForm" property="cqList"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq">

                <TR>
                  <TD class=2-td2-left width="8%" rowspan = "4">
                  <DIV align=right>	��Ǩ</DIV>
                   <DIV align=right>���</DIV></TD>
                  <TD class=2-td2-center width="25%">
                   <DIV align=right>����Ǩ���������ַ&nbsp; </DIV>
                </TD>
                <TD colspan="4" class=2-td2-center width="15%" style="word-break:break-all">
                  <DIV align=left>
                      &nbsp;<bean:write name = "cqdata" property = "zldz"/>
                  </DIV>
                </TD>
                </TR>
                <TR>
                 <TD class=2-td2-left width="15%";>
                   <DIV align=right>��ǨЭ�����&nbsp; </DIV>
                </TD>
                <TD colspan="4" class=2-td2-center width="25%">
                <DIV align=left>
                    &nbsp;<bean:write name = "cqdata" property = "cqxyh"/>
                </DIV>
                </TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left width="15%";>
                   <DIV align=right> ��Ǩ������&nbsp; </DIV>
                </TD>
                <TD  class=2-td2-left width="25%" colspan=4>
                  <DIV align=left>
                     &nbsp;
                     <%=DataConvert.BigDecimal2String(cqdata.getCqbce())%>
                     Ԫ(�����)
                  </DIV>
                </TD>
               </TR>
               <TR>
                 <TD class=2-td2-left width="15%";>
                   <DIV align=right>����ʹ�ò�����&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left width="25%">
                   <DIV align=left>
                      &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getBcsybce())%>Ԫ(�����)
                   </DIV>
                </TD>
                <TD class=2-td2-left width="17%">
                  <DIV align=right>��Ǩ����ʣ���&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="32%">
                  <DIV align=left>
                      &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getCqbcsye())%> Ԫ(�����)
                  </DIV>
                  </TD>
                  </TR>
       </logic:iterate>

      <logic:iterate id="gydata" indexId="index" length="length" name="sbxxForm" property="gyzfList"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf">

               <TR>
                   <TD class=2-td2-left width="8%" rowspan = "6">
                  <DIV align=right>	�ѹ�����ס��</DIV>
                  <DIV align=right>/��������ס��</DIV>
                   <DIV align=right>���г������</DIV>
				   <br>
				   </TD>
                  <TD   class=2-td2-left width="25%">
                   <DIV  align=right>���ۺ�ͬ����&nbsp;</DIV>
                </TD>
                <TD colspan="4" class=2-td2-center width="15%">
                  <DIV align=left><bean:write name="gydata" property="yggyzfqszsh" /></DIV></TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left width="15%"; style="word-break:break-all">
                   <DIV align=right>����Ȩ��֤���&nbsp; </DIV>
                </TD>
                 <TD colspan="4" class=2-td2-center width="25%">
                  <DIV align=left><bean:write name="gydata" property="fwqszsh" />&nbsp;</DIV></TD>
                  </TR>
                <TR>
                 <TD class=2-td2-left width="15%"; style="word-break:break-all">
                   <DIV align=right>�����ַ&nbsp; </DIV>
                </TD>
                 <TD colspan="4" class=2-td2-center width="25%">
                  <DIV align=left><bean:write name="gydata" property="zldz" />&nbsp;</DIV></TD>
                  </TR>
                  <TR>
                 <TD  class=2-td2-left width="15%";>
                   <DIV align=right>���ۺ�ͬ����Լ��ǩ��ʱ��&nbsp;</DIV>
                </TD>
                <TD colspan="4" class=2-td2-center width="25%">
                  <DIV align=left><%=DataConvert.TimeStamp2String(gydata.getQdsj())%> </DIV></TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left width="15%";>
                   <DIV align=right> �������&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left width="25%">
                   <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getJzmj())%> �O </DIV></TD>
                <TD class=2-td2-left width="17%">
                  <DIV align=right>�ɽ��۸�&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="32%">
                  <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getCjjg())%>Ԫ(�����)</DIV></TD>
                  </TR>
                 <TR>
                <TD class=2-td2-left width="17%">
                  <DIV align=right>���εֿ۶�&nbsp;</DIV></TD>
                <TD class=2-td2-left width="32%">
                  <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getBcdke())%>Ԫ(�����)</DIV></TD>
                   <TD class=2-td2-left width="17%">
                  <DIV align=right>ʣ���&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="32%">
                  <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getSye())%>Ԫ(�����)</DIV></TD>
                  </TR>

       </logic:iterate>
                  </TBODY></TABLE><BR>

            <DIV align=center>
            <!--input type=button name="btnInputFwjbxx" value="¼�뷿�ݻ�����Ϣ" onclick="doSubmitForm('Fwjbxx')">
            <input type=button name="btnInputCqxx" value="¼���Ǩ���" onclick = "doSubmitForm('Cqxx');">
            <input type=button name="btnInputGyzf" value="¼���ѹ�����ס���������" onclick = "doSubmitForm('Gyzf');">
            <input type=button name="btnInputJmsb" value="¼������걨��" onclick = "doSubmitForm(Jmsb);"><br><br-->

            <IMG alt=���� height=22 id=baocun name="btnSave"
            onclick="doSubmitForm('Save');" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
            src="<%=static_file%>images/baocun1.jpg" style="CURSOR: hand" width=79>

            <IMG alt=�˳� height=22 id=tuichu name=tuichu
            onclick="doSubmitForm('ReturnView')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
            src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand" width=79>
            </DIV><BR>


<script language="javascript" type='text/JavaScript'>

</script>
              <%if (sbxxBo.isPerson())
              {
              %>
<%@ include file="../include/BottomGr.jsp" %>
		      <%
			    }
          else
          {
          %>

<%@ include file="../include/Bottom.jsp" %>
		    <%
			}
			%>

 </html:form>
</BODY></HTML>
