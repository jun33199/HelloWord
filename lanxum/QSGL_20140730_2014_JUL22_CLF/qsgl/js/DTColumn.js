//function: constrcutor of DTColumn;
//parameters: numColumnType��type of DTColumn;
//			  strColumnName��name of DTColumn;
//			  numColumnWidth��DTColumn width;
//			  strProperties����ʾColumnһЩ���������
//			  columnCaption����ʾColumn������
//			  isPrimaryKey����ʾ�ж�Column�Ƿ�Ϊ���� true��ʾ�ǣ�false��ʾ��Ĭ��Ϊ�� false
//			  isNull����ʾ�ж�Column�Ƿ����Ϊ�� true��ʾ������Ϊ�գ�false��ʾ����Ϊ�գ�Ĭ��Ϊ����Ϊ��false
//			  maxLength����ʾColumn���Ե���󳤶�
//  		  specialChecker��
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
	//��ʼ��specialChecker.
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
		//���ݷ���������װ�ɷ�������Function()
		this.specialChecker = new Function("value","return "+this.specialChecker);
	}    
    
	return this;
}