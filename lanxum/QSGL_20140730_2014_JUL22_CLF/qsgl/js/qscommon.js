//��ҳ����
function FN_QSChangePage(opType)
{
    document.forms[0].operationType.value = "ChangePage";
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

//��鶼������0123456789.

function isValidNumber(strnum)
{
	if ((strnum == null) || strnum.length == 0)
	   return false;
	validnumber = "0123456789.";
	for (var i = 0 ; i < strnum.length; i++)
	{
		if (validnumber.indexOf(strnum.charAt(i)) < 0)
		{
			return false;
		}
	}
	var dot = strnum.indexOf(".");
	if (dot > 0)
	{
		frag = strnum.substr(dot+1);
		if (frag.indexOf(".") >= 0)
		{
			return false;
		}
	}
	return true;
}

//�������������� ���ָ�ʽ������8999999,Ҳ������999.9999
function FN_QSCheckNumber(val,objname)
{
	if (!isValidNumber(val))
	{
		alert(objname + "�������֣�");
		return false;
	}

	return true;
}

//�������������� ���ָ�ʽ������8999999,Ҳ������999.9999
//С��λ�����ܳ���ָ����digitLen
//�޸Ľ���������ֵ��ʾ������С��λ��Լ��Ϊ3λ��modify by fujx��20081129
function FN_QSCheckNumberDigit_Qssb(val,digitLen,objname)
{
//������ʾ��ʾ�����ֵ��
var maxMj="";
//ȡ����˰�걨�и�����Ǹ��˵ı�־��personΪfalse��ʾΪ���ˣ�����Ϊ�Ǹ���
var person = document.forms[0].person.value;
        //���ݸ��˻�����ҵѡ����ʾ�������
        if(person=="false"){
        maxMj="10000";
        }else{
        maxMj="1000";
        }
	if (!FN_QSCheckNumber(val,objname))
	{
		return false;
	}
	var dot = val.indexOf(".");
	if (dot > 0)
	{
		frag = val.substr(dot+1);
		if (frag.length > digitLen)
		{
			alert(objname + "С����λ��������");
			return false;
		}
		inte = val.substr(0,dot);
        //���ӡ�������������ߡ����ݼ�ֱ���������10000����ʾ
        //��ԭ���ĳ���Ϊ13���ж��޸�Ϊ����Ϊ5���жϣ�������confirm����ʾ������߼���֧
        //modify by fujx��20081126
        //�޸����ݿ�ʼ
      //  if(inte.length == 5){
        var mj = parseFloat(val);

        if(mj>maxMj){
        if(!confirm(objname+"����"+maxMj+"�����ʵ�Ƿ���ȷ!")){
          return false;
         }
        }
       // }
//        if(inte.length > 5){
//         if(!confirm(objname+"����"+maxMj+"�����ʵ�Ƿ���ȷ!")){
//          return false;
//         }
//        }
        //�޸����ݽ���


        //ԭ����code��ʼ
		if (inte.length > 13)
		{
			alert(objname + "��������λ��������");
			return false;
		}
       //ԭ����code����
	}
    else  //û��С��
	{
   // var inte = val.length;
    //���ӡ�������������ߡ����ݼ�ֱ���������10000����ʾ
        //��ԭ���ĳ���Ϊ13���ж��޸�Ϊ����Ϊ5���жϣ�������confirm����ʾ������߼���֧
        //modify by fujx��20081126
        //�޸����ݿ�ʼ
        var mj = parseInt(val);
        if(mj>maxMj){
        if(!confirm(objname+"����"+maxMj+"�����ʵ�Ƿ���ȷ!")){
          return false;
         }
        }
//        if(inte > 5){
//         if(!confirm(objname+"����"+maxMj+"�����ʵ�Ƿ���ȷ!")){
//          return false;
//         }
//        }
         //�޸����ݽ���
         //ԭ����code��ʼ
		if (val.length > 13)
		{
			alert(objname + "��������λ��������");
			return false;
		}
        //ԭ����code����
	}


	return true;
}
//�޸��ˣ�fujx�����Ӳ�����˰�걨�У���������뽨�������Լ��js
function FN_QSCheckNumberDigit_BZQS(val,digitLen,objname)
{
//������ʾ��ʾ�����ֵ��
var maxMj="";
//ȡ�ò�����˰�걨�и�����Ǹ��˵ı�־��yhbsΪ0��ʾΪ���ˣ�����Ϊ�Ǹ���
var yhbs = document.forms[0].yhbs.value;
        //���ݸ��˻�����ҵѡ����ʾ�������
        if(yhbs=="1"){
        maxMj="10000";
        }else{
        maxMj="1000";
        }
	if (!FN_QSCheckNumber(val,objname))
	{
		return false;
	}
	var dot = val.indexOf(".");
	if (dot > 0)
	{
		frag = val.substr(dot+1);
		if (frag.length > digitLen)
		{
			alert(objname + "С����λ��������");
			return false;
		}
		inte = val.substr(0,dot);
        //���ӡ�������������ߡ����ݼ�ֱ���������10000����ʾ
        //��ԭ���ĳ���Ϊ13���ж��޸�Ϊ����Ϊ5���жϣ�������confirm����ʾ������߼���֧
        //modify by fujx��20081126
        //�޸����ݿ�ʼ
      //  if(inte.length == 5){
        var mj = parseFloat(val);

        if(mj>maxMj){
        if(!confirm(objname+"����"+maxMj+"�����ʵ�Ƿ���ȷ!")){
          return false;
         }
        }
       // }
//        if(inte.length > 5){
//         if(!confirm(objname+"����"+maxMj+"�����ʵ�Ƿ���ȷ!")){
//          return false;
//         }
//        }
        //�޸����ݽ���


        //ԭ����code��ʼ
		if (inte.length > 13)
		{
			alert(objname + "��������λ��������");
			return false;
		}
       //ԭ����code����
	}
    else  //û��С��
	{
   // var inte = val.length;
    //���ӡ�������������ߡ����ݼ�ֱ���������10000����ʾ
        //��ԭ���ĳ���Ϊ13���ж��޸�Ϊ����Ϊ5���жϣ�������confirm����ʾ������߼���֧
        //modify by fujx��20081126
        //�޸����ݿ�ʼ
        var mj = parseInt(val);
        if(mj>maxMj){
        if(!confirm(objname+"����"+maxMj+"�����ʵ�Ƿ���ȷ!")){
          return false;
         }
        }
//        if(inte > 5){
//         if(!confirm(objname+"����"+maxMj+"�����ʵ�Ƿ���ȷ!")){
//          return false;
//         }
//        }
         //�޸����ݽ���
         //ԭ����code��ʼ
		if (val.length > 13)
		{
			alert(objname + "��������λ��������");
			return false;
		}
        //ԭ����code����
	}


	return true;
}








//�������С����λ�����࣬��ֱ��ʹ��+
function floatplus(val1,val2)
{
	tol = parseInt(val1*100) + parseInt(val2*100);
	return tol / 100;
}

//�������С����λ�����࣬��ֱ��ʹ��-
function floatminus(val1,val2)
{
	rem = parseInt(val1*100) - parseInt(val2*100);
	return rem / 100;
}

//����Ҽ۸�ת���������
//wbjgobj ������Ҽ۸�Ķ���
//hlobj ������ʵĶ���
//rmbobj ��������ҵĶ���
function fnConvertWb(wbjgobj,hlobj,rmbobj)
{
   wbjg = wbjgobj.value;
   hl = hlobj.value;
   if (!isValidNumber(wbjg))
     return;
   if (!isValidNumber(hl))
     return;

   rmbobj.value = parseInt((wbjg * hl + 0.005)*100) / 100;
}

//������������
function fnCheckMjJe(obj,len,name)
{
    if (!FN_QSCheckNumberDigit(obj.value,len,name))
    {
        obj.focus();
    }
}

//�͵�ǰʱ����бȽ�
function cmpDate(shijian,xtdqsj)
{
		today = new Date();
		var strYear = shijian.substr(0,4)
		var strMonth = shijian.substr(4,2)
		var strDay = shijian.substr(6,2)

		var strXtyear = xtdqsj.substr(0,4)
		var strXtmonth = xtdqsj.substr(4,2)
		var strXtday = xtdqsj.substr(6,2)

		if(strYear>strXtyear)
		{
			 return false
		}
		if(strYear<strXtyear)
		{
			return true
		}
	  else
		{
				if(strMonth>(strXtmonth))
				{
					return false
				}
				if(strMonth<(strXtmonth))
				{
					return true
				}
				else
				{
						if(strDay>strXtday)
						{
							return false

						}
				}
		}
		return true
}

//�ж�����Ƿ�ͨ����ͨ������true
function check_Mj(){

	//�ж��������
	if(cmpNumber(document.forms[0].tdfwqszymj.value,"10000")){
		return false;
	}
	//�жϷ��ݽ������
	if(cmpNumber(document.forms[0].fwjzmj.value,"10000")){
		return false;
	}
	return true;

}

//�жϼ۸��Ƿ�ͨ����ͨ������true
function check_Jg(){

	//�жϳɽ��۸������
	if(cmpNumber("50000",document.forms[0].cjjgyrmb.value)){
		return false;
	}
	//�ж�˰����غ˶��۸�
	if(cmpNumber("50000",document.forms[0].pgjg.value)){
		return false;
	}


	return true;

}


//�ж�number1�Ƿ��number2�󣬴����򷵻�true
function cmpNumber(number1,number2)
{
	if(number1 != "" && number1 != null && number2 != "" && number2 != null && parseFloat(number1)<=parseFloat(number2)){
		return false;
	}
	if(number1 == "" || number1 == null || number2 == "" || number2 == null){
		return false;
	}
	return true;
}

//�������������� ���ָ�ʽ������8999999,Ҳ������999.9999
//С��λ�����ܳ���ָ����digitLen

function FN_QSCheckNumberDigit(val,digitLen,objname)
{
	if (!FN_QSCheckNumber(val,objname))
	{
		return false;
	}
	var dot = val.indexOf(".");
	if (dot > 0)
	{
		frag = val.substr(dot+1);
		if (frag.length > digitLen)
		{
			alert(objname + "С����λ��������");
			return false;
		}
		inte = val.substr(0,dot);
		if (inte.length > 13)
		{
			alert(objname + "��������λ��������");
			return false;
		}
	}
    else  //û��С��
	{
		if (val.length > 13)
		{
			alert(objname + "��������λ��������");
			return false;
		}
	}


	return true;
}
/////////////////////////add by yangxiao 20081206 Start//////////////////////////////////////
//��ί֤������ת���ɵ�˰֤������
function zjlxJwToDs(zjlxJw)
{
    var zjlxDs = "02";
	if(zjlxJw != "" && zjlxJw != null){
        if(zjlxJw == "1"){
            zjlxDs = "02";
            return zjlxDs;
        }
        if(zjlxJw == "3"){
            zjlxDs = "04";
            return zjlxDs;
        }
        if(zjlxJw == "4"){
            zjlxDs = "03";
            return zjlxDs;
        }
        if(zjlxJw == "7"){
            zjlxDs = "06";
            return zjlxDs;
        }
        if(zjlxJw == "10"){
            zjlxDs = "11";
            return zjlxDs;
        }
		//edit by zzb 20090612 ������֤������'13 ע��֤��'��'14	��ְ�ɲ�����֤'
        if(zjlxJw == "2" || zjlxJw == "5" || zjlxJw == "6" || zjlxJw == "8" || zjlxJw == "9" || zjlxJw == "11" || zjlxJw == "12" || zjlxJw == "13" || zjlxJw == "14"){
            zjlxDs = "90";
            return zjlxDs;
        }
    }
}

//��˰֤������ת���ɽ�ί֤������
function zjlxDsToJw(zjlxDs)
{
    var zjlxJw = "1";
	if(zjlxDs != "" && zjlxDs != null){
        if(zjlxDs == "02"){
            zjlxJw = "1";
            return zjlxJw;
        }
        if(zjlxDs == "04"){
            zjlxJw = "3";
            return zjlxJw;
        }
        if(zjlxDs == "03"){
            zjlxJw = "4";
            return zjlxJw;
        }
        if(zjlxDs == "06"){
            zjlxJw = "7";
            return zjlxJw;
        }
        if(zjlxDs == "11"){
            zjlxJw = "10";
            return zjlxJw;
        }
        if(zjlxDs == "90"){
            zjlxJw = "6";
            return zjlxJw;
        }
    }
}

//��ί������;ת���ɵ�˰������;
function fwytJwToDs(fwytJw)
{
    var fwytDs = "03";
	if(fwytJw != "" && fwytJw != null){
        if(fwytJw == "3"){
            fwytDs = "01";
            return fwytDs;
        }
        if(fwytJw == "4" || fwytJw == "5" || fwytJw == "6" || fwytJw == "7"){
            fwytDs = "04";
            return fwytDs;
        }
        if(fwytJw == "1" || fwytJw == "2" || fwytJw == "21" || fwytJw == "10"){
            fwytDs = "03";
            return fwytDs;
        }
    }
}

function delFuhao(number){
    var frag = "";
    if(number != "" && number != null){
        for (var i = 0 ; i < number.length; i++)
        {
            var num = number.substring(i,i+1);
            if(isValidNumber(num)){
                frag = frag + num;
            }
        }
    }else{
        frag = "0.00";
    }
    return frag;
}
/////////////////////////add by yangxiao 20081206 end//////////////////////////////////////


/////////////////////////add by hazhengze 20081201 Start//////////////////////////////////////
/**//*��һλ���ָ�ʽ������λ,��:   9   to   09*/
function formatStr1To2(str_in_formatStr1To2){
	//alert(str_in_formatStr1To2);
	if(str_in_formatStr1To2==null||str_in_formatStr1To2==""||str_in_formatStr1To2=="null"){
		return "";
	}else if(str_in_formatStr1To2.length==1){
		return "0"+str_in_formatStr1To2;
	}
	return str_in_formatStr1To2;
}

function formatDateStr(str_in){
	var rtnStr_tmp="";
	//alert("str_in="+str_in);
	if(str_in==null||str_in==""||str_in=="null"){
		alert("���ڸ�ʽ����");
	}else{
		tmpObj_hyqdsj_Array=str_in.split("-");
		rtnStr_tmp_1=tmpObj_hyqdsj_Array[0];
		rtnStr_tmp_2=formatStr1To2(tmpObj_hyqdsj_Array[1]+"");
		rtnStr_tmp_3=formatStr1To2(tmpObj_hyqdsj_Array[2]+"");
		rtnStr_tmp=""+rtnStr_tmp_1+rtnStr_tmp_2+rtnStr_tmp_3;
		return rtnStr_tmp;
	}
}


/**
*����������ݷ�������Ҫ�����������£�
*1��ͷ��ʶ�Ƿ���ڣ�
*2��ͷ��ʶ���������ͣ�"000001"-�·���"000002"-������
*3��ͷ��ʶ������汾�ţ�"000001"ĿǰΪ�˽��
*˵���������������ݹ淶�����������Ҫ�޸Ĵ˷����������ǰ汾�ŷ�����������
*/
function checkQRCodeHeader(transObj_check){
	//
	var transObjArray_check=transObj_check.split("^");
	//
	//alert("..............1");
	if(transObjArray_check[1]==undefined){
		//alert("ɨ��ͼƬʧ�ܣ�������ɨ����ֹ�¼����Ϣ��");
		return "1";
	}
	//alert("..............2");
	var transObjArray_check_1=transObjArray_check[1].split("_");
	//alert("..............3");
	//
	if(transObj_check==""){
		//alert("ɨ��ͼƬʧ�ܣ�������ɨ����ֹ�¼����Ϣ��");
		return "1";
	}else if(transObjArray_check_1[0]!="object"){//������Ĳ�Ϊ"object_"��ͷ���ʾ����ͼƬɨ�費ȫ����ʾ����ɨ��
		//alert("��ɨ��ȫ����ά������ͼƬ�������޷����룡");
		return "2";
	}else{
		if(transObjArray_check_1[1]=="000001"&&transObjArray_check_1[2]=="000001"){//�·�,�汾��Ϊ"000001"
			return "3";
		}else if(transObjArray_check_1[1]=="000002"&&transObjArray_check_1[2]=="000001"){//���ַ�,�汾��Ϊ"000001"
			return "4";
		}else if(transObjArray_check_1[1]=="000001"&&transObjArray_check_1[2]=="000002"){//�·�,�汾��Ϊ"000002"
			return "3";
		}else if(transObjArray_check_1[1]=="000002"&&transObjArray_check_1[2]=="000002"){//���ַ�,�汾��Ϊ"000002"
			return "4";
		// add by zzb 20090612 begin
		}else if(transObjArray_check_1[1]=="000001"&&transObjArray_check_1[2]=="000003"){//�·�,�汾��Ϊ"000003"
			return "3";
		}else if(transObjArray_check_1[1]=="000002"&&transObjArray_check_1[2]=="000003"){//���ַ�,�汾��Ϊ"000003"
			return "4";
		// add by zzb 20090612 end
		}else{
			alert("ɨ��ͼƬʧ�ܣ�������Դ����ȷ��������ɨ����ֹ�¼����Ϣ��");
			return "1";
		}
	}
}

/////////////////////////add by hazhengze 20081201 end//////////////////////////////////////
