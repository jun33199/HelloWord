<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.ActionUtil"%>
<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.FwjhxxForm"%>
<%
	FwjhxxForm aForm = (FwjhxxForm)session.getAttribute("fwjhxxForm");

%>

<HTML><HEAD><TITLE>录入房屋交换信息表</TITLE>
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

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType)
{
    document.all.operationType.value=operationType;
    obj = document.forms[0].jhperson;
    var per;
    for (i=0;i<(obj.item.length);i++)
    {
          if (obj.item(i).checked)
          {
                  per=obj.item(i).value;
                  break
          }
    }

    if(operationType=="Save")
    {

		if(!check_Mj()){
			if (!confirm("房屋面积大于1万平方米，是否确认?"))
			{
			   return false;
			}
		}
		if(!check_Jg()){
			if (!confirm("房屋总价小于5万元，是否确认?"))
			{
			   return false;
			}
		}



		    document.forms[0].operationType.value='Save';

       //个人填写信息校验
        if(per == "0")
        {
       var checknsr=checkNsr();
       if (checknsr==false){
              return false;
       }
        }

            //非个人填写信息校验
if(per == "1")
{
    if (document.forms[0].jsjdm.value == "")
          {
          alert("计算机代码不能为空，请重新输入！");
          document.forms[0].jsjdm.focus();
          return false;
          }
    if (document.forms[0].fgrnsrmc.value == "")
    {
          alert("纳税人姓名不能为空，请获取登记信息！");
          document.all.imgGetNsrxx.focus();
          return false;
    }

    if (document.forms[0].lxrxm.value == "")
    {
          alert("联系人姓名不能为空，请重新输入！");
          document.forms[0].lxrxm.focus();
          return false;
    }
}


    //土房信息校验
    if(document.forms[0].fdcxmmc.value == "")
    {
    alert("房地产项目名称不能为空，请重新输入！");
    document.forms[0].fdcxmmc.focus();
    return false;
    }
                    if(document.forms[0].hyqdsj.value == "" || !checkDate(document.forms[0].hyqdsj.value))
    {
	    alert("合约签订时间不能为空或时间格式输入不正确，请重新输入！");
	    document.forms[0].hyqdsj.focus();
	    return false;
    }

        if (!cmpDate(document.forms[0].hyqdsj.value,document.forms[0].xtdqsj.value))
  	{
  	   alert("合约签订时间不能大于当前时间，请重新输入！");
  	   document.forms[0].hyqdsj.focus();
  	   return false;
  	}

    if(document.forms[0].tdfwzldz.value == "")
    {
	    alert("土地、房屋座落地址不能为空，请重新输入！");
	    document.forms[0].tdfwzldz.focus();
	    return false;
    }




    //房屋权属转移类型=土地转让或者出让,只能填写土地面积；否则可以不填或者只填一项

		if (!FN_QSCheckNumberDigit(document.forms[0].fwjzmj.value,2,"房屋建筑面积"))
		    {

		      document.forms[0].fwjzmj.focus();
		      return false;
		    }
		  if (document.forms[0].tdfwqszymj.value !="")
	    {
		    if (!FN_QSCheckNumberDigit(document.forms[0].tdfwqszymj.value,2,"土地面积！"))
		    {
		      document.forms[0].tdfwqszymj.focus();
		      return false;
		    }
	    }






    //如果金额不为空必须为数字
    if(document.forms[0].cjjgyrmb.value != "")
    {
	    if (!FN_QSCheckNumberDigit(document.forms[0].cjjgyrmb.value,2,"人民币成交价格"))
	    {
	      document.forms[0].cjjgyrmb.focus();
	      return false;
	    }
    }

    if(document.forms[0].pgjg.value != "")
    {
	    if (!FN_QSCheckNumberDigit(document.forms[0].pgjg.value,2,"人民币税务机关核定价格"))
	    {
	      document.forms[0].pgjg.focus();
	      return false;
	    }
    }

    if(document.forms[0].cjjgywb.value != "")
    {
	    if (!FN_QSCheckNumberDigit(document.forms[0].cjjgywb.value,2,"外币成交价格"))
	    {
	      document.forms[0].cjjgywb.focus();
	      return false;
	    }
    }

    if(document.forms[0].zhyrmb.value != "")
    {
	    if (!FN_QSCheckNumberDigit(document.forms[0].zhyrmb.value,2,"折合人民币价格"))
	    {
	      document.forms[0].zhyrmb.focus();
	      return false;
	    }
    }

    if(document.forms[0].hn.value != "")
    {
	    if (!FN_QSCheckNumberDigit(document.forms[0].hn.value,5,"汇率"))
	    {
	      document.forms[0].hn.focus();
	      return false;
	    }
    }

    //如果成交人民币价格为空，税务机关核定价格和外币价格不能都为空
    if(document.forms[0].cjjgyrmb.value == ""){
      if(document.forms[0].pgjg.value == ""){
        if(document.forms[0].cjjgywb.value == "")
        {
          alert("成交人民币价格、税务机关核定价格和外币价格不能都为空！");
          document.forms[0].cjjgyrmb.focus();
          return false;
        }
        else if(document.forms[0].cjjgywb.value != "")
        {

          if(document.forms[0].bzdm.value == "")
          {
            alert("币种不能为空，请输入！");
            document.forms[0].bzdm.focus();
            return false;

          }
          if(document.forms[0].hn.value == "")
          {
            alert("汇率不能为空，请输入！");
            document.forms[0].hn.focus();
            return false;

          }
          if(document.forms[0].zhyrmb.value == "")
          {
            alert("折合人民币不能为空，请输入！");
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
        alert("币种不能为空，请输入！");
        document.forms[0].bzdm.focus();
        return false;

      }
      if(document.forms[0].hn.value == "")
      {
        alert("汇率不能为空，请输入！");
        document.forms[0].hn.focus();
        return false;

      }
      if(document.forms[0].zhyrmb.value == "")
      {
        alert("折合人民币不能为空，请输入！");
        document.forms[0].zhyrmb.focus();
        return false;
      }
    }





        //document.forms[0].jkfsmc.value = document.forms[0].jkfsdm.options[document.forms[0].jkfsdm.selectedIndex].text;

    if (document.forms[0].jhperson[1].checked)
    {
    	document.forms[0].fldm.value="73";
    	document.forms[0].flmc.value = "房屋交换中房屋价格较大";
    }
    else
    {
    	document.forms[0].fldm.value="53";
    	document.forms[0].flmc.value = "房屋交换中房屋价格较大";
    }

          document.forms[0].tdfwqszymc.value = "房屋交换";
          document.forms[0].tdfwqszylx.value = "05";


          document.forms[0].bz.value = document.forms[0].bzdm.options[document.forms[0].bzdm.selectedIndex].text;
          //非个人信息信息负值
          if(per == "1")
          {
              if (document.forms[0].khyhdm.value != "")
              {
                document.forms[0].khyhmc.value = document.forms[0].khyhdm.options[document.forms[0].khyhdm.selectedIndex].text;
              }
              document.forms[0].nsrlxmc.value = document.forms[0].nsrlx.options[document.forms[0].nsrlx.selectedIndex].text;
          }





   }
    else if(operationType=="quit")
    {
            document.forms[0].operationType.value='Return';
    }
    document.forms[0].submit();
}


function checkSelect()
{
    if(document.forms[0].tdfwqszylx.value=="03")
    {

       document.forms[0].fwlxdm.disabled=false ;
    }
    else
    {
        document.forms[0].fwlxdm.disabled=true ;

    }

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
    return true;
	//转到下一个页面
}


//改变个人和非个人的情况
function checkSelectGr()
{

    document.all.operationType.value="ChangePerson";
    document.forms[0].submit();

}

</SCRIPT>

<SCRIPT LANGUAGE=javascript>
              <%if (aForm.getJhperson()!=null && aForm.getJhperson().equals("0"))
              {
              %>

//产权人数组
<%=aForm.getCqrJs()%>

    var arySelect_zjlx =<%=ActionUtil.displaySelectDataSource(aForm.getSfzjlxList(),null,"zjlxdm","zjlxmc")%>; // 证件
    var arySelect_gj = <%=ActionUtil.displaySelectDataSource(aForm.getGjdqList(),null,"gjdqdm","gjdqmc")%>;//国籍

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
              <%if (aForm.getJhperson()!=null && aForm.getJhperson().equals("0"))
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
              '<%=static_file%>imagess/shanchu2.jpg');initPage()"
              topMargin=0 marginheight="0" marginwidth="0">


<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="契税申报>录入房屋交换信息表"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

										</TD>
                                      </TR>
  </TBODY>
</TABLE>

<br>
 <TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width="98%">
  <TBODY>
  <TR>
    <TD vAlign=top>

         <TABLE align=center cellSpacing=0 class=table-99 width="100%">
        <TBODY>
        <TR>
          <TD class=1-td1>录入房屋交换信息</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top  width="100%">
            <html:form action="/qssb/addFwjhxx.do">
              <html:hidden property="operationType" value=""/>
              <html:hidden property="xtdqsj"/>
<input type="hidden" name="dataSource_gm" value="">
<TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
   <TBODY>
      <BR>
        <TR>
          <TD class=2-td2-t-left width="12%">纳税申报表号</TD>
          <html:hidden name="fwjhxxForm" property="sbbh"/>
          <TD class=2-td2-t-center width="43%" colspan=3><DIV align=left>&nbsp<bean:write name="fwjhxxForm" property="sbbh" /> </DIV></TD>


          </TR>
          <!--TR>
            <TD class=2-td2-left width="12%">缴款方式</TD>
            <TD class=2-td2-left width="34%"> <DIV align=left>
              <bean:define id="data" name="fwjhxxForm" property="jkfsList"  />
              <html:select property="jkfsdm">
                <html:options collection="data" labelProperty="jsfsmc" property="jsfsdm" />
              </html:select>
              <html:hidden property="jkfsmc"/></td>
              <TD class=2-td2-left width="22%">房屋土地管理部门受理号</TD>
              <TD class=2-td2-center width="23%"><DIV align=left><html:text name="fwjhxxForm" property="fcjslh" maxlength="30"/></DIV></TD>
            </TR-->


          <TR>
            <TD class=2-td2-left width="12%">交换对象</TD>
            <TD class=2-td2-center width="43%" colspan=3><DIV align=left>
            <html:radio property="jhperson" value="0" onclick="checkSelectGr()"/>个人
            <html:radio property="jhperson" value="1" onclick="checkSelectGr()"/>非个人
             </DIV></TD>


          </TR>

   </TBODY>
</TABLE>

<logic:equal name="fwjhxxForm" property="jhperson" scope="session" value="0">
<!--个人信息-->

        <TABLE border=0 cellSpacing=0 class=tabled-99 width="98%">
         <TBODY>    <TR>
    <TD class=2-td2-left colspan="3" width="50%" ><DIV align=left>&nbsp;&nbsp;个人填写部分</DIV></TD>
    <TD class=2-td2-center width="50%" colspan="3" >  <DIV align=right>
    <img onMouseOver="MM_swapImage('add','','<%=static_file%>/images/xinzeng2.jpg',1)" onMouseOut="MM_swapImgRestore()"  value="新增" id="Submit522"  name="addnsr" src="<%=static_file%>/images/xinzeng1.jpg" width="79" onClick="addRow()" height="22">
&nbsp;&nbsp;
</DIV> </TD>

</TR>
         <TR>
    <TD colspan="6" valign="top">

<table id="TABLE_LIST" border="0" cellpadding="0" cellspacing="0" class="table-99">
  <tr>
    <td width="2%" class="2-td1-left" >序号</td>
	<td width="25%" class="2-td1-left">纳税人名称<span class="bitian">*</span></td>
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
</TBODY>
</table>

</logic:equal>

<!--非个人信息-->

<logic:equal name="fwjhxxForm" property="jhperson" scope="session" value="1">
  <table border=0 cellSpacing=0 class=tabled-99 width="99%" id="fgrtable">
    <TBODY>
    <TR>
      <td class=2-td2-t-left width="15%" rowspan = "4">
        <DIV align=right>非个人填</DIV>
         <DIV align=right>写部分</DIV>
        <td class=2-td2-t-left width="15%">
         <DIV align=right>计算机代码&nbsp;
         </DIV>
      </td>
      <DIV align=right>&nbsp; </DIV>
        <td class=2-td2-t-left width="35%">
          <DIV align=left><html:text property="jsjdm" maxlength="8" />
          <FONT color=red>*</FONT>
          <span id="getDJimage1" style="visibility:visible">

          <input type="image"  name="imgGetNsrxx" value="获取登记信息" alt="获取登记信息"
           onClick="javascript:return doSubmitForm('GetNsrxx');"
           onMouseOver="MM_swapImage('b-hqdjxx1','','<%=static_file%>images/b-hqdjxx2.jpg',1)"
           onMouseOut="MM_swapImgRestore()"src="<%=static_file%>images/b-hqdjxx1.jpg"
           width="79" height="22" id="b-hqdjxx1"></span></DIV></td>


      <td class=2-td2-t-left width="10%">
        <DIV align=right>纳税人类型&nbsp; </DIV></td>
      <td colspan="2"  class=2-td2-t-center width="20%">
        <DIV align=left>
          <bean:define id="data1" name="fwjhxxForm" property="nsrlxList"  />
          <html:select property="nsrlx" >
            <html:options collection="data1" labelProperty="nsrlxmc" property="nsrlxdm" />
          </html:select></DIV></td>
          <html:hidden property="nsrlxmc" />
        </TR>


   <TR>
      <td  class=2-td2-left >
        <DIV align=right>纳税人名称&nbsp;</DIV></td>
      <td colspan="4"  class=2-td2-center width="33%">
         <DIV align=left>
         <bean:write name="fwjhxxForm" property="fgrnsrmc" />
         <html:hidden property="fgrnsrmc" /> </DIV></td>
   </TR>



   <TR>
       <td class=2-td2-left ;>
         <DIV align=right>开户银行&nbsp; </DIV>
      </td>
      <td class=2-td2-center colspan="4" >
        <DIV align=left>
          <bean:define id="yhList" name="fwjhxxForm" property="khyhList"  />
          <html:select property="khyhdm" >
            <html:options collection="yhList" labelProperty="khyhmc" property="yhdm" />
          </html:select></DIV></td>
          <html:hidden property="khyhmc" />

                              <html:hidden property="yhzh" />
    </TR>






    <TR>
       <td class=2-td2-left >
         <DIV align=right>联系人姓名&nbsp; </DIV>
      </td>

       <td class=2-td2-left >
         <DIV align=left><html:text property="lxrxm" maxlength="50" /><FONT color=red>*</FONT> </DIV></td>
      <td   class=2-td2-left width="19%">
        <DIV align=right>联系电话&nbsp;</DIV></td>
      <td colspan="2"  class=2-td2-center width="33%">
         <DIV align=left><html:text property="fgrlxdh" maxlength="20" /><FONT ></FONT> </DIV></td>
   </TR>
     <TR>
     </TBODY>
</table>

</logic:equal>


      <br>

	<!--土地房屋信息-->

  <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
    <TBODY>
    <TR>
      <TD class=2-td2-t-left width="8%" rowspan = "8">
        <DIV align=right>	土地房屋</DIV>
        <DIV align=right>权属转移</DIV></TD>
        <TD class=2-td2-t-left width="15%">
          <DIV align=right>房地产项目名称&nbsp;</DIV></TD>
          <TD colspan="4" class=2-td2-t-center width="15%">
            <DIV align=left><html:text property="fdcxmmc" name="fwjhxxForm" size="60" maxlength="100"/><FONT color=red>*</FONT> </DIV></TD>
          </TR>
      <TR>
        <TD class=2-td2-left width="15%";>
          <DIV align=right>合约签订时间(yyyymmdd)&nbsp; </DIV>
        </TD>
        <TD class=2-td2-left width="19%">
          <DIV align=left><html:text property="hyqdsj" name="fwjhxxForm" /><FONT color=red>*</FONT></DIV></TD>
          <TD class=2-td2-left width="19%">
            <DIV align=right>分类&nbsp;</DIV></TD>
            <TD colspan="2"  class=2-td2-center width="33%">
              <DIV align=left>
                <!--bean:define id="data1" name="fwjhxxForm" property="flList"/-->
                <!--html:select property="fldm"-->
                  <!--html:options collection="data1" labelProperty="qstdfwytmc" property="qstdfwytdm"/-->
                  <!--/html:select-->
              <select name="a" disabled="ture"><option value="02">房屋交换中房屋价格较大</option></select>
										<html:hidden property="fldm"/>
                    <html:hidden property="flmc"/>
                </DIV></TD>
            </TR>

         <html:hidden property="tdfwqszylx"/>
         <html:hidden property="tdfwqszymc"/>
        <!--TR>
          <TD class=2-td2-left width="15%";>
            <DIV align=right>土地、房屋权属转移类型&nbsp; </DIV> </TD>
            <TD class=2-td2-left width="19%">
              <DIV align=left>

                <bean:define id="data1" name="fwjhxxForm" property="tdfwqszylxList"/>
                <html:select property="tdfwqszylx" value="02" onclick="checkSelect()" >
                  <html:options collection="data1" labelProperty="qszyxsmc" property="qszyxsdm"/>
                </html:select>
                <html:hidden property="tdfwqszymc" />
              </DIV></TD>
              <TD class=2-td2-left width="19%">
                <DIV align=right>房屋类别&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="33%">
                  <DIV align=left>
                    <bean:define id="data1" name="fwjhxxForm" property="fwlbList"/>
                    <html:select property="fwlxdm" value="02"  disabled="true">
                      <html:options collection="data1" labelProperty="fwlxmc" property="fwlxdm"/>

                    </html:select>
                    <html:hidden property="fwlxmc"/>
                  </DIV></TD>
                </TR-->
            <TR>
              <TD  class=2-td2-left width="25%";>
                <DIV align=right>土地、房屋座落地址&nbsp; </DIV>
              </TD>
              <TD colspan="4"  class=2-td2-center width="15%">
                <DIV align=left><html:text property="tdfwzldz" name="fwjhxxForm" size="60" maxlength="200"/><FONT color=red>*</FONT> </DIV></TD>
              </TR>
            <TR>
              <TD class=2-td2-left width="25%";>
                <DIV align=right>土地、房屋权属转移面积&nbsp; </DIV>
              </TD>
              <TD  class=2-td2-left width="15%">
                <DIV align=left>土地：<html:text property="tdfwqszymj" name="fwjhxxForm" maxlength="14"/>㎡</DIV></TD>
                <TD class=2-td2-left width="17%">
                  <DIV align=right>房屋建筑面积&nbsp;</DIV></TD>
                  <TD colspan="2"  class=2-td2-center width="32%">
                    <DIV align=left><html:text property="fwjzmj" name="fwjhxxForm" maxlength="14"/>㎡</DIV></TD>
                  </TR>


          <TR>
            <TD class=2-td2-left width="25%";>
              <DIV align=right>容积率&nbsp; </DIV>
            </TD>
            <TD  class=2-td2-left width="15%">
              <DIV align=left>
				  	 <html:select property="rjl" >
                        <html:option value="01">1.0以上含1.0</html:option>
                        <html:option value="00">1.0以下</html:option>
                      </html:select>
			  </DIV></TD>
              <TD class=2-td2-left width="17%">
                  <!--修改土地级次为房屋所属区域-->
                <DIV align=right>房屋所属区域&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="32%">
                  <DIV align=left>
                      <bean:define id="data1" name="fwjhxxForm" property="tdjcList"/>
                                    <html:select property="tdjc">
                                      <html:options collection="data1" labelProperty="fwszqymc" property="fwszqydm"/>
                                    </html:select>

				  </DIV></TD>
           </TR>




          <TR>
            <TD class=2-td2-left width="8%" rowspan = "2">
              <DIV align=right>成交价格（评估价格） <FONT color=red>*</FONT></DIV>
              <TD  class=2-td2-left width="5%">
                <DIV align=left><html:text property="cjjgyrmb" name="fwjhxxForm" maxlength="15"/> 元(人民币) </DIV></TD>
                <TD class=2-td2-left width="17%">
                  <DIV align=right>税务机关核定价格&nbsp; </DIV></TD>
                  <TD  colspan="2"  class=2-td2-center width="32%">
                    <DIV align=left><html:text property="pgjg" name="fwjhxxForm" maxlength="15"/>元(人民币) </DIV></TD>
                  </TR>
              <TR>
                <TD class=2-td2-left width="15%";>
                  <DIV align=left><html:text property="cjjgywb" name="fwjhxxForm" maxlength="15" onchange="fnConvertWb(this,hn,zhyrmb)"/>元(外币) </DIV></TD>
                  <TD class=2-td2-left  width="25%" >
                    <DIV align=left>
                      <bean:define id="data1" name="fwjhxxForm" property="bzList" />
                      <html:select property="bzdm" onchange="doSubmitForm('GetHL')">
                        <html:options collection="data1" labelProperty="bzmc" property="bzdm" />
                    </html:select>
                    <html:hidden property="bz" />
                  </DIV>
                    <DIV align=left>汇率：<html:text property="hn"  name="fwjhxxForm" maxlength="15" onchange="fnConvertWb(cjjgywb,this,zhyrmb)"/> </DIV></TD>
                    <TD class=2-td2-center  width="17%">
                      <DIV align=left><html:text property="zhyrmb"  name="fwjhxxForm" maxlength="15"/><br>折合元(人民币) </DIV></TD>
                    </TR>


              </TBODY></TABLE><BR>
       <IMG alt=保存 height=22 id=baocun name="btnSave"
        onclick="doSubmitForm('Save');" onmouseout=MM_swapImgRestore()
        onmouseover="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
        src="<%=static_file%>images/baocun1.jpg" style="CURSOR: hand" width=79>

        <IMG alt=退出 height=22 id=tuichu name=tuichu
        onclick="doSubmitForm('Return')" onmouseout=MM_swapImgRestore()
        onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
        src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand" width=79>
        </DIV><BR>

</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
<script language=JavaScript type='text/JavaScript'>
    fnConvertWb(document.all.cjjgywb,document.all.hn,document.all.zhyrmb);
</script>
