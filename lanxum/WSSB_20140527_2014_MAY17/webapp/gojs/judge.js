/**********Ӧ�ð�ȫģ��������Ƶ�¼���ж�*******************/

//���object.value�ǲ���ֻ����RefString�е��ַ�
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

//���Email��ַ�ĺϷ��Ե��������
function Fn_Emaildz(object)
{
    var refString = "0123456789_-abcdefghijgklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ@.";
    if (!checkValidChars(object, refString))
    {
        alert("Email��ַֻ��Ϊ���֣��»��ߣ����ߺʹ�Сд��ĸ,����������");
        object.focus();
        return false;
    }
    return true;
}

//�ж��ַ����Ƿ�Ϊ����
function Fn_Number(object)
{
    var refString = "0123456789";
    if (!checkValidChars(object, refString))
    {
        alert("ÿҳ��ʾ�����ֻ��Ϊ���֣����������룡");
        object.focus();
        return false;
    }
    return true;
}


//������ 2003-06-20 for ϵͳȱʡ���ݹ���Ȩ��
//�жϽڵ��ʶ�Ƿ�Ϊ����
function Fn_Jdid(object)
{
    var refString = "0123456789";
    if (!checkValidChars(object, refString))
    {
        alert("���ܽڵ��ʶֻ��Ϊ���֣����������룡");
        object.focus();
        return false;
    }
    return true;
}
//end ������

//���ؽ� 2003-09-8
//�ж��ַ����Ƿ�Ϊ����0������
//���object.value�ǲ���ֻ����RefString�е��ַ�
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
    	alert("ÿҳ��ʾ���������Ϊ�����Ҵ���0������������!");
        object.focus();
        return false;
    }
    return true;
}
//���ؽ� 2003-09-7
//�жϽ�ɫ��ʶ(ID)��¼����
function Fn_Jsid(object)
{
	var refString = "0123456789_abcdefghijgklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    if (!checkValidChars(object, refString))
    {
        alert("��ɫ��ʶֻ��Ϊ���֡���Сд��ĸ���»���,����������");
        object.focus();
        return false;
    }
    return true;
}
//end ���ؽ�

//���ؽ� 2003-09-7
//�жϽ�ɫ��ʶ(ID)��¼����
function Fn_Jsmc(object)
{
	var refString = "0123456789_abcdefghijgklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    if (!checkValidChars(object, refString))
    {
        alert("��ɫ����ֻ��Ϊ���֡���Сд��ĸ���»���,����������");
        object.focus();
        return false;
    }
    return true;
}
//end ���ؽ�

//���ؽ� 2003-09-7
//�жϵ绰�����¼����
function Fn_Tel(object)
{
    //var refString = "0123456789-()abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	var refString = "0123456789-()";
	if (!checkValidChars(object, refString))
    {
        alert("����ĵ绰���벻�Ϸ������������룡");
        object.focus();
        return false;
    }
    return true;
}

//�ж��û���ʶ(ID)��¼����
function Fn_Yhid(object)
{
    var refString = "0123456789_abcdefghijgklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    if (!checkValidChars(object, refString))
    {
        alert("�û���ʶֻ��Ϊ���֡���Сд��ĸ���»���,����������");
        object.focus();
        return false;
    }
    return true;
}

//�ж��û������¼����
function Fn_Yhmm(object)
{
    var refString = "0123456789_abcdefghijgklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    if (!checkValidChars(object, refString))
    {
        alert("�û�����ֻ��Ϊ���֣��»��ߺ���ĸ,����������");
        object.focus();
        return false;
    }
    return true;
}

//�ж��û�ȷ�������¼����
function Fn_Yhmmqr(object)
{
    var refString = "0123456789_abcdefghijgklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    if (!checkValidChars(object, refString))
    {
        alert("�û�����ֻ��Ϊ���֣��»��ߺ���ĸ,����������");
        object.focus();
        return false;
    }
    return true;
}

//�ж�Email��ʽ�Ƿ���ȷ
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
        alert("�������Email��ַ�������������룡");
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
        alert("�������Email��ַ�������������룡");
        return false;
    }
    return true;
}

// ����������ȵļ��
function Fn_cscd(object)
{
    var refString = "0123456789";
    if (!checkValidChars(object, refString))
    {
        alert("������Ĳ�������ֻ��Ϊ���֣����������룡");
        object.focus();
        return false;
    }
    return true;
}

//���ҵ�����ĺϷ���
function Fn_Ywid(object)
{
    var refString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    if (!checkValidChars(object, refString))
    {
        alert("ҵ�����ֻ��Ϊ��д��ĸ,����������");
        object.focus();
        return false;
    }
    return true;
}

//��ҳ������Ƿ��ַ�����'������
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
        alert("�������˷Ƿ��ַ����Ѿ������Զ�ת��Ϊ�ո�");
        return false;
    }
    return true;
}

//��ҳ����
function FN_ChangePage(opType)
{
    document.forms[0].action.value = "ChangePage";
    //������"��һҳ"
    if (opType == "diyiye")
    {
        document.forms[0].changePage.value = "1";
        return true;
    }
    //�������"��һҳ"
    if (opType == "shangyiye")
    {
        document.forms[0].changePage.value = parseInt(document.forms[0].changePage.value)-1;
        return true;
    }
    //�������"��һҳ"
    if (opType == "xiayiye")
    {
        document.forms[0].changePage.value = parseInt(document.forms[0].changePage.value)+1;
        return true;
    }
    //�������"���һҳ"
    if(opType == "zuihouyiye")
    {
        document.forms[0].changePage.value = document.forms[0].pageCount.value;
        return true;
    }
    return false;
}

//���ҳ����ʾ
function Fn_ClearPage()
{
    document.forms[0].reset();
    return false;
}


//�ж�֤������ĺϷ���
function Fn_Zjhm(object)
{
    var refString = "0123456789";
    if (!checkValidChars(object, refString))
    {
        alert("֤���������벻�Ϸ������������룡");
        object.value = "";
        return false;
    }
    return true;
}


/*
 * ���ܣ��س��ַ����еĿո񣬷���������:
 * @1,�׿ո�:delSpace(str)     str��ȫΪ�ո�
 * @2,β�ո�:delLSpace(str)    str����ȫΪ�ո�
 * @3,��β�ո�:delRSpace(str)   str����ȫΪ�ո�
 * ����: 13/08/2003
 * ����:Yan-Jun Lee
 */
//��ȡ�ַ��������ҿո�
function delSpace(str) {
    if (delSpaceTmp(str + "@|*&") == "@|*&")
        return "";
    else
        return delSpaceTmp(str);
}
function delSpaceTmp(str) {
    return delLSpace(delRSpace(str));
}
//��ȡ�ַ��������ո�
function delLSpace(str) {
    for (var i = 0; i < str.length; i++) {
        if (str.charAt(i) != " ") {
            str = str.substring(i, str.length);
            break
        }
    }
    return str;
}
//��ȡ�ַ������Ҳ�ո�
function delRSpace(str) {
    for (var i = str.length - 1; i > -1; i--) {
        if (str.charAt(i) != " ") {
            str = str.substring(0, i + 1);
            break;
        }
    }
    return str;
}

//��ҳ���һ��form���е���������Ԫ�ؽ��нس���β�ո�Ĵ���
function delFormSpaces() {
	for (var i = 0; i < document.forms[0].elements.length; i++) {
		document.forms[0].elements[i].value = delSpace(document.forms[0].elements[i].value);
    }
}
//�����û�������
function checkYhPassword(object,objectQr)
{
    inString = object.value;
    if(inString == "")
    {
        alert("�Բ����û����벻��Ϊ�գ����������룡");
        object.focus();
        return false;
    }
    if(inString.length < 6)
    {
        alert("�Բ����û���������Ϊ6λ,���������룡");
        object.value = "";
        objectQr.value = "";
        object.focus();
        return false;
    }
    if(inString != objectQr.value)
    {
        alert("�Բ�����������������벻һ�£����������룡");
        object.value = "";
        objectQr.value = "";
        object.focus();
        return false;
    }
    //���Ƿ��ַ�
    if(!Fn_check(object))
    {
        return false;
    }
    return true;
}
//���ϵͳ�û�����
function checkXtyhPassword(object,objectQr)
{
    return checkYhPassword(object,objectQr);
}

//��������û�����
function checkWsyhPassword(object,objectQr)
{
    return checkYhPassword(object,objectQr);
}

function Fn_checkZzjgdm(object)
{
    var refString = "0123456789";
    if (object.value.length != 9)
    {
    	  alert("��֯��������ֻ��Ϊ9λ������������!");
        object.focus();
        return false;
    }
    if (!checkValidChars0(object, refString))
    {
    	  alert("��֯��������ֻ��Ϊ���֣�����������!");
        object.focus();
        return false;
    }
    return true;
}

//���ٶ�λ
function fndisplayDM(objLocate,object)
{
    objLocate.value = object.options[object.selectedIndex].value;
}
//���ٶ�λ
function fnFastLocate(objLocate,object)
{
//    Fn_checkDM(document.forms[0].locate);
    Fn_checkDM(objLocate);
    //�õ�������������ַ����ĳ���
    var cd = objLocate.value.length;
    for(var i=0; i<object.options.length; i++)
    {
        if(object.value.substring(0,cd) == object.options[i].value.substring(0,cd))
            break;
    }

    //���û����Ҫ�ҵĴ��룬���������е�ֵ����
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
//������ĺϷ���
function Fn_checkDM(object)
{
    var refString = "0123456789";
    if (!checkValidChars(object, refString))
    {
        alert("���ش���ֻ��Ϊ����������,���������룡");
        object.focus();
        return false;
    }
    return true;
}
