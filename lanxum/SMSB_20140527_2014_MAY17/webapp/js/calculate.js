/***Begin***计算相关的JavaScript脚本 ******************************/

// 对Double类型执行两位精度的四舍五入：
// 10.014 -> 10.01 / 10.015 -> 10.02 / 10 -> 10.00
function round2(n)
{
    var s = "" + Math.round(n * 100) / 100;
    var i = s.indexOf('.');
    if (i < 0)
    {
        // 如果是整数
        return s + ".00";
    }
    else
    {
        // 如果不是整数
        var t = s.substring(0, i+1) + s.substring(i+1, i+3);
        // 补0(10.0 -> 10.00)
        if (i+2 == s.length)
        {
            t += "0";
        }
        return t;
    }
}

// 四舍五入函数
function handleFloat(inputNum)
{
  inputString=inputNum.toString();
  for(i=1; i<=inputString.length; i++)
  {
    if(inputString.substring(i-1,i)==".")
    {
      if(inputString.length > i+2)
      {
        return ((parseFloat(inputString)+0.0050001).toString()).substring(0,i+2);
      }
      break;
    }
  }
  return inputString;
}

// 字符串到浮点数
function stringToFloat(inputString)
{
  return parseFloat(inputString);
}

// 是否数字 （整数 或者 浮点数）
function isNumber(inputString)
{
    str = parseFloat(inputString);
    return !isNaN(str) && (str == inputString);
}

// 输入是不是整数
function isInt(inputString)
{
    return (!isNaN(parseInt(inputString))) ? true : false;
}

// 检查object.value是不是只包含RefString中的字符
function checkValidChars (object, RefString)
{
    InString = object.value;
    alert(InString);
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

// 检查输入字符串，如果是合法的日期字符串，如2002-05-01，返回true
function isValidDate(inputString)
{
    var dateArray = inputString.split("-");

    if (dateArray.length < 3)
    {
        return false;
    }
    var myYear = dateArray[0];
    var myMonth = dateArray[1];
    var myDay = dateArray[2];

    if (!isInt(myYear) || !isInt(myMonth) || !isInt(myDay)
        || myMonth>12 || myMonth<1 || myDay>31 || myDay<1
        || myYear.length==3 || myYear.length==1)
    {
        return false;
    }

    return true;
}

// 将输入的日期字符串格式化为 2002-02-21格式
function formatDate(inputString)
{
    if (isValidDate(inputString))
    {
        var dateArray = inputString.split("-");
        var myYear = dateArray[0];
        var myMonth = dateArray[1];
        var myDay = dateArray[2];
        if (myYear.length == 2)
        {
            myYear = "20" + myYear;
        }
        if (myMonth.length == 1)
        {
            myMonth = "0" + myMonth;
        }
        if (myDay.length == 1)
        {
            myDay = "0" + myDay;
        }
        return myYear + "-" + myMonth + "-" + myDay;
    }
    return "";
}

//检验是否选择了纪录删除
function fnChkChk(txtValue)
{
    if (txtValue == null)
    {
        window.alert("没有记录！");
        return false;
    }

    if (txtValue.length == null)
    {
        if (!txtValue.checked)
        {
            window.alert("请至少选择一条要删除的记录！");
            return false;
        }
        else
        {
            return true;
        }
    }

    for(i=0;i<=txtValue.length-1;i++)
    {
        if (txtValue[i].checked)
        {
            return true;
        }
    }

    window.alert("请至少选择一条要删除的记录！");
    return false;
}

//全选按钮
function All_quanxuan()
{
    if(document.all.chkall.checked)
    {
        for(var i=0; i<document.forms[0].elements.length; i++)
        {
            var e = document.forms[0].elements[i];
            if(e.name != "checkall")
            {
                e.checked = true;
            }
        }
    }
    else
    {
        for(var i=0; i<document.forms[0].elements.length; i++)
        {
            var e = document.forms[0].elements[i];
            if(e.name != "uncheckall")
            {
                e.checked = false;
            }
        }
    }
}
//全选的三态显示
function FN_setChkState()
{
    var e;
    var checked = false;
    var unchecked = false;

    if (document.forms[0].selectedIndex.length == null)
    {
        if (document.forms[0].selectedIndex.checked)
        {
            document.forms[0].chkall.checked = true;
        }
        else
        {
            document.forms[0].chkall.checked = false;
        }
        return;
    }

    for(var i=0; i<document.forms[0].selectedIndex.length; i++)
    {
        e = document.forms[0].selectedIndex[i];
        if (e.checked)
        {
            checked = true;
        }
        else
        {
            unchecked = true;
        }
    }

    if (checked == true && unchecked == false)
    {
        document.forms[0].chkall.checked = true;
        document.forms[0].chkall.indeterminate = false;
    }
    else if (checked == false && unchecked == true)
    {
        document.forms[0].chkall.checked = false;
        document.forms[0].chkall.indeterminate = false;
    }
    else
    {
        document.forms[0].chkall.checked = true;
        document.forms[0].chkall.indeterminate = true;
    }
}

// 将下拉列表框selObject选到初值为preValue的项
function FN_PreSelect(selObject, preValue)
{
    for (i=0; i<selObject.length; i++)
    {
        selOption = selObject.options[i];
        if (selOption.value == preValue)
        {
            selOption.selected = true;
            break;
        }
    }
}

// 检查object.value包含RefString的字符重复的数目
function calCharNum(object, RefString)
{
    InString = object.value;
    var num = 0;
    for (i=0; i<InString.length; i=i+RefString.length)
    {
        var TempChar= InString.substring(i,i+RefString.length);
        if (TempChar==RefString)
        {
            num++;
        }
    }
    return num;
}
/***End*****计算相关的JavaScript脚本 *************************************/
