//表示是否需要带出附加税
var isadditions = false;
//根据数组名称得到数组对象
function get_obj(objname){
  return eval(objname);
}

//根据关键值返回第一个1维数组
function get_subobj(arrayName,key){
	var obj = get_obj(arrayName);
	for(var ii=0;ii<obj.length;ii++){
		if(obj[ii][0]==key) return obj[ii];
  }
  return null;
}

//根据关键值返回2维数组
function get_subobjs(arrayName,key){
  var obj = get_obj(arrayName);
  var tempArray = new Array();
  for(var ii=0;ii<obj.length;ii++){
		if(obj[ii][0]==key) tempArray.push(obj[ii]);
  }
  return tempArray;
}

//带出当前行-根据字段对象、值对象、行对象
function setTheRow(fields,values,row){
	if(!(fields && values && row)) return;
	//填写本行各值
	for(var i=0;i<fields.length;i++){
		var obj = row.all(fields[i]);
		if(!obj) continue;
		obj.value=values[i];
	} 
	publicMethod();
	//判断是否是课税金额可填
	var obj = row.all("asljbs");
	if(!obj) return; 
	if(obj.value!="1" && obj.value!="2" && obj.value!="4" && obj.value!="5"){
		row.all("kssl").readOnly = true;
		row.all("kssl").className = "inputnoborder";
		row.all("kssl").tabIndex=-1;
	}else{
		row.all("kssl").readOnly = false;  
		row.all("kssl").className = "inputalignright";
	}
	row.all.kssl.outerHTML=row.all.kssl.outerHTML;	
}

//带出当前行-根据数组名、触发对象
function setRow(arrayName,obj){
		var fields = get_obj(arrayName+"_fields");
		var values = get_subobj(arrayName,obj.value);
		var row = getObjRow(obj);
		setTheRow(fields,values,row);
}

//-------------------------------------------

//带出当前行及附加税(附加税代码不可改,不会触发此方法)
function setAdditions(nameHead,tableid,obj){
//	as("setAdditions");
	/*****外籍个人用来显示币种代码列表******/
	if(nameHead=="bzdm"){
		var values = get_subobj("bzlist",obj.value);
		var fields = get_obj("bzlist_fields");
		var row = getObjRow(obj);
				var oldCode = row.all("bzdm_old").value;
		//alert(row.rowIndex+" :"+obj.value+" :"+oldCode)
		//值未变时不作任何操作
		if(obj.value==oldCode) return;

	//不合法的数据使值不变（或清空：values内全部赋空值，不return，会连带清空所带附加税）
		if(!values){
			obj.value=oldCode;
		}
		setTheRow(fields,values,row);
		//焦点位置标志		
		document.all(nameHead+"_focus").value = "0";
		//关闭div
	  document.all(nameHead+"_epodDateLayer").style.display = 'none';	
		tempSelect.srcObj.select();
		return;  
	}
	/*****外籍个人用来显示币种代码列表******/
	var values = get_subobj("szsmlist",obj.value);
	var fields = get_obj("szsmlist_fields");
	var row = getObjRow(obj);
	//原税目代码

	var oldCode = row.all("szsmdm_old").value;
		//alert(row.rowIndex+" :"+obj.value+" :"+oldCode)
		//值未变时不作任何操作
	if(obj.value==oldCode) return;

	//不合法的数据使值不变（或清空：values内全部赋空值，不return，会连带清空所带附加税）
	if(!values){
		obj.value=oldCode;
		return;
	}

	//设置当前行
	setTheRow(fields,values,row);
	//删除该税目代码
	delSzsmList(nameHead,obj.value)
	
	//焦点位置标志
	document.all(nameHead+"_focus").value = "0";
	//关闭div
	document.all(nameHead+"_epodDateLayer").style.display = 'none';
	tempSelect.srcObj.select();
	

	if(!isadditions){
		delSonRow(nameHead,tableid,oldCode);
		return;
	}

	//查找附加税(szsmlist_add：税种税目代码和附加税的对应关系)
	var list = get_subobjs("szsmlist_add",obj.value);//附加税对象
	var addCode;
	for(var ii=0;ii<list.length;ii++){
		var addCode = list[ii][1];//附加税代码
		if(findRowIndex(tableid,addCode)>=0) continue;
		var row = addSonRow(tableid);//新增一行，并返回行对象
		var values = get_subobj("szsmlist",addCode);
		setTheRow(fields,values,row);
		//delSzsmList(addCode)//maybe not needed
	}
	//本行税目代码改变时,判断是否删除之前的附加税
	delSonRow(nameHead,tableid,oldCode);
}

//删除附加税(在删除本行之后调用)
function delSonRow(nameHead,tableid,father){
	if(!father || father==null || father=="") return;
	addSzsmList(nameHead,father);
	if(!isadditions) return;
	var sons = getSons(father);
	for(var ii=0;ii<sons.length;ii++){
		var rowIndex = findRowIndex(tableid,sons[ii]);
		if(rowIndex<0) continue;
		//如果附加税存在，查找其父
		fathers = getFathers(sons[ii]);
		for(var jj=0;jj<fathers.length;jj++){
			if(findRowIndex(tableid,fathers[jj])>=0){
				rowIndex = -1;
				break;
			}
		}
		//所有父不存在时删除该行
		if(rowIndex>=0) delRowByIndex(tableid,rowIndex);
	}	
}

//根据税目找附加税(一维数组)
function getSons(father){
	if(!isadditions) return new Array();
  var obj = get_obj("szsmlist_add");
  var tempArray = new Array();
  for(var ii=0;ii<obj.length;ii++){
		if(obj[ii][0]==father) tempArray.push(obj[ii][1]);
  }
  return tempArray;
}

//根据附加税找税目(一维数组)
function getFathers(son){
	if(!isadditions) return new Array();
  var obj = get_obj("szsmlist_add");
  var tempArray = new Array();
  for(var ii=0;ii<obj.length;ii++){
		if(obj[ii][1]==son) tempArray.push(obj[ii][0]);
  }
  return tempArray;
}

//新增一行(附加税),返回行对象
function addSonRow(tableid){
	var index =addRow(tableid);
	return eval(tableid).rows[index];
}

//根据行号删除行
function delRowByIndex(tableid,rowIndex){
	var rows = eval(tableid).rows;
	rows[rowIndex].removeNode(true);
}

//查找税目是否存在,存在时返回行号,不存在时返回-1
function findRowIndex(tableid,code){
	var rows = eval(tableid).rows;
	for(var ii=0;ii<rows.length;ii++){
		var obj = rows[ii].all("szsmdm");
		if(!obj) continue; 
		if(obj.value==code) return ii;
	}
	return -1;		
}


var valuelist;
var namelist;
var valuelist_del;
var namelist_del;
var valuelist_all;
var namelist_all;

//根据数组前缀找数组
function getArrayObjs(nameHead){
	valuelist = new Array();
	namelist = new Array();
	valuelist_del = eval(nameHead+"_value_del");
	namelist_del = eval(nameHead+"_name_del");
	valuelist_all = eval(nameHead+"_value_all");
	namelist_all = eval(nameHead+"_name_all");
	//数组的复制
	for(var ii=0;ii<valuelist_all.length;ii++){
		valuelist.push(valuelist_all[ii]);
		namelist.push(namelist_all[ii]);
	}
}

//数组对象的赋值
function setArrayObjs(nameHead){
	eval(nameHead+"_value = valuelist;");
	eval(nameHead+"_name = namelist;");
}

//删除一条税目
function delSzsmList(nameHead,key){
	valuelist = eval(nameHead+"_value");
	namelist = eval(nameHead+"_name");
	valuelist_del = eval(nameHead+"_value_del");
	namelist_del = eval(nameHead+"_name_del");

	for(var ii=0;ii<valuelist.length;ii++){
		if(valuelist[ii]==key) {
			valuelist_del.push(valuelist.splice(ii,1));
			namelist_del.push(namelist.splice(ii,1));
			break;
		}
  }
}

//增加一条税目
function addSzsmList(nameHead,key){
	//alert("addSzsmList");
	getArrayObjs(nameHead);
	//删除valuelist_del中的该元素
	for(var ii=0;ii<valuelist_del.length;ii++){
		if(valuelist_del[ii]==key) {
			//alert("key:"+key);
			valuelist_del.splice(ii,1);
			namelist_del.splice(ii,1);
			break;
		}
  }
	//valuelist_del转为字符串
	var delStr = ","+valuelist_del.toString()+",";

	//重设列表
	for(var ii=0;ii<valuelist.length;ii++){
		if(delStr.indexOf(","+valuelist[ii]+",")<0) continue;
		valuelist.splice(ii,1);
		namelist.splice(ii,1);			
  }
	setArrayObjs(nameHead);
}

//下拉表点击时提交
function selectClick(nameHead,tableid){	
	//使不重复执行
	if(tempSelect.selectObj.style.display=='none') return;
	tempSelect.enterTips(13,tempSelect.srcObj);
	setAdditions(nameHead,tableid,tempSelect.srcObj);
	window.event.returnValue=false;

}

//下拉表移动时判断
function selectMove(nameHead,tableid){
	var keyCode = window.event.keyCode;
	if(keyCode==13){
		selectClick(nameHead,tableid);
	}else if(keyCode==38 || keyCode==40){
		
		tempSelect.srcObj.value = tempSelect.selectObj.value;//document.all(nameHead+"_epodDateLayer").all.sel.value;
	}
}

//onblur事件触发
function resetRow(nameHead,tableid,obj){
  //如果焦点在select上，直接return
	try{
		if(document.all(nameHead+"_focus").value==nameHead) return;
		
		//如果税目代码变化，重设值
		var row = getObjRow(obj);		
		if(row.all(nameHead).value!=row.all(nameHead+"_old").value ) {
			setAdditions(nameHead,tableid,obj);
		}	
		/* Delete by huipeijie 2003-11-18
		如果没有select显示，使之关闭
		var s = tempSelect.selectObj.style;		
		if(s.display!='none') {
		s.display='none' ;
			tempSelect.selectObj.parentElement.style.display='none';
		}*/
	}catch(e){
		//alert("resetRow "+e);
	}
}
function as(aa){
	document.all.sbrq.value+=aa+":::";
}

//onkeydown事件触发
function doEnterDown(nameHead,tableid,obj){

	try{
		if(window.event.keyCode!=13) return;	
		
	if(!tempSelect) return;
	//enter->tab
	window.event.keyCode = 9;
	//执行带出
	tempSelect.enterTips(13,tempSelect.srcObj);
	setAdditions(nameHead,tableid,obj);
	}catch(e){
		//alert(e+"doEnterDown"); Delete by huipeijie 2003-11-18
	}

}

//----Huipj------------------
//onkeyup触发显示或提交
function setday(nameHead,tableid,obj) 
{	
	tempSelect = new InputSelect(event.srcElement,document.all(nameHead+"_epodDateLayer").all.sel,eval(nameHead+"_name"),eval(nameHead+"_value"));
	anchor(obj,nameHead);	
//modify by huipeijie 2003-11-17
//	if(obj!=srcobj &&　srcobj) return; modify by huipeijie for alt a do not show 2003-11-18 
if(obj!=srcobj) return; 
		tempSelect.showtips(obj);
	if(window.event.keyCode==40){		
		//焦点移向列表
		if(document.all(nameHead+"_epodDateLayer").all.sel.options.length<1) return;
		document.all(nameHead+"_epodDateLayer").all.sel.focus();
		document.all(nameHead+"_epodDateLayer").all.sel.options[0].selected = true;
		obj.value = tempSelect.selectObj.value;
		document.all(nameHead+"_focus").value = nameHead;
	}else if(window.event.keyCode==13){
		//执行带出;
		tempSelect.enterTips(window.event.keyCode,tempSelect.srcObj);
		setAdditions(nameHead,tableid,obj);
	}
}

//显示div
function anchor(tt,nameHead){

	var txtHeight = tt.clientHeight;
	var txtTop = getPosTop(tt);//obj.offsetTop;
	var txtLeft = getPosLeft(tt);
	var oDiv = document.all(nameHead+"_epodDateLayer");
	var parentHeight = document.body.clientHeight;//oParent.clientHeight;

	//oDiv.style.display = '';
	if(txtTop+oDiv.clientHeight > (parentHeight+document.body.scrollTop)){
		oDiv.style.top = txtTop - oDiv.offsetHeight - 5;//10 --> border or margin
	}else{
		oDiv.style.top = txtTop + txtHeight + 5;
	}
	oDiv.style.left = txtLeft - oDiv.clientLeft;
	oDiv.style.zindex = '0';
	
}
function getPosTop(obj){
	var pos = obj.offsetTop;;
	while(obj.tagName!="BODY"){
		obj = obj.offsetParent;
		pos += obj.offsetTop;
	}
	return pos;
}
function getPosLeft(obj){
	var pos = obj.offsetLeft;;
	while(obj.tagName!="BODY"){
		obj = obj.offsetParent;
		pos += obj.offsetLeft;
	}
	return pos;
}

