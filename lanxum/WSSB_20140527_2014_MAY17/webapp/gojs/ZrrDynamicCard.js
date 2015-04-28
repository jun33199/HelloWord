//function: DynamicCard的构造函数;
//parameters: TableID : 对应HTML文件中Table的ID属性;
//			  aryColumn: DTColumn数组，表示DataAwareTable中每一列的类型;
//						 只有第一个元素的类型可以是constCTSequence
//return: null :当TableID == null时;
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
		//章鹏修改,统一接口，直接将aryColumn传给Columns
		//this.Columns[this.Columns.length] =
				//new DTColumn(aryColumn[i].Type,aryColumn[i].Name,aryColumn[i].Width,aryColumn[i].DataSource);
	}
	return this;
}

//function: 清除DynamicCard中显示的数据
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
		        document.all("fwdwjsjdm")[i].selectedIndex = 0; //不处理select数据来源
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

//function: 设置DynamicCard的状态
//parameters:boolDisabled:Disabled状态标志
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

