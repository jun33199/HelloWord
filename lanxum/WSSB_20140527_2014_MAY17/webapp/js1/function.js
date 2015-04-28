//存放计算公式的数组 每个页面要重新赋值
var mathArray =new Array();
//存放同名域值合计的数组 每个页面要重新赋值
var hjArray =new Array();
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
	//if(!Number(str))
	if(isNaN(Number(str)))
		return false;
	return true;
}

//判断一个数是否为正整数
//参数：strNum ---- 需要判断的字符串
//返回值：true ---- 整数 false ---- 非整数
function fnIsIntNum(strNum)
{
 var strCheckNum = strNum + "";
 if(strCheckNum.length < 1)         //空字符串
  return false;
 else if(isNaN( Number(strCheckNum) ))         //不是数值
  return false;
 else if(parseInt( Number(strCheckNum) ) < 1)       //不是正数
  return false;
 else if(parseFloat(strCheckNum) > parseInt(strCheckNum)) //不是整数
  return false;

 return true;
}


///规定好值的长度 在没有达到长度的数值前补0
function formatLength(obj,length){
	if(obj){
		if(obj.value.length==0) return;
		if(obj.value.length<length){
			for(var i=0;i<(length-obj.value.length+1);i++){
				obj.value="0"+obj.value;
			}
		}else{
			obj.value=obj.value.substring(0,length);
		}
	}
}
////////////////////////////////////////////////////////////////////////////////
function formatNumber(iNum, iDn){
	var iTmp="";
	if ( iDn == null )
		iDn = 2;
	var strNum =iNum + "";
	strNum = trim(strNum);
	if(strNum.length == 0 || iDn==0){
		return strNum;
	}
	if (isNaN(strNum) || strNum.length == 0)
	{
		for (i = 0; i < iDn; i++)
			iTmp += "0"
		return "0."+iTmp;
	}

	var ifirst=0;
	for(var iN=0;iN<strNum.length-1;iN++)
	{
		if(strNum.indexOf(".")==1)
			break;
		else
		{
			if(strNum.charAt(iN)=="0") ifirst+=1;
			else break;
		}
	}
	strNum=strNum.substring(ifirst);
	if(strNum.indexOf(".")==0) strNum="0"+strNum;
	if(strNum.indexOf("-.")==0) strNum="-0."+strNum.substring(2);

	var iDi = strNum.indexOf(".",0);
	if(iDi < 0)
	{
		for (i = 0; i < iDn; i++)
			iTmp += "0"
		strNum  += "."+iTmp;
		return strNum;
	}
	var iDiLength = strNum.length - (iDi+1);
	if (iDiLength == iDn)
		return strNum;
	if (iDiLength < iDn)
	{
		for (var iLoop=iDn; iLoop>iDiLength; iLoop--)
		{
			strNum += '0';
		}
		return strNum;
	}
	else
	{
		var iNum1 = parseFloat(strNum);
		iNum1 = (iNum1+(5/Math.pow(10,(iDn+1))))*(Math.pow (10,iDn));
		iNum1 = Math.floor(iNum1);
		iNum1 = iNum1/Math.pow (10,iDn);
		strNum = new String(iNum1);
		var iDi = strNum.indexOf(".",0);
		if(iDi < 0)
		{
			for (i = 0; i < iDn; i++)
				iTmp += "0"
			strNum  += "."+iTmp;
			return strNum;
		}
		var iDiLength = strNum.length - (iDi+1);
		if (iDiLength == iDn){
			return strNum;}
		if (iDiLength < iDn)
		{
			for (var iLoop=iDn; iLoop>iDiLength; iLoop--)
			{
				strNum += '0';
			}
			return strNum;
		}
	}
}
function  trim(strInput)
{
	var iLoop  = 0;
	var iLoop2 = -1;
	var strChr;
	if((strInput == null)||(strInput == "<NULL>")) return "";
	if(strInput)
	{
		for(iLoop=0;iLoop<strInput.length-1;iLoop++)
		{
			strChr=strInput.charAt(iLoop);
			if(strChr!=' ' && strChr!='　')//英文的空格和汉字的空格
				break;
		}
		for(iLoop2=strInput.length-1;iLoop2>=0;iLoop2--)
		{
			strChr=strInput.charAt(iLoop2);
			if(strChr!=' ' && strChr!='　')//英文的空格和汉字的空格
				break;
		}
	}

	if(iLoop<=iLoop2)
	{
		return strInput.substring(iLoop,iLoop2+1);
	}
	else
	{
		return "";
	}
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

/*
* Notes: 根据表格单元，及提供的公式计算数据
* Version: 0.9.50
* Author: GuoXH
* LastModDate: 2003/10/29
* Parames:
* 		obj 表格单元
* 		strItem 计算公式中的第一个被计算的字符串，即等号后的字符串
* 		kind null时，按年报表计算；不为null时，根据Row计算
*/
function computeFunction(obj,strItem,kind){

	if(kind){//根据Row计算
		//调用smsb_common.js，获取表格行对象
		var oRow = getObjRow(obj);

		var stack = new Stack();
		//循环MathString数组,以找到匹配的公式
		for(var i=0;i<mathArray.length;i++)
		{
			if(mathArray[i].isInExpress(strItem))
			{
			  //计算
			  var retBolan = mathArray[i].compute(oRow);
			  if(retBolan != null)
			  {
				  var id = retBolan.operator+"";
				  var v = retBolan.value;
				  //赋值
				  setRowValue(oRow,id,v);
				  stack.push(retBolan.operator);
			  }
			}
		}//end [for]
	}else{//null时，按年报表计算
		var stack = new Stack();
		var strmyid = "" + strItem;
		var hc = strmyid;
		for(var i=0;i<mathArray.length;i++)
		{
		  if(mathArray[i].isInExpress(hc))
		  {
			  var retBolan = mathArray[i].compute();
			  if(retBolan != null)
			  {
				  var id = retBolan.operator+"";
				  var v = retBolan.value;
				  setValue(id, v);
				  stack.push(retBolan.operator);
			  }
		  }
		}
		//2003-10-29 增加一下代码，以便遍历公式
		while(stack.length() > 0){
		  var nHc = stack.pop();
		  computeFunction(obj,nHc,kind);
		}
	}//end [if-else][null]
}

/*
 *计算合计 放方法
 *
 */
function hj(){
//	alert('enter')
	for(var i=0;i<hjArray.length;i++){
			switch(hjArray[i].length){
				case 2:
				computeSameSum(hjArray[i][0],hjArray[i][1])
				break;
				case 3:
				computeSameSum(hjArray[i][0],hjArray[i][1],hjArray[i][2]);
				break;
			}
	}
}
/*
* Notes: 计算同一类表格单元合计，要求有相同的ID or Name
* Version: 0.9.00
* Author: GuoXH
* LastModDate: 2003/10/16
* Parames:
* 		addFrmItem 被计算的文本框的ID or Name
* 		resultFrmItem 结果的ID or Name
*     tableId 表格的ID 不填 会计算整个页面上的所有同名域值
*/
function computeSameSum(addFrmItem,resultFrmItem,tableId){

//alert("addFrmItem :"+addFrmItem +"resultFrmItem :"+resultFrmItem+"tableId :"+tableId )
    //表格对象
	var oTab=document;
	if(tableId!=null)
		{
		oTab=eval("document.all('"+tableId+"')");
		}

	//被计算的表格单元对象
	var addItem = eval("oTab.all('"+addFrmItem+"')");
	//被计算的表格单元的行数
	var length = getLength(addItem);
	//结果
	var result_sum = 0;
    if (length != null)
    {
		if(length == -1){
			result_sum = parseFloatPro(addItem.value);
		}else{
			if(length == 1)
			{
				result_sum = parseFloatPro(addItem[0].value);
			}
			else
			{
				// 有多行
			   for (var i=addItem.length-1; i>=0; i--)
			   {
					result_sum += parseFloatPro(addItem[i].value);
			   }
			}
		}

    }
	//赋值
    eval("oTab.all('"+resultFrmItem+"')").value=formatNumber(result_sum);
		if(oTab.all.xthjdx){
			oTab.all.xthjdx.innerText=getChineseMoney(formatNumber(result_sum));
		}
}
function parseFloatPro(value){
	if(isNaN(parseFloat(value))) return 0;
	return parseFloat(value)
}

/*
* LastDate: 2003/10/17
*/
function getLength(obj)
{
    var kssl = obj;

    if (kssl == null)
    {
        return null;
    }

    if (kssl.length == null)
    {
        // 只有一行
        return -1;
    }
    else
    {
        // 只有一行，是数组
        if(kssl.length == 1)
        {
            return 1;
        }

        return kssl.length;
    }
}


/*
* Notes: 给结果赋值
* Version: 0.9.00
* LastDate: 2003/10/16
*/
function setRowValue(oRow, hc, value)
{
	var o = oRow.all(hc);

    eval(o).value = round(value);
}
function setValue(hc, value)
{
    var o = document.forms[0].name + "." + hc;

    eval(o).value = round(value);
}

function getValue(hc)
{
    var o = document.forms[0].name + "." + hc;
    var obj = eval(o);
    if (obj.value == "")
    {
        return "0.0";
    }
    return obj.value;
}

function getNumber(obj){
    var v = trim(obj.value);
    if(v == ""){
    	return 0;
    }else{
    	return parseFloat(v);
    }
}
/*
* Notes: 数值最多保留两位
*/
function round(value){
    var temp = value * 100;
    temp = Math.round(temp);
    return temp/100;
}
/*
* 转换阿拉伯数字金钱到中文金钱数
*/
var NUM = ["零","壹","贰","叁","肆","伍","陆","柒","捌","玖"];

function getChineseMoney(num1,unit){
	if(unit!=null ) num1 = (String)(num1*unit);
	var num = (String)(num1*1);
	var flag = num.indexOf(".");
	var integerStr = "";
	var decimalStr = "";
	if(flag>0){
		integerStr = num.substring(0,flag);
		decimalStr = num.substring(flag+1);
	}else if(flag==0){
		decimalStr = num.substring(1);
	}else{
		integerStr = num;
	}//end if
	return getChineseInteger(integerStr) + getChineseDecimal(decimalStr);
}

function getChineseInteger(integerStr) {
	/**整数部分的单位*/
	var chineseInteger = "";
	var IUNIT = ["元","拾","佰","仟","万","拾","佰","仟","亿","拾","佰","仟","万","拾","佰","仟"];
	var isMust5 = getIsMust5(integerStr);
	var integerArray = num2Array(integerStr);
	var ILength = integerArray.length;
	var key = "";
	for(var ii=0; ii<ILength; ii++){
		if(integerArray[ii]==0){
			//万(亿)(必填)
			if((ILength-ii)==13)	key = IUNIT[4];
			//亿(必填)
			else if((ILength-ii)==9) key = IUNIT[8];
			//万(不必填)
			else if((ILength-ii)==5 && isMust5) key = IUNIT[4];
			//元(必填)
			else if((ILength-ii)==1) key = IUNIT[0];

			//0遇非0时补零，不包含最后一位
			if((ILength-ii)>1 && integerArray[ii+1]!=0) key += NUM[0];
			//其余情况
			else key += "";
			//if(ii==5) break;
		}//end if
		chineseInteger += (integerArray[ii]==0?key:(NUM[integerArray[ii]]+IUNIT[ILength-ii-1]));
		if(integerArray[0]==0)chineseInteger=NUM[0]+chineseInteger;//add by huipeijie 2003-11-17

		key = "";
	}//end for
	return chineseInteger ;
}

function getChineseDecimal(decimalStr) {
	var DUNIT = ["角","分","厘"];
	var decimalArray = num2Array(decimalStr);
	var chineseDecimal = "";

	for(var ii=0; ii<decimalArray.length; ii++){
		//舍去3位小数之后的
		if(ii==3) break;
		chineseDecimal += (decimalArray[ii]==0?"":(NUM[decimalArray[ii]]+DUNIT[ii]));
	}//end for
	return chineseDecimal ;
}

function getIsMust5(integerStr) {
	//取得从低位数，第5到第8位的字串
	var subNum = "";
	var ILength = integerStr.length;
	if(ILength>4){
		if(ILength>8){
			subNum = integerStr.substring(ILength-8,ILength-4);
		}else{
			subNum = integerStr.substring(0,ILength-4);
		}//end if
	}else{
		return false;
	}//end if
	if(parseInt(subNum)>0) return true;
	else return false;
}

function num2Array(integerStr) {
	var temp = new Array();
	for(var ii=0; ii<integerStr.length; ii++){
		temp.push(parseInt(integerStr.substring(ii,ii+1)));
	}//end for
	return temp;
}


/**
* Notes: 获取当前日期的上一年/月/季的起止日期。
* Version: 0.9.00
* Author: Guoxh
* Parames: flag 1,Last Year;2,Last Month;默认：输出季度
*/
function getStartEndDate(oInput1,oInput2,oInput3,flag){
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
					date_start = (year-1)+"1001";
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
					date_start = year+"0401";
					date_end = year+"0630";
					break;
				case "10":
				case "11":
				case "12":
					date_start = year+"0701";
					date_end = year+"0930";
					break;
			}
		}

		oInput2.value = date_start;
		oInput3.value = date_end;
	}
}
/**
* Notes: 获取累计型季报申报所属时间的计算，季报的申报所属日期，应该是当前日期所在年的１月１日至当前日期的月的上个月的月末。
*		并且如果输入1月份的日期，它的所属日期就是上一年1月1日-12月31日
* Version: 0.9.00
* Author: Guoxh
* Parames: flag 1,累计型季报申报所属时间
*/
function getTotalDate(oInput1,oInput2,oInput3,flag){
	var date_start,date_end;
	var year,mon,days;

	var inputDate = oInput1.value;

	//是否合法日期
	if(isDate(oInput1,"v")){
		year = inputDate.substring(0,4);
		mon = inputDate.substring(4,6);
		days = inputDate.substring(6,8);

		if(flag == 1){
			if(mon=="01"){
				date_start = (year-1)+"0101";
				date_end = (year-1)+"1231";
			}else{
				var date2 = new Date(year,mon-1,-1);
				days = date2.getDate()+1;

				date_start = year+"0101";
				date_end = year+""+formatMon(mon-1)+days;
			}
		}

		oInput2.value = date_start;
		oInput3.value = date_end;
	}
}

//补足两位
function formatMon(intMonth){
	var bak = "";

	if(intMonth<10){
		bak = "0"+intMonth;
	}else{
		bak = intMonth.toString();
	}

	return bak;
}

/**
* Notes: 检查输入的身份证号
* Parameters: IdentityCardNumber:string,待检查的身份证号;
* Version: 0.9.00
*/
function checkIdentityCard(IdentityCardNumber,empty)
{
	var err = "";
	var flag = false;

	if(empty==null || empty==""){
		if(IdentityCardNumber==null || IdentityCardNumber==""){
			return true;
		}
	}else{
		if(IdentityCardNumber==null || IdentityCardNumber==""){
			err = "身份证号码不允许为空！";
			alert(err);
			return false;
		}
	}

    // length of IdentityCardNumber
    if ((IdentityCardNumber.length != 15)&&(IdentityCardNumber.length != 18)){
		flag = true;
	}else{
		var strIdentityCard = "19990101";
		if (IdentityCardNumber.length == 15)
		{
			strIdentityCard = "19" + IdentityCardNumber.substr(6,6);
		}
		if (IdentityCardNumber.length == 18)
		{
			strIdentityCard = IdentityCardNumber.substr(6,8);
		}
		if (!isFullDate(strIdentityCard,0,empty,false)){
			flag = true;
		}
	}
    //the first char cannot be "0"
    if(IdentityCardNumber.charAt(0)=="0"){
		flag = true;
	}
    //Is the IdentityCardNumber string composed of digit char(last char may be "x" or "X")
    var lastChar=IdentityCardNumber.charAt(IdentityCardNumber.length-1);
    var tempString=IdentityCardNumber;
    if(tempString.length==18 && (lastChar=="x"||lastChar=="X"))
    {
     tempString=IdentityCardNumber.substr(0,IdentityCardNumber.length-1);
    }
    if (!isDigit(tempString))
    {
        flag = true;
    }
    //检查出生日期是否符合格式要求 15位：YYMMDD  18位:YYYYMMDD
    /*
	var strIdentityCard = "1999-01-01";
    if (IdentityCardNumber.length == 15)
    {
        strIdentityCard = "19" + IdentityCardNumber.substr(6,2) + "-" + IdentityCardNumber.substr(8,2) + "-"
                + IdentityCardNumber.substr(10,2);
    }
    if (IdentityCardNumber.length == 18)
    {
        strIdentityCard = IdentityCardNumber.substr(6,4) + "-" + IdentityCardNumber.substr(10,2) + "-"
                + IdentityCardNumber.substr(12,2);
    }
	*/
	if(flag){
		err = "不是合法的身份证号码！";
		alert(err);
		return false;
	}
    return true;
}

/**
* Notes: 多方面检验日期。
* Version: 0.9.00
* Parames: strDate string 日期值
*          dateKind int 日期类型；
*                   0；8位年月日,eg:20030609
*                   1；4位年,eg:2003
*          empty string/int/null 是否允许空
*          isThrow boolean 是否扔出错误信息
**/
function isFullDate(strDate,dateKind,empty,isThrow){
	var year,mon,days;
	var err = "";

	if(dateKind==0){//检验8位年月日
		err = "";

		if(strDate.length==0){
			  if(empty!=null){
					err="日期必须不为空!\n";
				}
		}else{
			if(strDate.length!=8){
				err="日期格式不正确，必须是8位数字!\n";
			}else{
				var strYear = strDate.substr(0,4);
				var strMonth = strDate.substr(4,2);
				var strDay = strDate.substr(6,2);
				var objDate = new Date(strMonth+'-'+strDay+'-'+strYear);
				if (isNaN(objDate)){
					err="日期格式不正确!\n"
				}
				if(strYear*1<1900) {
					err="日期格式不正确!\n"
				}
				if ((strYear*1 != objDate.getYear()*1)&&(strYear*1 != objDate.getYear()*1+1900)) {
					err="日期格式不正确!\n"
				}
				if (strMonth*1 != objDate.getMonth()*1+1){
					err="日期格式不正确!\n"
				}
				if (strDay*1 != objDate.getDate()*1) {
					err="日期格式不正确!\n"
				} //don't call getDay function.
			}
		}
		/*if(err!=""){
			if(isThrow){
				alert(alertStr);
			}
			return false;
		}
		return true;*/
	}else if(dateKind==1){//4位年
		err = "";
		if((strDate.charAt(0)=="0") || strDate.length!=4 || (!isDigit(strDate))){
			err = "不是合法的年份！";
		}
		//alert(err);
	}

	if(err!=""){
		if(isThrow){
			alert(err);
		}
		return false;
	}
	return true;
}

/**
 * 用来判断小数位的位数是否大于某个指定值
 * @input text
 * @input point
 * @output text的小数位小于point返回TRUE,反之返回FALSE
 */
function check_decimal(text , point){
	var value = trim(text+"");
	var index =value.indexOf(".");
	if(index >= 0){
		var x = value.substring(index+1);
		if(x.length > point){
			return false;
		}
	}
	return true;
}


//对比两个日期，要求2大于1
function compare(name1,name2){

	var obj1=name1;
	var obj2=name2;
	if(obj1  && obj2){
		if(parseFloat(obj2.value)>parseFloat(obj1.value)){
			return 1;
		}else if(parseFloat(obj2.value)==parseFloat(obj1.value)){
			return 0;
		}else{
			obj2.select();
			return -1;
		}
	}else{
		alert("对象未找到");
	}
}

/**
* Notes: 比较起止日期(日期格式：yyyymmdd)
* Parames: item1 开始日期的名称或ID
*          item2 终止日期的名称或ID
*          kind 查找录入框的形式
*               0，用 document.all(item)
*               1，用 row.all(item)
*          obj 当前录入框对象
* Author: Guoxh
* Version: 0.9.00
*/
function compareDate(item1,item2,kind,obj)
{
    var errMsg = "";
	var oItem1,oItem2;
	var val1,val2;

	if(isDate(obj,null)){
		if(kind==0){
			oItem1 = document.all(item1);
			oItem2 = document.all(item2);
		}else{
			oRow = getObjRow(obj);
			oItem1 = oRow.all(item1);
			oItem2 = oRow.all(item2);
		}
		val1 = oItem1.value;
		val2 = oItem2.value;
		if(parseFloat(val1)>parseFloat(val2)){
			errMsg = "开始日期不应该大于终止日期!";
			alert(errMsg);
			return false;
		}else{
			return true;
		}
	}

}
//计算两个时间之间的天20031010-20031001=9
//日起格式为20031010
//s起始日期,e终止日期
function getBDays(s,e){
	//得到开始的年月日
	var sy = s.substring(0,4);
	var sm = parseInt(s.substring(4,6))-1;
	var sd = s.substring(6,8);
	//生成日期对象
	var sdate = new Date(sy,sm,sd);
	//得到终止的年月日
	var ey = e.substring(0,4);
	var em = parseInt(e.substring(4,6))-1;
	var ed = e.substring(6,8);
	//生成日期对象
	var edate = new Date(ey,em,ed);
	//得到两个日期之间的秒数
	var ms=edate.getTime()-sdate.getTime();

	//计算一天有多少秒
	var MinMilli = 1000 * 60;
	var HrMilli = MinMilli * 60;
	var DyMilli = HrMilli * 24;
	var days = Math.round(ms/DyMilli);
	return days;
}
//计算两个日期之间的月份
function getBMonths(s,e){
	//得到开始的年月
	var sy = s.substring(0,4);
	var sm = s.substring(4,6);
	//得到终止的年月日
	var ey = e.substring(0,4);
	var em = e.substring(4,6);
	//相差的年
	var ys= ey-sy;
	//相差的相对月
	//var ms= parseInt(em)-parseInt(sm);
	var ms = em - sm;
	var months=ms+ys*12;
	return months;

}

/*
* Notes: 获取指定月份的天数
* Parames:
* 		intMonth 从 1 开始
*/
function CL_getDays(intYear,intMonth){
    var dtmDate = new Date(intYear,intMonth,-1);
    var intDay = dtmDate.getDate() + 1;

    return intDay;
}
//得到该月的天数
function getDaysOfMonth(d){
	//得到开始的年月
	var y = d.substring(0,4);
	var m = d.substring(4,6);
	var intDay  = CL_getDays(y,m);
	return intDay;
}


//用于补充申报下页面税款所属日期与申报日期的关联
//item0为申报日期，item1为税款开始日期，item2为税款结束日期
//kind为 kind 查找录入框的形式0，用 document.all(item)。 1，用 row.all(item)，obj 当前录入框对象
//sklx为税款类型0：年报 1：季报 2：月报
//skssrq=0为税款开始日期，1为税款结束日期
function compareSkssrqDate(item0,item1,item2,kind,obj,sklx,skssrq)
{
    var errMsg = "";
    var oItem1,oItem2,oItem3;
    var val1,val2,val1;
    var objYear,objMon;
    var sbYear,sbMon;
    var inputDate = obj.value;
    var sbDate;
    var sbDateValue;
    var month ;
    var flag = 0;

    if(isDate(obj,null)){
		if(kind==0){
			oItem1 = document.all(item1);
			oItem2 = document.all(item2);
			sbDate = document.all(item0);
		}else{
			oRow = getObjRow(obj);
			oItem1 = oRow.all(item1);
			oItem2 = oRow.all(item2);
                        sbDate = oRow.all(item0);
                }
		val1 = oItem1.value;
		val2 = oItem2.value;
		sbDateValue = sbDate.value;
		if(parseFloat(val1)>parseFloat(val2)){
			errMsg = "开始日期不应该大于终止日期!";
			flag = 1;
		}else{
                objYear = inputDate.substring(0,4);
	        objMon = inputDate.substring(4,6);
	        sbYear = sbDateValue.substring(0,4);
                sbMon = sbDateValue.substring(4,6);
	        //年报
	        if(sklx==0){
                   //税款开始日期或是结束日期与申报日期的比较
                   var result = parseFloat(sbYear) - parseFloat(objYear);
                   //要求上一年度
                   if(result!=1)
                   {  if(skssrq==0)
                      {errMsg = "年报的税款开始日期的年份不符合年报的规则!";}
				     else
                      {errMsg = "年报的税款结束日期的年份不符合年报的规则!";}
                      flag = 1;
                    }
                   if(skssrq==0){
                    if(objMon!="01")
                    {errMsg = "年报的税款开始日期的月份不符合年报的规则!";
                     flag = 1;
                    }
                   }else
                   {
                    if(objMon!="12")
                    {errMsg = "年报的税款结束日期的月份不符合年报的规则!";
                     flag = 1;
                    }
                   }
	         }
	         //累计型季报
	         if(sklx==1){
                  var ldResult = parseFloat(sbYear) - parseFloat(objYear);
//                  var ldMonth =  parseFloat(sbMon) - parseFloat(objMon);
                  if(sbMon=="01"){
                      if(ldResult!=1)
                      {
                        if(skssrq==0)
                        {errMsg = "累计型季报的税款开始日期的年份不符合累计型季报的规则!";}
                        else
                        {errMsg = "累计型季报的税款结束日期的年份不符合累计型季报的规则!";}
                        flag = 1;
                      }
                      if(skssrq==0)
                      {
                        if(objMon!='01')
                        { errMsg = "累计型季报的税款开始日期的月份不符合累计型季报的规则!";
                          flag = 1;
                        }
                      }else{
                        if(objMon!='12')
                        { errMsg = "累计型季报的税款结束日期的月份不符合累计型季报的规则!";
                          flag = 1;
                        }
                       }
                     }else{
                       var monResult = parseFloat(sbMon) - parseFloat(objMon);
                       if(skssrq==1){
                       if(monResult!=1)
                       {
                        errMsg = "累计型季报的税款结束日期的月份不符合累计型季报规则!";
                        flag = 1;
                       }
                       }else{
                        if(objMon!="01")
                       {
                         errMsg = "累计型季报的税款开始日期的月份不符合累计型季报规则!";
                         flag = 1;
                       }
                     }
	            }
	         }
                //月报比较
                if(sklx==2){
                  var ydResult = parseFloat(sbYear) - parseFloat(objYear);
                  var ydMonth =  parseFloat(sbMon) - parseFloat(objMon);
                  //申报日期为1月份的判断
                  if(sbMon=="01"){
                     if(ydResult!=1)
                      {
                        if(skssrq==0)
					    {errMsg = "月报的税款开始日期的年份不符合月报的规则!";}
				     else
                        {errMsg = "月报的税款结束日期的年份不符合月报的规则!";}
                       flag = 1;
                      }

                     if(objMon!="12")
                      {
                        if(skssrq==0)
					    {errMsg = "月报的税款开始日期的月份不符合月报的规则!";}
				        else
                        {errMsg = "月报的税款结束日期的月份不符合月报的规则!";}
                       flag = 1;
                       }
                  }else{//其它月份的判断
                      if(ydResult!=0)
                      {
                        if(skssrq==0)
					    {errMsg = "月报的税款开始日期的年份不符合月报的规则!";}
				       else
                        {errMsg = "月报的税款结束日期的年份不符合月报的规则!";}
                       flag = 1;
                      }
                      if(ydMonth!=1)
                      {
                        if(skssrq==0)
					    {errMsg = "月报的税款开始日期的月份不符合月报的规则!";}
				        else
                        {errMsg = "月报的税款结束日期的月份不符合月报的规则!";}
                       flag = 1;
                      }
                  }
              }
                  //普通季报
                  if(sklx==3){
                  var pjbResult = parseFloat(sbYear) - parseFloat(objYear);
                  var pjbMonth =  parseFloat(sbMon) - parseFloat(objMon);
                   if(sbMon=="01"||sbMon=="02"||sbMon=="03"){
                     if(pjbResult!=1)
                      {
                        if(skssrq==0)
                        {errMsg = "普通季报的税款开始日期的年份不符合普通季报的规则!";}
				     else
                        {errMsg = "普通季报的税款结束日期的年份不符合普通季报的规则!";}
                       flag = 1;
                      }
                        if(skssrq==0)
                        { if(objMon!='10')
                          {
                           errMsg = "普通季报的税款开始日期的月份不符合普通季报的规则!";
                           flag = 1;
                          }
                        }else
                        { if(objMon!='12')
                          {
                           errMsg = "普通季报的税款结束日期的月份不符合普通季报的规则!";
                           flag = 1;
                          }
                        }

                     }
                     if(sbMon=="04"||sbMon=="05"||sbMon=="06"){
                     if(pjbResult!=0)
                      {
                        if(skssrq==0)
                        {errMsg = "普通季报的税款开始日期的年份不符合普通季报的规则!";}
				     else
                        {errMsg = "普通季报的税款结束日期的年份不符合普通季报的规则!";}
                       flag = 1;
                      }
                        if(skssrq==0)
                        { if(objMon!='01')
                          {
                           errMsg = "普通季报的税款开始日期的月份不符合普通季报的规则!";
                           flag = 1;
                          }
                        }else
                        { if(objMon!='03')
                          {
                           errMsg = "普通季报的税款结束日期的月份不符合普通季报的规则!";
                           flag = 1;
                          }
                        }

                     }
                     if(sbMon=="07"||sbMon=="08"||sbMon=="09"){
                     if(pjbResult!=0)
                      {
                        if(skssrq==0)
                        {errMsg = "普通季报的税款开始日期的年份不符合普通季报的规则!";}
				     else
                        {errMsg = "普通季报的税款结束日期的年份不符合普通季报的规则!";}
                       flag = 1;
                      }
                        if(skssrq==0)
                        { if(objMon!='04')
                          {
                           errMsg = "普通季报的税款开始日期的月份不符合普通季报的规则!";
                           flag = 1;
                          }
                        }else
                        { if(objMon!='06')
                          {
                           errMsg = "普通季报的税款结束日期的月份不符合普通季报的规则!";
                           flag = 1;
                          }
                        }

                     }
                      if(sbMon=="10"||sbMon=="11"||sbMon=="12"){
                     if(pjbResult!=0)
                      {
                        if(skssrq==0)
                        {errMsg = "普通季报的税款开始日期的年份不符合普通季报的规则!";}
				     else
                        {errMsg = "普通季报的税款结束日期的年份不符合普通季报的规则!";}
                       flag = 1;
                      }
                        if(skssrq==0)
                        { if(objMon!='07')
                          {
                           errMsg = "普通季报的税款开始日期的月份不符合普通季报的规则!";
                           flag = 1;
                          }
                        }else
                        { if(objMon!='09')
                          {
                           errMsg = "普通季报的税款结束日期的月份不符合普通季报的规则!";
                           flag = 1;
                          }
                        }

                     }
                  }
        }
  }
  //错误的情况下保持通过申报日期推算的税款所属日期范围
  if(flag==1){
  	alert(errMsg);
//        if(sklx==1)
//        {getTotalDate(document.forms[0].sbrq,document.forms[0].skssksrq,document.forms[0].skssjsrq,1);}
//        if(sklx==2)
//        {getStartEndDate(document.forms[0].sbrq,document.forms[0].skssksrq,document.forms[0].skssjsrq,2);}
//        if(sklx==0)
//        {getStartEndDate(document.forms[0].sbrq,document.forms[0].skssksrq,document.forms[0].skssjsrq,1);}
        window.event.returnValue=false;
        obj.focus();
        obj.select();
        return false;
  }


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

/**
* Notes: 比较起止日期(日期格式：yyyy-mm-dd)
* Parames: ksrq 开始日期
*          jzrq 终止日期
* Author: Guoxh
* Version: 0.9.00
*/
function checkDate(ksrq,jzrq){
		d1Arr=ksrq.split('-');
		d2Arr=jzrq.split('-');
		v1=new Date(d1Arr[0],d1Arr[1],d1Arr[2]);
		v2=new Date(d2Arr[0],d2Arr[1],d2Arr[2]);
		if(v1>v2){
			alert("起始日期不能晚于终止日期");
			return false;
		}
		return true;
	}
	
function isNotNull(value){
		
		if(value!=null && value.length>0){
			alert("isNotNull");
			return true;
		}else{
			alert("isNull");
			return false;
		}	
	}	