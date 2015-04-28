<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page contentType="text/html; charset=gb2312" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>

<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>
自然人个税申报
</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/reader.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/Stack.js"></script>


</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
<%@ include file="./include/header.jsp"%>

<html:form action="/webapp/smsb/wjgrAction.do" method="POST">
<html:hidden property="actionType" value="Show" />

<html:hidden property="zydm" />
<html:hidden property="fdfs" />
<html:hidden property="yhdm" />
<html:hidden property="yhmc" />
<html:hidden property="sfczbs" />
<html:hidden property="gjdm" />
<html:hidden property="zjlxdm" />

<table align="center" cellspacing="0" class="table-99">
  <tr>
    <td class="1-td1">自然人个税申报</td>
  </tr>
  <tr>
     <td class="1-td2">
            <table cellspacing="0" class="table-99" width="100%">
              <tr class="2-td2-left">
              <td align="left" nowrap>
                      申报日期： <html:text property="sbrq" onchange="setSkssrq(this)" size="10" onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                </td>
                <td align="left" nowrap>
                      税款所属日期：<html:text property="skssksrq" onchange="isDate(this,false)" size="10" onkeydown="if(event.keyCode==13) event.keyCode=9;"/>&nbsp;到&nbsp;<html:text property="skssjsrq" onchange="isDate(this,false)" size="10" onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                </td>

                <td align="center" nowrap>
                    <div align="right">金额单位：人民币元</div>
                </td>
              </tr>

            </table>
         <br>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
<tr class="black9">
<td class="2-td2-t-left" nowrap><div align="left">&nbsp;&nbsp;国籍
                          </div>
</td>
<td class="2-td2-t-left" nowrap><div align="left">&nbsp;&nbsp;<!-- <ttsoft:select property="gjdm" codekey="GJDQ" onkeydown="if(event.keyCode==13) event.keyCode=9;" onchange="clearZjhm()" /> -->
<ttsoft:dmmc property="gjdm" codekey="GJDQ" />
                          </div>
</td>
<td class="2-td2-t-left" nowrap><div align="left">&nbsp;&nbsp;证件类型
                          </div>
</td>
<td class="2-td2-t-left" nowrap><div align="left">&nbsp;&nbsp;<!-- <ttsoft:select property="zjlxdm" codekey="ZJLX" onkeydown="if(event.keyCode==13) event.keyCode=9;" onchange="clearZjhm()" /> -->
<ttsoft:dmmc property="zjlxdm" codekey="ZJLX" />
                          </div>
</td>
<td class="2-td2-t-left" nowrap><div align="left">&nbsp;&nbsp;证件号码
                          </div>
</td>
<td class="2-td2-t-center" nowrap><div align="left"><html:text property="zjhm" maxlength="30" tabindex="-1"  styleClass="inputnoborder"    size="30" readonly="true" style="color:#3C5564"/>
                          </div>
</td>
</tr>
<tr class="black9">
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;银行名称
                          </div>
</td>
<td class="2-td2-left" nowrap ><div align="left" id="bankName">&nbsp;
                          </div>
</td>
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;帐号
                          </div>
</td>
<td class="2-td2-left" nowrap><div align="left">
<bean:define id="bankList" name="wjgrActionForm" property="bankList" />
                              <html:select property="zh" onchange="setBankName(this)"  onkeydown="if(event.keyCode==13) event.keyCode=9;">
                                 <html:options collection="bankList" property="zh" labelProperty="zh"/>
                              </html:select>
                          </div>
</td>
<td class="2-td2-left" nowrap><div align="left">&nbsp;计算机代码
                          </div>
</td>
<td class="2-td2-center" nowrap><div align="left"><html:text property="jsjdm"   size="10" onchange="checkZjhm(this)" onkeydown="jsjdmQuery()" maxlength="8"/>
                          </div>
</td>
</tr>
<tr class="black9">
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;纳税人姓名
                          </div>
</td>
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;<html:text property="nsrmc" tabindex="-1"  styleClass="inputnoborder"    size="30" readonly="true" style="color:#3C5564"/>
                          </div>
</td>
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;是否常驻
                          </div>
</td>
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;<ttsoft:dmmc property="sfczbs" codekey="SFCZBS" />
                          </div>
</td>
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;抵华日期
                          </div>
</td>
<td class="2-td2-center" nowrap><div align="left">&nbsp;&nbsp;<html:text property="dhrq" tabindex="-1"  styleClass="inputnoborder"    size="30" readonly="true" style="color:#3C5564"/>
                          </div>
</td>
</tr>
<tr class="black9">
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;租房费扣除额
                          </div>
</td>
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;<html:text property="zffkcs" tabindex="-1"  styleClass="inputnoborder"    size="30" readonly="true" style="color:#3C5564"/>
                          </div>
</td>
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;负担方式
                          </div>
</td>
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;
<ttsoft:dmmc property="fdfs" codekey="SKFDQK"/>
                          </div>
</td>
<td class="2-td2-left" nowrap><div align="left">&nbsp;&nbsp;单位负担比例(%)
                          </div>
</td>
<td class="2-td2-center" nowrap><div align="left">&nbsp;&nbsp;<html:text property="dwfdbl" tabindex="-1"  styleClass="inputnoborder"    size="30" readonly="true" style="color:#3C5564"/>
                          </div>
</td>
</tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
<tr class="black9">
<td class="2-td2-left"><div align="left">&nbsp;&nbsp;在中国境内住址<!-- <br>&nbsp;&nbsp;Address in China -->
                          </div>
</td>
<td class="2-td2-center"><div align="left">
                   <html:text property="jnzz" tabindex="-1"  styleClass="inputnoborder"    size="60" readonly="true" style="color:#3C5564"/>
                          </div>
</td>
</tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
<tr class="black9">
<td class="2-td2-left" nowrap>职业 <!-- Profession --> </TD><td class="2-td2-left" nowrap>
<ttsoft:dmmc property="zydm" codekey="ZYDM" />
</td>
<td class="2-td2-left" nowrap>服务单位 <!-- Employer --> </TD><td class="2-td2-left" nowrap><div align="left"> <html:text property="fwdw"   size="20" onkeydown="if(event.keyCode==13) event.keyCode=9;" maxlength="50"/></div>
</td>
<td class="2-td2-left" nowrap>服务地点<!--  Working locationg --></TD><td class="2-td2-center" nowrap><div align="left"> <html:text property="fwdd"   size="20" onkeydown="if(event.keyCode==13) event.keyCode=9;" maxlength="50"/></div>
</td>
</tr>
</table>
<br>
<table width="100%" border="0" cellpadding="0" cellspacing="0"  id="ZRRGRSDSMX" onkeydown=dokeydown(this.id,'szsmdm')  onmouseup='return dokeyUp(this.id)' class="table-99">
  <tr>
    <td rowspan="2" class="2-td1-left">所得项目<!-- <br>Categaries of in come --></td>
    <td rowspan="2" class="2-td1-left">所得开始日期<!-- <br>Income period --></td>
    <td rowspan="2" class="2-td1-left">所得结束日期<!-- <br>Income period --></td>
    <td rowspan="2" class="2-td1-left">原居住国税款<!-- <br>Income period --></td>
    <td rowspan="2" class="2-td1-left">法定扣除额<!-- <br>Income period --></td>
    <td colspan="3" class="2-td1-left">收入额 <!-- Receipts --></td>
    <td rowspan="2" class="2-td1-left">减费用额 <!-- <br> Deductions --></td>
    <td rowspan="2" class="2-td1-left">应纳税所得额 <!-- <br> Taxable income --></td>
    <td rowspan="2" class="2-td1-left">税率 <!-- <br> Tax rate --></td>
    <td rowspan="2" class="2-td1-left">速算扣除数 <!-- <br> Quick calculation deduction --></td>
    <td rowspan="2" class="2-td1-left">应纳税额 <!-- <br> Income tax --></td>
    <td rowspan="2" class="2-td1-left">已扣缴税款 <!-- <br> Tax withheld --></td>
    <td rowspan="2" class="2-td1-center">应补（退）税款<!--  <br> Amount of income tax due or over paid --></td>
  </tr>
  <tr>
    <td class="2-td1-left">人民币<!-- <br> RMB --></td>
    <td class="2-td1-left">折合人民币 <!-- Foreign currency --></td>
    <td class="2-td1-left">人民币合计<!-- <br> Total --></td>
  </tr>
  <!-- <tr>
    <td class="2-td1-left">货币名称</td>
    <td class="2-td1-left">金额</td>
    <td class="2-td1-left">外汇牌价</td>
    <td class="2-td1-left">折合人民币</td>
  </tr> -->
  <ttsoft:smsbtable form="wjgrActionForm" property="dataList"  tableId="ZRRGRSDSMX" hasTitle="false"/>
  <DIV id=divSfTemp></DIV>

</table>

   </td>
   </tr>
</table>
<!--页面按钮操作区-->
<br>
<table class="table-99">
<tr>
    <td align="center">

        <input type="image" accesskey="w" tabIndex="-1" onclick="javascript:doJkswh(); return false;"  onMouseOver="MM_swapImage('whjks1','','<%=static_contextpath%>images/whjks-w2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="维护缴款书" id="whjks1" src="<%=static_contextpath%>images/whjks-w1.jpg" width="110" height="22">
        &nbsp;&nbsp;
         <input type="image" accesskey="a" tabIndex="-1" onclick="javascript:add4Row('ZRRGRSDSMX');return false;"  onMouseOver="MM_swapImage('zj1','','<%=static_contextpath%>images/zj-a2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="增加" id="zj1" src="<%=static_contextpath%>images/zj-a1.jpg" width="79" height="22">
        &nbsp;&nbsp;
        <input type="image" accesskey="d" tabIndex="-1" onClick="javascript:deleteRow('ZRRGRSDSMX',null,'szsmdm'); return false;"  onMouseOver="MM_swapImage('sc2','','<%=static_contextpath%>images/sc-d2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="删除" id="sc2" src="<%=static_contextpath%>images/sc-d1.jpg" width="79" height="22">
        &nbsp;&nbsp;
        <input type="image" accesskey="s" tabIndex="-1" onClick="doSubmitCheck('Save'); return false;"  onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="保存" id="bc1" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" height="22">
        &nbsp;&nbsp;
        <input type="image" accesskey="c" tabIndex="-1"   onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22">
    </td>
</tr>
</table>
<div id=szsmdm_epodDateLayer style="position: absolute; width: 142; height: 166; z-index: 9998; display: none" ><select id=sel onclick=selectClick("szsmdm","ZRRGRSDSMX") onkeyup=selectMove("szsmdm","ZRRGRSDSMX")   onkeydown="if(window.event.keyCode==13) selectClick('szsmdm','ZRRGRSDSMX')"></select></div>
<INPUT TYPE="hidden" NAME="szsmdm_focus">


</td>
</tr>
</table>
</html:form>
<%@ include file="./include/footer.jsp"%>
<!--js脚本区-->
<script language="javascript" type="text/JavaScript">
//进入页面时将焦点设为计算机代码录入
// 页面进入焦点确定
function fnOnLoad()
{
    document.forms[0].jsjdm.focus();
}

var ZRRGRSDSMX_list=new DTable(ZRRGRSDSMX,jsArr_ZRRGRSDSMX);
ZRRGRSDSMX_list.tableHead=2;
//明细数据不可以超过4条
function add4Row(obj){
    if(ZRRGRSDSMX.rows.length>5){
        //已经有4条申报数据
        return false;
    }
    if(document.forms[0].zjhm.value=='' || document.forms[0].nsrmc.value==''){
        //没有录入基本信息
        return false;
    }
    //少于4条
    addRow('ZRRGRSDSMX');
    return false;
}

//明细列表使用的js数组
<ttsoft:write name="wjgrActionForm" property="scriptStr" filter="false"/>
//设置银行名称
<ttsoft:write name="wjgrActionForm" property="bankJsArray" filter="false"/>
function setBankName(obj){
    for (var i=0; i<bankInfo.length; i++){
        if (bankInfo[i][0] == obj.value){
            document.all.yhmc.value = bankInfo[i][1];
            document.all.yhdm.value = bankInfo[i][2];
            bankName.innerText = " " + bankInfo[i][1];
            break;
        }
    }
    if(obj.value==''){

        document.all.yhmc.value = '';
        document.all.yhdm.value = '';
        bankName.innerText = " ";
    }
}
setBankName(document.all.zh);
//计算税款所属日期
function setSkssrq(obj){
    //检查录入值的正确性
    if(isDate(obj,false)){
        //得到月报型的税款所属日期
        getStartEndDate(obj,document.forms[0].skssksrq,document.forms[0].skssjsrq,2);
    }
}
//检查税款所属日期的正确性
function checkSkssrq(obj){
    if(isDate(obj,false)){
        if(parseFloat(document.forms[0].skssksrq.value)>parseFloat(document.forms[0].skssjsrq.value)){

        }
    }
}
//当国籍代码证件类型变更时，清空证件号码
function clearZjhm(){
    document.forms[0].zjhm.value="";
}
//检查输入的证件号码
function checkZjhm(obj){
    //检查身份证号码
    //if(document.forms[0].zjlxdm.value=='02'&&!checkIdentityCard(obj.value,null)) {
    //	obj.value='';
    //	obj.focus();
    //	return false;
    //}
    if(obj.value!='') doSubmitForm('Query');
}
//跳转到缴款书维护界面
function doJkswh(){
    //必须录入计算机代码
    if(document.forms[0].zjhm.value=='' || document.forms[0].nsrmc.value=='')
      return false;
    document.all.actionType.value='Jkswh';
    document.forms[0].submit();
}
//响应计算机代码的回车查询
function jsjdmQuery(){
    if(event.keyCode==13) event.keyCode = 9;
}
//响应计算机代码的回车查询
function enter2Tab(){
    if(event.keyCode==13) event.keyCode = 9;
}
//检查录入项的合法性，然后提交
function doSubmitCheck(ope){
    //检查税款所属日期
    if(parseFloat(document.forms[0].skssksrq.value)>parseFloat(document.forms[0].skssjsrq.value)){
        alert("税款所属开始日期不能大于税款所属结束日期");
        document.forms[0].skssksrq.select();
        return false;
    }
    if(ZRRGRSDSMX.rows.length<3 || document.forms[0].zjhm.value==''){
        //无明细数据
        return false;
    }
    //检查所有明细数据的合法性
    for(var i=0;i<ZRRGRSDSMX.rows.length;i++){
        if(ZRRGRSDSMX.rows[i].all("szsmdm")){
            //税种税目代码不可以为空
            if(ZRRGRSDSMX.rows[i].all("szsmdm").value==''){
                alert("税种税目代码不可为空");
                ZRRGRSDSMX.rows[i].all("szsmdm").focus();
                return false;
            }
            //检查税款所属日期
            if(parseFloat(ZRRGRSDSMX.rows[i].all("sdksrq").value)>parseFloat(ZRRGRSDSMX.rows[i].all("sdjsrq").value)){
                alert("所得开始日期不能大于所得结束日期");
                ZRRGRSDSMX.rows[i].all("sdksrq").focus();
                return false;
            }
            //检查零申报
            if(ZRRGRSDSMX.rows[i].all("ybtsk").value=='' || ZRRGRSDSMX.rows[i].all("ybtsk").value=='0'){
                alert("已经取消０申报，本条数据不保存，请删除或修改");
                ZRRGRSDSMX.rows[i].all("ybtsk").focus();
                return false;
            }

//start add by qianchao 2005.11.1

            if(ZRRGRSDSMX.rows[i].all("ynse").value=='' || ZRRGRSDSMX.rows[i].all("ynse").value=='0'){
                alert("应纳税额不得为空");
                ZRRGRSDSMX.rows[i].all("ynse").focus();
                return false;
            }

//end add by qianchao 2005.11.1
        }
    }
    doSubmitForm(ope);
}

//计算人民币合计，合计人民币＝人民币＋折合人民币
function hjRMB(obj){
    //得到当前行
    var oRow = getObjRow(obj);
    if(isNum(oRow.all("srermb"),0,null,null,null,null) && isNum(oRow.all("zhrmb"),0,null,null,null,null)){
        //得到人民币
        var rmb = checkEmpty(oRow.all("srermb"));
        //得到折合人民币
        var zhrmb = checkEmpty(oRow.all("zhrmb"));
        var rmbhj = parseFloat(rmb)+parseFloat(zhrmb);
        oRow.all("rmbhj").value=rmbhj;
        //计算应纳税所得额
        comYnssde(obj);
    }
}
//检查录入知是否为'',如果为空则将录入值设为0
function checkEmpty(obj){
    var value = obj.value;
    if(value==''){
        value=0;
        obj.value=0;
    }
    return value;

}
//当税目为05011％的税目的应纳税额的计算，现在是按一个月计算的，如果所得期间计算出来超过一个月的，则应纳税额还要乘以计算出来的月得出实际应纳税额，计算后，应纳税额还要可修改，防止我们计算有误的时候可手工修改以保证数据保存到数据库中；
function comYnseByMonths(obj){
    //是否常驻标示
    var sfczbs = document.forms[0].sfczbs.value;
    if(sfczbs=='0'){
        //常驻的按跨月计算,非常驻的保留现有的计算方法;
        return false;
    }
    //得到当前行
    var oRow = getObjRow(obj);
    //调整的月份数
    var months=getBMonths(oRow.all("sdksrq").value,oRow.all("sdjsrq").value)+1;
    oRow.all("ynse").value = oRow.all("ynse").value*months;
}

//计算应纳税所得额
function comYnssde(obj){
    //得到当前行
    var oRow = getObjRow(obj);
    if(isNum(oRow.all("rmbhj"),0,null,null,null,null)){
    //得到税种税目代码
    var szsmdm = oRow.all("szsmdm").value;
    var szsmSub = szsmdm.substr(0,5);
    var szsmSub4 = szsmdm.substr(0,4);
    //得到负担方式
    var fdfs = document.forms[0].fdfs.value;
    if(szsmSub=='05011'){
        //if(szsmdm!='050110'){
            //非050110月工资
            if(fdfs=='3'){
                //个人负担			/*应纳税所得额=收入额（输入项，注：月收入）-法定扣除项目金额（注：可税前扣除的四项基金等项目）-标准扣除费用（中国人1000（北京），外籍人、港澳台4000，按登记表中的国籍项）；*/
                //收入额
                var zhrmb =  checkEmpty(oRow.all("rmbhj"));
                //减费用额
                var jfye = checkEmpty(oRow.all("jfye"));
                //法定扣除项目金额
                var fdkce = checkEmpty(oRow.all("fdkce"));
                //计算应纳税所得额
                oRow.all("ynssde").value = roundV(parseFloat(zhrmb)-parseFloat(fdkce)-parseFloat(jfye));
                var index = fixBhs(obj);
                if(index!='-1'){
                    //税率
                    var sl = szsmlist[index][12];
                    if(sl == '') sl=0;
                    //速算扣除数
                    var sskcs = szsmlist[index][13];
                    if(sskcs == '') sskcs=0;
                    //比较原居住国应缴纳税款
                    compareYnssde(obj,sl,sskcs);
                }
                //得到适用税率和速算扣除数
                fixSysl(obj);
                //计算应纳税额
                /*应纳税额=应纳税所得额* 适用税率（见税率表--工资薪金适用）-速算扣除数（见税率表--工资薪金适用）*/
                var ynssde = oRow.all("ynssde").value;
                //适用税率
                var sl = checkEmpty(oRow.all("sl"));
                //速算扣除数
                var sskcs = checkEmpty(oRow.all("sskcs"));
                oRow.all("ynse").value = roundV(parseFloat(ynssde)*parseFloat(sl)-parseFloat(sskcs));
                //当纳税人是否常驻标识为否的时候：税目05011％下的税目的计算的应纳税额＝原来公式计算的应纳税额/（所得期间结束日期－所得期间开始日期）的物理天数，应补还是应纳税额＝已扣；
                comYnse05011(obj);

                //按照所得期间计算应纳税额

                comYnseByMonths(obj);
                //计算应补税额
                /*应补税额=应纳税额-已扣或已缴税额（注：指本期已扣缴或已缴纳税额）*/
                //应纳税额
                var ynse = checkEmpty(oRow.all("ynse"));
                //已扣或已缴税额
                var ykjse = checkEmpty(oRow.all("ykjse"));
                oRow.all("ybtsk").value = parseFloat(ynse)-parseFloat(ykjse);


            }else if(fdfs=='1'){
                //公司全额负担				/*应纳税所得额=（不含税收入额-法定扣除项目金额-费用扣除标准-速算扣除数）÷（1-税率）①中的税率是指不含税所得按不含税级距对应的税率*/
                //得到不含税级距的税种税目数组index
                var index = fixBhs(obj);
                //alert(index)
                if(index!='-1'){
                    //得到收入额
                    var rmbhj =  checkEmpty(oRow.all("rmbhj"));
                    //减费用额
                    var jfye = checkEmpty(oRow.all("jfye"));
                    //法定扣除项目金额
                    var fdkce = checkEmpty(oRow.all("fdkce"));
                    //税率
                    var sl = szsmlist[index][12];
                    if(sl == '') sl=0;
                    //速算扣除数
                    var sskcs = szsmlist[index][13];
                    if(sskcs == '') sskcs=0;
                    //alert(sl+" : "+sskcs)
                    //计算应纳税所得额
                    oRow.all("ynssde").value = roundV((parseFloat(rmbhj)-parseFloat(fdkce)-parseFloat(jfye)-parseFloat(sskcs))/(1-parseFloat(sl)));
                    //比较原居住国应缴纳税款
                    compareYnssde(obj,sl,sskcs);
                    //计算应纳税额
                    //得到适用税率和速算扣除数
                    fixSysl(obj);
                    /*应纳税额=应纳税所得额*税率-速算扣除数*/
                    oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*parseFloat(oRow.all("sl").value)-parseFloat(oRow.all("sskcs").value));
                    //当纳税人是否常驻标识为否的时候：税目05011％下的税目的计算的应纳税额＝原来公式计算的应纳税额/（所得期间结束日期－所得期间开始日期）的物理天数，应补还是应纳税额＝已扣；
                    comYnse05011(obj);
                    //按照所得期间计算应纳税额

                    comYnseByMonths(obj);
                    //应纳税额
                    var ynse = checkEmpty(oRow.all("ynse"));

                    //已扣或已缴税额
                    var ykjse = checkEmpty(oRow.all("ykjse"));
                    oRow.all("ybtsk").value = parseFloat(ynse)-parseFloat(ykjse);

                }
            }else if(fdfs=='2'){
                //单位负担部分
                /*应纳税所得额=（未含雇主负担的税款收入额-费用扣除标准-速算扣除数*负担比例）÷（1-税率*负担比例）*/
                //得到不含税级距的税种税目数组index
                var index = fixBhs(obj);
                if(index!='-1'){
                    //负担比率
                    var fdbl = document.forms[0].dwfdbl.value;
                    //得到收入额
                    var rmbhj =  checkEmpty(oRow.all("rmbhj"));
                    //减费用额
                    var jfye = checkEmpty(oRow.all("jfye"));
                    //税率
                    var sl = szsmlist[index][12];
                    if(sl == '') sl=0;
                    //速算扣除数
                    var sskcs = szsmlist[index][13];
                    if(sskcs == '') sskcs=0;

                    //计算应纳税所得额

                    oRow.all("ynssde").value = roundV((parseFloat(rmbhj)-parseFloat(jfye)-parseFloat(sskcs)*(parseFloat(fdbl)/100))/(1-parseFloat(sl)*(parseFloat(fdbl)/100)));


                    //比较原居住国应缴纳税款
                    compareYnssde(obj,sl,sskcs);
                    //计算应纳税额
                    /*应纳税额=应纳税所得额*适用税率-速算扣除数*/
                    //得到适用税率和速算扣除数
                    fixSysl(obj);
                    //适用税率
                    var sysl = checkEmpty(oRow.all("sl"));
                    //使用速算扣除数
                    var sysscks = checkEmpty(oRow.all("sskcs"));
                    //应纳税所得额
                    var ynssde2 = checkEmpty(oRow.all("ynssde"));
                    //计算应纳税额
                    oRow.all("ynse").value =roundV(parseFloat(ynssde2)*parseFloat(sysl)-parseFloat(sysscks));

                    //当纳税人是否常驻标识为否的时候：税目05011％下的税目的计算的应纳税额＝原来公式计算的应纳税额/（所得期间结束日期－所得期间开始日期）的物理天数，应补还是应纳税额＝已扣；
                    comYnse05011(obj);

                    //按照所得期间计算应纳税额

                    comYnseByMonths(obj);
                    //应纳税额
                    var ynse = checkEmpty(oRow.all("ynse"));
                    //已扣或已缴税额
                    var ykjse = checkEmpty(oRow.all("ykjse"));
                    oRow.all("ybtsk").value = parseFloat(ynse)-parseFloat(ykjse);

                }

            }
        //}
    }
    //050150数月奖金、年终分红、年终奖
    com050150(obj);
    //0503劳务报酬
    com0503(obj);
    //0504稿酬所得
    com0504(obj);
    //0505特许权使用费所得
    com0505(obj);
    //0506股息、利息、红利所得
    com0506(obj);
    //050702一般财产租赁
    com050702(obj);
    //050701个人私房出租
    com050701(obj);
    //0508财产转让所得
    com0508(obj);
    //0509偶然所得
    com0509(obj);
    //0510经国务院财政部门确定征税的其他所得
    com0510(obj)
    //050151　调资
    com050151(obj)
    //050152　退职
    com050152(obj)
    }
}
//检查数值型合法性
function checkNum1(obj){
    isNum(obj,0,null,null,null,null);
}
//比较原居住国应缴纳税款
function compareYnssde(obj,sl,sskcs){
    //得到当前行
    var oRow = getObjRow(obj);
    //得到负担方式
    var fdfs = document.forms[0].fdfs.value;
    //收入额
    var zhrmb =  checkEmpty(oRow.all("rmbhj"));
    //减费用额
    var jfye = checkEmpty(oRow.all("jfye"));
    //法定扣除项目金额
    var fdkce = checkEmpty(oRow.all("fdkce"));
    //原居住国税款
    var yjzgsk = checkEmpty(oRow.all("yjzgsk"));
    //标准应纳税所得额
    var ynssde1 = checkEmpty(oRow.all("ynssde"));
    if(parseFloat(yjzgsk)>0){
        //原居住国税款大于0
        var sre = parseFloat(zhrmb)-parseFloat(yjzgsk);
        //按原居住国税款计算的应纳税所得额
        var ynssde = roundV((parseFloat(sre)-parseFloat(jfye)-parseFloat(sskcs))/(1-parseFloat(sl)));
        //alert(ynssde+" : "+ynssde1+" : "+sl+" : "+sskcs);
        if(parseFloat(ynssde)>parseFloat(ynssde1)){
            //按原居住国税款计算的应纳税所得额大于标准应纳税所得额则是用按原居住国税款计算的应纳税所得额
            oRow.all("ynssde").value =ynssde;
        }
    }

}
//当纳税人是否常驻标识为否的时候：税目05011％下的税目的计算的应纳税额＝原来公式计算的应纳税额/所得期间所在的月份的物理天数*（所得期间结束日期－所得期间开始日期）的物理天数，应补还是应纳税额＝已扣；

function comYnse05011(obj){
    //是否常驻标示
    var sfczbs = document.forms[0].sfczbs.value;
    if(sfczbs=='0'){
        //得到当前行
        var oRow = getObjRow(obj);
        //当月天数
        var days = getDaysOfMonth(oRow.all("sdksrq").value);
        //天数
        var wlts = getBDays(oRow.all("sdksrq").value,oRow.all("sdjsrq").value)+1;
        //当前应纳税额
        //alert(oRow.all("ynse").value+" : "+days+" : "+wlts)
        var ynse = roundV(oRow.all("ynse").value/days*wlts);
        oRow.all("ynse").value = ynse;
        //alert(days+" : "+ wlts+" : "+ynse)
    }
}

//计算应补税额
function comYbtsk(obj){
    //得到当前行
    var oRow = getObjRow(obj);
    if(isNum(oRow.all("ynse"),0,null,null,null,null) && isNum(oRow.all("ykjse"),0,null,null,null,null)){
        /*应补税额=应纳税额-已扣或已缴税额（注：指本期已扣缴或已缴纳税额）*/
        //应纳税额
        var ynse = checkEmpty(oRow.all("ynse"));
        //已扣或已缴税额
        var ykjse = checkEmpty(oRow.all("ykjse"));
        oRow.all("ybtsk").value = parseFloat(ynse)-parseFloat(ykjse);
    }
}
//保留小数点后两位
function roundV(value){
    return Math.round(value*100)/100;
}
//得到不含税级距的税种税目数组index
function fixBhs(obj){
    //得到当前行
    var oRow = getObjRow(obj);
    //得到收入额
    var rmbhj =  checkEmpty(oRow.all("rmbhj"));
    //减费用额
    var jfye = checkEmpty(oRow.all("jfye"));
    //法定扣除项目金额
    var fdkce = checkEmpty(oRow.all("fdkce"));
    //计算不含税参数
    var bhs = roundV(parseFloat(rmbhj)-parseFloat(jfye)-parseFloat(fdkce));
    for(i=0;i<szsmlist.length;i++){
        var szsmSub = szsmlist[i][0].substr(0,5);
    //找到大于不含税所得起始起始数小于不含税所得起始终止数的税种税目

        if(szsmSub=="05011" && parseFloat(szsmlist[i][10])<parseFloat(bhs) && parseFloat(szsmlist[i][11])>=parseFloat(bhs)){
            return i;
        }
    }
    //未查询到相应的不含税级距的税种税目
    return -1;
}
//得到不含税级距的税种税目数组index
function fixBhs(obj){
    //得到当前行
    var oRow = getObjRow(obj);
    //得到收入额
    var rmbhj =  checkEmpty(oRow.all("rmbhj"));
    //减费用额
    var jfye = checkEmpty(oRow.all("jfye"));
    //法定扣除项目金额
    var fdkce = checkEmpty(oRow.all("fdkce"));
    //计算不含税参数
    var bhs = roundV(parseFloat(rmbhj)-parseFloat(jfye)-parseFloat(fdkce));
    for(i=0;i<szsmlist.length;i++){
        var szsmSub = szsmlist[i][0].substr(0,5);
    //找到大于不含税所得起始起始数小于不含税所得起始终止数的税种税目

        if(szsmSub=="05011" && parseFloat(szsmlist[i][10])<parseFloat(bhs) && parseFloat(szsmlist[i][11])>=parseFloat(bhs)){
            return i;
        }
    }
    //未查询到相应的不含税级距的税种税目
    return -1;
}
//得到不含税级距的税种税目数组index
function fixBhs050150(obj){
    //得到当前行
    var oRow = getObjRow(obj);
    //得到收入额
    var rmbhj =  checkEmpty(oRow.all("rmbhj"));
    //减费用额
    var jfye = checkEmpty(oRow.all("jfye"));
    //法定扣除项目金额
    var fdkce = checkEmpty(oRow.all("fdkce"));
    //计算不含税参数
    //var bhs = roundV(parseFloat(rmbhj)-parseFloat(jfye)-parseFloat(fdkce));
    var bhs = roundV(parseFloat(rmbhj));
    for(i=0;i<szsmlist.length;i++){
        var szsmSub = szsmlist[i][0].substr(0,5);
    //找到大于不含税所得起始起始数小于不含税所得起始终止数的税种税目

        if(szsmSub=="05011" && parseFloat(szsmlist[i][10])<parseFloat(bhs) && parseFloat(szsmlist[i][11])>=parseFloat(bhs)){
            return i;
        }
    }
    //未查询到相应的不含税级距的税种税目
    return -1;
}
//得到不含税级距的税种税目数组index
function fixBhs050151(obj,sre){

    for(i=0;i<szsmlist.length;i++){
        var szsmSub = szsmlist[i][0].substr(0,5);
    //找到大于不含税所得起始起始数小于不含税所得起始终止数的税种税目

        if(szsmSub=="05011" && parseFloat(szsmlist[i][10])<parseFloat(sre) && parseFloat(szsmlist[i][11])>=parseFloat(sre)){
            return i;
        }
    }
    //未查询到相应的不含税级距的税种税目
    return -1;
}
//得到适用税率和速算扣除数
function fixSysl(obj){
    //得到当前行
    var oRow = getObjRow(obj);
    var ynssde = oRow.all("ynssde").value;
    for(i=0;i<szsmlist.length;i++){
        var szsmSub = szsmlist[i][0].substr(0,5);
    //找到大于应纳税起始数小于应纳税终止数的税种税目
    //if(szsmSub=="05011") alert(szsmSub+" : "+ynssde+" : "+szsmlist[i][4]+" : "+szsmlist[i][5])
        if(szsmSub=="05011" && parseFloat(szsmlist[i][4])<parseFloat(ynssde) && parseFloat(szsmlist[i][5])>=parseFloat(ynssde)){
            //设置税种税目代码
            oRow.all("szsmdm").value = szsmlist[i][0];
            //保留当前税款所属日期
            var oks = oRow.all("sdksrq").value;
            var ojs = oRow.all("sdjsrq").value;
            //设置税款所属日期
            //oRow.all("sdksrq").value = szsmlist[i][7];
            //oRow.all("sdjsrq").value = szsmlist[i][8];
            //设置税率
            //oRow.all("sl").value = szsmlist[i][3];
            //设置速算扣除数
            //oRow.all("sskcs").value = szsmlist[i][6];
            resetRow("szsmdm","ZRRGRSDSMX",oRow.all("szsmdm"))
            //oRow.all("szsmdm").select();
            //重设税款所属日期
            oRow.all("sdksrq").value = oks;
            oRow.all("sdjsrq").value = ojs;
            return i;
        }
    }
}

//得到适用税率和速算扣除数的列表index
function fixSysli(obj){
    //得到当前行
    var oRow = getObjRow(obj);
    var ynssde = oRow.all("ynssde").value;
    for(i=0;i<szsmlist.length;i++){
        var szsmSub = szsmlist[i][0].substr(0,5);
    //找到大于应纳税起始数小于应纳税终止数的税种税目
    //if(szsmSub=="05011") alert(szsmSub+" : "+ynssde+" : "+szsmlist[i][4]+" : "+szsmlist[i][5])
        if(szsmSub=="05011" && parseFloat(szsmlist[i][4])<parseFloat(ynssde) && parseFloat(szsmlist[i][5])>=parseFloat(ynssde)){
            return i;
        }
    }
    //未找到适合税率
    return -1;
}
//050150数月奖金、年终分红、年终奖
function com050150(obj){
    //得到当前行
    var oRow = getObjRow(obj);
    //得到税种税目代码
    var szsmdm = oRow.all("szsmdm").value;
    //得到负担方式
    var fdfs = document.forms[0].fdfs.value;
    if(szsmdm=='050150'){
        if(fdfs=='3'){
            //1、个人负担：
            //应纳税所得额=收入额
            //得到收入额
            var rmbhj =  checkEmpty(oRow.all("rmbhj"));
            oRow.all("ynssde").value = rmbhj;
            /*应纳税额=应纳税所得额* 税率（见税率表--工资薪金适用）-速算扣除数（见税率表--工资薪金适用）*/
            var hi = fixSysli(obj);
            if(hi!='-1'){
                //适用税率
                var sysl = szsmlist[hi][3];
                //使用速算扣除数
                var sysscks = szsmlist[hi][6];
                //应纳税所得额
                var ynssde2 = checkEmpty(oRow.all("ynssde"));
                //计算应纳税额
                oRow.all("ynse").value =roundV(parseFloat(ynssde2)*parseFloat(sysl)-parseFloat(sysscks));
            }
            /*应补税额=应纳税额-已扣或已缴税额（注：指本期已扣缴或已缴纳税额）*/
            var ynse3=checkEmpty(oRow.all("ynse"));
            var ykjse3 =checkEmpty(oRow.all("ykjse"));
            oRow.all("ybtsk").value=roundV(parseFloat(ynse3)-parseFloat(ykjse3));
        }else if(fdfs=='1'){
            //公司全额负担
            /*应纳税所得额=（收入额-速算扣除数）÷（1-税率）*/
            var bi1 =fixBhs050150(obj);
            if(bi1!='-1'){
                //得到收入额
                var rmbhj =  checkEmpty(oRow.all("rmbhj"));
                //税率
                var sl = szsmlist[bi1][12];
                if(sl == '') sl=0;
                //速算扣除数
                var sskcs = szsmlist[bi1][13];
                if(sskcs == '') sskcs=0;
                //计算应纳税所得额
                oRow.all("ynssde").value=roundV((parseFloat(rmbhj)-parseFloat(sskcs))/(1-parseFloat(sl)));
            }
            /*应纳税额=应纳税所得额*税率-速算扣除数*/
            var hi = fixSysli(obj);
            if(hi!='-1'){
                //适用税率
                var sysl = szsmlist[hi][3];
                //使用速算扣除数
                var sysscks = szsmlist[hi][6];
                //应纳税所得额
                var ynssde = checkEmpty(oRow.all("ynssde"));
                //计算应纳税额
                oRow.all("ynse").value =roundV(parseFloat(ynssde)*parseFloat(sysl)-parseFloat(sysscks));
            }
        }
    }
}
//050151　调资
function com050151(obj){
    //得到当前行
    var oRow = getObjRow(obj);
    //得到税种税目代码
    var szsmdm = oRow.all("szsmdm").value;
    //得到负担方式
    var fdfs = document.forms[0].fdfs.value;
    if(szsmdm=='050151'){

        //得到收入额
        var rmbhj =  checkEmpty(oRow.all("rmbhj"));
        //减费用额
        var jfye = checkEmpty(oRow.all("jfye"));
        //已扣或已缴税额
        var ykjse = checkEmpty(oRow.all("ykjse"));
        //法定扣除项目金额
        var fdkce = checkEmpty(oRow.all("fdkce"));
        //当月应纳税所得额＝当前的人民币合计/调整的月份数＋调整前的应纳税所得额
        //oRow.all("ynssde").value=roundV(parseFloat(rmbhj)-parseFloat(jfye)-parseFloat(fdkce));
        var iYq = getYqybjseI();
        //alert(iYq)
        if(iYq==-1) return;
        //调整前应纳税所得额
        var tzqynssde = ZRRGRSDSMX.rows[iYq].all("ynssde").value;
        if(tzqynssde=='') tzqynssde=0;
        //调整前应补
        var tzqybtsk = ZRRGRSDSMX.rows[iYq].all("ybtsk").value;
        if(tzqybtsk=='') tzqybtsk=0;
        //调资前人民币合计
        var tzqrmbhj = ZRRGRSDSMX.rows[iYq].all("rmbhj").value;
        if(tzqrmbhj=='') tzqrmbhj=0;
        //调资前应纳税额
        var tzqynse = ZRRGRSDSMX.rows[iYq].all("ynse").value;
        if(tzqynse=='') tzqynse=0;
        //调整的月份数
        var months=getBMonths(oRow.all("sdksrq").value,oRow.all("sdjsrq").value)+1;
        if(parseFloat(months)<0) months=1;
        if(fdfs=='3'){
            //2004034 Shi Yanfeng
            //一、调资:(个人负担)
            //1.调资后对应的应纳税所得额=(原工资+调资额/调资涉及的月份)-费用扣除额
            //2.调资后对应的应纳税额=调资后对应的应纳税所得额*适用税率-速算扣除数
            //3.应补税额=(调资后对应的应纳税额-原工资对应的应纳税额)*调资涉及的月份
            //2004034 Shi Yanfeng
            //个人负担
            //调资的收入额＝（上一行的人民币合计－调资税目的人民币合计/调整的月份）　－　法定　－　减费用额
            var sre = roundV((parseFloat(tzqrmbhj)+parseFloat(rmbhj/months))-fdkce-jfye);

            //调资税目的应纳税所得额＝调资的收入额
            oRow.all("ynssde").value=sre;
            //用调资税目的应纳税所得额找含税级距的速算扣除数和税率
            var index = fixSysli(obj);
            if(index!='-1'){
                //适用税率
                var sl = szsmlist[index][3];
                oRow.all("sl").value =sl;
                //使用速算扣除数
                var sscks = szsmlist[index][6];
                oRow.all("sskcs").value =sscks;
                //暂时的应纳税额＝应纳税所得额×税率－速算扣除数；
                var tempYnse = roundV(oRow.all("ynssde").value*sl - sscks);
                //调资的应纳税额＝（上一行的应纳税额－暂时的应纳税额）×调整月份；
                //oRow.all("ynse").value = roundV((tzqynse - tempYnse)*months);
                oRow.all("ynse").value = tempYnse;
                //当月应补＝（应纳税额－调整前应补）×调整的月份数
                //oRow.all("ybtsk").value = roundV(oRow.all("ynse").value-tzqybtsk);
                oRow.all("ybtsk").value = roundV((oRow.all("ynse").value-tzqynse)*months);
            }
        }else if(fdfs=='1'){
            //2004034 Shi Yanfeng
            //二、调资:(全部公司负担)
            //1.调资后对应的应纳税所得额=[(原工资+调资额/调资涉及的月份)-费用扣除额-速算扣除数]/1-税率
            //2.调资后对应的应纳税额=调资后对应的应纳税所得额*适用税率-速算扣除数
            //3.应补税额=(调资后对应的应纳税额-原工资对应的应纳税额)*调资涉及的月份
            //2004034 Shi Yanfeng
            //单位负担
            //调资的收入额＝（上一行的人民币合计－调资税目的人民币合计/调整的月份）　－　法定　－　减费用额
            var sre = roundV((parseFloat(tzqrmbhj)+parseFloat(rmbhj/months))-fdkce-jfye);
            //根据调资的收入额查找不含税级距的税率和速算扣除数
            var bhsi = fixBhs050151(obj,sre);
            //alert(tzqrmbhj+" : "+sre)
            if(bhsi!=-1){
                //税率
                var sl = szsmlist[bhsi][12];
                if(sl == '') sl=0;
                //速算扣除数
                var sskcs = szsmlist[bhsi][13];
                if(sskcs == '') sskcs=0;
                //alert(sl+" : "+sskcs)
                //调资税目的应纳税所得额＝（调资的收入额－速算扣除数）/(1－税率)
                oRow.all("ynssde").value=roundV((sre-sskcs)/(1-sl));
                //用调资税目的应纳税所得额找含税级距的速算扣除数和税率
                var index = fixSysli(obj);

                if(index!='-1'){
                    //适用税率
                    var sysl = szsmlist[index][3];
                    oRow.all("sl").value =sysl;
                    //使用速算扣除数
                    var sysskcs = szsmlist[index][6];
                    oRow.all("sskcs").value =sysskcs;
                    //暂时的应纳税额＝应纳税所得额×税率－速算扣除数；
                    var tempYnse = roundV(oRow.all("ynssde").value*sysl - sysskcs);
                    //调资的应纳税额＝（上一行的应纳税额－暂时的应纳税额）×调整月份；
                    //oRow.all("ynse").value = roundV((tzqynse - tempYnse)*months);
                    oRow.all("ynse").value = tempYnse;
                    //当月应补＝（应纳税额－调整前应补）×调整的月份数
                    //oRow.all("ybtsk").value = roundV(oRow.all("ynse").value-tzqybtsk);
                    //oRow.all("ybtsk").value = roundV(oRow.all("ynse").value-ykjse);
                    oRow.all("ybtsk").value = roundV((oRow.all("ynse").value-tzqynse)*months);
                }
            }
        }else if(fdfs=='2'){
            //单位负担部分
            ////2004034 Shi Yanfeng
            //调资:(公司负担为20%)
            //1.调资后对应的应纳税所得额=[(原工资+调资额/调资涉及的月份)-费用扣除额-速算扣除数*负担比例]/(1-税率*负担比例)
            //2.调资后对应的应纳税额=调资后对应的应纳税所得额*适用税率-速算扣除数
            //3.应补税额=(调资后对应的应纳税额-原工资对应的应纳税额)*调资涉及的月份
            //2004034 Shi Yanfeng

            //调资的收入额＝（上一行的人民币合计－调资税目的人民币合计/调整的月份）　－　法定　－　减费用额
            var sre = roundV((parseFloat(tzqrmbhj)+parseFloat(rmbhj/months))-fdkce-jfye);
            //根据调资的收入额查找不含税级距的税率和速算扣除数
            var bhsi = fixBhs050151(obj,sre);
            if(bhsi!=-1){
                //税率
                var sl = szsmlist[bhsi][12];
                if(sl == '') sl=0;
                //速算扣除数
                var sskcs = szsmlist[bhsi][13];
                if(sskcs == '') sskcs=0;
                //alert(sl+" : "+sskcs)
                //负担比率
                var fdbl = document.forms[0].dwfdbl.value;
                //调资税目的应纳税所得额＝（调资的收入额－速算扣除数×负担比例）/(1－税率×负担比例)
                oRow.all("ynssde").value=roundV((sre-sskcs*(fdbl/100))/(1-sl*(fdbl/100)));
                //用调资税目的应纳税所得额找含税级距的速算扣除数和税率
                var index = fixSysli(obj);
                if(index!='-1'){
                    //适用税率
                    var sysl = szsmlist[index][3];
                    oRow.all("sl").value =sysl;
                    //使用速算扣除数
                    var sysskcs = szsmlist[index][6];
                    oRow.all("sskcs").value =sysskcs;
                    //暂时的应纳税额＝应纳税所得额×税率－速算扣除数；
                    var tempYnse = roundV(oRow.all("ynssde").value*sysl - sysskcs);
                    //调资的应纳税额＝（上一行的应纳税额－暂时的应纳税额）×调整月份；
                    //oRow.all("ynse").value = roundV((tzqynse - tempYnse)*months);
                    oRow.all("ynse").value = tempYnse;

                    //当月应补＝（应纳税额－调整前应补）×调整的月份数
                    //oRow.all("ybtsk").value = roundV(oRow.all("ynse").value-tzqybtsk);
                    //oRow.all("ybtsk").value = roundV(oRow.all("ynse").value-ykjse);
                    oRow.all("ybtsk").value = roundV((oRow.all("ynse").value-tzqynse)*months);
                }
            }
        }



    }
}
//以前月份工资调整增部分应补交税额＝（调整后月应纳税额　－　调整前月实际应纳税额）×调整的月份数
//tzdyyn 调整当月应纳
//调整前月实际应纳税额等于前一条05011%的应纳税额，调整月份等于05011%的所得期间
function getYqybjse(tzdyyn){
    var war = '请录入调整前的工资薪金';
    if(ZRRGRSDSMX.rows.length<=3){
        //只有一条申报信息提示录入调整前的工资薪金
        alert(war);
        return 0;
    }
    //查找是否有05011%的申报
    for(var i=0;i<ZRRGRSDSMX.rows.length;i++){
        if(ZRRGRSDSMX.rows[i].all("szsmdm")){
            //是申报数据
            //匹配税种税目代码是否为05011%
            var szsmdm = ZRRGRSDSMX.rows[i].all("szsmdm").value;
            var szsmSub = szsmdm.substr(0,5);
            if(szsmSub == '05011'){
                //调整前月实际应纳税额
                var yynse=ZRRGRSDSMX.rows[i].all("ynse").value;
                if(yynse=='') yynse=0;
                //调整的月份数
                //var months=getBMonths(ZRRGRSDSMX.rows[i].all("sdksrq").value,ZRRGRSDSMX.rows[i].all("sdjsrq").value);
                //if(parseFloat(months)<0) return 0;
                //var yqybjse = roundV(parseFloat(tzdyyn)-parseFloat(yynse))*(parseFloat(months)+1);
                var yqybjse = roundV(parseFloat(tzdyyn)-parseFloat(yynse));
                return yqybjse;
            }
        }
    }
    alert(war);
    return 0;
}
//调整前月实际应纳税额等于前一条05011%的应纳税额，调整月份等于05011%的所得期间
//
function getYqybjseI(){
    var war = '请录入调整前的工资薪金';
    if(ZRRGRSDSMX.rows.length<=3){
        //只有一条申报信息提示录入调整前的工资薪金
        alert(war);
        return -1;
    }
    //查找是否有05011%的申报
    for(var i=0;i<ZRRGRSDSMX.rows.length;i++){
        if(ZRRGRSDSMX.rows[i].all("szsmdm")){
            //是申报数据
            //匹配税种税目代码是否为05011%
            var szsmdm = ZRRGRSDSMX.rows[i].all("szsmdm").value;
            var szsmSub = szsmdm.substr(0,5);
            if(szsmSub == '05011'){
                //返回行标
                return i;
            }
        }
    }
    //未找到05011
    alert(war);
    return -1;
}
//050152　退职
function com050152(obj){
    //得到当前行
    var oRow = getObjRow(obj);
    //得到税种税目代码
    var szsmdm = oRow.all("szsmdm").value;
    //得到负担方式
    var fdfs = document.forms[0].fdfs.value;
    if(szsmdm=='050152'){
        //得到收入额
        var rmbhj =  checkEmpty(oRow.all("rmbhj"));
        //减费用额
        var jfye = checkEmpty(oRow.all("jfye"));
        //已扣或已缴税额
        var ykjse = checkEmpty(oRow.all("ykjse"));
        //月收入＝　退职费/(实际在华工作时间/365)  其中时间在华工作时间＝所得截止日期－所得起始日期（包括周六周日的物理上的天数，即１月３１天，２月闰年２９天等）
        //在华工作天数
        var zhgzts = getBDays(oRow.all("sdksrq").value,oRow.all("sdjsrq").value)+1;

        var ysr = roundV(parseFloat(rmbhj)/(parseFloat(zhgzts)/365));
        if(fdfs=='3'){
            //个人负担
            //应纳税所得额＝调增后的收入额　－　费用扣除标准
            oRow.all("ynssde").value=roundV(parseFloat(ysr)-parseFloat(jfye));
            //月应纳税额＝（月收入－费用扣除标准）×税率－速算扣除数
            //退职费共计应缴纳税额＝月应纳税额×（实际在华工作时间/365）
            var index = fixSysli(obj);
            if(index!='-1'){
                //适用税率
                var sl = szsmlist[index][3];
                oRow.all("sl").value =sl;
                //使用速算扣除数
                var sskcs = szsmlist[index][6];
                oRow.all("sskcs").value =sskcs;
                var yynse = roundV((parseFloat(ysr)-jfye)*parseFloat(sl)-parseFloat(sskcs));
                //
                oRow.all("ynse").value = roundV(parseFloat(yynse)*(parseFloat(zhgzts)/365));
                //应补税额=应纳税额-已扣或已缴税额
                oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
            }
        }else if(fdfs=='1'){
            //单位负担
            //根据调资的收入额查找不含税级距的税率和速算扣除数
            var tempYsr = ysr-jfye;
            var bhsi = fixBhs050151(obj,tempYsr);
            if(bhsi!=-1){
                //税率
                var sl = szsmlist[bhsi][12];
                if(sl == '') sl=0;
                //速算扣除数
                var sskcs = szsmlist[bhsi][13];
                if(sskcs == '') sskcs=0;
                ////税目的应纳税所得额＝（月收入额-减费用额－速算扣除数）/(1－税率)
                oRow.all("ynssde").value=roundV((ysr-jfye-sskcs)/(1-sl));
                var index = fixSysli(obj);
                if(index!='-1'){
                    //适用税率
                    var sl = szsmlist[index][3];
                    oRow.all("sl").value =sl;
                    //使用速算扣除数
                    var sskcs = szsmlist[index][6];
                    oRow.all("sskcs").value =sskcs;
                    //月应纳税额＝（应纳税所得额）×税率－速算扣除数
                    var yynse = roundV(parseFloat(oRow.all("ynssde").value)*parseFloat(sl)-parseFloat(sskcs));

                    //alert(zhgzts+" : "+yynse)
                    oRow.all("ynse").value = roundV(parseFloat(yynse)*(parseFloat(zhgzts)/365));
                    //应补税额=应纳税额-已扣或已缴税额
                    oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
                }

            }
        }else if(fdfs=='2'){
            //单位负担部分
            //根据调资的收入额查找不含税级距的税率和速算扣除数
            var tempYsr = ysr-jfye;
            var bhsi = fixBhs050151(obj,tempYsr);
            if(bhsi!=-1){
                //税率
                var sl = szsmlist[bhsi][12];
                if(sl == '') sl=0;
                //速算扣除数
                var sskcs = szsmlist[bhsi][13];
                if(sskcs == '') sskcs=0;
                //负担比率
                var fdbl = document.forms[0].dwfdbl.value;
                ////税目的应纳税所得额＝（调资的收入额-减费用额－速算扣除数*负担比率）/(1－税率*负担比率)
                oRow.all("ynssde").value=roundV((ysr-jfye-sskcs*(fdbl/100))/(1-sl*(fdbl/100)));
                var index = fixSysli(obj);
                if(index!='-1'){
                    //适用税率
                    var sl = szsmlist[index][3];
                    oRow.all("sl").value =sl;
                    //使用速算扣除数
                    var sskcs = szsmlist[index][6];
                    oRow.all("sskcs").value =sskcs;
                    //月应纳税额＝（应纳税所得额）×税率－速算扣除数
                    var yynse = roundV(parseFloat(oRow.all("ynssde").value)*parseFloat(sl)-parseFloat(sskcs));
                    oRow.all("ynse").value = roundV(parseFloat(yynse)*(parseFloat(zhgzts)/365));
                    //应补税额=应纳税额-已扣或已缴税额
                    oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
                }

            }
        }


    }
}
//0503劳务报酬
function com0503(obj){
    //得到当前行
    var oRow = getObjRow(obj);
    //得到税种税目代码
    var szsmdm = oRow.all("szsmdm").value;
    var szsmSub = szsmdm.substr(0,4);
    //得到负担方式
    var fdfs = document.forms[0].fdfs.value;
    if(szsmSub=='0503'){
        //得到0503劳务报酬适用税率和速算扣除数的列表index

        //得到收入额
        var rmbhj =  checkEmpty(oRow.all("rmbhj"));
        //减费用额
        var jfye = checkEmpty(oRow.all("jfye"));
        //已扣或已缴税额
        var ykjse = checkEmpty(oRow.all("ykjse"));
        if(fdfs=='3'){
            //一、个人负担税款的
            if(parseFloat(rmbhj)<=1000){
                /*收入额1000元以下的：应纳税额=收入额*3%*/
                oRow.all("ynse").value = roundV(parseFloat(rmbhj)*0.03);
            }else if(4000>=parseFloat(rmbhj)){
                /*收入额1000-4000元的*/
                //应纳税所得额=收入额（输入项<=4000）-标准扣除费用（等于800）
                oRow.all("ynssde").value=roundV(parseFloat(rmbhj)-parseFloat(jfye));

                //应纳税额=应纳税所得额*20%
                oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2);
                //应补税额=应纳税额-已扣或已缴税额（注：指本期已扣缴或已缴纳税额）
                oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
            }else if(20000>=parseFloat(rmbhj)){
                //4000-20000
                //减费用额 = 收入额*20%
                oRow.all("jfye").value=roundV(parseFloat(rmbhj)*0.2);
                //应纳税所得额=收入额（输入项>4000）-标准扣除费用（等于收入额*20%）
                oRow.all("ynssde").value=roundV(parseFloat(rmbhj)-parseFloat(rmbhj)*0.2);
                //应纳税额=应纳税所得额*税率（20％）（见税率表--劳务报酬适用）-速算扣除数（800）（见税率表--劳务报酬适用）
                //oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2 -800);
                oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2 );
                //应补税额=应纳税额-已扣或已缴税额（注：指本期已扣缴或已缴纳税额）
                oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
            }else if(50000>=parseFloat(rmbhj)){
                //加成征收：
                //收入额20000-50000元的：
                //应纳税额=[收入额*（1-20%）]*30%-2000
                oRow.all("ynse").value = roundV(parseFloat(rmbhj)*0.8*0.3 - 2000);
            }else if(50000<parseFloat(rmbhj)){
                //收入额超过50000元的
                //应纳税额=[收入额*（1-20%）]*40%-7000
                oRow.all("ynse").value = roundV(parseFloat(rmbhj)*0.8*0.4 - 7000);
            }
            //根据匹配重设税种税目
            fixSysl0503(obj);

        }else if(fdfs=='1'){
            //公司负担税款的
            if(parseFloat(rmbhj)<=3360){
                //不含税收入≤3360元的
                //应纳税所得额=（不含税收入额-800）÷（1-20%）
                oRow.all("ynssde").value=roundV((parseFloat(rmbhj)-800)/0.8);
                //应纳税额=应纳税所得额*20%
                oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2);
            }else if(parseFloat(rmbhj)<=21000){
                //3360<不含税收入≤ 21000 元的
                //应纳税所得额=不含税收入*20÷21
                oRow.all("ynssde").value=roundV((parseFloat(rmbhj)*20)/21);
                //应纳税额=应纳税所得额*20%
                oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2);
            }else if(parseFloat(rmbhj)<=49500){
                //21000<不含税收入≤ 49500 元的
                //应纳税所得额=（不含税收入-2000）*20÷19
                oRow.all("ynssde").value=roundV(((parseFloat(rmbhj)-2000)*20)/19);
                //应纳税额=应纳税所得额*30%-2000
                oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.3-2000);

            }else if(parseFloat(rmbhj)>49500){
                //不含税收入超过495000  元的
                //应纳税所得额=（不含税收入-7000）*20÷17
                oRow.all("ynssde").value=roundV(((parseFloat(rmbhj)-7000)*20)/17);
                //应纳税额=应纳税所得额*40%-7000
                oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.4-7000);
            }
            //根据匹配重设税种税目
            fixSysl0503(obj);
        }

    }
}
//得到0503劳务报酬适用税率和速算扣除数的列表index
function fixSysl0503(obj){
    //得到当前行
    var oRow = getObjRow(obj);
    var ynssde = oRow.all("ynssde").value;
    for(i=0;i<szsmlist.length;i++){
        var szsmSub = szsmlist[i][0].substr(0,4);
    //找到大于应纳税起始数小于应纳税终止数的税种税目
    //if(szsmSub=="0503") alert(szsmlist[i][0]+" : "+ynssde+" : "+szsmlist[i][4]+" : "+szsmlist[i][5])
        if(szsmSub=="0503" && parseFloat(szsmlist[i][4])<parseFloat(ynssde) && parseFloat(szsmlist[i][5])>=parseFloat(ynssde)){

            //设置税种税目代码
            oRow.all("szsmdm").value = szsmlist[i][0];
            //设置税款所属日期
            //oRow.all("sdksrq").value = szsmlist[i][7];
            //oRow.all("sdjsrq").value = szsmlist[i][8];
            //设置税率
            //oRow.all("sl").value = szsmlist[i][3];
            //设置速算扣除数
            //oRow.all("sskcs").value = szsmlist[i][6];
            //保留当前税款所属日期
            var oks = oRow.all("sdksrq").value;
            var ojs = oRow.all("sdjsrq").value;
            var ojfye = oRow.all("jfye").value;

            resetRow("szsmdm","ZRRGRSDSMX",oRow.all("szsmdm"));
            //重设税款所属日期
            oRow.all("sdksrq").value = oks;
            oRow.all("sdjsrq").value = ojs;
            oRow.all("jfye").value = ojfye;
            //oRow.all("szsmdm").select();
            return i;
        }
    }
    //未找到适合税率
    return -1;
}
//得到0503劳务报酬不含税级距的税种税目数组index
function fixBhs0503(obj){
    //得到当前行
    var oRow = getObjRow(obj);
    //得到收入额
    var rmbhj =  checkEmpty(oRow.all("rmbhj"));
    //减费用额
    var jfye = checkEmpty(oRow.all("jfye"));
    //计算不含税参数
    var bhs = roundV(parseFloat(rmbhj)-parseFloat(jfye));
    for(i=0;i<szsmlist.length;i++){
        var szsmSub = szsmlist[i][0].substr(0,4);
    //找到大于不含税所得起始起始数小于不含税所得起始终止数的税种税目

        if(szsmSub=="0503" && parseFloat(szsmlist[i][10])<parseFloat(bhs) && parseFloat(szsmlist[i][11])>=parseFloat(bhs)){
            return i;
        }
    }
    //未查询到相应的不含税级距的税种税目
    return -1;
}
//0504稿酬所得
function com0504(obj){
    //得到当前行
    var oRow = getObjRow(obj);
    //得到税种税目代码
    var szsmdm = oRow.all("szsmdm").value;
    var szsmSub = szsmdm.substr(0,4);
    //得到负担方式
    var fdfs = document.forms[0].fdfs.value;
    if(szsmSub=='0504'){
        //得到收入额
        var rmbhj =  checkEmpty(oRow.all("rmbhj"));
        //减费用额
        var jfye = checkEmpty(oRow.all("jfye"));
        //已扣或已缴税额
        var ykjse = checkEmpty(oRow.all("ykjse"));
        if(parseFloat(rmbhj)<=4000){
            //应纳税所得额=收入额（输入项，<=4000注：稿酬所得以每次出版、发表取得的收入为一次）-标准扣除费用（等于800）
            oRow.all("ynssde").value=roundV(parseFloat(rmbhj)-parseFloat(jfye));
            //应纳税额=应纳税所得额*20%*（1-30%）
            oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2*0.7);
            //应补税额=应纳税额-已扣或已缴税额
            oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
        }else if(parseFloat(rmbhj)>4000){
            //减费用额 = 收入额*20%
            oRow.all("jfye").value=roundV(parseFloat(rmbhj)*0.2);
            //应纳税所得额=收入额（输入项，>4000注：稿酬所得以每次出版、发表取得的收入为一次）-标准扣除费用（等于收入额*20%）
            oRow.all("ynssde").value=roundV(parseFloat(rmbhj)-parseFloat(rmbhj)*0.2);
            //应纳税额=应纳税所得额*20%*（1-30%）
            oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2*0.7);
            //应补税额=应纳税额-已扣或已缴税额（注：指本期已扣缴或已缴纳税额）
            oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
        }
    }
}
//0505稿酬所得
function com0505(obj){
    //得到当前行
    var oRow = getObjRow(obj);
    //得到税种税目代码
    var szsmdm = oRow.all("szsmdm").value;
    var szsmSub = szsmdm.substr(0,4);
    //得到负担方式
    var fdfs = document.forms[0].fdfs.value;
    if(szsmSub=='0505'){
        //得到收入额
        var rmbhj =  checkEmpty(oRow.all("rmbhj"));
        //减费用额
        var jfye = checkEmpty(oRow.all("jfye"));
        //已扣或已缴税额
        var ykjse = checkEmpty(oRow.all("ykjse"));
        if(parseFloat(rmbhj)<=4000){
            //应纳税所得额=收入额（输入项，<=4000注：特许权使用费所得，以一项特许权的一次许可使用所取得的收入为一次）-标准扣除费用（等于800）
            oRow.all("ynssde").value=roundV(parseFloat(rmbhj)-parseFloat(jfye));
            //应纳税额=应纳税所得额*20%
            oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2);
            //应补税额=应纳税额-已扣或已缴税额
            oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
        }else if(parseFloat(rmbhj)>4000){

            //减费用额 = 收入额*20%
            oRow.all("jfye").value=roundV(parseFloat(rmbhj)*0.2);
        //应纳税所得额=收入额（输入项，>4000注：特许权使用费所得，以一项特许权的一次许可使用所取得的收入为一次）-标准扣除费用（等于收入额*20%）
            oRow.all("ynssde").value=roundV(parseFloat(rmbhj)-parseFloat(rmbhj)*0.2);
            //应纳税额=应纳税所得额*20%
            oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2);
            //应补税额=应纳税额-已扣或已缴税额（注：指本期已扣缴或已缴纳税额）
            oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
        }
    }
}
//0506股息、利息、红利所得
function com0506(obj){
    //得到当前行
    var oRow = getObjRow(obj);
    //得到税种税目代码
    var szsmdm = oRow.all("szsmdm").value;
    var szsmSub = szsmdm.substr(0,4);
    //得到负担方式
    var fdfs = document.forms[0].fdfs.value;
    if(szsmSub=='0506'){
        //得到收入额
        var rmbhj =  checkEmpty(oRow.all("rmbhj"));
        //减费用额
        var jfye = checkEmpty(oRow.all("jfye"));
        //已扣或已缴税额
        var ykjse = checkEmpty(oRow.all("ykjse"));
        //减费用额 = 0
        oRow.all("jfye").value=0;
        //应纳税所得额=收入额（输入项，注：利息、股息、红利所得，以支付利息、股息、红利时取得的收入为一次）-标准扣除费用（等于0）
        oRow.all("ynssde").value=roundV(parseFloat(rmbhj));
        //应纳税额=应纳税所得额*20%
        oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2);
        //应补税额=应纳税额-已扣或已缴税额
        oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));

    }
}
//050702一般财产租赁
function com050702(obj){
    //得到当前行
    var oRow = getObjRow(obj);
    //得到税种税目代码
    var szsmdm = oRow.all("szsmdm").value;
    var szsmSub = szsmdm.substr(0,4);
    //得到负担方式
    var fdfs = document.forms[0].fdfs.value;
    if(szsmdm=='050702'){
        //得到收入额
        var rmbhj =  checkEmpty(oRow.all("rmbhj"));
        //减费用额
        var jfye = checkEmpty(oRow.all("jfye"));
        //已扣或已缴税额
        var ykjse = checkEmpty(oRow.all("ykjse"));
        if(parseFloat(rmbhj)<=4000){			//应纳税所得额=收入额（输入项，<=4000注：特许权使用费所得，以一项特许权的一次许可使用所取得的收入为一次）-标准扣除费用（等于800）
            oRow.all("ynssde").value=roundV(parseFloat(rmbhj)-parseFloat(jfye));
            //应纳税额=应纳税所得额*20%
            oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2);
            //应补税额=应纳税额-已扣或已缴税额
            oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
        }else if(parseFloat(rmbhj)>4000){

            //减费用额 = 收入额*20%
            oRow.all("jfye").value=roundV(parseFloat(rmbhj)*0.2);	//应纳税所得额=收入额（输入项，>4000注：特许权使用费所得，以一项特许权的一次许可使用所取得的收入为一次）-标准扣除费用（等于收入额*20%）
            oRow.all("ynssde").value=roundV(parseFloat(rmbhj)-parseFloat(rmbhj)*0.2);
            //应纳税额=应纳税所得额*20%
            oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2);
            //应补税额=应纳税额-已扣或已缴税额（注：指本期已扣缴或已缴纳税额）
            oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
        }
    }
}
//050701个人私房出租
function com050701(obj){
    //得到当前行
    var oRow = getObjRow(obj);
    //得到税种税目代码
    var szsmdm = oRow.all("szsmdm").value;
    var szsmSub = szsmdm.substr(0,4);
    //得到负担方式
    var fdfs = document.forms[0].fdfs.value;
    if(szsmdm=='050701'){
        //得到收入额
        var rmbhj =  checkEmpty(oRow.all("rmbhj"));
        //减费用额
        var jfye = checkEmpty(oRow.all("jfye"));
        //已扣或已缴税额
        var ykjse = checkEmpty(oRow.all("ykjse"));
        if(parseFloat(rmbhj)<=4000){			//应纳税所得额=收入额（输入项，<=4000注：特许权使用费所得，以一项特许权的一次许可使用所取得的收入为一次）-标准扣除费用（等于800）
            oRow.all("ynssde").value=roundV(parseFloat(rmbhj)-parseFloat(jfye));
            //应纳税额=应纳税所得额*10%
            oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.1);
            //应补税额=应纳税额-已扣或已缴税额
            oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
        }else if(parseFloat(rmbhj)>4000){

        //减费用额 = 收入额*20%
            oRow.all("jfye").value=roundV(parseFloat(rmbhj)*0.2);	//应纳税所得额=收入额（输入项，>4000注：特许权使用费所得，以一项特许权的一次许可使用所取得的收入为一次）-标准扣除费用（等于收入额*20%）
            oRow.all("ynssde").value=roundV(parseFloat(rmbhj)-parseFloat(rmbhj)*0.2);
            //应纳税额=应纳税所得额*10%
            oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.1);
            //应补税额=应纳税额-已扣或已缴税额（注：指本期已扣缴或已缴纳税额）
            oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));
        }
    }
}
//0508财产转让所得
function com0508(obj){
    //得到当前行
    var oRow = getObjRow(obj);
    //得到税种税目代码
    var szsmdm = oRow.all("szsmdm").value;
    var szsmSub = szsmdm.substr(0,4);
    //得到负担方式
    var fdfs = document.forms[0].fdfs.value;
    if(szsmSub=='0508'){
        //得到收入额
        var rmbhj =  checkEmpty(oRow.all("rmbhj"));
        //减费用额
        var jfye = checkEmpty(oRow.all("jfye"));
        //已扣或已缴税额
        var ykjse = checkEmpty(oRow.all("ykjse"));
        //减费用额 = 0
        oRow.all("jfye").value=0;
        //应纳税所得额=收入额（输入项，注：利息、股息、红利所得，以支付利息、股息、红利时取得的收入为一次）-标准扣除费用（等于0）
        oRow.all("ynssde").value=roundV(parseFloat(rmbhj));
        //应纳税额=应纳税所得额*20%
        oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2);
        //应补税额=应纳税额-已扣或已缴税额
        oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));

    }
}
//0509偶然所得
function com0509(obj){
    //得到当前行
    var oRow = getObjRow(obj);
    //得到税种税目代码
    var szsmdm = oRow.all("szsmdm").value;
    var szsmSub = szsmdm.substr(0,4);
    //得到负担方式
    var fdfs = document.forms[0].fdfs.value;
    if(szsmSub=='0509'){
        //得到收入额
        var rmbhj =  checkEmpty(oRow.all("rmbhj"));
        //减费用额
        var jfye = checkEmpty(oRow.all("jfye"));
        //已扣或已缴税额
        var ykjse = checkEmpty(oRow.all("ykjse"));
        //减费用额 = 0
        oRow.all("jfye").value=0;
        //应纳税所得额=收入额（输入项，注：利息、股息、红利所得，以支付利息、股息、红利时取得的收入为一次）-标准扣除费用（等于0）
        oRow.all("ynssde").value=roundV(parseFloat(rmbhj));
        //应纳税额=应纳税所得额*20%
        oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2);
        //应补税额=应纳税额-已扣或已缴税额
        oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));

    }
}
//0510经国务院财政部门确定征税的其他所得
function com0510(obj){
    //得到当前行
    var oRow = getObjRow(obj);
    //得到税种税目代码
    var szsmdm = oRow.all("szsmdm").value;
    var szsmSub = szsmdm.substr(0,4);
    //得到负担方式
    var fdfs = document.forms[0].fdfs.value;
    if(szsmSub=='0510'){
        //得到收入额
        var rmbhj =  checkEmpty(oRow.all("rmbhj"));
        //减费用额
        var jfye = checkEmpty(oRow.all("jfye"));
        //已扣或已缴税额
        var ykjse = checkEmpty(oRow.all("ykjse"));
        //减费用额 = 0
        oRow.all("jfye").value=0;
        //应纳税所得额=收入额（输入项，注：利息、股息、红利所得，以支付利息、股息、红利时取得的收入为一次）-标准扣除费用（等于0）
        oRow.all("ynssde").value=roundV(parseFloat(rmbhj));
        //应纳税额=应纳税所得额*20%
        oRow.all("ynse").value = roundV(parseFloat(oRow.all("ynssde").value)*0.2);
        //应补税额=应纳税额-已扣或已缴税额
        oRow.all("ybtsk").value=roundV(parseFloat(oRow.all("ynse").value)-parseFloat(ykjse));

    }
}

function bzdmClass(){
  this.bzlist_fields="";
    this.bzlist="";
    this.bzdm_value="";
    this.bzdm_name="";
    this.bzdm_value_all="";
    this.bzdm_name_all="";
    this.bzdm_name_del="";
    this.bzdm_value_del="";
    this.row="";
    return this;
}
function showbzdm(obj){
    if(window.event.keyCode!=120) return;
  var dialogClass=new bzdmClass();
    dialogClass["bzlist_fields"]=bzlist_fields;
    dialogClass["bzlist"]=bzlist;
    dialogClass["bzdm_value"]=bzdm_value;
    dialogClass["bzdm_name"]=bzdm_name;
    dialogClass["bzdm_value_all"]=bzdm_value_all;
    dialogClass["bzdm_name_all"]=bzdm_name_all;
    dialogClass["bzdm_name_del"]=bzdm_name_del;
    dialogClass["bzdm_value_del"]=bzdm_value_del;
    //dialogClass["jsArr_BZDMMX"]=jsArr_BZDMMX;
    //dialogClass["BZDMMX_list"]=BZDMMX_list;
    dialogClass["row"]=getObjRow(obj);
    var value=window.showModalDialog("bzdm.html",dialogClass,"");
    if(value!=null){
    obj.value=value;
    }
    hjRMB(obj);
}
//var vvvv=Math.round(1000.4)
//alert(vvvv)
</script>
</body>
</html:html>
