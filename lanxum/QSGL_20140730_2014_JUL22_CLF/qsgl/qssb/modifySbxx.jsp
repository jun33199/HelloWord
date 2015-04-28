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

<HTML><HEAD><TITLE>申报信息修改表</TITLE>
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
			   		if (!FN_QSCheckNumberDigit(document.forms[0].jmsje.value,2,"减免税金额"))
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
		  	   alert("联系人姓名不能为空，请重新输入！");
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
				   alert("申报日期不能为空或格式不正确，请重新输入！");
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
    //对列表式数据进行检测

	isCheck = DataSourceCheck(daList);
    if(isCheck == false){
        return false;
    }
    //把textArea中的字窜放到hidden域中
    document.forms[0].dataSource_gm.value=daList.formatData(constRowSeparator,constColSeparator);
    //alert(document.forms[0].dataSource_gm.value);
    return true;
}


</SCRIPT>

<SCRIPT LANGUAGE=javascript>
              <%if (sbxxBo.isPerson())
              {
              %>

//产权人数组
<%=sbxxForm.getCqrJs()%>

    var arySelect_zjlx =<%=ActionUtil.displaySelectDataSource(sbxxForm.getSfzjlxList(),null,"zjlxdm","zjlxmc")%>; // 证件
    var arySelect_gj = <%=ActionUtil.displaySelectDataSource(sbxxForm.getGjdqList(),null,"gjdqdm","gjdqmc")%>;//国籍

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
		   alert ("产权人不能为空！");
		   return;
		}

		daList.moveto(numindex);
        var dataSource_del=daList.getData();
        var numRow_del=dataSource_del.length;
        var flagConfirm=confirm("确认删除当前纪录！");
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
        //纳税人名称* 联系电话* 身份证件类型* 身份证件号码* 国籍* 是否主产权人*
        var aryColumn = new Array();
        aryColumn[aryColumn.length] = new DTColumn(constCTSequence,"xh",2,null);
        aryColumn[aryColumn.length] = new DTColumn(constCTInput,"nsrmc",200,null,"","纳税人名称",false,true,"200");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput,"lxdh",100,null,"","联系电话",false,false,"20");
        aryColumn[aryColumn.length] = new DTColumn(constCTSelect,"sfzjlxdm",100,arySelect_zjlx,"need_autoselect","身份证件类型",false,true,"2");
         aryColumn[aryColumn.length] = new DTColumn(constCTInput,"sfzjhm",150,null,"","身份证件号码",false,true,"30");
        aryColumn[aryColumn.length] = new DTColumn(constCTSelect,"gjdm",100,arySelect_gj,"need_autoselect","国籍",false,true,"3");
        aryColumn[aryColumn.length] = new DTColumn(constCTRadioButton,"zcqr",50,null,"maxlength=1","主产权人",false,false,"1");
        aryColumn[aryColumn.length] = new DTColumn(constCTDelButton,"delIndex",10,null,"","删除",false,false,"20");

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
              alert("身份证号码格式不对！");              
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
            alert("请选择主产权人！");  
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
 <jsp:param name="subtitle" value="契税申报>申报信息修改表"/>
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
          <TD class=1-td1>北京市地方税务局契税申报表</TD></TR>
          <%
          }
          else
          {
          %>
          <TD class=1-td1>北京市地方税务局不征契税采集表</TD></TR>
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
        				<TD class=2-td2-t-left width="12%">申报日期</TD>
				<TD class=2-td2-t-center width="43%" colspan=3><DIV align=left><html:text name="sbxxForm" property="sbrq" />  </DIV></TD>
    </TR>
    			  <TR>
          <%if (!sbxxBo.isBZ())
          {
          %>

			  	<TD class=2-td2-left width="15%">批次号</TD>
							  	<html:hidden name="sbxxForm" property="voSbzb.drpch"/>
			  	<TD class=2-td2-left width="35%"><DIV align=left>&nbsp<bean:write name="sbxxForm" property="voSbzb.drpch" /> </DIV></TD>

			  	<TD class=2-td2-left width="15%">纳税申报表号</TD>
							  	<html:hidden name="sbxxForm" property="voSbzb.sbbh"/>
			  	<TD class=2-td2-center width="35%"><DIV align=left>&nbsp<bean:write name="sbxxForm" property="voSbzb.sbbh" /> </DIV></TD>
          <%
          }
          else
          {
          %>
			  	<TD class=2-td2-left width="15%">不征契税信息采集表号</TD>
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

			  	<TD class=2-td2-t-left width="15%">批次号</TD>
							  	<html:hidden name="sbxxForm" property="voSbzb.drpch"/>
			  	<TD class=2-td2-t-left width="35%"><DIV align=left>&nbsp<bean:write name="sbxxForm" property="voSbzb.drpch" /> </DIV></TD>

			  	<TD class=2-td2-t-left width="15%">纳税申报表号</TD>
							  	<html:hidden name="sbxxForm" property="voSbzb.sbbh"/>
			  	<TD class=2-td2-t-center width="35%"><DIV align=left>&nbsp<bean:write name="sbxxForm" property="voSbzb.sbbh" /> </DIV></TD>
          <%
          }
          else
          {
          %>
			  	<TD class=2-td2-t-left width="15%">不征契税信息采集表号</TD>
							  	<html:hidden name="sbxxForm" property="voSbzb.sbbh"/>
			  	<TD class=2-td2-t-center width="35%" colspan=3><DIV align=left>&nbsp<bean:write name="sbxxForm" property="voSbzb.sbbh" /> </DIV></TD>
          <%
          }
          %>
			  </TR>
</logic:equal>

			  <TR>
			  	<TD class=2-td2-left width="12%">缴款方式</TD>
			  	<TD class=2-td2-left width="43%"> <DIV align=left>&nbsp;
					<logic:notEqual name="sbxxBo" property="BZ" value="true">
                                  <bean:define id="data" name="sbxxForm" property="jkfsList"  />
                                  <html:select property="voSbzb.jsfsdm" >
                                    <html:options collection="data" labelProperty="jsfsmc" property="jsfsdm" />
                                  </html:select>
                                  <html:hidden property="voSbzb.jsfsmc"/>
                   </logic:notEqual>
				<TD class=2-td2-left width="22%">房屋土地管理部门受理号</TD>
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
    <TD class=2-td2-left colspan="4" width="70%"  ><DIV align=left>&nbsp;&nbsp;个人填写部分</DIV></TD>
    <TD class=2-td2-center  width="30%" colspan="2" >  <DIV align=right> 
    <img onMouseOver="MM_swapImage('add','','<%=static_file%>/images/xinzeng2.jpg',1)" onMouseOut="MM_swapImgRestore()"  value="新增" id="Submit522" src="<%=static_file%>/images/xinzeng1.jpg" width="79" onClick="addRow()" height="22">
&nbsp; </DIV> &nbsp;&nbsp;</TD>       
</TR>

         <TR>
    <TD  colspan="6" valign="top" width="100%">

<table id="TABLE_LIST" border="0" cellpadding="0" cellspacing="0" class="table-99">
  <tr>
    <td width="2%" class="2-td1-left" >序号</td>
	<td width="25%" class="2-td1-left">
	<%if (sbxxBo.isBZ()) {%> 申请人名称
     <% } else {%>纳税人名称<%}%>
<span class="bitian">*</span></td>
    <td width="13%" class="2-td1-left">联系电话</td>
    <td width="13%" class="2-td1-left">身份证件类型<span class="bitian">*</span></td>
    <td width="20%" class="2-td1-left">身份证件号码<span class="bitian">*</span></td>
    <td width="5%" class="2-td1-left">国籍<span class="bitian">*</span></td>
    <td width="7%" class="2-td1-left">是否主产权人<span class="bitian">*</span></td>
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
                  <DIV align=right>非个人填</DIV>
                   <DIV align=right>写部分</DIV>
                  <TD class=2-td2-t-left width="15%">
                   <DIV align=right>纳税计算机代码&nbsp; </DIV>
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
                  <DIV align=right>申请人类型&nbsp; </DIV></TD>
          <%
          }
            else
          {
          %>

                <TD class=2-td2-t-left width="19%">
                  <DIV align=right>纳税人类型&nbsp; </DIV></TD>
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
                  <DIV align=right>申请人名称&nbsp;</DIV></TD>
          <%
          }
           else
          {
          %>
                <TD  class=2-td2-left width="19%">
                  <DIV align=right>纳税人名称&nbsp;</DIV></TD>
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
                   <DIV align=right>开户银行&nbsp; </DIV>
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
                   <DIV align=right>联系人姓名&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left width="33%">
                   <DIV align=left>
                       &nbsp;<html:text name = "sbxxForm" property = "voFgrxx.lxrxm" maxlength="50"/>
                   </DIV>
                </TD>
                <TD class=2-td2-left width="19%">
                  <DIV align=right>联系电话&nbsp;</DIV></TD>
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
                  <DIV align=right>税务机关</DIV>
                  <DIV align=right>审核减免税</DIV>
                  <TD class=2-td2-left width="15%">
                   <DIV align=right>减免税文书字号&nbsp; </DIV>
                </TD>
                  <TD colspan="4" class=2-td2-center width="30%">
                    <DIV align=left><html:text name="sbxxForm" property="voSpjgxx.hdtzszh" size="40" maxlength="40"/>
                                    <IMG name="query"
			          onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
			          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/chaxun1.jpg"
			          onclick = "doSubmitForm('GetJmsje');"
			          width="79" height="22" id="chaxun1" alt="查询" style="cursor:hand"></DIV>
                  </TD>
                </TD>
              </TR>
               <TR>
                 <TD class=2-td2-left width="15%";>
                   <DIV align=right>减免税金额&nbsp; </DIV>
                </TD>
              <TD colspan="4" class=2-td2-center width="25%">
                <DIV align=left><html:text name="sbxxForm" property="jmsje"  maxlength="15"/>
                </DIV>
				</TD>
                  </TR>
                 <TR>
                 <TD class=2-td2-left width="15%";>
                   <DIV align=right>备注&nbsp; </DIV>
                </TD>
                <TD colspan="5" class=2-td2-center width="84">
                  <DIV align=left><html:textarea name="sbxxForm" property="voSbzb.bz"/></DIV></TD>
                </TR>


	<!-- 房屋基本信息-->
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
				    
				      <DIV align=right>	土地房屋</DIV>
                      <DIV align=right>权属转移</DIV>
                  </TD>
                  <TD class=2-td2-left width="15%">
                      <DIV align=right>房地产项目名称&nbsp;
                      </DIV>
                  </TD>
                      <TD colspan="3" class=2-td2-left width="15%" style="word-break:break-all">
                          <DIV align=left><bean:write name="fwtdVo" property="fdcxmmc" />
                          </DIV>
                      </TD>
                      <td width="13%" class=2-td2-center>
                          <DIV align=left>
                              <logic:equal name="fwtdVo" property="sfesf" value="00">非二手房</logic:equal>
                              <logic:equal name="fwtdVo" property="sfesf" value="01">二手房</logic:equal>
                          </div>
                      </td>
                  </TR>
                  <TR>
                      <TD class=2-td2-left width="15%";>
                          <DIV align=right>合约签订时间&nbsp; </DIV>
                      </TD>
                      <TD class=2-td2-left width="19%">
                          <DIV align=left><%=DataConvert.TimeStamp2String(fwtdVo.getHtqdsj())%>&nbsp;
                          </DIV>
                      </TD>
                      <%if (!sbxxBo.isBZ())
                      {
                          %>
                          <TD class=2-td2-left width="19%">
                              <DIV align=right>购房原因&nbsp;</DIV></TD>
                              <%
                    }
          else
          {
          %>
			            <TD class=2-td2-left width="19%">
			              <DIV align=right>分类&nbsp;</DIV></TD>
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
		              <DIV align=right>土地、房屋权属转移类型&nbsp; </DIV> </TD>
		              <TD class=2-td2-left width="19%">
		                <DIV align=left>
		                  <bean:write  name="fwtdVo" property="tdfwqszymc"/></DIV></TD>
		                  <TD class=2-td2-left width="19%">
		                    <DIV align=right>房屋类别&nbsp;</DIV></TD>
		                    <TD colspan="2"  class=2-td2-center width="33%">
		                      <DIV align=left>
		                        <bean:write  name="fwtdVo" property="fwlxmc"/>&nbsp;</DIV></TD>
		          </TR>
		          <TR>
		            <TD  class=2-td2-left width="25%";>
		              <DIV align=right>土地、房屋座落地址&nbsp; </DIV>
		            </TD>
		            <TD colspan="4"  class=2-td2-center width="15%" style="word-break:break-all">
		              <DIV align=left><bean:write name="fwtdVo" property="tdfwzldz" /></DIV></TD>
		            </TR>
                    <TR>
                      <TD class=2-td2-left width="25%";>
                        <DIV align=right>土地、房屋权属转移面积&nbsp; </DIV>
                      </TD>
                      <TD  class=2-td2-left width="15%">
                        <DIV align=left>土地：
                          <%=DataConvert.BigDecimal2String(fwtdVo.getTdfwqszymj())%>
                          ㎡ </DIV></TD>
                        <TD class=2-td2-left width="17%">
                          <DIV align=right>房屋建筑面积&nbsp;</DIV></TD>
                          <TD colspan="2"  class=2-td2-center width="32%">
                            <DIV align=left>
                              <%=DataConvert.BigDecimal2String(fwtdVo.getFwjzmj())%>
                              ㎡</DIV></TD>
                    </TR>
                    
                    <%if (!sbxxBo.isBZ())
			          {
			          %>
	                    <TR>
		                    <TD class=2-td2-left width="8%" rowspan = "2">
		                      <DIV align=right>成交价格（评估价格）</DIV>
		                      <TD  class=2-td2-left width="5%">
		                        <DIV align=left>
	                                  <%=DataConvert.BigDecimal2String(fwtdVo.getCjjgrmb())%>
	                                  元(人民币) </DIV></TD>
		                        <TD class=2-td2-left width="17%">
		                          <DIV align=right>税务机关核定价格&nbsp; </DIV></TD>
		                          <TD  colspan="2"  class=2-td2-center width="32%">
		                            <DIV align=left>
	                                      <%=DataConvert.BigDecimal2String(fwtdVo.getPgjgrmb())%>
	                                      元(人民币) </DIV></TD>
	                      </TR>
	                      <TR>
	                        <TD class=2-td2-left width="15%";>
	                          <DIV align=left>
	                            <%=DataConvert.BigDecimal2String(fwtdVo.getCjjgwb())%>
	                            元(外币) </DIV></TD>
	                          <TD class=2-td2-left width="25%">
	                            <DIV align=left>
	                              <bean:write name="fwtdVo" property="bzmc" />
	                            </DIV>
	                            <DIV align=left>汇率：&nbsp;<%=DataConvert.BigDecimal2String(fwtdVo.getHldm(),5)%> </DIV></TD>
	                            <TD  colspan="2" class=2-td2-center  width="17%">
	                              <DIV align=left>
	                                <%=DataConvert.BigDecimal2String(fwtdVo.getZhjgrmb())%>
	                                折合元(人民币) </DIV></TD>
	
	                       </tr>
					<%
					    }
					    %>

      <logic:iterate id="cqdata" indexId="index" length="length" name="sbxxForm" property="cqList"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq">

                <TR>
                  <TD class=2-td2-left width="8%" rowspan = "4">
                  <DIV align=right>	拆迁</DIV>
                   <DIV align=right>情况</DIV></TD>
                  <TD class=2-td2-center width="25%">
                   <DIV align=right>被拆迁房屋坐落地址&nbsp; </DIV>
                </TD>
                <TD colspan="4" class=2-td2-center width="15%" style="word-break:break-all">
                  <DIV align=left>
                      &nbsp;<bean:write name = "cqdata" property = "zldz"/>
                  </DIV>
                </TD>
                </TR>
                <TR>
                 <TD class=2-td2-left width="15%";>
                   <DIV align=right>拆迁协议号码&nbsp; </DIV>
                </TD>
                <TD colspan="4" class=2-td2-center width="25%">
                <DIV align=left>
                    &nbsp;<bean:write name = "cqdata" property = "cqxyh"/>
                </DIV>
                </TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left width="15%";>
                   <DIV align=right> 拆迁补偿额&nbsp; </DIV>
                </TD>
                <TD  class=2-td2-left width="25%" colspan=4>
                  <DIV align=left>
                     &nbsp;
                     <%=DataConvert.BigDecimal2String(cqdata.getCqbce())%>
                     元(人民币)
                  </DIV>
                </TD>
               </TR>
               <TR>
                 <TD class=2-td2-left width="15%";>
                   <DIV align=right>本次使用补偿额&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left width="25%">
                   <DIV align=left>
                      &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getBcsybce())%>元(人民币)
                   </DIV>
                </TD>
                <TD class=2-td2-left width="17%">
                  <DIV align=right>拆迁补偿剩余额&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="32%">
                  <DIV align=left>
                      &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getCqbcsye())%> 元(人民币)
                  </DIV>
                  </TD>
                  </TR>
       </logic:iterate>

      <logic:iterate id="gydata" indexId="index" length="length" name="sbxxForm" property="gyzfList"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf">

               <TR>
                   <TD class=2-td2-left width="8%" rowspan = "6">
                  <DIV align=right>	已购公有住房</DIV>
                  <DIV align=right>/经济适用住房</DIV>
                   <DIV align=right>上市出售情况</DIV>
				   <br>
				   </TD>
                  <TD   class=2-td2-left width="25%">
                   <DIV  align=right>出售合同号码&nbsp;</DIV>
                </TD>
                <TD colspan="4" class=2-td2-center width="15%">
                  <DIV align=left><bean:write name="gydata" property="yggyzfqszsh" /></DIV></TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left width="15%"; style="word-break:break-all">
                   <DIV align=right>房屋权属证书号&nbsp; </DIV>
                </TD>
                 <TD colspan="4" class=2-td2-center width="25%">
                  <DIV align=left><bean:write name="gydata" property="fwqszsh" />&nbsp;</DIV></TD>
                  </TR>
                <TR>
                 <TD class=2-td2-left width="15%"; style="word-break:break-all">
                   <DIV align=right>座落地址&nbsp; </DIV>
                </TD>
                 <TD colspan="4" class=2-td2-center width="25%">
                  <DIV align=left><bean:write name="gydata" property="zldz" />&nbsp;</DIV></TD>
                  </TR>
                  <TR>
                 <TD  class=2-td2-left width="15%";>
                   <DIV align=right>出售合同（契约）签订时间&nbsp;</DIV>
                </TD>
                <TD colspan="4" class=2-td2-center width="25%">
                  <DIV align=left><%=DataConvert.TimeStamp2String(gydata.getQdsj())%> </DIV></TD>
                  </TR>
                  <TR>
                 <TD class=2-td2-left width="15%";>
                   <DIV align=right> 建筑面积&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left width="25%">
                   <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getJzmj())%> ㎡ </DIV></TD>
                <TD class=2-td2-left width="17%">
                  <DIV align=right>成交价格&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="32%">
                  <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getCjjg())%>元(人民币)</DIV></TD>
                  </TR>
                 <TR>
                <TD class=2-td2-left width="17%">
                  <DIV align=right>本次抵扣额&nbsp;</DIV></TD>
                <TD class=2-td2-left width="32%">
                  <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getBcdke())%>元(人民币)</DIV></TD>
                   <TD class=2-td2-left width="17%">
                  <DIV align=right>剩余额&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="32%">
                  <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getSye())%>元(人民币)</DIV></TD>
                  </TR>

       </logic:iterate>
                  </TBODY></TABLE><BR>

            <DIV align=center>
            <!--input type=button name="btnInputFwjbxx" value="录入房屋基础信息" onclick="doSubmitForm('Fwjbxx')">
            <input type=button name="btnInputCqxx" value="录入拆迁情况" onclick = "doSubmitForm('Cqxx');">
            <input type=button name="btnInputGyzf" value="录入已购公有住房上市情况" onclick = "doSubmitForm('Gyzf');">
            <input type=button name="btnInputJmsb" value="录入减免申报表" onclick = "doSubmitForm(Jmsb);"><br><br-->

            <IMG alt=保存 height=22 id=baocun name="btnSave"
            onclick="doSubmitForm('Save');" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
            src="<%=static_file%>images/baocun1.jpg" style="CURSOR: hand" width=79>

            <IMG alt=退出 height=22 id=tuichu name=tuichu
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
