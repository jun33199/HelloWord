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

//�жϵ绰�����¼����
function Fn_Tel(object)
{
    var refString = "0123456789-()abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
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
        object.value = "";
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
        object.value = "";
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
    var refString = "'";
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
