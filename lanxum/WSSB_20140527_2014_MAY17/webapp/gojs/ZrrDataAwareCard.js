//function: DataAwareCard的构造函数;
//parameters: objDynamicCard: DynamicCard object;
//return: DataAwareCard Object;
//自然人模块用的卡片式，由于自然人模块页面的card式不规则，所以单独提出来做维护
//由于页面不规则，现在根据特定的id:document.all("fwdwjsjdm")[i]来做付值得判断
//modify by zhangp 2003-10-25

function DataAwareCard(objDynamicCard)
{
	//member variant of  dataAwareCard;
	this.DynamicCard = objDynamicCard;
	this.DataSource = null;
	this.CurrentRow = 0;
	this.TotalRows = 0;
	this.Disabled=false;
	//章鹏修改，添加移动监控
	this.MoveToListeners = new Array();

	//method of dataAwareCard;
	this.autoSetRowDisabled=dac_autoSetRowDisabled;
	this.saveData = dac_saveData;
	this.loadData = dac_loadData;
	this.appendRow = dac_appendRow;
	this.deleteRow = dac_deleteRow;
	this.modifyRow = dac_modifyRow;
	this.firstRow = dac_firstRow;
	this.previousRow = dac_previousRow;
	this.nextRow = dac_nextRow;
	this.lastRow = dac_lastRow;
	this.getRecordIndex = dac_getRecordIndex;
	this.setDataSource = dac_setDataSource;
	this.formatData = dac_formatData;
	this.moveto = dac_moveto;
	this.getData = dac_getData;
	this.getDataAt = dac_getDataAt;
	this.getDataAt = dac_getDataAt;
	this.setDataAt = dac_setDataAt;
	this.getCurrentRow = dac_getCurrentRow;
	this.getColumns = dac_getColumns;

	//章鹏修改，添加移动监控
    this.addMoveToListener = dac_addMoveToListener;
    this.notifyMoveToListener = dac_notifyMoveToListener;
	return this;
}

//function: 设置DataAwareCard的数据源;
//parameters: aryDataSource: 数据源，每条数据包含一个状态字段;
//return: null;
function dac_setDataSource(aryDataSource)
{

	this.DataSource = aryDataSource;
	for (var i = 0 ;i < this.DataSource.length; i++)
	{
		if ((this.DataSource[i][this.DynamicCard.Columns.length] != constObsoleteStatus)
				&&(this.DataSource[i][this.DynamicCard.Columns.length] != constDeleteStatus))
		{
			this.TotalRows++;
		}
	}
	if (this.TotalRows > 0)
	{
		this.CurrentRow = 1;
		this.loadData(this.CurrentRow);
	}
}

//function: 存储DynamicCard中第numIndex行的数据到DataSource中
//parameters:numIndex：指定的行号
//return: null;
function dac_saveData(numIndex)
{
	var objTable = this.DynamicCard.TableID;
	if (objTable == null) return;
	if (numIndex <= 0) return;
	var numRecordIndex = this.getRecordIndex(numIndex);
	if (this.DataSource.length<=0) {
		return;
	}
	for (var i = 0;i<this.DynamicCard.Columns.length;i++)
	{
		switch(this.DynamicCard.Columns[i].Type)
		{
			case constCTDefault:
				this.DataSource[numRecordIndex][i] = dac_trimString(document.all("fwdwjsjdm")[i].innerText);
				break;
			case constCTInput:
				this.DataSource[numRecordIndex][i] = dac_trimString(document.all("fwdwjsjdm")[i].value);
				break;
			case constCTSelect:
			    if (this.DynamicCard.Columns[i].DataSource==null || this.DynamicCard.Columns[i].DataSource.length==null) {
			        break;
			    }
				this.DataSource[numRecordIndex][i] = document.all("fwdwjsjdm")[i].value;
						//this.DynamicCard.Columns[i].DataSource[objTable.rows(i).cells(1).firstChild.selectedIndex][0];
				break;
			case constCTRadioButton:
				this.DataSource[numRecordIndex][i] =
						(document.all("fwdwjsjdm")[i].checked?constTrue:constFalse);
				if (this.DataSource[numRecordIndex][i] == constTrue)
				{	//修改其余record的相应属性
					for (var k = 0;k < this.DataSource.length; k++)
					{
						if (k != numRecordIndex) this.DataSource[k][i] = constFalse;
					}
				}
				break;
			case constCTCheckBox:
				this.DataSource[numRecordIndex][i] =
						(document.all("fwdwjsjdm")[i].firstChild.checked?constTrue:constFalse);
				break;
			case constCTSequence:
				if (this.DataSource[numRecordIndex][i] == null) this.DataSource[numRecordIndex][i] = "";
				break;
			default:
				alert("unknown Column Type");
				break;
		}
	}
}

//function: 从DataSource中读取对应DynamicCard中第numIndex行的数据,
//			显示在DynamicCard中;
//parameters: numIndex：指定的行号
//return: null;
function dac_loadData(numIndex)
{
	var objTable = this.DynamicCard.TableID;
	if (objTable == null) return;
	if (numIndex < 0) return;
	if (numIndex == 0)
	{
		this.DynamicCard.clearRow();
		//如果是最后一条记录disable所有的输入域，新增的时候可以恢复编辑
		this.DynamicCard.disableRow(true);
		return;
	}
	var numRecordIndex = this.getRecordIndex(numIndex);
	if (this.DataSource.length<=0) {
		//如果datasource.length=0，新增的时候可以恢复编辑
		this.DynamicCard.disableRow(true);
		return;
	}
	for (var i = 0;i<this.DynamicCard.Columns.length;i++)
	{
		switch(this.DynamicCard.Columns[i].Type)
		{
			case constCTDefault:
				document.all("fwdwjsjdm")[i].innerText = this.DataSource[numRecordIndex][i];//DataSource下标从零开始
				break;
			case constCTInput:
				document.all("fwdwjsjdm")[i].value = this.DataSource[numRecordIndex][i];
				break;
			case constCTSelect:
			    if (this.DynamicCard.Columns[i].DataSource==null || this.DynamicCard.Columns[i].DataSource.length==null) {
			        break;
			    }
			    document.all("fwdwjsjdm")[i].value = this.DataSource[numRecordIndex][i];
				//for (var k = 0;k < this.DynamicCard.Columns[i].DataSource.length;k++)
				//{
				//	if (this.DynamicCard.Columns[i].DataSource[k][0] == this.DataSource[numRecordIndex][i])
				//	{
				//		objTable.rows(i).cells(1).firstChild.selectedIndex = k;
				//		break;
				//	}
				//}
				break;
			case constCTRadioButton:
			    if(this.DataSource[numRecordIndex][i] == constTrue)
			    {
			        document.all("fwdwjsjdm")[i].checked = true;
			    }
			    break;
				document.all("fwdwjsjdm")[i].checked =
						(this.DataSource[numRecordIndex][i] == constTrue ? true : false);
				break;
			case constCTCheckBox:
				document.all("fwdwjsjdm")[i].checked =
						(this.DataSource[numRecordIndex][i] == constTrue ? true : false);
				break;
			case constCTSequence:
				//do nothing
				document.all("fwdwjsjdm")[i].innerText = numIndex;
				break;
			default:
				alert("unknown Column Type");
				break;
		}//end of switch
	} // end for
	
	this.autoSetRowDisabled(numIndex);

}

//function: 新增一行数据;
//parameters: aryDataSource:新增数据，如果aryDataSource == null,则新增数据各字段为缺省值
//return: null;
function dac_appendRow(arydata)
{
	this.saveData(this.CurrentRow);
	var objTable = this.DynamicCard.TableID;
	if (objTable == null) return;
	var numRecordCount = this.DataSource.length;
	this.DataSource[numRecordCount] = new Array();
	if (arydata != null)
	{
		this.DataSource[numRecordCount] = arydata;
	}
	else
	{	//creat Default data;
		for (var i = 0;i < this.DynamicCard.Columns.length;i++)
		{
			switch(this.DynamicCard.Columns[i].Type)
			{
				case constCTDefault:
					this.DataSource[numRecordCount][i] = " ";
					break;
				case constCTInput:
					this.DataSource[numRecordCount][i] = "";
					break;
				case constCTSelect:
					this.DataSource[numRecordCount][i] =
							this.DynamicCard.Columns[i].DataSource[0][0];
					break;
				case constCTRadioButton:
					this.DataSource[numRecordCount][i] = constFalse;
					break;
				case constCTCheckBox:
					this.DataSource[numRecordCount][i] = constFalse;
					break;
				case constCTSequence:
					this.DataSource[numRecordCount][i] = "";
					break;
				default:
					alert("unknown Column Type");
					break;
			}//end of switch;
		}
		this.DataSource[numRecordCount][i] = constNewStatus;
	}
	this.TotalRows++;
	this.CurrentRow = this.TotalRows;
	this.loadData(this.CurrentRow);
}

//function: 修改DataSource中相应数据的状态字段，更新DynamicCard显示的内容
//parameters:numIndex：指定的显示记录号
//return:
function dac_deleteRow(numIndex)
{
	if ((numIndex > 0)&& (numIndex <= this.TotalRows))
	{
		var numFieldIndex = this.DynamicCard.Columns.length;
		var numRecordIndex = this.getRecordIndex(numIndex);
		if (this.DataSource[numRecordIndex][numFieldIndex] == constNewStatus)
 			this.DataSource[numRecordIndex][numFieldIndex] = constObsoleteStatus;
		if ((this.DataSource[numRecordIndex][numFieldIndex] == constLoadStatus)||
				(this.DataSource[numRecordIndex][numFieldIndex] == constUpdateStatus))
			this.DataSource[numRecordIndex][numFieldIndex] = constDeleteStatus;
		this.TotalRows--;
		if (this.CurrentRow>this.TotalRows) 
		{
			this.CurrentRow = this.TotalRows;
		} 
		this.loadData(this.CurrentRow);
		//如果是最后一条记录disable所有的输入域，新增的时候可以恢复编辑
		if (this.CurrentRow==0)
		{
			this.DynamicCard.disableRow(true);
		}
	}
}

//function: 设置DynamicCard状态为Enabled，并且修改DataSource中相应数据状态字段为constUpdateStatus;
//parameters: numIndex：指定的显示记录号
//return: null;
function dac_modifyRow(numIndex)
{
	if ((numIndex > 0)&& (numIndex <= this.TotalRows))
	{
		var numFieldIndex = this.DynamicCard.Columns.length;
		var numRecordIndex = this.getRecordIndex(numIndex);
		if ((this.DataSource[numRecordIndex][numFieldIndex] == constLoadStatus)||
				(this.DataSource[numRecordIndex][numFieldIndex] == constUpdateStatus))
		{
			this.DynamicCard.disableRow(false);
			this.DataSource[numRecordIndex][numFieldIndex] = constUpdateStatus;
		}
	}
}

//function: 移动到第一条显示记录
//parameters:
//return:
function dac_firstRow()
{
	this.moveto(1);
}

//function: 移动到最后一条显示记录
//parameters:
//return:
function dac_lastRow()
{
	this.moveto(this.TotalRows);
}

//function: 移动到上一条显示记录
//parameters:
//return:
function dac_previousRow()
{
	this.moveto(this.CurrentRow-1);
}

//function: 移动到下一条显示记录
//parameters:
//return:
function dac_nextRow()
{
	this.moveto(this.CurrentRow+1);
}
//function: 移动到第numIndex条显示记录
//parameters:
//return:
function dac_moveto(numIndex)
{
	if ((numIndex > 0)&&(numIndex <= this.TotalRows))
	{
		this.saveData(this.CurrentRow);
		this.CurrentRow = numIndex;
		this.loadData(this.CurrentRow);
		//章鹏修改，添加移动监听
		this.notifyMoveToListener(numIndex);
	}
}

//function: 得到指定显示记录在DataSource中的实际位置
//parameters: numIndex：指定的显示记录号
//return: 对应数据在DataSource中的位置; -1, 越界
//注意：显示序列从1开始，数组序列从0开始
function dac_getRecordIndex(numIndex)
{
	if (numIndex>this.DataSource.length || numIndex<=0) {
		return -1;
	}
	var i=0;
	var j=0;
	while (i<this.DataSource.length)
	{
		//设定DataSource最后一列为状态位
		if ((this.DataSource[i][this.DataSource[i].length-1] != constDeleteStatus)
				&&(this.DataSource[i][this.DataSource[i].length-1] != constObsoleteStatus)) j++;
		if (j == numIndex) break;
		i++;
	} 
	if (i<this.DataSource.length) {
		return i;
	}
	return -1;
}

//function: 格式化DataSource所有状态标志不是constObsoleteStatus的数据
//parameters: RecordSeparator:记录分隔符 FieldSeparator: 字段分隔符
//return: 格式化的数据
function dac_formatData(RecordSeparator,FieldSeparator)
{
	var strResult = "";
	for (var i = 0; i < this.DataSource.length;i++)
	{
		var flag = this.DataSource[i][this.DynamicCard.Columns.length];
		if (flag!=null &&
			flag!="undefined" &&
			flag!=constLoadStatus && 
			flag!=constNewStatus && 
			flag!=constUpdateStatus) {
			continue;
		}
		//if (i != 0) strResult = strResult + RecordSeparator;
        if (strResult != "") strResult = strResult + RecordSeparator;
		for (var j = 0; j < this.DataSource[i].length;j++)
		{
			if (j != 0) strResult = strResult + FieldSeparator;
			strResult = strResult+this.DataSource[i][j];
		}
	}
	return strResult;
}

function dac_getData() {
	var returnValue = new Array();
	for (var i=0;i<this.DataSource.length;i++) {
		var flag = this.DataSource[i][this.DynamicCard.Columns.length];
		if (flag!=null &&
			flag!="undefined" &&
			flag!=constLoadStatus && 
			flag!=constNewStatus && 
			flag!=constUpdateStatus) {
			continue;
		}
		returnValue[returnValue.length] = new Array();
		for (var j=0;j<this.DataSource[i].length;j++) {
			returnValue[returnValue.length-1][j] = this.DataSource[i][j];
		}
	}
	return returnValue;
}

function dac_getDataAt(rowIndex) {
	var recordIndex = this.getRecordIndex(rowIndex);
	if (recordIndex<0) {
		window.alert("指定的显示列越界！");
	}
	return this.DataSource[recordIndex];
}

function dac_setDataAt(rowIndex, dataArray) {
	var recordIndex = this.getRecordIndex(rowIndex);
	if (recordIndex<0) {
		window.alert("指定的显示列越界！");
	}
	this.DataSource[recordIndex] = dataArray;
	this.loadData(rowIndex);
}

// 去掉字符串前后的空格。
// @parameter strValue 需要处理的字符串。
// @return String 前后空格和制表符都去掉
function dac_trimString(strValue) {
    if (strValue==null) {
        return null;
    }
    var returnValue = strValue;

    //删除字符串前面的空格(=32=0x20)和制表符(=09=0x09)
    //以及中文字符的空格(=41377=0xA1A1)
    while (returnValue.length>0) {
        var code = returnValue.charCodeAt(0);
        if (code==32 || code==9 || code==41377) {
            returnValue = returnValue.substr(1);
        } else {
            break;
        }
    }

    //删除字符串后面的空格(SPACE=20)和制表符(TAB=09)
    //以及中文字符的空格(=41377=0xA1A1)
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

function dac_getCurrentRow()
{
	return this.CurrentRow;
}

function dac_addMoveToListener(listener)
{
	this.MoveToListeners[this.MoveToListeners.length] = listener;
}

function dac_notifyMoveToListener(rowIndex)
{
	for (var i=0;i<this.MoveToListeners.length;i++)
	{
		var listener = this.MoveToListeners[i];
		eval(listener+"("+rowIndex+")");
	}
}

function dac_getColumns()
{
	return this.DynamicCard.Columns;
}

function dac_autoSetRowDisabled(rowIndex)
{
	var boolflag = this.DataSource[this.getRecordIndex(rowIndex)][this.DynamicCard.Columns.length];
	this.DynamicCard.disableRow((boolflag == constLoadStatus) || this.Disabled);//|| (boolflag == constUpdateStatus)
}