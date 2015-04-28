<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.ActionUtil"%>
<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.BzqsForm"%>
<%
	BzqsForm aForm = (BzqsForm)session.getAttribute("bzqsForm");

%>
<!--add by hazhengze 20081201 start-->
<%@ include file="/include/QRCodeHeader.jsp"%>
<!--add by hazhengze 20081201 end-->

<HTML><HEAD><TITLE>不征契税个人信息录入表</TITLE>
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
<META content="MSHTML 5.00.2919.6307" name=GENERATOR></HEAD>

<SCRIPT language=JavaScript>
function checkSubmit(operationType)
{

	document.forms[0].operationType.value=operationType;
    document.forms[0].yhbs.value=<%=Constants.ZB_YHBS_GR%>

	if(operationType=="Save" )
	{
		
	//add by hazhengze 20090108 start 
	//检查在房屋权属转移类型为房屋买卖或房屋交换时，必须选择房屋所属区域
	  if ((document.forms[0].tdfwqszylx.value == "03") || (document.forms[0].tdfwqszylx.value == "04")|| (document.forms[0].tdfwqszylx.value == "05")){
			if(document.forms[0].tdjc.value=="00"){
				alert("房屋交易必须选择房屋所在区域，请选择！");
				return false;
			}
	  }
	//add by hazhengze 20090108 end


       var checknsr=checkNsr();
       if (checknsr==false){
              return false;
       }

        if (document.forms[0].fdcxmmc.value == "")
  	{
  	   alert("房地产项目名称不能为空，请重新输入！ ");
  	   document.forms[0].fdcxmmc.focus();
  	   return false;
  	}
        if(document.forms[0].fl.value=="99"&&document.forms[0].beizhu.value == ""){
			 
			 alert("备注中不征理由不能为空，请重新输入！");
			 document.forms[0].beizhu.focus();
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
       //修改土地面积，房屋建筑面积小数位数约束为3位。
            //并且调用新的js方法
            //modify by fujx，20081201
	    if (!FN_QSCheckNumberDigit_BZQS(document.forms[0].tdfwqszymj.value,3,"土地面积"))
	    {
	      document.forms[0].tdfwqszymj.focus();
	      return false;
	    }
	    if (document.forms[0].fwjzmj.value !="")
	    {
            //修改土地面积，房屋建筑面积小数位数约束为3位。
            //并且调用新的js方法
            //modify by fujx，20081201
		    if (!FN_QSCheckNumberDigit_BZQS(document.forms[0].fwjzmj.value,3,"房屋建筑面积"))
		    {
		      document.forms[0].fwjzmj.focus();
		      return false;
		    }
	    }
    }
    else
    {
        //修改土地面积，房屋建筑面积小数位数约束为3位。
            //并且调用新的js方法
            //modify by fujx，20081201
    	if (!FN_QSCheckNumberDigit_BZQS(document.forms[0].fwjzmj.value,3,"房屋建筑面积"))
		    {
		      document.forms[0].fwjzmj.focus();
		      return false;
		    }
		  if (document.forms[0].tdfwqszymj.value !="")
	    {
            //修改土地面积，房屋建筑面积小数位数约束为3位。
            //并且调用新的js方法
            //modify by fujx，20081201
		    if (!FN_QSCheckNumberDigit_BZQS(document.forms[0].tdfwqszymj.value,3,"土地面积！"))
		    {
		      document.forms[0].tdfwqszymj.focus();
		      return false;
		    }
	    }

	}


	<%-- %>
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
    <% --%>


			document.forms[0].ztbs.value=<%=Constants.JMSBB_ZTBS_BC%>

			document.forms[0].flmc.value = document.forms[0].fl.options[document.forms[0].fl.selectedIndex].text;
			document.forms[0].tdfwqszylxmc.value = document.forms[0].tdfwqszylx.options[document.forms[0].tdfwqszylx.selectedIndex].text;

			if(document.forms[0].tdfwqszylx.value=="03")
			{
					document.forms[0].fwlbmc.value = document.forms[0].fwlb.options[document.forms[0].fwlb.selectedIndex].text;
			}
			else
			{
					document.forms[0].fwlb.value = "";
					document.forms[0].fwlbmc.value = "";
			}
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

function checkSelect()
{
    if(document.forms[0].tdfwqszylx.value=="03")
    {

       document.forms[0].fwlb.disabled=false ;
    }
    else
    {
        document.forms[0].fwlb.disabled=true ;

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
		if(document.forms[0].qrScanFlag.value=="1"){
			alert("扫描数据请勿修改！");
			return false;
		}
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
		if(document.forms[0].qrScanFlag.value=="1"){
			alert("扫描数据请勿修改！");
			return false;
		}
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
				//定位焦点到扫描组件的录入域上
				document.all.scanInputBzqsGr.focus();
				document.forms[0].qrScanFlag.value="0";
				//alert("document.forms[0].qrScanFlag.value="+document.forms[0].qrScanFlag.value);
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


<!----本页面的头文件和本页帮助的路径----->

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0" onload="checkSelect();initPage()">
<jsp:include page="/include/Header.jsp" flush="true">

 <jsp:param name="subtitle" value="不征契税录入>不征契税个人信息录入表"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>
<SCRIPT language=javascript>

</SCRIPT>
 <TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=98%>
  <TBODY>
  <TR>
    <TD vAlign=top>

         <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>北京市地方税务局不征契税信息采集表-个人</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
          <html:form action="/bzqslr/addBzqsGr.do">
              <html:hidden property="operationType"/>
              <html:hidden property="xtdqsj"/>
              <html:hidden property="ztbs" value="<%=Constants.ZB_ZTBS_BC%>"/>
              <html:hidden property="yhbs" value="<%=Constants.ZB_YHBS_GR%>"/>
<input type="hidden" name="dataSource_gm" value="">
<input type="hidden" name="qrScanFlag" value="0">

<!--add by hazhengze 20081201 start-->

<BR>采集域:<input type="text" name="scanInputBzqsGr" id="scanInputBzqsGr" size="80"  maxlength="5000" onkeydown="check()">
<IMG alt="生成" height=22 id="shengcheng" name="shengcheng"
 onclick="shengcheng_method()" onmouseout=MM_swapImgRestore()
 onmouseover="MM_swapImage('shengcheng','','<%=static_file%>images/shengcheng2.jpg',1)"
src="<%=static_file%>images/shengcheng1.jpg" style="CURSOR: hand" width=79>
<IMG alt="复位" height=22 id="fuwei" name="fuwei"
  onclick="fuwei_method()" onmouseout=MM_swapImgRestore()
  onmouseover="MM_swapImage('fuwei','','<%=static_file%>images/fuwei2.jpg',1)"
src="<%=static_file%>images/fuwei1.jpg" style="CURSOR: hand" width=79>

<SCRIPT language=javascript>

//定位焦点到扫描组件的录入域上，设置焦点到已有文本的最后
function QRInputFocus(){
	//定位焦点到扫描组件的录入域上
	document.all.scanInputBzqsGr.focus();
	//设置焦点到已有文本的最后
	var e=document.all.scanInputBzqsGr;
	var r =e.createTextRange();
	r.moveStart('character',e.value.length);
	r.collapse(true);
	r.select();
}

function addRowOnclickAlert(){
	alert("扫描数据请勿修改!");
}

function delRowOnclickAlert(){
	alert("扫描数据请勿修改!");
}

function fuwei_method(){
	//
	document.forms[0].qrScanFlag.value="0";
	//alert("call fuwei()");
	//alert("translateObj:"+translateObj);
	//第1段：头标志符（不作处理）
	//第2段：合同编号
	document.forms[0].htbh.value="";
	document.forms[0].htbh.readOnly=false;
	document.forms[0].htbh.style.color="";
	//第3段：合同网上签约日期
	document.forms[0].hyqdsj.value="";
	document.forms[0].hyqdsj.readOnly=false;
	document.forms[0].hyqdsj.style.color="";
	//第4段：交易房屋地址区县（不作处理）
	//第5段：交易房屋地址
	document.forms[0].tdfwzldz.value="";
	document.forms[0].tdfwzldz.readOnly=false;
	document.forms[0].tdfwzldz.style.color="";
	//第6段：房屋权属转移类型
	document.forms[0].tdfwqszylx.onclick="";
	document.forms[0].tdfwqszylx.style.color="";
	//第7段：是否为首次上市已购公房（不做导入）
	//第8段：房屋产权证号（不做导入）
	//第9段：房屋所有权证填发日期（不做导入）
	//第10段：房屋建筑面积
	document.forms[0].fwjzmj.value="";
	document.forms[0].fwjzmj.readOnly=false;
	document.forms[0].fwjzmj.style.color="";
	//第11段：建筑结构（不做导入）
	//第12段：规划用途（不做导入）
	//第13段：合同总价（不做导入）
	//第14段：所在楼层，总层数（不做导入）
	//第15段：外币种（不做导入）
	//第16段：外币金额（不做导入）
	//第17段：汇率（不做导入）
	//第18段：卖方信息（不做导入）
	//第19段：买方信息
	/**
	/* @todo 初始化动态表
	*/
  var daListRow = daList.getCurrentRow();
	//alert("动态表 ROW =="+daListRow);
	//删除动态表
           if(daListRow>1){
             for(;daListRow>1;daListRow--){
               daList.moveto(daListRow);
               daList.deleteRow(daListRow);
//               alert("减1以后=="+daListRow);
             }
           }
           document.forms[0].reset();

           daList.focusAt(daList.getCurrentRow(),1);
           this.TABLE_LIST.rows(1).cells(1).firstChild.readOnly = false;
           this.TABLE_LIST.rows(1).cells(1).firstChild.style.color="";
           this.TABLE_LIST.rows(1).cells(3).firstChild.disabled = false;
           this.TABLE_LIST.rows(1).cells(4).firstChild.readOnly = false;
           this.TABLE_LIST.rows(1).cells(4).firstChild.style.color="";
           var zjValue=daList.getCellAt(daList.getCurrentRow(),3);
           zjValue.value="02";
           var gjValue=daList.getCellAt(daList.getCurrentRow(),5);
           gjValue.value="CHN";
           this.TABLE_LIST.rows(1).cells(6).firstChild.checked = true;
	//第20段：房地产中介机构名称（不做导入）
	//定位焦点到扫描组件的录入域上
	QRInputFocus();
  document.forms[0].operationType.value='Show';
  document.all.scanInputBzqsGr.value="";
}

function shengcheng_method(){
	//alert("call shengcheng()");
	//定位焦点到扫描组件的录入域上
	QRInputFocus();
	//
	str_temp=document.all.scanInputBzqsGr.value;
	if(str_temp==""){
		alert("无扫描数据，请重新扫描输入！");
		return false;
	}else{
		scanPic();
		return false;
	}
}

//扫描枪扫入数据后自动触发键盘事件
function check(){
	//IE7.0（含）以下触发的是回车
	if(event.keyCode==13){
		//alert("检测到一个Enter...");
		transObj=scanPic();
		return false;
	}
}

//调用动态库，识别图片，把识别出来的内容显示到页面上
function scanPic(){
	//alert(">>>扫描完毕开始解析！");
	var istr=document.all.scanInputBzqsGr.value;
	//alert("扫描字符串为“"+istr+"”");
	//调用库中的方法
	var obj=new ActiveXObject("hyQRBarCode.QRCode");
	//alert("obj="+obj);
	var transObj=obj.DeBarCodeString(istr);
	//alert("transObj="+transObj);
	//判定是否空业务条形码
	var transObjArray=transObj.split("^");
	if(transObjArray.length==1&&transObj=="<%=com.creationstar.bjtax.qsgl.util.Constants.QRCODE_DEFAULT_NULL%>"){
		alert("扫描条形码内容为空，请手工录入申报表！");
		fuwei_method();
		return false;
	}

	//检查二维码明文是否正确；
	//alert("开始检查二维码头数据的格式");
	QRCodeCheckResult=checkQRCodeHeader(transObj);
	//alert("QRCodeCheckResult="+QRCodeCheckResult);
	//
	if(QRCodeCheckResult=="1"){
		alert("扫描图片失败，请重新扫描或手工录入信息！");
		fuwei_method();
		return false;
	}else if(QRCodeCheckResult=="2"){//如果明文不为"object_"开头则表示条码图片扫描不全，提示继续扫描
		alert("请扫描全部二维条形码图片，否则无法解码！");
		QRInputFocus();
		return false;
	}else if(QRCodeCheckResult=="3"){
		translate_xinfang(transObj);//新房
	}else if(QRCodeCheckResult=="4"){
		translate_cunliangfang(transObj);//存量房
	}
	//清空录入域并重新置焦点
	document.all.scanInputBzqsGr.value="";
	document.all.scanInputBzqsGr.focus();
	//alert(">>完毕...");
	//
	document.forms[0].qrScanFlag.value="1";
	return false;
	// document.picForm.piccode.value=ret;
}
//将明文字符串解析后填入页面各域，新房
function translate_cunliangfang(translateObj){
	/**
	/* @todo 对于新房进行解析
	*/
}

//将明文字符串解析后填入页面各域，二手房
function translate_cunliangfang(translateObj){
	//alert("translateObj:"+translateObj);
	var transObjArray=translateObj.split("^");
	//第1段：头标志符（不作处理）
	//第2段：合同编号
	index=2+0;
	tmpObj=transObjArray[index];
	putObjectValue(document.forms[0].htbh,tmpObj);
	//第3段：合同网上签约日期
	index=3+0;
	tmpObj=transObjArray[index];
	tmpObj=formatDateStr(tmpObj);
	putObjectValue(document.forms[0].hyqdsj,tmpObj);
	//第4段：交易房屋地址区县（不作处理）
	//第5段：交易房屋地址
	index=5+0;
	tmpObj=transObjArray[index];
	putObjectValue(document.forms[0].tdfwzldz,tmpObj);
	//第6段：房屋权属转移类型
	index=6+0;
	tmpObj=transObjArray[index];
	tmpObj="03";
	tmpObj_page=document.forms[0].tdfwqszylx;
	for(i=0;i<tmpObj_page.options.length;i++){
		if(tmpObj_page.options.value=tmpObj){
			tmpObj_page.style.color="#ADADAD";
			tmpObj_page.selectedIndex=i;
			tmpObj_page.value=tmpObj;
			tmpObj_page.onclick=function qr_readonly_tdfwqszylx(){alert("请勿修改！");return false;};//置位只读
			checkSelect();
		}
	}
	//第7段：是否为首次上市已购公房（不做导入）
	//第8段：房屋产权证号（不做导入）
	//第9段：房屋所有权证填发日期（不做导入）
	//第10段：房屋建筑面积
	index=10+0;
	tmpObj=transObjArray[index];
	putObjectValue(document.forms[0].fwjzmj,delFuhao(tmpObj));
	//第11段：建筑结构（不做导入）
	//第12段：规划用途（不做导入）
	//第13段：合同总价（不做导入）
    //document.forms[0].cjjgyrmb.value=retstr[13];
	//第14段：所在楼层，总层数（不做导入）
	//第15段：外币种（不做导入）
	//第16段：外币金额（不做导入）
	//第17段：汇率（不做导入）
	//第18段：卖方信息（不做导入）
	//第19段：买方信息
	index=19+0;
	tmpObj=transObjArray[index];
	var mfxx = tmpObj.split("~");
	var mfxxArrayLength=mfxx.length;
	var mfxxCount=mfxxArrayLength/6;
	//alert("mfxxCount="+mfxxCount);
	for(i=1;i<=mfxxCount;i++){
    if(mfxx[1] == "1"){
      if(i>1){
        addRow();
      }
      daList.focusAt(daList.getCurrentRow(),1);
      //纳税人名称
      var mcValue=daList.getCellAt(daList.getCurrentRow(),1);
      mcValue.value=mfxx[6*(i-1)+0];
      this.TABLE_LIST.rows(i).cells(1).firstChild.readOnly = true;
      this.TABLE_LIST.rows(i).cells(1).firstChild.style.color="#ADADAD";
      //电话
      var dhValue=daList.getCellAt(daList.getCurrentRow(),2);
      dhValue.value=mfxx[6*(i-1)+5];
      //联系电话 不设置为只读
      //this.TABLE_LIST.rows(1).cells(2).firstChild.readOnly = true;

      //证件类型
      var zjlxValue=daList.getCellAt(daList.getCurrentRow(),3);
      zjlxValue.value=zjlxJwToDs(mfxx[6*(i-1)+2]);
      this.TABLE_LIST.rows(i).cells(3).firstChild.disabled = true;
      //证件号码
      var zjhmValue=daList.getCellAt(daList.getCurrentRow(),4);
      zjhmValue.value=mfxx[6*(i-1)+3];
      this.TABLE_LIST.rows(i).cells(4).firstChild.readOnly = true;
      this.TABLE_LIST.rows(i).cells(4).firstChild.style.color="#ADADAD";
      //国籍
//      var gjValue=daList.getCellAt(daList.getCurrentRow(),5);
//      gjValue.value=mfxx[i];
      //产权人类型
      //var cqrValue=daList.getCellAt(daList.getCurrentRow(),6);
      //cqrValue.value=mfxx[5];
    }else{
      alert("买方信息为非个人，请重新扫描二维条形码图片，或者二维条形码图片错误！");
    	fuwei_method();
    	return false;
    }
	}
	//第20段：房地产中介机构名称（不做导入）
}

//置值方法
function putObjectValue(Obj_page,obj_value){
	if(obj_value==null||obj_value==""||obj_value=="null"){
		Obj_page.readOnly=false//置位只读
	}else{
		Obj_page.readOnly=true//置位只读
		Obj_page.style.color="#ADADAD";
		Obj_page.value=obj_value;
	}
}
</SCRIPT>
<!--add by hazhengze 20081201 end-->
            <TABLE border=0 cellSpacing=0 class=tabled-99 width="98%">
              <TBODY>
			  <BR>
			  <TR>
			  	<TD class=2-td2-t-left width="30%">不征契税信息采集表号</TD>
			  	<html:hidden name="bzqsForm" property="sbbh"/>
			  	<TD class=2-td2-t-center width="70%" colspan=5><DIV align=left>&nbsp<bean:write name="bzqsForm" property="sbbh" /> </DIV></TD>

			  </TR>
			  <TR>

				<TD class=2-td2-left>房屋土地管理部门受理号</TD>
				<TD class=2-td2-left>
				<DIV align=left><html:text  property="fwtdbmslh" size="30" maxlength="30"/></DIV></TD>
                <!--20081125 modify by fujx ,增加建委业务编号字段开始-->
                                             <TD class=2-td2-left>建委业务编号</TD>
                                          <TD class=2-td2-left>
                                            <DIV align=left><html:text name="bzqsForm" property="jwywbh" maxlength="30"/>  </DIV></TD>
                                            <!--20081125 modify by fujx ,增加建委业务编号字段结束-->

                                            <!--20081125 modify by fujx ,增加合同编号字段开始-->
                                             <TD class=2-td2-left>合同编号</TD>
                                          <TD class=2-td2-center>
                                            <DIV align=left><html:text name="bzqsForm" property="htbh" maxlength="30"/>  </DIV></TD>
                                            <!--20081125 modify by fujx ,增加合同编号字段结束-->
			  </TR>
			  </TBODY>
			  </TABLE>

        <TABLE border=0 cellSpacing=0 class=tabled-99 width="98%">
         <TBODY>    <TR>
    <TD class=2-td2-left colspan="4" width="70%" ><DIV align=left>&nbsp;&nbsp;个人填写部分</DIV></TD>
    <TD class=2-td2-center width="30%" colspan="2" >  <DIV align=right>
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


                  <TR>
                  <TD class=2-td2-left width="8%" rowspan = "6">
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
                <DIV align=left><html:text property="hyqdsj" size="15"/><FONT color=red>*</FONT><br>(yyyymmdd)</DIV></TD>
                <TD class=2-td2-left width="19%">
                  <DIV align=right>分类&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="33%">
                  <DIV align=left>

                  <bean:define id="list" name="bzqsForm" property="flList" />

                  <html:select property="fl">
                   <html:options collection="list" labelProperty="qstdfwytmc" property="qstdfwytdm"/>
                   </html:select><FONT color=red>*</FONT>
                   <html:hidden property="flmc"/>
                    </DIV></TD>
                  </TR>
                 <TR>
                 <TD class=2-td2-left width="15%";>
                   <DIV align=right>土地、房屋权属转移类型&nbsp; </DIV> </TD>
                 <TD class=2-td2-left width="19%">
                <DIV align=left>
                <html:hidden property="tdfwqszylxmc"/>

                  <bean:define id="list" name="bzqsForm" property="tdfwqszylxList" />
                  <html:select property="tdfwqszylx" onchange="checkSelect()">
                   <html:options collection="list" labelProperty="qszyxsmc" property="qszyxsdm"/>
                   </html:select><FONT color=red>*</FONT>
                    </DIV></TD>

                <TD class=2-td2-left width="19%">
                  <DIV align=right>房屋类别&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="33%">
                  <DIV align=left>

									<html:hidden property="fwlbmc"/>
                  <bean:define id="list" name="bzqsForm" property="fwlbList" />
                  <html:select property="fwlb" disabled="true">
                   <html:options collection="list" labelProperty="fwlxmc" property="fwlxdm"/>
                   </html:select>

                   </DIV></TD>
                  </TR>
                  <TR>
                 <TD  class=2-td2-left width="25%";>
                   <DIV align=right>土地、房屋座落地址&nbsp; </DIV>
                </TD>
                <TD colspan="4"  class=2-td2-center width="15%">
                  <DIV align=left><html:text property="tdfwzldz" size="60" maxlength="200"/><FONT color=red>*</FONT></DIV></TD>
                  </TR>



              <TR>
                 <TD class=2-td2-left width="25%";>
                   <DIV align=right>土地、房屋权属转移面积&nbsp; </DIV>
                </TD>
                <TD  class=2-td2-left width="15%">
                   <DIV align=left>土地：<html:text property="tdfwqszymj" size="15" maxlength="14" />㎡</DIV>
                   </TD>
                <TD class=2-td2-left width="17%">
                  <DIV align=right>房屋建筑面积&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="33%">
                  <DIV align=left><html:text property="fwjzmj" size="20" maxlength="14"/>㎡</DIV></TD>
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
					  </DIV>
                   </TD>
                <TD class=2-td2-left width="17%">
                   <!-- 修改土地级次为所属区域-->
                  <DIV align=right>所属区域&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="33%">
                  <DIV align=left>
				  <html:select property="tdjc" >
				  		<!-- 由于2014年10月北京普通住房标准调整，故作此需改为新标准：每平米价格上限、每套房屋价格、所在区域三方面调整 ，
				                                   此处只修改所在区域显示。modified by gaoyh to 20141020 -->
                        <html:option value="00">请选择</html:option>
                        <html:option value="21">5环内</html:option>
                        <html:option value="22">5-6环</html:option>
                        <html:option value="23">6环外</html:option>
                        <!-- 
                        <html:option value="10">================</html:option>
                        <html:option value="11">四环内北部地区</html:option>
                        <html:option value="12">四环内南部地区</html:option>
                        <html:option value="13">四环至五环北部地区</html:option>
                        <html:option value="14">四环至五环南部地区</html:option>
                        <html:option value="15">五环至六环北部地区</html:option>
                        <html:option value="16">五环至六环南部地区</html:option>
                        <html:option value="17">六环外地区</html:option>
                        <html:option value="00">================</html:option>
                        <html:option value="01">三环以内</html:option>
                        <html:option value="02">三环至四环之间</html:option>
                        <html:option value="03">四环至五环之间</html:option>
                        <html:option value="04">五环以外</html:option>
                         -->
                      </html:select>
				</DIV></TD>
             </TR>


			<%-- %>
                <TR>
                <TD class=2-td2-left width="8%" rowspan = "2">
                  <DIV align=right>成交</DIV>
                   <DIV align=right>价格 <FONT color=red>*</FONT> </DIV>
                  <TD  class=2-td2-left width="5%">
                  <DIV align=left><html:text property="cjjgyrmb" size="20" maxlength="15"/><br>元(人民币) </DIV></TD>
                  <TD class=2-td2-left width="17%">
                  <DIV align=right>评估价格&nbsp; </DIV></TD>
                <TD  colspan="2"  class=2-td2-center width="32%">
                   <DIV align=left><html:text property="pgjg" size="20" maxlength="15"/> <br>元(人民币) </DIV></TD>
              </TR>

               <TR>
                 <TD class=2-td2-left width="15%" >
                   <DIV align=left><html:text property="cjjgywb" size="20" maxlength="15" onchange="fnConvertWb(this,hn,zhyrmb)"/><br>元(外币) </DIV></TD>
                <TD class=2-td2-center width="33%" >
                  <DIV align=left>
									<html:hidden property="bzmc"/>
                  <bean:define id="list" name="bzqsForm" property="bzList" />
                  <html:select property="bz" onchange="checkSubmit('GetHL')">
                   <html:options collection="list" labelProperty="bzmc" property="bzdm"/>
                    </DIV>

                    <DIV align=left></html:select><br>汇率:<html:text property="hn" size="15" maxlength="15" onchange="fnConvertWb(cjjgywb,this,zhyrmb)"/></DIV></TD>

                 <TD class=2-td2-center colspan="3" width="25%">
                  <DIV align=left><html:text property="zhyrmb" size="20" maxlength="15"/><br>折合元(人民币) </DIV></TD>

				</tr>
				<% --%>



                 <TR>
                 <TD class=2-td2-left width="15%";>
                   <DIV align=right>备注&nbsp; </DIV>
                </TD>
                <TD colspan="5" class=2-td2-center width="84">
                  <DIV align=left><html:textarea property="beizhu" cols="45" rows="5" />
                  </DIV></TD>
                </TR>

                  </TBODY></TABLE><BR>

            <DIV align=center>

            <IMG alt=保存 height=22 id=baocun name=Submit63
            onclick="checkSubmit('Save')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
            src="<%=static_file%>images/baocun1.jpg" style="CURSOR: hand" width=79>

            <IMG alt=退出 height=22 id=tuichu name=tuichu
            onclick="checkSubmit('Return');" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
            src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand" width=79>
            </DIV><BR>

                                 </html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
<script language=JavaScript type='text/JavaScript'>
    <%-- %>fnConvertWb(document.all.cjjgywb,document.all.hn,document.all.zhyrmb);<% --%>
</script>
