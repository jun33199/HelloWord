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
// ��ȡ���������
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
// �� "|" ��ʽ�����  Select
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
// ��� firefox �����ⷽ����firefox�޷����ղ��� select ��itemɾ��
// �� "|" ��ʽ�����  Select, valStr��txtStr�ĳ�itemVals�еĲ���selObj1, itemVals�еĲ���selObj2
function fillFirefoxSelect(selObj1, valStr, txtStr, selObj2, itemVals)
{
    var valArray = valStr.substring(0,valStr.length-1).split('|');
    var txtArray = txtStr.substring(0,txtStr.length-1).split('|');

    // ��� itemVals Ϊһ����û�к��� '|' �����
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
// �� Select ��ɾ��һ��item
function removeFromSelect(selObj, itemVal) {
	for (i = 0;i < selObj.options.length;i++) {
		if (selObj.options[i].value == itemVal) {
			selObj.options.remove(i);
			break;
		}
	}
}
// �� Select1 ���ƶ�  value item �� Select2
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

// �ƶ�sel1������ѡ��item��sel2
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

// ��ȡselect������ֵ��ʽ����
function getStringFromSelect(selObj) {
	var valStr = "";
	for (i = 0;i < selObj.length;i++) {
		valStr += selObj.options[i].value + "|";
	}
	return valStr;
}
// ����ͼƬ JavaScript
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

// �˳����˵�
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
// �ж�Select�Ƿ�ѡ��ֵΪ""˵��û��ѡ��
function Fn_validate_select(object, msg)
{
    if (object.value == "")
    {
        object.focus();
        alert("��ѡ��" + msg + "��");
        return false;
    }
    return true;
}

// �ж��������ݴӳ������Ƿ�Ϸ�
function Fn_validate(object, nullable, maxlength, msg)
{
    var obj = object.value;
    if (obj == null || obj == "" || obj.length == 0)
    {
        if (nullable == false)
        {
            object.focus();
            alert(msg + "����Ϊ�գ����������룡");
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
            alert(msg + "�������޶�����Ϊ" + maxlength + "���ֽڣ����������룡");
            return false;
        }
        return true;
    }
    return true;
}
// �ж��Ƿ�Ϊ��
function Fn_NullDate(object, msg) {
	if (object == null || object.value == "" || object.length == 0) {
        alert(msg + "����Ϊ�գ����������룡");
        return false;
	}
	return true;
}

//�ж��������ݴӳ������Ƿ�Ϸ������hidden���͵�component)
function Fn_validate_hidden(object, nullable, maxlength, msg)
{
    var obj = object.value;
    if (obj == null || obj == "" || obj.length == 0)
    {
        if (nullable == false)
        {
            alert(msg + "����Ϊ�գ����������룡");
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
            alert(msg + "�������޶�����Ϊ" + maxlength + "���ֽڣ����������룡");
            return false;
        }
        return true;
    }
    return true;
}
// �ж�¼�������Ƿ�Ϊ����
function Fn_Number(object, msg)
{
	//����Ƿ���ڷǷ��ַ�����
    var strTemp = "0123456789";
    var strValue = object.value;

    //����Ƿ���ڷǷ��ַ�
    for(i = 0;i < strValue.length;i++)
    {
		if (strTemp.indexOf(strValue.charAt(i)) == -1)
       	{
       		alert(msg + "���ڷǷ��ַ�����¼�����֣�");
            object.focus();
            object.select();
           	return false;
       	}
    }
    return true;
}

function is_Number(strValue)
{
	//����Ƿ���ڷǷ��ַ�����
    var strTemp = "0123456789";
    //����Ƿ���ڷǷ��ַ�
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
�ַ�������
Trim(string):ȥ���ַ������ߵĿո�
==================================================================
*/
/*
==================================================================
LTrim(string):ȥ����ߵĿո�
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
RTrim(string):ȥ���ұߵĿո�
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
Trim(string):ȥ��ǰ��ո�
==================================================================
*/
function Trim(str)
{
    return RTrim(LTrim(str));
}

/*
	��ʽ��2005-01-01 12:20
*/
function Fn_isDate(obj, nullable, msg)
{
    var strTimeArray;
    var boolLeapYear;//�Ƿ�������
    var i, j, strTemp, sp1, sp2, sp3, sp4;
    var year, month, day, hour, min;

	var date = obj.value;
    if ((date == null || date.length == 0) && nullable == true)  return true;
    if (date == null || date.length != 16)
    {
        alert(msg + "�����Ȳ����ϸ�ʽ����ʽ��2005-01-01 12:05");
        obj.focus();
        obj.select();
        return false;
    }

    strTemp = "0123456789-: ";
    for(i = 0;i < date.length;i++)//����Ƿ���ڷǷ��ַ�
    {
        j = strTemp.indexOf(date.charAt(i));
        if (j == -1)
        {
	        alert(msg + "�����ڷǷ��ַ�����ʽ��2005-01-01 12:05��");
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
        alert(msg + "�����Ȳ����ϸ�ʽ����ʽ��2005-01-01 12:05");
        obj.focus();
        obj.select();
        return false;
    }

    if (month > 12 || month < 1)
    {
        alert(msg + "��������·�Ӧ����1��12֮�䣡");
        obj.focus();
        obj.select();
        return false;
    }

    if ((month == 1 || month == 3 || month == 5 || month == 7 ||
         month == 8 || month == 10 || month == 12) && (day > 31 || day < 1))
    {
        alert(msg + month + "���µ�����Ӧ����1-31֮�䣡");
        obj.focus();
        obj.select();
        return false;
    }

    if ((month == 4 || month == 6 || month == 9 || month == 11) && (day > 30 || day < 1))
    {
        alert(msg + month + "���µ�����Ӧ����1-30֮�䣡");
        obj.focus();
        obj.select();
        return false;
    }

    if (month == 2)//2��
    {
        if (day < 1)
        {
            alert(msg + "��2�µ�����Ӧ�ô���1��");
	        obj.focus();
    	    obj.select();
            return false;
        }

        boolLeapYear = false;
        if ((year % 100) == 0)
        {
            if((year % 400) == 0)  boolLeapYear = true;//������
        }
        else
        {
            if((year % 4) == 0) boolLeapYear = true;//������
        }
        if(boolLeapYear)//������
        {
            if(day > 29)
            {
                alert(msg + "������2�µ�����Ӧ����1-29֮�䣡");
    	    	obj.focus();
	    	    obj.select();
                return false;
            }
        }//������
        else//������
        {
            if(day > 28)
            {
                alert(msg + "��������2�µ�����Ӧ����1-28֮�䣡");
	    	    obj.focus();
    	    	obj.select();
                return false;
            }
        }//������
    }//2��

    if ((hour < 0) || (hour > 23))
    {
        alert(msg + "��СʱӦ����0-23֮�䣡");
        obj.focus();
        obj.select();
        return false;
    }
    if ((min < 0) || (min > 59))
    {
        alert(msg + "������Ӧ����0-59֮�䣡");
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
        alert("�밴����ȷ��ʽ����ͳ�����£�������200501��");
        object.focus();
        object.select();
        return false;
    }

    var strTemp = "0123456789";
    //����Ƿ���ڷǷ��ַ�
    for(i = 0;i < str.length;i++)
    {
		if (strTemp.indexOf(str.charAt(i)) == -1)
       	{
	        alert("�밴����ȷ��ʽ����ͳ�����£�������200501��");
            object.focus();
            object.select();
           	return false;
       	}
    }

    year = parseInt(str.substring(0, 4), 10);
    month = parseInt(str.substring(4, 6), 10);

    if (year < 1900 || year > 2050)
    {
        alert("���������ͳ���꣡��1900-2050��");
        object.focus();
        object.select();
        return false;
    }
    if (month < 1 || month > 12)
    {
        alert("���������ͳ���·ݣ���01-12��");
        object.focus();
        object.select();
        return false;
    }
    return true;
}

function Fn_isDateString(date, nullable, msg)
{
    var strTimeArray;
    var boolLeapYear;//�Ƿ�������
    var i, j, strTemp, sp1, sp2, sp3, sp4;
    var year, month, day, hour, min;

    if ((date == null || date.length == 0) && nullable == true)  return true;
    if (date == null || date.length != 16)
    {
        alert(msg + "�����Ȳ����ϸ�ʽ����ʽ��2005-01-01 12:05");
        return false;
    }

    strTemp = "0123456789-: ";
    for(i = 0;i < date.length;i++)//����Ƿ���ڷǷ��ַ�
    {
        j = strTemp.indexOf(date.charAt(i));
        if (j == -1)
        {
	        alert(msg + "�����ڷǷ��ַ�����ʽ��2005-01-01 12:05��");
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
        alert(msg + "�����Ȳ����ϸ�ʽ����ʽ��2005-01-01 12:05");
        return false;
    }

    if (month > 12 || month < 1)
    {
        alert(msg + "��������·�Ӧ����1��12֮�䣡");
        return false;
    }

    if ((month == 1 || month == 3 || month == 5 || month == 7 ||
         month == 8 || month == 10 || month == 12) && (day > 31 || day < 1))
    {
        alert(msg + month + "���µ�����Ӧ����1-31֮�䣡");
        return false;
    }

    if ((month == 4 || month == 6 || month == 9 || month == 11) && (day > 30 || day < 1))
    {
        alert(msg + month + "���µ�����Ӧ����1-30֮�䣡");
        return false;
    }

    if (month == 2)//2��
    {
        if (day < 1)
        {
            alert(msg + "��2�µ�����Ӧ�ô���1��");
            return false;
        }

        boolLeapYear = false;
        if ((year % 100) == 0)
        {
            if((year % 400) == 0)  boolLeapYear = true;//������
        }
        else
        {
            if((year % 4) == 0) boolLeapYear = true;//������
        }
        if(boolLeapYear)//������
        {
            if(day > 29)
            {
                alert(msg + "������2�µ�����Ӧ����1-29֮�䣡");
                return false;
            }
        }//������
        else//������
        {
            if(day > 28)
            {
                alert(msg + "��������2�µ�����Ӧ����1-28֮�䣡");
                return false;
            }
        }//������
    }//2��

    if ((hour < 0) || (hour > 23))
    {
        alert(msg + "��СʱӦ����0-23֮�䣡");
        return false;
    }
    if ((min < 0) || (min > 59))
    {
        alert(msg + "������Ӧ����0-59֮�䣡");
        return false;
    }
    return true;
}

//function  : ��������ַ����Ƿ������ֻ����ֺ�С���㣩��ɣ�xxxxxx.xx��
//            ͨ�����ڼ������Ľ���Ƿ�Ϸ�
//parameters: obj���������������ֵΪ����鴮
//            minValue���������ֵ��½磻������С�ڸ�ֵ
//            maxValue���������ֵ��Ͻ磻�����ܴ��ڸ�ֵ
//            empty�����������������Ƿ����Ϊ�գ���ֵΪnullʱ�������������Ϊ��
//            totalLength�����ֵĳ������ƣ�������С���㣩
//	      decimalLength��С��λ�ĳ������ƣ������0����û��С��λ�������趨Ϊ2
//            msg����ʾ��Ϣ
//return    : false���ַ����а�������������ַ�
//	      true������������������
function Fn_IsNum(obj,minValue,maxValue,empty,totalLength,decimalLength,msg)
{
  var digitString=obj.value;
  var succeed=true;
  var alertStr="";  //��ʾ��Ϣ

  var havedecimalLength=true;
  var or_decimalLength=decimalLength;

  if(digitString.length==0){
    if(empty!=null){
      alertStr+="����ֵ����Ϊ!\n";
      alert(alertStr);
      return false;
    }else{
      return true;
    }
  }
  //��С�����ֳ���Ϊ0ʱ,��С��������Ϊnull������û��С��λ
  if(decimalLength=="0"){
    decimalLength=null;
    or_decimalLength=0;
  }else{
    decimalLength=2;
    or_decimalLength=2;
  }
  var re = "";
  //��С�����ֳ��Ȳ�Ϊnull��������ֵû��С���㣬��������ֵ���һ��С������00
  if(decimalLength != null && digitString.indexOf(".")<0)
  {
    digitString+=".00";
  }
  if (decimalLength != null)
  {   //С����Ϊ��
      re = re+"\\.[\\d]{1,"+ decimalLength +"}";
  }

  if (totalLength == null)
  {   //δ�趨�ܳ���
      re= "\\d*"+re;
  }
  else
  {   //�趨�ܳ���
    //��С������Ϊ�յ�����£������ж����ֵĳ���
    var intergerLength = totalLength;
    if (decimalLength!=null)
    {
      //��С�����ֲ�Ϊ���жϣ�������λ����С����λ���Ƿ���ȷ
      intergerLength = totalLength-decimalLength;
    }
    re="[\\d]{1,"+ intergerLength +"}"+re;//�������С���㿪ʼ
  }
  re = new RegExp("^(-){0,1}"+re+"$","g");
  //���ַ���Ϊ�ա�λ�����ԡ�����ƥ�������ʱ
  if(re.exec(digitString) == null)
  {
    alertStr+="�������ֵ����Ϊ����!\n";
    if(totalLength!=null) alertStr+="���ֵ��ܳ���Ϊ"+totalLength+"λ!\n";
    if(havedecimalLength)
    {
      alertStr+="С�����ĳ���Ϊ"+or_decimalLength+"λ!\n";
    }
    succeed=false;
  }
  //��ָ���ַ�����Χʱ �ַ�������ָ����Χ��
  if(maxValue!=null){
    if(parseFloat(digitString)>parseFloat(maxValue)){
      alertStr+="���ֲ��ܴ���"+maxValue+"\n";
      succeed= false;
    }
  }
  if(minValue!=null){
    if(parseFloat(digitString)<parseFloat(minValue)){
      alertStr+="���ֲ���С��"+minValue+"\n";
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
