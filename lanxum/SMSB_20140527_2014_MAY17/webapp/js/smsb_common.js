
//add by huipeijie 2003-11-18
function hideSelectDiv(){
var obj=window.document.activeElement;
if(obj && obj.id && obj.id=="sel") return;
if(obj && obj.name &&(obj.name=="szsmdm" || obj.name=="bzdm")){
	if(window.event.keyCode!=18){
		return;
	}
}
var namedselobj=document.all.sel;
if(!namedselobj) return;
if(namedselobj && namedselobj.name==null){
	for(var i=0;i<namedselobj.length;i++){
		namedselobj[i].style.display='none';
		namedselobj[i].parentElement.style.zIndex='9999';
		document.zIndex='1';
		namedselobj[i].parentElement.style.display='none';
	}//end for
}else{//end add
	namedselobj.style.display='none';
	namedselobj.parentElement.style.zIndex='9999';
	document.zIndex='1';
	namedselobj.parentElement.style.display='none';
}
}
var srcobj;
function addRow(tableid){
eval("var tempDTable="+tableid+"_list");
tempDTable.appendRow();
tempDTable.focusAt(tempDTable.CurrentRow,0);
publicMethod();
hj();//定义在function里面 计算合计 
return tempDTable.doGetRowIndex(tempDTable.CurrentRow);
}
function addRowBzdm(tableid){
}
function clearTable(tableid){
var truthBeTold = window.confirm("该操作将删除全部的明细数据,且不能自动恢复,请确认");
if(!truthBeTold)	return false;
eval("var tempDTable="+tableid+"_list");
tempDTable.clearTable();
}
function deleteRow(tableid,cellIndex,nameHead)
{
eval("var tempDTable="+tableid+"_list");
var truthBeTold = window.confirm("该操作将删除页面中一行的数据,且不能自动恢复,请确认");
hj();//定义在function里面 计算合计 
if(!truthBeTold)	return false;
var row=tempDTable.doGetRow(tempDTable.CurrentRow);

var obj = row.all("szsmdm_old");
var father="";
if(obj) father = obj.value;

tempDTable.deleteRow(tempDTable.CurrentRow)
if(cellIndex!=null)	tempDTable.focusAt(tempDTable.CurrentRow,cellIndex);
if(nameHead!=null) delSonRow(nameHead,tableid,father);
publicMethod();
hj();//定义在function里面 计算合计 
}
   function first(tableid,cellIndex)
    {
		eval("var tempDTable="+tableid+"_list");
    tempDTable.firstRow();
		if(cellIndex!=null)
		tempDTable.focusAt(tempDTable.CurrentRow,cellIndex);

    }

    function previous(tableid,cellIndex)
    {
		eval("var tempDTable="+tableid+"_list");
      tempDTable.previousRow();
		if(cellIndex!=null)
			tempDTable.focusAt(tempDTable.CurrentRow,cellIndex);
    }

    function next(tableid,cellIndex)
    {
		eval("var tempDTable="+tableid+"_list");
		tempDTable.nextRow();
		if(cellIndex!=null)
		tempDTable.focusAt(tempDTable.CurrentRow,cellIndex);
    }

    function last(tableid,cellIndex)
    {	
		eval("var tempDTable="+tableid+"_list");
		tempDTable.lastRow();
		if(cellIndex!=null)
		tempDTable.focusAt(tempDTable.CurrentRow,cellIndex);

    }
  function dokeyUp(tableid)
	{
		 eval("var tempDTable="+tableid+"_list");
		 var obj=window.document.activeElement;
		 while(obj.tagName!="TR"){
		 obj=obj.parentElement;
		 }
  	 tempDTable.moveto(tempDTable.doGetCurrent(obj.rowIndex))
  	 hideSelectDiv()//add by huipeijie 2003-11-18
 		}	

	function dokeydown(tableid,nameHead)
	{
		 var obj=window.document.activeElement;
	   srcobj=obj;//onkeydown的时候找到触发事件的FIELD 用来判断是否为sel
		 while(obj.tagName!="TD"){
				 obj=obj.parentElement;
		 }
		 hideSelectDiv()//add by huipeijie 2003-11-18
//		 }
 		 eval("var tempDTable="+tableid+"_list");

    if(window.event.keyCode==46)
		{
			if(nameHead!=null) deleteRow(tableid,obj.cellIndex,nameHead);
			else deleteRow(tableid,obj.cellIndex);
		}
		
		 
		if (window.event.keyCode==13)  //return
		{ 
			  window.event.keyCode=9;
				//alert(lastEffectCell(obj))
				if(tempDTable.isLastEffectRow() &&lastEffectCell(obj)&& srcobj.tagName!="SELECT"){
				addRow(tableid);
				window.event.returnValue=false;
				}
				if(!tempDTable.isLastEffectRow() && lastEffectCell(obj)){
				next(tableid,0)
				window.event.returnValue=false;
				}
		}
	/*
		if (window.event.keyCode==38)  //up arrow
		{
			if (window.document.activeElement.tagName.toLowerCase()!="select")
			previous(tableid,obj.cellIndex);
		}
		if (window.event.keyCode==40) //down arrow
		{
			if (window.document.activeElement.tagName.toLowerCase()!="select")
			next(tableid,obj.cellIndex);
		}
		*/
		if (window.event.keyCode==36) //home
		{
  		if (window.document.activeElement.tagName.toLowerCase()!="select")
			previous(tableid,obj.cellIndex);

			//first(tableid,obj.cellIndex);
		}
		if (window.event.keyCode==35) //end
		{
			if (window.document.activeElement.tagName.toLowerCase()!="select")
			next(tableid,obj.cellIndex);
	
			//last(tableid,obj.cellIndex);
		}		
	}	
	function lastEffectCell(obj){
var tr=obj.parentElement;
 if(obj.cellIndex==tr.cells.length-1) return true;
		for(var i=obj.cellIndex+1;i<tr.cells.length;i++){
			var bb=tr.cells[i].all;
			for(var j=0;j<bb.length;j++){
					if(bb[j].tagName){//是否有tagname
							 if(bb[j].tagName=='INPUT' || bb[j].tagName=='SELECT' ){
							 //alert(bb[j].name+ " :" + bb[j].tagName +" :" +bb[j].tabIndex) 
								 if(bb[j].tabIndex!="-1") return false;
							}else{
							   continue;
							}//end if(bb.tagName=='INPUT' || bb.tagName=='SELECT' ){
					}//end if bb.tagName
			}//end for j
			if(i==tr.cells.length-1) return true;
		}//end for i
		return false;
}
function doSubmitForm(actionType){


	var truthBeTold = true;// = window.confirm("");
	switch (actionType)
	{
	case 'Delete':
		truthBeTold = window.confirm("该操作将删除数据库中的数据,且不能自动恢复,请确认");
		break;
	case 'Update':
		truthBeTold = window.confirm("该操作将修改数据库中的数据,且不能自动恢复,请确认");
		break;
  case 'Save':
		truthBeTold = window.confirm("该操作将修改数据库中的数据,且不能自动恢复,请确认");
		break;
	case 'Check':
		truthBeTold = window.confirm("该操作将修改数据库中的数据,且不能自动恢复,请确认");
		break;
  case 'Query':
		truthBeTold = true;
		break;
	default:
		break;
	}
  if(!truthBeTold){
		return false;
	}
	
document.all.actionType.value=actionType;
document.forms[0].submit();
}
	function getObjRow(obj){
		 if(!obj) return null;
		 while(obj.tagName!="TR"){
		 obj=obj.parentElement;
		 }
		 return obj;
	}
	function getObjRowIndex(obj){
		 return getObjRow(obj).rowIndex;
	}
	var returnStr="";
	//退出
function tuichu(){
	if(returnStr==null || returnStr==""){
		returnStr="zhsbAction.do";
	}
	//如果是由综合申报进入申报资料页面，则退出到综合申报页面
	if(document.all.iszhsb && document.all.iszhsb.value=='1')
		returnStr="zhsbAction.do?actionType=Show";
	window.location=returnStr;
}
function publicMethod(){
return false;
}

//document.onmouseup=hideSelectDiv
//document.onkeydown=hideSelectDiv

//计时
var timerID = null;
var timerRunning =false;
var startmark;

function setStartMark(){
	var starttime=new Date();
	var startseconds=starttime.getSeconds();
	var startminutes=starttime.getMinutes();
	var starthours=starttime.getHours();
	startmark=starthours*60*60+startminutes*60+startseconds
}

function startclock () {
	setStartMark();
  clearTimeout(timerID);
  showtime();
}

function showtime () {
  var now = new Date();
  var nowseconds = now.getSeconds();
  var nowminutes=now.getMinutes();
  var nowhours=now.getHours();
  nowmark=nowhours*60*60+nowminutes*60+nowseconds
  period=nowmark-startmark
  period=(period>=0) ? period : period+86400
  seconds=period%60;
  minutes=((period-seconds)/60)%60;
  hours=(period-seconds-(60*minutes))/3600;
  var timeValue=""+((hours<10)?"0":"")+hours
  timeValue+=((minutes<10)?":0":":")+minutes
  timeValue+=((seconds<10)?":0":":")+seconds
  face.innerHTML = timeValue;
  timerID = setTimeout("showtime()",1000);
}

function jhDisabled(){
	var obj = document.forms[0];
	for(var i=0;i<obj.elements.length;i++){
		obj.elements[i].disabled = true;
		obj.elements[i].style.backgroundColor = "#E3E3E3";
		floater.style.visibility = "visible";
		startclock();
	}
}
/****************START Hu Xiaofeng 2005-8-16******************************/
function checkNsrzt(){
	if(nsrzt=='30'){
		alert('此纳税人状态为非正常！');
	}else if(nsrzt=='20'){
		alert('此纳税人状态为停业！');
	}else if(nsrzt=='40'){
		alert('此纳税人状态为注销！');
	}else if(nsrzt=='41'){
		alert('此纳税人状态为吊销！');
	}else if(nsrzt=='50'){
		alert('此纳税人状态为在途！');
	//modified by hazhengze 2005-12-22 10:09 START
	}else if(nsrzt=="99"){
		//do nothing;
	}
	//modified by hazhengze 2005-12-22 10:09 END
}
/************end Hu Xiaofeng 2005-8-16**********************************/