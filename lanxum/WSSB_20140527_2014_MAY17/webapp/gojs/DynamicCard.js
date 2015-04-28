//function: DynamicCard�Ĺ��캯��;
//parameters: TableID : ��ӦHTML�ļ���Table��ID����;
//			  aryColumn: DTColumn���飬��ʾDataAwareTable��ÿһ�е�����;
//						 ֻ�е�һ��Ԫ�ص����Ϳ�����constCTSequence
//return: null :��TableID == nullʱ;
//		  DynamicCard Object: else; 
function DynamicCard(TableID,aryColumn)
{
	//member variant of DynamicCard object;
	if (TableID == null) return null;
	this.TableID = TableID;
	this.Columns = aryColumn;//new Array();
	this.HasSequenceColumn = false;

	//method of DynamicCard object;
	this.clearRow = dyc_clearRow;
	this.disableRow = dyc_disableRow;

	for (var i = 0;i<aryColumn.length;i++)
	{
		if (aryColumn[i].Type == constCTSequence)
		{
			if (i>0)
			{
				alert("In DynamicCard,only the first column can be Sequence Type,DynamicCard creation failed");
				return null;
			}
			this.HasSequenceColumn = true;
		}
		//�����޸�,ͳһ�ӿڣ�ֱ�ӽ�aryColumn����Columns
		//this.Columns[this.Columns.length] =
				//new DTColumn(aryColumn[i].Type,aryColumn[i].Name,aryColumn[i].Width,aryColumn[i].DataSource);
	}
	return this;
}

//function: ���DynamicCard����ʾ������
//parameters:
//return:
function dyc_clearRow()
{
	var objTable = this.TableID;
	if (objTable == null) return;
	for (var i = 0;i<this.Columns.length;i++)
	{
		switch (this.Columns[i].Type)
		{
			case constCTDefault:
				objTable.rows(i).cells(1).innerText = " ";
				break;
			case constCTInput:
				objTable.rows(i).cells(1).firstChild.value = "";
				break;
			case constCTSelect:
		        objTable.rows(i).cells(1).firstChild.selectedIndex = 0; //������select������Դ
				break;
			case constCTRadioButton:
				//must set name property;
		        objTable.rows(i).cells(1).firstChild.checked = false;
				break;
			case constCTCheckBox:
				objTable.rows(i).cells(1).firstChild.checked = false;
				break;
			case constCTSequence:
				//nothing to do
				objTable.rows(i).cells(1).innerText = " ";
				break;
			default:
				alert("unknown column type");  //need modify
				break;
		}
	}
	this.disableRow(false); //enabled
}

//function: ����DynamicCard��״̬
//parameters:boolDisabled:Disabled״̬��־
//return:
function dyc_disableRow(boolDisabled)
{
	var objTable = this.TableID;
	if (objTable == null) return;
	for (var i = 0;i < this.Columns.length;i++)
	{
		if (objTable.rows(i).cells(1).children.length > 0)
			objTable.rows(i).cells(1).firstChild.disabled = boolDisabled;
		else objTable.rows(i).cells(1).disabled = boolDisabled;
	}
}

