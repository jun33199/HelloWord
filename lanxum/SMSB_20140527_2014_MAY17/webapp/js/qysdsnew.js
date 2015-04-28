/**
 * 本js文件私有方法
 * 
 * @param 
 */
function getCellObject(){
   	var id = trim(window.event.srcElement.id + "");
  	var obj = document.getElementById(id);
  	return obj;
}

/**
 * 初始化数字内容文本框
 * 
 * @param 文本框数组名
 */
function initNumText(colName,align){
	var arr=document.all(colName);
	for(var i=0;i<arr.length;i++){
		
		if(arr[i].readOnly!=true){
			//arr[i].maxLength=16;
			//arr[i].size=16;
			arr[i].style.textAlign ="right";
			arr[i].onkeyup = function(){formateSign();}
			arr[i].onblur = function(){return isNum(getCellObject() , null, 9999999999999, null, 16, 2);}
		}
	}
}
/**
 * 字符测试
 * 用法:
 *   Parameter: 
 *  page---Form对象
 *  list--非法字符列表(以等号隔开的字符串)
 *   全部可以则返回true;
 */
function Char_Vaildate(page,list){
		
		//var list="=\\=] ]>=&lt=&gt=&amp=&apos=&quot=<=\"=>=,=?=/=|=]=[=}={=;=:=)=(";
		
		var c_ff=list.split("=");
		
		var msg=list.replace();
		var len = page.length;
		for(j=0;j<len;j++){
			if((page.elements[j].type=="text")||(page.elements[j].type=="textarea")){
				var context=page.elements[j].value;
				if((context!="")&&(context!=null)){
					page.elements[j].style.color="#000000";
				}
			}
		}
		for(i=0;i<len;i++){
			if((page.elements[i].type=="text")||(page.elements[i].type=="textarea")){
				if(page.elements[i].readOnly){
					continue;
				}
				var context=page.elements[i].value;
				if((context!="")&&(context!=null)){
					var context_temp=context;
					var context_name= page.elements[i].name
					page.elements[i].style.color="#000000";
					
					for(m=0;m<c_ff.length;m++){
						if(context_temp.indexOf(c_ff[m])!=-1){
							alert("您的输入中有非法字符,请不要使用半角的标点符号!");
							page.elements[i].style.color="red";
							page.elements[i].focus();
							return false; 
						}
					}
					
				}
			}
		}
		return true;
	}

/**
 * 响应小数点“.”的输入，自动变为“0.”的形式
 * 
 * @param 
 */
 function formateSign(){
 		//keyCode=190为键盘的小数点的码，keyCode=110为小键盘的小数点的码
		if(event.keyCode==190||event.keyCode==110){
			var obj = getCellObject();
			if(obj.value=="."){
				obj.value = "0.";
			}
		}
	}
	
/**
 * 响应小数点“.”的输入，自动变为“0.”的形式，用于动态增加行单独调用
 * 因动态增加行没有规定单元格的ID，getCellObject()取不到对象，需要传入对象参数
 * @param 
 */
 function formateSign1(obj){
 		//keyCode=190为键盘的小数点的码，keyCode=110为小键盘的小数点的码
		if(event.keyCode==190||event.keyCode==110){
			//var obj = getCellObject();
			if(obj.value=="."){
				obj.value = "0.";
			}
		}
	}

/**
 * 格式化输入数据为小数点后两位
 * 
 * @param 文本框
 */
function formate(obj){
	//if(!isNumber(obj.value)){
	//	alert("请使用数字格式输入！");
	//	obj.focus();
	//	return false;
	//}
	if(obj.value==""||obj.value==null){
		return false;	
	}else{
		var temp = trim(obj.value+"");
		if(temp.indexOf(".")!=-1){
			var len=temp.indexOf(".")+1;
			if(temp.length-len==1)
			temp = temp+"0";
		}else{
			temp=temp+".00";
		}
		obj.value=temp;	
	}		
}

/**
 * 将录入的数字格试化为百分数形式
 *     如：25 --> 25%
 * @param 文本框
 */
function formateParcent(obj)
{
	var temp = obj.value;
	//alert("temp = " + temp);
	if(parseFloat(temp) * 1 != 0)
	{
		temp = temp + "%";
	}
	obj.value=temp;			
}

/**
 * 将小数格式为百分数形式
 *     如：0.25 --> 25%
 * @param 文本框
 */
function formate2Parcent(obj, value)
{
	//alert("value = " + value);
	//alert("obj_value * 100 = " + obj.value);
	var temp = parseFloat(value*100).toFixed(2);
	//alert("temp = " + temp);
	if(parseFloat(temp) * 1 != 0)
	{
		temp = temp + "%";
	}
	obj.value=temp;			
}

/**
 * 格式化百分数据为小数
 * 
 * @param 文本框
 */
function formateParcent2Num(obj)
{
	//alert("formateParcent2Num");
	var temp = obj.value;
	//alert("obj.value = " + obj.value);
	
	//alert("temp = " + temp);
	if(parseFloat(temp) * 1 != 0)
	{
		temp = temp.substring(0, (temp.length-1));
		temp = parseFloat(parseFloat(temp)/100);
	}
	else
	{
		temp = 0;
	}
	return temp;
	//obj.value=temp;			
}

/**
 * 转换回车键为tab键
 * 
 */
function reponseEnterKey(){
	if(event.keyCode==13) event.keyCode = 9;
}

/**
 * 下一张
 * 
 */
function gotoNextTable(){
	document.getElementById("next").href=document.forms[0].nextTableURL.value;
}

/**
 * 上一张
 * 
 */
function gotoPreviousTable(){
	document.getElementById("previous").href=document.forms[0].previousTableURL.value;
}

/**
 * 修改单元格的Tabindex的值,以便在按下回车键或Tab键后跳过只读的单元格
 * 
 *   page--表单对象
 * 
 */
function InitTabindex(page){
		//len表单Form的单元格数目		
		var len = page.length;
		
		for(i=0;i<len;i++){
			if((page.elements[i].type=="text")||(page.elements[i].type=="textarea")){
				if(page.elements[i].readOnly){
					page.elements[i].tabIndex = '-1';
				}					
			}
		}		
		return true;
	}
