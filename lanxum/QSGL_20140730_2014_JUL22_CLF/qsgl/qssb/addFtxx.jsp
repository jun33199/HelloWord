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


<HTML><HEAD><TITLE>录入土地房屋基础信息表</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<script language="javascript" src="<%=static_file%>/js/inputchecker.js"></script>
<script language="javascript" src="../js/qscommon.js"></script>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType){
  document.all.operationType.value=operationType;
  if(operationType=="save")
  {
	//add by hazhengze 20090108 start 
	//检查在房屋权属转移类型为房屋买卖或房屋交换时，必须选择房屋所属区域
	  if ((document.forms[0].tdfwqszylxdm.value == "03") || (document.forms[0].tdfwqszylxdm.value == "04")|| (document.forms[0].tdfwqszylxdm.value == "05")){
			if(document.forms[0].tdjc.value=="00"||document.forms[0].tdjc.value==""||document.forms[0].tdjc.value==null){
				alert("房屋交易必须选择房屋所在区域，请选择！");
				return false;
			}
	  }
	//add by hazhengze 20090108 end

	//房屋权属转移类型=土地转让或者出让,只能填写土地面积；否则可以不填或者只填一项
    if ((document.forms[0].tdfwqszylxdm.value == "01") || (document.forms[0].tdfwqszylxdm.value == "02"))
    {
	    if (!FN_QSCheckNumberDigit_Qssb(document.forms[0].tdfwqszymj.value,3,"土地面积"))
	    {
	      document.forms[0].tdfwqszymj.focus();
	      return false;
	    }
	    if (document.forms[0].fwjzmj.value !="")
	    {
		    if (!FN_QSCheckNumberDigit_Qssb(document.forms[0].fwjzmj.value,3,"房屋建筑面积"))
		    {
		      document.forms[0].fwjzmj.focus();
		      return false;
		    }
	    }
    }
    else
    {
    	if (!FN_QSCheckNumberDigit_Qssb(document.forms[0].fwjzmj.value,3,"房屋建筑面积"))
		    {
		      document.forms[0].fwjzmj.focus();
		      return false;
		    }
		  if (document.forms[0].tdfwqszymj.value !="")
	    {
		    if (!FN_QSCheckNumberDigit_Qssb(document.forms[0].tdfwqszymj.value,3,"土地面积！"))
		    {
		      document.forms[0].tdfwqszymj.focus();
		      return false;
		    }
	    }

	}
//    if(!check_Mj()){
//        //modify bu fujx bigin
//        var grbz = document.forms[0].person.value;
//        if(grbz=="true"){
//            if (!confirm("房屋面积大于1千平方米，是否确认?"))
//            {
//                return false;
//            }
//        }else{
//            if (!confirm("房屋面积大于1万平方米，是否确认?"))
//            {
//                return false;
//            }
//        }
//        //end modify by fujx
//    }
    if(!check_Jg()){
		if (!confirm("房屋总价小于5万元，是否确认?"))
		{
		   return false;
		}
    }

    document.forms[0].operationType.value='Save';

    if(document.forms[0].fdcxmmc.value == "")
    {
      alert("房地产项目名称不能为空，请重新输入！");
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

    if(document.forms[0].tdfwzldz.value == "")
    {
      alert("土地、房屋座落地址不能为空，请重新输入！");
      document.forms[0].tdfwzldz.focus();
      return false;
    }




	//税额调整为“首次购买90㎡以下普通住房”时，土地、房屋权属转移类型必须为“房屋买卖”，房屋建筑面积必须小于等于90平方米
	if(document.forms[0].setz.value=="5")
  {
  	if(document.forms[0].tdfwqszylxdm.value!="03")
  	{
    	alert("税额调整为“首次购买90㎡以下普通住房”时，土地、房屋权属转移类型必须为“房屋买卖”");
    	return false;
  	}

    var ce = parseFloat(document.forms[0].fwjzmj.value)-90;
    if(ce>0)
    {
    	alert("税额调整为“首次购买90㎡以下普通住房”时，房屋建筑面积必须小于等于90㎡");
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




    document.forms[0].flmc.value = document.forms[0].fldm.options[document.forms[0].fldm.selectedIndex].text;
    document.forms[0].fwlbmc.value = document.forms[0].fwlbdm.options[document.forms[0].fwlbdm.selectedIndex].text;
    document.forms[0].tdfwqszylxmc.value = document.forms[0].tdfwqszylxdm.options[document.forms[0].tdfwqszylxdm.selectedIndex].text;
    document.forms[0].bzmc.value = document.forms[0].bzdm.options[document.forms[0].bzdm.selectedIndex].text;

    if (document.forms[0].tdfwqszylxdm.value != "03"&&document.forms[0].tdfwqszylxdm.value != "05")  //不是房屋买卖、房屋交换
    {
        document.forms[0].fwlbdm.value = "";
        document.forms[0].fwlbmc.value = "";
    }
	if(document.forms[0].tdfwqszylxdm.value == "05" || document.forms[0].tdfwqszylxdm.value == "03") //不是房屋交换
	{
      document.forms[0].fwlbmc.value = document.forms[0].fwlbdm.options[document.forms[0].fwlbdm.selectedIndex].text;
	}
    if(document.forms[0].sfesf.checked==false)
    {
        document.forms[0].sfesf.value = '00';
    }




  }
  else if(operationType=="quit"){
    document.forms[0].operationType.value='Return';
  }
  document.forms[0].submit();
}




function checkSelect()
{
  if((document.forms[0].tdfwqszylxdm.value=="03") || (document.forms[0].tdfwqszylxdm.value=="05"))
  {
    document.forms[0].fwlbdm.disabled=false ;
  }
  else
  {
    document.forms[0].fwlbdm.disabled=true ;
  }
  if((document.forms[0].tdfwqszylxdm.value=="01") || (document.forms[0].tdfwqszylxdm.value=="02"))
  {
      document.forms[0].sfesf.checked=false;
      document.forms[0].sfesf.disabled=true ;
      document.forms[0].tdjc.value="00" ;
  }
  else
  {
      document.forms[0].sfesf.disabled=false ;
  }
}


//税额调整为“首次购买90㎡以下普通住房”时，土地房屋权属转移类型为“房屋买卖”，是否二手房可录
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
//二手房
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
	//由于2014年10月北京普通住房标准调整，故作此需改为新标准：每平米价格上限、每套房屋价格、所在区域三方面调整 ，
	//此处只修改所在区域显示。modified by gaoyh to 20141020
	
	var   objSelect   =  document.forms[0].tdjc; 
	/*******************
	var flag=false;
	if(!document.forms[0].sfesf.checked){
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
		    objSelect.options[0] =  	new   Option("请选择","00");
		      return false;
		    }
		 if ( !checkDate(document.forms[0].hyqdsj.value))
		    {
		      alert("合约签订时间格式不正确，请重新输入！");
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


    objSelect.options[0] =  	new   Option("请选择","00");
    
	if(flag){
 	
	    objSelect.options[1] =  	new   Option("四环内北部地区","11");
        objSelect.options[2] =  	new   Option("四环内南部地区","12");
        objSelect.options[3] =  	new   Option("四环至五环北部地区","13");
        objSelect.options[4] =  	new   Option("四环至五环南部地区","14");
        objSelect.options[5] =  	new   Option("五环至六环北部地区","15");
        objSelect.options[6] =  	new   Option("五环至六环南部地区","16");
        objSelect.options[7] =  	new   Option("六环外地区","17");
    }else{
		   objSelect.options[1] =  	new   Option("三环以内","01");
	        objSelect.options[2] =  	new   Option("三环至四环之间","02");
	        objSelect.options[3] =  	new   Option("四环至五环之间","03");
	        objSelect.options[4] =  	new   Option("五环以外","04");

    }
	*******************/
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
		    objSelect.options[0] =  	new   Option("请选择","00");
		      return 01;
		    }
		 if ( !checkDate(document.forms[0].hyqdsj.value))
		    {
		      alert("合约签订时间格式不正确，请重新输入！");
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
		      objSelect.options[0] =  	new   Option("请选择","00");
		      return 01;
		    }
		 if ( !checkDate(document.forms[0].hyqdsj.value))
		    {
		      alert("合约签订时间格式不正确，请重新输入！");
		      document.forms[0].hyqdsj.focus();
		      return 01;
		    }
	}
	
	var selectItem = objSelect.value;

    objSelect.options.length=0;


    objSelect.options[0] =  	new   Option("请选择","00");
    
    if(flag=="03"){
	    objSelect.options[1] =  	new   Option("5环内","21");
        objSelect.options[2] =  	new   Option("5-6环","22");
        objSelect.options[3] =  	new   Option("6环外","23");
    }
	if(flag=="02"){
	    objSelect.options[1] =  	new   Option("四环内北部地区","11");
        objSelect.options[2] =  	new   Option("四环内南部地区","12");
        objSelect.options[3] =  	new   Option("四环至五环北部地区","13");
        objSelect.options[4] =  	new   Option("四环至五环南部地区","14");
        objSelect.options[5] =  	new   Option("五环至六环北部地区","15");
        objSelect.options[6] =  	new   Option("五环至六环南部地区","16");
        objSelect.options[7] =  	new   Option("六环外地区","17");
    }
	if(flag=="01"){
		    objSelect.options[1] =  	new   Option("三环以内","01");
	        objSelect.options[2] =  	new   Option("三环至四环之间","02");
	        objSelect.options[3] =  	new   Option("四环至五环之间","03");
	        objSelect.options[4] =  	new   Option("五环以外","04");

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
 //alert("sbrq==>>" + sbrq);


</SCRIPT>

<BODY bgColor=#ffffff leftMargin=0
  onload="init();MM_preloadImages('<%=static_file%>imagess/dayin2.jpg','<%=static_file%>imagess/fanhui2.jpg','<%=static_file%>imagess/tuichu2.jpg','<%=static_file%>imagess/diyitiao2.jpg','<%=static_file%>imagess/shangyitiao2.jpg','<%=static_file%>imagess/zuimotiao2.jpg','<%=static_file%>imagess/xinzeng2.jpg','<%=static_file%>imagess/shanchu2.jpg'),checkSelect()"
  topMargin=0 marginheight="0" marginwidth="0">
  <jsp:include page="/include/Header.jsp" flush="true">
   <jsp:param name="subtitle" value="契税申报>录入土地房屋基础信息表"/>
    <jsp:param name="helpURL" value=""/>
  </jsp:include>


  <TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=770>
    <TBODY>
  <TR>
    <TD vAlign=top>

    <TABLE align=center cellSpacing=0 class=table-99>
      <TBODY>
       <TR>
        <TD class=1-td1>契税申报表-房屋、土地基础信息</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
            <html:form action="/qssb/addFtxx.do">
            <html:hidden property="xtdqsj"/>
              <html:hidden property="operationType" value=""/>
              <!--增加个人或者非个人的标志字段，用于提示时显示相应的最大面积值
              modify by fujx 20081128-->
              <html:hidden property="person"/>
              <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
                <TBODY>
                  <BR>
         <TR>
            <TD class=2-td2-t-left width="25%">
              <DIV align=right>申报表号&nbsp; </DIV>
            </TD>
            <TD class=2-td2-t-center colspan=5 >
              <DIV align=left>&nbsp;<bean:write name="ftxxForm" property="sbbh" /> </DIV></TD>
              <!--TD class=2-td2-t-left width="25%">
                <DIV align=right>土地、房屋唯一标识&nbsp; </DIV>
              </TD>
              <TD  colspan="3" class=2-td2-t-center width="15%">
                <DIV align=left>&nbsp;<bean:write name="ftxxForm" property="tdfwwybz" /></DIV></TD-->
        </TR>

          <TR>
            <TD class=2-td2-left width="25%">
              <DIV align=right>税额调整&nbsp; </DIV>
            </TD>
            <TD class=2-td2-left colspan=3 >
              <DIV align=left>&nbsp;
                  <html:select property = "setz" onchange="checkSelectOne()">
						<html:option value = "<%=Constants.SETZ_QEZS%>" >全额征收</html:option>
						<html:option value = "<%=Constants.SETZ_JBZS%>" >减半征收</html:option>
						<%if(ftxxForm.getPerson().equals("true")){%>
						<html:option value = "<%=Constants.SETZ_SCGMPTZF%>" >首次购买90㎡以下普通住房</html:option>
						<html:option value = "<%=Constants.SETZ_JJSYF%>" >经济适用房</html:option>

                        <%}%>
                    </html:select><FONT color=red>对于符合普通标准住宅标准的申报数据，计税价格根据此项确定。</FONT>
                </DIV>
            </td>
            <td class=2-td2-center>
                <div align="left">&nbsp;
                    <html:checkbox property="sfesf" value="01" onclick="esfOnclik()"/>
                    是否为二手房
                </div>
            </TD>
        </TR>
        <TR>
            <TD class=2-td2-left width="8%" rowspan = "8">
              <DIV align=right>	土地房屋</DIV>
              <DIV align=right>权属转移</DIV></TD>
              <TD class=2-td2-left width="15%">
                <DIV align=right>房地产项目名称&nbsp;</DIV></TD>
                <TD colspan="4" class=2-td2-center width="15%">
                  <DIV align=left><html:text property="fdcxmmc" size="60" maxlength="100" /><FONT color=red>*</FONT> </DIV></TD>
                </TR>
        <TR>
          <TD class=2-td2-left width="15%";>
            <DIV align=right>合约签订时间&nbsp; </DIV>
          </TD>
          <TD class=2-td2-left width="19%">
            <DIV align=left><html:text property="hyqdsj" onchange="javascript:hyqdsjChange();" /><FONT color=red>*</FONT>(yyyymmdd) </DIV></TD>
            <TD class=2-td2-left width="19%">
              <DIV align=right>购房原因&nbsp;</DIV></TD>
              <TD colspan="2"  class=2-td2-center width="33%">
                <DIV align=left>
                  <bean:define id="data1" name="ftxxForm" property="flList" />
                  <html:select property="fldm" >
                    <html:options collection="data1" labelProperty="qstdfwytmc" property="qstdfwytdm" />
                  </html:select>
                  <html:hidden property="flmc"/>
                </DIV></TD>
              </TR>
     <TR>
      <TD class=2-td2-left width="15%";>
      <DIV align=right>土地、房屋权属转移类型&nbsp; </DIV> </TD>
      <TD class=2-td2-left width="19%">
        <DIV align=left>
          <bean:define id="data1" name="ftxxForm" property="tdfwqszylxList" />
          <html:select property="tdfwqszylxdm" onchange="checkSelect()" onclick="" >
            <html:options collection="data1" labelProperty="qszyxsmc" property="qszyxsdm" />
            <html:hidden property="tdfwqszylxmc" />


          </html:select> <FONT color=red>*</FONT></DIV></TD>
          <TD class=2-td2-left width="19%">
            <DIV align=right>房屋类别&nbsp;</DIV></TD>
            <TD colspan="2"  class=2-td2-center width="33%">
              <DIV align=left>
                <bean:define id="data1" name="ftxxForm" property="fwlbList"/>
                <html:select property="fwlbdm">

                  <html:options collection="data1" labelProperty="fwlxmc" property="fwlxdm"/>

                </html:select> </DIV></TD>
                <html:hidden property="fwlbmc"/>
              </TR>
          <TR>
            <TD  class=2-td2-left width="25%";>
              <DIV align=right>土地、房屋座落地址&nbsp; </DIV>
            </TD>
            <TD colspan="4"  class=2-td2-center width="15%">
              <DIV align=left><html:text property="tdfwzldz" size="60" maxlength="200"/> <FONT color=red>*</FONT></DIV></TD>
            </TR>
          <TR>
            <TD class=2-td2-left width="25%";>
              <DIV align=right>土地、房屋权属转移面积&nbsp; </DIV>
            </TD>
            <TD  class=2-td2-left width="15%">
              <DIV align=left>土地：<html:text property="tdfwqszymj" size="15" maxlength="14" />㎡</DIV></TD>
              <TD class=2-td2-left width="17%">
                <DIV align=right>房屋建筑面积&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="32%">
                  <DIV align=left><html:text property="fwjzmj" maxlength="14" />㎡</DIV></TD>
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
                <DIV align=right>房屋所在区域&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="32%">
                
                  <DIV align=left>
                  
                  
                   <html:select property="tdjc" >
                   
                       
                    </html:select>
                  
                     <!-- 
                      <bean:define id="data1" name="ftxxForm" property="tdjcList"/>
                                    <html:select property="tdjc">
                                      <html:options collection="data1" labelProperty="fwszqymc" property="fwszqydm"/>
                                    </html:select>
                         <html:select property="tdjc" >
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
                      </html:select>
                       -->
				  </DIV>
				  
				  
				  </TD>
           </TR>


          <TR>
            <TD class=2-td2-left width="8%" rowspan = "2">
              <DIV align=right>成交价格（评估价格）<FONT color=red>*</FONT></DIV>
              <TD  class=2-td2-left width="5%">
                <DIV align=left><html:text property="cjjgyrmb" maxlength="15"/> 元(人民币) </DIV></TD>
                <TD class=2-td2-left width="17%">
                  <DIV align=right>税务机关核定价格&nbsp; </DIV></TD>
                  <TD  colspan="2"  class=2-td2-center width="32%">
                    <DIV align=left><html:text property="pgjg" maxlength="15"/> 元(人民币) </DIV></TD>
                  </TR>
            <TR>
              <TD class=2-td2-left width="15%";>
                <DIV align=left><html:text property="cjjgywb" maxlength="15" onchange="fnConvertWb(this,hn,zhyrmb)"/> 元(外币) </DIV></TD>
                <TD class=2-td2-left width="25%">
                  <DIV align=left>
                    <bean:define id="data1" name="ftxxForm" property="bzList" />
                    <html:select property="bzdm" onchange="doSubmitForm('GetHL')">
                      <html:options collection="data1" labelProperty="bzmc" property="bzdm" />
                    </html:select>
                    <html:hidden property="bzmc"/>
                  </DIV>
                  <DIV align=left>汇率:&nbsp<html:text property="hn" maxlength="15" onchange="fnConvertWb(cjjgywb,this,zhyrmb)"/></DIV></TD>
                  <TD class=2-td2-center  width="17%">
                    <DIV align=left><html:text property="zhyrmb" maxlength="15"/> 折合元(人民币) </DIV></TD>
                  </TR>

                    </TBODY></TABLE><BR>

        <DIV align=center>
          <IMG name="baocun"
            onClick= "doSubmitForm('save');"
            onMouseOver="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
            onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/baocun1.jpg"
            width="79" height="22" id="baocun" alt="保存" style="cursor:hand">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

            <IMG name="tuichu1"
              onclick="doSubmitForm('quit');"
              onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
              onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
              width="79" height="22" id="tuichu1" alt="退出" style="cursor:hand">
            </DIV><BR>

</html:form>
  <%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>

<script language=JavaScript type='text/JavaScript'>
  function init()
  {
    <!-- yangxiao 2008-12-06 start -->
    <%if(ftxxForm.smbs.equals("1")){%>
    //临时用，以后要恢复 20111209
    document.forms[0].hyqdsj.readOnly = true;
    document.forms[0].hyqdsj.style.color="#ADADAD";
    document.forms[0].tdfwzldz.readOnly = true;
    document.forms[0].tdfwzldz.style.color="#ADADAD";
    document.forms[0].fwjzmj.readOnly = true;
    document.forms[0].fwjzmj.style.color="#ADADAD";
    //document.forms[0].sfesf.checked="checked";
    var sfesf = <%=ftxxForm.sfesf%>;
	if(sfesf == "1"){
		document.forms[0].sfesf.checked="checked";
	}

    tmpObj_page=document.forms[0].tdfwqszylxdm;
    var tmpObj = "03";
    <%if(ftxxForm.tdfwqszylxdm !=null && !ftxxForm.tdfwqszylxdm.equals("")){%>
    tmpObj = "0"+<%=ftxxForm.tdfwqszylxdm%>;
    <%}%>
    //alert("tmpObj=="+tmpObj);
    for(i=0;i<tmpObj_page.options.length;i++){
      if(tmpObj_page.options.value=tmpObj){
			tmpObj_page.style.color="#ADADAD";
			tmpObj_page.selectedIndex=i;
			tmpObj_page.value=tmpObj;
			tmpObj_page.onclick=function qr_readonly_tdfwqszylx(){alert("请勿修改！");return false;};//置位只读
		}
    }

    tmpObj_page=document.forms[0].cjjgyrmb;
    if(tmpObj_page.value != "" && tmpObj_page.value != null){
      tmpObj_page.readOnly = true;
      tmpObj_page.style.color="#ADADAD";
    }
    <%}%>
    <!-- yangxiao 2008-12-06 end -->
    if(document.forms[0].tdfwqszylxdm.value=="03"||document.forms[0].tdfwqszylxdm.value=="05")
    {

    }
	else
	  {
		document.forms[0].fwlbdm.disabled=true;
	  }

    fnConvertWb(document.all.cjjgywb,document.all.hn,document.all.zhyrmb);
  }
  
  changeFwszqy();
  
  var fwqydm = '<bean:write name="ftxxForm" property="tdjc" />';
  if(fwqydm  == '' || fwqydm == null ) fwqydm = '00';
  document.forms[0].tdjc.value=fwqydm;
  </script>
