//function: DataAwareTable的构造函数
//parameters: objDynamicTable: DynamicTable object;
//							   如果DynamicTable中包含constCTSequence类型字段，则在DataSource中要有预留字段对应，
//							   但是既不会将预留字段中的值显示在DynamicTable中，
//							   也不会将DynamicTable中的序号写入预留字段，
//							   因此可以在预留字段中存储不想在屏幕中显示的数据。
//return: DataAwareTable Object;
function DataAwareTable(objDynamicTable)
{
	//member variant of  DataAwareTable;
	this.DynamicTable = objDynamicTable;
	this.DynamicTable.MultiRowEdit = false;
	this.DataSource = null;
	this.Disabled=false;
    this.MoveToListeners = new Array();

	//method of DataAwareTable;
	this.autoSetRowDisabled = dat_autoSetRowDisabled;
	this.saveAllData = dat_saveAllData;
	this.saveData = dat_saveData;
	this.loadAllData = dat_loadAllData;
	this.loadData = dat_loadData;
	this.appendRow = dat_appendRow;
	this.deleteRow = dat_deleteRow;
	this.modifyRow = dat_modifyRow;
	this.paintRow = dat_paintRow;
	this.firstRow = dat_firstRow;
	this.previousRow = dat_previousRow;
	this.nextRow = dat_nextRow;
	this.lastRow = dat_lastRow;
	this.moveto = dat_moveto;
	this.getRecordIndex = dat_getRecordIndex;
	this.setDataSource = dat_setDataSource;
	this.formatData = dat_formatData;
	this.formatData2 = dat_formatData2;
	this.getCurrentRow = dat_getCurrentRow;
	this.getRowSize = dat_getRowSize;
	this.getData = dat_getData;
	this.getDataAt = dat_getDataAt;
	this.setDataAt = dat_setDataAt;
	this.getRowAt = dat_getRowAt;
	this.getCellAt = dat_getCellAt;
	this.focusAt = dat_focusAt;
	this.getCellsAt = dat_getCellsAt
    this.formatDataWithDelete = dat_formatDataWithDelete;
    //add by liqiang 2003-04-01
    this.doMouseDown = dat_doMouseDown;
    this.DynamicTable.Owner = this;
    
    this.addMoveToListener = dat_addMoveToListener;
    this.notifyMoveToListener = dat_notifyMoveToListener;
    
    this.getColumns = dat_getColumns;

	return this;
}
//function: 设置DataAwareTable的数据源;
//parameters: aryDataSource: 数据源，每条数据包含一个状态字段;
//return: null;
function dat_setDataSource(aryDataSource)
{
	this.DataSource = aryDataSource;
	this.loadAllData();
	if (this.DataSource.length==0) {
		return;
	}
	if (this.DynamicTable.CurrentRow>0)
	{
		this.autoSetRowDisabled(this.DynamicTable.CurrentRow);
	}
}

//function: 存储DynamicTable中所有的数据到DataSource中;
//parameters:
//return: null;
function dat_saveAllData()
{
	var objTable = this.DynamicTable.TableID;
	if (objTable == null) return;
	for (var i = 1;i < objTable.rows.length;i++)
	{
		this.saveData(i);
	}
}

//function: 存储DynamicTable中第numIndex行的数据到DataSource中
//parameters:numIndex：指定的行号
//return: null;
function dat_saveData(numIndex)
{
	var objTable = this.DynamicTable.TableID;
	if (objTable == null) return;
	if (numIndex <= 0) return;
	var numRecordIndex = this.getRecordIndex(numIndex);
	//add by zhangp 2003-10-24，在卡片式的时候删除到最后一条数据的时候numIndex始终是1，所以直接返回
	if(numRecordIndex<0) return;
	for (var i = 0;i<objTable.rows(0).cells.length;i++)
	{
		switch(this.DynamicTable.Columns[i].Type)
		{
			case constCTDefault:
				this.DataSource[numRecordIndex][i]=dat_trimString(objTable.rows(numIndex).cells(i).innerText);
				break;
			case constCTHideTD:
				this.DataSource[numRecordIndex][i]=dat_trimString(objTable.rows(numIndex).cells(i).innerText);
				break;
			case constCTInput:
				this.DataSource[numRecordIndex][i]=dat_trimString(objTable.rows(numIndex).cells(i).firstChild.value);
				break;
			case constCTSelect:
				//var selectedIndex = objTable.rows(numIndex).cells(i).firstChild.selectedIndex;
				//if (selectedIndex>=0) {
					this.DataSource[numRecordIndex][i]=objTable.rows(numIndex).cells(i).firstChild.value;
				//			this.DynamicTable.Columns[i].DataSource[selectedIndex][0];
				//} else {
				//	this.DataSource[numRecordIndex][i]="";
				//}
				break;
			case constCTRadioButton:
				this.DataSource[numRecordIndex][i]=
						(objTable.rows(numIndex).cells(i).firstChild.checked?constTrue:constFalse);
				if (this.DataSource[numRecordIndex][i] == constTrue)
				{	//修改其余record的相应属性
					for (var k = 0;k < this.DataSource.length; k++)
					{
						if (k != numRecordIndex)
						this.DataSource[k][i] = constFalse;
					}
				}
				break;
			case constCTCheckBox:
				this.DataSource[numRecordIndex][i]=
						(objTable.rows(numIndex).cells(i).firstChild.checked?constTrue:constFalse);
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

//function: 从DataSource中读取所有状态标志不是constDeleteStatus和constObsoleteStatus的数据,
//			显示在DynamicTable中;
//parameters:
//return: null;
function dat_loadAllData()
{
	for (var i = this.DynamicTable.TableID.rows.length-1;i > 0;i--)
	{
		this.DynamicTable.deleteRow(i);
	}
	var objTable = this.DynamicTable.TableID;
	if (objTable == null) return;
	var j=0;
	for (var i = 0;i < this.DataSource.length;i++)
	{
		if ((this.DataSource[i][this.DynamicTable.Columns.length] == constDeleteStatus)
				||(this.DataSource[i][this.DynamicTable.Columns.length] == constObsoleteStatus))
			continue;
		j++;
		if (j >= objTable.rows.length) this.DynamicTable.appendRow();//调用DynamicTable.appendRow(); don't create new record
		this.loadData(j);
	}
}

//function: 从DataSource中读取对应DynamicTable中第numIndex行的数据,
//			显示在DynamicTable中;
//parameters: numIndex：指定的行号
//return: null;
function dat_loadData(numIndex)
{
	var objTable = this.DynamicTable.TableID;
	if (objTable == null) return;
	if (numIndex <= 0) return;
	var numRecordIndex = this.getRecordIndex(numIndex);
	for (var i = 0;i<objTable.rows(0).cells.length;i++)
	{
		switch(this.DynamicTable.Columns[i].Type)
		{
			case constCTDefault:
				objTable.rows(numIndex).cells(i).innerText = this.DataSource[numRecordIndex][i];//DataSource下标从零开始
				break;
			case constCTHideTD:
				objTable.rows(numIndex).cells(i).innerText = this.DataSource[numRecordIndex][i];//DataSource下标从零开始
				break;
			case constCTInput:
				objTable.rows(numIndex).cells(i).firstChild.value = this.DataSource[numRecordIndex][i];
				break;
			case constCTSelect:
				//for (var k = 0;k < this.DynamicTable.Columns[i].DataSource.length;k++)
				//{
				//	if (this.DynamicTable.Columns[i].DataSource[k][0] == this.DataSource[numRecordIndex][i])
				//	{
				//		objTable.rows(numIndex).cells(i).firstChild.selectedIndex = k;
						objTable.rows(numIndex).cells(i).firstChild.value = this.DataSource[numRecordIndex][i];
				//		break;
				//	}
				//}
				break;
			case constCTRadioButton:
				objTable.rows(numIndex).cells(i).firstChild.checked =
						(this.DataSource[numRecordIndex][i] == constTrue ? true : false);
				break;
			case constCTCheckBox:
				objTable.rows(numIndex).cells(i).firstChild.checked =
						(this.DataSource[numRecordIndex][i] == constTrue ? true : false);
				break;
			case constCTSequence:
				//do nothing
				break;
			default:
				alert("unknown Column Type");
				break;
		}//end of switch
	}
	//add
	
	 //objTable.rows(0).cells(i).className="2-td2-center";
	// alert("jdie");
	//end
	this.autoSetRowDisabled(numIndex);
}

//function: 新增一行数据;
//parameters: aryDataSource:新增数据，如果aryDataSource == null,则新增数据各字段为缺省值
//return: null;
function dat_appendRow(aryData)
{
	this.saveData(this.DynamicTable.CurrentRow);
	var objTable = this.DynamicTable.TableID;
	if (objTable == null) return;
	var numRecordCount = this.DataSource.length;
	this.DataSource[numRecordCount] = new Array();
	if (aryData != null)
	{
		this.DataSource[numRecordCount] = aryData;
	}
	else
	{	//creat Default data;
		for (var i = 0;i<objTable.rows(0).cells.length;i++)
		{
			switch(this.DynamicTable.Columns[i].Type)
			{
				case constCTDefault:
					this.DataSource[numRecordCount][i] = " ";
					break;
				case constCTHideTD:
					this.DataSource[numRecordCount][i] = " ";
					break;
				case constCTInput:
					this.DataSource[numRecordCount][i] = "";
					break;
				case constCTSelect:
					this.DataSource[numRecordCount][i] =
							this.DynamicTable.Columns[i].DataSource[0][0];
					break;
				case constCTRadioButton:
					this.DataSource[numRecordCount][i] = constFalse;
					break;
				case constCTCheckBox:
					this.DataSource[numRecordCount][i] = constFalse;
					break;
				case constCTSequence:
					//do nothing
					this.DataSource[numRecordCount][i] = "";
					break;
				default:
					alert("unknown Column Type");
					break;
			}//end of switch;
		}
		this.DataSource[numRecordCount][i] = constNewStatus;
	}
	this.DynamicTable.appendRow();
	this.loadData(objTable.rows.length-1);
}

//function: 从DynamicTable中删除第numIndex行，并更改DataSource中相应数据的状态字段
//parameters:numIndex：指定的行号
//return:
function dat_deleteRow(numIndex)
{
	if ((numIndex > 0)&& (numIndex < this.DynamicTable.TableID.rows.length))
	{
		this.DynamicTable.deleteRow(numIndex);
		var numFieldIndex = this.DynamicTable.Columns.length;
		var numRecordIndex = this.getRecordIndex(numIndex);
		if (this.DataSource[numRecordIndex][numFieldIndex] == constNewStatus)
		{
			this.DataSource[numRecordIndex][numFieldIndex] = constObsoleteStatus;
		}
		if ((this.DataSource[numRecordIndex][numFieldIndex] == constLoadStatus)||
				(this.DataSource[numRecordIndex][numFieldIndex] == constCanEditStatus)||
				(this.DataSource[numRecordIndex][numFieldIndex] == constUpdateStatus))
		{
			this.DataSource[numRecordIndex][numFieldIndex] = constDeleteStatus;
		}
		var numCurRow = this.DynamicTable.CurrentRow;
		if (this.getRecordIndex(numCurRow)<0) 
		{
			return;
		}
		this.autoSetRowDisabled(numCurRow);
	}
}

//function: 设置DynamicTable中第numIndex行状态为Enabled，并且修改DataSource中相应数据状态字段为constUpdateStatus;
//parameters: numIndex：指定的行号
//return: null;
function dat_modifyRow(numIndex)
{

	if ((numIndex > 0)&& (numIndex < this.DynamicTable.TableID.rows.length))
	{
		
		var numFieldIndex = this.DynamicTable.Columns.length;
		var numRecordIndex = this.getRecordIndex(numIndex);
		if ((this.DataSource[numRecordIndex][numFieldIndex] == constLoadStatus)|| 
				(this.DataSource[numRecordIndex][numFieldIndex] == constCanEditStatus) ||
				(this.DataSource[numRecordIndex][numFieldIndex] == constUpdateStatus))
		{
			
			this.DynamicTable.disableRow(numIndex,(false|| this.Disabled));
			this.DataSource[numRecordIndex][numFieldIndex] = constUpdateStatus;
			
		}
	}	
}

//function: 设置是否高亮显示DynamicTable中第numIndex行
//parameters:numIndex :指定的行号
//			 boolHightLight:高亮显示标志
//return:
function dat_paintRow(numIndex,boolHighLight)
{
	this.DynamicTable.panitRow(numIndex,boolHighLight);
}

//function: 移动到第一行
//parameters:
//return:
function dat_firstRow()
{
	this.moveto(1);
}

//function: 移动到最后一行
//parameters:
//return:
function dat_lastRow()
{
	this.moveto(this.DynamicTable.TableID.rows.length-1);
}

//function:移动到前一行
//parameters:
//return:
function dat_previousRow()
{
	this.moveto(this.DynamicTable.CurrentRow-1);
}

//function:移动到后一行
//parameters:
//return:
function dat_nextRow()
{
	this.moveto(this.DynamicTable.CurrentRow+1);
}

//function: 移动到第numIndex行
//parameters：numIndex：指定的行号
//return:
function dat_moveto(numIndex)
{
	if (this.Disabled==false)
	{
		this.saveData(this.DynamicTable.CurrentRow);
	}
	if (this.DynamicTable.moveto(numIndex) == true)
	{
		//从数据库中取出的记录不允许修改？
		this.autoSetRowDisabled(numIndex);
		
		//激发moveto事件监听器		
		this.notifyMoveToListener(numIndex);
	}
	
}

//function: 得到对应DynamicTable中第numIndex行的数据在DataSource中的位置
//parameters: numIndex：指定的行号
//return: 对应数据在DataSource中的位置;-1,越界。
//注意：显示序列从1开始，数组序列从0开始
function dat_getRecordIndex(numIndex)
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
function dat_formatData(RecordSeparator,FieldSeparator)
{
	var strResult = "";
	for (var i = 0; i < this.DataSource.length;i++)
	{
		var flag = this.DataSource[i][this.DynamicTable.Columns.length];
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
//function: 格式化DataSource所有状态标志不是constObsoleteStatus的数据
//parameters: RecordSeparator:记录分隔符 FieldSeparator: 字段分隔符
//return: 格式化的数据
function dat_formatData2(RecordSeparator,FieldSeparator)
{
	var strResult = "";
	for (var i = 0; i < this.DataSource.length;i++)
	{
		var flag = this.DataSource[i][this.DynamicTable.Columns.length];
		if (flag!=null &&
			flag!="undefined" &&
			flag!=constLoadStatus &&
			flag!=constNewStatus &&
			flag!=constCanEditStatus &&
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
function dat_getCurrentRow() {
	return this.DynamicTable.CurrentRow;
}

function dat_getRowSize() {
	var size = 0;
	for (var i=0;i<this.DataSource.length;i++) {
		var flag = this.DataSource[i][this.DynamicTable.Columns.length];
		if (flag!=null &&
			flag!="undefined" &&
			flag!=constLoadStatus &&
			flag!=constNewStatus &&
			flag!=constCanEditStatus &&
			flag!=constUpdateStatus) {
			continue;
		}
		size++;
	}
	return size;

}

function dat_getData() {
	var returnValue = new Array();
	for (var i=0;i<this.DataSource.length;i++) {
		var flag = this.DataSource[i][this.DynamicTable.Columns.length];
		if (flag!=null &&
			flag!="undefined" &&
			flag!=constLoadStatus &&
			flag!=constNewStatus &&
			flag!=constCanEditStatus &&
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

function dat_getDataAt(rowIndex) {
	var recordIndex = this.getRecordIndex(rowIndex);
	if (recordIndex<0) {
		return null;
		//window.alert("指定的显示列越界！");
	}
	return this.DataSource[recordIndex];
}

function dat_setDataAt(rowIndex, dataArray) {
	var recordIndex = this.getRecordIndex(rowIndex);
	if (recordIndex<0) {
		window.alert("指定的显示列越界！");
	}
	this.DataSource[recordIndex] = dataArray;
	this.loadData(this.getCurrentRow())
}
function dat_getRowAt (rowIndex) {
	return this.DynamicTable.getRowAt(rowIndex);
}

function dat_getCellAt (rowIndex,columnIndex) {
	return this.DynamicTable.getCellAt(rowIndex,columnIndex);
}

function dat_focusAt(rowIndex, columnIndex) {
	return this.DynamicTable.focusAt(rowIndex,columnIndex);
}
function dat_getCellsAt(rowIndex) {
	return this.DynamicTable.getCellsAt(rowIndex);
}
//function: 格式化DataSource所有状态标志不是constObsoleteStatus的数据
//parameters: RecordSeparator:记录分隔符 FieldSeparator: 字段分隔符
//return: 格式化的数据
function dat_formatDataWithDelete(RecordSeparator,FieldSeparator)
{
    
	var strResult = "";
	for (var i = 0; i < this.DataSource.length;i++)
	{
		var flag = this.DataSource[i][this.DynamicTable.Columns.length];
		
		if (flag!=null &&
			flag!="undefined" &&
			flag!=constLoadStatus &&
			flag!=constNewStatus &&
			flag!=constUpdateStatus &&
			flag!=constDeleteStatus) {
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

// 去掉字符串前后的空格。
// @parameter strValue 需要处理的字符串。
// @return String 前后空格和制表符都去掉
function dat_trimString(strValue) {
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

//add by liqiang 2003-04-01
function dat_doMouseDown() {
	var rowIndex = event.srcElement.innerText*1;
	this.moveto(rowIndex);
	
	
	/* //modify by wanghaijuan in 2003-9-12
	obj=window.event.srcElement;
	currentRow=obj.cells[0].innerText*1;
	this.moveto(currentRow);
	//end by*/
}

function dat_addMoveToListener(listener)
{
	this.MoveToListeners[this.MoveToListeners.length] = listener;
}

function dat_notifyMoveToListener(rowIndex)
{
	for (var i=0;i<this.MoveToListeners.length;i++)
	{
		var listener = this.MoveToListeners[i];
		eval(listener+"("+rowIndex+")");
	}
}
function dat_getColumns()
{
	return this.DynamicTable.Columns;
}

function dat_autoSetRowDisabled(rowIndex)
{
	var boolflag = this.DataSource[this.getRecordIndex(rowIndex)][this.DynamicTable.Columns.length];
	this.DynamicTable.disableRow(rowIndex,((boolflag == constLoadStatus) || this.Disabled));//|| (boolflag == constUpdateStatus)
}