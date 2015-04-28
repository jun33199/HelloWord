<%@ page import="java.util.Map" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.code.CodeManager" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.constant.CodeConstants" %>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.util.SfHashList"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qysdsjb.web.QysdsjbForm"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>

<%@ page contentType="text/html; charset=GBK" %>
<jsp:useBean type="com.ttsoft.bjtax.smsb.sbzl.qysdsjb.web.QysdsjbForm" scope="request" id="qysdsjbForm"/>
<html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>企业所得税季度纳税申报表</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/function.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/Bolan.js"></script>
<script language=JavaScript src="../js/MathString.js"></script>
<script language=JavaScript src="../js/Stack.js"></script>
<script language=JavaScript src="../js/reader.js"></script>

<script language="JavaScript">

function jkpz(a){
  var valuess= a.value;
  if(valuess.length==15||valuess.length==16||valuess.length==0)
  {}
  else{
    alert("缴款凭号是15位或是16位");
    a.value="";
  }
}

function jsjdmQuery(){
  if(event.keyCode==13) event.keyCode = 9;
}

function doSave()
{  
  
  var jsjdm;
  var nsrmc;
  jsjdm = document.forms[0].jsjdm.value;
  nsrmc = document.forms[0].nsrmc.value;
  
  if(!compareDate2Save(document.forms[0].skssjsrq))return false;
  if(jsjdm=="" || nsrmc==""){
    alert("页面信息填写不正确！");
    return false;
  }else{
    doSubmitForm("Save");
    return false;
  }
  
}

function doDelete()
{ 
  var jsjdm;
  var nsrmc;
	jsjdm = document.forms[0].jsjdm.value;
	jsjdm = document.forms[0].jsjdm.value;
  nsrmc = document.forms[0].nsrmc.value;
	if(jsjdm=="" || nsrmc==""){
    alert("页面信息填写不正确！");
    return false;
  }else{
	  doSubmitForm("Delete");
    return false;
  }
}

function doReturn()
{
	document.forms[0].actionType.value = "Return";
	document.forms[0].submit();
}

function setFoucs(){
	document.forms[0].jsjdm.focus();
}

function doQuery(){
	var jsjdm;
	jsjdm = document.forms[0].jsjdm.value;
	if(jsjdm==""){
    alert("计算机代码不允许是空！");
    return false;
  }else{
    document.all.jmse.value="";//查询前清空减免额。
	document.all.jmsl.value="";//查询前清空减免额。
    doSubmitForm("Query");
    return false;
  }
}

var slnum = 0;
var szsmArray = new Array();

function szsm(sl, ynszzs,ynsqss){
  this.sl = sl;
  this.ynszzs = ynszzs;
  this.ynsqss=ynsqss;
}

szsmArray[0] = new szsm( 0.18, 30000,0);
szsmArray[1] = new szsm( 0.27,100000,30000 );
szsmArray[2] = new szsm( 0.33, 0 ,100000);
slnum=2;

function begins(){
  document.forms[0].srze.value = 0;
  document.forms[0].qcwjsdse.value = 0;
  document.forms[0].ynsdse.value = 0;
  document.forms[0].jmsdse.value = 0;
  document.forms[0].cbyqndse.value = 0 ;
  document.forms[0].sjyjsdsse.value = 0;
  document.forms[0].bqyjsdse.value = 0;
  document.forms[0].lrze.value = 0;
  document.forms[0].mbyqndks.value = 0;
  document.forms[0].bkhlrze.value = 0;
}

//清理
function ql(){
  if(confirm("是否清除还原页面所有信息")){
    document.forms[0].srze.value = 0;
    document.forms[0].qcwjsdse.value = 0;
    document.forms[0].jmsdse.value = 0;
    document.forms[0].cbyqndse.value = 0 ;
    document.forms[0].sjyjsdsse.value = 0;
    document.forms[0].bqyjsdse.value = 0;
    document.forms[0].lrze.value = 0;
    //document.forms[0].sl.value = 0;
    document.forms[0].sjybsdse.value = 0;
    document.forms[0].ynsdse.value=0;
	document.forms[0].mbyqndks.value = 0;
	document.forms[0].bkhlrze.value = 0;
    //定额征收
    <%
      if(CodeConstant.ZSFSDM_DEZS.equals(qysdsjbForm.getZsfs()) ){
    %>
      document.forms[0].srze.readOnly=true;
      document.forms[0].srze.value=0;
      document.forms[0].srze.className="inbright";
      document.forms[0].lrze.readOnly=true;
      document.forms[0].lrze.value=0;
      document.forms[0].lrze.className="inbright";
	  document.forms[0].mbyqndks.readOnly=true;
      document.forms[0].mbyqndks.value=0;
      document.forms[0].mbyqndks.className="inbright";
      document.forms[0].sl.readOnly=true;
      document.forms[0].sl.value="";
      document.forms[0].sl.className="inbright";
	  
      dejs();
      sjsdsejs();
      return;//定额征收则肯定不是下面的类型，直接返回  zhu guanglin
    <%
      }
    %>

    //高新企业
    if(document.forms[0].zssl.value!="")
      document.forms[0].sl.value = document.forms[0].zssl.value;
    sjsdsejs();
  }
}

//根据行次四的值找到适用税率并计算相应的应纳所得税额
function shczsl(){
	//modify by shiyanfeng 根据行次4的值来确定税率
  tmp= document.forms[0].bkhlrze.value;
 
  var month=document.forms[0].sbrq.value+"";
  var tmps=0;//用来寻找适用税率的临时值
  //mofified by zhu guanglin 2004-3-25 根据申报日期判断用利润总额的多少倍来找试用税率
  switch(month.substring(4,6)){
    case "01":
    case "02":
    case "03":
    tmps=tmp;//1月为总额不乘,2007-1-4 by liwenhua
    break;
    case "04":
    case "05":
    case "06":
      tmps=tmp*4;//4月乘4
    break;
    case "07":
	case "08":
	case "09":
      tmps=tmp*2;//7月乘2
    break;
    case "10":
	case "11":
	case "12":
      tmps=tmp*4/3;//10月乘4除3
    break;
    default:
      tmps=tmp*4;
    break;
  }
  tmps=formatNumber(tmps);
  //tmps=tmp*4;//原来是根据行次2的值找适用税率，现改为（行次2 ×4）的值去找适用税率（张昕） by zhu guanglin 2004-03-11
  for(k=0;k<=slnum;k++){
		
    if(tmps>szsmArray[k].ynsqss && tmps<=szsmArray[k].ynszzs){
      document.forms[0].sl.value=szsmArray[k].sl;
    }
    if(tmps>szsmArray[slnum].ynsqss){
      document.forms[0].sl.value=szsmArray[slnum].sl;
    }
  }

  if(tmp>0){
    document.forms[0].ynsdse.value=round(tmp*document.forms[0].sl.value*document.forms[0].yhbl.value);
  }else{
    document.forms[0].sl.value=szsmArray[0].sl;
    document.forms[0].ynsdse.value=0;
  }
}

//实际应补（退）所得税额计算 & 减免所得税额的计算
function sjsdsejs(){
  <%
    if(CodeConstant.XZQYBS.equals(qysdsjbForm.getXzqyjm())){//如果是乡镇企业则hc6=hc4*0.1
  %>
    document.forms[0].jmsdse.value=round(document.forms[0].ynsdse.value*<%=CodeConstant.JM_XIANZHEN%>);
  <%
    }else{
  %>//如果有减免税额（包括零）
    if(document.forms[0].jmsl.value!=""){
      //document.forms[0].jmsdse.value=document.forms[0].jmse.value;
	  //纳税人具有减免税认定时，减免所得税额=本期应纳所得税额*减免税率
	 document.forms[0].jmsdse.value= round(document.forms[0].ynsdse.value*document.forms[0].jmsl.value);
    }else{
      document.forms[0].jmsdse.value=0;
    }
  <%
    }  
  %>
//20050203 shiyanfeng 12=6+7-8+9-10-11
    document.forms[0].sjybsdse.value=round(parseFloat(document.forms[0].ynsdse.value)+parseFloat(document.forms[0].qcwjsdse.value)-parseFloat(document.forms[0].jmsdse.value)+parseFloat(document.forms[0].cbyqndse.value)-parseFloat(document.forms[0].sjyjsdsse.value)-parseFloat(document.forms[0].bqyjsdse.value));
	
	//20050203 shiyanfeng 12=6+7-8+9-10-11
	
}

//实际应补（退）所得税额计算
function sjsdsejs2(){
 //20050203 shiyanfeng 12=6+7-8+9-10-11
  document.forms[0].sjybsdse.value=round(parseFloat(document.forms[0].ynsdse.value)+parseFloat(document.forms[0].qcwjsdse.value)-parseFloat(document.forms[0].jmsdse.value)+parseFloat(document.forms[0].cbyqndse.value)-parseFloat(document.forms[0].sjyjsdsse.value)-parseFloat(document.forms[0].bqyjsdse.value));
}

//纯益率征收相关计算
function cyljs(){
  var jsjdm;
  var nsrmc;
  jsjdm = document.forms[0].jsjdm.value;
  nsrmc = document.forms[0].nsrmc.value;
  if(jsjdm=="" || nsrmc==""){
    alert("页面信息填写不正确！");
    begins();
  }else{
	  //纯益率征收时，利润总额（应纳税所得额）只读
	document.forms[0].lrze.readOnly=true;
    document.forms[0].lrze.value=0;
    document.forms[0].lrze.className="inbright";
    
	document.forms[0].lrze.value=round( document.forms[0].srze.value* document.forms[0].cyl.value)//纯益率适用税率
    shczsl();
    //sjsdsejs();
  }
}

//其他企业相关计算
function qtjs(){
  var jsjdm;
  var nsrmc;
  jsjdm = document.forms[0].jsjdm.value;
  nsrmc = document.forms[0].nsrmc.value;
	if(jsjdm=="" || nsrmc==""){
    alert("页面信息填写不正确！");
    begins();
  }else{
    tmp= document.forms[0].lrze.value;
    shczsl();
    //sjsdsejs();
  }
}

//定额征收相关计算
function dejs(){
	//定额征收时，本期应纳所得税额 6=4×5  可修改，其他为不可修改。	
  document.forms[0].ynsdse.readOnly=false;
  document.forms[0].ynsdse.value="0";
  document.forms[0].ynsdse.className="inputalignright";
  
  var jsjdm;
  var nsrmc;
  jsjdm = document.forms[0].jsjdm.value;
  nsrmc = document.forms[0].nsrmc.value;
  if(jsjdm=="" || nsrmc==""){
    alert("页面信息填写不正确！");
    begins();
  }else{
    document.forms[0].ynsdse.value=round(document.forms[0].de.value*document.forms[0].yhbl.value);//优惠比例
    //sjsdsejs();
  }
}
//高新企业的相关计算
function gxjs(){
  var jsjdm;
  var nsrmc;
  jsjdm = document.forms[0].jsjdm.value;
  nsrmc = document.forms[0].nsrmc.value;
  if(jsjdm=="" || nsrmc==""){
    alert("页面信息填写不正确！");
    begins();
  }else{
    document.forms[0].sl.value=document.forms[0].zssl.value;//高新企业适用税率
    document.forms[0].ynsdse.value=round(document.forms[0].bkhlrze.value*document.forms[0].zssl.value);
    //shczsl();
    //sjsdsejs();
	}
}

//修改行次1的相关处理
function lxxz(a){
  if(isNum(a,0,null,1,15,2)){
    //按征收方式分类
    <%
      if(CodeConstant.ZSFSDM_DEZS.equals(qysdsjbForm.getZsfs()) ){//定额征收
    %>
    document.forms[0].srze.readOnly=true;
    document.forms[0].srze.value=0;
    document.forms[0].srze.className="inbright";
    document.forms[0].lrze.readOnly=true;
    document.forms[0].lrze.value=0;
    document.forms[0].lrze.className="inbright";
	document.forms[0].mbyqndks.readOnly=true;
    document.forms[0].mbyqndks.value=0;
    document.forms[0].mbyqndks.className="inbright";
    document.forms[0].sl.readOnly=true;
    document.forms[0].sl.value="";
    document.forms[0].sl.className="inbright";
    dejs();
    sjsdsejs();
    return;//定额征收则肯定不是下面的类型，直接返回  zhu guanglin
    <%
      }else if(CodeConstant.ZSFSDM_CYLZS.equals(qysdsjbForm.getZsfs()) ){//纯益率征收
    %>
      cyljs();
    <%
      }else if(CodeConstant.QITAQIYE.equals(qysdsjbForm.getZsfs()) ){//其他企业
    %>
      qtjs();
    <%
      }
    %>
    
    //高新企业
    if(document.forms[0].zssl.value!="")
      gxjs();
	lxxz3(a);
    sjsdsejs();
  }//end if
}//end lxxz

//修改行次2的相关处理 zhu guanglin
function lxxz2(a){
  if(isNum(a,null,null,1,15,2)){
    if(document.forms[0].zssl.value!=""){//高新企业
      document.forms[0].sl.value=document.forms[0].zssl.value;//高新企业适用税率
      document.forms[0].ynsdse.value=round(document.forms[0].lrze.value*document.forms[0].zssl.value);
      if(document.forms[0].ynsdse.value<0)//行次4计算为负数后改为0
        document.forms[0].ynsdse.value=0;
    }else{//一般企业
      shczsl();//一般企业适用税率
    }
	//如果利润总额（应纳税所得额）小于零，则减：弥补以前年度亏损 为只读，缺省为零
	if(document.forms[0].lrze.value<0){
		document.forms[0].mbyqndks.readOnly=true;
        document.forms[0].mbyqndks.value=0;
        document.forms[0].mbyqndks.className="inbright";
	}else{
		document.forms[0].mbyqndks.readOnly=false;
        document.forms[0].mbyqndks.value=0;
        document.forms[0].mbyqndks.className="inputalignright";
	}
	lxxz3(document.forms[0].mbyqndks);
    sjsdsejs();//计算12=6+7-8+9-10-11和8
  }
}

//修改行次3的相关计算处理 shiyanfeng 20050203
function lxxz3(a){
	if(isNum(a,0,null,1,15,2)){
		document.forms[0].bkhlrze.value=round(document.forms[0].lrze.value - document.forms[0].mbyqndks.value);
		if(document.forms[0].zssl.value==""){//非高新技术企业高新企业
			shczsl();
		}else{
			gxjs();
		}
		sjsdsejs();//计算12=6+7-8+9-10-11和8
	}
}


//页面导入时显示的处理  modified by zhu guanglin  按新需求整理 2004-03-17
function shows(){

  document.forms[0].jsjdm.focus();
  if(document.forms[0].actionType.value=="Show"){
    begins();
    return;
  }//end type=show
  if(document.forms[0].actionType.value=="Query"){
    if( document.forms[0].jsjdm.value==""){
      begins();
      return;
    }//end if

    if(document.forms[0].srze.value==""){
      begins();
      
      //行次6的处理
      <%//如果不是乡镇企业
        if(!CodeConstant.XZQYBS.equals(qysdsjbForm.getXzqyjm())){
      %>//并且不具备一般减免资格的，第六行不可编辑
        //var jmsez=document.forms[0].jmse.value;
		var jmsl=document.forms[0].jmsl.value;
        if(jmsl==""){// || jmsez==0){      //去掉为'0'的情况
          //document.forms[0].jmsdse.readOnly=true;
          //document.forms[0].jmsdse.value=0;
          //document.forms[0].jmsdse.className="inbright";
        }
      <%
        }
      %>

      //按征收方式分类
      <%
        if(CodeConstant.ZSFSDM_DEZS.equals(qysdsjbForm.getZsfs()) ){//定额征收
      %>
        document.forms[0].srze.readOnly=true;
        document.forms[0].srze.value=0;
        document.forms[0].srze.className="inbright";
        document.forms[0].lrze.readOnly=true;
        document.forms[0].lrze.value=0;
        document.forms[0].lrze.className="inbright";
		document.forms[0].mbyqndks.readOnly=true;
        document.forms[0].mbyqndks.value=0;
        document.forms[0].mbyqndks.className="inbright";
        document.forms[0].sl.readOnly=true;
        document.forms[0].sl.value="";
        document.forms[0].sl.className="inbright";
        dejs();
        sjsdsejs();
        return;//定额征收则肯定不是下面的类型，直接返回  zhu guanglin
      <%
        }else if(CodeConstant.ZSFSDM_CYLZS.equals(qysdsjbForm.getZsfs()) ){//纯益率征收
      %>
        cyljs();
      <%
        }else if(CodeConstant.QITAQIYE.equals(qysdsjbForm.getZsfs()) ){//其他企业
      %>
        qtjs();
      <%
        }
      %>
      
      //高新企业
      if(document.forms[0].zssl.value!="")
        gxjs();
      
      sjsdsejs();
    }else{
      //按征收方式分类
      <%
        if(CodeConstant.ZSFSDM_DEZS.equals(qysdsjbForm.getZsfs()) ){//定额征收
      %>
        document.forms[0].srze.readOnly=true;
        document.forms[0].srze.value=0;
        document.forms[0].srze.className="inbright";
        document.forms[0].lrze.readOnly=true;
        document.forms[0].lrze.value=0;
        document.forms[0].lrze.className="inbright";
		document.forms[0].mbyqndks.readOnly=true;
		document.forms[0].mbyqndks.value=0;
		document.forms[0].mbyqndks.className="inbright";
        document.forms[0].sl.readOnly=true;
        document.forms[0].sl.value="";
        document.forms[0].sl.className="inbright";

		//定额征收时，本期应纳所得税额 6=4×5  可修改，其他为不可修改。	
	  document.forms[0].ynsdse.readOnly=false;
	  document.forms[0].ynsdse.className="inputalignright";
      <%
        }
      %>
	<%
       if(CodeConstant.ZSFSDM_CYLZS.equals(qysdsjbForm.getZsfs()) ){//纯益率征收
      %>
        	  //纯益率征收时，利润总额（应纳税所得额）只读
			document.forms[0].lrze.readOnly=true;		  
			document.forms[0].lrze.className="inbright";
      <%
        }
      %>
      //行次6的处理
      <%//如果不是乡镇企业
        if(!CodeConstant.XZQYBS.equals(qysdsjbForm.getXzqyjm())){
      %>//并且不具备一般减免资格的，第六行不可编辑
        //var jmsez=document.forms[0].jmse.value;
		var jmsl=document.forms[0].jmsl.value;
        if(jmsl==""){// || jmsez==0){ //去掉为'0'的情况
         // document.forms[0].jmsdse.readOnly=true;
          //document.forms[0].jmsdse.value=0;
         // document.forms[0].jmsdse.className="inbright";
        }
      <%
        }
      %>
    }
  }//end type=query
}// end show()

//最后计算（实际应补（退）所得税额计算）for hc4
function zuihoujisuan(a){
  if(isNum(a,0,null,1,15,2)){
    sjsdsejs();
  }
}

//最后计算（实际应补（退）所得税额计算） for hc5,可以为负值
function zuihoujisuan2(a){
  if(isNum(a,null,null,1,15,2)){
    sjsdsejs2();
  }
}

//最后计算（实际应补（退）所得税额计算） for hc6-9
function zuihoujisuan3(a){
  if(isNum(a,0,null,1,15,2)){
    sjsdsejs2();
  }
}

//如果申报月份不属于企业所得税季度征期4、7、10则提示操作人员
function checkZQ(sbrq,ksrq,jzrq,lx){
	if(!isDate(sbrq,"v")) return;
	getStartEndDate1(sbrq,ksrq,jzrq,lx);
	var inputDate = sbrq.value;
	mon = inputDate.substring(4,6);
	if(mon!='01' && mon!='04' && mon!='07' && mon!='10'){
		alert('注意：'+inputDate+'不属于征期。');
	}
}

/**
* Notes: 获取当前日期的上一年/月/季的起止日期。
* Version: 0.9.00
* Author: Guoxh
* Parames: flag 1,Last Year;2,Last Month;默认：输出季度
*/
function getStartEndDate1(oInput1,oInput2,oInput3,flag){
	var date_start,date_end;
	var year,mon,days;
	var strMon;

	var inputDate = oInput1.value;

	//是否合法日期
	if(isDate(oInput1,"v")){
		year = inputDate.substring(0,4);
		mon = inputDate.substring(4,6);
		days = inputDate.substring(6,8);

		if(flag == 1){//Last Year
			date_start = (year-1)+"0101";
			date_end = (year-1)+"1231";
		}else if(flag == 2){//Last Month
			var date2 = new Date(year,mon-1,-1);
			days = date2.getDate()+1;
			year = date2.getYear();
			mon = date2.getMonth()+1;

			date_start = year+""+formatMon(mon)+"01";
			date_end = year+""+formatMon(mon)+days;
			//date_start = year+""+formatMon(mon-1)+"01";
			//date_end = year+""+formatMon(mon-1)+days;
		}else{
			//mon = parseInt(mon);
			switch(mon){
				case "01":
				case "02":
				case "03":
					date_start = (year-1)+"0101";
					date_end = (year-1)+"1231";
					break;
				case "04":
				case "05":
				case "06":
					date_start = year+"0101";
					date_end = year+"0331";
					break;
				case "07":
				case "08":
				case "09":
					date_start = year+"0101";
					date_end = year+"0630";
					break;
				case "10":
				case "11":
				case "12":
					date_start = year+"0101";
					date_end = year+"0930";
					break;
			}
		}

		oInput2.value = date_start;
		oInput3.value = date_end;
	}
}

//判断比较税款所属日期
	function compareDate2Save(obj){
		
		var strDate1 = document.forms[0].skssksrq.value;		
		var strYear1 = strDate1.substr(0,4);		
		var strMonth1 = strDate1.substr(4,2);
 	 	var strDay1 = strDate1.substr(6,2);
 	 	
 	 	var strDate2 = document.forms[0].skssjsrq.value;
 	 	var strYear2 = strDate2.substr(0,4);		
		var strMonth2 = strDate2.substr(4,2);
 	 	var strDay2 = strDate2.substr(6,2);
 	 	
 	 	var sksbrq = document.forms[0].sbrq.value;
 	 	var strYear3 = sksbrq.substr(0,4);		
		var strMonth3 = sksbrq.substr(4,2);
 	 	var strDay3 = sksbrq.substr(6,2); 
 	 	
  	var objDate1 = new Date(strMonth1+'-'+strDay1+'-'+strYear1);
  	var objDate2 = new Date(strMonth2+'-'+strDay2+'-'+strYear2);
  	var objDate3 = new Date(strMonth3+'-'+strDay3+'-'+strYear3);
  	
  	if(objDate2>objDate3){
  		alert("税款结束时间不能大于申报日期");
  		window.event.returnValue=false;
			document.forms[0].skssjsrq.focus();
			document.forms[0].skssjsrq.select();
			return false;
  	}
  	  	
  		if(objDate1>=objDate2){
			if(obj == document.forms[0].skssjsrq){
				alert("税款结束时间不能小于或等于税款开始时间");
			}else{
				alert("税款开始时间不能大于或等于税款结束时间");	
			}
			window.event.returnValue=false;
			obj.focus();
			obj.select();
			return false;
		}
		
		if(strYear1!=strYear2){
			alert("税款开始时间与税款结束时间的年份不同，税款不能跨年！");
			return false;				
		}
		
		return true;
			
	}






//End-->
</script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="shows()">
<%@ include file="include/header.jsp" %>
<html:form action="/webapp/smsb/qysdsjbAction.do" method="post">
<html:hidden property="actionType"/>
<html:hidden property="swjgzzjgdm"/>
<html:hidden property="lrr"/>
<html:hidden property="lrrq"/>
<html:hidden property="fsdm"/>
<html:hidden property="cjrq"/>
<html:hidden property="jd"/>
<html:hidden property="nsrmc"/>
<html:hidden property="nd"/>
<html:hidden property="yhbl"/>
<html:hidden property="zssl"/>
<html:hidden property="zsfs"/>
<html:hidden property="cyl"/>
<html:hidden property="de"/>
<html:hidden property="iszhsb"/>
<html:hidden property="qxdm"/>
<html:hidden property="jmse"/>
<html:hidden property="jmsl"/>
<html:hidden property="xzqyjm"/>
<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0" onkeydown="jsjdmQuery()">
  <tr>
    <td valign="top"> <br>
      <table align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1">企业所得税季度纳税申报表</td>
           </tr>
             <tr>
              <td valign="top" class="1-td2">
               <br>
                <table cellspacing="0" class="table-99">

                <tr class="black9">
                    <td align="center" nowrap><div align="left">申报日期
                  <html:text property="sbrq" size="11" maxlength="8" onchange="checkZQ(this,document.forms[0].skssksrq,document.forms[0].skssjsrq,3);lxxz2(document.forms[0].lrze)" tabIndex="2"/> </div>
                    </td>
					          <td align="left" nowrap>税款所属日期：
                     <!-- <html:text property="skssksrq" size="11" maxlength="8" onchange="compareDate('skssksrq','skssjsrq',0,this)" tabIndex="2"/>
                      至
                      <html:text property="skssjsrq" size="11" maxlength="8" onchange="compareDate('skssksrq','skssjsrq',0,this)" tabIndex="2"/> -->
                     <html:text property="skssksrq" size="11" maxlength="8" tabIndex="2"  onchange="isDate(this,false);"/>至
                     <html:text property="skssjsrq" size="11" maxlength="8" tabIndex="2" onchange="isDate(this,false);"/>
                      </td>
                      <td align="right" nowrap>金额单位：元</td>
                      </tr>
				            </table>
                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                      <td nowrap class="2-td2-t-left"><div align="right">计算机代码&nbsp;&nbsp;</div></td>
                        <td nowrap class="2-td2-t-left"><div align="left">&nbsp;
                        <html:text property="jsjdm" size="8" maxlength="8" onchange="doQuery();return false;"  tabIndex="2"/>
                        </div></td>
                        <td nowrap class="2-td2-t-left"><div align="right">申报单位名称（公章）&nbsp;&nbsp;</div></td>
                        <td nowrap class="2-td2-t-center"><div align="left">&nbsp;
                        <ttsoft:write name="qysdsjbForm" property="nsrmc"  scope="request" /> </div></td>

                      </tr>

                     </table>
                    <br>


                <table width='100%' border='0' cellpadding='0' cellspacing='0'>
                <tr>
                <td colspan="3" nowrap class="2-td1-left">项目</td>
                <td colspan="1" nowrap class="2-td1-left">行次</td>
                <td colspan="2" nowrap class="2-td1-center">本期累计数</td>
                </tr>
                <bean:define id="items" name="qysdsjbForm" property="dataList"/>
                <logic:iterate id="item" name="items" type="java.util.Map">
                  <%
                    String hc = (String)item.get("hc");
                    if(hc.equals("1")){
                  %>
                  <tr>
                        <td colspan="3" nowrap class="2-td2-left">
                        <div align="left"><ttsoft:write name="item" property="xmmc"/>
                        <input name="hc" type="hidden" value="<ttsoft:write name="item" property="hc"/>" id="hc_<%=hc%>"><input name="xmmc" type="hidden" value="<ttsoft:write name="item" property="xmmc"/>" id="xmmc_<%=hc%>" >
                        </div>
                        </td>
                        <td colspan="1" nowrap class="2-td2-left">
                        <div align="center">1
                        </div>
                        </td>
                        <td colspan="2" nowrap class="2-td2-center">
                        <input name="srze" type="text" value="<ttsoft:write name="item" property="srze"/>" id="srze" size="16" onchange="lxxz(this)" class="inputalignright" tabIndex="2">
                        </td>
                  </tr>
                  <%
                    }
                    if(hc.equals("2")){
                  %>
                  <tr>
                       <td colspan="3" nowrap class="2-td2-left">
                       <div align="left"><ttsoft:write name="item" property="xmmc"/>
                       <input name="hc" type="hidden" value="<ttsoft:write name="item" property="hc"/>" id="hc_<%=hc%>"><input name="xmmc" type="hidden" value="<ttsoft:write name="item" property="xmmc"/>" id="xmmc_<%=hc%>" >
                       </div>
                       </td>
                       <td colspan="1" nowrap class="2-td2-left">
                       <div align="center">2
                       </div>
                       </td>
                       <td colspan="2" nowrap class="2-td2-center">
                       <input name="lrze" type="text" value="<ttsoft:write name="item" property="lrze"/>" id="lrze" size="16"   onchange="lxxz2(this); " class="inputalignright" tabIndex="2">
                       </td>
                  </tr>
                  <%
                    }
                    if(hc.equals("3")){
                  %>
                  <tr>
                        <td colspan="3" nowrap class="2-td2-left">
                        <div align="left"><ttsoft:write name="item" property="xmmc"/>
                        <input name="hc" type="hidden" value="<ttsoft:write name="item" property="hc"/>" id="hc_<%=hc%>"><input name="xmmc" type="hidden" value="<ttsoft:write name="item" property="xmmc"/>" id="xmmc_<%=hc%>" >
                        </div>
                        </td>
                        <td colspan="1" nowrap class="2-td2-left">
                        <div align="center">3
                        </div>
                        </td>
                        <td colspan="2" nowrap class="2-td2-center">
                        <input name="mbyqndks" type="text" value="<ttsoft:write name="item" property="mbyqndks"/>" id="mbyqndks" size="16" class="inputalignright" onchange="lxxz3(this)"   tabIndex="2" >
                        </td>
                  </tr>
                  <%
                    }
                    if(hc.equals("4")){
                  %>
                  <tr>
                        <td colspan="3" nowrap class="2-td2-left">
                        <div align="left"><ttsoft:write name="item" property="xmmc"/>
                        <input name="hc" type="hidden" value="<ttsoft:write name="item" property="hc"/>" id="hc_<%=hc%>"><input name="xmmc" type="hidden" value="<ttsoft:write name="item" property="xmmc"/>" id="xmmc_<%=hc%>" >
                        </div>
                        </td>
                        <td colspan="1" nowrap class="2-td2-left">
                        <div align="center">4
                        </div>
                        </td>
                        <td colspan="2" nowrap class="2-td2-center">
                        <input name="bkhlrze" type="text" value="<ttsoft:write name="item" property="bkhlrze"/>" id="bkhlrze" size="16"  class="inbright" readOnly tabIndex="2">
			                </td>
                  </tr>
                  <%
                    }
                    if(hc.equals("5")){
                  %>
                  <tr>
                        <td colspan="3" nowrap class="2-td2-left">
                        <div align="left"><ttsoft:write name="item" property="xmmc"/>
                        <input name="hc" type="hidden" value="<ttsoft:write name="item" property="hc"/>" id="hc_<%=hc%>"><input name="xmmc" type="hidden" value="<ttsoft:write name="item" property="xmmc"/>" id="xmmc_<%=hc%>" >
                        </div>
                        </td>
                        <td colspan="1" nowrap class="2-td2-left">
                        <div align="center">5
                        </div>
                        </td>
                        <td colspan="2" nowrap class="2-td2-center">
                        <input name="sl" type="text" value="<ttsoft:write name="item" property="sl"/>" id="sl" size="16"  class="inbright" readOnly tabIndex="-1">
                  </td>
                </tr>
                <%
                  }
                  if(hc.equals("6")){
                %>
                <tr>
                        <td colspan="3" nowrap class="2-td2-left">
                        <div align="left"><ttsoft:write name="item" property="xmmc"/>
                        <input name="hc" type="hidden" value="<ttsoft:write name="item" property="hc"/>" id="hc_<%=hc%>"><input name="xmmc" type="hidden" value="<ttsoft:write name="item" property="xmmc"/>" id="xmmc_<%=hc%>" >
                        </div>
			                  </td>
                        <td colspan="1" nowrap class="2-td2-left">
                        <div align="center">6
                        </div>
                        </td>
                        <td colspan="2" nowrap class="2-td2-center">
                        <input name="ynsdse" type="text" value="<ttsoft:write name="item" property="ynsdse"/>" id="ynsdse" size="16" onchange="zuihoujisuan(this)" class="inbright" readOnly tabIndex="2">
                        </td>
                  </tr>
                  <%
                    }
                    if(hc.equals("7")){
                  %>
                  <tr>
                        <td colspan="3" nowrap class="2-td2-left">
                        <div align="left"><ttsoft:write name="item" property="xmmc"/>
                        <input name="hc" type="hidden" value="<ttsoft:write name="item" property="hc"/>" id="hc_<%=hc%>"><input name="xmmc" type="hidden" value="<ttsoft:write name="item" property="xmmc"/>" id="xmmc_<%=hc%>" >
                        </div>
			                  </td>
                        <td colspan="1" nowrap class="2-td2-left">
                        <div align="center">7
                        </div>
			                  </td>
                        <td colspan="2" nowrap class="2-td2-center">
                        <input name="qcwjsdse" type="text" value="<ttsoft:write name="item" property="qcwjsdse"/>" id="qcwjsdse" size="16" onchange="zuihoujisuan2(this)" class="inputalignright" tabIndex="2">
			                </td>
                  </tr>
                  <%
                    }
                    if(hc.equals("8")){
                  %>
                  <tr>
                        <td colspan="3" nowrap class="2-td2-left">
                        <div align="left"><ttsoft:write name="item" property="xmmc"/>
                        <input name="hc" type="hidden" value="<ttsoft:write name="item" property="hc"/>" id="hc_<%=hc%>"><input name="xmmc" type="hidden" value="<ttsoft:write name="item" property="xmmc"/>" id="xmmc_<%=hc%>" >
                        </div>
			                  </td>
                        <td colspan="1" nowrap class="2-td2-left">
                        <div align="center">8
                        </div>
                        </td>
                        <td colspan="2" nowrap class="2-td2-center">
                        <input name="jmsdse" type="text" value="<ttsoft:write name="item" property="jmsdse"/>" id="jmsdse" size="16" onchange="zuihoujisuan3(this)" class="inputalignright"  tabIndex="2">
			                  </td>
		              </tr>
                  <%
                    }
                    if(hc.equals("9")){
                  %>
                  <tr>
                        <td colspan="3" nowrap class="2-td2-left">
                        <div align="left"><ttsoft:write name="item" property="xmmc"/>
                        <input name="hc" type="hidden" value="<ttsoft:write name="item" property="hc"/>" id="hc_<%=hc%>"><input name="xmmc" type="hidden" value="<ttsoft:write name="item" property="xmmc"/>" id="xmmc_<%=hc%>" >
                        </div>
			                  </td>
                        <td colspan="1" nowrap class="2-td2-left">
                        <div align="center">9
                        </div>
			                  </td>
                        <td colspan="2" nowrap class="2-td2-center">
                        <input name="cbyqndse" type="text" value="<ttsoft:write name="item" property="cbyqndse"/>" id="cbyqndse" size="16" onchange="zuihoujisuan3(this)" class="inputalignright" tabIndex="2">
			                  </td>
                  </tr>
                    <%
                      }
                      if(hc.equals("10")){
                    %>
                  <tr>
                        <td colspan="3" nowrap class="2-td2-left">
                        <div align="left"><ttsoft:write name="item" property="xmmc"/>
                        <input name="hc" type="hidden" value="<ttsoft:write name="item" property="hc"/>" id="hc_<%=hc%>"><input name="xmmc" type="hidden" value="<ttsoft:write name="item" property="xmmc"/>" id="xmmc_<%=hc%>" >
                        </div>
			                  </td>
                        <td colspan="1" nowrap class="2-td2-left">
                        <div align="center">10
                        </div>
			                  </td>
                        <td colspan="2" nowrap class="2-td2-center">
                        <input name="sjyjsdsse" type="text" value="<ttsoft:write name="item" property="sjyjsdsse"/>" id="sjyjsdsse" size="16" onchange="zuihoujisuan3(this)" class="inputalignright" tabIndex="2">
			                  </td>
		              </tr>
						<%
						}
						if(hc.equals("11")){
					  %>
					  <tr>
							<td colspan="3" nowrap class="2-td2-left">
							<div align="left"><ttsoft:write name="item" property="xmmc"/>
							<input name="hc" type="hidden" value="<ttsoft:write name="item" property="hc"/>" id="hc_<%=hc%>"><input name="xmmc" type="hidden" value="<ttsoft:write name="item" property="xmmc"/>" id="xmmc_<%=hc%>" >
							</div>
								  </td>
							<td colspan="1" nowrap class="2-td2-left">
							<div align="center">11
							</div>
								  </td>
							<td colspan="2" nowrap class="2-td2-center">
							<input name="bqyjsdse" type="text" value="<ttsoft:write name="item" property="bqyjsdse"/>" id="bqyjsdse" size="16" onchange="zuihoujisuan3(this)" class="inputalignright" tabIndex="2">
								  </td>
					  </tr>
						<%
						  }
						  if(hc.equals("12")){
						%>
					  <tr>
                        <td colspan="3" nowrap class="2-td2-left">
                        <div align="left"><ttsoft:write name="item" property="xmmc"/>
                        <input name="hc" type="hidden" value="<ttsoft:write name="item" property="hc"/>" id="hc_<%=hc%>"><input name="xmmc" type="hidden" value="<ttsoft:write name="item" property="xmmc"/>" id="xmmc_<%=hc%>" >
                        </div>
			                  </td>
                        <td colspan="1" nowrap class="2-td2-left">
                        <div align="center">12
                        </div>
			                  </td>
                        <td colspan="2" nowrap class="2-td2-center">
                        <input name="sjybsdse" type="text" value="<ttsoft:write name="item" property="sjybsdse"/>" id="sjybsdse" size="16" class="inbright" readOnly tabIndex="2">
			                  </td>
		              </tr>
                    <%
                      }
                    %>
                 </logic:iterate>
					  <tr>
                        <td colspan="6" nowrap class="2-td2-center"><div align="left"><font color="red">
						 <bean:write name="qysdsjbForm" property="promptStr" filter="false"/>
						 </font></div>
						</td>
		              </tr>
                </table>


				<br>
               &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="image" accesskey="q" tabIndex="-1"  onClick=" doQuery();return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg ',1)" onMouseOut="MM_swapImgRestore()" value="查询" src="<%=static_contextpath%>images/cx-q1.jpg " width="79" height="22" id="chaxun" style="cursor:hand">
                   &nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="u" tabIndex="-1"  onClick="ql();return false; " onMouseOver="MM_swapImage('qingchu','',' <%=static_contextpath%>images/qc-u2.jpg ',1)" onMouseOut="MM_swapImgRestore()" src=" <%=static_contextpath%>images/qc-u1.jpg " alt="清除"  width="79" height="22" id="qingchu" style="cursor:hand" >
                   &nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="s" tabIndex="-1" onClick="doSave();return false;" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/bc-s1.jpg" value="保存"  width="79" height="22" id="baocun"  style="cursor:hand">
                   &nbsp;&nbsp;&nbsp;&nbsp;<input type="image" accesskey="x" tabIndex="-1" onClick="doDelete();return false;"onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="清除" src="<%=static_contextpath%>images/qbsc-x1.jpg" width="79" height="22" id="shanchu" style="cursor:hand">
                  &nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="c" tabIndex="-1" onClick="tuichu();return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor:hand" >
                   <br> <br> </td></tr>
          </table></td>
        </tr>

      </table>
</html:form>
<%@ include file="include/footer.jsp" %>
<SCRIPT LANGUAGE="JavaScript">
<!--
/****如果该纳税人为非正常户，则显示提示信息****/
/****20050817 HU xiaofeng****/
var nsrzt = <ttsoft:write name="qysdsjbForm" property="nsrzt" filter="false"/>;
checkNsrzt();
/*************end*****************/
</SCRIPT>
</body>
</html>


