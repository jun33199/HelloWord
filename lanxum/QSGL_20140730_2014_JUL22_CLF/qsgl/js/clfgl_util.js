	//���ν����Ƿ�Ϊ�״������ѹ���������  
	  function getsfscssgf_mc(dm){
		  
	  	var mc ="";
		  switch (dm)
		  {
		  case '0':
		    mc="��";
		    break;
		  case '1':
			mc="��";
		    break; 
		  }
		  return mc;
	  }
	//�����ṹ����
	  function getjzjg_mc(dm){
		  
		  	var mc ="";
			  switch (dm)
			  {
			  case '1':
			    mc="�ֽṹ";
			    break;
			  case '2':
				mc="�ֽ�������ṹ";
			    break;
			  case '3':
					mc="��Ͻṹ";
				    break;
			  case '4':
					mc="שľ�ṹ";
				    break;
			  case '5':
					mc="�����ṹ";
				    break;	
			  case '6':
					mc="�֡��ֽ�������ṹ";
				    break;
			  default:
				  mc="�����ṹ";
					break;	    
			  }
			  return mc;
		  }
	//���������
	function nsrlb_mc(dm){
	  	var mc ="";
		  switch (dm)
		  {
		  case '0':
		    mc="�Ǹ���";
		    break;
		  case '1':
			mc="����";
		    break; 
		  default:
			  mc="�Ǹ���";
				break;		    
		  }
		  return mc;
	}
	
	//������֤���������
	function nsrzjlb_mc(dm){
	  	var mc ="";
		  switch (dm)
		  {
		  case '1':
			    mc="���֤";
			    break;
		  case '2':
				mc="������ס֤����ס֤";
			    break;
		  case '3':
				mc="����";
			    break;
		  case '4':
				mc="����֤";
			    break;
		  case '5':
				mc="Ӫҵִ��";
			    break;	
		  case '6':
				mc="����";
			    break;
		  case '7':
			    mc="̨��֤";
			    break;
		  case '8':
				mc="������½ͨ��֤";
			    break;
		  case '9':
				mc="ͳһ����";
			    break;
		  case '10':
				mc="��۾������֤";
			    break;
		  case '11':
				mc="����֤";
			    break;	
		  case '12':
				mc="��ҵ�Ǽ�֤";
			    break;			    
		  default:
			  mc="����";
				break;	    
		  }
		  return mc;
	
	}

/*
	*time ��ʽ yyyyMMdd  ��  yyyy-mm-dd
	*
	*returnType ��������  yyyy��mm��dd��  ��  yyyymmdd
	*/
	function transTime(timeName,time,returnType){
		if(time == "" || time == null){
			return "";
		}
		var type_1="yyyy��mm��dd��";
		var type_2 ="yyyymmdd";

		var y = "";//yyyy
		var m = "";//mm
		var d = "";//dd

	    if (time.length == 8){
			y = time.substr(0,4);
			m = time.substr(4,2);
			d = time.substr(6,2);

			if(returnType == type_1){
				return y + "��" + m + "��" + d +"��";
			}else if (returnType == type_2){
				return time;
			}

		}else if(time.length == 10){
			y = time.substr(0,4);
			m = time.substr(5,2);
			d = time.substr(8,2);

			if(returnType == type_1){
				return y + "��" + m + "��" + d +"��";
			}else if (returnType == type_2){
				return y + "" + m + "" + d;
			}

	   }else{
			alert(timeName +"���Ȼ������������޷�����������Ӧ��ΪyyyyMMdd��yyyy-mm-dd,�����ʱ��Ϊ"+time);
			return time;
	   }
	}
	
	
	function isDate(obj, empty) {
		if (window.event.keyCode != 13 && window.event.keyCode != 0
				&& obj == window.event.srcElement)
			return true;

		var strDate = obj.value;
		var succeed = true;
		var alertStr = "";

		if (strDate.length == 0) {
			if (empty != null) {
				alertStr += "����ֵ���벻Ϊ��!\n"
				succeed = false;
			}

		}
		if (strDate.length != 8 && succeed) {
			alertStr += "���ڸ�ʽ����ȷ!\n"
			succeed = false;
		}
		var strYear = strDate.substr(0, 4);
		var strMonth = strDate.substr(4, 2);
		var strDay = strDate.substr(6, 2);
		var objDate = new Date(strMonth + '-' + strDay + '-' + strYear);
		if (isNaN(objDate) && succeed) {
			alertStr += "���ڸ�ʽ����ȷ!\n"
			succeed = false;
		}
		if (strYear * 1 < 1900 && succeed) {
			alertStr += "���ڸ�ʽ����ȷ!\n"
			succeed = false;
		}
		if ((strYear * 1 != objDate.getYear() * 1)
				&& (strYear * 1 != objDate.getYear() * 1 + 1900) && succeed) {
			alertStr += "���ڸ�ʽ����ȷ!\n"
			succeed = false;
		}
		if (strMonth * 1 != objDate.getMonth() * 1 + 1 && succeed) {
			alertStr += "���ڸ�ʽ����ȷ!\n"
			succeed = false;
		}
		if (strDay * 1 != objDate.getDate() * 1 && succeed) {
			alertStr += "���ڸ�ʽ����ȷ!\n"
			succeed = false;
		} //don't call getDay function.
		if (alertStr != "") {
			alert(alertStr);
			window.event.returnValue = false;
			obj.focus();
			obj.select();
		}

		return succeed;
	}
	
	  //�ı�ɼ���ʽ
	  function  changeCjfs(obj){
		  //ת��ά��ɨ��
		  if(obj.value == "01"){
			  doSubmitForm("Show");
			  
		  }
		  
		  //ת�ֹ��ɼ�
		  if(obj.value == "02"){
			  doSubmitForm("ToSGCJ");
			  
		  }
	  }
	
	
