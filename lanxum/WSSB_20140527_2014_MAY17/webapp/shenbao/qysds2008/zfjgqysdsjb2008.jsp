<%@ page contentType="text/html; charset=GBK"%>

<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ page import="com.ttsoft.common.model.UserData"%>
<%@ page import="java.util.HashMap"%>

<%
	//静态文件路径
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);

    String containerName = "";
    UserData userdata = (UserData)session.getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA);
    if (userdata.getCaflag())
    {
		containerName = userdata.getCert().getContainerName();
    }
    else
    {
		containerName = "";
    }
%>

<html>
<head>
<title>企业所得税汇总纳税分支机构分配表</title>
<link href="<%=static_contextpath%>/css/text.css" rel="stylesheet" type="text/css">
<style>
input {
    font-size: 9pt;
    text-align: right;
}

.text-gray {
 color: #3E6071;
 background-color: #EEEEEE;
}
</style>

<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<!--<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>-->
<script language="JavaScript" type="text/JavaScript" src="js/XmlBuild.js"></script>
</head>

<script language="JavaScript" type="text/javascript">

g_objSI.Container = "<%=containerName%>";
//xml串
var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var InputName=['lrze','nstzzje','nstzjse','mbyqndks','ynssde','sysl','ynsdse','jmsdse','hznscyqyjdyjbl','sjyyjdsdse','ybdsdse'];
var InputNameAl=['利润总额','加：纳税调整增加额','减：纳税调整减少额','减：弥补以前年度亏损数','应纳税所得额','适用税率','应纳所得税额','减免所得税额','汇总纳税成员企业就地预缴比例','实际已预缴的所得税额','应补（退）的所得税额'];
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMAVersion = '<%=request.getAttribute("CA_XML_SCHEMA_VERSION")==null?"":request.getAttribute("CA_XML_SCHEMA_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';

var mxrows = '<%=request.getAttribute("maxrows")==null?"":request.getAttribute("maxrows")%>';
var xmlDoc;


//解析xml
function parseXmlOnLoad()
{
    var urlXSL="/XSLTWEB/model/010030/XSLT/" +strXSLTVersion+".xsl";
    //var urlXSL = "/XSLTWEB/model/010028/zfjgqysdsjb.xsl";
    if (strXml != "")
    {
        if (! parseXml(strXml,urlXSL,"result"))
        return false;
        xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
        xmlDoc.async = false;
        xmlDoc.loadXML(strXml);
    }
	//设置跨行单元格的跨行数+1
	var fzjgqk = document.getElementById("fzjgqk");
	//alert("fzjgqk rowspan = " + fzjgqk.rowSpan);
	//alert("fzjgqk rowspanaaa = " + document.all.fzjgqk.rowSpan);
	fzjgqk.rowSpan = mxrows + 2;
	//根据取出的数据重新计算页面分支机构分摊税额
	//compute_row_17();
    setFoucs();

    return true;
}

<%/*响应计算机代码的回车查询*/%>
function jsjdmQuery(){
    if(event.keyCode==13) event.keyCode = 9;
}

<%/*设置输入计算机代码时的焦点变化*/%>
function setFoucs(){
    //alert(document.forms[0].nsrsbh.value);
    if(document.getElementById("1_1").value == null || document.getElementById("1_1").value == ""){
        window.event.returnValue=false;
        document.getElementById("1_1").focus();
    }
}

<%/*增加行*/%>
function doAddRow()
{
    //获取已有分支机构明细信息数量
    var mxnum = document.forms[0].fzjgnsrsbh.length;
    //alert("mxnum = " + mxnum);

    //获取需要添加行的表格
    var table = document.getElementById("table_30");

    //然后创建行(TR对象)
    var NewTr = document.createElement("tr");
    //填充该表格行
    for(var i=10; i<18; i++)
    {
        var NewTd;
        if(i == 17)
        {
            NewTd = document.createElement("<td align=left nowrap class='2-td2-center'>");
        }
        else
        {
            NewTd = document.createElement("<td nowrap class='2-td2-left' align=left>");
        }
        var end = "." + (mxnum + 1) + "_1";
        var id = i + end;
        //alert("id = " + id);
        switch(i)
        {
            case 10:
				NewTd.innerHTML = "<td><input type='text' name='fzjgnsrsbh' id=" + id + " size='19' maxlength='18' onblur='checkNsrsbh(\"" + end + "\")' style='text-align:left'></td>";
					//alert(NewTd.innerHTML);
				break;
			case 11:
				NewTd.innerHTML = "<td nowrap class='2-td2-left' align=left><input type='text' name='fzjgmc' id=\"" + id + "\" size='19' style='text-align:left'></td>";
				break;
			case 12:
				NewTd.innerHTML = "<td nowrap class='2-td2-left' align=left><input type='text' name='fzjgsrze' id=\"" + id + "\" style='text-align:right' value='0.00' size='13' onblur='formate(this)' onchange='compute_Row_15(\"" + end + "\")'></td>";
				break;
			case 13:
				NewTd.innerHTML = "<td nowrap class='2-td2-left' align=left><input type='text' name='fzjggzze' id=\"" + id + "\" style='text-align:right' value='0.00' size='13' onblur='formate(this)' onchange='compute_Row_15(\"" + end + "\")'></td>";
				break;
			case 14:
				NewTd.innerHTML = "<td nowrap class='2-td2-left' align=left><input type='text' name='fzjgzcze' id=\"" + id + "\" style='text-align:right' value='0.00' size='13' onblur='formate(this)' onchange='compute_Row_15(\"" + end + "\")'></td>";
				break;
			case 15:
				NewTd.innerHTML = "<td nowrap class='2-td2-left' align=left><input type='text' name='fzjghj' id=\"" + id + "\" style='text-align:right' value='0.00' size='13' readonly='true' class='text-gray'></td>";
				break;
			case 16:
				NewTd.innerHTML = "<td align=left nowrap class='2-td2-left'><input type='text' name='fzjgfpbl' id=\"" + id + "\" style='text-align:right' value='0' size='5' onblur='checkPercent(this)'></td>";
				break;
			case 17:
				NewTd.innerHTML = "<td align=left nowrap class='2-td2-center'><input type='text' name='fzjgfpse' id=\"" + id + "\" style='text-align:right' value='0.00' size='13' onblur=\"checkInput(this)\"></td>";
				break;
        }
        //alert(NewTd.innerHTML);
        //往新行里面填充单元格
        NewTr.appendChild(NewTd);
    }
    //添加行
    var LastTr = table.rows[table.rows.length - 1];

    LastTr.parentNode.appendChild(NewTr);


    //设置跨行单元格的跨行数+1
    var fzjgqk = document.getElementById("fzjgqk");
    //alert("fzjgqk rowspan = " + fzjgqk.rowSpan);
    //alert("fzjgqk rowspanaaa = " + document.all.fzjgqk.rowSpan);
    fzjgqk.rowSpan += 1;
    //alert("fzjgqk rowspan = " + fzjgqk.rowSpan);
}

<%/*保存*/%>
function doSave()
{
    if(document.forms[0].nsrsbh.value == "" || document.forms[0].nsrsbh.value == null){
        alert("基本信息不正确,请重新输入!");
        return false;
    }

    if(!saveCheck())
    return;

    var old = document.forms[0].ywczlx.value;
    //alert("old = " + old);

    SaveExec(old);

    //doSubmitForm('Save');
}

function SaveExec(old)
{
    //alert("SaveExec");
    if (document.forms[0].ywczlx.value == 0)
    {
        document.forms[0].ywczlx.value = 1;
    }
    else if (document.forms[0].ywczlx.value == 1)
    {
        document.forms[0].ywczlx.value = 2;
    }
    else
    {
        alert("未知的操作类型：" + document.forms[0].ywczlx.value);
        return false;
    }
    document.forms[0].actionType.value="Save";
    //var xml = getPostXml(document.forms[0]);
    //alert(xml);
    if (g_objSI.Container != '')
    {
        if (!doSubmitXML(document.forms[0],"Save",true,"",true))
        {
            document.forms[0].ywczlx.value = old;
            return;
        }
    }
    else
    {
        if (!doSubmitXML(document.forms[0],"Save",false,"",true))
        {
            document.forms[0].ywczlx.value = old;
            return;
        }
    }
    //return true;
}

<%/*审核*/%>
function doCheck()
{
    doSubmitForm('Check');
}

<%/*清除*/%>
function doReset()
{
    if(confirm("确认是否清除当前页面数据？"))
    {
        //清除总机构数据
        for(var i=1; i<10; i++)
        {
			//分摊税额不能清除
			if(i == 9)
			{
				continue;
			}
            //排除带出的总机构信息
            if(i != 3 && i!= 4)
            {
                obj = eval("document.getElementById('" + i+"_1')");
                if(i < 5)
                {
                    obj.value = "";
                }
                else if(i >= 5)
                {
                    obj.value = "0.00";
                }
            }
        }

        // 清除分支机构明细信息
        var nsrsbhArrLength = document.forms[0].fzjgnsrsbh.length;
        for(var j=10; j < 18; j++)
        {
            for(var k=0; k<nsrsbhArrLength; k++)
            {
                obj = eval("document.getElementById('" + j + "." + (k+1) + "_1')");
                //分支机构纳税人信息
                if(j < 12)
                {
                    obj.value = "";
                }
                //分配比例信息
                else if(j == 16)
                {
                    obj.value = "0";
                }
                else
                {
                    obj.value = "0.00";
                }
            }
        }
    }
    setFoucs();
}

<%/*删除*/%>
function doDelete()
{
    var old  = document.forms[0].ywczlx.value;
    if((document.forms[0].ywczlx.value == 0) && confirm(confirmDelete))
    {
        document.forms[0].actionType.value="Delete";
        //将业务操作类型置为删除状态
        changeLocalYwczlx("3");

        if (g_objSI.Container != '')
        {
            if (!doSubmitXML(document.forms[0],"Delete",true,xmlDoc.xml,true))
            {
                changeLocalYwczlx(old);
                return false;
            }
        }
        else
        {
            if (!doSubmitXML(document.forms[0],"Delete",false,xmlDoc.xml,true))
            {
                changeLocalYwczlx(old);
                return false;
            }
        }
        return true;
    }
    else
    {
        return false;
    }
}

<%/*返回*/%>
function doReturn()
{
    if(confirm(confirmReturn))
    {
        document.forms[0].actionType.value="Return";
        //document.forms[0].submit();
		document.forms["zfjgForm"].submit();
        return true;
    }
    else
    {
        return false;
    }
}

<%/*计算总机构三项因素合计数*/%>
function compute_row_8()
{

    //判断输入的数据是否符合要求
    if(!isNum(getCellObject() , null, null, null, null, 2)){
        return false;
    }

    //根据5、6、7行输入的数据判断第8行数据是否要改动
    var row_5=convertSpace2Zero(document.getElementById("5_1").value);

    var row_6=convertSpace2Zero(document.getElementById("6_1").value);
    var row_7=convertSpace2Zero(document.getElementById("7_1").value);
    //alert("parseFloat(row_5) = " + parseFloat(row_5) + "\nparseFloat(row_6) = " + parseFloat(row_6) + "\nparseFloat(row_7) = " + parseFloat(row_7));

    //计算并格式化第8行的值
    var obj = document.getElementById("8_1");
    var temp8 = parseFloat(row_5) + parseFloat(row_6) + parseFloat(row_7);
    //obj.value=parseFloat(temp8);
    obj.value = parseFloat(temp8).toFixed(2);
    formate(obj);
	/*
			根据2008-3-21测试结果要求,取消合计数非0校验;取消非配比例、分配税额自动计算
    //alert("11111111");
    if(temp8 <= 0)
    {
        alert("总机构合计数必须大于0，请核实并修改！");
        return;
    }

    //根据输入的总机构合计数反算分支机构分配比例、分配数额
    compute_row_16_by_row_8();
	*/
}

<%/*根据输入的总机构合计数反算分支机构分配比例、分配数额*/%>
function compute_row_16_by_row_8()
{
    var nsrsbhArr = document.forms[0].fzjgnsrsbh;
    //alert("nsrsbhArr.length = " + nsrsbhArr.length);
    for(i=0; i<nsrsbhArr.length; i++)
    {
        var nsrsbh = nsrsbhArr[i].value;
        //alert("nsrsbh_" + (i + 1) + " = " + nsrsbh);
        if(nsrsbh != null && nsrsbh != "")
        {
            //拼当前行的后缀
            var id = "." + (i + 1) + "_1";
            //alert("aaa id = " + id);
            compute_row_16(id);
        }
        else
        {
            continue;
        }
    }
}

<%/*计算各分机构构三项因素合计数、分配比例、分配税额*/%>
function compute_Row_15(id)
{
    //判断输入的数据是否符合要求
    if(!isNum(getCellObject() , null, null, null, null, 2)){
        return false;
    }

    //alert("id = " + id);

    //根据12(收入总额)、12(工资总额)、14(资产总额)行输入的数据判断第15(合计)行数据是否要改动
    var row_12 = convertSpace2Zero(document.getElementById("12" + id).value);
    var row_13 = convertSpace2Zero(document.getElementById("13" + id).value);
    var row_14 = convertSpace2Zero(document.getElementById("14" + id).value);
    //alert("row_12 = " + row_12);

    //计算并格式化第15行的值
    var obj = document.getElementById("15" + id);
    var temp15 = parseFloat(row_12) + parseFloat(row_13) + parseFloat(row_14);
    //alert("temp15 = " + temp15);
    //obj.value=parseFloat(temp8);
    obj.value = parseFloat(temp15).toFixed(2);
    formate(obj);
	/*
			根据2008-3-21测试结果要求,取消合计数非0校验;取消非配比例、分配税额自动计算
    if(temp15 <= 0)
    {
        alert("各分支机构合计数必须大于0，请核实并修改！");
        return;
    }
	

    //计算对应行16分配比例
    compute_row_16(id);
	*/
    //计算行17(分配税额)
    //compute_row_17(id);
}

<%/*计算各分机构构分配比例*/%>
function compute_row_16(id)
{
    //alert("compute_row_16_id = " + id);
    var obj_16 = document.getElementById("16" + id);
    var obj_17 = document.getElementById("17" + id);
    var temp;
    //alert("obj_16 = " + obj_16.value);
    //判断输入的数据是否符合要求
    if(!isNum(getCellObject() , null, null, null, null, 2)){
        return false;
    }

    //根据总机构合计数计算分配比例、分配税额
    var row_8 = convertSpace2Zero(document.getElementById("8_1").value);
    //alert(parseFloat(row_8));
    if(parseFloat(row_8) == 0)
    {
        //总结构合计金额为0,则对应分支机构的分配比例为0
        obj_16.value = 0;
        //formate(obj_16);

        obj_17.value = 0;
        formate(obj_17);
    }
    else
    {
        //获取当前分支机构合计数
        var row_15 = convertSpace2Zero(document.getElementById("15" + id).value);
        temp = parseFloat((Math.abs(parseFloat(row_15)) / Math.abs(parseFloat(row_8))).toFixed(4));
        obj_16.value = parseFloat(temp);
        formate2Parcent(obj_16, obj_16.value);

        //总机构分摊税额
        var row_9 = convertSpace2Zero(document.getElementById("9_1").value);
        //alert("row_9 = " + row_9 + "\nobj_17 = " + obj_17.value);
        obj_17.value = parseFloat(parseFloat(row_9) * temp).toFixed(2);
        formate(obj_17);
    }
}

<%/*计算各分机构构分配税额*/%>
function compute_row_17()
{
	//alert("compute_row_17");
    //总机构分摊税额
	var row_9 = convertSpace2Zero(document.getElementById("9_1").value);
	var fzjg = document.forms[0].fzjgnsrsbh;
	for(var i=0; i<fzjg.length; i++)
	{
		if(fzjg[i].value != null && fzjg[i].vlaue != "")
		{
			//分支机构分配比例
			var row_16 = document.forms[0].fzjgfpbl[i];
			var fpbl = formateParcent2Num(row_16);
			//分支机构分配税额
			var row_17 = document.forms[0].fzjgfpse[i];
			row_17.value = parseFloat(parseFloat(row_9) * fpbl).toFixed(2);
			formate(row_17);
		}
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

<%/*判断比较税款所属日期*/%>
function compareDate(obj){

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

}

<%/*判断比较分配比例有效期*/%>
function compareFpblyxq(obj){
	
    var strDate1 = document.forms[0].fpblyxqq.value;
    var strYear1 = strDate1.substr(0,4);
    var strMonth1 = strDate1.substr(4,2);
    var strDay1 = strDate1.substr(6,2);

    var strDate2 = document.forms[0].fpblyxqz.value;
    var strYear2 = strDate2.substr(0,4);
    var strMonth2 = strDate2.substr(4,2);
    var strDay2 = strDate2.substr(6,2);

    var sksbrq = document.forms[0].sbrq.value;
    var strYear3 = sksbrq.substr(0,4);
    var strMonth3 = sksbrq.substr(5,2);
    var strDay3 = sksbrq.substr(8,2);

	//alert("strDate1 = " + (strMonth1+'-'+strDay1+'-'+strYear1) + "\nstrDate2 = " + (strMonth2+'-'+strDay2+'-'+strYear2) + "\nsksbrq = " + (strMonth3+'-'+strDay3+'-'+strYear3));

    var objDate1 = new Date(strMonth1+'-'+strDay1+'-'+strYear1);
    var objDate2 = new Date(strMonth2+'-'+strDay2+'-'+strYear2);
    var objDate3 = new Date(strMonth3+'-'+strDay3+'-'+strYear3);
	//var objDate3 = new Date(sksbrq);
	//alert("objDate1 = " + objDate1);

    //if(objDate2>objDate3){
        //alert("分配比例有效期末不能大于当前日期");
        //window.event.returnValue=false;
        //document.forms[0].fpblyxqz.focus();
        //document.forms[0].fpblyxqz.select();
        //return false;
    //}

    //if(objDate1>=objDate2){
        //if(obj == document.forms[0].fpblyxqz){
            //alert("分配比例有效期末不能小于或等于分配比例有效期初");
        //}else{
            //alert("分配比例有效期初不能大于或等于分配比例有效期末");
        //}
       // window.event.returnValue=false;
        //obj.focus();
       // obj.select();
        //return false;
    //}
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
        alert("税款结束时间不能大于当前日期");
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

<%/*将空格转换为零，用于其他函数调用*/%>
function convertSpace2Zero(inputValue){
    return inputValue==""?"0":inputValue;
}

<%/*保存时对页面数据进行校验*/%>
function saveCheck()
{
    //检验分配比例有效期是否已填写
    var fpblyxqq = document.getElementById("1_1").value;
    var fpblyxqz = document.getElementById("2_1").value;
    //alert("fpblyxqq = " + fpblyxqq + "\nfpblyxqz = " + fpblyxqz);
    if(fpblyxqq == null || fpblyxqq == "" || fpblyxqz == null || fpblyxqz == "")
    {
        alert("分配比例有效期为必填项，请填写后保存！");
        if(fpblyxqq == null || fpblyxqq == "")
        {
            document.getElementById("1_1").focus();
            return false;
        }
        if(fpblyxqz == null || fpblyxqz == "")
        {
            document.getElementById("2_1").focus();
            return false;
        }
    }

    //校验总机构合计数是否等于分支机构合计数之和
	/* 根据2008-3-21测试结果,取消总机构合计非负、分支机构各项合计非负，总机构合计等于各项分支机构合计数之和限制
    var zjghj = document.getElementById("8_1").value;
    if(zjghj <= 0)
    {
        alert("总机构合计数必须大于0，请核实并修改！");
        return false;
    }
	*/
    //alert("zjghj = " + zjghj);
    var fzjghj = 0;
    var fzjg = document.forms[0].fzjghj;
    var fzjgnsrsbh = document.forms[0].fzjgnsrsbh;
    var fzjgmc = document.forms[0].fzjgmc;
    //alert("fzjg.length = " + fzjg.length);
    for(i=0; i<fzjg.length; i++)
    {
        //alert("i = " + i);
        //alert("parseFloat(fzjg[i].value) = " + parseFloat(fzjg[i].value) + "\nfzjgnsrsbh[i].value = " + fzjgnsrsbh[i].value + "\nfzjgnsrmc[i].value = " + fzjgnsrmc[i].value);
        if(fzjgnsrsbh[i].value != null && fzjgnsrsbh[i].value != "" && fzjgmc[i].value != null && fzjgmc[i].value != "")
        {
            //alert("11111111");
            /*if(fzjg[i].value <= 0)
            {
                alert("第" + (i + 1) + "行分支机构合计数必须大于0，请核实并修改！");
                return false;
            }
            else
            {
                fzjghj = parseFloat(fzjghj) + parseFloat(fzjg[i].value);
            }*/
        }
        else
        {
            //alert("22222222");
            if(parseFloat(fzjg[i].value) != 0)
            {
                alert("请将第" + (i + 1) + "行分支机构相关信息补充完整后保存！");
                if(fzjgnsrsbh[i].value == null || fzjgnsrsbh[i].value == "")
                {
                    fzjgnsrsbh[i].focus();
                    return false;

                }
                if(fzjgmc[i].value == null || fzjgmc[i].value == ""){
                    fzjgmc[i].focus();
                    return false;
                }
            }
        }
		
		/*        //判断当前需要提交的分支机构分配税额与计划值（总机构分摊税额*分配比例）是否相等，不等则提示修改
        var cur_ftse = document.getElementById("9_1").value;
        //alert("cur_ftse = " + document.forms[0].fzjgfpbl[i].value);
        var cur_mxfpbl = formateParcent2Num(document.forms[0].fzjgfpbl[i]);
        //alert("cur_mxfpbl = " + cur_mxfpbl);
        var cur_mxftse = document.forms[0].fzjgfpse[i].value;
        //alert("cur_ftse = " + cur_ftse + "\ncur_mxfpbl = " + cur_mxfpbl + "\ncur_mxftse = " + cur_mxftse);
        if(parseFloat(cur_ftse * cur_mxfpbl).toFixed(2) != cur_mxftse)
        {
            alert("第" + (i + 1) + "行分支机构分配税额不符合分配税额对应比例，请修改后保存！\n当前分摊税额x分配比例 = " + parseFloat(cur_ftse * cur_mxfpbl).toFixed(2) + "\n当前分支机构分配税额 = " +　cur_mxftse);
            document.forms[0].fzjgfpse[i].focus();
            document.forms[0].fzjgfpse[i].select();
            return false;
        }
		*/
    }
    //alert("parseFloat(fzjghj) = " + parseFloat(fzjghj) + "\nparseFloat(zjghj) = " + parseFloat(zjghj));
	/*
    if((parseFloat(fzjghj) - parseFloat(zjghj)) != 0)
    {
        alert("各分支机构合计数之和与总机构合计数不等，请核实修改后再保存！");
        return false;
    }
	*/

    //校验分支机构分配比例
    /*var fpbl = document.forms[0].fzjgfpbl;
    var fpblhj = 0;
    for(var j=0; j<fpbl.length; j++)
    {
        //alert("parseFloat(fpbl[j].value) = " + parseFloat(fpbl[j].value));
        fpblhj = parseFloat(fpblhj) + parseFloat(fpbl[j].value);
    }
    //alert("parseFloat(fpblhj) = " + parseFloat(fpblhj));
    if(parseFloat(zjghj) == 0 && parseFloat(fpblhj) != 0)
    {
        //总机构合计数为0,则各项分配比例均为0
        return false;
    }
    if(parseFloat(zjghj) != 0 && ((parseFloat(fpblhj) - 1) != 0))
    {
        //总机构合计数不为0,则各项分配比例数合计为1
        alert("各分支机构分配比例之和不等于100%，请核实修改后再保存！");
        return false;
    }

    //校验分摊税额关系
    var zjgftse = document.forms[0].ftse.value;
    var fpsehj = 0;
    var fpse = document.forms[0].fzjgfpse;
    for(var k=0; k<fpse.length; k++)
    {
        fpsehj = parseFloat(fpsehj) + parseFloat(fpse[k].value);
    }
    if((parseFloat(fpsehj) - parseFloat(zjgftse)) != 0)
    {
        alert("各分支机构分配税额合计与分支机构分摊说的税额不等，请核实修改后再保存！");
    }
    */


    return true;
}

function formatData(obj)
{
    return (checkvalue(obj,1) && formatCurrency(obj,0) );
}

function changeLocalYwczlx(ywczlx)
{
    //alert("ywczlx = " + ywczlx);
    var rootNode = xmlDoc.documentElement;
    var objCDATA =xmlDoc.createCDATASection(ywczlx);
    rootNode.selectSingleNode("//ywczlx").text = "";
    rootNode.selectSingleNode("//ywczlx").appendChild(objCDATA);
}

function checkInput(obj)
{
    //判断输入的数据是否符合要求
    if(!isNum(getCellObject() , null, null, null, null, 2)){
        return false;
    }
    //格式化数据
    formate(obj);
}

<%//校验分配比例录入域%>
function checkPercent(obj)
{
	var temp = obj.value;
	var end = temp.substring(temp.length-1);		
	//alert("end = " + end);
	//如果数据为%结尾，则去掉%号后处理
	if(end == "%")
	{
		temp = temp.substring(0, (temp.length-1));
	}
	obj.value = temp;
	//判断输入的数据是否符合要求
	if(!isNum(obj, null, null, null, null, 2)){
		return false;			
	}

	formateParcent(obj);
}

//判断纳税人识别号是否由数字或字符组成
function checkNsrsbh(id)
{
	//alert("id = " + id);
	var obj = document.getElementById("10" + id);
	var nsrsbh = obj.value;
	/*if(nsrsbh.length == 0)
	{
		alert("请输入纳税人识别号!");
		obj.focus();
		return;
	}*/

    for(var loc=0;loc<nsrsbh.length;loc++)
	{
		//alert("loc = " + loc);
		//alert("nsrsbh.chatAt(loc) = " + nsrsbh.charAt(loc));
		if(!((('a'<=nsrsbh.charAt(loc)) && (nsrsbh.charAt(loc)<='z')) || (('A'<=nsrsbh.charAt(loc)) && (nsrsbh.charAt(loc)<='Z')) || (('0'<=nsrsbh.charAt(loc)) && (nsrsbh.charAt(loc)<='9'))))
		{
			alert("纳税人识别号只能为数字或字符，请重新输入!");
			obj.focus();
			obj.select();
			return;
		}
	}
}

//跳转查帐征收页面
function jumpToCZZS(){
	//document.forms[0].ActionType="Jump";
	//alert("此处需要填写转入企业所得税汇总纳税分支机构分配表》功能的链接");
	if(confirm("确定将返回查帐征收申报页面，但不会保存当前填报数据．是否继续？"))
	{
		document.forms[0].actionType.value="Jump";
		document.forms[0].submit();	
	}
}
</script>


<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="return parseXmlOnLoad();">
<br>
<table width="96%" align="center" cellspacing="0" class="table-99" onKeyDown="jsjdmQuery()">
	<tr>
		<td align="center" valign="top">
			<jsp:include page="../../include/SbHeader.jsp" flush="true">
				<jsp:param name="name" value="企业所得税汇总纳税分支机构分配表" />
				<jsp:param name="help_url" value="help/wssb/sbzl/lygfzwhztz/qysdsjd/qysdsjd-000.htm" />
			</jsp:include>
		</td>
	</tr>
	<form id="zfjgForm" name="zfjgForm" action="zfjgqyjb2008.dc" method="post">
		<input type="hidden" name="org.apache.struts.taglib.html.TOKEN" value="6b35a7b97d7fa2ddc8df56c592663bf5">
		<input name="actionType" type="hidden" id="actionType" value="Show">
	<tr>
		<td><br>
				<div id="result" align="center"></div>
		</td>
	</tr>
	</form>
	<tr>
		<td>
			<table class="table-99" align="center">
				<BR>
				<TR class="black9">
				  <TD> <div align="center">
					  <input type="image" accesskey="a" onClick="doAddRow();return false;" onMouseOver="MM_swapImage('zengjia','','<%=static_contextpath%>images/zj-a2.jpg ',1)" onMouseOut="MM_swapImgRestore()" alt="增加行" src="<%=static_contextpath%>images/zj-a1.jpg " width="79" height="22" id="zengjia" style="cursor:hand">
					  &nbsp;&nbsp;&nbsp;&nbsp;
					  <input type="image" accesskey="s" style="cursor:hand" onClick="doSave();return false;" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="保存" src="<%=static_contextpath%>images/bc-s1.jpg" name="Image13" width="79" height="22" border="0" id='baocun'>
					  &nbsp;&nbsp;&nbsp;&nbsp;
					  <input type="image" accesskey="x" style="cursor:hand" onClick="doDelete();return false;" onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="删除" src="<%=static_contextpath%>images/qbsc-x1.jpg" name="Image13" width="79" height="22" border="0" id='shanchu'>
					  &nbsp;&nbsp;&nbsp;&nbsp;
					  <input type="image" accesskey="u" style="cursor:hand" onClick="doReset();return false;" onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="清除" src="<%=static_contextpath%>images/qc-u1.jpg" name="Image132" width="79" height="22" border="0" id='qingchu'>
					  &nbsp;&nbsp;&nbsp;&nbsp;
					  <input type="image" onClick="jumpToCZZS();return false;" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="返回查帐征收申报页面" value="返回" id="fh1" src="<%=static_contextpath%>images/fanhui1.jpg" width="79" height="22" style="cursor:hand" tabIndex="-1">
					  &nbsp;&nbsp;&nbsp;&nbsp;
					  <input type="image" onClick="doReturn();return false;" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor:hand" tabIndex="-1">
					</div>
					<BR> </TD>
				</TR>
			</TABLE>
		</td>
	</tr>
</table>

	<br>
	<br>
	<br>

	<jsp:include page="../../include/bottom.jsp" flush="true"/>

</body>
</html>
