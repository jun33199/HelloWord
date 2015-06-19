//�жϸ��ַ����Ƿ����ַ����
function IsAlpha(str)
	{

		if(str.length==0) return false;
	    for(var loc=0;loc<str.length;loc++)
		   if((('a'<=str.chatAt(loc)) && (str.chatAt(loc)<='z')) || (('A'<=str.chatAt(loc)) && (str.chatAt(loc)<='Z')))
		      return false;
        return true;
	}

//�жϸ��ַ����Ƿ�Ϊ����,������0-9����
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

//�жϸ��ַ����Ƿ�Ϊ������������Ϊ��С�������
function isNumber(str)
{
	if(!Number(str))
		return false;
	return true;
}

//�ж�һ�����Ƿ�Ϊ������
//������strNum ---- ��Ҫ�жϵ��ַ���
//����ֵ��true ---- ���� false ---- ������
function fnIsIntNum(strNum)
{
 var strCheckNum = strNum + "";

// var c = strCheckNum.charAt(0);
// if (c!="1" || c!="2" || c!="3" || c!="4" || c!="5" || c!="6" || c!="7" || c!="8" || c!="9")
// {
//	return false;
// }

 if(strCheckNum.length < 1)         //���ַ���
  return false;
 else if(isNaN(strCheckNum))         //������ֵ
  return false;
 else if(parseInt(strCheckNum) < 1)       //��������
  return false;
 else if(parseFloat(strCheckNum) > parseInt(strCheckNum)) //��������
  return false;

 return true;
}


//ɾ���ַ���ǰ��Ŀո�
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

//����ֵ��intYear��intMonth�µ�����
function fnComputerDay(intYear,intMonth)
{
    var dtmDate = new Date(intYear,intMonth,-1);
    var intDay = dtmDate.getDate() + 1;

    return intDay;
}

//�ж�year month,day�Ƿ������������
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


 //�ж�email�Ƿ�Ϸ�

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
//���ܣ����ڼ�麯����֧��3���ꡢ�¡���֮��ķָ��� "-"��"."��"/"����ѡ���ꡢ�¡����Ƿ�Ӧ��������
//  ��ȷ�����ڸ�ʽΪ��2001-2-13 2001 2001-2 2001.2.13  2001.2 2001/2/3�����ڷ�ΧΪ 1-1-1 �� 9999-12-31
//  ͬʱ���Ե�ǰ�굱ǰ�µ�����Ҳ�����жϣ��磺2001-2-29 2001-4-31 ���ǷǷ�������
//������strDate ---- ��Ҫ�жϵ������ַ���
//  intFlag: 1 ---- ����û����  2 ---- ����û���պ��� 0 ---- �����ձ�����ȫ
//����ֵ��true ---- ���ںϷ� false ---- ���ڲ��Ϸ�
function fnCheckDate(strDate,intFlag)
{
 var strCheckDate = strDate + "";     //��һ��ȷ�������жϵĿ϶���һ���ַ���

 if(strCheckDate == "")        //���ַ���,���ǺϷ��������ַ���������false
 {
  return false;
 }

 //�жϴ����������������ָ�ʽд������
 var intIndex = -1;         //����������ʽ�������ַ������Ƿ����ĳ���ַ���û�ҵ�Ϊ-1,����Ϊ ��0 - String.length - 1��
 var arrDate;          //�ֱ�洢������
 var regExpInfo = /\./;        //������ʽ��ƥ���һ������ "."��λ��

 //�������֮���Բ�ʹ��replace���������е�"."��"/"����"-",Ȼ��ֱ�洢�����գ�����Ϊ�û��п������� 2001/3-2,���жϲ������ǲ��Ϸ�������
 intIndex = strCheckDate.search(regExpInfo);   //�����Ƿ��� "."
 if(intIndex == - 1)         //������
 {
  regExpInfo = /-/;
  intIndex = strCheckDate.search(regExpInfo);

  if(intIndex == -1)
  {
   regExpInfo = /\//;       //�����Ƿ��� "/"
   intIndex = strCheckDate.search(regExpInfo);

   if(intIndex == -1)
   {
    arrDate = new Array(strCheckDate);  //ֻ������
   }
   else
   {
    arrDate = strCheckDate.split("/");  //2001/3/7 ��
   }
  }
  else
  {
   arrDate = strCheckDate.split("-");   //2001-3-7 ��
  }
 }
 else
 {
  arrDate = strCheckDate.split(".");    //2001.3.7 ��
 }

 if(arrDate.length > 3)        //���������������3�����������ջ��������ģ����Ϸ����ڣ�����false
 {
  return false;
 }
 else if(arrDate.length > 0)
 {
  //�ж����Ƿ�Ϸ�
  if(fnIsIntNum(arrDate[0]))   //��������
  {
   if(parseInt(arrDate[0]) < 1 || parseInt(arrDate[0]) > 9999)  //�귶ΧΪ1 - 9999
   {
    return false;
   }
  }
  else
  {
   return false;     //�겻��������������
  }

  //�ж����Ƿ�Ϸ�
  if(arrDate.length > 1)
  {
   if(fnIsIntNum(arrDate[1]))  //��������
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
  else //û����
  {
   if(intFlag != 2)    //���������
   {
    return false;
   }
  }

  //�ж����Ƿ�Ϸ�
  if(arrDate.length > 2)
  {
   if(fnIsIntNum(arrDate[2]))  //��������
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
   if(intFlag == 0)    //���������
   {
    return false;
   }
  }
 }
 return true;
}


//�ж�
//���� obj ��Ҫ����ֵ�Ķ���
//���� par
//			0 -- ���ֵ���Ϸ����Ϊ0
//			1 -- ��У���Ƿ�С��0
//			2 -- ���ܴ���100
//			3 -- ���ܴ���1
//ע��trim()�����Ѱ����ڹ��ú�������
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
					alert("����ֵ����");
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
					alert("����ֵ����");
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
					alert("����ֵ����");
					obj.focus();
					obj.select();
					return false;
				}
				else
				{
					if(parseFloat(tmpValue) <= 0 || parseFloat(tmpValue) > 100)
					{
						alert("������(0��100]֮������֣�");
						obj.focus();
						obj.select();
						return false;
					}
				}
				break;
			case 3:
				if(tmpValue == null || isNaN(tmpValue) || tmpValue < 0)
				{
					alert("����ֵ����");
					obj.focus();
					obj.select();
					return false;
				}
				else
				{
					if(parseFloat(tmpValue) <= 0 || parseFloat(tmpValue) >= 1 || tmpValue.length > 3)
					{
						alert("������(0��1)֮�䲢��С������ܳ���2λ��С����");
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
				alert("����ֵ����Ϊ0��");
				obj.focus();
				obj.select();
				return false;
		}
	}
	//obj.value = tmp;
	//return obj;
	return true;
}

// ���ַ�����ʽ��Ϊ���Ҹ�ʽ
// parΪ0���Զ����0.00
// parΪ1�����Զ����
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
				alert("С������ܴ�����λ��");
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
			alert("С������ܴ�����λ��");
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
		alert("����������ֱ���С��10000000000000��");
		obj.focus();
		obj.select();
		return false;
	}
	obj.value = tmpValue;
	//return obj;
	return true;
}

//function  : ���������ַ����Ƿ����ת��ΪDate����
//Parameters: strData:String
//return    : true: ͨ�����
//			  false: δͨ����顣
function isDate(obj,empty)
{
	if(window.event.keyCode!=13 && window.event.keyCode!=0 && obj==window.event.srcElement) return true;

	var strDate=obj.value;
	var succeed=true;
	var alertStr="";

	if(strDate.length==0){
		if(empty!=null){
			alertStr+="����ֵ���벻Ϊ��!\n"
			succeed=false;
		}
	}
	if (strDate.length!=8 && succeed) {
				alertStr+="���ڸ�ʽ����ȷ��ӦΪYYYYMMDD��ʽ!\n"
				succeed=false;
	}
	var strYear = strDate.substr(0,4);
	var strMonth = strDate.substr(4,2);
	var strDay = strDate.substr(6,2);
	var objDate = new Date(strMonth+'-'+strDay+'-'+strYear);
	if (isNaN(objDate) && succeed){
				alertStr+="���ڸ�ʽ����ȷ!\n"
				succeed=false;
	}
    if(strYear*1<1900 && succeed) {
				alertStr+="���ڸ�ʽ����ȷ!\n"
				succeed=false;
	}
    if ((strYear*1 != objDate.getYear()*1)&&(strYear*1 != objDate.getYear()*1+1900) && succeed) {
				alertStr+="���ڸ�ʽ����ȷ!\n"
				succeed=false;
	}
    if (strMonth*1 != objDate.getMonth()*1+1 && succeed){
				alertStr+="���ڸ�ʽ����ȷ!\n"
				succeed=false;
	}
    if (strDay*1 != objDate.getDate()*1 && succeed) {
				alertStr+="���ڸ�ʽ����ȷ!\n"
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

//function  : ��������ַ����Ƿ����������
//parameters: DigitString: String,������ַ�����ֵ
//			  totalLength: int, ���ֵĳ������ƣ�������С���㣩
//			  decimalLength: int��С���ĳ�������
//            totalLength-decimalLength �������ֵ�����
//return    : false: �ַ����а�������������ַ�
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
				alertStr+="����ֵ���벻Ϊ��!\n"
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
		//��С�����ֳ���Ϊ0ʱ ��С��������Ϊnull
		if(decimalLength=="0"){
			decimalLength=null;
			or_decimalLength=0;
			}
		//��С�����ֳ��Ȳ�Ϊnull ���û��С������С������00
		if(decimalLength!=null){if(DigitString.indexOf(".")<0) DigitString+=".00";}
    if (decimalLength!=null)
    {   //С����Ϊ��
        re = re+"\\.[\\d]{1,"+ decimalLength +"}"
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
        {   //��С�����ֲ�Ϊ���жϣ�������λ����С����λ���Ƿ���ȷ
            intergerLength = totalLength-decimalLength;
        }
        //re="[\\d]{0,"+ intergerLength +"}"+re;
		re="[\\d]{1,"+ intergerLength +"}"+re;//�������С���㿪ʼ
    }
    re = new RegExp("^(-){0,1}"+re+"$","g");//���ֻ� modify 2003/11/11 �����жϸ�����

    //���ַ���Ϊ�ա�λ�����ԡ�����ƥ�������ʱ
		//var meetRequest=true;
    if(re.exec(DigitString) == null)
    {
			  alertStr+="�������ֵ����Ϊ����!\n"
				if(totalLength!=null) alertStr+="���ֵ��ܳ���Ϊ"+totalLength+"λ!\n";
				if(havedecimalLength) {
					alertStr+="С�����ĳ���Ϊ"+or_decimalLength+"λ!\n";
				}
        succeed=false;
    }
		//��ָ���ַ�����Χʱ �ַ�������ָ����Χ��
		if(maxValue!=null){
			if(parseFloat(DigitString)>parseFloat(maxValue)){
				alertStr+="���ֲ�����"+maxValue+"\n";
				succeed= false;
			}
		}
		if(minValue!=null){
			if(parseFloat(DigitString)<parseFloat(minValue)){
				alertStr+="���ֲ�С��"+minValue+"\n";
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
 * ��ʽ����������ΪС�������λ
 * 
 * @param �ı���
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
 * ��¼������ָ��Ի�Ϊ�ٷ�����ʽ
 *     �磺25 --> 25%
 * @param �ı���
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
 * ��С����ʽΪ�ٷ�����ʽ
 *     �磺0.25 --> 25%
 * @param �ı���
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
 * ��ʽ���ٷ�����ΪС��
 * 
 * @param �ı���
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

var confirmSave = "�Ƿ񱣴��걨����?";
var confirmSaveZero = "�������У����ݣ��Ƿ�Ҫ�����걨���ϣ�";
var confirmDelete = "�Ƿ�ɾ���걨����?";
var qysdsconfirmDelete = "�ò�����ɾ����ҵ��������ָ����������������ҵ��λ��������������걨����������Ҳ��ͬʱɾ����\nȷ��ɾ���걨����?";
var confirmReturn = "�Ƿ񷵻�?";