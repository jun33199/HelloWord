<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>



<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Language" content="zh-cn">
<link href="../css/beijing.css" rel="stylesheet" type="text/css">
<title>��֪�������ѯ</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/function.js"></script>
<script language="JavaScript">
function doSelectSubmit()
{
 //ѡ�������������룬���������check
  //alert("��ӭ");
 // alert(document.all.dqjs.value.length+"---------------document.all.dqjs.value.length");
 // alert(document.all.dqjs.value.length+"---------------document.all.dqjs.value.length");
  if(document.all.dqjs.value.length==0&&document.all.jsjdm.value.length==0){
  alert("û������:���������������ѡ�����ؾ֣�");
  return false;
  }
   var strJsjdm = document.forms[0].jsjdm.value;
  // alert("�����������ʲô��"+strJsjdm);
    if( trim(strJsjdm).length!=0&&trim(strJsjdm).length!=8 && trim(strJsjdm).length!=7 ){
      alert("ѡ�������������룬����������������룬��ʽΪ8λ����");
      return false;
    }
   
  //������ʼ����Check
  strGzqsrq =document.all.gzqsrq.value;
  strGzjzrq =document.all.gzjzrq.value;
  if(strGzqsrq.length!=0&&strGzjzrq.length==0||strGzqsrq.length==0&&strGzjzrq.length!=0){
   alert("�������������������飡");
   return false;
  }
  if(isFullDate(strGzqsrq,0,null,false)==false){
    alert("��֪��ʼ���ڸ�ʽ����ȷ��");
    return false;
  }
  //���ڽ�������Check
  
  if(isFullDate(strGzjzrq,0,null,false)==false){
    alert("��֪��ֹ���ڸ�ʽ����ȷ��");
    return false;
  }
  //������ʼ���ں����ڽ������ڹ�ϵCheck
 if( compare(strGzjzrq,strGzqsrq)<0 ){
    alert("��֪������ʼ����ӦС�ڸ�֪���ͽ������ڡ�");
    return false;
  }

  if(trim(strJsjdm).length==8||trim(strJsjdm).length==7){
  document.all.actionType.value="query";
  document.forms[0].submit();
  }else{
  if(document.all.dqjs.value.substring(2,4)=="00"&&document.all.cxdqjs.value.length==0){
    alert("����ѡ��˰������");
     return false;
  }
  if(document.all.dqjs.value.substring(2,4)!="00"){
   var a=document.all.dqjs.value;
   document.all.cxdqjs.value=a;
   }
   var hylb=document.getElementById("hylb");
   var hylbvalue='hylb';
   for (i=0; i < hylb.options.length; i++) {
	       if (hylb.options[i].selected ) {
	         hylbvalue=hylbvalue+","+hylb.options.item(i).value;
	       }
	     }
	     //alert(hylbvalue+"-------hylbvalue");
	     // alert(hylbvalue.length+"-------hylbvalue");
	     document.all.hylb1.value=hylbvalue;
	     
   var qylx=document.getElementById("qylx");
   var qylxvalue='qylx';
   for (i=0; i < qylx.options.length; i++) {
	       if (qylx.options[i].selected ) {
	        
	         qylxvalue=qylxvalue+","+qylx.options.item(i).value;
	       }
	     }
	   // alert(qylxvalue+"-------qylxvalue");
	   // alert(qylxvalue.length+"-------qylxvalue");
	     document.all.qylx1.value=qylxvalue;
   
   var cxdqjs1=document.getElementById("cxdqjs").value;
   var jxdm1=document.getElementById("jxdm").value;
  // alert(cxdqjs1+"---value-----------------");
   document.all.cxdqjs1.value=cxdqjs1;
   document.all.jxdm1.value=jxdm1;
   document.all.actionType.value="query";
   document.forms[0].submit();
   }
 
}
function compare(value1,value2){
  if(parseInt(value1)<parseInt(value2)){
    return -1;
  }else if(parseInt(value1)==parseInt(value2)){
    return 0;
  }else{
    return 1;
  }
}
function toweidu(value1,value2){
  document.all.gzsxnrbt.value=value1;
  document.all.gzsx_id.value=value2;
  document.all.actionType.value="getwydqd";
   document.forms[0].submit();
}
function toyuedu(value1,value2){
  document.all.gzsxnrbt.value=value1;
  document.all.gzsx_id.value=value2;
  document.all.actionType.value="getydqd";
   document.forms[0].submit();
}

function doQuery(num) {
       var dqjs = '<bean:write name="gzsxwhForm" property="dqjs"/>';
  //     alert(dqjs+"-------------dqjs")
        document.all.dqjs.value = dqjs;
        var cxdqjs = '<bean:write name="gzsxwhForm" property="cxdqjs1"/>';
     // alert(cxdqjs+"-----------------");
        document.all.cxdqjs1.value= cxdqjs;
         //var jxdm = '<bean:write name="gzsxwhForm" property="jxdm"/>';
        //document.all.jxdm.value = jxdm;
     var pageIndex = parseInt(document.all.pgNum.value);
     //alert(pageIndex+"--------------pageIndex----");
      var pageNum = parseInt(document.all.pgSum.value);
       //alert(pageNum+"--------------pageNum----");
      if (num == 1) {
       document.all.pgNum.value="1";
      }else if (num == 0) {
        if (pageIndex > 1) {
         document.all.pgNum.value=pageIndex-1;
        }else{
         document.all.pgNum.value="1"
        }
      }else if (num == -1) {
        if (pageIndex <= pageNum-1) {
         document.all.pgNum.value=pageIndex+1;
        }else{
          document.all.pgNum.value=pageNum;
        }
      } else if (num ==-2){
        document.all.pgNum.value=pageNum;
      }     
    document.all.actionType.value="query";
    
   document.forms[0].submit();
   }
  function exportExcel(){
     //alert("daochu ");
     document.all.actionType.value="ExportExcel2";
     var tj;
     dqjs=document.getElementById("dqjs");
     tj='��������  '+dqjs.options.item(dqjs.selectedIndex).text;
     hylb=document.getElementById("hylb");
     tj=tj+'��ҵ���  '+hylb.options.item(hylb.selectedIndex).text;
     qylx=document.getElementById("qylx");
     tj=tj+'��ҵ����  '+qylx.options.item(qylx.selectedIndex).text;
     nsrzt=document.getElementById("nsrzt");
     tj=tj+'��˰��״̬  '+nsrzt.options.item(nsrzt.selectedIndex).text;
     jxdm=document.getElementById("jxdm");
     tj=tj+'��������  '+jxdm.options.item(jxdm.selectedIndex).text;
     tj=tj+'��ʼ��������  '+document.all.gzqsrq.value+'������������  '+document.all.gzjzrq.value
     document.all.nsrmc.value=tj
     document.forms[0].submit();
   }
 
 function ajaxgetsws(){
 //alert("hsh");
 var xmlhttp_request;
 var obj = document.getElementById("dqjs");
 if(obj.value.length==0){
 alert("����ѡ���־�");
 return false;
 }
 
 //alert(obj.value);
 if(window.ActiveXObject){
	       xmlhttp_request=new ActiveXObject("Microsoft.XMLHTTP");
	    }else if(window.XMLHttpRequest){
	       xmlhttp_request=new XMLHttpRequest();
	     } else {
	       return;
	   }
	     xmlhttp_request.open("GET", "gzsxfkcxAction.do?actionType=getsws&dqjs="+obj.value, true);
     	 xmlhttp_request.send(null);
	     xmlhttp_request.onreadystatechange =function zdyprocessAjaxResponse(){
	      var xmlDOM;
         var obj1 = document.getElementById("cxdqjs");
         var obj2 = document.getElementById("jxdm");
         var oOption;
         var length1;
         var length2;
         var len1;
         var len2;
         var k;
        if (xmlhttp_request.readyState == 4) {
        if (xmlhttp_request.status == 200) {
       xmlDOM = xmlhttp_request.responseXML;
       length1 = xmlDOM.getElementsByTagName("gV").length;
       length2 = xmlDOM.getElementsByTagName("sV").length;
       len1=1;
       len2=1;
       while(len1!=0){
       len1=obj1.options.length;
       for(var i=0;i<len1;i++)
         obj1.options.remove(i);
         len1=len1/2;
       }
        while(len2!=0){
       len2=obj2.options.length;
       for(var i=0;i<len2;i++)
         obj2.options.remove(i);
         len2=len2/2;
       }
       oOption = document.createElement("option");
         oOption.text="";
         oOption.value="";
         obj1.add(oOption);
       for (k=0; k<length1;k++) {
         oOption = document.createElement("option");
         oOption.text= xmlDOM.getElementsByTagName("gU")[k].firstChild.data;
         oOption.value= xmlDOM.getElementsByTagName("gV")[k].firstChild.data;
         obj1.add(oOption);
       }
        oOption = document.createElement("option");
         oOption.text="";
         oOption.value="";
         obj2.add(oOption);
       for (k=0; k<length2;k++) {
         oOption = document.createElement("option");
         oOption.text= xmlDOM.getElementsByTagName("sU")[k].firstChild.data;
         oOption.value= xmlDOM.getElementsByTagName("sV")[k].firstChild.data;
         obj2.add(oOption);
       }
       
     }
   }
	     };
 
 }
   function loadform(){
        //alert("222");
        var pgNum = '<bean:write name="gzsxwhForm" property="pgNum"/>';
        document.all.pgNum.value = pgNum;
        //alert("333");
        var pgSum = '<bean:write name="gzsxwhForm" property="pgSum"/>';
        document.all.pgSum.value = pgSum;
       // alert("444");
        var nsrzt = '<bean:write name="gzsxwhForm" property="nsrzt"/>';
        document.all.nsrzt.value = nsrzt;
       // alert("555");
        var dqjs = '<bean:write name="gzsxwhForm" property="dqjs"/>';
        //document.all.dqjs.value = dqjs;
       // alert("666");
        var hylb1 = '<bean:write name="gzsxwhForm" property="hylb1"/>';
      
        //alert(hylb1+"-------------hylb--");
       /* var hylbsz=hylb1.split(",");
         var hylb=document.getElementById("hylb");
        for(i=1;i<hylbsz.length;i++){
        alert(hylbsz[i]); 
        for (i=0; i < hylb.options.length; i++) {
	       if (hylb.options.item(i).value==hylbsz[i] ) 
	         hylb.options[i].selected;
	     }  
        }   */
        document.all.hylb1.value = hylb1;
       // alert("777");
        var qylx = '<bean:write name="gzsxwhForm" property="qylx1"/>';
      //  alert(qylx+"----------qylx---");
        document.all.qylx1.value =qylx;
       // alert("888");
        var gzqsrq = '<bean:write name="gzsxwhForm" property="gzqsrq"/>';
        document.all.gzqsrq.value = gzqsrq;
       // alert("999");
        var gzjzrq = '<bean:write name="gzsxwhForm" property="gzjzrq"/>';
        document.all.gzjzrq.value = gzjzrq;
       // alert("124");
        var jxdm = '<bean:write name="gzsxwhForm" property="jxdm1"/>';
        document.all.jxdm1.value = jxdm;
        //alert("jxdm"+jxdm);
        var count = '<bean:write name="gzsxwhForm" property="jlcount"/>';
        if(count.length>0)
        show_jlcount.innerHTML='&nbsp;&nbsp;'+"-------��¼��Ϊ��"+count+"-------";
        var cxdqjs = '<bean:write name="gzsxwhForm" property="cxdqjs1"/>';
       // alert(cxdqjs+"333-----------456");
        document.all.cxdqjs1.value= cxdqjs;
        }
        
        //alert("456");
 


</script>
</head>

<body  leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="loadform()">
<%@ include file="./include/header.jsp"%>
<html:form action="/webapp/smsb/gzsxfkcxAction.do" method="POST" >
<html:hidden property="actionType" value="" />
<html:hidden property="gzsxnrbt" />
<html:hidden property="gzsx_id" />
<html:hidden property="pgNum" />
<html:hidden property="pgSum" />
<html:hidden property="nsrmc" />
<html:hidden property="cxdqjs1" />
<html:hidden property="jxdm1" />
<html:hidden property="hylb1" />
<html:hidden property="qylx1" />



<table width="780" height="302" border="0" cellpadding="0" cellspacing="0"  align="center">
  <tr> 
    <td>
    	<br>

      	<table align="center" cellspacing="0"  class="table-99"  width="98%">
	       <tr>
	          <td class="1-td1" >��֪�������ѯ</td>
	        </tr>
	        <tr>
	          <td class="1-td2" >
			  	  <br>
            <table cellspacing="0" class="table-99" width="98%">

             <tr>
                <td  class="3-td1-left">
                  <div align="right">�����������룺&nbsp;&nbsp;</div></td>
                <td  class="3-td1-left"><div align="left">&nbsp;&nbsp;&nbsp;<html:text property="jsjdm" size="20" 

/></div></td>
                <td  class="3-td1-left">&nbsp;&nbsp;</td>
                 <td   class="3-td1-right">&nbsp;&nbsp;</div></td>            
              </tr>
                 <tr>
                <td class="2-td2-left"><div align="right">ѡ����ҵ���ͣ�&nbsp;&nbsp;</div></td>
                <td class="2-td2-left"><div align="left">&nbsp;&nbsp; <select name="hylb"   style="width:80%" id="hylb"  multiple size="5">
                <option value="" selected="selected"></option>
                <logic:iterate id="data" indexId="index" length="length" name="gzsxwhForm" property="hylxlilst" 

type="com.ttsoft.bjtax.smsb.model.client.hylxmodel">
                      <option value="<bean:write name="data" property="hylxid"/>">
                          <bean:write name="data" property="hylxmc"/>
                      </option>
                  </logic:iterate>
                 
                 </select> </div>
                <td class="2-td2-left" nowrap><div align="right">ѡ��Ǽ�ע�����&nbsp;&nbsp;</div></td>
                <td class="2-td2-center"><div align="left">&nbsp;&nbsp;<select name="qylx"  style="width:80%" id="qylx" multiple size="5">
                     <option value="" selected="selected"></option>
                 <logic:iterate id="data" indexId="index" length="length" name="gzsxwhForm" property="djlxlilst" 

type="com.ttsoft.bjtax.smsb.model.client.djlxmodel">
                      <option value="<bean:write name="data" property="djlxid"/>">
                          <bean:write name="data" property="djlxmc"/>
                      </option>
                    </logic:iterate>
                 </select></div></td>
              </tr>
              
              <tr>
                <td class="2-td2-left"><div align="right">ѡ�����ؾ֣�&nbsp;&nbsp;</div></td>
                <td class="2-td2-left"><div align="left">&nbsp;&nbsp; <select name="dqjs"  style="width:80%" id="dqjs" onchange="ajaxgetsws()">
                    <option value="" selected="selected"></option>
                    <logic:iterate id="data" indexId="index" length="length" name="gzsxwhForm" property="swdwlilst" 

type="com.ttsoft.bjtax.smsb.model.client.swdwmodel">
                      <option value="<bean:write name="data" property="swdwid"/>">
                          <bean:write name="data" property="swdwmc"/>
                      </option>
                   </logic:iterate>
                    
                </select></div>
                <td class="2-td2-left"><div align="right">ѡ��˰������&nbsp;&nbsp;</div></td>
                <td class="2-td2-center"><div align="left">&nbsp; <select name="cxdqjs"  style="width:80%" id="cxdqjs" >
                    <option value=""></option>
                     
                </select></div></td>
                
              </tr>
             
            
              <tr>
                <td class="2-td2-left"><div align="right">�������磺&nbsp;&nbsp;</div></td>
                <td class="2-td2-left"><div align="left">&nbsp;&nbsp;&nbsp;<select name="jxdm"  style="width:80%" id="jxdm">
                    <option value="" selected="selected"></option>
                </select></div></td>
                <td class="2-td2-left"><div align="right">��˰��״̬��&nbsp;&nbsp;</div></td>
                <td class="2-td2-center"><div align="left">&nbsp;&nbsp;<select name="nsrzt"  style="width:80%" id="nsrzt">
                    <option value="" selected="selected"></option>
                   <logic:iterate id="data" indexId="index" length="length" name="gzsxwhForm" property="nsrztlilst" type="com.ttsoft.bjtax.smsb.model.client.nsrztmodel">
                      <option value="<bean:write name="data" property="nsrztid"/>">
                          <bean:write name="data" property="nsrztmc"/>
                      </option>
                   </logic:iterate>  
                </select></div></td>
              </tr>  
           <tr>
                <td class="2-td2-left"><div align="right">��֪������ʼ���ڣ�&nbsp;&nbsp;</div></td>
                <td class="2-td2-left"><div align="left">&nbsp;&nbsp;&nbsp;<html:text property="gzqsrq" 

size="20"/></div></td>
                <td class="2-td2-left"><div align="right">��֪���ͽ�ֹ���ڣ�&nbsp;&nbsp;</div></td>
                <td class="2-td2-center"><div align="left">&nbsp;&nbsp;<html:text property="gzjzrq" size="20"/></div></td>
              </tr> 
                          
           </table> 
            <br>
            <table border="0" width="100%">
              <tr>
              <td width="27%">&nbsp; </td>
                
                <td width="40%"><input name="I2" type="image" accesskey="q" value="��ѯ"  onclick='doSelectSubmit();return false;' onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)" 

onMouseOut="MM_swapImgRestore()" alt="��ѯ" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" 

id="chaxun"></td>   
               <td width="40%"><input name="I3" type="image" accesskey="c" value="�Ƴ�"  onClick='tuichu();return false;' border=0 onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="�˳�" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" id="tuichu"> </td>
                  
                <td width="27%">&nbsp;</td>
              </tr>  
              <tr>
               <div align="center" id="show_jlcount" ><br></div>
              </tr>
            </table>		
             
           <br>
            <table id="gzsxlistTable" border="1" cellpadding="0" cellspacing="0" class="table-99" height="1" width="99%">
              <tr>
 
                <td width="16%" class="2-td1-left" height="1">��֪������</td>
                <td width="16%" class="2-td1-left" height="1">��֪���ݱ���</td>
                <td width="20%" class="2-td1-left" height="1">��֪����Ŀ�ĵ�λ</td>
                <td width="16%" class="2-td1-left" height="1">��֪��������</td>
                <td width="16%" class="2-td1-left" height="1">���Ķ��嵥</td>
                <td width="16%" class="2-td1-center" height="1">δ�Ķ��嵥</td>
              </tr>
              <%  com.ttsoft.bjtax.smsb.gzwh.web.GzsxwhForm form = (com.ttsoft.bjtax.smsb.gzwh.web.GzsxwhForm)request.getAttribute("gzsxwhForm");
                 if(form.getDqjs()!=null){%>
               <logic:iterate id="data" indexId="index" length="length" name="gzsxwhForm" property="tjgzsxlilst" 

                         type="com.ttsoft.bjtax.smsb.model.client.swjgtolist">
                          <tr>
                           <td width="16%" class="2-td2-left" height="23"><bean:write name="data" property="gzsx_id"/>&nbsp</td>
                           <td width="16%" class="2-td2-left" height="23"><a href=gzsxfkcxAction.do?actionType=getgzsxnr&gzsx_id=<bean:write name="data" property="gzsx_id" /> target=_blank ><bean:write name="data" property="gzsxnrbt"/></a>&nbsp</td>
                           <td width="20%" class="2-td2-left" height="23"><bean:write name="data" property="swjgzzjg"/>&nbsp</td>
                           <td width="16%" class="2-td2-left" height="23"><bean:write name="data" property="gzsxfcrq"/>&nbsp</td>
                           <td width="16%" class="2-td2-left" height="23">
                            <a href=gzsxfkcxAction.do?actionType=getydqd&ydcs=<bean:write name="data" property="ydcs"/> target=_blank >���Ķ��嵥</a>&nbsp</td>
                            <td width="16%" class="2-td2-left" height="23">
                            <a href=gzsxfkcxAction.do?actionType=getwydqd&ydcs=<bean:write name="data" property="ydcs"/> target=_blank >δ�Ķ��嵥</a>&nbsp</td>
                        </tr>
                     </logic:iterate>
                     </table>
                     <br>
            <table border="0" width="100%">
              <tr>
              <td width="5%">&nbsp; </td>
                <td width="40%"><input name="I2" type="image" accesskey="e" value="����Excel"  onClick="exportExcel();return false;" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/b-dcexcelb2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="����Excel" src="<%=static_contextpath%>images/b-dcexcelb1.jpg" width="100" height="25" id="daochu"></td>
                <td width="17%"><input name="I2" type="image" accesskey="s" value="��ҳ"  onClick="doQuery(1);return false;" alt="��ҳ" src="<%=static_contextpath%>images/diyiye2.jpg" width="79" height="22" id="shouye"></td>
                <td width="17%"><input name="I3" type="image" accesskey="f" value="��һҳ"  onClick='doQuery(0);return false;' border=0  alt="��һҳ" src="<%=static_contextpath%>images/shangyiye2.jpg" width="79" height="22" id="shangy"> </td>
                <td width="17%"><input name="I2" type="image" accesskey="s" value="��һҳ"  onClick="doQuery(-1);return false;"  alt="��һҳ" src="<%=static_contextpath%>images/xaiyiye2.jpg" width="79" height="22" id="xiay"></td>
                <td width="17%"><input name="I3" type="image" accesskey="f" value="ĩҳ"  onClick="doQuery(-2);return false;" border=0  alt="ĩҳ" src="<%=static_contextpath%>images/zuimoye2.jpg" width="79" height="22" id="moy"> </td>
                <td width="27%">&nbsp;</td>
              </tr>
            </table>		
              <% }else {%> 
            	  </table>
            	  <% }%>		
			</td>
	   	    </tr>
	    </table>
    </td>
  </tr>
 </table>
</html:form>
</body>
<%@ include file="./include/footer.jsp"%>

</html:html>