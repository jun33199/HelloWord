//function  : 判断按键是否为空
//Parameters: object, 必填域对象
//return    : false: 未填值
//			  true : 有值
function checkNull(object){
  if(object.value==null || object.value==""){
      return false
  }
  return true
}

//function  : 判断按键是否为数字键
//Parameters: keyCode: 按键的keyCode值
//return    : false: 是数字键
//			  true : false
function isDigitKey(keyCode)
{
    //在onkeypress和onkeydown事件中，同一键的keyCode可能不同,这里响应onkeydown事件
    if ((keyCode >= 48)&&(keyCode <= 57)) return true;
    if ((keyCode >= 96)&&(keyCode <=105)) return true;//小键盘
    return false;
}

//function  : 判断按键是否为英文字符'a'-'z','A'-'Z'。
//Parameters: keyCode: 按键的keyCode值
//return    : false：不是英文字符
//			  true:  是
function isCharKey(keyCode)
{
    //在onkeypress和onkeydown事件中，同一键的keyCode可能不同,这里响应onkeydown事件
    if ((keyCode >= 65)&&(keyCode <= 90)) return true;
    return false;
}
//function  : 检查输入字符串是否由数字组成
function isNumber(numString)
{
    for(var i=0;i<numString.length;i++)
    {
      if(!isDigitKey(numString.charAt(i)))
            return false;
    }
    return true;
}
//function  : 检查输入字符串是否由数字组成
//parameters: DigitString: String,待检查字符串的值
//			  totalLength: int, 数字的长度限制（不包括小数点）
//			  decimalLength: int，小数的长度限制
//            totalLength-decimalLength 整数部分的限制
//return    : false: 字符串中包含除数字外的字符
//			  true : else
/*
function isDigitString(DigitString, totalLength, decimalLength)
{
    var dotNum = 0;
    var total = 0;
    for(var i=0;i<DigitString.length;i++)
    {
        if (DigitString.charAt(i)==".") {
            //计算小数点个数
            ++dotNum;
            if (dotNum>1) {
                //多于一个小书点有错误
                return false;
            }
        } else {
            if (isNaN(DigitString.charAt(i)*1)) {
                //判断是否是数字
                return false;
            }
            //计算数字全长，不包括小数点
            total++;
        }
    }
   
    //整数不能有小数点
    if(decimalLength==null && dotNum>0){
        return false;
    }
    //判断数字长度是否小雨规定值
    if (totalLength!=null && total>totalLength){ //未定义总长度的不作判断
        return false;
    }
    //判断小数位数是否小于规定值
    if (decimalLength!=null) { //未定义小数长度的不作判断
        var index = DigitString.indexOf(".");
        if (index>-1) {
            if (DigitString.substr(index+1).length>decimalLength
                || index>(totalLength-decimalLength)) {//整数部分的限制
                return false;
            }
        }else if(total>totalLength-decimalLength) {//没有小数点时的浮点数整数部分
            return false;
        }
    }
}
*/
//function  : 检查输入字符串是否由数字组成
//parameters: DigitString: String,待检查字符串的值
//			  totalLength: int, 数字的长度限制（不包括小数点）
//			  decimalLength: int，小数的长度限制
//            totalLength-decimalLength 整数部分的限制
//return    : false: 字符串中包含除数字外的字符
//			  true : else
function isDigitString(DigitString, totalLength, decimalLength)
{
	intergerLength = totalLength - decimalLength;
	var re="^[0123456789]{1," + intergerLength +"}.{0,1}[0123456789]{0,"+ decimalLength +"}$";
	re = new RegExp(re,"i");
	
	//只有在数字不为空时才进行判断
	if(re.exec(DigitString) == null && DigitString != null && DigitString != "")
	{
		return false;	
	}
	return true;
}
//function  : 检查输入的身份证号
//Parameters: IdentityCardNumber:string,待检查的身份证号;
//return    : returncode = 0: 通过检查
//			  returncode != 0: 未通过检查。
function checkIdentityCard(IdentityCardNumber)
{
    // length of IdentityCardNumber
    if ((IdentityCardNumber.length != 15)&&(IdentityCardNumber.length != 18)) return -1;
    //the first char cannot be "0"
    if(IdentityCardNumber.charAt(0)=="0")   return -1;
    //Is the IdentityCardNumber string composed of digit char(last char may be "x" or "X")
    var lastChar=IdentityCardNumber.charAt(IdentityCardNumber.length-1);
    var tempString=IdentityCardNumber;
    if(tempString.length==18 && (lastChar=="x"||lastChar=="X"))
    {
     tempString=IdentityCardNumber.substr(0,IdentityCardNumber.length-1);
    }
    if (!isDigitString(tempString))
    {
        return 1;
    }
    //检查出生日期是否符合格式要求 15位：YYMMDD  18位:YYYYMMDD
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
    if (!isDate(strIdentityCard)) return 1;
    return 0;
}
/*
//function  : 检查输入的email地址是否包含'@'和'.'
//Parameters: EmailAddress:String
//return    : returncode = 0: 通过检查
//			  returncode != 0: 未通过检查
function checkEmailAddress(EmailAddress)
{
    var returncode = 0;
    //EmailString中有'@'?
    if (EmailAddress.search("@")==-1) returncode=returncode+1;
    //if (EmailAddress.search(".")==-1) returncode=returncode+2;
    //EmailString中有'.'?
//	if (EmailAddress.search("@")>EmailAddress.search(".")) returncode=returncode+4;
    //add other check
    return returncode;
}*/
//modify by zhangpeng
//function	:匹配是否符合 xx@xx.xx 格式,xx不能包含[~!@#$%^&*().,]这些字符
//Parameters: EmailAddress:String
//return    : returncode = 0: 通过检查
//			  returncode != 0: 未通过检查
function checkEmailAddress(EmailAddress)
{
	var re=/^[^~!@#$%^&*().,]{1,30}@[^~!@#$%^&*().,]{1,20}.[^~!@#$%^&*().,]{1,10}$/i;
	
	if(re.exec(EmailAddress) == null)
	{
		return 1;	
	}
	return 0;
}
//function  : 检查输入的电话号码是否有数字和'-'组成
//Parameters: PhoneNumber:String
//return    : returncode = 0: 通过检查
//			  returncode != 0: 未通过检查。
function checkPhoneNumber(PhoneNumber)
{
    if (PhoneNumber==null || trimString(PhoneNumber).length==0) {
        return 0;
    }
	var strValue=trimString(PhoneNumber);
    if (strValue.length>0 && strValue.length<4) {
    	return 3;
    }
    if (strValue.length>0 ) {
        if (strValue.charAt(0)=='-' || strValue.charAt(strValue.length-1)=='-') {
            return 1
        }
        if (strValue.indexOf('--')>=0) {
            return 2;
        }
    }
    var returncode = 0;
    for(var i=0;i<strValue.length;i++)
    {
    	if (isAllCharValid(strValue,"1234567890-.ABCDEFGHIJKLMNOPQRSTUVWXYZ()abcdefghijklmnopqrstuvwxyz")==false) {
    		return 4;
    	}
    }
    return returncode;
}

//function  : 检查输入的移动电话号码
//Parameters: MobilePhoneNumber:String
//return    : returncode = 0: 通过检查
//			  returncode != 0: 未通过检查。
function checkMobilePhoneNumber(MobilePhoneNumber)
{
    var returncode = 0;
    if (trimString(MobilePhoneNumber).length!=11) {
    	return 1;
	}
    if (isAllCharValid(MobilePhoneNumber,"1234567890")==false) {
    	return 2;
    }
    return 0;
}

// 检查邮编，长度=6，都是数字
function checkPostCode(strValue) 
{
	if (trimString(strValue).length!=6) {
		return 1;
	}
	if (isAllCharValid(strValue,"1234567890")==false) {
		return 2;
	}
	return 0;
}

//function  : 检查输入的字符串是否可以转化为Date类型
//Parameters: strData:String
//return    : true: 通过检查
//			  false: 未通过检查。
function isDate(strDate)
{
	if (strDate.length!=10) return false;
	var strYear = strDate.substr(0,4);
	var strSep1 = strDate.substr(4,1);
	var strMonth = strDate.substr(5,2);
	var strSep2 = strDate.substr(7,1);
	var strDay = strDate.substr(8,2);
	var objDate = new Date(strMonth+strSep1+strDay+strSep2+strYear);
	if (isNaN(objDate)) return false;
	if(strYear*1<1900) return false;
	if ((strYear*1 != objDate.getYear()*1)&&(strYear*1 != objDate.getYear()*1+1900)) return false;
	if(strSep1!="-") return false;
	if (strMonth*1 != objDate.getMonth()*1+1) return false;
	if(strSep2!="-") return false;
	if (strDay*1 != objDate.getDate()*1) return false; //don't call getDay function.
	return true;
}

//function  : 自动要求用户输入正确的日期，格式为:YYYY-MM-DD
//Parameters: strData:String
//return    : true: 通过检查
//			  false: 未通过检查。
function generateDate(strDate)
{
    var strTest = strDate;
    if (strTest.length >=10)
    {
        strTest =strTest.substr(0,10);
        if (isDate(strTest)) return strTest;
        strTest = strTest.substr(0,7);

    }
    if (strTest.length == 7)
    {
        if (isDate(strTest + "-01")) return strTest + "-";
        strTest = strTest.substr(0,4);
    }
    if (strTest.length == 4)
    {
        if (isDate(strTest + "-01-01")) return strTest + "-";
        strTest = "";
    }
    return strTest;
}

//获取字符串的存储长度
//对于中文字符，每一个长度=2
//对于非中文Ascii2字符，每一个长度=1
function getStringLength(strValue) {
    if (strValue==null || strValue=="undefined") {
        return 0;
    }
    var strLength = 0;
    for (var i=0;i<strValue.length;i++) {
        var code = strValue.charCodeAt(i);
//		if (code*1>16677215) { //判断字节长为4的
//			strLength += 4;
//		} else if (code*1>65535) { //判断字节长度为3的
//			strLength += 3;
//		} else
        if (code*1>255) { //判断双字节字符
            strLength += 2;
        } else { //普通的ASCII字符
            strLength += 1;
        }
    }
    return strLength;
}

//判断是否是中文字符
//parameter strValue 字符串类型
//return true，有中文字符；false，没有中文字符
function hasChineseChar(strValue) {
    if (strValue==null) {
        return false;
    }
    for (var i=0;i<strValue.length;i++) {
        var code = strValue.charCodeAt(i);
        if (code*1>255) {
            return true;
        }
    }
    return false;
}

//判断是否全部是中文字符
//parameter strValue 字符串类型
//return true，全部是中文字符；false，包含非中文纪录
function isAllChineseChar(strValue) {
    if (strValue==null) {
        return false;
    }
    for (var i=0;i<strValue.length;i++) {
        var code = strValue.charCodeAt(i);
        if (code*1<=255) {
            return false;
        }
    }
    return true;
}

//检查字符串是不是合法的名称
//名称可以包含中文字符和英文字母
function checkName(qymcStr)
{
   var englishStr="abcdefghijklmnopqrstuvwxyz. ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for(var i=0;i<qymcStr.length;i++)
    {
        var tmpCode = qymcStr.charCodeAt(i);
        var tmpChar = qymcStr.charAt(i);
        if(tmpCode>255)
        {
            continue;
        }
        else if(englishStr.indexOf(tmpChar)==-1)
        {
          return false;
        }
    }
    return true;
}
//判断字符串中所有的字符是否都是有效自负
//strValue 被检查的字符串(Required)
//charList 指定的有效字符集合(Required)
function isAllCharValid(strValue, charList) {
//    if (strValue==null) {
//        return false;
//    }
//    if (charList==null) {
//        return false;
//    }
    for (var i=0;i<strValue.length;i++) {
        var char = strValue.charAt(i);
        if (charList.indexOf(char)<0) {
            return false;
        }
    }
    return true;
}

//判断字符串中是否包含指定的字符，
//此方法用于检查字符串中是否包含有被限制输入的字符
// strValue 被检查的字符串 (Required)
// charList 指定的非法输入的字符集合 (Required)
function hasIllegalChar(strValue,charList) {
//    if (charList==null) {
//        return false;
//    }
//    if (strValue==null) {
//        return false;
//    }
    for (var i=0;i<strValue.length;i++) {
        var char = strValue.charAt(i);
        if (charList.indexOf(char)>-1) {
            return true;
        }
    }
    return false;
}



// 去掉字符串前后的空格。
// @parameter strValue 需要处理的字符串。
// @return String 前后空格和制表符都去掉
function trimString(strValue) {
    if (strValue==null) {
        return null;
    }
    var returnValue = strValue;

    //删除字符串前面的空格(=32=0x20)和制表符(=09=0x09)
    //以及中文字符的空格(=41377=0xA1A1)
    while (returnValue.length>0) {
        var code = returnValue.charCodeAt(0);
        if (code==32 || code==9 || code==41377) {
            returnValue = returnValue.substr(1);
        } else {
            break;
        }
    }

    //删除字符串后面的空格(SPACE=20)和制表符(TAB=09)
    //以及中文字符的空格(=41377=0xA1A1)
    while (returnValue.length>0) {
        var index = returnValue.length-1;
        var code = returnValue.charCodeAt(index);
        if (code==32 || code==9 || code==41377) {
            returnValue = returnValue.substr(0,index);
        } else {
            break;
        }
    }

    return returnValue;
}

//从currentNode节电开始，遍历所有的父节点查找标记名称为tagName的节点。
function findNodeInParents(tagName,currentNode) {
    var parentNode = currentNode.getParentNode();
    if (parentNode==null) {
        return null;
    }
    if (parentNode.tagName==tagName){
        return node;
    }
    return findNodeInParents(tagName,parentNode);
}

//从currentNode节电开始，遍历所有的子孙节点查找标记名称为tagName的节点。
function findNodeInChildren(tagName,currentNode) {
    for (var i=0;i<currentNode.childNodes.length;i++) {
        var childNode = currentNode.childNodes(i);
        if (childNode.tagName == tagName) {
            return childNode;
        } else {
            var node =findNodeInChildren(tagName,childNode);
            if (node!=null) {
                return node;
            }
        }
    }
    return null;
}

function checkPersonName_CN(strValue) {
   var englishStr="abcdefghijklmnopqrstuvwxyz. ABCDEFGHIJKLMNOPQRSTUVWXYZ·";
    for(var i=0;i<strValue.length;i++)
    {
        var tmpCode = strValue.charCodeAt(i);
        var tmpChar = strValue.charAt(i);
        if(tmpCode>255)
        {	//允许中文
            continue;
        }
        else if(englishStr.indexOf(tmpChar)<0)
        {	//不是中文，且不在以上字符范围内
          return false;
        }
    }
    return true;
}

function checkPersonName_EN(strValue) {
    return isAllCharValid(strValue,"abcdefghijklmnopqrstuvwxyz. ABCDEFGHIJKLMNOPQRSTUVWXYZ");
}

function checkBankAccountNumber(strValue) {
	return !hasChineseChar(strValue);
    //return isAllCharValid(strValue,"1234567890 *-ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
}

function checkCompanyName_CN(strValue) {
   var englishStr="abcdefghijklmnopqrstuvwxyz. ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    for(var i=0;i<strValue.length;i++)
    {
        var tmpCode = strValue.charCodeAt(i);
        var tmpChar = strValue.charAt(i);
        if(tmpCode>255)
        {
            continue;
        }
        else if(englishStr.indexOf(tmpChar)<0)
        {
          return false;
        }
    }
    return true;
}

function checkCompanyName_EN(strValue) {
    return isAllCharValid(strValue,"abcdefghijklmnopqrstuvwxyz. ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890");
}

//function: 检查是否是百分数
function checkPercentNumber(strValue) {
    if (strValue=="."){
      return false;   
    }
	if (isAllCharValid(trimString(strValue),"1234567890.")==false) {
		return false;
	}
	if (strValue*1>100 || strValue*1<0) {
		return false;
	}
	return true;
}

//function  : 判断按键是否为数字键
function isDigitKey()
{

    //在onkeypress和onkeydown事件中，同一键的keyCode可能不同,这里响应onkeydown事件
    if(event.shiftKey) return  false;
    if ((event.keyCode >= 48)&&(event.keyCode <= 57)) return  true;
    if ((event.keyCode >= 96)&&(event.keyCode <=105))  return  true;//小键盘
    return false;
}
//判断按键是否为数字键
//typeStr ="flaot"表示检查是否是浮点数
//允许输入回退键
function isNumberKey(typeStr)
{
    if(isDigitKey()) return true;
    if (typeStr!=null&&typeStr=="float"&&event.keyCode == 190)return true;//.
    if (event.keyCode == 8)return true;//Backspace
    return false;
}

//function  : 判断按键是否为英文字符'a'-'z','A'-'Z'。
function isEnglishLetterKey()
{
    //在onkeypress和onkeydown事件中，同一键的keyCode可能不同,这里响应onkeydown事件
    if ((event.keyCode >= 65)&&(event.keyCode <= 90)) return true;
    return false;
}
//判断按键是否特殊字符
//允许输入回退键
function isSpecialKey()
{
    //"!@#$%^&*()"
    if(event.shiftKey&&(event.keyCode >= 48)&&(event.keyCode <= 57)) return true;

    //"`-=[]\;',./"
    if ((event.keyCode >= 186)&&(event.keyCode <= 192)) return true;
    if ((event.keyCode >= 219)&&(event.keyCode <= 222)) return true;
    if ((event.keyCode >= 106)&&(event.keyCode <= 107)) return true;
    if ((event.keyCode >= 109)&&(event.keyCode <= 111)) return true;
    return false;
}
//判断按键是否是组成中文名称的合法字符
//可以输入回退键
function isChineseNameChar()
{
    if (event.keyCode == 190) return  true;//.
    if (event.keyCode == 8) return  true;//Backspace
    if (event.keyCode == 32) return  true;//space
    return false;
}
//判断按键是否是组成名称的合法字符
//可以使中文和英文
//可以输入回退键
function isNameChar()
{
    if(isEnglishLetterKey()) return true;
    if (event.keyCode == 190) return  true;//.
    if (event.keyCode == 8) return  true;//Backspace
    if (event.keyCode == 32) return  true;//space
    return false;
}
//判断按键是否是组成时间的合法字符
//可以使数字和"-"
//可以输入回退键
function isStadardDateChar()
{
    if(isDigitKey()||event.keyCode==189) return true;//"-"
    if (event.keyCode == 8) return  true;//Backspace
    if (event.keyCode == 32) return  true;//space
    return false;
}
//判断按键是否是组成电话号码的合法字符
//可以使数字和"-"
//可以输入回退键
function isStadardPhoneChar()
{
    if(isDigitKey()||event.keyCode==189) return true;//"-"
    if (event.keyCode == 8) return  true;//Backspace
    if (event.keyCode == 32) return  true;//space
    return false;
}
