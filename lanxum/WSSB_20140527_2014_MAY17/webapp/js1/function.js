//��ż��㹫ʽ������ ÿ��ҳ��Ҫ���¸�ֵ
var mathArray =new Array();
//���ͬ����ֵ�ϼƵ����� ÿ��ҳ��Ҫ���¸�ֵ
var hjArray =new Array();
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
	//if(!Number(str))
	if(isNaN(Number(str)))
		return false;
	return true;
}

//�ж�һ�����Ƿ�Ϊ������
//������strNum ---- ��Ҫ�жϵ��ַ���
//����ֵ��true ---- ���� false ---- ������
function fnIsIntNum(strNum)
{
 var strCheckNum = strNum + "";
 if(strCheckNum.length < 1)         //���ַ���
  return false;
 else if(isNaN( Number(strCheckNum) ))         //������ֵ
  return false;
 else if(parseInt( Number(strCheckNum) ) < 1)       //��������
  return false;
 else if(parseFloat(strCheckNum) > parseInt(strCheckNum)) //��������
  return false;

 return true;
}


///�涨��ֵ�ĳ��� ��û�дﵽ���ȵ���ֵǰ��0
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
			if(strChr!=' ' && strChr!='��')//Ӣ�ĵĿո�ͺ��ֵĿո�
				break;
		}
		for(iLoop2=strInput.length-1;iLoop2>=0;iLoop2--)
		{
			strChr=strInput.charAt(iLoop2);
			if(strChr!=' ' && strChr!='��')//Ӣ�ĵĿո�ͺ��ֵĿո�
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

/*
* Notes: ���ݱ��Ԫ�����ṩ�Ĺ�ʽ��������
* Version: 0.9.50
* Author: GuoXH
* LastModDate: 2003/10/29
* Parames:
* 		obj ���Ԫ
* 		strItem ���㹫ʽ�еĵ�һ����������ַ��������Ⱥź���ַ���
* 		kind nullʱ�����걨����㣻��Ϊnullʱ������Row����
*/
function computeFunction(obj,strItem,kind){

	if(kind){//����Row����
		//����smsb_common.js����ȡ����ж���
		var oRow = getObjRow(obj);

		var stack = new Stack();
		//ѭ��MathString����,���ҵ�ƥ��Ĺ�ʽ
		for(var i=0;i<mathArray.length;i++)
		{
			if(mathArray[i].isInExpress(strItem))
			{
			  //����
			  var retBolan = mathArray[i].compute(oRow);
			  if(retBolan != null)
			  {
				  var id = retBolan.operator+"";
				  var v = retBolan.value;
				  //��ֵ
				  setRowValue(oRow,id,v);
				  stack.push(retBolan.operator);
			  }
			}
		}//end [for]
	}else{//nullʱ�����걨�����
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
		//2003-10-29 ����һ�´��룬�Ա������ʽ
		while(stack.length() > 0){
		  var nHc = stack.pop();
		  computeFunction(obj,nHc,kind);
		}
	}//end [if-else][null]
}

/*
 *����ϼ� �ŷ���
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
* Notes: ����ͬһ����Ԫ�ϼƣ�Ҫ������ͬ��ID or Name
* Version: 0.9.00
* Author: GuoXH
* LastModDate: 2003/10/16
* Parames:
* 		addFrmItem ��������ı����ID or Name
* 		resultFrmItem �����ID or Name
*     tableId ����ID ���� ���������ҳ���ϵ�����ͬ����ֵ
*/
function computeSameSum(addFrmItem,resultFrmItem,tableId){

//alert("addFrmItem :"+addFrmItem +"resultFrmItem :"+resultFrmItem+"tableId :"+tableId )
    //������
	var oTab=document;
	if(tableId!=null)
		{
		oTab=eval("document.all('"+tableId+"')");
		}

	//������ı��Ԫ����
	var addItem = eval("oTab.all('"+addFrmItem+"')");
	//������ı��Ԫ������
	var length = getLength(addItem);
	//���
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
				// �ж���
			   for (var i=addItem.length-1; i>=0; i--)
			   {
					result_sum += parseFloatPro(addItem[i].value);
			   }
			}
		}

    }
	//��ֵ
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
        // ֻ��һ��
        return -1;
    }
    else
    {
        // ֻ��һ�У�������
        if(kssl.length == 1)
        {
            return 1;
        }

        return kssl.length;
    }
}


/*
* Notes: �������ֵ
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
* Notes: ��ֵ��ౣ����λ
*/
function round(value){
    var temp = value * 100;
    temp = Math.round(temp);
    return temp/100;
}
/*
* ת�����������ֽ�Ǯ�����Ľ�Ǯ��
*/
var NUM = ["��","Ҽ","��","��","��","��","½","��","��","��"];

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
	/**�������ֵĵ�λ*/
	var chineseInteger = "";
	var IUNIT = ["Ԫ","ʰ","��","Ǫ","��","ʰ","��","Ǫ","��","ʰ","��","Ǫ","��","ʰ","��","Ǫ"];
	var isMust5 = getIsMust5(integerStr);
	var integerArray = num2Array(integerStr);
	var ILength = integerArray.length;
	var key = "";
	for(var ii=0; ii<ILength; ii++){
		if(integerArray[ii]==0){
			//��(��)(����)
			if((ILength-ii)==13)	key = IUNIT[4];
			//��(����)
			else if((ILength-ii)==9) key = IUNIT[8];
			//��(������)
			else if((ILength-ii)==5 && isMust5) key = IUNIT[4];
			//Ԫ(����)
			else if((ILength-ii)==1) key = IUNIT[0];

			//0����0ʱ���㣬���������һλ
			if((ILength-ii)>1 && integerArray[ii+1]!=0) key += NUM[0];
			//�������
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
	var DUNIT = ["��","��","��"];
	var decimalArray = num2Array(decimalStr);
	var chineseDecimal = "";

	for(var ii=0; ii<decimalArray.length; ii++){
		//��ȥ3λС��֮���
		if(ii==3) break;
		chineseDecimal += (decimalArray[ii]==0?"":(NUM[decimalArray[ii]]+DUNIT[ii]));
	}//end for
	return chineseDecimal ;
}

function getIsMust5(integerStr) {
	//ȡ�ôӵ�λ������5����8λ���ִ�
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
* Notes: ��ȡ��ǰ���ڵ���һ��/��/������ֹ���ڡ�
* Version: 0.9.00
* Author: Guoxh
* Parames: flag 1,Last Year;2,Last Month;Ĭ�ϣ��������
*/
function getStartEndDate(oInput1,oInput2,oInput3,flag){
	var date_start,date_end;
	var year,mon,days;
	var strMon;

	var inputDate = oInput1.value;

	//�Ƿ�Ϸ�����
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
* Notes: ��ȡ�ۼ��ͼ����걨����ʱ��ļ��㣬�������걨�������ڣ�Ӧ���ǵ�ǰ����������ģ��£�������ǰ���ڵ��µ��ϸ��µ���ĩ��
*		�����������1�·ݵ����ڣ������������ھ�����һ��1��1��-12��31��
* Version: 0.9.00
* Author: Guoxh
* Parames: flag 1,�ۼ��ͼ����걨����ʱ��
*/
function getTotalDate(oInput1,oInput2,oInput3,flag){
	var date_start,date_end;
	var year,mon,days;

	var inputDate = oInput1.value;

	//�Ƿ�Ϸ�����
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

//������λ
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
* Notes: �����������֤��
* Parameters: IdentityCardNumber:string,���������֤��;
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
			err = "���֤���벻����Ϊ�գ�";
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
    //�����������Ƿ���ϸ�ʽҪ�� 15λ��YYMMDD  18λ:YYYYMMDD
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
		err = "���ǺϷ������֤���룡";
		alert(err);
		return false;
	}
    return true;
}

/**
* Notes: �෽��������ڡ�
* Version: 0.9.00
* Parames: strDate string ����ֵ
*          dateKind int �������ͣ�
*                   0��8λ������,eg:20030609
*                   1��4λ��,eg:2003
*          empty string/int/null �Ƿ������
*          isThrow boolean �Ƿ��ӳ�������Ϣ
**/
function isFullDate(strDate,dateKind,empty,isThrow){
	var year,mon,days;
	var err = "";

	if(dateKind==0){//����8λ������
		err = "";

		if(strDate.length==0){
			  if(empty!=null){
					err="���ڱ��벻Ϊ��!\n";
				}
		}else{
			if(strDate.length!=8){
				err="���ڸ�ʽ����ȷ��������8λ����!\n";
			}else{
				var strYear = strDate.substr(0,4);
				var strMonth = strDate.substr(4,2);
				var strDay = strDate.substr(6,2);
				var objDate = new Date(strMonth+'-'+strDay+'-'+strYear);
				if (isNaN(objDate)){
					err="���ڸ�ʽ����ȷ!\n"
				}
				if(strYear*1<1900) {
					err="���ڸ�ʽ����ȷ!\n"
				}
				if ((strYear*1 != objDate.getYear()*1)&&(strYear*1 != objDate.getYear()*1+1900)) {
					err="���ڸ�ʽ����ȷ!\n"
				}
				if (strMonth*1 != objDate.getMonth()*1+1){
					err="���ڸ�ʽ����ȷ!\n"
				}
				if (strDay*1 != objDate.getDate()*1) {
					err="���ڸ�ʽ����ȷ!\n"
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
	}else if(dateKind==1){//4λ��
		err = "";
		if((strDate.charAt(0)=="0") || strDate.length!=4 || (!isDigit(strDate))){
			err = "���ǺϷ�����ݣ�";
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
 * �����ж�С��λ��λ���Ƿ����ĳ��ָ��ֵ
 * @input text
 * @input point
 * @output text��С��λС��point����TRUE,��֮����FALSE
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


//�Ա��������ڣ�Ҫ��2����1
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
		alert("����δ�ҵ�");
	}
}

/**
* Notes: �Ƚ���ֹ����(���ڸ�ʽ��yyyymmdd)
* Parames: item1 ��ʼ���ڵ����ƻ�ID
*          item2 ��ֹ���ڵ����ƻ�ID
*          kind ����¼������ʽ
*               0���� document.all(item)
*               1���� row.all(item)
*          obj ��ǰ¼������
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
			errMsg = "��ʼ���ڲ�Ӧ�ô�����ֹ����!";
			alert(errMsg);
			return false;
		}else{
			return true;
		}
	}

}
//��������ʱ��֮�����20031010-20031001=9
//�����ʽΪ20031010
//s��ʼ����,e��ֹ����
function getBDays(s,e){
	//�õ���ʼ��������
	var sy = s.substring(0,4);
	var sm = parseInt(s.substring(4,6))-1;
	var sd = s.substring(6,8);
	//�������ڶ���
	var sdate = new Date(sy,sm,sd);
	//�õ���ֹ��������
	var ey = e.substring(0,4);
	var em = parseInt(e.substring(4,6))-1;
	var ed = e.substring(6,8);
	//�������ڶ���
	var edate = new Date(ey,em,ed);
	//�õ���������֮�������
	var ms=edate.getTime()-sdate.getTime();

	//����һ���ж�����
	var MinMilli = 1000 * 60;
	var HrMilli = MinMilli * 60;
	var DyMilli = HrMilli * 24;
	var days = Math.round(ms/DyMilli);
	return days;
}
//������������֮����·�
function getBMonths(s,e){
	//�õ���ʼ������
	var sy = s.substring(0,4);
	var sm = s.substring(4,6);
	//�õ���ֹ��������
	var ey = e.substring(0,4);
	var em = e.substring(4,6);
	//������
	var ys= ey-sy;
	//���������
	//var ms= parseInt(em)-parseInt(sm);
	var ms = em - sm;
	var months=ms+ys*12;
	return months;

}

/*
* Notes: ��ȡָ���·ݵ�����
* Parames:
* 		intMonth �� 1 ��ʼ
*/
function CL_getDays(intYear,intMonth){
    var dtmDate = new Date(intYear,intMonth,-1);
    var intDay = dtmDate.getDate() + 1;

    return intDay;
}
//�õ����µ�����
function getDaysOfMonth(d){
	//�õ���ʼ������
	var y = d.substring(0,4);
	var m = d.substring(4,6);
	var intDay  = CL_getDays(y,m);
	return intDay;
}


//���ڲ����걨��ҳ��˰�������������걨���ڵĹ���
//item0Ϊ�걨���ڣ�item1Ϊ˰�ʼ���ڣ�item2Ϊ˰���������
//kindΪ kind ����¼������ʽ0���� document.all(item)�� 1���� row.all(item)��obj ��ǰ¼������
//sklxΪ˰������0���걨 1������ 2���±�
//skssrq=0Ϊ˰�ʼ���ڣ�1Ϊ˰���������
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
			errMsg = "��ʼ���ڲ�Ӧ�ô�����ֹ����!";
			flag = 1;
		}else{
                objYear = inputDate.substring(0,4);
	        objMon = inputDate.substring(4,6);
	        sbYear = sbDateValue.substring(0,4);
                sbMon = sbDateValue.substring(4,6);
	        //�걨
	        if(sklx==0){
                   //˰�ʼ���ڻ��ǽ����������걨���ڵıȽ�
                   var result = parseFloat(sbYear) - parseFloat(objYear);
                   //Ҫ����һ���
                   if(result!=1)
                   {  if(skssrq==0)
                      {errMsg = "�걨��˰�ʼ���ڵ���ݲ������걨�Ĺ���!";}
				     else
                      {errMsg = "�걨��˰��������ڵ���ݲ������걨�Ĺ���!";}
                      flag = 1;
                    }
                   if(skssrq==0){
                    if(objMon!="01")
                    {errMsg = "�걨��˰�ʼ���ڵ��·ݲ������걨�Ĺ���!";
                     flag = 1;
                    }
                   }else
                   {
                    if(objMon!="12")
                    {errMsg = "�걨��˰��������ڵ��·ݲ������걨�Ĺ���!";
                     flag = 1;
                    }
                   }
	         }
	         //�ۼ��ͼ���
	         if(sklx==1){
                  var ldResult = parseFloat(sbYear) - parseFloat(objYear);
//                  var ldMonth =  parseFloat(sbMon) - parseFloat(objMon);
                  if(sbMon=="01"){
                      if(ldResult!=1)
                      {
                        if(skssrq==0)
                        {errMsg = "�ۼ��ͼ�����˰�ʼ���ڵ���ݲ������ۼ��ͼ����Ĺ���!";}
                        else
                        {errMsg = "�ۼ��ͼ�����˰��������ڵ���ݲ������ۼ��ͼ����Ĺ���!";}
                        flag = 1;
                      }
                      if(skssrq==0)
                      {
                        if(objMon!='01')
                        { errMsg = "�ۼ��ͼ�����˰�ʼ���ڵ��·ݲ������ۼ��ͼ����Ĺ���!";
                          flag = 1;
                        }
                      }else{
                        if(objMon!='12')
                        { errMsg = "�ۼ��ͼ�����˰��������ڵ��·ݲ������ۼ��ͼ����Ĺ���!";
                          flag = 1;
                        }
                       }
                     }else{
                       var monResult = parseFloat(sbMon) - parseFloat(objMon);
                       if(skssrq==1){
                       if(monResult!=1)
                       {
                        errMsg = "�ۼ��ͼ�����˰��������ڵ��·ݲ������ۼ��ͼ�������!";
                        flag = 1;
                       }
                       }else{
                        if(objMon!="01")
                       {
                         errMsg = "�ۼ��ͼ�����˰�ʼ���ڵ��·ݲ������ۼ��ͼ�������!";
                         flag = 1;
                       }
                     }
	            }
	         }
                //�±��Ƚ�
                if(sklx==2){
                  var ydResult = parseFloat(sbYear) - parseFloat(objYear);
                  var ydMonth =  parseFloat(sbMon) - parseFloat(objMon);
                  //�걨����Ϊ1�·ݵ��ж�
                  if(sbMon=="01"){
                     if(ydResult!=1)
                      {
                        if(skssrq==0)
					    {errMsg = "�±���˰�ʼ���ڵ���ݲ������±��Ĺ���!";}
				     else
                        {errMsg = "�±���˰��������ڵ���ݲ������±��Ĺ���!";}
                       flag = 1;
                      }

                     if(objMon!="12")
                      {
                        if(skssrq==0)
					    {errMsg = "�±���˰�ʼ���ڵ��·ݲ������±��Ĺ���!";}
				        else
                        {errMsg = "�±���˰��������ڵ��·ݲ������±��Ĺ���!";}
                       flag = 1;
                       }
                  }else{//�����·ݵ��ж�
                      if(ydResult!=0)
                      {
                        if(skssrq==0)
					    {errMsg = "�±���˰�ʼ���ڵ���ݲ������±��Ĺ���!";}
				       else
                        {errMsg = "�±���˰��������ڵ���ݲ������±��Ĺ���!";}
                       flag = 1;
                      }
                      if(ydMonth!=1)
                      {
                        if(skssrq==0)
					    {errMsg = "�±���˰�ʼ���ڵ��·ݲ������±��Ĺ���!";}
				        else
                        {errMsg = "�±���˰��������ڵ��·ݲ������±��Ĺ���!";}
                       flag = 1;
                      }
                  }
              }
                  //��ͨ����
                  if(sklx==3){
                  var pjbResult = parseFloat(sbYear) - parseFloat(objYear);
                  var pjbMonth =  parseFloat(sbMon) - parseFloat(objMon);
                   if(sbMon=="01"||sbMon=="02"||sbMon=="03"){
                     if(pjbResult!=1)
                      {
                        if(skssrq==0)
                        {errMsg = "��ͨ������˰�ʼ���ڵ���ݲ�������ͨ�����Ĺ���!";}
				     else
                        {errMsg = "��ͨ������˰��������ڵ���ݲ�������ͨ�����Ĺ���!";}
                       flag = 1;
                      }
                        if(skssrq==0)
                        { if(objMon!='10')
                          {
                           errMsg = "��ͨ������˰�ʼ���ڵ��·ݲ�������ͨ�����Ĺ���!";
                           flag = 1;
                          }
                        }else
                        { if(objMon!='12')
                          {
                           errMsg = "��ͨ������˰��������ڵ��·ݲ�������ͨ�����Ĺ���!";
                           flag = 1;
                          }
                        }

                     }
                     if(sbMon=="04"||sbMon=="05"||sbMon=="06"){
                     if(pjbResult!=0)
                      {
                        if(skssrq==0)
                        {errMsg = "��ͨ������˰�ʼ���ڵ���ݲ�������ͨ�����Ĺ���!";}
				     else
                        {errMsg = "��ͨ������˰��������ڵ���ݲ�������ͨ�����Ĺ���!";}
                       flag = 1;
                      }
                        if(skssrq==0)
                        { if(objMon!='01')
                          {
                           errMsg = "��ͨ������˰�ʼ���ڵ��·ݲ�������ͨ�����Ĺ���!";
                           flag = 1;
                          }
                        }else
                        { if(objMon!='03')
                          {
                           errMsg = "��ͨ������˰��������ڵ��·ݲ�������ͨ�����Ĺ���!";
                           flag = 1;
                          }
                        }

                     }
                     if(sbMon=="07"||sbMon=="08"||sbMon=="09"){
                     if(pjbResult!=0)
                      {
                        if(skssrq==0)
                        {errMsg = "��ͨ������˰�ʼ���ڵ���ݲ�������ͨ�����Ĺ���!";}
				     else
                        {errMsg = "��ͨ������˰��������ڵ���ݲ�������ͨ�����Ĺ���!";}
                       flag = 1;
                      }
                        if(skssrq==0)
                        { if(objMon!='04')
                          {
                           errMsg = "��ͨ������˰�ʼ���ڵ��·ݲ�������ͨ�����Ĺ���!";
                           flag = 1;
                          }
                        }else
                        { if(objMon!='06')
                          {
                           errMsg = "��ͨ������˰��������ڵ��·ݲ�������ͨ�����Ĺ���!";
                           flag = 1;
                          }
                        }

                     }
                      if(sbMon=="10"||sbMon=="11"||sbMon=="12"){
                     if(pjbResult!=0)
                      {
                        if(skssrq==0)
                        {errMsg = "��ͨ������˰�ʼ���ڵ���ݲ�������ͨ�����Ĺ���!";}
				     else
                        {errMsg = "��ͨ������˰��������ڵ���ݲ�������ͨ�����Ĺ���!";}
                       flag = 1;
                      }
                        if(skssrq==0)
                        { if(objMon!='07')
                          {
                           errMsg = "��ͨ������˰�ʼ���ڵ��·ݲ�������ͨ�����Ĺ���!";
                           flag = 1;
                          }
                        }else
                        { if(objMon!='09')
                          {
                           errMsg = "��ͨ������˰��������ڵ��·ݲ�������ͨ�����Ĺ���!";
                           flag = 1;
                          }
                        }

                     }
                  }
        }
  }
  //���������±���ͨ���걨���������˰���������ڷ�Χ
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

/**
* Notes: �Ƚ���ֹ����(���ڸ�ʽ��yyyy-mm-dd)
* Parames: ksrq ��ʼ����
*          jzrq ��ֹ����
* Author: Guoxh
* Version: 0.9.00
*/
function checkDate(ksrq,jzrq){
		d1Arr=ksrq.split('-');
		d2Arr=jzrq.split('-');
		v1=new Date(d1Arr[0],d1Arr[1],d1Arr[2]);
		v2=new Date(d2Arr[0],d2Arr[1],d2Arr[2]);
		if(v1>v2){
			alert("��ʼ���ڲ���������ֹ����");
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