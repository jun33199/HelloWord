//��ʾ�Ƿ���Ҫ��������˰
var isadditions = false;
//�����������Ƶõ��������
function get_obj(objname){
  return eval(objname);
}

//���ݹؼ�ֵ���ص�һ��1ά����
function get_subobj(arrayName,key){
	var obj = get_obj(arrayName);
	for(var ii=0;ii<obj.length;ii++){
		if(obj[ii][0]==key) return obj[ii];
  }
  return null;
}

//���ݹؼ�ֵ����2ά����
function get_subobjs(arrayName,key){
  var obj = get_obj(arrayName);
  var tempArray = new Array();
  for(var ii=0;ii<obj.length;ii++){
		if(obj[ii][0]==key) tempArray.push(obj[ii]);
  }
  return tempArray;
}

//������ǰ��-�����ֶζ���ֵ�����ж���
function setTheRow(fields,values,row){
	if(!(fields && values && row)) return;
	//��д���и�ֵ
	for(var i=0;i<fields.length;i++){
		var obj = row.all(fields[i]);
		if(!obj) continue;
		obj.value=values[i];
	} 
	publicMethod();
	//�ж��Ƿ��ǿ�˰������
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

//������ǰ��-��������������������
function setRow(arrayName,obj){
		var fields = get_obj(arrayName+"_fields");
		var values = get_subobj(arrayName,obj.value);
		var row = getObjRow(obj);
		setTheRow(fields,values,row);
}

//-------------------------------------------

//������ǰ�м�����˰(����˰���벻�ɸ�,���ᴥ���˷���)
function setAdditions(nameHead,tableid,obj){
//	as("setAdditions");
	/*****�⼮����������ʾ���ִ����б�******/
	if(nameHead=="bzdm"){
		var values = get_subobj("bzlist",obj.value);
		var fields = get_obj("bzlist_fields");
		var row = getObjRow(obj);
				var oldCode = row.all("bzdm_old").value;
		//alert(row.rowIndex+" :"+obj.value+" :"+oldCode)
		//ֵδ��ʱ�����κβ���
		if(obj.value==oldCode) return;

	//���Ϸ�������ʹֵ���䣨����գ�values��ȫ������ֵ����return�������������������˰��
		if(!values){
			obj.value=oldCode;
		}
		setTheRow(fields,values,row);
		//����λ�ñ�־		
		document.all(nameHead+"_focus").value = "0";
		//�ر�div
	  document.all(nameHead+"_epodDateLayer").style.display = 'none';	
		tempSelect.srcObj.select();
		return;  
	}
	/*****�⼮����������ʾ���ִ����б�******/
	var values = get_subobj("szsmlist",obj.value);
	var fields = get_obj("szsmlist_fields");
	var row = getObjRow(obj);
	//ԭ˰Ŀ����

	var oldCode = row.all("szsmdm_old").value;
		//alert(row.rowIndex+" :"+obj.value+" :"+oldCode)
		//ֵδ��ʱ�����κβ���
	if(obj.value==oldCode) return;

	//���Ϸ�������ʹֵ���䣨����գ�values��ȫ������ֵ����return�������������������˰��
	if(!values){
		obj.value=oldCode;
		return;
	}

	//���õ�ǰ��
	setTheRow(fields,values,row);
	//ɾ����˰Ŀ����
	delSzsmList(nameHead,obj.value)
	
	//����λ�ñ�־
	document.all(nameHead+"_focus").value = "0";
	//�ر�div
	document.all(nameHead+"_epodDateLayer").style.display = 'none';
	tempSelect.srcObj.select();
	

	if(!isadditions){
		delSonRow(nameHead,tableid,oldCode);
		return;
	}

	//���Ҹ���˰(szsmlist_add��˰��˰Ŀ����͸���˰�Ķ�Ӧ��ϵ)
	var list = get_subobjs("szsmlist_add",obj.value);//����˰����
	var addCode;
	for(var ii=0;ii<list.length;ii++){
		var addCode = list[ii][1];//����˰����
		if(findRowIndex(tableid,addCode)>=0) continue;
		var row = addSonRow(tableid);//����һ�У��������ж���
		var values = get_subobj("szsmlist",addCode);
		setTheRow(fields,values,row);
		//delSzsmList(addCode)//maybe not needed
	}
	//����˰Ŀ����ı�ʱ,�ж��Ƿ�ɾ��֮ǰ�ĸ���˰
	delSonRow(nameHead,tableid,oldCode);
}

//ɾ������˰(��ɾ������֮�����)
function delSonRow(nameHead,tableid,father){
	if(!father || father==null || father=="") return;
	addSzsmList(nameHead,father);
	if(!isadditions) return;
	var sons = getSons(father);
	for(var ii=0;ii<sons.length;ii++){
		var rowIndex = findRowIndex(tableid,sons[ii]);
		if(rowIndex<0) continue;
		//�������˰���ڣ������丸
		fathers = getFathers(sons[ii]);
		for(var jj=0;jj<fathers.length;jj++){
			if(findRowIndex(tableid,fathers[jj])>=0){
				rowIndex = -1;
				break;
			}
		}
		//���и�������ʱɾ������
		if(rowIndex>=0) delRowByIndex(tableid,rowIndex);
	}	
}

//����˰Ŀ�Ҹ���˰(һά����)
function getSons(father){
	if(!isadditions) return new Array();
  var obj = get_obj("szsmlist_add");
  var tempArray = new Array();
  for(var ii=0;ii<obj.length;ii++){
		if(obj[ii][0]==father) tempArray.push(obj[ii][1]);
  }
  return tempArray;
}

//���ݸ���˰��˰Ŀ(һά����)
function getFathers(son){
	if(!isadditions) return new Array();
  var obj = get_obj("szsmlist_add");
  var tempArray = new Array();
  for(var ii=0;ii<obj.length;ii++){
		if(obj[ii][1]==son) tempArray.push(obj[ii][0]);
  }
  return tempArray;
}

//����һ��(����˰),�����ж���
function addSonRow(tableid){
	var index =addRow(tableid);
	return eval(tableid).rows[index];
}

//�����к�ɾ����
function delRowByIndex(tableid,rowIndex){
	var rows = eval(tableid).rows;
	rows[rowIndex].removeNode(true);
}

//����˰Ŀ�Ƿ����,����ʱ�����к�,������ʱ����-1
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

//��������ǰ׺������
function getArrayObjs(nameHead){
	valuelist = new Array();
	namelist = new Array();
	valuelist_del = eval(nameHead+"_value_del");
	namelist_del = eval(nameHead+"_name_del");
	valuelist_all = eval(nameHead+"_value_all");
	namelist_all = eval(nameHead+"_name_all");
	//����ĸ���
	for(var ii=0;ii<valuelist_all.length;ii++){
		valuelist.push(valuelist_all[ii]);
		namelist.push(namelist_all[ii]);
	}
}

//�������ĸ�ֵ
function setArrayObjs(nameHead){
	eval(nameHead+"_value = valuelist;");
	eval(nameHead+"_name = namelist;");
}

//ɾ��һ��˰Ŀ
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

//����һ��˰Ŀ
function addSzsmList(nameHead,key){
	//alert("addSzsmList");
	getArrayObjs(nameHead);
	//ɾ��valuelist_del�еĸ�Ԫ��
	for(var ii=0;ii<valuelist_del.length;ii++){
		if(valuelist_del[ii]==key) {
			//alert("key:"+key);
			valuelist_del.splice(ii,1);
			namelist_del.splice(ii,1);
			break;
		}
  }
	//valuelist_delתΪ�ַ���
	var delStr = ","+valuelist_del.toString()+",";

	//�����б�
	for(var ii=0;ii<valuelist.length;ii++){
		if(delStr.indexOf(","+valuelist[ii]+",")<0) continue;
		valuelist.splice(ii,1);
		namelist.splice(ii,1);			
  }
	setArrayObjs(nameHead);
}

//��������ʱ�ύ
function selectClick(nameHead,tableid){	
	//ʹ���ظ�ִ��
	if(tempSelect.selectObj.style.display=='none') return;
	tempSelect.enterTips(13,tempSelect.srcObj);
	setAdditions(nameHead,tableid,tempSelect.srcObj);
	window.event.returnValue=false;

}

//�������ƶ�ʱ�ж�
function selectMove(nameHead,tableid){
	var keyCode = window.event.keyCode;
	if(keyCode==13){
		selectClick(nameHead,tableid);
	}else if(keyCode==38 || keyCode==40){
		
		tempSelect.srcObj.value = tempSelect.selectObj.value;//document.all(nameHead+"_epodDateLayer").all.sel.value;
	}
}

//onblur�¼�����
function resetRow(nameHead,tableid,obj){
  //���������select�ϣ�ֱ��return
	try{
		if(document.all(nameHead+"_focus").value==nameHead) return;
		
		//���˰Ŀ����仯������ֵ
		var row = getObjRow(obj);		
		if(row.all(nameHead).value!=row.all(nameHead+"_old").value ) {
			setAdditions(nameHead,tableid,obj);
		}	
		/* Delete by huipeijie 2003-11-18
		���û��select��ʾ��ʹ֮�ر�
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

//onkeydown�¼�����
function doEnterDown(nameHead,tableid,obj){

	try{
		if(window.event.keyCode!=13) return;	
		
	if(!tempSelect) return;
	//enter->tab
	window.event.keyCode = 9;
	//ִ�д���
	tempSelect.enterTips(13,tempSelect.srcObj);
	setAdditions(nameHead,tableid,obj);
	}catch(e){
		//alert(e+"doEnterDown"); Delete by huipeijie 2003-11-18
	}

}

//----Huipj------------------
//onkeyup������ʾ���ύ
function setday(nameHead,tableid,obj) 
{	
	tempSelect = new InputSelect(event.srcElement,document.all(nameHead+"_epodDateLayer").all.sel,eval(nameHead+"_name"),eval(nameHead+"_value"));
	anchor(obj,nameHead);	
//modify by huipeijie 2003-11-17
//	if(obj!=srcobj &&��srcobj) return; modify by huipeijie for alt a do not show 2003-11-18 
if(obj!=srcobj) return; 
		tempSelect.showtips(obj);
	if(window.event.keyCode==40){		
		//���������б�
		if(document.all(nameHead+"_epodDateLayer").all.sel.options.length<1) return;
		document.all(nameHead+"_epodDateLayer").all.sel.focus();
		document.all(nameHead+"_epodDateLayer").all.sel.options[0].selected = true;
		obj.value = tempSelect.selectObj.value;
		document.all(nameHead+"_focus").value = nameHead;
	}else if(window.event.keyCode==13){
		//ִ�д���;
		tempSelect.enterTips(window.event.keyCode,tempSelect.srcObj);
		setAdditions(nameHead,tableid,obj);
	}
}

//��ʾdiv
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

