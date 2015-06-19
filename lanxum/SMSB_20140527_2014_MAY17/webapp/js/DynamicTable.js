
/**
 * ����һ��
 * @param trId ��ID
 * @param bottom_rows_count  ��ĩβ�м����ǹ̶��ģ�����ʱ�����й̶���֮ǰ��ʼ����
 */
	function addLabelCol(trId,bottom_rows_count){
		//alert("����һ��"+trId);
    	var r = document.getElementById(trId);
	  	var r0= r;
	  	var t = _p(event.srcElement,"table");
	  	//alert(t.rows.length);
	  	
	  	//��tableֻ�����У������ӱ���
	  	if(trId.lastIndexOf("_title")==-1){
	  		if((t.rows.length-1)==1){
	  			addLabelCol(trId+"_title");//�����Ե���
	  		}
	  	}
	  	
	  	//���ӷǱ�����
	  	var r1=t.insertRow(t.rows.length-bottom_rows_count);
		r1.mergeAttributes(r0);
		for(var i=0,c=r0.cells;i<c.length;i++){
		    var x=r1.insertCell();
		    x.innerHTML=c[i].innerHTML;
		    x.mergeAttributes(c[i]);
		}
	}
	function _p(obj,tagName){
		tagName=tagName.toUpperCase();
		while(obj.tagName!=tagName)
		  obj=obj.parentElement;
		return obj;
	}
	
	
  	//ɾ��һ��
	function removeLabelCol(table_name){
	  var delrows = getDelRowArr(table_name);   
	  if(delrows.length <= 0){
		     alert('��ѡ����Ҫɾ������!');
		     return false ;		  
	  }else{
	         if(!confirm('ȷ��Ҫɾ��ѡ�������')){
		            return false;
		         }
		       	 delLabelCol(table_name);		  
	  }
	} 
	
	function delLabelCol(table_name){
		var a=[],t=document.getElementById(table_name); 
		a =getDelRowArr(table_name);
		while(a.length && t.rows.length > 2) {
		  t.deleteRow(a.pop());
		}
	}	
	
	//��õ�ǰtable��Ҫɾ������
	function getDelRowArr(table_name){
		var delRows = new Array();//ɾ������
		var tableObj = document.getElementById(table_name); 
		for(var index =0;index < tableObj.rows.length; index ++){
			var oneRow = tableObj.rows[index];
			var ItemsColl = oneRow.getElementsByTagName("TD");
			
			for(  var subIdx =0; subIdx < ItemsColl.length;subIdx++){
				var inputItemsColl = ItemsColl[subIdx].getElementsByTagName("INPUT");
				
				for(var inpIdx =0; inpIdx < inputItemsColl.length;inpIdx++){
					var oneInputObj = inputItemsColl[inpIdx];
					if(oneInputObj.id=="labelFlag"){
						if(oneInputObj.checked){
							delRows.push(index);
						}					
					}					
				}
			}
		}
		//alert("Ҫɾ��������Ϊ"+delRows.length);
		return delRows;
	}
	
	
	
	//����Ϣƴ�ӵ�һ�𴫵���̨���д���,��¼֮����^�ָ��¼�е�ÿһ����~�ָ�
	function getTableXXContext(table_name,start_row,end_row,target){	
		var table_obj = document.getElementById(table_name);
	    var arr = new Array();
	    var str;
	    for(var i=start_row;i <table_obj.rows.length-end_row;i++){
	        var b = new Array(); 
            for(var j=0;j <table_obj.rows[i].cells.length-1;j++){     
            	var tempVal = table_obj.rows[i].cells[j].childNodes[0];
            	
            	var tempTagName = tempVal.tagName;
            	
            	if(tempTagName != "DIV"&& tempVal.value == ""){
            		alert("��Ϣ��д����ȷ������δ��д��Ϣ�");
            		tempVal.focus();
            		return false;
            	}
            	if(tempTagName != "DIV"){
            		b.push(clertBR(tempVal.value));
            	}
            }
            str = b.join("~");
            arr.push(str);
        }
	    //alert(arr.join("^"));
	    	document.getElementById(target).value = arr.join("^");
	    return true;
	}
	
	//ȥ���س�
	function clertBR(key)
	{
		key = key.replace(/<\/?.+?>/g,"");
		key = key.replace(/[\r\n \"]/g,"");
		key = key.replace(/(^\s*)|(\s*$)/g,"");

		return key;
	}
