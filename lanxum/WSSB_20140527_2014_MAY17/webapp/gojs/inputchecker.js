// ȥ���ַ���ǰ��Ŀո�
// @parameter strValue ��Ҫ������ַ�����
// @return String ǰ��ո���Ʊ����ȥ��
function trimString(strValue)
{
    if (strValue==null)
    {
        return null;
    }
    var returnValue = strValue;

    //ɾ���ַ���ǰ��Ŀո�(=32=0x20)���Ʊ��(=09=0x09)
    //�Լ������ַ��Ŀո�(=41377=0xA1A1)
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

    //ɾ���ַ�������Ŀո�(SPACE=20)���Ʊ��(TAB=09)
    //�Լ������ַ��Ŀո�(=41377=0xA1A1)
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

    //ɾ���ַ����Ŀո�(=32=0x20)���Ʊ��(=09=0x09)
    //�Լ������ַ��Ŀո�(=41377=0xA1A1)
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


//��ȡ�ַ����Ĵ洢����
//���������ַ���ÿһ������=2
//���ڷ�����Ascii2�ַ���ÿһ������=1
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
        //{ //�ж��ֽڳ�Ϊ4��
        //	strLength += 4;
        //}
        //else if (code*1>65535)
        //{ //�ж��ֽڳ���Ϊ3��
        //	strLength += 3;
        //}
        //else
        if (code*1>255)
        { //�ж�˫�ֽ��ַ�
            strLength += 2;
        }
        else
        { //��ͨ��ASCII�ַ�
            strLength += 1;
        }
    }
    return strLength;
}

//��ʼ����鷽�������������Լӵ��ı�����
//    required�����ֶ��Ƿ�����Ϊ�ա�"true"������Ϊ�գ�"false"������Ϊ�ա�Ĭ��="false"��
//    maxLength�����ֶ������������󳤶ȡ��������͡��޶����򲻼�顣
//    specialChecker��ע������������鷽�����ƣ����Դ�������Ϊʵ�Ρ�
//    objectChecker��ע������������鷽�����ƣ����Դ�������Ϊʵ�Σ���specialChecker��ͬ���ǣ�objectChecker�������õ�object��Ϊ������
//    captionName���������ֶε���ʾ���ơ�����������Ƿ�������£�����ʾ��Ϣ����ʾ���ֶ����ơ�
//    regExpPattern��������ʽ��pattern������������ʽ�жϡ�
//    nullable���Ƿ�����Ϊ�ա���δ����required���Ե�ʱ�򣬿��ô����������true������Ϊ�գ�false��������Ϊ�ա�
//    patternDesc����specialChecker��objectChecker��regExp���ʧЧ��ʱ����ʾ�û���ȷ����Ҫ���������Ϣ��
//    keepSpaces�������Ƿ񱣴�������ǰ��Ŀո���������棬����ǰ��trim������ַ�����������������Ĭ�ϣ�true��
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

//ͨ�������鷽����֧��select��textarea��input��type=text/password������ؼ�
//��������Ҫд�ڶ�Ӧ����ؼ���tag�У�����
//    required�����ֶ��Ƿ�����Ϊ�ա�true������Ϊ�գ�false������Ϊ�ա�Ĭ��=false��
//    maxLength�����ֶ������������󳤶ȡ��������͡��޶����򲻼�顣
//    specialChecker��ע������������鷽�����ƣ����Դ�������Ϊʵ�Ρ�
//    objectChecker��ע������������鷽�����ƣ����Դ�������Ϊʵ�Σ���specialChecker��ͬ���ǣ�objectChecker�������õ�object��Ϊ������
//    captionName���������ֶε���ʾ���ơ�����������Ƿ�������£�����ʾ��Ϣ����ʾ���ֶ����ơ�
//    regExpPattern��������ʽ��pattern������������ʽ�жϡ�
//    nullable���Ƿ�����Ϊ�ա���δ����required���Ե�ʱ�򣬿��ô����������true������Ϊ�գ�false��������Ϊ�ա�
//    patternDesc����specialChecker��objectChecker��regExp���ʧЧ��ʱ����ʾ�û���ȷ����Ҫ���������Ϣ��
//    keepSpaces�������Ƿ񱣴�������ǰ��Ŀո���������棬����ǰ��trim������ַ�����������������Ĭ�ϣ�true��
function checker_checkInput(obj)
{
    if (obj==null)
    {
        alert("�������д���ն���");
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
        alert("��鷽����֧��"+obj.name+"��������ͣ�");
        return false;
    }

    //ȡ��������󳤶�
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

    //ȡ�Ƿ��Ǳ�����
    var required = false;
    if (obj.required!=null)
    {   //������required����
        if (obj.required.toLowerCase()=="false")
        {   //��������˸�����==false
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

    //ȡ�Ƿ��Ǳ�����
    var keepSpaces = false;
    if (obj.tagName=="TEXTAREA" || obj.tagName=="SELECT")
    {   //TextArea��Select�Զ�����ԭ����ֵ
        keepSpaces = true;
    }
    else if (obj.tagName=="INPUT" && obj.type!=null && obj.type.toLowerCase()=="password")
    {   //password�����������Զ�����ԭ����ֵ
        keepSpaces = true;
    }
    if (obj.keepSpaces!=null)
    {   //���������keepSpaces����
        if (obj.keepSpaces.toLowerCase()=="false")
        {
            keepSpaces=false;
        }
        else
        {
            keepSpaces=true;
        }
    }

    //ȡ�����鷽������
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
        //���ݷ���������װ�ɷ�������Function()
        specialChecker = new Function("value","return "+specialChecker);
    }

    //ȡ��������鷽������
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
        //���ݷ���������װ�ɷ�������Function()
        objectChecker = new Function("inputObj","return "+objectChecker);
    }

    var regExpPattern = null;
    if (obj.regExpPattern!=null)
    {   //��������Tag���ж���������ʽ����ȡ������ʽ��pattern
        regExpPattern=obj.regExpPattern;
    }

    //ȡ��ʾ��������
    var captionName = obj.captionName;
    if (captionName==null || captionName=="")
    {
        captionName = obj.name;
    }

    //��ȡ��ȷ�����������Ϣ
    var patternDesc = obj.patternDesc;
    if (patternDesc!=null && patternDesc=="")
    {
        patternDesc==null;
    }

    if (keepSpaces==false)
    {   //�����Ҫtrim�ո�
        obj.value = trimString(obj.value);
    }

    //�������disabled����ö����ֵ���ᴫ���������ˣ����Բ�����˲���
    if (obj.disabled==true)
    {
        return true;
    }
    else if (obj.readOnly==true)
    {   //�������readOnly����ö����ֵ�߼��ϲ�Ӧ���޸ķ������˵�ֵ�����Բ����˲���
        return true;
    }

    if (required==true && obj.value=="")
    {   //�ж������Ƿ�Ϊ�յļ��
        if (obj.tagName=="SELECT")
        {
            alert("��"+captionName+"���Ǳ��������ѡ��");
        }
        else
        {
            alert("��"+captionName+"���Ǳ�����������룡");
        }
        checker_focusToObject(obj);
        return false;
    }
    if (maxLength!=null && obj.value!="" && getStringLength(obj.value)>maxLength)
    {   //�ж������Ƿ񳬹���󳤶ȵ��ж�
        alert("��"+captionName+"�������������������"+maxLength+"���ַ������������룡");
        checker_focusToObject(obj);
        return false;
    }
    
    if (minLength!=null && obj.value!="" && getStringLength(obj.value)<minLength)
    {   //�ж������Ƿ񳬹���С���ȵ��ж�
        alert("��"+captionName+"��������������Ҫ����"+minLength+"���ַ������������룡");
        checker_focusToObject(obj);
        return false;
    }
    
    if (specialChecker!=null && obj.value!="")
    {   //����ָ�������������鷽���ж�
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
    {   //����ָ��������object��鷽���ж�
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
    {   //����������ʽƥ�䣬�ж������Ƿ����Ҫ��
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

//������ת������ؼ�
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

//��ȡҳ����ʾ��Ϣ
function checker_getAlertMessage(captionName, patternDesc, rtnValue)
{
    var alertMessage = "";
    if (rtnValue!=null && rtnValue!=false && rtnValue!="false")
    {
        alertMessage += "��"+captionName+"�����벻����Ҫ��"+rtnValue+"�����������룡";
    }
    else if (patternDesc!=null)
    {
        alertMessage += "��"+captionName+"����ȷ������Ҫ��"+patternDesc+"�����������룡";
    }
    else
    {
        alertMessage += "��"+captionName+"�������ʽ����ȷ�����������룡"
    }
    return alertMessage;
}

//���������������飡
//aryObjects��������������
function checker_checkGroup(aryObjects)
{
    if (aryObjects==null || aryObjects.length==0)
    {
        alert("����Ķ��������飡");
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
//  ����SpecialChecker�ĵط�
/////////////////////////////////////////////////////////////////////////

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

//function  : ��������ַ����Ƿ����������
//parameters: DigitString: String,������ַ�����ֵ
//			  totalLength: int, ���ֵĳ������ƣ�������С���㣩
//			  decimalLength: int��С���ĳ�������
//            totalLength-decimalLength �������ֵ�����
//            isPositive �Ƿ�Ϊ�Ǹ��� true��ʾһ��Ҫ�ǷǸ�����false��ʾһ��Ҫ�Ǹ�����null��ʾ���� add by zhangp 2003-09-25
//return    : false: �ַ����а�������������ַ�
//			  true : else
function checkNumber(DigitString, totalLength, decimalLength,isPositive)
{
    var re = "";
    
    if(isPositive == "true" || isPositive == true)
    {//����ǷǸ���
        if(isNaN(DigitString*1) || DigitString*1<0)
            return false;
    }
    if(isPositive == "false" || isPositive == false)
    {//����Ǹ���
        if(isNaN(DigitString*1) || DigitString*1>=0)
            return false;
    }

    
    if (decimalLength!=null && decimalLength != 0)
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
        re="([\\d]{0,"+ intergerLength +"}"+re+"|[\\d]{0,"+intergerLength+"})";
    }
    re = new RegExp("^"+re+"$","g");

    //���ַ���Ϊ�ա�λ�����ԡ�����ƥ�������ʱ
    if(re.exec(DigitString) == null)
    {
        return false;
    }
    return true;
}

//function  : ���������ַ����Ƿ����ת��ΪDate����
//Parameters: strData:String
//return    : true: ͨ�����
//			  false: δͨ����顣
//�޸��ˣ�    ���� 
//�޸�ʱ�䣺  2003-09-28
//�޸�ԭ��  ���ڸ�ʽͳһ�ĳ�20030928
function checkDate(strDate)
{
    if (strDate.length!=8) return false;
    var strYear = strDate.substr(0,4);
    //var strSep1 = strDate.substr(4,1);
    var strMonth = strDate.substr(4,2);
    //var strSep2 = strDate.substr(7,1);
    var strDay = strDate.substr(6,2);
    var objDate = new Date(strMonth+"-"+strDay+"-"+strYear);
    if (isNaN(objDate)) return false; //������������
    //�������ͷ�Χ 1900<��<2100
    if(strYear*1<1900) return false;
    if ((strYear*1 != objDate.getYear()*1)&&(strYear*1 != objDate.getYear()*1+1900)) return false; //������겻��
    //if(strSep1!="-") return false; //���·ָ��������'-'
    if (strMonth*1 != objDate.getMonth()*1+1) return false; //������·ݲ���
    //if(strSep2!="-") return false; //���·ָ��������'-'
    if (strDay*1 != objDate.getDate()*1) return false; //don't call getDay function. //��������ڲ���
    return true;
}

/** 
 *   ������������Ƿ���ȷ
 *   ��������붼��8λ������λ����Ϊ���ֻ���ĸ���������е�λΪ���֣�
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
 *   ������������Ƿ���ȷ
 *   ��������붼��8λ������λ����Ϊ���ֻ���ĸ���������е�λΪ���֣�
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
 *   �����Ȼ�˼���������Ƿ���ȷ
 *   ��������붼��8λ�����һλ��������ĸ������Ϊ��Ϊ���ֺ���ĸ��
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
 *   �����Ȼ�˼���������Ƿ���ȷ
 *   ��������붼��8λ�����һλ��������ĸ������Ϊ��Ϊ���ֺ���ĸ��
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
 * ��ҵ�Ǽǵ�ʱ���������������8λ����
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
//function  : ��ѯ˰Դ���嵥��ѯ�����������������������ʮ����������룬�Զ��ŷָ�
//Parameters: QueryJsjdm:String
//return    : true: ͨ�����
//	      false: δͨ����顣
function checkSyhJsjdm(QueryJsjdm)
{
    if (QueryJsjdm==null || QueryJsjdm.length==0)
    {
        return true;
    }
    var strValue=QueryJsjdm;
    if (isAllCharValid(strValue,"1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz,")==false)
    {   //�����Ƿ��ַ�
        return false;
    }
    var str=strValue.split(",");    
    for(var i=0;i<str.length;i++)
    {
	if(!checkJsjdm(str[i]))
	{
	      //alert("����������е�"+(i+1)+"����ʽ���ԣ�");
	      return false;	
	}
    }

    return true;
}
/** �������ʼ�
* ƥ��ģʽ��xx@xx.xx ��ʽ,xx���ܰ���[~!@#$%^&*().,]��Щ�ַ�
* return 0����ȷ��1������ȷ
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

/** ����ʱ�
 * �Ϸ�����6λ
 * �Ϸ��ַ�������
 * return true:��ȷ��false������ȷ
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

//function  : �����������֤��
//Parameters: IdentityCardNumber:string,���������֤��;
//return    : true: ͨ�����
//			  false: δͨ����顣
function checkIdentityCard(IdentityCardNumber)
{
    // length of IdentityCardNumber
    if ((IdentityCardNumber.length != 15)&&(IdentityCardNumber.length != 18))
    {
        return false;
        //return "���֤����Ӧ����15��18λ";
    }
    //the first char cannot be "0"
    if(IdentityCardNumber.charAt(0)=="0")
    {
        return false;
        //return "���֤��λ������0";
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
        //return "���֤������������֣�18λ���֤�������һλ������x";
    }
    //�����������Ƿ���ϸ�ʽҪ�� 15λ��YYMMDD  18λ:YYYYMMDD
    var strIdentityCard = "19990101";
    if (IdentityCardNumber.length == 15)
    {
        strIdentityCard = "19" + IdentityCardNumber.substr(6,6);
    }
    else if (IdentityCardNumber.length == 18)
    {
        strIdentityCard = IdentityCardNumber.substr(6,8);
    }
    //�޸�Ϊ���·�ʱ,�ſ�У��,����û������֤Ϊũ��
    if (strIdentityCard.substr(4,2)=="02")
    {    
    	return true;	
    }
    else 
    {
    	if(checkDate(strIdentityCard)!=true)
        return false;
        //return "��������֤����δ������ȷ�ĳ�������";    	
    }
    return true;
}

//function  : �������ĵ绰�����Ƿ������ֺ�'-'���
//Parameters: PhoneNumber:String
//return    : true: ͨ�����
//			  false: δͨ����顣
function checkPhoneNumber(PhoneNumber)
{
    if (PhoneNumber==null || PhoneNumber.length==0)
    {
        return true;
    }
    var strValue=PhoneNumber;
    for(var i=0;i<strValue.length;i++)
    {
    	if(strValue.charAt(i)=='.'||strValue.charAt(i)==','||strValue.charAt(i)=='��'||strValue.charAt(i)=='��')
    	{
	    	if(strValue.charAt(i+1)=='.'||strValue.charAt(i+1)==','||strValue.charAt(i+1)=='��'||strValue.charAt(i+1)=='��')
	    	{
	    		//����С��4
	    		return false;
	    	}
	    	else if(strValue.charAt(i+2)=='.'||strValue.charAt(i+2)==','||strValue.charAt(i+2)=='��'||strValue.charAt(i+2)=='��')
	    	{
	    		//����С��4
	    		return false;	
	    	}
	    	else if(strValue.charAt(i+3)=='.'||strValue.charAt(i+3)==','||strValue.charAt(i+3)=='��'||strValue.charAt(i+3)=='��')
	    	{
	    		//����С��4
	    		return false;
	    	}	
    	}
    }
    if (strValue.length<4)
    {   //����С��4
        return false;
    }
    if (strValue.length>0 )
    {
        if (strValue.charAt(0)=='-' || strValue.charAt(strValue.length-1)=='-')
        {   //����ĸ��ĩβ��ĸ�����ǡ�-��
            return false
        }
        if (strValue.indexOf('--')>=0)
        {   //���ܰ���������������-��
            return false;
        }
    }
    if (isAllCharValid(strValue,"1234567890-.,����ABCDEFGHIJKLMNOPQRSTUVWXYZ()abcdefghijklmnopqrstuvwxyz")==false)
    {   //�����Ƿ��ַ�
        return false;
    }
    return true;
}

//function  : ���������ƶ��绰����
//Parameters: MobilePhoneNumber:String
//return    : true: ͨ�����
//			  false: δͨ����顣
function checkMobilePhoneNumber(MobilePhoneNumber)
{
    if (trimString(MobilePhoneNumber).length!=11)
    {   //���Ȳ���11λ
        return false;
    }
    if (isAllCharValid(MobilePhoneNumber,"1234567890")==false)
    {   //��ȫ������
        return false;
    }
    return true;
}

//������ƣ����ƿ��԰��������ַ���Ӣ����ĸ
function checkName(qymcStr)
{
    return true;
}
//�����˰������
function checkNsrmc(nsrmcStr)
{
    if(getStringLength(nsrmcStr)>200)
    {
        return false;        
    }
    return true;
}
//���˰���������
function checkSwdlmc(swdlmcStr)
{
    if(getStringLength(swdlmcStr)>200)
    {
        return false;        
    }
    return true;
}
//���Ͷ�ʷ�����
function checkTzfmc(tzfmcStr)
{
    if(getStringLength(tzfmcStr)>200)
    {
        return false;        
    }
    return true;
}
//����֧��������
function checkFzjgmc(fzjgStr)
{
    if(getStringLength(fzjgStr)>200)
    {
        return false;        
    }
    return true;
}
//����ܻ�������
function checkZjgmc(zjgStr)
{
    if(getStringLength(zjgStr)>200)
    {
        return false;        
    }
    return true;
}
//�������(����)
function checkPersonName_CN(strValue)
{
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

//�������(Ӣ��)
function checkPersonName_EN(strValue)
{
    return isAllCharValid(strValue,"abcdefghijklmnopqrstuvwxyz. ABCDEFGHIJKLMNOPQRSTUVWXYZ");
}

//��������ʺ�
function checkBankName(strValue)
{
    return !hasChineseChar(strValue);
    //return isAllCharValid(strValue,"1234567890 #-ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
}
//�����׼����֤�ջ��ļ�����
function checkYyzzh(strValue)
{
    if (trimString(strValue).length<6)
    {   //���Ȳ���6λ
        return false;
    }
    return true;
}

//function: ��鹫˾����(Ӣ��)
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

//function: ��鹫˾����
function checkCompanyName_EN(strValue)
{
    return isAllCharValid(strValue,"abcdefghijklmnopqrstuvwxyz. ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890");
}
//function: ���Ƿ��ַ�
function checkIllegalchar(strValue)
{
    var englishStr="abcdefghijklmnopqrstuvwxyz. ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
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
//function: ����Ƿ��ǰٷ���
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

//function�������֯��������
function checkZzjgdm(strValue)
{
    var re =/^[\w*#]{9}$/ig;
    if(re.exec(strValue) == null)
    {
        return false;
    }
    return true;
}
//function��˰��Ǽ�֤��
function checkSwdjzh(strValue)
{
	var re =/^[0123456789abcdefghijklmnopqrstuvwxyz*#]{0,18}$/ig;
    if(re.exec(strValue) == null)
    {
       return false;
    }   
    return true;
}
//function������˰��Ǽ�֤��У��
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
//function: ����form�е�input���ûس����Զ��ύ
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