//function: DynamicTable构造函数;
//parameters: TableID : 对应HTML文件中Table的ID属性;
//aryStrs: 带tr的一行field
//return: null :当TableID == null时;
//		  DTable Object: else;
function DTable(TableID,aryStrs,varName)
{
	//member variant of DTable object;
	if (TableID == null) return null;
	this.TableID = TableID;
	this.aryStr = aryStrs;//new Array();
	this.CurrentRow = 0;
	this.rowIndex = 0;
	this.HighlightColor = "#aabbcc";
	this.BackgroundColor = "#ECF2F4";
	this.MultiRowEdit = false;
	this.HasSequenceColumn = false;
	this.tableHead=1;
	this.tableTail=0;
	//add by liqiang 2003-04-01
	this.Owner = null;
	this.VariableName = "dtList";
	if (varName!=null) {
		this.VariableName = varName;
	}

	//method of DTable object;
	this.appendRow = dyt_appendRow;
	this.deleteRow = dyt_deleteRow;
	this.paintRow = dyt_paintRow;
	this.firstRow = dyt_firstRow;
	this.lastRow = dyt_lastRow;
	this.previousRow = dyt_previousRow;
	this.nextRow = dyt_nextRow;
	this.moveto = dyt_moveto;
	this.getRowAt = dyt_getRowAt;
	this.getCellAt = dyt_getCellAt;
	this.focusAt = dyt_focusAt;
	this.getCellsAt = dyt_getCellsAt;
	this.doGetRowLength=dyt_doGetRowLength;
	this.doGetRowIndex=dyt_doGetRowIndex;
	this.doGetCurrent=dyt_doGetCurrent;
	this.isLastEffectRow=dyt_isLastEffectRow;
	this.doGetRow=dyt_doGetRow;
	this.clearTable=dyt_clearTable;
	//add by liqiang 2003-04-01
	this.doMouseDown = dyt_doMouseDown;

	return this;
}

//function: 新增一行;
//parameters:
//return: null;
function dyt_appendRow()
{
	var objTable =	document.all(this.TableID.id);//this.TableID;
	if (objTable == null) return;
   var outerHTML = objTable.outerHTML;
   objTable.outerHTML = outerHTML.replace("<DIV id=divSfTemp></DIV>",this.aryStr+"<DIV id=divSfTemp></DIV>");
	 objTable =	document.all(this.TableID.id);
   this.paintRow(this.CurrentRow,false);//not highlight
   this.CurrentRow = objTable.rows.length-this.tableHead-this.tableTail;
	 this.paintRow(this.CurrentRow,true); //highlight
	 this.focusAt(this.CurrentRow,0);
}

//function: 删除第numIndex行
//parameters: numIndex :指定的行号;
//return:
function dyt_deleteRow(current)
{
	var objTable =	document.all(this.TableID.id);//this.TableID;	if (objTable == null) return;
	var father;
	if ((current > 0)&&(current < this.doGetRowLength()-this.tableHead-this.tableTail+1))
	{
		//father=getCellAt((this.doGetRowIndex(current), 0)).value;
		objTable.deleteRow(this.doGetRowIndex(current));
		//前提条件：DynamicTable只有第一列的类型可以是constCTSequence
		for (var j = current;j < objTable.rows.length-this.tableTail;j++)
		{
			if (this.HasSequenceColumn) objTable.rows(j).cells(0).innerText = j;
		}
		this.CurrentRow = (current < objTable.rows.length-this.tableTail-1 ? current : objTable.rows.length-this.tableHead-this.tableTail);
		this.paintRow(this.CurrentRow,true);
	    this.focusAt(this.CurrentRow,0);
	}
}

//function: 更新第numIndex行颜色
//parameters:numIndex :指定的行号;
//			 boolHeightLight:高亮显示标志
//return:
function dyt_paintRow(current,boolHighlight)
{
	var objTable =	document.all(this.TableID.id);//this.TableID;	if (objTable == null) return;
	if ((current > 0)&&(current < this.doGetRowLength()-this.tableHead-this.tableTail+1))
	{
		for (var i=0;i<objTable.rows[this.doGetRowIndex(current)].cells.length;i++)
		{
				objTable.rows[this.doGetRowIndex(current)].cells[i].style.background=(boolHighlight == true?this.HighlightColor:this.BackgroundColor);//'#aabbcc'; 
		}
  }
	//	objTable.rows(numIndex).bgColor =
		//		(boolHighlight == true?this.HighlightColor:this.BackgroundColor);
//	}

}


//function: 移动到第一行
//parameters:
//return:true:执行了移动操作;
//       false:else;
function dyt_firstRow()
{	
	return this.moveto(1);
}

//function: 移动到最后一行
//parameters:
//return:true:执行了移动操作;
//       false:else;
function dyt_lastRow()
{
		return this.moveto(this.doGetRowLength()-this.tableHead-this.tableTail);
}

//function: 移动到前一行
//parameters:
//return:true:执行了移动操作;
//       false:else;
function dyt_previousRow()
{
	return this.moveto(this.CurrentRow-1);
}

//function: 移动到后一行
//parameters:
//return:true:执行了移动操作;
//       false:else;
function dyt_nextRow()
{
	return this.moveto(this.CurrentRow+1);
}

//function: 移动到第numIndex行;
//parameters:numIndex；指定的行号
//return:true:执行了移动操作;
//       false:else;
function dyt_moveto(current)
{
		if ((current > 0)&&(current < this.doGetRowLength()-this.tableHead-this.tableTail+1))
	{
		this.paintRow(this.CurrentRow,false);//not highlight
		this.CurrentRow = current;
		this.paintRow(this.CurrentRow,true); //highlight
		return true;
	}
	return false;
}

function dyt_getRowAt(rowIndex) {
	var objTable = document.all(this.TableID.id);//this.TableID;
	return objTable.rows.item(rowIndex);
}

function dyt_getCellAt(rowIndex, columnIndex) {
	var row = this.getRowAt(rowIndex);
	if (row==null) {
		return;
	}
	return row.cells.item(columnIndex);

}

function dyt_focusAt(current, columnIndex) {
	var jixu=false;
	if ((current > 0)&&(current < this.doGetRowLength()-this.tableHead-this.tableTail+1)){
		jixu=true;
	}
 if(jixu==false) return;
	var cellObject = this.getCellAt(this.doGetRowIndex(current),columnIndex);
	if (cellObject!=null) {
		var firstChild = cellObject.firstChild;
		var columnIndexNum=0;
		while(!firstChild.tagName){
			columnIndexNum++;
			cellObject = this.getCellAt(this.doGetRowIndex(current),columnIndex+columnIndexNum)
			if (cellObject!=null) firstChild = cellObject.firstChild;
		}//end while

	}//end if	if (cellObject!=null) {
	
		if (firstChild!=null &&
			(firstChild.tagName.toLowerCase()=="input" ||
			 firstChild.tagName.toLowerCase()=="select"||
			firstChild.tagName.toLowerCase()=="div")
			 && firstChild.disabled==false) {
			//alert(firstChild.name)

		if(firstChild.tagName.toLowerCase()=="div"){
			firstChild=firstChild.firstChild;
		}
			//alert(firstChild.name+":"+firstChild.tagName);
			//firstChild.focus();
			if(firstChild.tagName.toLowerCase()!="select"){//2003-11-28 modify by hpj for 北京市地方税务局减免税申报表
				firstChild.select();
			}
		}//end if

}//end function 2003-10-8

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
function dyt_doMouseDown(current){
	if (this.Owner != null)
	{
		this.Owner.doMouseDown();
	} else
	{
		//var rowIndex = event.srcElement.innerText*1;
		this.moveto(current);
	}
}
function dyt_doGetRowLength(){
	var objTable = document.all(this.TableID.id);//this.TableID;
	return objTable.rows.length;
}
function dyt_doGetRowIndex(current){
  return parseFloat(current)+parseFloat(this.tableHead)-1;
}
function dyt_doGetRow(current){
var objTable =	document.all(this.TableID.id);//this.TableID;	if (objTable == null) return;
	  return objTable.rows[this.doGetRowIndex(current)];
}

function dyt_doGetCurrent(rowIndex){
	return parseFloat(rowIndex)-parseFloat(this.tableHead)+1;
}

function dyt_isLastEffectRow(){
	return (this.CurrentRow+this.tableHead+this.tableTail==this.doGetRowLength())
}

function dyt_clearTable(){
var objTable =	document.all(this.TableID.id);//this.TableID;	if (objTable == null) return;
for(var i=this.tableHead;i<this.doGetRowLength()-this.tableTail;i++){
		objTable.rows[i].removeNode(true);
		i--;
}

}