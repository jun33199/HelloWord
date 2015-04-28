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
var dataFromA = '<%=request.getAttribute("DataFromA")==null?"":request.getAttribute("DataFromA")%>';
var a_nsfs,a_zfjg;
var xmlDoc;

//alert("dataFromA==="+dataFromA);
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

	splitData();

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

/**
* Notes: 增加行
* Authors: Guoxh,2012-3-1,将appendChild更改为insertRow方式
**/
function doAddRow()
{
    //获取已有分支机构明细信息数量
    var mxnum = document.forms[0].fzjgnsrsbh.length;
    //alert("mxnum = " + mxnum);

    //获取需要添加行的表格
    var table = document.getElementById("table_30");

    //然后创建行(TR对象)
    //var NewTr = document.createElement("tr");
	var NewTr = table.insertRow(table.rows.length - 1);
    //填充该表格行
    for(var i=12; i<19; i++)
    {
        var NewTd;
        if(i == 18)
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
            case 12:
				NewTd.innerHTML = "<td><input type='text' name='fzjgnsrsbh' id=" + id + " size='19' maxlength='18' onblur='checkNsrsbh(\"" + end + "\")' style='text-align:left'></td>";
					//alert(NewTd.innerHTML);
				break;
			case 13:
				NewTd.innerHTML = "<td nowrap class='2-td2-left' align=left><input type='text' name='fzjgmc' id=\"" + id + "\" size='20' style='text-align:left'></td>";
				break;
			case 14:
				NewTd.innerHTML = "<td nowrap class='2-td2-left' align=left><input type='text' name='fzjgsrze' id=\"" + id + "\" style='text-align:right' value='0.00' size='13' onblur='formate(this)' onchange='compute_Row_7(\"fzjgsrze\",\"srehj\")'></td>";
				break;
			case 15:
				NewTd.innerHTML = "<td nowrap class='2-td2-left' align=left><input type='text' name='fzjggzze' id=\"" + id + "\" style='text-align:right' value='0.00' size='13' onblur='formate(this)' onchange='compute_Row_8(\"fzjggzze\",\"gzehj\")'></td>";
				break;
			case 16:
				NewTd.innerHTML = "<td nowrap class='2-td2-left' align=left><input type='text' name='fzjgzcze' id=\"" + id + "\" style='text-align:right' value='0.00' size='13' onblur='formate(this)' onchange='compute_Row_9(\"fzjgzcze\",\"zcehj\")'></td>";
				break;
			case 17:
				NewTd.innerHTML = "<td align=left nowrap class='2-td2-left'><input type='text' name='fzjgfpbl' id=\"" + id + "\" style='text-align:right' value='0' size='5' onblur='checkPercent(this)' onchange='compute_Row_10(\"fzjgfpbl\",\"fpblhj\")'></td>";
				break;
			case 18:
				NewTd.innerHTML = "<td align=left nowrap class='2-td2-center'><input type='text' name='fzjgfpse' id=\"" + id + "\" style='text-align:right' value='0.00' size='13' onblur=\"checkInput(this)\" onchange='compute_Row_11(\"fzjgfpse\",\"fpsehj\")'></td>";
				break;
        }
        //alert(NewTd.innerHTML);
        //往新行里面填充单元格
        NewTr.appendChild(NewTd);
    }
	/*modified by guoxh,2012-3-1
    //添加行
    var LastTr = table.rows[table.rows.length - 1];

    LastTr.parentNode.appendChild(NewTr);
	*/


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
	if(document.forms[0].nsrmc.value == "" || document.forms[0].nsrmc.value == null){
        alert("总机构基本信息中：“纳税人名称”不能为空,请重新输入!");
        return false;
    }
    
    if(document.forms[0].nsrsbh.value == "" || document.forms[0].nsrsbh.value == null){
        alert("总机构基本信息中：“纳税人识别号”不能为空,请重新输入!");
        return false;
    }
    
    if(document.forms[0].ynsdse.value == "" || document.forms[0].ynsdse.value == null){
        alert("总机构基本信息中：“应纳所得税额”不能为空,请重新输入!");
        return false;
    }
    
    if(document.forms[0].ftsdse.value == "" || document.forms[0].ftsdse.value == null){
        alert("总机构基本信息中：“总机构分摊所得税额”不能为空,请重新输入!");
        return false;
    }
    
    if(document.forms[0].fpsdse.value == "" || document.forms[0].fpsdse.value == null){
        alert("总机构基本信息中：“总机构财政集中分配所得税额”不能为空,请重新输入!");
        return false;
    }
    
    if(document.forms[0].fzjgftse.value == "" || document.forms[0].fzjgftse.value == null){
        alert("总机构基本信息中：“分支机构分摊的所得税额”不能为空,请重新输入!");
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
	var arrs = dataFromA.split(";");
	if(arrs.length == 10){
		a_nsfs = arrs[0].split(":")[1];
		a_zfjg = arrs[1].split(":")[1];
	}
	
    if(confirm("确认是否清除当前页面数据？"))
    {
        if(a_nsfs=="1" && a_zfjg=="1"){
        	
        	//清除合计数据
	        for(var i=7; i<12; i++)
	        {
				obj = eval("document.getElementById('" + i+"_1')");
                
				if(i == 10)
                {
                    obj.value = "0";
                }
                else
                {
                    obj.value = "0.00";
                }
	        }
	         // 清除分支机构明细信息
	        var nsrsbhArrLength = document.forms[0].fzjgnsrsbh.length;
	        for(var j=12; j < 19; j++)
	        {
	            for(var k=0; k<nsrsbhArrLength; k++)
	            {
	                obj = eval("document.getElementById('" + j + "." + (k+1) + "_1')");
	                //分支机构纳税人信息
	                if(j < 14)
	                {
	                    obj.value = "";
	                }
	                //分配比例信息
	                else if(j == 17)
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
        
        if(a_nsfs=="1" && a_zfjg=="2"){
        	//清除总机构与合计数据
	        for(var i=1; i<12; i++)
	        {	     
				//分支机构分摊的所得税额不能清除
				if(i == 6)
				{
					continue;
				}
	            obj = eval("document.getElementById('" + i+"_1')");
	            
	            if(i < 3)
                {
                    obj.value = "";
                }
                else if(i == 10)
                {
                
                    obj.value = document.getElementById('17.1_1').value;
                    //alert("document.getElementById==="+document.getElementById('17.1_1').value);
                }
                else if(i == 11)
                {
                    obj.value = document.getElementById('18.1_1').value;
                    //alert("document.getElementById==="+document.getElementById('18.1_1').value);
                }
                else
                {
                    obj.value = "0.00";
                }
	        }
	        
	         // 清除分支机构明细信息
	        var nsrsbhArrLength = document.forms[0].fzjgnsrsbh.length;
	        for(var j=12; j < 19; j++)
	        {	
	            for(var k=0; k<nsrsbhArrLength; k++)
	            {
	            	//分支机构分摊的所得税额不能清除
	            	if(k==0)
					{
						obj = eval("document.getElementById('" + j + "." + (k+1) + "_1')");
						if(j>13 && j<17){
		                    obj.value = "0.00";
		                }
					}
					
					if(k>=1){
						obj = eval("document.getElementById('" + j + "." + (k+1) + "_1')");
		                //分支机构纳税人信息
		                if(j < 14)
		                {
		                    obj.value = "";
		                }
		                //分配比例信息
		                else if(j == 17)
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
        }
    }
    //document.forms[0].actionType.value="Reset";
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
		document.forms["zfjg2012Form"].submit();
        return true;
    }
    else
    {
        return false;
    }
}

//计算总机构三项因素之收入额的合计数
//modified by Guoxh,2012-3-5
function compute_Row_7(colName, colHjName)
{
	//alert("compute_Row_7");
	//判断输入的数据是否符合要求
    if(!isNum(getCellObject() , null, null, null, null, 2)){
        return false;
    }
    computeHj(colName, colHjName);
	//格式化
	formatData(document.all(colHjName));
}
function compute_Row_8(colName, colHjName)
{
	//判断输入的数据是否符合要求
    if(!isNum(getCellObject() , null, null, null, null, 2)){
        return false;
    }

    computeHj(colName, colHjName);
	//格式化
	formatData(document.all(colHjName));
}
function compute_Row_9(colName, colHjName)
{
	//判断输入的数据是否符合要求
    if(!isNum(getCellObject() , null, null, null, null, 2)){
        return false;
    }

    computeHj(colName, colHjName);
	//格式化
	formatData(document.all(colHjName));
}
//分配比例
function compute_Row_10(colName, colHjName)
{
	//判断输入的数据是否符合要求
    if(!isNum(getCellObject() , null, null, null, null, 2)){
        return false;
    }

    computeFpblHj(colName, colHjName);
	checkPercent(document.all(colHjName));
}
function compute_Row_11(colName, colHjName)
{
	//判断输入的数据是否符合要求
    if(!isNum(getCellObject() , null, null, null, null, 2)){
        return false;
    }
    computeHj(colName, colHjName);
	//格式化
	formatData(document.all(colHjName));
}

/**
* Notes: 根据指定的列名称，计算该列的合计
* Parameters: 
*  colName列名称
*    收入额：fzjgsrze；工资额：fzjggzze；资产额：fzjgzcze
*    分配比例：fzjgfpbl；分配税额：fzjgfpse
*  colHjName合计列名称
* Authors: Guoxh, 2012-3-5
**/
function computeHj(colName, colHjName){
	//alert("coming into computeHj...");
	var hj = 0;
	var colObj = document.all(colName);
/*	
	for(i=0; i<colObj.length; i++){
        hj += parseFloat(convertSpace2Zero(colObj[i].value));  
	}
*/	
	var nsrsbhArrLength = document.forms[0].fzjgnsrsbh.length;
	for(i=0; i<colObj.length; i++){
		obj = eval("document.getElementById('" + 12 + "." + (i+1) + "_1')");
		if(obj.value == ""){
            	//alert("该行对应的纳税人识别号为空，该数值将不参与合计计算！");
            	//obj.focus();
				//obj.select();
                //return false;
            	continue;
        }
        hj += parseFloat(convertSpace2Zero(colObj[i].value));  
	}

	//赋值
	//document.all(colHjName).value = hj;
	document.all(colHjName).value = parseFloat(hj).toFixed(2);
}

function computeFpblHj(colName, colHjName){
	var hj = 0;
	var colObj = document.all(colName);
/*
	for(i=0; i<colObj.length; i++){
		hj += parseFloat(convertSpace2Zero(colObj[i].value).replace('%',''));
	}
*/
	var nsrsbhArrLength = document.forms[0].fzjgnsrsbh.length;
	for(i=0; i<colObj.length; i++){
		obj = eval("document.getElementById('" + 12 + "." + (i+1) + "_1')");
		if(obj.value == ""){
            	//alert("该行对应的纳税人识别号为空，该数值将不参与合计计算！");
				//obj.focus();
				//obj.select();
	            //return false;
	            continue;
        }
		hj += parseFloat(convertSpace2Zero(colObj[i].value).replace('%',''));  
	}
	//赋值
	//document.all(colHjName).value = hj;
	document.all(colHjName).value = parseFloat(hj).toFixed(2);
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
/**暂时屏蔽以下内容_delete，modified by Guoxh,2012-3-5*/
function compute_Row_15_delete(id)
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
function compute_row_16_delete(id)
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
function compute_row_17_delete()
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
    //根据2012版的特点，无法按照之前模式进行检验，故此完全重写，modified by guoxh,2012-3-5
	if(a_nsfs=="1"){
		if(a_zfjg=="1"){
			//汇总纳税 — 总机构
		}else{
			//汇总纳税 — 分支机构
			if(document.all.ynsdse.value==""){
				alert("应纳所得税额不能为空！");
				return false;
			}
			if(document.all.ftsdse.value==""){
				alert("总机构分摊所得税额不能为空！");
				return false;
			}
			if(document.all.fpsdse.value==""){
				alert("总机构财政集中分配所得税额不能为空！");
				return false;
			}
		}
	}
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
    formatData(obj);
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
	//当输入值为空时，初始化为0
	formateParcent(obj);
	if(((obj.value).length==1)&&(obj.value == "%"))
	{
		obj.value = "0";
	}
}

//判断纳税人识别号是否由数字或字符组成
function checkNsrsbh(id)
{
	//alert("id = " + id);
	var obj = document.getElementById("12" + id);
	var nsrsbh = obj.value;
	if(nsrsbh.length == 0)
	{
		alert("请输入纳税人识别号!");
		//obj.focus();
		//obj.select();
		return;
	}

    for(var loc=0;loc<nsrsbh.length;loc++)
	{
		//alert("loc = " + loc);
		//alert("nsrsbh.chatAt(loc) = " + nsrsbh.charAt(loc));
		if(!((('a'<=nsrsbh.charAt(loc)) && (nsrsbh.charAt(loc)<='z')) || (('A'<=nsrsbh.charAt(loc)) && (nsrsbh.charAt(loc)<='Z')) || (('0'<=nsrsbh.charAt(loc)) && (nsrsbh.charAt(loc)<='9'))))
		{
			alert("纳税人识别号只能为数字或字符，请重新输入!");
			//obj.focus();
			//obj.select();
			return;
		}
	}
    compute_Row_nsrsbh_7('fzjgsrze','srehj');
    compute_Row_nsrsbh_8('fzjggzze','gzehj');
    compute_Row_nsrsbh_9('fzjgzcze','zcehj');
    compute_Row_nsrsbh_10('fzjgfpbl','fpblhj');
    compute_Row_nsrsbh_11('fzjgfpse','fpsehj');
}

//判断纳税人识别号后重新计算总机构三项因素之收入额的合计数
//不再判断页面中纳税人识别号是否为数字
//modified by gaoyh,2012-03-26
//--------------------begin-----------------------------
function compute_Row_nsrsbh_7(colName, colHjName)
{
	//alert("compute_Row_nsrsbh_7");
	//var id = trim(window.event.srcElement.id + "");
	//alert("id=============="+id);
	//var obj = document.getElementById(id)
	//alert("obj.value=============="+obj.value);
    computeHj(colName, colHjName);
	//格式化
	formatData(document.all(colHjName));
}
function compute_Row_nsrsbh_8(colName, colHjName)
{
	//alert("compute_Row_nsrsbh_8");
	//var id = trim(window.event.srcElement.id + "");
	//alert("id=============="+id);
	//var obj = document.getElementById(id)
	//alert("obj.value=============="+obj.value);
    computeHj(colName, colHjName);
	//格式化
	formatData(document.all(colHjName));
}
function compute_Row_nsrsbh_9(colName, colHjName)
{
	//alert("compute_Row_nsrsbh_9");
	//var id = trim(window.event.srcElement.id + "");
	//alert("id=============="+id);
	//var obj = document.getElementById(id)
	//alert("obj.value=============="+obj.value);
    computeHj(colName, colHjName);
	//格式化
	formatData(document.all(colHjName));
}
//分配比例
function compute_Row_nsrsbh_10(colName, colHjName)
{
	//alert("compute_Row_nsrsbh_10");
	//var id = trim(window.event.srcElement.id + "");
	//alert("id=============="+id);
	//var obj = document.getElementById(id)
	//alert("obj.value=============="+obj.value);
    computeFpblHj(colName, colHjName);
	checkPercent(document.all(colHjName));
}
function compute_Row_nsrsbh_11(colName, colHjName)
{
	//alert("compute_Row_nsrsbh_11");
	//var id = trim(window.event.srcElement.id + "");
	//alert("id=============="+id);
	//var obj = document.getElementById(id)
	//alert("obj.value=============="+obj.value);
    computeHj(colName, colHjName);
	//格式化
	formatData(document.all(colHjName));
}
//--------------------end-----------------------------

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

function getPostXml(objForm)
{	
	//alert("getPostXml");
	var retstr;
	//initXMLObject();
	//基本数据
	getBasicXx("xsltVersion","/taxdoc");
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	//申报信息
	applendElement("/taxdoc","sbxx",["fsdm","jd","nd","sbrq","sbrqshow","skssksrq","skssjsrq"]);
	//总机构信息
	applendElement("/taxdoc","zjgxx",["jsjdm","nsrsbh","nsrmc","ynsdse","ftsdse","fpsdse","fzjgftse"]);
	//分支机构信息
	//g_Doc.XMLDoc.createElement
	var fzjgxx = objForm.fzjgnsrsbh;
	//alert("fzjgnsrsbh.length = " + fzjgxx.length);
			
	if(fzjgxx.length > 0)
	{
		//在根节点taxdoc下添加fzjgxx节点
		var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc");
		var objTemp = g_Doc.XMLDoc.createElement("fzjgxx");
		objNode.appendChild(objTemp);
		//添加明细信息明细信息
		for(var i = 0; i < fzjgxx.length; i++)
		{
			if(fzjgxx[i].value != null && fzjgxx[i].value != "")
			{
				applendMxxxElement("/taxdoc/fzjgxx","mxxx",["index","nsrsbh","nsrmc","srze","gzze","zcze","fpbl","fpse"],(i+1));
			}
		}
	}
	else
	{
		//添加空明细信息节点
		applendElement("/taxdoc","fzjgxx",["mxxx"]);
	}

	//分支机构合计信息
	applendElement("/taxdoc","fzjghj",["srehj","gzehj","zcehj","fpblhj","fpsehj"]);
	
	//去掉末尾自动添加的回车
	retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
	retstr = retstr.substr(0,retstr.length-2);
	//alert("retstr = " + retstr);
	return retstr;
}
//增加分支机构明细信息节点
function applendMxxxElement(root,node,tags,index)
{
	//alert("root = " + root + "\nnode = " + node);
	var objNode = g_Doc.XMLDoc.selectSingleNode(root);
	var objTemp = g_Doc.XMLDoc.createElement(node);
	for (i = 0; i < tags.length ; i++)
	{
		//明细数据域ID从12~19
		getMxxxChildren(objTemp,tags[i], (i+11), index);
	}
	if (objTemp.hasChildNodes())
	{
		objNode.appendChild(objTemp);
	}
}
/**
* Notes: 拆分从A表传递过来的数据，并赋值
* Authors: Guoxh,2012-3-9
**/
function splitData(){
	var arrs = dataFromA.split(";");
	if(arrs.length == 10){
		a_nsfs = arrs[0].split(":")[1];
		a_zfjg = arrs[1].split(":")[1];
		if(a_nsfs=="1"){
			if(a_zfjg=="1"){
				//汇总纳税 — 总机构
				setZjgInput(arrs);
			}else{
				//汇总纳税 — 分支机构
				setFzjgInput(arrs);
			}
		}
	}
}

function setZjgInput(arrs){
	setReadOnlyData(document.all.ynsdse, arrs[2].split(":")[1], true);
	setReadOnlyData(document.all.ftsdse, arrs[3].split(":")[1], true);
	setReadOnlyData(document.all.fpsdse, arrs[4].split(":")[1], true);
	setReadOnlyData(document.all.fzjgftse, arrs[5].split(":")[1], true);
}
function setFzjgInput(arrs){
	setReadOnlyData(document.all.fzjgnsrsbh[0], arrs[8].split(":")[1], false);
	setReadOnlyData(document.all.fzjgmc[0], arrs[9].split(":")[1], false);
	setReadOnlyData(document.all.fzjgfpbl[0], arrs[6].split(":")[1], false);
	checkPercent(document.all.fzjgfpbl[0]);
	setReadOnlyData(document.all.fzjgfpse[0], arrs[7].split(":")[1], true);
	setInputData(document.all.nsrsbh);
	setInputData(document.all.nsrmc);
}
function setReadOnlyData(obj, val, format){
	obj.value = val;
	obj.readOnly = true;
	obj.className = "text-gray";
	if(format){
		formatData(obj);
	}
}
function setInputData(obj){
	obj.readOnly = false;
	obj.className = "";
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
	<form id="zfjg2012Form" name="zfjg2012Form" action="zfjgqyjb2012.dc" method="post">
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
