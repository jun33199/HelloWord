//function: DynamicTable构造函数;
//parameters: TableID : 对应HTML文件中Table的ID属性;
//			  aryColumn: DTColumn数组，表示DataAwareTable中每一列的类型;
//return: null :当TableID == null时;
//		  DynamicTable Object: else;
function DynamicTable(TableID,aryColumn,varName)
{
	//member variant of DynamicTable object;
	if (TableID == null) return null;
	this.TableID = TableID;
	this.Columns = aryColumn;//new Array();
	this.CurrentRow = 0;
	this.HighlightColor = '#aabbcc';
	this.BackgroundColor = "#ECF2F4";
	this.MultiRowEdit = true;
	this.HasSequenceColumn = false;
	//add by liqiang 2003-04-01
	this.Owner = null;
	this.VariableName = "dtList";
	if (varName!=null) {
		this.VariableName = varName;
	}
	
	this.ClassName="2-td2-left";
	this.lastClassName="2-td2-center";

	//method of DynamicTable object;
	this.addCell = dyt_addCell;
	this.appendRow = dyt_appendRow;
	this.deleteRow = dyt_deleteRow;
	this.paintRow = dyt_paintRow;
	this.moveto = dyt_moveto;
	this.getRowAt = dyt_getRowAt;
	this.getCellAt = dyt_getCellAt;
	this.focusAt = dyt_focusAt;
	this.getCellsAt = dyt_getCellsAt;
	this.doMouseDown = dyt_doMouseDown;
	this.disableRow = dyt_disableRow;

	for (var i = 0;i<aryColumn.length;i++)
	{
		if (aryColumn[i].Type == constCTSequence)
		{
			if (i>0)
			{
				alert("In dynamicTable,only the first column can be Sequence Type,dynamicTable creation failed");
				return null;
			}
			this.HasSequenceColumn = true;
		}
	}
	return this;
}

//function: 新增一行;
//parameters:
//return: null;
function dyt_appendRow()
{
	var objTable = this.TableID;
	if (objTable == null) return;
	var objNewRow = objTable.insertRow();
	var numIndex = objTable.rows.length-1;
	for (var i = 0;i<this.Columns.length;i++)
	{
	  if(i<this.Columns.length-1)
		this.addCell(this.Columns[i],objNewRow,numIndex,this.ClassName);
	  else
	  	this.addCell(this.Columns[i],objNewRow,numIndex,this.lastClassName);
	}
	
	this.paintRow(this.CurrentRow,false);//not highlight
	this.CurrentRow = objTable.rows.length-1;
}

//function: 在指定的行列新增一个单元
//			由appendRow函数调用，开发者不需要直接调用;
//parameters:objColumn:DTColumn,指定列的信息;
//			 objNewRow:指定的行;
//			 numIndex:指定行的序号;
//return:
function dyt_addCell(objColumn,objNewRow,numIndex,ClassName)
{
	var objCell = objNewRow.insertCell();
	var propertyStr = "";

	switch (objColumn.Type)
	{
		case constCTDefault:
			objCell.innerText = " ";
			objCell.Width = objColumn.Width;
			objCell.className=ClassName;
			break;
		case constCTInput:
			var objInput = document.createElement("<INPUT border=0 "+objColumn.Properties+"></INPUT>");
	      	objInput.style.width = objColumn.Width;
	      	objInput.innerText = "";
	      	objCell.appendChild(objInput);
			objCell.className=ClassName;
	      	break;
		case constCTSelect:
			var objSelect = document.createElement("<SELECT "+objColumn.Properties+"></SELECT>");
			if (objColumn.DataSource != null)
			{
		        for (var i=0;i<objColumn.DataSource.length;i++)
		        {
		          var op = new Option();
		          op.innerText=objColumn.DataSource[i][1]+"";
		          op.value=objColumn.DataSource[i][0]+"";
		          //objSelect.options.add(op);
		          objSelect.insertBefore(op);
		        }
	    	}
	        objSelect.value = "";
	      	objSelect.style.width = objColumn.Width;
	      	objCell.appendChild(objSelect);
			objCell.className=ClassName;
			break;
		case constCTRadioButton:
			//must set name property;
			strRadioName = this.TableID+"_"+objColumn.name+"_RadioButton";
	        objInput = document.createElement("<INPUT border=0 type=radio name="+strRadioName+" "+objColumn.Properties+"></INPUT>");
	      	objCell.appendChild(objInput);
			objCell.className=ClassName;
	      	objInput.checked = false;
			break;
		case constCTCheckBox:
	        objInput = document.createElement("<INPUT border=0 type=CheckBox  "+objColumn.Properties+"></INPUT>");
	        objCell.appendChild(objInput);
			objCell.className=ClassName;
	        objInput.checked = false;
			break;
		case constCTSequence:
			//modify by guzx 2006-04-10
			//var func = new Function("eval(\""+this.VariableName+".doMouseDown("+numIndex+")\")");
			//objCell.onmousedown=func;
			//objCell.onmouseover=new Function("this.style.cursor='hand'");
			objCell.innerText=numIndex;
			objCell.className=ClassName;
			break;
		case constCTDelButton:
	      	var objDel = document.createElement("<INPUT border=0 type=Button  name=\"Submit\" "+objColumn.Properties+"  value=\"删除\" onclick=\"deleteRow("+numIndex+");return false;\">");
	  			//alert ("\"deleteRow("+numIndex+");return false;\"");
      objCell.appendChild(objDel);
			objCell.className=ClassName;
			break;
		default:
			alert("unknown column type");  //need modify
			break;
	}//end of switch
}

//function: 删除第numIndex行
//parameters: numIndex :指定的行号;
//return:
function dyt_deleteRow(numIndex)
{

	var objTable = this.TableID;
	if (objTable == null) return;
	if ((numIndex > 0)&&(numIndex < objTable.rows.length))
	{
		objTable.deleteRow(numIndex);
		//前提条件：DynamicTable只有第一列的类型可以是constCTSequence
		//	alert("index="+numIndex+" len="+objTable.rows.length);

		for (var j = numIndex;j < objTable.rows.length;j++)
		{
			if (this.HasSequenceColumn) objTable.rows(j).cells(0).innerText = j;
	      	
			var objDel=this.getCellAt(j,objTable.rows(j).cells.length-1);
            objTable.rows(j).cells(objTable.rows(j).cells.length-1).removeChild(objDel);

	      	objDel = document.createElement("<INPUT border=0 type=Button  name=\"Submit\" "+this.Columns[7].Properties+"  value=\"删除\" onclick=\"deleteRow("+j+");return false;\">");
	  			//alert ("<INPUT border=0 type=Button  name=\"Submit\" "+this.Columns[7].Properties+"  value=\"删除\" onclick=\"deleteRow("+j+");return false;\">");
      objTable.rows(j).cells(objTable.rows(j).cells.length-1).appendChild(objDel);
		}
		this.CurrentRow = (numIndex < objTable.rows.length-1 ? numIndex : objTable.rows.length-1);
		this.paintRow(this.CurrentRow,true);
	}
}

//function: 更新第numIndex行颜色
//parameters:numIndex :指定的行号;
//			 boolHeightLight:高亮显示标志
//return:
function dyt_paintRow(numIndex,boolHighlight)
{
	var objTable = this.TableID;
	if (objTable == null) return;
	if ((numIndex > 0)&&(numIndex <objTable.rows.length))
	{
			for(var j=0;j<objTable.rows[numIndex].cells.length;j++)
			{
				objTable.rows[numIndex].cells[j].style.background=this.BackgroundColor ;
			}
	}

}

//function: 移动到第numIndex行;
//parameters:numIndex；指定的行号
//return:true:执行了移动操作;
//       false:else;
function dyt_moveto(numIndex)
{
	if ((numIndex < this.TableID.rows.length)&&(numIndex > 0))
	{
		this.paintRow(this.CurrentRow,false);//not highlight
		this.CurrentRow = numIndex;
		return true;
	}
	return false;
}

function dyt_getRowAt(rowIndex) {
	return this.TableID.rows.item(rowIndex);
}

function dyt_getCellAt(rowIndex, columnIndex) {
	var row = this.getRowAt(rowIndex);
	if (row==null) {
		return;
	}
	return row.cells.item(columnIndex).firstChild;

}

function dyt_focusAt(rowIndex, columnIndex) {

	var cellObject = this.getCellAt(rowIndex,columnIndex);
	if (cellObject!=null) {
		var firstChild = cellObject.firstChild;
		if (firstChild!=null &&
			(firstChild.tagName.toLowerCase()=="input" ||
			 firstChild.tagName.toLowerCase()=="select")
			 && firstChild.disabled==false) {
			firstChild.focus();
		}
	}

}

//function: 设置第numIndex状态;
//parameters:numIndex :指定的行;
//			 boolDisabled:disabled标志;
//return:
function dyt_disableRow(numIndex,boolDisabled)
{
	var objTable = this.TableID;
	if (objTable == null) return;
	for (var i = 0;i < objTable.rows(0).cells.length;i++)
	{
		var cellElement = objTable.rows(numIndex).cells(i);
		if (cellElement.children.length > 0) {
			if (cellElement.firstChild.disabled!=null) {
				cellElement.firstChild.disabled = boolDisabled;
			}
		}
	}
}

function dyt_getCellsAt(rowIndex) {

	var row = this.getRowAt(rowIndex);
	if (row==null) {
		return;
	}
	var cells = new Array();
	for (var i=0;i<row.cells.length;i++) {
		cells[i] = row.cells.item(i).firstChild;
	}
	return cells;

}

//add by liqiang 2003-04-01
function dyt_doMouseDown(rowIndex){
	if (this.Owner != null)
	{
		this.Owner.doMouseDown();
	} else
	{
		//var rowIndex = event.srcElement.innerText*1;
		this.moveto(rowIndex);
	}
}