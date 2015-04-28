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
				document.all("fwdwjsjdm")[i].innerText = " ";
				break;
			case constCTInput:
				document.all("fwdwjsjdm")[i].value = "";
				break;
			case constCTSelect:
		        document.all("fwdwjsjdm")[i].selectedIndex = 0; //������select������Դ
				break;
			case constCTRadioButton:
				//must set name property;
		        document.all("fwdwjsjdm")[i].checked = false;
				break;
			case constCTCheckBox:
				document.all("fwdwjsjdm")[i].checked = false;
				break;
			case constCTSequence:
				//nothing to do
				document.all("fwdwjsjdm")[i].innerText = " ";
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
	var fwdwArray = document.all("fwdwjsjdm");
	var l = fwdwArray.length;
	for (var i = 0;i < l;i++)
	{
        fwdwArray[i].disabled = boolDisabled;
	}
}

