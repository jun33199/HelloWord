function DataAwarePage(objDynamicTable,objDynamicCard)
{
	//member variant of  DataAwarePage;
	this.DynamicTable = objDynamicTable;
	if (this.DynamicTable == null)
	{
		this.hasDynamicTable = false;
	}
	this.DynamicCard = objDynamicCard;
	if (this.DynamicCard == null)
	{
		this.CardDynamicCard = false;
	}
	this.DynamicTable.MultiRowEdit = false;
	this.DataSource = null;

	//method of DataAwarePage;
	this.saveAllData = dap_saveAllData;
	this.saveData = dap_saveData;
	this.loadAllData = dap_loadAllData;
	this.loadData = dap_loadData;
	this.appendRow = dap_appendRow;
	this.deleteRow = dap_deleteRow;
	this.modifyRow = dap_modifyRow;
	this.paintRow = dap_paintRow;
	this.firstRow = dap_firstRow;
	this.previousRow = dap_previousRow;
	this.nextRow = dap_nextRow;
	this.lastRow = dap_lastRow;
	this.getRecordIndex = dap_getRecordIndex;
	this.setDataSource = dap_setDataSource;
	this.formatData = dap_formatData;
	this.getCurrentRow=dap_getCurrentRow;
	this.first = dap_firstRow;
	this.previous = dap_previousRow;
	this.next = dap_nextRow;
	this.last = dap_lastRow;
	this.getData = dap_getData;
	this.getDataAt = dap_getDataAt;
	this.setDataAt = dap_setDataAt;

	return this;
}

function dap_setDataSource(aryDataSource)
{
	for (var i = this.DynamicTable.TableID.rows.length-1;i > 0;i--)
	{
		this.DynamicTable.deleteRow(i);
	}
	this.DataSource = aryDataSource;
	for (var i = 0;i < this.DataSource.length;i++)
	{
		if ((this.DataSource[i][this.DataSource[0].length-1] == constDeleteStatus)
				||(this.DataSource[i][this.DataSource[0].length-1] == constObsoleteStatus))
			continue;
		this.DynamicTable.appendRow();//调用DynamicTable.appendRow(); don't create new record
	}
	this.loadAllData();
	var boolflag = this.DataSource[this.getRecordIndex(
			this.getCurrentRow())][this.DataSource[0].length-1];
	this.DynamicTable.disableRow(this.getCurrentRow(),
			((boolflag == constLoadStatus) || (boolflag == constUpdateStatus)));
}

function dap_saveAllData()
{
	var objTable = this.DynamicTable.TableID;
	if (objTable == null) return;
	for (var i = 1;i < objTable.rows.length;i++)
	{
		this.saveData(i);
	}
}

function dap_saveData(numIndex)
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
				this.DataSource[numRecordIndex][i]=objTable.rows(numIndex).cells(i).innerText;
				break;
			case constCTInput:
				this.DataSource[numRecordIndex][i]=objTable.rows(numIndex).cells(i).firstChild.value;
				break;
			case constCTSelect:
				this.DataSource[numRecordIndex][i]=
						this.DynamicTable.Columns[i].DataSource[objTable.rows(numIndex).cells(i).firstChild.selectedIndex][0];
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
				break;
			default:
				alert("unknown Column Type");
				break;
		}
	}
}

function dap_loadAllData()
{
	var objTable = this.DynamicTable.TableID;
	if (objTable == null) return;
	for (var i = 1;i < objTable.rows.length;i++)
	{
		this.loadData(i);
	}
}

function dap_loadData(numIndex)
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
			case constCTInput:
				objTable.rows(numIndex).cells(i).firstChild.value = this.DataSource[numRecordIndex][i];
				break;
			case constCTSelect:
				for (var k = 0;k < this.DynamicTable.Columns[i].DataSource.length;k++)
				{
					if (this.DynamicTable.Columns[i].DataSource[k][0] == this.DataSource[numRecordIndex][i])
					{
						objTable.rows(numIndex).cells(i).firstChild.selectedIndex = k;
						break;
					}
				}
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
	//add status,need modify
}

function dap_appendRow(aryData)
{
	this.saveData(this.getCurrentRow());
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
					this.DataSource[numRecordCount][i] = ""+i;
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

//function: delete No.numIndex row in DynamicTable
//parameters:numIndex :index of row to be deleted;
//return:
function dap_deleteRow(numIndex)
{
	if ((numIndex > 0)&& (numIndex < this.DynamicTable.TableID.rows.length))
	{
		this.DynamicTable.deleteRow(numIndex);
		var numFieldIndex = this.DataSource[0].length-1;
		var numRecordIndex = this.getRecordIndex(numIndex);
		if (this.DataSource[numRecordIndex][numFieldIndex] == constNewStatus)
			this.DataSource[numRecordIndex][numFieldIndex] = constObsoleteStatus;
		if ((this.DataSource[numRecordIndex][numFieldIndex] == constLoadStatus)||
				(this.DataSource[numRecordIndex][numFieldIndex] == constUpdateStatus))
			this.DataSource[numRecordIndex][numFieldIndex] = constDeleteStatus;
	}
}

function dap_modifyRow(numIndex)
{
	if ((numIndex > 0)&& (numIndex < this.DynamicTable.TableID.rows.length))
	{
		var numFieldIndex = this.DataSource[0].length-1;
		var numRecordIndex = this.getRecordIndex(numIndex);
		if ((this.DataSource[numRecordIndex][numFieldIndex] == constLoadStatus)||
				(this.DataSource[numRecordIndex][numFieldIndex] == constUpdateStatus))
		{
			this.DynamicTable.disableRow(numIndex,false);
			this.DataSource[numRecordIndex][numFieldIndex] = constUpdateStatus;
		}
	}
}

//function: update No.numIndex row's color;
//parameters:numIndex :index of row to be deleted;
//return:
function dap_paintRow(numIndex,boolHighLightFlag)
{
	this.DynamicTable.panitRow(numIndex,boolHighLightFlag);
}

//function: move to first row of DynamicTable
//parameters:
//return:
function dap_firstRow()
{
	this.saveData(this.getCurrentRow());
	if (this.DynamicTable.firstRow() == true)
	{
		var boolflag = this.DataSource[this.getRecordIndex(
				this.getCurrentRow())][this.DataSource[0].length-1];
		this.DynamicTable.disableRow(this.getCurrentRow(),
				(boolflag == constLoadStatus)||(boolflag == constUpdateStatus));
	}
}

//function: move to last Row
//parameters:
//return:
function dap_lastRow()
{
	this.saveData(this.getCurrentRow());
	if (this.DynamicTable.lastRow() == true)
	{
		var boolflag = this.DataSource[this.getRecordIndex(
				this.getCurrentRow())][this.DataSource[0].length-1];
		this.DynamicTable.disableRow(this.getCurrentRow(),
				(boolflag == constLoadStatus)||(boolflag == constUpdateStatus));
	}
}

//function: move to previous row
//parameters:
//return:
function dap_previousRow()
{
	this.saveData(this.getCurrentRow());
	if (this.DynamicTable.previousRow() == true)
	{
		var boolflag = this.DataSource[this.getRecordIndex(
				this.getCurrentRow())][this.DataSource[0].length-1];
		this.DynamicTable.disableRow(this.getCurrentRow(),
				(boolflag == constLoadStatus)||(boolflag == constUpdateStatus));
	}
}

//function: move to next row
//parameters:
//return:
function dap_nextRow()
{
	this.saveData(this.getCurrentRow());
	if (this.DynamicTable.nextRow() == true)
	{
		var boolflag = this.DataSource[this.getRecordIndex(
				this.getCurrentRow())][this.DataSource[0].length-1];
		this.DynamicTable.disableRow(this.getCurrentRow(),
				((boolflag == constLoadStatus)||(boolflag == constUpdateStatus)));
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

function dap_formatData(RowSeparator,ColSeparator)
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
			if (j != 0) strResult = strResult + ColSeparator;
			strResult = strResult+this.DataSource[i][j];
		}
	}
	return strResult;
}

function dap_getCurrentRow(){
	return this.DynamicTable.CurrentRow;
}

function dap_getData() {
	var returnValue = new Array();
	for (var i=0;i<this.DataSource.length;i++) {
		var flag = this.DataSource[i][this.DynamicTable.Columns.length];
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

function dap_getDataAt(rowIndex) {
	var recordIndex = this.getRecordIndex(rowIndex);
	if (recordIndex<0) {
		window.alert("指定的显示列越界！");
	}
	return this.DataSource[recordIndex];
}

function dap_setDataAt(rowIndex, dataArray) {
	var recordIndex = this.getRecordIndex(rowIndex);
	if (recordIndex<0) {
		window.alert("指定的显示列越界！");
	}
	this.DataSource[recordIndex] = dataArray;
}

