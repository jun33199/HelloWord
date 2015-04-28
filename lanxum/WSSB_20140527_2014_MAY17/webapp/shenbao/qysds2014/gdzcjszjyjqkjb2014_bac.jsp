<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%
  String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>

<html>
<head>
<title>
�̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ�
</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type = "text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/XmlBuild.js"></script>

<script language=JavaScript>
<%
        String containerName = "";

        com.ttsoft.common.model.UserData userdata = (com.ttsoft.common.model.UserData)session.getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA);
        if (userdata.getCaflag())
        {
          containerName = userdata.getCert().getContainerName();
        }
        else
        {
          containerName = "";
        }
%>
g_objSI.Container = "<%=containerName%>";
var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION")==null?"":request.getAttribute("CA_XML_SCHEME_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';

//����xml
function parseXmlOnLoad()
{
    var xslPath="/XSLTWEB/model/010037/XSLT/" +strXSLTVersion+".xsl";
    if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    return true;
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
	getBasicXx("jumpFlag","/taxdoc");
	//�걨��Ϣ
	applendElement("/taxdoc","sbxx",["sbrqshow","skssksrq","skssjsrq"]);
	//��˰����Ϣ
	applendElement("/taxdoc","nsrxx",["jsjdm","nsrmc","nsrsbh","sshy","sshydm"]);
		//��֧�����ϼ���Ϣ
	applendElement("/taxdoc","sbsjlist",["zj_hc", "zj_xm", "zj_fwjzw_yz", "zj_fwjzw_bqzje", "zj_fwjzw_ljzje", "zj_jqsbhqtgdzc_yz", "zj_jqsbhqtgdzc_bqzje", "zj_jqsbhqtgdzc_ljzje", 
			"zj_hj_yz","zj_hj_bqzje_zczje", "zj_hj_bqzje_jszje", "zj_hj_ljzje_zczje", "zj_hj_ljzje_jszje"]);

	//��֧������Ϣ
	//g_Doc.XMLDoc.createElement
	var hc = objForm.hc;
	//alert("fzjgnsrsbh.length = " + fzjgxx.length);
			
	if(hc.length > 0)
	{
		//�ڸ��ڵ�taxdoc�����fzjgxx�ڵ�
//		var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc");
//		var objTemp = g_Doc.XMLDoc.createElement("sbsjlist");
//		objNode.appendChild(objTemp);
		//�����ϸ��Ϣ��ϸ��Ϣ
		for(var i = 0; i < hc.length; i++)
		{

			applendMxxxElement("/taxdoc/sbsjlist","sbsj",["hc", "xm", "fwjzw_yz", "fwjzw_bqzje", "fwjzw_ljzje", "jqsbhqtgdzc_yz", "jqsbhqtgdzc_bqzje", "jqsbhqtgdzc_ljzje", 
			"hj_yz","hj_bqzje_zczje", "hj_bqzje_jszje", "hj_ljzje_zczje", "hj_ljzje_jszje"],(i+1));

		}
	}
	else
	{
		//��ӿ���ϸ��Ϣ�ڵ�
		applendElement("/taxdoc","sbsjlist",["sbsj"]);
	}

	
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
		getMxxxChildren(objTemp,tags[i], (i+1), index);
	}
	if (objTemp.hasChildNodes())
	{
		objNode.appendChild(objTemp);
	}
}

<%/*����*/%>
function doSave()
{

		 if(!checkXhgx(true)){
		 	return false;
		 }	
		document.forms[0].actionType.value="Save";
		if (g_objSI.Container != '')
		{
				if (!doSubmitXML(document.forms[0],"Save",true,"",true))
					{
							return false;
					}	
		}else
		{
				if (!doSubmitXML(document.forms[0],"Save",false,"",true))
					{
							return false;
					}	
		}
		return true;

}

function checkvalue(obj){

	if(obj.value==""||obj.value==null){
		obj.value="0.00";	
	}else{
		var temp = trim(obj.value+"");
		if(temp.indexOf(".")!=-1){
			var len=temp.indexOf(".")+1;
			if(temp.length-len==1)
			temp = temp+"0";
			var zs=trim(temp.substring(0,temp.indexOf(".")));
			if(zs==""){
				temp="0"+temp;
			}
		}else{
			temp=temp+".00";
		}
		if(temp=="-.00"){
			temp="-0.00";
		}
		obj.value=temp;	
		formateNum(obj);
	}		
}

/**
 * ��ʽ������������0��ͷ
 *
 * @param �ı���
 */
 function formateNum(obj){
 	var tempNum=obj.value;
 	var num=trim(tempNum.substring(0,tempNum.indexOf(".")));
 	if(num.length>1){
 		num=num.substring(0,num.length-1);
 		var i;
 		for(i=0;i<num.length;i++){
 			var itemp=num.substring(i,i+1);
 			if(itemp!="0"){
 				break;
 			}
 		}
 		tempNum=tempNum.substring(i,tempNum.length);
 		obj.value=tempNum;
 	}
 
 }

	//��ʽ�����ݣ������ܼƣ���У������֮���໥��ϵ�Ƿ���ȷ
	function changeNum(obj,lc){
		numCheck(obj);
		computeZj(lc);
		autoCompute();
		checkXhgx(false);
	}
	//������������Ƿ�Ϊ���ֲ���ʽ��
	function numCheck(obj){
		if(!isNum(obj,null,null,null,null,2)){
			obj.value="0.00";
			return false;
		}
		formate(obj);
	}	
	//�����ܼ�
	function computeZj(lc){
		var qthy=0;
		var ldhygdzczj=0;
		var yxycxkcdgdzczj=0;
		var zj=0;
		
		for(var i=1;i<=13;i++){
			var id=lc+"."+i+"_1";
			var value=document.getElementById(id).value;
			if(value==null || trim(value)=="") continue;

			if(i>=2&&i<=7){
				ldhygdzczj= (parseFloat(ldhygdzczj)+parseFloat(value)).toFixed(2);
			}else if(i==8){
				qthy=value;
			}else if(i==10||i==12){
				yxycxkcdgdzczj= (parseFloat(yxycxkcdgdzczj)+parseFloat(value)).toFixed(2);
			}						
		}
		zj=(parseFloat(yxycxkcdgdzczj)+parseFloat(ldhygdzczj)+parseFloat(qthy)).toFixed(2);
		document.getElementById(lc+".1_1").value=ldhygdzczj;
		numCheck(document.getElementById(lc+".1_1"));
		document.getElementById(lc+".9_1").value=yxycxkcdgdzczj;
		numCheck(document.getElementById(lc+".9_1"));
		document.getElementById(lc+".13_1").value=zj;
		numCheck(document.getElementById(lc+".13_1"));
		
	}
	//�������֮����໥��ϵ�Ƿ���ȷ
	function checkXhgx(isSave){
		for(var i=1;i<=13;i++){
			if(isSave==true&&i==13){
				var lc7=document.getElementById("9."+i+"_1").value==""?0:document.getElementById("9."+i+"_1").value;
				var lc10=document.getElementById("12."+i+"_1").value==""?0:document.getElementById("12."+i+"_1").value;
				var lc11=document.getElementById("13."+i+"_1").value==""?0:document.getElementById("13."+i+"_1").value;
				if(parseFloat(lc7)<=0){
					alert("����ҵ����13��7��Ӧ>0���������Ŀ��д���ݡ�");
					//document.getElementById("9."+i+"_1").focus();
					return false;
				}
				if(parseFloat(lc10)<=0){
					alert("����ҵ����13��10��Ӧ>0���������Ŀ��д���ݡ�");
					//document.getElementById("12."+i+"_1").focus();
					return false;
				}
				if(parseFloat(lc11)<=0){
					alert("����ҵ����13��11��Ӧ>0���������Ŀ��д���ݡ�");
					//document.getElementById("13."+i+"_1").focus();
					return false;
				}								
			}
			if (i==1||i==9||i==13) continue;
			for(var j=3;j<=13;j++){
				var id=j+"."+i+"_1";
				if(i==10){
					var hc10 =document.getElementById(j+".10_1").value==""?0:document.getElementById(j+".10_1").value;
					var hc11 =document.getElementById(j+".11_1").value==""?0:document.getElementById(j+".11_1").value;
					if(parseFloat(hc10)<parseFloat(hc11)){
						alert("����ҵ����11��"+(j-2)+"��("+hc11+")Ӧ�ܡ���10��"+(j-2)+"��("+hc10+")���������Ŀ��д���ݡ�");
						document.getElementById(id).focus();
						return false;
					}					
				}	
				var lc1=document.getElementById("3."+i+"_1").value==""?0:document.getElementById("3."+i+"_1").value;			
				var lc2=document.getElementById("4."+i+"_1").value==""?0:document.getElementById("4."+i+"_1").value;
				var lc3=document.getElementById("5."+i+"_1").value==""?0:document.getElementById("5."+i+"_1").value;
				var lc4=document.getElementById("6."+i+"_1").value==""?0:document.getElementById("6."+i+"_1").value;
				var lc5=document.getElementById("7."+i+"_1").value==""?0:document.getElementById("7."+i+"_1").value;
				var lc6=document.getElementById("8."+i+"_1").value==""?0:document.getElementById("8."+i+"_1").value;
				var lc7=document.getElementById("9."+i+"_1").value==""?0:document.getElementById("9."+i+"_1").value;				
				var lc8=document.getElementById("10."+i+"_1").value==""?0:document.getElementById("10."+i+"_1").value;
				var lc9=document.getElementById("11."+i+"_1").value==""?0:document.getElementById("11."+i+"_1").value;
				var lc10=document.getElementById("12."+i+"_1").value==""?0:document.getElementById("12."+i+"_1").value;
				var lc11=document.getElementById("13."+i+"_1").value==""?0:document.getElementById("13."+i+"_1").value;	
				if(j==5){
					if(document.getElementById("5."+i+"_1").value=="" && isSave==false) continue;
					if(parseFloat(lc2)>parseFloat(lc3)){
						alert("����ҵ����"+i+"��2��("+lc2+")Ӧ�ܡ���"+i+"��3��("+lc3+")���������Ŀ��д���ݡ�");
						document.getElementById(id).focus();
						return false;
					}	
				}
				if(j==8){
					if(document.getElementById("8."+i+"_1").value==""&& isSave==false) continue;
					if(parseFloat(lc5)>parseFloat(lc6)){
						alert("����ҵ����"+i+"��5��("+lc5+")Ӧ�ܡ���"+i+"��6��("+lc6+")���������Ŀ��д���ݡ�");
						document.getElementById(id).focus();
						return false;
					}	
				}
				if(j==9){
					if(document.getElementById("9."+i+"_1").value==""&& isSave==false) continue;
					if((parseFloat(lc1)+parseFloat(lc4)).toFixed(2)>parseFloat(lc7)){
						alert("����ҵ����"+i+"��1��("+lc1+")�롱��"+i+"��4��("+lc4+")�ĺ�("+(parseFloat(lc1)+parseFloat(lc4)).toFixed(2)+")Ӧ�ܡ���"+i+"��7��("+lc7+")���������Ŀ��д���ݡ�");
						document.getElementById(id).focus();
						return false;
					}	
				}																			
				if(j==11){
					if(document.getElementById("11."+i+"_1").value==""&& isSave==false) continue;
					if(parseFloat(lc8)>=parseFloat(lc9)&& !(parseFloat(lc8)==parseFloat(0) && parseFloat(lc9)==parseFloat(0))){
						alert("����ҵ����"+i+"��"+8+"��("+lc8+")Ӧ<����"+i+"��"+9+"��("+lc9+")���������Ŀ��д���ݡ�");
						document.getElementById(id).focus();
						return false;
					}
				}
				if(j==12){
					if(document.getElementById("12."+i+"_1").value==""&& isSave==false) continue;
					if(parseFloat(lc8)>parseFloat(lc10)){
						alert("����ҵ����"+i+"��"+8+"��("+lc8+")Ӧ�ܡ���"+i+"��"+10+"��("+lc10+")���������Ŀ��д���ݡ�");
						document.getElementById(id).focus();
						return false;
					}
				}
				if(j==13){
					if(document.getElementById("13."+i+"_1").value==""&& isSave==false) continue;
					if(parseFloat(lc9)>parseFloat(lc11)){
						alert("����ҵ����"+i+"��"+9+"��("+lc9+")Ӧ�ܡ���"+i+"��"+11+"��("+lc11+")���������Ŀ��д���ݡ�");
						document.getElementById(id).focus();
						return false;
					}
					if(parseFloat(lc10)>=parseFloat(lc11)&& !(parseFloat(lc10)==parseFloat(0) && parseFloat(lc11)==parseFloat(0))){
						alert("����ҵ����"+i+"��"+10+"��("+lc10+")Ӧ<����"+i+"��"+11+"��("+lc11+")���������Ŀ��д���ݡ�");
						document.getElementById(id).focus();
						return false;
					}				
				}			  			 			  			  
			}									
		}
		
		
				//�������֮����໥��ϵ�Ƿ���ȷ(����)2015.02.11
		return checkXhgxNew(isSave)
		
		return true;
	}
<%/*���*/%>
function doClear()
{
	for(var i=1;i<=13;i++){
		for(j=3;j<=13;j++){
			document.getElementById(j+"."+i+"_1").value="";
		}
	}
    return true;
}
<%/*����*/%>
function doReturn()
{
    if(confirm(confirmReturn))
    {
        //document.forms[0].submit();
		document.forms[0].actionType.value="Return";
		if (g_objSI.Container != '')
		{
				if (!doSubmitXML(document.forms[0],"Return",true,"",true))
					{
							return false;
					}	
		}else
		{
				if (!doSubmitXML(document.forms[0],"Return",false,"",true))
					{
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
//�˳�
function doExit(){
		document.forms[0].actionType.value="Exit";
		if (g_objSI.Container != '')
		{
				if (!doSubmitXML(document.forms[0],"Exit",true,"",true))
					{
							return false;
					}	
		}else
		{
				if (!doSubmitXML(document.forms[0],"Exit",false,"",true))
					{
							return false;
					}	
		}
		return true;
}
<%/*ɾ��*/%>
function doDelete()
{		
		document.forms[0].ywczlx.value = 3;
		document.forms[0].actionType.value="Delete";
		//changeLocalYwczlx("3");
		if (g_objSI.Container != '')
		{
				if (!doSubmitXML(document.forms[0],"Delete",true,"",true))
					{
							return false;
					}	
		}else
		{
				if (!doSubmitXML(document.forms[0],"Delete",false,"",true))
					{
							return false;
					}	
		}
		return true;
}/*
function changeLocalYwczlx(ywczlx)
{
    //alert("ywczlx = " + ywczlx);
    var rootNode = xmlDoc.documentElement;
    var objCDATA =xmlDoc.createCDATASection(ywczlx);
    rootNode.selectSingleNode("//ywczlx").text = "";
    rootNode.selectSingleNode("//ywczlx").appendChild(objCDATA);
}*/
//ɾ���ַ���ǰ��Ŀո�
function valueTrim(str)
{
   if (str == "") return "";

    while(str.substring(0,1)==' ')
        str=str.substring(1,str.length);
    while(str.substring(str.length-1,str.length)==' ')
    str=str.substring(0,str.length-1);
    return str;
}

function checkZero()
{
    for(var i=0; i<document.forms[0].ynse.length; i++)
    {
         if(parseFloat(document.forms[0].ynse[i].value)==0)
         {
              return false;
         }
    }
    return true;
}
//��ʼ�������
function initInput(){
	for(var i=1;i<=13;i++){
		if(i==1||i==9||i==13){
			for(var j=3;j<=13;j++){
				var id=j+"."+i+"_1";
				var element=document.getElementById(id);
				element.setAttribute("readOnly",'true');
				element.style.backgroundColor = "#EEEEEE";	
	
			}
		}
		
		if(i==8||i==10||i==11||i==12){		
			for(var j=3;j<=5;j++){
				var id=j+"."+i+"_1";
				var element=document.getElementById(id);
				element.setAttribute("readOnly",'true');
				element.style.backgroundColor = "#EEEEEE";	
	
			}
		}
		
		
		if(i>=2&&i<=7){
			for(var j=3;j<=13;j++){
				if(j==9||j==11||j==13){
					var id=j+"."+i+"_1";
					var element=document.getElementById(id);
					element.setAttribute("readOnly",'true');
					element.style.backgroundColor = "#EEEEEE";	
				}
			}
		}
	}
}


function formateNumByValue(value){

		var temp = trim(value+"");
		if(temp.indexOf(".")!=-1){
			var len=temp.indexOf(".")+1;
			if(temp.length-len==1)
			temp = temp+"0";
		}else{
			temp=temp+".00";
		}
		return temp;
	}	
	
		//�Զ����㲿��
	function autoCompute(){
		for(var i=2;i<=7;i++){
			var lc1=document.getElementById("3."+i+"_1").value;
			var lc2=document.getElementById("4."+i+"_1").value;	
			var lc3=document.getElementById("5."+i+"_1").value;		
			var lc4=document.getElementById("6."+i+"_1").value;
			var lc5=document.getElementById("7."+i+"_1").value;
			var lc6=document.getElementById("8."+i+"_1").value;
			var lc7=document.getElementById("9."+i+"_1").value;
			var lc9=document.getElementById("11."+i+"_1").value;
			var lc11=document.getElementById("13."+i+"_1").value;
			if(!(isEmpty(lc1)&&isEmpty(lc4))){
				lc1=isEmpty(lc1)?0:lc1;
				lc4=isEmpty(lc4)?0:lc4;
				document.getElementById("9."+i+"_1").value=(parseFloat(lc1)+parseFloat(lc4)).toFixed(2);
				computeZj(9);
			}
			if(!(isEmpty(lc2)&&isEmpty(lc5))){
				lc2=isEmpty(lc2)?0:lc2;
				lc5=isEmpty(lc5)?0:lc5;
				document.getElementById("11."+i+"_1").value=(parseFloat(lc2)+parseFloat(lc5)).toFixed(2);
				computeZj(11);
			}
			if(!(isEmpty(lc3)&&isEmpty(lc6))){
				lc3=isEmpty(lc3)?0:lc3;
				lc6=isEmpty(lc6)?0:lc6;
				document.getElementById("13."+i+"_1").value=(parseFloat(lc3)+parseFloat(lc6)).toFixed(2);
				computeZj(13);
			}
			
			
		}
	}
	function isEmpty(obj){
		if(""==obj||"null"==obj||null==obj){
			return true;
		}
		return false;
	}
	
		//�������֮����໥��ϵ�Ƿ���ȷ(����)2015.02.11
function checkXhgxNew(isSave){
	for(var i=2;i<=8;i++){
		for(var j=3;j<=13;j++){
			var lc=document.getElementById(j+"."+i+"_1").value==""?0:document.getElementById(j+"."+i+"_1").value;
			if(document.getElementById(j+"."+i+"_1").value==""&& isSave==false) continue;
			if(j==4||j==7){
				if(parseFloat(lc)<0){
					alert("����ҵ����"+i+"��"+j+"("+lc+")��Ӧ��0���������Ŀ��д���ݡ�");
					document.getElementById(j+"."+i+"_1").focus();
					return false;
				}				
			}else{
				if(parseFloat(lc)<=0){
					alert("����ҵ����"+i+"��"+j+"("+lc+")��Ӧ>0���������Ŀ��д���ݡ�");
					document.getElementById(j+"."+i+"_1").focus();
					return false;
				}				
			}
		}
		if(i==8) continue;
		var lc1=document.getElementById("3."+i+"_1").value==""?0:document.getElementById("3."+i+"_1").value;
		var lc2=document.getElementById("4."+i+"_1").value==""?0:document.getElementById("4."+i+"_1").value;
		var lc3=document.getElementById("5."+i+"_1").value==""?0:document.getElementById("5."+i+"_1").value;
		var lc4=document.getElementById("6."+i+"_1").value==""?0:document.getElementById("6."+i+"_1").value;
		var lc5=document.getElementById("7."+i+"_1").value==""?0:document.getElementById("7."+i+"_1").value;
		var lc6=document.getElementById("8."+i+"_1").value==""?0:document.getElementById("8."+i+"_1").value;
		var lc10=document.getElementById("12."+i+"_1").value==""?0:document.getElementById("12."+i+"_1").value;
		
		if(document.getElementById("12."+i+"_1").value==""&& isSave==false) continue;
		if((parseFloat(lc2)+parseFloat(lc4)).toFixed(2)<parseFloat(lc10)){
			alert("����ҵ����"+i+"��2��("+lc2+")�롱��"+i+"��4��("+lc4+")�ĺ�("+(parseFloat(lc2)+parseFloat(lc4)).toFixed(2)+")Ӧ�ݡ���"+i+"��10��("+lc10+")���������Ŀ��д���ݡ�");
			document.getElementById("12."+i+"_1").focus();
			return false;
		}
		
		
		if(document.getElementById("5."+i+"_1").value==""&& isSave==false) continue;
		var jd=document.forms[0].jd.value;
		if(((parseFloat(lc1)*2/20)*(parseFloat(jd)/4)).toFixed(2)>=parseFloat(lc3)){
			alert("����ҵ����"+i+"��3��("+lc3+")Ӧ�ܡ���"+i+"��1������2/20��100%��������ֵ/4��ֵ("+((parseFloat(lc1)*2/20)*(parseFloat(jd)/4)).toFixed(2)+")���������Ŀ��д���ݡ�");
			document.getElementById("5."+i+"_1").focus();
			return false;
		}
		
		if(document.getElementById("4."+i+"_1").value==""&& isSave==false) continue;
		if(((parseFloat(lc1)*2/20)*(1/4)).toFixed(2)>=parseFloat(lc2)){
			alert("����ҵ����"+i+"��2��("+lc2+")Ӧ�ܡ���"+i+"��1������2/20��100%��/4��ֵ("+((parseFloat(lc1)*2/20)*(1/4)).toFixed(2)+")���������Ŀ��д���ݡ�");
			document.getElementById("4."+i+"_1").focus();
			return false;
		}
		
		if(document.getElementById("8."+i+"_1").value==""&& isSave==false) continue;
		if(((parseFloat(lc4)*2/20)*(parseFloat(jd)/4)).toFixed(2)>=parseFloat(lc6)){
			alert("����ҵ����"+i+"��6��("+lc6+")Ӧ�ܡ���"+i+"��4������2/20��100%��������ֵ/4��ֵ("+((parseFloat(lc4)*2/20)*(parseFloat(jd)/4)).toFixed(2)+")���������Ŀ��д���ݡ�");
			document.getElementById("8."+i+"_1").focus();
			return false;
		}
		
		if(document.getElementById("7."+i+"_1").value==""&& isSave==false) continue;
		if(((parseFloat(lc4)*2/20)*(1/4)).toFixed(2)>=parseFloat(lc5)){
			alert("����ҵ����"+i+"��5��("+lc5+")Ӧ�ܡ���"+i+"��4������2/20��100%��/4��ֵ("+((parseFloat(lc4)*2/20)*(1/4)).toFixed(2)+")���������Ŀ��д���ݡ�");
			document.getElementById("7."+i+"_1").focus();
			return false;
		}
	}
}
	
/*
function document.onkeydown(){
	var k=window.event.keyCode;
    if (k == '8'||k==8) {    	
		window.event.keyCode=0;
		window.event.returnValue=false;  
        return false;
   	}
}
 function avoidBackspace(e) {
 	//������ˡ�Backspace��������������Ч��
 	var k=e.keyCode;
 	alert(k);
    if (k == '8'||k==8) {    	
		window.event.keyCode=0;
		window.event.returnValue=false;  
        return false;
   	}
   }*/
</script>

<link href="<%=static_contextpath%>/css/text.css" rel="stylesheet" type="text/css">
<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="parseXmlOnLoad();initInput();">
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2>

      <jsp:include page="../../include/SbHeader.jsp" flush="true" >
        <jsp:param name="name" value="�̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ�" />
        <jsp:param name="help_url" value="help/wssb/sbzl/lygfzwhztz/qysdsjd/qysdsjd-000.htm"/>
      </jsp:include>
     </td>
  </tr>

  <tr>
    <td valign="top">
            <html:errors/>
    </td>
  </tr>
  <form id="gdzcjszjyjqkjb2014Form" name="gdzcjszjyjqkjb2014Form" action="gdzcjszjyjqkjb2014.dc" method="post">

  	<input name="actionType" type="hidden" id="actionType" value="Show">
  <tr>
    <td>
      <br>
      <table  align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1">�̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ�</td>
        </tr>
        <tr>
          <td class="1-td2">
            <table align="center" cellspacing="0" class="table-99">
             <tr class="black9">
               <td align="center" nowrap>
                 <div id="result"></div>
              </td>
             </tr>
            </table>  
            <br>
            <table>
              <tr>
                <td>
					<img src="<%=static_contextpath%>images/baocun1.jpg" onmouseover="this.src='<%=static_contextpath%>images/baocun2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/baocun1.jpg'" alt="����" onclick="return doSave()" style="cursor:hand">
					&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
                <td>
					<img src="<%=static_contextpath%>images/shanchu1.jpg" onmouseover="this.src='<%=static_contextpath%>images/shanchu2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/shanchu1.jpg'" alt="ɾ��" onclick="doDelete();" style="cursor:hand">
                    &nbsp;&nbsp;&nbsp;&nbsp;
                </td> 
                 <td>
					<img src="<%=static_contextpath%>images/qc-u1.jpg" onmouseover="this.src='<%=static_contextpath%>images/qc-u2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/qc-u1.jpg'" alt="���" onclick="doClear();" style="cursor:hand">
                    &nbsp;&nbsp;&nbsp;&nbsp;
                </td>               
                <td>
					<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="����" onclick="doReturn()" style="cursor:hand">
                	&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
                <td>
					<img src="<%=static_contextpath%>images/tc-c1.jpg" onmouseover="this.src='<%=static_contextpath%>images/tc-c2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/tc-c1.jpg'" alt="�˳�" onclick="doExit()" style="cursor:hand">
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
  </form>

</table>
<jsp:include page="../../include/bottom.jsp" flush="true"/>
</body>
</html>