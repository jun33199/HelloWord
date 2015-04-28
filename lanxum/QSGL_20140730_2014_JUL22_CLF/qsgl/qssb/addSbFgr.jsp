<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@page import="java.util.ArrayList"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<!-- yangxiao 2008-12-06 start -->
<%@include file="../include/QRCodeHeader.jsp"%>
<!-- yangxiao 2008-12-06 end -->
<HTML><HEAD><TITLE>契税申报非个人录入表</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type="text/JavaScript"></SCRIPT>
<script language="javascript" src="../js/qscommon.js" type="text/JavaScript"></script>
<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType){
  document.all.operationType.value=operationType;
  if(operationType=="GetNsrxx" )
  {
  	if (document.forms[0].jsjdm.value == "")
  	{
  	   alert("请输入计算机代码");
  	   document.forms[0].jsjdm.focus();
  	   return false;
  	}
  }

  else if(operationType=="Fwjbxx" ){
    document.forms[0].operationType.value='Redirect';
    document.forms[0].subAction.value='fwjbxx';
  }else if(operationType=="Cqxx"){
    document.forms[0].operationType.value='Redirect';
    document.forms[0].subAction.value='cqxx';
  }else if(operationType=="Gyzf"){
    document.forms[0].operationType.value='Redirect';
    document.forms[0].subAction.value='gyzf';
  }
  else if(operationType=="Fwjhxx"){
    document.forms[0].operationType.value='Redirect';
    document.forms[0].subAction.value='fwjhxx';
  }
  else if(operationType=="Print"){
    document.forms[0].operationType.value='Redirect';
    document.forms[0].subAction.value='print';
  }
  else if(operationType=="Save")
  {

    if (document.forms[0].sbbh.value != "")
    {
    	alert("申报主表记录已经生成，不能保存，如果想修改，请从修改功能修改。");
    	return false;
    }

    if(document.forms[0].bl.value=="true")
    {

		if (document.forms[0].sbrq.value == "" || !checkDate(document.forms[0].sbrq.value))
		{
			alert("申报日期不能为空或格式不正确，请重新输入！");
			document.forms[0].sbrq.focus();
			return false;
		}
		if (!cmpDate(document.forms[0].sbrq.value,document.forms[0].xtdqsj.value))
		{
			alert("申报日期不能大于当前时间，请重新输入！");
			document.forms[0].sbrq.focus();
			return false;
		}
     }

  	if (document.forms[0].jsjdm.value == "")
  	{
  	   alert("计算机代码不能为空，请重新输入！");
  	   document.forms[0].jsjdm.focus();
  	   return false;
  	}
  	if (document.forms[0].nsrmc.value == "")
  	{
  	   alert("纳税人名称不能为空，请通过计算机代码获取！");
  	   //document.forms[0].nsrmc.focus();
       //因鼠标无法定位到nsrmc，暂时修改为图片----yangxiao--2009.2.9
       document.all.imgGetNsrxx.focus();
  	   return false;
  	}

    if (document.forms[0].lxrxm.value == "")
  	{
  	   alert("联系人姓名不能为空，请重新输入！");
  	   document.forms[0].lxrxm.focus();
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

    document.forms[0].nsrlxmc.value = document.forms[0].nsrlxdm.options[document.forms[0].nsrlxdm.selectedIndex].text;
    document.forms[0].jkfsmc.value = document.forms[0].jkfsdm.options[document.forms[0].jkfsdm.selectedIndex].text;
    if (document.forms[0].yhdm.value != "")
    {
       document.forms[0].yhmc.value = document.forms[0].yhdm.options[document.forms[0].yhdm.selectedIndex].text;
    }
  }

	if ((operationType == "Return") && (document.forms[0].sbbh.value != ""))
	{
	   if (document.forms[0].fwjbxxAdded.value !="true")
	   {
	      if (!confirm("你还没有增加房屋权属转移信息，如果此时退出，这条记录将删除。是否退出？"))
	          return false;
	   }
	}
    <!-- yangxiao 2008-12-06 start -->
    if (operationType == "Shengcheng")
    {
//      alert("生成");
      //定位焦点到扫描组件的录入域上
      QRInputFocus();

      //调用扫描方法
      str_temp=document.all.piccode.value;
      if(str_temp==""){
        alert("无扫描数据，请重新扫描输入！");
        return false;
      }else{
        scanPic();
        return false;
      }
    }
    if (operationType == "Fuwei")
    {
//      alert("复位");
      //调用复位方法
      fuwei();
      document.forms[0].operationType.value='Show';
      return false;
    }
    <!-- yangxiao 2008-12-06 end -->
    document.forms[0].submit();
}



function doPrintSbb()
{
    window.open("/qsgl/qssb/printSbxx.do?sbbh="+document.forms[0].sbbh.value,"print","toolbar=no,location=yes,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=640,height=480");
}

</SCRIPT>

<!--add by yangxiao 20081206 start-->
<script language="javascript">
//将页面元素复位
function fuwei(){
  //计算机代码
  document.forms[0].jsjdm.value="";
  //开户银行
  document.forms[0].yhdm.value="";
  //联系电话
  document.forms[0].lxdh.value="";

  //复位合同编号
  document.forms[0].htbh.readOnly = false;
  document.forms[0].htbh.style.color="";
  //纳税人名称

  for(i=0;i<document.getElementsByName("nsrmc");i++){
    document.getElementsByName("nsrmc")[i].value="";
  }
  var ele_nsrmc_show=document.getElementById('nsrmc_show');
  if(ele_nsrmc_show.hasChildNodes() != null){
    ele_nsrmc_show.removeChild(ele_nsrmc_show.lastChild);
  }
  //将焦点设置到条形码输入框
  document.forms[0].piccode.focus();
}
function initPage(){

    //判断是否为点击获取登记信息后触发的事件
    var smbs = document.forms[0].smbs.value;
    if(smbs == "1"){
      <!--add by yangxiao 20090209 start-->
      var smnsrmc = document.forms[0].smnsrmc.value;
      var nsrmc = document.forms[0].nsrmc.value;
      if(nsrmc == ""){
        alert("纳税人不存在，请重新通过计算机代码获取！");
        document.all.imgGetNsrxx.focus();
      }else{
        if (nsrmc != smnsrmc){
          alert("纳税人名称与扫描取得的纳税人名称不相同，请检查登记信息！");
          document.all.imgGetNsrxx.focus();
        }
      }

      <!--add by yangxiao 20090209 end-->
      document.forms[0].htbh.readOnly = true;
      document.forms[0].htbh.style.color="#ADADAD";
    }else{
      //alert("初试化定焦点！");
      //将焦点设置到条形码输入框
      document.forms[0].piccode.focus();
    }
}
//定位焦点到扫描组件的录入域上，设置焦点到已有文本的最后
function QRInputFocus(){
	//定位焦点到扫描组件的录入域上
	document.all.piccode.focus();
	//设置焦点到已有文本的最后
	var e=document.all.piccode;
	var r =e.createTextRange();
	r.moveStart('character',e.value.length);
	r.collapse(true);
	r.select();
}
	 //扫描枪扫入数据后自动触发键盘事件
	function document.onkeydown(){
       //IE7.0以下触发的是回车
       if(event.keyCode==13){
          scanPic();
         return false;
       }

}
//调用动态库，识别图片，把识别出来的内容显示到页面上
function scanPic(){
  //alert(">>>扫描完毕开始解析！");
  var istr=document.forms[0].piccode.value;
  var obj=new ActiveXObject("hyQRBarCode.QRCode");
	//alert("obj="+obj);
	var transObj=obj.DeBarCodeString(istr);
	//alert("transObj="+transObj);
	//判定是否空业务条形码
	var transObjArray=transObj.split("^");
	if(transObjArray.length==1&&transObj=="<%=com.creationstar.bjtax.qsgl.util.Constants.QRCODE_DEFAULT_NULL%>"){
		alert("扫描条形码内容为空，请手工录入申报表！");
		return false;
	}

	//检查二维码明文是否正确；
	//alert("开始检查二维码头数据的格式");
	QRCodeCheckResult=checkQRCodeHeader(transObj);
	//alert("QRCodeCheckResult="+QRCodeCheckResult);
	if(QRCodeCheckResult=="1"){
		alert("扫描图片失败，请重新扫描或手工录入信息！");
        fuwei();
		QRInputFocus();
		return false;
	}else if(QRCodeCheckResult=="2"){//如果明文不为"object_"开头则表示条码图片扫描不全，提示继续扫描
		alert("请扫描全部二维条形码图片，否则无法解码！");
        fuwei();
		QRInputFocus();
		return false;
	}else if(QRCodeCheckResult=="3"){
      //alert(transObj);
      translate(transObj);//新房
      translate_xf(transObj);//设置为新房
		//translate_xinfang(transObj);//新房
      document.forms[0].smbs.value='1';
	}else if(QRCodeCheckResult=="4"){
      //alert(transObj);
		translate(transObj);//2手房
        translate_esf(transObj);//设置为二手房
        document.forms[0].smbs.value='1';
	}
	//清空录入域并重新置焦点
	document.all.piccode.value="";
	document.all.piccode.focus();
	return false;
}


//将明文字符串解析后填入页面各域
function translate(transObj){
  var retstr=transObj.split("^");
//   alert(retstr[19]);
  var mfxx = retstr[19].split("~");
    if(mfxx[1] == "2"){
        //纳税人名称(2009.2.9修改为将纳税人名称输入到隐藏域----yangxiao)
        document.forms[0].smnsrmc.value=mfxx[0];

  		for(i=0;i<document.getElementsByName("nsrmc");i++){
  			document.getElementsByName("nsrmc")[i].value=mfxx[0];
  		}
  		var ele_nsrmc_show=document.getElementById('nsrmc_show');
  		new_text=document.createTextNode(mfxx[0]);
  		ele_nsrmc_show.appendChild(new_text);

  		//证件号码(2009.2.9修改为不写入，需要手动输入----yangxiao)
  		//document.forms[0].jsjdm.value=mfxx[3];
  		//联系人（没有）

  		//联系人电话
  		document.forms[0].lxdh.value=mfxx[5];
    }else{
      alert("买方信息为个人，请重新扫描二维条形码图片，或者二维条形码图片错误！");
      doSubmitForm('Fuwei');
      return false;
    }

  //合同编号
  document.forms[0].htbh.value=retstr[2];
  document.forms[0].htbh.readOnly = true;
  document.forms[0].htbh.style.color="#ADADAD";
  //合约签订时间
  document.forms[0].time.value=formatDateStr(retstr[3]);
  //土地、房屋座落地址
  document.forms[0].address.value=retstr[5];
  //土地、房屋权属转移类型 直接默认为“房屋买卖”(03)
  //document.forms[0].divertType.value=retstr[6];
  document.forms[0].divertType.value="03";
  //房屋建筑面积
  document.forms[0].area.value=delFuhao(retstr[10]);
  //房屋类别
//  document.forms[0].tenementType.value=fwytJwToDs(retstr[12]);
  //成交价格
  document.forms[0].rmbPrice.value=delFuhao(retstr[13]);
}

//设置为二手房
function translate_esf(transObj){
  //是否为二手房  01:是 00:否
  document.forms[0].sfesf.value="01";
}
//设置为新房
function translate_xf(transObj){
  //是否为二手房  01:是 00:否
  document.forms[0].sfesf.value="00";
}
</script>
<!--add by yangxiao 20081206 end-->

<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js"type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>
<BODY bgColor=#ffffff leftMargin=0 onload="initPage();MM_preloadImages(
              '<%=static_file%>imagess/dayin2.jpg',
              '<%=static_file%>imagess/fanhui2.jpg',
              '<%=static_file%>imagess/tuichu2.jpg',
              '<%=static_file%>imagess/diyitiao2.jpg',
              '<%=static_file%>imagess/shangyitiao2.jpg',
              '<%=static_file%>imagess/zuimotiao2.jpg',
              '<%=static_file%>imagess/xinzeng2.jpg',
              '<%=static_file%>imagess/shanchu2.jpg')"
              topMargin=0 marginheight="0" marginwidth="0">

    <jsp:include page="/include/Header.jsp" flush="true">
    <jsp:param name="subtitle" value="契税申报>契税申报非个人录入表"/>
    <jsp:param name="helpURL" value=""/>
    </jsp:include>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=770>
  <TBODY>
  <TR>
    <td vAlign=top>
      <table align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <td class=1-td1>北京市地方税务局契税申报表-非个人</td></TR>
        <TR>
          <td class=1-td2 vAlign=top>
        <html:form action="/qssb/addSbFgr.do">
          <bean:define id="sbForm" name="sbFgrForm" type="com.creationstar.bjtax.qsgl.VisionLogic.form.SbFgrForm"/>
          <html:hidden property="operationType" value=""/>
          <html:hidden property="subAction" value=""/>
          <html:hidden property="person" value="false"/>
          <html:hidden property="alert" />
          <html:hidden property="alertMessage" />
          <html:hidden property="xtdqsj"/>
          <html:hidden property="bl"/>
          <!--add by yangxiao 20081206 start-->
          <html:hidden property="smnsrmc"/>
          <html:hidden property="smbs"/>
		  <html:hidden property="sfesf"/>
          <div id="caijiyu" style="display: block;"><BR>采集域:<input type="text" name="piccode" id="piccode" size="70"  maxlength="5000">
          <IMG
          alt=保存
          height=22
          id=shengcheng
          name="btnShengcheng"
          onclick="doSubmitForm('Shengcheng');"
          onmouseout=MM_swapImgRestore()
          onmouseover="MM_swapImage('shengcheng','','<%=static_file%>images/shengcheng2.jpg',1)"
          src="<%=static_file%>images/shengcheng1.jpg"
          style="CURSOR: hand"
          width=79>
          <IMG
          alt=复位
          height=22
          id=fuwei
          name="btnFuwei"
          onclick="doSubmitForm('Fuwei');"
          onmouseout=MM_swapImgRestore()
          onmouseover="MM_swapImage('fuwei','','<%=static_file%>images/fuwei2.jpg',1)"
          src="<%=static_file%>images/fuwei1.jpg"
          style="CURSOR: hand"
          width=79>
          </div>
          <table style="display: none;">
            <tr>
              <TD>
                <DIV>
                  <html:text name="sbFgrForm" property="time"/>
                </DIV>
              </TD>
              <TD>
                <DIV>
                  <html:text name="sbFgrForm" property="address"/>
                </DIV>
              </TD>
              <TD>
                <DIV>
                  <html:text name="sbFgrForm" property="divertType"/>
                </DIV>
              </TD>
              <TD>
                <DIV>
                  <html:text name="sbFgrForm" property="area"/>
                </DIV>
              </TD>
              <TD>
                <DIV align=left>
                  <html:text name="sbFgrForm" property="tenementType"/>
                </DIV>
              </TD>
              <TD>
                <DIV align=left>
                  <html:text name="sbFgrForm" property="rmbPrice"/>
                </DIV>
              </TD>
            </tr>
          </table>
          <!--add by yangxiao 20081206 end-->
          <table border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>
			  <BR>

                <TR>

			 <logic:equal name="sbFgrForm" property="bl" value="false">
                            <html:hidden name="sbFgrForm" property="sbbh"/>
			  	<td class=2-td2-t-left width="11%"><DIV align=right>纳税申报表号</DIV></td>

			  	<td class=2-td2-t-center width="47%" colspan=3><DIV align=left><bean:write name="sbFgrForm" property="sbbh" /> &nbsp </DIV></td></td>
				<html:hidden property="sbrq"/>
			</logic:equal>

			<logic:equal name="sbFgrForm" property="bl" value="true">
									    <TD class=2-td2-t-left width="12%">纳税申报表号</TD>
                                        <html:hidden name="sbFgrForm" property="sbbh"/>
                                        <TD class=2-td2-t-center width="43%">
										<DIV align=left>&nbsp<bean:write name="sbFgrForm" property="sbbh" /> </DIV></TD>
                                        <TD class=2-td2-t-left width="22%">申报日期(yyyymmdd)</TD>
                                        <TD class=2-td2-t-center width="23%"><DIV align=left>
										<html:text name="sbFgrForm" property="sbrq" />&nbsp<FONT color=red>*</FONT> </DIV>
			</logic:equal>

			  </TR>
			  <TR>
			  	<td class=2-td2-left>缴款方式</td>
			  	<td class=2-td2-left> <DIV align=left>
                                  <bean:define id="data" name="sbFgrForm" property="jkfsList"  />
                                  <html:select property="jkfsdm" >
                                    <html:options collection="data" labelProperty="jsfsmc" property="jsfsdm" />
                                  </html:select>
                                  <html:hidden property="jkfsmc"/>
				<td class=2-td2-left>房屋土地管理部门受理号</td>
				<td class=2-td2-left><DIV align=left><html:text property="fcjslh" maxlength="30"/></DIV></td>
            </TR>
            <TR>
                                            <!--20081125 modify by fujx ,增加建委业务编号字段开始-->
                                             <TD class=2-td2-left>建委业务编号</TD>
                                          <TD class=2-td2-left>
                                            <DIV align=left><html:text name="sbFgrForm" property="jwywbh" maxlength="30"/>  </DIV></TD>
                                            <!--20081125 modify by fujx ,增加建委业务编号字段结束-->
                                            <!--20081125 modify by fujx ,增加合同编号字段开始-->
                                             <TD class=2-td2-left>合同编号</TD>
                                          <TD class=2-td2-center>
                                            <DIV align=left><html:text name="sbFgrForm" property="htbh" maxlength="30"/>  </DIV></TD>
                                            <!--20081125 modify by fujx ,增加合同编号字段结束-->

                                          </TR>
			  </TBODY>
			  </TABLE>
            <table border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>
              <TR>
                <td class=2-td2-t-left width="18%" rowspan = "4">
                  <DIV align=right>非个人填</DIV>
                   <DIV align=right>写部分</DIV>
                  <td class=2-td2-t-left width="15%">
                   <DIV align=right>计算机代码&nbsp;
                   </DIV>
                </td>
                <DIV align=right>&nbsp; </DIV>
                  <td class=2-td2-t-left width="35%">
                    <DIV align=left><html:text property="jsjdm" maxlength="8"/><FONT color=red>*</FONT>
                    <span id="getDJimage1" style="visibility:visible"> <input type="image"  name="imgGetNsrxx" value="获取登记信息" alt="获取登记信息"
                     onClick="javascript:return doSubmitForm('GetNsrxx');"
                     onMouseOver="MM_swapImage('b-hqdjxx1','','<%=static_file%>images/b-hqdjxx2.jpg',1)"
                     onMouseOut="MM_swapImgRestore()"src="<%=static_file%>images/b-hqdjxx1.jpg"
                     width="79" height="22" id="b-hqdjxx1"></span></DIV></td>
                <td class=2-td2-t-left width="10%">
                  <DIV align=right>纳税人类型&nbsp; </DIV></td>
                <td colspan="2"  class=2-td2-t-center width="20%">
                  <DIV align=left>
                    <bean:define id="data1" name="sbFgrForm" property="nsrlxList"  />
                    <html:select property="nsrlxdm" >
                      <html:options collection="data1" labelProperty="nsrlxmc" property="nsrlxdm" />
                    </html:select></DIV></td>
                    <html:hidden property="nsrlxmc" />
                  </TR>
             <TR>
                <td  class=2-td2-left >
                  <DIV align=right>纳税人名称&nbsp;</DIV></td>
                <td colspan="4"  class=2-td2-center width="33%" style="word-break:break-all">
                   <DIV align=left id="nsrmc_show">
                   <bean:write name="sbFgrForm" property="nsrmc"/>
                   <html:hidden property="nsrmc" /> </DIV></td>
                  </TR>
                  <TR>
                 <td class=2-td2-left ;>
                   <DIV align=right>开户银行&nbsp; </DIV>
                </td>
                <td class=2-td2-center  colspan="4" >
                  <DIV align=left>
                    <bean:define id="yhList" name="sbFgrForm" property="yhList"  />
                    <html:select property="yhdm">
                      <html:options collection="yhList" labelProperty="khyhmc" property="yhdm" />
                    </html:select></DIV></td>
                    <html:hidden property="yhmc" />
                  </TR>

                  <TR>
                 <td class=2-td2-left >
                   <DIV align=right>联系人姓名&nbsp; </DIV>
                </td>

                 <td class=2-td2-left >
                   <DIV align=left><html:text property="lxrxm" maxlength="50"/><FONT color=red>*</FONT> </DIV></td>
                <td   class=2-td2-left width="19%">
                  <DIV align=right>联系电话&nbsp;</DIV></td>
                <td colspan="2"  class=2-td2-center width="33%">
                   <DIV align=left><html:text property="lxdh" maxlength="20"/><FONT ></FONT> </DIV></td>
                  </TR>
               <TR>
                <td class=2-td2-left  rowspan = "2">
                  <DIV align=right>税务机关</DIV>
                  <DIV align=right>审核减免税</DIV>
                  <TD class=2-td2-left width="15%";>
                    <DIV align=right>减免税文书字号&nbsp; </DIV>
                  </TD>
                  <TD colspan="4" class=2-td2-center width="25%">
                    <DIV align=left><html:text name="sbFgrForm" property="hdtzszh" size="40" maxlength="40"/>
                    <IMG name="query"
				          onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
				          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/chaxun1.jpg"
				          onclick = "doSubmitForm('GetJmsje');"
				          width="79" height="22" id="chaxun1" alt="查询" style="cursor:hand"></DIV>
					</TD>
                </TR>
                <TR>
                  <td class=2-td2-left >
                   <DIV align=right>减免税金额&nbsp; </DIV>
                </td>
                  <TD colspan="4" class=2-td2-center width="30%">
                    <DIV align=left><html:text name="sbFgrForm" property="jmsje" maxlength="15"/> </DIV>
                  </TD>
                  <html:hidden property="jmlydm"/>
                  </TR>
                 <TR>
                 <td class=2-td2-left >
                   <DIV align=right>备注&nbsp; </DIV>
                </td>
                <td colspan="5" class=2-td2-center width="84">
                  <DIV align=left><html:textarea property="bz"  cols="45" rows="5"
                  /></DIV></td>
                </TR>
<!-- 显示房产信息-->

    <html:hidden property="fwjbxxAdded"/>
<logic:equal name="sbFgrForm" property="fwjbxxAdded" value="true">
<bean:define id="fwtdVo" name="sbFgrForm" property="voTufwxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"/>
  <tr>
    <TD class=2-td2-left width="8%" rowspan = "8">
      <DIV align=right>	土地房屋</DIV>
      <DIV align=right>权属转移</DIV></TD>
      <TD class=2-td2-left width="15%">
        <DIV align=right>房地产项目名称&nbsp;</DIV></TD>
        <TD colspan="3" class=2-td2-left style="word-break:break-all">
          <DIV align=left><bean:write name="fwtdVo" property="fdcxmmc" />
          </DIV>
      </TD>
      <td width="13%" class=2-td2-center>
          <div align="left">
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
          <DIV align=left><%=DataConvert.TimeStamp2String(fwtdVo.getHtqdsj())%> </DIV>
      </TD>
      <TD class=2-td2-left width="19%">
          <DIV align=right>购房原因&nbsp;</DIV>
      </TD>
      <TD colspan="2"  class=2-td2-center width="33%">
          <DIV align=left>
              <bean:write  name="fwtdVo" property="flmc"/>&nbsp
          </DIV>
      </TD>
  </TR>
  <TR>
      <TD class=2-td2-left width="15%";>
          <DIV align=right>土地、房屋权属转移类型&nbsp; </DIV>
      </TD>
      <TD class=2-td2-left width="19%">
          <DIV align=left>
              <bean:write  name="fwtdVo" property="tdfwqszymc"/>
          </DIV>
      </TD>
      <html:hidden property="voTufwxx.tdfwqszylx"/>
      <TD class=2-td2-left width="19%">
          <DIV align=right>房屋类别&nbsp;</DIV>
      </TD>
      <TD colspan="2"  class=2-td2-center width="33%">
          <DIV align=left>
              <bean:write  name="fwtdVo" property="fwlxmc"/>&nbsp;
          </DIV></TD>
      </TR>
      <TR>
          <TD  class=2-td2-left width="25%";>
              <DIV align=right>土地、房屋座落地址&nbsp;
              </DIV>
          </TD>
          <TD colspan="4"  class=2-td2-center width="15%" style="word-break:break-all">
              <DIV align=left><bean:write name="fwtdVo" property="tdfwzldz" />
              </DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left width="25%";>
              <DIV align=right>土地、房屋权属转移面积&nbsp;
              </DIV>
          </TD>
          <TD  class=2-td2-left width="15%">
              <DIV align=left>土地：<%=DataConvert.BigDecimal2String(fwtdVo.getTdfwqszymj(),3)%> ㎡
              </DIV>
          </TD>
          <TD class=2-td2-left width="17%">
              <DIV align=right>房屋建筑面积&nbsp;
              </DIV>
          </TD>
          <TD colspan="2"  class=2-td2-center width="32%">
              <DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getFwjzmj(),3)%> ㎡
              </DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left width="15%";>
              <DIV align=right>容积率&nbsp;
              </DIV>
          </TD>
          <TD class=2-td2-left width="19%">
              <DIV align=left>
                  <%if(fwtdVo.getRjl().equals("00"))
                  {
                  %>
                  1.0以下
                  <%
                  }
                  if(fwtdVo.getRjl().equals("01"))
                  {
                  %>
                  1.0以上含1.0
                  <%
                  }
                  %>&nbsp;
              </DIV>
          </TD>
          <TD class=2-td2-left width="19%">
             <!--修改土地级次为房屋所属区域-->
              <DIV align=right>所属区域&nbsp;
              </DIV>
          </TD>
          <TD colspan="2"  class=2-td2-center width="33%">
              <DIV align=left>
                  <%if(fwtdVo.getTdjc().equals("00"))
                  {
                  %>
                  &nbsp;
                  <%
                  }
                  if(fwtdVo.getTdjc().equals("01"))
                  {
                  %>
                  三环以内&nbsp;
                  <%
                  }
                  if(fwtdVo.getTdjc().equals("02"))
                  {
                  %>
                  三环至四环之间&nbsp;
                  <%
                  }
                  %>
                  <%if(fwtdVo.getTdjc().equals("03"))
                  {
                  %>
                  四环至五环之间&nbsp;
                  <%
                  }
                  if(fwtdVo.getTdjc().equals("04"))
                  {
                  %>
                  五环以外&nbsp;
                  <%
                  }
                  if(fwtdVo.getTdjc().equals("11"))
              	{	
              	%>
                 	四环内北部地区&nbsp;
                  <% 
              	}
                  if(fwtdVo.getTdjc().equals("12"))
              	{	
              	%>
                 	四环内南部地区&nbsp;
                  <% 
              	}
                  if(fwtdVo.getTdjc().equals("13"))
              	{	
              	%>
                 	四环至五环北部地区&nbsp;
                  <% 
              	}
                   if(fwtdVo.getTdjc().equals("14"))
              	{	
              	%>
                 	四环至五环南部地区&nbsp;
                  <% 
              	}
                  if(fwtdVo.getTdjc().equals("15"))
              	{	
              	%>
                 	五环至六环北部地区&nbsp;
                  <% 
              	}
                  if(fwtdVo.getTdjc().equals("16"))
              	{	
              	%>
                 	五环至六环南部地区&nbsp;
                  <% 
              	}
                  if(fwtdVo.getTdjc().equals("17"))
              	{	
              	%>
                 	六环外地区&nbsp;
                <% 
				}
                //由于2014年10月北京普通住房标准调整，故作此需改为新标准：每平米价格上限、每套房屋价格、所在区域三方面调整 ，
  				//此处只修改所在区域显示。modified by gaoyh to 20141020
			    if(fwtdVo.getTdjc().equals("21"))
				{	
				%>
			   	5环内&nbsp;
			    <% 
				}
			    if(fwtdVo.getTdjc().equals("22"))
				{	
				%>
			   	5-6环&nbsp;
			    <% 
				}
			    if(fwtdVo.getTdjc().equals("23"))
				{	
				%>
			   	6环外&nbsp;
			    <% 
				}
			    %>

                  &nbsp;
              </DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left width="8%" rowspan = "2">
              <DIV align=right>成交价格（评估价格）
              </DIV>
          </td>
          <TD  class=2-td2-left width="5%">
              <DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getCjjgrmb())%> 元(人民币)
              </DIV>
          </TD>
          <TD class=2-td2-left width="17%">
              <DIV align=right>税务机关核定价格&nbsp;
              </DIV>
          </TD>
          <TD  colspan="2"  class=2-td2-center width="32%">
              <DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getPgjgrmb())%> 元(人民币)
              </DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left width="15%";>
              <DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getCjjgwb())%> 元(外币)
              </DIV>
          </TD>
          <TD class=2-td2-left width="25%">
              <DIV align=right>
                  币种：<bean:write name="fwtdVo" property="bzmc" />
              </DIV>
              <DIV align=right>汇率:&nbsp;<%=DataConvert.BigDecimal2String(fwtdVo.getHldm(),5)%>
              </DIV>
          </TD>
          <TD class=2-td2-center width="17%" colspan="2">
              <DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getZhjgrmb())%>
              </DIV>
          </TD>
      </tr>
  </logic:equal>
  <logic:iterate id="cqdata" indexId="index" length="length" name="sbFgrForm" property="cqList" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq">
      <TR>
          <TD class=2-td2-left  rowspan = "4">
              <DIV align=right>	拆迁
              </DIV>
              <DIV align=right>情况
              </DIV>
          </TD>
          <TD class=2-td2-center width="25%" style="word-break:break-all">
              <DIV align=right>被拆迁房屋坐落地址&nbsp;
              </DIV>
          </TD>
          <TD colspan="4" class=2-td2-center >
              <DIV align=left>
                  &nbsp;<bean:write name = "cqdata" property = "zldz"/>
              </DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left >
              <DIV align=right>拆迁协议号码&nbsp; </DIV>
          </TD>
          <TD colspan="4" class=2-td2-center >
              <DIV align=left>
                  &nbsp;<bean:write name = "cqdata" property = "cqxyh"/>
              </DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left >
              <DIV align=right> 拆迁补偿额&nbsp; </DIV>
          </TD>
          <TD  class=2-td2-center colspan=4>
              <DIV align=left>
                  &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getCqbce())%> 元(人民币)
              </DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left >
              <DIV align=right>本次使用补偿额&nbsp; </DIV>
          </TD>
          <TD class=2-td2-left >
              <DIV align=left>
                  &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getBcsybce())%>  元(人民币)
              </DIV>
          </TD>
          <TD class=2-td2-left >
              <DIV align=right>拆迁补偿剩余额&nbsp;
              </DIV>
          </TD>
          <TD colspan="2"  class=2-td2-center width="32%">
              <DIV align=left>
                  &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getCqbcsye())%> 元(人民币)
              </DIV>
          </TD>
      </TR>
  </logic:iterate>
  <logic:iterate id="gydata" indexId="index" length="length" name="sbFgrForm" property="gyList"
      type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf">
      <TR>
          <TD class=2-td2-left width="8%" rowspan = "5">
              <DIV align=right>	已购公有住房
              </DIV>
              <DIV align=right>上市出售情况
              </DIV><br>
          </TD>
          <TD   class=2-td2-left width="25%">
              <DIV  align=right>出售合同号码&nbsp;</DIV>
          </TD>
          <TD colspan="4" class=2-td2-center width="15%">
              <DIV align=left><bean:write name="gydata" property="yggyzfqszsh" /></DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left width="15%";>
              <DIV align=right>座落地址&nbsp; </DIV>
          </TD>
          <TD colspan="4" class=2-td2-center width="25%" style="word-break:break-all">
              <DIV align=left><bean:write name="gydata" property="zldz" />&nbsp;</DIV>
          </TD>
      </TR>
      <TR>
          <TD  class=2-td2-left width="15%";>
              出售合同（契约）签订时间&nbsp;</DIV>
          </TD>
          <TD colspan="4" class=2-td2-center width="25%">
              <DIV align=left><%=DataConvert.TimeStamp2String(gydata.getQdsj())%>  </DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left width="15%";>
              <DIV align=right> 建筑面积&nbsp; </DIV>
          </TD>
          <TD class=2-td2-left width="25%">
              <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getJzmj(),3)%> ㎡ </DIV>
          </TD>
          <TD class=2-td2-left width="17%">
              <DIV align=right>成交价格&nbsp;</DIV>
          </TD>
          <TD colspan="2"  class=2-td2-center width="32%">
              <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getCjjg())%> 元(人民币)</DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left width="15%">
              <DIV align=right>本次抵扣额&nbsp;</DIV>
          </TD>
          <TD  class=2-td2-left width="32%">
              <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getBcdke())%> 元(人民币)</DIV>
          </TD>
          <TD class=2-td2-left width="17%">
              <DIV align=right>剩余额&nbsp;</DIV>
          </TD>
          <TD colspan="2"  class=2-td2-center width="32%">
              <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getSye())%> 元(人民币)</DIV>
          </TD>
      </TR>
  </logic:iterate>
  <!-- 显示房产交换信息-->
  <html:hidden property="fwjhAdded"/>
  <logic:equal name="sbFgrForm" property="fwjhAdded" value="true">
      <bean:define id="fwjhBo" name="sbFgrForm" property="fwjhxxBo" type="com.creationstar.bjtax.qsgl.model.bo.FwjhxxBo"/>

      <logic:equal name="fwjhBo" property="jhperson"  value="1">
          <bean:define id="fgrxx" name="sbFgrForm" property="fwjhxxBo.fgrxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx"/>
      </logic:equal>
      <bean:define id="fwtdxx" name="sbFgrForm" property="fwjhxxBo.tufwxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"/>
      <!--TR>
      <TD class=2-td2-left width="12%">对方缴款方式</TD>
      <TD class=2-td2-left width="34%"> <DIV align=left>
          <bean:write name="fwjhBo" property="jkfsmc" />
      </td>
      <TD class=2-td2-left width="22%">对方房屋土地管理部门受理号</TD>
      <TD colspan="3" class=2-td2-center width="23%"><DIV align=left><bean:write name="fwjhBo" property="fcjslh" />&nbsp;</DIV>
      </TD>
      </TR-->
      <!--交换个人信息显示-->
      <logic:equal name="fwjhBo" property="jhperson"  value="0">

  <TR>
    <TD class=2-td2-left width="70%"  colspan="4"><DIV align=left>对方个人填写部分</DIV></TD>
    <TD class=2-td2-center width="30%" colspan="2" >  &nbsp; </TD>

</TR>

        <TR>
    <TD  colspan="6" valign="top" width="100%">

<table border="0" cellpadding="0" cellspacing="0" class="table-99" width="100%">
  <tr>
	<td width="20%" class="2-td2-left">
	纳税人名称
	</td>
    <td width="20%" class="2-td2-left">联系电话</td>
    <td width="20%" class="2-td2-left">身份证件类型</td>
    <td width="20%" class="2-td2-left">身份证件号码</td>
    <td width="10%" class="2-td2-left">国籍</td>
    <td width="10%" class="2-td2-center">产权人类型</td>
  </tr>

 <logic:iterate id="jhnsr" indexId="index" length="length" name="fwjhBo" property="nsrList">
  <tr>
	<td width="20%" class="2-td2-left"><bean:write name="jhnsr" property="nsrmc" /></td>
    <td width="20%" class="2-td2-left"><bean:write name="jhnsr" property="lxdh" />&nbsp;</td>
    <td width="20%" class="2-td2-left"><bean:write name="jhnsr" property="sfzjlxmc" /></td>
    <td width="20%" class="2-td2-left"><bean:write name="jhnsr" property="sfzjhm" /></td>
    <td width="10%" class="2-td2-left"><bean:write name="jhnsr" property="gjmc" /></td>
    <td width="10%" class="2-td2-center">
<logic:equal name="jhnsr" property="cqrlx" value="1">
    共有产权人
</logic:equal>
<logic:equal name="jhnsr" property="cqrlx" value="0">
    主产权人
</logic:equal>
    </td>
  </tr>
  </logic:iterate>

</table>
</TD>
</TR>




       </logic:equal>
      <!--交换非个人信息显示-->
      <logic:equal name="fwjhBo" property="jhperson"  value="1">
          <TR>
              <TD class=2-td2-left width="8%" rowspan = "4">
                  <DIV align=right>对方非个人</DIV>
                  <DIV align=right>填写部分</DIV>
              </TD>
              <TD class=2-td2-left width="15%">
                  <DIV align=right>计算机代码&nbsp; </DIV>
              </TD>
              <DIV align=right>&nbsp; </DIV>
              <TD class=2-td2-left width="25%">
                  <DIV align=left> <bean:write name="fgrxx" property="jsjdm" />&nbsp</DIV>
              </TD>
              <TD class=2-td2-left width="19%">
                  <DIV align=right>&nbsp; </DIV>
              </TD>
              <TD colspan="2"  class=2-td2-center width="33%">
                  <DIV align=left> &nbsp;</DIV>
              </TD>
          </TR>
          <TR>
              <TD class=2-td2-left width="15%";>
                  <DIV align=right>纳税人名称&nbsp; </DIV>
              </TD>
              <TD class=2-td2-left width="25%" style="word-break:break-all">
                  <DIV align=left>
                      <bean:write name="fgrxx" property="nsrmc" />
                  </DIV>
              </TD>
              <TD class=2-td2-left width="19%">
                  <DIV align=right>纳税人类型&nbsp;</DIV>
              </TD>
              <TD colspan="2"  class=2-td2-center width="33%">
                  <DIV align=left><bean:write name="fgrxx" property="nsrlxmc" />&nbsp;</DIV>
              </TD>
          </TR>
          <TR>
              <TD class=2-td2-left width="15%";>
                  <DIV align=right>开户银行&nbsp; </DIV>
              </TD>
              <TD class=2-td2-left width="25%" >
                  <DIV align=left>
                      <bean:write name="fgrxx" property="khyhmc" />&nbsp;
                  </DIV>
              </TD>
              <TD class=2-td2-left width="19%">
                  <DIV align=right>银行账号&nbsp;</DIV>
              </TD>
              <TD colspan="2"  class=2-td2-center width="33%">
                  <DIV align=left><bean:write name="fgrxx" property="yhzh" />&nbsp;</DIV>
              </TD>
          </TR>
          <TR>
              <TD class=2-td2-left width="15%";>
                  <DIV align=right>联系人姓名&nbsp; </DIV>
              </TD>
              <TD class=2-td2-left width="25%" >
                  <DIV align=left>
                      <bean:write name="fgrxx" property="lxrxm" />&nbsp;
                  </DIV>
              </TD>
              <TD class=2-td2-left width="19%">
                  <DIV align=right>联系电话&nbsp;</DIV>
              </TD>
              <TD colspan="2"  class=2-td2-center width="33%">
                  <DIV align=left><bean:write name="fgrxx" property="lxdh" />&nbsp</DIV>
              </TD>
          </TR>
      </logic:equal>
      <TR>
          <TD class=2-td2-left width="8%" rowspan = "7">
              <DIV align=right>	交换土地房屋</DIV>
              <DIV align=right>权属转移</DIV>
          </TD>
          <TD class=2-td2-left width="15%">
              <DIV align=right>房地产项目名称&nbsp;</DIV>
          </TD>
          <TD colspan="4" class=2-td2-center width="15%" style="word-break:break-all">
              <DIV align=left><bean:write name="fwtdxx" property="fdcxmmc" /></DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left width="15%";>
              <DIV align=right>合约签订时间&nbsp; </DIV>
          </TD>
          <TD class=2-td2-left width="19%">
              <DIV align=left><%=DataConvert.TimeStamp2String(fwtdxx.getHtqdsj())%> </DIV>
          </TD>
          <TD class=2-td2-left width="19%">
              <DIV align=right>分类&nbsp;</DIV>
          </TD>
          <TD colspan="2"  class=2-td2-center width="33%">
              <DIV align=left>
                  <bean:write  name="fwtdxx" property="flmc"/>
              </DIV>
          </TD>
      </TR>
      <!--TR>
      <TD class=2-td2-left width="15%";>
          <DIV align=right>土地、房屋权属转移类型&nbsp; </DIV> </TD>
          <TD class=2-td2-left width="19%">
              <DIV align=left>
                  <bean:write name="fwtdxx" property="tdfwqszymc" />
              </DIV>
          </TD>
          <TD class=2-td2-left width="19%">
              <DIV align=right>房屋类别&nbsp;</DIV>
          </TD>
          <TD colspan="2"  class=2-td2-center width="33%">
              <DIV align=left>
                  <bean:write name="fwtdxx" property="fwlxmc" />
              </DIV>
          </TD>
      </TR-->
      <TR>
          <TD  class=2-td2-left width="25%";>
              <DIV align=right>土地、房屋座落地址&nbsp; </DIV>
          </TD>
          <TD colspan="4"  class=2-td2-center width="15%" style="word-break:break-all">
              <bean:write name="fwtdxx" property="tdfwzldz" />
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left width="25%";>
              <DIV align=right>土地、房屋权属转移面积&nbsp; </DIV>
          </TD>
          <TD  class=2-td2-left width="15%">
              <DIV align=left>土地：
                  <%=DataConvert.BigDecimal2String(fwtdxx.getTdfwqszymj(),3)%>
                  ㎡
              </DIV>
          </TD>
          <TD class=2-td2-left width="17%">
              <DIV align=right>房屋建筑面积&nbsp;</DIV>
          </TD>
          <TD colspan="2"  class=2-td2-center width="32%">
              <DIV align=left>
                  <%=DataConvert.BigDecimal2String(fwtdxx.getFwjzmj(),3)%>
                  ㎡
              </DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left width="15%";>
              <DIV align=right>容积率&nbsp; </DIV>
          </TD>
          <TD class=2-td2-left width="19%">
              <DIV align=left>
                  <%if(fwtdxx.getRjl().equals("00"))
                  {
                  %>
                  1.0以下
                  <%
                  }
                  if(fwtdxx.getRjl().equals("01"))
                  {
                  %>
                  1.0以上含1.0
                  <%
                  }
                  %>&nbsp;
              </DIV>
          </TD>
          <TD class=2-td2-left width="19%">
              <!--修改土地级次为房屋所属区域-->
              <DIV align=right>所属区域&nbsp;</DIV>
          </TD>
          <TD colspan="2"  class=2-td2-center width="33%">
              <DIV align=left>
                   <%if(fwtdxx.getTdjc().equals("00"))
                  {
                  %>
                  &nbsp;
                  <%
                  }
                  if(fwtdxx.getTdjc().equals("01"))
                  {
                  %>
                  三环以内&nbsp;
                  <%
                  }
                  if(fwtdxx.getTdjc().equals("02"))
                  {
                  %>
                  三环至四环之间&nbsp;
                  <%
                  }
                  %>
                  <%if(fwtdxx.getTdjc().equals("03"))
                  {
                  %>
                  四环至五环之间&nbsp;
                  <%
                  }
                  if(fwtdxx.getTdjc().equals("04"))
                  {
                  %>
                  五环以外&nbsp;
                  <%
                  }
                  if(fwtdxx.getTdjc().equals("11"))
				{	
				%>
			   	四环内北部地区&nbsp;
			    <% 
				}
			    if(fwtdxx.getTdjc().equals("12"))
				{	
				%>
			   	四环内南部地区&nbsp;
			    <% 
				}
			    if(fwtdxx.getTdjc().equals("13"))
				{	
				%>
			   	四环至五环北部地区&nbsp;
			    <% 
				}
			     if(fwtdxx.getTdjc().equals("14"))
				{	
				%>
			   	四环至五环南部地区&nbsp;
			    <% 
				}
			    if(fwtdxx.getTdjc().equals("15"))
				{	
				%>
			   	五环至六环北部地区&nbsp;
			    <% 
				}
			    if(fwtdxx.getTdjc().equals("16"))
				{	
				%>
			   	五环至六环南部地区&nbsp;
			    <% 
				}
			    if(fwtdxx.getTdjc().equals("17"))
				{	
				%>
			   	六环外地区&nbsp;
			    <% 
				}
			    //由于2014年10月北京普通住房标准调整，故作此需改为新标准：每平米价格上限、每套房屋价格、所在区域三方面调整 ，
				//此处只修改所在区域显示。modified by gaoyh to 20141020
			    if(fwtdxx.getTdjc().equals("21"))
				{	
				%>
			   	5环内&nbsp;
			    <% 
				}
			    if(fwtdxx.getTdjc().equals("22"))
				{	
				%>
			   	5-6环&nbsp;
			    <% 
				}
			    if(fwtdxx.getTdjc().equals("23"))
				{	
				%>
			   	6环外&nbsp;
			    <% 
				}
			    %>

                  &nbsp;
              </DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left width="8%" rowspan = "2">
              <DIV align=right>成交价格（评估价格）</DIV>
          </td>
          <TD  class=2-td2-left width="5%">
              <DIV align=left>
                  <%=DataConvert.BigDecimal2String(fwtdxx.getCjjgrmb())%>
                  元(人民币)
              </DIV>
          </TD>
          <TD class=2-td2-left >
              <DIV align=right>税务机关核定价格&nbsp; </DIV>
          </TD>
          <TD  colspan="2"  class=2-td2-center width="32%">
              <DIV align=left>
                  <%=DataConvert.BigDecimal2String(fwtdxx.getPgjgrmb())%>
                  元(人民币)
              </DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left >
              <DIV align=left>
                  <%=DataConvert.BigDecimal2String(fwtdxx.getCjjgwb())%>
                  元(外币)
              </DIV>
          </TD>
          <TD class=2-td2-left >
              <DIV align=left>
                  币种：<bean:write name="fwtdxx" property="bzmc" />
              </DIV>
              <DIV align=left>
                  汇率:&nbsp;<%=DataConvert.BigDecimal2String(fwtdxx.getHldm(),5)%>
              </DIV>
          </TD>
          <TD class=2-td2-center  colspan="2" >
              <DIV align=left>
                  <%=DataConvert.BigDecimal2String(fwtdxx.getZhjgrmb())%>
                  折合元(人民币)
              </DIV>
          </TD>
      </TR>
  </logic:equal>
</TBODY>
</TABLE>
<BR>

            <DIV align=center>

			<%
			  if ((sbForm.getSbbh() != null) && (!sbForm.getSbbh().equals("")))
			  {

              if (!sbForm.isFwjbxxAdded())
              {
			%>
            <IMG alt=录入房屋基础信息 id=Fwjbxx name=Submit51
            onclick="doSubmitForm('Fwjbxx')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('Fwjbxx','','<%=static_file%>images/q_lrtdfwjbxx2.jpg',1)"
            src="<%=static_file%>images/q_lrtdfwjbxx1.jpg" style="CURSOR: hand">

            <%
              }

              if (sbForm.isFwjbxxAdded())

              {
            %>

              <!--modified by zhaobo 20041218
            <img alt=录入拆迁情况 id=Cqxx name=Submit53
            onclick="doSubmitForm('Cqxx')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('Cqxx','','<%=static_file%>images/q_lrcq2.jpg',1)"
            src="<%=static_file%>images/q_lrcq1.jpg" style="CURSOR: hand">
							//end modified

             <img alt=录入已购公有住房上市情况 id=Gyzf name=Submit43
            onclick="doSubmitForm('Gyzf')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('Gyzf','','<%=static_file%>images/q_lryggyzfssqk2.jpg',1)"
            src="<%=static_file%>images/q_lryggyzfssqk1.jpg" style="CURSOR: hand"-->

            <%
              if (sbForm.getVoTufwxx().getTdfwqszylx().equals("05")) //如果是房屋交换
              {
				  if (!sbForm.isFwjhAdded())
				  {
            %>
            <IMG alt=录入房屋交换信息  id=Fwjhxx name=Submit33
            onclick="doSubmitForm('Fwjhxx');" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('Fwjhxx','','<%=static_file%>images/q_lrfwjhxx2.jpg',1)"
            src="<%=static_file%>images/q_lrfwjhxx1.jpg" style="CURSOR: hand" >
			<%
			      }
			  }//end if (sbForm.getVoTufwxx().getTdfwqszylx().equals("05"))
			%>
             <img alt=打印   id=Print name=Submit33
            onclick="doPrintSbb()" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('Print','','<%=static_file%>images/q_dysbb2.jpg',1)"
            src="<%=static_file%>images/q_dysqb1.jpg" style="CURSOR: hand">
              <br><br>

            <%
             } //end if (sbForm.isFwjbxxAdded())
             }
             else  //申报表号为空
             {
            %>
            <IMG alt=保存 height=22 id=baocun name=Submit63
            onclick="doSubmitForm('Save');" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
            src="<%=static_file%>images/baocun1.jpg" style="CURSOR: hand" width=79>

            <%
            }
            %>

            <IMG alt=退出 height=22 id=tuichu name=tuichu
            onclick="doSubmitForm('Return')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
            src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand" width=79>
            </DIV><BR>
</html:form>




<script language="javascript" type='text/JavaScript'>
if (document.forms[0].sbbh.value == "")
{

	document.forms[0].jkfsdm.disabled = false;
	document.forms[0].fcjslh.disabled = false;
	document.forms[0].lxdh.disabled = false;
	document.forms[0].bz.disabled = false;
  document.forms[0].jsjdm.disabled = false;
  document.forms[0].lxdh.disabled = false;
  document.forms[0].lxrxm.disabled = false;
  document.forms[0].nsrlxdm.disabled = false;
	document.forms[0].hdtzszh.disabled = false;
	document.forms[0].jmsje.disabled = false;
	document.forms[0].sbrq.disabled = false;
    <!-- yangxiao 2008-12-06 start -->
    document.forms[0].jwywbh.disabled = false;
	document.forms[0].htbh.disabled = false;
    document.all.caijiyu.style.display = "block";
    <!-- yangxiao 2008-12-06 end -->
}
else
{

	document.forms[0].jkfsdm.disabled = true;
	document.forms[0].fcjslh.disabled = true;
	document.forms[0].lxdh.disabled = true;
	document.forms[0].bz.disabled = true;
  document.forms[0].jsjdm.disabled = true;
  document.forms[0].lxrxm.disabled = true;
  document.forms[0].nsrlxdm.disabled = true;
  document.forms[0].yhdm.disabled = true;
	document.forms[0].hdtzszh.disabled = true;
	document.forms[0].jmsje.disabled = true;
	document.forms[0].chaxun1.disabled = true;
document.forms[0].sbrq.disabled = true;
    getDJimage1.style.visibility = "hidden";
    <!-- yangxiao 2008-12-06 start -->
    document.forms[0].jwywbh.disabled = true;
	document.forms[0].htbh.disabled = true;
    document.all.caijiyu.style.display = "none";
    <!-- yangxiao 2008-12-06 end -->

}

if (document.forms[0].alert.value == "true")
{
    alert(document.forms[0].alertMessage.value);
    document.forms[0].alertMessage.value = "";
}

</script>


<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
