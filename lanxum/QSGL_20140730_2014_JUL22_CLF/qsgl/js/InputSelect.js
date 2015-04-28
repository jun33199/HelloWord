//可输入的下拉菜单的结构函数
//srcOBJ参数 调用下拉菜单的INPUT OBJ
//value参数 下拉菜单的value集合
//descr 下拉菜单的descr集合
function InputSelect(obj,selObj,value,descr){
	this.srcObj=obj;
	this.selectObj=selObj;
	this.msgValue=value;
	this.msgDescr=descr;
  this.showtips=dyt_showtips;
	this.enterTips=dyt_enterTips;
  this.rv=dyt_rv;
	this.c=dyt_c;
	return this;
}

function dyt_showtips(obj){
try{
//eo=window.event.srcElement;
this.selectObj.length=0;
var len=this.msgValue.length;
var re=new RegExp("^"+obj.value,"i")
var j=0
//add by huipeijie 2003-11-17	
var namedselobj=document.all.sel;
if(namedselobj && namedselobj.length){
	for(var i=0;i<namedselobj.length;i++){
		namedselobj[i].style.display='none';
		namedselobj[i].parentElement.style.display='none';
		namedselobj[i].parentElement.top=-2000;
		namedselobj[i].parentElement.left=-2000;

	}//end for
}//end add
for(var i=0;i<len;i++) if(re.test(this.msgValue[i])==true){ 

	this.selectObj.add(new Option(this.msgValue[i],this.msgDescr[i]));j++}
	
if(this.selectObj.length>0){
this.selectObj.parentElement.style.display='';
this.selectObj.style.display='';
}
	this.selectObj.size = (j>11)?11:11;
	obj.focus=true;
	var oParent = this.selectObj.offsetParent;
	var parentHeight = oParent.clientHeight;
	var selOfftop = this.selectObj.offsetTop;
	if(selOfftop+this.selectObj.clientHeight > parentHeight){
		this.selectObj.style.posTop = -(this.srcObj.clientHeight+this.selectObj.clientHeight+5);//5 --> border or margin
	}
	this.selectObj.style.posLeft = this.srcObj.offsetLeft-10;
}catch(e){
	alert(e+"dyt_showtips")
}
}

function dyt_enterTips(e){
	if(this.selectObj.style.display!='none'){
		if(e==13) {
			if(this.selectObj.options.length>0){
				var value = this.selectObj.value;
				if (value=="") this.srcObj.value=this.selectObj.options[0].value;
				else this.srcObj.value=value;//this.selectObj.value;
			//window.event.returnValue=false;
			}else{
				//alert(this.msgDescr[0]);
				//this.srcObj.value=this.msgDescr[0];
			}
			this.selectObj.style.display='none';
			this.selectObj.parentElement.style.display='none';
			this.c();//document.all.epodDateLayer.style.display='none';
		}if(e==40) this.selectObj.focus();
	}//else{
	//	this.srcObj.value = "";
	//}
}
function dyt_rv(){
this.srcObj.value=this.selectObj.value;
this.c()
}
function dyt_c(){
var div=document.all.epodDateLayer;
if(!div) return false;
if(div.length>1){
	 for(var i=0;i<div.length;i++){
		 div[i].style.display='none';
	 }
}else{
	   div.style.display='none';
}
//this.srcObj.focus()
}
