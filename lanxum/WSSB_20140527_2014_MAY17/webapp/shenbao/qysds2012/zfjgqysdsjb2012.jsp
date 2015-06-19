<%@ page contentType="text/html; charset=GBK"%>

<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ page import="com.ttsoft.common.model.UserData"%>
<%@ page import="java.util.HashMap"%>

<%
	//��̬�ļ�·��
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
<title>��ҵ����˰������˰��֧���������</title>
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
//xml��
var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var InputName=['lrze','nstzzje','nstzjse','mbyqndks','ynssde','sysl','ynsdse','jmsdse','hznscyqyjdyjbl','sjyyjdsdse','ybdsdse'];
var InputNameAl=['�����ܶ�','�ӣ���˰�������Ӷ�','������˰�������ٶ�','�����ֲ���ǰ��ȿ�����','Ӧ��˰���ö�','����˰��','Ӧ������˰��','��������˰��','������˰��Ա��ҵ�͵�Ԥ�ɱ���','ʵ����Ԥ�ɵ�����˰��','Ӧ�����ˣ�������˰��'];
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMAVersion = '<%=request.getAttribute("CA_XML_SCHEMA_VERSION")==null?"":request.getAttribute("CA_XML_SCHEMA_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';

var mxrows = '<%=request.getAttribute("maxrows")==null?"":request.getAttribute("maxrows")%>';
var dataFromA = '<%=request.getAttribute("DataFromA")==null?"":request.getAttribute("DataFromA")%>';
var a_nsfs,a_zfjg;
var xmlDoc;

//alert("dataFromA==="+dataFromA);
//����xml
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
	//���ÿ��е�Ԫ��Ŀ�����+1
	var fzjgqk = document.getElementById("fzjgqk");
	//alert("fzjgqk rowspan = " + fzjgqk.rowSpan);
	//alert("fzjgqk rowspanaaa = " + document.all.fzjgqk.rowSpan);
	fzjgqk.rowSpan = mxrows + 2;
	//����ȡ�����������¼���ҳ���֧������̯˰��
	//compute_row_17();
    setFoucs();

	splitData();

    return true;
}

<%/*��Ӧ���������Ļس���ѯ*/%>
function jsjdmQuery(){
    if(event.keyCode==13) event.keyCode = 9;
}

<%/*����������������ʱ�Ľ���仯*/%>
function setFoucs(){
    //alert(document.forms[0].nsrsbh.value);
    if(document.getElementById("1_1").value == null || document.getElementById("1_1").value == ""){
        window.event.returnValue=false;
        document.getElementById("1_1").focus();
    }
}

/**
* Notes: ������
* Authors: Guoxh,2012-3-1,��appendChild����ΪinsertRow��ʽ
**/
function doAddRow()
{
    //��ȡ���з�֧������ϸ��Ϣ����
    var mxnum = document.forms[0].fzjgnsrsbh.length;
    //alert("mxnum = " + mxnum);

    //��ȡ��Ҫ����еı��
    var table = document.getElementById("table_30");

    //Ȼ�󴴽���(TR����)
    //var NewTr = document.createElement("tr");
	var NewTr = table.insertRow(table.rows.length - 1);
    //���ñ����
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
        //������������䵥Ԫ��
        NewTr.appendChild(NewTd);
    }
	/*modified by guoxh,2012-3-1
    //�����
    var LastTr = table.rows[table.rows.length - 1];

    LastTr.parentNode.appendChild(NewTr);
	*/


    //���ÿ��е�Ԫ��Ŀ�����+1
    var fzjgqk = document.getElementById("fzjgqk");
    //alert("fzjgqk rowspan = " + fzjgqk.rowSpan);
    //alert("fzjgqk rowspanaaa = " + document.all.fzjgqk.rowSpan);
    fzjgqk.rowSpan += 1;
    //alert("fzjgqk rowspan = " + fzjgqk.rowSpan);
}

<%/*����*/%>
function doSave()
{
	if(document.forms[0].nsrmc.value == "" || document.forms[0].nsrmc.value == null){
        alert("�ܻ���������Ϣ�У�����˰�����ơ�����Ϊ��,����������!");
        return false;
    }
    
    if(document.forms[0].nsrsbh.value == "" || document.forms[0].nsrsbh.value == null){
        alert("�ܻ���������Ϣ�У�����˰��ʶ��š�����Ϊ��,����������!");
        return false;
    }
    
    if(document.forms[0].ynsdse.value == "" || document.forms[0].ynsdse.value == null){
        alert("�ܻ���������Ϣ�У���Ӧ������˰�����Ϊ��,����������!");
        return false;
    }
    
    if(document.forms[0].ftsdse.value == "" || document.forms[0].ftsdse.value == null){
        alert("�ܻ���������Ϣ�У����ܻ�����̯����˰�����Ϊ��,����������!");
        return false;
    }
    
    if(document.forms[0].fpsdse.value == "" || document.forms[0].fpsdse.value == null){
        alert("�ܻ���������Ϣ�У����ܻ����������з�������˰�����Ϊ��,����������!");
        return false;
    }
    
    if(document.forms[0].fzjgftse.value == "" || document.forms[0].fzjgftse.value == null){
        alert("�ܻ���������Ϣ�У�����֧������̯������˰�����Ϊ��,����������!");
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
        alert("δ֪�Ĳ������ͣ�" + document.forms[0].ywczlx.value);
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

<%/*���*/%>
function doCheck()
{
    doSubmitForm('Check');
}

<%/*���*/%>
function doReset()
{
	var arrs = dataFromA.split(";");
	if(arrs.length == 10){
		a_nsfs = arrs[0].split(":")[1];
		a_zfjg = arrs[1].split(":")[1];
	}
	
    if(confirm("ȷ���Ƿ������ǰҳ�����ݣ�"))
    {
        if(a_nsfs=="1" && a_zfjg=="1"){
        	
        	//����ϼ�����
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
	         // �����֧������ϸ��Ϣ
	        var nsrsbhArrLength = document.forms[0].fzjgnsrsbh.length;
	        for(var j=12; j < 19; j++)
	        {
	            for(var k=0; k<nsrsbhArrLength; k++)
	            {
	                obj = eval("document.getElementById('" + j + "." + (k+1) + "_1')");
	                //��֧������˰����Ϣ
	                if(j < 14)
	                {
	                    obj.value = "";
	                }
	                //���������Ϣ
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
        	//����ܻ�����ϼ�����
	        for(var i=1; i<12; i++)
	        {	     
				//��֧������̯������˰������
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
	        
	         // �����֧������ϸ��Ϣ
	        var nsrsbhArrLength = document.forms[0].fzjgnsrsbh.length;
	        for(var j=12; j < 19; j++)
	        {	
	            for(var k=0; k<nsrsbhArrLength; k++)
	            {
	            	//��֧������̯������˰������
	            	if(k==0)
					{
						obj = eval("document.getElementById('" + j + "." + (k+1) + "_1')");
						if(j>13 && j<17){
		                    obj.value = "0.00";
		                }
					}
					
					if(k>=1){
						obj = eval("document.getElementById('" + j + "." + (k+1) + "_1')");
		                //��֧������˰����Ϣ
		                if(j < 14)
		                {
		                    obj.value = "";
		                }
		                //���������Ϣ
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

<%/*ɾ��*/%>
function doDelete()
{
    var old  = document.forms[0].ywczlx.value;
    if((document.forms[0].ywczlx.value == 0) && confirm(confirmDelete))
    {
        document.forms[0].actionType.value="Delete";
        //��ҵ�����������Ϊɾ��״̬
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

<%/*����*/%>
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

//�����ܻ�����������֮�����ĺϼ���
//modified by Guoxh,2012-3-5
function compute_Row_7(colName, colHjName)
{
	//alert("compute_Row_7");
	//�ж�����������Ƿ����Ҫ��
    if(!isNum(getCellObject() , null, null, null, null, 2)){
        return false;
    }
    computeHj(colName, colHjName);
	//��ʽ��
	formatData(document.all(colHjName));
}
function compute_Row_8(colName, colHjName)
{
	//�ж�����������Ƿ����Ҫ��
    if(!isNum(getCellObject() , null, null, null, null, 2)){
        return false;
    }

    computeHj(colName, colHjName);
	//��ʽ��
	formatData(document.all(colHjName));
}
function compute_Row_9(colName, colHjName)
{
	//�ж�����������Ƿ����Ҫ��
    if(!isNum(getCellObject() , null, null, null, null, 2)){
        return false;
    }

    computeHj(colName, colHjName);
	//��ʽ��
	formatData(document.all(colHjName));
}
//�������
function compute_Row_10(colName, colHjName)
{
	//�ж�����������Ƿ����Ҫ��
    if(!isNum(getCellObject() , null, null, null, null, 2)){
        return false;
    }

    computeFpblHj(colName, colHjName);
	checkPercent(document.all(colHjName));
}
function compute_Row_11(colName, colHjName)
{
	//�ж�����������Ƿ����Ҫ��
    if(!isNum(getCellObject() , null, null, null, null, 2)){
        return false;
    }
    computeHj(colName, colHjName);
	//��ʽ��
	formatData(document.all(colHjName));
}

/**
* Notes: ����ָ���������ƣ�������еĺϼ�
* Parameters: 
*  colName������
*    ����fzjgsrze�����ʶfzjggzze���ʲ��fzjgzcze
*    ���������fzjgfpbl������˰�fzjgfpse
*  colHjName�ϼ�������
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
            	//alert("���ж�Ӧ����˰��ʶ���Ϊ�գ�����ֵ��������ϼƼ��㣡");
            	//obj.focus();
				//obj.select();
                //return false;
            	continue;
        }
        hj += parseFloat(convertSpace2Zero(colObj[i].value));  
	}

	//��ֵ
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
            	//alert("���ж�Ӧ����˰��ʶ���Ϊ�գ�����ֵ��������ϼƼ��㣡");
				//obj.focus();
				//obj.select();
	            //return false;
	            continue;
        }
		hj += parseFloat(convertSpace2Zero(colObj[i].value).replace('%',''));  
	}
	//��ֵ
	//document.all(colHjName).value = hj;
	document.all(colHjName).value = parseFloat(hj).toFixed(2);
}

<%/*����������ܻ����ϼ��������֧���������������������*/%>
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
            //ƴ��ǰ�еĺ�׺
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
<%/*������ֻ������������غϼ������������������˰��*/%>
/**��ʱ������������_delete��modified by Guoxh,2012-3-5*/
function compute_Row_15_delete(id)
{
    //�ж�����������Ƿ����Ҫ��
    if(!isNum(getCellObject() , null, null, null, null, 2)){
        return false;
    }

    //alert("id = " + id);

    //����12(�����ܶ�)��12(�����ܶ�)��14(�ʲ��ܶ�)������������жϵ�15(�ϼ�)�������Ƿ�Ҫ�Ķ�
    var row_12 = convertSpace2Zero(document.getElementById("12" + id).value);
    var row_13 = convertSpace2Zero(document.getElementById("13" + id).value);
    var row_14 = convertSpace2Zero(document.getElementById("14" + id).value);
    //alert("row_12 = " + row_12);

    //���㲢��ʽ����15�е�ֵ
    var obj = document.getElementById("15" + id);
    var temp15 = parseFloat(row_12) + parseFloat(row_13) + parseFloat(row_14);
    //alert("temp15 = " + temp15);
    //obj.value=parseFloat(temp8);
    obj.value = parseFloat(temp15).toFixed(2);
    formate(obj);
	/*
			����2008-3-21���Խ��Ҫ��,ȡ���ϼ�����0У��;ȡ���������������˰���Զ�����
    if(temp15 <= 0)
    {
        alert("����֧�����ϼ����������0�����ʵ���޸ģ�");
        return;
    }
	

    //�����Ӧ��16�������
    compute_row_16(id);
	*/
    //������17(����˰��)
    //compute_row_17(id);
}

<%/*������ֻ������������*/%>
function compute_row_16_delete(id)
{
    //alert("compute_row_16_id = " + id);
    var obj_16 = document.getElementById("16" + id);
    var obj_17 = document.getElementById("17" + id);
    var temp;
    //alert("obj_16 = " + obj_16.value);
    //�ж�����������Ƿ����Ҫ��
    if(!isNum(getCellObject() , null, null, null, null, 2)){
        return false;
    }

    //�����ܻ����ϼ�������������������˰��
    var row_8 = convertSpace2Zero(document.getElementById("8_1").value);
    //alert(parseFloat(row_8));
    if(parseFloat(row_8) == 0)
    {
        //�ܽṹ�ϼƽ��Ϊ0,���Ӧ��֧�����ķ������Ϊ0
        obj_16.value = 0;
        //formate(obj_16);

        obj_17.value = 0;
        formate(obj_17);
    }
    else
    {
        //��ȡ��ǰ��֧�����ϼ���
        var row_15 = convertSpace2Zero(document.getElementById("15" + id).value);
        temp = parseFloat((Math.abs(parseFloat(row_15)) / Math.abs(parseFloat(row_8))).toFixed(4));
        obj_16.value = parseFloat(temp);
        formate2Parcent(obj_16, obj_16.value);

        //�ܻ�����̯˰��
        var row_9 = convertSpace2Zero(document.getElementById("9_1").value);
        //alert("row_9 = " + row_9 + "\nobj_17 = " + obj_17.value);
        obj_17.value = parseFloat(parseFloat(row_9) * temp).toFixed(2);
        formate(obj_17);
    }
}

<%/*������ֻ���������˰��*/%>
function compute_row_17_delete()
{
	//alert("compute_row_17");
    //�ܻ�����̯˰��
	var row_9 = convertSpace2Zero(document.getElementById("9_1").value);
	var fzjg = document.forms[0].fzjgnsrsbh;
	for(var i=0; i<fzjg.length; i++)
	{
		if(fzjg[i].value != null && fzjg[i].vlaue != "")
		{
			//��֧�����������
			var row_16 = document.forms[0].fzjgfpbl[i];
			var fpbl = formateParcent2Num(row_16);
			//��֧��������˰��
			var row_17 = document.forms[0].fzjgfpse[i];
			row_17.value = parseFloat(parseFloat(row_9) * fpbl).toFixed(2);
			formate(row_17);
		}
	}
}


//����걨�·ݲ�������ҵ����˰��������4��7��10����ʾ������Ա
function checkZQ(sbrq,ksrq,jzrq,lx){
    if(!isDate(sbrq,"v")) return;
    getStartEndDate1(sbrq,ksrq,jzrq,lx);
    var inputDate = sbrq.value;
    mon = inputDate.substring(4,6);
    if(mon!='01' && mon!='04' && mon!='07' && mon!='10'){
        alert('ע�⣺'+inputDate+'���������ڡ�');
    }
}

/**
* Notes: ��ȡ��ǰ���ڵ���һ��/��/������ֹ���ڡ�
* Version: 0.9.00
* Author: Guoxh
* Parames: flag 1,Last Year;2,Last Month;Ĭ�ϣ��������
*/
function getStartEndDate1(oInput1,oInput2,oInput3,flag){
    var date_start,date_end;
    var year,mon,days;
    var strMon;

    var inputDate = oInput1.value;

    //�Ƿ�Ϸ�����
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

<%/*�жϱȽ�˰����������*/%>
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
        alert("˰�����ʱ�䲻�ܴ����걨����");
        window.event.returnValue=false;
        document.forms[0].skssjsrq.focus();
        document.forms[0].skssjsrq.select();
        return false;
    }

    if(objDate1>=objDate2){
        if(obj == document.forms[0].skssjsrq){
            alert("˰�����ʱ�䲻��С�ڻ����˰�ʼʱ��");
        }else{
            alert("˰�ʼʱ�䲻�ܴ��ڻ����˰�����ʱ��");
        }
        window.event.returnValue=false;
        obj.focus();
        obj.select();
        return false;
    }

    if(strYear1!=strYear2){
        alert("˰�ʼʱ����˰�����ʱ�����ݲ�ͬ��˰��ܿ��꣡");
        return false;
    }

}

<%/*�жϱȽϷ��������Ч��*/%>
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

//�жϱȽ�˰����������
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
        alert("˰�����ʱ�䲻�ܴ��ڵ�ǰ����");
        window.event.returnValue=false;
        document.forms[0].skssjsrq.focus();
        document.forms[0].skssjsrq.select();
        return false;
    }

    if(objDate1>=objDate2){
        if(obj == document.forms[0].skssjsrq){
            alert("˰�����ʱ�䲻��С�ڻ����˰�ʼʱ��");
        }else{
            alert("˰�ʼʱ�䲻�ܴ��ڻ����˰�����ʱ��");
        }
        window.event.returnValue=false;
        obj.focus();
        obj.select();
        return false;
    }

    if(strYear1!=strYear2){
        alert("˰�ʼʱ����˰�����ʱ�����ݲ�ͬ��˰��ܿ��꣡");
        return false;
    }

    return true;

}

<%/*���ո�ת��Ϊ�㣬����������������*/%>
function convertSpace2Zero(inputValue){
    return inputValue==""?"0":inputValue;
}

<%/*����ʱ��ҳ�����ݽ���У��*/%>
function saveCheck()
{
    //����2012����ص㣬�޷�����֮ǰģʽ���м��飬�ʴ���ȫ��д��modified by guoxh,2012-3-5
	if(a_nsfs=="1"){
		if(a_zfjg=="1"){
			//������˰ �� �ܻ���
		}else{
			//������˰ �� ��֧����
			if(document.all.ynsdse.value==""){
				alert("Ӧ������˰���Ϊ�գ�");
				return false;
			}
			if(document.all.ftsdse.value==""){
				alert("�ܻ�����̯����˰���Ϊ�գ�");
				return false;
			}
			if(document.all.fpsdse.value==""){
				alert("�ܻ����������з�������˰���Ϊ�գ�");
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
    //�ж�����������Ƿ����Ҫ��
    if(!isNum(getCellObject() , null, null, null, null, 2)){
        return false;
    }
    //��ʽ������
    formatData(obj);
    formate(obj);
}

<%//У��������¼����%>
function checkPercent(obj)
{
	var temp = obj.value;
	var end = temp.substring(temp.length-1);		
	//alert("end = " + end);
	//�������Ϊ%��β����ȥ��%�ź���
	if(end == "%")
	{
		temp = temp.substring(0, (temp.length-1));
	}
	obj.value = temp;
	//�ж�����������Ƿ����Ҫ��
	if(!isNum(obj, null, null, null, null, 2)){
		return false;			
	}
	//������ֵΪ��ʱ����ʼ��Ϊ0
	formateParcent(obj);
	if(((obj.value).length==1)&&(obj.value == "%"))
	{
		obj.value = "0";
	}
}

//�ж���˰��ʶ����Ƿ������ֻ��ַ����
function checkNsrsbh(id)
{
	//alert("id = " + id);
	var obj = document.getElementById("12" + id);
	var nsrsbh = obj.value;
	if(nsrsbh.length == 0)
	{
		alert("��������˰��ʶ���!");
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
			alert("��˰��ʶ���ֻ��Ϊ���ֻ��ַ�������������!");
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

//�ж���˰��ʶ��ź����¼����ܻ�����������֮�����ĺϼ���
//�����ж�ҳ������˰��ʶ����Ƿ�Ϊ����
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
	//��ʽ��
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
	//��ʽ��
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
	//��ʽ��
	formatData(document.all(colHjName));
}
//�������
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
	//��ʽ��
	formatData(document.all(colHjName));
}
//--------------------end-----------------------------

//��ת��������ҳ��
function jumpToCZZS(){
	//document.forms[0].ActionType="Jump";
	//alert("�˴���Ҫ��дת����ҵ����˰������˰��֧������������ܵ�����");
	if(confirm("ȷ�������ز��������걨ҳ�棬�����ᱣ�浱ǰ����ݣ��Ƿ������"))
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
	//��������
	getBasicXx("xsltVersion","/taxdoc");
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	//�걨��Ϣ
	applendElement("/taxdoc","sbxx",["fsdm","jd","nd","sbrq","sbrqshow","skssksrq","skssjsrq"]);
	//�ܻ�����Ϣ
	applendElement("/taxdoc","zjgxx",["jsjdm","nsrsbh","nsrmc","ynsdse","ftsdse","fpsdse","fzjgftse"]);
	//��֧������Ϣ
	//g_Doc.XMLDoc.createElement
	var fzjgxx = objForm.fzjgnsrsbh;
	//alert("fzjgnsrsbh.length = " + fzjgxx.length);
			
	if(fzjgxx.length > 0)
	{
		//�ڸ��ڵ�taxdoc�����fzjgxx�ڵ�
		var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc");
		var objTemp = g_Doc.XMLDoc.createElement("fzjgxx");
		objNode.appendChild(objTemp);
		//�����ϸ��Ϣ��ϸ��Ϣ
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
		//��ӿ���ϸ��Ϣ�ڵ�
		applendElement("/taxdoc","fzjgxx",["mxxx"]);
	}

	//��֧�����ϼ���Ϣ
	applendElement("/taxdoc","fzjghj",["srehj","gzehj","zcehj","fpblhj","fpsehj"]);
	
	//ȥ��ĩβ�Զ���ӵĻس�
	retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
	retstr = retstr.substr(0,retstr.length-2);
	//alert("retstr = " + retstr);
	return retstr;
}
//���ӷ�֧������ϸ��Ϣ�ڵ�
function applendMxxxElement(root,node,tags,index)
{
	//alert("root = " + root + "\nnode = " + node);
	var objNode = g_Doc.XMLDoc.selectSingleNode(root);
	var objTemp = g_Doc.XMLDoc.createElement(node);
	for (i = 0; i < tags.length ; i++)
	{
		//��ϸ������ID��12~19
		getMxxxChildren(objTemp,tags[i], (i+11), index);
	}
	if (objTemp.hasChildNodes())
	{
		objNode.appendChild(objTemp);
	}
}
/**
* Notes: ��ִ�A���ݹ��������ݣ�����ֵ
* Authors: Guoxh,2012-3-9
**/
function splitData(){
	var arrs = dataFromA.split(";");
	if(arrs.length == 10){
		a_nsfs = arrs[0].split(":")[1];
		a_zfjg = arrs[1].split(":")[1];
		if(a_nsfs=="1"){
			if(a_zfjg=="1"){
				//������˰ �� �ܻ���
				setZjgInput(arrs);
			}else{
				//������˰ �� ��֧����
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
				<jsp:param name="name" value="��ҵ����˰������˰��֧���������" />
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
					  <input type="image" accesskey="a" onClick="doAddRow();return false;" onMouseOver="MM_swapImage('zengjia','','<%=static_contextpath%>images/zj-a2.jpg ',1)" onMouseOut="MM_swapImgRestore()" alt="������" src="<%=static_contextpath%>images/zj-a1.jpg " width="79" height="22" id="zengjia" style="cursor:hand">
					  &nbsp;&nbsp;&nbsp;&nbsp;
					  <input type="image" accesskey="s" style="cursor:hand" onClick="doSave();return false;" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="����" src="<%=static_contextpath%>images/bc-s1.jpg" name="Image13" width="79" height="22" border="0" id='baocun'>
					  &nbsp;&nbsp;&nbsp;&nbsp;
					  <input type="image" accesskey="x" style="cursor:hand" onClick="doDelete();return false;" onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="ɾ��" src="<%=static_contextpath%>images/qbsc-x1.jpg" name="Image13" width="79" height="22" border="0" id='shanchu'>
					  &nbsp;&nbsp;&nbsp;&nbsp;
					  <input type="image" accesskey="u" style="cursor:hand" onClick="doReset();return false;" onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="���" src="<%=static_contextpath%>images/qc-u1.jpg" name="Image132" width="79" height="22" border="0" id='qingchu'>
					  &nbsp;&nbsp;&nbsp;&nbsp;
					  <input type="image" onClick="jumpToCZZS();return false;" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="���ز��������걨ҳ��" value="����" id="fh1" src="<%=static_contextpath%>images/fanhui1.jpg" width="79" height="22" style="cursor:hand" tabIndex="-1">
					  &nbsp;&nbsp;&nbsp;&nbsp;
					  <input type="image" onClick="doReturn();return false;" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor:hand" tabIndex="-1">
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
