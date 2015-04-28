//判断该字符串是否由字符组成
function IsAlpha(str)
	{

		if(str.length==0) return false;
	    for(var loc=0;loc<str.length;loc++)
		   if((('a'<=str.chatAt(loc)) && (str.chatAt(loc)<='z')) || (('A'<=str.chatAt(loc)) && (str.chatAt(loc)<='Z')))
		      return false;
        return true;
	}

//判断该字符串是否为整型,必须是0-9的数
function isDigit(str)
{
	if(str==null || str.length==0)
	{
		return false;
	}
	for(var loc=0;loc<str.length;loc++)
	{
		if(str.charAt(loc) < '0' || str.charAt(loc) > '9')
		{
                	return false;
		}
	}
    return true;
}

//判断该字符串是否为数，该数可以为带小数点的数
function isNumber(str)
{
	if(!Number(str))
		return false;
	return true;
}

//判断一个数是否为正整数
//参数：strNum ---- 需要判断的字符串
//返回值：true ---- 整数 false ---- 非整数
function fnIsIntNum(strNum)
{
 var strCheckNum = strNum + "";

// var c = strCheckNum.charAt(0);
// if (c!="1" || c!="2" || c!="3" || c!="4" || c!="5" || c!="6" || c!="7" || c!="8" || c!="9")
// {
//	return false;
// }

 if(strCheckNum.length < 1)         //空字符串
  return false;
 else if(isNaN(strCheckNum))         //不是数值
  return false;
 else if(parseInt(strCheckNum) < 1)       //不是正数
  return false;
 else if(parseFloat(strCheckNum) > parseInt(strCheckNum)) //不是整数
  return false;

 return true;
}


//删除字符串前后的空格
function trim(str)
{
	str = str + "";
   if (str == "") return "";

    while(str.substring(0,1)==' ' || str.substring(0,1)=='	')
	{
        str=str.substring(1,str.length);
	}
    while(str.substring(str.length-1,str.length)==' ' || str.substring(str.length-1,str.length)=='	')
	{
		str=str.substring(0,str.length-1);
	}
    return str;
}

//返回值：intYear年intMonth月的天数
function fnComputerDay(intYear,intMonth)
{
    var dtmDate = new Date(intYear,intMonth,-1);
    var intDay = dtmDate.getDate() + 1;

    return intDay;
}

//判断year month,day是否符合日期条件
 function DateIf(year,month,day)
 {
   /* var year=form2.year1.value;
    var month=form2.month1.value;
    var day=form2.day1.value; */
    if (month==2)
      {
        if((year%4==0 && year%100!=0) || (year%400==0))
          {
            if(day>29)
              {
		        return false;
              }
         }
       else
         {
            if(day>28)
              {
		         return false;
               }
         }
      }
    if((month==4)||(month==6)||(month==9)||(month==11))
      {
         if(day>30)
	         {
		         return false;
	          }
      }
	  return true;
 }


 //判断email是否合法

function isEmail(email)
{
	emailerr=0
	for (i=0; i<email.length; i++)
		{
		if ((email.charAt(i) == "@") & (email.length > 5))
			{
			  emailerr=emailerr+1
			}
		}
	if (emailerr != 1)
		{
		 return false;
		}
	return true;
}

//**********************************************************************************************************
//功能：日期检查函数，支持3种年、月、日之间的分隔符 "-"、"."和"/"可以选择年、月、日是否应该完整。
//  正确的日期格式为：2001-2-13 2001 2001-2 2001.2.13  2001.2 2001/2/3，日期范围为 1-1-1 到 9999-12-31
//  同时，对当前年当前月的天数也做了判断，如：2001-2-29 2001-4-31 都是非法的日期
//参数：strDate ---- 需要判断的日期字符串
//  intFlag: 1 ---- 可以没有日  2 ---- 可以没有日和月 0 ---- 年月日必须齐全
//返回值：true ---- 日期合法 false ---- 日期不合法
function fnCheckDate(strDate,intFlag)
{
 var strCheckDate = strDate + "";     //进一步确认哪来判断的肯定是一串字符串

 if(strCheckDate == "")        //空字符串,不是合法的日期字符串，返回false
 {
  return false;
 }

 //判断传进来的数据是那种格式写成日期
 var intIndex = -1;         //利用正则表达式，查找字符串中是否包含某个字符，没找到为-1,否则为 （0 - String.length - 1）
 var arrDate;          //分别存储年月日
 var regExpInfo = /\./;        //正则表达式，匹配第一个出现 "."的位置

 //在这里，我之所以不使用replace函数把所有的"."和"/"换成"-",然后分别存储年月日，是因为用户有可能输入 2001/3-2,就判断不出它是不合法日期了
 intIndex = strCheckDate.search(regExpInfo);   //查找是否含有 "."
 if(intIndex == - 1)         //不包含
 {
  regExpInfo = /-/;
  intIndex = strCheckDate.search(regExpInfo);

  if(intIndex == -1)
  {
   regExpInfo = /\//;       //查找是否含有 "/"
   intIndex = strCheckDate.search(regExpInfo);

   if(intIndex == -1)
   {
    arrDate = new Array(strCheckDate);  //只包含年
   }
   else
   {
    arrDate = strCheckDate.split("/");  //2001/3/7 型
   }
  }
  else
  {
   arrDate = strCheckDate.split("-");   //2001-3-7 型
  }
 }
 else
 {
  arrDate = strCheckDate.split(".");    //2001.3.7 型
 }

 if(arrDate.length > 3)        //如果分离出来的项超过3，除了年月日还有其它的，不合法日期，返回false
 {
  return false;
 }
 else if(arrDate.length > 0)
 {
  //判断年是否合法
  if(fnIsIntNum(arrDate[0]))   //是正整数
  {
   if(parseInt(arrDate[0]) < 1 || parseInt(arrDate[0]) > 9999)  //年范围为1 - 9999
   {
    return false;
   }
  }
  else
  {
   return false;     //年不是正整数，错误
  }

  //判断月是否合法
  if(arrDate.length > 1)
  {
   if(fnIsIntNum(arrDate[1]))  //是正整数
   {
    if(parseInt(arrDate[1]) < 1 || parseInt(arrDate[1]) > 12)
    {
     return false;
    }
   }
   else
   {
    return false;
   }
  }
  else //没有月
  {
   if(intFlag != 2)    //必须得有月
   {
    return false;
   }
  }

  //判断日是否合法
  if(arrDate.length > 2)
  {
   if(fnIsIntNum(arrDate[2]))  //是正整数
   {
    var intDayCount = fnComputerDay(parseInt(arrDate[0]),parseInt(arrDate[1]));
    if(intDayCount < parseInt(arrDate[2]))
    {
     return false;
    }
   }
   else
   {
    return false;
   }
  }
  else
  {
   if(intFlag == 0)    //必须得有日
   {
    return false;
   }
  }
 }
 return true;
}


//判断
//参数 obj 需要检验值的对象
//参数 par
//			0 -- 如果值不合法则改为0
//			1 -- 不校验是否小于0
//			2 -- 不能大于100
//			3 -- 不能大于1
//注：trim()函数已包含在公用函数库中
function checkvalue(obj,par)
{
	var tmpValue = trim(obj.value);
	var tmp = "";
	while(tmpValue.length>0 && tmpValue.charAt(0)=='0')
	{
		tmpValue = tmpValue.substring(1);
	}
	if(tmpValue.length > 0)
	{
		switch (par)
		{
			case 0:
				if(tmpValue == null || isNaN(tmpValue) || tmpValue < 0)
				{
					//for(var loc=0;loc<tmpValue.length;loc++)
					//{
					//	if(tmpValue.charAt(loc) >= '0' && tmpValue.charAt(loc) <= '9')
					//	{
					//		tmp += tmpValue.charAt(loc);
					//	}
					//}
					alert("输入值错误！");
					obj.focus();
					obj.select();
					return false;
				}
				else
				{
					tmp = tmpValue;
				}
				break;
			case 1:
				if(tmpValue == null || isNaN(tmpValue))
				{
					//for(var loc=0;loc<tmpValue.length;loc++)
					//{
					//	if((tmpValue.charAt(loc) >= '0' && tmpValue.charAt(loc) <= '9') ||
					//	(loc == 0 && tmpValue.charAt(loc) == '-'))
					//	{
					//		tmp += tmpValue.charAt(loc);
					//	}
					//}
					alert("输入值错误！");
					obj.focus();
					obj.select();
					return false;
				}
				else
				{
					tmp = tmpValue;
				}
				break;
			case 2:
				if(tmpValue == null || isNaN(tmpValue) || tmpValue < 0)
				{
					//for(var loc=0;loc<tmpValue.length;loc++)
					//{
					//	if(tmpValue.charAt(loc) >= '0' && tmpValue.charAt(loc) <= '9')
					//	{
					//		tmp += tmpValue.charAt(loc);
					//	}
					//}
					alert("输入值错误！");
					obj.focus();
					obj.select();
					return false;
				}
				else
				{
					if(parseFloat(tmpValue) <= 0 || parseFloat(tmpValue) > 100)
					{
						alert("请输入(0－100]之间的数字！");
						obj.focus();
						obj.select();
						return false;
					}
				}
				break;
			case 3:
				if(tmpValue == null || isNaN(tmpValue) || tmpValue < 0)
				{
					alert("输入值错误！");
					obj.focus();
					obj.select();
					return false;
				}
				else
				{
					if(parseFloat(tmpValue) <= 0 || parseFloat(tmpValue) >= 1 || tmpValue.length > 3)
					{
						alert("请输入(0－1)之间并且小数点后不能超过2位的小数！");
						obj.focus();
						obj.select();
						return false;
					}
				}
				break;
			default:
				//if(tmp.length>1 && tmp.charAt(0)=='0')
				//{
				//	tmp = tmp.substring(1);
				//}
				break;
		}
	}
	else if(tmpValue.length == 0)
	{
		switch (par)
		{
			case 2:
			case 3:
				alert("输入值不能为0！");
				obj.focus();
				obj.select();
				return false;
		}
	}
	//obj.value = tmp;
	//return obj;
	return true;
}

// 将字符串格式化为货币格式
// par为0，自动填充0.00
// par为1，不自动填充
function formatCurrency(obj,par)
{
	var tmpValue = trim(obj.value);
	var pointIndex = tmpValue.indexOf(".");
	if(pointIndex < 0)
	{
		if(tmpValue == null || tmpValue == "" || tmpValue == ".")
		{
			switch (par)
			{
			case 0:
				tmpValue = "0.00";
				break;
			case 1:
				tmpValue = "";
				break;
			default:
				break;
			}
		}
		else
		{
			tmpValue += ".00";
		}
	}
	else if(pointIndex == 0)
	{
		if(tmpValue.length > 1)
		{
			if(tmpValue.substring(1).length > 2)
			{
				alert("小数点后不能大于两位！");
				obj.focus();
				obj.select();
				return false;
			}
			else if(tmpValue.substring(1).length == 1)
			{
				tmpValue = "0" + tmpValue + "0";
			}
			else
			{
				tmpValue = "0" + tmpValue;
			}
		}
		else
		{
			switch (par)
			{
			case 0:
				tmpValue = "0.00";
				break;
			case 1:
				tmpValue = "";
				break;
			default:
				break;
			}
		}
	}
	else
	{
		afterpoint = (tmpValue.length-1) - pointIndex;
		if(afterpoint == 0)
		{
			tmpValue += "00";
		}
		else if(afterpoint > 2)
		{
			//tmpValue = tmpValue*100;
			//tmpValue = Math.floor(tmpValue);
			//var m = tmpValue % 100;
			//tmpValue = tmpValue/100;
			//if(m == 0)
			//{
			//	tmpValue += ".00";
			//}
			alert("小数点后不能大于两位！");
			obj.focus();
			obj.select();
			return false;
		}
		else if(afterpoint == 1)
		{
			tmpValue += "0";
		}
	}
	if(tmpValue > 9999999999999.99)
	{
		alert("您输入的数字必须小于10000000000000！");
		obj.focus();
		obj.select();
		return false;
	}
	obj.value = tmpValue;
	//return obj;
	return true;
}

//function  : 检查输入的字符串是否可以转化为Date类型
//Parameters: strData:String
//return    : true: 通过检查
//			  false: 未通过检查。
function isDate(obj,empty)
{
	if(window.event.keyCode!=13 && window.event.keyCode!=0 && obj==window.event.srcElement) return true;

	var strDate=obj.value;
	var succeed=true;
	var alertStr="";

	if(strDate.length==0){
		if(empty!=null){
			alertStr+="输入值必须不为空!\n"
			succeed=false;
		}
	}
	if (strDate.length!=8 && succeed) {
				alertStr+="日期格式不正确，应为YYYYMMDD格式!\n"
				succeed=false;
	}
	var strYear = strDate.substr(0,4);
	var strMonth = strDate.substr(4,2);
	var strDay = strDate.substr(6,2);
	var objDate = new Date(strMonth+'-'+strDay+'-'+strYear);
	if (isNaN(objDate) && succeed){
				alertStr+="日期格式不正确!\n"
				succeed=false;
	}
    if(strYear*1<1900 && succeed) {
				alertStr+="日期格式不正确!\n"
				succeed=false;
	}
    if ((strYear*1 != objDate.getYear()*1)&&(strYear*1 != objDate.getYear()*1+1900) && succeed) {
				alertStr+="日期格式不正确!\n"
				succeed=false;
	}
    if (strMonth*1 != objDate.getMonth()*1+1 && succeed){
				alertStr+="日期格式不正确!\n"
				succeed=false;
	}
    if (strDay*1 != objDate.getDate()*1 && succeed) {
				alertStr+="日期格式不正确!\n"
				succeed=false;
	} //don't call getDay function.
	if(alertStr!="") {
			alert(alertStr);
			window.event.returnValue=false;
			obj.focus();
			obj.select();
	}

    return succeed;
}

//function  : 检查输入字符串是否由数字组成
//parameters: DigitString: String,待检查字符串的值
//			  totalLength: int, 数字的长度限制（不包括小数点）
//			  decimalLength: int，小数的长度限制
//            totalLength-decimalLength 整数部分的限制
//return    : false: 字符串中包含除数字外的字符
//			  true : else
function isNum(obj,minValue,maxValue,empty,totalLength, decimalLength)
{
	  if(window.event.keyCode!=13 && window.event.keyCode!=0) return true;
		var DigitString=obj.value;
		var succeed=true;
		var alertStr="";
		var havedecimalLength=true;
		var or_decimalLength=decimalLength;
		if(DigitString.length==0){
		  if(empty!=null){
				alertStr+="输入值必须不为空!\n"
				succeed=false;
		  }else{
		    return true;
		  }
		}
		if(decimalLength==null){
			decimalLength=2;
			or_decimalLength=2;
		}
		var re = "";
		//当小数部分长度为0时 将小数部分设为null
		if(decimalLength=="0"){
			decimalLength=null;
			or_decimalLength=0;
			}
		//当小数部分长度不为null 如果没有小数点在小数点后加00
		if(decimalLength!=null){if(DigitString.indexOf(".")<0) DigitString+=".00";}
    if (decimalLength!=null)
    {   //小数不为空
        re = re+"\\.[\\d]{1,"+ decimalLength +"}"
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
        {   //当小数部分不为空判断，整数的位数和小数的位数是否正确
            intergerLength = totalLength-decimalLength;
        }
        //re="[\\d]{0,"+ intergerLength +"}"+re;
		re="[\\d]{1,"+ intergerLength +"}"+re;//不允许从小数点开始
    }
    re = new RegExp("^(-){0,1}"+re+"$","g");//郭现华 modify 2003/11/11 可以判断负数。

    //当字符串为空、位数不对、不能匹配成数字时
		//var meetRequest=true;
    if(re.exec(DigitString) == null)
    {
			  alertStr+="输入域的值必须为数字!\n"
				if(totalLength!=null) alertStr+="数字的总长度为"+totalLength+"位!\n";
				if(havedecimalLength) {
					alertStr+="小数点后的长度为"+or_decimalLength+"位!\n";
				}
        succeed=false;
    }
		//当指定字符串范围时 字符串不在指定范围内
		if(maxValue!=null){
			if(parseFloat(DigitString)>parseFloat(maxValue)){
				alertStr+="数字不大于"+maxValue+"\n";
				succeed= false;
			}
		}
		if(minValue!=null){
			if(parseFloat(DigitString)<parseFloat(minValue)){
				alertStr+="数字不小于"+minValue+"\n";
				succeed= false;
			}
		}
		if(alertStr!="") {
			alert(alertStr);
			window.event.returnValue=false;
			obj.focus();
			obj.select();
		}

    return succeed;
}

function getCellObject(){
   	var id = trim(window.event.srcElement.id + "");
  	var obj = document.getElementById(id);
  	return obj;
}

/**
 * 格式化输入数据为小数点后两位
 * 
 * @param 文本框
 */
function formate(obj){
	if(obj.value==""||obj.value==null){
		return false;	
	}else{
		var temp = trim(obj.value+"");
		if(temp.indexOf(".")!=-1){
			var len=temp.indexOf(".")+1;
			if(temp.length-len==1)
			temp = temp+"0";
		}else{
			temp=temp+".00";
		}
		obj.value=temp;	
	}		
}

/**
 * 将录入的数字格试化为百分数形式
 *     如：25 --> 25%
 * @param 文本框
 */
function formateParcent(obj)
{
	var temp = obj.value;
	//alert("temp = " + temp);
	if(parseFloat(temp) * 1 != 0)
	{
		temp = temp + "%";
	}
	obj.value=temp;			
}

/**
 * 将小数格式为百分数形式
 *     如：0.25 --> 25%
 * @param 文本框
 */
function formate2Parcent(obj, value)
{
	//alert("value = " + value);
	//alert("value * 100 = " + parseFloat(value*100).toFixed(2));
	var temp = parseFloat(value*100).toFixed(2);
	//alert("temp = " + temp);
	if(parseFloat(temp) * 1 != 0)
	{
		temp = temp + "%";
	}
	obj.value=temp;			
}

/**
 * 格式化百分数据为小数
 * 
 * @param 文本框
 */
function formateParcent2Num(obj)
{
	//alert("formateParcent2Num");
	var temp = obj.value;
	//alert("obj.value = " + obj.value);
	
	//alert("temp = " + temp);
	if(parseFloat(temp) * 1 != 0)
	{
		temp = temp.substring(0, (temp.length-1));
		temp = parseFloat(parseFloat(temp)/100);
	}
	else
	{
		temp = 0;
	}
	return temp;
	//obj.value=temp;			
}

var confirmSave = "是否保存申报资料?";
var confirmSaveZero = "数据中有０数据，是否要保存申报资料？";
var confirmDelete = "是否删除申报资料?";
var qysdsconfirmDelete = "该操作将删除企业基本财务指标情况表，如果存在事业单位、社会团体收入申报表等相关数据也将同时删除！\n确认删除申报资料?";
var confirmReturn = "是否返回?";