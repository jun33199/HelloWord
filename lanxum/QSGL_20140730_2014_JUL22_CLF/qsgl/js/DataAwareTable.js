//function: DataAwareTable�Ĺ��캯��
//parameters: objDynamicTable: DynamicTable object;
//							   ���DynamicTable�а���constCTSequence�����ֶΣ�����DataSource��Ҫ��Ԥ���ֶζ�Ӧ��
//							   ���ǼȲ��ὫԤ���ֶ��е�ֵ��ʾ��DynamicTable�У�
//							   Ҳ���ὫDynamicTable�е����д��Ԥ���ֶΣ�
//							   ��˿�����Ԥ���ֶ��д洢��������Ļ����ʾ�����ݡ�
//return: DataAwareTable Object;
function DataAwareTable(objDynamicTable)
{
	//member variant of  DataAwareTable;
	this.DynamicTable = objDynamicTable;
	this.DynamicTable.MultiRowEdit = true;
	this.DataSource = null;
    this.MoveToListeners = new Array();

	//method of DataAwareTable;
	this.saveAllData = dat_saveAllData;
	this.saveData = dat_saveData;
	this.loadAllData = dat_loadAllData;
	this.loadData = dat_loadData;
	this.appendRow = dat_appendRow;
	this.deleteRow = dat_deleteRow;
	this.modifyRow = dat_modifyRow;
	this.paintRow = dat_paintRow;
	this.moveto = dat_moveto;
	this.disableAll = dyt_disableAll;
	this.getRecordIndex = dat_getRecordIndex;
	this.setDataSource = dat_setDataSource;
	this.formatData = dat_formatData;
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
    this.doMouseDown = dat_doMouseDown;
    this.DynamicTable.Owner = this;
    
    this.addMoveToListener = dat_addMoveToListener;
    this.notifyMoveToListener = dat_notifyMoveToListener;
    
    this.getColumns = dat_getColumns;

	return this;
}
//function: ����DataAwareTable������Դ;
//parameters: aryDataSource: ����Դ��ÿ�����ݰ���һ��״̬�ֶ�;
//return: null;
function dat_setDataSource(aryDataSource)
{
	this.DataSource = aryDataSource;
	this.loadAllData();
	if (this.DataSource.length==0) {
		return;
	}
}

//function: �洢DynamicTable�����е����ݵ�DataSource��;
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

//function: �洢DynamicTable�е�numIndex�е����ݵ�DataSource��
//parameters:numIndex��ָ�����к�
//return: null;
function dat_saveData(numIndex)
{
	var objTable = this.DynamicTable.TableID;
	if (objTable == null) return;
	if (numIndex <= 0) return;
	var numRecordIndex = this.getRecordIndex(numIndex);
	if(numRecordIndex<0) return;
	for (var i = 0;i<objTable.rows(0).cells.length;i++)
	{
		switch(this.DynamicTable.Columns[i].Type)
		{
			case constCTDefault:
				this.DataSource[numRecordIndex][i]=dat_trimString(objTable.rows(numIndex).cells(i).innerText);
				break;
			case constCTInput:
				this.DataSource[numRecordIndex][i]=dat_trimString(objTable.rows(numIndex).cells(i).firstChild.value);
				break;
			case constCTSelect:
					this.DataSource[numRecordIndex][i]=objTable.rows(numIndex).cells(i).firstChild.value;
				break;
			case constCTRadioButton:
				this.DataSource[numRecordIndex][i]=
						(objTable.rows(numIndex).cells(i).firstChild.checked?constTrue:constFalse);
				if (this.DataSource[numRecordIndex][i] == constTrue)
				{	//�޸�����record����Ӧ����
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
			case constCTDelButton:
				if (this.DataSource[numRecordIndex][i] == null) this.DataSource[numRecordIndex][i] = "";
				break;
			default:
				alert("unknown Column Type");
				break;
		}
	}
}

//function: ��DataSource�ж�ȡ����״̬��־����constDeleteStatus��constObsoleteStatus������,
//			��ʾ��DynamicTable��;
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
		if (j >= objTable.rows.length) this.DynamicTable.appendRow();//����DynamicTable.appendRow(); don't create new record
		this.loadData(j);
	}
}

//function: ��DataSource�ж�ȡ��ӦDynamicTable�е�numIndex�е�����,
//			��ʾ��DynamicTable��;
//parameters: numIndex��ָ�����к�
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
				objTable.rows(numIndex).cells(i).innerText = this.DataSource[numRecordIndex][i];//DataSource�±���㿪ʼ
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
			case constCTDelButton:
				//do nothing
				break;
			default:
				alert("unknown Column Type");
				break;
		}//end of switch
	}
	//add
}

//function: ����һ������;
//parameters: aryDataSource:�������ݣ����aryDataSource == null,���������ݸ��ֶ�Ϊȱʡֵ
//return: null;
function dat_appendRow(aryData)
{
	//this.saveData(this.DynamicTable.CurrentRow);
    this.saveAllData();

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
					//do nothing
					this.DataSource[numRecordCount][i] = "";
					break;
				case constCTDelButton:
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

//function: ��DynamicTable��ɾ����numIndex�У�������DataSource����Ӧ���ݵ�״̬�ֶ�
//parameters:numIndex��ָ�����к�
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
				(this.DataSource[numRecordIndex][numFieldIndex] == constUpdateStatus))
		{
			this.DataSource[numRecordIndex][numFieldIndex] = constDeleteStatus;
		}
		var numCurRow = this.DynamicTable.CurrentRow;
		if (this.getRecordIndex(numCurRow)<0) 
		{
			return;
		}
	}
}

//function: ����DynamicTable�е�numIndex��״̬ΪEnabled�������޸�DataSource����Ӧ����״̬�ֶ�ΪconstUpdateStatus;
//parameters: numIndex��ָ�����к�
//return: null;
function dat_modifyRow(numIndex)
{
	if ((numIndex > 0)&& (numIndex < this.DynamicTable.TableID.rows.length))
	{
		var numFieldIndex = this.DynamicTable.Columns.length;
		var numRecordIndex = this.getRecordIndex(numIndex);
		if ((this.DataSource[numRecordIndex][numFieldIndex] == constLoadStatus)||
				(this.DataSource[numRecordIndex][numFieldIndex] == constUpdateStatus))
		{
			this.DataSource[numRecordIndex][numFieldIndex] = constUpdateStatus;
		}
	}
}

//function: �����Ƿ������ʾDynamicTable�е�numIndex��
//parameters:numIndex :ָ�����к�
//			 boolHightLight:������ʾ��־
//return:
function dat_paintRow(numIndex,boolHighLight)
{
	this.DynamicTable.panitRow(numIndex,boolHighLight);
}

//function: �ƶ�����numIndex��
//parameters��numIndex��ָ�����к�
//return:
function dat_moveto(numIndex)
{
	this.saveData(this.DynamicTable.CurrentRow);
	if (this.DynamicTable.moveto(numIndex) == true)
	{
		//����moveto�¼�������		
		this.notifyMoveToListener(numIndex);
	}
	
}

//function: �õ���ӦDynamicTable�е�numIndex�е�������DataSource�е�λ��
//parameters: numIndex��ָ�����к�
//return: ��Ӧ������DataSource�е�λ��;-1,Խ�硣
//ע�⣺��ʾ���д�1��ʼ���������д�0��ʼ
function dat_getRecordIndex(numIndex)
{
	if (numIndex>this.DataSource.length || numIndex<=0) {
		return -1;
	}
	var i=0;
	var j=0;
	while (i<this.DataSource.length)
	{
		//�趨DataSource���һ��Ϊ״̬λ
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

//function: ��ʽ��DataSource����״̬��־����constObsoleteStatus������
//parameters: RecordSeparator:��¼�ָ��� FieldSeparator: �ֶηָ���
//return: ��ʽ��������
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
		//window.alert("ָ������ʾ��Խ�磡");
	}
	return this.DataSource[recordIndex];
}

function dat_setDataAt(rowIndex, dataArray) {
	var recordIndex = this.getRecordIndex(rowIndex);
	if (recordIndex<0) {
		window.alert("ָ������ʾ��Խ�磡");
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
//function: ��ʽ��DataSource����״̬��־����constObsoleteStatus������
//parameters: RecordSeparator:��¼�ָ��� FieldSeparator: �ֶηָ���
//return: ��ʽ��������
function dat_formatDataWithDelete(RecordSeparator,FieldSeparator)
{
    var strResult = "";
    for (var i = 0; i < this.DataSource.length;i++)
    {
        var flag = this.DataSource[i][this.DynamicTable.Columns.length];
        if (flag!=null &&
            flag!=constLoadStatus &&
            flag!=constNewStatus &&
            flag!=constUpdateStatus &&
            flag!=constDeleteStatus) {
            continue;
        }
        if (i != 0) strResult = strResult + RecordSeparator;
        for (var j = 0; j < this.DataSource[i].length;j++)
        {
            if (j != 0) strResult = strResult + FieldSeparator;
            strResult = strResult+this.DataSource[i][j];
        }
    }
    return strResult;
}

// ȥ���ַ���ǰ��Ŀո�
// @parameter strValue ��Ҫ������ַ�����
// @return String ǰ��ո���Ʊ����ȥ��
function dat_trimString(strValue) {
    if (strValue==null) {
        return null;
    }
    var returnValue = strValue;

    //ɾ���ַ���ǰ��Ŀո�(=32=0x20)���Ʊ��(=09=0x09)
    //�Լ������ַ��Ŀո�(=41377=0xA1A1)
    while (returnValue.length>0) {
        var code = returnValue.charCodeAt(0);
        if (code==32 || code==9 || code==41377) {
            returnValue = returnValue.substr(1);
        } else {
            break;
        }
    }

    //ɾ���ַ�������Ŀո�(SPACE=20)���Ʊ��(TAB=09)
    //�Լ������ַ��Ŀո�(=41377=0xA1A1)
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

//function: ���õ�numIndex״̬;
//parameters:numIndex :ָ������;
//			 boolDisabled:disabled��־;
//return:
function dyt_disableAll(boolDisabled)
{
	var objTable = this.DynamicTable.TableID;
	if (objTable == null) return;

	for (var i = 1;i < objTable.rows.length;i++)
	{
		this.DynamicTable.disableRow(i,boolDisabled);
	}
	
}

