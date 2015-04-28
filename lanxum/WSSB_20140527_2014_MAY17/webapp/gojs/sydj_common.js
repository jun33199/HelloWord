//�ж��ַ��������е��ַ��Ƿ�����Ч�Ը�
//strValue �������ַ���(Required)
//charList ָ������Ч�ַ�����(Required)
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

//function  : �жϰ����Ƿ�Ϊ��
//Parameters: object, ���������
//return    : false: δ��ֵ
//			  true : ��ֵ
function checkNull(object){
  if(object.value==null || object.value==""){
      return false
  }
  return true
}

//function  : �жϰ����Ƿ�Ϊ���ּ�
//Parameters: keyCode: ������keyCodeֵ
//return    : false: �����ּ�
//			  true : false
function isDigitKey(keyCode)
{
	//��onkeypress��onkeydown�¼��У�ͬһ����keyCode���ܲ�ͬ,������Ӧonkeydown�¼�
	if ((keyCode >= 48)&&(keyCode <= 57)) return true;
	if ((keyCode >= 96)&&(keyCode <=105)) return true;//С����
	return false;
}

//function  : �жϰ����Ƿ�ΪӢ���ַ�'a'-'z','A'-'Z'��
//Parameters: keyCode: ������keyCodeֵ
//return    : false������Ӣ���ַ�
//			  true:  ��
function isCharKey(keyCode)
{
	//��onkeypress��onkeydown�¼��У�ͬһ����keyCode���ܲ�ͬ,������Ӧonkeydown�¼�
	if ((keyCode >= 65)&&(keyCode <= 90)) return true;
	return false;
}

//function  : ��������ַ����Ƿ����������
//parameters: DigitString: String,������ַ�����ֵ
//			  totalLength: int, ���ֵ��ܳ�������
//			  decimalLength: int��С���ĳ�������
//            totalLength-decimalLength �������ֵ�����
//return    : false: �ַ����а�������������ַ�
//			  true : else
function isDigitString(DigitString, totalLength, decimalLength)
{
    var dotNum = 0;
    var total = 0;
    
    //���Ϊһ��С���㣬�򷵻�false
    if(trimString(DigitString)==".")
      return false;
    
    for(i=0;i<DigitString.length;i++)
    {
        if (DigitString.charAt(i)==".") {
            //����С�������
            ++dotNum;
            if (dotNum>1) {
                //����һ��С�����д���
                return false;
            }
        } else {
            if (isNaN(DigitString.charAt(i)*1)) {
                //�ж��Ƿ�������
                return false;
            }
            //��������ȫ����������С����
            total++;
        }
    }
    //����������С����
    if(decimalLength==null && dotNum>0){
        return false;
    }
    //�ж����ֳ����Ƿ�С�ڹ涨ֵ
    if (totalLength!=null && total>totalLength){ //δ�����ܳ��ȵĲ����ж�
        return false;
    }
    
    
    //�ж�С��λ���Ƿ�С�ڹ涨ֵ
    if (decimalLength!=null) { //δ����С�����ȵĲ����ж�
        var index = DigitString.indexOf(".");
        if (index>-1) {
            if (DigitString.substr(index+1).length>decimalLength
                || index>(totalLength-decimalLength)) {//�������ֵ�����
                return false;
            }
        }else if(total>totalLength-decimalLength) {//û��С����ʱ�ĸ�������������
            return false;
        }
    }
    return true;
}
//function  : �����������֤��
//Parameters: IdentityCardNumber:string,���������֤��;
//return    : returncode = 0: ͨ�����
//			  returncode != 0: δͨ����顣
function checkIdentityCard(IdentityCardNumber)
{
	// Is the IdentityCardNumber string composed of digit char?
	var returncode = 0;
	
        //ǰʮ�߸��ַ�����Ϊ����
	if(IdentityCardNumber.length == 18 && !isDigitString(IdentityCardNumber)&&(!isDigitString(IdentityCardNumber.substr(0,17))||IdentityCardNumber.charAt(17)!='x'&& IdentityCardNumber.charAt(17)!='X'))
	  returncode = returncode + 8;
	  
	if (IdentityCardNumber.length == 15 && !isDigitString(IdentityCardNumber)) returncode=returncode + 1;
	
	// length of IdentityCardNumber
	if ((IdentityCardNumber.length != 15)&&(IdentityCardNumber.length != 18)) returncode=returncode + 2;
	//�����������Ƿ���ϸ�ʽҪ�� 15λ��YYMMDD  18λ:YYYYMMDD
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

//function  : ��������email��ַ�Ƿ����'@'��'.'
//Parameters: EmailAddress:String
//return    : returncode = 0: ͨ�����
//			  returncode != 0: δͨ�����
function checkEmailAddress(EmailAddress)
{
	var returncode = 0;
	//EmailString����'@'?
	if(EmailAddress.indexOf("@")<=0) returncode=1;
	
	//EmailString����'.'?
	if (EmailAddress.indexOf(".")==-1) returncode=1;
	//EmailString��"@"Ӧ��"."ǰ
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

//function  : �������ĵ绰�����Ƿ������ֺ�'-'���
//Parameters: PhoneNumber:String
//return    : returncode = 0: ͨ�����
//			  returncode != 0: δͨ����顣
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

//function  : ���������ƶ��绰����
//Parameters: MobilePhoneNumber:String
//return    : returncode = 0: ͨ�����
//			  returncode != 0: δͨ����顣
function checkMobilePhoneNumber(MobilePhoneNumber)
{
	var returncode = 0;
	if (!isDigitString(MobilePhoneNumber)) returncode=returncode+1;
	if (MobilePhoneNumber.length!=11) returncode=returncode+2;
	return returncode;
}

//function  : ���������ַ����Ƿ����ת��ΪDate����
//Parameters: strData:String
//return    : true: ͨ�����
//			  false: δͨ����顣
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

//function  : �Զ�Ҫ���û�������ȷ�����ڣ���ʽΪ:YYYY-MM-DD
//Parameters: strData:String
//return    : true: ͨ�����
//			  false: δͨ����顣
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

//��ȡ�ַ����Ĵ洢����
//���������ַ���ÿһ������=2
//���ڷ�����Ascii2�ַ���ÿһ������=1
function getStringLength(strValue) {
	if (strValue==null || strValue=="undefined") {
		return 0;
	}
	var strLength = 0;
	for (var i=0;i<strValue.length;i++) {
		var code = strValue.charCodeAt(i);
//		if (code*1>16677215) { //�ж��ֽڳ�Ϊ4��
//			strLength += 4;
//		} else if (code*1>65535) { //�ж��ֽڳ���Ϊ3��
//			strLength += 3;
//		} else
		if (code*1>255) { //�ж�˫�ֽ��ַ�
			strLength += 2;
		} else { //��ͨ��ASCII�ַ�
			strLength += 1;
		}
	}
	return strLength;
}

//�ж��Ƿ��������ַ�
//parameter strValue �ַ�������
//return true���������ַ���false��û�������ַ�
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

// ȥ���ַ���ǰ��Ŀո�
// @parameter strValue ��Ҫ������ַ�����
// @return String ǰ��ո���Ʊ����ȥ��
function trimString(strValue) {
	if (strValue==null) {
		return null;
	}
	var returnValue = strValue;

	//ɾ���ַ���ǰ��Ŀո�(=32=0x20)���Ʊ��(=09=0x09)
	//�Լ������ַ��Ŀո�(=41377=0xA1A1)
	while (returnValue.length>0) {
		var code = returnValue.charCodeAt(0);
		if (code==32 || code==9 || code==41377) {
			returnValue = returnValue.substr(1);
		} else {
			break;
		}
	}

	//ɾ���ַ�������Ŀո�(SPACE=20)���Ʊ��(TAB=09)
	//�Լ������ַ��Ŀո�(=41377=0xA1A1)
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

//��currentNode��㿪ʼ���������еĸ��ڵ���ұ������ΪtagName�Ľڵ㡣
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

//��currentNode��㿪ʼ���������е�����ڵ���ұ������ΪtagName�Ľڵ㡣
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
//�ж��ַ������Ƿ�����Ƿ��ַ�
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
//�ж��ַ����еķǷ��ַ�ֻ�����ַ���������
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
//�ж�Ӣ����
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
//�ж����ʱ���ǲ���������ʱ���Ժ�
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
//�ж�������
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