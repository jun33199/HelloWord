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
<HTML><HEAD><TITLE>土地房屋基础信息修改表</TITLE>
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
	//检查在房屋权属转移类型为房屋买卖或房屋交换时，必须选择房屋所属区域
	  if ((document.forms[0].tdfwqszylxdm.value == "03") || (document.forms[0].tdfwqszylxdm.value == "04")|| (document.forms[0].tdfwqszylxdm.value == "05")){
			if(document.forms[0].tdjc.value=="00"){
				alert("房屋交易必须选择房屋所在区域，请选择！");
				return false;
			}
	  }
	//add by hazhengze 20090108 end


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


function fnSave()//保存
    {


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

    //房屋权属转移类型=土地转让或者出让,只能填写土地面积；否则可以不填或者只填一项
    if ((document.forms[0].tdfwqszylxdm.value == "01") || (document.forms[0].tdfwqszylxdm.value == "02"))
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
	if(document.forms[0].tdfwqszylxdm.value == "05" || document.forms[0].tdfwqszylxdm.value == "03") //不是房屋交换
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
//判断是否是中文字符
//parameter strValue 字符串类型
//return true，有中文字符；false，没有中文字符
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
</SCRIPT>

<BODY bgColor=#ffffff leftMargin=0
onload="checkSelect();MM_preloadImages('<%=static_file%>imagess/dayin2.jpg','<%=static_file%>imagess/fanhui2.jpg','<%=static_file%>imagess/tuichu2.jpg','<%=static_file%>imagess/diyitiao2.jpg','<%=static_file%>imagess/shangyitiao2.jpg','<%=static_file%>imagess/zuimotiao2.jpg','<%=static_file%>imagess/xinzeng2.jpg','<%=static_file%>imagess/shanchu2.jpg')"
topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="契税申报&gt;土地房屋基础信息修改表"/>
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
            <html:form action="/qssb/modifyFwxx.do">
            <html:hidden property="xtdqsj"/>
              <html:hidden property="operationType" value=""/>
              <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
                <TBODY>
                  <BR>
          <TR>
            <TD class=2-td2-t-left width="25%">
              <DIV align=right>申报表号&nbsp; </DIV>
            </TD>
            <TD class=2-td2-t-left>
              <DIV align=left>&nbsp;<bean:write name="ftxxForm" property="sbbh" /> </DIV></TD>
              <TD class=2-td2-t-left>
                <DIV align=right>土地、房屋唯一标识&nbsp; </DIV>
              </TD>
                    <TD  colspan="3" class=2-td2-t-center width="15%">
                      <DIV align=left>&nbsp;<bean:write name="ftxxForm" property="tdfwwybz" /></DIV></TD>
                    </TR>
              <TR>


          <TR>
            <TD class=2-td2-left>
              <DIV align=right>税额调整&nbsp; </DIV>
            </TD>
            <TD class=2-td2-left colspan=3>
                <div align="left">
                <html:select property = "setz" onchange="checkSelectOne()">
                    <html:option value = "<%=Constants.SETZ_QEZS%>" >全额征收</html:option>
                    <html:option value = "<%=Constants.SETZ_JBZS%>" >减半征收</html:option>
									<%if(ftxxForm.getPerson().equals("true")){%>
										<html:option value = "<%=Constants.SETZ_SCGMPTZF%>" >首次购买90㎡以下普通住房</html:option>
									<html:option value = "<%=Constants.SETZ_JJSYF%>" >经济适用房</html:option>
                                        <%}%>
                </html:select><FONT color=red>对于符合普通标准住宅标准的申报数据，计税价格根据此项确定。</FONT>
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
                    是否为二手房
            </TD>
        </TR>

                <TD class=2-td2-left width="8%" rowspan = "8">
                  <DIV align=right>	土地房屋</DIV>
                  <DIV align=right>权属转移</DIV></TD>
                  <TD class=2-td2-left width="15%">
                    <DIV align=right>房地产项目名称&nbsp;</DIV></TD>
                    <TD colspan="4" class=2-td2-center width="15%">
                      <DIV align=left><html:text property="fdcxmmc" maxlength="100"/><FONT color=red>*</FONT> </DIV></TD>
                    </TR>
                <TR>
                  <TD class=2-td2-left width="15%";>
                    <DIV align=right>合约签订时间&nbsp; </DIV>
                  </TD>
                  <TD class=2-td2-left width="19%">
                    <DIV align=left><html:text property="hyqdsj" onchange="javascript:hyqdsjChange()" /><FONT color=red>*</FONT></DIV></TD>


<logic:equal name="ftxxForm" property="bzqs"  value="false">
                    <TD class=2-td2-left width="19%">
                      <DIV align=right>购房原因&nbsp;</DIV></TD>
</logic:equal>
<logic:equal name="ftxxForm" property="bzqs"  value="true">

                    <TD class=2-td2-left width="19%">
                      <DIV align=right>分类&nbsp;</DIV></TD>
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
                    <DIV align=right>土地、房屋权属转移类型&nbsp; </DIV> </TD>
                    <TD class=2-td2-left width="19%">
                      <DIV align=left>

					         <bean:define id="data1" name="ftxxForm" property="tdfwqszylxList" />
					         <html:select property="tdfwqszylxdm" onchange="checkSelect()" >
					           <html:options collection="data1" labelProperty="qszyxsmc" property="qszyxsdm" />
			                 </html:select><FONT color=red>*</FONT>

			    	       <html:hidden property="tdfwqszylxmc"/>

                             </DIV></TD>
                                <TD class=2-td2-left width="19%">
                                  <DIV align=right>房屋类别&nbsp;</DIV></TD>
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
                        <DIV align=right>土地、房屋座落地址&nbsp; </DIV>
                      </TD>
                      <TD colspan="4"  class=2-td2-center width="15%">
                        <DIV align=left><html:text property="tdfwzldz" maxlength="200"/><FONT color=red>*</FONT> </DIV></TD>
                      </TR>
                  <TR>
                    <TD class=2-td2-left width="25%";>
                      <DIV align=right>土地、房屋权属转移面积&nbsp; </DIV>
                    </TD>
                    <TD  class=2-td2-left width="15%">
                      <DIV align=left>土地：<html:text property="tdfwqszymj" maxlength="14"/>㎡</DIV></TD>
                      <TD class=2-td2-left width="17%">
                        <DIV align=right>房屋建筑面积&nbsp;</DIV></TD>
                        <TD colspan="2"  class=2-td2-center width="32%">
                          <DIV align=left><html:text property="fwjzmj" maxlength="14"/>㎡</DIV></TD>
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
			           <html:select property="tdjc" >
                          

                      </html:select>
				  </DIV></TD>
           </TR>






                    <TR>
                      <TD class=2-td2-left width="8%" rowspan = "2">
                        <DIV align=right>成交价格（评估价格） <FONT color=red>*</FONT></DIV>
                        <TD  class=2-td2-left width="5%">
                          <DIV align=left><html:text property="cjjgyrmb" maxlength="15"/> 元(人民币) </DIV></TD>
                          <TD class=2-td2-left width="17%">
                            <DIV align=right>税务机关核定价格&nbsp; </DIV></TD>
                            <TD  colspan="2"  class=2-td2-center width="32%">
                              <DIV align=left><html:text property="pgjg" maxlength="15"/>元(人民币) </DIV></TD>
                            </TR>
                      <TR>
                        <TD class=2-td2-left width="15%";>
                          <DIV align=left><html:text property="cjjgywb" maxlength="15" onchange="fnConvertWb(this,hn,zhyrmb)"/>元(外币) </DIV></TD>
                          <TD class=2-td2-left width="25%">
                            <DIV align=left>
                                     <bean:define id="data1" name="ftxxForm" property="bzList" />
                              <html:select property="bzdm" onchange="doSubmitForm('GetHL')">
                                <html:options collection="data1" labelProperty="bzmc" property="bzdm" />
                            </html:select>
                          <html:hidden property="bzmc"/>
                          </DIV>
                            <DIV align=left>汇率:&nbsp;<html:text property="hn" maxlength="15" onchange="fnConvertWb(cjjgywb,this,zhyrmb)"/> </DIV></TD>
                            <TD class=2-td2-center  width="17%">
                              <DIV align=left><html:text property="zhyrmb" maxlength="5" />折合元(人民币) </DIV></TD>
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
<script language="javascript">


	if(document.forms[0].tdfwqszylxdm.value=="03"||document.forms[0].tdfwqszylxdm.value=="05")
    {
       document.forms[0].fwlbdm.disabled=false ;
    }
    //fnConvertWb(document.all.cjjgywb,document.all.hn,document.all.zhyrmb);
 changeFwszqy();
 document.forms[0].tdjc.value='<bean:write name="ftxxForm" property="tdjc" />';
</SCRIPT>
