// 全局变量
var isNav4, isIE4, IEcurrState,IEinitState
if (navigator.appVersion.charAt(0) == "4")
{
    if (navigator.appName == "Netscape")
    {
        isNav4 = true
    }
    else
    {
        isIE4 = true
    }
}

// ** functions that get and set persistent data **
// set persistent data
function setCurrState(setting)
{
    if (isNav4)
    {
        document.cookie = "currState=" + escape(setting)
    }
    else
    {
        // for IE4, data is saved as a global variable instead of cookie
        IEcurrState = setting
    }
}
// retrieve persistent data
function getCurrState()
{
    if (isIE4)
    {
        // for IE4, data is in global var instead of cookie
        return IEcurrState;
    }
    var label = "currState=";
    var labelLen = label.length;
    var cLen = document.cookie.length;
    var i = 0;
    while (i < cLen)
    {
        var j = i + labelLen;
        if (document.cookie.substring(i,j) == label)
        {
            var cEnd = document.cookie.indexOf(";", j);
            if (cEnd ==	-1)
            {
                cEnd = document.cookie.length;
            }
            return unescape(document.cookie.substring(j,cEnd));
        }
        i++;
    }
    return "";
}
// **function that updates persistent storage of state**
// toggles an outline mother entry, storing new value
function toggles(n, obj)
{
    var newString = ""
    var expanded;

    var divobj;
    var ywdm_p ,ywdm;
    var len;

    // get all <DIV> tag objects in IE4
    if (isIE4)
    {
        var allDivs = document.all.tags("DIV");
    }

    var currState = getCurrState()+""; // of whole outline
    var ywdm_p = eval("document.forms[0].ywdm_"+n).value;
    var len_p = ywdm_p.length;

    for (var i = n+1; i < currState.length; i++)
    {
        ywdm = eval("document.forms[0].ywdm_"+i).value;
        len = ywdm.length;
        if(len_p == len - 1)
        {
            if(ywdm.substring(0,len_p) == ywdm_p)
            {
                expanded = currState.charAt(i); // of clicked item
                newString += currState.substring(0,i);
                newString += expanded ^ 1; // Bitwise XOR clicked item
                newString += currState.substring(i+1,currState.length);
                currState = newString;
                newString = "";
                if (isIE4)
                {
                    divobj = eval("div_"+i);
                    // dynamically change display style without reloading
                    if (expanded == "0")
                    {
                        obj.src = "images/minus.gif"
                        divobj.style.display = "";
                    }
                    else
                    {
                        obj.src = "images/plus.gif"
                        divobj.style.display = "none";
                    }
                }
            }
        }
    }
    setCurrState(currState); // write new state back to cookie
    if (isNav4)
    {
        location.reload();
    }
}

function check(n)
{
    var chkobj;
    var isChecked;
    var ywdm_p ,ywdm;
    var len;

    var currState = getCurrState(); // of whole outline
    var ywdm_p = eval("document.forms[0].ywdm_"+n).value;

    var len_p = ywdm_p.length;
    isChecked = eval("document.forms[0].chx_"+n).checked;

    for (var i = n+1; i < currState.length; i++)
    {
        ywdm = eval("document.forms[0].ywdm_"+i).value;
        len = ywdm.length;
        if(len_p < len)
        {
            if(ywdm.substring(0,len_p) == ywdm_p)
            {
                chkobj = eval("document.forms[0].chx_"+i);
                chkobj.checked=isChecked;
            }
        }
    }
}

// for IE4, initialize flagged tags to style display = "non"
function IEInit()
{
    if (isIE4)
    {
        var visState = getCurrState()+"";
        for (var i = 0; i < visState.length; i++)
        {
            if (visState.charAt(i) == "0")
            {
                document.all.tags("DIV")[i].style.display = "none";
            }
        }
    }
}

function initCurrState(state)
{
    if (!getCurrState())
    {
        var initState = state;

        var state ="1";
        for (var i = 1; i < initState.length; i++)
        {
            state = state+"1";
        }
        setCurrState(state)
    }
}
