//function: constrcutor of DTColumn;
//parameters: numColumnType：type of DTColumn;
//			  strColumnName：name of DTColumn;
//			  numColumnWidth：DTColumn width;
//			  strProperties：表示Column一些特殊的属性
//			  columnCaption：表示Column的名称
//			  isPrimaryKey：表示判断Column是否为主键 true表示是，false表示否，默认为否 false
//			  isNull：表示判断Column是否可以为空 true表示不可以为空，false表示可以为空，默认为可以为空false
//			  maxLength：表示Column可以的最大长度
//  		  specialChecker：
//return: DTColumn object;
function DTColumn(constColumnType,strColumnName,numColumnWidth,aryDataSource, strProperties, columnCaption, isPrimaryKey, isNull, maxLength, specialChecker )
{
	this.Type = constColumnType;
	this.Name = strColumnName;
	this.Width = numColumnWidth;
	this.DataSource = aryDataSource;
	this.isPrimaryKey = isPrimaryKey;
	this.isNull = isNull;
	this.maxLength = maxLength;
	this.columnCaption = columnCaption;
	if (maxLength != null) 
	{
		strProperties = strProperties + " maxLength=" + maxLength;
	}
	if (strProperties!=null) 
	{
		var strValue = strProperties;
		if (this.Type==constCTSelect) 
		{
			var index = strValue.toLowerCase().indexOf("need_autoselect");
			strValue = strValue.substr(0,index)
				+" onfocus=_autoselect_clearinput() onkeydown=_autoselect_keydown() onkeypress=_autoselect_keypress() "
				+strValue.substr(index+"need_autoselect".length);
		}else if (this.Type==constCTDelButton) 
		{
            strValue = strValue+" id =\"" +this.Name+ "\" ";
		}
		this.Properties = strValue;
	} 
	else 
	{
		this.Properties = "";
	}
	//初始化specialChecker.
	this.specialChecker = specialChecker;
    if (this.specialChecker != null) 
    {
		var index = this.specialChecker.indexOf("()");
		if (index>=0)
		{
			this.specialChecker = this.specialChecker.substr(0,index)+"(value)";
		}
		else 
		{
			index = this.specialChecker.indexOf("(");
			if (index>=0)
			{
				this.specialChecker=this.specialChecker.substr(0,index)+"(value,"+this.specialChecker.substr(index+1);
			}
			else 
			{
				this.specialChecker = this.specialChecker+"(value)";
			}
		}
		//根据方法名称组装成方法对象Function()
		this.specialChecker = new Function("value","return "+this.specialChecker);
	}    
    
	return this;
}