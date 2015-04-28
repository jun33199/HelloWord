/**********应用安全模块输入控制的录入判断*******************/

//检查object.value是不是只包含RefString中的字符
function checkValidChars (object, RefString)
{
    InString = object.value;
    for (i=0; i<InString.length; i++)
    {
        var TempChar= InString.charAt(i);
        if (RefString.indexOf(TempChar, 0)<0)
        {
            InString = InString.substring(0, i);
            object.value = InString;
            return false;
        }
    }
    return true;
}

//检测Email地址的合法性的输入过滤
function Fn_Emaildz(object)
{
    var refString = "0123456789_-abcdefghijgklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ@.";
    if (!checkValidChars(object, refString))
    {
        alert("Email地址只能为数字，下画线，短线和大小写字母,请重新输入");
        object.focus();
        return false;
    }
    return true;
}

//判断字符串是否为数字
function Fn_Number(object)
{
    var refString = "0123456789";
    if (!checkValidChars(object, refString))
    {
        alert("每页显示最大数只能为数字，请重新输入！");
        object.focus();
        return false;
    }
    return true;
}


//杨鸣镝 2003-06-20 for 系统缺省数据过滤权限
//判断节点标识是否为数字
function Fn_Jdid(object)
{
    var refString = "0123456789";
    if (!checkValidChars(object, refString))
    {
        alert("功能节点标识只能为数字，请重新输入！");
        object.focus();
        return false;
    }
    return true;
}
//end 杨鸣镝

//龙必进 2003-09-8
//判断字符串是否为大于0的数字
//检查object.value是不是只包含RefString中的字符
function checkValidChars0 (object, RefString)
{
    InString = object.value;

    for (i=0; i<InString.length; i++)
    {
        if(InString == 0){
			object.value="";
			return false;
		}
		var TempChar= InString.charAt(i);
        if (RefString.indexOf(TempChar, 0)<0)
        {
            InString = InString.substring(0, i);
            object.value = InString;
            return false;
        }
    }
    return true;
}
function Fn_Number1(object)
{
    var refString = "0123456789";
    if (!checkValidChars0(object, refString))
    {
    	alert("每页显示最大数必须为数字且大于0，请重新输入!");
        object.focus();
        return false;
    }
    return true;
}
//龙必进 2003-09-7
//判断角色标识(ID)的录入检测
function Fn_Jsid(object)
{
	var refString = "0123456789_abcdefghijgklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    if (!checkValidChars(object, refString))
    {
        alert("角色标识只能为数字、大小写字母和下画线,请重新输入");
        object.focus();
        return false;
    }
    return true;
}
//end 龙必进

//龙必进 2003-09-7
//判断角色标识(ID)的录入检测
function Fn_Jsmc(object)
{
	var refString = "0123456789_abcdefghijgklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    if (!checkValidChars(object, refString))
    {
        alert("角色名称只能为数字、大小写字母和下画线,请重新输入");
        object.focus();
        return false;
    }
    return true;
}
//end 龙必进

//龙必进 2003-09-7
//判断电话号码的录入检测
function Fn_Tel(object)
{
    //var refString = "0123456789-()abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	var refString = "0123456789-()";
	if (!checkValidChars(object, refString))
    {
        alert("输入的电话号码不合法，请重新输入！");
        object.focus();
        return false;
    }
    return true;
}

//判断用户标识(ID)的录入检测
function Fn_Yhid(object)
{
    var refString = "0123456789_abcdefghijgklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    if (!checkValidChars(object, refString))
    {
        alert("用户标识只能为数字、大小写字母和下画线,请重新输入");
        object.focus();
        return false;
    }
    return true;
}

//判断用户密码的录入检测
function Fn_Yhmm(object)
{
    var refString = "0123456789_abcdefghijgklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    if (!checkValidChars(object, refString))
    {
        alert("用户密码只能为数字，下画线和字母,请重新输入");
        object.focus();
        return false;
    }
    return true;
}

//判断用户确认密码的录入检测
function Fn_Yhmmqr(object)
{
    var refString = "0123456789_abcdefghijgklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    if (!checkValidChars(object, refString))
    {
        alert("用户密码只能为数字，下画线和字母,请重新输入");
        object.focus();
        return false;
    }
    return true;
}

//判断Email格式是否正确
function Fn_email(object)
{
    for(i=0,j=0; i<object.value.length && j==0; i++)
    {
        if(object.value.charAt(i) == "@")
        {
            j = 1;
        }
    }
    if(i >= object.value.length)
    {
       // object.value = "";
        object.focus();
        alert("您输入的Email地址错误，请重新输入！");
        return false;
    }
    for(i=0,j=0; i<object.value.length && j==0; i++)
    {
        if(object.value.charAt(i) == ".")
        {
            j = 1;
        }
    }
    if(i >= object.value.length)
    {
     //   object.value = "";
        object.focus();
        alert("您输入的Email地址错误，请重新输入！");
        return false;
    }
    return true;
}

// 检验参数长度的检测
function Fn_cscd(object)
{
    var refString = "0123456789";
    if (!checkValidChars(object, refString))
    {
        alert("您输入的参数长度只能为数字，请重新输入！");
        object.focus();
        return false;
    }
    return true;
}

//检测业务代码的合法性
function Fn_Ywid(object)
{
    var refString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    if (!checkValidChars(object, refString))
    {
        alert("业务代码只能为大写字母,请重新输入");
        object.focus();
        return false;
    }
    return true;
}

//对页面输入非法字符，如'做限制
function checkChars(object, RefString)
{
    InString = object.value;
    for (i=0; i<InString.length; i++)
    {
        var TempChar= InString.charAt(i);
        if (RefString.indexOf(TempChar, 0)>=0)
        {
            InString = InString.substring(0, i)+" ";
            object.value = InString;
            return false;
        }
    }
    return true;
}
function Fn_check(object)
{
    var refString = "'%";
    if (!checkChars(object, refString))
    {
        alert("您输入了非法字符，已经将其自动转换为空格。");
        return false;
    }
    return true;
}

//翻页操作
function FN_ChangePage(opType)
{
    document.forms[0].action.value = "ChangePage";
    //如果点击"第一页"
    if (opType == "diyiye")
    {
        document.forms[0].changePage.value = "1";
        return true;
    }
    //如果点中"上一页"
    if (opType == "shangyiye")
    {
        document.forms[0].changePage.value = parseInt(document.forms[0].changePage.value)-1;
        return true;
    }
    //如果点中"下一页"
    if (opType == "xiayiye")
    {
        document.forms[0].changePage.value = parseInt(document.forms[0].changePage.value)+1;
        return true;
    }
    //如果点中"最后一页"
    if(opType == "zuihouyiye")
    {
        document.forms[0].changePage.value = document.forms[0].pageCount.value;
        return true;
    }
    return false;
}

//清空页面显示
function Fn_ClearPage()
{
    document.forms[0].reset();
    return false;
}


//判断证件号码的合法性
function Fn_Zjhm(object)
{
    var refString = "0123456789";
    if (!checkValidChars(object, refString))
    {
        alert("证件号码输入不合法，请重新输入！");
        object.value = "";
        return false;
    }
    return true;
}


/*
 * 功能：截除字符串中的空格，分三种情形:
 * @1,首空格:delSpace(str)     str可全为空格
 * @2,尾空格:delLSpace(str)    str不可全为空格
 * @3,首尾空格:delRSpace(str)   str不可全为空格
 * 日期: 13/08/2003
 * 作者:Yan-Jun Lee
 */
//截取字符串的左右空格
function delSpace(str) {
    if (delSpaceTmp(str + "@|*&") == "@|*&")
        return "";
    else
        return delSpaceTmp(str);
}
function delSpaceTmp(str) {
    return delLSpace(delRSpace(str));
}
//截取字符串的左侧空格
function delLSpace(str) {
    for (var i = 0; i < str.length; i++) {
        if (str.charAt(i) != " ") {
            str = str.substring(i, str.length);
            break
        }
    }
    return str;
}
//截取字符串的右侧空格
function delRSpace(str) {
    for (var i = str.length - 1; i > -1; i--) {
        if (str.charAt(i) != " ") {
            str = str.substring(0, i + 1);
            break;
        }
    }
    return str;
}

//将页面第一个form表单中的所有输入元素进行截除首尾空格的处理
function delFormSpaces() {
	for (var i = 0; i < document.forms[0].elements.length; i++) {
		document.forms[0].elements[i].value = delSpace(document.forms[0].elements[i].value);
    }
}
//检验用户的密码
function checkYhPassword(object,objectQr)
{
    inString = object.value;
    if(inString == "")
    {
        alert("对不起，用户密码不能为空，请重新输入！");
        object.focus();
        return false;
    }
    if(inString.length < 6)
    {
        alert("对不起，用户密码至少为6位,请重新输入！");
        object.value = "";
        objectQr.value = "";
        object.focus();
        return false;
    }
    if(inString != objectQr.value)
    {
        alert("对不起，您两次输入的密码不一致，请重新输入！");
        object.value = "";
        objectQr.value = "";
        object.focus();
        return false;
    }
    //检查非法字符
    if(!Fn_check(object))
    {
        return false;
    }
    return true;
}
//检查系统用户密码
function checkXtyhPassword(object,objectQr)
{
    return checkYhPassword(object,objectQr);
}

//检查网上用户密码
function checkWsyhPassword(object,objectQr)
{
    return checkYhPassword(object,objectQr);
}

function Fn_checkZzjgdm(object)
{
    var refString = "0123456789";
    if (object.value.length != 9)
    {
    	  alert("组织机构代码只能为9位，请重新输入!");
        object.focus();
        return false;
    }
    if (!checkValidChars0(object, refString))
    {
    	  alert("组织机构代码只能为数字，请重新输入!");
        object.focus();
        return false;
    }
    return true;
}

//快速定位
function fndisplayDM(objLocate,object)
{
    objLocate.value = object.options[object.selectedIndex].value;
}
//快速定位
function fnFastLocate(objLocate,object)
{
//    Fn_checkDM(document.forms[0].locate);
    Fn_checkDM(objLocate);
    //得到检索域输入的字符串的长度
    var cd = objLocate.value.length;
    for(var i=0; i<object.options.length; i++)
    {
        if(object.value.substring(0,cd) == object.options[i].value.substring(0,cd))
            break;
    }

    //如果没有所要找的代码，则将下拉框中的值赋空
    if( i>=object.options.length )
    {
        object.selectedIndex = 0;
    }

    for (var i=0; i<object.options.length; i++)
    {
        var temp = object.options[i].value.substring(0,cd);
        if( temp == objLocate.value)
        {
            object.selectedIndex = i;
            break;
        }
    }
}
//检测代码的合法性
function Fn_checkDM(object)
{
    var refString = "0123456789";
    if (!checkValidChars(object, refString))
    {
        alert("机关代码只能为阿拉伯数字,请重新输入！");
        object.focus();
        return false;
    }
    return true;
}
