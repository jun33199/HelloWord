// 去掉字符串前后的空格。
// @parameter strValue 需要处理的字符串。
// @return String 前后空格和制表符都去掉
function trimString(strValue)
{
    if (strValue==null)
    {
        return null;
    }
    var returnValue = strValue;

    //删除字符串前面的空格(=32=0x20)和制表符(=09=0x09)
    //以及中文字符的空格(=41377=0xA1A1)
    while (returnValue.length>0)
    {
        var code = returnValue.charCodeAt(0);
        if (code==32 || code==9 || code==41377|| code==12288)
        {
            returnValue = returnValue.substr(1);
        }
        else
        {
            break;
        }
    }

    //删除字符串后面的空格(SPACE=20)和制表符(TAB=09)
    //以及中文字符的空格(=41377=0xA1A1)
    while (returnValue.length>0)
    {
        var index = returnValue.length-1;
        var code = returnValue.charCodeAt(index);
        if (code==32 || code==9 || code==41377 || code==12288)
        {
            returnValue = returnValue.substr(0,index);
        }
        else
        {
            break;
        }
    }

    return returnValue;
}

function trimBlank(strValue)
{
    if (strValue==null)
    {
        return null;
    }
    var returnValue = "";

    //删除字符串的空格(=32=0x20)和制表符(=09=0x09)
    //以及中文字符的空格(=41377=0xA1A1)
    var n = strValue.length;
    for(i=0;i<n;i++)
    {
        var code = strValue.charCodeAt(i);
        if (!(code==32 || code==9 || code==41377 || code==12288))
        {
           returnValue += strValue.charAt(i);           
        }
    }
    return returnValue;
}


//获取字符串的存储长度
//对于中文字符，每一个长度=2
//对于非中文Ascii2字符，每一个长度=1
function getStringLength(strValue)
{
    if (strValue==null || strValue=="undefined")
    {
        return 0;
    }
    var strLength = 0;
    for (var i=0;i<strValue.length;i++)
    {
        var code = strValue.charCodeAt(i);
        //if (code*1>16677215)
        //{ //判断字节长为4的
        //	strLength += 4;
        //}
        //else if (code*1>65535)
        //{ //判断字节长度为3的
        //	strLength += 3;
        //}
        //else
        if (code*1>255)
        { //判断双字节字符
            strLength += 2;
        }
        else
        { //普通的ASCII字符
            strLength += 1;
        }
    }
    return strLength;
}

//初始化检查方法，把以下属性加到文本域中
//    required：该字段是否允许为空。"true"，不能为空；"false"，可以为空。默认="false"。
//    maxLength：该字段允许输入的最大长度。数字类型。无定义则不检查。
//    specialChecker：注册的特殊输入检查方法名称，可以带常量作为实参。
//    objectChecker：注册的特殊输入检查方法名称，可以带常量作为实参，与specialChecker不同的是，objectChecker方法，得到object作为变量。
//    captionName：该输入字段的提示名称。用于输入检查非法的情况下，在提示信息中显示该字段名称。
//    regExpPattern：正则表达式的pattern。用于正则表达式判断。
//    nullable：是否允许为空。在未定义required属性的时候，可用此属性替代。true，可以为空；false，不可以为空。
//    patternDesc：当specialChecker、objectChecker、regExp检查失效的时候，提示用户正确输入要求的描述信息。
//    keepSpaces：设置是否保存输入中前后的空格，如果不保存，这检查前将trim输入的字符串，并更新输入域。默认：true。
function initCheckObject(obj,captionName,required,maxlength,specialChecker,patternDesc,objectChecker,regExpPattern,nullable,keepSpaces)
{
    obj.required = required;
    obj.maxlength = maxlength;
    obj.specialChecker = specialChecker;
    obj.objectChecker = objectChecker;
    obj.captionName = captionName;
    obj.regExpPattern = regExpPattern;
    obj.nullable = nullable;
    obj.patternDesc = patternDesc;
    obj.keepSpaces = keepSpaces;
}

//通用输入检查方法，支持select、textarea、input（type=text/password）输入控件
//检查参数需要写在对应输入控件的tag中，包括
//    required：该字段是否允许为空。true，不能为空；false，可以为空。默认=false。
//    maxLength：该字段允许输入的最大长度。数字类型。无定义则不检查。
//    specialChecker：注册的特殊输入检查方法名称，可以带常量作为实参。
//    objectChecker：注册的特殊输入检查方法名称，可以带常量作为实参，与specialChecker不同的是，objectChecker方法，得到object作为变量。
//    captionName：该输入字段的提示名称。用于输入检查非法的情况下，在提示信息中显示该字段名称。
//    regExpPattern：正则表达式的pattern。用于正则表达式判断。
//    nullable：是否允许为空。在未定义required属性的时候，可用此属性替代。true，可以为空；false，不可以为空。
//    patternDesc：当specialChecker、objectChecker、regExp检查失效的时候，提示用户正确输入要求的描述信息。
//    keepSpaces：设置是否保存输入中前后的空格，如果不保存，这检查前将trim输入的字符串，并更新输入域。默认：true。
function checker_checkInput(obj)
{
    if (obj==null)
    {
        alert("输入检查中传入空对象！");
        return false;
    }
    if (obj.tagName=="SELECT" || obj.tagName=="TEXTAREA")
    {
    }
    else if (obj.tagName=="INPUT" && (obj.type.toLowerCase()=="text"||obj.type.toLowerCase()=="password"))
    {
    }
    else
    {
        alert("检查方法不支持"+obj.name+"对象的类型！");
        return false;
    }

    //取可输入最大长度
    var maxLength = obj.maxLength;
    var minLength = obj.minLength;
/*    if (maxLength!=null)
    {
        maxLength=trimeString(maxLength);
        if (isNaN(maxLength*1)==false)
        {
            maxLength=maxLength*1;
        }
        else
        {
            maxLength=null;
        }
    }
    else
    {
        maxLength=null;
    }
*/

    //取是否是必填项
    var required = false;
    if (obj.required!=null)
    {   //定义了required属性
        if (obj.required.toLowerCase()=="false")
        {   //如果设置了该属性==false
            required=false;
        }
        else
        {
            required=true;
        }
    }
    else if (obj.nullable!=null && obj.nullable.toLowerCase()=="false")
    {
        required = true;
    }

    //取是否是必填项
    var keepSpaces = false;
    if (obj.tagName=="TEXTAREA" || obj.tagName=="SELECT")
    {   //TextArea和Select自动保持原输入值
        keepSpaces = true;
    }
    else if (obj.tagName=="INPUT" && obj.type!=null && obj.type.toLowerCase()=="password")
    {   //password类型输入域，自动保持原输入值
        keepSpaces = true;
    }
    if (obj.keepSpaces!=null)
    {   //如果设置了keepSpaces属性
        if (obj.keepSpaces.toLowerCase()=="false")
        {
            keepSpaces=false;
        }
        else
        {
            keepSpaces=true;
        }
    }

    //取特殊检查方法名称
    var specialChecker = obj.specialChecker;
    if (specialChecker != null)
    {
        var index = specialChecker.indexOf("()");
        if (index>=0)
        {
            specialChecker = specialChecker.substr(0,index)+"(value)";
        }
        else
        {
            index = specialChecker.indexOf("(");
            if (index>=0)
            {
                specialChecker=specialChecker.substr(0,index)+"(value,"+specialChecker.substr(index+1);
            }
            else
            {
                specialChecker = specialChecker+"(value)";
            }
        }
        //根据方法名称组装成方法对象Function()
        specialChecker = new Function("value","return "+specialChecker);
    }

    //取特殊对象检查方法名称
    var objectChecker = obj.objectChecker;
    if (objectChecker != null)
    {
        var index = objectChecker.indexOf("()");
        if (index>=0)
        {
            objectChecker = objectChecker.substr(0,index)+"(inputObj)";
        }
        else
        {
            index = objectChecker.indexOf("(");
            if (index>=0)
            {
                objectChecker=objectChecker.substr(0,index)+"(inputObj,"+objectChecker.substr(index+1);
            }
            else
            {
            objectChecker = objectChecker+"(inputObj)";
            }
        }
        //根据方法名称组装成方法对象Function()
        objectChecker = new Function("inputObj","return "+objectChecker);
    }

    var regExpPattern = null;
    if (obj.regExpPattern!=null)
    {   //如果对象的Tag中有定义正则表达式，则取正则表达式的pattern
        regExpPattern=obj.regExpPattern;
    }

    //取提示名称属性
    var captionName = obj.captionName;
    if (captionName==null || captionName=="")
    {
        captionName = obj.name;
    }

    //提取正确输入的描述信息
    var patternDesc = obj.patternDesc;
    if (patternDesc!=null && patternDesc=="")
    {
        patternDesc==null;
    }

    if (keepSpaces==false)
    {   //如果需要trim空格
        obj.value = trimString(obj.value);
    }

    //如果对象disabled，则该对象的值不会传到服务器端，所以不会检查此参数
    if (obj.disabled==true)
    {
        return true;
    }
    else if (obj.readOnly==true)
    {   //如果对象readOnly，则该对象的值逻辑上不应该修改服务器端的值，所以不检查此参数
        return true;
    }

    if (required==true && obj.value=="")
    {   //判断输入是否为空的检查
        if (obj.tagName=="SELECT")
        {
            alert("“"+captionName+"”是必填项，必须选择！");
        }
        else
        {
            alert("“"+captionName+"”是必填项，必须输入！");
        }
        checker_focusToObject(obj);
        return false;
    }
    if (maxLength!=null && obj.value!="" && getStringLength(obj.value)>maxLength)
    {   //判断输入是否超过最大长度的判断
        alert("“"+captionName+"”输入域最多允许输入"+maxLength+"个字符，请重新输入！");
        checker_focusToObject(obj);
        return false;
    }
    
    if (minLength!=null && obj.value!="" && getStringLength(obj.value)<minLength)
    {   //判断输入是否超过最小长度的判断
        alert("“"+captionName+"”输入域至少需要输入"+minLength+"个字符，请重新输入！");
        checker_focusToObject(obj);
        return false;
    }
    
    if (specialChecker!=null && obj.value!="")
    {   //根据指定的特殊输入检查方法判断
        var rtnValue = specialChecker(obj.value);
        if (rtnValue!=true && rtnValue!="true")
        {
            var alertMessage = checker_getAlertMessage(captionName,patternDesc,rtnValue);
            alert(alertMessage);
            checker_focusToObject(obj);
            return false;
        }
    }

    if (objectChecker!=null)
    {   //根据指定的特殊object检查方法判断
        var rtnValue = objectChecker(obj);
        if (rtnValue!=true && rtnValue!="true")
        {
            var alertMessage = checker_getAlertMessage(captionName,patternDesc,rtnValue);
            alert(alertMessage);
            checker_focusToObject(obj);
            return false;
        }
    }

    if (regExpPattern!=null && obj.value!="")
    {   //根据正则表达式匹配，判断输入是否符合要求
        if (regExpPattern.length>1 && regExpPattern.charAt(0)!="/")
        {
            regExpPattern = "/"+regExpPattern+"/";
        }
        var re = eval(regExpPattern);
        if (re.exec(obj.value)==null)
        {
            var alertMessage = checker_getAlertMessage(captionName,patternDesc);
            alert(alertMessage);
            checker_focusToObject(obj);
            return false;
        }
    }

    return true;
}

//焦点跳转到输入控件
function checker_focusToObject(obj)
{
    if (obj.tagName=="INPUT" && (obj.type=="text" || obj.type=="password"))
    {
        obj.focus();
        obj.select();
    }
    else
    {
        obj.focus();
    }
}

//获取页面提示信息
function checker_getAlertMessage(captionName, patternDesc, rtnValue)
{
    var alertMessage = "";
    if (rtnValue!=null && rtnValue!=false && rtnValue!="false")
    {
        alertMessage += "“"+captionName+"”输入不符合要求："+rtnValue+"，请重新输入！";
    }
    else if (patternDesc!=null)
    {
        alertMessage += "“"+captionName+"”正确的输入要求："+patternDesc+"，请重新输入！";
    }
    else
    {
        alertMessage += "“"+captionName+"”输入格式不正确，请重新输入！"
    }
    return alertMessage;
}

//检查输入域对象数组！
//aryObjects：包含待检查对象！
function checker_checkGroup(aryObjects)
{
    if (aryObjects==null || aryObjects.length==0)
    {
        alert("传入的对象不是数组！");
        return false;
    }
    for (var i=0;i<aryObjects.length;i++)
    {
        var rtnValue = checker_checkInput(aryObjects[i]);
        if (rtnValue!=true)
        {
            return false;
        }
    }
    return true;
}

/////////////////////////////////////////////////////////////////////////
//  定义SpecialChecker的地方
/////////////////////////////////////////////////////////////////////////

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

//function  : 检查输入字符串是否由数字组成
//parameters: DigitString: String,待检查字符串的值
//			  totalLength: int, 数字的长度限制（不包括小数点）
//			  decimalLength: int，小数的长度限制
//            totalLength-decimalLength 整数部分的限制
//            isPositive 是否为非负数 true表示一定要是非负数，false表示一定要是负数，null表示任意 add by zhangp 2003-09-25
//return    : false: 字符串中包含除数字外的字符
//			  true : else
function checkNumber(DigitString, totalLength, decimalLength,isPositive)
{
    var re = "";
    
    if(isPositive == "true" || isPositive == true)
    {//如果是非负数
        if(isNaN(DigitString*1) || DigitString*1<0)
            return false;
    }
    if(isPositive == "false" || isPositive == false)
    {//如果是负数
        if(isNaN(DigitString*1) || DigitString*1>=0)
            return false;
    }

    
    if (decimalLength!=null && decimalLength != 0)
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
        re="([\\d]{0,"+ intergerLength +"}"+re+"|[\\d]{0,"+intergerLength+"})";
    }
    re = new RegExp("^"+re+"$","g");

    //当字符串为空、位数不对、不能匹配成数字时
    if(re.exec(DigitString) == null)
    {
        return false;
    }
    return true;
}

//function  : 检查输入的字符串是否可以转化为Date类型
//Parameters: strData:String
//return    : true: 通过检查
//			  false: 未通过检查。
//修改人：    章鹏 
//修改时间：  2003-09-28
//修改原因：  日期格式统一改成20030928
function checkDate(strDate)
{
    if (strDate.length!=8) return false;
    var strYear = strDate.substr(0,4);
    //var strSep1 = strDate.substr(4,1);
    var strMonth = strDate.substr(4,2);
    //var strSep2 = strDate.substr(7,1);
    var strDay = strDate.substr(6,2);
    var objDate = new Date(strMonth+"-"+strDay+"-"+strYear);
    if (isNaN(objDate)) return false; //不是日期类型
    //日期类型范围 1900<年<2100
    if(strYear*1<1900) return false;
    if ((strYear*1 != objDate.getYear()*1)&&(strYear*1 != objDate.getYear()*1+1900)) return false; //输入的年不对
    //if(strSep1!="-") return false; //年月分割符必须是'-'
    if (strMonth*1 != objDate.getMonth()*1+1) return false; //输入的月份不对
    //if(strSep2!="-") return false; //年月分割符必须是'-'
    if (strDay*1 != objDate.getDate()*1) return false; //don't call getDay function. //输入的日期不对
    return true;
}

/** 
 *   检查计算机代码是否正确
 *   计算机代码都是8位，第三位可以为数字或字母，其余所有的位为数字；
 */
function checkJsjdm(jsjdm)
{
    var regExp = /^[\d]{8}$|^[\d]{2}[\w]{1}[\d]{5}$/g;
    if(regExp.exec(jsjdm) == null)
    {
        return false;
    }
    return true;
}
/** 
 *   检查计算机代码是否正确
 *   计算机代码都是8位，第三位可以为数字或字母，其余所有的位为数字；
 */
function checkJsjdmzrr(jsjdm)
{
    if(jsjdm==null||jsjdm=="")
    {
        return true;    
    }
    var regExp = /^[\d]{8}$|^[\d]{2}[\w]{1}[\d]{5}$/g;
    if(regExp.exec(jsjdm) == null)
    {
        return false;
    }
    return true;
}

/** 
 *   检查自然人计算机代码是否正确
 *   计算机代码都是8位，最后一位必须是字母，其余为可为数字和字母；
 */
function checkZrrjsjdm(jsjdm)
{
    if(jsjdm==null||jsjdm=="")
    {
        return true;    
    }
    var regExp =/^[1234567890ABCDEFGHIJKLMNPQRSTUVWXYZ]{7,7}[ABCDEFGHIJKLMNPQRSTUVWXYZ]{1,1}/g;
    if(regExp.exec(jsjdm) == null)
    {
        return false;
    }
    return true;
}

/** 
 *   检查自然人计算机代码是否正确
 *   计算机代码都是8位，最后一位必须是字母，其余为可为数字和字母；
 */
function checkZrrjsjdmBG(jsjdm)
{
    if(jsjdm==null||jsjdm=="")
    {
        return true;    
    }
    var regExp =/^[1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ]{7,7}[ABCDEFGHIJKLMNOPQRSTUVWXYZ]{1,1}/g;
    if(regExp.exec(jsjdm) == null)
    {
        return false;
    }
    return true;
}

/**
 * 开业登记的时候计算机代码必须是8位数字
 **/
function checkKydjJsjdm(jsjdm)
{
    var regExp = /^[\d]{8}$/g;
    if(regExp.exec(jsjdm) == null)
    {
        return false;
    }
    return true;    
}
//function  : 查询税源户清单查询条件计算机代码可以输入最多十个计算机代码，以逗号分隔
//Parameters: QueryJsjdm:String
//return    : true: 通过检查
//	      false: 未通过检查。
function checkSyhJsjdm(QueryJsjdm)
{
    if (QueryJsjdm==null || QueryJsjdm.length==0)
    {
        return true;
    }
    var strValue=QueryJsjdm;
    if (isAllCharValid(strValue,"1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz,")==false)
    {   //包含非法字符
        return false;
    }
    var str=strValue.split(",");    
    for(var i=0;i<str.length;i++)
    {
	if(!checkJsjdm(str[i]))
	{
	      //alert("计算机代码中第"+(i+1)+"个格式不对！");
	      return false;	
	}
    }

    return true;
}
/** 检查电子邮件
* 匹配模式：xx@xx.xx 格式,xx不能包含[~!@#$%^&*().,]这些字符
* return 0：正确；1：不正确
*/
function checkEmailAddress(EmailAddress)
{
    var re=/^[^~!@#$%^&*().,]{1,30}@[^~!@#$%^&*().,]{1,20}\.[^~!@#$%^&*(),]{1,10}$/i;

    if(re.exec(EmailAddress) == null)
    {
        return false;
    }
    return true;
}

/** 检查邮编
 * 合法长度6位
 * 合法字符：数字
 * return true:正确；false：不正确
 */

function checkPostCode(strValue)
{
    var re =/^\d{6}$/g;
    if(re.exec(strValue) == null)
    {
        return false;
    }
    return true;
}

//function  : 检查输入的身份证号
//Parameters: IdentityCardNumber:string,待检查的身份证号;
//return    : true: 通过检查
//			  false: 未通过检查。
function checkIdentityCard(IdentityCardNumber)
{
    // length of IdentityCardNumber
    if ((IdentityCardNumber.length != 15)&&(IdentityCardNumber.length != 18))
    {
        return false;
        //return "身份证长度应该是15或18位";
    }
    //the first char cannot be "0"
    if(IdentityCardNumber.charAt(0)=="0")
    {
        return false;
        //return "身份证首位不能是0";
    }
    //Is the IdentityCardNumber string composed of digit char(last char may be "x" or "X")
    var lastChar=IdentityCardNumber.charAt(IdentityCardNumber.length-1);
    var tempString=IdentityCardNumber;
    if(tempString.length==18 && (lastChar=="x"||lastChar=="X"))
    {
        tempString=IdentityCardNumber.substr(0,IdentityCardNumber.length-1);
    }
    if (checkNumber(tempString)!=true)
    {
        return false;
        //return "身份证号码必须是数字，18位身份证号码最后一位可以是x";
    }
    //检查出生日期是否符合格式要求 15位：YYMMDD  18位:YYYYMMDD
    var strIdentityCard = "19990101";
    if (IdentityCardNumber.length == 15)
    {
        strIdentityCard = "19" + IdentityCardNumber.substr(6,6);
    }
    else if (IdentityCardNumber.length == 18)
    {
        strIdentityCard = IdentityCardNumber.substr(6,8);
    }
    //修改为二月份时,放开校验,许多用户的身份证为农历
    if (strIdentityCard.substr(4,2)=="02")
    {    
    	return true;	
    }
    else 
    {
    	if(checkDate(strIdentityCard)!=true)
        return false;
        //return "输入的身份证号码未包含正确的出生日期";    	
    }
    return true;
}

//function  : 检查输入的电话号码是否有数字和'-'组成
//Parameters: PhoneNumber:String
//return    : true: 通过检查
//			  false: 未通过检查。
function checkPhoneNumber(PhoneNumber)
{
    if (PhoneNumber==null || PhoneNumber.length==0)
    {
        return true;
    }
    var strValue=PhoneNumber;
    for(var i=0;i<strValue.length;i++)
    {
    	if(strValue.charAt(i)=='.'||strValue.charAt(i)==','||strValue.charAt(i)=='、'||strValue.charAt(i)=='，')
    	{
	    	if(strValue.charAt(i+1)=='.'||strValue.charAt(i+1)==','||strValue.charAt(i+1)=='、'||strValue.charAt(i+1)=='，')
	    	{
	    		//长度小于4
	    		return false;
	    	}
	    	else if(strValue.charAt(i+2)=='.'||strValue.charAt(i+2)==','||strValue.charAt(i+2)=='、'||strValue.charAt(i+2)=='，')
	    	{
	    		//长度小于4
	    		return false;	
	    	}
	    	else if(strValue.charAt(i+3)=='.'||strValue.charAt(i+3)==','||strValue.charAt(i+3)=='、'||strValue.charAt(i+3)=='，')
	    	{
	    		//长度小于4
	    		return false;
	    	}	
    	}
    }
    if (strValue.length<4)
    {   //长度小于4
        return false;
    }
    if (strValue.length>0 )
    {
        if (strValue.charAt(0)=='-' || strValue.charAt(strValue.length-1)=='-')
        {   //首字母和末尾字母不能是‘-’
            return false
        }
        if (strValue.indexOf('--')>=0)
        {   //不能包含连续的两个“-”
            return false;
        }
    }
    if (isAllCharValid(strValue,"1234567890-.,、，ABCDEFGHIJKLMNOPQRSTUVWXYZ()abcdefghijklmnopqrstuvwxyz")==false)
    {   //包含非法字符
        return false;
    }
    return true;
}

//function  : 检查输入的移动电话号码
//Parameters: MobilePhoneNumber:String
//return    : true: 通过检查
//			  false: 未通过检查。
function checkMobilePhoneNumber(MobilePhoneNumber)
{
    if (trimString(MobilePhoneNumber).length!=11)
    {   //长度不是11位
        return false;
    }
    if (isAllCharValid(MobilePhoneNumber,"1234567890")==false)
    {   //不全是数字
        return false;
    }
    return true;
}

//检查名称，名称可以包含中文字符和英文字母
function checkName(qymcStr)
{
    return true;
}
//检查纳税人名称
function checkNsrmc(nsrmcStr)
{
    if(getStringLength(nsrmcStr)>200)
    {
        return false;        
    }
    return true;
}
//检查税务代理名称
function checkSwdlmc(swdlmcStr)
{
    if(getStringLength(swdlmcStr)>200)
    {
        return false;        
    }
    return true;
}
//检查投资方名称
function checkTzfmc(tzfmcStr)
{
    if(getStringLength(tzfmcStr)>200)
    {
        return false;        
    }
    return true;
}
//检查分支机构名称
function checkFzjgmc(fzjgStr)
{
    if(getStringLength(fzjgStr)>200)
    {
        return false;        
    }
    return true;
}
//检查总机构名称
function checkZjgmc(zjgStr)
{
    if(getStringLength(zjgStr)>200)
    {
        return false;        
    }
    return true;
}
//检查姓名(中文)
function checkPersonName_CN(strValue)
{
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

//检查姓名(英文)
function checkPersonName_EN(strValue)
{
    return isAllCharValid(strValue,"abcdefghijklmnopqrstuvwxyz. ABCDEFGHIJKLMNOPQRSTUVWXYZ");
}

//检查银行帐号
function checkBankName(strValue)
{
    return !hasChineseChar(strValue);
    //return isAllCharValid(strValue,"1234567890 #-ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
}
//检查批准成立证照或文件号码
function checkYyzzh(strValue)
{
    if (trimString(strValue).length<6)
    {   //长度不是6位
        return false;
    }
    return true;
}

//function: 检查公司名称(英文)
function checkCompanyName_CN(strValue)
{
   var englishStr="abcdefghijklmnopqrstuvwxyz. ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    for(var i=0;i<strValue.length;i++)
    {
        var tmpCode = strValue.charCodeAt(i);
        var tmpChar = strValue.charAt(i);
        if(tmpCode>255)
        {
            continue;
        }
        else if(englishStr.indexOf(tmpChar)>0)
        {
          return false;
        }
    }
    return true;
}

//function: 检查公司名称
function checkCompanyName_EN(strValue)
{
    return isAllCharValid(strValue,"abcdefghijklmnopqrstuvwxyz. ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890");
}
//function: 检查非法字符
function checkIllegalchar(strValue)
{
    var englishStr="abcdefghijklmnopqrstuvwxyz. ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
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
//function: 检查是否是百分数
function checkPercentNumber(strValue)
{
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

//function：检查组织机构代码
function checkZzjgdm(strValue)
{
    var re =/^[\w*#]{9}$/ig;
    if(re.exec(strValue) == null)
    {
        return false;
    }
    return true;
}
//function：税务登记证号
function checkSwdjzh(strValue)
{
	var re =/^[0123456789abcdefghijklmnopqrstuvwxyz*#]{0,18}$/ig;
    if(re.exec(strValue) == null)
    {
       return false;
    }   
    return true;
}
//function：生成税务登记证号校验
function checkNewSwdjzh(strValue)
{
	var re =/^[1234567890abcdefghijklmnopqrstuvwxyz*#]{15,15}\d\d\d/ig;
    if(re.exec(strValue) == null)
    {
       return false;
    }   
    return true;
}

//added by zhangp 20031124
//function: 避免form中的input框敲回车后，自动提交
function avoidEnter(){
   var e = window.event;
   if(e.srcElement.name){
       if(e.keyCode){
          if(e.keyCode == 13){
          	return false;
          }
       }
   }
   return true;
}