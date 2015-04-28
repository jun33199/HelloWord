/**
 *
 *创建检查对象
 *object指对象的Name
 *captionName指对象标签的caption，用于做错误提示名称
 *isNullable指对象是否可为空，false表示可以为空，true表示不可以为空
 *maxLength指对象允许的最大长度
 */
 
function GMIT_InputObject(object,captionName, isNullable, maxLength)
{
	this.object = object;
	this.isNullable = isNullable;
	this.captionName = captionName;
	
	
	//获取对象的名称
	//如果在对象标签中定义了CaptionName属性，此为输入框对象提示名称
	var objCaption = this.captionName;
	if (objCaption == null)
	{   
		//如果没有对对象定义CaptionName的属性，则用alt中的文字替代
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
	
	//获取对象
	this.getValue = GMIT_IO_getValue;
	this.getName = GMIT_IO_getName;
	this.getCaptionName = GMIT_IO_getCaptionName;
	this.getMaxLength = GMIT_IO_getMaxLength;

	//设置对象值
	this.setValue = GMIT_IO_setValue;
	this.setMaxLength = GMIT_IO_setMaxLength;
	this.setCaptionName = GMIT_IO_setCaptionName;
	
	//检查单个对象是否符合指定的规则
	this.check_object = GMIT_IO_check_object;
	//检查对象数组是否符合规则
	this.check_aryObject = GMIT_IO_check_aryObject;
	//特殊规则检查
	this.specialChecker = null;
}

/*
 *
 *获取对象的value值
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
				k=i;//寻找radio数组中被选中的值的位置
			}		
		}
		this.value = this.object[k].value;//给对象赋值
		this.object = this.object[k];//重新给radio对象赋值，从数组对象变为数组
		return this.value;
	}

	
	if (this.object.type == "checkbox")
	{
		if(this.object.checked == true)
		{
			this.setValue("1");//如果checkbox的被选中，那么他的值就是1
			return 1;
		}
		return "0";
	}	
	
	return this.object.value;
}

/*
 *
 *获取对象的名称，返回值name
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
 *获取对象的标签
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
 *获得字符允许的最大长度
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
 *设置字符允许的最大长度
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
 *设置CaptionName
 *
 */
function GMIT_IO_setCaptionName(captionName) 
{
	this.captionName = captionName;
}

/*
 *
 *设置对象的Value
 *
 */
function GMIT_IO_setValue(value) 
{
	this.value = value;
}

/*
 *
 *检查数组对象是否符合要求
 *aryObjects指由aryObject组成的数组
 *检查成功返回true,失败false
 *
 */
function GMIT_IO_check_aryObject(aryObjects)
{
	for (var i=0;i<aryObjects.length;i++) 
	{
		var returnMessage = GMIT_IO_check_object(aryObjects[i]);
		if (returnMessage!=true) 
		{
			alert("提示: " + returnMessage);
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
 * return null 传入的对象是空，或者对象没有value属性。
 *        string message 检查返回的提示消息。
 *        true 通过查询。
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

	//获取对象的名称
	var objCaption = obj.captionName;


	//过滤字符串两端的空格！
	var strValue = trimString(obj.getValue());
		
	
	if (obj.isNullable != null && obj.isNullable == false )
	{	
		//如果在对象标签中定义了isNullable属性
		if (strValue == "")
		{
			return objCaption+"是关键信息，必须输入！"
		}
	}

	//获取该字段允许的最大长度
	var maxlen = obj.maxLength;

	if (maxlen!=null && strValue != null && strValue != "")
	{	//判断输入的字符串长度是否符合要求
		if (isNaN(maxlen*1)==false) 
		{
			if (getStringLength(strValue)>maxlen) 
			{
				return objCaption+"输入的信息太长，允许输入最多"+maxlen+"个字符！";
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
	//判断obj的类型，是否为radio，如果是radio，则取出被选中的值
	if (obj.object.length!=null && obj.object.item!=null && obj.object[0]!=null && obj.object[0].tagName!=null && obj.object[0].tagName.toLowerCase()=="input" 	&& obj.object[0].type=="radio") 
	{
		return true;
	}	
	return false;
}


function checkFloat(obj)
{
	//检查浮点运算的格式是否正确
	if (obj.object.value != null)
	{
		//获取浮点的长度
		var strLength = obj.object.value.length;
		//获取小数位长度
		var decimalLength = 0;
		if (obj.object.value.indexOf(".") != -1)
		{
			var decimalLength = strLength - obj.object.value.indexOf(".") - 1;
		}
		
		//判断长度是否超出要求
		if (obj.totalLength < strLength)
		{
			return obj.captionName + "的总长度超过要求(" +obj.totalLength+ ")";
		}
			
		//判断小数位长度是否超出要求
		if (obj.deciamalLength < decimalLength)
		{
			return obj.captionName + "的小数点长度超过要求(" +obj.deciamalLength+ ")";
		}		
			
	}	
	return true;
}


/*
 *检查form内容，判断指定数组中的检查内容
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
 * 由aryObjects组成的数组对象
 * 
 */
function GMIT_InputGroup(aryObjects)
{
	this.arrayObjects = new Array();
	if (aryObjects!=null) {
		this.arrayObjects[0] = aryObjects;
	}

	//对要被检查的修改对象进行操作
	this.addObject = GMIT_IG_addObject;
	this.delObject = GMIT_IG_delObject;
	this.getObjectByIndex = GMIT_IG_getObjectByIndex;
	
	//this.checkInput = GMIT_IG_checkInput;
	this.check_group = GMIT_IG_check_group;
	this.copyValueToForm = GMIT_IG_copyValueToForm;
}

/**
 *
 * 添加aryObjects数组成员
 * obj 指 aryObjects
 * index 指 数组的位置 
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
 * 根据数组的位置来删除aryObjects
 * 
 */
function GMIT_IG_delObject(index) 
{
	this.arrayObjects[index]=null;
}

/**
 *
 * 根据数组的位置来获取aryObjects的值
 * 
 */
function GMIT_IG_getObjectByIndex(index) 
{
	return this.arrayObjects[index];
}

/**
 *
 * 根据名字从数组中来获取aryObjects的值
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
 * 检查用户的输入是否符合inputGroup中设置的条件
 * 成功返回 true ,失败false
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
 * 将inputGroup中所有的对象拷贝到form里
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
