<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
    String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
    String resourceContextPath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>

<html>
<head>
<title>北京市查帐征收个人独资企业和合伙企业投资者个人所得税年度申报表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="js/function.js">
</script>
<link href="<%=resourceContextPath%>css/text.css" rel="stylesheet" type="text/css">
<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
</head>

<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>
<script language="JavaScript" >
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
var oriXmlDoc;
var tempPostXml="";
var TSignData="";
//解析xml
function parseXmlOnLoad()
{
<logic:equal name="done" value='true'>
  var xslPath="/XSLTWEB/model/010004/XSLT/" +strXSLTVersion+".xsl";
  if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
     orixmlDoc = new ActiveXObject("Microsoft.XMLDOM");
     orixmlDoc.async = false;
     orixmlDoc.loadXML(strXml);
    }
  
  initAction();
  display(0);
  return true;
</logic:equal>
}

//生成申报数据和签名数据,这两项数据分别通过\r\n拼接而成
function getLocalPostXml(objForm,isSign)
{
  for(var j=0;j<tzzNum;j++)
  { 
    var localXMLDoc = new ActiveXObject("Microsoft.XMLDOM");
    if(localXMLDoc==null)
    {
      alert("无法创建localXMLDoc对象");
      return false;
    }
    localXMLDoc.loadXML("<taxdoc></taxdoc>");
    getLocalBasicXx(localXMLDoc,"xsltVersion","/taxdoc");
    getLocalBasicXx(localXMLDoc,"schemaVersion","/taxdoc");
    getLocalBasicXx(localXMLDoc,"ywlx","/taxdoc");
    getLocalBasicXx(localXMLDoc,"ywczlx","/taxdoc");
    appendLocalElement(localXMLDoc,"/taxdoc","nsrxx",["jsjdm","nsrmc","qylxdh","swjgzzjgdm"]);
    //申报信息
    appendLocalElement(localXMLDoc,"/taxdoc","sbxx",["fsdm","jd","nd","sbrq","sbxgrq","skssksrq","skssjsrq","done"]);
    
    var rootNode = localXMLDoc.selectSingleNode("/taxdoc");
    var oriNode = orixmlDoc.documentElement;

    appendLocalElement(localXMLDoc,"/taxdoc","czzbsj",["lrr","lrrq","cjsj"]);
    
    var tzfNode = localXMLDoc.createElement("tzfsj");
    
    //投资方数据
    nodeNameArray = new Array("tzzxm","zjhm","zjlxdm","cwfzr","jmsjcontrol");
    for(var i = 0;i<nodeNameArray.length;i++){
      var tmpNodeName = nodeNameArray[i];     
      var tmpNode= localXMLDoc.createElement(tmpNodeName);      
      var objCDATA = localXMLDoc.createCDATASection(document.getElementsByName(tmpNodeName).item(j).value);
      tmpNode.appendChild(objCDATA);
      tzfNode.appendChild(tmpNode);
      }

    for(var k = 0;k<10;k++){
        //alert("j="+j+" k="+k);
        var mxNode= localXMLDoc.createElement("tzfmx");
        var tmpNode= localXMLDoc.createElement("xmmc");
        var objCDATA = localXMLDoc.createCDATASection(oriNode.selectSingleNode("/taxdoc/tzfsj["+j+"]/tzfmx["+k+"]/xmmc").text);
        tmpNode.appendChild(objCDATA);
        mxNode.appendChild(tmpNode);
    
        tmpNode= localXMLDoc.createElement("hc");
        //alert("xh="+(tzzNum * (k-1) + (j-1)*1)+"  hc="+oriNode.selectSingleNode("/taxdoc/tzfsj["+j+"]/tzfmx["+k+"]/hc").text+"  xmmc="+oriNode.selectSingleNode("/taxdoc/tzfsj["+j+"]/tzfmx["+k+"]/xmmc").text+"  value="+document.forms[0].bl4[tzzNum * (k-1) + (j-1)*1].value);
        
        objCDATA = localXMLDoc.createCDATASection(oriNode.selectSingleNode("/taxdoc/tzfsj["+j+"]/tzfmx["+k+"]/hc").text);
        tmpNode.appendChild(objCDATA);
        mxNode.appendChild(tmpNode);
        tmpNode= localXMLDoc.createElement("bqljs");
        //alert(tzzNum * (k-1) + (j-1)*1);
        objCDATA = localXMLDoc.createCDATASection(document.forms[0].bl4[tzzNum * k + j*1].value);
        tmpNode.appendChild(objCDATA);
        mxNode.appendChild(tmpNode);        
        tzfNode.appendChild(mxNode);            
    
    }

    rootNode.appendChild(tzfNode);
    //申报数据列表
    var sbsjlistNode = localXMLDoc.createElement("sbsjlist");   
    for(var i = 0;i<18;i++){
        var sbxmNode= localXMLDoc.createElement("sbxm");
        var tmpNode= localXMLDoc.createElement("xmmc");
        var objCDATA = localXMLDoc.createCDATASection(oriNode.selectSingleNode("/taxdoc/sbsjlist/sbxm["+i+"]/xmmc").text);
        tmpNode.appendChild(objCDATA);
        sbxmNode.appendChild(tmpNode);
        tmpNode= localXMLDoc.createElement("hc");
        objCDATA = localXMLDoc.createCDATASection(oriNode.selectSingleNode("/taxdoc/sbsjlist/sbxm["+i+"]/hc").text);
        tmpNode.appendChild(objCDATA);
        sbxmNode.appendChild(tmpNode);
        tmpNode= localXMLDoc.createElement("bqljs");
        objCDATA = localXMLDoc.createCDATASection(document.forms[0].bl1[i].value);
        tmpNode.appendChild(objCDATA);
        sbxmNode.appendChild(tmpNode);        
        sbsjlistNode.appendChild(sbxmNode);     
      } 
    for(var i = 18;i<36;i++){
        var sbxmNode= localXMLDoc.createElement("sbxm");
        var tmpNode= localXMLDoc.createElement("xmmc");
        var objCDATA = localXMLDoc.createCDATASection(oriNode.selectSingleNode("/taxdoc/sbsjlist/sbxm["+i+"]/xmmc").text);
        tmpNode.appendChild(objCDATA);
        sbxmNode.appendChild(tmpNode);
        tmpNode= localXMLDoc.createElement("hc");
        objCDATA = localXMLDoc.createCDATASection(oriNode.selectSingleNode("/taxdoc/sbsjlist/sbxm["+i+"]/hc").text);
        tmpNode.appendChild(objCDATA);
        sbxmNode.appendChild(tmpNode);
        tmpNode= localXMLDoc.createElement("bqljs");
        objCDATA = localXMLDoc.createCDATASection(document.forms[0].bl2[i-18].value);
        tmpNode.appendChild(objCDATA);
        sbxmNode.appendChild(tmpNode);        
        sbsjlistNode.appendChild(sbxmNode);   
      } 
  
    for(var i = 36;i<40;i++){
        var sbxmNode= localXMLDoc.createElement("sbxm");
        var tmpNode= localXMLDoc.createElement("xmmc");
        var objCDATA = localXMLDoc.createCDATASection(oriNode.selectSingleNode("/taxdoc/sbsjlist/sbxm["+i+"]/xmmc").text);
        tmpNode.appendChild(objCDATA);
        sbxmNode.appendChild(tmpNode);
        tmpNode= localXMLDoc.createElement("hc");
        objCDATA = localXMLDoc.createCDATASection(oriNode.selectSingleNode("/taxdoc/sbsjlist/sbxm["+i+"]/hc").text);
        tmpNode.appendChild(objCDATA);
        sbxmNode.appendChild(tmpNode);
        tmpNode= localXMLDoc.createElement("bqljs");
        objCDATA = localXMLDoc.createCDATASection(document.forms[0].bl3[i-36].value);
        tmpNode.appendChild(objCDATA);
        sbxmNode.appendChild(tmpNode);        
        sbsjlistNode.appendChild(sbxmNode);   
      } 
      //分配比例数据
    for(var i = 0;i<4;i++){
        var fpblNode= localXMLDoc.createElement("fpblsj");
        var tmpNode= localXMLDoc.createElement("xmmc");
        var objCDATA = localXMLDoc.createCDATASection(oriNode.selectSingleNode("/taxdoc/sbsjlist/fpblsj["+i+"]/xmmc").text);
        tmpNode.appendChild(objCDATA);
        fpblNode.appendChild(tmpNode);
        tmpNode= localXMLDoc.createElement("hc");
        objCDATA = localXMLDoc.createCDATASection(oriNode.selectSingleNode("/taxdoc/sbsjlist/fpblsj["+i+"]/hc").text);
        tmpNode.appendChild(objCDATA);
        fpblNode.appendChild(tmpNode);
        tmpNode= localXMLDoc.createElement("bqljs");
        objCDATA = localXMLDoc.createCDATASection(document.forms[0].bl6[i].value);
        tmpNode.appendChild(objCDATA);
        fpblNode.appendChild(tmpNode);        
        tmpNode= localXMLDoc.createElement("fpbl");
        objCDATA = localXMLDoc.createCDATASection(document.forms[0].bl5[i].value);
        tmpNode.appendChild(objCDATA);
        fpblNode.appendChild(tmpNode);        
        sbsjlistNode.appendChild(fpblNode);   
      } 
  
  rootNode.appendChild(sbsjlistNode);
  var retstr ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+ localXMLDoc.xml;
  retstr=retstr.replace(/\r\n/g,"");
  var signData="";
  if(isSign)
  {
      signData= gSign(retstr);
      TSignData += signData+"\r\n";
  }
  tempPostXml += retstr+"\r\n";
  }
}
//提交表单数据 
function doLocalSubmitXML(objForm,aType,isSign,xmldata,ifsubmit)
{ 
  var strToPost="";
  var strAppType="";
  initXMLObject();

  //Check Parameter ////////////////////////////
  if (!objForm)
  {
    alert("无此: " + strFormName +" 表单！");
    return false;
  }
  if (!document.all("XML_Form"))
  {
    document.body.insertAdjacentHTML("BeforeEnd",IntHtml.HTMLFormString);
  }
  else
  {
    document.all("XML_Form").strSignData.value = "";
    document.all("XML_Form").actionType.value = "";
    document.all("XML_Form").appType.value = "";
    document.all("XML_Form").strXMLData.value = "";
    document.all("XML_Form").REQUEST_TOKEN.value = "";
  }
  getLocalPostXml(objForm,isSign);
  var strToPost = tempPostXml;
  if (xmldata != "")
  {
    strToPost = xmldata.replace(/\r\n/g,"");
    if(isSign)
    {
        TSignData=gSign(strToPost);
    }
  } 
  var sSignData=TSignData;
  if(isSign)
  {
    strAppType = gGetGlobalAppType();
  }
  document.all("XML_Form").strXMLData.value = strToPost;
  document.all("XML_Form").actionType.value = aType;
  document.all("XML_Form").strSignData.value = sSignData;
  document.all("XML_Form").appType.value = strAppType;
  document.all("XML_Form").REQUEST_TOKEN.value = strREQUEST_TOKEN;

  //alert("签名结果：" + document.all("XML_Form").strSignData.value);
  //alert("actionType:" + document.all("XML_Form").actionType.value);
  //alert("appType:" + document.all("XML_Form").appType.value);
  //alert("表单内容组XML：" + strToPost);
  //alert("REQUEST_TOKEN:" + document.all("XML_Form").REQUEST_TOKEN.value);

  document.all("XML_Form").action = objForm.action;
  document.all("XML_Form").target = objForm.target;
  //Submit Form/////////////////////////////////
  if (ifsubmit)
  {
    document.all("XML_Form").submit();
  }
  return true;
}
function getLocalBasicXx(localXMLDoc,strTag,strPath)
{
  var objNode = localXMLDoc.selectSingleNode(strPath);
  var objTemp = localXMLDoc.createElement(strTag);
  objNode.appendChild(objTemp);
  
  var strValue=formString(document.getElementById(strTag).value);
  var objCDATA = localXMLDoc.createCDATASection(strValue);
  objTemp.appendChild(objCDATA);
}

function getLocalChildren(localXMLDoc,temp,strTag)
{
  var element=document.getElementById(strTag);
  if(element!=null)
  {
    strValue=formString(element.value);
    var objTemp=temp;
    var node=localXMLDoc.createElement(strTag);
    objTemp.appendChild(node);
    var objCDATA = localXMLDoc.createCDATASection(strValue);
    node.appendChild(objCDATA);
  }
  
}
function appendLocalElement(localXMLDoc,root,node,tags)
{
  var objNode = localXMLDoc.selectSingleNode(root);
  var objTemp = localXMLDoc.createElement(node);
  for (i = 0; i < tags.length ; i++)
  {
    getLocalChildren(localXMLDoc,objTemp,tags[i]);
  }
  if (objTemp.hasChildNodes())
  {
    objNode.appendChild(objTemp);
  }
}



function formatDIV(obj)
{
  var tmpValue = trim(obj.innerText);
  var pointIndex = tmpValue.indexOf(".");
  if(pointIndex < 0)
  {
    if(tmpValue == null || tmpValue == "" || tmpValue == ".")
    {
      tmpValue = "0.00";
    }
    else
    {
      tmpValue += ".00";
    }
  }
  else
  {
    afterpoint = (tmpValue.length-1) - pointIndex;
    if(afterpoint == 0)
    {
      tmpValue += "00";
    }
    else if(afterpoint == 1)
    {
      tmpValue += "0";
    }
  }
  obj.innerText = tmpValue;
}
<%/*自动计算*/%>

  <%/*投资者序号*/%>
var tzzNum = 0;
var tzzNO = "";

function computer1()
{
<%/*企业利润总额 5=1-2-3-4*/%>
  var hj14 = parseFloat(document.forms[0].bl1[1].value) + parseFloat(document.forms[0].bl1[2].value) + parseFloat(document.forms[0].bl1[3].value);
  document.forms[0].bl1[4].value =Math.round((parseFloat(document.forms[0].bl1[0].value)- hj14)*100)/100;
  div5.innerText = document.forms[0].bl1[4].value;
  formatDIV(div5);
  computer6();
}

function computer2()
{
<%/*纳税调整增加额 6 = 7+19+30*/%>
  document.forms[0].bl1[5].value = Math.round((parseFloat(document.forms[0].bl1[6].value) +  parseFloat(document.forms[0].bl2[0].value) +  parseFloat(document.forms[0].bl2[11].value))*100)/100;
  div6.innerText = document.forms[0].bl1[5].value;
  formatDIV(div6);
  computer6();
}

function computer21()
{
<%/*超过规定比例扣除的项目 7 = 8+9+10+11+12+13+14+15+16+17+18*/%>
  document.forms[0].bl1[6].value = Math.round((parseFloat(document.forms[0].bl1[7].value) + parseFloat(document.forms[0].bl1[8].value) + parseFloat(document.forms[0].bl1[9].value) + parseFloat(document.forms[0].bl1[10].value) + parseFloat(document.forms[0].bl1[11].value) + parseFloat(document.forms[0].bl1[12].value) + parseFloat(document.forms[0].bl1[13].value) + parseFloat(document.forms[0].bl1[14].value) + parseFloat(document.forms[0].bl1[15].value) + parseFloat(document.forms[0].bl1[16].value) + parseFloat(document.forms[0].bl1[17].value))*100)/100;
  div7.innerText = document.forms[0].bl1[6].value;
  formatDIV(div7);
  computer2();
}

function computer3()
{
<%/*不允许扣除的项目 19 = 20+21+22+23+24+25+26+27+28+29*/%>
  document.forms[0].bl2[0].value = Math.round((
  parseFloat(document.forms[0].bl2[1].value) + parseFloat(document.forms[0].bl2[2].value) + parseFloat(document.forms[0].bl2[3].value) + parseFloat(document.forms[0].bl2[4].value) + parseFloat(document.forms[0].bl2[5].value) + parseFloat(document.forms[0].bl2[6].value) + parseFloat(document.forms[0].bl2[7].value) + parseFloat(document.forms[0].bl2[8].value) + parseFloat(document.forms[0].bl2[9].value) + parseFloat(document.forms[0].bl2[10].value))*100)/100;
  div19.innerText = document.forms[0].bl2[0].value;
  formatDIV(div19);
  computer2();
}

function computer4()
{
<%/*应税收益项目 30 = 31 + 32*/%>
  document.forms[0].bl2[11].value = Math.round((parseFloat(document.forms[0].bl2[12].value) + parseFloat(document.forms[0].bl2[13].value))*100)/100;
  div30.innerText = document.forms[0].bl2[11].value;
  formatDIV(div30);
  computer2();
}

function computer5()
{
<%/*纳税调整减少额 33 = 34+35+36+37*/%>
  document.forms[0].bl2[14].value = Math.round((parseFloat(document.forms[0].bl2[15].value) + parseFloat(document.forms[0].bl2[16].value) + parseFloat(document.forms[0].bl2[17].value) + parseFloat(document.forms[0].bl3[0].value))*100)/100;
  div33.innerText = document.forms[0].bl2[14].value;
  formatDIV(div33);
  computer6();
}

function computer6()
{
<%/*调整后的应纳税所得额 38 = 5+6-33*/%>
  document.forms[0].bl3[1].value = Math.round((parseFloat(document.forms[0].bl1[4].value) + parseFloat(document.forms[0].bl1[5].value) - parseFloat(document.forms[0].bl2[14].value))*100)/100;
  if(document.forms[0].bl3[1].value < 0)
  {
    document.forms[0].bl3[1].value = 0;
  }
  div38.innerText = document.forms[0].bl3[1].value;
  formatDIV(div38);
        if(tzzNO != null && tzzNO!= "")
  {
    var tzzNOtmp = tzzNO;
  }
  else
  {
    var tzzNOtmp = 0;
  }
  for(var i=0;i<tzzNum;i++)
  {
    tzzNO = i;
    computerTzz();
  }
  display(tzzNOtmp);
}

function checkbl5()
{
  var bl1 = parseFloat(document.forms[0].bl5[0].value);
  var bl2 = parseFloat(document.forms[0].bl5[1].value);
  var bl3 = parseFloat(document.forms[0].bl5[2].value);
  var bl4 = parseFloat(document.forms[0].bl5[3].value);
  <%/*分配比例原来是每个都可以小于100，每个之间没有联系，
        但是后来测试组提出四个的和不能大于100，所以改成现在的样子，
  如果你是来改这个问题的，去掉本函数就行。*/%>
  var bl = bl1 + bl2 + bl3 + bl4;
  if(bl > 100){
    alert("所有分配比例之和不能大于100！");
    return false;
  }
}

function computerTzz()
{
  if (tzzNO == "" || tzzNO == 0)
  {
    tzzNO = 0;
    if(tzzNum > 1)
    {
      document.forms[0].sel[tzzNO].checked = true;
    }
    else if(tzzNum == 1)
    {
      document.forms[0].sel.checked = true;
    }
  }

<%/*投资者应纳所得税额 40 = 38*39*/%>
    var v39 = parseFloat(document.forms[0].bl4[tzzNO].value);
    var v40 = Math.round(parseFloat(document.forms[0].bl3[1].value) * v39)/100;
    document.forms[0].bl4[tzzNum*1 + tzzNO*1].value = v40;
    div40.innerText = v40;
    formatDIV(div40);

<%/*通过40的值查找合适的41和42*/%>
    for(var j = 0; j < document.forms[0].sl.length; j++)
    {
      var var1 = v40;
      var var2 = parseFloat(document.forms[0].ynsqss[j].value);
      var var3 = parseFloat(document.forms[0].ynszzs[j].value);
      if(var1 > var2 && (var1 <= var3 || var3 == 0))
      {
        document.forms[0].bl4[tzzNum*2 + tzzNO*1].value = document.forms[0].sl[j].value;
              div41.innerText = document.forms[0].sl[j].value;
              document.forms[0].bl4[tzzNum*3 + tzzNO*1].value = document.forms[0].sskcsvo[j].value;
              div42.innerText = document.forms[0].sskcsvo[j].value;
        formatDIV(div42);
            break;
      }else{
        document.forms[0].bl4[tzzNum*2 + tzzNO*1].value = 0;
                          div41.innerText = document.forms[0].bl4[tzzNum*2 + tzzNO*1].value;
                          document.forms[0].bl4[tzzNum*3 + tzzNO*1].value = 0;
                          div42.innerText = document.forms[0].bl4[tzzNum*3 + tzzNO*1].value;
        formatDIV(div42);
      }
    }

<%/*应纳所得税额 43 = 40*41-42*/%>
//    var v43 = Math.round((v40 * parseFloat(document.forms[0].bl4[tzzNum * 2 + tzzNO*1].value) - parseFloat(document.forms[0].bl4[tzzNum * 3 + tzzNO*1].value))*100)/100;
//    document.forms[0].bl4[tzzNum * 4 + tzzNO*1].value = v43;
 //   div43.innerText = v43;
//    formatDIV(div43);

<%/*应缴入库所得税额 45 = 43-44*/%>

//    var v45 = Math.round((v43 - parseFloat(document.forms[0].bl4[tzzNum * 5 + tzzNO*1].value))*100)/100;
//    var v45 = Math.round((parseFloat(document.forms[0].bl4[tzzNum * 4 + tzzNO*1].value) - parseFloat(document.forms[0].bl4[tzzNum * 5 + tzzNO*1].value))*100)/100;
//    if(v45 < 0)
//    {
//      v45 = 0;
//    }
//    document.forms[0].bl4[tzzNum * 6 + tzzNO*1].value = v45;
//    document.forms[0].dataInput[1].value = v45;
//    formatCurrency(document.forms[0].dataInput[1],0);


<%/*期末应补(退)所得税额48＝45+46-47*/%>
//    var v48 = Math.round((v45 + parseFloat(document.forms[0].bl4[tzzNum * 7 + tzzNO*1].value) - parseFloat(document.forms[0].bl4[tzzNum * 8 + tzzNO*1].value))*100)/100;
//    if(v48 < 0)
//    {
//      v48 = 0;
//    }
//    document.forms[0].bl4[tzzNum * 9 + tzzNO*1].value = v48;
//    document.forms[0].dataInput[4].value = v48;
//    formatCurrency(document.forms[0].dataInput[4],0);


}

function display(i)
{

   tzzNum = tzzTable.rows.length - 1;
  tzzNO = i;
  div39.innerText = document.forms[0].bl4[tzzNO].value + "%";
  
  div40.innerText = document.forms[0].bl4[tzzNum*1 + tzzNO*1].value;
  
  formatDIV(div40);
  div41.innerText = document.forms[0].bl4[tzzNum * 2 + tzzNO*1].value;
  div42.innerText = document.forms[0].bl4[tzzNum * 3 + tzzNO*1].value;
  formatDIV(div42);
//  div43.innerText = document.forms[0].bl4[tzzNum * 4 + tzzNO*1].value;
//  formatDIV(div43);
    
  if(document.forms[0].jmsjControl[tzzNO].value == "1"){
    document.forms[0].dataInput[0].readOnly = false;
    
  }else{
    document.forms[0].dataInput[0].readOnly = true;   
  }


  document.forms[0].dataInput43.value = document.forms[0].bl4[tzzNum * 4 + tzzNO*1].value;//43



  document.forms[0].dataInput[0].value = document.forms[0].bl4[tzzNum * 5 + tzzNO*1].value;//44
  document.forms[0].dataInput[1].value = document.forms[0].bl4[tzzNum * 6 + tzzNO*1].value;//45
  document.forms[0].dataInput[2].value = document.forms[0].bl4[tzzNum * 7 + tzzNO*1].value;//46
  document.forms[0].dataInput[3].value = document.forms[0].bl4[tzzNum * 8 + tzzNO*1].value;//47
  document.forms[0].dataInput[4].value = document.forms[0].bl4[tzzNum * 9 + tzzNO*1].value;//48
  computerTzz();


}

<%/*保存*/%>
function doSave(){
 	var old  = document.forms[0].ywczlx.value;
  if(confirm(confirmSave)){
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
    if (g_objSI.Container != '')
    {   
					if (!doLocalSubmitXML(document.forms[0],"Save",true,"",true))
					{
							document.forms[0].ywczlx.value = old;
							return false;
					}	
			}else
			{
					if (!doLocalSubmitXML(document.forms[0],"Save",false,"",true))
					{
							document.forms[0].ywczlx.value = old;
							return false;
					}	
			}
      
    return true;
  }else{
      return false;
  }
      
}
<%/*返回*/%>
function doReturn(){
  if(confirm(confirmReturn)){
          document.forms[0].actionType.value = "Return";
    document.forms[0].submit();
          return true;
      }else{
          return false;
      }
}
<%/*删除*/%>
function doDelete(){
		var old=document.forms[0].ywczlx.value;
    if((document.forms[0].ywczlx.value == 1) && confirm(confirmDelete))
    {
				document.forms[0].actionType.value="Delete";
     		 changeLocalYwczlx("3");   
				if (g_objSI.Container != '')
				{
						if (!doLocalSubmitXML(document.forms[0],"Delete",true,orixmlDoc.xml,true))
						{
							changeLocalYwczlx(old);
							return false;
						}
				}else
				{
						 if(!doLocalSubmitXML(document.forms[0],"Delete",false,orixmlDoc.xml,true))
							{
						   changeLocalYwczlx(old);
								return false;
					   }
				}
			return true;
   }else
   {
   		return false;
   }
}
function changeLocalYwczlx(ywczlx)
{
		  var rootNode = orixmlDoc.documentElement;
      var objCDATA =orixmlDoc.createCDATASection(ywczlx);
			rootNode.selectSingleNode("//ywczlx").text = "";
			rootNode.selectSingleNode("//ywczlx").appendChild(objCDATA);
}
  //设置tabIndex和change函数
function initAction()
{
    //1-4
  for(var i=0;i<=3;i++){
     document.forms[0].bl1[i].onchange=function(){        
          return(checkvalue(this,0)&&formatCurrency(this,0)&&computer1());
       }
    document.forms[0].bl1[i].tabIndex = i+1;
  }
//7
    document.forms[0].bl1[6].onchange=function(){        
        return(checkvalue(this,0)&&formatCurrency(this,0)&&computer2());
    }
  document.forms[0].bl1[6].tabIndex = 7;
//8-18
    for(var i=7;i<=17;i++){
     document.forms[0].bl1[i].onchange=function(){        
          return (checkvalue(this,0)&&formatCurrency(this,0)&&computer21());
       }
    document.forms[0].bl1[i].tabIndex = i+1;
  }
//20-29
    for(var i=1;i<=10;i++){
     document.forms[0].bl2[i].onchange=function(){        
          return(checkvalue(this,0)&&formatCurrency(this,0)&&computer3());
       }
    document.forms[0].bl2[i].tabIndex = i+19;
  }
//31-32
    for(var i=12;i<=13;i++){
     document.forms[0].bl2[i].onchange=function(){        
          return(checkvalue(this,0)&&formatCurrency(this,0)&&computer4());
       }
    document.forms[0].bl2[i].tabIndex = i+19;
  }
//33    
  document.forms[0].bl2[14].onchange=function(){        
        return (checkvalue(this,0)&&formatCurrency(this,0));
    }
  document.forms[0].bl2[14].tabIndex = 33;
//34-36
    for(var i=15;i<=17;i++){
     document.forms[0].bl2[i].onchange=function(){        
          return(checkvalue(this,0)&&formatCurrency(this,0)&&computer5());
       }
     document.forms[0].bl2[i].tabIndex = i+19;
  }
//37
  document.forms[0].bl3[0].onchange=function(){        
          return(checkvalue(this,0)&&formatCurrency(this,0)&&computer5());
    }
  document.forms[0].bl3[0].tabIndex = 37;


//43
    document.forms[0].dataInput43.readOnly=false;
  	document.forms[0].dataInput43.onchange=function(){        
          var t  = (checkvalue(this,0)&&formatCurrency(this,0));
          if(t == false ) return;
  				
  				document.forms[0].bl4[tzzNum * 4 + tzzNO*1].value = document.forms[0].dataInput43.value  ;//43
    }

//44-48
    //document.forms[0].dataInput[1].readOnly="true";
    document.forms[0].dataInput[1].readOnly=false;
    document.forms[0].dataInput[2].readOnly=false;
    document.forms[0].dataInput[3].readOnly=false;
    document.forms[0].dataInput[4].readOnly=false;

  for(var i=0;i<=4;i++){
     if (i==1) continue;
    document.forms[0].dataInput[i].tabIndex = i+44;
  }
  //44
  document.forms[0].dataInput[0].onchange=function(){        
           if(checkvalue(this,0)&&formatCurrency(this,0))
       { document.forms[0].bl4[tzzNum * 5 + tzzNO*1].value = this.value; computerTzz(); return true;
       }else{
       return false;
       }
  }   

  //45
  document.forms[0].dataInput[1].onchange=function(){        
           if(checkvalue(this,0)&&formatCurrency(this,0))
       { document.forms[0].bl4[tzzNum * 6 + tzzNO*1].value = this.value; computerTzz(); return true;
       }else{
       return false;
       }
  } 
   
  //46
  document.forms[0].dataInput[2].onchange=function(){        
           if(checkvalue(this,0)&&formatCurrency(this,0))
       { document.forms[0].bl4[tzzNum * 7 + tzzNO*1].value = this.value; computerTzz(); return true;
       }else{
       return false;
       }
  }
  //47     
  document.forms[0].dataInput[3].onchange=function(){        
           if(checkvalue(this,0)&&formatCurrency(this,0))
       { document.forms[0].bl4[tzzNum * 8 + tzzNO*1].value = this.value; computerTzz(8); return true;
       }else{
       return false;
       }
  }    
  document.forms[0].dataInput[4].onchange=function(){        
           if(checkvalue(this,0)&&formatCurrency(this,0))
       { document.forms[0].bl4[tzzNum * 9 + tzzNO*1].value = this.value; computerTzz(9); return true;
       }else{
       return false;
       }
  } 
  
//49
  document.forms[0].bl3[2].onchange=function(){        
          if(checkvalue(this,0)){
        if(!isDigit(this.value)) {
          alert('人数必须为整数！');
          this.value = 0;
        }
        return true;
      }else{
        return false;
      }
    }
  document.forms[0].bl3[2].tabIndex = 49;
//50
  document.forms[0].bl3[3].onchange=function(){        
          return (checkvalue(this,0)&&formatCurrency(this,0));
    }
  document.forms[0].bl3[3].tabIndex = 50;
//51-54
    for(var i=0;i<=3;i++){
     document.forms[0].bl5[i].onchange=function(){        
          return (checkvalue(this,2)&&checkbl5());
       }
    document.forms[0].bl5[i].tabIndex = i+51;
     document.forms[0].bl6[i].onchange=function(){        
          return (checkvalue(this,0)&&formatCurrency(this,0));
       }
    document.forms[0].bl6[i].tabIndex = i+55;
  }

  //alert(document.getElementById("result").innerHTML);

}


</script>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onLoad="parseXmlOnLoad();">
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2>

      <jsp:include page="../include/SbHeader.jsp" flush="true" >
        <jsp:param name="name" value="查帐征收个人独资企业和合伙企业投资者个人所得税年度申报表" />
    <jsp:param name="help_url" value="help/wssb/sbzl/czzsnd/czzsnd-000.htm"/>
      </jsp:include>

      <table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td valign="top">
       <br>
       <%@//include file="../include/ShowErrMsg.jsp"%>
        <html:errors/>

    <table  align="center" cellspacing="0" class="table-99">
                    <tr>
              <td class="1-td1">查帐征收个人独资企业和合伙企业投资者个人所得税年度申报表</td>
                    </tr>

<form name="form1" method="POST" action="/shenbao/czzsnd.dc">
      <input name="actionType" type="hidden" id="actionType" value="Show">

<logic:equal name="done" value='true'>

                    <tr>
              <td valign="top" class="1-td2"><br>
    <div id="result"></div>
  <br>
     
     <logic:iterate id="slbsj" name="slbsjList"
  type="com.ttsoft.bjtax.shenbao.model.domain.Szsm">
  <input type="hidden" name="sl" value="0.00"/>
  <input type="hidden" name="szsmdm" value="<shenbao:write name='slbsj' property='szsmdm'/>"/>
  <input type="hidden" name="szsmmc" value="<shenbao:write name='slbsj' property='szsmmc'/>"/>
  <input type="hidden" name="ynsqss" value="<shenbao:write name='slbsj' property='ynsqss'/>"/>
  <input type="hidden" name="ynszzs" value="<shenbao:write name='slbsj' property='ynszzs'/>"/>
  <input name="sskcsvo" type="hidden" value="0.00">
</logic:iterate>
     
                        
                          </td>
                          </tr>
                    </table><br>
                  <TABLE border=0 cellPadding=0 cellSpacing=0 width="100%">
          <TBODY>
                                          <TR>
                                                  <TD height="40" colspan="8"> <DIV align="center">
        <img src="<%=static_contextpath%>images/baocun1.jpg" onMouseOver="this.src='<%=static_contextpath%>images/baocun2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/baocun1.jpg'" alt="保存" onClick="doSave();" style="cursor:hand">
        &nbsp;&nbsp;&nbsp;&nbsp;
        <img src="<%=static_contextpath%>images/shanchu1.jpg" onMouseOver="this.src='<%=static_contextpath%>images/shanchu2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/shanchu1.jpg'" alt="删除" onClick="doDelete();" style="cursor:hand">
        &nbsp;&nbsp;&nbsp;&nbsp;
        <img src="<%=static_contextpath%>images/fanhui1.jpg" onMouseOver="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onClick="doReturn();" style="cursor:hand">
              <BR><BR></DIV>
              </TD>
                                                </TR>
                                        </TBODY>
                                </TABLE><BR>
              </td>
                    </tr>
</logic:equal>
<logic:equal  name="done" value='false'>
  <tr>
    <td class="1-td1">
        <img src="<%=static_contextpath%>images/fanhui1.jpg" onMouseOver="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onClick="doReturn();" style="cursor:hand">
    </td>
  </tr>
</logic:equal>
    </form>
        </table>
      </td>
    </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td valign="bottom" align="center">
  <%@ include file="../include/bottom.jsp" %>
    </td>
  </tr>
 </table>
</body>
</html>