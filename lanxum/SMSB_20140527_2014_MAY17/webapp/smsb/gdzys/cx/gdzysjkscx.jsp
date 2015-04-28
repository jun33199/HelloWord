<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.ttsoft.framework.util.JspUtil"%>
<%@ page import="com.ttsoft.common.util.CodeConstants"%>
<%@ page import="java.util.*" %>
<%
//DhkscxForm form=(DhkscxForm)request.getAttribute("dhkscxForm");
//PKJBSJModel dhcxmodel = form.getPkjbsjModel();
//List plkkdhcxModelList = form.getPlkkdhcxlist();

%>
<html:html>
<head>
<title>耕地占用税缴款书查询</title>
<link href="../../css/text.css" rel="stylesheet" type="text/css">
<link href="../../css/top-box.css" rel="stylesheet" type="text/css">
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../../js/treatImage.js"></script>
<script language="JavaScript" src="../../js/smsb_common.js"></script>
<script language="JavaScript" src="../../js/DTable.js"></script>
<script language="JavaScript" src="../../js/InputSelect.js"></script>
<script language="JavaScript" src="../../js/function.js"></script>
<script language="javascript" src="../../js/gmit_selectcontrol.js"></script>
</head>
<SCRIPT LANGUAGE="JavaScript">
var swsdm='<bean:write name="gdzysJksCxForm" property="cxdqjs"/>';
var yhjb='<bean:write name="gdzysJksCxForm" property="yhjb"/>';
//var queryFlag='<bean:write name="gdzysJksCxForm" property="queryFlag"/>';
function doit(ope){
	if(!checkTwoDate(document.forms[0].ksrq.value,document.forms[0].jsrq.value)){
		return false;
	}
	document.forms[0].currentPage.value = "1";
    //doSubmitForm(ope);
	document.forms[0].actionType.value = "Query";
	document.forms[0].submit();   
}


  // 页面进入焦点确定
   function fnOnLoad()
  {
	  //alert(swsdm);
	   //function ajaxgetsws(false);
    //document.forms[0].dqjs.focus();
   ajaxgetsws('1');
   /*
   alert(swsdm);
   var obj1 = document.getElementById("cxdqjs");
   for(var i=0;i<obj1.options.length;i++)
   {
	   
       if(obj1.options[i].value==swsdm)
       {
    	   alert(obj1.options[i].value);
    	   obj1.options[i].selected=true;
           break;
       }
   }
   */
  }
   function ajaxgetsws(flag){
	   var xmlhttp_request;
	   var obj = document.getElementById("dqjs");
	   if(obj.value.length==0){
		   var obj1 = document.getElementById("cxdqjs");
		   obj1.style.width='80%';
		   var len1;
		   while(len1!=0){
		         len1=obj1.options.length;
		         for(var i=0;i<len1;i++)
		           obj1.options.remove(i);
		           len1=len1/2;
		         }
	   //alert("请先选定分局");
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
	  	     xmlhttp_request.open("GET", "gdzysJksCxAction.do?actionType=getsws&dqjs="+obj.value, true);
	       	 xmlhttp_request.send(null);
	  	     xmlhttp_request.onreadystatechange =function zdyprocessAjaxResponse(){
	  	      var xmlDOM;
	           var obj1 = document.getElementById("cxdqjs");
	           obj1.style.width='';
	           //var obj2 = document.getElementById("jxdm");
	           var oOption;
	           var length1;
	           //var length2;
	           var len1;
	           //var len2;
	           var k;
	          if (xmlhttp_request.readyState == 4) {
	          if (xmlhttp_request.status == 200) {
	         xmlDOM = xmlhttp_request.responseXML;
	         length1 = xmlDOM.getElementsByTagName("gV").length;
	         length2 = xmlDOM.getElementsByTagName("sV").length;
	         len1=1;
	         //len2=1;
	         while(len1!=0){
	         len1=obj1.options.length;
	         for(var i=0;i<len1;i++)
	           obj1.options.remove(i);
	           len1=len1/2;
	         }
	  
	         if(yhjb=='50'||yhjb=='40'){
		         oOption = document.createElement("option");
		         oOption.text="";
		         oOption.value="";
		         if(flag==true&&swsdm==oOption.value){
			            oOption.selected=true;
			     }
		         obj1.add(oOption);
	         }
	         for (k=0; k<length1;k++) {
	           oOption = document.createElement("option");
	           oOption.text= xmlDOM.getElementsByTagName("gU")[k].firstChild.data;
	           oOption.value= xmlDOM.getElementsByTagName("gV")[k].firstChild.data;
	           if(flag==true&&swsdm==oOption.value){
	            oOption.selected=true;
	           }
	           obj1.add(oOption);
	         }
	       
	       }
	     }
	  	 };
}

 //翻页
function doTurnPage(para){
        	 var currentPages = document.forms[0].currentPage.value;
            var maxPages = document.forms[0].maxPage.value;
            var queryFlag = document.forms[0].queryFlag.value;
            
            if(queryFlag == 'false'){
                alert("请先查询！");
                return;
    		}
    		if(para == 'first'){
                if(currentPages == 1){
    				alert('当前为第一页！');
    				return ;
                }
                document.forms[0].currentPage.value = 1;
    		}else if(para == 'previous'){
                if(currentPages == 1){
    				alert('当前为第一页！');
    				return ;
                }
                document.forms[0].currentPage.value = parseInt(currentPages) - 1;
    		}else if(para == 'next'){
                if(currentPages == maxPages){
    				alert('当前为最后一页！');
    				return ;
                }
                document.forms[0].currentPage.value = parseInt(currentPages) + 1;
    		}else if(para == 'last'){
                if(currentPages == maxPages){
    				alert('当前为最后一页！');
    				return ;
                }
                document.forms[0].currentPage.value = maxPages;
    		}else if(para == 'go'){
                switchNumb = document.forms[0].switchPage.value;
                currentNumb = document.forms[0].currentPage.value;
                if(!checkNumber(trimString(switchNumb))){
    				alert("您输入的是非数字字符，请您重新新输入！");
    				return;
                }
                if(switchNumb == currentNumb){
    				alert("当前就是第" + switchNumb + "页！");
    				return;
                }
                if(parseInt(switchNumb) > parseInt(tPage)){
    				alert("最大页数是" + tPage + "页，不能跳转到" + switchNumb + "页！");
    				return;
                }
                if(switchNumb <= '0'){
    				alert("请输入大于0的页面！");
                    return;
                }
                document.forms[0].currentPage.value = switchNumb;
    		}
    		document.forms[0].actionType.value = "Query";
    		document.forms[0].submit();   
   }

/* 
用途：判断是否是日期 
输入：date：日期；fmt：日期格式 
返回：如果通过验证返回true,否则返回false 
*/ 
function isDate(date, fmt) 
{ 
	if (fmt==null) fmt="yyyyMMdd";
	if(date.length!=fmt.length){
		return false; 
	}
	var yIndex = fmt.indexOf("yyyy"); 
	if(yIndex==-1) return false; 
	var year = date.substring(yIndex,yIndex+4); 
	var mIndex = fmt.indexOf("MM"); 
	if(mIndex==-1) return false; 
	var month = date.substring(mIndex,mIndex+2); 
	var dIndex = fmt.indexOf("dd"); 
	if(dIndex==-1) return false; 
	var day = date.substring(dIndex,dIndex+2); 
	if(!isNumber(year)||year>"2100" || year< "1900") return false; 
	if(!isNumber(month)||month>"12" || month< "01") return false; 
	if(day>getMaxDay(year,month) || day< "01") return false; 
	return true; 
}

/*
用途：检查输入字符串是否符合正整数格式 
输入： 
s：字符串 
返回： 
如果通过验证返回true,否则返回false 
*/ 
function isNumber(s)
{   
	var regu = "^[0-9]+$"; 
	var re = new RegExp(regu); 
	if (s.search(re) != -1)
	{ 
		return true; 
	} 
	else
	{ 
		return false; 
	} 
} 

function getMaxDay(year,month)
{ 
	if(month==4||month==6||month==9||month==11) 
	return "30"; 
	if(month==2) 
	if(year%4==0&&year%100!=0 || year%400==0) 
	return "29"; 
	else 
	return "28"; 
	return "31"; 
} 
function checkTwoDate(startDate,endDate) 
{ 
	if(startDate!=''&&!isDate(startDate,'yyyyMMdd')) { 
	alert("申报起始日期不正确!"); 
	return false; 
	}
	else if(endDate!=''&&!isDate(endDate,'yyyyMMdd') ) 
	{ 
	alert("申报终止日期不正确!"); 
	return false; 
	} else if( startDate!=''&&endDate!='') {
		if(startDate > endDate ){
			alert("申报起始日期不能大于申报终止日期!");
			return false; 
		}
	} 
	return true; 
} 
</SCRIPT>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
 <%@ include file="../../include/header.jsp"%>
<html:form action="/webapp/smsb/gdzys/gdzysJksCxAction.do" method="POST" >
<html:hidden property="actionType"/>
<html:hidden property="currentPage"/>
<html:hidden property="maxPage"/>
<html:hidden property="totalRowCount"/>
<html:hidden property="queryFlag"/>


    <logic:equal value="Query" name="gdzysJksCxForm" property="actionType">
    <bean:define id="plkklist" name="gdzysJksCxForm" property="dataList"/>
    <% 
    List dataList=(List)plkklist;
    if(dataList.size()==0){
    %>
	    <table width="96%" align="center" cellspacing="0" >
			<tr>
				<td><FONT SIZE="2" COLOR="#FF0000">没有查询到符合条件的结果！</FONT></td>
			</tr>
		</table>
	<% 
    }
	%>	
	</logic:equal>
	 
	<table width="96%" align="center" cellspacing="0" class="table-99">
		<tr>
			<td class="1-td1"  colspan="7">耕地占用税缴款书查询 </td>
		</tr>
		<tr>
			<td class="1-td2"  colspan="7">
			   <TABLE class=table-99 bgColor=#f3f3f3 border=0>
              <TBODY>
              <TR>
                <TD vAlign=top width="6%" height=20><IMG height=9 
                  src="<%=static_contextpath%>images/w-01.gif" width=9></TD>
                <TD class=black9 align=left width="32%">您可以输入或选择以下条件查询信息</TD>
                <TD align=left width="68%"></TD></TR></TBODY></TABLE>
                
            <TABLE class=table-99 id=table_LIST cellSpacing=0 border=0>
              <TBODY>
              <TR>
                <TD class=2-td2-t-left width="8%">分局</TD>
                <TD class=2-td2-t-left width="18%">
                 <div align=left > 
                 
                  <bean:define id="tempdqjs" name="gdzysJksCxForm" property="dqjs"/>
              
                 
                 <select name="dqjs"  style="width:80%" id="dqjs" onchange="ajaxgetsws('false')">
                 
                 <logic:equal value="50" name="gdzysJksCxForm" property="yhjb">
                    <option value="" <%=tempdqjs.equals("")?"selected":""%>></option>
                 </logic:equal>

                 <logic:iterate id="data" indexId="index" length="length" name="gdzysJksCxForm" property="swdwlist" type="com.ttsoft.bjtax.smsb.model.client.swdwmodel">
                    <bean:define id="tempswdwid" name="data" property="swdwid"/>
                      <option value="<bean:write name="data" property="swdwid"/>"  <%=tempdqjs.equals(tempswdwid)?"selected":""%>>
                      
                          <bean:write name="data" property="swdwmc"/>
                      </option>
                  </logic:iterate>
                </select>
                </div>
                  
                  
                  </TD>
                <TD class=2-td2-t-left width="9%">税务所</TD>
                <TD class=2-td2-t-left width="20%">
                  <div align="left">
                  <select name="cxdqjs"  style="width:80%" id="cxdqjs" >
                    <option value=""></option>
                   </select>
                   </div>
                  </TD>
                <TD class=2-td2-t-left width="10%">计算机代码</TD>
                <TD class=2-td2-t-center width="22%">
                  <div align="left"> 
                 <html:text property="jsjdm" size="20" />
                </div>
			  </TD>

             <!-- 
			 <TD class=2-td2-t-center width="8%" rowspan="2">
			       <input name="I2" type="image" accesskey="q" value="查询" onclick="doit('Query');return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="查询" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun" align="middle">
             </TD>
 			-->
			  </TR>
              
              <TR>
                <TD class=2-td2-left>缴款书状态</TD>
                <TD class=2-td2-left>
                  <DIV align=left>
                  <bean:define id="tempjkszt" name="gdzysJksCxForm" property="jkszt"/>
                  
                   <select name="jkszt"   id="jkszt" style="width:40%">
                      <option value="" <%=tempjkszt.equals("")?"selected":""%>>
                                                               全部
                      </option>
                      <option value="1" <%=tempjkszt.equals("1")?"selected":""%>>
                                                               已入库
                      </option>
                      <option value="0" <%=tempjkszt.equals("0")?"selected":""%>>
                                                               未入库
                      </option>
                  </select>
                  </DIV>
				</TD>
				 <TD class=2-td2-left>申报日期始</TD>
                <TD class=2-td2-left>
                  <DIV align=left><html:text property="ksrq" size="15" /><span>yyyyMMdd如：20130102</span></DIV>
				</TD>
				 <TD class=2-td2-left>申报日期止</TD>
                <TD class=2-td2-center>
                  <DIV align=left><html:text property="jsrq" size="15" /><span>yyyyMMdd如：20131231</span></DIV>
				</TD>
          </TR>
          
          <TR>
                <TD class=2-td2-left>批准占地文号</TD>
                <TD class=2-td2-left>
                  <DIV align=left>
                  <html:text property="pzzdwh" size="40" />
                  </DIV>
				</TD>
				 <TD class=2-td2-center colspan="4">&nbsp;</TD>
          </TR>
          
          
          
		  </TBODY>
		  </TABLE>
		  </TD>
		  </TR>
		  
		          <TR bgColor=#ffffff>
          <TD class=1-td2 vAlign=top align=middle width="82%"><BR>
            <DIV class=black9 id=layerView align=left><STRONG>您现在位置为第<SPAN 
            class=tishi><bean:write name="gdzysJksCxForm" property="currentPage"/></SPAN>页，共<SPAN class=tishi><bean:write name="gdzysJksCxForm" property="maxPage"/></SPAN>页，<SPAN 
            class=tishi><bean:write name="gdzysJksCxForm" property="totalRowCount"/></SPAN>条记录。</STRONG></DIV>
			<DIV style="FONT-SIZE: 13px" align=center width="50%"><STRONG>查询结果列表</STRONG></DIV>
            <TABLE class=table-99 id=table_LIST cellSpacing=0 border=0>
              <TBODY>
<!-- 开始-->
              <TR>
                <TD class=2-td1-left width="4%" >申报序列号</TD>
                <TD class=2-td1-left width="3%" >计算机代码</TD>
                <TD class=2-td1-left width="8%" >纳税人名称</TD>
                <TD class=2-td1-left width="4%" >缴款凭证号</TD>
				<TD class=2-td1-left width="3%" >缴款书类别</TD>
				<TD class=2-td1-left width="4%" >课税数量</TD>
				<TD class=2-td1-left width="4%" >实缴金额</TD>
				<TD class=2-td1-left width="4%" >申报日期</TD>
				<TD class=2-td1-left width="2%" >状态</TD>
				<TD class=2-td1-left width="4%" >入库日期</TD>
				<TD class=2-td1-left width="8%" >征收机关</TD>
                <TD class=2-td1-center width="6%" >备注</TD>
				</TR>
			
              <logic:iterate id="jkslist" name="gdzysJksCxForm" property="dataList" indexId="indexid">
               <tr>
              	<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="sbbxlh"/></td>
              	<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="jsjdm"/></td>
              	<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="nsrmc"/></td>
              	<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="jkpzh"/></td>
              	<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="splx"/></td>
              	<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="kssl"/></td>
              	<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="sjje"/></td>
              	<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="sbrq"/></td>
              	<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="jkszt"/></td>
              	<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="zyrq"/></td>
              	<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="zsjg"/></td>
              	<td class="2-td2-center" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="bz"/></td>
              	</tr>
              </logic:iterate> 
              </TBODY>
         </TABLE>
         
            <TABLE class=table-99 cellSpacing=0>
              <TBODY>
              <TR>
                <TD align=middle width="50%">
                  <DIV align=left><IMG id=first 
                  onmouseover="MM_swapImage('first','','<%=static_contextpath%>images/diyiye2.jpg',1)" 
                  onclick="doTurnPage('first')" onmouseout=MM_swapImgRestore() height=22 
                  src="<%=static_contextpath%>images/diyiye1.jpg" width=79 name=first> <IMG 
                  id=previous 
                  onmouseover="MM_swapImage('previous','','<%=static_contextpath%>images/shangyiye2.jpg',1)" 
                  onclick="doTurnPage('previous')" onmouseout=MM_swapImgRestore() 
                  height=22 src="<%=static_contextpath%>images/shangyiye1.jpg" width=79 
                  name=previous> <IMG id=next 
                  onmouseover="MM_swapImage('next','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" 
                  onclick="doTurnPage('next')" onmouseout=MM_swapImgRestore() height=22 
                  src="<%=static_contextpath%>images/xaiyiye1.jpg" width=79 border=0 
                  name=next> <IMG id=last 
                  onmouseover="MM_swapImage('last','','<%=static_contextpath%>images/zuimoye2.jpg',1)" 
                  onclick="doTurnPage('last')" onmouseout=MM_swapImgRestore() height=22 
                  src="<%=static_contextpath%>images/zuimoye1.jpg" width=79 border=0 
                  name=last> </DIV></TD>
                <TD width="15%">
                  <!--  
                  <DIV align=left>
                  <INPUT onkeydown=enterToPage(this.value) 
                  align=middle size=10 name=switchPage value="1"> <SPAN class=black9><FONT 
                  size=2>页</FONT></SPAN>
                  </DIV>
                  -->
                  </TD>
          
                  </TR></TBODY>
              </TABLE>
				  
				<table width="100%" border="0" cellpadding="0" cellspacing="4">
					<tr valign="bottom">
						<td width="50%" align="right">
						    <input name="I2" type="image" accesskey="q" value="查询" onclick="doit('Query');return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="查询" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun" >
						</td>
                        <td width="50%" align="left">	
							<input type="image" accesskey="c"   onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22">
						</td>
                      </tr>
				</table>
         </TD></TR>
		
	</table>
	<br>
	<br>
	<br>
	  
<%@ include file="../../include/footer.jsp"%>
    </td>
  </tr>
  
  
</table>

</html:form>
</body>
</html:html>