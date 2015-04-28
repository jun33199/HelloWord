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
//parameters: DigitString: String,待检查字符串的值
//			  totalLength: int, 数字的总长度限制
//			  decimalLength: int，小数的长度限制
//            totalLength-decimalLength 整数部分的限制
//return    : false: 字符串中包含除数字外的字符
//			  true : else
function isDigitString(DigitString, totalLength, decimalLength)
{
    var dotNum = 0;
    var total = 0;
    
    //如果为一个小数点，则返回false
    if(trimString(DigitString)==".")
      return false;
    
    for(i=0;i<DigitString.length;i++)
    {
        if (DigitString.charAt(i)==".") {
            //计算小数点个数
            ++dotNum;
            if (dotNum>1) {
                //多于一个小数点有错误
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
    //判断数字长度是否小于规定值
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
    return true;
}
//function  : 检查输入的身份证号
//Parameters: IdentityCardNumber:string,待检查的身份证号;
//return    : returncode = 0: 通过检查
//			  returncode != 0: 未通过检查。
function checkIdentityCard(IdentityCardNumber)
{
	// Is the IdentityCardNumber string composed of digit char?
	var returncode = 0;
	
        //前十七个字符必须为数字
	if(IdentityCardNumber.length == 18 && !isDigitString(IdentityCardNumber)&&(!isDigitString(IdentityCardNumber.substr(0,17))||IdentityCardNumber.charAt(17)!='x'&& IdentityCardNumber.charAt(17)!='X'))
	  returncode = returncode + 8;
	  
	if (IdentityCardNumber.length == 15 && !isDigitString(IdentityCardNumber)) returncode=returncode + 1;
	
	// length of IdentityCardNumber
	if ((IdentityCardNumber.length != 15)&&(IdentityCardNumber.length != 18)) returncode=returncode + 2;
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
	if (!isDate(strIdentityCard)||strIdentityCard.substr(0,4)*1<"1900"*1) returncode = returncode + 4;
	return returncode;
}

//function  : 检查输入的email地址是否包含'@'和'.'
//Parameters: EmailAddress:String
//return    : returncode = 0: 通过检查
//			  returncode != 0: 未通过检查
function checkEmailAddress(EmailAddress)
{
	var returncode = 0;
	//EmailString中有'@'?
	if(EmailAddress.indexOf("@")<=0) returncode=1;
	
	//EmailString中有'.'?
	if (EmailAddress.indexOf(".")==-1) returncode=1;
	//EmailString中"@"应在"."前
	if(returncode==0)
	{
	    var strLength=EmailAddress.length;
	    var strStation=EmailAddress.indexOf("@");
	    if (EmailAddress.substr(strStation,strLength-strStation).indexOf(".")==-1) 
	        returncode=1;
	}        
	//add other check
	return returncode;
}

//function  : 检查输入的电话号码是否有数字和'-'组成
//Parameters: PhoneNumber:String
//return    : returncode = 0: 通过检查
//			  returncode != 0: 未通过检查。
function checkPhoneNumber(PhoneNumber)
{
	var returncode = 0;
	if(PhoneNumber.length>30 || PhoneNumber.length<3)
	    returncode=returncode+1;
	for(var i=0;i<PhoneNumber.length;i++)
	{
		if ((PhoneNumber.charAt(i) != '-')&&(isNaN(PhoneNumber.charAt(i)*1))&&(PhoneNumber.charAt(i) != '.')&&(PhoneNumber.charAt(i) != '(')&&(PhoneNumber.charAt(i) != ')') && ((PhoneNumber.charAt(i)<"a" || PhoneNumber.charAt(i)>"z")&&(PhoneNumber.charAt(i)<"A" || PhoneNumber.charAt(i)>"Z" )) ) returncode=returncode+2;
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
	if (!isDigitString(MobilePhoneNumber)) returncode=returncode+1;
	if (MobilePhoneNumber.length!=11) returncode=returncode+2;
	return returncode;
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
		if (isDate(strTest)) 
		   return strTest;
		else
		   return false;   
		strTest = strTest.substr(0,7);

	}
	if (strTest.length == 7)
	{
		if (isDate(strTest + "-01")) 
		    return strTest + "-";
		else
		    return false;    
		
		strTest = strTest.substr(0,4);
	}
	if (strTest.length == 4)
	{
		if (isDate(strTest + "-01-01")) 
		    return strTest + "-";
		else
		   return false;    
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

//从currentNode结点开始，遍历所有的父节点查找标记名称为tagName的节点。
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

//从currentNode结点开始，遍历所有的子孙节点查找标记名称为tagName的节点。
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
//判断字符串中是否包含非法字符
function findInvalidChar(strValue)
{
	var code;
	for(var i=strValue.length-1; i>=0;i--) 
	{
		code=strValue.charCodeAt(i);
		if(code<96&&code>90 || code>57&&code<65 || code<48 || code>105 && code!=32)    
		    return false;
	}
	return true;
}
//判断字符串中的非法字符只能是字符或者数字
function findInvalidStr(strValue)
{
	var code;
	for(var i=strValue.length-1; i>=0;i--) 
	{
		code=strValue.charCodeAt(i);
		if(code<96&&code>90 || code>57&&code<65 || code<48 || code>122)    
		    return false;
	}
	return true;
}
//判断英文名
function judgeEname(strValue)
{
	var code;
	for(var i=strValue.length-1; i>=0;i--) 
	{
		code=strValue.charCodeAt(i);
		
		if((code<65 || (code>90&& code<97) || code>122)&& code!=46 && code!=45 && code!=32)    
		    return false;
	}
	return true;
}
//判断审核时间是不是在申请时间以后
function compareDate(strSq,strSh)
{
	
	var strYear = strSq.substr(0,4);
	var strSep1 = strSq.substr(4,1);
	var strMonth =strSq.substr(5,2);
	var strSep2 = strSq.substr(7,1);
	var strDay = strSq.substr(8,2);
	
	var strYear1 = strSh.substr(0,4);
	var strSep11 = strSh.substr(4,1);
	var strMonth1 = strSh.substr(5,2);
	var strSep21 = strSh.substr(7,1);
	var strDay1 = strSh.substr(8,2);
	
	if(strYear1*1<strYear*1)
	{
	    return false;
	}else if(strYear1*1==strYear*1)
	{
		if(strMonth1*1< strMonth*1)
		{
	   		return false;
		}else if(strMonth1*1== strMonth*1)
		{
			if(strDay1*1<strDay*1)
			{
	   			return false;
			}
		}
		return true;
	}
	return true;
}
//判断中文名
function judgeCname(strValue)
{
	var code;
	for(var i=strValue.length-1; i>=0;i--) 
	{
		code=strValue.charCodeAt(i);
		
		if((code<65 || (code>90&& code<97) || (code*1<255&&code>122)) && code!=32 )    
		    return false;
	}
	return true;
}
function judgeClength(strValue,length)
{
	var j=0;
	var code;
	for(var i=strValue.length-1; i>=0;i--) 
	{
		code=strValue.charCodeAt(i);
		if(code<255)
		  j++;
		if(code*1>255)
		  j=j+2;
	}
	
	if(j>length)
	  return false;
	
	return true;  	    
}