//
//
// 2004-12-17 Created by YangJian
// 2011-02-17 Add code by YangJian
//
String.prototype.getBytesLength = function() { 
return this.replace(/[^\x00-\xff]/gi, "--").length; 
};

function $(objStr)
{
	var obj = document.getElementById(objStr);
	if (obj == null) { // For firefox
		obj = document.getElementsByName(objStr);
		if (obj != null) return obj[0];
	}
	return obj;
}
// 获取浏览器类型
function getOs() 
{ 
	var OsObject = ""; 
	if(navigator.userAgent.indexOf("MSIE")>0) { 
		return "MSIE"; 
	} 
	if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){ 
		return "Firefox"; 
	} 
	if(isSafari=navigator.userAgent.indexOf("Safari")>0) { 
		return "Safari"; 
	} 
	if(isCamino=navigator.userAgent.indexOf("Camino")>0){ 
		return "Camino"; 
	} 
	if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){ 
		return "Gecko"; 
	} 
} 
// 用 "|" 格式串填充  Select
function fillSelect(selObj, valStr, txtStr)
{
    var valArray = valStr.substring(0,valStr.length-1).split('|');
    var txtArray = txtStr.substring(0,txtStr.length-1).split('|');
    
    len = valArray.length;
    len2 = txtArray.length;
    if (len2 < len) len = len2;
    
    selObj.options.length = 0;
    for (i = 0;i < len;i++) {
    	selObj.options[i] = new Option(txtArray[i], valArray[i]);
    }
}
// 针对 firefox 的特殊方法，firefox无法将刚插入 select 的item删除
// 用 "|" 格式串填充  Select, valStr和txtStr的除itemVals中的插入selObj1, itemVals中的插入selObj2
function fillFirefoxSelect(selObj1, valStr, txtStr, selObj2, itemVals)
{
    var valArray = valStr.substring(0,valStr.length-1).split('|');
    var txtArray = txtStr.substring(0,txtStr.length-1).split('|');

    // 针对 itemVals 为一个，没有后续 '|' 的情况
    strLen = itemVals.length - 1;
    if (itemVals.charAt(itemVals.strLen) != "|") strLen += 1;
    var itemArray = itemVals.substring(0,strLen).split('|');
    
    len = valArray.length;
    len2 = txtArray.length;
    len3 = itemArray.length;
    if (len2 < len) len = len2;
    
    selObj1.options.length = 0;
    selObj2.options.length = 0;
    for (i = 0;i < len;i++) {
    	for (k = 0;k < len3;k++) {
    		if (valArray[i] == itemArray[k]) break;
    	}
    	if (k < len3) {
			selObj2.options[selObj2.options.length] = new Option(txtArray[i], valArray[i]);
    	} else {
    		selObj1.options[selObj1.options.length] = new Option(txtArray[i], valArray[i]);
    	}
    }
}
// 从 Select 中删除一个item
function removeFromSelect(selObj, itemVal) {
	for (i = 0;i < selObj.options.length;i++) {
		if (selObj.options[i].value == itemVal) {
			selObj.options.remove(i);
			break;
		}
	}
}
// 从 Select1 中移动  value item 到 Select2
function moveValueBetweenSelect(sel1, sel2, itemVal) {
	for (i = 0;i < sel1.options.length;i++) {
		if (sel1.options[i].value == itemVal) {
			sel2.options[sel2.length] = 
				new Option(sel1.options[i].text, itemVal);
			sel1.options.remove(i);
			break;
		}
	}
}

// 移动sel1中所有选中item到sel2
function moveItemsBetweenSelect(sel1, sel2) {
	var itemArr = new Array();
	var j = 0;
	for (i = 0;i < sel1.length;i++) {
		if (sel1.options[i].selected) {
			sel2.options[sel2.length] = 
				new Option(sel1.options[i].text, sel1.options[i].value);
		      	itemArr[j] = i;
		      	j++;
		    }
		}
	for(i = itemArr.length-1;i >= 0;i--)
		sel1.options[itemArr[i]] = null;
}

// 获取select中所有值格式化串
function getStringFromSelect(selObj) {
	var valStr = "";
	for (i = 0;i < selObj.length;i++) {
		valStr += selObj.options[i].value + "|";
	}
	return valStr;
}
// 处理图片 JavaScript
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

function MM_showHideLayers() { //v6.0
  var i,p,v,obj,args=MM_showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3) if ((obj=MM_findObj(args[i]))!=null) { v=args[i+2];
    if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v=='hide')?'hidden':v; }
    obj.visibility=v; }
}

// 退出到菜单
function Fn_Exit(webRoot)
{
    document.location.href="/"+webRoot+"/frame/login/main.jsp";
    return false;
}

function OnInit()
{
    if (document.all.msg.value != null &&
    		document.all.msg.value != "")
    {
        alert(document.all.msg.value);
        document.all.msg.value = "";
    }
}
// 判断Select是否选择（值为""说明没有选择）
function Fn_validate_select(object, msg)
{
    if (object.value == "")
    {
        object.focus();
        alert("请选择：" + msg + "！");
        return false;
    }
    return true;
}

// 判断输入数据从长度上是否合法
function Fn_validate(object, nullable, maxlength, msg)
{
    var obj = object.value;
    if (obj == null || obj == "" || obj.length == 0)
    {
        if (nullable == false)
        {
            object.focus();
            alert(msg + "不能为空，请重新输入！");
            return false;
        }
        else return true;
    }
    else
    {
        if(obj.getBytesLength() > maxlength)
        {
            object.focus();
            object.select();
            alert(msg + "过长，限定长度为" + maxlength + "个字节，请重新输入！");
            return false;
        }
        return true;
    }
    return true;
}
// 判断是否为空
function Fn_NullDate(object, msg) {
	if (object == null || object.value == "" || object.length == 0) {
        alert(msg + "不能为空，请重新输入！");
        return false;
	}
	return true;
}

//判断输入数据从长度上是否合法（针对hidden类型的component)
function Fn_validate_hidden(object, nullable, maxlength, msg)
{
    var obj = object.value;
    if (obj == null || obj == "" || obj.length == 0)
    {
        if (nullable == false)
        {
            alert(msg + "不能为空，请重新输入！");
            return false;
        }
        else return true;
    }
    else
    {
        if(obj.getBytesLength() > maxlength)
        {
            object.focus();
            object.select();
            alert(msg + "过长，限定长度为" + maxlength + "个字节，请重新输入！");
            return false;
        }
        return true;
    }
    return true;
}
// 判断录入内容是否为数字
function Fn_Number(object, msg)
{
	//检测是否存在非法字符变量
    var strTemp = "0123456789";
    var strValue = object.value;

    //检测是否存在非法字符
    for(i = 0;i < strValue.length;i++)
    {
		if (strTemp.indexOf(strValue.charAt(i)) == -1)
       	{
       		alert(msg + "存在非法字符，请录入数字！");
            object.focus();
            object.select();
           	return false;
       	}
    }
    return true;
}

function is_Number(strValue)
{
	//检测是否存在非法字符变量
    var strTemp = "0123456789";
    //检测是否存在非法字符
    for(i = 0;i < strValue.length;i++)
    {
		if (strTemp.indexOf(strValue.charAt(i)) == -1)
       	{
           	return false;
       	}
    }
    return true;
}

/*
==================================================================
字符串操作
Trim(string):去除字符串两边的空格
==================================================================
*/
/*
==================================================================
LTrim(string):去除左边的空格
==================================================================
*/
function LTrim(str)
{
    var whitespace = new String(" \t\n\r");
    var s = new String(str);
    if (whitespace.indexOf(s.charAt(0)) != -1)
    {
        var j=0, i = s.length;
        while (j < i && whitespace.indexOf(s.charAt(j)) != -1)
        {
            j++;
        }
        s = s.substring(j, i);
    }
    return s;
}
/*
==================================================================
RTrim(string):去除右边的空格
==================================================================
*/
function RTrim(str)
{
    var whitespace = new String(" \t\n\r");
    var s = new String(str);
    if (whitespace.indexOf(s.charAt(s.length-1)) != -1)
    {
        var i = s.length - 1;
        while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1)
        {
            i--;
        }
        s = s.substring(0, i+1);
    }
    return s;
}
/*
==================================================================
Trim(string):去除前后空格
==================================================================
*/
function Trim(str)
{
    return RTrim(LTrim(str));
}

/*
	格式：2005-01-01 12:20
*/
function Fn_isDate(obj, nullable, msg)
{
    var strTimeArray;
    var boolLeapYear;//是否是闰年
    var i, j, strTemp, sp1, sp2, sp3, sp4;
    var year, month, day, hour, min;

	var date = obj.value;
    if ((date == null || date.length == 0) && nullable == true)  return true;
    if (date == null || date.length != 16)
    {
        alert(msg + "，长度不符合格式，格式：2005-01-01 12:05");
        obj.focus();
        obj.select();
        return false;
    }

    strTemp = "0123456789-: ";
    for(i = 0;i < date.length;i++)//检测是否存在非法字符
    {
        j = strTemp.indexOf(date.charAt(i));
        if (j == -1)
        {
	        alert(msg + "，存在非法字符，格式：2005-01-01 12:05！");
	        obj.focus();
    	    obj.select();
            return false;
        }
    }

    year = parseInt(date.substring(0, 4), 10);
    sp1 = date.substring(4, 5);
    month = parseInt(date.substring(5, 7), 10);
    sp2 = date.substring(7, 8);
    day = parseInt(date.substring(8, 10), 10);
    sp3 = date.substring(10, 11);
    hour = parseInt(date.substring(11, 13), 10);
    sp4 = date.substring(13, 14);
    min = parseInt(date.substring(14, 16), 10);

	if (sp1 != "-" || sp2 != "-" || sp3 != " " || sp4 != ":")
    {
        alert(msg + "，长度不符合格式，格式：2005-01-01 12:05");
        obj.focus();
        obj.select();
        return false;
    }

    if (month > 12 || month < 1)
    {
        alert(msg + "，输入的月份应该在1到12之间！");
        obj.focus();
        obj.select();
        return false;
    }

    if ((month == 1 || month == 3 || month == 5 || month == 7 ||
         month == 8 || month == 10 || month == 12) && (day > 31 || day < 1))
    {
        alert(msg + month + "，月的天数应该在1-31之间！");
        obj.focus();
        obj.select();
        return false;
    }

    if ((month == 4 || month == 6 || month == 9 || month == 11) && (day > 30 || day < 1))
    {
        alert(msg + month + "，月的天数应该在1-30之间！");
        obj.focus();
        obj.select();
        return false;
    }

    if (month == 2)//2月
    {
        if (day < 1)
        {
            alert(msg + "，2月的天数应该大于1！");
	        obj.focus();
    	    obj.select();
            return false;
        }

        boolLeapYear = false;
        if ((year % 100) == 0)
        {
            if((year % 400) == 0)  boolLeapYear = true;//是闰年
        }
        else
        {
            if((year % 4) == 0) boolLeapYear = true;//是闰年
        }
        if(boolLeapYear)//是闰年
        {
            if(day > 29)
            {
                alert(msg + "，闰年2月的天数应该在1-29之间！");
    	    	obj.focus();
	    	    obj.select();
                return false;
            }
        }//是闰年
        else//非闰年
        {
            if(day > 28)
            {
                alert(msg + "，非闰年2月的天数应该在1-28之间！");
	    	    obj.focus();
    	    	obj.select();
                return false;
            }
        }//非闰年
    }//2月

    if ((hour < 0) || (hour > 23))
    {
        alert(msg + "，小时应该在0-23之间！");
        obj.focus();
        obj.select();
        return false;
    }
    if ((min < 0) || (min > 59))
    {
        alert(msg + "，分钟应该在0-59之间！");
        obj.focus();
        obj.select();
        return false;
    }
    return true;
}

function isYearMonthStr(object)
{
    var str = object.value;
    if (str == null || str.length != 6)
    {
        alert("请按照正确格式输入统计年月！（例：200501）");
        object.focus();
        object.select();
        return false;
    }

    var strTemp = "0123456789";
    //检测是否存在非法字符
    for(i = 0;i < str.length;i++)
    {
		if (strTemp.indexOf(str.charAt(i)) == -1)
       	{
	        alert("请按照正确格式输入统计年月！（例：200501）");
            object.focus();
            object.select();
           	return false;
       	}
    }

    year = parseInt(str.substring(0, 4), 10);
    month = parseInt(str.substring(4, 6), 10);

    if (year < 1900 || year > 2050)
    {
        alert("请合理输入统计年！（1900-2050）");
        object.focus();
        object.select();
        return false;
    }
    if (month < 1 || month > 12)
    {
        alert("请合理输入统计月份！（01-12）");
        object.focus();
        object.select();
        return false;
    }
    return true;
}

function Fn_isDateString(date, nullable, msg)
{
    var strTimeArray;
    var boolLeapYear;//是否是闰年
    var i, j, strTemp, sp1, sp2, sp3, sp4;
    var year, month, day, hour, min;

    if ((date == null || date.length == 0) && nullable == true)  return true;
    if (date == null || date.length != 16)
    {
        alert(msg + "，长度不符合格式，格式：2005-01-01 12:05");
        return false;
    }

    strTemp = "0123456789-: ";
    for(i = 0;i < date.length;i++)//检测是否存在非法字符
    {
        j = strTemp.indexOf(date.charAt(i));
        if (j == -1)
        {
	        alert(msg + "，存在非法字符，格式：2005-01-01 12:05！");
            return false;
        }
    }

    year = parseInt(date.substring(0, 4), 10);
    sp1 = date.substring(4, 5);
    month = parseInt(date.substring(5, 7), 10);
    sp2 = date.substring(7, 8);
    day = parseInt(date.substring(8, 10), 10);
    sp3 = date.substring(10, 11);
    hour = parseInt(date.substring(11, 13), 10);
    sp4 = date.substring(13, 14);
    min = parseInt(date.substring(14, 16), 10);

	if (sp1 != "-" || sp2 != "-" || sp3 != " " || sp4 != ":")
    {
        alert(msg + "，长度不符合格式，格式：2005-01-01 12:05");
        return false;
    }

    if (month > 12 || month < 1)
    {
        alert(msg + "，输入的月份应该在1到12之间！");
        return false;
    }

    if ((month == 1 || month == 3 || month == 5 || month == 7 ||
         month == 8 || month == 10 || month == 12) && (day > 31 || day < 1))
    {
        alert(msg + month + "，月的天数应该在1-31之间！");
        return false;
    }

    if ((month == 4 || month == 6 || month == 9 || month == 11) && (day > 30 || day < 1))
    {
        alert(msg + month + "，月的天数应该在1-30之间！");
        return false;
    }

    if (month == 2)//2月
    {
        if (day < 1)
        {
            alert(msg + "，2月的天数应该大于1！");
            return false;
        }

        boolLeapYear = false;
        if ((year % 100) == 0)
        {
            if((year % 400) == 0)  boolLeapYear = true;//是闰年
        }
        else
        {
            if((year % 4) == 0) boolLeapYear = true;//是闰年
        }
        if(boolLeapYear)//是闰年
        {
            if(day > 29)
            {
                alert(msg + "，闰年2月的天数应该在1-29之间！");
                return false;
            }
        }//是闰年
        else//非闰年
        {
            if(day > 28)
            {
                alert(msg + "，非闰年2月的天数应该在1-28之间！");
                return false;
            }
        }//非闰年
    }//2月

    if ((hour < 0) || (hour > 23))
    {
        alert(msg + "，小时应该在0-23之间！");
        return false;
    }
    if ((min < 0) || (min > 59))
    {
        alert(msg + "，分钟应该在0-59之间！");
        return false;
    }
    return true;
}

//function  : 检查输入字符串是否由数字或（数字和小数点）组成；xxxxxx.xx；
//            通常用于检查输入的金额是否合法
//parameters: obj：输入区域对象，其值为待检查串
//            minValue：输入数字的下界；即不能小于该值
//            maxValue：输入数字的上界；即不能大于该值
//            empty：标明该输入区域是否可以为空；当值为null时表明该区域可以为空
//            totalLength：数字的长度限制（不包括小数点）
//	      decimalLength：小数位的长度限制；如果是0，则没有小数位，否则都设定为2
//            msg：提示信息
//return    : false－字符串中包含除数字外的字符
//	      true－符合上述所有条件
function Fn_IsNum(obj,minValue,maxValue,empty,totalLength,decimalLength,msg)
{
  var digitString=obj.value;
  var succeed=true;
  var alertStr="";  //提示信息

  var havedecimalLength=true;
  var or_decimalLength=decimalLength;

  if(digitString.length==0){
    if(empty!=null){
      alertStr+="输入值不能为!\n";
      alert(alertStr);
      return false;
    }else{
      return true;
    }
  }
  //当小数部分长度为0时,将小数部分设为null；即：没有小数位
  if(decimalLength=="0"){
    decimalLength=null;
    or_decimalLength=0;
  }else{
    decimalLength=2;
    or_decimalLength=2;
  }
  var re = "";
  //当小数部分长度不为null，且输入值没有小数点，则在输入值后加一个小数点后和00
  if(decimalLength != null && digitString.indexOf(".")<0)
  {
    digitString+=".00";
  }
  if (decimalLength != null)
  {   //小数不为空
      re = re+"\\.[\\d]{1,"+ decimalLength +"}";
  }

  if (totalLength == null)
  {   //未设定总长度
      re= "\\d*"+re;
  }
  else
  {   //设定总长度
    //当小数部分为空的情况下，就是判断数字的长度
    var intergerLength = totalLength;
    if (decimalLength!=null)
    {
      //当小数部分不为空判断，整数的位数和小数的位数是否正确
      intergerLength = totalLength-decimalLength;
    }
    re="[\\d]{1,"+ intergerLength +"}"+re;//不允许从小数点开始
  }
  re = new RegExp("^(-){0,1}"+re+"$","g");
  //当字符串为空、位数不对、不能匹配成数字时
  if(re.exec(digitString) == null)
  {
    alertStr+="输入域的值必须为数字!\n";
    if(totalLength!=null) alertStr+="数字的总长度为"+totalLength+"位!\n";
    if(havedecimalLength)
    {
      alertStr+="小数点后的长度为"+or_decimalLength+"位!\n";
    }
    succeed=false;
  }
  //当指定字符串范围时 字符串不在指定范围内
  if(maxValue!=null){
    if(parseFloat(digitString)>parseFloat(maxValue)){
      alertStr+="数字不能大于"+maxValue+"\n";
      succeed= false;
    }
  }
  if(minValue!=null){
    if(parseFloat(digitString)<parseFloat(minValue)){
      alertStr+="数字不能小于"+minValue+"\n";
      succeed= false;
    }
  }
  if(alertStr!="") {
    alertStr = msg+"::\n"+alertStr;
    alert(alertStr);
    window.event.returnValue=false;
    obj.focus();
    obj.select();
  }
  return succeed;
}
