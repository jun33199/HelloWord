//����һ��
	function addLabelCol(trId){
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
	  	var r1=t.insertRow(t.rows.length-1);
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
	
	/*
	 * ��ǰʵ�֣����ڲ��ã����·���������·�������ͬ������
	 * 
  	//ɾ��һ��
	function removeLabelCol(trId){
	    var a=[],b=[],t=_p(event.srcElement,"table");
	    var size=t.rows.length;
	    var c = document.getElementsByName("labelFlag");
	    var num =0;
	    for(var m=0;m<=c.length-1;m++)
	     if(c[m].checked)
	        num ++;
	     if(num<=0) {
		     alert('��ѡ����Ҫɾ������!');
		     return false ;
	     } else {
	         if(!confirm('ȷ��Ҫɾ��ѡ�������')){
	            return false;
	         }
	       	 delLabelCol(trId);
	     } 
	}  	
	function delLabelCol(table_name){
		//getDelRowArr(table_name);
		var a=[],t=document.getElementById(table_name); 
		for(var i=0,c=document.getElementsByName("labelFlag");i<=c.length-1;i++){
		  if(c[i].checked){
		    	a.push(i+2);//i+2 ��2��ʾ��table������������
		    }
		}    
		while(a.length && t.rows.length > 2) {
		  t.deleteRow(a.pop());
		}
	}*/
	
	
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
							//alert("����Ҫ��ɾ������"+index);
							delRows.push(index);
						}					
						
					}					
					
				}
				

			}
			
		}
		//alert("Ҫɾ��������Ϊ"+delRows.length);
		return delRows;
	}
	
	
	
	//��������Ϣ��������̨���д���,���ݵ�ÿһ����^�ָ�
	function getMMFXXContext(table_name){	
		var table_obj = document.getElementById(table_name);
	    var arr = new Array();
	    var str;
	    for(var i=2;i <table_obj.rows.length-1;i++){
	    	//alert("outer");
	        var b = new Array(); 
	        //alert(table_obj.rows[i].cells.length-1);
            for(var j=0;j <table_obj.rows[i].cells.length-1;j++){     
                //alert(table_obj.rows[i].cells[j].childNodes[0].value);
            	var tempVal = table_obj.rows[i].cells[j].childNodes[0];
            	
            	if(tempVal.value == ""){
            		if(table_name == "newAddTab"){
            			alert("����˰�����Ȩ����Ϣ��д����ȷ������δ��д��Ϣ�");
            		}else{
            			alert("��������Ϣ��д����ȷ������δ��д��Ϣ�");
            		}
            		
            		tempVal.focus();
            		return false;
            	}
            	
                b.push(tempVal.value);
                //b.join("~");
            }
            str = b.join("~");
            arr.push(str);
        }
	    //alert(arr.join("^"));
	    //setOneGroupInfo(b,table_obj);
	    //parseSaveBuyorSellInfo(arr.join("^").toString(),"sellTab",document.forms[0].all_sellerInfo);
	    
	    if(table_name == "sellTab" ){
	    	document.getElementById('all_sellerInfo').value = arr.join("^");
	    }
	    if(table_name == "buyTab"){
	    	document.getElementById('all_buyerInfo').value = arr.join("^");
	    	//alert(document.getElementById('all_buyerInfo').value);
	    }
        //document.getElementById('arr').value = arr;
	    
	    if(table_name == "newAddTab"){
	    	document.getElementById('allNewAddInfo').value = arr.join("^");
	    }
	    
	    
	    return true;
	}