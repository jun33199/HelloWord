/**
 * ��js�ļ�˽�з���
 * 
 * @param 
 */
function getCellObject(){
   	var id = trim(window.event.srcElement.id + "");
  	var obj = document.getElementById(id);
  	return obj;
}

/**
 * ��ʼ�����������ı���
 * 
 * @param �ı���������
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
 * �ַ�����
 * �÷�:
 *   Parameter: 
 *  page---Form����
 *  list--�Ƿ��ַ��б�(�ԵȺŸ������ַ���)
 *   ȫ�������򷵻�true;
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
							alert("�����������зǷ��ַ�,�벻Ҫʹ�ð�ǵı�����!");
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
 * ��ӦС���㡰.�������룬�Զ���Ϊ��0.������ʽ
 * 
 * @param 
 */
 function formateSign(){
 		//keyCode=190Ϊ���̵�С������룬keyCode=110ΪС���̵�С�������
		if(event.keyCode==190||event.keyCode==110){
			var obj = getCellObject();
			if(obj.value=="."){
				obj.value = "0.";
			}
		}
	}
	
/**
 * ��ӦС���㡰.�������룬�Զ���Ϊ��0.������ʽ�����ڶ�̬�����е�������
 * ��̬������û�й涨��Ԫ���ID��getCellObject()ȡ����������Ҫ����������
 * @param 
 */
 function formateSign1(obj){
 		//keyCode=190Ϊ���̵�С������룬keyCode=110ΪС���̵�С�������
		if(event.keyCode==190||event.keyCode==110){
			//var obj = getCellObject();
			if(obj.value=="."){
				obj.value = "0.";
			}
		}
	}

/**
 * ��ʽ����������ΪС�������λ
 * 
 * @param �ı���
 */
function formate(obj){
	//if(!isNumber(obj.value)){
	//	alert("��ʹ�����ָ�ʽ���룡");
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
 * ��¼������ָ��Ի�Ϊ�ٷ�����ʽ
 *     �磺25 --> 25%
 * @param �ı���
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
 * ��С����ʽΪ�ٷ�����ʽ
 *     �磺0.25 --> 25%
 * @param �ı���
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
 * ��ʽ���ٷ�����ΪС��
 * 
 * @param �ı���
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
 * ת���س���Ϊtab��
 * 
 */
function reponseEnterKey(){
	if(event.keyCode==13) event.keyCode = 9;
}

/**
 * ��һ��
 * 
 */
function gotoNextTable(){
	document.getElementById("next").href=document.forms[0].nextTableURL.value;
}

/**
 * ��һ��
 * 
 */
function gotoPreviousTable(){
	document.getElementById("previous").href=document.forms[0].previousTableURL.value;
}

/**
 * �޸ĵ�Ԫ���Tabindex��ֵ,�Ա��ڰ��»س�����Tab��������ֻ���ĵ�Ԫ��
 * 
 *   page--������
 * 
 */
function InitTabindex(page){
		//len��Form�ĵ�Ԫ����Ŀ		
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
