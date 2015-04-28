/**
 *
 *����������
 *objectָ�����Name
 *captionNameָ�����ǩ��caption��������������ʾ����
 *isNullableָ�����Ƿ��Ϊ�գ�false��ʾ����Ϊ�գ�true��ʾ������Ϊ��
 *maxLengthָ�����������󳤶�
 */
 
function GMIT_InputObject(object,captionName, isNullable, maxLength)
{
	this.object = object;
	this.isNullable = isNullable;
	this.captionName = captionName;
	
	
	//��ȡ���������
	//����ڶ����ǩ�ж�����CaptionName���ԣ���Ϊ����������ʾ����
	var objCaption = this.captionName;
	if (objCaption == null)
	{   
		//���û�жԶ�����CaptionName�����ԣ�����alt�е��������
		objCaption = this.object.alt;
	}
	if (objCaption == null || objCaption=="")
	{
		objCaption = obj.object.name;
	}
	this.captionName = objCaption;
	this.maxLength = maxLength;
	this.totalLength = null;
	this.deciamalLength = null;
	this.value = null;
	
	//��ȡ����
	this.getValue = GMIT_IO_getValue;
	this.getName = GMIT_IO_getName;
	this.getCaptionName = GMIT_IO_getCaptionName;
	this.getMaxLength = GMIT_IO_getMaxLength;

	//���ö���ֵ
	this.setValue = GMIT_IO_setValue;
	this.setMaxLength = GMIT_IO_setMaxLength;
	this.setCaptionName = GMIT_IO_setCaptionName;
	
	//��鵥�������Ƿ����ָ���Ĺ���
	this.check_object = GMIT_IO_check_object;
	//�����������Ƿ���Ϲ���
	this.check_aryObject = GMIT_IO_check_aryObject;
	//���������
	this.specialChecker = null;
}

/*
 *
 *��ȡ�����valueֵ
 *
 */
function GMIT_IO_getValue() 
{
	
	if (this.value!=null)
	{
		return this.value;
	}
	
	if (_isArrayObject(this) == true) 
	{
		objLength = this.object.length;
		for (i=0;i<objLength;i++)
		{
			if(this.object[i].checked == true)
			{
				k=i;//Ѱ��radio�����б�ѡ�е�ֵ��λ��
			}		
		}
		this.value = this.object[k].value;//������ֵ
		this.object = this.object[k];//���¸�radio����ֵ������������Ϊ����
		return this.value;
	}

	
	if (this.object.type == "checkbox")
	{
		if(this.object.checked == true)
		{
			this.setValue("1");//���checkbox�ı�ѡ�У���ô����ֵ����1
			return 1;
		}
		return "0";
	}	
	
	return this.object.value;
}

/*
 *
 *��ȡ��������ƣ�����ֵname
 *
 */
function GMIT_IO_getName() 
{
	if (this.name!=null)
	{
		return this.name;
	}
	else
	{
		return this.object.name;
	}
}

/*
 *
 *��ȡ����ı�ǩ
 *
 */
function GMIT_IO_getCaptionName() 
{
	if (this.captionName!=null)
	{
		return this.captionName;
	}
	else
	{
		return this.object.captionName;
	}
}
/*
 *
 *����ַ��������󳤶�
 *
 */
function GMIT_IO_getMaxLength() 
{
	if (this.object.maxLength) 
	{
		return this.maxLength;
	}
	return this.maxLength;
}

/*
 *
 *�����ַ��������󳤶�
 *
 */
function GMIT_IO_setMaxLength(length) 
{
	if (isNaN(length*1)==false) 
	{
		this.maxLength=length;
	}
}
/*
 *
 *����CaptionName
 *
 */
function GMIT_IO_setCaptionName(captionName) 
{
	this.captionName = captionName;
}

/*
 *
 *���ö����Value
 *
 */
function GMIT_IO_setValue(value) 
{
	this.value = value;
}

/*
 *
 *�����������Ƿ����Ҫ��
 *aryObjectsָ��aryObject��ɵ�����
 *���ɹ�����true,ʧ��false
 *
 */
function GMIT_IO_check_aryObject(aryObjects)
{
	for (var i=0;i<aryObjects.length;i++) 
	{
		var returnMessage = GMIT_IO_check_object(aryObjects[i]);
		if (returnMessage!=true) 
		{
			alert("��ʾ: " + returnMessage);
			aryObjects[i].object.focus();
			if (aryObjects[i].object.select!=null)
			{
				aryObjects[i].object.select();
			}
			return false;
		}
	}
}

/**
 *
 * return null ����Ķ����ǿգ����߶���û��value���ԡ�
 *        string message ��鷵�ص���ʾ��Ϣ��
 *        true ͨ����ѯ��
 */

function GMIT_IO_check_object(obj) 
{
	if ((obj==null || obj.object.value==null) && !_isArrayObject(obj))
	{
		return null;
	}

	/*
	if (obj!=null) {
		eval(this.specialChecker+"('"+this.value+"',"+totalLength","+decimalLength+")");
	}
	*/

	//��ȡ���������
	var objCaption = obj.captionName;


	//�����ַ������˵Ŀո�
	var strValue = trimString(obj.getValue());
		
	
	if (obj.isNullable != null && obj.isNullable == false )
	{	
		//����ڶ����ǩ�ж�����isNullable����
		if (strValue == "")
		{
			return objCaption+"�ǹؼ���Ϣ���������룡"
		}
	}

	//��ȡ���ֶ��������󳤶�
	var maxlen = obj.maxLength;

	if (maxlen!=null && strValue != null && strValue != "")
	{	//�ж�������ַ��������Ƿ����Ҫ��
		if (isNaN(maxlen*1)==false) 
		{
			if (getStringLength(strValue)>maxlen) 
			{
				return objCaption+"�������Ϣ̫���������������"+maxlen+"���ַ���";
			}
		}
	}
	if (obj.specialChecker != null)
	{
		var message = obj.specialChecker(obj);
		if (message != true) 
		{
			return message;
		}	
	}
	
	return true;
}

function _isArrayObject(obj) 
{
	//�ж�obj�����ͣ��Ƿ�Ϊradio�������radio����ȡ����ѡ�е�ֵ
	if (obj.object.length!=null && obj.object.item!=null && obj.object[0]!=null && obj.object[0].tagName!=null && obj.object[0].tagName.toLowerCase()=="input" 	&& obj.object[0].type=="radio") 
	{
		return true;
	}	
	return false;
}


function checkFloat(obj)
{
	//��鸡������ĸ�ʽ�Ƿ���ȷ
	if (obj.object.value != null)
	{
		//��ȡ����ĳ���
		var strLength = obj.object.value.length;
		//��ȡС��λ����
		var decimalLength = 0;
		if (obj.object.value.indexOf(".") != -1)
		{
			var decimalLength = strLength - obj.object.value.indexOf(".") - 1;
		}
		
		//�жϳ����Ƿ񳬳�Ҫ��
		if (obj.totalLength < strLength)
		{
			return obj.captionName + "���ܳ��ȳ���Ҫ��(" +obj.totalLength+ ")";
		}
			
		//�ж�С��λ�����Ƿ񳬳�Ҫ��
		if (obj.deciamalLength < decimalLength)
		{
			return obj.captionName + "��С���㳤�ȳ���Ҫ��(" +obj.deciamalLength+ ")";
		}		
			
	}	
	return true;
}


/*
 *���form���ݣ��ж�ָ�������еļ������
 *
 */
function checkInput() 
{
	if (inputGroup.check_group(inputGroup)==true) 
{
		inputGroup.copyValueToForm(inputGroup);
		alert(document.main_form.innerHTML);
	}
}


/**
 *
 * ��aryObjects��ɵ��������
 * 
 */
function GMIT_InputGroup(aryObjects)
{
	this.arrayObjects = new Array();
	if (aryObjects!=null) {
		this.arrayObjects[0] = aryObjects;
	}

	//��Ҫ�������޸Ķ�����в���
	this.addObject = GMIT_IG_addObject;
	this.delObject = GMIT_IG_delObject;
	this.getObjectByIndex = GMIT_IG_getObjectByIndex;
	
	//this.checkInput = GMIT_IG_checkInput;
	this.check_group = GMIT_IG_check_group;
	this.copyValueToForm = GMIT_IG_copyValueToForm;
}

/**
 *
 * ���aryObjects�����Ա
 * obj ָ aryObjects
 * index ָ �����λ�� 
 *
 */
function GMIT_IG_addObject(obj, index) 
{
	if (index!=null)
	{
		this.arrayObjects[index]=obj;
	}
	else
	{
		this.arrayObjects[this.arrayObjects.length]=obj;
	}
}

/**
 *
 * ���������λ����ɾ��aryObjects
 * 
 */
function GMIT_IG_delObject(index) 
{
	this.arrayObjects[index]=null;
}

/**
 *
 * ���������λ������ȡaryObjects��ֵ
 * 
 */
function GMIT_IG_getObjectByIndex(index) 
{
	return this.arrayObjects[index];
}

/**
 *
 * �������ִ�����������ȡaryObjects��ֵ
 * 
 */
function GMIT_IG_getObjectByName(name) 
{
	return this.arrayObjects[name];
}

function GMIT_IG_checkInput()
{
}

/**
 *
 * ����û��������Ƿ����inputGroup�����õ�����
 * �ɹ����� true ,ʧ��false
 */
function GMIT_IG_check_group(inputGroup) {
	for (var i=0;i<inputGroup.arrayObjects.length;i++) 
	{
		if (GMIT_IO_check_aryObject(inputGroup.arrayObjects[i]) == false)
		{
			return false;
		}
	}
	return true;
}

/**
 *
 * ��inputGroup�����еĶ��󿽱���form��
 * 
 */
function GMIT_IG_copyValueToForm(inputGroup) 
{
	for (var i=0;i<inputGroup.arrayObjects.length;i++) 
	{
		for (var j=0;j<inputGroup.arrayObjects[i].length;j++) 
		{
			var element = document.createElement("<input type='hidden' name='"+inputGroup.arrayObjects[i][j].object.name+"'>");
			//element.name = aryObject[i].name;
			element.value = inputGroup.arrayObjects[i][j].getValue();

			//var inputString = "<input type='hidden' name='"+aryObject[i].name+"' value='"+aryObject[i].value+"'"
			document.main_form.insertBefore(element);
		}
	}
}
