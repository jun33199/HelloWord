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
<TITLE>契税减免申报个人信息录入表</TITLE>
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
  	   alert("申报日期不能为空，请重新输入！");
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
		alert("请选择减免理由！");
		return false;
	}
  	
  	if(!checkDate(document.forms[0].sbrq.value)){
  	   alert("申报日期不正确，正确格式为yyyymmdd，不能大于2100，小于1900年，请重新输入！");
  	   document.forms[0].sbrq.focus();
  	   return false;
  	}
  	
  	if (!cmpDate(document.forms[0].sbrq.value,document.forms[0].xtdqsj.value))
  	{
  	   alert("申报日期不能大于当前时间，请重新输入！");
  	   document.forms[0].sbrq.focus();
  	   return false;
  	}
	  	
     if (document.forms[0].fdcxmmc.value == "")
	  	{
	  	   alert("房地产项目名称不能为空，请重新输入！ ");
	  	   document.forms[0].fdcxmmc.focus();
	  	   return false;
	  	}

     if (document.forms[0].hyqdsj.value == "" || !checkDate(document.forms[0].hyqdsj.value))
	  	{
	  	   alert("合约签订时间不能为空或格式不正确，请重新输入！");
	  	   document.forms[0].hyqdsj.focus();
	  	   return false;
	  	}
	
 	
     if (!cmpDate(document.forms[0].hyqdsj.value,document.forms[0].xtdqsj.value))
	  	{
	  	   alert("合约签订时间不能大于当前时间，请重新输入！");
	  	   document.forms[0].hyqdsj.focus();
	  	   return false;
	  	}  
		
     if (document.forms[0].tdfwzldz.value == "")
	  	{
	  	   alert("土地、房屋座落地址不能为空，请重新输入！");
	  	   document.forms[0].tdfwzldz.focus();
	  	   return false;
	  	}
 
    //房屋权属转移类型=土地转让或者出让,只能填写土地面积；否则可以不填或者只填一项
    if ((document.forms[0].tdfwqszylx.value == "01") || (document.forms[0].tdfwqszylx.value == "02"))
	    {
		    if (!FN_QSCheckNumberDigit(document.forms[0].tdfwqszymj.value,2,"土地面积"))
		    {
		      document.forms[0].tdfwqszymj.focus();
		      return false;
		    }
		    if (document.forms[0].fwjzmj.value !="")
		    {
			    if (!FN_QSCheckNumberDigit(document.forms[0].fwjzmj.value,2,"房屋建筑面积"))
			    {
			      document.forms[0].fwjzmj.focus();
			      return false;
			    }
		    }
	    }	  
    else
	    {
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
		    if (!FN_QSCheckNumberDigit(document.forms[0].pgjg.value,2,"人民币评估价格"))
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
 
 	//如果成交人民币价格为空，评估价格和外币价格不能都为空
    if(document.forms[0].cjjgyrmb.value == ""){
      if(document.forms[0].pgjg.value == ""){
        if(document.forms[0].cjjgywb.value == "")
        {
          alert("成交人民币价格、评估价格和外币价格不能都为空！");
          document.forms[0].cjjgyrmb.focus();
          return false;
        }
        else if(document.forms[0].cjjgywb.value != "")
        {

          if(document.forms[0].bz.value == "")
          {
            alert("币种不能为空，请输入！");
            document.forms[0].bz.focus();
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

      if(document.forms[0].bz.value == "")
      {
        alert("币种不能为空，请输入！");
        document.forms[0].bz.focus();
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
		    //如果是土地出让或者转让
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

function checkSelect(jmlbdm,jmxzdm)
{
	var isShowQtjmly = false;
				 
    //选择其它减免理由的可以输入减免理由内容
	 if(jmlbdm == <%=Constants.CXXJM_JMXMDM_QT%>){
	 	isShowQtjmly = true;
	 }

 	 		  	
  	if(isShowQtjmly){
  		//兼容firefox
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
        var flagConfirm=confirm("确认删除当前纪录！");
        if(flagConfirm==true){
           daList.deleteRow(daList.DynamicTable.CurrentRow);
        }
    }


	function initPage()
	{
        //序号* 纳税人名称* 联系电话* 身份证件类型* 身份证件号码* 国籍* 是否主产权人*
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

    function checkJmxzdm(obj){
    	document.forms[0].qtjmlybeizhu.value = obj.options[obj.selectedIndex].text;
    }

</SCRIPT>


<!----本页面的头文件和本页帮助的路径----->

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0
	marginheight="0" marginwidth="0" onload="initPage()">
<jsp:include page="/include/Header.jsp" flush="true">
	<jsp:param name="subtitle" value="契税减免申报录入>契税减免申报个人信息录入表" />
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
						<TD class=1-td1>北京市地方税务局契税减免申报表-个人</TD>
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
										<TD class=2-td1-left width="30%">契税减免申报表号</TD>
										<html:hidden name="jmsbForm" property="sbbh" />
										<TD class=2-td1-center width="70%" colspan="3">
										<DIV align=left>&nbsp;&nbsp;<bean:write name="jmsbForm"
											property="sbbh" /></DIV>
										</TD>

									</TR>
									<TR>

										<TD class=2-td2-left width="20%">房屋土地管理部门受理号</TD>
										<TD class=2-td2-left width="35%">
										<DIV align=left><html:text property="fwtdbmslh"
											size="30" maxlength="30" /></DIV>
										</TD>
										<TD class=2-td2-left width="22%">申报日期(yyyymmdd)</TD>
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
										<DIV align=left>&nbsp;&nbsp;个人填写部分</DIV>
										</TD>
										<TD class=2-td2-t-center width="30%" colspan="2">
										<DIV align=right><img
											onMouseOver="MM_swapImage('add','','<%=static_file%>/images/xinzeng2.jpg',1)"
											onMouseOut="MM_swapImgRestore()" value="新增" id="Submit522"
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
												<td width="2%" class="2-td1-left">序号</td>
												<td width="25%" class="2-td1-left">纳税人名称<span
													class="bitian">*</span></td>
												<td width="13%" class="2-td1-left">联系电话</td>
												<td width="13%" class="2-td1-left">身份证件类型<span
													class="bitian">*</span></td>
												<td width="20%" class="2-td1-left">身份证件号码<span
													class="bitian">*</span></td>
												<td width="5%" class="2-td1-left">国籍<span
													class="bitian">*</span></td>
												<td width="7%" class="2-td1-left">是否主产权人<span
													class="bitian">*</span></td>
												<td width="5%" class="2-td1-center">&nbsp;&nbsp;</td>
											</tr>



										</table>
										</TD>
									</TR>


									<TR>
										<TD class=2-td2-left width="8%" rowspan="7">
										<DIV align=right>土地房屋</DIV>
										<DIV align=right>权属转移</DIV>
										</TD>
										<TD class=2-td2-left width="15%">
										<DIV align=right>房地产项目名称&nbsp;</DIV>
										</TD>
										<TD colspan="4" class=2-td2-center width="15%">
										<DIV align=left><html:text property="fdcxmmc" size="60"
											maxlength="100" /><FONT color=red>*</FONT></DIV>
										</TD>
									</TR>
									<TR>
										<TD class=2-td2-left width="15%";>
										<DIV align=right>合约签订时间&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left width="19%">
										<DIV align=left><html:text property="hyqdsj" size="15" /><FONT
											color=red>*</FONT><br>
										(yyyymmdd)</DIV>
										</TD>


										<TD class=2-td2-left width="19%">
										<DIV align=right>分类&nbsp;</DIV>
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
										<DIV align=right>土地、房屋座落地址&nbsp;</DIV>
										</TD>
										<TD colspan="4" class=2-td2-center width="15%">
										<DIV align=left><html:text property="tdfwzldz" size="60"
											maxlength="200" /><FONT color=red>*</FONT></DIV>
										</TD>
									</TR>


									<TR>
										<TD class=2-td2-left width="15%";>
										<DIV align=right>土地、房屋权属转移类型&nbsp;</DIV>
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
										<DIV align=right>房屋类别&nbsp;</DIV>
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
										<DIV align=right>土地、房屋权属转移面积&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left width="15%">
										<DIV align=left>土地：<html:text property="tdfwqszymj"
											size="15" maxlength="14" />㎡</DIV>
										</TD>
										<TD class=2-td2-left width="17%">
										<DIV align=right>房屋建筑面积&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="33%">
										<DIV align=left><html:text property="fwjzmj" size="20"
											maxlength="14" />㎡</DIV>
										</TD>
									</TR>


									<!-- TR>
										<TD class=2-td2-left width="25%";>
										<DIV align=right>容积率&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left width="15%">
										<DIV align=left><html:select property="rjl">
											<html:option value="01">1.0以上含1.0</html:option>
											<html:option value="00">1.0以下</html:option>
										</html:select></DIV>
										</TD>
										<TD class=2-td2-left width="17%">
										<DIV align=right>土地级次&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="33%">
										<DIV align=left><html:select property="tdjc">
											<html:option value="01">一级</html:option>
											<html:option value="02">二级</html:option>
											<html:option value="03">三级</html:option>
											<html:option value="04">四级</html:option>
											<html:option value="05">五级</html:option>
											<html:option value="06">六级</html:option>
											<html:option value="07">七级</html:option>
											<html:option value="08">八级</html:option>
											<html:option value="09">九级</html:option>
											<html:option value="10">十级</html:option>
											<html:option value="11">六至十级</html:option>
										</html:select></DIV>
										</TD>
									</TR-->


									<TR>
										<TD class=2-td2-left width="8%" rowspan="2">
										<DIV align=right>成交价格 <FONT color=red>*</FONT></DIV>
										<TD class=2-td2-left width="5%">
										<DIV align=left><html:text property="cjjgyrmb" size="20"
											maxlength="15" /><br>
										元(人民币)</DIV>
										</TD>
										<TD class=2-td2-left width="17%">
										<DIV align=right>税务机关核定价格&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="32%">
										<DIV align=left><html:text property="pgjg" size="20"
											maxlength="15" /> <br>
										元(人民币)</DIV>
										</TD>
									</TR>

									<TR>
										<TD class=2-td2-left width="15%">
										<DIV align=left><html:text property="cjjgywb" size="20"
											maxlength="15" onchange="fnConvertWb(this,hn,zhyrmb)" /><br>
										元(外币)</DIV>
										</TD>
										<TD class=2-td2-center width="33%">
										<DIV align=left><html:hidden property="bzmc" /> <bean:define
											id="list" name="jmsbForm" property="bzList" /> <html:select
											property="bz" onchange="checkSubmit('GetHL')">
											<html:options collection="list" labelProperty="bzmc"
												property="bzdm" />
										</html:select></DIV>
										<DIV align=left>
										汇率:<html:text property="hn" size="15" maxlength="15"
											onchange="fnConvertWb(cjjgywb,this,zhyrmb)" /></DIV>
										</TD>

										<TD class=2-td2-center colspan="2" width="25%">
										<DIV align=left><html:text property="zhyrmb" size="20"
											maxlength="15" /><br>
										折合元(人民币)</DIV>
										</TD>

									</tr>

									<TR>
										<TD class=2-td2-left width="15%";>
										<DIV align=right>申请减税、免税理由<FONT color=red>*</FONT>&nbsp;</DIV>
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
										<DIV align=right>其它减免理由<FONT color=red>*</FONT>&nbsp;</DIV>
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
										<DIV align=right>备注&nbsp;</DIV>
										</TD>
										<TD colspan="5" class=2-td2-center width="85%">
										<DIV align=left><html:textarea property="beizhu"
											cols="45" rows="5" /></DIV>
										</TD>
									</TR>

								</TBODY>
							</TABLE>
							<BR>

							<DIV align=center><IMG alt=保存 height=22 id=baocun
								name=Submit63 onclick="checkSubmit('Save')"
								onmouseout=MM_swapImgRestore()
								onmouseover="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
								src="<%=static_file%>images/baocun1.jpg" style="CURSOR: hand"
								width=79> <IMG alt=退出 height=22 id=tuichu name=tuichu
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
