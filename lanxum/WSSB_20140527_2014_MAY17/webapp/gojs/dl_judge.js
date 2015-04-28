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

//判断电话号码的录入检测
function Fn_Tel(object)
{
    var refString = "0123456789-()abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
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
        object.value = "";
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
        object.value = "";
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
    var refString = "'";
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
