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
function isNumber(numString)
{
    for(var i=0;i<numString.length;i++)
    {
      if(!isDigitKey(numString.charAt(i)))
            return false;
    }
    return true;
}
//function  : ��������ַ����Ƿ����������
//parameters: DigitString: String,������ַ�����ֵ
//			  totalLength: int, ���ֵĳ������ƣ�������С���㣩
//			  decimalLength: int��С���ĳ�������
//            totalLength-decimalLength �������ֵ�����
//return    : false: �ַ����а�������������ַ�
//			  true : else
/*
function isDigitString(DigitString, totalLength, decimalLength)
{
    var dotNum = 0;
    var total = 0;
    for(var i=0;i<DigitString.length;i++)
    {
        if (DigitString.charAt(i)==".") {
            //����С�������
            ++dotNum;
            if (dotNum>1) {
                //����һ��С����д���
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
    //�ж����ֳ����Ƿ�С��涨ֵ
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
}
*/
//function  : ��������ַ����Ƿ����������
//parameters: DigitString: String,������ַ�����ֵ
//			  totalLength: int, ���ֵĳ������ƣ�������С���㣩
//			  decimalLength: int��С���ĳ�������
//            totalLength-decimalLength �������ֵ�����
//return    : false: �ַ����а�������������ַ�
//			  true : else
function isDigitString(DigitString, totalLength, decimalLength)
{
	intergerLength = totalLength - decimalLength;
	var re="^[0123456789]{1," + intergerLength +"}.{0,1}[0123456789]{0,"+ decimalLength +"}$";
	re = new RegExp(re,"i");
	
	//ֻ�������ֲ�Ϊ��ʱ�Ž����ж�
	if(re.exec(DigitString) == null && DigitString != null && DigitString != "")
	{
		return false;	
	}
	return true;
}
//function  : �����������֤��
//Parameters: IdentityCardNumber:string,���������֤��;
//return    : returncode = 0: ͨ�����
//			  returncode != 0: δͨ����顣
function checkIdentityCard(IdentityCardNumber)
{
    // length of IdentityCardNumber
    if ((IdentityCardNumber.length != 15)&&(IdentityCardNumber.length != 18)) return -1;
    //the first char cannot be "0"
    if(IdentityCardNumber.charAt(0)=="0")   return -1;
    //Is the IdentityCardNumber string composed of digit char(last char may be "x" or "X")
    var lastChar=IdentityCardNumber.charAt(IdentityCardNumber.length-1);
    var tempString=IdentityCardNumber;
    if(tempString.length==18 && (lastChar=="x"||lastChar=="X"))
    {
     tempString=IdentityCardNumber.substr(0,IdentityCardNumber.length-1);
    }
    if (!isDigitString(tempString))
    {
        return 1;
    }
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
    if (!isDate(strIdentityCard)) return 1;
    return 0;
}
/*
//function  : ��������email��ַ�Ƿ����'@'��'.'
//Parameters: EmailAddress:String
//return    : returncode = 0: ͨ�����
//			  returncode != 0: δͨ�����
function checkEmailAddress(EmailAddress)
{
    var returncode = 0;
    //EmailString����'@'?
    if (EmailAddress.search("@")==-1) returncode=returncode+1;
    //if (EmailAddress.search(".")==-1) returncode=returncode+2;
    //EmailString����'.'?
//	if (EmailAddress.search("@")>EmailAddress.search(".")) returncode=returncode+4;
    //add other check
    return returncode;
}*/
//modify by zhangpeng
//function	:ƥ���Ƿ���� xx@xx.xx ��ʽ,xx���ܰ���[~!@#$%^&*().,]��Щ�ַ�
//Parameters: EmailAddress:String
//return    : returncode = 0: ͨ�����
//			  returncode != 0: δͨ�����
function checkEmailAddress(EmailAddress)
{
	var re=/^[^~!@#$%^&*().,]{1,30}@[^~!@#$%^&*().,]{1,20}.[^~!@#$%^&*().,]{1,10}$/i;
	
	if(re.exec(EmailAddress) == null)
	{
		return 1;	
	}
	return 0;
}
//function  : �������ĵ绰�����Ƿ������ֺ�'-'���
//Parameters: PhoneNumber:String
//return    : returncode = 0: ͨ�����
//			  returncode != 0: δͨ����顣
function checkPhoneNumber(PhoneNumber)
{
    if (PhoneNumber==null || trimString(PhoneNumber).length==0) {
        return 0;
    }
	var strValue=trimString(PhoneNumber);
    if (strValue.length>0 && strValue.length<4) {
    	return 3;
    }
    if (strValue.length>0 ) {
        if (strValue.charAt(0)=='-' || strValue.charAt(strValue.length-1)=='-') {
            return 1
        }
        if (strValue.indexOf('--')>=0) {
            return 2;
        }
    }
    var returncode = 0;
    for(var i=0;i<strValue.length;i++)
    {
    	if (isAllCharValid(strValue,"1234567890-.ABCDEFGHIJKLMNOPQRSTUVWXYZ()abcdefghijklmnopqrstuvwxyz")==false) {
    		return 4;
    	}
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
    if (trimString(MobilePhoneNumber).length!=11) {
    	return 1;
	}
    if (isAllCharValid(MobilePhoneNumber,"1234567890")==false) {
    	return 2;
    }
    return 0;
}

// ����ʱ࣬����=6����������
function checkPostCode(strValue) 
{
	if (trimString(strValue).length!=6) {
		return 1;
	}
	if (isAllCharValid(strValue,"1234567890")==false) {
		return 2;
	}
	return 0;
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
        if (isDate(strTest)) return strTest;
        strTest = strTest.substr(0,7);

    }
    if (strTest.length == 7)
    {
        if (isDate(strTest + "-01")) return strTest + "-";
        strTest = strTest.substr(0,4);
    }
    if (strTest.length == 4)
    {
        if (isDate(strTest + "-01-01")) return strTest + "-";
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

//�ж��Ƿ�ȫ���������ַ�
//parameter strValue �ַ�������
//return true��ȫ���������ַ���false�����������ļ�¼
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

//����ַ����ǲ��ǺϷ�������
//���ƿ��԰��������ַ���Ӣ����ĸ
function checkName(qymcStr)
{
   var englishStr="abcdefghijklmnopqrstuvwxyz. ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for(var i=0;i<qymcStr.length;i++)
    {
        var tmpCode = qymcStr.charCodeAt(i);
        var tmpChar = qymcStr.charAt(i);
        if(tmpCode>255)
        {
            continue;
        }
        else if(englishStr.indexOf(tmpChar)==-1)
        {
          return false;
        }
    }
    return true;
}
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

//�ж��ַ������Ƿ����ָ�����ַ���
//�˷������ڼ���ַ������Ƿ�����б�����������ַ�
// strValue �������ַ��� (Required)
// charList ָ���ķǷ�������ַ����� (Required)
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

//��currentNode�ڵ翪ʼ���������еĸ��ڵ���ұ������ΪtagName�Ľڵ㡣
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

//��currentNode�ڵ翪ʼ���������е�����ڵ���ұ������ΪtagName�Ľڵ㡣
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

function checkPersonName_CN(strValue) {
   var englishStr="abcdefghijklmnopqrstuvwxyz. ABCDEFGHIJKLMNOPQRSTUVWXYZ��";
    for(var i=0;i<strValue.length;i++)
    {
        var tmpCode = strValue.charCodeAt(i);
        var tmpChar = strValue.charAt(i);
        if(tmpCode>255)
        {	//��������
            continue;
        }
        else if(englishStr.indexOf(tmpChar)<0)
        {	//�������ģ��Ҳ��������ַ���Χ��
          return false;
        }
    }
    return true;
}

function checkPersonName_EN(strValue) {
    return isAllCharValid(strValue,"abcdefghijklmnopqrstuvwxyz. ABCDEFGHIJKLMNOPQRSTUVWXYZ");
}

function checkBankAccountNumber(strValue) {
	return !hasChineseChar(strValue);
    //return isAllCharValid(strValue,"1234567890 *-ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
}

function checkCompanyName_CN(strValue) {
   var englishStr="abcdefghijklmnopqrstuvwxyz. ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    for(var i=0;i<strValue.length;i++)
    {
        var tmpCode = strValue.charCodeAt(i);
        var tmpChar = strValue.charAt(i);
        if(tmpCode>255)
        {
            continue;
        }
        else if(englishStr.indexOf(tmpChar)<0)
        {
          return false;
        }
    }
    return true;
}

function checkCompanyName_EN(strValue) {
    return isAllCharValid(strValue,"abcdefghijklmnopqrstuvwxyz. ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890");
}

//function: ����Ƿ��ǰٷ���
function checkPercentNumber(strValue) {
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

//function  : �жϰ����Ƿ�Ϊ���ּ�
function isDigitKey()
{

    //��onkeypress��onkeydown�¼��У�ͬһ����keyCode���ܲ�ͬ,������Ӧonkeydown�¼�
    if(event.shiftKey) return  false;
    if ((event.keyCode >= 48)&&(event.keyCode <= 57)) return  true;
    if ((event.keyCode >= 96)&&(event.keyCode <=105))  return  true;//С����
    return false;
}
//�жϰ����Ƿ�Ϊ���ּ�
//typeStr ="flaot"��ʾ����Ƿ��Ǹ�����
//����������˼�
function isNumberKey(typeStr)
{
    if(isDigitKey()) return true;
    if (typeStr!=null&&typeStr=="float"&&event.keyCode == 190)return true;//.
    if (event.keyCode == 8)return true;//Backspace
    return false;
}

//function  : �жϰ����Ƿ�ΪӢ���ַ�'a'-'z','A'-'Z'��
function isEnglishLetterKey()
{
    //��onkeypress��onkeydown�¼��У�ͬһ����keyCode���ܲ�ͬ,������Ӧonkeydown�¼�
    if ((event.keyCode >= 65)&&(event.keyCode <= 90)) return true;
    return false;
}
//�жϰ����Ƿ������ַ�
//����������˼�
function isSpecialKey()
{
    //"!@#$%^&*()"
    if(event.shiftKey&&(event.keyCode >= 48)&&(event.keyCode <= 57)) return true;

    //"`-=[]\;',./"
    if ((event.keyCode >= 186)&&(event.keyCode <= 192)) return true;
    if ((event.keyCode >= 219)&&(event.keyCode <= 222)) return true;
    if ((event.keyCode >= 106)&&(event.keyCode <= 107)) return true;
    if ((event.keyCode >= 109)&&(event.keyCode <= 111)) return true;
    return false;
}
//�жϰ����Ƿ�������������ƵĺϷ��ַ�
//����������˼�
function isChineseNameChar()
{
    if (event.keyCode == 190) return  true;//.
    if (event.keyCode == 8) return  true;//Backspace
    if (event.keyCode == 32) return  true;//space
    return false;
}
//�жϰ����Ƿ���������ƵĺϷ��ַ�
//����ʹ���ĺ�Ӣ��
//����������˼�
function isNameChar()
{
    if(isEnglishLetterKey()) return true;
    if (event.keyCode == 190) return  true;//.
    if (event.keyCode == 8) return  true;//Backspace
    if (event.keyCode == 32) return  true;//space
    return false;
}
//�жϰ����Ƿ������ʱ��ĺϷ��ַ�
//����ʹ���ֺ�"-"
//����������˼�
function isStadardDateChar()
{
    if(isDigitKey()||event.keyCode==189) return true;//"-"
    if (event.keyCode == 8) return  true;//Backspace
    if (event.keyCode == 32) return  true;//space
    return false;
}
//�жϰ����Ƿ�����ɵ绰����ĺϷ��ַ�
//����ʹ���ֺ�"-"
//����������˼�
function isStadardPhoneChar()
{
    if(isDigitKey()||event.keyCode==189) return true;//"-"
    if (event.keyCode == 8) return  true;//Backspace
    if (event.keyCode == 32) return  true;//space
    return false;
}
